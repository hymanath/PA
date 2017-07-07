var globalLevelObj =  {"distLevelDistrictNames":"DISTRICT","constLevelDistNames":"DISTRICT","mandalLevelDistNames":"DISTRICT","constLevelConstNames":"CONSTITUENCY","mandalLevelConstNames":"CONSTITUENCY","mandalLevelMandalNames":"MANDAL","villageLevelDistNames":"DISTRICT",'villageLevelConstNames':'CONSTITUENCY','villageLevelMandalNames':'MANDAL','villageLevelNames':'VILLAGE','constLevelParliaNames':'PARLIAMENT','parliamentLevelConstNames':'PARLIAMENT','villageLeveParliNames':'PARLIAMENT','distLevelParliamentNames':'PARLIAMENT'};
var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var $windowWidth = $(window).width();
	onLoadInitialisations();
	function onLoadInitialisations(){
		getALlProgramesAmountDetails();
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
		
		getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',0,0,0,0);
		getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',0,3,0,0);
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',0,4,0,0);
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',0,5,0,0);
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',0,6,0,0);
		
		getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
		getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
		getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
		getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);
		
		
		getGovtSchemesDetails("programNamesState");
		getGovtSchemesDetails("programNamesDistrict");		
		getGovtSchemesDetails("programNamesConst");		
		getGovtSchemesDetails("programNamesMandal");		
		getGovtSchemesDetails("programNamesVillage");

		 getGovtSubProgramsDetails(0,"subProgramNamesState");
		getGovtSubProgramsDetails(0,"subProgramNamesDistrict");
		getGovtSubProgramsDetails(0,"subProgramNamesConst");
		getGovtSubProgramsDetails(0,"subProgramNamesMandal");
		getGovtSubProgramsDetails(0,"subProgramNamesVillage");	
	}
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	function getblockType(){
		 var blockType = ''; 
		$('.distLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  blockType = $(this).attr("attr_type");
			 }
		});
		return blockType;
	}
	function getblockTypeCons(){
		 var blockTypeCons = ''; 
		$('.consLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeCons = $(this).attr("attr_type");
			 }
		});
		return blockTypeCons;
	}
	function getSelectedType(){
		 var sortingType = ''; 
		 var orderType='';
		$('.sortingDivDistCls li').each(function(i, obj){
			 if($(this).hasClass('active')){
			  sortingType = $(this).attr("attr_sorting_type");
			  orderType=$(this).attr("attr_order_type");
			 }
		});
		return {
				sortingType:sortingType,
				orderType:orderType
		}
	}
	function getblockTypeMandal(){
		 var blockTypeMadal = ''; 
		$('.mandalLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeMadal = $(this).attr("attr_type");
			 }
		});
		return blockTypeMadal;
	}
	function getblockTypeVillage(){
		 var blockTypeVillage = ''; 
		$('.villageLevelActive li').each(function(i, obj){
			 if($(this).hasClass('active')){
				blockTypeVillage = $(this).attr("attr_type");
			 }
		});
		return blockTypeVillage;
	}
	function getGovtSchemesDetails(divId){
		$("#"+divId).html('');
		var json = {
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtSchemesDetails',  
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){
		  if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL PROGRAMMES </option>');
				for(var i in result){
					$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		
			$("#"+divId).trigger('chosen:updated');
		});
	  }
	  
	//Program Onchange
	$(document).on("change","#programNamesState",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	 // getGovtSubProgramsDetails(programId,"subProgramNamesState");
	  getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',0,0,programId,subProgramId)
	  
	  
	});
	$(document).on("change","#programNamesDistrict",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	 //getGovtSubProgramsDetails(programId,"subProgramNamesDistrict");
	 getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',0,3,programId,subProgramId);
		
	});
	$(document).on("change","#programNamesConst",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	  $("#constLevelDistNames").val(0);
	  $("#constLevelParliaNames").val(0);
	  $("#constLevelDistNames").trigger("chosen:updated");
	  $("#constLevelParliaNames").trigger("chosen:updated");
	  var id = $("#constLevelConstNames").val();
	  if(id>0){
		  $("#constLevelConstNames").html('');
		  $("#constLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#constLevelConstNames").trigger("chosen:updated");
	  }
	  
	 //getGovtSubProgramsDetails(programId,"subProgramNamesConst");
	 getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',0,4,programId,subProgramId);
		
	});
	$(document).on("change","#programNamesMandal",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	  $("#mandalLevelDistNames").val(0);
	  $("#parliamentLevelConstNames").val(0);
	  $("#mandalLevelDistNames").trigger("chosen:updated");
	  $("#parliamentLevelConstNames").trigger("chosen:updated");
	  var id = $("#mandalLevelConstNames").val();
	  if(id>0){
		  $("#mandalLevelConstNames").html('');
		  $("#mandalLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#mandalLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#mandalLevelMandalNames").val();
	  if(id1>0){
		  $("#mandalLevelMandalNames").html('');
		  $("#mandalLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#mandalLevelMandalNames").trigger("chosen:updated");
	  }
	 //getGovtSubProgramsDetails(programId,"subProgramNamesMandal");
	 getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',0,5,programId,subProgramId);
		
	});
	$(document).on("change","#programNamesVillage",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	  $("#villageLevelDistNames").val(0);
	  $("#villageLeveParliNames").val(0);
	  $("#villageLevelDistNames").trigger("chosen:updated");
	  $("#villageLeveParliNames").trigger("chosen:updated");
	  var id = $("#villageLevelConstNames").val();
	  if(id>0){
		  $("#villageLevelConstNames").html('');
		  $("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#villageLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#villageLevelMandalNames").val();
	  if(id1>0){
		  $("#villageLevelMandalNames").html('');
		  $("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#villageLevelMandalNames").trigger("chosen:updated");
	  }
	  var id2 = $("#villageLevelNames").val();
	  if(id2>0){
		  $("#villageLevelNames").html('');
		  $("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		  $("#villageLevelNames").trigger("chosen:updated");
	  }
	 //getGovtSubProgramsDetails(programId,"subProgramNamesVillage");
	 getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',0,6,programId,subProgramId);
	});

	//SubProgram Onchange
	$(document).on("change","#subProgramNamesState",function(){
	  var subProgramId = $(this).val();
	  var programId = $("#programNamesState").val();
	  getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',0,0,programId,subProgramId)
	});
	$(document).on("change","#subProgramNamesDistrict",function(){
	 var subProgramId = $(this).val();
	 var programId = $("#programNamesDistrict").val();
	  getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',0,3,programId,subProgramId);
	});
	$(document).on("change","#subProgramNamesConst",function(){
	 var subProgramId = $(this).val();
	 var programId = $("#programNamesConst").val();
	 $("#constLevelDistNames").val(0);
	  $("#constLevelParliaNames").val(0);
	  $("#constLevelDistNames").trigger("chosen:updated");
	  $("#constLevelParliaNames").trigger("chosen:updated");
	  var id = $("#constLevelConstNames").val();
	  if(id>0){
		  $("#constLevelConstNames").html('');
		  $("#constLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#constLevelConstNames").trigger("chosen:updated");
	  }
	 getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',0,4,programId,subProgramId);
	});
	$(document).on("change","#subProgramNamesMandal",function(){
	 var subProgramId = $(this).val();
	 var programId = $("#programNamesMandal").val();
	 $("#mandalLevelDistNames").val(0);
	  $("#parliamentLevelConstNames").val(0);
	  $("#mandalLevelDistNames").trigger("chosen:updated");
	  $("#parliamentLevelConstNames").trigger("chosen:updated");
	  var id = $("#mandalLevelConstNames").val();
	  if(id>0){
		  $("#mandalLevelConstNames").html('');
		  $("#mandalLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#mandalLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#mandalLevelMandalNames").val();
	  if(id1>0){
		  $("#mandalLevelMandalNames").html('');
		  $("#mandalLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#mandalLevelMandalNames").trigger("chosen:updated");
	  }
	  getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',0,5,programId,subProgramId);
	});
	$(document).on("change","#subProgramNamesVillage",function(){
	  var subProgramId = $(this).val();
	  var programId = $("#programNamesVillage").val();
	  $("#villageLevelDistNames").val(0);
	  $("#villageLeveParliNames").val(0);
	  $("#villageLevelDistNames").trigger("chosen:updated");
	  $("#villageLeveParliNames").trigger("chosen:updated");
	  var id = $("#villageLevelConstNames").val();
	  if(id>0){
		  $("#villageLevelConstNames").html('');
		  $("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#villageLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#villageLevelMandalNames").val();
	  if(id1>0){
		  $("#villageLevelMandalNames").html('');
		  $("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#villageLevelMandalNames").trigger("chosen:updated");
		  
	  }
	  var id2 = $("#villageLevelNames").val();
	  if(id2>0){
		  $("#villageLevelNames").html('');
		  $("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		  $("#villageLevelNames").trigger("chosen:updated");
		  
	  }
	  getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',0,6,programId,subProgramId);
	});
	function emptyProgramSubProgramDistVal(){
		$("#programNamesDistrict").val(0);
		$("#programNamesDistrict").trigger('chosen:updated');
		$("#subProgramNamesDistrict").empty();
		$("#subProgramNamesDistrict").append('<option value="0">ALL SUB PROGRAMMES </option>');	
		$("#subProgramNamesDistrict").trigger('chosen:updated');
	}
	function emptyProgramSubProgramConstVal(){
		$("#programNamesConst").val(0);
		$("#programNamesConst").trigger('chosen:updated');
		$("#subProgramNamesConst").empty();
		$("#subProgramNamesConst").append('<option value="0">ALL SUB PROGRAMMES</option>');	
		$("#subProgramNamesConst").trigger('chosen:updated');
	}
	function emptyProgramSubProgramVillageVal(){
		$("#programNamesVillage").val(0);
		$("#programNamesVillage").trigger('chosen:updated');
		$("#subProgramNamesVillage").empty();
		$("#subProgramNamesVillage").append('<option value="0">ALL SUB PROGRAMMES</option>');	
		$("#subProgramNamesVillage").trigger('chosen:updated');
		$("#villageLevelNames").html('');
		$("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		$("#villageLevelNames").trigger("chosen:updated");
		$("#villageLevelMandalNames").html('');
		$("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		$("#villageLevelMandalNames").trigger("chosen:updated");
		$("#villageLevelConstNames").html('');
		$("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		$("#villageLevelConstNames").trigger("chosen:updated");
	}
	function emptyProgramSubProgramMandalVal(){
		$("#programNamesMandal").val(0);
		$("#programNamesMandal").trigger('chosen:updated');
		$("#subProgramNamesMandal").empty();
		$("#subProgramNamesMandal").append('<option value="0">ALL SubProgram</option>');	
		$("#subProgramNamesMandal").trigger('chosen:updated');
		$("#mandalLevelMandalNames").html('');
	    $("#mandalLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
	    $("#mandalLevelMandalNames").trigger("chosen:updated");
	    $("#mandalLevelConstNames").html('');
	    $("#mandalLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
	    $("#mandalLevelConstNames").trigger("chosen:updated");
	}
	$(document).on("click","[tab-switch] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("tab-switch");
		var blockType = $(this).attr("attr_type");
		if(blockName == 'distLevel'){
			
			if(blockType == "parliamentType"){
				emptyProgramSubProgramDistVal()
				getAllSubLocationsBySuperLocationId(30,'distLevelParliamentNames',3);
				$(".distLevelparliamentCls").show();
				$(".distLevelCls").hide();
				 getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',0,9,0,0);
			}else{
				$("#distLevelDistrictNames").val(0);
				$("#distLevelDistrictNames").trigger('chosen:updated');
				emptyProgramSubProgramDistVal()
				$(".distLevelparliamentCls").hide();
				$(".distLevelCls").show();
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',0,3,0,0);
			}
			
		}else if(blockName == 'consLevel'){
			if(blockType == "parliamentType"){
				emptyProgramSubProgramConstVal()
				getAllSubLocationsBySuperLocationId(30,'constLevelParliaNames',4);
				$(".constiLevelDistCls").hide();
				$(".parlaiLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',0,9,0,0);
			}else{
				$("#constLevelDistNames").val(0);
				$("#constLevelDistNames").trigger('chosen:updated');
				emptyProgramSubProgramConstVal()
				$(".parlaiLevelDistCls").hide();
				$(".constiLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',0,4,0,0);
			}
			 
		}else if(blockName == 'mandalLevel'){
			if(blockType == "parliamentType"){
				 
				emptyProgramSubProgramMandalVal()
				getAllSubLocationsBySuperLocationId(30,'parliamentLevelConstNames',5);
				$(".mandalLevelDistCls").hide();
				$(".levelparliamentConstiCls").show();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',0,9,0,0);
			}else{
				$("#mandalLevelDistNames").val(0);
				$("#mandalLevelDistNames").trigger('chosen:updated');
				 
				emptyProgramSubProgramMandalVal()
				$(".levelparliamentConstiCls").hide();
				$(".mandalLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',0,5,0,0);
			}
		}else if(blockName == 'villageLevel'){
			if(blockType == "parliamentType"){
				emptyProgramSubProgramVillageVal()
				getAllSubLocationsBySuperLocationId(30,'villageLeveParliNames',6);
				
				$(".villageLevelDistCls").hide();
				$(".villageLevelParliCls").show();
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',0,9,0,0);
			}else{
				$("#villageLevelDistNames").val(0);
				$("#villageLevelDistNames").trigger('chosen:updated');
				
				emptyProgramSubProgramVillageVal()
				$(".villageLevelParliCls").hide();
				$(".villageLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',0,6,0,0);
			}
		}
		
	});  
	  function getGovtSubProgramsDetails(programId,divId){
		  $("#"+divId).html('');
		var json = {
			govtSchemesId:programId,
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtSubProgramsDetails',  
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){
		  if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL SUB PROGRAMMES</option>');
				for(var i in result){
					$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		
			$("#"+divId).trigger('chosen:updated'); 
		});
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
				buildALlProgramesAmountDetails(result);
			}
		});
	}
	function buildALlProgramesAmountDetails(result)
	{
		var str='';
		for(var i in result)
		{
			str+='<div class="col-sm-3 m_top10">';
				str+='<table class="table table-bordered table-striped tableClr">';
					str+='<thead>';
						str+='<tr>';
							str+='<th class="text-center" colspan="3"><h4>'+result[i].name+'</h4><h3><b>'+result[i].ttlAmt+'</b></h3></th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						str+='<tr>';
							for(var j in result[i].subList)
							{
								if(result[i].subList[j].id == 1 ||result[i].subList[j].id == 2 ||result[i].subList[j].id == 3)
								{
									if(result[i].subList[j].totl != null && result[i].subList[j].totl > 0)
									{
										str+='<td class="text-center"><h4>'+result[i].subList[j].name+'</h4><h4><b><i class="fa fa-inr"></i>'+result[i].subList[j].totl+'</b></h4></td>';
									}else{
										str+='<td class="text-center"><h4>'+result[i].subList[j].name+'</h4><h4><b>-</b></h4></td>';
									}
									
								}
							}
						str+='</tr>';
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		}
		
		$("#overviewBlock").html(str);
	}
	
	function getSchemeWiseLocationWiseAmountDetails(levelId,divId,sortingType,orderType,locationId,locationLevelType,programId,subProgramId){
		$("#"+divId).html(spinner);
		$("#"+divId+"Table").html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var govtSchmeIdsList=[];
		var subProgramIdsList=[];
		var deptIdsArr =$('#DepartmentsId').val();
		var financialYrIdArr = $('#financialYearId').val();
		
		if(locationId == null || locationId == 0){
			 levelValues =[];
		}else{
			 var tempIdStr=""+locationId;
			 var finalIdStr= tempIdStr.substring(1);
			 levelValues.push(finalIdStr);
		}
		 
		if(programId == null || programId == 0){
			govtSchmeIdsList=[];
		}else{
			govtSchmeIdsList.push(programId)
		}
		if(subProgramId == null || subProgramId == 0){
			subProgramIdsList=[];
		}else{
			subProgramIdsList.push(subProgramId)
		}
		var json = {
			searchLevelId:locationLevelType,
			blockLevelId : levelId, 
			levelValues : levelValues,
			financialYrIdList : financialYrIdArr,
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			sourceIdsList:sourceIdsArr,
			schemeIdsList:schemeIdsArr,
			deptIdsList:deptIdsArr,			
			sortingType :sortingType,
			order :orderType,
			govtSchmeIdsList :govtSchmeIdsList,
			subProgramIdsList :subProgramIdsList,
			glSearchLevelValue :[],
			glSearchLevelId :1
		}
		$.ajax({
			url : "getFinancialYearWiseScheameDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				if(ajaxresp != null && ajaxresp.length>0){
					buildLocationWiseAmountDetails(ajaxresp,divId,levelId);
				}
					
				else{
					$("#"+divId).html("NO DATA AVAILABLE");
					$("#"+divId+"Table").html("NO DATA AVAILABLE");
				}
					
			}
		});
	}
	
	function buildLocationWiseAmountDetails(result,divId,levelId,displayType,locationId,locationName)//ara
	{
		$("#"+divId).html("");
		var str='';
		
		str+='<div class="scroller'+divId+'">';
			str+='<div class="chart'+divId+'"></div>';
		str+='</div>';
		str+='</div>';
		$("#"+divId).html(str);
		
		if(result[0].subList == null || result[0].subList.length == 0){
			$("#"+divId+"Table").html("");
			$("#"+divId+"Table").html('<span style="font-weight:bold;"> NO DATA AVAILABLE...</span>');
			return;
		}
			
		var table='';
		if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			}	
			table+='<table class="table table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						if(levelId == '2')
						{
							table+='<th>STATE</th>';
						}else if(levelId == '3')
						{
							table+='<th>DISTRICT</th>';
						}else if(levelId == '4')
						{
							table+='<th>DISTRICT</th>';
							table+='<th>PARLIAMENT</th>';
							table+='<th>CONSTITUENCY</th>';
						}else if(levelId == '5')
						{
							table+='<th>DISTRICT</th>';
							table+='<th>PARLIAMENT</th>';
							table+='<th>CONSTITUENCY</th>';
							table+='<th>MANDAL</th>';
						}else if(levelId == '6')
						{
							table+='<th>DISTRICT</th>';
							table+='<th>PARLIAMENT</th>';
							table+='<th>CONSTITUENCY</th>';
							table+='<th>MANDAL</th>';
							table+='<th>VILLAGE</th>';
						}
						table+='<th class="text-center">YEAR</th>';
						for(var j in result[0].subList[0].subList){
							table+='<th class="text-center">'+result[0].subList[0].subList[j].name+'</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					
					for(var j in result[0].subList)
					{
					table+='<tr>';
					var lvlVal = 0;
						if(levelId == '2')
						{
							lvlVal =1;
							//table+='<td>1</td>';
							table+='<td>Andhra Pradesh</td>';
						}
						else if(levelId == '3')
						{
							lvlVal =result[i].addressVO.districtId;
							table+='<td>'+result[i].addressVO.districtName+'</td>';
						}else if(levelId == '4')
						{
							lvlVal =result[i].addressVO.assemblyId;
							table+='<td>'+result[i].addressVO.districtName+'</td>';
							table+='<td>'+result[i].addressVO.parliamentName+'</td>';
							table+='<td>'+result[i].addressVO.assemblyName+'</td>';
						}else if(levelId == '5')
						{
							lvlVal =result[i].addressVO.id;
							table+='<td>'+result[i].addressVO.districtName+'</td>';
							table+='<td>'+result[i].addressVO.parliamentName+'</td>';
							table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							table+='<td>'+result[i].addressVO.tehsilName+'</td>';
						}else if(levelId == '6')
						{
							lvlVal =result[i].addressVO.id;
							table+='<td>'+result[i].addressVO.districtName+'</td>';
							table+='<td>'+result[i].addressVO.parliamentName+'</td>';
							table+='<td>'+result[i].addressVO.assemblyName+'</td>';
							table+='<td>'+result[i].addressVO.tehsilName+'</td>';
							table+='<td>'+result[i].addressVO.panchayatName+'</td>';
						}
						table+='<td class="text-center">'+result[0].subList[j].year+'</td>';
						for(var k in result[i].subList[j].subList)
						{
							if(result[i].subList[j].yearId != 0){
								newYearId = result[i].subList[j].yearId;
							}else{
								newYearId = $("#financialYearId").val();
							} 
							if(levelId != '2'){
								if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count > 0){
									table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+result[i].subList[j].subList[k].id+'" attr_dept_id="0" >'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'<small title="No of times amount sanctioned..." class="toolltipCls">&nbsp;&nbsp;(<u><span style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].count+'</span> </u>)</small></td>';
								}else{
									table+='<td class="text-center no-right-border">-</td>';
								}
							}else{
								if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count > 0){
									table+='<td class="text-center no-right-border">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'<small title="No of times amount sanctioned..." class="toolltipCls" >&nbsp;&nbsp;('+result[i].subList[j].subList[k].count+')</small></td>';
								}else{
									table+='<td class="text-center no-right-border">-</td>';
								}
							}
						}
					table+='</tr>';
					}
				}
				table+='</tbody>';
			table+='</table>';
			if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='</div>';
			}	
		$("#"+divId+"Table").html("");
		$("#"+divId+"Table").html(table);
		
		$('.toolltipCls').tooltip();
		
		if(levelId == 3 || levelId == 4 || levelId == 5 || levelId == 6)
		{
			$("#dataTable"+divId).dataTable({
					"iDisplayLength": 15,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			});
			
		}
			if(levelId == 2){
				if(result !=null && result.length>0){
					var yearsArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[]; 
					var APDRPGRANTArr=[]; 
					var NRDWPArr=[]; 
					var SDPArr=[];
					var totalCountArr = [];
					var totalCount2 = '';
					for(var i in result){
						
						
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
								var countAvailable = false;
								if(result[i].subList[j].subList != null && result[i].subList[j].subList.length > 0){
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
								}
								if(countAvailable){
									yearsArr.push(result[i].subList[j].year);
								}
								
								
								if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
									var countAvailable = false;
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
									var locationId= 1;
									if(countAvailable){
										totalCount2 = 0;
										for(var k in result[i].subList[j].subList){
                                            totalCount2 =totalCount2+result[i].subList[j].subList[k].count;
											if(result[i].subList[j].subList[k].id == 1){
												//NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 //FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }
										}
										 totalCountArr.push(totalCount2);
									}
									
								}	
							}
						}
						var mainJosnObjArr=[];
						if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#309AFF"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#01A64E"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#FF0DAD"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#FF872C"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#3C46FF"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#5B5B5B"});  
						}
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
						}if(NRDWPArr != null && NRDWPArr.length > 0){
							mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
						}if(SDPArr != null && SDPArr.length > 0){
							mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
						}
					}
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						xAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							categories: yearsArr
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								align: 'left',
								enabled: true,
								qTotals: totalCountArr,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							useHTML: true,
							formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
									this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
								});
								
								return s;
							},
							shared: true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							},series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {   
										var value = (this.appData).split("-");
										var blockLvlId = value[0];
										var levlValue = value[1];
										var financialYrId = value[2];
										var schemeId = value[3];
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0);
									}
								}
							}
				        }
						},
						legend: {
							verticalAlign:'top',
							enabled: true
						},
						series: mainJosnObjArr
					});
					
				}
			}else if(levelId == 3 || levelId == 4 || levelId == 5){
				
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var CRRArr=[];
					var RDFArr=[];
					var MRRArr=[];
					var FC13Arr=[];
					var APDRPGRANTArr=[];
					var NRDWPArr=[];
					var SDPArr=[];
					var totalCountArr = [];
                    var totalCount2 = '';
					for(var i in result){
						if(result[i].subList !=null && result[i].subList.length>0){
							for(var j in result[i].subList){
								var countAvailable = false;
								if(result[i].subList[j].subList != null && result[i].subList[j].subList.length > 0){
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
								}
								if(countAvailable){
									var locationId;
									if(levelId == 3){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.districtName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 4){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 5){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.name+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 6){
										locationId=result[i].subList[j].addressVO.id;
										assemblyShemeNameArr.push(result[i].subList[j].addressVO.panchayatName+"<br/>("+result[i].subList[j].year+")")
									}
								}
								
								
								if(result[i].subList[j].subList !=null && result[i].subList[j].subList.length>0){
									var countAvailable = false;
									for(var s in result[i].subList[j].subList){
										if(result[i].subList[j].subList[s].totalCount != null && result[i].subList[j].subList[s].totalCount > 0){
											countAvailable = true;
										}
									}
									if(countAvailable){
										 totalCount2 = 0;
										for(var k in result[i].subList[j].subList){
                                             totalCount2 =totalCount2+result[i].subList[j].subList[k].count;
											 if(result[i].subList[j].subList[k].id == 1){
												// NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												//FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NRDWPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NRDWPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SDPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }
											
										}
										totalCountArr.push(totalCount2);
									}
									
								}
							}
						}
						var mainJosnObjArr= [];
						if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#309AFF"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#01A64E"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#FF0DAD"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#FF872C"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#3C46FF"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#5B5B5B"});  
						}
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#46306A"});  
						}
						if(NRDWPArr != null && NRDWPArr.length > 0){
							mainJosnObjArr.push({name:'NRDWP',data:NRDWPArr,color:"#9C9C9C"});  
						}if(SDPArr != null && SDPArr.length > 0){
							mainJosnObjArr.push({name:'SDP',data:SDPArr,color:"#14BAAD"});  
						}
					}
					var length = result.length
					var height = '';
					if(length == 0)
					{
						height = length * 100;
					}else if(length > 3){
						height = length * 200;
					}
					if(length > 8)
					{
						$(".scroller"+divId).mCustomScrollbar({setHeight:'600px'})
					}
					$(".chart"+divId).height(height);
					$(".chart"+divId).highcharts({
						chart: {
							type: 'bar',
							backgroundColor:'transparent'
						
						},
						title: {
							text: null
						},
						subtitle: {
							text: null
						},
						xAxis: {
							min: 0,
							gridLineWidth: 0,
							minorGridLineWidth: 0,
							categories: assemblyShemeNameArr
						},
						yAxis: {
								min: 0,
								gridLineWidth: 0,
								minorGridLineWidth: 0,
							title: {
								text: null
							},
							labels: {
								enabled:false
							},
							stackLabels: {
								useHTML: true,
								align: 'left',
								enabled: true,
								qTotals: totalCountArr,
								style: {
									fontWeight: 'bold',
									color: (Highcharts.theme && Highcharts.theme.textColor) || '#333'
								},
								formatter: function() {
									return '<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total+'-('+this.options.qTotals[this.x]+')</span>';
									//return '<span style="top:16px; position: absolute;"><br/>'+this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')</span>';
									//return this.options.alertPerc[this.x]+'%'+' '+'('+this.total+')';
									//return (this.total);
								} 
							
							}
						
						},
						tooltip: {
							useHTML: true,
							formatter: function () {
							var s = '<b>' + this.x + '</b>';

								$.each(this.points, function () {
									if(this.series.name != "Series 1")  
									s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
									this.y+'-'+'('+(this.point.extra)+')'/* +' - ' +
									(Highcharts.numberFormat(this.percentage,1)+'%'); */
								});
								
								return s;
							},
							shared: true
						},
						plotOptions: {
							bar: {
								stacking: 'normal',
								pointWidth: 30,
								gridLineWidth: 15
							},series: {
							cursor: 'pointer',
							point: {
							events: {
									click: function () {   
										var value = (this.appData).split("-");
										var blockLvlId = value[0];
										var levlValue = value[1];
										var financialYrId = value[2];
										var schemeId = value[3];
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0);
									}
								}
							}
				        }
						},
						legend: {
							verticalAlign:'top',
							enabled: true
						},
						series: mainJosnObjArr
					});
					
				}
			}
	}
	getSchemeWiseOverviewDetails(0);
	function getSchemeWiseOverviewDetails(subProgId){
		var financialYearIdsList =[];
		financialYearIdsList.push(1);
		financialYearIdsList.push(3);
		financialYearIdsList.push(2);
		var deptIdsList=[];
		var sourceIdsList=[];
		var fromDateStr="01/01/1997";
		var toDateStr="01/01/2027";
		var glSearchLevelId=0;
		var glSearchLevelValue=[];
		var schemeIdsList = [];
		schemeIdsList.push(1);
		//schemeIdsList.push(2);
		//schemeIdsList.push(3);
		//schemeIdsList.push(4);
		var subProgIds = [];
		//subProgIds.push(subProgId);
		
		var json = {
		  financialYrIdList:financialYearIdsList,
		  deptIdsList : deptIdsList,
          sourceIdsList : sourceIdsList,
		  fromDateStr:fromDateStr,
		  toDateStr:toDateStr,
		  schemeIdsList:schemeIdsList,
		  subProgIds:subProgIds,
		  searchLevelId:glSearchLevelId,
		  searchLvlVals:glSearchLevelValue
		}
		$.ajax({
			url : "getSchemeWiseOverviewDetails",     
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
	
	function getAllSubLocationsBySuperLocationId(locationScopeId,divId,levelId){
		$("#"+divId).html('');
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		
		var sourceId = 0;
		var json = {
		  blockLevelId:levelId,
		  superLocationId : locationScopeId, 
		  deptIdsList : deptIdsArr,
		  sourceId : sourceId,
		  financialYrIdList : financialYrIdList,  
		  fromDateStr : glStartDate,       
		  toDateStr : glEndDate		  
		}
		$.ajax({
			url : "getAllSubLocationsBySuperLocationId",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){   
				if(result !=null && result.length>0){
					 $("#"+divId).append('<option value="0">ALL '+globalLevelObj[divId]+'</option>');
					for(var i in result){
						$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
				
				$("#"+divId).trigger('chosen:updated');
			}
		});
	}
	
	$(document).on("change",".distLevelCls1",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockType();
		var programId =$("#programNamesDistrict").val();
		var subProgramId =$("#subProgramNamesDistrict").val();
		var locationLevelType='';
		var locationId =0;
		if(blockType == "parliamentType"){
			locationLevelType = 9;
			locationId =$("#distLevelParliamentNames").val();
		}else{
			locationLevelType = 3;
			locationId =$("#distLevelDistrictNames").val();
		}
		getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("click",".sortingDivDistCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var programId =$("#programNamesDistrict").val();
		var subProgramId =$("#subProgramNamesDistrict").val();
		var blockType = getblockType();
		var locationLevelType='';
		var locationId =0;
		if(blockType == "parliamentType"){
			locationLevelType = 9;
			locationId =$("#distLevelParliamentNames").val();
		}else{
			locationLevelType = 3;
			locationId =$("#distLevelDistrictNames").val();
		}
		getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
	});	
	//Constituency
	$(document).on("change",".constiLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var locationId =0;
		var locationLevelType = 3;
		var locationId =$("#constLevelDistNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'constLevelConstNames',4);
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".parlaiLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var locationId =0;
		var locationLevelType = 9;
		var locationId =$("#constLevelParliaNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'constLevelConstNames',4);
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".constiLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var locationId =0;
		var locationLevelType = 4;
		var locationId =$("#constLevelConstNames").val();
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("click",".sortingDivConstCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var blockType = getblockTypeCons();
		
		var districtId = $("#constLevelDistNames").val();
		var parliamentId = $("#constLevelParliaNames").val();
		var constituencyId = $("#constLevelConstNames").val();
		var locationId = 0;
		var locationLevelType = 0;
		
		
		if(blockType == "parliamentType"){
			if(parliamentId != 0 && constituencyId == 0){
				locationId = parliamentId;
				locationLevelType =9;
			}else if(parliamentId != 0 && constituencyId != 0){
				locationId = constituencyId;
				locationLevelType =4;
			}else if(parliamentId ==0  && constituencyId == 0){
				locationLevelType =4;
			}
		}else if(blockType == "districtType"){
			if(districtId !=0 && constituencyId == 0){
				locationId = districtId;
				locationLevelType =3;
			}else if(districtId != 0 && constituencyId != 0){
				locationId = constituencyId;
				locationLevelType =4;
			}else if(districtId ==0  && constituencyId == 0){
				locationLevelType =4;
			}
		}
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
	});
	
	//Mandal
	$(document).on("change",".mandalLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var locationId =0;
		var locationLevelType = 3;
		var locationId =$("#mandalLevelDistNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelConstNames',5);
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".levelparliamentConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var locationId =0;
		var locationLevelType = 9;
		var locationId =$("#parliamentLevelConstNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelConstNames',5);
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".levelmandalConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var locationId =0;
		var locationLevelType = 4;
		var locationId =$("#mandalLevelConstNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelMandalNames',5);
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".mandalLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var locationId =0;
		var locationLevelType = 5;
		var locationId =$("#mandalLevelMandalNames").val();
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	
	$(document).on("click",".sortingDivMandalCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var blockType = getblockTypeCons();
		
		var districtId = $("#mandalLevelDistNames").val();
		var parliamentId = $("#parliamentLevelConstNames").val();
		var constituencyId = $("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		var locationId = 0;
		var locationLevelType = 0;
		
		
		if(blockType == "parliamentType"){
			if(parliamentId != 0 && constituencyId == 0 && mandalId == 0){
				locationId = parliamentId;
				locationLevelType =9;
			}else if(parliamentId != 0 && constituencyId != 0 && mandalId == 0){
				locationId = constituencyId;
				locationLevelType =4;
			}else if(parliamentId != 0 && constituencyId != 0 && mandalId != 0){
				locationId = mandalId;
				locationLevelType =5;
			}else if(parliamentId ==0  && constituencyId == 0 && mandalId == 0){
				locationLevelType =6;
			}
			
		}else if(blockType == "districtType"){
			if(districtId != 0 && constituencyId == 0 && mandalId == 0){
				locationId = districtId;
				locationLevelType =3;
			}else if(districtId != 0 && constituencyId != 0 && mandalId == 0){
				locationId = constituencyId;
				locationLevelType =4;
			}else if(districtId != 0 && constituencyId != 0 && mandalId != 0){
				locationId = mandalId;
				locationLevelType =5;
			}else if(districtId ==0  && constituencyId == 0 && mandalId == 0){
				locationLevelType =6;
			}
		}
		getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
	});
	//VILLAGE
	$(document).on("change",".villageLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var locationId =0;
		var locationLevelType = 3;
		var locationId =$("#villageLevelDistNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelConstNames',6);
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".villageLevelParliCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var locationId =0;
		var locationLevelType = 9;
		var locationId =$("#villageLeveParliNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelConstNames',6);
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".villageLevelConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var locationId =0;
		var locationLevelType = 4;
		var locationId =$("#villageLevelConstNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelMandalNames',6);
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".villageLevelMandalCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var locationId =0;
		var locationLevelType = 5;
		var locationId =$("#villageLevelMandalNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelNames',6);
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	$(document).on("change",".villageLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var locationId =0;
		var locationLevelType = 6;
		var locationId =$("#villageLevelNames").val();
		getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId);
			
	});
	