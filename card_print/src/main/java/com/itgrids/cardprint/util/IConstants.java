package com.itgrids.cardprint.util;


public interface IConstants {
	
	//String Constants
	public final static String DEPLOYED_HOST = "localhost";
	public final static String SECKEYFAC = "";
	public final static String SECRAN = "";
	public final static String NEW_STATUS = "new";
	public final static String UPDATE_STATUS = "update";
	
	// Date & Time Formats
	public static final String DATE_PATTERN = "dd/MM/yyyy";
	public static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static final String TIME_ZONE_INDIA = "Asia/Calcutta";
	public static final String DATE_AND_TIME_FORMAT_24HRS = "yyyy-MM-dd HH:mm:ss";
	
	//WebService Credential Details
	public static final String WEBSERVICE_USER_NAME = "cardprint";
	public static final String WEBSERVICE_PASSWORD = "cardprint@!tG";
	
	//File Path details
	public static final String FILE_SEPARATOR = "file.separator";
	public static final String STATIC_CONTENT_FOLDER ="C:/Program Files (x86)/Apache Software Foundation/Tomcat 6.0/webapps/CardPrint/";
	
	public static final String[] MONTH_NAMES = {"Jan", "Feb", "March", "April", "May", "June", "July", "August", "Sep", "Oct", "Nov", "Dec"};  
	
	public final Long MAX_PRINT_VENDOR_ID = 1L;
	public final Long ZEBRA_PRINT_VENDOR_ID = 2L;
	
	public final int  noOfRecordsUpdate = 100;
}
