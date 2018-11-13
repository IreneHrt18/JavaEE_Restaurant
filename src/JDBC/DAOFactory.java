package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DAOFactory {
	public static Object newInstance(String interfaceClassName) {
		InputStream is = DAOFactory.class.getResourceAsStream("DAO.property");
		Properties properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String name = properties.getProperty(interfaceClassName);
		try {
			return Class.forName(name).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
