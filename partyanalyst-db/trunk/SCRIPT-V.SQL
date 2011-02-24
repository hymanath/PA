INSERT INTO delimitation_ward (delimitation_constituency_
town_id, ward_id) VALUES ('72', '438');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('72', '439');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('72', '440');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('72', '441');
INSERT INTO delimitation_constituency_town (delimitation_constituency_id, township_id, is_partial) VALUES ('67', '4857', '1');
INSERT INTO delimitation_constituency_town (delimitation_constituency_id, township_id, is_partial) VALUES ('208', '20280', '1');
INSERT INTO delimitation_constituency_town (delimitation_constituency_id, township_id, is_partial) VALUES ('68', '5009', '1');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '485');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '486');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '487');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '488');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '490');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '491');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '492');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '493');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '494');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '495');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('90', '496');


INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2661');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2662');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2663');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2664');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2665');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2666');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2684');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2685');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2686');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2687');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('91', '2688');

INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '608');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '609');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '610');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '611');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '612');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '613');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '614');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '615');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '616');
INSERT INTO delimitation_ward (delimitation_constituency_town_id, ward_id) VALUES ('92', '617');


INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('CONSTITUENCY_MANAGEMENT_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('PROBLEM_MANAGEMENT_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('USER_GROUPS_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('CADRE_MANAGEMENT_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('CONSTITUENCY_RESULTS_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('BIEELECTION_ENTITLEMENT');
INSERT INTO `party_analyst_schema`.`entitlement` (`entitlement_type`) VALUES ('CENSUS_REPORT_ENTITLEMENT');

INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'CONSTITUENCY_MANAGEMENT_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'PROBLEM_MANAGEMENT_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'USER_GROUPS_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'CADRE_MANAGEMENT_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'CONSTITUENCY_RESULTS_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'BIEELECTION_ENTITLEMENT'), '1');
INSERT INTO `party_analyst_schema`.`group_entitlement_relation` (`entitlement_id`, `group_entitlement_id`) VALUES ((select entitlement_id from entitlement where entitlement_type = 'CENSUS_REPORT_ENTITLEMENT'), '1');

alter table local_group_region add column parliament_constituency_id bigint(20);

alter table local_group_region ADD CONSTRAINT fk_local_group_region_pConstituency
FOREIGN KEY(parliament_constituency_id)
REFERENCES constituency(constituency_id)
ON DELETE RESTRICT
ON UPDATE RESTRICT;

CREATE TABLE census_parameter (
    census_parameter_id    bigint(20) AUTO_INCREMENT NOT NULL,
    parameter_name       varchar(20) NULL,
    PRIMARY KEY(census_parameter_id)
);

INSERT INTO census_parameter (parameter_name) VALUES ('SC Population');
INSERT INTO census_parameter (parameter_name) VALUES ('ST Population');
INSERT INTO census_parameter (parameter_name) VALUES ('Literates');
INSERT INTO census_parameter (parameter_name) VALUES ('illiterates');
INSERT INTO census_parameter (parameter_name) VALUES ('Working People');
INSERT INTO census_parameter (parameter_name) VALUES ('Non Working People');

delete from constituency_census_details;

ALTER TABLE constituency_census_details
MODIFY COLUMN SC_PER double(10,2),
MODIFY COLUMN ST_PER double(10,2),
MODIFY COLUMN TOT_WOR_PER_P double(10,2),
MODIFY COLUMN NON_WORK_PER_P double(10,2),
MODIFY COLUMN P_LIT_PER double(10,2);
