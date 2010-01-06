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

	
<link rel="stylesheet" type="text/css" href="js/assets/dpSyntaxHighlighter.css">
<link rel="stylesheet" type="text/css" href="js/build/container/assets/skins/sam/container.css" />
<link rel="stylesheet" type="text/css" href="js/build/button/assets/skins/sam/button.css" />


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

	
<script type="text/javascript" src="js/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/build/event/event-min.js"></script>
<script type="text/javascript" src="js/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/build/container/container-min.js"></script>
<script type="text/javascript" src="js/build/element/element-min.js"></script>
<script type="text/javascript" src="js/build/button/button-min.js"></script>
	
	<!-- YUI Dependency Files-->
	<script type="text/javascript">
			
		var regionLevelZeroCadres = new Array();
		var regionLevelCadres = new Array();
		var cadreDetailsArr = new Array();

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
				
				var divElmt = document.getElementById("zeroCadreInfoDivBody");
				
				if(!divElmt)
					alert("No div element present to create tree");

				var childDivElmt = document.createElement('div');
				childDivElmt.setAttribute('id',val+''+id+'ZeroCadreDiv');
				divElmt.appendChild(childDivElmt);
				

				tree = new YAHOO.widget.TreeView(childDivElmt.id);					 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: val,id:id ,type:'ZeroLevelCadre'} ;
				
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);
				

				tree.render();
			}

			function buildRegionCadreTree(id,val)
			{
				var myLabel=val+"-"+id;
				
				var tree;
				var myobj;
				var divElmt = document.getElementById("cadreLevelInfoDivBody");
				
				if(!divElmt)
					alert("No div element present to create tree");

				var childDivElmt = document.createElement('div');
				childDivElmt.setAttribute('id',val+''+id+'RegioncadreDiv');
				divElmt.appendChild(childDivElmt);
				

				tree = new YAHOO.widget.TreeView(childDivElmt.id);				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: val,id:id ,type:'RegionLevelCadre'} ;
				
				var stateNode = new YAHOO.widget.TextNode(myobj, rootNode);				

				tree.render();
			}		
			
			function showTree(cType)
			{
				//'${userCadresInfoVO.userAccessType}'
				var myLabel='${userCadresInfoVO.userAccessDisplayValue}-${userCadresInfoVO.totalCadres}';
				
				var tree;
				var myobj;
				
				tree = new YAHOO.widget.TreeView("cadreInfoDivBody");				 
				tree.setDynamicLoad(loadNodeData);				
				var rootNode = tree.getRoot(); 				

				myobj = { label: myLabel, labelAccessValue: '${userCadresInfoVO.userAccessType}',id:'${userCadresInfoVO.userAccessValue}',type:cType } ; 
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
											{
												//console.log(myResults);
												buildHtmlNode(myResults.cadreInfo,node);																					
											}
											else if(myResults.zeroCadresRegion)	
												buildZeroCadreTable(myResults.zeroCadresRegion,node);
											else
												buildTextNode(myResults,node);																			
											
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
			
			function buildZeroCadreTable(cadreData,node)
			{
				var str='';
				str+='<table class="partyPerformanceCriteriaTable">';
				str+='<tr>';
				str+='<th>Name</th>';											
				str+='</tr>';				
				for(var i in cadreData)
				{
					str+='<tr>';					
					str+='<td>'+cadreData[i].name+'</td>';											
					str+='</tr>';
				}
				str+='</table>';

				//str+='<div id="basic" class="yui-skin-sam"></div>';
				var tempNode = new YAHOO.widget.HTMLNode(str, node, false); 
				tempNode.isLeaf = true;
			}
			function buildHtmlNode(cadreData,node)
			{
				var obj={
							"label":node.label,
							"obj":cadreData
						};
				
				cadreDetailsArr.push(obj);
				
				var tempNode = new YAHOO.widget.HTMLNode('<a href="javascript:{}" id="'+node.label+'" onclick="showLink(this.id)"/>view Details</a>', node, false); 
				tempNode.isLeaf = true;
			}

			function showLink(value)
			{	
				
				var cadreData = new Array();

				for(var i in cadreDetailsArr)
				{
					if(cadreDetailsArr[i].label == value)
					{
						cadreData = cadreDetailsArr[i].obj;
					}
				}

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
					str+='<td>'+cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName+'</td>';
					str+='<td>'+cadreData[i].mobile+'</td>';
					str+='<td>'+cadreData[i].landLineNo+'</td>';
					str+='<td>'+cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue+'</td>';
					str+='<td>'+cadreData[i].email+'</td>';					
					str+='</tr>';
				}
				str+='</table>';
//alert(str);
var panel = new YAHOO.widget.Panel("panel2", { width:"320px", visible:true, draggable:true, close:true } ); 
panel.setHeader("List of Cadres"); 
panel.setBody(str); 

panel.render("divPanel"); 

			YAHOO.util.Event.addListener("show1", "click", YAHOO.example.container.panel1.show, panel, true);
			//YAHOO.util.Event.addListener("hide1", "click", YAHOO.example.container.panel1.hide, YAHOO.example.container.panel1, true);
				//console.log(str);
			}

			function buildTextNode(results,node)
			{
				for (var i in results.cadreRegionInfo)
				{ 
					var myobj = { 
									label				: results.cadreRegionInfo[i].regionName+"-"+results.cadreRegionInfo[i].cadreCount,
									labelAccessValue	: results.cadreRegionInfo[i].region,
									id					: results.cadreRegionInfo[i].regionId ,
									type				: results.cadreType
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
				
			</div>
		</div>
		<div id="cadreLevelInfoDiv" style="margin-bottom: 20px;">
			<div id="cadreLevelInfoDivHead">
				<img height="10" width="10" src="<%=request.getContextPath()%>/images/icons/arrow.png"/>
				Cadre Details By Cadre Level <b>:</b>
			</div>
			<div id="cadreLevelInfoDivBody" style="padding-left: 50px;padding-top: 10px"">
				
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

			showTree("TotalCadre");

			<c:forEach var="pd" items="${userCadresInfoVO.regionLevelZeroCadres}" >
				buildJSObject('${pd.id}','${pd.name}',"regionLevelZeroCadres");
			</c:forEach>

			<c:forEach var="pd1" items="${userCadresInfoVO.regionLevelCadres}" >
				buildJSObject('${pd1.value}','${pd1.key}','regionLevelCadres');
			</c:forEach>

			buildTreeView();	
		</script>	
	</s:form>
	<div id="divPanel">
	</div>
</body>
</html>