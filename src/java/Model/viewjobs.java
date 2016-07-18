/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author jainR9527
 */
public class viewjobs {

  
    String jobid ;
    String jobtitle ;
    String jobdesc ;
    String jobcompany;
    String postedby;
    String sharestatus;
    String type;
    
    
    public viewjobs( String pjobid , String pjobtitle , String pjobdesc,
            String pjobcompany,String ppostedby, String psharestatus,String ptype)
    {
    
     jobid= pjobid;
     jobtitle =pjobtitle;
     jobdesc =pjobdesc ;
     jobcompany= pjobcompany;
     postedby=ppostedby;
     sharestatus=psharestatus;
     type=ptype;
       
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public String getJobcompany() {
        return jobcompany;
    }

    public void setJobcompany(String jobcompany) {
        this.jobcompany = jobcompany;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getSharestatus() {
        return sharestatus;
    }

    public void setSharestatus(String sharestatus) {
        this.sharestatus = sharestatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

   
}