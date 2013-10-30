<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Access</title>
<script type="text/Javascript" src="js/homePage/jquery.js"></script>
<style type="text/css">
  #managingCountryMainDiv
		{
			text-align:left;
			width:46%;
		}
  .headingID
		{
			font-weight:bold;			
		}
  .f2
		{
			border:4px solid #CFD6DF;
			margin-bottom:20px;
			margin-right:42px;
			padding:10px;
			width: 377px;
		}
   .l2  {
			background-color:#567AAF;
			color:#FFFFFF;
			font-size:12px;
			font-weight:bold;
			padding:5px;
		}
	.f1
		{
			border:4px solid #CFD6DF;
			margin-bottom:20px;
			padding:10px;
			width: 398px;
		}
	h2 {
           color: #669900;
           font-weight: normal;
           margin: 0 0 15px;
           padding: 0;
      }
   .button {
           background: none repeat scroll 0 0 #335291;
           color: #FFFFFF;
           margin-bottom: 5px;
           margin-top: 5px;
           padding: 5px;
           width: 110px;
          }
		  
	.edit {
            background-color:#335291;
			color: #FFFFFF;
          }
     p    {
           line-height: 5px;
           margin: 0 0 5px;
           padding: 0;
         }
	 th  {
           color: #113D5B;
           font-size: 12px;
           padding: 6px;
           text-align: left;
           vertical-align: top;
            width: 12px;
         }
</style>

<script type="text/javascript">

  function getAllDistrictDetails(){
      $("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
   	 document.getElementById("allDistrictAccessDistrictDiv").innerHTML = '';
	 document.getElementById("districtMessage").innerHTML = '';
   var stateEle = document.getElementById("districtId");
   var stateId = stateEle.options[stateEle.selectedIndex].value;
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
   if(stateId!= 0)
   {
   var jsObj=
		{					
				task:"getAllDistrictDetailByUserIdStateId",				
				userId : userId,
				stateId : stateId
		};
   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
   var url = "getAllAccessRegionDetailAction.action?"+rparam;	
   callAjax(jsObj,url);
    }
  }
  
  function showUserAccessDetails(results){
	   document.getElementById("districtMessage").innerHTML = '';
	    document.getElementById("constituencyMessage").innerHTML = '';
	  var countrydetails='';
	  
         for(var i in results.country){
           countrydetails = countrydetails+"<p style=\"font-size:15px;\">"+results.country[i].countryName+"</p><br />";
		 }
	  document.getElementById("allCountryAccessDiv").innerHTML = countrydetails ;
	  document.getElementById("saveCountryAccess").innerHTML = '';
	   
	   var statedetails='';
         for(var i in results.state){
           statedetails = statedetails+"<p style=\"font-size:15px;\">"+results.state[i].stateName+"</p><br />";
		 }
		
	  document.getElementById("allStateAccessDiv").innerHTML = statedetails ;
	  document.getElementById("saveStateAccess").innerHTML = '';
	  
	   var districtdetails='';
         for(var i in results.district){
           districtdetails = districtdetails+"<p style=\"font-size:15px;\">"+results.district[i].districtName+"</p><br />";
		 }
	  document.getElementById("allDistrictAccessStateDiv").innerHTML = districtdetails ;
	  document.getElementById("allDistrictAccessDistrictDiv").innerHTML = '';
	  document.getElementById("saveDistrictAccess").innerHTML = '';
	   
	   var constituencydetails='';
         for(var i in results.constituency){
           constituencydetails = constituencydetails+"<p style=\"font-size:15px;\">"+results.constituency[i].constituencyName+
			   "  "+results.constituency[i].electionType +"</p><br />";
		 }
	  document.getElementById("assemblyOrParliamentDiv").innerHTML = '';
	  document.getElementById("allConstituencyStateAccessDiv").innerHTML = '' ;	  
	  document.getElementById("allConstituencyAccessDiv").innerHTML = constituencydetails ;
	  document.getElementById("saveConstituencyAccess").innerHTML = '';
   }

  function editCountryAccess(){
       document.getElementById("countryMessage").innerHTML = '';
      var str ="<input class=\"button\" type=\"button\" onclick=\"saveCountryAccessDetails()\" value=\"Save\">";
	   document.getElementById("saveCountryAccess").innerHTML = str;
	  var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
	  var jsObj=
		{					
				task:"getCountryDetails",
				userId:userId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getAllAccessRegionDetailAction.action?"+rparam;	
	 callAjax(jsObj,url);
  }
  function editStateAccess(){
      document.getElementById("stateMessage").innerHTML = '';
      var str ="<input class=\"button\" type=\"button\" onclick=\"saveStateAccessDetails()\" value=\"Save\">";
	   document.getElementById("saveStateAccess").innerHTML = str;
	   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
	 var jsObj=
		{					
				task:"getStateDetails",
				userId:userId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getAllAccessRegionDetailAction.action?"+rparam;	
	 callAjax(jsObj,url); 
  }
  function editDistrictAccess(){
     var val ="<input class=\"button\" type=\"button\" onclick=\"saveDistrictAccessDetails()\" value=\"Save\">";
	   document.getElementById("saveDistrictAccess").innerHTML = val;
	  var str = "<select id=\"districtId\""; 
	  str = str+"onchange=\"getAllDistrictDetails()\">";
      document.getElementById("allDistrictAccessStateDiv").innerHTML = str;
	  var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
        var jsObj=
		{		userId:userId,
				task:"getStateDetailForDistrict"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
  }
  function editConstituencyAccess(){
	document.getElementById("allConstituencyAccessDiv").innerHTML ='';
      var val ="<input class=\"button\" type=\"button\" onclick=\"saveAssmbConstAccessDetails()\" value=\"Save\">";
	   document.getElementById("saveConstituencyAccess").innerHTML = val;
	  var str ='';
	str = "<tr><td class=\"tdradio\"><input type=\"radio\" checked=\"checked\" name=\"a_radio\" id=\"a_radio\" " ;
	str = str+ " onclick=\"getAssemblyConsDetailsByStateIdUserId();showStateSelect()\"/>Assembly</td> " ;
    str = str+ " <td><input type=\"radio\" name=\"a_radio\" id=\"p_radio\" onclick=\"getParliConsDetailsByUserId();hideStateSelect()\"/>Parliament</td></tr> " ;
	  document.getElementById("assemblyOrParliamentDiv").innerHTML = str;
	  getStateDetailForConstituency();
 }
  function getStateDetailForConstituency(){
    var str = "<select id=\"stateConstituencyId\""; 
	  str = str+"onchange=\"getAssemblyConsDetailsByStateIdUserId()\">";
      document.getElementById("allConstituencyStateAccessDiv").innerHTML = str;
	  var  userE = document.getElementById("userId");
	 var userId =  userE.options[userE.selectedIndex].value;
     var jsObj=
		{		userId:userId,
				task:"getStateDetailForConstituency"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
  }
  function getAllAccessRegionDetails(){
           $("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
		   $("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
		   $("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
          document.getElementById("userMessage").innerHTML = '';
          document.getElementById("allCountryAccessDiv").innerHTML = '';
		  document.getElementById("saveCountryAccess").innerHTML = '';
		  document.getElementById("allStateAccessDiv").innerHTML = '';
		  document.getElementById("saveStateAccess").innerHTML = '';
		  document.getElementById("districtMessage").innerHTML = '';
		  document.getElementById("allDistrictAccessStateDiv").innerHTML = '';
		  document.getElementById("allDistrictAccessDistrictDiv").innerHTML = '';
		  document.getElementById("saveDistrictAccess").innerHTML = '';
		  document.getElementById("constituencyMessage").innerHTML = '';
		  document.getElementById("assemblyOrParliamentDiv").innerHTML = '';
		  document.getElementById("allConstituencyStateAccessDiv").innerHTML = '';
		  document.getElementById("allConstituencyAccessDiv").innerHTML = '';
		  document.getElementById("saveConstituencyAccess").innerHTML = '';
		  document.getElementById("countryMessage").innerHTML = '';
		  document.getElementById("stateMessage").innerHTML = '';
	 var  userE = document.getElementById("userId");
	 var userId =  userE.options[userE.selectedIndex].value;
	   if(userId!=0)
		 {
	 var jsObj=
		{		
				userId:userId,			
				task:"getAllAccessRegionDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
		}
	  
  }
  function getAssemblyConsDetailsByStateIdUserId(){
       $("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
       var  userE = document.getElementById("userId");
       var userId =  userE.options[userE.selectedIndex].value;
	   var  stateE = document.getElementById("stateConstituencyId");
       var stateId =  stateE.options[stateE.selectedIndex].value;
	   document.getElementById("allConstituencyAccessDiv").innerHTML = '';
	   if(stateId!= 0){
	   document.getElementById("constituencyMessage").innerHTML = '';
	   
	   var jsObj=
		{		stateId:stateId,
				userId:userId,			
				task:"getAssemblyConsDetailsByStateIdUserId"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
		}
  }
  function getParliConsDetailsByUserId(){
         var  userE = document.getElementById("userId");
         var userId =  userE.options[userE.selectedIndex].value;
		 var jsObj=
		{		
				userId:userId,			
				task:"getParliConsDetailsByUserId"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAllAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
  }
  
  function showStateSelect()
{    document.getElementById("allConstituencyAccessDiv").innerHTML ='';
	document.getElementById("allConstituencyStateAccessDiv").style.display = 'block';
	var val ="<input class=\"button\" type=\"button\" onclick=\"saveAssmbConstAccessDetails()\" value=\"Save\">";
	   document.getElementById("saveConstituencyAccess").innerHTML = val;
}

function hideStateSelect()
{  
      document.getElementById("constituencyMessage").innerHTML ='';
      document.getElementById("allConstituencyAccessDiv").innerHTML ='';
	  document.getElementById("allConstituencyStateAccessDiv").style.display = 'none';
	  var val ="<input class=\"button\" type=\"button\" onclick=\"saveParlConstAccessDetails()\" value=\"Save\">";
	  document.getElementById("saveConstituencyAccess").innerHTML = val;
}
function saveCountryAccessDetails(){
        document.getElementById("allStateAccessDiv").innerHTML ='';
        document.getElementById("saveStateAccess").innerHTML ='';
		$("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
	    document.getElementById("allDistrictAccessStateDiv").innerHTML ='';
		document.getElementById("allDistrictAccessDistrictDiv").innerHTML ='';
        document.getElementById("saveDistrictAccess").innerHTML ='';
		$("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
	    document.getElementById("assemblyOrParliamentDiv").innerHTML ='';
		document.getElementById("allConstituencyStateAccessDiv").innerHTML ='';
	    document.getElementById("allConstituencyAccessDiv").innerHTML ='';
		document.getElementById("saveConstituencyAccess").innerHTML ='';
		$("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
		
        document.getElementById("userMessage").innerHTML= '';
   var  userE = document.getElementById("userId");
   var userId =  userE.options[userE.selectedIndex].value;
      if(userId!= 0){
        var countryIds = "";
		var elements = document.getElementsByName("countryAccessCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				countryIds = countryIds+elements[i].value ;
				countryIds =countryIds+"," ;
			}
		}
		var jsObj=
        {		
				userId:userId,
                countryIds : countryIds,				
				task:"saveCountryAccessDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
		}
		else
		{
		  document.getElementById("userMessage").innerHTML="<b style=\"color: red;\"> Please select a User.</b>";
		}
}
function saveStateAccessDetails(){
        document.getElementById("allCountryAccessDiv").innerHTML ='';
        document.getElementById("saveCountryAccess").innerHTML ='';
	    document.getElementById("allDistrictAccessStateDiv").innerHTML ='';
		document.getElementById("allDistrictAccessDistrictDiv").innerHTML ='';
        document.getElementById("saveDistrictAccess").innerHTML ='';
		$("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
	    document.getElementById("assemblyOrParliamentDiv").innerHTML ='';
		document.getElementById("allConstituencyStateAccessDiv").innerHTML ='';
	    document.getElementById("allConstituencyAccessDiv").innerHTML ='';
		document.getElementById("saveConstituencyAccess").innerHTML ='';
		$("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
	   
       document.getElementById("userMessage").innerHTML = '';
    var  userE = document.getElementById("userId");
    var userId =  userE.options[userE.selectedIndex].value;
	   if(userId!= 0){
	    var stateIds = "";
		var elements = document.getElementsByName("stateAccessCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				stateIds = stateIds+elements[i].value;
				stateIds =stateIds+",";
			}
		}
		var jsObj=
        {		
				userId:userId,
                stateIds : stateIds,		
				task:"saveStateAccessDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
		}
		else{
		  document.getElementById("userMessage").innerHTML = "<b style=\"color: red;\"> Please select a User.</b>";
		}
}
function saveDistrictAccessDetails(){

        document.getElementById("allCountryAccessDiv").innerHTML ='';
        document.getElementById("saveCountryAccess").innerHTML ='';
		document.getElementById("allStateAccessDiv").innerHTML ='';
        document.getElementById("saveStateAccess").innerHTML ='';
		$("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
	    document.getElementById("assemblyOrParliamentDiv").innerHTML ='';
		document.getElementById("allConstituencyStateAccessDiv").innerHTML ='';
	    document.getElementById("allConstituencyAccessDiv").innerHTML ='';
		document.getElementById("saveConstituencyAccess").innerHTML ='';
		$("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
		
    var  userE = document.getElementById("userId");
    var userId =  userE.options[userE.selectedIndex].value;
	var districtIds = "";
		var elements = document.getElementsByName("districtAccessCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				districtIds = districtIds+elements[i].value;
				districtIds =districtIds+",";
			}
		}
	var stateEle = document.getElementById("districtId");
	 var stateId = stateEle.options[stateEle.selectedIndex].value;
   if(stateId!= 0 && userId!= 0){
		var jsObj=
        {		
				userId:userId,
                districtIds : districtIds,
                 stateId : stateId,				
				task:"saveDistrictAccessDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
	}
	else{
	    if(stateId == 0)
       document.getElementById("districtMessage").innerHTML = "<b style=\"color: red;\"> Please select a State.</b>";
	   if(userId == 0)
	   document.getElementById("userMessage").innerHTML = "<b style=\"color: red;\"> Please select a User.</b>";
	}
}
function saveAssmbConstAccessDetails(){

        document.getElementById("allCountryAccessDiv").innerHTML ='';
        document.getElementById("saveCountryAccess").innerHTML ='';
		document.getElementById("allStateAccessDiv").innerHTML ='';
        document.getElementById("saveStateAccess").innerHTML ='';
		$("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
        document.getElementById("allDistrictAccessStateDiv").innerHTML ='';
		document.getElementById("allDistrictAccessDistrictDiv").innerHTML ='';
        document.getElementById("saveDistrictAccess").innerHTML ='';
		$("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
		
	     document.getElementById("constituencyMessage").innerHTML = '';
        var  userE = document.getElementById("userId");
        var userId =  userE.options[userE.selectedIndex].value;
		var stateEle = document.getElementById("stateConstituencyId");
	    var stateId = stateEle.options[stateEle.selectedIndex].value;
	 if(stateId!= 0 && userId!=0){
		var constituencyIds = "";
		var elements = document.getElementsByName("assConstituencyAccessCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				constituencyIds = constituencyIds+elements[i].value;
				constituencyIds =constituencyIds+",";
			}
		}
		var jsObj=
        {		
				userId:userId,
                constituencyIds : constituencyIds,
                 stateId : stateId,				
				task:"saveAssmbConstAccessDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
	 }
	 else{
	  if(stateId == 0) 
       document.getElementById("constituencyMessage").innerHTML = "<b style=\"color: red;\"> Please select a State.</b>";
	   if(userId == 0)
	   document.getElementById("userMessage").innerHTML = "<b style=\"color: red;\"> Please select a User.</b>";
	
	 }

}
function saveParlConstAccessDetails(){

        document.getElementById("allCountryAccessDiv").innerHTML ='';
        document.getElementById("saveCountryAccess").innerHTML ='';
		document.getElementById("allStateAccessDiv").innerHTML ='';
        document.getElementById("saveStateAccess").innerHTML ='';
		$("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
        document.getElementById("allDistrictAccessStateDiv").innerHTML ='';
		document.getElementById("allDistrictAccessDistrictDiv").innerHTML ='';
        document.getElementById("saveDistrictAccess").innerHTML ='';
		$("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
     
	 var  userE = document.getElementById("userId");
        var userId =  userE.options[userE.selectedIndex].value;
		if(userId!=0){
		var constituencyIds = "";
		var elements = document.getElementsByName("parlConstituencyAccessCheckBox");
		for (i = 0; i < elements.length; i++){
			if(elements[i].checked == true){
				constituencyIds = constituencyIds+elements[i].value;
				constituencyIds =constituencyIds+",";
			}
		}
		var jsObj=
        {		
				userId:userId,
                constituencyIds : constituencyIds,			
				task:"saveParlConstAccessDetails"						
		};				
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveAccessRegionDetailAction.action?"+rparam;	
		callAjax(jsObj,url);
	 }
	 else{
	   document.getElementById("userMessage").innerHTML = "<b style=\"color: red;\"> Please select a User.</b>";
	
	 }
	 
}
 function callAjax(jsObj,url)
   {
	var myResults;	
	
	var callback = {			
	
	success : function( o )
	{
		try {
			if(o.responseText)
				myResults =YAHOO.lang.JSON.parse(o.responseText);	
			if(jsObj.task == "getAllAccessRegionDetails")
			{    
				showUserAccessDetails(myResults);
			}
			if(jsObj.task == "getCountryDetails")
			{    
				showCountryDetails(myResults);
			}
			if(jsObj.task == "getStateDetails")
			{    
				showStateDetails(myResults);
			}
			
			if(jsObj.task == "getStateDetailForDistrict")
			{    
				showStateDetailForDistrict(myResults);
			}
			if(jsObj.task == "getAllDistrictDetailByUserIdStateId")
			{    
				showDistrictDetails(myResults);
			}
			if(jsObj.task == "getStateDetailForConstituency")
			{    
				showStateDetailForConstituency(myResults);
			}
			if(jsObj.task == "getAssemblyConsDetailsByStateIdUserId")
			{    
				showAssemblyConstituencyDetails(myResults);
			}
			if(jsObj.task == "getParliConsDetailsByUserId")
			{
			    showParliamentConstituencyDetails(myResults);
			}
			if(jsObj.task == "saveCountryAccessDetails" || jsObj.task == "saveStateAccessDetails"){
			
			    showResponse(myResults,jsObj.task);
			}
			if(jsObj.task == "saveDistrictAccessDetails" || jsObj.task == "saveAssmbConstAccessDetails" || jsObj.task == "saveParlConstAccessDetails" ){
			    showResponse(myResults,jsObj.task);
			}

		}catch (e) {   		
		   	alert("Your Session Expired  Please LogIn");   
		}  
    },
    scope : this,
    failure : function( o ) {
     	alert( "Failed to load result" + o.status + " " + o.statusText);
         }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);	
}
function showCountryDetails(results){
  
   var allcountryaccess = document.getElementById("allCountryAccessDiv");
	  	var str='';		 
		  		for(var i in results.country)
				{		  			
		  			str+='	<tr>';
                    if(results.country[i].message=="AVAILABLE"){
	  				str+='		<td><input name="countryAccessCheckBox" value="'+results.country[i].countryId+'" type="checkbox" checked="checked"   id=" '+results.country[i].countryId+' ">'+ results.country[i].countryName +'</input></td>';
	  			}else{
	  				str+='		<td><input name="countryAccessCheckBox" type="checkbox"  value="'+results.country[i].countryId+'"  id=" '+results.country[i].countryId+' ">'+ results.country[i].countryName +'</input></td>';	
	  			}					
		  			str+='	</tr>';
				}	
		  		
	  	allcountryaccess.innerHTML = str;
}

function showStateDetails(results){
    if(results.state.length <= 15)
	  $("#allStateAccessDiv").css({"height":"","width":"","overflow":""});
	  else
    $("#allStateAccessDiv").css({"height":"40em","width":"18em","overflow":"auto"});
	if(results.state.length <=0)
	 document.getElementById("saveStateAccess").innerHTML='';
   var allstateaccess = document.getElementById("allStateAccessDiv");
	  	var str='';	
		  		for(var i in results.state)
				{		  			
		  			str+='	<tr>';
                    if(results.state[i].message=="AVAILABLE"){
	  				str+='		<td><input name="stateAccessCheckBox" value="'+results.state[i].stateId+'" type="checkbox" checked="checked"   id=" '+results.state[i].stateId+' ">'+ results.state[i].stateName +'</input></td><br />';
	  			}else{
	  				str+='		<td><input name="stateAccessCheckBox" type="checkbox"  value="'+results.state[i].stateId+'"  id=" '+results.state[i].stateId+' ">'+ results.state[i].stateName +'</input></td><br />';	
	  			}					
		  			str+='	</tr>';
				}	
		  		
	  	allstateaccess.innerHTML = str;
}

function showStateDetailForDistrict(results){
      var elmt = document.getElementById("districtId");
	     var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select State";
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
		if(results.state.length <=0)
		document.getElementById("saveDistrictAccess").style.display = 'none';
		else {document.getElementById("saveDistrictAccess").style.display = 'block';}
		for(var i in results.state)
	  {
		var option = document.createElement('option');
		option.value=results.state[i].stateId;
		option.text=results.state[i].stateName;
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

function showDistrictDetails(results){
      if(results.district.length <= 15)
	  $("#allDistrictAccessDistrictDiv").css({"height":"","width":"","overflow":""});
	  else
     $("#allDistrictAccessDistrictDiv").css({"height":"40em","width":"18em","overflow":"auto"});
   var alldistrictaccess = document.getElementById("allDistrictAccessDistrictDiv");
	  	var str='';	
		         results.district.sort(function(a, b){
                    var nameA=a.districtName.toLowerCase(), nameB=b.districtName.toLowerCase()
                   if (nameA < nameB) //sort string ascending
                     return -1
                    if (nameA > nameB)
                      return 1
                      return 0 //default return value (no sorting)
                     });
					 if(results.district.length <=0)
	                   document.getElementById("saveDistrictAccess").style.display = 'none';
		else {document.getElementById("saveDistrictAccess").style.display = 'block';}
		  		for(var i in results.district)
				{		  			
		  			str+='	<tr>';
                    if(results.district[i].message=="AVAILABLE"){
	  				str+='		<td><input name="districtAccessCheckBox" value="'+results.district[i].districtId+'" type="checkbox" checked="checked"   id=" '+results.district[i].districtId+' ">'+ results.district[i].districtName +'</input></td><br />';
	  			}else{
	  				str+='		<td><input name="districtAccessCheckBox" type="checkbox"  value="'+results.district[i].districtId+'"  id=" '+results.district[i].districtId+' ">'+ results.district[i].districtName +'</input></td><br />';	
	  			}					
		  			str+='	</tr>';
				}	
		  		
	  	alldistrictaccess.innerHTML = str;
}
function showStateDetailForConstituency(results){
      var elmt = document.getElementById("stateConstituencyId");
	  var option1 = document.createElement('option');
		 option1.value= 0;
		option1.text= "Select State";
		
		try
		{
			elmt.add(option1,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option1); // IE only
		}
		if(results.state.length <=0)
	               document.getElementById("saveConstituencyAccess").style.display = 'none';
		else {document.getElementById("saveConstituencyAccess").style.display = 'block';}
		for(var i in results.state)
	  {
		var option = document.createElement('option');
		option.value=results.state[i].stateId;
		option.text=results.state[i].stateName;
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
   
 function showAssemblyConstituencyDetails(results){
      if(results.constituency.length <= 15)
	  $("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
	  else
     $("#allConstituencyAccessDiv").css({"height":"40em","width":"18em","overflow":"auto"});
	    results.constituency.sort(function(a, b){
                    var nameA=a.constituencyName.toLowerCase(), nameB=b.constituencyName.toLowerCase()
                   if (nameA < nameB) //sort string ascending
                     return -1
                    if (nameA > nameB)
                      return 1
                      return 0 //default return value (no sorting)
                     });
		if(results.constituency.length <=0)
		document.getElementById("saveConstituencyAccess").style.display = 'none';
		else {document.getElementById("saveConstituencyAccess").style.display = 'block';}
 var allconstituencyaccess = document.getElementById("allConstituencyAccessDiv");
	  	var str='';	
		  		for(var i in results.constituency)
				{		  			
		  			str+='	<tr>';
                    if(results.constituency[i].message=="AVAILABLE"){
	  				str+='		<td><input name="assConstituencyAccessCheckBox" value="'+results.constituency[i].constituencyId+'" type="checkbox" checked="checked"   id=" '+results.constituency[i].constituencyId+' ">'+ results.constituency[i].constituencyName +'</input></td><br />';
	  			}else{
	  				str+='		<td><input name="assConstituencyAccessCheckBox" type="checkbox"  value="'+results.constituency[i].constituencyId+'"  id=" '+results.constituency[i].constituencyId+' ">'+ results.constituency[i].constituencyName +'</input></td><br />';	
	  			}					
		  			str+='	</tr>';
				}	
		  		
	  	allconstituencyaccess.innerHTML = str;
 
 }
 function showParliamentConstituencyDetails(results){
 
     if(results.constituency.length <= 15)
	  $("#allConstituencyAccessDiv").css({"height":"","width":"","overflow":""});
	  else
     $("#allConstituencyAccessDiv").css({"height":"40em","width":"18em","overflow":"auto"});
     var allconstituencyaccess = document.getElementById("allConstituencyAccessDiv");
	     results.constituency.sort(function(a, b){
                    var nameA=a.constituencyName.toLowerCase(), nameB=b.constituencyName.toLowerCase()
                   if (nameA < nameB) //sort string ascending
                     return -1
                    if (nameA > nameB)
                      return 1
                      return 0 //default return value (no sorting)
                     });
			  if(results.constituency.length <=0)
	               document.getElementById("saveConstituencyAccess").style.display = 'none';
		else {document.getElementById("saveConstituencyAccess").style.display = 'block';}
	  	var str='';	
		  		for(var i in results.constituency)
				{		  			
		  			str+='	<tr>';
                    if(results.constituency[i].message=="AVAILABLE"){
	  				str+='		<td><input name="parlConstituencyAccessCheckBox" value="'+results.constituency[i].constituencyId+'" type="checkbox" checked="checked"   id=" '+results.constituency[i].constituencyId+' ">'+ results.constituency[i].constituencyName +'</input></td><br />';
	  			}else{
	  				str+='		<td><input name="parlConstituencyAccessCheckBox" type="checkbox"  value="'+results.constituency[i].constituencyId+'"  id=" '+results.constituency[i].constituencyId+' ">'+ results.constituency[i].constituencyName +'</input></td><br />';	
	  			}					
		  			str+='	</tr>';
				}	
		  		
	  	allconstituencyaccess.innerHTML = str;
 
 }
 
 function showResponse(results,task){
 
 if(task == "saveCountryAccessDetails")
   {
      var str ='';
     if(results.message=="SUCCESSFULLY_SAVED")
	 str = str+"<b style=\"color: green;\"> Successfully Saved.</b>";
	  else
	  str = str+"<b style=\"color: red;\"> Unable to Save.</b>";
       document.getElementById("countryMessage").innerHTML = str ;
   }
 if(task == "saveStateAccessDetails" )
   {
     var str ='';
     if(results.message=="SUCCESSFULLY_SAVED")
	 str = str+"<b style=\"color: green;\"> Successfully Saved.</b>";
	  else
	  str = str+"<b style=\"color: red;\"> Unable to Save.</b>";
	document.getElementById("stateMessage").innerHTML = str ;
   }
 if(task == "saveDistrictAccessDetails")
   {
    var str ='';
     if(results.message=="SUCCESSFULLY_SAVED")
	 str = str+"<b style=\"color: green;\"> Successfully Saved.</b>";
	  else
	  str = str+"<b style=\"color: red;\"> Unable to Save.</b>";
	document.getElementById("districtMessage").innerHTML = str;
   }
 if(task == "saveAssmbConstAccessDetails")
   {
     var str ='';
     if(results.message=="SUCCESSFULLY_SAVED")
	 str = str+"<b style=\"color: green;\"> Successfully Saved.</b>";
	  else
	  str = str+"<b style=\"color: red;\"> Unable to Save.</b>";
	document.getElementById("constituencyMessage").innerHTML = str;
   }
 if(task == "saveParlConstAccessDetails")
   {
     var str ='';
     if(results.message=="SUCCESSFULLY_SAVED")
	 str = str+"<b style=\"color: green;\"> Successfully Saved.</b>";
	  else
	  str = str+"<b style=\"color: red;\"> Unable to Save.</b>";
	document.getElementById("constituencyMessage").innerHTML = str;
   }
 }


</script>
</head>
<body>
 <h2>User Region Access Info</h2> 
 <table>
     <tr><div id ="userMessage"></div></tr>
	<tr>
		<td class="headingID">
				All Users		
		</td>
		<td>
			<select id="userId" onchange="getAllAccessRegionDetails()">
				<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
						<option value="${allUsers.id}"> ${allUsers.name} </option>	
				</c:forEach>
			</select>
		</td>
	</tr>
 </table>
 <table style="padding-top:25px;"> 
    <tr>
       <td valign="top">
	     <div id="managingCountryMainDiv">
				<fieldset class="f2">
					<legend class="l2">Country Access</legend>
                    <table>
					   <tr><div id="countryMessage"></div></tr>
	                  <tr>
					     <td>
	                      
						 </td>
	                      <th >Country</th>
	                      <td ><input type="button" class="edit" value="Edit" onclick="editCountryAccess()" />
	                      </td>				
		
	                  </tr>
                    </table>
                    <table id="countryaccess">
	                  <tr>
	                    <td>
		                  <div id="allCountryAccessDiv"></div>
	                    </td>										
	                  </tr>								
	                  <tr>
	                    <td>
		                  <div id="saveCountryAccess"></div>
	                    </td>										
	                  </tr>
                    </table>
		        </fieldset>
		  </div>
       </td>
	   <td valign="top">
	      <div id="managingStateMainDiv">
				<fieldset class="f1">
				  <legend class="l2">State Access</legend>
                    <table>
					   <tr><div id="stateMessage"></div></tr>
                       <tr>
	                     <th >State</th>	
	                     <td ><input type="button" class="edit" value="Edit"  onclick="editStateAccess()" />
	                     </td>	
                       </tr>
                    </table>
                    <table id="stateaccess">
	                   <tr>
	                     <td>
		                   <div id="allStateAccessDiv"></div>
	                     </td>										
	                   </tr>								
	                   <tr>
	                     <td>
		                   <div id="saveStateAccess"></div>
	                     </td>										
	                   </tr>
                    </table>
				</fieldset>
		  </div>
       </td>
    </tr>
	<tr>
	   <td valign="top">
	      <div id="managingDistrictMainDiv">
				<fieldset class="f2">
				   <legend class="l2">District Access</legend>
                   <table>
				      <tr><div id="districtMessage"></div></tr>
                      <tr>
	                    <th >District</th>
	                    <td ><input type="button" class="edit" value="Edit" onclick="editDistrictAccess()" />
	                    </td>					
	                  </tr>
                   </table>
                   <table id="districtaccess">
	                  <tr>
	                   <td>
		                 <div id="allDistrictAccessStateDiv"></div>
	                   </td>										
	                  </tr>
                      <tr>
	                   <td>
		                 <div id="allDistrictAccessDistrictDiv"></div>
	                   </td>										
	                  </tr>					  
	                  <tr>
	                    <td>
		                   <div id="saveDistrictAccess"></div>
	                    </td>										
	                  </tr>
                   </table>
				</fieldset>
		  </div>
     </td>
	 <td valign="top">
	      <div id="managingConstituencyMainDiv">
				<fieldset class="f1">
				   <legend class="l2">Constituency Access</legend>
                   <table>
				     <tr><div id="constituencyMessage"></div></tr>
                     <tr>
                       <th>Constituency</th>
                       <td><input type="button" class="edit" value="Edit" onclick="editConstituencyAccess()" />
	                   </td>					
		
	                 </tr>
                   </table>  
                   <table id="constituencyaccess">
				     <tr>
		                 <div id="assemblyOrParliamentDiv"></div>										
	                 </tr>
				     <tr>
	                   <td>
		                 <div id="allConstituencyStateAccessDiv"></div>
	                   </td>										
	                 </tr>	
	                 <tr>
	                   <td>
		                 <div id="allConstituencyAccessDiv" ></div>
	                   </td>										
	                 </tr>								
	                 <tr>
	                   <td>
		                 <div id="saveConstituencyAccess"></div>
	                   </td>										
	                 </tr>
                   </table>
				</fieldset>
		  </div>
	 </td>
	</tr>
  </table>
 </body>         
</html>