create table USER
(
    ID         INTEGER auto_increment primary key,
    AGE        INTEGER,
    FIRST_NAME VARCHAR(255),
    LAST_NAME  VARCHAR(255),
    PASSWORD   VARCHAR(255),
    SALARY     BIGINT,
    USERNAME   VARCHAR(255)
);
create table ODBERATEL
(
    ID            INTEGER auto_increment primary key,
    CISLO_POPISNE VARCHAR(25)  not null,
    DIC           VARCHAR(12),
    FIRMA         VARCHAR(200) not null,
    IC            VARCHAR(8),
    MESTO         VARCHAR(150) not null,
    PSC           VARCHAR(10)  not null,
    ULICE         VARCHAR(100) not null
);
create table FAKTURA
(
    ID                INTEGER auto_increment primary key,
    CENA_CELKEM       DOUBLE      not null,
    DATUM_SPLATNOSTI  DATE        not null,
    DATUM_UZP         DATE,
    DATUM_VYSTAVENI   DATE        not null,
    EVIDENCNI_CISLO   VARCHAR(30) not null,
    VARIABILNI_SYMBOL INTEGER,
    ODBERATEL_ID      INTEGER,
    constraint FKEE2B5MMD8PUJMIRIWIKG2QRC2
        foreign key (ODBERATEL_ID) references ODBERATEL (ID)
);

create table POLOZKA_FAKTURY
(
    ID               INTEGER auto_increment primary key,
    CENA_CELKEM      DOUBLE      not null,
    CENA_ZA_JEDNOTKU DOUBLE,
    JEDNOTKA         VARCHAR(10),
    MNOZSTVI         DOUBLE,
    POPIS            VARCHAR(10) not null,
    FAKTURA_ID       INTEGER     not null,
    constraint FK4OR59K8ECGUVQ48M5QGE3XDKF
        foreign key (FAKTURA_ID) references FAKTURA (ID)
);
