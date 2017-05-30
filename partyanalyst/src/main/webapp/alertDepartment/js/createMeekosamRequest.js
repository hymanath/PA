	getAllDistrictByStateId(1);
	function getAllDistrictByStateId(stateId){
		var jobj = {
			stateId : stateId  
		}
		$.ajax({
			type : 'GET',
			url : 'getDistrictsForStateAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)} 
		}).done(function(result){
			if(result != null && result.length > 0){
				buildDistrictList(result);
			}
		});
	}
	function buildDistrictList(result){
		$("#districtListId").html('');
		for(var i in result){			
			$('#districtListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	//getAllMandalsByDistrictID(11);
	function getAllMandalsByDistrictID(){
		var districtId = $('#districtListId').val();
		var jsObj={
			districtId :districtId
		}
		$.ajax({
			type:"POST",
			url :"getAllMandalsByDistrictIDAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildMandalList(result);
			}
		});
	}
	function buildMandalList(result){
		$("#mandalListId").html('');
		var type=0;
		$('#mandalListId').append('<option value="0">Select One Mandal or Town</option>');
		for(var i in result){
			if(result[i].name.indexOf("Mandal") == -1) 
				type = 1 ;
			else
				type = 0 ; 
			$('#mandalListId').append('<option value='+type+","+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	//getAllPanchayatByMandalId(83);//844
	var LocationType = "";
	function getAllPanchayatByMandalId(){
		var mandalTypeAndId = $('#mandalListId').val().split(",");
		var mandalId = mandalTypeAndId[1];
		if(mandalTypeAndId[0]==0){
			LocationType = "Mandal";
		}else{
			LocationType = "muncipality";
		}
		var jsObj={
			mandalId :mandalId,
			type:LocationType,
			task:""
		}
		$.ajax({
			type:"POST",
			url :"getPanchayatDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildPanchayatList(result);
			}
		});
	}
	function buildPanchayatList(result){
		$("#panchayatListId").html('');
		$('#panchayatListId').append('<option value="0">Select One Panchayat or Ward</option>');
		for(var i in result){			
			$('#panchayatListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	//getAllHamletByPanchayatID(1);
	function getAllHamletByPanchayatID(){
		if(LocationType=="muncipality"){
			$("#habitationId").html('');
			return;
		}
		var panchayatId = $('#panchayatListId').val();
		var jsObj={
			panchayatId :panchayatId
		}
		$.ajax({
			type:"POST",
			url :"getAllHamletByPanchayatIDAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildHamletList(result);
			}
		});
	}
	function buildHamletList(result){
		$("#habitationId").html('');
		$('#habitationId').append('<option value="0">Select One Hamlet</option>');
		for(var i in result){			
			$('#habitationId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	getMeekosamOccupationList();
	function getMeekosamOccupationList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamOccupationListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildMeekosamOccupationList(result);
			}
		});
	}
	function buildMeekosamOccupationList(result){
		$("#occupationListId").html('');
		$('#occupationListId').append('<option value="0">Select One Occupation</option>');
		for(var i in result){			
			$('#occupationListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	//getMeekosamCasteCategoryList();
	function getMeekosamCasteCategoryList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamCasteCategoryListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			for(var i in result){
				alert(result[i].name);
			}
		});
	}
	getMeekosamArgeeCategoryList();
	function getMeekosamArgeeCategoryList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamArgeeCategoryListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildMeekosamArgeeCategoryList(result);
			}
		});
	}
	function buildMeekosamArgeeCategoryList(result){
		$("#ArgeeCategoryListId").html('');
		$('#ArgeeCategoryListId').append('<option value="0">Select One Argee Category</option>');
		for(var i in result){			
			$('#ArgeeCategoryListId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
	}
	//getMeekosamAnnualIncomeList();
	function getMeekosamAnnualIncomeList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamAnnualIncomeListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			for(var i in result){
				alert(result[i].name);
			}
		});
	}
	function saveMeekosamPetitionerDetails(){
		var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
			}
		};   
		YAHOO.util.Connect.setForm('meekosamPetitionerDetails',true);
		YAHOO.util.Connect.asyncRequest('POST','saveMeekosamPetitionerDetailsAction.action',uploadHandler);
	
	}
	searchPetitionerDetailsByVoterNoAadharNoMobileNo();
	function searchPetitionerDetailsByVoterNoAadharNoMobileNo(){
		var jsObj={
			cardNo :"7207785117",
			type:"mobile"
		}
		$.ajax({
			type:"POST",
			url :"searchPetitionerDetailsByVoterNoAadharNoMobileNoAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildHamletList(result);
			}
		});
	}
	