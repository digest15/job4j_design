create table roles (
    id serial primary key,
    name varchar(255)
);

create table users (
    id serial primary key,
    name varchar(255),
    role_id int references roles(id)
);


create table rules (
    id serial primary key,
    name varchar(255)
);

create table roles_rules (
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);


create table categoryes (
    id serial primary key,
    name varchar(255)
);

create table states (
    id serial primary key,
    name varchar(255)
);

create table items (
    id serial primary key,
    title varchar(255),
    user_id int references users(id),
    category_id int references categoryes(id),
    state_id int references states(id)
);

create table comments (
    id serial primary key,
    text varchar(255),
    item_id int references items(id)
);

create table attachs (
    id serial primary key,
    title varchar(255),
    item_id int references items(id)
);