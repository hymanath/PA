package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRequiredFileFormatText;

public interface IPmRequiredFileFormatTextDAO extends GenericDao<PmRequiredFileFormatText, Long>{

	public String getCoverLetterMessage(List<Long> ofcrDesigIds);
}
