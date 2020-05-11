insert into USER (FIRST_NAME, LAST_NAME, AGE, SALARY, USERNAME, PASSWORD) values('Devglan', 'Devglan', 34, 12345, 'devglan', '$2a$10$vG9uv8bsmcgrDRDW.MCAJe1TRhbKCBy6aAu44oVV2IzZNfUo28acS');

insert into ODBERATEL (FIRMA, IC, DIC, PSC, MESTO, ULICE, CISLO_POPISNE)
values ('Tomas Vacek', '12345678', 'CZ12345678', '12345', 'Pardubice', '5. Května', '15'),
('David Mison', '12345677', 'CZ12345677', '12345', 'Pelhrimov', 'Prazska', '30');

insert into FAKTURA (EVIDENCNI_CISLO, VARIABILNI_SYMBOL, DATUM_VYSTAVENI, DATUM_SPLATNOSTI, DATUM_UZP, ODBERATEL_ID, CENA_CELKEM)
values ('120', '120', TO_DATE('1/1/2020', 'DD/MM/YYYY'),TO_DATE('15/1/2020', 'DD/MM/YYYY'), TO_DATE('15/1/2020', 'DD/MM/YYYY'), 1 , 20000),
('220', '220', TO_DATE('1/2/2020', 'DD/MM/YYYY'),TO_DATE('15/2/2020', 'DD/MM/YYYY'), TO_DATE('15/2/2020', 'DD/MM/YYYY'), 2 , 30000),
('320', '320', TO_DATE('1/3/2020', 'DD/MM/YYYY'),TO_DATE('15/3/2020', 'DD/MM/YYYY'), TO_DATE('15/3/2020', 'DD/MM/YYYY'), 2 , 40000);

insert into POLOZKA_FAKTURY (POPIS, CENA_ZA_JEDNOTKU, JEDNOTKA, MNOZSTVI, CENA_CELKEM, FAKTURA_ID)
values('Materiál', null, null, null, 12000, 1),
('Práce', 200, 'h', 40, 8000, 1),
('Materiál', null, null, null, 20000, 2),
('Práce', 200, 'h', 50, 1000, 2),
('Materiál', null, null, null, 20000, 3),
('Práce', 200, 'h', 100, 20000, 3);
