PGDMP                 	        z            Billing    14.2    14.2 .               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                        1262    41447    Billing    DATABASE     m   CREATE DATABASE "Billing" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1256';
    DROP DATABASE "Billing";
                postgres    false                        2615    41448    bscs    SCHEMA        CREATE SCHEMA bscs;
    DROP SCHEMA bscs;
                postgres    false                        2615    41449    rtx    SCHEMA        CREATE SCHEMA rtx;
    DROP SCHEMA rtx;
                postgres    false            !           0    0 
   SCHEMA rtx    COMMENT     3   COMMENT ON SCHEMA rtx IS 'standard public schema';
                   postgres    false    5            �            1259    41450    contract    TABLE     q  CREATE TABLE bscs.contract (
    contract_id integer NOT NULL,
    msisdn character varying(15),
    rateplane_id integer,
    userid integer,
    current_voice integer,
    current_cross_voice integer,
    current_data integer,
    current_sms integer,
    current_roaming integer,
    discount integer,
    additional_sp integer,
    current_additional_sp integer
);
    DROP TABLE bscs.contract;
       bscs         heap    postgres    false    4            �            1259    41453    contract_contract_id_seq    SEQUENCE     �   CREATE SEQUENCE bscs.contract_contract_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE bscs.contract_contract_id_seq;
       bscs          postgres    false    211    4            "           0    0    contract_contract_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE bscs.contract_contract_id_seq OWNED BY bscs.contract.contract_id;
          bscs          postgres    false    212            �            1259    41454 
   rateplanes    TABLE     �  CREATE TABLE bscs.rateplanes (
    id integer NOT NULL,
    commercial_name character varying,
    voice_service integer,
    cross_voice_service integer,
    data_service integer,
    sms_service integer,
    roaming_service integer,
    additional_minutes_service integer,
    additional_sms_service integer,
    additional_data_service integer,
    additional_roaming_service integer,
    fee integer
);
    DROP TABLE bscs.rateplanes;
       bscs         heap    postgres    false    4            �            1259    41459    rateplanes_id_seq    SEQUENCE     �   CREATE SEQUENCE bscs.rateplanes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE bscs.rateplanes_id_seq;
       bscs          postgres    false    213    4            #           0    0    rateplanes_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE bscs.rateplanes_id_seq OWNED BY bscs.rateplanes.id;
          bscs          postgres    false    214            �            1259    41460    service_package    TABLE     v   CREATE TABLE bscs.service_package (
    id integer NOT NULL,
    service_type character varying,
    units integer
);
 !   DROP TABLE bscs.service_package;
       bscs         heap    postgres    false    4            �            1259    41465    service_package_id_seq    SEQUENCE     �   CREATE SEQUENCE bscs.service_package_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE bscs.service_package_id_seq;
       bscs          postgres    false    215    4            $           0    0    service_package_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE bscs.service_package_id_seq OWNED BY bscs.service_package.id;
          bscs          postgres    false    216            �            1259    41466    users    TABLE     �   CREATE TABLE bscs.users (
    national_id integer NOT NULL,
    u_name character varying NOT NULL,
    age integer NOT NULL,
    address character varying
);
    DROP TABLE bscs.users;
       bscs         heap    postgres    false    4            �            1259    41471    consumption    TABLE       CREATE TABLE rtx.consumption (
    id integer NOT NULL,
    source_msisdn character varying(15),
    terminated_msisdn character varying,
    time_stamp character varying,
    duration integer,
    rate integer,
    service_id integer,
    rateplane_id integer
);
    DROP TABLE rtx.consumption;
       rtx         heap    postgres    false    5            �            1259    41476    consumption_id_seq    SEQUENCE     �   CREATE SEQUENCE rtx.consumption_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE rtx.consumption_id_seq;
       rtx          postgres    false    218    5            %           0    0    consumption_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE rtx.consumption_id_seq OWNED BY rtx.consumption.id;
          rtx          postgres    false    219            q           2604    41477    contract contract_id    DEFAULT     x   ALTER TABLE ONLY bscs.contract ALTER COLUMN contract_id SET DEFAULT nextval('bscs.contract_contract_id_seq'::regclass);
 A   ALTER TABLE bscs.contract ALTER COLUMN contract_id DROP DEFAULT;
       bscs          postgres    false    212    211            r           2604    41478    rateplanes id    DEFAULT     j   ALTER TABLE ONLY bscs.rateplanes ALTER COLUMN id SET DEFAULT nextval('bscs.rateplanes_id_seq'::regclass);
 :   ALTER TABLE bscs.rateplanes ALTER COLUMN id DROP DEFAULT;
       bscs          postgres    false    214    213            s           2604    41479    service_package id    DEFAULT     t   ALTER TABLE ONLY bscs.service_package ALTER COLUMN id SET DEFAULT nextval('bscs.service_package_id_seq'::regclass);
 ?   ALTER TABLE bscs.service_package ALTER COLUMN id DROP DEFAULT;
       bscs          postgres    false    216    215            t           2604    41480    consumption id    DEFAULT     j   ALTER TABLE ONLY rtx.consumption ALTER COLUMN id SET DEFAULT nextval('rtx.consumption_id_seq'::regclass);
 :   ALTER TABLE rtx.consumption ALTER COLUMN id DROP DEFAULT;
       rtx          postgres    false    219    218                      0    41450    contract 
   TABLE DATA           �   COPY bscs.contract (contract_id, msisdn, rateplane_id, userid, current_voice, current_cross_voice, current_data, current_sms, current_roaming, discount, additional_sp, current_additional_sp) FROM stdin;
    bscs          postgres    false    211   ?7                 0    41454 
   rateplanes 
   TABLE DATA           �   COPY bscs.rateplanes (id, commercial_name, voice_service, cross_voice_service, data_service, sms_service, roaming_service, additional_minutes_service, additional_sms_service, additional_data_service, additional_roaming_service, fee) FROM stdin;
    bscs          postgres    false    213   8                 0    41460    service_package 
   TABLE DATA           @   COPY bscs.service_package (id, service_type, units) FROM stdin;
    bscs          postgres    false    215   x8                 0    41466    users 
   TABLE DATA           @   COPY bscs.users (national_id, u_name, age, address) FROM stdin;
    bscs          postgres    false    217   9                 0    41471    consumption 
   TABLE DATA           ~   COPY rtx.consumption (id, source_msisdn, terminated_msisdn, time_stamp, duration, rate, service_id, rateplane_id) FROM stdin;
    rtx          postgres    false    218   �9       &           0    0    contract_contract_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('bscs.contract_contract_id_seq', 2, true);
          bscs          postgres    false    212            '           0    0    rateplanes_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('bscs.rateplanes_id_seq', 6, true);
          bscs          postgres    false    214            (           0    0    service_package_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('bscs.service_package_id_seq', 23, true);
          bscs          postgres    false    216            )           0    0    consumption_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('rtx.consumption_id_seq', 24, true);
          rtx          postgres    false    219            v           2606    41482    contract contract_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY bscs.contract
    ADD CONSTRAINT contract_pkey PRIMARY KEY (contract_id);
 >   ALTER TABLE ONLY bscs.contract DROP CONSTRAINT contract_pkey;
       bscs            postgres    false    211            x           2606    41484    rateplanes rateplanes_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_pkey;
       bscs            postgres    false    213            z           2606    41486 $   service_package service_package_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY bscs.service_package
    ADD CONSTRAINT service_package_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY bscs.service_package DROP CONSTRAINT service_package_pkey;
       bscs            postgres    false    215            |           2606    41488    users users_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY bscs.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (national_id);
 8   ALTER TABLE ONLY bscs.users DROP CONSTRAINT users_pkey;
       bscs            postgres    false    217            ~           2606    41490    consumption consumption_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY rtx.consumption
    ADD CONSTRAINT consumption_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY rtx.consumption DROP CONSTRAINT consumption_pkey;
       rtx            postgres    false    218                       2606    41491 #   contract contract_rateplane_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.contract
    ADD CONSTRAINT contract_rateplane_id_fkey FOREIGN KEY (rateplane_id) REFERENCES bscs.rateplanes(id);
 K   ALTER TABLE ONLY bscs.contract DROP CONSTRAINT contract_rateplane_id_fkey;
       bscs          postgres    false    3192    213    211            �           2606    41496    contract contract_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.contract
    ADD CONSTRAINT contract_userid_fkey FOREIGN KEY (userid) REFERENCES bscs.users(national_id);
 E   ALTER TABLE ONLY bscs.contract DROP CONSTRAINT contract_userid_fkey;
       bscs          postgres    false    217    3196    211            �           2606    41501 .   rateplanes rateplanes_cross_voice_service_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_cross_voice_service_fkey FOREIGN KEY (cross_voice_service) REFERENCES bscs.service_package(id);
 V   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_cross_voice_service_fkey;
       bscs          postgres    false    213    215    3194            �           2606    41506 '   rateplanes rateplanes_data_service_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_data_service_fkey FOREIGN KEY (data_service) REFERENCES bscs.service_package(id);
 O   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_data_service_fkey;
       bscs          postgres    false    215    3194    213            �           2606    41511 *   rateplanes rateplanes_roaming_service_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_roaming_service_fkey FOREIGN KEY (roaming_service) REFERENCES bscs.service_package(id);
 R   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_roaming_service_fkey;
       bscs          postgres    false    3194    213    215            �           2606    41516 &   rateplanes rateplanes_sms_service_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_sms_service_fkey FOREIGN KEY (sms_service) REFERENCES bscs.service_package(id);
 N   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_sms_service_fkey;
       bscs          postgres    false    3194    213    215            �           2606    41521 (   rateplanes rateplanes_voice_service_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY bscs.rateplanes
    ADD CONSTRAINT rateplanes_voice_service_fkey FOREIGN KEY (voice_service) REFERENCES bscs.service_package(id);
 P   ALTER TABLE ONLY bscs.rateplanes DROP CONSTRAINT rateplanes_voice_service_fkey;
       bscs          postgres    false    215    213    3194            �           2606    41526 )   consumption consumption_rateplane_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY rtx.consumption
    ADD CONSTRAINT consumption_rateplane_id_fkey FOREIGN KEY (rateplane_id) REFERENCES bscs.rateplanes(id);
 P   ALTER TABLE ONLY rtx.consumption DROP CONSTRAINT consumption_rateplane_id_fkey;
       rtx          postgres    false    213    218    3192               �   x�uP˭1;;�<�I[��_ǃ0;�$���FA�L3bł��JD�l������1+�L��B�`Da�ї_}��gpq,����J�K�^�Z�Y�-�t#�X�z��[hV��V��Hm�$�֊�Y�����]2�k�X'�3�$�;����MUr���S����]�&w.�ퟹ���o��:P          \   x�eʻ�@�ڙ�	P���5%���B'%�����,���q�)�T[�p���_��ʄMI��	���:!]��v:�]������         �   x�U��
�0E��c��di�e0BWJ�@3��Cy�� W����B "�:$��CV`��dȰ����KQ%��� ��8(����)�btw;��S���Sv��M;PȢ�!l1h8k9���C�B�}�vI����"� $\F9         �   x�M���0E�ׯ��)�+ap &����m��&�,����NN�m�j���%y�} �g��y�(�4�y��x��IN�l�"�LH�z�R��Cn`�;"�HS<�z�vN�m߭�����F��"��#�_����X[����`r)���$�� �18�         :   x��ʱ !��م?;&�a�X�;J��T���,qu��HfzX(�9��E�_��     