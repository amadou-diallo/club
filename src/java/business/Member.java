/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author amadou
 */
public class Member {
    private String memid, lastname, firstname, middlename;
    private String status, memdt;
    private long password, passattempt;
    
    public Member() {
        this.memid = "";
        this.lastname = "";
        this.firstname = "";
        this.middlename = "";
        this.status = "";
        this.memdt = "";
        this.password = -1;
        this.passattempt = -1;
        
                
    }

    public String getMemid() {
        return memid;
    }

    public void setMemid(String memid) {
        this.memid = memid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemdt() {
        return memdt;
    }

    public void setMemdt(String memdt) {
        this.memdt = memdt;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public long getPassattempt() {
        return passattempt;
    }

    public void setPassattempt(long passattempt) {
        this.passattempt = passattempt;
    }
    
    public boolean isAuthenticated() {
        if (this.password > 0) {
            if (this.password == this.passattempt) {
                return true;
            }
            
        }
        return false;
    }
    
}
