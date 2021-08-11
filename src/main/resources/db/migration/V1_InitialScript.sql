create table public.addrees(
   id serial not null,
   streetName varchar(255) not null,
   number varchar(20) not null,
   complement varchar(255),
   neighborhood varchar(255) not null,
   city varchar(100) not null,
   state varchar(20) not null,
   country varchar(100) not null,
   zipCode varchar(20) not null,
   latitude numeric,
   longitude numeric,

   constraint addrees_pkey primary key (id)
)