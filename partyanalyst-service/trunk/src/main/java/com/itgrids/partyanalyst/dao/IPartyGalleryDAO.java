package com.itgrids.partyanalyst.dao;
import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyGallery;

public interface IPartyGalleryDAO extends GenericDao<PartyGallery,Long>{

	public List<Object[]> getPartyGallaryDetail(Long partyId,int firstResult,int maxResult,String type) ;
	
	public List<File> getFirstFourNewsForParty(Long partyId,int firstResult,int maxResult,String queryType);
	
	public List<Object[]> getNewsByScope(Long partyId,Long scopeType,int startIndex,int maxResults,String queryType , String sourceStr , String languageStr);
	
	public List<Object[]> getAllRecordInGallary(Long gallaryId);
	
	public List<Object[]> getGallariesByPartyId(Long partyId,String contentType);
	
	public List<Object[]> getPartyGalleriesDescForUpdate(Long gallaryId,Long partyId);
	
	public List<Party> getPartyByGalleryId(Long gallaryId);
	
	public List<Object> getPartyVisibility(Long gallaryId);
}
