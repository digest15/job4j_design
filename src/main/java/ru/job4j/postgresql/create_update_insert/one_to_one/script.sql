create table engine (
    id serial primary key,
    win varchar(9)
);

create table car(
    id serial primary key,
    win varchar(9)
);

create table engine_car(
    id serial primary key,
    engine_id int references engine(id) unique,
    car_id int references car(id) unique
);

insert into engine(win) values ('123456789');
insert into car(win) values ('987654321');
insert into engine_car(engine_id, car_id) values (1,1);

select c.win, e.win from engine_car ec
    inner join car c on ec.car_id = c.id
    inner join engine e on ec.engine_id = e.id;