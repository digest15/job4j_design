create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--statement trigger
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();


--row trigger
create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_row
    before insert on products
    for each row
    execute procedure tax_row();


--history
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
            values (NEW.name, NEW.price, CURRENT_DATE);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger trigger_insert_history_of_price
    before insert on products
    for each row
    execute procedure insert_history_of_price();



insert into products (name, producer, count, price) values ('Burger', 'Delitious and .', 1, 10);
insert into products (name, producer, count, price) values ('SheesBurger', 'Delitious and .', 1, 10);

select * from products;
select * from history_of_price;