package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SocialCategory;

public interface ISocialCategoryDAO extends GenericDao<SocialCategory, Long> {
	
  public List<SocialCategory> getSocialCategoryDetailsByCategoryType(String categoryType);
}
