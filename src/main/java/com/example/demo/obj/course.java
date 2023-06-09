package com.example.demo.obj;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class course {
    @Id
    private int CID;
    private String CTitle;
    private int Credit;
    private int PID;
    private int ASCValue;
    private int stu_num;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getCTitle() {
        return CTitle;
    }

    public void setCTitle(String CTitle) {
        this.CTitle = CTitle;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getASCValue() {
        return ASCValue;
    }

    public void setASCValue(int ASCValue) {
        this.ASCValue = ASCValue;
    }

    public int getStuNum() {
        return stu_num;
    }

    public void setStuNum(int stuNum) {
        this.stu_num = stuNum;
    }
}
