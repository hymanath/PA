var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadPartyMeeting();
function onloadPartyMeeting(){
	
	var levelWiseArr=[{id:'meetingBlocksConstituencyDivId',levelName:'Constituency',partyMeetingTypeId:'3'},{id:'meetingBlocksMandalDivId',levelName:'mandalTownDivision',partyMeetingTypeId:'15'},{id:'meetingBlocksVillageDivId',levelName:'villageWard',partyMeetingTypeId:'14'}]
	for(var i in levelWiseArr){
		getLevelWisePartyMeetingExceptionReport(levelWiseArr[i].id,levelWiseArr[i].levelName,levelWiseArr[i].partyMeetingTypeId);
	}
	
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

function getLevelWisePartyMeetingExceptionReport(id,levelName,partyMeetingTypeIds){
		$("#"+id).html(spinner);
		var partyMeetingTypeIdsArr=[];
		partyMeetingTypeIdsArr.push(partyMeetingTypeIds);
		
	 	var jsObj = { 
					 partyMeetingLevel :levelName,
					 fromDate : "01/01/2018",
					 toDate : "31/01/2018",
					 partyMeetingTypeIds:partyMeetingTypeIdsArr,
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingExceptionReportMeetingLevelWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		     if(result !=null){
				 buildLevelWisePartyMeetingExceptionReport(result,id,levelName,partyMeetingTypeIds);
			 }
		});
}
function buildLevelWisePartyMeetingExceptionReport(result,id,levelName,partyMeetingTypeIds){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<h4 class="text_bold text-capital" >'+levelName+' Level Meeting Details</h4>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="">Total Meeting</h4>';
				str+='<h3 class="text_bold m_top10">'+result.totalCount+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="">Conducted Meetings</h4>';
				str+='<h3 class="text_bold m_top10">'+result.conductedCount+' <small style="color:green;">'+result.conductedPercentage+' %</small></h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="">Not Conducted Meetings</h4>';
				str+='<h3 class="text_bold m_top10">'+result.notConductedCount+' <small style="color:green;">'+result.notConductedPercentage+' %</small></h3>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital" >Top 5 Parliaments with Poor Performance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Parliament Name</th>';
								str+='<th>Total</th>';
								str+='<th>Not Conducted</th>';
								str+='<th>Not Conducted %</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							for(var i in result.subList2){
								str+='<tr>';
								str+='<td>'+result.subList2[i].addressVO.parliamentName+'</td>';
								str+='<td>'+result.subList2[i].totalCount+'</td>';
								str+='<td>'+result.subList2[i].notConductedCount+'</td>';
								str+='<td>'+result.subList2[i].percentage+'</td>';
								str+='</tr>';
								countVar =countVar+1;
								if (countVar === 5) {
									break;
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
			str+='<div class="col-sm-12 m_top20">';
				if(levelName != "Constituency"){
					str+='<h5 class="text_bold text-capital" >Top 10 Assembly Constituency with Poor Performance</h5>';
				}else{
					str+='<h5 class="text_bold text-capital" >Not Conducted Assembly Constituencies</h5>';
				}
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Parliament</th>';
								if(levelName != "Constituency"){
									str+='<th>Total</th>';
									str+='<th>Not Conducted</th>';
									str+='<th>Not Conducted %</th>';
								}
								
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar1 =0;
							for(var i in result.subList1){
								str+='<tr>';
								str+='<td>'+result.subList1[i].addressVO.constituencyName+'</td>';
								str+='<td>'+result.subList1[i].addressVO.parliamentName+'</td>';
								if(levelName != "Constituency"){
									str+='<td>'+result.subList1[i].totalCount+'</td>';
									str+='<td>'+result.subList1[i].notConductedCount+'</td>';
									str+='<td>'+result.subList1[i].percentage+'</td>';
								}
								str+='</tr>';
								countVar1 =countVar1+1;
								if (countVar1 === 10) {
									break;
								}
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#"+id).html(str);
}
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
 