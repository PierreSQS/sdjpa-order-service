DROP DATABASE IF EXISTS sdjpa_sect16_orderservice;
DROP USER IF EXISTS `orderadmin`@`%`;
DROP USER IF EXISTS `orderuser`@`%`;
CREATE DATABASE IF NOT EXISTS sdjpa_sect16_orderservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `orderadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'orderadmin';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `sdjpa_sect16_orderservice`.* TO `orderadmin`@`%`;
CREATE USER IF NOT EXISTS `orderuser`@`%` IDENTIFIED WITH mysql_native_password BY 'orderuser';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `sdjpa_sect16_orderservice`.* TO `orderuser`@`%`;
FLUSH PRIVILEGES;