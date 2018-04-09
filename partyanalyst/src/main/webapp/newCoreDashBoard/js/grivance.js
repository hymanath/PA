var smallSpinner='<i class="fa fa-spinner fa-spin" style="font-size:24px"></i>';
onloadcalls();
var fromDate=moment().startOf('month').format("MM-DD-YYYY");
var toDate=moment().format("MM-DD-YYYY");
$("#daterangeId").daterangepicker({
 opens: 'left',
 startDate: fromDate,
 endDate: toDate,
 locale: {
 format: 'MM-DD-YYYY'
 } ,
 ranges: {
	'Today' : [moment(), moment()],
	'Yesterday': [moment().subtract(1, 'day'), moment().subtract(1, 'day')],
	'This Month': [moment().startOf('month'), moment()],
	'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	'This Year': [moment().startOf('Year'), moment()],
	'Last Year': ['01-01-2017', moment()],
	'OverAll':['28-11-2016',moment()]
 } 
 });
$('#daterangeId').on('apply.daterangepicker', function(ev, picker) { 
	fromDate =picker.startDate.format('MM-DD-YYYY');       
	toDate = picker.endDate.format('MM-DD-YYYY');
	onloadcalls();
});
function onloadcalls(){
	getCadreGreivienceEfficiency();
	getCadreGreivienceIssueType();
	getCategoryAndIssuetypeStatusCount();
	getgrivanceDetailsBySearch("Complaint ID","");
}
function getCadreGreivienceEfficiency(){
		var jobj = {                                      
			daysArr : [30,60,90,180,365],
			grievanceReqType:"Grievance",
			enrollmentyearId:0,
		}
		$.ajax({
			type : "POST",
			url  : "getEffiencyDetailsForgrivanceByDatesAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){
				 buildGrivanceEfficiency(result);
			}
		});
}
function buildGrivanceEfficiency(result){
	var str='';
		str='<div class="row">';
		for(var i in result){
			str+='<div class="modifyTabs col-md-4 col-xs-12 col-sm-4 m_top5">';
				str+='<div class="well well-sm">';
					str+='<p class="text-muted">'+result[i].name+'</p>';
					str+='<h4>'+result[i].efficencyPercentage+'%</h4>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
	$("#efficiencyId").html(str);
}
//getCadreGreivienceIssueType();
function getCadreGreivienceIssueType(){
		var jobj = {                                      
			"searchType":"Mobile Number",
			"searchValue":"9063845874",
			"enrollmentyearId":0,
			"fromDate" :fromDate,
			"toDate" : toDate
		}
		$.ajax({
			type : "POST",
			url  : "getgrivanceDetailsForIssueTypeAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){
				buildPieChart(result);
			}
		});
} 
function buildPieChart(result){
$('#donutchart').html('');
if(result == null || result.length == 0)
return;

	var dataArr = [];
	var dataArr1 = [];
	var colorsArr =new Array();
	var totalStatusCount=0;
	for(var i in result)
	{
		dataArr = new Array();
		colorsArr.push("#306F8F","#FF9700","#C1392B","#E84B3C","#2CCA6F","#439C44");
		dataArr.push(result[i].issueType, result[i].grivanceCount);
		totalStatusCount=totalStatusCount+result[i].grivanceCount;
		dataArr1.push(dataArr);
	}
	
	Highcharts.setOptions({
        colors: colorsArr
    });
	$('#donutchart').highcharts({
       chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            
        },
		title: {
                  useHTML:'true',
				  margin: 0,
                  text: "<div class='row'><div class='col-sm-12 col-xs-12'><h4><b>TOTAL-"+totalStatusCount+"</b></h4></div></div>",
                  y:0,
                  x:100,
                  align: 'center',
                  verticalAlign: 'middle',
		},	
		plotOptions: {	
			pie: {
                size:'80%',
				innerSize: '60%',
                showInLegend: true,
				dataLabels: {
                    enabled: false,
				}
            }, 
		},
		legend: {
			padding: 10,
			itemMarginTop: 5,
			itemMarginBottom: 5,
			itemStyle: {
               fontWeight: 'bold',
				fontFamily:'roboto',
				fontSize:'16px',
				lineHeight: '14px'
            },
			enabled: true,
            layout: 'vertical',
            align: 'left',
            verticalAlign: 'middle',
			useHTML: true,
			
			labelFormatter: function() {
				return this.name + ' ' + this.y + '';
			}
		},
		
		series:[{
            name : 'Count',
            data: dataArr1
        }]
	});
}
function getCategoryAndIssuetypeStatusCount(){
	$("#verticleTabLists").html('');
	var jobj = {
					inputType : 'AllData',
					fromDate :fromDate,
					toDate : toDate,
					stateIds : '1,2',
					enrollmentYrId:0,
					task : 'Grievance'
				}
		$.ajax({
			type : "POST",
			url  : "getCategoryAndIssuetypeStatusCountAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){
				 console.log(result);
				 buildHorizontalTabs(result);
				 buidVerticleTabs(result);
			}
		});
}
function buildHorizontalTabs(result){
	var str='';
	str+='<ul class="nav nav-tabs mainHorTabStyle clickResponsiveHeight">';
	for(var i  in result){
		if(i==0){
			str+='<li class="active"><a href="#'+result[i].status.toLowerCase()+'" attr_tab_id="'+result[i].status.toLowerCase()+'" data-toggle="tab"><h5 style="color:#333;"><b>'+result[i].status+' <span id="'+result[i].status+'horiZontTabsCount"></span></b></h5></a></li>';
			//alert(result[i].status);
		}else{
			str+='<li><a href="#'+result[i].status.toLowerCase()+'" attr_tab_id="'+result[i].status.toLowerCase()+'" data-toggle="tab"><h5 style="color:#333;"><b>'+result[i].status+' <span id="'+result[i].status+'horiZontTabsCount"></span></b></h5></a></li>';
		}
	}
		str+='<li><a href="#totalSubId" attr_tab_id="TotalCls" data-toggle="tab"><h5 style="color:#333;"><b>'+"Total"+' <span id="totalTabsId"></span></b></h5></a></li>';
		str+='</ul>';
	
	$("#horizontalTabs").html(str);			
}
function buidVerticleTabs(result){
	var str='';
	var others;
	var otherCount = 0;
	var verticleTabsSttatusCountArr=[];
	var otherStatusDef=[];
	var othersStatusList=[];
	var verticleTabsSttatusCount;
	var horiTabsStatusCount;
	var totalTabsCount;
	var totalOtherCount;
	var totalId=[];
	var verticalOtherTabsStatusCountArr=[];
	str+='<ul class="tab-content" style="margin-left:-50px !important;; width:530px !important;; padding:0px !important;">';
	for(var i in result){
		if(i==0){
			str+='<li class="tab-pane active" id="'+result[i].status.toLowerCase()+'">';
		}else{
			str+='<li class="tab-pane" id="'+result[i].status.toLowerCase()+'">';
		}
				str+='<ul class="nav nav-tabs modifyTabs tabsViewStyle col-sm-5 col-md-5 tabHeight'+result[i].status.toLowerCase()+'">';
					others=[];
					for(var j in result[i].subList){
						if(result[i].subList[j].status!="Others"){
							if(j==0){
								str+='<li role="presentation" class="active" ><a href="#'+result[i].status.toLowerCase()+j+'"  data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>'+result[i].subList[j].status+'<span class="'+result[i].status.toLowerCase()+'" id="'+result[i].subList[j].status.trim().replace(/\s/g,'')+'TabListCount'+i+'"></span></b></h4></a></li>';
							}else {
								str+='<li role="presentation" ><a href="#'+result[i].status.toLowerCase()+j+'" data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>'+result[i].subList[j].status+'<span class="'+result[i].status.toLowerCase()+'" id="'+result[i].subList[j].status.trim().replace(/\s/g,'')+'TabListCount'+i+'"></span></b></h4></a></li>';
							}
						}else{
							others.push({"href":result[i].status.toLowerCase()+j,"text":result[i].subList[j].status,"spanClass":result[i].status.toLowerCase(),"spanId":result[i].subList[j].status.trim().replace(/\s/g,'')+'TabListCount'+i});
						}
					}
					if(others.length > 0){
						str+='<li role="presentation active" ><a href="#'+others[0].href+'" data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>'+others[0].text+'<span class="'+others[0].spanClass+'" id="'+others[0].spanId+'"></span></b></h4></a></li>';
					}			
				str+='</ul>';
				str+='<ul class="tab-content col-sm-7" style="padding-left:15px !important;">';
					for(var j in result[i].subList){
						verticleTabsSttatusCount=0;
						if(j==0){
							str+='<li class="tab-pane active" id="'+result[i].status.toLowerCase()+j+'">';
						}else{
							str+='<li class="tab-pane" id="'+result[i].status.toLowerCase()+j+'">';
						}
							str+='<table class="table borderLess tableHeight'+result[i].status.toLowerCase()+'" style="background-color:#fff;border:1px solid #94979A;">';
								str+='<tbody>';
								for(var k in result[i].subList[j].subList){
									str+='<tr>';
									str+='<td><p style="width:16px;height:12px;background-color:'+result[i].subList[j].subList[k].color+'"></td>';
									str+='<td><h4 style="color:#333;">'+result[i].subList[j].subList[k].status+'</h4></td>';
									str+='<td><h4 style="color:#333;">'+result[i].subList[j].subList[k].count+'</td>';
									str+='</h4></tr>';
									verticleTabsSttatusCount=verticleTabsSttatusCount+result[i].subList[j].subList[k].count;
									  /* if((result[i].subList[j].status).toLowerCase() === "others"){
										  otherStatusDef.push({"color":result[i].subList[j].subList[k].color});
									  } */
								  }
								str+='</tbody>';
							str+='</table>';
						str+='</li>';
						verticleTabsSttatusCountArr.push({"id":result[i].subList[j].status.trim().replace(/\s/g,'')+"TabListCount"+i,"count":verticleTabsSttatusCount});
					}
				str+='</ul>';
			str+='</li>';
	}
//for creating GrievanceEfficiencyTotal
var other=[];
for(var i in verticleTabsSttatusCountArr){
	if((verticleTabsSttatusCountArr[i].id).toString().replace('TabListCount', '').replace(/[0-9]+/,'').toLowerCase().trim() === "others"){
		otherCount+=verticleTabsSttatusCountArr[i].count;
	}else{
		other.push({"count":verticleTabsSttatusCountArr[i].count});
	}		
}
for(var i in result){
	for(var j in result[i].subList){
		if((result[i].subList[j].status).toLowerCase() === "others"){
			for(var k in result[i].subList[j].subList){
				verticalOtherTabsStatusCountArr.push({"id":result[i].subList[j].subList[k].status,"count":result[i].subList[j].subList[k].count});	
			}
		}
	}
}
var statuscount = true;
for(var i in result){
	for(var j in result[i].subList){
		if((result[i].subList[j].status).toLowerCase() === "others"){
			if(statuscount === true){
				for(var k in result[i].subList[j].subList){
					var count=0;
					for(var x in verticalOtherTabsStatusCountArr){
						if((verticalOtherTabsStatusCountArr[x].id).toLowerCase()=== (result[i].subList[j].    subList[k].status).toLowerCase()){
							count+=verticalOtherTabsStatusCountArr[x].count;
						}
					}
					othersStatusList.push({"id":result[i].subList[j].subList[k].status,"count":count,"colors":result[i].subList[j].subList[k].color});
				}
				statuscount = false;
			}	
		}
	}
}
str+='<li class="tab-pane" id="totalSubId" >';
	str+='<ul class="nav nav-tabs modifyTabs tabsViewStyle col-sm-5 col-md-5 tabHeightTotalCls">';
		var countOfTotalTabVert=0;
			for(var i in result){
				for(var j in result[i].subList){
					if((result[i].subList[j].status).toLowerCase() != "others" ){
						if(i==0 && j==0){
							str+='<li class="active" role="presentation"><a href="#totalSum'+result[i].subList[j].status.trim().replace(/\s/g,'')+j+'"  data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>'+result[i].subList[j].status+' <span class="totalSum'+result[i].status.toLowerCase()+'" id="">'+other[countOfTotalTabVert].count+'</span></b></h4></a></li>';
							countOfTotalTabVert++;
						}else{
							str+='<li role="presentation"><a href="#totalSum'+result[i].subList[j].status.trim().replace(/\s/g,'')+j+'"  data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>'+result[i].subList[j].status+' <span class="totalSum'+result[i].status.toLowerCase()+'" id="">'+other[countOfTotalTabVert].count+'</span></b></h4></a></li>';
							countOfTotalTabVert++;
						}		
					}
				}
			}
		if(otherCount!=0)
			str+='<li role="presentation"><a href="#totalSumOthersTabId"  data-toggle="tab" style="padding-left:10px;"><h4 style="color:#333;"><b>Others&nbsp;<span class="totalSum" id="">'+otherCount+'</span></b></h4></a></li>';
	str+='</ul>';
	str+='<ul class="tab-content col-sm-7">';
	for(var i in result){
		for(var j in result[i].subList){
			if((result[i].subList[j].status).toLowerCase() != "others"){
				if(i==0 && j==0){
					str+='<li class="tab-pane active" id="totalSum'+result[i].subList[j].status.trim().replace(/\s/g,'')+j+'">';
				}else{
					str+='<li class="tab-pane" id="totalSum'+result[i].subList[j].status.trim().replace(/\s/g,'')+j+'">';
				}
			str+='<table class="table borderLess tableHeightTotalCls" style="background-color:#fff;border:1px solid #94979A; height:0px;">';
				str+='<tbody>';
					for(var k in result[i].subList[j].subList){
						str+='<tr>';
							str+='<td><p style="width:20px;height:12px;background-color:'+result[i].subList[j].subList[k].color+'"></td>';
							str+='<td><h4 style="color:#333;">'+result[i].subList[j].subList[k].status+'</h4></td>';
							str+='<td><h4 style="color:#333;">'+result[i].subList[j].subList[k].count+'</td>';
						str+='</h4></tr>';
					}							  
				str+='</tbody>';
			str+='</table>';
		str+='</li>';
			}
		}
	}			
	str+='<li class="tab-pane" id="totalSumOthersTabId">';
		str+='<table class="table borderLess tableHeightTotalCls" style="background-color:#fff;border:1px solid #94979A; height:0px;">';
			str+='<tbody>';
				if(othersStatusList.length > 0){
					for(var k in othersStatusList){
						str+='<tr>';
							str+='<td><p style="width:20px;height:12px;background-color:'+othersStatusList[k].colors+'"></td>';
							str+='<td><h4 style="color:#333;">'+othersStatusList[k].id+'</h4></td>';
							str+='<td><h4 style="color:#333;">'+othersStatusList[k].count+'</h4></td>';
						str+='</tr>';
					}
				}								
			str+='</tbody>';
		str+='</table>';
	str+='</li>';
	str+='</ul>';
	str+='</li>';
	str+='</ul>';
$("#verticleTabLists").html(str);
for(var i in verticleTabsSttatusCountArr){
	$("#"+verticleTabsSttatusCountArr[i].id).html(" "+verticleTabsSttatusCountArr[i].count);
}
horiTabsStatusCount=0;
totalTabsCount = 0;
$('.govt').each(function() {
    horiTabsStatusCount=horiTabsStatusCount+Number($(this).html());
});
$("#GovthoriZontTabsCount").html(horiTabsStatusCount);
totalTabsCount = horiTabsStatusCount;
horiTabsStatusCount=0;
$('.party').each(function() {
    horiTabsStatusCount=horiTabsStatusCount+Number($(this).html());
});
$("#PartyhoriZontTabsCount").html(horiTabsStatusCount);
totalTabsCount+= horiTabsStatusCount;
horiTabsStatusCount=0;
$('.welfare').each(function() {
    horiTabsStatusCount=horiTabsStatusCount+Number($(this).html());
});
$("#WelfarehoriZontTabsCount").html(horiTabsStatusCount);
totalTabsCount+= horiTabsStatusCount;
$("#totalTabsId").html(totalTabsCount);
//alert(totalTabsCount);	
}
function getgrivanceDetailsBySearch(searchType,searchValue){
	$("#spinnerForComplaintsId").html(smallSpinner);
		$("#buildGrivanceDetailsBySearchTableId").html('');	
		var jobj = {                                      
			"searchType":searchType,
			"searchValue":searchValue,
			"enrollmentyearId":0                   
		}
		$.ajax({
			type : "POST",
			url  : "getgrivanceDetailsBySearchAction.action",  
			dataType: 'json',
			data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			if(result!=null){  
				console.log(result);
				$("#spinnerForComplaintsId").html('');
				 buildGrivanceDetailsBySearchTable(result);
			}
		});
}
function buildGrivanceDetailsBySearchTable(result){
	var str='';
		str='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>ConsitutencyName</th>';
						str+='<th>Date</th>';
						str+='<th>Description</th>';
						str+='<th>District Name</th>';
						str+='<th>Id</th>';
						str+='<th>Issue Type</th>';
						str+='<th>MandalName</th>';
						str+='<th>Name</th>';
						str+='<th>Number</th>';
						str+='<th>Status</th>';
						str+='<th>Subject</th>';
						str+='<th>Updated Date</th>';
						str+='<th>Village Name</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result)
				{
					str+='<tr>';
						str+='<td>'+result[i].consitutencyName+'</td>';
						str+='<td>'+result[i].date+'</td>';
						str+='<td>'+result[i].description+'</td>';
						str+='<td>'+result[i].districtName+'</td>';
						str+='<td>'+result[i].id+'</td>';
						str+='<td>'+result[i].issueType+'</td>';
						str+='<td>'+result[i].mandalName+'</td>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>'+result[i].number+'</td>';
						str+='<td>'+result[i].status+'</td>';
						str+='<td>'+result[i].subject+'</td>';
						str+='<td>'+result[i].updatedDate+'</td>';
						str+='<td>'+result[i].villageName+'</td>';
					str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#buildGrivanceDetailsBySearchTableId").html(str);
}
$(document).on("click",".clickResponsiveHeight li",function(){
	var heightOfLiId='';
	var heightOfLi='';
	var heightIncr=0;
	heightOfLiId=$(this).find('a').attr('attr_tab_id');
	heightOfLi=$(".tabHeight"+heightOfLiId).height();
	
	heightIncr=Number(heightOfLi)+5;
	$('.tableHeight'+heightOfLiId).css("height", heightIncr.toString());
});
$(document).on("click","#getSearchByComplaintesId",function()
{
	var searchValue='';
	var searchComplaintId='';
	searchValue=$("#searchValue").val().trim();
	searchComplaintId=$("#searchComplaintId").val().trim();
	getgrivanceDetailsBySearch(searchComplaintId,searchValue);
	
});
