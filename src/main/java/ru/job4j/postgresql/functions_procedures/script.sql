create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--procedure
create or replace procedure delete_products(like_name varchar)
language 'plpgsql'
as $$
    BEGIN
    delete from products where name like like_name;
    END
$$;

call delete_products('product%');

--function
create or replace function f_delete_products(like_name varchar)
returns integer[]
language 'plpgsql'
as
$$
    declare
        result integer[];
    begin
        result := ARRAY(
            select id from products where name like like_name
        );
        delete from products where name like like_name;
        return result;
    end;
$$;

select f_delete_products('product%');


--insert data
insert into products (name, producer, count, price) values ('product_1', 'produser_1', 15, 100);
insert into products (name, producer, count, price) values ('product_2', 'produser_2', 20, 200);

select * from products;
