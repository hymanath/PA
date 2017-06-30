 //Angular Start
		var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
		var glEndDate = moment().add(10, 'years').endOf('year').format("DD-MM-YYYY");
		var globalStatusObj={"QA":"#494949","PC":"#FC5049","FC":"#14BAAD","Ground":"#14BAAD","Surface":"#FC5049","SAFE":"#14BAAD","UN-SAFE":"#FC5049",
		"SINGAL VILLAGE":"#14BAAD","MULTI VILLAGE":"#FC5049","physicalTestCount":"#14BAAD","bacterialTestCount":"#FC5049",
		"Completely Satisfied":"#0FBE08","Not Satisfied":"#FF0909","Partially Satisfied":"#FFBA00","SATISFIED":"#0FBE08","SOSO":"#FFBA00","NOT SATISFIED":"#FF0909"}
		var blocksArr = [{name:'Coverage Status Of Habitation',id:'habitation',img:'coverage_status.png'},
						 {name:'Key Performance',id:'performance',img:'key_performance.png'},
						 {name:'<p><span><img src="Assests/icons/alert_status.png"/> Alert Status Jalavani</span> <span style="margin-left:6px;">&</span> <span style="margin-left:6px;"><img src="Assests/icons/driking.png"/ style="margin-top: -15px;"> <span style="margin-left: 2px;"> Drinking Water Satisfaction Levels</span></span></p>',id:'jalavani',img:'alert_status.png'},
						 {name:'Plan Of Action for Stressed Habitations <br><small>Water Budget has to be prepared for all habitations</small>',id:'planAction',img:'plan_action.png'}];
		var alertStatusBlockArr = [{name:'Alert Status Jalavani',id:'alertStatus'},{name:'Drinking Water Satisfaction Levels',id:'drinking'}];
		var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
		getAllFiniancialYears();
		function onloadCalls(){
			tabBlocks('stateBlockId','state');
			tabBlocks('districtBlockId','district');
			tabBlocks('constituencyBlockId','constituency');
			tabBlocks('mandalBlockId','mandal');
			responsiveTabs();
			getHabitationCoverageByStatusByLocationType('state','','graph');
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails();
			getAssetInfoBetweenDates();
			getWaterSourceInfo();
			getKeyPerformanceIndicatorsInfo('state','','graph');
			getPlanofActionForStressedHabitations();
			getStressedHabitationsInfoByLocationType();//on click ? call
			getAlertDetailsOfCategoryByStatusWise('graph','','');
			getAlertFeedbackStatusDetails();
			getLocationBasedOnSelection("district",2017,"","","");//get dist/const/mandal
			getHamletWiseIvrStatusCounts('graph','','');
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
		
		function getHabitationCoverageByStatusByLocationType(locationType,divId,type)
		{
			if(type=="graph"){
				$("#totalValues").html(spinner);
				$("#habitation").html(spinner);
			}else{
				for(var k in divId){
					$("#"+locationType+"BlockId"+divId[k].id).html(spinner);
				}
			}
			
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				locationType:locationType,
				year:financialVal,
				stressedHabitationYear:financialVal,
				filterType:"",
				filterValue:""
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
					if(response[i].statusList[j].status != "NC"){
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
		}
		
		function buildChartForHabitationCoverageStatus(response){
			var dataArr = [];
			var totalCount=0;
			var statusNamesArr=[];
			for(var i in response){
			  for(var j in response[i].statusList){
					if(response[i].statusList[j].status != "NC"){
						var tempArr = [];
						statusNamesArr.push(response[i].statusList[j].status);
						tempArr.push(parseInt(response[i].statusList[j].count));
						dataArr.push(tempArr);
						totalCount=totalCount+parseInt(response[i].statusList[j].count);
					}
				}
			}
			var colors = ['#14BAAD']
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
				categories: statusNamesArr
				
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
					align: 'canter',
					formatter: function() {
							var pcnt = (this.y / totalCount) * 100;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
					} 
				}
			}];
			highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip,colors,title);
			
		}
		function getLabTestDetails()
		{
			$("#overView").html(spinner);
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:financialVal
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
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:financialVal
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
					if(ajaxresp !=null ){
						if(ajaxresp.safeMLD !=null && ajaxresp.safeMLD >0 && ajaxresp.unsafeMLD !=null && ajaxresp.unsafeMLD >0){
							$("#levelSupplyTtlValues").show()
							buildHabitationSupplyDetails(ajaxresp);
						}else{
							$("#levelSupplyTtlValues").hide()
							$("#levelOfSupply1").html("No Data Available");
						}
						
					}else{
						$("#levelSupplyTtlValues").hide()
						$("#levelOfSupply1").html("No Data Available");
					}
				}
			});
		}
		
		function buildHabitationSupplyDetails(result){
			var dataArr = [];
			var subDataArr1=[],subDataArr2=[];
			var statusNamesArr=[];
			var totalCount=0;	
				statusNamesArr.push("SAFE");
				subDataArr1.push(result.safeMLD);
				dataArr.push(subDataArr1);
				
				statusNamesArr.push("UN-SAFE");
				subDataArr2.push(result.unsafeMLD);
				dataArr.push(subDataArr2);
				totalCount = result.safeMLD+result.unsafeMLD;
				
				$("#levelSupplyTtlValues").html("TOTAL:"+totalCount)
				var colors = ['#14BAAD','#FC5049']
				var id = 'levelOfSupply1';
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
					data:dataArr,

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
		
		function getSchemesDetails(){
			$("#schemes").html(spinner);
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				year:financialVal
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
		function getSchemeWiseWorkDetails(){
			$("#habitationWorks").html(spinner);
			var financialVal =$("#financialYearId").val();
			var json = {
					fromDateStr:glStartDate,
					toDateStr:glEndDate,
					year:financialVal
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
						buildSchemeWiseWorkDetails(ajaxresp);
					}else{
						$("#habitationWorks").html("No Data Available");
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
							name: 'Ongoing',
							data: workOngoingArr,
							color:'#14BBAE'
						}, {
							name: 'Not Grounded',
							data: workNotGroundedArr,
							color:'#FC5E57'
						}, {
							name: 'Completed',
							data: workCompletedArr,
							color:'#FFBF14'
						}, {
							name: 'Commissioned',
							data: workComissionedArr,
							color:'#465556'
						}]
				});
				
		}
		function getAssetInfoBetweenDates(){//ara1
			$("#assets").html(spinner);
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate
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
				success: function(ajaxresp){
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
					}else{
						$("#assets").html("No Data Available");
					}
				}
			});
		}
		
		
		function getWaterSourceInfo(){//ara1 
			$("#waterSources").html(spinner);
			
			$.ajax({
				url: 'getWaterSourceInfo',
				type: "GET",
				data: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null ){
						$("#waterSourcesTtlValues").show()
						var dataArr = [];
						var groundDataArr1=[],surfaceArrArr2=[];
						var totalCount=0;	
							groundDataArr1.push(parseInt(ajaxresp.groundWaterSourceTotalMlpdCount));
							dataArr.push(groundDataArr1);
							
							surfaceArrArr2.push(parseInt(ajaxresp.surfaceWaterSourceTotalMlpdCount));
							dataArr.push(surfaceArrArr2);
							totalCount =totalCount+parseInt(ajaxresp.groundWaterSourceTotalMlpdCount)+parseInt(ajaxresp.surfaceWaterSourceTotalMlpdCount);
							
							$("#waterSourcesTtlValues").html("TOTAL:"+totalCount)
							var colors = ['#14BAAD','#FC5049']
							var id = 'waterSources';
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
								categories: ['Ground','Surface'],
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
					}else{
						$("#waterSourcesTtlValues").hide()
						$("#waterSources").html("No Data Available");
					}
				}
			});	
		}
		
		function getKeyPerformanceIndicatorsInfo(locationType,divId,type){//ara1
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
						locationType:locationType
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
		function getStressedHabitationsInfoByLocationType(){
			var financialVal =$("#financialYearId").val();
			var json = {
						fromDateStr:glStartDate,
						toDateStr:glEndDate,
						locationType:"constituency",
						year:financialVal,
						filterType:"constituency",
						stressedHabitationYear:financialVal,
						filterValue:"120"

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
					if(ajaxresp !=null ){
						
					}
				}
			});
			
		}
		
		function getPlanofActionForStressedHabitations(){
			$("#planOfAction").html(spinner)
			var financialVal =$("#financialYearId").val();
			var json = {
					fromDateStr:glStartDate,
					toDateStr:glEndDate,
					locationType:"state",
					stressedHabitationYear:financialVal
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
				achievedHabitationArr.push({"y":result[0].achivedPopulation,"extra":result[0].percentageOne.toFixed(1)});
				targetHabitationArr.push({"y":result[0].targetPopulation,"extra":result[0].achivedHabPerc.toFixed(1)});
				
				stressedArr.push("Population");						
				achievedHabitationArr.push({"y":result[0].achived,"extra":result[0].targetPopPerc.toFixed(1)});
				targetHabitationArr.push({"y":result[0].target,"extra":result[0].achivedPopPerc.toFixed(1)});
								
				
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
										return '<span>'+this.y+'<br>('+(this.point.extra)+'%)</span>';
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
		function getAlertDetailsOfCategoryByStatusWise(type,divId,locationType){
			if(type=="graph"){
				$("#alertStatus").html(spinner);
			}else{
				$("#alertStatus"+locationType).html(spinner);
				$("#drinking"+locationType).html(spinner);
			}
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				deptId:49,
				year:financialVal
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
					if(ajaxresp !=null && ajaxresp.length>0){
						if(type=="graph"){
							$("#alertStatus").html('');
							buildAlertDetailsOfCategoryByStatusWise(ajaxresp);
						}else if(type=="table"){
							$("#alertStatus"+locationType).html('');
							$("#drinking"+locationType).html('');
							buildTableForHabitationCoverage(ajaxresp,locationType,divId,'alertStatus')
						}
						
						
					}else{
						if(type=="graph"){
							$("#alertStatus").html('No Data Available');
						}else if(type=="table"){
							$("#alertStatus"+locationType).html('No Data Available');
							$("#drinking"+locationType).html('No Data Available');
						}
					}
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
				var colors = ['#FC5049']
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
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				deptId:49,
				year:financialVal
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
			tabBlock+='<div class="panel-heading">';
				tabBlock+='<h4 class="panel-title text-capital">'+blockName+' level overview</h4>';
			tabBlock+='</div>';
			tabBlock+='<div>';
				tabBlock+='<div class="row">';
					tabBlock+='<div class="col-sm-12">';
						tabBlock+='<div>';
							
							tabBlock+='<ul class="nav nav-tabs nav-tabs-custom" role="tablist">';
								for(var i in blocksArr)
								{
									if(i == 0)
									{
										if(blockName == "constituency" || blockName == "mandal"){
											if(blocksArr[i].id == "jalavani"){
												tabBlock+='<li class="active" style="display:none"><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}else{
												tabBlock+='<li class="active" ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}
										}else{
											if(blocksArr[i].id == "jalavani"){
												tabBlock+='<li class="active" ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><span>'+blocksArr[i].name+'</span></a></li>';
											}else{
												tabBlock+='<li class="active" ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}
											
										}
										
									}else{
										if(blockName == "constituency" || blockName == "mandal"){
											if(blocksArr[i].id == "jalavani"){
												tabBlock+='<li style="display:none"><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}else{
												tabBlock+='<li ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+'><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}
											
										}else{
											if(blocksArr[i].id == "jalavani"){
												tabBlock+='<li ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><span>'+blocksArr[i].name+'</span></a></li>';
											}else{
												tabBlock+='<li ><a href="#'+blockId+''+blocksArr[i].id+'" aria-controls="'+blockId+'habitation" role="tab" data-toggle="tab" tab_id="'+blockId+''+blocksArr[i].id+'" attr_block_name='+blockName+' attr_block_id='+blockId+' style="padding: 15px;"><img src="Assests/icons/'+blocksArr[i].img+'"/><span style="margin-left:5px;">'+blocksArr[i].name+'</span></a></li>';
											}
											
										}
										
									}
									
								}
							tabBlock+='</ul>';
						tabBlock+='</div>';
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
					
					if(blockId == 'consBlockId')
					{
						tabBlock+='<div class="row">';
							tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constituencySelect'+blockId+'"></div></div>';
						tabBlock+='</div>';
					}else if(blockId == 'mandalBlockId')
					{
						tabBlock+='<div class="row">';
							tabBlock+='<div class="col-sm-3"><div id="districtSelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="constituencySelect'+blockId+'"></div></div>';
							tabBlock+='<div class="col-sm-3"><div id="mandalSelect'+blockId+'"></div></div>';
						tabBlock+='</div>';
					}
					
					for(var i in blocksArr)
					{
						if(i == 0)
						{
							 if(blocksArr[i].id == "jalavani"){
								 tabBlock+='<div >';
									 tabBlock+='<div role="tabpanel" class="tab-pane active" id="alertStatus'+blockName+'"></div>';
									 tabBlock+='<div role="tabpanel" class="tab-pane active" id="drinking'+blockName+'"></div>';
								 tabBlock+='</div>';
							 }else{
								 tabBlock+='<div role="tabpanel" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';
							 }
								//tabBlock+='<div role="tabpanel" class="tab-pane active" id="'+blockId+''+blocksArr[i].id+'"></div>';
						}else{
							 if(blocksArr[i].id == "jalavani"){
								tabBlock+='<div >';
									tabBlock+='<div  role="tabpanel" class="tab-pane" id="alertStatus'+blockName+'"></div>';
									tabBlock+='<div  role="tabpanel" class="tab-pane" id="drinking'+blockName+'"></div>';
								tabBlock+='</div>';
							}else{
								tabBlock+='<div role="tabpanel" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
							} 
							//tabBlock+='<div role="tabpanel" class="tab-pane" id="'+blockId+''+blocksArr[i].id+'">'+blocksArr[i].id+'</div>';
						}
					}
				tabBlock+='</div>';
			tabBlock+='</div>';
		tabBlock+='</div>';
		$("#"+blockId).html(tabBlock);
		$(".nav-tabs-custom li:last-child a").removeAttr("style");
		for(var i in blocksArr)
		{
			if(blocksArr[i].id == "habitation")
			getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
			
		}
		if(blockId == 'constituencyBlockId')
		{
			selectBox('districtSelect'+blockId+'')
			selectBox('constituencySelect'+blockId+'')
		}else if(blockId == 'mandalBlockId')
		{
			selectBox('districtSelect'+blockId+'')
			selectBox('constituencySelect'+blockId+'')
			selectBox('mandalSelect'+blockId+'')
		}
		
	}
	
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
								tableView+='<td>'+GLtbodyAlertArr[i].locationName+'</td>';
							}	
							
							for(var j in GLtbodyAlertArr[i].statusList){
								if(GLtbodyAlertArr[i].statusList[j].id==2){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										notifiedCount =GLtbodyAlertArr[i].statusList[j].count
									}
									
								}else if(GLtbodyAlertArr[i].statusList[j].id==13){
									if(GLtbodyAlertArr[i].statusList[j].count !=null &&GLtbodyAlertArr[i].statusList[j].count>0){
										proposalCount =GLtbodyAlertArr[i].statusList[j].count
									}
								}else if(GLtbodyAlertArr[i].statusList[j].id==3){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										actionInProgress =GLtbodyAlertArr[i].statusList[j].count
									}
								}else if(GLtbodyAlertArr[i].statusList[j].id==4 ||  GLtbodyAlertArr[i].statusList[j].id == 12){
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										CompleteClosedCount =CompleteClosedCount+GLtbodyAlertArr[i].statusList[j].count;
										completeClosed =CompleteClosedCount;
									}
								}else{
									if(GLtbodyAlertArr[i].statusList[j].count !=null && GLtbodyAlertArr[i].statusList[j].count>0){
										 othersCount =othersCount+GLtbodyAlertArr[i].statusList[j].count;
										others =othersCount
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
								tableView+='<td>'+notifiedCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+notifiedPerc+' </small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(proposalCount >0){
								tableView+='<td>'+proposalCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+proposalPerc+' </small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(actionInProgress >0){
								tableView+='<td>'+actionInProgress+'</td>';
								tableView+='<td><small style="color:#0FBE08">'+actionPerc+' </small></td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(CompleteClosedCount >0){
								tableView+='<td>'+CompleteClosedCount+'</td>';
								tableView+='<td><small style="color:#0FBE08">'+completeClosedPerc+'</small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(othersCount >0){
								tableView+='<td>'+othersCount+'</td>'; 
								tableView+='<td><small style="color:#0FBE08">'+othersPerc+' %</small> </td>';
							}else{
								tableView+='<td> - </td>';
								tableView+='<td> - </td>';
							}
							if(totalCount >0){
								tableView+='<td>'+totalCount+'</td>';
							}else{
								tableView+='<td> - </td>';
							}
							tableView+='</tr>'; 
						}
					tableView+='</tbody>';
					tableView+='</table>';
				$("#alertStatus"+locationType).html(tableView);
				$(".dataTableAlert"+locationType).dataTable();
				if($windowWidth < 768){
					$(".dataTableAlert"+locationType).wrap("<div class='table-responsive'></div>");
				}
			
		}
		function tableViewWaterStatus(divId,GLtbodyAlertArr,locationType){
			
			if(GLtbodyAlertArr !=null && GLtbodyAlertArr.length>0){
				var $windowWidth = $(window).width();
					var tableView='';
					tableView+='<h5 style="padding:5px"><span class="chartTitleAlign"><img src="Assests/icons/driking.png"> <span style="margin-left:5px;">Drinking Water Satisfaction Levels</span></span></h5>';
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
							if(GLtbodyAlertArr[i].statusList !=null && GLtbodyAlertArr[i].statusList.length>0){
								for(var j in GLtbodyAlertArr[i].statusList){
										tableView+='<td>'+GLtbodyAlertArr[i].statusList[j].count+'</td>';
										tableView+='<td>'+GLtbodyAlertArr[i].statusList[j].percentage+'</td>';
								}
							}
							tableView+='</tr>'; 
						}
					tableView+='</tbody>';
					tableView+='</table>';
				$("#drinking"+locationType).html(tableView);
				$(".dataTableDrinking"+locationType).dataTable();
				if($windowWidth < 768){
					$(".dataTableDrinking"+locationType).wrap("<div class='table-responsive'></div>");
				}
			}
			
			
		}
		function tableViewHabitationStatus(divId,GLtbodyArr,locationType)
		{
				 for(var k in divId){
					 var $windowWidth = $(window).width();
					 if(GLtbodyArr !=null && GLtbodyArr.length>0){
						var tableView='';
						if(divId[k].id=="habitation"){
							tableView+='<table class="table table-bordered" id="dataTable1'+locationType+divId[k].id+'">';
						}else if(divId[k].id=="performance"){
							tableView+='<table class="table table-bordered" id="dataTable'+locationType+divId[k].id+'">';
						}
							tableView+='<thead class="text-capital">';
							
							if(divId[k].id=="habitation"){
								tableView+='<tr>';
								tableView+='<th>'+locationType+'</th>';
								if(GLtbodyArr[0] !=null && GLtbodyArr[0].statusList !=null && GLtbodyArr[0].statusList.length>0){
									for(var j in GLtbodyArr[0].statusList){
										if(divId[k].id=="habitation"){
											if(GLtbodyArr[0].statusList[j].status != 'NC'){
												tableView+='<th>'+GLtbodyArr[0].statusList[j].status+'</th>';
												tableView+='<th>%</th>';
												
											}
										}
									}
								}
								tableView+='<th>TOTAL</th>';
								tableView+='</tr>'; 
							}else if(divId[k].id=="performance"){
								tableView+='<tr>';
								tableView+='<th rowspan="2">'+locationType+'</th>';
								tableView+='<th colspan="3">Partially Covered<br/>Habitations Through Schemes</th>';
								tableView+='<th colspan="3">Quality Affected<br/>Habitations Through Schemes</th>';
								tableView+='</tr>'; 
								tableView+='<tr>'; 
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='</tr>'; 
							}
						
						tableView+='</thead>';
						tableView+='<tbody>';
							if(divId[k].id=="habitation"){
								for(var i in GLtbodyArr){
									var totalCount=0;
									tableView+='<tr>';
									tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
									//&nbsp;&nbsp;&nbsp;<i class="fa fa-question-circle" aria-hidden="true"></i>
									if(GLtbodyArr[i].statusList !=null && GLtbodyArr[i].statusList.length>0){
										for(var j in GLtbodyArr[i].statusList){
										if(GLtbodyArr[i].statusList[j].status != 'NC'){
												if(GLtbodyArr[i].statusList[j].count !=null && GLtbodyArr[i].statusList[j].count>0){
													tableView+='<td>'+GLtbodyArr[i].statusList[j].count+'</td>';
													tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].statusList[j].percentage+'</small></td>';
												}else{
													tableView+='<td> - </td>';
													tableView+='<td> - </td>';
												}
												totalCount += GLtbodyArr[i].statusList[j].count;
											}
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
										tableView+='<td>'+GLtbodyArr[i].pcTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcAchivement !=null && GLtbodyArr[i].pcAchivement >0){
										tableView+='<td>'+GLtbodyArr[i].pcAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcPercentage !=null && GLtbodyArr[i].pcPercentage >0){
										tableView+='<td>'+GLtbodyArr[i].pcPercentage.toFixed(2)+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaTarget !=null && GLtbodyArr[i].qaTarget >0){
										tableView+='<td>'+GLtbodyArr[i].qaTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaAchivement !=null && GLtbodyArr[i].qaAchivement >0){
										tableView+='<td>'+GLtbodyArr[i].qaAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaPercentage !=null && GLtbodyArr[i].qaPercentage >0){
										tableView+='<td>'+GLtbodyArr[i].qaPercentage.toFixed(2)+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									tableView+='</tr>';
								}
								
							} 
						tableView+='</tbody>';
						tableView+='</table>';	
					if(divId[k].id !=="jalavani"){
						$("#"+locationType+"BlockId"+divId[k].id).html(tableView);
						if(divId[k].id=="habitation"){
							$("#dataTable1"+locationType+divId[k].id).dataTable();
							if($windowWidth < 768){
								$("#dataTable1"+locationType+divId[k].id).wrap("<div class='table-responsive'></div>");
							}
						}else if(divId[k].id=="performance"){
							$("#dataTable"+locationType+divId[k].id).dataTable();
							if($windowWidth < 768){
								$("#dataTable"+locationType+divId[k].id).wrap("<div class='table-responsive'></div>");
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
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id == "stateBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id == "stateBlockIdjalavani"){
					getAlertDetailsOfCategoryByStatusWise('table',alertStatusBlockArr,'state');
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'state');
				}
			}else if(blockName == "district"){
				emptyCheckDistrict();
				if(id == "districtBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id == "districtBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id == "districtBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'district');
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'district');
				}
			}else if(blockName == "constituency"){
				emptyCheckConstituency();
				if(id == "constituencyBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id=="constituencyBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id=="districtBlockIdjalavani"){
					
				}
			}else if(blockName == "mandal"){
				emptyCheckMandal();
				if(id == "mandalBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id=="mandalBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id=="districtBlockIdjalavani"){
					
				}
			}
		});
		function emptyCheckState(){
			$("#stateBlockIdhabitation").html('');
			$("#stateBlockIdperformance").html('');
			$("#alertStatusstate").html('');
			$("#drinkingstate").html('');
		}
		function emptyCheckDistrict(){
			$("#districtBlockIdhabitation").html('');
			$("#districtBlockIdperformance").html('');
			$("#alertStatusdistrict").html('');
			$("#drinkingdistrict").html('');
		}
		function emptyCheckConstituency(){
			$("#constituencyBlockIdhabitation").html('');
			$("#constituencyBlockIdperformance").html('');
		}
		function emptyCheckMandal(){
			$("#mandalBlockIdhabitation").html('');
			$("#mandalBlockIdperformance").html('');
		}
		function selectBox(id){
			var id = id;
			var selectBox='';
			selectBox+='<select class="chosen" id="chosen'+id+'"></select>';
			$("#"+id).html(selectBox);
			$("#chosen"+id).chosen();
		}
		$(".chosenSelect").chosen({width:'100%'});
		$(window,document).on('resize', function(){
			responsiveTabs();
		});
		$(document).on('click','[attr_click="questionMark"]', function(){
			$("#modalDivId").modal('show');
			$("#modalHeadingId").html($(this).attr("attr_title"));
			tableView('modalTable');
		});
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
		$(document).on("change","[role='tabListMobile']",function(){
			var id = $('option:selected', this).attr('tab_id');
			$("#"+id).closest(".tab-content").find("[role='tabpanel']").removeClass("active");
			$("#"+id).addClass("active");
			var blockName = $('option:selected', this).attr('attr_block_name');
			var blockId = $('option:selected', this).attr('attr_block_id');
			if(blockName == "state"){
				emptyCheckState();
				if(id == "stateBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id == "stateBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id == "stateBlockIdjalavani"){
					getAlertDetailsOfCategoryByStatusWise('table',alertStatusBlockArr,'state');
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'state');
				}
			}else if(blockName == "district"){
				emptyCheckDistrict();
				if(id == "districtBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id == "districtBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id == "districtBlockIdjalavani"){
					getLocationWiseAlertStatusCounts('table',alertStatusBlockArr,'district');
					getHamletWiseIvrStatusCounts('table',alertStatusBlockArr,'district');
				}
			}else if(blockName == "constituency"){
				emptyCheckConstituency();
				if(id == "constituencyBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id=="constituencyBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id=="districtBlockIdjalavani"){
					
				}
			}else if(blockName == "mandal"){
				emptyCheckMandal();
				if(id == "mandalBlockIdhabitation"){
					getHabitationCoverageByStatusByLocationType(blockName,blocksArr,'table');
				}else if(id=="mandalBlockIdperformance"){
					getKeyPerformanceIndicatorsInfo(blockName,blocksArr,'table');
				}else if(id=="districtBlockIdjalavani"){
					
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
		
		function getLocationBasedOnSelection(locationType,year,filterType,filterValue,districtValue){
			var json = {
				fromDateStr:glStartDate,
				toDateStr:glEndDate,
				locationType:locationType,
				year:year,
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
				var str = "";
				str+='<option value="0">ALL</option>';
				if(ajaxresp != null && ajaxresp.length > 0){
					for(var i in ajaxresp){
						str+='<option value="'+ajaxresp[i].id+'">'+ajaxresp[i].name+'</option>';
					}
				}
				
				if(locationType == "district"){
					$("#chosendistrictSelectmandalBlockId").html(str);
					$("#chosendistrictSelectmandalBlockId").trigger("chosen:updated");
				}else if(locationType == "constituency"){
					$("#chosenconstituencySelectmandalBlockId").html(str);
					$("#chosenconstituencySelectmandalBlockId").trigger("chosen:updated");
				}else if(locationType == "mandal"){
					$("#chosenmandalSelectmandalBlockId").html(str);
					$("#chosenmandalSelectmandalBlockId").trigger("chosen:updated");
				}
			}
		});
		}
		
		function getHamletWiseIvrStatusCounts(type,divId,locationType){
			if(type == "graph"){
				$("#drinkingWater").html(spinner);
			}else{
				$("#drinkingWater"+locationType).html(spinner);
			}
			
			var searchlevelId='';
			var searchLevelValues=[];
			
			var locationValues=[];
			var locationTypeId=''; 
			
			if(locationType == "" || locationType=="state"){
				
				searchlevelId =2;
				searchLevelValues.push(1);
				
				locationValues.push(1);
				locationTypeId=2;
				
			}else{
				searchlevelId =2;
				searchLevelValues.push(1);
				
				locationValues=[];
				locationTypeId=3;
				
			}
			var financialVal =$("#financialYearId").val();
			var json = {
				fromDate:glStartDate,
				toDate:glEndDate,
				blockLevelId:searchlevelId,
				levelValues:searchLevelValues,
				locationTypeId:locationTypeId,
				locationValues:locationValues,
				year:financialVal
				
				
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
	
	function getLocationWiseAlertStatusCounts(type,divId,locationType){
		$("#alertStatus"+locationType).html(spinner);
		$("#drinking"+locationType).html(spinner);
		var arr = [];
		var financialVal =$("#financialYearId").val();
		var json = {
		  deptId:49,
		  fromDate:glStartDate,
		  toDate:glEndDate,
		  year:financialVal,
		  groupByValue:5,
		  locationValues:arr
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
					buildTableForHabitationCoverage(ajaxresp,locationType,divId,'alertStatus')
				}else{
					$("#alertStatus"+locationType).html("No Data Available");
					$("#drinking"+locationType).html("No Data Available");
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
			categories: ['SATISFIED','SOSO','NOT SATISFIED'],
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
    
    $(".switch-btn li").removeClass("active");
    $(".switch-btn li:first-child").addClass("active");
    $('[role="tablist"] li:first-child a').trigger('click');
    $('#tabCons a[href="#consLevelGraph"]').trigger('click');
    glStartDate = picker.startDate.format('DD-MM-YYYY')
    glEndDate = picker.endDate.format('DD-MM-YYYY')
    if(picker.chosenLabel == 'All')
    {
      $("#dateRangePickerAUM").val('All');
    }
    onloadCalls();
    
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
			//$("#financialYearId").append("<option value='0'>Select Financial Year</option>");
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
  $(document).on("change","#chosendistrictSelectmandalBlockId",function(){
		var distId = $("#chosendistrictSelectmandalBlockId").val();
		var financialVal =$("#financialYearId").val();
		if(distId == 0){
			$("#chosenconstituencySelectmandalBlockId,#chosenmandalSelectmandalBlockId").html('');
		}else{
			distId = distId < 9?"0"+distId:distId;
			getLocationBasedOnSelection("constituency",financialVal,"district",distId,"");
		} 	
	});
	
	$(document).on("change","#chosenconstituencySelectmandalBlockId",function(){
		var constId = $("#chosenconstituencySelectmandalBlockId").val();
		var financialVal =$("#financialYearId").val();
		var distId = $("#chosendistrictSelectmandalBlockId").val()<9?"0"+$("#chosendistrictSelectmandalBlockId").val():$("#chosendistrictSelectmandalBlockId").val();
		if(constId == 0){
			$("#chosenmandalSelectmandalBlockId").html('');
		}else{
			getLocationBasedOnSelection("mandal",financialVal,"constituency",constId,distId);
		}
	});
	$(document).on("change","#financialYearId",function(){
		onloadCalls();
	});