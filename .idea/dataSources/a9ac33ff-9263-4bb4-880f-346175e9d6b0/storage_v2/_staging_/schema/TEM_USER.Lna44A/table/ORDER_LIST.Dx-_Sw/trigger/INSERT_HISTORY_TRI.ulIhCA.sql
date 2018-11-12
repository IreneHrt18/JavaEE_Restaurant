create trigger INSERT_HISTORY_TRI
  after insert
  on ORDER_LIST
  for each row
  declare 
dish_id int ;
restaurant_id  int;
begin
insert into history values (:new.dishid,:new.restaurantid,sysdate);
end;
/

