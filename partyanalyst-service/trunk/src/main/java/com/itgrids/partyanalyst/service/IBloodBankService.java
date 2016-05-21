package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;

public interface IBloodBankService {

 public List<BloodBankVO> getOccupationList();
 public List<BloodBankVO> getEducationalQualificationsList();
 public BloodBankVO getCadreDetails(String memberShipNO);
 public BloodBankDashBoardVO getBloodDonarsSummary();
}
