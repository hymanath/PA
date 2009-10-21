
CREATE TABLE booth ( 
	booth_id       	bigint(15) AUTO_INCREMENT NOT NULL,
	part_no        	bigint(20) NULL,
	part_name      	varchar(250) NULL,
	location       	varchar(250) NULL,
	village_covered	varchar(250) NULL,
	tehsil_id      	bigint(15) NOT NULL,
	male_voters    	double(10,0) NULL,
	female_voters  	double(10,0) NULL,
	total_voters   	double(10,0) NULL,
	PRIMARY KEY(booth_id) );
GO
CREATE TABLE booth_constituency_election ( 
	booth_constituency_election_id	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_id                      	bigint(15) NOT NULL,
	consti_elec_id                	bigint(15) NOT NULL,
	PRIMARY KEY(booth_constituency_election_id) );
GO
CREATE TABLE booth_result ( 
	booth_result_id               	bigint(15) AUTO_INCREMENT NOT NULL,
	nomination_id                 	bigint(15) NOT NULL,
	booth_constituency_election_id	bigint(15) NOT NULL,
	votes_earned                  	double(10,0) NULL,
	PRIMARY KEY(booth_result_id) );
GO
CREATE TABLE booth_village_census ( 
	booth_village_census_id	bigint(15)AUTO_INCREMENT NOT NULL,
	booth_id               	bigint(15) NOT NULL,
	village_census_code    	bigint(15) NULL,
	PRIMARY KEY(booth_village_census_id) );
GO
CREATE TABLE cadre ( 
	cadre_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	user_id          	bigint(15) NULL,
	first_name       	varchar(25) NOT NULL,
	midle_name       	varchar(25) NULL,
	last_name        	varchar(25) NOT NULL,
	mobile_no        	varchar(11) NOT NULL,
	email            	varchar(100) NOT NULL,
	gender           	varchar(1) NOT NULL,
	state_id         	bigint(15) NULL,
	district_id      	bigint(15) NULL,
	mandal_id        	bigint(15) NULL,
	village_id       	bigint(15) NULL,
	booth_id         	bigint(15) NULL,
	cadre_level_id   	bigint(15) NULL,
	cadre_level_value	bigint(15) NULL,
	PRIMARY KEY(cadre_id) );
GO
CREATE TABLE cadre_level ( 
	cadre_level_id	bigint(15) AUTO_INCREMENT NOT NULL,
	level         	varchar(25) NOT NULL,
	PRIMARY KEY(cadre_level_id) );
GO
CREATE TABLE candidate ( 
	candidate_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	firstname    	varchar(75) NULL,
	middlename   	varchar(75) NULL,
	lastname     	varchar(75) NULL,
	dateofbirth  	date NULL,
	email_address	varchar(60) NULL,
	phone        	varchar(25) NULL,
	mobile       	varchar(25) NULL,
	address      	varchar(25) NULL,
	education    	varchar(25) NULL,
	gender       	varchar(25) NULL,
	image        	varchar(50) NULL,
	PRIMARY KEY(candidate_id) );
GO
CREATE TABLE candidate_result ( 
	candidate_result_id	bigint(15) AUTO_INCREMENT NOT NULL,
	nomination_id      	bigint(15) NOT NULL,
	votes_earned       	double(10,0) NULL,
	rank               	bigint(15) NULL,
	votes_percentage   	varchar(15) NULL,
	PRIMARY KEY(candidate_result_id) );
GO
CREATE TABLE census_master ( 
	census_id     	bigint(15) AUTO_INCREMENT NOT NULL,
	state_id      	bigint(15) NULL,
	district_id   	bigint(15) NULL,
	tehsil_id     	bigint(15) NULL,
	township_id   	bigint(15) NULL,
	ward_id       	bigint(15) NULL,
	level         	varchar(10) NOT NULL,
	year          	int(5) NOT NULL,
	tru           	varchar(10) NOT NULL,
	No_HH         	bigint(20) NULL,
	TOT_P         	bigint(20) NULL,
	TOT_M         	bigint(20) NULL,
	TOT_F         	bigint(20) NULL,
	P_06          	bigint(20) NULL,
	M_06          	bigint(20) NULL,
	F_06          	bigint(20) NULL,
	P_SC          	bigint(20) NULL,
	M_SC          	bigint(20) NULL,
	F_SC          	bigint(20) NULL,
	P_ST          	bigint(20) NULL,
	M_ST          	bigint(20) NULL,
	F_ST          	bigint(20) NULL,
	P_LIT         	bigint(20) NULL,
	M_LIT         	bigint(20) NULL,
	F_LIT         	bigint(20) NULL,
	P_ILL         	bigint(20) NULL,
	M_ILL         	bigint(20) NULL,
	F_ILL         	bigint(20) NULL,
	TOT_WORK_P    	bigint(20) NULL,
	TOT_WORK_M    	bigint(20) NULL,
	TOT_WORK_F    	bigint(20) NULL,
	MAINWORK_P    	bigint(20) NULL,
	MAINWORK_M    	bigint(20) NULL,
	MAINWORK_F    	bigint(20) NULL,
	MAIN_CL_P     	bigint(20) NULL,
	MAIN_CL_M     	bigint(20) NULL,
	MAIN_CL_F     	bigint(20) NULL,
	MAIN_AL_P     	bigint(20) NULL,
	MAIN_AL_M     	bigint(20) NULL,
	MAIN_AL_F     	bigint(20) NULL,
	MAIN_HH_P     	bigint(20) NULL,
	MAIN_HH_M     	bigint(20) NULL,
	MAIN_HH_F     	bigint(20) NULL,
	MAIN_OT_P     	bigint(20) NULL,
	MAIN_OT_M     	bigint(20) NULL,
	MAIN_OT_F     	bigint(20) NULL,
	MARGWORK_P    	bigint(20) NULL,
	MARGWORK_M    	bigint(20) NULL,
	MARGWORK_F    	bigint(20) NULL,
	MARG_CL_P     	bigint(20) NULL,
	MARG_CL_M     	bigint(20) NULL,
	MARG_CL_F     	bigint(20) NULL,
	MARG_AL_P     	bigint(20) NULL,
	MARG_AL_M     	bigint(20) NULL,
	MARG_AL_F     	bigint(20) NULL,
	MARG_HH_P     	bigint(20) NULL,
	MARG_HH_M     	bigint(20) NULL,
	MARG_HH_F     	bigint(20) NULL,
	MARG_OT_P     	bigint(20) NULL,
	MARG_OT_M     	bigint(20) NULL,
	MARG_OT_F     	bigint(20) NULL,
	NON_WORK_P    	bigint(20) NULL,
	NON_WORK_M    	bigint(20) NULL,
	NON_WORK_F    	bigint(20) NULL,
	SEXRATIO      	double(10,0) NULL,
	SEXRATIO_SC   	double(10,0) NULL,
	SEXRATIO_ST   	double(10,0) NULL,
	HH_SIZE       	double(10,0) NULL,
	SC_PER        	double(10,0) NULL,
	ST_PER        	double(10,0) NULL,
	SEXRATIO_06   	double(10,0) NULL,
	M_LIT_RATE    	double(10,0) NULL,
	F_LIT_RATE    	double(10,0) NULL,
	GENDER_GAP    	double(10,0) NULL,
	P_LIT_PER     	double(10,0) NULL,
	M_LIT_PER     	double(10,0) NULL,
	F_LIT_PER     	double(10,0) NULL,
	TOT_P_PER     	double(10,0) NULL,
	TOT_WOR_PER_P 	double(10,0) NULL,
	TOT_WOR_PER_M 	double(10,0) NULL,
	TOT_WOR_PER_F 	double(10,0) NULL,
	TOT_MAIN_PER_P	double(10,0) NULL,
	TOT_MAIN_PER_M	double(10,0) NULL,
	TOT_MAIN_PER_F	double(10,0) NULL,
	TOT_MARG_PER_P	double(10,0) NULL,
	TOT_MARG_PER_M	double(10,0) NULL,
	TOT_MARG_PER_F	double(10,0) NULL,
	NON_WORK_PER_P	double(10,0) NULL,
	NON_WORK_PER_M	double(10,0) NULL,
	NON_WORK_PER_F	double(10,0) NULL,
	CL_PER_P      	double(10,0) NULL,
	CL_PER_M      	double(10,0) NULL,
	CL_PER_F      	double(10,0) NULL,
	AL_PER_P      	double(10,0) NULL,
	AL_PER_M      	double(10,0) NULL,
	AL_PER_F      	double(10,0) NULL,
	HH_PER_P      	double(10,0) NULL,
	HH_PER_M      	double(10,0) NULL,
	HH_PER_F      	double(10,0) NULL,
	OW_PER_P      	double(10,0) NULL,
	OW_PER_M      	double(10,0) NULL,
	OW_PER_F      	double(10,0) NULL,
	CL_MAIN_MARG_P	bigint(20) NULL,
	CL_MAIN_MARG_M	bigint(20) NULL,
	CL_MAIN_MARG_F	bigint(20) NULL,
	AL_MAIN_MARG_P	bigint(20) NULL,
	AL_MAIN_MARG_M	bigint(20) NULL,
	AL_MAIN_MARG_F	bigint(20) NULL,
	HH_MAIN_MARG_P	bigint(20) NULL,
	HH_MAIN_MARG_M	bigint(20) NULL,
	HH_MAIN_MARG_F	bigint(20) NULL,
	OW_MAIN_MARG_P	bigint(20) NULL,
	OW_MAIN_MARG_M	bigint(20) NULL,
	OW_MAIN_MARG_F	bigint(20) NULL,
	WPR           	double(10,0) NULL,
	PRIMARY KEY(census_id) );
GO
CREATE TABLE constituency ( 
	constituency_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	name             	varchar(50) NULL,
	election_scope_id	bigint(15) NULL,
	start_date       	date NULL,
	deform_date      	date NULL,
	state_id         	bigint(15) NULL,
	district_id      	bigint(15) NULL,
	country_id       	bigint(15) NULL,
	PRIMARY KEY(constituency_id) );
GO
CREATE TABLE constituency_election ( 
	consti_elec_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	constituency_id	bigint(15) NOT NULL,
	election_id    	bigint(15) NOT NULL,
	election_date  	date NULL,
	PRIMARY KEY(consti_elec_id) );
GO
CREATE TABLE constituency_election_result ( 
	consti_elec_result_id	bigint(15) AUTO_INCREMENT NOT NULL,
	total_votes          	double(10,0) NULL,
	valid_votes          	double(10,0) NULL,
	rejected_votes       	double(10,0) NULL,
	missing_votes        	double(10,0) NULL,
	total_votes_polled   	double(10,0) NULL,
	tendered_votes       	double(10,0) NULL,
	consti_elec_id       	bigint(15) NOT NULL,
	PRIMARY KEY(consti_elec_result_id) );
GO
CREATE TABLE country ( 
	country_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	country_name	varchar(100) NOT NULL,
	capital     	varchar(100) NULL,
	iso_code    	varchar(25) NULL,
	PRIMARY KEY(country_id) );
GO
CREATE TABLE delimitation_constituency ( 
	delimitation_constituency_id	bigint(15) AUTO_INCREMENT NOT NULL,
	year                        	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_constituency_id) );
GO
CREATE TABLE delimitation_constituency_data ( 
	delimitation_constituency_data_id	bigint(15) AUTO_INCREMENT NOT NULL,
	delimitation_constituency_id     	bigint(15) NOT NULL,
	new_constituency_no              	bigint(15) NOT NULL,
	constituency_id                  	bigint(15) NOT NULL,
	new_constituency_name            	varchar(50) NULL,
	PRIMARY KEY(delimitation_constituency_data_id) );
GO
CREATE TABLE delimitation_district ( 
	delimitation_district_id	bigint(15) AUTO_INCREMENT NOT NULL,
	year                    	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_district_id) );
GO
CREATE TABLE delimitation_district_data ( 
	delimitation_district_data_id	bigint(15) AUTO_INCREMENT NOT NULL,
	district_id                  	bigint(15) NOT NULL,
	new_district_no              	bigint(15) NOT NULL,
	delimitation_district_id     	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_district_data_id) );
GO
CREATE TABLE district ( 
	district_id     	bigint(15) AUTO_INCREMENT NOT NULL,
	district_name   	varchar(100) NULL,
	district_capital	varchar(100) NULL,
	area            	decimal(15,5) NULL,
	population      	double(10,0) NULL,
	state_id        	bigint(15) NULL,
	district_code   	bigint(15) NULL,
	start_date      	date NULL,
	deform_date     	date NULL,
	PRIMARY KEY(district_id) );
GO
CREATE TABLE election ( 
	election_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	election_scope_id	bigint(15) NULL,
	notification_date	date NULL,
	start_date       	date NULL,
	end_date         	varchar(25) NULL,
	election_year    	varchar(25) NULL,
	PRIMARY KEY(election_id) );
GO
CREATE TABLE election_alliance ( 
	alliance_id	bigint(15) NOT NULL,
	election_id	bigint(15) NOT NULL,
	party_id   	bigint(15) NOT NULL,
	PRIMARY KEY(alliance_id) );
GO
CREATE TABLE election_scope ( 
	election_scope_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_type_id 	bigint(15) NOT NULL,
	state_id         	bigint(15) NOT NULL,
	country_id       	bigint(15) NOT NULL,
	PRIMARY KEY(election_scope_id) );
GO
CREATE TABLE election_type ( 
	election_type_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_type   	varchar(25) NULL,
	scope           	varchar(25) NULL,
	PRIMARY KEY(election_type_id) );
GO
CREATE TABLE nomination ( 
	nomination_id    	bigint(15) AUTO_INCREMENT NOT NULL,
	candidate_id     	bigint(15) NOT NULL,
	consti_elec_id   	bigint(15) NOT NULL,
	nomination_status	varchar(25) NULL,
	party_id         	bigint(15) NOT NULL,
	assets           	double(10,0) NULL,
	liabilities      	double(10,0) NULL,
	criminal_charges 	varchar(300) NULL,
	PRIMARY KEY(nomination_id) );
GO
CREATE TABLE party ( 
	party_id           	bigint(15) AUTO_INCREMENT NOT NULL,
	long_name          	varchar(100) NULL,
	short_name         	varchar(25) NULL,
	symbol             	varchar(25) NULL,
	address            	varchar(300) NULL,
	comments           	varchar(300) NULL,
	party_recognization	varchar(25) NULL,
	PRIMARY KEY(party_id) );
GO
CREATE TABLE pollingstationdetails ( 
	PollingStationDetails_id	bigint(15) AUTO_INCREMENT NOT NULL,
	constituency_id         	bigint(15) NOT NULL,
	name                    	varchar(50) NULL,
	part_no                 	bigint(15) NOT NULL,
	total_electors          	double(10,0) NULL,
	villages_covered        	varchar(50) NULL,
	village_censuscode      	double(10,0) NOT NULL,
	election_id             	bigint(15) NOT NULL,
	constituency_no         	bigint(15) NOT NULL,
	district_no             	bigint(15) NOT NULL,
	PRIMARY KEY(PollingStationDetails_id) );
GO
CREATE TABLE registration ( 
	registration_id	bigint(15) AUTO_INCREMENT NOT NULL,
	firstname      	varchar(40) NULL,
	middlename     	varchar(40) NULL,
	lastname       	varchar(40) NULL,
	gender         	varchar(25) NULL,
	username       	varchar(40) NULL,
	password       	varchar(40) NULL,
	dateofbirth    	date NULL,
	email          	varchar(100) NULL,
	phone          	varchar(25) NULL,
	mobile         	varchar(25) NULL,
	address        	varchar(50) NULL,
	country        	varchar(25) NULL,
	pincode        	varchar(25) NULL,
	access_type    	varchar(40) NOT NULL,
	access_value   	varchar(40) NOT NULL,
	PRIMARY KEY(registration_id) );
GO
CREATE TABLE state ( 
	state_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	state_name       	varchar(100) NOT NULL,
	admin_capital    	varchar(100) NULL,
	legis_capital    	varchar(100) NULL,
	judiciary_capital	varchar(100) NULL,
	year_established 	date NULL,
	state_language   	varchar(50) NULL,
	state_symbol     	varchar(50) NULL,
	state_song       	varchar(50) NULL,
	state_animal     	varchar(50) NULL,
	state_bird       	varchar(50) NULL,
	state_tree       	varchar(50) NULL,
	state_sport      	varchar(50) NULL,
	state_dance      	varchar(50) NULL,
	state_flower     	varchar(50) NULL,
	iso_code         	varchar(25) NULL,
	country_id       	bigint(15) NULL,
	state_code       	bigint(15) NULL,
	start_date       	date NULL,
	deform_date      	date NULL,
	PRIMARY KEY(state_id) );
GO
CREATE TABLE tehsil ( 
	tehsil_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	tehsil_name	varchar(100) NULL,
	district_id	bigint(15) NULL,
	tehsil_code	bigint(15) NULL,
	start_date 	date NULL,
	deform_date	varchar(25) NULL,
	census_year	int(11) NULL,
	PRIMARY KEY(tehsil_id) );
GO
CREATE TABLE temp_census_master ( 
	STATE         	int(10) NULL,
	DISTRICT      	int(10) NULL,
	TEHSIL        	int(10) NULL,
	TOWN_VILL     	int(10) NULL,
	WARD          	int(10) NULL,
	LEVEL         	varchar(10) NULL,
	NAME          	varchar(100) NULL,
	TRU           	varchar(10) NULL,
	No_HH         	bigint(20) NULL,
	TOT_P         	bigint(20) NULL,
	TOT_M         	bigint(20) NULL,
	TOT_F         	bigint(20) NULL,
	P_06          	bigint(20) NULL,
	M_06          	bigint(20) NULL,
	F_06          	bigint(20) NULL,
	P_SC          	bigint(20) NULL,
	M_SC          	bigint(20) NULL,
	F_SC          	bigint(20) NULL,
	P_ST          	bigint(20) NULL,
	M_ST          	bigint(20) NULL,
	F_ST          	bigint(20) NULL,
	P_LIT         	bigint(20) NULL,
	M_LIT         	bigint(20) NULL,
	F_LIT         	bigint(20) NULL,
	P_ILL         	bigint(20) NULL,
	M_ILL         	bigint(20) NULL,
	F_ILL         	bigint(20) NULL,
	TOT_WORK_P    	bigint(20) NULL,
	TOT_WORK_M    	bigint(20) NULL,
	TOT_WORK_F    	bigint(20) NULL,
	MAINWORK_P    	bigint(20) NULL,
	MAINWORK_M    	bigint(20) NULL,
	MAINWORK_F    	bigint(20) NULL,
	MAIN_CL_P     	bigint(20) NULL,
	MAIN_CL_M     	bigint(20) NULL,
	MAIN_CL_F     	bigint(20) NULL,
	MAIN_AL_P     	bigint(20) NULL,
	MAIN_AL_M     	bigint(20) NULL,
	MAIN_AL_F     	bigint(20) NULL,
	MAIN_HH_P     	bigint(20) NULL,
	MAIN_HH_M     	bigint(20) NULL,
	MAIN_HH_F     	bigint(20) NULL,
	MAIN_OT_P     	bigint(20) NULL,
	MAIN_OT_M     	bigint(20) NULL,
	MAIN_OT_F     	bigint(20) NULL,
	MARGWORK_P    	bigint(20) NULL,
	MARGWORK_M    	bigint(20) NULL,
	MARGWORK_F    	bigint(20) NULL,
	MARG_CL_P     	bigint(20) NULL,
	MARG_CL_M     	bigint(20) NULL,
	MARG_CL_F     	bigint(20) NULL,
	MARG_AL_P     	bigint(20) NULL,
	MARG_AL_M     	bigint(20) NULL,
	MARG_AL_F     	bigint(20) NULL,
	MARG_HH_P     	bigint(20) NULL,
	MARG_HH_M     	bigint(20) NULL,
	MARG_HH_F     	bigint(20) NULL,
	MARG_OT_P     	bigint(20) NULL,
	MARG_OT_M     	bigint(20) NULL,
	MARG_OT_F     	bigint(20) NULL,
	NON_WORK_P    	bigint(20) NULL,
	NON_WORK_M    	bigint(20) NULL,
	NON_WORK_F    	bigint(20) NULL,
	SEXRATIO      	double(10,0) NULL,
	SEXRATIO_SC   	double(10,0) NULL,
	SEXRATIO_ST   	double(10,0) NULL,
	HH_SIZE       	double(10,0) NULL,
	SC_PER        	double(10,0) NULL,
	ST_PER        	double(10,0) NULL,
	SEXRATIO_06   	double(10,0) NULL,
	M_LIT_RATE    	double(10,0) NULL,
	F_LIT_RATE    	double(10,0) NULL,
	GENDER_GAP    	double(10,0) NULL,
	P_LIT_PER     	double(10,0) NULL,
	M_LIT_PER     	double(10,0) NULL,
	F_LIT_PER     	double(10,0) NULL,
	TOT_P_PER     	double(10,0) NULL,
	TOT_WOR_PER_P 	double(10,0) NULL,
	TOT_WOR_PER_M 	double(10,0) NULL,
	TOT_WOR_PER_F 	double(10,0) NULL,
	TOT_MAIN_PER_P	double(10,0) NULL,
	TOT_MAIN_PER_M	double(10,0) NULL,
	TOT_MAIN_PER_F	double(10,0) NULL,
	TOT_MARG_PER_P	double(10,0) NULL,
	TOT_MARG_PER_M	double(10,0) NULL,
	TOT_MARG_PER_F	double(10,0) NULL,
	NON_WORK_PER_P	double(10,0) NULL,
	NON_WORK_PER_M	double(10,0) NULL,
	NON_WORK_PER_F	double(10,0) NULL,
	CL_PER_P      	double(10,0) NULL,
	CL_PER_M      	double(10,0) NULL,
	CL_PER_F      	double(10,0) NULL,
	AL_PER_P      	double(10,0) NULL,
	AL_PER_M      	double(10,0) NULL,
	AL_PER_F      	double(10,0) NULL,
	HH_PER_P      	double(10,0) NULL,
	HH_PER_M      	double(10,0) NULL,
	HH_PER_F      	double(10,0) NULL,
	OW_PER_P      	double(10,0) NULL,
	OW_PER_M      	double(10,0) NULL,
	OW_PER_F      	double(10,0) NULL,
	CL_MAIN_MARG_P	bigint(20) NULL,
	CL_MAIN_MARG_M	bigint(20) NULL,
	CL_MAIN_MARG_F	bigint(20) NULL,
	AL_MAIN_MARG_P	bigint(20) NULL,
	AL_MAIN_MARG_M	bigint(20) NULL,
	AL_MAIN_MARG_F	bigint(20) NULL,
	HH_MAIN_MARG_P	bigint(20) NULL,
	HH_MAIN_MARG_M	bigint(20) NULL,
	HH_MAIN_MARG_F	bigint(20) NULL,
	OW_MAIN_MARG_P	bigint(20) NULL,
	OW_MAIN_MARG_M	bigint(20) NULL,
	OW_MAIN_MARG_F	bigint(20) NULL,
	WPR           	double(10,0) NULL 
	);
GO
CREATE TABLE township ( 
	township_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	township_name	varchar(100) NULL,
	township_code	bigint(15) COMMENT 'township_code is census code for village or town'  NULL,
	tehsil_id    	bigint(15) NULL,
	township_type	varchar(25) COMMENT 'It indicates either it is TOWN or VILLAGE'  NULL,
	census_year  	int(11) NULL,
	greater_town 	char(1) NULL,
	PRIMARY KEY(township_id) );
GO
CREATE TABLE ward ( 
	ward_id    	bigint(15) AUTO_INCREMENT NOT NULL,
	ward_name  	varchar(100) NULL,
	ward_code  	bigint(15) NULL,
	township_id	bigint(15) NOT NULL,
	census_year	int(11) NULL,
	PRIMARY KEY(ward_id) );
GO
ALTER TABLE census_master
	ADD CONSTRAINT census_id 	UNIQUE (census_id);
GO
ALTER TABLE booth_constituency_election
	ADD CONSTRAINT fk_booth_booth_constituency_election 	FOREIGN KEY(booth_id) 	REFERENCES booth(booth_id);
GO
ALTER TABLE booth_village_census
	ADD CONSTRAINT fk_booth_booth_village_cencus 	FOREIGN KEY(booth_id) 	REFERENCES booth(booth_id);
GO
ALTER TABLE booth_result
	ADD CONSTRAINT fk_booth_constituency_election_booth_result 	FOREIGN KEY(booth_constituency_election_id) 	REFERENCES booth_constituency_election(booth_constituency_election_id);
GO
ALTER TABLE cadre 
       ADD CONSTRAINT fk_regis_cadre FOREIGN KEY(user_id) REFERENCES registration(registration_id);
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_caderlevel_cader 	FOREIGN KEY(cadre_level_id) REFERENCES cadre_level(cadre_level_id);
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_candidate_nomination 	FOREIGN KEY(candidate_id) 	REFERENCES candidate(candidate_id);
GO
ALTER TABLE constituency_election
	ADD CONSTRAINT fk_constituency_constituency_election 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id);
GO
ALTER TABLE delimitation_constituency_data
	ADD CONSTRAINT fk_constituency_delimitation_constituency_data 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id);
GO
ALTER TABLE pollingstationdetails
	ADD CONSTRAINT fk_constituency_PollingStationDetails 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id);
GO
ALTER TABLE booth_constituency_election
	ADD CONSTRAINT fk_constituency_election_booth_constituency_election 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id);
GO
ALTER TABLE constituency_election_result
	ADD CONSTRAINT fk_constituency_election_constituency_election_result 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id);
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_constituency_election_nomination 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id);
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_country_election_scope 	FOREIGN KEY(country_id) 	REFERENCES country(country_id);
GO
ALTER TABLE state
	ADD CONSTRAINT fk_country_state 	FOREIGN KEY(country_id) 	REFERENCES country(country_id);
GO
ALTER TABLE delimitation_constituency_data
	ADD CONSTRAINT fk_delimitation_constituency_delimitation_constituency_data 	FOREIGN KEY(delimitation_constituency_id) 	REFERENCES delimitation_constituency(delimitation_constituency_id);
GO
ALTER TABLE delimitation_district_data
	ADD CONSTRAINT fk_delimitation_district_delimitation_district_data 	FOREIGN KEY(delimitation_district_id) 	REFERENCES delimitation_district(delimitation_district_id);
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_district_census 	FOREIGN KEY(district_id) 	REFERENCES district(district_id);
GO
ALTER TABLE delimitation_district_data
	ADD CONSTRAINT fk_district_delimitation_district_data 	FOREIGN KEY(district_id) 	REFERENCES district(district_id);
GO
ALTER TABLE tehsil
	ADD CONSTRAINT fk_district_mandal 	FOREIGN KEY(district_id) 	REFERENCES district(district_id);
GO
ALTER TABLE constituency_election
	ADD CONSTRAINT fk_election_constituency_election 	FOREIGN KEY(election_id) 	REFERENCES election(election_id);
GO
ALTER TABLE election_alliance
	ADD CONSTRAINT fk_alliance_election 	FOREIGN KEY(election_id) 	REFERENCES election(election_id);
GO
ALTER TABLE pollingstationdetails
	ADD CONSTRAINT fk_election_PollingStationDetails 	FOREIGN KEY(election_id) 	REFERENCES election(election_id);
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_election_type_election_scope 	FOREIGN KEY(election_type_id) 	REFERENCES election_type(election_type_id);
GO
ALTER TABLE booth_result
	ADD CONSTRAINT fk_nomination_booth_result 	FOREIGN KEY(nomination_id) 	REFERENCES nomination(nomination_id);
GO
ALTER TABLE candidate_result
	ADD CONSTRAINT fk_nomination_candidate_result 	FOREIGN KEY(nomination_id) 	REFERENCES nomination(nomination_id);
GO
ALTER TABLE election_alliance
	ADD CONSTRAINT fk_alliance_party 	FOREIGN KEY(party_id) 	REFERENCES party(party_id);
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_party_nomination 	FOREIGN KEY(party_id) 	REFERENCES party(party_id);
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_state_census 	FOREIGN KEY(state_id) 	REFERENCES state(state_id);
GO
ALTER TABLE district
	ADD CONSTRAINT fk_state_district 	FOREIGN KEY(state_id) 	REFERENCES state(state_id);
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_state_election_scope 	FOREIGN KEY(state_id) 	REFERENCES state(state_id);
GO
ALTER TABLE booth
	ADD CONSTRAINT fk_tehsil_booth 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id);
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_tehsil_census 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id);
GO
ALTER TABLE township
	ADD CONSTRAINT fk_tehsil_township 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id);
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_town_census 	FOREIGN KEY(township_id) 	REFERENCES township(township_id);
GO
ALTER TABLE ward
	ADD CONSTRAINT fk_township_ward 	FOREIGN KEY(township_id) 	REFERENCES township(township_id);
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_ward_census 	FOREIGN KEY(ward_id) 	REFERENCES ward(ward_id);
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_state FOREIGN KEY(state_id) REFERENCES state(state_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_district FOREIGN KEY(district_id) REFERENCES district(district_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_tehsil FOREIGN KEY(mandal_id) REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_township FOREIGN KEY(village_id) REFERENCES township(township_id)
GO
insert into country values(1,'India','Delhi','IN')
GO
insert into state (state_id,state_name,admin_capital,country_id) values (1,'Andhra Pradesh',' Hyderabad',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (2,'Arunachal Pradesh',' Itanagar',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (3,'Assam',' Dispur',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (4,'Bihar',' Patna',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (5,'Chattisgarh',' Raipur',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (6,'Goa',' Panaji',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (7,'Gujarat',' Gandhinagar',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (8,'Haryana',' Chandigarh',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (9,'Himachal Pradesh',' Shimla',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (10,'Jammu & Kashmir',' Jammu/Srinagar',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (11,'Jharkhand',' Ranchi',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (12,'Karnataka',' Bangalore',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (13,'Kerala',' Trivandrum',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (14,'Madhya Pradesh',' Bhopal',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (15,'Maharashtra',' Mumbai',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (16,'Manipur',' Imphal',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (17,'Meghalaya',' Shillong',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (18,'Mizoram',' Aizawl',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (19,'Nagaland',' Kohima',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (20,'Orissa',' Bhubaneswar',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (21,'Punjab',' Chandigarh',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (22,'Rajasthan',' Jaipur',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (23,'Sikkim',' Gangtok',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (24,'Tamil Nadu',' Chennai',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (25,'Tripura',' Agartala',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (26,'Uttaranchal',' Dehradun',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (27,'Uttar Pradesh',' Lucknow',1)
GO
insert into state (state_id,state_name,admin_capital,country_id) values (28,'West Bengal',' Kolkata',1)
GO
insert into election_type values(1,'Parliament','Country')
GO
insert into election_type values(2,'Assembly','State')
GO
insert into election_scope values(1,'1',1 ,1)
GO
insert into election_scope values(2,'2',1 ,1)
GO
insert into election_scope values(3,'2',2 ,1)
GO
INSERT INTO registration(registration_id, firstname, middlename, lastname, gender, username, password, dateofbirth, email, phone, mobile, address, country, pincode,access_type,access_value) 
    VALUES(0, 'Ram', '', 'Charan', '', 'itgrids', 'itgrids', '2009-9-10', '', '', '', '', '', '',' ',' ')

GO
insert into party (party_id,long_name,short_name,party_recognization) values(1,'Indian National Congress','INC','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(2,'Bharatiya Janata Party','BJP','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(3,'Bahujan Samaj Party','BSP','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(4,'Communist Party of India','CPI','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(5,'Communist Party of India (Marxist)','CPM','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(6,'Samajwadi Party','SP','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(7,'Samata Party','SP','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(8,'Rashtriya Janata Dal','RJD','NP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(9,'Praja Rajyam Party','PRP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(10,'Telangana Rashtra Samithi','TRS','SP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(11,'Telugu Desam','TDP','SP')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(12,'Lok Satta Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(13,'Independent','IND','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(14,'Pyramid Party of India','PPOI','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(15,'Janata Dal (Secular)','JD(S)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(16,'Bahujan Samaj Party(Ambedkar-Phule)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(17,'Trilinga Praja Pragati Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(18,'Marxist Communist Party of India (S.S.Srivastava)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(19,'Muslim League Kerala State Committee','MUL','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(20,'Bharateeya Sadharma Samsthapana Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(21,'Ajeya Bharat Party (Independent)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(22,'Janata Dal (United)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(23,'Republican Party of India (A)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(24,'Rashtriya Krantikari Samajwadi Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(25,'Great India Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(26,'Mana Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(27,'Ambedkar National Congress','ANC','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(28,'Akhil Bharatiya Jan Sangh','ABJS','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(29,'All India Forward Block','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(30,'Dalita Bahujana Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(31,'Andhra Pradesh Navodaya Praja Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(32,'All India Majlis-E-Ittehadul Muslimeen','AIMIM','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(33,'Praja Shanthi Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(34,'United Women Front','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(35,'Shiv Sena','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(36,'Revolutionary Socialist Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(37,'Samajwadi Janata Party (Rashtriya)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(38,'Republican Party of India (Khobragade)','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(39,'Rashtriya Praja Congress (Secular)','RPC(S)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(40,'Communist Party of India (Marxist)','CPM','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(41,'Majils Bachao Tahreek','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(42,'Kranti Kari Jai Hind Sena','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(43,'Jai Maha Bharath Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(44,'B.C.United Front','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(45,'Rajyadhikara Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(46,'Indian Justice Party','IJP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(47,'Ajeya Bharat Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(48,'Republican Party of India','RPI','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(49,'Praja Bharath Party','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(50,'Praja Party','PP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(51,'Bharatiya Jan Shakti','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(52,'Rayalaseema Rastra Samithi','','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(53,'Bahujan Republican Party','BJRP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(54,'Bharatiya Rashtravadi Paksha','BRP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(55,'Bahujan Samaj Party (Ambedkar)','BSP(A)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(56,'Indigenous Nationalist Party of Twipra','INPT','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(57,'Janata Party','JP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(58,'Lok Jan Shakti Party','LJNSP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(59,'Majlis Bachao Tahreek','MBT','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(60,'Marxist Communist Party of India (S.S. Srivastava)','MCPI(S)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(61,'Mudiraj Rashtriya Samithi','MRS','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(62,'NTR Telugu Desam Party (Lakshmi Parvathi)','NTRTDP(LP)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(63,'Peoples Republican Party','PRBP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(64,'Republican Party of India(A)','RPI(A)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(65,'Republican Party Of India(Khobragade)','RPI(KH)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(66,'Samajwadi Janata Party(Rashtriya)','SJP(R)','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(67,'Telangana Congress Party','TCP','')
GO
insert into party (party_id,long_name,short_name,party_recognization) values(68,'Telangana Praja Party','TGPP','')
GO

INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SIRPUR', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHENNUR', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BELLAMPALL', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANCHERIAL', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ASIFABAD', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( ' KHANPUR', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( ' ADHILABAD', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( ' BOATHAN', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( ' NIRMAL', 2, null, null, 1, 1, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( ' MUDHOLE', 2, null, null, 1, 1, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MUSHEERABAD', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MALAKPET', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'AMBERPET', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KHIARTHABAD', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JUBLEEHILLS', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SANATHNAGAR', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAMPALLY', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KHRAWAN', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GOSHMAHAL', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHARMINAR', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHANDRYANGUTTA', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YAKUTHPURA', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BAHADURPURA', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SECENDRABAD', 2, null, null, 1, 5, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CANTONMENT', 2, null, null, 1, 5, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KORATLA', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JAGITHIAL', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DHARMAPURI', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAMAGUNDAM', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANTAIN', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PEDDAPALLE', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KARIMNAGAR', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHOPPADANDI', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VEMULAWADA', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SIRICILLA', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANAKONDUR', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUZURABAD', 2, null, null, 1, 3, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUSNABAD', 2, null, null, 1, 3, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PINAPAKA', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YELLANDU', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KHAMMAM', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALAIR', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MADHIRA', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'WYRA', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SATHUPALLE', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KOTHAGUDAM', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ASWARAOPETA', 2, null, null, 1, 10, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BHADRACHELAM', 2, null, null, 1, 10, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KONDAGAL', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARAYANPET', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAHABUBNAGAR', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JADCHERLA', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DEVERKADRA', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAKTHAL', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'WANAPARTHY', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GADWAL', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ALAMPUR', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAGARKURNOOL', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ACHAMPETA', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KALWAKURTHY', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SHADNAGAR', 2, null, null, 1, 7, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KOLLAPUR', 2, null, null, 1, 7, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SIDDIPET', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MEDAK', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARAYANKHED', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ANDOLE', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSAPUR', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ZAHIRABAD', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SANGAREDDY', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PATANCHERU', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DUBBAK', 2, null, null, 1, 4, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GAJWEL', 2, null, null, 1, 4, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DEVARAKONDA', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAGARJUN SAGAR', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MIRYALAGUDA', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUZURNAGAR', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KODA', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SURYAPET', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NALGONDA', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MUNUGODU', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BHONGIRI', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAKREKAL', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ALAIR', 2, null, null, 1, 8, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'THUNGATHURTHY', 2, null, null, 1, 8, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ARMUR', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BODHAN', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)  
VALUES( 'JUKKAL', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BANSWADA', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)  
VALUES( 'BALAKONDA', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YELLAREDDY', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KAMAREDDY', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NIZAMBAD [URBAN]', 2, null, null, 1, 2, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NIZAMBAD [RURAL]', 2, null, null, 1, 2, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MEDCHEL', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MALKAJGIRI', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'QUTHBULLAPUR', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'UPPAL', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KUKATPALLY', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'IBRAHIMPATNAM', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'L B NAGAR', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAHESWARAM', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJENDRA NAGAR', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SERLINGAMPALLY', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHEVELLA', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PARIGI', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIKARABAD', 2, null, null, 1, 6, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TANDURU', 2, null, null, 1, 6, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JANGAM', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'STATION GHAPUR', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALAKURTHI', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DORNAKAL', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAHABUBABAD', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSAMPETA', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PARKALA', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'WARANGAL [EAST]', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'WARANGAL [WEST]', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'WARDHANNAPET', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BHUPALAPALLE', 2, null, null, 1, 9, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MULUGU', 2, null, null, 1, 9, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TUNI', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PRATHIPADU', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PITAPURAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KAKINADA [RURAL]', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PEDDAPURAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ANAPARTHY', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KAKINADA [CITY]', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAMACHANDRAPURAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MUMMIDIVARAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'AMALAPURAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAZOL', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GANNAVARAM', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KOTHAKOTA', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANDAPETA', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BURUGUPUDA', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJAMANDRY [CITY]', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJAMANDRY [RURAL]', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JAGGAMPETA', 2, null, null, 1, 14, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAMPACHODAVARAM', 2, null, null, 1, 14, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PEDAKURAPADU', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TADIKONDA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANGALAGIRI', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PONNUR', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VEMURU', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'REPALLE', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TENALI', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BAPATLA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PRATHIPADU', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GUNTUR [EAST]', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GUNTUR [WEST]', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GURAJALA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHILAKALURIPETA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSARAOPETA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VINUKONDA', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SATHENAPALLE', 2, null, null, 1, 17, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MACHERLA', 2, null, null, 1, 17, 1) 
GO








INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TIRIVUR', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NUZIVIDU', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GANNAVARAM', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GUDIWADA', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KIKALURU', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PEDANA', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MACHLIPATNAM', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'AVANIGADDA', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PAMARU', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PENAMALURU', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAYAWADA [EAST]', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAYAWADA [WEST]', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAYAEADA [CENTRAL]', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MYLAVARAM', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NANDIGAMA', 2, null, null, 1, 16, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JAGGAYAPETA', 2, null, null, 1, 16, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KAVALI', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ATMAKUR', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KOVUR', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NELLORE [CITY]', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NELLORE [RURAL]', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SARVEYPALLY', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GUDURU', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SULLURUPETA', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VENKATAGIRI', 2, null, null, 1, 19, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'UDAYAGIRI', 2, null, null, 1, 19, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YERRRAGONDAPALEM', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DARSHI', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PARCHEVUR', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHIRALA', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ADDANKI', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SANTHANUTHALAPADU', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ONGOLE', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KANDAKUR', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KONDAPI', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MARKAPURAM', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GIDDALUR', 2, null, null, 1, 18, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KANIGIRI', 2, null, null, 1, 18, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ITCHAPURAM', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALASA', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TELKKALI', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PATHAPATNAM', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SRIKAKULAM', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'AMUDHALAVALASA', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ETCHERLA', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSANNAPETA', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJAM', 2, null, null, 1, 11, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALAKONDA', 2, null, null, 1, 11, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BHIMILI', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIZAG [EAST]', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIZAG [WEST]', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIZAG [NORTH]', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIZAG [SOUTH]', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GAJUWAKA', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHODAVARAM', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MADUGULA', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ARAKU', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PADERU', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ANAKAPALLE', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PENDURTHI', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YELAMANCHILI', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PAYAKARAOPETA', 2, null, null, 1, 13, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSTPATNAM', 2, null, null, 1, 13, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KURUPAM', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PRAVATHIPURAM', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SALURU', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BOBBILI', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHEEPURAPALLE', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GANAPATHI NAGARAM', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NELLIMARLA', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAYANAGARAM', 2, null, null, 1, 12, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SRUNGAVARAPUKOTA', 2, null, null, 1, 12, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KOVUR', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NIDADAVOLE', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ACHANTA', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALACOLE', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NARSAPURAM', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BHIMAVARAM', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'UNDI', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TANUKU', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TADEPALLIGUDEM', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'UNGUTUR', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DENDULURU', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ELURU', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GOPALAPURAM', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'POLAVARAM', 2, null, null, 1, 15, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHINTALAPUDI', 2, null, null, 1, 15, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAYADURGA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'URAVAKONDA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GUNTAKAL', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TADIPATRI', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SINGANAMALA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ANANTAPUR', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KALYAN DURGA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAPTADU', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MADAKASIRA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HINDUPUR', 2, null, null, 1, 22, 1) 

GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PENUKONDA', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PUTTAPATRI', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DHARMAVARAM', 2, null, null, 1, 22, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KADIRI', 2, null, null, 1, 22, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'THAMBALAPALLE', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PILERU', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MADANAPALLE', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PUNGANUR', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHANDRAGIRI', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'THIRUPATHI', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SRIKALAHASTI', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SATYAVEDU', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAGIRI', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GANGADHRA', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHITOOR', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KUPPAM', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PUTHALAPATU', 2, null, null, 1, 23, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PALAMANER', 2, null, null, 1, 23, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BADWEL', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJAMPETA', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KADAPA', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KODURU', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAYACHOT', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PULIVENDULLA', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KAMALAPURAM', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'JAMMALAMADUGU', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PRODDUTUR', 2, null, null, 1, 20, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MYDKUR', 2, null, null, 1, 20, 1) 
GO



INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ALLAGADDA', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SRISHAILAM', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NANDIKOTKUR', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KURNOOL', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PANYAM', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NANDYALA', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DHONE', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PATTIKONDA', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KODMUR', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ADHONE', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YAMMIGANUR', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANTRALAYAM', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BANGANAPALLE', 2, null, null, 1, 21, 1) 
GO
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'ALURU', 2, null, null, 1, 21, 1) 
GO
insert into cadre_level values(1,'COUNTRY');
GO
insert into cadre_level values(2,'STATE');
GO
insert into cadre_level values(3,'DISTRICT');
GO
insert into cadre_level values(4,'CONSTITUENCY');
GO
insert into cadre_level values(5,'MANDAL');
GO
insert into cadre_level values(6,'VILLAGE');
GO