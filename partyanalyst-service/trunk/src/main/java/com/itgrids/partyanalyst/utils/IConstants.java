package com.itgrids.partyanalyst.utils;

import java.awt.Color;

public interface IConstants {
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy/MM/dd";
	public static final Long CENSUS_YEAR = 2001L;
	public static final Long DELIMITATION_YEAR = 2009L;
	public static final String ELECTION_SUBTYPE_MAIN = "MAIN";
	public static final String ELECTION_SUBTYPE_BYE = "BYE";
	public static final Long COUNTRY_INDIA_ID = 1L;
	public static final String STATIC_PARTIES = "'INC','PRP','TDP','TRS','CPI','CPM','AIMIM','BJP'";
	public static final String NATIONAL_STATIC_PARTIES = "'INC','CPI','CPM','BJP'";
	public static final String NATIONAL_PARTY_TYPE = "NP";
	public static final String STATE_PARTY_TYPE = "SP";
	public static final int LOOSING_BY_DROPPING_VOTES_CONSTANTS = 5;
	public static final String SPACE =" ";
	public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final String HAMLET_TYPE = "1";
	public static final String TOWNSHIP_TYPE = "2";
	public static final String INDIPENDENT = "IND";
	//MPTC  data upload column names in excel sheet - start
	public final static String MPTC_NAME = "MPTC_NAME";//2
	public final static String CONSTITUENCY_TOTAL_VOTES = "CONSTITUENCY_TOTAL_VOTES";//3
	public final static String CONSTITUENCY_TOTAL_VOTES_POLLED = "CONSTITUENCY_TOTAL_VOTES_POLLED";//4
	public final static String CONSTITUENCY_VALID_VOTES = "CONSTITUENCY_VALID_VOTES";//5
	public final static String CANDIDATE_NAME = "CANDIDATE_NAME";//6
	public final static String PARTY_NAME = "PARTY_NAME";//7
	public final static String CANDIDATE_VOTES_EARNED = "CANDIDATE_VOTES_EARNED";//8
	public final static String DISTRICT_NAME = "DISTRICT_NAME";
	public final static String MANDAL_NAME = "MANDAL_NAME";//1
	public final static String LOCAL_ELECTION_BODY = "LOCAL_ELECTION_BODY";//1
	public final static String MUNCIPLE_WARD = "WARD";//1
	public final static String MUNCIPLE_WARD_NO = "MUNCIPLE_WARD_NO";
	public static final Long ASSEMBLY_ELECTION_TYPE_ID = 2L;
	
	public final static String CANDIDATE_VOTES_PERCENTAGE = "CANDIDATE_VOTES_PERCENTAGE";
	public final static String CANDIDATE_GENDER = "CANDIDATE_GENDER";
	public final static String CANDIDATE_EDUCATION = "CANDIDATE_EDUCATION";
	public final static String CANDIDATE_ADDRESS = "CANDIDATE_ADDRESS";
	public final static String CANDIDATE_MOBILE = "CANDIDATE_MOBILE";
	public final static String CANDIDATE_PHONE = "CANDIDATE_PHONE";
	public final static String CANDIDATE_EMAIL = "CANDIDATE_EMAIL";
	public final static String CANDIDATE_AGE = "CANDIDATE_AGE";
	public final static String CANDIDATE_DATE_OF_BIRTH = "CANDIDATE_DATE_OF_BIRTH";
	public final static String CONSTITUENCY_VOTING_PERCENTAGE = "CONSTITUENCY_VOTING_PERCENTAGE";
	public final static String CONSTITUENCY_TENDERED_VOTES = "CONSTITUENCY_TENDERED_VOTES";
	public final static String CONSTITUENCY_MISSING_VOTES = "CONSTITUENCY_MISSING_VOTES";
	public final static String CONSTITUENCY_REJECTED_VOTES = "CONSTITUENCY_REJECTED_VOTES";//ok
	public final static String CONSTITUENCY_RESERVATION_ZONE = "CONSTITUENCY_RESERVATION_ZONE";
	public final static String GOVERNING_BODY = "GOVERNING_BODY";
	public final static String GOVERNING_BODY_PARTY = "GOVERNING_BODY_PARTY";
	public final static String SUB_GOVERNING_BODY = "SUB_GOVERNING_BODY";
	public final static String SUB_GOVERNING_BODY_PARTY = "SUB_GOVERNING_BODY_PARTY";
	
//MPTC  data upload column names in excel sheet - end

	public final static String ZPTC_NAME = "ZPTC_NAME";
	public static final String ZPTC = "ZPTC";
	public final static String ELECTION_UPLOAD_DISTRICT_COLUMN = "DISTRICT";
	
	public static final String MPTC = "MPTC";
	public static final String CALL_CENTER = "CALL CENTRE";
	public static final String USER = "USER";
	public static final String EXTERNAL_PERSON = "EXTERNAL PERSON"; 
	public static final String PRESENT_YEAR = "2010";	
	
	public final static String ZPTC_ELECTION_TYPE = "ZPTC";
	public final static String MPTC_ELECTION_TYPE = "MPTC";
	public final static String MUNCIPLE_ELECTION_TYPE = "MUNCIPALITY";
	public final static String CORPORATION_ELECTION_TYPE = "CORPORATION";
	public final static String ASSEMBLY_ELECTION_TYPE = "Assembly";
	public final static String PARLIAMENT_ELECTION_TYPE = "Parliament";
	
	//Governing Body Position
	public final static String MUNCIPALE_CHAIRMAN = "CHAIRPERSON";
	public final static String MUNCIPALE_VICE_CHAIRMAN = "VICE CHAIRPERSON";
	public final static String MAYOR = "MAYOR";
	public final static String DYPUTY_MAYOR = "DEPUTY MAYOR";
	
	public final static String NOT_APPLICABLE = "N/A";
	
	public final static String COMMA = ",";
	
	public final static String MANDAL ="MANDAL";
	public final static String REVENUE_VILLAGE ="RV";
	
	public final static String CANDIDATE_STATIC_PAGE_URL = "candidate_static_page/Andhra_Pradesh/";

	
	public final static String COMPLETE_MANDAL = "COMPLETE_MANDAL";
	public final static String MALE_TRENDZ = "MALE_TRENDZ";
	public final static String FEMALE_TRENDZ = "FEMALE_TRENDZ";
	public final static Long MALETRENDZ = new Long(0);
	public final static Long FEMALETRENDZ = new Long(0);
	public final static String MALE_FEMALE_TRENDZ = "MALE_FEMALE_TRENDZ";
	public final static String TEHSIL = "TEHSIL";
	public static final int LATEST_CENSUS_YEAR = 2001;
	public final static String USER_GROUP_CATEGORY_PARENT = "USER_GROUP_CATEGORY_PARENT";
	public final static String USER_GROUP_CATEGORY_CHILD = "USER_GROUP_CATEGORY_CHILD";
	
	public final static String SMS_DEAR = "DEAR ";
	
	public final static String ALL_GROUPS = "ALL_GROUPS";  
	public final static String MY_GROUPS = "MY_GROUPS";
	public final static String STATIC_GROUPS = "STATIC_GROUPS";
	public final static String MAIN_GROUP = "MAIN_GROUP";
	public final static String SUB_GROUP = "SUB_GROUP";
	public final static String STATIC_GROUP = "STATIC_GROUP";
	public final static String MY_GROUP = "MY_GROUP";
	public final static String VOTES_PERCENT_MARGIN = "0.1";
	
	//election data constants
	public final static String COUNTRY_LEVEL="COUNTRY";
	public final static String STATE_LEVEL="STATE";
	public final static String DISTRICT_LEVEL="DISTRICT";
	public final static String CONSTITUENCY_LEVEL="CONSTITUENCY";
	public final static String TEHSIL_LEVEL="TEHSIL";
	public final static String REVENUE_VILLAGE_LEVEL="REVENUE VILLAGE";
	public final static String HAMLET_LEVEL="HAMLET";
	public final static String WINNER_CANDIDATES="WINNER";
	public final static String ALL_CANDIDATES="ALL_CANDIDATES";
	public final static String LOST_CANDIDATES="LOST_CANDIDATES";
	public final static String CENSUS_VILLAGE_LEVEL = "VILLAGE";
	public final static String CENSUS_WARD_LEVEL = "WARD";
	
	//comments data constants
	public final static String CANDIDATE_COMMENTS_ALL="CANDIDATE_COMMENTS_ALL";
	public final static String CANDIDATE_COMMENTS_CONSTITUENCY="CANDIDATE_COMMENTS_CONSTITUENCY";
	public final static String CANDIDATE_COMMENTS_ALL_CONSTITUENCY="CANDIDATE_COMMENTS_ALL_CONSTITUENCY";
	public final static String PARTY_COMMENTS_ALL="PARTY_COMMENTS_ALL";
	public final static String PARTY_COMMENTS_ELECTION="PARTY_COMMENTS_ELECTION";
	public final static String CONSTITUENCY_COMMENTS_ALL="CONSTITUENCY_COMMENTS_ALL";
	public final static String CONSTITUENCY_COMMENTS_ELECTION="CONSTITUENCY_COMMENTS_ELECTION";
    public final static String CANDIDATE_COMMENTS_WON="WON";
    public final static String CANDIDATE_COMMENTS_LOST="LOST";
    
    //Party Names And Color Codes
    public final static String TDP = "TDP";
    public final static String INC = "INC";
    public final static String PRP = "PRP";
    public final static String BJP = "BJP";
    public final static String TRS = "TRS";
    public final static String AIMIM = "AIMIM";
    public final static String CPI = "CPI";
    public final static String BSP = "BSP";
    public final static String IND = "IND";
    public final static String CPM = "CPM";
    
    public final static Color IND_COLOR = Color.DARK_GRAY;
    public final static Color TDP_COLOR = Color.RED;
    public final static Color INC_COLOR = Color.BLUE;
    public final static Color PRP_COLOR = Color.GREEN;
    public final static Color BJP_COLOR = new Color(0xFAC30B);
    public final static Color TRS_COLOR = new Color(0xE408F3);
    public final static Color AIMIM_COLOR = Color.CYAN;
    public final static Color CPI_COLOR = Color.MAGENTA;
    public final static Color CPM_COLOR = Color.YELLOW;
    public final static Color DEFAULT_COLOR = Color.LIGHT_GRAY;
    //problem management status constants
    public final static String NEW = "NEW";
    public final static String CLASSIFY = "CLASSIFY";
    public final static String ASSIGNED = "ASSIGNED";
    public final static String PROGRESS = "PROGRESS";
    public final static String PENDING = "PENDING";
    public final static String FIXED = "FIXED";
    
    public final static Color NEW_COLOR = new Color(0XFEF200);
    public final static Color CLASSIFY_COLOR = new Color(0XB2D233);
    public final static Color ASSIGNED_COLOR = new Color(0XAB499C);
    public final static Color PROGRESS_COLOR = new Color(0XFFC50C);
    public final static Color PENDING_COLOR = new Color(0XB29D40);
    public final static Color FIXED_COLOR = new Color(0XF05922);
    
    public final static String TRUE = "true";
    public final static String FALSE = "false";
    public final static String EDIT = "edit";
    public final static String VILLAGE = "village";
    public final static String 	PROBLEMS_MANAGEMENT = "PROBLEMS_MANAGEMENT";
    public final static String 	CONSTITUENCY_MANAGEMENT = "CONSTITUENCY_MANAGEMENT";
    
    public final static String 	OTHERS = "Others *";//For Ballet votes In constituency Page
    
    public final static String PRESENT_ELECTION_YEAR = "2009";
    public final static String LATEST_BYE_ELECTION_YEAR = "2010";
    public final static String PREVIOUS_ELECTION_YEAR = "2004";
    public final static String BYE_ELECTION_YEAR_2008_PARLIAMENT = "2008";
    public final static String BYE_ELECTION_YEAR_2006_PARLIAMENT = "2006";
    
    public final static String LOCAL_ELECTIONS_PRESENT_ELECTION_YEAR = "2006";
    public final static String LOCAL_ELECTIONS_PREVIOUS_ELECTION_YEAR = "2001";
    
    public final static String MAIN_PARTY = "MAIN_PARTY";
    public final static String OPP_PARTY = "OPP_PARTY";
    public final static String VOTES_MARGIN = "VOTES_MARGIN";
    public final static String VOTES_PERCENT = "VOTES_PERCENT";
    
    //SMS Modules
    public final static String User_Groups = "User Groups";
    public final static String Cadre_Management = "Cadre Management";
    public final static String Influencing_People ="Influencing People";
    
    public static final String BYE_ELECTIONS_STATIC_PARTIES = "'INC','PRP','TDP','TRS','BJP'";
    
    public static final String ALL = "all";
    public static final String CADRE_MEMBER_TYPE_ACTIVE = "Active";
    public static final String CADRE_MEMBER_TYPE_NORMAL = "Normal";
    public static final String USER_TYPE_PARTY = "Party";
    public static final String USER_TYPE_POLITICIAN = "Politician";
    public static final String LANGUAGE_ENGLISH = "English";
    public static final String LANGUAGE_HINDI= "Hindi";
    public static final String LOCATION_BASED = "LOCATION";
    public static final String LEVEL_BASED = "LEVEL";
    
    public static final String DEFORMED_CONSTITUENCY = "DC";
    public static final String PARTY_NOT_PARTICIPATED = "PNP";
    public static final String NEW_CONSTITUENCY = "NC";
    
    //Entitlements
    public static final String CONSTITUENCY_PAGE = "CONSTITUENCY_PAGE";
    public static final String PARTY_PERFORMANCE_REPORT = "PARTY_PERFORMANCE_REPORT";
    public static final String ELECTION_COMPARISION_REPORT = "ELECTION_COMPARISION_REPORT";
    public static final String PARTY_RESULTS_REPORT = "PARTY_RESULTS_REPORT";
    public static final String PARTY_INFLUENCE_REPORT = "PARTY_INFLUENCE_REPORT";
    public static final String CROSS_VOTING_REPORT = "CROSS_VOTING_REPORT";
    public static final String PARTY_BOOTHWISE_RESULTS_REPORT = "PARTY_BOOTHWISE_RESULTS_REPORT";
    public static final String ELECTION_RESULTS_ANALYSIS_REPORT = "ELECTION_RESULTS_ANALYSIS_REPORT";
    public static final String CONSTITUENCY_ANALYSIS = "CONSTITUENCY_ANALYSIS";
    public static final String TEHSIL_ANALYSIS = "TEHSIL_ANALYSIS";
    public static final String DEFAULT_ENTITLEMENTS_GROUP = "DEFAULT_ENTITLEMENTS";
    
    
    public static final String NULL_POINTER_EXCEPTION = "NULL_POINTER_EXCEPTION";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = "ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION";
    public static final String NUMBER_FORMAT_EXCEPTION = "NUMBER_FORMAT_EXCEPTION";
    public static final String GENERAL_EXCEPTION = "GENERAL_EXCEPTION";
    public static final String CREATE_NEW = "new";
    public static final String UPDATE_EXISTING = "update_existing";
    public static final String SPEAK_LANGUAGE = "Able To Speak";
    public static final String READ_LANGUAGE = "Able To  Read"; 
    public static final String WRITE_LANGUAGE = "Able To  Write";
    
    
}
