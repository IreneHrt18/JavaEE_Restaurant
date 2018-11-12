package Model;

public class Order {
    private Menu menu;
    private int amount;
    private int userid;

    public Order(Menu menu, int amount, int userid) {
        this.menu = menu;
        this.amount = amount;
        this.userid = userid;
    }

    public Order() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
