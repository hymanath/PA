var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
var $windowWidth = $(window).width();
onLoadInitialisations();
function onLoadInitialisations(){
		$(document).keydown(function(event){
			if(event.keyCode==123){
				alert("Hoo no! don't try to expose me");
				return false;
			}
			else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
				alert("Hoo no! don't try to expose me");
				return false;  //Prevent from ctrl+shift+i
			}
		});
		var width = $(window).width()
		if(width > 767)
		{
			header = $('header section'),
			$(window).scroll(function(){
				var windowScrollTop = $(window).scrollTop();

				if (windowScrollTop>50) {
					header.addClass("header-fixed");
				} else {
					header.removeClass("header-fixed");
				}
			});
		}
		$(".chosenSelect").chosen();
		
		
		getAllDepartments();
		getAllFiniancialYears();		
	}
	var financialArrGlob=[];
	financialArrGlob.push("0");
	//console.log("glob -- "+financialArrGlob);
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
			 
		}console.log(financialArrGlob +" ---- "+ values);
		//financialArrGlob = values;
		
	});
	var departmentArrGlob =[];
	departmentArrGlob.push("0");
	$(document).on("change","#DepartmentsId",function(){//ara1
		var values = $(this).val();//debugger;
		
		if(values != null && values.length > 0){
			for(var i=0; i<values.length; i++) {
			//console.log(values[i]+" -- "+financialArrGlob+" -- "+$.inArray(values[i], financialArrGlob));
				if($.inArray(values[i], departmentArrGlob) == -1){
					if(values[i] == 0){values=[];values.push("0");
						$('#DepartmentsId').find($('option')).attr('selected',false)
						$("#DepartmentsId").val(0);
						$("#DepartmentsId").trigger('chosen:updated');
						departmentArrGlob = [];
						departmentArrGlob.push("0");
					}else{
						$('#DepartmentsId option:selected').each(function (index, option) { 
							if($(this).val()==0){
								$(option).attr('selected',false); 
								$("#DepartmentsId").trigger('chosen:updated');
							}
							departmentArrGlob=[];
							departmentArrGlob.push($(this).val());
						});
					}
				}
				
			 }
		}
	});
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
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
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
		glStartDate = picker.startDate.format('DD/MM/YYYY')
		glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		
		
	});
	function getAllDepartments(){
		$("#DepartmentsId").html('');
		var json = {

		}
		$.ajax({                
			type:'POST',    
			url: 'getAllDepartments',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			$("#DepartmentsId").append("<option value='0'>All</option>");
			if(result != null && result.length >0){
				for(var i in result){
					$("#DepartmentsId").append("<option value="+result[i].id+">"+result[i].name+"</option>");
				}
			}
			
			$("#DepartmentsId").val(0)
			$("#DepartmentsId").trigger("chosen:updated");
		});
		
	}
	function getAllFiniancialYears()
	{
		$("#financialYearId").html('');
		$(".compMultiFinancialYear").html('');
		$(".compSingleFinancialYear").html('');
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
			$("#financialYearId").append("<option value='0'>All</option>");
			if(result != null && result.length >0){
				for(var i in result){
					$("#financialYearId").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
					$(".compMultiFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
					$(".compSingleFinancialYear").append("<option value="+result[i].financialYearId+">"+result[i].financialYear+"</option>");
				}
				$("#financialYearId").val(0);
				$(".compMultiFinancialYear").val(1);
				$(".compSingleFinancialYear").val(3);
			}
			$("#financialYearId").chosen();
			$("#financialYearId").trigger('chosen:updated');
			$(".compMultiFinancialYear").trigger('chosen:updated');
			$(".compSingleFinancialYear").trigger('chosen:updated');
			$("#financialYearId_chosen").find(".search-choice-close").attr("data-option-array-index","0").addClass("clicked")
			
			
		});
   }
   getALlProgramesAmountDetails();
   function getALlProgramesAmountDetails(){
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		
		var sourceIdsList=[];
		var glSearchLevelId=0;
		var glSearchLevelValue=[];
		
		var json = {
		  financialYrIdList:financialYrIdList,
		  deptIdsList : deptIdsArr,
          sourceIdsList : sourceIdsList,
		  fromDateStr : glStartDate,       
		  toDateStr : glEndDate,		
		  searchLevelId:glSearchLevelId,
		  searchLvlVals:glSearchLevelValue
		}
		$.ajax({
			url : "getALlProgramesAmountDetails",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){   
				
			}
		});
	}