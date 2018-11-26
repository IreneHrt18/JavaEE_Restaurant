package Bean;
import oracle.sql.TIMESTAMP;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order {

	public Order() {
		this.dishes=new ArrayList<Dish_Order>();
	}

	public String getORDERNO() {
		return ORDERNO;
	}
	public void setORDERNO(String oRDERNO) {
		ORDERNO = oRDERNO;
	}
	public String getUSERNO() {
		return USERNO;
	}
	public void setUSERNO(String uSERNO) {
		USERNO = uSERNO;
	}
	public BigDecimal getPRICE() {
		return PRICE;
	}
	public void setPRICE(BigDecimal pRICE) {
		PRICE = pRICE;
	}
	public Timestamp getTIME() {
//		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString=formatter.format(TIME);
		return new Timestamp(TIME.getTime());
	}
	public void setTIME(Date tIME) {
		TIME = tIME;
	}
	public String getORDERSTATE() {
		return ORDERSTATE;
	}
	public void setORDERSTATE(String oRDERSTATE) {
		ORDERSTATE = oRDERSTATE;
	}
	public String getCOMMENTSTATE() {
		return COMMENTSTATE;
	}
	public void setCOMMENTSTATE(String cOMMENTSTATE) {
		COMMENTSTATE = cOMMENTSTATE;
	}
	
	private String ORDERNO;
	private String USERNO;
	private BigDecimal PRICE;
	private Date TIME;
	private String	ORDERSTATE;
	private String COMMENTSTATE;

	public ArrayList<Dish_Order> getDishes() {
		return dishes;
	}

	public void setDishes(ArrayList<Dish_Order> dishes) {
		this.dishes = dishes;
	}

	private ArrayList<Dish_Order> dishes;
	
}
