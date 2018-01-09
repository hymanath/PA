package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alert extends BaseModel implements Serializable {
	private Long alertId;
	private AlertType alertType;
	private Long alertTypeId;
	private String description;
	private AlertStatus alertStatus;
	private Long alertStatusId;
	private AlertSeverity alertSeverity;
	private Long alertSeverityId;
	private Long impactLevelId;
	private Long impactLevelValue;
	private UserAddress userAddress;
	private Long addressId;
	private Long userTypeId;
	private Long createdBy;
	private Date createdTime;
	private String isDeleted;
	private Long updatedBy;
	private Date updatedTime;
	private RegionScopes regionScopes;
	private AlertUserType alertUserType;
	private AlertSource alertSource;
	private Long alertSourceId;
	
	private Long alertCategoryId;
	private Long alertCategoryTypeId;
	private AlertCategory alertCategory;
	
	private String title;
	
	private Long impactScopeId;
	private AlertImpactScope alertImpactScope;
	
	private String imageUrl;
	private Long editionTypeId;
	private EditionType editionType;
	private Long tvNewsChannelId;
	private TvNewsChannel tvNewsChannel;
	private Long editionId;
	private Editions edition;
	
	private Long govtDepartmentId;
	private GovtDepartment govtDepartment;
	
	private String isMultiple;
	private Long newsUserId;
	
	private Long alertCallerId;
	private Long alertCallerTypeId;
	private Long alertEntrySourceId;
	private AlertCaller alertCaller;
	private AlertCallerType alertCallerType;
	private AlertEntrySource alertEntrySource;
	private Long alertIssueTypeId;
	private AlertIssueType alertIssueType;
	private Long alertFeedbackStatusId;
	private AlertFeedbackStatus alertFeedbackStatus;
	private AlertIssueSubType alertIssueSubType;
	private Long alertIssueSubTypeId;
	private User userId;
	
	private Long socialMediaTypeId;
	private SocialMediaType socialMediaType;
	private Long alertCallCenterTypeId;
	private  AlertCallCenterType alertCallCenterType;
	
	private String isVerified;
	private Long mondayGrievanceTypeId;
	private Long janmabhoomiTypeId;
	private Long specialGrievanceTypeId;
	private Long generalGrievanceTypeId;
	
	private MondayGrievanceType mondayGrievanceType;
	private JanmabhoomiType janmabhoomiType;
	private SpecialGrievanceType specialGrievanceType;
	private GeneralGrievanceType generalGrievanceType;
	
	private String zohoTicketId;
	private String apiType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_id", unique = true, nullable = false)
	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name = "alert_type_id")
	public Long getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}

	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}

	@Column(name = "alert_severity_id")
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}

	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}

	@Column(name = "impact_level_id")
	public Long getImpactLevelId() {
		return impactLevelId;
	}

	public void setImpactLevelId(Long impactLevelId) {
		this.impactLevelId = impactLevelId;
	}

	@Column(name = "impact_level_value")
	public Long getImpactLevelValue() {
		return impactLevelValue;
	}

	public void setImpactLevelValue(Long impactLevelValue) {
		this.impactLevelValue = impactLevelValue;
	}

	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "user_type_id")
	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_time")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_status_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_severity_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertSeverity getAlertSeverity() {
		return alertSeverity;
	}

	public void setAlertSeverity(AlertSeverity alertSeverity) {
		this.alertSeverity = alertSeverity;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "impact_level_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertUserType getAlertUserType() {
		return alertUserType;
	}

	public void setAlertUserType(AlertUserType alertUserType) {
		this.alertUserType = alertUserType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_source_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertSource getAlertSource() {
		return alertSource;
	}

	public void setAlertSource(AlertSource alertSource) {
		this.alertSource = alertSource;
	}
	@Column(name = "alert_source_id")
	public Long getAlertSourceId() {
		return alertSourceId;
	}

	public void setAlertSourceId(Long alertSourceId) {
		this.alertSourceId = alertSourceId;
	}

	@Column(name = "alert_category_id")
	public Long getAlertCategoryId() {
		return alertCategoryId;
	}

	public void setAlertCategoryId(Long alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	@Column(name = "alert_category_type_id")
	public Long getAlertCategoryTypeId() {
		return alertCategoryTypeId;
	}

	public void setAlertCategoryTypeId(Long alertCategoryTypeId) {
		this.alertCategoryTypeId = alertCategoryTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_category_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertCategory getAlertCategory() {
		return alertCategory;
	}

	public void setAlertCategory(AlertCategory alertCategory) {
		this.alertCategory = alertCategory;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "impact_scope_id")
	public Long getImpactScopeId() {
		return impactScopeId;
	}

	public void setImpactScopeId(Long impactScopeId) {
		this.impactScopeId = impactScopeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "impact_scope_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertImpactScope getAlertImpactScope() {
		return alertImpactScope;
	}

	public void setAlertImpactScope(AlertImpactScope alertImpactScope) {
		this.alertImpactScope = alertImpactScope;
	}
	@Column(name = "image_url")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "edition_type_id")
	public Long getEditionTypeId() {
		return editionTypeId;
	}

	public void setEditionTypeId(Long editionTypeId) {
		this.editionTypeId = editionTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "edition_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public EditionType getEditionType() {
		return editionType;
	}

	public void setEditionType(EditionType editionType) {
		this.editionType = editionType;
	}

	@Column(name = "tv_news_channel_id")
	public Long getTvNewsChannelId() {
		return tvNewsChannelId;
	}

	public void setTvNewsChannelId(Long tvNewsChannelId) {
		this.tvNewsChannelId = tvNewsChannelId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tv_news_channel_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TvNewsChannel getTvNewsChannel() {
		return tvNewsChannel;
	}

	public void setTvNewsChannel(TvNewsChannel tvNewsChannel) {
		this.tvNewsChannel = tvNewsChannel;
	}

	@Column(name = "edition_id")
	public Long getEditionId() {
		return editionId;
	}

	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "edition_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Editions getEdition() {
		return edition;
	}

	public void setEdition(Editions edition) {
		this.edition = edition;
	}

	@Column(name = "govt_department_id")
	public Long getGovtDepartmentId() {
		return govtDepartmentId;
	}

	public void setGovtDepartmentId(Long govtDepartmentId) {
		this.govtDepartmentId = govtDepartmentId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartment getGovtDepartment() {
		return govtDepartment;
	}

	public void setGovtDepartment(GovtDepartment govtDepartment) {
		this.govtDepartment = govtDepartment;
	}

	@Column(name = "is_multiple")
	public String getIsMultiple() {
		return isMultiple;
	}

	public void setIsMultiple(String isMultiple) {
		this.isMultiple = isMultiple;
	}
	@Column(name = "news_user_id")
	public Long getNewsUserId() {
		return newsUserId;
	}

	public void setNewsUserId(Long newsUserId) {
		this.newsUserId = newsUserId;
	}

	@Column(name = "alert_caller_id")
	public Long getAlertCallerId() {
		return alertCallerId;
	}
	public void setAlertCallerId(Long alertCallerId) {
		this.alertCallerId = alertCallerId;
	}

	@Column(name = "alert_caller_type_id")
	public Long getAlertCallerTypeId() {
		return alertCallerTypeId;
	}
	public void setAlertCallerTypeId(Long alertCallerTypeId) {
		this.alertCallerTypeId = alertCallerTypeId;
	}

	@Column(name = "alert_entry_source_id")
	public Long getAlertEntrySourceId() {
		return alertEntrySourceId;
	}
	public void setAlertEntrySourceId(Long alertEntrySourceId) {
		this.alertEntrySourceId = alertEntrySourceId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_caller_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertCaller getAlertCaller() {
		return alertCaller;
	}
	public void setAlertCaller(AlertCaller alertCaller) {
		this.alertCaller = alertCaller;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_caller_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertCallerType getAlertCallerType() {
		return alertCallerType;
	}
	public void setAlertCallerType(AlertCallerType alertCallerType) {
		this.alertCallerType = alertCallerType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_entry_source_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertEntrySource getAlertEntrySource() {
		return alertEntrySource;
	}
	public void setAlertEntrySource(AlertEntrySource alertEntrySource) {
		this.alertEntrySource = alertEntrySource;
	}

	@Column(name = "alert_issue_type_id")
	public Long getAlertIssueTypeId() {
		return alertIssueTypeId;
	}
	public void setAlertIssueTypeId(Long alertIssueTypeId) {
		this.alertIssueTypeId = alertIssueTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_issue_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertIssueType getAlertIssueType() {
		return alertIssueType;
	}
	public void setAlertIssueType(AlertIssueType alertIssueType) {
		this.alertIssueType = alertIssueType;
	}

	@Column(name = "alert_feedback_status_id")
	public Long getAlertFeedbackStatusId() {
		return alertFeedbackStatusId;
	}

	public void setAlertFeedbackStatusId(Long alertFeedbackStatusId) {
		this.alertFeedbackStatusId = alertFeedbackStatusId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_feedback_status_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertFeedbackStatus getAlertFeedbackStatus() {
		return alertFeedbackStatus;
	}

	public void setAlertFeedbackStatus(AlertFeedbackStatus alertFeedbackStatus) {
		this.alertFeedbackStatus = alertFeedbackStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_issue_sub_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertIssueSubType getAlertIssueSubType() {
		return alertIssueSubType;
	}

	public void setAlertIssueSubType(AlertIssueSubType alertIssueSubType) {
		this.alertIssueSubType = alertIssueSubType;
	}

	@Column(name = "alert_issue_sub_type_id")
	public Long getAlertIssueSubTypeId() {
		return alertIssueSubTypeId;
	}

	public void setAlertIssueSubTypeId(Long alertIssueSubTypeId) {
		this.alertIssueSubTypeId = alertIssueSubTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	@Column(name = "social_media_type_id")
	public Long getSocialMediaTypeId() {
		return socialMediaTypeId;
	}

	public void setSocialMediaTypeId(Long socialMediaTypeId) {
		this.socialMediaTypeId = socialMediaTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "social_media_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SocialMediaType getSocialMediaType() {
		return socialMediaType;
	}

	public void setSocialMediaType(SocialMediaType socialMediaType) {
		this.socialMediaType = socialMediaType;
	}
	@Column(name = "alert_call_center_type_id")
	public Long getAlertCallCenterTypeId() {
		return alertCallCenterTypeId;
	}

	public void setAlertCallCenterTypeId(Long alertCallCenterTypeId) {
		this.alertCallCenterTypeId = alertCallCenterTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_call_center_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertCallCenterType getAlertCallCenterType() {
		return alertCallCenterType;
	}

	public void setAlertCallCenterType(AlertCallCenterType alertCallCenterType) {
		this.alertCallCenterType = alertCallCenterType;
	}
	
	@Column(name = "is_verified")
	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	@Column(name = "monday_grievance_type_id")
	public Long getMondayGrievanceTypeId() {
		return mondayGrievanceTypeId;
	}
	public void setMondayGrievanceTypeId(Long mondayGrievanceTypeId) {
		this.mondayGrievanceTypeId = mondayGrievanceTypeId;
	}

	@Column(name = "janmabhoomi_type_id")
	public Long getJanmabhoomiTypeId() {
		return janmabhoomiTypeId;
	}
	public void setJanmabhoomiTypeId(Long janmabhoomiTypeId) {
		this.janmabhoomiTypeId = janmabhoomiTypeId;
	}

	@Column(name = "special_grievance_type_id")
	public Long getSpecialGrievanceTypeId() {
		return specialGrievanceTypeId;
	}
	public void setSpecialGrievanceTypeId(Long specialGrievanceTypeId) {
		this.specialGrievanceTypeId = specialGrievanceTypeId;
	}

	@Column(name = "general_grievance_type_id")
	public Long getGeneralGrievanceTypeId() {
		return generalGrievanceTypeId;
	}
	public void setGeneralGrievanceTypeId(Long generalGrievanceTypeId) {
		this.generalGrievanceTypeId = generalGrievanceTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "monday_grievance_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public MondayGrievanceType getMondayGrievanceType() {
		return mondayGrievanceType;
	}
	public void setMondayGrievanceType(MondayGrievanceType mondayGrievanceType) {
		this.mondayGrievanceType = mondayGrievanceType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "janmabhoomi_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public JanmabhoomiType getJanmabhoomiType() {
		return janmabhoomiType;
	}
	public void setJanmabhoomiType(JanmabhoomiType janmabhoomiType) {
		this.janmabhoomiType = janmabhoomiType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "special_grievance_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public SpecialGrievanceType getSpecialGrievanceType() {
		return specialGrievanceType;
	}
	public void setSpecialGrievanceType(SpecialGrievanceType specialGrievanceType) {
		this.specialGrievanceType = specialGrievanceType;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "general_grievance_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GeneralGrievanceType getGeneralGrievanceType() {
		return generalGrievanceType;
	}
	public void setGeneralGrievanceType(GeneralGrievanceType generalGrievanceType) {
		this.generalGrievanceType = generalGrievanceType;
	}
	@Column(name = "zoho_ticket_id")
	public String getZohoTicketId() {
		return zohoTicketId;
	}

	public void setZohoTicketId(String zohoTicketId) {
		this.zohoTicketId = zohoTicketId;
	}
	@Column(name = "api_type")
	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	
	
}
