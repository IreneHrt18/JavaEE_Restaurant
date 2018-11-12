package Servelet;



import Model.User;

import java.sql.*;

public class OracleHelper {

    /**
     * 静态代码块在类加载时调用，并且只调用一次
     * 1.当调用一个类的静态变量时，这个类中的静态代码块会执行。【只有静态代码块会执行】
     * 2.当调用一个 类的静态方法时，这个类中的静态代码块会执行。【只有静态代码块会执行】
     * 3.当创建一个 类的一个实例时，这个类中的静态代码块、非静态代码块（也叫构造代码块）、创建实例的相应的构造方法都会执行。
     */
    private static Connection conn = null;
    private static String driver;
    private static String url;
    private static String sys_username;
    private static String tem_username;
    private static String password;

    static {
        try {

//            InputStream is = OracleHelper.class.getClassLoader().
//                    getResourceAsStream("myProperties.property");
//            Properties prop = new Properties();
//
//            prop.load(is);
//            driver = prop.getProperty("Driver");
//            url = prop.getProperty("url");
//            sys_username = prop.getProperty("sys_username");
//            tem_username=prop.getProperty("tem_username");
//            password = prop.getProperty("password");
            driver = "oracle.jdbc.driver.OracleDriver";
            url ="jdbc:oracle:thin:@localhost:1521:MYORCL";
            sys_username = "system";
            tem_username="tem_user";
            password = "ting0818";
            Class.forName(driver);//加载驱动
            conn = DriverManager.getConnection(url, tem_username, password);//创建连接

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public static  Connection getConnection(){
//        //连接可能因为时间原因被关闭因此需要判断是否关闭，并且重连
//        //ODBC驱动还需要放在web 的lib下
//        try {
//            if(!conn.isClosed())
//            return conn;
//            else
//            {
//                reconnect();
//                return conn;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Connection getConnection(){

        try {
            Class.forName(driver);//加载驱动
            conn = DriverManager.getConnection(url, tem_username, password);//创建连接
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void free(ResultSet rs, PreparedStatement ps,Connection connection)
    {
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(ps!=null) {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }


    public static User chekIsUser(String _username,String password){

        PreparedStatement selectQuery = null;
        ResultSet resultSet=null;
        try {
            selectQuery=getConnection().prepareStatement("select PASSWORD,USERID from users_table where username =?");
            selectQuery.setString(1,_username);
            resultSet=selectQuery.executeQuery();
            while (resultSet.next())
            {
                if(resultSet.getString("password").equals(password)) {
                    User tem_user=new User(resultSet.getInt("userid"),_username,null,null);

                    return tem_user;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            free(resultSet,selectQuery,conn);

        }
        return null;

    }
    public boolean identUser(String username, String password){
        ResultSet resultSet=null;
        try {
            PreparedStatement statment=conn.prepareStatement("select password from rstusers where username =?");
            statment.setString(1,username);
            resultSet=statment.executeQuery();
            while(resultSet.next())
            {
                if(resultSet.getString("password").equals(password))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}