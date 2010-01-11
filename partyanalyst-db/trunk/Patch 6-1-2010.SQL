
CREATE TABLE user_event ( 
    user_event_id	bigint(15) AUTO_INCREMENT NOT NULL,
    user_id      	bigint(15) NOT NULL,
    description  	varchar(300) NULL,
    location_type	varchar(100) NOT NULL,
    location_id  	bigint(15) NOT NULL,
    start_date   	timestamp NOT NULL,
    end_date     	timestamp NOT NULL,
    title        	varchar(25) NOT NULL,
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

ALTER TABLE registration ADD COLUMN party_id BIGINT
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
CREATE TABLE assigned_problem_progress ( 
	assigned_problem_progress_id                	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_history_id                          	bigint(15) NULL,
	description                                 	varchar(25) NULL,
	performed_date                              	timestamp NULL,
	progress_level                              	varchar(25) NULL,
	problem_source_scope_concerned_department_id	bigint(15) NULL,
	assigned_date                               	timestamp NULL,
	PRIMARY KEY(assigned_problem_progress_id)
)
GO
CREATE TABLE booth_constituency_election_voter ( 
	booth_constituency_election_voter_id	bigint(15) AUTO_INCREMENT NOT NULL,
	booth_constituency_election_id      	bigint(15) NULL,
	voter_id                            	bigint(15) NULL,
	PRIMARY KEY(booth_constituency_election_voter_id)
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
CREATE TABLE problem ( 
	problem_id   	bigint(15) AUTO_INCREMENT NOT NULL,
	description  	varchar(250) NULL,
	intensity       varchar(25) NULL,
	identified_on	Date NULL,
	year            varchar(25),
	PRIMARY KEY(problem_id)
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
	desciption            	varchar(25) NULL,
	date_assigned         	timestamp NULL,
	PRIMARY KEY(problem_fund_source_id)
)
GO
CREATE TABLE problem_history ( 
	problem_history_id     	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_location_id    	bigint(15) NULL,
	problem_status_id      	bigint(15) NULL,
	comments               	varchar(25) NULL,
	problem_sourse_scope_id	bigint(15) NULL,
	updated_date           	timestamp NULL,
	PRIMARY KEY(problem_history_id)
)
GO
CREATE TABLE problem_location ( 
	problem_location_id      bigint(15) AUTO_INCREMENT NOT NULL,
	problem_source_id        bigint(15) NULL,
	township_id              	bigint(15) NULL,
	hamlet_id                	bigint(15) NULL,
	ward_id                  	bigint(15) NULL,
	problem_classification_id	bigint(15) NULL,
	updated_date             	timestamp NULL,
	PRIMARY KEY(problem_location_id)
)
GO
CREATE TABLE problem_source ( 
	problem_source_id         	bigint(15) AUTO_INCREMENT NOT NULL,
	posted_by                 	varchar(100) NULL,
	user_id                   	bigint(15) NULL,
	problem_id                	bigint(15) NULL,
	source                    	varchar(25) NULL,
	updated_date              	timestamp NULL,
	problem_external_source_id	bigint(15) NULL,
	PRIMARY KEY(problem_source_id)
)
GO
CREATE TABLE problem_source_scope ( 
	problem_source_scope_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_category_id       	bigint(15) NULL,
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
CREATE TABLE user_category ( 
	user_category_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_category   	varchar(25) NULL,
	updated_date    	timestamp NULL,
	PRIMARY KEY(user_category_id)
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
ALTER TABLE problem_fund_source
	ADD CONSTRAINT fk_fund_source_problem_fund_source_1
	FOREIGN KEY(fund_source_id)
	REFERENCES fund_source(fund_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location
	ADD CONSTRAINT fk_hamlet_problem_location
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_source
	ADD CONSTRAINT fk_problem_problem_source_1
	FOREIGN KEY(problem_id)
	REFERENCES problem(problem_id)
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
ALTER TABLE problem_source
	ADD CONSTRAINT fk_problem_external_source_problem_source_1
	FOREIGN KEY(problem_external_source_id)
	REFERENCES problem_external_source(problem_external_source_id)
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
ALTER TABLE problem_location
	ADD CONSTRAINT fk_problem_source_problem_location_1
	FOREIGN KEY(problem_source_id)
	REFERENCES problem_source(problem_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_history
	ADD CONSTRAINT fk_problem_source_scope_problem_history_1
	FOREIGN KEY(problem_sourse_scope_id)
	REFERENCES problem_source_scope(problem_source_scope_id)
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
ALTER TABLE problem_history
	ADD CONSTRAINT fk_problem_status_problem_history_1
	FOREIGN KEY(problem_status_id)
	REFERENCES problem_status(problem_status_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_source_scope
	ADD CONSTRAINT fk_user_catagory_problem_source_scope_1
	FOREIGN KEY(user_category_id)
	REFERENCES user_category(user_category_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE registration ADD user_category_id bigint(15)
GO
ALTER TABLE registration
	ADD CONSTRAINT fk_user_category_registration
	FOREIGN KEY(user_category_id)
	REFERENCES user_category(user_category_id)
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
ALTER TABLE problem_location ADD CONSTRAINT fk_township_problem_location FOREIGN KEY(township_id)
	REFERENCES township(township_id) 
                    ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_location ADD CONSTRAINT fk_ward_problem_location FOREIGN KEY(ward_id)
	REFERENCES ward(ward_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE problem_source ADD CONSTRAINT fk_registration_user_id FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
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
ALTER TABLE user_event
	ADD COLUMN is_deleted varchar(6) 
GO
INSERT INTO problem_external_source VALUES (1,"PartyAnalyst","9676696760","itgrids@itgrids.com","Hyderabad")
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