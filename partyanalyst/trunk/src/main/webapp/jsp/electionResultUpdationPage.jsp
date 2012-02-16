<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Results Updation Page</title>
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
    width: 992px;
}
.selectStyle{
   height: 26px;
    padding: 3px;
	 width: 129px;
}
.graphTop
{
	background-image:url(images/icons/electionResultsReport/Graybg.png);
	color:#FFFFFF;
	font-family:'Asap',Arial;
	font-size:18px;
	font-weight:bold;
	height:30px;
	width:1000px;	
	border-radius:5px 5px 0px 0px;
	-moz-border-radius:5px 5px 0px 0px;
	text-shadow:0 1px 0 #151515;
	padding-top:5px;
	margin-top:20px;
}
.eleResUpdate {
    background: none repeat scroll 0 0 #F5F5F5;
    border-bottom: 1px solid #D3D3D3;
    border-left: 1px solid #D3D3D3;
    border-right: 1px solid #D3D3D3;
    color: #606060;
    margin-bottom: 5px;
    padding-bottom: 10px;
    padding-top: 5px;
    width: 998px;
	min-height:400px;
}
 .tableStyle {
    
    width: 56%;
    text-align: left;
   
}
 .updateStyle{
    padding-left: 431px;
    padding-top: 10px;
 
 }
 .buttonStyle {
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
}
</style>
<script type="text/javascript">
  var id;
  var elecYear;
  var statId;
  var constId;
  var elecType ;
  var resultCode ="";
  function getStates()
{
  document.getElementById("searchAjaxImgSpan").style.display = "block";
  document.getElementById("electionResults").innerHTML = "";
  var jsObj =
		{ 
            time : new Date().getTime(),
			task:"getStates"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "candidatePhotoGallaryAction.action?"+rparam;						
	callAjax(jsObj,url);
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
		   clearOptionsList("state");
		   buildResults(myResults,"state");
		   document.getElementById("searchAjaxImgSpan").style.display = "none";
		 }
		 else if(jsObj.task == "getAllParliamentConstituencies" || jsObj.task ==  "getConstisForAState")
		 {
		   document.getElementById("constiSelect").style.display = "block";
		   clearOptionsList("constituencies");
		   buildResults(myResults,"constituencies");
		   document.getElementById("searchAjaxImgSpan").style.display = "none";
		 }
		 else if(jsObj.task == "getYears")
		 {
		    clearOptionsList("year");
		    buildResults(myResults,"year");
			document.getElementById("searchAjaxImgSpan").style.display = "none";
		 }
		 else if(jsObj.task == "getCandidateResults")
		 {
		    buildResultsData(myResults,jsObj);
			document.getElementById("searchAjaxImgSpan").style.display = "none";
		 }
		 else if(jsObj.task == "updateCandidateResults")
		 {
		    showUpdateStatus(myResults);
		 }
		}
		catch(e)
		{   
		 alert("Invalid JSON result" + e);   
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
function showUpdateStatus(result)
{
   if(result == null || result[0].resultCode == 1)
     resultCode ="<font color='red'><b>Unable to update, Please try Later </b></font>";
	else if(result[0].resultCode == 0)
	  resultCode ="<font color='green'><b>Result Update Successfully </b></font>";
	  
	  getDetails("afterResult");
}
function clearOptionsList(elmtId)
{
	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
	    if((elmtId == "state" || elmtId == "year" || elmtId == "constituencies") && i == 0)
		  return;
		
		elmt.remove(i);
	}	
}
 function buildResultsData(result,jsobj){
 
   var str = '';
    if(result != null && result.length >0)
	{
	  
     id = result[0].constituencyId;  
     elecYear = jsobj.electionYear;
     statId = jsobj.stateId;
     constId = jsobj.constituencyId;
     elecType = jsobj.electionType; 
	 str+='<div style="padding-top:10px;padding-left:220px;">'+resultCode+'</div>';
	 str+='<div style="padding-top:5px;">';
	 		 
	 str+= '<table class="tableStyle" border="1px" CELLSPACING="0" align="center">';
	 str+= '<tr><th style="background-color : #C4DEFF;width:130px;">Select Candidate</th><th style="background-color : #C4DEFF;width:200px;">Candidate Name</th><th style="background-color : #C4DEFF; width: 100px;">Party Name</th></tr>';
	 
	 for(var i in result)
	 {
	    str+= '<tr>';		
		if(result[i].electionType == "Lead" || result[i].electionType == "Won")
		  {
		      if(result[i].electionType == "Lead" )
		        str+= '		<td style="background-color : #FFFFFF;text-align:center;"><input type="radio" name="updateResRadio"  checked="true" value="'+result[i].candidateId+'" onclick="updateResult(\'Lead\')"   />&nbsp;<font color="green"><b>Lead</b></font> &nbsp;&nbsp<input type="radio" name="updateResRadio"   value="'+result[i].candidateId+'"  onclick="updateResult(\'Won\')"  />&nbsp;<b>Won</b></td>';
		      else
			    str+= '		<td style="background-color : #FFFFFF;text-align:center;"><input type="radio" name="updateResRadio" value="'+result[i].candidateId+'" onclick="updateResult(\'Lead\')"   />&nbsp;<b>Lead</b> &nbsp;&nbsp<input type="radio" name="updateResRadio"   checked="true"   value="'+result[i].candidateId+'"  onclick="updateResult(\'Won\')"  />&nbsp;<font color="green"><b>Won</b></font></td>';
		  }
		else
		   str+= '		<td style="background-color : #FFFFFF;text-align:center;"><input type="radio" name="updateResRadio"  value="'+result[i].candidateId+'" onclick="updateResult(\'Lead\')"  />&nbsp;<b>Lead</b> &nbsp;&nbsp<input type="radio" name="updateResRadio"   value="'+result[i].candidateId+'"  onclick="updateResult(\'Won\')"  />&nbsp;<b>Won</b></td>';
		   
		
		   str+= '		<td style="background-color:#FFFFFF"><b><a href="candidateElectionResultsAction.action?candidateId='+result[i].candidateId+' ">'+result[i].candidateName+'</a></b></td>';
		  if(result[i].partyName != 'IND') 
		    str+= '     <td style="background-color : #FFFFFF"><b><a href="partyPageAction.action?partyId='+result[i].partyId+' ">'+result[i].partyName+'</a></b></td>';
          else		    
			str+= '     <td style="background-color : #FFFFFF"><b>'+result[i].partyName+'</b></td>';
			
		str+= '</tr>';
	 }
	 
	 str+= '</table>';
	 str+= '</div>';
   
   
  }
  else
  {
    str+='No Records Found';
  }
   document.getElementById("electionResults").innerHTML = str;
 }
 function updateResult(result)
 {
  var candidateId = 0;
  var count = 0;
 var selectBoxEl = document.getElementsByName("updateResRadio");
   for (i=0; i< selectBoxEl.length; i++)
	{
		if(selectBoxEl[i].checked == true)
		 {
		  candidateId = selectBoxEl[i].value;
		  count = count+1;
		 }
	}
	if(count == 0)
	  return;
   var jsObj=
	     {	
		    time : new Date().getTime(),
			candidateId: candidateId,
			constiElecId: id,
			status: result,
			task: "updateCandidateResults"
				
	     }

      var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveUpdateElectionResultsAction.action?"+rparam;						
      callAjax(jsObj,url);
 }
function getAllConstituenciesInStateByType()
{	
    
    document.getElementById("electionResults").innerHTML = "";
    var stateEle = document.getElementById("state");
	var stateId = stateEle.options[stateEle.selectedIndex].value;
	 if(stateId == 0)
	  return ;
	  document.getElementById("searchAjaxImgSpan").style.display = "block";
	var jsObj=
	{		time : new Date().getTime(),		
			stateId: stateId,
			task: "getConstisForAState"	
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getConstituenciesOrYearsAction.action?"+rparam;						
callAjax(jsObj,url);
}
function getAllParliamentConstInCountry()
{
    document.getElementById("searchAjaxImgSpan").style.display = "block";
    document.getElementById("electionResults").innerHTML = "";
	var jsObj=
	{			
             time : new Date().getTime(),	
			task: "getAllParliamentConstituencies"
				
	}

var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getAllParliamentConstInCountry.action?"+rparam;						
callAjax(jsObj,url);
}

function buildResults(results,id){
  var elmt = document.getElementById(id);
      for(var i in results)
	  {
		var option = document.createElement('option');
         if(id == "constituencies" || id == "year")
          {	
		    option.value=results[i].id;
		    option.text=results[i].name;
          }
         else
          {		 
		    option.value=results[i].ids;
		    option.text=results[i].names;
		  }
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
	function showHideYear()
	{
	    clearOptionsList("state");
		clearOptionsList("year");
		clearOptionsList("constituencies");
	  document.getElementById("electionResults").innerHTML = "";
	  
	  var typeEle = document.getElementById("electionType");
      var typeVal = typeEle.options[typeEle.selectedIndex].value;
      if(typeVal == 1)
      {
        document.getElementById("stateSel").style.display = "block";
		 getStates();
	  }
      else if(typeVal == 2)
      {
        document.getElementById("stateSel").style.display = "none";
		getYearsForStates("Parliament");
	  }

	}
	function getDetails(type){
	 var electionYear ;
	 var stateId ;
	 var constituencyId ;
	 var electionType ;
	if(type == "initial")
	 {
	   resultCode = "";
	       document.getElementById("electionResults").innerHTML = "";
	     var electionYearEle = document.getElementById("year");
		 electionYear = electionYearEle.options[electionYearEle.selectedIndex].text;
		 
		 var stateEle = document.getElementById("state");
         stateId = stateEle.options[stateEle.selectedIndex].value;
		 
		 var constiEle = document.getElementById("constituencies");
	     constituencyId = constiEle.options[constiEle.selectedIndex].value;

		 if(constituencyId == 0)
		   return;
		 document.getElementById("searchAjaxImgSpan").style.display = "block";
	     var typeEle = document.getElementById("electionType");
         var typeVal = typeEle.options[typeEle.selectedIndex].value;
		
		 
         if(typeVal == 1)
         {
	        electionType = "Assembly";
	     }
        else if(typeVal == 2)
        {
	       electionType = "Parliament";
	    }
	  }
	  if(type == "afterResult")
	  {
	     electionYear  = elecYear ;
          stateId = statId;
          constituencyId = constId;
          electionType = elecType ;
	  }
	   var jsObj=
	     {	
		    time : new Date().getTime(),
		    electionYear: electionYear,
			stateId: stateId,
			constituencyId: constituencyId,
			electionType: electionType,
			task: "getCandidateResults"
				
	     }

      var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
      var url = "saveUpdateElectionResultsAction.action?"+rparam;						
      callAjax(jsObj,url);
	
	}
  function getYearsForStates(electionType)
  {
        clearOptionsList("year");
		clearOptionsList("constituencies");
     document.getElementById("electionResults").innerHTML = "";
     
     var stateId = 0;
	 if(electionType == "Assembly")
	  {
	    
       var stateEle = document.getElementById("state");
       stateId = stateEle.options[stateEle.selectedIndex].value;
	   if(stateId == 0)
	    return;
	  }
	
	document.getElementById("searchAjaxImgSpan").style.display = "block";
	 document.getElementById("yearSel").style.display = "block"; 
    var jObj = {
	        time : new Date().getTime(),
			stateId : stateId,
		    electionType: electionType,
			task: 'getYears'
			};

	var rparam = "task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getConstituenciesOrYearsAction.action?"+rparam;
	callAjax(jObj,url);
  }
  function getConstituencies()
  {
      clearOptionsList("constituencies");
      document.getElementById("electionResults").innerHTML = "";	  
	   
     var yearEle = document.getElementById("year");
      var yearVal = yearEle.options[yearEle.selectedIndex].value;
	  
	  if(yearVal == 0)
	    return;
	  
     var typeEle = document.getElementById("electionType");
      var typeVal = typeEle.options[typeEle.selectedIndex].value;
      if(typeVal == 1)
      {
	    
		getAllConstituenciesInStateByType();
	  }
      else if(typeVal == 2)
      {
	      
         getAllParliamentConstInCountry();
	  }
  }
</script>
</head>
<body>
 <div ><div class="main-mbg">Update Candidates Leading Positions</div></div>
 

<div class="eleResUpdate"  style="padding-left:5px;padding-top:10px;">
 <table style="padding-left:10px;"><tr>
   <td><b>Select Election Type  </b><select id="electionType" class="selectStyle" onchange="showHideYear();">
       <option value="0">Select Election Type</option>
       <option value="1">Assembly</option>
	   <option value="2">Parliament</option>
      </select>
   </td>
   <td><div id="stateSel" style="display:none;"> <b>&nbsp;&nbsp;State  </b><select id="state"  class="selectStyle"  onchange="getYearsForStates('Assembly');"><option value="0">Select State</option></select>
   </div></td>
   <td><div id="yearSel" ><b>&nbsp;&nbsp;Select Year</b><select id="year" class="selectStyle" onchange="getConstituencies();">
       <option value="0">Select Year</option>
      </select></div>
   </td>
	<td><div id="constiSelect" ><b>&nbsp;&nbsp;Select Constituency </b><select id="constituencies"  class="selectStyle"  onchange="getDetails('initial');"><option value="0">Select Constituency</option></select></div></td>
	&nbsp;&nbsp;<td><div id="searchAjaxImgSpan" style="display:none;"><img src="images/icons/search.gif"></div></td>
  </tr>
 </table>

<div id="electionResults"></div>
</div>
<script type="text/javascript">
</script>
</body>
</html>