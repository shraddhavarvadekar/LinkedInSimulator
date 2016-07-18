/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;
import org.json.JSONObject;

import Model.*;
import javax.faces.bean.ManagedBean;

// tai required libraries
import tai.caFeed;
import org.json.JSONObject;

/**
 *
 * @author jainR9527
 */
@ManagedBean(name = "account")
@javax.faces.bean.SessionScoped

public class Account implements Serializable {

    ArrayList<String> seec = new ArrayList<String>();
    ArrayList<String> test1 = new ArrayList<String>();

    /**
     * Creates a new instance of Account
     */
    private String accountid;
    private String name;
    private String password;
    private String company;
    private String type;
    private String email;
    private String v_connec;
    private user profile;
    private String newpassword;

    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    private String url = ec.getRequestContextPath();

    //--------Add Job Rohit Getters Setters-------
    private String jobTitle;
    private String jobDesc;
    private String jobCompany;
    private String shareStatus;

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(String shareStatus) {
        this.shareStatus = shareStatus;
    }
    //--------Add Job Rohit Getters Setters------

    public String getUrl() {
        return url;
    }

    public user getProfile() {
        return profile;
    }

    public void setProfile(user profile) {
        this.profile = profile;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String test1() {
        return "";
    }
    //---------Rohit Chat Getters Setters-------
    private String user1;
    private String user2;
    private String message;

//    public LinkedInChat() {
//
//    }
//    public LinkedInChat(String user1, String user2, String message) {
//        this.user1 = user1;
//        this.user2 = user2;
//        this.message = message;
//    }
    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //-----------Rohit Chat Getters Setters End------

    public String register1() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return ("Internal Error! Please try again later.");

        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from account "
                    + "where id='" + accountid + "'");

            if (resultSet.next()) {
                return ("Either you have an id already "
                        + "or your online ID is not available to register");
            } else {

                int r = statement.executeUpdate("insert into account values " + "('" + accountid + "', '" + name + "', '" + password + "', '" + company + "', '" + type + "','" + email + "')");

                return ("Registration Successful! Please "
                        + "return to login your account.");
            }
        } catch (SQLException e) {
            //error message
            e.printStackTrace();
            return ("Internal Error! Please try again later 123.");

        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }

    public String login() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from account "
                    + "where id = '" + accountid + "'");

            if (resultSet.next()) {

                if (password.equals(resultSet.getString(3))) {
                    //password is good 
                    //display welcome.xhtml
                    //create an OnlineAccount object
                    // theLoginAccount = new OnlineAccount(accountid,name, password,company,type);
                    //go to welcome.xhtml
                    name = resultSet.getString(2);

                    accountid = resultSet.getString("id");
                    type = resultSet.getString("type");
                    password = resultSet.getString("password");
                    company = resultSet.getString("company");
                    email = resultSet.getString("email");

                    // lastname=resultSet.getString(3);
                    // return "welcome";
                    return "home";

                } else {
                    accountid = "";
                    password = "";
                    //display loginNotOK.xhtml
                    return "loginNotOK";
                }
            } else {
                accountid = "";
                password = "";
                //id is not found, display loginNotOK.xhtml
                return "loginNotOK";

            }

        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String signOut() {
        FacesContext.getCurrentInstance().
                getExternalContext().invalidateSession();
        return "index.xhtml";

    }

    /*public ArrayList< JSONObject > get1stRecommendation(){
     accountid = accountid == null ? "user1!" : "" ;
     ArrayList< JSONObject > recs = caFeed.call( "team-get-user" , 
     new String[]{ accountid } );
     return recs ;
     }*/
    public ArrayList< JSONObject> get1stRecommendation() {
        company = company == null ? "uhcl" : company;
        accountid = accountid == null ? "user1!" : accountid;
        return caFeed.call("team-get-1st-recommendation", new String[]{company, accountid});
    }

    public ArrayList< JSONObject> get2ndRecommendation() {
        company = company == null ? "uhcl" : company;
        accountid = accountid == null ? "user1!" : accountid;
        return caFeed.call("team-get-2nd-recommendation", new String[]{company, accountid});
    }

    public String request(String name) {
        // http://stackoverflow.com/questions/2559269/how-to-read-querystring-value-via-managed-bean-in-jsf1-1
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();

        return requestMap.get(name);
    }

   //ROHAN VIEWCONNECTION
    public ArrayList<user> viewconnection() {

        ArrayList<user> seec = new ArrayList< user>();

        String test = "";
        int c = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return seec;
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from connection "
                    + "where id1='" + accountid + "'");

            //
            while (resultSet.next()) {
                c++;
                test1.add(resultSet.getString(2));

            }
            resultSet = statement.executeQuery("Select * from connection "
                    + "where id2='" + accountid + "'");

            //
            while (resultSet.next()) {
                c++;
                test1.add(resultSet.getString(1));

            }

            System.out.println(c);
            seec = new ArrayList<user>();

            for (int i = 0; i < c; i++) {
                resultSet = statement.executeQuery("Select * from account "
                        + "where id ='" + test1.get(i) + "'");
                while (resultSet.next()) {

                    user item = new user(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("company"),
                            resultSet.getString("type"),
                            resultSet.getString("email")
                    );
                    seec.add(item);

                }
            }

        } catch (SQLException e) {

        } finally {
            try {
                connection.close();
                statement.close();
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {

            }

        }
        return seec;

    }

    public String forgotpwd() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from account "
                    + "where id = '" + accountid + "'");

            if (resultSet.next()) {

                //   password = resultSet.getString(3);
                return "resetPassword";

            } else {

                //password = "User ID does not exist";
                return "User ID does not exist";
            }
        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String viewprofile(String profile_id, String url) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return "Internal Error";
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            String query = "Select * from account "
                    + "where id = '" + profile_id + "'";
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {

                profile = new user(
                        resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("company"), resultSet.getString("type"), resultSet.getString("email")
                );

                return url;

            }

        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "show_profile_details";
    }

    public String my_profile() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from account "
                    + "where id = '" + accountid + "'");

            if (resultSet.next()) {

                accountid = resultSet.getString(1);
                name = resultSet.getString(2);
                company = resultSet.getString(4);
                type = resultSet.getString(5);
                email = resultSet.getString(6);

            }

        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return "myprofile";

    }

    //----Rohit Chat Methord 
    public String chatMessage(String user1, String user2, String message) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return "internalError";
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            String sql = "insert into chat values ('" + user1 + "','" + user2 + "','" + message + "')";

            statement.executeUpdate(sql);
            return "messageSent";
        } catch (Exception e) {
            e.printStackTrace();
            return "internalError";
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Rohit Chat End
    }
        //------Rohit Check Messages

    /**
     *
     * @param user1
     * @param user2
     * @return
     */
    public ArrayList<Account> checkMessage(String user2) {

        ArrayList<Account> fetch = new ArrayList<Account>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            // return "internalError";
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from chat where  receiverID = '" + user2 + "'");

            while (resultSet.next()) {
                Account acc = new Account();
                acc.setMessage(resultSet.getString(3));
                fetch.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
            //return "internalError";
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //------Rohit Check Message End
        }
        return fetch;
    }

    //------Add Job Methord -----
    public String addJob(String username) {
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        try {
            //connect to the databse
            connection = DriverManager.getConnection(DATABASE_URL, "jainr9527", "1322607");

            //crate the statement
            statement = connection.createStatement();

            int r = statement.executeUpdate("insert into addjob values ('0','" + jobTitle + "', '" + jobDesc + "','" + jobCompany + "','" + username + "','" + shareStatus + "','recruiter')");

            // Registration Success go to Job Added page         
            return "jobAdded";
        } catch (SQLException e2) {
            e2.printStackTrace();
            //return"Internal Error";
            return "internalError";
        } finally {
            //close the database
            try {
                //resultSet.close();
                //statement.close();
                //connection.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    // -------- Add Job methord End--------
    // ROHAN VIEWSHAREDJOBS
    public List<viewjobs> viewsharedjobs() {
        ArrayList<viewjobs> seec1 = new ArrayList<viewjobs>();
        int p = 0;
        //String j = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return seec1;
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> t1 = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "jainr9527", "1322607");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("Select * from request "
                    + "where rid = '" + accountid + "' and status= 'Confirmed'");
            t1 = new ArrayList<String>();
            while (resultSet.next()) {

                t1.add(resultSet.getString(1));
            }
            resultSet = statement.executeQuery("select * from request where sid='" + accountid + "' and status= 'Confirmed'");

            while (resultSet.next()) {
                t1.add(resultSet.getString(2));
            }
            // j = " ";
            seec1 = new ArrayList<viewjobs>();
            for (int i = 0; i < t1.size(); i++) {

                resultSet = statement.executeQuery("select * from addjob where postedBy ='" + t1.get(i) + "' and shareStatus='Y'");
                while (resultSet.next()) {
                    viewjobs jobs = new viewjobs(
                            //                    j = j + (i + 1) + ": Job Title: "
                            //                            + resultSet.getString(2) + " Job Description: "
                            //                            + resultSet.getString(3) + " Job Company: "
                            //                            + resultSet.getString(4) + " Posted By: " + resultSet.getString(5) + "\n");

                            resultSet.getString("jobID"),
                            resultSet.getString("jobTitle"),
                            resultSet.getString("jobDesc"),
                            resultSet.getString("jobCompany"),
                            resultSet.getString("postedBy"),
                            resultSet.getString("shareStatus"),
                            resultSet.getString("type")
                    );
                    seec1.add(jobs);

                }
            }

            //
//            String[] rj = j.split("\n");
//            for (int i = 0; i < rj.length; i++) 
//            {
//                job.add(rj[i]);
//            }
            return seec1;

        } catch (SQLException e) {

        } finally {
            try {

                resultSet.close();
                connection.close();
                statement.close();
            } catch (Exception e) {

            }

        }
        return seec1;
    }
        // ROHAN VIEWSHAREDJOBS
    // Harish --------------------------------------------

        //Harish 
    String notSenderId;
    String notReceiverId;
    String notDisplaymessage;
    String frdreqReceiverid;

    public String getFrdreqReceiverid() {
        return frdreqReceiverid;
    }

    public void setFrdreqReceiverid(String frdreqReceiverid) {
        this.frdreqReceiverid = frdreqReceiverid;
    }

    public String getNotSenderId() {
        return notSenderId;
    }

    public void setNotSenderId(String notSenderId) {
        this.notSenderId = notSenderId;
    }

    public String getNotReceiverId() {
        return notReceiverId;
    }

    public void setNotReceiverId(String notReceiverId) {
        this.notReceiverId = notReceiverId;
    }

    public String getNotDisplaymessage() {
        return notDisplaymessage;
    }

    public void setNotDisplaymessage(String notDisplaymessage) {
        this.notDisplaymessage = notDisplaymessage;
    }

    public Account() {

    }

    public Account(String psid, String prid, String pmessage) {
        this.notSenderId = psid;
        this.notReceiverId = prid;
        this.notDisplaymessage = pmessage;
    }

    public void init() {
        Account user = new Account();

    }

    public ArrayList<friendRequest> getNotification() {
        ArrayList<friendRequest> notificationList = new ArrayList<friendRequest>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            //return result;
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "jainr9527", "1322607");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select n.* , a.* from notification n join account a on n.sid = a.id where rid = '" + accountid + "'");

            //  while( !resultSet.next() = null )
            while (resultSet.next()) {

                notificationList.add(new friendRequest(
                        resultSet.getString("sid"), resultSet.getString("rid"), resultSet.getString("name"), resultSet.getString("message")
                ));

            }
//            this.error_message = "Correct";
            return notificationList;

        } catch (SQLException e) {
            e.printStackTrace();
//            this.error_message = e.toString();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
//                this.error_message = e.toString();
            }
        }
        return notificationList;
    }

    public ArrayList<friendRequest> getFriendRequest() {

        ArrayList<friendRequest> notificationList = new ArrayList<friendRequest>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            //return result;
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            connection = DriverManager.getConnection(DATABASE_URL, "jainr9527", "1322607");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select r.* , c.* from request  r join account c on r.rid = c.id where rid = '" + accountid + "'");

            //  while( !resultSet.next() = null )
            while (resultSet.next()) {

                notificationList.add(new friendRequest(
                        resultSet.getString("sid"), resultSet.getString("rid"), resultSet.getString("name")
                ));
            }
//            this.error_message = "Correct";
            return notificationList;

        } catch (SQLException e) {
            e.printStackTrace();
//            this.error_message = e.toString();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
//                this.error_message = e.toString();
            }
        }
        return notificationList;
    }

    public String SendRequest(String frdreqReceiverid) {

        String returnmsg = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return ("Internal Error! Please try again later.");

        }

        Connection connection = null;

        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        //    getNotification();
        // sid, rid, 

        int notify;
        int r;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");

            statement = connection.createStatement();

            r = statement.executeUpdate("insert into request  values ('" + accountid + "', '"
                    + frdreqReceiverid + "','pending')");

            notify = statement.executeUpdate("insert into notification values ('" + accountid + "','" + frdreqReceiverid + "', 'Sent you a friend request' , '0' )");
            if (r == 1 && notify == 1) {
                //returnmsg = frdreqReceiverid+" added successfully";
                returnmsg = "sendrequest";
            } else {
                returnmsg = " cannot add " + frdreqReceiverid;
            }

        } catch (SQLException e) {
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnmsg;
    }

    public String AcceptRequest(String frdreqReceiverid, String finder) {

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            //error message
            return ("Internal Error! Please try again later.");

        }

        Connection connection = null;

        Statement statement = null;
        // Statement statement1 = null;
        // Statement statement2 =null;

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";

        try {
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");

            statement = connection.createStatement();

            int r = statement.executeUpdate("DELETE FROM request where rid = '" + accountid + "' and sid = '" + frdreqReceiverid + "'");

            //FacesContext fc = FacesContext.getCurrentInstance();
            //Map<String, String> params
            //        = fc.getExternalContext().getRequestParameterMap();
            //finder = params.get("status");
            statement.executeUpdate("delete from notification where sid = '" + frdreqReceiverid + "' and rid = '" + accountid + "' ");

            if (finder.equals("Accepted")) {
                int i = statement.executeUpdate("insert into notification values ('" + accountid + "','" + frdreqReceiverid + "', 'Friend Request Accepted' , '0'  )");
                int Connect = statement.executeUpdate("insert into connection values ( '" + frdreqReceiverid + "', '" + accountid + "')");
            } else {
                int j = statement.executeUpdate("insert into notification values ('" + accountid + "','" + frdreqReceiverid + "','Friend Request Denied' ,  '0' )");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String resetpassword(String URL) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();
            //return to internalError.xhtml
            return ("internalError");
        }

        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/jainr9527";
        Connection connection = null;  //a connection to the database
        Statement statement = null;    //execution of a statement
        ResultSet resultSet = null;   //set of results

        try {
            //connect to the database with user name and password
            connection = DriverManager.getConnection(DATABASE_URL,
                    "jainr9527", "1322607");
            statement = connection.createStatement();

            int r = statement.executeUpdate("update account set password='" + password + "' where id='" + this.accountid + "'");
            return URL;

        } catch (SQLException e) {
            //go to internalError.xhtml
            e.printStackTrace();
            return ("internalError");
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
