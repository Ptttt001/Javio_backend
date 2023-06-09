package com.example.demo.obj;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;;
@Entity
@Table(name="Attendance")
public class attendance {


    @Id
    private int sid;
    //@Id
    private int cid;

    private LocalDateTime time;

    private boolean canrollcall;

    private boolean status;

    
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean iscanrollcall() {
        return canrollcall;
    }

    public void setCanRollCall(boolean canrollcall) {
        this.canrollcall = canrollcall;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}