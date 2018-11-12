package Servelet;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OracleHelperTest {

    @Test
    public void initConnection() {
                    InputStream is = OracleHelper.class.getClassLoader().
                    getResourceAsStream("dbconfig.properties");
            Properties prop = new Properties();

        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = prop.getProperty("Driver");
            String url = prop.getProperty("url");
            String sys_username = prop.getProperty("sys_username");
            String tem_username=prop.getProperty("tem_username");
            String password = prop.getProperty("password");
            System.out.println(url);
            System.out.println(driver);
    }

    @Test
    public void reconnect() {
    }

    @Test
    public void signupUser() {
    }

    @Test
    public void chekIsSysUser() {
    }

    @Test
    public void identUser() {
    }
}