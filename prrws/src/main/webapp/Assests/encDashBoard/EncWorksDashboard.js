var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var levelWiseOverviewArr = ['state','district','constituency','mandal'];
var currentYear="";
if(moment().format('MM').toString < "04"){
	currentYear = moment().year()
}else{
	currentYear = moment().year()+1;
}
var glStartDate = "01-04-2014";
var glEndDate = "01-04-"+currentYear;

$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
getAllFiniancialYears();
getAllSchemes();
function getAllSchemes(){
	$("#schemeDivId").html('');
	var json = {
	}
	$.ajax({                
		type:'POST',    
		url: 'getPRProgramsCodeAndName',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length >0){
			for(var i in result){
				$("#schemeDivId").append("<option value="+result[i].subGrantId+" selected>"+result[i].programName+'-'+result[i].subGrantName+"</option>");
				
			}
			$('#schemeDivId').multiselect("destroy");
			$('#schemeDivId').multiselect({
				enableFiltering: true,
				includeSelectAllOption: true,
				selectAllText: 'All',
				maxHeight: 300,
				buttonWidth: '100%',
				dropDown: true,
				selectAllName: true,
				allSelectedText: 'All Schemes',
				onChange: function() {
					 onloadCalls();
				},onSelectAll: function() {
					 onloadCalls();
				}
			});
		}
	});
}

function getAllFiniancialYears(){
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
		$("#financialYearId").append("<option value='2003-2050'>All Financial Years</option>");
		$("#financialYearId").append("<option value='2008-2014'>Before 2014</option>");
		$("#financialYearId").append("<option value=2014-"+currentYear+">2014-"+currentYear+"</option>");
		if(result != null && result.length >0){
			for(var i in result){
				var value = result[i].financialYear.split('-');
				$("#financialYearId").append("<option value="+result[i].financialYear+">"+result[i].financialYear+"</option>");
				
			}
			$("#financialYearId").val('2014-'+currentYear);
		}
		
		$("#financialYearId").chosen();
		$("#financialYearId").trigger('chosen:updated');
			onloadCalls();	
	});
}
$(document).on("change","#financialYearId",function(){
	var split=$(this).val().split('-')
	var yearId = $(this).val();
	glStartDate="01-04-"+split[0];
	glEndDate="01-04-"+split[1];
	onloadCalls();
});
$("#dateRangePickerAUM").daterangepicker({
	opens: 'left',
	startDate: glStartDate,
	endDate: glEndDate,
	locale: {
	  format: 'DD-MM-YYYY'
	},
	ranges: {
		'All':[moment().subtract(15, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
		'2014 To Till Now':["01-04-2014", moment().format("DD-MM-YYYY")],
		'Today' : [moment(), moment()],
		'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		'This Month': [moment().startOf('month'), moment()],
		'This Year': [moment().startOf('Year'), moment()],
		'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
		'Last 3 Year': [moment().subtract(3, 'Year'), moment()]	
	}
 });
 
var dates= $("#dateRangePickerAUM").val();
var pickerDates = glStartDate+' - '+glEndDate;
if(dates == pickerDates)
{
$("#dateRangePickerAUM").val('2014 To Till Now');
}
$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
	glStartDate = picker.startDate.format('DD-MM-YYYY')
	glEndDate = picker.endDate.format('DD-MM-YYYY')
	if(picker.chosenLabel == '2014 To Till Now')
	{
	  $("#dateRangePickerAUM").val('2014 To Till Now');
	}
	$("#financialYearId").val('2003-2050');
	$("#financialYearId").trigger('chosen:updated');
		onloadCalls();
});


function onloadCalls(){
	
	var statusType =$('input:radio[name=optradio1]:checked').val();
	levelWiseOverview();
	getLocationWiseWorksInformation("state","state","graph");
	//getLocationWiseWorkTargetsNacheivements("state","state","graph");
	getLocationWiseExceededWorkDetails("state","state","graph",statusType,"overAll")
	getLocationWiseNotGroundedExceededWorkDetails("state","state","graph",statusType,"notGrounded");
	gettAllEncWorksByScheme();
}


function levelWiseOverview()
{
	var statusType =$('input:radio[name=optradio1]:checked').val();
	if(statusType == ""){
		statusName ="All";
		
	}else{
		statusName ="OnGoing";
	}		
	
	var collapse = '';
	collapse+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		for(var i in levelWiseOverviewArr)
		{
			collapse+='<div class="panel panel-default panel-black">';
				collapse+='<div class="panel-heading" role="tab" id="heading'+levelWiseOverviewArr[i]+'">';
					if(i ==0)
					{
						collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}else{
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapse'+levelWiseOverviewArr[i]+'" aria-expanded="true" aria-controls="collapse'+levelWiseOverviewArr[i]+'">';
					}
					
						collapse+='<h4 class="panel-title text-capital">'+levelWiseOverviewArr[i]+' level overview</h4>';
					collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}else{
					collapse+='<div id="collapse'+levelWiseOverviewArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+levelWiseOverviewArr[i]+'">';
				}
				
				
					collapse+='<div class="panel-body">';
						collapse+='<div class="row">';
							collapse+='<div class="col-sm-12">';
								collapse+='<ul class="list-inline switch-btn workWiseDetailsCls">';
									collapse+='<li attr_type="works" attr_location_type="'+levelWiseOverviewArr[i]+'" attr_status="'+statusType+'" class="f_14">OverAll Works</li>';
									if(statusType =="ongoing"){
										collapse+='<li class="active f_14" attr_type="exceed" attr_location_type="'+levelWiseOverviewArr[i]+'" attr_status="'+statusType+'" >OnGoing-Exceeded Works</li>';
									}else{
										collapse+='<li class="active f_14" attr_type="exceed" attr_location_type="'+levelWiseOverviewArr[i]+'" attr_status="'+statusType+'" >ALL-Exceeded Works</li>';
									}
									
									collapse+='<li attr_type="notGrounded" attr_location_type="'+levelWiseOverviewArr[i]+'" attr_status="'+statusType+'" class="f_14">Not Grounded Exceeded Works</li>';
									/* collapse+='<li attr_type="targets" attr_location_type="'+levelWiseOverviewArr[i]+'" attr_status="'+statusType+'">Target&Achivements</li>'; */
								collapse+='</ul>';
							collapse+='</div>';
						collapse+='</div>';
						collapse+='<div class="row m_top20">';
							collapse+='<div class="col-sm-12">';
								collapse+='<div id="'+levelWiseOverviewArr[i]+'levelBlockId"></div>';
							collapse+='</div>';
						collapse+='</div>';
					
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
	collapse+='</div>';
	$("#levelWiseOverviewId").html(collapse);
	$(".chosen-select").chosen({width :'100%'});
	for(var i in levelWiseOverviewArr)
	{

		if(levelWiseOverviewArr[i] == "state"){
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','state','table',statusType,"overAll");
		}
		else if(levelWiseOverviewArr[i] == "district"){
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','district','table',statusType,"overAll");
		}
		else if(levelWiseOverviewArr[i] == "constituency"){
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','constituency','table',statusType,"overAll");
		}
		else if(levelWiseOverviewArr[i] == "mandal"){
			getLocationWiseExceededWorkDetails(levelWiseOverviewArr[i]+'levelBlockId','mandal','table',statusType,"overAll");
		}
		
	}
	
}
// over All Works
function getLocationWiseWorksInformation(blockId,locationType,type){
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	if(type=='graph'){
		$("#enclocationWiseChart").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			year:yearVal,
			schemeIdStr:schemeValArr
		}
		$.ajax({                
			type:'POST',    
			url: 'getLocationWiseEncWorksInformation',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildLocationWiseWorksGraph(result)
				}
				locationwiseTableBlocks(result,blockId,locationType);
			}else{
				
				$('#'+blockId).html("NO DATA AVAILABLE");
			}
		});
}

function locationwiseTableBlocks(result,blockId,locationTypes){
	var locationType= locationTypes;
	var table='';
		table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId">';
				table+='<thead>';
					if(blockId == 'statelevelBlockId'){
						table+='<th style="background-color:#def2f7">STATE</th>';
					}
					else if(blockId == 'districtlevelBlockId'){
						table+='<th style="background-color:#def2f7">DISTRICTS</th>';
					}else if(blockId == 'constituencylevelBlockId'){
						table+='<th style="background-color:#def2f7">CONSTITUENCY</th>';
					}else if(blockId == 'mandallevelBlockId'){
						table+='<th style="background-color:#def2f7">MANDALS</th>';
					}
									
					table+='<th style="background-color:#def2f7">ADMIN SANCTIONED</th>';
					table+='<th style="background-color:#def2f7">NOT TECHNICALLY SANCTIONED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#def2f7">TECHNICALLY SANCTIONED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#def2f7">NOT ENTRUSTED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#def2f7">ENTRUSTED</th>';
					table+='<th style="background-color:#def2f7">%</th>';
					table+='<th style="background-color:#f9e3f0">NOT GROUNDED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">GROUNDED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">UNDER PROCESS</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					table+='<th style="background-color:#f9e3f0">COMPLETED</th>';
					table+='<th style="background-color:#f9e3f0">%</th>';
					
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					var notTechSanctioned = result[i].adminSanctionCount-result[i].technicallySanctionedCount;
					var notEntrusted = result[i].technicallySanctionedCount-result[i].totalWorksEntrusted;
					table+='<tr>';
						table+='<td style="background-color:#def2f7">'+result[i].locationName+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].adminSanctionCount+'" attr_type = "adminSanctioned" attr_location_name="'+result[i].locationName+'" style=" background-color:#def2f7; cursor:pointer;text-decoration:underline">'+result[i].adminSanctionCount+'</td>';
						}else{
							table+='<td style="background-color:#def2f7">-</td>';
						}
						if(notTechSanctioned !=null && notTechSanctioned !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+notTechSanctioned+'" attr_type = "notTechSanctioned" attr_location_name="'+result[i].locationName+'" style=" background-color:#def2f7; cursor:pointer;text-decoration:underline">'+notTechSanctioned+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#def2f7">'+parseFloat((notTechSanctioned/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#def2f7">-</td>';
							}
						}else{
							table+='<td style="background-color:#def2f7">-</td>';
							table+='<td style="background-color:#def2f7">-</td>';
						}
						if(result[i].technicallySanctionedCount !=null && result[i].technicallySanctionedCount !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].technicallySanctionedCount+'" attr_type = "techSanctioned" attr_location_name="'+result[i].locationName+'" style=" background-color:#def2f7; cursor:pointer;text-decoration:underline">'+result[i].technicallySanctionedCount+'</td>';
						
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0 ){
								table+='<td style="background-color:#def2f7">'+parseFloat((result[i].technicallySanctionedCount/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#def2f7">-</td>';
							}
						}else{
							table+='<td style="background-color:#def2f7">-</td>';
							table+='<td style="background-color:#def2f7">-</td>';
						}
						if(notEntrusted !=null && notEntrusted !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+notEntrusted+'" attr_type = "notEntrusted" attr_location_name="'+result[i].locationName+'" style=" background-color:#def2f7; cursor:pointer;text-decoration:underline">'+notEntrusted+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#def2f7">'+parseFloat((result[i].totalWorksEntrusted/notEntrusted)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#def2f7">-</td>';
							}
						}else{
							table+='<td style="background-color:#def2f7">-</td>';
							table+='<td style="background-color:#def2f7">-</td>';
						}
						if(result[i].totalWorksEntrusted !=null && result[i].totalWorksEntrusted !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].totalWorksEntrusted+'" attr_type = "entrusted" attr_location_name="'+result[i].locationName+'" style=" background-color:#def2f7; cursor:pointer;text-decoration:underline">'+result[i].totalWorksEntrusted+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#def2f7">'+parseFloat((result[i].totalWorksEntrusted/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#def2f7">-</td>';
							}
						}else{
							table+='<td style="background-color:#def2f7">-</td>';
							table+='<td style="background-color:#def2f7">-</td>';
						}
						if(result[i].notGrounded !=null && result[i].notGrounded !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].notGrounded+'" attr_type = "Not Grounded" attr_location_name="'+result[i].locationName+'" style="background-color:#f9e3f0;cursor:pointer;text-decoration:underline">'+result[i].notGrounded+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].notGrounded/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#f9e3f0">-</td>';
							}
						}else{
							table+='<td style="background-color:#f9e3f0">-</td>';
							table+='<td style="background-color:#f9e3f0">-</td>';
						}
						if(result[i].groundedCount !=null && result[i].groundedCount !=0){
							table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].groundedCount+'" attr_type = "ongoing" attr_location_name="'+result[i].locationName+'" style="background-color:#f9e3f0;cursor:pointer;text-decoration:underline">'+result[i].groundedCount+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].groundedCount/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#f9e3f0">-</td>';
							}
						}else{
							table+='<td style="background-color:#f9e3f0">-</td>';
							table+='<td style="background-color:#f9e3f0">-</td>';
						}
						if(result[i].underProcessCount !=null && result[i].underProcessCount !=0){
						table+='<td class="schemsClickView"  attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].underProcessCount+'" attr_type = "Grounded" attr_location_name="'+result[i].locationName+'" style="background-color:#f9e3f0;cursor:pointer;text-decoration:underline">'+result[i].underProcessCount+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].underProcessCount/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#f9e3f0">-</td>';
							}
							}else{
								table+='<td style="background-color:#f9e3f0">-</td>';
								table+='<td style="background-color:#f9e3f0">-</td>';
						}
						if(result[i].completedCount !=null && result[i].completedCount !=0){
							table+='<td class="schemsClickView" attr_location_type="'+locationType+'" attr_filter_value="'+result[i].locationId+'"attr_total_count = "'+result[i].completedCount+'" attr_type = "Completed" attr_location_name="'+result[i].locationName+'" style="background-color:#f9e3f0;cursor:pointer;text-decoration:underline">'+result[i].completedCount+'</td>';
							if(result[i].adminSanctionCount !=null && result[i].adminSanctionCount >0){
								table+='<td style="background-color:#f9e3f0">'+parseFloat((result[i].completedCount/result[i].adminSanctionCount)*100).toFixed(2)+'</td>';
							}else{
								table+='<td style="background-color:#f9e3f0">-</td>';
							}
						}else{
							table+='<td style="background-color:#f9e3f0">-</td>';
							table+='<td style="background-color:#f9e3f0">-</td>';
						}
						
					table+='</tr>';
				}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
		$("#"+blockId).html(table);
		if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				}
			]
		});
	}
}

function buildLocationWiseWorksGraph(result){
	var adminSanctionCount = result[0].adminSanctionCount;
	var techcount =result[0].technicallySanctionedCount;
	var entrusted= result[0].totalWorksEntrusted;
	var grounded =result[0].groundedCount;
	var dataArr =[];
	dataArr.push({"name": 'Admin Sanctioned',"y": result[0].adminSanctionCount,color:'#5fc24f'});
	dataArr.push({"name": 'Not Techincal Sancationed',y:  result[0].adminSanctionCount-result[0].technicallySanctionedCount,color:'#ed0e27'});
	dataArr.push({"name": 'Techincal Sancationed',y: result[0].technicallySanctionedCount,color:'#418CF0'});
	dataArr.push({"name": 'Not Entrusted',y: result[0].technicallySanctionedCount-result[0].totalWorksEntrusted,color:'#ed0e27'});
	dataArr.push({"name": 'Entrusted',y: result[0].totalWorksEntrusted,color:'#FFBF00'});
	dataArr.push({"name": 'Not Grounded',y: result[0].notGrounded,color:'#ed0e27'});
	dataArr.push({"name": 'Grounded',y: result[0].groundedCount,color:'#ACFA58'});
	dataArr.push({"name": 'UnderProcess',y: result[0].underProcessCount,color:'#FA5858'});
	dataArr.push({"name": 'Completed',y:result[0].completedCount,color:'#009999'});
	
	
	$("#enclocationWiseChart").highcharts({
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
			type: 'category'
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: ''
			}

		},
		legend: {
			enabled: false
		},
		
		plotOptions: {
				column: {
					pointWidth: 35,
					gridLineWidth: 30,
					dataLabels: {
						useHTML:true,
						enabled: true,
						color: '#000',
						style: {
							fontWeight: '',
							fontSize:'9px'
						},
						align: 'center',
						formatter: function() {
							if(this.point.name !="Admin Sanctioned"){
								if(this.y == 0){
									return null;
								}else{
									var name=this.point.name;
									var pcnt = (this.y / adminSanctionCount) * 100;
									return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
								}
							}else{
								return '<span>'+this.y+'</span>';
							}
						}
					}
				}
			},

		tooltip: {
			headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
			pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
		},

		series: [{
			name: 'Works',
			colorByPoint: true,
			data: dataArr
		}]
	});
}

// exceed  Work details
function getLocationWiseExceededWorkDetails(blockId,locationType,type,statusType,workType){

	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	if(type=='graph'){
		$("#ExceededWorkDetailsGraph").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			status:statusType,
			year:yearVal,
			schemeIdStr:schemeValArr
		}
		$.ajax({                
			type:'POST',    
			url: 'getExceededEncWorks',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildGraphforExceededWorks(result,statusType)
				}else{
					buildExceededWorksBlocks(result,blockId,locationType,statusType,workType);
				}
			}else{
				if(type=='graph'){
					$("#ExceededWorkDetailsGraph").html("NO DATA AVAILABLE");
				}else{
					$('#'+blockId).html("NO DATA AVAILABLE");
				}
			}
		});
}
function buildGraphforExceededWorks(response,statusType){
	var dataArr = [];
	var totalCount=0;
	var statusNamesArr=[];
	var colors = [];
	if(statusType !=null && statusType==""){
		for(var i in response){
		  for(var j in response[i].subList){
				var tempArr = [];
				statusNamesArr.push(response[i].subList[j].name);
				dataArr.push({"y":response[i].subList[j].count,color: "#EE6CA9","extra":""+response[i].subList[j].ongoingPWSExceededCount+"-"+response[i].subList[j].completedPWSExceededCount});
				totalCount=totalCount+response[i].subList[j].count;
				
			}
		}
		$("#ExceededWorkDetailsGraph").highcharts({
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
				categories: statusNamesArr
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			tooltip: {
				formatter: function () {
					var value = (this.point.extra).split("-");
					return '<b>' + this.x + '</b><br/>' +
						'Total : ' + this.y + '<br/>'+
						'OnGoingExceededWorks :' +value[0]+ '<br/>' +
						'CompletedExceededWorks :' +value[1]+ '';
				}
			},
			plotOptions: {
				column: {
					//colorByPoint: true
					dataLabels: {
						useHTML:true,
						enabled: true,
						color: '#000',
						style: {
							fontWeight: '',
							fontSize:'9px'
						},
						align: 'center',
						formatter: function() {
							if(this.y == 0){
								return null;
							}else{
								var pcnt = (this.y / totalCount) * 100;
								return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
							}
							
						}
					}
				}
			},
			series: [{
				name: '',
				data: dataArr,
				showInLegend: false
			}]
			
		});
	}else{
		var totalCount1=0;
		for(var i in response){
		  for(var j in response[i].subList){
				var tempArr = [];
				statusNamesArr.push(response[i].subList[j].name);
				dataArr.push({"y":response[i].subList[j].ongoingPWSExceededCount,color: "#EE6CA9"});
				totalCount1=totalCount1+response[i].subList[j].ongoingPWSExceededCount;
				
			}
		}
		$("#ExceededWorkDetailsGraph").highcharts({
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
				categories: statusNamesArr
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				allowDecimals: false,
				min: 0,
				title: {
					text: null
				}
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.x + '</b><br/>' +
						'Total : ' + this.y + '<br/>'+
						'OnGoingExceededWorks :' +this.y+ '<br/>';
				}
			},
			plotOptions: {
				column: {
					dataLabels: {
						useHTML:true,
						enabled: true,
						color: '#000',
						align: 'center',
						style: {
							fontWeight: '',
							fontSize:'9px'
						},
						formatter: function() {
							if(this.y == 0){
								return null;
							}else{
								var pcnt = (this.y / totalCount1) * 100;
								return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
							
							}
							
						}
					}
				}
			},
			series: [{
				name: '',
				data: dataArr,
				showInLegend: false
			}]
			
		});
	}
		
}
 function buildExceededWorksBlocks(result,blockId,locationType,statusType,workType){
	 var table='';
	 	table+='<div class="table-responsive">';
			table+='<table class="table table-bordered m_top10" id="'+blockId+'dataTableId1">';
				table+='<thead>';
					table+='<tr>';
						if(blockId == 'statelevelBlockId'){
							table+='<th rowspan="2">STATE</th>';
						}else if(blockId == 'districtlevelBlockId'){
							table+='<th rowspan="2">DISTRICTS</th>';
						}else if(blockId == 'constituencylevelBlockId'){
							table+='<th rowspan="2">CONSTITUENCY</th>';
						}else if(blockId == 'mandallevelBlockId'){
							table+='<th rowspan="2">MANDALS</th>';
						}
						table+='<th rowspan="2">TOTAL WORKS</th>';
						for(var i in result[0].subList){
							table+='<th colspan="2">'+result[0].subList[i].name+'</th>';			
						}
					table+='</tr>';
					table+='<tr>';
						for(var i in result[0].subList){
							table+='<th>works</th>';
							table+='<th><span class="colspanLenIncstate"><i class="fa fa-inr m_top5" style="font-size:12px" aria-hidden="true"></i> In Crores</span></th>';
							
						}
					table+='</tr>';
					
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							table+='<td>'+result[i].name+'</td>';
							table+='<td>'+result[i].count+'</td>';
							for(var j in result[i].subList){
								if(result[i].subList[j].count !=null && result[i].subList[j].count>0){
									
									table+='<td class="schemsClickView"  attr_status="'+statusType+'" attr_location_type="'+locationType+'" attr_filter_value="'+result[i].subList[j].name+'" attr_total_count = "'+result[i].subList[j].count+'" attr_type="'+workType+'" attr_location_name="'+result[i].name+'" attr_district_val="'+result[i].locationIdStr+'" style="cursor:pointer;text-decoration:underline">'+result[i].subList[j].count+'</td>';
									table+='<td>'+parseFloat(result[i].subList[j].sanctionedAmount/10000000).toFixed(2)+'</td>';
								}else{
									table+='<td>-</td>';
									table+='<td>-</td>';
								}
								
								
							}
						table+='</tr>';
					}
				table+='</tbody>';
			table+='</table>';
		table+='</div>';
	$("#"+blockId).html(table);
		
	if(blockId != 'statelevelBlockId'){
		$("#"+blockId+"dataTableId1").dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"order": [ 0, 'asc' ],
			"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			buttons: [
				{
					extend		:'csvHtml5',
					text		:'<i class="fa fa-file-text-o"></i>',
					titleAttr	: 'CSV',
					title		:  "ENC WORKS DASHBOARD",
					filename	:  blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend		:'pdfHtml5',
					text		:'<i class="fa fa-file-pdf-o"></i>',
					titleAttr	:'PDF',
					title		: "ENC WORKS DASHBOARD",
					filename	: blockId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation	: "landscape",
					pageSize	: 'A3',
					customize	: function (doc) {
								doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
						}
				}
			]
		});
	}
 }	 
 
 //Not grounded works
 
 function getLocationWiseNotGroundedExceededWorkDetails(blockId,locationType,type,empty,workType){
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	if(type=='graph'){
		$("#NotGroundedExceededWorkDetailsGraph").html(spinner);
	}else{
		$("#"+blockId).html(spinner);
		
	}
		var json = {
			locationType:locationType,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			year:yearVal,
			schemeIdStr:schemeValArr
		}
		$.ajax({                
			type:'POST',    
			url: 'getLocationWiseNotGroundedWorks',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		 	if(result !=null && result.length>0){
				if(type=='graph'){
					buildNotGroundedGraphforExceededWorks(result)
				}else{
					buildExceededWorksBlocks(result,blockId,locationType,"",workType);
				}
			}else{
				if(type=='graph'){
					$("#NotGroundedExceededWorkDetailsGraph").html("NO DATA AVAILABLE");
				}else{
					$('#'+blockId).html("NO DATA AVAILABLE");
				}
			}
		});
}

function buildNotGroundedGraphforExceededWorks(response){
	
	var dataArr = [];
	var totalCount=0;
	var statusnotGroundNamesArr=[];
	var colors1 = []
	for(var i in response){
		for(var j in response[i].subList){
			if(response[i].subList[j].name != "In Time"){
				var tempArr = [];
				statusnotGroundNamesArr.push(response[i].subList[j].name);
				dataArr.push({"y":response[i].subList[j].count,color: "#C61379","extra":""+response[i].subList[j].ongoingPWSExceededCount+"-"+response[i].subList[j].completedPWSExceededCount});
				totalCount=totalCount+response[i].subList[j].count;
			}
		}
	}
	$("#NotGroundedExceededWorkDetailsGraph").highcharts({
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
			categories: statusnotGroundNamesArr,
			
		},
		yAxis: {
			min: 0,
			gridLineWidth: 0,
			minorGridLineWidth: 0,
			allowDecimals: false,
			min: 0,
			title: {
				text: null
			}
		},
		
		tooltip: {
			formatter: function () {
				var value = (this.point.extra).split("-");
				return '<b>' + this.x + '</b><br/>' +
					'Not grounded Exceeded : ' + this.y + '';
			}
		},
		plotOptions: {
			column: {
				//colorByPoint: true
				dataLabels: {
					useHTML:true,
					enabled: true,
					color: '#000',
					align: 'center',
					style: {
						fontWeight: '',
						fontSize:'9px'
					},
					formatter: function() {
						if(this.y == 0){
							return null;
						}else{
							var pcnt = (this.y / totalCount) * 100;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}
						
					}
				}
			}
		},
		series: [{
			name: '',
			data: dataArr,
			showInLegend: false
		}]
	});
}


// radio button click
$(document).on("click",".exceedWorkTypeCls",function(e){
	var statusType = $(this).val();
	if(statusType == ""){
		$(".headingExceedId").html("All Exceeded Works")
	}else{
		$(".headingExceedId").html("OnGoing Exceeded Works")
	}	
	getLocationWiseExceededWorkDetails("state","state","graph",statusType);
	levelWiseOverview();
});

// onClick of tab change
 $(document).on("click",".workWiseDetailsCls li",function(e){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var type = $(this).attr("attr_type");
	var locationType = $(this).attr("attr_location_type");
	var statusType1 =$('input:radio[name=optradio1]:checked').val();
	if(type == "works"){
		if(locationType == "state"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','state','table');
		}
		else if(locationType == "district"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','district','table');
			
		}
		else if(locationType == "constituency"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','constituency','table');
		}
		else if(locationType == "mandal"){
			getLocationWiseWorksInformation(locationType+'levelBlockId','mandal','table');
		}
	}else if(type == "exceed"){
		if(locationType == "state"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','state','table',statusType1,"overAll");
		}
		else if(locationType == "district"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','district','table',statusType1,"overAll");
		}
		else if(locationType == "constituency"){
			
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','constituency','table',statusType1,"overAll");
		}
		else if(locationType == "mandal"){
			getLocationWiseExceededWorkDetails(locationType+'levelBlockId','mandal','table',statusType1,"overAll");
		}
	}else if(type == "notGrounded"){
		if(locationType == "state"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','state','table',"","notGrounded");
		}
		else if(locationType == "district"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','district','table',"","notGrounded");
		}
		else if(locationType == "constituency"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','constituency','table',"","notGrounded");
		}
		else if(locationType == "mandal"){
			getLocationWiseNotGroundedExceededWorkDetails(locationType+'levelBlockId','mandal','table',"","notGrounded");
			
		}
	}
});

// onclick

$(document).on("click",".schemsClickView",function(){
	
	var status = $(this).attr("attr_status");
	var totalCount=$(this).attr("attr_total_count");
	var workStatus=$(this).attr("attr_type");
	var locationValue = $(this).attr("attr_filter_value");
	var locationType=$(this).attr("attr_location_type");
	var districtVal=$(this).attr("attr_district_val");
	var locationName=$(this).attr("attr_location_name");
	if(workStatus == "overAll"){
		$("#modalHablitationDivId").modal('show');
		$("#modalExceededTable").html('');
		$("#modalSchemsTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+"("+locationValue+")&nbsp;&nbsp;Overview</h4>");
		getOnClickExceedWorkDetails(workStatus,totalCount,locationType,locationValue,districtVal,status);
	}else if(workStatus == "notGrounded"){
		$("#modalHablitationDivId").modal('show');
			$("#modalExceededTable").html('');
		$("#modalSchemsTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+status+"&nbsp;"+"("+locationValue+")&nbsp;&nbsp;Overview</h4>");
		getOnClickNotGroubnWorkDetails(workStatus,totalCount,locationType,locationValue,districtVal,status);
	}else{
		$("#modalHablitationDivId").modal('show');
			$("#modalExceededTable").html('');
		$("#modalSchemsTable").html('');
		$("#modalAmountSchemeTable").html('');
		$("#modalHabliHeadingId").html("<h4 class='text-capital'>"+locationName+"&nbsp;&nbsp;"+locationType+"&nbsp;&nbsp;"+"("+workStatus+")&nbsp;&nbsp;Overview</h4>");
		getOnclickWorkSchemsDetails(workStatus,totalCount,locationValue,locationType,"");
		getAmountWiseEncWorksCount(workStatus,totalCount,locationValue,locationType);
	}
	
});


// get Onclcick overAll Works

function getOnclickWorkSchemsDetails(workStatus,totalCount,locationValue,locationType,amountRange){
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	$("#modalSchemsTable").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
		workStatus:workStatus,
		locationType:locationType,
		locationValue:locationValue,
		schemeIdStr:schemeValArr,
		amountRange:amountRange
	}
	
	$.ajax({                
		type:'POST',    
		url: 'getLocationWiseEncWorksdetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOnclickWorkSchemsDetails(result,status,workStatus,totalCount,amountRange);
		}else{
			
			$("#modalSchemsTable").html('No Data Available');
		}
		
	});
}

function buildOnclickWorkSchemsDetails(result,status,workStatus,totalCount,amountRange){
	var tableView='';
	tableView+='<div class="row m_top20">';
		tableView+='<div class="panel panel-default panel-black">';
			tableView+='<div class="panel-heading">';
				if(amountRange !=null && ( amountRange.length==0 || amountRange =="")){
					tableView+='<h5 class="font_weight">OverAll Works</h5>';
				}else{
					var range=""
					if(amountRange=="0-499999"){
						range="Less&nbsp;Than&nbsp;5,00,000";
					}else if(amountRange=="500000-999999"){
						range="5,00,000-10,00,000";
					}else if(amountRange=="1000000-4999999"){
						range="10,00,000-50,00,000";
					}else if(amountRange=="5000000-above"){
						range="50,00,000&nbsp;&&nbsp;Above";
					}
					tableView+='<h5 class="font_weight">'+range+' Works</h5>';
				}
				
			tableView+='</div>';
		tableView+='</div>';
	tableView+='</div>';
	tableView+='<div class="table-responsive m_top10">';
		tableView+='<table class="table table-bordered" id="dataTableSchems">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>Work ID</th>';
					tableView+='<th>Work NAME</th>';
					tableView+='<th>Grant Type</th>';
					tableView+='<th>Sub Grant Type</th>';
					tableView+='<th>DISTRICT</th>';
					tableView+='<th>CONSTITUENCY</th>';
					tableView+='<th>MANDAL</th>';
					tableView+='<th>HABITATIONS NAMES</th>';
					tableView+='<th>SANCTIONED AMOUNT(In Lakhs)</th>';
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
						tableView+='<td>'+result[i].workName+'</td>';
						tableView+='<td>'+result[i].grantName+'</td>';
						tableView+='<td>'+result[i].subGrantName+'</td>';
						tableView+='<td>'+result[i].districtName+'</td>';
						tableView+='<td>'+result[i].constituencyname+'</td>';
						tableView+='<td>'+result[i].mandalName+'</td>';
						tableView+='<td>'+result[i].habCode+'</td>';
						if(typeof result[i].sanctionedAmount === undefined ||typeof result[i].sanctionedAmount =="undefined" || result[i].sanctionedAmount =='' ){
							tableView+='<td>-</td>';
						}else{
							tableView+='<td>'+parseFloat(result[i].sanctionedAmount/100000).toFixed(2)+'</td>';
						}
						if(workStatus !="not grounded"){
							if(typeof result[i].targetDate === undefined ||typeof result[i].targetDate =="undefined" || result[i].targetDate =='' ){
								tableView+='<td>-</td>';
							}else{
								tableView+='<td>'+result[i].targetDate+'</td>';
							}if(typeof result[i].groundedDate === undefined ||typeof result[i].groundedDate =="undefined" || result[i].groundedDate =='' ){
								tableView+='<td>-</td>';
							}else{
								tableView+='<td>'+result[i].groundedDate+'</td>';
							}if(typeof result[i].completedDate === undefined ||typeof result[i].completedDate =="undefined" || result[i].completedDate =='' ){
								tableView+='<td>-</td>';
							}else{
								tableView+='<td>'+result[i].completedDate+'</td>';
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
						
						
					tableView+='</tr>';
			}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	$("#modalSchemsTable").html(tableView);
	$("#dataTableSchems").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 10,
		"aLengthMenu": [[10, 20, 40, -1], [10, 20, 40, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Enc Works',
				filename:  'Enc Works'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			}
		]
	});
}
//getOnClickExceedWorkDetails(workStatus,totalCount,locationType,locationValue,districtVal);
function getOnClickExceedWorkDetails(workStatus,totalCount,locationType,locationValue,districtVal,status){
	$("#modalExceededTable").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
		exceededDuration:locationValue,
		locationType:locationType,
		locationValue:districtVal,
		schemeIdStr:schemeValArr,
		status:status
	}
	
	$.ajax({                
		type:'POST',    
		url: 'getOnclickExceededEncWorks',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOnclickExcededWorkSchemsDetails(result,status,workStatus,totalCount);
		}else{
			
			$("#modalExceededTable").html('No Data Available');
		}
		
	});
}

function getOnClickNotGroubnWorkDetails(workStatus,totalCount,locationType,locationValue,districtVal){
	$("#modalExceededTable").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
		exceededDuration:locationValue,
		locationType:locationType,
		locationValue:districtVal,
		schemeIdStr:schemeValArr
		}
	
	$.ajax({                
		type:'POST',    
		url: 'getOnclickNotGroundedExceededEncWorks',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildOnclickExcededWorkSchemsDetails(result,status,workStatus,totalCount);
		}else{
			
			$("#modalExceededTable").html('No Data Available');
		}
		
	});
}

function buildOnclickExcededWorkSchemsDetails(result,status,workStatus,totalCount){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems2">';
		tableView+='<thead>';
		tableView+='<tr>';
				tableView+='<th>Work ID</th>';
				tableView+='<th>Work Name</th>';
				tableView+='<th>Grant Type</th>';
				tableView+='<th>District</th>';
				tableView+='<th>Constituency</th>';
				tableView+='<th>Mandal</th>';
				tableView+='<th>Habitation Names</th>';
				tableView+='<th>Sancationed Amount(In Lakhs)</th>';
				tableView+='<th>Exceeded Days</th>';
				tableView+='<th>Work Status</th>';
				
			tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].wrokIdStr+'</td>';
					tableView+='<td>'+result[i].wrokName+'</td>';
					tableView+='<td>'+result[i].assetType+'</td>';
					tableView+='<td>'+result[i].districtName+'</td>';
					tableView+='<td>'+result[i].constituencyName+'</td>';
					tableView+='<td>'+result[i].mandalName+'</td>';
					tableView+='<td>'+result[i].habitationName+'</td>';
					if(typeof result[i].sanctionedAmount === undefined ||typeof result[i].sanctionedAmount =="undefined" || result[i].sanctionedAmount =='' ){
						tableView+='<td>-</td>';
					}else{
						tableView+='<td>'+parseFloat(result[i].sanctionedAmount/100000).toFixed(2)+'</td>';
					}
					tableView+='<td>'+result[i].noOfDays+'</td>';
					tableView+='<td>'+result[i].workStatus+'</td>';
					
					
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#modalExceededTable").html(tableView);
	$("#dataTableSchems2").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 15,
		"aLengthMenu": [[15, 30, 50, -1], [15, 30, 50, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Enc Works',
				filename:  'Enc Works'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			}
		]
	});
}

function gettAllEncWorksByScheme(){
	
	$("#encSchemesTableDivId").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
	}
	
	$.ajax({                
		type:'POST',    
		url: 'gettAllEncWorksByScheme',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildgettAllEncWorksBySchemeDetails(result);
		}else{
			
			$("#encSchemesTableDivId").html('No Data Available');
		}
		
	});
}

function buildgettAllEncWorksBySchemeDetails(result){
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems1">';
		tableView+='<thead>';
		tableView+='<tr>';
			tableView+='<th>GRANT&nbsp;NAME-SUB&nbsp;GRANT</th>';
			tableView+='<th>ADMIN SANCTIONED</th>';
			tableView+='<th>TECHNICALLY SANCTIONED</th>';
			tableView+='<th>ENTRUSTED</th>';
			tableView+='<th>NOT GROUNDED</th>';
			tableView+='<th>GROUNDED</th>';
			tableView+='<th>UNDER PROCESS</th>';
			tableView+='<th>COMPLETED</th>';
				
		tableView+='</tr>';
			
		tableView+='</thead>';
		tableView+='<tbody>';
		for(var i in result){
			tableView+='<tr>';
					tableView+='<td>'+result[i].districtName+'-'+result[i].locationName+'</td>';
					tableView+='<td>'+result[i].adminSanctionCount+'</td>';
					tableView+='<td>'+result[i].technicallySanctionedCount+'</td>';
					tableView+='<td>'+result[i].totalWorksEntrusted+'</td>';
					tableView+='<td>'+result[i].notGrounded+'</td>';
					tableView+='<td>'+result[i].groundedCount+'</td>';
					tableView+='<td>'+result[i].underProcessCount+'</td>';
					tableView+='<td>'+result[i].completedCount+'</td>';
				tableView+='</tr>';
		}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#encSchemesTableDivId").html(tableView);
	$("#dataTableSchems1").dataTable({
		"order": [ 0, 'desc' ],
		"iDisplayLength" : 5,
		"aLengthMenu": [[5, 10, 15, -1], [5, 10, 15, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-6'f><'col-sm-2'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Enc Works',
				filename:  'Enc Works'+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			}
		]
	});
	
}

function getAmountWiseEncWorksCount(workStatus,totalCount,locationValue,locationType){
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var schemeValArr=[];
	var schemeVal =$("#schemeDivId").val();
	if(schemeVal==null || schemeVal==""){
		schemeValArr=[];
	}else{
		schemeValArr=schemeVal;
	}
	$("#modalAmountSchemeTable").html(spinner);
	var yearVal="";
	var financialVal =$("#financialYearId").val();
	if(financialVal != 0){
		 yearVal=financialVal;
	}
	var json = {
		year:yearVal,
		fromDateStr:glStartDate,
		toDateStr:glEndDate,
		workStatus:workStatus,
		locationType:locationType,
		locationValue:locationValue,
		schemeIdStr:schemeValArr
	}
	
	$.ajax({                
		type:'POST',    
		url: 'getAmountWiseEncWorksCount',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildAmountWiseEncWorks(result,status,workStatus,totalCount,locationValue,locationType);
		}else{
			
			$("#modalAmountSchemeTable").html('No Data Available');
		}
		
	});
}

function buildAmountWiseEncWorks(result,status,workStatus,totalCount,locationValue,locationType){
	
	var tableView='';
	tableView+='<div class="table-responsive">';
	tableView+='<table class="table table-bordered" id="dataTableSchems1">';
		tableView+='<thead>';
		tableView+='<tr>';
			tableView+='<th>AMOUNT&nbsp;RANGE</th>';
			for(var i in result){
				if(result[i].workName=="0-499999"){
					tableView+='<td align="center" class=>Less&nbsp;Than&nbsp;5,00,000</td>';
				}else if(result[i].workName=="500000-999999"){
					tableView+='<td align="center">5,00,000-10,00,000</td>';
				}else if(result[i].workName=="1000000-4999999"){
					tableView+='<td align="center">10,00,000-50,00,000</td>';
				}else if(result[i].workName=="5000000-above"){
					tableView+='<td align="center">50,00,000&nbsp;&&nbsp;Above</td>';
				}
		}
		
		tableView+='</tr>';
		tableView+='<tr>';
			tableView+='<th>Works&nbsp;Count</th>';
			for(var i in result){
				if(result[i].workName=="0-499999"){
					if(result[i].adminSanctionCount !=0){
						tableView+='<td align="center" class="schemsClickView1"  attr_location_type="'+locationType+'" attr_filter_value="'+locationValue+'"attr_total_count = "'+result[i].adminSanctionCount+'" attr_type = "'+workStatus+'" attr_location_name="'+result[i].workName+'" style="cursor:pointer;text-decoration:underline">'+result[i].adminSanctionCount+'</td>';
					}else{
						tableView+='<td>-</td>';
					}
					
				}else if(result[i].workName=="500000-999999"){
					if(result[i].adminSanctionCount !=0){
						tableView+='<td align="center" class="schemsClickView1"  attr_location_type="'+locationType+'" attr_filter_value="'+locationValue+'"attr_total_count = "'+result[i].adminSanctionCount+'" attr_type = "'+workStatus+'" attr_location_name="'+result[i].workName+'" style="cursor:pointer;text-decoration:underline">'+result[i].adminSanctionCount+'</td>';
					}else{
						tableView+='<td>-</td>';
					}
				}else if(result[i].workName=="1000000-4999999"){
					if(result[i].adminSanctionCount !=0){
						tableView+='<td align="center" class="schemsClickView1"  attr_location_type="'+locationType+'" attr_filter_value="'+locationValue+'"attr_total_count = "'+result[i].adminSanctionCount+'" attr_type = "'+workStatus+'" attr_location_name="'+result[i].workName+'" style="cursor:pointer;text-decoration:underline">'+result[i].adminSanctionCount+'</td>';
					}else{
						tableView+='<td>-</td>';
					}
				}else if(result[i].workName=="5000000-above"){
					if(result[i].adminSanctionCount !=0){
						tableView+='<td align="center" class="schemsClickView1"  attr_location_type="'+locationType+'" attr_filter_value="'+locationValue+'"attr_total_count = "'+result[i].adminSanctionCount+'" attr_type = "'+workStatus+'" attr_location_name="'+result[i].workName+'" style="cursor:pointer;text-decoration:underline">'+result[i].adminSanctionCount+'</td>';
					}else{
						tableView+='<td>-</td>';
					}
				}
		}
		tableView+='</tr>';
		tableView+='</thead>';
	tableView+='</table>';
	tableView+='</div>';
	$("#modalAmountSchemeTable").html(tableView);
}
	$(document).on("click",".schemsClickView1",function(){
	
	var status = $(this).attr("attr_status");
	var totalCount=$(this).attr("attr_total_count");
	var workStatus=$(this).attr("attr_type");
	var locationValue = $(this).attr("attr_filter_value");
	var locationType=$(this).attr("attr_location_type");
	var districtVal=$(this).attr("attr_district_val");
	var locationName=$(this).attr("attr_location_name");
	$("#modalSchemsTable").html('');
	getOnclickWorkSchemsDetails(workStatus,totalCount,locationValue,locationType,locationName);
	//getAmountWiseEncWorksCount(workStatus,totalCount,locationValue,locationType);
	
});
	
