var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var customStartToursDateM = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndToursDateM = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
var dateHeadingStr1 = "Last Month&nbsp("+customStartToursDateM+"&nbspto&nbsp"+customEndToursDateM+")";
var globalStatusObj=[{"1":"color_Ex1"},{"2":"color_Ex2"},{"3":"color_Ex3"},{"4":"color_Ex4"},{"5":"color_Ex5"},{"6":"color_Ex6"},{"7":"color_Ex7"},{"8":"color_Ex8"},{"9":"color_Ex9"},{"10":"color_Ex10"}];

var globalStatusObj1={"1":"color_Ex1","2":"color_Ex2","3":"color_Ex3","4":"color_Ex4","5":"color_Ex5","6":"color_Ex6","7":"color_Ex7","8":"color_Ex8","9":"color_Ex9","10":"color_Ex10"}

var globalStatusObjCon=[{"1":"color_Ex1"},{"2":"color_Ex2"},{"3":"color_Ex3"},{"4":"color_Ex4"},{"5":"color_Ex5"},{"6":"color_Ex6"},{"7":"color_Ex7"},{"8":"color_Ex8"},{"9":"color_Ex9"}];

var globalStatusObjCon1={"1":"color_Ex1","2":"color_Ex2","3":"color_Ex3","4":"color_Ex4","5":"color_Ex5","6":"color_Ex6","7":"color_Ex7","8":"color_Ex8","9":"color_Ex9"}

//$("#exceptionReportMeetingDateId").html(dateHeadingStr1);
 onloadPartyMeeting();
$("#meetingExDateRangePickerId").daterangepicker({
	opens: 'left',
	 startDate: customStartToursDateM,
	 endDate:customEndToursDateM,  
	locale: {
	  format: 'DD/MM/YYYY'
	},
	ranges: { ////moment().endOf('Year')
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
	   //'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	    'Last 3 Months': [moment().subtract(3, 'month'), moment()],
	   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()],
	   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
	}
});
/* function getDay1(){
	var date = new Date();
	var dd = date.getDate(); 
	return dd;
} */
$('#meetingExDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	   customStartToursDateM = picker.startDate.format('DD/MM/YYYY');
	   customEndToursDateM = picker.endDate.format('DD/MM/YYYY');
	   var dateHeadingStr2 = "("+customStartToursDateM+"&nbspto&nbsp"+customEndToursDateM+")";
	    //$("#exceptionReportMeetingDateId").html(dateHeadingStr2);
	   onloadPartyMeeting();
		 
});
function onloadPartyMeeting(){
	
	//var levelWiseArr=[{id:'meetingBlocksConstituencyDivId',levelName:'Constituency',partyMeetingTypeId:'3'},{id:'meetingBlocksMandalDivId',levelName:'mandalTownDivision',partyMeetingTypeId:'15'},{id:'meetingBlocksVillageDivId',levelName:'villageWard',partyMeetingTypeId:'14'}]
	//for(var i in levelWiseArr){
		//getLevelWisePartyMeetingExceptionReport(levelWiseArr[i].id,levelWiseArr[i].levelName,levelWiseArr[i].partyMeetingTypeId);
		getConsolidatedLevelWisePartyMeetingExceptionReport();
		getConsolidatedLevelWisePartyMeetingExceptionReport1();
	//}
	
}	
 
 $(document).on("click",".meetingsExRRefresh",function(e){
	onloadPartyMeeting();
});

function getConsolidatedLevelWisePartyMeetingExceptionReport(){
		 $("#overAllMeetingLevelsDivId").html(spinner);
	 	var jsObj = { 
					// partyMeetingLevel :levelName,
					 fromDate : customStartToursDateM,
					 toDate : customEndToursDateM,
					 stateId:1,
					 accessType:"parliament"
					 //partyMeetingTypeIds:partyMeetingTypeIdsArr,
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getOverAllConsolidatedViewDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		     if(result !=null){
				 $(".waitingMsgCls").hide();
				buildConsolidatedLevelWisePartyMeetingExceptionReport(result); 
			 }else{
				 $("#overAllMeetingLevelsDivId").html("");
				 $("#overAllMeetingLevelsDivId").html("No data available");
			 }
		});
}
function getConsolidatedLevelWisePartyMeetingExceptionReport1(){
		 $("#overAllMeetingLevelsDivId1").html(spinner);
	 	var jsObj = { 
					// partyMeetingLevel :levelName,
					 fromDate : customStartToursDateM,
					 toDate : customEndToursDateM,
					 stateId:1,
					 accessType:"constituency"
					 //partyMeetingTypeIds:partyMeetingTypeIdsArr,
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getOverAllConsolidatedViewDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		     if(result !=null){
				 $(".waitingMsgCls").hide();
				buildConsolidatedLevelWisePartyMeetingExceptionReport1(result); 
			 }else{
				 $("#overAllMeetingLevelsDivId1").html("");
				 $("#overAllMeetingLevelsDivId1").html("No data available");
			 }
		});
}
function buildConsolidatedLevelWisePartyMeetingExceptionReport(result){
	
	var str='';

	str+='<div id="dataTableElecBlockCount"></div>';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size20" >Parliaments Wise Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview-cons" id="dataTableElecBlock">';
						str+='<thead>';
							str+='<tr>';
								str+='<th rowspan="3" style="border-right: 1px solid #d1ab66 !important;">Parliament Name</th>';
									str+='<th colspan="3">Meetings</th>';
									str+='<th colspan="4">Committees</th>';
									str+='<th rowspan="2">Dalitha Tejam</th>';
									str+='<th rowspan="2">Kaizala</th>';
									str+='<th rowspan="2">Training Camp</th>';
									
									/* for(var i in result.subList2[0].subList1){
										str+='<th colspan="1" rowspan="3">'+result.subList2[0].subList1[i].locationName+'</th>';
										
									}*/
 							str+='</tr>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Mandal/Town/Division</th>';
								str+='<th>Village/Ward</th>';
								str+='<th>Mandal/Town/Division</th>';
								str+='<th>Village/Ward</th>';
								str+='<th>Affiliated</th>';
								str+='<th>Booth</th>';
							str+='</tr>';
							str+='<tr>';
								for(var i in result.subList2[0].subList1){
									//str+='<th>Total</th>';
									//str+='<th>Not Conducted</th>';
									var arr = result.subList2[0].subList1[i].locationName.split(' ');
									var lastval = arr[arr.length-1];
									
									if(lastval == "Committees"){
										str+='<th>Pending&nbsp;%</th>';
									}else if(lastval == "Meetings"){
										str+='<th>Not&nbsp;Conducted&nbsp;%</th>';
									}else if(lastval == 'Tejam'){
										str+='<th>Village&nbsp;Not&nbsp;Covered&nbsp;%</th>';
									}else if(lastval == 'Kaizala'){
										str+='<th>Committee&nbsp;Not&nbsp;Installed&nbsp;%</th>';
									}else if(lastval == 'Camp'){
										str+='<th>Not&nbsp;Attended&nbsp;%</th>';
									}
									
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							for(var i in result.subList2){
								if(result.subList2[i].sortNo > 0){
								str+='<tr>';
								str+='<td attr_color="'+globalStatusObj1[result.subList2[i].sortNo]+'" class="dataTableElecBlockexception'+result.subList2[i].sortNo+' '+globalStatusObj1[result.subList2[i].sortNo]+'" style="color:#fff;text-align:left;">'+result.subList2[i].locationName+'</td>';
								for(var j in result.subList2[i].subList1){
									//str+='<td>'+result.subList2[i].subList1[j].totalCount+'</td>';
									//str+='<td>'+result.subList2[i].subList1[j].notConductedCount+'</td>';
									if(result.subList2[i].subList1[j].percentage != 0 && result.subList2[i].subList1[j].percentage!=null){
										str+='<td>'+result.subList2[i].subList1[j].percentage+'</td>';
									}else{
										str+='<td>-</td>';
									}
									
								}
								str+='</tr>';
								/* countVar =countVar+1;
								if (countVar === 7) {
									break;
								} */
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	
	
	$("#overAllMeetingLevelsDivId").html(str);
	var $windowWidth = $(window).width();
	getCountOfColorPar("dataTableElecBlock")
	if($windowWidth < 600)
	{
		$("#dataTableElecBlock").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"aoColumnDefs": [{ "bSortable": false}], 
            "scrollX":        true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			}
		});
		$("#dataTableElecBlock").removeClass('dataTable')
	}else{
		$("#dataTableElecBlock").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"sDom": '<"top"iflp>rt<"bottom"><"clear">',
			"aaSorting": []
		});
		$("#dataTableElecBlock").removeClass('dataTable')
	}
	
}

function buildConsolidatedLevelWisePartyMeetingExceptionReport1(result){
	
	var str='';
	
	//str+=''+getCountOfColor("dataTableElecBlock1")+'';
	str+='<div id="dataTableElecBlock1Count"></div>';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size20" >Assembly Constituencies Wise Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview-cons" id="dataTableElecBlock1">';
						str+='<thead>';
							str+='<tr>';
							str+='<th rowspan="3">Constituency&nbsp;Name</th>';
							str+='<th rowspan="3">Parliament&nbsp;Name</th>';
								str+='<th colspan="3">Meetings</th>';
								str+='<th colspan="4">Committees</th>';
								str+='<th rowspan="2">Dalitha Tejam</th>';
								str+='<th rowspan="2">Kaizala</th>';
								//str+='<th rowspan="2">Training Camp</th>';
							str+='</tr>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Mandal/Town/Division</th>';
								str+='<th>Village/Ward</th>';
								str+='<th>Mandal/Town/Division</th>';
								str+='<th>Village/Ward</th>';
								str+='<th>Affiliated</th>';
								str+='<th>Booth</th>';
							str+='</tr>';
							str+='<tr>';
								for(var i in result.subList1[0].subList1){
									var arr = result.subList1[0].subList1[i].locationName.split(' ');
									var lastval = arr[arr.length-1];
									
									if(lastval == "Committees"){
										str+='<th>Pending&nbsp;%</th>';
									}else if(lastval == "Meetings"){
										str+='<th>Not&nbsp;Conducted&nbsp;%</th>';
									}else if(lastval == 'Tejam'){
										str+='<th>Village&nbsp;Not&nbsp;Covered&nbsp;%</th>';
									}else if(lastval == 'Kaizala'){
										str+='<th>Committee&nbsp;Not&nbsp;Installed&nbsp;%</th>';
									}/* else if(lastval == 'Camp'){
										str+='<th>Not Attended %</th>';
									} */
									//str+='<th>Total</th>';
									//str+='<th>Not Conducted</th>';
									//str+='<th>Not Conducted %</th>';
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar1 =0;
							for(var i in result.subList1){
								
								if(result.subList1[i].addressVO != null && result.subList1[i].sortNo > 0){
									str+='<tr >';
									str+='<td attr_color="'+globalStatusObjCon1[result.subList1[i].sortNo]+'" class="dataTableElecBlock1exception'+result.subList1[i].sortNo+' '+globalStatusObjCon1[result.subList1[i].sortNo]+'" style="color:#fff;border-right: 1px solid #d1ab66 !important;text-align:left;">'+result.subList1[i].addressVO.constituencyName+'</td>';
								str+='<td style="text-align:left;">'+result.subList1[i].addressVO.parliamentName+'</td>';
								for(var j in result.subList1[i].subList1){
									//str+='<td>'+result.subList1[i].subList1[j].totalCount+'</td>';
									//str+='<td>'+result.subList1[i].subList1[j].notConductedCount+'</td>';
									var arr = result.subList1[i].subList1[j].locationName.split(' ');
									var lastval = arr[arr.length-1];
									if(result.subList1[i].subList1[j].percentage != 0 && result.subList1[i].subList1[j].percentage!= null && lastval != 'Camp'){
										str+='<td >'+result.subList1[i].subList1[j].percentage+'</td>';
									}else if(lastval != 'Camp'){
										str+='<td>-</td>';
									}
								}
								
								
								str+='</tr>';
								
								/* countVar1 =countVar1+1;
								if (countVar1 === 10) {
									break;
								} */
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#overAllMeetingLevelsDivId1").html(str);
	getCountOfColorCons("dataTableElecBlock1")
	var $windowWidth = $(window).width();
	if($windowWidth < 600)
	{
		$("#dataTableElecBlock1").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"aoColumnDefs": [{ "bSortable": false}], 
			//fixedHeader: true,
			"scrollX":        true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 2,
			}
		});
	$("#dataTableElecBlock1").removeClass('dataTable');
	}else{
		$("#dataTableElecBlock1").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"sDom": '<"top"iflp>rt<"bottom"><"clear">',
			"aaSorting": []
		});
		$("#dataTableElecBlock1").removeClass('dataTable');
	}
	
}

function getCountOfColorPar(divId)
{
	var str='';
		
		str+='<div class="row">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<ul class="list-inline li_border">';
			var k = 0;
			for(var i in globalStatusObj)
			{
				k = k+1;
				str+='<li>';
					str+='<h4 class="text-bold f_16"><span class="colorExpCls '+globalStatusObj1[k]+'"></span> '+k+' Excps - '+$('.'+divId+'exception'+k).length+'</h4>';
				str+='</li>';
			}	
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	$("#"+divId+"Count").html(str);
}
function getCountOfColorCons(divId)
{
	var str='';
		
		str+='<div class="row">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<ul class="list-inline li_border">';
			var k = 0;
			for(var i in globalStatusObjCon)
			{
				k = k+1;
				str+='<li>';
					str+='<h4 class="text-bold f_16"><span class="colorExpCls '+globalStatusObjCon1[k]+'"></span> '+k+' Excps - '+$('.'+divId+'exception'+k).length+'</h4>';
				str+='</li>';
			}	
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	$("#"+divId+"Count").html(str);
}
