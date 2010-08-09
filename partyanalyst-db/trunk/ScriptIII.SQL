
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

CREATE TABLE comment_category_candidate ( 
	comment_category_candidate_id	bigint(15) AUTO_INCREMENT NOT NULL,
	comment_data_id              	bigint(15) NOT NULL,
	nomination_id                	bigint(15) NOT NULL,
	PRIMARY KEY(comment_category_candidate_id)
)
GO
CREATE TABLE comment_category_constituency ( 
	comment_category_constituency_id	bigint(15) AUTO_INCREMENT NOT NULL,
	comment_data_id                 	bigint(15) NOT NULL,
	constituency_id                 	bigint(15) NOT NULL,
	election_id                     	bigint(15) NOT NULL,
	PRIMARY KEY(comment_category_constituency_id)
)
GO
CREATE TABLE comment_category_party ( 
	comment_category_party_id	bigint(15) AUTO_INCREMENT NOT NULL,
	comment_data_id          	bigint(15) NOT NULL,
	party_id                 	bigint(15) NOT NULL,
	election_id              	bigint(15) NOT NULL,
	PRIMARY KEY(comment_category_party_id)
)
GO
CREATE TABLE comment_data ( 
	comment_data_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	comment_desc            	varchar(250) NULL,
	commented_on            	timestamp NULL,
	commented_by            	varchar(25) NULL,
	comment_data_category_id	bigint(15) NULL,
	PRIMARY KEY(comment_data_id)
)
GO
CREATE TABLE comment_data_category ( 
	comment_data_category_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	comment_data_category_type	varchar(100) NULL,
	updated_date              	date NULL,
	comment_classification          varchar(25),
	comment_basic_category          varchar(25),
	PRIMARY KEY(comment_data_category_id)
)
GO
CREATE TABLE party_election_state_result ( 
    party_election_state_result_id	bigint(15) AUTO_INCREMENT NOT NULL,
    party_id                      	bigint(15) NOT NULL,
    election_id                   	bigint(15) NOT NULL,
    state_id                      	bigint(15) NOT NULL,
    total_consti_participated     	varchar(25) NULL,
    total_seats_won               	varchar(25) NULL,
    second_pos_won                	varchar(25) NULL,
    third_pos_won                 	varchar(25) NULL,
    fourth_pos_won                	varchar(25) NULL,
    nth_pos_won                   	varchar(25) NULL,
    votes_percentage              	varchar(25) NULL,
    complete_votes_percentage     	varchar(25) NULL,
    total_votes_gained            	double NULL,
    total_valid_votes             	double NULL,
    complete_consti_valid_votes   	double NULL,
    last_updated                  	date NULL,
    PRIMARY KEY(party_election_state_result_id)
)
GO
ALTER TABLE party_election_state_result
    ADD CONSTRAINT fk_state_party_election_state_result
	FOREIGN KEY(state_id)
	REFERENCES state(state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE party_election_state_result
    ADD CONSTRAINT fk_party_party_election_state_result
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE party_election_state_result
    ADD CONSTRAINT fk_election_party_election_state_result
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

ALTER TABLE influencing_people
	DROP FOREIGN KEY fk_hamlet_influence_people
GO
ALTER TABLE influencing_people
	DROP FOREIGN KEY fk_influence_people_position_influence_people
GO
ALTER TABLE influencing_people
	DROP FOREIGN KEY fk_party_influence_people
GO
DROP TABLE influencing_people
GO
DROP TABLE influencing_people_position
GO
CREATE TABLE influencing_people ( 
	influencing_people_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	first_name                    	varchar(25) NULL,
	last_name                     	varchar(25) NULL,
	influencing_scope             	varchar(25) NULL,
	party_id                      	bigint(15) NULL,
	caste                         	varchar(50) NULL,
	occupation                    	varchar(50) NULL,
	phone                         	varchar(25) NULL,
	influencing_people_position_id	bigint(15) NULL,
	hamlet_id                     	bigint(15) NULL,
	gender                        	varchar(25) NULL,
	email_id                      	varchar(25) NULL,
	PRIMARY KEY(influencing_people_id)
)
GO
CREATE TABLE influencing_people_position ( 
	influencing_people_position_id	bigint(15) AUTO_INCREMENT NOT NULL,
	position_type                 	varchar(50) NULL,
	position_desc                 	varchar(100) NULL,
	upadated_date                 	timestamp NULL,
	PRIMARY KEY(influencing_people_position_id)
)
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
ALTER TABLE comment_category_candidate
	ADD CONSTRAINT fk_comment_data_comment_category_candidate
	
	FOREIGN KEY(comment_data_id)
	
	REFERENCES comment_data(comment_data_id)
	
	ON DELETE RESTRICT 
	
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_candidate
    ADD CONSTRAINT fk_nomination_comment_category_candidate
	FOREIGN KEY(nomination_id)
	REFERENCES nomination(nomination_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_party
	ADD CONSTRAINT fk_comment_data_comment_category_party
	
	FOREIGN KEY(comment_data_id)
	
	REFERENCES comment_data(comment_data_id)
	
	ON DELETE RESTRICT 
	
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_party
    ADD CONSTRAINT fk_party_comment_category_party
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_party
    ADD CONSTRAINT fk_election_comment_category_party
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_constituency
	ADD CONSTRAINT fk_comment_data_comment_category_constituency
	
	FOREIGN KEY(comment_data_id)
	
	REFERENCES comment_data(comment_data_id)
	
	ON DELETE RESTRICT 
	
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_constituency
    ADD CONSTRAINT fk_constituency_comment_category_constituency
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_category_constituency
    ADD CONSTRAINT fk_election_comment_category_constituency
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE comment_data
	ADD CONSTRAINT fk_comment_data_category_comment_data
	FOREIGN KEY(comment_data_category_id)
	REFERENCES comment_data_category(comment_data_category_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

INSERT INTO comment_data_category VALUES(1,'CANDIDATE INFLUENCE',null,'WON','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(2,'STRONG CADRE',null,'WON','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(3,'CASTE SUPPORT',null,'WON','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(4,'PREVIOUS DEVELOPMENT ACTIVITIES',null,'WON','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(5,'GOOD SOCIAL SKILLS',null,'WON','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(6,'PARTY STRONG',null,'WON','CANDIDATE')
GO

INSERT INTO comment_data_category VALUES(7,'CANDIDATE INFLUENCE',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(8,'WEAK CADRE',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(9,'NO CASTE INFLUENCE',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(10,'REBELS INFLUENCE',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(11,'ALLIANCE IMPACT',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(12,'POOR DEVELOPMENT ACTIVITIES',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(13,'POOR RAPPORT WITH ACTIVISTS',null,'LOST','CANDIDATE')
GO
INSERT INTO comment_data_category VALUES(14,'POOR CAMPAIGN',null,'LOST','CANDIDATE')
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

CREATE TABLE village_booth_election ( 
    village_booth_election_id     	bigint(20) AUTO_INCREMENT NOT NULL,
    township_id                   	bigint(20) NULL,
    booth_constituency_election_id	bigint(20) NULL,
    PRIMARY KEY(village_booth_election_id)
)
GO
ALTER TABLE village_booth_election
    ADD CONSTRAINT booth_constituency_election_id
	UNIQUE (booth_constituency_election_id)
GO
ALTER TABLE village_booth_election
    ADD CONSTRAINT fk_township_village_booth_election
	FOREIGN KEY(township_id)
	REFERENCES township(township_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE village_booth_election
    ADD CONSTRAINT fk_booth_constituency_election_village_booth_election
	FOREIGN KEY(booth_constituency_election_id)
	REFERENCES booth_constituency_election(booth_constituency_election_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO

CREATE TABLE hamlet_booth_election ( 
    hamlet_booth_election_id      	bigint(15) AUTO_INCREMENT NOT NULL,
    hamlet_id                     	bigint(15) NULL,
    booth_constituency_election_id	bigint(25) NULL,
    PRIMARY KEY(hamlet_booth_election_id)
)
GO
ALTER TABLE hamlet_booth_election
    ADD CONSTRAINT fk_hamlet_hamlet_booth_election
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE hamlet_booth_election
    ADD CONSTRAINT fk_booth_constituency_election_hamlet_booth_election
	FOREIGN KEY(booth_constituency_election_id)
	REFERENCES booth_constituency_election(booth_constituency_election_id)
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

ALTER TABLE party_election_result ADD total_votes_gained double(10,0)
GO
ALTER TABLE party_election_result ADD total_valid_votes double(10,0)
GO
ALTER TABLE party_election_result ADD complete_consti_valid_votes double(10,0)
GO
ALTER TABLE party_election_district_result ADD total_votes_gained double(10,0)
GO
ALTER TABLE party_election_district_result ADD total_valid_votes double(10,0)
GO
ALTER TABLE party_election_district_result ADD complete_consti_valid_votes double(10,0)
GO

ALTER TABLE party
ADD (	party_logo	varchar(25),
 	party_flag	varchar(25) );
GO

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
VALUES(5, 'PENDING')
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


//Hamlet Statics

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
 insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(145,10,'AUDINARAYANAPURAM',21819,03,'ALLUR',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(146,03,'VADDEPALEM',21822,09,'NORTH AMULURU',15)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(147,01,'CHINA NAGAMPALLE',21547,02,'CHINA NAGAMPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(148,01,'PEDDA NAGAMPALLE',21548,02,'CHINA NAGAMPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(149,01,'CHINTHODU',21549,01,'kABBULETIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(150,03,'GONUVARIPALLE',21550,01,'PABBULETIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(151,02,'PERISETTIPALLE',21550,01,'PABBULETIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(152,01,'PABBULETIPALLE',21550,01,'PABBULETIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(153,02,'OGURUVANDLAPALLE',21551,03,'GUNDUPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(154,01,'GUNDUPALLE',21551,03,'GUNDUPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(155,03,'BODASIDDAYAPALLE',21551,01,'PABBULETIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(156,01,'MARAMREDDIPALLE',21552,04,'MARAMREDDIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(157,02,'GANDAMVARIPALLE',21552,04,'MARAMREDDIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(158,02,'NAGARAJUPALLE',21553,06,'JAYAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(159,01,'JAYAPURAM',21553,06,'JAYAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(160,01,'NEMALLADINNE',21554,07,'NEMALLADINNE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(161,01,'DEVISETTIPALLE',21555,05,'DEVISETTIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(162,05,'HARIJANAWADA',21556,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(163,04,'KOTTALU',21556,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(164,03,'SANGASANIPALLE',21556,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(165,02,'TURKAPALEM',21556,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(166,01,'SEETHARAMAPURAM',21556,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(167,01,'NARAYANAMPETA',21557,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(168,01,'PANDRANGI',21558,08,'SEETHARAMAPURAM',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(169,01,'SINGAREDDIPALLE',21559,09,'SINGAREDDIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(170,02,'NANCHARAMPALLE',21559,09,'SINGAREDDIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(171,01,'AYYAVARIPALLE',21560,10,'AYYAVARIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(172,03,'VADLAVARIPALLE',21560,10,'AYYAVARIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(173,02,'AYYAVARIPALLE H.W.',21560,10,'AYYAVARIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(174,04,'POKALAVARIPALLE',21561,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(175,03,'SOMPALLE',21561,07,'NEMALLADINNE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(176,02,'BEDUSUPALLE',21561,07,'NEMALLADINNE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(177,01,'GANGAVARAM',21561,07,'NEMALLADINNE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(178,01,'GANGAVARAM',22041,02,'GANGAVARAM',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(179,02,'ANKIREDDIPALLE',21562,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(180,05,'RANGANAIDUPALLE',21562,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(181,01,'BALAYAPALLE',21562,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(182,03,'KOTHAPALLE',21562,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(183,04,'SARASWATHIPURAM',21562,11,'BALAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(184,01,'BALAYAPALLE',22305,04,'BALAYAPALLI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(185,01,'DEVARAJUSURAYAPALLE',21563,12,'DEVARAJUSURAYAPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(186,01,'DEVAMMACHERUVU',21564,13,'DEVAMMACHERUVU',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(187,02,'RAVIMNAUDINNE',21565,13,'DEVAMMACHERUVU',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(188,01,'VEMPALLETHOKA',21565,13,'DEVAMMACHERUVU',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(189,01,'BASINENIPALLE',21566,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(190,02,'SANJEEVAREDDINAGAR',21566,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(191,03,'A.C. NAGAR',21566,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(192,04,'KOYYALAPADU',21566,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(193,02,'PADAMATIKODIGUDLAPADU',21567,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(194,01,'PADAMATI ROMPIDODLA',21567,14,'BASINENIPALLE',01)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(195,01,'PAPPULAVARIPALLE',21568,01,'ARLAPADIYA',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(196,01,'KOTAYAPALLE',21569,01,'ARLAPADIYA',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(197,01,'ARLAPADIYA',21570,01,'ARLAPADIYA',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(198,02,'UPPARAPALLE',21570,01,'ARLAPADIYA',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(199,03,'HARIJANAPALEM',21570,01,'ARLAPADIYA',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(200,01,'KRISHNAMPALLE',21571,02,'KRISHNAMPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(201,02,'VENGALARAO NAGAR',21572,04,'VENGALARAO NAGAR',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(202,01,'GANDIPALEM',21572,03,'GANDIPALEM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(203,01,'GUDINARAVA',21573,05,'THIRUMALPURAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(204,03,'GIRIJANA COLONY',21574,05,'THIRUMALAPURAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(205,02,'DUMPAVARI PALLE',21574,05,'THIRUMALAPURAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(206,01,'THIRUMALAPURAM',21574,05,'THIRUMALAPURAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(207,01,'S.V. CHINTALA',21575,05,'THIRUMALAPURAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(208,01,'GANNEPALLE',21576,06,'GANNEPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(209,02,'KOTTALA PALLE',21576,06,'GANNEPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(210,04,'ZETTIVARIPALLE',21576,06,'GANNEPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(211,05,'MITTAPALLE',21576,06,'GANNEPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(212,06,'GADDAMVARI PALLE',21576,06,'GANNEPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(213,01,'KURRAPALLE',21577,07,'SAKUNALAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(214,02,'P.YERRABALI PALLE',21577,07,'SAKUNALAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(215,01,'SAKUNALAPALLE',21578,07,'SAKUNALAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(216,03,'BODABANDA HIGHWAY',21578,07,'SAKUNALAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(217,02,'BODABANDA',21578,07,'SAKUNALAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(218,01,'PULLAYAPALLE',21579,08,'PULLAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(219,02,'ANNAMPALLE',21579,08,'PULLAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(220,04,'KRISHNAREDDI PALLE',21580,14,'BANDAGANIPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(221,05,'LINGAMNENI PALLE',21580,14,'BANDAGANIPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(222,03,'BIJJAMPALLE',21580,14,'BANDAGANIPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(223,02,'KOTHAPALLE',21580,14,'BANDAGANIPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(224,01,'BANDAGANIPALLE',21580,14,'BANDAGANIPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(225,07,'CHERLOPALLE',21581,17,'G.AYYAVARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(226,06,'G. AYYAVARIPALLE',21581,17,'G.AYYAVARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(227,05,'DANURUPALLE',21581,17,'G.AYYAVARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(228,04,'VADDIPALEM',21581,16,'G.CHERLOPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(229,03,'G. CHERLOPALLE H.W.',21581,16,'G.CHERLOPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(230,02,'SANJEEVARAJUPALLE',21581,15,'G.CHERUVU PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(231,01,'G. CHERUVU PALLE',21581,15,'G.CHERUVU PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(232,09,'RACHPUTVARI STREET',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(233,09,'GOLLAPALEM',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(234,09,'CHAKALIVEEDHI',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(235,09,'GAJULAVEEDI',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(236,04,'VEERAREDDI PALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(237,01,'KONDAYAPALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(238,09,'KARNAMVEEDI',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(239,03,'MASAI PETA',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(240,09,'BRAHMANAVARI STREET',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(241,05,'GANUGAPENTAPALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(242,06,'GANGIREDDI PALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(243,07,'DURGAMPALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(244,08,'NELATURU',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(245,09,'SARVARABABU',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(246,09,'HARIJANWADA',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(247,09,'GUNTABAZAR',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(248,09,'PEDDA BAZAR',21582,12,'NELATURU',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(249,02,'VENKATARAO PALLE',21582,11,'KONDAYAPALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(250,01,'APPASAMUDRAM',21583,09,'APPASAMUDRAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(251,02,'MASEEDU PALLE',21583,09,'APPASAMUDRAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(252,03,'NADIMPALLE',21583,09,'APPASAMUDRAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(253,04,'CHAVATAPALLE',21583,09,'APPASAMUDRAM',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(254,05,'MALLAREDDI PALLE',21583,10,'DASARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(255,06,'VADLAMUDI PALLE',21583,10,'DASARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(256,07,'DASARI PALLE',21583,10,'DASARI PALLE',10)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(257,01,'PAMURUPALLE',21584,01,'PAMURUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(258,01,'PEDDIREDDIPALLE',21585,02,'PEDDIREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(259,02,'BRAHMANAPALLE',21585,02,'PEDDIREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(260,03,'KOTHAPALLE',21585,02,'PEDDIREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(261,01,'THODUGUPALLE',21586,01,'PAMURUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(262,01,'THURPUPALEM',21587,03,'VIRUVURU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(263,01,'VIRUVOORU',21588,03,'VIRUVURU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(264,04,'PAPANNAGARIPALLE',21588,03,'VIRUVURU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(265,03,' KRISHNAMRAJUPALLE',21588,04,'KRISHNAMRAJUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(266,02,'KRIHSNAGANDHIPURAM',21588,04,'KRISHNAMRAJUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(267,02,'DHARMAVARAM',21589,05,'NORTH KONDAYAPALEM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(268,01,'KONDAYAPALEM',21589,05,'NORTH KONDAYAPALEM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(269,01,'KONDAYAPALEM (M)',21589,99,'NELLORE MUNICIPOLITY',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(270,02,'SATHUVARIPALLE',21590,06,'THOTALACHERUVUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(271,01,'THOTALACHERUVUPALLE',21590,06,'THOTALACHERUVUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(272,03,'KONDARAJU PALLE',21590,06,'THOTALACHERUVUPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(273,01,'YERRAMREDDIPALLE',21591,07,'YERRAMREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(274,01,'MOHAMMADAPURAM',21592,08,'MOHAMMADAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(275,02,'NAIDUPALLE',21592,08,'MOHAMMADAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(276,01,'GOLLAPALLE',21593,08,'MOHAMADAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(277,03,'NEW HARIJANWADA',21891,08,'GOLLAPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(278,02,'A.PALEM',21891,08,'GOLLAPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(279,01,'GOLLAPALLE',21891,08,'GOLLAPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(280,01,'GOLLAPALLE',22154,10,'NERNOOR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(281,03,'ASHOKNAGAR',21594,09,'VEMPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(282,04,'BHOGYAMVARIPALLE',21594,09,'VEMPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(283,01,'VEMPADU',21594,09,'VEMPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(284,02,'VENGAMAMBAPURAM',21594,09,'VEMPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(285,01,'DAMANCHERLA (U.I)',21595,00,'',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(286,02,'THIMMAREDDIPALLE',21595,10,'THIMMAREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(287,03,'KONDAREDDIPALLE',21595,11,'KONDAREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(288,04,'RAMAPURAM',21595,12,'RAMAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(289,05,'KAKOLLUVARIPALLE',21595,12,'RAMAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(290,06,'HUSSAINNAGAR',21595,12,'RAMAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(291,01,'DAKKANUR',21596,13,'DAKKANUR',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(292,01,'THURPU ROMPIDODLA',21597,14,'THURPU ROMPIDODLA',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(293,02,'VENKATESWRA PURAM',21597,14,'THURPU ROMPIDODLA',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(294,01,'BONGARARULAPADU (U.I)',21598,00,'',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(295,01,'NARASIMHAPURAM',21599,15,'GANESHWARAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(296,02,'JANAGAM PALLE',21600,16,'VARIKUNTAPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(297,01,'BHASKARAPURAM',21600,16,'VARIKUNTAPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(298,01,'VARIKUNTAPADU',21601,16,'VARIKUNTAPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(299,02,'ANDHRAVARIPALLE',21601,16,'VARIKUNTAPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(300,01,'KANCHERUVU',21602,17,'KANCHERUVU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(301,04,'NALLBOTHULAVARIPALLE',21604,21,'NALLABOTHULAVARIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(302,03,'KOTHAPALLE',21604,20,'KONDAREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(303,02,'KONDAREDDIPALLE',21604,20,'KONDAREDDIPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(304,01,'GUVVADI',21604,19,'GUVVADI',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(305,01,'THURPU BOYAMADUGULA',21605,22,'THURPU BOYAMADUGULA',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(306,01,'ALIVELUMANGAPURAM',21606,22,'THURPU BOYAMADUGULA',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(307,01,'THURPU CHENNAMPALLE',21607,23,'THURPU CHENNAMPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(308,01,'JADADEVI',21608,24,'JADADEVI',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(309,01,'KANIAMPADU',21609,24,'JADADEVI',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(310,02,'CHINNA KANYAMPALLE',21609,24,'JADADEVI',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(311,01,'RAMADEVULAPADU',21610,16,'VARKUNTAPADU',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(312,01,'GANESWARAPURAM',21611,15,'GANESWARAPURAM',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(313,01,'ISKADAMERLA',21612,01,'ISKADAMERLA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(314,02,'POTTIPALLE',21612,01,'ISKADAMERLA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(315,01,'VELIGANDLA',21613,02,'VELIGANDLA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(316,02,'BASIREDDIPALLI',21613,02,'VELIGANDLA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(317,01,'MARRIGUNTA',21614,03,'MARRIGUNTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(318,01,'GUDAVALLURU',21615,04,'GUDAVALLUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(319,02,'BOYYALAVARIPALEM',21615,04,'GUDAVALLUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(320,03,'RENAMALA',21616,06,'RENAMALA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(321,04,'REPALLE',21616,06,'RENAMALA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(322,02,'TURPUPALEM',21616,05,'NEKUNAMPETA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(323,01,'NEKUNAMPETA',21616,05,'NEKUNAMPETA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(324,01,'SAIPETA',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(325,06,'KOMMUPALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(326,07,'YERRABALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(327,05,'ISKAPALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(328,04,'VALLATURIVARIPALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(329,02,'KOTHAPALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(330,03,'MAKKANNAVARIPALEM',21617,07,'SAIPET',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(331,01,'BHIMAVARAPPADU',21618,08,'BHIMAVARAPPADU',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(332,02,'SATYAVOLU AGRAHARAM',21620,10,'SATYAVOLU',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(333,01,'SATYAVOLU',21620,10,'SATYAVOLU',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(334,03,'LINGANAPALEM',21620,10,'SATYAVOLU',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(335,01,'PARLAPALLE',21621,11,'PARLAPALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(336,02,'CHOWTAPALEM',21831,01,'PARLAPALLE',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(337,01,'PARLAPALLE',21831,01,'PARLAPALLE',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(338,01,'PARLAPALLE',22007,28,'PARLAPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(339,01,'MALLAVARAPPADU',21622,11,'PARLAPALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(340,01,'GANUGAPENTA',21623,12,'GANUGAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(341,02,'PERIKAPALEM',21623,12,'GANUGAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(342,01,'AUDIMURTHIPURAM',21624,12,'GANUGAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(343,02,'MALA PALLE',21625,13,'CHINTALADEVI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(344,01,'THURPU BRAHMANAPALLE',21625,13,'CHINTALADEVI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(345,04,'KOTHAUPPULUR',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(346,10,'VADDEPALEM',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(347,08,'UPPALURU SHANTI NGARAM',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(348,07,'MALA PALEM',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(349,05,'PATHA UPPULUR',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(350,03,'CHINTALADEVI FORM',21626,13,'CHINTALADEVI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(351,02,'BAGADIPALLE',21626,13,'CHINTALADEVI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(352,01,'CHINTHALADEVI',21626,13,'CHINTALADEVI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(353,06,'MADIGA PALEM',21626,14,'UPPULUR',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(354,02,'DASARIVARI PALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(355,04,'KOTTEVARIPALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(356,03,'BASIREDDIPALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(357,05,'MANNEMVARIPALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(358,01,'THURPUYERRABALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(359,06,'CHINNABOTHA PALLE',21627,15,'THURPUYERRABALLE',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(360,02,'CHALLAVARIPALLE',21628,16,'GARIMENAPETNA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(361,03,'KOVIVARIPALLE',21628,16,'GARIMENAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(362,01,'GARIMENAPENTA',21628,16,'GARIMENAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(363,04,'VENKATARANGAPURAM',21628,16,'GARIMENAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(364,01,'GARIMENAPENTA',21940,01,'TEGACHERLA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(365,01,'YERRABOTLAPALLE',21629,16,'GARIMENAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(366,01,'CHALLAGIRIGALA',21630,16,'GARIMENAPENTA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(367,01,'GOTTIGUNDALA',21631,17,'GOTTIGUNDALA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(368,02,'GOTTIGUNDALA PALEM',21631,18,'GOTTIGUNDALA PALEM',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(369,03,'PUPPALAVARIPALEM',21631,17,'GOTTIGUNDALA',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(370,01,'SETTIPALEM',21632,19,'SETTIPALEM',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(371,01,'THURPU JANGALAPALLE',21633,19,'SETTIPALEM',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(372,01,'KUNKUVARIPALEM',21634,09,'KOMMI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(373,01,'RAMANUJAPURAM',21635,09,'KOMMI',03)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(374,01,'CHODAVARAM',21636,01,'CHODAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(375,03,'S.R.RAJAPURAM KANDRIKA',21637,02,'KESAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(376,02,'THIMMA SAMUDRAM',21637,02,'KESAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(377,01,'KESAVARAM',21637,02,'KESAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(378,02,'SIDDAMA KHANDRIGA',22415,02,'KESAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(379,04,'KONDU GUNTA',22415,02,'KESAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(380,01,'KESAVARAM',22415,02,'KESAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(381,03,'RAGHAVA PURAM',22415,02,'KESAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(382,02,'CHINTHALAPALEM',21638,03,'GATTUPALLI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(383,03,'RAMAVARAPPADU',21638,03,'GATTUPALLI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(384,01,'GATTUPALLI',21638,03,'GATTUPALLI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(385,04,'KARKOLUPALEM',21638,03,'GATTUPALLI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(386,01,'JAMMALAPALEM',21639,04,'JAMMALAPALEM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(387,10,'GAMALLAPALEM',21640,06,'LINGA RAJU AGRAHARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(388,07,'LINGA RAJU AGRAHARAM',21640,06,'LINGA RAJU AGRAHARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(389,06,'APPARAO THOTA',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(390,08,'KOTHUR',21640,06,'LINGA RAJU AGRAHARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(391,09,'TAGETIVARIPALEM',21640,06,'LINGA RAJU AGRAHARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(392,11,'GOPANNAPALEM',21640,07,'GOPANNAPALEM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(393,04,'TANKAMVARIPALEM',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(394,03,'KAMMAPALEM',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(395,05,'NELLORE NAGAR',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(396,01,'JALADANKI',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(397,02,'NAGIREDDY PALEM',21640,05,'JALADANKI',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(398,01,'ANNAVARAM',21641,08,'ANNAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(399,02,'GADDAMVARIPALEM',21641,08,'ANNAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(400,03,'DASARI AGRAHARAM',21641,08,'ANNAVARAM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(401,02,'MITTAPALEM',21642,09,'CHINA KRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(402,01,'CHINA KRAKA',21642,09,'CHINA KRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(403,03,'VEMULPADU',21642,10,'VEMULPADU',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(404,01,'KODANDARAMAPURAM',21643,10,'VEMULPADU',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(405,06,'KAMMAVARI PALEM',21644,12,'KAMMAVARIPALEM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(406,04,'GANNAVARAM GUNTA',21644,11,'BRAHMANAKRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(407,03,'ANUMAKONDA PALEM',21644,11,'BRAHMANAKRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(408,02,'B.K. AGRAHARAM',21644,11,'BRAHMANAKRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(409,01,'BRAHMANAKRAKA',21644,11,'BRAHMANAKRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(410,05,'VIJAYANAGARAM',21644,11,'BRAHMANAKRAKA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(411,01,'SOMAVARAPPADU',21645,15,'SOMAVARAPPADU',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(412,02,'BOYALAPADU',21645,15,'SOMAVARAPPADU',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(413,03,'KOTHAPALEM',21645,16,'KOTHAPALEM',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(414,01,'KRISHNAPADU',21646,14,'KRISHNAPADU',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(415,02,'GURAVAREDDY PALEM',21647,13,'CHAMADALA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(416,01,'CHAMADALA',21647,13,'CHAMADALA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(417,03,'AUDIREDDY PALEM',21647,13,'CHAMADALA',04)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(418,01,'KOTHAPALLE',22563,03,'KOTHAPALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(419,01,'GOWRAVARAM',21903,02,'GOWRAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(420,01,'MALLAYAPALEM',22607,21,'THOGARAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(421,01,'THELLAPADU',21677,01,'THELLAPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(422,04,'AGRAHARAM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(423,14,'RAJUPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(424,13,'THOKALAVARIPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(425,12,'NARSAREDDIPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(426,11,'KUDUMALADINNEPADU',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(427,10,'TURUPUMADIGAPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(428,09,'KRIHSNAREDDIPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(429,08,'TURPUMALAPALEM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(430,07,'KOTTALU',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(431,05,'KODAREDDIPALEM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(432,17,'NADIMIMADIGAPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(433,03,'MURTHIRAJUPALEM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(434,02,'HARIJANAPALEM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(435,01,'CHINA ANNALURU',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(436,06,'THOGURUVARIPALEM',21678,02,'CHINA ANNALURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(437,24,'YEPINAPI',21678,05,'YEPINAPI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(438,15,'CHANDRAREDDIPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(439,25,'HARIJANAWADA',21678,05,'YEPINAPI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(440,16,'THULLIBILLIVARIPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(441,23,'MARTHULAVARIPALEM',21678,04,'GANGIREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(442,22,'BATTUVARIPALEM',21678,04,'GANGIREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(443,21,'MALAPALEM',21678,04,'GANGIREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(444,20,'MADIGAPALEM',21678,04,'GANGIREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(445,19,'GANGIREDDIPALEM',21678,04,'GANGIREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(446,18,'MITTAPALEM',21678,03,'KRISHNAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(447,01,'DUBAGUNTA',21679,06,'TURUPU DUBAGUNTA',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(448,01,'DUBAGUNTA',21769,01,'DUBAGUNTA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(449,04,'JIRRAVARIPALEM',21680,07,'KRAKUTURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(450,03,'KHADARPALEM',21680,07,'KRAKUTURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(451,02,'THIMMAREDDIPALEM',21680,07,'KRAKUTURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(452,01,'KRAKUTURU',21680,07,'KRAKUTURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(453,01,'KUMMARAKONDURU',21681,14,'KUMMARAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(454,05,'ATLAAGRAHARAM',21682,12,'PEDAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(455,03,'NEERUKATTUPALEM',21682,12,'PEDAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(456,01,'PEDA KONDURU',21682,12,'PEDAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(457,02,'AYYAPAREDDIPALEM',21682,12,'PEDAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(458,04,'PEDDAPADU',21682,12,'PEDAKONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(459,06,'POLAMPADU',21682,13,'POLAMPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(460,07,'VEERAREDDIPALEM',21682,13,'POLAMPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(461,01,'RAVULAKOLLU',21683,08,'RAVULAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(462,05,'KOPPOLUVARIPALEM',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(463,06,'PAPANNAMUSALIPALEM',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(464,04,'KAMALAVARIPALEM',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(465,03,'GAUDHANVARIPALEM',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(466,02,'JIRRAVARIPALEM',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(467,01,'KALIGIRI',21684,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(468,02,'BASIREDDIPALEM',21685,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(469,01,'VEERANAKOLLU',21685,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(470,03,'AMBATIVARIPALEM',21686,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(471,01,'NAGASAMUDRAM',21686,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(472,02,'NAGIREDDIPALEM',21686,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(473,04,'CHEEMALAVARIPALEM',21686,10,'VEERANAKOLLU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(474,01,'VENKANNAPALEM',21687,09,'KALIGIRI',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(475,01,'PEDAANNALURU',21688,11,'VELAGAPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(476,02,'KOTHUR',21689,11,'VELAGAPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(477,01,'VELAGAPADU',21689,11,'VELAGAPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(478,03,'KAMMAVARIPALEM',21689,11,'VELAGAPADU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(479,02,'DANTUPALEM',21690,17,'KAVALI MUSTHAPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(480,01,'KAVALI MUSTHAPURAM',21690,17,'KAVALI MUSTHAPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(481,02,'PATHANAPURAM',21691,17,'KAVALI MUSTHAPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(482,01,'KOTHAPETA',21691,17,'KAVALI MUSTHAPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(483,01,'KOTHAPETA',21714,03,'KOTHAPETA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(484,02,'VENGANAPALEM',21714,04,'VENGANAPALEM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(485,01,'KOTHAPETA',22385,24,'PUNNEPALLE',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(486,01,'ANANTHAPURAM',21692,18,'ANANTHAPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(487,02,'VARAKAVIPUDU',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(488,03,'MITTAPALEM',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(489,01,'ANANTHAPURAM',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(490,04,'KUMMARIPALEM',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(491,05,'VARAKAVIPUDI G.C.',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(492,06,'RAYAKUNTACHILKA',22083,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(493,01,'SIDDANA KONDURU',21693,16,'SIDDANA KONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(494,02,'MITTAPALEM',21693,16,'SIDDANA KONDURU',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(495,01,'PARIKOTA',21694,15,'PATIKOTA',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(496,01,'LAKSHMIPURAM',21695,19,'LAKSHMIPURAM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(497,04,'THURUPU KOTHUR',21696,22,'YERUKALAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(498,03,'YERUKALAREDDIPALEM',21696,22,'YERUKALAREDDIPALEM',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(499,02,'WEST KOTHURU',21696,20,'GUDLADONA WEST',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(500,01,'TURPU GUDLADONA',21696,20,'GUDLADONA WEST',07)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(501,03,'GOLLAVARIPALLI',21697,01,'CHAKALAKONDA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(502,04,'BATHINAVARI PALLI',21697,01,'CHAKALAKONDA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(503,02,'POLINAIDUPALLE',21697,01,'CHAKALAKONDA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(504,01,'CHAKALAKONDA',21697,01,'CHAKALAKONDA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(505,01,'JANARDHANAPURAM',21698,02,'JANARDHANAPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(506,01,'KATEPALLE',21699,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(507,04,'KANDEVVARI PALEM',21699,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(508,03,'VANNURAPPA PALEM',21699,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(509,02,'B.C. COLONY',21699,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(510,01,'THAMIDAPADU',21700,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(511,02,'MALLAREDDI PALLE',21700,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(512,03,'MAHADEVNAGAR',21700,03,'THAMIDAPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(513,03,'GUVVIGUNTAPALEM',21701,04,'UTUKURU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(514,05,'THAKKELLA PADU',21701,04,'UTUKURU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(515,04,'INDIRANAGAR COLONY',21701,04,'UTUKURU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(516,01,'UTUKURU',21701,04,'UTUKURU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(517,02,'HARIJANACOLONY',21701,04,'UTUKURU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(518,04,'VENKATANAREDDI PALEM',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(519,05,'SAMBUNIPALEM',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(520,03,'PALLI PALEM',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(521,02,'PATTAPUPALEM',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(522,01,'UTUKURU',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(523,07,'KANCHARAPALEM',21837,10,'UTUKURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(524,02,'B.C. COLONY',21702,05,'RAVIPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(525,01,'RAVIPADU',21702,05,'RAVIPADU',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(526,01,'RAVIPADU',22345,01,'RAVIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(527,01,'NANDIGUNTA',21703,06,'NANDIGUNTA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(528,01,'VINJAMUR',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(529,07,'NADIMUR',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(530,06,'PATHUR',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(531,05,'WEAVERS COLONY',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(532,04,'NARTH HARIJANAWADA',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(533,03,'B.C. COLONY',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(534,02,'B.K.R. COLONY',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(535,17,'SATHANIVARI PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(536,16,'LEKKALAVARI PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(537,15,'B.R. CHERUVU',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(538,14,'NEREDU PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(539,13,'KOKAVARI PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(540,12,'ADIANDHRA PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(541,11,'SOUTH HARIJANAWADA',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(542,10,'CHITTETIMITTA',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(543,09,'M.C. PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(544,08,'YERRABALI PALEM',21704,10,'VINJAMUR',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(545,01,'SANKAVARAM',21705,08,'SANKAVARAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(546,02,'VENKATADRIPALEM',21705,08,'SANKAVARAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(547,01,'GUNDEMADAKALA',21706,07,'GUNDEMADAKALA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(548,01,'NALLAGONDA',21707,09,'NALLAGONDA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(549,02,'CHOWTAPALLE',21707,07,'GUNDEMADAKALA',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(550,01,'A. KISTIPURAM',21708,13,'Kistipuram',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(551,02,'ARAVEETI PALLE',21708,13,'Kistipuram',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(552,01,'CHINTHALAPALEM',21709,13,'A. KISTHIPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(553,03,'KARAGOGULAPADU',21710,12,'BUCCAPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(554,02,'THOGATAPALEM',21710,12,'BUCCAPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(555,01,'CHANDRAPADIA',21710,12,'BUCCAPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(556,01,'BUKKAPURAM',21711,11,'BUKKAPURAM',08)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(557,01,'THEDDUPADU',21712,01,'THEDDUPADU',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(558,01,'THIMMAPURAM',21713,02,'THIMMAPURAM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(559,05,'BANDAKINDAPALLE',21715,06,'BANDAKINDA PALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(560,01,'NARRAWADA',21715,05,'NARRAWADA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(561,02,'GUDDIVARIPALEM',21715,05,'NARRAWADA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(562,04,'VADDIPALEM',21715,05,'NARRAWADA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(563,03,'ULAVARIPALEM',21715,05,'NARRAWADA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(564,01,'MANDALLAPALLE',21716,07,'BODUVARIPALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(565,01,'MANDALLANAIDUPALLE',21717,07,'BODUVARIPALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(566,02,'BODUVARIPALLE',21717,07,'BODUVARIPALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(567,01,'SOMALAREGADA',21718,08,'SOMALAREGADA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(568,07,'MUTHARASIPALLI',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(569,08,'VETTIPALEM',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(570,09,'RAGAVAREDDIPALLE',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(571,10,'CHITTALAGUNTA',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(572,05,'KATTA KINDAPALLE',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(573,04,'A.C. NAGAR COLONY',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(574,06,'KAMMAVARIPALEM',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(575,03,'DUTTALUR CENTRE',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(576,02,'LAKSHMIPURAM',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(577,01,'DUTTALUR',21719,09,'DUTTALUR',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(578,01,'BRAHMESWARAM',21720,10,'BRAHMESWARAM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(579,03,'RAVILLAVARIPALLE',21721,11,'YERRUKOLLU',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(580,02,'KAMMAVARIPALEM',21721,11,'YERRUKOLLU',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(581,01,'YERUKOLLU',21721,11,'YERRUKOLLU',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(582,01,'RACHAVARIPALLE',21722,12,'RACHAVARIPALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(583,02,'BOTIKARLAPADU',21723,14,'REDLADINNE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(584,01,'PAPAMPALLE',21723,13,'PAPAMPALLE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(585,01,'VENKATAMPETA',21724,15,'VENKATAMPETA',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(586,01,'NANDIPADU',21725,16,'NANDIPADU',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(587,02,'REDLADINNE',21725,14,'REDLADINNE',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(588,03,'THURAKAPALLE',21726,17,'BHYRAVARAM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(589,02,'JANGALAPALLE',21726,17,'BHYRAVARAM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(590,01,'BHYRAVARAM',21726,17,'BHYRAVARAM',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(591,01,'BHYRAVARAM',22339,23,'BHYRAVARAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(592,02,'VENKATA PURAM',21727,01,'CHABOLU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(593,01,'CHABOLU',21727,01,'CHABOLU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(594,01,'KAMPASAMUDRAM',21728,02,'KAMPASAMUDRAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(595,01,'BUDAWADA',21729,03,'BUDAWADA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(596,02,'KRISHNA PURAM',21729,03,'BUDAWADA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(597,03,'BEDSU PALLI',21729,03,'BUDAWADA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(598,04,'TIMMAYAPALEM',21729,03,'BUDAWADA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(599,03,'EPILAGUNTA',21730,05,'EPILAGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(600,02,'SETTISAMUDRAM',21730,05,'CHILAKAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(601,01,'CHILAKAPADU',21730,04,'CHILAKAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(602,01,'KADIRINENIPALLE',21731,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(603,01,'CHINAMACHANURU',21732,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(604,02,'PEDDAMACHANURU',21732,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(605,03,'SRINIVASAPURAM',21732,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(606,04,'KUKKUVARIPALLI',21732,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(607,01,'NAGARAJUPADU',21733,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(608,02,'POLIREDDYPALLI',21733,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(609,01,'NERADANAMPADU',21734,06,'CHINAMACHANURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(610,01,'BHEEMAVARAM',21735,07,'BHEEMAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(611,02,'PICHIVENGOM PALLI',21735,07,'BHEEMAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(612,01,'BHEEMAVARAM',22211,12,'K.BATLAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(613,01,'BHEEMAVARAM',22519,13,'BHEEMAVARAM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(614,03,'KHADARPUR',21736,10,'KHADARPUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(615,01,'ALLAMPADU',21736,08,'ALLAMPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(616,04,'SANNUVARIPALLE',21736,10,'KHADARPUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(617,02,'KOTHA ALLAMPADU',21736,08,'ALLAMPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(618,02,'MARRIPADU',21736,09,'MARRIPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(619,01,'ALLAMPADU',22433,21,'ALLAMPADU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(620,03,'ISKAPALLE',21737,11,'IRALAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(621,02,'RAJUPALEM',21737,11,'IRALAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(622,01,'IRALAPADU',21737,11,'IRALAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(623,03,'PICHIGUNTA PADU',21738,12,'PEGALLAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(624,01,'PEGALLAPADU',21738,12,'PEGALLAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(625,02,'UMMAYAPALLE',21738,12,'PEGALLAPADU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(626,01,'BRAHMANAPALLE',21739,13,'BRAHMANAPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(627,02,'KARUKUPALLI',21739,13,'BRAHMANAPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(628,01,'BRAHMANAPALLE',21920,02,'BRAHMANAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(629,04,'BATA',21740,14,'NAGINENIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(630,03,'YENEPALLE',21740,14,'NAGINENIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(631,02,'MITTAPALEM',21740,14,'NAGINENIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(632,01,'NAGINENIGUNTA',21740,14,'NAGINENIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(633,01,'SINGANAPALLE',21741,15,'SINGANAPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(634,02,'T.N. PET',21741,15,'SINGANAPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(635,04,'ANANTHAPURAM',21742,16,'PADAMATINAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(636,01,'PADAMATINAIDUPALLE',21742,16,'PADAMATINAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(637,03,'KUNTANELLIPADU',21742,16,'PADAMATINAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(638,02,'KADIRINAIDUPALLE',21742,16,'PADAMATINAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(639,01,'CHUNCHULUR',21743,17,'CHUNCHULUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(640,02,'RAJUPALEM',21743,17,'CHUNCHULUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(641,03,'GANGAVARAM',21743,17,'CHUNCHULUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(642,04,'KOTHAPALLE',21743,17,'CHUNCHULUR',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(643,02,'TIKKAVARAM',21744,18,'TIKKAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(644,03,'NUTHALA',21744,18,'TIKKAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(645,01,'PALLAVOLU',21744,18,'TIKKAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(646,03,'KETHIGUNTA',21745,21,'KETHIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(647,02,'KANDRIGA',21745,20,'PONGURU KANDRIKA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(648,04,'PALLI PADU',21745,21,'KETHIGUNTA',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(649,01,'PONGURU',21745,19,'PONGURU',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(650,01,'NANDAVARAM',21746,22,'NANDAVARAM',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(651,02,'RAMANAIDUPALLE',21746,23,'RAMANAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(652,03,'RAMPALLE',21746,23,'RAMANAIDUPALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(653,02,'KONASAMUDRAM',21747,24,'D.C. PALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(654,01,'D.C. PALLE',21747,24,'D.C. PALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(655,03,'KHANSEHEBPET',21747,24,'D.C. PALLE',11)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(656,01,'GANDLAVEDU',21748,01,'PADAKANDLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(657,01,'BOTIKARLAPADU',21749,01,'PADAKANDLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(658,01,'PADAKANDLA',21750,01,'PADAKANDLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(659,02,'VENNAWADA',21750,02,'VENNAWADA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(660,03,'YARRAVALLI',21750,01,'PADAKANDLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(661,01,'ARAVEDU',21751,03,'ARAVEDU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(662,02,'A.JANGALAPALLE',21751,03,'ARAVEDU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(663,01,'KARATAMPADU',21752,04,'KARATAMPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(664,02,'SATHANUPALLE',21752,04,'KARATAMPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(665,01,'BOILA CHIRUVELLA',21753,05,'BOILA CHIRUVELLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(666,01,'NABBINAGARAM',21754,05,'BOILA CHIRUVELLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(667,01,'PAMIDIPADU',21755,06,'PAMIDIPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(668,01,'NEELOREPALEM',21756,07,'NEELOREPALEM',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(669,01,'CHIRUVELLA KHANDRIKA',21757,05,'BOYLI CHIRIVELLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(670,01,'NARAMPETA',21758,08,'NARAMPETA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(671,01,'MAHIMALUR',21759,09,'MAHIMALUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(672,02,'BASAVARAJUPALEM',21759,09,'MAHIMALUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(673,04,'RAMASWAMIPALLE',21759,10,'RAMASWAMIPALLE',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(674,05,'BANTLAPALLE',21759,10,'RAMASWAMIPALLE',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(675,03,'KOTA PADU',21759,09,'MAHIMALUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(676,03,'CHERLOYADAVALLI',21760,13,'CHERLOYADAVALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(677,02,'RAVULAKOLLU',21760,12,'RAVULAKOLLU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(678,01,'NAGULAPADU',21760,11,'NAGULAPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(679,01,'NAGULAPADU',22226,20,'PALUGODU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(680,01,'JANGALAPALLE',22253,03,'JANGALAPALLE',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(681,01,'JANGALAPALLI',21761,15,'DEPURU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(682,01,'JANGALAPALLI',22627,13,'ABAKA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(683,01,'DEPURU',21762,15,'DEPURU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(684,01,'BANDARUPALLI',21763,16,'BANDARUPALLE',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(685,02,'MURAGALLA',21763,17,'MURAGALLA',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(686,01,'KANUPURUPALLI',21764,18,'KANUPURUPALLE',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(687,04,'CHOWTAPALLE',21937,18,'CHOWTAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(688,03,'VEDANAPARTHI',21937,18,'CHOWTAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(689,01,'KANUPURUPALLE',21937,17,'KANUPURUPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(690,02,'VERUBOTLAPALLE',21937,17,'KANUPURUPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(691,02,'ASWINIPURAM',21765,14,'NUVVURUPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(692,01,'NUVVURUPADU',21765,14,'NUVVURUPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(693,07,'JALAYANAGARAM',21766,23,'VENKATARAO PALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(694,01,'ATMAKUR',21766,22,'ATMAKUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(695,02,'ALLIPURAM',21766,22,'ATMAKUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(696,06,'NARASA PURAM',21766,22,'VENKATARAO PALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(697,03,'PERAREDDI PALLI',21766,22,'ATMAKUR',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(698,05,'VENKATARAO PALLI',21766,23,'VENKATARAO PALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(699,08,'MUSTAPURAM',21766,23,'VENKATARAO PALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(700,01,'VASILI',21767,24,'VASILI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(701,03,'KOTHAUR & RAMAPURAM',21768,21,'APPARAO PAELM',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(702,02,'NALLAPAREDDI PALLI',21768,20,'NALLAPAREDDI PALLI',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(703,04,'APPARAO PAELM',21768,21,'APPARAO PAELM',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(704,01,'BATTEPADU',21768,19,'BATTEPADU',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(705,05,'MUSLIM PALEM',21768,21,'APPARAO PALEM',12)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(706,02,'TIMMANAIDU PALLI',21770,02,'CHOUTA BHEEMAVARAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(707,01,'CHOUTA BHEEMAVARAM',21770,02,'CHOUTA BHEEMAVARAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(708,01,'GUDIPADU',21771,03,'GUDIPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(709,02,'ABBASAHEB PETA',21771,03,'GUDIPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(710,03,'KOTHA PALLE',21771,03,'GUDIPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(711,04,'REDDI PALEM',21772,04,'GUMPARLAPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(712,01,'GUMPARLAPADU',21772,04,'GUMPARLAPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(713,03,'VADDEPALEM',21772,04,'GUMPARLAPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(714,02,'GOLLA PETA',21772,04,'GUMPARLAPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(715,01,'PANDIPADU',21773,05,'PANDIPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(716,02,'CHINNABBIPURAM',21774,06,'PEDABBIPURAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(717,01,'PEDABBIPURAM',21774,06,'PEDABBIPURAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(718,01,'RAJAVOLU',21775,07,'RAJAVOLU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(719,02,'PUTTURU VARI PALLE',21775,07,'RAJAVOLU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(720,03,'TATIREDDY PALLE',21775,07,'RAJAVOLU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(721,01,'PONUGODU',21776,08,'PONUGODU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(722,02,'GANDUVARI PALLE',21777,09,'A.S. PETA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(723,01,'A.S. PETA',21777,09,'A.S. PETA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(724,01,'KAKARLAPADU',21778,10,'ZAMMAVARAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(725,01,'ZAMMAVARAM',21779,10,'ZAMMAVARAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(726,01,'KAVALI YADAVALLI',21780,11,'K. YADAVALLI',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(727,01,'VELPULAGUNTA',21781,12,'VELPULAGUNTA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(728,01,'KUNALAMMAPADU',21782,13,'AKBARABAD',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(729,01,'AKBARABAD',21783,13,'AKBARABAD',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(730,01,'ZUVVALAGUNTAPALLE',21784,18,'HASANAPURAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(731,03,'MAMIDICHETLA PALLE',21785,14,'CIHRAMANA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(732,01,'CHIRAMANA',21785,14,'CIHRAMANA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(733,02,'MADARA BAD',21785,14,'CIHRAMANA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(734,01,'CHANDULURUPADU',21786,09,'A.S. PETA',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(735,01,'ANUMASAMUDRAM',21787,15,'ANUMASAMUDRAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(736,02,'TELLAPADU',21787,15,'ANUMASAMUDRAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(737,01,'KUPPURUPADU',21788,16,'KUPPURUPADU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(738,01,'KONDAMEEDI KONDURU',21789,17,'K.KONDURU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(739,01,'HASANAPURAM',21790,18,'HASANAPURAM',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(740,01,'SRIKOLANU',21791,19,'SRIKOLANU',13)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(741,01,'ISAKAPALLE',21603,18,'ISAKAPALLE',02)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(742,01,'ISAKAPALLE',21923,05,'NUKANAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(743,02,'PARIREDDY KANDRIGA',21820,25,'GRADDAGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(744,01,'GRADDAGUNTA',22384,25,'GRADDAGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(745,01,'GRADDAGUNTA',22529,07,'GRADDAGUNTA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(746,02,'GOLKONDAYATHOPU',21828,03,'DAMPURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(747,01,'DAMPURU',21828,03,'DAMPURU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(748,02,'UPPALAPADU',21829,04,'VAVILLA',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(749,01,'VAVILLA',21829,04,'VAVILLA',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(750,02,'THUMMAGUNTA',21830,02,'CHOWKACHERLA',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(751,01,'CHOWKACHERLA',21830,02,'CHOWKACHERLA',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(752,04,'MANNADHARARAOPETA',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(753,05,'MORALILAREDDIGUNTA',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(754,06,'VELAGALAMMA GUNTA',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(755,07,'PATELNAGAR',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(756,03,'VADDIPALEM',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(757,02,'KHANDRIGA',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(758,01,'VIDAVALUR',21832,12,'VIDAVALUR',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(759,05,'BADRACHALAM',21833,14,'ANNAREDDIPALEM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(760,04,'ANNAREDDI PALEM',21833,14,'ANNAREDDIPALEM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(761,03,'CHELIKALA',21833,13,'MUDIVARTHI',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(762,01,'MUDIVARTHI',21833,13,'MUDIVARTHI',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(763,02,'UPPARATHOPU',21833,13,'MUDIVARTHI',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(764,01,'ALAGANIPADU',21834,11,'ALAGANIPADU',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(765,02,'DINNE',21835,06,'DINNE',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(766,01,'DANDIGUNTA',21835,05,'DANDIGUNTA',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(767,03,'RAMATHEERTHAM',21836,08,'RAMATHEERTHAM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(768,05,'VENKATANARAYANAPURAM',21836,09,'RAMACHANDRAPURAM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(769,07,'P.PALEM OF PONNAPUDI',21836,09,'RAMACHANDRAPURAM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(770,04,'PONNAPUDI',21836,09,'RAMACHANDRAPURAM',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(771,02,'VEERAREDDI PALEM',21836,07,'VARINI',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(772,01,'VARINI',21836,07,'VARINI',16)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(773,04,'VADDIPALEM',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(774,01,'KOTHAVANGALLU',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(775,03,'BONTHALA PALEM',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(776,05,'GUNTA KATTALA @ D.DIBBA',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(777,06,'CHRISTION PALEM',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(778,07,'SRINIVASA PURAM',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(779,08,'GOPAL NAGAR',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(780,09,'GANDHIJANASANGAM',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(781,10,'CHIKIRENI GUNTA',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(782,02,'PATHAVANGALLU',21838,11,'KOTHAVANGALLU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(783,01,'YELLAYAPALEM',21839,19,'YELLAYAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(784,07,'MICLIM PET',21839,20,'MICLIM PET',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(785,06,'RAMA PURAM',21839,19,'YELLAYAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(786,05,'RAYA REDDY THOPU',21839,19,'YEELLAYA PALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(787,04,'JYOTHSNAGAR',21839,19,'YEELLAYA PALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(788,02,'DASARI PALEM',21839,19,'YELLAYAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(789,03,'REDDY GUNTA',21839,19,'YELLAYAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(790,07,'PEYYALA PALEM',21840,14,'PEYYALA PALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(791,06,'PEMMAREDDI PALEM',21840,13,'GANDAVARAM (SOUTH)',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(792,05,'THATAKULA DINNE',21840,13,'GANDAVARAM (SOUTH)',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(793,04,'CHINTACHELIKA',21840,13,'GANDAVARAM (SOUTH)',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(794,03,'GIRIJANA COLONY',21840,13,'GANDAVARAM (SOUTH)',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(795,02,'DARGA HARIJANAWADA',21840,13,'GANDAVARAM (SOUTH)',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(796,01,'GANDAVARAM',21840,12,'GANDAVARAM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(797,01,'BODDUVARIPALEM',21841,08,'BODDUVARIPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(798,07,'KAMMAPALEM',21841,10,'KAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(799,09,'SANJEEVA NAGAR COLONY',21841,10,'KAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(800,08,'GURRALA DINNE',21841,10,'KAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(801,06,'CHANDRASEKHARA PURAM',21841,10,'KAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(802,05,'SIVAPURAM COLONY',21841,09,'SOUTH HARIJANAWADA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(803,04,'SOUTH HARIJANAWADA',21841,09,'SOUTH HARIJANAWADA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(804,03,'GAMALLAPALEM',21841,09,'SOUTH HARIJANAWADA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(805,02,'CHAYAPPANAGAR',21841,09,'SOUTH HARIJANAWADA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(806,03,'S.C. COLONY',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(807,13,'KONAMPADU',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(808,12,'VADDIPALEM',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(809,11,'RAILWAYSTATION CENTRE',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(810,10,'THUMMALAVARI THOPU',21842,02,'REGADICHELIKA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(811,09,'GUNTAKATTA EETHA THOPU',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(812,08,'PALLIPALEM',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(813,15,'L.B.NAGAR',21842,02,'REGADCHELIKA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(814,05,'B.C. COLONY',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(815,16,'RACHARLAPADU',21842,02,'REGADCHELIKA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(816,02,'ARUNDATHICOLONY',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(817,01,'TALAMANCHI',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(818,07,'PADMANABHASATRAM',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(819,14,'REGADCHELIKA',21842,02,'REGADCHELIKA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(820,06,'CHALIVENDRA',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(821,04,'SREERAMNAGAR COLONY',21842,01,'TALAMANCHI',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(822,01,'DAMEGUNTA',21843,03,'DAMEGUNTA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(823,04,'KONDAYAPALEM',21843,03,'DAMEGUNTA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(824,03,'GAMALA PALEM',21843,03,'DAMEGUNTA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(825,02,'GIRIJANAWADA',21843,03,'DAMEGUNTA',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(826,02,'PADMANABHASATRAM',21844,04,'BASAVAIPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(827,04,'LAXMIPURAM',21844,04,'BASAVAIPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(828,03,'RAMANATHAPURAM',21844,04,'BASAVAIPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(829,01,'BASAVAIPALEM',21844,04,'BASAVAIPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(830,02,'ARUNDHATIYA COLONY',21845,05,'VENKANNAPURAM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(831,01,'VENKANNAPURAM',21845,05,'VENKANNAPURAM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(832,02,'NELAGIRI SANGAM',21846,06,'GUNDALAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(833,03,'GANDHI SANGAM',21846,06,'GUNDALAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(834,01,'GUNDALAMMAPALEM',21846,06,'GUNDALAMMAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(835,02,'GUVVAGUNTA',21847,07,'NAIDUPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(836,01,'NAIDUPALEM',21847,07,'NAIDUPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(837,01,'NAIDUPALEM',22221,18,'LINGASAMUDRAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(838,06,'PRASAD THEOTRE CENTRE',21848,15,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(839,05,'MEENAMMA GUNTA',21848,15,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(840,04,'MULAKATTA SANGAM',21848,17,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(841,02,'H.W.SANGAM',21848,15,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(842,01,'KODAVALUR',21848,15,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(843,03,'GIRIJANASANGAM',21848,17,'KODAVALUR',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(844,03,'TOPATHOPU',21849,18,'NORTH RAJUPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(845,01,'NORTH RAJUPALEM',21849,18,'NORTH RAJUPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(846,02,'SATRAM',21849,18,'NORTH RAJUPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(847,01,'ALURPADU',21850,16,'ALURPADU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(848,03,'GIRIJANA COLONY',21850,16,'ALURPADU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(849,02,'REDDY PALEM',21850,16,'ALURPADU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(850,01,'MANEGUNTAPADU',21851,21,'MANEGUNTAPADU',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(851,03,'GIRJANAWADA',21852,17,'RAMANNAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(852,02,'KOTHURU',21852,17,'RAMANNAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(853,01,'RAMANNAPALEM',21852,17,'RAMANNAPALEM',17)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(854,02,'REDDIPALEM',21853,01,'MUNULAPUDI',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(855,01,'MUNULAPUDI',21853,01,'MUNULAPUDI',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(856,01,'VAVVERU',21854,02,'VAVVERU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(857,02,'BUCHIREDDIPALEM',21854,03,'BUCHIREDDIPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(858,03,'RAMACHANDRAPURAM',21854,02,'VAVVERU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(859,05,'PURANDHARAPURAM',21854,04,'KATTUBADIPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(860,04,'KATTUBADIPALEM',21854,04,'KATTUVADIPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(861,01,'NAGAMAMBAPURAM',21855,05,'NAGAMAMBAPURAM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(862,02,'WILLIAMSPETA',21855,05,'NAGAMAMBAPURAM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(863,04,'GANDHINAGAR G.C.',21856,06,'REBALA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(864,08,'NEHRU NAGAR COLONY',21856,07,'NAGAIAHGUNTA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(865,05,'KONDURUPALEM PATHA',21856,07,'NAGAIAHKUNTA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(866,07,'SANTHINAGAR COLONY',21856,07,'NAGAIAHGUNTA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(867,02,'PRAGATHINAGAR COLONY',21856,06,'REBALA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(868,01,'REBALA',21856,06,'REBALA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(869,06,'NAGAYAGUNTA',21856,07,'NAGAIAHGUNTA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(870,01,'ISKAPALEM',21857,08,'ISKAPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(871,02,'PALLAPROLU',21857,08,'ISKAPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(872,03,'PALLEPALEM',21857,08,'ISKAPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(873,04,'POLINAIDUCHERUVU',21857,08,'ISKAPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(874,01,'CHELLAYAPALEM',21858,09,'CHELLAYAPALEM',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(875,01,'KAVETIPALEM',21859,10,'PANCHEDU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(876,01,'PANCHEDU',21860,10,'PANCHEDU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(877,01,'MINAGALLU',21861,11,'MINAGALLU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(878,04,'NALLARAJUPALEM',21917,22,'MINAGALLU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(879,03,'LAKKARAJUPALLI',21917,22,'MINAGALLU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(880,02,'VENKATAREDDIPALLI',21917,22,'MINAGALLU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(881,01,'MINAGALLU',21917,22,'MINAGALLU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(882,01,'JONNAWADA',21862,12,'JONNAWADA',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(883,02,'SALMANUPURAM',21863,13,'PENUBALLE',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(884,01,'PENUBALLE',21863,13,'PENUBALLE',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(885,01,'KALAYAKAGOLLU',21864,14,'KALAYAKAGOLLU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(886,01,'SREERANGARAJAPURAM',21865,14,'KALYANAKAGALLU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(887,01,'DAMARAMADUGU',21866,15,'DAMARAMADUGU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(888,04,'KOTHAKALVAGIRIJANACOLON',21866,15,'DAMARAMADUGU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(889,03,'PALLIPALEM',21866,15,'DAMARAMADUGU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(890,02,'RAMACHANDRAREDDYNAGAR',21866,15,'DAMARAMADUGU',18)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(891,03,'JANGALADORUVU',21867,01,'PERAMANA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(892,02,'GOLLAPALEM',21867,01,'PERAMANA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(893,01,'PERAMANA',21867,01,'PERAMANA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(894,01,'NEELAYAPALEM',21868,02,'NEELAYAPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(895,01,'KORIMERLA',21869,05,'KORIMERLA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(896,01,'KORIMERLA KHANDRIKA(UI)',21869,05,'KORIMERLA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(897,02,'VEERLAGUDIPADU',21870,04,'VEERLAGUDIPADU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(898,01,'KOLAGATLA',21870,03,'KOLAGATLA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(899,02,'VENKATEASWARAPURAM',21871,12,'SANGAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(900,01,'SANGAM',21871,12,'SANGAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(901,05,'GANDHIJANASANGAM',21872,15,'GANDHIJANASANGAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(902,03,'SANKARAVEERARAGHAVAPURA',21872,13,'PADAMATIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(903,04,'VANGAREDDIPALEM',21872,14,'VANGAREDDIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(904,06,'SIDDIPURAM',21872,16,'SIDDIPURAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(905,07,'ANASUYANAGAR',21872,16,'SIDDIPURAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(906,02,'PALLIPALEM',21872,13,'PADAMATIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(907,01,'PADAMATIPALEM',21872,13,'PADAMATIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(908,04,'WOODHOUSEPETA',21873,11,'THARUNAVAYA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(909,03,'AMARAPANAIDUKANDRIGA',21873,11,'THARUNAVAYA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(910,02,'DASARIPALEM',21873,11,'THARUNAVAYA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(911,01,'THARUNAVAYA',21873,11,'THARUNAVAYA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(912,01,'TALUPURUPADU',21874,06,'TALUPURUPADU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(913,01,'CHENNAVARAPPADU',21875,07,'CHENNAVARAPPADU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(914,01,'MARRIPADU',21876,08,'MARRIPADU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(915,01,'JANGALA KHANDIRKA',21877,09,'JANGALA KHANDRIKA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(916,01,'KALIGIRI KONDURU (U.I)',21878,09,'JANGALA KHANDRIKA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(917,01,'VANGALLU',21879,10,'VANGALLU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(918,02,'ARAVAPALEM',21880,18,'JANDADIBBA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(919,01,'DUVVURU',21880,17,'DUVVURU',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(920,03,'YAKASIRIMATAM',21880,18,'JANDADIBBA',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(921,01,'MAKTHAPURAM',21881,19,'MAKTHAPURAM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(922,02,'PALLIPALEM',21882,20,'ANNAREDDIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(923,01,'ANNAREDDIPALEM',21882,20,'ANNAREDDIPALEM',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(924,02,'TURPU KHAMBHAMPADU',21883,02,'TURPU KHAMBHAMPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(925,01,'KOTTIHEERTHAM',21883,01,'KOTTIHEERTHAM',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(926,01,'YANANADALA',21884,03,'YANANADALA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(927,02,'YERRABALLI',21884,03,'YANANADALA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(928,01,'BILLUPADU',21885,21,'NAGULAVELLATUR',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(929,01,'KAKIVAYA',21886,22,'KAKIVAYA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(930,02,'KANDAPURAM',21886,22,'KAKIVAYA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(931,03,'HARIJANAWADA',21886,22,'KAKIVOYA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(932,01,'NAGULAVELLATURU',21887,21,'NAGULAVELLATUR',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(933,01,'KALAYAPALEM',21888,21,'NAGULAVELLATUR',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(934,04,'BODIPADU',21889,06,'BODIPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(935,03,'MADAPALLE',21889,05,'MADAPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(936,02,'CHEJERLA KHANDRIGA',21889,04,'CHEJERLA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(937,01,'CHEJERLA',21889,04,'CHEJERLA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(938,01,'THIMMAYAPALEM',21890,07,'THIMMAYAPALEM',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(939,02,'ULAVAPALLE',21890,07,'THIMMAYAPALEM',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(940,01,'THIMMAYAPALEM',22227,20,'PALUGODU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(941,03,'KOTTALU',21892,09,'PERUMALLAPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(942,01,'PERUMALLAPADU',21892,09,'PERUMALLAPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(943,02,'PUTTUPALLE',21892,09,'PERUMALLAPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(944,01,'PERUMALLA PADU',21892,02,'PERUMALLA PADU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(945,02,'THUMMALA THALUPURU',21892,03,'THUMMALA THALUPURU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(946,03,'TURPUPALLE COLONY',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(947,01,'PELLERU',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(948,05,'PULLAEELLAPALLE',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(949,04,'REDDIVARI KANDRIGA',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(950,02,'TURPUPALLE',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(951,06,'CHALAPANAIDUPALLE',21893,10,'PELLERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(952,02,'NADIGADDAAGRAHARAM',21894,11,'MAMUDURU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(953,01,'MAMUDURU',21894,11,'MAMUDURU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(954,03,'KONDALARAYUDIKHANDRIGA',21895,13,'OBULAIAHPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(955,02,'OBULAIAHPALLE',21895,13,'OBULAIAHPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(956,01,'PATHAPADU',21895,12,'PATHAPADU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(957,01,'PADERU',21896,14,'PADERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(958,02,'CHEERLAVARIKHANDRIGA',21896,14,'PADERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(959,03,'BOGGULAVARIKANDRIGA',21896,14,'PADERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(960,03,'DORUVUPADU',21897,15,'VAVILERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(961,02,'MYPATIVARIKANDRIGA',21897,15,'VAVILERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(962,01,'VAVILERU',21897,15,'VAVILERU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(963,02,'ADURUPALLI',21898,20,'ADURUPALLI',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(964,01,'CHITTALURU',21898,19,'CHITTALURU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(965,01,'NERNOOR',21899,18,'KOLAPANAIDUPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(966,02,'KOLAPANAIDUPALLE',21899,18,'KOLAPANAIDUPALLE',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(967,01,'YETURU',21900,16,'YETURU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(968,04,'GALIPALEM',21900,17,'NUTHAKKIVARIKANDRIGA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(969,03,'NUTHAKKIVARIKANDRIGA',21900,17,'NUTHAKKIVARIKANDRIGA',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(970,02,'THIRUPATHINAIDUPALLE',21900,16,'YETURU',20)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(971,01,'CHAPURALLAPALLI',21901,01,'BOMMAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(972,04,'GOGULAPALLI',21902,01,'BOMMAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(973,03,'BOMMAVARAMVADDIPALEM',21902,01,'BOMMAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(974,01,'BOMMAVARAM',21902,01,'BOMMAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(975,02,'BOMMAVARAM AGRAHARAM',21902,01,'BOMMAVARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(976,03,'VEMGAMPALLE (NORTH)',21904,03,'A.CHIRUVELLA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(977,02,'MEKAVARIPALLI',21904,03,'A.CHIRUVELLA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(978,04,'S.C.COLONY',21904,03,'A.CHIRUVELLA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(979,01,'AMANICHIRUVELLA',21904,03,'A.CHIRUVELLA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(980,05,'GOUNDLAPALEM (U.T)',21905,04,'INAGALUR',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(981,02,'KOTHURU',21905,05,'VENGAMPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(982,01,'INAGALUR',21905,04,'INAGALUR',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(983,04,'GARLADINNEPADU',21905,04,'INAGALUR',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(984,03,'VEMGAMPALLE SOUTH',21905,05,'VEMGAMPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(985,03,'SANJEEVNAGAR',21906,06,'ANANTHASAGARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(986,04,'CHUTTUGUNTAPALLI',21906,06,'ANANTHASAGARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(987,05,'MANCHERLAPALLI',21906,07,'MANCHERLAPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(988,06,'MUSTHAPURAM',21906,08,'MUSTHAPURAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(989,01,'ANANTHASAGARAM',21906,06,'ANANTHASAGARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(990,02,'SRINIVASA PURAM',21906,06,'ANANTHASAGARAM',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(991,01,'KAMIREDDIPADU',21907,09,'KAMIREDDIPADU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(992,04,'EGUVAPALLI',21908,12,'GUDIGUNTA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(993,01,'MANGUPALLI',21908,10,'MANGUPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(994,02,'CHILAKALAMARRI',21908,11,'CHILAKALAMARRI',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(995,03,'GUDIGUNTA',21908,12,'GUDIGUNTA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(996,01,'PADAMATI KAMBHAMPADU',21910,14,'PADAMATIKAMBHAMPADU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(997,02,'KAMMAVARIPALLI',21910,14,'PADAMATIKAMBHAMPADU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(998,01,'SOMASILA',21911,15,'SOMASILA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(999,01,'UPPALAPADU',21912,16,'UPPALAPADU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1000,01,'VAREKUNTAPADU',21913,17,'VAREKUNTAPADU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1001,02,'LINGAMGUNTA',21914,19,'LINGAMGUNTA',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1002,01,'BEDUSUPALLE',21914,18,'BEDUSUPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1003,02,'MUTTUKUR',21915,20,'DEVARAYAPALLI BIT-I',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1004,01,'DEVARAYAPALLI BIT-I',21915,20,'DEVARAYAPALLI BIT-I',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1005,01,'DEVARAYAPALLE BIT-II',21915,21,'DEVARAYAPALLI BIT-II',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1006,02,'VENGAMNAIDUPALLI',21915,21,'DEVARAYAPALLI BIT-II',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1007,05,'KAKURUVARIPALLI',21915,21,'DEVARAYAPALLI BIT-II',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1008,04,'KOTTAPALLIVADDIPALEM',21915,21,'DEVARAYAPALLI BIT-II',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1009,03,'KOTHAPALLI',21915,21,'DEVARAYAPALLI BIT-II',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1010,02,'SANKARNAGAR',21918,23,'REVURU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1011,01,'REVURU',21918,23,'REVURU',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1012,01,'KULLURU',21919,01,'KULLURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1013,03,'VENKATA REEDIPALLE',21919,01,'KULLURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1014,02,'RAJUPALEM',21919,01,'KULLURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1015,01,'BADDEVOLU',21921,03,'BADDEVOLU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1016,01,'BADDEVOLU',22137,18,'BADDEVOLU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1017,03,'SEETHAMPALLE',21922,04,'KALUVOYA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1018,02,'KUMMARAPALLE',21922,04,'KALUVOYA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1019,01,'KALUVOYA',21922,04,'KALUVOYA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1020,02,'KOLAVAPALLE',21924,05,'NUKANAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1021,01,'NUKANAPALLE',21924,05,'NUKANAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1022,01,'TELUGURAYAPURAM',21925,06,'TELUGURAYAPURAM',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1023,02,'BALAGIRAOPET',21926,08,'BALAGIRAOPET',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1024,01,'THOPUGUNTA',21926,07,'THOPUGUNTA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1025,01,'THOPUGUNTA AGRAHARAM(UI',21926,07,'THOPUGUNTA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1026,01,'MADANNAGARIPALLE',21927,09,'MADANNAGARIPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1027,02,'RAMANNAGARIPALLE',21927,09,'MADANNAGARIPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1028,01,'CHINTALAPALEM',21928,10,'CHINTALAPALEM',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1029,02,'VENKATARAMARAJUPET',21928,10,'CHINTALAPALEM',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1030,01,'UYYALAPALLE',21929,11,'UYYALAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1031,01,'KOTURUPALLE',21930,11,'UYYALAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1032,01,'YERRABALLE',21931,11,'UYYALAPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1033,01,'CHEEPINAPI',21932,12,'CHEEPINAPI',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1034,01,'CHINTALATMAKURU',21933,13,'CHINTALATMAKURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1035,01,'PALLAKONDA',21934,14,'PALLAKONDA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1036,01,'KESAMANENIPALLE',21935,15,'KESAMANENIPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1037,02,'TIRUMALAPADU',21935,15,'KESAMANENIPALLE',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1038,01,'CHINA GOPAVARAM',21936,16,'CHINA GOPAVARAM',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1039,02,'PEDDA GOPAVARAM',21936,16,'CHINA GOPAVARAM',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1040,01,'DACHURU',21938,19,'DACHURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1041,02,'KAMMAPALEM',21938,19,'DACHURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1042,03,'THOKANCHI',21938,19,'DACHURU',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1043,04,'PERAMAKONDA',21938,20,'PERAMAKONDA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1044,05,'DASARIPALLI',21938,20,'PERAMAKONDA',22)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1045,01,'TEGACHERLA',21939,01,'TEGACHERLA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1046,01,'GONUPALLE',21941,02,'GONUPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1047,02,'RAMAKUR',21941,02,'GONUPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1048,03,'PENCHALAKONA',21941,02,'GONUPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1049,01,'PENUBARTHIGOPASAMUDRAM',21942,03,'PENUBARTHI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1050,01,'PENUBARTHI',21943,03,'PENUBARTHI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1051,02,'NAGAVARIPALLE',21943,03,'PENUBARTHI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1052,01,'PENUBARTHI',22037,32,'PENUBARTHI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1053,04,'HARIJANAWADA',22037,32,'PENUBARTHI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1054,02,'PALLIPALEM',22037,32,'PENUBARTHI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1055,03,'YANADICOLONY',22037,32,'PENUBARTHI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1056,01,'LINGAPALEM',21944,03,'PENUBARTHI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1057,01,'SUDDAMALLA',21945,04,'AKILIVALASA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1058,01,'AKILAVALASA',21946,04,'AKILAVALASA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1059,02,'OBULAYAPALLE',21946,04,'AKILAVALASA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1060,03,'REGADAPALLE',21947,06,'NELLEPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1061,01,'GUNDAVOLU',21947,05,'GUNDAVOLU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1062,02,'YERUGUNTAPALLE A.W.',21947,04,'AKILIVALASA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1063,03,'KANDALERU',21948,08,'KAMBHALAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1064,02,'KUMMARAGUNTA',21948,09,'KUMMARAGUNTA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1065,01,'KAMBHALAPALLE',21948,08,'KAMBHALAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1066,04,'CHELLATUR',21948,08,'KAMBHALAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1067,01,'ADURUPALLE',21949,10,'ADURUPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1068,01,'SANAYANPALEM',21950,11,'SANYANPALEM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1069,01,'THATIPALLE',21951,11,'SANYANPALEM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1070,01,'SANKURATHRIPALLE',21952,13,'SANKURATHRIPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1071,01,'TUMAYA',21953,15,'TUMAYA',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1072,02,'VELUGONU',21954,06,'NELLEPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1073,01,'NELLEPALLE',21954,06,'NELLEPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1074,02,'MITTAPALLE',21955,14,'VEPINAPI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1075,05,'RAMADEVIPALLE',21955,14,'VEPINAPI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1076,04,'POKUVUPALLE',21955,13,'SANKURATHRIPADU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1077,03,'VEPINAPI',21955,14,'VEPINAPI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1078,01,'GILAKAPADU',21955,16,'GILAKAPADU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1079,02,'KUKKALAGUNTA',21956,16,'GILAKAPADU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1080,01,'GANDURUPALLE',21956,16,'GILAKAPADU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1081,01,'NAYANIPALLE',21957,12,'M.V.PURAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1082,01,'CHUTTUPALEM',21958,12,'M.V.PURAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1083,01,'MUNAGALA VENKATAPURAM',21959,12,'M.V.PURAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1084,01,'GONU NARASAYAPALEM H.W',21960,14,'VEPINAPI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1085,01,'VEERAYAPALEM',21961,17,'BOJJANAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1086,01,'BOJJANAPALLE',21962,17,'BOJJANAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1087,02,'LEPROSY COLONY (SANGAM)',21963,17,'BOJJANAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1088,01,'RAVIGUNTAPALLE',21963,17,'BOJJANAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1089,01,'RAPUR',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1090,06,'SYDADUPALLI',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1091,04,'RAMIREDDIPALLE',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1092,05,'NAWABPET',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1093,03,'MADDELAMADUGU',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1094,02,'MASIDPET',21964,18,'RAPUR',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1095,01,'YEPURU',21965,19,'YEPURU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1096,03,'KALUJUGUNTA',21966,20,'PANGILI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1097,02,'MADDURUPALLE',21966,20,'PANGILI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1098,01,'PANGILI',21966,20,'PANGILI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1099,04,'TORREVARI PALEM',21966,20,'PANGILI',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1100,01,'KOTURUPADU',21967,21,'SIDDAVARAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1101,01,'BANDEPALLE',21968,21,'SIDDAVARAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1102,01,'BANDE PALLE',21968,07,'BANDE PALLE',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1103,02,'JATLA KONDUR',21968,08,'JATLA KONDUR',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1104,01,'SIDDAVARAM',21969,21,'SIDDAVARAM',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1105,01,'SIDDAVARAM',22258,10,'SIDDAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1106,01,'SIDDAVARAM',22427,07,'SIDDAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1107,01,'PULIGILAPADU',21970,17,'BOJJANAPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1108,01,'JOREPALLE',21971,22,'JOREPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1109,02,'CHERLOPALLE',21971,23,'CHERLOPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1110,01,'JOREPALLE AKKAMAMBAPURA',21972,22,'JOREPALLE',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1111,01,'BHOGASAMUDRAM',21973,01,'BATHULAPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1112,01,'BATHULAPALLE',21974,01,'BATHULAPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1113,01,'SURAYAPALEM',21975,02,'SURAYAPALEM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1114,01,'SURAYAPALEM',22225,20,'PALUGODU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1115,04,'BANDLA YEDDULAVARIPALEM',21976,03,'VIROOR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1116,03,'ISKAPALEM',21976,03,'VIROOR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1117,01,'VIROOR',21976,03,'VIROOR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1118,02,'YERRABALLI',21977,04,'NEDURUPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1119,01,'NEDURUPALLE',21977,04,'NEDURUPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1120,01,'NALLAPALEM',21979,06,'TATIPARTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1121,01,'Thatiparthi',21980,06,'TATIPARTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1122,01,'MAHAMMADAPURAM',21981,07,'MAHAMMADAPURAM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1123,01,'PRABAGIRIPATNAM',21983,08,'PRABAGIRIPATNAM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1124,02,'ULAVARAPALLE',21983,08,'PRABAGIRIPATNAM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1125,02,'NAVOORU PALLE',21984,11,'NAVOORU PALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1126,01,'NAVOORU',21984,10,'NAVOORU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1127,03,'CHENNAREDDIPALLE',21984,12,'CHENNAREDDIPALLE',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1128,01,'ALTHURTHI',21985,13,'ALTHURTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1129,01,'RAVALLA YERUGUNTAPALEM',21986,14,'R.Y.PALEM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1130,02,'NARASIMHAKANDRIVA',21987,15,'KANUPARTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1131,01,'KANUPARTHI',21987,15,'KANUPARTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1132,01,'NANDIVAYA',21988,16,'NANDIVAYA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1133,01,'AYYAGARIPALEM',21989,17,'AYYAGARIPALEM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1134,01,'THODERU',21990,18,'THODERU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1135,01,'CHATAGOTLA',21992,20,'CHATAGOTLA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1136,03,'CHITTEPALLE',21993,21,'PODALAKUR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1137,01,'PODALAKUR',21993,21,'PODALAKUR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1138,02,'LINGAMPALLE',21993,21,'PODALAKUR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1139,01,'GURAVAYAPALEM',21994,22,'MOGALLUR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1140,01,'MOGALLUR',21995,22,'MOGALLUR',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1141,01,'ANKUPALLE',21996,14,'R.Y.PALEM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1142,03,'VENKATAPURAM',21997,23,'PULIKALLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1143,02,'PARVATHAPURAM',21997,23,'PULIKALLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1144,01,'PULIKALLU',21997,23,'PULIKALLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1145,01,'VAVINTAPARTHI',21998,24,'VAVINTAPARTHI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1146,01,'DUGGUNTA RAJUPALEM',22000,25,'DUGGUNTA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1147,01,'UTLA PALEM',22001,25,'DUGGUNTA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1148,03,'KONAGALUR',22002,25,'DUGGUNTA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1149,02,'RAMAPURAM',22002,25,'DUGGUNTA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1150,01,'DUGGUNTA',22002,25,'DUGGUNTA',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1151,01,'DEGAPUDI RAJUPALEM',22003,30,'DEGAPUDI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1152,01,'DEGAPUDI',22004,30,'DEGAPUDI',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1153,02,'MUDIGEDU',22005,26,'INUKURTHY',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1154,01,'INUKURTHY',22005,26,'INUKURTHY',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1155,01,'MARRIPALLE GOPASAMUDRAM',22006,27,'MARRIPALLE GOPASAMUDRAM',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1156,01,'BIRADAVOLU',22008,29,'BIRADAVOLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1157,02,'CHERLOPALLE',22008,29,'BIRADAVOLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1158,03,'KALAYANAPURAM',22008,29,'BIRADAVOLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1159,04,'MUTHYALAPETA',22008,29,'BIRADAVOLU',24)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1160,01,'GOLLA KANDUKUR',22009,02,'GOLLA KANDUKUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1161,01,'SAJJAPURAM',22010,03,'SAJJAPURAM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1162,01,'VELLANTI',22011,04,'VELLANTI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1163,01,'UPPUTURU',22013,24,'UPPUTURU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1164,01,'SOUTH MOPUR',22014,23,'SOUTH MOPUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1165,01,'MOGALLAPALEM',22015,23,'SOUTH MOPUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1166,01,'MATTEMPADU',22016,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1167,04,'APPAIAH KHANDRIGA',22017,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1168,03,'SIDDAVARAPPUPADU',22017,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1169,02,'GOTUVARI KHANDRIGA',22017,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1170,01,'AMANCHERLA',22017,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1171,01,'MANNAVARAPPADU',22018,22,'AMANCHERLA',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1172,02,'KOMARAPUDI',22019,07,'KOMARAPUDI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1173,01,'MULUMUDI',22019,06,'MULUMUDI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1174,04,'RAJAGOPALRAO GIRIJAN C.',22020,09,'KONDLAPUDI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1175,03,'KONDLA PUDI',22020,09,'KONDLAPUDI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1176,01,'DEVARAPALEM',22020,08,'DEVARIPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1177,02,'ISANAKAVARI PALEM',22020,08,'DEVARIPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1178,01,'POTTEPALEM',22021,10,'POTTEPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1179,01,'AKKACHERUVUPADU',22022,21,'AMBAPURAM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1180,01,'OGURUPADU',22023,21,'AMBAPURAM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1181,01,'AMBAPURAM',22024,21,'AMBAPURAM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1182,01,'DONTHALI',22025,25,'DONTHALI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1183,02,'NANCHARAMMASANGAM',22025,25,'DONTHALI',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1184,01,'BUJA BUJA NELLORE',22026,26,'BUJA BUJA NELLORE',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1185,01,'KALLURPALLE',22027,27,'KALLLURPALLE',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1186,01,'KANUPARTHIPADU',22028,28,'KANUPARTHIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1187,01,'ALLIPURAM',22029,12,'ALLIPURAM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1188,02,'NAVALAK GARDENS',22029,13,'NAVALAK GARDENS',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1189,03,'GIRIJAN COLONY',22030,14,'GUDIPALLIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1190,04,'NARAYANAREDDIPET',22030,15,'NARAYANAREDDIPET',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1191,05,'KODURUPADU',22030,16,'KODURUPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1192,02,'TATTUGOLLAPALEM',22030,14,'GUDIPALLIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1193,01,'GUDIPALLIPADU',22030,14,'GUDIPALLIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1194,06,'GOVALLAPALEM',22030,16,'KODURUPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1195,01,'PEDDA CHERUKUR',22031,17,'PEDDA CHERUKUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1196,02,'SOUTHRAJUPALEM',22032,18,'CHINTAREDDIPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1197,01,'CHINTAREDDIPALEM',22032,18,'CHINTAREDDIPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1198,02,'KRISHNAPURAM',22033,19,'VISAVAVILETTIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1199,01,'VISAVAVILETTIPADU',22033,19,'VISAVAVILETTIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1200,03,'THURAKAPALEM',22033,19,'VISAVAVILETTIPADU',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1201,01,'GUNDLAPALEM',22034,20,'GUNDLAPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1202,02,'VADDIPALEM',22034,20,'GUNDLAPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1203,05,'GOMMALAPALEM',22035,29,'KAKUPALLE',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1204,04,'NADAMURINAGAR',22035,29,'KAKUPALLE',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1205,01,'KAKUPALLE-I',22035,29,'KAKUPALLE-I',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1206,03,'SAMANTHULAVARITHOPU',22035,30,'KALIVELAPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1207,02,'KALIVELAPALEM',22035,30,'KALIVELAPALEM',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1208,01,'KAKUPALLE-II(M.R.GUDEM)',22036,31,'MADARAJAGUDUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1209,02,'TURAKAPALEM',22036,31,'MADARAJAGUDUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1210,05,'PALLEPALEM',22036,31,'MADARAJAGUDUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1211,04,'CHERUVUKATTAKINDA G.C.',22036,31,'MADARAJAGUDUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1212,03,'VADDIPALEM',22036,31,'MADARAJAGUDUR',25)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1213,01,'PATUR',22040,01,'PATUR',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1214,01,'POTHIREDDIPALEM',22042,03,'POTHIREDDIPALEM',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1215,02,'PATHAPADUGUPADU',22043,04,'PADUGUPADU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1216,01,'PADUGUPADU',22043,04,'PADUGUPADU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1217,01,'KOVUR (PT)',22044,05,'KOVUR',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1218,01,'MODEGUNTA',22046,06,'MODEGUNTA',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1219,01,'VEGURU',22047,07,'VEGURU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1220,02,'CHERLOPALEM',22047,07,'VEGURU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1221,01,'LEGUNTAPADU',22048,08,'LEGUNTAPADU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1222,09,'JAMMIPALEM',22049,09,'INAMADUGU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1223,01,'INAMADUGU',22049,09,'INAMADUGU',26)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1224,01,'PALLIPADU',22050,01,'PALLIPADU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1225,01,'PALLIPADU',22307,06,'PALLIPADU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1226,02,'NAGARAJATHOPU',22051,02,'LEBUR BET-I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1227,01,'LEBUR BET-I',22051,02,'LEBUR BET-I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1228,01,'NIDUMUSALI',22052,03,'NIDUMUSALI',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1229,02,'PATHAPALEM',22053,04,'MUDIVARTHI PALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1230,01,'MUDIVARTHI PALEM',22053,04,'MUDIVARTHI PALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1231,01,'KUDITHIPALEM',22054,05,'KUDITHIPALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1232,07,'RAMUDUPALEM',22055,07,'RAMUDUPALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1233,08,'RAMUDUPALEM PALLIPALEM',22055,07,'RAMUDUPALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1234,04,'VARANYANAGAR',22055,06,'GANGAPATNAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1235,05,'KALAVAMULAKANDRIGA',22055,06,'GANGAPATNAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1236,06,'VENKATESWARAPURAM',22055,07,'RAMUDUPALEM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1237,03,'SRINIVASAPURAM',22055,06,'GANGAPATNAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1238,01,'GANGAPATNAM',22055,06,'GANGAPATNAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1239,02,'THANGIRALAVARIKANDRIGA',22055,06,'GANGAPATNAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1240,04,'WEST PATTAPURAM',22056,08,'MYPADU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1241,02,'EAST PATTAPUPALEM',22056,08,'MYPADU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1242,01,'MYAPADU',22056,08,'MYPADU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1243,03,'KRISHNAPURAM',22056,08,'MYPADU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1244,01,'SOMARAJUPALLE',22057,09,'SOMARAJUPALLE',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1245,02,'URIBINDIVARIKANDRIGA',22057,09,'SOMARAJUPALLE',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1246,03,'MULAKATTA SANGAM',22057,09,'SOMARAJUPALLE',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1247,04,'MOTHALU',22057,09,'SOMARAJUPALLE',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1248,05,'NARSAPURAM',22057,10,'NARSAPURAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1249,06,'VISAKHAHARIJANAWADA',22057,10,'NARSAPURAM',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1250,01,'KOMARIKA',22058,11,'KOMARIKA',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1251,02,'VARANASIVARIKANDRIGA',22058,11,'KOMARIKA',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1252,03,'KADAYINTIVARI KANDRIGA',22058,11,'KOMARIKA',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1253,04,'PADMAVATHI G.COLONY',22058,11,'KOMARIKA',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1254,05,'RAVOORU',22058,12,'RAVOORU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1255,06,'SUJAATAAMMA NAGAR',22058,12,'RAVOORU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1256,07,'ARISAVARIKANDRIGA',22058,12,'RAVOORU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1257,04,'PALLIPALEM',22059,13,'PUNNUR',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1258,01,'PUNNUR',22059,13,'PUNNUR',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1259,02,'NADIMIPATIMITTA',22059,13,'PUNNUR',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1260,03,'MULAPADU',22059,13,'PUNNUR',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1261,01,'LEBUR BIT-II',22060,14,'JAGADEVIPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1262,02,'DHARBHALAMITTA KANDRIGA',22060,14,'JAGADEVIPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1263,03,'VADDIPALEM',22060,14,'JAGADEVIPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1264,04,'AUDEMMASATRRAM',22060,14,'JAGADEVIPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1265,05,'PAGAVARIPALEM',22060,15,'DEVISPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1266,06,'DEVISPET',22060,15,'DEVISPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1267,07,'BHATTAVARI KHANDRIGA',22060,15,'DEVISPET',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1268,08,'NARAYANAPURAM',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1269,06,'BHATTAVARI PALEM',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1270,01,'INDUKURPET BIT -II',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1271,05,'BUDAMUARI KANDRIGA',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1272,07,'POTHIREDDYPALEM',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1273,04,'MENATIVARIPALEM',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1274,03,'CHATTIVARIKANDRIGA',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1275,02,'PAMULAVARIPALEM',22061,16,'KOTHURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1276,09,'KOTHURU CHINTHOPU',22061,17,'KOTHURU CHINTHOPU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1277,04,'CHALLAYAGARITHOPU',22062,18,'INDUKURPET BIT - I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1278,02,'GAMALLAPALEM',22062,18,'INDUKURPET BIT - I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1279,01,'INDUKURPET BIT-I',22062,18,'INDUKURPET BIT - I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1280,03,'POTHURAJUDIBBA',22062,18,'INDUKURPET BIT - I',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1281,01,'JANGAMVANI DORUVU',22063,19,'JANGAMVANI DORUVU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1282,01,'P.D.KHANDRIGA',22064,20,'KORUTURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1283,01,'KORUTURU',22065,20,'KORUTURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1284,02,'K.PATTEPUPALEM',22065,20,'KORUTURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1285,03,'KOLLIMITTA PATTAPUPALEM',22065,20,'KORUTURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1286,04,'SEEKYAPATI KHANDRIGA',22065,20,'KORUTURU',27)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1287,01,'CHINNACHERUKUR',22066,01,'CHINNACHERUKUR',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1288,02,'JANAVARIPALEM',22067,02,'NARUKURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1289,01,'NARUKURU',22067,02,'NARUKURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1290,01,'PEDURU',22068,03,'PEDURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1291,02,'KRISHNAREDDIPALEM',22068,03,'PEDURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1292,03,'CHINATHOPU',22068,04,'CHINATHOPU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1293,01,'MUNGALADORUVU',22069,05,'MUNGALADORUVU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1294,01,'AMULUR SOUTH',22070,06,'SOUTH AMULUR',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1295,01,'VILUKANIPALLE',22071,07,'VILUKANIPALLE',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1296,01,'POTLAPUDI',22072,08,'POTLAPUDI',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1297,01,'KOTHAPALEM',22073,09,'KOTHAPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1298,03,'V.P.AGRAHARAM',22424,08,'JARUGUMALLI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1299,02,'JARUGUMALLI',22424,08,'JARUGUMALLI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1300,01,'KOTHAPALEM',22424,08,'JARUGUMALLI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1301,01,'KOTHAPALEM U.I',22073,11,'MALLAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1302,01,'VANKANAPALEM',22074,10,'VANKANAPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1303,01,'VENKANAPALEM',22074,17,'VENKANAPALEM',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1304,01,'KODURU - I',22075,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1305,05,'REDDIGUNTA',22075,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1306,04,'REDDIMANUDORUVU',22075,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1307,03,'PAMULADORUVUKANDRIGA',22075,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1308,02,'MAHALAXMIPURAM',22075,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1309,06,'NADIMIPATTAPUPALEM',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1310,01,'KODURU - II',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1311,02,'KOTHA KODURU',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1312,03,'NUKUDUGUNTAKANDRIGA',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1313,04,'VENKATESWARAPATTAPUPALE',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1314,05,'KOTHAPATTAPUPALEM',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1315,07,'PATHAPATTAPUPALEM',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1316,08,'NILLIMITTAKANDRIGA',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1317,09,'TENKAYA THOPU',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1318,10,'PATURIVARI KANDRIKA',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1319,11,'TALAPATIDIBBA',22076,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1320,01,'KODURU KHANDRIKA',22077,11,'KODURU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1321,04,'KUPPATCHIVARIKANDRIGA',22078,14,'MALLIKARGUNAPURAM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1322,05,'MALLIKARGUNAPURAM',22078,14,'MALLIKARGUNAPURAM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1323,01,'EDURU - I',22078,13,'EDURU - I',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1324,03,'NAKKAVARIPALEM PART',22078,14,'MALLIKARGUNAPURAM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1325,02,'MACHAVOLUKANDRIGA',22078,13,'EDURU - I',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1326,02,'ISKAPALEM PART',22079,16,'ISKAPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1327,01,'THOTAPALLE',22079,15,'THOTAPALLE',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1328,04,'MACHERLAVARIPALEM',22079,17,'MACHERLAVARIPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1329,05,'NAKKAVARIPALEM',22079,17,'MACHERLAVARIPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1330,03,'DAKKILIVARIPALEM',22079,15,'THOTAPALLE',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1331,04,'ISKAPALLEM',22080,16,'ISKAPALLEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1332,03,'KOVERAPALEM',22080,19,'KOVERAPALEM',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1333,02,'KRISHNAREDDIPALEM',22080,18,'T.P. GUDUR -II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1334,01,'T.P.GUDUR - II',22080,18,'T.P. GUDUR -II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1335,06,'TATTUGOLLAPALEM',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1336,05,'VANGURUVARIKHANDRIGA',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1337,03,'DEVELLAMITTA',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1338,02,'ARAVAPALEM',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1339,01,'VARIGONDA',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1340,04,'R.V.KHANDRIGA',22081,20,'VARIGONDA',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1341,03,'GAMALLAPALEM',22082,21,'T.P.GUDUR -I',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1342,02,'ISKAPALLE  PART',22082,21,'T.P.GUDUR -I',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1343,01,'T.P.GUDUR - I',22082,21,'T.P.GUDUR -I',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1344,01,'SIVARAMPURAM',22084,22,'VARAKAVIPUDU',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1345,01,'EDURU - II',22085,12,'EDURU - II(MANDAPAM)',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1346,03,'CHANDRASEKHARAPURAM',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1347,04,'TANDODUGUNTA',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1348,05,'PENGOLU',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1349,06,'SOMAYAJULAKANDRIGA',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1350,07,'GOLLAPALEM',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1351,08,'ISKADORUVU',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1352,09,'EDURU PATTAPALEM',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1353,10,'KATE PALLE',22085,12,'EDURU - II',28)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1354,07,'SAYANAKANDRIGA',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1355,04,'VEDICHARLA MITTA',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1356,05,'RANGACHARYALAKANDRIGA',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1357,03,'LINGAYAPALEM',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1358,08,'KOMATTIMITTA',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1359,06,'GOVINDAREDDY PALEM',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1360,01,'BRAHMADEVAM',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1361,02,'EDULAVARIPALEM',22086,01,'BRAHMADEVAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1362,06,'CHERLOVARIPALEM',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1363,05,'PALLIPADUDIBBA',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1364,04,'DUTTAPADU',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1365,02,'KATTA KINDAVADDEPALEM',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1366,01,'PIDATAPOLUR',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1367,03,'JANGALAKANDRIGA',22087,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1368,01,'AMUDALAPADU',22088,02,'PIDATAPOLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1369,05,'GAMALLAPALEM',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1370,03,'MUSUNURUIVARIPALEM H.W.',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1371,02,'MUSUNURIVARIPALEM',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1372,01,'PYNAMPURAM',22089,03,'PYNAMPURAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1373,04,'KOTHAPALEM',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1374,06,'MITTAPALEM H.W.',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1375,10,'ADAVULAPALEM',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1376,08,'DIBBAMIDAHARIJANAWADA',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1377,09,'VAGARTHA HARIJANAWADA',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1378,11,'KATCHAVARIKHANDRIGA',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1379,12,'PALLIPALEM',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1380,07,'ANANTHAVARAM G.C',22089,04,'MUSUNURIVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1381,02,'NELATURU H.W.',22090,05,'NELATURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1382,03,'GOUNDLA PALEM',22090,05,'NELATURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1383,04,'NAKKALAMITTA',22090,05,'NELATURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1384,05,'PATTAPUPALEM',22090,05,'NELATURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1385,01,'NELATURU',22090,05,'NELATURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1386,01,'MAMIDIPUDI',22091,06,'MAMIDIPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1387,01,'VALLURUVARI KHANDRIKA',22092,07,'TALLAPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1388,05,'ACHAMTOPU',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1389,11,'DOSAKAYALAMITTA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1390,10,'CHUTTUGUNTA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1391,09,'SUBBARAYALAMITTA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1392,08,'KOMATIMITTA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1393,13,'BOTLAVARIKHANDRIGA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1394,06,'BANDALAPALEM H.W',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1395,14,'TALLAPUDI',22093,07,'TALLAPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1396,04,'RAMAPURAM',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1397,03,'RAVIPADU',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1398,01,'VALLURU',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1399,07,'BANDLAPALEM',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1400,12,'PATCHAKAYALAMITTA',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1401,17,'ARUNDATIYAPALEM',22093,07,'TALLAPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1402,16,'THALLAPUDI PADMAVAI H.W',22093,07,'TALLAPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1403,15,'TALLAPUDI NEW H.W.',22093,07,'TALLAPUDI',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1404,02,'VALLURU H.W.',22093,08,'VALLURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1405,04,'ALAGALA HARIJANAWADA',22094,10,'POLAMRAJUGUNTA',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1406,01,'SURVEPALLE V ALIA.PPADU',22094,09,'POTTAMPADU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1407,02,'SARREPALLI H.W.',22094,09,'POTTAMPADU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1408,03,'POALMRAJUGUNTA',22094,10,'POLAMRAJUGUNTA',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1409,07,'NAGALADORUVU',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1410,01,'EPURU BIT - I(A)',22095,11,'EPURU',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1411,03,'VENKANNAPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1412,05,'PATTAPUPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1413,06,'GAMALLAPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1414,04,'ROYYALAPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1415,02,'DORUVULAPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1416,08,'MITTAPALEM',22095,12,'DORUVULAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1417,05,'DAMMAYAPALEM',22096,14,'DAMMAYAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1418,01,'NARIKELLAPALLE',22096,13,'NARIKELLAPALLE',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1419,02,'SUBBAREDDIPALEM',22096,13,'NARIKELLAPALLE',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1420,04,'BAVITHOPU',22096,13,'NARIKELLAPALLE',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1421,06,'POTHUNAIDUDIBBA',22096,14,'DAMMAYAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1422,03,'GADELAVAGU',22096,13,'NARIKELLAPALLE',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1423,05,'DUVVURUVARIPALEM',22097,17,'DUVVURUVARIPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1424,04,'PATURIVARIKHANDRIGA',22097,16,'PATURIVARIKHANDRIGA',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1425,03,'KUMARAMITTA',22097,15,'MOLLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1426,02,'GUNTAKATTA',22097,15,'MOLLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1427,01,'MOLLUR',22097,15,'MOLLUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1428,02,'CHALIVENDRA',22098,18,'MUTHUKUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1429,03,'EGUVABALIJAPALEM',22098,18,'MUTHUKUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1430,01,'MUTHUKUR',22098,18,'MUTHUKUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1431,04,'BALIJAPALEM',22098,18,'MUTHUKUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1432,05,'BODISWAMYKHANDRIGA',22098,18,'MUTHUKUR',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1433,01,'PANTAPALEM EPURU I B',22099,20,'PANTAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1434,02,'KOLLAMITTA',22099,20,'PANTAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1435,03,'BALIJAPALEM',22099,20,'PANTAPALEM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1436,05,'KAVALI HARIJANAWADA',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1437,11,'NORTH ARCOTPALEM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1438,03,'PADARTHIPALEM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1439,04,'BASIN',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1440,06,'SAMBHUNI THOPU',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1441,07,'AYYAVARAPPAKHANDRIGA',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1442,08,'ARCOT PALEM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1443,09,'MADAKA ARCOTPALEM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1444,10,'SOUTH ARCOTPALEM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1445,12,'NERILLAMILLA',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1446,01,'KRISHNAPATNAM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1447,02,'GOPALAPURAM',22100,19,'KRISHNAPATNAM',29)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1448,01,'PALICHERLAPADU',22101,01,'PALICHERLAPADU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1449,01,'KURICHERLAPADU',22102,02,'KURICHERLAPADU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1450,02,'ISLAMPET',22102,02,'KURICHERLAPADU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1451,02,'PULLATHEEGALAPADU',22103,03,'KASUMURU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1452,01,'KASUMURU',22103,03,'KASUMURU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1453,03,'MANGALAGUNTA',22103,03,'KASUMURU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1454,04,'VENKATAKRISHNAPURAM',22103,03,'KASUMURU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1455,03,'YERRAGUNTA',22104,12,'CHAVATAPALEM (K.BIT II)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1456,02,'ABBISAHEB KHANDRIGA',22104,12,'CHAVATAPALEM (K.BIT II)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1457,01,'KANUPURU BIT-II',22104,12,'CHAVATAPALEM (K.BIT II)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1458,04,'VADDIPALEM',22105,11,'VENKATACHALAM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1459,03,'DODDARAJUPALEM',22105,11,'VENKATACHALAM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1460,02,'VENKATACHALAM',22105,11,'VENKATACHALAM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1461,01,'KANUPURU  BIT-I',22105,10,'KANUPUR',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1462,01,'KANDALAPADU',22106,04,'KANDALAPADU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1463,01,'KUMKUMPUDI',22107,05,'CHEMUDUGUNTA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1464,03,'BURANPUR',22108,05,'CHEMUDUGUNTA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1465,02,'CHEMUDUGUNTA',22108,05,'CHEMUDUGUNTA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1466,01,'KAKUTURU',22108,06,'KAKUTURU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1467,01,'KANTEPALLE',22109,07,'KANTEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1468,01,'NAGULAVARAM',22110,08,'ANIKEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1469,01,'ANIKEPALLE',22111,08,'ANIKEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1470,02,'GOLAGAMUDI',22111,08,'ANIKEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1471,02,'GOTLAPALEM',22112,09,'TIKKAVARAPPADU(S BIT-I)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1472,05,'MALLUGUNTA SANGAM',22112,09,'TIKKAVARAPPADU(S BIT-I)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1473,03,'MANAGALAMPADU',22112,09,'TIKKAVARAPPADU(S BIT-I)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1474,01,'SURVEPALLE BIT - I',22112,09,'TIKKAVARAPPADU(S BIT-I)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1475,04,'JOSEPHPET',22112,09,'TIKKAVARAPPADU(S BIT-I)',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1476,01,'SURVEPALLE BIT-II',22113,14,'SURVEPALLE   BIT-II',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1477,02,'MUTYALAGUNTA',22113,14,'SURVEPALLE   BIT-II',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1478,03,'NAGAMBOTLAVARIKANDRIGA',22113,14,'SURVEPALLE   BIT-II',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1479,04,'NADIUPALEM',22113,15,'RAMDOSS KANDRIGA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1480,06,'RAMDOSS KANDRIGA',22113,15,'RAMDOSS KANDRIGA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1481,05,'LINGAMGUNTA',22113,15,'RAMDOSS KANDRIGA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1482,03,'VADDIPALEM',22114,13,'IDIMEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1483,02,'JANGALAPALLE',22114,13,'IDIMEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1484,01,'IDIMEPALLE',22114,13,'IDIMEPALLE',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1485,01,'EDAGALI',22115,25,'EDAGALI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1486,02,'EDAGALI KANDRIGA',22115,25,'EDAGALI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1487,05,'SREE KANTH COLONY',22115,25,'EDAGALI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1488,04,'PATIMITTA',22115,25,'EDAGALI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1489,03,'KOTHURU',22115,25,'EDAGALI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1490,01,'PUDIPARTHI',22116,23,'PUDIPARTHI',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1491,02,'YEGUVAMITTA',22116,24,'YEGUVAMITTA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1492,01,'SURVEPALLE BIT-III',22117,16,'SURVEPALLE   BIT-III',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1493,02,'THATIPARTHIPALEM',22117,17,'THATIPARTHIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1494,03,'DARBALAMITTA',22118,19,'ATRAMVARI KANDRIGA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1495,01,'SURVEPALLE BIT-IV',22118,18,'NIDIGUNTAPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1496,02,'ATRAMVARI KANDRIGA',22118,19,'ATRAMVARI KANDRIGA',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1497,01,'PUNJULURUPADU',22119,20,'PUNJULURUPADU',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1498,06,'TIRRUMALAMMAPALEM',22120,22,'TIRUMALAMNAPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1499,04,'MOLAKALAPADU',22120,21,'GUDLURUVARIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1500,03,'D.V.R.PALEM',22120,21,'GUDLURUVARIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1501,02,'Y.V.R.PALEM',22120,21,'GUDLURUVARIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1502,01,'GUDLURUVARIPALEM',22120,21,'GUDLURUVARIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1503,05,'GAJULAPALLE KONERU',22120,21,'GUDLURUVARIPALEM',30)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1504,02,'GOTLAPLEM',22121,01,'VADLAPUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1505,05,'KONDUPALEM',22121,02,'VEERAMPALLI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1506,04,'RAJAVOLAPALLI',22121,02,'VEERAMPALLI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1507,03,'VEERAMPALLI',22121,02,'VEERAMPALLI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1508,01,'VADLAPUDI',22121,01,'VADLAPUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1509,06,'LINGAREDDI PALLE',22121,02,'VEERAMPALLI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1510,01,'AKKAMPET',22122,03,'AKKAMPET',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1511,01,'AKKAMPETA',22651,07,'GRADDAGUNTA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1512,01,'CHRUKUMUDI',22123,04,'CHERUKUMUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1513,02,'KAMALAPUDI',22123,05,'KAMALAPUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1514,01,'PARLAPADU',22124,09,'MADAMANUR',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1515,01,'MUDDUMUDI',22125,04,'CHERUKUMUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1516,01,'ANUPALLIPADU',22126,06,'GURIVINDA PUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1517,01,'GURIVINDAPUDI',22127,05,'GUNDAVOLU',23)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1518,01,'GURIVINDA PUDI',22127,06,'GURUGUNDA PUDI',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1519,01,'MADAMANURU',22129,09,'MADAMANURU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1520,01,'KUDITHIPALLE',22130,08,'JATLAKONDURU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1521,01,'KAGITHALAPURU',22131,10,'KAGITHALAPURU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1522,01,'MANUBOLU',22132,11,'MANUBOLU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1523,02,'YACHAVARAM',22132,11,'MANUBOLU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1524,03,'KODANDARAMA PURAM',22132,11,'MANUBOLU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1525,04,'CHARLOPALLE',22132,12,'CHARLO PALLE',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1526,01,'PIDURU',22133,13,'PIDURU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1527,02,'LAXMI NARSA PURAM',22133,14,'LAXMINARASA PURAM',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1528,01,'KOLANUKUDURU',22134,15,'KOLANUKUDURU',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1529,01,'PIDURUPALEM',22135,16,'PIDURUPALEM',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1530,01,'KATTUVAPALLE',22138,19,'KATTUVAPALLE',31)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1531,01,'KATTUVAPALLE',22565,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1532,02,'KANDALI PETA',22139,08,'KANDALI',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1533,01,'KANDALI',22139,08,'KANDALI',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1534,01,'CHENNURU -I',22140,07,'CHENNURU -I',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1535,03,'NAIDU PALEM',22140,07,'CHENNURU -I',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1536,02,'PATHA THUNGA PALEM',22140,07,'CHENNURU -I',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1537,01,'MITTATHMAKURU',22141,01,'MITTATHMAKURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1538,03,'EDULA PALLE',22141,02,'PAGADALA PALLE',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1539,02,'PAGADALA PALLE',22141,02,'PAGADALA PALLE',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1540,03,'G.S. KANDRIGA',22142,04,'RAMALINGA PURAM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1541,04,'SANTHADAS PALLE',22142,05,'SANTHADAS PALLE',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1542,05,'CHEMBADI PALEM',22142,05,'SANTHADAS PALLE',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1543,02,'RAMALINGA PURAM',22142,04,'RAMALINGA PURAM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1544,01,'VINDURU',22142,03,'VINDURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1545,01,'CHENNURU -II',22143,06,'CHENNURU -II',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1546,01,'NELLATURU',22144,12,'NELLATURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1547,02,'GOVINENI PURAM',22144,12,'NELLATURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1548,03,'KRISHNA PURAM',22144,12,'NELLATURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1549,02,'PURITI PALEM',22145,13,'POTUPALEM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1550,03,'POTTI PALEM',22145,13,'POTUPALEM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1551,01,'EAST GUDUR',22145,13,'POTUPALEM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1552,03,'VEVIPALEM',22146,14,'DIVI PALEM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1553,01,'WEST GUDURU',22146,14,'DIVI PAELM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1554,02,'VEMULA PALEM',22146,14,'DIVI PAELM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1555,01,'PALICHERLA',22147,15,'PALICHERLA',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1556,01,'P.RAJU PALEM',22148,15,'PALICHERLA',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1557,01,'MANGALAPUR',22149,11,'MANGALAPUR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1558,01,'REDDIGUNTA',22150,09,'KOMMANETURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1559,01,'CHEMIDTHI',22151,08,'KANDALI',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1560,02,'T.V.PALLE',22152,09,'KOMMANETURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1561,01,'KOMMANETURU',22152,09,'KOMMANETURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1562,04,'VADDIPALEM',22152,09,'KOMMANETURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1563,03,'T.P.GARI PALLE',22152,09,'KOMMANETURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1564,02,'P.R.KANDRIGA',22153,10,'NERNUR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1565,01,'NERNUR',22153,10,'NERNUR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1566,01,'KUNDAKURU',22155,17,'KUNDAKURU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1567,01,'VEDICHERLA',22156,16,'VEDICHERLA',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1568,01,'VENDODU',22157,18,'VENDODU',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1569,01,'KANDRA',22158,23,'KANDRA',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1570,01,'KONDAGUNTA',22159,19,'KONDAGUNTA',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1571,02,'TIMMASAMUDRAM',22159,20,'THIMMASAMUDRAM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1572,03,'VENKATES PALLI',22159,20,'THIMMASAMUDRAM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1573,01,'MEKANUR',22160,21,'MEKANUR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1574,02,'PULIMCHRLAVARI KANDRIGA',22160,21,'MEKANUR',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1575,01,'AYYAVARI PALEM',22161,22,'AYYAVARI PALEM',32)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1576,01,'MUNAGAPADU',22163,01,'TOCHEM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1577,01,'MALICHEDU',22165,04,'MALICHEDU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1578,01,'KALICHEDU',22166,05,'KALICHEDU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1579,01,'DEVARAVEMUR',22167,06,'DEVARAVEMUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1580,02,'KATTUBADI PALLE',22167,06,'DEVARAVEMUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1581,01,'ORUPALLE',22169,03,'THUMMALATHALUPURU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1582,01,'TOCHEM',22170,01,'TOCHEM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1583,01,'KRISHNAREDDI PALLE',22171,08,'KRISHNAREDDI PALLE',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1584,01,'K.G.R.PALEM',22172,09,'K.G.R.PALEM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1585,01,'GULIMICHERLA',22173,10,'GULIMICHERLA',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1586,01,'CHAGANAM',22174,11,'CHAGANAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1587,02,'CH.RAJU PALEM',22174,12,'CHAGANAM RAJU PALEM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1588,01,'JAFLAPURAM',22175,13,'JOGIPALLE',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1589,01,'JOGIPALLE',22176,13,'JOGIPALLE',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1590,01,'UTUKUR',22177,14,'UTUKUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1591,01,'POKKANDALA',22178,15,'POKKANDALA',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1592,03,'ADURU PALLE',22179,16,'GRIDDALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1593,01,'GRIDDALUR',22179,16,'GRIDDALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1594,02,'PANASAREDDI PALLE',22179,16,'GRIDDALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1595,04,'HARIJAN WADA',22179,16,'GRIDDALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1596,01,'RAMASAGARAM',22180,17,'RAMASAGARAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1597,02,'THURUPU POONDLA',22180,17,'RAMASAGARAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1598,03,'THANGEDIMITTA',22180,17,'RAMASAGARAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1599,03,'DADISETTI PALLE',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1600,08,'ARUNDHATHIYAWADA',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1601,07,'HARIJANAWADA',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1602,06,'G.BRUNDAVANAM',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1603,01,'MOLAKALAPUNDLA',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1604,04,'THIMMASAMUDRAM',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1605,02,'SYDAPURAM',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1606,05,'PALIMITTA',22181,18,'SYDAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1607,01,'RAGANARAMAPURAM',22182,20,'RAGANARAMAPURAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1608,01,'THIPPIREDDI PALLE',22183,21,'THIPPIREDDI PALLE',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1609,01,'LINGASAMUDRAM',22184,22,'LINGASAMUDRAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1610,01,'LINGASAMUDRAM',22222,18,'LINGASAMUDRAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1611,02,'EGUVALINGASAMUDRAM',22222,18,'LINGASAMUDRAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1612,01,'THOKALAPUDI',22185,21,'THIPPIREDDI PALLE',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1613,01,'S.V.KANDRIKA',22186,22,'LINGASAMUDRAM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1614,03,'PELAMRAJU PALLE',22187,24,'MARLAPUDI',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1615,02,'MARLAPUDI',22187,24,'MARLAPUDI',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1616,01,'KOMMIPADU',22187,23,'KOMMIPADU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1617,01,'POTHEGUNTA',22188,26,'POTHEGUNTA',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1618,02,'LINGANAPALEM',22188,25,'LINGANAPALEM',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1619,01,'CHEEKAVOLU',22189,27,'CHEEKAVOLU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1620,01,'VEMULACHEDU',22190,29,'VEMULACHEDU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1621,01,'NALABOTLA PALLE',22191,29,'VEMULACHEDU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1622,01,'PATHALLAPALLI',22192,13,'PATHALLAPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1623,02,'GOVINDAMAPALLI',22192,13,'PATHALLAPALLE',21)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1624,01,'PATHALLA PALLE',22192,29,'VEMULACHEDU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1625,01,'PALUR',22193,30,'PALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1626,02,'PALUR RAJA PALEM',22193,30,'PALUR',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1627,03,'CHENCHU RAJU PALEM',22194,31,'ANANTHAMADUGU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1628,02,'GANGADEVI PALLE',22194,31,'ANANTHAMADUGU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1629,01,'ANANTHAMADUGU',22194,31,'ANANTHAMADUGU',33)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1630,03,'VADDI PALLE',22195,01,'DEVULAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1631,01,'DEVULAPALLE',22195,01,'DEVULAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1632,02,'PANTAVARI PALLE',22195,01,'DEVULAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1633,01,'MADHAVAIAHPALEM',22196,02,'MADHAVAIAH PALEM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1634,01,'CHERLOPALLE',22197,02,'MADHAVAIAH PALEM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1635,02,'EGUVA MARLAGUNTA',22198,03,'VELIKALLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1636,01,'VELIKALLU',22198,03,'VELIKALLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1637,01,'MARLAGUTA',22200,05,'MARLAGUNTA',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1638,06,'MITTAVADDIPALLI',22201,07,'KAMMAPALLI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1639,05,'KAMMAPALLI',22201,07,'KAMMAPALLI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1640,04,'THOPUGANI PALLI',22201,06,'VEMBULURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1641,03,'RAMAPURAM',22201,06,'VEMBULURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1642,02,'GIRIJANA COLONY',22201,06,'VEMBULURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1643,01,'VEMBULURU',22201,06,'VEMBULURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1644,02,'YANADI COLONY',22202,08,'VELAMPALLI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1645,01,'VELAMPALLI',22202,08,'VELAMPALLI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1646,01,'KUPPAYYAPALEM',22203,08,'VELAMPALLI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1647,01,'VEMPATIVARIPALLE',22204,09,'DAKKILI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1648,01,'DAKKILI',22205,09,'DAKKILI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1649,02,'NARASAPPANAIDU PALLI',22205,09,'DAKKILI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1650,01,'KANDALAVARI PALLE',22206,09,'DAKKILI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1651,01,'SANGANAPALLE',22207,10,'SANGANAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1652,01,'YELLAVAJJULAPALLE',22208,10,'SANGANAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1653,01,'PATHNALAPADU',22209,11,'PATHANALAPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1654,01,'KOTHNALAPADU',22210,11,'PATHANALAPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1655,01,'DAGGAVOLU',22212,13,'DAGGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1656,02,'REGADA PALLE',22212,13,'DAGGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1657,03,'SREERAMPALLI',22212,13,'DAGGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1658,01,'DANDAVOLU UPPARAPALLE',22213,15,'D.UPPARAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1659,01,'VENGAMANAIDUPALLE',22214,21,'SREEPURAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1660,01,'SREEPURAM',22215,21,'SREEPURAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1661,01,'NADIMPALLE',22216,14,'NADIMPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1662,01,'DANDAVOLU',22217,16,'DANDAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1663,01,'KONESWARABHATLAPALLE',22218,12,'K.B.PALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1664,03,'VELAMPALLI',22219,17,'MOPURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1665,02,'MOUPURU ROAD',22219,17,'MOPURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1666,01,'MOPURU',22219,17,'MOPURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1667,01,'CHKALAPALLE',22220,18,'LINGASAMUDRAM',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1668,01,'ATTALASIDDAVARAM',22223,09,'DEKKILI',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1669,01,'ALTHURUPADU',22224,19,'ALTHURUPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1670,02,'VELAMPALLI',22224,19,'ALTHURUPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1671,01,'PALUGODU',22228,20,'PALUGODU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1672,02,'VADDIPALLI',22229,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1673,01,'PEDAYACHASAMUDRAM',22229,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1674,02,'MITTAPALEM',22230,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1675,01,'NAGAVOLU',22230,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1676,01,'CHEEKIRENIPALLE',22231,19,'ALTHURAPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1677,03,'SIVAGIRI',22232,25,'AMUDURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1678,02,'AMUDURU',22232,25,'AMUDURU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1679,01,'THEERTHAMPADU',22232,24,'THEERTHAMPADU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1680,01,'THIMMANAGUNTA',22234,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1681,01,'CHENNASAMUDRAM',22235,26,'NAGAVOLU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1682,01,'CHAPALAPALLE',22236,23,'CHAPALAPALLE',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1683,01,'MATUMADUGU',22237,22,'MATUMADUGU',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1684,01,'C.C. KHANDRIGA',22238,01,'PARAVOLU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1685,01,'PARAVOLU',22239,01,'PARAVOLU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1686,03,'SAGUTURU @ KOTHURU',22239,01,'PARAVOLU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1687,01,'BALASAMUDRAM',22240,01,'PARAVOLU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1688,01,'VISWANADHAPURAM',22241,01,'PARAVOLU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1689,01,'KALAPADU',22242,02,'KALAPADU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1690,02,'GOPALAPURAM (M.MADUGU)',22244,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1691,01,'VARADANAPALLE',22244,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1692,01,'KANDANALAPADU',22245,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1693,01,'POGULAVARIPALLE',22246,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1694,02,'LAKSHMIRAMPALLE@MVPALEM',22247,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1695,01,'PAPAMAMBAPURAM',22247,04,'PAPAMAMBAPURAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1696,01,'KUPPAMPALLE',22248,05,'AMMAPALEM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1697,01,'PATRAPALLE',22249,03,'JANGALAPALLE',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1698,01,'AMMAPALEM',22250,05,'AMMAPALEM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1699,01,'MUDDAMPALLE',22251,03,'JANGALAPALLE',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1700,01,'YACHASAMUDRAM',22252,03,'JANGALAPALLE',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1701,01,'BANGARUPETA',22254,06,'BANGARUPETA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1702,01,'GUNDALASAMUDRAM',22255,07,'CHINTHAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1703,01,'CHINTHAGUNTA',22256,07,'CHINTHAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1704,02,'NUDASUPALLE',22256,07,'CHINTHAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1705,01,'PALAKONDA SATRAM',22257,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1706,01,'PALEMKOTA',22259,11,'PALEMKOTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1707,01,'MOKKALAPUDI',22261,11,'PALEMKOTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1708,01,'LALAPETA',22262,08,'LALAPET',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1709,01,'SOMASANIGUNTA',22264,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1710,03,'BYANDUPALEM',22264,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1711,02,'ARAVAPALEM @MARATIPALEM',22264,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1712,01,'BOPPAPURAM',22265,13,'AMMAVARIPET',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1713,04,'BODIREDDIGUNTA',22266,17,'PETLURU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1714,01,'PETLURU',22266,17,'PETLURU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1715,01,'KUMMARAGUNTA',22267,13,'AMMAVARIPET',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1716,02,'COLLEGE MITTA',22268,13,'AMMAVARIPETA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1717,01,'AMMAVARIPETA',22268,13,'AMMAVARIPETA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1718,01,'SUNKARAVARIPALLE',22269,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1719,01,'DACHERUVU',22272,15,'CHELIKAMPADU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1720,01,'VADDIPALLE',22273,09,'SOMASANIGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1721,01,'CHELIKAMPADU',22274,15,'CHELIKAMPADU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1722,01,'KURJAGUNTA',22275,19,'KURJAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1723,02,'RAJUPALEM',22276,19,'KURJAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1724,01,'MANNEGUNTA',22276,19,'KURJAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1725,01,'POOLARANGADUPALLE',22277,19,'KURJAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1726,01,'AKBARNIVASA KHANDRIKA',22278,19,'KURJAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1727,01,'GOTLAGUNTA',22280,14,'MOGALLAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1728,01,'CHINA GOTLAGUNTA',22281,14,'MOGALLAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1729,01,'MOGALLAGUNTA',22282,14,'MOGALLAGUNTA',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1730,07,'T.G.P. COLONY',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1731,06,'DOMMARAPALEM',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1732,05,'ARTISAN COMPLEX',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1733,04,'RAILWAYSTATION AREA',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1734,03,'MARATIPALLE',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1735,01,'PERIYAVARAM',22283,16,'PERIYAVARAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1736,01,'BUSAPALEM',22284,20,'VALLIVEDU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1737,01,'VALLIVEDU',22285,20,'VALLIVEDU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1738,01,'THIMMAYAGUNTA',22286,20,'VALLIVEDU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1739,01,'LINGAMANAIDUPALLE',22287,21,'PANJAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1740,01,'PANJAM',22288,21,'PANJAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1741,01,'KAMMAPALLE',22289,21,'PANJAM',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1742,01,'YATHALURU',22290,22,'YATHALURU',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1743,01,'UPPARAPALLE',22291,23,'UPPARAPALLE',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1744,01,'MANIGADARU KHANDRIKA',22292,24,'CHINANNAPET',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1745,01,'CHINNANNAPETA',22293,24,'CHINANNAPET',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1746,02,'G.K.PALLE',22294,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1747,03,'CHINNAPAREDDIPALLE',22294,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1748,04,'NEELAKANTAPURAM',22294,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1749,05,'YELLAKARU RS',22294,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1750,01,'KALAVALAPUDI',22294,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1751,01,'KUMMARAPETA',22295,25,'KALAVALAPUDI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1752,01,'VENKATAGIRI(PT)',22296,12,'VENKATAGIRI',35)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1753,01,'VAKYAM',22297,01,'NINDALI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1754,01,'NINDALI',22298,01,'NINDALI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1755,01,'VENKATAREDDIPALLE',22299,02,'ALIMILI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1756,02,'S.T. COLONY',22299,02,'ALIMILI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1757,01,'ALIMILI',22300,02,'ALIMILI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1758,02,'SREENIVASAPURAM',22301,02,'ALIMILI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1759,01,'AMBALAPUDI',22301,02,'ALIMILI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1760,01,'CHUTTI',22302,04,'BALAYAPALLI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1761,01,'KADAGUNTA',22303,03,'KADAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1762,01,'RAMAPURAM',22304,04,'BALAYAPALLI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1763,01,'RAMAPURAM',22660,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1764,04,'RAMAPURAM KUPPAM',22660,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1765,02,'VENKATADRI PALEM',22660,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1766,03,'SWAMYREDDIKANDRIKA',22660,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1767,01,'MANNURU',22306,05,'MANAORU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1768,01,'GOTTIKADU',22308,07,'GOTTIKADU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1769,01,'SUBRAHMANAYAM',22309,08,'KAYYURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1770,01,'YACHAVARAM',22310,04,'BALAYAPALLI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1771,02,'PACHERICHENU',22311,09,'VENGAMAMBAPURAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1772,01,'VENGAMAMBAPURAM',22311,09,'VENGAMAMBAPURAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1773,01,'VENGAMAMBAPURAM',22499,01,'PANDLURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1774,02,'SITHA KANDRIKA',22499,02,'PANDLURU',42)
GO
//
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1776,01,'AKKASAMUDRAM',22312,09,'VENGAMAMBAPURAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1777,01,'KAYYURU',22313,08,'KOYYURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1778,02,'VENUGOPALPURAM',22313,08,'KOYYURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1779,01,'PAKAPUDI',22314,10,'PAKAPUDI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1780,01,'PIGILAM KOTHAPALEM',22315,11,'PIGILAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1781,01,'JAYAMPU',22316,12,'JAYAMPU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1782,01,'NAIDUCHERUVU KHANDRIKA',22317,13,'HASTHAKAVERI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1783,01,'HASTAKAVERI',22318,13,'HASTHAKAVERI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1784,01,'JARLAPADU',22319,11,'PIGILAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1785,01,'PIGILAM',22320,11,'PIGILAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1786,01,'ARIGEPALLE',22321,14,'KATRAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1787,01,'TIKKAVARAM',22322,14,'KATRAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1788,01,'TALLAPALLE',22323,15,'SIDDAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1789,01,'SIDDAGUNTA',22324,15,'SIDDAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1790,01,'NIDIGALLU',22325,16,'NIDIGALLU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1791,02,'CHEKALAPALLI',22325,16,'NIDIGALLU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1792,03,'GAJULAPALLI',22325,16,'NIDIGALLU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1793,04,'GOLLAPALLI',22325,16,'NIDIGALLU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1794,01,'KATRAGUNTA',22326,14,'KATRAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1795,01,'KARIMENAGUNTA',22327,14,'KATRAGUNTA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1796,01,'KAMAKURU',22328,17,'KAMAKURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1797,01,'KOTAMBEDU',22329,18,'KOTAMBEDU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1798,01,'KALAGANDA',22330,19,'KALAGANDA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1799,02,'S.T.COLONY',22331,20,'UTLAPALLE',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1800,01,'UTLAPALLE',22331,20,'UTLAPALLE',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1801,01,'PAPIREDDIPALLE',22332,20,'UTLAPALLE',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1802,01,'KONAMGUNTA',22333,19,'KALAGANDA',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1803,01,'GOLLAGUNTA',22334,21,'CHILAMANURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1804,01,'CHILAMANURU',22335,21,'CHILAMANURU',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1805,01,'SANGAVARAM',22336,22,'SANGAVARAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1806,01,'MALLEMALA',22338,23,'BHYRAVARAM',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1807,01,'MELCHURU',22340,24,'PERIMIDI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1808,03,'KAIKALAVARI KANDRIKA',22342,24,'PERIMIDI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1809,01,'PERIMIDI',22342,24,'PERIMIDI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1810,02,'BOLLAVARIPALEM',22342,24,'PERIMIDI',36)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1811,01,'LINGAREDDI PALLE',22343,01,'RAVIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1812,02,'VAJJAVARI PALEM',22344,02,'INUGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1813,01,'INUGUNTA',22344,02,'INUGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1814,01,'PALEMPADU',22346,02,'INUGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1815,01,'BANDARUGUNTA',22347,01,'RAVI PADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1816,05,'KUMMARAPETA',22348,03,'ARIMANIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1817,04,'KAMPALEM',22348,03,'ARIMANIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1818,03,'VADDIPALEM',22348,03,'ARIMANIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1819,02,'MONDIKATTARAJU PALEM',22348,03,'ARIMANIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1820,01,'ARIMANIPADU',22348,03,'ARIMANIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1821,01,'BHUVANAGIRIPALEM',22349,04,'BHUVANAGIRIPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1822,01,'PEDA PARIYA',22350,05,'PEDA PARIYA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1823,02,'RAJU PAELM',22350,05,'PEDA PARIYA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1824,01,'PEDAPARIA RAJU PALEM',22351,06,'VENKATAREDDI PALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1825,01,'RUDRAYAPALEM',22352,07,'KONDAVALLI PADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1826,02,'CHUTTI GUNTA',22352,07,'KONDAVALLI PADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1827,01,'ACHARLAPARLAPALLE',22353,07,'KONDAVALLI PADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1828,01,'KURUGONDA',22354,08,'KURUGONDA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1829,01,'BHATHALAPURAM',22355,06,'VENKATREDDIPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1830,01,'MANAMALA',22356,19,'MANAMALA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1831,01,'KONDAVALLIPADU',22357,07,'KONDAVALLIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1832,01,'VIJAYANELLORE',22358,07,'KONDAVALLIPADU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1833,01,'VENKATAREDDIPALEM',22359,06,'VENKATAREDDIPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1834,01,'V.V.KHANDRIGA',22360,06,'VENKATAREDDIPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1835,01,'OJILI',22361,18,'OJILI',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1836,01,'RAGHAVAREDDIPALEM',22362,10,'CHILLAMANU CHENU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1837,01,'RACHAPALEM',22363,09,'RACHAPAELM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1838,01,'PINAPARIYAPADU',22364,10,'CHILLAMANUCHENU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1839,01,'POLIPADU',22366,04,'BHUVANAGIRIPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1840,01,'KARJAMEDU',22367,11,'GURRAMKONDA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1841,01,'GURRAMKONDA',22368,11,'GURRAMKONDA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1842,01,'SAGUTURU',22369,12,'SAGUTURU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1843,01,'VEERLAGUNAPADU',22370,12,'SAGUTURU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1844,01,'THIRUMALAPADI',22371,13,'THIRUMALAPUDI',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1845,02,'KUNDAM RAJU PALEM',22372,14,'KUNDAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1846,01,'KUNDAM',22372,14,'KUNDAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1847,01,'ENUGUWADA',22373,14,'KUNDAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1848,01,'KOTHA CHERUVU (U.I)',22374,00,'',34)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1849,01,'KOTHA CHERUVU',22374,26,'KOTHA CHERUVU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1850,01,'KOTHAA CHERUVU',22374,07,'JUVVINATTU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1851,01,'KARURU',22375,15,'ATHIVARAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1852,01,'ATHIVARAM',22376,15,'ATHIVARAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1853,01,'MUMMAYAPALEM',22377,22,'MUMMAYAPALEM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1854,01,'MACHAVARAM',22378,16,'MACHAVARAM',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1855,01,'KARABALLAVOLU',22379,21,'KARABALLAVOLU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1856,01,'BHATLAKANUPURU',22380,17,'BHATLAKANUPURU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1857,02,'DEVUNIKANDRIGA',22380,17,'BHATLAKANUPURU',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1858,01,'NEMALLAPUDI',22381,20,'NEMALLAPUDI',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1859,01,'MANAVALI',22382,23,'MANAVALI',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1860,02,'VARADAREDDIKANDRIGA',22383,24,'PUNNEPALLE',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1861,01,'PUNNEPALLE',22383,24,'PUNNEPALLE',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1862,01,'JISYULAVARI KHANDRIGA',22386,25,'GRADDAGUNTA',37)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1863,01,'CHILLAKUR',22387,01,'CHILLAKUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1864,01,'CHILLAKUR',22542,11,'CHILLAKUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1865,02,'VODDIPALEM',22542,11,'CHILLAKUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1866,01,'THEEPANUR',22388,24,'THEEPANUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1867,01,'NAKKALAKALVA KHANDRIKA',22389,21,'KADIVEDU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1868,01,'NELABALLI',22390,27,'NELABALLI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1869,02,'RETTA PALLI',22390,27,'NELABALLI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1870,02,'KALAGUNTA KANDRIKA',22560,01,'NELABALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1871,01,'NELABALLI',22561,01,'NELABALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1872,03,'EGUVARAJU PALEM',22391,26,'EGUVURAJU PALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1873,04,'DIGUVU RAJU PALEM',22391,26,'EGUVURAJU PALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1874,01,'ODURU',22391,25,'ODURU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1875,05,'MARLA METTA',22391,26,'EGUVURAJU PALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1876,02,'PUNNAPUVARI PALEM',22391,25,'ODURU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1877,01,'BUDANAM',22392,28,'BUDANAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1878,07,'THIMMANNAGARI PALEM',22393,23,'THIMMANNAGARI PALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1879,01,'KADIVEDU',22393,21,'KADIVEDU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1880,02,'MALLAVARAPUVARIKANDRIGA',22393,21,'KADIVEDU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1881,03,'PATRAGUNTA',22393,21,'KADIVEDU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1882,04,'KAMMAVARIPALEM',22393,22,'KAMMAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1883,06,'AMUDALA KANDRIGA',22393,22,'KAMMAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1884,05,'GUNTAVARIPALEM',22393,22,'KAMMAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1885,02,'RITERSATRAM',22394,02,'MUTHYALAPADU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1886,01,'MUTHYALAPADU',22394,02,'MUTHYALAPADU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1887,03,'P.C.VARI PALEM',22394,03,'P.C.VARI PALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1888,04,'NANCHARAMMAPETA',22394,02,'MUTHYALAPADU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1889,01,'PENTAPADU',22395,02,'MUTHYALAPADU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1890,01,'THIKKAVARAM',22396,04,'THIKKAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1891,06,'THIPPAGUNTAPALEM',22396,06,'THIPPAGUNTAPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1892,05,'PIDATALAPUDI',22396,05,'CHEDIMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1893,04,'NALLAYAGARIPALEM',22396,05,'CHEDIMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1894,03,'CHEDIMALA',22396,05,'CHEDIMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1895,02,'CHINAKATTAVARIPALLI',22396,04,'THIKKAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1896,01,'UDATHAVARIPARLAPALLE',22397,07,'TONUKUMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1897,01,'YOGESWARUNIPALLE',22398,07,'TONUKUMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1898,01,'THONUKUMALA',22399,20,'THONUKUMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1899,02,'GOTLAPALLE',22399,20,'THONUKUMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1900,03,'NAKKAVARIKANDRIGA',22399,20,'THONUKUMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1901,01,'KALAVA KONDA',22400,19,'KALAVA KONDA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1902,02,'BESTA PALEM',22400,19,'KALAVA KONDA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1903,01,'ANKULAPATURU',22401,08,'VUDATHAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1904,01,'PONNAVOLU',22402,17,'PONNAVOLU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1905,02,'M.G.KOTHAPALEM',22402,17,'PONNAVOLU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1906,01,'CHINTHAVARAM',22403,16,'CHINTHAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1907,02,'KOTHAPALEM',22403,16,'CHINTHAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1908,01,'YERURU',22404,09,'YERURU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1909,02,'YERURU PALEM',22404,09,'YERURU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1910,04,'PUNUGUNTAPALEM',22405,10,'MOMIDI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1911,05,'CHERUKURIVARI PALEM',22405,12,'MANNEGUNTA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1912,03,'VARAGALI',22405,11,'VARAGALI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1913,02,'MULASAVARIPALEM',22405,10,'MOMIDI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1914,06,'MANNEGUNTA',22405,12,'MANNEGUNTA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1915,01,'MOMIDI',22405,10,'MOMIDI',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1916,14,'GUMMALADIBBA',22406,13,'THIMMANAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1917,07,'APISETTITALLU',22406,14,'LINGAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1918,01,'THAMMINAPATNAM',22406,13,'THAMMINAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1919,02,'DODDIKATTUVA',22406,13,'THAMMINAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1920,03,'TALLABATTI',22406,13,'THAMMINAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1921,04,'HARIJANAWADA',22406,13,'THAMMINAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1922,06,'PALLIVANIDIBBA',22406,14,'LINGAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1923,08,'NADAVALAPALEM',22406,13,'THIMMANAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1924,09,'LINGAVARAM',22406,14,'LINGAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1925,10,'RAMASWAMYDORUVU',22406,13,'THIMMANAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1926,11,'PEDAVARAPALEM',22406,14,'LINGAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1927,12,'MIDDEREVU',22406,13,'THIMMANAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1928,13,'PATTAPUPALEM',22406,13,'THIMMANAPATNAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1929,05,'TIGAPALEM',22406,14,'LINGAVARAM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1930,03,'KOMARAVARIPALEM',22407,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1931,05,'RAVULAVARI PALEM',22407,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1932,01,'TURPU KANUPUR',22407,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1933,04,'POSINAVARIPALEM',22407,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1934,02,'EDULAVARI PALEM',22407,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1935,01,'ADDEPALLE',22408,15,'EAST KANUPUR',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1936,02,'KOVVURUVARI PALEM',22409,32,'BALLAVOLU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1937,01,'BALLAVOLU',22409,32,'BALLAVOLU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1938,03,'RAMIREDDY PALEM',22410,18,'UDATHAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1939,01,'UDATHAVARIPALEM',22410,18,'UDATHAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1940,04,'VADDI KANDRIGA',22410,18,'UDATHAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1941,02,'AKUTHOTA',22410,18,'UDATHAVARIPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1942,01,'ANNAMBAKA',22411,29,'ANNAMBAKA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1943,01,'VALLIPADU',22412,30,'VALLIPADU',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1944,02,'KAKUVARI PALEM',22413,33,'VELLAPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1945,01,'VELLAPALEM',22413,33,'VELLAPALEM',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1946,01,'PALLAMALA',22414,34,'PALLAMALA',38)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1947,02,'MADDALI KANDRIGA',22416,01,'MADDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1948,01,'MADDALI',22416,01,'MADDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1949,05,'RAMA PURAM',22417,03,'CHITTEDU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1950,04,'VAJJAVARI PALEM',22417,03,'CHITTEDU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1951,03,'MICRO COLONY',22417,03,'CHITTEDU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1952,02,'N.B.T.COLONY',22417,03,'CHITTEDU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1953,01,'CHITTEDU',22417,03,'CHITTEDU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1954,02,'UNUGUNTA PALEM',22418,17,'UNUGUNTA PALEM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1955,01,'RUDRAVARAM',22418,16,'RUDRAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1956,06,'CHANDRA SHEKARA PURAM',22419,18,'GUDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1957,05,'VIDHYA NAGAR',22419,18,'GUDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1958,04,'RAJU PALEM',22419,18,'GUDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1959,03,'THURUPU KHANDRIGA',22419,18,'GUDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1960,02,'DAKSHNA PALEM',22419,16,'RUDRAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1961,01,'GUDALI',22419,18,'GUDALI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1962,02,'RANGANNA GUNTA',22421,19,'THINNELA PUDI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1963,01,'THINNELAPADU',22421,19,'THINNELA PUDI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1964,01,'KOTA',22422,11,'KOTA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1965,03,'S.L.G.COLONY',22422,12,'KOKKUPADU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1966,04,'SURISETTI PALEM',22422,13,'THIMMANAIDU PALEM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1967,05,'THIMMANAIDU PALEM',22422,13,'THIMMANAIDUPALEM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1968,06,'KOKKUPADU',22422,12,'KOKKUPADU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1969,08,'VERAREDDI SATRAM',22422,14,'VENKANNAPALEM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1970,07,'VENKANNAPALEM',22422,14,'VENKANNAPALEM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1971,02,'A.S.R.PURAM(B.C.COLONY)',22422,11,'KOTA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1972,01,'CHENDODU',22423,15,'CHENDODU',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1973,01,'NELLOREPALLE',22561,02,'NELLOREPALLE',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1974,01,'NELLOREPALLI',22425,05,'NELLOREPALLI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1975,01,'KARLAPUDI',22426,06,'KARLAPUDI',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1976,03,'SRINIVASA SATRAM',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1977,04,'YAMADINNE PALEM',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1978,05,'VAVILLA DHORUVU',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1979,06,'MITTA PALEM',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1980,08,'PEDA PATTAPU PALEM',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1981,07,'JOGITIVANI DIBBA',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1982,09,'GOVINDA PALLI',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1983,02,'GUNNAM  PADIYA',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1984,01,'KOTHAPATNAM',22428,04,'KOTHAPATNAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1985,03,'DHORUVU KATTA',22429,09,'PUTCHALAPALLE',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1986,02,'DAIVALA DIBBA',22429,09,'PUTCHALAPALLE',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1987,01,'PUTCHALAPALLE',22429,09,'PUTCHALAPALLE',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1988,01,'ILLUKURUPADU',22430,07,'SIDDAVARAM',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1989,02,'TATIGUNTA CHEREVU',22431,10,'UTHAMANELLORE',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1990,01,'UTHAMANELLORE',22431,10,'UTHAMANELLORE',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1991,04,'CHIKKALA DIBBA',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1992,03,'UCHURUVARI PALEM',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1993,02,'PEDA VANJIVAKA',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1994,01,'VANJIVAKA',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1995,05,'HARIJANA WADA',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1996,06,'CHINNA VANJIVAKA',22432,20,'VANJIVAKA',39)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1997,01,'YARAGATIPALLE',22434,01,'YARAGATIPALLE',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1998,02,'BURLAVARIPALEM',22434,01,'YARAGATIPALLE',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(1999,03,'GADDALAMITTA',22434,01,'YARAGATIPALLE',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2000,04,'HARIJANAWADA',22434,01,'YARAGATIPALLE',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2001,01,'MOLAGANUR',22435,02,'MOLAGANUR',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2002,02,'MOMMAREDDIPALEM',22435,02,'MOLAGANUR',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2003,01,'VALAMEDU',22436,03,'VALAMEDU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2004,01,'PAMANAJI',22437,03,'VALAMEDU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2005,01,'PATHETIPALEM',22439,04,'JAMINKOTHAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2006,01,'GANGANAPALEM',22441,04,'JAMINKOTHAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2007,01,'JUVVINATTU',22444,07,'JUVVINATTU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2008,01,'ANDALAMALA',22445,06,'KONDURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2009,01,'BUDIDALAWAGU',22446,06,'KONDURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2010,01,'KONDURU',22447,06,'KONDURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2011,01,'KONDURU',22646,03,'KONDURU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2012,01,'MANYALANATTU',22448,07,'JUVVINATTU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2013,01,'GUNDLU',22449,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2014,03,'PALLIPALEM',22451,09,'NELLIPUDI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2015,02,'VENKATREDDIPALLE',22451,09,'NELLIPUDI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2016,01,'NELLIPUDI',22451,09,'NELLIPUDI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2017,04,'GIRIJANCOLONY',22451,09,'NELLIPUDI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2018,04,'GOLLAPALEM H.W',22452,10,'VAKADU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2019,01,'VAKADU',22452,10,'VAKADU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2020,02,'GOLLAPALEM',22452,10,'VAKADU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2021,05,'NEW GIRIJANA COLONY',22452,10,'VAKADU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2022,03,'BASAMMA KANDRIKA',22452,10,'VAKADU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2023,01,'KASIPURAM',22453,11,'KASIPURAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2024,04,'VADDIPALEM',22454,12,'KONDAPURAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2025,03,'NARASIMHAPURAM',22454,12,'KONDAPURAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2026,02,'JANDRAPETA',22454,12,'KONDAPURAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2027,01,'KONDAPURAM',22454,12,'KONDAPURAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2028,01,'MUTTEMBAKA',22455,13,'MUTTEMBAKA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2029,01,'DURGAVARAM',22456,13,'MUTHEMBAKA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2030,04,'VALLAYADIBBA',22457,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2031,01,'KALLURU',22457,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2032,03,'PALLIPALEM',22457,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2033,02,'KALLURU KOTHAPALEM',22457,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2034,05,'SIDDIGUNTA PALEM',22457,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2035,01,'KALLURU',22577,13,'KALLURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2036,01,'KODIWAKAA',22458,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2037,02,'VADDIPALEM',22458,08,'KALLURU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2038,01,'TIRUMUR',22459,14,'THIRUMUR',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2039,01,'CHEEMALAPADU',22460,14,'THIRUMUR',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2040,02,'VAVILLAPALEM',22460,14,'THIRUMUR',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2041,09,'PAMBALI LOCKS',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2042,02,'UTTARA POLAM',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2043,04,'SRINIVASAPURAM(M.PALEM)',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2044,03,'SRINIVASAPURAM(P.PALEM)',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2045,05,'KONDURUPALEM',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2046,07,'KAKIWAKAM',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2047,06,'KOTHURU',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2048,01,'DUGARAJAPATNAM',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2049,08,'PULINJERIVARIPALEM',22461,15,'DUGARAJAPATNAM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2050,01,'PAMBALI',22462,16,'RAVIGUNTAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2051,07,'VALLAMEDU',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2052,12,'CHEMBATIVARIPALEM',22463,16,'RAVIGUNTAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2053,11,'THEEPALAPUDI',22463,16,'RAVIGUNTAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2054,10,'BUSAPALEM',22463,16,'RAVIGUNTAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2055,01,'REDDIPALEM BIT-I',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2056,08,'NIDIGURTHI',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2057,09,'RAVIGUNTAPALEM',22463,16,'RAVIGUNTAPALEM',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2058,06,'GOLLAPALEM',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2059,03,'VENUGOPALAPURAM',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2060,02,'NIMMAVANITHIPPA',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2061,04,'REDDYPALEM (HW)',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2062,05,'SRIHARI PURAM',22463,17,'NIDIGURTHI',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2063,06,'PANTRANGAM',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2064,07,'JALLALADORUVU',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2065,05,'PUNNAVANITHIPPA',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2066,04,'PAMULATHERI',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2067,03,'MULAPADAVA',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2068,01,'REDDIPALAM BIT-II',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2069,02,'K.NALAGAMALA',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2070,08,'PATHA NALAGAMALA',22464,18,'MULAPADAVA',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2071,06,'RAYADORUVU',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2072,01,'PUDIRAYUDORUVU',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2073,02,'VADAPALEM',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2074,03,'MONAPALEM',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2075,04,'WHITE KUPPAM',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2076,05,'CHINATHOTA',22465,19,'PUDIRAYADORUVU',40)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2077,08,'NAWABPETA',22465,19,'PUDIRAYADORUVU',40)
GO
//
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2079,02,'GUNAPATHI PALEM',22466,05,'GUNAPATHI PALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2080,03,'HARIJANAWADA',22466,04,'GUNUPADU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2081,01,'GUNUPUDU',22466,04,'GUNUPUDU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2082,03,'HARIJANWADA',22467,06,'METTU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2083,02,'AKKAREDDI KHANDRIGA',22467,06,'METTU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2084,01,'METTU',22467,06,'METTU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2085,01,'PELLAKURU',22468,03,'PELLAKURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2086,03,'KOTHACHERUVU KUNTA',22468,03,'PELLAKURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2087,02,'THIMMIREDDI VANGA',22468,03,'PELLAKURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2088,01,'UPPALAMARTHI',22469,02,'UPPALAMARTHI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2089,01,'ADDEPUDI',22470,02,'UPPALAMARTHI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2090,01,'MARLAMUDI JANGALAPALLE',22471,02,'UPPALAMARTHI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2091,01,'ESWARAWAKA',22472,07,'ESWARAWAKA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2092,02,'KOTHALA GUNTA',22472,07,'ESWARAWAKA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2093,03,'KARAMPATI DIBBA',22472,07,'ESWARAWAKA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2094,03,'ALETIPADU',22473,08,'CHITTAMUR',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2095,02,'KOTHAGUNTA',22473,08,'CHITTAMUR',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2096,01,'CHITTAMUR',22473,08,'CHITTAMUR',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2097,01,'SOMASAMUDRAM',22474,10,'KOGILI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2098,01,'PITTIVANIPALLE',22475,12,'RANGANADHAPURAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2099,04,'MANGALA KHANDRIGA',22476,01,'YAKASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2100,05,'GANDANGI DIBBA',22476,01,'YAKASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2101,02,'PALI METTA',22476,01,'YAKASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2102,01,'YAKASIRI',22476,01,'YAKASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2103,03,'K.NAIDU KHANDRIGA',22476,01,'YAKASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2104,01,'YELLORE',22478,22,'YELLORE',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2105,01,'VELEGAJULA PALLE',22479,22,'YELLORE',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2106,02,'BUDAGALI',22480,23,'BURADAGALI KOTHAPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2107,01,'BURADAGALI KOTHAPALEM',22480,23,'BURADAGALI KOTHAPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2108,01,'KALAGURTHIPADU',22481,18,'CHILLAMURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2109,02,'RAMI REDDI KANDRIKA',22481,18,'CHILLAMURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2110,03,'SINGANUTHALA H.W',22481,18,'CHILLAMURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2111,01,'KUMMARA PALEM',22482,23,'B.G.K.PALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2112,02,'PERAPALA THIPPA',22482,23,'B.G.K.PALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2113,01,'PUTRAGUNTA',22483,13,'MUKKIDIPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2114,01,'MANGALAVARI PALLE',22484,13,'MUKKIDIPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2115,01,'MUKKIDIPALEM',22485,13,'MUKKIDIPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2116,03,'VALLEE PURAM',22486,11,'MALLAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2117,02,'KOTI GUNTA',22486,11,'MALLAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2118,01,'MALLAM',22486,11,'MALLAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2119,01,'KOGILI',22487,10,'KOGILI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2120,02,'GOLLAPALEM',22487,10,'KOGILI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2121,03,'VEMAGUNTA PALEM',22487,10,'KOGILI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2122,04,'VADDI KHANDRIGA',22487,10,'KOGILI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2123,01,'TADIMEDU',22488,14,'TADIMEDU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2124,01,'MANNEMALA',22489,07,'ESWARAWAKA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2125,06,'PATAMITTA',22490,15,'MANNEMALA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2126,05,'BOYYAVARI KHANDRIGA',22490,15,'MANNEMALA',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2127,04,'ARAVAPALEM',22490,16,'ARAVAPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2128,03,'VADDIKANDRIGA',22490,15,'YELLASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2129,02,'DARAKASTHU',22490,15,'YELLASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2130,01,'YELLASIRI',22490,15,'YELLASIRI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2131,01,'MOLAKALAPUDI',22491,17,'MOLAKALA PUDI',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2132,02,'RAMA PURAM',22492,18,'CHILLAMURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2133,01,'CHILLAMURU',22492,18,'CHILLAMURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2134,01,'VARTHUR NORTH',22493,19,'NORTH VARTHURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2135,06,'PEDDA HARIJANWADA',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2136,05,'MITTA HARIJANWADA',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2137,04,'ANAGOLLAPALEM',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2138,03,'TAMBUGARI PALEM',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2139,02,'RAGHAVARI PALEM',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2140,01,'ARURU',22494,20,'ARURU',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2141,01,'GOLLAPALEM',22496,13,'MUKKIDIPALEM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2142,01,'KOKKUPALEM',22497,11,'MALLAM',41)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2143,01,'PANDLURU',22500,02,'KALLIPADU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2144,02,'BURADAMADUGU',22501,02,'KALLIPEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2145,01,'KALLIPEDU',22501,02,'KALLIPEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2146,01,'KAPULURU',22502,03,'KAPULURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2147,02,'VASUDEVA PURAM',22502,03,'KAPULURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2148,03,'DARAKASTHU PURAM',22502,03,'KAPULURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2149,01,'CHILLAMATHUR',22503,04,'CHILLAMATHUR',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2150,02,'KUCHIWADA PALEM',22504,05,'KUCHIWADA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2151,01,'KUCHIWADA',22504,05,'KUCHIWADA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2152,02,'MADA PALEM',22505,06,'GOTTIPROLU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2153,01,'THANNAMALA',22505,06,'GOTTIPROLU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2154,03,'KALAVA GATTU',22506,07,'ANNAMEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2155,02,'VEMUGUNTA PALEM',22506,07,'ANNAMEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2156,01,'ANNAMEDU',22506,07,'ANNAMEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2157,02,'MURTHIREDDI PALEM',22507,02,'KALLIPEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2158,02,'SEETARAMAPURAM',22507,07,'ANNAMEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2159,01,'AYYAPAREDDIPALEM',22507,02,'KALLIPEDU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2160,01,'MARLAPALLE',22508,08,'MARLAPALLE',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2161,01,'THUMMUR',22509,09,'NAIDUPETA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2162,02,'NARASAREDDI KANDRIGA',22509,09,'NAIDUPETA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2163,03,'PAPAREDDI KHANDRIGA',22509,09,'NAIDUPETA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2164,01,'VINNAMALA',22510,15,'VINNAMALA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2165,03,'P.K.PATTI KHANDRIGA',22510,15,'VINNAMALA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2166,02,'KAMMAVARI PALEM',22510,15,'VINNAMALA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2167,01,'THIMMAJI KHANDRIKA',22511,10,'THIMMAJI KHANDRIKA',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2168,01,'VADDIGUNTA KHANDRIKA',22512,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2169,01,'YERRAMSETTIPALEM',22513,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2170,01,'MENAKURU',22514,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2171,01,'KONETIRAJU PALEM',22515,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2172,02,'MAMBAKA',22516,12,'DWARAKAPURAM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2173,01,'DWARAKAPURAM',22516,12,'DWARAKAPURAM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2174,01,'LANKAPALEM',22517,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2175,01,'PALEPOLAM',22518,11,'MENAKURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2176,01,'C.PADU AGRAHARAM',22520,14,'CHIGURUPADU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2177,01,'CHIGURUPADU',22521,14,'CHIGURUPADU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2178,01,'NAIDUPETA(PT)',22522,09,'NAIDUPET',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2179,01,'JUVVALAPALEM',22523,17,'JUVVALAPALEM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2180,01,'K.VARI KHANDRIGA',22524,17,'JUVVALAPALEM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2181,01,'BIRADA WADA',22525,17,'JUVVALAPALEM',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2182,04,'ATHI MITTA',22526,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2183,01,'PUDURU',22526,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2184,03,'GORRAPALLE GUNTA',22526,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2185,05,'KUPPARAGUNTA Y.COLONY',22526,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2186,02,'MITTA KHANDRIGA',22526,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2187,01,'ATHALAPALEM',22527,16,'PUDURU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2188,02,'N.VARI KHANDRIGA',22528,06,'GOTTIPROLU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2189,03,'RACHARLA KHANDRIGA',22528,06,'GOTTIPROLU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2190,01,'GOTTIPROLU',22528,06,'GOTTIPROLU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2191,01,'RAVULAGUNTA',22529,16,'PUDERU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2192,01,'ARAVPERIMIDI',22530,18,'ARAVAPERMIDI',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2193,01,'PUDERU',22531,16,'PUDERU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2194,02,'RAMAREDDY KANDRIGA',22531,16,'PUDERU',42)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2195,01,'KALAVAKUR',22532,01,'KALAVAKURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2196,02,'PIDIKITI MALA',22532,01,'KALAVAKURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2197,03,'YEDA PUDI',22532,01,'KALAVAKURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2198,01,'ANAKAVOLU',22533,02,'ANAKAVOLU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2199,02,'PERUMALA PALLI',22533,02,'ANAKAVOLU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2200,03,'JANGALA PALLI',22533,03,'PALACHUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2201,01,'PALACHUR',22534,03,'PALACHUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2202,02,'KAPPAGUNTA KHANDRIGA',22534,03,'PALACHUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2203,03,'DIRISANA MALA',22534,03,'PALACHUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2204,03,'CHAMBADI PALEM',22535,04,'CHAVALI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2205,02,'DIGUVA CHAVALI',22535,04,'CHAVALI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2206,01,'CHAVALI',22535,04,'CHAVALI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2207,01,'PENNEPALLI',22536,05,'PENNEPALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2208,01,'TALAVAYIPADU',22537,06,'TALAVAYIPADU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2209,01,'KANURU',22538,07,'KANURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2210,02,'KANURRAJU PALEM',22538,07,'KANURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2211,01,'ROSANURU',22539,08,'ROSANURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2212,02,'ROSANUR RAJU PALEM',22539,08,'ROSANURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2213,01,'SIRASANAMBEDU',22540,09,'SIRASANAMBEDU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2214,02,'S.BEDU RAJU PALEM',22540,09,'SIRASANAMBEDU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2215,02,'DODLA VARI MITTA',22541,10,'JEELLAPATUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2216,01,'JEELLAPATUR',22541,10,'JEELLAPATUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2217,01,'PELLAKUR',22543,12,'PELLAKUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2218,02,'MITTA',22543,12,'PELLAKUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2219,03,'RANIGARI THOTA',22543,12,'PELLAKUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2220,01,'KOTHUR',22544,13,'KOTHUR',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2221,05,'RAVULA PADU',22545,15,'RAVULA PADU H/O C.PUDI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2222,03,'GANGARAJU KHANDRIGA',22545,14,'CHINTAPUDI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2223,04,'YALAKARI KHANDRIGA',22545,14,'CHINTAPUDI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2224,06,'MUPPALA VARI KHANDRIGA',22545,16,'NELUBALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2225,02,'LAXMINAIDU KHANDRIGA',22545,14,'CHINTAPUDI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2226,01,'CHINTAPUDI',22545,14,'CHINTAPUDI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2227,01,'PULLURU',22546,17,'PULLURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2228,02,'MUMMAREDDI GUNTA',22546,18,'M.REDDIGUNTA H/O P.LURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2229,01,'P.C.T.KHANDIKA',22547,17,'PULLURU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2230,04,'KOCHARLAVARI KANDRIKA',22548,16,'NELUBALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2231,01,'NELUBALLI',22548,16,'NELUBALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2232,02,'AKKAHGARI PETA',22548,16,'NELUBALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2233,03,'BHEEMAVARAM',22548,16,'NELUBALLI',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2234,02,'CHERUVUMITTA KANDRIGA',22549,19,'PUNABAKA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2235,01,'PUNABAKA',22549,19,'PUNABAKA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2236,02,'KOTHA HARIJANA WADA',22549,19,'PUNABAKA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2237,03,'CHAVATA KHANDRIGA',22549,19,'PUNABAKA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2238,04,'P.T.KHANDRIGA',22549,19,'PUNABAKA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2239,01,'SETTIGUNTA U.I',22550,20,'ARDHAMALA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2240,01,'ARDHAMALA',22551,20,'ARDHAMALA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2241,01,'ARLAPADU',22552,20,'ARDHAMALA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2242,01,'CHEMBEDU',22553,21,'CHEMBEDU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2243,02,'EGUVA THAGULI',22553,21,'CHEMBEDU',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2244,03,'NALLAGANDLA KANDRIGA',22554,22,'C.NAIDU PETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2245,01,'CHENNAPPANAIDU PETA',22554,22,'C.NAIDU PETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2246,02,'PATHI PADU',22554,22,'C.NAIDU PETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2247,01,'BANGARAMMAPETA',22555,23,'BANGARAMMAPETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2248,01,'NANDIMALA',22556,24,'NANDIMALA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2249,01,'YERRAGUNTA',22557,20,'ARDHAMALA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2250,01,'UDIPUDI',22558,23,'BANGARAMMAPETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2251,01,'MODUGULA PALEM',22559,23,'BANGARAMMAPETA',43)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2252,01,'MODUGULAPALEM',22559,15,'MODUGULAPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2253,01,'MUCHALAGUNTA',22562,02,'NELLOREPALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2254,03,'MELANALATHURU',22564,04,'SINGANALATHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2255,01,'SINGANALATHURU',22564,04,'SINGANALATHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2256,02,'CHAVATAKANDRIGA',22564,04,'SINGANALATHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2257,02,'NAGINERI',22566,06,'MEEJURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2258,01,'KARIKADU',22566,06,'MEEJURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2259,01,'ANEGOTTAM',22567,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2260,02,'VADDIPALEM',22567,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2261,01,'KARATAMUDI',22568,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2262,03,'POOLATHOTA MITTA',22569,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2263,02,'VADDIKANDRIGA',22569,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2264,01,'POOLATHOTA',22569,05,'POOLATHOTA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2265,01,'P.KHANDRIKA',22570,07,'NADIMIKANDRIGA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2266,01,'MYLANGAM',22571,08,'NELAPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2267,02,'GIRIJANA COLONY',22572,08,'NELAPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2268,01,'NELAPATTU',22572,08,'NELAPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2269,02,'VENUMBAKARAJUPALEM',22573,10,'VENUMBAKA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2270,01,'VENUMBAKA',22573,10,'VENUMBAKA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2271,03,'KUPPAREDDI PALEM',22574,09,'YEKOLLU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2272,04,'D.V.SATRAM',22574,09,'YEKOLLU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2273,06,'PALEM PADU',22574,09,'YEKOLLU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2274,01,'YEKOLLU',22574,09,'YEKOLLU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2275,02,'LETHOPU',22574,09,'YEKOLLU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2276,01,'AKKARAPAKA',22575,11,'AKKARAPAKA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2277,02,'N.M.AGRAHARAM',22575,11,'AKKARAPAKA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2278,03,'KRISHNAPURAM',22575,11,'AKKARAPAKA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2279,05,'POLIREDDIPALEM',22575,12,'POLIREDDIPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2280,06,'GOLLAPALEM',22575,12,'POLIREDDIPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2281,07,'MUTHARASIPALEM',22575,12,'POLIREDDIPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2282,01,'ANEPUDI NADIMIKHANDRIGA',22576,07,'NADIMIKANDRIGA',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2283,01,'KALLURU KHANDRIKA',22578,13,'KALLURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2284,01,'VELUKADU',22579,06,'MEEJURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2285,01,'SURAGUNTA TAGELU',22580,06,'MEEJURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2286,01,'ADAPAMUDI',22581,13,'KALLURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2287,01,'SURAPU AGRAHARAM',22582,12,'POLIREDDIPALLEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2288,02,'NETHAJINAGAR',22583,14,'THALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2289,03,'EGUVAMALAPALLI',22583,14,'THALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2290,01,'MELUPAKA',22583,14,'THALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2291,01,'MAVILLAPADU',22584,15,'MODUGULAPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2292,01,'THANEYALI',22585,16,'THANEYALI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2293,02,'KOTHAKANDRIGA',22585,16,'THANEYALI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2294,03,'KAMMAKANDRIGA',22585,16,'THANEYALI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2295,04,'GIRIJANASANGAM',22585,16,'THANEYALI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2296,01,'BUDURU',22586,17,'BUDURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2297,04,'THUNGAMADUGU',22587,18,'VEDURUPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2298,03,'RAJUPALEM',22587,18,'VEDURUPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2299,02,'DUMPALAVARI KANDRIGA',22587,18,'VEDURUPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2300,01,'VEDURUPATTU',22587,18,'VEDURUPATTU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2301,01,'KASVAREDDY PALEM (U)',22588,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2302,01,'DAVADI GUNTA (U)',22589,10,'K.C.N. GUNTA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2303,01,'SULLURUPETA(PT)',22590,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2304,01,'PANDALALA GUNTA (U)',22591,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2305,01,'SULLURU JAPTHIKATTUBADI',22592,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2306,01,'YERRABALEM  (U)',22593,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2307,05,'VADDIPALEM',22595,19,'UTCHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2308,03,'ARIMENI KUNTA',22595,19,'UTCHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2309,04,'THIMINAIDUKANDRIGA',22595,19,'UTCHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2310,02,'GUMMIDITAGELI',22595,19,'UTCHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2311,01,'UTCHURU',22595,19,'UTCHURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2312,01,'VETAGIRIPALEM',22596,16,'THANEYALI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2313,01,'BURADAMADUGU',22597,15,'MODUGULAPALEM',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2314,01,'LINGAMPADU',22599,14,'TALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2315,04,'MUTHARASIPALEM',22600,14,'TALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2316,03,'VADDIPALEM U.I',22600,14,'TALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2317,02,'TAPAINDLU',22600,14,'TALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2318,01,'TALLAMPADU',22600,14,'TALLAMPADU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2319,01,'AYYAPALEM',22601,20,'MINAMANAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2320,01,'AYYANAPALLE (U.I)',21726,00,'',09)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2321,01,'MINAMANAMUDI',22602,20,'MINAMANAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2322,01,'CHANDANAMUDI',22603,20,'MINAMANAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2323,02,'CHALIVENDRA',22604,21,'THOGARAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2324,01,'THOGARAMUDI',22604,21,'THOGARAMUDI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2325,02,'K.P.V.KANDRIGA',22605,22,'SRIDHANAMALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2326,01,'SRIDHANAMALLI',22605,22,'SRIDHANAMALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2327,01,'MANERI',22606,22,'SRIDHANAMALLI',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2328,01,'MEEZURU',22608,06,'MEEZURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2329,02,'BERIJANGALAPALLE',22608,06,'MEEZURU',44)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2330,01,'VELAGALAPONNURU',22609,01,'VELAGALAPONNURU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2331,01,'SUDDAMADUGU THAGELU',22610,02,'MANGANELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2332,01,'ATCHUKATLA',22611,02,'MANGANELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2333,01,'MANGA NELLORE',22612,02,'MANGANELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2334,01,'DAMA NELLORE',22613,03,'DAMA NELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2335,02,'DEGAVARI KANDRIGA',22613,03,'DAMA NELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2336,01,'SUGGUPALLI',22614,04,'SUGGUPALLI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2337,01,'RAMACHANDRA GUNTA',22615,03,'DAMA NELLORE',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2338,01,'MATHAKAMUDI',22616,05,'MATHAKAMUDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2339,01,'UGGUMUDI',22617,06,'UGUUMUDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2340,02,'VENGALAPURAM',22618,07,'MANGALAMPADU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2341,01,'MANGALAMPADU',22618,07,'MANGALAMPADU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2342,01,'ILLUPURU',22619,08,'ILLUPURU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2343,02,'PERIMITIPADU',22620,08,'ILLUPURU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2344,01,'KONNEMBATTU',22620,08,'ILLUPURU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2345,01,'MANNAR POLURU (U)',22621,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2346,01,'VATRAPALEM  (UI)',22622,09,'SULLURU(SULLURUPETA)(U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2347,01,'NADENDLAVARI KANDRIGA',22623,11,'NADENDLAVARI KANDRIGA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2348,01,'SARVAREDDY  KANDRIGA',22624,11,'NADENDLAVARI KANDRIGA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2349,01,'SAMANTHAMALLAM',22625,12,'SAMANTHAMALLAM',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2350,02,'BONDALAPALEM',22625,12,'SAMANTHAMALLAM',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2351,01,'ABAKA',22626,13,'ABAKA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2352,01,'KOMMINENI PALLI',22628,13,'ABAKA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2353,02,'PEDAPADAVA',22628,13,'ABAKA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2354,01,'KOTAPOLURU',22629,14,'KOTAPOLLURU',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2355,05,'GOLLA KANDRIGA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2356,01,'MANNEMUTHERI',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2357,03,'PEDAULASAPADAVA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2358,04,'CHINNA ULASAPADAVA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2359,06,'GAMPALA KANDRIGA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2360,07,'BODIVANI DIBBA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2361,08,'YEDABALEM',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2362,02,'JANGAMLINGANIGUNTA',22630,15,'MANEMUTHURI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2363,01,'DEGALA PALEM',22631,10,'K.C.N.GUNTA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2364,01,'K.C.N. GUNTA',22632,10,'K.C.N.GUNTA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2365,01,'GOPALA REDDY PALEM',22633,16,'GOPALA REDDY PALEM',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2366,01,'KUDIRI',22634,17,'KUDIRI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2367,01,'KUDIRI THIPPA KANDRIGA',22635,17,'KUDIRI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2368,01,'ATAKANITHIPPA',22636,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2369,06,'CHINA KORITI',22637,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2370,04,'PAPINERI',22637,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2371,03,'KONATHURU',22637,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2372,02,'THEVALTHERI KATTA',22637,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2373,01,'KORIDI',22637,18,'KORIDI',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2374,02,'KOLLAPATTU',22638,19,'DAMARAYA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2375,04,'MAVERI',22638,19,'DAMARAYA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2376,03,'JALLIVANIPATTIDA',22638,19,'DAMARAYA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2377,01,'DAMARAYA',22638,19,'DAMARAYA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2378,06,'CHITRATHAGALU',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2379,01,'KADAPATRA (UI)',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2380,02,'THOTAKATLA',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2381,05,'KATERI KATTA',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2382,07,'PERINADU',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2383,04,'PATIMITTA',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2384,03,'CHERUVU MITTA',22639,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2385,02,'RAGANNA PATTIDA',22640,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2386,01,'PANTRANGAM',22640,20,'KADAPATRA',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2387,01,'KOTHACHENU (L.C)SHAR',22641,00,'SHAR PROJECT (U)',45)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2388,01,'VENDLURUPADU',22642,01,'VENDLURUPADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2389,05,'DANDUPADU',22642,01,'VENDLURUPADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2390,04,'GONUGUNTA',22642,01,'VENDLURUPADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2391,02,'PONNAPADU',22642,01,'VENDLURUPADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2392,03,'GURRALAMITTA',22642,01,'VENDLURUPADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2393,03,'NAMARLAMITTAKANDRIGA',22643,02,'MAMBATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2394,02,'CHINNAMAMBATTU',22643,02,'MAMBATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2395,01,'MAMBATTU',22643,02,'MAMBATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2396,02,'KATTAVA',22644,04,'KADALURU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2397,01,'KADALURU',22644,04,'KADALURU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2398,01,'PULIVENDRA',22645,03,'KONDURU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2399,01,'PALLEPALEM',22648,05,'VATAMBEDU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2400,01,'KARIJATHA',22649,06,'KARIJATHA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2401,02,'PAMULAMITTA',22649,06,'KARIJATHA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2402,02,'VATAMBEDU KUPPAM',22650,05,'VATAMBEDU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2403,01,'VATAMBEDU',22650,05,'VATAMBEDU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2404,01,'ANDAGUNDALA',22653,08,'ANDAGUNDALA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2405,02,'CHENIGUNTA',22653,09,'CHENIGUNTA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2406,03,'NADIMIKUPPAM',22654,10,'TADAKHANDRIKA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2407,04,'BODDILINGALAPADU',22654,10,'TADAKHANDRIKA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2408,02,'ANAPAGUNTA',22654,10,'TADAKHANDRIKA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2409,01,'TADA KHANDRIKA',22654,10,'TADAKHANDRIKA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2410,01,'TADA',22655,11,'TADA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2411,02,'PALLEPALEM',22655,11,'TADA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2412,03,'KOTHAKUPPAM',22655,11,'TADA',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2413,02,'PUDIKUPPAM',22656,12,'PUDI',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2414,01,'PUDI',22656,12,'PUDI',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2415,04,'KASIMGADIKUPPAM',22657,13,'KARUR',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2416,03,'KARURUMITTA',22657,13,'KARUR',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2417,02,'KARUR KOTHAKUPPAM',22657,13,'KARUR',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2418,01,'KARUR',22657,13,'KARUR',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2419,01,'BHEEMULAVARIPALEM',22658,14,'BHEEMULAVARIPALEM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2420,02,'PATHAKUPPAM',22658,14,'BHEEMULAVARIPALEM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2421,03,'KOTHAKUPPAM',22658,14,'BHEEMULAVARIPALEM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2422,01,'PERIAVATTU',22659,15,'PERIAVATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2423,02,'KAVALIMITTA',22659,15,'PERIAVATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2424,04,'PERIAVATTU KUPPAM',22659,15,'PERIAVATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2425,03,'PAKIR KANDRIGA',22659,15,'PERIAVATTU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2426,02,'CHINNA PANNAMGADU',22661,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2427,01,'PANNAMGADU',22661,16,'RAMAPURAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2428,04,'MOOLA',22662,17,'VENADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2429,05,'AALLAPATRI',22662,17,'VENADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2430,01,'VENADU',22662,17,'VENADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2431,02,'PALLIPETAKUPPAM',22662,17,'VENADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2432,03,'MATTIGUNTA',22662,17,'VENADU',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2433,01,'IRAKAM',22663,18,'IRAKAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2434,02,'MONA KUPPAM',22663,18,'IRAKAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2435,03,'PALEMTHOPUKUPPAM',22663,18,'IRAKAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2436,04,'VADAGODI KUPPAM',22663,18,'IRAKAM',46)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2437,01,'CHALAMCHERLA',21648,01,'CHALAMCHERLA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2438,02,'PEDDAVARAM',21648,01,'CHALAMCHERLA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2439,03,'PAPATLAVARI PALEM',21648,01,'CHALAMCHERLA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2440,04,'LAXMIPURAM',21648,02,'LAXMIPURAM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2441,02,'PADAMATIPALEM',21649,03,'R.C. PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2442,01,'R.C. PALEM',21649,03,'R.C. PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2443,01,'KOTHAPALLE',21650,04,'KOTHAPALLE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2444,02,'ADAVIRAJUPALEM',21651,05,'MADDURUPADU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2445,01,'MADDURUPADU',21651,05,'MADDURUPADU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2446,03,'SIRIPURAM',21651,06,'SIRIPURAM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2447,04,'NARAYANAPURAM',21651,06,'SIRIPURAM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2448,01,'RUDRAKOTA',21652,07,'RUDRAKOTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2449,02,'GUMMADI BANDALA',21652,07,'RUDRAKOTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2450,02,'MOOLAMPETA',21653,08,'CHENNAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2451,03,'PANUGUNTAPALEM',21653,08,'CHENNAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2452,04,'RAMASAWAMIPALEM',21653,08,'CHENNAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2453,05,'CHINAPATTAPU PALEM',21653,09,'PEDAPATTAPU PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2454,06,'PEDAPATTAPU PALEM',21653,09,'PEDAPATTAPU PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2455,09,'SUBBADU PALEM',21653,09,'PEDAPATTAPU PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2456,08,'NANDIMMAPURAM',21653,09,'PEDAPATTAPU PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2457,07,'PALLA PALEM',21653,09,'PEDAPATTAPU PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2458,01,'CHENNAYAPALEM',21653,08,'CHENNAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2459,02,'KATTAKINDA PALEM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2460,04,'AKUTHOTAGIRIJANACOLONY',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2461,07,'TURUPMALAPALEM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2462,05,'YANADI PALEM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2463,03,'PADAMATI PAELM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2464,01,'ANEMADUGU',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2465,06,'BYNETI PALEM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2466,08,'MONDIDINNE PALEM',21654,10,'ANEMADUGU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2467,22,'NATTU PATTAPAPALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2468,23,'PATTEPUPALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2469,24,'RAMUDUPALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2470,03,'MARKANDEYAPURAM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2471,02,'T.PALLI PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2472,01,'THUMMALAPENTA',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2473,11,'KOTHASATRAM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2474,04,'KARETIVARI PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2475,12,'LINGALAPALLE',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2476,13,'RAMACHANDRAPURAM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2477,17,'VOTTURUVARI PALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2478,16,'LAXMIPURAM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2479,14,'ANNAGARI PALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2480,15,'VENKATESWARAPURAM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2481,20,'MAMILLADORUVU',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2482,18,'SINGIRIGIRIPALEM',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2483,10,'INDIRANAGAR COLONY',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2484,09,'PEDARAMUDU PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2485,08,'CHINNARAMUDU PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2486,07,'SRIRAMA PURAM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2487,06,'PULLAVARI PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2488,21,'NADIMPALLE',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2489,05,'MORLAVARI PALEM',21655,11,'THUMMALAPENTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2490,19,'POOLADORUVU',21655,12,'ANNAGARI PALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2491,04,'AMUDALADINNE',21657,13,'AMUDALADINNE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2492,03,'KONADINNE',21657,13,'AMUDALADINNE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2493,02,'DUDEKULAPALEM',21657,13,'AMUDALADINNE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2494,01,'KAVALI BIT-II',21657,13,'KAVALI BIT-II',19)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2495,08,'SARVAYAPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2496,07,'TAGETIVARIPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2497,06,'MALA PALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2498,05,'ALLIGUNTAPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2499,04,'MATHINAPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2500,02,'KONETIPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2501,01,'KAVALI BIT-I',21658,14,'KAVALI BIT-I',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2502,03,'PEDADINNEPALEM',21658,14,'SARVAYAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2503,02,'CHOWDARIPALEM',21659,16,'CHOWDARIPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2504,03,'VADDAPALEM',21659,16,'CHOWDARIPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2505,01,'BUDAMGUNTA',21659,15,'BUDAMGUNTA',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2506,02,'CHENCHUGARIPALEM',21660,18,'CHENCHUGANIPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2507,03,'MAHALAXMI PURAM',21660,18,'CHENCHUGANIPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2508,01,'MUSUNURU',21660,17,'MUSUNURU',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2509,01,'MANNANGIDINNE',21656,19,'MANNANGIDINNE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2510,02,'KONDAYAGARI PALEM',21656,19,'MANNANGIDINNE',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2511,07,'KOTHUR',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2512,03,'KOTADAVI',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2513,06,'BATLADINNE',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2514,01,'THALLAPALEM',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2515,02,'JAVVIGUNTAPALEM',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2516,04,'RAMACHENDRAPURAM',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2517,05,'LINGAMGUNTTA',21662,20,'THALLAPALEM',05)
GO
insert into HAMLET (hamlet_id, hamlet_code,hamlet_name,township_id,panchayat_code, panchayat_name,mpp_code)
values(2518,01,'GOWRAVARAM',21661,21,'GOWRAVARAM',05)
GO

//	  Municipal Corporation 

ALTER TABLE constituency ADD local_election_body_id bigint(20)
GO
ALTER TABLE constituency_election MODIFY reservation_zone varchar(25)
GO
CREATE TABLE local_election_body  ( 
    local_election_body_id	bigint(20) AUTO_INCREMENT NOT NULL,
    name                  	varchar(50) NULL,
    election_type_id      	bigint(20) NULL,
    tehsil_id             	bigint(20) NULL,
    district_id           	bigint(20) NULL,
    no_of_wards           	bigint(20) NULL,
    PRIMARY KEY(local_election_body_id)
)
GO

CREATE TABLE election_governing_body_position  ( 
    election_governing_body_position_id	bigint(20) AUTO_INCREMENT NOT NULL,
    position                           	varchar(50) NULL,
    PRIMARY KEY(election_governing_body_position_id)
)
GO
CREATE TABLE election_governing_body  ( 
    election_governing_body_id         	bigint(20) AUTO_INCREMENT NOT NULL,
    candidate_id                       	bigint(20) NOT NULL,
    election_body_id                   	bigint(20) NULL,
    election_id                        	bigint(20) NULL,
    duration                           	bigint(20) NULL,
    election_governing_body_position_id	bigint(20) NULL,
    party_id                           	bigint(20) NULL,
    PRIMARY KEY(election_governing_body_id)
)
GO


ALTER TABLE constituency
    ADD CONSTRAINT fk_leb_constituency
	FOREIGN KEY(local_election_body_id)
	REFERENCES local_election_body(local_election_body_id)
GO

ALTER TABLE local_election_body
    ADD CONSTRAINT fk_tehsil_leb
	FOREIGN KEY(tehsil_id)
	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE local_election_body
    ADD CONSTRAINT fk_election_type_leb
	FOREIGN KEY(election_type_id)
	REFERENCES election_type(election_type_id)
GO
ALTER TABLE local_election_body
    ADD CONSTRAINT fk_district_leb
	FOREIGN KEY(district_id)
	REFERENCES district(district_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_party_egb
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_leb_egb
	FOREIGN KEY(election_body_id)
	REFERENCES local_election_body(local_election_body_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_election_egb
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_egbp_egb
	FOREIGN KEY(election_governing_body_position_id)
	REFERENCES election_governing_body_position(election_governing_body_position_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_candidate_egb
	FOREIGN KEY(candidate_id)
	REFERENCES candidate(candidate_id)
GO



insert into election_type values(5,'MUNCIPALITY','State');
go
insert into election_type values(6,'CORPORATION','State');
go
insert into election_governing_body_position values (1, 'CHAIRPERSON')
go
insert into election_governing_body_position values (2, 'VICE CHAIRPERSON')
go
insert into election_governing_body_position values (3, 'MAYOR')
go
insert into election_governing_body_position values (4, 'DEPUTY MAYOR')
go

INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Adilabad', 5, 5, 1,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Adoni', 5, 948, 21,41)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Amadalavalasa', 5, 476, 11,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Amalapuram', 5, 618, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Anakapalle', 5, 549, 13,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bapatla', 5, 770, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bellampalle', 5, 46, 1,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhainsa', 5, 35, 1,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bheemunipatnam', 5, 543, 13,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhimavaram', 5, 661, 15,39)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhongir', 5, 306, 8,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bobbili', 5, 494, 12,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bodhan', 5, 66, 2,35)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chilakaluripet H/O.Purushotha Patnam', 5, 747, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chirala', 5, 800, 18,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chittoor', 5, 1098, 23,18)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Dharmavaram', 5, 1013, 22,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Dhone', 5, 966, 21,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gadwal', 5, 281, 7,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gudivada', 5, 698, 16,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gudur', 5, 861, 19,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Guntakal', 5, 986, 22,37)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ichchapuram', 5, 456, 11,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jaggayyapeta', 5, 668, 16,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jagtial', 5, 109, 3,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jammalamadugu', 5, 892, 20,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jangaon', 5, 377, 9,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kadiri', 5, 1020, 22,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kagaznagar', 5, 14, 1,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kamareddy', 5, 86, 2,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kavali', 5, 835, 19,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Khammam', 5, 435, 10,41)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Koratla', 5, 111, 3,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kothagudem', 5, 388, 9,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kovvur', 5, 635, 15,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Macherla', 5, 717, 17,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Machilipatnam', 5, 711, 16,42)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Madanapalle', 5, 1069, 23,22)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mahbubnagar', 5, 246, 7,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mancherial', 5, 50, 1,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mandapeta', 5, 597, 14,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mangalagiri', 5, 732, 17,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Markapur', 5, 792, 18,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Medak', 5, 151, 4,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Miryalaguda', 5, 342, 8,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nagari', 5, 1083, 23,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nalgonda', 5, 324, 8,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nandyal', 5, 961, 21,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narasaraopet', 5, 744, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narayanpet', 5, 248, 7,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narsapur', 5, 182, 4,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nidadavole', 5, 637, 15,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nirmal', 5, 40, 1,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nuzvid', 5, 680, 16,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ongole', 5, 816, 18,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Palacole', 5, 665, 15,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Palamaner', 5, 1099, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Parvathipuram', 5, 490, 12,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pedana', 5, 707, 16,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Peddapuram', 5, 580, 14,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Piduguralla', 5, 724, 17,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pithapuram', 5, 588, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ponnur', 5, 761, 17,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Proddatur', 5, 891, 20,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pulivendla', 5, 896, 20,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Punganur', 5, 1092, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Puttur', 5, 1084, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rajampet', 5, 923, 20,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ramachandrapuram', 5, 601, 14,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rayachoti', 5, 916, 20,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rayadurg', 5, 982, 22,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Repalle', 5, 773, 17,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sadasivpet', 5, 184, 4,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Salur', 5, 495, 12,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Samalkota', 5, 587, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sangareddy', 5, 186, 4,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sathupalle', 5, 429, 10,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sattenapalle', 5, 735, 17,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Siddipet', 5, 154, 4,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sircilla', 5, 130, 3,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Srikakulam', 5, 480, 11,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Srikalahasti', 5, 1055, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Suryapet', 5, 316, 8,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tadepalligudem', 5, 638, 15,35)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tadpatri', 5, 990, 22,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tandur', 5, 212, 6,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tanuku', 5, 649, 15,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tenali', 5, 755, 17,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tuni', 5, 568, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Venkatagiri', 5, 864, 19,25)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vicarabad', 5, 208, 6,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vinukonda', 5, 739, 17,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vizianagaram', 5, 514, 12,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Wanaparthy', 5, 273, 7,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Yellandu', 5, 418, 10,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Yemmiganur', 5, 931, 21,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Zahirabad', 5, 168, 4,24)    
 GO

INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Anantapur', 6, 1007, 22,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Eluru', 6, 643, 15,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Guntur', 6, 751, 17,52)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kakinada', 6, 591, 14,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Cuddapah', 6, 907, 20,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Karimnagar', 6, 123, 3,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kurnool', 6, 936, 21,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nellore', 6, 854, 19,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nizamabad', 6, 64, 2,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vijayawada', 6, 687, 16,59)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Warangal', 6, 384, 9,53)    

GO


//MOdifictions Municipal Corporation Updates


alter table election add column sub_type varchar(25)
go
update election set sub_type = 'MAIN'
go 

                                                                     
                                                                     
 ALTER TABLE constituency ADD area_type varchar(20)
go                                                                    
ALTER TABLE constituency ADD tehsil_id bigint(20)
GO                                             

ALTER TABLE constituency ADD local_election_body_id bigint(20)
GO
ALTER TABLE constituency_election MODIFY reservation_zone varchar(25)
GO
CREATE TABLE local_election_body  ( 
    local_election_body_id	bigint(20) AUTO_INCREMENT NOT NULL,
    name                  	varchar(50) NULL,
    election_type_id      	bigint(20) NULL,
    tehsil_id             	bigint(20) NULL,
    district_id           	bigint(20) NULL,
    no_of_wards           	bigint(20) NULL,
    PRIMARY KEY(local_election_body_id)
)
GO

CREATE TABLE election_governing_body_position  ( 
    election_governing_body_position_id	bigint(20) AUTO_INCREMENT NOT NULL,
    position                           	varchar(50) NULL,
    PRIMARY KEY(election_governing_body_position_id)
)
GO

CREATE TABLE election_governing_body  ( 
    election_governing_body_id         	bigint(20) AUTO_INCREMENT NOT NULL,
    candidate_id                       	bigint(20) NOT NULL,
    election_body_id                   	bigint(20) NULL,
    election_id                        	bigint(20) NULL,
    duration                           	bigint(20) NULL,
    election_governing_body_position_id	bigint(20) NULL,
    PRIMARY KEY(election_governing_body_id)
)
GO
ALTER TABLE constituency
    ADD CONSTRAINT fk_leb_constituency
	FOREIGN KEY(local_election_body_id)
	REFERENCES local_election_body(local_election_body_id)
GO

ALTER TABLE local_election_body
    ADD CONSTRAINT fk_tehsil_leb
	FOREIGN KEY(tehsil_id)
	REFERENCES tehsil(tehsil_id)
GO
ALTER TABLE local_election_body
    ADD CONSTRAINT fk_election_type_leb
	FOREIGN KEY(election_type_id)
	REFERENCES election_type(election_type_id)
GO
ALTER TABLE local_election_body
    ADD CONSTRAINT fk_district_leb
	FOREIGN KEY(district_id)
	REFERENCES district(district_id)
GO


ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_leb_egb
	FOREIGN KEY(election_body_id)
	REFERENCES local_election_body(local_election_body_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_election_egb
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_egbp_egb
	FOREIGN KEY(election_governing_body_position_id)
	REFERENCES election_governing_body_position(election_governing_body_position_id)
GO
ALTER TABLE election_governing_body
    ADD CONSTRAINT fk_candidate_egb
	FOREIGN KEY(candidate_id)
	REFERENCES candidate(candidate_id)
GO

insert into election_type values(5,'MUNCIPALITY','State');
go
insert into election_type values(6,'CORPORATION','State');
go
insert into election_governing_body_position values (1, 'CHAIRPERSON')
go
insert into election_governing_body_position values (2, 'VICE CHAIRPERSON')
go
insert into election_governing_body_position values (3, 'MAYOR')
go
insert into election_governing_body_position values (4, 'DEPUTY MAYOR')
go

INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Adilabad', 5, 5, 1,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Adoni', 5, 948, 21,41)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Amadalavalasa', 5, 476, 11,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Amalapuram', 5, 618, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Anakapalle', 5, 549, 13,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bapatla', 5, 770, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bellampalle', 5, 46, 1,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhainsa', 5, 35, 1,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bheemunipatnam', 5, 543, 13,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhimavaram', 5, 661, 15,39)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bhongir', 5, 306, 8,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bobbili', 5, 494, 12,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Bodhan', 5, 66, 2,35)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chilakaluripet H/O.Purushotha Patnam', 5, 747, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chirala', 5, 800, 18,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Chittoor', 5, 1098, 23,18)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Dharmavaram', 5, 1013, 22,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Dhone', 5, 966, 21,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gadwal', 5, 281, 7,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gudivada', 5, 698, 16,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Gudur', 5, 861, 19,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Guntakal', 5, 986, 22,37)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ichchapuram', 5, 456, 11,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jaggayyapeta', 5, 668, 16,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jagtial', 5, 109, 3,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jammalamadugu', 5, 892, 20,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Jangaon', 5, 377, 9,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kadiri', 5, 1020, 22,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kagaznagar', 5, 14, 1,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kamareddy', 5, 86, 2,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kavali', 5, 835, 19,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Khammam', 5, 435, 10,41)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Koratla', 5, 111, 3,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kothagudem', 5, 388, 9,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kovvur', 5, 635, 15,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Macherla', 5, 717, 17,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Machilipatnam', 5, 711, 16,42)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Madanapalle', 5, 1069, 23,22)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mahbubnagar', 5, 246, 7,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mancherial', 5, 50, 1,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mandapeta', 5, 597, 14,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Mangalagiri', 5, 732, 17,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Markapur', 5, 792, 18,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Medak', 5, 151, 4,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Miryalaguda', 5, 342, 8,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nagari', 5, 1083, 23,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nalgonda', 5, 324, 8,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nandyal', 5, 961, 21,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narasaraopet', 5, 744, 17,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narayanpet', 5, 248, 7,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Narsapur', 5, 182, 4,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nidadavole', 5, 637, 15,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nirmal', 5, 40, 1,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nuzvid', 5, 680, 16,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ongole', 5, 816, 18,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Palacole', 5, 665, 15,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Palamaner', 5, 1099, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Parvathipuram', 5, 490, 12,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pedana', 5, 707, 16,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Peddapuram', 5, 580, 14,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Piduguralla', 5, 724, 17,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pithapuram', 5, 588, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ponnur', 5, 761, 17,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Proddatur', 5, 891, 20,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Pulivendla', 5, 896, 20,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Punganur', 5, 1092, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Puttur', 5, 1084, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rajampet', 5, 923, 20,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Ramachandrapuram', 5, 601, 14,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rayachoti', 5, 916, 20,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Rayadurg', 5, 982, 22,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Repalle', 5, 773, 17,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sadasivpet', 5, 184, 4,23)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Salur', 5, 495, 12,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Samalkota', 5, 587, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sangareddy', 5, 186, 4,31)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sathupalle', 5, 429, 10,20)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sattenapalle', 5, 735, 17,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Siddipet', 5, 154, 4,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Sircilla', 5, 130, 3,29)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Srikakulam', 5, 480, 11,36)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Srikalahasti', 5, 1055, 23,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Suryapet', 5, 316, 8,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tadepalligudem', 5, 638, 15,35)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tadpatri', 5, 990, 22,34)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tandur', 5, 212, 6,27)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tanuku', 5, 649, 15,32)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tenali', 5, 755, 17,40)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Tuni', 5, 568, 14,30)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Venkatagiri', 5, 864, 19,25)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vicarabad', 5, 208, 6,28)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vinukonda', 5, 739, 17,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vizianagaram', 5, 514, 12,38)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Wanaparthy', 5, 273, 7,26)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Yellandu', 5, 418, 10,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Yemmiganur', 5, 931, 21,33)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Zahirabad', 5, 168, 4,24)    
 GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Anantapur', 6, 1007, 22,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Eluru', 6, 643, 15,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Guntur', 6, 751, 17,52)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kakinada', 6, 591, 14,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Cuddapah', 6, 907, 20,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Karimnagar', 6, 123, 3,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kurnool', 6, 936, 21,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nellore', 6, 854, 19,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nizamabad', 6, 64, 2,50)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vijayawada', 6, 687, 16,59)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Warangal', 6, 384, 9,53)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Hyderabad', 6, 1110, 5,150)    
GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Anantapur', 6, 1007, 22,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Eluru', 6, 643, 15,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Guntur', 6, 751, 17,52)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kakinada', 6, 591, 14,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Cuddapah', 6, 907, 20,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Karimnagar', 6, 123, 3,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Kurnool', 6, 936, 21,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nellore', 6, 854, 19,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Nizamabad', 6, 64, 2,50)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Vijayawada', 6, 687, 16,59)    

GO
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)     VALUES( 'Warangal', 6, 384, 9,53)    

GO







