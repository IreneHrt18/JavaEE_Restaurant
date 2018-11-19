package Bean;

public class OrderDish {
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
}
