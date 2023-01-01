-- названия всех фильмов, которые сняты по книге
select name from movie
intersect
select title from book;

--  все названия книг, у которых нет экранизации
select title from book
except
select name from movie;

-- все уникальные названия произведений из таблиц movie и book
-- (т.е фильмы, которые сняты не по книге, и книги без экранизации)
(select title from book
except
select name from movie)
union all
(select name from movie
except
select title from book);
