package com.itgrids.partyanalyst.dao;
import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyGallery;

public interface IPartyGalleryDAO extends GenericDao<PartyGallery,Long>{

	public List<Object[]> getPartyGallaryDetail(Long partyId,int firstResult,int maxResult,String type) ;
}
