<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Voting Trendz Report</title>

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

<!-- YUI Dependency files (End) -->

<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRSqcWSqDo-rwCiW8VjO_0U_k7HAuxQBSweyAZ1v5ozDSPMDKAFtPwSrGw&sensor=true"
            type="text/javascript"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/electionVotingTrendz/electionVotingTrendz.js"></script>
<link rel="stylesheet" type="text/css"  href="styles/electionVotingTrendz/electionVotingTrendz.css"></link>

<script type="text/javascript">
	


function callAjax(jsObj,url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								console.log(myResults);
								
								if(jsObj.task == "getDistricts")
								{
									fillDistrictSelect(myResults);
								}
								if(jsObj.task == "getMandals")
								{
									fillMandalSelect(myResults);
								}
								if(jsObj.task == "getConstituencies")
								{
									fillConstituencySelect(myResults);
								}
								if(jsObj.task == "getVotingTrendz")
								{
									showElectionVotingTrendz(myResults,"votingTrendzReportContentOuter");
								}						
										
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

function getConstituenciesForElectionType()
{
	var stateSelectElement = document.getElementById("stateSelect");	
	var stateSelectValue = stateSelectElement.options[stateSelectElement.selectedIndex].value;
	if(stateSelectValue == 0)
		return;



	var elements = document.getElementsByTagName('input'); 
	var electionTypeId='';

		for(var i=0;i<elements.length;i++)
		{
			if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
			{
				electionTypeId = elements[i].value;				
			}
		}	
		
	

	var jsObj=	{
					electionTypeId:electionTypeId,
					stateId:stateSelectValue,
					task:"getConstituencies"
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getConstituenciesAjaxAction.action?"+rparam;

	callAjax(jsObj, url);
}

function getVotingTrendz()
{
	var stateSelectElmt = document.getElementById("stateSelect");		
	var constituencySelectElmt = document.getElementById("constituencySelect");

	var stateVal = stateSelectElmt.options[stateSelectElmt.selectedIndex].value;	
	var constituencyVal = constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value;

	var elements = document.getElementsByTagName('input'); 
	var electionTypeId='';

	for(var i=0;i<elements.length;i++)
	{
		if(elements[i].type=="radio" && elements[i].name=="electionType" && elements[i].checked==true)
		{
			electionTypeId = elements[i].value;				
		}
	}	
	
	var jsObj=	{
					stateValue:stateVal,					
					electionTypeId : electionTypeId,
					constituencyVal:constituencyVal,
					task:"getVotingTrendz"
				}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/getVotingTrendzAction.action?"+rparam;

	callAjax(jsObj, url);
}
window.history.forward(1);
</script>
</head>
<body>	
	<div id="votingTrendzmain">
		<div id="votingTrendzHeadingContentOuter" class="rounded"> 						
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>
				
				<div id="electionVotingpageHead"> Election Voting Trendz </div>
		</div>
		
		<div id="votingTrendzInputContentOuter" class="rounded"> 						
			<div class="corner topLeft"></div>
			<div class="corner topRight"></div>
			<div class="corner bottomLeft"></div>
			<div class="corner bottomRight"></div>
				<div id="votingTrendsInputMain">
					<div id="votingTrendzInputData">
						<table width="100%" id="votingTrendzInputTable">
							<tr>
								<th> Select Election Type</th>
								<td colspan="4"> 
									<div id="electionType">
										<input type="radio" name="electionType" value="2">Assembly
										<input type="radio" name="electionType" value="1">Parliament
									</div>
								</td>
							</tr>
							<tr>
								<th>Select State</th>
								<td> 
									<div id="stateSelectDiv">
										<s:select theme="simple" id="stateSelect" cssClass="selectBoxClass" name="stateSelect" list="stateList" listKey="id" listValue="name" onchange="getConstituenciesForElectionType(this.id)"></s:select>
									</div>
								</td>
								

								<th>Select Constituency</th>
								<td>
									<div id="constituencySelectDiv">
										<s:select theme="simple" id="constituencySelect" cssClass="selectBoxClass" name="constituencySelect" list="constituenciesList" listKey="id" listValue="name"></s:select>
									</div>
								</td>

							</tr>							
							<tr>
								<td colspan="6">
									<div style="text-align:center">
										<input type="button" value="Get Voting Trendz" onclick="getVotingTrendz()"></input>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div id="votingTrendzProcessingImg">
						
					</div>
				</div>
			</div>
			

			<div id="votingTrendzReportContentOuter" class="rounded"> 						
				<div class="corner topLeft"></div>
				<div class="corner topRight"></div>
				<div class="corner bottomLeft"></div>
				<div class="corner bottomRight"></div>					
			</div>
	</div>

	<script type="text/javascript">
		intializeVotingTrendzReport();
			
	</script>
</body>
</html>