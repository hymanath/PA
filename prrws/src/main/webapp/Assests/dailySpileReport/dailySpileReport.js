var globalLevelObj =  {"distLevelDistrictNames":"DISTRICT","constLevelDistNames":"DISTRICT","mandalLevelDistNames":"DISTRICT","constLevelConstNames":"CONSTITUENCY","mandalLevelConstNames":"CONSTITUENCY","mandalLevelMandalNames":"MANDAL/TOWN","villageLevelDistNames":"DISTRICT",'villageLevelConstNames':'CONSTITUENCY','villageLevelMandalNames':'MANDAL','villageLevelNames':'VILLAGE','constLevelParliaNames':'PARLIAMENT','parliamentLevelConstNames':'PARLIAMENT','villageLeveParliNames':'PARLIAMENT','distLevelParliamentNames':'PARLIAMENT','mandalLevelParliNames':'PARLIAMENT'};
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalScopeIds = {"DISTRICT":"3","CONSTITUENCY":"4","MANDAL":"5"};
var blockNames = ['DISTRICT','CONSTITUENCY','MANDAL'];
var globalStateIdForSelectBox = 21;
var glStartDate = moment().startOf('month').format("DD/MM/YYYY");
var glEndDate = moment().format("DD/MM/YYYY");
var selectedType = $("#spikeReportTypeId li.active").attr("attr_type");


var globalFilterType = 'Districts'; 
var globalFilterTypeForDist = 'Districts'; 
var globalFilterTypeForCon = 'Districts'; 
var globalFilterTypeForMan = 'Districts'; 
var globalBlockType = '';
var clickCount = 0;

onLoadCalls();              
onLoadCallsForBlocks();
function onLoadCallsForBlocks(){
	collapseBlock();  
}
function onLoadCalls()   
{
	selectedType = $("#spikeReportTypeId li.active").attr("attr_type");
	getSizeForRank(0,0,"",selectedType);
	initializeGlobalIds();
	onRequestCall();
}
$(document).on("click","#spikeReportTypeId li",function(){
	//$("#tourSlider").rangeSlider("destroy");
	var type = $(this).attr("attr_type")
	$("#spikeReportTypeId li").removeClass("active");
	$(this).addClass("active");
	$('.calendar_active_cls li').removeClass("active");
	$("#defaultButtonId").addClass("active");
	onLoadCalls();
});
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});
function getSizeForRank(min,max,status,selectedType){  
	var diseasesIdArr=[];       
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	//alert(glStartDate+":"+glEndDate+":"+diseasesIdArr+":"+min+":"+max+":"+selectedType);
	var json = {
		fromDate : glStartDate,  
		toDate : glEndDate,   
		diseasesIdList : diseasesIdArr,
		minVal : min,
		maxVal : max,
		type : selectedType
    }
    $.ajax({
      url : "getLocationDtlsRankWise",           
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){  
		  if(ajaxresp != null && ajaxresp.length > 0){
			  var len = ajaxresp.length;
			  initializeRank(ajaxresp[len-1].rankId,status);      
		  }else{
			  $("#rankDivId").html("No Data Available");
		  }
      }
    });
} //18:dateChange
function initializeRank(maxVal,status){
	if(status=="dateChange"){
		if(maxVal==1){
			$("#showHideRangeId").hide();
		}else{
			$("#showHideRangeId").show(); 
			$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 1, max: 2},defaultValues:{min: 1, max: 2}});
			$("#tourSlider").rangeSlider( "destroy" );
			$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 1, max: maxVal},defaultValues:{min: 1, max: maxVal}});
		}
		 
	}else{
		if(maxVal==1){
			$("#showHideRangeId").hide();
		}else{
			$("#showHideRangeId").show(); 
			$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 1, max: 2},defaultValues:{min: 1, max: 2}});
			$("#tourSlider").rangeSlider( "destroy" );
			$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 1, max: maxVal},defaultValues:{min: 1, max: maxVal}});
		}
	}
}

function initializeGlobalIds(){
	$(document).on("click","#getRangeId",function(){
		initializeSliderRange();  
	});
}

/* setTimeout(function(){
	$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 1, max: 174},defaultValues:{min: 1, max: 174}});   
},1000) */

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
function onLoadInitialisations(){
	$(document).keydown(function(event){   
		if(event.keyCode==123){
			alert("Hoo no! don't try to expose me");
			return false;
		}else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
			alert("Hoo no! don't try to expose me");
			return false;  //Prevent from ctrl+shift+i
		}
	});
	var width = $(window).width()
}

$("#reportrange").daterangepicker({
			opens: 'left',
			startDate: glStartDate,
			endDate: glEndDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
		   'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
});

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	glStartDate = picker.startDate.format('DD/MM/YYYY')
	glEndDate = picker.endDate.format('DD/MM/YYYY')
	if(picker.chosenLabel == 'All'){
		$("#reportrange").val('All');
	}
	$('.calendar_active_cls li').removeClass("active");
	$("#defaultButtonId").addClass("active");
	getSizeForRank(0,0,"dateChange",selectedType);
	onRequestCall();
});

$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	if(date == 'Day'){
		getCaseCountDateWise(date);
	}else if(date == 'Week'){
		getCaseCountDateWise(date);
	}else if(date == 'Month'){
		getCaseCountDateWise(date);
	}
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	
});


function onRequestCall(){
	getCaseCountDiseasesWise();
	getLocationDtlsRankWise(0,0);
	getCaseCountDateWise("day");
	var position = 0;
	$(".tableMenu").each(function(){
		var filterType = '';
		$(this).find("li").each(function(){
			if($(this).hasClass("active")){
				filterType = $(this).attr("attr-active");
			}
		});
		globalFilterType = 'Districts'; 
		globalFilterTypeForDist = 'Districts'; 
		globalFilterTypeForCon = 'Districts'; 
		globalFilterTypeForMan = 'Districts'; 
		globalBlockType = '';
		clickCount = 0;
		position = position + 1;
		initializeDistrictAsDefaultFilter(filterType,position);
	});
	
	for(var i in blockNames){  
		if(blockNames[i] == 'DISTRICT'){ 
			getCaseCountLocationWise(3,blockNames[i],"Districts",0,selectedType);    
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"distLevelDistrictNames",selectedType);
		}else if(blockNames[i] == 'CONSTITUENCY'){
			$("#constLevelConstNames").html('');   
			$("#constLevelConstNames").trigger('chosen:updated');  
			
			getCaseCountLocationWise(4,blockNames[i],"Districts",0,selectedType);
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"constLevelDistNames",selectedType);
		}else if(blockNames[i] == 'MANDAL'){
			$("#mandalLevelConstNames").html('');     
			$("#mandalLevelConstNames").trigger('chosen:updated');
			$("#mandalLevelMandalNames").html('');             
			$("#mandalLevelMandalNames").trigger('chosen:updated');
			
			getCaseCountLocationWise(5,blockNames[i],"Districts",0,selectedType);
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"mandalLevelDistNames",selectedType);
		}
	}
}
function initializeDistrictAsDefaultFilter(filterType,position){
	if(filterType == 'Parliaments'){
		if(position == 1){
			initializeDistBlock();
		}else if(position == 2){
			initializeConstBlock();
		}else if(position == 3){
			initializeMandalBlock();
		}
	}
}

function initializeDistBlock(){  
	$(".toggleClassForDistBlock").toggle();
	$("[table-menu='DISTRICT']").find("li").removeClass("active");
	$("[table-menu='DISTRICT'] li:first-child").addClass("active");
}
function initializeConstBlock(){
	$(".toggleClassForConstBlock").toggle();
	$("[table-menu='CONSTITUENCY']").find("li").removeClass("active");
	$("[table-menu='CONSTITUENCY'] li:first-child").addClass("active");
}
function initializeMandalBlock(){   
	$(".toggleClassForMandalBlock").toggle();
	$("[table-menu='MANDAL']").find("li").removeClass("active");
	$("[table-menu='MANDAL'] li:first-child").addClass("active");
}
function getCaseCountDiseasesWise(){
	$("#totalCasesId,#todayTotalCasesId,#dengueCountId,#denguePercentId,#dengueTodayId,#malariaCountId,#malariaPercentId,#malariaTodayId").html(spinner);
	var diseasesIdArr=[];
		diseasesIdArr.push(1);  
		diseasesIdArr.push(2);
	var json = {
		fromDate : glStartDate,
		toDate : glEndDate, 
		diseasesIdList : diseasesIdArr,
		type : selectedType
    }
    $.ajax({
      url : "getCaseCountDiseasesWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildOverviewBlock(ajaxresp);
		  }else{
			  
		  }
      }
    
    });
}
function buildOverviewBlock(ajaxresp){
	$("#totalCasesId").html(ajaxresp[0].count);
	$("#todayTotalCasesId").html(ajaxresp[0].todayCount);
	$("#dengueCountId").html(ajaxresp[2].count);
	$("#denguePercentId").html(ajaxresp[2].percent);
	$("#dengueTodayId").html(ajaxresp[2].todayCount);
	$("#malariaCountId").html(ajaxresp[1].count);
	$("#malariaPercentId").html(ajaxresp[1].percent);
	$("#malariaTodayId").html(ajaxresp[1].todayCount);  
}

function getLocationDtlsRankWise(min,max){
	$("#rankDivId").html(spinner);
	var diseasesIdArr=[];       
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : glStartDate,  
		toDate : glEndDate,   
		diseasesIdList : diseasesIdArr,
		minVal : min,
		maxVal : max,
		type : selectedType
    }
    $.ajax({
      url : "getLocationDtlsRankWise",           
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){  
		  if(ajaxresp != null && ajaxresp.length > 0){
			  $("#showHideRangeId").show();        
			  buildLocationDtlsRankWise(ajaxresp);
		  }else{
			  $("#showHideRangeId").hide();
			  $("#rankDivId").html("No Data Available");
		  }
      }
    });
} 
function initializeSliderRange(){  
	var min = $("#tourSlider").rangeSlider("min").toFixed(0);
	var max = $("#tourSlider").rangeSlider("max").toFixed(0);
	getLocationDtlsRankWise(min,max);      
} 

function buildLocationDtlsRankWise(ajaxresp){    
	var str = '';
	str+='<div style="padding:10px 15px;background-color:#fff;box-shadow:0 0 3px 3px rgba(0, 0, 0, 0.4)">';
		str+='<ul class="list-inline" id="rankWiseScrollId">';
		for(var i in ajaxresp){
			str+='<li style="border-left:1px solid #ccc;padding:10px;position:relative;">';
				str+='<div class="row">';
					str+='<div class="col-sm-9">';
						str+='<p><span style="padding:5px 10px;font-size:18px;border-radius:50%;background-color:#676767;color:#fff;">'+ajaxresp[i].rankId+'</span></p>';  
						str+='<p style="margin-top:5px;"><span class="text-success">D</span> - <span style="text-transform:capitalize">'+ajaxresp[i].districtName+'</span></p>';
						str+='<p><span class="text-success">C</span> - <span style="text-transform:capitalize">'+ajaxresp[i].constituencyName+'</span></p>';
						if(ajaxresp[i].mandalName.length > 0){
							str+='<p style="font-weight: bold;"><span class="text-success">M</span> - <span style="text-transform:capitalize">'+ajaxresp[i].mandalName+'</span></p>';
						}else{
							str+='<p style="font-weight: bold;"><span class="text-success">T</span> - <span style="text-transform:capitalize">'+ajaxresp[i].localElectionBodyName+'</span></p>';
						}
						
					str+='</div>';
					str+='<div class="col-sm-3">';
						str+='<h1 style="color:#FF0000;margin-top:40px;font-weight: bold;">'+ajaxresp[i].count+'</h1>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		}  
		str+='</ul>';
	str+='</div>';
	$("#rankDivId").html(str);
	$("#rankWiseScrollId").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 5,    
		slidesToScroll: 2,
		variableWidth: true
	}); 
}

          

function getCaseCountDateWise(dayType){
	$("#chronologicalId").html(spinner);
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : glStartDate,  
		toDate : glEndDate,   
		diseasesIdList : diseasesIdArr,
		rangeType : dayType,
		type : selectedType
    }
    $.ajax({
      url : "getCaseCountDateWise",           
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildGraph(ajaxresp,diseasesIdArr,dayType);
		  }else{
			  $("#chronologicalId").html("No Data Available");
		  }
      }
    });
}
function buildGraph(ajaxresp,diseasesIdArr,dayType){
	var chronologicalList = [];
	var caseCountList = [];
	for(var i in ajaxresp){
		if(dayType == 'Week'){
			chronologicalList.push(ajaxresp[i].name+' ( '+ajaxresp[i].month+' ) ');
		}else{
			chronologicalList.push(ajaxresp[i].name);
		}
		
		caseCountList.push(ajaxresp[i].count);
	}  
	var name = '';
	if(diseasesIdArr.length == 2){
		name = "Dengue & Malaria";
	}else if(diseasesIdArr[0] == 1){
		name = "Malaria";
	}else if(diseasesIdArr[0] == 2){
		name = "Dengue";
	}
	buildChronologicalGraph(chronologicalList,caseCountList,name,"chronologicalId");
}
function buildChronologicalGraph(chronologicalList,caseCountList,name,graphId){
	Highcharts.chart(graphId, {
		chart: {
			type: 'line'  
		},
		title: {
			text: 'Number Of Cases Registered'
		},
		subtitle: {
			text: 'Source: Govt Of Andhrapradesh'
		},
		xAxis: {
			categories: chronologicalList
		},
		yAxis: {
			title: {
				text: 'Number Of Cases'  
			}
		},
		plotOptions: {
			line: {
				dataLabels: {
					enabled: true
				},
				enableMouseTracking: false
			}
		},
		series: [{
			name: name,
			data: caseCountList
		}]
	});
}


$(document).on('click','[table-menu-active] li',function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
});
          
function collapseBlock(){
	var collapse='';
	if(blockNames != null){
	collapse+='<div class="row">';
		collapse+='<div class="col-sm-12">'
			for(var i in blockNames){
			collapse+='<div class="panel-group " id="accordion'+blockNames[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black">';
					collapse+='<div class="panel-heading" role="tab" id="headingOne'+blockNames[i]+'">';
						if(blockNames[i] == 'DISTRICT'){
							collapse+='<a role="button" class="panelCollapseIcon collapsed buildCollapse heagingFor'+blockNames[i]+'" data-toggle="collapse" data-parent="#accordion'+blockNames[i]+'" href="#collapseOne'+blockNames[i]+'" aria-expanded="true" aria-controls="collapseOne'+blockNames[i]+'">';
						}else if(blockNames[i] == 'CONSTITUENCY'){
							collapse+='<a role="button" class="panelCollapseIcon collapsed buildCollapse heagingFor'+blockNames[i]+'" data-toggle="collapse" data-parent="#accordion'+blockNames[i]+'" href="#collapseOne'+blockNames[i]+'" aria-expanded="true" aria-controls="collapseOne'+blockNames[i]+'">';
						}else if(blockNames[i] == 'MANDAL'){
							collapse+='<a role="button" class="panelCollapseIcon collapsed buildCollapse heagingFor'+blockNames[i]+'" data-toggle="collapse" data-parent="#accordion'+blockNames[i]+'" href="#collapseOne'+blockNames[i]+'" aria-expanded="true" aria-controls="collapseOne'+blockNames[i]+'">';
						}
							collapse+='<h4 class="panel-title">'+blockNames[i]+' LEVEL - OVERVIEW</h4>';
						collapse+='</a>';
					collapse+='</div>';
					collapse+='<div id="collapseOne'+blockNames[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+blockNames[i]+'">';
						collapse+='<div class="panel-body">';
							collapse+='<div class="row">';
								collapse+='<div class="col-sm-3" table-menu-active="'+blockNames[i]+'">';
								if(blockNames[i] == 'DISTRICT'){
									collapse+='<ul class="nav navbar-nav tableMenu" table-menu="'+blockNames[i]+'">';
										collapse+='<li class="active" attr-active="Districts">Districts</li>';
										collapse+='<li attr-active="Parliaments">Parliaments</li>';
									collapse+='</ul>'; 
								} 
								else if(blockNames[i] == 'CONSTITUENCY'){
									collapse+='<ul class="nav navbar-nav tableMenu" table-menu="'+blockNames[i]+'">';
										collapse+='<li class="active" attr-active="Districts">Districts</li>';
										collapse+='<li attr-active="Parliaments">Parliaments</li>';
									collapse+='</ul>';
								} 
								else if(blockNames[i] == 'MANDAL'){
									collapse+='<ul class="nav navbar-nav tableMenu" table-menu="'+blockNames[i]+'">';
										collapse+='<li class="active" attr-active="Districts">Districts</li>';
										collapse+='<li attr-active="Parliaments">Parliaments</li>';
									collapse+='</ul>';
								}
								collapse+='</div>';
								
								if(blockNames[i] == 'DISTRICT'){
									collapse+='<div class="col-md-2 toggleClassForDistBlock">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" id="distLevelDistrictNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2 toggleClassForDistBlock" style="display:none;">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="10" id="distLevelParliamentNames">';   
										collapse+='</select>';
									collapse+='</div>';
								}else if(blockNames[i] == 'CONSTITUENCY'){
									collapse+='<div class="col-md-2 toggleClassForConstBlock">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="no" attr_dependant_id="constLevelConstNames" id="constLevelDistNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2 toggleClassForConstBlock" style="display:none;">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="no" attr_dependant_id="constLevelConstNames" id="constLevelParliaNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="yes" attr_parent="constLevelDistNames" id="constLevelConstNames">';
										collapse+='</select>';
									collapse+='</div>';
								}else if(blockNames[i] == 'MANDAL'){
									collapse+='<div class="col-md-2 toggleClassForMandalBlock">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="no" attr_dependant_id="mandalLevelConstNames" id="mandalLevelDistNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2 toggleClassForMandalBlock" style="display:none;">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="no" attr_dependant_id="mandalLevelConstNames" id="mandalLevelParliNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2">';   
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="yes" attr_parent="mandalLevelDistNames" attr_dependant_id="mandalLevelMandalNames" id="mandalLevelConstNames">';
										collapse+='</select>';
									collapse+='</div>';
									collapse+='<div class="col-md-2">';      
										collapse+='<select class="form-control chosenSelect" attr_filter_type="Districts" attr_block_name="'+blockNames[i]+'" attr_scope_id="'+globalScopeIds[blockNames[i]]+'" is_parent="yes" attr_parent="mandalLevelConstNames" id="mandalLevelMandalNames">';
										collapse+='</select>';
									collapse+='</div>';    
								}
								/* collapse+='<div class="col-sm-3">';  
									collapse+='<ul class="nav navbar-nav casesPercentage">';
										collapse+='<li>Cases</li>';
										collapse+='<li>';
												collapse+='<div class="circle" style="background-color:#FF0000"></div>';
												collapse+='<span>70 & Above</span>';
										collapse+='</li>';
										collapse+='<li>';
												collapse+='<div class="circle" style="background-color:#FFBB00"></div>';
												collapse+='<span>30 to 70</span>';
										collapse+='</li>';
										collapse+='<li>';
												collapse+='<div class="circle" style="background-color:#00A521"></div>';
												collapse+='<span>0 to 30</span>';
										collapse+='</li>';
									collapse+='</ul>';
								collapse+='</div>'; */
							collapse+='</div>';
							collapse+='<div class="m_top20" id="'+blockNames[i]+'-tableDivId"></div>'
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>'  
			}
		collapse+='</div>'
	collapse+='</div>';
	}
	$("#levelBlock").html(collapse);
	initializeClickEventOnTableDiv();
	
	for(var i in blockNames){
		if(blockNames[i] == 'DISTRICT'){
			getCaseCountLocationWise(3,blockNames[i],"Districts",0,selectedType);    
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"distLevelDistrictNames",selectedType);
		}else if(blockNames[i] == 'CONSTITUENCY'){
			getCaseCountLocationWise(4,blockNames[i],"Districts",0,selectedType);
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"constLevelDistNames",selectedType);
		}else if(blockNames[i] == 'MANDAL'){
			getCaseCountLocationWise(5,blockNames[i],"Districts",0,selectedType);
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"mandalLevelDistNames",selectedType);
		}
	}
}

    
function initializeClickEventOnTableDiv(){
	$(".heagingForDISTRICT").addClass("collapsed");
	$(".heagingForCONSTITUENCY").addClass("collapsed");
	$(".heagingForMANDAL").addClass("collapsed");
	$("#collapseOneDISTRICT").removeClass("in");		
	$("#collapseOneCONSTITUENCY").removeClass("in");		
	$("#collapseOneMANDAL").removeClass("in");		
	$(".chosenSelect").chosen();     
	     
	$(document).on("click",".tableMenu",function(){         
		var blockLevel=$(this).attr("table-menu");
		var filterType = '';
		$(this).find("li").each(function(){
			if($(this).hasClass("active")){
				filterType = $(this).attr("attr-active");
			}
		});
		
		
		
		
		clickCount=clickCount+1;
		if(clickCount==1){
			globalBlockType=blockLevel;
			
			if(blockLevel=='DISTRICT'){
				globalFilterTypeForDist = filterType; 
				globalFilterTypeForCon='Districts';
				globalFilterTypeForMan='Districts';
			}else if(blockLevel=='CONSTITUENCY'){
				globalFilterTypeForCon = filterType; 
				globalFilterTypeForDist = 'Districts';
				globalFilterTypeForMan='Districts';
			}else if(blockLevel=='MANDAL'){
				globalFilterTypeForMan = filterType; 
				globalFilterTypeForDist = 'Districts'; 
				globalFilterTypeForCon='Districts';
			}
				
		}else{
			if(globalBlockType != blockLevel){
				if(blockLevel=='DISTRICT'){
					//alert(globalFilterTypeForDist);
					globalFilterType = globalFilterTypeForDist;
					globalFilterTypeForDist = filterType;
				}else if(blockLevel=='CONSTITUENCY'){
					//alert(globalFilterTypeForCon);
					globalFilterType = globalFilterTypeForCon;
					globalFilterTypeForCon = filterType;
				}else if(blockLevel=='MANDAL'){
					//alert(globalFilterTypeForMan);
					globalFilterType = globalFilterTypeForMan;
					globalFilterTypeForMan = filterType;
				}
				
				globalBlockType=blockLevel;
			}
		}
		
		
		var filterTypeTemp = '';
		$(this).find("li").each(function(){
			if($(this).hasClass("active")){
				filterTypeTemp = $(this).attr("attr-active");
			}
		});
		
		if(blockLevel=='DISTRICT'){
			globalFilterTypeForDist = filterTypeTemp; 
		}else if(blockLevel=='CONSTITUENCY'){
			globalFilterTypeForCon = filterType; 
		}else if(blockLevel=='MANDAL'){
			globalFilterTypeForMan = filterType; 
		}
		
		
		
		if(filterType == globalFilterType){
			return;         
		}
		globalFilterType=filterType;
		
		if(blockLevel == 'DISTRICT'){
			if(filterType == "Districts"){
				$("#distLevelDistrictNames").attr("attr_filter_type","Districts");
				getCaseCountLocationWise(3,blockLevel,filterType,0,selectedType);
			}else{
				$("#distLevelParliamentNames").attr("attr_filter_type","Parliaments");
				getCaseCountLocationWise(10,blockLevel,filterType,0,selectedType);
			}
		}else if(blockLevel == 'CONSTITUENCY'){
			if(filterType == "Districts"){
				$("#constLevelDistNames").attr("attr_filter_type","Districts");
				$("#constLevelParliaNames").attr("attr_filter_type","Districts");
				$("#constLevelConstNames").attr("attr_filter_type","Districts");
			}else{
				$("#constLevelDistNames").attr("attr_filter_type","Parliaments");
				$("#constLevelParliaNames").attr("attr_filter_type","Parliaments");
				$("#constLevelConstNames").attr("attr_filter_type","Parliaments");
			}
			getCaseCountLocationWise(4,blockLevel,filterType,0,selectedType);
		}else if(blockLevel == 'MANDAL'){
			if(filterType == "Districts"){
				$("#mandalLevelDistNames").attr("attr_filter_type","Districts");
				$("#mandalLevelParliNames").attr("attr_filter_type","Districts");
				$("#mandalLevelConstNames").attr("attr_filter_type","Districts");
				$("#mandalLevelMandalNames").attr("attr_filter_type","Districts");
			}else{
				$("#mandalLevelDistNames").attr("attr_filter_type","Parliaments");
				$("#mandalLevelParliNames").attr("attr_filter_type","Parliaments");
				$("#mandalLevelConstNames").attr("attr_filter_type","Parliaments");
				$("#mandalLevelMandalNames").attr("attr_filter_type","Parliaments");
			}
			getCaseCountLocationWise(5,blockLevel,filterType,0,selectedType);
		}
		//DISTRICT   
		if(blockLevel == 'DISTRICT' && filterType == "Parliaments"){
			$(".toggleClassForDistBlock").toggle();
			getSubLocationsBySuperLocationId(11,"distLevelParliamentNames",selectedType);
		}else if(blockLevel == 'DISTRICT' && filterType == "Districts"){
			$(".toggleClassForDistBlock").toggle();
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"distLevelDistrictNames",selectedType);   
		}
		
		//CONSTITUENCY
		if(blockLevel == 'CONSTITUENCY' && filterType == "Parliaments"){
			$(".toggleClassForConstBlock").toggle();
			
			var dependantDivId = $("#constLevelParliaNames").attr("attr_dependant_id");
			
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
			
			//UPDATE ATTR_PARENT OF constLevelConstNames SECTION
			$("#constLevelConstNames").attr("attr_parent","constLevelParliaNames");
			
			getSubLocationsBySuperLocationId(11,"constLevelParliaNames",selectedType);
		}else if(blockLevel == 'CONSTITUENCY' && filterType == "Districts"){
			$(".toggleClassForConstBlock").toggle();
			
			var dependantDivId = $("#constLevelDistNames").attr("attr_dependant_id");
			
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
			
			//UPDATE ATTR_PARENT OF constLevelConstNames SECTION
			$("#constLevelConstNames").attr("attr_parent","constLevelDistNames");
			
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"constLevelDistNames",selectedType);   
		}
		
		//MANDAL
		if(blockLevel == 'MANDAL' && filterType == "Parliaments"){
			$(".toggleClassForMandalBlock").toggle();
			
			var dependantDivId = $("#mandalLevelParliNames").attr("attr_dependant_id");
			
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
			
			var nextDependantDivId = $("#"+dependantDivId).attr("attr_dependant_id");
			
			$("#"+nextDependantDivId).html('');   
			$("#"+nextDependantDivId).trigger('chosen:updated');
			
			//UPDATE ATTR_PARENT OF mandalLevelConstNames SECTION
			$("#mandalLevelConstNames").attr("attr_parent","mandalLevelParliNames");
			
			
			getSubLocationsBySuperLocationId(11,"mandalLevelParliNames",selectedType);
		}else if(blockLevel == 'MANDAL' && filterType == "Districts"){
			$(".toggleClassForMandalBlock").toggle();
			
			var dependantDivId = $("#mandalLevelDistNames").attr("attr_dependant_id");
			
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
			
			var nextDependantDivId = $("#"+dependantDivId).attr("attr_dependant_id");
			
			$("#"+nextDependantDivId).html('');       
			$("#"+nextDependantDivId).trigger('chosen:updated');
			
			//UPDATE ATTR_PARENT OF mandalLevelConstNames SECTION
			$("#mandalLevelConstNames").attr("attr_parent","mandalLevelDistNames");
			
			getSubLocationsBySuperLocationId(globalStateIdForSelectBox,"mandalLevelDistNames",selectedType);   
		}
		
	});
	
	$(document).on("change","#constLevelDistNames",function(){
		var dependantDivId = $("#constLevelDistNames").attr("attr_dependant_id");
		var dependantId = $("#constLevelDistNames").val();
		if(dependantId == 0){
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
		}
		getSubLocationsBySuperLocationId(dependantId,dependantDivId,selectedType);
	});
	
	$(document).on("change","#mandalLevelDistNames",function(){
		var dependantDivId = $("#mandalLevelDistNames").attr("attr_dependant_id");
		var dependantId = $("#mandalLevelDistNames").val();
		if(dependantId == 0){
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
			
			var nextDependantDivId = $("#"+dependantDivId).attr("attr_dependant_id");
			$("#"+nextDependantDivId).html('');   
			$("#"+nextDependantDivId).trigger('chosen:updated');
			
		}
		getSubLocationsBySuperLocationId(dependantId,dependantDivId,selectedType);
	});
	
	$(document).on("change","#mandalLevelConstNames",function(){
		var dependantDivId = $("#mandalLevelConstNames").attr("attr_dependant_id");
		var dependantId = $("#mandalLevelConstNames").val();
		if(dependantId == 0){
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
		}  
		getSubLocationsBySuperLocationId(dependantId,dependantDivId,selectedType);
	});
	
	$(document).on("change","#constLevelParliaNames",function(){
		var dependantDivId = $("#constLevelParliaNames").attr("attr_dependant_id");
		var dependantId = $("#constLevelParliaNames").val();
		if(dependantId == 0){  
			$("#"+dependantDivId).html('');   
			$("#"+dependantDivId).trigger('chosen:updated');
		}
		getSubLocationsBySuperLocationId(dependantId,dependantDivId,selectedType);
	});
	
	$(document).on("change","#mandalLevelParliNames",function(){
		var dependantDivId = $("#mandalLevelParliNames").attr("attr_dependant_id");
		var dependantId = $("#mandalLevelParliNames").val();
		if(dependantId == 0){
			$("#"+dependantDivId).html('');      
			$("#"+dependantDivId).trigger('chosen:updated');
			
			var nextDependantDivId = $("#"+dependantDivId).attr("attr_dependant_id");
			$("#"+nextDependantDivId).html('');   
			$("#"+nextDependantDivId).trigger('chosen:updated');
			
		}
		getSubLocationsBySuperLocationId(dependantId,dependantDivId,selectedType);   
	});
	
	//ON CHANGE AJAX CALLS
	//DISTRICT ON CHANGE
	$(document).on("change","#distLevelDistrictNames",function(){
		var scopeId = $("#distLevelDistrictNames").attr("attr_scope_id");
		var blockName = $("#distLevelDistrictNames").attr("attr_block_name");
		var filterType = $("#distLevelDistrictNames").attr("attr_filter_type");
		var locationId = $("#distLevelDistrictNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	$(document).on("change","#distLevelParliamentNames",function(){
		var scopeId = $("#distLevelParliamentNames").attr("attr_scope_id");
		var blockName = $("#distLevelParliamentNames").attr("attr_block_name");
		var filterType = $("#distLevelParliamentNames").attr("attr_filter_type");
		var locationId = $("#distLevelParliamentNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	//CONSTITUENCY ON CHANGE
	$(document).on("change","#constLevelDistNames",function(){
		var scopeId = $("#constLevelDistNames").attr("attr_scope_id");
		var blockName = $("#constLevelDistNames").attr("attr_block_name");
		var filterType = $("#constLevelDistNames").attr("attr_filter_type");
		var locationId = $("#constLevelDistNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	$(document).on("change","#constLevelParliaNames",function(){
		var scopeId = $("#constLevelParliaNames").attr("attr_scope_id");
		var blockName = $("#constLevelParliaNames").attr("attr_block_name");
		var filterType = $("#constLevelParliaNames").attr("attr_filter_type");
		var locationId = $("#constLevelParliaNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	$(document).on("change","#constLevelConstNames",function(){
		var scopeId = $("#constLevelConstNames").attr("attr_scope_id");
		var blockName = $("#constLevelConstNames").attr("attr_block_name");
		var filterType = $("#constLevelConstNames").attr("attr_filter_type");
		var locationId = $("#constLevelConstNames").val();
		if(locationId == 0){
			var parentDivId = $("#constLevelConstNames").attr("attr_parent");
			locationId = $("#"+parentDivId).val();  
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}else{
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}
		
	});
	//MANDAL ON CHANGE
	$(document).on("change","#mandalLevelDistNames",function(){
		var scopeId = $("#mandalLevelDistNames").attr("attr_scope_id");
		var blockName = $("#mandalLevelDistNames").attr("attr_block_name");
		var filterType = $("#mandalLevelDistNames").attr("attr_filter_type");
		var locationId = $("#mandalLevelDistNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	$(document).on("change","#mandalLevelParliNames",function(){
		var scopeId = $("#mandalLevelParliNames").attr("attr_scope_id");
		var blockName = $("#mandalLevelParliNames").attr("attr_block_name");
		var filterType = $("#mandalLevelParliNames").attr("attr_filter_type");
		var locationId = $("#mandalLevelParliNames").val();
		getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
	});
	$(document).on("change","#mandalLevelConstNames",function(){
		var scopeId = $("#mandalLevelConstNames").attr("attr_scope_id");
		var blockName = $("#mandalLevelConstNames").attr("attr_block_name");
		var filterType = $("#mandalLevelConstNames").attr("attr_filter_type");
		var locationId = $("#mandalLevelConstNames").val();
		if(locationId == 0){
			var parentDivId = $("#mandalLevelConstNames").attr("attr_parent");
			locationId = $("#"+parentDivId).val();  
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}else{
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}
		
	});
	$(document).on("change","#mandalLevelMandalNames",function(){
		var scopeId = $("#mandalLevelMandalNames").attr("attr_scope_id");
		var blockName = $("#mandalLevelMandalNames").attr("attr_block_name");
		var filterType = $("#mandalLevelMandalNames").attr("attr_filter_type");
		var locationId = $("#mandalLevelMandalNames").val();
		if(locationId == 0){
			var parentDivId = $("#mandalLevelMandalNames").attr("attr_parent");
			locationId = $("#"+parentDivId).val();  
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}else{
			getCaseCountLocationWise(scopeId,blockName,filterType,locationId,selectedType);
		}
	});
}

function getCaseCountLocationWise(scopeId,blockName,filterType,locationId,type){
	$("#"+blockName+"-tableDivId").html(spinner);
	var diseasesIdArr=[];     
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var constituencyId ;
	
	if(scopeId != null && (scopeId == 5)){
		constituencyId = $("#mandalLevelConstNames").val();
	}else{
		constituencyId = 0;
	}
	var json = {
		fromDate : glStartDate,
		toDate : glEndDate, 
		diseasesIdList : diseasesIdArr,
		scopeId : scopeId,              
		locationId : locationId,
		type : type,
		constituencyId : constituencyId
    }
    $.ajax({
      url : "getCaseCountLocationWise",       
      data : JSON.stringify(json),
      type : "POST",    
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			  buildTableData(ajaxresp,blockName,filterType,glStartDate,glEndDate);
		  }else{
			  $("#"+blockName+"-tableDivId").html("No Data Available");
		  }
      }
    
    });
}

function buildTableData(ajaxresp,blockName,filterType,glStartDate,glEndDate){
	var buildTable='';
	buildTable+='<div class="row">';
		buildTable+='<div class="col-sm-12">';
			buildTable+='<div class="table-responsive">';
				buildTable+='<table class="table dataTable'+blockName+'" id="build'+blockName+'-tableId" style="width:100%">';
					buildTable+='<thead>';
						buildTable+='<tr>';
							if(blockName == 'DISTRICT'){
								if(filterType == "Districts"){
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}else{
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}
							}
							else if(blockName == 'CONSTITUENCY'){
								if(filterType == "Districts"){
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}else{
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}
							}
							else if(blockName == 'MANDAL'){
								if(filterType == "Districts"){
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}else{
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
									buildTable+='<th style="background-color:#FFFFFF;"></th>';
								}
							}
							buildTable+='<th colspan="3" style="background-color:#FFFFFF;"></th>';
							buildTable+='<th colspan="5" style="background-color:#F3FFFF;text-align:center;">'+glStartDate+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp-&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+glEndDate+'</th>'; 
						buildTable+='</tr>';    
						buildTable+='<tr>';
							if(blockName == 'DISTRICT'){
								if(filterType == "Districts"){
									buildTable+='<th></th>';
								}else{
									buildTable+='<th></th>';
									buildTable+='<th></th>';
								}
							}
							else if(blockName == 'CONSTITUENCY'){
								if(filterType == "Districts"){
									buildTable+='<th></th>';
									buildTable+='<th></th>';
								}else{
									buildTable+='<th></th>';
									buildTable+='<th></th>';
									buildTable+='<th></th>';
								}
							}
							else if(blockName == 'MANDAL'){
								if(filterType == "Districts"){
									buildTable+='<th></th>';
									buildTable+='<th></th>';
									buildTable+='<th></th>';
								}else{
									buildTable+='<th></th>';
									buildTable+='<th></th>';
									buildTable+='<th></th>';
									buildTable+='<th></th>';
								}
							}
							buildTable+='<th colspan="3" style="text-align:center"><img style="width: 50px; height: 30px;" src="Assests/img/Dengue&maleria_Sm.png">Dengue & Malaria Cases</th>';
							buildTable+='<th colspan="1" style="background-color:#F3FFFF"></th>';
							buildTable+='<th colspan="2" style="background-color:#FDE5F2;text-align:center"><img style="width: 50px; height: 30px;" src="Assests/img/Dengue_Sm.png">Dengue</th>';
							buildTable+='<th colspan="2" style="background-color:#E5F0F5;text-align:center"><img style="width: 50px; height: 30px;" src="Assests/img/maleria_Sm.png">Malaria</th>';
						buildTable+='</tr>';    
						buildTable+'<tr>';
							
							if(blockName=='DISTRICT'){
								if(filterType == "Districts"){
									buildTable+='<th>DISTRICT</th>';
								}else{
									buildTable+='<th>DISTRICT</th>';
									buildTable+='<th>PARLIAMENT</th>';
								}
							}else if(blockName=='CONSTITUENCY'){
								if(filterType == "Districts"){
									buildTable+='<th>DISTRICT</th>';
									buildTable+='<th>CONSTITUENCY</th>';
								}else{
									buildTable+='<th>DISTRICT</th>';
									buildTable+='<th>PARLIAMENT</th>';
									buildTable+='<th>CONSTITUENCY</th>';
								}
							}else if(blockName=='MANDAL'){
								if(filterType == "Districts"){
									buildTable+='<th>DISTRICT</th>';
									buildTable+='<th>CONSTITUENCY</th>';
									buildTable+='<th>MANDAL/TOWN</th>';
								}else{
									buildTable+='<th>DISTRICT</th>';
									buildTable+='<th>PARLIAMENT</th>';
									buildTable+='<th>CONSTITUENCY</th>';
									buildTable+='<th>MANDAL/TOWN</th>';
								}
							}
							buildTable+='<th style="background-color:#F3F3F3">TILL NOW</th>';
							buildTable+='<th style="background-color:#F3F3F3">TODAY DENGUE CASES</th>';
							buildTable+='<th style="background-color:#F3F3F3">TODAY MALARIA CASES</th>';
							
							buildTable+='<th style="background-color:#F3FFFF">TOTAL</th>';
							
							buildTable+='<th style="background-color:#FDE5F2">DENGUE TOTAL</th>';
							buildTable+='<th style="background-color:#FDE5F2">%</th>';
							
							buildTable+='<th style="background-color:#E5F0F5">MALARIA TOTAL</th>';
							buildTable+='<th style="background-color:#E5F0F5">%</th>';
						buildTable+'</tr>';
					buildTable+='</thead>';	
					buildTable+='<tbody>';
						for(var i in ajaxresp){
							buildTable+='<tr>';
								if(blockName=='DISTRICT'){
									if(filterType == "Districts"){
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}else{
										buildTable+='<td>'+ajaxresp[i].districtName+'</td>';
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}
								}else if(blockName=='CONSTITUENCY'){
									if(filterType == "Districts"){
										buildTable+='<td>'+ajaxresp[i].districtName+'</td>';
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}else{
										buildTable+='<td>'+ajaxresp[i].districtName+'</td>';
										buildTable+='<td>'+ajaxresp[i].parliamentName+'</td>';
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}
								}else if(blockName=='MANDAL'){
									if(filterType == "Districts"){
										buildTable+='<td>'+ajaxresp[i].districtName+'</td>';
										buildTable+='<td>'+ajaxresp[i].constituencyName+'</td>';
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}else{
										buildTable+='<td>'+ajaxresp[i].districtName+'</td>';
										buildTable+='<td>'+ajaxresp[i].parliamentName+'</td>';
										buildTable+='<td>'+ajaxresp[i].constituencyName+'</td>';
										buildTable+='<td>'+ajaxresp[i].name+'</td>';
									}
								}
								
								if(ajaxresp[i].overAll > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].overAll+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].dengueToday > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].dengueToday+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].malariaToday > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].malariaToday+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].total > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].total+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].dengueTotal > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].dengueTotal+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].denguePercent > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].denguePercent+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].malariaTotal > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].malariaTotal+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								if(ajaxresp[i].malariaPercent > 0){
									buildTable+='<td style="background-color:#F3F3F3">'+ajaxresp[i].malariaPercent+'</td>';
								}else{
									buildTable+='<td style="background-color:#F3F3F3">-</td>';
								}
								
								
							buildTable+='</tr>';
						}
					buildTable+='</tbody>';
				buildTable+='</table>';
			buildTable+='</div>';
		buildTable+='</div>';
	buildTable+='</div>';
	$("#"+blockName+"-tableDivId").html(buildTable);
	$("#build"+blockName+"-tableId").dataTable({
		"aLengthMenu": [[10, 15, 20,100, -1], [10, 15, 20,100, "All"]],
		 "iDisplayLength": 15	
	});
	
	
	
}

function getSubLocationsBySuperLocationId(globalStateIdForSelectBox,divId,selectedType){
	var diseasesIdArr=[];
	diseasesIdArr.push(1);
	diseasesIdArr.push(2);
	var json = {
		fromDate : glStartDate,
		toDate : glEndDate,                 
		diseasesIdList : diseasesIdArr,
		superLocationId : globalStateIdForSelectBox,
		type       : selectedType
    }
    $.ajax({
      url : "getSubLocationsBySuperLocationId",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
			if(ajaxresp !=null && ajaxresp.length>0){
			$("#"+divId).html('');
			$("#"+divId).append('<option value="0">ALL '+globalLevelObj[divId]+'</option>');
			for(var i in ajaxresp){
				$("#"+divId).append('<option value="'+ajaxresp[i].id+'">'+ajaxresp[i].name+'</option>');
			}
		}	
		$("#"+divId).trigger('chosen:updated');
      }
    });
}
