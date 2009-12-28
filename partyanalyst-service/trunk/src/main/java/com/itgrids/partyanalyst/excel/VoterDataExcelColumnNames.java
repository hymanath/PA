package com.itgrids.partyanalyst.excel;

public enum VoterDataExcelColumnNames {
	DISRICT_ID("District_Id"),
	CONSTITUENCY_NAME("Constituency_Name"),
	TEHSIL_NAME("Mandal_Name"),
	PART_NUMBER("Part_No"),
	HOUSE_NUMBER("HOUSE_NO");
	private final String value;

    public String getValue() {
                return value;
    }
    private VoterDataExcelColumnNames(String value) {
                   this.value=value;
    }
}
