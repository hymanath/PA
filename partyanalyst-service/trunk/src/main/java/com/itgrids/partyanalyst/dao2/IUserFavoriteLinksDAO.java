package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserFavoriteLinks;

public interface IUserFavoriteLinksDAO extends GenericDao<UserFavoriteLinks, Long>{
	
	public List<UserFavoriteLinks> getUserFavouriteLinksAction(Long userId);
	
	public int deleteUserFavouritelinkById(Long linkId);
	
	public List<UserFavoriteLinks> checkForAlreadyExistedOrNot(Long userId,String url);
	
	public List<Object[]> getFavoroteLinksForPublicProfileStreeming(Long userId,Date toDate,Date fromDate);
}
