create table types (
    id serial primary key,
    title varchar(255)
);

create table product(
    id serial primary key,
    title varchar(255),
    type_id int references types(id),
    expired_date date,
    price float
);


