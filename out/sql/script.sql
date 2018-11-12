create sequence DISHID_SEQ
  maxvalue 9999
/

create sequence USERID_SEQ
  maxvalue 9999
/

create sequence RESTAURANTID_SEQ
  maxvalue 9999
/

create table DISH
(
  DISHID NUMBER                    not null
    primary key,
  NAME   VARCHAR2(60) default NULL not null,
  PRICE  NUMBER                    not null,
  IMG    VARCHAR2(100) default 'res/bao.jpg',
  DES    VARCHAR2(200)
)
/

create trigger INSERT_DISH_ID_TRI
  before insert
  on DISH
  for each row
  begin
    select dishid_seq.nextval into :new.dishid from dual;
  end;
/

create table RESTAURANT
(
  RESTAURANTID NUMBER       not null
    primary key,
  NAME         VARCHAR2(30) not null,
  ADDRESS      VARCHAR2(200) default NULL,
  PHONE        VARCHAR2(20),
  NOTIC        VARCHAR2(300),
  FEE          NUMBER,
  STARS        NUMBER        default 5
)
/

create trigger INSERT_REST_ID_TRI
  before insert
  on RESTAURANT
  for each row
  begin
    select restaurantid_seq.nextval into :new.restaurantid from dual;
  end;
/

create table MENU
(
  DISHID       NUMBER not null
    constraint MENU_FK1
    references DISH,
  RESTAURANTID NUMBER not null
    constraint MENU_FK2
    references RESTAURANT,
  constraint MENU_PK
  primary key (DISHID, RESTAURANTID)
)
/

create table USERS_TABLE
(
  USERID   NUMBER       not null
    primary key,
  USERNAME VARCHAR2(30),
  PASSWORD VARCHAR2(20) not null,
  SIGNDATE TIMESTAMP(6) default NULL
)
/

create trigger INSERT_USERS_ID_TRI
  before insert
  on USERS_TABLE
  for each row
  begin
    select userid_seq.nextval into :new.userid from dual;
  end;
/

create table ORDER_LIST
(
  DISHID       NUMBER,
  RESTAURANTID NUMBER,
  AMOUNT       NUMBER not null,
  USERID       NUMBER default NULL
    constraint ORDER_FK3
    references USERS_TABLE,
  constraint ORDER_LIST_MENU_DISHID_RESTAURANTID_FK
  foreign key (DISHID, RESTAURANTID) references MENU
)
/

create index USER_ORDER_INDEX
  on ORDER_LIST (USERID)
/

create table HISTORY
(
  DISHID       NUMBER
    constraint HISTORY_FK1
    references DISH,
  RESTAURANTID NUMBER
    constraint HISTORY_FK2
    references RESTAURANT,
  BROWSE_DATE  TIMESTAMP(6) default NULL,
  constraint HISTORY_MENU_DISHID_RESTAURANTID_FK
  foreign key (DISHID, RESTAURANTID) references MENU
)
/

create trigger INSERT_HISTORY_TRI
  after insert
  on ORDER_LIST
  for each row
  declare
    dish_id       int;
    restaurant_id int;
  begin
    insert into history values (:new.dishid, :new.restaurantid, sysdate);
  end;
/

create view ALL_MENUES as
  (select DISH.DISHID             as dish_id,
          dish.name               as dish_name,
          dish.price,
          dish.IMG,
          dish.DES,
          RESTAURANT.RESTAURANTID as rest_id,
          restaurant.name         as rest_name,
          restaurant.ADDRESS,
          RESTAURANT.PHONE,
          RESTAURANT.NOTIC,
          restaurant.FEE,
          restaurant.STARS
   from menu
          join dish on menu.dishid = dish.dishid
          join restaurant on restaurant.restaurantid = menu.restaurantid)
/

create procedure rest_menu_in_page(
  restaurant_id in out number,
  dish_id       in out number,
  row_num       in     number

) is
  begin
    select DISHID, RESTAURANTID
        into dish_id, restaurant_id
    from (select rownum r, menu.*
          from menu
          where RESTAURANTID = restaurant_id
             or DISHID = dish_id) e
    where r = row_num;
  end;
/

create procedure insert_his
  (
    dish_id       in number,
    restaurant_id in number,
    browse_date_  in TIMESTAMP
  ) is
  begin
    insert into history (dishid, restaurantid, browse_date) values (dish_id, restaurant_id, browse_date_);
    commit;
  end;
/


