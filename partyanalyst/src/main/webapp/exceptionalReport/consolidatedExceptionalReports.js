var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
var customStartToursDateM = moment().subtract(1, 'month').startOf('month').format('DD/MM/YYYY')
var customEndToursDateM = moment().subtract(1, 'month').endOf('month').format('DD/MM/YYYY');
var dateHeadingStr1 = "Last Month&nbsp("+customStartToursDateM+"&nbspto&nbsp"+customEndToursDateM+")";
var globalStatusObj=[{"1":"rgba(255, 0, 0, 0.15)"},{"2":"rgba(255, 0, 0, 0.25)"},{"3":"rgba(255, 0, 0, 0.35)"},{"4":"rgba(255, 0, 0, 0.45)"},{"5":"rgba(255, 0, 0, 0.55)"},{"6":"rgba(255, 0, 0, 0.65)"},{"7":"rgba(255, 0, 0, 0.75)"},{"8":"rgba(255, 0, 0, 0.85)"},{"9":"rgba(255, 0, 0, 0.9)"},{"10":"rgba(255, 0, 0, 1)"}];
var globalStatusObj1={"1":"rgba(255, 0, 0, 0.15)","2":"rgba(255, 0, 0, 0.25)","3":"rgba(255, 0, 0, 0.35)","4":"rgba(255, 0, 0, 0.45)","5":"rgba(255, 0, 0, 0.55)","6":"rgba(255, 0, 0, 0.65)","7":"rgba(255, 0, 0, 0.75)","8":"rgba(255, 0, 0, 0.85)","9":"rgba(255, 0, 0, 0.9)","10":"rgba(255, 0, 0, 1)"}
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
	   'Last 3 Months': [moment().subtract(parseInt(91)+parseInt(getDay1()), 'days'), moment().subtract(parseInt(getDay1()), 'days')],
	   'Last 6 Months': [moment().subtract(parseInt(183)+parseInt(getDay1()), 'days'), moment().subtract(parseInt(getDay1()), 'days')],
	   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
	   'This Month': [moment().startOf('month'), moment()],
	   'This Year': [moment().startOf('Year'), moment()],
	   'Overall' : [moment().subtract(1, 'years').startOf('year'), moment()],
	}
});
function getDay1(){
	var date = new Date();
	var dd = date.getDate(); 
	return dd;
}
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

$(document).on("click",".MeetingsExSettingsIcon",function(e){
	e.stopPropagation();
	$(this).closest(".meetingblockOpen").find(".debatesSettingsBody").show();
});
$(document).on("click",".debatesSettingsBody",function(e){
	e.stopPropagation();
});
$(document).on("click",function(){
	$(".debatesSettingsBody").hide();
});
$(document).on("click",".meetingExSettingsCloseBody",function(){
	$(this).closest(".debatesSettingsBody").hide();
});

$(document).on("click",".levelWiseMeetingCls",function(e){
	var levelName=['meetingBlocksConstituencyDivId','meetingBlocksMandalDivId','meetingBlocksVillageDivId'];
	for(var i in levelName){
		$("#"+levelName[i]).hide();
	}
	var selectedlevelValArr=[];
	$(".getLevelValCls").each(function(){
       if($(this).is(":checked")){
			
		    selectedlevelValArr.push($(this).val());
		    
	   }
    });
	console.log(selectedlevelValArr)
	$("#"+selectedlevelValArr).show();
	$(".debatesSettingsBody").hide();
});

$(document).on("click",".exportToPdf",function(){
	var id = $(this).attr("attr_id");
	getPdf(id);
});
function getPdf(id)
{
	var 
		form = $("#"+id),
		cache_width = form.width(),
		a4  = [ 5600  , 6000];  // for a4 size paper width and height
	createPDF()
	
	//create pdf
	function createPDF(){
		getCanvas().then(function(canvas){
			var 
			img = canvas.toDataURL("image/png"),
			doc = new jsPDF("l", "pt", "a4");     
			doc.addImage(img, 'PNG', 5, 15);
			var options = {
				 pagesplit: true,
				 background: '#fff',
				format: 'PNG',
				padding: 10,
				margin: {
					top: 40,
					bottom: 30,
					left:20,
					right:20
				}
			};
			var margins = {
				top: 40,
				bottom: 30
			};
			
			doc.addHTML($('#'+id+''), options, function()
			{
				doc.setFontSize(3);
				doc.save("test.pdf");
			});
			
			form.width(cache_width);
		});
		//$("#"+id).hide();
	}

	// create canvas object
	function getCanvas(){
		//form.width((a1[0]*1.33333) -80).css('max-width','none');
		$("#"+id).show();
		form.width(a4).css('max-width','none');
		return html2canvas(form,{
			imageTimeout:100,
			removeContainer:true
		});	
	}	
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
	
	/* str+='<div class="row ">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<h4 class="text_bold text-capital font_size24" >Over All Meeting Details - <small>(<b>Constituency,Mandal,Village level</b>)</small></h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Total Meeting</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.totalCount+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Conducted Meetings</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.conductedCount+' <small style="color:green;">'+result.conductedPercentage+' %</small></h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Not Conducted Meetings</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.notConductedCount+' <small style="color:green;">'+result.notConductedPercentage+' %</small></h3>';
			str+='</div>';
		str+='</div>';
	str+='</div>'; */

	str+='<div id="dataTableElecBlockCount"></div>';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Parliaments with Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview" id="dataTableElecBlock">';
						str+='<thead>';
							str+='<tr>';
								str+='<th rowspan="2" style="border-right: 1px solid #d1ab66 !important;">Parliament Name</th>';
								for(var i in result.subList2[0].subList1){
									str+='<th colspan="1">'+result.subList2[0].subList1[i].locationName+'</th>';
								}
							str+='</tr>';
							str+='<tr>';
								for(var i in result.subList2[0].subList1){
									//str+='<th>Total</th>';
									//str+='<th>Not Conducted</th>';
									var arr = result.subList2[0].subList1[i].locationName.split(' ');
									var lastval = arr[arr.length-1];
									
									if(lastval == "Committees"){
										str+='<th>Pending %</th>';
									}else if(lastval == "Meetings"){
										str+='<th>Not Conducted %</th>';
									}else if(lastval == 'Tejam'){
										str+='<th>Village Not Covered %</th>';
									}else if(lastval == 'Kaizala'){
										str+='<th>Committee Not Installed %</th>';
									}else if(lastval == 'Camp'){
										str+='<th>Not Attended %</th>';
									}
									
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							for(var i in result.subList2){
								if(result.subList2[i].sortNo > 0){
								str+='<tr>';
								str+='<td attr_color="'+globalStatusObj1[result.subList2[i].sortNo]+'" class="dataTableElecBlockexception'+result.subList2[i].sortNo+'" style="background-color:'+globalStatusObj1[result.subList2[i].sortNo]+';color:#fff;">'+result.subList2[i].locationName+'</td>';
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
	getCountOfColor("dataTableElecBlock")
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
	}
	
}

function buildConsolidatedLevelWisePartyMeetingExceptionReport1(result){
	
	var str='';
	/* var excLocArr = [{exceptionCount:'1',val:'0'},{exceptionCount:'2',val:'0'},{exceptionCount:'3',val:'0'},{exceptionCount:'4',val:'0'},{exceptionCount:'5',val:'0'},{exceptionCount:'6',val:'0'},{exceptionCount:'7',val:'0'},{exceptionCount:'8',val:'0'},{exceptionCount:'9',val:'0'},{exceptionCount:'10',val:'0'}]
	var globalobj='';
	for(var i in result.subList1){
		var sortNo = result.subList1[i].sortNo;
		for(var j in excLocArr){
			if(excLocArr[j].exceptionCount == sortNo){
				var val = excLocArr[j].val+1;
				globalobj={""+excLocArr[j].exceptionCount+""};
				
			}
		}
	}
	console.log(globalobj) */
	
	/* var blocksArr = [{exceptionCount:'1',color:'#C1A384'},{exceptionCount:'2',color:'#7C664F'},{exceptionCount:'3',color:'#704722'},{exceptionCount:'4',color:'#A65C31'},{exceptionCount:'5',color:'#C70039'},{exceptionCount:'6',color:'#640707'},{exceptionCount:'7',color:'#FF5733'},{exceptionCount:'8',color:'#B02626'},{exceptionCount:'9',color:'#CD0707'},{exceptionCount:'10',color:'#FC1313'}]
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<ul class="list-inline">';
			for(var i in blocksArr){
				str+='<li>';
					str+='<h4 class="text-bold"><span class="colorExpCls" style="background-color:'+blocksArr[i].color+'"></span> Exceptions('+blocksArr[i].exceptionCount+') - Parliaments()</h4>';
				str+='</li>';
			}
				
			str+='</ul>';
		str+='</div>';
	str+='</div>'; */
	
	//str+=''+getCountOfColor("dataTableElecBlock1")+'';
	str+='<div id="dataTableElecBlock1Count"></div>';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Assembly Constituency with Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview" id="dataTableElecBlock1">';
						str+='<thead>';
							str+='<tr>';
							str+='<th rowspan="2" style="border-right: 1px solid #d1ab66 !important;" >Constituency Name</th>';
							str+='<th rowspan="2" style="border-right: 1px solid #d1ab66 !important;">Parliament Name</th>';
								for(var i in result.subList1[0].subList1){
									var arr = result.subList1[0].subList1[i].locationName.split(' ');
									var lastval = arr[arr.length-1];
									if(lastval != 'Camp'){
										str+='<th colspan="1">'+result.subList1[0].subList1[i].locationName+'</th>';
									}
								}
							str+='</tr>';
							str+='<tr>';
								for(var i in result.subList1[0].subList1){
									var arr = result.subList1[0].subList1[i].locationName.split(' ');
									var lastval = arr[arr.length-1];
									
									if(lastval == "Committees"){
										str+='<th>Pending %</th>';
									}else if(lastval == "Meetings"){
										str+='<th>Not Conducted %</th>';
									}else if(lastval == 'Tejam'){
										str+='<th>Village Not Covered %</th>';
									}else if(lastval == 'Kaizala'){
										str+='<th>Committee Not Installed %</th>';
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
									str+='<td  attr_color="'+globalStatusObj1[result.subList1[i].sortNo]+'" class="dataTableElecBlock1exception'+result.subList1[i].sortNo+'" style="background-color:'+globalStatusObj1[result.subList1[i].sortNo]+';color:#fff;border-right: 1px solid #d1ab66 !important;">'+result.subList1[i].addressVO.constituencyName+'</td>';
								str+='<td>'+result.subList1[i].addressVO.parliamentName+'</td>';
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
	getCountOfColor("dataTableElecBlock1")
	var $windowWidth = $(window).width();
	if($windowWidth < 600)
	{
		$("#dataTableElecBlock1").dataTable({
			"paging":   false,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"scrollX":        true,
			"scrollCollapse": true,
			"fixedColumns":   {
				"leftColumns": 1,
			}
		});
	}
	
}

function getCountOfColor(divId)
{
	var str='';
		
		str+='<div class="row">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<ul class="list-inline">';
			var k = 0;
			for(var i in globalStatusObj)
			{
				k = k+1;
				str+='<li>';
					str+='<h4 class="text-bold"><span class="colorExpCls" style="background-color:'+globalStatusObj1[k]+'"></span> Exceptions('+k+') - Parliaments('+$('.'+divId+'exception'+k).length+')</h4>';
				str+='</li>';
			}	
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	$("#"+divId+"Count").html(str);
}
