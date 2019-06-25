#######################################################################################################################
### TABLES
#######################################################################################################################
CREATE TABLE IF NOT EXISTS TT_ROLE
(
    ID         BIGINT       NOT NULL AUTO_INCREMENT,
    CREATED_AT DATETIME     NOT NULL,
    UPDATED_AT DATETIME     NOT NULL,
    UUID       VARCHAR(36)  NOT NULL,
    VERSION    DATETIME     NOT NULL,
    NAME       VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS TT_USER
(
    ID                  BIGINT       NOT NULL AUTO_INCREMENT,
    CREATED_AT          DATETIME     NOT NULL,
    UPDATED_AT          DATETIME     NOT NULL,
    UUID                VARCHAR(36)  NOT NULL,
    VERSION             DATETIME     NOT NULL,
    EMAIL               VARCHAR(255) NOT NULL,
    FIRST_NAME          VARCHAR(255) NOT NULL,
    LAST_NAME           VARCHAR(255) NOT NULL,
    PASSWORD            VARCHAR(255) NOT NULL,
    ACCOUNT_EXPIRED     BIT(1)       NOT NULL,
    ACCOUNT_LOCKED      BIT(1)       NOT NULL,
    CREDENTIALS_EXPIRED BIT(1)       NOT NULL,
    ENABLED             BIT(1)       NOT NULL,
    USERNAME            VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS TT_USER_ROLE
(
    USER_ID BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,
    PRIMARY KEY (USER_ID, ROLE_ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

ALTER TABLE TT_USER_ROLE
    ADD CONSTRAINT FK_ROLE_USER
        FOREIGN KEY (ROLE_ID)
            REFERENCES TT_ROLE (ID);

ALTER TABLE TT_USER_ROLE
    ADD CONSTRAINT FK_USER_ROLE
        FOREIGN KEY (USER_ID)
            REFERENCES TT_USER (ID);

#######################################################################################################################
### INDEXES
#######################################################################################################################
CREATE UNIQUE INDEX UK_USER_EMAIL ON TT_USER (EMAIL);
CREATE UNIQUE INDEX UK_USER_USERNAME ON TT_USER (USERNAME);
CREATE UNIQUE INDEX UK_ROLE_NAME ON TT_ROLE(NAME);

#######################################################################################################################
### INSERTS
#######################################################################################################################
INSERT INTO TT_ROLE
VALUES (DEFAULT, NOW(), NOW(), MD5(UUID()), NOW(), 'ROLE_USER');
