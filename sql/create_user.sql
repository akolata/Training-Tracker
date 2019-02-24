use `training-tracker`;
CREATE USER 'training-tracker-app'@'localhost' IDENTIFIED BY 'password';
GRANT CREATE, DROP, INSERT, DELETE, SELECT, UPDATE  ON `training-tracker`.* TO 'training-tracker-app'@'localhost';