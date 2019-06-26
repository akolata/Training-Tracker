use `training-tracker`;
CREATE USER 'training-tracker-app'@'localhost' IDENTIFIED BY 'password';
GRANT CREATE, DROP, INSERT, DELETE, SELECT, UPDATE, ALTER, REFERENCES, INDEX  ON `training-tracker`.* TO 'training-tracker-app'@'localhost';