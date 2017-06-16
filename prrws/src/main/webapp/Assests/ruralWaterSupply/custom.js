onLoadCalls();
function onLoadCalls()
{
	tabBlocks('stateBlockId','state');
	tabBlocks('districtBlockId','district');
	tabBlocks('consBlockId','constituency');
	tabBlocks('mandalBlockId','mandal');
	responsiveTabs();
	//totalValues('totalValues');
	totalValues('waterSources');
	totalValues('levelOfSupply');
	//totalValues('habitation');
	totalValues('habitationWorks');
	totalValues('schemes');
	
	//totalValues('keryPerformance');
	totalValues('keryPerformanceTarget');
	totalValues('alertStatus');
	totalValues('assets');
	//totalValues('overView');
	totalValues('planOfAction');
	totalValues('drinkingWater');
	
	$(".chosenSelect").chosen({width:'100%'});
	$(window,document).on('resize', function(){
		responsiveTabs();
	});
	$(document).on('click','[attr_click="questionMark"]', function(){
		$("#modalDivId").modal('show');
		$("#modalHeadingId").html($(this).attr("attr_title"));
		tableView('modalTable');
	});
	 
	$("#keryPerformance").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: 'Efficiency Optimization by Branch'
		},
		xAxis: {
			categories: [
				'Seattle HQ',
				'San Francisco'

			]
		},
		yAxis: [{
			min: 0,
			title: {
				text: 'Employees'
			}
		}, {
			title: {
				text: 'Profit (millions)'
			},
			opposite: true
		}],
		legend: {
			shadow: false
		},
		tooltip: {
			shared: true
		},
		plotOptions: {
			column: {
				grouping: false,
				shadow: false,
				borderWidth: 0
			}
		},
		series: [{
			name: 'Profit',
			color: 'rgba(248,161,63,1)',
			data: [183.6, 178.8],
			tooltip: {
				valuePrefix: '$',
				valueSuffix: ' M'
			},
			pointPadding: 0.3,
			pointPlacement: 0.2,
			yAxis: 1
		}, {
			name: 'Profit Optimized',
			color: 'rgba(186,60,61,.9)',
			data: [203.6, 198.8],
			tooltip: {
				valuePrefix: '$',
				valueSuffix: ' M'
			},
			pointPadding: 0.4,
			pointPlacement: 0.2,
			yAxis: 1
		}]
	});
}

function responsiveTabs()
{
	var $this = $(this);
	var $windowWidth = $(window).width();
	if($windowWidth < 768)
	{
		$('[role="tabListMobile"]').show();
		$('[role="tablist"]').hide();
	}else{
		$('[role="tabListMobile"]').hide();
		$('[role="tablist"]').show();
	}
	
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
	});
}
function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip)
{
	'use strict';
	$('#'+id).highcharts({
		 colors: [
                '#ff0000',
                '#00ff00',
                '#0000ff'
            ],
		chart: type,
		title: {
			text: null
		},
		subtitle: {
			text: null
		},
		xAxis: xAxis,
		yAxis: yAxis,
		tooltip: tooltip,
		plotOptions: plotOptions,
		legend: legend,
		series: data
	});
}
function totalValues(id)
{
	var id = id;
	var type = {
		type: 'column',
		backgroundColor:'transparent'
	};
	var legend = {
		enabled: false
	};
	var yAxis = {
		min: 0,
		gridLineWidth: 0,
		minorGridLineWidth: 0,
		title: {
			text: null
		},
	};
	var xAxis = {
		min: 0,
		gridLineWidth: 0,
		minorGridLineWidth: 0,
		categories: []
	};
	var plotOptions ={ column: {
			colorByPoint: true
		}};
	var tooltip = {
		pointFormat: '{point.y}'
	};

	var data = [{
		name: '',
		data: [
			['FC', 90],
			['PC', 62],
			['QA', 50],
		],

		dataLabels: {
			enabled: true,
			color: '#FFFFFF',
			align: 'right',
			format: '{point.y}',
		}
	}];
	highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
}
function tabBlocks(blockId,blockName)
{
	var tabBlock = '';
	var blocksArr = [{name:'Coverage Status Of<br/> Habitation',id:'habitation'},{name:'Key<br/> Performance',id:'performance'},{name:'Alert Status <br/>Jalavani',id:'jalavani'},{name:'Plan Of Action for Stressed Habitations <br/>Water Budget has to be prepared for all habitations',id:'planAction'}];
	var tableId = '';
	tabBlock+='<div class="panel panel-black panel-default">';
		tabBlock+='<div class="panel-heading">';
			tabBlock+='<h4 class="panel-title text-capital">'+blockName+' level overview</h4>';
		tabBlock+='</div>';
		tabBlock+='<div>';
			tabBlock+='<div class="row">';
				tabBlock+='<div class="col-sm-12">';
					tabBlock+='<div>';
						tabBlock+='<select class="form-control" role="tabListMobile">';
							for(var i in blocksArr)
							{
								tabBlock+='<option tab_id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].name+'</option>';
							}
					  	tabBlock+='</select>';
						tabBlock+='<ul class="nav nav-tabs nav-tabs-custom" role="tablist">';
							for(var i in blocksArr)
							{
								if(i == 0)
								{
									tabBlock+='<li class="active"><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab">'+blocksArr[i].name+'</a></li>';
								}else{
									tabBlock+='<li><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab">'+blocksArr[i].name+'</a></li>';
								}
								
							}
						tabBlock+='</ul>';
					tabBlock+='</div>';
				tabBlock+='</div>';
			tabBlock+='</div>';
		tabBlock+='</div>';
		tabBlock+='<div class="panel-body">';
			tabBlock+='<div class="tab-content">';
				
				if(blockId == 'consBlockId')
				{
					tabBlock+='<div class="row">';
						tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
						tabBlock+='<div class="col-sm-3"><div id="consSelect'+blockId+'"></div></div>';
					tabBlock+='</div>';
				}else if(blockId == 'mandalBlockId')
				{
					tabBlock+='<div class="row">';
						tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
						tabBlock+='<div class="col-sm-3"><div id="consSelect'+blockId+'"></div></div>';
						tabBlock+='<div class="col-sm-3"><div id="mandalSelect'+blockId+'"></div></div>';
					tabBlock+='</div>';
				}
				
				for(var i in blocksArr)
				{
					if(i == 0)
					{
						tabBlock+='<div role="tabpanel" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';	
					}else{
						tabBlock+='<div role="tabpanel" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
					}
				}
			tabBlock+='</div>';
		tabBlock+='</div>';
	tabBlock+='</div>';
	$("#"+blockId).html(tabBlock);
	for(var i in blocksArr)
	{
		tableId = blockId+''+blocksArr[i].id;
		tableView(tableId);
	}
	if(blockId == 'consBlockId')
	{
		selectBox('districtSelect'+blockId+'')
		selectBox('consSelect'+blockId+'')
	}else if(blockId == 'mandalBlockId')
	{
		selectBox('districtSelect'+blockId+'')
		selectBox('consSelect'+blockId+'')
		selectBox('mandalSelect'+blockId+'')
	}
	
}

function tableView(blockId)
{
	var tableView='';
	var theadArr = ['','QA','PC1','PC2','PC3','PC4','FC','TOTAL'];
	var tbodyArr = [];
	var $windowWidth = $(window).width();
	
	tableView+='<table class="table table-bordered dataTable'+blockId+'">';
		tableView+='<thead class="text-capital">';
			for(var i in theadArr)
			{
				tableView+='<th>'+theadArr[i]+'</th>';
			}
		tableView+='</thead>';
		tableView+='<tbody>';
			tableView+='<tr>';
				tableView+='<td class="text-capital">total <i class="fa fa-question-circle" attr_click="questionMark" attr_title="Modal TItle"></i></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
				tableView+='<td>574 <small class="text-success">57%</small></td>';
			tableView+='</tr>';
		tableView+='</tbody>';
	tableView+='</table>';
	$("#"+blockId).html(tableView);	
	$(".dataTable"+blockId).dataTable();
	if($windowWidth < 768)
	{
		$(".dataTable"+blockId).wrap("<div class='table-responsive'></div>");
	}
}
function selectBox(id)
{
	var id = id;
	var selectBox='';
	selectBox+='<select class="chosen" id="chosen'+id+'"><option>ss</option></select>';
	$("#"+id).html(selectBox);
	$("#chosen"+id).chosen();
}