create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments (name) values ('IT');
insert into departments (name) values ('BUH');
insert into departments (name) values ('HR');
insert into departments (name) values ('INVEST');

insert into employees (name, department_id) values ('Прогер 1', 1);
insert into employees (name, department_id) values ('Прогер 2', 1);
insert into employees (name, department_id) values ('Бух 1', 2);
insert into employees (name, department_id) values ('Бух 2', 2);
insert into employees (name, department_id) values ('Младший специалист по счастью', 3);
insert into employees (name, department_id) values ('Старший специалист по счастью', 3);
insert into employees (name) values ('Стажер');

--left join
select
    e.name employee,
    d.name department
from employees e
    left join departments d
        on e.department_id = d.id;

--right join
select
    e.name employee,
    d.name department
from employees e
    right join departments d
        on e.department_id = d.id;

--where departmen without employees
select
    d.name
from departments d
    left join employees e
        on d.id = e.department_id
where e.id is null;


--teens
create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(1)
);

insert into teens (name, gender) values ('Вася', 'M');
insert into teens (name, gender) values ('Петя', 'M');
insert into teens (name, gender) values ('Глаша', 'Ж');
insert into teens (name, gender) values ('Маша', 'Ж');

select
    t1.name as "Родитель 1",
    t2.name as "Родитель 2"
from teens t1
    cross join teens t2
where t1.name != t2.name;