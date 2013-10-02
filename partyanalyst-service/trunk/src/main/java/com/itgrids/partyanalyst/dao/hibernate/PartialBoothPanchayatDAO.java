package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public class PartialBoothPanchayatDAO extends
GenericDaoHibernate<PartialBoothPanchayat, Long> implements IPartialBoothPanchayatDAO{
	
	public PartialBoothPanchayatDAO(){
		super(PartialBoothPanchayat.class);		
	}

	@SuppressWarnings("unchecked")
	public List<Long> getPartialBoothPanchayatDetailsByPanchayatId(Long panchayatId,Long publicationDateId) {
		String queryString = "select distinct(model.booth.boothId) from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId  and model.booth.publicationDate.publicationDateId = :publicationDateId" ;
				//" and model.booth.isPartial like '%y%'";
		Query query = getSession().createQuery(queryString);
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
	return query.list();
	}
	
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatIdAndPublicationDateId(Long panchayatId,Long publicationDateId) {
		String queryString = " from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId and model.booth.publicationDate.publicationDateId =:publicationDateId " ;
		Query query = getSession().createQuery(queryString);
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("publicationDateId", publicationDateId);
	return query.list();
	}
	
	public List<Long> getPartialBoothDetailsByPanchayatIdsAndPublicationDateId(List<Long> panchayatIds , Long publicationDateId)
	{
		
		String queryString = " select model.booth.boothId from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId and model.booth.publicationDate.publicationDateId in(:publicationDateIds)";
		
			Query query = getSession().createQuery(queryString);
			
			query.setParameterList("panchayatIds", panchayatIds);
			query.setParameter("publicationDateId", publicationDateId);
			return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatesForPartialBooths(List<Long> panchayatIds,Long publicationId,Long constitiencyId)
	{
		Query quey = getSession().createQuery("select model.booth.tehsil.tehsilId ," +//1
				" model.booth.tehsil.tehsilName , " +
				" model.panchayat.panchayatId ," +
				" model.panchayat.panchayatName from PartialBoothPanchayat model  " +
				" where model.booth.constituency.constituencyId = :constitiencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId and " +
				" model.panchayat.panchayatId not in (:panchayatIds)");
		quey.setParameter("publicationId", publicationId);
		quey.setParameter("constitiencyId", constitiencyId);
		quey.setParameterList("panchayatIds", panchayatIds);
		return quey.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPanchayatIdsForPartialBooths(List<Long> panchayatIds,Long publicationId,Long constitiencyId)
	{
		Query quey = getSession().createQuery("select  " +
				" model.panchayat.panchayatId " +
				" from PartialBoothPanchayat model  " +
				" where model.booth.constituency.constituencyId = :constitiencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId and " +
				" model.panchayat.panchayatId not in (:panchayatIds)");
		quey.setParameter("publicationId", publicationId);
		quey.setParameter("constitiencyId", constitiencyId);
		quey.setParameterList("panchayatIds", panchayatIds);
		return quey.list();
	}
	
	public List<Object[]> getParitialBooths(List<Long> panchaytIds,Long constitiencyId,Long publicationId)
	{
		Query query = getSession().createQuery("select  model.panchayat.panchayatId," +
				" model.panchayat.panchayatName , " +
				" model.booth.boothId," +
				" model.booth.partNo " +
				" from PartialBoothPanchayat model " +
				" where model.booth.constituency.constituencyId = :constitiencyId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId and " +
				" model.panchayat.panchayatId in (:panchaytIds)");
		query.setParameter("constitiencyId", constitiencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameterList("panchaytIds", panchaytIds);
		return query.list();
	}
	
	public Long getBoothPanchayatDetails(Long panchayatId,Long boothId)
	{
		Query query = getSession().createQuery("select model.partialBoothPanchayatId from " +
				" PartialBoothPanchayat model where " +
				" model.panchayat.panchayatId = :panchayatId and  " +
				" model.booth.boothId = :boothId ");
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public Long getBoothPanchayatDetails(Long panchayatId,Long boothId,Long hamletId)
	{
		Query query = getSession().createQuery("select model.partialBoothPanchayatId from " +
				" PartialBoothPanchayat model where " +
				" model.panchayat.panchayatId = :panchayatId and  " +
				" model.booth.boothId = :boothId and " +
				" model.hamlet.hamletId = :hamletId ");
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("boothId", boothId);
		query.setParameter("hamletId", hamletId);
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getPartialBoothsAndPanchayats(Long mandalId,Long publicationId)
	{
		Query query = getSession().createQuery("select model.partialBoothPanchayatId , " +//0
				" model.panchayat.panchayatId ," +//1
				" model.panchayat.panchayatName , " +//2
				" model.booth.boothId," +//3
				" model.booth.partNo ," +//4
				" model.description , " +//5
				" model.hamlet.hamletId ," +//6
				" model.hamlet.hamletName " +//7
				" from PartialBoothPanchayat model where  " +
				" model.booth.tehsil.tehsilId = :mandalId and " +
				" model.booth.publicationDate.publicationDateId = :publicationId " +
				" and model.panchayat.panchayatId != model.booth.panchayat.panchayatId ");
		query.setParameter("mandalId", mandalId);
		query.setParameter("publicationId", publicationId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectdPartialPanchayatDetails(Long id)
	{
		Query query = getSession().createQuery("select model.panchayat.panchayatId ," +//0
				" model.panchayat.panchayatName , " +//1
				" model.booth.boothId , " +//2
				" model.booth.partNo , " +//3
				" model.booth.panchayat.panchayatId , " +//4
				" model.booth.panchayat.panchayatName, " +//5
				" model.description  ," +//6
				" model.hamlet.hamletId ," +//7
				" model.hamlet.hamletName " +//8
				" from PartialBoothPanchayat model where " +
				" model.partialBoothPanchayatId = :id ");
		query.setParameter("id", id);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getDescriptionForSelectedPartalPanchay(Long panchayatId,Long boothId)
	{
		Query query = getSession().createQuery("select model.description " +
				" from PartialBoothPanchayat model where " +
				" model.panchayat.panchayatId = :panchayatId and " +
				" model.booth.boothId = :boothId ");
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	public int deleteSelectedPartialBoothPanchayat(Long id)
	{
		Query query = getSession().createQuery(" delete from PartialBoothPanchayat model where " +
				" model.partialBoothPanchayatId = :id ");
		query.setParameter("id", id);
		int result = query.executeUpdate();
		return result;
	}
	
	public Long getCountForPartianBooths(Long boothId)
	{
		Query query = getSession().createQuery("select count(*) from PartialBoothPanchayat model where " +
				" model.booth.boothId = :boothId");
		query.setParameter("boothId", boothId);
		return (Long)query.uniqueResult();
	}
	
	public int deleteSelectedMultiplePartialBoothPanchayat(Long boothId)
	{
		Query query = getSession().createQuery(" delete from PartialBoothPanchayat model where " +
				" model.booth.boothId = :boothId ");
		query.setParameter("boothId", boothId);
		int result = query.executeUpdate();
		return result;
	}
	
	public int updateDescriptionForPartialPanchayat(Long panchayatId,Long boothId,String description)
	{
		Query query = getSession().createQuery("update PartialBoothPanchayat model " +
				" set model.description = :description " +
				" where model.panchayat.panchayatId = :panchayatId and " +
				" model.booth.boothId = :boothId");
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("boothId", boothId);
		query.setParameter("description", description);
		int result = query.executeUpdate();
		
		return result;
	}
	
}
