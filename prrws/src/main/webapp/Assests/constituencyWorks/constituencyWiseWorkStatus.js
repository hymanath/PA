	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	//var globalDeptArr={"dept":"Panchayati Raj & RD & RWS","id";"panchayatiRaj"}
	var glStartDate=moment().subtract(40,"years").startOf("year").format("DD/MM/YYYY");
	var glEndDate=moment().add(10,"years").startOf("year").format("DD/MM/YYYY");
	
	var departmentArrGlob =[];
	departmentArrGlob.push("0");
	var departmentArrGlob1 =[];
	departmentArrGlob1.push("3");
	var globalAllPdfFilesArr=[];
	var globalAllPdfFilesArr1=[];
	$(".chosenSelect").chosen();
	
	getAllFiniancialYears();
	
	function onloadCalls(){
		getFundManagementSystemWorkDetails(0,"")
		getFundManagementSystemWorkDetails(1,"enc")
		getFundManagementSystemWorkDetails(2,"rws")
		getConstituencyWiseNregsWorksDetails(3,"mgnrews")
		$("#headingId").html("<b class='font_size24'>Minister for Panchayathi Raj &amp; Rural Development, Information Technology.</b>")
		
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
	/* $(document).on('cut copy paste', function (e) {
		e.preventDefault();
	});*/
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
		
		$("#DepartmentsId").val(0);
		$("#DepartmentsId").trigger('chosen:updated');
		
		var constincyId=$("#districtSelId").val();
		var conId=constincyId.substr(1,constincyId.length-1);
		getLocationsNamesBySubLocation(conId,"onload");
		
		
	  });



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
		getAllSubLocationsOnsuperLocation("21");
		
		
 }
 
	var financialArrGlob=[];
	financialArrGlob.push("0");
	$(document).on("change","#financialYearId",function(){//ara
		var values = $(this).val();//debugger;
		if(values != null && values.length > 0){
			for(var i=0; i<values.length; i++) {
			//console.log(values[i]+" -- "+financialArrGlob+" -- "+$.inArray(values[i], financialArrGlob));
				if($.inArray(values[i], financialArrGlob) == -1){
					if(values[i] == 0){values=[];values.push("0");
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
			 onLoadInitialisations();
		}console.log(financialArrGlob +" ---- "+ values);
	});
	
function getAllSubLocationsOnsuperLocation(superLocationId){
	 $("#districtSelId").html("");
	 
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
				if(i==0){
					str+='<option value="'+result[i].id+'" selected>'+result[i].name+'</option>'
				}else{
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>'
				}
				
			}
		 $("#districtSelId").html(str);
		 $("#districtSelId").chosen();
		 $("#districtSelId").trigger('chosen:updated');
	});
	setTimeout(function(){
		getLocationsNamesBySubLocation(22,"onload");
		/* var constincyId=$("#districtSelId").val();
		alert(constincyId)
		var conId=constincyId.substr(1,constincyId.length-1);
		alert(conId)
		getLocationsNamesBySubLocation(22,"onload"); */
	}, 2000);
	
}
$(document).on("change","#districtSelId",function(){
	var constincyId=$(this).val();
	var conId=constincyId.substr(1,constincyId.length-1);
	getLocationsNamesBySubLocation(conId,"change");
});
function getLocationsNamesBySubLocation(locationId,type){  
 $("#constituencySelId").html('');
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
			if(i==0){
				str+='<option value="'+result[i].locationId+'" selected>'+result[i].locationName+'</option>'
			}else{
				str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>'
			}
			
		}
		 $("#constituencySelId").html(str);
		$("#constituencySelId").chosen();
		$("#constituencySelId").trigger('chosen:updated');
    });
	
	
	if(type == "onload"){
		 setTimeout(function(){
			$("#mainHeadingId").html("District : "+ $("#districtSelId option:selected").text()+",Constituency : "+$("#constituencySelId option:selected").text()+"")
			$(".waitingMsgCls").hide();
			onloadCalls(); 
		}, 3500);
	}
	
		
}
$(document).on("click",".withAndOutHeaderCls",function(){
	var divName = $(this).val();
		if(divName == "withOutHeader"){
			$(".headingCssCls").removeClass("withHeaderCls")
			$(".headingCssCls").addClass("withOutHeaderCls")
			
		}else{
			$(".headingCssCls").removeClass("withOutHeaderCls")
			$(".headingCssCls").addClass("withHeaderCls")
			
		}
});

$(document).on("click",".printViewCls",function(){
	var divName = $(this).attr("attr_divId");
	var departmentId = $("#DepartmentsId").val();
	
	var totalWorks = 0;
	var totalFunds = 0.00;
	
	if(departmentId == 0){
		$("#overAllDeparmentsDivId").css("display","block");
		
	}else if(departmentId == 1){
		$("#overAllDeparmentsDivId").css("display","none");
		//$(".pageBreakAddRemove").addClass("pagebreak")
		$("#rwsDetailsDivId").css("display","none");
		$("#mgnrewsDetailsDivId").css("display","none");
	}else if(departmentId == 2){
		$("#overAllDeparmentsDivId").css("display","none");
		//$(".pageBreakAddRemove").addClass("pagebreak")
		$("#encDetailsDivId").css("display","none");
		$("#mgnrewsDetailsDivId").css("display","none");
	}else if(departmentId == 3){
		$("#overAllDeparmentsDivId").css("display","none");
		//$(".pageBreakAddRemove").addClass("pagebreak")
		$("#encDetailsDivId").css("display","none");
		$("#rwsDetailsDivId").css("display","none");
	}
	printDiv(divName);
	/* var headerVal ='';
		$(".withAndOutHeaderCls").each(function(i, obj){
			 if($(this).is(":checked")){
				  headerVal = $(this).val();
			 }
		});
		if(headerVal == "withOutHeader"){
			$(".headingCssCls").removeClass("withHeaderCls")
			$(".headingCssCls").addClass("withOutHeaderCls")
			printDiv(divName)
		}else{
			$(".headingCssCls").removeClass("withOutHeaderCls")
			$(".headingCssCls").addClass("withHeaderCls")
			printDiv(divName)
		} */
});
 function printDiv(divName) {
	
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.getElementById("printcontent").innerHTML;
	 document.title = "";
     document.getElementById("printcontent").innerHTML = printContents;
	 window.print();
     document.getElementById("printcontent").innerHTML = originalContents;
	 $(".headingCssCls").addClass("withOutHeaderCls")
	//window.location.reload(true);
} 
	/* window.onload=function(e){
		  //alert();
		window.onafterprint = function() {
			//window.location.reload(false);
		   location.reload();
		};
		window.matchMedia('print').addListener(function (media) {
		//do before-printing stuff
		  if(media.matches){
			
		  }
		  else{
			  media.preventDefault();
			  window.location.reload(true);
			 //location.reload();
		  }
		});
	} */

function buildOverAllDepartmentsDetails(result,departmentId,divId){
	
	var departmentId = $("#DepartmentsId").val();
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	var departmentArr=[];
	var str='';
	//var str1='';
	var totalWorks = 0;
	var totalFunds = 0.00;
	
	if(departmentId == 0){
		departmentArr=['Panchayati Raj Engineering Department','Rural Water Supply & Sanitation','Mahatma Gandhi National Rural Employment Gurantee Scheme']
		
	}else if(departmentId == 1){
		departmentArr=['Panchayati Raj Engineering Department']
	}else if(departmentId == 2){
		departmentArr=['Rural Water Supply & Sanitation']
	}else if(departmentId == 3){
		departmentArr=['Mahatma Gandhi National Rural Employment Gurantee Scheme']
	}
	if(result != null && result.locationList1 != null && result.locationList1.length > 0){
		for(var i in result.locationList1){
			totalWorks = totalWorks+result.locationList1[i].workNumber;
			totalFunds = parseFloat(totalFunds)+parseFloat(result.locationList1[i].amountInDecimal);
		}
	}
		
		
		str+='<div class="go-works m_top20">';
			if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
				str+='<h3 class="font_size">G.O.Issued Works Summary - <span class="f_16">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span></span></h3>';
			}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
				str+='<h3 class="font_size">G.O.Issued Works Summary - <span class="f_16">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span> Constituency : <span class="font_weight text-capital">'+$("#constituencySelId option:selected").text()+'</span></span></h3>';
			}
				str+='<div class="main_level_css m_top10">';
					str+='<div class="row">';
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls">';
								str+='<h4 class="font_weight">Total Works</h4>';
								str+='<h3 class="font_weight">'+totalWorks+'</h3>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls">';
								str+='<h4 class="font_weight">Total Funds</h4>';
								str+='<h3 class="font_weight">'+totalFunds.toFixed(3)+' <span style="color: rgb(0, 0, 0); font-size: 14px ! important;font-weight:bold;"> (Cr)</span></h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="">';
					for(var i in result.locationList1){
						if(result.locationList1[i].workNumber > 0){
								str+='<div class="border_cls_low_level">';
								if(result.locationList1[i].departmentName == 'ENC')
									str+='<h4 class="font_weight">Panchayati Raj Engineering Department</h4>';
								else if(result.locationList1[i].departmentName == 'RWS')
									str+='<h4 class="font_weight">Rural Water Supply & Sanitation</h4>';
								else if(result.locationList1[i].departmentName == 'Mahatma Gandhi National Rural Employment Gurantee Scheme')
									str+='<h4 class="font_weight">Mahatma Gandhi National Rural Employment Gurantee Scheme</h4>';
									str+='<div class="row m_top10">';
										str+='<div class="col-sm-6">';
											str+='<div class="department_wise_css">';
												str+='<div class="row">';
													str+='<div class="col-sm-3">';
														str+='<h4 class="font_weight">Works</h4>';
														str+='<h3 class="font_weight">'+result.locationList1[i].workNumber+'</h3>';
													str+='</div>';
													if(result.locationList1[i].departmentName == 'ENC' || result.locationList1[i].departmentName == 'RWS'){
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">PLAIN</h4>';
															str+='<h4 class="font_weight">'+result.locationList1[i].plainWorkCount+'</h4>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">SCP</h4>';
															str+='<h4 class="font_weight">'+result.locationList1[i].scpWorkCount+'</h4>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">TSP</h4>';
															str+='<h4 class="font_weight">'+result.locationList1[i].tspWorkCount+'</h4>';
														str+='</div>';
													}
												str+='</div>';
											str+='</div>';
										str+='</div>';
										str+='<div class="col-sm-6">';
											str+='<div class="department_wise_css">';
												str+='<div class="row">';
														if(result.locationList1[i].departmentName == 'Mahatma Gandhi National Rural Employment Gurantee Scheme'){
															str+='<div class="col-sm-4">';
															str+='<h4 class="font_weight">Total</h4>';
														}
														else{
															str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">Funds</h4>';
														}
														str+='<h3 class="font_weight" style="font-size: 21px !important">'+parseFloat(result.locationList1[i].amountInDecimal).toFixed(2)+' <span style="color: rgb(0, 0, 0); font-size: 12px ! important;font-weight:bold;"> (Cr)</span></h3>';
													str+='</div>';
													if(result.locationList1[i].departmentName == 'ENC' || result.locationList1[i].departmentName == 'RWS'){
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">PLAIN</h4>';
															str+='<h4 class="font_weight">'+parseFloat(result.locationList1[i].plainAmountInDecimal).toFixed(2)+' <small style="color: rgb(0, 0, 0);font-size:12px;font-weight:bold;">(Cr)</small></h4>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">SCP</h4>';
															str+='<h4 class="font_weight">'+parseFloat(result.locationList1[i].scpAmountInDecimal).toFixed(2)+' <small style="color: rgb(0, 0, 0);font-size:12px;font-weight:bold;">(Cr)</small></h4>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h4 class="font_weight">TSP</h4>';
															str+='<h4 class="font_weight">'+parseFloat(result.locationList1[i].tspAmountInDecimal).toFixed(2)+' <small style="color: rgb(0, 0, 0);font-size:12px;font-weight:bold;">(Cr)</small></h4>';
														str+='</div>';
													}else if(result.locationList1[i].departmentName == 'Mahatma Gandhi National Rural Employment Gurantee Scheme'){
														str+='<div class="col-sm-4">';
															str+='<h4 class="font_weight">Wage</h4>';
															str+='<h4 class="font_weight">'+parseFloat(result.locationList1[i].wage).toFixed(2)+' <small style="color: rgb(0, 0, 0);font-size:12px;font-weight:bold;">(Cr)</small></h4>';
														str+='</div>';
														str+='<div class="col-sm-4">';
															str+='<h4 class="font_weight">Material</h4>';
															str+='<h4 class="font_weight">'+parseFloat(result.locationList1[i].material).toFixed(2)+' <small style="color: rgb(0, 0, 0);font-size:12px;font-weight:bold;">(Cr)</small></h4>';
														str+='</div>';
													}
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';	
								str+='</div>';
							}
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
	
	
	$("#overAllDeparmentsDivId").html(str);
	//getDistrictNameAndMlaNameByConsitutency();
	
}
$(document).on("click",".submitCls",function(){
	
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	var departmentId = $("#DepartmentsId").val();
	//getDistrictNameAndMlaNameByConsitutency();
	if(districtId == null || districtId == 0){
		alert("Please Select District")
		return;
	}
	
	if(constituencyId == null || constituencyId == 0){
		alert("Please Select  Constituency")
		return;
	}
	
	if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
		$("#mainHeadingId").html("District : "+ $("#districtSelId option:selected").text()+"")
		
	}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
		$("#mainHeadingId").html("District : "+ $("#districtSelId option:selected").text()+",Constituency : "+$("#constituencySelId option:selected").text()+"")
		
	}
	globalAllPdfFilesArr=[];
	globalAllPdfFilesArr1=[];
	//var headerType = ''; 
	$(".headingCssCls").addClass("withOutHeaderCls")
	/* $('.withAndOutHeaderCls').each(function(i, obj){
		 if($(this).is(':checked')){
		  headerType =  $(this).val();
		 }
	});
	if(headerType == "withOutHeader"){
		$(".headingCssCls").removeClass("withHeaderCls")
		$(".headingCssCls").addClass("withOutHeaderCls")
		
	}else{
		$(".headingCssCls").removeClass("withOutHeaderCls")
		$(".headingCssCls").addClass("withHeaderCls")
		
	} */
	if(departmentId == 3){
		if($(".footer").hasClass("withOutHeaderCls")){
			$("#mgnrewsDetailsDivId").removeClass("pagebreak")
		}else{
			$("#mgnrewsDetailsDivId").addClass("pagebreak")
		}
		
	}else{
		$("#mgnrewsDetailsDivId").removeClass("pagebreak")
	}
	//getDistrictNameAndMlaNameByConsitutency();
	if(departmentId ==0 || departmentId == null){
		$("#overAllDeparmentsDivId").css("display","block");
		
		$("#mgnrewsDetailsDivId").removeClass("pagebreak")
		$("#rwsDetailsDivId").removeClass("pagebreak")
		$("#encDetailsDivId").removeClass("pagebreak")
		
		$("#rwsDetailsDivId").addClass("pagebreak")
		$("#encDetailsDivId").addClass("pagebreak")
		
		if($(".footer").hasClass("withOutHeaderCls")){
			$("#mgnrewsDetailsDivId").removeClass("pagebreak")
		}else{
			$("#mgnrewsDetailsDivId").addClass("pagebreak")
		}
		
		
		$("#headingId").html("")
		$("#headingId").html("<b class='font_size24'>Minister for Panchayathi Raj &amp; Rural Development, Information Technology.</b>")
		getFundManagementSystemWorkDetails(0,"");
		getFundManagementSystemWorkDetails(1,"enc")
		getFundManagementSystemWorkDetails(2,"rws")
		getConstituencyWiseNregsWorksDetails(3,"mgnrews")
	}else{
		if(departmentId == 1){
			
			$("#encDetailsDivId").removeClass("pagebreak")
			$("#headingId").html("")
			$("#headingId").html("<b class='font_size24'>Information Technology.</b>")
			
			$("#overAllDeparmentsDivId").html('');
			$("#overAllDeparmentsDivId").css("display","none");
			
			$("#rwsDetailsDivId").html('');
			$("#rwsDetailsDivId").css("display","none");
			
			$("#encDetailsDivId").html('');	
			
			$("#mgnrewsDetailsDivId").html('');	
			$("#mgnrewsDetailsDivId").css("display","none");
			
			getFundManagementSystemWorkDetails(departmentId,"enc");
		}else if(departmentId == 2){
			
			$("#rwsDetailsDivId").removeClass("pagebreak")
			$("#headingId").html("")
			$("#headingId").html("<b class='font_size24'>Rural Development.</b>")
			$("#overAllDeparmentsDivId").html('');
			$("#overAllDeparmentsDivId").css("display","none");
			
			$("#rwsDetailsDivId").html('');
			
			$("#encDetailsDivId").html('');		
			$("#encDetailsDivId").css("display","none");
			
			$("#mgnrewsDetailsDivId").html('');
			$("#mgnrewsDetailsDivId").css("display","none");
			getFundManagementSystemWorkDetails(departmentId,"rws");
		}else if(departmentId == 3){
			
			$("#mgnrewsDetailsDivId").removeClass("pagebreak")
			$("#headingId").html("")
			$("#headingId").html("<b class='font_size24'>Minister for Panchayathi Raj.</b>")
			
			$("#overAllDeparmentsDivId").html('');
			$("#overAllDeparmentsDivId").css("display","none");
			
			$("#rwsDetailsDivId").html('');
			$("#rwsDetailsDivId").css("display","none");
			
			$("#encDetailsDivId").html('');	
			$("#encDetailsDivId").css("display","none");
			
			$("#mgnrewsDetailsDivId").html('');	
			getConstituencyWiseNregsWorksDetails(3,"mgnrews")
		}
		
	}
	
});
	
function getFundManagementSystemWorkDetails(departmentId,divId){
	$("#"+divId+"DetailsDivId").css("display","block");
	if(departmentId ==0 || departmentId == null){
		$("#overAllDeparmentsDivId").html(spinner);
	}
	
	$("#"+divId+"DetailsDivId").html(spinner);
	
	var financialYrIdList = $('#financialYearId').val();
	if(financialYrIdList == 0 || financialYrIdList == null){
		financialYrIdList=[];
	}
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	
	var locationId='';
	if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
		
		locationId =districtId;
	}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
		
		locationId =constituencyId;
	}
	
	var deptIdsList=[];
	deptIdsList.push(parseInt(departmentId))
	
	
	var json = {
			locationId:locationId, //districtId,cons    
			deptIdsList:deptIdsList, //ENC-1,Rws-2,M-3
			financialYrIdList:financialYrIdList,
			fromDateStr:glStartDate,
			toDateStr:glEndDate
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
		if(result !=null){
			if(departmentId == 0)
				buildOverAllDepartmentsDetails(result,departmentId,divId);
			else
				buildFundManagementSystemWorkDetails(result,divId);
		}else{
			if(departmentId == 0){
				$("#overAllDeparmentsDivId").html("No Data");
			}
			else{
				$("#"+divId+"DetailsDivId").html("No Data");
			}
		}
    });
}	
	function buildFundManagementSystemWorkDetails(result,divId,departmentId){
		var districtId = $("#districtSelId").val();
		var constituencyId = $("#constituencySelId").val();
		var length = $("#DepartmentsId").val();
		if(result.workNumber > 0){
			var str='';
			str+='<div class="go-works m_top20">';
					if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
						if(divId == "rws"){
							if(length == 2){
								str+='<span class="f_16 m_top20">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span></span>';
							}
							str+='<h3 class="font_size">Rural Water Supply & Sanitation Overview</h3>';
							
						}else{
							if(length == 1){
								str+='<span class="f_16 m_top10">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span></span>';
							}
							
							str+='<h3 class="font_size m_top20">Panchayati Raj Engineering Department Overview</h3>';
							
						}
						
					}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
						if(divId == "rws"){
							if(length == 2){
								str+='<span class="f_16 m_top20">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span> Constituency : <span class="font_weight text-capital">'+$("#constituencySelId option:selected").text()+'</span></span>';
							}
							str+='<h3 class="font_size">Rural Water Supply & Sanitation Overview</h3>';
							
						}else{
							if(length == 1){
								str+='<span class="f_16 m_top20">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span> Constituency : <span class="font_weight text-capital">'+$("#constituencySelId option:selected").text()+'</span></span>';
							}
							str+='<h3 class="font_size">Panchayati Raj Engineering Department Overview</h3>';
							
						}
						
					}
				
					str+='<div class="main_level_css m_top10">';
						str+='<div class="row">';
						
							str+='<div class="col-sm-4">';
								str+='<div class="border-cls padding_right_left">';
									str+='<h4>Total Govt Order</h4>';
									if(result.govtOrderCount == 0 || typeof(result.govtOrderCount) === 'undefined' || typeof(result.govtOrderCount) === undefined){
										str+='<h4 class="font_weight">0</h4>';
									}else{
										str+='<h4 class="font_weight">'+result.govtOrderCount+'</h4>';
										
									}
									str+='<hr class="hrlineCss"/>';
											str+='<div class="row">';
												str+='<div class="col-sm-4">';
													str+='<h4>PLAIN</h4>';
													if(result.plainGoCount == 0 || typeof(result.plainGoCount) === 'undefined' || typeof(result.plainGoCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.plainGoCount+'</h5>';
														
													}
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4>SCP</h4>';
													if(result.scpGoCount == 0 || typeof(result.scpGoCount) === 'undefined' || typeof(result.scpGoCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.scpGoCount+'</h5>';
														
													}
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4>TSP</h4>';
													if(result.tspGoCount == 0 || typeof(result.tspGoCount) === 'undefined' || typeof(result.tspGoCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.tspGoCount+'</h5>';
														
													}
												str+='</div>';
											str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="col-sm-4">';
								str+='<div class="border-cls padding_right_left">';
									str+='<h4>Total Works</h4>';
									if(result.workNumber == 0 || typeof(result.workNumber) === 'undefined' || typeof(result.workNumber) === undefined){
										str+='<h4 class="font_weight">0</h4>';
									}else{
										str+='<h4 class="font_weight">'+result.workNumber+'</h4>';
										
									}
									
									str+='<hr class="hrlineCss"/>';
											str+='<div class="row">';
												str+='<div class="col-sm-4">';
													str+='<h4>PLAIN</h4>';
													if(result.plainWorkCount == 0 || typeof(result.plainWorkCount) === 'undefined' || typeof(result.plainWorkCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.plainWorkCount+'</h5>';
														
													}
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4 >SCP</h4>';
													if(result.scpWorkCount == 0 || typeof(result.scpWorkCount) === 'undefined' || typeof(result.scpWorkCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.scpWorkCount+'</h5>';
														
													}
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4>TSP</h4>';
													if(result.tspWorkCount == 0 || typeof(result.tspWorkCount) === 'undefined' || typeof(result.tspWorkCount) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.tspWorkCount+'</h5>';
														
													}
													
												str+='</div>';
											str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="col-sm-4">';
								str+='<div class="border-cls padding_right_left">';
									str+='<h4>Total Funds</h4>';
									if(result.amountInDecimal == "0.00" || typeof(result.amountInDecimal) === 'undefined' || typeof(result.amountInDecimal) === undefined){
										str+='<h4 class="font_weight">0</h4>';
									}else{
										str+='<h4 class="font_weight">'+result.amountInDecimal+' <span style="font-size:12px;font-weight:bold;">(Cr)</span></h4>';
										
									}
									
									str+='<hr class="hrlineCss"/>';
										str+='<div class="row">';
												str+='<div class="col-sm-4">';
													str+='<h4>PLAIN</h4>';
													if(result.plainAmountInDecimal == "0.0" || typeof(result.plainAmountInDecimal) === 'undefined' || typeof(result.plainAmountInDecimal) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.plainAmountInDecimal+'</h5>';
														
													}
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4>SCP</h4>';
													if(result.scpAmountInDecimal == "0.0" || typeof(result.scpAmountInDecimal) === 'undefined' || typeof(result.scpAmountInDecimal) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.scpAmountInDecimal+'</h5>';
														
													}
													
												str+='</div>';
												str+='<div class="col-sm-4">';
													str+='<h4>TSP</h4>';
													if(result.tspAmountInDecimal == "0.0" || typeof(result.tspAmountInDecimal) === 'undefined' || typeof(result.tspAmountInDecimal) === undefined){
														str+='<h5 class="font_weight">0</h5>';
													}else{
														str+='<h5 class="font_weight">'+result.tspAmountInDecimal+'</h5>';
														
													}
												str+='</div>';
											str+='</div>';
								str+='</div>';
							str+='</div>';
							
						str+='</div>'
						
							str+='<div class="table-responsive">';
								str+='<table class="table details-overview">';
									str+='<thead>';
										str+='<tr>';
											str+='<th>Program&nbsp;Name</th>';
											str+='<th>Govt&nbsp;Order</th>';
											str+='<th>Works&nbsp;Name</th>';
											str+='<th>Total&nbsp;Works</th>';
											str+='<th>Amount&nbsp;Cr</th>';
										str+='</tr>';
									str+='</thead>';
									str+='<tbody>';
									if(result.locationList1 !=null && result.locationList1.length>0){
										for(var i in result.locationList1){
											for(var j in result.locationList1[i].locationList1){
												str+='<tr>';
													str+='<td>'+result.locationList1[i].programName+'</td>';
													if(result.locationList1[i].locationList1[j].filePath != null && result.locationList1[i].locationList1[j].filePath.length > 1){
														
													var obj1={ download: "http://www.mydepartments.in/PRRWS/Govt_Orders/"+result.locationList1[i].locationList1[j].filePath+"", filename: ""+result.locationList1[i].locationList1[j].filePath+""}
													
													var obj="http://www.mydepartments.in/PRRWS/Govt_Orders/"+result.locationList1[i].locationList1[j].filePath
													
													globalAllPdfFilesArr.push(obj)
													globalAllPdfFilesArr1.push(obj1)
														str+='<td><span filePath="'+result.locationList1[i].locationList1[j].filePath+'" style="cursor:pointer;" class="showPdfCls go_clickCr" >'+result.locationList1[i].locationList1[j].goNoDate+'</span></td>';
													}else{
														str+='<td>'+result.locationList1[i].locationList1[j].goNoDate+'</td>';
													}
													
													str+='<td>'+result.locationList1[i].locationList1[j].workName+'</td>';
													str+='<td>'+result.locationList1[i].locationList1[j].workNumber+'</td>';
													str+='<td>'+result.locationList1[i].locationList1[j].amountInDecimal+'</td>';
												str+='</tr>';
											}
											
										}
										str+='<tr>';
											str+='<th></th>';
											str+='<th></th>';
											str+='<th></th>';
											str+='<th>'+result.workNumber+'</th>';
											str+='<th>'+result.amountInDecimal+'</th>';
										str+='</tr>';
									}else{
										str+='<tr>';
											str+='<th colspan="5">No Data Available</th>';
										str+='</tr>';
									}
									
									str+='</tbody>';
								str+='</table>';
							
						str+='</div>';
					str+='</div>';
				str+='</div>';
			$("#"+divId+"DetailsDivId").html(str);
			if(departmentId == 1){
				$(".go-works").removeClass("m_top20")
			}else if(departmentId == 2){
				$(".go-works").removeClass("m_top20")
			}
		}
		else{
			$("#"+divId+"DetailsDivId").html("");
			$("#"+divId+"DetailsDivId").css("display","none");
		}
	}

function getConstituencyWiseNregsWorksDetails(departmentId,divId){
	$("#"+divId+"DetailsDivId").html(spinner);
	$("#"+divId+"DetailsDivId").css("display","block");
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	var locationId='';
	if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
		
		locationId =districtId;
	}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
		
		locationId =constituencyId;
	}
	
	
	var json = {
			locationId:locationId
	   }
    $.ajax({ 
		url: 'getConstituencyWiseNregsWorksDetails',
		type:'POST',  
		data : JSON.stringify(json),
		dataTypa : 'json',   
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},   
    }).done(function(result){
		if(result !=null && result.length>0){
			buildConstituencyWiseNregsWorksDetails(result,divId,departmentId);
		}else{
			$("#"+divId+"DetailsDivId").html("No Data");
		}
    });
}

function buildConstituencyWiseNregsWorksDetails(result,divId,departmentId){
	var str='';
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	var length = $("#DepartmentsId").val();
	str+='<div class="go-works m_top20">';
				if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
					if(length == 3){
						str+='<span class="f_16 m_top20">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span></span>';
					}
					str+='<h3 class="font_size">Mahatma Gandhi National Rural Employment Gurantee Scheme Overview</h3>';
					
				}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
					if(length == 3){
						str+='<span class="f_16 m_top20">District : <span class="font_weight text-capital">'+ $("#districtSelId option:selected").text()+'</span> Constituency : <span class="font_weight text-capital">'+$("#constituencySelId option:selected").text()+'</span></span>';
					}
					str+='<h3 class="font_size">Mahatma Gandhi National Rural Employment Gurantee Scheme Overview</h3>';
						
				}
				str+='<div class="main_level_css m_top10">';
					str+='<div class="row">';
					
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls padding_right_left">';
								str+='<h4>Total Works</h4>';
								str+='<h4 class="font_weight">'+result[0].finalWorks+'</h4>';
							str+='</div>';
						str+='</div>';
						
						str+='<div class="col-sm-6">';
							str+='<div class="border-cls padding_right_left">';
								str+='<div class="row">';
									str+='<div class="col-sm-4">';
										str+='<h4>Total</h4>';
										str+='<h4 class="font_weight">'+result[0].finalAmount+' <span style="font-size:12px;font-weight:bold;">(Cr)</span></h4>';
									str+='</div>';
									str+='<div class="col-sm-4">';
										str+='<h4>Wage</h4>';
										str+='<h4 class="font_weight">'+result[0].wageAmount+' <span style="font-size:12px;font-weight:bold;">(Cr)</span></h4>';
									str+='</div>';
									str+='<div class="col-sm-4">';
										str+='<h4>Material</h4>';
										str+='<h4 class="font_weight">'+result[0].materialAmount+' <span style="font-size:12px;font-weight:bold;">(Cr)</span></h4>';
									str+='</div>';
								str+='</div>';
								
							str+='</div>';
						str+='</div>';
					str+='</div>'
						
						str+='<h4 class="m_top20">Works Details</h4>';
						str+='<div class="table-responsive">';
							str+='<table class="table details-overview">';
								str+='<thead>';
									str+='<tr>';
										str+='<th>Work&nbsp;Names</th>';
										str+='<th>Works</th>';
										str+='<th>Wage&nbsp;<span style="font-size:12px;font-weight:bold;">(Cr)</span></th>';
										str+='<th>Material&nbsp;<span style="font-size:12px;font-weight:bold;">(Cr)</span></th>';
										str+='<th>Amount&nbsp;<span style="font-size:12px;font-weight:bold;">(Cr)</span></th>';
									str+='</tr>';
								str+='</thead>';
								str+='<tbody>';
								var completedWorks=0;
								var totalFunds=0.00;
								if(result !=null && result.length>0){
									for(var i in result){
										completedWorks=completedWorks+result[i].completed;
										totalFunds = parseFloat(totalFunds)+parseFloat(result[i].material);
										str+='<tr>';
											str+='<td>'+result[i].workName+'</td>';
											str+='<td>'+result[i].completed+'</td>';
											str+='<td>'+result[i].wage+'</td>';
											str+='<td>'+result[i].material+'</td>';
											str+='<td>'+result[i].total+'</td>';
										str+='</tr>';
										
									}
									str+='<tr>';
										str+='<th></th>';
										str+='<th>'+result[0].finalWorks+'</th>';
										str+='<th>'+result[0].wageAmount+' (Cr)</th>';
										str+='<th>'+result[0].materialAmount+' (Cr)</th>';
										str+='<th>'+result[0].finalAmount+' (Cr)</th>';
									str+='</tr>';
								}else{
									str+='<tr>';
										str+='<th colspan="5">No Data Available</th>';
									str+='</tr>';
								}
								
								str+='</tbody>';
							str+='</table>';
						
					str+='</div>';
				str+='</div>';
			str+='</div>';
	
	$("#"+divId+"DetailsDivId").html(str);
	
	if(length == 3){
		$(".go-works").removeClass("m_top20")		
	}
}
function getDistrictNameAndMlaNameByConsitutency(){
	$("#addressDivId").html(spinner);	
	var districtId = $("#districtSelId").val();
	var constituencyId = $("#constituencySelId").val();
	var locationId='';
	if((districtId !=0 || districtId !=null) && (constituencyId == null || constituencyId == 0)){
		
		locationId =districtId;
	}else if((districtId !=0 || districtId !=null) && (constituencyId != null || constituencyId != 0)){
		
		locationId =constituencyId;
	}
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
		if(result !=null && result.length>0){
		var str='';
			str+='<p>';
				str+='<span><strong>To,</strong></span><br/>';
				str+='<span><strong>'+result[0].mlaName+',</strong></span><br/>';
				str+='<span><strong>Member of Legislative Assembly,</strong></span><br/>';
				str+='<span><strong>'+result[0].workName+' Constituency.</strong></span>';
			str+='</p>';
			
		$("#addressDivId").html(str);	
		}
    });
}
$(document).on('click','.showPdfCls',function(){
	$("#pdfReportDetailsId").html('');
	$("#pdfReportDetailsId").html(spinner);
	var str = '';
	var filePath = $(this).attr("filePath");
	if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
		$("#pdfModelId").modal("hide");
		window.open('http://www.mydepartments.in/PRRWS/Govt_Orders/'+filePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
	
		/*setTimeout(function(){
		$(w.document).find('html').append('<head><title>your title</title></head>');}, 2000); */
		//w.onload = function() { this.document.title = "your new title"; }
		

	}else{
		
		$("#pdfModelId").modal("show");
		str+='<object data="http://www.mydepartments.in/PRRWS/Govt_Orders/'+filePath+'" type="application/pdf" width="100%" height="400px;" internalinstanceid="3" title=""></object>';
		$("#pdfReportDetailsId").find(".modal-title").html(filePath+" PDF REPORT");
		$("#pdfReportDetailsId").html(str);
	}
	
});
function download_files(files) {
    $.each(files, function(key, value) {
        $('<iframe></iframe>')
            .hide()
            .attr('src', value)
            .appendTo($('body'))
            .load(function() {
                var that = this;
                setTimeout(function() {
                    $(that).remove();
                }, 100);
            });
    });
}
function download_files1(files) {
  function download_next(i) {
    if (i >= files.length) {
      return;
    }
    var a = document.createElement('a');
    a.href = files[i].download;
    a.target = '_parent';
    // Use a.download if available, it prevents plugins from opening.
    if ('download' in a) {
      a.download = files[i].filename;
    }
    // Add a to the doc for click to work.
    (document.body || document.documentElement).appendChild(a);
    if (a.click) {
      a.click(); // The click method is supported by most browsers.
    } else {
      $(a).click(); // Backup using jquery
    }
    // Delete the temporary link.
    a.parentNode.removeChild(a);
    // Download the next file with a small timeout. The timeout is necessary
    // for IE, which will otherwise only download the first file.
    setTimeout(function() {
      download_next(i + 1);
    }, 500);
  }
  // Initiate the first download.
  download_next(0);
}
 $(document).on("click",".exportToPdf1",function(){
	 if(navigator.userAgent.indexOf("Chrome") !== -1){
		 setTimeout(function(){
			download_files1(globalAllPdfFilesArr1);
		},1000);
		
	}else{ 
		setTimeout(function(){
			 download_files(globalAllPdfFilesArr);
		},1000);
		
	}
	
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
		a1  = [ 90160  , 109933];  // for a4 size paper width and height
	createPDF()
	
	//create pdf
	function createPDF(){
		getCanvas().then(function(canvas){
			var 
			img = canvas.toDataURL("image/png"),
			doc = new jsPDF({
				unit:'px', 
				format:'a0'
			});     
			doc.addImage(img, 'JPEG', 05, 05);
			doc.save(''+id+'.pdf');
			form.width(cache_width);
		});
		$("#"+id).hide();
	}

	// create canvas object
	function getCanvas(){
		//form.width((a1[0]*1.33333) -80).css('max-width','none');
		$("#"+id).show();
		form.width(a1).css('max-width','none');
		return html2canvas(form,{
			imageTimeout:100,
			removeContainer:true
		});	
	}	
}