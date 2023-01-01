select * from customers;

-- Список клиентов, возраст которых является минимальным
select
    c.first_name,
    c.last_name
from customers c
where
    c.age = (select min(age) from customers);

-- Список пользователей, которые еще не выполнили ни одного заказа
select
    c.first_name,
    c.last_name
from customers c
where
    c.id not in (select customer_id from orders)