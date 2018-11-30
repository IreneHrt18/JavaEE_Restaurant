package Bean;

import java.math.BigDecimal;

public class OrderDish {
	private BigDecimal DISHACCOUNT;
	private Order MyOrder;
	private Dish  MyDish;
	private String COMMENTS;
	public Order getMyOrder() {
		return MyOrder;
	}
	public Dish getMyDish() {
		return MyDish;
	}
	public String getCOMMENTS() {
		return COMMENTS;
	}
	public void setMyOrder(Order myOrder) {
		MyOrder = myOrder;
	}
	public void setMyDish(Dish myDish) {
		MyDish = myDish;
	}
	public void setCOMMENTS(String cOMMENTS) {
		COMMENTS = cOMMENTS;
	}
	@Override
	public String toString() {
		return "OrderDish [MyOrder=" + MyOrder + ", MyDish=" + MyDish + ", COMMENTS=" + COMMENTS + "]";
	}
	public BigDecimal getDISHACCOUNT() {
		return DISHACCOUNT;
	}
	public void setDISHACCOUNT(BigDecimal dISHACCOUNT) {
		DISHACCOUNT = dISHACCOUNT;
	}
	public String getDISHNAME() {
		return MyDish.getDISHNAME();
	}
	public void setDISHNAME(String dISHNAME) {
		MyDish.setDISHNAME(dISHNAME);
	}
	public BigDecimal getPRICE() {
		return MyDish.getPRICE();
	}
	public void setPRICE(BigDecimal pRICE) {
		MyDish.setPRICE(pRICE);
	}
}
