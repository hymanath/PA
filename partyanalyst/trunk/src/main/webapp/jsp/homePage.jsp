<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Party Analyst - An Election Analysis &amp; Political Management Platform</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />

<meta http-equiv="Content-Language" content="en" />

<meta name="description" content="Party Analyst is a complete Indian Election Analysis Platform that also offers, Constituency, Cadre Management to Indian Political Parties and Politicians." />

<meta name="keywords" content="Indian Elections, Election Analysis, Indian Democracy, Andhra Pradesh Politics, Indian Political Parties, Indian Politicians, Indian Leaders, Congress, BJP, TDP, TRS, Indian Election Commission, Know Analyze, Act, MLA Elections, MP Elections, Cross Voting, District Election Results, MPTC Elections, ZPTC Elections, Constituency Management, Cadre Management, Party Performance, Election Comparison, Municipal Elections, Corporation Elections" />

<meta name="copyright" content="IT Grids (India) Pvt. Ltd." />

<meta name="author" content="Ashok Dakavaram" />

<meta name="email" content="a.dakavaram@partyanalyst.com" />

<meta name="Charset" content="ISO-8859-1" />

<meta name="Distribution" content="Global" />

<meta name="Rating" content="General" />

<meta name="ROBOTS" content="INDEX,FOLLOW" />

<meta name="Revisit-after" content="1 Day" />

<meta name="expires" content="Never" />

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="styles/combo.css" /> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"></link> 
<!-- Combo-handled YUI JS files: --> 

<script type="text/javascript" src="js/homePage/homePage.js"> </script>

  
<script type="text/javascript">
var homePageUpdates = null;
</script>
<style type="text/css">
#menu ul.menu li.active a {

  background-position: left 0px;
}

#menu ul.menu li.active a span {
		color: #E8F3F7;
  background-position: right -27px;
}
#feedback_window
{
	background-color:#C7CFD2;	
}

#feedback_window_inner
{
	background-color:#FFFFFF;	
}

#feedback_window_head
{
	background-color:#7898BC;
	color:#FFFFFF;
	font-weight:bold;
	padding:5px;
}

#feedback_window_body
{
	background-color:#FFFFFF;
	color:#3A4347;
	padding:5px;
}

#feedBackNote_div
{	
	border-bottom:1px solid #695532;
	margin-bottom:10px;
	padding-bottom:10px;
}

#feedbackTable th
{
	padding:5px;
	text-align:left;
	width:200px;
}

#feedbackTable td
{
	padding:5px;
	text-align:left;
}

#feedback_window_footer
{
	text-align:right;
	padding:5px;
	background-color:#D6E5E9;
}

#feedback_window_errorMsg
{
	color:red;
	font-size:11px;
	text-align:left;
}
.scrollDataStyle{
	font-family : verdana;
	font-weight : bold;
}
#allGallariesDisplay a:hover{
	text-decoration:none;
}
.pft-sec a:hover{
text-decoration:none;
}
.buttonStyle {
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
	.updatesDiv{

 background: none repeat scroll 0 0 powderBlue;
    border-bottom: 3px solid blue;
    border-radius: 5px 5px 0 0;
    height: 20px;
    margin-left: -9px;
    margin-top: -8px;
    padding: 5px 17px 7px;
    width: 401px;
	padding-left:40px;
	 
	}
	.updatesDiv a{
		 border-radius: 5px 5px 0 0;
    margin: 0;
    padding: 10px 18px 10px;

    text-decoration: none;
	}
	.updatesDiv a:hover,.updatesDiv a.current{
	background:blue;
	color:#FFFFFF;

	}



</style>
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
               <a href="freeUserRegistration.action"><img alt="" src="images/icons/homePage_new/updatebutton.jpg" style="padding-left:13px;" ></img></a>
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
            <h1 class="main-title">Quick View</h1>
            <div class="sr-sec">
              <div class="sr-mbg">
                <div class="glossymenu"> <a class="menuitem submenuheader" href="javascript:{}">View Your State.</a>
                  <div class="submenu">
                    <p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
                    <div class="view-results"><a href="javascript:{}" onclick="navigateToStatePage()">view results</a></div>
                  </div>
                  <a class="menuitem submenuheader" href="#" >View Your district.</a>
                  <div class="submenu">
				  <div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
                    <p>Select your district to view its election results in district level.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
                    <div class="view-results"><a href="javascript:{}" onclick="navigateToDistrictPage()">view results</a></div>
                  </div>
                   <a class="menuitem submenuheader" href="#">View Your Constituency.</a>
                  <div class="submenu">
				  <div id="alertMessage" style="color:red;font-weight:bold;"></div>
                    <p>Select Constituency Type<br />
                      <span class="radio-type">
                      <input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/>
                      Assembly</span> <span class="radio-type">
                      <input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/>
                      Parliament</span> </p><br />
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
                    <div class="view-results"><a href="javascript:{}" onclick="navigateToConstituencyPage()">view results</a></div>
                  </div>
                  
                  <a class="menuitem submenuheader" href="javascript:{}">View Your Localty.</a>
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
							<div class="view-results"><a onclick="navigateToLocalBodyPage()" href="javascript:{}">view results</a></div>
                  </div>
						                   
						<a class="menuitem submenuheader" href="javascript:{}">View all Election Results.</a>
                  <div class="submenu" style="padding-bottom:5px;">
                    <table>
						<tr>
							<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									
						</tr>			<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="statesList" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td><td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>			
									</tr>		
									
									<tr><td><select id="electionLists" class="selectBoxWidth" onchange="getElectionYearsInHomePage((this.options[this.selectedIndex].text))" ></select></td></tr>
									
									<tr><td><select id="electionYears" class="selectBoxWidth"></select></td></tr>
								</table>
                    <div class="view-results"><a href="javascript:{}" onclick="viewElectionResults()">view results</a></div>
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
         <marquee onmouseover="this.stop()" onmouseout="this.start()" scrollDelay="180" >
          <font class="scrollDataStyle" color="blue"><u>News</u> : Now You Can View/Analyse <a href="electionDetailsReportAction.action?electionId=136&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2007">Punjab</a>, <a href="electionDetailsReportAction.action?electionId=158&stateID=27&stateName=Uttar Pradesh&electionType=Assembly&electionTypeId=2&year=2007">Uttar Pradesh</a>, <a href="electionDetailsReportAction.action?electionId=134&stateID=26&stateName=Uttaranchal&electionType=Assembly&electionTypeId=2&year=2007">Uttaranchal
		  </a>
		  <a href="electionDetailsReportAction.action?electionId=173&stateID=16&stateName=Manipur&electionType=Assembly&electionTypeId=2&year=2007">Manipur</a>, <a href="electionDetailsReportAction.action?electionId=165&stateID=6&stateName=Goa&electionType=Assembly&electionTypeId=2&year=2007">Goa</a>, <a href="electionDetailsReportAction.action?electionId=126&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2007">Gujarat</a> States Previous Election Results to know Present Parties & Candidates Previous Results, <a href="javascript:{}"  onclick="openAssembly2011Window()">Pulivendula Bi Election 2011 Results</a> , <a href="javascript:{}"  onclick="openKadapa2011Window()">Kadapa Bi Election 2011 Results</a> , <a href="javascript:{}"  onclick="openTN2011Window()">Tamil Nadu Assembly Election 2011 Results</a> , <a href="javascript:{}"  onclick="openWB2011Window()">West Bengal Assembly Election 2011 Results</a> , <a href="javascript:{}"  onclick="openKerala2011Window()">Kerala Assembly Election 2011 Results</a> , <a href="javascript:{}"  onclick="openAssam2011Window()">Assam Assembly Election 2011 Results</a> , <a href="javascript:{}"  onclick="openPD2011Window()">Puducherry Assembly Election 2011 Results</a>
		</font>
          </marquee>
            <span class="fright"><img	 src="./images/new_homepage/blue-right-arrow.gif" alt=""/></span> </div>
        </div>
        
        <!--LATEST NEWS AND UPDATES SECTION END--> 
        
        <!--QA AND CURRENT ELECTION SECTION START-->
        
        <div class="qa-current-election-sec"> 
          
          
          
          <div class="qa-sec">
		  
		  
<div style="width: 465px;">
		
		<div style="margin-left: -9px; height: 43px; margin-top: -3px;">
		<h1 style="color:#FF4705;float:left;font-size:13px; text-transform:uppercase;">Updates From Party Analyst</h1>
		</div>
		<div class="updatesDiv">

		<a onclick="buildVideoGallary()" href="javascript:{}" id="videoGallaryButton" class="current">Video Gallary</a>

		  <a onclick="buildPhotoGallary()"  href="javascript:{}" id="photoGallaryButton">Photo Gallary</a>
			
		  <a onclick="buildNewsGallary()" href="javascript:{}" id="newsGallaryButton">News Gallary
			</a>
			</div>
			&nbsp;
			 <div id="allGallariesDisplay" style="overflow-x: hidden; overflow-y: scroll; padding-right: 0px; border-right-width: 17px; margin-left: -11px; width: 465px; height: 250px;"></div>
			</div>
		
</div>
		

          
          
          <!--CURRENT ELECTION SECTION START-->
          
          <div class="ce-sec" style="padding-top:0px;">
	<h1 class="title"><span class="orange">Current </span>Elections</h1> <div id="wrap">
              <div id="list">
                <div class="prev"><img src="./images/new_homepage/wallpapers-left-arrow.png" alt="prev" /></div>
                <div class="slider">
                  <ul>
				  <li> <a href="specialPageAction.action?specialPageId=3" title="UttarPradesh 2012 Election"><img src="./images/new_homepage/uttarpradesh.png" alt="UttarPradesh 2012 Election" /> <span>UttarPradesh</span> </a></li>
				  <li> <a href="specialPageAction.action?specialPageId=4" title="Punjab 2012 Election"><img src="images/new_homepage/punjab.png" alt="Punjab 2012 Election" /> <span>Punjab</span> </a></li>
				  <li> <a href="specialPageAction.action?specialPageId=5" title="Uttarakhand 2012 Election"><img src="./images/new_homepage/uttaranchal.png" alt="Uttarakhand 2012 Election"/> <span>Uttarakhand</span> </a></li>
				  <li> <a href="specialPageAction.action?specialPageId=7" title="Goa 2012 Election"><img src="./images/new_homepage/goa.jpg" alt="Goa 2012 Election"/> <span>Goa</span> </a></li>
                  <li> <a href="specialPageAction.action?specialPageId=6" title="Manipur 2012 Election"><img src="./images/new_homepage/manipur.png" alt="Manipur 2012 Election" /> <span>Manipur</span> </a></li>
				</ul>
                </div>
                <div class="next" style="right:-30px;"><img src="./images/new_homepage/wallpapers-right-arrow.png" alt="next" /></div>
              </div>
            </div>
            
          </div>          

<div class="ce-sec" >
<h1 class="title"><span class="orange">PartyAnalyst </span>Launch</h1> <div id="wrap1">
              <div id="list">
                <div class="prev1"></div>
                <div class="slider1">
                  <ul>
                    <li> <a href="http://www.youtube.com/watch?v=mMTRWXNVXCw" title="Overview" target="_blank"><img src="http://img.youtube.com/vi/mMTRWXNVXCw/0.jpg" alt="PartAnalyst" /> <span>PartyAnalyst Overview</span> </a></li>
                    <li> <a href="http://www.youtube.com/watch?v=PKZpPe1pYIw" title="Media" target="_blank"><img src="http://img.youtube.com/vi/PKZpPe1pYIw/0.jpg" alt="PartAnalyst"/> <span>Media coverage</span> </a></li>
                    <li> <a href="http://www.youtube.com/watch?v=3k9vFj0Ca54" title="Media" target="_blank"><img src="http://img.youtube.com/vi/3k9vFj0Ca54/0.jpg" alt="Title 3" /> <span>Media coverage</span> </a></li>
					
                  </ul>
                </div>
                <div class="next1" style="right:-30px;"><img src="./images/new_homepage/wallpapers-right-arrow.png" alt="next" /></div>
              </div>
            </div>
            
          </div>          

          <!--CURRENT ELECTION SECTION END--> 
          
        </div>
        
        <!--QA AND CURRENT ELECTION SECTION END-->
        
        <div class="clear"></div>
        
        <!--SNEAK PEAK - PARTY ANALYST SECTION START-->
        
        <div class="sneak-peak-sec" style="width:630px;">
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

		<!--QA CONTENT SLIDE SECTION START-->
        
          <div class="pft-social-sec" style="width:308px;">
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
                <div class="a-theme" style="width:300px;">They can know their leaders in better way, they can publish their constituency problems,
				they can connect their local people and leaders. They can discuss about their parties and leaders. </div>
							  </li>
							   <li>
                <div class="q-icon">q</div>
                <div class="q-theme">How Party Analyst is useful to Politicians?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="width:300px;">They can get all elections information in a single platform, they can know which area they performed well and
				in which area they need to improve. They can maitain Cadre, Influencing People and Influencing Groups information, and
				they can improve the communication channels with them. They can maintain all news, photo galleries and videos in one place.</div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">How Party Analyst is useful to Political Parties?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="border-bottom:none;width:300px;">They can get all elections information in a single platform, they can analyze the elections based on geographically,
				demographically, political alliances and based on previous elections. They can maintain cadre, influencing people, groups
				information in one place and improves the communication channels with them. </div>
							  </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">What are the different types users in Party Analyst?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="width:300px;">1) Free User 2) Registered User 3) Commercial User</div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">What are the other services provided by Party Analyst?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="width:300px;">Apart online we can also provide detailed Election Analysis Reports for any Constituency.
				Election Surveys
				Voters details with Caste Analysis
				Profile Maintainance - integrations of news, videos, photos
				Call Center Services - Remote Office to Politicians.
                </div>
              </li>
              <li>
                <div class="q-icon">q</div>
                <div class="q-theme">Want to Know More?</div>
                <div class="clear"></div>
                <div class="a-icon">a</div>
                <div class="a-theme" style="border-bottom:none;width:300px;">Call us at +91 9676696760 or +91 40 4012 4153<br />
                Drop an email to a.dakavaram@itgrids.com or a.dakavaram@partyanalyst.com</div>
              </li>
							  </ul>
			<script type="text/javascript" src="js/vscroll.js"></script> 
            <script type="text/javascript" src="js/vcontent-scroll.js"></script> 

			<!--QA CONTENT SLIDE SECTION END--> 
		
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
<div id="popupDiv" style="display: block; margin-left: auto; margin-right: auto; width: 850px;">
<c:if test='${sessionScope.loadingFirstTime == true}'>
	<div id="fancydivbox" style="position: absolute; z-index: 1000001;top: -560px;">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
	</c:if>

</div>	

<!--CONTENT MAIN SECTION END--> 
 
<script type="text/javascript">
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
var loadingFirstTime = '${sessionScope.loadingFirstTime}';
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

$(function() {
    		$(".slider1").jCarouselLite({
        		btnNext: ".next1",
        		btnPrev: ".prev1",
        		visible: 3
    		});
		});

		$(document).ready(function(){
			$('img.captify').captify({

				speedOver: 'fast',

				speedOut: 'normal',

				hideDelay: 500,	

				animation: 'slide',		

				prefix: '',		

				opacity: '0.7',					

				className: 'caption-bottom',	

				position: 'bottom',

				spanWidth: '100%'
			});
		});

	function buildPhotoGallary()
	{
		$(".updatesDiv a").removeClass("current");
		$("#photoGallaryButton").addClass("current");

		if(homePageUpdates == null)
			return;
		var str ='';
		for(var i in homePageUpdates.photogallary)
		{
			str +='<table>';
			str += '<tr>';
			str += '<td>';
			str +='<a href="';
			
			if(homePageUpdates.photogallary[i].fileType == 'Candidate')
				str += 'candidateElectionResultsAction.action?candidateId=';
			else if(homePageUpdates.photogallary[i].fileType == 'Party')
				str += 'partyPageAction.action?partyId=';
			else if(homePageUpdates.photogallary[i].fileType == 'Special Page')
				str += 'specialPageAction.action?specialPageId=';

			str += ''+homePageUpdates.photogallary[i].candidateId+'&contentId='+homePageUpdates.photogallary[i].contentId+'">';
			str += '<img src="'+homePageUpdates.photogallary[i].pathOfFile+'" style="width:95px;height:80px" alt="'+homePageUpdates.photogallary[i].fileTitle1+'" title="'+homePageUpdates.photogallary[i].description+'"/>';
			str += '</td>';
			str +='</a>';
			str += '<td>';
			str +='<a href="';
			
			if(homePageUpdates.photogallary[i].fileType == 'Candidate')
				str += 'candidateElectionResultsAction.action?candidateId=';
			else if(homePageUpdates.photogallary[i].fileType == 'Party')
				str += 'partyPageAction.action?partyId=';
			else if(homePageUpdates.photogallary[i].fileType == 'Special Page')
				str += 'specialPageAction.action?specialPageId=';

			str += ''+homePageUpdates.photogallary[i].candidateId+'&contentId='+homePageUpdates.photogallary[i].contentId+'" title="'+homePageUpdates.photogallary[i].description+'" style="color: #000000;font-family: verdana;">';
			str += '<h5 style="color:#357EC7 !important;">'+homePageUpdates.photogallary[i].fileTitle1+'</h5>';
			str += 'New Photo Added to';
			str +='\t';
			str +=''+homePageUpdates.photogallary[i].candidateName+'';
			str +='\t';
			str += ''+homePageUpdates.photogallary[i].gallaryName+'';
			str +='\t';
			str +='Photo Gallary';
			str += '</td>';
			str +='</a>';
			str +='</tr>';
			str += '</table>';
			str +='</div>';
			document.getElementById("allGallariesDisplay").innerHTML = str;
		} 
			
	}

	function buildVideoGallary()
	{	
		$(".updatesDiv a").removeClass("current");
		$("#videoGallaryButton").addClass("current");
		if(homePageUpdates == null)
			return;

		var str ='';
		for(var i in homePageUpdates.VideoGallary)
		{
			str += '<table>';
			str += '<tr>';
			str += '<td>';
			str += '<a href="';
			
			if(homePageUpdates.VideoGallary[i].fileType == 'Candidate')
				str += 'candidateElectionResultsAction.action?candidateId=';
			else if(homePageUpdates.VideoGallary[i].fileType == 'Party')
				str += 'partyPageAction.action?partyId=';
			else if(homePageUpdates.VideoGallary[i].fileType == 'Special Page')
				str += 'specialPageAction.action?specialPageId=';

			str += ''+homePageUpdates.VideoGallary[i].candidateId+'&contentId='+homePageUpdates.VideoGallary[i].contentId+'">';
			str += '<img src="http://img.youtube.com/vi/'+homePageUpdates.VideoGallary[i].pathOfFile+'/1.jpg" style="width:95px;height:80px" alt="'+homePageUpdates.VideoGallary[i].fileTitle1+'" title="'+homePageUpdates.VideoGallary[i].description+'"/>';
			str += '</td>';
			str +='</a>';
			str += '<td>';
			str +='<a href="';
			
			if(homePageUpdates.VideoGallary[i].fileType == 'Candidate')
				str += 'candidateElectionResultsAction.action?candidateId=';
			else if(homePageUpdates.VideoGallary[i].fileType == 'Party')
				str += 'partyPageAction.action?partyId=';
			else if(homePageUpdates.VideoGallary[i].fileType == 'Special Page')
				str += 'specialPageAction.action?specialPageId=';
			
			str += ''+homePageUpdates.VideoGallary[i].candidateId+'&contentId='+homePageUpdates.VideoGallary[i].contentId+'" title="'+homePageUpdates.VideoGallary[i].description+'" style="color: #000000;font-family: verdana;">';
			str += '<h5 style="color:#357EC7 !important;">'+homePageUpdates.VideoGallary[i].fileTitle1+'</h5>';
			str += 'New Video Added to';
			str +='\t';
			str +=''+homePageUpdates.VideoGallary[i].candidateName+'';
			str +='\t';
			str += ''+homePageUpdates.VideoGallary[i].gallaryName+'';
			str +='\t';
			str +='Video Gallary';
			str +='</a>';
			str += '</td>';
			str +='</tr>';
			str += '</table>';
			document.getElementById("allGallariesDisplay").innerHTML = str;
		}
	}

	function buildNewsGallary()
	{
		$(".updatesDiv a").removeClass("current");
		$("#newsGallaryButton").addClass("current");

		if(homePageUpdates == null)
			return;
		var str ='';
		
		for(var i in homePageUpdates.NewsGallary)
		{
			str +='<table>';
			str += '<tr>';
			str += '<td>';
			str +='<a href="';

			if(homePageUpdates.NewsGallary[i].fileType == 'Candidate')
				str += 'candidateElectionResultsAction.action?candidateId=';
			else if(homePageUpdates.NewsGallary[i].fileType == 'Party')
				str += 'partyPageAction.action?partyId=';
			else if(homePageUpdates.NewsGallary[i].fileType == 'Special Page')
				str += 'specialPageAction.action?specialPageId=';

			str += ''+homePageUpdates.NewsGallary[i].candidateId+'&contentId='+homePageUpdates.NewsGallary[i].contentId+'">';
			str += '<img src="'+homePageUpdates.NewsGallary[i].pathOfFile+'" style="width:95px;height:80px" alt="'+homePageUpdates.NewsGallary[i].fileTitle1+'" title="'+homePageUpdates.NewsGallary[i].description+'"/>';
			str += '</td>';
			str +='</a>';
			str += '<td>';
						
			if(homePageUpdates.NewsGallary[i].fileType == 'Candidate')
				str += '<a href="candidateElectionResultsAction.action?candidateId='+homePageUpdates.NewsGallary[i].candidateId+'&contentId='+homePageUpdates.NewsGallary[i].contentId+'" title="'+homePageUpdates.NewsGallary[i].description+'" style="color: #000000;font-family: verdana;">';
			else if(homePageUpdates.NewsGallary[i].fileType == 'Party')
				str += '<a href="partyPageAction.action?partyId='+homePageUpdates.NewsGallary[i].candidateId+'&contentId='+homePageUpdates.NewsGallary[i].contentId+'" title="'+homePageUpdates.NewsGallary[i].description+'" style="color: #000000;font-family: verdana;">';
			else if(homePageUpdates.NewsGallary[i].fileType == 'Special Page')
				str += '<a href="specialPageAction.action?specialPageId='+homePageUpdates.NewsGallary[i].candidateId+'&contentId='+homePageUpdates.NewsGallary[i].contentId+'" title="'+homePageUpdates.NewsGallary[i].description+'" style="color: #000000;font-family: verdana;">';
			
			str += '<h5 style="color: #357EC7 !important;">'+homePageUpdates.NewsGallary[i].fileTitle1+'</h5>';
			str += 'New News Added to';
			str +='\t';
			str +=''+homePageUpdates.NewsGallary[i].candidateName+'';
			str +='\t';
			str += ''+homePageUpdates.NewsGallary[i].gallaryName+'';
			str +='\t';
			str +='News Gallary';
			str +='</a>';
			str += '</td>';
			str +='</tr>';
			str += '</table>';
			document.getElementById("allGallariesDisplay").innerHTML = str;
			}
		}
photosNewsVideosUpdateForACandidate();
</script>
	
</body>
</html>
