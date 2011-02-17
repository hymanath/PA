package com.itgrids.partyanalyst.utils;

/**
 * This class can be used to throw custom exceptions
 * 
 * @author Ravi Kiran.Y
 *
 */
public class GenericException extends Exception {

	public GenericException(){
		
	}
	
	public GenericException(String excepMsg){
		super(excepMsg);
	}
	
}
