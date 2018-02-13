package com.itgrids.utils;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;

public interface IConstants {
	public static final String HOST = "smtp.gmail.com";
	public static final String PORT = "465";
	public static final String FROMEMAILID = "itgrids.portal@gmail.com";
    public static final String PASSWORD="itgrids$678";//"ITGRIDS123";
    public static final String FILE_SEPARATOR = "file.separator";
    
    public static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_ZONE_INDIA = "Asia/Calcutta";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_PATTERN_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    
    public static final Long COUNTRY_LEVEL_SCOPE_ID = 1L;
    public static final Long STATE_LEVEL_SCOPE_ID = 2L;
    public static final Long DISTRICT_LEVEL_SCOPE_ID = 3L;
    public static final Long CONSTITUENCY_LEVEL_SCOPE_ID = 4L;
    public static final Long MANDAL_LEVEL_SCOPE_ID = 5L;
    public static final Long VILLAGE_LEVEL_SCOPE_ID = 6L;
    public static final Long MUNICIPAL_CORP_GMC_LEVEL_SCOPE_ID = 7L;
    public static final Long WARD_LEVEL_SCOPE_ID = 8L;
    public static final Long BOOTH_LEVEL_SCOPE_ID = 9L;
    public static final Long TEMP_PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID = 9L;
    public static final Long PARLIAMENT_CONSTITUENCY_LEVEL_SCOPE_ID = 10L;
    public static final Long HAMLET_LEVEL_SCOPE_ID = 11L;
    public static final Long URBAN_LOCALITY_LEVEL_SCOPE_ID = 12L;
    public static final Long URBAN_BLOCK_LEVEL_SCOPE_ID = 13L;
    
    public static final Long TOTAL_AP_TOTAL_DISTRICTS = 13L;
    public static final Long TOTAL_AP_TOTAL_CONSTITUENCIES = 175L;
    public static final Long TOTAL_AP_TOTAL_MANDALS = 664L;
    public static final Long TOTAL_AP_TOTAL_VILLAGES = 16624L;
    public static final String TOTAL_AP_DISTRICT_IDS = "11,12,13,14,15,16,17,18,19,20,21,22,23";
    
    public static final int MATRIX_REPORT_INTERVALS = 8;
    public static final Long[] FUND_TYPE_IDS = {1L,2L,3L,4L};
    public static final String[] FUND_TYPE = {"PLAIN","SCP","TSP","SCSP"};
    public static final Long[] SCHEME_TYPE_IDS = {5L,6L,7L,9L};  
    public static final String[] SCHEME_TYPE = {"SVS","SOLAR","CWTP","MVS"}; 
    
    public static final String STATUS_SUCCESS = "Success";
    public static final String STATUS_FAIL = "Fail";
    
    public static final String RWS_NIC_DOMINE_IP = "http://rwss.ap.nic.in";//LIVE URL
    //public static final String RWS_NIC_DOMINE_IP = "http://192.168.11.109:8085";//LOCAL URL
	
    public static final String MANDAL = "mandal";
    public static final String DISTRICT = "district";
    public static final String CONSTITUENCY = "constituency";
    public static final String SERVER = "server";
    public static final String DEFAULT_SCHEDULER_SEVER = "localhost";
    public static final String ITC_WEB_SERVICE_USER_NAME = "MEESEVA";
    public static final String ITC__WEB_SERVICE_PASSWORD = "MEESEVA";
    public static final String ITC_WEB_SERVICE_NAME_SPACE = "http://AP.org/";
    public static final Long[] ITEC_EOFFICE_DEPT_IDS = {15L,6567L,1260L,6575L,6581L,1257L,3688L,5300L};
    
    public static final String DEFAULT_MAIL_SERVER = "server";
    public static final String EMAIL_USERNAME = "info@itgrids.com";
    public static final String EMAIL_PASSWORD = "itgrids$678";
 // public static final String STATIC_CONTENT_FOLDER_URL = "/app/static_content/PRRWS/";
    public static final String STATIC_CONTENT_FOLDER_URL = "D:/static_content/PRRWS/";
    public static final String PETITIONS_FOLDER = "Petition_Documents";
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";
    public static final String LOCALHOST = "localhost";
    public static final ImmutableList<Long>  COMPONENT_IDS =ImmutableList.of(15L, 16L, 17L, 18L, 39L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 14L, 16L, 17L, 18L,19L, 20L, 21L, 22L, 25L, 26L, 27L, 28L, 29L, 30L, 31L, 32L, 33L, 34L, 35L,  37L, 38L, 40L, 41L,43L, 44L, 45L, 46L, 47L, 48L, 49L, 50L, 51L, 52L, 53L, 54L, 55L, 56L, 57L, 58L, 59L, 60L);
    
    public static final Long[] PRESENT_FINANCIAL_YEAR_IDS = {9L,10L,11L,12L};
    public static final Long[] AP_PARLIAMENT_IDS_LIST = {504L,509L,466L,508L,464L,463L,500L,479L,494L,473L,483L,507L,493L,474L,467L,497L,495L,506L,478L,501L,482L,491L,476L,465L,472L};
    public static final List<Long> PETITION_IN_PROGRESS_IDS =Arrays.asList(3l,6l,7l,10l,11l,12l,13l,14l,15l);
    public static final Long[] PR_RD_EOFFICE_DEPT_IDS = {1448L,1466L,1424L,729L,2798L};
    public static final List<Long> PETITION_COMPLETED_IDS = Arrays.asList(4l,5l,8l,9l);
    public static final Long DEFAULT_PETITION_ASSIGNED_USER_ID = 21L;// Nara Lokesh
    public static final List<Long> DASHBOARD_ACCESS_OFFICER_DESIGNATION_IDS=Arrays.asList(2L,86L,92L);//2 Minister, 86 - OSD, 23 - principal secretory ,92 data entry operator
    public static final List<Long> PETITIONS_STATE_LEVEL_DESIGNATION_IDS=Arrays.asList(2L,86L,23L,94L,95L,93L,96L,97L,84L);//,87L);// 87 - HODs
    public static final List<Long> PETITIONS_DISTRICT_LEVEL_DESIGNATION_IDS=Arrays.asList(19L,80L,79L,90L,88L,9L);
    public static final List<Long> FINAL_APPROVED_ACCESS_DESIGNAION_IDS=Arrays.asList(23L);
    
}