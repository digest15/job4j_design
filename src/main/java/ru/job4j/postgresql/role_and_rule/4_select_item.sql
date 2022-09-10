select
    it.title as "Задание",
    usr.name as "Автор",
    cat.name "Категория",
    st.name as "Состояние задачи",
    com.text as "Комментарий"
from items it
 inner join users usr on it.user_id = usr.id
 inner join categoryes cat on it.category_id = cat.id
 inner join states st on it.category_id = st.id
 left join comments com on it.id = com.item_id;