var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');
var blockNameArr=[{name:'District',id:'3'},{name:'Constituency',id:'4'},{name:'Mandal',id:'5'}]
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
buildLevelWiseDetailsBlock();
getBasicCountBlock();
var maxHeight = 0;
$(".blockHeights").each(function(){
   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
});
$(".chosen-select").chosen();
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
$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
$(".chosenSelect").chosen({width:'100%'});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
	
	globalFromDate = picker.startDate.format('DD-MM-YYYY')
	globalToDate = picker.endDate.format('DD-MM-YYYY')
	
	buildLevelWiseDetailsBlock();
	getBasicCountBlock();
});
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	
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
	if(date != "custom"){
		buildLevelWiseDetailsBlock();
		getBasicCountBlock();
	}
	
});

function getDistrictOverview(blockId,blockName){
	$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
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
			$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}

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
											collapse+='<div id="'+blockNameArr[i].name+'_'+blockNameArr[i].id+'"></div>';
										collapse+='</div>';
									collapse+='</div>';
								collapse+='</div>';
							collapse+='</div>';
						}
					collapse+='</div>';
				collapse+='</div>';
			$("#levelWiseBlockId").html(collapse);
			for(var i in blockNameArr){
				if(blockNameArr[i].id == 3){
					getDistrictOverview(blockNameArr[i].id,blockNameArr[i].name);
				}else if(blockNameArr[i].id == 4){
					getConstituencyOverview(blockNameArr[i].id,blockNameArr[i].name);
				}else if(blockNameArr[i].id == 5){
					getMandalOverview(blockNameArr[i].id,blockNameArr[i].name);
					
				}	
				
			}
	}
}
function buildTableData(result,blockId,blockName){
	
		var tableView='';
		tableView+='<div class="row">';
			tableView+='<div class="col-sm-12">';
					tableView+='<div class="row">';
						tableView+='<div class="col-sm-6">';
							tableView+='<div class="col-sm-6">';
								tableView+='<ul class="nav navbar-nav tableMenu">';
									tableView+='<li class="active" id="showDistrictData">Districts</li>';
									tableView+='<li id="showParliamentData">Parliament</li>';
								tableView+='</ul>';
							tableView+='</div>';
							if(blockId == '4' || blockId == '5'){
								tableView+='<div class="col-sm-3">';
									tableView+='<div id="chosen'+blockId+'">';
										
									tableView+='</div>';
								tableView+='</div>';
							}
							
						tableView+='</div>';
					tableView+='</div>';
					tableView+='<table class="table dataTable'+blockId+'">';
							tableView+='<thead>';
							if(blockId == 3){
								tableView+='<th>District</th>';
							}else if(blockId == 4){
								tableView+='<th>Constituency</th>';
							}else if(blockId == 5){
								tableView+='<th>Mandal</th>';
							}
								
								tableView+='<th><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="Target_icon"><br/>TARGET</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIVED</th>';
								tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_Perc_Icon.png" alt="Achived_%_Icon"><br/>ACHIVED %</th>';
							tableView+='</thead>';
							
							tableView+='<tbody>';
								for(var i in result.subList){
								tableView+='<tr>';
									tableView+='<td>'+result.subList[i].name+'</td>';
									tableView+='<td>'+result.subList[i].total+'</td>';
									tableView+='<td>'+result.subList[i].target+'</td>';
									tableView+='<td>'+result.subList[i].achieved+'</td>';
									tableView+='<td>'+result.subList[i].achievedPercentage+'</td>';
								tableView+='</tr>';
								}
							tableView+='</tbody>';
							tableView+='</table>';
					tableView+='</div>';
			tableView+='</div>';
		tableView+='</div>';
		$("#"+blockName+'_'+blockId).html(tableView);
		//$("#chosen"+blockName)
		if(blockId == '4' || blockId == '5'){
			$(".dataTable"+blockId).dataTable();
		}
		$(".tableMenu li").on('click',function(){
			$(this).closest("ul").find("li").removeClass("active");
			$(this).addClass("active");
		});
		if(blockId == '4'){
			selectBox("chosen"+blockId);
		}
		if(blockId == '5'){
			selectBox("chosen"+blockId);			
		}
}
function getConstituencyOverview(blockId,blockName){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"constituency",
			filterId:0,
			filterType:"district"
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
			$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
			
		});
	}

	function getMandalOverview(blockId,blockName){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
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
			$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
			
		});
	}
	function getBasicCountBlock(){
		var json = {
			fromDate:"",
			toDate:"",
			locationId:0,
			locationType:"district"
			}
		$.ajax({                
			type:'POST',    
			url: 'getPrisSurveyBasicData',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			buildBasicBlock(result);
		});
	}
	function buildBasicBlock(result){
		$("#totalHouseHolds").append(result.totalHouseHolds);
		$("#targetOverall").append(result.targetOverall);
		$("#targetOverallPercent").append(result.targetOverallPercent);
		$("#achievedOverall").append(result.achievedOverall);
		$("#achievedOverallpercent").append(result.achievedOverallpercent);
		$("#subTarget").append(result.subTarget);
		$("#subTargetPercentage").append(result.subTargetPercentage);
		$("#subAchieved").append(result.achievedOverall);
		$("#subAchievedPercentage").append(result.achievedOverallpercent);
}
function selectBox(id)
{
	
	var id = id;
	var selectBox='';
	selectBox+='<select class="chosen" id="chosen'+id+'">';
		selectBox+='<option></option>';
	selectBox+='</select>';
	$("#"+id).html(selectBox);
	$("#chosen"+id).chosen();
}
getConstDateForAssemblyInfo();
getMandalDateForAssemblyInfo();
function getConstDateForAssemblyInfo(){
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"assembly"
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
			buildBasicBlock(result);
		});
	}
	function getMandalDateForAssemblyInfo(){

		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
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
			buildBasicBlock(result);
		});
	}