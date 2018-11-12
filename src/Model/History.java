package Model;


import java.sql.Timestamp;
import java.util.Date;

public class History {

  private Menu menu;
  private int dishid;
  private int restaurantid;
  private Date browseDate;

  public History(int dishid, int restaurantid, Date browseDate) {
    this.dishid = dishid;
    this.restaurantid = restaurantid;

    this.browseDate = browseDate;
  }

  public History(Menu menu,Date browseDate)
  {
    this.menu=menu;
    this.browseDate=browseDate;
  }

  public int getDishid() {
    return dishid;
  }

  public void setDishid(int dishid) {
    this.dishid = dishid;
  }


  public int getRestaurantid() {
    return restaurantid;
  }

  public void setRestaurantid(int restaurantid) {
    this.restaurantid = restaurantid;
  }


  public java.sql.Timestamp getBrowseDate() {
    return new Timestamp(browseDate.getTime());
  }

  public void setBrowseDate(java.sql.Timestamp browseDate) {
    this.browseDate = browseDate;
  }


  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }
}
