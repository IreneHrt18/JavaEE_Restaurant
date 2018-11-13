package Bean;
import java.math.BigDecimal;
import java.util.Date;
public class Order {
	
	private BigDecimal ORDERNO;
	private BigDecimal USERNO;
	private BigDecimal PRICE;
	private Date		TIME;
	private BigDecimal	ORDERSTATE;
	
	public BigDecimal getORDERNO() {
		return ORDERNO;
	}
	public void setORDERNO(BigDecimal oRDERNO) {
		ORDERNO = oRDERNO;
	}
	public BigDecimal getUSERNO() {
		return USERNO;
	}
	public void setUSERNO(BigDecimal uSERNO) {
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
