var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));

if(wurl.length == 3)
wurl = url.substr(0,(url.indexOf(".in")+3));


var performanceResult = '';
var fromDate = moment().startOf('month').format("DD-MM-YYYY");
var toDate = moment().endOf('month').format("DD-MM-YYYY");
$("#pressMeetHeadDate").html("THIS MONTH ("+moment().startOf('month').format("DD/MM/YYYY")+" To "+moment().endOf('month').format("DD/MM/YYYY")+")");
function globalPressMeetMediaCalls(type)
{
	if(type == "default"){
		$('#dateRangePressmeetId').data('daterangepicker').setStartDate(moment());
		$('#dateRangePressmeetId').data('daterangepicker').setEndDate(moment());
		fromDate = moment().startOf('month').format("DD-MM-YYYY")
		toDate = moment().endOf('month').format("DD-MM-YYYY")
		$("#pressMeetHeadDate").html("THIS MONTH("+moment().startOf('month').format("DD/MM/YYYY")+" To "+moment().endOf('month').format("DD/MM/YYYY")+")");
	}else if(type == "currentMonth"){
		$('#dateRangePressmeetId').data('daterangepicker').setStartDate(moment().startOf("month"));
		$('#dateRangePressmeetId').data('daterangepicker').setEndDate(moment().endOf("month"));
		fromDate = moment().startOf("month").format("DD-MM-YYYY")
		toDate = moment().endOf("month").format("DD-MM-YYYY")
		$("#pressMeetHeadDate").html("THIS MONTH("+moment().startOf('month').format("DD/MM/YYYY")+" To "+moment().endOf('month').format("DD/MM/YYYY")+")");
	}else if(type == "lastMonth"){
		$('#dateRangePressmeetId').data('daterangepicker').setStartDate(moment().subtract(1,'month').startOf("month"));
		$('#dateRangePressmeetId').data('daterangepicker').setEndDate(moment().subtract(1,'month').endOf("month"));
		fromDate = moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY")
		toDate = moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")
		$("#pressMeetHeadDate").html("LAST MONTHs"+" ( "+moment().subtract(1,'month').startOf("month").format("DD/MM/YYYY")+"-"+moment().subtract(1,'month').endOf("month").format("DD/MM/YYYY")+" )");
	}
	$("#dateRangePressmeetId").val(fromDate+" - "+toDate);
	preemeeetOnloadCalls();
	 
}


$("#dateRangePressmeetId").daterangepicker({
		opens: 'left',
	    startDate: fromDate,
        endDate: toDate,
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
		    'Today': [moment(), moment()],
		   'This Month': [moment().startOf("month").format("DD-MM-YYYY"), moment().endOf('month').format("DD-MM-YYYY")],
		   'Last Month': [moment().subtract(1,'month').startOf("month").format("DD-MM-YYYY"),moment().subtract(1,'month').endOf("month").format("DD-MM-YYYY")],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
           'This Year': [moment().startOf('Year'), moment()]
		}
	})
	

$('#dateRangePressmeetId').on('apply.daterangepicker', function(ev, picker) {
  fromDate = picker.startDate.format('DD-MM-YYYY');
  toDate = picker.endDate.format('DD-MM-YYYY');
	preemeeetOnloadCalls();
});
$(document).on("click",".pressMeetIconRefresh",function(){
	preemeeetOnloadCalls();
});
function preemeeetOnloadCalls(){
		getPartyWiseThenCandidateWisePerformance();
		getPrintMediaOverAllPartyWiseCounts();
		getTopFiveLeaders("1,2,3");
		getPublicationVsPartiesPerformance("1,2,3");
		getDesignationWisePerformance();		
}
var partywiseresult='';
$(document).on("click",".pressmeetIconExpand",function(){
	if( $(this).find("i").hasClass("glyphicon glyphicon-resize-small" )){
		getPartyWiseThenCandidateWisePerformance();
	}else{
		$(".collapsepressmeetIcon").addClass("collapsed")
		$(".panel-collapse").removeClass("in")
	}    
	$(".pressmeetSpokesCls li").removeClass("active");
	$(".pressmeetSpokesCls li:first-child").addClass("active");
});
function getPartyWiseThenCandidateWisePerformance(){
	 $("#spokesPersonWisepressmeetDetailsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	 $("#candidateOverAllPerformanceCohortId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $.ajax({
  url: wurl+"/CommunityNewsPortal/webservice/getPartyWiseThenCandidateWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getPartyWiseThenCandidateWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"
 }).then(function(result){
     
   if(result !=null){
	  partywiseresult= result;
	  $("#spokesPersonWisepressmeetDetailsId").html(" ");
	  buildSpokesPersonWisePressmeet(partywiseresult,"top");
	  buildCandidateOverAllPerformanceCohortPressmeet(result);
  }else{
      $("#spokesPersonWisepressmeetDetailsId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
	  $("#candidateOverAllPerformanceCohortId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
	}
 });   
}
$(document).on("click",".edtionTypescls li",function(){
	var edtionList=[];
		var editionId = $(this).attr("id");
		if(editionId == 0){
			edtionList.push(1,2,3);
		}else if(editionId == 1){
			edtionList.push(1);
		}else{
			edtionList.push(2,3);
		}
		getTopFiveLeaders(edtionList);	
	 });


function getTopFiveLeaders(edtionList){
	$("#candidatePressMeetPerformanceId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $.ajax({
  url: wurl+"/CommunityNewsPortal/webservice/getTopFiveLeaders/"+fromDate+"/"+toDate+"/"+edtionList+"/"
 //url: "http://localhost:8080/CommunityNewsPortal/webservice/getTopFiveLeaders/"+fromDate+"/"+toDate+"/"+edtionList+"/"
 }).then(function(result){
     
  if(result !=null){
	  buildRolesPerformanceOfCandidatePressmeet(result);
  }else{
		$("#candidatePressMeetPerformanceId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
  }
 });   
}
$(document).on("click",".publicationAndEdtionTypescls li",function(){
	var edtionList=[];
		var editionId = $(this).attr("id");
		if(editionId == 0){
			edtionList.push(1,2,3);
		}else if(editionId == 1){
			edtionList.push(1);
		}else{
			edtionList.push(2,3);
		}
		getPublicationVsPartiesPerformance(edtionList);	
	 });

function getPublicationVsPartiesPerformance(edtionList){
	$("#publicationAndPartyWiseDetailsId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
 $.ajax({
  url: wurl+"/CommunityNewsPortal/webservice/getPublicationVsPartiesPerformance/"+fromDate+"/"+toDate+"/"+edtionList+"/"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getPublicationVsPartiesPerformance/"+fromDate+"/"+toDate+"/"+edtionList+"/"
 }).then(function(result){
     
  if(result !=null){
		buildPublicationAndPartyWiseDetails(result);
  }else{
     $("#publicationAndPartyWiseDetailsId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
  }
 });   
}

function getPrintMediaOverAllPartyWiseCounts(){
	
	$("#partyWisePressMeetDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#scaleBasedPerformanceCohortId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaOverAllPartyWiseCounts/"+fromDate+"/"+toDate
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getPrintMediaOverAllPartyWiseCounts/"+fromDate+"/"+toDate
	}).then(function(result){
			if(result !=null){
				performanceResult = result;
     		   bulidPressMeetPartyWiseDetails(result);
     		}else{
				$("#partyWisePressMeetDetails").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
			}
		});

	}

	
	function bulidPressMeetPartyWiseDetails(result)
{
	var str='';
	
	if(result.pressmeetList !=null){
		for(var i in result.pressmeetList){
			if(result.pressmeetList[i].partyId == "TDP"){
				str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive">';
				str+='<table class="table tableTraining bg_ED tablePressmeetMain">';
				str+='<tbody>';
				str+='<tr>';
				str+='<td style="vertical-align:middle;">';
					str+='<img src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+' Icon" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">Total press Meets</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_partyId = "'+result.pressmeetList[i].partyId+'" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">total spokes persons</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_type="candidate" attr_partyId = "'+result.pressmeetList[i].partyId+'"><a>'+result.pressmeetList[i].candidateArticleCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
				  var rating =parseInt(result.pressmeetList[i].overAllPerfomance)/5;
					str+='<p class="text-capital">performance</p>';
					str+='<input class="performanceRating" value="'+rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom overAllPerformanceCls " attr_partyId = "'+result.pressmeetList[i].partyId+'" style="cursor:pointer;">'+rating+'</span>';
				str+='</td>';
				str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				str+='</div>';
			}else if(result.pressmeetList[i].partyName == "YSRC"){
				str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive">';
				str+='<table class="table tableTraining bg_ED tablePressmeetMain">';
				str+='<tbody>';
				str+='<tr>';
				str+='<td style="vertical-align:middle;">';
					str+='<img src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+' Icon" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">Total press Meets</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_partyId = "'+result.pressmeetList[i].partyId+'" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">total spokes persons</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_type="candidate" attr_partyId = "'+result.pressmeetList[i].partyId+'"><a>'+result.pressmeetList[i].candidateArticleCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
				  var rating =parseInt(result.pressmeetList[i].overAllPerfomance)/5;
					str+='<p class="text-capital">performance</p>';
					str+='<input class="performanceRating" value="'+rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom overAllPerformanceCls " attr_partyId = "'+result.pressmeetList[i].partyId+'" style="cursor:pointer;">'+rating+'</span>';
				str+='</td>';
				str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-12 m_top10">';
				str+='<div class="table-responsive">';
				str+='<table class="table tableTraining bg_ED tablePressmeetMain">';
				str+='<tbody>';
				str+='<tr>';
				str+='<td style="vertical-align:middle;">';
					str+='<img src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+' Icon" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">Total press Meets</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_partyId = "'+result.pressmeetList[i].partyId+'" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
					str+='<p class="text-capital">total spokes persons</p>';
					str+='<h4><span class="partyWisePressMeetCls" style="cursor:pointer;color:#337ab7" attr_type="candidate" attr_partyId = "'+result.pressmeetList[i].partyId+'"><a>'+result.pressmeetList[i].candidateArticleCount+'</a></span></h4>';
				str+='</td>';
				str+='<td>';
				  var rating =parseInt(result.pressmeetList[i].overAllPerfomance)/5;
					str+='<p class="text-capital">performance</p>';
					str+='<input class="performanceRating" value="'+rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom overAllPerformanceCls " attr_partyId = "'+result.pressmeetList[i].partyId+'" style="cursor:pointer;">'+rating+'</span>';
				str+='</td>';
				str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
				str+='</div>';
			}
					
				 
		}
		
		$("#partyWisePressMeetDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:true,
			hoverOnClear: true,
			animate:false
		});
	
	}else{
			$("#partyWisePressMeetDetails").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
		}
	buildScaleBasedPerformanceCohortPressMeet(result)
}
$(document).on("click","#pressmeetTopId",function(){
	buildSpokesPersonWisePressmeet(partywiseresult,"top");
});
$(document).on("click","#pressmeetLowId",function(){
	buildSpokesPersonWisePressmeet(partywiseresult,"poor");
});

   function buildSpokesPersonWisePressmeet(result,searchType){
     
		var str='';
		if(result != null){		
			for(var i in result.paliticalPartyList){
				str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<h5 class="text-capital">'+result.paliticalPartyList[i].name+'</h5>';
					
				if($(window).width() < 500)
				{
					str+='<div id="pressmeetSpokes'+i+'" style="height:150px;"></div>';
				}else if($(window).width() > 500){
					 
					str+='<div id="pressmeetSpokes'+i+'" style="height:120px;"></div>';
				}else if($(window).width() > 900){
					
					str+='<div id="pressmeetSpokes'+i+'" style="height:80px;"></div>';
				}
				str+='</div>';
			}
		
		}
		  
		$('#spokesPersonWisepressmeetDetailsId').html(str);
		if(result.paliticalPartyList != null && result.paliticalPartyList.length > 0){
		for(var i in result.paliticalPartyList){
			var candidateNameAndCompletedCountArray1 =[];  
			          var countVar =0;
					  if(result.paliticalPartyList[i].participantList.length > 5){
						 if(searchType == 'top'){
							for(var j in result.paliticalPartyList[i].participantList){
								
								 var obj1 = {							
										name: result.paliticalPartyList[i].participantList[j].name.toUpperCase(),
										y: result.paliticalPartyList[i].participantList[j].totalCount,
										partyId:result.paliticalPartyList[i].id,
										candidateId:result.paliticalPartyList[i].participantList[j].id
									};
									
								candidateNameAndCompletedCountArray1.push(obj1);
								countVar =countVar+1;
								
								if (countVar == 5) {
								 break;
								}
						    }	
					      }else{
								var arrLen = result.paliticalPartyList[i].participantList.length;
								arrLen = arrLen -1;
								var limit = arrLen - 4;
								for(var k=arrLen ; k>=limit ; k--){
									var obj1 = {	
										name: result.paliticalPartyList[i].participantList[k].name.toUpperCase(),
										y: result.paliticalPartyList[i].participantList[k].totalCount,
										partyId:result.paliticalPartyList[i].id,
										candidateId:result.paliticalPartyList[i].participantList[k].id
									}; 
									candidateNameAndCompletedCountArray1.push(obj1);
								}          
					      } 
						   
					  }else{
						  if(searchType == 'top'){
								for(var j in result.paliticalPartyList[i].participantList){
									var obj1 = {							
										name: result.paliticalPartyList[i].participantList[j].name.toUpperCase(),
										y: result.paliticalPartyList[i].participantList[j].totalCount,
										partyId:result.paliticalPartyList[i].id,
										candidateId:result.paliticalPartyList[i].participantList[j].id
									};
									
									candidateNameAndCompletedCountArray1.push(obj1);
								}
						  }else{
							  var arrLen = result.paliticalPartyList[i].participantList.length;
							  arrLen = arrLen -1;
							  for(var k=arrLen ; k>=0 ; k--){
								  var obj1 = {	
										name: result.paliticalPartyList[i].participantList[k].name.toUpperCase(),
										y: result.paliticalPartyList[i].participantList[k].totalCount,
										partyId:result.paliticalPartyList[i].id,
										candidateId:result.paliticalPartyList[i].participantList[k].id
									}; 
									candidateNameAndCompletedCountArray1.push(obj1);
							  }
						  }                    
						
					   }
			 	
				
				if(candidateNameAndCompletedCountArray1.length != 0){
					$(function () {
						 $("#pressmeetSpokes"+i).highcharts({
							 colors: ['#0066DC'], 
							chart: {
								type: 'column'
							},
							title: {
								text: ''
							},
							subtitle: {
								text: ''
							},
							xAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								
								type: 'category',
								labels: {
											formatter: function() {
												if($(window).width() < 799)
												{
													return this.value.toString();
												}else if($(window).width() > 800){
													return this.value.toString().substring(0, 10)+'...';
												}
												
											},
											
										}        
								
							},
							yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
								title: {
									text: ''
								}

							},plotOptions: {
								series: {
									cursor: 'pointer',
									point: {
										events: {
											click: function () {
												var partyId = this.options.partyId;
												var candidateId = this.options.candidateId;
												//alert(partyId+":"+candidateId);
												candidateIdAndPartyIdGraphClick(partyId,candidateId)
											}  
										}
									}
								}    
							},
							legend: {
								enabled: false
							},
							

							tooltip: {
								headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
								pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b>'
							},

							series: [{
								name: 'SpokesPersons',
								dataLabels: {
									enabled: true,
									 formatter: function() {
										if (this.y === 0) {
											return null;
										} else {
											return Highcharts.numberFormat(this.y);
										}
									}
								},
								data: candidateNameAndCompletedCountArray1
							}],
						 
						});
					});
				}
				
		}    
		
		}
		else{
		$("#spokesPersonWisepressmeetDetailsId").html("No Data Available");
		}
		
	
	}
function buildScaleBasedPerformanceCohortPressMeet(result)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="table-responsive">';
			for(var i in result.pressmeetList){
				str+='<table class="table tablepressmeet tablepressmeetMain m_top10">';
				  str+='<tbody>';
					
					if(result.pressmeetList[i].partyId == 872 || result.pressmeetList[i].partyId == "872"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">OverAll Press Meets</p>';
							
							  str+='<h4><span style="cursor:pointer;" attr_partyId="'+result.pressmeetList[i].partyId+'" class="partyWisePressMeetCls" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<p class="text-capital" style="font-size:12px">'+result.pressmeetList[i].characterList[j].characterstics+'</p>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else if(result.pressmeetList[i].partyId == 1117 || result.pressmeetList[i].partyId == "1117"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">OverAll Press Meets</p>';
							
							  str+='<h4><span style="cursor:pointer;" attr_partyId="'+result.pressmeetList[i].partyId+'" class="partyWisePressMeetCls" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<p class="text-capital" style="font-size:12px">'+result.pressmeetList[i].characterList[j].characterstics+'</p>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else if(result.pressmeetList[i].partyId == 163 || result.pressmeetList[i].partyId == "163"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">OverAll Press Meets</p>';
							
							  str+='<h4><span style="cursor:pointer;" attr_partyId="'+result.pressmeetList[i].partyId+'" class="partyWisePressMeetCls" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<p class="text-capital" style="font-size:12px">'+result.pressmeetList[i].characterList[j].characterstics+'</p>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else{
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							str+='<p class="text-capital">OverAll Press Meets</p>';
							
							  str+='<h4><span style="cursor:pointer;" attr_partyId="'+result.pressmeetList[i].partyId+'" class="partyWisePressMeetCls" attr_type="party" ><a>'+result.pressmeetList[i].pressMeetCount+'</a></span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<p class="text-capital" style="font-size:12px">'+result.pressmeetList[i].characterList[j].characterstics+'</p>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}
						
					
				 str+='</tbody>';
				str+='</table>';
			str+='<hr class="m_0"/>';
			}
			str+='</div>';
		str+='</div>';
		$("#scaleBasedPerformanceCohortId").html(str)
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		if(result.pressmeetList.length> 6)
		{
			$("#scaleBasedPerformanceCohortId").mCustomScrollbar({setHeight:'300px'})
		}
	}else{
		$("#scaleBasedPerformanceCohortId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
	}
}

function buildCandidateOverAllPerformanceCohortPressmeet(result)
{
	
	var str='';
	
	if(result.paliticalPartyList !=null){
		str+='<div class="panel-group" id="accordionInner" role="tablist" aria-multiselectable="true">';
		for(var i in result.paliticalPartyList){
			str+='<div class="panel panel-default collapsepressmeet">';
				str+='<div class="panel-heading" role="tab" id="headingInner'+i+'">';
					if(i == 0)
					{
						str+='<a role="button" class="collapsepressmeetIcon" data-toggle="collapse" data-parent="#accordionInner" href="#collapseInner'+i+'" aria-expanded="true" aria-controls="collapseInner'+i+'">';
					}else{
						str+='<a role="button" class="collapsepressmeetIcon collapsed" data-toggle="collapse" data-parent="#accordionInner" href="#collapseInner'+i+'" aria-expanded="true" aria-controls="collapseInner'+i+'">';
					}
					
						str+='<h4 class="panel-title"><img  src="newCoreDashBoard/img/'+result.paliticalPartyList[i].name+'.png" alt="'+result.paliticalPartyList[i].name+'" class="PressmeetPartyIcon"/> '+result.paliticalPartyList[i].name +' spokespersons</h4>';
					str+='</a>';
				str+='</div>';
				if(i == 0)
				{
					str+='<div id="collapseInner'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingInner'+i+'">';
				}else{
					str+='<div id="collapseInner'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingInner'+i+'">';
				}
					str+='<div class="panel-body">';
						str+='<div class="row">';
							str+='<div class="col-md-12 col-xs-12 col-sm-12">';
								str+='<div class="table-responsive scroller'+i+'">';
									str+='<table class="table table-bordered tablepressmeetMainText m_top10 dataTableSorting text-center">';
										str+='<thead>';
											str+='<th>NAME</th><th>OVERALL PERFORMANCE </th><th>SUBJECT</th><th>CREATIVITY/COUNTER</th><th>TIMELY/OUT OF TIME</th><th>COUNTER ATTACK</th><th>COVERAGE</th>';
										str+='</thead>';
										str+='<tbody>';
											for(var j in result.paliticalPartyList[i].participantList){
												str+='<tr>';
													str+='<td class="text-capitalize">'+result.paliticalPartyList[i].participantList[j].name.toUpperCase()+'<br><span class="pressmeetIdCountCls" attr_partyId="'+result.paliticalPartyList[i].id+'" attr_candidateId="'+result.paliticalPartyList[i].participantList[j].id+'" style="cursor:pointer;">Total press Meet : <a>'+result.paliticalPartyList[i].participantList[j].pressMeetCount+'</a></span></td>';
													str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].totalCount+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].totalCount.toFixed(2)+'</span>';
													str+='</td>';
														str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].subjectRatio+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span style="padding-right: 0px;" class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].subjectRatio.toFixed(2)+'</span>';
													str+='</td>';
														str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].creativityRatio+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span  class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].creativityRatio.toFixed(2)+'</span>';
													str+='</td>';
														str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].timelyRatio+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].timelyRatio.toFixed(2)+'</span>';
													str+='</td>';
														str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].counterRatio+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].counterRatio.toFixed(2)+'</span>';
													str+='</td>';
														str+='<td>';
														str+='<input class="performanceRating" value="'+result.paliticalPartyList[i].participantList[j].coverageRatio+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.paliticalPartyList[i].participantList[j].coverageRatio.toFixed(2)+'</span>';
													str+='</td>';
												str+='</tr>';
											}
										str+='</tbody>';
									str+='</table>';
								str+='</div>';
							str+='<hr class="m_0"/>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		
		
		$("#candidateOverAllPerformanceCohortId").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
		$(".dataTableSorting").dataTable({
		"iDisplayLength": 5,
		"aaSorting": [],
		"aLengthMenu": [[5, 15, 20, -1], [5, 15, 20, "All"]]
	});
	}else{
		$("#candidateOverAllPerformanceCohortId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
	}
}

function buildRolesPerformanceOfCandidatePressmeet(result){
	
		var str='';
		if(result.participantList !=null && result.participantList.length>0){
			str+='<div class="table-responsive">';
				str+='<table class="table tableTopPressmeet tablepressmeetMainText">';			
				for(var i in result.participantList){
					str+='<tr>';
							str+='<td class="text-capital" style="width:25% !important" id="'+result.participantList[i].name+'">'+result.participantList[i].name+'</td>';
							str+='<td class="text-capital">';
								str+='<p>PARTY</p>';
								str+='<p><img src="newCoreDashBoard/img/'+result.participantList[i].partyName+'.png" class="PressmeetPartyIcon"/>'+result.participantList[i].partyName+'</p>';
							str+='</td>';
							str+='<td class="text-capital">';
								str+='<p>Press Meets</p>';
								str+='<p class="text-muted"><span style="cursor:pointer;" class="performancePressMeetCls" attr_partyId="'+result.participantList[i].partyId+'" attr_candidateId="'+result.participantList[i].id+'"><a>'+result.participantList[i].totalPressmeet+'</a></span></p>';
							str+='</td>';
							str+='<td class="text-capital">';
								str+='<p>performance</p>';
								str+='<input class="performanceRating" value="'+result.participantList[i].rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom"  data-readonly>'+result.participantList[i].rating.toFixed(2)+'</span>';
							str+='</td>';
						str+='</tr>';
				}
				str+='</table>';
			str+='</div>';
		}else{
			str+='<div class="text-capital">No Data Available</div>';
		}	
		$("#candidatePressMeetPerformanceId").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});		
	}
 
 function buildPublicationAndPartyWiseDetails(result)
{
	var str='';
	if(result !=null){
		str+='<div class="table-responsive">';
			str+='<table class="table tablePressmeetVs m_top10">';
			  str+='<tbody>';
					for(var i in result.articlesList){
						if(result.articlesList[i].name !=null){
						str+='<tr>';
							str+='<td class="b_right1"><img src="newCoreDashBoard/img/Nes_Papers_Small LOGO/'+result.articlesList[i].name+'.png" class="publicationLogo" alt="Eenadu Logo"/>'+result.articlesList[i].name+'</td>';
							for(var j in result.articlesList[i].paliticalPartyList){
								str+='<td>';

									str+='<p class="text-capital"><img  src="newCoreDashBoard/img/'+result.articlesList[i].paliticalPartyList[j].partyName+'.png" style="height:30px;width:30px;" class="debatesPartyIcon"/> '+result.articlesList[i].paliticalPartyList[j].partyName+'</p>';
									
									str+='<input class="performanceRating" value="'+result.articlesList[i].paliticalPartyList[j].rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.articlesList[i].paliticalPartyList[j].rating.toFixed(2)+'</span>';
									
									str+='<p>Press Meets</p>';
									str+='<p class="text-muted"><span style="cursor:pointer;" class="pressmeetIdCountPublicationCls" attr_partyId="'+result.articlesList[i].paliticalPartyList[j].partyId+'" attr_articleId="'+result.articlesList[i].id+'"><a>'+result.articlesList[i].paliticalPartyList[j].totalPressmeet+'</a></span></p>';
									str+='</td>';
							}
						str+='</tr>';
						}
					}
			  str+='</tbody>';
			str+='</table>';
		str+='</div>';
		$("#publicationAndPartyWiseDetailsId").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
	}else{
		$("#publicationAndPartyWiseDetailsId").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
	}
	
}

 function getDesignationWisePerformance(){
    
  $("#designationWiseTotalPressMeetDetails").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
  $.ajax({
   url: wurl+"/CommunityNewsPortal/webservice/getDesignationWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getDesignationWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"
  }).then(function(result){   
   if(result !=null){
	   buildPressmeetDesignationWiseTotalPressmeetDetails(result);
   }else{  
    $("#designationWiseTotalPressMeetDetails").html("<h3>NO DATA AVAILABLE</h3>");
   }
  });

 }
function buildPressmeetDesignationWiseTotalPressmeetDetails(result){
	var str='';
	if(result !=null){
		str+='<table class="table table-striped" id="designationWiseDtlsId">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Designation</th>';
					str+='<th>Total Pressmeets</th>';
					str+='<th>Total Spokes Persons</th>';
					str+='<th>Performance</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.pressmeetList){
				str+='<tr>';
					str+='<th scope="row">'+result.pressmeetList[i].partyName+'</th>';
					str+='<td class="designationWisePressmeetCountCls" style="cursor:pointer;" attr_designation_id="'+result.pressmeetList[i].partyId+'"><a>'+result.pressmeetList[i].totalPressmeet+'</a></td>';
					str+='<td class="designationWiseCandidatesPressmeetCls" style="cursor:pointer;" attr_designation="'+result.pressmeetList[i].partyName+'" attr_designation_id="'+result.pressmeetList[i].partyId+'"><a>'+result.pressmeetList[i].candidatesCount+'</a></td>';
					str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].percentage.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].percentage.toFixed(2)+'</span>';
					str+='</td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		$("#designationWiseTotalPressMeetDetails").html(str);
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		}); 
		$("#designationWiseDtlsId").dataTable({
			"iDisplayLength": 10,       
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}else{
		$("#designationWiseTotalPressMeetDetails").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
	}
}
$(document).on("click",".designationWiseCandidatesPressmeetCls",function(){
	var designation = $(this).attr("attr_designation");
	var designationId = $(this).attr("attr_designation_id");
	
	getCandidateWiseCandidateOverAllPerformancePressmeetCohort(designation,designationId);
}); 
 
 
function getCandidateWiseCandidateOverAllPerformancePressmeetCohort(designation,designationId){
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html(designation);   
	$(".pressmeetModelCls").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		url: wurl+"/CommunityNewsPortal/webservice/getCandidateWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"+designationId+"/"
		//url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidateWisePerformance/"+fromDate+"/"+toDate+"/1,2,3,4/"+designationId+"/"
	}).then(function(result){                       
		if(result !=null){
			buildCandidateWiseCandidateOverAllPerformancePressmeetCohort(result,designation,designationId);
		}else{  
			$(".pressmeetModelCls").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>');
		}
	});
}
function buildCandidateWiseCandidateOverAllPerformancePressmeetCohort(result,designation,designationId){
	var str="";
	str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered tablepressmeetMainText m_top10 dataTableSorting text-center">';
				str+='<thead>';
					str+='<th>NAME</th><th>PressMeets </th><th>OVERALL PERFORMANCE </th><th>SUBJECT</th><th>CREATIVITY</th><th>TIMELY</th><th>COUNTER ATTACK</th><th>COVERAGE</th>';
				str+='</thead>'; 
				str+='<tbody>';
				for(var i in result.pressmeetList){
					str+='<tr>';
						str+='<td class="text-capitalize" style="min-width:150px;"">'+result.pressmeetList[i].candidateName+' ('+result.pressmeetList[i].partyName+')</td>';
						str+='<td>';       
						str+='<h5 ><a style="cursor:pointer;" class="pressMeetsInnerCls" attr_designationId="'+designationId+'"attr_candidateId="'+result.pressmeetList[i].candidateId+'">'+result.pressmeetList[i].pressMeetCount+'</a></h5>';
						str+='</td>';     
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].percentage.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].percentage.toFixed(2)+'</span>';
						str+='</td>';
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].subjectRatio.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].subjectRatio.toFixed(2)+'</span>';
						str+='</td>';
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].creativityRatio.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].creativityRatio.toFixed(2)+'</span>';
						str+='</td>';
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].timelyRatio.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].timelyRatio.toFixed(2)+'</span>';
						str+='</td>';
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].counterRatio.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].counterRatio.toFixed(2)+'</span>';
						str+='</td>';
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].coverageRatio.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="label label-default label-xs labelCustom">'+result.pressmeetList[i].coverageRatio.toFixed(2)+'</span>';
						str+='</td>';
					str+='</tr>';
					}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';	
	str+='</div>';	 
	$(".pressmeetModelCls").html(str);
	$(".performanceRating").rating({
		showClear: false,
		showCaption:false,
		hoverOnClear: true,
		animate:false
	});  
	$(".dataTableSorting").dataTable();
 }

$(document).on("click",".partyWisePressMeetCls",function(){//partyWisePressMeetCls
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details");   
	var partyId = $(this).attr("attr_partyId");
	var type = $(this).attr("attr_type");
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
   url: wurl+"/CommunityNewsPortal/webservice/getPressMeetDetailsByPartyWise/"+fromDate+"/"+toDate+"/"+partyId+"/"+type+"/"
  // url: "http://localhost:8080/CommunityNewsPortal/webservice/getPressMeetDetailsByPartyWise/"+fromDate+"/"+toDate+"/"+partyId+"/"+type+"/"
	}).then(function(result){
     
   if(result !=null){
		if( type == 'candidate'){
		 buildCorePressmeetBasicDetailsOfCandidates(result);
		}else if(type='party'){
	      buildCorePressmeetBasicDetailsOfPartyClick(result);
	   }
	  
	}
	});
});
 
 function buildCorePressmeetBasicDetailsOfCandidates(result){
	var str = '';
		if(result.pressmeetList !=null && result.pressmeetList.length>0){
								
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		 
			 	str+= '<div class="table-responsive">';
				str+= '<table class="table table-bordered " id="dataTablePressMeetId">';
				str+= '<thead style="background:#ccc">';
				str+='<th>Candidate Name</th>';
				str+='<th style="max-width:200px;">Designation</th>';
				str+='<th>Party Name </th>';
				str+='<th>Subject </th>';
				str+='<th>CREATIVITY/COUNTER </th>';
				str+='<th>TIMELY/OUT OF TIME </th>';
				str+='<th>COUNTER ATTACK </th>';
				str+='<th>COVERAGE </th>';
				str+= '</thead>';
				str+= '<tbody>';					
				for(var i in result.pressmeetList){
				str+='<tr>';
				str+='<td>'+result.pressmeetList[i].candidateName+'</td>';
				str+='<td>'+result.pressmeetList[i].designation+'</td>';
				str+='<td>'+result.pressmeetList[i].partyName+'</td>';
					for(var j in result.pressmeetList[i].participantList){
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].participantList[j].rating+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class=class="badge" style="font-weight:bold"  data-readonly>'+result.pressmeetList[i].participantList[j].rating.toFixed(2)+'</span>';
						str+='</td>';
					}	
				str+='</tr>';
				}	
				str+= '</tbody>';
				str+= '</div>';
				str+= '</div>';
				
			
			$(".pressmeetModelCls").html(str);
			$(".performanceRating").rating({
				showClear: false,
				showCaption:false,
				hoverOnClear: true,
				animate:false
			});
			$('#dataTablePressMeetId').dataTable({
					"iDisplayLength": 10,
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
					
			});
		}
		
}
 
 function buildCorePressmeetBasicDetailsOfPartyClick(result){
	var str = '';
		if(result.pressmeetList !=null && result.pressmeetList.length>0){
								
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		 
			 	str+= '<div class="table-responsive">';
				str+= '<table class="table table-bordered" id="dataTablePressMeetPartyId">';
				str+= '<thead style="background:#ccc">';
				str+='<th style="max-width:300px;">Title</th>';                         
				str+='<th>Conducted Date</th>';
				str+='<th>Candidate Name/Party Name </th>'
				str+= '</thead>';
				str+= '<tbody>';					
				for(var i in result.pressmeetList){
				str+='<tr>';
				str+='<td>'+result.pressmeetList[i].title+'</td>';
				str+='<td>'+result.pressmeetList[i].conductedDate+'</td>';
				str+='<td>';
					str+='<table class="table table-bordered m_top10" style="background-color:#D3D3D3" >';
					str+='<tbody>';
					for(var j in result.pressmeetList[i].participantList){
						str+='<tr>';
						str+='<td width="70%">'+result.pressmeetList[i].participantList[j].candidateName+'</td>';
						str+='<td width="30%">'+result.pressmeetList[i].participantList[j].partyName+'</td>';
						str+='</tr>';      
					}
					str+='</tbody>';	
					str+='</table>';	
				str+='</td>';
				str+='</tr>';
				}	
				str+= '</tbody>';
				str+= '</div>';
				str+= '</div>';
				
			
			$(".pressmeetModelCls").html(str);
			$('#dataTablePressMeetPartyId').dataTable({
				"iDisplayLength": 10,
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			 });
			
		}
		
}
 
  $(document).on("click",".overAllPerformanceCls",function(){
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var performancePartyId = $(this).attr("attr_partyId");
		 buildScaleBasedCohortPressMeetClickPerformance(performanceResult,performancePartyId);
});
 
 function buildScaleBasedCohortPressMeetClickPerformance(result,partyIds)
{
	var str='';
	if(result !=null){
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="table-responsive">';
			for(var i in result.pressmeetList){
			if(result.pressmeetList[i].partyId == partyIds){
				str+='<table class="table table-bordered  m_top10">';
				str+= '<thead style="background:#ccc">';
				str+='<th>Party Name</th>';
				str+='<th>PressMeet Count</th>';
				str+='<th>Subject</th>';
				str+='<th>Creativity / Counter</th>';
				str+='<th>Timely / Out of time</th>';
				str+='<th>Counter Attack(Scale)</th>';
				str+='<th>Coverage</th>';
				str+= '</thead>';
				  str+='<tbody>';
					
					if(result.pressmeetList[i].partyId == 872 || result.pressmeetList[i].partyId == "872"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							  str+='<h4><span style="cursor:pointer;">'+result.pressmeetList[i].pressMeetCount+'</span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom pull-right"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else if(result.pressmeetList[i].partyId == 1117 || result.pressmeetList[i].partyId == "1117"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							  str+='<h4><span style="cursor:pointer;">'+result.pressmeetList[i].pressMeetCount+'</span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom pull-right"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else if(result.pressmeetList[i].partyId == 163 || result.pressmeetList[i].partyId == "163"){
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							  str+='<h4><span style="cursor:pointer;">'+result.pressmeetList[i].pressMeetCount+'</span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom pull-right"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}else{
						str+='<tr>';
						str+='<td>';
							str+='<img  src="newCoreDashBoard/img/'+result.pressmeetList[i].partyName+'.png" alt="'+result.pressmeetList[i].partyName+'" class="PressmeetPartyIcon"/>'+result.pressmeetList[i].partyName+'';
						str+='</td>';
						str+='<td>';
							  str+='<h4><span style="cursor:pointer;">'+result.pressmeetList[i].pressMeetCount+'</span></h4>';
							
						str+='</td>';
						for(var j in result.pressmeetList[i].characterList){
						str+='<td>';
							str+='<input class="performanceRating" value="'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><br/><h5 class="label label-default label-xs labelCustom pull-right"  data-readonly>'+result.pressmeetList[i].characterList[j].rating.toFixed(2)+'</h5>';
						str+='</td>';
						}
						str+='</tr>';
					}
						
						
					
				 str+='</tbody>';
				str+='</table>';
			}
			str+='</div>';
		str+='</div>';
		$(".pressmeetModelCls").html(str)
		$(".performanceRating").rating({
			showClear: false,
			showCaption:false,
			hoverOnClear: true,
			animate:false
		});
	 }	
	}else{
		$(".pressmeetModelCls").html('<div class="col-sm-12"<h4>NO DATA AVAILABLE</h4></div>')
	}
}

 $(document).on("click",".pressmeetIdCountCls",function(){
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
	var partyId = $(this).attr("attr_partyId");
	var candidateId = $(this).attr("attr_candidateId");
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
    url: wurl+"/CommunityNewsPortal/webservice/getCandidatePerformanceInPressMeetByPartyWiseClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidatePerformanceInPressMeetByPartyWiseClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"
	}).then(function(result){
     
   if(result !=null){
	   buildCandidateWisePartyWisePressmeetsClick(result);
		}
	});
});
 
 function buildCandidateWisePartyWisePressmeetsClick(result){
	var str = '';
		if(result.pressmeetList !=null && result.pressmeetList.length>0){
								
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		 
			 	str+= '<div class="table-responsive">';
				str+= '<table class="table table-bordered " id="dataTablePressmeetCandidateId">';
				str+= '<thead style="background:#ccc">';
				str+='<th>PressMeet Title</th>';
				str+='<th>Candidate Name</th>';
				str+='<th>Designation</th>';
				str+='<th>Party Name </th>';
				str+='<th>Subject</th>';
				str+='<th style="min-width:150px;">Creativity / Counter</th>';
				str+='<th>Timely / Out of time</th>';
				str+='<th>Counter Attack(Scale)</th>';
				str+='<th>Coverage</th>';
				str+= '</thead>';
				str+= '<tbody>';					
				for(var i in result.pressmeetList){
				str+='<tr>';
				str+='<td>'+result.pressmeetList[i].title+'</td>';
				str+='<td>'+result.pressmeetList[i].candidateName+'</td>';
				str+='<td>'+result.pressmeetList[i].designation+'</td>';
				str+='<td>'+result.pressmeetList[i].partyName+'</td>';
					for(var j in result.pressmeetList[i].participantList){
						str+='<td>';
						str+='<input class="performanceRating" value="'+result.pressmeetList[i].participantList[j].rating.toFixed(2)+'" type="hidden" class="rating" min=0 max=5 step=0.2 data-size="xs"  data-readonly><span class="badge" style="font-weight: bold"  data-readonly>'+result.pressmeetList[i].participantList[j].rating.toFixed(2)+'</span>';
						str+='</td>';
					}	
				str+='</tr>';
				}	
				str+= '</tbody>';
				str+= '</div>';
				str+= '</div>';
				str+= '</div>';
				str+= '</div>';
			
			$(".pressmeetModelCls").html(str);
			 $(".performanceRating").rating({
				showClear: false,
				showCaption:false,
				hoverOnClear: true,
				animate:false
		  });
		 $('#dataTablePressmeetCandidateId').dataTable({
				"iDisplayLength": 10,
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			 });
			
		}
		
}
 
 $(document).on("click",".pressmeetIdCountPublicationCls",function(){
  var edtionList =[];
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
	var partyId = $(this).attr("attr_partyId");
	var articleId = $(this).attr("attr_articleId");
	  $(".edtionTypescls li").each(function(){			
        if($(this).hasClass("active")){
		 var editionId = $(this).attr("id");
		 //alert(editionId);
			if(editionId == 0){
				edtionList.push(1,2,3);
			}else if(editionId == 1){
				edtionList.push(1);
			}else{
				edtionList.push(2,3);
			}
			
		}
        
      });
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
   url: wurl+"/CommunityNewsPortal/webservice/getparyOverallPressMeetByEditionIdsClick/"+fromDate+"/"+toDate+"/"+partyId+"/0/"+edtionList+"/"+articleId+"/"			
 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getparyOverallPressMeetByEditionIdsClick/"+fromDate+"/"+toDate+"/"+partyId+"/0/"+edtionList+"/"+articleId+"/"
	}).then(function(result){
     
   if(result !=null){
	   buildCorePressmeetBasicDetailsOfPartyClick(result);
   }
	});
});
 
 $(document).on("click",".performancePressMeetCls",function(){
  var edtionList =[];
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
	var partyId = $(this).attr("attr_partyId");
	var candidateId = $(this).attr("attr_candidateId");
	
	  $(".edtionTypescls li").each(function(){			
        if($(this).hasClass("active")){
		 var editionId = $(this).attr("id");
		 //alert(editionId);
			if(editionId == 0){
				edtionList.push(1,2,3);
			}else if(editionId == 1){
				edtionList.push(1);
			}else{
				edtionList.push(2,3);
			}
			
		}
        
      });
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
	url: wurl+"/CommunityNewsPortal/webservice/getparyOverallPressMeetByEditionIdsClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"+edtionList+"/0"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getparyOverallPressMeetByEditionIdsClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"+edtionList+"/0"
	}).then(function(result){
     
   if(result !=null){
	  buildCandidateWisePartyWisePressmeetsClick(result); 
   }
	});
});
 function candidateIdAndPartyIdGraphClick(partyId,candidateId){
	$("#pressmeetModelDivId").modal("show");
	$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
	$(".pressmeetModelCls").html("");	
	$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$.ajax({
    url: wurl+"/CommunityNewsPortal/webservice/getCandidatePerformanceInPressMeetByPartyWiseClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"
  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getCandidatePerformanceInPressMeetByPartyWiseClick/"+fromDate+"/"+toDate+"/"+partyId+"/"+candidateId+"/"
	}).then(function(result){
     
   if(result !=null){
	   buildCandidateWisePartyWisePressmeetsClick(result);
   }
	}); 
 }
 $(document).on("click",".designationWisePressmeetCountCls",function(){
		$("#pressmeetModelDivId").modal("show");
		$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
		var designationId = $(this).attr("attr_designation_id");
		$(".pressmeetModelCls").html("");	
		$(".pressmeetModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$.ajax({
	  url: wurl+"/CommunityNewsPortal/webservice/getDesignationWiseOverAllPerformanceClick/"+fromDate+"/"+toDate+"/"+designationId+"/0"
	 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getDesignationWiseOverAllPerformanceClick/"+fromDate+"/"+toDate+"/"+designationId+"/0"
		}).then(function(result){
	     
	   if(result !=null){
		 buildCorePressmeetBasicDetailsOfPartyClick(result); 
			}
		});
	 });
 
  $(document).on("click",".pressMeetsInnerCls",function(){
		//$("#pressmeetModelDivId").modal("show");
		$("#pressmeetInnerModelDivId").modal("show");
		//$("#modalPressmeetHeadingId").html("Pressmeet Details"); 
		$("#modalInnerPressmeetHeadingId").html("Pressmeet Details"); 
		var designationId = $(this).attr("attr_designationId");
		var candidateId =$(this).attr("attr_candidateId");
		$(".pressmeetInnerModelCls").html("");	
		$(".pressmeetInnerModelCls").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
		$.ajax({
	  url: wurl+"/CommunityNewsPortal/webservice/getDesignationWiseOverAllPerformanceClick/"+fromDate+"/"+toDate+"/"+designationId+"/"+candidateId+"/"
	 // url: "http://localhost:8080/CommunityNewsPortal/webservice/getDesignationWiseOverAllPerformanceClick/"+fromDate+"/"+toDate+"/"+designationId+"/"+candidateId+"/"
		}).then(function(result){
	     
	   if(result !=null){
		 buildCoreInnerPressmeetBasicDetailsOfPartyClick(result); 
			}
		});
	 });
	 
	function buildCoreInnerPressmeetBasicDetailsOfPartyClick(result){
	var str = '';
		if(result.pressmeetList !=null && result.pressmeetList.length>0){
								
			str+= '<div class="col-md-12 col-xs-12 col-sm-12">';
		 
			 	str+= '<div class="table-responsive">';
				str+= '<table class="table table-bordered" id="dataTablePressMeetPartyId">';
				str+= '<thead style="background:#ccc">';
				str+='<th style="max-width:300px;">Title</th>';                         
				str+='<th>Conducted Date</th>';
				str+='<th>Candidate Name/Party Name </th>'
				str+= '</thead>';
				str+= '<tbody>';					
				for(var i in result.pressmeetList){
				str+='<tr>';
				str+='<td>'+result.pressmeetList[i].title+'</td>';
				str+='<td>'+result.pressmeetList[i].conductedDate+'</td>';
				str+='<td>';
					str+='<table class="table table-bordered m_top10" style="background-color:#D3D3D3">';
					str+='<tbody>';
					for(var j in result.pressmeetList[i].participantList){
						str+='<tr>';
						str+='<td width="70%">'+result.pressmeetList[i].participantList[j].candidateName+'</td>';
						str+='<td width="30%">'+result.pressmeetList[i].participantList[j].partyName+'</td>';
						str+='</tr>';      
					}
					str+='</tbody>';	
					str+='</table>';	
				str+='</td>';
				str+='</tr>';
				}	
				str+= '</tbody>';
				str+= '</div>';
				str+= '</div>';
				
			
			$(".pressmeetInnerModelCls").html(str);
			$('#dataTablePressMeetPartyId').dataTable({
				"iDisplayLength": 10,
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
				
			 });
			
		}
		
}
$(document).on("click",".closeModalpress",function(){
	setTimeout(function(){
		$('body').addClass("modal-open");
	}, 400);                     
});