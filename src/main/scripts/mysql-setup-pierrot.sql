DROP DATABASE IF EXISTS jt_orderservice_db;
DROP USER IF EXISTS `orderadmin`@`%`;
DROP USER IF EXISTS `orderuser`@`%`;
CREATE DATABASE IF NOT EXISTS jt_orderservice_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `orderadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'orderadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `jt_orderservice_db`.* TO `orderadmin`@`%`;
CREATE USER IF NOT EXISTS `orderuser`@`%` IDENTIFIED WITH mysql_native_password BY 'orderuser';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `jt_orderservice_db`.* TO `orderuser`@`%`;
FLUSH PRIVILEGES;