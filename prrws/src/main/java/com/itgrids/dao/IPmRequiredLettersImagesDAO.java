package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmRequiredLettersImages;

public interface IPmRequiredLettersImagesDAO extends GenericDao<PmRequiredLettersImages, Long > {
	public List<Object[]> getDesignationWiseImages(List<Long> ofcrDesigIds,String letterType);
}
