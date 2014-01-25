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

}
