## Use to run mysql db docker image, optional if you are not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 e MYSQL_ALLOW_EMPTY_PASSWORD = yes -d mysql

#connect to my sql and run as root user
#Creation of Databases
CREATE DATABASE recipeapp_dev;
CREATE DATABASE recipeapp_prod;

#Creation of service accounts
CREATE USER 'recipeapp_dev_user'@'localhost' IDENTIFIED BY 'dts';
CREATE USER 'recipeapp_prod_user'@'localhost' IDENTIFIED BY 'dts';
#For a docker since there is its own networkstuff and we are mapping ports
CREATE USER 'recipeapp_dev_user'@'%' IDENTIFIED BY 'dts';
CREATE USER 'recipeapp_prod_user'@'%' IDENTIFIED BY 'dts';

#Database grants
GRANT SELECT ON recipeapp_dev.* to 'recipeapp_dev_user'@'localhost';
GRANT INSERT ON recipeapp_dev.* to 'recipeapp_dev_user'@'localhost';
GRANT DELETE ON recipeapp_dev.* to 'recipeapp_dev_user'@'localhost';
GRANT UPDATE ON recipeapp_dev.* to 'recipeapp_dev_user'@'localhost';
GRANT SELECT ON recipeapp_prod.* to 'recipeapp_prod_user'@'localhost';
GRANT INSERT ON recipeapp_prod.* to 'recipeapp_prod_user'@'localhost';
GRANT DELETE ON recipeapp_prod.* to 'recipeapp_prod_user'@'localhost';
GRANT UPDATE ON recipeapp_prod.* to 'recipeapp_prod_user'@'localhost';
#For a docker since there is its own networkstuff and we are mapping ports
GRANT SELECT ON recipeapp_dev.* to 'recipeapp_dev_user'@'%';
GRANT INSERT ON recipeapp_dev.* to 'recipeapp_dev_user'@'%';
GRANT DELETE ON recipeapp_dev.* to 'recipeapp_dev_user'@'%';
GRANT UPDATE ON recipeapp_dev.* to 'recipeapp_dev_user'@'%';
GRANT SELECT ON recipeapp_prod.* to 'recipeapp_prod_user'@'%';
GRANT INSERT ON recipeapp_prod.* to 'recipeapp_prod_user'@'%';
GRANT DELETE ON recipeapp_prod.* to 'recipeapp_prod_user'@'%';
GRANT UPDATE ON recipeapp_prod.* to 'recipeapp_prod_user'@'%';