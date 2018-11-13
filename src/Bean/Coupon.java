package Bean;
import java.math.BigDecimal;
public class Coupon {
	
	private BigDecimal COUPONNO;
	private String QUOTA;
	private BigDecimal LIMITPRICE;
	private BigDecimal PERIOD;
	private BigDecimal  COUPONSTATE;

	public BigDecimal getCOUPONSTATE() {
		return COUPONSTATE;
	}
	public void setCOUPONSTATE(BigDecimal cOUPONSTATE) {
		COUPONSTATE = cOUPONSTATE;
	}
	public BigDecimal getCOUPONNO() {
	return COUPONNO;
}
public void setCOUPONNO(BigDecimal cOUPONNO) {
	COUPONNO = cOUPONNO;
}
public String getQUOTA() {
	return QUOTA;
}
public void setQUOTA(String qUOTA) {
	QUOTA = qUOTA;
}
public BigDecimal getLIMITPRICE() {
	return LIMITPRICE;
}
public void setLIMITPRICE(BigDecimal lIMITPRICE) {
	LIMITPRICE = lIMITPRICE;
}
public BigDecimal getPERIOD() {
	return PERIOD;
}
public void setPERIOD(BigDecimal pERIOD) {
	PERIOD = pERIOD;
}


}
