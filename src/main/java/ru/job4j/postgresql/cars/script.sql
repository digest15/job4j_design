create table car_bodies (
    id serial primary key,
    name varchar(255)
);

create table car_engines (
    id serial primary key,
    name varchar(255)
);

create table car_transmissions (
    id serial primary key,
    name varchar(255)
);

create table car (
    id serial primary key,
    name varchar(255),
    car_bodi_id int references car_bodies(id),
    car_engine_id int references car_engines(id),
    car_transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('hatchback');
insert into car_bodies (name) values ('pickup');
insert into car_bodies (name) values ('vagon');

insert into car_engines (name) values ('v6');
insert into car_engines (name) values ('v8');
insert into car_engines (name) values ('v12');
insert into car_engines (name) values ('gibrid');
insert into car_engines (name) values ('electric');

insert into car_transmissions (name) values ('mehanic');
insert into car_transmissions (name) values ('gidro');
insert into car_transmissions (name) values ('variator');
insert into car_transmissions (name) values ('no transmission');
insert into car_transmissions (name) values ('CVT');

insert into car (name, car_bodi_id, car_engine_id, car_transmission_id) values ('Mazda', 2, 1, 2);
insert into car (name, car_bodi_id, car_engine_id, car_transmission_id) values ('Honda', 1, 3, 3);
insert into car (name, car_bodi_id, car_engine_id, car_transmission_id) values ('GMC', 3, 2, 1);
insert into car (name, car_bodi_id, car_engine_id, car_transmission_id) values ('Tesla', 1, 4, 4);
insert into car (name, car_bodi_id, car_transmission_id) values ('Lada', 2, 1);

--all car
select
    car.id id,
    car.name car,
    body.name body,
    eng.name engine,
    trm.name transmission
from car car
    left join car_bodies body
        on car.car_bodi_id = body.id
    left join car_engines eng
        on car.car_engine_id = eng.id
    left join car_transmissions trm
        on car.car_transmission_id = trm.id;

-- car body without car
select
    b.name
from car_bodies b
    left join car c
        on b.id = c.car_bodi_id
where c.id is null;

-- car engine without car
select
    b.name
from car_engines b
    left join car c
        on b.id = c.car_engine_id
where c.id is null;

select * from car;

-- car transmission without car
select
    b.name
from car_transmissions b
    left join car c
        on b.id = c.car_transmission_id
where c.id is null;




