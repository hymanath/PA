package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.Habitation;

public interface IHabitationDAO extends GenericDao<Habitation, Long> {
	public List<Object[]> getHabitationDataList(InputVO inputVO);
	public int deleteObsolateData(List<Long> habitationIdList);
	public List<Object[]> getHabitationDetails(InputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getTressedHabitationDetails(InputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getHabitationDetailsForConstituency(InputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getTressedHabitationDetailsForConstituency(InputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getHabitationDetailsForMandal(InputVO inputVO,Date fromDate,Date toDate);
}
