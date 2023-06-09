package com.example.demo.responobj;

//登入:
//入:帳號/密碼
//回:成功|失敗/課程代碼
public class loginrspn {
    private boolean status;
    private Integer coursenum;
    private Integer[] courselist;
    private String[] courseName;
    public loginrspn(boolean status,Integer coursenum, Integer[] courselist,String[] courseName)
    {
        this.status=status;
        this.coursenum=coursenum;
        this.courselist=courselist;
        this.courseName = courseName;
    }
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(Integer coursenum) {
        this.coursenum = coursenum;
    }

    public  Integer[] getCourselist() {
        return courselist;
    }

    public void setCourselist(Integer[] courselist) {
        this.courselist = courselist;
    }
    public String[] getcourseName() {
        return courseName;
    }

    public void setcourseName(String[] courseName) {
        this.courseName = courseName;
    }
}
