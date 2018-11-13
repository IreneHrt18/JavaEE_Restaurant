package Bean;
import java.math.BigDecimal;
import java.util.ArrayList;
public class User {
	private String USERNAME;
	private String PASSWORD;
	private String ICONURL;
	private String PHONENUM;
	private String CUSNOTE;	
	private BigDecimal USERNO;
	

public BigDecimal getUSERNO() {
	return USERNO;
}
public void setUSERNO(BigDecimal uSERNO) {
	USERNO = uSERNO;
}
public String getUSERNAME() {
	return USERNAME;
}
public void setUSERNAME(String uSERNAME) {
	USERNAME = uSERNAME;
}
public String getPASSWORD() {
	return PASSWORD;
}
public void setPASSWORD(String pASSWORD) {
	PASSWORD = pASSWORD;
}
public String getICONURL() {
	return ICONURL;
}
public void setICONURL(String iCONURL) {
	ICONURL = iCONURL;
}
public String getPHONENUM() {
	return PHONENUM;
}
public void setPHONENUM(String pHONENUM) {
	PHONENUM = pHONENUM;
}
public String getCUSNOTE() {
	return CUSNOTE;
}
public void setCUSNOTE(String cUSNOTE) {
	CUSNOTE = cUSNOTE;
}

}
