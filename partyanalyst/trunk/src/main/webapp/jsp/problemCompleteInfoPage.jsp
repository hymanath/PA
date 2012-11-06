<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${problemBeanVO.problem}</title>


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

<script type="text/javascript" src="js/problemCompleteDetails.js"></script>

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />


<!-- JQuery files (End) -->

<style type="text/css">
a img {
    border: none
    margin: 0;
    padding: 0px;
    vertical-align: top;
}
#problemDetailsPageMainDiv
{
	margin-left: auto;
    margin-right: auto;
    float: none;
    width: 960px;
    margin-bottom: 20px;
}
.divInfo
{
	border-left: 1px solid #B3C1CE;
    border-right: 1px solid #B3C1CE;
    border-bottom: 1px solid #B3C1CE;
	border-top: 1px solid #B3C1CE;
    padding: 5px;
	
}
#headerDiv
{
	background: #06ABEA;
    color: #FFFFFF;
    font-weight: bold;
    font-size: 15px;
    padding: 7px;
    border-radius: 3px;
}
h3
{
	font-size: 15px;
    font-weight: normal;
    padding: 0 0 5px;
	color:#669900;
}
hr
{
	background: url("images/hr.jpg") repeat-x scroll 0 0 transparent;
    border: medium none;
    padding: 5px;
}
#description p {
    color: #000000;
    font-family: Verdana,Arial,Helvetica,sans-serif;
    font-size: 12px;
    margin: 0;
}
.bluetext {
    color: #3B5998;
    font-weight: bold;

}
p
{
	font-size: 14px;
    line-height: 1.3em;
}
#rasonText1
{
	height: 60px;
	width:800px;
	background:#FFF;
	border:1px solid  #B3C1CE;
}
.imageButton {
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
#content {
    background: none repeat scroll 0 0 #FAFAFA;
    border: 1px solid #CBCBCB;
    box-shadow: 0 0 10px #CBCBCB;
    margin: 40px auto 0;
    padding: 0 60px 30px;
    width: 85%;
}
</style>

<script type="text/javaScript">

var id = '${problemHistoryId}';

var logInStat = '${sessionScope.loginStatus}';
var userType = '${sessionScope.UserType}';

function getProblemRelatedImages(pHistoryId){
    
	var jsObj ={
				pHistoryId:pHistoryId,
				task:"getProblemImages"

	           };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemImagesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function callAjax(jsObj,url)
{	
	var callback = {			
	               success : function( o ) {
					try {

						myResults = YAHOO.lang.JSON.parse(o.responseText);

						if(jsObj.task =="getProblemImages")
						{
							builImagesDiv(myResults);
							
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
str+= '<a href="'+results[i].pathOfFile+'" style="margin-left: 400px;"><img alt="" src="images/doc_images/wordImage.png" height="100px" style="padding:2px;"></img></a>';
}

str+= '</a></td>';
str+= '</tr><tr><td><div class="fancyBoxImageDivTitle">'+results[i].title+'</div></td></tr></table></td>';


}
else{
str+= '<td><table><tr><td>';
str+= '<a rel="photo_gallery" href="'+results[i].pathOfFile+'" title="'+results[i].description+'"><img alt="" src="'+results[i].pathOfFile+'" height="100px" / style="margin-top: 15px; padding: 2px; border: 1px solid #BBBBBB;"></a></td>';
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

function openFile(filePath)
{
	window.open(filePath, "browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
}

function problemCompleteDetails()
{
	<c:if test="${problemBeanVO != null}">
		var jsObj = {
				task: 'checkApprovalStatus',
				id: ${problemBeanVO.problemHistoryId}
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "saveProblemApproveDetailsAction.action?"+rparam;
		
	approvalCallAjax(jsObj,url);	
	</c:if>

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

function alltrim(str) 
{
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
	var fileUploadDivEle = document.getElementById("alertMessage");
	fileUploadDivEle.style.display = 'block';
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
function focusOnTextareas()
{
	var rasonTextEl = document.getElementById("rasonText");
	var rasonTextEl1 = document.getElementById("rasonText1");
	if(rasonTextEl)
		rasonTextEl.focus();
	if(rasonTextEl1)
		rasonTextEl1.focus();	
	
}

</script>
</head>
<body>
<div id="popupOuterDiv">
	<div id="popuoInnerDiv"></div>
</div>

<div id="problemDetailsPageMainDiv">
	<c:if test="${problemBeanVO != null}">
		<div style="background:#FFFFFF;">
			<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
				  <tr>
				  <td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
				  <td width="98%">
				 <div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
				 <span class="headerLabelSpan2" style="text-decoration:none;">Problem Complete Details</span>
				 </div>
				 </td>
				 <td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/>
				 </td>
				 </tr>
			</table> -->
			<div id="headerDiv">
				<span>Problem Complete Details</span>

				<c:if test="${problemBeanVO.isApproved == true}">
				<span style="float:right;">
			<g:plusone size="medium"></g:plusone>

			<script type="text/javascript">
			 (function() {
			  var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
				 po.src = 'https://apis.google.com/js/plusone.js';
				 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
			 })();
			</script>
		</span>
		<span style="float:right;">
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId=${problemBeanVO.problemHistoryId}">
				Tweet</a>
			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");
			</script>
		</span>
		<span style="float:right;margin-right: 20px;">
			<a href="javascript:{}" onClick="shareInFacebook('www.partyanalyst.com/problemCompleteInfoAction.action?problemHistoryId=${problemBeanVO.problemHistoryId}')" title="Share this Page in Facebook"><img alt="Share in Facebook" src="images/FBshare.jpg"></img></a>

		</span>
			</c:if>

			<c:if test="${problemBeanVO.isApproved != true}">

				<c:if test="${problemBeanVO.isApproved == 'Rejected'}">
					<span style="margin-left: 480px;">This problem is Rejected by Admin</span>
				</c:if>

				<c:if test="${problemBeanVO.isApproved == 'false'}">
					<span style="margin-left: 480px;">This Problems is Not Considered</span>
				</c:if>
			</c:if>
			</div>

			<table width="100%" border="0">
			<tr>
			<td width="70%" id="problemDetailsCellId">
			<div id="problemDetails" style="margin-top:10px;border-radius:2px;">
			 <div class="divInfo">
				<h3><b>Problem</b></h3>
			     <div id="description">${problemBeanVO.problem}</div><hr>
	            <h3><b>Description</b></h3>
	             <div id="description"><p>${problemBeanVO.description}</p></div><hr>
				 <p>
				 <c:if test="${problemBeanVO.impactLevel == 'STATE'}">
					<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}</span><BR>
				</c:if>

				<c:if test="${problemBeanVO.impactLevel == 'DISTRICT'}">
					<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation} , ${problemBeanVO.state}</span><BR>
				</c:if>
				
				<c:if test="${problemBeanVO.impactLevel == 'STATE'}">
					<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}</span><BR>
				</c:if>

				
				<c:if test="${problemBeanVO.impactLevel == 'CONSTITUENCY'}">

					<c:if test="${problemBeanVO.constituency == null}">
						<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
					</c:if>
					<c:if test="${problemBeanVO.constituency != null}">
						<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.state}</span><BR>
					</c:if>
				</c:if>
			<c:if test="${problemBeanVO.impactLevel == 'MANDAL'}">
				<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.constituency},${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
			</c:if>
			<c:if test="${problemBeanVO.impactLevel == 'VILLAGE'}">
				<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.tehsil},${problemBeanVO.constituency},${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
			</c:if>
			<c:if test="${problemBeanVO.impactLevel == 'WARD'}">
				<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.localBody},${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
			</c:if>
			<c:if test="${problemBeanVO.impactLevel == 'BOOTH'}">
				<c:if test="${problemBeanVO.tehsil != null}">
					<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.tehsil}, ${problemBeanVO.constituency}, ${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
				</c:if>
				<c:if test="${problemBeanVO.localBody != null}">
					<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.localBody}, ${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>
				</c:if>
			</c:if>

			<c:if test="${problemBeanVO.impactLevel == 'MUNICIPAL-CORP-GMC'}">
				<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">${problemBeanVO.problemLocation}, ${problemBeanVO.district}, ${problemBeanVO.state}</span><BR>';
			</c:if>
			<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Impact Level: <span class="bluetext">${problemBeanVO.impactLevel}</span><BR>
			<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Status: <span class="bluetext">${problemBeanVO.status}</span></p><hr>
			<p><img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported Date: <span class="bluetext">${problemBeanVO.identifiedOn}</span><BR>
			<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Existing From: <span class="bluetext">${problemBeanVO.existingFrom}</span><BR>
			<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported By: <span class="bluetext">${problemBeanVO.name}</span></p><hr>
			</div>
			</td>
			<c:if test="${sessionScope.UserType != 'PartyAnalyst'}">
				<td width="30%" valign="top">
					<div id="approveProblem" style="height:100px;margin-top:10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						 <tr>
						   <td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/>
						   </td>
						   <td width="98%">
							<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">
								<span class="headerLabelSpan2" style="text-decoration:none;">
									Give Comments 
								</span>
							</div>
							</div>
						</td>
						<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/>
						</td>
						<tr>
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
								<td><textarea id="rasonText" name="reasonText" onkeyup="clearError()" style="background: #FFF; height: 40px; width: 160px;"></textarea></td>
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
			
	<c:if test="${problemBeanVO.isApproved != 'Rejected'}">

	<c:if test="${hasFileUploadRight}">
			
        <div id="mainDiv">
			<table>
			   <tr> 
				   <td> 
				<input type="button" class="imageButton" onClick="showOrHideProblemFilesDiv()" value="Upload Problem Related Files"/>
				   </td> 
				   <td style="padding-left: 146px;">
				
				 <DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px; width: 200%; display:none;">File Uploaded Successfully...</DIV>
				
				  </td>		
			   </tr>			
			</table>				
	   </div>
       <br>
      <div id='problemUploadFilesMainOuterDiv' style="display:none;">

			<div id='problemUploadFilesHeaderDiv'>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"></td><td width="98%"><div style="text-decoration: none;" class="productFeatureHeaderBackground_center2"><span style="text-decoration: none;" class="headerLabelSpan2">Upload Problem Related Files/Images</span></div></td><td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg"></td></tr>
				</table>
			</div>
 
         <div id='problemUploadFilesMainInnerDiv' class="divInfo">
	          <div id="fileUploadDiv">
		        <form name="uploadForm" action="postImagesAndFilesAction.action" enctype="multipart/form-data"  method="post" id="uploadPicForm"> 
		           <table class="statusData_table" width="100%">	
		           <tr>
			         <td rowspan="2" width="13%" class="statusData_table_label">
						<table width="100%" class="statusData_table_inner">
						<tr>
							<td width="25%"><img src="images/icons/file_upload_icon.png"></td>
							<th>File Upload</th>       
						</tr>
						</table>								
			       </td>

			      <td class="statusData_table_data" width="83%">
						<table>
						<tr>
							<center><DIV id="errorMsgDivId" class="errorDiv"></DIV></center>
							<td style="padding-right: 62px;">Title</td> 
							<td> <input type="text" id="titleField" name="fileTitle" size="32"/></td>
							<span id="alertMsg1"></span>
							<td style="padding-left: 22px; padding-right: 10px;">Description </td>
							<td> <textarea name="fileDescription"  id="fileDescription" cols="20" rows="3" style="background:#FFFFFF;"> </textarea></td>
							<span id="alertMsg2"></span>
						</tr>
						</table>
			       </td>
		     </tr>

		     <tr>
			    <td class="statusData_table_links">
				  <span id="alertMsg3"></span>
					<table style="width:65%;">
					<tr>
						<td>Documents And Images</td>
						<input type="hidden" name="problemHistoryId" value="${problemHistoryId}">
						<td><input type="file" name="userImage" id="userImage"/></td>
						<td style="padding-left: 113px;"><input type="button" style="float:none" class="button" value="Upload" onclick="postFilesAndImages()" ></td>
					</tr></table>	
			    </td>
		    </tr>
		 </table>
	   </form>
      </div>
   </div>
  </div>
</c:if>
</c:if>
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
	<c:if test="${sessionScope.UserType != 'PartyAnalyst' && problemBeanVO.isApproved != 'Rejected'}">
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


		</div>
</c:if>
</div>

<script type="text/javascript">

getProblemRelatedImages(id);
focusOnTextareas();
<c:if test="${problemBeanVO.isApproved != 'Rejected'}">

getProblemAllComments(id);
</c:if>
problemCompleteDetails();
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