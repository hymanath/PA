package com.itgrids.electoralconnect.dto;
/**
 * used as a resultCode values of ResultStatus 
 * @author Narender
 *
 */
public interface ResultCodeMapper {
	public final static int SUCCESS = 0;
    public final static int FAILURE = 1; //not to be used in most cases..use specific failures
    public final static int CHARTING_WEBSERVICE_FAILURE = 2;
    public final static int DATA_NOT_FOUND = 121;
}
