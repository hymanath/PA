//var globalFromDate = moment().subtract(0,'month').startOf("month").format('DD-MM-YYYY');
var globalFromDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');    
var globalToDate = moment().format('DD-MM-YYYY');
var blockNameArr=[{name:'District',id:'3'},{name:'Constituency',id:'4'},{name:'Mandal',id:'5'}]
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globallocId = 0;
var globallevelId = 0;
var globalLocationName='';
$("#selectedName").attr("attr_id","0");
$("#selectedName").attr("attr_levelidvalue","2");

	onLoadCalls()
	$(".thisMonthOverview").html("OVERALL");
	
	$(".chosen-select").chosen();
	$(document).on('cut copy paste', function (e) {
		e.preventDefault();
	});
	function onLoadCalls()
	{
		buildLevelWiseDetailsBlock();
		POSTBasicCountBlock();
		getPIRSSurveyInfoStateLevel();
		$("header").on("click",".menu-cls",function(e){
			e.stopPropagation();
			$(".menu-data-cls").toggle();
		});
		$(document).on("click",function(){
			$(".menu-data-cls").hide();
		});
		
	}
	$(document).on("click",".menuDataCollapse",function(){
		globallocId = $(this).attr("attr_id");
		globallevelId = $(this).attr("attr_levelidvalue");
		globalLocationName=$(this).attr("attr_name");
		$("#selectedName").text($(this).html());
		$("#selectedName").attr("attr_id",globallocId);
		$("#selectedName").attr("attr_levelidvalue",globallevelId);
		$("#selectedName").attr("attr_name",globalLocationName);
	
		if(globallevelId == 2 || globallevelId == 0){
			$(".tableMenu li").removeClass("active");
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").show();
			$("[overview-level]").show();
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
		}else if(globallevelId == 3){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level]").show();
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
			$("#districtSelectBoxchosen").html('');
			$("#districtSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#districtSelectBoxchosen").trigger("chosen:updated");
			
			$("#mandalsDistrictSelectBoxchosen").html('');
			$("#mandalsDistrictSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#mandalsDistrictSelectBoxchosen").trigger("chosen:updated");
			getAllConstituenciesForDistrict(5,"Mandal","Districts",globallocId);
			
		}else if(globallevelId == 4){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level='District']").hide();
			$("[overview-level='constituency']").show();
			
			$("#mandalsDistrictSelectBox").hide();
			$("#districtSelectBox").hide();
			$("#mandalsSelectBox").hide();
		}else if(globallevelId == 5){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level='District']").hide();
			$("[overview-level='Constituency']").hide();
			$("#mandalsDistrictSelectBox").hide();
			$("#districtSelectBox").hide();
			$("#mandalsSelectBox").hide();
		}
		POSTBasicCountBlock();
		getPIRSSurveyInfoStateLevel();
		for(var i in blockNameArr){
			if(blockNameArr[i].id == 3){
				POSTDistrictOverview(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView');
			}else if(blockNameArr[i].id == 4){
				POSTConstDateForAssemblyInfo(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView',0);
				if(globallevelId == 2 || globallevelId == 0){
					getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Districts');
				}
				
			}else if(blockNameArr[i].id == 5){
				POSTMandalDateForAssemblyInfo(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView',0,0,"assembly");
				if(globallevelId == 2 || globallevelId == 0){
					getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Parliament');
				}
				
			}
		}
});
	var width = $(window).width();
	
	if(width > 767){
		$(".border_adjust_align").removeClass("border_top")
		$(".border_adjust_align").addClass("border_right")
		
	}else{
		$(".border_adjust_align").removeClass("border_right")
		$(".border_adjust_align").addClass("border_top");
	}
	$(document).on('click','[table-menu] li',function(){
		$('#districtSelectBoxchosen').val(0);
		$('#mandalsDistrictSelectBoxchosen').val(0);
		$('#mandalsSelectBoxchosen').val(0);
		$('#districtSelectBoxchosen').trigger('chosen:updated');
		$('#mandalsDistrictSelectBoxchosen').trigger('chosen:updated');
		$('#mandalsSelectBoxchosen').trigger('chosen:updated');
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("table-menu");
		var subBlockName = $(this).html();
		
		if(blockName == 'District'){
			if(subBlockName == 'Districts'){
				getAllDistricts(3,blockName,subBlockName);
				POSTDistrictOverview(3,blockName,subBlockName,'tableView');
			}else if(subBlockName == 'Parliament'){
				POSTDistrictOverview(3,blockName,subBlockName,'tableView');
				getAllParliaments(3,blockName,subBlockName);
			}
		}else if(blockName == 'Constituency'){
			if(subBlockName == 'Districts'){
				getAllDistricts(4,blockName,subBlockName);
				POSTConstDateForAssemblyInfo(4,blockName,subBlockName,'tableView',0);
			}else if(subBlockName == 'Parliament'){
				 POSTConstituencyOverview(4,blockName,subBlockName,'tableView',0);
				getAllParliaments(4,blockName,subBlockName);
			}
		}else if(blockName == 'Mandal'){
			$("#mandalsSelectBox").hide();
			if(subBlockName == 'Districts'){
				getAllDistricts(5,blockName,subBlockName);
				POSTMandalDateForAssemblyInfo(5,blockName,subBlockName,'tableView',0,0,"assembly");
			}else if(subBlockName == 'Parliament'){
				getAllParliaments(5,blockName,subBlockName);
				POSTMandalOverview(5,blockName,subBlockName,'tableView',0);
			}
		}
	});
	$(document).on("change","#mandalsDistrictSelectBoxchosen",function(){
		var value = $(this).val();
		$("#mandalsSelectBox").show();
		var blockName = $(this).attr("attr_blockName");
		var subBlockName = $("[table-menu='"+blockName+"'] li.active").html();
		
		$("#table"+blockName).DataTable().destroy();
		$("#table"+blockName).html(spinner);
		if(subBlockName == 'Districts')
		{
			getAllConstituenciesForDistrict(5,blockName,subBlockName,value);
			mandalDataForDistChangeForAssembly(5,blockName,subBlockName,'tableView',value);//Teja
			
		}else if(subBlockName == 'Parliament')
		{
			getAllConstituenciesForParliament(5,blockName,subBlockName,value);
			distChangeMandalOverview(5,blockName,subBlockName,'tableView',value);
			//constMandalView(5,blockName,subBlockName,'tableView',value);
		}
	});
	$(document).on("change","#mandalsSelectBoxchosen",function(){
		var value = $(this).val();
		var blockName = $(this).attr("attr_blockName");
		//var subBlockName = $("[table-menu='"+blockName+"'] li.active").html();
		var subBlockName = '';
		 $(".tableMenu li").each(function(){
			if($(this).hasClass("active")){
				subBlockName = $(this).attr("attr_type")
			}
		}); 
		//$("#table"+blockName).DataTable().destroy();
		$("#table"+blockName).html(spinner);
		
		if(subBlockName == 'districts')
		{
			POSTMandalDateForAssemblyInfo(5,blockName,subBlockName,'tableView',value,$("#mandalsDistrictSelectBoxchosen").val(),"assembly");
		}else if(subBlockName == 'parliament')
		{
			parliamentAssemblyChangeMandalOverview(5,blockName,subBlockName,'tableView',value);
		}
	});
	$(document).on("change","#districtSelectBoxchosen",function(){
		var value = $(this).val();
		var blockName = $(this).attr("attr_blockName");
		$("#table"+blockName).DataTable().destroy();
		
		$("#table"+blockName).html(spinner);
		var subBlockName = $("[table-menu='"+blockName+"'] li.active").html();
		if(subBlockName == 'Districts')
		{
			POSTConstDateForAssemblyInfo(4,blockName,subBlockName,'tableView',value);
		}else if(subBlockName == 'Parliament')
		{
			parliamentChnageConstituencyOverview(4,blockName,subBlockName,'tableView',value);
		}
	});
	$("#singleDateRangePicker").daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		}
	});
	
$(".chosenSelect").chosen({width:'100%'});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
	
	globalFromDate = picker.startDate.format('DD-MM-YYYY')
	globalToDate = picker.endDate.format('DD-MM-YYYY')
	$(".thisMonthOverview").html(globalFromDate+" to "+globalToDate);
	buildLevelWiseDetailsBlock();
	POSTBasicCountBlock();
	getPIRSSurveyInfoStateLevel();
	
});
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	
	if(date == 'Today')
	{
		globalFromDate = moment().format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("TODAY")
	}else if(date == 'Week'){
		globalFromDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("This week")
	}else if(date == 'Month'){
		globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("This Month - "+moment().format("MMMM"));
	}else if(date == '3Months'){
		globalFromDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(moment().subtract(3,'month').format("MMMM")+" - "+moment().format("MMMM"));
	}else if(date == '6Months'){
		globalFromDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(moment().subtract(6,'month').format("MMMM")+" - "+moment().format("MMMM"));
	}else if(date == 'Overall'){
		globalFromDate = moment().subtract(20,'years').startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html("Overall");
	}else if(date == 'Year'){
		globalFromDate = moment().startOf("year").format('DD-MM-YYYY');
		globalToDate = moment().format('DD-MM-YYYY');
		$(".thisMonthOverview").html(globalFromDate+" to "+globalToDate);
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if(date != "custom"){
		buildLevelWiseDetailsBlock();
		POSTBasicCountBlock();
		getPIRSSurveyInfoStateLevel();
	}
	
});

function POSTDistrictOverview(blockId,blockName,subBlockName,viewType){
	$("#"+blockName+'_'+blockId).html(spinner);
	var locationId =0;
	if(globallevelId == 2 || globallevelId == 0){
		locationId =0;
	}else{
		locationId = globallocId
	}
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:locationId,
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
				buildTableData(result,blockId,blockName,subBlockName,'tableView');
				//buildSelectBox('districtSelectBox',result.subList,'Constituency');
				//buildSelectBox('mandalsDistrictSelectBox',result.subList,'Mandal');
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
			
			
		});
	}			
function buildLevelWiseDetailsBlock(){
	var collapse='';
	if(blockNameArr !=null){
	collapse+='<div class="row">';
		collapse+='<div class="col-sm-12">';
			for(var i in blockNameArr)
			{
				collapse+='<div class="panel-group" id="accordion'+blockNameArr[i].id+'" role="tablist" aria-multiselectable="true" overview-level="'+blockNameArr[i].name+'">';
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
								collapse+='<div class="col-sm-3">';
								if(blockNameArr[i].id == 3){
									collapse+='<ul class="nav navbar-nav tableMenu list-inline" table-menu="'+blockNameArr[i].name+'" style="border:none;">';
								}else{
									collapse+='<ul class="nav navbar-nav tableMenu list-inline" table-menu="'+blockNameArr[i].name+'">';
								}
								if(blockNameArr[i].id == 4){
									collapse+='<li class="active" id="showDistrictData" attr_type="districts">Districts</li>';
									collapse+='<li id="showParliamentData" attr_type="parliament">Parliament</li>';
								}else if(blockNameArr[i].id == 5){
									collapse+='<li class="active" id="showDistrictData" attr_type="districts">Districts</li>';
									collapse+='<li id="showParliamentData" attr_type="parliament">Parliament</li>';
								}
									collapse+='</ul>';
								collapse+='</div>';
								if(blockNameArr[i].id == 4){
									collapse+='<div class="col-sm-3">';
										collapse+='<div id="districtSelectBox"></div>';
									collapse+='</div>';
								}
								if(blockNameArr[i].id == 5){
									collapse+='<div class="col-sm-3">';
										collapse+='<div id="mandalsDistrictSelectBox"></div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-3">';
										collapse+='<div id="mandalsSelectBox"></div>';
									collapse+='</div>';
								}
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
				POSTDistrictOverview(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView');
			}else if(blockNameArr[i].id == 4){
				POSTConstDateForAssemblyInfo(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView',0);
				if(globallevelId == 2 || globallevelId == 0){
					getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Districts');
				}
				
			}else if(blockNameArr[i].id == 5){
				POSTMandalDateForAssemblyInfo(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView',0,0,"assembly");
				if(globallevelId == 2 || globallevelId == 0){
					getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Parliament');
				}
				
			}
		}
	}
	
}
function buildTableData(result,blockId,blockName,subBlockName,viewType){//Teja
		if(viewType == 'tableView')
		{
			var tableView='';
			tableView+='<div class="row">';
				tableView+='<div class="col-sm-12">';
						tableView+='<div class="row">';
							tableView+='<div class="col-sm-6">';
								/*tableView+='<div class="col-sm-6">';
									tableView+='<ul class="nav navbar-nav tableMenu" attr="table-menu">';
										tableView+='<li  id="showDistrictData" attr_type="districts">Districts</li>';
										tableView+='<li class="active" id="showParliamentData" attr_type="parliament">Parliament</li>';
									tableView+='</ul>';
								tableView+='</div>';*/
								
							tableView+='</div>';
						tableView+='</div>';
						tableView+='<div class="table-responsive">';
						tableView+='<table class="table dataTable'+blockId+'" id="table'+blockName+'" style="width:100%">';
								tableView+='<thead>';
								
								if(blockName == 'District'){
									tableView+='<tr>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center">District</th>';
									tableView+='<th style="font-size:10px;" colspan="1" class="text-center"><img class="img_widthTable" src="Assests/icons/District_Survy_icon.png" alt="District_Survy_icon"><br/>DISTRICT</th>';
									
									tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Constituency_Survy_icon.png" alt="Constituency_Survy_icon"><br/>CONSTITUENCY</th>';
									
									tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Mandal_Survy_icon.png" alt="Mandal_Survy_icon"><br/>MANDAL</th>';
									
									tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Gram_Panchayat_Survy_icon.png" alt="Gram_Panchayat_Survy_icon"><br/>PANCHAYAT</th>';
									
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="target_icon"><br/>TARGET</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIEVED</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_Perc_Icon.png" alt="Achived_%_Icon"><br/>ACHIEVED %</th>';
									tableView+='</tr>';
									
									tableView+='<tr>';
										tableView+='<th style="font-size:10px;" class="cyan_color"></th>';
										tableView+='<th style="font-size:10px;" class="light_pink_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_pink_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_pink_color">COMPLETED</th>';
										tableView+='<th style="font-size:10px;" class="light_yellow_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_yellow_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_yellow_color">COMPLETED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">COMPLETED</th>';
									tableView+='</tr>';
								}
								
								else if(blockName == 'Constituency' ){
									
									tableView+='<tr>';
										if(subBlockName == "Districts" || subBlockName == "districts"){
											tableView+='<th colspan="1">District</th>';
											tableView+='<th colspan="1">Constituency</th>';
										}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
											tableView+='<th colspan="1">Parliament</th>';
											tableView+='<th colspan="1">Constituency</th>';
										}
										
										tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Mandal_Survy_icon.png" alt="Mandal_Survy_icon"><br/>MANDAL</th>';
										tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Gram_Panchayat_Survy_icon.png" alt="Gram_Panchayat_Survy_icon"><br/>PANCHAYAT</th>';
										tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
										tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="target_icon"><br/>TARGET</th>';
										tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIEVED</th>';
										tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_Perc_Icon.png" alt="Achived_%_Icon"><br/>ACHIEVED %</th>';
									tableView+='</tr>';
									tableView+='<tr>';
										if(subBlockName == "Districts"  || subBlockName == "districts"){
											tableView+='<th ></th>';
											tableView+='<th ></th>';
										}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
											tableView+='<th ></th>';
											tableView+='<th ></th>';
										}
										
										tableView+='<th style="font-size:10px;" class="light_yellow_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_yellow_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_yellow_color">COMPLETED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">COMPLETED</th>';
									tableView+='</tr>';
									
									
								}else if(blockName == 'Mandal'){
									tableView+='<tr>';
									if(subBlockName == "Districts" || subBlockName == "districts"){
										tableView+='<th colspan="1">District</th>';
										tableView+='<th colspan="1">Constituency</th>';
										tableView+='<th colspan="1">Mandal</th>';
									}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
										tableView+='<th colspan="1">Parliament</th>';
										tableView+='<th colspan="1">Constituency</th>';
										tableView+='<th colspan="1">Mandal</th>';
									
									}
									
									
									tableView+='<th style="font-size:10px;" colspan="3" class="text-center"><img class="img_widthTable" src="Assests/icons/Gram_Panchayat_Survy_icon.png" alt="Gram_Panchayat_Survy_icon"><br/>PANCHAYAT</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="target_icon"><br/>TARGET</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIEVED</th>';
									tableView+='<th style="font-size:10px;" rowspan="2" class="text-center"><img class="img_widthTable" src="Assests/icons/Achived_Perc_Icon.png" alt="Achived_%_Icon"><br/>ACHIEVED %</th>';
									tableView+='</tr>';
									
									tableView+='<tr>';
									if(subBlockName == "Districts" || subBlockName == "districts"){
										tableView+='<th ></th>';
										tableView+='<th ></th>';
										tableView+='<th ></th>';
									}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
										tableView+='<th ></th>';
										tableView+='<th ></th>';
										tableView+='<th ></th>';
										
									}
										
										tableView+='<th style="font-size:10px;" class="light_rose_color">STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">NOT-STARTED</th>';
										tableView+='<th style="font-size:10px;" class="light_rose_color">COMPLETED</th>';
									tableView+='</tr>';
								}
									
								tableView+='</thead>';
								
								tableView+='<tbody>';
									for(var i in result.subList){
									tableView+='<tr>';
										if(blockName == 'District'){
											tableView+='<td  style="font-size:12px;"><img src="Assests/icons/'+result.subList[i].name+'.png" style="height: 30px;margin-right: 7px;width:30px;"/></br>'+result.subList[i].name+'</td>';
											tableView+='<td style="font-size:12px;" class="cyan_color">'+result.subList[i].districtStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_pink_color">'+result.subList[i].consStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_pink_color">'+result.subList[i].consNotStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_pink_color">'+result.subList[i].consCompleted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalNotStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalCompleted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatNotStarted+'</td>';
											tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatCompleted+'</td>';
										}
										
										else if(blockName == 'Constituency')
										{
												if(subBlockName == "Districts" || subBlockName == "districts"){
													tableView+='<td>'+result.subList[i].districtName+'</td>';
													tableView+='<td>'+result.subList[i].name+'</td>';
												}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
													tableView+='<td>'+result.subList[i].parliament+'</td>';
													tableView+='<td>'+result.subList[i].name+'</td>';
													
												}
												
												tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalNotStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_yellow_color">'+result.subList[i].mandalCompleted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatNotStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatCompleted+'</td>';
												
										}else if(blockName == 'Mandal')
										{
											
											if(subBlockName == "Districts" || subBlockName == "districts"){
												tableView+='<td>'+result.subList[i].districtName+'</td>';
												tableView+='<td>'+result.subList[i].assemblyName+'</td>';
												tableView+='<td>'+result.subList[i].name+'</td>';
											}else if(subBlockName == "Parliament" || subBlockName == "parliament"){
												tableView+='<td>'+result.subList[i].constituencyName+'</td>';
												tableView+='<td>'+result.subList[i].assemblyName+'</td>';
												tableView+='<td>'+result.subList[i].name+'</td>';
												
											}
												
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatNotStarted+'</td>';
												tableView+='<td style="font-size:12px;" class="light_rose_color">'+result.subList[i].panchayatCompleted+'</td>';
										}
										
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
			tableView+='</div>';
			$("#"+blockName+'_'+blockId).html(tableView);
			//$("#chosen"+blockName)
			if(blockId == '4' || blockId == '5'){
				$(".dataTable"+blockId).dataTable({
					"iDisplayLength": 10,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
					"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
						"<'row'<'col-sm-12'tr>>" +
						"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					buttons: [
						{
							extend:    'csvHtml5',
							text:      '<i class="fa fa-file-text-o"></i>',
							titleAttr: 'CSV',
							title:	   blockName+'_'+blockId,
							filename:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
						},
						{
							extend:    'pdfHtml5',
							text:      '<i class="fa fa-file-pdf-o"></i>',
							titleAttr: 'PDF',
							title:	   blockName,
							filename:  blockName+'_'+blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							orientation: "landscape",
							pageSize:'A3',
							customize: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
							}
						}
					]
				});
			}
			if(blockId == '3'){
				$(".dataTable"+blockId).dataTable({
					 "paging":   false,
					 "info":     false,
					 "searching": false,
					 "autoWidth": true,
					 "order": [ 0, 'asc' ],
					 "aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
					 "dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
							"<'row'<'col-sm-12'tr>>" +
							"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					 buttons: [
							{
								extend:    'csvHtml5',
								text:      '<i class="fa fa-file-text-o"></i>',
								titleAttr: 'CSV',
								title:	   blockId,
								filename:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							},
							{
								extend:    'pdfHtml5',
								text:      '<i class="fa fa-file-pdf-o"></i>',
								titleAttr: 'PDF',
								title:	   blockId,
								filename:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
								orientation: "landscape",
								pageSize:'A3',
								customize: function (doc) {
									doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
								}
							}
						]
				});
			}
		}
	}
	
	function POSTConstituencyOverview(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:locId,
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function buildSelectBox(id,result,blockName,type)
	{
		
		var selectBox = '';
		
		if(type == "districts"){
			selectBox+='<label>District</label>';
		}else if(type == "constituency"){
			selectBox+='<label>Constituency</label>';
		}else if(type == "parliaments"){
			selectBox+='<label>Parliament</label>';
		}
		selectBox+='<select id="'+id+'chosen" attr_blockName="'+blockName+'">';
		if(type == "districts"){
			selectBox+='<option value="0">All Districts</option>';
		}else if(type == "constituency"){
			selectBox+='<option value="0">All Constituency</option>';
		}else if(type == "parliaments"){
			selectBox+='<option value="0">All Parliaments</option>';
		}
			
			for(var i in result)
			{
				selectBox+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		selectBox+='</select>';
		$("#"+id).html(selectBox);
		$("#"+id+"chosen").chosen();
		$("#"+id).trigger("chosen:updated");
	}
	function constMandalView(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var filterId = $("#mandalsSelectBoxChosen").val();
		if(filterId == null)
		{
			filterId = 0;
		}
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId: locId,
			filterType:'district'
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
				//buildSelectBox('mandalsSelectBoxchosen',result.subList,'Mandal');
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function POSTMandalOverview(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var filterId = $("#mandalsDistrictSelectBoxchosen").val();
		if(filterId == null)
		{
			filterId = 0;
		} 
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId: 0,
			filterType:'assembly',
			subFilterId: filterId ,
			subFilterType: 'assembly'//assembly
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function distChangeMandalOverview(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var filterId = $("#mandalsDistrictSelectBoxchosen").val();
		if(filterId == null)
		{
			filterId = 0;
		}
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId:0,
			filterType:'constituency',
			subFilterId:filterId ,
			subFilterType: 'constituency'//assembly
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	
	function POSTBasicCountBlock(){
		$("#totalHouseHolds").html(spinner);
		$("#targetOverall").html(spinner);
		$("#targetOverallPercent").html(spinner);
		$("#achievedOverall").html(spinner);
		$("#achievedOverallpercent").html(spinner);
		$("#subTarget").html(spinner);
		$("#subTargetPercentage").html(spinner);
		$("#subAchieved").html(spinner);
		$("#subAchievedPercentage").html(spinner);
		
		var dateType = 'OVERALL';
		 $(".calendar_active_cls li").each(function(){
			if($(this).hasClass("active")){
				dateType = $(this).attr("attr_val")
			}
		}); 
		
		var locationType='';
		var locationId=0;
		
		if(globallevelId == 2 || globallevelId == 0){
			locationType = "district";
			locationId = 0;
		}else if(globallevelId == 3){
			locationType = "district";
			locationId = globallocId;
		}else if(globallevelId == 4){
			locationType = "assembly";
			locationId = globallocId;
			
		}else if(globallevelId == 5){
			locationType = "mandal";
			locationId = globallocId;
		}
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:locationId,
			locationType:locationType,
			type:dateType
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
			if( result != null && result != ""){
				buildBasicBlock(result);
			}else{
				$("#totalHouseHolds").html("");
				$("#targetOverall").html("");
				$("#targetOverallPercent").html("");
				$("#achievedOverall").html("");
				$("#achievedOverallpercent").html("");
				$("#subTarget").html("");
				$("#subTargetPercentage").html("");
				$("#subAchieved").html("");
				$("#subAchievedPercentage").html("");
			}
			
		});
	}
	function buildBasicBlock(result){
		if(result.totalHouseHolds !=null && result.totalHouseHolds >0){
			$("#totalHouseHolds").html(result.totalHouseHolds);
		}else{
			$("#totalHouseHolds").html(0);
		}
		if(result.targetOverall != null && result.targetOverall > 0){
			$("#targetOverall").html(result.targetOverall);
		}else{
			$("#targetOverall").html(0);
		}
		if(result.targetOverallPercent != null && result.targetOverallPercent > 0){
			$("#targetOverallPercent").html(result.targetOverallPercent);
		}else{
			$("#targetOverallPercent").html(0);
		}
		if(result.achievedOverall != null && result.achievedOverall > 0){
			$("#achievedOverall").html(result.achievedOverall);
		}else{
			$("#achievedOverall").html(0);
		}
		
		$("#achievedOverallpercent").html(result.achievedOverallpercent != null && result.achievedOverallpercent >0?result.achievedOverallpercent:0);
		$("#subTarget").html(result.subTarget != null && result.subTarget > 0 ? result.subTarget:0);
		$("#subTargetPercentage").html(result.subTargetPercentage != null && result.subTargetPercentage > 0 ? result.subTargetPercentage:0);
		$("#subAchieved").html(result.subAchieved != null && result.subAchieved > 0?result.subAchieved:0);
		$("#subAchievedPercentage").html(result.subAchievedPercentage != null && result.subAchievedPercentage > 0?result.subAchievedPercentage:0);
		
		var maxHeight = 0;
		 $(".blockHeights").each(function(){
		   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
		});
		$(".blockHeights").height(maxHeight);
	}
	function POSTConstDateForAssemblyInfo(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var filterIdN=0;
		var filterType='';
		var locationId=0;
		if(globallevelId ==2 || globallevelId ==0){
			$(".tableMenu li").removeClass("active");
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").show();
			$("[overview-level]").show();
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
			filterIdN =locId;
			filterType ="district";
			locationId=0;
		}else if(globallevelId ==3){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level]").show();
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
			filterIdN = globallocId;
			filterType ="district";
			locationId=0;
			
			$("#districtSelectBoxchosen").html('');
			$("#districtSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#districtSelectBoxchosen").trigger("chosen:updated");
			
			$("#mandalsDistrictSelectBoxchosen").html('');
			$("#mandalsDistrictSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#mandalsDistrictSelectBoxchosen").trigger("chosen:updated");
			getAllConstituenciesForDistrict(5,"Mandal","Districts",globallocId);
			
		}else if(globallevelId ==4){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level='District']").hide();
			$("[overview-level='constituency']").show();
			$("#mandalsDistrictSelectBox").hide();
			$("#districtSelectBox").hide();
			$("#mandalsSelectBox").hide();
			filterIdN = 0;
			filterType ="";
			locationId=globallocId;
		}
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:locationId,
			locationType:"assembly",
			filterId:filterIdN,
			filterType:filterType
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				if(viewType == 'tableView')
				{
					buildTableData(result,blockId,blockName,subBlockName,viewType);
				}
				//buildSelectBox('mandalsSelectBox',result.voList,blockName);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function POSTMandalDateForAssemblyInfo(blockId,blockName,subBlockName,viewType,locId,filterId,subFilterType){
		$("#"+blockName+'_'+blockId).html(spinner);
		
		var locationId=0;
		var subFilterId=0;
		var subFilterTypeN='';
		var filterIdN=0;
		var filterTypeN='';
		if(globallevelId ==2 || globallevelId ==0){
			$(".tableMenu li").removeClass("active");
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").show();
			$("[overview-level]").show();
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
			locationId=0
			subFilterId = locId;
			subFilterTypeN =subFilterType;
			filterIdN=filterId;
			filterTypeN='district';
		}else if(globallevelId ==3){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level]").show();
			
			locationId=0;
			filterIdN=globallocId;
			filterTypeN='district';
			subFilterId = 0;
			subFilterTypeN ="";
			$("#mandalsDistrictSelectBox").show();
			$("#districtSelectBox").show();
			$("#mandalsSelectBox").show();
			$("#districtSelectBoxchosen").html('');
			$("#districtSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#districtSelectBoxchosen").trigger("chosen:updated");
			
			$("#mandalsDistrictSelectBoxchosen").html('');
			$("#mandalsDistrictSelectBoxchosen").append("<option value="+globallocId+">"+globalLocationName+" </option>");
			$("#mandalsDistrictSelectBoxchosen").trigger("chosen:updated");
			getAllConstituenciesForDistrict(5,"Mandal","Districts",globallocId);
			
		}else if(globallevelId ==4){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level='District']").hide();
			$("[overview-level='constituency']").show();
			$("#mandalsDistrictSelectBox").hide();
			$("#districtSelectBox").hide();
			$("#mandalsSelectBox").hide();
			locationId=0;
			filterIdN=0;
			filterTypeN='';
			subFilterId = globallocId;
			subFilterTypeN ="assembly";
		}else if(globallevelId ==5){
			$(".tableMenu li:nth-child(1)").addClass("active");
			$(".tableMenu li:nth-child(2)").hide();
			$("[overview-level='District']").hide();
			$("[overview-level='Constituency']").hide();
			$("#mandalsDistrictSelectBox").hide();
			$("#mandalsSelectBox").hide();
			locationId = globallocId
			filterIdN=0;
			filterTypeN='';
			subFilterId = 0;
			subFilterTypeN ="";
		}
		
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:locationId,
			locationType:"mandal",
			filterId: filterIdN,
			filterType:filterTypeN,
			subFilterId: subFilterId ,
			subFilterType: subFilterTypeN//assembly
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
			//$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function getAllDistricts(blockId,blockName,subBlockName){
		//$("#"+blockName+'_'+blockId).html('');
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllDistrictsForPris',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
			if(result !=null && result.length>0){
				if(blockId ==4){
					if(subBlockName == "Districts"){
						buildSelectBox('districtSelectBox',result,blockName,"districts");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('districtSelectBox',result,blockName,"districts");
					}
				}else if(blockId ==5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName,"districts");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName,"districts");
					}
					
				}
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function getAllParliaments(blockId,blockName,subBlockName){
		//$("#"+blockName+'_'+blockId).html('');
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllParliaments',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
			if(result !=null && result.length>0){
				if(blockId ==4){
					if(subBlockName == "Districts"){
						buildSelectBox('districtSelectBox',result,blockName,"parliaments");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('districtSelectBox',result,blockName,"parliaments");
					}
				}else if(blockId ==5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName,"parliaments");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName,"parliaments");
					}
					
				}
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	
	
	function getAllConstituenciesForDistrict(blockId,blockName,subBlockName,value){
		var json = {
			districtId :parseInt(value)
		}
		$.ajax({                
			type:'POST',    
			url: 'getAllConstituenciesForDistrict',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			// $("#"+blockName+'_'+blockId).html('');
			if(result !=null  && result.length>0){
				if(blockId == 5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsSelectBox',result,blockName,"constituency");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsSelectBox',result,blockName,"constituency");
					}
				}
				
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			} 
		});
	}
	
	function getAllConstituenciesForParliament(blockId,blockName,subBlockName,value){
		var json = {
				parliamentId:parseInt(value)
			}
		$.ajax({                
			type:'POST',    
			url: 'getAllConstituenciesForParliament',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		//	$("#"+blockName+'_'+blockId).html('');
			if(result !=null  && result.length>0){
				if(blockId == 5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsSelectBox',result,blockName,"constituency");
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsSelectBox',result,blockName,"constituency");
					}
				}
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			} 
		});
	}
	function mandalDataForDistChangeForAssembly(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId:locId,
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				if(viewType == 'tableView')
				{
					buildTableData(result,blockId,blockName,subBlockName,viewType);
				}
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function parliamentAssemblyChangeMandalOverview(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var filterId = $("#mandalsDistrictSelectBoxchosen").val();
		if(filterId == null)
		{
			filterId = 0;
		} 
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId:0,
			filterType:'district',
			subFilterId:locId,
			subFilterType: 'assembly'//assembly
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function parliamentChnageConstituencyOverview(blockId,blockName,subBlockName,viewType,locId){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"assembly",
			filterId:0,
			filterType:'district',
			subFilterId:locId,
			subFilterType: 'constituency'//assembly
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	
	function getPIRSSurveyInfoStateLevel(){
		$("#levelBlocksDivId").html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate
			
		}
		$.ajax({                
			type:'POST',    
			url: 'getPIRSSurveyInfoStateLevel',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null){
				buildPIRSSurveyInfoStateLevel(result);
			}
			
		});
	}
	
	function buildPIRSSurveyInfoStateLevel(result){
		
		
		if(result !=null){
			var str='';
					str+='<div class="white-block" style="padding:10px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-3">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object" src="Assests/icons/District_Survy_icon.png" alt="District_Survy_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="panel-title" style="font-size:12px;font-weight: 800;">NO OF <span style="color:#827B13;">DISTRICTS</span> SURVEY</h5>';
										str+='<ul class="list-inline block-level">';
											str+='<li>';
												str+='<h4><b>'+result.districtStarted+'</b></h4>';
												str+='<p>Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.districtNotStarted+'</b></h4>';
												str+='<p>Not Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.districtCompleted+'</b></h4>';
												str+='<p>Completed</p>';
											str+='</li>';
										str+='</ul>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object" src="Assests/icons/Constituency_Survy_icon.png" alt="Constituency_Survy_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="panel-title" style="font-size:12px;font-weight: 800;">NO OF <span style="color:#8341D6;">CONSTITUENCIES</span> SURVEY</h5>';
										str+='<ul class="list-inline block-level">';
											str+='<li>';
												str+='<h4><b>'+result.parliamentStarted+'</b></h4>';
												str+='<p>Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.parliamentNotStarted+'</b></h4>';
												str+='<p>Not Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.parliamentCompleted+'</b></h4>';
												str+='<p>Completed</p>';
											str+='</li>';
										str+='</ul>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object" src="Assests/icons/Mandal_Survy_icon.png" alt="Mandal_Survy_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="panel-title" style="font-size:12px;font-weight: 800;">NO OF <span style="color:#00BEE8;">MANDALS</span> SURVEY</h5>';
										str+='<ul class="list-inline block-level">';
											str+='<li>';
												str+='<h4><b>'+result.mandalStarted+'</b></h4>';
												str+='<p>Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.mandalNotStarted+'</b></h4>';
												str+='<p>Not Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.mandalCompleted+'</b></h4>';
												str+='<p>Completed</p>';
											str+='</li>';
										str+='</ul>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img class="media-object" src="Assests/icons/Gram_Panchayat_Survy_icon.png" alt="Gram_Panchayat_Survy_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="panel-title" style="font-size:12px;font-weight: 800;">NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span> SURVEY</h5>';
										str+='<ul class="list-inline block-level">';
											str+='<li>';
												str+='<h4><b>'+result.panchayatStarted+'</b></h4>';
												str+='<p>Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.panchayatNotStarted+'</b></h4>';
												str+='<p>Not Started</p>';
											str+='</li>';
											str+='<li>';
												str+='<h4><b>'+result.panchayatCompleted+'</b></h4>';
												str+='<p>Completed</p>';
											str+='</li>';
										str+='</ul>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
			$("#levelBlocksDivId").html(str);
		}
		
	}