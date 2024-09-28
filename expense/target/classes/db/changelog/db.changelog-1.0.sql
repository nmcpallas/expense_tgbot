--liquibase formatted sql

--create type money.category as enum (
--	'GROCERY',
--    'GIFT',
--    'HEALTH',
--    'TRANSPORT',
--    'PERSONAL_EXPENSES',
--    'PETS',
--    'ADDC',
--    'TRAVEL',
--    'SUBSCRIPTION',
--    'BEAUTY',
--    'INTERNET',
--    'RESTAURANT',
--    'ENTERTAINMENT',
--    'HOME',
--    'SAVING',
--    'CLOTH',
--    'CREDIT',
--    'RENT',
--    'INSTALLMENT',
--    'DEBT',
--    'PSYCH',
--    'CRYPTO',
--    'FAMILY',
--    'SPORT'
--)

--changeset nmcpallas:1
create table if not exists money.expectation(
    id serial primary key,
    expense_category money.category not null,
    expectation_amount int not null,

    created_at timestamp,
    modified_at timestamp
);
--rollback DROP TABLE company;

--changeset nmcpallas:2
create table if not exists money.expense(
    id serial primary key,
    expectation_id int references money.expectation (id) not null,
    amount int not null ,
    description varchar(25) not null ,
    creation_date date not null,

    created_at timestamp,
    modified_at timestamp
);
--rollback DROP TABLE company;