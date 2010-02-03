
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



 //Epapers tables and statics

  drop table epapers
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
ALTER TABLE  epaper_url 
	ADD CONSTRAINT  fk_epaperUrl_epaper 
	FOREIGN KEY( epaper_id )
	REFERENCES  epaper ( epaper_id )
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT 
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