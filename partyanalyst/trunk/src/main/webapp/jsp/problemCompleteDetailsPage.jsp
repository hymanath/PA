<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst-Know | Analyze | Act</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	<script type="text/javascript" src="js/problemCompleteDetails.js"></script>
	
	<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" href="styles/style.css" />

<!-- JQuery files (End) -->
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<script type="text/javascript">
var id = '${problemHistoryId}';
var logInStat = '${sessionScope.loginStatus}';
var userType = '${sessionScope.UserType}';

function showOrHideProblemFilesDiv()
{
	
var fileUploadDivEle = document.getElementById("problemUploadFilesMainOuterDiv");
var hideRadioDivEle= document.getElementById("hideTd");
if(fileUploadDivEle.style.display =='none')
	fileUploadDivEle.style.display = 'block';
else
    fileUploadDivEle.style.display = 'none';
    hideRadioDivEle.style.display = 'none';
}

 function alltrim(str) {
                return str.replace(/^\s+|\s+$/g, '');
            }

			function postFilesAndImages()
{
   if(!uploadFormValidation()){
	var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				getMessage();				
			}
		};

	
	YAHOO.util.Connect.setForm('uploadPicForm',true);
	YAHOO.util.Connect.asyncRequest('POST', 'postImagesAndFilesAction.action', uploadHandler);
	}
	return;
   }

      function getMessage()
		{
			alert("File Uploaded Successfully...");
			emptyFields();
		}

function uploadFormValidation()
{
	
	var elmt1 = document.getElementById("titleField");
	var elmt2 = document.getElementById("fileDescription");
	var elmt3 = document.getElementById("userImage");
	
	var textFieldValue = elmt1.value;
	var textAreaValue = elmt2.value;
	var fileValue = elmt3.value;
	document.getElementById("alertMsg1").innerHTML ='';
   	document.getElementById("alertMsg2").innerHTML ='';
    document.getElementById("alertMsg3").innerHTML ='';


	if(alltrim(elmt1.value) ==''){
		document.getElementById("alertMsg1").innerHTML ='<font color="red">Please enter Title</font>';
		
		return true;
	}
	
	if(alltrim(elmt2.value) ==''){
		document.getElementById("alertMsg2").innerHTML ='<font color="red">Please enter Description</font>';
		
		return true;
	}

	if(alltrim(elmt3.value) ==''){
		document.getElementById("alertMsg3").innerHTML ='<font color="red">Please enter File</font>';
		
		return true;
	}
	
   }

   function emptyFields()
   {
    document.getElementById("titleField").value='';
	 document.getElementById("fileDescription").value='';
	 document.getElementById("userImage").value='';
   }


function callAjax(jsObj,url)
{	
		var callback = {			
		               success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task == "getProblemCompleteDetails")
							{										
								showProblemDetails(myResults, jsObj);
							}
							else if(jsObj.task =="getProblemImages")
							{
								builImagesDiv(myResults);
								builUploadImagesDiv(myResults);
							}

							
						}catch (e) {   
						  // 	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function approvalCallAjax(jsObj,url)
{	
		var callback = {			
		               success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);							
							showConfirmation(myResults,jsObj);
						}catch (e) {   
						  // 	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showConfirmation(results, obj)
{	
	var postProblemElmt = document.getElementById('postProblemDiv');
	var approvalElmt = document.getElementById('approveProblem');
	if(obj.task == 'checkApprovalStatus'){
		if(results.resultState == '0'){
			
			postProblemElmt.style.display='none';
		}
		else if((results.resultState == '1')){
			
			approvalElmt.style.display = 'none';
			var problemDetailsCellElmt = document.getElementById('problemDetailsCellId');
			problemDetailsCellElmt.width = '100%';
		
		}
	}
	else{
	var rasonTextEl = document.getElementById("rasonText");
	var rasonTextEl1 = document.getElementById("rasonText1");
	var str = '';
	if(results.resultCode == 0)
	{
		str ="Your comment has been queued for moderation by the Moderator and will be published after approval.!";
		
	} else if(results.resultCode == 1)
	{
		str = results.exceptionMsg;
	}

	$( "#popupOuterDiv" ).dialog({
		title:"",
		autoOpen: true,
		show: "blind",
		width: 300,
		minHeight:100,
		modal: true,
		hide: "explode"
	});

var elmt = document.getElementById("popuoInnerDiv");
if(elmt)
	elmt.innerHTML = str;
 
	
	if(rasonTextEl)
		rasonTextEl.value = '';
	if(rasonTextEl1)
		rasonTextEl1.value = '';
	
	getProblemAllComments(id);
	
	}
	if(obj.isAccepted == 'Accept' || obj.isAccepted == 'Reject'){
		var approvalElmt = document.getElementById('approveProblem');
		approvalElmt.style.display = 'none';
		var problemDetailsCellElmt = document.getElementById('problemDetailsCellId');
		problemDetailsCellElmt.width = '100%';
		var postProblemElmt = document.getElementById('postProblemDiv');
		postProblemElmt.style.display='block';
	}
}

function getProblemRelatedImages(pHistoryId){
    
	var jsObj ={
				pHistoryId:pHistoryId,
				task:"getProblemImages"

	           };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemImagesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function openFile(filePath){
	 
	 
window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}
function builImagesDiv(results)
{
	no_of_imagesPerRow = 4;
  if(results == null || results.length == 0)
  {
	document.getElementById('problemFilesMainOuterDiv').style.display = 'none';	
  }

 var problemRelatedImagesElmt = document.getElementById("problemFilesMainInnerDiv");
 var str ='';
 str+='<div id="content">';
 str+='<table>'
 for(var i in results)
{
no_of_imagesPerRow = 4; 
j = i;
if(j++ % no_of_imagesPerRow == 0){
str+= '<tr>';
}
var fileType = results[i].file.split(".");

if(fileType[(fileType.length-1)].indexOf('word') != -1 || fileType[(fileType.length-1)] == 'pdf' || fileType[(fileType.length-1)] == 'text'){


str+= '<td><table><tr><td>';
str+= '<a  href="javascript:{}" title="View file" ">';

if(fileType[(fileType.length-1)] == "pdf"  ){
str+= '<img alt="" src="images/doc_images/PDFImage.png" height="100px" 				onclick="javascript:{openFile(\''+results[i].pathOfFile+'\')}"/>';
}
else if(fileType[(fileType.length-1)] == 'text'){
str+= '<img alt="" src="images/doc_images/docImage.png" height="100px" 				onclick="javascript:{openFile(\''+results[i].pathOfFile+'\')}"/>';
}
else if(fileType[(fileType.length-1)].indexOf('word') != -1){
str+= '<a href="'+results[i].pathOfFile+'"><img alt="" src="images/doc_images/wordImage.png" height="100px" ></img></a>';
}

str+= '</a></td>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+results[i].title+'</div></td></tr></table></td>';


}
else{
str+= '<td><table><tr><td>';
str+= '<a rel="photo_gallery" href="'+results[i].pathOfFile+'" title="'+results[i].description+'"><img alt="" src="'+results[i].pathOfFile+'" height="100px" /></a></td>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+results[i].title+'</div></td></tr></table></td>';

}

if(j % no_of_imagesPerRow == 0){
str+= '</tr>';
}

}
 str+= ' </table>';
 str+='</div>';
 problemRelatedImagesElmt.innerHTML = str;
 
 $("a[rel=photo_gallery]").fancybox({
	
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'titlePosition' 	: 'over',
				'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
					
					return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
				}
			});

}

function builUploadImagesDiv(results)
{
  if(results == null || results.length == 0)
  {
	document.getElementById('problemUploadFilesMainOuterDiv').style.display = 'none';	
  }

 var problemRelatedImagesElmt = document.getElementById("problemUploadFilesMainInnerDiv");
 var str ='';
 
    str += '<div id="fileUploadDiv">';
	str += '<form name="uploadForm" action="postImagesAndFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadPicForm">'; 
	
	str += '	<table class="statusData_table" width="100%">';	
	str += '		<tr>';
	str += '			<td rowspan="2" width="13%" class="statusData_table_label">';
	str += '				<table width="100%" class="statusData_table_inner">';
	str += '					<tr>';
	str += '						<td width="25%"><img src="images/icons/file_upload_icon.png"></td>';
	str += '						<th>File Upload</th>';       
	str += '					</tr>';
	str += '				</table>';								
	str += '			</td>';
	str += '			<td class="statusData_table_data" width="83%">';
	str += ' <table>';
	str += '<tr>';
	str +='<center><DIV id="errorMsgDivId" class="errorDiv"></DIV></center>';
	str += ' <td style="padding-right: 62px;">Title</td> ';
	str += ' <td> <input type="text" id="titleField" name="fileTitle" size="32"/></td>';
	str+='<span id="alertMsg1"></span>';
	str += '<td style="padding-left: 22px; padding-right: 10px;">Description </td>';
	str += ' <td> <textarea name="fileDescription"  id="fileDescription" cols="20" rows="3"> </textarea></td>';
	str+='<span id="alertMsg2"></span>';
	str += ' </tr>';
	str += '</table>';
	str += '			</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<td class="statusData_table_links">';
	str+='<span id="alertMsg3"></span>';
	str += '			<table style="width:65%;">';
	str += '				<tr>';
	str += '				<td>Documents And Images</td>';
	str += '<input type="hidden" name="problemHistoryId" value='+id+'>';
	str += '				<td><input type="file" name="userImage" id="userImage"/></td>';
	str += ' <td style="padding-left: 113px;"><input type="button" style="float:none" class="button" value="Upload" onclick="postFilesAndImages()" ></td>';
	str += ' ';
	str += '				</tr></table>';	
	str += '			</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '</form>';
	str += '</div>';
 
 problemRelatedImagesElmt.innerHTML = str;
 
 
}


</script>
<style>
#problemHead
{
	
}
body {
	color:#000000;
	font-family:Arial,Helvetica,sans-serif;
	line-height:1.3em;
}
h2 {
	font-size:20px;
	font-weight:normal;
	margin:0;
	padding:0;
}
hr {
	-moz-background-clip:border;
	-moz-background-inline-policy:continuous;
	-moz-background-origin:padding;
	background:transparent url(images/hr.jpg) repeat-x scroll 0 0;
	border:medium none;
	padding:5px;
}
p{
	font-size:14px;
	margin:0;
	line-height:1.3em;	
}
.bluetext {
	color:#3B5998;
	font-weight: bold;
}
h3 {
	font-size:15px;
	font-weight:normal;
	margin:0;
	padding:0 0 5px;
}
#description, #description p {
	color:#000000;
	font-family:Verdana,Arial,Helvetica,sans-serif;
	font-size:12px;
	margin:0;
}
.bluebar_heading {
	background-color:ThreeDDarkShadow;
	color:#FFFFFF;
	font-size:15px;
	font-weight:bold;
	padding:8px 5px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
.accept
{
	background: url{images/accept.jpeg};
}
.reject
{
	background: url{images/reject.jpeg};
}
</style>
</head>
<body>
<div id="popupOuterDiv">
	<div id="popuoInnerDiv"></div>
</div>
<div id="problemDetailsPageMainDiv" style="margin:20px;">
<table width="100%" border="0">
	<tr>
		<td id="problemDetailsCellId" width="70%">
			<div id="problemDetails"></div>			
		</td>
		
		<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
		<td width="30%" valign="top">
				<div id="approveProblem" style="height:100px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  <tr>
						<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
						<td width="98%">
							<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
								<span class="headerLabelSpan2" style="text-decoration:none;">
									Give Comments 
								</span>
							</div>
						</td>
						<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
					  </tr>
					</table>
					<div class="divInfo">
						
						<div id="description">
						<p style="margin-top:10px;">If you reside in the same area and know any details about this problem, please give your comments either by Accepting<img height="20" title="Accept" width="20" src="images/icons/accept.png"/> or <img height="20" width="20" title="Reject" src="images/icons/reject.png"/> Rejecting this problem </p>
						</div>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
							<div id="alertDiv" style="color:red;font-weight:bold;margin:2px;"></div>	
							<table style="margin-top:10px;">
							<tr>
								<td><label>Comment <span style="font-weight:bold;color:red;font-size: 18px;">*</span></label></td>
								<td><textarea id="rasonText" name="reasonText" onkeyup="clearError()"></textarea></td>
							</tr>
							</table>												
							<table style="margin:25px;" border="0" cellpadding="0" cellspacing="3">	
								<tr>
									<td><a href="javascript:{}" onclick="submitHandler('Accept')"><img border="0" title="Click To Accept" height="30" width="100" src="images/accept.jpg"></a></td>
									<td><a href="javascript:{}" onclick="submitHandler('Reject')"><img border="0" title="Click To Reject" height="30" width="100" src="images/reject.jpg"></a></td>
								</tr>
							</table>
						</c:if>
						<c:if test="${sessionScope.loginStatus != 'out'}">
						<s:form action="loginInputAction" method="POST">
						<input type="hidden" name="redirectLoc" value="PROBLEM_DISCUSSION" />
						<input type="hidden" name="problemHistoryId" value="${problemHistoryId}" />
						<input type="hidden" name="logInStatus" value="${logInStatus}" />
						<p style="margin-top:10px;">Registered Users Login to post comments</p>
						<input type="submit" Value="Login"/>						
						</s:form>
						</c:if>
					</div>							
				</div>
				
		</td>
		</c:if>		
	</tr>	
</table>
<HR><BR>
<div style="padding-right: 662px;"><BUTTON 
    TYPE=BUTTON 
    onClick="showOrHideProblemFilesDiv()" style="color: #ffffFF; font-size: 10pt"><font color="blue">Upload Problem Related Files/Images </font></BUTTON></div>
<div id='problemUploadFilesMainOuterDiv' style="display:none;">
	<div id='problemUploadFilesHeaderDiv'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"></td><td width="98%"><div style="text-decoration: none;" class="productFeatureHeaderBackground_center2"><span style="text-decoration: none;" class="headerLabelSpan2">Upload Problem Related Files/Images</span></div></td><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg"></td></tr>
		</table>
	</div>

	<div id='problemUploadFilesMainInnerDiv' class="divInfo">
	
	</div>

</div>
<HR><BR>

<div id='problemFilesMainOuterDiv'>
	<div id='problemFilesHeaderDiv'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"></td><td width="98%"><div style="text-decoration: none;" class="productFeatureHeaderBackground_center2"><span style="text-decoration: none;" class="headerLabelSpan2">Problem Files/Images</span></div></td><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg"></td></tr>
		</table>
	</div>

	<div id='problemFilesMainInnerDiv' class="divInfo">
	
	</div>

</div>



<div id="postProblemDiv">
<div id="showAllPostsDiv" style="margin-top:10px;margin-bottom:10px;"></div>
<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
	<td width="98%">
	<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
	<span class="headerLabelSpan2" style="text-decoration:none;">Reply To Above Discussions</span>
	</div>
	</td>
	<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
	</tr>
</table>
<div class="divInfo">	
	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser'}">
							<div id="alertDiv1" style="color:red;font-weight:bold;margin:2px;"></div>	
							<table style="margin-top:10px;">
							<tr>
								<td><label>Comment <span style="font-weight:bold;color:red;">*</span></label></td>
								<td><textarea id="rasonText1" cols="100" name="reasonText" onkeyup="clearError()"></textarea></td>
							</tr>
							</table>												
							<table style="margin:25px;" border="0" cellpadding="0" cellspacing="3">	
								<tr>
									<td><input type="button" value="POST" onclick="submitHandler('FollowUp')"></td>									
								</tr>
							</table>
	</c:if>
	<c:if test="${sessionScope.loginStatus != 'out'}">
	<s:form action="loginInputAction" method="POST">
	<input type="hidden" name="redirectLoc" value="PROBLEM_DISCUSSION" />
	<input type="hidden" name="problemHistoryId" value="${problemHistoryId}" />
	<input type="hidden" name="logInStatus" value="${logInStatus}" />
	<p style="margin-top:10px;">Registered Users please Login to follow up in this discussion forum</p>
	<input type="submit" Value="Login"/>						
	</s:form>
	</c:if>
</div>
</c:if>
</div>
</div>

<script type="text/javascript">

executeOnload();
getProblemRelatedImages(id);
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

<script type="text/javascript" src="js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>

</body>
</html>