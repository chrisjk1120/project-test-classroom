# Setup
## Database setup
Run the following commands in a terminal
MYSQL > CREATE DATABASE classrooms;
MYSQL > CREATE USER 'classrooms'@'%' IDENTIFIED BY 'MySecretPassword';
MYSQL > GRANT ALL PRIVILEGES ON classroom.* TO 'classrooms'@'%';
MYSQL > FLUSH PRIVILEGES;

From terminal run mysql -u root -pMySecretPassword < masterdata.sql

in /src/main/java/storage find DbConfig and change it to your settings.

