delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;
insert into Ingredient (id, name, type)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type)
values ('SRCR', 'Sour Cream', 'SAUCE');

--create users
insert into user (id, username, password, full_name, street, city, state, zip, phone_number)
values ('111', 'sa', '199d5574b320560884ed1dfb4ef8baabe3d514c2823c5ca1c3e3ac80b97449bb6bf2ae07cbe7871d',
        'sa', 'sa', 'city', 'state', '123', '123-456');

insert into user (id, username, password, full_name, street, city, state, zip, phone_number)
values ('222', 'na', 'na',
        'na', 'na', 'cifty', 'stdate', '888', '123-888');

--fill order with tacos
insert into taco (name, created_at)
values ('aaaaa', '2022-04-03 22:44:24.045');
insert into taco (name, created_at)
values ('bbbbb', '2022-04-03 22:45:24.045');
insert into taco (name, created_at)
values ('ccccc', '2022-04-03 22:46:24.045');

insert into taco_order
(DELIVERY_NAME, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_AT, user_id)
values
    ('aaaaa', 'aaaaa', 'aaaaa', 'aaaaa', '111', '111', '111', '111', '2022-04-03 22:45:36.105023', '111');

insert into TACO_ORDER_TACOS (order_id, tacos_id)
values ('1', '1');
insert into TACO_ORDER_TACOS (order_id, tacos_id)
values ('1', '2');
insert into TACO_ORDER_TACOS (order_id, tacos_id)
values ('1', '3');

insert into taco_ingredients (taco, ingredient) values ('1','FLTO');
insert into taco_ingredients (taco, ingredient) values ('1','COTO');
insert into taco_ingredients (taco, ingredient) values ('2','CARN');
insert into taco_ingredients (taco, ingredient) values ('3','SRCR');
insert into taco_ingredients (taco, ingredient) values ('3','SLSA');
insert into taco_ingredients (taco, ingredient) values ('3','JACK');

