package DAO;

import Model.User;
import Servelet.OracleHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersDAO {
    public static boolean addUser(User user){
        PreparedStatement insertQuery =null;
        try {
//            createQuery = conn.prepareStatement("create user ? identified by  ?");
            insertQuery=OracleHelper.getConnection().prepareStatement("insert into users_table values (?,?,?,?)");
            insertQuery.setInt(1,user.getUserid());
            insertQuery.setString(2,user.getUsername());
            insertQuery.setString(3,user.getPassword());
            insertQuery.setObject(4, user.getSignDate());
            insertQuery.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            OracleHelper.free(null,insertQuery,null);
        }
    }
}
