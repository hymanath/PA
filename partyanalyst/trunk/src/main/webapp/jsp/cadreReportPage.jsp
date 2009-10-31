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
			
		var regionLevelZeroCadres = new Array();
		var regionLevelCadres = new Array();

			function buildJSObject(id,value,type)
			{				
				var ob =
					{
						id:id,
						val:value
					};

				if(type=="regionLevelZeroCadres")
					regionLevelZeroCadres.push(ob);
				else if(type="regionLevelCadres")
					regionLevelCadres.push(ob);


			}

			function buildTreeView()
			{
				for (var i in  regionLevelZeroCadres)
				{
					buildZeroCadreTree(regionLevelZeroCadres[i].id,regionLevelZeroCadres[i].val);
				}
				
				for (var j in  regionLevelCadres)
				{
					buildRegionCadreTree(regionLevelCadres[j].id,regionLevelCadres[j].val);
				}
			}

			function buildZeroCadreTree(id,val)
			{
				var myLabel=val+"-"+id;
				
				var tree;
				var myobj;
				
				tree = new YAHOO.widget.TreeView("zeroCadreInfoDivBody");				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: val,id:id ,type:'ZeroLevelCadre'} ;
				console.log(myobj);
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);
				

				tree.render();
			}

			function buildRegionCadreTree(id,val)
			{
				var myLabel=val+"-"+id;
				
				var tree;
				var myobj;
				
				tree = new YAHOO.widget.TreeView("cadreLevelInfoDivBody");				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: val,id:id ,type:'RegionLevelCadre'} ;
				console.log(myobj);
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);				

				tree.render();
			}		
			
			function showTree()
			{
				//'${userCadresInfoVO.userAccessType}'
				var myLabel='${userCadresInfoVO.userAccessDisplayValue}-${userCadresInfoVO.totalCadres}';
				
				var tree;
				var myobj;
				
				tree = new YAHOO.widget.TreeView("cadreInfoDivBody");				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: '${userCadresInfoVO.userAccessType}',id:'${userCadresInfoVO.userAccessValue}',type:'TotalCadre' } ; 
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);
				

				tree.render();
				
			}

			function loadNodeData(node, fnLoadComplete)
			{
				var nodeLabel = node.label;
				var index=nodeLabel.indexOf("-");
				var subString = nodeLabel.substring(0,index);				
				
				var cadreUrl = "<%=request.getContextPath()%>/cadresInfoAjaxAction.action?cadreRegion="+node.data.labelAccessValue+"&cadreId="+node.data.id+"&cadreType="+node.data.type; 
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
												window.location.assign(href);												
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
					str+='<td>'+cadreData[i].mobile+'</td>';
					str+='<td>'+cadreData[i].landLineNo+'</td>';
					str+='<td>'+cadreData[i].strCadreLevel+'-'+strCadreLevelValue+'</td>';
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
									label	: cadreData[i].regionName+"-"+cadreData[i].cadreCount,
									labelAccessValue : cadreData[i].region,
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
	<table>
		<tr>
			<td>
	<div id="cReportMain" style="text-align: left; font-size: 12px; font-family: Verdana;">
		<div id="cadreInfoDiv" style="margin-bottom: 20px;">
			<div id="cadreInfoDivHead">
				<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
				Cadres Availabilty <b>:</b>
			</div>
			<div id="cadreInfoDivBody" style="padding-left: 50px;padding-top: 10px">
				
			</div>
		</div>
		<div id="zeroCadreInfoDiv" style="margin-bottom: 20px;">
			
				<div id="zeroCadreInfoDivHead">
					<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
					Non Available Region Cadres <b>:</b>
				</div>
			
			<div id="zeroCadreInfoDivBody" style="padding-left: 50px;padding-top: 10px">				
				<table>
					<c:forEach var="pd" items="${userCadresInfoVO.regionLevelZeroCadres}" >
					<tr>
						<td><c:out value="${pd.id}" /> </td>
						<td><c:out value="${pd.name}" /></td>
					</tr>
					</c:forEach>			
				</table>
			</div>
		</div>
		<div id="cadreLevelInfoDiv" style="margin-bottom: 20px;">
			<div id="cadreLevelInfoDivHead">
				<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
				Region Level Cadres Available <b>:</b>
			</div>
			<div id="cadreLevelInfoDivBody" style="padding-left: 50px;padding-top: 10px"">
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
			<table align="middle">
				<tr>
					<td>
						<input type="submit" name="registersubmit" value="Register Cadre"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
			</td>
		</tr>
	</table>
		<script type="text/javascript">

			showTree();

			<c:forEach var="pd" items="${userCadresInfoVO.regionLevelZeroCadres}" >
				buildJSObject('${pd.id}','${pd.name}',"regionLevelZeroCadres");
			</c:forEach>

			<c:forEach var="pd1" items="${userCadresInfoVO.regionLevelCadres}" >
				buildJSObject('${pd1.value}','${pd1.key}','regionLevelCadres');
			</c:forEach>

			buildTreeView();	
		</script>	
	</s:form>
</body>
</html>