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
 .newsImage {
   height:53px;
   width:29px;
}

.eleprofileImg{
	margin: 1px;
    padding-right: 8px;
}

</style>

</head>
<body>
<div id="candidateProfileInfo">
 <span style="margin-top: 12px; margin-left: 12px;">${candidateVO.candidateName} 'S  Profile</span></div><br>
<table width="987px" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:15px;">
 <tr>
  <td width="206" valign="top">
  <div class="rel">
   <div class="box1">
   <div id="candidateInfo" >
	
	</div>
	<div style="border-bottom: 1px solid #D7E2EB;"></div>
	   <div class="linkClass"><img src="images/icons/star.jpg" class="eleprofileImg"/>News And Events</div>
	   <div class="linkClass"><img src="images/icons/star.jpg" class="eleprofileImg"/>Videos</div>
	   <div class="linkClass"><img src="images/icons/star.jpg" class="eleprofileImg"/>Photo Gallery</div>
	   <div class="linkClass"><img src="images/icons/star.jpg" class="eleprofileImg"/>Developments</div>
    </div>
	</div>
 </td>
 <td width="10">&nbsp;</td>
 <td width="444" valign="top"><div class="rel">
 <div class="box2">
 <div id="ProfileInfo" style="font-family:arial;font-size:12px;text-align:justify;"> </div>
<div id="showProfile"> </div>
<div id="electionInfo" style="font-family:arial;font-size:12px"></div>
     <div id="photoGallaryDiv"></div>
  </div>
 </div>
</td>
 <td width="10">&nbsp;</td>
<td width="326" valign="top">
<div class="rel">
 <div class="box3">
  <div id="newsDisplayDiv"></div>

<s:if test="fileVO != null && fileVO.size() > 0"> 
<img src="images/icons/videos.jpg" style="margin-top:5px;margin-bottom:5px;text-align:left;"></img>
<div id="videogallery" style="text-align:left;">

	<s:iterator status="stat" value="fileVO">
		
		<s:if test="#stat.index == 0">
		<DIV>
		<a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer'>
		<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' width="230px;" height="210px;"/></a>
		</DIV>
		</s:if>
	</s:iterator>

	<s:if test="fileVO.size() > 1"> 
		<DIV style="margin-top:5px;"><table width="100%"><tr>
			<s:iterator status="stat" value="fileVO">
				<s:if test="#stat.index >= 1 && #stat.index <= 3">
				<td><a rel="#voverlay" href='http://www.youtube.com/v/<s:property value="path"/>?autoplay=1&rel=0&enablejsapi=1&playerapiid=ytplayer' style="width:72px;">
				<img src='http://img.youtube.com/vi/<s:property value="path"/>/0.jpg' width="72px;" height="75px;"/></a></td>
				</s:if>
			</s:iterator>
		</tr></table>
		</DIV>
	</s:if>

</div>
</s:if>


<s:if test="fileVO != null && fileVO.size() > 4"> 
 <img src="images/icons/more.jpg" align="right" style="margin-top:5px;" onClick="videoGallaryPopUp();" />
 </s:if>
 </div>
 
 </td>
 </tr>
 
</table>
</div>

<div id="videoGallaryPopUpDiv">
	
</div>


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
   var descriptions = '${descriptions}'; 
   var timeST = new Date().getTime();
   var candidateId = '${candidateId}';
   var fileIdArray = new Array();
   var initialFileIdArray = new Array();
   var initialArraySize =0;
   var initialCurrentSize=0;
   var arraySize =0;
   var currentSize=0;
     

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
  if(results.length>0)
  {
   var str ='';
   str +='<img src="images/icons/news_events.jpg" style="margin-bottom:5px;"/>';
   str+='  <table style="width:98%;">';
  
   for(var i =0 ;i<results.length && i<4;i++)
   {
     initialFileIdArray[i]=results[i].fileId;
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'initialArray\')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td><font color="#FF4500">'+results[i].source+'</font> | '+results[i].fileDate+'</td>';
     str+='     </tr>';
     str+='     <tr>';
     str+='       <td>'+results[i].fileDescription1+'</td>';
     str+='     </tr>';
	 str+='     <tr><td> <hr style="width:98%;"></hr></td></tr>';
   }
   str+='  </table>';
   
   str+='<a href="javascript:{}" onclick="getTotalNews(\'totalNews\');" \"><img src="images/icons/more.jpg" align="right"></a>';
   
   str+='<table><tr><td><div id="showNewsDiv" /></td></tr></table>';
   str+='<table><tr><td><div id="showAllNewsDiv" /></td></tr></table>';
   
   document.getElementById("newsDisplayDiv").innerHTML=str;
    for(var i =4 ;i<results.length;i++)
	{
	  initialFileIdArray[i]=results[i].fileId;
	}
   }
 }
 function getTotalNews(viewType)
 {
   var jsObj =
		{   
		    time : timeST,
			viewtype:viewType,
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
     str+='  <table style="width:98%">'; 
  for(var i in results)
   { 
	 fileIdArray[i]=results[i].fileId;	    	  
     str+='     <tr>';
     str+='       <td><a href="javascript:{}" onclick="getNews('+results[i].fileId+','+i+',\'array\')" class="titleStyle"\">'+results[i].fileTitle1+'</a></td>';
     str+='     </tr>';
//	 str+='     <tr>';
 //    str+='       <td><img alt="" src="'+results[i].path+'" style="width:242px;height:275px;"/></td>';
 //    str+='     </tr>';
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
   }
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
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>: '+results[0].fileDate+'';
	 str+='       </td>';
	 str+='     </tr>';
	 str+='<table>';
	}
	else if(arrayType=="initialArray")
	{
	 str+='<table>';
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
	 str+='     <tr>';
	 str+='       <td>';
	 str+='        <B>Source</B> : <font color="#FF4500">'+results[0].source+'</font> <B> Date </B>: '+results[0].fileDate+'';
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
function buildFirstThreePhotoRecords(results){
if(results.length>0)
 {
  var count=0;
  document.getElementById("photoGallaryDiv").innerHTML= '';
  str ='';
  str+='<table>';
  str+='  <tr>';
  str+='    <td style="margin-bottom:21px;font-weight:bolder;font-size:14px;font-family:Arial;">PHOTO GALLERY</td>';
  str+='  </tr>';
  str+='  <tr>';
  if(results[0].path!=null)
  {
   count++;
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
  }
  if(results[1]!=null && results[1].path!=null)
  {
  count++;
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
  if(results[2]!=null  && results[2].path!=null)
  {
  count++;
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
  for(var i=3;i<results.length;i++)
  {
   if(results[i]!=null  && results[i].path!=null && count<3)
   {
    count++;
	str+='     <td>';
  str+='       <table>';
  str+='         <tr><td>';
  str+='           <img alt="" src="'+results[i].path+'" class="photoStyle" onclick="getCandidatesPhotosInAGallary('+results[i].gallaryId+')"/>';
  str+='         </td></tr>';
  str+='         <tr><td>';
  str+='             &nbsp;&nbsp;'+results[i].title+'';
  str+='         </td></tr>';
  str+='       </table>';
  str+='      </td>';  
   }
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
	
	str+='<table><tr><td>';
    str+='<div style="margin-bottom: 21px; font-weight: normal; font-size: 19px; font-family: tahoma;text-align:left">Election Profile</div>';

	str+='<s:iterator value="candidateElectionDetails" status="stat">';

	str+='<s:if test="status == true">';
	
	if(wFlag == true)
	{
		str += '<div style="text-align:left"><img src="images/icons/won.jpg"><br><br></div>';
		wFlag = false;
	}
	
    str+='<div onmouseover="this.style.color=\'#4D2AEB\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<b><img src="images/icons/round.JPG" class="eleprofileImg">Won</b>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for   <s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName" /> constituency<br><br></div></s:if>';
	
	str+'</s:iterator>';
	
	str+='<s:iterator value="candidateElectionDetails" status="stat">';
	str+='<s:if test="status == false">';
	
	if(lFlag == true)
	{
		str+='<div style="text-align:left"><img src="images/icons/lost.jpg"><br><br></div>';
		lFlag=false;
	}
	
    str+='<div onmouseover="this.style.color=\'#F13144\';" onmouseout="this.style.color=\'#333333\';" style="cursor:pointer;text-align:left;" onclick="showCandidateElectionDetails(\'constituencyElectionResultsAction.action?constituencyId=<s:property value="constituencyId"/>&electionType=<s:property value="electionType"/>&electionYear=<s:property value="electionYear"/>\')">';
	str+='<b><img src="images/icons/round.JPG" class="eleprofileImg">Lost</b>';
	str+=' <b>in <s:property value="electionYear" /></b>&nbsp;&nbsp;<s:property value="electionType" />&nbsp;&nbsp;Election with &nbsp;&nbsp;<b><s:property value="votesPercentage" />% </b>&nbsp;&nbsp;of votes gain for   <s:property value="partyName" />&nbsp;&nbsp;party in &nbsp;&nbsp;<s:property value="constituencyName"/> constituency<br><br></div></s:if>';
	
	str+'</s:iterator>';
		
	str+='</td></tr></table>';
	
 electionInfoElmt.innerHTML = str;
}

function candidateInfo()
{
	var candidateInfoElmt = document.getElementById("candidateInfo");
    var str='';
	

	str+='<table style="margin-bottom:25px;">';
	str+='<img id="candidateImage" height="250" width="180" onerror="setDefaultImage(this)" src="images/candidates/${candidateVO.candidateName}.jpg">';
	str+='<tr><td align="center">';
	str+='<span id="candidateName">${candidateVO.candidateName}</span></td></tr>';
	
	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size:12px;">';
	
	<s:if test="candidateElectionDetails[0].electionType == 'Assembly'">
		str += 'MLA';
	</s:if>

	<s:if test="candidateElectionDetails[0].electionType == 'Parliament'">
		str += 'MP';
	</s:if>

	str+='</span></td>';
	str+='</tr>';

	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">${candidateElectionDetails[0].constituencyName} CONSTITUENCY</span></td></tr>';

	str+='<tr><td align="center">';
	str+='<span  style="font-weight: bold; font-size: 12px;">${candidateElectionDetails[0].partyName} PARTY</span></td></tr>';

	str += '</table>';
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

function showCandidateElectionDetails(str)
{
	var politicalChangesWindow = window.open(str+"","politicalChangesWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
    politicalChangesWindow.focus();
}

function videoGallaryPopUp()
{
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
displayProfile();
candidateInfo();
buildCandidateElectionInfo();
getTotalNews('getFirstFourNewsRecordsToDisplay');
getFirstThreePhotoRecords();
</script>
</body>
</html>