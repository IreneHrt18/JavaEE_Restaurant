package Data;

import DAO.DishDAOIpt;
import DAO.MenuDAOIpt;
import DAO.RestaurantDAOIpt;
import Model.Dish;
import Model.Menu;
import Model.Restaurant;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class InitData {
    public static void main(String[] args)
    {
        ArrayList<Dish> dishes =new ArrayList<Dish>();
        ArrayList<Restaurant> restaurants=new ArrayList<Restaurant>();
        String [] dishName={"招牌骨汤牛肉面","油泼扯面","红柳羊肉","串碳烤羊腿","老碗鱼","大盘鸡",
                "枣泥甑糕","秦岭山菌烧豆腐","八宝葫芦鸡","酸奶安康浆水鲈鱼","招牌辣子面",
                "芹菜麦饭","秘制面皮","水盆儿羊肉","biangbiang面","酱肉烤包子","桂花稠酒",
                "羊肚酸汤饺","陕北烤羊排","烤面筋","农家蜜枣甄糕","疙瘩汤","长豆角烧茄子",
                "时辰豆腐","蒜泥捣茄子","烤羊腰子","川北凉粉","蒜香黄瓜","手撕包菜","番茄鳕鱼"
                ,"西域椒麻鸡","麻酱毛肚","香辣土豆片","烤猪手","美颜盆盆菜","果仁菠菜",
                "关中特色炒饭","椒麻鸡","陕北小米粥","手撕有机花菜","豆腐砂锅","BBQ香烤牛肉饭",
                "咖喱扇贝饭","罗勒奶酪意大利面","经典番茄鸡肉意大利面","牛肉咖喱饭","抹茶冰淇淋",
                "奶香布朗酱牛肉煎蛋双汉堡排饭","黑芝麻冰淇淋","肋眼牛排饭黑胡椒炒饭",
                "香菇玉米汁土豆泥","海鲜咖喱饭","三文鱼鸡肉双拼饭","蔬菜沙拉","金桔柠檬水",
                "铁板寿喜烧","咖喱牛肉芝士胡椒寿喜烧牛肉饭","蒜香牛肉意面","芝士蛋包饭","" +
                "青柠梅子牛肉拼汉堡排","咖喱饭","bbq牛肉鸡肉饭","bbq特选牛肉拼汉堡排",
                "三文鱼拼鸡排煎蛋双片汉堡排","胡椒鸡排芝士咖喱鸡肉饭","味增汤牛肉寿喜烧",
                "珍宝咖喱牛肉饭","珍宝黑椒肥牛饭","青桔梅子柚子茶","花生巧克力冰淇淋",
                "蒜香海鲜面煎蛋","BBQ特选牛肉双片西冷牛排","桂花酸梅汤"};
        String restaurantName[]= {"纽约客美式餐厅", "秦门·陕西菜","10 Corso Como Cafe",
                "蜀国演义酒楼","保利大厦丽宫中餐厅","原食日记","北平食府","艺薯家土豆焗饭大师"};

        String restAddress[]={"朝阳区湖景东路11号新奥购物中心H1层（新奥购物中心地下一层H1-61号纽约客餐厅，" +
                "近MRT奥林匹克公园站D口）",
        "房山区长阳镇天星街1号院绿地缤纷城3层","朝阳区建国路87号北京SKPD区1层",
                "西城区黄寺大街23号北广福丽特大厦5楼（近福丽特商业街）",
                "朝阳区东直门南大街14号保利大厦A座2层","朝阳区光华路9号2号楼四层04商业内L402F、L402G号",
                "丰台区方庄南路157号7天酒店1楼","朝阳区工体北路8号院三里屯SOHO1号商场B1层B1-121"
        };
        String restPhone[]={"3215648","9871465","7894534","8897621","7748215","3354789","6664512","3038741"};

        DishDAOIpt dishDAOIpt=new DishDAOIpt();
        RestaurantDAOIpt restaurantDAOIpt=new RestaurantDAOIpt();
        MenuDAOIpt menuDAOIpt=new MenuDAOIpt();
//        for(int i=0;i<dishName.length;i++)
//        {
//            Dish dish =new Dish(0,dishName[i],i*37%100);
//            try {
//                dishDAOIpt.addDish(dish);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        for(int i=0;i<restaurantName.length;i++)
//        {
//            Restaurant restaurant=new Restaurant(0,restaurantName[i],restAddress[i],restPhone[i]);
//            try {
//                restaurantDAOIpt.addRestaurant(restaurant);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        Random random=new Random();

        for(int  i=9;i<11;i++)
        {
            Restaurant restaurant=new Restaurant(i);
            for(int j=0;j<random.nextInt(62)+10;j++) {
                Menu menu = new Menu(restaurant, new Dish(random.nextInt(73) + 69));
                try {
                    menuDAOIpt.addMenu(menu);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
