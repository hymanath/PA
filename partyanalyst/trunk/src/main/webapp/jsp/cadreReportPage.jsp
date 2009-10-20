<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Report</title>
	<!-- YUI Dependency Files-->
	
	<link href="styles/yuiStyles/treeview.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/calendar.css" rel="stylesheet" type="text/css" />
	<link href="styles/yuiStyles/datatable.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js" ></script>
	<script type="text/javascript" src="js/yahoo/animation-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/calendar-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/element-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/connection.js"></script> 
	<script type="text/javascript" src="js/yahoo/history.js"></script> 
	
	<!-- YUI Dependency Files-->
	<script type="text/javascript">
			function showTree()
			{
				
				var myLabel='${userCadresInfoVO.userAccessType}-${userCadresInfoVO.totalCadres}';
				
				var tree;
				var myobj;
				
				tree = new YAHOO.widget.TreeView("cadreInfoDivBody");				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, id:'${userCadresInfoVO.userAccessValue}' } ; 
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);
				

				tree.render();
				
			}

			function loadNodeData(node, fnLoadComplete)
			{
				var nodeLabel = node.label;
				var index=nodeLabel.indexOf("-");
				var subString = nodeLabel.substring(0,index);				
				
				var cadreUrl = "<%=request.getContextPath()%>/cadresInfoAjaxAction.action?cadreRegion="+subString+"&cadreId="+node.data.id; 
				var callback = {			
 		               success : function( o )
									{
										try 
										{
											myResults = YAHOO.lang.JSON.parse(o.responseText);								
											
											if(myResults.cadreInfo[0])											
												buildHtmlNode(myResults.cadreInfo,node);																					
											else											
												buildTextNode(myResults.cadreRegionInfo,node);																			
											
											fnLoadComplete(); 
											var anchortag = YAHOO.util.Dom.get("cadreInfoDivBody").getElementsByTagName("a");											
											YAHOO.util.Event.addListener(anchortag, "click", function (evt)
											{ 												
												href = this.getAttribute("href"); 
												window.open(href);												
											})
										}
										catch (e)
										{   
											alert("Invalid JSON result" + e);   
										}  
											
 									},
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         },

						 argument: { 
								"node": node, 
								"fnLoadComplete": fnLoadComplete 
						} 
 		               };

 			YAHOO.util.Connect.asyncRequest('GET', cadreUrl, callback);
			}
			
			function buildHtmlNode(cadreData,node)
			{
				
				var str='';
				str+='<table class="partyPerformanceCriteriaTable">';
				str+='<tr>';
				str+='<th>Name</th>';				
				str+='<th>MobileNo</th>';
				str+='<th>LandLineNo</th>';				
				str+='<th>CadreLevel</th>';
				str+='<th>Email</th>';				
				str+='</tr>';				
				for(var i in cadreData)
				{
					str+='<tr>';					
					str+='<td><a name="cadreName" href="cadreInformationPageAction.action">'+cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName+'</a></td>';		
					str+='<td>'+cadreData[i].mobileNo+'</td>';
					str+='<td>'+cadreData[i].landLineNo+'</td>';
					str+='<td>'+cadreData[i].cadreLevel+'</td>';
					str+='<td>'+cadreData[i].email+'</td>';					
					str+='</tr>';
				}
				str+='</table>';

				//str+='<div id="basic" class="yui-skin-sam"></div>';
				var tempNode = new YAHOO.widget.HTMLNode(str, node, false); 
				tempNode.isLeaf = true;
				
				
			}

			function buildTextNode(cadreData,node)
			{
				for (var i in cadreData)
				{ 
					var myobj = { 
									label	: cadreData[i].region+"-"+cadreData[i].cadreCount,
									id		: cadreData[i].regionId 
								} ;
					
					var tempNode = new YAHOO.widget.TextNode(myobj, node, false); 
					
				}				
			}

	</script>
</head>
<body>
	<s:form name="cadrereport" action="cadreRegisterPageAction" method="post">	
	<h3>Cadre Details Page.</h3>
	
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
			
				<div id="zeroCadreInfoDivHead">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					Non Available Region Cadres <b>:</b>
				</div>
			
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
		<div id="cadreRegistration" style="margin-left: 50px;">
			<table>
				<tr>
					<td>
						<input type="submit" name="registersubmit" value="Register Cadre"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	</s:form>
</body>
</html>