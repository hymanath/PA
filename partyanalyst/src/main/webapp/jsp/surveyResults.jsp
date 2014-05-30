<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/highcharts4.1/js/highcharts.js"></script>
<script type="text/javascript" src="js/highcharts4.1/js/highcharts-3d.js"></script>
<script src="http://code.highcharts.com/modules/funnel.js"></script>
<!--<script src="http://code.highcharts.com/modules/exporting.js"></script>-->
</head>
<body>
<script>


$('document').ready(function(){
	$('#stateId').change(function(){
		getSurveyDetaisByRegionid(this.value);
		getConstituencyDetaisByRegionid(this.value);
	});
 
});
</script>

<div class="container" style="margin_top:20px;">
<div class="offset3">
	<div class="span8">
	   <div class="span3">Select State</div>
	   <div class="span5">
		<select id="stateId">
		 <option value="1">Telangana</option>
		 <option value="2">Seemandra</option>
		</select>
	   </div>
	  </div>

  <div class="span8">
   <div class="span3">Select Constituency</div>
   <div class="span5">
    <select id="constituencyId">
		</select>
   </div>
  </div>


  <div class="span8">
   <div class="span3">Select Survey(s)</div>
   <div class="span5">
    <select multiple="true" id="surveyId">
	</select>
   </div>
  </div>
	<input type="button" value="TEST" onClick="getPartyWiseCountDetailsForSelectedSurveys()"/>

</div>

	<div class="span12"> 
		<div id="container1" style="width:180px; height:300px;float:left;"></div>
		<div id="container2" style="width:550px; height:300px;"></div>
		<div id="winningCandidateInfo" style="width:100px;float:left;"></div>
	</div>

</div>
<script>
var partyWiseCountDtls = {
	electionId:'',
    constituencyId:'',
	surveyIds:[]
};
function getPartyWiseCountDetailsForSelectedSurveys()
{
	partyWiseCountDtls.electionId = 258;
	partyWiseCountDtls.constituencyId = $('#constituencyId').val();
	partyWiseCountDtls.surveyIds = $('#surveyId').val();


	$.ajax({
          type:'POST',
          url: 'getPartyWiseCountDetailsByConstituencyIdAndSurveyIds.action',
          dataType: 'json',
          data: {task:JSON.stringify(partyWiseCountDtls)},
     	  }).done(function(result){
             buildSurveyElectionComparison(result);
			 buildTop5CastesDetails(result.casteDetails);
   	     });
}
function buildSurveyElectionComparison(result)
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
            text: 'Top 5 Castes Details',
            x: -50
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
	$.ajax({
          type:'POST',
          url: 'getSurveyDetailsByRegion.action',
          dataType: 'json',
          data: {regionId:regionId},
     	  }).done(function(result){
			 buildOptions(result,"surveyId");
   	     });
}
function getConstituencyDetaisByRegionid(regionId)
{
	$.ajax({
          type:'POST',
          url: 'getConstituencyDetaisByRegionid.action',
          dataType: 'json',
          data: {regionId:regionId},
     	  }).done(function(result){
			  buildOptions(result,"constituencyId");
   	     });
}
function buildOptions(result,id)
{
	$('#'+id).find('option').remove();

	$.map(result,function(index,value){
     $('#'+id).append('<option value="'+value+'">'+index+'</option>');
	});

}
</script>
</body>
</html>