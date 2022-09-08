create table engine(
    id serial primary key,
    win varchar(9)
);

create table car(
    id serial primary key,
    win varchar(9),
    engine_id int references engine(id) unique
);

insert into engine(win) values ('123456789');
insert into car(win, engine_id) values ('987654321', 1);

select c.win, e.win from car c left join engine e on c.engine_id = e.id;