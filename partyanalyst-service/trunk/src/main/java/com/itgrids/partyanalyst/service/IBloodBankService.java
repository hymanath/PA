package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.dto.IdNameVO;

public interface IBloodBankService {

 public List<BloodBankVO> getOccupationList();
 public List<BloodBankVO> getEducationalQualificationsList();
 public BloodBankVO getCadreDetails(String memberShipNO);
 public BloodBankDashBoardVO getBloodDonarsSummary(Long campId);
 public List<IdNameVO> getAcceptanceStatus();
 public List<IdNameVO> getBloodBagType();
 public List<IdNameVO> getBloodBagQuantity();
}
