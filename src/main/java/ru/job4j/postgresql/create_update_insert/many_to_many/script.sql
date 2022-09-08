create table peoples(
     id serial primary key,
     firstname varchar(255)
 );

 create table channels(
     id serial primary key,
     title varchar(255)
 );

 create table peoples_channels(
     id serial primary key,
     people_id int references peoples(id),
     channel_id int references channels(id)
 );

insert into peoples(firstname) values ('Ivan');
insert into peoples(firstname) values ('Kirill');
insert into peoples(firstname) values ('Roman');

insert into channels(title) values ('Java Book');
insert into channels(title) values ('Navalniy live');
insert into channels(title) values ('Polite People');

insert into peoples_channels(people_id, channel_id) values (1, 1);
insert into peoples_channels(people_id, channel_id) values (1, 2);
insert into peoples_channels(people_id, channel_id) values (1, 3);
insert into peoples_channels(people_id, channel_id) values (2, 1);
insert into peoples_channels(people_id, channel_id) values (2, 2);
insert into peoples_channels(people_id, channel_id) values (3, 3);

select p.firstname, c.title from peoples_channels pc
    inner join peoples p on pc.people_id = p.id
    inner join channels c on pc.channel_id = c.id;