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
<title>Welcome to Party Analyst</title>

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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container_core-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/menu/menu-min.js"></script>		

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
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/tabview/assets/skins/sam/tabview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/fonts/fonts-min.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/menu/assets/skins/sam/menu.css">


<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/Javascript" src="js/homePage/jquery.js"></script>
<script type="text/javascript" src="js/homePage/jquery.sudoSlider.min.js"></script>

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/homePage.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/jquerySlider.css"/>
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		String stateSelect = rb.getString("stateSelect");
		String distSelect = rb.getString("distSelect");
		String constSelect = rb.getString("constSelect");
		String assembly = rb.getString("assembly");
		String parliament = rb.getString("parliament");
		
		ResourceBundle resb = ResourceBundle.getBundle("global_ErrorMessages");
		String errorMsg = resb.getString("constTypeAlert");
%> }
var errotMsg = '<%=errorMsg%>';

<c:forEach var="state" items="${statesList}" >
			
	var obj={
				id:"${state.id}",
				name:"${state.name}"				
			};
	statesInCountryObj.push(obj);	
</c:forEach>



google.load("elements", "1", {packages : ["newsshow"]});

YAHOO.util.Event.onContentReady("navigationHead", function () { 	 
	    
	    var oMenuBar = new YAHOO.widget.MenuBar("navigationHead", {  
	                                                autosubmenudisplay: true,  
	                                                hidedelay: 100,  
	                                                lazyload: true }); 

		 
		oMenuBar.subscribe("beforeRender", function () { 
	 
	    if (this.getRoot() == this) { 	 
		this.getItem(0).cfg.setProperty("submenu", aSubmenuData[0]); 
		this.getItem(1).cfg.setProperty("submenu", aSubmenuData[1]); 
	    } 
	 
	}); 
	 
	oMenuBar.render();  
	 
	}); 

	var aSubmenuData = [ 
		{
			 id: "home", 
	        itemdata: [] 
		},	 
	    { 
	        id: "staticData",  
	        itemdata: [ 
	            { text: "Andhra Pradesh", url: "statePageAction.action?stateId=1" },
	            { text: "Tamil Nadu", url: "statePageAction.action?stateId=24" },
	            { text: "Karnataka", url: "statePageAction.action?stateId=12" },
				{ text: "Telangana Bye-Elections 2010", url: "biElectionAction.action" }
	        ]     
	    }
	]; 
</script>
</head>
<body>
	<div class="yui-skin-sam"><div id="electionResultsPopupDiv_inner"></div></div>
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
                                <td id="searchBox">
                                    <jsp:include page="../jsp/cncSearch.jsp"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>		
        </div>	
        <div id="indexNavContainer"  class="indexLayoutContainer yui-skin-sam">
		<div id="navigationHead" class="yuimenubar yuimenubarnav"> 
					<div class="bd"> 
						<ul class="first-of-type"> 
							<li class="yuimenubaritem" style="background:none;cursor:pointer;"> 
								<a class="yuimenubaritemlabel" href="<c:out value="${pageContext.request.contextPath}" />/homePage.action" >
									<img id="indexHomeImg" src="images/icons/indexPage/pa_home.png" title="home"/>								
									HOME
								</a> 
							</li> 
							<li class="yuimenubaritem"> 
								<a class="yuimenubaritemlabel" href="statePageAction.action?stateId=1">STATES</a> 
							</li> 
						</ul> 
					</div> 
			</div> 
		</div>

       <!-- <div id="homePage_header">
        	<img height="180" width="960" src="images/icons/homePage_new/homePage_header.jpg"/>
        </div> -->
        <div id="homePageContent_main">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="30%" valign="top">
                		
						<!-- Product Feature Div start -->
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>                                    
                                    <td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
                                    <td width="98%">
                                        <div class="productFeatureHeaderBackground_center">
                                            <span class="headerLabelSpan">
                                            	Election Comparison  
                                            </span>
                                        </div>
                                    </td>
                                    <td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
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
							
						</div>

						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tr>                                    
                                    <td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
                                    <td width="98%">
                                        <div class="productFeatureHeaderBackground_center">
                                            <span class="headerLabelSpan">
                                            	Party Result
                                            </span>
                                        </div>
                                    </td>
                                    <td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
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
										<td colspan="2">assembly/parliament/zptc/mptc/municipality in different party participated years in a single glance.The results can be classified and viewed in three different views like statewise or districtwise or constituencywise.</td>
									</tr>	
								</table>
								<div class="productFeature_button">
									<div class="viewReportButtonSpan" onclick="javascript:{window.location = 'partyResultsCriteriaAction.action'}">
										<span class="viewReportButtonLabel">View</span>
									</div>
								</div>
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
										<span class="headerLabelSpan" style="color:#3b3b3b">
											Latest Election Trendz
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/newsContainerHead_right.jpg"/></td>
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
								<td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
								<td width="98%">
									<div class="productFeatureHeaderBackground_center">
										<span class="headerLabelSpan">
											Advertisements
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
							  </tr>
							</table>
						</div>
						<div id="adDataMain_body">
							<div class="adData_main">
								<div class="adData_head">
									<a href="landing.action" class="newsHeadLink">Party Analyst</a>
								</div>
								<div class="adData_body">
									<table>
										<tr>
											<td valign="top"><a href="landing.action"><img src="images/icons/homePage_new/header_human_main.jpg"/></a></td>
											<td valign="top"><b>Political Analysis Software for politicians and political parties.</b></td>
										</tr>
										<tr>
											<td colspan="2">We help you to do Party Analysis, Politician Analysis,Cadre Management and Constituency Management through our effective tools.Creating a wealth of knowledge and know-how for a politician or a party to improve and stay on top.</td>
										</tr>
									</table>
									
								</div>
								<div class="adData_footer">
									<div onclick="javascript:{window.location = 'landing.action'}" class="votingTrendzHeadLabelDiv">
										<span class="votingTrendzHeadLabelSpan">Know More</span>
									</div>
								</div>								
							</div>
						</div>
                    </div>    
					
				
					<div id="giftWidgetMain" style="margin-top:5px;">
						<div id="giftWidgetHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img height="41" width="45" src="images/icons/homePage_new/gift_header_left.jpg"/></td>
								<td width="98%">
									<div class="electionTrendzHeaderBackground_center">
										<span class="headerLabelSpan" style="color:#3b3b3b">
											Gift Your MLA
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/poll_header_right.jpg"/></td>
							  </tr>
							</table>	
						 </div>
						<div id="giftWidgetBody" class="yui-skin-sam">
							
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
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center">
											<span class="headerLabelSpan">
												Leaders 
											</span>
										</div>
									</td>
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
								  </tr>
								</table>
							</div>							
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:220px;">
								<iframe frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"
										src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?q=Y%20S%20Jagan%2C%20Sonia%20Gandhi%2C%20Chandra%20Babu%20Naidu%2C%20K%20Chandrasekhar%20Rao%2CK%20Chiranjeevi%2CJaya%20Prakash%20Narayana%2C%20&ned=in&rsz=small&hl=en&format=300x250">
								</iframe>
							</div>						
						</div>
					</td>
					<td width="33%">
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="1%"><img src="images/icons/homePage_new/news_head_left.jpg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center" style="background-image:url('images/icons/homePage_new/news_Head_center.jpg');">
											<span class="headerLabelSpan">
												Top Stories 
											</span>
										</div>
									</td>
									<td width="1%"><img src="images/icons/homePage_new/1r.jpg"/></td>
								  </tr>
								</table>
							</div>
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:220px;">
								<iframe frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"
										src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?topic=n&ned=in&rsz=small&hl=en&format=300x250">
								</iframe>
							</div>						
						</div>
					</td>
					<td width="33%">
						<div class="productFeatureMain">							
							 <div class="productFeatureHeader">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								  <tr>                                    
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>
									<td width="98%">
										<div class="productFeatureHeaderBackground_center">
											<span class="headerLabelSpan">
												Parties 
											</span>
										</div>
									</td>
									<td width="1%"><img src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>
								  </tr>
								</table>
							</div>
							<div class="productFeatureBody" style="overflow:hidden;width:300px;height:220px;">
								<iframe frameborder="0" width="300" height="250" marginwidth="0" marginheight="0"
										src="http://www.google.com/uds/modules/elements/newsshow/iframe.html?q=INC%2C%20TDP%2C%20TRS%2C%20PRP%2C%20CPI%2C%20CPM%2C%20DMK%2CAIADMK&ned=in&rsz=small&hl=en&format=300x250">
								</iframe>
							</div>						
						</div>
					</td>
				</tr>
			</table>
		</div>

        <div id="homePageLocationWidgets">
        	<table width="100%" border="0" cellspacing="5" cellpadding="0">
              <tr>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/homePage_new/1.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#5889a3;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your State  </span>
                                    </div>
                                </td>
                                 <td width="1%"><img src="images/icons/homePage_new/1r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#3e728e;">
							<table>
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/></td>									
								</tr>								
							</table>
						</div>
                        <div class="widgetsFooter" style="background-color:#5889a3;">
							<img src="images/icons/homePage_new/b1.jpg" onclick="navigateToStatePage()"></img>
						</div>
                    </div>
                </td>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/homePage_new/2.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#dea659;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your District  </span>
                                    </div>
                                </td>   
								 <td width="1%"><img src="images/icons/homePage_new/2r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#d98d06;">
							<table>
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
							<img src="images/icons/homePage_new/b2.jpg" onclick="navigateToDistrictPage()"></img>
						</div>
                    </div>
                </td>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/homePage_new/3.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center"  style="background-color:#efdcbc;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View Your Constituency  </span>
                                    </div>
                                </td>  
								<td width="1%"><img src="images/icons/homePage_new/3r.jpg"/></td>
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
									<td colspan="2"><input type="radio" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/><%=assembly%></td>
									<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/><%=parliament%></td>
								</tr>
							</table>
							<table id="stateTable" style="display:none;">
								<tr>
									<td><%=stateSelect%></td>
								</tr>
								<tr>
									<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"/></td>
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
							<img src="images/icons/homePage_new/b3.jpg" onclick="navigateToConstituencyPage()"></img>
						</div>
                    </div>
                </td>                
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/homePage_new/4.jpg"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center" style="background-color:#a0a5a7;">
                                    	<span class="headerLabelSpan" style="color:#000000"> View MPTC/ZPTC Elections  </span>
                                    </div>
                                </td>
								<td width="1%"><img src="images/icons/homePage_new/4r.jpg"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody" style="background-color:#909597;"></div>
                        <div class="widgetsFooter" style="background-color:#a0a5a7;">
							<img src="images/icons/homePage_new/b4.jpg" onclick=""></img>
						</div>
                    </div>
                </td>
              </tr>
            </table>

        </div>

		<table>
			<tr>
				<td width="70%" valign="top">
					<!-- News Div start -->
					<div id="newsDivMain" class="widgetsMain">
						<div class="widgetsHeader">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
							  <tr>
								<td width="1%"><img src="images/icons/homePage_new/widgetHeader_left.jpg"/></td>
								<td width="98%">
									<div class="widgetHeaderBackground_center">
										<span class="headerLabelSpan" style="color:#102F30;text-decoration:underline">
											Latest News  
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/widgetHeader_right.jpg"/></td>
							  </tr>
							</table>
						</div>
						<div class="widgetsBody" style="background-color:#DEEAE6;height:auto">
							<div class="newsDataMain">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
								<td rowspan="3" valign="top" width="100px">
									<img src="images/icons/homePage_new/telangana.jpg"/>
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
								<td width="1%"><img src="images/icons/homePage_new/widgetHeaderBottom_left.jpg"/></td>
								<td width="98%">
									<div class="widgetHeaderBackground_center">
										<span class="headerLabelSpan">  </span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/widgetHeaderBottom_right.jpg"/></td>
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
								<td width="1%"><img src="images/icons/homePage_new/poll_header_left.jpg"/></td>
								<td width="98%">
									<div class="electionTrendzHeaderBackground_center">
										<span class="headerLabelSpan" style="color:#C66E17;top:13px;">
											Polls
										</span>
									</div>
								</td>
								<td width="1%"><img src="images/icons/homePage_new/poll_header_right.jpg"/></td>
							  </tr>
							</table>	
						 </div>
						<div id="pollsWidgetBody" class="yui-skin-sam">
							
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
						<td valign="top" style="width:50px"><img src="images/icons/indexPage/importantNote.png"></img> </td>
						<td valign="top">
							<div id="copyRightTextDiv"> The information displayed in this website are based on the data provided by the Election Commmission Of India.
							Further suggestions and corrections please contact us at <font color="#b76823"><b>info@itgrids.com</b></font></div> </td>
					</tr>
				</table>
				
			</div>
            <div id="index_inner_footer">
            <table width="100%" id="copyrightLinksTable">
                <tr>
                    <td align="left"> � Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
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