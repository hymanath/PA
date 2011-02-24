                                                                     
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
INSERT INTO entitlement VALUES(11,'CONSTITUENCY_ANALYSIS');
INSERT INTO entitlement VALUES(12,'TEHSIL_ANALYSIS');
INSERT INTO entitlement VALUES(13,'DISTRICT_PAGE_ALL_ELECTION_HIRARCHIES');
INSERT INTO ENTITLEMENT VALUES ('14', 'CONSTITUENCY_PAGE_COMPLETE_VIEW');
INSERT INTO ENTITLEMENT VALUES ('15', 'ENTITLEMENT_PAGE');
INSERT INTO entitlement VALUES ('16', 'MANDAL_VOTING_TRENDZ');
INSERT INTO entitlement VALUES ('17', 'VOTING_TRENDZ');


INSERT INTO group_entitlement_relation VALUES(1,2,1);
INSERT INTO group_entitlement_relation VALUES(2,3,1);
INSERT INTO group_entitlement_relation VALUES(3,4,1);
INSERT INTO group_entitlement_relation VALUES(4,5,1);
INSERT INTO group_entitlement_relation VALUES(5,6,1);
INSERT INTO group_entitlement_relation VALUES(6,7,1);
INSERT INTO group_entitlement_relation VALUES(7,8,1);
INSERT INTO group_entitlement_relation VALUES(8,3,2);
INSERT INTO group_entitlement_relation VALUES(9,4,2);
INSERT INTO group_entitlement_relation VALUES(10, 11, 1);
INSERT INTO group_entitlement_relation VALUES(11, 12, 1);
INSERT INTO group_entitlement_relation VALUES(12, 13, 3);
INSERT INTO group_entitlement_relation VALUES(13, 14, 1);
INSERT INTO group_entitlement_relation VALUES ('14', '15', '1');
INSERT INTO group_entitlement_relation VALUES ('15', '16', '1');
INSERT INTO group_entitlement_relation VALUES ('16', '17', '1');

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
	ADD COLUMN education BIGINT NULL
	ADD COLUMN occupation BIGINT NULL NULL, 
	ADD COLUMN caste_category BIGINT NULL,
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

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(1,163,'State Committee');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(2,163,'Kisan Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(3,163,'Dalita Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(4,163,'Mahila Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(5,163,'Yuva Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(6,163,'Girijana Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(7,163,'Minority Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(8,163,'Cells');

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

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(1,163,'State Committee');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(2,163,'Kisan Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(3,163,'Dalita Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(4,163,'Mahila Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(5,163,'Yuva Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(6,163,'Girijana Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(7,163,'Minority Morcha');

insert into party_working_committee(party_working_committee_id,party_id,committee_name) values(8,163,'Cells');

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

INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(1, 'Un Educated');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(2, 'Below X th Class');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(3, 'X th /Inter/Diploma');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(4, 'Graduation');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(5, 'Professional Degree');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(6, 'Post Graduation');
INSERT INTO `educational_qualifications`(`educational_qualification_id`, `qualification`)
VALUES(7, 'Doctorate');
INSERT INTO `educational_qualifications` (`educational_qualification_id`, `qualification`) VALUES ('8', 'Not Applicable');


INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(1, 'ST');
INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(2, 'SC');
INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(3, 'BC');
INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(4, 'Minority');
INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(5, 'General');
INSERT INTO `social_category`(`social_category_id`, `category`)
VALUES(6, 'N/A');


INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(1, 'Agriculture');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(7, 'Lawyer');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(2, 'Handicrafts');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(3, 'Business');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(4, 'Industrialist');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(5, 'Management');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(6, 'Doctor');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(12, 'House Wife');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(8, 'Chartered Accountant');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(9, 'Scientist Engineer');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(10, 'Teaching');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(11, 'Computer Operator');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(13, 'Daily Labour');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(14, 'Social Service');
INSERT INTO `occupation`(`occupation_id`, `occupation`)
VALUES(15, 'Others');
INSERT INTO `occupation` (`occupation_id`, `occupation`) VALUES ('16', 'Not Applicable');


INSERT INTO `language`(`language_id`, `language`)
VALUES(1, 'English');
INSERT INTO `language`(`language_id`, `language`)
VALUES(2, 'Hindi');



INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(1, 'Funds Collection/Accounts Mgmt', 163);
INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(2, 'Election Campaign', 163);
INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(5, 'Social/Political Problems Analysis', 163);
INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(4, 'Good Singing Skills', 163);
INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(3, 'Good Public Relations', 163);
INSERT INTO `party_cadre_skills`(`party_cadre_skill_id`, `skill`, `party_id`)
VALUES(6, 'Writing Essays/Poems', 163);



INSERT INTO `party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(5, 'National Level', 163);
INSERT INTO `party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(4, 'State Level', 163);
INSERT INTO `party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(3, 'Zonal Level', 163);
INSERT INTO `party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(2, 'District Level', 163);
INSERT INTO `party_training_camps`(`party_training_camps_id`, `region_level`, `party_id`)
VALUES(1, 'Mandal Level', 163);


update party set long_name='PRAJA RAJYAM PARTY' WHERE PARTY_ID=661;
update party set long_name='PANCHAYAT RAJ PARTY' WHERE PARTY_ID=653;



INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(1,4,1,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(2,4,2,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(3,4,5,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(4,5,3,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(5,5,4,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(6,2,1,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(7,2,2,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(8,2,5,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
VALUES(9,3,3,1);
INSERT INTO `election_alliances`(`election_alliance_id`,`election_id`,`group_id`,state_id)
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

#Party TAble MOdi.


ALTER TABLE party ADD state_id bigint(15) NULL;

ALTER TABLE party
	ADD CONSTRAINT fk_party_state
	FOREIGN KEY(state_id)
	REFERENCES state(state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
   
update party set state_id = 24 where party_id in (284,50,537,912,283,520,641);
update party set party_recognization="SP" where party_id in (284,50,537,912,283,520,641);

update party set state_id = 12 where party_id in (390,392,456,469,743);
update party set party_recognization="SP" where party_id in (390,392,456,469,743);

update party set state_id = 1 where party_id in (871,885,661,513,72);
update party set party_recognization="SP" where party_id in (871,885,661,513,72);

                                                   
                                                                     
                                                                     
                                             

CREATE TABLE political_changes_information_source  ( 
	source_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	source_name	varchar(50) NULL,
	description	varchar(250) NULL,
	PRIMARY KEY(source_id)
);
CREATE TABLE political_changes  ( 
	political_changes_id                   	bigint(15) AUTO_INCREMENT NOT NULL,
	party_id                               	bigint(15) NULL,
	user_id                                	bigint(15) NULL,
	information_source_id			bigint(15) NULL,
	external_source_id                     	bigint(15) NULL,	
	title                                  	varchar(200) NULL,
	description                            	varchar(500) NULL,
	identified_date                        	date NULL,
	occured_date                           	date NULL,
	updated_date                           	date NULL,
	is_delete	                  	varchar(25) NULL,
	effected_range				VARCHAR(250) NULL,
	effected_location			BIGINT(15) NULL 
	PRIMARY KEY(political_changes_id)
);

ALTER TABLE political_changes
	ADD CONSTRAINT fk_information_source_political_changes
	FOREIGN KEY(information_source_id)
	REFERENCES information_source(information_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE political_changes
    ADD CONSTRAINT fk_local_political_changes_external_source
	FOREIGN KEY(external_source_id)
	REFERENCES problem_external_source(problem_external_source_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE political_changes
    ADD CONSTRAINT fk_local_political_changes_registration
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE political_changes
    ADD CONSTRAINT fk_political_changes_party
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

insert into political_changes_information_source values(1,"USER",NULL);;
insert into political_changes_information_source values(2,"EXTERNAL PERSON",NULL);;
insert into political_changes_information_source values(3,"MEDIA",NULL);

#Karnataka and Tamilnadu states delimitation statics
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'B T M LAYOUT', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BABALESHWAR', 6, '2008-03-03', null, 12, 58, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BANGALORE SOUTH', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BANGARAPET', 6, '2008-03-03', null, 12, 74, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BELGAUM DAKSHIN', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BELGAUM RURAL', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BELGAUM UTTAR', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BELLARY CITY', 6, '2008-03-03', null, 12, 67, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BIDAR SOUTH', 6, '2008-03-03', null, 12, 60, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BIJAPUR CITY', 6, '2008-03-03', null, 12, 58, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BOMMANA HALLI', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BYATARAYANAPURA', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'C V RAMAN NAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHIKKODI SADALGA', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'CHITTAPUR', 6, '2008-03-03', null, 12, 59, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DASARAHALLI', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DAVANGERE NORTH', 6, '2008-03-03', null, 12, 69, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DAVANGERE SOUTH', 6, '2008-03-03', null, 12, 69, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'DEVAR HIPPARGI', 6, '2008-03-03', null, 12, 58, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GOVINDRAJ NAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GULBARGA DAKSHIN', 6, '2008-03-03', null, 12, 59, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GULBARGA RURAL', 6, '2008-03-03', null, 12, 59, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'GULBARGA UTTAR', 6, '2008-03-03', null, 12, 59, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HEBBAL', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUBLI DHARWAD CENTRAL', 6, '2008-03-03', null, 12, 64, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUBLI DHARWAD EAST', 6, '2008-03-03', null, 12, 64, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HUBLI DHARWAD WEST', 6, '2008-03-03', null, 12, 64, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'K R PURA', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'KUDACHI', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAHADEVAPURA', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MAHALAKSHMI LAYOUT', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANGALORE CITY NORTH', 6, '2008-03-03', null, 12, 79, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MANGALORE CITY SOUTH', 6, '2008-03-03', null, 12, 79, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MASKI', 6, '2008-03-03', null, 12, 61, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'MELUKOTE', 6, '2008-03-03', null, 12, 77, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'NAGTHAN', 6, '2008-03-03', null, 12, 58, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PADMANABA NAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'PULAKESHINAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAICHUR RURAL', 6, '2008-03-03', null, 12, 61, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'RAJARAJESHWARINAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SARVAGNANAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SAUNDATTI YELLAMMA', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'SHIMOGA RURAL', 6, '2008-03-03', null, 12, 70, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TERDAL', 6, '2008-03-03', null, 12, 57, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TUMKUR CITY', 6, '2008-03-03', null, 12, 73, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'TUMKUR RURAL', 6, '2008-03-03', null, 12, 73, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VARUNA', 6, '2008-03-03', null, 12, 81, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAY NAGAR', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'VIJAYANAGARA', 6, '2008-03-03', null, 12, 67, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YELLAPUR', 6, '2008-03-03', null, 12, 65, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YEMKANMARDI', 6, '2008-03-03', null, 12, 56, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'YESVANTHAPURA', 6, '2008-03-03', null, 12, 75, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HAGARIBOMMANAHALLI', 6, '2008-03-03', null, 12, 67, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'HOMNABAD', 6, '2008-03-03', null, 12, 60, 1);
INSERT INTO constituency(name, election_scope_id, start_date, deform_date, state_id, district_id, country_id)    VALUES( 'BABALESHWAR', 6, '2008-03-03', null, 12, 58, 1);

insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3250, (select constituency_id from constituency where name=upper('Bagalkot')  and election_scope_id=1 and state_id=12),2009 ,1);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3251, (select constituency_id from constituency where name=upper('Bangalore Central')  and election_scope_id=1 and state_id=12),2009 ,2);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3252, (select constituency_id from constituency where name=upper('Bangalore North')  and election_scope_id=1 and state_id=12),2009 ,3);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3253, (select constituency_id from constituency where name=upper('Bangalore Rural')  and election_scope_id=1 and state_id=12),2009 ,4);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3254, (select constituency_id from constituency where name=upper('Bangalore South')  and election_scope_id=1 and state_id=12),2009 ,5);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3255, (select constituency_id from constituency where name=upper('Belgaum')  and election_scope_id=1 and state_id=12),2009 ,6);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3256, (select constituency_id from constituency where name=upper('Bellary ')  and election_scope_id=1 and state_id=12),2009 ,7);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3257, (select constituency_id from constituency where name=upper('Bidar')  and election_scope_id=1 and state_id=12),2009 ,8);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3258, (select constituency_id from constituency where name=upper('Bijapur')  and election_scope_id=1 and state_id=12),2009 ,9);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3259, (select constituency_id from constituency where name=upper('Chamarajanagar')  and election_scope_id=1 and state_id=12),2009 ,10);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3260, (select constituency_id from constituency where name=upper('Chikballapur')  and election_scope_id=1 and state_id=12),2009 ,11);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3261, (select constituency_id from constituency where name=upper('Chikkodi')  and election_scope_id=1 and state_id=12),2009 ,12);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3262, (select constituency_id from constituency where name=upper('Chitradurga')  and election_scope_id=1 and state_id=12),2009 ,13);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3263, (select constituency_id from constituency where name=upper('Dakshina Kannada')  and election_scope_id=1 and state_id=12),2009 ,14);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3264, (select constituency_id from constituency where name=upper('Davanagere')  and election_scope_id=1 and state_id=12),2009 ,15);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3265, (select constituency_id from constituency where name=upper('Dharwad')  and election_scope_id=1 and state_id=12),2009 ,16);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3266, (select constituency_id from constituency where name=upper('Gulbarga')  and election_scope_id=1 and state_id=12),2009 ,17);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3267, (select constituency_id from constituency where name=upper('Hassan')  and election_scope_id=1 and state_id=12),2009 ,18);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3268, (select constituency_id from constituency where name=upper('Haveri')  and election_scope_id=1 and state_id=12),2009 ,19);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3269, (select constituency_id from constituency where name=upper('Kolar')  and election_scope_id=1 and state_id=12),2009 ,20);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3270, (select constituency_id from constituency where name=upper('Koppal')  and election_scope_id=1 and state_id=12),2009 ,21);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3271, (select constituency_id from constituency where name=upper('Mandya')  and election_scope_id=1 and state_id=12),2009 ,22);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3272, (select constituency_id from constituency where name=upper('Mysore')  and election_scope_id=1 and state_id=12),2009 ,23);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3273, (select constituency_id from constituency where name=upper('Raichur')  and election_scope_id=1 and state_id=12),2009 ,24);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3274, (select constituency_id from constituency where name=upper('Shimoga')  and election_scope_id=1 and state_id=12),2009 ,25);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3275, (select constituency_id from constituency where name=upper('Tumkur')  and election_scope_id=1 and state_id=12),2009 ,26);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3276, (select constituency_id from constituency where name=upper('Udupi Chikmagalur')  and election_scope_id=1 and state_id=12),2009 ,27);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3277, (select constituency_id from constituency where name=upper('Uttara Kannada')  and election_scope_id=1 and state_id=12),2009 ,28);

insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6000,3261,(select constituency_id from constituency where upper(name)=upper('Nippani') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6001,3261,(select constituency_id from constituency where upper(name)=upper('CHIKKODI ') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6002,3261,(select constituency_id from constituency where upper(name)=upper('ATHANI') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6003,3261,(select constituency_id from constituency where upper(name)=upper('KAGWAD') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6004,3261,(select constituency_id from constituency where upper(name)=upper('Kudachi') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6005,3261,(select constituency_id from constituency where upper(name)=upper('RAIBAG ') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6006,3261,(select constituency_id from constituency where upper(name)=upper('HUKKERI') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6007,3261,(select constituency_id from constituency where upper(name)=upper('Yemkanmardi') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6008,3255,(select constituency_id from constituency where upper(name)=upper('ARABHAVI') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6009,3255,(select constituency_id from constituency where upper(name)=upper('GOKAK ') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6010,3255,(select constituency_id from constituency where upper(name)=upper('Belgaum Uttar') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6011,3255,(select constituency_id from constituency where upper(name)=upper('Belgaum Dakshin') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6012,3255,(select constituency_id from constituency where upper(name)=upper('Belgaum Rural') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6013,3255,(select constituency_id from constituency where upper(name)=upper('BAILHONGAL') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6014,3255,(select constituency_id from constituency where upper(name)=upper('Saundatti Yellamma') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6015,3255,(select constituency_id from constituency where upper(name)=upper('Ramdurg') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6016,3250,(select constituency_id from constituency where upper(name)=upper('MUDHOL ') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6017,3250,(select constituency_id from constituency where upper(name)=upper('Terdal') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6018,3250,(select constituency_id from constituency where upper(name)=upper('JAMKHANDI') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6019,3250,(select constituency_id from constituency where upper(name)=upper('Bilgi') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6020,3250,(select constituency_id from constituency where upper(name)=upper('Badami') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6021,3250,(select constituency_id from constituency where upper(name)=upper('Bagalkot') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6022,3250,(select constituency_id from constituency where upper(name)=upper('HUNGUND') and election_scope_id=6 and district_id=57 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6023,3250,(select constituency_id from constituency where upper(name)=upper('NARAGUND') and election_scope_id=6 and district_id=63 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6024,3258,(select constituency_id from constituency where upper(name)=upper('Muddebihal') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6025,3258,(select constituency_id from constituency where upper(name)=upper('Devar Hippargi') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6026,3258,(select constituency_id from constituency where upper(name)=upper('BASAVANA BAGEWADI') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6028,3258,(select constituency_id from constituency where upper(name)=upper('BIJAPUR') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6029,3258,(select constituency_id from constituency where upper(name)=upper('Nagthan') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6030,3258,(select constituency_id from constituency where upper(name)=upper('INDI') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6031,3258,(select constituency_id from constituency where upper(name)=upper('Sindgi') and election_scope_id=6 and district_id=58 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6032,3266,(select constituency_id from constituency where upper(name)=upper('AFZALPUR') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6033,3266,(select constituency_id from constituency where upper(name)=upper('JEWARGI') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6034,3266,(select constituency_id from constituency where upper(name)=upper('GURMITKAL ') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6035,3266,(select constituency_id from constituency where upper(name)=upper('CHITAPUR') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6036,3266,(select constituency_id from constituency where upper(name)=upper('Sedam') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6037,3266,(select constituency_id from constituency where upper(name)=upper('Gulbarga Rural') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6038,3266,(select constituency_id from constituency where upper(name)=upper('Gulbarga Dakshin') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6039,3266,(select constituency_id from constituency where upper(name)=upper('Gulbarga Uttar') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6040,3273,(select constituency_id from constituency where upper(name)=upper('Shorapur') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6041,3273,(select constituency_id from constituency where upper(name)=upper('Shahapur') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6042,3273,(select constituency_id from constituency where upper(name)=upper('Yadgir') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6043,3273,(select constituency_id from constituency where upper(name)=upper('Raichur') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6044,3273,(select constituency_id from constituency where upper(name)=upper('RAICHUR RURAL') and election_scope_id=6 and district_id=61 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6045,3273,(select constituency_id from constituency where upper(name)=upper('Raichur') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6046,3273,(select constituency_id from constituency where upper(name)=upper('Manvi') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6048,3273,(select constituency_id from constituency where upper(name)=upper('Lingsugur') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6049,3257,(select constituency_id from constituency where upper(name)=upper('CHINCHOLI') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6050,3257,(select constituency_id from constituency where upper(name)=upper('ALLAND') and election_scope_id=6 and district_id=59 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6051,3257,(select constituency_id from constituency where upper(name)=upper('Basavakalyan') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6052,3257,(select constituency_id from constituency where upper(name)=upper('HUMNABAD') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6053,3257,(select constituency_id from constituency where upper(name)=upper('Bidar South') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6054,3257,(select constituency_id from constituency where upper(name)=upper('Bidar') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6055,3257,(select constituency_id from constituency where upper(name)=upper('Bhalki') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6056,3257,(select constituency_id from constituency where upper(name)=upper('Aurad') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6057,3270,(select constituency_id from constituency where upper(name)=upper('SINDHNOOR') and election_scope_id=6 and district_id=60 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6058,3270,(select constituency_id from constituency where upper(name)=upper('Maski') and election_scope_id=6 and district_id=61 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6059,3270,(select constituency_id from constituency where upper(name)=upper('Kushtagi') and election_scope_id=6 and district_id=62 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6060,3270,(select constituency_id from constituency where upper(name)=upper('Kanakagiri') and election_scope_id=6 and district_id=62 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6061,3270,(select constituency_id from constituency where upper(name)=upper('GANGAVATHI') and election_scope_id=6 and district_id=62 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6062,3270,(select constituency_id from constituency where upper(name)=upper('Yelburga') and election_scope_id=6 and district_id=62 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6063,3270,(select constituency_id from constituency where upper(name)=upper('Koppal') and election_scope_id=6 and district_id=62 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6064,3270,(select constituency_id from constituency where upper(name)=upper('Siruguppa') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6065,3256,(select constituency_id from constituency where upper(name)=upper('Hadagalli') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6066,3256,(select constituency_id from constituency where upper(name)=upper('Hagaribommanahalli') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6067,3256,(select constituency_id from constituency where upper(name)=upper('Vijayanagara') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6068,3256,(select constituency_id from constituency where upper(name)=upper('Kampli') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6069,3256,(select constituency_id from constituency where upper(name)=upper('Bellary') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6070,3256,(select constituency_id from constituency where upper(name)=upper('Bellary City') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6071,3256,(select constituency_id from constituency where upper(name)=upper('Sandur') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6072,3256,(select constituency_id from constituency where upper(name)=upper('Kudligi') and election_scope_id=6 and district_id=67 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6073,3268,(select constituency_id from constituency where upper(name)=upper('SHIRHATTI') and election_scope_id=6 and district_id=63 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6074,3268,(select constituency_id from constituency where upper(name)=upper('Gadag') and election_scope_id=6 and district_id=63 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6075,3268,(select constituency_id from constituency where upper(name)=upper('Ron') and election_scope_id=6 and district_id=63 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6076,3268,(select constituency_id from constituency where upper(name)=upper('Hangal') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6077,3268,(select constituency_id from constituency where upper(name)=upper('Haveri') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6078,3268,(select constituency_id from constituency where upper(name)=upper('BYADGI ') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6079,3268,(select constituency_id from constituency where upper(name)=upper('Hirekerur') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6080,3268,(select constituency_id from constituency where upper(name)=upper('Ranibennur') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6081,3265,(select constituency_id from constituency where upper(name)=upper('Navalgund') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6082,3265,(select constituency_id from constituency where upper(name)=upper('Kundgol') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6083,3265,(select constituency_id from constituency where upper(name)=upper('Dharwad') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6084,3265,(select constituency_id from constituency where upper(name)=upper('Hubli Dharwad East') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6085,3265,(select constituency_id from constituency where upper(name)=upper('Hubli Dharwad Central') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6086,3265,(select constituency_id from constituency where upper(name)=upper('Hubli Dharwad West') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6087,3265,(select constituency_id from constituency where upper(name)=upper('Kalghatgi') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6088,3265,(select constituency_id from constituency where upper(name)=upper('Shiggaon') and election_scope_id=6 and district_id=66 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6089,3277,(select constituency_id from constituency where upper(name)=upper('Khanapur') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6090,3277,(select constituency_id from constituency where upper(name)=upper('Kittur') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6091,3277,(select constituency_id from constituency where upper(name)=upper('Haliyal') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6092,3277,(select constituency_id from constituency where upper(name)=upper('Karwar') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6093,3277,(select constituency_id from constituency where upper(name)=upper('Kumta') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6094,3277,(select constituency_id from constituency where upper(name)=upper('Bhatkal') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6095,3277,(select constituency_id from constituency where upper(name)=upper('SIRSI ') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6096,3277,(select constituency_id from constituency where upper(name)=upper('Yellapur') and election_scope_id=6 and district_id=65 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6097,3264,(select constituency_id from constituency where upper(name)=upper('Jagalur') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6098,3264,(select constituency_id from constituency where upper(name)=upper('HARAPANAHALLI ') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6099,3264,(select constituency_id from constituency where upper(name)=upper('Harihar') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6100,3264,(select constituency_id from constituency where upper(name)=upper('DAVANGERE NORTH') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6101,3264,(select constituency_id from constituency where upper(name)=upper('DAVANGERE SOUTH') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6102,3264,(select constituency_id from constituency where upper(name)=upper('Mayakonda') and election_scope_id=6 and district_id=69 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6103,3264,(select constituency_id from constituency where upper(name)=upper('Channagiri') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6104,3264,(select constituency_id from constituency where upper(name)=upper('Honnali') and election_scope_id=6 and district_id=64 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6105,3274,(select constituency_id from constituency where upper(name)=upper('Shimoga Rural') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6106,3274,(select constituency_id from constituency where upper(name)=upper('BHADRAVATHI') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6107,3274,(select constituency_id from constituency where upper(name)=upper('Shimoga') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6108,3274,(select constituency_id from constituency where upper(name)=upper('Tirthahalli') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6109,3274,(select constituency_id from constituency where upper(name)=upper('Shikaripur') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6110,3274,(select constituency_id from constituency where upper(name)=upper('Sorab') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6111,3274,(select constituency_id from constituency where upper(name)=upper('Sagar') and election_scope_id=6 and district_id=70 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6112,3274,(select constituency_id from constituency where upper(name)=upper('BAINDUR') and election_scope_id=6 and district_id=71 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6113,3276,(select constituency_id from constituency where upper(name)=upper('COONDAPUR') and election_scope_id=6 and district_id=71 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6114,3276,(select constituency_id from constituency where upper(name)=upper('Udupi') and election_scope_id=6 and district_id=71 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6115,3276,(select constituency_id from constituency where upper(name)=upper('KAUP') and election_scope_id=6 and district_id=71 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6116,3276,(select constituency_id from constituency where upper(name)=upper('Karkal') and election_scope_id=6 and district_id=71 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6117,3276,(select constituency_id from constituency where upper(name)=upper('SRINGERI') and election_scope_id=6 and district_id=72 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6118,3276,(select constituency_id from constituency where upper(name)=upper('MUDIGERE ') and election_scope_id=6 and district_id=72 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6119,3276,(select constituency_id from constituency where upper(name)=upper('chikmagalur') and election_scope_id=6 and district_id=56 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6120,3276,(select constituency_id from constituency where upper(name)=upper('Tarikere') and election_scope_id=6 and district_id=72 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6121,3267,(select constituency_id from constituency where upper(name)=upper('Kadur') and election_scope_id=6 and district_id=72 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6122,3267,(select constituency_id from constituency where upper(name)=upper('SRAVANABELAGOLA') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6123,3267,(select constituency_id from constituency where upper(name)=upper('Arsikere') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6124,3267,(select constituency_id from constituency where upper(name)=upper('BELUR ') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6125,3267,(select constituency_id from constituency where upper(name)=upper('HASSAN') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6126,3267,(select constituency_id from constituency where upper(name)=upper('Holenarasipur') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6127,3267,(select constituency_id from constituency where upper(name)=upper('Arkalgud') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6128,3267,(select constituency_id from constituency where upper(name)=upper('Sakleshpur') and election_scope_id=6 and district_id=78 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6129,3263,(select constituency_id from constituency where upper(name)=upper('Belthangady') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6130,3263,(select constituency_id from constituency where upper(name)=upper('Moodabidri') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6131,3263,(select constituency_id from constituency where upper(name)=upper('Mangalore City North') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6132,3263,(select constituency_id from constituency where upper(name)=upper('Mangalore City South') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6133,3263,(select constituency_id from constituency where upper(name)=upper('Mangalore') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6134,3263,(select constituency_id from constituency where upper(name)=upper('BANTWAL') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6135,3263,(select constituency_id from constituency where upper(name)=upper('Puttur') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6136,3263,(select constituency_id from constituency where upper(name)=upper('SULLIA ') and election_scope_id=6 and district_id=79 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6137,3262,(select constituency_id from constituency where upper(name)=upper('Molakalmuru') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6138,3262,(select constituency_id from constituency where upper(name)=upper('Challakere') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6139,3262,(select constituency_id from constituency where upper(name)=upper('Chitradurga') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6140,3262,(select constituency_id from constituency where upper(name)=upper('HIRIYUR ') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6141,3262,(select constituency_id from constituency where upper(name)=upper('Hosadurga') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6142,3262,(select constituency_id from constituency where upper(name)=upper('HOLALKERE') and election_scope_id=6 and district_id=68 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6143,3262,(select constituency_id from constituency where upper(name)=upper('Sira') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6144,3262,(select constituency_id from constituency where upper(name)=upper('PAVAGADA ') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6145,3275,(select constituency_id from constituency where upper(name)=upper('CHIKNAIKANAHALLI') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6146,3275,(select constituency_id from constituency where upper(name)=upper('Tiptur') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6147,3275,(select constituency_id from constituency where upper(name)=upper('TURUVEKERE') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6148,3275,(select constituency_id from constituency where upper(name)=upper('Tumkur city') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6149,3275,(select constituency_id from constituency where upper(name)=upper('Tumkur Rural') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6150,3275,(select constituency_id from constituency where upper(name)=upper('Koratagere') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6151,3275,(select constituency_id from constituency where upper(name)=upper('Gubbi') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6152,3275,(select constituency_id from constituency where upper(name)=upper('MADHUGIRI ') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6153,3271,(select constituency_id from constituency where upper(name)=upper('MALAVALLI ') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6154,3271,(select constituency_id from constituency where upper(name)=upper('Maddur') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6155,3271,(select constituency_id from constituency where upper(name)=upper('Melukote') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6156,3271,(select constituency_id from constituency where upper(name)=upper('Mandya') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6157,3271,(select constituency_id from constituency where upper(name)=upper('Srirangapatna') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6158,3271,(select constituency_id from constituency where upper(name)=upper('Nagamangala') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6159,3271,(select constituency_id from constituency where upper(name)=upper('Krishnarajpet') and election_scope_id=6 and district_id=77 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6160,3271,(select constituency_id from constituency where upper(name)=upper('KRISHNARAJANAGAR') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6161,3272,(select constituency_id from constituency where upper(name)=upper('Madikere') and election_scope_id=6 and district_id=80 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6162,3272,(select constituency_id from constituency where upper(name)=upper('VIRAJPET ') and election_scope_id=6 and district_id=80 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6163,3272,(select constituency_id from constituency where upper(name)=upper('PERIYAPATNA') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6164,3272,(select constituency_id from constituency where upper(name)=upper('Hunsur') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6165,3272,(select constituency_id from constituency where upper(name)=upper('CHAMUNDESWARI') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6166,3272,(select constituency_id from constituency where upper(name)=upper('KRISHNARAJ') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6167,3272,(select constituency_id from constituency where upper(name)=upper('Chamaraja') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6168,3272,(select constituency_id from constituency where upper(name)=upper('Narasimharaja') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6169,3259,(select constituency_id from constituency where upper(name)=upper('HEGGADADEVANAKOTE ') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6170,3259,(select constituency_id from constituency where upper(name)=upper('Nanjangud') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6171,3259,(select constituency_id from constituency where upper(name)=upper('Varuna') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6172,3259,(select constituency_id from constituency where upper(name)=upper('T NARASIPUR ') and election_scope_id=6 and district_id=81 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6173,3259,(select constituency_id from constituency where upper(name)=upper('Hanur') and election_scope_id=6 and district_id=82 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6174,3259,(select constituency_id from constituency where upper(name)=upper('KOLLEGAL ') and election_scope_id=6 and district_id=82 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6175,3259,(select constituency_id from constituency where upper(name)=upper('Chamarajanagar') and election_scope_id=6 and district_id=82 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6176,3259,(select constituency_id from constituency where upper(name)=upper('Gundlupet') and election_scope_id=6 and district_id=82 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6177,3253,(select constituency_id from constituency where upper(name)=upper('Kunigal') and election_scope_id=6 and district_id=73 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6178,3253,(select constituency_id from constituency where upper(name)=upper('Rajarajeshwarinagar') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6179,3253,(select constituency_id from constituency where upper(name)=upper('Bangalore South') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6180,3253,(select constituency_id from constituency where upper(name)=upper('ANEKAL ') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6181,3253,(select constituency_id from constituency where upper(name)=upper('Magadi') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6182,3253,(select constituency_id from constituency where upper(name)=upper('Ramanagaram') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6183,3253,(select constituency_id from constituency where upper(name)=upper('Kanakapura') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6184,3253,(select constituency_id from constituency where upper(name)=upper('Channapatna') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6185,3252,(select constituency_id from constituency where upper(name)=upper('K R Pura') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6186,3252,(select constituency_id from constituency where upper(name)=upper('Byatarayanapura') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6187,3252,(select constituency_id from constituency where upper(name)=upper('YESVANTHAPURA') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6188,3252,(select constituency_id from constituency where upper(name)=upper('Dasarahalli') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6189,3252,(select constituency_id from constituency where upper(name)=upper('Mahalakshmi Layout') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6190,3252,(select constituency_id from constituency where upper(name)=upper('MALLESWARAM') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6191,3252,(select constituency_id from constituency where upper(name)=upper('Hebbal') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6192,3252,(select constituency_id from constituency where upper(name)=upper('Pulakeshinagar') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6193,3251,(select constituency_id from constituency where upper(name)=upper('Sarvagnanagar') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6194,3251,(select constituency_id from constituency where upper(name)=upper('C V RAMAN NAGAR') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6195,3251,(select constituency_id from constituency where upper(name)=upper('Shivajinagar') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6196,3251,(select constituency_id from constituency where upper(name)=upper('SHANTHINAGAR ') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6197,3251,(select constituency_id from constituency where upper(name)=upper('GANDHINAGAR') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6198,3251,(select constituency_id from constituency where upper(name)=upper('RAJAJINAGAR') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6199,3251,(select constituency_id from constituency where upper(name)=upper('CHAMARAJAPET') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6200,3251,(select constituency_id from constituency where upper(name)=upper('Mahadevapura') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6201,3254,(select constituency_id from constituency where upper(name)=upper('GOWRIBIDANUR') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6202,3254,(select constituency_id from constituency where upper(name)=upper('Bagepalli') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6203,3254,(select constituency_id from constituency where upper(name)=upper('CHIKBALLAPUR ') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6204,3254,(select constituency_id from constituency where upper(name)=upper('YELAHANKA ') and election_scope_id=6 and district_id=75 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6205,3254,(select constituency_id from constituency where upper(name)=upper('Hosakote') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6206,3254,(select constituency_id from constituency where upper(name)=upper('DEVANAHALLI ') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6207,3254,(select constituency_id from constituency where upper(name)=upper('Doddaballapur') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6208,3254,(select constituency_id from constituency where upper(name)=upper('Nelamangala') and election_scope_id=6 and district_id=76 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6209,3269,(select constituency_id from constituency where upper(name)=upper('Sidlaghatta') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6210,3269,(select constituency_id from constituency where upper(name)=upper('Chintamani') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6211,3269,(select constituency_id from constituency where upper(name)=upper('SRINIVASAPUR') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6212,3269,(select constituency_id from constituency where upper(name)=upper('Mulbagal') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6213,3269,(select constituency_id from constituency where upper(name)=upper('KOLAR GOLD FIELDS ') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6214,3269,(select constituency_id from constituency where upper(name)=upper('Bangarapet') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6215,3269,(select constituency_id from constituency where upper(name)=upper('KOLAR') and election_scope_id=6 and district_id=74 and state_id=12) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6216,3269,(select constituency_id from constituency where upper(name)=upper('Malur') and election_scope_id=6 and district_id=74 and state_id=12) );


insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3300, (select constituency_id from constituency where name=upper('Arakkonam')  and election_scope_id=1 and state_id=24),2009 ,1);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3301, (select constituency_id from constituency where name=upper('Arani')  and election_scope_id=1 and state_id=24),2009 ,2);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3302, (select constituency_id from constituency where name=upper('Chengalpattu')  and election_scope_id=1 and state_id=24),2009 ,3);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3303, (select constituency_id from constituency where name=upper('Chidambaram')  and election_scope_id=1 and state_id=24),2009 ,4);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3304, (select constituency_id from constituency where name=upper('Coimbatore')  and election_scope_id=1 and state_id=24),2009 ,5);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3305, (select constituency_id from constituency where name=upper('Cuddalore')  and election_scope_id=1 and state_id=24),2009 ,6);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3306, (select constituency_id from constituency where name=upper('Dharmapuri')  and election_scope_id=1 and state_id=24),2009 ,7);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3307, (select constituency_id from constituency where name=upper('Dindigul')  and election_scope_id=1 and state_id=24),2009 ,8);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3308, (select constituency_id from constituency where name=upper('Gobichettipalayam')  and election_scope_id=1 and state_id=24),2009 ,9);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3309, (select constituency_id from constituency where name=upper('Karur')  and election_scope_id=1 and state_id=24),2009 ,10);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3310, (select constituency_id from constituency where name=upper('Krishnagiri')  and election_scope_id=1 and state_id=24),2009 ,11);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3311, (select constituency_id from constituency where name=upper('Madras Central')  and election_scope_id=1 and state_id=24),2009 ,12);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3312, (select constituency_id from constituency where name=upper('Madras North')  and election_scope_id=1 and state_id=24),2009 ,13);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3313, (select constituency_id from constituency where name=upper('Madras South')  and election_scope_id=1 and state_id=24),2009 ,14);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3314, (select constituency_id from constituency where name=upper('Madurai')  and election_scope_id=1 and state_id=24),2009 ,15);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3315, (select constituency_id from constituency where name=upper('Mayiladuthurai')  and election_scope_id=1 and state_id=24),2009 ,16);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3316, (select constituency_id from constituency where name=upper('Nagapattinam')  and election_scope_id=1 and state_id=24),2009 ,17);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3317, (select constituency_id from constituency where name=upper('Nagercoil')  and election_scope_id=1 and state_id=24),2009 ,18);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3318, (select constituency_id from constituency where name=upper('Nilgiris')  and election_scope_id=1 and state_id=24),2009 ,19);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3319, (select constituency_id from constituency where name=upper('Palani')  and election_scope_id=1 and state_id=24),2009 ,20);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3320, (select constituency_id from constituency where name=upper('Perambalur')  and election_scope_id=1 and state_id=24),2009 ,21);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3321, (select constituency_id from constituency where name=upper('Periyakulam')  and election_scope_id=1 and state_id=24),2009 ,22);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3322, (select constituency_id from constituency where name=upper('Pollachi')  and election_scope_id=1 and state_id=24),2009 ,23);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3323, (select constituency_id from constituency where name=upper('Pudukkottai')  and election_scope_id=1 and state_id=24),2009 ,24);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3324, (select constituency_id from constituency where name=upper('Ramanathapuram')  and election_scope_id=1 and state_id=24),2009 ,25);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3325, (select constituency_id from constituency where name=upper('Rasipuram')  and election_scope_id=1 and state_id=24),2009 ,26);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3326, (select constituency_id from constituency where name=upper('Salem')  and election_scope_id=1 and state_id=24),2009 ,27);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3327, (select constituency_id from constituency where name=upper('Sivaganga')  and election_scope_id=1 and state_id=24),2009 ,28);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3328, (select constituency_id from constituency where name=upper('Sivakasi')  and election_scope_id=1 and state_id=24),2009 ,29);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3329, (select constituency_id from constituency where name=upper('Sriperumbudur')  and election_scope_id=1 and state_id=24),2009 ,30);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3330, (select constituency_id from constituency where name=upper('Tenkasi')  and election_scope_id=1 and state_id=24),2009 ,31);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3331, (select constituency_id from constituency where name=upper('Thanjavur')  and election_scope_id=1 and state_id=24),2009 ,32);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3332, (select constituency_id from constituency where name=upper('Tindivanam')  and election_scope_id=1 and state_id=24),2009 ,33);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3333, (select constituency_id from constituency where name=upper('Tiruchendur')  and election_scope_id=1 and state_id=24),2009 ,34);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3334, (select constituency_id from constituency where name=upper('Tiruchengode')  and election_scope_id=1 and state_id=24),2009 ,35);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3335, (select constituency_id from constituency where name=upper('Tiruchirappalli')  and election_scope_id=1 and state_id=24),2009 ,36);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3336, (select constituency_id from constituency where name=upper('Tirunelveli')  and election_scope_id=1 and state_id=24),2009 ,37);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3337, (select constituency_id from constituency where name=upper('Tiruppattur')  and election_scope_id=1 and state_id=24),2009 ,38);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3338, (select constituency_id from constituency where name=upper('Vandavasi')  and election_scope_id=1 and state_id=24),2009 ,39);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3339, (select constituency_id from constituency where name=upper('Vellore')  and election_scope_id=1 and state_id=24),2009 ,40);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3340, (select constituency_id from constituency where name=upper('Chennai Central')  and election_scope_id=1 and state_id=24),2009 ,41);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3341, (select constituency_id from constituency where name=upper('Chennai North')  and election_scope_id=1 and state_id=24),2009 ,42);
insert into DELIMITATION_CONSTITUENCY ( delimitation_constituency_id,constituency_id, year, constituency_no) 
values(3342, (select constituency_id from constituency where name=upper('Chennai South')  and election_scope_id=1 and state_id=24),2009 ,43);



insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6250,3300,(select constituency_id from constituency where upper(name)=upper('Pallipet') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6251,3300,(select constituency_id from constituency where upper(name)=upper('ARCOT') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6252,3300,(select constituency_id from constituency where upper(name)=upper('ARKONAM') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6253,3300,(select constituency_id from constituency where upper(name)=upper('Cheyyar') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6254,3300,(select constituency_id from constituency where upper(name)=upper('RANIPET') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6255,3300,(select constituency_id from constituency where upper(name)=upper('SHOLINGHUR') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6256,3303,(select constituency_id from constituency where upper(name)=upper('Kurinjipadi') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6257,3303,(select constituency_id from constituency where upper(name)=upper('Bhuvanagiri') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6258,3303,(select constituency_id from constituency where upper(name)=upper('Kattumannarkoil') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6259,3303,(select constituency_id from constituency where upper(name)=upper('Chidambaram') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6260,3303,(select constituency_id from constituency where upper(name)=upper('Vridhachalam') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6261,3303,(select constituency_id from constituency where upper(name)=upper('Mangalore') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6262,3340,(select constituency_id from constituency where upper(name)=upper('Park Town') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6263,3340,(select constituency_id from constituency where upper(name)=upper('Purasawalkam') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6264,3340,(select constituency_id from constituency where upper(name)=upper('Egmore') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6265,3340,(select constituency_id from constituency where upper(name)=upper('Anna Nagar') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6266,3340,(select constituency_id from constituency where upper(name)=upper('Thousand Lights') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6267,3340,(select constituency_id from constituency where upper(name)=upper('Chepauk') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6268,3341,(select constituency_id from constituency where upper(name)=upper('Royapuram') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6269,3341,(select constituency_id from constituency where upper(name)=upper('Harbour') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6270,3341,(select constituency_id from constituency where upper(name)=upper('DRRADHAKRISHNAN NAGAR') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6271,3341,(select constituency_id from constituency where upper(name)=upper('PERAMBUR') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6272,3341,(select constituency_id from constituency where upper(name)=upper('Thiruvottiyur') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6273,3341,(select constituency_id from constituency where upper(name)=upper('Villivakkam') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6274,3342,(select constituency_id from constituency where upper(name)=upper('Triplicane') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6275,3342,(select constituency_id from constituency where upper(name)=upper('Mylapore') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6276,3342,(select constituency_id from constituency where upper(name)=upper('THEAGARAYA NAGAR') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6277,3342,(select constituency_id from constituency where upper(name)=upper('Saidapet') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6278,3342,(select constituency_id from constituency where upper(name)=upper('Alandur') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6279,3342,(select constituency_id from constituency where upper(name)=upper('Tambaram') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6280,3304,(select constituency_id from constituency where upper(name)=upper('PALLADAM') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6281,3304,(select constituency_id from constituency where upper(name)=upper('Tiruppur') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6282,3304,(select constituency_id from constituency where upper(name)=upper('Perur') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6283,3304,(select constituency_id from constituency where upper(name)=upper('Coimbatore West') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6284,3304,(select constituency_id from constituency where upper(name)=upper('Coimbatore East') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6285,3304,(select constituency_id from constituency where upper(name)=upper('SINGANALLUR') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6286,3305,(select constituency_id from constituency where upper(name)=upper('Ulundurpet') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6287,3305,(select constituency_id from constituency where upper(name)=upper('Nellikuppam') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6288,3305,(select constituency_id from constituency where upper(name)=upper('Cuddalore') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6289,3305,(select constituency_id from constituency where upper(name)=upper('Panruti') and election_scope_id=5 and district_id=41 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6290,3305,(select constituency_id from constituency where upper(name)=upper('Rishivandiam') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6291,3305,(select constituency_id from constituency where upper(name)=upper('Sankarapuram') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6292,3306,(select constituency_id from constituency where upper(name)=upper('DHARMAPURI') and election_scope_id=5 and district_id=28 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6293,3306,(select constituency_id from constituency where upper(name)=upper('HARUR') and election_scope_id=5 and district_id=28 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6294,3306,(select constituency_id from constituency where upper(name)=upper('Morappur') and election_scope_id=5 and district_id=28 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6295,3306,(select constituency_id from constituency where upper(name)=upper('PENNAGARAM') and election_scope_id=5 and district_id=28 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6296,3306,(select constituency_id from constituency where upper(name)=upper('Taramangalam') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6297,3306,(select constituency_id from constituency where upper(name)=upper('METTUR') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6298,3307,(select constituency_id from constituency where upper(name)=upper('Thirumangalam') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6299,3307,(select constituency_id from constituency where upper(name)=upper('Usilampatti') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6300,3307,(select constituency_id from constituency where upper(name)=upper('Nilakottai') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6301,3307,(select constituency_id from constituency where upper(name)=upper('Sholavandan') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6302,3307,(select constituency_id from constituency where upper(name)=upper('Dindigul') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6303,3307,(select constituency_id from constituency where upper(name)=upper('Athoor') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6304,3334,(select constituency_id from constituency where upper(name)=upper('Kapilamalai') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6305,3334,(select constituency_id from constituency where upper(name)=upper('Tiruchengode') and election_scope_id=5 and district_id=32 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6306,3334,(select constituency_id from constituency where upper(name)=upper('Sankari') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6307,3334,(select constituency_id from constituency where upper(name)=upper('Edapadi') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6308,3334,(select constituency_id from constituency where upper(name)=upper('Modakurichi') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6309,3334,(select constituency_id from constituency where upper(name)=upper('Erode') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6310,3329,(select constituency_id from constituency where upper(name)=upper('Gummidipundi') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6311,3329,(select constituency_id from constituency where upper(name)=upper('Ponneri') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6312,3329,(select constituency_id from constituency where upper(name)=upper('Sriperumbudur') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6313,3329,(select constituency_id from constituency where upper(name)=upper('Poonamallee') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6314,3329,(select constituency_id from constituency where upper(name)=upper('Tiruvallur') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6315,3329,(select constituency_id from constituency where upper(name)=upper('Tiruttani') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6316,3302,(select constituency_id from constituency where upper(name)=upper('Tirupporur') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6317,3302,(select constituency_id from constituency where upper(name)=upper('Chengalpattu') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6318,3302,(select constituency_id from constituency where upper(name)=upper('Maduranthakam') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6319,3302,(select constituency_id from constituency where upper(name)=upper('Acharapakkam') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6320,3302,(select constituency_id from constituency where upper(name)=upper('Uthiramerur') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6321,3302,(select constituency_id from constituency where upper(name)=upper('Kancheepuram') and election_scope_id=5 and district_id=26 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6322,3339,(select constituency_id from constituency where upper(name)=upper('Katpadi') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6323,3339,(select constituency_id from constituency where upper(name)=upper('Gudiyatham') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6324,3339,(select constituency_id from constituency where upper(name)=upper('Pernambut') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6325,3339,(select constituency_id from constituency where upper(name)=upper('Anaicut') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6326,3339,(select constituency_id from constituency where upper(name)=upper('Vellore') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6327,3339,(select constituency_id from constituency where upper(name)=upper('Arni') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6328,3337,(select constituency_id from constituency where upper(name)=upper('Vaniayambadi') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6329,3337,(select constituency_id from constituency where upper(name)=upper('Natrampalli') and election_scope_id=5 and district_id=27 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6330,3337,(select constituency_id from constituency where upper(name)=upper('Tiruppattur') and election_scope_id=5 and district_id=46 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6331,3337,(select constituency_id from constituency where upper(name)=upper('Chengam') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6332,3337,(select constituency_id from constituency where upper(name)=upper('Thandarambattu') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6333,3337,(select constituency_id from constituency where upper(name)=upper('Kalasapakkam') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6334,3338,(select constituency_id from constituency where upper(name)=upper('Tiruvannamalai') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6335,3338,(select constituency_id from constituency where upper(name)=upper('Polur') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6336,3338,(select constituency_id from constituency where upper(name)=upper('Vandavasi') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6337,3338,(select constituency_id from constituency where upper(name)=upper('Peranamallur') and election_scope_id=5 and district_id=29 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6338,3338,(select constituency_id from constituency where upper(name)=upper('Melmalayanur') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6339,3338,(select constituency_id from constituency where upper(name)=upper('Gingee') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6340,3332,(select constituency_id from constituency where upper(name)=upper('Tindivanam') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6341,3332,(select constituency_id from constituency where upper(name)=upper('Vanur') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6342,3332,(select constituency_id from constituency where upper(name)=upper('Kandamangalam') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6343,3332,(select constituency_id from constituency where upper(name)=upper('Villupuram') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6344,3332,(select constituency_id from constituency where upper(name)=upper('Mugaiyur') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6345,3332,(select constituency_id from constituency where upper(name)=upper('Thirunavalur') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6346,3310,(select constituency_id from constituency where upper(name)=upper('Hosur') and election_scope_id=5 and district_id=54 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6347,3310,(select constituency_id from constituency where upper(name)=upper('Thalli') and election_scope_id=5 and district_id=54 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6348,3310,(select constituency_id from constituency where upper(name)=upper('Kaveripattinam') and election_scope_id=5 and district_id=43 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6349,3310,(select constituency_id from constituency where upper(name)=upper('Krishnagiri') and election_scope_id=5 and district_id=54 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6350,3310,(select constituency_id from constituency where upper(name)=upper('Bargur') and election_scope_id=5 and district_id=54 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6351,3310,(select constituency_id from constituency where upper(name)=upper('Palacode') and election_scope_id=5 and district_id=28 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6352,3325,(select constituency_id from constituency where upper(name)=upper('Chinnasalem') and election_scope_id=5 and district_id=30 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6353,3325,(select constituency_id from constituency where upper(name)=upper('Attur') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6354,3325,(select constituency_id from constituency where upper(name)=upper('Talavasal') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6355,3325,(select constituency_id from constituency where upper(name)=upper('Rasipuram') and election_scope_id=5 and district_id=32 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6356,3325,(select constituency_id from constituency where upper(name)=upper('Sendamangalam') and election_scope_id=5 and district_id=32 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6357,3325,(select constituency_id from constituency where upper(name)=upper('Namakkal') and election_scope_id=5 and district_id=32 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6358,3326,(select constituency_id from constituency where upper(name)=upper('Omalur') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6359,3326,(select constituency_id from constituency where upper(name)=upper('Yercaud') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6360,3326,(select constituency_id from constituency where upper(name)=upper('Salem I') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6361,3326,(select constituency_id from constituency where upper(name)=upper('Salem II') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6362,3326,(select constituency_id from constituency where upper(name)=upper('Veerapandi') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6363,3326,(select constituency_id from constituency where upper(name)=upper('Panamarathupatty') and election_scope_id=5 and district_id=31 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6364,3318,(select constituency_id from constituency where upper(name)=upper('Mettupalayam') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6365,3318,(select constituency_id from constituency where upper(name)=upper('Avanashi') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6366,3318,(select constituency_id from constituency where upper(name)=upper('Thondamuthur') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6367,3318,(select constituency_id from constituency where upper(name)=upper('Coonoor') and election_scope_id=5 and district_id=34 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6368,3318,(select constituency_id from constituency where upper(name)=upper('Ootacamund') and election_scope_id=5 and district_id=34 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6369,3318,(select constituency_id from constituency where upper(name)=upper('Gudalur') and election_scope_id=5 and district_id=34 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6370,3308,(select constituency_id from constituency where upper(name)=upper('Gobichettipalayam') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6371,3308,(select constituency_id from constituency where upper(name)=upper('Perundurai') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6372,3308,(select constituency_id from constituency where upper(name)=upper('Bhavani') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6373,3308,(select constituency_id from constituency where upper(name)=upper('Andhiyur') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6374,3308,(select constituency_id from constituency where upper(name)=upper('Bhavanisagar') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6375,3308,(select constituency_id from constituency where upper(name)=upper('Sathyamangalam') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6376,3322,(select constituency_id from constituency where upper(name)=upper('Pollachi') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6377,3322,(select constituency_id from constituency where upper(name)=upper('Kinathukkadavu') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6378,3322,(select constituency_id from constituency where upper(name)=upper('Valparai') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6379,3322,(select constituency_id from constituency where upper(name)=upper('Udumalpet') and election_scope_id=5 and district_id=35 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6380,3322,(select constituency_id from constituency where upper(name)=upper('Dharapuram') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6381,3322,(select constituency_id from constituency where upper(name)=upper('Pongalur') and election_scope_id=5 and district_id=37 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6382,3319,(select constituency_id from constituency where upper(name)=upper('Palani') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6383,3319,(select constituency_id from constituency where upper(name)=upper('Vellakoil') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6384,3319,(select constituency_id from constituency where upper(name)=upper('Kangayam') and election_scope_id=5 and district_id=33 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6385,3319,(select constituency_id from constituency where upper(name)=upper('Oddanchatram') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6386,3319,(select constituency_id from constituency where upper(name)=upper('Natham') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6387,3319,(select constituency_id from constituency where upper(name)=upper('Vedasandur') and election_scope_id=5 and district_id=36 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6388,3314,(select constituency_id from constituency where upper(name)=upper('Tirupparankundram') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6389,3314,(select constituency_id from constituency where upper(name)=upper('Madurai West') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6390,3314,(select constituency_id from constituency where upper(name)=upper('Madurai Central') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6391,3314,(select constituency_id from constituency where upper(name)=upper('Madurai East') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6392,3314,(select constituency_id from constituency where upper(name)=upper('Samayanallur') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6393,3314,(select constituency_id from constituency where upper(name)=upper('Melur') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6394,3321,(select constituency_id from constituency where upper(name)=upper('Periyakulam') and election_scope_id=5 and district_id=48 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6395,3321,(select constituency_id from constituency where upper(name)=upper('Theni') and election_scope_id=5 and district_id=48 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6396,3321,(select constituency_id from constituency where upper(name)=upper('Bodinayakkanur') and election_scope_id=5 and district_id=48 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6397,3321,(select constituency_id from constituency where upper(name)=upper('Cumbum') and election_scope_id=5 and district_id=48 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6398,3321,(select constituency_id from constituency where upper(name)=upper('Andipatti') and election_scope_id=5 and district_id=48 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6399,3321,(select constituency_id from constituency where upper(name)=upper('Sedapatti') and election_scope_id=5 and district_id=47 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6400,3309,(select constituency_id from constituency where upper(name)=upper('Aravakurichi') and election_scope_id=5 and district_id=37 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6401,3309,(select constituency_id from constituency where upper(name)=upper('Karur') and election_scope_id=5 and district_id=37 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6402,3309,(select constituency_id from constituency where upper(name)=upper('Krishnarayapuram') and election_scope_id=5 and district_id=37 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6403,3309,(select constituency_id from constituency where upper(name)=upper('Marungapuri') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6404,3309,(select constituency_id from constituency where upper(name)=upper('Kulithalai') and election_scope_id=5 and district_id=37 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6405,3309,(select constituency_id from constituency where upper(name)=upper('Thottiam') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6406,3335,(select constituency_id from constituency where upper(name)=upper('Musiri') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6407,3335,(select constituency_id from constituency where upper(name)=upper('Lalgudi') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6408,3335,(select constituency_id from constituency where upper(name)=upper('Srirangam') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6409,3335,(select constituency_id from constituency where upper(name)=upper('TIRUCHIRAPALLI I') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6410,3335,(select constituency_id from constituency where upper(name)=upper('TIRUCHIRAPALLI II') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6411,3335,(select constituency_id from constituency where upper(name)=upper('Thiruverambur') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6412,3320,(select constituency_id from constituency where upper(name)=upper('Uppiliapuram') and election_scope_id=5 and district_id=38 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6413,3320,(select constituency_id from constituency where upper(name)=upper('Perambalur') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6414,3320,(select constituency_id from constituency where upper(name)=upper('Varahur') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6415,3320,(select constituency_id from constituency where upper(name)=upper('Ariyalur') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6416,3320,(select constituency_id from constituency where upper(name)=upper('Andimadam') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6417,3320,(select constituency_id from constituency where upper(name)=upper('Jayankondam') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6418,3315,(select constituency_id from constituency where upper(name)=upper('Sirkali') and election_scope_id=5 and district_id=42 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6419,3315,(select constituency_id from constituency where upper(name)=upper('Poompuhar') and election_scope_id=5 and district_id=42 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6420,3315,(select constituency_id from constituency where upper(name)=upper('Mayuram') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6421,3315,(select constituency_id from constituency where upper(name)=upper('Kuttalam') and election_scope_id=5 and district_id=43 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6422,3315,(select constituency_id from constituency where upper(name)=upper('Kumbakonam') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6423,3315,(select constituency_id from constituency where upper(name)=upper('Thiruvidamarudur') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6424,3316,(select constituency_id from constituency where upper(name)=upper('Nagapattinam') and election_scope_id=5 and district_id=42 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6425,3316,(select constituency_id from constituency where upper(name)=upper('Nannilam') and election_scope_id=5 and district_id=43 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6426,3316,(select constituency_id from constituency where upper(name)=upper('Tiruvarur') and election_scope_id=5 and district_id=24 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6427,3316,(select constituency_id from constituency where upper(name)=upper('Vedaranyam') and election_scope_id=5 and district_id=42 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6428,3316,(select constituency_id from constituency where upper(name)=upper('Tiruthuraipundi') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6429,3316,(select constituency_id from constituency where upper(name)=upper('Mannargudi') and election_scope_id=5 and district_id=43 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6430,3331,(select constituency_id from constituency where upper(name)=upper('Thanjavur') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6431,3331,(select constituency_id from constituency where upper(name)=upper('Orathanad') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6432,3331,(select constituency_id from constituency where upper(name)=upper('Thiruvonam') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6433,3331,(select constituency_id from constituency where upper(name)=upper('Tiruvaiyaru') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6434,3331,(select constituency_id from constituency where upper(name)=upper('Papanasam') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6435,3331,(select constituency_id from constituency where upper(name)=upper('Valangiman') and election_scope_id=5 and district_id=43 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6436,3323,(select constituency_id from constituency where upper(name)=upper('Pudukkottai') and election_scope_id=5 and district_id=45 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6437,3323,(select constituency_id from constituency where upper(name)=upper('Pattukkottai') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6438,3323,(select constituency_id from constituency where upper(name)=upper('Peravurani') and election_scope_id=5 and district_id=44 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6439,3323,(select constituency_id from constituency where upper(name)=upper('Kolathur') and election_scope_id=5 and district_id=25 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6440,3323,(select constituency_id from constituency where upper(name)=upper('Alangudi') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6441,3323,(select constituency_id from constituency where upper(name)=upper('Arantangi') and election_scope_id=5 and district_id=39 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6442,3327,(select constituency_id from constituency where upper(name)=upper('Thirumayam') and election_scope_id=5 and district_id=45 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6443,3327,(select constituency_id from constituency where upper(name)=upper('Tiruppattur') and election_scope_id=5 and district_id=46 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6444,3327,(select constituency_id from constituency where upper(name)=upper('Karaikudi') and election_scope_id=5 and district_id=46 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6445,3327,(select constituency_id from constituency where upper(name)=upper('Tiruvadanai') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6446,3327,(select constituency_id from constituency where upper(name)=upper('Sivaganga') and election_scope_id=5 and district_id=46 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6447,3327,(select constituency_id from constituency where upper(name)=upper('ILAYANGUDI') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6448,3324,(select constituency_id from constituency where upper(name)=upper('Ramanathapuram') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6449,3324,(select constituency_id from constituency where upper(name)=upper('Manamadurai') and election_scope_id=5 and district_id=46 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6450,3324,(select constituency_id from constituency where upper(name)=upper('Paramakudi') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6451,3324,(select constituency_id from constituency where upper(name)=upper('Kadaladi') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6452,3324,(select constituency_id from constituency where upper(name)=upper('Mudukulathur') and election_scope_id=5 and district_id=50 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6453,3324,(select constituency_id from constituency where upper(name)=upper('Aruppukottai') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6454,3328,(select constituency_id from constituency where upper(name)=upper('Sivakasi') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6455,3328,(select constituency_id from constituency where upper(name)=upper('Sattur') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6456,3328,(select constituency_id from constituency where upper(name)=upper('Virudhunagar') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6457,3328,(select constituency_id from constituency where upper(name)=upper('Srivilliputhur') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6458,3328,(select constituency_id from constituency where upper(name)=upper('Rajapalayam') and election_scope_id=5 and district_id=49 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6459,3328,(select constituency_id from constituency where upper(name)=upper('Koilpatti') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6460,3336,(select constituency_id from constituency where upper(name)=upper('Tirunelveli') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6461,3336,(select constituency_id from constituency where upper(name)=upper('Vilathikulam') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6462,3336,(select constituency_id from constituency where upper(name)=upper('Pottapidaram') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6463,3336,(select constituency_id from constituency where upper(name)=upper('Palayamcottai') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6464,3336,(select constituency_id from constituency where upper(name)=upper('Srivaikuntam') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6465,3336,(select constituency_id from constituency where upper(name)=upper('Tuticorin') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6466,3330,(select constituency_id from constituency where upper(name)=upper('Tenkasi') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6467,3330,(select constituency_id from constituency where upper(name)=upper('Sankaranayanarkoil') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6468,3330,(select constituency_id from constituency where upper(name)=upper('Vasudevanallur') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6469,3330,(select constituency_id from constituency where upper(name)=upper('Kadayanallur') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6470,3330,(select constituency_id from constituency where upper(name)=upper('Alangulam') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6471,3330,(select constituency_id from constituency where upper(name)=upper('Ambasamudram') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6472,3333,(select constituency_id from constituency where upper(name)=upper('Tiruchendur') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6473,3333,(select constituency_id from constituency where upper(name)=upper('Cheranmahadevi') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6474,3333,(select constituency_id from constituency where upper(name)=upper('Nanguneri') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6475,3333,(select constituency_id from constituency where upper(name)=upper('Radhapuram') and election_scope_id=5 and district_id=52 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6476,3333,(select constituency_id from constituency where upper(name)=upper('Sattangulam') and election_scope_id=5 and district_id=51 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6477,3333,(select constituency_id from constituency where upper(name)=upper('Kanniyakumari') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6478,3317,(select constituency_id from constituency where upper(name)=upper('Nagercoil') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6479,3317,(select constituency_id from constituency where upper(name)=upper('Colachel') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6480,3317,(select constituency_id from constituency where upper(name)=upper('Padmanabhapuram') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6481,3317,(select constituency_id from constituency where upper(name)=upper('Thiruvattar') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6482,3317,(select constituency_id from constituency where upper(name)=upper('Vilavancode') and election_scope_id=5 and district_id=53 and state_id=24) );
insert into DELIMITATION_CONSTITUENCY_ASSEMBLY_DETAILS (dca_id, delimitation_constituency_id, constituency_id)
values(6483,3317,(select constituency_id from constituency where upper(name)=upper('Killiyoor') and election_scope_id=5 and district_id=53 and state_id=24) );


#Opinion Poll Scripts
CREATE TABLE opinion_poll ( 
	opinion_poll_id  	  bigint(15) AUTO_INCREMENT NOT NULL,
	registration_id		  bigint(15) NULL,
	opinion_poll_start_date   date NULL,
	opinion_poll_end_date     date NULL,
	description		  varchar(300) NULL,
	title                     varchar(300) NULL,
	is_delete		  varchar(10)  Null,
	PRIMARY KEY(opinion_poll_id)
);


CREATE TABLE questions_repository ( 
	questions_repository_id    bigint(15) AUTO_INCREMENT NOT NULL,
	question		  varchar(300) NULL,
	PRIMARY KEY(questions_repository_id)
);

CREATE TABLE opinion_poll_questions ( 
	opinion_poll_questions_id   bigint(15) AUTO_INCREMENT NOT NULL,
	opinion_poll_id		    bigint(15) NULL,
	questions_repository_id     bigint(15) NULL,
	PRIMARY KEY(opinion_poll_questions_id)
);

CREATE TABLE opinion_poll_question_options ( 
	opinion_poll_question_options_id   bigint(15) AUTO_INCREMENT NOT NULL,
	questions_repository_id             bigint(15) NULL,
	question_option                     varchar(300) NULL,
	PRIMARY KEY(opinion_poll_question_options_id)
);

CREATE TABLE opinion_poll_result ( 
	opinion_poll_result_id		   bigint(15) AUTO_INCREMENT NOT NULL,
	opinion_poll_questions_id          bigint(15) NULL,
	opinion_poll_question_options_id   bigint(15) NULL,
	count				   bigint(15) Null,
	PRIMARY KEY(opinion_poll_result_id)
);



ALTER TABLE opinion_poll ADD CONSTRAINT fk_opinion_poll_registration
	FOREIGN KEY(registration_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


ALTER TABLE opinion_poll_questions ADD CONSTRAINT fk_opinion_poll_questions_opinion_poll
	FOREIGN KEY(opinion_poll_id)
	REFERENCES opinion_poll(opinion_poll_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


ALTER TABLE opinion_poll_questions ADD CONSTRAINT fk_opinion_poll_questions_questions_repository
	FOREIGN KEY(questions_repository_id)
	REFERENCES questions_repository(questions_repository_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


ALTER TABLE opinion_poll_question_options ADD CONSTRAINT fk_opinion_poll_question_options_questions_repository
	FOREIGN KEY(questions_repository_id)
	REFERENCES questions_repository(questions_repository_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


ALTER TABLE opinion_poll_result ADD CONSTRAINT fk_opinion_poll_result_opinion_poll_questions
	FOREIGN KEY(opinion_poll_questions_id)
	REFERENCES opinion_poll_questions(opinion_poll_questions_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE opinion_poll_result ADD CONSTRAINT fk_opinion_poll_result_opinion_poll_question_options
	FOREIGN KEY(opinion_poll_question_options_id)
	REFERENCES opinion_poll_question_options(opinion_poll_question_options_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE party_election_district_result_with_alliance ( 
	party_election_district_result_with_alliance_id	bigint(15) AUTO_INCREMENT NOT NULL,
	party_id                                       	bigint(15) NOT NULL,
	election_id                                    	bigint(15) NOT NULL,
	district_id                                    	bigint(15) NOT NULL,
	group_id                                       	bigint(15) NULL,
	total_consti_participated                      	bigint(15) NULL,
	total_seats_won                                	bigint(15) NULL,
	second_pos_won                                 	bigint(15) NULL,
	third_pos_won                                  	bigint(15) NULL,
	fourth_pos_won                                 	bigint(15) NULL,
	nth_pos_won                                    	bigint(15) NULL,
	complete_consti_valid_votes                    	bigint(15) NULL,
	total_valid_votes                              	bigint(15) NULL,
	total_votes_gained                             	bigint(15) NULL,
	votes_percentage                               	varchar(25) NULL,
	last_updated                                   	date NULL,
	complete_votes_percentage                      	varchar(25) NULL 
	)
GO
CREATE TABLE party_election_result_with_alliance ( 
	party_election_result_with_alliance_id	bigint(15) AUTO_INCREMENT NOT NULL,
	party_id                              	bigint(15) NOT NULL,
	election_id                           	bigint(15) NOT NULL,
	group_id                              	bigint(15) NULL,
	total_consti_participated             	bigint(15) NULL,
	total_seats_won                       	bigint(15) NULL,
	second_pos_won                        	bigint(15) NULL,
	third_pos_won                         	bigint(15) NULL,
	fourth_pos_won                        	bigint(15) NULL,
	nth_pos_won                           	bigint(15) NULL,
	complete_consti_valid_votes           	bigint(15) NULL,
	total_valid_votes                     	bigint(15) NULL,
	total_votes_gained                    	bigint(15) NULL,
	votes_percentage                      	varchar(25) NULL,
	last_updated                          	date NULL,
	complete_votes_percentage             	varchar(25) NULL 
	)
GO
CREATE TABLE party_election_state_result_with_alliance ( 
	party_election_state_result_with_alliance_id	bigint(15) AUTO_INCREMENT NOT NULL,
	party_id                                    	bigint(15) NOT NULL,
	election_id                                 	bigint(15) NOT NULL,
	group_id                                    	bigint(15) NOT NULL,
	state_id                                    	bigint(15) NOT NULL,
	total_consti_participated                   	bigint(15) NULL,
	total_seats_won                             	bigint(15) NULL,
	second_pos_won                              	bigint(15) NULL,
	third_pos_won                               	bigint(15) NULL,
	fourth_pos_won                              	bigint(15) NULL,
	nth_pos_won                                 	bigint(15) NULL,
	complete_consti_valid_votes                 	bigint(15) NULL,
	total_valid_votes                           	bigint(15) NULL,
	total_votes_gained                          	bigint(15) NULL,
	votes_percentage                            	varchar(25) NULL,
	last_updated                                	date NULL,
	complete_votes_percentage                   	varchar(25) NULL 
	)
GO

ALTER TABLE party_election_district_result_with_alliance
	ADD PRIMARY KEY (party_election_district_result_with_alliance_id)
GO
ALTER TABLE party_election_result_with_alliance
	ADD PRIMARY KEY (party_election_result_with_alliance_id)
GO
ALTER TABLE party_election_state_result_with_alliance
	ADD PRIMARY KEY (party_election_state_result_with_alliance_id)
GO
ALTER TABLE party_election_district_result_with_alliance
	ADD CONSTRAINT fk_party_party_election_district_result_with_alliance
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
GO
ALTER TABLE party_election_district_result_with_alliance
	ADD CONSTRAINT fk_group_party_election_district_result_with_alliance
	FOREIGN KEY(group_id)
	REFERENCES groups(group_id)
GO
ALTER TABLE party_election_district_result_with_alliance
	ADD CONSTRAINT fk_election_party_election_district_result_with_alliance
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
GO
ALTER TABLE party_election_district_result_with_alliance
	ADD CONSTRAINT fk_district_party_election_district_result_with_alliance
	FOREIGN KEY(district_id)
	REFERENCES district(district_id)
GO
ALTER TABLE party_election_result_with_alliance
	ADD CONSTRAINT fk_party_party_election_result_with_alliance
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
GO
ALTER TABLE party_election_result_with_alliance
	ADD CONSTRAINT fk_group_party_election_result_with_alliance
	FOREIGN KEY(group_id)
	REFERENCES groups(group_id)
GO
ALTER TABLE party_election_result_with_alliance
	ADD CONSTRAINT fk_election_party_election_result_with_alliance
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
GO
ALTER TABLE party_election_state_result_with_alliance
	ADD CONSTRAINT fk_state_party_election_state_result_with_alliance
	FOREIGN KEY(state_id)
	REFERENCES state(state_id)
GO
ALTER TABLE party_election_state_result_with_alliance
	ADD CONSTRAINT fk_party_party_election_state_result_with_alliance
	FOREIGN KEY(party_id)
	REFERENCES party(party_id)
GO
ALTER TABLE party_election_state_result_with_alliance
	ADD CONSTRAINT fk_group_party_election_state_result_with_alliance
	FOREIGN KEY(group_id)
	REFERENCES groups(group_id)
GO
ALTER TABLE party_election_state_result_with_alliance
	ADD CONSTRAINT fk_election_party_election_state_result_with_alliance
	FOREIGN KEY(election_id)
	REFERENCES election(election_id)
GO


# GHMC Statics Insertions.

insert into election_type values(7,'Greater Municipal Corp','State');
insert into election_scope values(7,'7',1 ,1);
INSERT INTO local_election_body( name, election_type_id, tehsil_id, district_id, no_of_wards)   
VALUES( 'GHMC', 7, 1110, 5,150);


CREATE TABLE ananymous_user (
	  user_id bigint(15) NOT NULL AUTO_INCREMENT,
	  name varchar(70) DEFAULT NULL,
	  last_name VARCHAR(70) NULL,
	  gender varchar(10) DEFAULT NULL,
	  username varchar(40) DEFAULT NULL,
	  password varchar(40) DEFAULT NULL,
	  dateofbirth date DEFAULT NULL,
	  email varchar(100) DEFAULT NULL,
	  phone varchar(25) DEFAULT NULL,
	  mobile varchar(25) DEFAULT NULL,
	  address varchar(250) DEFAULT NULL,
	  pincode varchar(15) DEFAULT NULL,
	  state_id bigint(15) NULL,
	  district_id bigint(15) NULL,	 
	  constituency_id bigint(15) NULL,
	  PRIMARY KEY (user_id)
); 

ALTER TABLE ananymous_user 
	ADD CONSTRAINT fk_state_ananymous_user
	FOREIGN KEY (state_id)
	REFERENCES state (state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE ananymous_user 
	ADD CONSTRAINT fk_district_ananymous_user
	FOREIGN KEY (district_id)
	REFERENCES district (district_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT;

ALTER TABLE ananymous_user
	ADD CONSTRAINT fk_constituency_ananymous_user
	FOREIGN KEY (constituency_id)
	REFERENCES constituency (constituency_id)
	ON DELETE RESTRICT
	ON UPDATE RESTRICT;

create table problem_impact_level (
           problem_impact_level_id bigint(15) AUTO_INCREMENT NOT NULL,
           problem_impact_level varchar(25) NOT NULL,
           description varchar(25) NULL, 
           PRIMARY KEY(problem_impact_level_id));

insert into problem_impact_level values(1,'STATE','state level');
insert into problem_impact_level values(2,'DISTRICT','district level');
insert into problem_impact_level values(3,'CONSTITUENCY','constituency level');
insert into problem_impact_level values(4,'LOCAL ELECTION BODY','local election body level');
insert into problem_impact_level values(5,'TEHSIL','tehsil level');
insert into problem_impact_level values(6,'HAMLET','hamlet level');
insert into problem_impact_level values(7,'WARD','ward level');

#influencing people updates
ALTER TABLE influencing_people ADD COLUMN `user_address_id` BIGINT NULL  AFTER `influencing_scope_value` ;

ALTER TABLE influencing_people ADD COLUMN `father_or_spouse_name` VARCHAR(25) NULL  AFTER `user_address_id` , ADD COLUMN `middle_name` VARCHAR(25) NULL  AFTER `user_address_id` ;


ALTER TABLE influencing_people ADD CONSTRAINT fk_influencing_people_user_address
	FOREIGN KEY(user_address_id)
	REFERENCES user_address(user_address_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_address ADD COLUMN `local_election_body` BIGINT NULL  AFTER `pincode` , ADD COLUMN `ward` BIGINT NULL  AFTER `local_election_body` ;


ALTER TABLE user_address ADD CONSTRAINT fk_user_address_local_election_body
	FOREIGN KEY(local_election_body)
	REFERENCES local_election_body(local_election_body_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


ALTER TABLE user_address ADD CONSTRAINT fk_user_address_constituency_ward
	FOREIGN KEY(ward)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

INSERT INTO influencing_people_position (`position_type`) VALUES ('EX-MLA');
INSERT INTO influencing_people_position (`position_type`) VALUES ('EX-MP');

CREATE  TABLE booth_local_body_ward (
	booth_local_body_ward_id BIGINT AUTO_INCREMENT NOT NULL,
	booth_id BIGINT NULL ,
	ward_id BIGINT NULL ,
	PRIMARY KEY (booth_local_body_ward_id) );

ALTER TABLE booth ADD column local_election_body_id BIGINT(15);

ALTER TABLE booth
	ADD CONSTRAINT fk_booth_local_election_body
	FOREIGN KEY(local_election_body_id)
	REFERENCES local_election_body(local_election_body_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE booth_local_body_ward
	ADD CONSTRAINT fk_booth_booth_local_body_ward
	FOREIGN KEY(booth_id)
	REFERENCES booth(booth_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE booth_local_body_ward
	ADD CONSTRAINT fk_ward_booth_local_body_ward
	FOREIGN KEY(ward_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;




CREATE TABLE  custom_message( 
	custom_message_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	
	subject		  	varchar(300) NULL,
	status		  	varchar(10) NULL,
	sent_date    	  	DATETIME   NOT NULL,	
	message_type_id	   	bigint(15) NULL,
	sender_id	   	bigint(15) NOT NULL,
	recepient_id 	  	bigint(15) NOT NULL,

	PRIMARY KEY(custom_message_id)
);

CREATE TABLE message_type( 
	message_type_id    bigint(15) AUTO_INCREMENT NOT NULL,
	message_type	   varchar(30) NULL,
	description	   varchar(100) NULL,

	PRIMARY KEY(message_type_id)
);

CREATE TABLE user_connectedto( 
	user_connectedto_id  bigint(15) AUTO_INCREMENT NOT NULL,
	user_source_id	     bigint(15) NOT NULL,
	user_target_id	     bigint(15) NOT NULL,

	PRIMARY KEY(user_connectedto_id)
);



ALTER TABLE user_connectedto ADD CONSTRAINT fk_user_source_id_anonymous_user
	FOREIGN KEY(user_source_id)
	REFERENCES ananymous_user(user_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE user_connectedto ADD CONSTRAINT fk_user_target_id_anonymous_user
	FOREIGN KEY(user_target_id)
	REFERENCES ananymous_user(user_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE custom_message ADD CONSTRAINT fk_custom_message_sender_id_anonymous_user
	FOREIGN KEY(sender_id)
	REFERENCES ananymous_user(user_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE custom_message ADD CONSTRAINT fk_custom_message_recepient_id_anonymous_user
	FOREIGN KEY(recepient_id)
	REFERENCES ananymous_user(user_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE custom_message ADD CONSTRAINT fk_custom_message_message_type
	FOREIGN KEY(message_type_id)
	REFERENCES message_type(message_type_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

INSERT INTO message_type VALUES(1,'CONNECTED','connected');
INSERT INTO message_type VALUES(2,'DISCONNECTED','dis connected');
INSERT INTO message_type VALUES(3,'PENDING','pending of request');
INSERT INTO message_type VALUES(4,'FRIEND REQUEST','friend request acceptance');
INSERT INTO message_type VALUES(5,'COMMENTS','posting comments');
INSERT INTO message_type VALUES(6,'SCRAP','posting a scrap');
INSERT INTO message_type VALUES(7,'BLOCK','block a user');
INSERT INTO message_type VALUES(8,'UNBLOCK','unblock a user');
INSERT INTO message_type VALUES ('9','NOT CONNECTED', 'not connected');


CREATE TABLE  static_local_group( 
	
   static_local_group_id  	bigint(15) AUTO_INCREMENT NOT NULL,
   group_type                   varchar(50) NOT NULL,
   description		  	varchar(300) NULL,
   updated_date    	  	date NULL,
   group_scope	   	        varchar(50) NULL,
   user_id 	  	        bigint(15) NULL,

   PRIMARY KEY(static_local_group_id)
);

ALTER TABLE static_local_group ADD CONSTRAINT fk_static_local_group_user
	FOREIGN KEY(user_id)
	REFERENCES registration(registration_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE  local_group_region( 
	
   local_group_region_id  	bigint(15) AUTO_INCREMENT NOT NULL,
   description		  	varchar(300) NULL,
   updated_date    	  	date NULL,
   group_region_scope	   	varchar(50) NULL,
   state_id 	  	        bigint(15) NULL,
   district_id 	  	        bigint(15) NULL,
   constituency_id 	  	bigint(15) NULL,
   tehsil_id 	  	        bigint(15) NULL,
   local_body_id 	  	bigint(15) NULL,
   township_id 	  	        bigint(15) NULL,
   ward_id 	  	        bigint(15) NULL,
   hamlet_id 	  	        bigint(15) NULL,

   PRIMARY KEY(local_group_region_id)
);

ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_state
	FOREIGN KEY(state_id)
	REFERENCES state(state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_district
	FOREIGN KEY(district_id)
	REFERENCES district(district_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_constituency
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_tehsil
	FOREIGN KEY(tehsil_id)
	REFERENCES tehsil(tehsil_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_local_body
	FOREIGN KEY(local_body_id)
	REFERENCES local_election_body(local_election_body_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_township
	FOREIGN KEY(township_id)
	REFERENCES township(township_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_ward
	FOREIGN KEY(ward_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
ALTER TABLE local_group_region ADD CONSTRAINT fk_local_group_region_hamlet
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE  static_user_designation(
   static_user_designation_id  	bigint(15) AUTO_INCREMENT NOT NULL,
   designation_type             varchar(50) NOT NULL,
   description		  	varchar(300) NULL,
   updated_date    	  	date NULL,
   static_local_group_id 	bigint(15) NULL,

   PRIMARY KEY(static_user_designation_id)
);

ALTER TABLE static_user_designation ADD CONSTRAINT fk_static_user_designation_local_group
	FOREIGN KEY(static_local_group_id)
	REFERENCES static_local_group(static_local_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE personal_user_group ADD COLUMN static_local_group_id bigint(15) NULL;

ALTER TABLE personal_user_group ADD CONSTRAINT fk_personal_user_group_local_group
	FOREIGN KEY(static_local_group_id)
	REFERENCES static_local_group(static_local_group_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE personal_user_group ADD COLUMN local_group_region_id bigint(15) NULL;

ALTER TABLE personal_user_group ADD CONSTRAINT fk_personal_user_group_region
	FOREIGN KEY(local_group_region_id)
	REFERENCES local_group_region(local_group_region_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE static_users ADD COLUMN static_user_designation_id bigint(15) NULL;

ALTER TABLE static_users ADD CONSTRAINT fk_static_user_designation
	FOREIGN KEY(static_user_designation_id)
	REFERENCES static_user_designation(static_user_designation_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

UPDATE `party` SET `party_flag`='DMK.png' WHERE `party_id`='284';
UPDATE `party` SET `party_flag`='AIADMK.png' WHERE `party_id`='50';
UPDATE `party` SET `party_flag`='PMK_Mango.png' WHERE `party_id`='641';
UPDATE `party` SET `party_flag`='MDMK_Top.png' WHERE `party_id`='537';

CREATE TABLE  local_election_body_ward ( 
	local_election_body_ward_id  	bigint(15) AUTO_INCREMENT NOT NULL,
	
	ward_name		        varchar(100) NULL,
	circle_name	  	        varchar(100) NULL,
	circle_zone			varchar(100) NULL,
	constituency_id	  	        bigint(15) NOT NULL,

	PRIMARY KEY(local_election_body_ward_id)
);


ALTER TABLE local_election_body_ward ADD CONSTRAINT fk_local_election_body_ward_constituency
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(1,'Kapra','Kapra','Circle-1',(select Constituency_id from constituency where name ='WARD-1' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(2,'Cherlapalli','Kapra','Circle-1',(select Constituency_id from constituency where name ='WARD-2' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(3,'Mallapur','Kapra','Circle-1',(select Constituency_id from constituency where name ='WARD-3' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(4,'Nacharam','Kapra','Circle-1',(select Constituency_id from constituency where name ='WARD-4' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(5,'Uppal ','Uppal ','Circle-2',(select Constituency_id from constituency where name ='WARD-5' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(6,'Habsiguda','Uppal ','Circle-2',(select Constituency_id from constituency where name ='WARD-6' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(7,'Ramanthapur','Uppal ','Circle-2',(select Constituency_id from constituency where name ='WARD-7' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(8,'Kothapet','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-8' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(9,'Mansoorabad','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-9' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(10,'Hayathnagar','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-10' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(11,'Vanasthalipuram','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-11' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(12,'Karmanghat','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-12' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(13,'Champapet','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-13' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(14,'Saroornagar','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-14' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(15,'Rama Krishna Puram','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-15' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(16,'Gaddiannaram','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-16' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(17,'P&T Colony','L.B.Nagar','Circle-3',(select Constituency_id from constituency where name ='WARD-17' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(18,'Moosarambagh','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-18' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(19,'Saidabad','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-19' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(20,'I.S.Sadan','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-20' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(21,'Santoshnagar','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-21' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(22,'Riyasat Nagar','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-22' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(23,'Kanchanbagh','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-23' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(24,'Barkas','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-24' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(25,'Chandrayan gutta','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-25' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(26,'Jangammet ','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-26' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(27,'Uppuguda ','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-27' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(28,'Lalithabagh','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-28' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(29,'Rain Bazar','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-29' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(30,'Kurmaguda','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-30' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(31,'Chavni','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-31' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(32,'Akberbagh','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-32' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(33,'Saleem Nagar','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-33' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(34,'Old Malakpet','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-34' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(35,'Azampura','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-35' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(36,'Dabeerpura','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-36' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(37,'Noorkhan Bazar','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-37' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(38,'Pathergatti','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-38' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(39,'Talabchanchalam','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-39' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(40,'Moghalpura','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-40' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(41,'Gowlipura','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-41' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(42,'Aliabad','SOUTH ZONE','Circle-4',(select Constituency_id from constituency where name ='WARD-42' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(43,'Falaknuma','','Circle-5',(select Constituency_id from constituency where name ='WARD-43' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(44,'Nawabsaheb kunta','','Circle-5',(select Constituency_id from constituency where name ='WARD-44' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(45,'Jahanuma ','','Circle-5',(select Constituency_id from constituency where name ='WARD-45' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(46,'Fathe Darwaza','','Circle-5',(select Constituency_id from constituency where name ='WARD-46' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(47,'Shali Banda','','Circle-5',(select Constituency_id from constituency where name ='WARD-47' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(48,'Hussain Alam','','Circle-5',(select Constituency_id from constituency where name ='WARD-48' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(49,'Ghansi Bazar','','Circle-5',(select Constituency_id from constituency where name ='WARD-49' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(50,'Begum Bazar','','Circle-5',(select Constituency_id from constituency where name ='WARD-50' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(51,'Gosha Mahal','','Circle-5',(select Constituency_id from constituency where name ='WARD-51' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(52,'Dhoolpet','','Circle-5',(select Constituency_id from constituency where name ='WARD-52' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(53,'Puranapul','','Circle-5',(select Constituency_id from constituency where name ='WARD-53' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(54,'Doodbowli','','Circle-5',(select Constituency_id from constituency where name ='WARD-54' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(55,'Ramnaspura','','Circle-5',(select Constituency_id from constituency where name ='WARD-55' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(56,'Kishanbagh','','Circle-5',(select Constituency_id from constituency where name ='WARD-56' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(57,'Shivarampally','','Circle-6',(select Constituency_id from constituency where name ='WARD-57' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(58,'Mylardevpally','','Circle-6',(select Constituency_id from constituency where name ='WARD-58' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(59,'Rajendra Nagar','','Circle-6',(select Constituency_id from constituency where name ='WARD-59' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(60,'Attapur','','Circle-6',(select Constituency_id from constituency where name ='WARD-60' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(61,'Karwan','','Circle-7',(select Constituency_id from constituency where name ='WARD-61' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(62,'Ziaguda','','Circle-7',(select Constituency_id from constituency where name ='WARD-62' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(63,'Dattathreyanagar','','Circle-7',(select Constituency_id from constituency where name ='WARD-63' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(64,'Manghalhat','','Circle-7',(select Constituency_id from constituency where name ='WARD-64' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(65,'Asifnagar','','Circle-7',(select Constituency_id from constituency where name ='WARD-65' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(66,'Muradnagar','','Circle-7',(select Constituency_id from constituency where name ='WARD-66' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(67,'Mehdipatnam','','Circle-7',(select Constituency_id from constituency where name ='WARD-67' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(68,'Gudimalkapur','','Circle-7',(select Constituency_id from constituency where name ='WARD-68' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(69,'Lunger House ','','Circle-7',(select Constituency_id from constituency where name ='WARD-69' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(70,'Tolichowki','','Circle-7',(select Constituency_id from constituency where name ='WARD-70' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(71,'Nanalnagar','','Circle-7',(select Constituency_id from constituency where name ='WARD-71' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(72,'Ahmed Nagar','','Circle-7',(select Constituency_id from constituency where name ='WARD-72' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(73,'Vijayanagar Colony','','Circle-7',(select Constituency_id from constituency where name ='WARD-73' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(74,'Chintalbasti','','Circle-7',(select Constituency_id from constituency where name ='WARD-74' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(75,'Mallepally','','Circle-7',(select Constituency_id from constituency where name ='WARD-75' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(76,'Red Hills','','Circle-7',(select Constituency_id from constituency where name ='WARD-76' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(77,'Jambagh','','Circle-8',(select Constituency_id from constituency where name ='WARD-77' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(78,'Gunfoundry','','Circle-8',(select Constituency_id from constituency where name ='WARD-78' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(79,'Sultan Bazar','','Circle-8',(select Constituency_id from constituency where name ='WARD-79' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(80,'Himayathnagar ','','Circle-9',(select Constituency_id from constituency where name ='WARD-80' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(81,'Barkatpura','','Circle-9',(select Constituency_id from constituency where name ='WARD-81' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(82,'Kachiguda','','Circle-9',(select Constituency_id from constituency where name ='WARD-82' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(83,'Golnaka','','Circle-9',(select Constituency_id from constituency where name ='WARD-83' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(84,'Amberpet','','Circle-9',(select Constituency_id from constituency where name ='WARD-84' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(85,'Bagh Amberpet','','Circle-9',(select Constituency_id from constituency where name ='WARD-85' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(86,'Vidyanagar','','Circle-9',(select Constituency_id from constituency where name ='WARD-86' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(87,'Nallakunta','','Circle-9',(select Constituency_id from constituency where name ='WARD-87' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(88,'Bagh Lingampally','','Circle-9',(select Constituency_id from constituency where name ='WARD-88' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(89,'Adikmet','','Circle-9',(select Constituency_id from constituency where name ='WARD-89' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(90,'Ramnagar','','Circle-9',(select Constituency_id from constituency where name ='WARD-90' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(91,'Musheerabad','','Circle-9',(select Constituency_id from constituency where name ='WARD-91' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(92,'Bholakpur','','Circle-9',(select Constituency_id from constituency where name ='WARD-92' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(93,'Gandhinagar','','Circle-9',(select Constituency_id from constituency where name ='WARD-93' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(94,'Kavadiguda','','Circle-9',(select Constituency_id from constituency where name ='WARD-94' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(95,'Domalguda','','Circle-9',(select Constituency_id from constituency where name ='WARD-95' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(96,'Khairtabad','','Circle-10',(select Constituency_id from constituency where name ='WARD-96' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(97,'Panjagutta','','Circle-10',(select Constituency_id from constituency where name ='WARD-97' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(98,'Somajiguda ','','Circle-10',(select Constituency_id from constituency where name ='WARD-98' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(99,'Ameerpet','','Circle-10',(select Constituency_id from constituency where name ='WARD-99' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(100,'Balkampet','','Circle-10',(select Constituency_id from constituency where name ='WARD-100' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(101,'Sanathnagar','','Circle-10',(select Constituency_id from constituency where name ='WARD-101' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(102,'Erragadda','','Circle-10',(select Constituency_id from constituency where name ='WARD-102' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(103,'Vengalrao Nagar','','Circle-10',(select Constituency_id from constituency where name ='WARD-103' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(104,'Srinagar Colony','','Circle-10',(select Constituency_id from constituency where name ='WARD-104' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(105,'Banjara Hills ','','Circle-10',(select Constituency_id from constituency where name ='WARD-105' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(106,'Yousufguda','','Circle-10',(select Constituency_id from constituency where name ='WARD-106' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(107,'Rahamath Nagar','','Circle-10',(select Constituency_id from constituency where name ='WARD-107' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(108,'Borabanda','','Circle-10',(select Constituency_id from constituency where name ='WARD-108' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(109,'Jubilee Hills ','','Circle-10',(select Constituency_id from constituency where name ='WARD-109' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(110,'Shaikpet','','Circle-10',(select Constituency_id from constituency where name ='WARD-110' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(111,'Gachibowli ','','Circle-11',(select Constituency_id from constituency where name ='WARD-111' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(112,'Serilingampally ','','Circle-11',(select Constituency_id from constituency where name ='WARD-112' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(113,'Hafeezpet','','Circle-12',(select Constituency_id from constituency where name ='WARD-113' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(114,'Chanda Nagar','','Circle-12',(select Constituency_id from constituency where name ='WARD-114' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(115,'Ramachandrapuram','','Circle-13',(select Constituency_id from constituency where name ='WARD-115' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(116,'Patancheruvu','','Circle-13',(select Constituency_id from constituency where name ='WARD-116' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(117,'KPHB Colony','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-117' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(118,'Moosapet','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-118' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(119,'Mothinagar','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-119' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(120,'Fathe Nagar','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-120' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(121,'Old Bowenpally','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-121' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(122,'Kukatpally','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-122' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(123,'Vivekananda Nagar Colony','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-123' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(124,'Hydernagar','Kukatpally','Circle-14',(select Constituency_id from constituency where name ='WARD-124' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(125,'Gajula Ramaram','Gajula Ramaram','Circle-15',(select Constituency_id from constituency where name ='WARD-125' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(126,'Jagadgiri gutta','Jagadgiri gutta','Circle-15',(select Constituency_id from constituency where name ='WARD-126' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(127,'Chintal','Chintal','Circle-15',(select Constituency_id from constituency where name ='WARD-127' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(128,'Shapur Nagar','Shapur Nagar','Circle-15',(select Constituency_id from constituency where name ='WARD-128' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(129,'Suraram Colony','Suraram Colony','Circle-15',(select Constituency_id from constituency where name ='WARD-129' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(130,'Jeedimetla','Jeedimetla','Circle-15',(select Constituency_id from constituency where name ='WARD-130' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(131,'Qutbullapur','Qutbullapur','Circle-15',(select Constituency_id from constituency where name ='WARD-131' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(132,'Alwal','Alwal','Circle-16',(select Constituency_id from constituency where name ='WARD-132' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(133,'Macha Bollaram','Alwal','Circle-16',(select Constituency_id from constituency where name ='WARD-133' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(134,'Yapral','Alwal','Circle-16',(select Constituency_id from constituency where name ='WARD-134' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(135,'Defence Colony',' Malkajgiri','Circle-17',(select Constituency_id from constituency where name ='WARD-135' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(136,'Moulali',' Malkajgiri','Circle-17',(select Constituency_id from constituency where name ='WARD-136' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(137,'Safilguda',' Malkajgiri','Circle-17',(select Constituency_id from constituency where name ='WARD-137' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(138,'Gautham Nagar ',' Malkajgiri','Circle-17',(select Constituency_id from constituency where name ='WARD-138' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(139,'Old Malkajgiri',' Malkajgiri','Circle-17',(select Constituency_id from constituency where name ='WARD-139' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(140,'Tarnaka','','Circle-18',(select Constituency_id from constituency where name ='WARD-140' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(141,'Mettuguda','','Circle-18',(select Constituency_id from constituency where name ='WARD-141' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(142,'Seethaphal mandi','','Circle-18',(select Constituency_id from constituency where name ='WARD-142' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(143,'Boudha Nagar','','Circle-18',(select Constituency_id from constituency where name ='WARD-143' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(144,'Chilkalguda','','Circle-18',(select Constituency_id from constituency where name ='WARD-144' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(145,'Padmarao Nagar','','Circle-18',(select Constituency_id from constituency where name ='WARD-145' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(146,'Bansilalpet','','Circle-18',(select Constituency_id from constituency where name ='WARD-146' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(147,'Ramgopal Pet','','Circle-18',(select Constituency_id from constituency where name ='WARD-147' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(148,'Begumpet','','Circle-18',(select Constituency_id from constituency where name ='WARD-148' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(149,'Maredpally ','','Circle-18',(select Constituency_id from constituency where name ='WARD-149' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));
INSERT INTO local_election_body_ward (local_election_body_ward_id,ward_name,circle_name,circle_zone,constituency_id) VALUES(150,'Addagutta','','Circle-18',(select Constituency_id from constituency where name ='WARD-150' and local_election_body_id in(select local_election_body_id from local_election_body where name='GHMC')));


insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1143,133,513,FALSE);


update delimitation_constituency_mandal_details set tehsil_id=184
where dcm_id=5914;

insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(5804,384,284,TRUE);

CREATE TABLE  booth_village(
    booth_village_id      bigint(15) AUTO_INCREMENT NOT NULL,
    st_code               varchar(300) NULL,
    ac_no        bigint(15) NULL,
    ac_name              varchar(300) NULL,
    part_no               bigint(15) NULL,
    section_no        bigint(15) NULL,
    startsl_no        bigint(15) NULL,   
    endsl_no           varchar(300) NULL,
    area_ide           varchar(300) NULL,
    localityide           varchar(300) NULL,

    PRIMARY KEY(booth_village_id));

ALTER TABLE `booth` CHANGE COLUMN `village_covered` `village_covered` VARCHAR(800) NULL DEFAULT NULL  ;

ALTER TABLE influencing_people ADD COLUMN registration_id BIGINT(15) NULL  AFTER influencing_people_id ;

ALTER TABLE influencing_people ADD CONSTRAINT fk_influencing_people_registration
    FOREIGN KEY(registration_id)
    REFERENCES registration(registration_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;


INSERT INTO GROUP_ENTITLEMENT_RELATION VALUES ('13', '14', '1');

update delimitation_constituency_mandal_details set tehsil_id=573
where dcm_id=5411;


update delimitation_constituency_mandal_details set tehsil_id=601
where dcm_id=5387;

update delimitation_constituency_mandal_details set tehsil_id=269
where dcm_id=5801;

CREATE TABLE user_relation (
    user_relation_id    bigint(20) AUTO_INCREMENT NOT NULL,
    relationship        varchar(25) NULL,
    PRIMARY KEY(user_relation_id)
);

CREATE TABLE cadre_family_member_info (
    cadre_family_member_info_id  bigint(20) AUTO_INCREMENT NOT NULL,
    cadre_id			 bigint(20) NULL,
    name			 varchar(50) NULL,
    date_of_birth		 date NULL,
    user_relation_id		 bigint(20) NULL,
    PRIMARY KEY(cadre_family_member_info_id)
);

ALTER TABLE cadre_family_member_info ADD CONSTRAINT fk_cadre_family_member_info_cadre
    FOREIGN KEY(cadre_id)
    REFERENCES cadre(cadre_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;

ALTER TABLE cadre_family_member_info ADD CONSTRAINT fk_cadre_family_member_info_user_relation
    FOREIGN KEY(user_relation_id)
    REFERENCES user_relation(user_relation_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;


INSERT INTO user_relation (relationship) VALUES ('Others');
INSERT INTO user_relation (relationship) VALUES ('Mother');
INSERT INTO user_relation (relationship) VALUES ('Father');
INSERT INTO user_relation (relationship) VALUES ('Spouse');
INSERT INTO user_relation (relationship) VALUES ('Son');
INSERT INTO user_relation (relationship) VALUES ('Daughter');
INSERT INTO user_relation (relationship) VALUES ('Brother');
INSERT INTO user_relation (relationship) VALUES ('Sister');

ALTER TABLE cadre ADD COLUMN  no_of_family_members  VARCHAR(5)  NULL  AFTER hamlet_id , ADD COLUMN  no_of_voters  VARCHAR(5) NULL  AFTER  no_of_family_members ;

## 1.Vizianagaram District

##Bobbili Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Bobbili') where constituency_id=(select constituency_id 
from constituency where name='Bobbili' and election_scope_id=2 and district_id=12) 
and part_no between 36 and 71 and year=2009;

## Salur Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Salur') where constituency_id=(select constituency_id 
from constituency where name='Salur' and election_scope_id=2 and district_id=12) 
and part_no between 30 and 63 and year=2009;

## Vizianagaram Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Vizianagaram ') where constituency_id=(select constituency_id 
from constituency where name='Vizianagaram ' and election_scope_id=2 and district_id=12) 
and part_no between 67 and 209 and year=2009;


## 2.Kadapa District

## Rayachoti Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Rayachoti ') where constituency_id=(select constituency_id 
from constituency where name='RAYACHOTY ' and election_scope_id=2 and district_id=20) 
and part_no between 74 and 111 and year=2009;

## Jammalamadugu Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='JAMMALAMADUGU ') where constituency_id=(select constituency_id 
from constituency where name='JAMMALAMADUGU ' and election_scope_id=2 and district_id=20) 
and part_no between 81 and 107 and year=2009;



## 3.Srikakulam District

## Amadalavalasa Municipality


update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Amadalavalasa ') where constituency_id=(select constituency_id 
from constituency where name='Amadalavalasa ' and election_scope_id=2 and district_id=11) 
and part_no between 31 and 47 and year=2009;

## Ichapuram Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Ichchapuram ') where constituency_id=(select constituency_id 
from constituency where name='ICHAPURAM' and election_scope_id=2 and district_id=11) 
and part_no between 27 and 45 and year=2009;

## Nalgonda District

## Bhongir Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='BHONGIR ') where constituency_id=(select constituency_id 
from constituency where name='Bhongir' and election_scope_id=2 and district_id=8) 
and part_no between 51 and 88 and year=2009;

## Suryapet Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Suryapet ') where constituency_id=(select constituency_id 
from constituency where name='Suryapet' and election_scope_id=2 and district_id=8) 
and part_no between 1 and 116 and year=2009;


## Khammam District

## Khammam Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Khammam ') where constituency_id=(select constituency_id 
from constituency where name='Khammam' and election_scope_id=2 and district_id=10) 
and part_no between 75 and 202 and year=2009;
 

## Yellandu Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Yellandu ') where constituency_id=(select constituency_id 
from constituency where name='Yellandu' and election_scope_id=2 and district_id=10) 
and part_no between 59 and 89 and year=2009;



## East Godavari District

## Tuni Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Tuni ') where constituency_id=(select constituency_id 
from constituency where name='Tuni' and election_scope_id=2 and district_id=14) 
and part_no between 98 and 133 and year=2009;

## Samarlakota Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='PEDDAPURAM ') where constituency_id=(select constituency_id 
from constituency where name='PEDDAPURAM' and election_scope_id=2 and district_id=14) 
and part_no between 121 and 161 and year=2009;

## Amalapuram Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Amalapuram ') where constituency_id=(select constituency_id 
from constituency where name='Amalapuram' and election_scope_id=2 and district_id=14) 
and part_no in(22,23,24,25,26,27,28,29,30,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50
,51,52,53,54,55,56,57,58,59,60,61,62,68,87,88,89,90,91) and year=2009;


## Warangal District

## Jangoan Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='JANGAON ') where constituency_id=(select constituency_id 
from constituency where name='JANGAON' and election_scope_id=2 and district_id=9) 
and part_no between 208 and 247 and year=2009;


## Krishna District

## GUDIVADA Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='GUDIVADA ') where constituency_id=(select constituency_id 
from constituency where name='GUDIVADA' and election_scope_id=2 and district_id=16) 
and part_no in(40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,57,58,59,60,61,62,63,64,
65,66,67,68,69,70,71,72,73,74,75,76,77,89,90,91,92,93,94,95,99,100,101,102,103,104,105
,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127
,128,129,130,131,132,133,134,135,136,137,138,139,140,142,143)and year=2009;


## JAggayyapet Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Jaggayyapeta ') where constituency_id=(select constituency_id 
from constituency where name='JAGGAYYAPET' and election_scope_id=2 and district_id=16) 
and part_no between 24 and 58 and year=2009;


## Mahabubnagar District

## Gadwal Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='GADWAL ') where constituency_id=(select constituency_id 
from constituency where name='GADWAL' and election_scope_id=2 and district_id=7) 
and part_no between 172 and 220 and year=2009;


## WANAPARTHY Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='WANAPARTHY ') where constituency_id=(select constituency_id 
from constituency where name='WANAPARTHY' and election_scope_id=2 and district_id=7) 
and part_no between 77 and 121 and year=2009;


## Medak District

## Sadashivpet Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Sadasivpet ') where constituency_id=(select constituency_id 
from constituency where name='SANGAREDDY' and election_scope_id=2 and district_id=4) 
and part_no between 126 and 148 and year=2009;

## Sangareddy Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='SANGAREDDY ') where constituency_id=(select constituency_id 
from constituency where name='SANGAREDDY' and election_scope_id=2 and district_id=4) 
and part_no between 60 and 111 and year=2009;


## Siddipet Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='SIDDIPET ') where constituency_id=(select constituency_id 
from constituency where name='SIDDIPET' and election_scope_id=2 and district_id=4) 
and part_no between 57 and 130 and year=2009;

## Zahirabad Municipality



## Karim Nagar District

## Jagtial Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='JAGTIAL') where constituency_id=(select constituency_id 
from constituency where name='JAGTIAL' and election_scope_id=2 and district_id=3) 
and part_no between 121 
and 199 and year=2009;

##Sircilla Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='SIRCILLA') where constituency_id=(select constituency_id 
from constituency where name='SIRCILLA' and election_scope_id=2 and district_id=3) 
and part_no between 74 and 130 and year=2009;

## Koratla Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='Koratla') where constituency_id=(select constituency_id 
from constituency where name='Koratla' and election_scope_id=2 and district_id=3) 
and part_no between 122 and 175 and year=2009;

## Ranga Reddy District

## Tandur Municipality

update booth set local_election_body_id=(select local_election_body_id from 
local_election_body where name='TANDUR') where constituency_id=(select constituency_id 
from constituency where name='TANDUR' and election_scope_id=2 and district_id=6) 
and part_no between 85 and 138 and year=2009;


## Vicarabad Municipality




## NELLORE District

### KAVALI Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='kavali') where constituency_id=
(select constituency_id from constituency where name='kavali' and election_scope_id=2 and 
district_id=19) and part_no between 25 and 93 and year=2009;

### VENKATAGIRI Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='venkatagiri') where constituency_id=
(select constituency_id from constituency where name='venkatagiri' and election_scope_id=2 and 
district_id=19) and part_no between 205 and 242 and year=2009;



###GUDURU Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Gudur') where constituency_id=
(select constituency_id from constituency where name='Gudur' and election_scope_id=2 and 
district_id=19) and part_no between 8 and 63 and year=2009;


##PRAKASAM District


### CHIRALA Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Chirala') where constituency_id=
(select constituency_id from constituency where name='Chirala' and election_scope_id=2 and 
district_id=18) and part_no between 39 and 114 and year=2009;

## GUNTUR District

### Bapatla Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Bapatla') where constituency_id=
(select constituency_id from constituency where name='Bapatla' and election_scope_id=2 and 
district_id=17) and part_no between 39 and 87 and year=2009;

### Macherla muncipality


update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='macherla') where constituency_id=
(select constituency_id from constituency where name='macherla' and election_scope_id=2 and 
district_id=17) and part_no between 38 and 74 and year=2009;

### REPALLE MUNICIPALITY

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Repalle') where constituency_id=
(select constituency_id from constituency where name='Repalle' and election_scope_id=2 and 
district_id=17) and part_no between 12 and 45 and year=2009;

### TENALI Constituency

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Tenali') where constituency_id=
(select constituency_id from constituency where name='Tenali' and election_scope_id=2 and 
district_id=17) and part_no between 41 and 166 and year=2009;

### Vinukonda Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Vinukonda') where constituency_id=
(select constituency_id from constituency where name='Vinukonda' and election_scope_id=2 and 
district_id=17) and part_no between 138 and 174 and year=2009;

## Sattenapalli Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='SATTENAPALLI') where constituency_id=
(select constituency_id from constituency where name='SATTENAPALLI' and election_scope_id=2 and 
district_id=17) and part_no between 20 and 48 and year=2009;


## Chilakaluripet Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='CHILAKALURIPET') where constituency_id=
(select constituency_id from constituency where name='CHILAKALURIPET' and election_scope_id=2 and 
district_id=17) and part_no between 120 and 186 and year=2009;




## CHITTOOR District

### Chittoor Municipality


update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='CHITTOOR') where constituency_id=
(select constituency_id from constituency where name='CHITTOOR' and election_scope_id=2 and 
district_id=23) and part_no in(18,27,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46
,48,49,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,71,72,73,74,75,76,77,78,79,89,93,
94,98,99,100,101,102,104,105,116,117,118,119,120,121,122,123,124,126,127,134,135,137,139,140
)and year=2009;


### Srikalahasti Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='srikalahasti') where constituency_id=
(select constituency_id from constituency where name='srikalahasti' and election_scope_id=2 and 
district_id=23) and part_no between 45 and 115 and year=2009;

### MADANPALLE Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='MADANAPALLE') where constituency_id=
(select constituency_id from constituency where name='MADANPALLE' and election_scope_id=2 and 
district_id=23) and part_no in(81,86,88,89,90,92,97,98,100,101,103,104,106,108,
110,111,113,115,118,120,122,124,128,130,138,140,142,151,156,158,163,171,175,177,179,181)and year=2009;


## West Godavari District

### Bhimavaram Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Bhimavaram') where constituency_id=
(select constituency_id from constituency where name='Bhimavaram' and election_scope_id=2 and 
district_id=15) and part_no between 32 and 127 and year=2009;


### Kovvur Municipality


update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Kovvur') where constituency_id=
(select constituency_id from constituency where name='Kovvur' and election_scope_id=2 and 
district_id=15) and part_no between 64 and 93 and year=2009;


### Tanuku Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='tanuku') where constituency_id=
(select constituency_id from constituency where name='tanuku' and election_scope_id=2 and 
district_id=15) and part_no between 37 and 84 and year=2009;

### Tadepalligudem Municipality


update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='tadepalligudem') where constituency_id=
(select constituency_id from constituency where name='tadepalligudem' and election_scope_id=2 and 
district_id=15) and part_no between 65 and 127 and year=2009;


##  NIZAMABAD District

### Bodhan Municipality


update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='bodhan') where constituency_id=
(select constituency_id from constituency where name='bodhan' and election_scope_id=2 and 
district_id=2) and part_no between 58 and 111 and year=2009;

###Kamareddy Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Kamareddy') where constituency_id=
(select constituency_id from constituency where name='Kamareddy' and election_scope_id=2 and 
district_id=2) and part_no between 156 and 214 and year=2009;



## ADILABAD District

### Adilabad Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='adilabad') where constituency_id=
(select constituency_id from constituency where name='adilabad' and election_scope_id=2 and 
district_id=1) and part_no between 121 and 191 and year=2009;


### Bellampalle Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='bellampalle') where constituency_id=
(select constituency_id from constituency where name='bellampalli' and election_scope_id=2 and 
district_id=1) and part_no between 37 and 89 and year=2009;

### Bhainsa Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Mudhole') where constituency_id=
(select constituency_id from constituency where name='Mudhole' and election_scope_id=2 and 
district_id=1) and part_no between 86 and 117 and year=2009;

## Kagazpur Municipality
update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Sirpur') where constituency_id=
(select constituency_id from constituency where name='Sirpur' and election_scope_id=2 and 
district_id=1) and part_no between 30 and 69 and year=2009;


### Vishakapatnam District

## Anakapalli Municipality

update booth set local_election_body_id=(select local_election_body_id 
from local_election_body where name='Anakapalli') where constituency_id=
(select constituency_id from constituency where name='Anakapalli' and election_scope_id=2 and 
district_id=13) and part_no in(125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,
140,141,142,143,144,145,146,147,154,155,156,157,162,163,164,165,166,167,168,169,170,171,172
,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,
196,197,198,199,200,201,202,203) and year=2009;

ALTER TABLE `user_address` ADD COLUMN `parliament_constituency_id` BIGINT NULL , ADD COLUMN `booth_id` BIGINT NULL  AFTER 
`parliament_constituency_id` ;

ALTER TABLE user_address ADD CONSTRAINT fk_user_address_constituency
    FOREIGN KEY(parliament_constituency_id)
    REFERENCES constituency(constituency_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;

ALTER TABLE user_address ADD CONSTRAINT fk_user_address_booth
    FOREIGN KEY(booth_id)
    REFERENCES booth(booth_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;


update delimitation_constituency_mandal_details set tehsil_id=184
where dcm_id=5914;

insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(5804,384,284,TRUE)

## 03-11-10

## Mahabubnagar

insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1142,84,277,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1143,84,278,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1144,84,279,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1145,84,286,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1146,84,287,TRUE);

## Medak 

update delimitation_constituency_mandal_details set tehsil_id=269
where dcm_id=5801;

insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, 
is_partial) values(6473,362,170,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, 
is_partial) values(6474,362,169,TRUE)
;
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, 
is_partial) values(6475,362,167,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, 
is_partial) values(6476,362,166,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id,
is_partial) values(6477,362,168,TRUE)
;

## 9-11-10

update delimitation_constituency_mandal_details set tehsil_id=573
where dcm_id=5411;


update delimitation_constituency_mandal_details set tehsil_id=601
where dcm_id=5387;

insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(6478,469,574,TRUE);


insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(6479,467,612,FALSE);

ALTER TABLE local_group_region ADD COLUMN group_region_scope_value varchar(25);

ALTER TABLE local_group_region ADD COLUMN street_name varchar(50);

ALTER TABLE local_group_region ADD COLUMN house_no varchar(25);

ALTER TABLE local_group_region ADD COLUMN pincode varchar(25);

ALTER TABLE local_group_region ADD COLUMN booth_id bigint(15);

ALTER TABLE local_group_region
	ADD CONSTRAINT fk_local_group_region_booth
	FOREIGN KEY(booth_id)
	REFERENCES booth(booth_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

update delimitation_constituency_mandal_details set tehsil_id=351 where dcm_id=723;

#parliament 2009 alliances

insert into groups values(6,'UPA','CONG,DMK,NCP,AITC,JMM,NC & Others');
insert into groups values(7,'NDA','BJP,JD(U),SHS,SAD,AGP,INLD & RLD');
insert into groups values(8,'Third Front','Left,JD(S),AIADMK,TDP,TRS & Others');
insert into groups values(9,'Fourth Front','SP,RJD & LJP');

insert into alliance_groups values(13,361,6);
insert into alliance_groups values(14,284,6);
insert into alliance_groups values(15,578,6);
insert into alliance_groups values(16,76,6);
insert into alliance_groups values(17,413,6);
insert into alliance_groups values(18,424,6);
insert into alliance_groups values(19,163,7);
insert into alliance_groups values(20,392,7);
insert into alliance_groups values(21,793,7);

insert into alliance_groups values(22,815,7);
insert into alliance_groups values(23,53,7);
insert into alliance_groups values(24,369,7);
insert into alliance_groups values(25,728,7);
insert into alliance_groups values(26,239,8);
insert into alliance_groups values(27,390,8);
insert into alliance_groups values(28,50,8);
insert into alliance_groups values(29,871,8);
insert into alliance_groups values(30,885,8);
insert into alliance_groups values(31,838,9);
insert into alliance_groups values(32,712,9);
insert into alliance_groups values(33,491,9);

insert into election_alliances values(11,2,6,null);
insert into election_alliances values(12,2,7,null);
insert into election_alliances values(13,2,8,null);

CREATE  TABLE IF NOT EXISTS profile_opts (
  profile_opts_id BIGINT NOT NULL AUTO_INCREMENT ,
  name VARCHAR(45) NULL ,
  description VARCHAR(45) NULL ,
  is_display VARCHAR(45) NULL ,
  PRIMARY KEY (profile_opts_id) );

CREATE  TABLE IF NOT EXISTS user_profile_opts (
  user_profile_opts_id BIGINT(15) NOT NULL AUTO_INCREMENT ,
  user_id BIGINT(15) NULL ,
  profile_opts_id BIGINT(15) NULL ,
  PRIMARY KEY (user_profile_opts_id) );

ALTER TABLE user_profile_opts 
  ADD CONSTRAINT ananymous_user_user_profile_opts
  FOREIGN KEY (user_id )
  REFERENCES ananymous_user (user_id )
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE user_profile_opts 
  ADD CONSTRAINT profile_opts_user_profile_opts
  FOREIGN KEY (profile_opts_id )
  REFERENCES profile_opts (profile_opts_id )
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

INSERT INTO profile_opts VALUES ('1', 'Get updates from Party Analyst', 'Get updates from Party Analyst', 'true');
INSERT INTO profile_opts VALUES ('2', 'Become A Volunteer Of Party Analyst', 'Become A Volunteer Of Party Analyst', 'true');

ALTER TABLE comment_category_candidate ADD COLUMN free_user_id BIGINT(15) NULL  AFTER nomination_id , ADD COLUMN paid_user_id BIGINT(15) NULL  AFTER nomination_id ;
ALTER TABLE comment_category_candidate ADD COLUMN severity FLOAT NULL  AFTER free_user_id ;
ALTER TABLE `comment_data` CHANGE COLUMN `is_approved` `is_approved` VARCHAR(20) NULL DEFAULT NULL  ;

CREATE TABLE census_year( 
	
   census_year_id  	bigint(15) AUTO_INCREMENT NOT NULL,
   year                 varchar(25) NOT NULL,
   country_id           bigint(15) NOT NULL,
   description		varchar(100) NULL,
   updated_date    	date NULL,
  
   PRIMARY KEY(census_year_id)
);

ALTER TABLE census_year ADD CONSTRAINT fk_census_year_country
	FOREIGN KEY(country_id)
	REFERENCES country(country_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE delimitation_year( 
	
   delimitation_year_id bigint(15) AUTO_INCREMENT NOT NULL,
   year                 varchar(25) NOT NULL,
   country_id           bigint(15) NOT NULL,
   description		varchar(100) NULL,
   updated_date    	date NULL,
  
   PRIMARY KEY(delimitation_year_id)
);

ALTER TABLE delimitation_year ADD CONSTRAINT fk_delimitation_year_country
	FOREIGN KEY(country_id)
	REFERENCES country(country_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_constituency ADD COLUMN census_year_id bigint(15) NULL;

ALTER TABLE delimitation_constituency ADD CONSTRAINT fk_delimitation_constituency_census_year
	FOREIGN KEY(census_year_id)
	REFERENCES census_year(census_year_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_constituency ADD COLUMN delimitation_year_id bigint(15) NULL;

ALTER TABLE delimitation_constituency ADD CONSTRAINT fk_delimitation_constituency_delimitation_year
	FOREIGN KEY(delimitation_year_id)
	REFERENCES delimitation_year(delimitation_year_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE delimitation_constituency_town( 
	
   delimitation_constituency_town_id  	bigint(15) AUTO_INCREMENT NOT NULL,
   delimitation_constituency_id         bigint(15) NOT NULL,
   township_id                          bigint(15) NOT NULL,
   is_partial                           bigint(15) NOT NULL,
   description		                varchar(100) NULL,
   updated_date    	                date NULL,
  
   PRIMARY KEY(delimitation_constituency_town_id)
);

ALTER TABLE delimitation_constituency_town ADD CONSTRAINT fk_delimitation_constituency_town_delimitation_constituency
	FOREIGN KEY(delimitation_constituency_id)
	REFERENCES delimitation_constituency(delimitation_constituency_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_constituency_town ADD CONSTRAINT fk_delimitation_constituency_town_township
	FOREIGN KEY(township_id)
	REFERENCES township(township_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE delimitation_village( 
	
   delimitation_village_id  	               bigint(15) AUTO_INCREMENT NOT NULL,
   delimitation_constituency_mandal_id         bigint(15) NOT NULL,
   township_id                                 bigint(15) NOT NULL,
   is_partial                                  bigint(15) NOT NULL,
   description		                       varchar(100) NULL,
   updated_date    	                       date NULL,
  
   PRIMARY KEY(delimitation_village_id)
);

ALTER TABLE delimitation_village ADD CONSTRAINT fk_delimitation_village_delimitation_constituency_mandal
	FOREIGN KEY(delimitation_constituency_mandal_id)
	REFERENCES delimitation_constituency_mandal_details(dcm_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_village ADD CONSTRAINT fk_delimitation_village_township
	FOREIGN KEY(township_id)
	REFERENCES township(township_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE delimitation_ward( 
	
   delimitation_ward_id  	             bigint(15) AUTO_INCREMENT NOT NULL,
   delimitation_constituency_town_id         bigint(15) NOT NULL,
   ward_id                                   bigint(15) NOT NULL,
   is_partial                                bigint(15) NOT NULL,
   description		                     varchar(100) NULL,
   updated_date    	                     date NULL,
  
   PRIMARY KEY(delimitation_ward_id)
);

ALTER TABLE delimitation_ward ADD CONSTRAINT fk_delimitation_ward_delimitation_constituency_town
	FOREIGN KEY(delimitation_constituency_town_id)
	REFERENCES delimitation_constituency_town(delimitation_constituency_town_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_ward ADD CONSTRAINT fk_delimitation_ward_ward
	FOREIGN KEY(ward_id)
	REFERENCES ward(ward_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

CREATE TABLE block( 
	
   block_id  	             bigint(15) AUTO_INCREMENT NOT NULL,
   block_name                varchar(100) NULL,
   ward_id                   bigint(15) NOT NULL,
   description		     varchar(100) NULL,
   updated_date    	     date NULL,
  
   PRIMARY KEY(block_id)
);

ALTER TABLE block ADD CONSTRAINT fk_block_ward
	FOREIGN KEY(ward_id)
	REFERENCES ward(ward_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;


CREATE TABLE delimitation_block( 
	
   delimitation_block_id       bigint(15) AUTO_INCREMENT NOT NULL,
   delimitation_ward_id        bigint(15) NOT NULL,
   block_id                    bigint(15) NOT NULL,
   is_partial                  bigint(15) NOT NULL,
   description		       varchar(100) NULL,
   updated_date    	       date NULL,
  
   PRIMARY KEY(delimitation_block_id)
);

ALTER TABLE delimitation_block ADD CONSTRAINT fk_delimitation_block_delimitation_ward
	FOREIGN KEY(delimitation_ward_id)
	REFERENCES delimitation_ward(delimitation_ward_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE delimitation_block ADD CONSTRAINT fk_delimitation_block_block
	FOREIGN KEY(block_id)
	REFERENCES block(block_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE `delimitation_constituency_mandal_details` CHANGE COLUMN `dcm_id` `dcm_id` BIGINT(15) NOT NULL AUTO_INCREMENT;

ALTER TABLE `delimitation_ward` CHANGE COLUMN `is_partial` `is_partial` BIGINT(15) NULL;

ALTER TABLE `delimitation_village` CHANGE COLUMN `is_partial` `is_partial` BIGINT(15) NULL;

ALTER TABLE `delimitation_block` CHANGE COLUMN `is_partial` `is_partial` BIGINT(15) NULL;

INSERT INTO user_country_access_info(user_country_access_info_id, user_group_id, country_id)
    VALUES(1, 2, 1);

ALTER TABLE cadre ADD COLUMN member_of_party_since VARCHAR(10);

ALTER TABLE cadre ADD COLUMN present_resp_in_party VARCHAR(100);

INSERT INTO user_relation VALUES(9,'Child');

INSERT INTO social_category VALUES(6,'N/A');

update delimitation_constituency_mandal_details set tehsil_id=1006 where dcm_id=5086;

ALTER TABLE `registration` ADD COLUMN `parent_user_id` BIGINT(15) NULL  AFTER `user_type` ;

ALTER TABLE `problem` CHANGE COLUMN `problem_title` `problem_title` VARCHAR(100) NULL DEFAULT NULL  ;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=540;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=542;

INSERT INTO `entitlement` (`entitlement_id`, `entitlement_type`) VALUES ('18', 'CADRE_VIEW');
INSERT INTO `entitlement` (`entitlement_id`, `entitlement_type`) VALUES ('19', 'CADRE_CREATE');
INSERT INTO `entitlement` (`entitlement_id`, `entitlement_type`) VALUES ('20', 'CADRE_UPDATE');
INSERT INTO `entitlement` (`entitlement_id`, `entitlement_type`) VALUES ('21', 'CADRE_DELETE'); 

ALTER TABLE candidate_result ADD COLUMN margin_votes DOUBLE NULL  AFTER votes_percentage , ADD COLUMN margin_votes_percentage VARCHAR(15) NULL  AFTER margin_votes ;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=487;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=522;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=525;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=497;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=496;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=515;

delete from delimitation_constituency_mandal_details where dcm_id in(521,526,528);

UPDATE booth SET tehsil_id=304 WHERE booth_id=84409;
delete from delimitation_constituency_mandal_details where dcm_id in(513,514,517,518);

UPDATE booth SET tehsil_id=337 WHERE booth_id=81785;

UPDATE booth` SET `location`='Gujja', `tehsil_id`='326' WHERE `booth_id`='83194';

CREATE TABLE `constituency_census_details` (
  `constituency_census_id` bigint(15) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(15) DEFAULT NULL,
  `constituency_id` bigint(15) NOT NULL,
  `level` varchar(20) NOT NULL,
  `year` int(5) NOT NULL,
  `tru` varchar(10) NOT NULL,
  `No_HH` bigint(20) DEFAULT NULL,
  `TOT_P` bigint(20) DEFAULT NULL,
  `TOT_M` bigint(20) DEFAULT NULL,
  `TOT_F` bigint(20) DEFAULT NULL,
  `P_06` bigint(20) DEFAULT NULL,
  `M_06` bigint(20) DEFAULT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `P_SC` bigint(20) DEFAULT NULL,
  `M_SC` bigint(20) DEFAULT NULL,
  `F_SC` bigint(20) DEFAULT NULL,
  `P_ST` bigint(20) DEFAULT NULL,
  `M_ST` bigint(20) DEFAULT NULL,
  `F_ST` bigint(20) DEFAULT NULL,
  `P_LIT` bigint(20) DEFAULT NULL,
  `M_LIT` bigint(20) DEFAULT NULL,
  `F_LIT` bigint(20) DEFAULT NULL,
  `P_ILL` bigint(20) DEFAULT NULL,
  `M_ILL` bigint(20) DEFAULT NULL,
  `F_ILL` bigint(20) DEFAULT NULL,
  `TOT_WORK_P` bigint(20) DEFAULT NULL,
  `TOT_WORK_M` bigint(20) DEFAULT NULL,
  `TOT_WORK_F` bigint(20) DEFAULT NULL,
  `MAINWORK_P` bigint(20) DEFAULT NULL,
  `MAINWORK_M` bigint(20) DEFAULT NULL,
  `MAINWORK_F` bigint(20) DEFAULT NULL,
  `MAIN_CL_P` bigint(20) DEFAULT NULL,
  `MAIN_CL_M` bigint(20) DEFAULT NULL,
  `MAIN_CL_F` bigint(20) DEFAULT NULL,
  `MAIN_AL_P` bigint(20) DEFAULT NULL,
  `MAIN_AL_M` bigint(20) DEFAULT NULL,
  `MAIN_AL_F` bigint(20) DEFAULT NULL,
  `MAIN_HH_P` bigint(20) DEFAULT NULL,
  `MAIN_HH_M` bigint(20) DEFAULT NULL,
  `MAIN_HH_F` bigint(20) DEFAULT NULL,
  `MAIN_OT_P` bigint(20) DEFAULT NULL,
  `MAIN_OT_M` bigint(20) DEFAULT NULL,
  `MAIN_OT_F` bigint(20) DEFAULT NULL,
  `MARGWORK_P` bigint(20) DEFAULT NULL,
  `MARGWORK_M` bigint(20) DEFAULT NULL,
  `MARGWORK_F` bigint(20) DEFAULT NULL,
  `MARG_CL_P` bigint(20) DEFAULT NULL,
  `MARG_CL_M` bigint(20) DEFAULT NULL,
  `MARG_CL_F` bigint(20) DEFAULT NULL,
  `MARG_AL_P` bigint(20) DEFAULT NULL,
  `MARG_AL_M` bigint(20) DEFAULT NULL,
  `MARG_AL_F` bigint(20) DEFAULT NULL,
  `MARG_HH_P` bigint(20) DEFAULT NULL,
  `MARG_HH_M` bigint(20) DEFAULT NULL,
  `MARG_HH_F` bigint(20) DEFAULT NULL,
  `MARG_OT_P` bigint(20) DEFAULT NULL,
  `MARG_OT_M` bigint(20) DEFAULT NULL,
  `MARG_OT_F` bigint(20) DEFAULT NULL,
  `NON_WORK_P` bigint(20) DEFAULT NULL,
  `NON_WORK_M` bigint(20) DEFAULT NULL,
  `NON_WORK_F` bigint(20) DEFAULT NULL,
  `SEXRATIO` double(10,0) DEFAULT NULL,
  `SEXRATIO_SC` double(10,0) DEFAULT NULL,
  `SEXRATIO_ST` double(10,0) DEFAULT NULL,
  `HH_SIZE` double(10,0) DEFAULT NULL,
  `SC_PER` double(10,0) DEFAULT NULL,
  `ST_PER` double(10,0) DEFAULT NULL,
  `SEXRATIO_06` double(10,0) DEFAULT NULL,
  `M_LIT_RATE` double(10,0) DEFAULT NULL,
  `F_LIT_RATE` double(10,0) DEFAULT NULL,
  `GENDER_GAP` double(10,0) DEFAULT NULL,
  `P_LIT_PER` double(10,0) DEFAULT NULL,
  `M_LIT_PER` double(10,0) DEFAULT NULL,
  `F_LIT_PER` double(10,0) DEFAULT NULL,
  `TOT_P_PER` double(10,0) DEFAULT NULL,
  `TOT_WOR_PER_P` double(10,0) DEFAULT NULL,
  `TOT_WOR_PER_M` double(10,0) DEFAULT NULL,
  `TOT_WOR_PER_F` double(10,0) DEFAULT NULL,
  `TOT_MAIN_PER_P` double(10,0) DEFAULT NULL,
  `TOT_MAIN_PER_M` double(10,0) DEFAULT NULL,
  `TOT_MAIN_PER_F` double(10,0) DEFAULT NULL,
  `TOT_MARG_PER_P` double(10,0) DEFAULT NULL,
  `TOT_MARG_PER_M` double(10,0) DEFAULT NULL,
  `TOT_MARG_PER_F` double(10,0) DEFAULT NULL,
  `NON_WORK_PER_P` double(10,0) DEFAULT NULL,
  `NON_WORK_PER_M` double(10,0) DEFAULT NULL,
  `NON_WORK_PER_F` double(10,0) DEFAULT NULL,
  `CL_PER_P` double(10,0) DEFAULT NULL,
  `CL_PER_M` double(10,0) DEFAULT NULL,
  `CL_PER_F` double(10,0) DEFAULT NULL,
  `AL_PER_P` double(10,0) DEFAULT NULL,
  `AL_PER_M` double(10,0) DEFAULT NULL,
  `AL_PER_F` double(10,0) DEFAULT NULL,
  `HH_PER_P` double(10,0) DEFAULT NULL,
  `HH_PER_M` double(10,0) DEFAULT NULL,
  `HH_PER_F` double(10,0) DEFAULT NULL,
  `OW_PER_P` double(10,0) DEFAULT NULL,
  `OW_PER_M` double(10,0) DEFAULT NULL,
  `OW_PER_F` double(10,0) DEFAULT NULL,
  `CL_MAIN_MARG_P` bigint(20) DEFAULT NULL,
  `CL_MAIN_MARG_M` bigint(20) DEFAULT NULL,
  `CL_MAIN_MARG_F` bigint(20) DEFAULT NULL,
  `AL_MAIN_MARG_P` bigint(20) DEFAULT NULL,
  `AL_MAIN_MARG_M` bigint(20) DEFAULT NULL,
  `AL_MAIN_MARG_F` bigint(20) DEFAULT NULL,
  `HH_MAIN_MARG_P` bigint(20) DEFAULT NULL,
  `HH_MAIN_MARG_M` bigint(20) DEFAULT NULL,
  `HH_MAIN_MARG_F` bigint(20) DEFAULT NULL,
  `OW_MAIN_MARG_P` bigint(20) DEFAULT NULL,
  `OW_MAIN_MARG_M` bigint(20) DEFAULT NULL,
  `OW_MAIN_MARG_F` bigint(20) DEFAULT NULL,
     `WPR`         double(10,0) DEFAULT NULL,
 
   PRIMARY KEY(constituency_census_id)
 
) ;

ALTER TABLE constituency_census_details ADD CONSTRAINT fk_constituency_census_details_state
    FOREIGN KEY(state_id)
    REFERENCES state(state_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;

ALTER TABLE constituency_census_details ADD CONSTRAINT fk_constituency_census_details_constituency
    FOREIGN KEY(constituency_id)
    REFERENCES constituency(constituency_id)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT ;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=1047;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6483;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6484;

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=1044;

update delimitation_constituency_mandal_details set tehsil_id=1096 where dcm_id=1031;

UPDATE `constituency` SET `deform_date`='2009-01-03' WHERE `constituency_id`='259';

update delimitation_constituency_mandal_details set tehsil_id=416 where dcm_id=233;

ALTER TABLE approval_details ADD is_admin_approved  VARCHAR(10);

UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=723;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6490;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6491;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6492;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6493;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6494;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=695;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=873;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=580;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=567;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6487;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=485;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6488;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=356;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6499;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6498;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=402;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6500;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6501;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6502;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6503;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6504;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=405;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=418;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=422;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=429;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=430;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=439;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=423;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=424;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=425;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=426;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=369;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=370;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=371;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=373;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6496;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=491;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=495;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=341;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6482;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6541;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6542;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6543;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6544;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6545;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6546;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6529;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=769;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=789;
UPDATE delimitation_constituency_town SET is_partial=1 WHERE delimitation_constituency_town_id=24;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=795;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=766;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6530;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=1138;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6532;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6533;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6534;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6536;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6535;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6537;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6538;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6539;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6540;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=277;
UPDATE delimitation_constituency_mandal_details SET is_partial=1 WHERE dcm_id=6531;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=251;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=265;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=253;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=257;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=256;
UPDATE delimitation_constituency_mandal_details SET is_partial=0 WHERE dcm_id=1114;

update constituency set deform_date=null where constituency_id=258;

UPDATE delimitation_constituency_mandal_details SET delimitation_constituency_id=211, is_partial=0 WHERE dcm_id=65;

UPDATE delimitation_constituency_mandal_details SET delimitation_constituency_id=211 WHERE dcm_id=66;

UPDATE delimitation_constituency_mandal_details SET delimitation_constituency_id=211 WHERE dcm_id=67;

UPDATE delimitation_constituency_mandal_details SET delimitation_constituency_id=211 WHERE dcm_id=68;

UPDATE delimitation_constituency_mandal_details SET delimitation_constituency_id=211 WHERE dcm_id=69;                                                                 
                                                                     
                                             
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1161,70,197,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1162,70,198,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1163,70,200,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1164,70,201,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1165,69,199,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1166,71,196,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1167,67,195,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1168,72,202,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1169,72,200,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1170,59,203,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1171,59,224,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1172,59,225,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1173,59,226,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1174,68,204,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1175,61,222,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1176,61,223,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1177,61,204,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1178,63,205,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1179,63,221,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1180,64,194,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1181,64,195,FALSE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1182,60,192,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1183,60,193,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1184,60,206,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1185,60,207,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1186,60,220,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1187,62,215,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1188,62,216,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1189,62,217,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1190,62,218,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1191,62,219,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1192,66,190,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1193,66,191,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1194,66,208,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1195,66,209,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1196,66,210,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1197,65,211,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1198,65,212,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1199,65,213,TRUE);
insert into DELIMITATION_CONSTITUENCY_MANDAL_DETAILS (dcm_id, delimitation_constituency_id, tehsil_id, is_partial)
values(1200,65,214,TRUE);


update delimitation_constituency_mandal_details set tehsil_id=367 where dcm_id=752;

update delimitation_constituency_mandal_details set tehsil_id=365 where dcm_id=755;

CREATE  TABLE `region_scopes` (  `region_scopes_id` BIGINT NOT NULL AUTO_INCREMENT ,  `scope` VARCHAR(45) NULL ,  `description` VARCHAR(200) NULL ,  PRIMARY KEY (`region_scopes_id`) );

INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('1', 'COUNTRY');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('2', 'STATE');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('3', 'DISTRICT');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('4', 'CONSTITUENCY');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('5', 'MANDAL');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('6', 'VILLAGE');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('7', 'MUNICIPAL-CORP-GMC');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('8', 'WARD');
INSERT INTO `region_scopes` (`region_scopes_id`, `scope`) VALUES ('9', 'BOOTH');

CREATE  TABLE module_details (
  `module_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `module` VARCHAR(45) NULL ,
  `description` VARCHAR(100) NULL ,
  PRIMARY KEY (`module_id`) );

  CREATE  TABLE module_region_scopes (
  `module_region_scopes_id` BIGINT NOT NULL AUTO_INCREMENT ,
  `state_id` BIGINT NULL ,
  `module_id` BIGINT NULL ,
  `region_scope_id` BIGINT NULL ,
  PRIMARY KEY (`module_region_scopes_id`) );

 ALTER TABLE module_region_scopes
	ADD CONSTRAINT fk_state_module_region_scopes
	FOREIGN KEY(state_id)
	REFERENCES state(state_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;
 	

 ALTER TABLE module_region_scopes
	ADD CONSTRAINT fk_module_details_module_region_scopes
	FOREIGN KEY(module_id)
	REFERENCES module_details(module_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

ALTER TABLE module_region_scopes
	ADD CONSTRAINT fk_region_scopes_module_region_scopes
	FOREIGN KEY(region_scope_id)
	REFERENCES region_scopes(region_scopes_id)
	ON DELETE RESTRICT 
	ON UPDATE RESTRICT ;

INSERT INTO `module_details` (`module_id`, `module`, `description`) VALUES ('1', 'ADD_NEW_PROBLEM', 'add new problems ');
INSERT INTO `module_details` (`module_id`, `module`, `description`) VALUES ('2', 'ADD_NEW_CADRE', 'add new cadre details');
INSERT INTO `module_details` (`module_id`, `module`, `description`) VALUES ('3', 'ADD_INFLUENCING_PEOPLE', 'add new influencing people');
INSERT INTO `module_details` (`module_id`, `module`, `description`) VALUES ('4', 'ADD_LOCAL_POLITICAL_CHANGES', 'add local political changes');

INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('1', '1', '1', '2');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('2', '1', '1', '3');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('3', '1', '1', '4');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('4', '1', '1', '5');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('5', '1', '1', '6');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('6', '1', '1', '7');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('7', '1', '1', '8');
INSERT INTO `module_region_scopes` (`module_region_scopes_id`, `state_id`, `module_id`, `region_scope_id`) VALUES ('8', '1', '1', '9');

ALTER TABLE `problem_location` DROP FOREIGN KEY `fk_problem_impact_level_problem_location`;
ALTER TABLE `problem_location` DROP COLUMN `problem_impact_level_id`
ALTER TABLE `problem_location` ADD COLUMN `region_scopes_id` BIGINT NULL  AFTER `problem_impact_level_value` ;

alter table problem_location
add constraint fk_region_scopes_problem_location
foreign key(region_scopes_id)
references region_scopes(region_scopes_id)
on delete restrict
on update restrict;


CREATE TABLE problem_complete_location ( 
	problem_complete_location_id	bigint(20) AUTO_INCREMENT NOT NULL,
	state_id       	bigint(20) NULL,
	district_id    	bigint(20) NULL,
	constituency_id	bigint(20) NULL,
	tehsil_id      	bigint(20) NULL,
	township_id    	bigint(20) NULL,
	hamlet_id      	bigint(20) NULL,
	local_election_body_id bigint(20) NULL,
	ward_id		bigint(20) NULL,
	parliament_constituency_id	bigint(20) NULL,
	booth_id	bigint(20) NULL,

    	PRIMARY KEY(problem_complete_location_id)
);


ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_state_problem_complete_location
	FOREIGN KEY(state_id)
	REFERENCES state(state_id);

ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_district_problem_complete_location
	FOREIGN KEY(district_id)
	REFERENCES district(district_id);

ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_constituency_problem_complete_location
	FOREIGN KEY(constituency_id)
	REFERENCES constituency(constituency_id);

ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_tehsil_problem_complete_location
	FOREIGN KEY(tehsil_id)
	REFERENCES tehsil(tehsil_id);

ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_township_problem_complete_location
	FOREIGN KEY(township_id)
	REFERENCES township(township_id);

ALTER TABLE problem_complete_location
	ADD CONSTRAINT fk_hamlet_problem_complete_location
	FOREIGN KEY(hamlet_id)
	REFERENCES hamlet(hamlet_id);

ALTER TABLE problem_complete_location 
	ADD CONSTRAINT fk_problem_complete_location_local_election_body
	FOREIGN KEY(local_election_body_id)
	REFERENCES local_election_body(local_election_body_id);

ALTER TABLE problem_complete_location 
	ADD CONSTRAINT fk_problem_complete_location_constituency_ward
	FOREIGN KEY(ward_id)
	REFERENCES constituency(constituency_id);

ALTER TABLE problem_complete_location 
	ADD CONSTRAINT fk_problem_complete_location_constituency
   	FOREIGN KEY(parliament_constituency_id)
    	REFERENCES constituency(constituency_id);

ALTER TABLE problem_complete_location 
    	ADD CONSTRAINT fk_problem_complete_location_booth
        FOREIGN KEY(booth_id)
	REFERENCES booth(booth_id);


ALTER TABLE problem_location ADD COLUMN problem_complete_location_id BIGINT NULL  AFTER problem_impact_level_value ;


alter table problem_location
add constraint fk_problem_complete_location_problem_location
foreign key(problem_complete_location_id)
references problem_complete_location(problem_complete_location_id)
on delete restrict
on update restrict;


INSERT INTO `entitlement` (`entitlement_type`) VALUES ('ADMIN_PAGE');

ALTER TABLE `user_constituency_access_info` DROP FOREIGN KEY `fk_registration_constituency_user` ;
ALTER TABLE `user_country_access_info` DROP FOREIGN KEY `fk_registration_country_user` ;
ALTER TABLE `user_district_access_info` DROP FOREIGN KEY `fk_registration_district_user` ;
ALTER TABLE `user_state_access_info` DROP FOREIGN KEY `fk_registration_state_user` ;

ALTER TABLE `user_constituency_access_info` CHANGE COLUMN `user_group_id` `user_id` BIGINT(20) NULL DEFAULT NULL 
, DROP INDEX `fk_registration_constituency_user`
, ADD INDEX `fk_registration_constituency_user` (`user_id` ASC) ;

ALTER TABLE `user_country_access_info` CHANGE COLUMN `user_group_id` `user_id` BIGINT(20) NULL DEFAULT NULL 
, DROP INDEX `fk_registration_country_user`
, ADD INDEX `fk_registration_country_user` (`user_id` ASC) ;

ALTER TABLE `user_district_access_info` CHANGE COLUMN `user_group_id` `user_id` BIGINT(20) NULL DEFAULT NULL 
, DROP INDEX `fk_registration_district_user`
, ADD INDEX `fk_registration_district_user` (`user_id` ASC) ;

ALTER TABLE `user_state_access_info` CHANGE COLUMN `user_group_id` `user_id` BIGINT(20) NULL DEFAULT NULL 
, DROP INDEX `fk_registration_state_user`
, ADD INDEX `fk_registration_state_user` (`user_id` ASC) ;

 INSERT INTO user_country_access_info (`user_country_access_info_id`, `user_id`, `country_id`) VALUES ('1', '1', '1');


insert into group_entitlement_relation (entitlement_id, group_entitlement_id) values
((select entitlement_id from entitlement where entitlement_type = 'ADMIN_PAGE'), 1);

UPDATE `constituency` SET `tehsil_id`='1075' WHERE `constituency_id`='2097';

INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('REASONS_POSTING');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select `entitlement_id` from `entitlement` where `entitlement_type` = 'REASONS_POSTING'), '1');


