<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${specialPageVO.title}</title>
<s:if test="metaInfoVO != null && metaInfoVO.keywords != null">
<META NAME="Keywords" CONTENT="${metaInfoVO.keywords}"/>
</s:if>
<s:if test="metaInfoVO != null && metaInfoVO.description != null">
<meta name="description" content="${metaInfoVO.description}"/>
</s:if>
<meta property="fb:app_id" content="167844749984003"/>
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
<script type="text/javascript" src="js/jQuery/floating-1.5.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css"
	href="styles/candidatePage/candidatePage.css">
<style>
.pft-sec {
    color: #777777;
    float: left;
    font-size: 20px;
    line-height: 24px;
    padding: 32px 0 16px 112px;
    width: 244px;
}
.container {
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    	background-color: #FFFFFF;
    	margin: 9px auto 40px;
    	max-width: 800px;
    	padding: 10px;
	}
	.main-mbg {
    -moz-border-radius: 6px 6px 6px 6px;
	border-radius :6px;
    background-color: #06ABEA;
    clear: both;
    color: #FFFFFF;
    float: left;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    margin-bottom: 5px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 974px;
}
.profile-left-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll left top transparent;
    display: inline-block;
    float: left;
    margin-right: 2px;
    padding-top: 5px;
    width: 206px;
}
.profile-mid-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -211px top transparent;
    display: inline-block;
    float: left;
    padding-top: 5px;
    position: relative;
    width: 453px;
}
.profile-right-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -683px top transparent;
    float: left;
    margin-left: 2px;
    padding-top: 5px;
    width: 326px;
}
#contenttable {
    display: block;
    margin-left: auto;
    margin-right: auto;
    padding: 0;
    width: 975px;
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
.view-results a {
    background: url("images/icons/homePage_new/b3.jpg") no-repeat scroll 0 0 transparent;
    border: medium none;
    cursor: pointer;
    display: block;
    height: 27px;
    margin: 11px 0 15px 113px;
    text-indent: -9999px;
    width: 94px;
}
.selectBoxWidth{
	margin-left: 4px;
    margin-top: 26px;
    padding-left: 3px;
    width: 179px;
}
.selectDivStyle {
   
    background-color: #21B2ED;
    color: #FFFFFF;
    float: left;
    padding: 4px;
    width: 179px;
	 font-weight: bold;
	 margin-bottom:10px;
}
.flagStyle  
{
	background:#f0f0f0;float: left;
	width: 60px;
	text-align: center;
	left: 0px;
	clear:both;
	bottom: 0px;
	height: 21px;
	color: #2A4F97;
	font-size: 12px;
    font-weight: bold;
	font-size: 11px;
	line-height: 21px;

}
.selectHeading{
font-size: 13px; width: 187px; border-right: 1px solid rgb(205, 205, 205); border-left: 1px solid rgb(205, 205, 205); border-bottom: 1px solid rgb(205, 205, 205); margin-bottom: 16px;


}
.tableStyle 
 {
   align:top;
 }
  .gallaryImg
{
	width  : 150px;
	height : 130px;
}

</style>
<script type="text/javascript">

var specialPageId = '${specialPageId}';
var showContentResultList = null;

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

function getContentDetails(contentId)
{
	var jsObj =
		{   
		    contentId : contentId,
			requestFrom : 'Special Page',
			requestPageId : '${specialPageId}',
			task:"getSelectedContent"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedContentAndRelatedGalleriesAction.action?"+rparam;					callAjaxForSpecialPage(jsObj,url); 
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
	
	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${specialPageVO.heading} - '+result.contentType;

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
		str += '<div class="main-mbg" style="width:850px;border-radius:0px 0px 0px 0px;">Other '+galType+' gallaries Of ${specialPageVO.heading}</div><div class="main-bbg"/></div>';
		
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
	str += '<a href="javascript:{}" onClick="shareInFacebook(\'www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}&contentId='+preContentId+'\')"><img alt="Share in Facebook" src="images/FBshare.jpg" title="Click here to Share this in Facebook"></img></a>';
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

 function openFile(filePath){

	window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
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
		    else if(jsObj.task == "getFirstThreePhotoRecords")
		    {
              buildFirstThreePhotoRecords(myResults);
		    }
			else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="new")
			{
               showPhotosInInitialGallary(myResults);
			}
			else if(jsObj.task == "getPhotosInAGallary" && jsObj.value=="old")
			{
               showPhotosInAGallary(myResults);
			}
			else if(jsObj.task == "getPhotoGallaryWithOutGallerySizeZero")
			{
               buildCandidatePhotoGallary(myResults);
			}

		}catch(e){   
			// alert("Invalid JSON result" + e);   
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
function navigateToConstituencyPageFrmSpeclPge(elmtId,alertId)
{
 var constSelectEl = document.getElementById(elmtId);
 var alertEl = document.getElementById(alertId);
 var constSelectElVal = constSelectEl.options[constSelectEl.selectedIndex].value
 alertEl.innerHTML = '';
 if(constSelectElVal == 0)
 {
	 alertEl.innerHTML = '<font color="red">Please Select Constituency</font>';
	 return;
 }
 window.location = "constituencyPageAction.action?constituencyId="+constSelectElVal;

} 
function navigateToDistrictPageFrmSpeclPge(elmtId,alertElmtId)
{
 var distSelectEl = document.getElementById(elmtId);
 var alertEl = document.getElementById(alertElmtId);
	alertEl.innerHTML = '';
 var distSelectElVal = distSelectEl.options[distSelectEl.selectedIndex].value;
 var distSelectElText = distSelectEl.options[distSelectEl.selectedIndex].text;
if(distSelectElVal == 0)
 {
 alertEl.innerHTML = '<font color="red">Please Select District</font>';
 return;
 }
 else
 alertEl.innerHTML = '';
 window.location="districtPageAction.action?districtId="+distSelectElVal+"&districtName="+distSelectElText;

} 
function getFirstThreePhotoRecords(){
	
	   var jsObj =
			{   
			    time : timeST,
			    specialPageId : specialPageId,
				task:"getFirstThreePhotoRecords"
			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getFirstThreePhotoRecordsAction.action?"+rparam;						
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
function photoGallaryPopUp(){
	
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
			specialPageId:specialPageId,
			startRecord:0,
			maxRecord:20,
			task:"getPhotoGallaryWithOutGallerySizeZero"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "showPhotoGallaryAction.action?"+rparam;						
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


$(document).ready(function() {
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
</head>

<body>
<!--CONTENT MAIN SECTION START-->
<!--PROFILE LEFT CONENT SECTION START-->

<div class="main-mbg">${specialPageVO.heading}
<span style="margin-top:10px;margin-right:30px;float:right">
<g:plusone size="medium"></g:plusone>

<script type="text/javascript">
  (function() {
    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/plusone.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
  })();
</script>
</span>

<span style="margin-top:10px;float:right">
<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}">
Tweet</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
</script>
</span>

<span style="margin-top:10px;margin-right:18px;float:right">
<a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a> 
</span>
</div>
<s:if test="customPages != null && customPages.size() > 0">
<s:iterator value="customPages" var="custom"> 
<s:if test="#custom.type == 'banner'">
	<div>
		<jsp:include page='${custom.name}' flush="true"/> 
	</div>
</s:if>
</s:iterator>
</s:if>

      <div class="profile-left-sec">
        <div class="pl-cont-sec">
          <div class="ptd-sec"><img src="${specialPageVO.eventImagePath}" alt=""/> <span class="tc-tf pa-fi"></span>
            <label class="c-red"></label>
            </div>
          <div class="clear"></div>
          <div class="pl-sub-fields"> <span style="margin-left:14px;"></span>
            <ul>
             <!-- <li><a href="#">About</a><span></span></li> -->
              <li><a href="javascript:{getTotalNews('totalNews')}">News and Events</a><span></span></li>
              <li><a href="javascript:{videoGallaryPopUp();}">Videos</a><span></span></li>
              <li><a href="#">Photo Gallery</a><span></span></li>
               <!--<li><a href="#">elections</a><span></span></li>
              <li><a href="#">Developments</a><span></span></li> -->
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
              <h2 class="ea-fc-title">email alert <span class="blue-down-arrow">
               <img	src="images/icons/candidatePage/blue-down-arrow.png" alt="" /></span></h2>
                  <div class="ea-fc-cont-sec">Subscribe an email alert to get
                         updates of <br />
                  <span class="li-red">${specialPageVO.heading}</span> <input name="" type="text" id="emailId" class="ea-text-fields" value="your email"
					onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value=''; document.getElementById('alertMsg').innerHTML = '';" />

			<div id="alertMsg" style="dispaly:block"></div>
			<div class="pl-sub-but"><a onclick="validateEmailField()"
				href="javascript:{};"><strong>Subscribe Alert</strong></a></div>

		</div>
		</div>

<!--EMAIL ALERT SECTION END--></div>
<s:if test="isAdmin == 'true'">
<div><a href="specialPageManageAction.action?specialPageId=${specialPageId}">Manage Special Pages</a></div>
</s:if>
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
          
			<s:if test="customPages != null && customPages.size() > 0">
			<s:iterator value="customPages" var="custom"> 
				<s:if test="#custom.type == 'center_div'">
					<div style="width:435px;">
						<jsp:include page='${custom.name}' flush="true"/> 
					</div>
				</s:if>
			</s:iterator>
			</s:if>
			<!--ELECTION PROFILE SECTION START--> 

		 
            <!--ELECTION PROFILE SECTION END--> 
          
            <!--PHOTO GALLERY SECTION START-->
          
            <div class="pm-inner-cont-sec">
           
             <div id="photoGallaryDiv"> </div>
            
            </div>
          
            <!--PHOTO GALLERY SECTION END-->
          <span style="background-color: #ED5B21; color: #FFFFFF; font-weight: bold;padding: 5px;">Share Your Views On 2012 Assembly Elections</span>
		  <div class="fb-comments" data-href="http://www.partyanalyst.com/specialPageAction.action?specialPageId=${specialPageId}" data-num-posts="500" data-width="430" style="margin-top: 9px;"></div>
		  </div>
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
	 <div class="more"><a onClick="videoGallaryPopUp(<s:property value ='fileVOList.size'/>);" href="javascript:{};">More</a></div>
	 </s:if>

	<div id="showContentDiv">
	<div id="showContentDivInnerDiv"></div>
	</div>
	<div id="videoGallaryPopUpDiv"></div>

 <!-- VIDEOS SECTION END--> 

	<s:if test="customPages != null && customPages.size() > 0">
		<s:iterator value="customPages" var="custom"> 
			<s:if test="#custom.type == 'right_navigation'">
				<div style="width:300px;">
					<jsp:include page='${custom.name}' flush="true"/> 
				</div>
			</s:if>
		</s:iterator>
	</s:if>

   <div id="showProfile"></div>
      <!--PROFILE RIGHT CONTENT SECTION END--> 
      
    <!--CONTENT SECTION END--> 
 
 <!--CONTENT MAIN SECTION END--> 
<div id="loginPopupDiv"></div>

<script type="text/javascript">
  
getTotalNews('getFirstFourNewsRecordsToDisplay');
displayProfile();
getFirstThreePhotoRecords();
<s:if test="contentId != null">
	showSelectedContentAndRelatedGalleries();
</s:if>
</script>
</body>
</html>