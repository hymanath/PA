                                                                     
 #Entitlement Scripts
 
 
drop table if exists group_entitlement_relation;
drop table if exists user_group_entitlement;
drop table if exists entitlement;
drop table if exists group_entitlement;
drop table if exists user_group_relation;
drop table if exists user_country_access_info;
drop table if exists user_state_access_info;
drop table if exists user_district_access_info;
drop table if exists user_constituency_access_info;
drop table if exists user_groups;

CREATE TABLE entitlement  ( 
	entitlement_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	entitlement_type	varchar(100) NULL,
	PRIMARY KEY(entitlement_id)
);
CREATE TABLE group_entitlement_relation  ( 
	group_entitlement_relation_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	entitlement_id  	bigint(15) NULL,
	group_entitlement_id	bigint(15) NULL,
	PRIMARY KEY(group_entitlement_relation_id)
);
CREATE TABLE group_entitlement  ( 
	group_entitlement_id	bigint(15) AUTO_INCREMENT NOT NULL,
	description        	varchar(250) NULL,
	PRIMARY KEY(group_entitlement_id)
);
CREATE TABLE user_group_entitlement  ( 
	user_group_entitlement_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_group_id            	bigint(15) NULL,
	group_entitlement_id     	bigint(15) NULL,
	PRIMARY KEY(user_group_entitlement_id)
);
CREATE TABLE user_groups  ( 
	user_group_id	bigint(15) AUTO_INCREMENT NOT NULL,
	notes        	varchar(250) NULL,
	PRIMARY KEY(user_group_id)
);
CREATE TABLE user_group_relation  ( 
	user_group_relation_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_id               	bigint(15) NULL,
	user_group_id         	bigint(15) NULL,
	PRIMARY KEY(user_group_relation_id)
);
CREATE  TABLE user_country_access_info (
	user_country_access_info_id BIGINT NOT NULL ,
	user_group_id BIGINT NULL ,
	country_id BIGINT NULL ,
	PRIMARY KEY (user_country_access_info_id) );

CREATE  TABLE user_state_access_info (
	user_state_access_info_id BIGINT NOT NULL ,
	user_group_id BIGINT NULL ,
	state_id BIGINT NULL ,
	PRIMARY KEY (user_state_access_info_id) );

CREATE  TABLE user_district_access_info (
	user_district_access_info_id BIGINT NOT NULL ,
	user_group_id BIGINT NULL ,
	district_id BIGINT NULL ,
	PRIMARY KEY (user_district_access_info_id) );

CREATE  TABLE user_constituency_access_info (
	user_constituency_access_info_id BIGINT NOT NULL ,
	user_group_id BIGINT NULL ,
	constituency_id BIGINT NULL ,
	PRIMARY KEY (user_constituency_access_info_id) );

ALTER TABLE group_entitlement_relation
	ADD CONSTRAINT fk_group_entitlement_group_entitlement_relation
	FOREIGN KEY(group_entitlement_id)
	REFERENCES group_entitlement(group_entitlement_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE group_entitlement_relation
	ADD CONSTRAINT fk_entitlement_group_entitlement_relation
	FOREIGN KEY(entitlement_id)
	REFERENCES entitlement(entitlement_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_group_entitlement
	ADD CONSTRAINT fk_user_group_entitlement_user_groups
	FOREIGN KEY(user_group_id)
	REFERENCES user_groups(user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_group_entitlement
	ADD CONSTRAINT fk_user_group_entitlement_group_entitlement
	FOREIGN KEY(group_entitlement_id)
	REFERENCES group_entitlement(group_entitlement_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_group_relation
	ADD CONSTRAINT fk_user_group_relation_user_group
	FOREIGN KEY(user_group_id)
	REFERENCES user_groups(user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_group_relation
	ADD CONSTRAINT fk_user_group_relation_user
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_country_access_info 
	ADD CONSTRAINT fk_registration_country_user
	FOREIGN KEY (user_group_id)
	REFERENCES user_groups (user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE user_country_access_info 
	ADD CONSTRAINT fk_country_country_user
	FOREIGN KEY (country_id)
	REFERENCES country (country_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE user_state_access_info 
	ADD CONSTRAINT fk_registration_state_user
	FOREIGN KEY (user_group_id)
	REFERENCES user_groups (user_group_id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE user_state_access_info 
	ADD CONSTRAINT fk_state_state_user
	FOREIGN KEY (state_id)
	REFERENCES state (state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE user_district_access_info 
	ADD CONSTRAINT fk_registration_district_user
	FOREIGN KEY (user_group_id)
	REFERENCES user_groups (user_group_id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

ALTER TABLE user_district_access_info 
	ADD CONSTRAINT fk_district_district_user
	FOREIGN KEY (district_id)
	REFERENCES district (district_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE user_constituency_access_info 
	ADD CONSTRAINT fk_registration_constituency_user
	FOREIGN KEY (user_group_id)
	REFERENCES user_groups (user_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE user_constituency_access_info 
	ADD CONSTRAINT fk_constituency_constituency_user
	FOREIGN KEY (constituency_id)
	REFERENCES constituency (constituency_id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

INSERT INTO group_entitlement VALUES(1,'PRIVILEGED_ENTITLEMENTS');
INSERT INTO group_entitlement VALUES(2,'DEFAULT_ENTITLEMENTS');

INSERT INTO entitlement VALUES(1,'CONSTITUENCY_PAGE');
INSERT INTO entitlement VALUES(2,'PARTY_PERFORMANCE_REPORT');
INSERT INTO entitlement VALUES(3,'ELECTION_COMPARISION_REPORT');
INSERT INTO entitlement VALUES(4,'PARTY_RESULTS_REPORT');
INSERT INTO entitlement VALUES(5,'PARTY_INFLUENCE_REPORT');
INSERT INTO entitlement VALUES(6,'CROSS_VOTING_REPORT');
INSERT INTO entitlement VALUES(7,'PARTY_BOOTHWISE_RESULTS_REPORT');
INSERT INTO entitlement VALUES(8,'ELECTION_RESULTS_ANALYSIS_REPORT');
INSERT INTO entitlement VALUES(9,'DISTRICT_LEVEL_ACCESS');
INSERT INTO entitlement VALUES(10,'CONSTITUENCY_LEVEL_ACCESS');

INSERT INTO group_entitlement_relation VALUES(1,2,1);
INSERT INTO group_entitlement_relation VALUES(2,3,1);
INSERT INTO group_entitlement_relation VALUES(3,4,1);
INSERT INTO group_entitlement_relation VALUES(4,5,1);
INSERT INTO group_entitlement_relation VALUES(5,6,1);
INSERT INTO group_entitlement_relation VALUES(6,7,1);
INSERT INTO group_entitlement_relation VALUES(7,8,1);
INSERT INTO group_entitlement_relation VALUES(8,3,2);
INSERT INTO group_entitlement_relation VALUES(9,4,2);

INSERT INTO user_groups VALUES(1,'NORMAL_USER');
INSERT INTO user_groups VALUES(2,'BJP_PARTY');

INSERT INTO user_group_entitlement (user_group_entitlement_id, user_group_id, group_entitlement_id) VALUES ('1', '2', '1');
INSERT INTO user_group_entitlement (user_group_entitlement_id, user_group_id, group_entitlement_id) VALUES ('2', '2', '2');

INSERT INTO user_group_relation (user_group_relation_id, user_id, user_group_id) VALUES ('1', '1', '2');



                                                                     
# modifications


ALTER TABLE constituency_election MODIFY COLUMN reservation_zone VARCHAR(25);
ALTER TABLE election_scope MODIFY COLUMN state_id bigint(15) NULL;
UPDATE election_scope set state_id = null where election_scope_id = 1 and country_id = 1;
ALTER TABLE party_election_district_result modify COLUMN state_id bigint(15) NULL;


CREATE TABLE sms_history  ( 
	sms_history_id   	bigint(15) AUTO_INCREMENT NOT NULL,
	user_sms_track_id	bigint(15) NULL,
	mobile_no        	varchar(25) NULL,
	sms_content      	varchar(400) NULL,
	sent_date        	timestamp NULL,
	location_type    	varchar(100) NULL,
	location_id      	bigint(15) NULL,
	sms_module_id    	bigint(15) NULL,
	PRIMARY KEY(sms_history_id)
);
CREATE TABLE sms_module  ( 
	sms_module_id	bigint(15) AUTO_INCREMENT NOT NULL,
	module_name  	varchar(100) NULL,
	description  	varchar(300) NULL,
	PRIMARY KEY(sms_module_id)
);
CREATE TABLE user_sms_track  ( 
	user_sms_track_id	bigint(15) AUTO_INCREMENT NOT NULL,
	user_id          	bigint(15) NULL,
	renewal_sms_count	bigint(15) NULL,
	renewal_date     	timestamp NULL,
	description      	varchar(300) NULL,
	PRIMARY KEY(user_sms_track_id)
) ;
ALTER TABLE sms_history
    ADD CONSTRAINT fk_sms_history_user_sms_track
	FOREIGN KEY(user_sms_track_id)
	REFERENCES user_sms_track(user_sms_track_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE user_sms_track
    ADD CONSTRAINT fk_user_sms_track_registration
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE sms_history
    ADD CONSTRAINT sms_module_id_sms_history
	FOREIGN KEY(sms_module_id)
	REFERENCES sms_module(sms_module_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
insert into sms_module values(1,"User Groups","");;
insert into sms_module values(2,"Cadre Management","");;
insert into sms_module values(3,"Influencing People","");



# Cadre Modi


ALTER TABLE cadre
	ADD COLUMN date_of_birth date NULL, 
	ADD COLUMN telephone varchar(25) NULL, 
	ADD COLUMN house_No varchar(25) NULL, 
	ADD COLUMN street varchar(75) NULL, 
	ADD COLUMN pin_code varchar(25) NULL, 
	ADD COLUMN education varchar(75) NULL, 
	ADD COLUMN occupation varchar(75) NULL, 
	ADD COLUMN caste_category varchar(25) NULL,
	ADD COLUMN annual_income double,
	ADD COLUMN member_type varchar(25) NULL,
	ADD COLUMN current_address bigint(20) NULL,
	ADD COLUMN permanent_address bigint(20) NULL,
	ADD COLUMN party_wkg_committee_designation_id bigint(20) NULL,
	ADD COLUMN exact_date_of_birth varchar(25) NULL,
	ADD COLUMN effective_date date NULL, 
	ADD COLUMN ending_date date NULL,
	ADD COLUMN father_or_spouse_name varchar(100) NULL;


CREATE TABLE party_working_committee  ( 
    party_working_committee_id	bigint(15) AUTO_INCREMENT NOT NULL,
    party_id                  	bigint(15) NULL,
    committee_name            	varchar(100) NULL,
    region_level		bigint(20) NULL,
    region_level_value          bigint(20) NULL,
    PRIMARY KEY(party_working_committee_id);

ALTER TABLE party_working_committee
    ADD CONSTRAINT fk_party_working_committee_party
	FOREIGN KEY(party_id)
	REFERENCES party(party_id);

ALTER TABLE party_working_committee
    ADD CONSTRAINT fk_party_working_committee_cadre_level
	FOREIGN KEY(region_level)
	REFERENCES cadre_level(cadre_level_id);

CREATE TABLE party_wkg_committee_designation  ( 
    party_wkg_committee_designation_id	bigint(15) AUTO_INCREMENT NOT NULL,
    party_working_committee_id        	bigint(15) NULL,
    designation                       	varchar(100) NULL,
    PRIMARY KEY(party_wkg_committee_designation_id);

ALTER TABLE cadre
    ADD CONSTRAINT fk_cadre_party_wkg_committee_designation
	FOREIGN KEY(party_wkg_committee_designation_id)
	REFERENCES party_wkg_committee_designation(party_wkg_committee_designation_id);

ALTER TABLE party_wkg_committee_designation
    ADD CONSTRAINT fk_party_wkg_committee_designation_party_working_committee
	FOREIGN KEY(party_working_committee_id)
	REFERENCES party_working_committee(party_working_committee_id);

CREATE TABLE user_address ( 
	user_address_id	bigint(20) AUTO_INCREMENT NOT NULL,
	country_id     	bigint(20) NULL,
	state_id       	bigint(20) NULL,
	district_id    	bigint(20) NULL,
	constituency_id	bigint(20) NULL,
	tehsil_id      	bigint(20) NULL,
	township_id    	bigint(20) NULL,
	hamlet_id      	bigint(20) NULL,
	house_no       	varchar(25) NULL,
	street         	varchar(100) NULL,
	pincode        	varchar(10) NULL,
    PRIMARY KEY(user_address_id)
	);

ALTER TABLE user_address
	ADD CONSTRAINT fk_country_user_address
	FOREIGN KEY(country_id)
	REFERENCES country(country_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_state_user_address
	FOREIGN KEY(state_id)
	REFERENCES state(state_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_district_user_address
	FOREIGN KEY(district_id)
	REFERENCES district(district_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_constituency_user_address
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_tehsil_user_address
	FOREIGN KEY(tehsil_id)
	REFERENCES tehsil(tehsil_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_township_user_address
	FOREIGN KEY(township_id)
	REFERENCES township(township_id);

ALTER TABLE user_address
	ADD CONSTRAINT fk_hamlet_user_address
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id);


ALTER TABLE cadre
	ADD CONSTRAINT fk_cadre_user_address
	FOREIGN KEY(current_address)
	REFERENCES user_address(user_address_id);

ALTER TABLE cadre
	ADD CONSTRAINT fk_user_address_cadre
	FOREIGN KEY(permanent_address)
	REFERENCES user_address(user_address_id);

ALTER TABLE cadre
    ADD CONSTRAINT fk_cadre_party_wkg_committee_designation
	FOREIGN KEY(party_wkg_committee_designation_id)
	REFERENCES party_wkg_committee_designation(party_wkg_committee_designation_id);

CREATE TABLE social_category ( 
	social_category_id	bigint(20) AUTO_INCREMENT NOT NULL,
	category          	varchar(10) NOT NULL,
	PRIMARY KEY(social_category_id)
);
CREATE TABLE `educational_qualifications` ( 
	`educational_qualification_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`qualification`               	varchar(75) NOT NULL,
	PRIMARY KEY(`educational_qualification_id`)
);

CREATE TABLE `occupation` ( 
	`occupation_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`occupation`   	varchar(100) NOT NULL,
	PRIMARY KEY(`occupation_id`)
);
CREATE TABLE `language` ( 
	`language_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`language`   	varchar(50) NOT NULL,
	PRIMARY KEY(`language_id`)
);
CREATE TABLE `cadre_language_efficiency` ( 
	`cadre_language_efficiency_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`cadre_id`                    	bigint(20) NULL,
	`language_id`                 	bigint(20) NULL,
	`is_able_to_speak`            	varchar(25) NULL,
	`is_able_to_read`             	varchar(25) NULL,
	`is_able_to_write`            	varchar(25) NULL,
	PRIMARY KEY(`cadre_language_efficiency_id`)
);

ALTER TABLE `cadre_language_efficiency`
	ADD CONSTRAINT `fk_language_cadre_language_efficiency`
	FOREIGN KEY(`language_id`)
	REFERENCES `language`(`language_id`);

ALTER TABLE `cadre_language_efficiency`
	ADD CONSTRAINT `fk_cadre_cadre_language_efficiency`
	FOREIGN KEY(`cadre_id`)
	REFERENCES `cadre`(`cadre_id`)
;
CREATE TABLE `party_cadre_skills` ( 
	`party_cadre_skill_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`skill`               	varchar(100) NULL,
	`party_id`            	bigint(20) NULL,
	PRIMARY KEY(`party_cadre_skill_id`)
);

ALTER TABLE `party_cadre_skills`
	ADD CONSTRAINT `fk_party_party_cadre_skills`
	FOREIGN KEY(`party_id`)
	REFERENCES `party`(`party_id`);
	CREATE TABLE `party_training_camps` ( 
	`party_training_camps_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`region_level`                 	varchar(100) NOT NULL,
	PRIMARY KEY(`party_training_camps_id`)
);

ALTER TABLE `party_training_camps`
	ADD COLUMN `party_id` bigint(20) NULL;
ALTER TABLE `party_training_camps`
	ADD CONSTRAINT `fk_party_party_training_camps`
	FOREIGN KEY(`party_id`)
	REFERENCES `party`(`party_id`);

CREATE TABLE `cadre_participated_training_camps` ( 
	`cadre_participated_training_camp_id`	bigint(20) AUTO_INCREMENT NOT NULL,
	`cadre_id`                           	bigint(20) NOT NULL,
	`party_training_camps_id`            	bigint(20) NOT NULL,
	PRIMARY KEY(`cadre_participated_training_camp_id`)
);

ALTER TABLE `cadre_participated_training_camps`
	ADD CONSTRAINT `fk_party_training_camps_cadre_participated_training_camp`
	FOREIGN KEY(`party_training_camps_id`)
	REFERENCES `party_training_camps`(`party_training_camps_id`);

ALTER TABLE `cadre_participated_training_camps`
	ADD CONSTRAINT `fk_cadre_cadre_participated_training_camp`
	FOREIGN KEY(`cadre_id`)
	REFERENCES `cadre`(`cadre_id`);

CREATE TABLE `cadre_skills` ( 
	`cadre_skill_id`      	bigint(20) AUTO_INCREMENT NOT NULL,
	`party_cadre_skill_id`	bigint(20) NOT NULL,
	`cadre_id`            	bigint(20) NOT NULL,
	PRIMARY KEY(`cadre_skill_id`)
);

ALTER TABLE `cadre_skills`
	ADD CONSTRAINT `fk_party_cadre_skills_cadre_skills`
	FOREIGN KEY(`party_cadre_skill_id`)
	REFERENCES `party_cadre_skills`(`party_cadre_skill_id`);

ALTER TABLE `cadre_skills`
	ADD CONSTRAINT `fk_cadre_cadre_skills`
	FOREIGN KEY(`cadre_id`)
	REFERENCES `cadre`(`cadre_id`);


alter table cadre add foreign key(occupation) references occupation(occupation_id);

alter table cadre add foreign key(education) references educational_qualifications(educational_qualification_id);

alter table cadre add foreign key(caste_category) references social_category(social_category_id);

UPDATE party_cadre_skills SET skill='Good Singing Skills' WHERE party_cadre_skill_id=4;
UPDATE party_cadre_skills SET skill='Good Public Relations' WHERE party_cadre_skill_id=3;



INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(1, 'Un Educated');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(2, 'Below X th Class');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(3, 'X th /Inter/Diploma');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(4, 'Graduation');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(5, 'Professional Degree');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(6, 'Post Graduation');
INSERT INTO `party_analyst_schema`.`educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(7, 'Doctorate');


INSERT INTO `party_analyst_schema`.`social_category`(`social_category_id`, `category`)
VALUES(1, 'ST');
INSERT INTO `party_analyst_schema`.`social_category`(`social_category_id`, `category`)
VALUES(2, 'SC');
INSERT INTO `party_analyst_schema`.`social_category`(`social_category_id`, `category`)
VALUES(3, 'BC');
INSERT INTO `party_analyst_schema`.`social_category`(`social_category_id`, `category`)
VALUES(4, 'Minority');
INSERT INTO `party_analyst_schema`.`social_category`(`social_category_id`, `category`)
VALUES(5, 'General');


INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(1, 'Agriculture');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(7, 'Lawyer');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(2, 'Handicrafts');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(3, 'Business');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(4, 'Industrialist');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(5, 'Management');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(6, 'Doctor');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(12, 'House Wife');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(8, 'Chartered Accountant');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(9, 'Scientist Engineer');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(10, 'Teaching');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(11, 'Computer Operator');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(13, 'Daily Labour');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(14, 'Social Service');
INSERT INTO `party_analyst_schema`.`occupation`(`occupation_id`, `occupation`)
VALUES(15, 'Others');


INSERT INTO `party_analyst_schema`.`language`(`language_id`, `language`)
VALUES(1, 'English');
INSERT INTO `party_analyst_schema`.`language`(`language_id`, `language`)
VALUES(2, 'Hindi');



INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(1, 'Funds Collection/Accounts Mgmt', 15);
INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(2, 'Election Campaign', 15);
INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(5, 'Social/Political Problems Analysis', 15);
INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(4, 'Good Singing Skills', 15);
INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(3, 'Good Public Relations', 15);
INSERT INTO `party_analyst_schema`.`party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(6, 'Writing Essays/Poems', 15);



INSERT INTO `party_analyst_schema`.`party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(5, 'National Level', 15);
INSERT INTO `party_analyst_schema`.`party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(4, 'State Level', 15);
INSERT INTO `party_analyst_schema`.`party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(3, 'Zonal Level', 15);
INSERT INTO `party_analyst_schema`.`party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(2, 'District Level', 15);
INSERT INTO `party_analyst_schema`.`party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(1, 'Mandal Level', 15);


UPDATE `party_analyst_schema`.`party_cadre_skills` SET `skill`='Good Singing Skills' WHERE `party_cadre_skill_id`='4';
UPDATE `party_analyst_schema`.`party_cadre_skills` SET `skill`='Good Public Relations' WHERE `party_cadre_skill_id`='3';


update party set long_name='PRAJA RAJYAM PARTY' WHERE PARTY_ID=661;
update party set long_name='PANCHAYAT RAJ PARTY' WHERE PARTY_ID=653;



INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(1,4,1,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(2,4,2,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(3,4,5,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(4,5,3,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(5,5,4,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(6,2,1,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(7,2,2,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(8,2,5,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(9,3,3,1);
INSERT INTO `party_analyst_schema`.`election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(10,3,4,1);


insert into alliance_groups values(1,871,1);

insert into alliance_groups values(2,885,1);

insert into alliance_groups values(3,265,1);

insert into alliance_groups values(4,268,1);

insert into alliance_groups values(5,361,3);

insert into alliance_groups values(6,885,3);

insert into alliance_groups values(7,265,3);

insert into alliance_groups values(8,268,3);

insert into alliance_groups values(9,871,4);

insert into alliance_groups values(10,163,4);

insert into alliance_groups values(11,661,5);

insert into alliance_groups values(12,525,5);