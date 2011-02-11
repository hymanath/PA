
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst - Know, Analyse, Act.</title>

 <META NAME="Keywords" CONTENT="AP Elections, Assembly Elections, Parliament elections, Municipal elections, Election opinion polls, Andhra Elections, Andhra Politics, Andhra pradesh elections, Telugu Desam, Congress, PRP, TDP, CPI, CPM, TRA, BJP, Hyderabad, Chandrababu, Chiranjeevi, andhra polls, by-polls, by-elections, MLC, Rajyasabha, MPTC, ZPTC, Loksabha, Parliament, 2009 elections, Gram Panchayats, Mandals, Villages, Andhra municipalities, Municipal Corporations">

<META NAME="Description" CONTENT=" Party Analyst is a tool used by the political parties, media, and politicians. This product has different interfaces for Constituency Management, Cadre Management, Party Results, Cross Voting, Party Performance, Election Comparison, States election results, District election results, Constituency election results, MLA constituencies information, MP constituencies information, Latest news .This tool also provides Assembly election results, Parliament election results, MPTC election results, ZPTC election results, Municipal election results, Corporation election results of all election years.">


<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> 

<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js" ></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/landingPage/landingPage.js" ></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="js/cncSearch.js"> </script>

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.accordion.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>

<!-- JQuery files (End) -->

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>

<script type="text/javascript">

google.load("elements", "1", {packages : ["newsshow"]});

var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String stateSelect = rb.getString("stateSelect");
		String distSelect = rb.getString("distSelect");
		String constSelect = rb.getString("constSelect");
		String assembly = rb.getString("assembly");
		String parliament = rb.getString("parliament");
		String localBody = rb.getString("localBodies");
		
		ResourceBundle resb = ResourceBundle.getBundle("global_ErrorMessages");
		String errorMsg = resb.getString("constTypeAlert");
%> }
var errotMsg = '<%=errorMsg%>';
localBodyString = '<%=localBody%>';

	<c:forEach var="state" items="${statesList}" >
		var obj={
					id:"${state.id}",
					name:"${state.name}"				
				};
		statesInCountryObj.push(obj);	
	</c:forEach>

	YAHOO.util.Event.onContentReady("navigationHead", function () { 	 
	    
	    var oMenuBar = new YAHOO.widget.MenuBar("navigationHead", {  
	                                                autosubmenudisplay: true,  
	                                                hidedelay: 100,  
	                                                lazyload: true }); 
	
		 
		oMenuBar.subscribe("beforeRender", function () { 
	 
	    if (this.getRoot() == this) { 	 
			this.getItem(0).cfg.setProperty("submenu", aSubmenuData[0]); 
			this.getItem(1).cfg.setProperty("submenu", aSubmenuData[1]); 
			this.getItem(2).cfg.setProperty("submenu", aSubmenuData[2]); 
			this.getItem(3).cfg.setProperty("submenu", aSubmenuData[3]); 
			this.getItem(4).cfg.setProperty("submenu", aSubmenuData[4]); 
			this.getItem(5).cfg.setProperty("submenu", aSubmenuData[5]);
			this.getItem(6).cfg.setProperty("submenu", aSubmenuData[6]);
	    } 
	 
	}); 
	 
	oMenuBar.render();  
	 
	}); 
	<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}"> 	
		var aSubmenuData = [ 
		{
			 id: "home", 
	        itemdata: [ 				
	   	          
	
	        ] 
		},	 
	    { 
	        id: "partyanalysis",  
	        itemdata: [  
	            { text: "Party Performance Report", url: "partyPerformanceMain.action" }, 	            
	            { text: "Elections Comparisons Report", url: "electionComparisonAction.action" },
	            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" },	 
	            { text: "Party Influence Report", url: "partyInfluenceMainAction.action" },
	            { text: "Election Results Analysis Report", url:"electionResultsAnalysisAction.action"},
	            { text: "Telangana Bye-Elections 2010", url: "biElectionAction.action" },
				{ text: "Elections Vs Census", url: "censusReportAction.action" }                          
	        ] 
	    }, 
	    { 
	        id: "cadreManagement", 
	        itemdata: [ 				
	   	          
	
	        ] 
	    },  
		{ 
	        id: "constituenceyManagement",
	        itemdata: [
		    { text: "Problem Management Report", url: "problemManagementReportAction.action" },
		    { text: "User Groups", url: "userGroupAction.action" },
		    { text: "Constituency Election Report", url: "constituencyElectionReportAction.action" }
	        ] 
	    },   
	    { 
	        id: "politicianAnalysis",  
	        itemdata: [ 
		    { text: "Mandal Voting Report", url: "mandalPageSDetailAction.action" },
		    { text: "Cross Voting Report", url: "crossVotingReportInputAction.action" },				
	            { text: "Constituencey Booth Results Report", url: "partyBoothResultAction.action" }						
	        ]  
	    },	 
	    { 
	        id: "staticData",  
	        itemdata: [ 
	            { text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
	            { text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
	            { text: "Karnataka", url: "statePageAction.action?stateId=12" }
	        ]     
	    }, 
	    { 
	        id: "search", 
	        itemdata: [
	        ] 
	    }  
	     
	     
		]; 
	</c:if>
	<c:if test="${(sessionScope.loginStatus == 'out' && sessionScope.UserType == 'FreeUser') || (sessionScope.loginStatus == null || sessionScope.loginStatus == 'in')}">
		var aSubmenuData = [ 
					{
						 id: "home", 
						itemdata: [ 				
							  
	
						] 
					},
					{ 
						id: "partyanalysis",  
				        itemdata: [	            
				            { text: "Elections Comparisons Report", url: "electionComparisonAction.action" },
				            { text: "Party Results Report", url: "partyResultsCriteriaAction.action" }	                       
				        ]    
					},
					{ 
						id: "staticData",  
						itemdata: [ 
							{ text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
							{ text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
							{ text: "Karnataka", url: "statePageAction.action?stateId=12" }
						]     
					}, 
					{ 
						id: "search", 
						itemdata: [
						] 
					}  					 
				]; 
	</c:if>
</script>
</head>
<body>
	<div id="loginPopupDivMain" class="yui-skin-sam"><div id="loginPopupDiv"></div></div>
	<div class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
	<div id="knowMore_window"><div id="knowMore_window_inner"></div></div>
	<div id="homePageContainer">
		<div id="indexheader" class="indexLayoutContainer" style="overflow:visible;background-image:url('images/icons/homePage_new/headerBG.jpg');height:125px;">
            <table  width="100%" id="headerTable">
                <tr>
                    <td style="vertical-align:top;width:540px;">
                        <div id="pa_Logo" style="padding-left: 10px; padding-top: 10px;">
                        	
                        </div>
                    </td>
                    <td style="vertical-align:top;">
                        <table width="100%" style="width:100%">                           
                            <tr>
                                <th id="searchBox" style="color:#FFFFFF">
									<c:if test="${sessionScope.loginStatus == 'out'}">        		
										<c:out value="Welcome, ${sessionScope.UserName} | "/>
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/logOut.jsp">LogOut</a> | 
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>         		
									</c:if>		
									<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
										
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}" />/loginInputAction.action" >Login</a> | 
										<a class="loginStatusAnc" style="color:#FFFFFF" href="<c:out value="${pageContext.request.contextPath}/adminUpload.action" />" >Admin</a>
									</c:if>		
                                </th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>		
        </div>	
		
		<!-- Note Data Div (Start)-->
		<div id="noteDataDiv">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="5%">
						<div id="noteDataDiv_head"><span id="noteDataDiv_head_span">Note</span></div>
					</td>
					<td width="95%">
						<div id="noteDataDiv_body">
							<marquee onmouseover="this.stop()" onmouseout="this.start()" scrollamount="4">
								This product is not ready to use presently and it will be very soon.The users registered presently may lose their login credentials. 
							</marquee>
						</div>
					</td>
				</tr>
			</table>
		</div>		
		<!-- Note Data Div (End)-->

        <div id="indexNavContainer"  class="indexLayoutContainer yui-skin-sam">
			<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
				<div class="bd"> 
					<ul class="first-of-type"> 
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
								<!--<img id="indexHomeImg" width="28" height="25" src="images/icons/indexPage/pa_home.png" title="home"/>-->
								HOME
							</a> 
						</li>
						<c:if test="${sessionScope.loginStatus == 'out' && sessionScope.UserType == 'PartyAnalyst'}">  
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="javascript:{}">PARTY ANALYSIS</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="cadreManagementAction.action">CADRE</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="initailConstituencyManagementAction.action">CONSTITUENCEY MANAGEMENT</a> 
							</li> 
						</c:if>
						
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="javascript:{}">POLITICIAN ANALYSIS</a> 
						</li>
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="statePageAction.action?stateId=1">STATES</a> 
						</li>
						<li class="yuimenubaritem"> 
							<a class="yuimenubaritemlabel" href="searchPartyAnalystAction.action">SEARCH</a> 
						</li> 
						
						<c:if test="${sessionScope.loginStatus == 'out'}">  
							<li class="yuimenubaritem"> 
								<c:if test="${sessionScope.UserType == 'PartyAnalyst'}"> 
									<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/index.action" >
										DASH BOARD
									</a> 
								</c:if>
								<c:if test="${sessionScope.UserType == 'FreeUser'}"> 
									<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/connectPeopleAction.action" >												
										DASH BOARD
									</a>  
								</c:if>	
							</li> 
						</c:if>
					</ul> 
				</div> 
			</div> 
		</div>
		
       <!-- <div id="homePage_header">
        	<img height="180" width="960" src="images/icons/homePage_new/homePage_header.jpg"/>
        </div> -->
        <div id="homePageContent_main" style="height:auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  <tr>
				<td width="70%" valign="top">
					<div id="homePage_Image_Header">
						<div style="padding:325px 30px 10px 0;text-align:right;">
							<img style="cursor:pointer" onclick="openKnowMoreWindow()" width="140" height="30" src="images/icons/homePage_new/learn_more.jpeg">
						</div>
					</div>					
				</td>
				<td width="30%" valign="top">
					
					<div id="accordion">
						<h3><a href="#">View Your State</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>
									<tr>
										<td style="text-align:justify;padding-bottom:30px;line-height:25px;"> Select your state to view its Assembly, Parliament, Local Bodies election results. </td>
									</tr>
									<tr>
										<td style="height:40px;color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/></td>									
									</tr>								
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToStatePage()"></img>
							</div>
						</div>
						<h3><a href="#">View Your District</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
								<table>
									<tr>
										<td style="text-align:justify;padding-bottom:5px;line-height:25px;"> Select your district to view its election results in district level. </td>
									</tr>
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"/></td>
									</tr>
									<tr>
										<td style="color:#004078"><%=distSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/></td>
									</tr>
								</table>
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToDistrictPage()"></img>
							</div>
						</div>
						<h3><a href="#">View Your Constituency</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<div id="alertMessage" style="color:red;font-weight:bold;"></div>
								<table>
									<tr>
										<td colspan="4">Select Constituency Type</td>
									</tr>	
									<tr>
										<td colspan="2"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/><%=assembly%></td>
										<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/><%=parliament%></td>
									</tr>
								</table>
								<table id="stateTable" style="display:none;">
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/></td>
									</tr>
								</table>
									
								
								<table id="constTable" style="display:none;">
									<tr>
										<td style="color:#004078"><%=constSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
									</tr>
								</table>										
							</div>
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToConstituencyPage()"></img>
							</div>
						</div>
						<h3><a href="#">View Your Locality</a></h3>
						<div style="padding:0px;">
							<div class="widgetsBody" style="background-color:#FFFFFF;color:#49443E;">
								<table>								
									<tr>
										<td style="color:#004078"><%=stateSelect%></td>
									</tr>
									<tr>
										<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesList" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
									</tr>
									<tr>
										<td style="color:#004078"><div id="localBodiesRadioDiv_label"><%=localBody%></div></td>
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
							<div class="widgetsFooter" style="background-color:#FFFFFF;height:37px;">
								<img width="70" height="25" src="images/icons/homePage_new/b3.jpg" onclick="navigateToLocalBodyPage()"></img>
							</div>
						</div>
					</div>
				</td>
			  </tr>
			</table>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top:10px;">			  
              <tr>
                <td width="30%" valign="top">
                		
						<!-- Product Feature Div start -->
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
                               <table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center">
											<span class="headerLabelSpan" style="position:relative;top:6px;">
												<table cellspacing="0" cellpadding="0">
													<tr>
														<td align="left">Election Comparison</td>
														<td style="padding:2px 0px 0px 8px">
														<img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
													</tr>
												</table>
												 
											</span>
										</div>
									</td>
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
								  </tr>
								</table>
                            </div>
							<div class="productFeatureBody">
								<table>
									<tr>
										<td valign="top"><img height="60" width="110" src="images/icons/indexPage/partyanalysis/report2.png"></td>
										<td valign="top">
											Elections Comparison Report gives a glance of compared election results for a party participated any two elections in a detailed view.
										</td>
									</tr>
									<tr>
										<td colspan="2">This report mainly provides a overview  for a user to know wheather the party improved/lost its performance in selected present year when compared to selected previous year.</td>
									</tr>
								</table>
								<div class="productFeature_button">
									<div class="viewReportButtonSpan" onclick="javascript:{window.location = 'electionComparisonAction.action'}">
										<span class="viewReportButtonLabel">View</span>
									</div>
								</div>
                            </div>
							<div class="productFeatureFooter">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureFooterBackground_center">
											
										</div>
									</td>
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
						</div>

						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center">
											<span class="headerLabelSpan" style="position:relative;top:6px;">
												<table cellspacing="0" cellpadding="0">												
													<tr>
														<td align="left">Party Results </td>
														<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
													</tr>
												</table>
												 
											</span>
										</div>
									</td>
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
								  </tr>
								</table>
                            </div>
							<div class="productFeatureBody">
								<table>
									<tr>
										<td valign="top"><img height="60" width="110" src="images/icons/indexPage/partyanalysis/report3.png"></td>
										<td valign="top">Party Results Report gives overall picture for a party in different types of elections like 
										</td>
									</tr>
									<tr>
										<td colspan="2">Assembly/Parliament/ZPTC/MPTC/Local Election Bodies (Municipality/Corporation/Greater) in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.</td>
									</tr>	
								</table>
								<div class="productFeature_button">
									<div class="viewReportButtonSpan" onclick="javascript:{window.location = 'partyResultsCriteriaAction.action'}">
										<span class="viewReportButtonLabel">View</span>
									</div>
								</div>
                            </div>
							<div class="productFeatureFooter">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureFooterBackground_center">
											
										</div>
									</td>
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
						</div>


						<!-- Product Feature Div End -->
                </td>
                <td width="45%" valign="top">
                	<div id="electionTrendzWidgetMain">
                    	<div id="electionTrendzWidgetHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img height="43" width="45" src="images/icons/homePage_new/newsContainerHead_left.jpg"/></td>
								<td width="98%">
									<div class="electionTrendzHeaderBackground_center">
										<span class="headerLabelSpan headerLabelSpan1">
											Latest Election Trendz
										</span>
									</div>
								</td>
								<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/newsContainerHead_right.jpg"/></td>
							  </tr>
							</table>	
						 </div>
                        <div id="electionTrendzWidgetBody" class="yui-skin-sam">                        	

							<div id="electionTrendzDiv_main">
								
							</div>
                        </div>
						<div id="electionTrendzWidgetFooter">
                        	
                        </div>
                    </div>
                </td>
                <td width="25%" valign="top">
                	<div id="adDataDiv_main">
                    	<div id="adDataMain_header">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>                                    
								<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
								<td width="98%">
									<div class="productFeatureHeaderBackground_center">
										<span class="headerLabelSpan" style="position:relative;top:6px;">
											<table cellspacing="0" cellpadding="0">
												<tr>
													<td align="left">Quick Search </td>
													<td style="padding:2px 0px 0px 8px"><img  width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
												</tr>
											</table>
											 
										</span>
									</div>
								</td>
								<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
							  </tr>
							</table>
						</div>
						<div id="adDataMain_body">
							<div class="adData_main">
								
								<div class="adData_body">
									<table>
									<tr>
									<td><jsp:include page="../jsp/cncSearch.jsp"/></td>
									</tr>
									</table>									
								</div>
								<!--<div class="adData_footer">
									<div onclick="javascript:{window.location = 'landing.action'}" class="votingTrendzHeadLabelDiv">
										<span class="votingTrendzHeadLabelSpan">Search</span>
									</div>
								</div>								
							--></div>
						</div>
                    </div>    
					
				
					<div id="giftWidgetMain" style="margin-top:5px;">
						<div id="giftWidgetHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img height="41" width="45" src="images/icons/homePage_new/gift_header_left.jpg"/></td>
								<td width="98%">
									<div class="electionTrendzHeaderBackground_center">
										<span class="headerLabelSpan headerLabelSpan1" style="color:#3b3b3b">
											Gift Your Leader
										</span>
									</div>
								</td>
								<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/poll_header_right.jpg"/></td>
							  </tr>
							</table>	
						 </div>
						<div id="giftWidgetBody" class="yui-skin-sam" style="height:140px;">							
							View how <font color="#184165" style="font-weight:bold;">PartyAnalyst.com</font> platform is useful to your leader or party, if you got inspired with that gift to him or let them know about this.<br/>
							To get more details mail us 
							at <font color="#184165" style="font-weight:bold;">info@itgrids.com</font> or <a class="newsHeadLink" href="landing.action">click here</a> for more details. 						
						</div>
						<div id="giftWidgetFooter">
							
						</div>
					</div>
					
                </td>
              </tr>
            </table>
        </div>
		
		<div style="padding:5px;">
			<table width="100%">
				<tr>
					<td width="33%">
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center1">
											<span class="headerLabelSpan">
												<table cellspacing="0" cellpadding="0">
													<tr>
														<td align="left">Know Your</td>
														<td></td>
													</tr>
													<tr>
														<td align="left">Leaders</td>
														<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
													</tr>
												</table>
												 
											</span>
										</div>
									</td>
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>							
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:250px;">
								<div id="leadersNews"></div>								
							</div>	
							<div class="productFeatureFooter">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureFooterBackground_center">
											
										</div>
									</td>
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
						</div>
					</td>
					<td width="33%">
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center1">
											<span class="headerLabelSpan">
												<table cellspacing="0" cellpadding="0">
													<tr>
														<td align="left">Know Your</td>
														<td></td>
													</tr>
													<tr>
														<td align="left">Nation</td>
														<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
													</tr>
												</table>
												 
											</span>
										</div>
									</td>
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:250px;">
								<div id="topStories"></div>								
							</div>	
							<div class="productFeatureFooter">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureFooterBackground_center">
											
										</div>
									</td>
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
						</div>
					</td>
					<td width="33%">
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center1">
											<span class="headerLabelSpan">
												<table cellspacing="0" cellpadding="0">
													<tr>
														<td align="left">Know Your</td>
														<td></td>
													</tr>
													<tr>
														<td align="left">Parties</td>
														<td style="padding:2px 0px 0px 8px"><img width="10" width="10" src="images/icons/homePage_new/widgetHeaderIcon.jpeg"></td>
													</tr>
												</table>
												 
											</span>
										</div>
									</td>
									<td width="8px"><img width="8" height="40" src="images/icons/homePage_new/white_header_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:250px;">
							<div id="partiesNews"></div>
							
								<!--<iframe frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"
										src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?q=INC%2C%20TDP%2C%20TRS%2C%20PRP%2C%20CPI%2C%20CPM%2C%20DMK%2CAIADMK&ned=in&rsz=small&hl=en&format=300x250">
								</iframe>-->
							</div>		
							<div class="productFeatureFooter">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_left.jpeg"/></td>
									<td width="98%">
										<div class="productFeatureFooterBackground_center">
											
										</div>
									</td>
									<td width="12px"><img width="12" height="25" src="images/icons/homePage_new/white_footer_image_right.jpeg"/></td>
								  </tr>
								</table>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>

        <!--<div id="homePageLocationWidgets">
        	<table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td width="24%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/1.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#5889a3;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your State  </span>
                                    </div>
                                </td>
                                 <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/1r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#3e728e;">
							<table>
								<tr>
									<td style="text-align:justify;padding-bottom:30px;"> Select your state to view its Assembly, Parliament, Local Bodies election results. </td>
								</tr>
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/></td>									
								</tr>								
							</table>
						</div>
                        <div class="widgetsFooter" style="background-color:#5889a3;">
							<img width="70" height="25" src="images/icons/homePage_new/b1.jpg" onclick="navigateToStatePage()"></img>
						</div>
                    </div>
                </td>
                <td width="24%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/2.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#dea659;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your District  </span>
                                    </div>
                                </td>   
								 <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/2r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#d98d06;">
							<div id="alertMessage_district" style="color:red;font-weight:bold;"></div>
							<table>
								<tr>
									<td style="text-align:justify;padding-bottom:5px;"> Select your district to view its election results in district level. </td>
								</tr>
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"/></td>
								</tr>
								<tr>
									<td><%=distSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/></td>
								</tr>
							</table>
						</div>
                        <div class="widgetsFooter" style="background-color:#dea659;">
							<img width="70" height="25" src="images/icons/homePage_new/b2.jpg" onclick="navigateToDistrictPage()"></img>
						</div>
                    </div>
                </td>
                <td width="24%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/3.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center"  style="background-color:#efdcbc;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your Constituency  </span>
                                    </div>
                                </td>  
								<td width="1%"><img width="35" height="40" src="images/icons/homePage_new/3r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#e8cda0;">
                        	<div id="alertMessage" style="color:red;font-weight:bold;"></div>
							<table>
								<tr>
									<td colspan="4">Select Constituency Type</td>
								</tr>	
								<tr>
									<td colspan="2"><input type="radio" checked="checked" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/><%=assembly%></td>
									<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/><%=parliament%></td>
								</tr>
							</table>
							<table id="stateTable" style="display:none;">
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name" onchange="getAllConstituenciesInStateByType(2,this.options[this.selectedIndex].value,'constituency')"/></td>
								</tr>
							</table>
								
							
							<table id="constTable" style="display:none;">
								<tr>
									<td><%=constSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
								</tr>
							</table>					
						
						</div>
                        <div class="widgetsFooter" style="background-color:#efdcbc;">
							<img width="70" height="25" src="images/icons/homePage_new/b3.jpeg" onclick="navigateToConstituencyPage()"></img>
						</div>
                    </div>
                </td>                
                <td width="28%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img width="35" height="40" src="images/icons/homePage_new/4.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#a0a5a7;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Local Bodies Elections  </span>
                                    </div>
                                </td>
								<td width="1%"><img width="35" height="40" src="images/icons/homePage_new/4r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#909597;">
							<table>								
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_l" list="statesList" listKey="id" listValue="name" onchange="getLocalBodiesForState(this.options[this.selectedIndex].value)"/></td>									
								</tr>
								<tr>
									<td><div id="localBodiesRadioDiv_label"><%=localBody%></div></td>
								</tr>
								<tr>
									<td><div id="localBodiesRadioDiv_data"></div></td>									
								</tr>
								<tr>
									<td><div id="localBodiesSelectDiv_label"></div></td>
								</tr>
								<tr>
									<td><div id="localBodiesSelectDiv_data"></div></td>									
								</tr>
								<tr>
									<td><div id="localBodies_errorDiv"></div></td>									
								</tr>
							</table>
						</div>
                        <div class="widgetsFooter" style="background-color:#a0a5a7;">
							<img width="70" height="25" src="images/icons/homePage_new/b4.jpg" onclick="navigateToLocalBodyPage()"></img>
						</div>
                    </div>
                </td>
              </tr>
            </table>

        </div>-->

		<table>
			<tr>
				<td width="70%" valign="top">
					<!-- News Div start -->
					<div id="newsDivMain" class="widgetsMain">
						<div class="widgetsHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/widgetHeader_left.jpg"/></td>
								<td width="98%">
									<div class="widgetHeaderBackground_center">
										<span class="headerLabelSpan" style="color:#102F30;text-decoration:underline">
											Latest News  
										</span>
									</div>
								</td>
								<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/widgetHeader_right.jpg"/></td>
							  </tr>
							</table>
						</div>
						<div class="widgetsBody" style="background-color:#DEEAE6;height:auto">
							<div class="newsDataMain">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td rowspan="3" valign="top" width="100px">
									<img width="80" height="75" src="images/icons/homePage_new/telangana.jpg"/>
								</td>
								<td valign="top"> <span class="newsHeaderLabel"> Telangana Bye Elections - 2010 </span></td>
								</tr>
								<tr>                               
								<td valign="top">
									<div class="newsContent">
									Crucial elections for major parties like INC, TDP and TRS in Andhra Pradesh. What is each
									parties strengths in these constituencies? What are the factors influence these elections
									like non participating parties (PRP, BJP, Loksatta etc) ...? 
									</div>
								</td>
								</tr>
								<tr>                               
								<td valign="top">
									<div class="newsAnchorDiv">
									<a href="biElectionAction.action" class="newsAnchor">Know More</a>
									</div>
								</td>
								</tr>
								</table>
							</div>
							
							<div class="newsDataMain">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td rowspan="3" valign="top" width="100px">
									
								</td>
								<td valign="top">
									<span class="newsHeaderLabel">Govt to talk to allies on fuel price hike:pranab </span>
								 </td>
								</tr>
								<tr>                               
								<td valign="top">
									<div class="newsContent">
									Finance minister Pranab Mukherjee on Wednesday briefed the Congress MP's in the backdrop 
									of demands for a rollback in the fuel prices and explained why reduction in the excise
									duty on petroleum products was difficult.
									</div>
								</td>
								</tr>
								<tr>                               
								<td valign="top">
									<div class="newsAnchorDiv">
									<a href="biElectionAction.action" class="newsAnchor">Know More</a>
									</div>
								</td>
								</tr>
								</table>
							 </div>
						</div>
						<div >
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/widgetHeaderBottom_left.jpg"/></td>
								<td width="98%">
									<div class="widgetHeaderBackground_center">
										<span class="headerLabelSpan">  </span>
									</div>
								</td>
								<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/widgetHeaderBottom_right.jpg"/></td>
							  </tr>
							</table>
						</div>
					</div>
					<!-- News Div End -->       
				</td>
				<td width="30%" valign="top">
					<div id="pollsWidgetMain">
						<div id="pollsWidgetHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img width="45" height="40" src="images/icons/homePage_new/poll_header_left.jpg"/></td>
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
						 </div>
						<div id="pollsWidgetBody" class="yui-skin-sam" style="height:260px;">
						</div>
						<div id="pollsWidgetFooter">
							
						</div>
					</div>
				</td>
			</tr>
		</table>		
		
        <div id="index_footer" class="indexLayoutContainer">
			<div id="cpyRight_info" style="padding:10px 0px 10px 0px;">
				<table width="100%">
					<tr>
						<td valign="top" style="width:50px"><img width="60" height="40" src="images/icons/indexPage/importantNote.png"></img> </td>
						<td valign="top">
							<div id="copyRightTextDiv"> The information displayed in this website are based on the data provided by the Election Commmission Of India.
							Further suggestions and corrections please contact us at <font color="#b76823"><b>info@itgrids.com</b></font></div> </td>
					</tr>
				</table>
				
			</div>
            <div id="index_inner_footer">
            <table width="100%" id="copyrightLinksTable">
                <tr>
                    <td align="left"> © Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
                    <td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
                </tr>
            </table>
            </div>
        </div>
	</div>
	
<script type="text/javascript">
	initializeHomePage();
</script>
	
</body>
</html>