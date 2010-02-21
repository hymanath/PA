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
	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

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
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

	<!-- YUI Dependency Files-->

	<script type="text/javascript"><!--
			
		var regionLevelZeroCadres = new Array();
		var regionLevelCadres = new Array();
		var cadreDetailsArr = new Array();
		//var cadresArray = new Array();
		var panel;

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
				
				var myLabel='${userCadresInfoVO.userAccessDisplayValue}';

				var type='${userCadresInfoVO.userAccessType}';
				if(type=="MLA" || type=="MP"){
					myLabel+="-Constituency-";
					myLabel+='${userCadresInfoVO.totalCadres}'
				}
				else{
					myLabel+="-"+type[0].toUpperCase();
					for(var i=1;i<type.length;i++){
						myLabel+=type[i].toLowerCase();
					}
					myLabel+="-"+'${userCadresInfoVO.totalCadres}';
				}
				
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
												buildHtmlNode(myResults.cadreInfo,node);	
																															
											}
											else if(myResults.zeroCadresRegion)	{
												buildZeroCadreTable(myResults,myResults.zeroCadresRegion,node);
											}
											else{
												buildTextNode(myResults,node);																			
											}
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
			
			function buildZeroCadreTable(myResults,cadreData,node)
			{
				
				var myLabel='${userCadresInfoVO.userAccessDisplayValue}';
				var location = myLabel;
				
				var type='${userCadresInfoVO.userAccessType}';
				if(type=="MLA" || type=="MP"){
					myLabel="Constituency Name";
				}
				else if(type=="DISTRICT"){
					myLabel="District Name";
				}
				else if(type=="STATE"){
					myLabel="State Name";
				}
				else if(type=="COUNTRY"){
					myLabel="Country Name";
				}
								
				var str='';
				str+='<table class="partyPerformanceCriteriaTable">';
				str+='<tr>';
				str+='<th>Name</th>';	
				str+='<th>'+myLabel+'</th>';											
				str+='</tr>';				
				for(var i in cadreData)
				{
					str+='<tr>';					
					str+='<td>'+cadreData[i].name+'</td><td>'+location+'</td>';										
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
				var divElmt = document.getElementById("cReportMain");
				var cadreData = new Array();
				var cadresArray = new Array();

				if(panel)
					panel.destroy();

				for(var i in cadreDetailsArr)
				{
					if(cadreDetailsArr[i].label == value)
					{
						cadreData = cadreDetailsArr[i].obj;
					}
				}
				

				for(var i in cadreData)
				{
					var cObj={
								name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
								mobile:cadreData[i].mobile,
								landLine:cadreData[i].landLineNo,
								cadreLevel:cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
								email:cadreData[i].email
							 };

					cadresArray.push(cObj);
				}
				
				
				var divChild = document.createElement('div');
				divChild.setAttribute('id','cadreDiv');
				divChild.setAttribute('class','yui-skin-sam');
				var str='';
				str+='<div class="hd">List Of Cadres...</div> ';
				str+='<div class="bd">'; 
				str+='<div id="cadresDetailsDiv"></div>';
				str+='</div>';

				divChild.innerHTML=str;
				divElmt.appendChild(divChild);

				var myColumnDefs = [ 	           
										{key:"name",label : "Name",sortable:true,resizeable:true}, 
										{key:"mobile",label : "Mobile", sortable:true, resizeable:true}, 
										{key:"landLine",label : "Landline", sortable:true, resizeable:true},
										{key:"cadreLevel",label : "Cadre Level", sortable:true, resizeable:true}, 
										{key:"email",label : "Email",sortable:true, resizeable:true}
									]; 
				var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
				myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
				myDataSource.responseSchema = { 
				fields : [
							{key : "name"}, {key : "mobile",parser:"number"}, {key : "landLine",parser:"number"},
							{key :"cadreLevel"},{key : "email"}
						 ]
				};
				
				var myConfigs = {
					paginator : new YAHOO.widget.Paginator({
						rowsPerPage: 10
					})
				};

				var myDataTable = new YAHOO.widget.DataTable("cadresDetailsDiv",myColumnDefs, myDataSource,myConfigs); 			
		
				

				panel = new YAHOO.widget.Panel("cadreDiv", {x:300,y:500,visible:true, draggable:true, close:true } ); 
				panel.render(); 
								
			//YAHOO.util.Event.addListener("hide1", "click", YAHOO.example.container.panel1.hide, YAHOO.example.container.panel1, true);
				
			}

			function buildTextNode(results,node)
			{
				
				for (var i in results.cadreRegionInfo)
				{ 
					var type = results.region;
					if(results.region=="MLA" || results.region=="MP"||results.region=="DISTRICT"){
						type="Mandal";
					}
					if(results.region=="MANDAL" ){
						type="Village";
					}
					if(results.region=="V" ){
						type="Hamlet";
					}
					var myobj = { 									
									label				: results.cadreRegionInfo[i].regionName+"-"+type+"-"+results.cadreRegionInfo[i].cadreCount,
									labelAccessValue	: results.cadreRegionInfo[i].region,
									id					: results.cadreRegionInfo[i].regionId ,
									type				: results.cadreType
								} ;
				
					var tempNode = new YAHOO.widget.TextNode(myobj, node, false); 
					
				}				
			}

	--></script>
</head>
<body>
	<s:form name="cadrereport" action="cadreRegisterPageAction" method="post">	
	<h3>Cadre Details Page.</h3>
	<table>
		<tr>
			<td>
	<div id="cReportMain" class="yui-skin-sam" style="text-align: left; font-size: 12px; font-family: Verdana;">
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
</body>
</html>