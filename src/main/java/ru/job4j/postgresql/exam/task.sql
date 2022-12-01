--task 1
select
    p.name as person,
    c.name as company
from person p
    inner join company c
        on p.company_id = c.id
            and p.company_id <> 5;

--task 2
select
    count(p.id) as "Person count",
    c.name as Company
from person p
    inner join company c
        on p.company_id = c.id
group by c.name
having count(p.id) = (select
                            count(c.id)
                        from person c
                        group by c.company_id
                        order by count(c.id) DESC
                        fetch first 1 rows only);