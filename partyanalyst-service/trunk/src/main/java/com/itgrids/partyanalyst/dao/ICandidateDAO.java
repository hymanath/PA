/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Nomination;

/**
 * Interface for CandidateDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ICandidateDAO extends GenericDao<Candidate, Long>{

	/**
	 * Find all Candidate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Candidate property to query
	 * @param value
	 *            the property value to match
	 * @return List<Candidate> found by query
	 */
	public List<Candidate> findByProperty(CandidateColumnNames propertyName, Object value);

	public List<Candidate> findByFirstname(Object firstname);

	public List<Candidate> findByMiddlename(Object middlename);

	public List<Candidate> findByLastname(Object lastname);

	public List<Candidate> findByEmailAddress(Object emailAddress);

	public List<Candidate> findByPhone(Object phone);

	public List<Candidate> findByMobile(Object mobile);

	public List<Candidate> findByAddress(Object address);

	public List<Candidate> findByEducation(Object education);

	public List<Candidate> findByGender(Object gender);
	
	public List<Candidate> findCandidteIds(String[] firstName);
	
	public List<Candidate> findCandidateDetails(Long candidateId);
	
}