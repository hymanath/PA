	$(document).on("click",".alertsIconExpand",function(){
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".alertsBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".alertsBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".alertLocationDiv").show();
			getAlertCategoryDtlsLocationWise();
			console.log("opening")
			getTotalAlertGroupByDist();   
			$(".districtAltCtnCls").toggle();
		}else{
			console.log("closing")
			$("#districtWiseAlertCountId").html("");    
			$(".districtAltCtnCls").toggle();     
            $(".alertLocationDiv").hide();  			
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
						if(result.overAllVO.totalAlertCnt == 0){  
							str+='<h3>'+result.overAllVO.totalAlertCnt+'</h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="0" attr_count="'+result.overAllVO.totalAlertCnt+'">'+result.overAllVO.totalAlertCnt+'</h3>';
						}
						str+='<p class="text-capital">TOTAL ALERTS</p>';
					str+='</td>';
					str+='<td>';
						if(result.overAllVO.partyAlertCnt == 0){
							str+='<h3>'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'%</small> </h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="1" attr_count="'+result.overAllVO.partyAlertCnt+'">'+result.overAllVO.partyAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.partyAlertCntPer+'%</small> </h3>';
						}
						str+='<p class="text-capital">party</p>';
					str+='</td>';
					str+='<td>';
						if(result.overAllVO.otherAlertCnt == 0){
							str+='<h3>'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'%</small></h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="0" attr_alert_type_id="3" attr_count="'+result.overAllVO.otherAlertCnt+'">'+result.overAllVO.otherAlertCnt+'&nbsp;&nbsp;<small class="text-success">'+result.overAllVO.otherAlertCntPer+'%</small></h3>';
						}
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
						if(result.statusList[i].statusCnt == 0){
							str+='<h3>'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'%</small></h3>';
						}else{
							str+='<h3 class="alertDtlsCls" style="cursor:pointer;" attr_category_id="0" attr_status_id="'+result.statusList[i].statusTypeId+'" attr_alert_type_id="0" attr_count="'+result.statusList[i].statusCnt+'">'+result.statusList[i].statusCnt+'&nbsp;&nbsp;<small class="text-success" style="font-size:13px">'+result.statusList[i].statusCntPer+'%</small></h3>';
						}
						str+='<p class="text-capital text-muted">'+result.statusList[i].statusType+'</p>';
					str+='</div>';  
				str+='</div>';
			}    
			str+='</div>';  
		}
		if(result.categoryList != null && result.categoryList.length > 0){
		for(var i in result.categoryList)  
		{
			if(result.categoryList[i].statusCnt == 0){
				str+='<h4 class="panel-title m_top10">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'%</h4>';
			}else{
				str+='<h4 class="panel-title m_top10 alertDtlsCls" style="cursor:pointer;" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="0" attr_alert_type_id="0" attr_count="'+result.categoryList[i].statusCnt+'">'+result.categoryList[i].statusType+' - '+result.categoryList[i].statusCnt+'</h4>';
			}  
			str+='<table class="table table-condensed bg_ED">';
				str+='<tbody>';
					str+='<tr>';
					for(var j in result.categoryList[i].statusList)
					{
						if(result.categoryList[i].statusList[j].statusCnt == 0){
							str+='<td><p class="text-muted text-capitalize responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
						}else{
							str+='<td><p class="text-muted text-capitalize responsiveFont">'+result.categoryList[i].statusList[j].statusType+'</p><p class="responsiveFont alertDtlsCls" style="cursor:pointer;" attr_category_id="'+result.categoryList[i].statusTypeId+'" attr_status_id="'+result.categoryList[i].statusList[j].statusTypeId+'" attr_alert_type_id="0" attr_count="'+result.categoryList[i].statusList[j].statusCnt+'">'+result.categoryList[i].statusList[j].statusCnt+'&nbsp;&nbsp;<small class="text-success">'+result.categoryList[i].statusList[j].statusCntPer+'%</small></p></td>';
						}             
					}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
			}
		}
		$("#alertOverview").html(str)
			
	}
	function getTotalAlertGroupByDist(){
		var scopeIdsArr = [];
		scopeIdsArr.push(2);  
		scopeIdsArr.push(3);  
		scopeIdsArr.push(7);  
		scopeIdsArr.push(9);              
		var jsObj = { 
			stateId : 1,             
			fromDate : '',        
			toDate : '',             
			scopeIdsArr : scopeIdsArr,              
			activityMemberId : 44                                 
		}                  
		$.ajax({
			type : 'POST',      
			url : 'getTotalAlertGroupByDistAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			
			if(result != null && result.length > 0){
				buildTotalAlertGroupByDist(result);       
			}  
		});  
	}
	function buildTotalAlertGroupByDist(result){  
		var str='';
		var distWiseArticlesRelated = [];
		str+='<div id="districtAlertCountId" style="height:300px;"></div>';            
		for(var i in result){
			var obj1 = {
				name: result[i].locaitonName,
				y: result[i].count,
				extra:result[i].count       
			};
			distWiseArticlesRelated.push(obj1);
		}
		$("#districtWiseAlertCountId").html(str)
		$(function () {
			 $("#districtAlertCountId").highcharts({  
				colors: ['#53BF8B'],    
				chart: {
					type: 'column'
				},
				title: {
					text: ''
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					
					type: 'category',
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
						text: ''
					},
					labels: {
						enabled:false
					}
				},
				legend: {
					enabled: false
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
									return Highcharts.numberFormat(this.y,0);      
								}
							}
						}
					}
				},
				tooltip: {
					headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
					pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.extra}</b>'
				},
				series: [{    
					name: 'Number Of Alerts',    
					data: distWiseArticlesRelated
				}],
			 
			});
		});
	}
	$(document).on("click",".alertDtlsCls",function(){
		var alertStatusId = $(this).attr("attr_status_id");
		var alertCategoryId = $(this).attr("attr_category_id");
		var alertTypeId = $(this).attr("attr_alert_type_id");
		var alertCount = $(this).attr("attr_count");  
		getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount);
		
	});
	
	function getAlertDtls(alertStatusId, alertCategoryId, alertTypeId,alertCount){
		$("#tourDocumentBodyId").html("");           
		$("#tourDocumentBodyId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');           
		$("#tourDocumentId").modal("show");  
		$("#alertCntTitId").html("TOTAL ALERTS-"+alertCount);
		var jsObj = { 
			alertTypeId : alertTypeId,    
			alertStatusId : alertStatusId,
			alertCategoryId : alertCategoryId,  
			stateId : 1,             
			fromDate : '',                
			toDate : '',                
			activityMemberId : 44                                 
		}                          
		$.ajax({
			type : 'POST',      
			url : 'getAlertDtlsAction.action',
			dataType : 'json',      
			data : {task:JSON.stringify(jsObj)}     
		}).done(function(result){       
			
			if(result != null && result.length > 0){
				buildAlertDtls(result);   
			}
		});  
	}
	function buildAlertDtls(result){
		$("#cadreExcelExpBtnId").show();
		var str='';
		str+='<div class="table-responsive">';
	 	str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed " id="alertDtlsTabId">';   
		str+='<thead>';
             str+='<th>Created Date</th>';
			 str+='<th>Last Updated Date</th>';
			 str+='<th>Current Status</th>'	 
			 str+='<th>LAG Days</th>';
			 str+='<th>Alert Level</th>';
			 str+='<th></th>';
		 str+='</thead>';
		 str+='<tbody>';
		 for(var i in result){
			str+='<tr>';
			if(result[i].createdDate != null && result[i].createdDate.length > 0){
				str+='<td>'+result[i].createdDate+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].updatedDate != null && result[i].updatedDate.length > 0){
				str+='<td>'+result[i].updatedDate+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].status != null && result[i].status.length > 0){
				str+='<td>'+result[i].status+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].interval != null){
				str+='<td>'+result[i].interval+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			if(result[i].alertLevel != null && result[i].alertLevel.length > 0){
				str+='<td>'+result[i].alertLevel+'</td>';      
			}else{
				str+='<td> - </td>';  
			}
			str+='<td><button type="button" class="btn btn-default btn-success descAlertCls" attr_alert_id="'+result[i].id+'">Alert Details</button></td>';  
			str+='</tr>';
			}
			 str+='</tbody>';
			 str+='</table>';
			 str+='</div>';
		 $("#tourDocumentBodyId").html(str);          
		  $("#alertDtlsTabId").dataTable({  
				 "aaSorting": [[ 4, "desc" ]], 
				"iDisplayLength" : 10,
				"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
			 }); 
	}
	var globalStr1 = '';  
	$(document).on("click",".descAlertCls",function(){
		$("#tourDocHeadingId").html("ALERT TITLE <br>");
		$("#cdrModelDivId").modal("show");
		var alertId = $(this).attr("attr_alert_id");
		alertId = 76;    
		getAlertData(604);
		getAlertAssignedCandidates(alertId);    
		getAlertStatusCommentsTrackingDetails(alertId);
	});
	function getAlertAssignedCandidates(alertId){
		GlobalAlertData = [];
		var jsObj ={
			alertId  : alertId,    
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertAssignedCandidatesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
		});
	}
	function getAlertData(alertId){  
		var jsObj ={
			alertId  :alertId,
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertsDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			buildAlertData(result);
		});
	}
	function buildAlertData(result){
		$("#tourDocHeadingId").html("<span style='color:#FFFFFF;'>ALERT TITLE</span><br><span class='text-capital'>"+result[0].title+"</span>");
		$("#cdrModelId").html("<span>ALERT DESCRIPTION</span><br>");
		$("#alertDestId").html(result[0].desc);
		$("#alertAttachTitId").html("<span style='margin-left: 11px;'>ALERT ATTACHMENTS</span>");
		var imgStr = '';
		
		imgStr+='<ul class="list-inline imageUrlUlCls">';
		imgStr+='<li class="articleDetailsCls" attr_articleid="414441" style="cursor:pointer">';
		imgStr+='<img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 150px; height: 150px;">';
		imgStr+='</li>';
		imgStr+='</ul> '; 
		$("#alertAttachImgId").html(imgStr);          
	}
	function getAlertStatusCommentsTrackingDetails(alertId){  
		var jsObj={
			alertId:alertId,
			task:""
		}
		$.ajax({  
			type : 'GET',
			url : 'getAlertStatusCommentsTrackingDetails.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		});
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
			  str+='<h5 class="text-capital m_top10 alertCategoryCls">'+result[i].name+'</h5>';      
			  str+='<div id="alertCategory'+i+'" attr_category_name='+result[i].name+' attr_id="alertCategory'+i+'" class="dddddd" style="height:130px;"></div>';
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
						//alertCntArr.push(result[i].subList[j].alertCount);
						alertCntArr.push({"y":result[i].subList[j].alertCount,"extra":result[i].name});
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
						enabled: true,
					}
				},
				tooltip: {
				 useHTML: true,
				 backgroundColor: '#FCFFC5',
				 formatter: function() {
						 var categoryName = this.point.extra;
						 var obj1 = result.filter(function ( obj1 ) {
							 	return obj1.name.toUpperCase().trim() === categoryName.toUpperCase().trim();
							})[0];
						 var locationList = obj1.subList;
						 var _locationName = this.x;
						 var obj = locationList.filter(function ( obj ) {
								return obj.locationType.toUpperCase().trim() === _locationName.toUpperCase().trim();
							})[0];
							return "Total Alerts - "+obj.alertCount+"<br/>Pending - " + obj.pendingCnt + "<br/>Notified - " + obj.notifiedCnt + " <br/>Action In Progess - " + obj.actionInProgessCnt+"<br/>Completed - " + obj.completedCnt + "<br/>Unable to Resolve - " + obj.unabletoResolveCnt + "<br/>Action Not Required - " + obj.actionNotRequiredCnt+"";     
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
					enabled: false,
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