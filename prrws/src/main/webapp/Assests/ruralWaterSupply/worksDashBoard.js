	//Angular Start  getAlertsOfCategoryByStatusWise()
var currentYear="";
if(moment().format('MM').toString < "04"){
	currentYear = moment().year()
}else{
	currentYear = moment().year()+1;
}
	var glStartDate = "01-04-2014";
	var	glEndDate = "01-04-"+currentYear;
	var blocksArr = [{name:'Coverage Status Of Habitation',id:'habitation',img:'coverage_status.png'},
	{name:'Work Schemes',id:'schemeId',img:'schemes.png'}];

	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	var levelNamesArr=[{name:'state',id:'2'},{name:'district',id:'3'},{name:'constituency',id:'4'},{name:'mandal',id:'5'}];
	var levelWiseSBArr = ['state','district','constituencies','mandal','panchayat'];
	var globalStateLevelExceededTargetWorks='';
	getAllFiniancialYears();
	getAllSchemes();
	function getAllSchemes(){
	$("#schemeDivId").html('');
	var json = {
	}
	$.ajax({                
		type:'POST',    
		url: 'getRwsProgramsCodeAndName',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length >0){
			for(var i in result){
				$("#schemeDivId").append("<option value="+result[i].programCode+" selected>"+result[i].programName+"</option>");
				
			}
			$('#schemeDivId').multiselect("destroy");
			$('#schemeDivId').multiselect({
				enableFiltering: true,
				includeSelectAllOption: true,
				selectAllText: 'All',
				maxHeight: 300,
				buttonWidth: '100%',
				dropDown: true,
				selectAllName: true,
				allSelectedText: 'All Schemes',
				onChange: function() {
					 onloadCalls();
				},onSelectAll: function() {
					 onloadCalls();
				}
			});
		}
	});
}
	function onloadCalls(){
		globalStateLevelExceededTargetWorks='';
		getSchemeWiseWorkDetails('graph','state',"","","","");
		tabBlocks('stateBlockId','state');
		tabBlocks('districtBlockId','district');
		tabBlocks('constituencyBlockId','constituency');
		tabBlocks('mandalBlockId','mandal');
		getExceedWorkDetailsLocationWise("",'state',"","","","","","onGoing");
		getNotGroundedWorkDetailsLocationWise("graph",'state',"","","","","","not grounded");
		gettAllRwsWorksByScheme();
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
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
			var schemeId="";
			if(schemeVal[i].length ==1){
				schemeId="0"+schemeVal[i]
			}else{
				schemeId=schemeVal[i];
			}
			schemeValArr.push(schemeId);
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
		districtValue:districtValue,
		schemeIdStr:schemeValArr
	}
		  
	$.ajax({
		url: 'getLocationWiseSchemeWiseWorkDetails',
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
					//buildSchemeWiseWorkDetails(ajaxresp);
					buildSchemeWiseWorkDetailsforState(ajaxresp);
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
		  
function buildSchemeWiseWorkDetailsforState(result){
	var PWSAdminSanctionCount ="";
	var CPWSAdminSanctionCount="";
	var pwsDataArr =[];var cpwsDataArr =[];
	for(var i in result[0].subList){
		if(result[0].subList[i].assetType == "PWS"){
			PWSAdminSanctionCount= result[0].subList[i].adminSanctionedCount;
			
			pwsDataArr.push({"name": 'Admin Sanctioned',"y": PWSAdminSanctionCount,color:'#5fc24f'});
			pwsDataArr.push({"name":  'Techincal Sancationed',y: result[0].subList[i].technicalSanctionedCount,color:'#418CF0'});
			pwsDataArr.push({"name":  'Entrusted',y: result[0].subList[i].entrustedCount,color:'#FFBF00'});
			pwsDataArr.push({"name":  'Grounded',y: result[0].subList[i].groundedCount,color:'#ACFA58'});
			pwsDataArr.push({"name":  'Not Grounded',y: result[0].subList[i].notgroundedCount,color:'#DF013A'});
			pwsDataArr.push({"name":  'UnderProcess',y: result[0].subList[i].undrProcessCount,color:'#FA5858'});
			pwsDataArr.push({"name": 'Completed',y:result[0].subList[i].completedCount,color:'#009999'});
			pwsDataArr.push({"name": 'Commissioned',y:result[0].subList[i].commissionedCount,color:'#009988'});
			
		}else if(result[0].subList[i].assetType == "CPWS"){
			CPWSAdminSanctionCount= result[0].subList[i].adminSanctionedCount;
			
			cpwsDataArr.push({"name": 'Admin Sanctioned',"y": CPWSAdminSanctionCount,color:'#5fc24f'});
			cpwsDataArr.push({"name":  'Techincal Sancationed',y: result[0].subList[i].technicalSanctionedCount,color:'#418CF0'});
			cpwsDataArr.push({"name":  'Entrusted',y: result[0].subList[i].entrustedCount,color:'#FFBF00'});
			cpwsDataArr.push({"name":  'Grounded',y: result[0].subList[i].groundedCount,color:'#ACFA58'});
			cpwsDataArr.push({"name":  'Not Grounded',y: result[0].subList[i].notgroundedCount,color:'#DF013A'});
			cpwsDataArr.push({"name":  'UnderProcess',y: result[0].subList[i].undrProcessCount,color:'#FA5858'});
			cpwsDataArr.push({"name": 'Completed',y:result[0].subList[i].completedCount,color:'#009999'});
			cpwsDataArr.push({"name": 'Commissioned',y:result[0].subList[i].commissionedCount,color:'#009988'});
			
		}
	}
	
	$("#habitationWorksPWS").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
	   
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			type: 'category'
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}

		},
		legend: {
			enabled: false
		},
		
		plotOptions: {
				column: {
					//colorByPoint: true
					dataLabels: {
						useHTML:true,
						enabled: true,
						color: '#000',
						style: {
							fontWeight: '',
							fontSize:'9px'
						},
						align: 'center',
						formatter: function() {
							if(this.point.name !="Admin Sanctioned"){
								if(this.y == 0){
									return null;
								}else{
									var name=this.point.name;
									var pcnt = (this.y / PWSAdminSanctionCount) * 100;
									return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
								}
							}else{
								return '<span>'+this.y+'</span>';
							}
						}
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
			data: pwsDataArr
		}]
	});
	
	$("#habitationWorksCPWS").highcharts({
		chart: {
			type: 'column'
		},
		title: {
			text: ''
		},
	   
		xAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			type: 'category'
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}

		},
		legend: {
			enabled: false
		},
		
		plotOptions: {
				column: {
					//colorByPoint: true
					dataLabels: {
						useHTML:true,
						enabled: true,
						color: '#000',
						style: {
							fontWeight: '',
							fontSize:'9px'
						},
						align: 'center',
						formatter: function() {
							if(this.point.name !="Admin Sanctioned"){
								if(this.y == 0){
									return null;
								}else{
									var name=this.point.name;
									var pcnt = (this.y / CPWSAdminSanctionCount) * 100;
									return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
								}
							}else{
								return '<span>'+this.y+'</span>';
							}
						}
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
			data: cpwsDataArr
		}]
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
					tabBlock+='<ul class=" list-inline switch-btn" role="tabCummulative" attr_level_type="'+blockName+'">';
						tabBlock+='<li attr_type="completeOverview" class="f_14">OverAll Works</li>';
						tabBlock+='<li class="active ActiveStateCls f_14" attr_type="exceededOverview"><span class="headingExceedId"> </span></li>';
						tabBlock+='<li attr_type="notGroundedoverView" class="f_14">NotGrounded Works</li>';
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
			
			
			tabBlock+='<div class="tab-content">';
				if(blockId == 'constituencyBlockId'){
						tabBlock+='<div class="row">';
						//	tabBlock+='<div class="col-sm-3"><div id="distVal'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constVal'+blockId+'"></div></div>';
						tabBlock+='</div>';
				}else if(blockId == 'mandalBlockId'){
						tabBlock+='<div class="row">';
								///tabBlock+='<div class="col-sm-3"><div id="distVal'+blockId+'"></div></div>';
								//tabBlock+='<div class="col-sm-3"><div id="constVal'+blockId+'"></div></div>';
								tabBlock+='<div class="col-sm-3"><div id="mandalVal'+blockId+'"></div></div>';
						tabBlock+='</div>';
				}
				for(var i in blocksArr)
				{
					if(i == 1)
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
						tableView+='<div class="table-responsive">';
					if(divId[k].id=="schemeId"){
						tableView+='<table class="table table-bordered table_custom" id="dataTable3'+locationType+divId[k].id+'">';
					}
					tableView+='<thead class="text-capital">';
					
					if(divId[k].id=="schemeId"){
						if(blockType == "completeOverview"){
							tableView+='<tr>';
							tableView+='<th rowspan="2">'+locationType+'</th>';
							if(GLtbodyArr[0] !=null && GLtbodyArr[0].subList !=null && GLtbodyArr[0].subList.length>0){
								for(var j in GLtbodyArr[0].subList){
										if(GLtbodyArr[0].subList[j].assetType == 'PWS' || GLtbodyArr[0].subList[j].assetType == "CPWS"){
											tableView+='<th colspan="15">'+GLtbodyArr[0].subList[j].assetType+'</th>';
										}
									
								}
							}
							tableView+='</tr>';
							tableView+='<tr>';
								tableView+='<th>admin Sanctioned</th>';
								tableView+='<th>Techincal Sancationed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>entrusted</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>underProcess</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Completed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Commissioned</th>';
								tableView+='<th>%</th>';
								tableView+='<th>admin Sanctioned</th>';
								tableView+='<th>Techincal Sancationed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>entrusted</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>underProcess</th>';
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
									
									if(GLtbodyArr[i].subList !=null && GLtbodyArr[i].subList.length>0){
										for(var j in GLtbodyArr[i].subList){
										if(GLtbodyArr[i].subList[j].assetType == 'PWS' || GLtbodyArr[i].subList[j].assetType == "CPWS"){
												
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].subList[j].adminSanctionedCount+'" attr_type = "adminSanctioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].adminSanctionedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].technicalSanctionedCount !=null && GLtbodyArr[i].subList[j].technicalSanctionedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].subList[j].technicalSanctionedCount+'" attr_type = "adminSanctioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].technicalSanctionedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].technicalSanctionedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].entrustedCount !=null && GLtbodyArr[i].subList[j].entrustedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].subList[j].entrustedCount+'" attr_type = "adminSanctioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].entrustedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].entrustedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].groundedCount !=null && GLtbodyArr[i].subList[j].groundedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].subList[j].groundedCount+'" attr_type = "grounded" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].groundedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].groundedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].notgroundedCount !=null && GLtbodyArr[i].subList[j].notgroundedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].subList[j].adminSanctionedCount+'" attr_type = "not grounded" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].notgroundedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].notgroundedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].undrProcessCount !=null && GLtbodyArr[i].subList[j].undrProcessCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'"attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'"attr_total_count = "'+GLtbodyArr[i].subList[j].undrProcessCount+'" attr_type = "adminSanctioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].undrProcessCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].undrProcessCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}												
												if(GLtbodyArr[i].subList[j].completedCount !=null && GLtbodyArr[i].subList[j].completedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].subList[j].adminSanctionedCount+'" attr_type = "completed" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].completedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].completedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}	
												if(GLtbodyArr[i].subList[j].commissionedCount !=null && GLtbodyArr[i].subList[j].commissionedCount>0){
													tableView+='<td class="schemsClickView"  attr_status="'+GLtbodyArr[i].subList[j].assetType+'" attr_location_type="'+locationType+'"attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].districtId+'" attr_total_count = "'+GLtbodyArr[i].subList[j].adminSanctionedCount+'" attr_type = "Commissioned" attr_location_name= "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].subList[j].commissionedCount+'</td>';
												}else{
													tableView+='<td> - </td>';
												}
												if(GLtbodyArr[i].subList[j].adminSanctionedCount !=null && GLtbodyArr[i].subList[j].adminSanctionedCount>0){
													tableView+='<td><small style="color:#0FBE08">'+(parseFloat(GLtbodyArr[i].subList[j].commissionedCount/GLtbodyArr[i].subList[j].adminSanctionedCount)*100).toFixed(1)+'</small></td>';
												}else{
													tableView+='<td> - </td>';
												}	
											}
										}
									}else{
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
											tableView+='<td> - </td>';tableView+='<td> - </td>';tableView+='<td> - </td>';
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
		//$("#stateBlockIdhabitation").html('');
		$("#stateBlockIdschemeId").html('');
	}
	function emptyCheckDistrict(){
		//$("#districtBlockIdhabitation").html('');
		$("#districtBlockIdschemeId").html('');
	}
	function emptyCheckConstituency(){
		//$("#constituencyBlockIdhabitation").html('');
		$("#constituencyBlockIdschemeId").html('');
		$("#chosenconstValconstituencyBlockId").html('');
		$("#chosenconstValconstituencyBlockId").trigger("chosen:updated");
		
	}
	function emptyCheckMandal(){
		//$("#mandalBlockIdhabitation").html('');
		$("#mandalBlockIdschemeId").html('');
		$("#chosenmandalValmandalBlockId").html('');
		$("#chosenmandalValmandalBlockId").trigger("chosen:updated");
	}
	
	
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
		'2014 To Till Now':["01-04-2014", moment().format("DD-MM-YYYY")],
		'Today' : [moment(), moment()],
		'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'This Month': [moment().startOf('month'), moment()],
		'This Year': [moment().startOf('Year'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
		'Last 3 Year': [moment().subtract(3, 'Year'), moment()]	
    }
  });
    var dates= $("#dateRangePickerAUM").val();
var pickerDates = glStartDate+' - '+glEndDate
  if(dates == pickerDates)
  {
    $("#dateRangePickerAUM").val('2014 To Till Now');
  }
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
glStartDate = picker.startDate.format('DD-MM-YYYY')
glEndDate = picker.endDate.format('DD-MM-YYYY')
if(picker.chosenLabel == '2014 To Till Now')
{
  $("#dateRangePickerAUM").val('2014 To Till Now');
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
		$("#financialYearId").append("<option value='2003'>2014-"+currentYear+"</option></option>");
		if(result != null && result.length >0){
			for(var i in result){
				var value = result[i].financialYear.split('-');
				$("#financialYearId").append("<option value="+value[0]+">"+result[i].financialYear+"</option>");
				
			}
			$("#financialYearId").val('2003');
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
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
				var schemeId="";
				if(schemeVal[i].length ==1){
					schemeId="0"+schemeVal[i]
				}else{
					schemeId=schemeVal[i];
				}
				schemeValArr.push(schemeId);
			}
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
		assetType:status,
		"schemeIdStr":schemeValArr
	}
	
	$.ajax({                
		type:'POST',    
		url: 'getLocattionWiseOnclickWorkDetails',
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
	}else if(blockType == "exceededOverview"){
		getExceedWorkDetailsLocationWise('table','state',blocksArr,"","","",blockType,statusType)
		//buildTableForHabitationCoverage(globalStateLevelExceededTargetWorks,'state',blocksArr,'habitations',blockType);
	}else if(blockType == "notGroundedoverView"){
		getNotGroundedWorkDetailsLocationWise('table','state',blocksArr,"","","",blockType);
	}
	
}else if(blockName == "district"){
	if(blockType == "completeOverview"){
		getSchemeWiseWorkDetails('table','district',blocksArr,"","","",blockType);
	}else if(blockType == "exceededOverview"){
		getExceedWorkDetailsLocationWise('table','district',blocksArr,"","","",blockType,statusType);
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
		getExceedWorkDetailsLocationWise('table','mandal',blocksArr,"","","",blockType,statusType)
	}else if(blockType == "notGroundedoverView"){
		$("#distValmandalBlockId").hide();
		$("#constValmandalBlockId").hide();
		$("#mandalValmandalBlockId").hide();
		getNotGroundedWorkDetailsLocationWise('table','mandal',blocksArr,"","","",blockType);
	}
		
	}
});
function getExceedWorkDetailsLocationWise(type,locationType,divId,filterType,filterValue,districtValue,blockType,statusType){
	if(type != 'table'){
		$("#ExceededTargetDetailsTotal").html(spinner);
		$("#ExceededTargetDetails").html(spinner);
	}else{
		for(var k in divId){
			$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
		}
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
				var schemeId="";
				if(schemeVal[i].length ==1){
					schemeId="0"+schemeVal[i]
				}else{
					schemeId=schemeVal[i];
				}
				schemeValArr.push(schemeId);
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
			"schemeIdStr":schemeValArr
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
	 	if(ajaxresp !=null && ajaxresp.length>0){
			if(type != 'table'){
				//globalStateLevelExceededTargetWorks=ajaxresp;
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
	var districtId="";
	var locationVal ="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
		
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
			var schemeId="";
			if(schemeVal[i].length ==1){
				schemeId="0"+schemeVal[i]
			}else{
				schemeId=schemeVal[i];
			}
			schemeValArr.push(schemeId);
		}
	}
	if(locationType=='mandal'){
		districtId=locationValue.substr(0,2);
		 locationVal=locationValue.substr(2,3);
	}else{
		locationVal=locationValue;
	}
 	var json = {
		"assetType":assetType,
		"fromDateStr":glStartDate,
		"toDateStr":glEndDate,
		"locationType":locationType,
		"exceededDuration":exceededDuration,
		"locationIdStr":locationVal,
		"status" : statusType,
		"year": yearVal,
		"districtValue":districtId,
		"schemeIdStr":schemeValArr
		 
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
	tableView+='<table class="table table-bordered" id="dataTableSchems13">';
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
	$("#dataTableSchems13").dataTable({
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
	//getExceedWorkDetailsLocationWise(type,locationType,divId,filterType,filterValue,districtValue,blockType,statusType)
	getExceedWorkDetailsLocationWise("",'state',blocksArr,"","","",'exceededOverview',statusType);
	getExceedWorkDetailsLocationWise("table",'state',blocksArr,"","","",'exceededOverview',statusType);
	getExceedWorkDetailsLocationWise("table",'district',blocksArr,"","","",'exceededOverview',statusType);
	getExceedWorkDetailsLocationWise("table",'constituency',blocksArr,"","","",'exceededOverview',statusType);
	getExceedWorkDetailsLocationWise("table",'mandal',blocksArr,"","","",'exceededOverview',statusType);
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
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
			var schemeId="";
			if(schemeVal[i].length ==1){
				schemeId="0"+schemeVal[i]
			}else{
				schemeId=schemeVal[i];
			}
			schemeValArr.push(schemeId);
		}
	}
 	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
		districtValue:"",
		filterType:"",
		filterValue:"",
		locationType:locationType, 
		schemeIdStr:schemeValArr
		
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
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		for(var i in schemeVal){
			var schemeId="";
			if(schemeVal[i].length ==1){
				schemeId="0"+schemeVal[i]
			}else{
				schemeId=schemeVal[i];
			}
			schemeValArr.push(schemeId);
		}
	}
 	var json = {
		"assetType":assetType,
		"fromDateStr":glStartDate,
		"toDateStr":glEndDate,
		"locationType":locationType,
		"exceededDuration":exceededDuration,
		"locationIdStr":locationValue,
		"status" : statusType,
		"year": yearVal,
		"schemeIdStr":schemeValArr
		 
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
	tableView+='<table class="table table-bordered" id="dataTableSchems12">';
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
	$("#dataTableSchems12").dataTable({
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

function gettAllRwsWorksByScheme(){
	
	$("#encSchemesTableDivId").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
	}
	
	$.ajax({                
		type:'POST',    
		url: 'getAllWorksByScheme',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildgettAllRwsWorksBySchemeDetails(result);
		}else{
			
			$("#encSchemesTableDivId").html('No Data Available');
		}
		
	});
}

function buildgettAllRwsWorksBySchemeDetails(result){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems1">';
		tableView+='<thead>';
		tableView+='<tr>';
			tableView+='<th>GRANT&nbsp;NAME</th>';
			tableView+='<th>ADMIN SANCTIONED</th>';
			tableView+='<th>TECHNICALLY SANCTIONED</th>';
			tableView+='<th>ENTRUSTED</th>';
			tableView+='<th>NOT GROUNDED</th>';
			tableView+='<th>GROUNDED</th>';
			tableView+='<th>UNDER PROCESS</th>';
			tableView+='<th>COMPLETED</th>';
			tableView+='<th>COMISSIONED</th>';
				
		tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].locationName+'</td>';
					tableView+='<td>'+result[i].adminSanctionedCount+'</td>';
					tableView+='<td>'+result[i].technicalSanctionedCount+'</td>';
					tableView+='<td>'+result[i].entrustedCount+'</td>';
					tableView+='<td>'+result[i].notgroundedCount+'</td>';
					tableView+='<td>'+result[i].groundedCount+'</td>';
					tableView+='<td>'+result[i].undrProcessCount+'</td>';
					tableView+='<td>'+result[i].completedCount+'</td>';
					tableView+='<td>'+result[i].commissionedCount+'</td>';
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#encSchemesTableDivId").html(tableView);
	$("#dataTableSchems1").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 5,
		"aLengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Rws Works',
				filename:  'Rws Works'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			}
		]
	});
	
}
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});