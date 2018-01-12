package com.itgrids.partyanalyst.utils;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public interface IConstants {
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy/MM/dd";
	public static final String DATE_PATTERN_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
	public static final Long CENSUS_YEAR = 2001L;
	public static final Long DELIMITATION_YEAR = 2009L;
	public static final Long PREV_DELIMITATION_YEAR = 2004L;
	public static final String ELECTION_SUBTYPE_MAIN = "MAIN";
	public static final String ELECTION_SUBTYPE_BYE = "BYE";
	public static final Long COUNTRY_INDIA_ID = 1L;
	public static final String STATIC_PARTIES = "'INC','PRP','TDP','TRS','YSRC','CPI','CPM','AIMIM','BJP'";
	public static final String STATIC_TAMIL_NADU_PARTIES = "'MDMK','AIADMK','DMK','PMK','INC','CPI','CPM','BJP','DMDK'";
	public static final String STATIC_KARNATAKA_PARTIES = "'JD(S)','JD(U)','KNDP','AIADMK','INC','CPI','CPM','BJP'";
	public static final String STATIC_PARTIES_WITHOUT_QUOTES = "INC,PRP,TDP,TRS,CPI,CPM,AIMIM,BJP";
	public static final String STATIC_PARTIES_TAMIL_NADU_WITHOUT_QUOTES = "MDMK,AIADMK,DMK,PMK,INC,CPI,CPM,BJP";
	public static final String STATIC_PARTIES_KARNATAKA_WITHOUT_QUOTES = "JD(S),JD(U),KNDP,AIADMK,INC,CPI,CPM,BJP";
	public static final String STATIC_STATE_PARTIES_35 = "'AIADMK','DMK','PMK'";
	public static final String STATIC_STATE_PARTIES_13 = "'AIADMK','RSP'";
	public static final String DATE_PATTERN_VALUE = "dd-MM-yyyy";
	
	public static final String NoPassword = "0";
	public static final String YesPassword = "121";
	public static final String NATIONAL_STATIC_PARTIES = "'INC','CPI','CPM','BJP','BSP'";
	public static final String NATIONAL_PARTY_TYPE = "NP";
	public static final String STATE_PARTY_TYPE = "SP";
	public static final int LOOSING_BY_DROPPING_VOTES_CONSTANTS = 5;
	public static final String SPACE =" ";
	public static final String MESSAGE_APPENDER ="From";
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
	public static final Long   JOB_SCHEDULER_USER_ID = 7951l;
	
	public final static String ZPTC_ELECTION_TYPE = "ZPTC";
	public final static String MPTC_ELECTION_TYPE = "MPTC";
	public final static String MUNCIPLE_ELECTION_TYPE = "MUNCIPALITY";
	public final static String CORPORATION_ELECTION_TYPE = "CORPORATION";
	public final static String ASSEMBLY_ELECTION_TYPE = "Assembly";
	public final static String PARLIAMENT_ELECTION_TYPE = "Parliament";
	public final static String GREATER_ELECTION_TYPE = "Greater Municipal Corp";
	
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
	public final static String WARD = "WARD";
	public final static String LOCAL_BODY_ELECTION = "LOCAL_BODY_ELECTION";
	public final static String WINNER_CANDIDATES="WINNER";
	public final static String SUCCESSOR_CANDIDATES="SUCCESSOR";
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
    public final static String VILLAGE = "VILLAGE";
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
    public final static String LATEST_ELECTION_YEAR = "2014";
    
    public final static String MAIN_PARTY = "MAIN_PARTY";
    public final static String OPP_PARTY = "OPP_PARTY";
    public final static String VOTES_MARGIN = "VOTES_MARGIN";
    public final static String VOTES_PERCENT = "VOTES_PERCENT";
    public static final String TIRUPATHI_BYEELECTION = "TIRUPATHI_BYEELECTION";
    //SMS Modules
    public final static String User_Groups = "User Groups";
    public final static String Cadre_Management = "Cadre Management";
    public final static String Influencing_People ="Influencing People";
    public final static String PROBLEM_MANAGEMENT = "Problem Managemet";
    public final static String VOTER = "Voter";
    
    public static final String BYE_ELECTIONS_STATIC_PARTIES = "'INC','PRP','TDP','TRS','BJP','CPM','CPI'";
    
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
    
    public static final Long ADMIN_USER_ID = 1L;
    public static final String ADMIN_PAGE = "ADMIN_PAGE";
    public static final String CONSTITUENCY_PAGE = "CONSTITUENCY_PAGE";
    public static final String CONSTITUENCY_PAGE_COMPLETE_VIEW = "CONSTITUENCY_PAGE_COMPLETE_VIEW";
    public static final String MANDAL_VOTING_TRENDZ = "MANDAL_VOTING_TRENDZ";
    public static final String VOTING_TRENDZ = "VOTING_TRENDZ";
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
    public static final String DISTRICT_PAGE_ALL_ELECTION_HIRARCHIES = "DISTRICT_PAGE_ALL_ELECTION_HIRARCHIES";
    public static final String ENTITLEMENT_PAGE = "ENTITLEMENT_PAGE";
    public static final String COMMENTS_POST = "COMMENTS_POST";
    public static final String COMMETNS_ANALYZE = "COMMETNS_ANALYZE";
    public static final String CADRE_VIEW = "CADRE_VIEW";
    public static final String CADRE_CREATE = "CADRE_CREATE";
    public static final String CADRE_UPDATE = "CADRE_UPDATE";
    public static final String CADRE_DELETE = "CADRE_DELETE";
    public static final String CADRE_SEARCH = "cadreSearch";
    public static final String PROBLEM_ADDING = "problemAdding";
    public static final String CONSTITUENCY_MANAGEMENT_ENTITLEMENT = "CONSTITUENCY_MANAGEMENT_ENTITLEMENT";
    public static final String PROBLEM_MANAGEMENT_ENTITLEMENT = "PROBLEM_MANAGEMENT_ENTITLEMENT";
    public static final String USER_GROUPS_ENTITLEMENT = "USER_GROUPS_ENTITLEMENT";
    public static final String CADRE_MANAGEMENT_ENTITLEMENT = "CADRE_MANAGEMENT_ENTITLEMENT";
    public static final String CONSTITUENCY_RESULTS_ENTITLEMENT = "CONSTITUENCY_RESULTS_ENTITLEMENT";
    public static final String BIEELECTION_ENTITLEMENT = "BIEELECTION_ENTITLEMENT";
    public static final String CENSUS_REPORT_ENTITLEMENT = "CENSUS_REPORT_ENTITLEMENT";
    public static final String CADRE_UPLOAD_ENTITLEMENT = "CADRE_UPLOAD_ENTITLEMENT";
    public static final String CALL_CENTER_ENTITLEMENT = "CALL_CENTER_ENTITLEMENT";
    public static final String PROFILE_MANAGEMENT_ENTITLEMENT = "PROFILE_MANAGEMENT_ENTITLEMENT";
    public static final String NEWS_MONITORING_ENTITLEMENT = "NEWS_MONITORING_ENTITLEMENT";
    public static final String ECR_DETAILED_ANALYSIS = "ECR_DETAILED_ANALYSIS";
    public static final String PARTY_STRENGTH_AND_WEAKNESS = "PARTY_STRENGTH_AND_WEAKNESS";
    public static final String ELECTION_RESULT_REPORT_DETAILED_ANALYSIS = "ELECTION_RESULTS_REPORT_DETAILED_ANALYSIS";
    public static final String ADD_USER_ENTITLEMENT = "ADD_USER_ENTITLEMENT";
    public static final String ADD_SUBUSER_ENTITLEMENT = "ADD_SUBUSER_ENTITLEMENT";
    public static final String LIVE_RESULTS_ANALYSIS_ENTITLEMENT = "LIVE_RESULTS_ANALYSIS_ENTITLEMENT";
    public static final String ASSIGN_A_ELECTION_GOVERNING_BODY = "ASSIGN_A_ELECTION_GOVERNING_BODY";
    public static final String MINISTERS_AND_SPECIAL_CANDIDATES_ANALYSIS = "MINISTERS_AND_SPECIAL_CANDIDATES_ANALYSIS";
    public static final String UPDATE_LIVE_ELECTION_RESULTS = "UPDATE_LIVE_ELECTION_RESULTS";
    public static final String ASSIGN_KEY_CANDIDATE_ENTITLEMENT = "ASSIGN_KEY_CANDIDATE_ENTITLEMENT";
    public static final String DISTRICTWISE_LIVE_RESULTS_ANALYSIS = "DISTRICTWISE_LIVE_RESULTS_ANALYSIS";
    public static final String VOTER_SEARCH_AND_EDIT = "VOTER_SEARCH_AND_EDIT";
    public static final String VOTER_ANALYSIS = "VOTER_ANALYSIS";
    public static final String CADRE_PARLIAMENT_WISE = "CADRE_PARLIAMENT_WISE";
    public final static String VOTER_DATA_TOOLS = "VOTER_DATA_TOOLS";
    public final static String ASPIRANT_DEMO_REQUESTS_VIEW = "ASPIRANT_DEMO_REQUESTS_VIEW";
    public final static String HOUSEHOLDS_SURVEY_ENTITLEMENT = "HOUSEHOLDS_SURVEY_ENTITLEMENT";
    public final static String INFORMATION_MONITOTING_SYSTEM = "INFORMATION_MONITOTING_SYSTEM";
    public static final String CASTE_SURVEY_CALL_CENTER = "CASTE_SURVEY_CALL_CENTER";
    public static final String VIZAG_WM = "VIZAG_WM";
    public static final String WEB_MONITORING_LEAD = "WEB_MONITORING_LEAD";
    public final static String NEW_LIVE_RESULTS = "NEW_LIVE_RESULTS"; 
    public final static String PARTY_CADRE_SEARCH = "PARTY_CADRE_SEARCH"; 
    public final static String IVR_MOBILE_NUMBERS_RETRIVAL = "IVR_MOBILE_NUMBERS_RETRIVAL"; 
    public static final String NULL_POINTER_EXCEPTION = "NULL_POINTER_EXCEPTION";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = "ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION";
    public static final String NUMBER_FORMAT_EXCEPTION = "NUMBER_FORMAT_EXCEPTION";
    public static final String GENERAL_EXCEPTION = "GENERAL_EXCEPTION";
    public static final String CREATE_NEW = "new";
    public static final String UPDATE_EXISTING = "update_existing";
    public static final String SPEAK_LANGUAGE = "Speak";
    public static final String READ_LANGUAGE = "Read"; 
    public static final String WRITE_LANGUAGE = "Write";
    public static final String CONST_TYPE_URBAN = "URBAN";
    public static final String CONST_TYPE_RURAL = "RURAL";	
    public static final String CONST_TYPE_RURAL_URBAN = "RURAL-URBAN";
    public static final String URBAN_TYPE = "1";
    public static final String RURAL_TYPE = "2";
    public final static String IVR_MOBILE_NUMBERS_RETRIVAL_REDIRECT = "IVR_MOBILE_NUMBERS_RETRIVAL_REDIRECT"; 
    public static final String SURVEY_USER_CREATION = "SURVEY_USER_CREATION";
    public static final String CTP_CASTE_REPORT_ENTITLEMENT = "CTP_CASTE_REPORT_ENTITLEMENT";
    public static final String CTP_CASTE_ADMIN_ENTITLEMENT = "CTP_CASTE_ADMIN_ENTITLEMENT";
    public static final String TDP_CADRE_2014_ADMIN = "TDP_CADRE_2014_ADMIN";
    public static final String TDP_CADRE_SEARCH = "TDP_CADRE_SEARCH";
    public static final String TDP_CADRE_DETAILS = "TDP_CADRE_DETAILS";
    public static final String CREATE_ALERT_ENTITLEMENT = "CREATE_ALERT_ENTITLEMENT";
	public static final String UPDATE_ALERT_ENTITLEMENT = "UPDATE_ALERT_ENTITLEMENT";
	public static final String ALERT_DASHBOARD_ENTITLEMENT= "ALERT_DASHBOARD_ENTITLEMENT";
    
    //Problem Management
    public static final String MLA = "MLA";
    public static final String MP = "MP";
    public static final String PREVIOUS_DAY = "previous";
    public static final String PRESENT_DAY = "present";
    public static final String FUTURE_DAY = "future";
    
    public static final String NOT_LOGGED_IN = "NOT_LOGGED_IN";
	public static final String LOGGED_IN = "LOGGED_IN";
	public static final String PROBLEM_MANAGEMENT_LOGIN = "PM_LOGIN";
	public static final String PARTY_ANALYST_USER = "PARTY_ANALYST_USER";
	public static final String FREE_USER = "FREE_USER";
	public static final String SUB_USER = "subUser";
	public static final String POLITICIAN = "POLITICIAN";
	public static final String COUNTRY = "COUNTRY";
	public static final String STATE = "STATE";
	public static final String DISTRICT = "DISTRICT";
	public static final String CONSTITUENCY = "CONSTITUENCY";
	public static final String TEHSIL = "TEHSIL";
	public static final String HAMLET = "HAMLET";
	public static final String TOWNSHIP = "TOWNSHIP";
	public static final String User = "User";
	public static final String External_person = "External_person";
	public static final String Call_Center = "Call_Center";
	public static final String CONNECT_REDIRECT = "CONNECT_REDIRECT";
	
    public static final String LOCALELECTIONBODY = "LOCAL ELECTION BODY";
    public static final String REJECTED = "Rejected";
    public static final Long MAX_PROBLEMS_DISPLAY = 20L;
    public static final Long MAX_ANONYMOUS_USER_DISPLAY = 20L;
    public static final Long ALL_CONNECTED_USER_DISPLAY = 100L;
    public static final Long MAX_SEARCH_RESULTS_DISPLAY = 100L;
    
    public static final String CONNECTED = "CONNECTED";
    public static final String NOTCONNECTED = "NOT CONNECTED"; 
    public static final String DISCONNECTED = "DISCONNECTED";   
    public static final String FRIEND_REQUEST = "FRIEND REQUEST";
    public static final String COMMENTS = "COMMENTS";
    public static final String BLOCK = "BLOCK";
    public static final String UNBLOCK = "UNBLOCK";
    public static final String SCRAP = "SCRAP";
    public static final String LOGGED_USER = "LOGGED_USER";
    public static final String MSG_READ = "READ";
    public static final String MSG_UNREAD = "UNREAD";
    
    public static final String INFLUENCING_PEOPLE = "INFLUENCING_PEOPLE";
    public static final String LOCAL_USER_GROUPS = "LOCAL_USER_GROUPS";
    public static final String LOCAL_POLITICAL_CHANGES = "LOCAL_POLITICAL_CHANGES";
    
    public static final String PARLIAMENT_CONSTITUENCY_LEVEL = "PARLIAMENT CONSTITUENCY";
    public static final String ASSEMBLY_CONSTITUENCY_LEVEL = "ASSEMBLY CONSTITUENCY";
    public static final String MUNCIPALITY_CORPORATION_LEVEL = "MUNCIPALITY/CORPORATION";
    public static final String PANCHAYAT = "PANCHAYAT";
    public static final String MANDAL_LEVEL = "MANDAL";
    public static final int MAX_LEVEL_OF_CONNECTION = 2;
    public static final String COMPLETE_DETAILS = "COMPLETE_DETAILS";
    public static final String PARTIAL_DETAILS = "PARTIAL_DETAILS";
    
    public static final String AVAILABLE = "AVAILABLE";
    public static final String NOT_AVAILABLE = "NOT_AVAILABLE";
    
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String SUCCESSFULLY_SAVED = "SUCCESSFULLY_SAVED";
    public static final String BOOTH = "BOOTH";
    public static final String MP_CONSTITUENCY = "MP CONSTITUENCY";
    public static final String NULL = "Null";
    
    //Constants for census mappings upload
    public static final String SNO          = "SNO";
    public static final String TRU          = "TRU";
    public static final String SCOPE        = "SCOPE";
    public static final String REGION_NAME  = "NAME";
    public static final String REGION_LEVEL = "LEVEL";
    
    public static final String HTMLDOUBLEQUOTES = "&#34";
    public static final String HTMLSINGLEQUOTE = "&#34";
    public static final String HTMLSLASH = "&#47";
    public static final String HTMLENTER = "<BR>";
    public static final String HTMLSPACE = " ";
    public static final String ADD_NEW_PROBLEM = "ADD_NEW_PROBLEM";
    public static final String ADD_INFLUENCING_PEOPLE = "ADD_INFLUENCING_PEOPLE";
    public static final String ADD_CADRE = "ADD_NEW_CADRE";
    public static final String ADD_POLITICAL_CHANGES = "ADD_LOCAL_POLITICAL_CHANGES";
    public static final String MUNICIPAL_CORP_GMC = "MUNICIPAL-CORP-GMC";
	public static final String URBAN = "URBAN";
	public static final String RURAL = "RURAL";
	public static final String RURALURBAN = "RURAL-URBAN";
	public static final String TOWN = "TOWN"; 
    
    public static final String APPROVED = "Approved"; 
    public static final String OTHERUSERS = "OtherUsers";
    public static final String NOTCONSIDERED = "NotConsidered";
    public static final String NAME = "NAME"; 
    public static final String CADRE = "CADRE"; 
    public static final String FAMILY_MEMBERS = "FAMILY MEMBERS"; 
    public static final String PROBLEM_DISCUSSION = "PROBLEM_DISCUSSION";
    public static final String MUNCPAL_CORP = "MUNCIPALITY/CORPORATION";
    public static final String TOTAL = "Total";
    public static final String STATE_STR = "state";   
    public static final String DISTRICT_STR = "district";
    public static final String CADRE_PERSONAL = "PERSONAL";
    public static final String CADRE_ASSIGNED = "ASSIGNED";
    public static final String VOTES_PERCENTAGE_CONCERNED = "0.5";
    
    public static final String PROBLEMS_BY_DATE = "PROBLEMS_BY_DATE";
    public static final String PROBLEMS_BY_MONTH = "PROBLEMS_BY_MONTH";
    
    public static final String PROBLEMS_LIFE_CYCLE = "'NEW','PROGRESS','PENDING','FIXED'";
    
    public static final String ID = "ID";
    public static final String MODEL = "MODEL";
    public static final String MODEL_ID = "MODEL_ID";
    
    public static final String SELECT_USER_MESSAGE = "select a user";
    
    public static final String STATES_FOR_SEARCH_FUNCTIONALITY = "1,12,24,3,28,13,35,7,26,21,9,27,16,6";
    
    public static final String ACCEPT = "Accept";
    public static final String REJECT = "Reject";
    
    public static final String CADRE_ADD = "CADRE_ADD";
    public static final String CADRE_MODIFY = "CADRE_MODIFY";
      
    public static final String DEPARTMENT_ADD = "DEPARTMENT_ADD";
    public static final String DEPARTMENT_MODIFY = "DEPARTMENT_MODIFY";
    public static final String DEPARTMENT_DELETE = "DEPARTMENT_DELETE";
    
    public static final String PROBLEM_TYPE_ADD = "PROBLEM_TYPE_ADD";
    public static final String PROBLEM_TYPE_MODIFY = "PROBLEM_TYPE_MODIFY";
    public static final String PROBLEM_TYPE_UPDATE = "PROBLEM_TYPE_UPDATE";

          
    public static final String PROBLEM_STATUS_ADD = "PROBLEM_STATUS_ADD";
    public static final String PROBLEM_STATUS_MODIFY = "PROBLEM_STATUS_MODIFY";
    public static final String PROBLEM_STATUS_CHANGE = "PROBLEM_STATUS_CHANGE";
    public static final String STATUS_CHANGE = "STATUS_CHANGE";

    public static final String PROBLEM_COMMENTS_ADD = "PROBLEM_COMMENTS_ADD";
    public static final String PROBLEM_COMMENTS_MODIFY = "PROBLEM_COMMENTS_MODIFY";
    
    public static final String REASONS_POSTING = "REASONS_POSTING";
        
        
    public static final String REQUIRED_CONSTITUENCIES = "REQUIRED_CONSTITUENCIES";    
    public static final String REMAINING_CONSTITUENCIES = "REMAINING_CONSTITUENCIES";    
    public static final String LATEST_CONSTITUENCIES = "LATEST_CONSTITUENCIES";
    public static final String ALL_PARTIES = "ALL PARTIES";
    
    public static final String PROFILE_PIC = "profiles";
    public static final String COVER_PIC = "coverImage";
    public static final String PROBLEM_PIC = "problems";
    public static final String CADRE_IMAGES = "cadre_images";
    
    public static final String ELECTION_RESULTS = "RESULTS";
    public static final String ELECTION_NOMINATIONS = "NOMINATIONS";
    
    
    // Fror Mailing
    public static final String LOCALFROMEMAILID = "itgrids.portal@gmail.com";//"partyanalyst04@gmail.com";
    public static final String SERVER = "server";
    public static final String LOCALHOST = "localhost";
    public static final String HOST = "smtp.gmail.com";
    public static final String PORT = "465";
    public static final String FROMEMAILID = "info@partyanalyst.com";
    public static final String TOEMAILID = "kripton514@gmail.com";
    public static final String PASSWORD="itgrids$678";//"ITGRIDS123";
    public static final String SUBJECT = "User Requirements From PartyAnalyst";
    public static final String CADRE_ROLE_ASSIGN_PROBLEMS = "Assign Problems";
    public static final String YES = "Yes";
    public static final String NO = "No";
    
    public static final String PARTYANALYST_SITE = "partyanalyst.com";
    public static final String FILE_SEPARATOR = "file.separator";
    public static final String UPLOADED_FILES = "uploaded_files";
    
    public static final String PHOTO_GALLARY ="Photo Gallary";
    public static final String NEWS_GALLARY ="News Gallary";
    public static final String DEVELOPEMENT_ACTIVITIES = "Developement Activities";
    public static final String VIDEO_GALLARY = "Video Gallary";
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_ZONE_INDIA = "Asia/Calcutta"; 
    public static final String CUSTOM_JSP_PAGES_PATH = "custom_jsp_pages";
    
    public static final String MALE = "M";
    public static final String FEMALE = "F";
    public static final String PUBLIC = "Public";
    public static final String PRIVATE = "Private";
    
    public static final String LEAD = "Lead";
    public static final String WON = "Won";
    
    public static final String CANDIDATE_MEG = "candidateMessage";
    public static final String PARTY_MEG = "PartyMessage";
    public static final String SPECIAL_PAGE = "Special Page";
    public static final String CANDIDATE_PAGE = "Candidate Page";
    public static final String PARTY_PAGE = "Party Page";
    
    public static final String CABINET_MINISTER = "Cabinet Minister";
    public static final String MINISTER_OF_STATE = "Ministers of State";
    public static final String MINISTER_OF_STATE_WITH_INDEPENDENT_CHARGE = "Ministers of State with Independent Charge";
    public static final String CHIEF_MINISTER = "Chief Minister";
    public static final String DEPUTY_CHIEF_MINISTER = "Deputy Chief Minister";
    public static final String PRIME_MINISTER = "Prime Minister";
    public static final String LOGIN = "Login";
	public static final String LOGOUT = "Logout";
	public static final String PROBLEM_FILES = "Problem_Files";
	
	//public static final String ADMIN_USERNAME_FOR_SMS = "partyanalyst";
	public static final String ADMIN_USERNAME_FOR_SMS = "koffeemedia";
	//public static final String ADMIN_PASSWORD_FOR_SMS = "dakaV@ram";
	public static final String ADMIN_PASSWORD_FOR_SMS = "9885410393";
	
	public static final String ADMIN_SENDERID_FOR_SMS = "16242";
	
	public static final String ADMIN_USERNAME_FOR_SMS_FOR_OTP = "Koffeemediaotp";
	public static final String ADMIN_PASSWORD_FOR_SMS_FOR_OTP = "otp@account";
	
	public static final String DEFAULT_MAIL_SERVER = "server";
	public static final String DEFAULT_SCHEDULER_SEVER = "localhost";
	public static final String DEFAULT_SCHEDULER_UPDATES_SEVER = "localhost";
	
	public static final String Ex_MLA="Ex MLA";
	public static final String Ex_MP="Ex MP";
	public static final String Contested_MLA="Contested for MLA";
	public static final String Contested_MP="Contested for MP";
	
	public static final String PROBLEM_CLASSIFICATION_SOCIAL="SOCIAL";
	public static final String PROBLEM_CLASSIFICATION_ECONOMICAL="ECONOMICAL";
	public static final String PROBLEM_CLASSIFICATION_PERSONAL="PERSONAL";
	public static final String MODIFIED = "MODIFIED";
	public static final String DELETED = "DELETED";
	public static final String BOTH ="BOTH";
	public static final String STATUS_ADDED = "Added";
	public static final String STATUS_DELETED = "Deleted";
	public static final String STATUS_MOVED = "Moved";
	public static final String STATUS_RELOCATED = "Relocated";
			
	public static final int MAX_PROBLES = 8;
	
	public static final String STARTING_WITH_NAME ="StartingWith";
	
	public static final String EXACT_MATCH_NAME ="ExactMatch";
	
	public static final String ANY_WHERE_IN_NAME ="AnyWhereInName";
	
	public static final String ALL_CADRES = "AllCadres";
	
	public static final String CADRE_REGISTER_FROM_ONLINE = "RegisteredFromOnline";
	
	public static final String CADRE_REGISTERED_BY_USER = "RegisteredByUser";
	
	public static final String VIDEO  = "video";
	
	public static final String WORKING_STATUS_WORKING = "Working";
	public static final String WORKING_STATUS_COMPLETED = "Completed";
	
	public static final Long TWITTER_ID = 1l;
	
	public static final Long FACEBOOK_ID = 2l;
	
	//constants for thumbnails
	public static final String SMALL  = "Small";
	public static final String MEDIUM  = "Medium";
	public static final String LARGE  = "Large";
	
	public static final int SMALL_WIDTH   = 120;
	public static final int SMALL_HEIGHT  = 90;
	public static final int MEDIUM_WIDTH  = 150;
	public static final int MEDIUM_HEIGHT = 110;
	public static final int LARGE_WIDTH   = 320;
	public static final int LARGE_HEIGHT  = 240;
	
	/*public static final long AGE18  = 18l;
	public static final long AGE22  = 22l;
	public static final long AGE23  = 23l;
	public static final long AGE30  = 30l;
	public static final long AGE31  = 31l;
	public static final long AGE45  = 45l;
	public static final long AGE46  = 46l;
	public static final long AGE60  = 60l;
	public static final long AGE61  = 61l;
	public static final long AGE160  = 160l;*/
	
	
	public static final long AGE18  = 18l;
	public static final long AGE22  = 22l;
	public static final long AGE23  = 26l;
	public static final long AGE30  = 35l;
	public static final long AGE31  = 36l;
	public static final long AGE45  = 45l;
	public static final long AGE46  = 46l;
	public static final long AGE60  = 60l;
	public static final long AGE61  = 61l;
	public static final long AGE160  = 160l;
	
	public static final long AGE25 = 25l;
	
	public static final String YOUNGER_VOTERS = "Young Voters";
	
	public static final String HAMLETLOCALAREA = "hamletLocalArea";
	
	public static final long CONSTITUENCY_REPORT_LEVEL_ID = 1L;
	public static final long MANDAL_REPORT_LEVEL_ID = 2L;
	public static final long PANCHAYAT_REPORT_LEVEL_ID = 3L;
	public static final long BOOTH_REPORT_LEVEL_ID = 4L;
	public static final long LOCALELECTIONBODY_REPORT_LEVEL_ID = 5L;
	public static final long WARD_REPORT_LEVEL_ID = 6L;
	
	public static final String PUBLICATION = "Publication";
	public static final String ELECTION = "Election";
	
	public static final String GALLARY_PDF_FILES= "gallary_pdf_files";
	/*public static final String AGE18to22  = "18-25";
	public static final String AGE23to30  = "26-35";
	public static final String AGE31to45  = "36-45";
	public static final String AGE46to60 = "46-60";
	public static final String Above60  = "Above60";*/

	public static final String AGE18to22  = "18-25";
	public static final String AGE23to30  = "26-35";
	public static final String AGE31to45  = "36-45";
	public static final String AGE46to60 = "46-60";
	public static final String Above60  = "Above60";
	
	public static final String AGE18to25 = "18-25";
	
	public static final long YOUNG_VOTERS_AGE_FROM = 18L;
	public static final long YOUNG_VOTERS_AGE_TO = 22L;
	public static final String YOUNG_VOTERS = "Young Voters";
	public static final String YOUNG_VOTERS_AGE_RANGE = "18-22";
	
	public final static String ASSEMBLY_CONSTITUENCY_TYPE = "Assembly";
	public final static String PARLIAMENT_CONSTITUENCY_TYPE = "Parliament";
	
	public final static String CUSTOMWARD = "customWard";
	public final static String GHMC = "Greater Municipal Corp";
	public final static String MUNCIPALITYWARDS = "muncipalityWards";
	public final static String LOCAL_ELECTION_BODY_BOOTHS = "localElectionBodyBooths";
	
	
	public static final long MPTC_ELCTION_TYPE_ID = 3L;
	public static final long ZPTC_ELCTION_TYPE_ID = 4L;
	
	public final static String AREA_TYPE_URBAN = "URBAN";
	public final static String AREA_TYPE_RURAL = "RURAL";
	public final static String AREA_TYPE_RURAL_URBAN = "RURAL-URBAN";
	public final static String MULTIPLE_ACCESS = "multipleAccess";
	
	public final static String LOCAL_HOST = "localhost";
	public final static String PROJECT_TYPE = "PARTY";

	/*
	 * tdpserver for tdp project
	 * partyanalyst.com for live server
	 * localhost for development environment
    */	
    //public final static String DEPLOYED_HOST = "tdpserver";
    public final static String DEPLOYED_HOST = "localhost";
    public final static String DEPLOYED_TEMP_HOST = "tdpserver";
    public final static Double GOOD = 40.00;
    public final static Double VERY_GOOD = 20.00;
    public final static Double BAD = 50.00;
    public final static Double VERY_BAD = 60.00;
    public final static Double AVERAGE = 45.00;
    
    public final static int MAX_LEVEL = 3;
   
    public final static Double LOW_VOTING_PERCENTAGE_IN_STRONG_LOCATIONS = 80.00;
    public final static Double HIGH_VOTING_PERCENTAGE_IN_WEEK_LOCATIONS = 80.00;
    
    public final static long MIN_ADDED_VOTERS = 50l;
    
    public final static String PRESENT_ASSEMBLY_ELECTION_ID = "38";
    public final static String PREVIOUS_ASSEMBLY_ELECTION_ID = "3";
    
    
    /* VOICE SMS START*/
    public static final String VERIFICATION_NUMBER_NOT_EXIST = "Verification Number Not Exist";
    public static final String ERROR_IN_VOICE_SMS = "Error Occureds While Sending Voice Sms.Please Try again.....";
    public static final String VOICE_SMS_SUCCESSFULLY_SENT = "Successfully Sent..";
    public static final String VOICE_SMS_USER_NAME = "karthik1";
    public static final String VOICE_SMS_PASS_WORD = "184314";
    /* VOICE SMS END*/
    
    public static final String VOICE_SMS_TYPE = "Voice Sms";
    public static final String TEXT_SMS_TYPE = "Text Sms";
    
    public static final String DEMO_REQUEST_ACTIONS = "Email,Phone Call,SMS";
    
    public static final String Hamlet_Booth = "HamletBooth";
    
    
    /*SMS COUNTRY VOICE SMS START*/
    public static final String VOICE_SMS_API_KEY = "Nj8uWwjyzEORIuAAKCVG";
    public static final String VOICE_SMS_ACCESS_KEY ="koIDAaXxaZEfQ6EAPwyLaOuk4n574CcEBNKdBWha";
    /*SMS COUNTRY VOICE SMS END*/
    
    public static final String LOCALITY = "Locality";
    
    public static final String ADMINGROUPMOBILE = "9676696760,9246837788,9246827788,9966542524";
    public static final String LIVE_VOICE_RECORDINGS_URL = "http://www.partyanalyst.com/voice_recordings/";
    public static final String MOBILE_APP_USER_TYPE = "SuperAdmin";
    
    public static final String Get_Base_URL = "Get_Base_URL";
    public static final String App_Authorization = "App_Authorization";
    public static final String Request_For_Forget_Pwd_Access_Key = "Request_For_Forget_Pwd_Access_Key";
    public static final String Validate_User_Access_Key = "Validate_User_Access_Key";
    public static final String Get_User_Voice_Recording_Files = "Get_User_Voice_Recording_Files";
    public static final String Send_Voice_Sms = "Send_Voice_Sms";
    public static final String Send_Text_Sms = "Send_Text_Sms";
    public static final String Authorisation_Access_Key = "Authorisation_Access_Key";
    
    public static final Long HOUSE_HOLD_VOTER_EDUCATION=8l;
    public static final Long HOUSE_HOLD_VOTER_OCCUPATION=7l;
    public static final Long HOUSE_HOLD_VOTER_SOCIAL_POSITIONS=9l;
    
    public static final Long STRATAGIC_REPORT_PUBLICATION_DATE_ID=10l;
    public static final Long TDP_PARTY_ID=872l;
    public static final Long INDEPENDENT_ID=366l;
    public static final Long INC_PARTY_ID=362L;

    //public static final String STATIC_CONTENT_FOLDER_URL = "C:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\PartyAnalyst\\";
    //public static final String STATIC_CONTENT_FOLDER_URL = "/app/static_content/";
    // public static final String STATIC_CONTENT_FOLDER_URL = "D:\\Tomcat 6.0\\webapps\\PartyAnalyst";
    
    
    //public static final String STATIC_CONTENT_FOLDER_URL = "/app/static_content/";
    public static final String STATIC_CONTENT_FOLDER_URL = "D:/Tomcat 6.0/webapps/PartyAnalyst/";
    public static final String CADRE_REG_AMOUNT_FILES_FOLDER = "cadre_reg_amount_files";
    
    public static final String STATIC_PARTIESFOR_DELIMATION = "'INC','PRP','TDP','TRS,Others'";
    
    public static final String pdfLocalPath="C:\\Users\\sasi\\Desktop\\sample.pdf";
    public static final String IMAGE="C:\\pdfs\\const\\back\\indeximage.jpg";
    
    public static final String SECKEYFAC ="PBKDF2WithHmacSHA1";
	public static final String SECRAN="SHA1PRNG";
	public static final String  WEBIP="http://192.168.3.73:8080/Survey";
    public static final String NORMAL="NORMAL";
    public static final String FORWARD="FORWARD";
    public static final String RESEND="RESEND";
   // public static final String DISTRICT_IDS ="1";
    public static int censusYear = 2011;
    public static final String SMS_APP_URL = "http://localhost:8080/Survey";
    
    public static final Long PREV_PARLIAMENT_ELECTION_ID = 17l;
    public static final Long PRES_PARLIAMENT_ELECTION_ID = 260l;
    
    
    public static final Long DATA_COLLECTOR_TYPE_ID = 1L;
    public static final Long VERIFIER_TYPE_ID = 2L;
    
    public static final Long VOTER_DATA_PUBLICATION_ID = 22L;
    public static final Long ELECTION_SCOPE_ID = 2L;
    
    public static final Long OTHER_STATE_PUBLICATION_ID = 13L;
    public static final Long DISTRICT_SCOPE_ID = 3L;
    public static final Long CONSTITUENCY_SCOPE_ID = 4L;
    public static final Long PARLIAMENT_CONSTITUENCY_SCOPE_ID = 10L;
    public static final Long TEHSIL_SCOPE_ID = 5L;
    public static final Long BOOTH_SCOPE_ID = 9L;
    public static final Long MUNICIPAL_CORP_GMC_SCOPE_ID = 7L;
    public static final Long VILLAGE_SCOPE_ID = 6L;
    public static final Long WARD_SCOPE_ID = 8L;
    
    public static final Long DATA_COLLECTOR_ROLE_ID = 1L;
    public static final Long VERIFIER_ROLE_ID = 4L;
    public static final Long THIRD_PARTY_ROLE_ID = 10L;
    
    public static final Long BOOTH_PROCESS_DC_STATUS_ID =1L;
    public static final Long BOOTH_COMPLETED_DC_STATUS_ID =2L;
    public static final Long BOOTH_COMPLETED_WM_STATUS_ID = 3L;
    public static final Long VERIFICATION_PROCESS_STATUS_ID =4L;
    public static final Long VERIFICATION_COMPLETD_STATUS_ID =5L;
  
    public static final Long HH_KUPPAM_PUBLICATION_ID = 10L;
    
    public enum  MatchTypes{
    	PExactMatch,PSoundexMatch,CExactMatch,CsoundexMatch
    }
    
    
    public static final Long DC_PROCESS_STATUS_ID =1L;
    public static final Long DC_COMPLETED_STATUS_ID =2L;
    public static final Long WM_COMPLETED_STATUS_ID = 3L;
    public static final Long DV_PROCESS_STATUS_ID =4L;
    public static final Long DV_COMPLETED_STATUS_ID =5L;
    public static final Long TP_READY_STATUS_ID =6L;
    public static final Long TP_PROCESS_STATUS_ID =7L;
    public static final Long TP_COMPLETED_STATUS_ID =8L;
    public static final Long TP_WM_PROCESS_STATUS_ID =9L;
    public static final Long TP_WM_COMPLETED_STATUS_ID =10L;
    public static final Long READY_FOR_REVIEW =11L;
    
    public static final Long SIGN_OFF_STATUS_ID = 1L;
    public static final Long QUERY_STATUS_ID = 2L;
    
    public static final Long KPM_AGE1_MIN = 0L;
    public static final Long KPM_AGE1_MAX = 15L;
    public static final Long KPM_AGE2_MIN = 16L;
    public static final Long KPM_AGE2_MAX = 21L;
    public static final Long KPM_AGE3_MIN = 22L;
    public static final Long KPM_AGE3_MAX = 120L;
    
    public static final Long CADRE_ENROLLMENT_NUMBER = 2014l;
    public static final Long RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER = 2016l;
    public static final Long AFFLIATED_TDP_MEMBER_TYPE_ID = 2l;
    public static final Long CTP_CASTE_INSERT_TYPE = 5l;
    public static final String DATE_AND_TIME_FORMAT_24HRS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String CADRE_MEMBERSHIPCARD_DISPATCHER = "CADRE_MEMBERSHIPCARD_DISPATCHER";
    public static final String CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP = "CADRE_MEMBERSHIPCARD_DISPATCHER_GROUP";
    public static final String GHMC_CADRE_MEGA_DRIVE_USER = "GHMC_CADRE_MEGA_DRIVE_USER";
    public static final String GHMC_CADRE_MEGA_DRIVE_USER_GROUP = "GHMC_CADRE_MEGA_DRIVE_USER_GROUP";
 

    public static final int AP_VOTERS_2014 = 36669181;
    public static final int TG_VOTERS_2014 = 28145591;
    
    public static final int TARGET_CADRE_AP = 2000000;
    public static final int TARGET_CADRE_TG = 1000000;
    
    public static final String CADRE_2014_START_DATE = "2014-11-03";
    public static final String CADRE_2014_LAST_DATE = "2014-12-02";
    
    public static final String AP_CADRE_2014_END_DATE = "2014-12-23 16:00:00";
    public static final String TG_CADRE_2014_END_DATE = "2014-12-23 16:00:00";
    
    public static final String AP_AFFLIATED_CADRE_END_DATE = "2014-02-04 16:00:00";
    public static final String TG_AFFLIATED_CADRE_END_DATE = "2014-02-16 16:00:00";
    
    public static final String MINORITY_CASTE_IDS = "292,301,430";
    public static final boolean ENABLE_CADRE_LOGS = false;
    public static final boolean ENABLE_LOGS_SAVE = true;
    public static final Long CADREPERCENTAGE = 30l;
    public static final String[] excludeUrl ={"callCenterMembershipCardStatusAction","getCadreDetailsForCallCenterAction","s" +
    		"aveCallCenterFeedbackAction","getFeedbackDataAction","tdpCadreVolunteerAction","tdpCadreVolunteerRegistrationAction","saveMessageInfo","publicMembershipCardStatusAction","sampleWebServiceCalls","cadreMissedCallCampaignUrl","insertVotersCasteAndPartyDataToIntermediateTablesAction","displayDashboardCommentsAction","savingDashboardCommentAction","deleteDashBoardcommentsAction",
    		 "coreDashboardAction1","updateTransactionTrackingDtalsAction","tdpMembershipPerformanceForAPAction.action","tdpMembershipPerformanceForTSAction.action",
    		 "getConstituencyWiseTodayAndOverAllCountsAction.action","getDistrictWiseTodayAndOverAllCountsAction.action","coreDashboardNewAction.action",
    		 "tdpMembershipPerformanceAction","getConstituencyWiseTodayAndOverAllCountsNewAction","getDistrictWiseTodayAndOverAllCountsNewAction",
    		 "govtLoginAction"};
    public static final String CADRE_SMS_STATUS_FILES = "cadre_sms_status_files";
    public static final String EMPTY_STRING = "";
    
    //Dynamic Keys
    
    public static final String ON = "ON";
    public static final String OFF = "OFF";
    public static final String SAVE_VERIFY_DATA = "SAVE_VERIFY_DATA";
    public static final String RESTRICT_TAB_DATA_SYNC = "RESTRICT_TAB_DATA_SYNC";
    
    public static final String Received = "Received";
    public static final String NotReceived = "NotReceived";
    public static final String NotRegistered = "NotRegistered";
    public static final String Response = "Response";
    public static final String IVR_SUCCESS_GROUP = "NORMAL_CLEARING";
    public static final String IVR_ERROR_GROUP = "USER_BUSY,NO_ANSWER";
    public static final Long  received =1l;
    public static final Long  notReceived = 2l;
    public static final Long notMember = 3l;
    public static final String USER_BUSY = "USER_BUSY";
    public static final String NO_ANSWER = "NO_ANSWER";
    public static final String NORMAL_CLEARING = "NORMAL_CLEARING";
    public static final String SWITCH_CONGESTION = "SWITCH_CONGESTION";
    public static final String NO_USER_RESPONSE = "NO_USER_RESPONSE";
    public static final String CALL_REJECTED = "CALL_REJECTED";
    public static final String NORMAL_CIRCUIT_CONGESTION = "NORMAL_CIRCUIT_CONGESTION";   
    public static final String NETWORK_OUT_OF_ORDER = "NETWORK_OUT_OF_ORDER";
    public static final String DESTINATION_OUT_OF_ORDER = "DESTINATION_OUT_OF_ORDER";
    public static final String NORMAL_TEMPORARY_FAILURE = "NORMAL_TEMPORARY_FAILURE";
    public static final String UNALLOCATED_NUMBER = "UNALLOCATED_NUMBER";
    public static final String SUBSCRIBER_ABSENT = "SUBSCRIBER_ABSENT";
    public static final String INTERWORKING = "INTERWORKING";
    
    public static final Long  CURRENT_ENROLLMENT_ID =2l;
    public static final Long CADRE_ENROLLMENT_YEAR_FOROTHER_STATES = 2014l;
    
    // RabbitMQ Configurations
    public static final String RMQ_USERNAME = "itgrids";
    public static final String RMQ_PASSWORD = "1tGrids456";
    public static final String RMQ_VIRTUALHOST = "/";
    public static final String RMQ_HOST = "localhost";
    public static final Integer RMQ_PORT = 5672;
    
    public static final String RMQ_EXCHANGE = "event_attendee_ws";
    public static final String RMQ_QUEUE = "attendee_queue";
    public static final String ROUTING_KEY = "event_Ws_184375";
    
    public static final String ATTENDANCE_RMQ_EXCHANGE = "attendance_ws";
    public static final String ATTENDANCE_RMQ_QUEUE = "attendance_queue";
    public static final String ATTENDANCE_ROUTING_KEY = "attendacen_Ws_747294";
    
    public static final String 	SURVEY_WEBSERVICE_USERNAME = "itgrids";
    public static final String  SURVEY_WEBSERVICE_PASSWORD = "servey@android";
    public static final String PARTY_OFFICE_USER_IDS = "PARTY OFFICE,3930";
    public static final String MAHANADU_USER_IDS = "MAHANADU,7287,7288,7289,7290,7291,7292";
    
    public static final String INVITED ="Invited";
    public static final String LATER="Later";
    public static final String INTERESTED="Interested";
    public static final String NOTINTERESTED="Not Interested";
    public static final String CALLBACK_BUSY="Call Back - Busy";
    public static final String CALLBACK_CONFIRM_LATER="Call Back - Confirm Later";
    public static final String WRONG_MOBILE_NO="Wrong Mobile No";
    public static final String INVALID_MOBILE_NO="Invalid Mobile No";
    public static final String CONFIRMED="Confirmed";
    public static final String CALL_ANSWERED="Call Answered";
    public static final String SWITCHOFF="SwitchOff";
    public static final String TRAINING_USER_BUSY="User Busy";
    public static final String INVITATION="Invitation";
    public static final String CONFIRMATION="Confirmation";
    public static final String BATCH_CHANGE="Batch Change";
    
   //public static final String LOCAL_FILES_FOLDER =	"D:/tomcat 6.0/webapps/DocFiles";
    //public static final String LOCAL_FILES =	"http://localhost:8080/DocFiles";
    
    public static final String LOCAL_FILES_FOLDER =	"/app/apache-tomcat-6.0.37/webapps/DocFiles";
    public static final String LOCAL_FILES =	"http://mytdp.com/DocFiles";
   
    public static final Long  CONSTITUENCY_COMMITTEE_LEVEL_ID =4l;
    public static final Long  MANDAL_COMMITTEE_LEVEL_ID =5l;
    public static final Long  VILLAGE_COMMITTEE_LEVEL_ID =6l;
    public static final Long  TOWN_COMMITTEE_LEVEL_ID =7l;
    public static final Long  WARD_COMMITTEE_LEVEL_ID =8l;
    public static final Long  DIVISION_COMMITTEE_LEVEL_ID =9l;
    public static final Long  STATE_COMMITTEE_LEVEL_ID =10l;
    public static final Long  DISTRICT_COMMITTEE_LEVEL_ID =11l;
    
    public static final Long  STATE_PARTY_MEETING_LEVEL_ID =1l;
    public static final Long  DISTRICT_PARTY_MEETING_LEVEL_ID =2l;
    public static final Long  CONSTITUENCY_PARTY_MEETING_LEVEL_ID =3l;
    public static final Long  MANDAL_PARTY_MEETING_LEVEL_ID =4l;
    public static final Long  TOWN_PARTY_MEETING_LEVEL_ID =5l;
    public static final Long  DIVISION_PARTY_MEETING_LEVEL_ID =6l;
    public static final Long  VILLAGE_PARTY_MEETING_LEVEL_ID =7l;
    public static final Long  WARD_PARTY_MEETING_LEVEL_ID =8l;
   
    public static final String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};  
    public static final Long MONTHLY_VILLAGEorWARD_MEETING_ID=14L;
    public static final Long MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID=15L;
    public static final Long MONTHLY_CONSTITUENCY_MEETING_ID=3L;
    public static final Long MONTHLY_STATE_MEETING_ID=1L;
    public static final Long MONTHLY_DISTRICT_MEETING_ID=2L;

	 public static final String HEALTH_CARD_FOLDER = "tdp_cadre_health_cards";
	 public static final String CADRE_FEEDBACK_DOCUMENT = "cadre_feedback_document";
    public static final String[] ACTIVITY_REPORT_FIELDS ={"PLANNED","IVR COVERED","IVR COVERED %","IVR NOT PLANNED","IVR TOTAL","INFO CELL COVERED","INFO CELL COVERED %","INFO CELL NOT PLANNED","INFO CELL TOTAL","WHATSAPP IMAGES COVERED","WHATSAPP IMAGES COVERED %","NO OF WHATSAPP IMAGES RECIEVED"};
    public static final String ACTIVITY_DOCUMENTS ="activity_documents";
    public static final String ACTIVITY_DOCUMENT_FOLDER = "activity_documents";
    public static final String ACTIVITY_DOCUMENT_UPLOAD = "activity_documents_upload";
    public static final Long	ACTIVITY_MAX_FILES_COUNT = 50L;
    public static final Long ACTIVITY_REQUIRED_ATTRIBUTE_IDS = 2l;
    public static final Long LATEST_PUBLICATION_DATE_ID = 17L;//17L;//12L //10L;
    public static final String ITGRIDS_USERNAME_FOR_SMS = "ITGridsapi";
    public static final String ITGRIDS_PASSWORD_FOR_SMS = "03274254";
    
    public static final Long[] GHMC_UNDECIDED_VOTERS = {3L};
    public static final Long[] GHMC_INCLINED_VOTERS ={4L,5L};
    public static final Long[] GHMC_OTHER_VOTERS ={1L,2L};
    public static final Long[] GHMC_NONOPTED_VOTERS ={0L};
    public static final Long VOTER_PUBLICATION_ID = 12L;
    public static final Long AFFILIATED_VOTER_PUBLICATION_ID = 22L;
    public static final Long UNIONS_REGISTRATION_YEAR = 2016L;
    
    public static final Long PUBLICATION_DATE_FOR_VOTER_IMG_PATH = 11L;
    public static final Long[] APPOINTMENT_STATUS_IDS = {4L,5L,6L};
	public static final Long[] TOWN_TYPE_IDS = {5L,6L};
	public static final Long[] DIVISION_TYPE_IDS = {7L};
	public static final Long CADRE_ENROLLMENT_YEAR = 2014L;
	public static final Long MANDAL_LEVEL_ID = 5L; 
	public static final Long TOWN_LEVEL_ID =7L; 
	public static final Long DIVISION_LEVEL_ID = 9L; 
	public static final Long VILLAGE_LEVEL_ID = 6L; 
	public static final Long WARD_LEVEL_ID = 8L; 
	public static final String TRAINING_CAMP_FEEDBACK_SMS_CONTENT = "\u0c36\u0c3f\u0c15\u0c4d\u0c37\u0c23\u0c3e \u0c36\u0c3f\u0c2d\u0c3f\u0c30\u0c02\u0c32\u0c4b \u0c2e\u0c40\u0c30\u0c41 \u0c07\u0c1a\u0c4d\u0c1a\u0c3f\u0c28 \u0c35\u0c3f\u0c28\u0c24\u0c3f/\u0c05\u0c2d\u0c3f\u0c2a\u0c4d\u0c30\u0c3e\u0c2f\u0c02 \u0c28\u0c3e \u0c26\u0c43\u0c37\u0c4d\u0c1f\u0c3f\u0c15\u0c3f \u0c35\u0c1a\u0c4d\u0c1a\u0c3f\u0c02\u0c26\u0c3f. \u0c27\u0c28\u0c4d\u0c2f\u0c35\u0c3e\u0c26\u0c2e\u0c41\u0c32\u0c41 -\u0c2e\u0c40 \u0c28\u0c3e\u0c30\u0c3e \u0c32\u0c4b\u0c15\u0c47\u0c37\u0c4d";//"Sikshana sibiramlo meeru ichina vinathi/ abhiprayam naa drustiki vachindi. Dhanyavadamulu - mee	Nara lokesh";

	public static final Long FIXED_APPOINTMENT_STATUS_ID=2l;
	public static final Long ATTENDED_APPOINTMENT_STATUS_ID=3l;
	public static final Long WAITING_APPOINTMENT_STATUS_ID=1l;

	public static final Long APPOINTMENT_STATUS_WAITING=1L;
	public static final Long APPOINTMENT_STATUS_LABELED=2L;
	public static final Long APPOINTMENT_STATUS_FIXED=3L;
	public static final Long APPOINTMENT_STATUS_ATTENDED=4L;
	public static final Long APPOINTMENT_STATUS_NOTATTENDED=10L;
	public static final Long APPOINTMENT_STATUS_RESCHEDULED=8L;
	public static final Long APPOINTMENT_STATUS_CANCELLED=5L;
	public static final Long APPOINTMENT_STATUS_TENTATIVE=8L;
	public static final Long APPOINTMENT_STATUS_VOID=9L;
	public static final Integer APPOINTMENT_HISTORY_MAX_RESULT =5;
	
	public static final Long[] APPOINTMENT_STATUS_LABELED_LIST={2L,3L,9L};
	
	public static final Long[] APPOINTMENT_STATUS_SCHEDULED_LIST={3L,4L,5L};
	public static final Long[] APPOINTMENT_STATUS_WAITING_LIST={1L,2L,6L,7L,8L};
	public static final String APPOINTMENT_IMAGES = "appointment_images";
	//SMS Module Constants
	
	public static final String SMS_MODULE_USER_GROUPS = "User Groups";
	public static final String SMS_MODULE_CADRE_MANAGEMENT = "Cadre Management";
	public static final String SMS_MODULE_INFLUENCING_PEOPLE = "Influencing People";
	public static final String SMS_MODULE_PROBLEM_MANAGEMENT = "Problem Managemet";
	public static final String SMS_MODULE_VOTER = "Voter";
	public static final String SMS_MODULE_GRIEVANCE = "Grievance";
	public static final String SMS_MODULE_TRAINING_CAMP_FEEDBACK = "Training Camp Feedback";
	public static final String SMS_MODULE_APPOINTMENT = "Appointment";
	
	public static final String STATIC_STR = "Static";
	public static final String DYNAMIC_STR = "Dynamic";
	public static final String SMS_AFFILIATED_GRADUATES_ENROLLMENT_MODULE = "Affiliated Graduates Enrollment";
    
	//NEW
	public static final Long[] APPOINTMENT_STATUS_CREATION_LIST={1L,2L,3L,5L,8L,10L};
	public static final Long[] REQUIRED_APPOINTMENTS_LIST={1L,2l,3L,4L,5L,6l,7L,8L,10L};
	public static final Long   APPOINTMENT_STATUS_WITHDRAWN=9L;
	public static final Long   APPOINTMENT_STATUS_APPROVED=2L;
	public static final Long  APPOINTMENT_STATUS_SCHEDULED=3L;
	//public static final Long[] TODAY_APPOINTMENTS_STATUS_LIST={2L,3L,4L,5L,8L,10L};
	public static final Long[] TODAY_APPOINTMENTS_STATUS_LIST={3L,4L,10L};
	public static final String[] LOGIN_SKIP_URLS_LIST = {"pushNotificationAction.action","notificationAction.action","unionSearchRegiststrationAction.action",
		 "getCadreDetailsAction.action","affiliatedGraduatesRegistrationAction.action","getAddressDetailsAction.action","getVoterWorkAddressDetailsAction.action",
		 "getAllRelationDetails.action","getCadreLevelsForCadreSearchAction.action","getConstituenciesForDistrictAction.action",
		 "getSubLevelLctnsForConstituencyAndMandal.action","rtcUnionRegistrationPage.action","getVoterDetailsByVoterCardNumberAction.action",
		 "generateOTPForMobileNumberAction.action","validateOTPAction.action","registrationSuccessAction.action",
		 "saveRegistrationFeedbackQueriesDetailsAction.action" ,"updateNotificationTypeAction.action","updateNotificationAction.action",
		 "notificationIsActiveStatusAction.action","getNotificationTypeDetailsStatsAction.action","getNotificationTypeDetailsAction.action",
		 "saveNotificationTypeTextAction.action","saveNotificationAction.action",
		 "genereateReportAction.action","retriveDebateDetailsAction.action","tdpMembershipPerformanceForAPAction.action","tdpMembershipPerformanceForTSAction.action",
		 "getConstituencyWiseTodayAndOverAllCountsAction.action","getDistrictWiseTodayAndOverAllCountsAction.action","coreDashboardNewAction.action",
		 "tdpMembershipPerformanceAction","getConstituencyWiseTodayAndOverAllCountsNewAction","getDistrictWiseTodayAndOverAllCountsNewAction"};
	
	public static final Long  APPOINTMENT_ACTION_STATUS_CHANGE = 1L;
	
	public static final String PAID_STATUS = "PAID";
	public static final String NOT_PAID_STATUS = "NOT PAID";
	public static final String NOT_REQUIRED = "NR";
	public static final Long[] APPOINTMENT_ALL_PENDING_STATUS={2L,10L,5L,8L};
	public static final Long[] PR_AC_DESG_IDS={2L,8L,10L,21L};
	public static final Long[] PR_PC_DESG_IDS={1L,9L,11L};
	public static final Long[] PR_MANDAL_DESG_IDS={3L,4L,5L,13L};
	public static final Long[] PR_STATE_DESG_IDS={6L,7L,12L,16L,22L,23L};
	public static final Long[] PR_DISTRICT_DESG_IDS={14L,15L};
	public static final Long[] PR_TOWN_DESG_IDS={17L,19L,20L};
	public static final Long[] PR_WARD_DESG_IDS={18L};
	//public static final String EMAIL_IMAGES_PATH = "D:/Tomcat/webapps/PartyAnalyst/images/TDP/";
	
	public static final Long APPOINTMENTS_PUBLICATION_ID = 12l;
	
	public static final Long TGNF_REGISTRATION_CADRE_TYPE_ID=5L;
	//public static final String TGNF_REGISTRATION_REDIRECTURL="http://localhost:8080/PartyAnalyst/registrationSuccessAction.action";//"file:///C:/Users/Administrator/Desktop/tngfRegistrationSuccess.html";//"http://telugudesam.org/emembership/tngfRegistrationSuccess.html";//
	public static final String TGNF_REGISTRATION_REDIRECTURL="http://telugudesam.org/emembership/tngfRegistrationSuccess.html";
	public static final String CADRE_ONLINE_REGISTRATION_REDIRECTURL="http://www.telugudesam.org/cadre-registration-portal/registration";
	public static final String TGNF_ENROLLMENT_AMOUNT="1";
	public static final String TGNF_ENROLLMENT_RANDOMNUMBERCODE="CADRE_2016";
	public static final String CADRE_2016_ONLINE_REGISTRATION="CADRE_2016";
	public static final String TGNF_ENROLLMENT_MERCHANT_ID="M_tdpcbn_2144";
	public static final String TGNF_ENROLLMENT_WORKING_KEY="0kag9s53yyi788y3prdk8ydhf8glfj9e";
	
	public static final String MD5_PRIMARY_KEY ="CADRE2016!tGrid$";
	
	
	public static final Long[] APPOINTMENT_WAITING_LIST= {1L,2L,5L,6L,7L,8L,9L};
	public static final Long[] APPOINTMENT_SCHEDULED_LIST= {3L,4L,10L};
	public static final String GRIEVANCE_REQUESTS_STATUS_LIST ="Not Verified,In Progress,Not Possible,Not Eligible,approved,completed";
	
	public static final String EVENT_PRE_ENTRY = "Pre Entry";
	public static final String IS_VISIBLE = "Y";
	
	/*//LIVE
	public static final String GHOST_SCRIPT_PATH = "/app/static_content/Ghost_Script/gs-915-linux_x86_64";*/
	
	//LOCAL
	public static final String GHOST_SCRIPT_PATH = "C:\\Program Files (x86)\\gs\\gs9.14\\bin\\gswin32c.exe";
	
	public static final String MAHANADU_IMAGES_RETRIEVE_PATH = "http://mytdp.com/images/mahanadu_images";
	public static final String MAHANADU_IMAGES_2016 = "mahanadu_images";
	
	public static final String GOOGLE_SERVER_KEY = "AAAAZP0HrS8:APA91bGtOcN7g9tChOaiIoisxXeZ8KeAUYvv0YDdomghUbGG5UrHve3aRAgHSmedbJVNhNn7ivgIixcIsj2A-xWPyg3aUouuIQ36FBKIa0Mp4PvGcFckAkCZ0wIYyVaOqXMatmgNo-gv";
	public static final String GCM_SERVER_STATIC_MESSAGE = "Please Update Notifications";
	
	public static final String VOTER_IMG_FOLDER_PATH = "voter_images";
	public static final String STATIC_STATE_IDS= "1,36";
	
	public static final Long[] PUBLIC_REPR_DESIGNATION_IDS= {1L,16L,2L,12L,4L,5L,3L,21L,16L,10L}; 
	public static final Long[] COMMITTEE_LEVEL_IDS= {12L,10L}; 
	public static final Long[] COMMITTEE_ROLE_IDS= {1L}; 
	
	public static final String NEW_MINORITY_CASTE_IDS = "301,302,303,120,430";
	public static final Long  ALERT_ACTION_STATUS_CHANGE = 1L;
	public static final Long  ALERT_ADDED_COMMENT = 2L;
	

	public static final String[] ATTENDANCE_REPORT_EMAIL_GROUP = {"a.dakavaram@itgrids.com,a.dakavaram@gmail.com,kamalakar@itgrids.com"};
	public static final String EMAIL_USERNAME = "info@itgrids.com";
	public static final String EMAIL_PASSWORD = "itgrids$678";
	public static final String[] NOMINATED_POST_APPLICATION_STATUSES = {"TOTAL AVAILABLE","APPLICATIONS NOT RECIEVED",
		 "READY TO SHORT LIST","RUNNING","READY FOR FINAL REVIEW","FINALIZED","GO ISSUED / COMPLETED","TOTAL"};
	
	public static final String SHORTLISTED_STATUS ="Shortlisted";
	public static final String RUNNING_NOMINATED_POSTS_STATUS_IDS ="1";
	public static final String NOMINATED_POST_DOCUMENTS ="nominated_post_documents";
	public static final String TOUR_DOCUMENTS ="tour_documents";  
	public static final String GO_DOCUMENTS ="GO_documents";
	
	public static final String NOMINATED_APPLIED_STATUS ="'Applied'";
	public static final String NOT_CADRE_IMAGES = "not_cadre_images";
	
    //core dashboard
	public static final Long COUNTRY_LEVEl_ACCESS_ID    = 1l; 
	public static final Long STATE_LEVEl_ACCESS_ID      = 2l; 
	public static final Long DISTRICT_LEVEl_ACCESS_ID   = 3l; 
	public static final Long PARLIAMENT_LEVEl_ACCESS_ID = 4l;
	public static final Long ASSEMBLY_LEVEl_ACCESS_ID   = 5l;
	public static final Long MANDAL_LEVEl_ID   = 6l; 
	public static final Long MUNCIPALITY_LEVEl_ID  = 7l; 
	public static final Long VILLAGE_LEVEl_ID  = 8l; 
	public static final Long WARD_LEVEl_ID     = 9l;
	
	public static final Long[] STATE_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {10l,11l ,5l,7l,9l, 6l,8l};
	public static final Long[] DISTRICT_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {11l ,5l,7l,9l, 6l,8l}; 
	public static final Long[] CONSTITUENCY_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
	public static final Long[] MANDAL_ACCESS_REQUIED_COMMITTEE_LEVEl_IDS = {5l,7l,9l, 6l,8l};
	
	public static final Long COUNTRY_USER_TYPE_ID    = 1l;
	public static final Long STATE_USER_TYPE_ID    = 2l;
	
	public static final String NOMINATED_POST_NOT_RUNNING_STATUS="1,5,6,7"; 
	public static final String NOMINATED_POST_REJECTED_STATUS_IDS="2,4,8";
	public static final String AP_NEW_DISTRICTS_IDS_LIST = "11,12,13,14,15,16,17,18,19,20,21,22,23,517";
	public static final Long[] AP_NEW_DISTRICTS_IDS = {11l,12l,13l,14l,15l,16l,17l,18l,19l,20l,21l,22l,23l,517l};
	public static final String TS_NEW_DISTRICTS_IDS_LIST = "1,2,3,4,5,6,7,8,9,10,518";
	public static final Long[] TS_NEW_DISTRICTS_IDS = {1l,2l,3l,4l,5l,6l,7l,8l,9l,10l,518l};
	public static final Long NOMINATED_APPLICATION_FINAL_REVIEW=6L;
	public static final Long NOMINATED_POST_FINAL_REVIEW=2L;
	public static final String NOMINATED_OPEN_POSTS_STATUS_IDS ="1,2";
	public static final Long rejectedInFinalized = 8l;
	public static final Long[] NOMINATED_SHORTLISTED_STATUS_IDS ={3l,5l,6l,7l};
	public static final String NOMINATED_POST_DASHBOARD_REJECTED_APPLICATION_STATUS="2,4,8,1";
	public static final String NOMINATED_POST_APPLICATION_STATUS="2,4,5,7";
	public static final String NOMINATED_POST_FINALIZED_GOISSUED_STATUS="3,4";
	//User Type Id
	public static final Long COUNTRY_TYPE_USER_ID=1l;
	public static final Long STATE_TYPE_USER_ID=2L;
	public static final Long GENERAL_SECRETARY_USER_TYPE_ID = 3l;
	public static final Long ORGANIZING_SECRETARY_USER_TYPE_ID = 4l;
	public static final Long DISTRICT_PRESIDENT_USER_TYPE_ID = 5l;
	public static final Long MP_USER_TYPE_ID = 6l;
	public static final Long MLA_USER_TYPE_ID = 7l;
	public static final Long CONSTITUENCY_USER_TYPE_ID = 8l;
	public static final Long CONSTITUENCY_INCHARGE_USER_TYPE_ID = 9l;
	public static final Long SECRETARY_USER_TYPE_ID = 11l;
	public static final Long INCHARGE_MINISTER_USER_TYPE_ID = 12l;
	public static final Long PARLIAMENT_INCHARGE_USER_TYPE_ID = 14l;
	
	//Debate Core
	
	public static final String CORE_DEBATE_ELIMINATED_PARTIES_AP = "1676,1677,1678,1887,662,514,268,72";
	public static final String CORE_DEBATE_ELIMINATED_PARTIES_TS = "1676,1677,1678,1887,662,514,268,1117";
	public static final String[] EXCLUDE_URL_LIST = {"callCenterMembershipCardStatusAction","getCadreDetailsForCallCenterAction","s" +
    		"aveCallCenterFeedbackAction","getFeedbackDataAction","tdpCadreVolunteerAction","tdpCadreVolunteerRegistrationAction","saveMessageInfo","publicMembershipCardStatusAction","sampleWebServiceCalls","cadreMissedCallCampaignUrl","insertVotersCasteAndPartyDataToIntermediateTablesAction","displayDashboardCommentsAction","savingDashboardCommentAction","deleteDashBoardcommentsAction",
   		 "coreDashboardAction1","updateTransactionTrackingDtalsAction","tdpMembershipPerformanceForAPAction.action","tdpMembershipPerformanceForTSAction.action",
   		 "getConstituencyWiseTodayAndOverAllCountsAction.action","getDistrictWiseTodayAndOverAllCountsAction.action","coreDashboardNewAction.action",
   		 "tdpMembershipPerformanceAction","getConstituencyWiseTodayAndOverAllCountsNewAction","getDistrictWiseTodayAndOverAllCountsNewAction",
   		 "getTrainingRecentTimeAction","coreDashboardNewAction","getLatestDebateAction","getApTsDistrictListAction" ,"getLoggedInUserStructureAction" ,
   		  "getPartyMeetingTypeByPartyMeetingMainTypeAction" ,"getCommitteesBasicCountReportAction","getTrainingCampBasicDetailsCntOverviewAction" ,
   		   "getPartyWiseTotalDebateDetailsAction" ,"getToursBasicOverviewCountDetailsAction","getDesigWiseMemberDtlsAction" ,"getTotalNewRenewalCadreStateWiseAction",
   		    "getTotalNewRenewalCadreStateWiseTSAction","getStateDtls","getStateDtlsTS","getAttendanceOverViewForPartyOfficeAction" ,"getAttendanceOverViewForPartyOfficeWiseAction",
   		     "getStateLevelCampAttendedDetails" ,"getPartyMeetingsMainTypeOverViewDataAction","getPartyMeetingBasicCountDetailsAction","coreDashboardNewAction"};
	
	//public static final String CADRE_REGISTRATION_URL = "http://www.mytdp.in/";
	//public static final String CADRE_REGISTRATION_URL = "http://localhost:8090/CadreReg-1.4.0.RELEASE/";
	public static final String CADRE_REGISTRATION_URL = "http://localhost:65535/CadreReg-1.4.0.RELEASE/";      
	
	public static final String MEMBERSHIP_DRIVE_PERFORMANCE_PARAMETERS[] = {"VERY GOOD-90-#196F3D","GOOD-80-#27AE60","OK-60-#A9DFBF","POOR-30-F5B7B1","VERY POOR-10-#FF5733"};//,"OTHERS-0-#FBFCFC"};
	public static final Long DAY_WISE_TARGET_REGISTRATIONS_COUNT = 7000L;
	public static final Long DAY_WISE_AP_TARGET_REGISTRATIONS_COUNT = 350000L;
	public static final Long DAY_WISE_TS_TARGET_REGISTRATIONS_COUNT = 350000L; 
	public static final Long CADRE_REGISTRATION_2016_PUBLICATION_ID = 22L;
	public static final Long  PRESENT_CADRE_ENROLLMENT_YEAR = 4L;
	public static final Long  PREVIOUS_CADRE_ENROLLMENT_YEAR = 3L;
	public static final int  DATE_WISE_CADRE_INTERMEDIATE_PUSH_DAYS = 2;
	
	//cadre image saving .
	//public static final String STATIC_CONTENT_FOLDER_PATH = "/app/static_content";
	public static final String STATIC_CONTENT_FOLDER_PATH = "D:/static_content";
	public static final String CADRE_DATA_SOURCE_TYPE_TAB = "TAB";
	public static final String CADRE_DATA_SOURCE_TYPE_WEB = "WEB";
	public static final String CADRE_DATA_SOURCE_TYPE_ONLINE = "ONLINE";
	public static final String STATUS_SUCCESS = "Success";
	public static final String STATUS_FAIL = "Fail";
	public static final String CADRE_IMAGE_TYPE_VOTER = "VOTER";
	public static final String CADRE_IMAGE_TYPE_CADRE = "CADRE";
	public static final String CADRE_IMAGE_TYPE_NEW = "NEW";
	public static final String VOTER_IMAGES = "voter_images";
	
	public static final String COURIER_AMOUNT="30";
	public static final Long CADRE_REGISTRATION_2016_DAYS=30L;
	
	public static final String CADRE_REGISTRATION_START_DATE = "01/11/2016";
	
	public static final String HYDERABAD_PARY_OFFICE = "HYD PARTY OFFICE";
	public static final String VIJAYAWADA_PARY_OFFICE = "VIJ PARTY OFFICE";
	public static final String LATEST_VIJ_PARTY_OFFICE_USER_IDS = "11252,11253,11254,11270,11271";
	public static final String LATEST_HYD_PARTY_OFFICE_USER_IDS = "11255,11256,11257,11258,11259";
	//TOUR SUB LEVEL
	public static final Long GENERAL_SECRETARY_SUB_LEVEL_DESIG_IDS[] = {4L,5L,7L,8L};
	public static final Long ORGANIZING_SECRETARY_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long DISTRICT_PRESIDENT_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long MP_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long SECRETARY_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long INCHARGE_MINISTER_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long PARLIAMENT_INCHARGE_SUB_LEVEL_DESIG_IDS[] = {7L,8L};
	public static final Long STATE_SUB_LEVEL_DESIG_IDS [] = {1L,2L,3L,4L,5L,6L,7L,8L,9L};
	
	public static final String CADRE_NEW_MINORITY_CASTE_IDS = "81,97,107,292,299,300,301";
	
	public static final Long CNP_VISHAKAPATTANAM_RURAL_DISTRICT_ID=334L;
	public static final Long CNP_MANCHERIAL_DISTRICT_ID=344L;  
	public static final String ALERT_PARTY_AND_OTHERS_TYPE_IDS="1,2";
	public static final String ALERT_PENDING_STATUS_IDS="1";
	public static final String ALERT_IMPACT_SCOPE_IDS="10,11";
	public static final String ALERT_EDITION_TYPE_IDS="1,2,3"; 
	
	public static final Integer[] specialCharsUnicodeIgnoreList = {8204,8205};
	
	public static final Long CARD_PRINT_STATUS_NOT_VERIFIED= 1L ;
	public static final Long CARD_PRINT_STATUS_VERIFICATION_PASSED= 2L ;
	public static final Long CARD_PRINT_STATUS_VERIFICATION_FAILED= 3L ;
	
	public final int  noOfRecordsUpdate = 1000;
	
	public static final String ALERTS_ATTACHMENTS = "alerts_attachments";
	public static final String ALERT_STATUS_ID="1,6,7";
	public static final String ALERT_ACTION_TYPE_ID = "1";
	
	public static final Long GOVT_CORE_DASHBOARD_ALERT_TYPE_ID = 2L;
	public static final String PARTY_MEETINGS ="party_meetings"; 
	public static final Long GOVT_TOUR_TYPE_ID = 2L;
	//ALERT MONITORING DASHBOARD
	public static final String GOVT_ALERT_CATEGORY_ID="2,3,4,5,6,7,8,9";
	public static final String GOVT_ALERT_TYPE_ID="2";	
	
	public static final String ALERT_DEPT_DOCUMENTS ="alert_documents";
	public static final List<Long> MAHANADUEVENTIDS = Arrays.asList(7l,30l);
	
	public static final String TAB_ATTENDANCE_ACCEPT_CARDS_YEAR = "All";//"2014-2016","2016-2018"
	
	public static final String  REQUIRED_COMMITTEE_LEVEL_IDS = "10,11";
	public static final String  REMAINING_COMMITTEE_LEVEL_IDS = "5,6,7,8,9,12";
	public static final String  REQUIRED_PUBLIC_REPRESENTATIVE_TYPE_IDS = "1,2,12,21";
	public static final String  REMAINING_PUBLIC_REPRESENTATIVE_TYPE_IDS = "3,4,5,6,7,8,9,10,11,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29";
	public static final String POSITION_OF_MLA_AND_MLC = "3,4";
	public static final String ELECTION_TYPE_IDS="6,7";
	
	public static final String CORE_DASHBOARD_INSURANCE_STATUS = "INTIMATIONS,FORWARDED,SETTLED,REJECTED";
	public static final String[] CORE_DASHBOARD_INSURANCE_STATUS_ARR = {"INTIMATIONS","FORWARDED","SETTLED","REJECTED"};
	
	public static final Long GOVT_DEPARTMENT_STATE_LEVEL_ID = 1L;
	public static final Long GOVT_DEPARTMENT_ZONE_LEVEL_ID = 2L;
	public static final Long GOVT_DEPARTMENT_REGION_LEVEL_ID = 3L;
	public static final Long GOVT_DEPARTMENT_CIRCLE_LEVEL_ID = 4L;
	public static final Long GOVT_DEPARTMENT_DISTRICT_LEVEL_ID = 5L;
	public static final Long GOVT_DEPARTMENT_DIVISION_LEVEL_ID = 6L;
	public static final Long GOVT_DEPARTMENT_SUB_DIVISION_LEVEL_ID = 7L;
	public static final Long GOVT_DEPARTMENT_MANDAL_LEVEL_ID = 8L;
	public static final Long GOVT_DEPARTMENT_MUNICIPALITY_LEVEL_ID = 9L;
	public static final Long GOVT_DEPARTMENT_PANCHAYAT_LEVEL_ID = 10L;
	public static final Long GOVT_DEPARTMENT_WARD_LEVEL_ID = 11L;
	public static final Long GOVT_DEPARTMENT_GMC_LEVEL_ID = 12L;
	public static final Long GOVT_DEPARTMENT_CLUSTER_LEVEL_ID = 13L;
	
	public static final List<Long>  AMS_REOPEN_ALERT_STATUS = Arrays.asList(11l,12l);
	public static final String CALL_CENTER_VERSION = "new";    //old - new
	public static final List<Long> ALERT_STATUS_IDS = Arrays.asList(1L,2L,3L,4L,5L,6L,7l,8L,9L,10L,11L,12L,13L);
	public static final Long PANCHAYATRAJ_MPDO_DESIGNATION_ID = 71L;
	public static final Long RWS_DEPARTMENT_ID = 49L;
	public static final Long PANCHAYATRAJ_PANCHAYAT_SECRERATORY_ID=72L;
	
	public static final Long GOVT_SOCIAL_MEDIA_CATEGORY_ID=5L;
	public static final Long MAHANADU_2017_EVENT_ID=51l;
	public static String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	public static final String FEEDBACK_ALERT_CATEGORY_ID="4,5";
	public static final Long MAHANADU_EVENT_ID = 51L;
	public static final List<Long> IMPACT_IDS = Arrays.asList(3l,5l,6l,9l,12l);
	public static final String PARLIAMENT_CONSTITUENCY = "PARLIAMENT CONSTITUENCY";
	public static final Long BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID = 24L;
	public static final String TRAINING_INVITEE_ROLE_IDS = "1,2,3";
	public static final List<Long> PARTY_ALERT_SMS_STATUS_IDS = Arrays.asList(2l,3l); 
	public static final Long PARTY_ALERT_TYPE_ID=1l;
	public static final List<Long> VISHAKAPATNAM_IDS=Arrays.asList(13l,517l);
	//DEBATE STATE ID
	public static final Long DEBATE_AP_STATE_ID = 1L;
	public static final Long DEBATE_TS_STATE_ID = 36L;
	public static final Long DEBATE_OTHERS_ID = 2L;
	public static final String ITDP_LIVE_URL = "http://mytdp.com/ITDP/WebService/";
	
	
	public static final Long PARTY_MEETING_STATE_LEVEL_ID = 2L;
	public static final Long PARTY_MEETING_DISTRICT_LEVEL_ID = 3L;
	public static final Long PARTY_MEETING_CONSTITUENCY_LEVEL_ID = 4L;
	public static final Long PARTY_MEETING_PARLIAMENT_LEVEL_ID = 10L;
	public static final Long PARTY_MEETING_MANDAL_LEVEL_ID = 5L;
	public static final Long PARTY_MEETING_MUNICIPALITY_LEVEL_ID = 7L;
	public static final Long PARTY_MEETING_PANCHAYAT_LEVEL_ID = 6L;
	
	public static final Long COMMITTEE_MEETINGS = 1L;
	public static final Long SPECIAL_MEETINGS = 3L;
	
	public static final Long ATTENDEE_TYPE_ID=1L;
	
	public static final String[] AP_PARLIAMENT_IDS_LIST = {"504","509","466","508","464","463","500","479","494","473","483","507","493","474","467","497","495","506","478","501","482","491","476","465","472"};
	public static final String AP_PARLIAMENT_IDS_LIST_STR = "504, 509, 466, 508, 464, 463, 500, 479, 494, 473, 483, 507, 493, 474, 467, 497, 495, 506, 478, 501, 482, 491, 476, 465, 472";
	public static final Long [] leaderShipSkillsProgramId ={8L};
	
	public static final Long TDP_COMMITTEE_ENROLLMENT_ID_16_18 = 2l;
	public static final Long TDP_COMMITTEE_ENROLLMENT_ID_14_16 = 1l;
	
	public static final Long REGIONSCOPE_COUNTRY_LEVEl_ID   = 1l;
	public static final Long REGIONSCOPE_PARLIAMENT_LEVEl_ACCESS_ID = 10l;
	public static final Long REGIONSCOPE_ASSEMBLY_LEVEl_ACCESS_ID   = 4l;
	public static final Long REGIONSCOPE_MANDAL_LEVEl_ID   = 5l;
	public static final Long REGIONSCOPE_VILLAGE_LEVEl_ID   = 6l;
	public static final Long REGIONSCOPE_MUNCIPLITY_LEVEl_ID   = 7l;
	public static final Long REGIONSCOPE_WARD_LEVEl_ID   = 8l;
	public static final String PARTY_MEETING_MOM_DOCUMENT="mom_documents";
	public static final String TRAINING_CAMP_SURVEY_QUIZS_FEEDBACK_IDS="342";
	public static final String TRAINING_CAMP_SURVEY_PROGRAM_FEEDBACK_IDS="341";
	
	public static final int DEBATE_AVG_VALUE=5;
	
	public static final String ZOHO_ADMIN_CONTACTID="239398000000148093";
	public static final String ZOHO_ADMIN_DEPTID="239398000000006907";
	public static final String ZOHO_ADMIN_AUTHORIZATION="0334a9147e76cd11781e207ef38f10fc";
	public static final String ZOHO_ADMIN_ORGID="659130259";
	public static final String ZOHO_JWT_SECRETKEY="dEkQ7T0NGxWZXSrfXka5jRIJr5nA0LTMqfBAbs9g";
}