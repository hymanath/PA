<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${candidateVO.candidateName} - Profile, Constituency, Photos, Videos, Political & Election Info</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script src="http://www.google.com/uds/api?file=uds.js&v=1.0"
    type="text/javascript"></script>
<link href="http://www.google.com/uds/css/gsearch.css"
    rel="stylesheet" type="text/css"/>
<script src="http://www.google.com/uds/solutions/videobar/gsvideobar.js"
    type="text/javascript"></script>
<link href="http://www.google.com/uds/solutions/videobar/gsvideobar.css"
    rel="stylesheet" type="text/css"/>


<script type="text/javascript" src="js/candidatePage/candidatePage.js"></script>
<script type="text/javascript" src="js/candidatePage/carousel.js"></script>
<script type="text/javascript" src="js/SWFObject/swfobject.js" ></script>

<link rel="stylesheet" type="text/css" href="styles/candidatePage/candidatePage.css">	

<script>
		!window.jQuery && document.write('<script src="js/fancybox/jQuery/jquery-1.4.3.min.js"><\/script>');
</script>
	
	<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<style type="text/css">
#btnStyle {
    background:none repeat scroll 0 0 #335291;
    color:#FFFFFF;
    font-weight:bold;
    margin-bottom:5px;
    margin-top:5px;
    padding:2px;
    width:125px;	
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

.imgFieldset
{
	-moz-border-radius: 4px 4px 4px 4px;
	border			: 4px solid #9F81F7;
    margin-bottom	: 10px;
	margin-top		: 5px;
}

.gallaryImg
{
	width  : 150px;
	height : 130px;
}

.tableStyle {
    width:80%;
}
#mainTable{
    width:67%;
}
#titleStyle{
   color:navy;
   font-weight:bold;
}

</style>
<script type="text/javascript">
		google.load("elements", "1", {packages : ["newsshow"]});
		var timeST = new Date().getTime();
		var candidateId = '${candidateId}';
 
 function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText);
         if(jsObj.task == "getCandidatePhotoGallaryDetail")
			{
               buildCandidatePhotoGallary(myResults);
			}
		else if(jsObj.task == "getPhotosInAGallary")
			{
               showPhotosInAGallary(myResults);
			}
       else if(jsObj.task == "getCandidateNewsGallaryDetail")
			{
               buildCandidateGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getNewsInAGallary")
			{ 
               buildGallary(myResults,"zero","news");
			}
		else if(jsObj.task == "getDevelopmentsInAGallary")
			{ 
               buildGallary(myResults,"DevelopmentsGallaryDiv","developments");
			}
		else if(jsObj.task == "getCandidateDevelopmentGallaryDetail")
			{ 
               buildCandidateGallary(myResults,"DevelopmentsGallaryDiv","developments");
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
function buildCandidateGallary(results,elementId,type){
  var problemRelatedImagesElmt = document.getElementById(elementId);
var str ='';
str+='<div id="content" style="width:650px;">';
str += '<fieldset class="imgFieldset">';
str+='<table width="100%" style="margin-top:10px;">';
  for(var i in results)
{
no_of_imagesPerRow = 3; 
j = i;
if(j++ % no_of_imagesPerRow == 0){
str+= '<tr style="height:220px;">';
}
var fileType = results[i].name.split(".");

if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){


str+= '<td width="33%"><table class="tableStyle">';
str+= '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].gallaryName+'</b></font></div></tr></td>';

str+= '<tr><td><a  href="javascript:{}" title="'+results[i].gallaryDescription+'" >';

if(fileType[(fileType.length-1)] == "pdf"  ){
str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 	onclick="getFilesInAGallary(\''+results[i].gallaryId+'\',\''+type+'\')"/>';
}
else if(fileType[(fileType.length-1)] == 'text'){
str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 	onclick="getFilesInAGallary(\''+results[i].gallaryId+'\',\''+type+'\')"/>';
}
else if(fileType[(fileType.length-1)].indexOf('word') != -1){
str+= '<img alt="" src="images/doc_images/wordImage.png" height="100px" onclick="getFilesInAGallary(\''+results[i].gallaryId+'\',\''+type+'\')"></img>';
}

str+= '</a></td></tr>';
str+= '<tr><td><div><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
str+= '<tr><td><div><b>Gallery Size: ('+results[i].sizeOfGallary+' )</b></div></td></tr></table></td>';


}
else{
str+= '<td><table class="tableStyle">';
str+= '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].gallaryName+'</b></font></div></tr></td>';
str+= '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img alt="" src="'+results[i].path+'" height="100px" onclick="getFilesInAGallary(\''+results[i].gallaryId+'\',\''+type+'\')"/></a></td></tr>';
str+= '<tr><td><div><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
str+= '<tr><td><div><b>Gallery Size: ('+results[i].sizeOfGallary+' )</b></div></td></tr></table></td>';

}

if(j % no_of_imagesPerRow == 0){
str+= '</tr>';
}

}
str+= ' </table>';
str += ' </fieldset>';
str+='</div>';
problemRelatedImagesElmt.innerHTML = str;

}

function getFilesInAGallary(gallaryId,type){
    var task = '';
  if(type=="news")
    task="getNewsInAGallary";
   if(type=="developments")
    task="getDevelopmentsInAGallary";
  var jsObj =
		{   
		    time : timeST,
			gallaryId:gallaryId,
			task:task
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
}
function buildGallary(results,elementId,type){
   var problemRelatedImagesElmt = document.getElementById(elementId);
var str ='';
str+='<div id="content" style="width:650px;">';
str += '<fieldset class="imgFieldset">';
str+='<table width="100%" style="margin-top:10px;">';
str+='<tr><td>';
if(type=="news")
str+='<input type="button" value="Back To Gallery"  id="btnStyle" onclick="showCandidateNewsGallary();" />';
if(type=="developments")
str+='<input type="button" value="Back To Gallery"  id="btnStyle" onclick="showDevelopmentsGallary();" />';
str+= '</td></tr>';
for(var i in results)
{
no_of_imagesPerRow = 3; 
j = i;
if(j++ % no_of_imagesPerRow == 0){
str+= '<tr style="height:220px;">';
}
var fileType = results[i].name.split(".");

if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){
str+= '<td width="33%"><table class="tableStyle">';
str+= '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileTitle1+'</b></font></div></tr></td>';
str+= '<tr><td><a  href="javascript:{}" title="'+results[i].fileDescription1+'" >';

if(fileType[(fileType.length-1)] == "pdf"  ){
str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 	onclick="javascript:{openFile(\''+results[i].path+'\')}"/>';
}
else if(fileType[(fileType.length-1)] == 'text'){
str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 	onclick="javascript:{openFile(\''+results[i].path+'\')}"/>';
}
else if(fileType[(fileType.length-1)].indexOf('word') != -1){
str+= '<a href="'+results[i].path+'"><img alt="" src="images/doc_images/wordImage.png" height="100px" ></img></a>';
}

str+= '</a></td></tr>';
str+= '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';


}
else{
str+= '<td><table class="tableStyle">';
str+= '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileTitle1+'</b></font></div></tr></td>';
str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" height="100px" /></a></td>';
str+= '<tr><td><div><b>'+results[i].fileDescription1+'</b></div></td></tr>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';

}

if(j % no_of_imagesPerRow == 0){
str+= '</tr>';
}

}
str+= ' </table>';
str += ' </fieldset>';
str+='</div>';
problemRelatedImagesElmt.innerHTML = str;

$("a[rel=photo_gallery]").fancybox({
'transitionIn'		: 'none',
'transitionOut'		: 'none',
'titlePosition' 	: 'over',
'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
	return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
}
});

}
function showCandidateNewsGallary(){
 var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			task:"getCandidateNewsGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url); 
 }
function showPhotoGallary(){
    var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			task:"getCandidatePhotoGallaryDetail"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function showDevelopmentsGallary(){
   var jsObj =
		{   
		    time : timeST,
			candidateId:candidateId,
			task:"getCandidateDevelopmentGallaryDetail"
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
   str+='<input type="button" value="Back To Gallery"  id="btnStyle" onclick="showPhotoGallary();" />';
   str+= '</td></tr>';
   for(var i in results)
   {
     no_of_imagesPerRow = 3; 
     j = i;
     if(j++ % no_of_imagesPerRow == 0){
       str+= '<tr style="height:220px;">';
     }
     str+= '<td width="33%"><table class="tableStyle">';
	 str += '<tr><td><div><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileTitle1+'</b></font></div></td></tr>';
     str+= '<tr><td><a rel="photo_gallery" href="'+results[i].path+'" title="'+results[i].fileDescription1+'"><img alt="" src="'+results[i].path+'" class="gallaryImg" height="100px" /></a></td></tr>';
	 str += '<tr><td><div><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+results[i].fileDescription1+'</b></div></td></tr>';
     str+= '<tr><td><div class="fancyBoxImageDivTitle"></div></td></tr></table></td>';
     if(j % no_of_imagesPerRow == 0){
       str+= '</tr>';
     }

   }
   str+= ' </table>';
   str += ' </fieldset>';
   str+='</div>';
   document.getElementById("photoGallaryDiv").innerHTML = str;

   $("a[rel=photo_gallery]").fancybox({
     'transitionIn'		: 'none',
     'transitionOut'		: 'none',
     'titlePosition' 	: 'over',
     'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
        return '<div id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; <span>' + title : '') + '</span></div>';
     }
   });
}

function buildCandidatePhotoGallary(results)
{
	var str ='';

	if(results.length<=0)
	{
		str+='<b>&nbsp;No Photo Galleries Found </b>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
	}
	else
	{
		str+='<div id="content" style="width:650px;">';
		
		str += '<table style="margin:5px;width:40%;margin-left:50px;">';
		str += '<tr>';
		str += '	<td><input type="button" class="imageButton" value="Create Gallary"></td>';
		str += '	<td><input type="button" class="imageButton" value="Upload photos"></td>';
		str += '</tr>';
		str += '</table>'

		str += '<fieldset class="imgFieldset">';
		str +='<table width="100%" style="margin-top:10px;">';
		
		for(var i in results)
		{
			no_of_imagesPerRow = 3; 
			j = i;

			if(j++ % no_of_imagesPerRow == 0)
				str += '<tr style="height:220px;">';
			
			str += '<td width="33%">';
			str += '<table class="tableStyle">';
			str += '<tr><td><div align="center"><font style="color:#FF0084;font-size:13px;font-family: verdana,arial;"><b>'+results[i].gallaryName+'</b></font></div></td></tr>';
			str += '<tr><td><a href="javascript:{}" title="'+results[i].gallaryDescription+'"><img src="'+results[i].path+'" class="gallaryImg" onclick="getCompleteGallaries(\''+results[i].gallaryId+'\')"/></a></td></tr>';
			str += '<tr><td><div align="center"><b>'+results[i].gallaryDescription+'</b></div></td></tr>';
			str+= '<tr><td><div align="center"><b>Gallery Size: ('+results[i].sizeOfGallary+' )</b></div></td></tr>';
			str += '</table>';
			str += '</td>';
			
			if(j % no_of_imagesPerRow == 0)
				str+= '</tr>';
		
		}
		str += ' </table>';
		str += ' </fieldset>';
		str+='</div>';
		document.getElementById("photoGallaryDiv").innerHTML = str;
	}
}

function getCompleteGallaries(gallaryId){
    var jsObj =
		{ 
            time : timeST,
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

</script>

</head>
<body class="yui-skin-sam">
<div id="CandidatePageMainDiv">
	
	<!--<div id="candidateImgFlash">
		<img src="images/candidates/andhra_pradesh/chandra_babu_naidu.jpg"/>
	</div> -->

	<div id="candidatePageLayoutDiv">
		<div id="cand_elect_div" class="yui-skin-sam">
			<div id="cand_elec_div_panel"></div>
			<div id="cand_image_div_panel"></div>
		</div>
	</div>	

	<div id="candidatePageLeftContentDiv">
		<div id="candidatePageLeftContentDiv_head"></div>
		<div id="candidatePageLeftContentDiv_body" class="yui-skin-sam">
			<div id="candidatePageLeftContentDiv_Image">
				<img id="candidateImage" onerror="setDefaultImage(this)" height="200" width="200" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />${candidateVO.candidateName}.jpg" >
				<div id="candidateProfile" class ="candDetailsStyle">
				 </div>
			</div>
			<div id="candidatePageLeftContentDiv_leftNavLinks"></div>
		</div>
	</div>

	<div id="candidatePageCenterContentDiv">
		<div id="candidatePageContent_header" class="leftAlignText">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="candidatePageHeader_center" style="width:651px;"><span>${candidateVO.candidateName}</span>	
					<span id="candidatePageHeader" style="color:#B40404"></span></div>
				</td>
					<td><img src="images/icons/districtPage/header_right.gif"/></td>
				</tr>
			</table>			
		</div>
		<div id="candidatePageContent_body" class="leftAlignText">
			<%
				java.lang.String staticURL = (java.lang.String) request.getAttribute("candidateURLString");

				java.lang.String profileURL = staticURL + "/profile.jsp";
				java.lang.String constituencyURL = staticURL + "/constituency.jsp";
				java.lang.String newsURL = staticURL + "/news.jsp";
				java.lang.String developmentsURL = staticURL + "/developments.jsp";							
				java.lang.String speechesURL = staticURL + "/speeches.jsp";						
				
				java.lang.String photosURL = staticURL + "/photo.jsp";
				java.lang.String videosURL = staticURL + "/video.jsp";
				java.lang.String contactURL = staticURL + "/contact.jsp";
			%>
			
			<div id="candidatePoliticalCareer_main">
				<div id="candidatePoliticalCareer_head" class="centerContentHeader">Election Profile	</div>
				<div id="candidatePoliticalCareer_body">
					<table>
						<tr>
							<td style="vertical-align:top;"><span id="candidatePoliticalInfo"></span></td>
							<td style="vertical-align:top;"><span id="candidatePartyFlag"></span></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div id="candidateStaticInfo_main">
				<div id="candidateStaticInfo_head" class="centerContentHeader"> ${candidateVO.candidateName}'s Profile Info</div>
				<!-- Profile Info Div-->
				<div id="candidatePageContent_body_profileMain">
					<jsp:include page="<%= profileURL%>" flush="true" />
				</div>
				
				<!-- Constituency Info Div-->
				<div id="candidatePageContent_body_constituencyMain" class="candidateStaticContentDiv">
					<jsp:include page="<%= constituencyURL%>" flush="true"/>				
				</div>
				
				<!-- News Info Div-->
				<div id="candidatePageContent_body_NewsMain" class="candidateStaticContentDiv" style="overflow: scroll;overflow-x:hidden;display: block;">
				    <div id="zero" style="position:relative;left:15px;padding-top:15px;"> </div>
					<div id="one" style="position:relative;left:-165px;padding-top:15px;"> </div>
					<div id="two" style="position:relative;left:-165px;padding-top:15px;"> </div>					
				</div>

				<!-- Developments Info Div-->
				<div id="candidatePageContent_body_DevelopmentsMain" class="candidateStaticContentDiv" style="overflow:scroll;overflow-x:hidden;">
					 <div id="DevelopmentsGallaryDiv" width="100%" ></div>		
				</div>

				<!-- Speeches Info Div-->
				<div id="candidatePageContent_body_SpeechesMain" class="candidateStaticContentDiv">
					<jsp:include page="<%= speechesURL%>" flush="true"/>				
				</div>

				<!-- Photo Info Div-->
				
				<div id="candidatePageContent_body_photoMain" class="candidateStaticContentDiv" style="overflow:scroll;overflow-x:hidden;">
				   
					<div id="photoGallaryDiv" width="100%" ></div>	
				   
				</div>

				<!-- Videos Info Div-->
				<div id="candidatePageContent_body_videosMain" class="candidateStaticContentDiv">
					
				  <marquee scrolldelay="150" onmouseover="this.stop()" onmouseout="this.start()">
					<table width="100%">
						 <tr>
							<td width="50%" ><div id="videoBarOne"> </div></td>
							<td width="39%" align="left"><div id="videoBartwo"></div></td>
						</tr>
					</table>
				  </marquee>
					<div id="ytVideoPlayer"> </div>
				
					<!--<jsp:include page="<%= videosURL%>" flush="true"/>-->
				</div>	
			<!-- Contact Info Div-->
				<div id="candidatePageContent_body_contactMain" class="candidateStaticContentDiv">
					<jsp:include page="<%= contactURL%>" flush="true"/>
				</div>	
			</div>
		</div>			
	</div>

	<!--<div id="candidatePoliticalCareer">
		<div id="candidatePoliticalCareer_head">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
					<td><div class="candidatePageHeader_center" style="width:895px;"><span> ${candidateVO.candidateName}'s Election Profile </span></div></td>
					<td><img src="images/icons/districtPage/header_right.gif"/></td>
				</tr>
			</table>
		</div>
		<div id="candidatePoliticalCareer_body">
			
		</div>
	</div>-->
	
	<div id="candidatePageBottomContentDiv">
		<table width="100%">
			<tr>
				<td>
					<div id="candidate_images_div" class="bottomContentDiv">
						<div id="candidate_images_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="candidatePageHeader_center" style="width:260px;"><span> Photo Gallery </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="candidate_images_body" class="bottomContentDiv_content">
							<div class="bottomContentDiv_msg">Welcome to photo gallery.This gallery has a collection of photos divided into different categories.</div>
							<div class="bottomContentDiv_links">
								<table>
									<tr>
									<td><img src="images/icons/candidatePage/camera.png"/></td>
									<td style="vertical-align:middle;"><span class="bottomContentDiv_links_view" onclick="showCandidateNewsGallary();showPhotoGallary();showDevelopmentsGallary();showLeftMenuContent('photo');">View</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
				<td>
					<div id="candidate_video_div" class="bottomContentDiv">
						<div id="candidate_video_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="candidatePageHeader_center" style="width:260px;"><span> Video Gallery </span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="candidate_video_body" class="bottomContentDiv_content">
							<div class="bottomContentDiv_msg">Welcome to video gallery.This gallery has a collection of videos divided into different categories.</div>
							<div class="bottomContentDiv_links">
								<table>
									<tr>
									<td><img src="images/icons/candidatePage/video.png"/></td>
									<td style="vertical-align:middle;"><span class="bottomContentDiv_links_view"  onclick="showLeftMenuContent('video')">View</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
				<!--<td>
					<div id="writeTo_candidate_div" class="bottomContentDiv">
						<div id="writeTo_candidate_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="candidatePageHeader_center" style="width:180px;"><span> Write To Me</span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="writeTo_candidate_body" class="bottomContentDiv_content">
							<div class="bottomContentDiv_msg">
								
							</div>
							<div class="bottomContentDiv_links">
								<table>
									<tr>
									<td><img src="images/icons/candidatePage/note.ico"/></td>
									<td style="vertical-align:middle;"><span class="bottomContentDiv_links_view">Write</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>-->
				<td>
					<div id="contact_candidate_div" class="bottomContentDiv">
						<div id="contact_candidate_head">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<td width="30px"><img src="images/icons/districtPage/header_left.gif"/></td>
									<td><div class="candidatePageHeader_center" style="width:260px;"><span> Contact Me</span></div></td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
							</table>
						</div>
						<div id="contact_candidate_body" class="bottomContentDiv_content">
							<div class="bottomContentDiv_msg">
								This feature allows the user to communicate with the person directly via email.This section also provides the address for communication and important telephone numbers.
							</div>
							<div class="bottomContentDiv_links">
								<table>
									<tr>
									<td><img src="images/icons/candidatePage/contact.png"/></td>
									<td style="vertical-align:middle;"><span class="bottomContentDiv_links_view" onclick="showLeftMenuContent('contact')">Contact</span></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>

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

	initializeCandidatePage();
    showLeftMenuContent('video');
	//showLeftMenuContent('News/Events');
candidateProfileInfo();
showPhotoGallary();
showCandidateNewsGallary();
showDevelopmentsGallary();
</script>
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

</body>
</html>
  