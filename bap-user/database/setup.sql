CREATE TABLE Users (
    id VARCHAR(255) PRIMARY KEY NOT NULL,
    dotaid VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    showdotamatches boolean
);