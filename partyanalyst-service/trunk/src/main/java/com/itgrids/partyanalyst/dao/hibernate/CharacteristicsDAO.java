package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICharacteristicsDAO;
import com.itgrids.partyanalyst.model.Characteristics;

public class CharacteristicsDAO extends GenericDaoHibernate<Characteristics, Long> implements ICharacteristicsDAO{

	public CharacteristicsDAO() {
		super(Characteristics.class);

	}
			
	@SuppressWarnings("unchecked")
	public List<Characteristics> getCharacteristicsDetails(){
		Query query = getSession().createQuery("select model from Characteristics model where model.isDeleted != 'Y'");
		 
		return query.list();
	 }
	
	public Long checkForExists(String name)
	{
		Query query = getSession().createQuery("select count(model.characteristicsId) from Characteristics model " +
				" where model.name = :name");
		query.setParameter("name", name);
		return (Long)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCharacteristicsDetailsNew(){
		Query query = getSession().createQuery("select model.characteristicsId,model.name from Characteristics model where model.isDeleted = 'N'");
		 
		return query.list();
	 }
	
	public Long getSumOfCharacters(Long charactersticsId){
		StringBuilder str = new StringBuilder();
		
		str.append(" select sum(model.maxScale) from Characteristics model " +
				" where model.isDeleted ='N' ");
		
		if(charactersticsId !=null && charactersticsId.longValue()>0){
			str.append(" and model.characteristicsId =:charactersticsId ");
		}
		
		Query query = getSession().createQuery(str.toString());
		
		if(charactersticsId !=null && charactersticsId.longValue()>0){
			query.setParameter("charactersticsId", charactersticsId);
		}
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getEachCharacterWiseMaxScale(){
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.characteristicsId,model.maxScale from Characteristics model " +
				" where model.isDeleted ='N' ");
		Query query = getSession().createQuery(str.toString());

		return query.list();
	}

}
