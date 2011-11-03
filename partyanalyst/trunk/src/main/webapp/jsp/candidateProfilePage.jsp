<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${candidateVO.candidateName} 'S  Profile</title>
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
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">

<style type="text/css">
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
	font:bold 14px/24px "Trebuchet MS", Arial, Helvetica, sans-serif; 
	margin-bottom:13px; 
	padding:0px 15px;
}
.requiredFont
	{
		color:red;
	
	}
.text-fields
{
	font:12px/21px "Trebuchet MS", Arial, Helvetica, sans-serif; 
	color:#1a1a1a; 
	height:19px; 
	background-color:#EBE8E8; 
	border:1px solid #ffffff; 
	padding:0px 10px; 
	width:171px; 
	color:#000;
    padding-top:5px;
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

</style>

</head>
<body>
<table width="999px" border="0" align="center" cellpadding="0" cellspacing="0">
<tr><td><div class="clear"></div>
<div class="main-title-sec">
 <div class="main-mbg">${candidateVO.candidateName} 'S  Profile
  <span style="margin-top:12px; margin-left: 520px;">
 <a name="fb_share" type="button_count" 
share_url="www.partyanalyst.com/candidateElectionResultsAction.action?candidateId=${candidateId}">Share in Facebook</a> 
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript">
</script>
</span>
</div>
 <div class="main-bbg"></div></div><br></td></tr></table>
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
	   <!--EMAIL ALERT SECTION START-->
          
          <div class="ea-fc-sec">
            <h2 class="ea-fc-title">email alert <span class="blue-down-arrow"><img src="images//icons/candidatePage/blue-down-arrow.png" alt=""/></span> </h2>
            <div class="ea-fc-cont-sec" style="font-size:13px;"> Set an email elert to get<br />
              updates of<br />
              <span class="li-red">${candidateVO.candidateName}</span>
              <input name="" type="text" id="emailId" class="ea-text-fields" value="your email" onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value='';"/>
              <div id="alertMsg" style="dispaly:none"></div>
			  <div class="pl-sub-but"><a onclick="validateEmailField()"><strong>Set alert</strong></a></div>
            </div>
          </div>
          
          <!--EMAIL ALERT SECTION END--> 
     </div>
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
		
			 <!--ELECTION PROFILE SECTION START-->

              <div class="pm-inner-cont-sec" id="electionInfo"></div>

			  <!--ELECTION PROFILE SECTION END--> 

			  <!--PHOTO GALLERY SECTION START-->
          
               <div class="pm-inner-cont-sec" id="photoGallaryDiv"></div>
            
			 <div class="clear"></div>
			
			 
			 <!--FACE BOOK COMMENTS SECTION START
          
          <div class="fleft"> <img src="images/icons/candidatePage/facebook-comments.jpg" alt=""/></div>
          
          FACE BOOK COMMENTS SECTION END--> 
     			  
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

  <s:if test="fileVO == null">
  <div class="pr-sub-fields-sec">
            <h1 class="pr-title">videos<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>
	<br><br><strong>videos Updated Soon..</strong>
	</div>
  </s:if>

    <s:if test="fileVO != null && fileVO.size() > 4"> 
	 <div class="more"><a onClick="videoGallaryPopUp();">More</a></div>
	 </s:if>
	<div id="videoGallaryPopUpDiv">
	<div id="sendMessageDiv">
      <div id="constituencySelectDiv" />
    </div>

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
   var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var candidateId = '${candidateId}';
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
   var queryTypeChecked='Public';
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
		   	task : "saveMessage"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "createNewGallaryAction.action?"+rparam;
	callAjax(jsObj,url);	

}
function showAssemblyData()
 {
   var str='';
   
   str+='  <div class="pr-sub-fields-sec">';
   str+='  <h1 class="pr-title">send a message to ${candidateVO.candidateName} <span class="or-down-arrow">';
   str+=' 	  <img src="images/icons/or-down-arrow.png" alt=""/></span> </h1>';
   str += '<div id="galErrorMsgDivId" style = "width:190px;"></div>';
   str += '<div id="fileUploadErrorMsgDivId" style = "width:190px;"></div>';	  
   str+=' <table>';
   str += '<tr>';
   str+='<td style = "padding-top:9px;">Name <font class="requiredFont"> * </font></td>';
   str+='<td> <input type = "text" id="name" size = "20" class="text-fields"> </td>';
   str+='</tr>';
   str+='   <tr>';
   str+='     <td>State</td>';
   str+='     <td>';
   str+='       <select id="stateSelect"  onchange="clearAll(\'constituencySelect\');getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,\'constituency\')" style = "width:192px;background-color:#EBE8E8; border:1px solid #ffffff;"/>';
   str+='     </td>';
   str+='   </tr>';
   str+='   <tr>';
   str+='     <td>Constituency <font class="requiredFont"> * </font></td>';
   str+='     <td>';
   str+='       <select id="constituencySelect" style = "width:192px; background-color:#EBE8E8; border:1px solid #ffffff; "/>';
   str+='     </td>';
   str+='   </tr>';
   str+=' <tr>  <td>Message <font class="requiredFont"> * </font></td>';
   str+=' <td> <textarea id= "message" name ="message" rows="4" cols="8" style="background-color: #EBE8E8;';
   str+=' border: 1px solid #ffffff;';
   str+=' color: #000000; width: 181px;height: 104px;';
   str+=' font: 12px/17px "Trebuchet MS",Arial,Helvetica,sans-serif;';
   str+=' padding: 0px 0 0px 0px;"></textarea></td>';
   str+= '</tr>';
   str += '<tr>';
   str += '<td></td><td> <a href="javascript:{}" onClick="sendMessage()"> <img src="images/icons/send_btn.jpg"/></a> </td>';
   str += '</tr>';
   str+=' <table>';
   document.getElementById("constituencySelectDiv").innerHTML=str;
   getStates();
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

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
   }
   
  function showStatus(myResult)  
{

	var errorDivEle = document.getElementById('fileUploadErrorMsgDivId');
	var str = '';

	if(myResult.resultCode == 0)
	{
		cleardescriptionFields();
		str += '<font color="green"><b>Message Send Successfully.</b>';
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
}
function onYouTubePlayerReady(playerId) 
{ 
	ytplayer = document.getElementById("video_overlay"); 
	ytplayer.setVolume(100);
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
		else if(jsObj.task == "getStates")
			  {    
				buildResults(myResults,"stateSelect");
			  }
	   else if(jsObj.task == "getConstituencies")
			 {   
				showResults(myResults,"constituencySelect");
			 }
	    else if(jsObj.task == "saveMessage") 
			 {   
				showStatus(myResults);
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
		else if(jsObj.task == "getNewsByScope")
            {
			   showTotalNews(myResults);
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

function setDefaultImage(img)
{
		img.src = "images/candidates/human.jpg";
}
function validateEmailField()
	{
		debugger;
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

	/* var jsObj = {
		          emailId : emailIdVal,
				  candidateId :candidateId,
                   task:"setEmailAlertForUser"
	             };
    
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
	callAjax(jsObj,url);*/
}
function photoGallaryPopUp(){
	
	if(document.getElementById('buildPhotoGallaryDiv') == null)
		return;
     $("#buildPhotoGallaryDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
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
			candidateId:candidateId,
			startRecord:0,
			maxRecord:20,
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
   fireEvent(document.getElementById('photoClickId'),'click');
    document.getElementById("buildPhotoGallaryDiv").innerHTML ='';
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
  
   var str ='';
   str +='<h1 class="pr-title">news &amp; Events<span class="or-down-arrow"><img src="images/candidatePage/or-down-arrow.png" alt=""/></span></h1>';
	
  if(results.length>0)
  {
   str+='<ul>';
   for(var i =0 ;i<results.length && i<4;i++)
   {
     initialFileIdArray[i]=results[i].fileId;
     str+='<a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'initialArray\')" class="titleStyle"\">';
	 
	 if(results[i].fileTitle1.length > 30)
		str +='<li><strong>'+results[i].fileTitle1.substring(0,30)+'..</strong>';
	 else
		str +='<li><strong>'+results[i].fileTitle1+'</strong>';;

	 str += '</a>';
     str+='<div class="year-time"><span class="li-red">'+results[i].source+'</span> | '+results[i].fileDate+'</div>';
     
	 if(results[i].fileDescription1.length > 62)
		str += results[i].fileDescription1.substring(0,62)+'..</li>';
	 else
		str += ''+results[i].fileDescription1+'</li>';

     }
   str+='  </ul>';
   
   str+='<div class="more"><a href="javascript:{}" onclick="getTotalNews(\'totalNews\');" \">More</a></div>';
   
   str+='<table><tr><td><div id="showNewsDiv" /></td></tr></table>';
   str+='<table><tr><td><div id="showAllNewsDiv" /></td></tr></table>';
   
  
    for(var i =4 ;i<results.length;i++)
	{
	  initialFileIdArray[i]=results[i].fileId;
	}
   }
	else
	{
		str += '</ul><li><strong>News And Events Updated Soon..</strong></li></ul>';
	}
    document.getElementById("newsDisplayDiv").innerHTML=str;
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
			viewtype:viewType,
			candidateId:candidateId,
			startRecord:0,
			maxRecord:20,
			queryType:queryType,
			task:"getNewsToDisplay"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
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
     str+='       <td><font color="#FF4500">'+results[i].source+'</font> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='    <tr><td><hr style="width:98%;"></td></hr></tr>';
   }
   str+='  </table>';
   str+='</fieldset>';
   document.getElementById("showAllNewsDiv").innerHTML=str;
   getScopeWiseNewsCount();
   }
 }
 function  buildNewsCount(result)
 {
    str='';
	if(result[9].visibility=="True")
	{
	 str+='   <table>';
	 str+='     <tr>';
	 str+='       <td>Sort By : </td>';
	 str+='       <td>';
	 str+='          <select id="candidateVisibility" onchange="getTotalNews(\'totalNews\');"  >';
	 str+='             <option value="Public">Public</option>';
	 str+='             <option value="Private">Private</option>';
	 str+='             <option value="All">All</option>';
	 str+='          </select>';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='   </table>';
   	}
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
	 str+='             <td><img alt="" src="images/doc_images/PDFImage.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
	 str+='             <td><img alt="" src="'+results[0].path+'" /></td>';
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
	 str+='             <td><img alt="" src="images/doc_images/PDFImage.png" onclick="openFile(\''+results[0].path+'\')" style="cursor:pointer;" /></td>';
	 }
	 else
	 {
	 str+='             <td><img alt="" src="'+results[0].path+'" /></td>';
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
	 document.getElementById("showNewsDiv").innerHTML=str;
	
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
	   str+='<li><img alt="" src="'+results[0].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[0].gallaryId+')"/><br />';
	  str+=''+results[0].title+'</li>';
	 
	  }
	  if(results[1]!=null && results[1].path!=null)
	  {
	  count++;
	  str+='<li><img alt="" src="'+results[1].path+'" style="height:120px;width:127px;" onclick="getCandidatesPhotosInAGallary('+results[1].gallaryId+')"/><br />';
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
	  str+='<a href="javascript:{}" onclick="photoGallaryPopUp()" \">More</a></div>';
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
		
	electionInfoElmt.innerHTML = str;
}

function candidateInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';
	
    str+='<div class="ptd-sec">';
	str+='<img height="250" width="180" onerror="setDefaultImage(this)" src="images/candidates/${candidateVO.candidateName}.jpg"><span class="tc-tf pa-fi">';
	str+='<label class="c-red">${candidateVO.candidateName}</label></span><br />';
	
	<s:if test="candidateElectionDetails[0].electionType == 'Assembly'">
		str += 'MLA <br />';
	</s:if>

	<s:if test="candidateElectionDetails[0].electionType == 'Parliament'">
		str += 'MP <br />';
	</s:if>
	str+='<a target ="blank" href="constituencyPageAction.action?constituencyId=${candidateElectionDetails[0].constituencyId}">${candidateElectionDetails[0].constituencyName} CONSTITUENCY</a><br />';
	str+='${candidateElectionDetails[0].partyName} PARTY';
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
   str+='<h1 class="inc-title">About ${candidateVO.candidateName}</h1>';
   
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
   <s:if test="fileVO == null || fileVO.size() < 4"> 
   return;
   </s:if>
  
   $("#videoGallaryPopUpDiv").dialog({ stack: false,
							    height: 350,
								width: 520,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">Video Galleries</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});

	showAllVideoGalleries();
}

function showAllVideoGalleries(){
		
   var jsObj = {
	       	   time : timeST,
			   candidateId:candidateId,
			   startRecord:0,
			   maxRecord:20,
			   task:"videoGalleriesForACandidate"
            };

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
	}

function buildVideoGallaries(results)
{
    var str ='';
    str+='<table>';
    str += '<tr>';
	for(var i=0;i<results.length;i++)
	{
		str += '<td><table><tr><td><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
		str += '<tr><td>';
		str+='<img src="http://img.youtube.com/vi/'+results[i].path+'/0.jpg" width="72px;" height="75px;" onClick="getVideosInAGallary('+results[i].gallaryId+')"/></td></tr>';
		str+='</div>';
		str+= '<tr><td><div style="font-size: 13px; font-family: verdana,arial;""><b>Gallery Size: ('+results[i].sizeOfGallary+')</b></div></td></tr>';
		str += '<tr><td><div style="font-size: 13px; font-family: verdana,arial;"><b>'+results[i].gallaryDescription+'</b></table></td>';
			
		 }
		 str+='</tr>';
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
showAssemblyData();
displayProfile();
candidateInfo();
buildCandidateElectionInfo();
getTotalNews('getFirstFourNewsRecordsToDisplay');
getFirstThreePhotoRecords();
</script>
</body>
</html>