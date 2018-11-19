package Bean;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.naming.java.javaURLContextFactory;
public class Order {
	
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
	public String getTIME() {
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=formatter.format(TIME);
		return dateString;
		
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
	
	
}
