<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ministers Analysis</title>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">

<style type="text/css">
.main-mbg {
    background-color: #06ABEA;
    border-radius: 7px 7px 7px 7px;
    color: #FFFFFF;
    font: bold 14px/35px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 35px;
    padding-left: 13px;
    text-align: left;
    text-transform: uppercase;
    width: 977px;
}
table.searchresultsTable {
    border-collapse: collapse;
    border-color: #666666;
    border-width: 1px;
    color: #333333;
    font-family: verdana,arial,sans-serif;
    font-size: 11px;
    margin-top: 10px;
}
table.searchresultsTable th {
    background-color: #C4DEFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable td {
    background-color: #FFFFFF;
    border-color: #666666;
    border-style: solid;
    border-width: 1px;
}
table.searchresultsTable, table.searchresultsTable * td, table.searchresultsTable * th {
    -moz-border-bottom-colors: none;
    -moz-border-image: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border-color: #D3D3D3 !important;
    border-style: solid;
    border-width: 1px;
}
.dashBoardtabsDiv {
    background: none repeat scroll 0 0 #EBE4F2;
    border: 1px solid #CDCDCD;
    border-radius: 5px 5px 5px 5px;
    font-family: verdana;
    font-weight: bold;
	margin-left: 247px;
    margin-top: 13px;
    padding: 10px 7px 10px;
    text-align: left;
    width: 52%;
}

.dashBoardtabsDivSelected
	{
	background :skyBlue; color: #000000;
	border-radius:10px;
	-moz-border-radius:10px;
	}
.dashBoardtabsDiv a {
    color: #000000;
    cursor: pointer;
    font-family: verdana;
    font-weight: bold;
    padding: 5px 16px 5px;
    text-decoration: none;
}
</style>
<script type="text/javascript">

var qtype = "minis";
function getElectionYears(electionType)
{
   stateId = 1;
   if(electionType == "Assembly")
   {
	var stateEle = document.getElementById("stateListId");
    stateId = stateEle.options[stateEle.selectedIndex].value;
	document.getElementById("showData").innerHTML = "";
    document.getElementById("keyCandidatesData").innerHTML = "";
	if(stateId == 0)
	  {
	    removeData("yearSelId");
        addData("yearSelId");
	    return ;
	  }
   }
	removeData("yearSelId");
	var jObj = {
			stateId : stateId,
		electionType: electionType,
				task: 'getElectionYearsForAState'
				};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "electionYearsForstateAndElectionTypeAction.action?"+rparam;
	callAjax(jObj,url);
}
  function showHidsState()
    {
      if(document.getElementById("state").checked == true)
	      document.getElementById("showHideState").style.display ="block";
	  else
	      document.getElementById("showHideState").style.display ="none";
    }
	function getAllStates()
   {
    
      
    var jsObj =
		{ 
            time : new Date().getTime(),
			eleType: 2,
			task:"getStatesForAssign"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getElectionYearsBasedOnElectionTypeAction.action?"+rparam;						
	callAjax(jsObj,url);
  }
  function buildDataTable(result,id)
  {
     var str = '';
	 if(id == "showData")
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;"><td> Ministers Performance : </td></tr></table>';
	 else
	 str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;"><td> Important Candidates Performance : </td></tr></table>';
	 if(result.length > 0)
	 {
	   str+='<table class="searchresultsTable" style="width:975px;margin-left:10px;text-align:center;">';
	   str+='   <tr style="width:100%;">';
	   str+='      <th style="width:13%;">Candidate Name</th>';
	   if(id == "showData")
	   {
	     str+='      <th style="width:13%;">Ministry</th>';
	   }
	  if(result[0].currentResult.isPartial == 1)
	  {
	   str+='      <th  style="width:35%;">';
	   str+='         <table style="width:100%;">';
	   str+='             <tr style="width:100%;"><th colspan="3" style="width:100%;border:0px;color: rgb(0, 87, 144);">'+result[0].currentResult.year+'</th></tr>';
	   str+='             <tr style="width:100%;">';
	   str+='                  <th style="width:13%;border:0px;">Party</th>';
	   str+='                  <th style="width:33%;border:0px;">Constituency</th>';
	   str+='                  <th style="width:33%;border:0px;">Status</th>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </th>';
	  }
	  else
	  {
	   str+='      <th style="width:40%;">';
	   str+='         <table  style="width:100%;" >';
	   str+='             <tr style="width:100%;"><th colspan="5"  style="width:100%;border:0px;color: rgb(0, 87, 144);">'+result[0].currentResult.year+'</th></tr>';
	   str+='             <tr style="width:100%;">';
	   str+='                  <th  style="width:13%;border:0px;">Party</th>';
	   str+='                  <th  style="width:25%;border:0px;">Constituency</th>';
	   str+='                  <th  style="width:15%;border:0px;">Votes Earned</th>';
	   str+='                  <th  style="width:15%;border:0px;">Votes Percentage</th>';
	   str+='                  <th  style="width:20%;border:0px;">Result</th>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </th>';
	  }
      if(result[0].previousResult.isPartial == 1)
	  {
	   str+='      <th style="width:35%;">';
	   str+='         <table style="width:100%;">';
	   str+='             <tr  style="width:100%;"><th colspan="3" style="width:100%;border:0px;color: rgb(0, 87, 144);">'+result[0].previousResult.year+'</th></tr>';
	   str+='             <tr  style="width:100%;">';
	   str+='                  <th style="width:13%;border:0px;">Party</th>';
	   str+='                  <th style="width:33%;border:0px;">Constituency</th>';
	   str+='                  <th style="width:33%;border:0px;">Status</th>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </th>';
	  }
	  else
	  {
	   str+='      <th style="width:35%;">';
	   str+='         <table style="width:100%;">';
	   str+='             <tr style="width:100%;"><th colspan="5" style="width:100%;border:0px;color: rgb(0, 87, 144);">'+result[0].previousResult.year+'</th></tr>';
	   str+='             <tr style="width:100%;">';
	   str+='                  <th style="width:13%;border:0px;">Party</th>';
	   str+='                  <th style="width:25%;border:0px;">Constituency</th>';
	   str+='                  <th style="width:15%;border:0px;">Votes Earned</th>';
	   str+='                  <th style="width:15%;border:0px;">Votes Percentage</th>';
	   str+='                  <th style="width:20%;border:0px;">Result</th>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </th>';
	  }	   	  
	  str+='   </tr>';
	  
	  
	  for(var i in result)
	   {
	    
	   str+='   <tr style="width:100%;">';
	   str+='      <td style="width:13%;"><a href="candidateElectionResultsAction.action?candidateId='+result[i].candidateId+' ">'+result[i].candidateName+'</a></td>';
	   if(id == "showData")
	   {
	     str+='      <td style="width:13%;">'+result[i].positionName+'</td>';
	   }
	 if(result[i].currentResult.positionManagementVOList.length >0)
	 {
	  if(result[0].currentResult.isPartial == 1)
	  {
	   str+='      <td style="width:35%;">';
	   str+='         <table style="width:100%;">';
	   for(var j in result[i].currentResult.positionManagementVOList)
	     {
	      str+='             <tr style="width:100%;">';
	      str+='                  <td style="width:13%;border:0px;"><a href="partyPageAction.action?partyId='+result[i].currentResult.partyId+' ">'+result[i].currentResult.partyName+'</a></td>';
	      str+='                  <td style="width:33%;border:0px;"><a href="constituencyPageAction.action?constituencyId='+result[i].currentResult.positionManagementVOList[j].constituencyId+'">'+result[i].currentResult.positionManagementVOList[j].constituencyName+'</a></td>';
	      str+='                  <td style="width:33%;border:0px;">'+result[i].currentResult.positionManagementVOList[j].result+'</td>';
	      str+='             </tr>';
		 }
	   str+='         </table>';
	   str+='      </td>';
	  }
	  else
	  {
	   str+='      <td style="width:45%;">';
	   str+='         <table style="width:100%;">';
	  for(var j in result[i].currentResult.positionManagementVOList)
	    {
	     str+='             <tr style="width:100%;">';
	     str+='                  <td  style="width:13%;border:0px;"><a href="partyPageAction.action?partyId='+result[i].currentResult.partyId+' ">'+result[i].currentResult.partyName+'</a></td>';
	     str+='                  <td  style="width:25%;border:0px;"><a href="constituencyPageAction.action?constituencyId='+result[i].currentResult.positionManagementVOList[j].constituencyId+'">'+result[i].currentResult.positionManagementVOList[j].constituencyName+'</a></td>';
	     str+='                  <td  style="width:15%;border:0px;">'+result[i].currentResult.positionManagementVOList[j].votesEarned+'</td>';
	     str+='                  <td  style="width:15%;border:0px;">'+result[i].currentResult.positionManagementVOList[j].votesPercengate+'</td>';
		 if(result[i].currentResult.positionManagementVOList[j].rank == 1)
	        str+='                  <td  style="width:20%;border:0px;">Won</td>';
		 else
		    str+='                  <td  style="width:20%;border:0px;">Lost</td>';
	     str+='             </tr>';
	    }
	   str+='         </table>';
	   str+='      </td>';
	  }
	 }
	 else
	 {
	   if(result[0].currentResult.isPartial == 1)
	  {
	   str+='      <td  style="width:35%;">';
	   str+='         <table  style="width:100%;">';
	   str+='             <tr  style="width:100%;">';
	   str+='                  <td  style="width:33%;border:0px;">--</td>';
	   str+='                  <td style="width:33%;border:0px;">--</td>';
	   str+='                  <td style="width:33%;border:0px;">--</td>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </td>';
	  }
	  else
	  {
	   str+='      <td  style="width:45%;">';
	   str+='         <table  style="width:100%;">';
	   str+='             <tr  style="width:100%;">';
	   str+='                  <td  style="width:25%;border:0px;">--</td>';
	   str+='                  <td style="width:25%;border:0px;">--</td>';
	   str+='                  <td style="width:15%;border:0px;">--</td>';
	   str+='                  <td style="width:15%;border:0px;">--</td>';
	   str+='                  <td style="width:20%;border:0px;">--</td>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </td>';
	  }
	 
	 }
	 
	 if(result[i].previousResult.positionManagementVOList.length >0)
	 {
	  if(result[0].previousResult.isPartial == 1)
	  {
	   str+='      <td  style="width:35%;">';
	   str+='         <table  style="width:100%;">';
	   for(var j in result[i].previousResult.positionManagementVOList)
	     {
	      str+='             <tr  style="width:100%;">';
	      str+='                  <td  style="width:13%;border:0px;"><a href="partyPageAction.action?partyId='+result[i].previousResult.partyId+' ">'+result[i].previousResult.partyName+'</a></td>';
	      str+='                  <td  style="width:33%;border:0px;"><a href="constituencyPageAction.action?constituencyId='+result[i].previousResult.positionManagementVOList[j].constituencyId+'">'+result[i].previousResult.positionManagementVOList[j].constituencyName+'</a></td>';
	      str+='                  <td  style="width:33%;border:0px;">'+result[i].previousResult.positionManagementVOList[j].result+'</td>';
	      str+='             </tr>';
		 }
	   str+='         </table>';
	   str+='      </td>';
	  }
	  else
	  {
	   str+='      <td  style="width:45%;">';
	   str+='         <table  style="width:100%;">';
	  for(var j in result[i].previousResult.positionManagementVOList)
	    {
	     str+='             <tr  style="width:100%;">';
	     str+='                  <td  style="width:13%;border:0px;"><a href="partyPageAction.action?partyId='+result[i].previousResult.partyId+' ">'+result[i].previousResult.partyName+'</a></td>';
	     str+='                  <td  style="width:25%;border:0px;"><a href="constituencyPageAction.action?constituencyId='+result[i].previousResult.positionManagementVOList[j].constituencyId+'">'+result[i].previousResult.positionManagementVOList[j].constituencyName+'</a></td>';
	     str+='                  <td  style="width:15%;border:0px;">'+result[i].previousResult.positionManagementVOList[j].votesEarned+'</td>';
	     str+='                  <td style="width:15%;border:0px;">'+result[i].previousResult.positionManagementVOList[j].votesPercengate+'</td>';
		 if(result[i].previousResult.positionManagementVOList[j].rank == 1)
	        str+='                  <td style="width:20%;border:0px;">Won</td>';
		 else
		    str+='                  <td style="width:20%;border:0px;">Lost</td>';
	     str+='             </tr>';
	    }
	   str+='         </table>';
	   str+='      </td>';
	  }
	 }
	 else
	 {
	   if(result[0].previousResult.isPartial == 1)
	  {
	   str+='      <td  style="width:35%;">';
	   str+='         <table  style="width:100%;">';
	   str+='             <tr  style="width:100%;">';
	   str+='                  <td  style="width:33%;border:0px;">--</td>';
	   str+='                  <td  style="width:33%;border:0px;">--</td>';
	   str+='                  <td  style="width:33%;border:0px;">--</td>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </td>';
	  }
	  else
	  {
	   str+='      <td  style="width:45%;">';
	   str+='         <table  style="width:100%;">';
	   str+='             <tr style="width:100%;">';
	   str+='                  <td style="width:25%;border:0px;">--</td>';
	   str+='                  <td style="width:25%;border:0px;">--</td>';
	   str+='                  <td style="width:15%;border:0px;">--</td>';
	   str+='                  <td style="width:15%;border:0px;">--</td>';
	   str+='                  <td style="width:20%;border:0px;">--</td>';
	   str+='             </tr>';
	   str+='         </table>';
	   str+='      </td>';
	  }
	 
	 }   	  
	  str+='   </tr>';
		
	   
	   }
	   str+='</table>';
	 }
	 else
	 {
	    str+='<div style="padding-top:20px;"><center><b>No Data Found</b></center></div>';
	 }
     document.getElementById(id).innerHTML = str; 
  }
 function callAjax(jsObj,url)
   {
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									
									if(jsObj.task == "getElectionYearsForAState")
									{
									      buildData(myResults,"yearSelId");
									}
									else if(jsObj.task == "getStatesForAssign")
									{
									      buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getdetails")
									{
									   document.getElementById("select_ImgSpan").style.display="none";
									   if(jsObj.reqtype == "ImportantCandidates")
									      buildDataTable(myResults,"keyCandidatesData");
									   else
									      buildDataTable(myResults,"showData");
									}
								}
							catch (e) {   
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
 function buildData(results,id)
 {
   var elmt = document.getElementById(id);
   
       for(var i in results)
	  {
		var option = document.createElement('option');     
		
		    option.value=results[i].id;
		    option.text=results[i].name;       
        
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
 function showOthers()
 {
	removeData("yearSelId");
	addData("yearSelId");
	document.getElementById("stateListId").value = 0;
 }
 function getDetails(reqtype)
 {
       document.getElementById("showData").innerHTML = "";
       document.getElementById("keyCandidatesData").innerHTML = "";
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 if(eleId == 0)
	  return;
	 document.getElementById("select_ImgSpan").style.display="block";
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			reqtype:reqtype,
			task:"getdetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 function removeData(elmtId)
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
 function addData(id)
 {
  
    var elmt = document.getElementById(id);
   
		var option = document.createElement('option');     
		
		    option.value= 0;
		    option.text= "Select Year";       
        
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		} 
 
 
 }

function showMinisPerf(id)
{
   qtype = "minis";
  $("#impCandPerf").removeClass("dashBoardtabsDivSelected");
  $("#minisPerf").addClass("dashBoardtabsDivSelected");
  document.getElementById("showData").innerHTML = "";
  document.getElementById("keyCandidatesData").innerHTML = "";
  document.getElementById("parlSel").checked = true;  
  document.getElementById("showHideState").style.display = "none";
  getElectionYears("Parliament");
}
function showImpCandPerf(id)
{
   qtype = "impCand";
  $("#minisPerf").removeClass("dashBoardtabsDivSelected");
  $("#impCandPerf").addClass("dashBoardtabsDivSelected");
  document.getElementById("showData").innerHTML = "";
  document.getElementById("keyCandidatesData").innerHTML = "";
  document.getElementById("parlSel").checked = true;
  document.getElementById("showHideState").style.display = "none";
  getElectionYears("Parliament");
}
function getDeltailForMinisImpCand()
{
   if(qtype == "minis")
     getDetails('ministers');
   else 
    getDetails('ImportantCandidates')
}
</script>
</head>
<body>
<div style="width:998px;padding-left:5px;">
   <div style="padding-left:5px;"><div class="main-mbg">Results Analysis For Ministers</div></div>
   <div style="background-color:#F5F5F5;min-height:360px;">
         <div class="dashBoardtabsDiv" style="align:left;">
	           <a  id="minisPerf" onclick="showMinisPerf(this.id);">Ministers Performance</a>
	           <a  id="impCandPerf" onclick="showImpCandPerf(this.id);">Important Candidates Performance</a>
         </div>
   <div style="padding-top:10px;padding-left:250px;width:65%;text-align:center;">
     <table>  
	  <tr>
	      <td><input type="radio" name="selectScope" checked="true" id="parlSel" onclick="showHidsState();getElectionYears('Parliament');" />&nbsp;&nbsp;<b>Parliament</b>&nbsp;&nbsp;</td>
	      <td><input type="radio" id="state" name="selectScope" onclick="showHidsState();showOthers();" />&nbsp;&nbsp;<b>Assembly</b></td>
		  <td><div id="showHideState" style="display:none;"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  id="stateListId"  onchange="getElectionYears('Assembly');"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId" onchange="getDeltailForMinisImpCand();" ></select></td>
      </tr>
	 </table>
	 <table>
	   <tr>
	     <td><span id="select_ImgSpan" style="padding-left:206px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span></td>
	   </tr>
	 </table>
   </div>
   <div style="padding-top:30px;">
     <div style="width:980px;" id="showData" />
   </div>
   <div style="padding-bottom:30px;">
     <div style="width:980px;" id="keyCandidatesData" />
   </div>
  </div>
</div>
   <script type="text/javascript">
     getElectionYears("Parliament");
	 getAllStates();
	 $("#impCandPerf").removeClass("dashBoardtabsDivSelected");
     $("#minisPerf").addClass("dashBoardtabsDivSelected");
   </script>
</body>
</html>