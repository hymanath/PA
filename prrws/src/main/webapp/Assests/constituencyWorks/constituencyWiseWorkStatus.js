	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	//var globalDeptArr={"dept":"Panchayati Raj & RD & RWS","id";"panchayatiRaj"}
	var glStartDate=moment().subtract(40,"years").startOf("year").format("DD/MM/YYYY");
	var glEndDate=moment().add(10,"years").startOf("year").format("DD/MM/YYYY");
	
	$(".chosenSelect").chosen();
	onloadCalls();
	function onloadCalls(){
		getAllSubLocationsOnsuperLocation("21");
		getAllFiniancialYears();
		getAllDepartmentsDetails();
	}
	
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
		
		setTimeout(function(){  onloadCalls(); }, 1000);
	  });


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
	 $("#constituencySelId").html("<option value='0'>SELECT CONSTITUENCY</option>");
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
		str+='<option value="0">SELECT DISTRICT</option>';
		for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>'
		}
     $("#districtSelId").html(str);
	 $("#districtSelId").chosen();
	 $("#districtSelId").trigger('chosen:updated');
    });
}
$(document).on("change","#districtSelId",function(){
	var constincyId=$(this).val();
	var conId=constincyId.substr(1,constincyId.length-1);
	getLocationsNamesBySubLocation(conId);
});
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
		str+='<option value="0">SELECT CONSTITUENCY</option>';
		for(var i in result){
			str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>'
		}
		 $("#constituencySelId").html(str);
		$("#constituencySelId").chosen();
		$("#constituencySelId").trigger('chosen:updated');
    });
}	
function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;
	 document.title = "";
     document.body.innerHTML = printContents;
     window.print();
     document.body.innerHTML = originalContents;
}
buildOverAllDepartmentsDetails();
function buildOverAllDepartmentsDetails(){
	
	var departmentId = $("#departmentSelId").val();
	var departmentArr=[];
	var str='';
	
	if(departmentId == 0){
		departmentArr=['Panchayati Raj Engineering Department','Rural Water Supply & Sanitation','Mahatma Gandhi National Rural Employment Gurantee Scheme']
		
	}else if(departmentId == 1){
		departmentArr=['Panchayati Raj Engineering Department']
	}else if(departmentId == 2){
		departmentArr=['Rural Water Supply & Sanitation']
	}else if(departmentId == 3){
		departmentArr=['Mahatma Gandhi National Rural Employment Gurantee Scheme']
	}
		str+='<div class="go-works">';
			str+='<h4>G.O.Issued Works Summery</h4>';
				str+='<div class="main_level_css m_top10">';
					str+='<div class="row">';
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls">';
								str+='<h4 class="font_weight">Total Works</h4>';
								str+='<h3 class="font_weight">15,000</h3>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls">';
								str+='<h4 class="font_weight">Total Works</h4>';
								str+='<h3 class="font_weight">15,000</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="">';
					for(var i in departmentArr){
							str+='<div class="border_cls_low_level">';
								str+='<h4 class="font_weight">'+departmentArr[i]+'</h4>';
								str+='<div class="row m_top10">';
									str+='<div class="col-sm-6">';
										str+='<div class="department_wise_css">';
											str+='<div class="row">';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">Works</h4>';
													str+='<h3 class="font_weight">1023130</h3>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">PLANE</h4>';
													str+='<h4 class="font_weight">1120</h4>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">SCP</h4>';
													str+='<h4 class="font_weight">233</h4>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">TSP</h4>';
													str+='<h4 class="font_weight">212</h4>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='<div class="department_wise_css">';
											str+='<div class="row">';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">Works</h4>';
													str+='<h3 class="font_weight">1023130</h3>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">PLANE</h4>';
													str+='<h4 class="font_weight">1120</h4>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">SCP</h4>';
													str+='<h4 class="font_weight">233</h4>';
												str+='</div>';
												str+='<div class="col-sm-3">';
													str+='<h4 class="font_weight">TSP</h4>';
													str+='<h4 class="font_weight">212</h4>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';	
							str+='</div>';
						
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
	
	
	$("#overAllDeparmentsDivId").html(str);
}

