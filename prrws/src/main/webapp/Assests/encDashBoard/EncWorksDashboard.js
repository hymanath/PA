var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['state','district','constituency','mandal']
var glStartDate = "01-04-"+moment().subtract(40,'years').format("YYYY");

var glEndDate = "01-04-"+moment().add(10, 'years').format("YYYY");

onloadCalls();
function onloadCalls(){
	levelWiseOverview();
}

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

function levelWiseOverview()
{
	var collapse = '';
	collapse+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in levelWiseOverviewArr)
		{
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseOverviewArr[i]+'">';
					if(i ==0)
					{
						collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}
					
						collapse+='<h4 class="panel-title text-capital">'+levelWiseOverviewArr[i]+' level overview</h4>';
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}
				
					collapse+='<div class="panel-body">';
						
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-12">';
								collapse+='<div id="'+levelWiseOverviewArr[i]+'levelBlockId"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	collapse+='</div>';
	$("#levelWiseOverviewId").html(collapse);
	$(".chosen-select").chosen({width :'100%'});
	for(var i in levelWiseOverviewArr)
	{
		if(levelWiseOverviewArr[i] == "state"){
			getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','s');
		}
		else if(levelWiseOverviewArr[i] == "district"){
			getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','d');
		}
		else if(levelWiseOverviewArr[i] == "constituency"){
			
			getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','a');
		}
		else if(levelWiseOverviewArr[i] == "mandal"){
			getLocationWiseWorksInformation(levelWiseOverviewArr[i]+'levelBlockId','m');
		}
		
	}
	
}


function getLocationWiseWorksInformation(blockId,locationType){
	$("#"+blockId).html(spinner);
	$("#enclocationWiseChart").html(spinner);
		var json = {
			locationType:locationType
		}
		$.ajax({                
			type:'POST',    
			url: 'getLocationWiseWorksInformation',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				buildLocationWiseWorksGraph(result)
				locationwiseTableBlocks(result,blockId,locationType);
			}else{
				
				$('#'+blockId).html("NO DATA AVAILABLE");
			}
		});
}
function locationwiseTableBlocks(result,blockId,locationType){
	var table='';
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId">';
				table+='<thead>';
					if(blockId == 'statelevelBlockId'){
						table+='<th>STATE</th>';
					}
					else if(blockId == 'districtlevelBlockId'){
						table+='<th>DISTRICTS</th>';
					}else if(blockId == 'constituencylevelBlockId'){
						table+='<th>CONSTITUENCY</th>';
					}else if(blockId == 'mandallevelBlockId'){
						table+='<th>MANDALS</th>';
					}
					table+='<th>UNDER PROCESS COUNT</th>';
					table+='<th>ADMIN SANCTION COUNT</th>';
					table+='<th>TECHNICALLY SANCTIONED COUNT</th>';
					table+='<th>GROUNDED COUNT</th>';
					table+='<th>NOT GROUNDED COUNT</th>';
					table+='<th>COMPLETED COUNT</th>';
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					table+='<tr>';
						table+='<td>'+result[i].locationName+'</td>';
						table+='<td>'+result[i].underProcessCount+'</td>';
						table+='<td>'+result[i].adminSanctionCount+'</td>';
						table+='<td>'+result[i].technicallySanctionedCount+'</td>';
						table+='<td>'+result[i].groundedCount+'</td>';
						table+='<td>'+result[i].notGrounded+'</td>';
						table+='<td>'+result[i].completedCount+'</td>';
					table+='</tr>';
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockId).html(table);
		if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "ENC WORKS DASHBOARD",
					filename	: blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
				}
			]
		});
	}
}

function buildLocationWiseWorksGraph(result){

	$("#enclocationWiseChart").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
	   
		xAxis: {
			type: 'category'
		},
		yAxis: {
			title: {
				text: 'Counts'
			}

		},
		legend: {
			enabled: false
		},
		plotOptions: {
			series: {
				borderWidth: 0,
				dataLabels: {
					enabled: true,
					format: '{point.y}'
				}
			}
		},

		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
		},

		series: [{
			name: 'Works',
			colorByPoint: true,
			data: [{
				name: 'Admin Sanctioned',
				y: result[0].adminSanctionCount,
				color:'#5fc24f'
				
			}, {
				name: 'Techincal Sancationed',
				y: result[0].technicallySanctionedCount
			},{
				name: 'Entrusted',
				y: result[0].totalWorksEntrusted,
				color:'red'
			},{
				name: 'Grounded',
				y: result[0].groundedCount
			},{
				name: 'UnderProcess',
				y: result[0].underProcessCount
			},{
				name:'Conmpleted',
				y:result[0].completedCount
			},{
				name: 'Not Grounded',
				y: result[0].notGrounded,
				color:'red'
			}]
		}]
	});
}