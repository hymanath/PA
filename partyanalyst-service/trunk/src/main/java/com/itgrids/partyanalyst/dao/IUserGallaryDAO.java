package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.UserGallary;

public interface IUserGallaryDAO extends GenericDao<UserGallary,Long>{
	public List<Object[]> getAllNewsGallaryBasedOnUserId(Long userId);
}
