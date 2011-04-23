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
								else if(jsObj.task == "fillSelectElements")
									fillSelectElement(myResults,jsObj);
								else if(jsObj.task == "sendSMS")
									displaySuccessMessage(myResults,jsObj);
								else if(jsObj.task=="CADRE_LEVEL")
									fillDataForCadreLevel(myResults);
									
							}catch (e) {   
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

	function displaySuccessMessage(results,jsObj)
	{
		var divElmt = document.getElementById("successDiv");

		var str="SMS sent successfully to "+results+" cadres";
		divElmt.innerHTML=str;
	}
	function fillSelectElement(results,jsObj)
	{
		if(jsObj.type == "STATE")
		{
			var elmt = document.getElementById("DistrictSelect");
		}
		else if(jsObj.type == "DISTRICT	")
		{
			var elmt = document.getElementById("ConstituencySelect");
		}
		else if(jsObj.type == "CONSTITUENCY")
		{	
			var elmt = document.getElementById("MandalSelect");
		}	
		else if(jsObj.type == "MANDAL")
		{
			var elmt = document.getElementById("VillageSelect");
		}

		var len=elmt.length;			
		for(i=len-1;i>=0;i--)		
			elmt.remove(i);
		
		var x=document.createElement('option');		
		x.value="0";		
		x.text="Select";		

		try
		{
			elmt.add(x,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(x); // IE only
		}
		
		for (var l in results)
		{
			var y=document.createElement('option');
			y.value=results[l].id;
			y.text=results[l].name;
			
			try
			{
				elmt.add(y,null); // standards compliant
			}
			catch(ex)
			{
				elmt.add(y); // IE only
			}
		}
		
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


	function getUsersCadreLevelData(){		
		var jsObj={
				task:"CADRE_LEVEL"
			  };
		var url = "<%=request.getContextPath()%>/usersCadreLevelData.action";
		callAjax(jsObj,url);
	}
	
	function getNextRegions(id,val)
	{
		var selectElmt = document.getElementById(id);
		var selectValue = selectElmt.options[selectElmt.selectedIndex].value;
		
		if(selectValue=="0")
			return;

		var jsObj={
					value:selectValue,
					type:val,
					task:"fillSelectElements"
				  };
		var url = "<%=request.getContextPath()%>/regionsByCadreScope.action?REGION="+val+"&REGION_ID="+selectValue;
		callAjax(jsObj,url);

	}

	function fillDataForCadreLevel(results)
	{
		var successDivElmt=	 document.getElementById("successDiv");
		successDivElmt.innerHTML="";

		var regionTypeElmtLabel = document.getElementById("region_type_Label");
		var regionTypeElmtData = document.getElementById("region_type_Data");

		regionTypeElmtLabel.innerHTML="Select Cadre Level";

		var str='';
		for(var i in results)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results[i].id+'"> '+results[i].name+' ';
		}
		regionTypeElmtData.innerHTML=str;
		//--------------
		var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
		var regionTypeSelectElmtData = document.getElementById("region_select_Data");

		if(regionTypeSelectElmtLabel && regionTypeSelectElmtData)
		{
			regionTypeSelectElmtLabel.innerHTML="";
			regionTypeSelectElmtData.innerHTML="";
		}
		//-------------
		var smsTextElmtLabel = document.getElementById("sms_text_Label");
		var smsTextElmtData = document.getElementById("sms_text_Data");
		
		if(smsTextElmtLabel)
			smsTextElmtLabel.innerHTML="SMS Text";
		
		var smsStr='';
		smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
		smsStr+='<div id="limitDiv">';
		smsStr+='<table style="width:100%;"><tr>';
		smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
		smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
		smsStr+='</tr></table>';
		smsStr+='</div>';	
			
		
		if(smsTextElmtData)
			smsTextElmtData.innerHTML=smsStr;


		var buttonDiv =  document.getElementById("button_div");
		var bstr='';
		bstr+='<input type="button" value="Send SMS" onclick="sendSMSCadreLevel()"/>';
		
		if(buttonDiv)
			buttonDiv.innerHTML=bstr;
		
	}
	function fillDataOptions(results)
	{	
		
		
		//Setting values for region type..
		var regionTypeElmtLabel = document.getElementById("region_type_Label");
		var regionTypeElmtData = document.getElementById("region_type_Data");

		var str='';
		for(var i in results.regions)
		{
			str+='<input type="radio" name="region_type_radio" value="'+results.regions[i].name+'" onclick="displayRegionsSelect(this.value)" /> '+results.regions[i].name+'';
		}		

		if(regionTypeElmtLabel)
			regionTypeElmtLabel.innerHTML="Select Level";
		if(regionTypeElmtData)
			regionTypeElmtData.innerHTML=str;

		//---------
		//Setting values for select box..
		var regionTypeSelectElmtLabel = document.getElementById("region_select_Label");
		var regionTypeSelectElmtData = document.getElementById("region_select_Data");

		if(regionTypeSelectElmtLabel)
			regionTypeSelectElmtLabel.innerHTML="Select Location";

		var regionStr='';
		
		regionStr+='<select id="StateSelect" class="selectBox" onchange="getNextRegions(this.id,\'STATE\')" disabled="true">';
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

		
		regionStr+='<select id="DistrictSelect" class="selectBox" onchange="getNextRegions(this.id,\'DISTRICT\')" disabled="true">';
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
		
		
		regionStr+='<select id="ConstituencySelect" class="selectBox" onchange="getNextRegions(this.id,\'CONSTITUENCY\')" disabled="true">';
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
	
	
	
		regionStr+='<select id="MandalSelect" class="selectBox" onchange="getNextRegions(this.id,\'MANDAL\')" disabled="true">';
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
		smsStr+='<div><textarea rows="5" cols="50" id="smsTextArea" onkeyup="limitText(\'smsTextArea\',\'maxcount\',200)" ></textarea></div> ';
		smsStr+='<div id="limitDiv">';
		smsStr+='<table style="width:100%;"><tr>';
		smsStr+='<td style="width:50%;"><div id="remainChars"><span id="maxcount">200 </span> <span>chars remaining..</span></div></td>';
		smsStr+='<td style="width:50%;"><div>Should not exceed 200 chars</div></td>';
		smsStr+='</tr></table>';
		smsStr+='</div>';	
		
		if(smsTextElmtData)
			smsTextElmtData.innerHTML=smsStr;

		//--------------------------
		//Displaying button

		var buttonDiv = document.getElementById("button_div");
		var bstr='';
		bstr+='<input type="button" value="Send SMS" onclick="sendSMS()"/>';
		if(buttonDiv)
		{
			buttonDiv.innerHTML=bstr;
		}
	
	}
	
	function limitText(limitField, limitCount, limitNum)
	{		
		var limitFieldElmt = document.getElementById(limitField);
		var limitCountElmt = document.getElementById(limitCount);

		if (limitFieldElmt.value.length > limitNum) 
		{
			limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
		}
		else
		{			
			limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
		}
	}

	function sendSMSCadreLevel(){
		for( i = 0; i < document.smsForm.region_type_radio.length; i++ )
		{
			if( document.smsForm.region_type_radio[i].checked == true ){
				val = document.smsForm.region_type_radio[i].value;
				
			}
		}
		
		var textAreaElmt = document.getElementById("smsTextArea");

		textAreaElmtValue = textAreaElmt.value
		val=val.toUpperCase();
		var jsObj={
					SMS_LEVEL_TYPE:'CADRE_LEVEL',
					SMS_LEVEL_VALUE:val,
					SMS_MESSAGE:textAreaElmtValue,
					task:"sendSMS"
				  };
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
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
		
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/sendCadreSMS.action?"+rparam;
		callAjax(jsObj,url);
		
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
	padding:5px;	
}
#successDiv
{
	color:blue;
	font-weight:bold;
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
			<tr>
				<td align="left" colspan="2">
					<div id="successDiv" ></div>
				</td>
			</tr>
		</table>
	  </s:form>
	</body>
</html>
