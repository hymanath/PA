package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmailNotificationVO implements Serializable {
   /**
	 * 
	 */
   private static final long serialVersionUID = -7354846330897302533L;
   
   private Long id;
   private String name;
   
   private String title;   
   private Long fileGallaryId;
   private String gallaryName;
   private String source;
   private String language;
   private String description;
   private String subscriberName;
   private String subscriberEmail;
   private String filePath;
   

        //Contains all subscribed party Page related information, if exist for a user
   private List<EmailNotificationVO> partyPage = new ArrayList<EmailNotificationVO>();
   
        //Contains all subscribed candidate Page related information, if exist for a user
   private List<EmailNotificationVO> candidatePage = new ArrayList<EmailNotificationVO>();
   
   	   //Contains all subscribed special Page related information, if exist for a user
   private List<EmailNotificationVO> specialPage = new ArrayList<EmailNotificationVO>();
   
   
       //used to check for a user, party page updates exist or not for a subscribed party page
   private boolean partyPagepresent;
       //used to check for a user, candidate page updates exist or not for a subscribed candidate page
   private boolean candidatePagepresent;
       //used to check for a user, special page updates exist or not for a subscribed special page
   private boolean specialPagepresent;
   
   
       //Contains all news related information, if exist for a party or candidate or special Page etc
   private List<EmailNotificationVO> news = new ArrayList<EmailNotificationVO>();
   
       //Contains all photos related information, if exist for a party or candidate or special Page etc
   private List<EmailNotificationVO> photos = new ArrayList<EmailNotificationVO>();
   
       //Contains all videos related information, if exist for a party or candidate or special Page etc
   private List<EmailNotificationVO> videos = new ArrayList<EmailNotificationVO>();
   
   
       //used to check for a party or candidate or special Page etc, news updates exist or not 
   private boolean newspresent;
       //used to check for a party or candidate or special Page etc, photo updates exist or not 
   private boolean photopresent;
       //used to check for a party or candidate or special Page etc, video updates exist or not 
   private boolean videopresent;
   
   private Set<String> candidates = new HashSet<String>();
   private Set<String> parties = new HashSet<String>();
   private Set<String>specialPgs = new HashSet<String>();

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Long getFileGallaryId() {
		return fileGallaryId;
	}
	
	public void setFileGallaryId(Long fileGallaryId) {
		this.fileGallaryId = fileGallaryId;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public List<EmailNotificationVO> getNews() {
		return news;
	}
	
	public void setNews(List<EmailNotificationVO> news) {
		this.news = news;
	}
	
	public List<EmailNotificationVO> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<EmailNotificationVO> photos) {
		this.photos = photos;
	}
	
	public List<EmailNotificationVO> getVideos() {
		return videos;
	}
	
	public void setVideos(List<EmailNotificationVO> videos) {
		this.videos = videos;
	}
	
	public String getGallaryName() {
		return gallaryName;
	}
	
	public void setGallaryName(String gallaryName) {
		this.gallaryName = gallaryName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSubscriberName() {
		return subscriberName;
	}
	
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	
	public String getSubscriberEmail() {
		return subscriberEmail;
	}

	public void setSubscriberEmail(String subscriberEmail) {
		this.subscriberEmail = subscriberEmail;
	}

	public List<EmailNotificationVO> getPartyPage() {
		return partyPage;
	}
	
	public void setPartyPage(List<EmailNotificationVO> partyPage) {
		this.partyPage = partyPage;
	}
	
	public List<EmailNotificationVO> getCandidatePage() {
		return candidatePage;
	}
	
	public void setCandidatePage(List<EmailNotificationVO> candidatePage) {
		this.candidatePage = candidatePage;
	}
	
	public List<EmailNotificationVO> getSpecialPage() {
		return specialPage;
	}
	
	public void setSpecialPage(List<EmailNotificationVO> specialPage) {
		this.specialPage = specialPage;
	}
	
	public boolean isPartyPagepresent() {
		return partyPagepresent;
	}
	
	public void setPartyPagepresent(boolean partyPagepresent) {
		this.partyPagepresent = partyPagepresent;
	}
	
	public boolean isCandidatePagepresent() {
		return candidatePagepresent;
	}
	
	public void setCandidatePagepresent(boolean candidatePagepresent) {
		this.candidatePagepresent = candidatePagepresent;
	}
	
	public boolean isSpecialPagepresent() {
		return specialPagepresent;
	}
	
	public void setSpecialPagepresent(boolean specialPagepresent) {
		this.specialPagepresent = specialPagepresent;
	}
	
	public boolean isNewspresent() {
		return newspresent;
	}
	
	public void setNewspresent(boolean newspresent) {
		this.newspresent = newspresent;
	}
	
	public boolean isPhotopresent() {
		return photopresent;
	}
	
	public void setPhotopresent(boolean photopresent) {
		this.photopresent = photopresent;
	}
	
	public boolean isVideopresent() {
		return videopresent;
	}
	
	public void setVideopresent(boolean videopresent) {
		this.videopresent = videopresent;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Set<String> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<String> candidates) {
		this.candidates = candidates;
	}

	public Set<String> getParties() {
		return parties;
	}

	public void setParties(Set<String> parties) {
		this.parties = parties;
	}

	public Set<String> getSpecialPgs() {
		return specialPgs;
	}

	public void setSpecialPgs(Set<String> specialPgs) {
		this.specialPgs = specialPgs;
	}


	 
	   
}
