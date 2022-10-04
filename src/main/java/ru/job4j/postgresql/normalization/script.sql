create table peoples (
    id serial primary key,
    name varchar(255),
    sex varchar(1)
);

create table films (
    id serial primary key,
    name varchar(255)
);

create table addresses (
    id serial primary key,
    name varchar(255)
);

create table films_sharings (
    people_id int references peoples(id),
    address_id int references addresses(id),
    film_id int references films(id),
    PRIMARY KEY(people_id, address_id)
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

Table films_sharings {
  people_id int
  address_id int
  film_id int
}

Table addresses {
  id int [pk]
  name varchar
}

// Creating references
// You can also define relaionship separately
// > many-to-one; < one-to-many; - one-to-one; <> many-to-many
Ref: peoples_films.film_id > films.id
Ref: peoples_films.people_id > peoples.id
Ref: addresses.people_id > peoples.id