$(document).on("click",".stateClass",function(){
	getStatusWisePrintingConstituencyDetails();
	getDistrictWiseStatusWiseConstituenciesCounts();
	getStatusWiseVendorWisePrintingConstituencyDetails();
});

$(document).on("change","#vendorId",function(){
	getStatusWisePrintingConstituencyDetails();
	getDistrictWiseStatusWiseConstituenciesCounts();
});

$(document).on("click",".applyBtn",function(){
	getStatusWisePrintingConstituencyDetails();
	getDistrictWiseStatusWiseConstituenciesCounts();
	getStatusWiseVendorWisePrintingConstituencyDetails();
});

function getStatusWisePrintingConstituencyDetails(){
	var stateId = $("input[name='radio']:checked").val();
	var vendorId = $("#vendorId").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var jsObj = { 
		stateId : stateId,
		vendorId : vendorId,
		startDate : fromDate,
		endDate : toDate
	}
	$.ajax({
		type : 'GET',
		url : 'getStatusWisePrintingConstituencyDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			buildStatusWisePrintingDetails(result);
		}
	});
}

function buildStatusWisePrintingDetails(result){
	var str='';
	
	if(result.overAllList != null && result.overAllList.length > 0){
		for(var i in result.overAllList){
			if(result.overAllList[i].id != 8 && result.overAllList[i].id != 9){
				str+='<div class="col-md-2 col-xs-12 col-sm-4">';
					str+='<div class="pad_10">';
						str+='<p><b>'+result.overAllList[i].name+' Constituencies</b></p>';
						str+='<h3>'+result.overAllList[i].count+'</h3>';
						str+='<small>('+result.overAllList[i].cardsCount+' Records)</small>';
					str+='</div>';
				str+='</div>';
			}
		}
	}
	$("#statusWisePrintingDiv").html(str);
	
	$("#todayHeadingId").html(result.todayDate);
	var str1='';
	
	if(result.todayList != null && result.todayList.length > 0){
		for(var i in result.todayList){
			if(result.todayList[i].id != 8 && result.todayList[i].id != 9){
				str1+='<div class="col-md-2 col-xs-12 col-sm-4">';
					str1+='<div class="pad_10">';
						str1+='<p><b>'+result.todayList[i].name+' Constituencies</b></p>';
						str1+='<h3>'+result.todayList[i].count+'</h3>';
						str1+='<small>('+result.todayList[i].cardsCount+' Records)</small>';
					str1+='</div>';
				str1+='</div>';
			}
		}
	}
	$("#todayStatusWisePrintingDiv").html(str1);
}

function getDistrictWiseStatusWiseConstituenciesCounts(){
	
	var vendorId = $("#vendorId").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var jsObj = { 
		vendorId : vendorId,
		startDate : fromDate,
		endDate : toDate
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictWiseStatusWiseConstituenciesCountsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			buildApDistrictWisePrintingDetails(result);
			buildTsDistrictWisePrintingDetails(result);
		}
	});
}

function buildApDistrictWisePrintingDetails(result){
	$("#distWiseCards").html('');
	if(result.overAllList != null && result.overAllList.length > 0){
		if(result.overAllList != null && result.overAllList.length > 0){
			var str='';
			str+='<div class="row">';
			str+='<ul class="apPrintDetailsSlickApply">';
			for(var i in result.overAllList){
				str+='<li class="col-md-3 col-xs-12 col-sm-3">';
				str+='<div id="printDetailsGraphId'+i+'" class="" style="height:200px" ></div>';
				str+='</li>';
			}
			str+='</div>';
		}
		$("#distWiseCards").html(str);
		
		if(result.overAllList != null && result.overAllList.length > 0){
			for(var i in result.overAllList){
				var districtNamesArray =[];
				districtNamesArray.push(result.overAllList[i].name);
				var mainArray=[];
			
				if(result.overAllList[i].overAllList !=null && result.overAllList[i].overAllList.length>0){
					for(var j in result.overAllList[i].overAllList){
						if(result.overAllList[i].overAllList[j].id < 8){
							var countArray=[];
							countArray.push(result.overAllList[i].overAllList[j].count)
							var obj1 = {
									name: result.overAllList[i].overAllList[j].name,
									data: countArray
								};
							mainArray.push(obj1)
						}
					}
				}
				$("#printDetailsGraphId"+i).highcharts({
				colors:['#B091BB','#9CCBCC','#C8CA92','#71F0CC','#FF6F9B'],
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
					categories: districtNamesArray
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels:{enabled: false},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},

				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>'
							;
					}
				},
				legend: {
					enabled: false,				
				},
				plotOptions: {
					column: {
						//stacking: 'normal'
						dataLabels:{
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<b>' + this.y + '</b>';
								}
							}
						},
					}
				},
				series: mainArray
			});
			}
		}
		$(".apPrintDetailsSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		});
	}
	else
		$("#distWiseCards").html('<h4 style="color:red"> NO DATA AVAILABLE...</h4>');
}


function buildTsDistrictWisePrintingDetails(result){
	$("#distWiseCards1").html('');
	if(result.todayList != null && result.todayList.length > 0){
		if(result.todayList != null && result.todayList.length > 0){
			var str='';
			str+='<div class="row">';
			str+='<ul class="tsPrintDetailsSlickApply">';
			for(var i in result.todayList){
				str+='<li class="col-md-3 col-xs-12 col-sm-3">';
				str+='<div id="tsPrintDetailsGraphId'+i+'" class="" style="height:200px" ></div>';
				str+='</li>';
			}
			str+='</div>';
		}
		$("#distWiseCards1").html(str);
		
		if(result.todayList != null && result.todayList.length > 0){
			for(var i in result.todayList){
				var districtNamesArray =[];
				districtNamesArray.push(result.todayList[i].name);
				var mainArray=[];
			
				if(result.todayList[i].overAllList !=null && result.todayList[i].overAllList.length>0){
					for(var j in result.todayList[i].overAllList){
						if(result.todayList[i].overAllList[j].id < 8){
							var countArray=[];
							countArray.push(result.todayList[i].overAllList[j].count)
							var obj1 = {
									name: result.todayList[i].overAllList[j].name,
									data: countArray
								};
							mainArray.push(obj1)
						}
					}
				}
				$("#tsPrintDetailsGraphId"+i).highcharts({
				colors:['#B091BB','#9CCBCC','#C8CA92','#71F0CC','#FF6F9B'],
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
					categories: districtNamesArray
				},

				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					labels:{enabled: false},
					title: {
						text: ''
					},
					stackLabels: {
						enabled: true,
						style: {
							fontWeight: 'bold',
							color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
						}
					}
				},

				tooltip: {
					formatter: function () {
						return '<b>' + this.x + '</b><br/>' +
							this.series.name + ': ' + this.y + '<br/>'
							;
					}
				},
				legend: {
					enabled: false,				
				},
				plotOptions: {
					column: {
						//stacking: 'normal'
						dataLabels:{
							enabled: true,
							formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<b>' + this.y + '</b>';
								}
							}
						},
					}
				},
				series: mainArray
			});
			}
		}
		$(".tsPrintDetailsSlickApply").slick({
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,
			 infinite: false,
			 swipeToSlide:false,
			 swipe:false,
			 touchMove:false
		});
	}
	else
		$("#distWiseCards1").html('<h4 style="color:red"> NO DATA AVAILABLE...</h4>');
}

function getStatusWiseVendorWisePrintingConstituencyDetails(){
	
	var stateId = $("input[name='radio']:checked").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var jsObj = { 
		stateId : stateId,
		fromDate : fromDate,
		toDate : toDate
	}
	$.ajax({
		type : 'GET',
		url : 'getVendorWiseStatusWiseConstituenciesDetailsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		if(result != null){
			buildStatusWiseVendorWisePrintingDetails(result);
		}
	});
}

function buildStatusWiseVendorWisePrintingDetails(result){
	var str='';
	
	str+='<table class="table table-bordered" style="border: 0px;">';
	if(result.overAllList != null && result.overAllList.length > 0){
		for(var i in result.overAllList){
			str+='<tr>';
				str+='<td><h4>'+result.overAllList[i].name+'</h4></td>';
				if(result.overAllList[i].overAllList != null && result.overAllList[i].overAllList.length > 0){
					for(var j in result.overAllList[i].overAllList){
						if(result.overAllList[i].overAllList[j].id != 8 && result.overAllList[i].overAllList[j].id != 9){
							str+='<td>';
								str+='<small>'+result.overAllList[i].overAllList[j].name+' Constituencies</small>';
								str+='<h4>'+result.overAllList[i].overAllList[j].count+'</h4>';
							str+='</td>';
						}
					}
					str+='<td>';
						str+='<small>Total Avg Per Day Print Capacity</small>';
						str+='<h4>10000</h4>';
						str+='<span class="text-success">View Day Wise</span>';
					str+='</td>';
				}
			str+='</tr>';
			
			str+='<tr>';
				str+='<td class="bg_EE pos_relative"><div class="arrowTOp">Today <br/><small>'+result.todayDate+'</small></div></td>';
				if(result.todayList != null && result.todayList.length > 0){
					if(result.todayList[i].overAllList != null && result.todayList[i].overAllList.length > 0){
						for(var j in result.todayList[i].overAllList){
							if(result.todayList[i].overAllList[j].id != 8 && result.todayList[i].overAllList[j].id != 9)
								str+='<td class="bg_EE">'+result.todayList[i].overAllList[j].count+'</td>';
						}
						str+='<td class="bg_EE">1000</td>';
					}
				}
			str+='</tr>';
		}
	}
	str+='</table>';
	
	$("#vendorWiseDivId").html(str);
}