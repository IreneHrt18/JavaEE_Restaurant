package Bean;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//	public String getTIME() {
//		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
//		String dateString=formatter.format(TIME);
//		return dateString;
//		
//	}
	public Timestamp getTIME() {
	return (Timestamp)TIME;	
}
	public void setTIME(Date tIME) {
		TIME = tIME;
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=formatter.format(TIME);
		this.TIMESTRING=dateString;
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

	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	private String ORDERNO;
	private String USERNO;
	private BigDecimal PRICE;
	private Date TIME;
	private String	ORDERSTATE;
	private String COMMENTSTATE;
	private String USERNAME;
	private String TIMESTRING;
	public String getTIMESTRING() {
		return TIMESTRING;
	}

	
}
