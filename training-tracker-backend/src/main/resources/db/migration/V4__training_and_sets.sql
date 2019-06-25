#######################################################################################################################
### TABLES
#######################################################################################################################
CREATE TABLE IF NOT EXISTS TT_TRAINING
(
    ID              BIGINT      NOT NULL AUTO_INCREMENT,
    CREATED_AT      DATETIME    NOT NULL,
    UPDATED_AT      DATETIME    NOT NULL,
    UUID            VARCHAR(36) NOT NULL,
    VERSION         DATETIME    NOT NULL,
    ADDITIONAL_INFO VARCHAR(255),
    DATE            DATE        NOT NULL,
    NAME            VARCHAR(255),
    GYM_ID          BIGINT,
    USER_ID         BIGINT,
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS TT_TRAINING_SET
(
    ID                  BIGINT      NOT NULL AUTO_INCREMENT,
    CREATED_AT          DATETIME    NOT NULL,
    UPDATED_AT          DATETIME    NOT NULL,
    UUID                VARCHAR(36) NOT NULL,
    VERSION             DATETIME    NOT NULL,
    ADDITIONAL_INFO     VARCHAR(255),
    CALORIES            INTEGER,
    DISTANCE_IN_KM      DOUBLE PRECISION,
    DURATION_IN_MINUTES INTEGER,
    REPS                INTEGER,
    WEIGHT              INTEGER,
    EXERCISE_ID         BIGINT,
    TRAINING_ID         BIGINT,
    PRIMARY KEY (ID)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

#######################################################################################################################
### INDEXES
#######################################################################################################################
ALTER TABLE TT_TRAINING
    ADD CONSTRAINT FK_TRAINING_GYM_ID
        FOREIGN KEY (GYM_ID)
            REFERENCES TT_GYM (ID);

ALTER TABLE TT_TRAINING
    ADD CONSTRAINT FK_TRAINING_USER_ID
        FOREIGN KEY (USER_ID)
            REFERENCES TT_USER (ID);

ALTER TABLE TT_TRAINING_SET
    add constraint FK_TRAINING_SET_EXERCISE_ID
        FOREIGN KEY (EXERCISE_ID)
            REFERENCES TT_EXERCISE (ID);

ALTER TABLE TT_TRAINING_SET
    ADD CONSTRAINT FK_TRAINING_SET_TRAINING_ID
        FOREIGN KEY (TRAINING_ID)
            REFERENCES TT_TRAINING (ID);