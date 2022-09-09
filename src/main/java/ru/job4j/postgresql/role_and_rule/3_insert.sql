insert into roles (name) values ('ADMIN');
insert into roles (name) values ('SUPER_USER');
insert into roles (name) values ('USER');

insert into users (name, role_id) values ('Петр Николаевич', 2);
insert into users (name, role_id) values ('Вася Петрович', 1);
insert into users (name, role_id) values ('Глаша', 3);

insert into rules (name) values ('GOT');
insert into rules (name) values ('create_task');
insert into rules (name) values ('create_comment');
insert into rules (name) values ('finish_task');

insert into roles_rules (role_id, rule_id) values (2, 1);
insert into roles_rules (role_id, rule_id) values (1, 2);
insert into roles_rules (role_id, rule_id) values (1, 3);
insert into roles_rules (role_id, rule_id) values (1, 4);
insert into roles_rules (role_id, rule_id) values (3, 2);
insert into roles_rules (role_id, rule_id) values (3, 3);

insert into categoryes (name) values ('IT');
insert into categoryes (name) values ('BUH');
insert into categoryes (name) values ('HR');
insert into categoryes (name) values ('ZUP');

insert into states ("name") values ('NEW');
insert into states ("name") values ('WORK');
insert into states ("name") values ('CANSELLED');
insert into states ("name") values ('FINISH');

insert into items (title, user_id, category_id, state_id) values ('Все сломалось', 3, 1, 4);

insert into comments (text, item_id) values ('Дебилка жмет не ту кнопку', 1);
insert into comments (text, item_id) values ('Сам такой - не приходи домой', 1);

insert into attachs (title, item_id) values ('Вот скрин куда жмакать нада!', 1);

