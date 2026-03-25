create table if not exists users (
    id bigserial primary key,
    email varchar(255) not null unique,
    password_hash varchar(255) not null,
    name varchar(100) not null,
    created_at timestamptz not null default now()
);

create table if not exists posts (
    id bigserial primary key,
    user_id bigint not null references users(id),
    title varchar(200) not null,
    content text not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);