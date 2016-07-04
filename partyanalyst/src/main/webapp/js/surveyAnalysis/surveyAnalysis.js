function buildChart(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'votesObtained');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].votesObtained);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:400, height:300, title: "", legendFontSize:14,fontSize:13});
}

function buildChartForOptionSurveyAnalysis(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'optionCount');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].optionCount);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:350, height:200, title: "", legendFontSize:10,fontSize:10});
}

function buildChartForSurveyAnalysis(result,div)
{
var chartDiv = document.getElementById(''+div+'');

var data = new google.visualization.DataTable();
data.addColumn('string', 'option');
data.addColumn('number', 'total');
data.addRows(result.length);
		
		for (var i=0;i<result.length;i++)
			{
			
			data.setValue(i, 0,result[i].option);
			data.setValue(i, 1,result[i].total);
			}
			var chart = new google.visualization.PieChart(chartDiv);
					chart.draw(data, {width:350, height:200, title: "", legendFontSize:10,fontSize:10});
}

function getDistricts()
{
	var selectedId = $("#stateId option:selected").val();
	var jsObj=
		{				
			id: selectedId,
			task: "districtsInState",
			taskType:"cadreReg",
			selectElementId: "districtField_s" ,
			address: "cadreLevel",
			areaType: "null",
			constId: "",
			isParliament : "null"
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getConstituencys()
{
	var selectedId = $("#districtId option:selected").val();
	var jsObj=
		{				
			id: selectedId,
			task: "constituenciesInDistrict",
			taskType:"cadreReg",
			selectElementId: "constituencyField_s" ,
			address: "cadreLevel",
			areaType: "null",
			constId: "",
			isParliament : "null"			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "locationsHierarchiesAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getMandals(){
	constituencyId = $("#constituencyList option:selected").val();
	reportLevel = "Constituency";
	  var jsObj=
			{
					
					selected:constituencyId,
					selectElmt:"mandalField",
					str   : "all",
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function getVillages(id,value){
	
	  var jsObj=
			{
					
					id:id,
					task:"hamletsOrWardsInRegion"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVilliageListAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function callAjax(jsObj,url)
{
			 var myResults;
			 var callback = {			
 		               success : function( o ) {
							try {												
								    myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
									if(jsObj.task == "districtsInState")
									{
										buildDistrictes(myResults);
									}
									else if(jsObj.task == "constituenciesInDistrict")
									{
										buildConstituenceys(myResults);
									}
									else if(jsObj.task == "getMandalList")
									{
										buildMandals(myResults);
									}
									else if(jsObj.task == "hamletsOrWardsInRegion")
									{
										//console.log("myResults : "+myResults);
										buildVilliages(myResults);
									}
									}catch (e) {
								
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };
 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}

function buildDistrictes(myResults)
{
	var str = "";
	$("#districtId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#districtId').html(str);
}

function buildConstituenceys(myResults)
{
	var str = "";
	$("#constituencyList option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#constituencyList').html(str);
}

function buildMandals(myResults)
{
	var str = "";
	$("#tehsilId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#tehsilId').html(str);
	
}

function buildVilliages(myResults)
{
	var str = "";
	$("#villageId option").remove();
	for(var i in myResults)
	{
		str += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
	}
	$('#villageId').html(str);
	
}