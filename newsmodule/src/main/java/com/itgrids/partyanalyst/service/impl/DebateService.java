package com.itgrids.partyanalyst.service.impl;

import java.util.Formatter;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.service.IDebateService;
import org.apache.commons.lang.StringEscapeUtils;

public class DebateService implements IDebateService{
	
	private static final Logger LOG = Logger.getLogger(DebateService.class); 
	
	
	/**
	 * This service is used for telugu font saving as well as telugu font retriving
	 * @param input
	 * @return String b
	 */
	public String escapeUnicode(String input)
	{
		StringBuilder b = new StringBuilder(input.length());
		Formatter f = new Formatter(b);
		for (char c : input.toCharArray()) {
		if (c < 128) {
		b.append(c);
		} else {
		f.format("\\u%04x", (int) c);
		}
		}
		return b.toString();
	}

	/**
	 * this service is used for getting the debate details for selected debate
	 * @param debateId
	 * @return List<DebateVO> returnList
	 */
	public List<DebateVO> getDebateDetailsForSelected(Long debateId)
	{
		List<DebateVO> returnList = null;
		try {
			LOG.info("Enterd into getDebateDetailsForSelected method in DebateService class");
		} catch (Exception e) {
			LOG.error("Error occured in getDebateDetailsForSelected method in DebateService class",e);
		}
		return returnList;
	}
}
