<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${specialPageVO.title}</title>
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>
<style type="text/css">#videogallery a#videolb{display:none}</style>
<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<script type="text/javascript" src="js/specialPage/specialPage.js"></script>
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script> 
<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="styles/candidatePage/candidatePage.css">
</head>

<body>



<!--CONTENT MAIN SECTION START-->


      <!--PROFILE LEFT CONENT SECTION START-->
      <div class="main-title-sec">
	 
 <div class="main-mbg">${specialPageVO.heading}
 <span style="margin-top:10px;margin-right:18px;float:right">
 <a name="fb_share" type="button_count" 
share_url="www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}">Share in Facebook</a> 
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
</script>
</span>
</div>
 <div class="main-bbg"></div></div><br>
      <div class="profile-left-sec">
        <div class="pl-cont-sec">
          <div class="ptd-sec"><img src="${specialPageVO.eventImagePath}" alt=""/> <span class="tc-tf pa-fi">
            <label class="c-red"></label>
            </div>
          <div class="clear"></div>
          <div class="pl-sub-fields"> <span style="margin-left:14px;"></span>
            <ul>
              <li><a href="#">About</a><span></span></li>
              <li><a href="#">News and events</a><span></span></li>
              <li><a href="#">videos</a><span></span></li>
              <li><a href="#">photo gallery</a><span></span></li>
              <li><a href="#">elections</a><span></span></li>
              <li><a href="#">Developments</a><span></span></li>
            </ul>
          </div>
          <div class="clear"></div>
          
          <!--EMAIL ALERT SECTION START-->

             <div class="ea-fc-sec">
              <h2 class="ea-fc-title">email alert <span class="blue-down-arrow">
               <img	src="images/icons/candidatePage/blue-down-arrow.png" alt="" /></span></h2>
                  <div class="ea-fc-cont-sec">Set an email elert to get<br />
                         updates of<br />
                  <span class="li-red">Event</span> <input name="" type="text" id="emailId" class="ea-text-fields" value="your email"
					onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value=''; document.getElementById('alertMsg').innerHTML = '';" />

			<div id="alertMsg" style="dispaly: none"></div>
			<div class="pl-sub-but"><a onclick="validateEmailField()"
				href="javascript:{};"><strong>Set alert</strong></a></div>

		</div>
		</div>

<!--EMAIL ALERT SECTION END--></div>
</div>
      </div>
      
      <!--PROFILE LEFT CONENT SECTION END--> 
      
      <!--PROFILE MIDDLE CONTENT SECTION START-->
      
      <div class="profile-mid-sec">
        <div class="pm-cont-sec"> 
          
          <!--ABOUT POLITICIAN SECTION START-->
          
          <div class="pm-inner-cont-sec" id="pm-inner-cont-sec">
            <h1 class="inc-title">About Event</h1>
            <p></p>
            <div class="read-more"><a href="#">read more</a></div>
          </div>
          
          <!--ABOUT POLITICIAN SECTION END--> 
          
          <!--ELECTION PROFILE SECTION START-->
          
          <div class="pm-inner-cont-sec">
            <h1 class="inc-title">Election Profile</h1>
            
          </div>
          
          <!--ELECTION PROFILE SECTION END--> 
          
          <!--PHOTO GALLERY SECTION START-->
          
          <div class="pm-inner-cont-sec">
           
             <div id="photoGallaryDiv"> </div>
            
          </div>
          
          <!--PHOTO GALLERY SECTION END-->
          
          <div class="clear"></div>
          <p></p>
          
         </div>
      </div>
      
      <!--PROFILE MIDDLE CONTENT SECTION END--> 
      
      <!--PROFILE RIGHT CONTENT SECTION START-->
      
      <div class="profile-right-sec">
        <div class="pr-cont-sec"> 
          
          <!--NEWS AND EVENTS SECTION START-->
          
          <div class="pr-sub-fields-sec" style="margin-bottom:0px; border-bottom:0px;">
            
            <div class="news-events-fields">
              <div id="newsDisplayDiv"></div>
              <!--<div class="more"><a href="#">more</a></div>--> 
            </div>
          </div>
          
          <!--NEWS AND EVENTS SECTION END--> 
          
          <!--VIDEOS SECTION START-->
          <s:if test="fileVOList != null && fileVOList.size() > 0"> 
		<div class="pr-sub-fields-sec">
            <h1 class="pr-title">videos<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span> </h1>
		<div id="videogallery" class="fleft">

	<s:iterator status="stat" value="fileVOList">
		
		<s:if test="#stat.index == 0">
		<DIV>
		<a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width: 297px; height: 227px;"/></a>
		</DIV>
		</s:if>
	</s:iterator>

	<s:if test="fileVOList.size() > 1"> 
		<ul class="video-thumb-sec">
			<s:iterator status="stat" value="fileVOList">
				<s:if test="#stat.index >= 1 && #stat.index <= 3">
				<li><a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer' style="width:72px;">
				<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width:95px;height:80px;"/></a></li>
				</s:if>
			</s:iterator>
		</ul>
	</s:if>
    </div>
  </s:if>
    <s:if test="fileVOList != null && fileVOList.size() > 4"> 
	 <div class="more"><a onClick="videoGallaryPopUp(fileVOList);" href="javascript:{};">More</a></div>
	 </s:if>
	<div id="videoGallaryPopUpDiv"></div>



       <!-- VIDEOS SECTION END--> 
          
          <!--SEND MESSAGE TO POLITICIAN SECTION START-->

			<div class="pr-sub-fields-sec">
				<h1 class="pr-title">send a message to Party Analyst <span
			class="or-down-arrow"><img src="images/icons/or-down-arrow.png"
				alt="" /></span></h1>
			<ul class="cbn-fields-sec">
				<li><label>Name :</label>
			<div class="text-fields-sec"><input name="" type="text"
			class="text-fields" /></div>
			</li>
			<li><label>location :</label>
			<div class="text-fields-sec"><input name="" type="text"
			class="text-fields" /></div>
			</li>
		<li><label>Message</label>
		<div class="text-fields-sec"><textarea name="" cols="" rows=""></textarea>
		</div>
		</li>
		<li><input name="" type="submit" value="" class="send-but" /></li>
	</ul> 
   </div>    
      <div id="showProfile"></div>
      <!--PROFILE RIGHT CONTENT SECTION END--> 
      
    <!--CONTENT SECTION END--> 
 
 <!--CONTENT MAIN SECTION END--> 

<script type="text/javascript">
  var specialPageId = '${specialPageId}';

 function displayProfile()
 {
   var profileInfoElmt = document.getElementById("pm-inner-cont-sec");
   
   if(profileInfoElmt == null)
	   return;
   
   var str = '';
   var descFlag = 1;
   
   str += '<s:if test="descriptions != null"> ';
   str += '<h1 class="inc-title">About ${specialPageVO.heading}</h1>';
   
   str += '<s:iterator value="descriptions">';
   
   if(descFlag <= 2)
   {
	  str += '  <p style="font-size:13px;text-align:justify;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property/>';
	  descFlag++;
   }
   str += '  </s:iterator>';

   str += '<div class="read-more"><a href="javascript:{}" onclick="getTotalProfile()" style="color: LightSkyBlue;">';
   str += 'Read More >></a></div>';
   str += '</s:if>';
   
   profileInfoElmt.innerHTML = str;
 }

 
 function getTotalProfile()
 {

 $.fx.speeds._default = 900;
  $("#showProfile").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:600,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy"><b>${specialPageVO.heading}</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showProfile").dialog();
   
 
   var str ='';
    str+='<fieldset class="imgFieldset">';
    str+='  <table><tr><td>';
    str+='  <s:if test="descriptions != null">'; 
    str+='  <div style="font-weight: bold; font-size: 14px;">About ${specialPageVO.heading}</div>';
    str+=' <br><s:iterator value="descriptions">';
	str+=' <div style="margin-bottom:-5px; font-weight: normal; font-size: 11px; font-family: tahoma;text-align:justify;">';
    str+=' <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:property />';
	str+='</div>';
    str+='</s:iterator>';
    str+=' </s:if>';
    str+=' </td></tr> </table>';
    str+='</fieldset>';
    document.getElementById("showProfile").innerHTML=str;
   
    
 }
	function callAjax(jsObj,url)
	{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
		 if(jsObj.task == "setEmailAlertsForEvent")
            {
			   showStatusForEmailSubscription(myResults);
			}

			}catch(e)

			{   
			 alert("Invalid JSON result" + e);   
			}
		 },
			scope : this,
			failure : function( o )
			{
										//alert( "Failed to load result" + o.status + " " + o.statusText);
			}
		  };

		 	YAHOO.util.Connect.asyncRequest('GET', url, callback);
			}
	 function validateEmailField()
	  {
		 document.getElementById("alertMsg").innerHTML = '';
			var emailIdVal = document.getElementById("emailId").value;
			var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
	      if(emailIdVal !='' && emailIdVal!='your email'){
	          
			  if(!emailIdVal.match(emailExp)){

					document.getElementById("alertMsg").innerHTML = '<font color="red">Please enter valid Email</font>';
					return;
			  }
		}
		 else {
			document.getElementById("alertMsg").innerHTML ='<font color="red">Please enter Email id</font>';  
			return;
		 	}
			 var jsObj={
	 			emailId : emailIdVal,
	 			specialPageId : 1,
	 			task:"setEmailAlertsForEvent"
		 	};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url="specialEmailAlertsForUserAction.action?"+rparam;
		callAjax(jsObj,url);

		}

		function showStatusForEmailSubscription(results){

	   		var str='';
			if(results.resultCode == 0){
			cleardescriptionFields();
		
			document.getElementById("alertMsg").innerHTML='<font color="green">You are Subscribed For Email alerts Successfully</font>';
				}
	    
		}
		function cleardescriptionFields(){
			document.getElementById('emailId').value='';
		}
var specialPageId = '${specialPageVO.specialPageId}';
getTotalNews('getFirstFourNewsRecordsToDisplay');
getFirstThreePhotoRecords();
displayProfile();
</script>
</body>
</html>