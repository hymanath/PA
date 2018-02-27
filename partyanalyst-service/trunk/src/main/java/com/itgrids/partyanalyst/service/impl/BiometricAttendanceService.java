package com.itgrids.partyanalyst.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.hibernate.BiometricDeviceLogDAO;
import com.itgrids.partyanalyst.dao.hibernate.BiometricDeviceLogTrackDAO;
import com.itgrids.partyanalyst.dto.AttendanceLogTrackVO;
import com.itgrids.partyanalyst.dto.AttendanceLogVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.BiometricDeviceLog;
import com.itgrids.partyanalyst.model.BiometricDeviceLogTrack;
import com.itgrids.partyanalyst.service.IBiometricAttendanceService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class BiometricAttendanceService implements IBiometricAttendanceService{

	private static final Logger LOG = Logger.getLogger(BiometricAttendanceService.class);
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://183.82.97.144:1433;databaseName=";
	static final String USER = "sa";
	static final String PASS = "1tGrids";
	
	Connection conn = null;
	Statement stmt = null;
	private DateUtilService dateUtilService = new DateUtilService();
	
	private BiometricDeviceLogDAO biometricDeviceLogDAO;
	private BiometricDeviceLogTrackDAO biometricDeviceLogTrackDAO;
	
	public void setBiometricDeviceLogDAO(BiometricDeviceLogDAO biometricDeviceLogDAO) {
		this.biometricDeviceLogDAO = biometricDeviceLogDAO;
	}

	public void setBiometricDeviceLogTrackDAO(
			BiometricDeviceLogTrackDAO biometricDeviceLogTrackDAO) {
		this.biometricDeviceLogTrackDAO = biometricDeviceLogTrackDAO;
	}

	public AttendanceLogTrackVO getBiometricLogDetails(Date logDate)
	{
		AttendanceLogTrackVO attendanceLogTrackVO = new AttendanceLogTrackVO();
		try{
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(DB_URL+"eSSLSmartOffice",USER,PASS);
    		stmt = conn.createStatement();
    		
    		List<AttendanceLogVO> list = new ArrayList<AttendanceLogVO>(0);
    		AttendanceLogVO attendanceLogVO = null;
    		
    		int year = (1900+logDate.getYear());
    		int month = (1+logDate.getMonth());
    		
    		Integer maxId = biometricDeviceLogDAO.getMaxLogIdForAMonth(year,month);
    		if(maxId == null)
    			maxId = 0;
    		
    		attendanceLogTrackVO.setStartTime(dateUtilService.getCurrentDateAndTime());
    		String tableName = month+"_"+year;
    		
    		ResultSet rs = stmt.executeQuery("SELECT D.DeviceLogId,D.DeviceId,D.UserId,D.LogDate FROM DeviceLogs_"+tableName+" D " +
    				" WHERE D.DeviceLogId > "+maxId+" ORDER BY UserId,LogDate");
    		
    		attendanceLogTrackVO.setEndTime(dateUtilService.getCurrentDateAndTime());
    		
    		long totalRecords = 0;
    		Timestamp timestamp = null;
    		
    		while(rs.next())
    		{
    			try{
    				totalRecords++;
    				attendanceLogVO = new AttendanceLogVO();
    				attendanceLogVO.setDeviceLogId(new Long(rs.getInt("DeviceLogId")));
    				attendanceLogVO.setDeviceId(new Long(rs.getInt("DeviceId")));
    				attendanceLogVO.setEmployeeCode(rs.getString("UserId"));
    				timestamp = rs.getTimestamp("LogDate");
    				if(timestamp != null)
    					attendanceLogVO.setLogTime(new Date(timestamp.getTime()));
	    			list.add(attendanceLogVO);
    			}catch(Exception e)
    			{
    				LOG.error(e);
    			}
    		}
    		attendanceLogTrackVO.setLogData(list);
    		attendanceLogTrackVO.setTotalRecords(totalRecords);
    		attendanceLogTrackVO.setStatus(IConstants.STATUS_SUCCESS);
    		rs.close();
    		conn.close();
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getBiometricLogDetails() - ",e);
			attendanceLogTrackVO.setStatus(IConstants.STATUS_FAIL);
		}
		return attendanceLogTrackVO;
	}
	
	public ResultStatus saveBiometricLogDetails(Date logDate)
	{
		ResultStatus resultStatus = new ResultStatus();
		AttendanceLogTrackVO attendanceLogTrackVO = null;
		try{
			attendanceLogTrackVO = getBiometricLogDetails(logDate);
			
			if(attendanceLogTrackVO != null)
			{
				BiometricDeviceLogTrack biometricDeviceLogTrack = new BiometricDeviceLogTrack();
				
				biometricDeviceLogTrack.setStartTime(attendanceLogTrackVO.getStartTime());
				biometricDeviceLogTrack.setEndTime(attendanceLogTrackVO.getEndTime());
				biometricDeviceLogTrack.setRecordsDownloaded(attendanceLogTrackVO.getTotalRecords());
				biometricDeviceLogTrack.setStatus(attendanceLogTrackVO.getStatus());
				
				biometricDeviceLogTrack = biometricDeviceLogTrackDAO.save(biometricDeviceLogTrack);
				
				if(attendanceLogTrackVO.getLogData() != null && attendanceLogTrackVO.getLogData().size() > 0)
				{
					BiometricDeviceLog biometricDeviceLog = null;
					Date currentTime = dateUtilService.getCurrentDateAndTime();
					
					for(AttendanceLogVO attendanceLogVO : attendanceLogTrackVO.getLogData())
					{
						biometricDeviceLog = new BiometricDeviceLog();
						
						biometricDeviceLog.setBiometricDeviceLogTrack(biometricDeviceLogTrack);
						biometricDeviceLog.setDeviceLogId(attendanceLogVO.getDeviceLogId());
						biometricDeviceLog.setDeviceId(attendanceLogVO.getDeviceId());
						biometricDeviceLog.setEmployeeCode(attendanceLogVO.getEmployeeCode());
						biometricDeviceLog.setLogTime(attendanceLogVO.getLogTime());
						biometricDeviceLog.setInsertedTime(currentTime);
						
						biometricDeviceLogDAO.save(biometricDeviceLog);
					}
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in saveBiometricLogDetails() Method - ",e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
	}
	
}
