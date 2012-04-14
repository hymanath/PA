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
<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>
<style type="text/css">#videogallery a#videolb{display:none}</style>
<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script> 
<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>
<script type="text/javascript" src="js/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/customPaginator/messagePaginator.js"></script>
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">



<style type="text/css">
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
.ui-widget-header {
	background:url("");
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
              <li><a onclick="videoGallaryPopUp();" href="javascript:{}">Videos</a><span></span></li>
              <li><a onclick="photoGallaryPopUp();" href="javascript:{}">Photo Gallery</a><span></span></li>
              <li><a onclick="" href="javascript:{}">Developments</a><span></span></li>
            </ul>
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

	   <!--EMAIL ALERT SECTION START-->
          
          <div class="ea-fc-sec">
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images/icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            <div class="ea-fc-cont-sec" style="font-size:13px;"> Set an email alert to get<br />
              updates of<br />
              <span class="li-red">${partyVO.partyLongName}</span>
              <input name="" type="text" id="emailId" class="ea-text-fields" value="your email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';document.getElementById('alertMsg').innerHTML = '';"/>
              <div id="alertMsg" style="dispaly:none"></div>
			  <div class="pl-sub-but"><a onclick="validateEmailField()" href="javascript:{};"><strong>Set alert</strong></a></div>
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
			   <a href="partyResultsCriteriaAction.action" class="buttonClass" style="text-decoration: none;" title="Click here to view other party performances and results">View Party Performance and Opposition Parties Details</a></div>
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
			<div class="fb-comments" data-href="http://www.partyanalyst.com/partyPageActionAction.action?partyId=${partyId}" data-num-posts="500" data-width="420"></div>
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
          </div>
       </div>
          
          <!--NEWS AND EVENTS SECTION END--> 
           
		   <!--VIDEOS SECTION START-->
 
		<s:if test="fileVO != null && fileVO.size() > 0"> 
		<div class="pr-sub-fields-sec">
            <h1 class="pr-title">videos<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span> </h1>
		<div id="videogallery" class="fleft">

	<s:iterator status="stat" value="fileVO">
		
		<s:if test="#stat.index == 0">
		<DIV>
		<a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' style="width: 297px; height: 227px;"/></a>
		</DIV>
		</s:if>
	</s:iterator>

	<s:if test="fileVO.size() > 1"> 
		<ul class="video-thumb-sec">
			<s:iterator status="stat" value="fileVO">
				<s:if test="#stat.index >= 1 && #stat.index <= 3">
				<li><a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer' style="width:72px;">
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
			   <h1 class="inc-title"><span class="da-gray">${partyVO.partyShortName} Party Manifestoes</span></h1>
			   <s:iterator value="partyVO.electionTypes" status="stat">
			   <s:if test="partyVO.electionTypes[#stat.index] =='Parliament'">
			   <div><input style="margin:5px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="getPartyManifesto(${partyVO.partyId});" checked="true"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   <s:if test="partyVO.electionTypes[#stat.index] == 'Assembly'">
			   <input style="margin:5px;" type="radio" name="elecType" id="<s:property value='partyVO.electionTypes[#stat.index]' />Id" onclick="selectedState('stateDiv');"><s:property value="partyVO.electionTypes[#stat.index]" />
			   </s:if>
			   </s:iterator>
			   <div id="selectStatediv" style="display:none;margin-top: -17px; margin-left: 138px;"><select id="stateDiv" name="stateDiv" onchange="getPartyManifestoBasedOnStateId();" class="selectWidth"></select></div>
				<div id="manifestoFilesDiv" style="margin-top: 7px;"></div>
			   </div>
			</s:if>
			</div>
			
	<div id="showContentDiv">
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
    str+='<div style="font-size: 13px; margin: 19px 10px 10px;">PC = Participated Constituencies    <br>VP = Voting Percentage <br> PC (%)= Participated Constituencies Voting Percentage	</div>';
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
   getStates();
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
		 else if(jsObj.task == "getFileByFileId")
			{
               showNews(myResults,jsObj.arrayType);
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
		}
		catch(e)
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
	str+='<div style="margin:5px;font-size:13px;margin-left: 69px;"> Loading Photo Gallaries .....<img style="float:right;margin-right: 295px;display:block;" src="images/icons/goldAjaxLoad.gif" id="videosLoadingImg_ImgSpan"></div>';
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
    var jsObj =
		{   
		    time : timeST,
			partyId:partyId,
			startRecord:0,
			maxRecord:20,
			task:"getPartyPhotoGallaryDetailWithOutGallerySizeZero"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
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
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
            }
			else
			{
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="images/icons/DefaultPhotoGalleryImage.jpg" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
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
   for(var i in results)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
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
   str+='<table width="100%" style="margin-top:10px;">'
   str+= '<tr style="display:none">';
    str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
    str+= '<tr><td><a rel="photo_gallery" id="photoClickId" href="'+results[0].path+'" title="'+results[0].fileTitle1+'"><img alt="" src="'+results[0].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	str += '<tr><td><div><b>'+results[0].fileDescription1+'</b></div></td></tr>';
    str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
    str+= '</tr>';
   for(var i =1;i<results.length; i++)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="display:none">';
     }
     str+= '<td width="33%" class="imageStyle"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileTitle1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
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
   $('#photoClickId').trigger('click');
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
  
  var str ='';
  
  if(results.length>0)
  {
   
   str +='<h1 class="pr-title">news &amp; Events<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>';

   str+='<ul>';
   for(var i =0 ;i<results.length && i<4;i++)
   {
     initialFileIdArray[i]=results[i].fileId;
     str+='<a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'initialArray\')" class="titleStyle"\">';
	 
	 if(results[i].fileTitle1.length > 42)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,42)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+results[i].source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 100)
		str += results[i].fileDescription1.substring(0,100)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
   str+='<div class="more"><a href="javascript:{}" onclick="getTotalNews(\'totalNews\');">More</a></div>';
   
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
    document.getElementById("newsDisplayDiv").innerHTML=str;
 }

 function getTotalNews(viewType)
 {  
    var queryType='Public';
   var jsObj =
		{   
		    time : timeST,
			viewtype:viewType,
			partyId:partyId,
			startRecord:0,
			maxRecord:20,
			queryType:queryType,
			task:"getNewsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }
 function showTotalNews(results)
 { 
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
     str+='       <td colspan="2"><div id="showScopeWiseNewsCount" /></td>';
     str+='     </tr>';
	 str+='   </table>';
     str+='  <table style="width:98%">'; 
  for(var i in results)
   { 
	 fileIdArray[i]=results[i].fileId;	    	  
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'array\')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
//	 str+='     <tr>';
 //  str+='       <td><img alt="" src="'+results[i].path+'" style="width:242px;height:275px;"/></td>';
 //  str+='     </tr>';
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNewsBySource1(\''+results[i].source+'\')" class="titleStyle"\"><font color="#FF4500">'+results[i].source+'</font></a> | <a href="javascript:{}" onclick="getNewsByLanguage1(\''+results[i].language+'\')" class="titleStyle"\"><font color="#FF4500">'+results[i].language+'</font></a> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='    <tr><td><hr style="width:98%;"></td></hr></tr>';
   }
   str+='  </table>';
   str+='</fieldset>';
   document.getElementById("showAllNewsDiv").innerHTML=str;
  // getScopeWiseNewsCount();
   }
 }
 function  buildNewsCount(result)
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
			maxResults:20,
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
			maxResults:20,
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
			candidateId:candidateId,
			queryType:queryTypeChecked,
			task:"getNewsCountByScope"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateNewsGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
   
 }
 function getNews(fileId,filSize,arrayType)
 { 
  if(arrayType=="initialArray")
  initialCurrentSize=filSize;
  if(arrayType=='array')
  currentSize= filSize;
    var jsObj =
		{   arrayType:arrayType,
		    time : timeST,
			fileId:fileId,
			task:"getFileByFileId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }
 function showNews(results,arrayType)
  {
    arraySize = fileIdArray.length;
	initialArraySize = initialFileIdArray.length;
    var fileType = results[0].name.split(".");
	  $.fx.speeds._default = 1000;
	  $("#showNewsDiv").dialog({ stack: false,
								height: 'auto',
								width: 'auto',
								closeOnEscape: true,
								position:[20,20],
								show: "blind",
								hide: "explode",
								modal: true,
								title:'<font color="Navy">'+results[0].fileTitle1+'</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();
	var str='';
	if(arrayType=='array')
	{
	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>:<font color="#FF4500"> '+results[0].fileDate+'</font>';
	 str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results[0].contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='   <tr>';
	 str+='      <td>';
	 str+='         <table>';
	 str+='	          <tr>';
	 if(currentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 if(fileType[(fileType.length-1)] == "pdf"  ){
	 str+='             <td><img alt="" src="images/doc_images/manifesto.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
		   str+='             <td><div class="container"><img alt="'+results[0].fileTitle1+'" src="'+results[0].path+'" title="'+results[0].fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 }
	 if(currentSize+1 <= (arraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+currentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 str+='		      </tr>';
	 str+='         </table>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        '+results[0].fileDescription1+'';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='<table>';
	}
	else if(arrayType=="initialArray")
	{
	 str+='<table>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>: <font color="#FF4500">'+results[0].fileDate+'</font>';
	 str+='       <a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results[0].contentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this News in Facebook" style="margin-left:30px;"></img></a>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='   <tr>';
	 str+='      <td>';
	 str+='         <table>';
	 str+='	          <tr>';
	 if(initialCurrentSize-1 >=0)
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getPreviousNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
	 }
	 if(fileType[(fileType.length-1)] == "pdf"  ){
	 str+='             <td><img alt="" src="images/doc_images/manifesto.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
	 str+=' <td><div class="container"><img alt="'+results[0].fileTitle1+'" src="'+results[0].path+'" title="'+results[0].fileDescription1+'" style="max-width:780px;max-length:800px;"/></div></td>';
	 }
	 if(initialCurrentSize+1 <= (initialArraySize-1))
	 {
	 str+='		        <td><a href="javascript:{}" onclick="getNextNews('+initialCurrentSize+',\''+arrayType+'\')"><img alt="" src="images/icons/jQuery/next.png"  class="newsImage" /></a></td>';
     }
	 str+='		      </tr>';
	 str+='         </table>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        '+results[0].fileDescription1+'';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='<table>';
	}

	str += '<div id="facebookCommentsInNewsDiv" class="container" style="overflow:auto;width:600px;"></div>';

	document.getElementById("showNewsDiv").innerHTML=str;

	var lb = document.getElementById("facebookCommentsInNewsDiv");
	lb.innerHTML='<div class="fb-comments" data-href="http://www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+results[0].contentId+'" data-num-posts="5" data-width="500"></div>';
	FB.XFBML.parse(lb);
	
  }
  function getPreviousNews(val,arrayType)
  { if(arrayType=="initialArray")
    {
     initialCurrentSize = val-1;
    getNews(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	}
	else if(arrayType=='array')
	{
	  currentSize = val-1;
    getNews(fileIdArray[currentSize],currentSize,arrayType);
	
	}
	
	
  }
  function getNextNews(val,arrayType)
  {
    if(arrayType=="initialArray")
	{
     initialCurrentSize = val+1;
     getNews(initialFileIdArray[initialCurrentSize],initialCurrentSize,arrayType);
	 }
	else if(arrayType=='array')
	{
	 currentSize = val+1;
     getNews(fileIdArray[currentSize],currentSize,arrayType);
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
	   str+='<li><img alt="" src="'+results[0].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary(\''+results[0].gallaryId+'\')"/><br />';
	  str+=''+results[0].title+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img alt="" src="'+results[1].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary(\''+results[1].gallaryId+'\')"/><br />';
	  str+=''+results[1].title+'</li>';
	  }
	  if(results[2]!=null  && results[2].path!=null)
	  {
	  count++;
	  str+=' <li><img alt="" src="'+results[2].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/><br />';
	  str+=''+results[2].title+'</li>';
	  
	  }
	  for(var i=3;i<results.length;i++)
	  {
	   if(results[i]!=null  && results[i].path!=null && count<3)
	   {
		count++;
		str+='<li><img alt="" src="'+results[i].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/><br />';
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
			maxRecords	: 20,
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
		showBusyImgWithId("videosLoadingImg");
   var jsObj = {
	       	   time : timeST,
			   partyId:partyId,
			   startRecord:0,
			   maxRecord:20,
			   task:"videoGalleriesForAParty"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "partyPhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
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
		str += '<td valign="top" width="25%"><table width="100%">';
		str += '<tr><td style="padding-left:4px;">';
		str+='<img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" height="120px;" width="120px;" style="cursor: pointer; border-radius: 5px 5px 5px 5px; left: 13px; padding: 7px; border: 1px solid #ccc; background: none repeat scroll 0pt 0pt #d3d3d3;"  onClick="getVideosInAGallary('+results[i].gallaryId+')"/></td></tr>';
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
		
		
		str+='<a href=javascript:{} style="color: #FFFFFF;margin-left: 339px;"" onclick="showAllVideoGalleries()" class="imageButton">Back To My Gallary</a>';
		
		str+='<table style="width:100%;">';
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:230px;">';
			
			str+='<td>';
			str+='<table style="font-size: 13px; font-family: verdana,arial;">';
			str+='<tr>';
			str+='<td>'+results[i].title+'';
			str+='</td>';
			str+='</tr>';
			str+='	<tr >';
			str+='<td style="border: 2px solid #CCCCCC;padding:5px;">';
			str+='<a target="blank"  href="http://www.youtube.com/v/'+results[i].pathOfFile+'?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer">';
			str+='<img src="http://img.youtube.com/vi/'+results[i].pathOfFile+'/0.jpg" width="110px;" height="100px;"/></td></a>';
			str+='</tr>';
			str+='<tr>';
			str+='<td>'+results[i].description+'';
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
			maxResults:20,
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
			maxResults:20,
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

		{key:"view details",label:"Complete Details",formatter:YAHOO.widget.DataTable.viewDetails},

		

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
	
	str+='<h1 class="pr-title" style="margin-top:15px;"> ${partyVO.partyLongName} Party Manifestoes <span class="or-down-arrow">';
   str += '  <img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>';
	str+= '<fieldset class="imgFieldset">';
	str+='<s:iterator value="partyVO.electionTypes" status="stat">';
	str+='<input style="margin:5px;" type="radio" name="elecType"><s:property value="partyVO.electionTypes[#stat.index]" />';
	str+='</s:iterator>';
	//str+='Select State';
	str+='<div id="selectStatediv" style="display: none;margin-top: -17px; margin-left: 138px;"><select id="stateDiv" name="stateDiv" onchange="getPartyManifestoBasedOnStateId();" class="selectWidth"></select></div>';
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
			 str+='<tr><td>'+results[i].description+'   '+results[i].title+'    '+results[i].fileDate +'  Manifesto Of    '+results[i].gallaryName;
			 }
			else
			 {
			 str+='<tr><td>'+results[i].title+'    '+results[i].fileDate +'  Manifesto Of    '+results[i].gallaryName;
			 }			
			str +='</td></tr><tr><td>';
			str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}"/>';
			str +='</td></tr></table>';
		   }
		   
		   str+='</td>';
		 }
		 str += '</tr></table>';
		 str+='</div>';
		 

		str+='<div class="more">';
		str+='<a href="javascript:{}" onclick="PartyManifestoPopup()">More</a></div>';
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
		str += '<input type="radio" name = "manifestoByScope" id="manifestoByScope" onclick="showManifestoGallary();getCountry();" checked="true"> Country</td><td style="width:10%;font-size: 14px;font-family:Trebuchet MS,Arial,Helvetica,sans-serif;">';
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
	maxRecord:20,
	task:"getPartyManifestoDetails"
  };

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "partyPhotoGallaryAction.action?"+rparam;						
callAjax(jsObj,url);
}

function getContentDetails(contentId)
{
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
								overlay: { opacity: 0.5, background: 'black'}
								});
		$("#showContentDiv").dialog();
		getContentDetails('${contentId}');
}

function buildContentDetails()
{
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
		titleStr = result.relatedGalleries[0].filesList[i].title;
		pathStr = result.relatedGalleries[0].filesList[i].path;
		descriptionStr = result.relatedGalleries[0].filesList[i].description;
		preContentId = result.relatedGalleries[0].filesList[i].contentId;
		curPos = i+1;
		totSize = result.relatedGalleries[0].filesList.length;
		
		if(result.contentType == 'Video Gallary' || result.contentType == 'News Gallary')
		{
			str+='<table>';
			str+='<tr>';
			str+='<td>';
			if(result.relatedGalleries[0].filesList[i].source != null)
				str+='<B>Source</B> : <font color="#FF4500">'+result.relatedGalleries[0].filesList[i].source+'</font> &nbsp;&nbsp;&nbsp;<B>';

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
		str += '<div class="container" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';
		
		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 0))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
		}
		str += '</Table>';
		str += '</div>';
		str += '</td>';
		}
		
		str += '<td valign="top" style="horizontal-align:center;">';
		str += '<div class="container">';
		str += '<iframe width="500" height="396" src="http://www.youtube.com/embed/'+pathStr+'" frameborder="0" allowfullscreen="true"></iframe>';
		str += '<table><tr>';
		str += '<td>';
		str += ''+descriptionStr+'';
		str += '</td>';
		str += '</tr>';
		str += '</table>';
		str += '</div>';
		str += '</td>';
		
		if(result.relatedGalleries[0].filesList.length >= 2){
		str += '<td valign="top">';
		str += '<div class="container" style="height:425px;overflow:auto;width:140px;">';
		str += '<b><font color="blue">Other Videos</font></b>';
		str += '<Table>';

		for(var i=0;i<result.relatedGalleries[0].filesList.length;i++)
		if(!result.relatedGalleries[0].filesList[i].isSelectedContent && (i%2 == 1))
		{
			str += '<tr><td><a href="javascript:{}" onClick="buildContentDetailsOfSelected('+preContentId+','+result.relatedGalleries[0].filesList[i].contentId+')" title="Click here to See the Video about - '+result.relatedGalleries[0].filesList[i].description+'"><img style="margin-top:8px;" src="http://img.youtube.com/vi/'+result.relatedGalleries[0].filesList[i].path+'/1.jpg" alt="'+result.relatedGalleries[0].filesList[i].title+'"></img></a></td></tr>';
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

			if(i != 0)
			{
				str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
			}
			
			str += '<td><div class="container"><img alt="'+result.relatedGalleries[0].filesList[i].title+'" title="'+result.relatedGalleries[0].filesList[i].description+'" style="max-width:780px;max-length:800px;" src="'+result.relatedGalleries[0].filesList[i].path+'" /></div></td>';

			if(i != result.relatedGalleries[0].filesList.length-1)
			{
				str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i+1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i+1].contentId+')"><img src="images/icons/jQuery/next.png" class="newsImage" /></a></td>';
			}
		}

		str += '</table>';

		str += '<div>';
		str += '<table>';
		str += '<tr><td>Description : <b>'+descriptionStr+'</b></td></tr>';
		str += '</table>';
		str += '</div>';
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
		
		str += '<div class="container" style="overflow:auto;width:880px;max-width:850px;">';
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
	str += '<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/partyPageAction.action?partyId=${partyId}&contentId='+preContentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this in Facebook"></img></a>';
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
	str+='<div id="content" style="width:650px;">';		
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
		
		str += '<td width="33%" class="imageStyle">';
		str += '<table class="tableStyle">';
	
		str +='<tr><td>';
		if(results[i].title=='Assembly'){
		str += results[i].description+'   '+results[i].title+' '+results[i].fileDate+'  Manifesto';
		 }
		else
		 {
		 str += results[i].title+'     '+results[i].fileDate;
		 }			
		str +='</td></tr><tr><td>';
		str+= '<img alt="" src="images/doc_images/manifesto.png" height="100px" onclick="javascript:{openFile(\''+results[i].path+'\')}"/>';
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
  selectedState();
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
</script>

</body>
</html>