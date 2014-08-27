package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserViewNews;

public interface IUserViewNewsDAO extends GenericDao<UserViewNews, Long>{
	public List checkFileExist(Long userId,Long fileId);
}
