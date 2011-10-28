<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<title>Party Analyst - An Election Analysis &amp; Political Management Platform</title>

<meta http-equiv="Content-Language" content="en">

<meta name="description" content="Party Analyst is a complete Indian Election Analysis Platform that also offers, Constituency, Cadre Management to Indian Political Parties and Politicians.">

<meta name="keywords" content="Indian Elections, Election Analysis, Indian Democracy, Andhra Pradesh Politics, Indian Political Parties, Indian Politicians, Indian Leaders, Congress, BJP, TDP, TRS, Indian Election Commission, Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results, MPTC Elections, ZPTC Elections, Constituency Management, Cadre Management, Party Performance, Election Comparison, Municipal Elections, Corporation Elections">

<meta name="copyright" content="IT Grids (India) Pvt. Ltd.">

<meta name="author" content="Ashok Dakavaram">

<meta name="email" content="info@itgrids.com">

<meta name="Charset" content="ISO-8859-1">

<meta name="Distribution" content="Global">

<meta name="Rating" content="General">

<meta name="ROBOTS" content="INDEX,FOLLOW">

<meta name="Revisit-after" content="1 Day">

<meta name="expires" content="Never">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 

<script type="text/javascript" src="js/homePage/homePage.js"> </script>

  
<script type="text/javascript">

function openAddNewProblemWindow()
{	
	var browser_addNewProblem = window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
	
	browser_addNewProblem.focus();
}

</script>

</head>

<body>
<div id="feedback_window"><div id="feedback_window_inner"></div></div>
<div id="username_change_window" style="background-color: #C7CFD2;">
	<div id="username_change_window_inner"></div></div>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
	<div id="knowMore_window"><div id="knowMore_window_inner"></div></div>

	<div id="assembly_2011_window"><div id="assembly_2011_window_inner"></div></div>
	
	<div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>
	
</div>
<!--CONTENT MAIN SECTION START-->

<div id="content-mainsec">
  <div class="js-banner-bg">
    <div class="mainwrapper"> 
      
      <!--CONTENT SECTION START-->
      
      <div class="content-sec"> 
        
        <!--BANNER AND SEARCH RESULTS SECTION START-->
        
        <div class="banner-main-sec"> 
          
          <!--FOLLOW US SECTION START-->
         
         
          <!--FOLLOW US SECTION END--> 
          
          <!--BANNER SLIDE SECTION START-->
          
          <div class="banner-left-sec">
            
            
            <div class="anythingSlider">
              <div class="wrapper">
                <ul>
                   <li class="js-banner-main-bg"> <img src="./images/new_homepage/banner-slide-01.png" alt=""/> </li>
                  <li class="js-banner-main-bg"> <img src="./images/new_homepage/banner-slid.png" alt=""/> </li>
                  
                </ul>
              </div>
            </div>
            <div class="clear"></div>
             <div class="clear"></div>
			<c:if test="${sessionScope.UserType == 'PartyAnalyst' || sessionScope.UserType == 'FreeUser'}"> 
			<div class="register-free"><span class="fleft" style="line-height:30px">Click Here for </span>
               <a href="freeUserRegistration.action"><img src="images/icons/homePage_new/updatebutton.jpg" style="padding-left:13px;"></a>
            </div>
			</c:if>
			<c:if test="${sessionScope.UserType != 'PartyAnalyst' && sessionScope.UserType != 'FreeUser'}"> 
            <div class="register-free"><span class="fleft">Click Here for Free Registration</span>
               <a class="rf-button" href="freeUserRegistration.action"></a>
            </div>
			</c:if>
          </div>
          
          <!--BANNER SLIDE SECTION END--> 
          
          <!--BANNER RIGHT (SEARCH RESULTS) SECTION START-->
          
          <div class="banner-right-sec">
            <h1 class="main-title">Search for results</h1>
            <div class="sr-sec">
              <div class="sr-mbg">
                <div class="glossymenu"> <a class="menuitem submenuheader" href="#">View Your State.</a>
                  <div class="submenu">
                    <p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
                    <div class="view-results"><a href="#" onclick="navigateToStatePage()">view results</a></div>
                  </div>
                  <a class="menuitem submenuheader" href="#" >View Your district.</a>
                  <div clAss="submenu">
				  <div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
                    <p>Select your district to view its election results in district level.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
                    <div class="view-results"><a href="#" onclick="navigateToDistrictPage()">view results</a></div>
                  </div>
                   <a class="menuitem submenuheader" href="#">View Your Constituency.</a>
                  <div class="submenu">
				  <div id="alertMessage" style="color:red;font-weight:bold;"></div>
                    <p>Select Constituency Type<br />
                      <span class="radio-type">
                      <input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/>
                      Assembly</span> <span class="radio-type">
                      <input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/>
                      Parliament</span> </p><br>
					  <table id="stateTable" style="display:none;">
									<tr>
										<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name" headerKey = "0" headerValue="Select State" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
										</td>
										<td><div id="constituency_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>	
									</tr>
								</table>
									
								
								<table id="constTable" style="display:none;">
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
									</tr>
								</table>
                    <div class="view-results"><a href="#" onclick="navigateToConstituencyPage()">view results</a></div>
                  </div>
                  
                  <a class="menuitem submenuheader" href="#">View Your Localty.</a>
                  <div class="submenu">
                   
							<div >
								<table>								
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesListForLocalBodyElection" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
									</tr>
									<tr>
										<td style="color:#004078"><div id="localBodiesRadioDiv_label"></div></td>
									</tr>
									<tr>
										<td><div id="localBodiesRadioDiv_data"></div></td>									
									</tr>
									<tr>
										<td style="color:#004078"><div id="localBodiesSelectDiv_label"></div></td>
									</tr>
									<tr>
										<td><div id="localBodiesSelectDiv_data"></div></td>									
									</tr>
									<tr>
										<td><div id="localBodies_errorDiv"></div></td>									
									</tr>
								</table>
							</div>
							<div class="view-results"><a href="#">view results</a></div>
                  </div>
						                   
						<a class="menuitem submenuheader" href="#">View all Election Results.</a>
                  <div class="submenu" style="padding-bottom:5px;">
                    <table>
									<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="statesList" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td><td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>			
									</tr>		
									
									<tr><td><select id="electionLists" class="selectBoxWidth" onchange="getElectionYearsInHomePage((this.options[this.selectedIndex].text))" ></select></td></tr>
									
									<tr><td><select id="electionYears" class="selectBoxWidth"></select></td></tr>
								</table>
                    <div class="view-results"><a href="#" onclick="viewElectionResults()">view results</a></div>
                  </div>
                </div>
              </div>
              <div class="sr-bbg"></div>
            </div>
          </div>
          
          <!--BANNER RIGHT (SEARCH RESULTS) SECTION END--> 
          
        </div>
        
        <!--BANNER AND SEARCH RESULTS SECTION END--> 
        
        <!--LATEST NEWS AND UPDATES SECTION START-->
        
        <div class="latest-nu-sec">
          <h1 class="news-title">Latest news &amp; Updates</h1>
          <div class="news-updates-scroll"> <span class="fleft"><img src="./images/new_homepage/blue-left-arrow.gif" alt=""/></span>
         <marquee onmouseover="this.stop()" onmouseout="this.start()" scrollDelay="180"><font class="scrollDataStyle"/><u>News</u> :<font><a href="javascript:{}" class="scrollDataStyle" onclick="openAssembly2011Window()">Pulivendula Bi Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openKadapa2011Window()">Kadapa Bi Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openTN2011Window()">Tamil Nadu Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openWB2011Window()">West Bengal Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openKerala2011Window()">Kerala Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openAssam2011Window()">Assam Assembly Election 2011 Results</a> , <a href="javascript:{}" class="scrollDataStyle" onclick="openPD2011Window()">Puducherry Assembly Election 2011 Results</a>
		</font>
          </marquee>
            <span class="fright"><img	 src="./images/new_homepage/blue-right-arrow.gif" alt=""/></span> </div>
        </div>
        
        <!--LATEST NEWS AND UPDATES SECTION END--> 
        
        <!--QA AND CURRENT ELECTION SECTION START-->
        
        <div class="qa-current-election-sec"> 
          
          <!--QA CONTENT SLIDE SECTION START-->
          
          <div class="qa-sec">
            <h1 class="fleft"><img src="./images/new_homepage/qa.gif" alt=""/></h1>
            <div class="rd-arrows"> <a href="#" onmouseover="movedown()" onmouseout="clearTimeout(movedownvar)"><img src="./images/new_homepage/orange-top-arrow.gif" alt=""/></a> <a href="#" onmouseover="moveup()" onmouseout="clearTimeout(moveupvar)"><img src="./images/new_homepage/blue-down-arrow.gif" alt=""/></a> </div>
            <div class="clear"></div>
            <script type="text/javascript" src="js/vcontent.js"></script>
            <ul>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">How Party Analyst is useful to Voters? </div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme">They can know their leaders in better way, they can publish their constituency problems,
				they can connect their local people and leaders. They can discuss about their parties and leaders. </div>
							  </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">How Party Analyst is useful to Politicians?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme">They can get all elections information in a single platform, they can know which area they performed well and
				in which area they need to improve. They can maitain Cadre, Influencing People and Influencing Groups information, and
				they can improve the communication channels with them. They can maintain all news, photo galleries and videos in one place.</div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">How Party Analyst is useful to Political Parties?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="border-bottom:none;">They can get all elections information in a single platform, they can analyze the elections based on geographically,
				demographically, political alliances and based on previous elections. They can maintain cadre, influencing people, groups
				information in one place and improves the communication channels with them. </div>
							  </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">What are the different types users in Party Analyst?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme">1) Free User 2) Registered User 3) Commercial User</div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">What are the other services provided by Party Analyst?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme">Apart online we can also provide detailed Election Analysis Reports for any Constituency.
				Election Surveys
				Voters details with Caste Analysis
				Profile Maintainance - integrations of news, videos, photos
				Call Center Services - Remote Office to Politicians.
                </div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">The information displayed in this website are based on the data collected from the Election Commmission Of India. ?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="border-bottom:none;">The information displayed in this website are based on the data</div>
              </li>
            </ul>
            <script type="text/javascript" src="js/vscroll.js"></script> 
            <script type="text/javascript" src="js/vcontent-scroll.js"></script> 
          </div>
          
          <!--QA CONTENT SLIDE SECTION END--> 
          
          <!--CURRENT ELECTION SECTION START-->
          
          <div class="ce-sec">
<h1 class="title"><span class="orange">Current</span> Elections</h1> <div id="wrap">
              <div id="list">
                <div class="prev"><img src="./images/new_homepage/wallpapers-left-arrow.png" alt="prev" /></div>
                <div class="slider">
                  <ul>
                    <li> <a href="statePageAction.action?stateId=7" title="Title 1"><img src="./images/new_homepage/ce-01.jpg" alt="Title 1" /> <span>BJP Narendra modi</span> </a></li>
                    <li> <a href="statePageAction.action?stateId=28" title="Title 2"><img src="./images/new_homepage/ce-02.jpg" alt="Title 2"/> <span>TCON Mamatha</span> </a></li>
                    <li> <a href="statePageAction.action?stateId=24" title="Title 3"><img src="./images/new_homepage/ce-03.jpg" alt="Title 3" /> <span>ADMK Jayalalitha</span> </a></li>
					<li> <a href="statePageAction.action?stateId=13" title="Title 3"><img src="images/icons/homePage_new/KL_elections_img.jpg" alt="Title 3" /> <span>INC Oommen Chandy</span> </a></li>
                  </ul>
                </div>
                <div class="next"><img src="./images/new_homepage/wallpapers-right-arrow.png" alt="next" /></div>
              </div>
            </div>
            <div class="clear"></div>
            <ul class="ce-sub-fields">
              <li>Post a problem of their constituency</li>
              <li>View all posted comments</li>
              <li>View replies to their comments</li>
            </ul>
          </div>
          
          <!--CURRENT ELECTION SECTION END--> 
          
        </div>
        
        <!--QA AND CURRENT ELECTION SECTION END-->
        
        <div class="clear"></div>
        
        <!--SNEAK PEAK - PARTY ANALYST SECTION START-->
        
        <div class="sneak-peak-sec">
          <h1 class="title tc-tf">Sneak Peak @Party Analyst</h1>
		  <div class="homePageContentWidget_body" style="width:650px; border: 0px solid #DDDDDD;">

									<div class="homePageContentWidget_readMore">
										
									</div>
									<div id="homePage_Chart_Header_main" style="overflow:hidden;height:360px;">
										<div id="homePage_Chart_Header">
									   
										</div>
									</div>
							</div>
                    
          <p>The information displayed in this website are based on the data collected from the Election Commmission Of India. Further suggestions and corrections please contact us at <a href="mailto:info@itgrids.com" class="blue">info@itgrids.com</a></p>
        </div>
        
        <!--SNEAK PEAK - PARTY ANALYST SECTION END--> 
        
        <!--PPPM AND FT ICONS SECTION START-->
        
        <div class="pft-social-sec">
          <div class="pft-sec"> <img src="./images/new_homepage/pft.jpg" alt=""/>
            <div class="clear"></div>
            <p></p>
            <span class="gray">Are you a</span> <strong>Politician<span class="orange">/</span>Political Party<span class="orange">/</span>Media...</strong> Want to know how you can be benefited with <span class="orange">PartyAnalyst</span> ?
            <div class="clear"></div>
            <div class="clickhere-button"><a href="viewFeaturesAction.action">Click Here to Learn More...</a></div>
          </div>
          <div class="social-icons"> <strong>We are SOCIAL</strong>
            <div class="clear"></div>
            <div class="social-io"><a title="Facebook" href="http://www.facebook.com/share.php?u=http%3A%2F%2Fpartyanalyst.com%2Fhomepage.action&amp;t=to%20know%20%20Analyse%20Act%20for%20Politics" target="_blank" rel="nofollow"><img src="./images/new_homepage/facebook-io.jpg" alt=""/></a> <a href="http://twitter.com/share" class="twitter-share-button" data-url="http://www.partyanalyst.com" data-count="none" target="_blank"><img src="./images/new_homepage/twitter-io.jpg" alt=""/></a> <a href="http://www.linkedin.com/company/it-grids-ltd" target="_blank"><img src="./images/new_homepage/in.jpg" alt=""/></a> <a href="http://www.youtube.com/partyanalyst" target="_blank"><img src="./images/new_homepage/youtube.jpg" alt=""/></a> </div>
          </div>
        </div>
        
        <!--PPPM AND FT ICONS SECTION END--> 
        
      </div>
      
      <!--CONTENT SECTION END--> 
      
    </div>
  </div>
</div>

<!--CONTENT MAIN SECTION END--> 

<script type="text/javascript">
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
buildHOmePageChartsSlider();
	initializeHomePage();
	getElectionTypeValue(1);
	
	<c:if test="${loginStatus == 'true' && sessionScope.UserType == 'FreeUser'}">
	showUserNameChangePanel(uname);
	</c:if>

	//getElectionYears("Assembly");


function showDetailsForAvailability(results){
var email = document.getElementById("emailField").value;
var result = document.getElementById("feedback_window_errorMsg");
	var str='';
	if(results==121){
		str = '<font color="#000000">Sending Your Request.Please wait</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'

		var jsObj=
		{		
 				userName:email,
				task:"changeAnanymousUserNameToEmail"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/changeAnanymousUserNameToEmailAction.action?"+rparam;						
		callAJAX(jsObj,url);
		}	
	else{
		str+='<div style="color:red"> Username is not available</div>';	
	}
	result.innerHTML = str;

}
function showDetails(results)
{
	
	var result = document.getElementById("feedback_window_errorMsg");
	 str='';
	
	if(results == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	
	else{
		$("#username_change_window").dialog("destroy");
		afterUserNameChanges();
		return;
	}
	result.innerHTML = str;
	
}

function afterUserNameChanges(){
var email = document.getElementById("emailField").value;
 $("#username_change_window").dialog({
			resizable:false,
			width: 600,
			minHeight:150,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
		str += '<div id="feedback_window_body"';
		str +='style="font-weight:bold;color:green;text-align:center;">';
		str += 'Your New UserName Is :'+email;
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr><td>';
		str += '	<input id="OkButton" type="button" width="50px" align="center"' ;
		str += '   value="OK"></input></td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("OkButton");

		oPushButton2.on("click",function(){
			$("#username_change_window").dialog("destroy");
		});
}



function changeUserName(){

var email = document.getElementById("emailField").value;
	var resultDIVEle = document.getElementById("feedback_window_errorMsg");
	resultDIVEle.innerHTML = "";
	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	if(email.length == 0)
		resultDIVEle.innerHTML = "<font color='red'><b>Please Enter Email Id.</b></font>";
	
	else if(reg.test(email) == false)
		resultDIVEle.innerHTML = "<font color='red'><b>Plase provide correct Email Address.</b></font>";
    else if(emailId == ''|| emailId== null){
		document.getElementById("feedback_window_errorMsg").innerHTML = " ";
       var jsObj=
		{		
 				userName:email,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);

	}
	else if(reg.test(email) == true)
	{
        document.getElementById("feedback_window_errorMsg").innerHTML = " ";
 		var jsObj=
		{		
 				userName:email,
				task:"checkAnanymousUserNameAvailability"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/checkAnanymousUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);

	}
	
	
	}
function showUserNameChangePanel(uname){

	var alertIndicator = '${changedUserName}';
	
	if(alertIndicator == null || alertIndicator == 'false')
		return;

var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
if(reg.test(uname) == false)
	{

$("#username_change_window").dialog({
			resizable:false,
			width: 600,
			minHeight:225,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
		str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
		 if(emailId == ''|| emailId== null)
		str += '<div id="feedback_window_body">';
		 else
        str += '<div id="feedback_window_body" style="padding-bottom:48px">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
	 if(emailId == ''|| emailId== null){

		str += '		<td><img src="images/icons/infoicon.png"></td>';
		str += '		<td>Fields marked with (<font color="red">*</font>) are mandatory</td>';
		str += '		</tr>';
		str += '		</table>';
		str += '	</div>';
		str += '	<div id="feedBackForm_div">';
		str += '		<table id="feedbackTable" width="100%">';
		str += '		<tr>';
       	str += '		<th><font color="red">*</font>Enter Your EmailId </th>';
		str += '		<td>';
		str += '			<input type="text" id="emailField" size="25"/>';
		str += '		</td>';
		str += '		</tr>';
        }
		else{
        str += '<input type="hidden" value='+emailId+' id="emailField" size="25"/>'; 
		str += '		<tr>';
       	str += '		<th></th>';
        str += '		<td>';
        str += '		</td>';
		str += '		</tr>';
	    }
		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="post" type="button" value="Yes"></input>';
		str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
		str += '	</td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton1 = new YAHOO.widget.Button("post");  
		var oPushButton2 = new YAHOO.widget.Button("cancelButton");

		oPushButton1.on("click",function(){
			changeUserName();
		});

		oPushButton2.on("click",function(){
			$("#username_change_window").dialog("destroy");
		});
	}
}



	</script>
	
</body>
</html>
