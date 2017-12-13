package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.PmRepresenteeRefDetails;

public interface IPmRepresenteeRefDetailsDAO extends GenericDao<PmRepresenteeRefDetails, Long> {
	public List<Object[]> getRepresentativeSearchWiseDetails(InputVO inputVO);
}
