create table if not exists Ingredient (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null
);
create table if not exists Taco (
id identity,
name varchar(50) not null,
created_at timestamp not null
);
create table if not exists Taco_Ingredients (
taco bigint not null,
ingredient varchar(4) not null
);
alter table Taco_Ingredients
    add foreign key (taco) references Taco (id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient (id);
create table if not exists Taco_Order (
id identity,
deliveryName varchar(50) not null,
deliveryStreet varchar(50) not null,
deliveryCity varchar(50) not null,
deliveryState varchar(2) not null,
deliveryZip varchar(10) not null,
ccNumber varchar(16) not null,
ccExpiration varchar(5) not null,
ccCVV varchar(3) not null,
placedAt timestamp not null
);
create table if not exists Taco_Order_Tacos (
tacoOrder bigint not null,
taco bigint not null
);
alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order (id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco (id);

create table if not exists User(
    id identity,
    username varchar(50) not null,
    password varchar(100) not null,
    full_name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(50) not null,
    zip varchar(50) not null,
    phone_number varchar(50) not null
);

CREATE SEQUENCE HIBERNATE_SEQUENCE start with 1 increment by 1;