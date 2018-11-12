package Model;


public class Restaurant {

  private int restaurantid;
  private String name;
  private String address;
  private String phone;
  private String notic;
  private int fee;
  private int stars;

    public Restaurant(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public Restaurant() {
    }

  public Restaurant(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public Restaurant(int restaurantid, String name, String address, String phone, String notic, int fee, int stars) {
    this.restaurantid = restaurantid;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.notic = notic;
    this.fee = fee;
    this.stars = stars;
  }


  public int getRestaurantid() {
    return restaurantid;
  }

  public void setRestaurantid(int restaurantid) {
    this.restaurantid = restaurantid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getNotic() {
    return notic;
  }

  public void setNotic(String notic) {
    this.notic = notic;
  }


  public int getFee() {
    return fee;
  }

  public void setFee(int fee) {
    this.fee = fee;
  }


  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

}
