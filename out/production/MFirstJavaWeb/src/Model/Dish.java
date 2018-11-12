package Model;


public class Dish {

  private int dishid;
  private String name;
  private int price;
  private String img;
  private String des;

  public Dish(int dishid, String name, int price, String img, String des) {
    this.dishid = dishid;
    this.name = name;
    this.price = price;
    this.img = img;
    this.des = des;
  }

  public Dish(int dishid) {
    this.dishid = dishid;
  }

  public Dish() {
  }


  public int getDishid() {
    return dishid;
  }

  public void setDishid(int dishid) {
    this.dishid = dishid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

}
