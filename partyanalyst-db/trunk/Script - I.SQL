
CREATE TABLE alliance_groups ( 
	alliance_group_id	bigint(15) AUTO_INCREMENT NOT NULL,
	party_id         	bigint(15) NOT NULL,
	group_id         	bigint(15) NOT NULL,
	PRIMARY KEY(alliance_group_id) )
GO
CREATE TABLE booth ( 
	booth_id       	bigint(15) AUTO_INCREMENT NOT NULL,
	part_no        	varchar(50) NULL,
	part_name      	varchar(250) NULL,
	location       	varchar(250) NULL,
	village_covered	varchar(500) NULL,
	tehsil_id      	bigint(15) NOT NULL,
	male_voters    	bigint(15) NULL,
	female_voters  	bigint(15) NULL,
	total_voters   	bigint(15) NULL,
	constituency_id bigint(15) NULL,
	year bigint(15) NULL,
	PRIMARY KEY(booth_id) )
GO
CREATE TABLE booth_constituency_election ( 
	booth_constituency_election_id	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_id                      	bigint(15) NOT NULL,
	consti_elec_id                	bigint(15) NOT NULL,
	PRIMARY KEY(booth_constituency_election_id) )
GO
CREATE TABLE booth_result ( 
	booth_result_id               	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_constituency_election_id	bigint(15) NULL,
	valid_votes                   	bigint(15) NULL,
	rejected_votes                	bigint(15) NULL,
	tendered_votes                	bigint(15) NULL,
	PRIMARY KEY(booth_result_id) )
GO
CREATE TABLE booth_village_census ( 
	booth_village_census_id	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_id               	bigint(15) NOT NULL,
	village_census_code    	bigint(15) NULL,
	PRIMARY KEY(booth_village_census_id) )
GO
CREATE TABLE cadre ( 
	cadre_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	user_id          	bigint(15) NOT NULL,
	first_name       	varchar(40) NOT NULL,
	middle_name       	varchar(40) NULL,
	last_name        	varchar(40) NOT NULL,
	mobile_no        	varchar(11) NOT NULL,
	email            	varchar(100) NULL,
	gender           	varchar(6) NOT NULL,
	state_id         	bigint(15) NOT NULL,
	district_id      	bigint(15) NOT NULL,
	mandal_id        	bigint(15) NOT NULL,
	village_id       	bigint(15) NOT NULL,
	booth_id         	bigint(15) NULL,
	cadre_level_id   	bigint(15) NULL,
	cadre_level_value	bigint(15) NULL,
	constituency_id  	bigint(15) NULL,
	PRIMARY KEY(cadre_id) )
GO
CREATE TABLE cadre_level ( 
	cadre_level_id	bigint(15) AUTO_INCREMENT NOT NULL,
	level         	varchar(25) NOT NULL,
	PRIMARY KEY(cadre_level_id) )
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
	is_delete    	varchar(20) NULL,
	PRIMARY KEY(candidate_id) )
GO
CREATE TABLE candidate_booth_result ( 
	candidate_booth_result_id     	bigint(15) AUTO_INCREMENT NOT NULL,
	nomination_id                 	bigint(15) NOT NULL,
	booth_constituency_election_id	bigint(15) NOT NULL,
	votes_earned                  	bigint(15) NULL,
	percentage                    	varchar(25) NULL,
	PRIMARY KEY(candidate_booth_result_id) )
GO
CREATE TABLE candidate_consolidation_task ( 
	candidate_consolidation_task_id	bigint(15) AUTO_INCREMENT NOT NULL,
	candidate_id                   	bigint(15) NULL,
	performed_date                 	timestamp NULL,
	PRIMARY KEY(candidate_consolidation_task_id) )
GO
CREATE TABLE candidate_result ( 
	candidate_result_id	bigint(15) AUTO_INCREMENT NOT NULL,
	nomination_id      	bigint(15) NOT NULL,
	votes_earned       	double(10,0) NULL,
	rank               	bigint(15) NULL,
	votes_percentage   	varchar(15) NULL,
	PRIMARY KEY(candidate_result_id) )
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
	PRIMARY KEY(census_id) )
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
	PRIMARY KEY(constituency_id) )
GO
CREATE TABLE constituency_election ( 
	consti_elec_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	constituency_id	bigint(15) NOT NULL,
	election_id    	bigint(15) NOT NULL,
	election_date  	date NULL,
	PRIMARY KEY(consti_elec_id) )
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
	voting_percentage       varchar(15) NULL,
	PRIMARY KEY(consti_elec_result_id) )
GO
CREATE TABLE country ( 
	country_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	country_name	varchar(100) NOT NULL,
	capital     	varchar(100) NULL,
	iso_code    	varchar(25) NULL,
	PRIMARY KEY(country_id) )
GO
CREATE TABLE delimitation_constituency ( 
	delimitation_constituency_id	bigint(15) AUTO_INCREMENT NOT NULL,
	constituency_id             	bigint(15) NOT NULL,
	year                        	integer(15) NOT NULL,
	constituency_no             	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_constituency_id) )
GO
CREATE TABLE delimitation_constituency_assembly_details ( 
	dca_id                      	bigint(15) NOT NULL,
	delimitation_constituency_id	bigint(15) NOT NULL,
	constituency_id             	bigint(15) NOT NULL,
	PRIMARY KEY(dca_id) )
GO
CREATE TABLE delimitation_constituency_mandal_details ( 
	dcm_id                      	bigint(15) NOT NULL,
	delimitation_constituency_id	bigint(15) NOT NULL,
	tehsil_id                   	bigint(15) NOT NULL,
	is_partial                  	bit(15) NOT NULL,
	PRIMARY KEY(dcm_id) )
GO
CREATE TABLE delimitation_district ( 
	delimitation_district_id	bigint(15) AUTO_INCREMENT NOT NULL,
	year                    	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_district_id) )
GO
CREATE TABLE delimitation_district_data ( 
	delimitation_district_data_id	bigint(15) AUTO_INCREMENT NOT NULL,
	district_id                  	bigint(15) NOT NULL,
	new_district_no              	bigint(15) NOT NULL,
	delimitation_district_id     	bigint(15) NOT NULL,
	PRIMARY KEY(delimitation_district_data_id) )
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
	PRIMARY KEY(district_id) )
GO
CREATE TABLE election ( 
	election_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	election_scope_id	bigint(15) NULL,
	notification_date	date NULL,
	start_date       	date NULL,
	end_date         	varchar(25) NULL,
	election_year    	varchar(25) NULL,
	PRIMARY KEY(election_id) )
GO
CREATE TABLE election_alliance ( 
	alliance_id	bigint(15) NOT NULL,
	election_id	bigint(15) NOT NULL,
	party_id   	bigint(15) NOT NULL,
	PRIMARY KEY(alliance_id) )
GO
CREATE TABLE election_alliances ( 
	election_alliance_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_id         	bigint(15) NOT NULL,
	group_id            	bigint(15) NOT NULL,
	PRIMARY KEY(election_alliance_id) )
GO
CREATE TABLE election_scope ( 
	election_scope_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_type_id 	bigint(15) NOT NULL,
	state_id         	bigint(15) NULL,
	country_id       	bigint(15) NOT NULL,
	PRIMARY KEY(election_scope_id) )
GO
CREATE TABLE election_type ( 
	election_type_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_type   	varchar(25) NULL,
	scope           	varchar(25) NULL,
	PRIMARY KEY(election_type_id) )
GO
CREATE TABLE group_member ( 
	group_member_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_group_id  	bigint(15) NULL,
	firstname      	varchar(40) NULL,
	middlename     	varchar(40) NULL,
	lastname       	varchar(40) NULL,
	gender         	varchar(25) NULL,
	email          	varchar(100) NULL,
	phone          	varchar(25) NULL,
	country        	varchar(25) NULL,
	pincode        	varchar(25) NULL,
	PRIMARY KEY(group_member_id) )
GO
CREATE TABLE groups ( 
	group_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	group_name	varchar(25) NULL,
	comments  	varchar(100) NULL,
	PRIMARY KEY(group_id) )
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
	PRIMARY KEY(nomination_id) )
GO
CREATE TABLE nomination_history ( 
	nomination_history_id          	bigint(15) AUTO_INCREMENT NOT NULL,
	candidate_consolidation_task_id	bigint(15) NULL,
	candidate_id                   	bigint(15) NULL,
	nomination_id                  	bigint(15) NULL,
	date                           	timestamp NULL,
	PRIMARY KEY(nomination_history_id) )
GO
CREATE TABLE party ( 
	party_id           	bigint(15) AUTO_INCREMENT NOT NULL,
	long_name          	varchar(100) NULL,
	short_name         	varchar(25) NULL,
	symbol             	varchar(25) NULL,
	address            	varchar(300) NULL,
	comments           	varchar(300) NULL,
	party_recognization	varchar(25) NULL,
	PRIMARY KEY(party_id) )
GO
CREATE TABLE party_rebel ( 
	party_rebel_id	bigint(15) AUTO_INCREMENT NOT NULL,
	election_id   	bigint(15) NOT NULL,
	party_id      	bigint(15) NOT NULL,
	PRIMARY KEY(party_rebel_id) )
GO
CREATE TABLE party_rebel_candidate ( 
	party_rebel_candidate_id	bigint(15) AUTO_INCREMENT NOT NULL,
	party_rebel_id          	bigint(15) NOT NULL,
	candidate_id            	bigint(15) NOT NULL,
	PRIMARY KEY(party_rebel_candidate_id) )
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
	PRIMARY KEY(PollingStationDetails_id) )
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
	PRIMARY KEY(registration_id) )
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
	admin_control           varchar(5),
	PRIMARY KEY(state_id) )
GO
CREATE TABLE tehsil ( 
	tehsil_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	tehsil_name	varchar(100) NULL,
	district_id	bigint(15) NULL,
	tehsil_code	bigint(15) NULL,
	start_date 	date NULL,
	deform_date	varchar(25) NULL,
	census_year	int(11) NULL,
	PRIMARY KEY(tehsil_id) )
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
	)
GO
CREATE TABLE township ( 
	township_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	township_name	varchar(100) NULL,
	township_code	bigint(15) COMMENT 'township_code is census code for village or town'  NULL,
	tehsil_id    	bigint(15) NULL,
	township_type	varchar(25) COMMENT 'It indicates either it is TOWN or VILLAGE'  NULL,
	census_year  	int(11) NULL,
	greater_town 	char(1) NULL,
	PRIMARY KEY(township_id) )
GO
CREATE TABLE user_group ( 
	user_group_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	registration_id	bigint(15) NULL,
	group_name     	varchar(50) NULL,
	PRIMARY KEY(user_group_id) )
GO
CREATE TABLE ward ( 
	ward_id    	bigint(15) AUTO_INCREMENT NOT NULL,
	ward_name  	varchar(100) NULL,
	ward_code  	bigint(15) NULL,
	township_id	bigint(15) NOT NULL,
	census_year	int(11) NULL,
	PRIMARY KEY(ward_id) )
GO
ALTER TABLE booth
	ADD CONSTRAINT fk_constituency_booth
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
GO
ALTER TABLE census_master
	ADD CONSTRAINT census_id 	UNIQUE (census_id)
GO
ALTER TABLE booth_constituency_election
	ADD CONSTRAINT fk_booth_booth_constituency_election 	FOREIGN KEY(booth_id) 	REFERENCES booth(booth_id)
GO
ALTER TABLE booth_village_census
	ADD CONSTRAINT fk_booth_booth_village_cencus 	FOREIGN KEY(booth_id) 	REFERENCES booth(booth_id)
GO
ALTER TABLE candidate_booth_result
	ADD CONSTRAINT fk_booth_constituency_election_candidate_booth_result 	FOREIGN KEY(booth_constituency_election_id) 	REFERENCES booth_constituency_election(booth_constituency_election_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE booth_result
	ADD CONSTRAINT fk_booth_constituency_election_booth_result 	FOREIGN KEY(booth_constituency_election_id) 	REFERENCES booth_constituency_election(booth_constituency_election_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_caderlevel_cader 	FOREIGN KEY(cadre_level_id) 	REFERENCES cadre_level(cadre_level_id)
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_candidate_nomination 	FOREIGN KEY(candidate_id) 	REFERENCES candidate(candidate_id)
GO
ALTER TABLE nomination_history
	ADD CONSTRAINT fk_candidate_nomination_history 	FOREIGN KEY(candidate_id) 	REFERENCES candidate(candidate_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE candidate_consolidation_task
	ADD CONSTRAINT fk_candidate_candidate_consolidation_task 	FOREIGN KEY(candidate_id) 	REFERENCES candidate(candidate_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE party_rebel_candidate
	ADD CONSTRAINT fk_candidate_rebel 	FOREIGN KEY(party_rebel_id) 	REFERENCES candidate(candidate_id)
GO
ALTER TABLE nomination_history
	ADD CONSTRAINT fk_candidate_consolidation_task_nomination_history 	FOREIGN KEY(candidate_consolidation_task_id) 	REFERENCES candidate_consolidation_task(candidate_consolidation_task_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE constituency_election
	ADD CONSTRAINT fk_constituency_constituency_election 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id)
GO
ALTER TABLE pollingstationdetails
	ADD CONSTRAINT fk_constituency_PollingStationDetails 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_constituency 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id)
GO
ALTER TABLE delimitation_constituency
	ADD CONSTRAINT fk_constituency_dc 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id)
GO
ALTER TABLE delimitation_constituency_assembly_details
	ADD CONSTRAINT fk_constituency_dcad 	FOREIGN KEY(constituency_id) 	REFERENCES constituency(constituency_id)
GO
ALTER TABLE booth_constituency_election
	ADD CONSTRAINT fk_constituency_election_booth_constituency_election 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id)
GO
ALTER TABLE constituency_election_result
	ADD CONSTRAINT fk_constituency_election_constituency_election_result 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id)
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_constituency_election_nomination 	FOREIGN KEY(consti_elec_id) 	REFERENCES constituency_election(consti_elec_id)
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_country_election_scope 	FOREIGN KEY(country_id) 	REFERENCES country(country_id)
GO
ALTER TABLE state
	ADD CONSTRAINT fk_country_state 	FOREIGN KEY(country_id) 	REFERENCES country(country_id)
GO
ALTER TABLE delimitation_constituency_mandal_details
	ADD CONSTRAINT fk_dc_dcmd 	FOREIGN KEY(delimitation_constituency_id) 	REFERENCES delimitation_constituency(delimitation_constituency_id)
GO
ALTER TABLE delimitation_constituency_assembly_details
	ADD CONSTRAINT fk_dc_dca 	FOREIGN KEY(delimitation_constituency_id) 	REFERENCES delimitation_constituency(delimitation_constituency_id)
GO
ALTER TABLE delimitation_district_data
	ADD CONSTRAINT fk_delimitation_district_delimitation_district_data 	FOREIGN KEY(delimitation_district_id) 	REFERENCES delimitation_district(delimitation_district_id)
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_district_census 	FOREIGN KEY(district_id) 	REFERENCES district(district_id)
GO
ALTER TABLE delimitation_district_data
	ADD CONSTRAINT fk_district_delimitation_district_data 	FOREIGN KEY(district_id) 	REFERENCES district(district_id)
GO
ALTER TABLE tehsil
	ADD CONSTRAINT fk_district_mandal 	FOREIGN KEY(district_id) 	REFERENCES district(district_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_district 	FOREIGN KEY(district_id) 	REFERENCES district(district_id)
GO
ALTER TABLE constituency_election
	ADD CONSTRAINT fk_election_constituency_election 	FOREIGN KEY(election_id) 	REFERENCES election(election_id)
GO
ALTER TABLE election_alliance
	ADD CONSTRAINT fk_alliance_election 	FOREIGN KEY(election_id) 	REFERENCES election(election_id)
GO
ALTER TABLE pollingstationdetails
	ADD CONSTRAINT fk_election_PollingStationDetails 	FOREIGN KEY(election_id) 	REFERENCES election(election_id)
GO
ALTER TABLE election_alliances
	ADD CONSTRAINT fk_election_alliances 	FOREIGN KEY(election_id) 	REFERENCES election(election_id)
GO
ALTER TABLE party_rebel
	ADD CONSTRAINT fk_rebel_election 	FOREIGN KEY(election_id) 	REFERENCES election(election_id)
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_election_type_election_scope 	FOREIGN KEY(election_type_id) 	REFERENCES election_type(election_type_id)
GO
ALTER TABLE alliance_groups
	ADD CONSTRAINT fk_alliance_groups_groups 	FOREIGN KEY(group_id) 	REFERENCES groups(group_id)
GO
ALTER TABLE election_alliances
	ADD CONSTRAINT fk_election_alliance_groups 	FOREIGN KEY(group_id) 	REFERENCES groups(group_id)
GO
ALTER TABLE candidate_booth_result
	ADD CONSTRAINT fk_nomination_booth_result 	FOREIGN KEY(nomination_id) 	REFERENCES nomination(nomination_id)
GO
ALTER TABLE candidate_result
	ADD CONSTRAINT fk_nomination_candidate_result 	FOREIGN KEY(nomination_id) 	REFERENCES nomination(nomination_id)
GO
ALTER TABLE nomination_history
	ADD CONSTRAINT fk_nomination_nomination_history 	FOREIGN KEY(nomination_id) 	REFERENCES nomination(nomination_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE election_alliance
	ADD CONSTRAINT fk_alliance_party 	FOREIGN KEY(party_id) 	REFERENCES party(party_id)
GO
ALTER TABLE nomination
	ADD CONSTRAINT fk_party_nomination 	FOREIGN KEY(party_id) 	REFERENCES party(party_id)
GO
ALTER TABLE alliance_groups
	ADD CONSTRAINT fk_alliance_groups_parties 	FOREIGN KEY(party_id) 	REFERENCES party(party_id)
GO
ALTER TABLE party_rebel
	ADD CONSTRAINT fk_rebel_party 	FOREIGN KEY(party_id) 	REFERENCES party(party_id)
GO
ALTER TABLE party_rebel_candidate
	ADD CONSTRAINT fk_party_rebel 	FOREIGN KEY(party_rebel_id) 	REFERENCES party_rebel(party_rebel_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_regis_cadre 	FOREIGN KEY(user_id) 	REFERENCES registration(registration_id)
GO
ALTER TABLE user_group
	ADD CONSTRAINT fk_registration_user_group 	FOREIGN KEY(registration_id) 	REFERENCES registration(registration_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_state_census 	FOREIGN KEY(state_id) 	REFERENCES state(state_id)
GO
ALTER TABLE district
	ADD CONSTRAINT fk_state_district 	FOREIGN KEY(state_id) 	REFERENCES state(state_id)
GO
ALTER TABLE election_scope
	ADD CONSTRAINT fk_state_election_scope 	FOREIGN KEY(state_id) 	REFERENCES state(state_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_state 	FOREIGN KEY(state_id) 	REFERENCES state(state_id)
GO
ALTER TABLE booth
	ADD CONSTRAINT fk_tehsil_booth 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_tehsil_census 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE township
	ADD CONSTRAINT fk_tehsil_township 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_tehsil 	FOREIGN KEY(mandal_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE delimitation_constituency_mandal_details
	ADD CONSTRAINT fk_del_tehsil_constituency 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE delimitation_constituency_mandal_details
	ADD CONSTRAINT fk_dcmd_tehsil 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE delimitation_constituency_mandal_details
	ADD CONSTRAINT fk_tehsil_dcmd 	FOREIGN KEY(tehsil_id) 	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_town_census 	FOREIGN KEY(township_id) 	REFERENCES township(township_id)
GO
ALTER TABLE ward
	ADD CONSTRAINT fk_township_ward 	FOREIGN KEY(township_id) 	REFERENCES township(township_id)
GO
ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_township 	FOREIGN KEY(village_id) 	REFERENCES township(township_id)
GO
ALTER TABLE group_member
	ADD CONSTRAINT fk_user_group_group_member 	FOREIGN KEY(user_group_id) 	REFERENCES user_group(user_group_id) 	ON DELETE RESTRICT  	ON UPDATE RESTRICT 
GO
ALTER TABLE census_master
	ADD CONSTRAINT fk_ward_census 	FOREIGN KEY(ward_id) 	REFERENCES ward(ward_id)
GO

ALTER TABLE constituency_election add reservation_zone varchar(10)
GO

insert into country values(1,'India','Delhi','IN')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (1,'Andhra Pradesh',' Hyderabad',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (2,'Arunachal Pradesh',' Itanagar',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (3,'Assam',' Dispur',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (4,'Bihar',' Patna',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (5,'Chattisgarh',' Raipur',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (6,'Goa',' Panaji',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (7,'Gujarat',' Gandhinagar',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (8,'Haryana',' Chandigarh',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (9,'Himachal Pradesh',' Shimla',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (10,'Jammu & Kashmir',' Jammu/Srinagar',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (11,'Jharkhand',' Ranchi',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (12,'Karnataka',' Bangalore',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (13,'Kerala',' Trivandrum',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (14,'Madhya Pradesh',' Bhopal',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (15,'Maharashtra',' Mumbai',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (16,'Manipur',' Imphal',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (17,'Meghalaya',' Shillong',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (18,'Mizoram',' Aizawl',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (19,'Nagaland',' Kohima',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (20,'Orissa',' Bhubaneswar',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (21,'Punjab',' Chandigarh',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (22,'Rajasthan',' Jaipur',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (23,'Sikkim',' Gangtok',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (24,'Tamil Nadu',' Chennai',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (25,'Tripura',' Agartala',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (26,'Uttaranchal',' Dehradun',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (27,'Uttar Pradesh',' Lucknow',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (28,'West Bengal',' Kolkata',1,'S')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (29,'Andaman & Nicobar Islands',' Andaman & Nicobar Islands',1,'U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (30,'Chandigarh',' Chandigarh',1,'U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (31,'Dadar & Nagar Haveli',' Dadar & Nagar Haveli',1,'U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (32,'Daman & diu',' Daman & diu',1,'U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (33,'Delhi','Delhi',1,'S/U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (34,'Lakshadweep','Lakshadweep',1,'U')
GO
insert into state (state_id,state_name,admin_capital,country_id,admin_control) values (35,'Puducherry','Puducherry',1,'S/U')
GO
insert into election_type values(1,'Parliament','Country')
GO
insert into election_type values(2,'Assembly','State')
GO
insert into election_type values(3,'MPTC','State')
GO
insert into election_type values(4,'ZPTC','State')
GO
insert into election_scope values(1,'null',1 ,1)
GO
insert into election_scope values(2,'2',1 ,1)
GO
insert into election_scope values(3,'3',1 ,1)
GO
insert into election_scope values(4,'4',1 ,1)
GO
insert into party (party_id,long_name,short_name,party_recognization) values(1,'Ajeya Bharat Party','AJBP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(2,'Ajeya Bharat Party (Independent)','','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(3,'Akhil Bharatiya Jan Sangh','ABJS','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(4,'All India Forward Block','AIFB','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(5,'All India Majlis-E-Ittehadul Muslimeen','AIMIM','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(6,'Ambedkar National Congress','ANC','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(7,'Andhra Pradesh Navodaya Praja Party','APNPP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(8,'B.C.United Front','BCUF','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(9,'Bahujan Republican Party','BJRP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(10,'Bahujan Samaj Party','BSP','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(11,'Bahujan Samaj Party (Ambedkar)','BSP(A)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(12,'Bahujan Samaj Party(Ambedkar-Phule)','BSP(AP)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(13,'Bharateeya Sadharma Samsthapana Party','BHSASP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(14,'Bharatiya Jan Shakti','BJSH','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(15,'Bharatiya Janata Party','BJP','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(16,'Bharatiya Rashtravadi Paksha','BRP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(17,'Communist Party of India','CPI','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(18,'Communist Party of India (Marxist)','CPM','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(19,'Communist Party of India (Marxist-Leninist) (Liberation)','CPI(ML)(L)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(20,'Dalita Bahujana Party','DABAP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(21,'Great India Party','GRIP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(22,'Independent','IND','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(23,'Indian Justice Party','IJP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(24,'Indian National Congress','INC','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(25,'Indigenous Nationalist Party of Twipra','INPT','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(26,'Jai Maha Bharath Party','JMBP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(27,'Janata Dal (Secular)','JD(S)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(28,'Janata Dal (United)','JD(U)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(29,'Janata Party','JP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(30,'Kranti Kari Jai Hind Sena','KKJHS','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(31,'Lok Jan Shakti Party','LJNSP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(32,'Lok Satta Party','LSP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(33,'Majlis Bachao Tahreek','MBT','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(34,'Mana Party','MANP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(35,'Marxist Communist Party of India (S.S. Srivastava)','MCPI(S)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(36,'Marxist Communist Party of India (S.S.Srivastava)','','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(37,'Mudiraj Rashtriya Samithi','MRS','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(38,'Muslim League Kerala State Committee','MUL','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(39,'NTR Telugu Desam Party (Lakshmi Parvathi)','NTRTDP(LP)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(40,'Peoples Republican Party','PRBP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(41,'Praja Bharath Party','PBHP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(42,'Praja Party','PP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(43,'Praja Rajyam Party','PRP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(44,'Praja Shanthi Party','PRSHP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(45,'Pyramid Party of India','PPOI','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(46,'Rajyadhikara Party','RDHP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(47,'Rashtriya Janata Dal','RJD','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(48,'Rashtriya Krantikari Samajwadi Party','RKSP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(49,'Rashtriya Praja Congress (Secular)','RPC(S)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(50,'Rayalaseema Rastra Samithi','RRS','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(51,'Republican Party of India','RPI','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(52,'Republican Party of India (A)','RPI(A)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(53,'Republican Party of India (Khobragade)','RPI(KH)','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(54,'Revolutionary Socialist Party','RSP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(55,'Samajwadi Janata Party (Rashtriya)','SJP(R)','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(56,'Samajwadi Party','SP','NP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(57,'Samata Party','SAP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(58,'Shiv Sena','SHS','SP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(59,'Telangana Congress Party','TCP','SP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(60,'Telangana Praja Party','TGPP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(61,'Telangana Rashtra Samithi','TRS','SP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(62,'Telugu Desam','TDP','SP')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(63,'Trilinga Praja Pragati Party','TPPP','')  
GO
insert into party (party_id,long_name,short_name,party_recognization) values(64,'United Women Front','UWF','')  
GO

//PArty Flag Modificaton

ALTER TABLE party
ADD (	party_logo	varchar(25),
 	party_flag	varchar(25) );
go


update  party 
set symbol="Hand",party_flag="INC.png" where party_id=24
go
update  party 
set symbol="Bicycle",party_flag="TDP.PNG" where party_id=62
go
update  party 
set symbol="Railway Engine",party_flag="PRP.png",party_recognization="SP" where party_id=43
go
update  party 
set symbol="Lotus",party_flag="BJP.png" where party_id=15
go
update  party 
set symbol="Ears of maize & sickle",party_flag="CPI.png" where party_id=17
go
update  party 
set symbol="Hammer, Sickle and Star",party_flag="CPM.png" where party_id=18
go
update  party 
set symbol="Flag with Three Stars",party_flag="CPIML.png",party_recognization="NP" where party_id=19
go
update  party 
set symbol="Elephant",party_flag="BSP.png" where party_id=10
go
update  party 
set symbol="Hurricane Lamp",party_flag="RJD.png" where party_id=47
go
update  party 
set symbol="Car",party_flag="TRS.png" where party_id=61
go
update  party 
set symbol="Whistle",party_flag="LSP.png",party_recognization="SP" where party_id=32
go
update  party 
set symbol="Kite",party_flag="AIMIM.png",party_recognization="SP" where party_id=5
go


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
insert into cadre_level values(7,'MUNICIPAL-CORP-GMC');
GO
INSERT INTO cadre_level VALUES ('8', 'WARD')
GO
INSERT INTO cadre_level VALUES ('9', 'BOOTH')


INSERT INTO groups(group_id, group_name, comments)
  VALUES(1, 'MahaKutami', 'TDP & allainces')
GO
INSERT INTO groups(group_id, group_name, comments)
  VALUES(2, 'Congress & AMIM', 'INC & Muslim patiesr')
GO
INSERT INTO groups(group_id, group_name, comments)
  VALUES(3, 'Congress Kutami', 'INC & alliances')
GO
INSERT INTO groups(group_id, group_name, comments)
  VALUES(4, 'TDP & BJP', 'TDP & BJP')
GO
INSERT INTO groups(group_id, group_name, comments)
  VALUES(5, 'PRP & ManaParty', 'PRP & ManaParty')
GO

INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(1, 62, 1)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(2, 61, 1)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(3, 18, 1)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(4,17, 1)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(5, 33, 1)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(6, 24, 2)
GO

INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(7, 15, 4)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(8, 62, 4)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(9, 24, 3)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(10, 17, 3)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(11, 18, 3)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(12, 61, 3)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(13, 34, 5)
GO
INSERT INTO alliance_groups(alliance_group_id, party_id, group_id)
  VALUES(14, 43, 5)
GO