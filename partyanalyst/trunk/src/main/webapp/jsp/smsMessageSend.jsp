<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send SMS Message to Cadres</title>

<script type="text/javascript" src="js/json/json-min.js"></script> 
   <script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>

<script type="text/javascript">
	function callAjax(jsObj,url){		
					
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "getUserLocation")
									fillDataOptions(myResults);	
								else if(jsObj.task == "DISTRICT")
									fillData(myResults,"DistrictSelect");
								else if(jsObj.task == "CONSTITUENCY")
									fillData(myResults,"ConstituencySelect");
								else if(jsObj.task == "MANDAL")
									fillData(myResults,"MandalSelect");
								else if(jsObj.task == "VILLAGE")
									fillData(myResults,"VillageSelect");	

							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}


	function fillDataOptions(results, id)
	{	
		var element = document.getElementById(id);
		
	}
	function getUserLocationData(val)
	{
		var jsObj={
					value:val,
					task:"getUserLocation"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/cadreSMSLocationWiseData.action?";
		callAjax(jsObj,url);
	}
	function getDistricts(val)
	{
		var jsObj={
					value:val,
					task:"DISTRICT"
				  };	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?"+rparam;
		callAjax(jsObj,url);
		
	}
	function getConstituencies(val)
	{
		var jsObj={
					value:val,
					task:"CONSTITUENCY"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?"+rparam;
		callAjax(jsObj,url);
	}
	function getMandals(val)
	{
		var jsObj={
					value:val,
					task:"MANDAL"
				  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?"+rparam;
		callAjax(jsObj,url);
	}
	function getVillages(val)
	{
		var jsObj={
					value:val,
					task:"VILLAGE"
				  };
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?"+rparam;
		callAjax(jsObj,url);
	}

	function getUsersGroupData(){
		
	}
	function getUsersCadreLevelData(){
		
	}
	function fillDataOptions(results)
	{	
		console.log(results);
		
		//Setting values for region type..
		var regionTypeElmtLabel = document.getElementById("region_type_Label");
		var regionTypeElmtData = document.getElementById("region_type_Data");

		var str='';
		for(var i in results.regions)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value)" /> '+results.regions[i].name+'';
		}		

		if(regionTypeElmtLabel)
			regionTypeElmtLabel.innerHTML="Select Location";
		if(regionTypeElmtData)
			regionTypeElmtData.innerHTML=str;

		//---------
		//Setting values for select box..
		var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
		var regionTypeSelectElmtData = document.getElementById("region_select_Data");

		if(regionTypeSelectElmtLabel)
			regionTypeSelectElmtLabel.innerHTML="Select Data";

		var regionStr='';
		
		regionStr+='<select id="StateSelect" class="selectBox" onchange="getDistricts(this.value)" disabled="true">';
		if(results.states != "")
		{
			for(var state in results.states)
			{
				regionStr+='<option value="'+results.states[state].id+'">'+results.states[state].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select State</option>';
		}
		regionStr+='</select>';	

		
		regionStr+='<select id="DistrictSelect" class="selectBox" onchange="getConstituencies(this.value)" disabled="true">';
		if(results.districts != "")
		{
			for(var district in results.districts)
			{
				regionStr+='<option value="'+results.districts[district].id+'">'+results.districts[district].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select District</option>';
		}
		regionStr+='</select>';
		
		
		regionStr+='<select id="ConstituencySelect" class="selectBox" onchange="getMandals(this.value)" disabled="true">';
		if(results.constituencies != "")
		{
			for(var consti in results.constituencies)
			{
				regionStr+='<option value="'+results.constituencies[consti].id+'">'+results.constituencies[consti].name+'</option>';
			}
		}
		else
		{
			regionStr+='<option value="0"> Select Constituency</option>';
		}
		regionStr+='</select>';
	
	
	
		regionStr+='<select id="MandalSelect" class="selectBox" onchange="getVillages(this.value)" disabled="true">';
		if(results.mandals != "")
		{
			for(var mandal in results.mandals)
			{
				regionStr+='<option value="'+results.mandals[mandal].id+'">'+results.mandals[mandal].name+'</option>';
			}
		}
		else
		{	
			regionStr+='<option value="0"> Select Mandal</option>';
		}
		regionStr+='</select>';

		
		regionStr+='<select id="VillageSelect" class="selectBox" disabled="true">';
		if(results.villages != "")
		{
			for(var village in results.villages)
			{
				regionStr+='<option value="'+results.villages[village].id+'">'+results.villages[village].name+'</option>';
			}
		}
		else
		{	
			regionStr+='<option value="0"> Select Village</option>';
		}
		regionStr+='</select>';
	
		if(regionTypeSelectElmtData)
			regionTypeSelectElmtData.innerHTML=regionStr;

		//---------------
		// Displaying Text Area
		var smsTextElmtLabel = document.getElementById("sms_text_Label");
		var smsTextElmtData = document.getElementById("sms_text_Data");
		
		if(smsTextElmtLabel)
			smsTextElmtLabel.innerHTML="SMS Text";

		var smsStr='';
		smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="" ></textarea></div> ';
		smsStr+='<div id="limitDiv">Should not exceed 200 chars</div>';		
		
		if(smsTextElmtData)
			smsTextElmtData.innerHTML=smsStr;

		/*var smsStr1='<div style="text-align:right;"> You have <input type="text" id="sizeInput" size="3" value="200"/> chars left</div>';
		if(smsTextElmtData)
			smsTextElmtData.innerHTML+=smsStr1;*/

		//--------------------------
		//Displaying button

		var buttonDiv =  document.getElementById("button_div");
		var bstr='';
		bstr+='<input type="button" value="Send SMS" onclick="sendSMS()"';
		
		if(buttonDiv)
			buttonDiv.innerHTML=bstr;
	
	}

	function sendSMS()
	{
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true )
				val = document.smsForm.region_type_radio[i].value;
		}
		
		var valSelect = document.getElementById(val+"Select");
		var textAreaElmt = document.getElementById("smsTextArea");

		textAreaElmtValue = textAreaElmt.value
		var valSelectValue = valSelect.options[valSelect.selectedIndex].value;
		val=val.toUpperCase();
		var jsObj={
					SMS_LEVEL_TYPE:val,
					SMS_LEVEL_VALUE:valSelectValue,
					SMS_MESSAGE:textAreaElmtValue,
					task:"sendSMS"
				  };
		console.log(jsObj);
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
		
	}
	
	function limitText(limitField, limitCount, limitNum)
	{
		if (limitField.value.length > limitNum) 
		{
			limitField.value = limitField.value.substring(0, limitNum);
		}
		else
		{
			limitCount.value = limitNum - limitField.value.length;
		}
	}

	function displayRegionsSelect(val)
	{
		var stateSelectElmt = document.getElementById("StateSelect");
		var districtSelectElmt = document.getElementById("DistrictSelect");
		var constituencySelectElmt = document.getElementById("ConstituencySelect");
		var mandalSelectElmt = document.getElementById("MandalSelect");
		var villageSelectElmt = document.getElementById("VillageSelect");
		
		stateSelectElmt.disabled=true;
		districtSelectElmt.disabled=true;	
		constituencySelectElmt.disabled=true;	
		mandalSelectElmt.disabled=true;	
		villageSelectElmt.disabled=true;	

		if(val == "District")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;			
		}
		if(val == "Constituency")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;	
			constituencySelectElmt.disabled=false;	
		}
		if(val == "Mandal")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;
			constituencySelectElmt.disabled=false;	
			mandalSelectElmt.disabled=false;	
		}
		if(val == "Village")
		{
			stateSelectElmt.disabled=false;
			districtSelectElmt.disabled=false;	
			constituencySelectElmt.disabled=false;	
			mandalSelectElmt.disabled=false;	
			villageSelectElmt.disabled=false;	
		}		
	}
</script>

<style type="text/css">
.selectBox
{
	padding:2px;
	margin-right:10px;
	font-size:12px;
}
#limitDiv
{
	color:#AAAAAA;
	font-size:11px;
	margin-left:180px;
	padding:5px;	
}
</style>
</head>
	<body>
	  <s:form action="cadreRegisterAction" method="POST" theme="simple" name="smsForm">
		<h2>Cadre SMS Message</h2>
		<table>
			<tr>
				<th align="left">SMS Type</th>
				<td align="left">
					<input type="radio" name="sms_type" value="locationWise" onclick="getUserLocationData(this.value)"/> Location Wise
					<!-- <td><input type="radio" name="sms_type" onselect="getUsersGroupData()"/> Group Wise</td>-->
					<input type="radio" name="sms_type" value="cadreLevelWise" onclick="getUsersCadreLevelData(this.value)"/> Cadre Level Wise
				</td>		
			</tr>
			<tr>
				<th align="left"><div id="region_type_Label"></div></th>
				<td align="left"><div id="region_type_Data"></div></td>				
			</tr>
			<tr>
				<th align="left">
					<div id="region_select_Label">
					</div>
				</th>
				<td align="left">
					<div id="region_select_Data">
					</div>					
			    </td>
			</tr>
			<tr>
				<th align="left"><div id="sms_text_Label"></div></th>
				<td align="left"><div id="sms_text_Data"></div></td>				
			</tr>
			<tr>
				<td align="center" colspan="2"><div id="button_div"></div></td>				
			</tr>
		</table>
	  </s:form>
	</body>
</html>
