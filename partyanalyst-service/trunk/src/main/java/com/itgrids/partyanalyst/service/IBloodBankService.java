package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BloodBankDashBoardVO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.dto.DonationsInBloodBankVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IBloodBankService {

 public List<BloodBankVO> getOccupationList();
 public List<BloodBankVO> getEducationalQualificationsList();
 public BloodBankVO getCadreDetails(String memberShipNO);
 public List<IdNameVO> getAcceptanceStatus();
 public List<IdNameVO> getBloodBagType();
 public List<IdNameVO> getBloodBagQuantity();
 public List<BloodBankVO> getBleedingCadreDetails(List<Long> statusIds,Long campId);
 public BloodBankDashBoardVO getBloodDonarsCountsSummary(Long campId);
 public BloodBankDashBoardVO gettotalCollectedBloodDetails(Long campId);
 public Long getBloodDonatedOtherThanBloodBank();
 public Long getBloodDonorInEmergency();
 public Long getCalledForDonationCount();
 public BloodBankDashBoardVO gettotalCollectedBloodBagsInfo(Long campId);
 public List<BloodBankDashBoardVO> getBloodDonorDetailsByAgeGroupingInfo(Long campId);
// public BloodBankDashBoardVO getBloodDonarsSummary(Long campId);
 public  ResultStatus saveBloodBankCadreDetails(final BloodBankVO bloodBanKVO,final Long userId);
 public List<BloodBankVO> getBloodComponentList();
 public DonationsInBloodBankVO getNumberOfTimesCollectedBlood(Long campId);
 public ResultStatus saveBleedingDetails(BloodBankVO bloodBankVO);
 public ResultStatus updatePrintstatus(Long id);
 public BloodBankDashBoardVO getDistrictWiseBloodDonorCounts(Long campId,Long stateId,String type);
}
