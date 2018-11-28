package Bean;
import java.math.BigDecimal;
import java.util.Comparator;
public class Dish {
	private String DISHNO;
	private String DISHNAME;
	private String DESCRIPTION;
	private String IMG;
	private BigDecimal  PRICE;
	
	public Dish() {
		// TODO Auto-generated constructor stub
		this.DISHNAME = "";
		this.DISHNO="";
		this.DESCRIPTION="";
		this.IMG="";
		this.PRICE=new BigDecimal("0");
	}
	public static class DishCompar implements Comparator<Dish>{
		public static  final int BY_NAME=1;
		public static final int BY_PRICE=2;
		public static final int BY_NAME_DESC=3;
		public static final int BY_PRICE_DESC=0;
		public int  orderParam=BY_NAME;

		public DishCompar(int orderParam) {
			this.orderParam = orderParam;
		}

		@Override
		public int compare(Dish o1, Dish o2) {
			switch (orderParam)
			{
				case BY_NAME:
					return o1.getDISHNAME().compareTo(o2.getDISHNAME());

				case BY_PRICE:
					return o1.getPRICE().compareTo(o2.getPRICE());

				case BY_PRICE_DESC:
					return o2.getPRICE().compareTo(o1.getPRICE());

				default:
					return o2.getDISHNAME().compareTo(o1.getDISHNAME());

			}
		}
	}
public String getDISHNO() {
		return DISHNO;
	}
	public void setDISHNO(String dISHNO) {
		DISHNO = dISHNO;
	}
	public String getDISHNAME() {
		return DISHNAME;
	}
	public void setDISHNAME(String dISHNAME) {
		DISHNAME = dISHNAME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getIMG() {
		return IMG;
	}
	public void setIMG(String iMG) {
		IMG = iMG;
	}
	public BigDecimal getPRICE() {
		return PRICE;
	}
	public void setPRICE(BigDecimal pRICE) {
		PRICE = pRICE;
	}
	@Override
	public String toString() {
		return "Dish [DISHNO=" + DISHNO + ", DISHNAME=" + DISHNAME + ", DESCRIPTION=" + DESCRIPTION + ", IMG=" + IMG
				+ ", PRICE=" + PRICE + "]";
	}

}