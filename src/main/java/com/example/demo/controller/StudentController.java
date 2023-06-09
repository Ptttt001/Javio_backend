package com.example.demo.controller;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.lang.Math;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repository.StudentRepository;
import com.example.demo.responobj.loginrspn;
import com.example.demo.responobj.attstatusrspn;

@RestController
@RequestMapping("/")
public class StudentController {

  private final StudentRepository studentRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public StudentController(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  // 登入:
  // 入:帳號/密碼
  // 回:成功|失敗/課程代碼
  @GetMapping("login/{sid}/{pswd}")
  public ResponseEntity<loginrspn> login(@PathVariable Integer sid, @PathVariable String pswd) {
    String queryString = "SELECT Spwd FROM Student WHERE SID= :sid";//找出有修課的人SID
    Query query = entityManager.createNativeQuery(queryString);
    query.setParameter("sid", sid);
    String result = query.getResultList().toString();// 獲取修課SID清單
    System.out.println(result);
      if (result.equals('['+pswd+']'))// 密碼正確
      {
        queryString = "SELECT CID , ct FROM Coursev2 WHERE SID= :sid";//找出有修課的人SID
        query = entityManager.createNativeQuery(queryString);
        query.setParameter("sid", sid);
        List<Object[]> resultList2 = query.getResultList();// 獲取修課SID清單
        Integer[] dataArray = new Integer[resultList2.size()];
        String[] dataArray2 = new String[resultList2.size()];
        for (int i = 0; i < resultList2.size(); i++) {
          Object[] row = resultList2.get(i);
          Integer cid = (Integer) row[0];
          String ct = (String) row[1];
          dataArray[i] = cid;
          dataArray2[i] = ct;
      }
      loginrspn loginrspn1 = new loginrspn(true, resultList2.size(), dataArray, dataArray2);
      return ResponseEntity.ok(loginrspn1);      
      } 
      else//密碼錯誤
      {
        return ResponseEntity.notFound().build();
      }
    } 


/////////////////////////////////////////////////////////////////////////////////////////////////
  @GetMapping("/get")
  public String startNum() {
      return "更新成功";
  }
/////////////////////////////////////////////////////////////////////////////////////////////////
  // 老師按開啟點名:
  // 入:課程代碼
  // 做: 分配正確數字+學生數字
  // 回:正確數字
  // 完成 釋放完成
  @GetMapping("/holdattendant/{cid}")
  @Transactional
  public Integer[] holdattendant(@PathVariable int cid) {
    LocalDateTime holdtime = LocalDateTime.now();//擷取現在點名時間
    Timestamp timestamp = Timestamp.valueOf(holdtime);//轉成sql用時間格式
    Integer[] correct = new Integer[10];
    for (int i = 0; i <= 9; i++)// 列出10個正確數字
    {
      correct[i] = (int) (Math.random() * 10) + 10 * i;
    }
    String queryString = "SELECT SID FROM Coursev2 WHERE CID= :cid";//找出有修課的人SID
    Query query = entityManager.createNativeQuery(queryString);
    query.setParameter("cid", cid);
    List<Object[]> resultList = query.getResultList();// 獲取修課SID清單
    for (Object Elements : resultList)//跑每一SID
    {
      queryString = "UPDATE Student SET ESCValue = :esc WHERE SID =:sid";//更新正確數值
      query = entityManager.createNativeQuery(queryString);
      query.setParameter("esc", correct[(int) (Math.random() * 10)].toString());
      query.setParameter("sid", Elements.toString());
      query.executeUpdate();//送出個人正確點名數字
      //新增參與資料
    }
    queryString ="INSERT INTO Attendance (CID, SID, Time , status, canrollcall) SELECT cv.CID, cv.SID,  :time , '0', '1' FROM Coursev2 cv WHERE cv.CID = :cid ";
    query = entityManager.createNativeQuery(queryString);
    query.setParameter("time", timestamp);
    query.setParameter("cid", cid);
    query.executeUpdate();//創立該課程attendance
    query = null;
    queryString = null;
    resultList = null;
    holdtime=null;
    timestamp=null;
    return correct;

  }
////////////////////////////////////////////////////////
//老師按結束:
//入:課程代碼/
//做:停止接收學生點名
@GetMapping("/stopattendant/{cid}")
  @Transactional
  public String stopattendant(@PathVariable Integer cid) {
    try {
      String queryString = "UPDATE Attendance SET canrollcall = 0 WHERE CID = :cid AND canrollcall = 1 ";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("cid", cid);
      int rowsUpdated = query.executeUpdate();
      if (rowsUpdated>0)
      {
        return "更新成功";
      }
      else
      {
        return "沒有發起的點名";
      }
    } catch (Exception e) {
      return e.toString();
    }
  }
  /////////////////////////////////////////////////////////
  // 學生按下點名:(確認是否有點名)
  // 入:學生id/課程代碼/日期
  // 回:成功|失敗
  @GetMapping("/ishavingatt/{cid}/{sid}")
  @Transactional
  public boolean stopattendant(@PathVariable Integer cid, @PathVariable Integer sid) {
    try {
      String queryString = "SELECT canrollcall FROM Attendance WHERE CID = :cid AND SID = :sid AND Time = (SELECT MAX(Time) FROM Attendance WHERE CID=:cid)";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("cid", cid);
      query.setParameter("sid", sid);
      String result = query.getResultList().toString();
      System.out.println("result here:" + result);
      if (result.equals("[true]")) {
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////////
  // 按下到了:
  // 入:學生id/課程代碼
  // 回:五個數字
  @GetMapping("/iamhere/{cid}/{sid}")
  @Transactional
  public Integer[] iamhere(@PathVariable Integer cid, @PathVariable Integer sid) {
    Integer[] numlist = new Integer[5];
    try {
      String queryString = "SELECT ESCValue FROM Student WHERE SID = :sid";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("sid", sid);
      Integer result = (Integer) query.getSingleResult();
      numlist[0] = result;
      int i = 1;
      while (i < 5) {
        int temp = (int) (Math.random() * 100);

        // 检查是否重复
        boolean isDuplicate = false;
        for (int j = 0; j < i; j++) {
          if (temp == numlist[j]) {
            isDuplicate = true;
            break;
          } else {
            queryString = "SELECT COUNT(*) FROM Student WHERE SID IN (SELECT SID FROM Coursev2 WHERE CID=:cid )AND  ESCValue =:escv";
            query = entityManager.createNativeQuery(queryString);
            query.setParameter("cid", cid);
            query.setParameter("escv", temp);
            Integer count = (Integer) query.getSingleResult();
            if (count != 0) {
              isDuplicate = true;
              break;
            }
          }
        }
        if (!isDuplicate) {
          numlist[i] = temp;
          i++;
        }

      }
      for (int k = 0; k < 5; k++) {
        int temp = numlist[k];
        int temp2 = (int) (Math.random() * 10 % 5);
        numlist[k] = numlist[temp2];
        numlist[temp2] = temp;
      }
      return numlist;
    } catch (Exception e) {
      System.out.println(e.toString());
      Integer[] emptyArray = new Integer[0];
      return emptyArray;
    }
  }
  //////////////////////////////////////////////////////////////////
  // 按下數字
  // 入:學生id/課程代碼/日期/數字
  // 回: 成功|失敗

  @GetMapping("/checknum/{cid}/{sid}/{clknum}")
  @Transactional
  public boolean checknum(@PathVariable Integer cid, @PathVariable Integer sid, @PathVariable Integer clknum) {
    try {
      String queryString = "SELECT ESCValue FROM Student WHERE SID=:sid";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("sid", sid);
      Integer correct = (Integer) query.getSingleResult();
      query = null;
      if (correct == clknum) {
        queryString = "UPDATE Attendance SET Status = '1' WHERE CID= :cid AND SID= :sid AND Canrollcall=1";
        query = entityManager.createNativeQuery(queryString);
        query.setParameter("cid", cid);
        query.setParameter("sid", sid);
        query.executeUpdate();
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.toString());
      return false;
    }
  }
  ////////////////////////////////////////////////////////////
  // 點名狀態:
  // 入:課程代碼/日期
  // 回:點名表單

  @GetMapping("/attendtstatus/{cid}")
  @Transactional
  public List<attstatusrspn> checknum(@PathVariable Integer cid) {
    try {
      String queryString = "SELECT SName,Attendance.SID,Status FROM Attendance,Student WHERE Attendance.SID=Student.SID AND CID=:cid AND Time=(SELECT MAX(Time) FROM Attendance WHERE CID=1)";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("cid", cid);
      List<Object[]> resultList = query.getResultList();
      List<attstatusrspn> attendanceRecords = new ArrayList<>();

      for (Object[] row : resultList) {
        attstatusrspn attstatusrspn1 = new attstatusrspn();
        attstatusrspn1.setSName((String) row[0]);
        attstatusrspn1.setSId((Integer) row[1]);
        attstatusrspn1.setStatus((String) row[2]);
        attendanceRecords.add(attstatusrspn1);
      }

      return attendanceRecords;
    } catch (Exception e) {
      // 处理异常情况
      return Collections.emptyList();
    }
  }

  //////////////////////////////////////////////////
  // 更改點名:
  // 入:課程代碼/學號/狀態
  // 回:成功|失敗
  @GetMapping("/alteratt/{cid}/{sid}/{status}")
  @Transactional
  public boolean alteratt(@PathVariable Integer cid, @PathVariable Integer sid,
      @PathVariable String status) {
    try {
      String queryString = "UPDATE Attendance SET Status=:status where SID=:sid AND CID=:cid AND Time=(SELECT MAX(Time) FROM Attendance WHERE CID =:cid AND SID=:sid)";
      Query query = entityManager.createNativeQuery(queryString);
      query.setParameter("cid", cid);
      query.setParameter("sid", sid);
      query.setParameter("status", status);
      int rowsAffected = query.executeUpdate();
      if (rowsAffected > 0) {
        return true;
      } else {
        System.out.println("Update failed. No rows affected.");
        return false;
      }
      
    } catch (Exception e) {
      // 处理异常情况
      return false;
    }
  }
}

