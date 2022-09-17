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
