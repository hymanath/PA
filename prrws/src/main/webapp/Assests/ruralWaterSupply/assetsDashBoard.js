 //Angular Start  getAlertsOfCategoryByStatusWise()
		var glStartDate = moment().subtract(40, 'years').startOf('year').format("DD-MM-YYYY");
		var glEndDate = moment().add(10, 'years').endOf('year').format("DD-MM-YYYY");
		var globalStatusObj={"QA":"#494949","PC":"#FC5049","FC":"#14BAAD","Ground":"#14BAAD","Surface":"#FC5049","SAFE":"#14BAAD","UN-SAFE":"#FC5049",
		"SINGAL VILLAGE":"#14BAAD","MULTI VILLAGE":"#FC5049","physicalTestCount":"#14BAAD","bacterialTestCount":"#FC5049",
		"Completely Satisfied":"#0FBE08","Not Satisfied":"#FF0909","Partially Satisfied":"#FFBA00","SATISFIED":"#0FBE08","PARTIALLY SATISFIED":"#FFBA00","NOT SATISFIED":"#FF0909","TARGET":"#FC5049","COMPLETED":"#14BAAD"}
		var blocksArr = [{name:'Coverage Status Of Habitation',id:'habitation',img:'coverage_status.png'},
						 {name:'Key Performance',id:'performance',img:'key_performance.png'},
						 {name:'<p><span><img src="Assests/icons/alert_status.png"/> Jalavani </span></p>',id:'jalavani',img:'alert_status.png'},
						 /*{ name:'Plan Of Action for Stressed Habitations <br><small>Water Budget has to be prepared for all habitations</small>',id:'planAction',img:'plan_action.png'  }*/
						 {name:'Assests',id:'assestsId',img:'assets.png'},{name:'Water Source',id:'waterSourceId',img:'works.png'},{name:'Work Schemes',id:'schemeId',img:'schemes.png'},];
		var alertStatusBlockArr = [{name:'Alert Status Jalavani',id:'alertStatus'},{name:'Drinking Water Satisfaction Levels',id:'drinking'}];
		var blockArrClickArr = [{name:'HabitationClick',id:'modalHablitationTable'},{name:'KpiClick',id:'modalKpiTable'},{name:'JalavaniClick',id:'modalAlertTable'},{name:'IvrStatusClick',id:'modalIvrStatusTable'},{name:'AssetsClick',id:'modalAssetsTable'},{name:'WaterSourceClick',id:'modalWaterSourceTable'},{name:'WorkSchemsClick',id:'modalWorkSchemsTable'}];
		var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
		var levelNamesArr=[{name:'state',id:'2'},{name:'district',id:'3'},{name:'constituency',id:'4'},{name:'mandal',id:'5'}];
		var levelWiseSBArr = ['state','district','constituencies','mandal','panchayat'];
		//var overViewArr = ['IHHL'];
		getAllFiniancialYears();
		function onloadCalls(){
			getAssetInfoBetweenDates('graph','state',"","","","");
			getAllPrrwsDistricts("chosendistValconstituencyBlockId");
			getAllPrrwsDistricts("chosendistValmandalBlockId");
			tabBlocks('stateBlockId','state');
			tabBlocks('districtBlockId','district');
			tabBlocks('constituencyBlockId','constituency');
			tabBlocks('mandalBlockId','mandal');
			responsiveTabs();
		}
		/*$(document).on('cut copy paste', function (e) {
			e.preventDefault();
		});*/
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
		function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title)
		{
			
			'use strict';
			$('#'+id).highcharts({
				 colors: colors,
				chart: type,
				title: title,
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
		function getAssetInfoBetweenDates(type,locationType,divId,filterType,filterValue,districtValue){//ara1
			if(type =="graph"){
				$("#assets").html(spinner);
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
				filterType:filterType,
				filterValue:filterValue,
				districtValue:districtValue,
				locationType:locationType
			}
			$.ajax({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(result){
					if(type == "graph"){
						buildAssetInfoBetweenDates(result);
					}else{
						if(result !=null && result.length>0){
							buildTableForHabitationCoverage(result,locationType,divId,'habitations');
						}else{
							for(var k in divId){
								$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
							}
						}
						
					}
				}
			});
		}
		
	function buildAssetInfoBetweenDates(result){
			var ajaxresp = null;
		if(result != null && result.length > 0){
			if(result[0].basicList != null && result[0].basicList.length > 0){
			  ajaxresp = result[0].basicList;
			}
			if(ajaxresp !=null && ajaxresp.length>0){
				var dataArr = [];
				var totalCount=0;
				var statusNamesArr=[];
					for(var i in ajaxresp)
					{
						var tempArr = [];
						statusNamesArr.push(ajaxresp[i].assetType);
						tempArr.push(parseInt(ajaxresp[i].count));
						dataArr.push(tempArr);
						totalCount=totalCount+parseInt(ajaxresp[i].count);
					  
					}
					var colors = ['#14BBAE'];
					var id = 'assets';
					var type = {
						type: 'column',
						backgroundColor:'transparent'
					};
					var legend = {
						enabled: false
					};
					var title = {
						text: ''
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
						categories: statusNamesArr
					};
					var plotOptions ={ column: {
							colorByPoint: false
						}};
					var tooltip = {
						useHTML:true,
						formatter: function () {
							var pcnt = (this.y / totalCount) * 100;
							return '<b>' + this.x + '</b><br/>' +
								this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
						}
					};

					var data = [{
						name: '',
						data: dataArr,

						dataLabels: {
							enabled: true,
							color: '#000',
							align: 'center',
							formatter: function() {
								var pcnt = (this.y / totalCount) * 100;
								return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
							}
						}
					}];
					highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			}
		}else{
			$("#assets").html("No Data Available")
		}
	}
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
			
				tabBlock+='<div class="row">';
					tabBlock+='<div class="col-sm-12 overviewCls">';
						tabBlock+='<div>';
							
							tabBlock+='<ul class="nav nav-tabs nav-tabs-custom custom_value'+blockName+'" role="tablist">';
								for(var i in blocksArr)
								{
									if(i == 3)
									{
										if(blocksArr[i].id == "jalavani"){
											tabBlock+='<li class="active" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><span>'+blocksArr[i].name+'</span></a></li>';
										}else{
											tabBlock+='<li class="active" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
										}
										
									}else{
										if(blocksArr[i].id == "jalavani"){
											tabBlock+='<li tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><span>'+blocksArr[i].name+'</span></a></li>';
										}else{
											tabBlock+='<li tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
										}
										
									}
									
								}
							tabBlock+='</ul>';
						tabBlock+='</div>';
					tabBlock+='</div>';
				tabBlock+='</div>';
			
			tabBlock+='<div class="panel-body">';
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
						if(i == 3)
						{
							 if(blocksArr[i].id == "jalavani"){
								 tabBlock+='<div >';
									 tabBlock+='<div role="tabpanel" class="tab-pane active" id="alertStatus'+blockName+'"></div>';
									 tabBlock+='<div role="tabpanel" class="tab-pane active" id="drinking'+blockName+'"></div>';
								 tabBlock+='</div>';
							 }else{
								 tabBlock+='<div role="tabpanel" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';
							 }
						}else{
							 if(blocksArr[i].id == "jalavani"){
								tabBlock+='<div >';
									tabBlock+='<div  role="tabpanel" class="tab-pane" id="alertStatus'+blockName+'"></div>';
									tabBlock+='<div  role="tabpanel" class="tab-pane" id="drinking'+blockName+'"></div>';
								tabBlock+='</div>';
							}else{
								tabBlock+='<div role="tabpanel" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
							} 
						}
					}
				tabBlock+='</div>';
			tabBlock+='</div>';
		tabBlock+='</div>';
		tabBlock+='</div>';
		$("#"+blockId).html(tabBlock);
		$(".overviewCls").hide();
		/* for(var i in blocksArr)
		{
			if(blocksArr[i].id == "habitation"){ */
				getAssetInfoBetweenDates('table','state',blocksArr,"","","");
				getAssetInfoBetweenDates('table','district',blocksArr,"","","");
				getAssetInfoBetweenDates('table','constituency',blocksArr,"","","");
				getAssetInfoBetweenDates('table','mandal',blocksArr,"","","");
			//}
		//}
		
		if(blockId == 'constituencyBlockId'){
				selectBox('distVal'+blockId+'')
				selectBox('constVal'+blockId+'')
		}else if(blockId == 'mandalBlockId'){
			selectBox('distVal'+blockId+'')
			selectBox('constVal'+blockId+'')
			selectBox('mandalVal'+blockId+'')
		}
			
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
		function buildTableForHabitationCoverage(result,locationType,divId,type){
			if(type =="alertStatus"){
				var GLtbodyAlertArr=[];
				if(result !=null && result.length>0){
					for(var i in result){
						GLtbodyAlertArr.push(result[i]);
					}
				}
				tableViewAlertStatus(divId,GLtbodyAlertArr,locationType)
			
			}else if(type =="drinking"){
				var GLtbodyWaterArr=[];
				if(result !=null && result.length>0){
					for(var i in result){
						GLtbodyWaterArr.push(result[i]);
					}
				}
				tableViewWaterStatus(divId,GLtbodyWaterArr,locationType)
			
			}else{
				var GLtbodyArr=[];
				if(result !=null && result.length>0){
					for(var i in result){
						GLtbodyArr.push(result[i]);
					}
				}
				tableViewHabitationStatus(divId,GLtbodyArr,locationType)
			}
		}
		
		function tableViewAlertStatus(divId,GLtbodyAlertArr,locationType){
			
				var $windowWidth = $(window).width();
				var tableView='';
				tableView+='<h5 style="padding:5px"><span class="chartTitleAlign"><img src="Assests/icons/alert_status.png"> <span style="margin-left:5px;">Alert Status Jalavani</span></span></h5>';
				if($windowWidth < 768)
				{
					tableView+='<div class="table-responsive">';
				}
					tableView+='<table class="table table-bordered dataTableAlert'+locationType+'">';
					tableView+='<thead class="text-capital">';
						tableView+='<tr>'; 
							tableView+='<th>'+locationType+'</th>';
							tableView+='<th>Notified</th>';
							tableView+='<th>%</th>';
							tableView+='<th>Praposal</th>';
							tableView+='<th>%</th>';
							tableView+='<th>Action in Progress</th>';
							tableView+='<th>%</th>';
							tableView+='<th>Completed & Closed</th>';
							tableView+='<th>%</th>';
							tableView+='<th>Others</th>';
							tableView+='<th>%</th>';
							tableView+='<th>Total</th>';
						tableView+='</tr>'; 
					tableView+='</thead>';
					tableView+='<tbody>';
						for(var i in GLtbodyAlertArr){
							tableView+='<tr>'; 
							var CompleteClosedCount=0;var othersCount=0;var totalCount=0;var notifiedCount=0;var proposalCount=0;var actionInProgress=0;	
							var completeClosed=0;var others=0;
							var notifiedPerc=0;var proposalPerc=0;var actionPerc=0;var completeClosedPerc=0;var othersPerc=0;
							
							if(locationType == "state"){
								tableView+='<td>Andhra Pradesh</td>';
							}else{
								tableView+='<td >'+GLtbodyAlertArr[i].locationName+'</td>';
							}	
							
							for(var j in GLtbodyAlertArr[i].statusList){
								if(GLtbodyAlertArr[i].statusList[j].id==2){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										notifiedCount =GLtbodyAlertArr[i].statusList[j].count;
										
									}
									
								}else if(GLtbodyAlertArr[i].statusList[j].id==13){
									if(GLtbodyAlertArr[i].statusList[j].count !=null &&GLtbodyAlertArr[i].statusList[j].count>0){
										proposalCount =GLtbodyAlertArr[i].statusList[j].count
										locationValue =GLtbodyAlertArr[i].statusList[j].locationId;
									}
								}else if(GLtbodyAlertArr[i].statusList[j].id==3){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										actionInProgress =GLtbodyAlertArr[i].statusList[j].count;
										locationValue =GLtbodyAlertArr[i].statusList[j].locationId;
									}
								}else if(GLtbodyAlertArr[i].statusList[j].id==4 ||  GLtbodyAlertArr[i].statusList[j].id == 12){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										CompleteClosedCount =CompleteClosedCount+GLtbodyAlertArr[i].statusList[j].count;
										completeClosed =CompleteClosedCount;
										locationValue =GLtbodyAlertArr[i].statusList[j].locationId;
									}
								}else{
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										 othersCount =othersCount+GLtbodyAlertArr[i].statusList[j].count;
										others =othersCount;
										locationValue =GLtbodyAlertArr[i].statusList[j].locationId;
									}
								}
							}
							totalCount =notifiedCount+proposalCount+actionInProgress+completeClosed+others;
							notifiedPerc = (notifiedCount/totalCount*100).toFixed(2);
							proposalPerc = (proposalCount/totalCount*100).toFixed(2);
							actionPerc = (actionInProgress/totalCount*100).toFixed(2);
							completeClosedPerc = (completeClosed/totalCount*100).toFixed(2);
							othersPerc = (others/totalCount*100).toFixed(2);
							
							if(notifiedCount >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="2" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_total_count="'+notifiedCount+'" attr_status="notified" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+notifiedCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+notifiedPerc+' </small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(proposalCount >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="13" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_total_count="'+proposalCount+'" attr_status="proposal" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+proposalCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+proposalPerc+' </small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(actionInProgress >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="3" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_total_count="'+actionInProgress+'"  attr_status="Action In Progess" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+actionInProgress+'</td>';
								tableView+='<td><small style="color:#0FBE08">'+actionPerc+' </small></td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(CompleteClosedCount >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="4,12" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_status="Completed & Closed" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" attr_total_count="'+CompleteClosedCount+'" style="cursor:pointer;text-decoration:underline">'+CompleteClosedCount+'</td>';
								tableView+='<td><small style="color:#0FBE08">'+completeClosedPerc+'</small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(othersCount >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="5,6,7,8,9,10,11,14" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_total_count="'+othersCount+'" attr_status="Others" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+othersCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+othersPerc+' %</small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(totalCount >0){
								tableView+='<td class="alertStatusViewCls" attr_status_id="" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].locationId+'" attr_total_count="'+totalCount+'" attr_status="Total" attr_location_name="'+GLtbodyAlertArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+totalCount+'</td>';
							}else{
								tableView+='<td> - </td>';
							}
							tableView+='</tr>'; 
						}
					tableView+='</tbody>';
					tableView+='</table>';
					if($windowWidth < 768)
					{
						tableView+='</div>';
					}
				
				$("#alertStatus"+locationType).html(tableView);
				if(locationType !="state" || locationType !="district"){
					$(".dataTableAlert"+locationType).dataTable({
						"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
							"<'row'<'col-sm-12'tr>>" +
							"<'row'<'col-sm-5'i><'col-sm-7'p>>",
						buttons: [
							{
								extend:    'csvHtml5',
								text:      '<i class="fa fa-file-text-o"></i>',
								titleAttr: 'CSV',
								title:	   locationType,
								filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							},
							{
								extend:    'pdfHtml5',
								text:      '<i class="fa fa-file-pdf-o"></i>',
								titleAttr: 'PDF',
								title:	   locationType,
								filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
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
		function tableViewWaterStatus(divId,GLtbodyAlertArr,locationType){
			
			if(GLtbodyAlertArr !=null && GLtbodyAlertArr.length>0){
				var $windowWidth = $(window).width();
					var tableView='';
					
					tableView+='<h5 style="padding:5px"><span class="chartTitleAlign"><img src="Assests/icons/driking.png"> <span style="margin-left:5px;">Drinking Water Satisfaction Levels</span></span></h5>';
				if($windowWidth < 768)
				{
					tableView+='<div class="table-responsive">';
				}
					tableView+='<table class="table table-bordered dataTableDrinking'+locationType+'">';
					tableView+='<thead class="text-capital">';
					tableView+='<tr>'; 
					tableView+='<th>'+locationType+'</th>';
						if(GLtbodyAlertArr[0] !=null && GLtbodyAlertArr[0].statusList !=null && GLtbodyAlertArr[0].statusList.length>0){
							for(var j in GLtbodyAlertArr[0].statusList){
									tableView+='<th>'+GLtbodyAlertArr[0].statusList[j].name+'</th>';
									tableView+='<th>%</th>';
							}
						}
					tableView+='<th>Total Hamlets</th>';	
					tableView+='</tr>'; 
					tableView+='</thead>';
					tableView+='<tbody>';	
					
						for(var i in GLtbodyAlertArr){
							tableView+='<tr>'; 
							if(locationType == "state"){
								tableView+='<td>Andhra Pradesh</td>';
							}else{
								tableView+='<td>'+GLtbodyAlertArr[i].name+'</td>';
							}	
							var totalCount=0;
							if(GLtbodyAlertArr[i].statusList !=null && GLtbodyAlertArr[i].statusList.length>0){
								for(var j in GLtbodyAlertArr[i].statusList){
										tableView+='<td class="ivrStatusViewCls" attr_status_name="'+GLtbodyAlertArr[i].statusList[j].name+'" attr_location_type="'+locationType+'" attr_location_value="'+GLtbodyAlertArr[i].id+'" attr_location_name="'+GLtbodyAlertArr[i].name+'" attr_total_count="'+GLtbodyAlertArr[i].statusList[j].count+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyAlertArr[i].statusList[j].count+'</td>';
										if(GLtbodyAlertArr[i].statusList[j].percentage !=null && GLtbodyAlertArr[i].statusList[j].percentage >0){
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyAlertArr[i].statusList[j].percentage+'</small></td>';
										}else{
											tableView+='<td> - </td>';
										}
										
										totalCount =totalCount+GLtbodyAlertArr[i].statusList[j].count;
								}
							}
							tableView+='<td>'+totalCount+'</td>';
							tableView+='</tr>'; 
						}
					tableView+='</tbody>';
					tableView+='</table>';
				if($windowWidth < 768)
				{
					tableView+='</div>';
				}
				
				$("#drinking"+locationType).html(tableView);
				if(locationType !="state" || locationType !="district"){
					$(".dataTableDrinking"+locationType).dataTable({
						"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
							"<'row'<'col-sm-12'tr>>" +
							"<'row'<'col-sm-5'i><'col-sm-7'p>>",
						buttons: [
							{
								extend:    'csvHtml5',
								text:      '<i class="fa fa-file-text-o"></i>',
								titleAttr: 'CSV',
								title:	   locationType,
								filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							},
							{
								extend:    'pdfHtml5',
								text:      '<i class="fa fa-file-pdf-o"></i>',
								titleAttr: 'PDF',
								title:	   locationType,
								filename:  locationType+''+moment().format("DD/MMMM/YYYY  HH:MM"),
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
		function tableViewHabitationStatus(divId,GLtbodyArr,locationType)//aravind
		{
				 for(var k in divId){
					 var $windowWidth = $(window).width();
					 if(GLtbodyArr !=null && GLtbodyArr.length>0){
						var tableView='';
						//if($windowWidth < 768)
						//{
							tableView+='<div class="table-responsive">';
						//}
						if(divId[k].id=="assestsId"){
							tableView+='<table class="table table-bordered" id="dataTable2'+locationType+divId[k].id+'" style="width: 100%;">';
						}
							tableView+='<thead class="text-capital">';
							
							if(divId[k].id=="assestsId"){
								tableView+='<tr>';
								tableView+='<th>'+locationType+'</th>';
								if(GLtbodyArr[0] !=null && GLtbodyArr[0].basicList !=null && GLtbodyArr[0].basicList.length>0){
									for(var j in GLtbodyArr[0].basicList){
										tableView+='<th>'+GLtbodyArr[0].basicList[j].assetType+'</th>';
									}
								}
								tableView+='<th>TOTAL</th>';
								tableView+='</tr>';
							}
						
						tableView+='</thead>';
						tableView+='<tbody>';
							if(divId[k].id=="assestsId"){
								for(var i in GLtbodyArr){
									var totalCount=0;
									tableView+='<tr>';
										tableView+='<td>'+GLtbodyArr[i].name+'</td>';
									
									if(GLtbodyArr[i].basicList !=null && GLtbodyArr[i].basicList.length>0){
										for(var j in GLtbodyArr[i].basicList){
											if(GLtbodyArr[i].basicList[j].count !=null && GLtbodyArr[i].basicList[j].count>0){
												tableView+='<td class="assetsClickView" attr_status="'+GLtbodyArr[i].basicList[j].assetType+'" attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_location_type="'+locationType+'" attr_total_count = "'+GLtbodyArr[i].basicList[j].count+'" attr_location_name="'+GLtbodyArr[i].name+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].basicList[j].count+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											
											totalCount =totalCount+GLtbodyArr[i].basicList[j].count;
										}
									}
									if(totalCount >0){
										tableView+='<td>'+totalCount+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									
									tableView+='</tr>';
								}
							}
						tableView+='</tbody>';
						tableView+='</table>';	
						//if($windowWidth < 768)
						//{
							tableView+='</div>';
						//}
					if(divId[k].id !=="jalavani"){
						$("#"+locationType+"BlockId"+divId[k].id).html(tableView);
						if(divId[k].id=="assestsId"){
							if(locationType !="state" && locationType !="district"){
								$("#dataTable2"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
										"<'row'<'col-sm-12'tr>>" +
										"<'row'<'col-sm-5'i><'col-sm-7'p>>",
									buttons: [
										{
											extend:    'csvHtml5',
											text:      '<i class="fa fa-file-text-o"></i>',
											titleAttr: 'CSV',
											title:	   locationType,
											filename:  locationType+divId[k].id+''+moment().format("DD/MMMM/YYYY  HH:MM"),
										},
										{
											extend:    'pdfHtml5',
											text:      '<i class="fa fa-file-pdf-o"></i>',
											titleAttr: 'PDF',
											title:	   locationType,
											filename:  locationType+divId[k].id+''+moment().format("DD/MMMM/YYYY  HH:MM"),
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
				if(id == "stateBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id == "stateBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id == "stateBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'state',"","",2);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'state',"","",2);
				}else if(id == "stateBlockIdassestsId"){
					getAssetInfoBetweenDates('table','state',blocksArr,"","","");
				}else if(id == "stateBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','state',blocksArr,"","","");
				}else if(id == "stateBlockIdwaterSourceId"){
					getWaterSourceDeatils2('state',blocksArr,"","","","table")
				}
			}else if(blockName == "district"){
				emptyCheckDistrict();
				if(id == "districtBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id == "districtBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id == "districtBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'district',"","",2);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'district',"","",2);
				}else if(id == "districtBlockIdassestsId"){
					getAssetInfoBetweenDates('table','district',blocksArr,"","","");
				}else if(id == "districtBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','district',blocksArr,"","","");
				}else if(id == "districtBlockIdwaterSourceId"){
					getWaterSourceDeatils2('district',blocksArr,"","","","table")
				}
			}else if(blockName == "constituency"){
				emptyCheckConstituency();
				if(id == "constituencyBlockIdhabitation"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id=="constituencyBlockIdperformance"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id=="constituencyBlockIdjalavani"){
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',"","",3);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',"","",3);
				}else if(id == "constituencyBlockIdassestsId"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getAssetInfoBetweenDates('table','constituency',blocksArr,"","","");
				}else if(id == "constituencyBlockIdschemeId"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","");
				}else if(id == "constituencyBlockIdwaterSourceId"){
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getWaterSourceDeatils2('constituency',blocksArr,"","","","table")
				}
			}else if(blockName == "mandal"){
				emptyCheckMandal();
				if(id == "mandalBlockIdhabitation"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id=="mandalBlockIdperformance"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id=="mandalBlockIdjalavani"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',"","",3);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',"","",3);
				}else if(id == "mandalBlockIdassestsId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getAssetInfoBetweenDates('table','mandal',blocksArr,"","","");
				}else if(id == "mandalBlockIdschemeId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","");
				}else if(id == "mandalBlockIdwaterSourceId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getWaterSourceDeatils2('mandal',blocksArr,"","","","table")
				}
			}
		});
		function emptyCheckState(){
			$("#stateBlockIdhabitation").html('');
			$("#stateBlockIdperformance").html('');
			$("#alertStatusstate").html('');
			$("#drinkingstate").html('');
			$("#stateBlockIdassestsId").html('');
			$("#stateBlockIdschemeId").html('');
			$("#stateBlockIdwaterSourceId").html('');
		}
		function emptyCheckDistrict(){
			$("#districtBlockIdhabitation").html('');
			$("#districtBlockIdperformance").html('');
			$("#alertStatusdistrict").html('');
			$("#drinkingdistrict").html('');
			$("#districtBlockIdassestsId").html('');
			$("#districtBlockIdschemeId").html('');
			$("#districtBlockIdwaterSourceId").html('');
		}
		function emptyCheckConstituency(){
			$("#constituencyBlockIdhabitation").html('');
			$("#constituencyBlockIdperformance").html('');
			$("#alertStatusconstituency").html('');
			$("#drinkingconstituency").html('');
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
			$("#drinkingmandal").html('');
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
				if(id == "stateBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id == "stateBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id == "stateBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'state',"","",2);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'state',"","",2);
				}else if(id == "stateBlockIdassestsId"){
					getAssetInfoBetweenDates('table','state',blocksArr,"","","");
				}else if(id == "stateBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','state',blocksArr,"","","");
				}else if(id == "stateBlockIdwaterSourceId"){
					getWaterSourceDeatils2('state',blocksArr,"","","","table")
				}
			}else if(blockName == "district"){
				emptyCheckDistrict();
				if(id == "districtBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id == "districtBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id == "districtBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'district',"","",2);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'district',"","",2);
				}else if(id == "districtBlockIdassestsId"){
					getAssetInfoBetweenDates('table','district',blocksArr,"","","");
				}else if(id == "districtBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','district',blocksArr,"","","");
				}else if(id == "districtBlockIdwaterSourceId"){
					getWaterSourceDeatils2('district',blocksArr,"","","","table")
				}
			}else if(blockName == "constituency"){
				emptyCheckConstituency();
				if(id == "constituencyBlockIdhabitation"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id=="constituencyBlockIdperformance"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id=="constituencyBlockIdjalavani"){
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',"","",3);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',"","",3);
				}else if(id == "constituencyBlockIdassestsId"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getAssetInfoBetweenDates('table','constituency',blocksArr,"","","");
				}else if(id == "constituencyBlockIdschemeId"){
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","");
				}else if(id == "constituencyBlockIdwaterSourceId"){
					getAllPrrwsDistricts("chosendistValconstituencyBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
					getWaterSourceDeatils2('constituency',blocksArr,"","","","table")
				}
			}else if(blockName == "mandal"){
				emptyCheckMandal();
				if(id == "mandalBlockIdhabitation"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
				}else if(id=="mandalBlockIdperformance"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table',"","","");
				}else if(id=="mandalBlockIdjalavani"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',"","",3);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',"","",3);
				}else if(id == "mandalBlockIdassestsId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getAssetInfoBetweenDates('table','mandal',blocksArr,"","","");
				}else if(id == "mandalBlockIdschemeId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","");
				}else if(id == "mandalBlockIdwaterSourceId"){
					getAllPrrwsDistricts("chosendistValmandalBlockId");
					//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
					getWaterSourceDeatils2('mandal',blocksArr,"","","","table")
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
	   'This Month': [moment().startOf('month'), moment()],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()]
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
			$("#financialYearId").append("<option value='0'>Select Financial Year</option>");
			if(result != null && result.length >0){
				for(var i in result){
					var value = result[i].financialYear.split('-');
					$("#financialYearId").append("<option value="+value[1]+">"+result[i].financialYear+"</option>");
					
				}
				$("#financialYearId").val('2017');
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
				if(tabId == "constituencyBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType('constituency',blocksArr,'table',"","","");
				}else if(tabId == "constituencyBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo('constituency',blocksArr,'table',"","","");
				}else if(tabId == "constituencyBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',"","",searchLevelId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',"","",searchLevelId);
				}else if(tabId == "constituencyBlockIdassestsId"){
					getAssetInfoBetweenDates('table','constituency',blocksArr,"","","");
				}else if(tabId == "constituencyBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"","","");
				}else if(tabId == "constituencyBlockIdwaterSourceId"){
					getWaterSourceDeatils2('constituency',blocksArr,"","","","table")
				}
				
			}else{
				 
				 if(tabId == "constituencyBlockIdhabitation"){
					 getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					// getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
					getHabitationCoverageByStatusByLocationType('constituency',blocksArr,'table',"district",distId,"");
				}else if(tabId == "constituencyBlockIdperformance"){
					getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					//getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
					getKeyPerformanceIndicatorsInfo('constituency',blocksArr,'table',"district",distId,"");
				}else if(tabId == "constituencyBlockIdjalavani"){
					
					getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',distId,"",searchLevelId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',distId,"",searchLevelId);
				}else if(tabId == "constituencyBlockIdassestsId"){
					getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					 //getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
					getAssetInfoBetweenDates('table','constituency',blocksArr,"district",distId,"");
				}else if(tabId == "constituencyBlockIdschemeId"){
					getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					 //getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"district",distId,"");
					
				}else if(tabId == "constituencyBlockIdwaterSourceId"){
					getConstituenciesForDistrict(distId,"chosenconstValconstituencyBlockId");
					//getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValconstituencyBlockId");
					getWaterSourceDeatils2('constituency',blocksArr,"district",distId,"","table")
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
			if(tabId == "constituencyBlockIdhabitation"){
				if(constId == 0){
				  getHabitationCoverageByStatusByLocationType('constituency',blocksArr,'table',"district",distId,"");
				}else{
					getHabitationCoverageByStatusByLocationType('constituency',blocksArr,'table',"constituency",constId,"");
				}
			}else if(tabId == "constituencyBlockIdperformance"){
				if(constId == 0){
					getKeyPerformanceIndicatorsInfo('constituency',blocksArr,'table',"district",distId,"");
				}else{
					getKeyPerformanceIndicatorsInfo('constituency',blocksArr,'table',"constituency",constId,"");
					
				}
			}else if(tabId == "constituencyBlockIdjalavani"){
			
				if(constId == 0){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',distId,"",searchLevelDistId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',distId,"",searchLevelDistId);
				}else{
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'constituency',distId,constId,searchLevelDistId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'constituency',distId,constId,searchLevelDistId);
					
				}
				
			}else if(tabId == "constituencyBlockIdassestsId"){
				if(constId == 0){
					getAssetInfoBetweenDates('table','constituency',blocksArr,"district",distId);
				}else{
					getAssetInfoBetweenDates('table','constituency',blocksArr,"constituency",constId,"");
				}
				
			}else if(tabId == "constituencyBlockIdschemeId"){
				if(constId == 0){
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"district",distId,"");
				}else{
					getSchemeWiseWorkDetails('table','constituency',blocksArr,"constituency",constId,"");
				}
				
			}else if(tabId == "constituencyBlockIdwaterSourceId"){
				if(constId == 0){
					getWaterSourceDeatils2('constituency',blocksArr,"district",distId,"","table")
				}else{
					getWaterSourceDeatils2('constituency',blocksArr,"constituency",constId,"","table")
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
				if(tabIdM == "mandalBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"","","");
				}else if(tabIdM == "mandalBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"","","");
				}else if(tabIdM == "mandalBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',"","",searchLevelDistId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',"","",searchLevelDistId);
				}else if(tabIdM == "mandalBlockIdassestsId"){
					getAssetInfoBetweenDates('table','mandal',blocksArr,"","","");
				}else if(tabIdM == "mandalBlockIdschemeId"){
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"","","");
				}else if(tabIdM == "mandalBlockIdwaterSourceId"){
					getWaterSourceDeatils2('mandal',blocksArr,"","","","table")
				}
			}else{
				 if(tabIdM == "mandalBlockIdhabitation"){
					 getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					 //getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
					getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"district",distId,"");
				}else if(tabIdM == "mandalBlockIdperformance"){
					getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					//getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"district",distId,"");
				}else if(tabIdM == "mandalBlockIdjalavani"){
					getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',distId,"",searchLevelDistId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',distId,"",searchLevelDistId);
				}else if(tabIdM == "mandalBlockIdassestsId"){
					getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					// getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
					getAssetInfoBetweenDates('table','mandal',blocksArr,"district",distId,"");
				}else if(tabIdM == "mandalBlockIdschemeId"){
					getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					// getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
					 getSchemeWiseWorkDetails('table','mandal',blocksArr,"district",distId,"");
					
				}else if(tabIdM == "mandalBlockIdwaterSourceId"){
					getConstituenciesForDistrict(distId,"chosenconstValmandalBlockId");
					//getLocationBasedOnSelection("constituency","district",distId,"","chosenconstValmandalBlockId");
					getWaterSourceDeatils2('mandal',blocksArr,"district",distId,"","table")
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
			if(tabIdM == "mandalBlockIdhabitation"){
				$("#chosenmandalValmandalBlockId").html("");
				$("#chosenmandalValmandalBlockId").trigger("chosen:updated");
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
				 //getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
				if(constId == 0){
				  getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"district",distId,"");
				}else{
					getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"constituency",constId,"");
				}
			}else if(tabIdM == "mandalBlockIdperformance"){
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
			//	getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
				if(constId == 0){
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"district",distId,"");
				}else{
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"constituency",constId,"");
					
				}
			}else if(tabIdM == "mandalBlockIdjalavani"){
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
				if(constId == 0){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',distId,"",searchLevelDistId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',distId,"",searchLevelDistId);
				}else{
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',constId,"",searchLevelConstId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',constId,"",searchLevelConstId);
					
				}
				
			}else if(tabIdM == "mandalBlockIdassestsId"){
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
				//getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
				if(constId == 0){
					getAssetInfoBetweenDates('table','mandal',blocksArr,"district",distId,"");
				}else{
					getAssetInfoBetweenDates('table','mandal',blocksArr,"constituency",constId,"");
				}
				
			}else if(tabIdM == "mandalBlockIdschemeId"){
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
				//getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
				if(constId == 0){
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"district",distId,"");
				}else{
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"constituency",constId,"");
				}
				
			}else if(tabIdM == "mandalBlockIdwaterSourceId"){
				getTehsilsForConstituency(constId,"chosenmandalValmandalBlockId");
				//getLocationBasedOnSelection("mandal","constituency",constId,"","chosenmandalValmandalBlockId");
				if(constId == 0){
					getWaterSourceDeatils2('mandal',blocksArr,"district",distId,"","table")
				}else{
					getWaterSourceDeatils2('mandal',blocksArr,"constituency",constId,"","table")
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
			if(tabIdM == "mandalBlockIdhabitation"){
				
				if(mandalId == 0){
					getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"constituency",constId,"");
				}else{
					getHabitationCoverageByStatusByLocationType('mandal',blocksArr,'table',"mandal",mandalId,distId);
				}
			}else if(tabIdM == "mandalBlockIdperformance"){
				if(mandalId == 0){
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"constituency",constId,"");
				}else{
					getKeyPerformanceIndicatorsInfo('mandal',blocksArr,'table',"mandal",mandalId,distId);
				}
			}else if(tabIdM == "mandalBlockIdjalavani"){
				if(mandalId == 0){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',constId,"",searchLevelConstId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',constId,"",searchLevelConstId);
				}else{
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'mandal',constId,mandalId,searchLevelConstId);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'mandal',constId,mandalId,searchLevelConstId);
				}
				
				
			}else if(tabIdM == "mandalBlockIdassestsId"){
				if(mandalId == 0){
					getAssetInfoBetweenDates('table','mandal',blocksArr,"constituency",constId,"");
				}else{
					getAssetInfoBetweenDates('table','mandal',blocksArr,"mandal",mandalId,distId);
				}
				
			}else if(tabIdM == "mandalBlockIdschemeId"){
				if(mandalId == 0){
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"constituency",constId,"");
				}else{
					getSchemeWiseWorkDetails('table','mandal',blocksArr,"mandal",mandalId,distId);
				}
				
			}else if(tabIdM == "mandalBlockIdwaterSourceId"){
				if(mandalId == 0){
					getWaterSourceDeatils2('mandal',blocksArr,"constituency",constId,"","table")
				}else{
					getWaterSourceDeatils2('mandal',blocksArr,"mandal",mandalId,distId,"table")
				}
				
			}
		}
	});
	$(document).on("change","#financialYearId",function(){
		glStartDate="";
		glEndDate="";
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

$(document).on("click",".assetsClickView",function(){
		$("#modalHablitationTable").html('');
		$("#modalAlertTable").html('');
		$("#modalAssetsTable").html('');
		$("#modalWaterSourceTable").html('');
		$("#modalIvrStatusTable").html('');
		$("#modalKpiTable").html('');
		$("#modalSchemsTable").html('');
		
		var status = $(this).attr("attr_status");
		var locationValue = $(this).attr("attr_filter_value");
		var locationType=$(this).attr("attr_location_type");
		var totalCount=$(this).attr("attr_total_count");
		var locationName=$(this).attr("attr_location_name");
		var districtVal = $(this).attr("attr_district_val");
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;&nbsp;Overview</h4>");
		var startIndex=0;
		getAssetDetailsByAssetType(status,locationType,locationValue,startIndex,totalCount,districtVal);
		
		
	});
	
function getAssetDetailsByAssetType(status,locationType,locationValue,startIndex,totalCount,districtVal){
		$("#modalAssetsTable").html(spinner);
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
			var districtVal1="";
			if(locationType == "mandal"){
				districtVal1=districtVal;
			}

		var json = {
			assetType:status,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			startValue:startIndex,
			endValue:"10",
			filterType:filterType,
			filterValue:filterValue,
			year:yearVal,
			districtValue:districtVal1
		}
		$.ajax({                
			type:'POST',    
			url: 'getAssetDetailsByAssetType',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildAssetDetailsByAssetType(result,status,locationType,locationValue,startIndex,totalCount);
			}else{
				$(".paginationId").html("");
				$("#modalAssetsTable").html('No Data Available');
			}
			
		});
	}
//Asssets build
	function buildAssetDetailsByAssetType(result,status,locationType,locationValue,startIndex,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableAssetsTable">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>HABITATIONS NAME</th>';
					tableView+='<th>HABITATIONS CODE</th>';
					tableView+='<th>ASSEST NAME</th>';
					tableView+='<th>ASSEST CODE</th>';
					tableView+='<th>ASSEST COST</th>';
				tableView+='</tr>';
				
			tableView+='</thead>';
			tableView+='<tbody>';
			for(var i in result){
				tableView+='<tr>';
						tableView+='<td>'+result[i].districtName+'</td>';
						tableView+='<td>'+result[i].constituencyName+'</td>';
						tableView+='<td>'+result[i].mandalName+'</td>';
						tableView+='<td>'+result[i].habitationName+'</td>';
						tableView+='<td>'+result[i].habitationCode+'</td>';
						tableView+='<td>'+result[i].assestName+'</td>';
						tableView+='<td>'+result[i].assestCode+'</td>';
						tableView+='<td>'+result[i].assestCost+'</td>';
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalAssetsTable").html(tableView);
		$("#dataTableAssetsTable").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"order": [ 0, 'desc' ],
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
		if(startIndex == 0 && totalCount > 0){
				$(".paginationId").pagination({
					items: totalCount,
					itemsOnPage: 10,
					cssStyle: 'light-theme',
					hrefTextPrefix: '#pages-',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getAssetDetailsByAssetType(status,locationType,locationValue,num,totalCount);
					}
					
				});
			}
		$(".prev,.next").css("width","70px !important");	
	}