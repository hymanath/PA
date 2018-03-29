package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.dto.InputVO;
import com.itgrids.model.EncHabLocation;
import com.itgrids.model.EncWorks;

public interface IEncWorksDAO extends GenericDao<EncWorks,Long>{

	public List<Long> getAllDistinctWorkIds();

	public List<Object[]> getWorksData(Date fromDate, Date toDate, String status, List<Long> schemeIds, String locationType, Long LocationValue );

	public EncWorks findOneByworkId(Long workId);

	public List<Object[]> getExceedWorksBystatus(Date date, String type);

	public Long getEncWorkId(Long mandalId, Long districtCode, Long constituencycode,Long workId);

	public EncHabLocation getENCHablocationId(Long encWorkId, String habCode);

	public List<Object[]> getSchemeWiseWorkDetails(InputVO inputVO, String StatusType);

	public List<Object[]> getSchemeWiseOnclickWorkDetails(InputVO inputVO);

	public List<Object[]> getPRProgramsCodeAndName();

	List<Object[]> getAllSchemeWiseWorkDetails(InputVO inputVO, String StatusType);

}
