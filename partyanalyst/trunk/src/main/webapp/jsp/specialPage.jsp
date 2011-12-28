<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Partyanalyst</title>
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>
<style type="text/css">#videogallery a#videolb{display:none}</style>
<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script> 
<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
</head>

<body>

 

<!--CONTENT MAIN SECTION START-->


      <!--PROFILE LEFT CONENT SECTION START-->
      <div class="main-title-sec">
 <div class="main-mbg">EVENT
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
          <div class="ptd-sec"><img src="images/icons/group-default.png" alt=""/> <span class="tc-tf pa-fi">
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
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images/icons/blue-down-arrow.png" alt=""/></span> </h2>
            <div class="ea-fc-cont-sec"> Set an email elert to get<br />
              updates of<br />
              <span class="li-red">Event</span>
              <input name="" type="text" class="ea-text-fields" value="your email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
              <div class="pl-sub-but"><a href="#"><strong>Set alert</strong></a></div>
            </div>
          </div>
          
          <!--EMAIL ALERT SECTION END--> 
          
          
          
        </div>
      </div>
      
      <!--PROFILE LEFT CONENT SECTION END--> 
      
      <!--PROFILE MIDDLE CONTENT SECTION START-->
      
      <div class="profile-mid-sec">
        <div class="pm-cont-sec"> 
          
          <!--ABOUT POLITICIAN SECTION START-->
          
          <div class="pm-inner-cont-sec">
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
            <h3 class="main-title"><span class="da-gray">Photo Gallery</span></h3>
            <ul class="photo-gallery-fields">
              
            </ul>
            <div class="more"><a href="#">more</a></div>
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
            <h1 class="pr-title">news &amp; Events <span class="or-down-arrow"><img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>
            <div class="news-events-fields">
              
              <div class="more"><a href="#">more</a></div>
            </div>
          </div>
          
          <!--NEWS AND EVENTS SECTION END--> 
          
          <!--VIDEOS SECTION START-->
          
          <div class="pr-sub-fields-sec">
            <h1 class="pr-title">videos <span class="or-down-arrow"><img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>
            <ul class="video-thumb-sec">
              <li><a href="#"><img src="" alt=""/>  </a></li>
              <li><a href="#"><img src="" alt=""/> </a></li>
              <li style="padding-right:0px;"><a href="#"><img src="" alt=""/> </a></li>
            </ul>
            <div class="more"><a href="#">more</a></div>
          </div>
          
          <!--VIDEOS SECTION END--> 
          
          <!--SEND MESSAGE TO POLITICIAN SECTION START-->
          
          <div class="pr-sub-fields-sec">
          <h1 class="pr-title">send a message to Party Analyst <span class="or-down-arrow"><img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>
            <ul class="cbn-fields-sec">
              <li>
                <label>Name :</label>
                <div class="text-fields-sec">
                  <input name="" type="text" class="text-fields"/>
                </div>
              </li>
              <li>
                <label>location :</label>
                <div class="text-fields-sec">
                  <input name="" type="text" class="text-fields"/>
                </div>
              </li>
              <li>
                <label>Message</label>
                <div class="text-fields-sec">
                  <textarea name="" cols="" rows=""></textarea>
                </div>
              </li>
              <li>
                <input name="" type="submit" value="" class="send-but"/>
              </li>
            </ul>
          </div>
          
          <!--SEND MESSAGE TO POLITICIAN SECTION END--> 
       
      
      <!--PROFILE RIGHT CONTENT SECTION END--> 
      
    <!--CONTENT SECTION END--> 
 
 <!--CONTENT MAIN SECTION END--> 


</body>
</html>