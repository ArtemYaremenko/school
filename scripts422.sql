create table human (id serial primary key, name text not null, age integer check(age >= 18), rights boolean);
create table car (id serial primary key, mark text, model text, price numeric);
alter table human add car_id serial references car (id);
