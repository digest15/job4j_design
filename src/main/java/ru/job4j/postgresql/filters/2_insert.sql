insert into types (title) values ('СЫР');
insert into types (title) values ('МОЛОКО');
insert into types (title) values ('МЯСО');

insert into product (title, price, type_id, expired_date)
    values ('Молоко замороженое', 75, 2, '2022-10-25');

insert into product (title, price, type_id, expired_date)
    values ('Молоко прокисшее', 20, 2, '2022-08-30');

insert into product (title, price, type_id, expired_date)
    values ('Сыр плавленный', 50, 1, '2025-01-10');

insert into product (title, price, type_id, expired_date)
    values ('Сыр моцарелла', 1500, 1, '2025-01-10');