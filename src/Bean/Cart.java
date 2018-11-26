package Bean;

import java.math.BigDecimal;

public class Cart {

    private Dish dish;
    private User user;
    private BigDecimal dishCount;

    public Cart() {
        this.dish=new Dish();
        this.user=new User();
        this.dishCount=BigDecimal.valueOf(0);
    }

    public String getDISHNO() {
        return dish.getDISHNO();
    }

    public void setDishno(String dishno) {
        this.dish.setDISHNO(dishno);
    }

    public String getUserno() {
        return user.getUSERNO();
    }

    public void setUserno(String userno) {
        this.user.setUSERNO(userno);
    }

    public BigDecimal getDishCount() {
        return dishCount;
    }

    public void setDishCount(BigDecimal dishCount) {
        this.dishCount = dishCount;
    }

    public Dish getDish()
    {
        return this.dish;
    }
    public User getUser()
    {
        return user;
    }

    public void setPrice(BigDecimal price)
    {
        dish.setPRICE(price);
    }
    public BigDecimal getPrice()
    {
        return dish.getPRICE();

    }

    public void setImg(String img)
    {
        dish.setIMG(img);
    }

    public String getImg()
    {
        return dish.getIMG();
    }
    public String getDishname() {
        return dish.getDISHNAME();
    }

    public void setDishname(String dishname) {
        this.dish.setDISHNAME(dishname);
    }


}
