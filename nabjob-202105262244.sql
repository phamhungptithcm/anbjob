--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7
-- Dumped by pg_dump version 12.7

-- Started on 2021-05-26 22:44:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16396)
-- Name: branchs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.branchs (
    branch_id integer NOT NULL,
    branch_code character varying(15) NOT NULL,
    branch_name character varying(500) NOT NULL,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true
);


ALTER TABLE public.branchs OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16394)
-- Name: branchs_branch_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.branchs_branch_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.branchs_branch_id_seq OWNER TO postgres;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 202
-- Name: branchs_branch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.branchs_branch_id_seq OWNED BY public.branchs.branch_id;


--
-- TOC entry 205 (class 1259 OID 16411)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    category_id integer NOT NULL,
    category_code character varying(15) NOT NULL,
    category_name character varying(500) NOT NULL,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16409)
-- Name: categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categories_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categories_category_id_seq OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 204
-- Name: categories_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categories_category_id_seq OWNED BY public.categories.category_id;


--
-- TOC entry 214 (class 1259 OID 16554)
-- Name: order_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_details (
    order_detail_id integer NOT NULL,
    quantity integer NOT NULL,
    price real NOT NULL,
    order_id integer NOT NULL,
    product_id integer NOT NULL,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.order_details OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16552)
-- Name: order_details_order_detail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_details_order_detail_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_details_order_detail_id_seq OWNER TO postgres;

--
-- TOC entry 2934 (class 0 OID 0)
-- Dependencies: 213
-- Name: order_details_order_detail_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_details_order_detail_id_seq OWNED BY public.order_details.order_detail_id;


--
-- TOC entry 216 (class 1259 OID 16597)
-- Name: order_statuses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_statuses (
    order_status_id integer NOT NULL,
    order_status_code character varying(50) NOT NULL,
    order_status_name real NOT NULL,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.order_statuses OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16595)
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_statuses_order_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_statuses_order_status_id_seq OWNER TO postgres;

--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 215
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.order_statuses_order_status_id_seq OWNED BY public.order_statuses.order_status_id;


--
-- TOC entry 212 (class 1259 OID 16532)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    order_id integer NOT NULL,
    order_code character varying(50) NOT NULL,
    order_amount real NOT NULL,
    ordered_by character varying(50),
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    order_status_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16530)
-- Name: orders_order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_order_id_seq OWNER TO postgres;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_order_id_seq OWNED BY public.orders.order_id;


--
-- TOC entry 208 (class 1259 OID 16494)
-- Name: product_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_category (
    product_id numeric(14,0) NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.product_category OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16514)
-- Name: product_histories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product_histories (
    product_history_id integer NOT NULL,
    description character varying(500) NOT NULL,
    product_id integer NOT NULL,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.product_histories OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16512)
-- Name: product_histories_product_history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_histories_product_history_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_histories_product_history_id_seq OWNER TO postgres;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 209
-- Name: product_histories_product_history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_histories_product_history_id_seq OWNED BY public.product_histories.product_history_id;


--
-- TOC entry 207 (class 1259 OID 16476)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    product_id integer NOT NULL,
    product_code character varying(15) NOT NULL,
    product_name character varying(500) NOT NULL,
    product_price real NOT NULL,
    product_colors character varying(100),
    branch_id integer,
    created_by character varying(50),
    created_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    modified_by character varying(50),
    modified_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    active boolean DEFAULT true,
    fulltext_search character varying(2000)
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16474)
-- Name: products_product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_product_id_seq OWNER TO postgres;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 206
-- Name: products_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_product_id_seq OWNED BY public.products.product_id;


--
-- TOC entry 2731 (class 2604 OID 16399)
-- Name: branchs branch_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branchs ALTER COLUMN branch_id SET DEFAULT nextval('public.branchs_branch_id_seq'::regclass);


--
-- TOC entry 2735 (class 2604 OID 16414)
-- Name: categories category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories ALTER COLUMN category_id SET DEFAULT nextval('public.categories_category_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 16557)
-- Name: order_details order_detail_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details ALTER COLUMN order_detail_id SET DEFAULT nextval('public.order_details_order_detail_id_seq'::regclass);


--
-- TOC entry 2752 (class 2604 OID 16600)
-- Name: order_statuses order_status_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_statuses ALTER COLUMN order_status_id SET DEFAULT nextval('public.order_statuses_order_status_id_seq'::regclass);


--
-- TOC entry 2746 (class 2604 OID 16535)
-- Name: orders order_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN order_id SET DEFAULT nextval('public.orders_order_id_seq'::regclass);


--
-- TOC entry 2743 (class 2604 OID 16517)
-- Name: product_histories product_history_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_histories ALTER COLUMN product_history_id SET DEFAULT nextval('public.product_histories_product_history_id_seq'::regclass);


--
-- TOC entry 2739 (class 2604 OID 16479)
-- Name: products product_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN product_id SET DEFAULT nextval('public.products_product_id_seq'::regclass);


--
-- TOC entry 2912 (class 0 OID 16396)
-- Dependencies: 203
-- Data for Name: branchs; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.branchs (branch_id, branch_code, branch_name, created_by, created_date, modified_by, modified_date, active) FROM stdin;
\.


--
-- TOC entry 2914 (class 0 OID 16411)
-- Dependencies: 205
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (category_id, category_code, category_name, created_by, created_date, modified_by, modified_date, active) FROM stdin;
\.


--
-- TOC entry 2923 (class 0 OID 16554)
-- Dependencies: 214
-- Data for Name: order_details; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_details (order_detail_id, quantity, price, order_id, product_id, created_by, created_date, modified_by, modified_date) FROM stdin;
\.


--
-- TOC entry 2925 (class 0 OID 16597)
-- Dependencies: 216
-- Data for Name: order_statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.order_statuses (order_status_id, order_status_code, order_status_name, created_by, created_date, modified_by, modified_date) FROM stdin;
\.


--
-- TOC entry 2921 (class 0 OID 16532)
-- Dependencies: 212
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (order_id, order_code, order_amount, ordered_by, created_by, created_date, modified_by, modified_date, order_status_id) FROM stdin;
\.


--
-- TOC entry 2917 (class 0 OID 16494)
-- Dependencies: 208
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_category (product_id, category_id) FROM stdin;
\.


--
-- TOC entry 2919 (class 0 OID 16514)
-- Dependencies: 210
-- Data for Name: product_histories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product_histories (product_history_id, description, product_id, created_by, created_date, modified_by, modified_date) FROM stdin;
\.


--
-- TOC entry 2916 (class 0 OID 16476)
-- Dependencies: 207
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (product_id, product_code, product_name, product_price, product_colors, branch_id, created_by, created_date, modified_by, modified_date, active, fulltext_search) FROM stdin;
\.


--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 202
-- Name: branchs_branch_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.branchs_branch_id_seq', 1, false);


--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 204
-- Name: categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_category_id_seq', 1, false);


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 213
-- Name: order_details_order_detail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_details_order_detail_id_seq', 1, false);


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 215
-- Name: order_statuses_order_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_statuses_order_status_id_seq', 1, false);


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 211
-- Name: orders_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_order_id_seq', 1, false);


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 209
-- Name: product_histories_product_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_histories_product_history_id_seq', 1, false);


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 206
-- Name: products_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_product_id_seq', 1, false);


--
-- TOC entry 2756 (class 2606 OID 16408)
-- Name: branchs branchs_branch_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branchs
    ADD CONSTRAINT branchs_branch_code_key UNIQUE (branch_code);


--
-- TOC entry 2758 (class 2606 OID 16406)
-- Name: branchs branchs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.branchs
    ADD CONSTRAINT branchs_pkey PRIMARY KEY (branch_id);


--
-- TOC entry 2760 (class 2606 OID 16423)
-- Name: categories categories_category_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_category_code_key UNIQUE (category_code);


--
-- TOC entry 2762 (class 2606 OID 16421)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (category_id);


--
-- TOC entry 2776 (class 2606 OID 16561)
-- Name: order_details order_details_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT order_details_pkey PRIMARY KEY (order_detail_id);


--
-- TOC entry 2778 (class 2606 OID 16606)
-- Name: order_statuses order_statuses_order_status_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_statuses
    ADD CONSTRAINT order_statuses_order_status_code_key UNIQUE (order_status_code);


--
-- TOC entry 2780 (class 2606 OID 16604)
-- Name: order_statuses order_statuses_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_statuses
    ADD CONSTRAINT order_statuses_pkey PRIMARY KEY (order_status_id);


--
-- TOC entry 2772 (class 2606 OID 16541)
-- Name: orders orders_order_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_order_code_key UNIQUE (order_code);


--
-- TOC entry 2774 (class 2606 OID 16539)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (order_id);


--
-- TOC entry 2768 (class 2606 OID 16498)
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (product_id, category_id);


--
-- TOC entry 2770 (class 2606 OID 16524)
-- Name: product_histories product_histories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_histories
    ADD CONSTRAINT product_histories_pkey PRIMARY KEY (product_history_id);


--
-- TOC entry 2764 (class 2606 OID 16486)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (product_id);


--
-- TOC entry 2766 (class 2606 OID 16488)
-- Name: products products_product_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_product_code_key UNIQUE (product_code);


--
-- TOC entry 2783 (class 2606 OID 16562)
-- Name: order_details order_details_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT order_details_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(order_id);


--
-- TOC entry 2784 (class 2606 OID 16567)
-- Name: order_details order_details_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_details
    ADD CONSTRAINT order_details_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- TOC entry 2782 (class 2606 OID 16525)
-- Name: product_histories product_histories_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product_histories
    ADD CONSTRAINT product_histories_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(product_id);


--
-- TOC entry 2781 (class 2606 OID 16489)
-- Name: products products_branch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_branch_id_fkey FOREIGN KEY (branch_id) REFERENCES public.branchs(branch_id);


-- Completed on 2021-05-26 22:44:37

--
-- PostgreSQL database dump complete
--

