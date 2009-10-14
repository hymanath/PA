<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Report</title>
	<!-- YUI Dependency Files-->
	
	<link href="styles/yuiStyles/treeview.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/calendar.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/calendar-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>	

	<!-- YUI Dependency Files-->
	<script type="text/javascript">
			function showTree()
			{
				
				var myLabel='${userCadresInfoVO.userAccessType}-${userCadresInfoVO.totalCadres}';
				
				var tree;
				var myobj;
				//instantiate the TreeView control: 
				 tree = new YAHOO.widget.TreeView("cadreInfoDivBody"); 
				// tree.setDynamicLoad(loadNodeData);
				//get a reference to the root node; all 
				//top level nodes are children of the root node: 
				var rootNode = tree.getRoot(); 
				 
				//begin adding children 

				myobj = { label: myLabel, myNodeId:"State1" } ; 
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode); 

				myobj = { label: "State 1", myNodeId:"State_1" } ; 
				var StateNode1 = new YAHOO.widget.TextNode(myobj, stateNode); 
		 
				myobj = { label: "State 2", myNodeId:"State_2" } ; 
				var StatetNode2 = new YAHOO.widget.TextNode(myobj, stateNode); 

				myobj = { label: "District 1", myNodeId:"District 1" } ; 
				var districtNode1 = new YAHOO.widget.TextNode(myobj, StateNode1); 
		 
				myobj = { label: "District 2", myNodeId:"District2" } ; 
				var districtNode2 = new YAHOO.widget.TextNode(myobj, StateNode1); 

				tree.render(); 
			}

			function loadNodeData(node, fnLoadComplete)
			{
				var nodeLabel = node.label;
				var index=nodeLabel.indexOf("-");
				var subString = nodeLabel.substring(0,index);
				
				var cadreUrl = "<%=request.getContextPath()%>/cadresInfoAjaxAction.action?cadreRegion="+subString; 
				var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								console.log("request completed"); 
								//processResponse(param, myResults);
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 			YAHOO.util.Connect.asyncRequest('GET', cadreUrl, callback);
			}
	</script>
</head>
<body>
	
	<h3>Welcome to cadre report page.</h3>
	<div id="cReportMain" style="text-align: left; margin-left: 50px; font-size: 12px; font-family: Verdana;">
		<div id="cadreInfoDiv" style="margin-bottom: 20px;">
			<div id="cadreInfoDivHead">
				<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
				Cadres Availabilty <b>:</b>
			</div>
			<div id="cadreInfoDivBody" style="padding-left: 50px;padding-top: 10px">
				<script type="text/javascript">
						showTree();
				</script>				
			</div>
		</div>
		<div id="zeroCadreInfoDiv" style="margin-bottom: 20px;">
			<c:if test="${userCadresInfoVO.regionLevelZeroCadres}>0">
				<div id="zeroCadreInfoDivHead">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					Non Available Region Cadres <b>:</b>
				</div>
			</c:if>
			<div id="zeroCadreInfoDivBody" style="padding-left: 50px;padding-top: 10px"">
				<table>
					<c:forEach var="pd" items="${userCadresInfoVO.regionLevelZeroCadres}" >
					<tr>
						<td><c:out value="${pd.key}" /> </td>
						<td><c:out value="${pd.value}" /></td>
					</tr>
					</c:forEach>			
				</table>
			</div>
		</div>
		<div id="levelCadreInfoDiv" style="margin-bottom: 20px;">
			<div id="levelCadreInfoDivHead">
				<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
				Region Level Cadres Available <b>:</b>
			</div>
			<div id="levelCadreInfoDivBody" style="padding-left: 50px;padding-top: 10px"">
				<table>
				<c:forEach var="pd1" items="${userCadresInfoVO.regionLevelCadres}" >
				<tr>
					<td><c:out value="${pd1.key}" /></td>
					<td>  level cadres - </td>
					<td><c:out value="${pd1.value}" /></td>
				</tr>
				</c:forEach>
				</table>
			</div>
		</div>	
	</div>
</body>
</html>