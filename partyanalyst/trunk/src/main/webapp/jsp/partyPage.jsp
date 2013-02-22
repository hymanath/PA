<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${partyVO.partyLongName} NEWS, PHOTOS, VIDEOS, GALLERIES, ELECTION RESULTS AND ANALYSIS</title>
<META NAME="Keywords" CONTENT="${partyVO.partyLongName} NEWS, PHOTOS, VIDEOS, GALLERIES, ELECTION RESULTS AND ANALYSIS">
<meta name="description" content="${partyVO.partyLongName} NEWS, PHOTOS, VIDEOS, GALLERIES, ELECTION RESULTS AND ANALYSIS">
<meta property="fb:app_id" content="167844749984003"/>
<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>

<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<!--<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>-->
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
<style type="text/css">#videogallery a#videolb{display:none}</style>
<!--<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>-->

<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<!-- <script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script> -->
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script> 
<!--<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
-->
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/customPaginator/messagePaginator.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
<!--  for pagination purpose -->
<script type="text/javascript" src="js/customPaginator/totalNewsPaginator.js"></script>



<style type="text/css">

.popupcontainer {		
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 780px;
    	padding: 10px;
		box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
}

.yui-skin-sam.yui-dt table th {
		background-color: #CEEDF0;
		font-size: 13px;
		font-weight: bold;
		/*padding: 7px;*/
		text-align: left;
		border-collapse :collapse;
}
.yui-skin-sam.yui-dt table  {
		
		border-collapse :collapse;
}
.buttonClass {
	background-color: #5CB275;
    border-radius: 6px 6px 6px 6px;
    color: white;
    cursor: pointer;
    margin: 13px;
	width: 580px;
	font-family: verdana;
	font-size: 13px;
	text-decoration: none;
    padding: 4px;
}
.yui-skin-sam.yui-dt tbody{
border: 1px solid #CDCDCD;
    font: 12px verdana,arial,tahoma;
}
.yui-skin-sam .yui-dt-liner {
margin: 0;
padding: 0;
padding: 4px 2px 4px 10px;
}
.yui-skin-sam thead .yui-dt-sortable{color:#000;}
 .yui-skin-sam.yui-dt td {
	font-weight: normal;
	/*padding: 8px 8px 8px 10px;*/
}
.yui-skin-sam .yui-pg-container{font-size: 12px;}
#parliamentElecProfileDiv > table * tr:nth-child(even){background:#f9f9f9;}
#parliamentElecProfileDiv{margin-top:12px;}
#assemblyElecProfileDiv > table * tr:nth-child(even){background:#f9f9f9;}

.yui-dt-liner a:hover{ color: blue}		

.ui-widget-header {
	border:0px;
	color:none;
	font-weight:bold;
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
 .tableStyle 
 {
   align:top;
   font: 14px "Trebuchet MS",Arial,Helvetica,sans-serif;
 }
 .imageStyle {
  vertical-align:top;
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
	font:bold 11px/24px "Trebuchet MS", Arial, Helvetica, sans-serif; 
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
#statePageHeading {
    background-image: url("images/icons/electionResultsAnalysisReport/mid.png");
    background-repeat: repeat-x;
    color: #1C4B7A;
    font-size: 12px;
    font-weight: bold;
    height: 30px;
}
#stateNameSpan {
    padding-left: 9px;
    padding-right: 19px;
    position: relative;
    top: 6px;
}
#constituencyPageCenterInfoDiv .rounded {
    width: 620px;
	
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
.ui-widget-content a {
    color:Navy;
}
.paginatorElmtClass{
	text-align: right;
	 margin-right: 10px;
}
.yui-dt-liner a {
	text-decoration:none;
    /*font-weight: bold;*/
	color: #5B5B5B;
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
#fiedsetDiv{
	border-color: -moz-use-text-color #D1D1D1 #D1D1D1;
    border-left: 1.7px solid #D1D1D1;
    border-right: 1.7px solid #D1D1D1;
    border-style: none solid solid;
    border-width: 0 1.7px 1.7px;
}

.container {
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 800px;
    	padding: 10px;
}
#tabsDiv
{
	background: none repeat scroll 0 0 #A3A3A3;
    border-radius: 5px 5px 5px 5px;
    font: bold 14px/20px "Trebuchet MS",Arial,Helvetica,sans-serif;
    margin-left: -9px;
    margin-right: -12px;
    padding: 7px;
    text-transform: uppercase;
}
#tabsDiv a {color: #FFFFFF;text-decoration: none;}
.dashBoardtabsDivSelected
	{
	background: none repeat scroll 0 0 #21B2ED;
    color: #FFFFFF;
    font-weight: bold;
    margin-left: -6px;
    padding: 7px 16px 6px 9px;
	}
#electionProfileDiv {
    border: 1px solid #CDCDCD;
    border-radius: 4px 4px 4px 4px;
    margin-left: -12px;
    margin-top: 15px;
    padding-left: 9px;
    padding-right: 11px;
    width: 100%;
}
#assemblyProfileId{margin-left: 9px;}
#partyManifestoDiv {border-bottom: 1px solid #CDCDCD;width: 285px;}

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
 padding:8px 8px 8px 8px;
 margin-left:5px;
 border-radius: 5px 5px 5px 5px;

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

</style>

</head>
<body>

<div id="fb-root"></div>
<script>


(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {return;}
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

</script>

<table width="999px" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td>
<!-- <div style="text-align:center;margin-bottom:10px;">
<script type="text/javascript"><!--
google_ad_client = "ca-pub-0938408694174139";
/* PartyPageHeader */
google_ad_slot = "2678494123";
google_ad_width = 728;
google_ad_height = 90;
//-->
<!-- </script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</div> -->

<a id="inline" href="#fancydivbox" style="display:none"></a>
<div id="promodiv" style="display:none;">
	<div id="fancydivbox">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
</div>

<div class="main-title-sec">
 <div class="main-mbg">${partyVO.partyLongName} 
 
 <span style="margin-top:10px;margin-right:18px;float:right">
 <a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/partyPageAction.action?partyId=${partyId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a> 
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
              <li><a id="developmentsParty" onclick="" href="javascript:{}">Developments</a><span></span></li>
            </ul>
			<div id="developmentspopForParty">
				<div id="developmentspopForParty_window_inner"></div>
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
	 <div id="logInDiv"></div>
      <div class="ea-fc-sec">
            <h2 class="ea-fc-title">Assess Your Leader <span class="blue-down-arrow"><img src="images/icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            <div id="analyzeConstituency" class="ea-fc-cont-sec" >

<table style="margin-top:10px;">
 <tr>
	<td>
      <select id="elecTypeSel" onchange="showHideState();" style="width:145px;" >
         <option value="0">Select Election Type</option>
         <option value="1">Parliament</option>
         <option value="2">Assembly</option>
      </select>
	</td>
 </tr>
 <tr>
	<td>
	  <div id ="stateBody" >
        <select onchange="getYears(2);" id="stateTypeSel" style="width:145px;" >
          <option value="0">Select State</option>
        </select>
	  </div>
	</td>
	<td>
	  <div style="display:none;" id="state_ImgSpan"><img src="images/icons/search.gif"></div>
	<td>
 </tr>
 <tr>
	<td>
      <select id="yearTypeSel" style="width:145px;" onchange="getCandidates();" >
         <option value="0">Select Year</option>
      </select>
	</td>
	<td>
	  <div style="display:none;" id="year_ImgSpan"><img src="images/icons/search.gif"></div>
	<td>
 </tr>
 <tr>
	<td>
      <select style="width:145px;" id="candidateTypeSel" >
         <option value="0">Select Candidate</option>
      </select>
	</td>
	<td>
	  <div style="display:none;" id="candidate_ImgSpan"><img src="images/icons/search.gif"></div>
	<td>
 </tr>
</table>

<div id="errorDivEle" style="margin-top: 3px; margin-bottom: 3px; margin-left: 0px;color:red;font-weight:bold;"></div>
	<div style="margin-top:5px; margin-bottom: 28px;margin-left:-12px;">
		<a href="javascript:{}" style="background:#5CB275;color: #FFFFFF;font-weight: bold;margin-left:0px;padding: 3px 5px;text-decoration:none;border-radius:4px;" onclick="getCandidateDetailsForAsses('analyze');">Assess</a>
		<a href="javascript:{}" style="background:#5CB275;color: #FFFFFF;font-weight: bold;margin-left: 3px;padding: 3px 5px;text-decoration:none;border-radius:4px;" onclick="getCandidateDetailsForAsses('viewResults')">Previous Posts</a>
	</div>
 </div>
</div>
	   <!--EMAIL ALERT SECTION START-->
          
          <div class="ea-fc-sec">
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images/icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            <div class="ea-fc-cont-sec" style="font-size:13px;">
              			  
				<span id="subscribeSpan">
		 
					
					<s:if test="isSubscribed == true ">
					Unsubscribe to stop<br/>
					updates of<br />
					<span class="li-red">${partyVO.partyLongName}</span><br/>
					<input  class="unsubscribebtn" type="button" onclick=
					"unsubscriptionDetails()" value="UNSUBSCRIBE"/>
					</s:if>
					
					<s:else>
					Subscribe and get <br/>
					updates of<br />
					
					<span class="li-red">${partyVO.partyLongName}</span><br/>
					<input  class="subscribebtn" type="button" onclick=
					"subscriptionDetails()" value="SUBSCRIBE"/>
					</s:else>

					
				

				</span>
              <!--<input name="" type="text" id="emailId" class="ea-text-fields" value="your email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';document.getElementById('alertMsg').innerHTML = '';"/>
              <div id="alertMsg" style="dispaly:none"></div>
			  <div class="pl-sub-but"><a onclick="validateEmailField()" href="javascript:{};"><strong>Set alert</strong></a></div>-->
            </div>
          </div>
          
		   <!-- Assess Candidate Start 
			<div id="assessCandidateMainDiv" class="ea-fc-sec">
			<h2 class="ea-fc-title" style="font:bold 12px/33px 'Trebuchet MS',Arial,Helvetica,sans-serif">Assess your party leader</h2>
				<div id="assessCandidateBodyDiv" class="ea-fc-cont-sec"> </div>
			</div>
	
	<!-- Assess Candidate End --> 

          <!--EMAIL ALERT SECTION END--> 
     </div>
   
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
		
		  <!--  ABOUT POLITICIAN SECTION END  --> 
             
			<!-- <div id="showParliamentResultDiv"></div>-->
		
			 <!--ELECTION PROFILE SECTION START-->
			 <h1 class="inc-title"><span class="da-gray">Election Profile</span></h1>
			 <div style="margin-top: 10px;"> 
			   <a href="partyResultsCriteriaAction.action" class="buttonClass" style="text-decoration: none;" title="Click here to view other party performances and results">View ${partyVO.partyShortName} Performance and Opposition Parties Details</a></div>
			 <div id="electionProfileDiv"></div>
			   
              <!-- <div class="pm-inner-cont-sec" id="partyManifestoDiv">
			   
			   <div id="manifestoesDiv" style="border-top: 1px solid rgb(205, 205, 205); margin-top: 25px; padding-top: 13px;">
			   <s:if test="partyVO.electionTypes.size>0">
			   <h1 class="inc-title"><span class="da-gray">${partyVO.partyShortName} Party Manifestoes</span></h1>
			   <s:iterator value="partyVO.electionTypes" status="stat">
			   <s:if test="partyVO.electionTypes[#stat.index] =='Parliament'">
			   <div><input style="margin:5px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="getPartyManifesto(${partyVO.partyId});" checked="true"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   <s:if test="partyVO.electionTypes[#stat.index] == 'Assembly'">
			   <input style="margin:5px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="selectedState('stateDiv');"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   </s:iterator>
			   <div id="selectStatediv" style="display:none;margin-left: 188px;margin-top: -17px;"><select id="stateDiv" name="stateDiv" onchange="getPartyManifestoBasedOnStateId();" class="selectWidth"></select></div>
				<div id="manifestoFilesDiv" style="margin-top: 7px;"></div>
			   </div>
			</s:if>
			</div>-->
			  <!--ELECTION PROFILE SECTION END--> 

			<s:if test="customPages != null && customPages.size() > 0">
			<s:iterator value="customPages" var="custom"> 
				<s:if test="#custom.type == 'center_div'">
					<div style="width:435px;">
						<jsp:include page='${custom.name}' flush="true"/> 
					</div>
				</s:if>
			</s:iterator>
			</s:if>

			  <!--PHOTO SECTION START-->
          
                <div class="pm-inner-cont-sec" id="photoGallaryDiv"  style="border-top: 1px solid #cdcdcd; padding-top: 17px;"></div>
            
			 <div class="clear"></div>
			
			<div>
			<div class="fb-comments" data-href="http://www.partyanalyst.com/partyPageAction.action?partyId=${partyId}" data-num-posts="500" data-width="420"></div>
			</div>

			
		  <!-- Message displaying-->
		  <div id="commentBoxDiv" style="margin-top:15px;" class="constituencyPageCenterInfoDiv.rounded">
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
		<div id="videogallery" class="fleft">

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
				<li><a rel="#voverlay" title='<s:property value="title"/>'onClick="getVideoDetails(<s:property value='contentId'/>)">
				<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width:95px;height:80px;"/></a></li>
				</s:if>
			</s:iterator>
		</ul>
	</s:if>
    </div>
  </s:if>

     <s:if test="fileVO != null && fileVO.size() > 4"> 
	 <div class="more"><a onClick="videoGallaryPopUp();" href="javascript:{};">More</a></div>
	 </s:if>
	<div class="pm-inner-cont-sec" id="partyManifestoDiv">
			   
			   <div id="manifestoesDiv" style="border-top: 1px solid rgb(205, 205, 205); margin-top: 25px; padding-top: 13px;">
			   <s:if test="partyVO.electionTypes.size>0">
			   <!-- <h1 class="inc-title" ><span class="da-gray">${partyVO.partyShortName} Party Manifestoes</span></h1>-->
			   <h1 class="pr-title" style="font-family: sans-serif;">${partyVO.partyShortName} Party Manifestos
			   <span class="or-down-arrow">
				<img alt="" src="images/icons/or-down-arrow.png">
				</span>
			   </h1><br><br>
			   <s:iterator value="partyVO.electionTypes" status="stat">
			   <s:if test="partyVO.electionTypes[#stat.index] =='Parliament'">
			   <div style="margin-top: 1px;font-size: 13px;"><input style="margin: 5px 5px 5px -140px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="getPartyManifesto(${partyVO.partyId});" checked="true"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   <s:if test="partyVO.electionTypes[#stat.index] == 'Assembly'">
			   <input style="margin:5px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="selectedStateForAssembly('stateDiv');"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   </s:iterator>
			   
			   <div id="selectStatediv" style="display:none;margin-top: 0px; margin-left: 100px;"><select id="stateDiv" name="stateDiv" onchange="getPartyManifestoBasedOnStateId();" class="selectWidth" style="width:160px;"></select></div>
				<div id="manifestoFilesDiv" style="margin-top: 7px;"></div>
			   </div>
			</s:if>
			</div>
	<!--
	<div>
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
	</div>  -->
			
	<div id="showContentDiv">
	 <div id="contentAjaxCallImg"><img src="images/icons/goldAjaxLoad.gif"></div>
	 <div id="showContentDivInnerDiv"></div>
	</div>
	<div id="videoGallaryPopUpDiv"></div>
	<div id="emailAlertDiv"></div>
	<div id="sendMessageDiv">
		<div id="constituencySelectDiv"></div>
	</div>
	<!--VIDEOS SECTION END--> 

	<s:if test="customPages != null && customPages.size() > 0">
		<s:iterator value="customPages" var="custom"> 
			<s:if test="#custom.type == 'right_navigation'">
				<div style="width:300px;">
					<jsp:include page='${custom.name}' flush="true"/> 
				</div>
			</s:if>
		</s:iterator>
	</s:if>

 </td>
 </tr>
</table>
<script type="text/javascript">
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

		var loadingFirstTime = '${sessionScope.partyPageLoadingFirstTime}';

$(document).ready(function(){
	if(loadingFirstTime  == 'true'){
		
		$("#inline").fancybox();
		$("#inline").trigger("click");
		}
	});


 });
		 
    var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var partyId = '${partyId}';
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
   var queryTypeChecked='Public';
   var showContentResultList = null;
   var incAlliances = false;
   var clickedButtonType = '';
   var newsData;
   var newsPopUpData;
   var selectedContentFile;
   var videosNewData;
   var stateId='${stateId}';
   var cnstuencyId='${constituencyId}';
   var userName = '${sessionScope.UserName}';
   var isSubscribed = '${sessionScope.isSubscribed}';


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
			    partyId:partyId,
			    queryType:queryType
			    
		     };
        
	    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	    var url = "partyPhotoGallaryAction.action?"+rparam;

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
	</script>
 <script type="text/javascript">
   var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var partyId = '${partyId}';
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
   var queryTypeChecked='Public';
   var showContentResultList = null;
   var incAlliances = false;
   var clickedButtonType = '';
   var newsData;
   var newsPopUpData;
   var selectedContentFile;
   var videosNewData;
   var stateId='${stateId}';
   var cnstuencyId='${constituencyId}';
   var userName = '${sessionScope.UserName}';
   var isSubscribed = '${sessionScope.isSubscribed}';
   
 function sendMessage()
 {
  var name = document.getElementById("name").value;
  var stateSelect=document.getElementById("stateSelect").value;
  var constituencySelect=document.getElementById("constituencySelect").value;
  var message=document.getElementById("message").value;
    document.getElementById('galErrorMsgDivId').innerHTML = '';
    document.getElementById('fileUploadErrorMsgDivId').innerHTML = '';
  var errorDivEle = document.getElementById('galErrorMsgDivId');
	var eFlag = false;
	var isprivate='';
	if(document.getElementById("privateId").checked==true)
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
		    partyId :partyId,
            name : name,
			stateSelect : stateSelect,
			constituencySelect : constituencySelect,
			message : message,
		    isprivate:isprivate,
		   	task : "saveMessageToParty"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	

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
			id: partyId,
			task: "subscriptionDetails",
			page:"partyPage"
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
			id: partyId,
			task: "unsubscriptionDetails",
			page:"partyPage"
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
 }
function unSubscribeBtnBuild()
{
$('#subscribeSpan').html('');


//alert('in unsubscribe');
var str='';
str+='Unsubscribe to stop<br/>updates of<br />';
 str+='<span class="li-red">${partyVO.partyLongName}</span><br/>';
str+='<input  class="unsubscribebtn" type="button" onclick="unsubscriptionDetails()" value="UNSUBSCRIBE"/>';

$('#subscribeSpan').html(str);
subscribeAlert();
}

function subscribeBtnBuild()
{
$('#subscribeSpan').html('');
var str='';
str+='subscribe to get<br/>updates of<br />';
 str+='<span class="li-red">${partyVO.partyLongName}</span><br/>';
str+='<input  class="subscribebtn" type="button" onclick="subscriptionDetails()" value="SUBSCRIBE"/>';

$('#subscribeSpan').html(str);
unSubscribeAlert();
}
function showAssessMentDiv()
{
 var assessCanBodyDivElmt=document.getElementById("assessCandidateBodyDiv");
	var str='';
	str+='<input type="radio" name="assessradio" value="1" id="assessParliamentId" onclick="getElectionYearsForAssessment()">Parliament';
	str+='<input type="radio" name="assessradio" value="2" id="assessAssemblyId" onclick="getStatesBasedOnPartyId()">Assembly';
	str+='<select id="assessStateId" style="display:none;" onchange="getElectionYearsForAssessment()"><option value="0">Select State</select>';
	str+='<select id="assessElectionYearDiv" style="display:none;"><option value="0">Select Election Year</select>';
	/*str+='';
	str+='';
	str+='';*/
	assessCanBodyDivElmt.innerHTML = str;
}
function getStatesBasedOnPartyId()
{
	var jsObj = {
			partyId : ${partyId},
			task : "getStatesBasedOnParty"
	};
	var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url= "getCandidateDetailsForAssessment.action?"+rparam;
	callAjax(jsObj,url);
}
function getElectionYearsForAssessment(){

	var stateId = document.getElementById("assessStateId").value;
    if(document.getElementById("assessAssemblyId").checked == true)
		var electionTypeId = document.getElementById("assessAssemblyId").value;

	if(document.getElementById("assessParliamentId").checked == true)
		var electionTypeId = document.getElementById("assessParliamentId").value;
	
	var jsObj = {
				partyId : ${partyId},
				divElmt :"assessElectionYearDiv",
				electionTypeId : electionTypeId,
				stateId :stateId,
				task : "getElectionYearsForAssess"
			}
	var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
	var url= "getCandidateDetailsForAssessment.action?"+rparam;
	
	callAjax(jsObj,url);
 }
function getCandidatesForAssess()
{
  var jsObj = {
			partyId : ${partyId},
			electionId:electionId,
			task:"getCandidatesForAssess"
  };
var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
var url="getCandidateDetailsForAssessment.action?"+rparam;
callAjax(jsObj,url);


}
function clickedButton(type)
{
   clickedButtonType = type;
}
function buildElectionProfile()
{
	var str='';
	str+='<div id="tabsDiv">';
	str+='<a id="parliamentProfileId" href="javascript:{}" onclick="clickedButton(\'parliament\');buildResultForParliamentElection();" class="dashBoardtabsDivSelected"> In Parliament</a>';
    str+='<a id="assemblyProfileId" href="javascript:{}" onclick="clickedButton(\'assembly\');buildResultForAssemblyElection();">In Assembly</a></div>';
	str+='<div style="margin-top:10px;margin-left:20px;"><input type="checkbox" onclick="getElecDetails();" id="allianceChkBox">&nbsp;&nbsp;<font color="navy"><b>Include Alliance</b></font>&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="getElecDetails();" id="bielectionChkBox">&nbsp;&nbsp;<font color="navy"><b>Include Bi Elections</b></font></div>';
	str+='<div id="electionLoading_ImgSpan" style="font-size: 13px;margin: 16px 5px 5px 32px;"> Loading .......<img style="float:right;margin-right:161px;display:block;" src="images/icons/goldAjaxLoad.gif" ></div>';
    str+='<div id="parliamentElecProfileDiv" class="yui-skin-sam" style="display:none"></div>';
    str+='<div style="font-size: 13px; margin: 19px 10px 10px;">Bi = Bi-Election <br/>PC = Participated Constituencies    <br>VP = Voting Percentage <br> PC (%)= Participated Constituencies Voting Percentage	</div>';
 $("#electionProfileDiv").html(str);

}
function getElecDetails()
{
  var bielec = false;
  var alliance = false;
  if(document.getElementById("allianceChkBox").checked == true)
     alliance = true;
  else
     alliance = false;
  if(document.getElementById("bielectionChkBox").checked == true)
     bielec = true;
  else
     bielec = false;
	getElectionProfile(alliance,bielec);
}
function showAssemblyData()
 {
   var str='';
   
   str += '  <div class="pr-sub-fields-sec">';
   str += '  <h1 class="pr-title" style="margin-top:15px;">send a message to ${partyVO.partyLongName} <span class="or-down-arrow">';
   str += '  <img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>';
   str += '	<div id="galErrorMsgDivId" style = "width:190px;"></div>';
   str += '	<div id="fileUploadErrorMsgDivId" style = "width:190px;"></div>';	  
   str += ' <table>';
   
   str += ' <tr>';
   str += '		<td style = "padding-top:9px;">Name <font class="requiredFont"> * </font></td>';
   str += '		<td> <input type = "text" id="name" size = "20" class="sm-text-fields"> </td>';
   str += ' </tr>';

   str +='   <tr>';
   str +='     <td>State</td>';
   str +='     <td style="padding-top: 5px;">';
   str +='     <select id="stateSelect"  onchange="clearAll(\'constituencySelect\');getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,\'constituency\')" style = "width:192px;background-color:#EBE8E8; border:1px solid #ffffff;"/></td>';
   str +='   </tr>';

   str +='   <tr>';
   str +='     <td>Constituency <font class="requiredFont"> * </font></td>';
   str +='     <td style="padding-top: 5px;"><select id="constituencySelect" style = "width:192px; background-color:#EBE8E8; border:1px solid #ffffff;"/></td>';
   str +='   </tr>';
   str +='<tr>';
   str +='<td colspan="2"> <input type="checkbox" id ="privateId" checked>&nbsp Display this message publicly</td>';
  
   str +='</tr>';

   str +=' <tr>';
   str +='   <td >Message <font class="requiredFont"> * </font></td>';
   str +=' <td style="padding-top: 5px;"> <textarea id= "message" name ="message" rows="4" cols="8" style="background-color: #EBE8E8;';
   str +=' border: 1px solid #ffffff;';
   str +=' color: #000000; width: 181px;height: 85px;';
   str +=' font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;';
   str +=' padding: 0px 0 0px 0px;"></textarea></td>';
   str += '</tr>';

   str += '<tr>';
   str += '   <td></td><td style="padding-top: 5px;"> <a href="javascript:{}" onClick="sendMessage()"> <img src="images/icons/send_btn.jpg"/></a> </td>';
   str += '</tr>';

   str +=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   $('#name').val(userName);
   getStates();
   if(stateId!=null && stateId!="null" && stateId!="")
   getAllConstituenciesInStateByType(2,stateId,"constituency");
   else
   getAllConstituenciesInStateByType(2,1,"constituency");
 }
 function showBusyImgWithId(elmtId)
{		
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = 'block';
}
function hideBusyImgWithId(elmtId)
{
	var spanElmt = document.getElementById(elmtId+"_ImgSpan");
	if(spanElmt)
		spanElmt.style.display = "none";
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
 function createOptions(elmtId,optionsList)
   {	
	if(document.getElementById("selectStatediv").style.display = 'none')
		document.getElementById("selectStatediv").style.display = 'block';
	if(document.getElementById("selectStatePopupdiv")){
	if(document.getElementById("selectStatePopupdiv").style.display = 'none')
		document.getElementById("selectStatePopupdiv").style.display = 'block';
	}
	var elmt = document.getElementById(elmtId);
	
	if( !elmt || optionsList == null)
		return;
	
	for(var i in optionsList)
	{
		var option = document.createElement('option');
		option.value=optionsList[i].candidateId;
		option.text=optionsList[i].description;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

}
function onYouTubePlayerReady(playerId) 
{ 
	ytplayer = document.getElementById("video_overlay"); 
	ytplayer.setVolume(100);
}
function showResults(results,divId)
 {
  var elmt = document.getElementById(divId);

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
		clearFields();
		str += '<font color="green"><b>Message Send Successfully.</b>';
	}
	else if(myResult.resultCode == 1) 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	
	errorDivEle.innerHTML = str;
}
function clearFields()
{  
	document.getElementById('name').value = '';
    document.getElementById('message').value = '';
    document.getElementById('constituencySelect').value = 0;
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
		 else if(jsObj.task == "getNewsToDisplay")
			{
               showTotalNews(myResults);
			}
		 else if(jsObj.task == "getFirstThreePhotoGallaryDetail")
			{
               buildFirstThreePhotoRecords(myResults);
			}
		 else if(jsObj.task == "getPartyPhotoGallaryDetailWithOutGallerySizeZero")
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
		else if(jsObj.task == "videoGalleriesForAParty")
			{
			     buildVideoGallaries(myResults);
		    }
		else if(jsObj.task == "getStates")
			  {    
				buildResults1(myResults,"stateSelect");
			  }
		else if(jsObj.task == "getVideosInGallary")
            {
			   buildAllVideosInGallary(myResults);
			}
		else if(jsObj.task == "getNewsCountByScope")
            {
			   buildNewsCount(myResults);
			}
		else if(jsObj.task == "getNewsByScope")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getNewsBySourceScope")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getNewsByLanguageScope")
            {
			   showTotalNews(myResults);
			}	
			else if(jsObj.task == "setEmailAlertsForUser")
            {
			   showStatusForEmailSubscription(myResults);
			}
		else if(jsObj.task == "getOtherNews")
            {
			   showTotalNews(myResults);
			}
		else if(jsObj.task == "getPartyElectionProfile")
            {
				electionObj = myResults;
			   
			   hideBusyImgWithId("electionLoading");
			   if(clickedButtonType == "parliament")
			   {
			     buildResultForParliamentElection();
			   }
			   else if(clickedButtonType == "assembly")
			   {
			     buildResultForAssemblyElection();
			   }
			   else
			   {
			    buildElectionResultsOfParty(myResults,jsObj.includeAlliances);
			   }
			}	
		else if(jsObj.task =="getPartyManifesto")
			{
				buildPartyManifestos(myResults);
			}
		else if(jsObj.task =="getPartyManifestoDetails")
			{
				buildPartyManifesoGallary(myResults); 
			}
        else if(jsObj.task == "getSelectedStateDetails")
			{ 
			
			   clearOptionsListForSelectElmtId(jsObj.divElmt);
			   
				if(jsObj.divElmt =='statePopUpDiv')
				{
					clearOptionsListForSelectElmtId(jsObj.divElmt);
					buildResults(myResults,jsObj.divElmt)
					getPartyManifestoFile();
				}
				else
				{
					createOptions(jsObj.divElmt,myResults);
					getPartyManifestoBasedOnStateId();

				}
			}			
		
		else if(jsObj.task == "getElectionTypesByStateId"){
			
			document.getElementById("electionTypeDiv").style.display ='block';

			clearOptionsListForSelectElmtId("electionTypeDiv");
			createOptionsForSelectElmtId("electionTypeDiv",myResults);
		  }
		  else if(jsObj.task == "getElectionYears"){
			
			document.getElementById("electionYearDiv").style.display ='block';

			clearOptionsListForSelectElmtId("electionYearDiv");
			createOptionsForSelectElmtId("electionYearDiv",myResults);
		  }
		  else if(jsObj.task =="PartyManifestoBasedOnStateId")
			{
				buildPartyManifestos(myResults); 
			}
		 else if(jsObj.task =="getPartyManifestoFile")
			{
				buildPartyManifesoGallary(myResults); 
			}
		else if(jsObj.task == "getConstituencies")
		 {  
			clearOptionsListForSelectElmtId("constituencySelect");
			showResults(myResults,"constituencySelect");
		 }
		  else if(jsObj.task == "saveMessageToParty") 
			 { 
				showStatus(myResults);
			 }
		else if(jsObj.task == "getSelectedContent")
			{
				showContentResultList = myResults;
				buildContentDetails();
			}
		
		else if(jsObj.task == "getElectionYears" || jsObj.divElmt == "assessElectionYearDiv"){
			//alert(jsObj.divElmt +" = assessElectionYearDiv");
			document.getElementById("assessElectionYearDiv").style.display ='block';

			clearOptionsListForSelectElmtId("assessElectionYearDiv");
			createOptionsForSelectElmtId("assessElectionYearDiv",myResults);
		  }
	   else if(jsObj.task == "getStatesBasedOnParty"){
			//alert("getStatesBasedOnParty");
			document.getElementById("assessStateId").style.display ='block';

			clearOptionsListForSelectElmtId("assessStateId");
			createOptionsForSelectElmtId("assessStateId",myResults);
		  }
	   else if(jsObj.task == "getCandidateDetailsForAsses")
			{
				openAnalyzeConstituencyWindow(myResults,jsObj.selectType);
			}
			else if(jsObj.task == "candidatedetails")
			{
			   clearData("candidateTypeSel",'Select Candidate');
			   buildData("candidateTypeSel",myResults);
			   document.getElementById("candidate_ImgSpan").style.display = 'none';
			}
			else if(jsObj.task == "statedetails")
			{
			   clearData("stateTypeSel",'Select State');
			   buildData("stateTypeSel",myResults);
			   document.getElementById("state_ImgSpan").style.display = 'none';
			}
			else if(jsObj.task == "elecyears")
			{
			   clearData("yearTypeSel",'Select Year');
			   buildData("yearTypeSel",myResults);
			   document.getElementById("year_ImgSpan").style.display = 'none';
			   
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

function showStatusForEmailSubscription(results){

    /*$("#emailAlertDiv").dialog({ stack: false,
							    height: 'auto',
								width: 'auto',
								position:'center',								
								modal: true,
								title:'<font color="Navy">Email Alert</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#emailAlertDiv").dialog();*/
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
	
	cleardescriptionFields();
	document.getElementById("alertMsg").innerHTML = str;
    document.getElementById("emailId").value='';
}
function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
}
function cleardescriptionFields()
  {
	document.getElementById('emailId').value = '';
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
				  partyId :partyId,
                   task:"setEmailAlertsForUser"
	             };
    
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyEmailAlertsForUserAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function photoGallaryPopUp(){
	var str='';
	str+='<div style="margin:5px;font-size:13px;margin-left: 69px;"> Loading Photo Galleries .....<img style="float:right;margin-right: 295px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
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
	var str ="";
str+='<div style="margin:5px;font-size:13px;margin-left: 69px;"> Loading Photo Galleries .....<img style="float:right;margin-right: 295px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
	$("#buildPhotoGallaryDiv").html(str);

    var jsObj =
		{   
		    time : timeST,
			partyId:partyId,
			startRecord:0,
			maxRecord:100,
			task:"getPartyPhotoGallaryDetailWithOutGallerySizeZero"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function buildCandidatePhotoGallary(results)
{
	var str ='';
	str+='<div id="content">';		
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
   str+='<div id="content">';
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
function showPhotosInInitialGallary(results){

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
     str+= '<tr><td><a rel="photo_gallery"  id="photoClickId'+i+''+k+'"  href="'+results[i].filePath[k]+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].filePath[k]+'" class="gallaryImg" height="100px" /></a></td></tr>';
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
     str+='<a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle"\">';
	 
	 if(results[i].fileTitle1.length > 42)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,42)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 100)
		str += results[i].fileDescription1.substring(0,110)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
  // str+='<div class="more"><a href="javascript:{}" onclick="getTotalNews(\'totalNews\');">More</a></div>';
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
	 /*str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';*/
	 str +='<a href="javascript:{}" onClick="shareInFacebook("www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results.contentId+'")" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
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
	lb.innerHTML='<div class="fb-comments" data-href="http://www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results.contentId+'" data-num-posts="5" data-width="500"></div>';
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
	/* str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';*/
	str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}&contentId='+results.contentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
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
 /*function getTotalNews(viewType)
 {  
    var queryType='Public';
   var jsObj =
		{   
		    time : timeST,
			viewtype:viewType,
			partyId:partyId,
			startRecord:0,
			maxRecord:100,
			queryType:queryType,
			task:"getNewsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }*/
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
			    partyId:partyId,
			    startRecord:0,
			    maxRecord:100,
			    queryType:queryType,
			    task:"getNewsToDisplay"
		     };
	 
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }

 function showTotalNews(results)
 { 
     deleteAllElements();
     newsPopUpData = results;
    $("#showAllNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy"><b>News</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showAllNewsDiv").dialog();
    if(results.length>0)
  {
     var str ='';
     str+='<fieldset class="imgFieldset">';
	 str+='   <table>';
	 str+='     <tr>';
     str+='       <td colspan="2"><div id="showScopeWiseNewsCount" style="display:none;"/></td>';
     str+='     </tr>';
	 str+='   </table>';
     str+='  <table style="width:98%">'; 
  for(var i in results)
   { 
	 fileIdArray[i]=results[i].fileId;	    	  
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getVideoDetails('+results[i].contentId+')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
	 var sourcedata ='';
	 var languag = new Array();

	 for(var j in results[i].fileVOList)
	 {
	    count = 0;
	   sourcedata+='<span class="titleStyle"><font color="#FF4500">'+results[i].fileVOList[j].source+'</font></span> - ';
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
	     lang+='<span  class="titleStyle"><font color="#FF4500">'+languag[l]+'</font></span> - ';
	   }
     str+='       <td>'+sourcedata.slice(0, -2)+' | '+lang.slice(0, -2)+' | '+results[i].fileDate+'</td>'; 
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
/* function  buildNewsCount(result)
 {
    if(result[result.length-1].visibility=="False")
	   queryTypeChecked='Public';
    str='';
	str+='   <table align="center">';
	str+='     <tr>';
	if(result[result.length-1].visibility=="True")
	{	 
	 str+='       <td>Sort By : </td>';
	 str+='       <td>';
	 str+='          <select id="candidateVisibility" onchange="getTotalNews(\'totalNews\');"  >';
	 str+='             <option value="Public">Public</option>';
	 str+='             <option value="Private">Private</option>';
	 str+='             <option value="All">All</option>';
	 str+='          </select>';
	 str+='       </td>';	 
   	}
	 str+='       <td>&nbsp;&nbsp;Total News Articels : </td>';
	 str+='       <td><a href="javascript:{}" onclick="getScopeWiseNews(\'\')" ><font color="brown">'+result[result.length-2].fileTypeId+'</font></a></td>';
	 str+='    </tr>';
	 str+='   </table>';
     str+='   <table>';
	 str+='     <tr>';
	 str+='     <td>News Impact: </td>';
	 if(result[3].fileTypeId>0)
     str+='       <td>'+result[3].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+4+')" ><font color="brown">'+result[3].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[3].name+' Level -<font color="brown">'+result[3].fileTypeId+'</font></td>';
	 if(result[4].fileTypeId>0)
	 str+='       <td>'+result[4].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+5+')" ><font color="brown">'+result[4].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[4].name+' Level -<font color="brown">'+result[4].fileTypeId+'</font></td>';
     if(result[5].fileTypeId>0)
	 str+='       <td>'+result[5].name+' Level -<a href="javascript:{}" onclick="getScopeWiseNews('+6+')" ><font color="brown">'+result[5].fileTypeId+'</font></a></td>';
	 else
	 str+='       <td>'+result[5].name+' Level -<font color="brown">'+result[5].fileTypeId+'</font></td>';
	
	var remainingCount = getRemainingCount(parseInt(result[result.length-2].fileTypeId),parseInt(result[3].fileTypeId),parseInt(result[4].fileTypeId),parseInt(result[5].fileTypeId));
	
	if(remainingCount>0)
	 str+='       <td> Others -<a href="javascript:{}" onclick="getOtherNews()" ><font color="brown">'+remainingCount+'</font></a></td>';
	 else
	 str+='       <td> Others -<font color="brown">'+remainingCount+'</font></td>';
	
     str+='     </tr>';
	 str+='   </table>';
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
	
 }*/

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
	 str+='       <td style="font-weight:bold;font-size:12px;color:navy;">&nbsp;&nbsp;&nbsp;TOTAL NEWS ARTICELS : </td>';
	 str+='       <td style="font-weight:bold;font-size:12px;"><a title="Click To Get Total News" href="javascript:{}" onclick="news_Obj.getScopeWiseNews(\'\')" ><font color="brown">'+result[result.length-2].fileTypeId+'</font></a></td>';
	 str+='    </tr>';
	 str+='   </table>';
	 str+='	<hr style="width:98%;">';
     str+='   <table style="padding-bottom:10px;">';
	// str+='     <tr>';
	// str+='     <td style="font-weight:bold;font-size:12px;color:#06ABEA;">NEWS IMPACT LEVELS :</td></tr>';
	 str+='     <tr>';
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
 function deleteAllElements()
 {
   
   for(var i=0 ; i<fileIdArray.length;)
   {
     fileIdArray.pop();
   }
 }
 function getScopeWiseNews(scopeId)
 {
   deleteAllElements();
   timeST = new Date().getTime();
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
		    time : timeST,
			candidateId:candidateId,
			scopeType:scopeId,
			startIndex:0,
			maxResults:100,
			queryType:queryType,
			task:"getNewsByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
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
 function getRemainingCount(total,constituency,mandal,village)
 {
    var remaining = total-constituency-mandal-village;
   return remaining;
 }
 function getScopeWiseNewsCount()
 { 
   timeST = new Date().getTime();
   var jsObj =
		{   
		    time : timeST,
			candidateId:partyId,
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
			partyId:partyId,
			task:"getFirstThreePhotoGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
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
	   str+='<li><img class="imageClass" alt="'+results[0].title+'" title="'+results[0].gallaryDescription+'" src="'+results[0].path+'"  onclick="getCandidatesPhotosInAGallary(\''+results[0].gallaryId+'\')"/><br />';
	  str+=''+results[0].title+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img class="imageClass" alt="'+results[1].title+'" title="'+results[1].gallaryDescription+'" src="'+results[1].path+'"  onclick="getCandidatesPhotosInAGallary(\''+results[1].gallaryId+'\')"/><br />';
	  str+=''+results[1].title+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li><img class="imageClass" alt="'+results[2].title+'" title="'+results[2].gallaryDescription+'" src="'+results[2].path+'"  onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].title+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li><img class="imageClass" alt="'+results[i].title+'" title="'+results[i].gallaryDescription+'" src="'+results[i].path+'"  onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
	    str+=''+results[i].title+'</li>';
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

function partyInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';
	
    str+='<div class="ptd-sec">';
	str+='<img height="175" width="180" onerror="setDefaultImage(this)" src="images/party_flags/${partyVO.partyFlag}"><span class="tc-tf pa-fi">';
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
								title:'<font color="Navy"><b>${partyVO.partyLongName} Party</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showProfile").dialog();
   
 
   var str ='';
    str+='<fieldset class="imgFieldset">';
    str+='  <table><tr><td>';
    str+='  <s:if test="descriptions != null">'; 
    //str+='  <div style="font-weight: bold; font-size: 14px;">About ${partyVO.partyLongName}</div>';
    str+=' <br><s:iterator value="descriptions">';
	str+=' <div style="margin-bottom: 21px; font-weight: normal; font-size: 11px; font-family: tahoma;text-align:justify;">';
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
    var str='';
    var x=1;
   
   str+='<s:if test="descriptions != null"> ';
   str+='<h1 class="inc-title">About ${partyVO.partyLongName}</h1>';
   
   str+='<s:iterator value="descriptions">';
 if (x<=2)
   {
   str+='  <p style="font-size: 13px;"><s:property /></p><br />';
   x++;
   }
   str+='  </s:iterator>';
   str+='<div class="read-more"><a href="javascript:{}" onclick="getTotalProfile()" style="color: LightSkyBlue;">';
   str+='Read More >></a></div>';
   str+='</s:if>';
   
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
	var politicalChangesWindow = window.open(str+"","politicalChangesWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
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
								position:[130,120],								
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

		//showBusyImgWithId("videosLoadingImg");
   var jsObj = {
	       	   time : timeST,
			   partyId:partyId,
			   startRecord:0,
			   maxRecord:100,
			   task:"videoGalleriesForAParty"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
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

function buildVideoGallaries(results)
{
    var str ='';
	str+='<table width="100%">';
    //str += '<tr>';
	for(var i=0;i<results.length;i++)
	{
		if(i%4 == 0)
			str += '<tr height="185px;">';
		str += '<td valign="top" width="25%"><table width="100%" style="margin-bottom: 15px;">';
		str += '<tr><td style="padding-left:4px;">';

		str+='<img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" height="120px;" width="110px;" style="cursor: pointer; border-radius: 5px 5px 5px 5px; left: 13px; padding: 7px; border: 1px solid #ccc; background: none repeat scroll 0pt 0pt #d3d3d3;"  onClick="getVideosInAGallary('+results[i].gallaryId+')"/></td></tr>';
		str += '<tr><td><div style="color:#FF0084;font-size: 13px; font-family: verdana,arial;"><b>'+results[i].gallaryDescription+'</b></td></tr>';
		str+= '<tr><td><div style="font-size: 13px; font-family: verdana,arial;""><b>Videos: ('+results[i].sizeOfGallary+')</b></div></td></tr></table></td>';
		str+='</div>';
			
			if((i+1)% 4 == 0)
			str += '</tr>';
		 }
		// str+='</tr>';
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
		
		str+='<table style="width:100%; margin-top: 8px;">';
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:230px;">';
			
			str += '<td valign="top" width="5%;">';
			str += '<table style="font-size:13px;font-family:verdana,arial;">';
			str += '<tr>';
			str += '<td style="border:2px solid #CCCCCC;padding:5px;width:100px;">';
			str += '<a href=javascript:{} onclick="getVideoDetails('+results[i].contentId+')">';
		
			//str+='<a target="blank"  href="http://www.youtube.com/v/'+results[i].pathOfFile+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
			str+='<img src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="160px;" height="160px;" title="'+results[i].description+'"/></td></a>';
			str+='</tr>';
			str+='<tr>';
			/* str+='<td>'+results[i].description+'';*/
			str+='<td>'+results[i].title+'';
			str+='</td>';
			str+='</table>';
			
			if(j % no_of_imagesPerRow == 0)
             str+= '</tr>';
						
		}
		str+='</table>';
		str+='</div>';
		videosDivElmt.innerHTML =str;
			
		
		
}
function getNewsBySource(source)
  {
  scopeId='';
  deleteAllElements();
   timeST = new Date().getTime();
   var queryType='Public';
   var jsObj =
		{   
		    time : timeST,
			partyId:partyId,
			scopeType:scopeId,
			startIndex:0,
			maxResults:100,
			queryType:queryType,
			source : source,
			task:"getNewsBySourceScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
  }
  
function getNewsByLanguage(language)
  {
   scopeId='';
  deleteAllElements();
   timeST = new Date().getTime();
   var queryType='Public';
   var jsObj =
		{   
		    time : timeST,
			partyId:partyId,
			scopeType:scopeId,
			language : language,
			startIndex:0,
			maxResults:100,
			queryType:queryType,
			task:"getNewsByLanguageScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
  }  
 
function getResult()
  {
  var partyId =  ${partyId};
  var jsObj =
	{   
		partyShortName : '${partyVO.partyShortName}',
		partyId : partyId,
		electionId  : 1,
		countryId	: 1,
		stateId		: 1,
		districtId : 0,
		constituencyId : 0,
		hasAlliances : false,
		task:"getParliamentElectionResultsByState"
	};

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getParliamentElectionResultByStateAction.action?"+rparam;					
callAjax(jsObj,url);
  }

function getElectionProfile(includeAlliances,includeBielections)
{
	showBusyImgWithId("electionLoading");
	incAlliances = includeAlliances;
	var jsObj = {

		partyId :'${partyVO.partyId}',
		includeAlliances:includeAlliances,
		includeBielections:includeBielections,
		task:"getPartyElectionProfile"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyElectionProfileAction.action?"+rparam;					
	callAjax(jsObj,url);
}
function buildElectionResultsOfParty(results,includeAlliances)
 {
	if(results != null)
	 {
		buildResultForParliamentElection(results.Parliament,includeAlliances);
		//buildResultForAssemblyElection(results.Assembly);
	 }	
 }
 function buildResultForParliamentElection(results,includeAlliances)
 {
	$("#parliamentProfileId").addClass("dashBoardtabsDivSelected");
	 $("#assemblyProfileId").removeClass("dashBoardtabsDivSelected");
	document.getElementById("parliamentElecProfileDiv").style.display='block';
	 YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
	  {
		 
		var partyId= oRecord.getData("partyId");
		var partyShortName= oRecord.getData("partyShortName");
		var electionType = oRecord.getData("electionType");
		var electionTypeId = oRecord.getData("electionTypeId");
		var electionYear = oRecord.getData("electionYear");
		var electionId = oRecord.getData("electionId");
		elLiner.innerHTML ="<a title='Click here to view complete election results' href='electionDetailsReportAction.action?electionId="+electionId+"&stateID=1&stateName=AndhraPradesh&electionType="+electionType+"&electionTypeId="+electionTypeId+"&year="+electionYear+"'>View Details</a>";

	  };
	  var electionColumnDefs = [
		{key:"electionYear" , label:"Year" , sortable:true},
		{key:"seatsParticipated" , label:"PC" , sortable:true},
		{key:"seatsWin" , label : "Seats Won" , sortable:true},
		{key:"percentageOfVotes",label:"VP (%)",sortable:true},
		{key:"participatedPercentage" , label:"PCV(%)",sortable:true},

		{key:"view details",label:"Complete Details",formatter:YAHOO.widget.DataTable.viewDetails}

		

	];
	var electionDataSource = new YAHOO.util.DataSource(electionObj.Parliament);
		electionDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		electionDataSource.responseSchema = {
                fields: [{key:"electionYear"},{key: "seatsParticipated",parser:"number"},
						 {key:"seatsWin"},
						 {key:"percentageOfVotes"},
						 {key:"percentageOfVotes"},
						 {key:"participatedPercentage"},
						 {key:"partyId"},
						 {key:"partyShortName"},
						 {key:"electionType"},
					{key:"electionTypeId"},
					{key:"electionId"}
						]
		};
		
		 var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [10,20,30], 
						pageLinks: 10
						})
						
					};
				partywiseResults = new YAHOO.widget.DataTable("parliamentElecProfileDiv", electionColumnDefs, electionDataSource,myConfigs);
 }
 function buildResultForAssemblyElection(results)
 {
	 
	 $("#parliamentProfileId").removeClass("dashBoardtabsDivSelected");
	$("#assemblyProfileId").addClass("dashBoardtabsDivSelected");

	YAHOO.widget.DataTable.viewDetails = function(elLiner, oRecord, oColumn, oData) 
	  {
		 
		var partyId= oRecord.getData("partyId");
		var partyShortName= oRecord.getData("partyShortName");
		var electionType = oRecord.getData("electionType");
		var electionTypeId = oRecord.getData("electionTypeId");
		var stateName = oRecord.getData("stateName");
		var stateId = oRecord.getData("stateId");
		var electionYear = oRecord.getData("electionYear");
		var electionId = oRecord.getData("electionId");
		
       //elLiner.innerHTML ="<a href='partyResultsAction.action?selectedPartyShortName="+partyShortName+"&selectedPartyId="+partyId+"&selectedElectionTypeName="+electionType+"&selectedLocationName="+stateName+"&partySelectName="+partyId+"&electionType="+electionTypeId+"&reportLevel=State&stateSelectName="+stateId+"&__checkbox_alliances=true'>View Details</a>";
		elLiner.innerHTML ="<a title='Click here to view complete election results' style='text-decoration:none;' href='electionDetailsReportAction.action?electionId="+electionId+"&stateID="+stateId+"&stateName="+stateName+"&electionType="+electionType+"&electionTypeId="+electionTypeId+"&year="+electionYear+"'>View Details</a>";
	 };
	YAHOO.widget.DataTable.stateLink = function(elLiner, oRecord, oColumn, oData) 
	  {
		var stateName = oRecord.getData("stateName");
		var stateId = oRecord.getData("stateId");
		elLiner.innerHTML ="<a title='Click here to view "+stateName+" details' href='statePageAction.action?stateId="+stateId+"'>"+stateName+"</a>";
	  };

	  var electionColumnDefs = [
		{key:"electionYear" , label:"Year" , sortable:true},
		{key:"stateName" , label:"State",sortable:true,formatter:YAHOO.widget.DataTable.stateLink},
		{key:"seatsParticipated" , label:"PC" , sortable:true},
		{key:"seatsWin" , label : "Seats Won" , sortable:true},
		{key:"percentageOfVotes",label:"VP (%)",sortable:true},
		{key:"participatedPercentage" , label:"PCV(%)",sortable:true},
		{key:"view details",label:"Complete Details",formatter:YAHOO.widget.DataTable.viewDetails}
	];
	var electionDataSource = new YAHOO.util.DataSource(electionObj.Assembly);
		electionDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		electionDataSource.responseSchema = {
                fields: [{key:"electionYear"},{key:"stateName"},
					{key: "seatsParticipated",parser:"number"},
					{key:"seatsWin"},
					{key:"percentageOfVotes"},
					{key:"percentageOfVotes"},
					{key:"participatedPercentage"},
					{key:"stateId"},{key:"partyId"},
						 {key:"partyShortName"},
						 {key:"electionType"},
					{key:"electionTypeId"},
					{key:"electionId"}
					]
		};
		
		 var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [10,20,30], 
						pageLinks: 10
						})
						
					};

				partywiseResults = new YAHOO.widget.DataTable("parliamentElecProfileDiv", electionColumnDefs, electionDataSource,myConfigs);
 }
function getPartyManifesto(partyId)
 {
	 $("#stateDiv").css("display","none");
	var jsObj ={
		partyId:partyId,
		task:"getPartyManifesto"

	   };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
	  
function builPartyDiv()
{     
 var partyManifestoDivElmt = document.getElementById("partyManifestoDiv");
	var str ='';
	str+='<h1 class="pr-title" style="margin-top:15px;"> ${partyVO.partyLongName} Party Manifestos <span class="or-down-arrow">';
   str += '  <img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>';
	str+= '<fieldset class="imgFieldset">';
	str+='<s:iterator value="partyVO.electionTypes" status="stat">';
	str+='<input style="margin:5px;" type="radio" name="elecType"><s:property value="partyVO.electionTypes[#stat.index]" />';
	str+='</s:iterator>';
	//str+='Select State';
	str+='<div id="selectStatediv" style="display: none;margin-top:0px; margin-left: 100px;"><select id="stateDiv" name="stateDiv" onchange="getPartyManifestoBasedOnStateId();" class="selectWidth" style="width:160px;"></select></div>';
	str+='<div id="manifestoFilesDiv"></div>';
	
}
function buildPartyManifestos(results)
{
	 var manifestoFilesDivElmt = document.getElementById("manifestoFilesDiv");
	 var str ='';
	// document.getElementById("manifestoGallaryPopupDiv").innerHTML = str;
	if(results!=null && results.length>0)
			{
		str+='<div id="content">';
		str+='<table>'
		str +='<tr>';
	 for(var i=0;i<2;i++)
		 {
			str += '<td style="padding-left:15px">';
			if(results[i]!=null)
			{
				str+='<table>';
			if(results[i].title=='Assembly')
			{
				document.getElementById("AssemblyId").checked ='true';
			 /*str+='<tr><td>'+results[i].description+'   '+results[i].title+'    '+results[i].fileDate +'  Manifesto Of    '+results[i].gallaryName;*/
			 str +='<tr><td>'+results[i].fileDate +' Manifesto</td></tr>';
			 }
			else
			 {
			/* str+='<tr><td>'+results[i].title+'    '+results[i].fileDate +'  Manifesto Of    '+results[i].gallaryName;*/
			str +='<tr><td>'+results[i].fileDate +'  Manifesto</td></tr>';
			 }			
			str +='</td></tr><tr><td>';

			if(results[i].title=='Assembly')
			str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}" title="Click here to view '+results[i].description+' '+results[i].title+' '+results[i].fileDate +'  Manifesto Of '+results[i].gallaryName+'"/>';
			else
				str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}" title="Click here to view '+results[i].title+' '+results[i].fileDate +' Manifesto Of '+results[i].gallaryName+'"/>';
			str +='</td></tr></table>';
		   }
		   
		   str+='</td>';
		 }
		 str += '</tr></table>';
		 str+='</div>';
		 

		str+='<div class="more" style="margin-bottom: 35px;">';
		str+='<a href="javascript:{}" onclick="PartyManifestoPopup()" title="Click here to view more Manifestos">More</a></div>';
		str+='</fieldset>';
		str+='<div id="buildManifestoGallaryDiv"><div id="selectionDiv"></div>';
		str+='<div id="manifestoGallaryPopupDiv"></div></div>';
		manifestoFilesDivElmt.innerHTML = str;
	  }	
	if(results.length==0 && document.getElementById("selectStatediv"))
		selectedState('stateDiv');
		
}
function getPartyManifestoFile(){

	var stateElmt = document.getElementById("statePopUpDiv");
	var electionTypeId =0;
	var electionId =0;
	if(stateElmt.value !='')
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
	var electionTypeIdElmt = document.getElementById("electionTypeDiv");
    
	if(electionTypeIdElmt.value !=''){
		electionTypeId = electionTypeIdElmt.options[electionTypeIdElmt.selectedIndex].value;
	}
   var electionYearDivElmt = document.getElementById("electionYearDiv");
   
   if(electionYearDivElmt.value !=''){
	electionId = electionYearDivElmt.options[electionYearDivElmt.selectedIndex].value;
	}
	
	var jsObj = {
			partyId : ${partyVO.partyId},
			stateId : stateId,
			electionId : electionId,
			electionTypeId : electionTypeId,		
		    task : "getPartyManifestoFile"
	  };
		var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPartyRelatedManifestoFileAction.action?"+rparam;
	callAjax(jsObj ,url);
}
function getElectionYearsBasedOnElecTypePartyIdAndStateId(){

	var stateElmt = document.getElementById("statePopUpDiv");
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
	var electionTypeIdElmt = document.getElementById("electionTypeDiv");
	var electionTypeId = electionTypeIdElmt.options[electionTypeIdElmt.selectedIndex].value;
	 
	  var jsObj = {
		stateId : stateId,
        partyId : ${partyVO.partyId},
		electionTypeId : electionTypeId,
		task :"getElectionYears"

	  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElecTypePartyAndStateAction.action?"+rparam;						
	callAjax(jsObj,url);

}

function getElectionTypesBasedOnStateId(){
	
	var stateElmt = document.getElementById("statePopUpDiv");
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		var jsObj =
		   {   
			stateId : stateId,
			partyId : ${partyVO.partyId},
			task:"getElectionTypesByStateId"
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionTypesBasedOnStateAction.action?"+rparam;						
	callAjax(jsObj,url);

}
function getPartyManifestoBasedOnStateId(){

	var stateElmt = document.getElementById("stateDiv");
	var stateId = stateElmt.options[stateElmt.selectedIndex].value;
		var jsObj =
		   {   
			stateId : stateId,
			partyId : ${partyVO.partyId},
			task:"PartyManifestoBasedOnStateId"
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyManifestoBasedOnStateAction.action?"+rparam;						
	callAjax(jsObj,url);

}
function PartyManifestoPopup(){

 if(document.getElementById('buildManifestoGallaryDiv') == null)
	   return;
   $("#buildManifestoGallaryDiv").dialog({ stack: false,
						height: 570,
						width: 720,
						position:[130,130],								
						modal: true,
						title:'<font color="Navy">${partyVO.partyLongName} Manifesto</font>',
						overlay: { opacity: 0.5, background: 'black'}
						});
	$("#buildManifestoGallaryDiv").dialog();
	buildSelectionDiv();
	showManifestoGallary();
	
	
}
function buildSelectionDiv(){
	
	var selectionDivElmt = document.getElementById("selectionDiv");
	var str ='';
	
		str +='<table style="width:100%;">';
		str +='<tr><td style="width: 18%; font-size: 14px; font-family: Trebuchet MS,Arial,Helvetica,sans-serif; margin-right: 0px; margin-left: 0px; padding-left: 34px;">';
		str += '<input type="radio" name = "manifestoByScope" id="manifestoByScope" onclick="showManifestoGallary();getCountry();" checked="true"> Country</td><td style="width:11%;font-size: 14px;font-family:Trebuchet MS,Arial,Helvetica,sans-serif;">';
		str += '<input type="radio" name = "manifestoByScope" id="manifestoByScope" onclick="selectedState(\'statePopUpDiv\')" style="padding-right: 28px; width: 22px;"> State</td>';
		str +='<td style="font-size: 14px; font-family: Trebuchet MS,Arial,Helvetica,sans-serif; width: 65px; height: 35px; margin-right: 13px; padding-top: 1px;"><div id="selectStatePopupdiv" style="display:none"><select id="statePopUpDiv" onchange="getPartyManifestoFile();getElectionTypesBasedOnStateId();" class="selectWidth"/></div></td>';
		str+='<td style="font-size: 14px;font-family:Trebuchet MS,Arial,Helvetica,sans-serif;"><select id="electionTypeDiv" style="display:none" onchange="getPartyManifestoFile();getElectionYearsBasedOnElecTypePartyIdAndStateId()" class="selectWidth"></select>';
		str+='</td>';
		str+='<td style="font-size: 14px;font-family:Trebuchet MS,Arial,Helvetica,sans-serif;"><select id="electionYearDiv" style="display:none" onchange="getPartyManifestoFile()" class="selectWidth"></select>';
		str+='</tr></td>';
		str +='</table>';
selectionDivElmt.innerHTML = str;

	}
function showManifestoGallary()
{
  var jsObj =
   {   
	time : timeST,
	partyId:partyId,
	startRecord:0,
	maxRecord:100,
	task:"getPartyManifestoDetails"
  };

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "partyPhotoGallaryAction.action?"+rparam;						
callAjax(jsObj,url);
}

function getContentDetails(contentId)
{
	document.getElementById("contentAjaxCallImg").style.display="block";
	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Party Page',
			requestPageId : '${partyId}',
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
	document.getElementById("contentAjaxCallImg").style.display="none";
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
	
	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${partyVO.partyShortName} Party - '+result.contentType;

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
		str += '<div class="popupcontainer" id="nextPartImage" style="width:500px;">';
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+pathStr+'" frameborder="0" allowfullscreen="true"></iframe></div>';
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
	            str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[0].fileSourceLanguageId+','+selectedContentFile.fileVOList[0].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[0].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[0].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[0].fileVOList[j].orderName+'</a></td>';
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
	
	str += '<div id="fbc" style="overflow:auto;max-height:400px;"></div>';
	
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
		str += '<div class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;">Other '+galType+' gallaries Of ${partyVO.partyShortName} Party </div><div class="main-bbg"/></div>';
		
		str += '<div class="popupcontainer" style="overflow:auto;width:880px;max-width:850px;">';
		str += '<Table>';
		
		for(var i=0;i<result.otherGalleries.length;i++)
		{
			if(i%5 == 0)
				str += '<tr>';
			
			str += '<td width="20%" valign="top">';

			str += '<table>';
			str += '<tr><td><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery"><font color="red">'+result.otherGalleries[i].gallaryName+'</font></a></td></tr>';
			str += '<tr><td><a href="javascript:{}" onClick="getContentDetails('+result.otherGalleries[i].filesList[0].fileId+')" title="Click here to View '+result.otherGalleries[i].gallaryName+''+galType+' Gallery">';
			
			if(result.contentType == 'Photo Gallary' || result.contentType == 'News Gallary')
				str += '<img width="120px" height="90px" alt="'+result.otherGalleries[i].gallaryName+'" src="'+result.otherGalleries[i].filesList[0].path+'"></img>';
				
			else if(result.contentType == 'Video Gallary')
				str += '<img src="http://img.youtube.com/vi/'+result.otherGalleries[i].filesList[0].path+'/1.jpg"></img>';
			
			str += '</a></td></tr>';
			str += '<tr><td>Gallery Size : ('+result.otherGalleries[i].orderNo+')</td></tr>';
			str += '<tr><td>'+result.otherGalleries[i].description+'</td></tr>';
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
	/*str += '<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+preContentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this in Facebook"></img></a>';*/
	str +='<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+preContentId+'\')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>';
	str += '</span>';
	
	document.getElementById("showContentHeaderDiv").innerHTML=str;
	
	var lb = document.getElementById("fbc");
	lb.innerHTML='<div class="fb-comments" data-href="http://www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+preContentId+'" data-num-posts="2" data-width="420"></div>';
	FB.XFBML.parse(lb);
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

function buildPartyManifesoGallary(results)
{
	var str='';
	document.getElementById("manifestoGallaryPopupDiv").innerHTML = str;
	str+='<div id="content">';		
	str += '<fieldset class="imgFieldset">';
	str +='<table  width="100%" style="margin-top:10px;">';
	
if(results.length <=0)
{
	str+='<b>&nbsp;No Manifesto Found </b>';
}
	for(var i in results)
	{
		no_of_imagesPerRow = 3; 
		j = i;

		if(j++ % no_of_imagesPerRow == 0)
			str += '<tr style="height:220px;">';
		
		str += '<td width="33%" class="imageStyle" style="display:table-cell;">';
		str += '<table class="tableStyle" style="width:100%;display:table;">';
	
		str +='<tr><td>';
		if(results[i].title=='Assembly'){
		/*str += results[i].description+'   '+results[i].title+' '+results[i].fileDate+'  Manifesto';*/
		str +=''+results[i].fileDate+'  Manifesto';
		 }
		else
		 {
		 str += results[i].title+'     '+results[i].fileDate;
		 }			
		str +='</td></tr><tr><td>';

		if(results[i].title=='Assembly')
		 str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}" title="Click here to view '+results[i].description+' '+results[i].title+' '+results[i].fileDate +'  Manifesto Of '+results[i].gallaryName+'"/>';
		else
		 str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}" title="Click here to view '+results[i].title+' '+results[i].fileDate +' Manifesto Of '+results[i].gallaryName+'"/>';

		str +='</td></tr><tr><td>';
		str +=''+results[i].problem+'';
		str +='</td></tr>';
		
		str += '</table>';
		str += '</td>';
		
		if(j % no_of_imagesPerRow == 0)
			str+= '</tr>';
	
	}
	str += ' </table>';
	str += ' </fieldset>';
	str+='</div>';
	document.getElementById("manifestoGallaryPopupDiv").innerHTML = str;
}   
function relatedState()
{
/*
  var elmt = document.getElementById('selectStatediv');
  str +='<select id="stateDiv" name="stateDiv" class="selectWidth"/>';
  elmt.innerHTML = str;*/
  //selectedState();
}
function buildResults1(results,divId)
 {
  var elmt = document.getElementById(divId);
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
 
function buildResults(results,divId){
	
	if(document.getElementById("selectStatePopupdiv")){
	if(document.getElementById("selectStatePopupdiv").style.display = 'none')
		document.getElementById("selectStatePopupdiv").style.display = 'block';
	}
  var elmt = document.getElementById(divId);
  var option1;
        
			    option1 = document.createElement('option');
				option1.value= 0;
				option1.text= "Select State";
		    
				try
				{
					elmt.add(option1,null); // standards compliant
				}
				catch(ex)
				{
					elmt.add(option1); // IE only
				}
				
				for(var i in results)
			   {
				
				var option = document.createElement('option');
				  option.value=results[i].candidateId;
				  option.text=results[i].description;
				  
				
				try
				{
					elmt.add(option,null); // standards compliant
				}
				catch(ex)
				{
					elmt.add(option); // IE only
				}
				
			  }
		 
}
function selectedState(selectStateDiv)
{
	$("#stateDiv").css("display","block");
       var jsObj =
		   {   
		    time : timeST,
			partyId:partyId,
			task:"getSelectedStateDetails",
			divElmt : selectStateDiv
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);

}

function selectedStateForAssembly(selectStateDiv)
{
	
$("#stateDiv").css("display","block");
       var jsObj =
		   {   
		    time : timeST,
			partyId:partyId,
			task:"getSelectedStateDetails",
			divElmt : selectStateDiv
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getCountry()
{
	document.getElementById("selectStatediv").style.display = 'none';
	document.getElementById("selectStatePopupdiv").style.display = 'none';

}

function displayPartyMessages(results)
{ 
	
	if(results == null || results.length == 0)
		return;

    var resultDiv = document.getElementById('commentBoxDiv');
    var str = '';

    str += '<Div class="layoutHeadersClass" style=" margin-bottom:0px;"> Messages From Followers </DIv>';
      str += '<fieldset id="fiedsetDiv">';
    for(var i=0;i<results.length;i++)
    {
	   
       str += '<div class="annDivId">'
	   str += '<Table>';
	   str += '<tr class="annHeaderFont"><th>'+results[i].userName+'&nbsp;&nbsp;</th><th>'+results[i].time+'&nbsp;&nbsp;</th><th>Location : <td class=""><font color="black"><a href="constituencyPageAction.action?constituencyId='+results[i].consituencyId+' ">'+results[i].constituency+'</a></font></td></th><tr>';
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

var message_Obj = {
		
		
		problemStatus:[],
		startIndex:0,
		problemsCount:5,
		
		initialize:function(){
			
			this. getPartyMessages();

		},
		getPartyMessages:function ()
	    {
	   	 var jsObj =
	   		{ 
	   			partyId:partyId,
	   			task:"getPartyMessages"
	   		};
	   	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	   	 var url = "partyMessagesAction.action?"+rparam;
	   		message_paginator.paginator({
	   		   startIndex:this.startIndex,
			   resultsCount:this.problemsCount,
			   jsObj:jsObj,
			   ajaxCallURL:url,
			   paginatorElmt:"message_paginator_class",
			   callBackFunction:function(){
	   		       displayPartyMessages(results);
			   }
		     });	
		   
	   	message_paginator.initialize();							
	   	 
	    },
	   
};

<s:if test="contentId != null">
showSelectedContentAndRelatedGalleries();
</s:if>
function getCandidateDetailsForAsses(selectType)
{
    var candEle = document.getElementById("candidateTypeSel");
	var candidateId = candEle.options[candEle.selectedIndex].value;
	var elecEle = document.getElementById("yearTypeSel");
	var electionId =  elecEle.options[elecEle.selectedIndex].value;
	
	if(candidateId == 0)
	{
		document.getElementById("errorDivEle").innerHTML = 'Please Select Candidate.';
		return;
	}
	else
	{
		document.getElementById("errorDivEle").innerHTML = '';
	}

	var jsObj =
		{   
			selectType  : selectType,
		    candidateId : candidateId,
			electionId : electionId,
			task:"getCandidateDetailsForAsses"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidateDetailsForAssesAction.action?"+rparam;			callAjax(jsObj,url); 
	
}
function showHideState()
{
   var elecTypeEle =  document.getElementById("elecTypeSel");
   var elecType = elecTypeEle.options[elecTypeEle.selectedIndex].value;
    if(elecType == 1)
	 {
	  document.getElementById("stateBody").style.display = "none";	  
	  getYears(1);
	 }
	else if(elecType == 2)
	 {
	  document.getElementById("stateBody").style.display = "block";
	  clearData("stateTypeSel",'Select State');
	  clearData("yearTypeSel",'Select Year');
	  clearData("candidateTypeSel",'Select Candidate');
	  getStatesForAssembly();
	 }
	 else if(elecType == 0)
	 {
	  clearData("stateTypeSel",'Select State');
	  clearData("yearTypeSel",'Select Year');
	  clearData("candidateTypeSel",'Select Candidate');
	 }
} 
function getYears(electionType)
{
  clearData("yearTypeSel",'Select Year');
  clearData("candidateTypeSel",'Select Candidate');
  var stateId = 0;
  if(electionType == 2)
  {
   var stateEle = document.getElementById("stateTypeSel");
   stateId = stateEle.options[stateEle.selectedIndex].value;
   if(stateId == 0)
    return;
	document.getElementById("year_ImgSpan").style.display = 'block';
  }
  var jsObj =
		{   
			partyId  : partyId,
		    electionType : electionType,
			stateId : stateId,
			task:"elecyears"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyElectionDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
	
}
function getStatesForAssembly()
{
   document.getElementById("state_ImgSpan").style.display = 'block';
  var jsObj =
		{   
			partyId  : partyId,
			task:"statedetails"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyElectionDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}
function getCandidates()
{
   clearData("candidateTypeSel",'Select Candidate');
   var elecEle = document.getElementById("yearTypeSel");
   var electionId = elecEle.options[elecEle.selectedIndex].value;
   
   if(electionId == 0)
    return;
	 document.getElementById("candidate_ImgSpan").style.display = 'block';
  var jsObj =
		{   
			partyId  : partyId,
			electionId : electionId,
			task:"candidatedetails"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getPartyElectionDetailsAction.action?"+rparam;
	callAjax(jsObj,url); 
}
function buildData(eleId,results)
{
   var elem = document.getElementById(eleId);
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				  elem.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				  elem.add(opElmt); // IE only
				}
		}
}

function clearData(selId,val)
{
   var elmt = document.getElementById(selId);
  if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}
        var opElmt=document.createElement('option');
			opElmt.value = 0;
			opElmt.text = val;
		
			try
				{
				  elmt.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				  elmt.add(opElmt); // IE only
				}	
}
function openAnalyzeConstituencyWindow(result,type)
{
	var candidateId = result.candidateId;
	var constituencyId = result.constituencyId;
	var constituencyName = result.constituencyName;
	var userId = result.districtId;
	var userType = result.districtName;
	var parliamentConstiId = result.partyId;
	var parliamentConstiName = result.partyName;
	var constiElecId = result.state;
	
	var taskType = type;


	if(userId == null && taskType == 'analyze')
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
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">Only Registered Users Can Assess Party Leaders.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" onclick="openDialogForLoginWindow()" href="javascript:{}">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById("logInDiv").innerHTML = str;

		//alert("Please Login To Post Comment");
		return;
	}
	var parlcheck = false;
	var elecTypeEle =  document.getElementById("elecTypeSel");
    var elecType = elecTypeEle.options[elecTypeEle.selectedIndex].value;
    if(elecType == 1)
	 {
	  parlcheck = true;
	 }
	else if(elecType == 2)
	 {
	  parlcheck = false;
	 }
	if(taskType == 'viewResults')
	{
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId+"&parlchecked="+parlcheck+"&constiElecId="+constiElecId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
		browser1.focus();
	}

	if(userType == "FREE_USER" && taskType == 'analyze')
	{
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId+"&parlchecked="+parlcheck+"&constiElecId="+constiElecId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
		browser1.focus();
	}
	
	if(userType != "FREE_USER" && taskType == 'analyze')
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
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">Comment For Free User Only.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById("logInDiv").innerHTML = str;

		//alert("Comment For Free User Only");
	}

}
function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(type != 'video')
	    var str='<div class="popupcontainer" id="nextPartImage" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:780px;max-length:800px;" src="'+path+'"></img></div>';
	  else
	   var str='<div class="popupcontainer" id="nextPartImage" style="width:500px;"><iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe></div>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=0;j<selectedContentFile.fileVOList[i].fileVOList.length;j++)
	     {
		   if(selectedContentFile.fileVOList[i].fileVOList[j].orderNo != orderNo)
		    {
			  if(type != 'video')
	             str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[i].fileSourceLanguageId+','+selectedContentFile.fileVOList[i].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[i].fileVOList[j].path+'\',\'other\')"><img width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" src="'+selectedContentFile.fileVOList[i].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[i].fileVOList[j].orderName+'</a></td>';
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
	    var str='<div class="popupcontainer" id="nextPartImage" style="text-align:center;"><img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" style="max-width:780px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" ></img></div>';
	  else
	    var str='<div class="popupcontainer" id="nextPartImage" style="width:500px;"><iframe width="500" height="396" src="http://www.youtube.com/embed/'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" frameborder="0" allowfullscreen="true"></iframe></div>';
	  document.getElementById("nextPartImage").innerHTML = str;
	
	   str = '<center><table><tr>';

	    for(var j=1;j<selectedContentFile.fileVOList[m].fileVOList.length;j++)
	     {
		    if(type != 'video')
	         str += '<td><a style="color:#FF4500;margin:5px;" href="javascript:{}" onclick="showNextNewsPart('+selectedContentFile.fileVOList[m].fileSourceLanguageId+','+selectedContentFile.fileVOList[m].fileVOList[j].orderNo+',\''+selectedContentFile.fileVOList[m].fileVOList[j].path+'\',\'other\')"><img  width="65" height="60" alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'"  src="'+selectedContentFile.fileVOList[m].fileVOList[j].path+'" /><br />&nbsp;&nbsp;'+selectedContentFile.fileVOList[m].fileVOList[j].orderName+'</a></td>';
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
displayProfile();
partyInfo();
getTotalNews('getFirstFourNewsRecordsToDisplay');
getFirstThreePhotoRecords();
getPartyManifesto(partyId);
showAssemblyData();
message_Obj.initialize();
getElectionProfile(false,false);
buildElectionProfile();
//showAssessMentDiv();

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
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;"> Please Login to subscribe. <a href="loginInputAction.action" style="color:#80D1F1;">Click here to Login </a></div></div>';	
		document.getElementById("logInDiv").innerHTML = str;
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

$(document).ready(function(){
$('#developmentsParty').click(function(){
$("#developmentspopForParty").dialog({
									stack: false,
								height: 230,
								width: 800,
								modal: true,
								position: [170,150],
								title:'Developments',
								overlay: { opacity: 0.5, background: 'black'}
									
	
	});

	var elemt = document.getElementById("developmentspopForParty_window_inner");
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

</body>
</html>