create table peoples (
    id serial primary key,
    name varchar(255),
    sex varchar(1)
);

create table films (
    id serial primary key,
    name varchar(255)
);

create table peoples_films (
    id serial primary key,
    film_id int references films(id),
    people_id int references peoples(id)
);

create table addresses (
    id serial primary key,
    name varchar(255),
    people_id int references peoples(id)
);


Table peoples {
  id int [pk]
  name varchar
  sex varchar
 }

Table films {
  id int [pk]
  name varchar
}

Table peoples_films {
  id int [pk]
  film_id int
  people_id int
}

Table addresses {
  id int [pk]
  name varchar
  people_id int
}

// Creating references
// You can also define relaionship separately
// > many-to-one; < one-to-many; - one-to-one; <> many-to-many
Ref: peoples_films.film_id > films.id
Ref: peoples_films.people_id > peoples.id
Ref: addresses.people_id > peoples.id