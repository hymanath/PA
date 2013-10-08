package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public interface IPartialBoothPanchayatDAO extends 
					GenericDao<PartialBoothPanchayat,Long>{
	public List<Long> getPartialBoothPanchayatDetailsByPanchayatId(Long PanchayatId,Long publicationId);
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId);
	public List<Long> getPartialBoothDetailsByPanchayatIdsAndPublicationDateId(List<Long> panchayatIds , Long publicationDateId);
	
	//public List<Object[]> getPanchayatByBoothId(Long boothId);
	
	public List<Object[]> getPanchayatesForPartialBooths(List<Long> panchayatIds,Long publicationId,Long constitiencyId);
	
	public List<Long> getPanchayatIdsForPartialBooths(List<Long> panchayatIds,Long publicationId,Long constitiencyId);
	
	public List<Object[]> getParitialBooths(List<Long> panchaytIds,Long constituencyId,Long publicationId);
	
	public Long getBoothPanchayatDetails(Long panchayatId,Long boothId);
	
	public List<Object[]> getPartialBoothsAndPanchayats(Long mandalId,Long publicationId);
	
	public List<Object[]> getSelectdPartialPanchayatDetails(Long id);
	
	public List<String> getDescriptionForSelectedPartalPanchay(Long panchayatId,Long boothId);
	
	public int deleteSelectedPartialBoothPanchayat(Long id);
	
	public Long getCountForPartianBooths(Long boothId);
	
	public int deleteSelectedMultiplePartialBoothPanchayat(Long boothId);
	
	public int updateDescriptionForPartialPanchayat(Long panchayatId,Long boothId,String description);
	
	public Long getBoothPanchayatDetails(Long panchayatId,Long boothId,Long hamletId);
	
	public List<Long> getPanchayatIdsForPartialPanchayat(List<Long> panchayatIds ,Long publicationDateId);
	public List<Long> getDistinctPartialBoothsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId);
	public List<Object[]> getPartialBoothDetailsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId);




	public List<Long> getPartialPanchayatIds(List<Long> boothIds,Set<Long> panchayatIds);
	
	public List<Object[]> getPartialPanchayats(Long constituencyId,Long publicationId);

	public Long getPartialBoothPanchayatDetails(Long panchayatId,Long publicationDateId);
	
	public List<Object[]> getPartialBoothsDetails(Long panchayatId,Long publicationDateId);
	
	public List<Long> getPartialBooths(Long panchayatId,Long publicationDateId);
	
	public List<Long> getPartialPanchayats(Long publicationDateId,Set<Long> panchayatIds);
	
	public Long getPartialBoothsCount(Long panchayatId,Long publicationDateId);
	
	public Long checkPanchayatIsPartial(Long panchayatId,Long publicationId);
}
