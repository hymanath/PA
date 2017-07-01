package com.itgrids.utils;


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
	
}