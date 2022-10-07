create table peoples (
    id serial primary key,
    name varchar(255),
    address varchar(255),
    gender_id int references genders(id)
);

create table films (
    id serial primary key,
    name varchar(255)
);

create table genders (
    id serial primary key,
    name varchar(6)
);

create table films_sharings (
    people_id int references peoples(id),
    film_id int references films(id),
    PRIMARY KEY(people_id, film_id)
);


Table peoples {
  id int [pk]
  name varchar
  address carchar
  gender_id int
 }

 Table gender {
  id int [pk]
  name varchar
}

Table films {
  id int [pk]
  name varchar
}

Table films_sharings {
  people_id int
  film_id int
}

Ref: films_sharings.film_id > films.id
Ref: films_sharings.people_id > peoples.id
Ref: peoples.gender_id > gender.id