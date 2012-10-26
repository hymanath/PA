<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"></link> 
<script type="text/javascript">

    var ScopeAll = false; 
    var scopeId = null;
	var locationValue = null;
	var statusAll = false;
	var statusId = null;
	var typeAll = false;
	var typeId = null;
	var usersAll = false;
	var userId = null;
function showDiv(divId)
{
	if(divId == "problemLocationDivId")
	{
		if(document.getElementById("probLocation").checked == true)
		 document.getElementById(divId).style.display = 'block';
		else if(document.getElementById("allProblems").checked == true)
		 document.getElementById(divId).style.display = 'none';
	}
	else if(divId == "probStatusInfo")
	{
		if(document.getElementById("selectedProblemId").checked == true)
			document.getElementById(divId).style.display = 'block';
		else if(document.getElementById("problemStatusId").checked == true)
			document.getElementById(divId).style.display = 'none';
	}
	else if(divId == "userListDiv")
	{
	  if(document.getElementById("selectedUser").checked == true)
		document.getElementById(divId).style.display = 'block';
	  else if(document.getElementById("allUsers").checked == true)
		document.getElementById(divId).style.display = 'none';
	}
	else if(divId == "problemTypeDiv")
	{
		if(document.getElementById("selectedProblemtypeId").checked == true)
			document.getElementById("problemTypeDiv").style.display = 'block';
   		else if(document.getElementById("allProblemTypes").checked == true)
			document.getElementById("problemTypeDiv").style.display = 'none';
	}

}

function getProblemsPostedUsersList()
   {
	var jsObj = {
		time : new Date().getTime(),
		task : "getProblemsPostedUsersList"

	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url);

}
function getStatesForProblem()
{

		var jsObj = {

			time : new Date().getTime(),
			task : "getStates"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url);

	}
function getDistricts(stateId)
{
	var jsObj = {
		stateId : stateId, 
		task    : "getDistrictsForASelectedState"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url); 

}
function clearAll(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
   }
function clearAllElmts(scopeId,value){
 if(scopeId==4)
 { 
    if(value==1)
	clearAllForSelect("constituencyDiv");
	
 }
 if(scopeId==5)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	}
   if(value==2)
	clearAllForSelect("mandalDiv");
 }
 if(scopeId==6)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }
 if(scopeId==9)
 {
   if(value==1)
   {
	clearAllForSelect("constituencyDiv");
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==2)
	{
	clearAllForSelect("mandalDiv");
	clearAllForSelect("villageDiv");
	}
	if(value==3)
	clearAllForSelect("villageDiv");
 }

}
function clearAllForSelect(elmtId)
   {
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=1;i--)
	{
		elmt.remove(i);
	}	
   }
   function getAllDetails(id,task,areaType,constId)
   {
        var jsObj =
		{ 
            time : new Date().getTime(),
			id:id,
			task:task,
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:areaType,
			constId:constId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	 callAjax(jsObj,url);
   }
 
function getProblemtypes()
   {
		var jsObj = {
			time : new Date().getTime(),
			task : "getProblemtypes"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getScopesForAProblemSearch.action?"+rparam;						
	callAjax(jsObj,url); 
   }
function callAjax(jsObj,url){

	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
		 
		 if(jsObj.task == "getStates")
			{
				clearOptionsListForSelectElmtId("stateDiv");
				createOptionsForSelectElmtIdWithSelectOption("stateDiv",myResults);
				
			}
		    else if(jsObj.task == "getDistrictsForASelectedState")
			{
				
				clearOptionsListForSelectElmtId("districtDiv");
		        createOptionsForSelectElmtIdWithSelectOption("districtDiv",myResults);
							
			}
			
			else if(jsObj.task == "constituenciesInDistrict")
			{ 
				
				buildResults("constituencyDiv",myResults);

				
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{
                 buildResults("mandalDiv",myResults);

				
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{
				
				
                 buildResults("constituencyDiv",myResults);

			}

			else if(jsObj.task == "hamletsOrWardsInRegion")
			{ 
				
				
				buildResults("villageDiv",myResults);

				
			}
			else if(jsObj.task == "boothsInTehsilOrMunicipality")
			{ 
				
				buildResults("villageDiv",myResults);

				
				
			}
		   else if(jsObj.task == "getProblemsPostedUsersList")
			{
				showDiv('userListDiv');
				clearOptionsListForSelectElmtId("problemsPostedUsersList");
				createOptionsForSelectElmtIdWithSelectOption("problemsPostedUsersList",myResults);
			}
			else if(jsObj.task == "getProblemtypes")
			{
				showDiv('problemTypeDiv');
				clearOptionsListForSelectElmtId("problemTypeId");
				createOptionsForSelectElmtIdWithSelectOption("problemTypeId",myResults);
			}
			
			         			
	}
	catch(e){   
				//alert("Invalid JSON result" + e);   
			}  
		},
	   scope : this,
	   failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				 }
	   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function buildResults(divId,results){
  var elmt = document.getElementById(divId);
         if(divId=='problemImpactRegion')
		 {
		    var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Scope";
		 }
		 else
		 {
	     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Location";
		}
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
		for(var i in results)
	  {
		var option = document.createElement('option');
		if(divId =="problemImpactRegion")
		  {
		  option.value=results[i].locationScope;
		  option.text=results[i].locationScopeValue;
		  }
		else if(divId =="stateDiv" || divId =="districtDiv")
		{
		  option.value=results[i].ids;
		  option.text=results[i].names;
		}
		else 
		{
		  if(results[i].id!=0)
		  {
		  option.value=results[i].id;
		  option.text=results[i].name;
		  }
		}
		if(results[i].id!=0)
		  {
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
		}
	  }
 
}

 /*function buildResults(divId,results){
   var elmt = document.getElementById(divId);

     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select Location";
         try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
	    
				
		for(var i in results)
	    {
		var option = document.createElement('option');
		
		  if(results[i].id!=0)
		  {
		  option.value = results[i].id;
		  option.text=results[i].name;
		  }
				
	 
	  if(results[i].id!=0)
		  {
	  try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}
	 }
 
}*/


function getLocations(id)
{
	if(id==0)
		alert("Please select Valid Location");
	/* else if(id==1)
	{
	 var str ='';
	str +='<table>';
	str +='  <tr><td></td>';
	str +='  </tr>';
	str +='</table>';
	 //document.getElementById("showScopeSubs").innerHTML = str;
	}*/
	else if(id==2)
	{
		var str = '';
		str +='<table>';
		str +='<tr>';
		str +='<th><span id="stateSpan">Select State :</span></th>';
		str +='<td>';
		str +='<select id="stateDiv" class="selectWidth" style="margin-left: 173px;"></select>';
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		document.getElementById("showLocationDiv").innerHTML = str;
	getStatesForProblem();
	}
	else if(id==3)
	{
		var str='';
		str +='<table>';
		str +='<tr>';
		str +='<td><span id="stateSpan">Select State :</span></td>';
		str +='<td>';
		str +='<select id="stateDiv" onchange="clearAll(\'districtDiv\'),getDistricts(this.options[this.selectedIndex].value)" class="selectWidth" style="margin-left: 160px;"></select>';
		str +='</td></tr>';
		str +='<tr><td><span id="districtSpan">Select District :</span></td>';
		str +='<td>';
		str +='<select id="districtDiv" class="selectWidth" style="margin-left: 160px;"></select>';
		str +='</td>';
		str +='</tr>';
		str +='</table>';
		document.getElementById("showLocationDiv").innerHTML = str;
	getStatesForProblem();
	}

	else if(id==4)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="stateSpan">Select State :</span></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts(this.options[this.selectedIndex].value)"style="margin-left: 60px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="districtSpan">Select District :</span></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')" style="margin-left: 60px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="constituencySpan">Select Assembly Constituency :</span></td>';
  str +='	   <td><select id="constituencyDiv" name="locationValue" class="selectWidth" style="margin-left: 60px;"></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showLocationDiv").innerHTML = str;
   getStatesForProblem();
  }
	 else if(id==5 || id==7)
  {
   if(id==5)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==7)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
   
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="stateSpan">Select State :</span></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth"  onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts(this.options[this.selectedIndex].value)" style="margin-left: 13px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="districtSpan">Select District :</span></td>';
  str +='	   <td>';
  str+='<select style="margin-left:13px;" id="districtDiv" class="selectWidth" onchange="clearAllElmts(5,2); clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='<td class="tdWidth"><span id="constituencySpan">Select Assembly Constituency :</span></td>';
  str +='	   <td><select id="constituencyDiv" style="margin-left: 13px;" class="selectWidth" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="mandalSpan">Select Mandal/Municipality/Corp/GMC :</span></td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" style="margin-left: 13px;" class="selectWidth" ></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showLocationDiv").innerHTML = str;
   getStatesForProblem();
  }
 
   else if(id==6 || id==8)
  {
   if(id==6)
   {
     areaType1 = "URBAN" ;
     areaType2 = "RURAL" ;
   }
   if(id==8)
   {
     areaType1 = "RURAL" ;
     areaType2 = "URBAN" ;
   }
    var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="stateSpan">Select State :</span></td>';
  str +='	   <td><select id="stateDiv" style="margin-left: 12px;" class="selectWidth" onchange="clearAllElmts(6,1);clearAll(\'districtDiv\');getDistricts(this.options[this.selectedIndex].value)"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="districtSpan">Select District :</span></td>';
  str +='	   <td>';
  str +='<select id="districtDiv" style="margin-left: 12px;" class="selectWidth" onchange="clearAllElmts(6,2); clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="constituencySpan">Select Assembly Constituency :</span></td>';
  str +='	   <td><select id="constituencyDiv" style="margin-left: 12px;" class="selectWidth" onchange="clearAllElmts(6,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="mandalSpan">Select Mandal/Municipality/Corp/GMC :</span></td>';
  str +='	   <td><select id="mandalDiv" style="margin-left: 12px;" class="selectWidth" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'hamletsOrWardsInRegion\',\'\',\'\')"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="villageSpan">Select Village/Ward/Division :</span></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth"  style="margin-left:12px;"></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showLocationDiv").innerHTML = str;
   getStatesForProblem();
   }
    else if(id==9)
  {
     var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="stateSpan">Select State :</span></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,1);clearAll(\'districtDiv\');getDistricts(this.options[this.selectedIndex].value)" style="margin-left: 12px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="districtSpan">Select District :</span></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')" style="margin-left: 12px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="constituencySpan">Select Assembly Constituency :</span></td>';
  str +='	   <td><select id="constituencyDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAllElmts(9,3);clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\'\',\'\')" style="margin-left: 12px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="mandalSpan">Select Mandal/Municipality/Corp/GMC :</span></td>';
  str +='	   <td><select id="mandalDiv" class="selectWidth" onkeydown="if (event.keyCode == 13) document.getElementById(\'searchButton\').click()" onchange="clearAll(\'villageDiv\');getAllDetails(this.options[this.selectedIndex].value,\'boothsInTehsilOrMunicipality\',\'\',document.getElementById(\'constituencyDiv\').options[document.getElementById(\'constituencyDiv\').selectedIndex].value)" style="margin-left: 12px;"></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth"><span id="villageSpan">Select Village/Ward/Division :</span></td>';
  str +='	   <td><select id="villageDiv" name="locationValue" class="selectWidth"  style="margin-left: 12px;"></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showLocationDiv").innerHTML = str;
   getStatesForProblem();
  }
  }

function getProblems()
{
	var str="<font style='color:red'>";
	var eFlag = false;
	var errorDivEle = document.getElementById("errorDiv");
	clickid = null;
	
	if(document.getElementById("allProblems").checked == true)
	{
		ScopeAll = true;
	}
	
	else 
	{
		var problemImpactRegionEle = document.getElementById("problemImpactRegion");
		var stateSelectElmt = document.getElementById("stateDiv");
		var districtSelectElmt = document.getElementById("districtDiv");
		var constituencySelectElmt = document.getElementById("constituencyDiv");
		var mandalSelectElmt = document.getElementById("mandalDiv");
		var villageSelectElmt = document.getElementById("villageDiv");
		ScopeAll = false;
		scopeId = problemImpactRegionEle.options[problemImpactRegionEle.selectedIndex].value;
		
		if(scopeId == 0)
		{
			str += 'Please Select Any Location';
			eFlag = true;
		}
		else if(scopeId == 2)
		{
			locationValue = stateSelectElmt.options[stateSelectElmt.selectedIndex].value;

			if(locationValue == 0)
			{
				str += 'Please Select State';
				eFlag = true;
			}
			
		}
		else if(scopeId == 3)
		{
			
			if(stateSelectElmt.options[stateSelectElmt.selectedIndex].value == 0)
			{
				str += 'Please Select State<br>';
				eFlag = true;
			}
			var selIndex = districtSelectElmt.selectedIndex;
			if(selIndex != -1)
			{
				locationValue = districtSelectElmt.options[districtSelectElmt.selectedIndex].value;
				if(locationValue == 0)
				{
					str += 'Please Select District';
					eFlag = true;
				}
			}
		}
		else if(scopeId == 4)
		{
			var seldistrictIndex = districtSelectElmt.selectedIndex;
			var selIndex = constituencySelectElmt.selectedIndex;

			if(stateSelectElmt.options[stateSelectElmt.selectedIndex].value == 0)
			{
				str += 'Please Select State.<br>';
				eFlag = true;
			}
			
			if(seldistrictIndex != -1)
			{
				if(districtSelectElmt.options[districtSelectElmt.selectedIndex].value == 0)
			   {
				str += 'Please Select District.<br>';
				eFlag = true;
			   }
			}
			
			if(selIndex != -1)
			{
			 locationValue = constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value;
				if(locationValue == 0)
				{
					str += 'Please Select Constituency.<br>';
					eFlag = true;
				}
		  }
			
		}
		else if(scopeId == 5 || scopeId == 7)
		{
			var seldistrictIndex = districtSelectElmt.selectedIndex;
			var constiIndex = constituencySelectElmt.selectedIndex;
			var selIndex = mandalSelectElmt.selectedIndex;

			if(stateSelectElmt.options[stateSelectElmt.selectedIndex].value == 0)
			{
				str += 'Please Select State<br>';
				eFlag = true;
			}
			
			if(seldistrictIndex != -1)
			{
				if(districtSelectElmt.options[districtSelectElmt.selectedIndex].value == 0)
			   {
				str += 'Please Select District.<br>';
				eFlag = true;
			   }
			}
		   if(constiIndex != -1)
			{
				if(constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value == 0)
			   {
				str += 'Please Select Constituency<br>';
				eFlag = true;
			   }
		    }
		   if(selIndex != -1)
			{
			locationValue = mandalSelectElmt.options[mandalSelectElmt.selectedIndex].value;
			
			if(locationValue == 0)
			{
			 str += 'Please Select Mandal/Municipality';
			 eFlag = true;
			}
		  }
		 
		}
		else if(scopeId == 6 || scopeId == 8 || scopeId == 9)
		{
			var seldistrictIndex = districtSelectElmt.selectedIndex;
			var constiIndex = constituencySelectElmt.selectedIndex;
			var mandalIndex = mandalSelectElmt.selectedIndex;
			var selIndex = villageSelectElmt.selectedIndex;
			if(stateSelectElmt.options[stateSelectElmt.selectedIndex].value == 0)
			{
				str += 'Please Select State.<br>';
				eFlag = true;
			}
			if(seldistrictIndex != -1)
			{
			 if(districtSelectElmt.options[districtSelectElmt.selectedIndex].value == 0)
			 {
				str += 'Please Select District.<br>';
				eFlag = true;
			 }
			}
		
		if(constiIndex != -1)
		 {	
			if(constituencySelectElmt.options[constituencySelectElmt.selectedIndex].value == 0)
			{
				str += 'Please Select Constituency.<br>';
				eFlag = true;
			}
		 }
		 if(mandalIndex != -1)
		  {

			if(mandalSelectElmt.options[mandalSelectElmt.selectedIndex].value == 0)
			{
			 str += 'Please Select Mandal/Municipality.<br>';
			 eFlag = true;
			}
		  }
		  if(selIndex != -1)
		  {
			 locationValue = 
			 villageSelectElmt.options[villageSelectElmt.selectedIndex].value;
			 if(locationValue == 0)
			 {
				str += 'Please Select Village/Ward/Booth.';
				eFlag = true;
			 }
			}
			
		}
		
	  }
	  if(document.getElementById("problemStatusId").checked == true)
	  {
		statusAll = true;
	  }
	  else
	  {
		 var problemstatus = document.getElementById("problemStatusDiv");
		 statusAll = false;
		 statusId = problemstatus.options[problemstatus.selectedIndex].value;

  		 if(statusId == 0)
		  {
			str +='Please Select Any Status.<br>';
			eFlag = true;
		  }
		  
	  }
	 if(document.getElementById("allProblemTypes").checked == true)
		typeAll = true;
	 else
	 {
		var problemType = document.getElementById("problemTypeId");
		typeId = problemType.options[problemType.selectedIndex].value;
		typeAll = false;
		if(typeId == 0)
		{
		   str +='Please Select Any ProblemType.<br>';
		   eFlag = true;
		}

	 }
	 if(document.getElementById("allUsers").checked == true)
		usersAll = true;
	 else
	{
		var probPostedUsersList = document.getElementById("problemsPostedUsersList");
		usersAll = false;
		userId = probPostedUsersList.options[probPostedUsersList.selectedIndex].value;
		if(userId == 0)
		  {
			str +='Please Select Any User.';
			eFlag = true;
		  }
	}
	str +='</font>';
	errorDivEle.innerHTML = str;
	
	  if(!eFlag)
	{
	   document.getElementById("searchAjaxImgSpan").style.display = 'block';
       news_Obj.initialize();
	}

}
var clickid = null;
function copyId(id)
{

  clickid = id;
}
var custom_paginator = {
	resultsCount:"",
	startIndex:"",
	ajaxCallURL:"",
	initialParams:"",
	resultsShown:"",
	callBackFunction:"",
	jsObj:{},
	results:{},
	totalRecords:"",
	paginatorElmt:"",		
	paginator:function(obj){
		this.startIndex = obj.startIndex;
		this.ajaxCallURL = obj.ajaxCallURL;
		this.jsObj = obj.jsObj;
		this.callBackFunction = obj.callBackFunction;
		this.paginatorElmt = obj.paginatorElmt;
		this.resultsCount = obj.resultsCount;		
	},	
	doAjaxCall:function(start){

		var url = this.ajaxCallURL+"&startIndex="+start+"&resultsCount="+this.resultsCount;
		
		var callback = {			
	    success : function( o ) {
			try 
			{				
				results = YAHOO.lang.JSON.parse(o.responseText);
				
				if(results != null && results.length>0)
				{
				    this.totalRecords = parseInt(results[0].problemCount);
					
				}
				else
                     this.totalRecords = 0;
					 this.buildPaginator();
				     this.callBackFunction();
				
			}
			catch (e)
			{   		
				//alert("Invalid JSON result" + e);   
			}  
		},
		scope : this,
		failure : function( o ) {
					//alert( "Failed to load result" + o.status + " " + o.statusText);
				  }
		};

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	},
	initialize:function (){		
		this.doAjaxCall(this.startIndex);
	},
	buildPaginator:function()
	{
		var paginatorElmt = document.createElement('Div');
		paginatorElmt.setAttribute("class","paginatorElmtClass");
		var iteration = Math.ceil(this.totalRecords/this.resultsCount);		
		var countIndex = this.startIndex;
		var str = '';

		if(iteration > 1)
		{
			for(var i=1; i<=iteration; i++)
			{			
				str += '<a href="javascript:{}" id="customPaginationId'+i+'" onclick="copyId(this.id);custom_paginator.doAjaxCall('+countIndex+')">'+i+'</a>';
				countIndex+=this.resultsCount;
			}
		}
		
		if(document.getElementById("custom_paginator_class")!=null)	
     	  document.getElementById("custom_paginator_class").innerHTML = str;
		if(clickid != null)
		{
		 $("#"+clickid).addClass('pagenationStyle');

		}
		 else
		{
		  $("#customPaginationId1").addClass('pagenationStyle');
		}
	}
};

  $(document).ready(function() {
    $('#custom_paginator_class a').live("click",function() {
	   
        $('#custom_paginator_class a').removeClass('pagenationStyle');
		$(this).addClass('pagenationStyle');
      });


	
	  $('#problemStatusId,#allProblemTypes,#allUsers').click(function()
	  {
		$('#probStatusDiv').css("margin-left","0px");

	  });
	
  });
custom_paginator.totalRecords = '${result.problemCount}';
var news_Obj = {
	
	problemStatus:[],
	startIndex:0,
	maxIndex:10,
	
	initialize:function(){
		
		this.getProblemDetails();

	},
	getProblemDetails:function()
    {  	
	
       var jsObj = {
				 time	       :  new Date().getTime(),
				 scopeAll      :  ScopeAll,
				 scopeId       :  scopeId,
				 locationValue :  locationValue,
				 statusAll     :  statusAll,
				 statusId      :  statusId,
				 typeAll       :  typeAll,
				 typeId        :  typeId,
				 usersAll      :  usersAll,
				 userId        :  userId,
				 task          :  "getProblemDetails"
			  };	
		
	     var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	     var url = "getProblemDetailsForFreeUserAction.action?"+rparam;				

	    custom_paginator.paginator({
			startIndex:this.startIndex,
			resultsCount:this.maxIndex,
			jsObj:jsObj,
			ajaxCallURL:url,
			paginatorElmt:"custom_paginator_class",
			callBackFunction:function(){
	    	showFreeUserProblemDetails(results);
			}
		});
		
		custom_paginator.initialize();
    }
	};

function showFreeUserProblemDetails(result)
{
	
	document.getElementById("searchAjaxImgSpan").style.display = 'none';
	var problemDetailsDivEle = document.getElementById("problems");
	var str ='';
	if(result == null || result == '')
	{
		str +='<div id="problemsNotFoundDiv">Problems Not Found For Your Search Criteria.</div>';
		problemDetailsDivEle.innerHTML = str;
		return;
	}

	//$("#customPaginationId1").css("background","red");
	for(var i in result)
	{
		str +='<div style="border: 1px solid #CCCCCC;margin-bottom: 10px;margin-left: 74px;margin-top: 21px; padding: 9px 9px 9px 9px;width: 790px;border-radius: 4px;">';
		str +='<div style="font-size: 16px;font-weight:bold; margin-bottom: 10px;border-bottom: 1px solid #CCCCCC;padding-bottom: 6px;"><a href="completeProblemDetailsAction.action?problemId='+result[i].problemHistoryId+'" style="color: #357EC7;">'+result[i].problem+'</a></div></td>';
		str +='<div>'+result[i].description+'</div>';
		str +='<div style="padding-top: 4px; padding-bottom: 4px;"><table>';
		str +='<tr>';
		str +='<td><span style="color: #028D35;font-weight:bold;">Location :</span></td>';
		str +='<td><span style="font-size: 16px;">'+result[i].problemLocation+'</span></td>';
		
		str +='</tr>';
		str +='</table></div>';
		str +='<div style="margin-bottom: 3px;"><table>';
		str +='<tr>';
		str +='<td><span style="color: #028D35;font-weight:bold;">Existing From : </td>';
        str +='<td><span>'+result[i].existingFrom+'</span></td>';
        str +='<td><span style="color: #028D35;font-weight:bold;margin-left: 15px;">Identified On :</td>';
	    str +='<td><span>'+result[i].reportedDate+'</span></td>';
		str +='<td><span style="color: #028D35;font-weight:bold;margin-left: 15px;">Posted By :</span></td>';
        str +='<td><span>'+result[i].name+'</span></td>';
		str +='</tr>';
		str +='</table></div>';
		str +='</div>';
	}

  problemDetailsDivEle.innerHTML = str;
}
</script>
<style type="text/css">
#headerImageCenterDiv {
    background-image: url("images/icons/constituencyManagement/header_body_blue.png");
    height: 30px;
    text-align: center;
    width: 250px;
}
#headerImageCenterDiv span{
	position: relative;
    top: 6px;
    font-size: 14px;
	font-weight:bold;
}
.requiredFont
{
	color:red;
}
#mainDiv
{
	background : #EEF4F6;
    border: 1px solid #B2BDC4;
    border-radius: 6px 6px 6px 6px;
	}
.btnClass
{
	background-color: #FFFFFF;
    background-image: url("images/icons/constituencyManagement/buttonblue.png");
    background-position: left bottom;
    background-repeat: repeat-x;
    border-color: #C4D1DB #9EB7CD #9EB7CD #C4D1DB;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1), 0 1px 0 rgba(255, 255, 255, 0.9) inset;
    font-size: 14px;
    font-weight: bold;
    height: 33px;
    margin-bottom: 10px;
    padding-left: 12px;
    padding-right: 12px;
    text-shadow: 0 1px 1px #FFFFFF;
	}
	.fontStyle
	{
		font-weight:bold;
		font-size: 16px;
		
	}
	#problemDetailsDiv
	{
	background:#FFFFFF;
	border: 1px solid #CCCCCC;
	border-radius: 3px 3px 3px 3px;
	margin-top: 6px;
	margin-bottom: 5px;
	}
	#problemsNotFoundDiv
	{
	margin-top: 26px;
	margin-left: 304px;
	margin-bottom: 28px;
	font-weight:bold;
	color:red;
	font-size:16px;
	}
	.paginatorElmtClass a {
    border: 1px solid #ADADAD;
    margin: 0 3px;
    font-size:11px;
    text-decoration: none;
    padding:5px;
    border-radius:50%;
    width:12px;height:14px;
    display:inline-block;
    text-align:center;
 
}
.paginatorElmtClass {
    margin-bottom: 19px;
    margin-left: 90px;
    margin-top: 19px;
}
.pagenationStyle{
  background-color:#F5371C;color:#fff;
}
.selectWidth
{
 width: 185px;
}
#stateSpan,#districtSpan,#mandalSpan,#villageSpan,#constituencySpan
{
	font-weight:bold
}
.pagination
{
	background: yellow ;
}
</style>
</head>
<body>
<div id="probSearchMainDiv" style="margin-left: auto; margin-right: auto; float:none;width:960px;">

	<div style="margin: 15px 15px 15px 350px;color:white;">
		<table border="0" cellpadding="0" cellspacing="0">          
			<tr>
			   <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
			   <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Problems Search</span></div></td>
			   <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
			 </tr>
		</table>
	</div>
	<div id="mainDiv">
	<div id="errorDiv" style="margin-left: 720px; margin-top: 10px;"></div>
	
		<div style="margin-left: 120px;">
			<table cellspacing="8" cellpadding="0" style="font-weight: bold; width: 80%;">
			<tr>
				<td style="width: 30%;">Problem Location</td>
				<td style="width: 20%; padding: 6px;padding-left:25px;">
					<input type="radio" value="" id="allProblems" name="problemOption" checked="true" onclick="showDiv('problemLocationDivId')" />&nbsp;All
				</td>
				<td style="margin-left: 0px; margin-right: 0px; padding-right: 24px; padding-left: 36px;">
					<input type="radio" value="" id="probLocation" name="problemOption" onclick="showDiv('problemLocationDivId')" />&nbsp;Select Specific Location
				</td>
			</tr>
			</table>
				<div id="problemLocationDivId" style="display:none;">
					<div style="margin-right: 0px; padding-right: 0px; margin-left: -54px;">
						<table id="probImpactRegion" style="width:80%;">
							<tr>
								<td><b style="margin-left: 59px;">Problem Impacted Region :</b></td>
								<td>
									<select class="selectWidth" id="problemImpactRegion" onchange="getLocations(this.options[this.selectedIndex].value)" style="margin-left: 0px; margin-right: 96px;">
										<option value="0">Select</option>
										<option value="2">STATE</option>
										<option value="3">DISTRICT</option>
										<option value="4">CONSTITUENCY</option>
										<option value="5">MANDAL</option>
										<option value="6">VILLAGE</option>
										<option value="7">MUNICIPAL-CORP-GMC</option>
										<option value="8">WARD</option>
										<option value="9">BOOTH</option>
									</select>
								</td>
								</tr>
								</table>
								</div>
								<div id="locationDiv" style="margin-left: 82px;">
								<table>
								<tr>
								<td>
									<div id="showLocationDiv" style="margin-left: -79px;"></div>

								</td>
							</tr>
						</table>
					</div>
					</div>
		  <div id="probStatusDiv">
			<table cellspacing="8" cellpadding="0" style="font-weight: bold; width: 80%;">
			<tr>
				<td style="width: 30%;">Problem Status</td>
				<td style="width: 20%; padding: 6px;">
					<input type="radio" value="" id="problemStatusId" name="problemStatus" checked="true" onclick="showDiv('probStatusInfo')" />&nbsp;All
				</td>
				
				<td style="width: 20%; padding: 6px;">
					<input type="radio" value="" id="selectedProblemId" name="problemStatus" onclick="showDiv('probStatusInfo')" />&nbsp;Select
				</td>
				<td style="padding: 6px; width: 20%;">
					<div id="probStatusInfo" style="display:none;">
						<select id="problemStatusDiv" class="selectWidth">
						<option value="0">select</option>
						<option value="1">NEW</option>
						<option value="2">PROGRESS</option>
						<option value="3">PENDING</option>
						<option value="4">FIXED</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td style="width: 4%;">Problem Type</td>
				<td style="padding:6px;">
					<input type="radio" value="" id="allProblemTypes" name="problemType" checked="true" onclick="showDiv('problemTypeDiv')" />&nbsp;All
				</td>
				<td style="padding:6px;">
					<input type="radio" value="" id="selectedProblemtypeId" name="problemType"  onclick="getProblemtypes()" />&nbsp;Select
				</td>
				<td>
				<div id="problemTypeDiv" style="display:none;">
					<select id="problemTypeId" class="selectWidth"></select>
				</div>
				</td>
			</tr>
			<tr>
				<td>By User</td>
				<td style="padding:6px;">
					<input type="radio" value="" id="allUsers" name="userType" checked="true" onclick="showDiv('userListDiv')" />&nbsp;All
				</td>
				<td style="padding:6px;">
					<input type="radio" value="" id="selectedUser" name="userType"  onclick="getProblemsPostedUsersList()" />&nbsp;Select
				</td>
				<td>
					<div id="userListDiv" style="display:none;">
						<select id="problemsPostedUsersList" class="selectWidth"></select>
					</div>
				</td>
			</tr>
			</table>
		</div>
		</div>
		<div style="margin-left: 421px; margin-top: 20px; margin-bottom: 12px;">
		<input type="button" class="btnClass" onclick="getProblems()" value="Search"/>
		<span id="searchAjaxImgSpan" style="display: block; margin-left: 101px; margin-bottom: 30px; margin-top: -37px;"><img src="images/icons/search.gif"  width="18px" height="18px;"></img></span>
		</div>
	</div>
	<div id="problemDetailsDiv">
	<div id="problems"></div>
	<div id="custom_paginator_class" class="paginatorElmtClass"></div>
	</div>
	
	
</div>
<script type="text/javascript">
  getProblems();
</script>
</body>
</html>