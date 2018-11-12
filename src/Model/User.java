package Model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int userid;
    private String username;
    private String password;
    private Date signDate;

    public User(int userid, String username, String password, Date signDate) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.signDate = signDate;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignDate() {
        return new Timestamp(signDate.getTime());
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
