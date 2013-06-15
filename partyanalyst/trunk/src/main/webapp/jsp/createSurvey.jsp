<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Survey</title>

    <script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 

	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 

	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	  <script type="text/javascript" src="js/jQuery/js/jquery-1.4.2.min.js"></script>
	  <link href="styles/assets/css/bootstrap.css" rel="stylesheet">
	  <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
	<!-- Skin CSS files resize.css must load before layout.css --> 

	<script type="text/javascript" src="js/specialPage/specialPage.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<style type="text/css">
#surveyMainDiv{float: none;
    margin-left: auto;
    margin-right: auto;
    width: 750px;}
.requiredFont,#errorMsgDiv{color:red;}
.selectWidth{width: 175px;}
#surveyInnerDiv{font-family: arial; font-size: 13px; padding-top: 11px;}
#headingDiv1 {
    background-color: #05A8E9;
    border-radius: 4px 4px 4px 4px;
    color: snow;
    padding: 2px;
	float: none;
    margin-left: auto;
    margin-right: auto;
	text-align: center;
    width: 600px;
	margin-top:10px;margin-bottom:20px;
}
#btnDiv{text-align: center; margin-top: 20px; padding-bottom: 22px;}
#errorMsgDiv{margin-bottom: 10px;}
</style>
</head>
<body>

<div id="surveyMainDiv">
 <div id="headingDiv1"><h3>Create New Survey</h3></div>
  <div id="surveyInnerDiv" class="widget blue">
  <div id="successMsgDiv"></div>
  <div id="errorMsgDiv"></div>
    <table>
	 <tr>
	   <td colspan="1" style="width:125px;">Name:<font class="requiredFont">*</font></td>
	   <td colspan="1"><input type="text" id="userName" /></td>
	 </tr>
	 <tr>
	   <td colspan="1" style="width:125px;">Description:<font class="requiredFont">*</font></td>
	   <td colspan="1"><textarea id="description" rows="3" colums="20"></textarea></td>
	 </tr>
     <tr>
	   <td style="width:125px;">LocationScope:<font class="requiredFont">*</font></td>
	   <td>
	     <select id="scopeDiv">
		   <option value="0">Select Scope</option>
		   <option value="1">COUNTRY</option>
		   <option value="2">STATE</option>
		   <option value="3">DISTRICT</option>
		   <option value="4">CONSTITUENCY</option>
		   <option value="5">MANDAL</option>
		   <option value="7">MUNICIPAL-CORP-GMC</option>
		 </select>
	   </td>
	   
	    <td id="parliamentTd" style="display:none;">
		  <input type="radio" class="constituencyRadioCls" id="parliament" value="Parliament" name="constituencyType" />Parliament
		</td>
	    <td id="assemblyTd" style="display:none;">
		  <input name="constituencyType" class="constituencyRadioCls" type="radio" id="assembly" value="Assembly" checked="true"/>Assembly
		</td>
	 </tr>
	 <tr><td colSpan="3"><div id="showScopeSubs"></div></td></tr>

	</table>
	
	<div id="btnDiv"><input type="button" value="save" class="btn btn-info" id="saveBtn"/></div>
  </div>
</div>

<script type="text/javascript">

$(document).ready(function(){

   $("#scopeDiv").change(function(){
	var id = $("#scopeDiv").val();
	
	$("#showScopeSubs").html('');
	$("#assemblyTd").css("display","none");
	$("#parliamentTd").css("display","none");

	if(id==0)
  {
   var val ='';
  val +='<table>';
  val +='  <tr><td></td>';
  val +='  </tr>';
  val +='</table>';
  document.getElementById("showScopeSubs").innerHTML = val;
    
  }
  else if(id==1)
  {
    var str ='';
  str +='<table>';
  str +='  <tr><td></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
  }
  else if(id==2)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" name="locationValue" style="width:175px;"/></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  
   else if(id==3)
  {
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" style="width:175px;"  onchange="clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" style="width:175px;" name="locationValue" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }

  else if(id==4)
  {
    
	$("#assemblyTd").css("display","inline-block");
	$("#parliamentTd").css("display","inline-block");
	var constituencyType = $("input[name='constituencyType']:checked").val();
	if(constituencyType == "Parliament")
	{
	  var str ='';
	  str +='<table>';
	  str +='<tr>';
      str +='<td class="tdWidth" style="width: 121px;">Parliament Constituency :<font class="requiredFont">*</font></td>';
      str +='	   <td><select id="parliamentConsDiv" name="locationValue" style="width:175px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
      str +='  </tr>';
      str +='</table>';
	  document.getElementById("showScopeSubs").innerHTML = str;
	  getAllParliamentConstInCountry();
	}
	else
	{
   var str ='';
  str +='<table>';
  str +='  <tr>';
  str +='<td class="tdWidth" style="width: 121px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv" class="selectWidth" style="width:175px;"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv" class="selectWidth" style="width:175px;"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='<td class="tdWidth" style="width: 121px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	<td><select id="constituencyDiv" name="locationValue" style="width:175px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
	  }
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
  str +='	   <td class="tdWidth" style="width: 121px;">State :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="stateDiv"   class="selectWidth" style="width:175px;" onchange="clearAllElmts(5,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">District :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="districtDiv"  class="selectWidth" style="width:175px;" onchange="clearAllElmts(5,2);clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'getConstNotInGivenAreaType\',\''+areaType1+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="constituencyDiv"  class="selectWidth" style="width:175px;" onchange="clearAll(\'mandalDiv\');getAllDetails(this.options[this.selectedIndex].value,\'subRegionsInConstituency\',\''+areaType2+'\',\'\')"><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='  <tr>';
  str +='	   <td class="tdWidth" style="width: 121px;">Mandal/Municipality/Corp/GMC :<font class="requiredFont">*</font></td>';
  str +='	   <td><select id="mandalDiv" name="locationValue" style="width:175px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
  str +='  </tr>';
  str +='</table>';
   document.getElementById("showScopeSubs").innerHTML = str;
   getStatesForSpecialPage();
  }
  
	
   });//End of Regions Displaying


 $("#saveBtn").click(function(){
	 
  var flage = validateFields();

   if(flage)
   {
	   var name = $("#userName").val();
	   var desc = $("#description").val();

	  var id = $("#scopeDiv").val();
	  var countryId = 0;
	  var stateId = 0;
	  var districtId = 0;
	  var constituencyId = 0;
	  var mandalId = 0;
	  var constituencyType = null;
	  if(id==1)
	   countryId = id;
	  else if(id==2)
	   stateId = $("#stateDiv").val();
	  else if(id==3)
	  {
	    stateId = $("#stateDiv").val();
	   districtId = $("#districtDiv").val();
	  }
	  else if(id==4)
	  {
		constituencyType = $("input[name='constituencyType']:checked").val(); 
		if(constituencyType == "Parliament")
		 constituencyId = $("#parliamentConsDiv").val();
		else
		{
		stateId = $("#stateDiv").val();
	    districtId = $("#districtDiv").val();
	    constituencyId = $("#constituencyDiv").val();
		}
      }
	  else if(id==5 || id == 7)
	 {
		stateId = $("#stateDiv").val();
	    districtId = $("#districtDiv").val();
	    constituencyId = $("#constituencyDiv").val();
	    mandalId = $("#mandalDiv").val();
    }

	var jsObj = {
	    scopeVal:id,
	    stateId:stateId,
	    districtId:districtId,
	    constituencyId:constituencyId,
	    mandalId:mandalId,
	    name:name,
	    description:desc,
		constituecyType:constituencyType,
	    task:"saveSurveyData",
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "surveyDetailsSaveAction.action?"+rparam;						
	callAjax(jsObj,url);
	  
   }
	 else
	  return;
	 
 });//End of save method

 $(".constituencyRadioCls").live("click",function(){
	
	$("#showScopeSubs").html('');

	var constituencytype = $("input[name='constituencyType']:checked").val();
	var str ='';
	if(constituencytype == "Assembly")
	{
		
    str +='<table>';
    str +='  <tr>';
    str +='	   <td class="tdWidth" style="width: 121px;">State :<font class="requiredFont">*</font></td>';
    str +='	   <td><select id="stateDiv" class="selectWidth" style="width:175px;"  onchange="clearAllElmts(4,1);clearAll(\'districtDiv\');getDistricts1(this.options[this.selectedIndex].value)"/></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth" style="width: 121px;">District :<font class="requiredFont">*</font></td>';
    str +='	   <td><select id="districtDiv" class="selectWidth" style="width:175px;"  onchange="clearAll(\'constituencyDiv\');getAllDetails(this.options[this.selectedIndex].value,\'constituenciesInDistrict\',\'\',\'\')"><option value="0">Select Location</option></select></td>';
    str +='  </tr>';
    str +='  <tr>';
    str +='	   <td class="tdWidth" style="width: 121px;">Assembly Constituency :<font class="requiredFont">*</font></td>';
    str +='	   <td><select id="constituencyDiv" name="locationValue" style="width:175px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
    str +='  </tr>';
    str +='</table>';
     document.getElementById("showScopeSubs").innerHTML = str;
     getStatesForSpecialPage();
	}

	else if(constituencytype == "Parliament")
	{
	  str +='<table>';
	  str +='  <tr>';
      str +='	   <td class="tdWidth" style="width: 121px;">Parliament Constituency :<font class="requiredFont">*</font></td>';
      str +='	   <td><select id="parliamentConsDiv" name="locationValue" style="width:175px;" class="selectWidth" ><option value="0">Select Location</option></select></td>';
      str +='  </tr>';
      str +='</table>';
	  document.getElementById("showScopeSubs").innerHTML = str;
	  getAllParliamentConstInCountry();
	}

 });
	
});//End of ready

 function getAllParliamentConstInCountry(element)
 {
    var timeST = new Date().getTime();
	var jsObj=
	{		
            time : timeST,		
			task: "getAllParliamentConstituencies",
			elmtId: "parliamentConsDiv" 	
	}

    var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
    var url = "getAllParliamentConstInCountry.action?"+rparam;						
    callAjax(jsObj,url);
 }

function validateFields()
{
  $("#successMsgDiv").html('');
  $("#errorMsgDiv").html('');
  var name = $("#userName").val();
  var desc = $("#description").val();
  var id = $("#scopeDiv").val();
  var flag = true;
  var str = '';
  if($.trim(name) == "")
  {
	str +='Please Enter Name.<br>';
	flag = false;
  }
  if($.trim(desc) == "")
  {
	str +='Please Enter Description.<br>';
	flag = false;
  }
  if(id == 0)
  {
	str +='Location Scope is Required.';
	flag = false;
  }
  else if(id==2)
  {
    var stateVal = $("#stateDiv").val();
	if(stateVal == 0)
	{
	  str +='Please Select State.';
	  flag = false;
	}
  }
  else if(id==3)
  {
	var stateVal = $("#stateDiv").val();
	var districtVal = $("#districtDiv").val();
	if(stateVal == 0)
	{
	  str +='Please Select State.<br>';
	  flag = false;
	}
	if(districtVal == 0)
	{
	  str +='Please Select District.';
	  flag = false;
	}
  }
  else if(id==4)
  {
	var stateVal = $("#stateDiv").val();
	var districtVal = $("#districtDiv").val();
	var constituencyVal = $("#constituencyDiv").val();
	if(stateVal == 0)
	{
	  str +='Please Select State.<br>';
	  flag = false;
	}
	if(districtVal == 0)
	{
	  str +='Please Select District.<br>';
	  flag = false;
	}
	if(constituencyVal == 0)
	{
	  str +='Please Select Constituency.';
	  flag = false;
	}
  }

  else if(id==5 || id == 7)
  {
	var stateVal = $("#stateDiv").val();
	var districtVal = $("#districtDiv").val();
	var constituencyVal = $("#constituencyDiv").val();
	var mandalVal = $("#mandalDiv").val();

	if(stateVal == 0)
	{
	  str +='Please Select State.<br>';
	  flag = false;
	}
	if(districtVal == 0)
	{
	  str +='Please Select District.<br>';
	  flag = false;
	}
	if(constituencyVal == 0)
	{
	  str +='Please Select Constituency.<br>';
	  flag = false;
	}
	if(mandalVal == 0)
	{
	  str +='Please Select Mandal.';
	  flag = false;
	}
  }
  $("#errorMsgDiv").html(str);
  return flag;
}

function callAjax(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
		 myResults = YAHOO.lang.JSON.parse(o.responseText); 
         	 
            if(jsObj.task == "getStates")
			{ 
               buildResults(myResults,"stateDiv");
			}
			else if(jsObj.task == "getDistrictsByStateId")
			{ 
               buildResults(myResults,"districtDiv");
			}
			else if(jsObj.task == "constituenciesInDistrict")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "getConstNotInGivenAreaType")
			{ 
               buildResults(myResults,"constituencyDiv");
			}
			else if(jsObj.task == "subRegionsInConstituency")
			{ 
               buildResults(myResults,"mandalDiv");
			}
			else if(jsObj.task == "getAllParliamentConstituencies")
			{
			  clearOptionsListForSelectElmtId(jsObj.elmtId);
			  createOptionsForSelectElmtId(jsObj.elmtId,myResults);
			}

			else if(jsObj.task == "saveSurveyData")
			{
			  showSurveyStatus(myResults);
			}
				
			}
		catch(e)
		{   
		// alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{
								//alert( "Failed to load result" + o.status + " " + o.statusText);
	}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showSurveyStatus(results)
{
	$("#successMsgDiv").html('');
	$("#errorMsgDiv").html('');
  if(results.resultCode == "0")
  {
	$("select#scopeDiv").val('0'); 
	$("#userName").val('');
	$("#description").val('');
	$("#parliamentTd").css("display","none");
	$("#assemblyTd").css("display","none");
	$("#showScopeSubs").html('');

    $("#successMsgDiv").html("Survey Created Successfully.").css("color","green");
	return;
  }
  else
  {
	$("#errorMsgDiv").html("Error Occured try again.").css("color","red");
	return;
  }
  
}

</script>
</body>
</html>