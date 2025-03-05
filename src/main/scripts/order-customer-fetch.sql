-- EAGER FETCHING
select
        oh1_0.id                ,
        oh1_0.bill_to_address   ,
        oh1_0.bill_to_city      ,
        oh1_0.bill_to_state     ,
        oh1_0.bill_to_zip_code  ,
        oh1_0.created_date      ,
        c1_0.id                 ,
        c1_0.address            ,
        c1_0.city               ,
        c1_0.state              ,
        c1_0.zip_code           ,
        c1_0.created_date       ,
        c1_0.customer_name      ,
        c1_0.email              ,
        c1_0.last_modified_date ,
        c1_0.phone              ,
        oh1_0.last_modified_date,
        oa1_0.id                ,
        oa1_0.approved_by       ,
        oa1_0.created_date      ,
        oa1_0.last_modified_date,
        oh1_0.order_status      ,
        oh1_0.shipping_address  ,
        oh1_0.shipping_city     ,
        oh1_0.shipping_state    ,
        oh1_0.shipping_zip_code
from
        order_header oh1_0
left join
        customer c1_0
on
        c1_0.id=oh1_0.customer_id
left join
        order_approval oa1_0
on
        oh1_0.id=oa1_0.order_header_id
where
        oh1_0.id=?

-- LAZY FETCHING
select
        oh1_0.id                ,
        oh1_0.bill_to_address   ,
        oh1_0.bill_to_city      ,
        oh1_0.bill_to_state     ,
        oh1_0.bill_to_zip_code  ,
        oh1_0.created_date      ,
        oh1_0.customer_id       ,
        oh1_0.last_modified_date,
        oa1_0.id                ,
        oa1_0.approved_by       ,
        oa1_0.created_date      ,
        oa1_0.last_modified_date,
        oh1_0.order_status      ,
        oh1_0.shipping_address  ,
        oh1_0.shipping_city     ,
        oh1_0.shipping_state    ,
        oh1_0.shipping_zip_code
from
        order_header oh1_0 left join order_approval oa1_0
on
        oh1_0.id=oa1_0.order_header_id
where
        oh1_0.id=?

select
        c1_0.id                ,
        c1_0.address           ,
        c1_0.city              ,
        c1_0.state             ,
        c1_0.zip_code          ,
        c1_0.created_date      ,
        c1_0.customer_name     ,
        c1_0.email             ,
        c1_0.last_modified_date,
        c1_0.phone
from
        customer c1_0
where
        c1_0.id=?

