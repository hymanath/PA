
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
CREATE TABLE epapers ( 
	epapers_id 	bigint(15) AUTO_INCREMENT NOT NULL,
	epapers_url	varchar(200) NULL,
	district_id	bigint(15) NOT NULL,
	state_id   	bigint(15) NOT NULL,
	PRIMARY KEY(epapers_id))
GO
ALTER TABLE  epapers
    ADD CONSTRAINT  fk_epapers_district
	FOREIGN KEY(district_id)
	REFERENCES  district(district_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
GO
ALTER TABLE  epapers
    ADD CONSTRAINT  fk_epapers_state
	FOREIGN KEY(state_id)
	REFERENCES  state(state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
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

insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(1,'http://www.eenadu.net/district/districtshow1.asp?dis=adilabad',1,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(2,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=adb',1,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(3,'http://www.vaartha.com/contents/229/adilabad.html',1,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(4,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=1',1,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(5,'http://www.eenadu.net/district/districtshow1.asp?dis=anantapur',22,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(6,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=atp',22,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(7,'http://www.vaartha.com/contents/292/anantapuram.html',22,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(8,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=12',22,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(9,'http://www.eenadu.net/district/districtshow1.asp?dis=chittoor',23,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(10,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=atp',23,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(11,'http://www.vaartha.com/contents/237/chittoor.html',23,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(12,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=13',23,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(13,'http://www.eenadu.net/district/districtshow1.asp?dis=kadapa',20,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(14,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=cdp',20,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(15,'http://www.vaartha.com/contents/231/kadapa.html',20,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(16,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=14',20,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(17,'http://www.eenadu.net/district/districtshow1.asp?dis=eastgodavari',14,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(18,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=egd',14,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(19,'http://www.vaartha.com/contents/238/east-godavari.html',14,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(20,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=20',14,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(21,'http://www.eenadu.net/district/districtshow1.asp?dis=guntur',17,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(22,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=gnt',17,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(23,'http://www.vaartha.com/contents/236/guntur.html',17,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(24,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=17',17,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(25,'http://www.eenadu.net/district/districtshow1.asp?dis=hyderabad',5,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(26,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=hyd',5,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(27,'http://www.vaartha.com/contents/250/hydearbad.html',5,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(28,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=7',5,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(29,'http://www.eenadu.net/district/districtshow1.asp?dis=karimnagar',3,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(30,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=knr',3,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(31,'http://www.vaartha.com/contents/233/karimnagar.html',3,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(32,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=3',3,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(33,'http://www.eenadu.net/district/districtshow1.asp?dis=khammam',10,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(34,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=kmm',10,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(35,'http://www.vaartha.com/contents/232/khammam.html',10,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(36,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=10',10,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(37,'http://www.eenadu.net/district/districtshow1.asp?dis=krishna',16,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(38,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=kri',16,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(39,'http://www.vaartha.com/contents/235/krishna.html',16,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(40,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=18',16,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(41,'http://www.eenadu.net/district/districtshow1.asp?dis=kurnool',21,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(42,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=knl',21,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(43,'http://www.vaartha.com/contents/234/kurnool.html',21,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(44,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=11',21,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(45,'http://www.eenadu.net/district/districtshow1.asp?dis=mahaboobnagar',7,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(46,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=mnr',7,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(47,'http://www.vaartha.com/contents/243/mehboobnagar.html',7,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(48,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=9',7,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(49,'http://www.eenadu.net/district/districtshow1.asp?dis=medak',4,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(50,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=mdk',4,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(51,'http://www.vaartha.com/contents/244/medak.html',4,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(52,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=4',4,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(53,'http://www.eenadu.net/district/districtshow1.asp?dis=medak',8,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(54,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nlg',8,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(55,'http://www.vaartha.com/contents/239/nalgonda.html',8,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(56,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=8',8,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(57,'http://www.eenadu.net/district/districtshow1.asp?dis=nellore',19,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(58,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nlr',19,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(59,'http://www.vaartha.com/contents/241/nellore.html',19,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(60,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=15',19,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(61,'http://www.eenadu.net/district/districtshow1.asp?dis=nizamabad',2,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(62,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=nzb',2,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(63,'http://www.vaartha.com/contents/240/nizamabad.html',2,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(64,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=2',2,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(65,'http://www.eenadu.net/district/districtshow1.asp?dis=prakasham',18,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(66,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=pkm',18,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(67,'http://www.vaartha.com/contents/187/prakasam.html',18,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(68,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=16',18,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(69,'http://www.eenadu.net/district/districtshow1.asp?dis=rangareddy',6,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(70,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=rrd',6,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(71,'http://www.vaartha.com/contents/245/ranga-reddy.html',6,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(72,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=6',6,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(73,'http://www.eenadu.net/district/districtshow1.asp?dis=srikakulam',11,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(74,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=skl',11,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(75,'http://www.vaartha.com/contents/249/srikakulam.html',11,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(76,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=23',11,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(77,'http://www.eenadu.net/district/districtshow1.asp?dis=Visakhapatnam',13,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(78,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=vsp',13,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(79,'http://www.vaartha.com/contents/248/vishakapatnam.html',13,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(80,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=21',13,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(81,'http://www.eenadu.net/district/districtshow1.asp?dis=vijayanagaram',12,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(82,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=vzm',12,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(83,'http://www.vaartha.com/contents/247/vijayanagaram.html',12,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(84,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=22',12,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(85,'http://www.eenadu.net/district/districtshow1.asp?dis=warangal',9,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(86,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=wgl',9,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(87,'http://www.vaartha.com/contents/246/warangal.html',9,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(88,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=5',9,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(89,'http://www.eenadu.net/district/districtshow1.asp?dis=westgodavari',15,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(90,'http://www.andhrajyothy.com/districts/districtshow.asp?qry=wgd',15,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(91,'http://www.vaartha.com/contents/242/west-godavari.html',15,1)
GO
insert into epapers ( epapers_id,epapers_url,district_id,state_id) 
values(92,'http://www.sakshi.com/Main/districtlistings.aspx?Catid=7&dist=19',15,1)
GO


