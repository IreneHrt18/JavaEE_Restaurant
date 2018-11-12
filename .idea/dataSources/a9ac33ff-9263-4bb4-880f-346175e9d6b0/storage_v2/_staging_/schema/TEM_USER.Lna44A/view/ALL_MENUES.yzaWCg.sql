create or replace view ALL_MENUES as
  (
  select DISH.DISHID as dish_id, dish.name as dish_name,dish.price,dish.IMG,dish.DES,RESTAURANT.RESTAURANTID as rest_id ,restaurant.name as rest_name,restaurant.ADDRESS,RESTAURANT.PHONE,RESTAURANT.NOTIC,restaurant.FEE,restaurant.STARS
  from menu join dish on menu.dishid=dish.dishid
            join restaurant on  restaurant.restaurantid=menu.restaurantid
  )


