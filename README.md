dts
===

dts


create table cable 
(
   id                   int                            null,
   length               char(50)                       null,
   signal_1             char(255)                      null,
   signal_2             char(255)                      null,
   created_at           datetime                       null,
   temperature          double                         null
);



1. create table
/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" 
(
   id                   int                            null,
   name                 char(255)                      null,
   created_at           datetime                       null,
   password             char(255)                      null
);


2. create model

user model

3. create service

user service   new edit  findAll findByName

4. create controller 
user controller  login  new  list

5. create views

user login view  new view index view list view

6. modify views

add form in user new page

list view iterator

add login form in login page

