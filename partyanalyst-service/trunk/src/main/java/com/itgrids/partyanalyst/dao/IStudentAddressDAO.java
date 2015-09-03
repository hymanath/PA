package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AddressForStudent;
import com.itgrids.partyanalyst.model.StudentAddress;

public interface IStudentAddressDAO extends GenericDao<StudentAddress, Long>{

	public List<StudentAddress> getStudentSpecificAddressDetails(Long studentId);
}
