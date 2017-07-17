var globalFromDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var globalToDate = moment().format('DD-MM-YYYY');
var blockNameArr=[{name:'District',id:'3'},{name:'Constituency',id:'4'},{name:'Mandal',id:'5'}]
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	onLoadCalls()
	$(".thisMonthOverview").html("This Month - "+moment().format("MMMM"));
	
	$(".chosen-select").chosen();
	
	function onLoadCalls()
	{
		buildLevelWiseDetailsBlock();
		POSTBasicCountBlock();
	}
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
	$(".thisMonthOverview").html(globalFromDate+" to "+globalToDate);
	
	buildLevelWiseDetailsBlock();
	POSTBasicCountBlock();
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
	}
	
});

function POSTDistrictOverview(blockId,blockName,subBlockName,viewType){
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
			getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Districts');
		}else if(blockNameArr[i].id == 5){
			POSTMandalDateForAssemblyInfo(blockNameArr[i].id,blockNameArr[i].name,'Districts','tableView',0,0,"assembly");
			getAllDistricts(blockNameArr[i].id,blockNameArr[i].name,'Parliament');
		}
	}
	}
	
}
function buildTableData(result,blockId,blockName,subBlockName,viewType){
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
						tableView+='<table class="table dataTable'+blockId+'" id="table'+blockName+'">';
								tableView+='<thead>';
								//(for constituency viwew in adding district name)
								//tableView+='<th>District Name</th>';
								//tableView+='<th>Constituency Name</th>';
								
								if(blockName == 'District'){
									tableView+='<th>District</th>';
								}else if(blockName == 'Constituency' ){
									tableView+='<th>District</th>';
									tableView+='<th>Constituency</th>';
								}else if(blockName == 'Mandal'){
									tableView+='<th>District</th>';
									tableView+='<th>Constituency</th>';
									tableView+='<th>Mandal</th>';
								}
									tableView+='<th><img class="img_widthTable" src="Assests/icons/house_icon.png" alt="house_icon"><br/>TOTAL</th>';
									tableView+='<th><img class="img_widthTable" src="Assests/icons/Target_icon.png" alt="target_icon"><br/>TARGET</th>';
									tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_icon.png" alt="Achived_icon"><br/>ACHIEVED</th>';
									tableView+='<th><img class="img_widthTable" src="Assests/icons/Achived_Perc_Icon.png" alt="Achived_%_Icon"><br/>ACHIEVED %</th>';
								tableView+='</thead>';
								
								tableView+='<tbody>';
									for(var i in result.subList){
									tableView+='<tr>';
										if(blockName == 'District'){
											tableView+='<td><img src="Assests/icons/'+result.subList[i].name+'.png" style="height: 30px;margin-right: 7px;width:30px;"/>'+result.subList[i].name+'</td>';
										}else if(blockName == 'Constituency')
										{
												tableView+='<td>'+result.subList[i].districtName+'</td>';
												tableView+='<td>'+result.subList[i].name+'</td>';
										}else if(blockName == 'Mandal')
										{
												tableView+='<td>'+result.subList[i].districtName+'</td>';
												tableView+='<td>'+result.subList[i].constituencyName+'</td>';
												tableView+='<td>'+result.subList[i].name+'</td>';
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
			$("#"+blockName+'_'+blockId).html(tableView);
			//$("#chosen"+blockName)
			if(blockId == '4' || blockId == '5'){
				$(".dataTable"+blockId).dataTable({
					"aaSorting": [],
				});
			}
			if(blockId == '3'){
				$(".dataTable"+blockId).dataTable({
					 "paging":   false,
					 "info":     false,
					 "searching": false,
					 "autoWidth": true,
					 "sDom": '<"top"iflp>rt<"bottom"><"clear">',
					 "order": [ 0, 'asc' ]
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
			if(result !=null && result.subList !=null && result.subList.length>0){
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function buildSelectBox(id,result,blockName)
	{
		var selectBox = '';
		selectBox+='<select id="'+id+'chosen" attr_blockName="'+blockName+'">';
			selectBox+='<option value="0">Select All</option>';
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
			filterType:'constituency',
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
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
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
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"assembly",
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
				//buildSelectBox('mandalsSelectBox',result.voList,blockName);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function POSTMandalDateForAssemblyInfo(blockId,blockName,subBlockName,viewType,locId,filterId,subFilterType){
		$("#"+blockName+'_'+blockId).html(spinner);
		var json = {
			fromDate:globalFromDate,
			toDate:globalToDate,
			locationId:0,
			locationType:"mandal",
			filterId: filterId,
			filterType:'district',
			subFilterId: locId ,
			subFilterType: subFilterType//assembly
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
				buildTableData(result,blockId,blockName,subBlockName,viewType);
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function getAllDistricts(blockId,blockName,subBlockName){
		
		var json = {}
		$.ajax({                
			type:'POST',    
			url: 'getAllDistricts',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.length>0){
				if(blockId ==4){
					if(subBlockName == "Districts"){
						buildSelectBox('districtSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('districtSelectBox',result,blockName);
					}
				}else if(blockId ==5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName);
					}
					
				}
			}else{
				$("#"+blockName+'_'+blockId).html("No Data Available");
			}
		});
	}
	function getAllParliaments(blockId,blockName,subBlockName){
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
			$("#"+blockName+'_'+blockId).html('');
			if(result !=null && result.length>0){
				if(blockId ==4){
					if(subBlockName == "Districts"){
						buildSelectBox('districtSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('districtSelectBox',result,blockName);
					}
				}else if(blockId ==5){
					if(subBlockName == "Districts"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsDistrictSelectBox',result,blockName);
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
						buildSelectBox('mandalsSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsSelectBox',result,blockName);
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
						buildSelectBox('mandalsSelectBox',result,blockName);
					}else if(subBlockName == "Parliament"){
						buildSelectBox('mandalsSelectBox',result,blockName);
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