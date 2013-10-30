
function getElectionYears(electionType)
{
   document.getElementById("errorMessage").innerHTML = "";
   document.getElementById("analysisTable").innerHTML= '';
   document.getElementById("analysisTableDisplay").innerHTML= '';
   stateId = 1;
   if(electionType == "Assembly")
   {
	var stateEle = document.getElementById("stateListId");
    stateId = stateEle.options[stateEle.selectedIndex].value;
	document.getElementById("districtAny").innerHTML = "";
	document.getElementById("districtAnyHeading").innerHTML = "";
	document.getElementById("districtChart").innerHTML = "";
	document.getElementById("districtPartyChart").innerHTML = "";
	document.getElementById("districtPartyChartMore").innerHTML = "";
	removeData("partiesIdDiv");
	document.getElementById("hideSelect").style.display ="none";
	if(stateId == 0)
	  {
	    removeData("yearSelId");
        addData("yearSelId");
	    return ;
	  }
   }
	removeData("yearSelId");
	document.getElementById("select_ImgSpan").style.display="block";
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
									      document.getElementById("select_ImgSpan").style.display="none";
									      buildData(myResults,"yearSelId");
									}
									else if(jsObj.task == "getStatesForAssign")
									{
									      buildData(myResults,"stateListId");
									}
									else if(jsObj.task == "getDistrictWiseAnalysisDetails")
									{ 
									     document.getElementById("resultImgDiv").style.display="none";
										 pushData(myResults);
										 if(myResults.length > 0)
										  {
										    buildPartyComparision(myResults[0].currResPartial)
										  }
										//if(myResults.length > 0)
									     getParties();
										 document.getElementById("hideSelect").style.display="block";
									//	showDistrictWiseAnalysis(myResults);
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
									       //buildParties(myResults);
										 }
									}
									else if(jsObj.task == "getpartyPerfoDistWiseForPartialEle")
									{
									    document.getElementById("searchImage").style.display="none";
									    if(myResults.length <=35)
									     buildGraph(myResults);
										else
										{
										 divideArray(myResults);
										 }
										 selectPartiesForGraph();
									}
									else if(jsObj.task == "getAllPartiesForResults" && jsObj.type == "main")
									{
									  document.getElementById("select_ImgSpan").style.display="none";
									  removeData("partiesIdDiv");
									  buildParties(myResults,"partiesIdDiv");
									}
									else if(jsObj.task == "getAllPartiesForResults" && jsObj.type == "sub")
									{
									  removeData("partiesSelIdDiv");
									  buildParties(myResults,"partiesSelIdDiv");
									}
									else if(jsObj.task == "getAllPartiesForResults" && jsObj.type == "graph")
									{
									  removeData("parties");
									  buildParties(myResults,"parties");
									}
									else if(jsObj.task == "getPartiesPerfResultsWise" && jsObj.name == 'main')
									{
									  showPartyPerf(myResults,jsObj.type);
									}
									else if(jsObj.task == "getPartiesPerfResultsWise" && jsObj.name == 'sub')
									{
									  document.getElementById("select_ImgAnalysisSpan").style.display="none";
									  showPartyPerf(myResults,jsObj.type);
									}
								}
							catch (e) {   
							   //	alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
function showPartyPerf(results,type)
   {  
     var str='';
	 if(results != null && results.length>0 )
	 {
	  str+='<table class="searchresultsTable" align="center" style="minwidth:60%;">';
	  str+='    <tr>';
	  str+='       <th rowspan="2">District</th>';
	  for(var j in results[0].positionManagementVOList)
	  {
	   if(type == "seats" || type == "seatsandvotes")
	    str+='       <th colspan="2">'+results[0].positionManagementVOList[j].partyName+'(Seats)</th>';
	   if(type == "votes" || type == "seatsandvotes")
	    str+='       <th colspan="2">'+results[0].positionManagementVOList[j].partyName+'(Votes)</th>';
	  }
	  str+='    </tr>';
	  str+='    <tr>';
	  for(var j in results[0].positionManagementVOList)
	  {
	   if(type == "seats" || type == "seatsandvotes")
	    {
	     str+='       <th>'+results[0].prevYear+'</th>';
		 str+='       <th>'+results[0].presentYear+'</th>';
		}
	   if(type == "votes" || type == "seatsandvotes")
	    {
		 str+='       <th>'+results[0].prevYear+'</th>';
	     str+='       <th>'+results[0].presentYear+'</th>';
	    }
	  }
	  str+='    </tr>';
	  
	  
	  for(var i in results)
	  {
	    str+='    <tr>';
	   str+='       <td>'+results[i].districtName+'</td>';
	  for(var k in results[i].positionManagementVOList)
	  {
	   if(type == "seats" || type == "seatsandvotes")
	    {
		if(results[i].positionManagementVOList[k].partyPrevCount != null && results[i].positionManagementVOList[k].partyCount != null)
		 {
	     str+='       <td>'+results[i].positionManagementVOList[k].partyPrevCount+'</td>';
		 if(results[i].positionManagementVOList[k].status == "increased"){
			if(results[i].positionManagementVOList[k].totalCount !='0')
					str+='       <td>'+results[i].positionManagementVOList[k].partyCount+'('+results[i].positionManagementVOList[k].totalCount+'<font color="green">&uarr;</font>)</td>';
				else
					str+='       <td>'+results[i].positionManagementVOList[k].partyCount+'('+results[i].positionManagementVOList[k].totalCount+')</td>';

		 }
		 else
		  str+='       <td>'+results[i].positionManagementVOList[k].partyCount+'('+results[i].positionManagementVOList[k].totalCount+'<font color="red">&darr;</font>)</td>';
		 }
		 else
		 {
		   if(results[i].positionManagementVOList[k].partyPrevCount != null)
		     str+='       <td>'+results[i].positionManagementVOList[k].partyPrevCount+'</td>';
		   else
		     str+='       <td>--</td>';
		   if(results[i].positionManagementVOList[k].partyCount != null)
		     str+='       <td>'+results[i].positionManagementVOList[k].partyCount+'</td>';
		   else
		     str+='       <td>--</td>';
		 }
		}
	   if(type == "votes" || type == "seatsandvotes")
	    {
		 if(results[i].positionManagementVOList[k].prevVotesEarned != null && results[i].positionManagementVOList[k].votesEarned != null)
		 {
		  str+='       <td>'+results[i].positionManagementVOList[k].prevVotesEarned+'</td>';
		 if(results[i].positionManagementVOList[k].type == "increased")
		  str+='       <td>'+results[i].positionManagementVOList[k].votesEarned+'('+results[i].positionManagementVOList[k].votesEarnedDiff+'<font color="green">&uarr;</font>)</td>';
		 else
		  str+='       <td>'+results[i].positionManagementVOList[k].votesEarned+'('+results[i].positionManagementVOList[k].votesEarnedDiff+'<font color="red">&darr;</font>)</td>';
		 }
		 else
		 {
		   if(results[i].positionManagementVOList[k].prevVotesEarned != null)
		     str+='       <td>'+results[i].positionManagementVOList[k].prevVotesEarned+'</td>';
		   else
		     str+='       <td>--</td>';
		   if(results[i].positionManagementVOList[k].votesEarned != null)
		     str+='       <td>'+results[i].positionManagementVOList[k].votesEarned+'</td>';
		   else
		     str+='       <td>--</td>';
		 }
	    }
	  }
	  str+='    </tr>';
	  }
	  
	  
	  str+='</table>';
	 }
	 else
	 {   
	    str+='<div style="padding-top:20px;"><center><b>No Data Found</b></center></div>';
	 }
	  document.getElementById("analysisTableDisplay").innerHTML=str;
	  
	  selectParties();
   }


function buildPartyComparision(value)
{
   var str='';
   
	 str+='<div style="font-weight: bold;padding-left: 130px;padding-bottom: 9px;font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;">Compare Party Wise Seats And Votes Earned In All Districts : </div>';
	    str+='<table>';
		str+='   <tr>';
		str+='      <td>';
		str+='         <input type="radio" id="seats" checked="true" value="seats" name="analysisRadio">&nbsp;&nbsp;<b>Seats</b>';
		str+='      </td>';
		if(value != 1)
		{
		str+='      <td>';
		str+='         <input type="radio" id="votes" value="votes" name="analysisRadio">&nbsp;&nbsp;<b>Votes</b>';
		str+='      </td>';
		str+='      <td>';
		str+='         <input type="radio" id="seatsandvotes"  value="seatsandvotes"  name="analysisRadio">&nbsp;&nbsp;<b>Seats & Votes</b>';
		str+='      </td>';
		}
		str+='      <td>&nbsp;&nbsp;<b>Select Party :</b></td>';
		str+='      <td>&nbsp;&nbsp;<select name "partiesSelIdDiv" id="partiesSelIdDiv" style="width:100px;" multiple="multiple"><option value="0">Select Party</option></select></td>';
		str+='      <td>';
		str+='         <input type="checkbox" id="allianceChk"  name="allianceChk">&nbsp;&nbsp;<b>Include Alliances</b>';
		str+='      </td>';
		str+='      <td>';
		str+='         &nbsp;&nbsp;<input type="button" class="buttonStyle" onclick="getAnalysisDetails();" value="View">';
		str+='      </td>';
		str+='      <td><span style="padding-left: 10px; padding-top: 5px; display: none;" id="select_ImgAnalysisSpan"><img src="images/icons/partypositions.gif"></span></td>';
		str+='   </tr>';
		str+='</table>';
	 
	   document.getElementById("analysisTable").innerHTML= str;
     getAllPartiesSub();
}
function selectParties(){
 setTimeout(function(){
    $("#partiesIdDiv :selected").each(function(){
		var objValue=$(this).val();
       $('#partiesSelIdDiv option[value=' + objValue + ']').attr("selected",true);
    });},2000);
 }
 function selectPartiesForGraph(){
 setTimeout(function(){
    $("#partiesIdDiv :selected").each(function(){
		var objValue=$(this).val();
       $('#parties option[value=' + objValue + ']').attr("selected",true);
    });},2000);
 }
function getAnalysisDetails(name)
{
  	if(name == "main")
	{
		//select();
		 document.getElementById("analysisTableDisplay").innerHTML= '';
         var yearEle =  document.getElementById("yearSelId");
		 var eleId = yearEle.options[yearEle.selectedIndex].value;
		  	var partyIds = [];
			$('#partiesIdDiv option:selected').each(function(){ partyIds.push($(this).val()); });
			var result = partyIds.join(',');
			var includeAlliances = 'false';
			/*alert(partyIds);
			$("#partiesSelIdDiv").val(partyIds);
			$("#partiesSelIdDiv").multiselect("refresh");*/
	      var type;
		  var jsObj =
		  { 
            time : new Date().getTime(),
			electionId: eleId,
			partyIds:result,
			includeAlliances:includeAlliances,
			type:'seats',
			name:'main',
			task:"getPartiesPerfResultsWise"
		  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;			
	callAjax(jsObj,url);
	
	}
	else
	{
		 document.getElementById("analysisTableDisplay").innerHTML= '';
         var yearEle =  document.getElementById("yearSelId");
	     var eleId = yearEle.options[yearEle.selectedIndex].value;
		 var partyIds ='';
		 var includeAlliances = document.getElementById("allianceChk").checked;
		 var partyEle =  document.getElementById("partiesSelIdDiv");
		 for(var i = 0;i < partyEle.options.length;i++)
	    {
	       if (partyEle.options[i].selected) 
		   {
             partyIds+= ''+partyEle.options[i].value+',';
			 
           } 
	   }
	     var type;
	   var ele = document.getElementsByName("analysisRadio");
          for(var i=0;i<ele.length ;i++)
		  {
		   if(ele[i].checked == true)
		    type = ele[i].value;
		  }
		  document.getElementById("select_ImgAnalysisSpan").style.display="block";
         var jsObj =
		  { 
            time : new Date().getTime(),
			electionId: eleId,
			partyIds:partyIds,
			includeAlliances:includeAlliances,
			type:type,
			name:'sub',
			task:"getPartiesPerfResultsWise"
		  };

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getministersDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
}
function getAllParties()
{   
         document.getElementById("analysisTable").innerHTML= '';
		 document.getElementById("analysisTableDisplay").innerHTML= '';
		 document.getElementById("hideSelect").style.display="none";
         document.getElementById("districtPartyChart").innerHTML="";
         document.getElementById("districtPartyChartMore").innerHTML="";
         removeData("partiesIdDiv");
         var yearEle =  document.getElementById("yearSelId");
	     var eleId = yearEle.options[yearEle.selectedIndex].value;
		 var stateEle =  document.getElementById("stateListId");
	     var stateId = stateEle.options[stateEle.selectedIndex].value;
		 document.getElementById("select_ImgSpan").style.display="block";
         var jsObj =
		  { 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateId,
			type:'main',
			task:"getAllPartiesForResults"
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function getAllPartiesSub()
{   
         var yearEle =  document.getElementById("yearSelId");
	     var eleId = yearEle.options[yearEle.selectedIndex].value;
		 var stateEle =  document.getElementById("stateListId");
	     var stateId = stateEle.options[stateEle.selectedIndex].value;
		 //document.getElementById("select_ImgSpan").style.display="block";
         var jsObj =
		  { 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateId,
			type:'sub',
			task:"getAllPartiesForResults"
		  };

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
}
function pushData(result)
{
 if(result.length >0)
 {
  var obj = new Array();
    for(var i in result)
	{
	  var objval = new Array();
	  objval["districtName"] = result[i].districtName;
      objval["prevTotalCount"] = result[i].prevTotalCount;
      objval["totalCount"] = result[i].totalCount;
      objval["prevCount"] = result[i].prevCount;
      objval["presentCount"] = result[i].presentCount;
	  objval["notPresent"] = "--";
	  
	   for(var j in result[i].positionManagementVOList)
	    {
         objval["partyPrevCount"+j] = result[i].positionManagementVOList[j].partyPrevCount;
         objval["partyCount"+j] = result[i].positionManagementVOList[j].partyCount;
		}
	  obj.push(objval);
	}
	build(obj,result[0].prevYear,result[0].presentYear,result[0].positionManagementVOList)
 }
 else
 {
    document.getElementById("districtAny").innerHTML ='<div style="padding-top:20px;"><center><b>No Data Found</b></center></div>';
 }
  
}
function build(arr,prevYear,presentYear,result)
{
    document.getElementById("districtAnyHeading").innerHTML='<center><div style="font-weight: bold; font-family: verdana; font-size: 12px; color: rgb(0, 87, 144);padding-top:10px;">Party Wise Seats Earned In All Districts : </div></center>';
	var resultsDataSource = new YAHOO.util.DataSource(arr);
	resultsDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseschema = {
		fields : [
		{
			key : "districtName"
		},
		{
			key : "prevTotalCount",parser:"number"
		},
		{
			key : "totalCount",parser:"number"
		},		
		{
			key : "prevCount",parser:"number"
		},		 
		
		{
			key : "presentCount",parser:"number"
		},
		{
			key : "notPresent"
		}
		]
	};
    for(var i in result)
    {
	   var obj = {
    	     key :"partyPrevCount"+i,parser:"number"
    		};
    		resultsDataSource.responseschema.fields.push(obj);
	}
	for(var i in result)
    {
	   var obj = {
    	     key :"partyCount"+i,parser:"number"
    		};
    		resultsDataSource.responseschema.fields.push(obj);
	}
	var resultsColumnDefs = [ 
	{
		key : "districtName",
		label : "District",
		sortable : true
	}, 
	{
		label:"Total Seats",
		children:[ 
					{
						label : ""+prevYear,
						key : "prevTotalCount",
						sortable : true
					},
					{
						label : ""+presentYear,
						key : "totalCount",
						sortable : true
					}				
				]
	},
	{
		label:"Known Results",
		children:[ 
					{
						label : ""+prevYear,
						key : "prevCount",
						sortable : true
					},
					{
						label : ""+presentYear,
						key : "presentCount",
						sortable : true
					}				
				]
	}
	];
   for(var i in result)
    {
	  var obj;
	  if(result[i].partyPrevPresence == 1)
	  {
	     obj = {
    	label:""+result[i].partyName,
		children:[ 
					{
						label : ""+prevYear,
						key : "notPresent",
						sortable : true
					},
					{
						label : ""+presentYear,
						key : "partyCount"+i,
						sortable : true
					}				
				]
    		};
	  }
	  else
	  {
	   obj = {
    	label:""+result[i].partyName,
		children:[ 
					{
						label : ""+prevYear,
						key : "partyPrevCount"+i,
						sortable : true
					},
					{
						label : ""+presentYear,
						key : "partyCount"+i,
						sortable : true
					}				
				]
    		};
	  }
    		resultsColumnDefs.push(obj);
	}
	var myConfigs = { 
				initialRequest: "sort=prevTotalCount&dir=asc&startIndex=0&results=10", // Initial request for first page of data
				//dynamicData: true, // Enables dynamic server-driven data
				sortedBy : {key:"prevTotalCount", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    })
				};
    myDataTable = new YAHOO.widget.DataTable("districtAny",resultsColumnDefs, resultsDataSource,myConfigs); 

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
         //document.getElementById("analysisTable").innerHTML= '';
		 //document.getElementById("analysisTableDisplay").innerHTML= '';
         //removeData("partiesIdDiv");
         var yearEle =  document.getElementById("yearSelId");
	     var eleId = yearEle.options[yearEle.selectedIndex].value;
		 var stateEle =  document.getElementById("stateListId");
	     var stateId = stateEle.options[stateEle.selectedIndex].value;
		 //document.getElementById("select_ImgSpan").style.display="block";
         var jsObj =
		  { 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateId,
			type:"graph",
			task:"getAllPartiesForResults"
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
  function buildParties(results,id)
 {
   var elmt = document.getElementById(id);
   
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

    document.getElementById("analysisTable").innerHTML= '';
    document.getElementById("analysisTableDisplay").innerHTML= '';
      validate = false;
	   var str='';
	   document.getElementById("hideSelect").style.display="none";
       document.getElementById("districtPartyChart").innerHTML="";
       document.getElementById("districtPartyChartMore").innerHTML="";
       document.getElementById("errorMessage").innerHTML = "";
       document.getElementById("districtAny").innerHTML = "";
	   document.getElementById("districtAnyHeading").innerHTML = "";
	   document.getElementById("districtChart").innerHTML = "";
	   document.getElementById("districtPartyChart").innerHTML = "";
	   document.getElementById("districtPartyChartMore").innerHTML = "";
	   document.getElementById("hideSelect").style.display ="none";
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 var yearEle =  document.getElementById("stateListId");
	 var stateListId = yearEle.options[yearEle.selectedIndex].value;
	 var partyIds ='';
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
	 var partyEle = document.getElementById("partiesIdDiv");
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
	   {
	     validate = true;
	       str+='<b><font style="color:red;font-size:12px;">Please Select Atleast One Party </font></b><br />';
	   }
	 if(validate)
	  {
         document.getElementById("errorMessage").innerHTML = str;
    	return;
	  } 
	  
	 document.getElementById("resultImgDiv").style.display="block";
	  partyIds = partyIds.slice(0, -1);
	 var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			stateId:stateListId,
			partyIds:partyIds,
			task:"getDistrictWiseAnalysisDetails"
		};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
	getDataForChart('main');
	getAnalysisDetails('main');
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
 function getDataForChart(name)
 {
	if(name == "main" ){
     var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 var partyIds = [];
			$('#partiesIdDiv option:selected').each(function(){ partyIds.push($(this).val()); });
			var result = partyIds.join(',');
			var partyEle =  document.getElementById("parties");
			document.getElementById("searchImage").style.display="block";
	  var jsObj =
		{ 
            time : new Date().getTime(),
			electionId: eleId,
			partyIds:result,
			task:"getpartyPerfoDistWiseForPartialEle"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getministersDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
	
	}
	else
	{
		var yearEle =  document.getElementById("yearSelId");
	 var eleId = yearEle.options[yearEle.selectedIndex].value;
	 var partyIds='';
	 var partyName = $('#partiesIdDiv :selected').val();
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
	    //if(count == 0)
	     //return;
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
   document.getElementById("districtPartyChartMore").innerHTML ='';
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
		  draw(data, {curveType: "function",width: 1002, height: 520,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:13,fontName:"verdana"},slantedText:true,slantedTextAngle:35}});
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
