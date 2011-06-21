<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Report</title>

<style>
#voterHeading
{
	margin-bottom:20px;
	margin-top:20px;
}

#voterSearchMain 
{
	-moz-border-radius	: 6px 6px 6px 6px;
	background-color	: #B2BDC4;
	border				: 1px solid #B2BDC4;
	padding-left		: 20px;
	padding-top			: 20px;
	width				: 80%;
}

.btnClass {
	background-image:url("images/icons/indexPage/swasthic_body.png");
	background-repeat:repeat-x;
	border:1px solid #ADADAD;
	color:#244565;
	font-weight:bold;
	padding:5px;
	text-decoration:none;
}
</style>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css">
<link type="text/css" rel="stylesheet" href="styles/cadreSearch/cadreSearch.css"></link>

<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"/>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>

<script type = "text/javascript">

var accessType = '${sessionScope.USER.accessType}';

function populateLocations(val,source)
{	

	REPORTLEVEL = val;
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");

	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value; 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");		
	var boothFieldEl = document.getElementById("boothField_s");
	
		
 if(source == 'onChange')
{
	if(accessType == 'COUNTRY')
	{
		stateFieldEl.selectedIndex = '0';
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'STATE')
	{
		districtFieldEl.selectedIndex = '0';
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
	} else if(accessType == 'DISTRICT' || accessType == 'MP')
	{
		constituencyFieldEl.selectedIndex = '0';
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInDistrict(selectedDistrict,'cadreSearch','constituencyField_s','cadreSearch')
	} else if(accessType == 'MLA')
	{
		mandalFieldEl.selectedIndex = '0';
		hamletFieldEl.selectedIndex = '0';
		boothFieldEl.selectedIndex = '0';
		getSubRegionsInConstituency(accessValue,'cadreSearch','mandalField_s','cadreSearch')
	}						
} else if(source == "onLoad")
	{
		//setCadreValue(accessValue,'onChange');
		if(val == 9)
		{
			mandalField_sVal = mandalFieldEl.options[mandalFieldEl.selectedIndex].text;
			var flag = mandalField_sVal.search("Greater Municipal Corp");
			if(flag == '-1')
			{
				if(row6El.style.display == 'none')
					row6El.style.display = '';						
			} else {
				if(row5El.style.display == 'none')
					row5El.style.display = '';
				if(row6El.style.display == 'none')
					row6El.style.display = '';
			}
		}
	}	
	
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';		
	var value = val;
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';					
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';			
	} else if(value == 5)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 6)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 7)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 8)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 9)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row6El.style.display == 'none')
			row6El.style.display = '';			
	}
			 
}

function getVoterDetails()
{
	var rangeEle   = document.getElementsByName("region_type_radio");
	var stateEle	   = document.getElementById("stateField_s");
	var districtEle = document.getElementById("districtField_s");
	var constituencyEle = document.getElementById("constituencyField_s");
	var mandalEle  = document.getElementById("mandalField_s");
	var villageEle = document.getElementById("hamletField_s");
	var boothEle   = document.getElementById("boothField_s");
	
	var range = '';
	var rangeValue = '';
	var str = '';
	var flag = false;
	var errorMsgDivEle = document.getElementById("errorMsgDiv");

	for(var i= 0;i<rangeEle.length;i++)
		if(rangeEle[i].checked == true)
			range = rangeEle[i].value;

	if(range == 'CONSTITUENCY' || range == 'MANDAL' || range == 'VILLAGE' 
		|| range == 'MUNICIPAL-CORP-GMC' || range == 'WARD' || range == 'BOOTH')
	{
		if(stateEle.options[stateEle.selectedIndex].text == 'Select State')
		{
			str += 'Please Select State<br>';
			flag = true;
		}

		if(districtEle.options[districtEle.selectedIndex].text == 'Select District')
		{
			str += 'Please Select District<br>';
			flag = true;
		}
		if(constituencyEle.options.length == 0 || constituencyEle.options[constituencyEle.selectedIndex].text == 'Select Location')
		{
			str += 'Please Select Constituency<br>';
			flag = true;
		}
		if(!flag)
		rangeValue = constituencyEle.options[constituencyEle.selectedIndex].value;
	}
	if(range == 'MANDAL' || range == 'VILLAGE' 
		|| range == 'MUNICIPAL-CORP-GMC' || range == 'WARD' || range == 'BOOTH')
	{
		if(mandalEle.options.length == 0 || mandalEle.options[mandalEle.selectedIndex].text == 'Select Location')
		{
			str += 'Please Select Mandal/Municipality/Corp/GMC<br>';
			flag = true;
		}
		else
		rangeValue = mandalEle.options[mandalEle.selectedIndex].value;
	}
	if(range == 'VILLAGE' || range == 'WARD')
	{
		if(villageEle.options.length == 0 || villageEle.options[villageEle.selectedIndex].text == 'Select Location')
		{
			str += 'Please Select Village/Ward/Division <br>';
			flag = true;
		}
		else
		rangeValue = villageEle.options[villageEle.selectedIndex].value;
	}
	if(range == 'BOOTH')
	{
		if(boothEle.options.length == 0 || boothEle.options[boothEle.selectedIndex].text == 'Select Location')
		{
			str += 'Please Select Booth No <br>';
			flag = true;
		}
		else
		rangeValue = boothEle.options[boothEle.selectedIndex].value;
	}
	
	if(flag)
	{
		errorMsgDivEle.innerHTML = str;
		return;
	}
	else if(!flag)
	{
		errorMsgDivEle.innerHTML = '';
		alert(range);
		alert(rangeValue);
		var jsObj=
		{
			range : range,
		rangeValue: rangeValue,
			task	  : "getvoterDetails"	
		}
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVoterDetailsInALocationAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
				   success : function( o ) 
				  {
					try{			
						if(o.responseText)
						myResults = YAHOO.lang.JSON.parse(o.responseText);

						if(jsObj.task == 'getvoterDetails')
						{	
							alert("k");
						} 
							
						}
					catch(e)
					{}
							  
					},
				   scope : this,
				   failure : function( o ){}
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

</script>

</head>
<body>

<div id="voterHeading"><center>
	<table border="0" cellpadding="0" cellspacing="0">          
	  <tr>
		<td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
		<td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Voter Report</span></div></td>
		<td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
	  </tr>
	</table>    	
  </center>
</div>

<center>
	<div id="voterSearchMain">
		<table width="100%" class="cadreSearchInputTable">				
			<tr>
				<td colspan="2"><div id="errorMsgDiv"></div></td>
			</tr>
		</table>

		<table width="100%" class="cadreSearchInputTable">	
			<tr>
				<th align="left"><font color="#FF0000"> * </font>Select Range</th>                
				<td align="left">
				<div id="rangeRegionsRadioElmtDiv">
				<!---	<input type="radio" onclick="populateLocations(this.id,'onChange')" value="CONSTITUENCY" id="3" name="region_type_radio"> P CONSTITUENCY  -->
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="CONSTITUENCY" id="4" name="region_type_radio"> CONSTITUENCY
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="MANDAL" id="5" name="region_type_radio">MANDAL
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="VILLAGE" id="6" name="region_type_radio"> VILLAGE
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="MUNICIPAL-CORP-GMC" id="7" name="region_type_radio"> MUNICIPAL-CORP-GMC
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="WARD" id="8" name="region_type_radio"> WARD
					<input type="radio" onclick="populateLocations(this.id,'onChange')" value="BOOTH" id="9" name="region_type_radio"> BOOTH
				</div>
							
				<div id="rangeRegionsRadioElmtDiv">
				<table  width="100%" >
					<tr id="row1" style="display:none;">
						<td width="200"><s:label for="stateField_s" id="stateLabel" theme="simple" value="STATE" /><font color="#FF0000"> * </font></td>
						<td>
							<s:select id="stateField_s" cssClass="regionSelect" theme="simple" list="stateList" listKey="id" listValue="name" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','cadreSearch','districtField_s','cadreSearch', 'null')"></s:select>
						</td>
					</tr>

					<tr id="row2" style="display:none;">
						<td width="200"><s:label for="districtField_s" id="districtLabel" theme="simple" value="DISTRICT" /><font color="#FF0000"> * </font></td>
						<td>
							<s:select id="districtField_s" cssClass="regionSelect" theme="simple" list="districtList" listKey="id" listValue="name" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'cadreSearch','constituencyField_s','cadreSearch')"></s:select>
						</td>
					</tr>

					<tr id="row3" style="display:none;">
						<td width="200"><s:label for="constituencyField_s" id="constituencyLabel" theme="simple"  value="CONSTITUENCY"/><font color="#FF0000"> * </font></td>
						<td>
							<s:select id="constituencyField_s" theme="simple" cssClass="regionSelect" list="constituencyList" listKey="id" listValue="name" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'cadreSearch','mandalField_s','cadreSearch')"></s:select>
						</td>
					</tr>
					<tr id="row4" style="display:none;">
						<td width="200"><s:label for="mandalField_s" id="mandalLabel" theme="simple"  value="Mandal/Municipality/Corp/GMC" /><font color="#FF0000"> * </font></td>
						<td>
							<s:select id="mandalField_s" cssClass="regionSelect" theme="simple" list="{}" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'cadreSearch','null','cadreSearch','constituencyField_s','row6', 'row5')"></s:select>
						</td>
					</tr>	

					<tr id="row5" style="display:none;">
						<td width="200"><s:label for="hamletField_s" id="mandalLabel" theme="simple"  value="Village/Ward/Division" /><font color="#FF0000"> * </font></td>
						<td>
							<s:select id="hamletField_s" cssClass="regionSelect" onchange="getBoothsInWard('cadreSearch','constituencyField_s','boothField_s',this.options[this.selectedIndex].value,'cadreSearch','mandalField_s')" list="{}" theme="simple" listKey="id" listValue="name"></s:select>
						</td>
					</tr>
					<tr id="row6" style="display:none;">
						<td width="200">Booth No<font color="#FF0000"> * </font></td>
						<td>
							<s:select id="boothField_s" cssClass="regionSelect" theme="simple" list="{}" listKey="id" listValue="name"></s:select>
						</td>
					</tr>
				</table>
			</div>
			</tr>
		</table>

		<div>
			<table>
			<tr>
				<td align="center">
				<input type="button" value="Get Details" onclick="getVoterDetails()" class="btnClass">
				</td>
			</tr>
			</table>
		</div>

	</div>
</center>
</body>
</html>