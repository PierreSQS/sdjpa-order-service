alter table order_header add column customer_id bigint;
alter table order_header drop column customer;
alter table order_header add constraint order_header_customer_fk
foreign key (customer_id) references customer(id);