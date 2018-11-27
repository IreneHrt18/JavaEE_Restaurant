package DAOIMPL;

import Bean.Cart;
import DAO.InsertDAO;
import DAO.ModifyDAO;
import DAO.SearchDAO;
import JDBC.BaseDAOIMPL;

import java.util.ArrayList;

public class CartIMPL extends BaseDAOIMPL implements SearchDAO, InsertDAO, ModifyDAO {
    @Override
    public ArrayList searchByPrimaryKey(String[] params) {
        //String sql = "select dishno,dishcount from user_cart  where userno=? ";

        String sql="select dishname ,price,img,dishno,dishcount,userno  from all_carts where" +
                " userno=? ";
        //获取用户id
        Object userno[]={params[0]};
        ArrayList<Cart> list = searchOBJ(sql,userno, Cart.class);

        return list;
    }

    @Override
    public ArrayList searchAll() {
        return  null;
    }

    public void trucOrderList() {
        String sql="TRUNCATE TABLE USER_CART " +
                " PRESERVE MATERIALIZED VIEW " +
                "LOG REUSE STORAGE";

        modifyObj(sql,null);
    }

    @Override
    public ArrayList searchByPage(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public int getCountByParam(String colunm, String value) {
        return 0;
    }

    @Override
    public ArrayList fuzzyQuery(String colunm, String value) {
        return null;
    }

    @Override
    public int[] getMuiltCountByParams(String colunm, String[] value) {
        return new int[0];
    }

    @Override
    public boolean addObj(Object obj) {
        String sql="insert into user_cart(dishno,userno,dishcount)" +
                "values(?,?,?) ";
        Cart cart=(Cart) obj;

        Object para[]={cart.getDISHNO(),
                cart.getUserno(),
                1
        };
        modifyObj(sql,para);
        return true;
    }

    @Override
    public boolean alterObj(Object obj) {
        String sql="update user_cart set dishcount =? where dishno=? and userno=?";
        Cart cart=(Cart) obj;

        Object para[]={cart.getDishCount(),cart.getDISHNO(),
                cart.getUserno(),
        };
        modifyObj(sql,para);
        return true;
    }

    @Override
    public boolean deleteObj(Object obj) {
        String sql="delete from user_cart where dishno=? and userno=?";
        Cart cart=(Cart) obj;

        Object para[]={cart.getDISHNO(),
                cart.getUserno(),
        };
        modifyObj(sql,para);
        return true;
    }

	@Override
	public int modifyImgUrl(String[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyAllByPrimarykey(String primary, String[] params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList searchByParams(String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList searchByPage(int currentPage, int pageSize, String viewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(String viewName) {
		// TODO Auto-generated method stub
		return 0;
	}
}
