DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

COMMENT ON SCHEMA public IS ''standard public schema'';

SET default_with_oids = false;

CREATE TABLE public.country_dictionary (
    id bigint NOT NULL,
    data_created_at timestamp without time zone,
    data_updated_at timestamp without time zone,
    is_active boolean NOT NULL,
    code character varying(255),
    definition character varying(255),
    name character varying(255)
);

CREATE SEQUENCE public.country_dictionary_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.currency_dictionary (
    id bigint NOT NULL,
    data_created_at timestamp without time zone,
    data_updated_at timestamp without time zone,
    is_active boolean NOT NULL,
    code character varying(255),
    definition character varying(255),
    name character varying(255)
);

CREATE SEQUENCE public.currency_dictionary_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.football_player (
    id bigint NOT NULL,
    data_created_at timestamp without time zone,
    data_updated_at timestamp without time zone,
    is_active boolean NOT NULL,
    birth_date date,
    height bigint,
    name character varying(255),
    sur_name character varying(255),
    weight bigint,
    country_dictionary_id bigint,
    football_team_id bigint,
    surname character varying(255),
    unique_football_player_identifier character varying(255)
);

CREATE TABLE public.football_player_football_team_history (
    id bigint NOT NULL,
    data_created_at timestamp without time zone,
    data_updated_at timestamp without time zone,
    is_active boolean NOT NULL,
    exit_date date,
    join_date date,
    team_commission numeric(19,2),
    transfer_fee numeric(19,2),
    football_player_id bigint,
    football_team_id bigint,
    contract_price numeric(19,2)
);

CREATE SEQUENCE public.football_player_football_team_history_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.football_player_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.football_team (
    id bigint NOT NULL,
    data_created_at timestamp without time zone,
    data_updated_at timestamp without time zone,
    is_active boolean NOT NULL,
    create_date date,
    name character varying(255),
    country_dictionary_id bigint,
    currency_dictionary_id bigint,
    unique_football_team_identifier character varying(255)
);

CREATE SEQUENCE public.football_team_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.id_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

INSERT INTO public.country_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (1, NULL, NULL, true, ''TURKEY'', ''Turkey'', ''TR'');
INSERT INTO public.country_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (2, NULL, NULL, true, ''AMERICA'', ''America'', ''USA'');
INSERT INTO public.country_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (3, NULL, NULL, true, ''ENGLAND'', ''England'', ''EN'');

INSERT INTO public.currency_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (1, NULL, NULL, true, ''TURKISH_LIRA'', ''Turkish Lira'', ''TL'');
INSERT INTO public.currency_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (2, NULL, NULL, true, ''AMERICAN_DOLLAR'', ''American Dollar'', ''USD'');
INSERT INTO public.currency_dictionary (id, data_created_at, data_updated_at, is_active, code, definition, name) VALUES (3, NULL, NULL, true, ''BRITISH_STERLIN'', ''British Sterlin'', ''GBP'');

INSERT INTO public.football_player (id, data_created_at, data_updated_at, is_active, birth_date, height, name, sur_name, weight, country_dictionary_id, football_team_id, surname, unique_football_player_identifier) VALUES (1, ''2020-04-13 18:18:10.108'', ''2020-04-13 19:03:30.37'', true, ''2000-01-01'', 190, ''ilknur'', NULL, 70, 1, 1, ''bisirici'', ''01'');
INSERT INTO public.football_player (id, data_created_at, data_updated_at, is_active, birth_date, height, name, sur_name, weight, country_dictionary_id, football_team_id, surname, unique_football_player_identifier) VALUES (2, ''2020-04-14 20:30:09.617'', ''2020-04-14 20:30:09.617'', true, ''1990-02-02'', 185, ''mehmet'', NULL, 99, 1, 53, ''topcuoglu'', ''02'');
INSERT INTO public.football_player (id, data_created_at, data_updated_at, is_active, birth_date, height, name, sur_name, weight, country_dictionary_id, football_team_id, surname, unique_football_player_identifier) VALUES (3, ''2020-04-14 20:36:06.402'', ''2020-04-14 20:38:08.575'', true, ''1980-02-02'', 185, ''fatih'', NULL, 99, 1, 103, ''topuz'', ''03'');
INSERT INTO public.football_player (id, data_created_at, data_updated_at, is_active, birth_date, height, name, sur_name, weight, country_dictionary_id, football_team_id, surname, unique_football_player_identifier) VALUES (4, ''2020-04-14 20:38:57.174'', ''2020-04-14 20:38:57.174'', true, ''1980-02-02'', 185, ''kerem'', NULL, 99, 1, 53, ''topal'', ''04'');


INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (3, ''2020-04-13 19:03:14.439'', ''2020-04-13 19:03:14.439'', true, NULL, ''2020-02-02'', 13.50, 135.00, 1, 1, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (1, ''2020-04-13 18:18:10.159'', ''2020-04-13 19:03:30.369'', true, ''2020-02-02'', ''2018-01-01'', 10.00, 100.00, 1, 103, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (4, ''2020-04-14 20:30:09.668'', ''2020-04-14 20:30:09.668'', true, NULL, ''2010-03-03'', 50.00, 500.00, 2, 1, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (5, ''2020-04-14 20:30:09.696'', ''2020-04-14 20:30:09.696'', true, NULL, ''2014-03-03'', 250.00, 2500.00, 2, 53, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (6, ''2020-04-14 20:36:06.413'', ''2020-04-14 20:36:06.413'', true, NULL, ''2005-03-03'', 50.00, 500.00, 2, 52, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (7, ''2020-04-14 20:36:06.424'', ''2020-04-14 20:36:06.424'', true, NULL, ''2015-03-03'', 250.00, 2500.00, 2, 54, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (8, ''2020-04-14 20:38:08.585'', ''2020-04-14 20:38:08.585'', true, NULL, ''2000-03-03'', 50.00, 500.00, 2, 52, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (9, ''2020-04-14 20:38:08.595'', ''2020-04-14 20:38:08.595'', true, NULL, ''2010-03-03'', 250.00, 2500.00, 2, 54, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (10, ''2020-04-14 20:38:08.602'', ''2020-04-14 20:38:08.602'', true, NULL, ''2016-03-03'', 250.00, 2500.00, 2, 103, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (11, ''2020-04-14 20:38:57.187'', ''2020-04-14 20:38:57.187'', true, NULL, ''2000-03-03'', 50.00, 500.00, 2, 1, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (12, ''2020-04-14 20:38:57.195'', ''2020-04-14 20:38:57.195'', true, NULL, ''2010-03-03'', 250.00, 2500.00, 2, 52, NULL);
INSERT INTO public.football_player_football_team_history (id, data_created_at, data_updated_at, is_active, exit_date, join_date, team_commission, transfer_fee, football_player_id, football_team_id, contract_price) VALUES (13, ''2020-04-14 20:38:57.201'', ''2020-04-14 20:38:57.201'', true, NULL, ''2016-03-03'', 250.00, 2500.00, 2, 53, NULL);


INSERT INTO public.football_team (id, data_created_at, data_updated_at, is_active, create_date, name, country_dictionary_id, currency_dictionary_id, unique_football_team_identifier) VALUES (103, ''2020-04-13 12:33:28.361'', ''2020-04-13 12:48:38.76'', true, ''1955-04-23'', ''Adana Spor'', 3, 1, ''05'');
INSERT INTO public.football_team (id, data_created_at, data_updated_at, is_active, create_date, name, country_dictionary_id, currency_dictionary_id, unique_football_team_identifier) VALUES (1, ''2020-04-13 10:40:27.644'', ''2020-04-13 10:40:27.644'', true, ''1993-04-28'', ''Galatasaray'', 1, 1, ''01'');
INSERT INTO public.football_team (id, data_created_at, data_updated_at, is_active, create_date, name, country_dictionary_id, currency_dictionary_id, unique_football_team_identifier) VALUES (52, ''2020-04-13 10:58:12.402'', ''2020-04-13 10:58:12.402'', true, ''1992-04-28'', ''Fenerbahce'', 1, 1, ''02'');
INSERT INTO public.football_team (id, data_created_at, data_updated_at, is_active, create_date, name, country_dictionary_id, currency_dictionary_id, unique_football_team_identifier) VALUES (53, ''2020-04-13 11:04:02.71'', ''2020-04-13 11:04:02.71'', true, ''1993-04-25'', ''Besiktas'', 1, 1, ''03'');
INSERT INTO public.football_team (id, data_created_at, data_updated_at, is_active, create_date, name, country_dictionary_id, currency_dictionary_id, unique_football_team_identifier) VALUES (54, ''2020-04-13 11:27:58.619'', ''2020-04-13 11:27:58.619'', true, ''1993-04-23'', ''Trabzon'', 1, 1, ''04'');

SELECT pg_catalog.setval(''public.country_dictionary_sequence'', 1, false);

SELECT pg_catalog.setval(''public.currency_dictionary_sequence'', 1, false);

SELECT pg_catalog.setval(''public.football_player_football_team_history_sequence'', 13, true);

SELECT pg_catalog.setval(''public.football_player_sequence'', 4, true);

SELECT pg_catalog.setval(''public.football_team_sequence'', 151, true);

SELECT pg_catalog.setval(''public.id_sequence'', 1, false);

ALTER TABLE ONLY public.country_dictionary
    ADD CONSTRAINT country_dictionary_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.currency_dictionary
    ADD CONSTRAINT currency_dictionary_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.football_player_football_team_history
    ADD CONSTRAINT football_player_football_team_history_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.football_player
    ADD CONSTRAINT football_player_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY public.football_team
    ADD CONSTRAINT football_team_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY public.football_player_football_team_history
    ADD CONSTRAINT fk2hb0cxga5x79sooi1s2pw1k1j FOREIGN KEY (football_player_id) REFERENCES public.football_player(id);

ALTER TABLE ONLY public.football_player
    ADD CONSTRAINT fk49o3datpimqs0ax8ydqijtxt8 FOREIGN KEY (football_team_id) REFERENCES public.football_team(id);

ALTER TABLE ONLY public.football_player_football_team_history
    ADD CONSTRAINT fk4tws2k7ke4pyxhvm9uefi3yp3 FOREIGN KEY (football_team_id) REFERENCES public.football_team(id);

ALTER TABLE ONLY public.football_team
    ADD CONSTRAINT fkbq4ilbikwc6pdw6m5pbw2x3r8 FOREIGN KEY (currency_dictionary_id) REFERENCES public.currency_dictionary(id);

ALTER TABLE ONLY public.football_player
    ADD CONSTRAINT fkepe32g831mq8yt5vk534uva3t FOREIGN KEY (country_dictionary_id) REFERENCES public.country_dictionary(id);

ALTER TABLE ONLY public.football_team
    ADD CONSTRAINT fkm5fao0ft5uv7o6w6xifddqeby FOREIGN KEY (country_dictionary_id) REFERENCES public.country_dictionary(id);
