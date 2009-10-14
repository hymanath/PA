/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
   
   package com.itgrids.partyanalyst.dao.columns.enums;

  /**
 * Candidate Table Column Name Constants in the form enum. 
 * 
 * @author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
   @author <a href="mailto:sriharigopalnalam@gmail.com">Srihari</a>
 *
 */

public enum NominationColumnNames {
	
	
	 NOMINATION_STATUS("nominationStatus"),
	 ASSETS("assets"),
	 LIABILITIES("liabilities"),
	 CRIMINAL_CHARGES("criminalCharges");
	 
	 
	 private final String value;

     public String getValue() {
                 return value;
     }

     private NominationColumnNames(String value) {
                    this.value=value;
     }

}
