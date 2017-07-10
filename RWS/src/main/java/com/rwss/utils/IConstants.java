package com.rwss.utils;


public interface IConstants {
    
    public static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_ZONE_INDIA = "Asia/Calcutta";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_PATTERN_WITH_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String FILE_SEPARATOR = "file.separator";

    public static final String WORK_COMPLETION = "completed";
    public static final String WORK_COMMISSIONED = "commissioned";
	public static final String WORK_GROUNDED = "grounded";
	public static final String WORK_NOTGROUNDED = "not grounded";
	
	public static final String ACTION_UPDATE= "updateUser";
	public static final String ACTION_INSERT= "addUser";
	public static final String ACTION_DELETE= "deleteUser";
	
	public static final String RESULT_SUCCESS= "Success";
	public static final String RESULT_FAILURE= "Failure";
	
	public static final String TARGET_COMPLETED = "achieved";
	public static final String TARGET_NOT_COMPLETED = "not achieved";
	public static final String TARGET_ALL = "targets";
	public static final String STATUS ="status";
	
	public static final String STATE= "state";
	public static final String DISTRICT= "district";
	public static final String CONSTITUENCY="constituency";
	public static final String MANDAL ="mandal";
	public static final String ERROR_MESSAGE = "exceptionMessage";
	
	public static final String SUPPLY_TYPE_SAFE="safe";
	public static final String SUPPLY_TYPE_UNSAFE="un-safe";
}