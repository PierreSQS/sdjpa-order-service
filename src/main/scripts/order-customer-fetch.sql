############ EAGER LOADING ############
select
        orderheade0_.id                 as id1_3_0_      ,
        orderheade0_.created_date       as created_2_3_0_,
        orderheade0_.last_modified_date as last_mod3_3_0_,
        orderheade0_.bill_to_address    as bill_to_4_3_0_,
        orderheade0_.bill_to_city       as bill_to_5_3_0_,
        orderheade0_.bill_to_state      as bill_to_6_3_0_,
        orderheade0_.bill_to_zip_code   as bill_to_7_3_0_,
        orderheade0_.customer_id        as custome13_3_0_,
        orderheade0_.order_status       as order_st8_3_0_,
        orderheade0_.shipping_address   as shipping9_3_0_,
        orderheade0_.shipping_city      as shippin10_3_0_,
        orderheade0_.shipping_state     as shippin11_3_0_,
        orderheade0_.shipping_zip_code  as shippin12_3_0_,
        customer1_.id                   as id1_1_1_      ,
        customer1_.created_date         as created_2_1_1_,
        customer1_.last_modified_date   as last_mod3_1_1_,
        customer1_.address              as address4_1_1_ ,
        customer1_.city                 as city5_1_1_    ,
        customer1_.state                as state6_1_1_   ,
        customer1_.zip_code             as zip_code7_1_1_,
        customer1_.customer_name        as customer8_1_1_,
        customer1_.email                as email9_1_1_   ,
        customer1_.phone                as phone10_1_1_  ,
        orderappro2_.id                 as id1_2_2_      ,
        orderappro2_.created_date       as created_2_2_2_,
        orderappro2_.last_modified_date as last_mod3_2_2_,
        orderappro2_.approved_by        as approved4_2_2_,
        orderappro2_.order_header_id    as order_he5_2_2_
from
        order_header orderheade0_
left outer join
        customer customer1_
on
        orderheade0_.customer_id=customer1_.id
left outer join
        order_approval orderappro2_
on
        orderheade0_.id=orderappro2_.order_header_id
where
        orderheade0_.id=?select orderheade0_.id as id1_3_0_      ,
        orderheade0_.created_date               as created_2_3_0_,
        orderheade0_.last_modified_date         as last_mod3_3_0_,
        orderheade0_.bill_to_address            as bill_to_4_3_0_,
        orderheade0_.bill_to_city               as bill_to_5_3_0_,
        orderheade0_.bill_to_state              as bill_to_6_3_0_,
        orderheade0_.bill_to_zip_code           as bill_to_7_3_0_,
        orderheade0_.customer_id                as custome13_3_0_,
        orderheade0_.order_status               as order_st8_3_0_,
        orderheade0_.shipping_address           as shipping9_3_0_,
        orderheade0_.shipping_city              as shippin10_3_0_,
        orderheade0_.shipping_state             as shippin11_3_0_,
        orderheade0_.shipping_zip_code          as shippin12_3_0_,
        customer1_.id                           as id1_1_1_      ,
        customer1_.created_date                 as created_2_1_1_,
        customer1_.last_modified_date           as last_mod3_1_1_,
        customer1_.address                      as address4_1_1_ ,
        customer1_.city                         as city5_1_1_    ,
        customer1_.state                        as state6_1_1_   ,
        customer1_.zip_code                     as zip_code7_1_1_,
        customer1_.customer_name                as customer8_1_1_,
        customer1_.email                        as email9_1_1_   ,
        customer1_.phone                        as phone10_1_1_  ,
        orderappro2_.id                         as id1_2_2_      ,
        orderappro2_.created_date               as created_2_2_2_,
        orderappro2_.last_modified_date         as last_mod3_2_2_,
        orderappro2_.approved_by                as approved4_2_2_,
        orderappro2_.order_header_id            as order_he5_2_2_
from
        order_header orderheade0_
left outer join
        customer customer1_
on
        orderheade0_.customer_id=customer1_.id
left outer join
        order_approval orderappro2_
on
        orderheade0_.id=orderappro2_.order_header_id
where
        orderheade0_.id=?