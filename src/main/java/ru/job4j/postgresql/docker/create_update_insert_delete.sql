create table book (
    id serial primary key,
    title text,
    price integer,
    ebook boolean
);

insert into book (title, price, ebook) values ('Thinking in Java', 150, false );

update book set price=200;

delete from book;

select * from book;