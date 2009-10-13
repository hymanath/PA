package com.itgrids.partyanalyst.helper;
/**
 * 
 * @author Narender Akula
 *
 */
public enum PartyShortNamesEnum {
	INC("INC"),
	BJP("BJP"),
	CPM("CPM"),
	PRP("PRP"),
	TRP("TRP"),
	TRS("TRS"),
	TDP("TDP"),
	IND("IND"),
	PPOI("PPOI");

	private final String value;

	public String getValue() {
		org.apache.struts2.tiles.StrutsTilesListener a;
		return value;
	}

	private PartyShortNamesEnum(String value) {
		this.value = value;
	}
}
