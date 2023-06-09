package com.example.demo.obj;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Student")
public class student {
    @Id
    private int SID;
    private String SName;
    private String SDepartment;
    private String Spwd;
    private int CID;
    private int ESCValue;

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getSDepartment() {
        return SDepartment;
    }

    public void setSDepartment(String SDepartment) {
        this.SDepartment = SDepartment;
    }

    public String getSpwd() {
        return Spwd;
    }

    public void setSpwd(String Spwd) {
        this.Spwd = Spwd;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public int getESCValue() {
        return ESCValue;
    }

    public void setESCValue(int ESCValue) {
        this.ESCValue = ESCValue;
    }
}
