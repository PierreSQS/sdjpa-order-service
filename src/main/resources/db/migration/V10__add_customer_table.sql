create table customer (
      id bigint not null auto_increment primary key,
      contact_info varchar(50),
      created_date timestamp,
      last_modified_date timestamp
);