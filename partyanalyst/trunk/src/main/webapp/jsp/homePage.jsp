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
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>

<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/>
<link  rel="stylesheet" type="text/css" href="styles/homePage/homePage.css"/>
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />

</head>
<body>
	
	<div id="homePageContainer">
		<div id="indexheader" class="indexLayoutContainer" style="overflow:visible">
            <table  width="100%" id="headerTable">
                <tr>
                    <td style="vertical-align:top;width:580px;">
                        <div id="pa_Logo">
                        	<img src="images/icons/homePage/pa_logo.jpg" style="padding-left: 10px; padding-top: 5px;"></img>
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
        <div></div>
        <div id="homePage_header">
        	<img src="images/icons/homePage_new/homePage_header.jpg"/>
        </div>
        <div id="homePageContent_main">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="40%" valign="top">
                	<div>
                    	<div class="widgetsMain">
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
                                 
                                 <div class="newsDataMain">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                    <td rowspan="3" valign="top" width="100px">
                                        
                                    </td>
                                    <td valign="top">
                                        <span class="newsHeaderLabel">All-India bandh call by trade unions: Bengal, Kerala worst hit </span>
                                     </td>
                                    </tr>
                                    <tr>                               
                                    <td valign="top">
                                        <div class="newsContent">
                                        Nine major trade unions have called for a 24-hour long nationwide bandh today to 
                                        protest against price rise and other issues like disinvestment of public sector,
                                        retrenchment and entry of foreign capital in retail market. 
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
                            <div class="widgetsFooter">
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
                        <div></div>
                    </div>
                </td>
                <td width="40%" valign="top">
                	<div id="newsWidgetMain">
                    	<div id="newsWidgetHeader"> Latest Election Trendz </div>
                        <div id="newsWidgetBody">
                        	
                        </div>
                    </div>
                </td>
                <td width="20%" valign="top">
                	<div id="adDataMain">
                    	<div id="adDataMain_header"> Advertisements</div>
                    </div>                    
                </td>
              </tr>
            </table>
        </div>
        
        <div id="homePageLocationWidgets">
        	<table width="100%" border="0" cellspacing="10" cellpadding="0">
              <tr>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/cadreReport/bg_left.png"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center">
                                    	<span class="headerLabelSpan"> View Your State  </span>
                                    </div>
                                </td>
                                <td width="1%"><img src="images/icons/cadreReport/bg_right.png"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody">
							<table>
								<tr>
									<td>Select Your State</td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state_s" id="stateList_s" list="statesList" listKey="id" listValue="name" onchange="setStateValue()"/></td>									
								</tr>
								<tr>
									<td><a href="javascript:{}" onclick="navigateToStatePage()" /> View</a></td>
								</tr>
							</table>
						</div>
                        <div class="widgetsFooter"></div>
                    </div>
                </td>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/cadreReport/bg_left.png"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center">
                                    	<span class="headerLabelSpan"> View Your District  </span>
                                    </div>
                                </td>
                                <td width="1%"><img src="images/icons/cadreReport/bg_right.png"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody">
							<table>
								<tr>
									<td>Select Your State</td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your State" name="state" id="stateList_d" list="statesList" listKey="id" listValue="name" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'districtList_d')"/></td>
								</tr>
								<tr>
									<td>Select Your District</td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="district" id="districtList_d" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select District"/></td>
								</tr>
								<tr>
									<td><a href="javascript:{}" onclick="navigateToDistrictPage()"/> View</a></td>
								</tr>
							</table>
						</div>
                        <div class="widgetsFooter"></div>
                    </div>
                </td>
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/cadreReport/bg_left.png"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center">
                                    	<span class="headerLabelSpan"> View Your Constituency  </span>
                                    </div>
                                </td>
                                <td width="1%"><img src="images/icons/cadreReport/bg_right.png"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody">
							<table>
								<tr>
									<td>Select Your State</td>
								</tr>
								<tr>
									<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="state" id="stateList_c" list="statesList" listKey="id" listValue="name"/></td>
								</tr>
							</table>
							<table>	
								<tr>
									<td colspan="2"><input type="radio" name="a_radio" id="a_radio" onclick="hideUnhideSelectBox(this.id, 'constituency')"/>Assembly</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="radio" name="a_radio" id="p_radio" onclick="hideUnhideSelectBox(this.id,'constituency')"/>Parliament</td>
								</tr>
							</table>	
							<table id="constTable" style="display:none;">
								<tr>
									<td>Select Constituency</td>
								</tr>
								<tr>
									<td><s:select theme="simple" cssClass="selectBoxWidth" label="Select Your Constituency" name="constituency" id="constituency" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue="Select Constituency"/></td>
								</tr>
							</table>
							<table>
								<tr>
									<td><a href="javascript:{}" onclick="navigateToConstituencyPage()"/> View</a></td>
								</tr>
							</table>
						
						</div>
                        <div class="widgetsFooter"></div>
                    </div>
                </td>                
                <td width="25%">
                	<div class="widgetsMain">
                    	<div class="widgetsHeader">
                        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                              <tr>
                                <td width="1%"><img src="images/icons/cadreReport/bg_left.png"/></td>
                                <td width="98%">
                                	<div class="headerBackground_center">
                                    	<span class="headerLabelSpan"> View MPTC/ZPTC Elections  </span>
                                    </div>
                                </td>
                                <td width="1%"><img src="images/icons/cadreReport/bg_right.png"/></td>
                              </tr>
                            </table>
                        </div>
                        <div class="widgetsBody"></div>
                        <div class="widgetsFooter"></div>
                    </div>
                </td>
              </tr>
            </table>

        </div>
        
        <div id="index_footer" class="indexLayoutContainer">
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