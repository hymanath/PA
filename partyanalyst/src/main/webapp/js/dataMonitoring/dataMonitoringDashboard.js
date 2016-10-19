  $(document).on("click",".applyBtn",function(){
	getDataMonitoringOverViewDetails();
  });
  
  function getDataMonitoringOverViewDetails(){
 $("#dataMonitoringOverviewTblId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $("#dataMonitoringOverviewHighChartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $("#webTabOnlineHighchartDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
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
		  str+='<tbody>';
				 str+='<tr>';
					str+='<td class="text-capital">active team members ('+result.activyTeamMemberCnt+')</td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
					str+='<td> - </td>';
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">total registrations ('+result.totalRegCnt+')</td>';
					if(result.totalTabRegCnt > 0){
					   str+='<td>'+result.totalTabRegCnt+'</td>';
					}else{
						str+='<td> - </td>';
					}
					if(result.totalWebRegCnt > 0){
					  str+='<td>'+result.totalWebRegCnt+'</td>';
					}else{
					  str+='<td> - </td>';
					}
					if(result.totalOnlineRegCnt > 0){
					  str+='<td>'+result.totalOnlineRegCnt+'</td>';
					}else{
				      str+='<td> - </td>';
					}
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">verified-passed ('+result.totalVerifyRegCnt+')</td>';
					if(result.totalTabVerifyCnt > 0){
					   str+='<td>'+result.totalTabVerifyCnt+'</td>';
					}else{
						str+='<td> - </td>';
					}
					if(result.totalWebVerifyCnt > 0){
					  str+='<td>'+result.totalWebVerifyCnt+'</td>';
					}else{
					  str+='<td> - </td>';
					}
					if(result.totalOnlineVerifyCnt > 0){
					  str+='<td>'+result.totalOnlineVerifyCnt+'</td>';
					}else{
				      str+='<td> - </td>';
					}
				 str+='</tr>';
				  str+='<tr>';
					str+='<td class="text-capital">verified- junk/rejected ('+result.totalVerifyRejectCnt+')</td>';
						if(result.totalTabVerifyRejectedCnt > 0){
						   str+='<td>'+result.totalTabVerifyRejectedCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result.totalWebVerifyRejectedCnt > 0){
						  str+='<td>'+result.totalWebVerifyRejectedCnt+'</td>';
						}else{
						  str+='<td> - </td>';
						}
						if(result.totalOnlineVerifyRejectedCnt > 0){
						  str+='<td>'+result.totalOnlineVerifyRejectedCnt+'</td>';
						}else{
						  str+='<td> - </td>';
						}
				 str+='</tr>'; str+='<tr>';
					str+='<td class="text-capital">pending ('+result.totalPendingCnt+')</td>';
					if(result.totalTabPendingCnt > 0){
						   str+='<td>'+result.totalTabPendingCnt+'</td>';
						}else{
							str+='<td> - </td>';
						}
						if(result.totalWebPendingCnt > 0){
						  str+='<td>'+result.totalWebPendingCnt+'</td>';
						}else{
						  str+='<td> - </td>';
						}
						if(result.totalOnlinePendingCnt > 0){
						  str+='<td>'+result.totalOnlinePendingCnt+'</td>';
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
				 categories:"",
				labels: {
						rotation: -45,
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
				title: {
					text: ''
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
				name: 'total registrations',
				data: [result.totalRegCnt]
			}, {
				name: 'verified-passed',
				data: [result.totalVerifyRegCnt]
			}, {
				name: 'verified- junk/rejected',
				data: [result.totalVerifyRejectCnt]
			}, {
				name: 'pending',
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
            }
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
								return Highcharts.numberFormat(this.y,1) +'%';
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
					