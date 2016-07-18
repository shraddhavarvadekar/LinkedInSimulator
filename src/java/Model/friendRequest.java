/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author viky
 */
public class friendRequest {
    String sid;
    String rid;
    String sname;
    String status ;
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    public friendRequest( String sid , String rid , String sname  ){
        this.sid = sid;
        this.rid = rid ;
        this.sname = sname;
   }
    
    public friendRequest( String sid , String rid , String sname , String message ){
        this.sid = sid;
        this.rid = rid ;
        this.sname = sname;
        this.message = message ;
   }
}
