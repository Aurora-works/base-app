use base_app_db;
-- drop table
set foreign_key_checks = 0;
drop table if exists t_system_user;
drop table if exists t_system_role;
drop table if exists t_system_user_role;
drop table if exists t_system_menu;
drop table if exists t_system_role_menu;
drop table if exists t_system_request_log;
drop table if exists t_system_error_log;
drop table if exists t_system_dict;
drop table if exists t_system_param;
set foreign_key_checks = 1;

-- create table
create table t_system_user (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    username varchar(50) not null unique,
    password varchar(64),
    salt varchar(32),
    nickname varchar(50) not null,
    sex varchar(20),
    email varchar(50),
    mobile varchar(11) unique,
    avatar varchar(255),
    status varchar(20) not null,
    user_type varchar(20) not null,
    is_deleted varchar(20) not null
);

create table t_system_role (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    role_code varchar(50) not null unique,
    role_name varchar(50) not null,
    parent_id bigint,
    order_by varchar(50) not null,
    status varchar(20) not null,
    foreign key (parent_id) references t_system_role(id)
);

create table t_system_user_role (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    user_id bigint not null,
    role_id bigint not null,
    foreign key (user_id) references t_system_user(id),
    foreign key (role_id) references t_system_role(id),
    unique (user_id, role_id)
);

create table t_system_menu (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    menu_code varchar(50) not null unique,
    menu_name varchar(50) not null,
    href varchar(255),
    parent_id bigint,
    order_by varchar(20) not null,
    css varchar(255),
    is_open varchar(20) not null,
    status varchar(20) not null,
    foreign key (parent_id) references t_system_menu(id)
);

create table t_system_role_menu (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    create_op varchar(20) not null,
    update_op varchar(20) not null,
    delete_op varchar(20) not null,
    read_op varchar(20) not null,
    role_id bigint not null,
    menu_id bigint not null,
    foreign key (role_id) references t_system_role(id),
    foreign key (menu_id) references t_system_menu(id),
    unique (role_id, menu_id)
);

create table t_system_request_log (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    request_controller varchar(255),
    request_url varchar(255),
    request_method varchar(10),
    request_ip varchar(50),
    request_params text
);

create table t_system_error_log (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    error_name varchar(255),
    error_stack_trace text,
    error_controller varchar(255),
    request_url varchar(255),
    request_method varchar(10),
    request_ip varchar(50),
    request_params text
);

create table t_system_dict (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    dict_code varchar(50) not null,
    dict_key varchar(20) not null,
    dict_value varchar(50) not null,
    order_by varchar(20) not null,
    status varchar(20) not null,
    unique (dict_code, dict_key)
);

create table t_system_param (
    id bigint not null primary key auto_increment,
    description varchar(255),
    create_time datetime(3) not null,
    create_user_id bigint not null,
    last_time datetime(3),
    last_user_id bigint,
    version integer,
    foreign key (create_user_id) references t_system_user(id),
    foreign key (last_user_id) references t_system_user(id),
    param_code varchar(50) not null unique,
    param_desc varchar(50) not null unique,
    param_value varchar(255) not null
);