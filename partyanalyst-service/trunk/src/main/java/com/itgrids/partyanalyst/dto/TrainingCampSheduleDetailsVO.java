package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sys
 *
 */
public class TrainingCampSheduleDetailsVO implements java.io.Serializable{
	
	    private Long batchId;
		private String batchCode;
		private String batchName;
		private String startDateStr;
		private String endDateStr;
		private Long programId;
		private String programName;
		private Long campId;
		private String campName;
	    private Long nonInviteeCount=0L;
	    private Long inviteeCount=0L;
	    private Long scheduleId;
		private String scheduleName;
		private Long trainingCampBatchTypeId;
		private List<TrainingCampSheduleDetailsVO> campDetails =new ArrayList<TrainingCampSheduleDetailsVO>(0);
		private List<TrainingCampSheduleDetailsVO> scheduleDetails =new ArrayList<TrainingCampSheduleDetailsVO>(0);
		private List<TrainingCampSheduleDetailsVO> batchDetails=new ArrayList<TrainingCampSheduleDetailsVO>(0);
		public Long getBatchId() {
			return batchId;
		}
		public void setBatchId(Long batchId) {
			this.batchId = batchId;
		}
		public String getBatchCode() {
			return batchCode;
		}
		public void setBatchCode(String batchCode) {
			this.batchCode = batchCode;
		}
		public String getBatchName() {
			return batchName;
		}
		public void setBatchName(String batchName) {
			this.batchName = batchName;
		}
		public String getStartDateStr() {
			return startDateStr;
		}
		public void setStartDateStr(String startDateStr) {
			this.startDateStr = startDateStr;
		}
		public String getEndDateStr() {
			return endDateStr;
		}
		public void setEndDateStr(String endDateStr) {
			this.endDateStr = endDateStr;
		}
		public Long getProgramId() {
			return programId;
		}
		public void setProgramId(Long programId) {
			this.programId = programId;
		}
		public String getProgramName() {
			return programName;
		}
		public void setProgramName(String programName) {
			this.programName = programName;
		}
		public Long getCampId() {
			return campId;
		}
		public void setCampId(Long campId) {
			this.campId = campId;
		}
		public String getCampName() {
			return campName;
		}
		public void setCampName(String campName) {
			this.campName = campName;
		}
		public Long getNonInviteeCount() {
			return nonInviteeCount;
		}
		public void setNonInviteeCount(Long nonInviteeCount) {
			this.nonInviteeCount = nonInviteeCount;
		}
		public Long getInviteeCount() {
			return inviteeCount;
		}
		public void setInviteeCount(Long inviteeCount) {
			this.inviteeCount = inviteeCount;
		}
		public Long getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(Long scheduleId) {
			this.scheduleId = scheduleId;
		}
		public String getScheduleName() {
			return scheduleName;
		}
		public void setScheduleName(String scheduleName) {
			this.scheduleName = scheduleName;
		}
		public List<TrainingCampSheduleDetailsVO> getCampDetails() {
			return campDetails;
		}
		public void setCampDetails(List<TrainingCampSheduleDetailsVO> campDetails) {
			this.campDetails = campDetails;
		}
		public List<TrainingCampSheduleDetailsVO> getScheduleDetails() {
			return scheduleDetails;
		}
		public void setScheduleDetails(List<TrainingCampSheduleDetailsVO> scheduleDetails) {
			this.scheduleDetails = scheduleDetails;
		}
		public List<TrainingCampSheduleDetailsVO> getBatchDetails() {
			return batchDetails;
		}
		public void setBatchDetails(List<TrainingCampSheduleDetailsVO> batchDetails) {
			this.batchDetails = batchDetails;
		}
		
		public Long getTrainingCampBatchTypeId() {
			return trainingCampBatchTypeId;
		}
		public void setTrainingCampBatchTypeId(Long trainingCampBatchTypeId) {
			this.trainingCampBatchTypeId = trainingCampBatchTypeId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((campId == null) ? 0 : campId.hashCode());
			result = prime * result
					+ ((campName == null) ? 0 : campName.hashCode());
			result = prime * result
					+ ((programId == null) ? 0 : programId.hashCode());
			result = prime * result
					+ ((programName == null) ? 0 : programName.hashCode());
			result = prime * result
					+ ((scheduleId == null) ? 0 : scheduleId.hashCode());
			result = prime * result
					+ ((scheduleName == null) ? 0 : scheduleName.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TrainingCampSheduleDetailsVO other = (TrainingCampSheduleDetailsVO) obj;
			if (campId == null) {
				if (other.campId != null)
					return false;
			} else if (!campId.equals(other.campId))
				return false;
			if (campName == null) {
				if (other.campName != null)
					return false;
			} else if (!campName.equals(other.campName))
				return false;
			if (programId == null) {
				if (other.programId != null)
					return false;
			} else if (!programId.equals(other.programId))
				return false;
			if (programName == null) {
				if (other.programName != null)
					return false;
			} else if (!programName.equals(other.programName))
				return false;
			if (scheduleId == null) {
				if (other.scheduleId != null)
					return false;
			} else if (!scheduleId.equals(other.scheduleId))
				return false;
			if (scheduleName == null) {
				if (other.scheduleName != null)
					return false;
			} else if (!scheduleName.equals(other.scheduleName))
				return false;
			return true;
		}
		
	}


