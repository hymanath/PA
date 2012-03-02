<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>District Wise Party Performance Analysis</title>
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
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
    border-style: solid;
    border-width: 1px;
}
.buttonClass {
    background-color: #F52B43;
    border-radius: 6px 6px 6px 6px;
    color: #FFFFFF;
    cursor: pointer;
    font-weight: bold;
    padding: 4px;
}
</style>
<script type="text/javascript">
google.load("visualization", "1", {packages:["corechart"]});
function getElectionYears(electionType)
{
   document.getElementById("errorMessage").innerHTML = "";
   stateId = 1;
   if(electionType == "Assembly")
   {
	var stateEle = document.getElementById("stateListId");
    stateId = stateEle.options[stateEle.selectedIndex].value;
	document.getElementById("districtAny").innerHTML = "";
	document.getElementById("districtChart").innerHTML = "";
	document.getElementById("districtPartyChart").innerHTML = "";
	document.getElementById("districtPartyChartMore").innerHTML = "";
	document.getElementById("hideSelect").style.display ="none";
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
									else if(jsObj.task == "getDistrictWiseAnalysisDetails")
									{ 
									     document.getElementById("select_ImgSpan").style.display="none";
										if(myResults.length > 0)
									      getParties();
										showDistrictWiseAnalysis(myResults);
									}
									else if(jsObj.task == "getDistrictWiseAnalysisDetailsForGraph")
									{
									      drawChart(myResults,jsObj.districtName);
									}
									else if(jsObj.task == "getPartiesForPartialEle")
									{    if(myResults.length >0)
									     {
										    removeData("parties");
									       document.getElementById("hideSelect").style.display="block";
									       buildParties(myResults);
										 }
									}
									else if(jsObj.task == "getpartyPerfoDistWiseForPartialEle")
									{
									    document.getElementById("searchImage").style.display="none";
									    if(myResults.length <=35)
									     buildGraph(myResults);
										else
										 divideArray(myResults);
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
  function divideArray(result)
  {
     var firstArray = new Array();
	 var secondArray = new Array();
	 for(var i =0;i<result.length;i++)
	 {
	    if(i<=32)
		{
		  firstArray.push(result[i]);
		}
		else if(i>32 && i<65) 
		{
		  secondArray.push(result[i]);
		}
	 }
     buildGraph(firstArray);	 
	 buildMoreGraph(secondArray);
  }
  function getParties()
   {
         var yearEle =  document.getElementById("yearSelId");
	     var eleId = yearEle.options[yearEle.selectedIndex].value;
	     if(eleId == 0)
	     return;
	     var yearEle =  document.getElementById("stateListId");
	     var stateListId = yearEle.options[yearEle.selectedIndex].value;
	    var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateListId,
			task:"getPartiesForPartialEle"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
   }
  function showDistrictWiseAnalysis(result)
  {
      var str ='';
     if(result.length > 0)
	 {
	    var leng1 =  result[0].currentResultList.length+2;
		var leng2 =  result[0].previousResultList.length+1;
		if(result[0].currResPartial == 1)
		leng1 = leng1+1;
		if(result[0].prevResPartial == 1)
		leng2 = leng2+1;
		str +='	<table cellspacing="0px" cellpadding="0px" align="center"><tr style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;"><td> District Wise Party Performance : </td></tr></table>';
	    str+=' <table class="searchresultsTable" style="width:99%;">';
		str+='   <tr>';
		str+='   <th colspan="'+leng1+'">'+result[0].presentYear+'</th>';
		str+='   <th colspan="'+leng2+'">'+result[0].prevYear+'</th>';
		str+='   </tr>';
		str+='   <tr>';
		str+='     <th>District Name</th>';
		str+='	   <th>Total Seats</th>';
		if(result[0].currResPartial == 1)
		  str+='	   <th>Result Known</th>';
	   for(var i in result[0].currentResultList)
	    {
		   str+='  <th>'+result[0].currentResultList[i].partyName+'</th>';
	    }
		str+='     <th>Total Seats</th>';
		if(result[0].prevResPartial == 1)
		  str+='     <th>Result Known</th>';
	   for(var i in result[0].previousResultList)
	    {
		  str+='   <th>'+result[0].previousResultList[i].partyName+'</th>';
	    }
		str+='   </tr>';
	   for(var i in result)
	    {
		 str+='   <tr>';
		 str+='     <td><a href="javascript:{}" title="Click To Get Party Wise Performance In '+result[i].districtName+' District Graph " onclick="getDetailsForGraph('+result[i].districtId+',\''+result[i].districtName+'\');" >'+result[i].districtName+'</a></td>';
         str+='     <td>'+result[i].currentResultList[0].totalCount+'</td>'; 
         if(result[0].currResPartial == 1)
          	str+='     <td>'+result[i].currentResultList[0].knownResultsCount+'</td>';
         for(var j in result[i].currentResultList)
          {
            str+='     <td>'+result[i].currentResultList[j].partyCount+'</td>'; 
          }
		 str+='     <td>'+result[i].previousResultList[0].totalCount+'</td>'; 
         if(result[0].prevResPartial == 1)
          	str+='     <td>'+result[i].previousResultList[0].knownResultsCount+'</td>';
         for(var k in result[i].previousResultList)
          {
            str+='     <td>'+result[i].previousResultList[k].partyCount+'</td>'; 
          }
		 str+='   </tr>'; 	 
		}
		  
		
		str+='</table>';
	    str+='<div style="margin-top:5px;" id="messageDiv"><b>Click on any District Name to get party wise performance in that District</b></div>';
	 
	 }
	 else
	 {
	   str+='<div style="padding-top:20px;"><center><b>No Data Found</b></center></div>';
	 }
  document.getElementById("districtAny").innerHTML = str;
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
  function buildParties(results)
 {
   var elmt = document.getElementById("parties");
   
       for(var i in results)
	  {
		var option = document.createElement('option');     
		
		    option.value=results[i].partyId;
		    option.text=results[i].partyName;       
        
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
 function getDetails()
 {
      validate = false;
	   var str='';
       document.getElementById("errorMessage").innerHTML = "";
       document.getElementById("districtAny").innerHTML = "";
	   document.getElementById("districtChart").innerHTML = "";
	   document.getElementById("districtPartyChart").innerHTML = "";
	   document.getElementById("districtPartyChartMore").innerHTML = "";
	   document.getElementById("hideSelect").style.display ="none";
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 var yearEle =  document.getElementById("stateListId");
	 var stateListId = yearEle.options[yearEle.selectedIndex].value;
	 if(stateListId == 0)
	 {
	   validate = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select State </font></b><br />';
	 }
	 if(eleId == 0)
	 {
	   validate = true;
	   str+='<b><font style="color:red;font-size:12px;">Please Select Year </font></b><br />';
	 }
	 if(validate)
	  {
         document.getElementById("errorMessage").innerHTML = str;
    	return;
	  } 
	  
	 document.getElementById("select_ImgSpan").style.display="block";
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateListId,
			task:"getDistrictWiseAnalysisDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 function getDetailsForGraph(districtId,districtName)
 {
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	  var yearEle =  document.getElementById("stateListId");
	 var stateListId = yearEle.options[yearEle.selectedIndex].value;
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateListId,
			districtId:districtId,
			districtName:districtName,
			task:"getDistrictWiseAnalysisDetailsForGraph"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
 }
 function getDataForChart()
 {
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 var partyIds='';
	 var partyEle =  document.getElementById("parties");
	 var count = 0;
	  for(var i = 0;i < partyEle.options.length;i++)
	    {
	       if (partyEle.options[i].selected) 
		   {
             partyIds+= ''+partyEle.options[i].value+',';
			 count = count+1;
           } 
	   }
	   if(count == 0)
	     return;
	 document.getElementById("searchImage").style.display="block";
	   partyIds = partyIds.slice(0, -1);
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			partyIds:partyIds,
			task:"getpartyPerfoDistWiseForPartialEle"
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
  function drawChart(result,districtName) 
  {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Party');
        data.addColumn('number', result[0].presentYear);
        data.addColumn('number', result[0].prevYear);
		for(var i in result)
		{
		  var array = new Array();
		  array.push(result[i].partyName);
		  array.push(result[i].presentCount);
		  array.push(result[i].prevCount);
          data.addRow(array);
		}
 var ctitle = 'Party Wise Performance in '+districtName+' District';
        var chart = new google.visualization.ColumnChart(document.getElementById('districtChart'));
		chart.draw(data, {width:1002,height: 350,title:ctitle,legend:'right',legendTextStyle:{fontSize:10},
				  hAxis: {title: 'Party',textStyle:{fontSize:'10',fontWeight:'bold'},slantedText:true, slantedTextAngle:30, titleTextStyle: {color: 'red'}}
	});
  }
  function buildGraph(result)
  {
   if(result.length > 0)
	{
     var data = new google.visualization.DataTable();
	 data.addColumn('string', 'District');
	 for(var k in result[0].positionManagementVOList){
	 data.addColumn('number',result[0].positionManagementVOList[k].partyName);
	 }
	 for(var i in result)
	 {
	     var array = new Array();
	     array.push(result[i].districtName);
	     for(var j in result[i].positionManagementVOList)
	      {
		    array.push(result[i].positionManagementVOList[j].partyCount);
		  }
		  data.addRow(array);			 
	 }  
	 var chartResultDiv = document.getElementById("districtPartyChart");
	    ctitle = "Selected Parties Wise Results(Won/Lead) Comparison";
			  
	 new google.visualization.LineChart(chartResultDiv).
		  draw(data, {curveType: "function",width: 1002, height: 300,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
   }
  else
   {
       document.getElementById("districtPartyChartMore").innerHTML ='<div style="padding-top:20px;"><b>No Data Found</b></div>';
   }	   
  }
  function buildMoreGraph(result)
  {
    
     var data = new google.visualization.DataTable();
	 data.addColumn('string', 'District');
	 for(var k in result[0].positionManagementVOList){
	 data.addColumn('number',result[0].positionManagementVOList[k].partyName);
	 }
	 for(var i in result)
	 {  	
	     var array = new Array();
	     array.push(result[i].districtName);
	     for(var j in result[i].positionManagementVOList)
	      {
		    array.push(result[i].positionManagementVOList[j].partyCount);
		  }
		  data.addRow(array);		 
	 }  
	 var chartResultDiv = document.getElementById("districtPartyChartMore");
	    ctitle = "Selected Parties Wise Results(Won/Lead) Comparison";
			  
	 new google.visualization.LineChart(chartResultDiv).
		  draw(data, {curveType: "function",width: 1002, height: 300,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
   	
  }
</script>
</head>
<body>
<div style="width:998px;padding-left:5px;">
   <div style="padding-left:5px;"><div class="main-mbg">District Wise Party Performance</div></div>
   <div style="background-color:#F5F5F5;min-height:360px;">
         
   <div style="padding-top:10px;padding-left:250px;width:65%;text-align:center;">
     <div id="errorMessage"></div>
     <table>  
	  <tr>
		  <td><div id="showHideState"><b>&nbsp;&nbsp;Select State :</b>&nbsp;&nbsp;<select  id="stateListId"  onchange="getElectionYears('Assembly');"><option value="0">Select State</option></select></div></td>
		  <td>&nbsp;&nbsp;&nbsp;&nbsp;<b>Select Year :</b>&nbsp;&nbsp;<select id="yearSelId" onchange="getDetails();" ><option value="0">Select State</option></select></td>
		  <td><img src="images/icons/refreshImg.png"  onclick="getDetails();" title="Click To Refresh The Result" /></td>
      </tr>
	 </table>
	 <table>
	   <tr>
	     <td><span id="select_ImgSpan" style="padding-left:206px;padding-top:5px;display:none;"><img src="images/icons/partypositions.gif"></span></td>
	   </tr>
	 </table>
   </div>
   <div style="padding-bottom:30px;width:100%">
     <div style="width:990px;padding-left:5px;padding-right:5px;overflow:auto;" id="districtAny" />
   </div>
   <div style="margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px; margin-bottom: 23px;padding-right:5px;" id="districtChart" />
   </div>
   <div id="hideSelect" style="display:none;">
     <center><div style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;">Compare Party Wise Results In All Districts : </div></center>
     <center>
	     <div>
		    <table>
			   <tr>
			     <td><b>Select Parties : </b>&nbsp;&nbsp;</td>
			     <td><select multiple="multiple" id="parties" style="width:100px;" ></select></td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <td><input type="button" class="buttonClass" value="View" onclick="getDataForChart();" /></td>&nbsp;&nbsp;&nbsp;
				 <td><div id="searchImage" style="display:none;"><img src="images/icons/search.gif"></img width="18px" height="18px;"></div></td>
			   </tr>
			 </table>
		  </div>
	  </center>
   </div>
   <div style="margin-bottom:30px;margin-top:20px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChart" />
   </div>
   <div style="padding-bottom:30px;margin-left:0px;width:980px;overflow:hidden;">
     <div style="width:980px;padding-left:5px;padding-right:5px;" id="districtPartyChartMore" />
   </div>
  </div>
</div>
   <script type="text/javascript">
	 getAllStates();
   </script>
</body>
</html>