var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

$(document).on("click",".emnIconExpand",function(){
	$(".electronicMediaBlock").toggleClass("col-md-6").toggleClass("col-md-12");
	$(".electronicMediaBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
	$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
	if($(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
		//before expand
	}else{
		//after expand		
	}
});
var currentFromDateEmn = moment().format("DD-MM-YYYY");
var currentToDateEmn = moment().format("DD-MM-YYYY");
$(".dateRangePickerClsForEmn").daterangepicker({
	opens: 'left',
	startDate: moment(),
	endDate: moment(),
	locale: {
	  format: 'DD-MM-YYYY'
	}
});
$('.dateRangePickerClsForEmn').on('apply.daterangepicker', function(ev, picker) {
	currentFromDateEmn = picker.startDate.format('DD-MM-YYYY');
	currentToDateEmn = picker.endDate.format('DD-MM-YYYY');
});

getEMMDetailedPartyDistrictWiseProgramsOverview();
function getEMMDetailedPartyDistrictWiseProgramsOverview()
{
	alert(4);
	$("#userTypeWiseNewsForTopFiveStrongAndPoorDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	$.ajax({
		//url: wurl+"CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"
		url: "http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"
	}).then(function(result){
			console.log(result)
	});
}
/* http://localhost:8080/CommunityNewsPortal/webservice/getEMMDetailedPartyDistrictWiseProgramsOverview/2/1/2016-10-28/2016-10-28/1,2,3/2/872,1117,163,362/N"






levelid---1/2016-10-28/2016-10-28/1
and locationLevelArray-1

startDate-2016-01-01

endDate--2016-10-27
channelids--1,2,3,
impactScopeIds---1,2,3,4,5,6,7,8
orgids--872,1117,163,362
isDept-N
searchType--category
 */