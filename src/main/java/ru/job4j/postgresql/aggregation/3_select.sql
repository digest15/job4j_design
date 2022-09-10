select
    p.name People,
    d.name Device,
    d.price Price
from devices_people dp
    join people p on p.id = dp.people_id
    join devices d on d.id = dp.id

select
    'All devices' as "Devise",
    round(avg(d.price)::numeric, 2) as "AVG Price"
from devices d;

select
    p.name as "People",
    round(avg(d.price)::numeric, 2) as "AVG Divices Price"
from devices_people dp
    join people p on p.id = dp.people_id
    join devices d on d.id = dp.id
group by p.name
having round(avg(d.price)::numeric, 2)
order by round(avg(d.price)::numeric, 2);

select
    p.name as "People",
    round(avg(d.price)::numeric, 2) as "AVG Divices Price"
from devices_people dp
    join people p on p.id = dp.people_id
    join devices d on d.id = dp.id
group by p.name
having round(avg(d.price)::numeric, 2) > 1000
order by round(avg(d.price)::numeric, 2);






