<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<title><decorator:title default=" TDP News Portal "/></title>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	<!--Bootstrap styles file-->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/loginpopup.js"> </script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
	<!--Script file
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
	<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
	<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
	
	<!--YUI SCRIPT-->
	<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
    
	
	<style>
	body{color:#5B5B5B;}
	
.background
{
background:#ffffff;
margin:-1px 0px 0px 0px ;
}

.header2 {
     position: absolute;
    top: -126px;
	left:-2%;
}
.gradlightblack{
	margin-top:10px;
}

.header-right-sec{width:750px;}
.lr-sec{padding:14px 5px 5px;}
#menu ul.menu li{z-index: 999;}
.memberArea li a{font-weight:bold;}
.navMenu li a{font-weight:bold;}


.nav1 li.outerLi{float:left;list-style-type:none;margin:0.4px;position:relative;}
.nav1 li a.innerA{color:#000;font-weight:bold;display:block;padding:23px;font-family:verdana,arial;font-size:14px;}

.nav1 li.outerLi:hover a.innerA{color:red;background:#FFFF00;text-decoration:none;}
.nav1 li.outerLi:hover{background:#FFFF00;border-bottom:4px solid red;}
.nav1 li.outerLi a{outline:0;text-decoration:none;}

.nav1 li.outerLi ul{display:none;}

.nav1 li.outerLi:hover ul{display:inline-block;}
.nav1 li.outerLi ul{position: absolute;
      top:62px;left:-27px;z-index:100;}
.nav1 li.outerLi:hover ul a{color:#ffffff;text-decoration:none;}
.nav1 li.outerLi:hover ul li{padding:10px;margin:2px;list-style-type:none;background:#7FC67F;width:200px;}
.nav1 li.outerLi:hover ul li:hover{background:#FFFF00;color:red;border:1px solid #ccc;}
.nav1 li.outerLi:hover ul li:hover a{color:red;}


.menuActive{border-bottom:4px solid red;box-shadow:0px 0px 2px #ccc;border-radius:2px;}
.requiredFont{color:red;}
#changePasswordInnerDiv{font-size:13px;}
#errorMsgDiv{margin-top: 5px; margin-bottom: -10px; margin-left: 134px;}
</style>
<decorator:head/>
</head>
<body>

<table style="border-collapse: collapse;" width="100%">
<tr>
<td>

  <div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
  <div id="changePasswordDiv">
  <div id="changePasswordInnerDiv"></div>
  </div>

		<!---Header----->
		<div class="container">	
		<div class="row">
			<!----Logo----->
			<div class="span4">
				<a href="homePage.action"><img src="images/Logo.png" alt="Telugudhesam party logo" />		</a>
			</div>
			<!-----Nav main div---->
			<div class="span8">
				<div class="row-fluid">
					<!----Member Area Div---->
					<div class="span12">
					
					 <c:if test="${sessionScope.USER != null}">
					   <c:set var="urlString" value="${pageContext.request.requestURI}" />
					   
						<c:if test="${fn:contains(urlString, 'homePage')}">
   						
						 <div class="btn-group" id="changePasswordTab" style="margin-left: 264px; margin-top: 5px;" >
								 <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                 <img src="images/userSettings.png" />
                                 <span class="caret"></span>
                                 </a>
                             <ul class="dropdown-menu">
								<li><a tabindex="-1" id="changePasswordId" href="javascript:{}">Change Password</a></li>
								
							</ul>
						 </div>
					</c:if>

					 </c:if>

						<ul class="nav nav-pills pull-right memberArea ">
						 <c:if test="${sessionScope.USER == null}">
						  <li><a href="javascript:{}" onClick="openDialogForLoginWindow()">Login</a></li>
						  <li class="selected"><a href="userRegistration.action">Register</a></li>
						 </c:if>
						 <c:if test="${sessionScope.USER != null}">
						  <li style="padding-top: 6px;">${sessionScope.USER.name}</li>
						   <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
						    <li><a href="adminPageAction.action">Admin</a></li>
						   </c:if>
						  <li class="selected"><a href="logoutAction.action">Logout</a></li>
						 </c:if>
						</ul>
					</div>
					<!------Menu div-------->
					<div class="span10" style="margin-top:-15px;height:80px;width:625px;">
						<ul class="nav1 nav-pills1 navMen pull-right headerMenu">

						<c:if test="${sessionScope.USER != null}">
						 <c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
						  <li class="outerLi" id="homeTabId"><a  href="homePage.action" class="innerA">Home</a></li>
						<!--  <li class=""><a href="aboutUs.action">About Us</a></li>-->
						 <li class="outerLi" id="newsTabId">
						  <a href="newsDetailsAction.action" class="innerA">News</a></li>
						  <li class="outerLi" id="videosTabId">
						  <a href="showMoreVideos.action" class="innerA">Videos</a></li>
						 
						   <li class="outerLi" id="reportsTabId">
								<a href="candidateNewsDetailsAction.action?fromDate=&toDate=&requestFor=candidate" class="innerA">Reports</a>
									<ul>
										<li><a href="candidateNewsDetailsAction.action?fromDate=&toDate=&requestFor=candidate"><span>Candidate News Report</span></a></li>
										<li><a href="candidateNewsDetailsAction.action?fromDate=&toDate=&requestFor=opponent"><span>Opponent Party Critics Report</span></a></li>
									</ul>
							</li>
							  
						      <li class="outerLi" id="partyManagementTabId"><a href="partyManagementAction.action" class="innerA">Manage Party Profile</a></li>
							  
							 
						   
						  
						  <!--<li class=""><a href="contactUs.action">Contact Us</a></li>-->
						   </c:if> </c:if>
						  
						</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
		</div>

</td>
</tr>

<tr>
<td class="background" border="0">
<div style="width:960px;position:relative;margin:0 auto;">
<center>

<div class="header2">
<!--Facebook like icon-->
	<div style="float:left;margin-left:80px;margin-top:15px;" class="fb-like" data-href="http://www.facebook.com/PartyAnalyst" data-send="false" data-layout="button_count" data-width="0" data-show-faces="false">
	</div>
	<!--Facebook and twitter follow us
		<div class="follow-us" style="margin-left:180px;">
            <ul>
              <li><a href="http://twitter.com/#!/partyanalyst" target="_blank"><img src="./images/new_homepage/twitter.gif" alt="" height="30px" width="30px"/></a></li>
              <li><a title="Facebook" href="http://www.facebook.com/PartyAnalyst" target="_blank"><img src="./images/new_homepage/facebook.gif" alt="" height="30px" width="30px"/></a></li>
            </ul>
            <h5 style="color:#08AAEC;">follow us</h5> 
		</div>-->
</div>

</center>
</div>
<div id="login_window">
	<div id="login_window_inner"></div>
</div>
</td>
</tr>
<!--BODY -->
<tr>
	<td class="" border="0">
 		<div id="contenttable" class="background">
			<decorator:body/>
		</div>
	</td>
</tr>
<tr>
<td>
	<!-----Footer---->
	  <footer>
        <p class="text-center">&copy; Telugu Desam Party 2013</p>
      </footer>
  
  
</div>
</td></tr>
</table>
<!--FOOTER SECTION END-->
<script type="text/javascript">
var notLogged = '${notLogged}';
 if(notLogged == 'true')
 {
	 redirectTo = true;
	 openDialogForLoginWindow();
  }

function getNewsDetailsByContentId(contentId)
{
  var urlstr = "newsDetailsPopupAction.action?contentId="+contentId+"&";
	
    var browser1 = window.open(urlstr,"gallaryDetails"+contentId+"","scrollbars=yes,height=600,width=1050,left=200,top=200");	
    browser1.focus();
} 
/* updated by srishailam
function showMoreVideoGallaries(){
	 var urlstr = "showMoreVideos.action";
		
     var browser1 = window.open(urlstr,"showMoreVideos","scrollbars=yes,height=600,width=1050,left=200,top=200");	
     browser1.focus();
}
*/


$('.headerMenu li a').click(function() {
	$('.headerMenu li.menuActive').removeClass('menuActive');
	$(this).closest('li').addClass('menuActive');
});

$(document).ready(function(){
$("#changePasswordId").click(function(){

  var str = "";
  str +='<div id="errorMsgDiv"></div>';
  str +='<table style="margin-left: 35px; margin-top: 18px;">';
  str +='<tr>';
  str +='<td>Current Password <font class="requiredFont">*</font></td>';
  str +='<td><input type="password" id="currentPWD"></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>New Password <font class="requiredFont">*</font></td>';
  str +='<td><input type="password" id="newPWD"></td>';
  str +='</tr>';
  str +='<tr>';
  str +='<td>Confirm Password <font class="requiredFont">*</font></td>';
  str +='<td><input type="password" id="ConfirmPWD"></td>';
  str +='</tr>';
  
  str +='</table>';
  str +='<input type="button" class="btn btn-info" value="submit" id="changePasswordBtn" style="margin-left: 225px; margin-top: 13px;"/>';
  $("#changePasswordInnerDiv").html(str);



  $("#changePasswordDiv").dialog({
   width: 500,
   height: 280,
   modal: true,
   title:"Change Password"
  
  });

$("#changePasswordDiv").dialog();	
	
});


$("#changePasswordBtn").live("click",function(){
 $("#errorMsgDiv").html('');	
 var currentPWD = $.trim($("#currentPWD").val());
 var newPWD = $.trim($("#newPWD").val());
 var confirmPWD = $.trim($("#ConfirmPWD").val());

 if(currentPWD.length == 0)
 {
   $("#errorMsgDiv").html('Please Enter Current Password.').css("color","red");
   return;
 }

 else if(newPWD.length==0)
 {
  $("#errorMsgDiv").html('Please Enter New Password.').css("color","red");
   return;
 }
 else if(newPWD.length >0 && newPWD.length < 8)
 {
  $("#errorMsgDiv").html('New Password Minimum Of 8 Characters.').css("color","red");
   return;
 }
 else if(confirmPWD.length==0)
 {
  $("#errorMsgDiv").html('Please Enter Confirm Password.').css("color","red");
   return;
 }

 
 else if(currentPWD == newPWD)
 {
  $("#errorMsgDiv").html('Your new password is same as existing one').css("color","red");
   return;
 }
 else if(newPWD != confirmPWD)
 {
  $("#errorMsgDiv").html('New and confirm passwords are not same.').css("color","red");
   return;
 }

$.ajax({
      url: "changePasswordAction.action?",
      type: "post",
      data: {currentPWD:currentPWD,newPWD:newPWD,confirmPWD:confirmPWD},
      success: function(results){
       showPasswordChangeStatus(results);   
      },
      error:function(results){
       showPasswordChangeStatus(results);     
      }   
    }); 

	
});

});

function showPasswordChangeStatus(results)
{
 
 $("#errorMsgDiv").html('');

 if(results == null)
  $("#errorMsgDiv").html('Error Occured Try Again !').css("color","red");

 else if(results.resultCode == 1 && results.message != null)
  $("#errorMsgDiv").html(''+results.message+'').css("color","red");

 else
 {
  $("#currentPWD").val('');
  $("#newPWD").val('');
  $("#ConfirmPWD").val('');
  $("#errorMsgDiv").html('PassWord Changed Successfully.').css("color","green");

 }

}

</script>

</body>
</html>

