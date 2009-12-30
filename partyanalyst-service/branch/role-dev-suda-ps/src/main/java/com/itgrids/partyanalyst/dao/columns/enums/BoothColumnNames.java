package com.itgrids.partyanalyst.dao.columns.enums;

public enum BoothColumnNames {
	
	 PART_NO("partNo"),
	 PART_NAME("partName"),
	 VILLAGE_COVERED("villageCovered");
 
     private final String value;

     public String getValue() {
                 return value;
     }
     private BoothColumnNames(String value) {
                    this.value=value;
     }
}
