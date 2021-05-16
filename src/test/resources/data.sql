DROP TABLE IF EXISTS restaurant_meal;
DROP TABLE IF EXISTS restaurant_user;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS usr;

DROP sequence IF EXISTS sequence_glob;

create sequence sequence_glob start 100;

create table if not exists meals
(
	id integer not null DEFAULT nextval('sequence_glob'),
	name varchar(255) not null,
	description varchar(255) not null,
	price integer not null,
	constraint meals_pkey
		primary key (id)
);

create table if not exists restaurants
(
	id integer not null DEFAULT nextval('sequence_glob'),
	name varchar(255) not null,
	description varchar(255) not null,
	rating integer not null,
	constraint restaurants_pkey
		primary key (id)
);

create table if not exists restaurant_meal
(
	id integer not null DEFAULT nextval('sequence_glob'),
	name varchar(255) not null,
	added date not null,
	meel_id integer not null,
	restaurant_id integer not null,
	constraint restaurant_meal_pkey
		primary key (id),
	constraint fklh3wbcwu2r6gy6m1qajf1y5vs
		foreign key (meel_id) references meals
			on delete cascade,
	constraint fkb91043jejuf4v06v1pkndhf71
		foreign key (restaurant_id) references restaurants
			on delete cascade
);

create table if not exists usr
(
	id bigint not null DEFAULT nextval('sequence_glob'),
	active boolean not null,
	name varchar(255),
	password varchar(255),
	constraint usr_pkey
		primary key (id)
);

create table if not exists restaurant_user
(
	id integer not null DEFAULT nextval('sequence_glob'),
	name varchar(255) not null,
	voited timestamp not null,
	restaurant_id integer not null,
	user_id bigint not null,
	constraint restaurant_user_pkey
		primary key (id),
	constraint fkpch0xg2wdfy45m9f6ob65mujj
		foreign key (restaurant_id) references restaurants
			on delete cascade,
	constraint fkm1sv8mvtkpajoih0tjdtol0jc
		foreign key (user_id) references usr
			on delete cascade
);

create table if not exists user_role
(
	user_id bigint not null DEFAULT nextval('sequence_glob'),
	role varchar(255),
	constraint fkfpm8swft53ulq2hl11yplpr5
		foreign key (user_id) references usr
);

INSERT INTO usr (id, active, name, password) VALUES
  (1, true, 'admin', '$2y$12$sCcExijql0/nks7tJh/cPOxvk1mwhPVZwpggPmX285tgO3tvnLcIm'),
  (2, true, 'user', '$2y$12$OLJPuWKFtd/5VkE6faUxAuYkLl1DcT8nKY2u2Scyt6KPp3M3KBNPS'),
  (3, true, 'notuser', '$2y$12$N4KgCR4zwzBevxVObUjKUurfSTYDec2k6SsBaqpAXM5VyKg1V4TXS');

INSERT INTO user_role (user_id, role) VALUES
  (1,'ADMIN'),
  (1,'USER'),
  (2,'USER');

INSERT INTO meals (id, name, description, price) VALUES
    (1,'milk','fat white milk',100),
    (6,'bread','tasty crusty bread',200),
    (2,'ratatouille','wash your paws before cooking',50),
    (4,'water','just water',1),
    (5,'wiedzminskie eliksiry','dont eat it!',1000),
    (3,'pizza','Excellent pizza',0);

INSERT INTO restaurants (id, name, description, rating) VALUES
    (1,'The Three Broomsticks','It so magick',3),
    (2,'The Mos Eisley Cantina','Do. Or do not. There is no try',2),
    (3,'La Ratatouille','It is Rat',4);

INSERT INTO restaurant_meal (id, name, added, meel_id, restaurant_id) VALUES
    (1, 'item', '2021-05-15', 1, 1),
    (2, 'item', '2021-05-15', 2, 1),
    (3, 'item', '2021-05-15', 3, 2),
    (4, 'item', '2021-05-15', 4, 2),
    (5, 'item', '2021-05-15', 5, 3),
    (6, 'item', '2021-05-15', 6, 3);

INSERT INTO restaurant_user (id, name, voited, restaurant_id, user_id) VALUES
    (1, 'vote', '2021-05-15 09:11:10.000000', 1, 1),
    (2, 'vote', '2021-05-15 09:00:01.000000', 2, 2);



