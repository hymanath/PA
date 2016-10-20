  $(document).on("click",".applyBtn",function(){
	getDataMonitoringOverViewDetails();
  });
  
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
			colors: ['#4C7BA7','#9F526F','#DB4C21','#495763'],
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
		colors: ['#5E3243','#DB4C21','#495763'],
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
		 console.log(result);
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
										  str+='<td><button attr_survery_user_id='+result[i].surveryUserId+' attr_user_id='+tabUserList[j].tabUserId+' class="btn btn-success getMemberDtlsCls">Verify Records</button></td>';
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
								  str+='<td><button  attr_user_id='+result[j].surveryUserId+' class="btn btn-success getMemberDtlsCls">Verify Records</button></td>';
								  str+='</tr>';	
								 }
						}
				  str+='</tbody>';
               str+='</table>';
		$("#userWiseRegDivId").html(str);
		$("#userWiseDataTableId").dataTable();
 }
  $(document).on("click",".getMemberDtlsCls",function(){
	 
	   var surveryUserId = $(this).attr("attr_survery_user_id");
	   var userId = $(this).attr("attr_user_id");
	    if (typeof(surveryUserId) == "undefined"){
		  surveryUserId	 = 0;
		}
   });
   
  