create or replace procedure insert_his
  (
    dish_id in number,
    restaurant_id in number,
    browse_date_ in date
  ) is 
  begin
    insert into history (dishid,restaurantid,browse_date)
    values(dish_id,restaurant_id,browse_date_);
    commit;
  end;
/

