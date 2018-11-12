package DAO;



import Servelet.OracleHelper;
import oracle.jdbc.internal.OracleClob;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

public class BaseDAO {

        public ArrayList findObjs(String sql, Class clazz) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList objlist = null;
            try {
                conn = OracleHelper.getConnection();
                // 创建statement对象
                ps = conn.prepareStatement(sql);
                // 处理结果集
                rs = ps.executeQuery();
                objlist = new ArrayList();
                while (rs.next()) {
                    Object obj = MappingObj(rs, clazz);
                    objlist.add(obj);
                }
                return objlist;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                OracleHelper.free(rs, ps, conn);
            }
            return null;
        }

    /**
     * 实现向数据库表中添加一条数据
     * @param sql
     * @param clas
     * @param colName
     */



        /**
         * @param rs
         * @return
         * @throws SQLException
         */
        private Object MappingObj(ResultSet rs, Class clazz) throws SQLException {

            Object obj;
            try {
                obj = clazz.newInstance();
                // 获取映射对象的方法集合
                Method[] methods = clazz.getMethods();
                // 获取结果集中元数据信息
                ResultSetMetaData meta = rs.getMetaData();
                // 按字段数目循环结果集中记录，进行对象映射
                for (int i = 1; i <= meta.getColumnCount(); i++) {
//                    // 构造当前列的set方法名称
//                    String colname = meta.getColumnLabel(i);
                   String colname=meta.getColumnLabel(i).toUpperCase().substring(0,1)+ meta.getColumnName(i).toLowerCase().substring(1);
                    String methodname = "set" + colname;
                    // 循环查找同名方法，并通过反射调用该方法，设置属性值
                    for (Method method : methods) {
                        if (method.getName().equals(methodname)) {
                            Object obj0=rs.getObject(i);
                            if(method.getParameterTypes()[0]==int.class) {
                                obj0 = Integer.parseInt(obj0.toString());
                            }
                                method.invoke(obj, obj0);
                                break;

                        }
                    }
                }
                return obj;
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
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

        public int modifyObj(String sql, Object[] params) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = OracleHelper.getConnection();
                // 创建statement对象
                ps = conn.prepareStatement(sql);
                ParameterMetaData pm = ps.getParameterMetaData();
                for (int i = 1; i <= pm.getParameterCount(); i++) {
                    ps.setObject(i, params[i-1]);
                }
                return ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                OracleHelper.free(rs, ps, conn);
            }
            return 0;
        }

        public ArrayList seleObj(String sql, Class clazz,Object[] params){

                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                ArrayList objlist = null;
                try {
                    conn = OracleHelper.getConnection();
                    // 创建statement对象
                    ps = conn.prepareStatement(sql);
                    ParameterMetaData pm = ps.getParameterMetaData();
                    for (int i = 1; i <= pm.getParameterCount(); i++) {
                        ps.setObject(i, params[i-1]);
                    }
                    // 处理结果集
                    rs = ps.executeQuery();
                    objlist = new ArrayList();
                    while (rs.next()) {
                        Object obj = MappingObj(rs, clazz);
                        objlist.add(obj);
                    }
                    return objlist;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    OracleHelper.free(rs, ps, conn);
                }
                return null;


        }
    }
