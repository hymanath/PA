onLoadCalls();
function onLoadCalls()
{
	getNREGSProjectsOverview()
	$(document).on('click','[overview-block]', function(){
		$("[overview-block]").removeClass("active");
		$(this).addClass("active");
		var projectDivId = $(this).attr("overview-block");
		projectData(projectDivId)
		if(projectDivId == 'Labout Budget')
		{
			
		}
	});
	getNregsVermiOverview()
	getNregsVermiData();
}

function projectData(divId)
{
	var collapse='';
	var dataArr = ['state','district','constituency','mandal']
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+dataArr[i]+'">';
								collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview - '+divId+'</h4>';
								collapse+='</a>';
							collapse+='</div>';
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								collapse+='<div class="panel-body">';
									collapse+='<div id="table'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#projectData").html(collapse);
	for(var i in dataArr)
	{ 
		var tableId = divId.replace(/\s+/g, '')+''+dataArr[i]
		functionName(tableId,dataArr[i])
	}
}
function tableView(blockId,theadArr,result)
{
	var tableView='';
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
function getNREGSProjectsOverview()
{
	
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30"
		}
	$.ajax({
		url: 'getNREGSProjectsOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSProjectsOverview(ajaxresp);
		}
	});
}
function buildNREGSProjectsOverview(result)
{
	var str='';
	str+='<div class="row">';
		for(var i in result)
		{
			str+='<div class="col-sm-2">';
				if(result[i].percentage < 50)
				{
					str+='<div class="panel-block-white panel-block-white-low text-center" overview-block="'+result[i].parameter+'">';
				}else if(result[i].percentage > 50 && result[i].percentage < 80)
				{
					str+='<div class="panel-block-white panel-block-white-medium text-center" overview-block="'+result[i].parameter+'">';
				}else if(result[i].percentage > 80)
				{
					str+='<div class="panel-block-white panel-block-white-high text-center" overview-block="'+result[i].parameter+'">';	
				}
				
					str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i].parameter+'</h4>';
					str+='<small class="text-center">Achieved</small>';
					str+='<h1 class="text-center">'+result[i].percentage+'<small>%</small>';
				if(result[i].percentage < 50)
				{
					str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
				}else if(result[i].percentage > 50 && result[i].percentage < 80)
				{
					str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
					
				}else if(result[i].percentage > 80)
				{
					str+='<small><i class="fa fa-long-arrow-top"></i></small></h1>';
				}
					str+='<div class="row">';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Target</label>';
							str+='<h4>'+result[i].target+'</h4>';
						str+='</div>';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Generated</label>';
							str+='<h4>'+result[i].completed+'</h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	str+='</div>';
	$("#projectsOverview").html(str);
}

function functionName(tableId,dataArr)
{
	var theadArr = ['','QA','PC1','PC2','PC3','PC4','FC','TOTAL'];
	tableView(tableId,theadArr,result)
}
function getNregsVermiOverview()
{
	
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30"
		}
	$.ajax({
		url: 'getNregsVermiOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			
		}
	});
}
function getNregsVermiData()
{
	
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30",
		    locationType: "state" 
		}
	$.ajax({
		url: 'getNregsVermiData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			
		}
	});
}