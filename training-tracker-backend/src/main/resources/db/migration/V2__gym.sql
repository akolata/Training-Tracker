#######################################################################################################################
### TABLES
#######################################################################################################################
CREATE TABLE IF NOT EXISTS TT_GYM
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

#######################################################################################################################
### INDEXES
#######################################################################################################################
CREATE UNIQUE INDEX UK_GYM_NAME on TT_GYM (NAME);