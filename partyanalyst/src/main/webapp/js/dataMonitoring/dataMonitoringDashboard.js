  $(document).on("click",".applyBtn",function(){
	getDataMonitoringOverViewDetails();
  });
  getReasons();
  var globalSrt = '';
	function getReasons(){
		$.ajax({
			type:'GET',
			url: 'getReasonAction.action',           
			dataType: 'json',
			data: {}
		}).done(function(result){     
			
			if(result != null && result.length > 0){
				globalSrt = '';  
				globalSrt+='<option value="'+0+'">Select Reason</option>';
				for(var i in result){
					globalSrt+='<option value="'+result[i].id+'">'+result[i].name+'</option>';  
				}
			}else{
				
			}     
		});
	}
  function getDataMonitoringOverViewDetails(){
 $("#dataMonitoringOverviewTblId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $("#dataMonitoringOverviewHighChartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $("#webTabOnlineHighchartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $("#userWiseRegDivId").html(' ');
 $("#totalHeadingId").html(' ');
  $(".headingCls").hide();
       var fromDate;
       var toDateDate;
       var date=$(".multiDateRangePicker").val();
	  if(date != undefined && date.trim().length > 0){
		  fromDate = date.split("-")[0]; 
		  toDateDate = date.split("-")[1]; 
	  }	 
   var jsObj = {
	   
		  fromDate : fromDate,
		  toDate : toDateDate
   }
   $.ajax({
     type : 'GET',    
      url : 'getDataMonitoringOverViewDetailsAction.action',  
      dataType : 'json',
      data : {task:JSON.stringify(jsObj)} 
   }).done(function(result){
	    $("#dataMonitoringOverviewTblId").html(' ');
		$("#dataMonitoringOverviewHighChartDivId").html(' ');
		$("#webTabOnlineHighchartDivId").html(' ');
	    if(result != null ){
		   buildDataMonitoringOverViewRslt(result);
		   buildDataMonitoringHighChart(result);
		   buildWebTabAndOnlineRslt(result);
		}else{
		  $("#dataMonitoringOverviewTblId").html("NO DATA AVAILABLE.");	
		  $("#dataMonitoringOverviewHighChartDivId").html("NO DATA AVAILABLE.");	
		  $("#webTabOnlineHighchartDivId").html("NO DATA AVAILABLE");
		}
   });
   }
   function buildDataMonitoringOverViewRslt(result){
	   var str='';
        str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered">';
			str+='<thead>';
				str+='<th>TOTAL</th>';
				str+='<th>TAB</th>';
				str+='<th>WEB</th>';
				str+='<th>ONLINE</th>';
			str+='</thead>';
		  str+='<tbody style="font-size:12px;">';
				 str+='<tr>';
					str+='<td class="text-capital">active team members (<b>'+result.activyTeamMemberCnt+'<b>)</td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">total registrations (<b>'+result.totalRegCnt+'</b>)</td>';
					if(result.totalTabRegCnt > 0){
					   str+='<td><a style="cursor:pointer;" attr_count='+result.totalTabRegCnt+' class="overAllCountCls" attr_data_source_type="TAB" attr_vefification_type="Total">'+result.totalTabRegCnt+'<a></td>';
					}else{
						str+='<td> - </td>';
					}
					if(result.totalWebRegCnt > 0){
					  str+='<td><a style="cursor:pointer;" attr_count='+result.totalWebRegCnt+' class="overAllCountCls" attr_data_source_type="WEB" attr_vefification_type="Total">'+result.totalWebRegCnt+'</a></td>';
					}else{
					  str+='<td> - </td>';
					}
					if(result.totalOnlineRegCnt > 0){
					  str+='<td><a style="cursor:pointer;" attr_count='+result.totalOnlineRegCnt+' class="overAllCountCls" attr_data_source_type="ONLINE" attr_vefification_type="Total">'+result.totalOnlineRegCnt+'</a></td>';
					}else{
				      str+='<td> - </td>';
					}
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">verified-passed (<b>'+result.totalVerifyRegCnt+'</b>)</td>';
					if(result.totalTabVerifyCnt > 0){
					   str+='<td><a style="cursor:pointer;" attr_count='+result.totalTabVerifyCnt+' class="overAllCountCls" attr_data_source_type="TAB" attr_vefification_type="Approved">'+result.totalTabVerifyCnt+'</a></td>';
					}else{
						str+='<td> - </td>';
					}
					if(result.totalWebVerifyCnt > 0){
					  str+='<td><a style="cursor:pointer;" attr_count='+result.totalWebVerifyCnt+' class="overAllCountCls" attr_data_source_type="WEB" attr_vefification_type="Approved">'+result.totalWebVerifyCnt+'</a></td>';
					}else{
					  str+='<td> - </td>';
					}
					if(result.totalOnlineVerifyCnt > 0){
					  str+='<td><a style="cursor:pointer;" attr_count='+result.totalOnlineVerifyCnt+' class="overAllCountCls" attr_data_source_type="ONLINE" attr_vefification_type="Approved">'+result.totalOnlineVerifyCnt+'</a></td>';
					}else{
				      str+='<td> - </td>';
					}
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">verified-junk/rejected (<b>'+result.totalVerifyRejectCnt+'</b>)</td>';
						if(result.totalTabVerifyRejectedCnt > 0){
						  str+='<td><a style="cursor:pointer;" attr_count='+result.totalTabVerifyRejectedCnt+' class="overAllCountCls" attr_data_source_type="TAB" attr_vefification_type="Rejected">'+result.totalTabVerifyRejectedCnt+'</a></td>';
						}else{
							str+='<td> - </td>';
						}
						if(result.totalWebVerifyRejectedCnt > 0){
						  str+='<td><a style="cursor:pointer;" attr_count='+result.totalWebVerifyRejectedCnt+' class="overAllCountCls" attr_data_source_type="WEB" attr_vefification_type="Rejected">'+result.totalWebVerifyRejectedCnt+'</a></td>';
						}else{
						  str+='<td> - </td>';
						}
						if(result.totalOnlineVerifyRejectedCnt > 0){
						  str+='<td><a style="cursor:pointer;" attr_count='+result.totalOnlineVerifyRejectedCnt+' class="overAllCountCls" attr_data_source_type="ONLINE" attr_vefification_type="Rejected">'+result.totalOnlineVerifyRejectedCnt+'</a></td>';
						}else{
						  str+='<td> - </td>';
						}
				 str+='</tr>'; str+='<tr>';
					str+='<td class="text-capital">pending (<b>'+result.totalPendingCnt+'</b>)</td>';
					if(result.totalTabPendingCnt > 0){
						   str+='<td><a style="cursor:pointer;" attr_count='+result.totalTabPendingCnt+' class="overAllCountCls" attr_data_source_type="TAB" attr_vefification_type="Pending">'+result.totalTabPendingCnt+'</a></td>';
						}else{
							str+='<td> - </td>';
						}
						if(result.totalWebPendingCnt > 0){
						  str+='<td><a style="cursor:pointer;" attr_count='+result.totalWebPendingCnt+' class="overAllCountCls" attr_data_source_type="WEB" attr_vefification_type="Pending">'+result.totalWebPendingCnt+'</a></td>';
						}else{
						  str+='<td> - </td>';
						}
						if(result.totalOnlinePendingCnt > 0){
						  str+='<td><a style="cursor:pointer;" attr_count='+result.totalOnlinePendingCnt+' class="overAllCountCls" attr_data_source_type="ONLINE" attr_vefification_type="Pending">'+result.totalOnlinePendingCnt+'</a></td>';
						}else{
						  str+='<td> - </td>';
						}
				 str+='</tr>';
		str+='</tbody>';
    str+='</table>';
	  $("#dataMonitoringOverviewTblId").html(str); 
   }
   function buildDataMonitoringHighChart(result){
	   
		$('#dataMonitoringOverviewHighChartDivId').highcharts({
			colors: ['#4C7BA7','#9F526F','#DB4C21','#808000'],
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
				 categories: ['Data Verification'],
				
				labels: {
						rotation: -45,
						 enabled: false,
						style: {
							fontSize: '10px',
							fontFamily: 'Verdana, sans-serif'
						}
					}
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
			    minorGridLineWidth: 0,
				enabled: false,
				labels: {
					enabled: false,
				},
				title: {
					text: null
				},
				stackLabels: {
					enabled: false,
					style: {
						fontWeight: 'bold',
						color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
					}
				}
			},
			legend: {
				enabled: true,
				backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
				borderColor: '#CCC',
				borderWidth: 1,
				shadow: false
			},
			
		
			tooltip: {
							pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y:.0f}</b><br/>'
						},
								
			plotOptions: {
				column: {
					dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.point.y === 0) {
								return null;
							} else {
								return '<b>' + this.point.y + '</b>';
							}
						}
					  
					}
				}
			},
			series: [{
				name: 'Total Registrations',
				data: [result.totalRegCnt]
			}, {
				name: 'Verified-Passed',
				data: [result.totalVerifyRegCnt]
			}, {
				name: 'Verified-Junk/rejected',
				data: [result.totalVerifyRejectCnt]
			}, {
				name: 'Pending',
				data: [result.totalPendingCnt]
			}]
		});
   }
   
   function buildWebTabAndOnlineRslt(result){
	    $('#webTabOnlineHighchartDivId').highcharts({
		colors: ['#5E3243','#DB4C21','#808000'],
        chart: {
            type: 'column'
        },
        title: {
            text: ' '
        },
        xAxis: {
			 min: 0,
			 gridLineWidth: 0,
			 minorGridLineWidth: 0,
            categories: ['TAB', 'WEB', 'ONLINE']
        },
        yAxis: {
           min: 0,
		   gridLineWidth: 0,
			minorGridLineWidth: 0,
            title: {
                text: ' '
            },
			labels: {
					enabled: false,
				},
        },
        tooltip: {
            pointFormat: '<span style="color:{series.color}">{series.name}</span>: {point.y:.1f} %<br/>',
            shared: true
        },
        plotOptions: {
			pointPadding: 0.2,
			borderWidth: 2,
			groupPadding: 0.2,
            column: {
                stacking: 'percent',
				dataLabels: {
						enabled: true,
						 formatter: function() {
							if (this.y === 0) {
								return null;
							} else {
								return Highcharts.numberFormat(this.y,2) +'%';
							}
						}
					  
					}
            }
        },
        series: [ 
		  {
            name: 'Verify',
            data: [result.totalVerifyTabPer,result.totalVerifyWebPer,result.totalVerifyOnlinePer]
         }, {
            name: 'Rejected',
            data: [result.totalRejectedTabPer,result.totalRejectedWebPer,result.totalRejectedOnlinePer]
        }, {
            name: 'Pending',
            data: [result.totalPendingTabPer,result.totalPendingWebPer,result.totalPendingOnlinePer]
        }  ]
    });
   }
   $(document).on("click",".overAllCountCls",function(){
	   var dataSourceType = $(this).attr("attr_data_source_type");
	   var dataVerificationStatus = $(this).attr("attr_vefification_type");
	   var totalRegCnt = $(this).attr("attr_count");
	    getRegistrationDetailsUserWise(dataSourceType,dataVerificationStatus,totalRegCnt);
   });
  
   function getRegistrationDetailsUserWise(dataSourceType,dataVerificationStatus,totalRegCnt){
	  $("#totalHeadingId").html(" ");
	  $(".headingCls").hide();
	  $("#userWiseRegDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	   var fromDate;
       var toDateDate;
       var date=$(".multiDateRangePicker").val();
	  if(date != undefined && date.trim().length > 0){
		  fromDate = date.split("-")[0]; 
		  toDateDate = date.split("-")[1]; 
	  }	 
	 var jsObj = {
        dataSourceType : dataSourceType,
        verificationStatus : dataVerificationStatus,
        fromDate : fromDate,
        toDate : toDateDate
     }
     $.ajax({
       url:'getRegistrationDetailsUserWiseAction.action',
       type:'GET',
       dataType:'json',
       data:{task:JSON.stringify(jsObj)}
     }).done(function(result){
		  $("#userWiseRegDivId").html(' ');
         if(result != null && result.length > 0){
			 buildUserWiseResult(result,totalRegCnt,dataVerificationStatus,dataSourceType); 
		 }else{
		   $("#userWiseRegDivId").html("NO DATA AVAILABLE."); 	 
		 }
     });
   }
function buildUserWiseResult(result,totalRegCnt,dataVerificationStatus,dataSourceType){
	             
				 $(".headingCls").show();
	             if(dataVerificationStatus == "Total"){
				   $("#totalHeadingId").html("TOTAL "+dataSourceType+" REGISTRATIONS - "+totalRegCnt);	 
				 }else{
				    $("#totalHeadingId").html(dataSourceType+" "+dataVerificationStatus.toUpperCase()+" - "+totalRegCnt);	 
				 }
				  var str='';
	               str+='<table style="background-color:#EDEEF0;border:1px solid #ddd" class="table table-condensed table-bordered" id="userWiseDataTableId">';
                    	str+='<thead>';
						    if(dataSourceType=="TAB"){
							 	str+='<th>Survey UserId</th>';
                                str+='<th>Field Staff Name</th>';
                       		 }else if(dataSourceType=="WEB"){
							    str+='<th>Web UserId</th>'; 
							 }else{
							    str+='<th>Online UserId</th>'; 
							 }
                            str+='<th>Contact Number</th>';
                            str+='<th>Completed Registrations</th>';
                            str+='<th>Verified - Passed</th>';
                            str+='<th>Verified- Junk/Rejected</th>';
                            str+='<th>Pending</th>';
                            str+='<th></th>';
                        str+='</thead>';
						 str+='<tbody>';
						 if(dataSourceType=="TAB"){
							for(var i in result){
									var tabUserList = result[i].subList;
									 if(tabUserList != null && tabUserList.length > 0){
										for(var j in tabUserList){
										  str+='<tr>';
										 if(tabUserList[j].isActive != null && tabUserList[j].isActive=="Active"){
										   str+='<td class="issueCmpltd">'+result[i].surveryUserName+'</td>'; 
										  }else{
											str+='<td class="issuePending">'+result[i].surveryUserName+'</td>'; 
										  }
										 if(tabUserList[j].tabUserName != null && tabUserList[j].tabUserName.length > 0){
											str+='<td>'+tabUserList[j].tabUserName+'</td>';    
										  }else{
											str+='<td> - </td>';  
										  }
										  if(tabUserList[j].mobileNo != null && tabUserList[j].mobileNo.length > 0){
											str+='<td>'+tabUserList[j].mobileNo+'</td>';    
										  }else{
											str+='<td> - </td>';  
										  }
										  if(tabUserList[j].totalRegCnt != null && tabUserList[j].totalRegCnt> 0){
											str+='<td>'+tabUserList[j].totalRegCnt+'</td>';   
										  }else{
											str+='<td> - </td>';  
										  }
										   if(tabUserList[j].totalVerifyRegCnt != null && tabUserList[j].totalVerifyRegCnt> 0){
											str+='<td>'+tabUserList[j].totalVerifyRegCnt+'</td>';    
										  }else{
											str+='<td> - </td>';  
										  }
										   if(tabUserList[j].totalVerifyRejectCnt != null && tabUserList[j].totalVerifyRejectCnt> 0){
											str+='<td>'+tabUserList[j].totalVerifyRejectCnt+'</td>';    
										  }else{
											str+='<td> - </td>';  
										  }
										   if(tabUserList[j].totalPendingCnt != null && tabUserList[j].totalPendingCnt> 0){
											str+='<td>'+tabUserList[j].totalPendingCnt+'</td>';    
										  }else{
											str+='<td> - </td>';  
										  }
										 str+='<td><button attr_vefification_type="'+dataVerificationStatus+'" attr_survery_user_id='+result[i].surveryUserId+'  attr_web_user_id="'+0+'"  attr_user_name="'+tabUserList[j].tabUserName+'" attr_user_mobile="'+tabUserList[j].mobileNo+'" attr_tab_user_id='+tabUserList[j].tabUserId+' class="btn btn-success getMemberDtlsCls">Verify Records</button></td>';
										  str+='</tr>';	
										}
								}
							}
						 }else{
							   for(var j in result){
								  str+='<tr>';
								 if(result[j].isActive != null && result[j].isActive=="Active"){
								   str+='<td class="issueCmpltd">'+result[j].surveryUserName+'</td>'; 
								  }else{
									str+='<td class="issuePending">'+result[j].surveryUserName+'</td>'; 
								  }
								  if(result[j].mobileNo != null && result[j].mobileNo.length > 0){
									str+='<td>'+result[j].mobileNo+'</td>';    
								  }else{
									str+='<td> - </td>';  
								  }
								  if(result[j].totalRegCnt != null && result[j].totalRegCnt> 0){
									str+='<td>'+result[j].totalRegCnt+'</td>';   
								  }else{
									str+='<td> - </td>';  
								  }
								   if(result[j].totalVerifyRegCnt != null && result[j].totalVerifyRegCnt> 0){
									str+='<td>'+result[j].totalVerifyRegCnt+'</td>';    
								  }else{
									str+='<td> - </td>';  
								  }
								   if(result[j].totalVerifyRejectCnt != null && result[j].totalVerifyRejectCnt> 0){
									str+='<td>'+result[j].totalVerifyRejectCnt+'</td>';    
								  }else{
									str+='<td> - </td>';  
								  }
								   if(result[j].totalPendingCnt != null && result[j].totalPendingCnt> 0){
									str+='<td>'+result[j].totalPendingCnt+'</td>';    
								  }else{
									str+='<td> - </td>';  
								  }
								  str+='<td><button attr_vefification_type="'+dataVerificationStatus+'" attr_survery_user_id="'+0+'" attr_tab_user_id="'+0+'"  attr_web_user_id='+result[j].surveryUserId+' attr_user_name="'+result[j].surveryUserName+'" attr_user_mobile="'+result[j].mobileNo+'" class="btn btn-success getMemberDtlsCls">Verify Records</button></td>';
								  str+='</tr>';	
								 }
						}
				  str+='</tbody>';
               str+='</table>';
		$("#userWiseRegDivId").html(str);
		$("#userWiseDataTableId").dataTable();
 }
    function modifyDate(date){  
		var dateArr = date.split("/");     
		return dateArr[1]+"/"+dateArr[0]+"/"+dateArr[2];
	}
  $(document).on("click",".getMemberDtlsCls",function(){
	   var surveyUserId = $(this).attr("attr_survery_user_id");
	   var tabUserId = $(this).attr("attr_tab_user_id");
	   var webUserId = $(this).attr("attr_web_user_id");
	   var userName = $(this).attr("attr_user_name");
	   var mobileNo = $(this).attr("attr_user_mobile");
	   var verificationStatus = $(this).attr("attr_vefification_type");
	   var resultType="All";
	    if(webUserId==0){
		 $("#userId").html("Tab UserID - "+tabUserId+"");	
		 $("#userDescriptionId").html("<i>"+userName+" - "+mobileNo+"</i>");	
		}else{
		  $("#userId").html("User ID - "+webUserId+"");	
		  $("#userDescriptionId").html("<i>"+userName+" - "+mobileNo+"</i>");		
		}
	   
	   	$("#relativePaginationId").html(' ');
		$("#selfPaginationId").html(' ');
	    $("#issuesDataMonitroingDashboardModal").modal('show');
		$("#issuesDataMonitroing li").removeClass("active");
		$("#issuesDataMonitroing li:first-child").addClass("active");
		$(".activeCls").addClass("active");
		$(".relativeCls").removeClass("active");
	    getMembersDetails(surveyUserId,tabUserId,webUserId,userName,mobileNo,0,resultType,verificationStatus);
   });
   function getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType,verificationStatus){
	   if(resultType=="All"){
		 $("#selfTblDivId").html(' ');
	     $("#relativeDivId").html(' ');
		 $("#selfTblDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	     $("#relativeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   }else if(resultType=="Self"){
		  $("#selfTblDivId").html(' ');  
		   $("#selfTblDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   }else if(resultType=="Relative"){
		  $("#relativeDivId").html(' ');   
		   $("#relativeDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	   }
	   var fromDate;
       var toDateDate;
       var date=$(".multiDateRangePicker").val();
	  if(date != undefined && date.trim().length > 0){
		  fromDate = date.split("-")[0]; 
		  toDateDate = date.split("-")[1]; 
	  }	 
	var jsObj=
		{				
			surveyUserId : surveyUserId,
			tabUserId : tabUserId,
			webUserId : webUserId,
			startDate : modifyDate(fromDate),
			endDate : modifyDate(toDateDate),
            minValue :minValue,
            maxValue :10,
			resultType:resultType,
			verificationStatus:verificationStatus
      }
		$.ajax({
			type:'GET',
			url: 'getVerifiedDtlsAction.action',           
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){  
				buildVerifiedDtlsCount(result,surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType);  
			}else{
			if(resultType=="All"){
			    $("#selfTblDivId").html('No Data Available...');
				$("#relativeDivId").html('NO DATA Available.');
	        }else if(resultType=="Self"){
			    $("#selfTblDivId").html('No Data Available...');
		   }else if(resultType=="Relative"){
			   $("#relativeDivId").html('No Data Available...');
		    }
			}
		});  
	   
   }  
   function buildVerifiedDtlsCount(result,surveyUserId,tabUserId,webUserId,userName,userMobile,minValue,resultType){
		if(resultType=="All" || resultType=="Self"){  
		var str = '';
		var selfTotalCount=0;
			str+='<table class="table">';
				str+='<thead class="text-capital">';
					str+='<th>captured photo</th>';
					str+='<th>Voter photo</th>';
					str+='<th>name</th>';
					str+='<th>mobile number</th>';
					str+='<th>gender</th>';
					str+='<th><input id="globalSelectOwnId" type="checkbox"/></th>';
				str+='</thead>';
				str+='<tbody class="b_1">';
				selfTotalCount=result[0][0].totalCount;
					for(var i in result[0]){
						if(result[0][i].status == "Approved"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';  
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					
							str+='<tr>';
								str+='<td><img src="images/right.jpg" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[0][i].status == "Rejected"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td>';
									//str+='<input type="checkbox"/>';
								str+='</td>';
							str+='</tr>';  

					     
							str+='<tr>';
								str+='<td><img src="images/close.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str+='<td colspan="3">';
									str+='<input type="text" value="'+result[0][i].wish+'" class="form-control"></input>';    
										//str+='<option></option>';  
									//str+='</select>';
								str+='</td>';
							str+='</tr>';
						}
						if(result[0][i].status == "noStatus"){
							str+='<tr>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.in/images/cadre_images/'+result[0][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td rowspan="2">';
									str+='<img src="http://mytdp.com/voter_images/'+result[0][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str+='</td>';
								str+='<td>'+result[0][i].name+'</td>';
								str+='<td>'+result[0][i].mobileNo+'</td>';
								str+='<td>'+result[0][i].gender+'</td>';
								str+='<td><input attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'" class="localSelectOwnCls" type="checkbox"/></td>';  
							str+='</tr>'; 
					
					
							str+='<tr>';
								str+='<td>';  
									str+='<button class="btn btn-success singleApproveCls"  attr_cadre_id="'+result[0][i].cadreId+'">Approve</button>';
									str+='<button class="btn btn-danger singleRejectCls"  attr_cadre_id="'+result[0][i].cadreId+'" attr_reason_id="ownHideSelectBoxId'+i+'">Reject</button>';
								str+='</td>';
								str+='<td colspan="3">';
									str+='<select class="select" id="ownHideSelectBoxId'+i+'" style="display:none;">';
										str+='<option value="0">Andhra Pradesh</option>';                
									str+='</select>';  
								str+='</td>';    
							str+='</tr>';  
							
						}
					}
				str+='</tbody>';
			str+='</table>';
			$("#selfTblDivId").html(str);
			for(var i in result[0]){
				if(result[0][i].status == "noStatus"){
					$("#ownHideSelectBoxId"+i).html(globalSrt);  
				}
			}
			if(minValue == 0 && selfTotalCount > 10){
				$("#selfPaginationId").pagination({
					items: selfTotalCount,
					itemsOnPage: 10,
					cssStyle: 'light-theme',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,num,"Self");
					}
				});
			}	
		}
		if(resultType=="All" || resultType=="Relative"){
			var relativeTotalCount=0;
			var str2 = '';
			str2+='<table class="table">';
				str2+='<thead class="text-capital">';
					str2+='<th>captured photo</th>';
					str2+='<th>Voter photo</th>';
					str2+='<th>name</th>';
					str2+='<th>mobile number</th>';
					str2+='<th>gender</th>';
					str2+='<th><input id="globalSelectFamilyId" type="checkbox"/></th>';  
				str2+='</thead>';
				str2+='<tbody class="b_1">';
					for(var i in result[1]){ 
						relativeTotalCount=result[1][0].totalCount;
						if(result[1][i].status == "Approved"){
							str2+='<tr>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							str2+='<tr>';
								str2+='<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td colspan="3">';
									//str+='<select>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';
						}
						if(result[1][i].status == "Rejected"){
							str2+='<tr>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td>';
									//str+='<input type="checkbox"/>';
								str2+='</td>';
							str2+='</tr>';  

					
							str2+='<tr>';
								str2+='<td><img src="Assests/img/verified.png" class="img-responsive" style="width:40px;height:40px;" alt="verified"/></td>';
								str2+='<td colspan="3">';
									str2+='<input type="text" value="'+result[1][i].wish+'"></input>';
										//str+='<option></option>';
									//str+='</select>';
								str2+='</td>';
							str2+='</tr>';
						}
						if(result[1][i].status == "noStatus"){
							str2+='<tr>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.in/images/cadre_images/'+result[1][i].image+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';
								str2+='<td rowspan="2">';
									str2+='<img src="http://mytdp.com/voter_images/'+result[1][i].voterImage+'" class="img-responsive img-thumbnail" alt="image" style="width:80px;height:80px;"/>';
								str2+='</td>';  
								str2+='<td>'+result[1][i].name+'</td>';
								str2+='<td>'+result[1][i].mobileNo+'</td>';
								str2+='<td>'+result[1][i].gender+'</td>';
								str2+='<td><input attr_cadre_id="'+result[1][i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'" class="localSelectFamilyCls" type="checkbox"/></td>';
							str2+='</tr>'; 
							str2+='<tr>';
								str2+='<td>';
									str2+='<button class="btn btn-success singleApproveCls" attr_cadre_id="'+result[1][i].cadreId+'">Approve</button>';//familyHideSelectBoxId0
									str2+='<button class="btn btn-danger singleRejectCls" attr_cadre_id="'+result[1][i].cadreId+'" attr_reason_id="familyHideSelectBoxId'+i+'">Reject</button>';
								str2+='</td>';
								str2+='<td colspan="3">';
									str2+='<select class="select" id="familyHideSelectBoxId'+i+'" style="display:none;">';      
										str2+='<option value="0">Andhra Pradesh</option>';                  
									str2+='</select>';  
								str2+='</td>';  
							str2+='</tr>';    
						}
					}
					
					str2+='</tbody>';
					str2+='</table>';
					$("#relativeDivId").html(str2);
					for(var i in result[1]){
						if(result[1][i].status == "noStatus"){    
							$("#familyHideSelectBoxId"+i).html(globalSrt);        
						}
					}
					if(minValue == 0 && relativeTotalCount > 10){
						$("#relativePaginationId").pagination({
						items: relativeTotalCount,
						itemsOnPage: 10,
						cssStyle: 'light-theme',
						onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getMembersDetails(surveyUserId,tabUserId,webUserId,userName,userMobile,num,"Relative");
					}
				});
			}	
		}
	}      		
	$(document).on('click','#globalSelectOwnId',function(){
		if($(this).is(':checked')){
			$(".localSelectOwnCls").prop( "checked", true);
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else{
			$(".localSelectOwnCls").prop( "checked", false);
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);    
		}
		$('.localSelectOwnCls').each(function(){
			if($(this).is(':checked')){
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).show();
			}else{
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).hide(); 
			}  
		});
	}); 
	$(document).on('click','#globalSelectFamilyId',function(){
		if($(this).is(':checked')){
			$(".localSelectFamilyCls").prop( "checked", true);  
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else{
			$(".localSelectFamilyCls").prop( "checked", false);
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);    
		}
		$('.localSelectFamilyCls').each(function(){
			if($(this).is(':checked')){
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).show();
			}else{    
				var selectReasonId = $(this).attr("attr_reason_id");
				$("#"+selectReasonId).hide(); 
			}  
		});
	});
	
	//by selecting single check box show and hide the chexk box. for own tab
	$(document).on('click','.localSelectOwnCls',function(){
		if($(this).is(':checked')){
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
		}
		var count = $("input.localSelectOwnCls:checked").length;
		if(count >= 1){
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else if( count == 0){
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);   
		}
	});
	//by selecting single check box show and hide the chexk box. for family tab
	$(document).on('click','.localSelectFamilyCls',function(){
		
		if($(this).is(':checked')){   
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).show();
		}else{
			var selectReasonId = $(this).attr("attr_reason_id");
			$("#"+selectReasonId).hide(); 
		}
		var count = $("input.localSelectFamilyCls:checked").length;
		if(count >= 1){
			$('.singleApproveCls').prop('disabled', true);
			$('.singleRejectCls').prop('disabled', true);
		}else if( count == 0){
			$('.singleApproveCls').prop('disabled', false);
			$('.singleRejectCls').prop('disabled', false);   
		}
	});
	//single rejected 
	$(document).on('click','.singleRejectCls',function(){
		var cadreId = $(this).attr("attr_cadre_id");
		var selectReasonId = $(this).attr("attr_reason_id");
		var reasonId = $("#"+selectReasonId).val();
		var rejectList = [];
		rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "userId" : 3256 }); 
		var singleReject = {"data" : rejectList};
		$.ajax({
			type:'GET',      
			url: 'updateRejectListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				console.log(true);
			}else{
			}
		});		
	});
	//bulk reject
	$(document).on('click','#bulkRejectId',function(){  
		var cadreId = '';
		var selectReasonId = '';
		var reasonId = '';
		
		var rejectList = [];
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "userId" : 3256 });     
				}
			});
		}else{
			$('.localSelectFamilyCls').each(function(){
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					selectReasonId = $(this).attr("attr_reason_id");
					reasonId = $("#"+selectReasonId).val();
					rejectList.push({"cadreId" : cadreId, "reasonId" : reasonId, "userId" : 3256 });     
				}
			});
		}
		
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateRejectListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				console.log(true);
			}else{
			}
		});
	});
	//single approve
	$(document).on('click','.singleApproveCls',function(){
		 
		var cadreId = $(this).attr("attr_cadre_id");
		$("#groupingApprovedYes").attr("attr_cadre_id",cadreId);
		$("#confirmModalId").modal("show");
		var rejectList = [];
		rejectList.push({"cadreId" : cadreId, "userId" : 3256 }); 
		var singleReject = {"data" : rejectList};
		$.ajax({
			type:'GET',      
			url: 'updateApproveListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				console.log(true);         
			}else{
			}
		});  
	});
	//bulk approve
	$(document).on('click','#bulkApproveId',function(){    
		var cadreId = '';
		var rejectList = [];
		if($("#self").hasClass('active')){
			$('.localSelectOwnCls').each(function(){  
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					rejectList.push({"cadreId" : cadreId, "userId" : 3256 });     
				}
			});
		}else{
			$('.localSelectFamilyCls').each(function(){  
				if($(this).is(':checked')){
					cadreId = $(this).attr("attr_cadre_id");
					rejectList.push({"cadreId" : cadreId, "userId" : 3256 });     
				}
			});
		}  
		
		var singleReject = {"data" : rejectList};        
		$.ajax({
			type:'GET',      
			url: 'updateApproveListAction.action',      
			dataType: 'json',
			data: {task:JSON.stringify(singleReject)}  
		}).done(function(result){
			if(result != null){  
				console.log(true);         
			}else{
			}
		});
	});
   //*******************************************
 /*  $(document).on("click",".approveCls",function(){
	  var cadreId = $(this).attr("attr_cadre_id");
	  $("#groupingApprovedYes").attr("attr_cadre_id",cadreId);
	  $("#confirmModalId").modal("show");  
  });	 */
  /* $(document).on("click",".rejectedCls",function(){
	    var cadreId = $(this).attr("attr_cadre_id");
	    $("#submitBtnReasonId").attr("attr_cadre_id",cadreId);
		$(".reasonErrorCls").html(' ');
	    $("#rejectedModalId").modal("show");
		
  });	
  $(document).on("click","#submitBtnReasonId",function(){
	  var cadreId = $(this).attr("attr_cadre_id");
	  var reasonId = $("#rsnSlctBxId").val();
	   if(reasonId == 0){
		   $(".reasonErrorCls").html("Please Select Reason.");
		   return;
	   }
	   $(".reasonErrorCls").html(' ');
	 
  });
  $(document).on("click","#groupingApprovedYes",function(){
	  var cadreId = $(this).attr("attr_cadre_id");
	 
  });
  $(document).on("click","#groupingApprovedNo",function(){
	  var cadreId = $(this).attr("attr_cadre_id");
	    $("#confirmModalId").modal("hide");  
  }); */
  //***************************************************
  