package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public interface IPartialBoothPanchayatDAO extends 
					GenericDao<PartialBoothPanchayat,Long>{
	public List<Long> getPartialBoothPanchayatDetailsByPanchayatId(Long PanchayatId,Long publicationDateId);
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

}
