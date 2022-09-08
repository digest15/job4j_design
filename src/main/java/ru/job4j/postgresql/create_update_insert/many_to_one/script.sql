create table employees(
    id serial primary key,
    firstname varchar(255)
);

create table tasks(
    id serial primary key,
    title varchar(255),
    employee_id int references employees(id)
);

insert into employees(firstname) values ('programmer1');
insert into tasks(title, employee_id) VALUES ('Make new table in DB', 1);

select * from tasks;

select * from employees where id in (select employee_id from tasks);