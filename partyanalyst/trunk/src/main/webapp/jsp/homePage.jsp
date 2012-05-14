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
<link media="screen" href="js/fancybox/jquery.fancybox-1.3.4.css" type="text/css" rel="stylesheet">
<!-- Combo-handled YUI JS files: --> 

<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script src="js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
<script src="js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript">	</script>
  
<script type="text/javascript">
var homePageUpdates = null;
google.load("visualization", "1", {packages:["piechart"]});
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

.paginatorElmtClass a {
    border: 1px solid #ADADAD;
    margin: 0 3px;
    padding: 3px;
	font-size:11px;
   text-decoration: none;
}	
.pagenationStyle{
  background-color:#6AC9C1;
}
.problemheadClass{
  padding:8px;
  border-radius:10px;
  margin-right: 10px;
  margin-bottom:10px;
  margin-top:15px;
  background-color:#F7F7F7;
}
.problemTitleClass{
  color:#357EC7;
  font-weight:bold;
  padding-right:10px;
  font-size:15px;
  font-family: trebuchet MS;
}
.social-icons {
    width: 311px;
}
</style>
</head>

<body>
<!--<div id="feedback_window"><div id="feedback_window_inner"></div></div>-->
<div id="username_change_window" style="background-color: #C7CFD2;">
	<div id="username_change_window_inner"></div></div>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
	<div id="knowMore_window"><div id="knowMore_window_inner"></div></div>

	<div id="assembly_2011_window"><div id="assembly_2011_window_inner"></div></div>
	
	<!--<div id="contactWindowDiv"><div id="contactWindowDiv_window_inner"></div></div>-->
	
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
                      <input type="radio" checked="checked" name="assembly_radio" id="assembly_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/>
                      Assembly</span> <span class="radio-type">
                      <input type="radio" name="assembly_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/>
                      Parliament</span> </p><br />
					  <table id="stateTable" style="display:none;">
									<tr>
										<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"  onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/>
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
          <font class="scrollDataStyle" color="blue"><u style="color:#BF00FF;">News</u> :
		  
		  <a href="specialPageAction.action?specialPageId=8" title="Click here to View Andhra Pradhesh State 2012 Bye Results, Latest News Updates, Photos, Videos and Previous Results">AP Bielection Schedule:Election Commission of India Announced Andhra Pradesh Bi-Election Schedule For 18 Assembly Constituencies and One Parliament Constituency  in this summer. All Parties treated there elections as semi finals and mini General elections,Andhra Pradesh 2012 Assembly Bye Elections Results : Adilabad - TRS, Kamareddy - TRS, Kollapur - TRS, Station Ghanpur - TRS, Mahabubnagar - BJP, Nagarkurnool - Nagam Janardhana Reddy(IND), Kovur - YSRC.</a>&nbsp;

		  <a href="statePageAction.action?statePageId=12" title="Click here to View Karnataka State Details">Karnataka</a> Bye Election Results :  <a href="constituencyPageAction.action?constituencyId=709" title="Click here to View Udipi Chikmagalur Constituency Details, Election Results and Voting Trendz">Udipi Chikmagalur</a> - <a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Profile - Previous Election Results, News Updates, Photos, Videos">INC</a>.&nbsp; 
		  <a href="statePageAction?action?stateId=24" title="Click here to view Tamil Nadu State Details">Tamil Nadu</a> Bye Election Results : <a href="constituencyPageAction.action?constituencyId=1353" title="Click here to View Sankarankoil Constituency Details, Election Results and Voting Trendz">Sankarankoil</a> - <a href="partyPageAction.action?partyId=987" title="Click here to view ADMK Party Profile - Previous Election Results, News Updates, Photos, Videos">ADMK</a>.&nbsp;

		 <a href="statePageAction.action?statePageId=13" title="Click here to view Kerala State Details">Kerala</a> Bye Election Results : <a href="constituencyPageAction.action?constituencyId=1694" title="Click here to View Piravom Constituency Details, Election Results and Voting Trendz">Piravom</a> - <a href="partyPageAction.action?partyId=1149" title="Click here to view UDF Party Profile - Previous Election Results, News Updates, Photos, Videos">UDF</a>.&nbsp;

		 <a href="statePageAction.action?statePageId=7" title="Click here to view Gujarat State Details">Gujarat</a> Bye Election Results :  <a href="constituencyPageAction.action?constituencyId=34860" title="Click here to view Mansa Constituency Details, Election Results and Voting Trendz">Mansa</a> - <a href="partyPageAction.action?partyId=362" title="Click here to view INC Party Details">INC</a>.

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
					<li> <a href="specialPageAction.action?specialPageId=8" title="Bye Elections In Telangana"><img src="./images/new_homepage/Chadra Babu _ KCR.png" alt="Bye Elections In Telangana" /> <span>AP Bye Elections - 2012</span> </a></li>
					<li> <a href="specialPageAction.action?specialPageId=8" title="Bye Election In Kovur"><img src="./images/new_homepage/Kiran Kumar _Y.S. Jagan.png" alt="Bye Election In Kovur" /> <span>Kovur Bye Election</span> </a></li>
    				 <li> <a href="specialPageAction.action?specialPageId=3" title="UttarPradesh 2012 Election"><img src="./images/new_homepage/uttarpradesh_Mulayam.png" alt="UttarPradesh 2012 Election" /> <span>UttarPradesh</span> </a></li>
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
                    <li> <a href="specialPageAction.action?specialPageId=1&contentId=7593" title="Overview" target="_blank"><img src="http://img.youtube.com/vi/mMTRWXNVXCw/0.jpg" alt="PartAnalyst" /> <span>PartyAnalyst Overview</span> </a></li>
                    <li> <a href="specialPageAction.action?specialPageId=1&contentId=7592" title="Media" target="_blank"><img src="http://img.youtube.com/vi/PKZpPe1pYIw/0.jpg" alt="PartAnalyst"/> <span>Media coverage</span> </a></li>
                    <li> <a href="specialPageAction.action?specialPageId=1&contentId=7594" title="Media" target="_blank"><img src="http://img.youtube.com/vi/3k9vFj0Ca54/0.jpg" alt="Title 3" /> <span>Media coverage</span> </a></li>
					
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
        
        <div class="sneak-peak-sec" style="width:615px; border-right: 1px dotted;margin-right: 10px;">
          <h1 class="title tc-tf" style="color:#C66E17;margin-left:23px;margin-bottom:-19px;">Problems In Your Area</h1>
		  
              <div style="height: 325px;overflow-y:scroll;" id="problemsShowDIV"></div>
									
		      <div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;"></div>
                    
         <div style="margin-top:10px;"> <p>The information displayed in this website are based on the data collected from the Election Commmission Of India. Further suggestions and corrections please contact us at <a href="mailto:info@itgrids.com" class="blue">info@itgrids.com</a></p></div>
        </div>
        
        <!--SNEAK PEAK - PARTY ANALYST SECTION END--> 


		<!-- Opinion Poll Start-->
								
												<!--<div id="pollsWidgetHeader">
													
												
												<table><tr><td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td width="98%">
												<div class="electionTrendzHeaderBackground_center">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										  </table>
											
										 	
							
									<div id="pollsWidgetBody" class="yui-skin-sam" style="height:293px;">
							</div>
									<div id="pollsWidgetFooter">
										
									</div>
								</div>-->
						
						<div>
						<div id="pollsWidgetHeader">
										<table width="34%" border="0" cellspacing="0" cellpadding="0">
										
										
										  <tr>
										 	<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td>
												<div class="electionTrendzHeaderBackground_center">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										</table>	
									 </div>
									<div id="pollsWidgetBody" style="height:263px;width: 318px;">
									</div>
									<div id="pollsWidgetFooter">
										
									</div></div>
								
							<!-- Opinion Poll End-->

		<!--QA CONTENT SLIDE SECTION START-->
      <!--  <c:if test="${loginStatus != 'true' && sessionScope.UserType != 'FreeUser'}">
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
            <script type="text/javascript" src="js/vcontent-scroll.js"></script> </c:if>-->

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
<a id="inline" href="#fancydivbox" style="displaynone"></a>
<div id="promodiv" style="display:none;">
	<div id="fancydivbox">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
</div>

<!--CONTENT MAIN SECTION END--> 

<script type="text/javascript">
var clickid = null;
function copyId(id)
{
  clickid = id;
}
var custom_paginator = {
	resultsCount:"",
	startIndex:"",
	ajaxCallURL:"",
	initialParams:"",
	resultsShown:"",
	callBackFunction:"",
	jsObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jsObj = obj.jsObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.resultsCount = obj.resultsCount;		
	},	
	doAjaxCall:function(start){

		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {			
	    success : function( o ) {
			try 
			{				
				results = YAHOO.lang.JSON.parse(o.responseText);
				
				if(results != null && results.length>0)
				    this.totalRecords = parseInt(results[0].totalResultsCount);	
				else
                     this.totalRecords = 0;
					 this.buildPaginator();
				this.callBackFunction();
				
			}
			catch (e)
			{   		
				alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){		
		this.doAjaxCall(this.startIndex);
	},
	buildPaginator:function()
	{
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{			
				str += '<a href="javascript:{}" id="customPaginationId'+i+'" onclick="copyId(this.id);custom_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
     	  document.getElementById("custom_paginator_class").innerHTML = str;
		if(clickid != null)
		{
		 $("#"+clickid).addClass('pagenationStyle');
		}
		else
		{
		  $("#customPaginationId1").addClass('pagenationStyle');
		}
	}
};

  $(document).ready(function() {
    $('#custom_paginator_class a').live("click",function() {
	   
        $('#custom_paginator_class a').removeClass('pagenationStyle');
		$(this).addClass('pagenationStyle');
      });
  });
custom_paginator.totalRecords = '${problemCount}';
var news_Obj = {
	
	problemStatus:[],
	startIndex:0,
	problemsCount:3,
	
	initialize:function(){
		
		this.getProblemDetails();

	},
	getProblemDetails:function()
    {  	
        var jsObj=
		{		
				task:"getProblemDetails"						
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getProblemDetailsForHomePageAction.action?"+rparam;

	    custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.problemsCount,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
	    	showProblemDetails(results);
			}
		});
		
		custom_paginator.initialize();
    }
	};
news_Obj.initialize();
function showProblemDetails(result)
{
    var probEle = document.getElementById("problemsShowDIV");
	var str = '';
	for(var i in result)
	{
	   str += '<div class ="problemheadClass">';
	   str += '   <div ><span><a class ="problemTitleClass" href="problemCompleteDetailsAction.action?problemHistoryId='+result[i].problemHistoryId+'" >'+(result[i].problem).toUpperCase()+'</a></span>  <img width="20" height="20" src="images/icons/accept.png" title="Like"><font color="#FF4500;"><b style="margin-left:5px;">'+result[i].likesCount+'</b></font> <img width="20" style="margin-left:15px;" height="20" src="images/icons/reject.png" title="Dislike"><font color="#FF4500;"><b style="margin-left:5px;">'+result[i].dislikesCount+'</b></font> <span style="color:#A71100;margin-left:15px;">Comments :</span><font color="#FF4500;"><b style="margin-left:5px;">'+result[i].commentCount+'</b></font></div>';
	   str += '   <div style="padding-top:5px;font-family:arial;">'+result[i].description+' </div>';
	   str += '   <div style="padding-top:5px;"> <span style="color:#028D35;">Location :&nbsp;&nbsp;</span> '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+' &nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#028D35;">Posted By :&nbsp;&nbsp;</span>'+initialCap(result[i].name)+' </div>'; 
	   str += '   <div style="padding-top:5px;"> <span style="color:#028D35;">Existing From :&nbsp;&nbsp;</span> '+result[i].existingFrom+' &nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#028D35;">Identified On :&nbsp;&nbsp;</span>'+result[i].postedDate+' </div>';
       str += '   <div style="padding-top:5px;"> <span style="color:#028D35;">Files Attached :&nbsp;&nbsp;</span>'+result[i].fileCount+' </div>';	   
	   str += '</div>';
	}
	probEle.innerHTML = str;
	
}
function initialCap(data) {
   data = data.substr(0, 1).toUpperCase() + data.substr(1).toLowerCase();
   return data;
}
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
var loadingFirstTime = '${sessionScope.loadingFirstTime}';
$(document).ready(function(){
if(loadingFirstTime == 'true'){
		$("#inline").fancybox();
		$("#inline").trigger("click");
		}
	});
//buildHOmePageChartsSlider();
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
function callAJAX(jsObj,url){
	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "checkAnanymousUserNameAvailability" || 
						jsObj.task == "saveUserEmailAndsetAsUserName")
					{
						showDetails(results);
					}
					else if(jsObj.task == "saveUserEmailAndSendPwd")
					{
						showEmailStatus(results);
					}
					else if(jsObj.task == "getProblemDetails")
					{
                          showProblemDetails(results);
					}
					
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
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
		str += '<div id="feedback_window_head">Your UserName is changed </div>';
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
		var url = "checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
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
		var url = "checkAnanymousUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);

	}
	
	
	}

function changeUserNameAfterEmailSave()
{
	var email = document.getElementById('emailField').value;

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
		var url = "checkAnanymousFreashUserNameAvailabilityAction.action?"+rparam;						
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
		var url = "checkAnanymousUserNameAvailabilityAction.action?"+rparam;						
		callAJAX(jsObj,url);

	}
	
}

function saveEmailAndSetAsUserName()
{

	var email = document.getElementById('emailField').value;
	var errorDivEle = document.getElementById("ErrorMsgDivId");
	var setAsUserName = document.getElementById("changeUserNameCheck").checked;
	var eFlag = false;
	var str = '<font color="red">';
	var task = null;
	
	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
      if(email !='' && email!='your email'){
          
		  if(!email.match(emailExp)){

				document.getElementById("ErrorMsgDivId").innerHTML = '<font color="red">Please enter valid Email</font>';
				return;
		  }
	  }
	 else {
		document.getElementById("ErrorMsgDivId").innerHTML ='<font color="red">Please enter Email id</font>';  
		return;
	 }

	str += '</font>';

	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	if(setAsUserName)
		task = "saveUserEmailAndsetAsUserName";
	else
		task = "saveUserEmailAndSendPwd";

	var jsObj=
	{
			userName : uname,
			email : email,
			task : task

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserEmailAction.action?"+rparam;						
	callAJAX(jsObj,url);
}

function saveEmailForUser()
{

	var email = document.getElementById('emailField').value;
	var errorDivEle = document.getElementById("ErrorMsgDivId");
	var eFlag = false;
	var str = '<font color="red">';
	
	
	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
      if(email !='' && email!='your email'){
          
		  if(!email.match(emailExp)){

				document.getElementById("ErrorMsgDivId").innerHTML = '<font color="red">Please enter valid Email</font>';
				return;
		  }
	  }
	 else {
		document.getElementById("ErrorMsgDivId").innerHTML ='<font color="red">Please enter Email id</font>';  
		return;
	 }

	str += '</font>';

	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	var jsObj=
	{
			userName : uname,
			email : email,
			task : "saveUserEmailAndSendPwd"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserEmailAction.action?"+rparam;						
	callAJAX(jsObj,url);
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
			height:130,
			show:'slide',
			modal:true
		});	
		$(".ui-dialog-titlebar").hide();

		var elmt = document.getElementById("username_change_window_inner");

		var str = '';
			
		if(emailId == ''|| emailId== null)
		{
			str += '<div id="feedback_window_body">';
			str += '<div id="feedback_window_head">Enter Your Email</div>';
			str += '	<div id="feedBackForm_div">';
			str += '		<table id="feedbackTable" width="100%">';
			str += '		<tr>';
			str += '			<div id="ErrorMsgDivId"></div>';
		 	str += '		<th><font color="red">*</font>Enter Your EmailId </th>';
			str += '		<td><input type="text" id="emailField" size="25"/></td>';
			str += '		</tr>';
			str += '		</table>';
			str += '	<input type="checkbox" checked="true" id="changeUserNameCheck"/>&nbsp;Do you want to change Email as Username';
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

			$("#username_change_window").css("height","160px");
			
			var oPushButton1 = new YAHOO.widget.Button("post");  
			oPushButton1.on("click",function(){
				saveEmailAndSetAsUserName();
			});
			var oPushButton2 = new YAHOO.widget.Button("cancelButton");
	
			oPushButton2.on("click",function(){
				$("#username_change_window").dialog("destroy");
			});
	 
		}
		else
		{
			str += '<div id="feedback_window_head">Do You Want To Change Your UserName As Your Email</div>';
	        str += '<input type="hidden" value='+emailId+' id="emailField" size="25"/>'; 
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

			$("#username_change_window").css("height","85px");

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
}
$(function() {
    		$(".slider1").jCarouselLite({
        		btnNext: ".next1",
        		btnPrev: ".prev1",
        		visible: 3
    		});
		});

		$(document).ready(function(){
		$("#inline").fancybox();
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




/*function showUserNameChangePanelAfterEmailSave(uname){
	
	var alertIndicator = '${changedUserName}';
	
	if(alertIndicator == null || alertIndicator == 'false')
		return;

	var reg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	

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
		 
		str += '<div id="feedback_window_body">';
	
        str += '<div id="feedback_window_body">';
		str += '	<div id="feedBackNote_div">';
		str += '		<table>';
		str += '		<tr>';
	
	
        str +='<input type="hidden" id="emailField" size="25"/>';
		str += '		<tr>';
       	str += '		<th></th>';
        str += '		<td>';
        str += '		</td>';
		str += '		</tr>';
		
		str += '		</table>';
		str += '	</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '		<input id="postButtonId" type="button" value="Yes"></input>';
		str += '		<input style="width:52px; text-align:center;" id="cancelButton" type="button" value="No"></input>';
		str += '	</td>';
		
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton3 = new YAHOO.widget.Button("postButtonId");  
		var oPushButton4 = new YAHOO.widget.Button("cancelButton");

		oPushButton3.on("click",function(){
			changeUserNameAfterEmailSave();
		});

		oPushButton4.on("click",function(){
			$("#username_change_window").dialog("destroy");
		});
	
	
}*/

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

function CancelDialogue()
{
document.getElementById("username_change_window").style.display = 'none';
		

}
function showEmailStatus(results)
{
	var elemt =document.getElementById('feedback_window_head');
	var str = '';
	
	if(results.resultCode == 0)
	{
	
		str += '<font color="#000000">Email Saved Successfully</font>';
		//elemt.innerHTML = str;
		setTimeout("closewindow()",2000);
		//showUserNameChangePanelAfterEmailSave(uname);
		
		//str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
		
	}
	else
		
		str += '<font color="red"><b>Error Ocuured, Try Again.</b></font>';

	elemt.innerHTML = str;
	
}

function closewindow()
{
	$("#username_change_window").dialog("destroy");
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
			str +='Video Gallary.';
			str +='</a>';
			str +='<div style="margin-left:5px;margin-top:6px;">'
			str +='<font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"> Date : <span style="color:#FF4500;">'+homePageUpdates.VideoGallary[i].fileDate+'</font></span>&nbsp;';
			str +='<font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"> Source : </b><span style="color:#FF4500;">'+homePageUpdates.VideoGallary[i].source+'</font></span>';
			str +='</div>';
		
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
			str +='<div style="margin-left:135px;">'
			str +='<font style="font-weight:bold;font-size:12px;font-family: verdana;color:#000000;"> Date : <span style="color:#FF4500;">'+homePageUpdates.NewsGallary[i].fileDate+'</font></span>&nbsp;';
			str +='<font style="font-weight:bold;font-size:12px; font-family: verdana;color:#000000;"> Source : </b><span style="color:#FF4500;">'+homePageUpdates.NewsGallary[i].source+'</font></span>'; 
			str +='</div>';
			document.getElementById("allGallariesDisplay").innerHTML = str;
			}
		}
photosNewsVideosUpdateForACandidate();
buildPolls();
</script>
	
</body>
</html>
