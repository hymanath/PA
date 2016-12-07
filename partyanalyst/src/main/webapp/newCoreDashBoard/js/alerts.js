	$(document).on("click",".alertsIconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			getAlertCategoryDtlsLocationWise();
			console.log("opening")
		}else{
			//$(".newsHiddenMoreBlock,.moreAttBlocks").hide();
			console.log("closing")		
		}
		if( $(".trainingIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".dateRangePickerClsForTraining").addClass("hide");
			$(".trainingIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".trainingsHiddenBlock,.moreTrainingBlocks,.moreTrainingBlocksIcon").hide();
			$(".moreTrainingBlocksIcon").removeClass("unExpandTrainingBlock");
			$(".trainingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".debatesIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".debatesIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".debatesMoreHiddenBlock,.debatesHiddenBlock,.dateRangePickerClsForDebates").hide();
			$(".moreDebatesBlocksIcon").removeClass("unExpandDebatesBlock");
			$(".debatesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}else if( $(".iconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".iconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".committeesHiddenBlock,.moreBlocks,.moreBlocks1,.moreBlocksDetailAndComp,.moreBlocksIcon,.moreBlocksDistrictlevel").hide();
			$(".committeesBlock,.basicCommitteesBlock,.userTypeCommitteesBlock,.committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerCls").toggleClass("hide");
			$(".moreBlocksIcon").removeClass("unExpandBlock");
		}else if( $(".meetingsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".meetingsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".meetingsHiddenBlock,.moreMeetingsBlocksIcon").hide();
			$(".meetingsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForMeetings").toggleClass("hide");
			$(".moreMeetingsBlocks1").hide();
			$(".moreMeetingsBlocksDetailed").hide();
			$(".moreMeetingsBlocksComparision").hide();
		}else if( $(".eventsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".eventsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".eventsHiddenBlock,.moreEventsBlocks,.comparisonBlockEvents,.detailedBlockEvents").hide();
			$(".eventsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForEvents").toggleClass("hide");
		}else if( $(".newsIconExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".newsIconExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".newsHiddenBlock,.morenewsBlocksIcon,.newsHiddenMoreBlock").hide();
			$(".newsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
			$(".dateRangePickerClsForNews").toggleClass("hide");
		}else if( $(".cadreExpand").find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".cadreExpand").find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
			$(".moreCadreBlock,.moreBlocksCadre,.moreBlocksCadreIcon").hide();
			$(".cadreBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		}
		});
  function getAlertOverviewDetails(){
		$("#alertOverview").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate :"",        
			toDate :""    
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
			if(result != null){
			  buildAlertOverviewDetails(result);
			}else{
			  $("#alertOverview").html("NO DATA AVAILABLE.");	
			}
      });	
	}
	
	function buildAlertOverviewDetails(result)
	{
		var str='';
		if(result.overAllVO != null){
				str+='<div class="pad_15 bg_ED">';
				str+='<table class="table table-bordered alertOverviewTable bg_ED">';
					str+='<tr>';
						str+='<td>';
							str+='<h3>'+result.overAllVO.totalAlertCnt+'</h3>';
							str+='<p class="text-capital">TOTAL ALERTS</p>';
						str+='</td>';
						str+='<td>';
							str+='<h3>'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'%</small> </h3>';
							str+='<p class="text-capital">party</p>';
						str+='</td>';
						str+='<td>';
							str+='<h3>'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'%</small></h3>';
							str+='<p class="text-capital">others</p>';
						str+='</td>';
					str+='</tr>';
				str+='</table>';
			 str+='</div>';
		}
		if(result.statusList != null && result.statusList.length > 0){
		  str+='<div class="row">';
			 for(var i in result.statusList)
				{
					str+='<div class="col-md-4 col-xs-12 col-sm-4 m_top10">';
						str+='<div class="bg_ED pad_5">';
						str+='<h3>'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'%</small></h3>';
							str+='<p class="text-capital text-muted">'+result.statusList[i].statusType+'</p>';
						str+='</div>';
					str+='</div>';
				}	
		   str+='</div>';
		}
		if(result.categoryList != null && result.categoryList.length > 0){
			for(var i in result.categoryList)
			{
				str+='<h4 class="panel-title m_top10">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'</h4>';
				str+='<table class="table table-condensed bg_ED">';
					str+='<tbody>';
						str+='<tr>';
						for(var j in result.categoryList[i].statusList)
						{
							str+='<td><p class="text-muted text-capitalize responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
						}
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			}	
		}
		$("#alertOverview").html(str)
			
	}

	function getAlertCategoryDtlsLocationWise(){
		$("#locationWiseAlertDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		var jsObj={  
			activityMemberId : globalActivityMemberId,      
			stateId : globalStateId,           
			fromDate :"",        
			toDate :""    
		};
		$.ajax({
			type : 'POST',
			url : 'getAlertCategoryDtlsLocationWiseAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
           if(result != null && result.length > 0){
			   buildAlertCategoriesDlsHIghchart(result);
		   }else{
			   $("#locationWiseAlertDivId").html("NO DATA AVAILABLE.");
		   }
		});	
	}
	
	function buildAlertCategoriesDlsHIghchart(result){
		var str='';
		if(result != null && result.length > 0){
		  var str='';
		  for(var i in result){
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			  str+='<h5 class="text-capital m_top10">'+result[i].name+'</h5>';      
			  str+='<div id="alertCategory'+i+'" style="height:80px;"></div>';
			str+='</div>'
		  }
		}
		$("#locationWiseAlertDivId").html(str);
	   if(result != null && result.length > 0){
			for(var i in result){
				var locationNameArr = [];
				var alertCntArr = [];
			  if(result[i].subList !=null && result[i].subList.length>0){
					for(var j in result[i].subList){
						locationNameArr.push(result[i].subList[j].locationType);
						alertCntArr.push(result[i].subList[j].alertCount);	
					}
				}
		//if(result[i][0].totalTour!=0){
			var getWidth = $("#alertCategory"+i).parent().width()+'px';
				$("#alertCategory"+i).width(getWidth);
		     $(function () {
			$('#alertCategory'+i).highcharts({
				colors: ['#0066DC'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: locationNameArr,
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 10)+'...';
							},
							
						}
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
			 tooltip: {formatter: function(){
					return '<b>Total Alerts:'+ Highcharts.numberFormat(this.y, 0) +'</b><br/>';
                }      
				}, 
				plotOptions: {
					column: {  
						stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y, 0) +""; 
								}
							}
						  
						}
					}
				},
				legend: {
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				
				series: [{
					name: 'Number Of Alert',
					data: alertCntArr
				}]
			});
		});
		//}else{
		//$("#alertCategory"+i).html("No Data Available");
		//$("#alertCategory"+i).css("height","35px");
		//$("#alertCategory"+i).hide();
		//} 
	}
	}else{
    $("#locationWiseAlertDivId").html('NO DATA AVAILABLE.');
	}
	}