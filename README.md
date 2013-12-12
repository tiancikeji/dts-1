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
   id                   int                            NOT NULL AUTO_INCREMENT,
   name                 char(255)                      null,
   created_at           datetime(255)                  null,
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



create table area 
(
   id                   int(11)                        NOT NULL AUTO_INCREMENT,
   name                 char                           null,
   created_at           datetime                       null,
   parent_id            int                            null,
   background           char(255)                      null,
   scope_start          int                            null,
   scope_end            int                            null,
   constraint PK_AREA primary key clustered (id)
);



git usage
1.先git status 查看状态

/*==============================================================*/
/* Table: sensor                                                */
/*==============================================================*/
create table sensor 
(
   id                   int                            not null,
   name                 char(255)                      null,
   constraint PK_SENSOR primary key clustered (id)
);


/*==============================================================*/
/* Table: channel                                               */
/*==============================================================*/
create table channel 
(
   id                   int                            not null,
   name                 char(255)                      null,
   sensor_id            int                            null,
   constraint PK_CHANNEL primary key clustered (id)
);


/*==============================================================*/
/* Table: area_channel                                          */
/*==============================================================*/
create table area_channel 
(
   area_id              int                            not null,
   channel_id           int                            not null,
   scope_start          int                            null,
   scope_end            int                            null,
   constraint PK_AREA_CHANNEL primary key clustered (area_id, channel_id)
);



