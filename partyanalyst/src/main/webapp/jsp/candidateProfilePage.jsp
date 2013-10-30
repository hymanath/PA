<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.itgrids.partyanalyst.utils.IConstants"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${candidateVO.candidateName}'S PROFILE, NEWS, PHOTOS, CONSTITUENCY, ELECTION DETAILS AND ELECTION RESULTS WITH ANALYSIS</title>
<META NAME="Keywords" CONTENT="${candidateVO.candidateName}'S PROFILE, NEWS, PHOTOS, CONSTITUENCY, ELECTION DETAILS AND ELECTION RESULTS WITH ANALYSIS"/>
<meta name="description" content="${candidateVO.candidateName}'S PROFILE, NEWS, PHOTOS, CONSTITUENCY, ELECTION DETAILS AND ELECTION RESULTS WITH ANALYSIS"/>
<meta property="fb:app_id" content="167844749984003"/>
<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>

<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<!--<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>-->
<style type="text/css">#videogallery a#videolb{display:none}</style>
<!--<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>-->
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script>
<!--<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  -->
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<!--<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>-->
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
<!--  for pagination purpose -->
<script type="text/javascript" src="js/customPaginator/totalNewsPaginator.js"></script>
<script type="text/javascript" src="js/customPaginator/messagePaginator.js"></script>
<script type='text/javascript' src="http://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.0.1/bootstrap.min.js"></script>
<style type="text/css">
#showAllNewsDiv #videoGallaryPopUpDiv #buildPhotoGallaryDiv
#showProfile .ui-widget-header {
	background:url("");
	border:0px;
	color:none;
	font-weight:bold;
	}
	.unsc{
	color:red;
	}
.imageButton{
	
	-moz-border-radius: 4px 4px 4px 4px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}

.popupcontainer {		
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 780px;
    	padding: 10px;
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
}

.titleStyle {
    font-family:sans-serif;
    font-size: 13px;
    font-weight: bold;
	color:black;
    text-decoration:none;
 }
 p.sourceColor {  
    color:#FF4500;
 }
 .imgFieldset
  {
	-moz-border-radius: 4px 4px 4px 4px;
	border			: 2px solid #CCCCCC;
    margin-bottom	: 10px;
	margin-top		: 5px;
  }
  .photoStyle {
   height:120px;
   margin-bottom:4px;
   margin-left:3px;
   width:133px;
   cursor:pointer;
  }
  .gallaryImg
{
	width  : 150px;
	height : 130px;
}
 

 .newsImage {
   height:53px;
   width:29px;
}

.eleprofileImg{
	margin: 1px;
    padding-right: 8px;
}
.buttonStyle {
    -moz-border-radius: 5px 5px 5px 5px;
    background:none repeat scroll 0pt 0pt rgb(246, 29, 80);
    border: medium none;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
.pr-sub-fields-sec
{
	width:296px; 
	float:left; 
	padding-bottom:12px; 
	border-bottom:1px solid #D9D9D9; 
	margin-bottom:12px;
}
.or-down-arrow
{
	position:absolute; 
	left:25px;
	 bottom:-6px;
 }
 
 .pr-title
 {
	 background-color:#ed5b21; 
	 color:#fff; 
	 height:24px; 
	 position:relative; 
	 text-transform:uppercase; 
	 float:left;
	font:bold 14px/24px "Trebuchet MS", Arial, Helvetica, sans-serif; 
	margin-bottom:13px; 
	padding:0px 15px;
}
.requiredFont
	{
		color:red;
	
	}
.submenuImg
{
	float: left;
    height: 28px;
    padding-right: 8px;
}
#videoGallaryPopUpDiv {
    height: auto;
    min-height: 0;
    width: auto;
}
.container {
    -moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    background-color: #FFFFFF;
    margin: 9px auto 10px;
    max-width: 800px;
    padding: 10px;
}

.layoutHeadersClass {
    color: #89745D;
    font-size: 16px;
    font-weight: bold;
    padding: 5px;
    text-align: left;
    background: none repeat scroll 0 0 #06ABEA;
	color: #FFFFFF;
	margin-bottom: 10px;
	border-radius: 4px 4px 0px 0px;
}
.annDivId{
		-moz-border-radius : 5px 5px 5px 5px;
		background : none repeat scroll 0 0 #E0F2F8;
		margin : 15px 10px;
		overflow : hidden;
		padding : 14px 18px 14px 9px;
		text-align : left;
		padding-bottom : 9px;
		padding-top : 9px;
}
.annHeaderFont {
    color: #0053B2;
    font-size: 11px;
	
}
#constituencyPageCenterInfoDiv .rounded {
    width: 620px;
	
}

.rounded {
    background-color: #FFFFFF;
    margin: 10px 0;
    padding: 10px;
    position: relative;
}
.paginatorElmtClass a {
    border: 1px solid #ADADAD;
    margin: 0 3px;
    padding: 3px;
	font-size:11px;
   text-decoration: none;
}	
a:hover {
    text-decoration: underline;
}

.paginatorElmtClass{
	text-align: right;
	 margin-right: 10px;
}
a {
   
    font-weight: bold;
   
}
#assessStyles{
    background: none repeat scroll 0 0 #5CB275;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    font-weight: bold;
    margin-right:20px;
    padding: 3px 12px;
    text-decoration: none;
    width: 160px;
}
#fiedsetDiv{
	border-color: -moz-use-text-color #D1D1D1 #D1D1D1;
    border-left: 1.7px solid #D1D1D1;
    border-right: 1.7px solid #D1D1D1;
    border-style: none solid solid;
    border-width: 0 1.7px 1.7px;
}


.service-box a {
    background: none repeat scroll 0 0 #000000;
    color: activecaption;
    margin-left: 3px;
    opacity: 0.55;
    font-weight: normal;
}
#buildSources,#buildNewSources{
border:1px solid #d3d3d3;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
margin-bottom: 20px;
width: 618px;
}
#buildVideoNewSources{
border:1px solid #d3d3d3;
margin-left: auto;
margin-right: auto;
margin-top: 10px;
margin-bottom: 20px;
width: 500px;
}
.newssources{
 background-color:#97DFEB;
 padding:6px;
 margin-left:2px;
 border-radius: 5px 5px 5px 5px;
 display:table;

}
.newsParts{
  
  color:#FF4500;
}
 #contentAjaxCallImg{ 
	padding-left: 369px;
    padding-top: 110px;
	display:none;
  }
  .close{opacity:1.5;}
  .close:hover{opacity:1.5;}
#buildNewSources{display:table;}

</style>

</head>
<body>
<a id="inline" href="#fancydivbox" style="display:none"></a>
<div id="promodiv" style="display:none;">
	<div id="fancydivbox">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
</div>

<div id="fb-root"></div>
<script>
var isSubscribed = '${sessionScope.isSubscribed}';
var userName = '${sessionScope.UserName}';
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


$(document).ready(function(){
$('#development').click(function(){

$("#developmentspop").dialog({	
								stack: false,
								height: 230,
								width: 800,
								modal: true,
								position: [170,150],
								title:'Developments',
								overlay: { opacity: 0.5, background: 'black'}
	
	});
		var elemt = document.getElementById("developmentspop_window_inner");
		var str ='';
		str+='<table width="100%">';
		str+='<tr>';
		str+='<td><img src="images/icons/homePage_new/UnderConstruction.jpg" height="100px" width="170px"></td>';
		str+='<td>';
		str+='<table style="margin-left:17px">';
		str+='<tr>';
		str+='<td><h6 style="color:red;">SORRY PAGE UNDER CONSTRUCTION</h6><br></td></tr>';
		str+='<td><B>IT Grids (India) Pvt. Ltd.</B><br></td></tr>';
		str+='<tr><td>Enquires: customer.servies@partyanalyst.com<br></td></tr>';
		str+='<tr><td>Demo: sales@partyanalyst.com<br></td></tr>';
		str+='</table>';
		str+='</td>';
		str+='</tr>';
		str+='</table>';
		elemt.innerHTML = str;

});
});

</script>

<table width="999px" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<!--<div style="text-align:center;margin-bottom:10px;">
<script type="text/javascript"><!--
google_ad_client = "ca-pub-0938408694174139";
/* CandiatePageHeader */
google_ad_slot = "2678494123";
google_ad_width = 728;
google_ad_height = 90;
//-->
<!--</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</div>--></td></tr>
<tr><td>

<s:if test="candidateElectionDetails != null && candidateElectionDetails.size() > 0">
<div class="ppc-title" style="margin-top:-4px;">
<s:if test="candidateElectionDetails[0].stateId !=null">State: 
<a href="statePageAction.action?stateId=${candidateElectionDetails[0].stateId}"><strong>
<s:property value="candidateElectionDetails[0].stateName"/></strong></a>
 &gt;  </s:if>
 <s:if test="candidateElectionDetails[0].getDistricts!=null && candidateElectionDetails[0].getDistricts.size >0">

  District :
<s:iterator status="districs" value="candidateElectionDetails[0].getDistricts">


 <a href="districtPageAction.action?districtId=<s:property value="id"/>&districtName=<s:property value="name"/>"> <s:property value="name"/><s:if test="#districs.index < candidateElectionDetails[0].getDistricts.size - 1"> ,</a> </s:if></s:iterator> > </s:if>
<s:if test="candidateElectionDetails[0].districtName!=null"> 
District: 
<strong><a href="districtPageAction.action?districtId=${candidateElectionDetails[0].districtId}&districtName=${candidateElectionDetails[0].districtName}"><s:property value="candidateElectionDetails[0].districtName"/></strong> &gt; </s:if> </a> <span style="color: #171717;font-weight:normal;"><s:property value="candidateElectionDetails[0].electionType"/></span>:
<a href="constituencyPageAction.action?constituencyId=${candidateElectionDetails[0].constituencyId}" ><strong><s:property value="candidateElectionDetails[0].constituencyName"/></strong></a></div>
</s:if>

<div class="clear"></div>
<div class="main-title-sec">
 <div class="main-mbg">${candidateVO.candidateName} 'S  Profile

<span id="googlePlusSpanId" style="margin-top:10px;margin-right:10px;float:right">
<g:plusone size="medium"></g:plusone>

<script type="text/javascript">
  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
</script>
</span>

<span id="twitterSpanId" style="margin-top:10px;margin-right:0px;float:right;">
<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}">
Tweet</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
</script>
</span>

 <span id="facebookSpanId" style="margin-top:10px;margin-right:18px;float:right">
 <a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>
</span>
</div>
<div class="main-bbg"></div></div><br></td></tr></table>
<s:if test="customPages != null && customPages.size() > 0">
<s:iterator value="customPages" var="custom"> 
<s:if test="#custom.type == 'banner'">
	<div>
		<jsp:include page='${custom.name}' flush="true"/> 
	</div>
</s:if>
</s:iterator>
</s:if>

<table width="999px" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
  <td width="200" valign="top">

  <!--PROFILE LEFT CONENT SECTION START-->

    <div class="profile-left-sec" id="profile-left-sec">
	 </div>
	 <div class="pl-cont-sec" id="pl-cont-sec">
	<span id="candidateInfo"></span>
	<div class="pl-sub-fields"> <span style="margin-left:14px;"></span>
            <ul>
              <li><a onclick="getTotalNews('totalNews');" href="javascript:{}">News and events</a><span></span></li>
              <li><a onclick="videoGallaryPopUp();" href="javascript:{}">Video Gallery</a><span></span></li>
              <li><a onclick="photoGallaryPopUp();" href="javascript:{}">Photo Gallery</a><span></span></li>
              <li><a  id= "development"  href="javascript:{}" >Developments</a><span></span></li>
            </ul>
			<div id="developmentspop">
				<div  id="developmentspop_window_inner"></div>
			</div>

          </div>
	
	  <div class="clear"></div>

		<s:if test="customPages != null && customPages.size() > 0">
		    <s:iterator value="customPages" var="custom"> 
			<s:if test="#custom.type == 'left_navigation'">
				<div style="width:200px;">
					<jsp:include page='${custom.name}' flush="true"/> 
				</div>
			</s:if>
			</s:iterator>
		</s:if>
		
		<s:if test="false">
		<!--<div>
		<script type="text/javascript"><!--
			google_ad_client = "ca-pub-0938408694174139";
			/* CandidatePageLeftHandBox */
			google_ad_slot = "1524357461";
			google_ad_width = 200;
			google_ad_height = 200;
			//-->
			<!--</script>
			<script type="text/javascript"
			src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>
		</div> -->
		</s:if>

	   <!--EMAIL ALERT SECTION START-->
          
          <div class="ea-fc-sec">
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images/icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            
            <div class="ea-fc-cont-sec" style="font-size:13px;">
		
		<span id="subscribeSpan">
 
			<s:if test="isSubscribed == true ">
			Unsubscribe to stop<br/>
            updates of<br />
            <span class="li-red">${candidateVO.candidateName}</span><br/>
            <input  class="unsubscribebtn" type="button" onclick=
            "unsubscriptionDetails()" value="UNSUBSCRIBE"/>
            </s:if>
			
			<s:else >
			Subscribe and get <br/>
            updates of<br />
            <span class="li-red">${candidateVO.candidateName}</span><br/>
			<input  class="subscribebtn" type="button" onclick=
			"subscriptionDetails()" value="SUBSCRIBE"/>
 			</s:else>

            

		</span>
		
             <!-- <input name="" type="text" id="emailId" class="ea-text-fields" value="your email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';document.getElementById('alertMsg').innerHTML = '';"/>
               <div id="alertmsg"> </div>
              <div id="alertMsg" style="dispaly:none"></div>
			  <div class="pl-sub-but"><a onclick="validateEmailField()" href="javascript:{};"><strong>Set alert</strong></a></div>-->

			 
            </div>
          </div>
          
          <!--EMAIL ALERT SECTION END--> 
     </div>
	 <s:if test="isAdmin == 'true'"> 
	 <div><a href="profileManagePageAction.action?candidateId=${candidateId}">Manage Profile</a>
	 </div>
	 </s:if>
   
</td>
	
	<!--PROFILE LEFT CONENT SECTION END--> 

  <td width="444" valign="top">

    <!--PROFILE MIDDLE CONTENT SECTION START-->
      
      <div class="profile-mid-sec">
        <div class="pm-cont-sec"> 
		
		<!--  ABOUT POLITICIAN SECTION START   --> 
         
		<s:if test="descriptions != null"> 
		  <div id="pm-inner-cont-sec" class="pm-inner-cont-sec"> </div>
		</s:if>  
		  <!--  ABOUT POLITICIAN SECTION END  --> 
             
			 <div id="showProfile"></div>
		
			 <!--ELECTION PROFILE SECTION START-->
			<s:if test="candidateElectionDetails[0].electionId !=null">
              <div class="pm-inner-cont-sec" id="electionInfo"></div>
			</s:if>
			  <!--ELECTION PROFILE SECTION END--> 
			  <s:if test="electionGoverningBodyVO != null && electionGoverningBodyVO.size()>0">
		
		<div class="pm-inner-cont-sec">
		<h1 class="inc-title">Minister Profile</h1>

		
		<s:iterator status="stat" value="electionGoverningBodyVO" var="ministerDetails">
		
		<s:if test="toDate == null">
			<ul class="wl-sub-details"><li><s:property value="status"/>
		</s:if>
		<s:else> 
		<ul class="wl-sub-details"><li>	Worked
		</s:else> 
		As <b><s:property value="ministry"/> 
		Minister</b>
		
		<s:if test="ministerType != 'Cabinet Minister'">
		 (<s:property value="ministerType"/>) 
		</s:if>
		For 
		<s:if test="stateName != null"> 
			<b><s:property value="stateName"/></b> State
		</s:if> 
		<s:else> 
			<b>India</b> 
		</s:else> From <s:property value="fromDate"/>
		
		<s:if test="toDate != null"> 
		 - To <s:property value="toDate"/>
		</s:if></li></ul></li></ul>
	
		</s:iterator>
		
		</div>
		</s:if>

			  <!--PHOTO GALLERY SECTION START -->
          
		<div class="pm-inner-cont-sec" id="photoGallaryDiv"></div>
		<div class="clear"></div>
		<div id="analyzeCandidateDiv_Body"></div>
		
		<s:if test="customPages != null && customPages.size() > 0">
			<s:iterator value="customPages" var="custom"> 
				<s:if test="#custom.type == 'center_div'">
					<div style="width:435px;">
						<jsp:include page='${custom.name}' flush="true"/> 
					</div>
				</s:if>
			</s:iterator>
		</s:if>

          <div> 
		  <div class="fb-comments" data-href="http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}" data-num-posts="500" data-width="420"></div>
		  </div>

		  <div id="commentBoxDiv" style="margin-top:15px;"class="constituencyPageCenterInfoDiv.rounded">
		  </div>
		<div id="advVideoDiv" >
		<b>
		More Videos</b>
		<div style="margin-left: 12px;"><ul class="video-thumb-sec">
		<s:iterator value="advVideosList" var="advVideos">
		<li  style="height:175px;">
		<a   role="button"  data-toggle="modal"  onClick='openAdvVidepForView("<s:property value="advVideoId"/>")')><img class="thumbnail" style="width: 85px; height: 85px;" src='<s:property value="thumbnailUrl"/>'/>
		</a>
		
		<a style="cursor: pointer; font-size: 11px; font-family: arial;" onClick='openAdvVidepForView("<s:property value="advVideoId"/>")'><s:property value="description"/></a>
		</li>
		
		</s:iterator>
		</ul>
		</div>
		<div id="moreBtnDiv" ><a role="button" class="btn btn-success" data-toggle="modal" type="button"  style="float: right; margin-top: -12px;" onClick="openMoreViodeosWindow();">More</a></div>
		</div> 
		<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
			<h3 id="myModalLabel"></h3>
			</div>
			  <div class="modal-body">
			   <div id="advVideoDialogDiv"></div>
			  </div>
			  <div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			  </div>
		</div>
	</div>
   </div>
          
      <!--PROFILE MIDDLE CONTENT SECTION END--> 
</td>
 <td width="10">&nbsp;</td>
 
 <!-- PROFILE RIGHT CONTENT SECTION START -->
   
   <td width="326" valign="top">
      <div class="profile-right-sec">
        <div class="pr-cont-sec"> 
		  
		 <!-- NEWS AND EVENTS SECTION START -->

        <div class="pr-sub-fields-sec" style="margin-bottom:0px; border-bottom:0px;">
         <div class="news-events-fields" id="newsDisplayDiv">
			<img style="display: block;" src="images/icons/goldAjaxLoad.gif" id="news_ImgSpan">
          </div>		  
       </div>
          
          <!--NEWS AND EVENTS SECTION END--> 
           
		   <!--VIDEOS SECTION START-->
 
		<s:if test="fileVO != null && fileVO.size() > 0"> 
		<div class="pr-sub-fields-sec">
            <h1 class="pr-title">videos<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span> </h1>
		<div id="videogallery" class="fleft" style="margin-bottom:15px;">

	<s:iterator status="stat" var="vo" value="fileVO">
		<s:if test="#stat.index == 0">
		<DIV>
		<a rel="#voverlay" title='<s:property value="title"/>' onClick="getVideoDetails(<s:property value='contentId'/>)">
		<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width: 297px; height: 227px;"/></a>
		</DIV>
		</s:if>
	</s:iterator>

	<s:if test="fileVO.size() > 1"> 
		<ul class="video-thumb-sec">
			<s:iterator status="stat" value="fileVO">
				<s:if test="#stat.index >= 1 && #stat.index <= 3">
				<li><a rel="#voverlay" title='<s:property value="title"/>' onClick="getVideoDetails(<s:property value='contentId'/>)" style="width:72px;">
				<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width:95px;height:80px;"/></a></li>
				</s:if>
			</s:iterator>
		</ul>
	</s:if>
    </div>
  </s:if>

     <s:if test="fileVO != null && fileVO.size() > 4"> 
	 <div class="more"><a onClick="videoGallaryPopUp();" href="javascript:{};" style="margin-bottom: 20px;">More</a></div>
	 </s:if>
	
	<!--<div>
	<script type="text/javascript"><!--
		google_ad_client = "ca-pub-0938408694174139";
		/* CandidatePageRightBox */
		google_ad_slot = "5426332176";
		google_ad_width = 300;
		google_ad_height = 250;
		//-->
		<!--</script>
		<script type="text/javascript"
		src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
		</script>
	</div> -->

	<div id="showContentDiv">
	<div id="showContentDivInnerDiv"></div>
	<div id="showContentDivInnerDiv"></div>
	</div>
	
	<div id="videoGallaryPopUpDiv"></div>
	
	<div id="emailAlertDiv"></div>
	<div id="sendMessageDiv">
    <div id="constituencySelectDiv"/>
	
	</div>
	
	<div id="logInDiv"></div>
	
	<s:if test="customPages != null && customPages.size() > 0">
		<s:iterator value="customPages" var="custom"> 
			<s:if test="#custom.type == 'right_navigation'">
				<div style="width:300px;">
					<jsp:include page='${custom.name}' flush="true"/> 
				</div>
			</s:if>
		</s:iterator>
	</s:if>

    <!--VIDEOS SECTION END--> 
 </td>
 </tr>
</table>
<script>

  $(document).ready(function() {
    $("#tabs").tabs();
   $("a[rel=photo_gallery]").fancybox({
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});
  });
 </script> 
  <script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js">
	</script>
	<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js">
	</script>
 <script type="text/javascript">
 
 $(document).ready(function(){
 	 
	$('#googlePlusSpanId').css("display","none");
	$('#twitterSpanId').css("display","none");
	$('#facebookSpanId').css("display","none");
	 
	 var host = "<%=IConstants.DEPLOYED_HOST%>";
	  if(host != "tdpserver"){
		$('#googlePlusSpanId').css("display","block");
		$('#twitterSpanId').css("display","block");
		$('#facebookSpanId').css("display","block");
		$("#inline").fancybox();
		$("#inline").trigger("click");
	  }
	});

   var descriptions = '${descriptions}';
   var timeST = new Date().getTime();
   var candidateId = '${candidateId}';
   var cnstuencyId='${constituencyId}';;
   var stateId='${stateId}';
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
   var queryTypeChecked='Public';
   var userId = "${sessionScope.USER.registrationID}";
   var userType = "${sessionScope.USER.userStatus}";
   var loginStat = "${sessionScope.loginStatus}";
   var showContentResultList = null;
   var newsData;
   var newsPopUpData;
   var selectedContentFile;
   var videosNewData;
var news_Obj = {
	
	problemStatus:[],
	startIndex:0,
	problemsCount:20,
	
	initialize:function(){
		
		this.getAllNewsForUser("totalNews");

	},
	getAllNewsForUser:function(viewType)
    {  
	  
        var queryType='Public';
        if(document.getElementById("candidateVisibility")!=null)
        { 
	        var candidateVisibilityEle = document.getElementById("candidateVisibility");
	        if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
		    {
		       queryType='Public';
		       queryTypeChecked='Public';
		    }
		    if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
		    {
		       queryType='Private';
		       queryTypeChecked='Private';
		    }
		   if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
		   {
		      queryType='All';
		      queryTypeChecked='All';
		   }
	    }
	
        var jsObj =
		    {   
        		task:"getAllNewsToDisplay",
		        time : new Date().getTime(),
			    viewtype:viewType,
			    candidateId:candidateId,
			    queryType:queryType
			    
		     };
        
	    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	    var url = "candidatePhotoGallaryAction.action?"+rparam;

	    custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.problemsCount,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
	    	showTotalNews(results);
			}
		});
		
		custom_paginator.initialize();
    },
    
    getScopeWiseNews:function(scopeId)
    {
       
        deleteAllElements();
        
        var queryType='Public';
        if(document.getElementById("candidateVisibility")!=null)
        { 
   	        var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	        if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
   		    {
   		        queryType='Public';
   		        queryTypeChecked='Public';
   		    }
   	   }
       if(document.getElementById("candidateVisibility")!=null)
       {
   	         var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	        if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
   		    {
   		        queryType='Private';
   		        queryTypeChecked='Private';
   		    }
      }
      if(document.getElementById("candidateVisibility")!=null)
      {
   	       var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	       if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
   		   {
   		       queryType='All';
   		       queryTypeChecked='All';
   		  }
   	  }
      var jsObj =
   		{   
   		    time : new Date().getTime(),
   			candidateId:candidateId,
   			scopeType:scopeId,
   			queryType:queryType,
   			task:"getNewsByScope"
   		};

    	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    	var url = "candidateNewsGallaryAction.action?"+rparam;
    	
    	custom_paginator.paginator({
		  startIndex:this.startIndex,
		  resultsCount:this.problemsCount,
		  jsObj:jsObj,
		  ajaxCallURL:url,
		  paginatorElmt:"custom_paginator_class",
		  callBackFunction:function(){
    	  showTotalNews(results);
		 }
	   });	
   	custom_paginator.initialize();					
   	 
   },
   
   
   
   getNewsBySource:function(source)
   {
        scopeId='';
        deleteAllElements();
       
        var queryType='Public';
        if(document.getElementById("candidateVisibility")!=null)
        { 
 	         var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	         if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
 		     {
 		       queryType='Public';
 		       queryTypeChecked='Public';
 		     }
 	    }
       if(document.getElementById("candidateVisibility")!=null)
       {
 	       var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	      if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
 		  {
 		    queryType='Private';
 		    queryTypeChecked='Private';
 		  }
 	   }
      if(document.getElementById("candidateVisibility")!=null)
      {
 	      var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	      if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
 		  {
 		   queryType='All';
 		   queryTypeChecked='All';
 		  }
 	 }
  	 
      var jsObj =
 		{   
 		    time : new Date().getTime(),
 			candidateId:candidateId,
 			scopeType:scopeId,
 			queryType:queryType,
 			source : source,
 			task:"getNewsBySourceScope"
 		};

 	  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 	  var url = "candidateNewsGallaryAction.action?"+rparam;
 	  
 	  custom_paginator.paginator({
		  startIndex:this.startIndex,
		  resultsCount:this.problemsCount,
		  jsObj:jsObj,
		  ajaxCallURL:url,
		  paginatorElmt:"custom_paginator_class",
		  callBackFunction:function(){
   	          showTotalNews(results);
		 }
	   });	
	   
  	 custom_paginator.initialize();									
 	 
   },
   getNewsByLanguage: function (language)
   {
        scopeId='';
        deleteAllElements();
        
        var queryType='Public';
        if(document.getElementById("candidateVisibility")!=null)
        { 
 	          var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	          if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
 		      {
 		             queryType='Public';
 		             queryTypeChecked='Public';
 		      }
 	     }
        if(document.getElementById("candidateVisibility")!=null)
        {
 	         var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	         if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
 		     {
 		         queryType='Private';
 		         queryTypeChecked='Private';
 		     }
 	    }
       if(document.getElementById("candidateVisibility")!=null)
       {
 	        var candidateVisibilityEle = document.getElementById("candidateVisibility");
 	        if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
 		    {
 		        queryType='All';
 		        queryTypeChecked='All';
 		    }
 	   }
       var jsObj =
 		   {   
 		    time : new Date().getTime(),
 			candidateId:candidateId,
 			scopeType:scopeId,
 			language : language,
 			queryType:queryType,
 			task:"getNewsByLanguageScope"
 		  };

 	     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
 	     var url = "candidateNewsGallaryAction.action?"+rparam;	
 	
 	    custom_paginator.paginator({
		   startIndex:this.startIndex,
		   resultsCount:this.problemsCount,
		   jsObj:jsObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
  	          showTotalNews(results);
		   }
	     });	
	   
 	 custom_paginator.initialize();						
 	
   },
  getNewsByCategory:function (category)
  {
         scopeId='';
         deleteAllElements();
       
         var queryType='Public';
        if(document.getElementById("candidateVisibility")!=null)
        { 
   	        var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	        if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
   		    {
   		        queryType='Public';
   		        queryTypeChecked='Public';
   		    }
        }
       if(document.getElementById("candidateVisibility")!=null)
       {
   	      var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	      if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
   		  {
   		  queryType='Private';
   		  queryTypeChecked='Private';
   		  }
       }
      if(document.getElementById("candidateVisibility")!=null)
      {
   	     var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	     if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
   		  {
   		   queryType='All';
   		   queryTypeChecked='All';
   		  }
   	  }
       var jsObj =
   		{   
   		    time : new Date().getTime(),
   			candidateId:candidateId,
   			scopeType:scopeId,
   			category : category,
   			queryType:queryType,
   			task:"getNewsByCategoryScope"
   		};

   	    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    	var url = "candidateNewsGallaryAction.action?"+rparam;	
        custom_paginator.paginator({
		   startIndex:this.startIndex,
		   resultsCount:this.problemsCount,
		   jsObj:jsObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
	          showTotalNews(results);
		   }
	     });	
	   
	   custom_paginator.initialize();						
   	
   },  
getNewsByNewsImportance:function(newsImportance)
 {
         scopeId='';
         deleteAllElements();
         var queryType='Public';
       if(document.getElementById("candidateVisibility")!=null)
       { 
   	        var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	       if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
   		   {
   		      queryType='Public';
   		      queryTypeChecked='Public';
   		   }
       }
      if(document.getElementById("candidateVisibility")!=null)
      {
   	      var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	      if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
   		  {
   		    queryType='Private';
   		   queryTypeChecked='Private';
   		  }
      }
     if(document.getElementById("candidateVisibility")!=null)
     {
   	      var candidateVisibilityEle = document.getElementById("candidateVisibility");
   	      if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
   		  {
   		   queryType='All';
   		   queryTypeChecked='All';
   		  }
   	}
     var jsObj =
   		{   
   		    time : new Date().getTime(),
   			candidateId:candidateId,
   			scopeType:scopeId,
   			newsImportance : newsImportance,
   			queryType:queryType,
   			task:"getNewsByNewsImportance"
   		};

   	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   	var url = "candidateNewsGallaryAction.action?"+rparam;	
     custom_paginator.paginator({
		   startIndex:this.startIndex,
		   resultsCount:this.problemsCount,
		   jsObj:jsObj,
		   ajaxCallURL:url,
		   paginatorElmt:"custom_paginator_class",
		   callBackFunction:function(){
	          showTotalNews(results);
		   }
	     });	
	   
	   custom_paginator.initialize();						
   	 
   }, 

   
};
var message_Obj = {
		
	
		problemStatus:[],
		startIndex:0,
		problemsCount:5,
		
		initialize:function(){
			
			this. getUserDetails();

		},
		getUserDetails:function ()
	    {
	   	 var jsObj =
	   		{ 
	   		    candidateId : candidateId,
	   			task:"getUserMessages"
	   		};
	   	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	   	 var url = "candidateMessagesAction.action?"+rparam;
	   		message_paginator.paginator({
	   		   startIndex:this.startIndex,
			   resultsCount:this.problemsCount,
			   jsObj:jsObj,
			   ajaxCallURL:url,
			   paginatorElmt:"message_paginator_class",
			   callBackFunction:function(){
	   		       displayUserDetails(results);
			   }
		     });	
		   
	   	message_paginator.initialize();							
	   	 
	    },
	   
};

function openAdvVidepForView(id)
{
	var jsObj=
	{ 
		id:id,
		task:"advVideoDisplay"
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "advVideoAction.action?"+rparam;
	callAjax(jsObj,url);
}
	
function openPouUpForVideo(result)
{
	var str = '';
	//var vstr = '';
	//vstr += '<b style="color:seagreen">'+result.title+'</b>';
	//$('#myModalLabel').html(vstr);	
	str += '<div>';
	str += '<div  style="padding-left: 38px;">'+result.code+'</div>'; 
	str += '<div style="margin-top: 23px;"><b style="color:seagreen">Description : </b> '+result.description+'</div>';
	str += '<div><b style="color:seagreen">Tags : </b> '+result.tags+'</div>';
	//str += '<div><b>Duration : </b></div><div> '+result.duration+' Seconds</div>';
	str += '</div>';
	
	//$('#modelHeader').html(vstr);	
	$('#advVideoDialogDiv').html(str);
	
	$('#advVideoDialogDiv').dialog({
		title : ''+result.title+'',
		width: 500,
		height : 500
	}); 
	
}
function openMoreViodeosWindow()
{
	var urlStr="moreVideosAction.action";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
function buildAnalyzeConstituencyWindow()
 {
	var constituencyId = '${candidateElectionDetails[0].constituencyId}';
	var constituencyName = '${candidateElectionDetails[0].constituencyName}';
	var candidateId = '${candidateElectionDetails[0].candidateId}';
	var bodyElmt = document.getElementById('analyzeCandidateDiv_Body');
	
	if(candidateId == null || candidateId == '')
		return;


	var str='';
	str+='<fieldset id="analyzeConstituencyFieldSet">';
	str+='<legend style="font-weight:bold;background:#06ABEA"> Assess Your Politician</legend>';
	str+='<div id="analyzeConstituencyContentDiv" class="problemPostingContentDivClass">';	
	str+='<div>Assess your Politician and post your reasons for winning/loosing .</div>';
	if(loginStat == 'out')
	{
		if(userType == "PARTY_ANALYST_USER" || userType == "FREE_USER")
		{
			str	+= '<div id="analyzeConstituencyButtonDiv" style="text-align:right;padding:5px;">';
			str += '<a href="javascript:{}" title="Click to Assess Results" style="margin-right:10px;background: none repeat scroll 0% 0% rgb(92, 178, 117); text-decoration: none; color: rgb(255, 255, 255); font-weight: bold; margin: 4px 19px 5px 4px; border-radius: 4px 4px 4px 4px; padding: 3px 12px; width: 160px;" onclick="openAnalyzeConstituencyWindow(\'analyze\')">Assess</a>';
			str += '<a href="javascript:{}" title="Click to View Previous Posts" style="margin-right:10px;background: none repeat scroll 0% 0% rgb(92, 178, 117); text-decoration: none; color: rgb(255, 255, 255); font-weight: bold; margin: 4px 19px 5px 4px; border-radius: 4px 4px 4px 4px; padding: 3px 12px; width: 160px;" onclick="openAnalyzeConstituencyWindow(\'viewResults\')">Previous Posts</a>';
			str += '</div>';		
		}
	}
	else {
        str += '<div id="analyzeConstituencyButtonDiv" style="text-align:right;padding:5px;">';
		str += '<a id = "assessStyles" href="loginInputAction.action?redirectLoc=CANDIDATE_PROFILE&candidateId='+candidateId+'" title="Click to Assess Results" style="margin-right:10px;">Assess</a>';
		str += '<a id = "assessStyles" href="loginInputAction.action?redirectLoc=CANDIDATE_PROFILE&candidateId='+candidateId+'" title="Click to View Previous Posts" style="margin-right:10px;">Previous Posts</a>';
		str += '</div>';		
	}
		
	
	str+='</div>';
	str+='</fieldset>';
	
	if(bodyElmt)
		bodyElmt.innerHTML=str;
}
function openAnalyzeConstituencyWindow(type)
{		
	var constituencyId = '${candidateElectionDetails[0].constituencyId}';
	var constituencyName = '${candidateElectionDetails[0].constituencyName}';
	var constiTypeName = '${candidateElectionDetails[0].electionType}';
	var candidateId = '${candidateElectionDetails[0].candidateId}';
	var parliamentConstiId = '${candidateElectionDetails[0].parliamentConstituencyId}';
	var parliamentConstiName = '${candidateElectionDetails[0].parliamentConstituencyName}';
	if(parliamentConstiId=='')
		parliamentConstiId = constituencyId;
	if(parliamentConstiName =='')
		parliamentConstiName = constituencyName;
	var taskType = type;

	if(userId != "" && userType == "FREE_USER")
	{
	   if(constiTypeName == 'Assembly')
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
	   else
	    var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&parlchecked=true&taskType="+taskType+"&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");
		browser1.focus();
	}
	if(userId != "" && taskType == 'viewResults')
	{
	   if(constiTypeName == 'Assembly')
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
	   else
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&parlchecked=true&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");
		browser1.focus();
	}
	else if(userId == "" && userType == "FREE_USER")
	{
		alert("Please Login To Post Comment");
	}
	else if(userType != "FREE_USER")
	{
		alert("Comment For Free User Only");
	}
}


 function subscriptionDetails()
 {
	if(userName==''){
       showNotLogIn();
    return false;}
	
	else{	
	var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			id: '${candidateId}',
			task: "subscriptionDetails",
			page:"candidatePage"
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
   }
 }
 
 function unsubscriptionDetails()
 {
		
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			id: '${candidateId}',
			task: "unsubscriptionDetails",
			page:"candidatePage"
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
 }
 
function sendMessage()
{
	var isprivate='';
  var name = document.getElementById("name").value;
  var stateSelect=document.getElementById("stateSelect").value;
  var constituencySelect=document.getElementById("constituencySelect").value;
  var message=document.getElementById("message").value;
    document.getElementById('galErrorMsgDivId').innerHTML = '';
    document.getElementById('fileUploadErrorMsgDivId').innerHTML = '';
  var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;

	if(document.getElementById("check1").checked==true)
         isprivate=false;
	else
		isprivate=true;

	var str = '<font color="red">';

	if(name.length == 0)
	{
		str += 'Name is Required<br>';
		eFlag = true;
	}
	
	if(stateSelect.length == 0)
	{
		str += 'State is Required<br>';
		eFlag = true;
	}
	if(constituencySelect == 0)
	{
		str += 'Constituency is Required<br>';
		eFlag = true;
	}
	if(message.length == 0)
	{
		str += 'Message is Required<br>';
		eFlag = true;
	}
	
    str += '</font>';
	errorDivEle.innerHTML = str;
	
	if(eFlag)
		return;
	var jsObj =
		{ 
		    candidateId : candidateId,
            name : name,
			stateSelect : stateSelect,
			constituencySelect : constituencySelect,
			message : message,
            isprivate : isprivate, 
		   	task : "saveMessage"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	

}
function showAssemblyData()
 {
    var stateId='${stateId}';
   var str='';
   
   str+='  <div class="pr-sub-fields-sec">';
   str+='  <h1 class="pr-title" style="margin-top:15px;">send a message to ${candidateVO.candidateName} <span class="or-down-arrow">';
   str+=' 	  <img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>';
   str += '<div id="galErrorMsgDivId" style = "width:190px;"></div>';
   str += '<div id="fileUploadErrorMsgDivId" style = "width:190px;"></div>';	  
   str+=' <table>';
   str += '<tr>';
   str+='<td style = "padding-top:9px;">Name <font class="requiredFont"> * </font></td>';
   str+='<td><input type = "text" id="name" size = "20" class="sm-text-fields"></td>';
   str+='</tr>';
   str+='   <tr>';
   str+='     <td>State</td>';
   str+='     <td style="padding-top: 5px;">';
   
 
   str+='       <select id="stateSelect"  onchange="clearAll(\'constituencySelect\');getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,\'constituency\')" style = "width:192px;background-color:#EBE8E8; border:1px solid #ffffff;"/>';
   str+='     </td>';
   str+='   </tr>';
   str+='   <tr>';
   str+='     <td>Constituency <font class="requiredFont"> * </font></td>';
   str+='     <td style="padding-top: 5px;">';
   str+='       <select id="constituencySelect" style = "width:192px; background-color:#EBE8E8; border:1px solid #ffffff; "/>';
   str+='     </td>';
   str+='   </tr>';
   str+=' <tr>';
   str+='<td colspan="2"><input type="checkbox" id="check1"  checked>&nbsp Display this message publicly</input></td>'
   str+=' </tr>';
   str+=' <tr>  <td >Message <font class="requiredFont"> * </font></td>';
   str+=' <td style="padding-top: 5px;"> <textarea id= "message" name ="message" rows="4" cols="8" style="background-color: #EBE8E8;';
   str+=' border: 1px solid #ffffff;';
   str+=' color: #000000; width: 181px;height: 85px;';
   str+=' font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;';
   str+=' padding: 0px 0 0px 0px;"></textarea></td>';
   str+= '</tr>';
   str+= '<tr>';
   str+= '<td></td><td style="padding-top: 5px;"> <a href="javascript:{}" onClick="sendMessage()"> <img src="images/icons/send_btn.jpg"/></a> </td>';
   str+= '</tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   $('#name').val(userName);
   getStates();
   if(stateId!=null && stateId!="null" && stateId!="")
       getAllConstituenciesInStateByType(2,stateId,"constituency");
	else
	 getAllConstituenciesInStateByType(2,1,"constituency");
 }
 
 function getAllConstituenciesInStateByType(electionType, stateId, element)
 {
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,	
			electionTypeId: electionType,
			stateId: stateId,
			task: "getConstituencies",
			elmtId: element 	
	}

   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllConstituenciesInState.action?"+rparam;						
   callAjax(jsObj,url);
 }

function getStates()
 {
  var timeST = new Date().getTime();
  var jsObj =
		{ 
            time : timeST,
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 
 function buildResults(results,divId)
 {
  var elmt = document.getElementById(divId);
  var stateId='${stateId}';
  
     
	for(var i in results)
	  {
		var option = document.createElement('option');
		
		  option.value=results[i].ids;
		  option.text=results[i].names;
		  
		try
		 {
			elmt.add(option,null); // standards compliant
		 }
		catch(ex)
		 {
			elmt.add(option); // IE only
		 }
		 
		
	 }
	if(stateId!=null && stateId!="null" && stateId!=""){
	$('#'+divId).val(stateId);
	}
	 
	
 }
 
 function showResults(results,divId)
 {
  var elmt = document.getElementById(divId);
  var cnstuencyId='${constituencyId}';
   if(results.length<=0 && divId=="constituencySelect")
     {
   	   var option1 = document.createElement('option');
		option1.value= 0;
		option1.text= "Select Constituency";
		 try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
	  }
	for(var i in results)
	  {
		var option = document.createElement('option');
		  option.value=results[i].id;
		  option.text=results[i].name;
		try
		 {
			elmt.add(option,null); // standards compliant
		 }
		catch(ex)
		 {
			elmt.add(option); // IE only
		 }
	 }
	 if(cnstuencyId!=null && cnstuencyId!="null" && cnstuencyId!=""){
	 $('#'+divId).val(cnstuencyId);
	 }
 }
 
 function clearAll(elmtId)
   {
	var elmt = document.getElementById(elmtId);
}
function showStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(myResult.resultCode == 0)
	{
		cleardescriptionFields();
		str += '<font color="green"><b>Message Sent Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}
function cleardescriptionFields()
{
	document.getElementById('name').value = '';
    document.getElementById('message').value = '';
    document.getElementById('constituencySelect').value = 0;
}
function onYouTubePlayerReady(playerId) 
{ 
	ytplayer = document.getElementById("video_overlay"); 
	ytplayer.setVolume(100);
}


function getVideoDetails(contentId)
{
	$.fx.speeds._default = 1000;
	  $("#showContentDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								minHeight: 650,
								overlay: { opacity: 0.5, background: 'black'},
							 close: function(event, ui) {
		   					document.getElementById("showContentDivInnerDiv").innerHTML ='';
							}
								});
		$("#showContentDiv").dialog();
		getContentDetails(contentId);
}


function callAjax(jsObj,url)
{
	var callback = {			
	 success : function( o ) {
		try
		{ 

		 myResults = YAHOO.lang.JSON.parse(o.responseText);
         if(jsObj.viewtype == "getFirstFourNewsRecordsToDisplay")
			{
 				showFirstFourNewsRecords(myResults);
			}
		 else if(jsObj.task =="advVideoDisplay")
			{
				openPouUpForVideo(myResults);
			}
		else if(jsObj.task == "getStates")
			  {    
				buildResults(myResults,"stateSelect");
			  }
	   else if(jsObj.task == "getConstituencies")
			 {  
				clearOptionsListForSelectElmtId("constituencySelect");
				showResults(myResults,"constituencySelect");
				
			 }
	    else if(jsObj.task == "saveMessage") 
			 {   
				showStatus(myResults);
			 }		 
		 else if(jsObj.task == "getNewsToDisplay")
			{
               showTotalNews(myResults);
			}
		
		 else if(jsObj.task == "getFirstThreePhotoGallaryDetail")
			{
               buildFirstThreePhotoRecords(myResults);
			}
		 else if(jsObj.task == "getCandidatesPhotoGallaryDetailWithOutGallerySizeZero")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="old")
			{
               showPhotosInAGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="new")
			{
               showPhotosInInitialGallary(myResults);
			}
		else if(jsObj.task == "getCandidateLatestVideos")
			{
			   buildVideoGallaryDiv(myResults);
			}
		else if(jsObj.task == "videoGalleriesForACandidate")
			{
			     buildVideoGallaries(myResults);
		    }
		else if(jsObj.task == "getVideosInGallary")
            {
			   buildAllVideosInGallary(myResults);
			}
		else if(jsObj.task == "getNewsCountByScope")
            {
			   buildNewsCount(myResults);
			}
	    else if(jsObj.task == "setEmailAlertForUser")
            {
			   showStatusForEmailSubscription(myResults);
			}
		else if(jsObj.task == "getOtherNews")
            {
			   showTotalNews(myResults);
            }
		else if(jsObj.task == "getSelectedContent")
			{
				showContentResultList = myResults;
				buildContentDetails();
			}
			else if(jsObj.task == "subscriptionDetails")
			{
				unSubscribeBtnBuild();
			}
			else if(jsObj.task == "unsubscriptionDetails")
			{
				subscribeBtnBuild();
			}
		
			
	  }
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
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

function unSubscribeBtnBuild()
{
$('#subscribeSpan').html('');


//alert('in unsubscribe');
 var str='';
 str+='Unsubscribe to stop<br/>updates of<br />';
 str+='<span class="li-red">${candidateVO.candidateName}</span><br/>';
 str+='<input  class="unsubscribebtn" type="button" onclick="unsubscriptionDetails()" value="UNSUBSCRIBE"/>';

$('#subscribeSpan').html(str);
subscribeAlert();
}

function subscribeBtnBuild()
{
$('#subscribeSpan').html('');
 var str='';
 str+='Subscribe to get<br/>updates of<br />';
 str+='<span class="li-red">${candidateVO.candidateName}</span><br/>';
 str+='<input  class="subscribebtn" type="button" onclick="subscriptionDetails()" value="SUBSCRIBE"/>';

$('#subscribeSpan').html(str);
unSubscribeAlert();
}
function displayUserDetails(results)
{ 
	
	if(results == null || results.length == 0)
		return;

    var resultDiv = document.getElementById('commentBoxDiv');
    var str = '';

    str += '<Div class="layoutHeadersClass" style=" margin-bottom:0px;"> Messages From Followers </DIv>';
      str += '<fieldset id ="fiedsetDiv">';
    for(var i=0;i<results.length;i++)
    {
	   
       str += '<div class="annDivId">'
	   str += '<Table>';
	   str += '<tr class="annHeaderFont"><th>'+results[i].userName+'&nbsp;&nbsp;</th><th>'+results[i].time+'&nbsp;&nbsp;</th><th>Location:<td class=""><font color="black"><a href="constituencyPageAction.action?constituencyId='+results[i].consituencyId+' ">'+results[i].constituency+'</a></font></td></th><tr>';
	   str += '</Table>';
	   str += '<Table style="width:auto;">';
	   str += '<tr class="annHeaderFont"><th>Message</th></tr>';
	   str += '<tr><td>'+results[i].message+'</td></tr>';
	   str += '</Table>';
	   str += '</div>'
	
    }
    str +='<div id="message_paginator_class" class="paginatorElmtClass"></div>';
   str += '</fieldset>';
   
   
   resultDiv.innerHTML = str;

}
function showStatusForEmailSubscription(results){


	var str='';
	if(results.resultCode == 0){
		if(results.exceptionMsg != null && results.exceptionMsg != '')
		{
			str+='<span style="font-size: 13px; font-family: trebuchet MS;color:red">'+results.exceptionMsg+'</span>';
			
		}
		else
		{
	    str+='<span style="font-size: 13px; font-family: trebuchet MS;color:green">You are Subscribed For Email alerts Successfully</span>';
		}
	}
	else
	{
		 str+='<span style="font-size: 13px; font-family: trebuchet MS;color:red">Unable To Process,Please Try Later</span>';
	}
    document.getElementById("alertmsg").innerHTML = str;
    document.getElementById("emailId").value='';
}
function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
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

	var jsObj = {
		          emailId : emailIdVal,
				  candidateId :candidateId,
                   task:"setEmailAlertForUser"
	             };
    
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function photoGallaryPopUp(){
	var str='';
	str +='<div style="margin:5px;font-size:13px;margin-left: 69px;"> Loading Photo Galleries .....<img style="float:right;margin-right: 295px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#buildPhotoGallaryDiv").html(str);
	if(document.getElementById('buildPhotoGallaryDiv') == null)
		return;
     $("#buildPhotoGallaryDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy">Photo Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#buildPhotoGallaryDiv").dialog();
	showPhotoGallary();
  }
function showPhotoGallary(){

var str='';
	str +='<div style="margin:5px;font-size:13px;margin-left: 69px;"> Loading Photo Galleries .....<img style="float:right;margin-right: 295px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#buildPhotoGallaryDiv").html(str);
    var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			startRecord:0,
			maxRecord:100,
			task:"getCandidatesPhotoGallaryDetailWithOutGallerySizeZero"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function buildCandidatePhotoGallary(results)
{
	var str ='';

		str+='<div id="content" style="width:650px;">';		
		str += '<fieldset class="imgFieldset">';
		str +='<table  width="100%" style="margin-top:10px;">';
		
	if(results.length<=0)
	{
		str+='<b>&nbsp;No Photo Galleries Found </b>';
	}
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:220px;">';
			
			str += '<td width="33%" class="imageStyle">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			if(results[i].path!=null)
			{
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')" alt="'+results[i].title+'" title="'+results[i].gallaryDescription+'"/></a></td></tr>';
            }
			else
			{
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="images/icons/DefaultPhotoGalleryImage.jpg" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')" alt="'+results[i].title+'" title="'+results[i].gallaryDescription+'"/></a></td></tr>';
			}
			str+= '<tr><td><div><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
			str += '<tr><td><div><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			
			str += '</table>';
			str += '</td>';
			
			if(j % no_of_imagesPerRow == 0)
				str+= '</tr>';
		
		}
		str += ' </table>';
		str += ' </fieldset>';
		str+='</div>';
		document.getElementById("buildPhotoGallaryDiv").innerHTML = str;
}
function getCompleteGallaries(gallaryId){
    var jsObj =
		{ 
            time : timeST,
			value:"old",
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showPhotosInAGallary(results){
   var str ='';
   str+='<div id="content" style="width:650px;">';
   str += '<fieldset class="imgFieldset">';
   str+='<table width="100%" style="margin-top:10px;">'
   str+='<tr><td>';
   str+='<input type="button" value="Back To Gallery"  class="imageButton" onclick="showPhotoGallary();" />';
   str+= '</td></tr>';
   count = 0;
   for(var i in results)
   {
    for(var k in results[i].filePath)
	{
     no_of_imagesPerRow = 3; 
     j = count;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].filePath[k]+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].filePath[k]+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';     
    }
	count++;
   }
  }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("buildPhotoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
}

function showPhotosInInitialGallary(results)
{
	if(results == null || results.length == 0)
		 return;

   var str ='';
     str+='<div id="content" style="display:none;">';
	count = 0;
   for(var i =0;i<results.length; i++)
   {
    for(var k in results[i].filePath)
	{
	 
     no_of_imagesPerRow = 3; 
     j = count;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="display:none">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" id="photoClickId'+i+''+k+'" href="'+results[i].filePath[k]+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].filePath[k]+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }
	 count++;
   }
   }
   str+= ' </table>';
   str+='</div>';
   document.getElementById("buildPhotoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
    $('#photoClickId00').trigger('click');
   //fireEvent(document.getElementById('photoClickId'),'click');
    //document.getElementById("buildPhotoGallaryDiv").innerHTML ='';
}
function fireEvent(obj,evt){
	
	var fireOnThis = obj;
	if( document.createEvent ) {
	  var evObj = document.createEvent('MouseEvents');
	  evObj.initEvent( evt, true, false );
	  fireOnThis.dispatchEvent(evObj);
	} else if( document.createEventObject ) {
	  fireOnThis.fireEvent('on'+evt);
	}
}

function getFirstFourNewsRecords(){
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			
			task:"getFirstFourNewsRecordsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showFirstFourNewsRecords(results)
 { 
     newsData = results;
  var str ='';
  
  if(results.length>0)
  {
   
   str +='<h1 class="pr-title">news &amp; Events<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>';

   str+='<ul>';
   for(var i =0 ;i<results.length && i<4;i++)
   {
      var source ='';
      for(var j in results[i].fileVOList)
	   source += ''+results[i].fileVOList[j].source+' - ';
	   
	   source  = source.slice(0, -2); 
	   
     initialFileIdArray[i]=results[i].fileId;
     str+='<a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle">';
	 
	 if(results[i].fileTitle1.length > 30)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,40)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';;

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 60)
		str += results[i].fileDescription1.substring(0,127)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
   str+='<div class="more"><a href="javascript:{}" onclick=" news_Obj.initialize();">More</a></div>';
   
   str+='<table><tr><td><div id="showNewsDiv" /></td></tr></table>';
   str+='<table><tr><td><div id="showAllNewsDiv" /></td></tr></table>';
   
  
    for(var i =4 ;i<results.length;i++)
	{
	  initialFileIdArray[i]=results[i].fileId;
	}
   }
	else
	{
		str+='<div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>';
            str+='<div class="clear"></div>';
            str+='<p></p>';
            str+='<span class="gray">Are you a</span>'; 
			str+='<strong>Politician';
			str+='<span class="orange">/</span>Political Party';
			str+='<span class="orange">/</span>Media...</strong> Want to know how you can be benefited with ';
			str+='<span class="orange">PartyAnalyst</span> ?';
            str+='<div class="clear"></div>';
            str+='<div class="clickhere-button">';
			str+='<a href="viewFeaturesAction.action">Click Here to Learn More...</a></div>';
          str+='</div>';
	}
	$("newsDisplayDiv").html('');
    document.getElementById("newsDisplayDiv").innerHTML=str;
  
 }
 function showNewsWithDiffSources(fileId,filSize,arrayType)
 {
     if(arrayType=="initialArray")
        initialCurrentSize=filSize;
     if(arrayType=='array')
        currentSize= filSize;
	arraySize = fileIdArray.length;
	initialArraySize = initialFileIdArray.length;
     var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	    for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
	  $.fx.speeds._default = 1000;
	  $("#showNewsDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								title:'<center><font color="Navy">'+results.fileTitle1+'</font><center>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();

	var str='<DIV id="showremainingNewsSources"><center>';

	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results.fileVOList[0].source+'</font> &nbsp;&nbsp;&nbsp;<B> Date </B>:<font color="#FF4500"> '+results.fileDate+'</font>';
	 str+='       </td>';
	 str+='       <td>';
	 str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     </table>';
	
	 str+='     <table>';
	 str+='			<tr>';

	if(arrayType=='array')
	{
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }

	 
		str+='             <td><div  id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+results.fileVOList[0].fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 

	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
  }

	else if(arrayType=="initialArray")
	{
	 
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 
	 
	 str+='             <td><div id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+results.fileVOList[0].fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 
	 
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
	}

	str+='		      </tr>';
	str+='         </table>';

	str += '<table><tr>';
	str+='       <td>';
	str+='        '+results.fileDescription1+'';
	str+='       </td>';
	str+='     </tr>';
	str+='</table>';
	
	str += '</center></DIV>';
	
    str +='<div id="buildSourceParts">';
	str += '<center><table><tr>';

	for(var j=1;j<results.fileVOList[0].fileVOList.length;j++)
	{
	  str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[0].fileSourceLanguageId+','+results.fileVOList[0].fileVOList[j].orderNo+','+fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+results.fileVOList[0].fileVOList[j].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+results.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+results.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	}
	str += '  </tr></table>';
	str +='</center></div>';
	
	if(results.multipleSource >1 )
	{
	  str +='<div id="buildSources">';
	  str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	  str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	  for(var k=1;k<results.fileVOList.length;k++)
	  {
	     str += '<td><a class="newssources" href="javascript:{}" onclick="showAnotherSource('+results.fileVOList[k].fileSourceLanguageId+','+fileId+',\''+arrayType+'\')">'+results.fileVOList[k].source+'</a></td>';
	  }
	  str += '  </tr></table>';
	  str +='</center></div>';
	}
	str += '<div id="facebookCommentsInNewsDiv" class="popupcontainer" style="overflow:auto;width:600px;"></div>';

	document.getElementById("showNewsDiv").innerHTML=str;

	var lb = document.getElementById("facebookCommentsInNewsDiv");
	lb.innerHTML='<div class="fb-comments" data-href="http://www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'" data-num-posts="5" data-width="500"></div>';
	FB.XFBML.parse(lb);
 
 }
 function showAnotherNewsPart(fileSourceLanguageId,orderNo,fileId,arrayType,title,desc,path)
 {
    var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	    for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
	 
	 
	  document.getElementById("newschangeDIV").innerHTML = '<img alt="'+title+'" src="'+path+'" title="'+desc+'" style="max-width:780px;max-length:800px;"/>';
	  var str='';
	  str += ' <center> <table><tr>';
	 for(var j in results.fileVOList)
	 {
	    if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
		 for(var k in results.fileVOList[j].fileVOList)
		 {
		   if(results.fileVOList[j].fileVOList[k].orderNo != orderNo)
		    str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+results.fileVOList[j].fileSourceLanguageId+','+results.fileVOList[j].fileVOList[k].orderNo+','+results.fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+results.fileVOList[j].fileVOList[k].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+results.fileVOList[j].fileVOList[k].path+'" /><br />&nbsp;&nbsp;'+results.fileVOList[j].fileVOList[k].orderName+'</a></td>';
		 }
	 }
	 str += '  </tr></table></center>';
	 document.getElementById("buildSourceParts").innerHTML = str;
 }
 function showAnotherSource(fileSourceLanguageId,fileId,arrayType)
 {
    var results;
	 if(arrayType=="initialArray")
	  {
	    for(var i in newsData){
	       if(newsData[i].fileId == fileId)
	        results = newsData[i];
	     }
	  }
	 else
	 {
	   for(var i in newsPopUpData){
	       if(newsPopUpData[i].fileId == fileId)
	        results = newsPopUpData[i];
	     }
	 }
 var fileVo;
 for(var j in results.fileVOList)
 {
    if(results.fileVOList[j].fileSourceLanguageId == fileSourceLanguageId)
     fileVo = results.fileVOList[j];
 }
    str = '';
    str+='<center>';

	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+fileVo.source+'</font> &nbsp;&nbsp;&nbsp;<B> Date </B>:<font color="#FF4500"> '+results.fileDate+'</font>';
	 str+='       </td>';
	 str+='       <td>';
	 str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     </table>';
	
	 str+='     <table>';
	 str+='			<tr>';

	if(arrayType=='array')
	{
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }

	 
		str+='             <td><div  id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+fileVo.fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 

	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
  }

	else if(arrayType=="initialArray")
	{
	 
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 
	 
	 str+='             <td><div id="newschangeDIV" class="popupcontainer"><img alt="'+results.fileTitle1+'" src="'+fileVo.fileVOList[0].path+'" title="'+results.fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 
	 
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 
	}

	str+='		      </tr>';
	str+='         </table>';

	str += '<table><tr>';
	str+='       <td>';
	str+='        '+results.fileDescription1+'';
	str+='       </td>';
	str+='     </tr>';
	str+='</table>';
	
	str += '</center>';
 
 document.getElementById("showremainingNewsSources").innerHTML = str;
 
  str ='';
	str += '<center>  <table><tr>';

	for(var l=1;l<fileVo.fileVOList.length;l++)
	{
	  str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showAnotherNewsPart('+fileVo.fileSourceLanguageId+','+fileVo.fileVOList[l].orderNo+','+fileId+',\''+arrayType+'\',\''+results.fileTitle1+'\',\''+results.fileDescription1+'\',\''+fileVo.fileVOList[l].path+'\')"><img width="65" height="60" alt="'+results.fileTitle1+'" title="'+results.fileDescription1+'" src="'+fileVo.fileVOList[l].path+'" /><br />&nbsp;&nbsp;'+fileVo.fileVOList[l].orderName+'</a></td>';
	}
	str += '  </tr></table></center>';
	
	document.getElementById("buildSourceParts").innerHTML = str;
	
	str ='';
	str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	for(var k=0;k<results.fileVOList.length;k++)
	{
	  if(results.fileVOList[k].fileSourceLanguageId != fileSourceLanguageId)
	  str += '<td><a  class="newssources"  href="javascript:{}" onclick="showAnotherSource('+results.fileVOList[k].fileSourceLanguageId+','+fileId+',\''+arrayType+'\')">'+results.fileVOList[k].source+'</a></td>';
	}
	str += '  </tr></table></center>';

  
    document.getElementById("buildSources").innerHTML = str;
 
 }
 function getTotalNews(viewType)
 {  
	  
    var queryType='Public';
   if(document.getElementById("candidateVisibility")!=null)
    { 
	   var candidateVisibilityEle = document.getElementById("candidateVisibility");
	    if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Public')
		 {
		  queryType='Public';
		  queryTypeChecked='Public';
		 }
		 if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='Private')
		 {
		  queryType='Private';
		  queryTypeChecked='Private';
		 }
		  if(candidateVisibilityEle.options[candidateVisibilityEle.selectedIndex].value=='All')
		  {
		   queryType='All';
		   queryTypeChecked='All';
		  }
	}
	
        var jsObj =
		    {   
		        time : timeST,
			    viewtype:viewType,
			    candidateId:candidateId,
			    startRecord:0,
			    maxRecord:100,
			    queryType:queryType,
			    task:"getNewsToDisplay"
		     };
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }

 function showTotalNews(results)
 { 
     deleteAllElements();
	 newsPopUpData = results;
	if(results != null && results.length >0){
     $("#showAllNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy"><b>News</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showAllNewsDiv").dialog();
    
     var str ='';
     str+='<fieldset class="imgFieldset">';
	 str+='   <table style="width:98%">';
	 str+='     <tr>';
     str+='       <td colspan="2"><div id="showScopeWiseNewsCount" /></td>';
     str+='     </tr>';
	 str+='   </table>';
     str+='  <table style="width:100%;font-family: trebuchet MS; font-size: 13px;">'; 
  for(var i in results)
   { 
	 fileIdArray[i]=results[i].fileId;	    	  
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
	 var sourcedata ='';
	 var languag = new Array();

	 for(var j in results[i].fileVOList)
	 {
	    count = 0;
	   sourcedata+='<a title="Click To Get All '+results[i].fileVOList[j].source+' News " href="javascript:{}" onclick="news_Obj.getNewsBySource(\''+results[i].fileVOList[j].source+'\')" class="titleStyle"><font color="#FF4500">'+results[i].fileVOList[j].source+'</font></a> - ';
       for(var k in languag)
	   {
	     if(languag[k] == results[i].fileVOList[j].language)
	       ++count;
	   }
	   if(count == 0)
	   languag.push(results[i].fileVOList[j].language);
	 }
	 var lang = '';
	 for(var l in languag)
	   {
	     lang+='<a title="Click To Get All '+languag[l]+' News" href="javascript:{}" onclick="news_Obj.getNewsByLanguage(\''+languag[l]+'\')" class="titleStyle"><font color="#FF4500">'+languag[l]+'</font></a> - ';
	   }
	 str+='       <td>'+sourcedata.slice(0, -2)+' | '+lang.slice(0, -2)+' |'; 
	 if(results[i].categoryType != null)
	   {
	    str+='  <a title="Click To Get All '+results[i].categoryType+' News" href="javascript:{}" onclick="news_Obj.getNewsByCategory(\''+results[i].categoryType+'\')" class="titleStyle"><font color="#FF4500">'+results[i].categoryType+'</font></a> |'; 
	   }
	 if(results[i].importance != null)
	   {
	    str+='  <a title="Click To Get All '+results[i].importance+' News" href="javascript:{}" onclick="news_Obj.getNewsByNewsImportance(\''+results[i].importance+'\')" class="titleStyle"><font color="#FF4500">'+results[i].importance+'</font></a> |'; 
	   }
	str+=''+results[i].fileDate+'</td>';
    
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='    <tr><td><hr style="width:98%;"></td></hr></tr>';
   }
   str+='  </table>';
   str+='<div id="custom_paginator_class" class="paginatorElmtClass"></div>';	
   str+='</fieldset>';
   document.getElementById("showAllNewsDiv").innerHTML=str;
   getScopeWiseNewsCount();
  
  
	 }
 }
 
 function  buildNewsCount(result)
 {
    pagi= result[result.length-2].fileTypeId;

    if(result[result.length-1].visibility=="False")
	   queryTypeChecked='Public';
    str='';
	str+='   <table style="padding-left:118px;padding-top:10px;padding-bottom:20px;">';
	str+='     <tr>';
	if(result[result.length-1].visibility=="True")
	{	 
	 str+='       <td style="font-weight:bold;font-size:12px;color:navy;">SORT BY : </td>';
	 str+='       <td>';
	 str+='          <select id="candidateVisibility" onchange="news_Obj.getAllNewsForUser(\'totalNews\');"  >';
	 str+='             <option value="Public">Public</option>';
	 str+='             <option value="Private">Private</option>';
	 str+='             <option value="All">All</option>';
	 str+='          </select>';
	 str+='       </td>';	 
   	}
	 str+='       <td style="font-weight:bold;font-size:12px;color:navy;">&nbsp;&nbsp;&nbsp;TOTAL NEWS ARTICLES : </td>';
	 str+='       <td style="font-weight:bold;font-size:12px;"><a title="Click To Get Total News" href="javascript:{}" onclick="news_Obj.getScopeWiseNews(\'\')" ><font color="brown">'+result[result.length-2].fileTypeId+'</font></a></td>';
	 str+='    </tr>';
	 str+='   </table>';
	 str+='	<hr style="width:98%;">';
     str+='   <table style="padding-bottom:10px;">';
	 str+='     <tr>';
	 str+='     <td style="font-weight:bold;font-size:12px;color:#06ABEA;">NEWS IMPACT LEVELS :</td>';
	 str+='     </tr><tr>';
	 str+='     <td>';
	 str+='      <table>';
	 var i=-1;
	 no_of_imagesPerRow = 5; 
	 var remainingCount = getRemainingCount(parseInt(result[result.length-2].fileTypeId),result);
     for(var k = 0;k<9 ;k++)
	 {
	    
	    if(result[k].fileTypeId>0)
		{
		 i++
        j =i;
		if(j++ % no_of_imagesPerRow == 0)
       str+= '<tr>';
     str+='       <td style="padding-left:20px;font-weight:bold;font-size:11px;color:navy;">'+getFirstCapsLetter(result[k].name)+'</td>';
	 str+='<td><a style="font-size:11px;" title="Click To Get '+getFirstCapsLetter(result[k].name)+' Level News " href="javascript:{}" onclick="news_Obj.getScopeWiseNews('+(k+1)+')" ><font color="brown"><b>-'+result[k].fileTypeId+'</b></font></a></</td>';
	 if(j % no_of_imagesPerRow == 0)
       str+= '</tr>';
	 }
	 }
	 if(++i % no_of_imagesPerRow != 0)
	 {
	   if(remainingCount>0)
	   {
	    str+='<td style="padding-left:20px;font-weight:bold;font-size:11px;color:navy;"> Others</td>';
	    str+='<td><a style="font-size:11px;" title="Click To Get Remaining News" href="javascript:{}" onclick="getOtherNews()" ><font color="brown"><b>-'+remainingCount+'</b></font></a></td>';
	   }
		str+= '</tr>';
	 }
	    str+= ' </table>';	 
	  var remainingCount = getRemainingCount(parseInt(result[result.length-2].fileTypeId),result);

     str+='     </tr>';
	 str+='   </table>';

	str+='	<hr style="width:98%;">';
    document.getElementById("showScopeWiseNewsCount").innerHTML=str; 
	if(queryTypeChecked=="Public")
     {
	    if(document.getElementById("candidateVisibility")!=null)  
        {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='Public';
        }		 
	 }
   if(queryTypeChecked=="Private")
     {
	   if(document.getElementById("candidateVisibility")!=null)    
	    {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='Private';
        }
	 }
   if(queryTypeChecked=="All")
     {
	   if(document.getElementById("candidateVisibility")!=null)    
	    {		
	     var candidateVisibilityEle = document.getElementById("candidateVisibility").value='All';
        }
	 }
   

}
 function getFirstCapsLetter(string)
 {
    return string.charAt(0)+ string.slice(1).toLowerCase();
 }
 function deleteAllElements()
 {
   
   for(var i=0 ; i<fileIdArray.length;)
   {
     fileIdArray.pop();
   }
 }
 
 function getOtherNews()
 {
    timeST = new Date().getTime();
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			startIndex:0,
			maxResults:100,
			queryType:queryTypeChecked,
			task:"getOtherNews"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
 
 }
 function getRemainingCount(total,result)
 {
  for(var i=0;i<9;i++)
    var total = total-parseInt(result[i].fileTypeId);
	
   return total;
 }
 function getScopeWiseNewsCount()
 { 
   timeST = new Date().getTime();
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			queryType:queryTypeChecked,
			task:"getNewsCountByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
   
 }

  function getPreviousNews(val,arrayType)
  { if(arrayType=="initialArray")
    {
     initialCurrentSize = val-1;
    showNewsWithDiffSources(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	}
	else if(arrayType=='array')
	{
	  currentSize = val-1;
    showNewsWithDiffSources(fileIdArray[currentSize],currentSize,arrayType);
	
	}
	
	
  }
  function getNextNews(val,arrayType)
  {
    if(arrayType=="initialArray")
	{
     initialCurrentSize = val+1;
     showNewsWithDiffSources(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	 }
	else if(arrayType=='array')
	{
	 currentSize = val+1;
     showNewsWithDiffSources(fileIdArray[currentSize],currentSize,arrayType);
	}
  }
function getFirstThreePhotoRecords(){
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			task:"getFirstThreePhotoGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
}
function buildFirstThreePhotoRecords(results)
 {

	if(results.length>0)
	 {
	  var count=0;
	  document.getElementById("photoGallaryDiv").innerHTML= '';
	  str ='';
	  str+='<h3 class="main-title"><span class="da-gray">PHOTO GALLERY</span></h3>';
	  str+='<ul class="photo-gallery-fields">';
	  if(results[0].path!=null)
	  {
	   count++;
	   str+='<li><img alt="'+results[0].title+'" title="'+results[0].gallaryDescription+'" src="'+results[0].path+'" class="imageClass"  onclick="getCandidatesPhotosInAGallary('+results[0].gallaryId+')"/><br />';
	  str+=''+results[0].gallaryName+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li style="margin-right: 3px;width:120px;"><img alt="'+results[1].title+'" title="'+results[1].gallaryDescription+'" src="'+results[1].path+'" class="imageClass"  onclick="getCandidatesPhotosInAGallary('+results[1].gallaryId+')"/><br />';
	  str+=''+results[1].gallaryName+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li style="width:120px;"><img alt="'+results[2].title+'" title="'+results[2].gallaryDescription+'" src="'+results[2].path+'" class="imageClass"  onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].gallaryName+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li style="margin-right: 3px;width:120px;"><img alt="'+results[i].title+'" title="'+results[i].gallaryDescription+'" src="'+results[i].path+'" class="imageClass"  onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
	    str+=''+results[i].gallaryName+'</li>';
	   }
	  }
	  str+='</ul>';
	  str+='<div class="more">';
	  str+='<a href="javascript:{}" onclick="photoGallaryPopUp()">More</a></div>';
	  str+='<div id="buildPhotoGallaryDiv"></div>';
	  document.getElementById("photoGallaryDiv").innerHTML= str;
	 }
	 else
	 {
		 document.getElementById("photoGallaryDiv").style.display = 'none';
	 }
}
function getCandidatesPhotosInAGallary(gallaryId)
{
    var jsObj =
		{   time : timeST,
			value:"new",
		    gallaryId:gallaryId,
			task:"getPhotosInAGallary"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
}
function openFile(filePath,fileType){

window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}

function buildCandidateElectionInfo()
{	
	var wFlag = true;
	var lFlag = true;
	var str ='';
	var electionInfoElmt = document.getElementById("electionInfo");
	
	str+='<h1 class="inc-title">Election Profile</h1>';

	str+='<s:iterator value="candidateElectionDetails" status="stat">';

	str+='<s:if test="status == true">';
	
	if(wFlag == true)
	{
		str += '<h3 class="win-title">Won</h3>';
		wFlag = false;
	}
	
    str+='<ul class="wl-sub-details">';
	str+='<li onmouseover="this.style.color=\'#06ABEA\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<strong>Won in <s:property value="electionYear" /> </strong> <s:property value="electionType" /> Election with <s:property value="votesPercentage" />% of votes gain for <s:property value="partyName" /> party in <s:property value="constituencyName" /> constituency</li></ul></s:if>';
	
	str+'</s:iterator>';
	
	str+='<s:iterator value="candidateElectionDetails" status="stat">';
	str+='<s:if test="status == false">';
	
	if(lFlag == true)
	{
		str+='<h3 class="loss-title">Lost</h3>';
		lFlag=false;
	}
	
    str+='<ul class="wl-sub-details">';
	str+='<li  onmouseover="this.style.color=\'#F13144\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<strong>Lost in <s:property value="electionYear" /></strong> <s:property value="electionType" /> Election with <s:property value="votesPercentage" />% of votes gain for <s:property value="partyName" />  party in <s:property value="constituencyName"/> constituency</li></ul></s:if>';
	
	str+'</s:iterator>';
	if(electionInfoElmt != null)	
	electionInfoElmt.innerHTML = str;
}

function candidateInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';
	
    str+='<div class="ptd-sec">';
	str+='<img height="250" width="180" onerror="setDefaultImage(this)" src="images/candidates/${candidateVO.candidateName}.jpg"><span class="tc-tf pa-fi">';
	str+='<label class="c-red"><a href="candidateElectionResultsAction.action?candidateId=${candidateId} ">${candidateVO.candidateName}</a></label></span><br />';
	
	str += '<span style="font-weight:bold;font-family:arial;">${candidateElectionDetails[0].candidateRole}</span><br />';
	
	str+='<s:if test="candidateElectionDetails[0].constituencyId !=null">';
	str+='<a href="constituencyPageAction.action?constituencyId=${candidateElectionDetails[0].constituencyId}">${candidateElectionDetails[0].constituencyName} CONSTITUENCY</a><br />';
	str+='</s:if>';
	str+='<s:if test="candidateElectionDetails[0].partyName !='INDEPENDENT'">';
	
	str+='<a href="partyPageAction.action?partyId=${candidateElectionDetails[0].partyId} ">${candidateElectionDetails[0].partyName} PARTY</a>';
	str+='</s:if>';
	str+='<s:if test="candidateElectionDetails[0].partyName =='INDEPENDENT'">';
	str+='<span style="font-weight:bold;font-family:arial;">${candidateElectionDetails[0].partyName} PARTY</span>';
	str+='</s:if>';
    str+='</div>';
	str+='<div class="clear"></div>';
     	
	candidateInfoElmt.innerHTML = str;
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
								title:'<font color="Navy"><b>${candidateVO.candidateName}</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showProfile").dialog();
   
 
   var str ='';
    str+='<fieldset class="imgFieldset">';
    str+='  <table><tr><td>';
    str+='  <s:if test="descriptions != null">'; 
    str+='  <div style="font-weight: bold; font-size: 14px;">About ${candidateVO.candidateName}</div>';
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
 
 function displayProfile()
 {
 
   var profileInfoElmt = document.getElementById("pm-inner-cont-sec");
   
   if(profileInfoElmt == null)
	   return;
   
   var str = '';
   var descFlag = 1;
   
   str += '<s:if test="descriptions != null"> ';
   str += '<h1 class="inc-title">About ${candidateVO.candidateName}</h1>';
   
   str += '<s:iterator value="descriptions">';
   
   if(descFlag <= 2)
   {
	  str += '  <p style="font-size:13px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property/>';
	  descFlag++;
   }
   str += '  </s:iterator>';

   str += '<div class="read-more"><a href="javascript:{}" onclick="getTotalProfile()" style="color: LightSkyBlue;">';
   str += 'Read More >></a></div>';
   str += '</s:if>';
   
   profileInfoElmt.innerHTML = str;
 }

 function getVideosOfCandidate()
{
	var jsObj =
		{   
		   	candidateId : candidateId,
			startIndex  : 0,
			maxRecords	: 100,
			time		: timeST,
			task:"getCandidateLatestVideos"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidateLatestVideosAction.action?"+rparam;					
	callAjax(jsObj,url);
}

function buildVideoGallaryDiv(result)
{
	if(result == null || result.length == 0)
		return;

	var videoGalEle = document.getElementById("videogallery");
	var str = '';

	str += '<DIV>';
	str += '<a rel="#voverlay" href="http://www.youtube.com/v/'+result[0].path+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
	str += '<img src="http://img.youtube.com/vi/'+result[0].path+'/0.jpg" width="230px;" height="210px;"/></a>';
	str += '</DIV>';

	if(result.length >= 2)
	{
		str += '<DIV><table><tr>';
		for(var i=1;i<result.length && i<5;i++)
		{
			str += '<td><a rel="#voverlay" href="http://www.youtube.com/v/'+result[i].path+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer" width="70px;">';
	str += '<img src="http://img.youtube.com/vi/'+result[i].path+'/0.jpg" width="75px;" height="70px;"/></a></td>';
		}
		str += '</tr></table></DIV>';
	}

	videoGalEle.innerHTML = str;
}

function showCandidateElectionDetails(str)
{
	var politicalChangesWindow = window.open(str+"","constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
    politicalChangesWindow.focus();
}

function videoGallaryPopUp()
{
	var str='';
   <s:if test="fileVO == null || fileVO.size() < 4"> 
   return;
   </s:if>
  
   $("#videoGallaryPopUpDiv").dialog({ stack: false,
							    height: 590,
								width: 630,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">Video Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});

								 str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading Video Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#videoGallaryPopUpDiv").html(str);


	showAllVideoGalleries();
}

function showAllVideoGalleries(){
		var str='';
str+='<div style="margin:5px;font-size:13px;margin-left:32px;"> Loading Video Galleries .....<img style="float:right;margin-right:249px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#videoGallaryPopUpDiv").html(str);

   var jsObj = {
	       	   time : timeST,
			   candidateId:candidateId,
			   startRecord:0,
			   maxRecord:100,
			   task:"videoGalleriesForACandidate"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
	}

function buildVideoGallaries(results)
{
    var str ='';
    str+='<table width="100%">';
   	
	for(var i=0;i<results.length;i++)
	{
		if(i%4 == 0)
			str += '<tr height="185px;">';

		str += '<td valign="top" width="25%"><table width="100%" style="margin-bottom: 15px;">';
		str += '<tr><td>';
		str+='<img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" width="120px;" height="120px;" style="cursor: pointer;" onClick="getVideosInAGallary('+results[i].gallaryId+')" alt="'+results[i].gallaryName+'" title="'+results[i].gallaryDescription+'"/></td></tr>';
		str+='</div>';
		str += '<tr><td><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
		str+= '<tr><td><div style="font-size: 13px; font-family: verdana,arial;""><b>Videos: ('+results[i].sizeOfGallary+')</b></div></td></tr></table></td>';
		
		if((i+1)% 4 == 0)
			str += '</tr>';
		
	}
		str+='</table>';
		document.getElementById("videoGallaryPopUpDiv").innerHTML = str;
}
function getVideosInAGallary(gallaryId){
	var jsObj = {
			
			time : timeST,
			gallaryId:gallaryId,
			task:"getVideosInGallary"
          };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
     callAjax(jsObj,url);
}
function buildAllVideosInGallary(results){

 var str ='';
 var videosDivElmt = document.getElementById("videoGallaryPopUpDiv");
		
		
		str+='<a href=javascript:{} style="color: #FFFFFF;margin-left: 339px;"" onclick="showAllVideoGalleries()" class="imageButton">Back To Galleries</a>';
		
		str+='<table style="width:100%;margin-top:15px;">';
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:230px;">';
			
			str += '<td valign="top" width="5%">';
			str += '<table style="font-size:13px;font-family:verdana,arial;">';
			str += '<tr>';
			str += '<td style="border:2px solid #CCCCCC;padding:5px;width:100px;">';
			str += '<a href=javascript:{} onclick="getVideoDetails('+results[i].contentId+')">';
		
			str+='<img src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="160px;" height="160px;" alt="'+results[i].title+'" title="'+results[i].description+'"/></td></a>';
			str += '</tr>';
			str += '<tr><td>'+results[i].title+'</td></tr>';
			str += '</table>';
			
			if(j % no_of_imagesPerRow == 0)
             str+= '</tr>';
						
		}
		str+='</table>';
		str+='</div>';
		videosDivElmt.innerHTML =str;
			
		
		
}

function getContentDetails(contentId)
{
	var ajaximgEle = document.getElementById("contentAjaxCallImg");
	if(ajaximgEle != null)
		ajaximgEle.style.display="block";

	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Candidate Page',
			requestPageId : '${candidateId}',
			task:"getSelectedContent"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedContentAndRelatedGalleriesAction.action?"+rparam;					callAjax(jsObj,url);
}

function showSelectedContentAndRelatedGalleries()
{
	$.fx.speeds._default = 1000;
	  $("#showContentDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								minHeight: 650,
							  overlay: { opacity: 0.5, background: 'black'},
		 						 close: function(event, ui) {
		  					  document.getElementById("showContentDivInnerDiv").innerHTML ='';
							}
								});
		$("#showContentDiv").dialog();
		getContentDetails('${contentId}');
}

function buildContentDetails()
{
	var ajaximgEle = document.getElementById("contentAjaxCallImg");
	if(ajaximgEle != null)
		ajaximgEle.style.display="none";

	result = showContentResultList;
	if(result == null)
		return;

	var divEle = document.getElementById('showContentDivInnerDiv');
	var str = '';
	var titleStr = null;
	var pathStr = null;
	var descriptionStr = null;
	var preContentId = null;
	var curPos = null;
	var totSize = null;
	
	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${candidateVO.candidateName}\'S - '+result.contentType;

	str += '<Div><center>';
	str += '<div class="main-title-sec">';
	str += '<div id="showContentHeaderDiv" class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;"></div><div class="main-bbg"/></div>';
	
	
	for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
	if(result.relatedGalleries[0].filesList[i].isSelectedContent)
	{
	    selectedContentFile = result.relatedGalleries[0].filesList[i];
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		totSize = result.relatedGalleries[0].filesList.length;
		
		if(result.contentType == 'Video Gallary' || result.contentType == 'News Gallary')
		{
			str+='<table>';
			str+='<tr>';
			str+='<td>';
			if(result.relatedGalleries[0].filesList[i].fileVOList[0].source != null)
				str+='<B>Source</B> : <font color="#FF4500"><span id="sourceChangeSpan">'+result.relatedGalleries[0].filesList[i].fileVOList[0].source+'</span></font> &nbsp;&nbsp;&nbsp;<B>';

			if(result.relatedGalleries[0].filesList[i].fileDate != null)
				str+=' Date </B>:<font color="#FF4500"> '+result.relatedGalleries[0].filesList[i].fileDate+'</font>';

			 str+='</td>';
			 str+='</tr>';
			 str+='</table>';
		}
	}
	
	if(result.contentType == 'Video Gallary')
	{
		if(result.relatedGalleries[0].filesList.length < 2)
			str += '<table width="530px">';
		else
			str += '<table width="880px">';
		str += '<tr>';

		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';
		
		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 0))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
		str += '<td valign="top" style="horizontal-align:center;">';
		str += '<div class="popupcontainer" style="width:500px;">';
		str += '<span id="nextPartImage"><iframe width="500" height="396" src="http://www.youtube.com/embed/'+pathStr+'" frameborder="0" allowfullscreen="true"></iframe></span>';
		str += '<table><tr>';
		str += '<td>';
		str += ''+descriptionStr+'';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[0].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildVideoNewSources">';
	             str += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		   
		str += '</div>';
		str += '</td>';
		
		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="popupcontainer" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 1))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
	str += '</tr>';
	str += '</table>';
	}

	else if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
	{
		str += '<table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
			descriptionStr = result.relatedGalleries[0].filesList[i].description;
          if(result.relatedGalleries[0].filesList.length == 1){
			
		  }else{
		    if(i > 0)
			{
				str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
			}else{
			   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[result.relatedGalleries[0].filesList.length-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
			}
		  }
			str += '<td><div class="popupcontainer" id="nextPartImage" style="text-align:center;"><img alt="'+result.relatedGalleries[0].filesList[i].title+'" title="'+result.relatedGalleries[0].filesList[i].description+'" style="max-width:780px;max-length:800px;" src="'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'" /></div></td>';
          if(result.relatedGalleries[0].filesList.length == 1){
			
		  }else{
			if(i != result.relatedGalleries[0].filesList.length-1)
			{
				str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i+1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i+1].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
			}else{
			   str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[0].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[0].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
			}
		  }
		}

		str += '</table>';

		str += '<div>';
		str += '<table>';
		str += '<tr><td>Description : <b>'+descriptionStr+'</b></td></tr>';
		str += '</table>';
		str += '</div>';
		
		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(result.relatedGalleries[0].filesList[i].isSelectedContent)
		{
		   selectedContentFile = result.relatedGalleries[0].filesList[i];
		   str +='<div id="buildNewSourceParts">';
	       str += '<center><table><tr>';

	         for(var j=1;j<selectedContentFile.fileVOList[0].fileVOList.length;j++)
	         {
	            str += '<td><a style="color:#FF4500;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
	         }
		 
	       str += '  </tr></table>';
	       str +='</center></div>';
		   
		   if(selectedContentFile.multipleSource >1 )
	          {
	             str +='<div id="buildNewSources">';
	             str += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	             str += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
	           
			      for(var k=1;k<selectedContentFile.fileVOList.length;k++)
	              {
	               str += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[k].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[k].source+'</a></td>';
	              }
	            str += '  </tr></table>';
	            str +='</center></div>';
	          }
		}
	}

	if(result.otherGalleries != null && result.otherGalleries.length > 0)
	{
		var galType = null;
		
		if(result.contentType == 'Photo Gallary')
			galType = ' Photo ';
		else if(result.contentType == 'News Gallary')
			galType = ' News ';
		else if(result.contentType == 'Video Gallary')
			galType = ' Video ';

		str += '<div>';

		str += '<Div><center>';
		str += '<div class="main-title-sec">';
		str += '<div class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;">Other '+galType+' gallaries Of ${candidateVO.candidateName}</div><div class="main-bbg"/></div>';
		
		str += '<div class="popupcontainer" style="overflow:auto;width:880px;max-width:850px;">';
		str += '<Table>';
		
		for(var i=0;i<result.otherGalleries.length;i++)
		{
			if(i%5 == 0)
				str += '<tr>';
			
			str += '<td width="20%" valign="top">';

			str += '<table>';
			str += '<tr><td class="videoGalTitleStyle"><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+'Gallery"><font color="red">'+result.otherGalleries[i].gallaryName+'</font></a></td></tr>';
			str += '<tr><td><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+'Gallery">';
			
			if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
				str += '<img width="120px" height="90px" alt="'+result.otherGalleries[i].gallaryName+'" src="'+result.otherGalleries[i].filesList[0].path+'"></img>';
				
			else if(result.contentType == 'Video Gallary')
				str += '<img src="http://img.youtube.com/vi/'+result.otherGalleries[i].filesList[0].path+'/1.jpg"></img>';
			
			str += '</a></td></tr>';
			str += '<tr><td class="fontStyle videoGallDescStyle">Gallery Size : ('+result.otherGalleries[i].orderNo+')</td></tr>';
			str += '<tr><td class="videoGallDescStyle">'+result.otherGalleries[i].description+'</td></tr>';
			str += '</table>';

			str += '</td>';

			if(i%5 == 4)
				str += '</tr>';
		}
		str += '</Table>';
		str += '</div>';

		str += '</div>';
	}
	
	str += '</center></Div>';

	divEle.innerHTML = str;

	var str = '';
	str += ''+titleStr+' ('+curPos+' of '+totSize+')<span style="margin-top:10px;margin-right:18px;float:right">';
	str += '<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+preContentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this in Facebook"></img></a>';
	str += '</span>';
	
	document.getElementById("showContentHeaderDiv").innerHTML=str;
	
}

function buildContentDetailsOfSelected(preId,selId)
{
	for(var i=0;i<showContentResultList.relatedGalleries[0].filesList.length;i++)
	{
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == preId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = false;
		if(showContentResultList.relatedGalleries[0].filesList[i].contentId == selId)
			showContentResultList.relatedGalleries[0].filesList[i].isSelectedContent = true;
	}

	buildContentDetails();
}
function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(type != 'video')
	    var str='<div class="popupcontainer" id="nextPartImage" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:780px;max-length:800px;" src="'+path+'"></div></img>';
	  else
	   var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			  if(type != 'video')
	             str += '<td><a style="color:#FF4500;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
	          else
			     str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[i].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
		    }
		 }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
  
  }

}
function showNewAnotherSource(fileSourceLanguageId,type)
{
     var str1 ='';
	   if(type != 'video')
	     str1 += '<center><table><tr><td><b>Same News in another sources</b></td></tr></table></center>';
	   else 
         str1 += '<center><table><tr><td><b>Same Video in another sources</b></td></tr></table></center>';	   
		 str1 += ' <center> <table style="margin-top:8px;margin-bottom:10px;"><tr>';
  for(var m in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[m].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(document.getElementById("sourceChangeSpan") != null)
	    document.getElementById("sourceChangeSpan").innerHTML = ''+selectedContentFile.fileVOList[m].source+'';
	  if(type != 'video')
	    var str='<div class="popupcontainer" id="nextPartImage" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:780px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" ></div></img>';
	  else
	    var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" frameborder="0" allowfullscreen="true"></iframe>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    if(type != 'video')
	         str += '<td><a style="color:#FF4500;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
			else 
			 str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'video\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="http://img.youtube.com/vi/'+selectedContentFile.fileVOList[m].fileVOList[j].path+'/1.jpg" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
	     }
		 
	   str += '  </tr></table>';
	   str +='</center>';
	  document.getElementById("buildNewSourceParts").innerHTML = str;
	}
    else
	{
	   if(type != 'video')
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'other\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';	             	          	
	   else
	    str1 += '<td><a class="newssources" href="javascript:{}" onclick="showNewAnotherSource('+selectedContentFile.fileVOList[m].fileSourceLanguageId+',\'video\')">'+selectedContentFile.fileVOList[m].source+'</a></td>';
	}
  }
     	str1 += '  </tr></table>';
	    str1 +='</center>';
     if(document.getElementById("buildNewSources") != null)
       document.getElementById("buildNewSources").innerHTML = str1;
	 else
	   document.getElementById("buildVideoNewSources").innerHTML = str1;
}

<s:if test="contentId != null">
	showSelectedContentAndRelatedGalleries();
</s:if>

showAssemblyData();
displayProfile();
candidateInfo();
buildCandidateElectionInfo();
getTotalNews('getFirstFourNewsRecordsToDisplay');
getFirstThreePhotoRecords();
buildAnalyzeConstituencyWindow();
message_Obj.getUserDetails();

function showNotLogIn()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;"> Please Login to ';
		str+='subscribe. <a href="loginInputAction.action" style="color:#80D1F1;">Click here to Login </a></div></div>';		document.getElementById("logInDiv").innerHTML = str;
}

function subscribeAlert()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">You had subscribed successfully </div></div>';		document.getElementById("logInDiv").innerHTML = str;
}
function unSubscribeAlert()
{
   document.getElementById("logInDiv").style.display='block';
			var str='';
		$("#logInDiv").dialog({ stack: false,
									height: 'auto',
									width: 500,
									position:'center',								
									modal: true,
									title:'<font color="#000">ALERT</font>',
									overlay: { opacity: 0.5, background: 'black'},
									
							});
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">You had Unsubscribed successfully </div></div>';		document.getElementById("logInDiv").innerHTML = str;
}

</script>
</body>
</html>