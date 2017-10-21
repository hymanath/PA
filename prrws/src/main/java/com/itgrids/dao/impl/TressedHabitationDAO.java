package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITressedHabitationDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.TressedHabitation;
@Repository
public class TressedHabitationDAO extends GenericDaoHibernate<TressedHabitation, Long> implements ITressedHabitationDAO {
	public TressedHabitationDAO(){
		super(TressedHabitation.class);
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Object[]> getTressedHabitationDataList(InputVO inputVO){
		StringBuilder sb = new StringBuilder();
		sb.append(" select "
				+ " tressedHabitation.tressedHabitationId, "//0
				+ " tressedHabitation.districtCode , "//1
				+ " tressedHabitation.districtName , "//2
				+ " tressedHabitation.constituencyCode , "//3
				+ " tressedHabitation.constituencyName , "//4
				+ " tressedHabitation.mandalCode , "//5
				+ " tressedHabitation.mandalName , "//6
				+ " tressedHabitation.habitationCode , "//7
				+ " tressedHabitation.habitationName , "//8
				+ " tressedHabitation.coverageStatus "//9
				+ " from TressedHabitation tressedHabitation "
				+ " where  "
				+ " tressedHabitation.isDeleted = 'N' ");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
	public int deleteObsolateData(List<Long> tressedHabitationIdList){
		StringBuilder sb = new StringBuilder();
		sb.append(" update tressed_habitation set is_deleted='Y' where tressed_habitation_id in (:tressedHabitationIdList) ");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameterList("tressedHabitationIdList", tressedHabitationIdList);
		return query.executeUpdate();
	}
}
