create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--insert data
insert into products (name, producer, count, price) values ('product_1', 'produser_1', 15, 100);
insert into products (name, producer, count, price) values ('product_2', 'produser_2', 20, 200);

--transaction
begin transaction isolation level serializable;
    update products set count = 26 where name = 'product_2';
    select * from products;
commit;

begin;
    insert into products (name, producer, count, price) values ('product_2', 'produser_2', 20, 200);
    select * from products;
    savepoint svt1;
    insert into products (name, producer, count, price) values ('product_2', 'produser_2', 20, 200);
    select * from products;
    savepoint svt2;
    delete from products;
    rollback to svt1;
commit;
select * from products;

--cursor
insert into products (name, producer, count, price) values ('product_1', 'produser_1', 15, 100);
insert into products (name, producer, count, price) values ('product_2', 'produser_2', 20, 200);
insert into products (name, producer, count, price) values ('product_3', 'produser_3', 15, 100);
insert into products (name, producer, count, price) values ('product_4', 'produser_4', 15, 100);
insert into products (name, producer, count, price) values ('product_5', 'produser_5', 20, 200);
insert into products (name, producer, count, price) values ('product_6', 'produser_6', 20, 200);
insert into products (name, producer, count, price) values ('product_7', 'produser_7', 20, 200);
insert into products (name, producer, count, price) values ('product_8', 'produser_8', 20, 200);
insert into products (name, producer, count, price) values ('product_9', 'produser_9', 20, 200);
insert into products (name, producer, count, price) values ('product_10', 'produser_10', 20, 200);
insert into products (name, producer, count, price) values ('product_11', 'produser_11', 20, 200);
insert into products (name, producer, count, price) values ('product_12', 'produser_12', 20, 200);
insert into products (name, producer, count, price) values ('product_13', 'produser_13', 20, 200);
insert into products (name, producer, count, price) values ('product_14', 'produser_14', 20, 200);
insert into products (name, producer, count, price) values ('product_15', 'produser_15', 20, 200);
insert into products (name, producer, count, price) values ('product_16', 'produser_16', 20, 200);
insert into products (name, producer, count, price) values ('product_17', 'produser_17', 20, 200);
insert into products (name, producer, count, price) values ('product_18', 'produser_18', 20, 200);
insert into products (name, producer, count, price) values ('product_19', 'produser_19', 20, 200);
insert into products (name, producer, count, price) values ('product_20', 'produser_10', 20, 200);

begin;
    declare cursor_products cursor for select * from products order by id;
    fetch last from cursor_products;
    fetch backward from cursor_products;
    close cursor_products;
commit;




