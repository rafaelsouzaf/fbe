create table fbe_company
(
	id bigserial not null
		constraint fbe_company_pkey
			primary key,
	created_at timestamp default CURRENT_TIMESTAMP,
	name varchar(500)
		constraint uk_hy3oy5uuekvey4o2wt2lndm7b
			unique
);

alter table fbe_company owner to postgres;

create table fbe_employee
(
	id bigserial not null
		constraint fbe_employee_pkey
			primary key,
	address varchar(255),
	created_at timestamp default CURRENT_TIMESTAMP,
	email varchar(255),
	name varchar(255),
	salary integer,
	surname varchar(255),
	company_id bigint not null
		constraint fkfuvpxvxaa7h1lk33rie2b2jvb
			references fbe_company
);

alter table fbe_employee owner to postgres;

create index idx_employee_company
	on fbe_employee (company_id);

