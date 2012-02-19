package com.itgrids.partyanalyst.utils;

import java.awt.Color;

public interface IConstants {
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy/MM/dd";
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
    
    public final static String MAIN_PARTY = "MAIN_PARTY";
    public final static String OPP_PARTY = "OPP_PARTY";
    public final static String VOTES_MARGIN = "VOTES_MARGIN";
    public final static String VOTES_PERCENT = "VOTES_PERCENT";
    
    //SMS Modules
    public final static String User_Groups = "User Groups";
    public final static String Cadre_Management = "Cadre Management";
    public final static String Influencing_People ="Influencing People";
    public final static String PROBLEM_MANAGEMENT = "Problem Managemet";
    
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
    
    public static final String NULL_POINTER_EXCEPTION = "NULL_POINTER_EXCEPTION";
    public static final String ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION = "ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION";
    public static final String NUMBER_FORMAT_EXCEPTION = "NUMBER_FORMAT_EXCEPTION";
    public static final String GENERAL_EXCEPTION = "GENERAL_EXCEPTION";
    public static final String CREATE_NEW = "new";
    public static final String UPDATE_EXISTING = "update_existing";
    public static final String SPEAK_LANGUAGE = "Able To Speak";
    public static final String READ_LANGUAGE = "Able To  Read"; 
    public static final String WRITE_LANGUAGE = "Able To  Write";
    public static final String CONST_TYPE_URBAN = "URBAN";
    public static final String CONST_TYPE_RURAL = "RURAL";	
    public static final String CONST_TYPE_RURAL_URBAN = "RURAL-URBAN";
    public static final String URBAN_TYPE = "1";
    public static final String RURAL_TYPE = "2";
    
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
    public static final String MANDAL_LEVEL = "MANDAL";
    public static final int MAX_LEVEL_OF_CONNECTION = 2;
    public static final String COMPLETE_DETAILS = "COMPLETE_DETAILS";
    public static final String PARTIAL_DETAILS = "PARTIAL_DETAILS";
    
    public static final String AVAILABLE = "AVAILABLE";
    public static final String NOT_AVAILABLE = "NOT_AVAILABLE";
    
    public static final String SUCCESS = "Success";
    public static final String SUCCESSFULLY_SAVED = "SUCCESSFULLY_SAVED";
    public static final String BOOTH = "BOOTH";
    public static final String MP_CONSTITUENCY = "MP CONSTITUENCY";
    
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

          
    public static final String PROBLEM_STATUS_ADD = "PROBLEM_STATUS_ADD";
    public static final String PROBLEM_STATUS_MODIFY = "PROBLEM_STATUS_MODIFY";
    
    public static final String PROBLEM_COMMENTS_ADD = "PROBLEM_COMMENTS_ADD";
    public static final String PROBLEM_COMMENTS_MODIFY = "PROBLEM_COMMENTS_MODIFY";
    
    public static final String REASONS_POSTING = "REASONS_POSTING";
        
        
    public static final String REQUIRED_CONSTITUENCIES = "REQUIRED_CONSTITUENCIES";    
    public static final String REMAINING_CONSTITUENCIES = "REMAINING_CONSTITUENCIES";    
    public static final String LATEST_CONSTITUENCIES = "LATEST_CONSTITUENCIES";
    public static final String ALL_PARTIES = "ALL PARTIES";
    
    public static final String PROFILE_PIC = "profiles";
    public static final String PROBLEM_PIC = "problems";
    public static final String CADRE_IMAGES = "cadre_images";
    
    public static final String ELECTION_RESULTS = "RESULTS";
    public static final String ELECTION_NOMINATIONS = "NOMINATIONS";
    
    
    // Fror Mailing
    public static final String LOCALFROMEMAILID = "partyanalyst04@gmail.com";
    public static final String SERVER = "server";
    public static final String LOCALHOST = "localhost";
    public static final String HOST = "smtp.gmail.com";
    public static final String PORT = "465";
    public static final String FROMEMAILID = "info@partyanalyst.com";
    public static final String TOEMAILID = "kripton514@gmail.com";
    public static final String PASSWORD="ITGRIDS123";
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

}
