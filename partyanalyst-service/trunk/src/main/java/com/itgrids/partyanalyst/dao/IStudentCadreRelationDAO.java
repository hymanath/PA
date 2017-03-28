package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StudentCadreRelation;

public interface IStudentCadreRelationDAO extends GenericDao<StudentCadreRelation, Long>{
	public List<Object[]> getNtrTrustStudentDetailsInstitutionWise(List<Long> tdpCadreIds);
	public List<Object[]> getStudentFormalDetailsByCadre(List<Long> tdpCadreIds,Long institutionId);

}
