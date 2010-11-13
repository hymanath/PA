package com.itgrids.partyanalyst.utils;

public interface ISessionConstants {
	//REGIONAL VARIABLES FOR CURRENT ADDRESS
	public static final String STATES = "statesList";
	public static final String DISTRICTS = "districtsList";
	public static final String CONSTITUENCIES = "constituenciesList";
	//parliament Constituencies
	public static final String P_CONSTITUENCIES = "p_constituencies";
	public static final String MANDALS = "mandalsList";
	public static final String VILLAGES = "villagesList";
	public static final String WARDS_OR_HAMLETS = "wardsOrHamletsList";
	
	// ADDRESS VARIBALES FOR OFFICIAL ADDRESS
	public static final String STATES_O = "statesList_o";
	public static final String DISTRICTS_O = "districtsList_o";
	public static final String CONSTITUENCIES_O = "constituenciesList_o";
	public static final String P_CONSTITUENCIES_O = "p_constituencies_o";
	public static final String MANDALS_O = "mandalsList_o";
	public static final String VILLAGES_O = "villagesList_o";
	// cadre levle variables
	public static final String CADRE_LEVELS_LIST = "cadreLevelsList";
	public static final String DISTRICTS_C = "districtsList_c";
	public static final String STATES_C = "statesList_c";
	public static final String P_CONSTITUENCIES_C = "p_constituencies_c";
	public static final String CONSTITUENCIES_C = "constituenciesList_c";
	public static final String MANDALS_C = "mandalsList_c";
	public static final String VILLAGES_C = "villagesList_c";
	//contains social categories like sc, st etc.
	public static final String SOCIALCATEGORIES = "socialStatus";
	// contains educational qualifications
	public static final String EDU_QUALIFICATIONS = "eduQualsList";
	
	public static final String OCCUPATIONS = "occupationsList";
	public static final String LANGUAGES = "languagesList";
	
	//party committees specific to party
	public static final String PARTY_COMMITTEES = "partyCommittees";
	public static final String COMMITTEE_DESIGNATIONS = "designations";
	
	//cadre skills list based on party
	public static final String CADRE_SKILLS = "cadreSkills";
	public static final String CADRE_LEVELS = "cadreLevels";
	// training camps based on party
	public static final String PARTY_TRAINING_CAMPS="partyTrainingCamps";
	public static final String GENDERS = "genders";
	public static final String DOB_OPTIONS = "dob_Options";
	public static final String CADRETYPES = "cadreType";
	public static final String LANGUAGE_OPTIONS = "language_options";
	//booleans used whether to show or hide the corresponding content 
	public static final String PARTY_COMMITTEES_FLAG = "partyCommittees_flag"; 
	public static final String CADRE_SKILLS_FLAG = "cadreSkills_flag"; 
	public static final String PARTY_TRAINING_CAMPS_FLAG = "partyTrainingCamps_flag";
	
	
	//list of information sources for problem complains, local policitcal changes, influencing people 
	public static final String INFO_SOURCES = "informationSourcesList";
	
	//list of information static(main) parties 
	public static final String MAIN_PARTIES = "mainPartiesList";
	
	//list of impacted regions for local policitcal changes, influencing people, problem management 
	public static final String IMPACTED_REGIONS = "impactedRegionsList";
	
}
