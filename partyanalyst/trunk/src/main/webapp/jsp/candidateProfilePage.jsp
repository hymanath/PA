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
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" type="text/css" href="styles/videoGallary/videolightbox.css"/>
<style type="text/css">#videogallery a#videolb{display:none}</style>
<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<script type="text/javascript" src="js/videoGallary/jquery.tools.min.js"></script> 
<script type="text/javascript" src="js/videoGallary/swfobject.js" ></script>  
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>

<style type="text/css">
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
	border			: 4px solid #9F81F7;
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
</style>
<script type="text/javascript">
   var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var candidateId = '${candidateId}';

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
         if(jsObj.task == "getFirstFourNewsRecordsToDisplay")
			{
               showFirstFourNewsRecords(myResults);
			}
		 else if(jsObj.task == "getFileByFileId")
			{
               showNews(myResults);
			}
		 else if(jsObj.task == "getNewsToDisplay")
			{
               showTotalNews(myResults);
			}
		 else if(jsObj.task == "getFirstThreePhotoGallaryDetail")
			{
               buildFirstThreePhotoRecords(myResults);
			}
		 else if(jsObj.task == "getCandidatePhotoGallaryDetail")
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
function photoGallaryPopUp(){
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
			task:"getCandidatePhotoGallaryDetail"
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
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
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
  if(results.length>0)
  {
   var str ='';
   str+='  <table>';
  
   for(var i in results)
   {
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td><font color="#FF4500">'+results[i].source+'</font> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='     <hr style="width:98%;"></hr>';
   }
   str+='  </table>';
   
   str+='<a href="javascript:{}" onclick="getTotalNews()" \"><img src="images/icons/more.jpg" align="right"></a>';
   str+='<div id="showNewsDiv" />';
   str+='<div id="showAllNewsDiv" />';
   
   document.getElementById("newsDisplayDiv").innerHTML=str;
   }
 }
 function getTotalNews()
 {
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			startRecord:0,
			maxRecord:20,
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
   str+='  <table>';
  
   for(var i in results)
   {
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td><font color="#FF4500">'+results[i].source+'</font> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='     <hr style="width:98%;"></hr>';
   }
   str+='  </table>';
   str+='</fieldset>';
   document.getElementById("showAllNewsDiv").innerHTML=str;
   }
 }
 function getNews(fileId)
 {
    var jsObj =
		{   
		    time : timeST,
			fileId:fileId,
			task:"getFileByFileId"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);  
 }
 function showNews(results)
  {
    var fileType = results[0].name.split(".");
	if(fileType[(fileType.length-1)] == "pdf"  )
     openFile(results[0].path);	 
    else if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'text' )
	{
	  
	}
	else
	{
	  $("#showNewsDiv").dialog({ stack: false,
							    height: 570,
								width: 720,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy">'+results[0].fileTitle1+'</font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showNewsDiv").dialog();
	var str='';
	 str+='<fieldset class="imgFieldset">';
	 str+='<table>';
	  str+='<tr>';
	   str+='<td align="center">';
	  str+='<img alt="" src="'+results[0].path+'" style="max-width:645px;max-height:418px;align:center;"/>';
	  str+='</td>';
	   str+='</tr>';
	   str+='<tr>';
	   str+='<td>';
	  str+=''+results[0].fileDescription1+'';
	  str+='</td>';
	   str+='</tr>';
	   str+='<tr>';
	   str+='<td>';
	  str+='<B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>: '+results[0].fileDate+'';
	  str+='</td>';
	   str+='</tr>';
	 str+='<table>';
	 str+='</fieldset>';
	 document.getElementById("showNewsDiv").innerHTML=str;
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
function buildFirstThreePhotoRecords(results){
if(results.length>0)
 {
  document.getElementById("photoGallaryDiv").innerHTML= '';
  str ='';
  str+='<table>';
  str+='  <tr>';
  str+='    <td style="margin-bottom:21px;font-weight:bolder;font-size:14px;font-family:Arial;">PHOTO GALLERY</td>';
  str+='  </tr>';
  str+='  <tr>';
  str+='     <td>';
  str+='      <table>';
  str+='         <tr><td>';
  str+='             <img alt="" src="'+results[0].path+'" class="photoStyle" onclick="getCandidatesPhotosInAGallary('+results[0].gallaryId+')"/>';
  str+='         </td></tr>';
  str+='         <tr><td>';
  str+='           &nbsp;&nbsp;'+results[0].title+'';
  str+='         </td></tr>';
  str+='       </table>';
  str+='     </td>';
  if(results[1]!=null)
  {
  str+='     <td>';
  str+='       <table>';
  str+='         <tr><td>';
  str+='           <img alt="" src="'+results[1].path+'" class="photoStyle" onclick="getCandidatesPhotosInAGallary('+results[1].gallaryId+')"/>';
  str+='         </td></tr>';
  str+='         <tr><td>';
  str+='            &nbsp;&nbsp;'+results[1].title+'';
  str+='         </td></tr>';
  str+='       </table>';
  str+='      </td>';
  }
  if(results[2]!=null)
  {
  str+='      <td>';
  str+='       <table>';
  str+='         <tr><td>';
  str+='           <img alt="" src="'+results[2].path+'" class="photoStyle" onclick="getCandidatesPhotosInAGallary('+results[2].gallaryId+')"/>';
  str+='         </td></tr>';
  str+='         <tr><td>';
  str+='             &nbsp;&nbsp;'+results[2].title+'';
  str+='         </td></tr>';
  str+='       </table>';
  str+='      </td>';
  }
  str+='    </tr>';
  str+='</table>';
  str+='</tr>';
  str+='<a href="javascript:{}" onclick="photoGallaryPopUp()" \"><img src="images/icons/more.jpg" align="right"></a>';
  str+='<div id="buildPhotoGallaryDiv"></div>';
  document.getElementById("photoGallaryDiv").innerHTML= str;
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
	
	str+='<table>';
    str+='<div style="margin-bottom: 21px; font-weight: normal; font-size: 19px; font-family: tahoma;">Election Profile</div>';

	str+='<s:iterator value="candidateElectionDetails" status="stat">';

	str+='<s:if test="status == true">';
	
	if(wFlag == true)
	{
		str += '<div><img src="images/icons/won.jpg"><br><br></div>';
		wFlag = false;
	}
	
    str+='<div onmouseover="this.style.color=\'#4D2AEB\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<b><img src="images/icons/round.JPG">Won</b>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for   <s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName" /> constituency<br><br></div></s:if>';
	
	str+'</s:iterator>';
	
	str+='<s:iterator value="candidateElectionDetails" status="stat">';
	str+='<s:if test="status == false">';
	
	if(lFlag == true)
	{
		str+='<div><img src="images/icons/lost.jpg"><br><br></div>';
		lFlag=false;
	}
	
    str+='<div onmouseover="this.style.color=\'#F13144\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<b><img src="images/icons/round.JPG">Lost</b>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for   <s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName"/> constituency<br><br></div></s:if>';
	
	str+'</s:iterator>';
		
	str+='</table>';
	
 electionInfoElmt.innerHTML = str;
}

function candidateInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';
	

	str+='<table>';
	str+='<img id="candidateImage" height="250" width="180" onerror="setDefaultImage(this)" src="images/candidates/'+candidateInfoObject.candidateInfoArray[0].candidateName+'.jpg">';
	str+='<tr><td>';
	str+='<span id="candidateName">'+candidateInfoObject.candidateInfoArray[0].candidateName+'</span></td></tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size:12px;">';
    if(candidateInfoObject.candidateInfoArray[0].electionType =="Assembly")
	{
     str+='MLA';
	}
	if(candidateInfoObject.candidateInfoArray[0].electionType =="Parliament")
	{
     str+='MP';
	} 
	str+='</span></td>';
	str+='</tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">'+candidateInfoObject.candidateInfoArray[0].constituencyName+'&nbsp;Constituency</span></td></tr>';
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">'+candidateInfoObject.candidateInfoArray[0].partyName+' Party</span></td>';
	
	candidateInfoElmt.innerHTML = str;
}

 function getTotalProfile()
 {

 $.fx.speeds._default = 900;
  $("#showProfile").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 570,
								width: 600,
								position:[150,120],								
								modal: true,
								title:'<font color="Navy"><b>${candidateVO.candidateName}</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#showProfile").dialog();
   
 
   var str ='';
    str+='<fieldset class="imgFieldset">';
    str+='  <table>';
    str+='  <s:if test="descriptions != null">'; 
    str+='  <div style="font-weight: bold; font-size: 14px;">About ${candidateVO.candidateName}</div>';
    str+=' <br><s:iterator value="descriptions">';
	str+=' <div style="margin-bottom: 21px; font-weight: normal; font-size: 11px; font-family: tahoma;">';
    str+=' <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:property />';
	str+='</div>';
    str+='</s:iterator>';
    str+=' </s:if>';
    str+='  </table>';
    str+='</fieldset>';
    document.getElementById("showProfile").innerHTML=str;
   
    
 }
 
 function displayProfile()
 {
 
   var profileInfoElmt = document.getElementById("ProfileInfo");
    var str='';
    var x=1;
   
   str+='<s:if test="descriptions != null"> ';
   str+='<div style="font-weight: bold; font-size: 15px;">About ${candidateVO.candidateName}</div>';
   
   str+='	<br><s:iterator value="descriptions">';
 if (x<=2)
   {
   str+='  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:property />';
   x++;
   }
   str+='  </s:iterator>';
   str+='<div style="text-align: right;"><a href="javascript:{}" onclick="getTotalProfile()" style="color: LightSkyBlue;">';
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

</script>
</head>
<body>
<div id="candidateProfileInfo">
 <span style="margin-top: 12px; margin-left: 12px;">${candidateVO.candidateName} 'S  Profile</span></div>
<table width="987px" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
  <td width="206">
  <div class="rel">
   <div class="box1">
   <div id="candidateInfo" >
	
	</div>
	<div style="border-bottom: 1px solid #D7E2EB;"></div>
	 <div class="linkClass">About
	   </div>
	   <div class="linkClass">News And Events
	   </div>
	   <div class="linkClass">Videos
	   </div>
	   <div class="linkClass">Photo Gallery
	   </div>
	   <div class="linkClass">Elections
	   </div>
	   <div class="linkClass">Developments
	   </div>
    </div>
	</div>
 </td>
 <td width="10">&nbsp;</td>
 <td width="444" valign="top"><div class="rel">
 <div class="box2">
 <div id="ProfileInfo"> </div>
<div id="showProfile"> </div>
<div id="electionInfo"></div>
     <div id="photoGallaryDiv"></div>
  </div>
 </div>
</td>
 <td width="10">&nbsp;</td>
<td width="326">
<div class="rel">
 <div class="box3"><img src="images/icons/news_events.jpg"/>
  <div id="newsDisplayDiv"></div>

<s:if test="fileVO != null && fileVO.size() > 0"> 
<img src="images/icons/videos.jpg" style="margin-top:5px;margin-bottom:5px;"></img>
<div id="videogallery">

	<s:iterator status="stat" value="fileVO">
		
		<s:if test="#stat.index == 0">
		<DIV>
		<a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' width="230px;" height="210px;"/>
		</DIV>
		</s:if>
	</s:iterator>

	<s:if test="fileVO.size() > 1"> 
		<DIV style="margin-top:5px;"><table width="100%"><tr>
			<s:iterator status="stat" value="fileVO">
				<s:if test="#stat.index >= 1 && #stat.index <= 3">
				<td><a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer' style="width:72px;">
				<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' width="72px;" height="75px;"/></td>
				</s:if>
			</s:iterator>
		</tr></table>
		</DIV>
	</s:if>

</div>
</s:if>

<s:if test="fileVO != null && fileVO.size() > 4"> 
<img src="images/icons/more.jpg" align="right" style="margin-top:5px;" onClick="javascript:{}">
</s:if>

 </div>
 
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
	
	
	candidateInfoObject.name = "${candidateVO.candidateName}";
	candidateInfoObject.candidateImgURL = "<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ;
	candidateInfoObject.contextPath = "<%=request.getContextPath()%>";
	candidateInfoObject.candidatePartyFlag = "<%=request.getContextPath()%>/images/party_flags/${partyFlag}";

	<c:forEach var="candidateElectionResults" items="${candidateElectionDetails}" >		
			var candidateObj={
								electionId:'${candidateElectionResults.electionId}',
								candidateName:'${candidateElectionResults.candidateName}',
								partyName:'${candidateElectionResults.partyName}',
								partyFlag:'${candidateElectionResults.partyFlag}',
								constituencyName:'${candidateElectionResults.constituencyName}',
								electionType:'${candidateElectionResults.electionType}',
								electionYear:'${candidateElectionResults.electionYear}',
								districtName:'${candidateElectionResults.districtName}',
								stateName:'${candidateElectionResults.stateName}',
								votesEarned:'${candidateElectionResults.votesEarned}',
								votePercentage:'${candidateElectionResults.votesPercentage}',
								constituencyId:'${candidateElectionResults.constituencyId}',
								education:'${candidateElectionResults.education}',
								partyShortName:'${candidateElectionResults.shortName}',	
								status:'',
								oppositionCandidates:[]
							};
			<c:if test="${candidateElectionResults.status == true }">
				candidateObj.status='Won';
			</c:if>						
			<c:if test="${candidateElectionResults.status == false }">
				candidateObj.status='Lost';
			</c:if>

			<c:forEach var="detailedResult" items="${candidateElectionResults.oppositionCandidates}" >
				var oppositionList={
									candidateName:'${detailedResult.candidateName}',
									partyName:'${detailedResult.partyName}',
									votesEarned:'${detailedResult.votesEarned}',
									votesPercentage:'${detailedResult.votesPercentage}',
									status:''
								};
						<c:if test="${detailedResult.status == true }">
							oppositionList.status='Won';
						</c:if>
						<c:if test="${detailedResult.status == false }">
							 oppositionList.status='Lost';
						</c:if>
					candidateObj.oppositionCandidates.push(oppositionList);
			</c:forEach>			
			candidateInfoObject.candidateInfoArray.push(candidateObj);			
	</c:forEach>
	
	function setDefaultImage(img)
	{
		img.src = "images/candidates/human.jpg";
	}

function showCandidateElectionDetails(str)
{
	
	//var data = candidateInfoObject.candidateInfoArray[index];
	var politicalChangesWindow = window.open(str+"","politicalChangesWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
    politicalChangesWindow.focus();
}

displayProfile();
candidateInfo();
buildCandidateElectionInfo();
getFirstFourNewsRecords();
getFirstThreePhotoRecords();
</script>
</body>
</html>