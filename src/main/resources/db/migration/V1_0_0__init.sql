CREATE TABLE address
(
    id       bigserial NOT NULL,
    country  varchar(255),
    region   varchar(255),
    city     varchar(255),
    street   varchar(255),
    house    varchar(255),
    flat     varchar(255),
    created  timestamp,
    modified timestamp,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE customer
(
    id                   bigserial    NOT NULL,
    registred_address_id bigint       NOT NULL,
    actual_address_id    bigint       NOT NULL,
    first_name           varchar(255) NULL,
    last_name            varchar(255) NULL,
    middle_name          varchar(255) NULL,
    sex                  varchar(6)   NOT NULL,
    CONSTRAINT ck_customer_sex CHECK (((sex)::text = ANY
                                       ((ARRAY ['male'::character varying, 'female'::character varying])::text[]))),
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT fk_registred_address_id FOREIGN KEY (registred_address_id) REFERENCES address (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_actual_address_id FOREIGN KEY (actual_address_id) REFERENCES address (id) ON UPDATE RESTRICT ON DELETE RESTRICT
);

INSERT INTO address(country, region, city, street, house, flat, created, modified) VALUES
('Россия', 'Новосибирская область', 'Новосибирск', 'Советская', '53','2','2015-05-30 10:00:00', '2018-11-23 16:54:11'),
('Россия', 'Новосибирская область', 'Новосибирск', 'Мичурина', '9','1','2018-02-28 09:15:25', null),
('Россия', 'Новосибирская область', 'Новосибирск', 'Ядринцевская', '27','9','2010-01-25 11:30:00', '2015-01-23 16:00:00'),
('Россия', 'Новосибирская область', 'Бердск', 'Лунная', '14','4','1991-05-21 18:01:21', null),
('Россия', 'Новосибирская область', 'Бердск', 'Первомайская', '127','5','2011-11-11 11:11:11', '2012-12-12 12:12:12'),
('Россия', 'Крассноярский кроай', 'Крассноярск', 'Ленина', '13','33','2000-01-01 10:00:00', '2015-10-25 11:54:01'),
('Россия', 'Крассноярский край', 'Ачинск', 'Труда', '1','1','1999-07-11 14:25:00', null),
('Россия', null, 'Москва', 'Петровка', '38', null,'1956-01-30 08:00:00', '2000-08-11 23:00:56');

INSERT INTO customer(registred_address_id, actual_address_id, first_name, last_name, middle_name, sex) VALUES
(1,1,'Иванов', 'Павел', 'Сергеевич','male'),
(2,3,'Петров', 'Иван', null,'male'),
(4,5,'Рылова', 'Светлана', 'Николаевна','female'),
(6,6,'Антонова', 'Алла', 'Викторовна','female'),
(7,8,'Головин', 'Александр', null,'male');
