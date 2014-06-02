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
	<div id="containeradsdres" style="clear:both;margin-top:10px;margin-bottom:10px;"></div>
	
	<!-- end -->
	<!-- prasad -->
	<div id="genderWiseChart" style="margin-top:10px;margin-bottom:10px;"></div>
	<div id="genderWiseTable" style="margin-top:10px;margin-bottom:10px"></div>
	
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



function buildChartForTopCaste(result1){
var result = result1[0];
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
    dataArray.push(result[j].percents[i]);
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
var votsResult = result1[1];

var str="";
    str+="<table id='casteTable1' class='table table-bordered'>";
	str+="<tr>";
	str+="<th>Survey</th>";
	for(var i =0;i<result[0].optionsList.length;i++){
      str+="<th>"+result[0].optionsList[i]+"</th>";
    }
	str+="<th>Total</th>";
	str+="<th>Party Secured In 2014 AC</th>";
	str+="</tr>";
	for(var i in votsResult){
	var total = 0;
	str+="<tr>";
	 str+="<td>"+votsResult[i].name+"</td>";
	 for(var j =0;j<votsResult[i].locationValuesList.length;j++){
	   if(votsResult[i].locationValuesList[j] != null){
         str+="<td>"+votsResult[i].locationValuesList[j]+"</td>";
		 total =total+votsResult[i].locationValuesList[j];
		}else{
		  str+="<td>-</td>";
		}
     }
	 str+="<td>"+total+"</td>";
	 if(i == 0){
	      str+="<td rowspan='"+votsResult.length+"'>"+votsResult[0].totalCount+"</td>";
	 }
	 str+="</tr>";
	}
	str+="</table>";
	$("#containeradsdres").html(str);
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
			var total = 0;
				total = result[i].maleCount + result[i].femaleCount + result[i].youngerCount + result[i].olderCount;
					names.push(result[i].name);
					var malePer = (result[i].maleCount * 100/ total).toFixed(2);
					
					var femalePer = (result[i].femaleCount * 100/ total).toFixed(2);
					var youngPer = (result[i].youngerCount * 100/ total).toFixed(2);
					var oldPer = (result[i].olderCount * 100/ total).toFixed(2);
					
					maleArray.push(parseFloat(malePer));
					femaleArray.push(parseFloat(femalePer));
					youngArray.push(parseFloat(youngPer));
					oldArray.push(parseFloat(oldPer));
				}
				
				
				
				buildChartForGenderWise(names,maleArray,femaleArray,youngArray,oldArray);

				//genderWiseTable(result);
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
                text: 'Gender Wise Surey Details'
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
	var str = '';
	str += '<table class="table table-bordered" style="margin-left:64px;width:50%;">';
	str += '<tr>';
	str += '<th>Name</th>';
	str += '<th>Male</th>';
	str += '<th>FeMale</th>';
	str += '<th>18 to 25</th>';
	str += '<th>Above 60</th>';
	str += '</tr>';
	for(var i in result)
	{
		var maleFor = result[i].maleCount-(result[i].youngerCount+result[i].olderCount);
	var femaleFor = result[i].femaleCount-(result[i].youngerCount+result[i].olderCount); 
	str += '<tr>';
	str += '<td></td>';
	str += '<td>'+maleFor+'</td>';
	str += '<td>'+femaleFor+'</td>';
	str += '<td>'+result[i].youngerCount+'</td>';
	
	str += '<td>'+result[i].olderCount+'</td>';
	str += '</tr>';
	}
	str += '</table>';
	$("#genderWiseTable").html(str);
}
/* end */
</script>
