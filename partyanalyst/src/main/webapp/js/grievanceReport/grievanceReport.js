var start = moment().subtract(29, 'days');
var end = moment();
function cb(start, end){
	$('#reportrange span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
}

$('#reportrange').daterangepicker({
	startDate: start,
	endDate: end,
	ranges: {
	   'Today': [moment(), moment()],
	   'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
	   'Last 7 Days': [moment().subtract(6, 'days'), moment()],
	   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
	   'This Month': [moment().startOf('month'), moment().endOf('month')],
	   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
}, cb);
 
var callCenterUserFDate=moment().format("DD/MM/YYYY");
var callCenterUserTDate=moment().format("DD/MM/YYYY");

$('#reportrange').on('apply.daterangepicker', function(ev, picker) {
	callCenterUserFDate = picker.startDate.format('DD/MM/YYYY');
	callCenterUserTDate = picker.endDate.format('DD/MM/YYYY');
	getTotalLocationWiseGrivenaceReport();
});
$(document).on("click",".daterangeClorCls",function(){ 
    $(".daterangeClorCls").removeClass("dateColorCls");
}); 


getGrievanceReport();
 function getGrievanceReport(){
$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
$("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
    var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val();
    var jsObj ={
		fromDate: callCenterUserFDate,                       
		toDateStr:callCenterUserTDate,  
		deptId:deptId,
		sourceId:sourceId,       
		rangeType:"day",     
		stateId:1
	}
	$.ajax({
			type:'GET',         
			url: 'getGrievanceReportAction.action',
			data: {task :JSON.stringify(jsObj)}
			}).done(function(result){
			if(result !=null && result.length>0){
				buildGrievanceReport(result);
				buildLocationWiseGrivenacereportGraph(result);
				getAverageIssuePendingDays(result);
				getAverageIssuePendingDays(result);
		    }
	}); 
	    
}	
//location wise table
 function buildGrievanceReport(result) {
	$("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
	$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var str='';
        str+='<table id="grievanceReportTableId" class="table table-bordered " cellspacing="0">';
        str+='<thead>';
        str+='<tr>';
        str+='<th>District</th>';
        str+='<th>Total</th>';
     for(var i in result[0].subList1){       
           str+='<th>'+result[0].subList1[i].statusType+'</th>';
       }
       for(var j in result[0].subList2){         
          str+='<th style="background-color:#ecebd6">'+result[0].subList2[j].day+'</th>';
       }
         str+=' </tr>';
         str+='</thead>';
     str+='<tbody>';
     var locTotal = 0;
     for(var i in result){
 		str+='<tr>'; 
         str+='<td style="cursor:pointer;" attr_location_id="'+result[i].id+'" class="bellowLvlLocCls"><i class="glyphicon glyphicon-plus-sign"></i>'+result[i].name+'</td>';         
         str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_group_type="status" attr_location_id="'+result[i].id+'">'+result[i].totalAlertCnt+'</td>';
         locTotal = parseInt(locTotal) + parseInt(result[i].totalAlertCnt);
 		for(var j in result[i].subList1){
           if(result[i].subList1[j].totalAlertCnt != 0){
             str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_status_id="'+result[i].subList1[j].statusTypeId+'" attr_location_id="'+result[i].id+'" attr_group_type="status">'+result[i].subList1[j].totalAlertCnt+'</td>';
           }else{
             str+='<td>-</td>';
           }      
        }
         for(var j in result[i].subList2){  
           if(result[i].subList2[j].totalAlertCnt != 0){        
             str+='<td style="cursor:pointer;" class="getAlertDtlsCls" attr_pattern="'+result[i].subList2[j].day+'" attr_location_id="'+result[i].id+'" attr_group_type="day">'+result[i].subList2[j].totalAlertCnt+'</td>';
           }else{      
            str+='<td>-</td>';
           }
         } 
              str+='</tr>';
     }  
 			str+='<tr>';
 			str+='<td>Total</td>';
 			str+='<td>'+locTotal+'</td>';
       for(var i in result[0].subList1){
            str+='<td>'+result[0].subList1[i].grandTotal+'</td>';//  result[0].subList1[i].statusType  
       }
       for(var i in result[0].subList2){
            str+='<td>'+result[0].subList2[i].grandTotal+'</td>';    
        }   
 		   str+='</tr>';
 		   str+='</tbody>';
 		   str+='</table>';
 		  $('#grivenaceTableId').html(str);
 		  $("#totalAlertCountId").html(locTotal);
 		  $('#grievanceReportTableId').DataTable();  
 }


 getAverageIssuePendingDays();
 function getAverageIssuePendingDays(){
 	var deptIds=[];
 	var sourceIds =[];
 	deptIds.push(49);
 	sourceIds.push(1);
 	sourceIds.push(2);
 	sourceIds.push(3);
     var jobj = {
       deptIds :deptIds,
 	  sourceIds:sourceIds,
 	  fromDate : callCenterUserFDate,//2016-11-01
 	  toDate:callCenterUserTDate//2017-05-01
     }
     $.ajax({
       type : "POST",
       url  : "getAverageIssuePendingDaysAction.action",
       dataType: 'json',
       data: {task:JSON.stringify(jobj)},
     }).done(function(result){
     	var str ='';
 		if(result != null){
 			str +=''+result.count+'Days '+result.totalCount+'Hours';
 			 $("#averageIssueId").html(str);
 		}
 		$('#issuePendingCntId').text(str);
     });
     
     }	  	   	
//on change media 	
 function getMediaInformation(){
 $("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
 	var jobj = {
            fromDate: callCenterUserFDate,                       
 		   toDateStr:callCenterUserTDate,  
 		   deptId:deptId,
 		   sourceId:sourceId,
 		   rangeType:rangeType,
 		   stateId:1
     }
 	$.ajax({
 		 type : "GET",
 		 url  : "getGrievanceReportAction.action",
 		 dataType: 'json',
 		 data: {task:JSON.stringify(jobj)},
     }).done(function(result){
 		 buildGrievanceReport(result);
 	     buildLocationWiseGrivenacereportGraph(result);
 		 getAverageIssuePendingDays(result);
 	});
 }

//on dept change
 function getDepartmentInformation(){
 $("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
 	var jobj = {
           fromDate: callCenterUserFDate,                       
 		  toDateStr:callCenterUserTDate,  
 		  deptId:deptId,
 		  sourceId:sourceId,
 		  rangeType:rangeType,   
 		  stateId:1
 	}
 	$.ajax({
        type : "GET",
        url  : "getGrievanceReportAction.action",
        dataType: 'json',
        data: {task:JSON.stringify(jobj)},
     }).done(function(result){
 		buildGrievanceReport(result);
 		buildLocationWiseGrivenacereportGraph(result);
 		getAverageIssuePendingDays(result);
 	});
 }
//on change daterangepicker
 function getTotalLocationWiseGrivenaceReport(){
 $("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
 $("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
     var sourceId=$("#selectMediaId").val();
     var deptId=$("#selecDepartmentId").val();
	 var rangeType=$("#dateRangeId").attr("value");
     var jsObj ={
 		fromDate:callCenterUserFDate,                         
 		toDateStr:callCenterUserTDate,
         sourceId :sourceId,  
         deptId:deptId,
 		rangeType:rangeType,
         stateId:1		 
     }
     $.ajax({
     type:'GET',         
     url: 'getGrievanceReportAction.action',
     data: {task :JSON.stringify(jsObj)}
     }).done(function(result){
 		buildGrievanceReport(result);
 		buildLocationWiseGrivenacereportGraph(result);
 		getAverageIssuePendingDays(result);
 	});
 }
 //on click month week day btn
 $(document).on("click",".rangeTypeCls",function(){  
$("#barGraph").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
$("#grivenaceTableId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
var sourceId=$("#selectMediaId").val();
    var deptId=$("#selecDepartmentId").val(); 
	var rangeType=$(this).attr("attr_range_val");
    var jsObj ={
		fromDate:callCenterUserFDate,                         
		toDateStr:callCenterUserTDate,
        sourceId :sourceId,  
        deptId:deptId,
		rangeType:rangeType,
        stateId:1                     
	}
	$.ajax({
		type:'GET',         
		url: 'getGrievanceReportAction.action',
		data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildGrievanceReport(result);
		buildLocationWiseGrivenacereportGraph(result);
		getAverageIssuePendingDays(result);
	});
});
//Graph building
 function buildLocationWiseGrivenacereportGraph(result){
 	var mainArry=[];
 	    for(var i in result[0].subList1){
 			var locationWiseReport=[];
 				 locationWiseReport.push(result[0].subList1[i].statusType)    //result[0].subList1[i].grandTotal
 				 locationWiseReport.push(result[0].subList1[i].grandTotal) 
 				 mainArry.push(locationWiseReport);
 	    }
 Highcharts.chart('barGraph', {
             colors: ['#FFCF2C'],
             chart: {
                 backgroundColor:'transparent',
                 type: 'column'
             },
             title:{
                 text: null,
                 align: 'left'
             },
             xAxis: {
                 min: 0,
                 gridLineWidth: 0,
                 minorGridLineWidth: 0,
                 type: 'category'
             },
             yAxis: {
                 lineWidth: 0,
                 gridLineWidth: 0,
                 minorGridLineWidth: 0,
                 allowDecimals: true,
                 title: {
                     enabled: false
                 },
                 stackLabels: {
                     enabled: false,
                     style: {
                         fontWeight: 'bold',
                         color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                     }
                 }
             },

             tooltip:{
                 enabled: true,
 				useHtml:true,
 				pointFormat: '<span>{point.y}</span>'
             },
             legend: {
                 enabled: false
             },
             plotOptions: {
                 series: {
                     borderWidth: 0,
                     dataLabels: {
                         enabled: true,

                     }
                 }
             },
             plotOptions: {
                 series: {
                     borderRadius: 2.5,
                     pointWidth: 15
                 }
             },
             exporting: {
                 buttons: {
                     contextButton: {
                         enabled: false
                     },
                 }
             },
             series: [{
 				colorByPoint: false,
                 lineWidth: 1,
                 data:mainArry
             }],
         });
 }
onLoadInitialisations();        
function onLoadInitialisations(){                     
	$(document).on("click",".getAlertDtlsCls",function(){
		$("#totalAlertDistricTableId").html("");  
		$("#grievanceDtlsModalId").modal("show");     
		$("#grevinceDetailsId").html('<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>');
var rangeType=$("#dateRangeId").attr("value");
		var locationId = $(this).attr("attr_location_id");
		var group = $(this).attr("attr_group_type");
		var statusId = $(this).attr("attr_status_id");
		var pattern = $(this).attr("attr_pattern");
		 
		var sourceId=$("#selectMediaId").val();
		var deptId=$("#selecDepartmentId").val(); 
		//alert(locationId+":"+statusId+":"+group+":"+pattern+":"+rangeType);
		if(locationId==undefined){
		   locationId=0;      
		}
		if(statusId==undefined){
		   statusId=0;      
		}
		if(group==undefined){
		   group="";
		}
		if(pattern==undefined){
		   pattern="";     
		}
		 
		var jobj = {
		  fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  group:group,
		  pattern:pattern,
		  rangeType:rangeType        
		  
		}
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAction.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
		  var str ='';
		  if(result != null){
				if(group=="day"){ 
				buildTotalAlertDistrictTable(result);
				}
				buildGrivenceDetailsTable(result,group);
		  }
		});
     
	});
	
	$(document).on("click",".rangeTypeCls",function(){
		$("#dateRangeId").attr("value",$(this).attr("attr_range_val"));
	});
	$(document).on("click",".getAlertDtlsOnLocCls",function(){
		var locationId = $(this).attr("attr_location_id");
		var statusId = $(this).attr("attr_status_id");
		var sourceId=$("#selectMediaId").val();
        var deptId=$("#selecDepartmentId").val();
	    var rangeType=$("#dateRangeId").attr("value");
		alert(statusId);
		var jobj = {
		  fromDate: callCenterUserFDate,                       
		  toDateStr:callCenterUserTDate,  
		  deptId:deptId,
		  sourceId:sourceId,                                      
		  stateId:1,
		  locationId:locationId,
		  statusId : statusId,      
		  rangeType:rangeType        
		  }
		$.ajax({    
		  type : "POST",
		  url  : "getGrievanceReportBasedOnLocationAndStatus.action",  
		  dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
		}).done(function(result){
			
		});
	});
	$(document).on("click",".getAlertDtls",function(){
		var alertId = $(this).attr("attr_alert_id");
		var alertStatus = $(this).attr("attr_alert_status");
		getAlertData(alertId);
		getAlertAssignedCandidates(alertId);
		getAlertStatusCommentsTrackingDetails(alertId,alertStatus);
		getVerificationDtls(alertId);
	});
	$(document).on("click",".bellowLvlLocCls",function(){
		$("#bellowLvlLocId").modal("show");
	});
}

function getAlertData(alertId){ 
$("#cdrModelDivId").modal("show");   
		var jsObj ={
			alertId  :alertId,
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertsDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			$("#alertGroupAttachTitId,#alertGroupAttachImgId").html(' ');
			if(result != null && result.length > 0){
				buildAlertData(result);
				if(result[0].categoryId == 2)
				{
					getGroupedArticlesInfo(result[0].alertCategoryTypeId)
				}
			}    
		});
	} 

function buildAlertData(result){
		var docName = '';
		var extName =[];
		$("#tourDocHeadingId").html("<h5 style='color:#FFFFFF;font-size:14px;'>ALERT TITLE</h5><h5 class='text-capital m_top10' style='color:#000'>"+result[0].title+"</h5>");
		$("#cdrModelId").html("<h5 class='text-muted headingColorStyling'>ALERT DESCRIPTION</h5>");
		$("#alertDestId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 6px;'>"+result[0].desc+"</p>");
		$("#sourceHeadingId").html("<h5 class='text-muted headingColorStyling'>ALERT SOURCE</h5>");
		$("#headingNameId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 10px;'>"+result[0].alertSource+"</p>");
		
		if(result[0].documentList != null && result[0].documentList.length >= 1){
			$("#alertDocHeadingId").html("<h5  class='text-muted headingColorStyling'>ALERT DOCUMENTS</h5>");
			var docStr = '';
			docStr+='<ul>';
			for(var i in result[0].documentList){
				docName = result[0].documentList[i];
				extName = docName.split(".");
				if(result[0].documentNameList[i].search('#') != -1 || result[0].documentNameList[i].search('u0') != -1){
					var randumNum = result[0].documentList[i].substring(result[0].documentList[i].indexOf("/")+1,result[0].documentList[i].lastIndexOf("."));      
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+randumNum+'.'+extName[1]+'</a></li>';  
				}else{
					docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+result[0].documentNameList[i]+'.'+extName[1]+'</a></li>';  
				}
				
			}
			docStr+='</ul>';
			$("#alertDocId").html(docStr);    
		}
		if(result[0].imageUrl != null && result[0].imageUrl.length > 1){    
			$("#alertAttachTitId").html("<h5  class='text-muted headingColorStyling'>ALERT ATTACHMENTS</h5>");
			var imgStr = '';
			imgStr+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
			imgStr+='<li><img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 90px; height: 90px;cursor:pointer;" class="articleImgDetailsCls" attr_articleId="'+result[0].alertCategoryTypeId+'"></img></li>';
			imgStr+='</ul> '; 
			$("#alertAttachImgId").html(imgStr);  
		}
		var str='';
		var invCandCnt = 0;
		if(result[0].subList.length > 0){
			for(var i in result){
				for(var j in result[i].subList){
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){    
						invCandCnt+=1;
					}
				}    
			}
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+invCandCnt+'</h5>';           
			str+='<ul class="list-inline assignedCandidatesUl1">';     
			for(var i in result){
				for(var j in result[i].subList){   
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){
						str+='<li>';      
							str+='<p style="color: rgb(0, 0, 0);"><b>'+result[i].subList[j].name+'</b></p>';
							if(result[i].subList[j].mobileNo.length <= 1  || result[i].subList[j].mobileNo == null){
							}else{
								str+='<p><i> - </i>'+result[i].subList[j].mobileNo+'</p>';      
							}  
							if(result[i].subList[j].committeePosition != null){
								str+='<p><i> - </i>'+result[i].subList[j].committeePosition+'</p>';  
							}     
						str+='</li>';      
					}
				}    
			}
			str+='</ul>';      
			
			$("#alertInvolvedCandidates").html(str);       
		}else{
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>'; 
			$("#alertInvolvedCandidates").html(str);        
		}
		$(".assignedCandidatesUl1").slick({          
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,    
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 5,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				
			  ]  
		}); 
	}	
	
	function getGroupedArticlesInfo(articleId)
	{
		$.ajax({
			  type : 'GET',      
			  url: wurl+"/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
			  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getGroupedArticlesInfo/"+articleId+""
		}).then(function(result){
			$("#alertGroupAttachTitId").html("<h5 class='text-muted headingColorStyling'>GROUPED ARTICLES</h5>");
			var str='';
			if(result !=null && result.length>0){
				str+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
				for(var i in result)
				{
					if(articleId != result[i].id){
						str+='<li class="articleImgDetailsCls" attr_articleId='+result[i].id+' style="cursor:pointer"><img src="http://mytdp.com/NewsReaderImages/'+result[i].name+'" style="width: 150px; height: 150px;margin-top:5px;"></img></li>';
					}
				}
				str+='</ul>';
			}
			$("#alertGroupAttachImgId").html(str);
		});
	}
	function getAlertAssignedCandidates(alertId){
		GlobalAlertData = [];
		var jsObj ={
			alertId  : alertId,    
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertAssignedCandidatesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){   
				buildAlertAssignedCandidates(result);  
			}else{
				var str = '';
				str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
				$("#alertAssignedCandidates").html(str);    
			}
		});
	}
	
	function buildAlertAssignedCandidates(result)
	{
	var str='';
	if(result[0].subList.length > 0){  
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+result[0].subList.length+'</h5>';
		str+='<ul class="list-inline assignedCandidatesUl">';
		for(var i in result)
		{
			for(var j in result[i].subList)
			{
				str+='<li>';
					str+='<p style="color:#000"><b>'+result[i].subList[j].name+'</b></p>';
					if(result[i].subList[j].committeePosition == null || result[i].subList[j].committeePosition.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].committeePosition+'</i></p>';
					}
					if(result[i].subList[j].mobileNo == null || result[i].subList[j].mobileNo.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].mobileNo+'</i></p>';
					}
					if(result[i].subList[j].locationVO.districtName == null || result[i].subList[j].locationVO.districtName.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].locationVO.districtName+'</i></p>';
					}  
				str+='</li>';
			}
		}
		str+='</ul>';
		
		$("#alertAssignedCandidates").html(str);
	}else{
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
		$("#alertAssignedCandidates").html(str);                    
	}
	
	$(".assignedCandidatesUl").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		  responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 5,
				slidesToScroll: 3,
				infinite: false,
				dots: false
			  }
			},
			{
			  breakpoint: 800,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 2
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 2,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			
		  ]
	});  
}
function getAlertStatusCommentsTrackingDetails(alertId,alertStatus){  
		var jsObj={
			alertId:alertId,
			task:""
		}
		$.ajax({  
			type : 'GET',
			url : 'getAlertStatusCommentsTrackingDetails.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null)           
				buildAlertStatusCommentsTrackingDetails(result,alertStatus);    
		});
	}
	
function buildAlertStatusCommentsTrackingDetails(result,alertStatus)
{
	alert("cmt");
	var docName = '';
	var extName = [];
	$("#alertStatusDiv").html("<h4 class='text-muted headingColorStyling' style='font-size:15px;'>ALERT STATUS</h4>");          
	if(result != null && result.length > 0){  
		var length = result.length;
		length = length - 1;
		var str='';  
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';  
			for(var i in result)
			{
			   if(result[i].currentSts == result[i].status)  
			   {  
					str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-hourglass pull-right" style="font-size: 22px;color: #777 !important;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
				}else{
					str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-ok pull-right" style="font-size: 22px;color: #777 !important;margin-left: 15px;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
				}        
			}
			str+='</ul>';
			str+='<div class="tab-content alertComment">';
				for(var i in result)
				{
				   if(result[i].currentSts == result[i].status)  
					{
						str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
					}else{
						str+='<div role="tabpanel" class="tab-pane " id="commentStatus'+i+'">';
					}
					for(var j in result[i].sublist2)
					{
						str+='<div class="row m_top10">';
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date      
								var dateArr = date.split("-");
								var year = dateArr[0];  
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';  
										str+='<div class="arrow_box_left" style="background: #f5f3f8 none repeat scroll 0 0 !important;">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE&nbsp;&nbsp;:&nbsp;</span>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span> <span>'+result[i].sublist2[j].sublist[k][l].cadreName+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;">|</span>';
												}   
												str+='&nbsp;&nbsp;&nbsp;&nbsp; <small style="font-size:11px">'+result[i].sublist2[j].sublist[k][0].timeString+'</small>';
												str+='</p>';  
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p class="m_top10">'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split("/");  
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">'+extName[1]+'</a></li>';
													}
													str+='</ul>';    
												}
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;border-color:#a792d2 -moz-use-text-color -moz-use-text-color;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					}           
				str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		$("#alertCommentsDiv").html(str);
	}else{
		var str = '';
		var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable To Resolve","6":"Action Not Required","7":"Duplicate"};            
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';
		for(var i = 1 ; i <= 7 ; i++){
			if(alertStatus == statusArr[i]){
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<span class="glyphicon glyphicon-ok"></span><br/></a></li>';
			}else{
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<br/></a></li>';
			}
		}
		str+='</ul>';       
		str+='<div class="tab-content alertComment">';    
		$("#alertCommentsDiv").html(str);       
	}//glyphicon glyphicon-ok
	//alertStatus
}
function getVerificationDtls(alertId){  
		var jsObj={
			alertId:alertId
		}
        $.ajax({
        type : 'POST',
        url : 'getAlertVerificationDetailsAction.action',
        dataType : 'json',
        data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#converSationDtlsDivId").html(' ');
			buildAlertVerificationStatusRlst(result);
		});
	}
	function buildAlertVerificationStatusRlst(result){
		var str = '';
		if(result.conversationList != null && result.conversationList.length > 0){
			$("#alertVerificationDiv").html("<h4 class='text-muted verifyHeadingColorStyling' style='font-size:15px;'>VERIFICATION STATUS-"+result.actionTypeStatus+"</h4>");  
			for(var i in result.conversationList){
				str+='<p class="text-capital panelTitleFont m_top20 verifyHeadingColorStyling" style="font-size:12px;">'+result.conversationList[i].heading+'</p>';  
				if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
					str+='<p style="border: 1px solid rgb(211, 211, 211); padding: 6px;">'+result.conversationList[i].comments+'</p>';     
				}
				var documentList = result.conversationList[i].documentList;
				if(documentList != null && documentList.length > 0){
					str+='<p style="font-weight:bold;font-size:12px;" class="text-capital m_top10 panelTitleFont headingColorStyling">Attachments</p>';
					str+='<ul class="attachmentsBlock">';
					var order = 0;
					for(var k in documentList){
						order = order+1;
						var fullName = documentList[k];
						var nameArr = fullName.split(".");
						var type = nameArr[1];  
						var orderStr='';
						if(k<9){
							orderStr ="0"+order;
						}else{
							orderStr = order;  
						}
						var attachment = orderStr+'&nbspAttachment.'+type;
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></li>';
					}
					str+='</ul>';
				}
				if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
					str+='<p class="text-right" style="color:#7155D6;font-size:12px;">Created By:'+result.conversationList[i].name+'('+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+')</p>';     
				}  
			}
			str+='<hr class="m_top10" style="border-top: 1px solid #ccc;">';
			$("#alertVerificationDtlsDiv").html(str);
		}
   }
   
	function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}

$(document).on("click",".articleImgDetailsCls",function(){
	alert("image");
	var articleId= $(this).attr("attr_articleId");
	getTotalArticledetails(articleId);
});
function getTotalArticledetails(articleId){
	
	$("#myModalShowNew").modal('show');
	$("#myModalShowNewId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var url = window.location.href;
	  var wurl = url.substr(0,(url.indexOf(".com")+4));
	  if(wurl.length == 3)
	  wurl = url.substr(0,(url.indexOf(".in")+3));
	  $.ajax({       
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  
		  //url: "http://mytdp.com/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""          
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""     
	}).then(function(results){
			var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
				var result = results[0];
				var str = '';
					str+='<div class="modal-header">';
					str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<button type="button" class="close topModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
						str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="modal-body">';
					str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
						str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;border:1px solid #ddd;width:100%" alt="Img Title"/>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="panel-title text-success">Description</h4>';
					str+='<p class="m_0 f_14">'+result.description+'</p>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if( result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							/* Candidate*/
							str+='<div class="row m_top10">';
							str+='<div class="col-md-6 col-xs-12 col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].fromList[j].candidateName;
										}
										if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
											candidataExist = true; 
											str+=' ('+result.subList[i].fromList[j].designation + ")";
										}
										if(!candidataExist){
											str+=' - ';
										}
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
											str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
										}else{ 
											str+='<p class="m_0">Impact Level : - </p>';	
										}
										if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
											str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
										}else{ 
											str+='<p class="m_0">Category : - </p>';	
										}
										if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
											str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
										}else{ 
											str+='<p class="m_0">News Activity : - </p>';	
										}
										if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
											str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
										}else{ 
											str+='<p class="m_0">News type : - </p>';	
										}
										if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
											if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
												str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
											}else{ 
												str+='<p class="m_0">News Related : - </p>';	
											}
											if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
												str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
											}else{ 
												str+='<p class="m_0">Priority : - </p>';	
											}
											if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
												str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
											}else{ 
												str+='<p class="m_0">Solution : - </p>';	
											}
										}
										str+='</td>';
										str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
							str+='</div>';//panel
							str+='</div>';//colmd6
							str+='<div class="col-md-6 col-xs-12  col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* TO Table*/
								if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
									for( var j in result.subList[i].toList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].toList[j].candidateName;
																			}
																			if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																				candidataExist = true; 
																				str+=' ('+result.subList[i].toList[j].designation + ")";
																			}
																			if(!candidataExist){
																				str+=' - ';
																			}
																		   str+='</td>';
																	str+='</tr>';
																	str+='<tr>';
																		str+='<td colspan="2">';
																		    
																			if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																			  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Impact Level : - </p>';	
																			}
																		
																		    if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																			  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Category : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																			  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Activity : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																			  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News type : - </p>';	
																			}
																			if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																				
																				if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																				  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																				}else{ 
																				  str+='<p class="m_0">News Related : - </p>';	
																				}
																				if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																				  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Priority : - </p>';	
																				}
																				if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																				  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Solution : - </p>';	
																				}
																			}
																		str+='</td>';
																	str+='</tr>';
																str+='</table>';
															}
														}
														
													str+='</div>';//panelbody
												str+='</div>';//panel
											str+='</div>';//colmd6
											
										str+='</div>';//row
								  }
								}
								
								str+='</div>';//colmd12
							str+='</div>';//row
								/* Article Scope Location */
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Impact Scope : </td>';
													if(result.impactScopeId!=null){
														str+='<td>'+obj[result.impactScopeId]+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Location : </td>';
													if(result.scopeLocation!=null){
														str+='<td>'+result.scopeLocation+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
											str+='</table>';       
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="row">';
							/*Lnking*/
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										     if( result.linkedList != null && result.linkedList.length > 1){
											str+='<div class="row">';
												for( var i in result.linkedList){
													if(result.linkedList[i].articleId !=articleId ){
														str+='<div class="col-md-4" style="margin-top:5px;">';
															str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Linked Articles Available </h5>";
											}
											
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';  
					$("#myModalShowNewId").html(str);
		});    
}

function buildGrivenceDetailsTable(result,group){
 $("#grivancHeadinId").html('<h3>Grievance Details</h3>');
 if(group=="day"){
	 $("#grivenaceModalHeedingId").html('<h4 class="modal-title" >'+result[0].name+'</h4><span>'+result[0].fromDateStr+'-'+result[0].toDateStr+'</span>');
 }else{
	 $("#grivenaceModalHeedingId").html('<h4 class="modal-title" >'+result[0].name+'</h4>');
 }
  
	var str='';
	
	str+='<table id="alertIdListTableId" class="table  table-bordered" cellspacing="0" width="100%">';
                    str+='<thead>';
                     str+='<tr>';
				     str+=' <th>ComplentId</th>';
					 str+=' <th>Date</th>';
				     str+=' <th>Location</th>';
				     str+=' <th>Title</th>';
					 str+=' <th>Related to</th>';
				     str+=' <th>Problem</th>';
					 str+='<th>Status</th>';
                     str+='</tr>';
                   str+=' </thead>';
                   str+='<tbody>';
				   
				   for(var i in result[0].alertCoreDashBoardVOs){
					 str+='<tr>';
					 str+='<td style="cursor:pointer;" class="getAlertDtls" attr_alert_id="'+result[0].alertCoreDashBoardVOs[i].id+'" attr_alert_status="'+result[0].alertCoreDashBoardVOs[i].status+'">'+result[0].alertCoreDashBoardVOs[i].id+'</td>';
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].createdDate+'</td>';
					 if(result[0].alertCoreDashBoardVOs[i].location != null && result[0].alertCoreDashBoardVOs[i].location.length >0){
						str+='<td>'+result[0].alertCoreDashBoardVOs[i].location+'</td>';
					 }else{
						 str+='<td>-</td>';
					 }
					 if(result[0].alertCoreDashBoardVOs[i].title != null &&result[0].alertCoreDashBoardVOs[i].title.length >0){
						str+='<td>'+result[0].alertCoreDashBoardVOs[i].title+'</td>';
					 }else{
						str+='<td>-</td>'; 
					 }
					  if(result[0].alertCoreDashBoardVOs[i].relatedTo != null &&result[0].alertCoreDashBoardVOs[i].relatedTo.length >0){
					   str+='<td>'+result[0].alertCoreDashBoardVOs[i].relatedTo+'</td>';
					  }else{
						  str+='<td>-</td>'; 
					  }
					   if(result[0].alertCoreDashBoardVOs[i].problem != null &&result[0].alertCoreDashBoardVOs[i].problem.length >0){
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].problem+'</td>';
					   }else{
						    str+='<td>-</td>'; 
					   }
					    if(result[0].alertCoreDashBoardVOs[i].status != null &&result[0].alertCoreDashBoardVOs[i].status.length >0){
					 str+='<td>'+result[0].alertCoreDashBoardVOs[i].status+'</td>';
						}else{
							    str+='<td>-</td>'; 
						}
					 str+='</tr>';
				    }
				   str+='</tbody>';    
				   str+='</table>';
				   
				   $("#grevinceDetailsId").html(str);
				   $("#alertIdListTableId").dataTable();
}
function buildTotalAlertDistrictTable(result){
     var str='';
	    str+='<table class=" table table-bordered">';
	    str+='<thead>';
        str+='<tr>'; 
		str+='<th>Total</th>';
	    for(var i in result[0].subList1){       
           str+='<th  style="background-color:#ecebd6;">'+result[0].subList1[i].statusType+'</th>';
		}
	        str+='<tr>';	 
	        str+='</thead>';	
			str+='<tbody>'
			str+='<tr>';
			str+='<td>'+result[0].totalAlertCnt+'</td>';
		for( var i in result[0].subList1){
		    str+='<td  class="getAlertDtlsOnLocCls" attr_status_id="'+result[0].subList1[i].statusTypeId+'" attr_location_id="'+result[0].id+'" style="background-color:#ecebd6;">'+result[0].subList1[i].totalAlertCnt+'</td>';
       }
			str+='<tr>'; 
	        str+='</tbody>'
			str+='</table>';
		 $("#totalAlertDistricTableId").html(str);
}