var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');
var blockNameArr=[{name:'District',id:'3'},{name:'Constituency',id:'4'},{name:'Mandal',id:'5'}]
var maxHeight = 0;
$(".blockHeights").each(function(){
   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
});

var width = $(window).width()
	if(width > 767){
		$(".border_adjust_align").removeClass("border_top")
		$(".border_adjust_align").addClass("border_right")
		$(".blockHeights").height(maxHeight);
	}else{
		$(".border_adjust_align").removeClass("border_right")
		$(".border_adjust_align").addClass("border_top");
		
		
	}
$("#singleDateRangePicker").daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		}
});
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(".calendar_active_cls li.active").html();
	if(date == 'Today')
	{
		globalFromDate = moment().format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}else if(date == 'Week'){
		globalFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}else if(date == 'Month'){
		globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}else if(date == '3Months'){
		globalFromDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}else if(date == '6Months'){
		globalFromDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}else if(date == 'Overall'){
		globalFromDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
});
buildLevelWiseDetailsBlock();
function buildLevelWiseDetailsBlock(){
	var collapse='';
	if(blockNameArr !=null){
					collapse+='<div class="">';
						collapse+='<div class="col-sm-12">';
							for(var i in blockNameArr)
							{
							collapse+='<div class="panel-group" id="accordion'+blockNameArr[i].id+'" role="tablist" aria-multiselectable="true">';
								collapse+='<div class="panel panel-default panel-black">';
									collapse+='<div class="panel-heading" role="tab" id="heading'+blockNameArr[i].id+'">';
										if(i == 0)
										{
											collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+blockNameArr[i].id+'" href="#collapse'+blockNameArr[i].id+'" aria-expanded="true" aria-controls="collapse'+blockNameArr[i].id+'">';
										}else{
											collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+blockNameArr[i].id+'" href="#collapse'+blockNameArr[i].id+'" aria-expanded="true" aria-controls="collapse'+blockNameArr[i].id+'">';
										}
											collapse+='<h4 class="panel-title text-capital">'+blockNameArr[i].name+'  level  overview</h4>';
										collapse+='</a>';
									collapse+='</div>';
									if(i == 0)
									{
										collapse+='<div id="collapse'+blockNameArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+blockNameArr[i].id+'">';
									}else{
										collapse+='<div id="collapse'+blockNameArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+blockNameArr[i].id+'">';
									}
									
										collapse+='<div class="panel-body">';
											collapse+='<div id="'+blockNameArr[i].name+'_'+blockNameArr[i].id+'">ddd</div>';
										collapse+='</div>';
									collapse+='</div>';
								collapse+='</div>';
							collapse+='</div>';
						}
					collapse+='</div>';
				collapse+='</div>';
			$("#levelWiseBlockId").html(collapse);
			for(var i in blockNameArr){
				buildTableData();
			}
	}
}

function buildTableData(){
	for(var i in blockNameArr){
	var tableView='';
		tableView+='<div class="row">';
			tableView+='<div class="col-sm-12">';
					tableView+='<table class="table ">';
							tableView+='<thead>';
								tableView+='<th>District</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="Target_icon"><br/>TARGET</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIVED</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_%_Icon.png" alt="Achived_icon"><br/>ACHIVED %</th>';
							tableView+='</thead>';
						tableView+='</table>';
					tableView+='</div>';
			tableView+='</div>';
		tableView+='</div>';
		
		$("#"+blockNameArr[i].name+'_'+blockNameArr[i].id).html(tableView)
	}	
}


getDistrictOverview();
function getDistrictOverview(){
		var json = {
			fromDate:"07-05-2016",
			toDate:"07-05-2017",
			locationId:0,
			locationType:"district"
			}
		$.ajax({                
			type:'POST',    
			url: 'getPIRSSurveyInfo',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
		});
	}
	getConstituencyOverview();
	function getConstituencyOverview(){
		var json = {
			fromDate:"07-05-2016",
			toDate:"07-05-2017",
			locationId:0,
			locationType:"constituency"
			}
		$.ajax({                
			type:'POST',    
			url: 'getPIRSSurveyInfo',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
		});
	}
	getMandalOverview();
	function getMandalOverview(){
		var json = {
			fromDate:"07-05-2016",
			toDate:"07-05-2017",
			locationId:447,
			locationType:"mandal"
			}
		$.ajax({                
			type:'POST',    
			url: 'getPIRSSurveyInfo',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
		});
	}