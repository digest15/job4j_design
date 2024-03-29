create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age) values ('tiger', 7000);
insert into fauna (name, avg_age, discovery_date) values ('superfish', 18000, '2022-10-09');
insert into fauna (name, avg_age, discovery_date) values ('plainfish', 300, '1850-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >=10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';