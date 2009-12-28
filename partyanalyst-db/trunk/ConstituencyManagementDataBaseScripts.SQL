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
	booth_constituency_election_voter_id	bigint(15) NOT NULL,
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
	intensity    	varchar(25) NULL,
	identified_on	timestamp NULL,
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
	problem_location_id      	bigint(15) AUTO_INCREMENT NOT NULL,
	problem_source_id        	bigint(15) NULL,
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
	posted_by                 	varchar(25) NULL,
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
	first_name         	varchar(25) NULL,
	last_name          	varchar(25) NULL,
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
	ON UPDATE RESTRICT ;
