
CREATE TABLE user_event ( 
    user_event_id	bigint(15) AUTO_INCREMENT NOT NULL,
    user_id      	bigint(15) NOT NULL,
    description  	varchar(300) NULL,
    location_type	varchar(100) NOT NULL,
    location_id  	bigint(15) NOT NULL,
    start_date   	timestamp NOT NULL,
    end_date     	timestamp NOT NULL,
    title        	varchar(100) NOT NULL,
    is_deleted          varchar(6) NULL,
    PRIMARY KEY(user_event_id)
)
GO

CREATE TABLE user_events_organizer ( 
    user_events_organizer_id	bigint(15) AUTO_INCREMENT NOT NULL,
    user_event_id          	bigint(15) NULL,
    cadre_id                	bigint(15) NULL,
    PRIMARY KEY(user_events_organizer_id)
)
GO

CREATE TABLE event_action_plan ( 
    event_action_plan_id	bigint(15) AUTO_INCREMENT NOT NULL,
    action              	varchar(250) NOT NULL,
    user_event_id	 bigint(15) NOT NULL,
    target_date         	date NOT NULL,
    PRIMARY KEY(event_action_plan_id)
)
GO

CREATE TABLE event_action_organizer ( 
    event_action_organizer_id	bigint(15) AUTO_INCREMENT NOT NULL,
    cadre_id                 	bigint(15) NOT NULL,
    event_action_plan_id     	bigint(15) NOT NULL,
    PRIMARY KEY(event_action_organizer_id)
)
GO

CREATE TABLE user_imp_dates ( 
    user_imp_dates_id             	bigint(15) AUTO_INCREMENT NOT NULL,
    user_id                       	bigint(15) NOT NULL,
    title                         	varchar(100) NOT NULL,
    description                   	varchar(300) NULL,
    effective_date                	date NOT NULL,
    till_date                     	date NOT NULL,
    rec_freq_type                 	varchar(7) NOT NULL,
    PRIMARY KEY(user_imp_dates_id)
)
GO

CREATE TABLE party_important_date ( 
    party_important_date_id	bigint(15) AUTO_INCREMENT NOT NULL,
    party_id               	bigint(15) NOT NULL,
    important_date         	date NOT NULL,
    title                  	varchar(100) NOT NULL,
    importance             	varchar(300) NULL,
    recursive              	char(1) NOT NULL,
    recursive_frequency    	varchar(25) NULL,
    PRIMARY KEY(party_important_date_id)
)
GO

ALTER TABLE registration ADD COLUMN party_id bigint(15)
GO

ALTER TABLE registration ADD COLUMN include_party_imp_date_status VARCHAR(50)
GO

ALTER TABLE user_imp_dates
       ADD CONSTRAINT fk_user_imp_dates_user
       FOREIGN KEY(user_id)
       REFERENCES registration(registration_id)
GO

ALTER TABLE user_event
    ADD CONSTRAINT fk_registration_user_event
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
GO
ALTER TABLE user_events_organizer
    ADD CONSTRAINT fk_user_event_user_events_organizer
	FOREIGN KEY(user_event_id)
	REFERENCES user_event(user_event_id)
GO
ALTER TABLE event_action_plan
    ADD CONSTRAINT fk_user_event_event_action_plan
	FOREIGN KEY(user_event_id)
	REFERENCES user_event(user_event_id)
GO
ALTER TABLE event_action_organizer
    ADD CONSTRAINT fk_event_action_plan_event_action_organizer
	FOREIGN KEY(event_action_plan_id)
	REFERENCES event_action_plan(event_action_plan_id)
GO
ALTER TABLE event_action_organizer
    ADD CONSTRAINT fk_cadre_event_action_organizer
	FOREIGN KEY(cadre_id)
	REFERENCES cadre(cadre_id)
GO
ALTER TABLE party_important_date
    ADD CONSTRAINT fk_party_party_important_date
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
GO
CREATE TABLE booth_constituency_election_voter ( 
	booth_constituency_election_voter_id	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_constituency_election_id      	bigint(15) NULL,
	voter_id                            	bigint(15) NULL,
	PRIMARY KEY(booth_constituency_election_voter_id)
)
GO
CREATE TABLE hamlet ( 
	hamlet_id     	bigint(15) AUTO_INCREMENT NOT NULL,
	hamlet_code   	varchar(25) NULL,
	hamlet_name   	varchar(25) NULL,
	township_id   	bigint(15) NOT NULL,
	panchayat_code	varchar(25) NULL,
	panchayat_name	varchar(25) NULL,
	mpp_code      	varchar(25) NULL,
	PRIMARY KEY(hamlet_id)
)
GO
CREATE TABLE voter ( 
	voter_id           	bigint(15) AUTO_INCREMENT NOT NULL,
	house_no           	varchar(25) NULL,
	first_name         	varchar(50) NULL,
	last_name          	varchar(50) NULL,
	relationship_type  	varchar(50) NULL,
	relative_first_name	varchar(50) NULL,
	relative_last_name 	varchar(50) NULL,
	cast               	varchar(25) NULL,
	cast_catagery      	varchar(25) NULL,
	cast_subcatagery   	varchar(25) NULL,
	gender             	varchar(25) NULL,
	age                	varchar(25) NULL,
	voter_id_card_no   	varchar(25) NULL,
	hamlet_id          	varchar(25) NULL,
	insertion_date     	varchar(25) NULL,
	local_area         	varchar(50) NULL,
	PRIMARY KEY(voter_id)
)
GO
CREATE TABLE  epaper  ( 
	 epaper_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	 name           	varchar(200) NULL,
	 description    	varchar(200) NULL,
	 classification 	varchar(200) NULL,
	 country_id     	bigint(15) NULL,
	 country_url    	varchar(200) NULL,
	 state_id       	bigint(15) NULL,
	 state_url      	varchar(200) NULL,
	 image          	varchar(200) NULL,
	 language       	varchar(200) NULL,
	PRIMARY KEY( epaper_id )
)
GO
CREATE TABLE  epaper_url  ( 
	 epaperurl_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	 epaper_id    	bigint(15) NULL,
	 district_id  	bigint(15) NULL,
	 district_url 	varchar(200) NULL,
	PRIMARY KEY( epaperurl_id )
)
GO

CREATE TABLE influencing_people ( 
influencing_people_id         bigint(15) AUTO_INCREMENT NOT NULL,
first_name                     varchar(25) NULL,
last_name                     varchar(25) NULL,
influencing_scope             varchar(25) NULL,
position                       varchar(50) NULL,
party_id                       bigint(15) NULL,
caste                         varchar(50) NULL,
occupation                     varchar(50) NULL,
phone                         varchar(25) NULL,
influencing_people_position_id bigint(15) NULL,
hamlet_id                     bigint(15) NULL,
PRIMARY KEY(influencing_people_id)
)
GO
CREATE TABLE influencing_people_position ( 
influencing_people_position_id bigint(15) AUTO_INCREMENT NOT NULL,
position_type                 varchar(50) NULL,
position_desc                 varchar(100) NULL,
upadated_date                 timestamp NULL,
PRIMARY KEY(influencing_people_position_id)
)
GO

CREATE TABLE  party_election_result  ( 
     party_election_result_id  	bigint(15) AUTO_INCREMENT NOT NULL,
     party_id                  	bigint(15) NOT NULL,
     election_id               	bigint(15) NOT NULL,
     total_consti_participated 	varchar(25) NULL,
     total_seats_won           	varchar(25) NULL,
     second_pos_won            	varchar(25) NULL,
     third_pos_won             	varchar(25) NULL,
     fourth_pos_won            	varchar(25) NULL,
     nth_pos_won               	varchar(25) NULL,
     votes_percentage          	varchar(25) NULL,
     last_updated              	date NULL,
     complete_votes_percentage  varchar(25),
    PRIMARY KEY( party_election_result_id )
)
GO
ALTER TABLE  party_election_result 
    ADD CONSTRAINT  fk_party_party_election_result 
	FOREIGN KEY( party_id )
	REFERENCES  party ( party_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE  party_election_result 
    ADD CONSTRAINT  fk_election_party_election_result 
	FOREIGN KEY( election_id )
	REFERENCES  election ( election_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

CREATE TABLE  party_election_district_result  ( 
     party_election_district_result_id 	bigint(15) AUTO_INCREMENT NOT NULL,
     party_id                          	bigint(15) NOT NULL,
     election_id                       	bigint(15) NOT NULL,
     state_id                          	bigint(15) NOT NULL,
     district_id                       	bigint(15) NOT NULL,
     total_consti_participated         	varchar(25) NULL,
     total_seats_won                   	varchar(25) NULL,
     second_pos_won                    	varchar(25) NULL,
     third_pos_won                     	varchar(25) NULL,
     fourth_pos_won                    	varchar(25) NULL,
     nth_pos_won                       	varchar(25) NULL,
     votes_percentage                  	varchar(25) NULL,
     last_updated                      	date NULL,
     complete_votes_percentage          varchar(25),
    PRIMARY KEY( party_election_district_result_id )
)
GO

CREATE TABLE user_category ( 
	user_category_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_category   	varchar(25) NULL,
	updated_date    	timestamp NULL,
	PRIMARY KEY(user_category_id)
)
GO

ALTER TABLE registration ADD user_category_id bigint(15)
GO

CREATE TABLE assigned_problem_progress ( 
	assigned_problem_progress_id                	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_history_id                          	bigint(15) NULL,
	description                                 	varchar(250) NULL,
	performed_date                              	timestamp NULL,
	progress_level                              	varchar(25) NULL,
	concerned_person_name				varchar(100) null,
	designation					varchar(25) null,
	contact_no					varchar(25) null,
	problem_source_scope_concerned_department_id	bigint(15) NULL,
	PRIMARY KEY(assigned_problem_progress_id)
)
GO
CREATE TABLE fund_source ( 
	fund_source_id	bigint(15) AUTO_INCREMENT NOT NULL,
	department_id 	bigint(15) NULL,
	fund_source   	varchar(25) NULL,
	description   	varchar(25) NULL,
	updated_date  	timestamp NULL,
	PRIMARY KEY(fund_source_id)
)
GO
CREATE TABLE problem ( 
	problem_id   	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_title   varchar(200) null,
	description  	varchar(250) NULL,
	intensity    	varchar(25) NULL,
	identified_on	date NULL,
	year         	varchar(25) NULL,
	existing_from   date null,
	PRIMARY KEY(problem_id)
)
GO
CREATE TABLE problem_and_problem_source ( 
	problem_and_problem_source_id	bigint(15) AUTO_INCREMENT NOT NULL,
	posted_by                    	varchar(100) NULL,
	user_id                      	bigint(15) NULL,
	problem_id                   	bigint(15) NULL,
	problem_source_id            	bigint(15) NULL,
	updated_date                 	timestamp NULL,
	problem_external_source_id   	bigint(15) NULL,
	PRIMARY KEY(problem_and_problem_source_id)
)
GO
CREATE TABLE problem_classification ( 
	problem_classification_id	bigint(15) AUTO_INCREMENT NOT NULL,
	classification           	varchar(25) NULL,
	description              	varchar(25) NULL,
	updated_date             	timestamp NULL,
	PRIMARY KEY(problem_classification_id)
)
GO
CREATE TABLE problem_external_source ( 
	problem_external_source_id	bigint(15) AUTO_INCREMENT NOT NULL,
	name                      	varchar(25) NULL,
	mobile                    	varchar(25) NULL,
	telephone             	        varchar(25) NULL,
	email                     	varchar(25) NULL,
	address                   	varchar(100) NULL,
	PRIMARY KEY(problem_external_source_id)
)
GO
CREATE TABLE problem_fund_source ( 
	problem_fund_source_id	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_history_id    	bigint(15) NULL,
	fund_source_id        	bigint(15) NULL,
	status                	varchar(25) NULL,
	description            	varchar(25) NULL,
	date_assigned         	timestamp NULL,
	PRIMARY KEY(problem_fund_source_id)
)
GO
CREATE TABLE problem_history ( 
	problem_history_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_location_id     	bigint(15) NULL,
	problem_status_id       	bigint(15) NULL,
	comments                	varchar(250) NULL,
	problem_source_scope_id 	bigint(15) NULL,
	updated_table_and_column	varchar(25) NULL,
	previous_data           	varchar(25) NULL,
	updated_date            	timestamp NULL,
	is_delete                       varchar(25),
	PRIMARY KEY(problem_history_id)
)
GO
CREATE TABLE problem_location ( 
	problem_location_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_and_problem_source_id bigint(15) NULL,
	township_id              	bigint(15) NULL,
	hamlet_id                	bigint(15) NULL,
	ward_id                  	bigint(15) NULL,
	problem_classification_id	bigint(15) NULL,
	updated_date             	timestamp NULL,
	PRIMARY KEY(problem_location_id)
)
GO
CREATE TABLE problem_source ( 
	problem_source_id	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_source   	varchar(25) NULL,
	description      	varchar(25) NULL,
	PRIMARY KEY(problem_source_id)
)
GO
CREATE TABLE problem_source_scope ( 
	problem_source_scope_id	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_source_scope   	varchar(25) NULL,
	updated_date           	timestamp NULL,
	PRIMARY KEY(problem_source_scope_id)
)
GO
CREATE TABLE problem_source_scope_concerned_department ( 
	problem_source_scope_concerned_department_id	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_source_scope_id                     	bigint(15) NULL,
	department                                  	varchar(25) NULL,
	description                                 	varchar(25) NULL,
	updated_date                                	timestamp NULL,
	PRIMARY KEY(problem_source_scope_concerned_department_id)
)
GO
CREATE TABLE problem_status ( 
	problem_status_id	bigint(15) AUTO_INCREMENT NOT NULL,
	status           	varchar(25) NULL,
	updated_date     	timestamp NULL,
	PRIMARY KEY(problem_status_id)
)
GO

CREATE TABLE static_group ( 
	static_group_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	group_name       	varchar(100) NOT NULL,
	group_desc	        varchar(250) NULL,
	created_date    	timestamp NULL,
        updated_date            timestamp NULL,
	PRIMARY KEY(static_group_id)
)
GO
CREATE TABLE my_group ( 
	my_group_id 	        bigint(15) AUTO_INCREMENT NOT NULL,
	group_name       	varchar(100) NOT NULL,
	group_desc      	varchar(250) NULL,
	created_date    	timestamp NULL,
	updated_date            timestamp NULL,
	PRIMARY KEY(my_group_id)
)
GO
CREATE TABLE personal_user_group ( 
	personal_user_group_id	bigint(15) AUTO_INCREMENT NOT NULL,
	group_name             	varchar(100) NOT NULL,
	description           	varchar(250) NOT NULL,
	static_group_id       	bigint(15) NULL,
	my_group_id             bigint(15) NULL,
	parent_group_id       	bigint(15) NULL,
	created_user_id       	bigint(15) NOT NULL,
	updated_date          	timestamp NULL,
	created_date          	timestamp NULL,
	parent_group_name     	varchar(100) NULL,
        PRIMARY KEY(personal_user_group_id)
)
GO

CREATE TABLE static_users ( 
	static_user_id        	bigint(15) AUTO_INCREMENT NOT NULL,
	name                  	varchar(100) NOT NULL,
	mobile          	varchar(25) NOT NULL,
	email               	varchar(100) NULL,
	address               	varchar(250) NULL,
	location              	varchar(100) NULL,
	designation           	varchar(100) NULL,
	created_date          	timestamp NULL,
	updated_date          	timestamp NULL,
	phone           	varchar(25) NULL,
	PRIMARY KEY(static_user_id)
)
GO
CREATE TABLE user_group_privileges ( 
	user_group_privilege_id	bigint(15) AUTO_INCREMENT NOT NULL,
	personal_user_group_id 	bigint(15) NOT NULL,
	read_right_user_id     	bigint(15) NULL,
	write_right_user_id    	bigint(15) NULL,
	updated_date           	timestamp NULL,
	created_date           	timestamp NULL,
	PRIMARY KEY(user_group_privilege_id)
)
GO

CREATE TABLE static_user_group ( 
        static_user_group_id    bigint(15) AUTO_INCREMENT NOT NULL,
        static_user_id        	bigint(15) NOT NULL,
        personal_user_group_id 	bigint(15) NOT NULL,
        updated_date          	timestamp NULL,
	PRIMARY KEY(static_user_group_id)
)
GO

ALTER TABLE personal_user_group
	ADD CONSTRAINT fk_parent_group_id_personal_user_group
	FOREIGN KEY(parent_group_id)
	REFERENCES personal_user_group(personal_user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE static_user_group
	ADD CONSTRAINT fk_personal_user_group_static_user_group
	FOREIGN KEY(personal_user_group_id)
	REFERENCES personal_user_group(personal_user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE static_user_group
	ADD CONSTRAINT fk_static_user_id_static_user
	FOREIGN KEY(static_user_id)
	REFERENCES static_users(static_user_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE user_group_privileges
	ADD CONSTRAINT fk_personal_user_group_privileges
	FOREIGN KEY(personal_user_group_id)
	REFERENCES personal_user_group(personal_user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE personal_user_group
	ADD CONSTRAINT fk_personal_user_group_registration
	FOREIGN KEY(created_user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE user_group_privileges
	ADD CONSTRAINT fk_registration_user_group_privileges
	FOREIGN KEY(read_right_user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE user_group_privileges
	ADD CONSTRAINT fk_registration_id_write_write_user_id
	FOREIGN KEY(write_right_user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE personal_user_group
	ADD CONSTRAINT fk_static_group_personal_user_group
	FOREIGN KEY(static_group_id)
	REFERENCES static_group(static_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE personal_user_group
	ADD CONSTRAINT fk_my_group_personal_user_group
	FOREIGN KEY(my_group_id)
	REFERENCES my_group(my_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

INSERT INTO static_group(static_group_id, group_name, group_desc, created_date)
VALUES(1, "Media", "This group contains sub groups for people who are working in media sector", '2010-02-25')
GO
INSERT INTO static_group(static_group_id, group_name, group_desc, created_date)
VALUES(2, "Officials", "This group contain sub groups which contain officials as group members", '2010-02-25')
GO
INSERT INTO static_group(static_group_id, group_name, group_desc, created_date)
VALUES(3, "NGOs" , "This groupd contains several non governmen organizations", '2010-02-25')
GO

ALTER TABLE problem_location
	ADD CONSTRAINT fk_hamlet_problem_location
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location
	ADD CONSTRAINT fk_problem_and_problem_source_problem_location_1
	FOREIGN KEY(problem_and_problem_source_id)
	REFERENCES problem_and_problem_source(problem_and_problem_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location
	ADD CONSTRAINT fk_township_problem_location
	FOREIGN KEY(township_id)
	REFERENCES township(township_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location
	ADD CONSTRAINT fk_problem_classification_problem_location_1
	FOREIGN KEY(problem_classification_id)
	REFERENCES problem_classification(problem_classification_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location
	ADD CONSTRAINT fk_ward_problem_location
	FOREIGN KEY(ward_id)
	REFERENCES ward(ward_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_and_problem_source
	ADD CONSTRAINT fk_problem_problem_source_1
	FOREIGN KEY(problem_id)
	REFERENCES problem(problem_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE assigned_problem_progress
	ADD CONSTRAINT fk_problem_history_assigned_problem_progress_1
	FOREIGN KEY(problem_history_id)
	REFERENCES problem_history(problem_history_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_fund_source
	ADD CONSTRAINT fk_fund_source_problem_fund_source_1
	FOREIGN KEY(fund_source_id)
	REFERENCES fund_source(fund_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_fund_source
	ADD CONSTRAINT fk_problem_history_problem_fund_source_1
	FOREIGN KEY(problem_history_id)
	REFERENCES problem_history(problem_history_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_history
	ADD CONSTRAINT fk_problem_location_problem_history_1
	FOREIGN KEY(problem_location_id)
	REFERENCES problem_location(problem_location_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_and_problem_source
	ADD CONSTRAINT fk_problem_external_source_problem_source_1
	FOREIGN KEY(problem_external_source_id)
	REFERENCES problem_external_source(problem_external_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_and_problem_source
	ADD CONSTRAINT fk_registration_user_id
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_and_problem_source
	ADD CONSTRAINT fk_prob_source_prob_and_prob_source
	FOREIGN KEY(problem_source_id)
	REFERENCES problem_source(problem_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_history
	ADD CONSTRAINT fk_problem_source_scope_problem_history_1
	FOREIGN KEY(problem_source_scope_id)
	REFERENCES problem_source_scope(problem_source_scope_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_history
	ADD CONSTRAINT fk_problem_status_problem_history_1
	FOREIGN KEY(problem_status_id)
	REFERENCES problem_status(problem_status_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_source_scope_concerned_department
	ADD CONSTRAINT fk_pblm_src_scope_pblm_src_scope_concnd_dept_1
	FOREIGN KEY(problem_source_scope_id)
	REFERENCES problem_source_scope(problem_source_scope_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE assigned_problem_progress
	ADD CONSTRAINT fk_problm_src_scope_concrnd_dept_assignd_pblm_progress_1
	FOREIGN KEY(problem_source_scope_concerned_department_id)
	REFERENCES problem_source_scope_concerned_department(problem_source_scope_concerned_department_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO




ALTER TABLE  party_election_district_result 
	ADD CONSTRAINT  fk_district_party_election_district_result 
FOREIGN KEY( district_id )
	REFERENCES  district ( district_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE  party_election_district_result 
	ADD CONSTRAINT  fk_election_party_election_district_result 
FOREIGN KEY( election_id )
	REFERENCES  election ( election_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE  party_election_district_result 
	ADD CONSTRAINT  fk_party_party_election_district_result 
FOREIGN KEY( party_id )
	REFERENCES  party ( party_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE  party_election_district_result 
	ADD CONSTRAINT  fk_state_party_election_district_result 
FOREIGN KEY( state_id )
	REFERENCES  state ( state_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE  epaper_url 
	ADD CONSTRAINT  fk_epaperUrl_epaper 
	FOREIGN KEY( epaper_id )
	REFERENCES  epaper ( epaper_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE influencing_people
ADD CONSTRAINT fk_hamlet_influence_people
FOREIGN KEY(hamlet_id)
REFERENCES hamlet(hamlet_id)
ON DELETE RESTRICT 
ON UPDATE RESTRICT 
GO
ALTER TABLE influencing_people
ADD CONSTRAINT fk_influence_people_position_influence_people
FOREIGN KEY(influencing_people_position_id)
REFERENCES influencing_people_position(influencing_people_position_id)
ON DELETE RESTRICT 
ON UPDATE RESTRICT 
GO
ALTER TABLE influencing_people
ADD CONSTRAINT fk_party_influence_people
FOREIGN KEY(party_id)
REFERENCES party(party_id)
ON DELETE RESTRICT 
ON UPDATE RESTRICT 
GO
ALTER TABLE booth_constituency_election_voter
	ADD CONSTRAINT fk_voter_booth_constituency_election_voter
	FOREIGN KEY(voter_id)
	REFERENCES voter(voter_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE cadre ADD COLUMN hamlet_id bigint(15)  
GO
ALTER TABLE cadre ADD CONSTRAINT fk_cadre_hamlet FOREIGN KEY(hamlet_id) REFERENCES hamlet(hamlet_id)
GO
ALTER TABLE user_imp_dates
	ADD COLUMN is_deleted varchar(6)  
GO
ALTER TABLE constituency
    ADD COLUMN tehsil_id bigint(15) 
GO
ALTER TABLE constituency
    ADD CONSTRAINT fk_constituency_tehsil
    FOREIGN KEY(tehsil_id)
    REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE user_imp_dates MODIFY rec_freq_type varchar(10)
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(1,24 ,'1885-12-28','INC PARTY FORMATION DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(2,24 ,' 1970-06-19','RAHUL GANDHI BIRTHDAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(3,24 ,'1956-11-1','AP FORMATION DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(4,24 ,'1984-10-31','INDIRA GANDHI DEATH ANNIVERSARY DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(5,24 ,'1991-5-21','RAJIV GANDHI DEATH ANNIVERSARY DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(6,24 ,'2009-9-2','YSR DEATH ANNIVERSARY DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(7,24 ,'1946-12-9','SONIA GANDHI BIRTH DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(8,24 ,'1950-1-26','REPUBLIC DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(9,24 ,'1947-8-15','INDEPENDENCE DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(10,24 ,'0000-12-25','CHRISTMAS DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(11,62 ,'1950-1-26','REPUBLIC DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(12,62 ,'1947-8-15','INDEPENDENCE DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(13,62 ,'1956-11-1','AP FORMATION DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(14,62 ,'9999-12-14','WORLD DISABILITY DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(15,62 ,'1982-3-29','TDP PARTY FORMATION DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(16,62 ,'1996-1-18','NTR DEATH ANNIVERSARY DAY','','T','YEARLY')
GO
insert into PARTY_IMPORTANT_DATE(party_important_date_id,party_id,important_date,title,importance,recursive,recursive_frequency) 
values(17,62 ,'0000-12-25','CHRISTMAS DAY','','T','YEARLY')
GO




insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(1,'eenadu','null','SP',1,'null',1,'http://www.eenadu.net/','eenadulogo.gif','Telugu')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(2,'andhrajyothy','null','SP',1,'null',1,'http://www.andhrajyothy.com/','ajlogo.jpg','Telugu')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(3,'vartha','null','SP',1,'null',1,'http://www.vaartha.com/','vaarthalogo.jpg','Telugu')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(4,'saakshi','null','SP',1,'null',1,'http://www.saakshi.com/','sakshilogo.gif','Telugu')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(5,'The Deccan Chronicle','null','NP',1,'http://www.deccanchronicle.com/national',1,'http://www.deccanchronicle.com/city/hyderabad','deccanlogo.jpg','English')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(6,'The Hindu','null','NP',1,'http://www.thehindu.com/',1,'http://www.hindu.com/2010/01/27/21hdline.htm','thehindu.jpg','English')
GO
insert into epaper( epaper_id,name,description,classification,country_id,country_url,state_id,state_url,image,language) 
values(7,'The Times Of India','null','NP',1,'http://timesofindia.indiatimes.com/',1,'http://timesofindia.indiatimes.com/city/hyderabad/articlelist/-2128816011.cms','timesofindia.jpg','English')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(1,1,1,'http://www.eenadu.net/district/districtshow1.asp?dis=adilabad')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(2,2,1,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=adb')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(3,3,1,'http://www.vaartha.com/contents/229/adilabad.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(4,4,1,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=1')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(5,1,22,'http://www.eenadu.net/district/districtshow1.asp?dis=anantapur')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(6,2,22,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=atp')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(7,3,22,'http://www.vaartha.com/contents/292/anantapuram.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(8,4,22,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=12')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(9,1,23,'http://www.eenadu.net/district/districtshow1.asp?dis=chittoor')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(10,2,23,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=atp')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(11,3,23,'http://www.vaartha.com/contents/237/chittoor.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(12,4,23,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=13')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(13,1,20,'http://www.eenadu.net/district/districtshow1.asp?dis=kadapa')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(14,2,20,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=cdp')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(15,3,20,'http://www.vaartha.com/contents/231/kadapa.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(16,4,20,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=14')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(17,1,14,'http://www.eenadu.net/district/districtshow1.asp?dis=eastgodavari')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(18,2,14,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=egd')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(19,3,14,'http://www.vaartha.com/contents/238/east-godavari.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(20,4,14,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=20')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(21,1,17,'http://www.eenadu.net/district/districtshow1.asp?dis=guntur')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(22,2,17,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=gnt')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(23,3,17,'http://www.vaartha.com/contents/236/guntur.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(24,4,17,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=17')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(25,1,5,'http://www.eenadu.net/district/districtshow1.asp?dis=hyderabad')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(26,2,5,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=hyd')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(27,3,5,'http://www.vaartha.com/contents/250/hydearbad.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(28,4,5,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=7')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(29,1,3,'http://www.eenadu.net/district/districtshow1.asp?dis=karimnagar')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(30,2,3,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=knr')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(31,3,3,'http://www.vaartha.com/contents/233/karimnagar.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(32,4,3,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=3')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(33,1,10,'http://www.eenadu.net/district/districtshow1.asp?dis=khammam')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(34,2,10,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=kmm')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(35,3,10,'http://www.vaartha.com/contents/232/khammam.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(36,4,10,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=10')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(37,1,16,'http://www.eenadu.net/district/districtshow1.asp?dis=krishna')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(38,2,16,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=kri')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(39,3,16,'http://www.vaartha.com/contents/235/krishna.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(40,4,16,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=18')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(41,1,21,'http://www.eenadu.net/district/districtshow1.asp?dis=kurnool')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(42,2,21,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=knl')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(43,3,21,'http://www.vaartha.com/contents/234/kurnool.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(44,4,21,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=11')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(45,1,7,'http://www.eenadu.net/district/districtshow1.asp?dis=mahaboobnagar')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(46,2,7,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=mnr')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(47,3,7,'http://www.vaartha.com/contents/243/mehboobnagar.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(48,4,7,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=9')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(49,1,4,'http://www.eenadu.net/district/districtshow1.asp?dis=medak')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(50,2,4,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=mdk')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(51,3,4,'http://www.vaartha.com/contents/244/medak.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(52,4,4,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=4')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(53,1,8,'http://www.eenadu.net/district/districtshow1.asp?dis=medak')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(54,2,8,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nlg')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(55,3,8,'http://www.vaartha.com/contents/239/nalgonda.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(56,4,8,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=8')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(57,1,19,'http://www.eenadu.net/district/districtshow1.asp?dis=nellore')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(58,2,19,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nlr')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(59,3,19,'http://www.vaartha.com/contents/241/nellore.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(60,4,19,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=15')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(61,1,2,'http://www.eenadu.net/district/districtshow1.asp?dis=nizamabad')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(62,2,2,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nzb')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(63,3,2,'http://www.vaartha.com/contents/240/nizamabad.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(64,4,2,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=2')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(65,1,18,'http://www.eenadu.net/district/districtshow1.asp?dis=prakasham')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(66,2,18,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=pkm')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(67,3,18,'http://www.vaartha.com/contents/187/prakasam.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(68,4,18,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=16')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(69,1,6,'http://www.eenadu.net/district/districtshow1.asp?dis=rangareddy')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(70,2,6,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=rrd')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(71,3,6,'http://www.vaartha.com/contents/245/ranga-reddy.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(72,4,6,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=6')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(73,1,11,'http://www.eenadu.net/district/districtshow1.asp?dis=srikakulam')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(74,2,11,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=skl')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(75,3,11,'http://www.vaartha.com/contents/249/srikakulam.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(76,4,11,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=23')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(77,1,13,'http://www.eenadu.net/district/districtshow1.asp?dis=Visakhapatnam')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(78,2,13,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=vsp')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(79,3,13,'http://www.vaartha.com/contents/248/vishakapatnam.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(80,4,13,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=21')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(81,1,12,'http://www.eenadu.net/district/districtshow1.asp?dis=vijayanagaram')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(82,2,12,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=vzm')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(83,3,12,'http://www.vaartha.com/contents/247/vijayanagaram.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(84,4,12,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=22')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(85,1,9,'http://www.eenadu.net/district/districtshow1.asp?dis=warangal')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(86,2,9,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=wgl')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(87,3,9,'http://www.vaartha.com/contents/246/warangal.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(88,4,9,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=5')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(89,1,15,'http://www.eenadu.net/district/districtshow1.asp?dis=westgodavari')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(90,2,15,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=wgd')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(91,3,15,'http://www.vaartha.com/contents/242/west-godavari.html')
GO
insert into epaper_url ( epaperurl_id,epaper_id,district_id,district_url) 
values(92,4,15,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=19')
GO

INSERT INTO user_category(`user_category_id`, `user_category`)
VALUES(1, 'MP')
GO
INSERT INTO user_category(`user_category_id`, `user_category`)
VALUES(2, 'MLA')
GO
INSERT INTO user_category(`user_category_id`, `user_category`)
VALUES(3, 'MPTC')
GO
INSERT INTO user_category(`user_category_id`, `user_category`)
VALUES(4, 'ZPTC')
GO
INSERT INTO problem_source_scope(`problem_source_scope_id`, `problem_source_scope`)
VALUES(1, 'DISTRICT')
GO
INSERT INTO problem_source_scope(`problem_source_scope_id`, `problem_source_scope`)
VALUES(2, 'MANDAL')
GO
INSERT INTO problem_source_scope(`problem_source_scope_id`, `problem_source_scope`)
VALUES(3, 'VILAGE')
GO
INSERT INTO problem_classification(`problem_classification_id`, `classification`)
VALUES(1, 'SOCIAL')
GO
INSERT INTO problem_classification(`problem_classification_id`, `classification`)
VALUES(2, 'ECONOMICAL')
GO
INSERT INTO problem_classification(`problem_classification_id`, `classification`)
VALUES(3, 'PERSONAL')
GO
INSERT INTO problem_status(`problem_status_id`, `status`)
VALUES(1, 'NEW')
GO
INSERT INTO problem_status(`problem_status_id`, `status`)
VALUES(2, 'CLASSIFY')
GO
INSERT INTO problem_status(`problem_status_id`, `status`)
VALUES(3, 'ASSIGNED')
GO
INSERT INTO problem_status(`problem_status_id`, `status`)
VALUES(4, 'PROGRESS')
GO
INSERT INTO problem_status(`problem_status_id`, `status`)
VALUES(6, 'FIXED')
GO
INSERT INTO problem_source_scope_concerned_department(`problem_source_scope_concerned_department_id`, `problem_source_scope_id`, `department`)
VALUES(1, 1, 'DEO')
GO
INSERT INTO problem_source_scope_concerned_department(`problem_source_scope_concerned_department_id`, `problem_source_scope_id`, `department`)
VALUES(2, 1, 'COLLECTORATE')
GO
INSERT INTO problem_source_scope_concerned_department(`problem_source_scope_concerned_department_id`, `problem_source_scope_id`, `department`)
VALUES(3, 2, 'MRO')
GO
INSERT INTO problem_source_scope_concerned_department(`problem_source_scope_concerned_department_id`, `problem_source_scope_id`, `department`)
VALUES(4, 3, 'VILAGE SECRETARY')
GO
INSERT INTO problem_source VALUES(1, 'USER', null)
GO
INSERT INTO problem_source VALUES(2, 'EXTERNAL PERSON', null)
GO
INSERT INTO problem_source VALUES(3, 'CALL CENTRE', null)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1,02,'SREERAM NAGAR COLONY',21814,01,'VELICHERLA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2,01,'VELICHERLA',21814,01,'VELICHERLA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(3,04,'KAPPARALAPART',21815,02,'SINGAPETA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(4,03,'MURTHIRAJU SANGAM',21815,02,'SINGAPETA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(5,02,'CHELIKASANGAM',21815,02,'SINGAPETA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(6,01,'SINGAPETA',21815,02,'SINGAPETA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(7,10,'UDDIPAGUNTA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(8,09,'RAYAPETA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(9,08,'WOODHOUSE PETA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(10,01,'ALLURUPETA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(11,02,'CHINTA GUNTA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(12,03,'YARRAPAGUNTA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(13,04,'MALAPATI DIBBALU',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(14,05,'PALLI PADU',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(15,06,'KONDAYA GUNTA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(16,07,'CHAKIREVUGUNTA',21816,03,'ALLURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(17,05,'SAMBUNI PALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(18,01,'ISAKAPALLI',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(19,02,'YELLUVARI PALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(20,04,'ISAKAPALLE PATTAPUPALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(21,06,'JUVVAGULAKSHMI PURAM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(22,07,'KURRU CHANDRASEKHARAPUR',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(23,08,'KURRU PATTAPU PALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(24,09,'ISAKAPALLI LABBIPALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(25,10,'KURRU LABBIPALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(26,11,'KOTHAKURRU PATTAPUPALEM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(27,03,'AUDIRAGHAVAPURAM',21817,04,'ISAKAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(28,04,'GOPALA PURAM',21818,05,'EAST GOGULAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(29,01,'GOGULAPALLI',21818,05,'EAST GOGULAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(30,03,'CHANDRASEKHARA PURAM',21818,05,'EAST GOGULAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(31,05,'C.C. GORIJANA COLONY',21818,06,'WEST GOGULA PALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(32,06,'WEST GOGULAPALLE',21818,06,'WEST GOGULA PALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(33,02,'NADIMURU',21818,05,'EAST GOGULAPALLE',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(34,04,'NAGULADEVI NAGAR',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(35,09,'CHERLOSANGAM',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(36,07,'GUDIVADA GUNTA',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(37,05,'SUNGAREDDI DINNE',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(38,10,'BODISATRAM',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(39,06,'CHUKKAVARI DIBBA',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(40,08,'GOLI DIBBALU',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(41,03,'KOMAREDDYGUNTA',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(42,01,'ALLUR',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(43,02,'BUDURUGUNTA',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(44,01,'GRADDAGUNTA',21820,07,'GRADDAGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(45,02,'KOTHURU',21821,07,'GRADDAGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(46,01,'BATRAKAGOLLU',21821,08,'BATRAKAGOLLU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(47,02,'GOLLAPALEM',21822,09,'NORTH AMULURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(48,01,'NORTH AMULURU',21822,09,'NORTH AMULURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(49,01,'NORTH MOPURU',21825,10,'NORTH MOPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(50,02,'NAIDU CHERUVU',21825,10,'NORTH MOPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(51,03,'GAMALLA PALEM',21825,10,'NORTH MOPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(52,04,'VADDEPALEM',21825,10,'NORTH MOPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(53,01,'INDUPURU',21826,11,'INDUPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(54,02,'PALLE PALEM',21826,11,'INDUPURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(55,02,'NALLACHERUVU KATTA',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(56,01,'PURINI',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(57,03,'SUBBANAIDU TOPU',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(58,06,'TUFAN NAGAR',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(59,05,'KOTHA PALEM',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(60,04,'MANDIRAM',21827,12,'PURINI',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(61,01,'BEERAMGUNTA',21824,13,'BEERAMGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(62,02,'RICE MILLGIRIJANACOLONY',21824,13,'BEERAMGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(63,01,'KALAMBOTLAVARIKANDRIKA',21823,13,'BEERAMGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(64,01,'ANATHABOTLAVARI KAN(UI)',21823,13,'BEERAMGUNTA',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(65,01,'BITRAGUNTA',21668,01,'BITRAGUNTA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(66,03,'PATHABITRAGUNTA',21668,01,'BITRAGUNTA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(67,02,'KONDABITRAGUNTA',21668,01,'BITRAGUNTA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(68,01,'THALLURU',21665,02,'THALLURU',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(69,02,'GURAVAREDDIPALEM',21665,02,'THALLURU',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(70,05,'S.G.V. KANDRIGA',21666,04,'S.G.V. KANDRIGA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(71,04,'ARUNDATIYAPALEM',21666,03,'JAKKEPALLIGUDUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(72,03,'KAMMAPALEM',21666,03,'JAKKEPALLIGUDUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(73,02,'SOMESWARAPURAM',21666,03,'JAKKEPALLIGUDUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(74,01,'JAKKEPALLIGUDUR',21666,03,'JAKKEPALLIGUDUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(75,06,'B.G.V. KANDRIGA',21666,04,'S.G.V. KANDRIGA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(76,08,'YAGNESWARAPURAM',21666,05,'A.B. KANDRIGA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(77,07,'A.B. KANDRIGA',21666,05,'A.B. KANDRIGA',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(78,01,'BOGOLE',21667,06,'BOGOLE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(79,02,'K.THIPPA',21667,06,'BOGOLE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(80,03,'A.K.V. KANDRIGA',21667,06,'BOGOLE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(81,04,'NAGULAVARAM',21667,07,'NAGULAVARAM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(82,05,'C.G.V. KANDRIGA',21667,07,'NAGULAVARAM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(83,01,'KOVURUPALLE (PT)',21676,08,'KOVURUPALLE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(84,01,'UMAMAHESWARAPURAM',21669,10,'UMAMAHESWARAPURAM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(85,01,'AKKIMADUGU',21670,11,'ALLIMADUGU',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(86,02,'TELLAGUNTA',21670,11,'ALLIMADUGU',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(87,03,'KADANUTALA',21670,11,'ALLIMADUGU',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(88,01,'MUNGAMUR',21671,12,'MUNGAMUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(89,01,'SAMBASIVAPURAM',21672,12,'MUNGAMUR',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(90,05,'KOTHABANGARUPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(91,01,'JUVVALADINNE',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(92,02,'ODDIPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(93,03,'PANDIGUNTA',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(94,04,'KOTHAKADAPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(95,14,'PATAPALEM',21673,15,'C.R. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(96,13,'T.C. PALEM',21673,15,'C.R. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(97,12,'C.R. PALEM',21673,15,'C.R. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(98,11,'JADAGOGULA',21673,14,'YENUGULABAVI',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(99,10,'KOLLADINNE',21673,14,'YENUGULABAVI',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(100,09,'YENUGULABAVI',21673,14,'YENUGULABAVI',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(101,08,'PATABANGARUPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(102,07,'PATAKADAPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(103,06,'ALICHERLABANGARUPALEM',21673,13,'JUVVALADINNE',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(104,01,'S.V. PALEM',21674,16,'S.V. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(105,02,'SIDDAVARAM',21674,16,'S.V. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(106,01,'MALLAYAPALEM',21675,15,'C.R. PALEM',06)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(107,02,'VADDEPALLE',21795,01,'DUNDIGAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(108,01,'DUNDIGAM',21795,01,'DUNDIGAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(109,01,'MARELLAPADU',21794,02,'RANGASAMUDRAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(110,01,'MANUBOLUPADU',21793,03,'MANUBOLUPADU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(111,02,'DHARMAVARAM',21793,04,'DHARMAVARAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(112,01,'BODAGUDIPADU',21792,05,'BODAGUDIPADU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(113,01,'KATRAYAPADU',21799,06,'KATRAYAPADU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(114,01,'LINGALAPADU',21798,04,'DHARMAVARAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(115,01,'RANGASAMUDRAM',21797,02,'RANGASAMUDRAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(116,01,'ITHAMPADU',21796,02,'RANGASAMUDRAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(117,05,'KATTABADI PALEM',21807,07,'CHENNURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(118,01,'CHENNURU',21807,07,'CHENNURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(119,02,'KATTUBADIPALEM',21807,07,'CHENNURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(120,03,'SIDDAREDDI PALEM',21807,07,'CHENNURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(121,04,'THIMMAREDDI PALEM',21807,07,'CHENNURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(122,01,'VELUPODU',21800,08,'VELUPODU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(123,02,'MABBUGUNTA PALEM',21800,08,'VELUPODU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(124,01,'KAMINENIPALEM',21801,08,'VELUPODU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(125,02,'NARAYANAPURAM',21802,09,'DAMAVARAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(126,01,'DAMAVARAM',21802,09,'DAMAVARAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(127,01,'ANANTHAVARAM',21803,10,'ANANTHAVARAM',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(128,01,'ULAVAPALLA',21804,11,'ULAVAPALLA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(129,02,'SUNNAPU BATTI',21805,12,'K.K. GUNTA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(130,01,'K.K. GUNTA',21805,12,'K.K. GUNTA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(131,02,'TADAKALURU',21806,14,'TADAKALURU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(132,01,'DAGADARTHI',21806,13,'DAGADARTHI',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(133,01,'THURIMERLA',21809,15,'THURIMERLA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(134,02,'DAKSHINA MITTA',21809,15,'THURIMERLA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(135,03,'LAXMINARAYANAPALEM',21809,15,'THURIMERLA',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(136,01,'THIRUVEEDHIPADU',21808,16,'THIRUVEEDHIPADU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(137,01,'YELAMANCHIPADU',21810,17,'YELAMANCHIPADU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(138,01,'PEDAPUTHEDU',21811,18,'PEDAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(139,05,'RAMAKRISHNAPURAM',21811,18,'PEDAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(140,04,'UPPALA PADU',21811,18,'PEDAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(141,03,'RAMALINGA PURAM',21811,18,'PEDAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(142,02,'PADISONPET',21811,18,'PEDAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(143,01,'CHOUTAPUTHEDU',21812,19,'CHOUTAPUTHEDU',14)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(144,01,'UCHAGUNTA PALEM',21813,20,'UCHAGUNTA PALEM',14)
GO
