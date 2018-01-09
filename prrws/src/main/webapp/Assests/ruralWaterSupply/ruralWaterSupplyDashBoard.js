 //Angular Start  getAlertsOfCategoryByStatusWise()
	var month =moment().format("M");
	var glStartDate="";
	var glEndDate="";
	var year ="";
	if(month >= 04){
		glStartDate = "01-04-"+moment().format("YYYY");
		glEndDate = "01-04-"+moment().add(1, 'years').format("YYYY");
		year=moment().format("YYYY");
	}else{
		glStartDate = "01-04-"+moment().subtract(1, 'years').format("YYYY");
		glEndDate = "01-04-"+moment().format("YYYY");
		year=moment().subtract(1, 'years').format("YYYY");
	}
	
		
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
		/*$(document).on('cut copy paste', function (e) {
			e.preventDefault();
		});*/
		function onloadCalls(){
			getHabitationCoverageByStatusByLocationType('state','','graph',"","","");
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails('graph','state',"","","","");
			getAssetInfoBetweenDates('graph','state',"","","","");
			//getWaterSourceInfo();
			getWaterSourceDeatils2("state","","","","","graph")
			getKeyPerformanceIndicatorsInfo('state','','graph',"","","");
			getPlanofActionForStressedHabitations();
			//getAlertDetailsOfCategoryByStatusWise();
			getLocationWiseAlertStatusCounts('graph',"",'state',"","",2);
			getAlertFeedbackStatusDetails();
			
			getHamletWiseIvrStatusCounts('graph','','state',"","",2);
			getIHHLOverviewData("abstract");
			getSBPaymentsAbstract();
			getSwachhBharatMissionOverviewDtls();
			//locationType,filterType,filterValue,districtValue,divId
			//getLocationBasedOnSelection("district","","","","chosendistValconstituencyBlockId");
			//getLocationBasedOnSelection("district","","","","chosendistValmandalBlockId");
			
			getAllPrrwsDistricts("chosendistValconstituencyBlockId");
			getAllPrrwsDistricts("chosendistValmandalBlockId");
			
			tabBlocks('stateBlockId','state');
			tabBlocks('districtBlockId','district');
			tabBlocks('constituencyBlockId','constituency');
			tabBlocks('mandalBlockId','mandal');
			
			//hamlet detail List
			//getLocationWiseHamletIvrList();
			
			responsiveTabs();
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
		
		function getHabitationCoverageByStatusByLocationType(locationType,divId,type,filterType,filterValue,districtValue)
		{
			var typeVal="";
			if(type=="graph"){
				$("#totalValues").html(spinner);
				$("#habitation").html(spinner);
				typeVal = "graph";
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
				locationType:locationType,
				year:yearVal,
				//stressedHabitationYear:yearVal,
				filterType:filterType,
				filterValue:filterValue,
				districtValue:districtValue,
				type:typeVal
			}
			$.ajax({
				url: 'getHabitationCoverageByStatusByLocationType',
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
							$("#totalCntTtlValues").show()
							buildChartForHabitationCoverage(ajaxresp);
							buildChartForHabitationCoverageStatus(ajaxresp);
						}else{
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations');
						}
						
						
					}else{
						if(type == "graph"){
							$("#totalValues").html("No Data Available");
							$("#habitation").html("No Data Available");
							$("#totalCntTtlValues").hide()
						}else{
							for(var k in divId){
								$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
							}
						}
						
					}
				}
			});
			
		}
		function buildChartForHabitationCoverage(response){
			
			var dataArr = [];
			var totalCount=0;
			var statusNamesArr=[];
			var pcCount = 0;
			for(var i in response){
				for(var j in response[i].statusList){
					
					if(response[i].statusList[j].status == "PC1" || response[i].statusList[j].status == "PC2" || response[i].statusList[j].status == "PC3" || response[i].statusList[j].status == "PC4"){
						pcCount = pcCount+parseInt(response[i].statusList[j].count);
						totalCount =totalCount+parseInt(response[i].statusList[j].count);
					}else{
						var tempArr = [];
						response[i].statusList[j].status == "NSS" ? statusNamesArr.push("QA"):statusNamesArr.push(response[i].statusList[j].status);
						tempArr.push(parseInt(response[i].statusList[j].count));
						dataArr.push(tempArr);
						totalCount =totalCount+parseInt(response[i].statusList[j].count);
					}
				}
				var pcArr = [];
				statusNamesArr.push("PC");
				pcArr.push(pcCount);
				dataArr.push(pcArr);
			}
			
			$("#totalCntTtlValues").html("TOTAL:"+totalCount)
			var colors = ['#14BAAD','#494949','#FC5049']
			var id = 'totalValues';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
			};
		
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						
					},
					
				}
				
			};
			
			var plotOptions ={ column: {
					colorByPoint: true
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
					useHTML:true,
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
			$("#totalCountId").html(totalCount);
		}
		
		function buildChartForHabitationCoverageStatus(response){
			var dataArr = [];
			var totalCount=0;
			var statusNamesArr=[];
			var colors = []
			for(var i in response){
			  for(var j in response[i].statusList){
						var tempArr = [];
						statusNamesArr.push(response[i].statusList[j].status);
						//tempArr.push(parseInt(response[i].statusList[j].count));
						//dataArr.push(tempArr);
						dataArr.push({"y":parseInt(response[i].statusList[j].count),"extra":""+response[i].statusList[j].percentage});
						totalCount=totalCount+parseInt(response[i].statusList[j].count);
						
						if(response[i].statusList[j].status == "FC"){
							colors.push('#14BAAD')
						}else if(response[i].statusList[j].status == "NSS"){
							colors.push('#FC5049')
						}else{
							colors.push('#FFBF14')
						}
				}
			}
			var id = 'habitation';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			
			var title = {
				text: '',
				align:'left',
				 style: {
					 color: '#777',
					 font: 'bold 8px "Lato", sans-serif'
				  } 
			};
			var yAxis = {
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
					colorByPoint: true
				}};
			var tooltip = {
				useHTML:true,
				formatter: function () {
					var pcnt = this.point.extra;
					return '<b>' + this.x + '</b><br/>' +
						this.y+"-"+pcnt+'%';
				}
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'canter',
					formatter: function() {
							var pcnt = this.point.extra;;
							return '<span>'+this.y+'<br>('+pcnt+'%)</span>';
					} 
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
		function getLabTestDetails()
		{
			$("#overView").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:yearVal
			}
			$.ajax({
				url: 'getLabTestDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						buildChartForLabTestDetails(ajaxresp);
					}else{
						$("#overView").html("No Data Available");
					}
				}
			});
		}
		
		function buildChartForLabTestDetails(response){
			var dataArr = [];
			var arr1 = [],arr2 = [];
			var statusNamesArr=[];
			var totalCount=0;
			statusNamesArr.push("physicalTestCount");
			arr1.push(parseInt(response.physicalTestCount));
			dataArr.push(arr1);
			statusNamesArr.push("bacterialTestCount");
			arr2.push(parseInt(response.bacterialTestCount));
			dataArr.push(arr2);
			totalCount =parseInt(response.physicalTestCount)+parseInt(response.bacterialTestCount);
			var colors = ['#14BAAD','#FC5049']
			var id = 'overView';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						if(this.value.toString().length >= 6){
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 6)+'..'+'</p>';
						}else{
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						}
						
						
					},
					
				}
			};
			var plotOptions ={ column: {
					colorByPoint: true
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
		
		function getHabitationSupplyDetails(){
			$("#levelOfSupply1").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:yearVal
			}
			$.ajax({
				url: 'getHabitationSupplyDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					$("#levelOfSupply1").html('');//ara3
					if(ajaxresp !=null ){
						buildHabitationSupplyDetails(ajaxresp);
					}else{
						$("#levelOfSupply1").html("No Data Available");
					}
				}
			});
		}
		
		function buildHabitationSupplyDetails(result){
				var groundArr=[];
				var surfaceArr=[];
					
					groundArr.push(result.groundWaterSafeMLD)
					surfaceArr.push(result.groundWaterUnSafeMLD)
					surfaceArr.push(result.surfaceWaterUnSafeMLD)
					groundArr.push(result.surfaceWaterSafeMLD)
				
				//$("#levelSupplyTtlValues").html("TOTAL:"+totalCount)
				$("#levelOfSupply1").highcharts({
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
						categories: ['GROUND','SURFACE']
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
								this.series.name + ': ' + this.y;
						},
						
					},
					plotOptions: {
						column: {
							stacking: 'percent',
							//colorByPoint: true
							dataLabels: {
								useHTML:true,
								enabled: true,
								formatter: function() {
									if(this.y == 0){
										return null;
									}else{
										return '<span>'+this.y+'</span>';
									}
									
								}
							}
						}
					},
					series: [{
							name: 'SAFE',
							data: groundArr,
							color:'#14BBAE'
						}, {
							name: 'UNSAFE',
							data: surfaceArr,
							color:'#FC5E57'
						}]
				});
		}
		
		function getSchemesDetails(){
			$("#schemes").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:yearVal
			}
			$.ajax({
				url: 'getSchemesDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						$("#schemesTtlValues").show()
						buildSchemesDetails(ajaxresp);
					}else{
						$("#schemesTtlValues").hide()
						$("#schemes").html("No Data Available");
					}
				}
			});
			
		}
		function buildSchemesDetails(result){
			var dataArr = [];
			var statusNamesArr=[];
			var totalCount=0;
				for(var i in result)
				  {
					var tempArr = [];
					
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						if(result[i].assetType == "PWS"){
							result[i].assetType = "SINGAL VILLAGE";
						}else{
							result[i].assetType = "MULTI VILLAGE";
						}
						statusNamesArr.push(result[i].assetType);
						tempArr.push(parseInt(result[i].count));
						dataArr.push(tempArr);
						totalCount=totalCount+parseInt(result[i].count);
						
					}
					
				  }
				$("#schemesTtlValues").html("TOTAL:"+totalCount)
				var colors = ['#14BAAD','#FC5049']
				var id = 'schemes';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var title = { 
					text: '',
					align:'left',
					 style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  } 
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
					categories: statusNamesArr,
					labels: {
						useHTML:true,
						formatter: function() {
							
							if(this.value.toString().length >= 6){
								return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 6)+'..'+'</p>';
							}else{
								return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
							}
							
							
						},
						
					}
				};
				var plotOptions ={ column: {
						colorByPoint: true
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
				var colors = ['#14BAAD','#FC5049']
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
		}
		function getSchemeWiseWorkDetails(type,locationType,divId,filterType,filterValue,districtValue){
			var typeVal="";
			if(type =="graph"){
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
			$.ajax({
				url: 'getSchemeWiseWorkDetails',
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
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations');
						}
						
					}else{
						if(type == "graph"){
							$("#habitationWorks").html("No Data Available");
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
			var dataArr = [];
			var assetTypeArr = [];
			var workOngoingArr = [];
			var workNotGroundedArr = [];
			var workCompletedArr = [];
			var workComissionedArr = [];
				for(var i in result)
				  {					 
					if(result[i].assetType == "PWS" || result[i].assetType == "CPWS")
					{
						assetTypeArr.push(result[i].assetType);						
						workOngoingArr.push({"y":result[i].workOngoingCount,"extra":result[i].percentageOne.toFixed(1)});
						workNotGroundedArr.push({"y":result[i].workNotGroundedCount,"extra":result[i].percentageFour.toFixed(1)});
						workCompletedArr.push({"y":result[i].workCompletedCount,"extra":result[i].percentageThree.toFixed(1)});
						workComissionedArr.push({"y":result[i].workComissionedCount,"extra":result[i].percentageTwo.toFixed(1)});	
						
					}
					
				  }
				$("#habitationWorks").highcharts({
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
						categories: assetTypeArr
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
							data: workComissionedArr,
							color:'#16af18'
						},{
							name: 'Completed',
							data: workCompletedArr,
							color:'#3ae80f'
						}, {
							name: 'Ongoing',
							data: workOngoingArr,
							color:'#EA8512'
						}, {
							name: 'Not Grounded',
							data: workNotGroundedArr,
							color:'#FC5E57'
						}]
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
		function getWaterSourceInfo(){//ara1 
			$("#waterSources").html(spinner);
			
			var json = {
				locationType:""
			}
			$.ajax({
				url: 'getWaterSourceInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){//ara4
						$("#waterSourcesTtlValues").show()
						var safeArr = [];
						var unSafeArr = [];
						safeArr.push({"y":ajaxresp.safeGroundWaterSourceCount})
						safeArr.push({"y":ajaxresp.safeSurfaceWaterSourceCount})
						unSafeArr.push({"y":ajaxresp.unSafeGroundWaterSourceCount})
						unSafeArr.push({"y":ajaxresp.unSafeSurfaceWaterSourceCount})
						//$("#waterSourcesTtlValues").html("TOTAL:"+ajaxresp.totalGroundWaterSourceCount)
						$("#waterSources").highcharts({
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
									categories: ['GROUND','SURFACE']
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
											this.series.name + ': ' + this.y;
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
													return '<span>'+this.y+'</span>';
												}
												
											}
										}
									}
								},
								series: [{
										name: 'SAFE',
										data: safeArr,
										color:'#14BBAE'
									}, {
										name: 'UNSAFE',
										data: unSafeArr,
										color:'#FC5E57'
									}]
							});
					}else{
						$("#waterSourcesTtlValues").hide()
						$("#waterSources").html("No Data Available");
					}
				}
			});	
		}
		
		function getKeyPerformanceIndicatorsInfo(locationType,divId,type,filterType,filterValue,districtValue){//ara1
			if(type=="graph"){
				$("#keyPerformance").html(spinner);
			}else{
				for(var k in divId){
					$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
				}
			}
		
			var json={
						fromDateStr:glStartDate,
						toDateStr:glEndDate,
						locationType:locationType,
						filterType:filterType,
						filterValue:filterValue,
						districtValue:districtValue,
						year:$("#financialYearId").val()==0?"":$("#financialYearId").val()
						
					}
			$.ajax({
				url: 'getKeyPerformanceIndicatorsInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						if(type=="graph"){
							buildKeyPerformanceIndicatorsInfo(ajaxresp)
						}else{
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations');
						}
						
					}else{
						if(type=="graph"){
							$("#keyPerformance").html("No Data Available");
						}else{
							for(var k in divId){
								$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
							}
						}
					}
				}
			});
			
		}
		function buildKeyPerformanceIndicatorsInfo(result){
				var keyNamesArr =[];
				var targetArr=[];
				var achivedArr=[];
				if(result !=null && result.length>0){
					/* for(var i in result){
							keyNamesArr.push(result[i].type);
							targetArr.push(result[i].targetCount)
							achivedArr.push(result[i].achivmentCount)
					} */
					keyNamesArr.push("Partially Covered");
					targetArr.push({"y":result[0].pcTarget});
					achivedArr.push({"y":result[0].pcAchivement});
					keyNamesArr.push("Quality Affected");	
					targetArr.push({"y":result[0].qaTarget});
					achivedArr.push({"y":result[0].qaAchivement});
				}
				$("#keyPerformance").highcharts({
					chart: {
						type: 'column'
					},
					title: {
						useHTML:true,
						text: ''
					},
					xAxis: {
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						categories: keyNamesArr
					},
					yAxis: [{
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
						title: {
							text: ''
						}
					}, {
						title: {
							text: ''
						},
						opposite: true
					}],
					legend: {
						symbolHeight: 12,
						symbolWidth: 12,
						symbolRadius: 6,
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
						name: 'Target',
						color: '#FC5049',
						data: targetArr,
						tooltip: {
							valuePrefix: ' ',
							valueSuffix: ' '
						},
						pointPadding: 0.2,
						pointPlacement: 0.1,
						yAxis: 1
					}, {
						name: 'Achived',
						color: '#14BAAD',
						data: achivedArr,
						tooltip: {
							valuePrefix: ' ',
							valueSuffix: ' '
						},
						pointPadding: 0.3,
						pointPlacement: 0.1,
						yAxis: 1
					}]
				});
		}
		function getStressedHabitationsInfoByLocationType(locationType,levelId,levelName,parentLocId){
			$("#modalTable").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			
			if(locationType == "state"){
				levelId ="";
			}else if(locationType == "district"){
				levelId = levelId;
			}
			
			
			var districtValue ="";
			if(locationType == "mandal"){
				districtValue = parentLocId;
			}
			var json = {
					locationType:locationType,
					year:yearVal,
					//stressedHabitationYear:yearVal,
					filterType:locationType,
					filterValue:levelId,
					districtValue:districtValue,
					fromDateStr:glStartDate,
					toDateStr:glEndDate

					}
			$.ajax({
				url: 'getStressedHabitationsInfoByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null){
						buildgetStressedHabitationsInfoByLocationType(ajaxresp,levelName,locationType);
					}
				}
			});
			
		}
		
		function getPlanofActionForStressedHabitations(){
			$("#planOfAction").html(spinner)
			
			var financialVal ="";
			if($("#financialYearId").val() != 0){
				 financialVal=$("#financialYearId").val();
			}
			var json = {
					fromDateStr:glStartDate,
					toDateStr:glEndDate,
					locationType:"state",
					//stressedHabitationYear:financialVal,
					year:financialVal
					}
			$.ajax({
				url: 'getPlanofActionForStressedHabitations',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						//buildPlanofActionForStressedHabitations(ajaxresp);
						buildPlanofActionForStressedHabitationsNew(ajaxresp);
					}else{
						$("#planOfAction").html("No Data Available")
					}
				}
			});
			
		}
		
		function buildPlanofActionForStressedHabitationsNew(result){
			if(result !=null && result[0] !=null){
				var dataArr = [];
				var stressedArr = [];
				var achievedHabitationArr = [];
				var targetHabitationArr = [];
				
				/* var stressedArrOne = [];
				var achievedPopulationArrOne = [];
				var targetPopulationArrOne = []; */

				stressedArr.push("Habitations");						
				achievedHabitationArr.push({"y":result[0].achived,"extra":result[0].percentageOne.toFixed(1)});
				targetHabitationArr.push({"y":result[0].target,"extra":result[0].achivedHabPerc.toFixed(1)});
				
				stressedArr.push("Population");						
				achievedHabitationArr.push({"y":result[0].achivedPopulation,"extra":result[0].targetPopPerc.toFixed(1)});
				targetHabitationArr.push({"y":result[0].targetPopulation,"extra":result[0].achivedPopPerc.toFixed(1)});
								
				
				$("#planOfAction").highcharts({
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
						categories: stressedArr
					},
					yAxis:{
						min: 0,
						gridLineWidth: 0,
						minorGridLineWidth: 0,
							title: {
								text: null
							}
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
								this.series.name + ': ' + this.y;
						}
					},
					plotOptions: {
						column: {
							//colorByPoint: true
							dataLabels: {
								enabled: true,
								formatter: function() {
									if(this.y == 0){
										return null;
									}else{
										return '<span>'+this.y;
									}
									
								}
							}
						}
					},
					series: [{
							name: 'Target',
							data: targetHabitationArr,
							color:'#FC5E57'
						}, {
							name: 'Achieved',
							data: achievedHabitationArr,
							color:'#14BBAE'
						}]
				});
			}
		}
		
		function buildPlanofActionForStressedHabitations(result){
			var targetArr=[];
			var achivedArr=[];
			if(result !=null && result[0] !=null){
				targetArr.push({"y":result[0].achivedPopulation,color:"#14BAAD"})
				achivedArr.push({"y":result[0].targetPopulation,color:"#FC5049"})
			}
			$("#planOfAction").highcharts({
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
					categories: ['Habitations']
				},
				yAxis:{
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels: {
						enabled: false
					},
					title: {
						text: null
					}
				}, 
				
				legend: {
					symbolHeight: 12,
					symbolWidth: 12,
					symbolRadius: 6,
					enabled: true
				},
				tooltip: {
					 pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b>'
				},
				plotOptions: {
					column: {
						//colorByPoint: true
					}
				},
				series: [{
						name: 'Target',
						data: targetArr
					}, {
						name: 'Achived',
						data: achivedArr
					}]
			});
		}
		function getAlertDetailsOfCategoryByStatusWise(){
			//$("#alertStatus").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				deptId:49,
				year:yearVal
			}
			$.ajax({
				url: 'getAlertDetailsOfCategoryByStatusWise',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					/* if(ajaxresp !=null && ajaxresp.length>0){
						buildAlertDetailsOfCategoryByStatusWise(ajaxresp);
					}else{
						$("#alertStatus").html('No Data Available');
					} */
				}
			});
		}
		
		function buildAlertDetailsOfCategoryByStatusWise(result){
			
			if(result !=null && result.length>0){
				var statusCountArr = [];
				var totalCount=0;
				for(var i in result){
					var CompleteClosedCount=0;
					var othersCount=0;
					var notifiedCnt=0;
					var actionPgress=0;
					var propasalCnt=0;
					for(var j in result[i].statusList){
						 if(result[i].statusList[j].id==2){
							statusCountArr.push(result[i].statusList[j].count);
							notifiedCnt=result[i].statusList[j].count;
						}else if(result[i].statusList[j].id==13){
							statusCountArr.push(result[i].statusList[j].count);
							actionPgress=result[i].statusList[j].count;
						}else if(result[i].statusList[j].id==3){
							 statusCountArr.push(result[i].statusList[j].count);
							 propasalCnt=result[i].statusList[j].count;
						}else if(result[i].statusList[j].id==4 ||  result[i].statusList[j].id == 12){
							 CompleteClosedCount =CompleteClosedCount+result[i].statusList[j].count;
						}else{
							 othersCount =othersCount+result[i].statusList[j].count;
						}
							
					}
					
				}
				totalCount =notifiedCnt+actionPgress+propasalCnt+CompleteClosedCount+othersCount;
				statusCountArr.push(CompleteClosedCount);
				statusCountArr.push(othersCount);
				var colors = ['#efa5b6','#ffcb7f','#5a8476','#4d9b66','#9698c8']
				var id = 'alertStatus';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				
				var title = {
					text: '',
					align:'left',
					 style: {
						 color: '#000',
						 font: 'bold 16px "Lato", sans-serif'
					  } 
				};
				var yAxis = {
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ['Notified','Action In Progess','Proposal','Completed & Closed','Others']
					
				};
				var plotOptions ={ column: {
						colorByPoint: true
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
					data: statusCountArr,
					
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
		}
		function getAlertFeedbackStatusDetails(){
			$("#feedbackId").html(spinner);
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				deptId:49,
				year:yearVal
			}
			$.ajax({
				url: 'getAlertFeedbackStatusDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildAlertFeedbackStatusDetails(ajaxresp);
					}else{
						$("#feedbackId").html("No Data Available");
					}
				}
			});
		} 
	
		function buildAlertFeedbackStatusDetails(result){
			
			var statusNamesArr=[];
			var statusCountArr=[];
			var totalCount=0;
			if(result !=null && result.length>0){
				for(var i in result){
					statusNamesArr.push(result[i].name)
					statusCountArr.push(result[i].count)
					totalCount=totalCount+result[i].count;
				}
			}
			var colors = ['#0FBE08','#FF0909','#FFBA00']
			var id = 'feedbackId';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
			};
		
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: statusNamesArr,
				labels: {
					useHTML:true,
					formatter: function() {
						if(this.value.toString().length >= 3){
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value.toString().substring(0, 3)+'..'+'</p>';
						}else{
							return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						}
					},
					
				}
				
			};
			
			var plotOptions ={ column: {
					colorByPoint: true
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
				data: statusCountArr,

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
					tabBlock+='<div class="col-sm-12">';
						tabBlock+='<div>';
							
							tabBlock+='<ul class="nav nav-tabs nav-tabs-custom custom_value'+blockName+'" role="tablist">';
								for(var i in blocksArr)
								{
									if(i == 0)
									{
										if(blocksArr[i].id == "jalavani"){
											tabBlock+='<li block-levels="'+blocksArr[i].id+'" class="active" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><span>'+blocksArr[i].name+'</span></a></li>';
										}else{
											tabBlock+='<li block-levels="'+blocksArr[i].id+'" class="active" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
										}
										
									}else{
										if(blocksArr[i].id == "jalavani"){
											tabBlock+='<li block-levels="'+blocksArr[i].id+'" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><span>'+blocksArr[i].name+'</span></a></li>';
										}else{
											tabBlock+='<li block-levels="'+blocksArr[i].id+'" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+'><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
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
						if(i == 0)
						{
							 if(blocksArr[i].id == "jalavani"){
								 tabBlock+='<div >';
									 tabBlock+='<div role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane active" id="alertStatus'+blockName+'"></div>';
									 tabBlock+='<div role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane active" id="drinking'+blockName+'"></div>';
								 tabBlock+='</div>';
							 }else{
								 tabBlock+='<div role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';
							 }
						}else{
							 if(blocksArr[i].id == "jalavani"){
								tabBlock+='<div >';
									tabBlock+='<div  role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane" id="alertStatus'+blockName+'"></div>';
									tabBlock+='<div  role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane" id="drinking'+blockName+'"></div>';
								tabBlock+='</div>';
							}else{
								tabBlock+='<div role="tabpanel" block-levels="'+blocksArr[i].id+'" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
							} 
						}
					}
				tabBlock+='</div>';
			tabBlock+='</div>';
		tabBlock+='</div>';
		tabBlock+='</div>';
		$("#"+blockId).html(tabBlock);
		//$(".nav-tabs-custom li:last-child a").removeAttr("style");
		for(var i in blocksArr)
		{
			if(blocksArr[i].id == "habitation"){
				getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table',"","","");
			}
		}
		
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
						"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
						"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
						if(divId[k].id=="habitation"){
							if(locationType !="district"){
								tableView+='<table class="table table-bordered" id="dataTable1'+locationType+divId[k].id+'">';
							}else{
								tableView+='<table class="table table-bordered" id="dataTableDis'+locationType+divId[k].id+'">';
							}
							
						}else if(divId[k].id=="performance"){
							tableView+='<table class="table table-bordered" id="dataTable'+locationType+divId[k].id+'">';
						}else if(divId[k].id=="schemeId"){
							tableView+='<table class="table table-bordered" id="dataTable3'+locationType+divId[k].id+'">';
						}else if(divId[k].id=="assestsId"){
							tableView+='<table class="table table-bordered" id="dataTable2'+locationType+divId[k].id+'">';
						}else if(divId[k].id=="waterSourceId"){
							tableView+='<table class="table table-bordered" id="dataTable4'+locationType+divId[k].id+'">';
						}
							tableView+='<thead class="text-capital">';
							
							if(divId[k].id=="habitation"){
								tableView+='<tr>';
								tableView+='<th>'+locationType+'</th>';
								if(GLtbodyArr[0] !=null && GLtbodyArr[0].statusList !=null && GLtbodyArr[0].statusList.length>0){
									for(var j in GLtbodyArr[0].statusList){
										if(divId[k].id=="habitation"){
											tableView+='<th>'+GLtbodyArr[0].statusList[j].status+'</th>';
											tableView+='<th>%</th>';	
											
										}
									}
								}
								tableView+='<th>TOTAL</th>';
								tableView+='</tr>'; 
							}else if(divId[k].id=="performance"){
								tableView+='<tr>';
								tableView+='<th rowspan="2">'+locationType+'</th>';
								tableView+='<th colspan="3" style="text-align: center;">Partially Covered<br/>Habitations Through Schemes</th>';
								tableView+='<th colspan="3" style="text-align: center;">Quality Affected<br/>Habitations Through Schemes</th>';
								tableView+='</tr>'; 
								tableView+='<tr>'; 
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='</tr>'; 
							}else if(divId[k].id=="schemeId"){
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
									
							}else if(divId[k].id=="assestsId"){
								tableView+='<tr>';
								tableView+='<th>'+locationType+'</th>';
								if(GLtbodyArr[0] !=null && GLtbodyArr[0].basicList !=null && GLtbodyArr[0].basicList.length>0){
									for(var j in GLtbodyArr[0].basicList){
										tableView+='<th>'+GLtbodyArr[0].basicList[j].assetType+'</th>';
									}
								}
								tableView+='<th>TOTAL</th>';
								tableView+='</tr>';
							}else if(divId[k].id=="waterSourceId"){
								tableView+='<tr>';
									tableView+='<th rowspan="2">'+locationType+'</th>';
									tableView+='<th colspan="3">GROUND</th>';
									tableView+='<th colspan="3">SURFACE</th>';
								tableView+='</tr>';
								tableView+='<tr>';
									tableView+='<th>SAFE</th>';
									tableView+='<th>UNSAFE</th>';
									tableView+='<th>TOTAL</th>';
									tableView+='<th>SAFE</th>';
									tableView+='<th>UNSAFE</th>';
									tableView+='<th>TOTAL</th>';
								tableView+='</tr>';
							}
						
						tableView+='</thead>';
						tableView+='<tbody>';
							if(divId[k].id=="habitation"){
								for(var i in GLtbodyArr){
									var totalCount=0;
									tableView+='<tr>';
										tableView+='<td>'+GLtbodyArr[i].locationName+'&nbsp;&nbsp;&nbsp;<i class="fa fa-question-circle getDetailsCls" aria-hidden="true" attr_location_type="'+locationType+'" attr_level_id="'+GLtbodyArr[i].goNumber+'" attr_level_name="'+GLtbodyArr[i].locationName+'" attr_parent_locationId="'+GLtbodyArr[i].parentLocationId+'"></i></td>';
									
									if(GLtbodyArr[i].statusList !=null && GLtbodyArr[i].statusList.length>0){
										for(var j in GLtbodyArr[i].statusList){
										
										if(GLtbodyArr[i].statusList[j].count !=null && GLtbodyArr[i].statusList[j].count>0){
											tableView+='<td class="hablitationClickView" attr_location_name="'+GLtbodyArr[i].locationName+'" attr_status="'+GLtbodyArr[i].statusList[j].status+'" attr_filter_value="'+GLtbodyArr[i].goNumber+'" attr_location_type="'+locationType+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_total_count = "'+GLtbodyArr[i].statusList[j].count+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].statusList[j].count+'</td>';
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].statusList[j].percentage+'</small></td>';
										}else{
											tableView+='<td> - </td>';
											tableView+='<td> - </td>';
										}
										totalCount += GLtbodyArr[i].statusList[j].count;
									}
										
									}
									tableView+='<td>'+totalCount+'</td>';
									tableView+='</tr>';
								}
							}else if(divId[k].id=="performance"){
								
								for(var i in GLtbodyArr){
									tableView+='<tr>';
									if(locationType == "state"){
										tableView+='<td>Andhra Pradesh</td>';
									}else{
										tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
									}
									if(GLtbodyArr[i].pcTarget !=null && GLtbodyArr[i].pcTarget >0){
										tableView+='<td class="kpiClickView" attr_status="PC" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_total_count = "'+GLtbodyArr[i].pcTarget+'" attr_type="targets" attr_location_name="'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].pcTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcAchivement !=null && GLtbodyArr[i].pcAchivement >0){
										tableView+='<td class="kpiClickView" attr_status="PC" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_total_count = "'+GLtbodyArr[i].pcAchivement+'" attr_location_name="'+GLtbodyArr[i].locationName+'" attr_type="achieved" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].pcAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcPercentage !=null && GLtbodyArr[i].pcPercentage >0){
										if(GLtbodyArr[i].pcPercentage < 100){
											tableView+='<td style="background-color:#FFE296">'+GLtbodyArr[i].pcPercentage.toFixed(2)+'</td>';
										}else{
											tableView+='<td style="background-color:#C7F0C5;">'+GLtbodyArr[i].pcPercentage.toFixed(2)+'</td>';
										}
										
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaTarget !=null && GLtbodyArr[i].qaTarget >0){
										tableView+='<td class="kpiClickView" attr_status="NSS" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_total_count = "'+GLtbodyArr[i].qaTarget+'" attr_type="targets" attr_location_name="'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].qaTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaAchivement !=null && GLtbodyArr[i].qaAchivement >0){
										tableView+='<td class="kpiClickView" attr_status="NSS" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_district_val="'+GLtbodyArr[i].parentLocationId+'" attr_total_count = "'+GLtbodyArr[i].qaAchivement+'" attr_type="achieved" attr_location_name="'+GLtbodyArr[i].locationName+'"  style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].qaAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaPercentage !=null && GLtbodyArr[i].qaPercentage >0){
										if(GLtbodyArr[i].qaPercentage < 100){
											tableView+='<td style="background-color:#FFE296">'+GLtbodyArr[i].qaPercentage.toFixed(2)+'</td>';
										}else{
											tableView+='<td style="background-color:#C7F0C5;">'+GLtbodyArr[i].qaPercentage.toFixed(2)+'</td>';
										}
									}else{
										tableView+='<td> - </td>';
									}
									tableView+='</tr>';
								}
								
							}else if(divId[k].id=="schemeId"){
								for(var i in GLtbodyArr){
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
								}
							}else if(divId[k].id=="assestsId"){
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
							}else if(divId[k].id=="waterSourceId"){
									for(var i in GLtbodyArr){
										tableView+='<tr>';
											tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
											if(GLtbodyArr[i].safeGroundWaterSourceCount !=null && GLtbodyArr[i].safeGroundWaterSourceCount>0){
												//tableView+='<td class="waterSourceClickView" attr_status="ground" attr_type="safe" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_total_count = "'+GLtbodyArr[i].safeGroundWaterSourceCount+'" attr_district_val="'+GLtbodyArr[i].locationId+'" attr_location_name = "'+GLtbodyArr[i].locationName+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].safeGroundWaterSourceCount+'</td>';
												tableView+='<td >'+GLtbodyArr[i].safeGroundWaterSourceCount+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											if(GLtbodyArr[i].unSafeGroundWaterSourceCount !=null && GLtbodyArr[i].unSafeGroundWaterSourceCount>0){
												//tableView+='<td class="waterSourceClickView" attr_status="ground" attr_type="un-safe" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_total_count = "'+GLtbodyArr[i].unSafeGroundWaterSourceCount+'" attr_location_name = "'+GLtbodyArr[i].locationName+'" attr_district_val="'+GLtbodyArr[i].locationId+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].unSafeGroundWaterSourceCount+'</td>';
												tableView+='<td >'+GLtbodyArr[i].unSafeGroundWaterSourceCount+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											if(GLtbodyArr[i].totalGroundWaterSourceCount !=null && GLtbodyArr[i].totalGroundWaterSourceCount>0){
												tableView+='<td >'+GLtbodyArr[i].totalGroundWaterSourceCount+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											if(GLtbodyArr[i].safeSurfaceWaterSourceCount !=null && GLtbodyArr[i].safeSurfaceWaterSourceCount>0){
												//tableView+='<td class="waterSourceClickView" attr_status="surface" attr_type="safe" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_total_count = "'+GLtbodyArr[i].safeSurfaceWaterSourceCount+'" attr_location_name = "'+GLtbodyArr[i].locationName+'"  attr_district_val="'+GLtbodyArr[i].locationId+'" style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].safeSurfaceWaterSourceCount+'</td>';
												tableView+='<td >'+GLtbodyArr[i].safeSurfaceWaterSourceCount+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											if(GLtbodyArr[i].unSafeSurfaceWaterSourceCount !=null && GLtbodyArr[i].unSafeSurfaceWaterSourceCount>0){
												//tableView+='<td class="waterSourceClickView" attr_status="surface" attr_type="un-safe" attr_filter_value="'+GLtbodyArr[i].locationId+'" attr_location_type="'+locationType+'" attr_total_count = "'+GLtbodyArr[i].unSafeSurfaceWaterSourceCount+'" attr_location_name = "'+GLtbodyArr[i].locationName+'" attr_district_val="'+GLtbodyArr[i].locationId+'"  style="cursor:pointer;text-decoration:underline">'+GLtbodyArr[i].unSafeSurfaceWaterSourceCount+'</td>';
												tableView+='<td>'+GLtbodyArr[i].unSafeSurfaceWaterSourceCount+'</td>';
											}else{
												tableView+='<td> - </td>';
											}
											if(GLtbodyArr[i].totalSurfaceWaterSourceCount !=null && GLtbodyArr[i].totalSurfaceWaterSourceCount>0){
												tableView+='<td>'+GLtbodyArr[i].totalSurfaceWaterSourceCount+'</td>';
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
						if(divId[k].id=="habitation"){
							if(locationType == 'district'){
								$("#dataTableDis"+locationType+divId[k].id).dataTable({
									"paging":   false,
									"info":     false,
									"searching": false,
									"autoWidth": true,
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
							}else{
								if(locationType != 'state'){
									$("#dataTable1"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
						}else if(divId[k].id=="performance"){
							if(locationType !="state" && locationType !="district"){
								$("#dataTable"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
										"<'row'<'col-sm-12'tr>>" +
										"<'row'<'col-sm-5'i><'col-sm-7'p>>",
									buttons: [
										{
											extend:    'csvHtml5',
											text:      '<i class="fa fa-file-text-o"></i>',
											titleAttr: 'CSV',
											title:	   locationType,
											filename:  locationType+divId[k].id+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
										},
										{
											extend:    'pdfHtml5',
											text:      '<i class="fa fa-file-pdf-o"></i>',
											titleAttr: 'PDF',
											title:	   locationType,
											filename:  locationType+divId[k].id+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
											orientation: "landscape",
											pageSize:'A3',
											customize: function (doc) {
												doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
											}
										}
									]
								});
							}
						}else if(divId[k].id=="schemeId"){
							if(locationType !="state" && locationType !="district"){
								$("#dataTable3"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
										"<'row'<'col-sm-12'tr>>" +
										"<'row'<'col-sm-5'i><'col-sm-7'p>>",
									buttons: [
										{
											extend:    'csvHtml5',
											text:      '<i class="fa fa-file-text-o"></i>',
											titleAttr: 'CSV',
											title:	   locationType,
											filename:  locationType+divId[k].id+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
										},
										{
											extend:    'pdfHtml5',
											text:      '<i class="fa fa-file-pdf-o"></i>',
											titleAttr: 'PDF',
											title:	   locationType,
											filename:  locationType+divId[k].id+' '+moment().format("DD/MMMM/YYYY  HH:MM"),
											orientation: "landscape",
											pageSize:'A3',
											customize: function (doc) {
												doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
											}
										}
									]
								});
							}
						}else if(divId[k].id=="assestsId"){
							if(locationType !="state" && locationType !="district"){
								$("#dataTable2"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
						}else if(divId[k].id=="waterSourceId"){
							if(locationType !="state" && locationType !="district"){
								$("#dataTable4"+locationType+divId[k].id).dataTable({
									"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
			var activeBlock = $(this).closest("li").attr("block-levels");
				$("[block-levels]").removeClass("active");
				$("[block-levels="+activeBlock+"]").addClass("active");
			var id = $(this).attr('tab_id');
			var blockName = $(this).attr('attr_block_name');
			var blockId = $(this).attr('attr_block_id');
			var locationArr = ['state','district','constituency','mandal']
			for(var i in locationArr)
			{
				if(locationArr[i] == 'state')
				{
					emptyCheckState();
				
				}else if(locationArr[i] == 'district')
				{
					emptyCheckDistrict();
				}else if(locationArr[i] == 'constituency')
				{
					emptyCheckConstituency();
					getAllPrrwsDistricts("chosendistVal"+locationArr[i]+"BlockId");
				}else if(locationArr[i] == 'mandal')
				{
					emptyCheckMandal();
					getAllPrrwsDistricts("chosendistVal"+locationArr[i]+"BlockId");
				}
				if(activeBlock == 'habitation')
				{
					getHabitationCoverageByStatusByLocationType(locationArr[i],blocksArr,'table',"","","");
				}else if(activeBlock == 'performance')
				{
					getKeyPerformanceIndicatorsInfo(locationArr[i],blocksArr,'table',"","","");
				}else if(activeBlock == 'jalavani')
				{
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,locationArr[i],"","",2);
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,locationArr[i],"","",2);
				}else if(activeBlock == 'assestsId')
				{
					getAssetInfoBetweenDates('table',locationArr[i],blocksArr,"","","");
				}else if(activeBlock == 'schemeId')
				{
					getSchemeWiseWorkDetails('table',locationArr[i],blocksArr,"","","");
				}else if(activeBlock == 'waterSourceId')
				{
					getWaterSourceDeatils2(locationArr[i],blocksArr,"","","","table")
				} 
			}
			/* if(blockName == "state"){
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
			} */
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
		
		function getHamletWiseIvrStatusCounts(type,divId,locationType,searchLevelValue,locationValue,searchlevelVal){
			if(type == "graph"){
				$("#drinkingWater").html(spinner);
			}else{
				$("#drinkingWater"+locationType).html(spinner);
			}
			
			var searchlevelId=searchlevelVal;
			var searchLevelValues=[];
			
			var locationTypeId=''; 
			var locationValues=[];
			
			if(locationType=="" || locationType=="state"){
				searchlevelId =searchlevelId;
				searchLevelValues.push(1);
				
				locationTypeId=2;
				locationValues.push(1);
			}else if(locationType=="district"){
				searchlevelId =searchlevelId;
				searchLevelValues.push(1);
				
				locationTypeId=3;
				locationValues=[];
			}else if(locationType=="constituency"){
				searchlevelId =searchlevelId;
				if(searchLevelValue == 0){
					searchLevelValues=[];
				}else{
					searchLevelValues.push(searchLevelValue)
				}
				
				
				locationTypeId=4;
				if(locationValue == 0){
					locationValues=[];
				}else{
					locationValues.push(locationValue)
				}
			}else if(locationType=="mandal"){
				searchlevelId =searchlevelId;
				if(searchLevelValue == 0){
					searchLevelValues=[];
				}else{
					searchLevelValues.push(searchLevelValue)
				}
				
				locationTypeId=5;
				if(locationValue == 0){
					locationValues=[];
				}else{
					locationValues.push(locationValue)
				}
			}
			var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				blockLevelId:searchlevelId,
				levelValues:searchLevelValues,
				locationTypeId:locationTypeId,
				locationValues:locationValues,
				year:yearVal
				
				
			}
			
			$.ajax({
				url: 'getHamletWiseIvrStatusCounts',
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
							buildHamletWiseIvrStatusCounts(ajaxresp);
						}else if(type == "table"){
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'drinking')
						}
					}else{
						if(type == "graph"){
							$("#drinkingWater").html("No Data Available");
						}else if(type == "table"){
							$("#drinkingWater"+locationType).html("No Data Available");
						}
					}
						
					
				}
			});
		}
	
	function getLocationWiseAlertStatusCounts(type,divId,locationType,searchLevelValue,locationValue,searchlevelVal){
		if(type == "graph"){//araa
			$("#alertStatus").html(spinner);
		}else{
			$("#alertStatus"+locationType).html(spinner);
			$("#drinking"+locationType).html(spinner);
		}
		
		
		var searchlevelId =searchlevelVal;
		var searchLevelValues=[];
		
		var locationTypeId=''; 
		var locationValues=[];
		
		if(locationType=="state"){
			searchlevelId =searchlevelId;
			searchLevelValues.push(1);
			
			locationTypeId=2;
			locationValues.push(1);
		}else if(locationType=="district"){
			searchlevelId =searchlevelId;
			searchLevelValues.push(1);
			
			locationTypeId=3;
			locationValues=[];
		}else if(locationType=="constituency"){
			searchlevelId =searchlevelId;
			if(searchLevelValue == 0){
				searchLevelValues=[];
			}else{
				searchLevelValues.push(searchLevelValue)
			}
			
			
			locationTypeId=4;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
			
		}else if(locationType=="mandal"){
			searchlevelId =searchlevelId;
			if(searchLevelValue == 0){
				searchLevelValues=[];
			}else{
				searchLevelValues.push(searchLevelValue)
			}
			
			locationTypeId=5;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
		}
		var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
		var json = {
		  deptId:49,
		  fromDate:glStartDate,
		  toDate:glEndDate,
		  year:yearVal,
		  locationTypeId:locationTypeId,
		  locationValues:locationValues,
		  searchLevelId:searchlevelId,
		  searchLvlVals:searchLevelValues
		}
		$.ajax({
			url: 'getLocationWiseAlertStatusCounts',
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
						buildAlertDetailsOfCategoryByStatusWise(ajaxresp);
					}else{
						buildTableForHabitationCoverage(ajaxresp,locationType,divId,'alertStatus')
					}
					
				}else{
					if(type == "graph"){
						$("#alertStatus").html("No Data Available");
					}else{
						$("#alertStatus"+locationType).html("No Data Available");
						$("#drinking"+locationType).html("No Data Available");
					}
					
				}
				
			}
		});
	}
	
	function buildHamletWiseIvrStatusCounts(result){
		
		if(result !=null && result.length>0){
			var dataArr=[];
			var totalCount=0;
			for(var i in result){
				if(result[i].statusList !=null && result[i].statusList.length>0){
					for(var j in result[i].statusList){
						dataArr.push(result[i].statusList[j].count)
						totalCount=totalCount+result[i].statusList[j].count;
					}
				}
			}
		var colors = ['#0FBE08','#FFBA00','#FF0909']
		var id = 'drinkingWater';
		var type = {
			type: 'column',
			backgroundColor:'transparent'
		};
		var title = { text: ''
			/* text: 'Habitation',
			align:'left',
			 style: {
				 color: '#000',
				 font: 'bold 16px "Lato", sans-serif'
			  } */
		};
	
		var legend = {
			enabled: false
		};
		var yAxis = {
			title: {
				text: null
			},
		};
		var xAxis = {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			categories: ['SATISFIED','PARTIALLY SATISFIED','NOT SATISFIED'],
			labels: {
				useHTML:true,
				formatter: function() {
					return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
					
				},
				
			}
			
		};
		
		var plotOptions ={ column: {
				colorByPoint: true
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
				$("#financialYearId").val(year);
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
		var yearId = $(this).val();
		glStartDate="";
		glEndDate="";
		if(yearId == 0){
			glStartDate="01-01-1977";
			glEndDate="01-01-2027";
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
	
	
	function getWaterSourceDeatils2(locationType,divId,filterType,filterValue,districtValue,type){//araV
		
		if(type == "graph"){
			$("#waterSources").html(spinner);
		}else{
			for(var k in divId){
				$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
			}
		}
		
		var json = {
			year:"2017",
			locationType:locationType,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			filterType:filterType,
			filterValue:filterValue,
			districtValue:districtValue
		}
		$.ajax({                
			type:'POST',    
			url: 'getWaterSourceDeatils2',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(ajaxresp){
				if(ajaxresp !=null && ajaxresp.length>0){
					if(type == "graph"){
						buildWaterSourceStateLevelGraphView(ajaxresp);
					}
					buildTableForHabitationCoverage(ajaxresp,locationType,divId,'habitations');
				}else{
					
					for(var k in divId){
						$("#"+locationType+"BlockId"+divId[k].id).html("No Data Available");
					}
					
				}
		});
	}
	
	function buildWaterSourceStateLevelGraphView(ajaxresp){
		if(ajaxresp !=null && ajaxresp.length>0){//ara4
			var safeArr = [];
			var unSafeArr = [];
			for(var i in ajaxresp){
				safeArr.push({"y":ajaxresp[i].safeGroundWaterSourceCount})
				safeArr.push({"y":ajaxresp[i].safeSurfaceWaterSourceCount})
				unSafeArr.push({"y":ajaxresp[i].unSafeGroundWaterSourceCount})
				unSafeArr.push({"y":ajaxresp[i].unSafeSurfaceWaterSourceCount})
			}
			$("#waterSources").highcharts({
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
						categories: ['GROUND','SURFACE']
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
								this.series.name + ': ' + this.y;
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
										return '<span>'+this.y+'</span>';
									}
									
								}
							}
						}
					},
					series: [{
							name: 'SAFE',
							data: safeArr,
							color:'#14BBAE'
						}, {
							name: 'UNSAFE',
							data: unSafeArr,
							color:'#FC5E57'
						}]
				});
		}else{
			$("#waterSourcesTtlValues").hide()
			$("#waterSources").html("No Data Available");
		}
	}
	$(document).on("click",".ivrStatusViewCls",function(){
		$("#modalHablitationTable").html('');
		$("#modalAlertTable").html('');
		$("#modalAssetsTable").html('');
		$("#modalWaterSourceTable").html('');
		$("#modalIvrStatusTable").html('');
		$("#modalKpiTable").html('');
		$("#modalSchemsTable").html('');
		var status = $(this).attr("attr_status_name");
		var locationType=$(this).attr("attr_location_type");
		var locationValue=$(this).attr("attr_location_value");
		var totalCount=$(this).attr("attr_total_count");
		var locationName=$(this).attr("attr_location_name");
		var startIndex=0;
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;&nbsp;Overview</h4>");
		
		getLocationWiseHamletIvrList(status,locationType,locationValue,totalCount);
	});
	$(document).on("click",".alertStatusViewCls",function(){
		$("#modalHablitationTable").html('');
		$("#modalAlertTable").html('');
		$("#modalAssetsTable").html('');
		$("#modalWaterSourceTable").html('');
		$("#modalIvrStatusTable").html('');
		$("#modalKpiTable").html('');
		$("#modalSchemsTable").html('');
		var status = $(this).attr("attr_status_id");
		var locationType=$(this).attr("attr_location_type");
		var locationValue=$(this).attr("attr_location_value");
		var totalCount=$(this).attr("attr_total_count");
		var statusName=$(this).attr("attr_status");
		var locationName=$(this).attr("attr_location_name");
		var startIndex=0;
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+statusName+"&nbsp;&nbsp;Overview</h4>");
		
		getAlertsOfCategoryByStatusWise(status,locationType,locationValue,0,startIndex,totalCount);
	});
	$(document).on("click",".hablitationClickView",function(){
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
		var districtVal=$(this).attr("attr_district_val");
		var totalCount=$(this).attr("attr_total_count");
		var locationName=$(this).attr("attr_location_name");
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;&nbsp;Overview</h4>");
		var startIndex=0;
		getHabitationDetailsByStatusByLocationType(status,locationType,locationValue,districtVal,startIndex,totalCount);
		
		
	});
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
	$(document).on("click",".waterSourceClickView",function(){
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
		var safeUnsafeType=$(this).attr("attr_type");
		var districtVal=$(this).attr("attr_district_val");
		$("#modalHablitationDivId").modal('show');
		//$("#modalHabliHeadingId").html(status+" &nbsp;&nbsp;Wise Details");
		var startIndex=0;
		getWaterSourceDeatilsLocationWise(status,safeUnsafeType,locationType,locationValue,districtVal,startIndex,totalCount);
		
		
	});
	$(document).on("click",".schemsClickView",function(){
		$("#modalHablitationTable").html('');
		$("#modalAlertTable").html('');
		$("#modalAssetsTable").html('');
		$("#modalWaterSourceTable").html('');
		$("#modalIvrStatusTable").html('');
		$("#modalKpiTable").html('');
		$("#modalSchemsTable").html('');
		$(".paginationId").html("");
		
		var status = $(this).attr("attr_status");
		var totalCount=$(this).attr("attr_total_count");
		var workStatus=$(this).attr("attr_type");
		var locationValue = $(this).attr("attr_filter_value");
		var locationType=$(this).attr("attr_location_type");
		var districtVal=$(this).attr("attr_district_val");
		var locationName=$(this).attr("attr_location_name");
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;"+"("+workStatus+")&nbsp;&nbsp;Overview</h4>");
		var startIndex=0;
		getOnclickWorkSchemsDetails(status,workStatus,totalCount,locationValue,locationType,
		districtVal);
		
		
	});
	$(document).on("click",".kpiClickView",function(){
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
		var districtVal=$(this).attr("attr_district_val");
		var workStatus=$(this).attr("attr_type");
		var totalCount=$(this).attr("attr_total_count");
		var locationName=$(this).attr("attr_location_name");
		$("#modalHablitationDivId").modal('show');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+workStatus+"&nbsp;&nbsp;Overview</h4>");
		var startIndex=0;
		getOnclickTargetsAcheievementsDetails(status,workStatus,locationType,locationValue,districtVal,startIndex,totalCount);
		
		
	});
	
	//KPI ON CLICK
	function getOnclickTargetsAcheievementsDetails(status,workStatus,locationType,locationValue,districtVal,startIndex,totalCount){
		$("#modalKpiTable").html(spinner);
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
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			year:yearVal,
			startValue:startIndex,
			endValue:"10",
			districtValue:districtValStr,
			filterType:filterType,
			filterValue:filterValue,
			workStatus:workStatus,
			assetType:status
		}
		$.ajax({                
			type:'POST',    
			url: 'getOnclickTargetsAcheievementsDetails',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildOnclickTargetsAcheievementsDetails(result,status,workStatus,locationType,locationValue,districtVal,startIndex,totalCount);
			}else{
				$(".paginationId").html("");
				$("#modalKpiTable").html('No Data Available');
			}
			
			
		});
	}
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
	//waterSource ON CLICK
	function getWaterSourceDeatilsLocationWise(status,safeUnsafeType,locationType,locationValue,districtVal,startIndex,totalCount){
		
		$("#modalWaterSourceTable").html(spinner);
		
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
				startValue:startIndex,
				endValue:"10",
				filterType:filterType,
				filterValue:filterValue,
				districtValue:districtValStr,
				type:safeUnsafeType,
				status:status
				}
		
		$.ajax({                
			type:'POST',    
			url: 'getWaterSourceDeatilsLocationWise',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildWaterSourceDeatilsLocationWise(result,status,safeUnsafeType,locationType,locationValue,districtVal,startIndex,totalCount);
			}else{
				$(".paginationId").html("");
				$("#modalWaterSourceTable").html('No Data Available');
			}
			
		});
	}
	
	//Assets ON CLICK
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
	
	//IVRSATATUSCLICK
	function getLocationWiseHamletIvrList(status,locationType,locationValue,totalCount){
		$("#modalIvrStatusTable").html(spinner);
		var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
		var locationValues=[];	
		if(locationType=="state"){
			locationTypeId=2;
			locationValues.push(1);
		}else if(locationType=="district"){
			locationTypeId=3;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
		}else if(locationType=="constituency"){
			locationTypeId=4;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
			
		}else if(locationType=="mandal"){
			locationTypeId=5;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
		}	
		var jsObj={
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:yearVal,
				locationTypeId:locationTypeId,
				locationValues:locationValues,
				status:status
		}
		 $.ajax({
	      type : "POST",
	      url : "getLocationHamletIvrStatusList",
	      dataType : 'json',
	     data : JSON.stringify(jsObj),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
	    }).done(function(result){
			if(result !=null && result.length>0){
				buildLocationWiseHamletIvrList(result,status,locationType,locationValue,totalCount);
			}else{
				$("#modalIvrStatusTable").html('No Data Available');
			}	
	    	
		});	
	}
	//alerts Status onclick
	function getAlertsOfCategoryByStatusWise(status,locationType,locationValue,districtVal,startIndex,totalCount){
	$("#modalAlertTable").html(spinner);
		var yearVal="";
			var financialVal =$("#financialYearId").val();
			if(financialVal != 0){
				 yearVal=financialVal;
			}
		var locationTypeId=''; 
		var locationValues=[];
		var statusIds=[];
		var stringIds = status,
			strx   = stringIds.split(',');
			statusIds = statusIds.concat(strx);
		if(locationType=="state"){
			locationTypeId=2;
			locationValues.push(1);
		}else if(locationType=="district"){
			locationTypeId=3;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
		}else if(locationType=="constituency"){
			locationTypeId=4;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
			
		}else if(locationType=="mandal"){
			locationTypeId=5;
			if(locationValue == 0){
				locationValues=[];
			}else{
				locationValues.push(locationValue)
			}
		}	
		var json = {
			
		  deptId:49,
		  fromDate:glStartDate,
		  toDate:glEndDate,
		  year:yearVal,
		  locationTypeId:locationTypeId,
		  locationValues:locationValues,
		  statusIds : statusIds,
		  startIndex:startIndex,
		  endIndex:10		 
		}
		$.ajax({
			url: 'getAlertsOfCategoryByStatusWise',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp){
				if(ajaxresp !=null && ajaxresp.length>0){
					buildHabitationDetailsByStatusByLocationType(ajaxresp,'alert_click',status,locationType,locationValue,districtVal,startIndex,totalCount);
				}else{
					$(".paginationId").html("");
					$("#modalAlertTable").html('No Data Available');
				}
				
			}
		});
	}

	//habilationOneTAb
	function getHabitationDetailsByStatusByLocationType(status,locationType,locationValue,districtVal,startIndex,totalCount){
		
		$("#modalHablitationTable").html(spinner);
		$(".paginationId").css('display','none');
		var statusArr = [];
		statusArr.push(status);
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
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			statusList:statusArr,
			year:yearVal,
			startValue:startIndex,
			endValue:"10",
			districtValue:districtValStr,
			filterType:filterType,
			filterValue:filterValue
		}
		$.ajax({                
			type:'POST',    
			url: 'getHabitationDetailsByStatusByLocationType',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildHabitationDetailsByStatusByLocationType1(result,'habitation_Click',status,locationType,locationValue,districtVal,startIndex,totalCount);
				$(".paginationId").css('display','block')
				
			}else{
				$(".paginationId").html("");
				$("#modalHablitationTable").html('No Data Available');
			}
			
		});
	}
	//AlertStatusbuild
	function buildHabitationDetailsByStatusByLocationType(result,type,status,locationType,locationValue,districtVal,startIndex,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableAlertTable">';
			tableView+='<thead>';
			tableView+='<tr>';
				tableView+='<th>TITLE</th>';
				tableView+='<th>SOURCE</th>';
				tableView+='<th>IMPACTLEVEL</th>';
				tableView+='<th>STATUS</th>';
				tableView+='<th>CREATED DATE</th>';
			tableView+='</tr>';
				
			tableView+='</thead>';
			tableView+='<tbody>';
			for(var i in result){
				tableView+='<tr>';
						tableView+='<td>'+result[i].title+'</td>';
						tableView+='<td>'+result[i].source+'</td>';
						tableView+='<td>'+result[i].alertLevel+'</td>';
						tableView+='<td>'+result[i].status+'</td>';
							tableView+='<td>'+result[i].createdDate+'</td>';
				tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalAlertTable").html(tableView);
		$("#dataTableAlertTable").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,			
			"order": [ 0, 'desc' ],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
						getAlertsOfCategoryByStatusWise(status,locationType,locationValue,districtVal,num,totalCount);
					}
					
				});
			}
		$(".prev,.next").css("width","70px !important");	
	} 
	//habilationOneTAbbuild
	function buildHabitationDetailsByStatusByLocationType1(result,type,status,locationType,locationValue,districtVal,startIndex,totalCount){
		
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableHablitationTable">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>HABITATIONS NAME</th>';
					tableView+='<th>HABITATIONS CODE</th>';
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
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalHablitationTable").html(tableView);
		//$(".paginationId").html("");
		$("#dataTableHablitationTable").dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"order": [ 0, 'desc' ],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
						getHabitationDetailsByStatusByLocationType(status,locationType,locationValue,districtVal,num,totalCount);
							
					}
					
				});
			}
		$(".prev,.next").css("width","70px !important");	
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
	//waterSorce Build
	function buildWaterSourceDeatilsLocationWise(result,status,safeUnsafeType,locationType,locationValue,districtVal,startIndex,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableWaterSource"> ';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>HABITATION NAME</th>';
					tableView+='<th>HABITATION CODE</th>';
					
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
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalWaterSourceTable").html(tableView);
		$("#dataTableWaterSource").dataTable({
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
	//schems build
	function buildOnclickWorkSchemsDetails(result,status,workStatus,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableSchems">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>Work CODE</th>';
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
						
						
						//
						//tableView+='<td>'+result[i].habitationCode+'</td>';
						
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalSchemsTable").html(tableView);
		$("#dataTableSchems").dataTable({
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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
	//ivr buld
	function buildLocationWiseHamletIvrList(result,status,locationType,locationValue,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableivr">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>PANCHAYAT</th>';
					tableView+='<th>HABITATIONS NAME</th>';
					tableView+='<th>HABITATIONS CODE</th>';
				tableView+='</tr>';
				
			tableView+='</thead>';
			tableView+='<tbody>';
			for(var i in result){
				tableView+='<tr>';
						tableView+='<td>'+result[i].districtName+'</td>';
						tableView+='<td>'+result[i].constituencyName+'</td>';
						tableView+='<td>'+result[i].mandalName+'</td>';
						tableView+='<td>'+result[i].panchayat+'</td>';
						tableView+='<td>'+result[i].habitationName+'</td>';
						tableView+='<td>'+result[i].habitationCode+'</td>';
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalIvrStatusTable").html(tableView);
		
		$("#dataTableivr").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"order": [ 0, 'desc' ],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
	
	// kpi build
	function buildOnclickTargetsAcheievementsDetails(result,status,workStatus,locationType,locationValue,districtVal,startIndex,totalCount){
		var tableView='';
		tableView+='<table class="table table-bordered" id="dataTableKpi">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>HABITATIONS NAME</th>';
					tableView+='<th>HABITATIONS CODE</th>';
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
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
		$("#modalKpiTable").html(tableView);
		$("#dataTableKpi").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"order": [ 0, 'desc' ],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
						getOnclickTargetsAcheievementsDetails(status,workStatus,locationType,locationValue,districtVal,num,totalCount)
							
					}
					
				});
			}
		$(".prev,.next").css("width","70px !important");	
	}

	function getIHHLOverviewData(type){
		if(type == "abstract"){
			$("#swatchBharatDivId").html(spinner);
		}else{
			$("#swatchBharatOvervwIHHL").html(spinner);
		}
		
		var json = {
				fromMonth:"201704",
				toMonth:"201707",
				location:"state",
				locationId:"01"
				}
		
		$.ajax({                
			type:'POST',    
			url: 'getIHHLOverviewData',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null){
				if(type == "abstract"){
					buildIHHLOverviewDataAbstractOverView(result);
				}else{
					
					builOverViewBlock(result,"IHHL");
				}
				
			}else{
				if(type == "abstract"){
					$("#swatchBharatDivId").html("NO DATA AVAILABLE");
				}else{
					$("#swatchBharatOvervwIHHL").html("NO DATA AVAILABLE");
				}
				
			}
		});
	}
	
	function buildIHHLOverviewDataAbstractOverView(result){
		if(result.subList1 !=null && result.subList1.length>0){
				var dataArr=[];
				var totalCount=0;
				var ComPerc=0;
				for(var i in result.subList1){
					dataArr.push(result.subList1[i].target)
					dataArr.push(result.subList1[i].completed)
					totalCount =result.subList1[i].target+result.subList1[i].completed;
					ComPerc =result.subList1[i].completed*100/result.subList1[i].target;
				}
			var colors = ['#FC5049','#14BAAD']
			var id = 'swatchBharatDivId';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
			};
		
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: ['TARGET','COMPLETED'],
				labels: {
					useHTML:true,
					formatter: function() {
						return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						
					},
					
				}
				
			};
			
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				useHTML:true,
				formatter: function () {
						if(this.x != "TARGET"){
								var pcnt = ComPerc;
								return '<b>' + this.x + '</b><br/>' +
								this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
						}else{
								return '<b>' + this.x + '</b><br/>' +
								this.y+'';
						}
						
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
						if(this.x != "TARGET"){
							var pcnt = ComPerc;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}else{
							return '<span>'+this.y+'';
						}
					}
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
	}
	
	$(document).on("click",".overViewDtlsSwatchBharatCls",function(){
		//window.open('swatchBharatIHHLInfo','_blank');
		window.open('mgnregsIHHLInfo','_blank');
		/* $("#sbModalDivId").modal('show');
		var str='';
		str+='<div id="overViewBlockId"></div>';
		str+='<div id="levelWiseSwatchBharatId"></div>';
		
		$("#sbDataDivId").html(str);
		//$("#sbmIhhlBlockId").html(str);
		
		overviewData("IHHL");
		levelWiseSBData("IHHL");
		/* $('html,body').animate({
			scrollTop: $("#overViewBlockId").offset().top},
        'slow'); */
	});
	
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
function levelWiseSBData(divId)
{
	var collapse='';
		collapse+='<section>';
			collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in levelWiseSBArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseSBArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
								}
								if(levelWiseSBArr[i] == "state" || levelWiseSBArr[i] == "district" || levelWiseSBArr[i] == "constituency")
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview - (SBM- '+divId+') - All Amounts in Lakhs.</h4>';
								else
									collapse+='<h4 class="panel-title text-capital">'+levelWiseSBArr[i]+' level overview - (SBM- '+divId+') - All Amounts in Rupees.</h4>';
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+levelWiseSBArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
			collapse+='</div>';
			collapse+='</section>';
	
	if(divId == "IHHL"){
		$("#levelWiseSwatchBharatId").html(collapse);
	}else if(divId == "Payments"){
		$("#levelWiseSwatchBharatPaymentsId").html(collapse);
	}	
	
	
	setTimeout(function(){ 
		for(var i in levelWiseSBArr){
			if(levelWiseSBArr[i] == "state"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("state")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("state")
				}
				
			}else if(levelWiseSBArr[i] == "district"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("district")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("district")
				}
				
			}else if(levelWiseSBArr[i] == "constituencies"){
				if(divId == "IHHL"){
					getIHHLlocationLvlWiseData("constituencies")
				}else if(divId == "Payments"){
					getSBPaymentsLvlWiseData("constituency")
				}
				
			}
		}	
	
	}, 1500);
	
}
$(document).on("click",".IHHLmandal",function(){
		getIHHLlocationLvlWiseData("mandal")
});
$(document).on("click",".IHHLpanchayat",function(){
	getIHHLlocationLvlWiseData("panchayat")
});	
$(document).on("click",".Paymentsmandal",function(){
		getSBPaymentsLvlWiseData("mandal")
});
$(document).on("click",".Paymentspanchayat",function(){
	getSBPaymentsLvlWiseData("panchayat")
});	
function getIHHLlocationLvlWiseData(locationType){
		$("#IHHL"+locationType).html(spinner);
		
		var json = {
			fromMonth:"201704",
			toMonth:"201707",
			location:"state",
			locationId:"01",
			subLocation :locationType
				
		}
		$.ajax({                
			type:'POST',    
			url: 'getIHHLlocationLvlWiseData',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildIHHLlocationLvlWiseData(result,locationType);
			}
		});
	}
	
		function buildIHHLlocationLvlWiseData(ajaxresp,locationType){
			if(ajaxresp != null && ajaxresp.length > 0){
					var str = '';
					str+='<div class="table-responsive">';
						str+='<table class="table table-bordered dataTable'+locationType+'">';
							str+='<thead class="text-capital">';
								if(locationType == "state"){
									str+='<th class="text-capital">'+locationType+'</th>';
								}
								else if(locationType == "district"){
									str+='<th class="text-capital">district</th>';
								}
								else if(locationType == "constituencies"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
								}
								else if(locationType == "mandal"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
									str+='<th class="text-capital">mandal</th>';
								}
								else if(locationType == "panchayat"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
									str+='<th class="text-capital">mandal</th>';
									str+='<th class="text-capital">panchayat</th>';
								}
								str+='<th class="text-capital">TARGET</th>';
								str+='<th class="text-capital">Grounded</th>';
								str+='<th class="text-capital">Not-Grounded</th>';
								str+='<th class="text-capital">In Progress</th>';
								str+='<th class="text-capital">Completed</th>';
								str+='<th class="text-capital">Achivement Percentage</th>';
								
							str+='</thead>';
							str+='<tbody>';
								for(var i in ajaxresp){
									str+='<tr>';
										if(locationType == "state"){
											str+='<td class="text-capital">'+locationType+'</td>';
										}
										else if(locationType == "district"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
										}
										else if(locationType == "constituencies"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
										}
										else if(locationType == "mandal"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
										}
										else if(locationType == "panchayat"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
										}
										str+='<td class="text-capital">'+ajaxresp[i].target+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].grounded+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].noTGrounded+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].completed+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].completed+'</td>';	
										
										if(ajaxresp[i].percentage >=80){
											str+='<td class="text-capital" style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
										}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
												str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
										}else if(ajaxresp[i].percentage <50){
											str+='<td class="text-capital" style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
										}
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
					$("#IHHL"+locationType).html(str);
					
					if(locationType !="state" || locationType !="district"){
						$(".dataTable"+locationType).dataTable({
							"iDisplayLength": 20,
							"aaSorting": [],
							"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
							"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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


function getSBPaymentsAbstract(){
		
		$("#swatchBharatPaymentsDivId").html(spinner);
		var json = {
			//fromDate:"201704",
			//toDate:"201708",
			location:"state",
			locationId:"01",
			subLocation :"state"
				
		}
		$.ajax({                
			type:'POST',    
			url: 'getSBPaymentsAbstract',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null){
				buildSBPaymentsAbstract(result);
			}
		});
	}
		
	function buildSBPaymentsAbstract(result){
		
		var dataArr=[];
		
		dataArr.push({"y":result.ttlAmt,"extra":result.totalAmount});
		dataArr.push({"y":result.paidAmt,"extra":result.paidAmount});
		dataArr.push({"y":result.pndgAmt,"extra":result.pendingAmount});
		var colors = ['#14BBAE'];
		var id = 'swatchBharatPaymentsDivId';
			var type = {
				type: 'bar',
				backgroundColor:'transparent'
			};
			var legend = {
				enabled: false
			};
			
			var title = {
				text: '',
				align:'left',
				 style: {
					 color: '#777',
					 font: 'bold 8px "Lato", sans-serif'
				  } 
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
				categories: ["TOTAL","PAID","PENDING"]
				
			};
			var plotOptions ={ bar: {
					colorByPoint: true
				}};
			var tooltip = {
				useHTML:true,
				formatter: function () {
					//var pcnt = (this.y / totalCount) * 100;
					return '<b>' + this.x + ' Amount</b><br/>' +
						this.point.extra;
				}
			};

			var data = [{
				name: '',
				data: dataArr,

				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'canter',
					formatter: function() {
							//var pcnt = (this.y / totalCount) * 100;
							//return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
							return '<span>'+this.point.extra+'</span>';
					} 
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
	}
	$(document).on("click",".overViewDtlsSwatchBharatPaymentCls",function(){
		window.open('swatchBharatPaymentsInfo','_blank');
		/* $("#sbPaymentModalDivId").modal('show');
		var str='';
			str+='<div id="levelWiseSwatchBharatPaymentsId"></div>';
		$("#sbPaymentDataDivId").html(str);
		
		levelWiseSBData("Payments")
		//$('html,body').animate({
		//	scrollTop: $("#overViewBlockId").offset().top},
        //'slow'); */
	});
	
	function getSBPaymentsLvlWiseData(locationType){
		$("#Payments"+locationType).html(spinner);
		
		var json = {
			//fromDate:"201704",
			//toDate:"201708",
			location:"state",
			locationId:"01",
			subLocation :locationType
				
		}
		$.ajax({                
			type:'POST',    
			url: 'getSBPaymentsLevelsWiseData',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null && result.length>0){
				buildSBPaymentsLvlWiseData(result,locationType);
			}
	});
}
	
	
	function buildSBPaymentsLvlWiseData(ajaxresp,locationType){
			if(ajaxresp != null && ajaxresp.length > 0){
					var str = '';
					str+='<div class="table-responsive">';
						str+='<table class="table table-bordered dataTablePayments'+locationType+'">';
							str+='<thead class="text-capital">';
								if(locationType == "state"){
									str+='<th class="text-capital">'+locationType+'</th>';
								}
								else if(locationType == "district"){
									str+='<th class="text-capital">district</th>';
								}
								else if(locationType == "constituency"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
								}
								else if(locationType == "mandal"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
									str+='<th class="text-capital">mandal</th>';
								}
								else if(locationType == "panchayat"){
									str+='<th class="text-capital">district</th>';
									str+='<th class="text-capital">constituency</th>';
									str+='<th class="text-capital">mandal</th>';
									str+='<th class="text-capital">panchayat</th>';
								}
								str+='<th class="text-capital">TOTAL FTO</th>';
								str+='<th class="text-capital">Total Amount</th>';
								str+='<th class="text-capital">Paid Fto</th>';
								str+='<th class="text-capital">Paid Amount</th>';
								str+='<th class="text-capital">Pending Fto</th>';
								str+='<th class="text-capital">Pending AMount</th>';
								
							str+='</thead>';
							str+='<tbody>';
								for(var i in ajaxresp){
									str+='<tr>';
										if(locationType == "state"){
											str+='<td class="text-capital">'+locationType+'</td>';
										}
										else if(locationType == "district"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
										}
										else if(locationType == "constituency"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
										}
										else if(locationType == "mandal"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
										}
										else if(locationType == "panchayat"){
											str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
											str+='<td class="text-capital">'+ajaxresp[i].panchayt+'</td>';
										}
										str+='<td class="text-capital">'+ajaxresp[i].totalFTO+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].totalAmount+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].paidFTO+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].paidAmount+'</td>';										
										str+='<td class="text-capital">'+ajaxresp[i].pendingFTO+'</td>';	
										str+='<td class="text-capital">'+ajaxresp[i].pendingAmount+'</td>';	
										
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
					if(locationType == "constituency"){
						$("#Paymentsconstituencies").html(str);
					}else{
						$("#Payments"+locationType).html(str);
					}
					if(locationType !="state" || locationType !="district"){
						$(".dataTablePayments"+locationType).dataTable({
							"iDisplayLength": 20,
							"aaSorting": [],
							"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
							"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
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

$(document).on("click",".overViewDtlsSwatchBharatMissionCls",function(){
		window.open('swachhBharatMissionIHHL','_blank');
});
function getSwachhBharatMissionOverviewDtls(){
	$("#swatchBharatMissionDivId").html(spinner);
	
	var json = {
		fromDate:"",
		toDate:"",
		location:"state",
		locationId:"-1",
		subLocation:"state"
	}
	$.ajax({                
		type:'POST',    
		url: 'getSwachhBharatMissionOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			return buildSwachhBharatMissionOverviewDtls(result);
		}
	});	
	
	function buildSwachhBharatMissionOverviewDtls(result){
		if(result !=null){
			var dataArr=[];
			dataArr.push(result.target)
			dataArr.push(result.completed)
			var colors = ['#FC615E','#13B9AC']
			var id = 'swatchBharatMissionDivId';
			var type = {
				type: 'column',
				backgroundColor:'transparent'
			};
			var title = { text: ''
				/* text: 'Habitation',
				align:'left',
				 style: {
					 color: '#000',
					 font: 'bold 16px "Lato", sans-serif'
				  } */
			};
		
			var legend = {
				enabled: false
			};
			var yAxis = {
				title: {
					text: null
				},
			};
			var xAxis = {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				categories: ['TARGET','COMPLETED'],
				labels: {
					useHTML:true,
					formatter: function() {
						return '<p><span class="roundClr" style="background-color:'+globalStatusObj[this.value]+'"></span>&nbsp;&nbsp;&nbsp;'+this.value+'</p>';
						
					},
					
				}
				
			};
			
			var plotOptions ={ column: {
					colorByPoint: true
				}};
			var tooltip = {
				useHTML:true,
				formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.y;
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
						return '<span>'+this.y+'</span>';
					}
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
	}
}