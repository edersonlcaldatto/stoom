CREATE TABLE public.address (
    id bigserial NOT NULL,
    city varchar(255) NOT NULL,
    complement varchar(255) NULL,
    country varchar(255) NOT NULL,
    latitude float4 NULL,
    longitude float4 NULL,
    neighborhood varchar(255) NOT NULL,
    "number" varchar(255) NOT NULL,
    state varchar(255) NOT NULL,
    street_name varchar(255) NOT NULL,
    zip_code varchar(255) NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
);
