CREATE TABLE geo_entry (
	geonameid varchar(255) NOT NULL,
	admin1code varchar(255) NULL,
	admin2code varchar(255) NULL,
	admin3code varchar(255) NULL,
	admin4code varchar(255) NULL,
	ascii_name varchar(255) NULL,
	cc2 varchar(255) NULL,
	country_code varchar(255) NULL,
	dem varchar(255) NULL,
	elevation varchar(255) NULL,
	feature_class varchar(255) NULL,
	feature_code varchar(255) NULL,
	latitude varchar(255) NULL,
	longitude varchar(255) NULL,
	modification_date varchar(255) NULL,
	"name" varchar(255) NULL,
	population varchar(255) NULL,
	timezone varchar(255) NULL,
	CONSTRAINT geo_entry_pkey PRIMARY KEY (geonameid)
);

CREATE TABLE geo_entry_alternate_names (
	geo_entry_geonameid varchar(255) NOT NULL,
	"name" varchar(255) NULL,
	script varchar(255) NULL,
	CONSTRAINT fkfpy6a3qlgjaxthb412mwufsfj FOREIGN KEY (geo_entry_geonameid) REFERENCES geo_entry(geonameid)
);

CREATE TABLE feature (
	id varchar(255) NOT NULL,
	category varchar(255) NULL,
	code varchar(255) NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT feature_pkey PRIMARY KEY (id)
);