<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" src="js/highcharts4.1/js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts4.1/js/highcharts-3d.js"></script>
<script src="http://code.highcharts.com/modules/funnel.js"></script>
<!--<script src="http://code.highcharts.com/modules/exporting.js"></script>-->

<style>
	#casteTable1 th{color:#5FB9B9;background-color: #DDDDDD;border:1px solid black !important;}
#casteTable1 td
{
border:1px solid black !important;
}
</style>

<!-- start chart Analysis tab -->


<div>
<table class="offset1 headingTbl">

<tr><td class="span3">Select State<select id="stateIdForChart" class="input-block-level">
	 <option value="1">Telangana</option>
	 <option value="2">Seemandra</option>
	 </select></td>
						    <td class="span3"> Select Constituency<img src="./images/icons/search.gif" alt="Processing Image" id="constituencyIdForChartImg" style="display:none;"/><select class="input-block-level" id="constituencyIdForChart" style="width:96%;">
							
						   </select>	</td>

	 <td class="span3">Select Survey(s) <img src="./images/icons/search.gif" alt="Processing Image" id="surveyIdForChartImg" style="display:none;"/><select class="input-block-level"  multiple="true" id="surveyIdForChart" style="width:96%;height:55px;">
								
						   </select>	</td>

						   
			

<td class="span3"> 

<a onClick="getPartyWiseCountDetailsForSelectedSurveys();genderWiseReport();getCasteData();" value="Submit" class="btn" style="margin-top:0px; background: none repeat scroll 0 0 #0088CC; color: #FFFFFF;font-weight: normal;">Submit</a>
</td>	

	
</tr>
</table>
</div>
<!-- end -->

<div style="margin-left:400px;margin-top:20px;display:none;" id="chartAnalysisAjax"><img src="./images/Loading-data.gif" alt="Processing Image" /></div>
<div id="errorDivForChartAnalysis" ></div>
	<div class="span12" style="margin-bottom:30px"> 
		<div id="container1" style=" height:300px;float:left;" class="span5"></div>
		<div id="container2" style=" height:300px;" class="span6"></div>
		<div id="winningCandidateInfo" style="margin-top:10px;float:left;" class="span"></div>
	</div>
	<!-- Mahesh start -->
	<div id="containeradsd" style="clear:both;margin-top:10px;margin-bottom:10px;"></div>
	<div id="containeradsdres" style="overflow-x:scroll;clear:both;margin-top:10px;margin-bottom:10px;"></div>
	<div id="containeradsdres1" style="overflow-x:scroll;clear:both;margin-top:10px;margin-bottom:10px;"></div>
	<!-- end -->
	<!-- prasad -->
	<div id="genderWiseChart" style="margin-top:10px;margin-bottom:10px;"></div>
	<div id="genderWiseTable" style="margin-top:10px;margin-bottom:10px"></div>
		<div id="genderWiseConstSummary" style="margin-top:10px;margin-bottom:10px;margin-left:30px;"></div>
	    <div id="genderWiseConstSurveySummary" style="margin-top:10px;margin-bottom:10px;margin-left:30px;"></div>
	    
		<div id="genderWiseTableIndividual" style="margin-top:10px;margin-bottom:10px;margin-left:30px;"></div>
	
	
	<!-- end -->

<!-- end -->

<script>
$('document').ready(function(){
	getConstituencyDetaisByRegionid(1);
	getSurveyDetaisByRegionid(1);
	$('#stateIdForChart').change(function(){
		getSurveyDetaisByRegionid(this.value);
		getConstituencyDetaisByRegionid(this.value);
	});
 
});
var partyWiseCountDtls = {
	electionId:'',
    constituencyId:'',
	surveyIds:[]
};
function getPartyWiseCountDetailsForSelectedSurveys()
{
	$('#winningCandidateInfo').html('');
	$('#container2').html('');
	$('#container1').html('');
	
	partyWiseCountDtls.electionId = 258;
	partyWiseCountDtls.constituencyId = $('#constituencyIdForChart').val();
	partyWiseCountDtls.surveyIds = $('#surveyIdForChart').val();
	if(partyWiseCountDtls.surveyIds == null)
	{
	$("#errorDivForChartAnalysis").html('Select atleast one survey').css("color","red");
	return;
	}
	$("#errorDivForChartAnalysis").html('');
	$("#chartAnalysisAjax").show();
	$.ajax({
          type:'POST',
          url: 'getPartyWiseCountDetailsByConstituencyIdAndSurveyIds.action',
          dataType: 'json',
          data: {task:JSON.stringify(partyWiseCountDtls)},
     	  }).done(function(result){
             buildSurveyElectionComparison(result);
			 buildTop5CastesDetails(result.casteDetails);
			 $("#chartAnalysisAjax").hide();
   	     });
}
/* function buildSurveyElectionComparison(result)
{

	var electionData = [];
	var surveyData   = [];
	var partiesDetails   = [];
	$.each(result.subList,function(index,value){
		partiesDetails.push(value.name);
		surveyData.push(parseFloat(value.surveyPercent));
		electionData.push(parseFloat(value.percent));
	});

	$.each(result.subList,function(index,value){
		if(value.rank == 1)
		{
			var str='';
	str+='<h2 style="color:#0088CC;font-family:verdana;font-size:12px;font-weight:bold;">Won Candidate Info </h2>';
            str+='<table border="1">';
			 str+='<tr>';
			  str+='<th>'+value.candidateName+'</th>';
			  str+='<th>'+value.name+'</th>';
			 str+='</tr>';
			str+='</table>';

			$('#winningCandidateInfo').html(str);
		}
	});
	

	
    
        $('#container2').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'PartyWise Survey Result',
				 style: {
            color: '#0088CC',
            fontWeight: 'bold',
          
			'font-family':'verdana',
			'font-size':'12px'
			
        }

            },
            subtitle: {
                text: 'Form Survey'
            },
            xAxis: {
                categories:partiesDetails
            },
            yAxis: {
                min: 0,
                title: {
                    text: '<font style="font-weight:bold;color:#222222;">Voting Percentage</font>'
                }
            },


				 plotOptions: {
					series: {
						dataLabels: {
							enabled: true,
							format: '{point.y:.1f}%',
								 style: {
							fontSize: '11px',
							fontFamily: 'Verdana, sans-serif'

							}

					}
					}
			},
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}% </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
          
            series: [{
                name: 'Survey',
                data: surveyData
    
            }, {
                name: 'Election',
                data: electionData
    
            }]
        });

} */

function buildSurveyElectionComparison(result)
{
var electionData = [];
var surveyData = [];
var partiesDetails = [];
$.each(result.subList,function(index,value){
partiesDetails.push(value.name);
surveyData.push(parseFloat(value.surveyPercent));
electionData.push(parseFloat(value.percent));
});


$.each(result.subList,function(index,value){

	if(value.rank == 1)
	{
		var str='';

		str+='<table class="table table-bordered">';
		 str+='<thead>';
			str+='<tr>';
				str+='<th>Candidate</th>';
				str+='<th>Party</th>';
				str+='<th>Votes(%)</th>';
				str+='<th>Margin(%)</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			str+='<tr>';
				str+='<td>'+value.candidateName+'</td>';
				str+='<td>'+value.name+'</td>';
				str+='<td>'+value.percent+'</td>';
				str+='<td>'+value.marginPercent+'</td>';
			str+='</tr>';
		str+='</tbody>';
		
		str+='</table>';

		$('#winningCandidateInfo').html(str);
	}
});


var surveyChartInput = [];
var surveyDetails = [];

for(var i in result.surveyWiseResult)
{
surveyDetails.push(result.surveyWiseResult[i].name);
}

for(var i in result.surveyWiseResult[0].subList)
{
var obj ={name:result.surveyWiseResult[0].subList[i].name,data: []};
surveyChartInput.push(obj);
}

for(var i in result.surveyWiseResult)
{
for(var k in result.surveyWiseResult[i].subList)
{
for(var j in surveyChartInput)
{
if(surveyChartInput[j].name == result.surveyWiseResult[i].subList[k].name)
{
surveyChartInput[j].data.push(parseFloat(result.surveyWiseResult[i].subList[k].percent));
}
}
}
}

$('#container2').highcharts({
chart: {
type: 'column'
},
title: {
text: 'PartyWise Voting Percent From Survey',
style: {
            color: '#0088CC',
            fontWeight: 'bold',
          
			'font-family':'verdana',
			'font-size':'12px'
			}
},
subtitle: {
text: ''
},
xAxis: {
categories: surveyDetails,
labels: {

style: {
color: '#E00000'
}
}

},
yAxis: {
min: 0,
title: {
text: '<font style="font-family:verdana,font-size:12px;color:#E00000;">Voting Percent</font>'
}
},
tooltip: {
headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
'<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
footerFormat: '</table>',
shared: true,
useHTML: true
},
plotOptions: {
 series: {
dataLabels: {
							enabled: true,
							format: '{point.y:.1f}%',
								 style: {
							fontSize: '11px',
							fontFamily: 'Verdana, sans-serif'

							}

					}
					}
},
series:surveyChartInput
});


/*
$('#container2').highcharts({
chart: {
type: 'column'
},
title: {
text: 'Party Wise Survey Result'
},
subtitle: {
text: 'Form Survey'
},
xAxis: {
categories:partiesDetails
},
yAxis: {
min: 0,
title: {
text: ''
}
},
tooltip: {
headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
'<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
footerFormat: '</table>',
shared: true,
useHTML: true
},
plotOptions: {
column: {
pointPadding: 0.2,
borderWidth: 0
}
},
series: [{
name: 'Survey',
data: surveyData

}, {
name: 'Election',
data: electionData

}]
});
*/



}





function buildTop5CastesDetails(casteDetails)
{
	var topCastes = [];

	$.each(casteDetails.reverse(),function(index,value){
		var obj=[value.name,parseFloat(value.percent)];
		topCastes.push(obj);
	});

$('#container1').highcharts({
        chart: {
            type: 'pyramid',
            marginRight: 100
			
        },
        title: {
            text: 'Top Castes Details',
            x: -50,
			
				 style: {
            color: '#0088CC',
            fontWeight: 'bold',
          
			'font-family':'verdana',
			'font-size':'12px'
			
        }

        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b> ({point.y:,.0f})',
                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
                    softConnector: true
                }
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: 'Caste Percent',
            data: topCastes
        }]
    });
}
function getSurveyDetaisByRegionid(regionId)
{
	$("#surveyIdForChartImg").show();
	$.ajax({
          type:'POST',
          url: 'getSurveyDetailsByRegion.action',
          dataType: 'json',
          data: {regionId:regionId},
     	  }).done(function(result){
			  $("#surveyIdForChartImg").hide();
			 buildOptions(result,"surveyIdForChart");
   	     });
}
function getConstituencyDetaisByRegionid(regionId)
{
	$("#constituencyIdForChartImg").show();
	$.ajax({
          type:'POST',
          url: 'getConstituencyDetaisByRegionid.action',
          dataType: 'json',
          data: {regionId:regionId},
     	  }).done(function(result){
			  $("#constituencyIdForChartImg").hide();
			  buildOptions(result,"constituencyIdForChart");
   	     });
}
function buildOptions(result,id)
{
	
	
	$('#'+id).find('option').remove();

	$.map(result,function(index,value){
     $('#'+id).append('<option value="'+value+'">'+index+'</option>');
	});

}

/* mahesh start */
function getCasteData(){
$("#containeradsd").html('');
$("#containeradsdres").html('');
$("#containeradsdres1").html('');
var surveyIDs = $("#surveyIdForChart").val();
if(surveyIDs == null)
	return;

var constituencyID = $("#constituencyIdForChart").val();
        var jsObj=
	{
			constituencyId : constituencyID,
			surveyIds :surveyIDs.toString()
			
	};
	$.ajax({
	type: "GET",
	url: "getTop5CastePartySupport.action",
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	 error:function() { 
           
         }
	})
	.done(function( result ) {
		buildChartForTopCaste(result);
		try{
		 
		}catch(e){
		  
		}
	});
}



function buildChartForTopCaste(result){
var colorsArray = new Array();
colorsArray.push("#F93535");
colorsArray.push("#4CF935");
colorsArray.push("#F9F235");
colorsArray.push("#3553F9");
colorsArray.push("#E935F9");
colorsArray.push("#090404");
var surveyArray = new Array();
var casteArray = new Array();
for(var i in result){
surveyArray.push(result[i].name);
}
for(var i =0;i<result[0].optionsList.length;i++){
  var obj={};
  obj['type'] = 'column';
  obj['name'] = result[0].optionsList[i];
  var dataArray = new Array();
  for(var j in result){
    dataArray.push(result[j].percents[i].percentage);
  }
  obj['data'] = dataArray;
  obj['color']= colorsArray[i] ;
  obj['tooltip']= {
			valueSuffix:'%'
        };
  casteArray.push(obj);
 // console.log(casteArray);
}

var obj={};

        obj['type']= 'pie';
        obj['name']= 'Avg Percent';
		var casteSubArray = new Array();
		 for(var i =0;i<result[0].optionsList.length;i++){
		    var obj1={};
			  obj1['name']= result[0].optionsList[i];
            obj1['y']= result[0].avgPercs[i];
            obj1['color']= colorsArray[i] ;
			casteSubArray.push(obj1);
		}
        obj['data'] =  casteSubArray;
        obj['center']= [38, 8];
        obj['size']= 100;
        obj['showInLegend']= false;
        obj['dataLabels']= {
            enabled: false
        };
    casteArray.push(obj);

$('#containeradsd').highcharts({
    title: {
        text: 'Top Castes Lineance Towards TDP Analysis',
				 style: {
            color: '#0088CC',
            fontWeight: 'bold',
          
			'font-family':'verdana',
			'font-size':'12px'
			
        }

    },
    xAxis: {
        categories: surveyArray,
		labels: {

style: {
color: '#E00000'
}
}

    },
			 yAxis: {
			min: 0,
			title: {
			text: '<font style="font-family:verdana,font-size:12px;color:#E00000;">Caste Percentage</font>'
			}
			},
    labels: {
        items: [{
            html: 'Top Castes Average %',
            style: {
                left: '5px',
                top: '-38px',
                color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
            }
        }]
    },
			plotOptions: {
					series: {
						dataLabels: {
							enabled: true,
						
							format: '{point.y:.1f}%',
								 style: {
							fontSize: '11px',
							fontFamily: 'Verdana, sans-serif'
						

							}

					}
					}
			},
    series: casteArray
});
var votsResult = result;

var str="";
    str+="<table id='casteTable1' class='table table-bordered'>";
	str+="<tr>";
	str+="<th rowspan='2'>Survey</th>";
	str+="<th rowspan='2'>Total Samples</th>";
	for(var i =0;i<result[0].optionsList.length;i++){
      str+="<th colspan='3'>"+result[0].optionsList[i]+"</th>";
    }
	str+="<th rowspan='2'>Total</th>";
	str+="<th rowspan='2'>Party Secured In 2014 AC</th>";
	str+="</tr>";
	str+="<tr>";
	for(var i =0;i<result[0].optionsList.length;i++){
      str+="<th>Caste Samples</th>";
	  str+="<th>TDP Support</th>";
	   str+="<th>TDP Votes</th>";
    }
	str+="</tr>";
	for(var i in votsResult){
	var total = 0;
	str+="<tr>";
	 str+="<td>"+votsResult[i].name+"</td>";
	 str+="<td>"+votsResult[i].count+"</td>";
	 for(var j =0;j<votsResult[i].percents.length;j++){  
	     str+="<td>"+votsResult[i].percents[j].total+"("+votsResult[i].percents[j].totalPercentage+"%)</td>";
	     str+="<td>"+votsResult[i].percents[j].votesObtained+"("+votsResult[i].percents[j].percentage+"%)</td>";
         str+="<td>"+votsResult[i].percents[j].goodBoothCount+"</td>";
		 total =total+votsResult[i].percents[j].goodBoothCount;
		
     }
	 str+="<td>"+total+"</td>";
	 if(i == 0){
	      str+="<td rowspan='"+votsResult.length+"'>"+votsResult[0].total+"</td>";
	 }
	 str+="</tr>";
	}
	str+="</table>";
	$("#containeradsdres").html(str);
	
	var str="";
    str+="<table id='casteTable1' class='table table-bordered'>";
	str+="<tr>";
	str+="<th rowspan='2'>Survey</th>";
	for(var i =0;i<result[0].optionsList.length;i++){
      str+="<th colspan='2'>"+result[0].optionsList[i]+"</th>";
    }
	str+="<th rowspan='2'>Total</th>";
	str+="<th rowspan='2'>Party Secured In 2014 AC</th>";
	str+="</tr>";
	str+="<tr>";
	for(var i =0;i<result[0].optionsList.length;i++){
	  str+="<th>TDP Support %</th>";
	   str+="<th>TDP Votes</th>";
    }
	str+="</tr>";
	for(var i in votsResult){
	var total = 0;
	str+="<tr>";
	 str+="<td>"+votsResult[i].name+"</td>";
	 for(var j =0;j<votsResult[i].correctionPercs.length;j++){  
	     str+="<td>"+votsResult[i].correctionPercs[j].percentage+"</td>";
         str+="<td>"+votsResult[i].correctionPercs[j].goodBoothCount+"</td>";
		 total =total+votsResult[i].correctionPercs[j].goodBoothCount;
		
     }
	 str+="<td>"+total+"</td>";
	 if(i == 0){
	      str+="<td rowspan='"+votsResult.length+"'>"+votsResult[0].total+"</td>";
	 }
	 str+="</tr>";
	}
	str+="</table>";
	$("#containeradsdres1").html(str);
}

/* end */


/* prasad */
function genderWiseReport()
{
$("#genderWiseChart").html('');
$("#genderWiseTable").html('');
var surveyIds = new Array();
var surveyIDs = $("#surveyIdForChart").val();
var constituencyID = $("#constituencyIdForChart").val();
if(surveyIDs == null)
	return;
for(var i in surveyIDs)
{
	surveyIds.push(parseInt(surveyIDs[i]));
}
	var jsObj = 
	{
		partyId:872,
		constituencyId : constituencyID,
		surveyIds : surveyIds,
		task:"getPartiesGainAndLossInfo"
	}
	$.ajax({
          type:'GET',
          url: 'getGenderWiseReportAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  if(result != null){
				var names = new Array();
				var maleArray = new Array();
				var femaleArray = new Array();
				var youngArray = new Array();
				var oldArray = new Array();
				for(var i in result)
				{
			/*var total = 0;
				total = result[i].maleCount + result[i].femaleCount + result[i].youngerCount + result[i].olderCount;
					names.push(result[i].name);
					var malePer = (result[i].maleCount * 100/ total).toFixed(2);
					
					var femalePer = (result[i].femaleCount * 100/ total).toFixed(2);
					var youngPer = (result[i].youngerCount * 100/ total).toFixed(2);
					var oldPer = (result[i].olderCount * 100/ total).toFixed(2);*/
					
					maleArray.push(result[i].malePercent);
					femaleArray.push(result[i].femalePercent);
					youngArray.push(result[i].youngerPercent);
					oldArray.push(result[i].elderPercent);
				}
				
				
				
				buildChartForGenderWise(names,maleArray,femaleArray,youngArray,oldArray);

				genderWiseTable(result);
			}
	   });
}


function buildChartForGenderWise(names,maleArray,femaleArray,youngArray,oldArray)
{

		//$('#liveResultsDiv').show();
        $('#genderWiseChart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Age Wise Surey Details'
				,
				 style: {
            color: '#0088CC',
            fontWeight: 'bold',
          
			'font-family':'verdana',
			'font-size':'12px'
			
        }
            },
           
            xAxis: {
                categories:names,
				labels: {

style: {
color: '#E00000'
}
}

            },
			
            yAxis: {
			min: 0,
			title: {
			text: '<font style="font-family:verdana,font-size:12px;color:#E00000;">Gender Wise Voter Percentage</font>'
			}
			},
				plotOptions: {
					series: {
						dataLabels: {
							enabled: true,
							format: '{point.y:.1f}%',
								 style: {
							fontSize: '11px',
							fontFamily: 'Verdana, sans-serif'

							}

					}
					}
			},
			
           tooltip: {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			'<td style="padding:0"><b>{point.y:.1f}%</b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
			},
          
			/* legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'top',
			x: -40,
			y: 100,
			floating: true,
			borderWidth: 1,
			backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
			shadow: true
		},  */
            credits: {
                enabled: false
            },
            series: [{
				name: 'Male',
				data: maleArray
				}, {
				name: 'Female',
				data: femaleArray
				}, {
				name: 'Young',
				data: youngArray
				}, {
				name: 'Old',
				data: oldArray
				}
				]
        });

}

function genderWiseTable(result)
{

	//actual summary
	var str1 = '';
	str1+='<h4 style="color: #0088CC;fontWeight:bold;font-family:verdana;font-size:12px">Constituency Summary Table</h4>';
	
	str1 += '<table class="table table-bordered" style="margin-left:64px;width:50%;">';
	str1 += '<tr>';
	str1 += '<th>Total</th>';
	str1 += '<th>Male</th>';
	str1 += '<th>Female</th>';
	str1 += '<th>18 to 25</th>';
	str1 += '<th>Above 60</th>';
	str1 += '</tr>';


	var str2 = '';
	str2+='<h4 style="color: #0088CC;fontWeight:bold;font-family:verdana;font-size:12px">Constituency Survey Summary Table</h4>';
	
	str2 += '<table class="table table-bordered" style="margin-left:64px;width:50%;">';
	str2 += '<tr>';
	
	str2 += '<th>Male</th>';
	str2 += '<th>Female</th>';
	str2 += '<th>18 to 25</th>';
	str2 += '<th>Above 60</th>';
	str2 += '</tr>';
	
	for(var i in result)
	{
	 console.log(result[i].actualTotal);
	 
	str1 += '<tr>';
	str1 += '<td>'+result[i].actualTotal+'</td>';
	str1 += '<td>'+result[i].actualmaleCount+'</td>';
	str1 += '<td>'+result[i].actualFemaleCount+'</td>';
	str1 += '<td>'+result[i].actualYoungVoters+'</td>';	
	str1 += '<td>'+result[i].actualYelderVoters+'</td>';
	str1 += '</tr>';



	 
	str2 += '<tr>';
	
	str2 += '<td>'+result[i].middleageMaleVotersCumm+'</td>';
	str2 += '<td>'+result[i].middleAgeFemaleVotersCumm+'</td>';
	str2 += '<td>'+result[i].youngVOtersCumm+'</td>';	
	str2 += '<td>'+result[i].yeldervotersCumm+'</td>';
	str2 += '</tr>';

	break;
	}
	str2 += '</table>';
	str1 += '</table>';

	$("#genderWiseConstSummary").html(str1);
		$("#genderWiseConstSurveySummary").html(str2);
	
		var str = '';
			
		str+='<h4 style="color: #0088CC;fontWeight:bold;font-family:verdana;font-size:12px">Individual Survey Summary Table(s)</h4>';
		str += '<table class="table table-bordered" style="margin-left:64px;width:50%;">';
		str += '<th>';
		str += '<th></th>';
		str += '<th colspan="3">Male</th>';
		str += '<th colspan="3">Female</th>';
		str += '<th colspan="3">Youngers</th>';
		str += '<th colspan="3">Elders</th>';
		str += '<th>Total</th>';
		str += '</th>';
		str += '<th>';
		str += '<th>Survey Name</th>';
		str += '<th >Total Male Samples</th>';
		str += '<th >Tdp Male Samples</th>';
		str += '<th >Male Count</th>';
		str += '<th >Total Female Samples</th>';
		str += '<th >Tdp Female Samples</th>';
		str += '<th >Female</th>';
		str += '<th >Total Youngers Samples</th>';
		str += '<th >Tdp Youngers Samples</th>';
		str += '<th >Youngers</th>';
		str += '<th >Total Elders Samples</th>';
		str += '<th >Tdp Elders Samples</th>';
		str += '<th>Elders</th>';
		str += '<th>Total</th>';
		str += '</th>';
	
	for(var i in result)
	{
	

	str += '<tr>';
	str += '<td>'+result[i].name+'</td>';
	
	str += '<td>'+result[i].actualmaleCountPercentage+'('+result[i].surveyMalePercent+')</td>';
	str += '<td>'+result[i].maleCount+'('+result[i].malePercent+')</td>';
	str += '<td>'+result[i].maleFromTotal+'</td>';
	
	str += '<td>'+result[i].actualFemaleCountPercentage+'('+result[i].surveyFemalePercent+')</td>';
	str += '<td>'+result[i].femaleCount+'('+result[i].femalePercent+')</td>';
	str += '<td>'+result[i].femaleFromTotal+'</td>';
	
	
	str += '<td>'+result[i].actualYoungVotersPercentage+'('+result[i].surveyYoungerPercent+')</td>';
	str += '<td>'+result[i].youngerCount+'('+result[i].youngerPercent+')</td>';
	str += '<td>'+result[i].youngerFromTotal+'</td>';
	
	str += '<td>'+result[i].actualYelderVotersPercentage+'('+result[i].surveyElderPercent+')</td>';
	str += '<td>'+result[i].olderCount+'('+result[i].elderPercent+')</td>';
	str += '<td>'+result[i].olderFromTotal+'</td>';
	
	str += '<td>'+result[i].totalFromTotal+'</td>';
	str += '</tr>';
	
	//$("#genderWiseConstSummary").html(str);
	//var strId="individual"+i;
	
	//$("#genderWiseTableIndividual").append("<div id="+strId+"></div>")
	//$("#"+strId).html(str);
	}
	str += '</table>';
	$("#genderWiseTableIndividual").html(str);
	
}
/* end */
</script>
