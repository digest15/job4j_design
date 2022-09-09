select
    it.title title,
    usr.name autor,
    cat.name category,
    st.name state,
    com.text comm
from items it
 inner join users usr on it.user_id = usr.id
 inner join categoryes cat on it.category_id = cat.id
 inner join states st on it.category_id = st.id
 left join comments com on it.id = com.item_id;