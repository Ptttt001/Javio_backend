package com.example.demo.obj;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Professor")
public class Professor {
        @Id
        private int PID;
        private String PName;
        private String PDepartment;
        private String Ppwd;
    
        public int getPID() {
            return PID;
        }
    
        public void setPID(int PID) {
            this.PID = PID;
        }
    
        public String getPName() {
            return PName;
        }
    
        public void setPName(String PName) {
            this.PName = PName;
        }
    
        public String getPDepartment() {
            return PDepartment;
        }
    
        public void setPDepartment(String PDepartment) {
            this.PDepartment = PDepartment;
        }
    
        public String getPpwd() {
            return Ppwd;
        }
    
        public void setPpwd(String Ppwd) {
            this.Ppwd = Ppwd;
        }
    }
    
