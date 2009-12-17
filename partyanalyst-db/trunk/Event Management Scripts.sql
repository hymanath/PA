
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
