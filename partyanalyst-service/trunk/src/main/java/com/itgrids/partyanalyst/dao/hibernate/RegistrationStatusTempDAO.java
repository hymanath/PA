package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;




import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRegistrationStatusTempDAO;
import com.itgrids.partyanalyst.model.RegistrationStatusTemp;



public class RegistrationStatusTempDAO extends  GenericDaoHibernate<RegistrationStatusTemp,Long> implements IRegistrationStatusTempDAO {
    public RegistrationStatusTempDAO(){
   	 super(RegistrationStatusTemp.class);
    }
	public List<Object[]> getLatestMessages(Integer startIndex,Integer maxIndex)
	{
		 Query query = getSession().createSQLQuery("select rst.message,rst.type,rst.inserted_time " +
	     	 		" from (select booth1.booth_id as booth_id,max(rst1.inserted_time) as inserted_time,rst1.type as type from registration_status_temp rst1,booth booth1 " +
	     	 		" where rst1.booth_id = booth1.booth_id  and booth1.publication_date_id =:publicationId group by rst1.booth_id,rst1.type) as uniqueResult , registration_status_temp rst," +
	     	 		" booth booth where rst.booth_id = uniqueResult.booth_id and rst.inserted_time = uniqueResult.inserted_time and rst.booth_id = booth.booth_id  " +
	     	 		" and uniqueResult.type =rst.type  order by rst.inserted_time desc");
	    	
	    	 query.setParameter("publicationId", 12);
	    	 query.setFirstResult(startIndex);
		     query.setMaxResults(maxIndex);
		     return query.list();
	}

}
