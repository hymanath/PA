<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Report</title>
	
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
	
	<!-- JQuery files (Start) -->
	<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
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

	<!-- JQuery files (End) -->

	<script type="text/javascript" src="js/cadreReport/cadreReport.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/cadreReport/cadreReport.css"></link>
	

	<script type="text/javascript">
			
		var regionLevelZeroCadres = new Array();
		var regionLevelCadres = new Array();
		var cadreDetailsArr = new Array();
		//var cadresArray = new Array();
		var panel;
		var cadreView;
		var cadreCreate;
		var cadreUpdate;
		var cadreDelete;
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

			function callAjax(jsObj,url)
			{					
				var callback = {			
							   success : function( o ) {
									try {
											myResults = YAHOO.lang.JSON.parse(o.responseText);																	
											
											if(jsObj.nodeType == "ZeroLevelCadre")	
											{
												buildZeroCadreTable(myResults, myResults.zeroCadresRegion1);
											}
											else if(jsObj.nodeType == "RegionLevelCadre")
											{
												buildCadreLevelTable(jsObj,myResults.cadreInfo);
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

			function showCadreDetails(id,val,type)
			{
				
				var jsObj = {
								nodevalue:id,
								nodeLabel:val,
								nodeType:type
							};
				var cadreUrl = "<%=request.getContextPath()%>/cadreDetailsByRegionAction.action?cadreRegion="+val+"&cadreId="+id+"&cadreType="+type; 
				

				callAjax(jsObj,cadreUrl);
			}
			
			function loadNodeData(node, fnLoadComplete)
			{				

				var nodeLabel = node.label;
				var index=nodeLabel.indexOf("-");
				var subString = nodeLabel.substring(0,index);				
				
				var jsObj = {
								nodeLabel:node.label,
								task:"totalcadres"
							};

				var cadreUrl = "<%=request.getContextPath()%>/cadreInfoAjaxAction.action?cadreRegion="+node.data.labelAccessValue+"&cadreId="+node.data.id+"&cadreType="+node.data.type; 
		 		

				var callback = {			
 		               success : function( o )
									{
										try 
										{
											myResults = YAHOO.lang.JSON.parse(o.responseText);						
											
											if(myResults.cadreInfo[0])											
												buildHtmlNode(myResults,node);																					
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
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         },

						 argument: { 
								"node": node, 
								"fnLoadComplete": fnLoadComplete 
						} 
 		               };

 				YAHOO.util.Connect.asyncRequest('GET', cadreUrl, callback);
			}

	function showDetails(id, region, nodeLabel)
	{
		var jsObj = {
				
			cadreId: id,
			cadreRegion: region,
			cadreType: 'TotalCadre',
			nodeLabel: nodeLabel
		}	

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "boothwiseCadreInfoAjaxAction.action?"+rparam;						
	callAjaxForShowDetails(jsObj,url);
	}

	function callAjaxForShowDetails(jsObj,url)
	{
		var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							if(jsObj.cadreRegion == "Not Assigned To Any Booth")	
							{
								showNotAssignedBoothCadreInWardable(myResults, jsObj);
								
							}
							else if(jsObj.cadreRegion == "CADRES BY BOOTHS IN WARD")
							{
								showAssignedBoothCadreInWardable(myResults, jsObj);
							}
						}
						catch(e)
						{   
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
	
	function showAssignedBoothCadreInWardable(myResults, jsObj)
	{
		var count = myResults.cadreInfo.length;
		var title = count+" Cadres having booth Details in "+jsObj.nodeLabel;
		var cadreData = myResults.cadreInfo;
		var cadresArray = new Array();
		var update = "No Access";
		var remove = "No Access";
		for(var i in cadreData)
		{
			if(cadreUpdate)			
				update = '<A href="javascript:{}" onclick="openRegistrationForm('+cadreData[i].cadreID+')"><img src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A>';
			if(cadreDelete)
				remove = '<A href="javascript:{}" onclick="deleteCadre('+cadreData[i].cadreID+')"><img src="images/icons/delete.png" style="text-decoration:none;border:0px;"></A>';

			var cObj={
						name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
						mobile:cadreData[i].mobile,
						cadreLevel: cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
						address: cadreData[i].villageName+', '+cadreData[i].mandalName+', '+cadreData[i].districtName,
						memberType: cadreData[i].memberType,												
						caste:cadreData[i].casteCategoryStr,
						moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+cadreData[i].cadreID+'\')">More Details</a>',
						update:update,
						remove:remove
					 };

			cadresArray.push(cObj);
		}			
	

		var myColumnDefs = [ 	           
								{key:"name",label : "Name",sortable:true}, 
								{key:"mobile",label : "Mobile", sortable:true}, 
								{key:"cadreLevel",label : "Cadre Level", sortable:true}, 
								{key:"address",label : "Address", sortable:true},
								{key:"memberType",label : "Cadre Type", sortable:true},								
								{key:"caste",label : "Caste", sortable:true},								
								{key:"moreDetails",label : "More Details"},
								{key:"update",label : "Edit"},
								{key:"remove",label : "Remove"}
								
							]; 
		var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		myDataSource.responseSchema = { 
		fields : [
					{key : "name"}, {key : "mobile",parser:"number"},{key : "address"}, 
					{key :"cadreLevel"},{key : "memberType"},{key :"caste"},{key : "update"},{key:"remove"},{key:"moreDetails"} 
				 ]
		};
		
		var configs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 10
			})
		};

		getCadrePopup(myColumnDefs,myDataSource,configs, title);
	}

	function showNotAssignedBoothCadreInWardable(myResults, jsObj)
	{
		var count = myResults.cadreInfo.length;
		var title = count+" Cadres not having booth Details in "+jsObj.nodeLabel ;
		var cadreData = myResults.cadreInfo;
		var cadresArray = new Array();
		var update = "No Access";
		var remove = "No Access";
		
		for(var i in cadreData)
		{ 
			if(cadreUpdate)			
				update = '<A href="javascript:{}" onclick="openRegistrationForm('+cadreData[i].cadreID+')"><img src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A>';
			if(cadreDelete)
				remove = '<A href="javascript:{}" onclick="deleteCadre('+cadreData[i].cadreID+')"><img src="images/icons/delete.png" style="text-decoration:none;border:0px;"></A>';

			var cObj={
						name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
						mobile:cadreData[i].mobile,
						cadreLevel: cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
						address: cadreData[i].villageName+', '+cadreData[i].mandalName+', '+cadreData[i].districtName,
						memberType: cadreData[i].memberType,						
						caste:cadreData[i].casteCategoryStr,
						moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+cadreData[i].cadreID+'\')">More Details</a>',
						update:update,
						remove:remove
					 };

			cadresArray.push(cObj);
		}			
	

		var myColumnDefs = [ 	           
								{key:"name",label : "Name",sortable:true}, 
								{key:"mobile",label : "Mobile", sortable:true}, 
								{key:"cadreLevel",label : "Cadre Level", sortable:true}, 
								{key:"address",label : "Address", sortable:true},
								{key:"memberType",label : "Cadre Type", sortable:true},								
								{key:"caste",label : "Caste", sortable:true}, 
								{key:"moreDetails",label : "More Details"},
								{key:"update",label : "Edit"},
								{key:"remove",label : "Remove"}
								
							]; 
		var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		myDataSource.responseSchema = { 
		fields : [
					{key : "name"},{key : "mobile",parser:"number"},{key : "address"},{key :"cadreLevel"},{key : "memberType"}, 
					{key :"caste"},{key : "update"},{key:"remove"},{key:"moreDetails"} 
				 ]
		};
		
		var configs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 10
			})
		};

		getCadrePopup(myColumnDefs,myDataSource,configs,title);

		
	}	
	function showTree(cType)
	{
		//'${userCadresInfoVO.userAccessType}'
		region = '${userCadresInfoVO.userAccessType}';
		var myLabel;
			if(region=='MLA' || region=='MP')
				region ='CONSTITUENCY';
		if(region == 'VILLAGE COUNT' || region == 'WARD COUNT')
		{			
			myLabel='${userCadresInfoVO.userAccessDisplayValue}-${userCadresInfoVO.totalCadres}';
		} else
		{
			myLabel='${userCadresInfoVO.userAccessDisplayValue}('+ region+')-${userCadresInfoVO.totalCadres}';
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

	
	function buildHtmlNode(results,node)
	{
		var cadreData = results.cadreInfo;
		var cadreRegionInfo = results.cadreRegionInfo; 
		var obj={
					"label":node.label,
					"obj":cadreData
				};
		
		cadreDetailsArr.push(obj);
		var str = '';
		str+='<a href="javascript:{}" id="'+node.label+'" onclick="showLink(this.id)"/>view Details</a>';
		if(cadreRegionInfo != null && cadreRegionInfo.length >0)
		{
			if(cadreRegionInfo[0].cadreCount != 0)
			{
				str+='<div style="margin:5px;"><span id="'+node.label+'" >'+cadreRegionInfo[0].regionName+'('+cadreRegionInfo[0].region+')-'+cadreRegionInfo[0].cadreCount+' </span><a href="javascript:{}" id="anchor1" onclick="showDetails('+cadreRegionInfo[0].regionId+',\''+cadreRegionInfo[0].region+'\', \''+node.label+'\')"/>view Details</a></div>';
			} else {
				str+='<div style="margin:5px;"><span id="'+node.label+'" >'+cadreRegionInfo[0].regionName+'('+cadreRegionInfo[0].region+')-'+cadreRegionInfo[0].cadreCount+' </span></div>';
				}
			if(cadreRegionInfo[1].cadreCount != 0)
			{
				str+='<div style="margin:5px;"><span id="'+node.label+'" >'+cadreRegionInfo[1].regionName+'('+cadreRegionInfo[1].region+')-'+cadreRegionInfo[1].cadreCount+' </span><a href="javascript:{}" id="anchor2" onclick="showDetails('+cadreRegionInfo[1].regionId+',\''+cadreRegionInfo[1].region+'\', \''+node.label+'\')"/>view Details</a></div>';
			} else 
			{
				str+='<div style="margin:5px;"><span id="'+node.label+'" >'+cadreRegionInfo[1].regionName+'('+cadreRegionInfo[1].region+')-'+cadreRegionInfo[1].cadreCount+' </span></div>';
			}			
		}	
			
		var tempNode = new YAHOO.widget.HTMLNode(str, node, false); 
		tempNode.isLeaf = true;
	}

	function showLink(value)
	{			
		var cadreData = new Array();
		var cadresArray = new Array();
		var indexOfHyphen = value.lastIndexOf("-");
		var title = value.substring(indexOfHyphen+1)+" Cadres Available in "+value.substring(0,indexOfHyphen);
		var update = "No Access";
		var remove = "No Access";		
		

		for(var i in cadreDetailsArr)
		{
			if(cadreDetailsArr[i].label == value)
			{
				cadreData = cadreDetailsArr[i].obj;
			}
		}		
		
		for(var i in cadreData)
		{
			if(cadreUpdate)	
				update = '<A href="javascript:{}" onclick="openRegistrationForm('+cadreData[i].cadreID+')"><img src="images/icons/edit.png" style="text-decoration:none;border:0px;"></A>';			
			if(cadreDelete)
				remove = '<A href="javascript:{}" onclick="deleteCadre('+cadreData[i].cadreID+')"><img src="images/icons/delete.png" style="text-decoration:none;border:0px;"></A>';

			var cObj={
						name:cadreData[i].firstName+' '+cadreData[i].middleName+' '+cadreData[i].lastName,
						mobile:cadreData[i].mobile,
						cadreLevel: cadreData[i].strCadreLevel+'-'+cadreData[i].strCadreLevelValue,
						address: cadreData[i].villageName+', '+cadreData[i].mandalName+', '+cadreData[i].districtName,
						memberType: cadreData[i].memberType,
						casteCategory: cadreData[i].casteCategoryStr,
						moreDetails:'<a href="javascript:{}" onclick="getCadreInfo(\''+cadreData[i].cadreID+'\')">More Details</a>',
						update:update,
						remove:remove
					 };

			cadresArray.push(cObj);
		}			
	

		var myColumnDefs = [ 	           
								{key:"name",label : "Name",sortable:true}, 
								{key:"mobile",label : "Mobile"}, 
								{key:"cadreLevel",label : "Cadre Level", sortable:true}, 
								{key:"address",label : "Address", sortable:true},
								{key:"memberType",label : "Cadre Type", sortable:true},
								{key:"casteCategory",label : "Caste Category", sortable:true},
								{key:"moreDetails",label : "More Details"},
								{key:"update",label : "Edit"},
								{key:"remove",label : "Remove"}
								
							]; 
		var myDataSource = new YAHOO.util.LocalDataSource(cadresArray); 		
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		myDataSource.responseSchema = { 
		fields : [
					{key : "name"}, {key : "mobile",parser:"number"}, {key : "address"},{key: "casteCategory"}, 
					{key :"cadreLevel"},{key : "memberType"},{key : "update"},{key:"remove"},{key:"moreDetails"} 
				 ]
		};
		
		var configs = {
			paginator : new YAHOO.widget.Paginator({
				rowsPerPage: 10
			})
		};

		getCadrePopup(myColumnDefs,myDataSource,configs, title);
	}

	function getCadreInfo(cadreId)
	{
		var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
		var browser2 = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=500,width=600,left=200,top=200");	
		browser2.focus();
	}
			
			function deleteCadre(cadreId)
			{
				var ask = confirm("Do You want to delete");
				if (ask ==  true)
				  {
					var jsObj = {
							id: cadreId,
							task: "deleteCadre"
						};
						var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);	
						var url = "<%=request.getContextPath()%>/deleteCadreInfoAjaxAction.action?"+rparam; 
						deleteCadreAjax(rparam,jsObj,url);	  	
				  }
				else
				  {
				  		return;	
				  }
				
			}

			function deleteCadreAjax(param,jsObj,url){
				var myResults;
					
		 		var callback = {			
		 		               success : function( o ) 
								  {
									try {												
											if(o.responseText)
												myResults = YAHOO.lang.JSON.parse(o.responseText);
											showConfirmation(myResults);
									}
									catch (e)
										{   
										   	alert("Invalid JSON result" + e);   
										}	  
						              },
						               scope : this,
						               failure : function( o ) {
						                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
						                         }
						               };

						YAHOO.util.Connect.asyncRequest('GET', url, callback);
				}
					
			function showConfirmation(result)
			{
				if(result != null && result>0)
				{
					alert("Cadre Details Successfully Deleted!");
					//cadreDetailsPanel.hide();
					window.location.reload(true);
				}
					
			}

			function openRegistrationForm(cadreId)
			{
				var task = "update_existing";
				var urlStr = "cadreRegisterPageAction.action?cadreId="+cadreId+"&windowTask="+task;
				var browser2 = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
				browser2.focus();				
			}

			function buildTextNode(results,node)
			{

				for (var i in results.cadreRegionInfo)
				{
					var region = results.cadreRegionInfo[i].region;
					if(region=='V')
						region ='VILLAGE';
					else if(region=='T')
						region ='TOWN';
					var myobj = { 
									label				: results.cadreRegionInfo[i].regionName+"("+region+")-"+results.cadreRegionInfo[i].cadreCount,
									labelAccessValue	: results.cadreRegionInfo[i].region,
									id					: results.cadreRegionInfo[i].regionId ,
									type				: results.cadreType
								} ;
					
					var tempNode = new YAHOO.widget.TextNode(myobj, node, false); 
					
				}				
			}

			function registerInfluencePeople(){
			
			}
			
	</script>
</head>
<body>
	<s:form name="cadrereport" action="cadreRegisterPageAction" method="get" theme="simple">	

	<div id="cadreReportMain">
		<div id="cadreReportLayout_main"></div>
		
		<div  class="yui-skin-sam">
			<div id="cadreDetailsPopup"></div>
		</div>
		
		<!-- Cadre Top Layout Div-->
		<div id="cadreReportLayout_top">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><img border="none" src="images/icons/statePage/header_left.png"></td>
					<td><div id="CadreReportHeading"> Cadre Details</div></td>
					<td><img border="none" src="images/icons/statePage/header_right.png"></td>
				</tr>
			</table>
		</div>
		
		<!-- Cadre Top Layout Div-->
		<div id="cadreReportLayout_right">
			<div id="cadreRegistration">
				<div id="cadreRegistration_head">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="width:100%">
						<tr>
							<td><img border="none" src="images/icons/cadreReport/addCadre.png"></td>
							<td><div class="registerCadreClass" style="width:200px;"> <u> Register Cadre </u></div></td>						
						</tr>
					</table>
				</div>				
				<div id="cadreRegistration_body">
					<div>Add/Register your party cadre to your cadre list in different locations based on their availability.</div>
					<div style="text-align:right;padding-top:10px;">
					<input type="hidden" id="hiddenVal" name="cadreId" value="0"/>
					<input type="hidden" id="hiddenValue" name="windowTask" value="new"/>
					<c:if test="${userCadresInfoVO.cadreCreate}">
					<input type="submit" id="registerCadreSubmit" name="registersubmit" value="Register Cadre"/>
					</c:if>
				</div>					
				</div>
			</div>	
			
			<!--<div id="influencePeopleRegistration">
				<div id="influencePeopleRegistration_head">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" style="width:100%">
						<tr>
							<td><img border="none" src="images/icons/cadreReport/addCadre.png"></td>
							<td><div class="registerinfluencePeopleClass" style="width:200px;"> <u> Register Most Influence People </u></div></td>						
						</tr>
					</table>
				</div>				
				
				<div id="influencePeopleRegistration_body">
					<div>Add/Register most influence people you know.</div>
					<div style="text-align:right;padding-top:10px;">
					<a href="influencingPeopleAction.action">
						<input type="button" id="influencePeopleCadreSubmit" name="influencePeopleRegistersubmit" onclick="registerInfluencePeople()" value="Register Influence People"/></div>
					</a>					
				</div>	
				
			--></div>
		</div>

		<!-- Cadre Top Layout Div-->
		<div id="cadreReportLayout_center">				
			<div id="cReportMain" class="yui-skin-sam">
				<div id="cadreInfoDiv" style="margin-bottom: 20px;">
					<div id="cadreInfoDivHead">									
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img border="none" src="images/icons/cadreReport/bg_left.png"></td>
								<td><div class="cadreReportHeader"> <span class="reportHeaderSpan"> Cadre Availability </span></div></td>
								<td><img border="none" src="images/icons/cadreReport/bg_right.png"></td>
							</tr>
						</table>					
					</div>
					<div id="cadreInfoDivBody" style="padding-left: 50px;padding-top: 10px">
						
					</div>
				</div>

				<div id="zeroCadreInfoDiv" style="margin-bottom: 20px;">
					
						<div id="zeroCadreInfoDivHead">			
							<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img border="none" src="images/icons/cadreReport/bg_left.png"></td>
									<td><div class="cadreReportHeader"><span class="reportHeaderSpan"> Non Available Region Cadres</span></div></td>
									<td><img border="none" src="images/icons/cadreReport/bg_right.png"></td>
								</tr>
							</table>							
						</div>
					
					<div id="zeroCadreInfoDivBody" style="padding-left: 50px;padding-top: 10px">				
						
					</div>
				</div>

				<div id="cadreLevelInfoDiv" style="margin-bottom: 20px;">
					<div id="cadreLevelInfoDivHead">
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img border="none" src="images/icons/cadreReport/bg_left.png"></td>
								<td><div class="cadreReportHeader"> <span class="reportHeaderSpan">Cadre Details By Cadre Level </span></div></td>
								<td><img border="none" src="images/icons/cadreReport/bg_right.png"></td>
							</tr>
						</table>						
					</div>
					<div id="cadreLevelInfoDivBody" style="padding-left: 50px;padding-top: 10px"">
						
					</div>
				</div>					
			</div>				
		</div>

		<script type="text/javascript">

			initializeCadreReport();
			showTree("TotalCadre");

			<c:forEach var="pd" items="${userCadresInfoVO.regionLevelZeroCadres}" >
				buildJSObject('${pd.id}','${pd.name}',"regionLevelZeroCadres");
			</c:forEach>

			<c:forEach var="pd1" items="${userCadresInfoVO.regionLevelCadres}" >
				buildJSObject('${pd1.value}','${pd1.key}','regionLevelCadres');
			</c:forEach>
			
			cadreView = ${userCadresInfoVO.cadreView};
			cadreCreate = ${userCadresInfoVO.cadreCreate};
			cadreUpdate = ${userCadresInfoVO.cadreUpdate};
			cadreDelete = ${userCadresInfoVO.cadreDelete};		
		
			buildTreeView();	
		</script>	
	</s:form>	
</body>
</html>