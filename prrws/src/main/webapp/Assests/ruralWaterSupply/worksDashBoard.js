 //Angular Start  getAlertsOfCategoryByStatusWise()
	var glStartDate = "01-04-"+moment().subtract(40,'years').format("YYYY");
	var glEndDate = "01-04-"+moment().add(10, 'years').format("YYYY");
	var globalStatusObj={"QA":"#494949","PC":"#FC5049","FC":"#14BAAD","Ground":"#14BAAD","Surface":"#FC5049","SAFE":"#14BAAD","UN-SAFE":"#FC5049",
	"SINGAL VILLAGE":"#14BAAD","MULTI VILLAGE":"#FC5049","physicalTestCount":"#14BAAD","bacterialTestCount":"#FC5049",
	"Completely Satisfied":"#0FBE08","Not Satisfied":"#FF0909","Partially Satisfied":"#FFBA00","SATISFIED":"#0FBE08","PARTIALLY SATISFIED":"#FFBA00","NOT SATISFIED":"#FF0909","TARGET":"#FC5049","COMPLETED":"#14BAAD"}
	var blocksArr = [{name:'Coverage Status Of Habitation',id:'habitation',img:'coverage_status.png'},
					 {name:'Key Performance',id:'performance',img:'key_performance.png'},
					 {name:'<p><span><img src="Assests/icons/alert_status.png"/> Jalavani </span></p>',id:'jalavani',img:'alert_status.png'},
					 {name:'Assests',id:'assestsId',img:'assets.png'},{name:'Water Source',id:'waterSourceId',img:'works.png'},{name:'Work Schemes',id:'schemeId',img:'schemes.png'}];
	var alertStatusBlockArr = [{name:'Alert Status Jalavani',id:'alertStatus'},{name:'Drinking Water Satisfaction Levels',id:'drinking'}];
	var blockArrClickArr = [{name:'HabitationClick',id:'modalHablitationTable'},{name:'KpiClick',id:'modalKpiTable'},{name:'JalavaniClick',id:'modalAlertTable'},{name:'IvrStatusClick',id:'modalIvrStatusTable'},{name:'AssetsClick',id:'modalAssetsTable'},{name:'WaterSourceClick',id:'modalWaterSourceTable'},{name:'WorkSchemsClick',id:'modalWorkSchemsTable'}];
	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	var levelNamesArr=[{name:'state',id:'2'},{name:'district',id:'3'},{name:'constituency',id:'4'},{name:'mandal',id:'5'}];
	var levelWiseSBArr = ['state','district','constituencies','mandal','panchayat'];
	var globalStateLevelExceededTargetWorks='';
	getAllFiniancialYears();
	function onloadCalls(){
		globalStateLevelExceededTargetWorks='';
		getSchemeWiseWorkDetails('graph','state',"","","","");
		getAllPrrwsDistricts("chosendistValconstituencyBlockId");
		getAllPrrwsDistricts("chosendistValmandalBlockId");
		tabBlocks('stateBlockId','state');
		tabBlocks('districtBlockId','district');
		tabBlocks('constituencyBlockId','constituency');
		tabBlocks('mandalBlockId','mandal');
		responsiveTabs();
		//getExceededTargetWorksDetails();
		getExceedWorkDetailsLocationWise("",'state',"","","","","","onGoing");
		getNotGroundedWorkDetailsLocationWise("graph",'state',"","","","","","not grounded");
	}
	function getSelectedType(){
		for(var i in levelNamesArr){
			if(levelNamesArr[i].name == "constituency"){
				var blockNameC = ''; 
				var tabIdC='';
				$('.custom_value'+levelNamesArr[i].name+' li').each(function(i, obj){
					 if($(this).hasClass('active')){
					  blockNameC = $(this).attr("attr_block_name");
					  tabIdC=$(this).attr("tab_id");
					 }
				});
				return {
					blockNameC:blockNameC,
					tabIdC:tabIdC
				}
			}
		} 
	}
	var width = $(window).width();
	if(width > 767)
	{
		header = $('header section'),
		$(window).scroll(function(){
			var windowScrollTop = $(window).scrollTop();

			if (windowScrollTop>50) {
				header.addClass("header-fixed");
			} else {
				header.removeClass("header-fixed");
			}
		});
	}
	function getSelectedTypeMandal(){
		for(var i in levelNamesArr){
			if(levelNamesArr[i].name == "mandal"){
				var blockNameM = ''; 
				var tabIdM='';
				$('.custom_value'+levelNamesArr[i].name+' li').each(function(i, obj){
					 if($(this).hasClass('active')){
					  blockNameM = $(this).attr("attr_block_name");
					  tabIdM=$(this).attr("tab_id");
					 }
				});
				return {
					blockNameM:blockNameM,
					tabIdM:tabIdM
				}
			}
			
		} 
	}
	function getSchemeWiseWorkDetails(type,locationType,divId,filterType,filterValue,districtValue){
		//$("#habitationWorksPWS,#habitationWorksCPWS").html(spinner);
		var typeVal="";
		if(type =="graph"){
			$("#habitationWorksPWS,#habitationWorksCPWS").html(spinner);
			$("#habitationWorks").html(spinner);
			typeVal ="graph"
		}else{
			for(var k in divId){
				$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
			}
		}
		
		var yearVal="";
		var financialVal =$("#financialYearId").val();
		if(financialVal != 0){
			 yearVal=financialVal;
		}
		var json = {
			  fromDateStr:glStartDate,
			  toDateStr:glEndDate,
			  year:yearVal,
			  locationType:locationType,
			  type:typeVal,
			  filterType:filterType,
			  filterValue:filterValue,
			  districtValue:districtValue
		  }
			  var url ='';
			  if(type == "graph"){
				  url='getSchemeWiseWorkDetails2';
			  }else{
				   url='getSchemeWiseWorkDetails';
			  }
			$.ajax({
				url: url,
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						if(type == "graph"){
							buildSchemeWiseWorkDetails(ajaxresp);
						}else{
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations',"completeOverview");
						}
						
					}else{
						if(type == "graph"){
							$("#habitationWorks").html("No Data Available");
							$("#habitationWorksPWS,#habitationWorksCPWS").html("No Data Available");
						}else{
							for(var k in divId){
								$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
							}
						}
						
					}
				}
			});
		  }
		  function buildSchemeWiseWorkDetails(result){
			  var str='';
			   var str1='';
				str+='<table id="datatable">';
					str+='<thead>';
						str+='<tr>';
							str+='<th></th>';
							str+='<th>OverAll Works</th>';
							str+='<th>Exceed Works</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						if(result[i].assetType == "PWS"){
							for(var j in result[i].subList){
								str+='<tr>';
									str+='<th>'+result[i].subList[j].workStatus+'</th>';
									str+='<td>'+result[i].subList[j].workOngoingCount+'</td>';
									str+='<td>'+result[i].subList[j].ongoingPWSExceededCount+'</td>';
								str+='</tr>';
							}
						}
						
						
					}
						
					str+='</tbody>';
				str+='</table>';
				str+='<table id="datatable1">';
					str+='<thead>';
						str+='<tr>';
							str+='<th></th>';
							str+='<th>OverAll Works</th>';
							str+='<th>Exceed Works</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						if(result[i].assetType == "CPWS"){
							for(var j in result[i].subList){
								str+='<tr>';
									str+='<th>'+result[i].subList[j].workStatus+'</th>';
									str+='<td>'+result[i].subList[j].workOngoingCount+'</td>';
									str+='<td>'+result[i].subList[j].ongoingPWSExceededCount+'</td>';
								str+='</tr>';
							}
						}
						
						
					}
						
					str+='</tbody>';
				str+='</table>';
			$("#graphTable").html(str);	
			$("#habitationWorksPWS").highcharts({
				colors:['#16af18','red'],
				data: {
					table: 'datatable'
				},
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					title: {
						text: 'Units'
					}
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
							this.point.y + '';
					}
				}
			});
			$("#habitationWorksCPWS").highcharts({
				colors:['#16af18','red'],
				data: {
					table: 'datatable1'
				},
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					allowDecimals: false,
					title: {
						text: 'Units'
					}
				},
				tooltip: {
					formatter: function () {
						return '<b>' + this.series.name + '</b><br/>' +
							this.point.y + '';
					}
				}
			});
    
		  }
		  
	/* function buildSchemeWiseWorkDetails(result){
		var dataArr = [];
		var assetTypeArrPWS = [];
		var assetTypeArrCPWS = [];
		var workOngoingArrPWS = [];
		var workOngoingArrCPWS = [];
		var workNotGroundedArrPWS = [];
		var workNotGroundedArrCPWS = [];
		var workCompletedArrPWS = [];
		var workCompletedArrCPWS = [];
		var workComissionedArrPWS = [];
		var workComissionedArrCPWS = [];
			for(var i in result)
			  {					 
				if(result[i].assetType == "PWS")
				{
					assetTypeArrPWS.push(result[i].assetType);						
					workOngoingArrPWS.push({"y":result[i].workOngoingCount,"extra":result[i].percentageOne.toFixed(1)});
					workNotGroundedArrPWS.push({"y":result[i].workNotGroundedCount,"extra":result[i].percentageFour.toFixed(1)});
					workCompletedArrPWS.push({"y":result[i].workCompletedCount,"extra":result[i].percentageThree.toFixed(1)});
					workComissionedArrPWS.push({"y":result[i].workComissionedCount,"extra":result[i].percentageTwo.toFixed(1)});						
				}else if(result[i].assetType == "CPWS"){
					assetTypeArrCPWS.push(result[i].assetType);						
					workOngoingArrCPWS.push({"y":result[i].workOngoingCount,"extra":result[i].percentageOne.toFixed(1)});
					workNotGroundedArrCPWS.push({"y":result[i].workNotGroundedCount,"extra":result[i].percentageFour.toFixed(1)});
					workCompletedArrCPWS.push({"y":result[i].workCompletedCount,"extra":result[i].percentageThree.toFixed(1)});
					workComissionedArrCPWS.push({"y":result[i].workComissionedCount,"extra":result[i].percentageTwo.toFixed(1)});			
				}
				
			  }
			$("#habitationWorksPWS").highcharts({
				colors:['#00B5A7','#FC5D57','#FFC013','#1D9ED5'],
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align:'left',
					style: {
						color: '#000',
						font: 'bold 16px "Lato", sans-serif'
					}
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: assetTypeArrPWS
				},
				yAxis:{
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
						title: {
							text: null
						},
				}, 
				
				legend: {
					symbolHeight: 12,
					symbolWidth: 12,
					symbolRadius: 6,
					enabled: true
				},
				tooltip: {
					useHTML:true,
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y+"-"+((this.point.extra))+'%';
					}
				},
				plotOptions: {
					column: {
						//colorByPoint: true
						dataLabels: {
							useHTML:true,
							enabled: true,
							formatter: function() {
								if(this.y == 0){
									return null;
								}else{
									return '<span>'+this.y+'<br>('+(this.point.extra)+'%)</span>';
								}
								
							}
						}
					}
				},
				series: [{
						name: 'Commissioned',
						data: workComissionedArrPWS,
						color:'#16af18'
					},{
						name: 'Completed',
						data: workCompletedArrPWS,
						color:'#3ae80f'
					}, {
						name: 'Ongoing',
						data: workOngoingArrPWS,
						color:'#EA8512'
					}, {
						name: 'Not Grounded',
						data: workNotGroundedArrPWS,
						color:'#FC5E57'
					}]
			});
			$("#habitationWorksCPWS").highcharts({
				colors:['#00B5A7','#FC5D57','#FFC013','#1D9ED5'],
				chart: {
					type: 'column'
				},
				title: {
					text: '',
					align:'left',
					style: {
						color: '#000',
						font: 'bold 16px "Lato", sans-serif'
					}
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: assetTypeArrCPWS
				},
				yAxis:{
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
						title: {
							text: null
						},
				}, 
				
				legend: {
					symbolHeight: 12,
					symbolWidth: 12,
					symbolRadius: 6,
					enabled: true
				},
				tooltip: {
					useHTML:true,
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y+"-"+((this.point.extra))+'%';
					}
				},
				plotOptions: {
					column: {
						//colorByPoint: true
						dataLabels: {
							useHTML:true,
							enabled: true,
							formatter: function() {
								if(this.y == 0){
									return null;
								}else{
									return '<span>'+this.y+'<br>('+(this.point.extra)+'%)</span>';
								}
								
							}
						}
					}
				},
				series: [{name: 'Commissioned',
						data: workComissionedArrCPWS,
						color:'#16af18'
						
					}, {
						name: 'Completed',
						data: workCompletedArrCPWS,
						color:'#3ae80f'
					}, {
						name: 'Ongoing',
						data: workOngoingArrCPWS,
						color:'#EA8512'
					}, {
						name: 'Not Grounded',
						data: workNotGroundedArrCPWS,
						color:'#FC5E57'
					}]
			});
			
	} */
function tabBlocks(blockId,blockName){
	var tabBlock = '';
	var tableId = '';
	tabBlock+='<div class="panel panel-black panel-default">';
		tabBlock+='<div class="panel-heading" id="heading'+blockId+'">';
		if(blockId == "stateBlockId"){
			tabBlock+='<a class="panelCollapseIcon collapseActiveStateCls" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+blockId+'" aria-expanded="true" aria-controls="collapse'+blockId+'">';
			tabBlock+='<h4 class="panel-title text-capital">'+blockName+' level overview</h4>';
			tabBlock+='</a>';
		}else{
			tabBlock+='<a class="panelCollapseIcon collapseActiveStateCls collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+blockId+'" aria-expanded="true" aria-controls="collapse'+blockId+'">';
			tabBlock+='<h4 class="panel-title text-capital">'+blockName+' level overview</h4>';
			tabBlock+='</a>';
		}
		
			
			
		tabBlock+='</div>';
		if(blockId  == "stateBlockId"){
			tabBlock+='<div id="collapse'+blockId+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+blockId+'">';
		}else{
			tabBlock+='<div id="collapse'+blockId+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+blockId+'">';
		}
		
			
		
				var statusType='';
				$('.exceedWorkTypeCls').each(function(i, obj){
					 if($(this).is(':checked')){
						statusType = $(this).val();
					 }
				});
				if(statusType == ""){
					$(".headingExceedId").html("All Exceeded Works")
				}else{
					$(".headingExceedId").html("OnGoing Exceeded Works")
				}		
		tabBlock+='<div class="panel-body">';
			tabBlock+='<div class="row">';
				tabBlock+='<div class="col-sm-8">';
					tabBlock+='<ul class="switch-btn-New" role="tabCummulative" attr_level_type="'+blockName+'">';
						tabBlock+='<li attr_type="completeOverview">Complete Works Overview</li>';
						tabBlock+='<li class="active ActiveStateCls" attr_type="exceededOverview">Exceeded Target Works Details - <span class="headingExceedId"></span></li>';
						tabBlock+='<li attr_type="notGroundedoverView">NotGrounded Works Overview</li>';
					tabBlock+='</ul>';
				tabBlock+='</div>';
				tabBlock+='<div class="col-sm-4">';
					tabBlock+='<div class="pull-right">';
						tabBlock+='<label class="checkbox-inline">';
							tabBlock+='<input type="checkbox"  class="checkboxTypeCls" attr_block_name="'+blockName+'" id="checkboxType'+blockName+'Id" name="option" value="amount" checked>With Amount';
						tabBlock+='</label>';
					tabBlock+='</div>';
				tabBlock+='</div>';
			tabBlock+='</div>';
			
			tabBlock+='<select class="form-control" role="tabListMobile">';
				for(var i in blocksArr)
				{
					tabBlock+='<option  attr_block_name='+blockName+' attr_block_id='+blockId+' tab_id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].name+'</option>';
				}
			tabBlock+='</select>';
			tabBlock+='<div class="tab-content">';
				if(blockId == 'constituencyBlockId'){
						tabBlock+='<div class="row">';
							tabBlock+='<div class="col-sm-3"><div id="distVal'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constVal'+blockId+'"></div></div>';
						tabBlock+='</div>';
				}else if(blockId == 'mandalBlockId'){
						tabBlock+='<div class="row">';
								tabBlock+='<div class="col-sm-3"><div id="distVal'+blockId+'"></div></div>';
								tabBlock+='<div class="col-sm-3"><div id="constVal'+blockId+'"></div></div>';
								tabBlock+='<div class="col-sm-3"><div id="mandalVal'+blockId+'"></div></div>';
						tabBlock+='</div>';
				}
				for(var i in blocksArr)
				{
					if(i == 5)
					{
						tabBlock+='<div role="tabpanel" class="tab-pane active m_top10" id="'+blockId+''+blocksArr[i].id+'"></div>';
					
					}else{
						tabBlock+='<div role="tabpanel" class="tab-pane m_top10" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
					}
				} 
			tabBlock+='</div>';
		tabBlock+='</div>';
	tabBlock+='</div>';
	tabBlock+='</div>';
	$("#"+blockId).html(tabBlock);
	$(".overViewCls").hide();
	
	if(blockId == 'constituencyBlockId'){
		selectBox('distVal'+blockId+'');
		selectBox('constVal'+blockId+'');
	}else if(blockId == 'mandalBlockId'){
		selectBox('distVal'+blockId+'');
		selectBox('constVal'+blockId+'');
		selectBox('mandalVal'+blockId+'');
	}
	$("[role='tabCummulative'] li:nth-child(2)").trigger("click");
}
function selectBox(id){
	var id = id;
	var selectBox='';
	if(id == "distValconstituencyBlockId" || id == "distValmandalBlockId"){
		selectBox+='<label>District</label>';
	}else if(id == "constValconstituencyBlockId" || id == "constValmandalBlockId"){
			selectBox+='<label>Constituency</label>';
	}else if(id == "mandalValmandalBlockId"){
			selectBox+='<label>Mandal</label>';
	}
	selectBox+='<select class="chosen" id="chosen'+id+'"></select>';
	$("#"+id).html(selectBox);
	$("#chosen"+id).chosen();
}
$(".chosenSelect").chosen({width:'100%'});
$(window,document).on('resize', function(){
	responsiveTabs();
});
	function buildTableForHabitationCoverage(result,locationType,divId,type,blockType){
		
		var GLtbodyArr=[];
		if(result !=null && result.length>0){
			for(var i in result){
				GLtbodyArr.push(result[i]);
			}
		}
		tableViewHabitationStatus(divId,GLtbodyArr,locationType,blockType)
		
	}
	
	function tableViewHabitationStatus(divId,GLtbodyArr,locationType,blockType)//aravind
	{
			 for(var k in divId){
				 var name= divId[k].id;
				 var $windowWidth = $(window).width();
				 if(GLtbodyArr !=null && GLtbodyArr.length>0){
					var tableView='';
					//if($windowWidth < 768)
					//{
						tableView+='<div class="table-responsive">';
					//}
					if(divId[k].id=="schemeId"){
						tableView+='<table class="table table-bordered table_custom" id="dataTable3'+locationType+divId[k].id+'">';
					}
					tableView+='<thead class="text-capital">';
					
					if(divId[k].id=="schemeId"){
						if(blockType == "completeOverview"){
							tableView+='<tr>';
							tableView+='<th rowspan="2">'+locationType+'</th>';
							if(GLtbodyArr[0] !=null && GLtbodyArr[0].basicList !=null && GLtbodyArr[0].basicList.length>0){
								for(var j in GLtbodyArr[0].basicList){
										if(GLtbodyArr[0].basicList[j].assetType == 'PWS' || GLtbodyArr[0].basicList[j].assetType == "CPWS"){
											tableView+='<th colspan="9">'+GLtbodyArr[0].basicList[j].assetType+'</th>';
										}
									
								}
							}
							tableView+='</tr>';
							tableView+='<tr>';
								tableView+='<th>OnGoing</th>';
								tableView+='<th>Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Completed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Commissioned</th>';
								tableView+='<th>%</th>';
								tableView+='<th>OnGoing</th>';
								tableView+='<th>Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Completed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Commissioned</th>';
								tableView+='<th>%</th>';
							tableView+='</tr>'
						}else if(blockType != "completeOverview"){
							var totalCPWSCount=0;
							var totalPWSCount=0;
							var length = (GLtbodyArr[0].subList[0].subList.length+1)*2;
							for(var i in GLtbodyArr){
								for(var j in GLtbodyArr[i].subList){
									if(GLtbodyArr[i].subList[j].assetType == "CPWS"){
										totalCPWSCount = totalCPWSCount+GLtbodyArr[i].subList[j].count;
									}else{
										totalPWSCount = totalPWSCount+GLtbodyArr[i].subList[j].count;
									}
									
									
								}
							}
							tableView+='<tr>';
								tableView+='<th rowspan="3">'+locationType+'</th>';
								for(var i in GLtbodyArr[0].subList){
									if(GLtbodyArr[0].subList[i].assetType == "CPWS"){
										//tableView+='<th colspan="7" class="colspanLen" style="display:none">'+GLtbodyArr[0].subList[i].assetType+' - '+totalCPWSCount+' (Works)</th>';
										tableView+='<th colspan="'+length+'" class="" >'+GLtbodyArr[0].subList[i].assetType+' - '+totalCPWSCount+' (Works)</th>';
									}else{
										//tableView+='<th colspan="7" class="colspanLen" style="display:none">'+GLtbodyArr[0].subList[i].assetType+' - '+totalPWSCount+' (Works)</th>';
										tableView+='<th colspan="'+length+'" class="" >'+GLtbodyArr[0].subList[i].assetType+' - '+totalPWSCount+' (Works)</th>';
										
										
									}
									
								}
							tableView+='</tr>'
							tableView+='<tr>';
								
								//tableView+='<th class="colspanLen" style="display:none">TOTAL WORKS</th>';
								tableView+='<th colspan="2" class="">TOTAL WORKS</th>';
								
								for(var i in GLtbodyArr[0].subList[0].subList){
									//tableView+='<th class="colspanLen" style="display:none">'+GLtbodyArr[0].subList[0].subList[i].name+'</th>';
									tableView+='<th colspan="2" class="">'+GLtbodyArr[0].subList[0].subList[i].name+'</th>';
									
								}
								//tableView+='<th class="colspanLen" style="display:none">TOTAL WORKS</th>';
								tableView+='<th colspan="2" class="">TOTAL WORKS</th>';
								
								for(var i in GLtbodyArr[0].subList[0].subList){
									//tableView+='<th class="colspanLen" style="display:none">'+GLtbodyArr[0].subList[0].subList[i].name+'</th>';
									tableView+='<th colspan="2" class="">'+GLtbodyArr[0].subList[0].subList[i].name+'</th>';
								}		
							tableView+='</tr>'
							tableView+='<tr>';
							
								//tableView+='<th class="colspanLen" style="display:none">Works</th>';
								
								tableView+='<th class="">Works</th>';
								
								tableView+='<th><span class="colspanLenInc'+locationType+'"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac<span>';
								tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></th>';
											
								//tableView+='<th class=""><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac</th>';
								
								for(var i in GLtbodyArr[0].subList[0].subList){
									//tableView+='<th class="colspanLen" style="display:none">Works</th>';
									
									tableView+='<th class="">Works</th>';
									
									tableView+='<th><span class="colspanLenInc'+locationType+'"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac<span>';
									tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></th>';
								
									//tableView+='<th class=""><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac</th>';
									
									
								}
								//tableView+='<th class="colspanLen" style="display:none">Works</th>';
								
								tableView+='<th class="">Works</th>';
								
								tableView+='<th><span class="colspanLenInc'+locationType+'"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac<span>';
								tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></th>';
									
								//tableView+='<th class=""><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac</th>';
								
								for(var i in GLtbodyArr[0].subList[0].subList){
									//tableView+='<th class="colspanLen" style="display:none">Works</th>';
									
									tableView+='<th class="">Works</th>';
									
									tableView+='<th><span class="colspanLenInc'+locationType+'"><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac<span>';
									tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></th>';
								
									//tableView+='<th class=""><i class="fa fa-inr m_top5" aria-hidden="true" style="font-size:12px"></i>&nbsp;in&nbsp;Lac</th>';
									
								}
							tableView+='</tr>'
						}
						
							
					}
					
					tableView+='</thead>';
					tableView+='<tbody>';
						if(divId[k].id=="schemeId"){
							for(var i in GLtbodyArr){
								if(blockType == "completeOverview"){
									tableView+='<tr>';
										tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
									
									if(GLtbodyArr[i].basicList !=null && GLtbodyArr[i].basicList.length>0){
										for(var j in GLtbodyArr[i].basicList){
										if(GLtbodyArr[i].basicList[j].assetType == 'PWS' || GLtbodyArr[i].basicList[j].assetType == "CPWS"){
												
												if(GLtbodyArr[i].basicList[j].workOngoingCount !=null && GLtbodyArr[i].basicList[j].workOngoingCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].basicList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].basicList[j].workOngoingCount+'" attr_type = "ongoing" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].workOngoingCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].workGroundedCount !=null && GLtbodyArr[i].basicList[j].workGroundedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].basicList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].basicList[j].workGroundedCount+'" attr_type = "grounded" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].workGroundedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].percentageOne !=null && GLtbodyArr[i].basicList[j].percentageOne>0){
													tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageOne.toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].workNotGroundedCount !=null && GLtbodyArr[i].basicList[j].workNotGroundedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].basicList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].basicList[j].workOngoingCount+'" attr_type = "not grounded" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].workNotGroundedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].percentageFour !=null && GLtbodyArr[i].basicList[j].percentageFour>0){
													tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageFour.toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}	
												if(GLtbodyArr[i].basicList[j].workCompletedCount !=null && GLtbodyArr[i].basicList[j].workCompletedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].basicList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].basicList[j].workOngoingCount+'" attr_type = "completed" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].workCompletedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].percentageThree !=null && GLtbodyArr[i].basicList[j].percentageThree>0){
													tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageThree.toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}	
												if(GLtbodyArr[i].basicList[j].workComissionedCount !=null && GLtbodyArr[i].basicList[j].workComissionedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].basicList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].basicList[j].workOngoingCount+'" attr_type = "Commissioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].workComissionedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].basicList[j].percentageTwo !=null && GLtbodyArr[i].basicList[j].percentageTwo>0){
													tableView+='<td ><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageTwo.toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}	
											}
										}
									}
									tableView+='</tr>';
								}else if(blockType == "exceededOverview"){
									tableView+='<tr>';
										tableView+='<td>'+GLtbodyArr[i].name+'</td>';
										for(var j in GLtbodyArr[i].subList){
											var cpwsPerc =0;
											var pswsPerc=0;
											if(GLtbodyArr[i].subList[j].assetType == "CPWS"){
												cpwsPerc = (GLtbodyArr[i].subList[j].count/totalCPWSCount*100).toFixed(2);
												if(GLtbodyArr[i].subList[j].count !=null && GLtbodyArr[i].subList[j].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="CPWS" attr_location_type="'+locationType+'" attr_filter_value="" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].count+'" attr_type = "exccedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].count+'</span><br/></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
											}else{
												
												pswsPerc = (GLtbodyArr[i].subList[j].count/totalPWSCount*100).toFixed(2);
												if(GLtbodyArr[i].subList[j].count !=null && GLtbodyArr[i].subList[j].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="PWS" attr_location_type="'+locationType+'" attr_filter_value="" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].count+'" attr_type = "exccedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].count+'</span><br/></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
											}
											if(GLtbodyArr[i].subList[j].sanctionedAmount !=null && GLtbodyArr[i].subList[j].sanctionedAmount>0){
												tableView+='<td><span class="colspanLenInc'+locationType+'">'+GLtbodyArr[i].subList[j].sanctionedAmount+'<span>';
												tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></td>';

											}else{
												tableView+='<td><span class="colspanLenInc'+locationType+'"></span> - <span class="colspanLen" style="display:none"> - </span></td>';
											}
										
											for(var k in GLtbodyArr[i].subList[j].subList){
												
												if(GLtbodyArr[i].subList[j].subList[k].count !=null && GLtbodyArr[i].subList[j].subList[k].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'" attr_location_type="'+locationType+'" attr_filter_value="'+GLtbodyArr[i].subList[j].subList[k].name+'" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].subList[k].count+'" attr_type = "exccedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].subList[k].count+'</span><br/> <small style="color:green;">'+GLtbodyArr[i].subList[j].subList[k].percentage+' %</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
												if(GLtbodyArr[i].subList[j].subList[k].sanctionedAmount !=null && GLtbodyArr[i].subList[j].subList[k].sanctionedAmount>0){
													tableView+='<td><span class="colspanLenInc'+locationType+'">'+GLtbodyArr[i].subList[j].subList[k].sanctionedAmount+'';
													tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </td>';
												}else{
													tableView+='<td><span class="colspanLenInc'+locationType+'"> - </span><span class="colspanLen" style="display:none"> - </span> - </td>';
												}
												
												
											}
										}
									tableView+='</tr>';
								}else if(blockType == "notGroundedoverView"){
									tableView+='<tr>';
										tableView+='<td>'+GLtbodyArr[i].name+'</td>';
										for(var j in GLtbodyArr[i].subList){
											var cpwsPerc =0;
											var pswsPerc=0;
											if(GLtbodyArr[i].subList[j].assetType == "CPWS"){
												cpwsPerc = (GLtbodyArr[i].subList[j].count/totalCPWSCount*100).toFixed(2);
												if(GLtbodyArr[i].subList[j].count !=null && GLtbodyArr[i].subList[j].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="CPWS" attr_location_type="'+locationType+'" attr_filter_value="" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].count+'" attr_type = "NotGroundedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].count+'</span><br/></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
											}else{
												
												pswsPerc = (GLtbodyArr[i].subList[j].count/totalPWSCount*100).toFixed(2);
												if(GLtbodyArr[i].subList[j].count !=null && GLtbodyArr[i].subList[j].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="PWS" attr_location_type="'+locationType+'" attr_filter_value="" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].count+'" attr_type = "NotGroundedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].count+'</span><br/></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
											}
											if(GLtbodyArr[i].subList[j].sanctionedAmount !=null && GLtbodyArr[i].subList[j].sanctionedAmount>0){
												tableView+='<td><span class="colspanLenInc'+locationType+'">'+GLtbodyArr[i].subList[j].sanctionedAmount+'<span>';
												tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </span></td>';

											}else{
												tableView+='<td><span class="colspanLenInc'+locationType+'"></span> - <span class="colspanLen" style="display:none"> - </span></td>';
											}
										
											for(var k in GLtbodyArr[i].subList[j].subList){
												
												if(GLtbodyArr[i].subList[j].subList[k].count !=null && GLtbodyArr[i].subList[j].subList[k].count>0){
													tableView+='<td><span  class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'" attr_location_type="'+locationType+'" attr_filter_value="'+GLtbodyArr[i].subList[j].subList[k].name+'" attr_district_val="'+GLtbodyArr[i].locationIdStr+'" attr_total_count = "'+GLtbodyArr[i].subList[j].subList[k].count+'" attr_type = "NotGroundedSchemes" attr_location_name= "'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline" >'+GLtbodyArr[i].subList[j].subList[k].count+'</span><br/> <small style="color:green;">'+GLtbodyArr[i].subList[j].subList[k].percentage+' %</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												
												if(GLtbodyArr[i].subList[j].subList[k].sanctionedAmount !=null && GLtbodyArr[i].subList[j].subList[k].sanctionedAmount>0){
													tableView+='<td><span class="colspanLenInc'+locationType+'">'+GLtbodyArr[i].subList[j].subList[k].sanctionedAmount+'';
													tableView+='<span class="colspanLen'+locationType+'" style="display:none"> - </td>';
												}else{
													tableView+='<td><span class="colspanLenInc'+locationType+'"> - </span><span class="colspanLen" style="display:none"> - </span> - </td>';
												}
												
												
											}
										}
									tableView+='</tr>';
								
								}
								
							}
						}
					tableView+='</tbody>';
					tableView+='</table>';	
					//if($windowWidth < 768)
					//{
						tableView+='</div>';
					//}
					$("#"+locationType+"BlockId"+name).html(tableView);
				if(name=="schemeId"){
					if(locationType !="state"){
						$("#dataTable3"+locationType+name).dataTable({
							"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
								"<'row'<'col-sm-12'tr>>" +
								"<'row'<'col-sm-5'i><'col-sm-7'p>>",
								"order": [ 0, 'desc' ],
								"iDisplayLength" : 15,
								"aLengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
							buttons: [
								{
									extend:    'csvHtml5',
									text:      '<i class="fa fa-file-text-o"></i>',
									titleAttr: 'CSV',
									title:	   locationType,
									filename:  locationType+name+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
								},
								{
									extend:    'pdfHtml5',
									text:      '<i class="fa fa-file-pdf-o"></i>',
									titleAttr: 'PDF',
									title:	   locationType,
									filename:  locationType+name+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
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
					
			 }else{
				 $("#"+locationType+"BlockId"+divId[k].id).html("No DATA AVAILABLE")
			 }
		}
		
	}
	
	 $(document).on("click","[role='tablist'] li a",function(){
		var id = $(this).attr('tab_id');
		var blockName = $(this).attr('attr_block_name');
		var blockId = $(this).attr('attr_block_id');
		if(blockName == "state"){
			emptyCheckState();
			if(id == "stateBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','state',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "district"){
			emptyCheckDistrict();
			if(id == "districtBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','district',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "constituency"){
			emptyCheckConstituency();
			if(id == "constituencyBlockIdschemeId"){
				//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
				getAllPrrwsDistricts("chosendistValconstituencyBlockId");
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "mandal"){
			emptyCheckMandal();
			if(id == "mandalBlockIdschemeId"){
				getAllPrrwsDistricts("chosendistValmandalBlockId");
				//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","","completeOverview");
			}
		}
	}); 
	function emptyCheckState(){
		$("#stateBlockIdhabitation").html('');
		$("#stateBlockIdperformance").html('');
		$("#alertStatusstate").html('');
		$("#stateBlockIdassestsId").html('');
		$("#stateBlockIdschemeId").html('');
		$("#stateBlockIdwaterSourceId").html('');
	}
	function emptyCheckDistrict(){
		$("#districtBlockIdhabitation").html('');
		$("#districtBlockIdperformance").html('');
		$("#alertStatusdistrict").html('');
		$("#districtBlockIdassestsId").html('');
		$("#districtBlockIdschemeId").html('');
		$("#districtBlockIdwaterSourceId").html('');
	}
	function emptyCheckConstituency(){
		$("#constituencyBlockIdhabitation").html('');
		$("#constituencyBlockIdperformance").html('');
		$("#alertStatusconstituency").html('');
		$("#constituencyBlockIdassestsId").html('');
		$("#constituencyBlockIdschemeId").html('');
		$("#constituencyBlockIdwaterSourceId").html('');
		$("#chosenconstValconstituencyBlockId").html('');
		$("#chosenconstValconstituencyBlockId").trigger("chosen:updated");
		
	}
	function emptyCheckMandal(){
		$("#mandalBlockIdhabitation").html('');
		$("#mandalBlockIdperformance").html('');
		$("#alertStatusmandal").html('');
		$("#mandalBlockIdassestsId").html('');
		$("#mandalBlockIdschemeId").html('');
		$("#mandalBlockIdwaterSourceId").html('');
		$("#chosenconstValmandalBlockId").html('');
		$("#chosenconstValmandalBlockId").trigger("chosen:updated");
		$("#chosenmandalValmandalBlockId").html('');
		$("#chosenmandalValmandalBlockId").trigger("chosen:updated");
	}
	function responsiveTabs(){
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
	}
	//mobile
	$(document).on("change","[role='tabListMobile']",function(){
		var id = $('option:selected', this).attr('tab_id');
		$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
		$("#"+id).addClass("active");
		var blockName = $('option:selected', this).attr('attr_block_name');
		var blockId = $('option:selected', this).attr('attr_block_id');
		if(blockName == "state"){
			emptyCheckState();
			 if(id == "stateBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','state',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "district"){
			emptyCheckDistrict();
			if(id == "districtBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','district',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "constituency"){
			emptyCheckConstituency();
			if(id == "constituencyBlockIdschemeId"){
				//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
				getAllPrrwsDistricts("chosendistValconstituencyBlockId");
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","","completeOverview");
			}
		}else if(blockName == "mandal"){
			emptyCheckMandal();
			if(id == "mandalBlockIdschemeId"){
				getAllPrrwsDistricts("chosendistValmandalBlockId");
				//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","","completeOverview");
			}
		}
	});
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	
	function getLocationBasedOnSelection(locationType,filterType,filterValue,districtValue,divId){
		var yearVal="";
		var financialVal =$("#financialYearId").val();
		if(financialVal != 0){
			 yearVal=financialVal;
		}
		var blockName = getSelectedType().blockNameC;
		var tabId = getSelectedType().tabIdC;
		$("#"+divId).html('');
		
		var json = {
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			locationType:locationType,
			year:yearVal,
			filterType:filterType,
			filterValue:filterValue,
			districtValue:districtValue
		}
		
		$.ajax({
			url: 'getLocationBasedOnSelection',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp){
				
				if(ajaxresp !=null && ajaxresp.length>0){
					 $("#"+divId).append('<option value="0">ALL</option>');
					for(var i in ajaxresp){
						$("#"+divId).append('<option value="'+ajaxresp[i].id+'">'+ajaxresp[i].name+' </option>');
					}
				}
				
				$("#"+divId).trigger('chosen:updated');
				}
			});
		}
	
  $("#dateRangePickerAUM").daterangepicker({
  opens: 'left',
  startDate: glStartDate,
  endDate: glEndDate,
locale: {
  format: 'DD-MM-YYYY'
},
ranges: {
    'All':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
    'Today' : [moment(), moment()],
	'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
    'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
    'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
    'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
    'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
    'This Month': [moment().startOf('month'), moment()],
    'This Year': [moment().startOf('Year'), moment()]
    }
  });
    var dates= $("#dateRangePickerAUM").val();
var pickerDates = glStartDate+' - '+glEndDate
  if(dates == pickerDates)
  {
    $("#dateRangePickerAUM").val('All');
  }
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
glStartDate = picker.startDate.format('DD-MM-YYYY')
glEndDate = picker.endDate.format('DD-MM-YYYY')
if(picker.chosenLabel == 'All')
{
  $("#dateRangePickerAUM").val('All');
}
$("#financialYearId").val(0);
$("#financialYearId").trigger('chosen:updated');
	
	setTimeout(function(){  onloadCalls(); }, 1000);
   
    
  });
  function getAllFiniancialYears()
	{
		$("#financialYearId").html('');
	
	var json = {
	}
	$.ajax({                
		type:'POST',    
		url: 'getAllFiniancialYears',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#financialYearId").append("<option value='0'>All Financial Years</option>");
		if(result != null && result.length >0){
			for(var i in result){
				var value = result[i].financialYear.split('-');
				$("#financialYearId").append("<option value="+value[0]+">"+result[i].financialYear+"</option>");
				
			}
			//$("#financialYearId").val('2017');
		}
		
		$("#financialYearId").chosen();
		$("#financialYearId").trigger('chosen:updated');
			onloadCalls();	
		});
   }
   //Constituency
$(document).on("change","#chosendistValconstituencyBlockId",function(){
	var blockName = getSelectedType().blockNameC;
	var tabId = getSelectedType().tabIdC;
	var distId = $(this).val();
	/* if(tabId == "constituencyBlockIdjalavani"){
		distId = distId;
	}else{
		distId = distId <= 9?"0"+distId:distId;
	} */
	var searchLevelId = 3;
	if(blockName == "constituency"){
		if(distId == 0){
			$("#chosenconstValconstituencyBlockId").html("");
			$("#chosenconstValconstituencyBlockId").trigger("chosen:updated");
			if(tabId == "constituencyBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","","completeOverview");
			}
			
		}else{
			 if(tabId == "constituencyBlockIdschemeId"){
				getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
				 //getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"district",distId,"","completeOverview");
				
			}
		}
	}
});
$(document).on("change","#chosenconstValconstituencyBlockId",function(){
	var	constId = $(this).val();
	var distId = $("#chosendistValconstituencyBlockId").val();
	/* if(tabId == "constituencyBlockIdjalavani"){
		var distId = $("#chosendistValconstituencyBlockId").val();
	}else{
		var distId = $("#chosendistValconstituencyBlockId").val()<9?"0"+$("#chosendistValconstituencyBlockId").val():$("#chosendistValconstituencyBlockId").val();
	} */
	
	
	var blockName = getSelectedType().blockNameC;
	var tabId = getSelectedType().tabIdC;
	var searchLevelConstId = 4;
	var searchLevelDistId = 3;
	if(blockName == "constituency"){
		if(tabId == "constituencyBlockIdschemeId"){
			if(constId == 0){
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"district",distId,"","completeOverview");
			}else{
				getSchemeWiseWorkDetails('table','constituency',blocksArr,"constituency",constId,"","completeOverview");
			}
			
		}
	}
});
//Mandal
$(document).on("change","#chosendistValmandalBlockId",function(){
	var blockNameM = getSelectedTypeMandal().blockNameM;
	var tabIdM = getSelectedTypeMandal().tabIdM;
	var distId = $(this).val();
	/* if(tabIdM == "mandalBlockIdjalavani"){
		distId = distId;
	}else{
		distId = distId <= 9?"0"+distId:distId;
	} */
	var searchLevelDistId = 3;
	if(blockNameM == "mandal"){
		if(distId == 0){
			$("#chosenconstValmandalBlockId").html("");
			$("#chosenconstValmandalBlockId").trigger("chosen:updated");
			$("#chosenmandalValmandalBlockId").html("");
			$("#chosenmandalValmandalBlockId").trigger("chosen:updated");
			 if(tabIdM == "mandalBlockIdschemeId"){
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","","completeOverview");
			}
		}else{
			 if(tabIdM == "mandalBlockIdschemeId"){
				getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
				// getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
				 getSchemeWiseWorkDetails('table','mandal',blocksArr,"district",distId,"","completeOverview");
				
			}
		}
	}
});

$(document).on("change","#chosenconstValmandalBlockId",function(){
	var	constId = $(this).val();
	var blockNameM = getSelectedTypeMandal().blockNameM;
	var tabIdM = getSelectedTypeMandal().tabIdM;
	var distId = $("#chosendistValmandalBlockId").val();
	/* if(tabIdM == "mandalBlockIdjalavani"){
		var distId = $("#chosendistValmandalBlockId").val();
	}else{
		var distId = $("#chosendistValmandalBlockId").val()<9?"0"+$("#chosendistValmandalBlockId").val():$("#chosendistValmandalBlockId").val();
	} */
	var searchLevelConstId = 4;
	var searchLevelDistId = 3;
	if(blockNameM == "mandal"){
		if(tabIdM == "mandalBlockIdschemeId"){
			getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
			//getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
			if(constId == 0){
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"district",distId,"","completeOverview");
			}else{
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"constituency",constId,"","completeOverview");
			}
			
		}
	}
});
$(document).on("change","#chosenmandalValmandalBlockId",function(){
	var	mandalId = $(this).val();
	var blockNameM = getSelectedTypeMandal().blockNameM;
	var tabIdM = getSelectedTypeMandal().tabIdM;
		var constId = $("#chosenconstValmandalBlockId").val();
		mandalId=mandalId;
		var distId = $("#chosendistValmandalBlockId").val();
	/* if(tabIdM == "mandalBlockIdjalavani"){
		var constId = $("#chosenconstValmandalBlockId").val();
		mandalId=mandalId;
		var distId = $("#chosendistValmandalBlockId").val();
	}else{
		var constId = $("#chosenconstValmandalBlockId").val()<9?"0"+$("#chosenconstValmandalBlockId").val():$("#chosenconstValmandalBlockId").val();
		mandalId = mandalId <= 9?"0"+mandalId:mandalId;
		var distId = $("#chosendistValmandalBlockId").val()<9?"0"+$("#chosendistValmandalBlockId").val():$("#chosendistValmandalBlockId").val();
	} */
	var searchLevelConstId = 4;
	var searchLevelDistId = 3;
	var searchLevelManId = 5;
	if(blockNameM == "mandal"){
		if(tabIdM == "mandalBlockIdschemeId"){
			if(mandalId == 0){
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"constituency",constId,"","completeOverview");
			}else{
				getSchemeWiseWorkDetails('table','mandal',blocksArr,"mandal",mandalId,distId,"completeOverview");
			}
			
		}
	}
});
$(document).on("change","#financialYearId",function(){
	var yearId = $(this).val();
	glStartDate="";
	glEndDate="";
	if(yearId == 0){
		glStartDate="01-01-1900";
		glEndDate="01-01-2050";
	}
	onloadCalls();
});
$(document).on("click",".getDetailsCls",function(){
	$("#modalTable").html('');
	
	var locationType = $(this).attr("attr_location_type");
	var levelId = $(this).attr("attr_level_id");
	var levelName=$(this).attr("attr_level_name");
	var parentLocId=$(this).attr("attr_parent_locationId");
	$("#modalDivId").modal('show');
	$("#modalHeadingId").html(levelName+" Stresseed Habitations");
	
	getStressedHabitationsInfoByLocationType(locationType,levelId,levelName,parentLocId);//on click ? call
	
});
function buildgetStressedHabitationsInfoByLocationType(result,levelName,locationType){
	
	if(result.statusList !=null && result.statusList.length>0){
		var str='';
		var totalStressCount=0;
		var totalAllHabsCount=0;
		str+='<table class="table table-bordered">';
							str+='<thead>';
							str+='<tr>';
							str+='<th rowspan="2"></th>';
							for(var i in result.statusList){
								totalStressCount =totalStressCount+result.statusList[i].stressedCount;
								totalAllHabsCount =totalAllHabsCount+result.statusList[i].count;
									str+='<th colspan="2">'+result.statusList[i].status+'</th>';
									
							}
							str+='<th colspan="2">TOTAL</th>';
							str+='</tr>';
							str+='<tr>';
							for(var i in result.statusList){
									str+='<th>Stressed Habs</th>';
									str+='<th>ALL Habs</th>';
									
							}
							str+='<th>Stressed Habs</th>';
							str+='<th>ALL Habs</th>';
							str+='</tr>';
							
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>';
							str+='<td>TOTAL</td>';
							for(var i in result.statusList){
								if(result.statusList[i].stressedCount !=null && result.statusList[i].stressedCount>0){
									str+='<td>'+result.statusList[i].stressedCount+' <small style="color:#0FBE08">'+result.statusList[i].percentage+' %</small></td>';
								}else{
									str+='<td> - </td>';
								}
								if(result.statusList[i].count !=null && result.statusList[i].count>0){
									str+='<td>'+result.statusList[i].count+'</td>';
								}else{
									str+='<td> - </td>';
								}
								
							}
							if(totalStressCount !=null && totalStressCount>0){
								str+='<td>'+totalStressCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							if(totalAllHabsCount !=null && totalAllHabsCount>0){
								str+='<td>'+totalAllHabsCount+'</td>';
							}else{
								str+='<td> - </td>';
							}
							str+='</tr>';
						str+='</tbody>';
				
		str+='</table>';
		$("#modalTable").html(str);
	}else{
		$("#modalTable").html("No Data Available");
	}
	
}




function getAllPrrwsDistricts(divId){

	$("#"+divId).html('');
	var json = {
	}
	$.ajax({                
		type:'POST',    
		url: 'getAllPrrwsDistricts',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		if(ajaxresp !=null && ajaxresp.length>0){
			 $("#"+divId).append('<option value="0">ALL</option>');
			for(var i in ajaxresp){
				$("#"+divId).append('<option value="'+ajaxresp[i].key+'">'+ajaxresp[i].value+' </option>');
			}
		}
				
		$("#"+divId).trigger('chosen:updated');
	});
}

function getConstituenciesForDistrict(id,divId){
	$("#"+divId).html('');
	var json = {
		id:id
	}
	$.ajax({                
		type:'POST',    
		url: 'getConstituenciesForDistrict',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		if(ajaxresp !=null && ajaxresp.length>0){
			 $("#"+divId).append('<option value="0">ALL</option>');
			for(var i in ajaxresp){
				$("#"+divId).append('<option value="'+ajaxresp[i].key+'">'+ajaxresp[i].value+' </option>');
			}
		}
				
		$("#"+divId).trigger('chosen:updated');
	});
}

function getTehsilsForConstituency(id,divId){
	var json = {
		id:id
	}
	$.ajax({                
		type:'POST',    
		url: 'getTehsilsForConstituency',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		if(ajaxresp !=null && ajaxresp.length>0){
			 $("#"+divId).append('<option value="0">ALL</option>');
			for(var i in ajaxresp){
				$("#"+divId).append('<option value="'+ajaxresp[i].key+'">'+ajaxresp[i].value+' </option>');
			}
		}
				
		$("#"+divId).trigger('chosen:updated');
	});
}

$(document).on("click",".schemsClickView",function(){
	
	var status = $(this).attr("attr_status");
	var totalCount=$(this).attr("attr_total_count");
	var workStatus=$(this).attr("attr_type");
	var locationValue = $(this).attr("attr_filter_value");
	var locationType=$(this).attr("attr_location_type");
	var districtVal=$(this).attr("attr_district_val");
	var locationName=$(this).attr("attr_location_name");
	
	if(workStatus == "exccedSchemes"){
		$("#modalHablitationDivId").modal('show');
		$("#modalSchemsTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;"+"("+locationValue+")&nbsp;&nbsp;Overview</h4>");
		getOnClickExceedWorkDetails(status,locationType,locationValue,districtVal);
	}else if(workStatus == "NotGroundedSchemes"){
		$("#modalHablitationDivId").modal('show');
		$("#modalSchemsTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;"+"("+locationValue+")&nbsp;&nbsp;Overview</h4>");
		getOnClickNotGroubnWorkDetails(status,locationType,locationValue,districtVal);
	}else{
		$("#modalHablitationDivId").modal('show');
		$("#modalSchemsExceedTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;"+"("+workStatus+")&nbsp;&nbsp;Overview</h4>");
		getOnclickWorkSchemsDetails(status,workStatus,totalCount,locationValue,locationType,
		districtVal);
	}
	
	
	
});

//schems ON CLICK
function getOnclickWorkSchemsDetails(status,workStatus,totalCount,locationValue,locationType,districtVal){
	
	$("#modalSchemsTable").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var filterValue ='';
	var filterType = '';	
	if(locationType == "state"){
		filterValue="";
		filterType="";
	}else{
		filterValue = locationValue;
		filterType = locationType;
	}
	var districtValStr="";	
	if(locationType == "mandal"){
		districtValStr = districtVal;
		
	}
	
	var json = {
			year:yearVal,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			workStatus:workStatus,
			districtValue:districtValStr,
			filterType:filterType,
			filterValue:filterValue,
			assetType:status
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getOnclickWorkDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOnclickWorkSchemsDetails(result,status,workStatus,totalCount);
		}else{
			
			$("#modalSchemsTable").html('No Data Available');
		}
		
	});
}

//schems build
function buildOnclickWorkSchemsDetails(result,status,workStatus,totalCount){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems">';
		tableView+='<thead>';
		tableView+='<tr>';
				tableView+='<th>Work ID</th>';
				tableView+='<th>Work NAME</th>';
				tableView+='<th>DISTRICT</th>';
				tableView+='<th>CONSTITUENCY</th>';
				tableView+='<th>MANDAL</th>';
				tableView+='<th>HABITATIONS NAME</th>';
				tableView+='<th>SANCTIONED AMOUNT</th>';
				if(workStatus !="not grounded"){
				tableView+='<th>TARGET DATE</th>';
				tableView+='<th>GROUNDED DATE</th>';
				tableView+='<th>COMPLETION DATE</th>';
				}else{
					tableView+='<th>ADMIN DATE</th>';
					tableView+='<th>TARGET DATE</th>';
				}
				
			tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].workId+'</td>';
					tableView+='<td>'+result[i].workName+'</td>';
					tableView+='<td>'+result[i].districtName+'</td>';
					tableView+='<td>'+result[i].constituencyName+'</td>';
					tableView+='<td>'+result[i].mandalName+'</td>';
					tableView+='<td>'+result[i].habitationName+'</td>';
					if(typeof result[i].sacntionedAmount === undefined ||typeof result[i].sacntionedAmount =="undefined" || result[i].sacntionedAmount =='' ){
						tableView+='<td>-</td>';
					}else{
						tableView+='<td>'+result[i].sacntionedAmount+'</td>';
					}
					if(workStatus !="not grounded"){
						if(typeof result[i].targetDate === undefined ||typeof result[i].targetDate =="undefined" || result[i].targetDate =='' ){
							tableView+='<td>-</td>';
						}else{
							tableView+='<td>'+result[i].targetDate+'</td>';
						}if(typeof result[i].groundingDate === undefined ||typeof result[i].groundingDate =="undefined" || result[i].groundingDate =='' ){
							tableView+='<td>-</td>';
						}else{
							tableView+='<td>'+result[i].groundingDate+'</td>';
						}if(typeof result[i].completionDate === undefined ||typeof result[i].completionDate =="undefined" || result[i].completionDate =='' ){
							tableView+='<td>-</td>';
						}else{
							tableView+='<td>'+result[i].completionDate+'</td>';
						}
						
					}else{
						if(typeof result[i].adminDate === undefined ||typeof result[i].adminDate =="undefined" || result[i].adminDate =='' ){
							tableView+='<td>-</td>';
						}else{
							tableView+='<td>'+result[i].adminDate+'</td>';
						}
						if(typeof result[i].targetDate === undefined ||typeof result[i].targetDate =="undefined" || result[i].targetDate =='' ){
							tableView+='<td>-</td>';
						}else{
						tableView+='<td>'+result[i].targetDate+'</td>';
						}
					}
					
					
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#modalSchemsTable").html(tableView);
	$("#dataTableSchems").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 15,
		"aLengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}

function overviewData(divId){
	var collapse = '';
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+'" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-black">';
						collapse+='<div class="panel-heading" role="tab" id="heading'+divId+'">';
							collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+'" href="#collapse'+divId.replace(/\s+/g, '')+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+'">';
							collapse+='<h4 class="panel-title text-capital">'+divId+' overview</h4>';
							collapse+='</a>';
						collapse+='</div>';
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+'">';
							collapse+='<div class="panel-body">';
								collapse+='<div id="swatchBharatOvervw'+divId.replace(/\s+/g, '')+'"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#overViewBlockId").html(collapse);
	
	getIHHLOverviewData("overview")
		
	}
	
function builOverViewBlock(result,divId){
	var $windowWidth = $(window).width();
	if(result.subList2 !=null && result.subList2.length>0){
		var str1="";
	str1+='<div class="table-responsive">';
		str1+='<table class="table table-bordered m_top10" >';
			str1+='<tbody>';
			str1+='<tr>';
			for(var i in result.subList2){
					str1+='<td>';
						str1+='<div class="col-sm-12">';
						if(result.subList2[i].type == "District"){
							str1+='<h4 ><strong>Total Districts : '+result.subList2[i].total+'</strong></h4>';
						}else if(result.subList2[i].type == "Constituencies"){
							str1+='<h4 ><strong>Total Constituencies : '+result.subList2[i].total+'</strong></h4>';
						}else if(result.subList2[i].type == "Mandal"){
							str1+='<h4 ><strong>Total Mandal : '+result.subList2[i].total+'</strong></h4>';
						}else if(result.subList2[i].type == "Panchayat"){
							str1+='<h4 ><strong>Total Panchayat : '+result.subList2[i].total+'</strong></h4>';
						}
							
						str1+='</div>';
						str1+='<div class="col-sm-12 m_top10">';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.subList2[i].red != null && result.subList2[i].red > 0){
									if(result.subList2[i].type == "District"){
										str1+='<p class="media-body" >Districts in Red : '+result.subList2[i].red+'</p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p class="media-body" >Constituencies in Red : '+result.subList2[i].red+'</p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p class="media-body" >Mandal in Red : '+result.subList2[i].red+'</p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p class="media-body" >Panchayat in Red : '+result.subList2[i].red+'</p>';
									}
									
								}else{
									if(result.subList2[i].type == "District"){
										str1+='<p>Districts in Red : - </p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p>Constituencies in Red : - </p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p>Mandal in Red : - </p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p>Panchayat in Red : - </p>';
									}
									
								}
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
								if(result.subList2[i].orange != null && result.subList2[i].orange > 0){
									if(result.subList2[i].type == "District"){
										str1+='<p class="media-body"  >Districts in Orange : '+result.subList2[i].orange+'</p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p class="media-body"  >Constituencies in Orange : '+result.subList2[i].orange+'</p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p class="media-body" >Mandal in Orange : '+result.subList2[i].orange+'</p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p class="media-body" >Panchayat in Orange : '+result.subList2[i].orange+'</p>';
									}
								}else{
									if(result.subList2[i].type == "District"){
										str1+='<p>Districts in Red : - </p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p>Constituencies in Red : - </p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p>Mandal in Red : - </p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p>Panchayat in Red : - </p>';
									}
								}
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.subList2[i].green != null && result.subList2[i].green > 0){
									if(result.subList2[i].type == "District"){
										str1+='<p class="media-body" >Districts in Green : '+result.subList2[i].green+'</p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p class="media-body" >Constituencies in Green : '+result.subList2[i].green+'</p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p class="media-body" >Mandal in Green : '+result.subList2[i].green+'</p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p class="media-body" >Panchayat in Green : '+result.subList2[i].green+'</p>';
									}
								}else{
									if(result.subList2[i].type == "District"){
										str1+='<p>Districts in Red : - </p>';
									}else if(result.subList2[i].type == "Constituencies"){
										str1+='<p>Constituencies in Red : - </p>';
									}else if(result.subList2[i].type == "Mandal"){
										str1+='<p>Mandal in Red : - </p>';
									}else if(result.subList2[i].type == "Panchayat"){
										str1+='<p>Panchayat in Red : - </p>';
									}
								}
							str1+='</div>';
						str1+='</div>';
					str1+='</td>';
			}
			str1+='</tr>';
			str1+='</tbody>';
		str1+='</table>';
	str1+='</div>';	
	$("#swatchBharatOvervw"+divId.replace(/\s+/g, '')).html(str1);	
		
	}
}

function buildGraph(result)
{
	var colorsArr=['#EE6CA9','#C61379'];
	var cateArr = [];
	var pwsArr = [];
	var cpwsArr = [];
	var totalWorksPWS = 0;
	var OnGoingExceededWorks =[];
	var CompletedExceededWorks =[];
	var CommissionedExceededWorks =[];
	var totalWorksCPWS = 0;
	var totalAmountPWS = 0;
	var totalAmountCPWS = 0;
	
	var OnGoingExceededWorks='';
	var CompletedExceededWorks='';
	var CommissionedExceededWorks='';
	
	for(var i in result[0].subList)
	{
		/* cateArr.push(result[0].subList[i].assetType)
		pwsArr.push(result.subList[i].count)
		cpwsArr.push(result.completedList[i].cpwsCount) 
		totalWorksPWS = totalWorksPWS + result.subList[i].count;
		totalWorksCPWS = totalWorksCPWS + result.subList[i].count;
		totalAmountPWS = totalAmountPWS + result.completedList[i].pwsAmount;
		totalAmountCPWS = totalAmountCPWS + result.completedList[i].cpwsAmount;*/
		
		if(result[0].subList[i].assetType == 'PWS'){
			totalWorksPWS = totalWorksPWS + result[0].subList[i].count;
			
		}else if(result[0].subList[i].assetType == 'CPWS'){
			totalWorksCPWS = totalWorksCPWS + result[0].subList[i].count;
			
		}
		
		for( var j in result[0].subList[i].subList){
			cateArr.push(result[0].subList[i].subList[j].name);
			if(result[0].subList[i].assetType == 'PWS'){
				pwsArr.push({"y":result[0].subList[i].subList[j].count,"extra":""+result[0].subList[i].subList[j].ongoingPWSExceededCount+"-"+result[0].subList[i].subList[j].completedPWSExceededCount+"-"+result[0].subList[i].subList[j].commissionedPWSExceededCount});
			}else if(result[0].subList[i].assetType == 'CPWS'){
				cpwsArr.push({"y":result[0].subList[i].subList[j].count,"extra":""+result[0].subList[i].subList[j].ongoingPWSExceededCount+"-"+result[0].subList[i].subList[j].completedPWSExceededCount+"-"+result[0].subList[i].subList[j].commissionedPWSExceededCount});
			}
		}
	}
	$("#ExceededTargetDetailsTotal").highcharts({
		chart: {
			type: 'column'
			
		},
		title: {
			text: null
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories:["Total"]
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b><br/>' +
					this.series.name + ': ' + this.y + '<br/>'
					//+'Total: ' + this.point.stackTotal;
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
		series: [{
			name: 'PWS',
			data: [totalWorksPWS],
			stack: 'PWS',
			color:'#EE6CA9'
		}, {
			name: 'CPWS',
			data: [totalWorksCPWS],
			stack: 'CPWS',
			color:'#C61379'
		}]
	});
	$("#ExceededTargetDetails").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: null
		},
		xAxis: {
			categories:cateArr
		},
		yAxis: {
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function () {
				var value = (this.point.extra).split("-");
				return '<b>' + this.x + '</b><br/>' +
					this.series.name + ': ' + this.y + '<br/>' +
					//'Total: ' + this.point.stackTotal + '<br/>' +
					
					'OnGoingExceededWorks :' +value[0]+ '<br/>' +
					'CompletedExceededWorks :' +value[1]+ '<br/>' +
					'CommissionedExceededWorks :' +value[2]+ '';
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
		series: [{
			name: 'PWS',
			data: pwsArr,
			stack: 'PWS',
			color:'#EE6CA9'
		}, {
			name: 'CPWS',
			data: cpwsArr,
			stack: 'CPWS',
			color:'#C61379'
			}]
		});
	}
$(document).on("click","[role='tabCummulative'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var blockName = $(this).closest("ul").attr("attr_level_type");
	var blockType = $(this).attr("attr_type");
	var statusType='';
	$('.exceedWorkTypeCls').each(function(i, obj){
		 if($(this).is(':checked')){
			statusType = $(this).val();
		 }
	});
if(blockName == "state"){
	if(blockType == "completeOverview"){
		getSchemeWiseWorkDetails('table','state',blocksArr,"","","",blockType);
	}else if(blockType == "exceededOverview"){// here using global globalStateLevelExceededTargetWorks arr no need sent ajax call in state level table building we have responce object onLoad
		buildTableForHabitationCoverage(globalStateLevelExceededTargetWorks,'state',blocksArr,'habitations',blockType);
	}else if(blockType == "notGroundedoverView"){
		getNotGroundedWorkDetailsLocationWise('table','state',blocksArr,"","","",blockType);
	}
	
}else if(blockName == "district"){
	if(blockType == "completeOverview"){
		getSchemeWiseWorkDetails('table','district',blocksArr,"","","",blockType);
	}else if(blockType == "exceededOverview"){
		getExceedWorkDetailsLocationWise('table','district',blocksArr,"","","",blockType,statusType)
	}else if(blockType == "notGroundedoverView"){
		getNotGroundedWorkDetailsLocationWise('table','district',blocksArr,"","","",blockType);
	}
	
}else if(blockName == "constituency"){
	if(blockType == "completeOverview"){
		$("#distValconstituencyBlockId").show();
		$("#constValconstituencyBlockId").show();
		getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","",blockType);
	}else if(blockType == "exceededOverview"){
		$("#distValconstituencyBlockId").hide();
		$("#constValconstituencyBlockId").hide();
		getExceedWorkDetailsLocationWise('table','constituency',blocksArr,"","","",blockType,statusType)
	}else if(blockType == "notGroundedoverView"){
		$("#distValconstituencyBlockId").hide();
		$("#constValconstituencyBlockId").hide();
		getNotGroundedWorkDetailsLocationWise('table','constituency',blocksArr,"","","",blockType);
	}
	
}else if(blockName == "mandal"){
	if(blockType == "completeOverview"){
		$("#distValmandalBlockId").show();
		$("#constValmandalBlockId").show();
		$("#mandalValmandalBlockId").show();
		getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","",blockType);	
	}else if(blockType == "exceededOverview"){
		$("#distValmandalBlockId").hide();
		$("#constValmandalBlockId").hide();
		$("#mandalValmandalBlockId").hide();
		getExceedWorkDetailsLocationWise('table','mandal',blocksArr,"","","",blockType)
	}else if(blockType == "notGroundedoverView"){
		$("#distValmandalBlockId").hide();
		$("#constValmandalBlockId").hide();
		$("#mandalValmandalBlockId").hide();
		getNotGroundedWorkDetailsLocationWise('table','mandal',blocksArr,"","","",blockType);
	}
		
	}
});
function getExceedWorkDetailsLocationWise(type,locationType,divId,filterType,filterValue,districtValue,blockType,statusType){
	if(locationType == 'state'){
		$("#ExceededTargetDetailsTotal").html(spinner);
		$("#ExceededTargetDetails").html(spinner);
	}else{
		for(var k in divId){
			$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
		}
	}
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
			
 	var json = {
			year:yearVal,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			districtValue:"",
			filterType:"",
			filterValue:"",
			locationType:locationType, 
			assetTypeList:['CPWS','PWS'],
			"status" : statusType,
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getExceedWorkDetailsLocationWise2',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		$("[attr_level_type='state'][role='tabCummulative'] li:nth-child(2)").trigger("click");
	 	if(ajaxresp !=null && ajaxresp.length>0){
			if(locationType == 'state'){
				globalStateLevelExceededTargetWorks=ajaxresp;
				buildGraph(ajaxresp);
			}else{
				buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations',blockType);
			}
		}else{
			$("#ExceededTargetDetailsTotal,#ExceededTargetDetails").html("No Data Available");
			for(var k in divId){
				$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
			}
		}
	});
}
function getOnClickExceedWorkDetails(assetType,locationType,exceededDuration,locationValue,statusType){
	$("#modalSchemsExceedTable").html(spinner);
	$('.exceedWorkTypeCls').each(function(i, obj){
		 if($(this).is(':checked')){
			statusType = $(this).val();
		 }
	});
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
 	var json = {
			"assetType":assetType,
			"fromDateStr":glStartDate,
			"toDateStr":glEndDate,
			"locationType":locationType,
			"exceededDuration":exceededDuration,
			"locationValue":locationValue,
			 "status" : statusType,
			 "year": yearVal
			 
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getOnClickExceedWorkDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	 	if(result !=null && result.length>0){
			buildOnclickWorkSchemsExccedDetails(result,exceededDuration);
		}else{
			
			$("#modalSchemsExceedTable").html('No Data Available');
		}
	});
}

function buildOnclickWorkSchemsExccedDetails(result,exceededDuration){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems1">';
		tableView+='<thead>';
		tableView+='<tr>';
				tableView+='<th class="text-capital">Work CODE</th>';
				tableView+='<th class="text-capital">Work NAME</th>';
				tableView+='<th>PROGRAM NAME</th>';
				tableView+='<th>DISTRICT</th>';
				tableView+='<th>CONSTITUENCY</th>';
				tableView+='<th>MANDAL</th>';
				tableView+='<th>HABITATIONS NAME</th>';
				tableView+='<th>SANCTIONED AMOUNT</th>';
				tableView+='<th>GROUNDED DATE</th>';
				tableView+='<th>TARGET DATE</th>';
				tableView+='<th>COMPLETION DATE</th>';
				if(exceededDuration !=='0 Days'){
					tableView+='<th class="text-capital">Exceeded Days</th>';
				}	
				
				tableView+='<th class="text-capital">Work Status</th>';
			tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].wrokIdStr+'</td>';
					tableView+='<td>'+result[i].wrokName+'</td>';
					tableView+='<td>'+result[i].programName+'</td>';
					tableView+='<td>'+result[i].districtName+'</td>';
					tableView+='<td>'+result[i].constituencyName+'</td>';
					tableView+='<td>'+result[i].mandalName+'</td>';
					tableView+='<td>'+result[i].habitationName+'</td>';
					tableView+='<td>'+result[i].sanctionedAmount+'</td>';
					tableView+='<td>'+result[i].groundedDate+'</td>'
					tableView+='<td>'+result[i].targetDate+'</td>';
					if(result[i].workStatus=='Grounded'){
						tableView+='<td>-</td>';
					}else{
						tableView+='<td>'+result[i].completionDate+'</td>';
					}
					if(typeof result[i].noOfDays === undefined || typeof result[i].noOfDays == "undefined" || result[i].noOfDays == null || result[i].noOfDays == 0){
						tableView+='<td> - </td>';
					}else{
						if(exceededDuration !=='0 Days'){
							tableView+='<td>'+result[i].noOfDays+'</td>';
						}
					}
					tableView+='<td>'+result[i].workStatus+'</td>';
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#modalSchemsExceedTable").html(tableView);
	$("#dataTableSchems1").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 15,
		"aLengthMenu": [[15, 30, 50, -1], [15,30,50, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});

//radio button 
$(document).on("click",".exceedWorkTypeCls",function(e){
	var statusType = $(this).val();
	if(statusType == ""){
		$(".headingExceedId").html("All Exceeded Works")
	}else{
		$(".headingExceedId").html("OnGoing Exceeded Works")
	}	
	getExceedWorkDetailsLocationWise("",'state',"","","","","",statusType);
	getExceedWorkDetailsLocationWise("table",'state',"","","","","",statusType);
	getExceedWorkDetailsLocationWise("table",'district',"","","","","",statusType);
	getExceedWorkDetailsLocationWise("table",'constituency',"","","","","",statusType);
	getExceedWorkDetailsLocationWise("table",'mandal',"","","","","",statusType);
});
$(document).on("click",".checkboxTypeCls",function(e){
	var blocksArr = ['state','district','constituency','mandal']
	if($(this).is(":checked")){
		$(".checkboxTypeCls").prop("checked",true);
		for(var i in blocksArr)
		{
			$(".colspanLenInc"+blocksArr[i]).css("display","block")
			$(".colspanLen"+blocksArr[i]).css("display","none")				
		}
	}else{
		$(".checkboxTypeCls").prop("checked",false);
		for(var i in blocksArr)
		{
			$(".colspanLenInc"+blocksArr[i]).css("display","none")
			$(".colspanLen"+blocksArr[i]).css("display","block")
		}
	}
});

function getNotGroundedWorkDetailsLocationWise(type,locationType,divId,filterType,filterValue,districtValue,blockType){
	if(type == 'graph'){
		$("#ExceededNotGroundedTotal").html(spinner);
		$("#NotGroundedTargetDetails").html(spinner);
	}else{
		for(var k in divId){
			$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
		}
	}
	
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
			
 	var json = {
			year:yearVal,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			districtValue:"",
			filterType:"",
			filterValue:"",
			locationType:locationType, 
			
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getNotGroundedWorkDetailsLocationWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		
	 	if(ajaxresp !=null && ajaxresp.length>0){
			if(type=='graph'){
				buildnotGroundedGraph(ajaxresp);
			}else{
				buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations',blockType);
			}
		}else{
			$("#ExceededNotGroundedTotal,#NotGroundedTargetDetails").html("No Data Available");
			//$("#"+locationType+"BlockId").html("No Data Available");
			for(var k in divId){
				$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
			}
		}
	});
}

function buildnotGroundedGraph(result)
{
	var colorsArr=['#EE6CA9','#C61379'];
	var cateArr = [];
	var pwsArr = [];
	var cpwsArr = [];
	var totalWorksPWS = 0;
	var OnGoingExceededWorks =[];
	var CompletedExceededWorks =[];
	var CommissionedExceededWorks =[];
	var totalWorksCPWS = 0;
	var totalAmountPWS = 0;
	var totalAmountCPWS = 0;
	
	var OnGoingExceededWorks='';
	var CompletedExceededWorks='';
	var CommissionedExceededWorks='';
	
	for(var i in result[0].subList)
	{
		
		
		if(result[0].subList[i].assetType == 'PWS'){
			totalWorksPWS = totalWorksPWS + result[0].subList[i].count;
			
		}else if(result[0].subList[i].assetType == 'CPWS'){
			totalWorksCPWS = totalWorksCPWS + result[0].subList[i].count;
			
		}
		
		for( var j in result[0].subList[i].subList){
			cateArr.push(result[0].subList[i].subList[j].name);
			if(result[0].subList[i].assetType == 'PWS'){
				pwsArr.push({"y":result[0].subList[i].subList[j].count,"extra":""+result[0].subList[i].subList[j].ongoingPWSExceededCount+"-"+result[0].subList[i].subList[j].completedPWSExceededCount+"-"+result[0].subList[i].subList[j].commissionedPWSExceededCount});
			}else if(result[0].subList[i].assetType == 'CPWS'){
				cpwsArr.push({"y":result[0].subList[i].subList[j].count,"extra":""+result[0].subList[i].subList[j].ongoingPWSExceededCount+"-"+result[0].subList[i].subList[j].completedPWSExceededCount+"-"+result[0].subList[i].subList[j].commissionedPWSExceededCount});
			}
		}
	}
	$("#ExceededNotGroundedTotal").highcharts({
		chart: {
			type: 'column'
			
		},
		title: {
			text: null
		},
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories:["Total"]
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function () {
				return '<b>' + this.x + '</b><br/>' +
					this.series.name + ': ' + this.y + '<br/>'
					//+'Total: ' + this.point.stackTotal;
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
		series: [{
			name: 'PWS',
			data: [totalWorksPWS],
			stack: 'PWS',
			color:'#EE6CA9'
		}, {
			name: 'CPWS',
			data: [totalWorksCPWS],
			stack: 'CPWS',
			color:'#C61379'
		}]
	});
	$("#NotGroundedTargetDetails").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: null
		},
		xAxis: {
			categories:cateArr
		},
		yAxis: {
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		tooltip: {
			formatter: function () {
				var value = (this.point.extra).split("-");
				return '<b>' + this.x + '</b><br/>' +
					this.series.name + ': ' + this.y + '<br/>' ;
			}
		},
		plotOptions: {
			column: {
				stacking: 'normal'
			}
		},
		series: [{
			name: 'PWS',
			data: pwsArr,
			stack: 'PWS',
			color:'#EE6CA9'
		}, {
			name: 'CPWS',
			data: cpwsArr,
			stack: 'CPWS',
			color:'#C61379'
			}]
		});
	
}

function getOnClickNotGroubnWorkDetails(assetType,locationType,exceededDuration,locationValue,statusType){
	$("#modalSchemsExceedTable").html(spinner);
	$('.exceedWorkTypeCls').each(function(i, obj){
		 if($(this).is(':checked')){
			statusType = $(this).val();
		 }
	});
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
 	var json = {
			"assetType":assetType,
			"fromDateStr":glStartDate,
			"toDateStr":glEndDate,
			"locationType":locationType,
			"exceededDuration":exceededDuration,
			"locationValue":locationValue,
			 "status" : statusType,
			 "year": yearVal
			 
			}
	
	$.ajax({                
		type:'POST',    
		url: 'getOnClickNotGroundedWorkDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	 	if(result !=null && result.length>0){
			buildOnClickNotGroubnWorkDetails(result,exceededDuration);
		}else{
			
			$("#modalSchemsExceedTable").html('No Data Available');
		}
	});
}

function buildOnClickNotGroubnWorkDetails(result,exceededDuration){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems1">';
		tableView+='<thead>';
		tableView+='<tr>';
				tableView+='<th class="text-capital">Work CODE</th>';
				tableView+='<th class="text-capital">Work NAME</th>';
				tableView+='<th>PROGRAM NAME</th>';
				tableView+='<th>DISTRICT</th>';
				tableView+='<th>CONSTITUENCY</th>';
				tableView+='<th>MANDAL</th>';
				tableView+='<th>HABITATIONS NAME</th>';
				tableView+='<th>SANCTIONED AMOUNT</th>';
				if(exceededDuration !=='0 Days'){
					tableView+='<th class="text-capital">Exceeded Days</th>';
				}	
				
				tableView+='<th class="text-capital">Work Status</th>';
			tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].wrokIdStr+'</td>';
					tableView+='<td>'+result[i].wrokName+'</td>';
					tableView+='<td>'+result[i].programName+'</td>';
					tableView+='<td>'+result[i].districtName+'</td>';
					tableView+='<td>'+result[i].constituencyName+'</td>';
					tableView+='<td>'+result[i].mandalName+'</td>';
					tableView+='<td>'+result[i].habitationName+'</td>';
					tableView+='<td>'+result[i].sanctionedAmount+'</td>';
					
					if(typeof result[i].noOfDays === undefined || typeof result[i].noOfDays == "undefined" || result[i].noOfDays == null || result[i].noOfDays == 0){
						tableView+='<td> - </td>';
					}else{
						if(exceededDuration !=='0 Days'){
							tableView+='<td>'+result[i].noOfDays+'</td>';
						}
					}
					tableView+='<td>'+result[i].workStatus+'</td>';
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#modalSchemsExceedTable").html(tableView);
	$("#dataTableSchems1").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 15,
		"aLengthMenu": [[15, 30, 50, -1], [15,30,50, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Rural Water Supply',
				filename:  'Rural Water Supply'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}
$(".collapseClick").click(function(){
		getNotGroundedWorkDetailsLocationWise("graph",'state',"","","","","","not grounded");
});