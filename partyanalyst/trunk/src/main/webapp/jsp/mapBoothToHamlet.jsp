<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Sam Skin CSS -->
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css">
 
<!-- Dependencies -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
 
<!-- OPTIONAL: Animation (only required if using ContainerEffect) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/animation/animation-min.js"></script>
 
<!-- OPTIONAL: Drag & Drop (only required if enabling Drag & Drop) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script>
 
<!-- Source file -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script>
 
<script type="text/javascript">

		var mandalId;
		var boothIds = new Array();
		var locationName;
		var locationId;
        var myPanel;
		
		function getDistrictsList(id)
		{
			var jsObj=
				{
						location:"STATE",
						locationId:id,
						task:"getDistricts"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/hamletBoothMapperAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getMandalsList(id)
		{
			
			var jsObj=
				{
						location:"DISTRICT",
						locationId:id,
						task:"getMandals"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/hamletBoothMapperMandalAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}

		function getRevenueVillagesAndBoothsList(id)
		{
			mandalId = id;
			var jsObj=
				{
						location:"REVENUE VILLAGES",
						locationId:id,
						task:"getRevenueVillagesAndBooths"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/villageBoothMapperFinalAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}
		
		function callAjax(rparam, jsObj, url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {								
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										
									
									if(jsObj.task == "getDistricts")
									{								
										showDistricts(resultVO);				
									}
									else if(jsObj.task == "getMandals")
									{										
										showMandals(resultVO);				
									}
									else if(jsObj.task == "getRevenueVillagesAndBooths")
									{	
										showBoothsAndVillages(resultVO);				
									}
									else if(jsObj.task == "storeHamletBoothInfo"){
										updateAndGetBoothHamletRecords(resultVO);
									}									
							}catch (e)  {   
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

	  function showDistricts(resultVO){
		
		var selectContentEl = document.getElementById("districtSelect");
		var selectLableEl = document.getElementById("districtLable");
		var strContent = '';
		strContent+='<select onchange="getMandalsList(this.options[this.selectedIndex].value)" class = "selectWidth">';
		for(var i in resultVO)
		{			
			strContent+='<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
		
		strContent+='</select>';

		var strLable = 'District:';

		selectLableEl.innerHTML = strLable;
		selectContentEl.innerHTML = strContent;
	  }

	  function showMandals(resultVO){
		var selectContentEl = document.getElementById("mandalSelect");
		var selectLableEl = document.getElementById("mandalLable");
		var strContent = '';
		strContent+='<select onchange="getRevenueVillagesAndBoothsList(this.options[this.selectedIndex].value)" class = "selectWidth">';
		for(var i in resultVO)
		{
			strContent+='<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
		strContent+='</select>';

		var strLable = 'Mandal:';

		selectLableEl.innerHTML = strLable;
		selectContentEl.innerHTML = strContent;
		
	  }

	  function showBoothsAndVillages(resultVO){

			var selectHamletEl = document.getElementById("hamletsList");
			var selectBoothEl = document.getElementById("boothsList");
			var selectButtonEl = document.getElementById("hamletButtonDiv");
			selectBoothEl.style.overflow='auto';
			selectBoothEl.style.height = '120px';
			selectBoothEl.style.width = '500px';
			
			var strHamlet = '';
			var strBoothInfo = '';
			
			strHamlet += '<select id="hamlet" class = "selectWidth">';
			for(var i in resultVO.hamlets)
			{
				strHamlet+='<option value='+resultVO.hamlets[i].id+'>'+resultVO.hamlets[i].name+'</option>';
			}
			strHamlet += '</select>';

			strBoothInfo='';
			strBoothInfo+='<table>';
			strBoothInfo+='<tr>';
			strBoothInfo+='<th>Select</th>';
			strBoothInfo+='<th>Booth No</th>';
			strBoothInfo+='<th>Villages Covered</th>';
			strBoothInfo+='<th>Constituency Name</th>';
			strBoothInfo+='</tr>';				
			for(var i in resultVO.constituenciesAndBooths)
			{	
				strBoothInfo+='<tr id = "'+resultVO.constituenciesAndBooths[i].boothConstiElecId+'_row">';			
				strBoothInfo+= '<td align="center"><span><input type="checkbox" id="'+resultVO.constituenciesAndBooths[i].boothConstiElecId+'_input" onclick ="getBoothIdsIntoArray(this.id)"></span></td></td>';
				strBoothInfo+= '<td align="center"><span id="'+resultVO.constituenciesAndBooths[i].boothConstiElecId+'_partNo">'+resultVO.constituenciesAndBooths[i].partNo+'</span></td>';
				strBoothInfo+= '<td align="center"><span id="'+resultVO.constituenciesAndBooths[i].boothConstiElecId+'_villagesCovered">'+resultVO.constituenciesAndBooths[i].villagesCovered+'</span></td>';
				strBoothInfo+= '<td align="center"><span id="'+resultVO.constituenciesAndBooths[i].boothConstiElecId+'_constiName">'+resultVO.constituenciesAndBooths[i].constituencyName+'</span><br></td>';
				strBoothInfo+='</tr>';
			}			
			strBoothInfo+='</table>';
								
			var strButton = '';
			strButton += '<input type="button" value="Attach" onclick="showHamletBoothMappings()" align = "middle"/>';

			selectHamletEl.innerHTML = strHamlet;
			selectBoothEl.innerHTML = strBoothInfo;
			selectButtonEl.innerHTML = strButton;
	  }

	  function getBoothIdsIntoArray(id){
		 boothIds.push(id.substring(0,id.indexOf('_')));
	  }
  
	  function showHamletBoothMappings(){
		  			
		var hamletSelectEl = document.getElementById("hamlet");
		var errorDivEl = document.getElementById("errorMessageDiv");
		locationName = hamletSelectEl.options[hamletSelectEl.selectedIndex].text;
		locationId = hamletSelectEl.options[hamletSelectEl.selectedIndex].value;

		if(locationId == 0)
		{
			errorDivEl.innerHTML = '<span style="color:RED"><b>Please Select Hamlet</b></span>' ;
			return;
		}else{
			errorDivEl.innerHTML = '';
		}
		

		
		var str = '';
		str += '<table>';
		str += '<tr><th>Village</th>';
		str += '<th>Booth No</th>';
		str += '<th>Locations Covered</th></tr>';	
		for(var i in boothIds){
			str += '<tr>';
			str += '<td>'+locationName+'</td>';
			str += '<td>'+document.getElementById(boothIds[i]+"_partNo").innerHTML+'</td>';
			str += '<td>'+document.getElementById(boothIds[i]+"_villagesCovered").innerHTML+'</td>';
			str += '</tr>';
		}
		str += '<tr>';
		str += '<td align="center"><input type="button" value="Confirm" onclick="sendDataToAction()" align = "middle"/></td>';
		str += '<td><input type="button" value="Cancel" onclick="hidePanel()" align = "middle"/></td>';
		str += '</tr>';
		myPanel = new YAHOO.widget.Panel("boothHamletMapPanel", {
	            width: "450px", 
	            fixedcenter: true, 
	            constraintoviewport: false, 
	            underlay: "none", 
	            close: false, 
	            visible: true, 
	            draggable: true
	    });
	    
	    myPanel.setHeader("Mapped Booth Information");
	    myPanel.setBody(str);
		myPanel.render();
		    
	  }
	  function hidePanel(){
		  myPanel.hide();
	  }

	  function sendDataToAction(){
		  for(var i in boothIds){
			 	var deleteElmt = document.getElementById(boothIds[i]+'_row');
			    deleteElmt.style.display = 'none';
			}
		  myPanel.hide();
		  var jsObj=
			{
				  	townshipId:locationId,
				 	townshipName:locationName,
					boothIds:boothIds,
					task:"storeHamletBoothInfo"
			};
		  boothIds = new Array();
		  var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);	
		  var url = "<%=request.getContextPath()%>/hamletBoothMapperDataStoreAjaxAction.action?"+rparam; 			
		  callAjax(rparam, jsObj, url);
	  }

	  function updateAndGetBoothHamletRecords(resultVO){
		  var hamletSelectEl = document.getElementById("popupPanel");
		  var strBoothContent = '';
		  strBoothContent += '<div id="mainDeleteDiv">';
		  for(var i in resultVO){				  
			  strBoothContent += '<div id="'+resultVO[i].villageBoothElectionId+'_div">';
			  strBoothContent += '<span class="confirmSpan">'+resultVO[i].villageName+'</span>';
			  strBoothContent += '<span class="confirmSpan">'+resultVO[i].partNo+'</span>';
			  strBoothContent += '<span class="confirmSpan">'+resultVO[i].villagesCovered+'</span>';
			  strBoothContent += '<span class="confirmSpan">'+resultVO[i].constituencyName+'</span>';
			  strBoothContent += '<span class="confirmSpan"><a href="#" id="'+resultVO[i].villageBoothElectionId+'" onclick = "deleteRecord(this.id)">UnDo</a></span>';
			  strBoothContent += '</div>';
		  }
		  strBoothContent += '</div>';
		  hamletSelectEl.innerHTML = strBoothContent;
		  
	  }

	  function deleteRecord(id){
		
		 var deleteElmt = document.getElementById(id+'_div');
		 deleteElmt.style.display = 'none';
		 var jsObj=
			{
				  	villageBoothId:id,
				 	task:"deleteVillageBoothInfo"
			};
		  boothIds = new Array();
		  var rparam = "task="+YAHOO.lang.JSON.stringify(jsObj);	
		  var url = "<%=request.getContextPath()%>/hamletBoothMapperDeleteRecordAjaxAction.action?"+rparam; 			
		  callAjax(rparam, jsObj, url);
	  }

	  function deletedRecordInfo(resultVO){
		  alert("After Delete ::"+resultVO);
	  }

	  
</script>
<style type="text/css">

	.selectWidth
	{
		width:160px;
	}
	.confirmSpan
	{
		padding:2px;
		margin-right:5px;
	}
	
</style>
</head>
<body>	
	<table>
		<tr>
			<td colspan="2" align="left"><div id="errorMessageDiv"></div></td>
			
		</tr>
		<tr>
			<td align = "right">State:</td>
			<td><s:select id="stateId" theme="simple" cssClass="selectWidth" list="result" listKey="id" listValue="name" onchange="getDistrictsList(this.value)" /></td>
		</tr>
		<tr>
			<td align = "right"><div id="districtLable" /></td>
			<td><div id="districtSelect" /></td>
		</tr>
		<tr>
			<td align = "right"><div id="mandalLable" /></td>
			<td><div id="mandalSelect" /></td>
		</tr>
		<tr>
			<td><div id="hamletsList" /></td>
			<td><div id="boothsList" /></td>
		</tr>	
		<tr>
			<td colspan="2" align="center"><div id="hamletButtonDiv" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><div class="yui-skin-sam"><div id="boothHamletMapPanel"/></div></td>
		</tr>		
		
	</table>
	<div id="popupPanel">
		<table>
			<tr>
				<td></td>
			</tr>			
		</table>
	</div>		
</body>
</html>