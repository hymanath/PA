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
<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">
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

.pr-cont-sec
{
	margin-left: 10px;
}

.pft-sec {
    color: #777777;
    float: left;
    font-size: 20px;
    line-height: 24px;
    padding: 32px 0 16px 21px;
    width: 244px;
}
.popupcontainer {
    	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.25), 0 1px 5px 3px rgba(0, 0, 0, 0.05), 0 5px 4px -3px rgba(0, 0, 0, 0.06);
    	background-color: #FFFFFF;
    	margin: 9px auto 10px;
    	max-width: 780px;
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
    width: 204px;
}
.profile-mid-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -211px top transparent;
    display: inline-block;
    float: left;
    padding-top: 5px;
    position: relative;
    width: 441px;
}
.profile-right-sec {
    background: url("../../images/icons/candidatePage/content-crv-bgs.png") no-repeat scroll -683px top transparent;
    float: left;
    margin-left: 2px;
    padding-top: 5px;
    width: 300px;
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
.pagenationStyle{
  background-color:#F5371C;color:#fff;
}
.paginatorElmtClass a {
    border: 1px solid #ADADAD;
    font-size: 11px;
    margin: 0 3px;
    padding: 3px;
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


#videoGallaryPopUpDiv {
    height: 526px;
    min-height: 0;
    width: auto;
}</style>
<script type="text/javascript">
var isSubscribed = '${sessionScope.isSubscribed}';
var userName = '${sessionScope.UserName}';

var specialPageId = '${specialPageId}';
var showContentResultList = null;


function unSubscribeBtnBuild()
{
$('#subscribeSpan').html('');

var str='';
str+='Unsubscribe to stop<br/>updates of<br />';
str+='<span class="li-red">${specialPageVO.heading}</span><br/>';
str+='<input  class="unsubscribebtn" type="button" onclick="unsubscriptionDetails()" value="UNSUBSCRIBE"/>';

$('#subscribeSpan').html(str);
subscribeAlert();
}

function subscribeBtnBuild()
{
$('#subscribeSpan').html('');
var str='';
str+='Subscribe to get<br/>updates of<br />';
str+='<span class="li-red">${specialPageVO.heading}</span><br/>';
str+='<input  class="subscribebtn" type="button" onclick="subscriptionDetails()" value="SUBSCRIBE"/>';

$('#subscribeSpan').html(str);
unSubscribeAlert();
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
			id: specialPageId,
			task: "subscriptionDetails",
			page:"specialPage"
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
			id: specialPageId,
			task: "unsubscriptionDetails",
			page:"specialPage"
	}
   
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "candidateEmailAlertsForUserAction.action?"+rparam;						
   callAjax(jsObj,url);
 }
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
	document.getElementById("contentAjaxCallImg").style.display="block";
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
	
	document.getElementById('ui-dialog-title-showContentDiv').innerHTML = '<font color="darkgreen"><b>${specialPageVO.heading} - '+result.contentType;

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
		str += '<div class="popupcontainer" id="nextPartImage" style="width:500px;text-align:center;">';
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

			if(i != 0)
			{
				str += '<td><a href="javascript:{}" title="Click here to View -  '+result.relatedGalleries[0].filesList[i-1].title+'" onclick="buildContentDetailsOfSelected('+result.relatedGalleries[0].filesList[i].contentId+','+result.relatedGalleries[0].filesList[i-1].contentId+')"><img src="images/icons/jQuery/previous.png" class="newsImage" /></a></td>';
			}
			
			str += '<td><div class="popupcontainer" id="nextPartImage" style="width:700px;text-align:center;"><img alt="'+result.relatedGalleries[0].filesList[i].title+'" title="'+result.relatedGalleries[0].filesList[i].description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+result.relatedGalleries[0].filesList[i].fileVOList[0].fileVOList[0].path+'" /></div></td>';

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
		
		str += '<div class="popupcontainer" style="overflow:auto;width:880px;max-width:850px;">';
		str += '<Table>';
		
		for(var i=0;i<result.otherGalleries.length;i++)
		{
			if(i%4 == 0)
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

			if(i%4 == 3)
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
function showNextNewsPart(fileSourceLanguageId,orderNo,path,type)
{
  for(var i in selectedContentFile.fileVOList)
  {
    if(selectedContentFile.fileVOList[i].fileSourceLanguageId == fileSourceLanguageId)
	{
	  if(type != 'video')
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle" style="max-width:780px;max-length:800px;" src="'+path+'" />';
	  else
	   var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+path+'" frameborder="0" allowfullscreen="true"></iframe>';
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
	    var str='<img alt="'+selectedContentFile.title+'" title="'+selectedContentFile.description+'" align="middle"  style="max-width:780px;max-length:800px;" src="'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" />';
	  else
	    var str='<iframe width="500" height="396" src="http://www.youtube.com/embed/'+selectedContentFile.fileVOList[m].fileVOList[0].path+'" frameborder="0" allowfullscreen="true"></iframe>';
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
			else if(jsObj.task == "subscriptionDetails")
			{
				unSubscribeBtnBuild();
			}
			else if(jsObj.task == "unsubscriptionDetails")
			{
				subscribeBtnBuild();
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
		callAjaxForSpecialPage(jsObj,url);  
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
		str+='<div class="popupcontainer"><h4><div style="margin: 10px;color:ActiveCaption;">Please login to subscribe </div></div>';		document.getElementById("logInDiv").innerHTML = str;
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
</head>

<body>
<!--CONTENT MAIN SECTION START-->
<!--PROFILE LEFT CONENT SECTION START-->
<!--<div style="text-align:center;margin-bottom:10px;">
<script type="text/javascript"><!--
google_ad_client = "ca-pub-0938408694174139";
/* PartyPageHeader */
google_ad_slot = "2678494123";
google_ad_width = 728;
google_ad_height = 90;
//-->
<!--</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>
</div> -->
<a id="inline" href="#fancydivbox" style="displaynone"></a>
<div id="promodiv" style="display:none;">
	<div id="fancydivbox">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
</div>

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
 <div id="logInDiv"></div>
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
             
              <li><a href="javascript:{getTotalNews('totalNews')}">News and Events</a><span></span></li>
              <li><a href="javascript:{videoGallaryPopUp();}">Videos</a><span></span></li>
              <li><a href="javascript:{photoGallaryPopUp();}">Photo Gallery</a><span></span></li>
               
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
                  <div class="ea-fc-cont-sec" style="font-size:13px;">
                 
			<span id="subscribeSpan">
 
			<s:if test="isSubscribed == true ">
			Unsubscribe to stop<br/>
			updates of<br />
			<span class="li-red">${specialPageVO.heading}</span><br/>
            <input  class="unsubscribebtn" type="button" onclick=
            "unsubscriptionDetails()" value="UNSUBSCRIBE"/>
            </s:if>
			
			<s:else>
			Subscribe and get <br/>
			updates of<br />
			<span class="li-red">${specialPageVO.heading}</span><br/>
			<input  class="subscribebtn" type="button" onclick=
			"subscriptionDetails()" value="SUBSCRIBE"/>
 			</s:else>

            

			</span>
				  <!--<input name="" type="text" id="emailId" class="ea-text-fields" value="your email"
					onblur="if(this.value=='')this.value=this.defaultValue;" onfocus="if(this.value==this.defaultValue)this.value=''; document.getElementById('alertMsg').innerHTML = '';" />

			<div id="alertMsg" style="dispaly:block"></div>
			<div class="pl-sub-but"><a onclick="validateEmailField()"
				href="javascript:{};"><strong>Subscribe Alert</strong></a></div>-->

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
          <span style="background-color: #ED5B21; color: #FFFFFF; font-weight: bold;padding: 5px;">Share Your Views On ${specialPageVO.heading}</span>
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
	 <div class="more"><a onClick="videoGallaryPopUp(<s:property value ='fileVOList.size'/>);" href="javascript:{};" style="margin-bottom:30px;">More</a></div>
	 </s:if>

	<div id="showContentDiv">
	<div id="contentAjaxCallImg" ><img src="images/icons/goldAjaxLoad.gif"></div>
	<div id="showContentDivInnerDiv"></div>
	</div>
	<div id="videoGallaryPopUpDiv" style="width:auto;"></div>

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
var loadingFirstTime = '${sessionScope.loadingFirstTime}';
$(document).ready(function(){
	if(loadingFirstTime  == 'true'){
		$("#inline").fancybox();
		$("#inline").trigger("click");
		}
	});

<%
	session.setAttribute("loadingFirstTime", "false");
%>
<s:if test="contentId != null">
	showSelectedContentAndRelatedGalleries();
</s:if>
</script>
</body>
</html>