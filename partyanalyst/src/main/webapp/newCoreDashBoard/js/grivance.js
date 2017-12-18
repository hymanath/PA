
getCadreGreivienceEfficiency();
function getCadreGreivienceEfficiency(){
		var jobj = {                                      
			daysArr : [30,60,90,180,365],
			grievanceReqType:"Grievance",
			enrollmentyearId:0,
		}
		$.ajax({
			type : "POST",
			url  : "getEffiencyDetailsForgrivanceByDatesAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){
				 buildGrivanceEfficiency(result);
			}
		});
}
function buildGrivanceEfficiency(result){
	var str='';
		for(var i in result){
				str+='<div class="col-sm-2">';
					str+='<table class="table tableefficiency">';
						str+='<tbody>';
							str+='<tr>';
								str+='<td >';
								str+='<p class="text-muted">'+result[i].name+'</p>';
								str+='<h4>'+result[i].efficencyPercentage+'%</h4>';
								str+='</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
		}
	$("#efficiencyId").html(str);
}


getCadreGreivienceIssueType();
function getCadreGreivienceIssueType(){
		var jobj = {                                      
			"searchType":"Mobile Number",
			"searchValue":"9063845874",
			"enrollmentyearId":0                   
		}
		$.ajax({
			type : "POST",
			url  : "getgrivanceDetailsForIssueTypeAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){
				buildPieChart(result);
			}
		});
}     
getgrivanceDetailsBySearch();
function getgrivanceDetailsBySearch(){
		var jobj = {                                      
			"searchType":"Complaint ID",
			"searchValue":"",
			"enrollmentyearId":0                   
		}
		$.ajax({
			type : "POST",
			url  : "getgrivanceDetailsBySearchAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){   
				// buildGrivanceEfficiency(result);
			}
		});
}

function buildPieChart(result){

//$("#totalCntId").html('0');
$('#donutchart').html('');
if(result == null || result.length == 0)
return;

	var dataArr = [];
	var dataArr1 = [];
	var colorsArr =new Array();
	for(var i in result)
	{
		dataArr = new Array();
		
		colorsArr.push("#306F8F","#FF9700","#C1392B","#E84B3C","#2CCA6F","#439C44");
		dataArr.push(result[i].issueType, result[i].grivanceCount);
		dataArr1.push(dataArr);
	}
	
	Highcharts.setOptions({
        colors: colorsArr
    });
	$('#donutchart').highcharts({
       chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 45
            }
        },	
		plotOptions: {	
			pie: {
                innerSize: 65,
                depth: 60,
				showInLegend: true,
				dataLabels: {
                    enabled: false,
				}
            }, 
		},
		legend: {
			itemStyle: {
               fontWeight: 'normal',
				'font-family':'roboto',
				'font-size':'13px'
            },
			enabled: true,
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'middle',
			useHTML: true,
			
			labelFormatter: function() {
				return this.name + ' ' + this.y + '';
			}
		},
		
		series:[{
            name : 'Count',
            data: dataArr1
        }]
	});
     
}
