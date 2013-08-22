<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>
<HTML>
<HEAD>
<link  href="css/main.css" rel="stylesheet" type="text/css" />
<TITLE>Party Performance Report In Each Booth</TITLE>

<style type="text/css">

#errorMessage
{
	color:red;
	font-weight:bold;
	font-size:12px;
}
#partyboothresultsheading{
	background-color:#0B3861;
	height:30px;
	width:224px;
	 color: #ffffff;
    font-family: times New Roman;
    font-size: 24px;
    font-weight: bold;
    margin-top: 10px;
	margin-left:auto;
	margin-right:auto;
	 margin-bottom: -5px;
    }
#tableDiv{
	margin-left: 75px;
}
th.thstyle{
	 color: #724400;
	 font-size:13px
}
 select.selectstyle{
 width:150px;
 }
 #btnStyle{
	background-color:#0B3861;
	border:none;
    color: #FFFFFF;
    font-size: 15px;
    font-weight: bold;
    height: 31px;
  
    width: 103px;
 }
</style>

<!-- Dependencies -->
<script src="http://yui.yahooapis.com/2.7.0/build/yahoo/yahoo-min.js"></script>

<!-- Source file -->
<script src="http://yui.yahooapis.com/2.7.0/build/json/json-min.js"></script>
<script src="js/partyBoothResults/boothResults.js" ></script>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript">

var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("global_ErrorMessages");
		String electionTypeMsg = rb.getString("electionTypeMsg");
		String electionYearMsg = rb.getString("electionYearMsg");
		String ConstituencyNameMsg = rb.getString("ConstituencyNameMsg");
		String partyNameMsg = rb.getString("partyNameMsg");
			%> }
	var resultYears;
	function showAjaxDiv()
	{
		var elmt = document.getElementById("ajaxImg");
		if(elmt)
			elmt.style.display = "block";

		return true;
	}


	function showImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display = 'block';
		setTimeout('stopImage2()',500);
	}

	function showImageC()
	{
		var img = document.getElementById("cAjaxImg");
		img.style.display = 'block';
		//setTimeout('stopImageC()',1000);
	}

	function stopImage2()
	{
		var img = document.getElementById("ajaxImg2");
		img.style.display='none';
	}

	function stopImageC()
	{
		var img = document.getElementById("cAjaxImg");
		img.style.display='none';
	}

	
	function clearConstituencys()
	{
			clearOptionsListForSelectElmtId("ConstituencyName1");
			createSelectOptionsForSelectElmtId("ConstituencyName1");
		
	}

	function clearpartyNames()
	{
			clearOptionsListForSelectElmtId("partyName1");
			createSelectOptionsForSelectElmtId("partyName1");
	}

	function removeErrorMessage()
		{
			if(document.getElementById("errorMessage"))
			{
				document.getElementById("errorMessage").innerHTML="";
			}
		}

	
	function validateAndForwardToAction()
	{
		    var errorFlag=0;
			var message="";	
			
			if(document.getElementById("electionType1").value==0)
			{
				message+='<%=electionTypeMsg %>';
				message+='<br/>';
				errorFlag=1;
			}

			if(document.getElementById("electionYear1").value==0)
			{
				message+='<%=electionYearMsg %>';
				message+='<br/>';
				errorFlag=1;
			}

			if(document.getElementById("ConstituencyName1").value==0){
				message+='<%=ConstituencyNameMsg %>';
				message+='<br/>';
				errorFlag=1;
			}
			
			if(document.getElementById("partyName1").value==0)
			{
				message+='<%=partyNameMsg %>';
				message+='<br/>';
				errorFlag=1;
				electionFlag=1;
			}
			
			if(errorFlag==1){
				document.getElementById("errorMessage").innerHTML = message;
				return false;
			}
			
			else
			{
				showAjaxDiv();
				document.BoothPerformanceReport.action="partyBoothResult2Action.action";
				document.BoothPerformanceReport.method="post"
				document.BoothPerformanceReport.submit();
				return true;							
			}	
      }
	  
	  function getParlYears(){
	     $("#typeAjaxImg").show();
         var jsObj=
             {
			     stateId:0,
				 electionType:1,
				 task:"years"	
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getOptsForBoothResultsReportAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
     }
	 
	 function getAssemblyStates(){
	    $("#typeAjaxImg").show();
	    var jsObj=
             {
				 task:"states"	
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getOptsForBoothResultsReportAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
	 }
	 
	  function getAssemblyYears(){
	  clearOptions(false,true,true,true);
	  if($("#electionState option:selected").val() == 0 || $("#electionState option:selected").val() == "0")
	   return;
	    $("#stateAjaxImg").show();
         var jsObj=
             {
			     stateId:$("#electionState option:selected").val(),
				 electionType:2,
				 task:"years"	
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getOptsForBoothResultsReportAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
     }
	 
	 function getSelectedConsituencies(){
	     clearOptions(false,false,true,true);
		 if($("#electionYear1 option:selected").val() == 0 || $("#electionYear1 option:selected").val() == "0")
	        return;
			$("#cAjaxImg").show();
	     var electionId = 0;
		 var year = $("#electionYear1 option:selected").val();
		 for(var i in resultYears){
		  if(parseInt(resultYears[i].name) == parseInt(year))
		    electionId = resultYears[i].id;
		 }
		var jsObj=
             {
			     electionId:electionId,				 
				 task:"constituencies"	
			 }
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getOptsForBoothResultsReportAction.action?"+rparam;

		callAjaxToGetData(jsObj, url);
	 
	 }
	 
    function callAjaxToGetData(jsObj,url)
	  {			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								if(jsObj.task == "states"){
									buildSelectOptions(myResults,"electionState");
									$("#typeAjaxImg").hide();
								}
								else if(jsObj.task == "years"){
								      resultYears = myResults;
								      buildNewSelectOptions(myResults,"electionYear1");
									  $("#typeAjaxImg").hide();
									  $("#stateAjaxImg").hide();
								}
								else if(jsObj.task == "constituencies"){
								    buildSelectOptions(myResults,"ConstituencyName1");
									$("#cAjaxImg").hide();
								}
							
							}catch (e) {   
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
	
	function buildSelectOptions(result,id){
       $("#"+id).find("option").remove();
       $("#"+id).append('<option value=0>Select</option>');
       if(result != null && result.length >0){
         for(var i in result){
	       if(result[i].id != 0)
             $("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	     }
	   }
    }
	
	function buildNewSelectOptions(result,id){
       $("#"+id).find("option").remove();
       $("#"+id).append('<option value=0>Select</option>');
       if(result != null && result.length >0){
         for(var i in result){
	       if(result[i].id != 0)
             $("#"+id).append('<option value='+result[i].name+'>'+result[i].name+'</option>');
	     }
	   }
    }
	
	function clearOptions(state,year,consti,party){
	  if(state){
	   $("#electionState").find("option").remove();
	   $("#electionState").append('<option value=0>Select</option>');
	  }
	  if(year){
       
	   $("#electionYear1").find("option").remove();
	   $("#electionYear1").append('<option value=0>Select</option>');
	  }
	  if(consti){
       
	   $("#ConstituencyName1").find("option").remove();
	   $("#ConstituencyName1").append('<option value=0>Select</option>');
	  }
	  if(party){
       
	   $("#partyName1").find("option").remove();
	   $("#partyName1").append('<option value=0>Select</option>');
	  }
	}
	
	function getStatesOrYears(){
	  clearOptions(true,true,true,true);
	 var electionType =  $("#electionType1 option:selected").val();
	 
	 if(electionType == 0)
	   return ;
	 else if(electionType == 1){
	    $("#staterow").hide();
	   getParlYears();
	 }else if(electionType == 2){
	    $("#staterow").show();
	    getAssemblyStates();
	 }
	}
	// window.history.forward(1);
</script>
</HEAD>
<body>
<div style="margin-left:auto;margin-right:auto;width:900px;">
<div style="background:#ffffff;">
	<s:url action="partyBoothResult1AjaxAction" id="getConsituencyURL" />
	<s:url action="partyBoothResultPartyAjaxAction" id="getPartyURL" />
	
	<div  id= "partyboothresultsheading">
	<table border="0" cellpadding="3" cellspacing="0">          
		<tr>
			<td><div>Party Booth Results</div></td>
	       
	   </tr>
		</table>
	</div>
	<table>
	  <tr>
	   <th align="left" style="padding-left:298px;padding-top: 22px;">
		<div id="errorMessage"></div>
	   </th><td></td>
	  </tr>
	</table>
	 <s:form name="BoothPerformanceReport">
	 <div id="tableDiv">
	   <table cellpadding="10px" cellspacing="0px" style="border: 2px solid #0B3861;background-color: #F2F0EE;margin-left:auto;margin-right:auto;width:40%;text-align:left;">
		 <tr id="ElectionTyperow">
			<th class="thstyle">Election type</th>
		      <td> <s:select cssClass="selectstyle" label="Election Type"  name="electionType" list="electionTypes" listKey="id" listValue="name" headerKey="0" headerValue="Select" id="electionType1" onchange="getStatesOrYears();removeErrorMessage()" theme="simple"/>
			  </td>
			  <td style="border:none;">
				<img id="typeAjaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
		   </tr>
		   <tr id="staterow">
			<th class="thstyle">State</th>
		      <td> <select name="electionState" class="selectstyle" id="electionState" onchange="getAssemblyYears()" >
				     <option value="0">Select<option>
				  </select>
			  </td>
			  <td style="border:none;">
				<img id="stateAjaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
		   </tr>
           <tr id="ElectionYearrow">
			 <th class="thstyle">Election Year</th>
			  <td>
			      <select name="electionYear" class="selectstyle" id="electionYear1" onchange="getSelectedConsituencies(),removeErrorMessage()" >
				     <option value="0">Select<option>
				  </select>
			  </td>

				<td style="border:none;">
				<img id="cAjaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
			</tr>
			<tr id="constituencyRow">
			  <th class="thstyle">Constituency</th>
				<td width="150px"><s:select cssClass="selectstyle" label="Constituency" name="constituencyName" 
				list="%{#{'0':'Select'}}" theme="simple" id="ConstituencyName1"  onchange="getConstituenciesList(this.form,'%{getPartyURL}'),showImage2(),clearpartyNames(),removeErrorMessage()"/>
				</td>

				<td style="border:none;">
				<img id="ajaxImg2" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
   			</tr>

			<tr id="partyRow">
				<th class="thstyle">Party</th>
				 <td><s:select cssClass="selectstyle" label="Party" name="partyName" id="partyName1" 		                         onchange="removeErrorMessage()"
					  list="%{#{'0':'Select'}}" theme="simple"/>			
				 </td>
				 <td style="border:none;">
					<img id="ajaxImg" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
				</td>
			</tr>
		</table>
		</div>
		<div style="background:#ffffff;text-align:center;padding-top:8px;margin-bottom: 10px;">
		
					<input id="btnStyle" type="button" id="subbutton" value="View Report" onclick="validateAndForwardToAction()" />
				
		</div>
	</table>
</s:form>
</div>
</div>
</body>
</HTML>
