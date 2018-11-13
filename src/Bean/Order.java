package Bean;
import java.math.BigDecimal;
import java.util.Date;
public class Order {
	
	private String ORDERNO;
	private String USERNO;
	private BigDecimal PRICE;
	private Date		TIME;
	private BigDecimal	ORDERSTATE;
	
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
	public Date getTIME() {
		return TIME;
	}
	public void setTIME(Date tIME) {
		TIME = tIME;
	}
	public BigDecimal getORDERSTATE() {
		return ORDERSTATE;
	}
	public void setORDERSTATE(BigDecimal oRDERSTATE) {
		ORDERSTATE = oRDERSTATE;
	}


	
}
