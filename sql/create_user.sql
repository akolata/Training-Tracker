use `training-tracker`;
CREATE USER 'training-tracker-app'@'localhost' IDENTIFIED BY 'password';
GRANT CREATE, DROP, INSERT, DELETE, SELECT, UPDATE, ALTER, REFERENCES, INDEX  ON `training-tracker`.* TO 'training-tracker-app'@'localhost';

INSERT INTO `training-tracker`.roles(created_at, updated_at, version, name) VALUES
(now(), now(), 1, 'ROLE_USER'),
(now(), now(), 1, 'ROLE_ADMIN');