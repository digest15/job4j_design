insert into company (id, name) values (1, 'Yandex');
insert into company (id, name) values (2, 'Google');
insert into company (id, name) values (3, 'Facebook');
insert into company (id, name) values (4, 'Red Hat');
insert into company (id, name) values (5, 'Twitter');

select * from company;

insert into person (id, company_id, name) values (1, 1, 'Kudrin');
insert into person (id, company_id, name) values (2, 2, 'Larry');
insert into person (id, company_id, name) values (3, 2, 'Zuckerberg');
insert into person (id, company_id, name) values (4, 4, 'Mark');
insert into person (id, company_id, name) values (5, 4, 'Bob');
insert into person (id, company_id, name) values (6, 5, 'Mask');

select * from person;
