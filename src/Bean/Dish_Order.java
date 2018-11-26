package Bean;

import java.math.BigDecimal;

public class Dish_Order {
    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    private String orderno;
    private String dishno ;
    private String comments;
    private BigDecimal dishcount;

    public String getDishno() {
        return dishno;
    }

    public void setDishno(String dishno) {
        this.dishno = dishno;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public BigDecimal getDishcount() {
        return dishcount;
    }

    public void setDishcount(BigDecimal dishcount) {
        this.dishcount = dishcount;
    }
}
