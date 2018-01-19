var globalLevelObj =  {"distLevelDistrictNames":"DISTRICT","constLevelDistNames":"DISTRICT","mandalLevelDistNames":"DISTRICT","constLevelConstNames":"CONSTITUENCY","mandalLevelConstNames":"CONSTITUENCY","mandalLevelMandalNames":"MANDAL","villageLevelDistNames":"DISTRICT",'villageLevelConstNames':'CONSTITUENCY','villageLevelMandalNames':'MANDAL','villageLevelNames':'VILLAGE','constLevelParliaNames':'PARLIAMENT','parliamentLevelConstNames':'PARLIAMENT','villageLeveParliNames':'PARLIAMENT','distLevelParliamentNames':'PARLIAMENT'};
var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var $windowWidth = $(window).width();
var globalLocationLevelTypeId=0;
var globalLocationId=0;
var globalLevelId=0;
var globalLocationLevelCompTypeId =0;
var globalLocationName='';
var districtIdForMenu = '';  
var districtNameForMenu = '';	
var consIdForMenu = '';
var consNameForMenu =''; 
$("#selectedName").attr("attr_levelidvalue",0)
$("#selectedName").attr("attr_id",0)
$("#selectedName").attr("attr_levelid",0)
/*$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});*/
getAllFiniancialYears();
getAllDepartments();
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
		$(".chosenSelect").chosen();
		var programId=0;
		var subProgramId=0;
		
		setTimeout(function(){ 
			getALlProgramesAmountDetails();	
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,0,0,'cumulative','onload',0);
			getGovtSchemesDetails("overviewSelect");
			getGovtSchemesDetails("programNamesState");
			getGovtSubProgramsDetails(0,"subProgramNamesState");
			getGovtGrantTypeDetails(programId,subProgramId, "grantTypeState");
		}, 1500);
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
	//District Collapse view
	$(document).on("click",".collapseActiveDistCls",function(){
		if(!$(this).hasClass("collapsed")){
			if(globalLevelId == 0){
				globalLocationLevelTypeId=3;
				globalLocationId=0;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
			}else{
				globalLocationLevelTypeId = globalLocationLevelTypeId;
				globalLocationId = globalLocationId;
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				var districtIdForMenuForDistrictBlock = "3"+globalLocationId;
				
				
				$("#distLevelDistrictNames").html('');
				$("#distLevelDistrictNames").append("<option value="+districtIdForMenuForDistrictBlock+">"+globalLocationName+" </option>");
				$("#distLevelDistrictNames").trigger("chosen:updated");
			}
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			
			getGovtSchemesDetails("programNamesDistrict");
			getGovtSubProgramsDetails(0,"subProgramNamesDistrict");
			var programId=0;
			var subProgramId=0;
			getGovtGrantTypeDetails(programId,subProgramId, "grantTypeDistrict");
			compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
	});
	//Constituency Collapse View
	$(document).on("click",".collapseActiveConstCls",function(){
		if(!$(this).hasClass("collapsed")){
			if(globalLevelId == 0){
				globalLocationLevelTypeId=3;
				globalLocationId=0;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
			}else{
				globalLocationLevelTypeId = globalLocationLevelTypeId
				globalLocationId = globalLocationId
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				
				if(globalLocationLevelTypeId == 3){
					var distrctIdForMenuForConsBlock = "3"+globalLocationId;
					$("#constLevelDistNames").html('');
					$("#constLevelDistNames").append("<option value="+distrctIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#constLevelDistNames").trigger("chosen:updated");
					
					getAllSubLocationsBySuperLocationId(distrctIdForMenuForConsBlock,'constLevelConstNames',4);
					
				}else if(globalLocationLevelTypeId == 4){
					var constituencyIdForMenuForConsBlock = "4"+globalLocationId;
					$("#constLevelDistNames").html('');
					$("#constLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#constLevelDistNames").trigger("chosen:updated");
					
					$("#constLevelConstNames").html('');
					$("#constLevelConstNames").append("<option value="+constituencyIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#constLevelConstNames").trigger("chosen:updated");
				}
				
			}
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			
			getGovtSchemesDetails("programNamesConst");
			getGovtSubProgramsDetails(0,"subProgramNamesConst");
			var programId=0;
			var subProgramId=0;
			getGovtGrantTypeDetails(programId,subProgramId, "grantTypeConst");
			compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
	});
	//Mandal Collapse View
	$(document).on("click",".collapseActiveMandalCls",function(){
		if(!$(this).hasClass("collapsed")){
			if(globalLevelId == 0){
				globalLocationLevelTypeId=5;
				globalLocationId=0;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
			}else{
				globalLocationLevelTypeId = globalLocationLevelTypeId
				globalLocationId = globalLocationId
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				
				if(globalLocationLevelTypeId == 3){
					var districtIdForMenuForConsBlock1 = "3"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenuForConsBlock1+">"+globalLocationName+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					
					getAllSubLocationsBySuperLocationId(districtIdForMenuForConsBlock1,'mandalLevelConstNames',5);
					
					
					
					$("#mandalLevelMandalNames").html('');
					$("#mandalLevelMandalNames").trigger("chosen:updated");
				}
				else if(globalLocationLevelTypeId == 4){
					var mandalIdForMenuForConsBlock1 = "4"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					
					$("#mandalLevelConstNames").html('');
					$("#mandalLevelConstNames").append("<option value="+mandalIdForMenuForConsBlock1+">"+globalLocationName+" </option>");
					$("#mandalLevelConstNames").trigger("chosen:updated");
					
					getAllSubLocationsBySuperLocationId(mandalIdForMenuForConsBlock1,'mandalLevelMandalNames',5);
					
				}else if(globalLocationLevelTypeId == 5){
					var mandalIdForMenuForConsBlock = "5"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					
					$("#mandalLevelConstNames").html('');
					$("#mandalLevelConstNames").append("<option value="+consIdForMenu+">"+consNameForMenu+" </option>");
					$("#mandalLevelConstNames").trigger("chosen:updated");
					
					$("#mandalLevelMandalNames").html('');
					$("#mandalLevelMandalNames").append("<option value="+mandalIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#mandalLevelMandalNames").trigger("chosen:updated");
				}
				
				
				
			}
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			
			getGovtSchemesDetails("programNamesMandal");
			getGovtSubProgramsDetails(0,"subProgramNamesMandal");
			var programId=0;
			var subProgramId=0;
			getGovtGrantTypeDetails(programId,subProgramId, "grantTypeMandal");
			compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
	});
	//VILLAGE Collapse View
	$(document).on("click",".collapseActiveVillageCls",function(){
		if(!$(this).hasClass("collapsed")){
			if(globalLevelId == 0){
				globalLocationLevelTypeId=6;
				globalLocationId=0;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);
			}else{
				globalLocationLevelTypeId = globalLocationLevelTypeId
				globalLocationId = globalLocationId
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				if(globalLocationLevelTypeId == 3){
					var districtIdForMenuForConsBlock2 = "3"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenuForConsBlock2+">"+globalLocationName+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					
					getAllSubLocationsBySuperLocationId(districtIdForMenuForConsBlock2,'villageLevelConstNames',6);
					
					$("#villageLevelMandalNames").html('');
					$("#villageLevelMandalNames").trigger("chosen:updated");
				}
				else if(globalLocationLevelTypeId == 4){
					var mandalIdForMenuForConsBlock2 = "5"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					
					$("#villageLevelConstNames").html('');
					$("#villageLevelConstNames").append("<option value="+mandalIdForMenuForConsBlock2+">"+globalLocationName+" </option>");
					$("#villageLevelConstNames").trigger("chosen:updated");
					
					getAllSubLocationsBySuperLocationId(mandalIdForMenuForConsBlock2,'villageLevelMandalNames',6);
					
				}else if(globalLocationLevelTypeId == 5){
					var mandalIdForMenuForConsBlock = "5"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					
					$("#villageLevelConstNames").html('');
					$("#villageLevelConstNames").append("<option value="+consIdForMenu+">"+consNameForMenu+" </option>");
					$("#villageLevelConstNames").trigger("chosen:updated");
					
					$("#villageLevelMandalNames").html('');
					$("#villageLevelMandalNames").append("<option value="+mandalIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#villageLevelMandalNames").trigger("chosen:updated");
				}
			}
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
					
			getGovtSchemesDetails("programNamesVillage");
			getGovtSubProgramsDetails(0,"subProgramNamesVillage");
			var programId=0;
			var subProgramId=0;
			getGovtGrantTypeDetails(programId,subProgramId, "grantTypeVillage");			
			compareFundsBetweenFinancialYears(6,'comparionVillageLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
	});
	//Program Onchange
	$(document).on("change","#programNamesState",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	  getGovtSubProgramsDetails(programId,"subProgramNamesState");
	  getGovtGrantTypeDetails(programId,subProgramId, "grantTypeState");
		var blockType ='';
		$("[role='tabCummulative'] li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0)
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0)
		}
	  
	});
	//district
	$(document).on("change","#programNamesDistrict",function(){
	  var programId = $(this).val();
	  $("#distLevelDistrictNames").val(0);
	  $("#distLevelDistrictNames").trigger("chosen:updated");
	  $("#distLevelParliamentNames").val(0);
	  $("#distLevelParliamentNames").trigger("chosen:updated");
	  /* $(".tabCummulativeDistrict li").removeClass("active");
	  $(".ActiveDistrictCls").addClass("active"); */
	  var subProgramId =0;
	  getGovtSubProgramsDetails(programId,"subProgramNamesDistrict");
	  getGovtGrantTypeDetails(programId,subProgramId, "grantTypeDistrict");
		var blockType ='';
		$(" .tabCummulativeDistrict li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
		}
	});
	//constituency
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
		  $("#constLevelConstNames").trigger("chosen:updated");
	  }
	  
	  var blockTypeCons ='';
		$(" .tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockTypeCons = $(this).attr("attr_type");
			 }
			
		});
	 getGovtSubProgramsDetails(programId,"subProgramNamesConst");
	 getGovtGrantTypeDetails(programId,subProgramId,"grantTypeConst");
	
	 if(blockTypeCons == "normalView"){
		 getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
	 }else if(blockTypeCons == "cummulativeView"){
		 getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
	 }
	 
		
	});
	//mandal
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
		 // $("#mandalLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#mandalLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#mandalLevelMandalNames").val();
	  if(id1>0){
		  $("#mandalLevelMandalNames").html('');
		 // $("#mandalLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#mandalLevelMandalNames").trigger("chosen:updated");
	  }
	   var blockType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		
	 getGovtSubProgramsDetails(programId,"subProgramNamesMandal");
	 getGovtGrantTypeDetails(programId,subProgramId,"grantTypeMandal");
	 if(blockType == "normalView"){
		  getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
	}else if(blockType == "cummulativeView"){
		  getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
	 }
	
	});
	//village
	$(document).on("change","#programNamesVillage",function(){
	  var programId = $(this).val();
	  var subProgramId =0;
	  $("#villageLevelDistNames").val(0);
	  $("#villageLeveParliNames").val(0);
	  $("#villageLevelDistNames").trigger("chosen:updated");
	  $("#villageLeveParliNames").trigger("chosen:updated");
	  var id = $("#villageLevelConstNames").val();
	 /*  $(".tabCummulativeVillage li").removeClass("active");
	  $(".ActiveVillageCls").addClass("active"); */
	  if(id>0){
		  $("#villageLevelConstNames").html('');
		 // $("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#villageLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#villageLevelMandalNames").val();
	  if(id1>0){
		  $("#villageLevelMandalNames").html('');
		 // $("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#villageLevelMandalNames").trigger("chosen:updated");
	  }
	  var id2 = $("#villageLevelNames").val();
	  if(id2>0){
		  $("#villageLevelNames").html('');
		//  $("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		  $("#villageLevelNames").trigger("chosen:updated");
	  }
		getGovtSubProgramsDetails(programId,"subProgramNamesVillage");
		getGovtGrantTypeDetails(programId,subProgramId,"grantTypeVillage");
	  var blockType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		 if(blockType == "normalView"){
			 getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else if(blockType == "cummulativeView"){
			 getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
		 }
	 
	});
	//SubProgram Onchange 
	$(document).on("change","#subProgramNamesState",function(){
	  var subProgramId = $(this).val();
	  var programId = $("#programNamesState").val();
	  var blockType ='';
		$("[role='tabCummulative'] li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0)
		}
		//getGovtSchemsTypeDetails(programId,subProgramId, "grantTypeState");
	  
	});
	//district
	$(document).on("change","#subProgramNamesDistrict",function(){
	 var subProgramId = $(this).val();
	 var programId = $("#programNamesDistrict").val();
	  $("#distLevelDistrictNames").val(0);
	  $("#distLevelDistrictNames").trigger("chosen:updated");
	  $("#distLevelParliamentNames").val(0);
	  $("#distLevelParliamentNames").trigger("chosen:updated");
	  /* $(".tabCummulativeDistrict li").removeClass("active");
	  $(".ActiveDistrictCls").addClass("active"); */
		var blockType ='';
		$(".tabCummulativeDistrict li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
		}
	  
	  //getGovtGrantTypeDetails(programId,subProgramId,"grantTypeDistrict");
	});
	//constituency
	$(document).on("change","#subProgramNamesConst",function(){
	 var subProgramId = $(this).val();
	 var programId = $("#programNamesConst").val();
	 /*  $(".tabCummulativeConstituency li").removeClass("active");
	  $(".ActiveConstituencyCls").addClass("active"); */
	  $("#constLevelDistNames").val(0);
	  $("#constLevelParliaNames").val(0);
	  $("#constLevelDistNames").trigger("chosen:updated");
	  $("#constLevelParliaNames").trigger("chosen:updated");
	  var id = $("#constLevelConstNames").val();
	  if(id>0){
		  $("#constLevelConstNames").html('');
		  $("#constLevelConstNames").trigger("chosen:updated");
	  }
	  var blockType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
	if(blockType == "normalView"){
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
	}else if(blockType == "cummulativeView"){
		getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
	}	
	 //getGovtGrantTypeDetails(programId,subProgramId, "grantTypeConst");
	});
	
	//mandal
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
		  $("#mandalLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#mandalLevelMandalNames").val();
	  if(id1>0){
		  $("#mandalLevelMandalNames").html('');
		  $("#mandalLevelMandalNames").trigger("chosen:updated");
	  }
	   var blockType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
		}
	  //getGovtGrantTypeDetails(programId,subProgramId, "grantTypeMandal");
	});
	//village
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
		  //$("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		  $("#villageLevelConstNames").trigger("chosen:updated");
	  }
	  var id1 = $("#villageLevelMandalNames").val();
	  if(id1>0){
		  $("#villageLevelMandalNames").html('');
		 // $("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		  $("#villageLevelMandalNames").trigger("chosen:updated");
		  
	  }
	  var id2 = $("#villageLevelNames").val();
	  if(id2>0){
		  $("#villageLevelNames").html('');
		  //$("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		  $("#villageLevelNames").trigger("chosen:updated");
		  
	  }
	   var blockType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onLoad',0);
		}
	  //getGovtGrantTypeDetails(programId,subProgramId, "grantTypeVillage");
	});
	//Schems Build
	$(document).on("change","#grantTypeState",function(){
	  var grantType = $(this).val();
	  var programId = $("#programNamesState").val();
	  var blockType ='';
		$("[role='tabCummulative'] li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,0,"",'onload',grantType);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,0,"cumulative",'onload',grantType)
		}
		getGovtSchemsTypeDetails(programId,grantType, "subProgramNamesState");
	  
	});
	$(document).on("change","#grantTypeDistrict",function(){
	  var grantType = $(this).val();
	  var programId = $("#programNamesDistrict").val();
	  var blockType ='';
		$("[role='tabCummulativeDistrict'] li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"",'onload',grantType);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"cumulative",'onload',grantType);
		}
		getGovtSchemsTypeDetails(programId,grantType, "subProgramNamesDistrict");
	  
	});
	$(document).on("change","#grantTypeConst",function(){
	  var grantType = $(this).val();
	  var programId = $("#programNamesConst").val();
	   var blockType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"",'onload',grantType);
		}else if(blockType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"cumulative",'onload',grantType);
		}	
		getGovtSchemsTypeDetails(programId,grantType, "subProgramNamesConst");
	  
	});
	$(document).on("change","#grantTypeMandal",function(){
	  var grantType = $(this).val();
	  var programId = $("#programNamesMandal").val();
	  var blockType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"",'onload',0);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"cumulative",'onload',0);
		}	
		getGovtSchemsTypeDetails(programId,grantType, "subProgramNamesMandal");
	  
	});
	$(document).on("change","#grantTypeVillage",function(){
	  var grantType = $(this).val();
	  var programId = $("#programNamesVillage").val();
	  var blockType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  blockType = $(this).attr("attr_type");
			 }
			
		});
		if(blockType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"",'onload',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,0,"cumulative",'onLoad',grantType);
		}	
		getGovtSchemsTypeDetails(programId,grantType, "subProgramNamesVillage");
	  
	});
	$(document).on("click",".stateProSubProCls",function(){
		var blockName = $(this).attr("attr_switch");
		if(blockName == "stateLevel"){
			$(".showHideStatePgramsCls").toggle();
		}else if(blockName == "distLevel"){
			$(".showHideDistrictPgramsCls").toggle();
		}else if(blockName == "consLevel"){
			$(".showHideConstituencyPgramsCls").toggle();
		}else if(blockName == "mandalLevel"){
			$(".showHideMandalPgramsCls").toggle();
		}else if(blockName == "villageLevel"){
			$(".showHideVillagePgramsCls").toggle();
		}
	});	
	$(document).on("click","[role='tabCummulative'] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("attr_switch");
		var blockType = $(this).attr("attr_type");
		
			
		if(blockName == 'stateLevel'){
			var programId = $("#programNamesState").val();
			var subProgramId = $("#subProgramNamesState").val();
			if(blockType == "normalView"){
				getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,'','onload',0);
			}else{
				getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,'cumulative','onload',0);
			}
			
		}else if(blockName == 'distLevel'){
			var programId = $("#programNamesDistrict").val();
			var subProgramId = $("#subProgramNamesDistrict").val();
			$("#distLevelDistrictNames").val(0);
			$("#distLevelDistrictNames").trigger("chosen:updated");
			$("#distLevelParliamentNames").val(0);
			$("#distLevelParliamentNames").trigger("chosen:updated");
			if(blockType == "normalView"){
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
			}else{
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
			}
			
		}else if(blockName == 'consLevel'){
			var programId = $("#programNamesConst").val();
			var subProgramId = $("#subProgramNamesConst").val();
			$("#constLevelDistNames").val(0);
			$("#constLevelDistNames").trigger("chosen:updated");
			$("#constLevelParliaNames").val(0);
			$("#constLevelParliaNames").trigger("chosen:updated");
			$("#constLevelConstNames").html('');
			$("#constLevelConstNames").trigger("chosen:updated");
			
			if(blockType == "cummulativeView"){
				
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
			}else{
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
			}
		}else if(blockName == 'mandalLevel'){
			var programId = $("#programNamesMandal").val();
			var subProgramId = $("#subProgramNamesMandal").val();
			$("#mandalLevelDistNames").val(0);
			$("#mandalLevelDistNames").trigger("chosen:updated");
			$("#parliamentLevelConstNames").val(0);
			$("#parliamentLevelConstNames").trigger("chosen:updated");
			$("#mandalLevelConstNames").html('');
			$("#mandalLevelConstNames").trigger("chosen:updated");
			$("#mandalLevelMandalNames").html('');
			$("#mandalLevelMandalNames").trigger("chosen:updated");
			if(blockType == "normalView"){
				
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
			}else{
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
			}
		}else if(blockName == 'villageLevel'){
			var programId = $("#programNamesVillage").val();
			var subProgramId = $("#subProgramNamesVillage").val();
			$("#villageLevelDistNames").val(0);
			$("#villageLevelDistNames").trigger("chosen:updated");
			$("#villageLeveParliNames").val(0);
			$("#villageLeveParliNames").trigger("chosen:updated");
			$("#villageLevelNames").html('');
			$("#villageLevelNames").trigger("chosen:updated")
			$("#villageLevelMandalNames").html('');
			$("#villageLevelMandalNames").trigger("chosen:updated")
			$("#villageLevelConstNames").html('');
			$("#villageLevelConstNames").trigger("chosen:updated")
			if(blockType == "normalView"){
				
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
			}else{
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
			}
		}
	});
	$(document).on("click","[role='tablist'] li",function(){
			var cummulativeType ='';
			var blockName = $(this).closest("ul").attr("attr_switch");
			
			
			$("[role='tabCummulative'] li").each(function(i, obj){
				 if($(this).hasClass("active")){
					  cummulativeType = $(this).attr("attr_type");
				 }
				
			});
			if(blockName == 'stateLevel'){
				var programId = $("#programNamesState").val();
				var subProgramId = $("#subProgramNamesState").val();
				if(cummulativeType == "normalView"){
					getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,'','onload',0);
				}else{
					getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,'cumulative','onload',0);
				}
			}else if(blockName == 'distLevel'){
				var programId = $("#programNamesDistrict").val();
				var subProgramId = $("#subProgramNamesDistrict").val();
				if(cummulativeType == "normalView"){
					getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
				}else{
					getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
				}
			}else if(blockName == 'consLevel'){
				var programId = $("#programNamesConst").val();
				var subProgramId = $("#subProgramNamesConst").val();
				if(cummulativeType == "normalView"){
					getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
				}else{
					getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
				}
			}else if(blockName == 'mandalLevel'){
				var programId = $("#programNamesMandal").val();
				var subProgramId = $("#subProgramNamesMandal").val();
				if(cummulativeType == "normalView"){
					getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
				}else{
					getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
				}
			}else if(blockName == 'villageLevel'){
				var programId = $("#programNamesVillage").val();
				var subProgramId = $("#subProgramNamesVillage").val();
				if(cummulativeType == "normalView"){
					getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"",'onload',0);
				}else{
					getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'onload',0);
				}
			}
	});
	//tab click
	$(document).on("click","[tab-switch] li",function(){
		
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var blockName = $(this).closest("ul").attr("tab-switch");
		var blockType = $(this).attr("attr_type");
		
		if(blockName == 'distLevel'){
			$(".tabCummulativeDistrict li").removeClass("active");
			$(".ActiveDistrictCls").addClass("active");
			if(blockType == "parliamentType"){
				if(globalLevelId == 0){
					globalLocationLevelTypeId=9;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				$(".distLevelparliamentCls").show();
				$(".distLevelCls").hide();
				emptyProgramSubProgramDistVal()
				getAllSubLocationsBySuperLocationId(11,'distLevelParliamentNames',3);
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}else{
				if(globalLevelId == 0){
					globalLocationLevelTypeId=3;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				$(".distLevelCls").show();
				$("#distLevelDistrictNames").val(0);
				$("#distLevelDistrictNames").trigger('chosen:updated');
				emptyProgramSubProgramDistVal()
				$(".distLevelparliamentCls").hide();
				getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}
			
		}else if(blockName == 'consLevel'){
			$(".tabCummulativeConstituency li").removeClass("active");
			$(".ActiveConstituencyCls").addClass("active");
			$("#constLevelConstNames").val(0);
			$("#constLevelConstNames").trigger('chosen:updated');
			if(blockType == "parliamentType"){
				if(globalLevelId == 0){
					globalLocationLevelTypeId=9;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				emptyProgramSubProgramConstVal()
				getAllSubLocationsBySuperLocationId(11,'constLevelParliaNames',4);
				$(".constiLevelDistCls").hide();
				$(".parlaiLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}else{
				if(globalLevelId == 0){
					globalLocationLevelTypeId=4;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				$("#constLevelDistNames").val(0);
				$("#constLevelDistNames").trigger('chosen:updated');
				emptyProgramSubProgramConstVal()
				$(".parlaiLevelDistCls").hide();
				$(".constiLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}
			 
		}else if(blockName == 'mandalLevel'){
			$(".tabCummulativeMandal li").removeClass("active");
			$(".ActiveMandalCls").addClass("active");
			if(blockType == "parliamentType"){
				 if(globalLevelId == 0){
					globalLocationLevelTypeId=9;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = $("#selectedName").attr("attr_levelidvalue")
					globalLocationId = $("#selectedName").attr("attr_levelidvalue")
				}
				emptyProgramSubProgramMandalVal()
				getAllSubLocationsBySuperLocationId(11,'parliamentLevelConstNames',5);
				$(".mandalLevelDistCls").hide();
				$(".levelparliamentConstiCls").show();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}else{
				if(globalLevelId == 0){
					globalLocationLevelTypeId=5;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				} 
				$("#mandalLevelDistNames").val(0);
				$("#mandalLevelDistNames").trigger('chosen:updated');
				 
				emptyProgramSubProgramMandalVal()
				$(".levelparliamentConstiCls").hide();
				$(".mandalLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}
		}else if(blockName == 'villageLevel'){
			$(".tabCummulativeVillage li").removeClass("active");
			$(".ActiveVillageCls").addClass("active");
			if(blockType == "parliamentType"){
				if(globalLevelId == 0){
					globalLocationLevelTypeId=9
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				emptyProgramSubProgramVillageVal()
				getAllSubLocationsBySuperLocationId(11,'villageLeveParliNames',6);
				
				$(".villageLevelDistCls").hide();
				$(".villageLevelParliCls").show();
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}else{
				if(globalLevelId == 0){
					globalLocationLevelTypeId=6;
					globalLocationId=0;
				}else{
					globalLocationLevelTypeId = globalLocationLevelTypeId
					globalLocationId = globalLocationId
				}
				$("#villageLevelDistNames").val(0);
				$("#villageLevelDistNames").trigger('chosen:updated');
				
				emptyProgramSubProgramVillageVal()
				$(".villageLevelParliCls").hide();
				$(".villageLevelDistCls").show();
				getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			}
		}
		
		// hiding select box in the case of MGNREGS department.
		  var deptIdArr = $("#DepartmentsId").val();
		  var deptId = 0;
		  if (deptIdArr != null && deptIdArr.length==1){
			 deptId = deptIdArr[0];
		  }
		  if (deptId == 3){//In the case MGNREGS we are hiding select box.
			 $(".selectBoxCommonCls").hide();
		  }else{
			  if(blockName == 'distLevel'){
				  if(blockType == "parliamentType"){
					  $("#distLevelDistrictNames_chosen").hide();
				  }else{
					  $("#distLevelParliamentNames_chosen").show();
					  $("#distLevelDistrictNames_chosen").show();
				  }
			  }
		  }
	});
	var overviewSelectBoxData = '';
	function getGovtSchemesDetails(divId){
		$("#"+divId).html('');
		if(divId == 'overviewSelect'){
			$("#overViewTableId").html(spinner);
		}
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
			var departmentArr = $("#DepartmentsId").val();
			for(var i in departmentArr){
				if(departmentArr[i] == 3 || departmentArr[i] == 0){
					if(departmentArr.length == 1 && departmentArr[i] == 3){
						result = [];
					}
					if(divId == "overviewSelect"){
						result.push({"id":"-1","name":"MGNREGS"})
					}
					
				}
			}
			
			if(divId == "overviewSelect"){
				overviewSelectBoxData = result;
			}
			var str='';
			if(result !=null && result.length>0){
				  str+='<option value="0">ALL</option>';
					for(var i in result){
						 str+='<option value="'+result[i].id+'">'+result[i].name+' </option>';
					}
					
				}
				$("#"+divId).html(str);
				$("#"+divId).trigger('chosen:updated');
			
			
			
			if(divId == 'overviewSelect')
			{
				$("#overViewTableId").html(spinner);
				var overviewTable = '';
				overviewTable+='<div class="col-sm-12">';
					overviewTable+='<div class="panel panel-default">';
						overviewTable+='<div class="panel-body">';
							overviewTable+='<div class="">';
								overviewTable+='<table class="table">';
									overviewTable+='<thead>';
										overviewTable+='<th><select id="overviewSelect" >';
											overviewTable+='<option value="0">ALL PROGRAMMES </option>';
											for(var i in result){
												overviewTable+='<option value="'+result[i].id+'">'+result[i].name+' </option>';
											}
										overviewTable+='</select></th>';
										overviewTable+='<th class="text-center"><span class="rounded-circle fa fa-inr"></span><p class="text-capital">total amount</p></th>';
										overviewTable+='<th><span class="rounded-circle">D</span><p class="text-capital m_top10">district</p></th>';
										overviewTable+='<th><span class="rounded-circle">C</span><p class="text-capital m_top10">constituency</p></th>';
										overviewTable+='<th><span class="rounded-circle">M</span><p class="text-capital m_top10">Mandal</p></th>';
									overviewTable+='</thead>';
									overviewTable+='<tbody>';
										overviewTable+='<tr id="blockProgram0">';
											overviewTable+='<td class="text-capital clearCls" style="border-right:2px dashed #777;"><h4>ALL </h4></td>';
											overviewTable+='<td class="total0 text-center clearCls"></td>';
											overviewTable+='<td class="district0 clearCls"></td>';
											overviewTable+='<td class="constituency0 clearCls"></td>';
											overviewTable+='<td class="mandal0 clearCls"></td>';
										overviewTable+='</tr>';
										var k = 1;					
										for(var i in result)
										{
											overviewTable+='<tr id="blockProgram'+result[i].id+'">';
												overviewTable+='<td class="text-capital clearCls" style="border-right:2px dashed #777;"><h4>'+result[i].name+'</h4></td>';
												overviewTable+='<td class="total'+result[i].id+' text-center clearCls"></td>';
												overviewTable+='<td class="district'+result[i].id+' clearCls"></td>';
												overviewTable+='<td class="constituency'+result[i].id+' clearCls"></td>';
												overviewTable+='<td class="mandal'+result[i].id+' clearCls"></td>';
											overviewTable+='</tr>';
											k = k +1;
										}
									overviewTable+='</tbody>';
								overviewTable+='</table>';
							overviewTable+='</div>';
						overviewTable+='</div>';
					overviewTable+='</div>';
				overviewTable+='</div>';
					$("#overViewTableId").html(overviewTable);
					$("#overviewSelect").chosen();
					getSchemeWiseOverviewDetails(0,0);
					for(var i in result)
					{
						var subprogramId = result[i].id
						getSchemeWiseOverviewDetails(subprogramId,subprogramId);
					} 
			}
		});
	  }
	  
	
	
	function emptyProgramSubProgramDistVal(){
		$("#programNamesDistrict").val(0);
		$("#programNamesDistrict").trigger('chosen:updated');
		$("#subProgramNamesDistrict").val(0);
		$("#subProgramNamesDistrict").trigger('chosen:updated');
		$("#grantTypeDistrict").val(0);
		$("#grantTypeDistrict").trigger('chosen:updated');
	}
	function emptyProgramSubProgramConstVal(){
		$("#programNamesConst").val(0);
		$("#programNamesConst").trigger('chosen:updated');
		$("#subProgramNamesConst").val(0);
		$("#subProgramNamesConst").trigger('chosen:updated');
		$("#grantTypeConst").val(0);
		$("#grantTypeConst").trigger('chosen:updated');
	}
	function emptyProgramSubProgramVillageVal(){
		$("#programNamesVillage").val(0);
		$("#programNamesVillage").trigger('chosen:updated');
		$("#subProgramNamesVillage").val(0);
		$("#subProgramNamesVillage").trigger('chosen:updated');
		$("#grantTypeMandal").val(0);
		$("#grantTypeMandal").trigger('chosen:updated');
		$("#villageLevelNames").html('');
		//$("#villageLevelNames").append('<option value="0">ALL VILLAGE</option>');	
		$("#villageLevelNames").trigger("chosen:updated");
		$("#villageLevelMandalNames").html('');
		//$("#villageLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
		$("#villageLevelMandalNames").trigger("chosen:updated");
		$("#villageLevelConstNames").html('');
		//$("#villageLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
		$("#villageLevelConstNames").trigger("chosen:updated");
	}
	function emptyProgramSubProgramMandalVal(){
		$("#programNamesMandal").val(0);
		$("#programNamesMandal").trigger('chosen:updated');
		$("#subProgramNamesMandal").val(0);
		$("#subProgramNamesMandal").trigger('chosen:updated');
		$("#grantTypeVillage").val(0);
		$("#grantTypeVillage").trigger('chosen:updated');
		$("#mandalLevelMandalNames").html('');
	   // $("#mandalLevelMandalNames").append('<option value="0">ALL MANDAL</option>');	
	    $("#mandalLevelMandalNames").trigger("chosen:updated");
	    $("#mandalLevelConstNames").html('');
	    //$("#mandalLevelConstNames").append('<option value="0">ALL CONSTITUENCY</option>');	
	    $("#mandalLevelConstNames").trigger("chosen:updated");
	}
	  
	  function getGovtSchemsTypeDetails(programId,grantTypeId,divId){	  
		$("#"+divId).html('');
		var json = {
			programId:programId,
			grantTypeId:grantTypeId
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtSchemsTypeDetails',  
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){

		  if(result !=null && result.length>0){
				 $("#"+divId).append('<option value="0">ALL</option>');
				for(var i in result){
					$("#"+divId).append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
				}
			}
		
			$("#"+divId).trigger('chosen:updated');
		});
	  }
	  
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
		var str='';
		  if(result !=null && result.length>0){
				 str+='<option value="0">ALL</option>';
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].name+' </option>';
				}
			}
			$("#"+divId).html(str);
			$("#"+divId).trigger('chosen:updated');
		});
	  }
	var financialArrGlob=[];
	financialArrGlob.push("0");
	//console.log("glob -- "+financialArrGlob);
	$(document).on("change","#financialYearId",function(){//ara
	/* $('#tabVill a[href="#villageLevelTable"]').trigger('click');
	$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
	$('#tabSt a[href="#stateLevelTable"]').trigger('click');
	$('#tabDis a[href="#distLevelTable"]').trigger('click');
	$('#tabCons a[href="#consLevelTable"]').trigger('click'); */
	$(".tabCummulativeDistrict li").removeClass("active");
	$(".ActiveDistrictCls").addClass("active"); 
	$(".tabCummulativeVillage li").removeClass("active");
	$(".ActiveVillageCls").addClass("active"); 
	$(".tabCummulativeConstituency li").removeClass("active");
	$(".ActiveConstituencyCls").addClass("active"); 
	$(".tabCummulativeStateCls li").removeClass("active");
	$(".ActiveStateCls").addClass("active"); 
	
		$(".distLevelActive li").removeClass("active");
		$(".districtActiveCls").addClass("active"); 
		
		$(".consLevelActive li").removeClass("active");
		$(".constituencyActiveCls").addClass("active"); 
		
		$(".mandalLevelActive li").removeClass("active");
		$(".mandalActiveCls").addClass("active"); 
		
		$(".villageLevelActive li").removeClass("active");
		$(".villageActiveCls").addClass("active"); 
		//district
		$(".distLevelparliamentCls").hide();
		getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
		
		//Constituency
		$(".parlaiLevelDistCls").hide();
		$("#constLevelConstNames").html('');
		$("#constLevelConstNames").trigger("chosen:updated");
		getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
			
		
		//mandal
		$(".levelparliamentConstiCls").hide(); 
		getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
		$("#mandalLevelConstNames").html('');
		$("#mandalLevelConstNames").trigger("chosen:updated");
		
		$("#mandalLevelMandalNames").html('');
		$("#mandalLevelMandalNames").trigger("chosen:updated");
		
		//VILLAGE
		$(".villageLevelParliCls").hide();
		getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);	
		$("#villageLevelConstNames").html('');
		$("#villageLevelConstNames").trigger("chosen:updated");
		
		$("#villageLevelMandalNames").html('');
		$("#villageLevelMandalNames").trigger("chosen:updated");
		
		$("#villageLevelNames").html('');
		$("#villageLevelNames").trigger("chosen:updated");
		$(".showHideConstituencyPgramsCls").hide();
		$(".showHideDistrictPgramsCls").hide();
		$(".showHideVillagePgramsCls").hide();
		$(".showHideMandalPgramsCls").hide();
		
	$(".collapseActiveVillageCls").addClass("collapsed");
	$(".collapseActiveMandalCls").addClass("collapsed");
	$(".collapseActiveConstCls").addClass("collapsed");
	$(".collapseActiveDistCls").addClass("collapsed");
	$("#collapseTwo").removeClass("in");		
	$("#collapseThree").removeClass("in");		
	$("#collapseFour").removeClass("in");		
	$("#collapseFive").removeClass("in");	
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
		//financialArrGlob = values;
		
	});
	/* $("#DepartmentsId").chosen().on("change", function(event, params) {
	  var value = $(this).val();
	  var changedValue = params.selected || params.deselected;
     console.log(value);
     console.log("Selected Value"+params.selected);
     console.log("previous selected Value"+);
	}); */
	
	var departmentArrGlob =[];
	departmentArrGlob.push("0");
	var departmentArrGlob1 =[];
	departmentArrGlob1.push("3");
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
			 /* $('#tabVill a[href="#villageLevelTable"]').trigger('click');
			$('#tabMan a[href="#mandalLevelTable"]').trigger('click');
			$('#tabSt a[href="#stateLevelTable"]').trigger('click');
			$('#tabDis a[href="#distLevelTable"]').trigger('click');
			$('#tabCons a[href="#consLevelTable"]').trigger('click'); */
			$(".tabCummulativeDistrict li").removeClass("active");
			$(".ActiveDistrictCls").addClass("active"); 
			$(".tabCummulativeVillage li").removeClass("active");
			$(".ActiveVillageCls").addClass("active"); 
			$(".tabCummulativeConstituency li").removeClass("active");
			$(".ActiveConstituencyCls").addClass("active"); 
			$(".tabCummulativeStateCls li").removeClass("active");
			$(".ActiveStateCls").addClass("active"); 
			
			$(".distLevelActive li").removeClass("active");
			$(".districtActiveCls").addClass("active"); 
			
			$(".consLevelActive li").removeClass("active");
			$(".constituencyActiveCls").addClass("active"); 
			
			$(".mandalLevelActive li").removeClass("active");
			$(".mandalActiveCls").addClass("active"); 
			
			$(".villageLevelActive li").removeClass("active");
			$(".villageActiveCls").addClass("active"); 
			$(".showHideConstituencyPgramsCls").hide();
			$(".showHideDistrictPgramsCls").hide();
			$(".showHideVillagePgramsCls").hide();
			$(".showHideMandalPgramsCls").hide();
			
			var firstIndexVal=0;
			var secondIndexVal=0;
			
			if(values !=null){
				if(values.length == 2 || values.length==1){
					if(values.length == 2){
						for(var i in values){
							if(i == 0){
								firstIndexVal=values[i];
							}else{
								secondIndexVal=values[i];
							}
						} 
						if(firstIndexVal == 0 && secondIndexVal == 3){
							$(".advancedSearchStyle").hide();
							$(".comaprisionViewShow").hide();
							$(".showHideStatePgramsCls").hide();
							$(".showHideDistrictPgramsCls").hide();
							$(".showHideConstituencyPgramsCls").hide();
							$(".showHideMandalPgramsCls").hide();
							$(".showHideVillagePgramsCls").hide();
							$(".selectBoxCommonCls").hide();
							$(".panchyatLevelYearWiseViewCls").hide();
							$("#financialYearId").html('');
							//var finalYearArr =[{"financialYearId":"4","financialYear":"2017-2018"}];
							//buildFinancialYearRlst(finalYearArr);
							getAllFiniancialYears();
						}else {
						   $(".advancedSearchStyle").show();	
						   $(".comaprisionViewShow").show();
							$(".showHideStatePgramsCls").show();
							$(".showHideDistrictPgramsCls").show();
							$(".showHideConstituencyPgramsCls").show();
							$(".showHideMandalPgramsCls").show();
							$(".showHideVillagePgramsCls").show();
							$(".selectBoxCommonCls").show();
							$(".panchyatLevelYearWiseViewCls").show();
							getAllFiniancialYears();
						}
					}
				
					if(values.length == 1){
						for(var i in values){
						  firstIndexVal=values[i];
						} 
						if(firstIndexVal == 3){
								$(".advancedSearchStyle").hide();
								$(".comaprisionViewShow").hide();
								$(".showHideStatePgramsCls").hide();
								$(".showHideDistrictPgramsCls").hide();
								$(".showHideConstituencyPgramsCls").hide();
								$(".showHideMandalPgramsCls").hide();
								$(".showHideVillagePgramsCls").hide();
								$(".selectBoxCommonCls").hide();
								$(".panchyatLevelYearWiseViewCls").hide();
								$("#financialYearId").html('');
								var finalYearArr =[{"financialYearId":"4","financialYear":"2017-2018"}];
								  buildFinancialYearRlst(finalYearArr);
						}else {
								 $(".advancedSearchStyle").show();
								 $(".comaprisionViewShow").show();
								 $(".showHideStatePgramsCls").show();
								 $(".showHideDistrictPgramsCls").show();
								 $(".showHideConstituencyPgramsCls").show();
								 $(".showHideMandalPgramsCls").show();
								 $(".showHideVillagePgramsCls").show();
								$(".selectBoxCommonCls").show();
								$(".panchyatLevelYearWiseViewCls").show();
								getAllFiniancialYears();
						}
					}
				
				} else {
					//console.log("other department");
						$(".advancedSearchStyle").show();
						$(".comaprisionViewShow").show();
						$(".showHideStatePgramsCls").show();
						$(".showHideDistrictPgramsCls").show();
						$(".showHideConstituencyPgramsCls").show();
						$(".showHideMandalPgramsCls").show();
						$(".showHideVillagePgramsCls").show();
						$(".selectBoxCommonCls").show();
						$(".panchyatLevelYearWiseViewCls").show();
						getAllFiniancialYears();
				}
			}
			
			
			//district
			$(".distLevelparliamentCls").hide();
			getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
			
			//Constituency
			$(".parlaiLevelDistCls").hide();
			$("#constLevelConstNames").html('');
			$("#constLevelConstNames").trigger("chosen:updated");
			getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
				
			
			//mandal
			$(".levelparliamentConstiCls").hide();
			getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
			$("#mandalLevelConstNames").html('');
			$("#mandalLevelConstNames").trigger("chosen:updated");
			
			$("#mandalLevelMandalNames").html('');
			$("#mandalLevelMandalNames").trigger("chosen:updated");
			
			//VILLAGE
			$(".villageLevelParliCls").hide();
			getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);	
			$("#villageLevelConstNames").html('');
			$("#villageLevelConstNames").trigger("chosen:updated");
			
			$("#villageLevelMandalNames").html('');
			$("#villageLevelMandalNames").trigger("chosen:updated");
			
			$("#villageLevelNames").html('');
			$("#villageLevelNames").trigger("chosen:updated");
				
			$(".collapseActiveVillageCls").addClass("collapsed");
			$(".collapseActiveMandalCls").addClass("collapsed");
			$(".collapseActiveConstCls").addClass("collapsed");
			$(".collapseActiveDistCls").addClass("collapsed");		
			$("#collapseTwo").removeClass("in");		
			$("#collapseThree").removeClass("in");		
			$("#collapseFour").removeClass("in");		
			$("#collapseFive").removeClass("in");
			 onLoadInitialisations();
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
		
		$(".collapseActiveVillageCls").addClass("collapsed");
		$(".collapseActiveMandalCls").addClass("collapsed");
		$(".collapseActiveConstCls").addClass("collapsed");
		$(".collapseActiveDistCls").addClass("collapsed");
		$("#collapseTwo").removeClass("in");		
		$("#collapseThree").removeClass("in");		
		$("#collapseFour").removeClass("in");		
		$("#collapseFive").removeClass("in");	
		glStartDate = picker.startDate.format('DD/MM/YYYY')
		glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		
		 onLoadInitialisations();
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
			buildFinancialYearRlst(result);
		});
		onLoadInitialisations();
   }
   function buildFinancialYearRlst(result) {
	     
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
			
   }
   function getALlProgramesAmountDetails(){
	   $("#overviewBlock").html(spinner);
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		
		var sourceIdsList=[];
		var glSearchLevelValue=[];
		
		if(globalLevelId == 0 || globalLevelId == 1){
			globalLocationLevelTypeId = 0;
			glSearchLevelValue=[]
		}else{
			glSearchLevelValue.push(globalLocationId);
			globalLocationLevelTypeId = globalLocationLevelTypeId;
		}
		
		var json = {
			financialYrIdList:financialYrIdList,
			deptIdsList : deptIdsArr,
			sourceIdsList : sourceIdsList,
			fromDateStr : glStartDate,       
			toDateStr : glEndDate,		
			searchLevelId:globalLocationLevelTypeId,
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
		if(result !=null && result.length>0){
			var str='';
			var orderNo = 1;
			for(var i in result)
			{
				if(result[i].count !=null && result[i].count>0){
					str+='<div class="col-sm-3 m_top10">';
						str+='<table class="table table-bordered table-striped tableClr'+i+'">';
							str+='<thead>';
								str+='<tr>';
									if(result[i].name == "MGNREGS"){
										str+='<th class="text-center" colspan="3"><h4>MGNREGS <span class="pull-right rankingColor">'+orderNo+'</span></h4></b></h3></th>';
									}else{
										str+='<th class="text-center" colspan="3"><h4>'+result[i].name+' <span class="pull-right rankingColor">'+orderNo+'</span></h4><h3><b>'+result[i].ttlAmt+'</b></h3></th>';
									}
									
									//str+='<th class="text-center" colspan="3"><h4>'+result[i].name+'</h4><h3><b>'+result[i].ttlAmt+'</b></h3></th>';
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
												if(result[i].name == "MGNREGS" && result[i].subList[j].name == "MATERIAL EXP"){
													str+='<td class="text-center"><h4 class="m_top10">'+result[i].subList[j].name+'</h4><h4 class="m_top10"><b><i class="fa fa-inr"></i>'+result[i].subList[j].totl+'</b></h4></td>';
												}else if(result[i].name != "MGNREGS"){
													str+='<td class="text-center"><h4>'+result[i].subList[j].name+'</h4><h4><b><i class="fa fa-inr"></i>'+result[i].subList[j].totl+'</b></h4></td>';
												}
												
											}else{
												str+='<td class="text-center"><h4>'+result[i].subList[j].name+'</h4><h4><b>-</b></h4></td>';
											}
											
										}
									}
								str+='</tr>';
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
					orderNo = orderNo + 1;
				}else{
					$(".tableClr"+i).html('');
				}
				
			}
			
			$("#overviewBlock").html(str);
		}else{
			$("#overviewBlock").html('');
		}
		
	}
	
	function getSchemeWiseLocationWiseAmountDetails(levelId,divId,sortingType,orderType,locationId,locationLevelType,programId,subProgramId,viewType,onchageType,grantType){
		
		$("#"+divId).html(spinner);
		$("#"+divId+"Table").html(spinner);
		var levelValues = [];
		var sourceIdsArr = [];
		var schemeIdsArr = [];
		var govtSchmeIdsList=[];
		var subProgramIdsList=[];
		var grantTypeIdsList=[];
		var deptIdsArr =$('#DepartmentsId').val();
		var financialYrIdArr = $('#financialYearId').val();
		
		var length = locationId.toString().length;
		if(locationId == null || locationId == 0){
			 levelValues =[];
		}else{
			if(onchageType == "onload"){
				levelValues.push(locationId);
			}else{
				if(length >2){
				var tempIdStr=""+locationId;
					var finalIdStr= tempIdStr.substring(1);
					levelValues.push(finalIdStr);
				}else{
					levelValues.push(locationId);
				}
			}
			
			
			 
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
		
		if(locationLevelType == 9 && levelId == 3){
			 levelId = 9;
		}
		
		if(grantType == null || grantType == 0){
			grantTypeIdsList=[];
		}else{
			grantTypeIdsList.push(grantType)
		}
		var subFilterType = "district";
		if(levelId == 3 || levelId == 9){
			var blockDis = getblockType();
			if(blockDis == "districtType"){
				subFilterType = "district"
			}else{
				subFilterType = "parliament"
			}
		}else if(levelId == 4){
			var blockCons = getblockTypeCons();
			if(blockCons == "districtType"){
				subFilterType = "district"
			}else{
				subFilterType = "parliament"
			}
		}else if(levelId == 5){
			var blockMan = getblockTypeMandal();
			if(blockMan == "districtType"){
				subFilterType = "district"
			}else{
				subFilterType = "parliament"
			}
		}else if(levelId == 6){
			var blockVill = getblockTypeVillage();
			if(blockVill == "districtType"){
				subFilterType = "district"
			}else{
				subFilterType = "parliament"
			}
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
			glSearchLevelId :1,
			viewType :viewType,
			grantTypeIdsList:grantTypeIdsList,
			subFilterType:subFilterType
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
					buildLocationWiseAmountDetails(ajaxresp,divId,levelId,viewType);
				}
					
				else{
					$("#"+divId).html("NO DATA AVAILABLE");
					$("#"+divId+"Table").html("NO DATA AVAILABLE");
				}
					
			}
		});
		
	}
	
	function buildLocationWiseAmountDetails(result,divId,levelId,viewType)//ara
	{
		
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
		var newYearId;
		var departmentId = $("#DepartmentsId").val();
		/* if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			} */
			table+='<div class="table-responsive">';	
			table+='<table class="table table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						if(levelId == '2')
						{
							table+='<th>STATE</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}else if(levelId == '3')
						{
							var blockType = getblockType();
							
							table+='<th>DISTRICT</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}else if(levelId == '9')
						{
							var blockType = getblockType();
						
							table+='<th>PARLIAMENT</th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}
						else if(levelId == '4')
						{
							var blockType = getblockTypeCons();
							table+='<th>DISTRICT</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(blockType == "parliamentType"){ 
								table+='<th>PARLIAMENT</th>';
							}
							table+='<th>CONSTITUENCY</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}else if(levelId == '5')
						{
							var blockType = getblockTypeMandal();
							table+='<th>DISTRICT</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(blockType == "parliamentType"){
								table+='<th>PARLIAMENT</th>';
							}
							table+='<th>CONSTITUENCY</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							table+='<th>MANDAL</th>';
							if(departmentId != 1 && departmentId != 3)
							table+='<th></th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}else if(levelId == '6')
						{
							var blockType = getblockTypeVillage();
							table+='<th>DISTRICT</th>';
							if(blockType == "parliamentType"){
								table+='<th>PARLIAMENT</th>';
							}
							table+='<th>CONSTITUENCY</th>';
							table+='<th>MANDAL</th>';
							table+='<th>VILLAGE</th>';
							if(viewType == "cumulative"){
								table+='<th>TOTAL</th>';
							}
						}
						table+='<th class="text-center">YEAR</th>';
						for(var j in result[0].subList[0].subList){//qa
							table+='<th class="text-center">'+result[0].subList[0].subList[j].name+'</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
				for(var i in result){
					    
					var schmeIdstr = '';
					
					for(var j in result[i].subList)
					{
						for(var m in result[i].subList[j].subList)
						{
							schmeIdstr = result[i].subList[j].subList[m].id;
							
						}
					if((result[i].amount !=null && result[i].amount !="0.000") || (result[i].totalCount !=null && result[i].totalCount>0)){	
						table+='<tr>';
						var lvlVal = 0;
						var locatioName='';
						var levelName='';
							if(levelId == '2')
							{
								lvlVal =1;
								//table+='<td>1</td>';
									table+='<td>Andhra Pradesh </td>';
									if(departmentId != 1 && departmentId != 3){
										table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="2" attr_rws_locationId="1" attr_district_val ="" attr_type="state" attr_location_name="Andhra Pradesh">R</span></td>';
									}
									
										if(viewType == "cumulative"){
											if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											  table+='<td>'+result[i].amount+'</td>';	
											}else {
											  //table+='<td>'+result[i].amount+'  ('+result[i].count+')</td>';	
											  table+='<td>'+result[i].amount+'</td>';	
											}
											
										}
								
							}
							else if(levelId == '3')
							{
								lvlVal =result[i].addressVO.districtId;
								locatioName =result[i].addressVO.districtName;
								levelName = "district";
								var blockType = getblockType();
								table+='<td>'+result[i].addressVO.districtName+' </td>';
								if(departmentId != 1 && departmentId != 3){
									table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="3" attr_rws_locationId="'+lvlVal+'" attr_district_val ="" attr_type="district" attr_location_name="'+result[i].addressVO.districtName+'">R</span></td>';
								}
								
									if(viewType == "cumulative"){
											if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											   table+='<td>'+result[i].amount+'</td>';	
											}else {
											  //table+='<td>'+result[i].amount+'  (<small title="No of times amount sanctioned..." class="toolltipCls"  attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+schmeIdstr+'" attr_dept_id="0" style="color:green;" attr_level_name = "DISTRICT" >'+result[i].count+')</small></td>';
										      table+='<td>'+result[i].amount+'</td>';		
											}
									}
								
								
								
							}else if(levelId == '9')
							{
								lvlVal =result[i].addressVO.parliamentId;
								locatioName =result[i].addressVO.parliamentName;
								levelName = "parliament";
								var blockType = getblockType();
								table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									if(viewType == "cumulative"){
										    if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											   table+='<td>'+result[i].amount+'</td>';	
											}else{
										      //table+='<td>'+result[i].amount+'  (<small title="No of times amount sanctioned..." class="toolltipCls"  attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+schmeIdstr+'" attr_dept_id="0" style="color:green;" attr_level_name = "DISTRICT" >'+result[i].count+')</small></td>';
										      table+='<td>'+result[i].amount+'</td>';
											}
									}
							}
							else if(levelId == '4')
							{
								var blockType = getblockTypeCons();
								lvlVal =result[i].addressVO.assemblyId;
								var districtId =result[i].addressVO.districtId;
								levelName = "constituency";
								locatioName =result[i].addressVO.assemblyName;
								table+='<td>'+result[i].addressVO.districtName+' </td>';
								if(departmentId != 1 && departmentId != 3){
									table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="4" attr_rws_locationId="'+districtId+'" attr_district_val ="" attr_type="district" attr_location_name="'+result[i].addressVO.districtName+'">R</span></td>';
								}
								
									if(blockType == "parliamentType"){
										table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									}
									table+='<td>'+result[i].addressVO.assemblyName+' </td>';
										if(departmentId != 1 && departmentId != 3){
											table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="4" attr_rws_locationId="'+lvlVal+'" attr_district_val ="" attr_type="constituency" attr_location_name="'+result[i].addressVO.assemblyName+'">R</span></td>';
										}
									
									if(viewType == "cumulative"){
										    if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											   table+='<td>'+result[i].amount+'</td>';	
											}else{
										      //table+='<td>'+result[i].amount+'  (<small title="No of times amount sanctioned..." class="toolltipCls"  attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+schmeIdstr+'" attr_dept_id="0" style="color:green;" attr_level_name = "CONSTITUENCY" >'+result[i].count+')</small></td>';
										      table+='<td>'+result[i].amount+'</td>';
											}
									}
								
								
							}else if(levelId == '5')
							{
								var blockType = getblockTypeMandal();
								lvlVal =result[i].addressVO.id;
								locatioName =result[i].addressVO.tehsilName;
								levelName = "mandal";
								var districtId =result[i].addressVO.districtId;
								var constituencyId =result[i].addressVO.assemblyId;
								table+='<td>'+result[i].addressVO.districtName+' </td>';
								if(departmentId != 1 && departmentId != 3){
									table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="5" attr_rws_locationId="'+districtId+'" attr_district_val ="" attr_type="district" attr_location_name="'+result[i].addressVO.districtName+'">R</span></td>';
								}
								
									if(blockType == "parliamentType"){
										table+='<td>'+result[i].addressVO.parliamentName+'</td>';
									}
									table+='<td>'+result[i].addressVO.assemblyName+' </td>';
									if(departmentId != 1 && departmentId != 3){
										table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="5" attr_rws_locationId="'+constituencyId+'" attr_district_val ="" attr_type="constituency" attr_location_name="'+result[i].addressVO.assemblyName+'">R</span></td>';
									}
									
									table+='<td>'+result[i].addressVO.tehsilName+' </td>';
									if(departmentId != 1 && departmentId != 3){
										table+='<td><span class="icon-text rwsModalClickView" attr_rws_levelId="5" attr_rws_locationId="'+lvlVal+'" attr_district_val ="'+districtId+'" attr_type="mandal" attr_location_name="'+result[i].addressVO.tehsilName+'">R</span></td>';
									}
									
									if(viewType == "cumulative"){
										 if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											   table+='<td>'+result[i].amount+'</td>';	
										}else{
										  //table+='<td>'+result[i].amount+'  (<small title="No of times amount sanctioned..." class="toolltipCls"  attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+schmeIdstr+'" attr_dept_id="0" style="color:green;" attr_level_name = "MANDAL">'+result[i].count+')</small></td>';
										   table+='<td>'+result[i].amount+'</td>';
										}
									}
								
							}else if(levelId == '6')
							{
								var blockType = getblockTypeVillage();
								lvlVal =result[i].addressVO.id;
								locatioName =result[i].addressVO.panchayatName;
								levelName = "village";
								
								var districtId =result[i].addressVO.districtId;
								var constituencyId =result[i].addressVO.assemblyId;
								var mandalId =result[i].addressVO.tehsilId;
								
								table+='<td>'+result[i].addressVO.districtName+'</td>';
										if(blockType == "parliamentType"){
											table+='<td>'+result[i].addressVO.parliamentName+'</td>';
										}
										table+='<td>'+result[i].addressVO.assemblyName+'</td>';
										table+='<td>'+result[i].addressVO.tehsilName+'</td>';
										
										table+='<td>'+result[i].addressVO.panchayatName+'</td>';
										if(viewType == "cumulative"){
											 if(result[i].count == 0) { // in the case MGNREGS we are sending count is zero and we are not displaying it.
											   table+='<td>'+result[i].amount+'</td>';	
											 }else{
												//table+='<td>'+result[i].amount+'  (<small title="No of times amount sanctioned..." class="toolltipCls"  attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+schmeIdstr+'" attr_dept_id="0" style="color:green;" attr_level_name = "VILLAGE">'+result[i].count+')</small></td>';
												table+='<td>'+result[i].amount+'</td>';
											 }
										}
								
							}
							table+='<td class="text-center">'+result[i].subList[j].year+'</td>';
							for(var k in result[i].subList[j].subList)
							{
								//newYearId = $("#financialYearId").val();
								if(result[i].subList[j].yearId != 0 || typeof(result[i].subList[j].yearId) === 'undefined' || typeof(result[i].subList[j].yearId) === undefined){
									newYearId = result[i].subList[j].yearId;
								}else{
									newYearId = $("#financialYearId").val();
								}  
								 //location block
									if(levelId != '2'){
										if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count == 0){//in the case of MGNREGS count will come zero
											
											table+='<td class="text-center no-right-border">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</small></td>';
											
										}else{
											//table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+result[i].subList[j].subList[k].id+'" attr_dept_id="0" attr_location_name="'+locatioName+'" attr_level_name="'+levelName+'" attr_location_strids="'+result[i].locationIdStr+'" attr_mgnregs_name="'+result[i].subList[j].subList[k].name+'" attr_view_type="'+viewType+'">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'<br/><small title="No of times amount sanctioned..." class="toolltipCls">&nbsp;&nbsp;(<u><span style="cursor:pointer;color:green;">'+result[i].subList[j].subList[k].count+'</span> </u>)</small></td>';
											table+='<td><span class="text-center no-right-border fundSanctionCls toolltipCls" attr_scope_id="'+levelId+'" attr_level_value="'+lvlVal+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="'+result[i].subList[j].subList[k].id+'" attr_dept_id="0" attr_location_name="'+locatioName+'" attr_level_name="'+levelName+'" attr_location_strids="'+result[i].locationIdStr+'" attr_mgnregs_name="'+result[i].subList[j].subList[k].name+'" attr_view_type="'+viewType+'" title="Click here to view total works ('+result[i].subList[j].subList[k].count+') details">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</span></td>';
										}
									}else{
										if(result[i].subList[j].subList[k].count != null && result[i].subList[j].subList[k].count == 0){
											table+='<td class="text-center no-right-border">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</td>';
										}else{
											//table+='<td class="text-center no-right-border">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'<br/><small title="No of times amount sanctioned..." class="toolltipCls" >&nbsp;&nbsp;('+result[i].subList[j].subList[k].count+')</small></td>';
											table+='<td class="text-center no-right-border">'+parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, ""))+'</td>';
										}
									}
								
							}
						table+='</tr>';
					}
					}
				}
				table+='</tbody>';
			table+='</table>';
			/* if(result[0].subList.length >= 2 || $windowWidth < 768)
			{
				table+='</div>';
			} */
			table+='</div>';

		$("#"+divId+"Table").html(table);
		$('.toolltipCls').tooltip();
		
		if( levelId == 4 || levelId == 5 || levelId == 6)
		{
			$("#dataTable"+divId).dataTable({
				"iDisplayLength": 15,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
				"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
				"<'row'<'col-sm-12'tr>>" +
				"<'row'<'col-sm-5'i><'col-sm-7'p>>",
				buttons: [
					{
						extend:    'csvHtml5',
						text:      '<i class="fa fa-file-text-o"></i>',
						titleAttr: 'CSV',
						title:	   divId,
						filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					},
					{
						extend:    'pdfHtml5',
						text:      '<i class="fa fa-file-pdf-o"></i>',
						titleAttr: 'PDF',
						title:	   divId,
						filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
						orientation: "landscape",
						pageSize:'A3'
					}
				]
			});
			
		}else if(levelId == 3 || levelId == 9){
			if(viewType == "cumulative"){
				$("#dataTable"+divId).dataTable({
					"paging":   false,
					"info":     false,
					"searching": true,
					"autoWidth": true,
					"sDom": '<"top"iflp>rt<"bottom"><"clear">',
					"order": [ 0, 'desc' ],
					"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
					"<'row'<'col-sm-12'tr>>" +
					"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					buttons: [
						{
							extend:    'csvHtml5',
							text:      '<i class="fa fa-file-text-o"></i>',
							titleAttr: 'CSV',
							title:	   divId,
							filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
						},
						{
							extend:    'pdfHtml5',
							text:      '<i class="fa fa-file-pdf-o"></i>',
							titleAttr: 'PDF',
							title:	   divId,
							filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							orientation: "landscape",
							pageSize:'A3'
						}
					]
				});
			}else{
				$("#dataTable"+divId).dataTable({
					"iDisplayLength": 15,
					"aaSorting": [],
					"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
					"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
					"<'row'<'col-sm-12'tr>>" +
					"<'row'<'col-sm-5'i><'col-sm-7'p>>",
					buttons: [
						{
							extend:    'csvHtml5',
							text:      '<i class="fa fa-file-text-o"></i>',
							titleAttr: 'CSV',
							title:	   divId,
							filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
						},
						{
							extend:    'pdfHtml5',
							text:      '<i class="fa fa-file-pdf-o"></i>',
							titleAttr: 'PDF',
							title:	   divId,
							filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
							orientation: "landscape",
							pageSize:'A3'
						}
					]
				});
			}
				
			}
			if(levelId == 2){
				if(result !=null && result.length>0){
					var yearsArr=[];
					var SVSArr=[];
					var MVSArr=[];
					var SOLARArr=[];
					var CWTPArr=[];
					var CRRArr=[];
					var RDFArr=[];
					var FC13Arr=[];
					var MRRArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var APDRPGRANTArr=[];
					var MGNREGSArr=[];
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
												//SVSArr.push({"y":result[i].subList[j].subList[k].totalCount})
												SVSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //MVSArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MVSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //SOLARArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SOLARArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //CWTPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CWTPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												 //RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 10){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 11){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 0){
												 MGNREGSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":0})
											 }
										}
										 totalCountArr.push(totalCount2);
									}
									
								}	
							}
						}
						var mainJosnObjArr=[];
						if(SVSArr != null && SVSArr.length > 0){
							mainJosnObjArr.push({name:'SVS',data:SVSArr,color:"#309AFF"});  
						}
						if(MVSArr != null && MVSArr.length > 0){
							mainJosnObjArr.push({name:'MVS',data:MVSArr,color:"#01A64E"});  
						}
						if(SOLARArr != null && SOLARArr.length > 0){
							mainJosnObjArr.push({name:'SOLAR',data:SOLARArr,color:"#FF0DAD"});  
						}
						if(CWTPArr != null && CWTPArr.length > 0){
							mainJosnObjArr.push({name:'CWTP',data:CWTPArr,color:"#FF872C"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#3C46FF"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#5B5B5B"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#46306A"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#9C9C9C"});  
						}if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#14BAAD"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#14BEED"});  
						}
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#18BGGD"});  
						}
						if(MGNREGSArr != null && MGNREGSArr.length > 0){
							mainJosnObjArr.push({name:'MGNREGS',data:MGNREGSArr,color:"#01A64D"});  
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
									var str='';
									str+='<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total
									 var count = this.options.qTotals[this.x];
									 if(count > 0) {
										str +=' -('+this.options.qTotals[this.x]+')';
									 }
									 str+='</span>';
									return str;
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
									if(this.series.name != "Series 1"){
									  s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
									  this.y/* +' - ' +
									  (Highcharts.numberFormat(this.percentage,1)+'%'); */
									   var count = (this.point.extra);
									   if (count > 0){
										   s+='-('+(this.point.extra)+')'
									   }
									}  
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
										if(schemeId==0){ //MGNREGS schemeId id  is zero we are sending from service and in that case we are stopping click call.
											return; 
										}
										var locationName="";
										var levelName="STATE";
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0,locationName,levelName);
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
			}else if(levelId == 3 || levelId == 4 || levelId == 5 || levelId == 9){
				
				if(result !=null && result.length>0){
				
					var assemblyShemeNameArr=[];
					var SVSArr=[];
					var MVSArr=[];
					var SOLARArr=[];
					var CWTPArr=[];
					var CRRArr=[];
					var RDFArr=[];
					var FC13Arr=[];
					var MRRArr=[];
					var NABARDArr =[];
					var NREGPArr =[];
					var APDRPGRANTArr=[];
					var MGNREGSArr=[];
					
					var totalCountArr = [];
                    var totalCount2 = '';
					for(var i in result){
						var locationName='';
						var levelName='';
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
										//locationId=result[i].subList[j].addressVO.id;
										locationId=result[i].addressVO.id;
										//locationName =result[i].subList[j].addressVO.districtName;
										locationName =result[i].addressVO.districtName;
										levelName = "DISTRICT";
										//assemblyShemeNameArr.push(result[i].subList[j].addressVO.districtName+"<br/>("+result[i].subList[j].year+")")
										assemblyShemeNameArr.push(result[i].addressVO.districtName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 4){
										//locationId=result[i].subList[j].addressVO.id;
										locationId=result[i].addressVO.id;
									//	locationName =result[i].subList[j].addressVO.assemblyName;
										locationName =result[i].addressVO.assemblyName;
										levelName = "CONSTITUENCY";
										//assemblyShemeNameArr.push(result[i].subList[j].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+")")
										assemblyShemeNameArr.push(result[i].addressVO.assemblyName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 5){
										//locationId=result[i].subList[j].addressVO.id;
										locationId=result[i].addressVO.id;
										//locationName =result[i].subList[j].addressVO.name;
										locationName =result[i].addressVO.name;
										levelName = "MANDAL";
										//assemblyShemeNameArr.push(result[i].subList[j].addressVO.name+"<br/>("+result[i].subList[j].year+")")
										assemblyShemeNameArr.push(result[i].addressVO.name+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 6){
										//locationId=result[i].subList[j].addressVO.id;
										locationId=result[i].addressVO.id;
										//locationName =result[i].subList[j].addressVO.panchayatName;
										locationName =result[i].addressVO.panchayatName;
										levelName = "VILLAGE";
										//assemblyShemeNameArr.push(result[i].subList[j].addressVO.panchayatName+"<br/>("+result[i].subList[j].year+")")
										assemblyShemeNameArr.push(result[i].addressVO.panchayatName+"<br/>("+result[i].subList[j].year+")")
									}else if(levelId == 9){
										//locationId=result[i].subList[j].addressVO.id;
										locationId=result[i].addressVO.id;
										//locationName =result[i].subList[j].addressVO.parliamentName;
										locationName =result[i].addressVO.parliamentName;
										levelName = "PARLIAMENT";
										//assemblyShemeNameArr.push(result[i].subList[j].addressVO.parliamentName+"<br/>("+result[i].subList[j].year+")")
										assemblyShemeNameArr.push(result[i].addressVO.parliamentName+"<br/>("+result[i].subList[j].year+")")
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
												// SVSArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SVSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 2){
												 //MVSArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MVSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 3){
												 //SOLARArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 SOLARArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 4){
												 //CWTPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CWTPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 5){
												 //CRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 CRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 6){
												//RDFArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 RDFArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 7){
												 //FC13Arr.push({"y":result[i].subList[j].subList[k].totalCount})
												 FC13Arr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 8){
												 //MRRArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 MRRArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 9){
												 //NABARDArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NABARDArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 10){
												 //NREGPArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 NREGPArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 11){
												 //APDRPGRANTArr.push({"y":result[i].subList[j].subList[k].totalCount})
												 APDRPGRANTArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id+"-"+locationName+"-"+levelName,"extra":result[i].subList[j].subList[k].count})
											 }else if(result[i].subList[j].subList[k].id == 0){
												 MGNREGSArr.push({"y":parseFloat(result[i].subList[j].subList[k].amount.replace(/,/g, "")),appData:levelId+"-"+locationId+"-"+result[i].subList[j].yearId+"-"+result[i].subList[j].subList[k].id,"extra":0})
											 }
											
										}
										totalCountArr.push(totalCount2);
									}
									
								}
							}
						}
						var mainJosnObjArr= [];
						if(SVSArr != null && SVSArr.length > 0){
							mainJosnObjArr.push({name:'SVS',data:SVSArr,color:"#309AFF"});  
						}
						if(MVSArr != null && MVSArr.length > 0){
							mainJosnObjArr.push({name:'MVS',data:MVSArr,color:"#01A64E"});  
						}
						if(SOLARArr != null && SOLARArr.length > 0){
							mainJosnObjArr.push({name:'SOLAR',data:SOLARArr,color:"#FF0DAD"});  
						}
						if(CWTPArr != null && CWTPArr.length > 0){
							mainJosnObjArr.push({name:'CWTP',data:CWTPArr,color:"#FF872C"});  
						}
						if(CRRArr != null && CRRArr.length > 0){
							mainJosnObjArr.push({name:'CRR',data:CRRArr,color:"#3C46FF"});  
						}
						if(RDFArr != null && RDFArr.length > 0){
							mainJosnObjArr.push({name:'RDF',data:RDFArr,color:"#5B5B5B"});  
						}
						if(FC13Arr != null && FC13Arr.length > 0){
							mainJosnObjArr.push({name:'13th FC',data:FC13Arr,color:"#46306A"});  
						}
						if(MRRArr != null && MRRArr.length > 0){
							mainJosnObjArr.push({name:'MRR',data:MRRArr,color:"#9C9C9C"});  
						}if(NABARDArr != null && NABARDArr.length > 0){
							mainJosnObjArr.push({name:'NABARD',data:NABARDArr,color:"#14BAAD"});  
						}
						if(NREGPArr != null && NREGPArr.length > 0){
							mainJosnObjArr.push({name:'NREGP',data:NREGPArr,color:"#14BEED"});  
						}
						if(APDRPGRANTArr != null && APDRPGRANTArr.length > 0){
							mainJosnObjArr.push({name:'APDRP GRANT',data:APDRPGRANTArr,color:"#18BGGD"});  
						}
						if(MGNREGSArr != null && MGNREGSArr.length > 0){
							mainJosnObjArr.push({name:'MGNREGS',data:MGNREGSArr,color:"#01A64D"});  
						}
					}
					var heightOfDiv = assemblyShemeNameArr.length ;
					if(heightOfDiv >10){
						heightOfDiv = heightOfDiv * 80;
						$(".chart"+divId).css("height",heightOfDiv);
						$(".scroller"+divId).mCustomScrollbar({setHeight:'600px'})
					}else{
						$(".chart"+divId).css("height","auto");
						$(".scroller"+divId).removeAttr('style')
						$(".scroller"+divId).mCustomScrollbar('destroy');
					  }
					/* 
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
					$(".chart"+divId).height(height); */
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
									 var str = '';
									 str +='<span style="top:30px; position: absolute;"><i class="fa fa-inr"></i>: '+this.total
									 var count = this.options.qTotals[this.x];
									 if(count > 0) {
										str +=' -('+this.options.qTotals[this.x]+')';
									 }
									 str+='</span>';
									return str;
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
									if(this.series.name != "Series 1")  {
										s += '<br/><b style="color:'+this.series.color+'">' + this.series.name + '</b> :<i class="fa fa-inr"></i> ' +
										this.y/*+'-'+'('+(this.point.extra)+')'/* +' - ' +
										(Highcharts.numberFormat(this.percentage,1)+'%'); */
										  var count = (this.point.extra);
										   if (count > 0){
											   s+='-('+(this.point.extra)+')'
										   }
									}
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
										var locationName = value[4];
										var LevelName = value[5];
										if(schemeId==0){ //MGNREGS schemeId id  is zero we are sending from service  and in that case we are stopping click call.
											return; 
										}
										getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,0,locationName,LevelName);
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
	
	function getSchemeWiseOverviewDetails(subProgId,schemes){
		$(".total"+schemes).html(spinner);
		$(".district"+schemes).html(spinner);
		$(".constituency"+schemes).html(spinner);
		$(".mandal"+schemes).html(spinner);
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		var sourceIdsList=[];
		
		var glSearchLevelValue=[];
		if(globalLevelId == 0 || globalLevelId == 1){
			globalLocationLevelTypeId = 0;
			glSearchLevelValue=[]
		}else{
			glSearchLevelValue.push(globalLocationId);
			globalLocationLevelTypeId = globalLocationLevelTypeId;
		}
		
		var schemeIdsList = [];
			schemeIdsList.push(schemes);
		var subProgIds = [];
			subProgIds.push(subProgId);
		
		var json = {
			financialYrIdList:financialYrIdList,
			deptIdsList : deptIdsArr,
			sourceIdsList : sourceIdsList,
			fromDateStr:glStartDate,
			toDateStr:glEndDate,
			schemeIdsList:schemeIdsList,
			subProgIds:subProgIds,
			searchLevelId:globalLocationLevelTypeId,
			searchLvlVals:glSearchLevelValue
			
		}
		$.ajax({
			url : "getSchemeWiseOverviewDetails",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){
				buildSchemeWiseOverviewDetails(result,schemes);
				
				/* if(result !=null && result.length>0){
					buildSchemeWiseOverviewDetails(result,schemes);
				}else{
					//$(".clearCls").remove();
				} */
				
			}
		});
	}
	function buildSchemeWiseOverviewDetails(result,schemes)
	{
		var total = '';
		var district = '';
		var constituency = '';
		var mandal = '';
		if(result.ttlAmt !=null && result.ttlAmt>0){
			var totalMGNMaterialCount = 0;
			for(var i in result.subList)
			{
				if(result.subList[i].name == "MATERIAL EXP"){
					totalMGNMaterialCount = result.subList[i].totl;
				}
			}	
			if(schemes == "-1"){
				total+='<h3>'+totalMGNMaterialCount+'</h3>';
				total+='<ul class="list-inline">';
					total+='<li>';
						total+='<p>MATERIAL EXP</p>';
						total+='<p>'+totalMGNMaterialCount+'</p>';
						
					total+='</li>';
				total+='</ul>';
			}else{
				//var totalAmountVal =result.totalAmt;
				total+='<h3>'+result.totalAmt+'</h3>';
				total+='<ul class="list-inline">';
				for(var i in result.subList)
				{
					total+='<li>';
						total+='<p>'+result.subList[i].name+'</p>';
						total+='<p>'+result.subList[i].totl+'</p>';
						
					total+='</li>';
					
				}
				total+='</ul>';
			}
			if(result.fundList.length > 0)
			{
				district+='<p class="text-primary"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[0].highLocName+'-'+result.fundList[0].highCroreAmt+'</p>';
				district+='<p class="text-warning"><i class="rounded-circle fa fa-inr"></i>Avg - '+result.fundList[0].avgCroreAmt+'</p>';
				district+='<p class="text-danger"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[0].lowLocName+'-'+result.fundList[0].lowCroreAmt+'</p>';
			}
			if(result.fundList.length > 1)
			{
				constituency+='<p class="text-primary"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[1].highLocName+'-'+result.fundList[1].highCroreAmt+'</p>';
				constituency+='<p class="text-warning"><i class="rounded-circle fa fa-inr"></i>Avg - '+result.fundList[1].avgCroreAmt+'</p>';
				constituency+='<p class="text-danger"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[1].lowLocName+'-'+result.fundList[1].lowCroreAmt+'</p>';
			}
			if(result.fundList.length > 2)
			{
				mandal+='<p class="text-primary"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[2].highLocName+'-'+result.fundList[2].highCroreAmt+'</p>';
				mandal+='<p class="text-warning"><i class="rounded-circle fa fa-inr"></i>Avg - '+result.fundList[2].avgCroreAmt+'</p>';
				mandal+='<p class="text-danger"><i class="rounded-circle fa fa-inr"></i>'+result.fundList[2].lowLocName+'-'+result.fundList[2].lowCroreAmt+'</p>';
			}
		}else{
			$("#blockProgram"+schemes).html('')
			$("#blockProgramInd").html('No Data Available')
		} 
		
		
		$(".total"+schemes).html(total);
		$(".district"+schemes).html(district);
		$(".constituency"+schemes).html(constituency);
		$(".mandal"+schemes).html(mandal);
	}
	function getAllSubLocationsBySuperLocationId(locationScopeId,divId,levelId){
		$("#"+divId).html('');
		var financialYrIdList = $('#financialYearId').val();
		var deptIdsArr = $('#DepartmentsId').val();
		var mgrwsIdsArr=[];
		for(var i in deptIdsArr){
			if(deptIdsArr[i] == 3){
				if(deptIdsArr[i].length == 1){
					mgrwsIdsArr=[];
					mgrwsIdsArr.push(0);
				
				}
			}
		}
		for(var i in deptIdsArr){
			if(deptIdsArr[i] != 3){
				mgrwsIdsArr.push(deptIdsArr[i]);
				
			}
		}
		var sourceId = 0;
		var json = {
		  blockLevelId:levelId,
		  superLocationId : locationScopeId, 
		  deptIdsList : mgrwsIdsArr,
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
	
	$(document).on("change",".distLevelCls1, #grantTypeDistrict",function(){  
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockType();
		var programId =$("#programNamesDistrict").val();
		var subProgramId =$("#subProgramNamesDistrict").val();
		var grantType = $("#grantTypeDistrict").val();
		var cummulativeType ='';
		$(".tabCummulativeDistrict li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		
		var locationLevelType='';
		var locationId =0;
		
		if(blockType == "parliamentType"){
			locationLevelType = 9;
			locationId =$("#distLevelParliamentNames").val();
		}else{
			locationLevelType = 3;
			locationId =$("#distLevelDistrictNames").val();
		}
		if(cummulativeType == "normalView"){//aaaa
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else if(cummulativeType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("click",".sortingDivDistCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		var sortingType = $(this).attr("attr_sorting_type");
		var orderType = $(this).attr("attr_order_type");
		var programId =$("#programNamesDistrict").val();
		var subProgramId =$("#subProgramNamesDistrict").val();
		var blockType = getblockType();
		var grantType = $("#grantTypeDistrict").val();
		var locationLevelType='';
		var locationId =0;
		if(blockType == "parliamentType"){
			locationLevelType = 9;
			locationId =$("#distLevelParliamentNames").val();
		}else{
			locationLevelType = 3;
			locationId =$("#distLevelDistrictNames").val();
		}
		var cummulativeType ='';
		$("[role='tabCummulative'] li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else if(cummulativeType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
	});	
	//Constituency
	$(document).on("change",".constiLevelDistCls,#grantTypeConst",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var cummulativeType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var grantType = $("#grantTypeConst").val();
		var locationId =0;
		var locationLevelType = 3;
		var locationId =$("#constLevelDistNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'constLevelConstNames',4);
		if(cummulativeType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
			
		}else if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}
		
			
	});
	$(document).on("change",".parlaiLevelDistCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var cummulativeType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var grantType = $("#grantTypeConst").val();
		
		var parliamentId =$("#constLevelParliaNames").val();
		var constituencyId =$("#constLevelConstNames").val();
		var locationId=0;
		var locationLevelType = 0;
		if((constituencyId !=0 && parliamentId !=0) || constituencyId == null || constituencyId==0){
			locationId = parliamentId;
			locationLevelType=9;
		}else if(constituencyId !=0 && parliamentId ==0){
			locationId = constituencyId;
			locationLevelType=4;
		}
		
		getAllSubLocationsBySuperLocationId(parliamentId,'constLevelConstNames',4);
		if(cummulativeType == "cummulativeView"){     
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}else if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}
		
			
	});
	$(document).on("change",".constiLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeCons();
		var cummulativeType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesConst").val();
		var subProgramId =$("#subProgramNamesConst").val();
		var grantType = $("#grantTypeConst").val();
		
		var districtId = $("#constLevelDistNames").val();
		var constituencyId =$("#constLevelConstNames").val();
		var locationId=0;
		var locationLevelType = 0;
		if(districtId !=0 && constituencyId !=0){
			locationId = constituencyId;
			locationLevelType=4;
		}else if(districtId !=0 && constituencyId ==0){
			locationId = districtId;
			locationLevelType=3;
		}
		
		
		if(cummulativeType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}else if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);	
		}
		
			
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
		var grantType = $("#grantTypeConst").val();
		
		var locationId = 0;
		var locationLevelType = 0;
		
		var cummulativeType ='';
		$(".tabCummulativeConstituency li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		
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
		if(cummulativeType == "cummulativeView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}else if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}
		
	});
	
	//Mandal
	$(document).on("change",".mandalLevelDistCls,#grantTypeMandal",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var grantType = $("#grantTypeMandal").val();
		
		var locationId =$("#mandalLevelDistNames").val();
		var locationLevelType = 3;
		if(locationId == 0){
			 $("#mandalLevelMandalNames").html('');
			 $("#mandalLevelMandalNames").trigger("chosen:updated")
			 
		 }
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelConstNames',5);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".levelparliamentConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var grantType = $("#grantTypeMandal").val();
		
		var locationLevelType = 9;
		var locationId =$("#parliamentLevelConstNames").val();
		 if(locationId == 0){
			 $("#mandalLevelMandalNames").html('');
			 $("#mandalLevelMandalNames").trigger("chosen:updated")
			 
		 }
		
		
		
		getAllSubLocationsBySuperLocationId(locationId,'mandalLevelConstNames',5);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".levelmandalConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var grantType = $("#grantTypeMandal").val();
		
		var constituencyId =$("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		var parliamentId = $("#parliamentLevelConstNames").val();
		var districtId = $("#mandalLevelDistNames").val();
		
		var locationLevelType = 0;
		var locationId =0;
		if(constituencyId !=0){
			locationId = constituencyId;
			locationLevelType =4;
			getAllSubLocationsBySuperLocationId(locationId,'mandalLevelMandalNames',5);
		}else if(constituencyId ==0){
			$("#mandalLevelMandalNames").html('');
			$("#mandalLevelMandalNames").trigger("chosen:updated");
			if(blockType == "districtType"){
				locationId = districtId;
				locationLevelType =3;
			}else if(blockType == "parliamentType"){
				locationId = parliamentId;
				locationLevelType =9;
			}
			
		}
		
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".mandalLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesMandal").val();
		var subProgramId =$("#subProgramNamesMandal").val();
		var grantType = $("#grantTypeMandal").val();
		
		
		var constituencyId =$("#mandalLevelConstNames").val();
		var mandalId = $("#mandalLevelMandalNames").val();
		
		var parliamentId = $("#parliamentLevelConstNames").val();
		var districtId = $("#mandalLevelDistNames").val();
		
		var locationLevelType = 0;
		var locationId =0;
		if(mandalId !=0){
			locationId = mandalId;
			locationLevelType =5;
		}else if(mandalId ==0){
			locationId = constituencyId;
			locationLevelType =4;
			
		}
		
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
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
		var grantType = $("#grantTypeMandal").val();
		
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
		var cummulativeType ='';
		$(".tabCummulativeMandal li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
	});
	//VILLAGE
	$(document).on("change",".villageLevelDistCls,#grantTypeVillage",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeVillage();
		var cummulativeType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var grantType = $("#grantTypeVillage").val();
		
		var locationId =0;
		var locationLevelType = 3;
		var locationId =$("#villageLevelDistNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelConstNames',6);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".villageLevelParliCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var grantType = $("#grantTypeVillage").val();
		
		var locationLevelType = 9;
		var locationId =$("#villageLeveParliNames").val();
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelConstNames',6);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".villageLevelConstiCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var grantType = $("#grantTypeVillage").val();
		
		var constituencyId =$("#villageLevelConstNames").val();
		var districtId =$("#villageLevelDistNames").val();
		var locationId =0;
		var locationLevelType = 0;
		
		if(constituencyId !=0){
			locationId = constituencyId;
			locationLevelType = 4;
		}else if(constituencyId ==0){
			$("#villageLevelNames").html('');
			$("#villageLevelNames").trigger("chosen:updated")
			$("#villageLevelMandalNames").html('');
			$("#villageLevelMandalNames").trigger("chosen:updated")
			locationId = districtId;
			locationLevelType = 3;
		}
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelMandalNames',6);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".villageLevelMandalCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var grantType = $("#grantTypeVillage").val();
		
		var mandalId =$("#villageLevelMandalNames").val();
		var constituencyId =$("#villageLevelConstNames").val();
		
		var locationLevelType = 0;
		var locationId=0;
		
		if(mandalId !=0){
			locationId = mandalId;
			locationLevelType = 5
		}else if(mandalId ==0){
			$("#villageLevelNames").html('');
			$("#villageLevelNames").trigger("chosen:updated")
			locationId = constituencyId;
			locationLevelType = 4
		}
		getAllSubLocationsBySuperLocationId(locationId,'villageLevelNames',6);
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	$(document).on("change",".villageLevelCls",function(){
		var sortingType = getSelectedType().sortingType;
		var orderType = getSelectedType().orderType;
		var blockType = getblockTypeMandal();
		var cummulativeType ='';
		$(".tabCummulativeVillage li").each(function(i, obj){
			 if($(this).hasClass("active")){
				  cummulativeType = $(this).attr("attr_type");
			 }
			
		});
		var programId =$("#programNamesVillage").val();
		var subProgramId =$("#subProgramNamesVillage").val();
		var grantType = $("#grantTypeVillage").val();
		
		var villageId =$("#villageLevelNames").val();
		var mandalId =$("#villageLevelMandalNames").val();
		
		var locationId=0;
		var locationLevelType = 0;
		
		if(villageId !=0){
			locationId = villageId;
			locationLevelType =6;
		}else if(villageId == 0){
			locationId = mandalId;
			locationLevelType =5;
		}
		if(cummulativeType == "normalView"){
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"",'change',grantType);
		}else{
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw',sortingType,orderType,locationId,locationLevelType,programId,subProgramId,"cumulative",'change',grantType);
		}
		
			
	});
	
	$(document).on("click",".comapreFinancialYearCls",function(){
	var type = $(this).attr("attr_type");
	if(globalLevelId == 0){
		globalLocationId=0;
		globalLocationLevelCompTypeId =0;
	}else{
		globalLocationId = globalLocationId
		globalLocationLevelCompTypeId =globalLocationLevelTypeId;
	}
	if(type == "distComp"){
		compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
	}else if(type == "constComp"){
		compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
	}else if(type == "mandalComp"){
		compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
	}else if(type == "villageComp"){
		compareFundsBetweenFinancialYears(6,'comparionVillageLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
	}
	
});

function compareFundsBetweenFinancialYears(levelId,divId,globalLocationId,globalLocationLevelTypeId){ 
	
	
		var financialYrIdList=[];
		var firstFinancialYearArr=[];
		var secondFinancialYearArr=[];
	   var deptIds =$("#DepartmentsId").val();
		
		var temp=0;
		if(levelId == 3){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear = $(".multiDistYearCls").val();
			var secondFinancialYear = $(".distYearCls").val();
			
			if( firstFinancialYear > secondFinancialYear ){
				temp = firstFinancialYear;
				firstFinancialYear = secondFinancialYear;
				secondFinancialYear = temp;
			}
			financialYrIdList.push(firstFinancialYear);
			financialYrIdList.push(secondFinancialYear);
			firstFinancialYearArr.push(firstFinancialYear);
			secondFinancialYearArr.push(secondFinancialYear);
			if ($.inArray(firstFinancialYear, secondFinancialYear) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 4){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear4 = $(".multiConYearCls").val();
			var secondFinancialYear4 = $(".conYearCls").val();
			if( firstFinancialYear4 > secondFinancialYear4 ){
				temp = firstFinancialYear4;
				firstFinancialYear4 = secondFinancialYear4;
				secondFinancialYear4 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear4);
			financialYrIdList.push(secondFinancialYear4);
			firstFinancialYearArr.push(firstFinancialYear4);
			secondFinancialYearArr.push(secondFinancialYear4);
			if ($.inArray(firstFinancialYear4, secondFinancialYear4) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 5){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear5 = $(".multiMandalYearCls").val();
			var secondFinancialYear5 = $(".mandalYearCls").val();
			if( firstFinancialYear5 > secondFinancialYear5 ){
				temp = firstFinancialYear5;
				firstFinancialYear5 = secondFinancialYear5;
				secondFinancialYear5 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear5);
			financialYrIdList.push(secondFinancialYear5);
			firstFinancialYearArr.push(firstFinancialYear5);
			secondFinancialYearArr.push(secondFinancialYear5);
			if ($.inArray(firstFinancialYear5, secondFinancialYear5) != -1){
				alert("Please Select Different Year")
				return;
			}
		}else if(levelId == 6){
			var firstFinancialYear6 = $(".multiVillageYearCls").val();
			var secondFinancialYear6 = $(".villageYearCls").val();
			if( firstFinancialYear6 > secondFinancialYear6 ){
				temp = firstFinancialYear6;
				firstFinancialYear6 = secondFinancialYear6;
				secondFinancialYear6 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear6);
			financialYrIdList.push(secondFinancialYear6);
			firstFinancialYearArr.push(firstFinancialYear6);
			secondFinancialYearArr.push(secondFinancialYear6);
			if ($.inArray(firstFinancialYear6, secondFinancialYear6) != -1){
				alert("Please Select Different Year")
				return;
			}
		}
	
		
			
	 
	 
	 
	 $("#"+divId).html(spinner);
    var json = {
      blockLevelId : levelId,
      financialYrIdList : financialYrIdList,
	  deptIdsList:deptIds,
	  searchLevelId: globalLocationLevelTypeId,
	  searchLevelValue :globalLocationId 
    }
    $.ajax({
      url : "compareFundsBetweenFinancialYears",     
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		if(ajaxresp !=null && ajaxresp.length>0){
			buildCompareFundsBetweenFinancialYears(ajaxresp,financialYrIdList,firstFinancialYearArr,secondFinancialYearArr,divId,levelId);
		}else{
			$("#"+divId).html("NO DATA AVAILABLE")
		}
		
      }
    
    });
   }
   
   function buildCompareFundsBetweenFinancialYears(result,financialYrIdList,firstFinancialYearArr,secondFinancialYearArr,divId,levelId){
	   
	   var yearLen =financialYrIdList.length;
	   
	    var globalYearObj =  {"1":"2014-2015","2":"2015-2016","3":"2016-2017","4":"2017-2018"};
		 $("#"+divId).removeClass('comparionStyle')
		 $("#"+divId).addClass('comparionStyle1')
	   var str='';
	   if(result !=null && result.length>0){
			if($windowWidth < 768)
			{
				str+='<div class="table-responsive">';
			}
		    str+='<table class="table table-bordered table-striped">';
			if(result[0].rangeList !=null && result[0].rangeList.length>0){
			    str+='<thead>';
				str+='<tr>';
				 str+='<th><span class="first"> '+globalYearObj[firstFinancialYearArr]+'</span><span class="second"> '+globalYearObj[secondFinancialYearArr]+'</span></th>';
			   for(var i in result[0].rangeList){
					   if(yearLen >=3){
							str+='<th colspan="2">'+result[0].rangeList[i].name+'</th>'
						}else{
							if(i == 0){
								str+='<th>Total</th>'
							}else if(i == 1){
								str+='<th>'+result[0].rangeList[i].name+'</th>'
							}else if(result[0].rangeList[i].name == ""){
								str+='<th>'+result[0].rangeList[i].name+'</th>'
							}
							else{
								str+='<th> > '+result[0].rangeList[i].name+'</th>'
							}
							
						}
				}
			   str+='</tr>';
			   str+='</thead>';
			   str+='<tbody>';
				for(var i in result){
				   var verticalRanges = result[i].range.split('/');
				   str+='<tr>';
						if(yearLen >=3){
							str+='<td style="background-color:#EAEBFF">'+verticalRanges[0]+'</td>';
							str+='<td style="background-color:#FFF3E9">'+verticalRanges[1]+'</td>';
						}else{
							if(i == 0){
								 str+='<td>'+result[i].range+'</td>';
							}else{
								 str+='<td> > '+result[i].range+'</td>';
							}
							 
						}
					  if(result[i].rangeList !=null && result[i].rangeList.length>0){
							for(var j in result[i].rangeList){
								var rangeValue='';
								var locationIds='';
								if(result[i].rangeList[j].value == null || result[i].rangeList[j].value == "0/0"){
									var emptyRangeVal = "-,-";
									var emptyLocIds="0,0";
									rangeValue = emptyRangeVal.split(',');
									locationIds = emptyLocIds.split(',');
									
								}else{
									rangeValue = result[i].rangeList[j].value.split('/');
									locationIds = result[i].rangeList[j].locationIds.split('-');
								}
								if(yearLen >=3){
									str+='<td class="compClickCls" style="background-color:#EAEBFF;cursor:pointer;color:green;" attr_locationIds="'+locationIds[0]+'" attr_scope_id="'+levelId+'">'+rangeValue[0]+'</td>';
									 str+='<td class="compClickCls" style="background-color:#FFF3E9;cursor:pointer;color:green;" attr_locationIds="'+locationIds[0]+'" attr_scope_id="'+levelId+'">'+rangeValue[1]+'</td>';
								}else{
									if(result[i].rangeList[j].value > 0){
										str+='<td class="compClickCls" attr_locationIds="'+result[i].rangeList[j].locationIds+'" style="cursor:pointer;color:green;"attr_scope_id="'+levelId+'">'+result[i].rangeList[j].value+'</td>';
									}else{
										str+='<td >-</td>';
									}
									 
								}
							}
						}
					
					str+='</tr>';
				}
				
			   str+='</tbody>';
		   }
			str+='</table>';
			if($windowWidth < 768)
			{
				str+='</div>';
			}
		}
		$("#"+divId).html(str)
	}
	
	$(document).on("click",".compClickCls",function(){
	 var blockLvlId = $(this).attr("attr_scope_id");
	 var levlValue = $(this).attr("attr_locationIds");
	 getLocationWiseAmountAndCountDetails(blockLvlId,'locationsModal','overview',levlValue);
	// getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId);
	});

	$(document).on("click",".fundSanctionCls",function(){
	 var blockLvlId = $(this).attr("attr_scope_id");
	  var levlValue = $(this).attr("attr_level_value");
	  var financialYrId =$(this).attr("attr_financial_yr_id");
	  var schemeId = $(this).attr("attr_scheme_id");
	  var deptId = $(this).attr("attr_dept_id");
	  var locationName=$(this).attr("attr_location_name");
	  var levelName=$(this).attr("attr_level_name");
	  var locationStrIdsForMgnregs=$(this).attr("attr_location_strids");
	  var type = $(this).attr("attr_mgnregs_name");
	  var viewType = $(this).attr("attr_view_type");
	  if(type == "MGNREGS"){
		  getMgnregsFMSWorksDetails(locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName);
	  }else{
		  getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId,locationName,levelName);
	  }
	  
	});
	
	function getLocationWiseFundSanctionDetails(blockLvlId,levlValue,financialYrId,schemeId,deptId,locationName,levelName){ 
	if (schemeId == 0) {// MGNREGS
		return ;
	}
	
	$("#fundModal").modal('show');
	$("#diptNameId").html('<h4 class="text-capital"><b>'+locationName+'&nbsp;&nbsp; '+levelName+' &nbsp;&nbsp;  Location Wise Fund Overview</b></h4>');
	$("#fundSanctionModal").html(spinner);
    var searchLvlVals = [];
	searchLvlVals.push(levlValue);
    var financialYrIdList =[];
	//var financialYrIdList = $('#financialYearId').val();
	 var financialyr = financialYrId.split(',');
	for(var i = 0; i < financialyr.length; i++)
       {  
			 financialYrIdList.push(financialyr[i]);
       } 

    var schemeIdsList = [];
	schemeIdsList.push(schemeId);
    var deptIdsList = [];
	deptIdsList=$('#DepartmentsId').val();
	
    var subProgramIdsList = [];
	var programId=0;
	if(blockLvlId == 2){
		programId = $("#programNamesState").val();
	}else if(blockLvlId == 3){
		programId = $("#programNamesDistrict").val();
	}else if(blockLvlId == 4){
		programId = $("#programNamesConst").val();
	}else if(blockLvlId == 5){
		programId = $("#programNamesMandal").val();
	}else if(blockLvlId == 6){
		programId = $("#programNamesVillage").val();
	}
	subProgramIdsList.push(programId);
	
    var json = {
          blockLevelId : blockLvlId, 
          searchLvlVals : searchLvlVals,
          financialYrIdList : financialYrIdList,
          schemeIdsList : schemeIdsList,  
          deptIdsList : deptIdsList,       
          fromDateStr : glStartDate,
          toDateStr : glEndDate,
		  subProgramIdsList:subProgramIdsList
        }
    $.ajax({ 
      url: 'getLocationWiseFundSanctionDetails', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
      if(result != null && result.length>0){
      buildLocationWiseFundSanctionDetails(result,blockLvlId);
    }
    });
  }
  function buildLocationWiseFundSanctionDetails(result,blockLvlId){
  
  var str = '';
	if($windowWidth < 768)
	{
		str+='<div class="table-responsive">';
	}
	str+='<table class="table table-condensed table-bordered table-striped" id="tableId">';
		str+='<thead>';
			str+='<th class="text-capital">District Name</th>';
			str+='<th class="text-capital">Location Name</th>';
			str+='<th class="text-capital">Work Name</th>';
			str+='<th class="text-capital">Department</th>'; 
			str+='<th class="text-capital" > Scheme Name</th>';
			str+='<th class="text-capital"> G.o</th>';
			str+='<th class="text-capital"> Sanction Amount</th>';
		str+='</thead>';
		str+='<tbody>';
	for(var i in result){
		str+='<tr>'; 
		if(result[i].locationName != null){
			 str+='<td>'+result[i].locationName+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		 if(result[i].locName != null){
			 str+='<td>'+result[i].locName+'</td>';
		}else{
			 str+='<td>-</td>';
		}
			 
		if(result[i].workName != null){
			 str+='<td>'+result[i].workName+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].departmentName != null){
			 str+='<td>'+result[i].departmentName+'</td>';
		}else{
			 str+='<td>-</td>';
		}    
		if(result[i].schemeName != null){
			 str+='<td>'+result[i].schemeName+'</td>';
		}else{
			 str+='<td>-</td>';
		}  
		if(result[i].goNoDate != null){
			if(result[i].filePath != null && result[i].filePath.length > 1){
				str+='<td style="cursor:pointer;" class="viewPdfCls" data-toggle="tooltip" data-placement="top" title="" data-original-title="Click to get govt order document." attr_filePath="'+result[i].filePath+'"><a>'+result[i].goNoDate+'</a></td>';
			}else{
				str+='<td>'+result[i].goNoDate+'</td>';
			}
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].sactionAmount != null){
			 str+='<td>'+result[i].sactionAmount+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		str+='</tr>';     
	}
	str+='</tbody>';  
	str+='</table>';  
	if($windowWidth < 768)
	{
		str+='</div>';
	}
	$("#fundSanctionModal").html(str);   
	$("#tableId").dataTable({
		"iDisplayLength": 15,
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Dashboard',
				filename:  'dashboard'+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Dashboard',
				filename:  'dashboard '+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});  
}
function getLocationWiseAmountAndCountDetails(levelId,divId,type,levelValueStr){
		$("#locDivModal").modal('show');
		$("#"+divId).html(spinner);   
		var levelValues = [];
		var strx   = levelValueStr.split(',');
			levelValues = levelValues.concat(strx);
			
		var financialYrIdList = $('#financialYearId').val();
		/*if(divId == 'locationsModal'){ 
		   financialYrIdList.length = 0;//remove all items 
		   financialYrIdList.push($(".multiDistYearCls").val());
		}*/
		
		var financialYrIdList=[];
		var firstFinancialYearArr=[];
		var secondFinancialYearArr=[];
	   var deptIds =$("#DepartmentsId").val();
		
		var temp=0;
		if(divId == 'locationsModal'){ 
		if(levelId == 3){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear = $(".multiDistYearCls").val();
			var secondFinancialYear = $(".distYearCls").val();
			
			if( firstFinancialYear > secondFinancialYear ){
				temp = firstFinancialYear;
				firstFinancialYear = secondFinancialYear;
				secondFinancialYear = temp;
			}
			financialYrIdList.push(firstFinancialYear);
			financialYrIdList.push(secondFinancialYear);
		}else if(levelId == 4){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear4 = $(".multiConYearCls").val();
			var secondFinancialYear4 = $(".conYearCls").val();
			if( firstFinancialYear4 > secondFinancialYear4 ){
				temp = firstFinancialYear4;
				firstFinancialYear4 = secondFinancialYear4;
				secondFinancialYear4 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear4);
			financialYrIdList.push(secondFinancialYear4);
		}else if(levelId == 5){
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			var firstFinancialYear5 = $(".multiMandalYearCls").val();
			var secondFinancialYear5 = $(".mandalYearCls").val();
			if( firstFinancialYear5 > secondFinancialYear5 ){
				temp = firstFinancialYear5;
				firstFinancialYear5 = secondFinancialYear5;
				secondFinancialYear5 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear5);
			financialYrIdList.push(secondFinancialYear5);
		}else if(levelId == 6){
			var firstFinancialYear6 = $(".multiVillageYearCls").val();
			var secondFinancialYear6 = $(".villageYearCls").val();
			if( firstFinancialYear6 > secondFinancialYear6 ){
				temp = firstFinancialYear6;
				firstFinancialYear6 = secondFinancialYear6;
				secondFinancialYear6 = temp;
			}
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList=[];
			firstFinancialYearArr=[];
			secondFinancialYearArr=[];
			financialYrIdList.push(firstFinancialYear6);
			financialYrIdList.push(secondFinancialYear6);
		}
		}
		var json = {
			blockLevelId : levelId, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			sortingType : "name",      //name,count    
			order : "desc",   //asc,desc
			fromDateStr : glStartDate,//"01-06-2013",       
			toDateStr : glEndDate,//"10-06-2020",
			deptIdsList:deptIds
		}
		$.ajax({
			url : "getLocationWiseAmountAndCountDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				$("#"+divId).html("");
				if(ajaxresp != null && ajaxresp.length > 0)
				{
					
					buildLocationsAmountDetailsOverview(ajaxresp,divId,type,levelId);
				}else{
					$("#"+divId).html("NO DATA AVAILABLE");
				}
			}
		});
	}
	
	function buildLocationsAmountDetailsOverview(result,divId,type,levelId){
		var table='';
		if(type == 'overview')
		{
			if($windowWidth < 768)
			{
				table+='<div class="table-responsive">';
			}
			var levelName='';
			table+='<table class="table table-bordered table-striped table-condensed" id="dataTable'+divId+'" style="width:100%;">';
				table+='<thead class="text-center">';
					table+='<tr>';
						table+='<th></th>';
						table+='<th></th>';
						for(var i in result[0].locationList1)
						{
							table+='<th colspan="2" class="text-center text-capital">'+result[0].locationList1[i].financialYear+'</th>';
						}
					table+='</tr>';
					table+='<tr>';
						//table+='<th class="text-capital">ID</th>';
						if(levelId == '2')
						{
							levelName = "STATE";
							table+='<th class="text-capital">State</th>';
						}else if(levelId == '3'){
							levelName = "DISTRICT";
							table+='<th class="text-capital">District</th>';
						}else if(levelId == '4')
						{
							levelName = "CONSTITUENCY";
							table+='<th class="text-capital">Constituency</th>';
						}else if(levelId == '5')
						{
							levelName = "MANDAL";
							table+='<th class="text-capital">Mandal</th>';
						}
						
						for(var i in result[0].locationList1)
						{
							table+='<th class="text-center no-right-border text-capital">No</th>';
							table+='<th class="text-center text-capital">Amt.</th>';
						}
					table+='</tr>';
				table+='</thead>';
				table+='<tbody>';
					for(var i in result){
						table+='<tr>';
							//table+='<td>'+result[i].locationId+'</td>';
							table+='<td>'+result[i].locationName+'</td>';
							
							for(var j in result[i].locationList1){
								if(result[i].locationList1[j].financialYearId != 0){
									newYearId = result[i].locationList1[j].financialYearId;
								}else{
                                    newYearId=result[i].locationList1[0].financialYearId;
									var len = result[i].locationList1.length;
									len = len - 1;
									for(var k=1 ; k < len ; k++){
										newYearId = newYearId+","+result[i].locationList1[k].financialYearId;
									}      
								} 
								if(result[i].locationList1[j].count != null && result[i].locationList1[j].count > 0){
									table+='<td class="text-center no-right-border fundSanctionCls" attr_scope_id="'+levelId+'" attr_level_value="'+result[i].locationId+'" attr_financial_yr_id="'+newYearId+'" attr_scheme_id="0" attr_dept_id="0" attr_location_name="'+result[i].locationName+'" attr_level_name="'+levelName+'" style="cursor:pointer;color:green;">'+result[i].locationList1[j].count+'</td>';
								}else{
									table+='<td class="text-center no-right-border">-</td>';
								}
								table+='<td class="text-center">'+result[i].locationList1[j].amunt+'</td>';
							}
						table+='</tr>';
					}
				table+='</tbody>';
			table+='</table>';
			if($windowWidth < 768)
			{
				str+='</div>';
			}
		}
		
		$("#"+divId).html(table);
		
		$("#dataTable"+divId).dataTable({
			"iDisplayLength": 15,
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o"></i>',
					titleAttr: 'CSV',
					title:	   divId,
					filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o"></i>',
					titleAttr: 'PDF',
					title:	   divId,
					filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					orientation: "landscape",
					pageSize:'A3'
				}
			]
		});
		
	}
$(document).on('click','.closeShowPdfCls',function(){
		setTimeout(function(){
			$("body").addClass("modal-open");
		},400);
	});
	$(document).on('click','.viewPdfCls',function(){
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if(extName.trim()=="pdf" || extName.trim()=="PDF"){
			if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i)) || navigator.userAgent.match(/Mobile|Windows Phone|Lumia|Android|webOS|iPhone|iPod|Blackberry|PlayBook|BB10|Opera Mini|\bCrMo\/|Opera Mobi/i)) {
				$("#govtOrderDocumentId").modal("hide");
				window.open('http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}else{
					$('#govtOrderDocumentId').modal({
						show: true,
						keyboard: false,
						backdrop: 'static'
					});
					str+='<object data="http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'" type="application/pdf" width="100%" height="400px;" internalinstanceid="3" title=""></object>';
					//str+='<iframe src="http://www.mydepartments.in/PRRWS/Govt_Orders/'+dbFilePath+'" width="100%" height="800"></iframe>';
					$("#govtOrderDocumentId").find(".modal-title").html($(this).html());
					$("#govtOrderDocumentBodyId").html(str);       
			}   
		}		
	})
	
	
	$(document).on("change","#overviewSelect",function(){
		$(".clearCls").html(spinner)
		var selectedHtml = $(this).html();
		var programId = $(this).val();
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
			$(".clearCls").html('');	
			console.log(overviewSelectBoxData);
			//getSchemeWiseOverviewDetails(0,programId);
		if(programId !=0){
				var overviewTable = '';
				var selectedValueData = '';
				overviewTable+='<div class="col-sm-12">';
					overviewTable+='<div class="panel panel-default">';
						overviewTable+='<div class="panel-body">';
							overviewTable+='<table class="table">';
								overviewTable+='<thead>';
									overviewTable+='<th><select id="overviewSelect" >';
										overviewTable+='<option value="0">ALL PROGRAMMES </option>';
										for(var i in overviewSelectBoxData){
											if(overviewSelectBoxData[i].id == programId)
											{
												selectedValueData = overviewSelectBoxData[i].name;
												overviewTable+='<option value="'+overviewSelectBoxData[i].id+'" selected>'+overviewSelectBoxData[i].name+' </option>';
											}else{
												overviewTable+='<option value="'+overviewSelectBoxData[i].id+'">'+overviewSelectBoxData[i].name+' </option>';
											}
											
										}
									overviewTable+='</select></th>';
									overviewTable+='<th class="text-center"><span class="rounded-circle fa fa-inr"></span><p class="text-capital">total amount</p></th>';
									overviewTable+='<th><span class="rounded-circle">D</span><p class="text-capital m_top10">district</p></th>';
									overviewTable+='<th><span class="rounded-circle">C</span><p class="text-capital m_top10">constituency</p></th>';
									overviewTable+='<th><span class="rounded-circle">M</span><p class="text-capital m_top10">Mandal</p></th>';
								overviewTable+='</thead>';
								overviewTable+='<tr id="blockProgramInd">';
									overviewTable+='<td class="text-capital clearCls"><h4>'+selectedValueData+'</h4></td>';
									overviewTable+='<td class="total'+programId+' text-center clearCls"></td>';
									overviewTable+='<td class="district'+programId+' clearCls"></td>';
									overviewTable+='<td class="constituency'+programId+' clearCls"></td>';
									overviewTable+='<td class="mandal'+programId+' clearCls"></td>';
								overviewTable+='</tr>';
							overviewTable+='</table>';
						overviewTable+='</div>';
					overviewTable+='</div>';
				overviewTable+='</div>';
				$("#overViewTableId").html(overviewTable);
				$("#overviewSelect").chosen();
				getSchemeWiseOverviewDetails(0,programId);
		}else{
			$("#overViewTableId").html(spinner);
				var overviewTable = '';
				overviewTable+='<div class="col-sm-12">';
					overviewTable+='<div class="panel panel-default">';
						overviewTable+='<div class="panel-body">';
							overviewTable+='<div class="table-responsive">';
								overviewTable+='<table class="table">';
									overviewTable+='<thead>';
										overviewTable+='<th><select id="overviewSelect" >';
											overviewTable+='<option value="0">ALL PROGRAMMES </option>';
											for(var i in overviewSelectBoxData){
												overviewTable+='<option value="'+overviewSelectBoxData[i].id+'">'+overviewSelectBoxData[i].name+' </option>';
											}
											
										overviewTable+='</select></th>';
										overviewTable+='<th class="text-center"><span class="rounded-circle fa fa-inr"></span><p class="text-capital">total amount</p></th>';
										overviewTable+='<th><span class="rounded-circle">D</span><p class="text-capital m_top10">district</p></th>';
										overviewTable+='<th><span class="rounded-circle">C</span><p class="text-capital m_top10">constituency</p></th>';
										overviewTable+='<th><span class="rounded-circle">M</span><p class="text-capital m_top10">Mandal</p></th>';
									overviewTable+='</thead>';
									overviewTable+='<tbody>';
										overviewTable+='<tr id="blockProgram0">';
											overviewTable+='<td class="text-capital clearCls" style="border-right:2px dashed #777;"><h4>ALL </h4></td>';
											overviewTable+='<td class="total0 text-center clearCls"></td>';
											overviewTable+='<td class="district0 clearCls"></td>';
											overviewTable+='<td class="constituency0 clearCls"></td>';
											overviewTable+='<td class="mandal0 clearCls"></td>';
										overviewTable+='</tr>';
										var k = 1;					
										for(var i in overviewSelectBoxData)
										{
											overviewTable+='<tr id="blockProgram'+overviewSelectBoxData[i].id+'">';
												overviewTable+='<td class="text-capital clearCls" style="border-right:2px dashed #777;"><h4>'+overviewSelectBoxData[i].name+'</h4></td>';
												overviewTable+='<td class="total'+overviewSelectBoxData[i].id+' text-center clearCls"></td>';
												overviewTable+='<td class="district'+overviewSelectBoxData[i].id+' clearCls"></td>';
												overviewTable+='<td class="constituency'+overviewSelectBoxData[i].id+' clearCls"></td>';
												overviewTable+='<td class="mandal'+overviewSelectBoxData[i].id+' clearCls"></td>';
											overviewTable+='</tr>';
											k = k +1;
										}
										 
									overviewTable+='</tbody>';
								overviewTable+='</table>';
							overviewTable+='</div>';
						overviewTable+='</div>';
					overviewTable+='</div>';
				overviewTable+='</div>';
					$("#overViewTableId").html(overviewTable);
					$("#overviewSelect").chosen();
			getSchemeWiseOverviewDetails(0,0);
			for(var i in overviewSelectBoxData)
			{
				var subprogramId = overviewSelectBoxData[i].id
				getSchemeWiseOverviewDetails(subprogramId,subprogramId);
			} 
		}
			
		});
		
	});
	/* $(document).on("click","#selectedName",function(){
		$(".arrowIconChanged").parent().find('i').removeClass("fa-chevron-down");
		$(".arrowIconChanged").parent().find('i').addClass("fa-chevron-up");
		
	}); */	
	$(document).on("click",".menuDataCollapse",function(){
		$(".multi-level-selection-menu").css("display","none");
		$("#selectedName").html($(this).html());
		
		globalLocationLevelTypeId = $(this).attr("attr_levelidvalue");
		globalLocationId = $(this).attr("attr_id");
		globalLevelId = $(this).attr("attr_levelid");
		globalLocationName=$(this).attr("attr_name");
		
		districtIdForMenu = "3"+$(this).attr("attr_distid");
		districtNameForMenu = $(this).attr("attr_dist_name");
		
		consIdForMenu = "4"+$(this).attr("attr_constid");
		consNameForMenu = $(this).attr("attr_cons_name");
		
			
		/* $(".arrowIconChanged").find('i').removeClass("fa-chevron-up");
		$(".arrowIconChanged").find('i').addClass("fa-chevron-down"); */
		getALlProgramesAmountDetails();
		getGovtSchemesDetails("overviewSelect");
		getGovtSchemesDetails("programNamesState");
		getGovtSubProgramsDetails(0,"subProgramNamesState");
		getGovtGrantTypeDetails(0,0, "grantTypeState");
		$("#selectedName").attr("attr_levelidvalue",globalLocationLevelTypeId)
		$("#selectedName").attr("attr_id",globalLocationId)
		$("#selectedName").attr("attr_levelid",globalLevelId)
		$("#selectedName").attr("attr_name",globalLocationName)
		$("#selectedName").attr("attr_distid",districtIdForMenu)
		$("#selectedName").attr("attr_dist_name",districtNameForMenu)
		$("#selectedName").attr("attr_constid",consIdForMenu)
		$("#selectedName").attr("attr_cons_name",consNameForMenu)
		
		
		
		
		if(globalLevelId == 0 || globalLevelId == 1){
			$(".parliamentCls").show();
		}else{
			$(".parliamentCls").hide();
		}
		if(globalLocationLevelTypeId == 0){
			$(".villageBlock").show();
			$(".constituencyBlock").show();
			$(".mandalBlock").show();
			$(".districtBlock").show();
			$(".stateBlock").show();
		}else if(globalLocationLevelTypeId == 3){
			$(".stateBlock").hide();
			$(".districtBlock").show();
			$(".villageBlock").show();
			$(".constituencyBlock").show();
			$(".mandalBlock").show();
		}else if(globalLocationLevelTypeId == 4){
			$(".stateBlock").hide();
			$(".districtBlock").hide();
			$(".villageBlock").show();
			$(".constituencyBlock").show();
			$(".mandalBlock").show();
		}else if(globalLocationLevelTypeId == 5){
			$(".stateBlock").hide();
			$(".districtBlock").hide();
			$(".villageBlock").show();
			$(".constituencyBlock").hide();
			$(".mandalBlock").show();
		}
		if(!$(".collapseActiveStateCls").hasClass("collapsed")){
			if(globalLevelId == 0 || globalLevelId == 1){
				globalLocationId = 0;
				globalLocationLevelTypeId = 3;
			}else{
				globalLocationId = globalLocationId;
				globalLocationLevelTypeId = globalLocationLevelTypeId;
			}
			getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw','','',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
		}
		
		if(!$(".collapseActiveDistCls").hasClass("collapsed")){
			if(globalLevelId == 0 || globalLevelId == 1){
				globalLocationId = 0;
				globalLocationLevelTypeId = 3;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'distLevelDistrictNames',3);
			}else{
				globalLocationId = globalLocationId;
				globalLocationLevelTypeId = globalLocationLevelTypeId;
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				var districtIdForMenuForDistrictBlock = "3"+globalLocationId;
				
				
				$("#distLevelDistrictNames").html('');
				$("#distLevelDistrictNames").append("<option value="+districtIdForMenuForDistrictBlock+">"+globalLocationName+" </option>");
				$("#distLevelDistrictNames").trigger("chosen:updated");
			}
			
			getSchemeWiseLocationWiseAmountDetails(3,'distLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			getGovtSchemesDetails("programNamesDistrict");
			getGovtSubProgramsDetails(0,"subProgramNamesDistrict");
			getGovtGrantTypeDetails(0,0, "grantTypeDistrict");
			compareFundsBetweenFinancialYears(3,'comparionDistLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
		if(!$(".collapseActiveConstCls").hasClass("collapsed")){
			if(globalLevelId == 0 || globalLevelId == 1){
				globalLocationId = 0;
				globalLocationLevelTypeId = 4;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'constLevelDistNames',4);
			}else{
				globalLocationId = globalLocationId;
				globalLocationLevelTypeId = globalLocationLevelTypeId;
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				if(globalLocationLevelTypeId == 3){
					var districtIdForMenuForConsBlock = "3"+globalLocationId;
					$("#constLevelDistNames").html('');
					$("#constLevelDistNames").append("<option value="+districtIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#constLevelDistNames").trigger("chosen:updated");
					getAllSubLocationsBySuperLocationId(districtIdForMenuForConsBlock,'constLevelConstNames',4);
					
				}else if(globalLocationLevelTypeId == 4){
					var constituencyIdForMenuForConsBlock = "4"+globalLocationId;
					$("#constLevelDistNames").html('');
					$("#constLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#constLevelDistNames").trigger("chosen:updated");
					
					$("#constLevelConstNames").html('');
					$("#constLevelConstNames").append("<option value="+constituencyIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#constLevelConstNames").trigger("chosen:updated");
				}
				
				
			}
			
			
			getSchemeWiseLocationWiseAmountDetails(4,'consLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
			getGovtSchemesDetails("programNamesConst");
			getGovtSubProgramsDetails(0,"subProgramNamesConst");
			getGovtGrantTypeDetails(0,0, "grantTypeConst");
			compareFundsBetweenFinancialYears(4,'comparionConstLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
		if(!$(".collapseActiveMandalCls").hasClass("collapsed")){
			if(globalLevelId == 0 || globalLevelId == 1){
				globalLocationId = 0;
				globalLocationLevelTypeId = 5;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'mandalLevelDistNames',5);
			}else{
				globalLocationId = globalLocationId;
				globalLocationLevelTypeId = globalLocationLevelTypeId;
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				if(globalLocationLevelTypeId == 3){
					var districtIdForMenuForConsBlock1 = "3"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenuForConsBlock1+">"+globalLocationName+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					getAllSubLocationsBySuperLocationId(districtIdForMenuForConsBlock1,'mandalLevelConstNames',5);
				
					$("#mandalLevelMandalNames").html('');
					$("#mandalLevelMandalNames").trigger("chosen:updated");
				}
				else if(globalLocationLevelTypeId == 4){
					var mandalIdForMenuForConsBlock1 = "4"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					
					$("#mandalLevelConstNames").html('');
					$("#mandalLevelConstNames").append("<option value="+mandalIdForMenuForConsBlock1+">"+globalLocationName+" </option>");
					$("#mandalLevelConstNames").trigger("chosen:updated");
					getAllSubLocationsBySuperLocationId(mandalIdForMenuForConsBlock1,'mandalLevelMandalNames',5);
					
				}else if(globalLocationLevelTypeId == 5){
					var mandalIdForMenuForConsBlock = "5"+globalLocationId;
					$("#mandalLevelDistNames").html('');
					$("#mandalLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#mandalLevelDistNames").trigger("chosen:updated");
					
					$("#mandalLevelConstNames").html('');
					$("#mandalLevelConstNames").append("<option value="+consIdForMenu+">"+consNameForMenu+" </option>");
					$("#mandalLevelConstNames").trigger("chosen:updated");
					
					$("#mandalLevelMandalNames").html('');
					$("#mandalLevelMandalNames").append("<option value="+mandalIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#mandalLevelMandalNames").trigger("chosen:updated");
				}
			}//ara
			
			
			getSchemeWiseLocationWiseAmountDetails(5,'mandalLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
		
			getGovtSchemesDetails("programNamesMandal");
			getGovtSubProgramsDetails(0,"subProgramNamesMandal");
			getGovtGrantTypeDetails(0,0, "grantTypeMandal");
			compareFundsBetweenFinancialYears(5,'comparionMandalLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
		if(!$(".collapseActiveVillageCls").hasClass("collapsed")){
			if(globalLevelId == 0 || globalLevelId == 1){
				globalLocationId = 0;
				globalLocationLevelTypeId = 6;
				globalLocationLevelCompTypeId =0;
				getAllSubLocationsBySuperLocationId(21,'villageLevelDistNames',6);
			}else{
				globalLocationId = globalLocationId;
				globalLocationLevelTypeId = globalLocationLevelTypeId;
				globalLocationLevelCompTypeId =globalLocationLevelTypeId;
				
				if(globalLocationLevelTypeId == 3){
					var districtIdForMenuForConsBlock2 = "3"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenuForConsBlock2+">"+globalLocationName+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					getAllSubLocationsBySuperLocationId(districtIdForMenuForConsBlock2,'villageLevelConstNames',6);
					
					
					$("#villageLevelMandalNames").html('');
					$("#villageLevelMandalNames").trigger("chosen:updated");
				}
				else if(globalLocationLevelTypeId == 4){
					var mandalIdForMenuForConsBlock2 = "4"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					
					$("#villageLevelConstNames").html('');
					$("#villageLevelConstNames").append("<option value="+mandalIdForMenuForConsBlock2+">"+globalLocationName+" </option>");
					$("#villageLevelConstNames").trigger("chosen:updated");
					getAllSubLocationsBySuperLocationId(mandalIdForMenuForConsBlock2,'villageLevelMandalNames',6);
					
					
				}else if(globalLocationLevelTypeId == 5){
					var mandalIdForMenuForConsBlock = "5"+globalLocationId;
					$("#villageLevelDistNames").html('');
					$("#villageLevelDistNames").append("<option value="+districtIdForMenu+">"+districtNameForMenu+" </option>");
					$("#villageLevelDistNames").trigger("chosen:updated");
					
					$("#villageLevelConstNames").html('');
					$("#villageLevelConstNames").append("<option value="+consIdForMenu+">"+consNameForMenu+" </option>");
					$("#villageLevelConstNames").trigger("chosen:updated");
					
					$("#villageLevelMandalNames").html('');
					$("#villageLevelMandalNames").append("<option value="+mandalIdForMenuForConsBlock+">"+globalLocationName+" </option>");
					$("#villageLevelMandalNames").trigger("chosen:updated");
				}
			}
			getSchemeWiseLocationWiseAmountDetails(6,'villageLevlOvervw','count','desc',globalLocationId,globalLocationLevelTypeId,0,0,"cumulative",'onload',0);
					
			getGovtSchemesDetails("programNamesVillage");
			getGovtSubProgramsDetails(0,"subProgramNamesVillage");	
			getGovtGrantTypeDetails(0,0, "grantTypeVillage");
			compareFundsBetweenFinancialYears(6,'comparionVillageLevlOvervwTable',globalLocationId,globalLocationLevelCompTypeId);
		}
		
	});	
	$(document).on("click","[role='tablist'] a",function(){	
		var value = $(this).attr("attr_compision");
		var departmentIds = $("#DepartmentsId").val();
		if(departmentIds !=3){
			if(value == 'Comparision')
			{
			  $(this).closest("[role='tabpanel']").find(".selectboxsShowHide").hide();
			
			}else{
				 $(this).closest("[role='tabpanel']").find(".selectboxsShowHide").show();
				 //  $(".stateProSubProCls").trigger('click');
			}
		}
		
		
		
	});
	$(document).on("click",".rwsModalClickView",function(){	
		$("#rwsModalDivId").modal("show");
		
		var rwsLocationId = $(this).attr("attr_rws_locationid");
		var rwsLevelId = $(this).attr("attr_rws_levelid");
		var districtVal=$(this).attr("attr_district_val")
		var loctype=$(this).attr("attr_type")
		var locationName=$(this).attr("attr_location_name")
		
		var locationType='';
		var filterType='';
		var filterValue ='';
		var districtValue='';
		
		if(rwsLevelId == 2){
			locationType = "state";
			filterType="";
			filterValue="";
			districtValue="";
		}else if(rwsLevelId == 3){
			locationType = "district";
			filterType="district";
			filterValue=rwsLocationId;
			districtValue="";
		}else if(rwsLevelId == 4){
			if(loctype == "district"){
				filterType="district";
				locationType = "district";
			}else{
				filterType="constituency";
				locationType = "constituency";
			}
			
			filterValue=rwsLocationId;
			districtValue="";
		}else if(rwsLevelId == 5){
			if(loctype == "district"){
				filterType="district";
				locationType = "district";
			}else if (loctype == "constituency"){
				filterType="constituency";
				locationType = "constituency";
			}else{
				filterType="mandal";
				locationType = "mandal";
			}
			filterValue=rwsLocationId;
			districtValue=districtVal;
		}
		$("#modalTitleRwsId").html("<h4 class='text-capital'>"+locationName+" "+filterType+" Details</h4>");
		getHabitationCoverageByStatusByLocationType(locationType,filterType,filterValue,districtValue,"habilitationsBlockId","habilitations");
		getKeyPerformanceIndicatorsInfo(locationType,filterType,filterValue,districtValue,"kpiBlockId","kpi")
		getAssetInfoBetweenDates(locationType,filterType,filterValue,districtValue,"assestsBlockId","assests")
		getSchemeWiseWorkDetails(locationType,filterType,filterValue,districtValue,"waterSchemsBlockId","schemes")
	});	
	//Habilitations
	function getHabitationCoverageByStatusByLocationType(locationType,filterType,filterValue,districtValue,divId,type)
		{
			$("#"+divId).html(spinner);
			var date1 = glStartDate.split('/');
			var date2 = glEndDate.split('/');
			var rwsFromDateStr = date1[0]+'-'+date1[1]+'-'+date1[2]
			var rwsToDateStr = date2[0]+'-'+date2[1]+'-'+date2[2]
			var json = {
				fromDateStr:rwsFromDateStr,
				toDateStr:rwsToDateStr,
				locationType:locationType,
				year:"2017",
				filterType:filterType,
				filterValue:filterValue,
				districtValue:districtValue,
				type:""
			}
			$.ajax({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildStatusByLocationType(ajaxresp,locationType,divId,type);
					}else{
						$("#"+divId).html("No Data Available");
					}
					
				}
			});
			
		}
	//KPI
	function getKeyPerformanceIndicatorsInfo(locationType,filterType,filterValue,districtValue,divId,type){//ara1
			$("#"+divId).html(spinner);
			var date1 = glStartDate.split('/');
			var date2 = glEndDate.split('/');
			var rwsFromDateStr = date1[0]+'-'+date1[1]+'-'+date1[2]
			var rwsToDateStr = date2[0]+'-'+date2[1]+'-'+date2[2]
			var json={
						fromDateStr:rwsFromDateStr,
						toDateStr:rwsToDateStr,
						locationType:locationType,
						filterType:filterType,
						filterValue:filterValue,
						districtValue:districtValue,
						year:"2017"
						
					}
			$.ajax({
				url: 'getKeyPerformanceIndicatorsInfo',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildStatusByLocationType(ajaxresp,locationType,divId,type);
					}else{
						$("#"+divId).html("No Data Available");
					}
				}
			});
			
		}
	//Assets
	function getAssetInfoBetweenDates(locationType,filterType,filterValue,districtValue,divId,type){//ara1
			$("#"+divId).html(spinner);
			var date1 = glStartDate.split('/');
			var date2 = glEndDate.split('/');
			var rwsFromDateStr = date1[0]+'-'+date1[1]+'-'+date1[2]
			var rwsToDateStr = date2[0]+'-'+date2[1]+'-'+date2[2]
			var json = {
				fromDateStr:rwsFromDateStr,
				toDateStr:rwsToDateStr,
				year:"2017",
				filterType:filterType,
				filterValue:filterValue,
				districtValue:districtValue,
				locationType:locationType
			}
			$.ajax({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildStatusByLocationType(ajaxresp,locationType,divId,type);
					}else{
						$("#"+divId).html("No Data Available");
					}
				}
			});
		}
	//Work Schems
		function getSchemeWiseWorkDetails(locationType,filterType,filterValue,districtValue,divId,type){
			$("#"+divId).html(spinner);
			var date1 = glStartDate.split('/');
			var date2 = glEndDate.split('/');
			var rwsFromDateStr = date1[0]+'-'+date1[1]+'-'+date1[2]
			var rwsToDateStr = date2[0]+'-'+date2[1]+'-'+date2[2]
			var json = {
				  fromDateStr:rwsFromDateStr,
				  toDateStr:rwsToDateStr,
				  year:"2017",
				  locationType:locationType,
				  type:"",
				  filterType:filterType,
				  filterValue:filterValue,
				  districtValue:districtValue
			  }
			$.ajax({
				url: 'getSchemeWiseWorkDetails',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(ajaxresp){
					if(ajaxresp !=null && ajaxresp.length>0){
						buildStatusByLocationType(ajaxresp,locationType,divId,type);
					}else{
						$("#"+divId).html("No Data Available");
					}
				}
			});
			
		}
	function buildStatusByLocationType(GLtbodyArr,locationType,divId,type){
			var tableView='';
			if(GLtbodyArr !=null && GLtbodyArr.length>0){
				tableView+='<div class="table-responsive">';
				if(type == "habilitations"){
					tableView+='<h5 class="text-capital"><b>Coverage Status Of Habitation</b></h5>';
				}else if(type == "kpi"){
					tableView+='<h5 class="text-capital"><b>Key Performance</b></h5>';
				}else if(type == "assests"){
					tableView+='<h5 class="text-capital"><b>Assests</b></h5>';
				}else if(type == "schemes"){
					tableView+='<h5 class="text-capital"><b>Work Schemes</b></h5>';
				}
				tableView+='<table class="table table-bordered m_top10">';
					tableView+='<thead class="text-capital">';
					if(type == "habilitations"){
						tableView+='<tr>';
								tableView+='<th>'+locationType+'</th>';
								if(GLtbodyArr[0].statusList !=null && GLtbodyArr[0].statusList.length>0){
									for(var j in GLtbodyArr[0].statusList){
											if(GLtbodyArr[0].statusList[j].status != 'NC'){
												tableView+='<th>'+GLtbodyArr[0].statusList[j].status+'</th>';
												tableView+='<th>%</th>';
												
											}
									}
								}
								tableView+='<th>TOTAL</th>';
								tableView+='</tr>'; 
					}else if(type == "kpi"){
						tableView+='<tr>';
								tableView+='<th rowspan="2">'+locationType+'</th>';
								tableView+='<th colspan="3" style="text-align: center;">Partially Covered<br/>Habitations Through Schemes</th>';
								tableView+='<th colspan="3" style="text-align: center;">Quality Affected<br/>Habitations Through Schemes</th>';
								tableView+='</tr>'; 
								tableView+='<tr>'; 
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='<th>Target</th>';
								tableView+='<th>Achived</th>';
								tableView+='<th>% Percentage</th>';
								tableView+='</tr>'; 
					}else if(type=="assests"){
						tableView+='<tr>';
						tableView+='<th>'+locationType+'</th>';
						if(GLtbodyArr[0] !=null && GLtbodyArr[0].basicList !=null && GLtbodyArr[0].basicList.length>0){
							for(var j in GLtbodyArr[0].basicList){
								tableView+='<th>'+GLtbodyArr[0].basicList[j].assetType+'</th>';
							}
						}
						tableView+='<th>TOTAL</th>';
						tableView+='</tr>';
					}else if(type=="schemes"){
							tableView+='<tr>';
							tableView+='<th rowspan="2">'+locationType+'</th>';
							if(GLtbodyArr[0] !=null && GLtbodyArr[0].basicList !=null && GLtbodyArr[0].basicList.length>0){
								for(var j in GLtbodyArr[0].basicList){
										if(GLtbodyArr[0].basicList[j].assetType == 'PWS' || GLtbodyArr[0].basicList[j].assetType == "CPWS"){
											tableView+='<th colspan="8">'+GLtbodyArr[0].basicList[j].assetType+'</th>';
										}
									
								}
							}
							tableView+='</tr>';
							tableView+='<tr>';
								tableView+='<th>OnGoing</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Completed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Commissioned</th>';
								tableView+='<th>%</th>';
								tableView+='<th>OnGoing</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Not Grounded</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Completed</th>';
								tableView+='<th>%</th>';
								tableView+='<th>Commissioned</th>';
								tableView+='<th>%</th>';
							tableView+='</tr>'
								
						}
						
					tableView+='</thead>';
					tableView+='<tbody>';
					if(type == "habilitations"){
						for(var i in GLtbodyArr){
							var totalCount=0;
							tableView+='<tr>';
								tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
							
							if(GLtbodyArr[i].statusList !=null && GLtbodyArr[i].statusList.length>0){
								for(var j in GLtbodyArr[i].statusList){
								if(GLtbodyArr[i].statusList[j].status != 'NC'){
										if(GLtbodyArr[i].statusList[j].count !=null && GLtbodyArr[i].statusList[j].count>0){
											tableView+='<td >'+GLtbodyArr[i].statusList[j].count+'</td>';
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].statusList[j].percentage+'</small></td>';
										}else{
											tableView+='<td> - </td>';
											tableView+='<td> - </td>';
										}
										totalCount += GLtbodyArr[i].statusList[j].count;
									}
								}
							}
							tableView+='<td>'+totalCount+'</td>';
							tableView+='</tr>';
						}
					}else if(type == "kpi"){
						for(var i in GLtbodyArr){
									tableView+='<tr>';
									if(locationType == "state"){
										tableView+='<td>Andhra Pradesh</td>';
									}else{
										tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
									}
									if(GLtbodyArr[i].pcTarget !=null && GLtbodyArr[i].pcTarget >0){
										tableView+='<td >'+GLtbodyArr[i].pcTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcAchivement !=null && GLtbodyArr[i].pcAchivement >0){
										tableView+='<td >'+GLtbodyArr[i].pcAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].pcPercentage !=null && GLtbodyArr[i].pcPercentage >0){
										if(GLtbodyArr[i].pcPercentage < 100){
											tableView+='<td style="background-color:#FFE296">'+GLtbodyArr[i].pcPercentage.toFixed(2)+'</td>';
										}else{
											tableView+='<td style="background-color:#C7F0C5;">'+GLtbodyArr[i].pcPercentage.toFixed(2)+'</td>';
										}
										
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaTarget !=null && GLtbodyArr[i].qaTarget >0){
										tableView+='<td >'+GLtbodyArr[i].qaTarget+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaAchivement !=null && GLtbodyArr[i].qaAchivement >0){
										tableView+='<td >'+GLtbodyArr[i].qaAchivement+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									if(GLtbodyArr[i].qaPercentage !=null && GLtbodyArr[i].qaPercentage >0){
										if(GLtbodyArr[i].qaPercentage < 100){
											tableView+='<td style="background-color:#FFE296">'+GLtbodyArr[i].qaPercentage.toFixed(2)+'</td>';
										}else{
											tableView+='<td style="background-color:#C7F0C5;">'+GLtbodyArr[i].qaPercentage.toFixed(2)+'</td>';
										}
									}else{
										tableView+='<td> - </td>';
									}
									tableView+='</tr>';
								}
					}else if(type=="assests"){
						for(var i in GLtbodyArr){
							var totalCount=0;
							tableView+='<tr>';
								tableView+='<td>'+GLtbodyArr[i].name+'</td>';
							
							if(GLtbodyArr[i].basicList !=null && GLtbodyArr[i].basicList.length>0){
								for(var j in GLtbodyArr[i].basicList){
									if(GLtbodyArr[i].basicList[j].count !=null && GLtbodyArr[i].basicList[j].count>0){
										tableView+='<td >'+GLtbodyArr[i].basicList[j].count+'</td>';
									}else{
										tableView+='<td> - </td>';
									}
									
									totalCount =totalCount+GLtbodyArr[i].basicList[j].count;
								}
							}
							if(totalCount >0){
								tableView+='<td>'+totalCount+'</td>';
							}else{
								tableView+='<td> - </td>';
							}
							
							tableView+='</tr>';
						}
					}else if(type=="schemes"){
						for(var i in GLtbodyArr){
							tableView+='<tr>';
								tableView+='<td>'+GLtbodyArr[i].locationName+'</td>';
							
							if(GLtbodyArr[i].basicList !=null && GLtbodyArr[i].basicList.length>0){
								for(var j in GLtbodyArr[i].basicList){
								if(GLtbodyArr[i].basicList[j].assetType == 'PWS' || GLtbodyArr[i].basicList[j].assetType == "CPWS"){
										
										if(GLtbodyArr[i].basicList[j].workOngoingCount !=null && GLtbodyArr[i].basicList[j].workOngoingCount>0){
											tableView+='<td >'+GLtbodyArr[i].basicList[j].workOngoingCount+'</td>';
										}else{
											tableView+='<td> - </td>';
										}
										if(GLtbodyArr[i].basicList[j].percentageOne !=null && GLtbodyArr[i].basicList[j].percentageOne>0){
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageOne.toFixed(1)+'</small></td>';
										}else{
											tableView+='<td> - </td>';
										}
										if(GLtbodyArr[i].basicList[j].workNotGroundedCount !=null && GLtbodyArr[i].basicList[j].workNotGroundedCount>0){
											tableView+='<td >'+GLtbodyArr[i].basicList[j].workNotGroundedCount+'</td>';
										}else{
											tableView+='<td> - </td>';
										}
										if(GLtbodyArr[i].basicList[j].percentageFour !=null && GLtbodyArr[i].basicList[j].percentageFour>0){
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageFour.toFixed(1)+'</small></td>';
										}else{
											tableView+='<td> - </td>';
										}	
										if(GLtbodyArr[i].basicList[j].workCompletedCount !=null && GLtbodyArr[i].basicList[j].workCompletedCount>0){
											tableView+='<td >'+GLtbodyArr[i].basicList[j].workCompletedCount+'</td>';
										}else{
											tableView+='<td> - </td>';
										}
										if(GLtbodyArr[i].basicList[j].percentageThree !=null && GLtbodyArr[i].basicList[j].percentageThree>0){
											tableView+='<td><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageThree.toFixed(1)+'</small></td>';
										}else{
											tableView+='<td> - </td>';
										}	
										if(GLtbodyArr[i].basicList[j].workComissionedCount !=null && GLtbodyArr[i].basicList[j].workComissionedCount>0){
											tableView+='<td >'+GLtbodyArr[i].basicList[j].workComissionedCount+'</td>';
										}else{
											tableView+='<td> - </td>';
										}
										if(GLtbodyArr[i].basicList[j].percentageTwo !=null && GLtbodyArr[i].basicList[j].percentageTwo>0){
											tableView+='<td ><small style="color:#0FBE08">'+GLtbodyArr[i].basicList[j].percentageTwo.toFixed(1)+'</small></td>';
										}else{
											tableView+='<td> - </td>';
										}	
									}
								}
							}
							tableView+='</tr>';
						}
					}
						
					tableView+='</tbody>';
				tableView+='</table>';
				tableView+='</div>';
			}
			$("#"+divId).html(tableView);
	}
$(document).on("click",".panelCollapseIconClick",function(e){
	e.stopPropagation();
	var levelId = $(this).attr("attr_levelIdValue");
	var locationScopeId = $(this).attr("attr_id");
	var locationLevelName = $(this).attr("attr_name");
	 if(levelId == 3){
			
		setTimeout(function(){ 
			$(".menuDataCollapse").attr("attr_distid",locationScopeId);
			$(".menuDataCollapse").attr("attr_dist_name",locationLevelName);
			
			$(".menuAttrAddNames").attr("attr_distid",locationScopeId);
			$(".menuAttrAddNames").attr("attr_dist_name",locationLevelName);
		},3000);
		
		
	} 
	if(levelId == 4){
		var dist = $(this).attr("attr_distid")
		var dist1 = $(this).attr("attr_dist_name")
		setTimeout(function(){ 
			$(".menuDataCollapse").attr("attr_distid",dist);
			$(".menuDataCollapse").attr("attr_dist_name",dist1);
			
			$(".menuDataCollapse").attr("attr_constid",locationScopeId);
			$(".menuDataCollapse").attr("attr_cons_name",locationLevelName);
		},3000);
	}

});	

function getGovtGrantTypeDetails(programId,subProgramId,divId){
	$("#"+divId).html('');
		var json = {
			programId:programId,		//program	
			govtSchemesId:subProgramId    // sub program
			
		}
		$.ajax({                
			  type:'POST',    
			  url: 'getGovtGrantTypeDetails',
			  dataType: 'json',
		  data : JSON.stringify(json),
		  beforeSend :   function(xhr){
				  xhr.setRequestHeader("Accept", "application/json");
				  xhr.setRequestHeader("Content-Type", "application/json");
		}  
		}).done(function(result){  
			var str='';	
		  if(result !=null && result.length>0){
				 str+='<option value="0" selected="selected">ALL</option>';
				for(var i in result){
					str+='<option value="'+result[i].id+'">'+result[i].name+' </option>';
				}
			}
			$("#"+divId).html(str);
			$("#"+divId).trigger('chosen:updated');
		});
	
}

 $(document).on("change","#grantTypeState",function(){
	var grantType =  $(this).val();
	var programId = $("#programNamesState").val();
	var subProgramId = $("#subProgramNamesState").val();
	
	var sortingType = getSelectedType().sortingType;
	var orderType = getSelectedType().orderType;	
	var cummulativeType ='';
	$(".tabCummulativeStateCls li").each(function(i, obj){
		 if($(this).hasClass("active")){
			  cummulativeType = $(this).attr("attr_type");
		 }
		
	});
	
	if(cummulativeType == "cummulativeView"){
		
		getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw',sortingType,orderType,globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"cumulative",'change',grantType);
	}else{
		
		getSchemeWiseLocationWiseAmountDetails(2,'stateLevlOvervw',sortingType,orderType,globalLocationId,globalLocationLevelTypeId,programId,subProgramId,"normalView",'change',grantType);
	}
	
});

 function getDateInRequiredFormat(date){
	 var dateStr="";
	 var dateArr = date.split("/");
	  dateStr = dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];
	  return dateStr;
 }
 function getMgnregsFMSWorksDetails(locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName){ 

    $("#fundModal").modal('show');
	$("#diptNameId").html('<h4 class="text-capital"><b>'+locationName+'&nbsp;&nbsp; '+levelName+' &nbsp;&nbsp; - Category Wise Fund Overview</b></h4>');
	$("#fundSanctionModal").html(spinner);
	var financialYrIdList =[];
	var financialyr = financialYrId.split(',');
	for(var i = 0; i < financialyr.length; i++)
       {  
			if(financialyr[i] !=0)
			 financialYrIdList.push(financialyr[i]);
       } 
	   
	/* var financialYrIdList = [];
	var financialyr = $('#financialYearId').val();
	if(financialyr == 0){
		financialYrIdList=[];
	}else{
		financialYrIdList = financialyr;
	}
	 */
	var ReportType="";
	if(viewType == "cumulative"){
		ReportType = "cumulative";
	}else{
		ReportType = "yearwise";
	}
	/* var parliamentTypeval="";
	var blockVill = getblockTypeVillage();
	var blockMan = getblockTypeMandal();
	var blockCons = getblockTypeCons(); */
	
	
	/* if(blockLvlId == 3){
		if(blockDis == "districtType"){
			parliamentTypeval ="district"
		}else{
			parliamentTypeval ="parliament"
		}
	}else if(blockLvlId == 4){
		if(blockCons == "districtType"){
			parliamentTypeval ="district"
		}else{
			parliamentTypeval ="parliament"
		}
	}else if(blockLvlId == 5){
		if(blockMan == "districtType"){
			parliamentTypeval ="district"
		}else{
			parliamentTypeval ="parliament"
		}
	}else if(blockLvlId == 6){
		if(blockVill == "districtType"){
			parliamentTypeval ="district"
		}else{
			parliamentTypeval ="parliament"
		}
	} */
	
	if (levelName != null && levelName=="village") {
		levelName = "panchayat";
	}
	
    var json = {
          locationType : levelName, 
          locationIdStr : locationStrIdsForMgnregs,
          financialYrIdList : financialYrIdList,
          reportType : ReportType 
        }
    $.ajax({ 
      url: 'getMgnregsFMSWorksDetails', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
		if(result !=null && result.length>0){
			buildMgnregsFMSWorksDetails(result,locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName);
		}else{
			$("#fundSanctionModal").html("NO DATA AVAILABLE");
		}
    });
  }
 function buildMgnregsFMSWorksDetails(result,locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName){
	
  var str = '';
	var blockDis = getblockType();
	var blockVill = getblockTypeVillage();
	var blockMan = getblockTypeMandal();
	var blockCons = getblockTypeCons();
	if($windowWidth < 768)
	{
		str+='<div class="table-responsive">';
	}
	str+='<table class="table table-condensed table-bordered table-striped" id="tableMgrnesId">';
		str+='<thead>';
		/*if(blockLvlId == 3){
			str+='<th class="text-capital">District</th>';
		}else if(blockLvlId == 4){
			str+='<th class="text-capital">Constituency</th>';
		}else if(blockLvlId == 5){
			str+='<th class="text-capital">Mandal</th>';
		}else if(blockLvlId == 6){
			str+='<th class="text-capital">Panchayat</th>';
		}*/
			
			str+='<th class="text-capital">Category</th>';
			str+='<th class="text-capital">Works</th>'; 
			//str+='<th class="text-capital">Wage</th>';
			str+='<th class="text-capital">Material</th>';
			//str+='<th class="text-capital">Total</th>';
		str+='</thead>';
		str+='<tbody>';
	for(var i in result){
		str+='<tr>'; 
		/*if(blockLvlId == 3){
			if(result[i].district != null){
				 str+='<td>'+result[i].district+'</td>';
			}else{
				 str+='<td>-</td>';
			}
		}else if(blockLvlId == 4){
			if(result[i].constituency != null){
				 str+='<td>'+result[i].constituency+'</td>';
			}else{
				 str+='<td>-</td>';
			}
		}else if(blockLvlId == 5){
			if(result[i].mandal != null){
				 str+='<td>'+result[i].mandal+'</td>';
			}else{
				 str+='<td>-</td>';
			}
		}else if(blockLvlId == 6){
			if(result[i].panchayat != null){
				 str+='<td>'+result[i].panchayat+'</td>';
			}else{
				 str+='<td>-</td>';
			}
		}*/
		if(result[i].category != null){
			if(blockLvlId == 3 || blockLvlId == 4 || blockLvlId == 5 || blockLvlId == 6 && blockDis == "districtType"){
				str+='<td><span class="categoryClickCls" style="cursor:pointer;color:green;" attr_name="'+result[i].category+'" attr_location_strids="'+	locationStrIdsForMgnregs+'" attr_level_name="'+levelName+'" attr_financial_yr_id="'+financialYrId+'" attr_view_type="'+viewType+'" attr_levelid="'+blockLvlId+'" attr_location_name="'+locationName+'">'+result[i].category+'</span></td>';
			}else{
				str+='<td>'+result[i].category+'</td>';
			}
			
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].works != null && result[i].works>0){
			 str+='<td>'+result[i].works+'</td>';
		}else{
			 str+='<td>-</td>';
		}    
		/* if(result[i].wage != null && result[i].wage>0){
			 str+='<td>'+result[i].wage+'</td>';
		}else{
			 str+='<td>-</td>';
		}   */
		
		if(result[i].material != null && result[i].material>0){
			 str+='<td>'+result[i].material+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		/* if(result[i].total != null && result[i].total>0){
			 str+='<td>'+result[i].total+'</td>';
		}else{
			 str+='<td>-</td>';
		} */
		str+='</tr>';     
	}
	str+='</tbody>';  
	str+='</table>';  
	if($windowWidth < 768)
	{
		str+='</div>';
	}
	$("#fundSanctionModal").html(str);   
	$("#tableMgrnesId").dataTable({
		"iDisplayLength": 15,
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Dashboard',
				filename:  'dashboard '+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Dashboard',
				filename:  'dashboard'+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3'
			}
		]
	});  
}
$(document).on("click",".categoryClickCls",function(){
	var categoryName = $(this).attr("attr_name");
	var locationStrIdsForMgnregs = $(this).attr("attr_location_strids");
	var levelName = $(this).attr("attr_level_name");
	var financialYrId = $(this).attr("attr_financial_yr_id");
	var viewType = $(this).attr("attr_view_type");
	var blockLvlId = $(this).attr("attr_levelid");
	var locationName = $(this).attr("attr_location_name");
	
	getMgnregsFMSWorksDetailsByCategory(locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName,categoryName)
	
});	

function getMgnregsFMSWorksDetailsByCategory(locationStrIdsForMgnregs,levelName,financialYrId,viewType,blockLvlId,locationName,categoryName){ 
	
	$("#fundCategoryModal").modal('show');
	$("#diptCatNameId").html('<h5 class="text-capital"><b>'+categoryName+'&nbsp;&nbsp; - Category Wise Details</b></h5>');
	$("#fundSanctionCategoryModal").html(spinner);
	var financialYrIdList =[];
	var financialyr = financialYrId.split(',');
	for(var i = 0; i < financialyr.length; i++)
       {  
			if(financialyr[i] !=0)
			 financialYrIdList.push(financialyr[i]);
       } 
	   
	/* var financialYrIdList = [];
	var financialyr = $('#financialYearId').val();
	if(financialyr == 0){
		financialYrIdList=[];
	}else{
		financialYrIdList = financialyr;
	}
	 */
	var ReportType="";
	if(viewType == "cumulative"){
		ReportType = "cumulative";
	}else{
		ReportType = "yearwise";
	}
	
	var json = {
          locationType : levelName, 
          locationIdStr : locationStrIdsForMgnregs,
          financialYrIdList : financialYrIdList,
		  category:categoryName,
          reportType : ReportType 
        }
    $.ajax({ 
      url: 'getMgnregsFMSWorksDetailsByCategory', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
		if(result !=null && result.length>0){
			buildMgnregsFMSWorksDetailsByCategory(result,blockLvlId);
		}else{
			$("#fundSanctionCategoryModal").html("NO DATA AVAILABLE");
		}
    });
  }
  
  function buildMgnregsFMSWorksDetailsByCategory(result,blockLvlId){
	  var str = '';
	//if($windowWidth < 500)
	//{
		str+='<div class="table-responsive">';
	//}
	str+='<table class="table table-condensed table-bordered table-striped tableStyledCategory" id="tableCategoryMgrnesId">';
		str+='<thead>';
			str+='<tr>'; 
			str+='<th class="text-capital">PROGRAM</th>';
			str+='<th class="text-capital">HABITATION_NAME</th>'; 
			str+='<th class="text-capital">WORK_NAME</th>';
			str+='<th class="text-capital">DESCRIPTION</th>';
			str+='<th class="text-capital">AMOUNT_UNSKILLED</th>';
			str+='<th class="text-capital">AMOUNT_MATERIAL</th>';
			str+='<th class="text-capital">TOTAL_COST</th>';
			str+='<th class="text-capital">TOTAL_MANDAYS</th>';
			//str+='<th class="text-capital">WAGE</th>';
			str+='<th class="text-capital">MATERIAL</th>';
			//str+='<th class="text-capital">TOTAL</th>';
			str+='<th class="text-capital">DAYS</th>';
			str+='</tr>'; 
		str+='</thead>';
		str+='<tbody>';
	for(var i in result){
		str+='<tr>'; 
		if(result[i].program != null && typeof(result[i].program) != "undefined"){
			str+='<td>'+result[i].program+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].habitationName != null && typeof(result[i].habitationName) != "undefined"){
			str+='<td>'+result[i].habitationName+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].workName != null && typeof(result[i].workName) != "undefined"){
			if(result[i].workName !=null && result[i].workName.length>14){
				str+='<td><span style="cursor:pointer;" data-toggle="tooltip" data-placement="top" class="tooltipCategoryCls" title="'+result[i].workName+'" >'+result[i].workName.substring(0,14)+'...</span></td>';
			}else{
				str+='<td>'+result[i].workName+'</td>';
			}
			
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].description != null && typeof(result[i].description) != "undefined"){
			str+='<td>'+result[i].description+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		if(result[i].amountUnSkilled != null && result[i].amountUnSkilled>0 && typeof(result[i].amountUnSkilled) != "undefined"){
			 str+='<td>'+result[i].amountUnSkilled+'</td>';
		}else{
			 str+='<td>-</td>';
		}    
		if(result[i].amountMaterial != null && result[i].amountMaterial>0 && typeof(result[i].amountMaterial) != "undefined"){
			 str+='<td>'+result[i].amountMaterial+'</td>';
		}else{
			 str+='<td>-</td>';
		}  
		if(result[i].totalCost != null && result[i].totalCost>0 && typeof(result[i].totalCost) != "undefined"){
			 str+='<td>'+result[i].totalCost+'</td>';
		}else{
			 str+='<td>-</td>';
		} 
		if(result[i].totalManDays != null && result[i].totalManDays>0 && typeof(result[i].totalManDays) != "undefined"){
			 str+='<td>'+result[i].totalManDays+'</td>';
		}else{
			 str+='<td>-</td>';
		} 		
		/* if(result[i].wage != null && result[i].wage>0 && typeof(result[i].wage) != "undefined"){
			 str+='<td>'+result[i].wage+'</td>';
		}else{
			 str+='<td>-</td>';
		} */
		if(result[i].material != null && result[i].material>0 && typeof(result[i].material) != "undefined"){
			 str+='<td>'+result[i].material+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		/* if(result[i].total != null && result[i].total>0 && typeof(result[i].total) != "undefined"){
			 str+='<td>'+result[i].total+'</td>';
		}else{
			 str+='<td>-</td>';
		} */
		if(result[i].days != null && result[i].days>0 && typeof(result[i].days) != "undefined"){
			 str+='<td>'+result[i].days+'</td>';
		}else{
			 str+='<td>-</td>';
		}
		str+='</tr>';     
	}
	str+='</tbody>';  
	str+='</table>';  
	//if($windowWidth < 768)
	//{
		str+='</div>';
	//}
	$("#fundSanctionCategoryModal").html(str); 
	$(".tooltipCategoryCls").tooltip();	
	$("#tableCategoryMgrnesId").dataTable({
		"iDisplayLength": 15,
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   'Dashboard',
				filename:  'dashboard'+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   'Dashboard',
				filename:  'dashboard'+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3'
			}
		]
	}); 
  }