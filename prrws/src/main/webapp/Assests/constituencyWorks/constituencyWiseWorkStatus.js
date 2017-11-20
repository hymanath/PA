	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	//var globalDeptArr={"dept":"Panchayati Raj & RD & RWS","id";"panchayatiRaj"}
	var glStartDate=moment().subtract(40,"years").startOf("year").format("DD/MM/YYYY");
	var glEndDate=moment().add(10,"years").startOf("year").format("DD/MM/YYYY");
	var blockNames = ['ENC','RWS'];
	var deptIds = [1,2];
	var financialArrGlob=[];
	financialArrGlob.push("0");
	 $("#dateRangePickerAUM").daterangepicker({
      opens: 'left',
      startDate: glStartDate,
      endDate: glEndDate,
    locale: {
      format: 'DD/MM/YYYY'
    },
    ranges: {
        'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
        'Today' : [moment(), moment()],
		'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
        'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
        'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
        'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
        'This Month': [moment().startOf('month'), moment()],
        'This Year': [moment().startOf('Year'), moment()]
    }
  });
 var dates= $("#dateRangePickerAUM").val();
    var pickerDates = glStartDate+' - '+glEndDate
  if(dates == pickerDates)
  {
    $("#dateRangePickerAUM").val('All');
  }
  $('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
    glStartDate = picker.startDate.format("DD/MM/YYYY")
    glEndDate = picker.endDate.format("DD/MM/YYYY")
    if(picker.chosenLabel == 'All')
    {
      $("#dateRangePickerAUM").val('All');
    }
	$("#financialYearId").val(0);
	$("#financialYearId").trigger('chosen:updated');
	
	setTimeout(function(){  onlyDataBlocks(); }, 1000);
  });
$(".chosenSelect").chosen();
onloadCalls();
initializeOnChange();
function onloadCalls(){
	getAllSubLocationsOnsuperLocation("21");
	getAllFiniancialYears();
	getAllDepartmentsDetails();
	collapseBlock();
	var financialYrIds = $('#financialYearId').val();
	var financialYrIdArr =[];
	for(var i in financialYrIds){
		financialYrIdArr.push(financialYrIds[i]);
	}
	var fromDate = glStartDate;
	var toDate = glEndDate;
	
	var locationId = $("#constituencySelId").val();
	if(locationId==0){
		locationId = $("#districtSelId").val();
	}
	
	for(var i in deptIds){
		getFundManagementSystemWorkDetails(deptIds[i],locationId,financialYrIdArr,fromDate,toDate);
	}
}
function  getAllDepartmentsDetails(){
	
	 $.ajax({ 
      url: 'getDepartmentDetails', 
      type:'POST',  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },   
    }).done(function(result){
		var str='';
		str+='<option value="0" selected>ALL DEPARTMENTS</option>';
		for(var i in result){
			str+='<option value="'+result[i].locationId+'">'+result[i].departmentName+'</option>'
		}
		$("#departmentSelId").html(str);
		$("#departmentSelId").chosen();
		$("#departmentSelId").trigger('chosen:updated');
    });
 }

function onlyDataBlocks(){
	var financialYrIds = $('#financialYearId').val();
	var financialYrIdArr =[];
	for(var i in financialYrIds){
		financialYrIdArr.push(financialYrIds[i]);
	}
	var fromDate = glStartDate;
	var toDate = glEndDate;
	
	var locationId = $("#constituencySelId").val();
	if(locationId==0){
		locationId = $("#districtSelId").val();
	}
	for(var i in deptIds){
		getFundManagementSystemWorkDetails(deptIds[i],locationId,financialYrIdArr,fromDate,toDate);
	}
}
function getFundManagementSystemWorkDetails(deptId,locationId,financialYrIdArr,fromDate,toDate){
	var json = {
			locationId:locationId,     
			departmentId:deptId,
			financialYrIdList:financialYrIdArr,
			fromDateStr:fromDate,
			toDateStr:toDate
       }
    $.ajax({ 
		url: 'getFundManagementSystemWorkDetails', 
		type:'POST',  
		data : JSON.stringify(json),
		dataTypa : 'json',   
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},   
    }).done(function(result){
		buildWorkDeatilsLocation(blockNames[deptId-1],result);   
		buildWorkDeatils(blockNames[deptId-1],result);
		buildOverAllDetails(blockNames[deptId-1],result);
    });
}

function  getAllFiniancialYears(){
	
	 $.ajax({ 
      url: 'getAllFiniancialYears', 
      type:'POST',  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },   
    }).done(function(result){
		var str='';
		str+='<option value="0" selected>ALL</option>';
		for(var i in result){
			str+='<option value="'+result[i].financialYearId+'">'+result[i].financialYear+'</option>'
		}
		$("#financialYearId").html(str);
		$("#financialYearId").chosen();
		$("#financialYearId").trigger('chosen:updated');
    });
 }
function getAllSubLocationsOnsuperLocation(superLocationId){
	 $("#constituencySelId").html("<option value='0'>ALL CONSTITUENCY</option>");
	 $("#constituencySelId").trigger('chosen:updated');
	 var json = {
          superLocationId:superLocationId
        }
    $.ajax({ 
      url: 'getAllSubLocationsOnsuperLocation', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
		var str='';
		str+='<option value="0">ALL DISTRICT</option>';
		for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>'
		}
     $("#districtSelId").html(str);
	$("#districtSelId").chosen();
	 $("#districtSelId").trigger('chosen:updated');
    });
}





function initializeOnChange(){
	$(document).on("change","#districtSelId",function(){
		var constincyId=$(this).val();
		var conId=constincyId.substr(1,constincyId.length-1);
		var locationId = $("#districtSelId").val();
		getLocationsNamesBySubLocation(conId);
		departmentWiseBlocks(locationId);
	});
	$(document).on("change","#constituencySelId",function(){
		var constincyId=$(this).val();
		if(constincyId != null && constincyId !=0 ){
			$("#leaderNameId").show();
			getDistrictNameAndLeaderName(constincyId);
			departmentWiseBlocks(constincyId);  
		} else{
			var locationId = $("#districtSelId").val();//for all constituency take districtId
			departmentWiseBlocks(locationId);    
		   $("#leaderNameId").hide();
		}
	});
	$(document).on("change","#financialYearId",function(){
		var values = $(this).val();
		if(values != null && values.length > 0){
			for(var i=0; i<values.length; i++) {
				if($.inArray(values[i], financialArrGlob) == -1){
					if(values[i] == 0){
						values=[];
						values.push("0");
						$('#financialYearId').find($('option')).attr('selected',false)
						$("#financialYearId").val(0);
						$("#financialYearId").trigger('chosen:updated');
						financialArrGlob = [];
						financialArrGlob.push("0");
					}else{
						$('#financialYearId option:selected').each(function (index, option) { 
							if($(this).val()==0){
								$(option).attr('selected',false); 
								$("#financialYearId").trigger('chosen:updated');
							}
							financialArrGlob=[];
							financialArrGlob.push($(this).val());
						});
					}
				}
			}
		}
		$('#financialYearId option:selected').each(function (index, option) { 
			financialArrGlob=[];
			financialArrGlob.push($(this).val());
		});
		
		var financialYrIds = $('#financialYearId').val();
		var financialYrIdArr =[];
		for(var i in financialYrIds){
			financialYrIdArr.push(financialYrIds[i]);
		}
		alert(financialYrIdArr);
		var fromDate = glStartDate;
		var toDate = glEndDate;
		
		var locationId = $("#constituencySelId").val();
		if(locationId==0){
			locationId = $("#districtSelId").val();
		}
		for(var i in deptIds){
			getFundManagementSystemWorkDetails(deptIds[i],locationId,financialYrIdArr,fromDate,toDate);
		}
	});
	
}


function departmentWiseBlocks(locationId){
	
	var financialYrIds = $('#financialYearId').val();
	var financialYrIdArr =[];
	for(var i in financialYrIds){
		financialYrIdArr.push(financialYrIds[i]);
	}
	var fromDate = glStartDate;
	var toDate = glEndDate;
	for(var i in deptIds){    
		getFundManagementSystemWorkDetails(deptIds[i],locationId,financialYrIdArr,fromDate,toDate);
	}
}
function getLocationsNamesBySubLocation(locationId){  
	 var json = {
          locationId:locationId
        }
    $.ajax({ 
      url: 'getLocationsNamesBySubLocation', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },   
    }).done(function(result){
		var str='';
		str+='<option value="0">ALL CONSTITUENCY</option>';
		for(var i in result){
			str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>'
		}
		 $("#constituencySelId").html(str);
		$("#constituencySelId").chosen();
		$("#constituencySelId").trigger('chosen:updated');
    });
}	

	function getDistrictNameAndLeaderName(locationId){
       $("#mlaName").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
      $("#districtName").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
      $("#constituencyName").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>'); 		
	   var json = {
	         locationId:locationId
	       }
	   $.ajax({ 
	     url: 'getDistrictNameAndMlaNameByConsitutency', 
	     type:'POST',  
	     data : JSON.stringify(json),
	     dataTypa : 'json',   
	     beforeSend: function(xhr) {
	       xhr.setRequestHeader("Accept", "application/json");
	       xhr.setRequestHeader("Content-Type", "application/json");
	     },
	           
	   }).done(function(result){
	     if(result!= null){
			$("#mlaName").html(" ");
			$("#districtName").html(" ");
			$("#constituencyName").html(" ");
			$("#mlaName").append("<h5>"+result[0].mlaName+"</h5>");
			$("#districtName").append("<h5>"+result[0].locationName+"</h5>");
			$("#constituencyName").append("<h5>"+result[0].workName+"</h5>");
	   }else{
			$("#districtName").html('District');
			$("#mlaName").html('MLA Name');
	        $("#constituencyName").html('Constituency Name');
	   }
	   });
	}

function collapseBlock(){
	var collapse='';
	if(blockNames != null){
	collapse+='<div class="row">';
		collapse+='<div class="col-sm-12">'
		collapse+='<div class="row m_top20"><div class="col-sm-1 col-sm-offset-11"><button class="btn btn-md btn-success" id="btnPrint">PRINT</button></div></div>';
			for(var i in blockNames){
				collapse+='<div class="panel-group " id="accordion'+blockNames[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black m_top20">';
					collapse+='<div class="panel-heading" role="tab" id="headingOne'+blockNames[i]+'">';
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+blockNames[i]+'" href="#collapseOne'+blockNames[i]+'" aria-expanded="true" aria-controls="collapseOne'+blockNames[i]+'">';
							collapse+='<h4 class="panel-title">'+blockNames[i]+' DEPARTMENT - OVERVIEW</h4>';
						collapse+='</a>';
					collapse+='</div>';
					collapse+='<div id="collapseOne'+blockNames[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+blockNames[i]+'">';
						collapse+='<div class="panel-body">';
							collapse+='<div class="" >';
								collapse+='<div class="row m_left20" style="background-color:lightgrey;">';
									collapse+='<div class="col-sm-3 m_left20"><p>Total Govt Order:<span id="overallGo'+blockNames[i]+'"></span></p></div>';
									collapse+='<div class="col-sm-3"><p>Total Work :<span id="overallWork'+blockNames[i]+'"></span></p></div>';
									collapse+='<div class="col-sm-3"><p>Total Amount :<span id="overallAmount'+blockNames[i]+'"></span></p></div>';
								collapse+='</div>';
							collapse+='</div>';
							collapse+='<div class="m_top20" id="'+blockNames[i]+'DivId">';
							collapse+='</div>';
							collapse+='<div class="m_top20" id="'+blockNames[i]+'TableDivId">';
						collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
			collapse+='</div>'
				
			}
		collapse+='</div>'
	collapse+='</div>';
	}
	$("#deptBlocks").html(collapse);
}

function buildWorkDeatils(divId,result){
	
	var tab='';
		tab+='<h3 class="panel-title m_left20" style="background-color:lightgrey;padding:5px;">Govt Order Wise Works Details</h3>';
		tab+='<div class="table-responsive m_top20">';
			tab+='<table class="table table-bordered">';
				tab+='<tr>';
					tab+='<th> Govt Order </th>';
					tab+='<th> Total Works</th>';
					tab+='<th> Issue Date</th>';
					tab+='<th> Amount</th>';
				tab+='</tr>';
				for(var i in result.locationList1){
					tab+='<tr>';
						tab+='<td>'+result.locationList1[i].goNoDate+'</td>';
						tab+='<td>'+result.locationList1[i].workNumber+'</td>';
						tab+='<td>'+result.locationList1[i].issueDate+'</td>';
						tab+='<td>'+result.locationList1[i].amountInDecimal+'</td>';
					tab+='</tr>';
				}
			tab+='</table>';
		tab+='</div>';
		$("#"+divId+"DivId").html(tab);
}

function buildWorkDeatilsLocation(divId,result){
	var tab='';
		tab+='<div class="row">';    
			tab+='<div class="col-sm-2 m_left20" style="background-color:lightgrey;padding:5px;margin-left:15px;">';
				tab+='<h3 class="panel-title m_left20" style="background-color:lightgrey;"> Works Details</h3>';
			tab+='</div>';         
		tab+='</div>';
		tab+='<div class="table-responsive m_top20">';
			tab+='<table class="table table-bordered">';
				tab+='<tr>';
					tab+='<th> Govt Order </th>';
					tab+='<th> Work Name</th>';
					tab+='<th> Mandal</th>';
					tab+='<th> village</th>';
					tab+='<th> Amount</th>';
				tab+='</tr>';
				for(var i in result.locationList2){
					tab+='<tr>';
						tab+='<td>'+result.locationList2[i].goNoDate+'</td>';
						tab+='<td>'+result.locationList2[i].workName+'</td>';
						tab+='<td>'+result.locationList2[i].tehsilName+'</td>';
						tab+='<td>'+result.locationList2[i].panchayatName+'</td>';
						tab+='<td>'+result.locationList2[i].amountInDecimal+'</td>';
					tab+='</tr>';
				}
			tab+='</table>';
		tab+='</div>';
		$("#"+divId+"TableDivId").html(tab);
}
function buildOverAllDetails(divId,result){
	$("#overallGo"+divId).html(result.govtOrderCount);
	$("#overallWork"+divId).html(result.workNumber);
	$("#overallAmount"+divId).html(result.amountInDecimal);
}