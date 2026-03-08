select current_database(), current_user, current_schema(), inet_server_addr(), inet_server_port();
select table_schema, table_name
from information_schema.tables
where table_schema='public'
order by table_name;


SELECT * FROM book;

SELECT * FROM customer;