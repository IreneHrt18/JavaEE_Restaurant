package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseDAOIMPL extends BaseDAO {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList searchOBJ(String sql, Class clazz) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		ResultSet resultSet = null;
		PreparedStatement pStatement = null;
		ArrayList<Object> list = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Object object = this.mapping(resultSet, clazz);
				if (object != null)
					list.add(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(resultSet, pStatement, connection);
		return list;
	}

	public Object mapping(ResultSet rSet, Class<Object> clazz) {
		// TODO Auto-generated method stub
		try {
			Object object = clazz.newInstance();
			Method[] methods = clazz.getMethods();
			ResultSetMetaData metaData = rSet.getMetaData();
			for (int i = 0; i < metaData.getColumnCount(); i++) {
				StringBuffer value = new StringBuffer("set");
				value.append(metaData.getColumnLabel(i + 1));
				for (Method method : methods) {
					if (method.getName().equals(value.toString())) {
						method.invoke(object, rSet.getObject(i + 1));
					}
				}
			}
			return object;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int singleSQL(String sql, Object[] params) {
		Connection connection = Helper.getConnection();
		PreparedStatement pStatement;
		int count = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i + 1, params[i]);
				}
			}
			count = pStatement.executeUpdate();
			Helper.free(null, pStatement, connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList searchOBJ(String sql, Object[] params, Class clazz) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		ResultSet resultSet = null;
		PreparedStatement pStatement = null;
		ArrayList<Object> list = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i + 1, params[i]);
				}
			}
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Object object = this.mapping(resultSet, clazz);
				if (object != null)
					list.add(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(resultSet, pStatement, connection);
		return list;
	}

	@Override
	public int[] multipleSQL(String sql, Object[] params) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		PreparedStatement pStatement = null;
		int[] value = null;
		try {
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i + 1, params[i]);
					pStatement.addBatch();
				}
			}
			value = pStatement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(null, pStatement, connection);
		return value;
	}

	@Override
	public Object getCount(String sql, Object[] params) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Object value = null;
		try {
			pStatement = connection.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pStatement.setObject(i + 1, params[i]);
				}
			}
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				value = rSet.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(rSet, pStatement, connection);
		return value;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList fuzzyQuery(String sql, String param, Class clazz) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		ResultSet resultSet = null;
		PreparedStatement pStatement = null;
		ArrayList<Object> list = new ArrayList<>();
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setObject(1, "%" + param + "%");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				Object object = this.mapping(resultSet, clazz);
				if (object != null)
					list.add(object);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(resultSet, pStatement, connection);
		return list;
	}

	@Override
	public Object[] getMuiltCountByParams(String sql, Object[] value) {
		// TODO Auto-generated method stub
		Connection connection = Helper.getConnection();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		Object[] rets = new Object[value.length];
		try {
			pStatement = connection.prepareStatement(sql);
			for (int i = 0; i < value.length; i++) {
				pStatement.setObject(1, value[i]);
				rSet = pStatement.executeQuery();
				while (rSet.next()) {
					rets[i] = rSet.getObject(1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Helper.free(rSet, pStatement, connection);
		return rets;
	}

}
