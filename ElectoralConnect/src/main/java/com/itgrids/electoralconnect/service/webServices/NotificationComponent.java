package com.itgrids.electoralconnect.service.webServices;

import java.util.HashMap;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;



import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.service.IAnnouncementService;
import com.sun.jersey.api.core.InjectParam;
import com.sun.jersey.api.spring.Autowire;
//
@Path("/notifications")
@Component
//@Scope("request")
public class NotificationComponent {

	@Autowired
	private IAnnouncementService announcementService ;

	
	public IAnnouncementService getAnnouncementService() {
	
		return announcementService;
	}


	public void setAnnouncementService(IAnnouncementService announcementService) {
		
		this.announcementService = announcementService;
	}
 
	
	@GET
  //  @RolesAllowed("anils")
	@Path("/top5Announcements")
	@Produces(MediaType.APPLICATION_JSON)
   public List<AnnouncementVO>  getTop5Announcements() {
    	
		  return  announcementService.getTop5Announcements();
 
	}
	@GET
    @RolesAllowed("anils")
	@Path("/allAnnouncements/{announcemetTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	
	 public List<AnnouncementVO>  getAllAnnouncements(@PathParam("announcemetTypeId")  long announcemetTypeId) {
	    	
		return  announcementService.getAllAnnouncements(announcemetTypeId);


	}


	

}
