package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class CadreDetailsVO implements java.io.Serializable{
    
	private Long id;
	private String name;
	private String mobileno;
	private String image;
	private Long constituencyId;
	private String constituency;
	private String districtName;
	private String programName;
	private String designation;
	
	private boolean achievements;
	private boolean goals;
	private boolean leaderShipLevels;
	private boolean communicationSkills;
	private boolean leaderShipSkills;
	private boolean health;
	
	private Long leaderShipLevelId;
	private Long communicationSkillsStatusId;
	private Long leaderShipSkillsStatusId;
	private Long healthStatusId;
	private String remarks;
	
	private boolean smartphoneExist;
	private boolean whatsappUsing;
	private boolean whatsappSharing;
	private boolean facebookUsing;
	
	private String smartphone;
	private String whatsapp;
	private String whatsappShare;
	private String facebook;
	
	private String partyPosition;
	private String position;
	private String healthCardImage;
	
	private Map<Long,CadreDetailsVO> subMap;
	private List<CadreDetailsVO> subList;
	
	private List<SimpleVO> achievementsList;
	private List<SimpleVO> goalsList;
	
	private List<IdNameVO> leadershiplevelslist;
	private List<IdNameVO> communicationsSkillslist;
	private List<IdNameVO> leadershipSkillslist;
	private List<IdNameVO> healthStatuslist;
	private List<IdNameVO> healthCardAttachments;
	private List<IdNameVO> feedbackDocuments;
	private int resultCode;
	private String isFeedbackUpdatable;
	
	private String committeeLevel;
	private String committeeRole;
	private String committeeType;
	private String familyUpdted;
	private String memberShipId;
	private Long feedBackAnswersCount;
	private Long feedBackDocumentsCount;
	
	private Long apptcandidateId;
	private Long apptCount;
	private Long apptUserId;
	private String apptcandidateName;
	private Long marks;
	
	
	
	
	public Long getMarks() {
		return marks;
	}
	public void setMarks(Long marks) {
		this.marks = marks;
	}
	public String getApptcandidateName() {
		return apptcandidateName;
	}
	public void setApptcandidateName(String apptcandidateName) {
		this.apptcandidateName = apptcandidateName;
	}
	public Long getApptcandidateId() {
		return apptcandidateId;
	}
	public void setApptcandidateId(Long apptcandidateId) {
		this.apptcandidateId = apptcandidateId;
	}
	public Long getApptCount() {
		return apptCount;
	}
	public void setApptCount(Long apptCount) {
		this.apptCount = apptCount;
	}
	public Long getApptUserId() {
		return apptUserId;
	}
	public void setApptUserId(Long apptUserId) {
		this.apptUserId = apptUserId;
	}
	
	public Long getFeedBackAnswersCount() {
		return feedBackAnswersCount;
	}
	public void setFeedBackAnswersCount(Long feedBackAnswersCount) {
		this.feedBackAnswersCount = feedBackAnswersCount;
	}
	public Long getFeedBackDocumentsCount() {
		return feedBackDocumentsCount;
	}
	public void setFeedBackDocumentsCount(Long feedBackDocumentsCount) {
		this.feedBackDocumentsCount = feedBackDocumentsCount;
	}
	public List<IdNameVO> getFeedbackDocuments() {
		return feedbackDocuments;
	}
	public void setFeedbackDocuments(List<IdNameVO> feedbackDocuments) {
		this.feedbackDocuments = feedbackDocuments;
	}
	public List<IdNameVO> getHealthCardAttachments() {
		return healthCardAttachments;
	}
	public void setHealthCardAttachments(List<IdNameVO> healthCardAttachments) {
		this.healthCardAttachments = healthCardAttachments;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getCommitteeLevel() {
		return committeeLevel;
	}
	public void setCommitteeLevel(String committeeLevel) {
		this.committeeLevel = committeeLevel;
	}
	public String getCommitteeRole() {
		return committeeRole;
	}
	public void setCommitteeRole(String committeeRole) {
		this.committeeRole = committeeRole;
	}
	public String getCommitteeType() {
		return committeeType;
	}
	public void setCommitteeType(String committeeType) {
		this.committeeType = committeeType;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAchievements() {
		return achievements;
	}
	public void setAchievements(boolean achievements) {
		this.achievements = achievements;
	}
	public boolean isGoals() {
		return goals;
	}
	public void setGoals(boolean goals) {
		this.goals = goals;
	}
	public boolean isLeaderShipLevels() {
		return leaderShipLevels;
	}
	public void setLeaderShipLevels(boolean leaderShipLevels) {
		this.leaderShipLevels = leaderShipLevels;
	}
	public boolean isCommunicationSkills() {
		return communicationSkills;
	}
	public void setCommunicationSkills(boolean communicationSkills) {
		this.communicationSkills = communicationSkills;
	}
	public boolean isLeaderShipSkills() {
		return leaderShipSkills;
	}
	public void setLeaderShipSkills(boolean leaderShipSkills) {
		this.leaderShipSkills = leaderShipSkills;
	}
	public boolean isHealth() {
		return health;
	}
	public void setHealth(boolean health) {
		this.health = health;
	}
	
	public Map<Long, CadreDetailsVO> getSubMap() {
		return subMap;
	}
	public void setSubMap(Map<Long, CadreDetailsVO> subMap) {
		this.subMap = subMap;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public List<CadreDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<CadreDetailsVO> subList) {
		this.subList = subList;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getLeaderShipLevelId() {
		return leaderShipLevelId;
	}
	public void setLeaderShipLevelId(Long leaderShipLevelId) {
		this.leaderShipLevelId = leaderShipLevelId;
	}
	public Long getCommunicationSkillsStatusId() {
		return communicationSkillsStatusId;
	}
	public void setCommunicationSkillsStatusId(Long communicationSkillsStatusId) {
		this.communicationSkillsStatusId = communicationSkillsStatusId;
	}
	public Long getLeaderShipSkillsStatusId() {
		return leaderShipSkillsStatusId;
	}
	public void setLeaderShipSkillsStatusId(Long leaderShipSkillsStatusId) {
		this.leaderShipSkillsStatusId = leaderShipSkillsStatusId;
	}
	public Long getHealthStatusId() {
		return healthStatusId;
	}
	public void setHealthStatusId(Long healthStatusId) {
		this.healthStatusId = healthStatusId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<SimpleVO> getAchievementsList() {
		return achievementsList;
	}
	public void setAchievementsList(List<SimpleVO> achievementsList) {
		this.achievementsList = achievementsList;
	}
	public List<SimpleVO> getGoalsList() {
		return goalsList;
	}
	public void setGoalsList(List<SimpleVO> goalsList) {
		this.goalsList = goalsList;
	}
	public List<IdNameVO> getLeadershiplevelslist() {
		return leadershiplevelslist;
	}
	public void setLeadershiplevelslist(List<IdNameVO> leadershiplevelslist) {
		this.leadershiplevelslist = leadershiplevelslist;
	}
	public List<IdNameVO> getCommunicationsSkillslist() {
		return communicationsSkillslist;
	}
	public void setCommunicationsSkillslist(List<IdNameVO> communicationsSkillslist) {
		this.communicationsSkillslist = communicationsSkillslist;
	}
	public List<IdNameVO> getLeadershipSkillslist() {
		return leadershipSkillslist;
	}
	public void setLeadershipSkillslist(List<IdNameVO> leadershipSkillslist) {
		this.leadershipSkillslist = leadershipSkillslist;
	}
	public List<IdNameVO> getHealthStatuslist() {
		return healthStatuslist;
	}
	public void setHealthStatuslist(List<IdNameVO> healthStatuslist) {
		this.healthStatuslist = healthStatuslist;
	}
	public String getIsFeedbackUpdatable() {
		return isFeedbackUpdatable;
	}
	public void setIsFeedbackUpdatable(String isFeedbackUpdatable) {
		this.isFeedbackUpdatable = isFeedbackUpdatable;
	}
	public String getSmartphone() {
		return smartphone;
	}
	public void setSmartphone(String smartphone) {
		this.smartphone = smartphone;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getWhatsappShare() {
		return whatsappShare;
	}
	public void setWhatsappShare(String whatsappShare) {
		this.whatsappShare = whatsappShare;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	public boolean isSmartphoneExist() {
		return smartphoneExist;
	}
	public void setSmartphoneExist(boolean smartphoneExist) {
		this.smartphoneExist = smartphoneExist;
	}
	public boolean isWhatsappUsing() {
		return whatsappUsing;
	}
	public void setWhatsappUsing(boolean whatsappUsing) {
		this.whatsappUsing = whatsappUsing;
	}
	public boolean isWhatsappSharing() {
		return whatsappSharing;
	}
	public void setWhatsappSharing(boolean whatsappSharing) {
		this.whatsappSharing = whatsappSharing;
	}
	public boolean isFacebookUsing() {
		return facebookUsing;
	}
	public void setFacebookUsing(boolean facebookUsing) {
		this.facebookUsing = facebookUsing;
	}
	public String getPartyPosition() {
		return partyPosition;
	}
	public void setPartyPosition(String partyPosition) {
		this.partyPosition = partyPosition;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getFamilyUpdted() {
		return familyUpdted;
	}
	public void setFamilyUpdted(String familyUpdted) {
		this.familyUpdted = familyUpdted;
	}
	public String getHealthCardImage() {
		return healthCardImage;
	}
	public void setHealthCardImage(String healthCardImage) {
		this.healthCardImage = healthCardImage;
	}
	
                 	
}
