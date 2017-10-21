package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.TressedHabitation;

public interface ITressedHabitationDAO extends GenericDao<TressedHabitation, Long> {
	public List<Object[]> getTressedHabitationDataList(InputVO inputVO);
	public int deleteObsolateData(List<Long> tressedHabitationIdList);
}
