select
    p.title as "Продукт"
from product p
    join types t on p.type_id = t.id and t.title = 'СЫР';

select
    p.title as "Продукт"
from product p
where title like '%мороженое%';

select
    p.title as "Продукт"
from product p
where expired_date < CURRENT_DATE;

select
    p.title as "Продукт",
    p.price as "Цена"
from product p
order by price desc
limit 1;

select
    t.title type,
    count(p.id) count
from types t
    left join product p on p.type_id = t.id
group by t.title;

select
    p.title as "Продукт"
from product p
    join types t on p.type_id = t.id and (t.title = 'СЫР' or t.title = 'МОЛОКО');

select
    t.title type,
    count(p.id) count
from types t
    left join product p on p.type_id = t.id
group by t.title
having count(p.id) < 10;

select
    p.title as "Продукт",
    t.title as "Тип"
from product p
    join types t on p.type_id = t.id;