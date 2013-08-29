<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Party Analyst - An Election Analysis &amp; Management Platform</title>
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
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"></link> 
<link media="screen" href="js/fancybox/jquery.fancybox-1.3.4.css" type="text/css" rel="stylesheet">
<!-- Combo-handled YUI JS files: --> 

 <!--BOOT STRAP START-->	
	
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="Assets/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="Assets/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed" href="Assets/ico/apple-touch-icon-57-precomposed.png">


<!--BOOT STRAP END-->

<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script src="js/fancybox/jquery.mousewheel-3.0.4.pack.js" type="text/javascript"></script>
<script src="js/fancybox/jquery.fancybox-1.3.4.pack.js" type="text/javascript">	</script>

 <script language="javascript" src="js/jquery.marquee.js"></script>
<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
<script type="text/javascript">

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
    font-size:11px;
   text-decoration: none;
   padding:5px;
   border-radius:50%;
   width:12px;height:14px;
   display:inline-block;
   text-align:center;
 
}	
.pagenationStyle{
  background-color:#F5371C;color:#fff;
}
.problemheadClass{
  padding:8px;
border:1px solid #cccccc;
  width:94%;
  margin:8px;
}
.problemTitleClass{
  color:#357EC7;
  font-weight:bold;
  padding-right:10px;
  font-size:15px;
  font-family: trebuchet MS;
}
.problem-register {
  margin-right:46px;
}
.partiesList{
    float:right;
    border: 1px solid #CCCCCC;
    border-radius: 5px 5px 5px 5px;
    display: block;
    float: right;
    margin-right: 20px;
    margin-top: 6px;
    width: 367px;
}
.partiesList a {

    text-align: center;
	 width: 58px;
	 padding: 7px;
}
.titleClass{
    background: none repeat scroll 0 0 #FFFFFF;
    border-bottom: 1px solid #CCCCCC;
    border-radius: 5px 5px 0 0;
    color: #0A0B39;
    font-size: 20px;
    padding: 7px 5px;
}
.listyle{
overflow: hidden; float: left; width: 64px; height: 90px;
}
.headingClass
{
color: #C66E17;
font-size: 15px;
font-weight: bold;
margin-bottom: 4px;
margin-left: 18px;
margin-top: 4px;
}
.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
.form-horizontal .radio{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;}
	
.opinionpoll .question{padding:5px;border-bottom:1px dashed #ccc;font:13px Arial;}
.opinionpoll .answer{border-bottom:1px dashed #ccc;padding:0px ;margin-bottom:5px;margin-left:19px;}
.opinionpoll .votebtn{margin:0px auto;display:block;width:75px;}
.resultdisplay a{display:inline-block;text-decoration:none;}
.resultdisplay .previouslink{ float:left;}
.resultdisplay .nextlink{ float:right;}

.opinionpoll .answer .span2{margin-left:0px;}
.opinionpoll .answer span{margin-left:10px;}

</style>
</head>

<body>
<script type="text/javascript">
var pollStatus = [];

</script>
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
                <div class="glossymenu"> <a class="menuitem submenuheader" href="javascript:{}">View Your State</a>
                  <div class="submenu">
                    <p>Select your state to view its Assembly, Parliament, Local Bodies election results.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/>
                    <div class="view-results"><a href="javascript:{}" onclick="navigateToStatePage()">view results</a></div>
                  </div>
                  <a class="menuitem submenuheader" href="#" >View Your district</a>
                  <div class="submenu">
				  <div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
                    <p>Select your district to view its election results in district level.</p>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"></s:select>
                    <s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/>
                    <div class="view-results"><a href="javascript:{}" onclick="navigateToDistrictPage()">view results</a></div>
                  </div>
                   <a class="menuitem submenuheader" href="#">View Your Constituency</a>
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
                  
                  <a class="menuitem submenuheader" href="javascript:{}">View Your Locality</a>
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
						                   
						<a class="menuitem submenuheader" href="javascript:{}">View Election Results</a>
                  <div class="submenu" style="padding-bottom:5px;">
                    <table>
						<tr>
							<td width="65%"><div id="electionDetailsErrorMsgDiv" style="display:none;"><font color="red"><b>*Select All Inputs</b></font></div></td>
									
						</tr>			
						
						<!--<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateLists" list="statesList" listKey="id" listValue="name" onchange="getElectionTypeValue((this.options[this.selectedIndex].value))"/></td><td><div id="stateLists_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></div></td>			
									</tr>		
									
									<tr><td><select id="electionLists" class="selectBoxWidth" onchange="getElectionYearsInHomePage((this.options[this.selectedIndex].text))" ></select></td></tr>
									
									<tr><td><select id="electionYears" class="selectBoxWidth"></select></td></tr>-->



									
		<tr>
		<td><select id="electionTypeId" name="electionType"  cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;width: 200px;"
		onchange="checkElectionType(this.options[this.selectedIndex].value)"/>
		<option value="0">Select Type</option>
		<option value="2">Assembly</option>
		<option value="1">Parliament</option>
		</select>	</div></td></tr>
						
			

		<tr><td>
		<select id="states" name="state_s" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;
		width: 200px;"
		onchange="getElectionYearsInHomePage('Assembly')">
		
			</select></td></tr>

		<tr><td><select id="electionYears" cssClass="textFieldStyle" cssStyle="width: 145px;margin-left:0px;" style="margin-left:12px;padding: 1px;
			width: 200px;"></select></td></tr></table>
						
								
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
        
        <div class="latest-nu-sec" style="padding:4px 15px 0px;">
          <h1 class="news-title"><b>Latest news &amp; Updates</b></h1>
          <div class="news-updates-scroll" style="line-height:19px;"> <span class="fleft"><img src="./images/new_homepage/blue-left-arrow.gif" style="margin-top:12px;" alt=""/></span>
         <marquee style="margin:6px 5px;" onmouseover="this.stop()" onmouseout="this.start()"  direction="left" scrollamount="3">
		 <font class="scrollDataStyle" color="blue">The Election Commission of India released Notification for General Election of <a href=" specialPageAction.action?specialPageId=13" > Gujarat</a> and <a href=" specialPageAction.action?specialPageId=14" > Himachal Pradesh </a>. <a href="specialPageAction.action?specialPageId=14" > Himachal Pradesh </a> (All 68ACs) Date of Poll is 04.11.2012. <a href="specialPageAction.action?specialPageId=13" > Gujarat</a> Election will be held in two phases, Date of Poll for Phase-I (87ACs) is 13.12.2012 and Phase-II (95ACs) is 17.12.2012 and Counting of Votes in 20.12.2012.
       	</font>
         </marquee>
            <span class="fright"><img	 src="./images/new_homepage/blue-right-arrow.gif" style="margin-top:12px;" alt=""/></span> </div>
        </div>
        
        <!--LATEST NEWS AND UPDATES SECTION END--> 
        
        <!--QA AND CURRENT ELECTION SECTION START-->
        
        <div class="qa-current-election-sec"> 
          
          
          
   <div class="qa-sec">
		  
		  
     <div style="width: 465px;">
		
		<div style="margin-left: -9px; height: 43px; margin-top: -3px;">
		  <h1 style="color:#FF4705;float:left;font-size:16px; text-transform:uppercase;line-height:12px;"><b>Updates From Party Analyst</b></h1>
		</div>
		<div class="updatesDiv">

		  <a onclick="showVideoGallary()" href="javascript:{}" id="videoGallaryButton" class="current">Video Gallery</a>

		  <a onclick="showPhotoGallary()"  href="javascript:{}" id="photoGallaryButton">Photo Gallery</a>
			
		  <a onclick="showNewsGallary()" href="javascript:{}" id="newsGallaryButton">News Gallery</a>
		</div>
			&nbsp;
			<div id="videoGallariesDisplay" style="overflow-x: hidden; overflow-y: scroll; padding-right: 0px; border-right-width: 17px; margin-left: -11px; width: 465px; height: 250px;">
			 <s:if test="resultMap != null && resultMap.size() > 0">
			 <s:iterator value="resultMap.VideoGallary" var="videoGallaryDetails">
			    <table>
			      <tr>
			       <td>
			        <s:if test="%{#videoGallaryDetails.fileType == 'Candidate'}" >         
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
                    <img src='http://img.youtube.com/vi/<s:property value="pathOfFile"/>/1.jpg' style="width:95px;height:80px" alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>			         
				  </td>		
			      <td>
			        <s:if test="%{#videoGallaryDetails.fileType == 'Candidate'}" >
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
					<s:if test="%{#videoGallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
			        <h5 style="color:#357EC7 !important;"><s:property value="fileTitle1"/></h5>
			           New Video Added to <s:property value="candidateName"/> &nbsp;<s:property value="gallaryName"/> Video Gallary.</a>
			        <div style="margin-left:5px;margin-top:6px;">
			           <font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"><span style="color:#FF4500;"><s:property value="fileDate"/></font></span>&nbsp;
			           <font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"> Source : </b><span style="color:#FF4500;"><s:property value="source"/></font></span>
			        </div>
				  </td>
			     </tr>
			    </table>
			</s:iterator>
			</s:if>
		   </div>
		   <div id="photoGallaryDisplay" style="display:none;overflow-x: hidden; overflow-y: scroll; padding-right: 0px; border-right-width: 17px; margin-left: -11px; width: 465px; height: 250px;">
			 <s:if test="resultMap != null && resultMap.size() > 0">
			  <s:iterator value="resultMap.photogallary" var="photogallaryDetails">
			    <table>
			     <tr>
			      <td>
			        <s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >         
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>'>
			        </s:if>
		            <img src='<s:property value="pathOfFile"/>' style="width:95px;height:80px" alt='<s:property value="fileTitle1"/>' title='<s:property value="description"/>'/></a>			         
				  </td>		
			      <td>
			        <s:if test="%{#photogallaryDetails.fileType == 'Candidate'}" >
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
					<s:if test="%{#photogallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;">
			        </s:if>
					<h5 style="color:#357EC7 !important;"><s:property value="fileTitle1"/></h5>
			           New Photo Added to <s:property value="candidateName"/>&nbsp; <s:property value="gallaryName"/> Photo Gallary.</a>
					<s:if test="%{#photogallaryDetails.fileDate != '' && #photogallaryDetails.fileDate != null}" >
					
						<div style="margin-left:5px;margin-top:6px;">
					   <font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"><span style="color:#FF4500;"><s:property value="fileDate"/></font></span>&nbsp;
					   </div>
					 </s:if>
				  </td>
			     </tr>
			    </table>
			  </s:iterator>
			 </s:if>
		   </div>
		   <div id="newsGallaryDisplay" style="display:none;overflow-x: hidden; overflow-y: scroll; padding-right: 0px; border-right-width: 17px; margin-left: -11px; width: 465px; height: 250px;">
			 <s:if test="resultMap != null && resultMap.size() > 0"> 
			  <s:iterator value="resultMap.NewsGallary" var="newsGallaryDetails">
			    <table>
			     <tr>
				 <td>	
				 
				


				  <s:if test="%{#newsGallaryDetails.fileType == 'Candidate'}" >

				  <c:if test="${newsGallaryDetails.displayImagePath != null}">
				  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="${newsGallaryDetails.displayImagePath}"/></a>
				  </c:if>

				  <c:if test="${newsGallaryDetails.displayImagePath == null}">

				  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="./images/candidates/${newsGallaryDetails.imagePathInUpperCase}.jpg"/></a>
				  
				 </c:if>
				

				 </s:if>

				 <s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >

                 <c:if test="${newsGallaryDetails.displayImagePath != null}">
				 <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="${newsGallaryDetails.displayImagePath}"/></a>
				 </c:if>

				 <c:if test="${newsGallaryDetails.displayImagePath == null}">
				 <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="./images/party_flags/${newsGallaryDetails.imagePathInUpperCase}"/></a>
				 </c:if>

				 </s:if>


				<s:if test="%{#newsGallaryDetails.fileType == 'Special Page'}" >

                 <c:if test="${newsGallaryDetails.displayImagePath != null}">
				 <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="${newsGallaryDetails.displayImagePath}"/></a>
				 </c:if>

				 <c:if test="${newsGallaryDetails.displayImagePath == null}">

				  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>'><img width="95px" height="80px;" src="${newsGallaryDetails.imagePathInUpperCase}"/></a>
				 </c:if>

				</s:if>
				 </td>
			      <td>
				  
			        <s:if test="%{#newsGallaryDetails.fileType == 'Candidate'}" >
					  <a href='candidateElectionResultsAction.action?candidateId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;font-size: 13px; line-height: 1.5em;">
			        </s:if>
					<s:if test="%{#newsGallaryDetails.fileType == 'Party'}" >
					  <a href='partyPageAction.action?partyId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;font-size: 13px; line-height: 1.5em;">
			        </s:if>
					<s:if test="%{#newsGallaryDetails.fileType == 'Special Page'}" >
					  <a href='specialPageAction.action?specialPageId=<s:property value="candidateId"/>&contentId=<s:property value="contentId"/>' title='<s:property value="description"/>' style="color: #000000;font-family: verdana;font-size: 13px; line-height: 1.5em;">
			        </s:if>
			        <h4 style="color:#357EC7 !important;"><s:property value="fileTitle1"/></h4>
			           New News Added to <s:property value="candidateName"/> &nbsp;<s:property value="gallaryName"/> News Gallary.</a>
			        <div style="margin-left:5px;margin-top:6px;">
			           <font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"><span style="color:#FF4500;"><s:property value="fileDate"/></font></span>&nbsp;
			           <font style="font-weight:bold;font-size:12px;font-family:verdana;color:#000000;"> Source : </b><span style="color:#FF4500;"><s:property value="source"/></font></span>
			        </div>
				  </td>
			     </tr>
				
			    </table>
				 <hr style="border: 1px dotted #CDCDCD;width: 440px;">
			  </s:iterator>
			 </s:if>
		   </div>
     </div>
		
   </div>
		
	<!--CURRENT ELECTION SECTION START-->
          
          <div class="ce-sec" style="padding-top:0px;">
	<h1 class="title"><span class="orange">Current </span>Elections</h1> <div id="wrap">
              <div id="list">
                <div class="prev"><img src="" alt="prev" /></div>
                <div class="slider">
                  <ul>
					<!--<li> <a href="specialPageAction.action?specialPageId=8" title="Bye Elections In Telangana"><img src="./images/new_homepage/Chadra Babu _ KCR.png" alt="Bye Elections In Telangana" /> <span>AP Bye Elections - 2012</span> </a></li>-->
					<li> <a href="specialPageAction.action?specialPageId=13" title="Gujarat Elections - 2012"><img src="./images/new_homepage/GujaratTham.png" alt="Gujarat Elections - 2012" /><span>Gujarat Elections - 2012</span> </a></li>
					<li> <a href="specialPageAction.action?specialPageId=14" title="Himachal Pradesh Elections - 2012"><img src="./images/new_homepage/Himachal Pradesh Tham.png" alt="Himachal Pradesh Elections - 2012" /> <span>Himachal Pradesh Elections - 2012</span> </a></li>
					<li> <a href="specialPageAction.action?specialPageId=10" title="Presidential Elections-2012"><img src="./images/new_homepage/presidentElection.png" alt="President Elections In India" /><span>Presidential Elections</span> </a></li>
					<li> <a href="specialPageAction.action?specialPageId=8" title="AP Bye Elections - 2012"><img src="./images/new_homepage/Kiran Kumar _Y.S. Jagan.png" alt="AP Bye Elections - 2012" /> <span>AP Bye Elections - 2012</span> </a></li>
    				 <li> <a href="specialPageAction.action?specialPageId=3" title="UttarPradesh 2012 Election"><img src="./images/new_homepage/uttarpradesh_Mulayam.png" alt="UttarPradesh 2012 Election" /> <span>UttarPradesh</span> </a></li>
				  	<li> <a href="specialPageAction.action?specialPageId=4" title="Punjab 2012 Election"><img src="images/new_homepage/punjab.png" alt="Punjab 2012 Election" /> <span>Punjab</span> </a></li>
				  	<li> <a href="specialPageAction.action?specialPageId=5" title="Uttarakhand 2012 Election"><img src="./images/new_homepage/uttaranchal.png" alt="Uttarakhand 2012 Election"/> <span>Uttarakhand</span> </a></li>
				  	<li> <a href="specialPageAction.action?specialPageId=7" title="Goa 2012 Election"><img src="./images/new_homepage/goa.jpg" alt="Goa 2012 Election"/> <span>Goa</span> </a></li>
                 	<li> <a href="specialPageAction.action?specialPageId=6" title="Manipur 2012 Election"><img src="./images/new_homepage/manipur.png" alt="Manipur 2012 Election" /> <span>Manipur</span> </a></li>
				</ul>
                </div>
                <div class="next" style="right:-30px;"><img src="" alt="next" /></div>
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
                <div class="next1" style="right:-30px;"><img src="" alt="next" /></div>
              </div>
            </div>
            
          </div>          

          <!--CURRENT ELECTION SECTION END--> 
          
        </div>
        
        <!--QA AND CURRENT ELECTION SECTION END-->
        
        <div class="clear"></div>
        
        <!--SNEAK PEAK - PARTY ANALYST SECTION START-->
        
        <div class="sneak-peak-sec" style="width:580px;padding:0px;margin:5px;border-radius:5px;position:relative;border:1px solid #cccccc;">
          <div class="problem-h1"> <img src="images/alerticons.png" alt="Problem Icon" class="problemicon"/><span style="display:table;position:relative;width:auto;margin-left:75px;"><h2>PROBLEMS?</h2></span>
		  <h2 class="problem-h2"><b><font style="color:#000;"> New or Existing</font></b> - Our Platform elevates your problems to the external world.</h2>

		  <a href="problemSearchAction.action" class="btn btn-danger btn-xlarge pull-right" style="margin:5px;">View All Problems</a>

		  <c:if test="${sessionScope.hasFreeUserRole != true && sessionScope.hasPartyAnalystUserRole != true}"> 
			 <a href="javascript:{}" onclick="showNotLogIn();" class="btn btn-danger btn-xlarge pull-right" style="margin:5px;">Post Your Problems</a>
		   </c:if>
			<c:if test="${sessionScope.hasFreeUserRole == true && sessionScope.hasPartyAnalystUserRole != true}">
		     <a href="javascript:{}" onclick="openAddNewProblemWindowForFreeUser('${sessionScope.USER.constituencyId}');" class="btn btn-danger btn-xlarge pull-right" style="margin:5px;">Post Your Problems</a>
			</c:if>
		</div>
		<div id="logInDiv"></div>
		  <!-- <h2 class="problem-cont-h2"><b><font style="color:#F55C41"> Post your problem</font></b> -It's quick and free</h2>-->
           <!-- <c:if test="${sessionScope.UserType != 'PartyAnalyst' && sessionScope.UserType != 'FreeUser'}"> 
			 <a href="javascript:{}" onclick="showNotLogIn();" class="problem-register">Post Your Problems</a>
		   </c:if>
			<c:if test="${sessionScope.UserType == 'PartyAnalyst' || sessionScope.UserType == 'FreeUser'}">
		     <a href="javascript:{}" onclick="openAddNewProblemWindowForFreeUser('${sessionScope.USER.constituencyId}');" class="problem-register">Post Your Problems</a>
			</c:if> -->
		  <!-- <h3 class="problem-cont-h3"><b><font style="color:#F55C41">VIEW HERE</font></b> -Problems in your AREA</h3>-->
			<div style="margin-top: 8px; margin-left: 0px; margin-bottom: 10px;">
			
				<div style="margin-left: 9px; margin-bottom: 0px; margin-top: 15px;font-weight: bold;">
				
					To Quick View the Problem, Enter the Reference Id : <input type="text" id="problemReferenceId" size="10" style="width:130px;" />
					
					<input type="button" value="Search" onclick="getProblemReferenceId()" style=" background: #0063DC;color: #FFF; font-weight: bold;padding: 4px 5px 4px 5px;border-radius: 5px;cursor: pointer; border: medium;" />
					<span id="searchAjaxImgSpan" style="margin-left: 515px; display: none; margin-top: -24px;"><img src="images/icons/search.gif"  width="18px" height="18px;" style="margin-left: 35px;"></img></span>

				</div>
				<div id="problemErrorMsgDiv" style="margin-left: 50px;"></div>
				<div id="problemDescriptionDiv" style="margin-left: 139px; margin-top: 9px; margin-bottom: 0px; padding-top: 7px;"></div>
				</div>
				
              <div style="min-height:504px;max-height:850px;overflow-y:auto;display:block;clear:both;" id="problemsShowDIV"></div>
									
		      <div id="custom_paginator_class" class="paginatorElmtClass" style="margin-top:10px;margin-left:20px;margin-bottom: 30px;"></div>
                    
        
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
						<div class="partiesList" style="background:#fff;position:absolute;margin-left:605px;margin-top:217px;">
						     <!--<h3 class="headingClass">View Your Parties</h3><hr style=" border: 0;width: 80%;">-->
							<!-- <h3 style=" border-bottom-color: #CCCCCC;
    border-bottom-style: solid;
    border-bottom-width: 1px; padding-left: 9px;
    color: #C66E17;font-size: 13px;
    font-weight: bold; padding: 7px;margin-bottom: 3px;">View Your Parties</h3>-->
	<h3 style="margin-top:11px;width:344px;background:#0088CC;color:#fff;padding:5px;margin-left:1px;box-shadow:4px 0px 3px #888;margin-bottom:7px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> View Your Parties</h3>
							 <table>
							  <tr  style="height:130px;padding-top:30px;">
                                <td><div class="prevImg"><img src="" alt="prev" /></div></td>
                                <td>
							       <div class="sliderImg">
                                     <ul>
					                   <li class="listyle" > <a href="partyPageAction.action?partyId=362" title="Click here to view INC party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/INC.png" width="50" height="50"  alt="INC Party" /> <div>INC</div> </a></li>
					                   <li class="listyle" > <a href="partyPageAction.action?partyId=872" title="Click here to view TDP party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/TDP.PNG" width="50" height="50" alt="TDP Party" /> <div>TDP</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=1117" title="Click here to view YSRC party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/YSRC.PNG" width="50" height="50" alt="YSRC Party" /> <div>YSRC</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=163" title="Click here to view BJP party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/BJP.png" width="50" height="50" alt="BJP Party" /> <div>BJP</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=886" title="Click here to view TRS party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/TRS.png" width="50" height="50" alt="TRS Party" /> <div>TRS</div> </a></li>
				                       <li class="listyle" > <a href="partyPageAction.action?partyId=839" title="Click here to view SP party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/SP.png" width="50" height="50" alt="SP Party" /> <div>SP</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=239" title="Click here to view BSP party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/BSP.png" width="50" height="50" alt="BSP Party" /> <div>BSP</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=76" title="Click here to view AITC party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/AITC.png" width="50" height="50" alt="AITC Party" /> <div>AITC</div> </a></li>
									   <li class="listyle" > <a href="partyPageAction.action?partyId=579" title="Click here to view NCP party profile -previous election result, Updated News, Photos and Videos"><img src="images/party_flags/NCP.png" width="50" height="50" alt="NCP Party" /> <div>NCP</div> </a></li>
									 </ul>
                                   </div>
								</td>
                                <td><div class="nextImg" ><img src="" alt="next" /></div></td>
                              </tr>
							 </table>
						</div>
						<div class="our-services" style="background:#fff;width:367px;position:relative;margin-top:7px;,margin-left:604px;">
		                  <!--  <h3 class="headingClass">Our Services</h3>-->
<!--<h3 style=" border-bottom-color: #CCCCCC;
    border-bottom-style: solid;
    border-bottom-width: 1px; padding-left: 9px;
    color: red;font-size: 13px;
    font-weight: bold; padding: 7px;margin-bottom: 3px;background:#0088CC;">Our Services</h3>-->

	<h3 style="margin-top:11px;width:344px;background:#0088CC;color:#fff;padding:5px;margin-left:1px;box-shadow:4px 0px 3px #888;margin-bottom:7px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Our Services</h3>
		                    <a href="newsMonitoringService.action" title="News tracking service">News<br/>Monitoring Service</a>
		                    <a href="VotersPulse.action" title="Election-Political Surveys">Voters<br/> Pulse</a>
		                    <a href="constituencyProfileReport.action" title="Reports">Constituency Management Reports</a>
		                    <a href="electionAnalysisAndManagementTool.action" title="Our unique tool designed for Politicians">Election Analysis And <br/>Management Tool</a>
		                </div>


						<div style="width:350px;float:right;margin-top: 10px;margin-right: 40px;">
						<div id="pollsWidgetHeader" >
										<!--<table width="97%" border="0" cellspacing="0" cellpadding="0">
										
										
										  <tr>
										 	<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
											<td>
												<div class="electionTrendzHeaderBackground_center" style="width:300px;">
													<span class="headerLabelSpan headerLabelSpan1" style="color:#C66E17;top:13px;">
														Opinion Poll
													</span>
												</div>
											</td>
											<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
										  </tr>
										</table>-->	
									 </div>

                                     <div style="height:15px;padding-bottom:5px;">
									 <div id="alreadyVotedMsg"></div>
                                     </div>
									<div id="pollsWidgetBody" class="well" style="background:#FFFFFF;width:348px;margin-top:203px;margin-left:-4px;">
								
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
  <div style="margin:10px auto;width:960px;padding:5px;text-align:center;display:table;"> <p style="margin:7px 0px;">The Election information displayed in this website are based on the data collected from the Election Commission Of India. Further suggestions and corrections please contact us at <a href="mailto:info@partyanalyst.com" class="blue">info@partyanalyst.com</a></p></div>
<a id="inline" href="#fancydivbox" style="displaynone"></a>
<div id="promodiv" style="display:none;">
	<div id="fancydivbox">
	<jsp:include page="custom_jsp_pages/homePagePopupPage.jsp" flush="true" />
	</div>
</div>

<!--CONTENT MAIN SECTION END--> 

<script type="text/javascript">
<c:if test="${freeUserConstituencyId != null }"> 
constituencyId = "${freeUserConstituencyId}";
</c:if>
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
				//alert("Invalid JSON result" + e);   
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
	   str += '   <div ><table><tr><td><span><a title="Click Here To View Problem Complete details" class ="problemTitleClass" href="completeProblemDetailsAction.action?problemId='+result[i].problemId+'" >'+(result[i].problem).toUpperCase()+'</a></span></td> <td><span style="color:#A71100;margin-left:15px;">Comments:</span></td><td><font color="#FF4500;"><b style="margin-left:5px;">'+result[i].commentCount+'</b></font></td></tr></table></div>';
	   str += '   <div style="padding-top:5px;font-family:arial;">'+result[i].description+' </div>';
	   if(result[i].url != null){
	     str += '   <div style="padding-top:5px;"><table><tr><td> <span style="color:#028D35;">Location:&nbsp;&nbsp;</span></td><td><a title="Click Here To View  '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+' Details, Election Results and Different Parties Performances" href="'+result[i].url+'"> '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+'</a> &nbsp;&nbsp;&nbsp;&nbsp;</td><td><span style="color:#028D35;">Posted By:&nbsp;&nbsp;</span></td><td>'+initialCap(result[i].name)+' '+initialCap(result[i].lastName)+'</td><tr></table></div>';
	   }else{
	     str += '   <div style="padding-top:5px;"><table><tr><td> <span style="color:#028D35;">Location:&nbsp;&nbsp;</span></td><td> '+initialCap(result[i].problemLocation)+' '+initialCap(result[i].impactLevel)+' &nbsp;&nbsp;&nbsp;&nbsp;</td><td><span style="color:#028D35;">Posted By:&nbsp;&nbsp;</span></td><td>'+initialCap(result[i].name)+' '+initialCap(result[i].lastName)+'</td><tr></table></div>';
	   }
	   
       str += '   <div style="padding-top:5px; margin-left: 3px;">';
	   if(result[i].referenceNo != null && result[i].referenceNo != '')
		{
		str += '<Span style="color:#028D35;">Ref No :&nbsp;&nbsp;</span> '+result[i].referenceNo+'&nbsp;&nbsp;&nbsp;&nbsp;';
		}
	   str +='<span style="color:#028D35;">Existing From :&nbsp;&nbsp;</span> '+result[i].existingFrom+' &nbsp;&nbsp;&nbsp;&nbsp; <span style="color:#028D35;">Identified On :&nbsp;&nbsp;</span>'+result[i].identifiedOn+' </div>';
       //str += '   <div style="padding-top:5px;"> <span style="color:#028D35;">Files Attached :&nbsp;&nbsp;</span>'+result[i].fileCount+' </div>';	   
	   str += '</div>';
	}
	probEle.innerHTML = str;
	
}
<c:forEach var="status" varStatus="stat" items="${opinionPollVO.questionsOptionsVO.options}">
			var obj =	{
							option:"${status.option}",
							votesObtained:${status.votesObtained}
						};
			pollStatus.push(obj);
		</c:forEach>
	
function initialCap(data) {
   data = data.substr(0, 1).toUpperCase() + data.substr(1).toLowerCase();
   return data;
}
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
		str+='<div class="container"><h4><div style="margin: 10px;color:ActiveCaption;">Only Registered Users Can Post Problems.</div></h4><h5 style="color:#000;display:inline;position:relative;top:0px;"><div style="margin: 10px;"> Already a member ,   Click here to <a style="color:red;" href="loginInputAction.action">Login</a></div><div style="margin-left:160px;">(OR)</div><div style="margin: 10px;margin-top:-20px;">Not a member, Click here for <a style="color: Red; width: 114px; height: 8px;" href="freeUserRegistration.action"> FREE REGISTRATION <span style="margin-bottom: 20px;"><img src="images/specialPage/freeUser.png"></span></a></div></h5></div>';
		document.getElementById("logInDiv").innerHTML = str;
   

}
var uname = '${sessionScope.USER.userName}';
var emailId='${sessionScope.USER.email}';
var changedUserName='${sessionScope.changedUserName}';
var loadingFirstTime = '${sessionScope.homePageLoadingFirstTime}';
$(document).ready(function(){
//if(loadingFirstTime == 'true'){
		$("#inline").fancybox();
		$("#inline").trigger("click");
//		}
	});
//buildHOmePageChartsSlider();
	initializeHomePage();
	//getElectionTypeValue(1);
	showVotesObtainedForOpinionPoll();
	<c:if test="${loginStatus == 'true' && sessionScope.hasFreeUserRole == true }">
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
		callHomePageAjax(jsObj,url);
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
		$(".ui-widget-overlay").css("width","1000px");
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
		callHomePageAjax(jsObj,url);

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
		callHomePageAjax(jsObj,url);

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
		callHomePageAjax(jsObj,url);

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
		callHomePageAjax(jsObj,url);

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
	callHomePageAjax(jsObj,url);
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
	callHomePageAjax(jsObj,url);
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
		$(".ui-widget-overlay").css("width","1000px");
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
$(function() {
    		$(".sliderImg").jCarouselLite({
        		btnNext: ".nextImg",
        		btnPrev: ".prevImg",
        		visible: 5
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

	function showPhotoGallary()
	{
		$(".updatesDiv a").removeClass("current");
		$("#photoGallaryButton").addClass("current");
		document.getElementById("videoGallariesDisplay").style.display = 'none';
		document.getElementById("photoGallaryDisplay").style.display = 'block';
		document.getElementById("newsGallaryDisplay").style.display = 'none';
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
	function showVideoGallary()
	{	
		$(".updatesDiv a").removeClass("current");
		$("#videoGallaryButton").addClass("current");
		document.getElementById("videoGallariesDisplay").style.display = 'block';
		document.getElementById("photoGallaryDisplay").style.display = 'none';
		document.getElementById("newsGallaryDisplay").style.display = 'none';
	}



function showVotesObtainedForOpinionPoll()
{
    //If opinion poll end date expired
	//if('${opinionPollVO.quesitons[0].question}' == null || '${opinionPollVO.quesitons[0].question}' == "" )
		//$('#pollsWidgetBody').hide();

    var elmt = document.getElementById("pollsWidgetBody");
	var str='';	

	
	if(${opinionPollVO.avaliability != true})
	{

		str+='<div class="opinionpoll">';

		str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';

		str+='<div class="breadcrumb"><p class="question"><b>${opinionPollVO.quesitons[0].question}</b></p>';	
	
		str+='<div id="qstnDiv1" style="margin-top:3px;">';	

		str+='<div class="control-group form-horizontal "><p class="answer">';
		<s:iterator status="stat" value="opinionPollVO.quesitons[0].options">

		<s:if test="%{#stat.index == 0}">         

			str +='<label><input type="radio" class="radio" name="pollradio" value="<s:property value="optionId"/>" checked="true">';
	        str +="<s:property value='option'/></label>"; 
		</s:if>
		<s:else>
			str +='<label><input type="radio" class="radio"  name="pollradio" value="<s:property value="optionId"/>">';
	        str+="<s:property value='option'/></label>";
		</s:else></s:iterator>
		
		str+='</p><a href="javaScript:saveCurrentPollResult(${opinionPollVO.quesitons[0].questionId});" class="btn btn-primary votebtn" title="Click Here To Vote">Vote</a>';
		
		str+='<p class="resultdisplay"><a  class="previouslink" href="javaScript:{callAjaxToGetQuestionsDetails(\'vote\',${opinionPollVO.quesitons[0].questionId});}" title="Click Here To See This Poll Result">View Results</a>';

		    
		str+=' <a class="nextlink" href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments" title="Click Here To See Comments On This Poll" >View Comments</a></p>';
		str+=' </div></div>';

		/*str+='<div>';
		str+='<a href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments" class="btn btn-primary" title="Post Your Comment On This Poll">Post Your Comment</a>';
		str+='<a href="getAllPollsAction.action" class="btn btn-primary" style="float:right;" title="Click Here To View Rececnt Poll Details">View Recent Polls</a>';
		str+='</div>';*/

		
		str+='</div>';
str+='<div class="pager">';
   
        str+='<a style="float:left;" href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}&comments=getComments"  class="btn" title="Post Your Comment On This Poll">Post Your Comment</a>';
 
  
    str+='<a style="float:right;" class="btn" href="getAllPollsAction.action"  title="Click Here To View Rececnt Poll Details">View Recent Polls </a>';
   
    str+='</div>';
	elmt.innerHTML = str;		
	
	}
	else
	{

		
			
		 displayPollResult('${opinionPollVO.questionsOptionsVO.questionId}');
	
  //  afterRefreshOpinionPOll1('${opinionPollVO.questionsOptionsVO.questionId}');
	
	}
	/*str += '<div id="viewPollResDiv">';
	str += '<table><tr>';
	str += '<td><div style="width:157px;"><a href="completeResultForAPollAction.action?questionId=${opinionPollVO.quesitons[0].questionId}" style="text-decoration:underline;cursor:pointer;padding-right:10px;color:#3d3d3d;"> View Current Poll Result</a></div>';
	str += '</td>';
	str += '<td><div style="width:83px;"><a href="getAllPollsAction.action?.action" style="text-align:right;text-decoration:underline;cursor:pointer;color:#3d3d3d;"> View All Polls</a></div>';
	str += '</td>';	
	str += '</tr></table>';
	str += '</div>';
	
	elmt.innerHTML = str;*/	
	
}

function displayPollResult(questionId){



	callAjaxToGetQuestionsDetails("",questionId);
	
}
function afterRefreshOpinionPOll()
{

	

var elmt = document.getElementById("pollsWidgetBody");
	var str = '';
	str += '<table><tr><td>';
	str += '<div id="pollQuestionDiv" > Q) '  +'${opinionPollVO.questionsOptionsVO.question}';
	str += '</div>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	//str += '<img src="charts/'+myResults.imagePath+'"></img>';
	str += '</td></tr>';
	
	str += '<tr><td>';
	str += '<div id="viewPollResDiv">';
	str += '<table style="margin-left: 26px;"><tr>';
	str += '<td><div style="width:157px;"><a href="completeResultForAPollAction.action?questionId=${opinionPollVO.questionsOptionsVO.questionId}" style="cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 5px; margin: 2px;"> View Current Poll Result</a></div>';
	str += '</td>';
	str += '<td><div style="width:94px;"><a href="getAllPollsAction.action?.action" style="text-align: right; cursor: pointer; color:#0C67AC; font-weight: bold; text-decoration: none; background:#e3e3e3; padding: 4px; border-radius: 4px;"> View All Polls</a></div>';
	str += '</td>';	
	str += '</tr></table>';
	str += '</div>';
	str += '</tr></table>';
	

	str+='<div id="pollsChart" style=" height: auto;width: 324px; overflow: hidden;"></div>';
	elmt.innerHTML = str;


var arrData = pollStatus;

	var data = new google.visualization.DataTable();
	data.addColumn('string','option');
	data.addColumn('number','votesObtained');
		
	data.addRows(arrData.length);

		for(var j=0; j<arrData.length; j++)
		{
			
			data.setValue(j,0,arrData[j].option);
			data.setValue(j,1,arrData[j].votesObtained);
			
		}
			var chart = new google.visualization.LineChart(document.getElementById('pollsChart'));
	
	chart.draw(data,{width: 300, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'${opinionPollVO.questionsOptionsVO.title}',titleTextStyle:{fontName:'verdana',fontSize:9}});
	
}
	function showNewsGallary()
	{
		$(".updatesDiv a").removeClass("current");
		$("#newsGallaryButton").addClass("current");
		document.getElementById("videoGallariesDisplay").style.display = 'none';
		document.getElementById("photoGallaryDisplay").style.display = 'none';
		document.getElementById("newsGallaryDisplay").style.display = 'block';
    }

	function getProblemReferenceId()
	{

		var problemRefId = document.getElementById("problemReferenceId").value.replace(/^\s+|\s+$/g,"");
		var errorDivEle = document.getElementById('problemErrorMsgDiv');

		var problemDescDivEle = document.getElementById("problemDescriptionDiv");
		var content = "Please Enter Valid Problem Referenece Id";
		var eFlag = false;
		var validCharacters = /[a-zA-Z]/;
		var onlyNumbers = /[0-9]/;
		var invalidChars = /^[a-zA-Z0-9]{7}$/;
		var str = '<font style="color:red">';
		if(problemRefId.length == 0)
		{
			str +='Please Enter Problem Reference Id';
			eFlag = true;
		}
		else if(problemRefId.length != 7)
		{
		str += content;
		eFlag = true;
		}
		else if (!invalidChars.test(problemRefId))
		{
			str += content;
			eFlag = true;
		}
		else if(!validCharacters.test(problemRefId.substring(0,1)))
			{
				str += content;
				eFlag = true;
			}
		
		else if(!onlyNumbers.test(problemRefId.substring(2,7)))
			{
				str += content;
				eFlag = true;
			
			}
		str +='</font>';
		problemDescDivEle.innerHTML = str;

		if(eFlag)
			return;
		else
		{
			document.getElementById("searchAjaxImgSpan").style.display = 'block';
		var jsObj = 
			{
				problemRefId : problemRefId,
				task         : "getProblemDetailsBasedOnProblemRefId"
			};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getProblemDetailsAction.action?"+rparam;						
		callHomePageAjax(jsObj,url);
		}
	}	
function showProblemDescriptionByProblemRefId(results)
{
	document.getElementById("searchAjaxImgSpan").style.display = 'none';
	var problemDescDivEle = document.getElementById("problemDescriptionDiv");
    document.getElementById("problemReferenceId").value = '';
	if(results == null)
	{
		problemDescDivEle.innerHTML = '<font style="color:red">This Problem is not Existed.</font>';
		
	}
	else if(results != null)
	{
		window.location.href = 'completeProblemDetailsAction.action?problemId='+results.problemHistoryId;
	}
	
}


function checkElectionType(electionTypeId)
{
var electionType = document.getElementById('electionTypeId').value;

if(electionType == 1)
	{
getStates();
document.getElementById('states').style.display="none";
getElectionYearsInHomePage('Parliament');
	}

if(electionType == 2)
	{
	document.getElementById('states').style.display="block";

getStates();

	}
}
function getStates()
{

	var electionType = document.getElementById('electionTypeId').value;

	var jsObj=
		{						
				
				electionType:electionType,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}

function checkElectionType(electionTypeId)
{
var electionType = document.getElementById('electionTypeId').value;

if(electionType == 1)
	{
getStates();
document.getElementById('states').style.display="none";
getElectionYearsInHomePage('Parliament');
	}

if(electionType == 2)
	{
	document.getElementById('states').style.display="block";

getStates();

	}
}
function getStates()
{

	var electionType = document.getElementById('electionTypeId').value;

	var jsObj=
		{						
				
				electionType:electionType,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}






//SAMBA

function callAjaxToGetQuestionsDetails(voteStatus,questionId){
	


	var jsObj =
			{				
			   questionId:questionId,
				task:"getPollDetails"

			};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getQuestionAndPercentageOfVotesForChoices.action?"+rparam;						
		callAjax(voteStatus,jsObj,url); 

}

function callAjax(voteStatus,jsObj,url)
{
	
		var callback = {			
	 	success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);

		    
			if(jsObj.task == "getPollDetails"){				
				buildResult(voteStatus,myResults);		   
			}
		    
		}catch(e){}
	  },
			scope : this,
			failure : function( o )
			{}
		  };

		 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildResult(voteStatus,result){


	var elmt = document.getElementById("pollsWidgetBody");

	var str='';
  
	str+='<div class="opinionpoll">';

	str+='<h3 style="background:#0088CC;color:#fff;padding:5px;margin-left:-10px;box-shadow:3px 0px 2px #888;margin-bottom:5px;border-radius:0px 5px 5px 0px;"><i class="icon-forward icon-white" style="margin-top:3px;"></i> Opinion Poll</span></h3>';

	str+='<div class="breadcrumb"><p class="question"><b>'+result.question+'</b></p>';	


	//str+='<p class="breadcrumb well" style="margin-bottom:3px;"><b>'+result.question+'</b></p>'; 

	str+='<div id="qstnDiv1" style="margin-top:3px;">';	


	str+='<p style="margin-bottom:3px;" class="pull-right">Total Votes  Polled:<b>'+result.totalVotesObtainedForPoll+'</b></p>';

	
	str+='<ul style="margin-left:25px;">';
	for(var i=0;i<result.options.length;i++){   
		
          str+='<li>';
		str+='<h5>'+result.options[i].option+'</h5>';

		str+='<div>';
		str+='<div class="span2 pull-left">';

			str+='<div class="progress" style="margin:0px;">';
			  str+='<div id="option1" class="bar" style="width:'+result.options[i].percentage+'%"></div>';
			str+='</div>';							
			str+='</div>	';

			str+='<span class="label label-info">'+result.options[i].percentage+'% </span>';
	  str+= '</div>';
	   str+='</li>';
		
	}
	str+='</ul></div>';
	
	 if(voteStatus == "vote")

        str+='<a class="btn btn-primary" href="javaScript:{showVotesObtainedForOpinionPoll()}");" class="btn btn-primary" title="Click Here To Vote" style="margin:10px 0px 0px 104px;">Vote Now</a>';

		str+='</div>';
  
		//str+='</div>';

  
		
	    str+='<div class="pager" style="margin-top:25px;">';
		str+='<a href="completeResultForAPollAction.action?questionId='+result.questionId+'&comments=getComments"  id='+result.questionId+' class="btn" style="float:left;" title="Click Here To Post Your Comment On This poll">Post Your Comment</a>';

		


		str+='<a href="getAllPollsAction.action" class="btn" style="float:right;" title="Click Here To See Recent Poll Details">View Recent Polls</a>';
		str+='</div>';
	
	

	elmt.innerHTML=str;

}

function saveCurrentPollResult(questionId){

	var elmts = document.getElementsByName("pollradio");
	var checkedElmtId = '';
	
	for(var i=0; i<elmts.length;i++)
	{
		if(elmts[i].checked == true)
			checkedElmtId = elmts[i].value;
	}
	var jsObj=
	{
			questionId:questionId,
			selectedPollId:checkedElmtId,
			task:"saveSelectedPoll"					
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveSelectedPoll.action?"+rparam;						
	callAjaxToSaveSelectedPollDetails(rparam,jsObj,url,questionId);	
}

function callAjaxToSaveSelectedPollDetails(param,jsObj,url,questionId){

	var myResults;
		
		var callback = {			
		               success : function( o ) 
					  {
						try {			
								if(o.responseText)
								myResults = YAHOO.lang.JSON.parse(o.responseText);


									if(myResults.availability == true){

										var cssObj = {    
										'font-weight' : 'bold',
										'color' : 'green'
								      }
									   $('#alreadyVotedMsg').text("You are already voted.").css(cssObj).show().delay(2000).fadeOut(400);

										

									}							
															
								 
							displayPollResult(questionId);
													
								
						}
						catch (e)
							{   
							  	//alert("Invalid JSON result" + e);   
							}	  
			              },
			               scope : this,
			               failure : function( o ) {

			            }
			               };

			YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
//buildPolls();

</script>
	
</body>
</html>
