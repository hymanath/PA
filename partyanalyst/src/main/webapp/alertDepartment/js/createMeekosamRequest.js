	//getAllDistrictByStateId(1);
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
			for(var i in result){
				alert(result[i].name);
			}
		});
	}
	//getAllMandalsByDistrictID(11);
	function getAllMandalsByDistrictID(districtId){
		var jsObj={
			districtId :districtId
		}
		$.ajax({
			type:"POST",
			url :"getAllMandalsByDistrictIDAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		});
	}
	//getAllPanchayatByMandalId(83);//844
	function getAllPanchayatByMandalId(mandalId){
		var jsObj={
			mandalId :mandalId,
			type:"muncipality",/*"Mandal" or "muncipality"*/
			task:""
		}
		$.ajax({
			type:"POST",
			url :"getPanchayatDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			for(var i in result){
				alert(result[i].name);
			}
		});
	}
	//getAllHamletByPanchayatID(1);
	function getAllHamletByPanchayatID(panchayatId){
		var jsObj={
			panchayatId :panchayatId
		}
		$.ajax({
			type:"POST",
			url :"getAllHamletByPanchayatIDAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		});
	}
	//getMeekosamOccupationList();
	function getMeekosamOccupationList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamOccupationListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			for(var i in result){
				alert(result[i].name);
			}
		});
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
	//getMeekosamArgeeCategoryList();
	function getMeekosamArgeeCategoryList(){
		var jsObj={}
		$.ajax({
			type:"POST",
			url :"getMeekosamArgeeCategoryListAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			for(var i in result){
				alert(result[i].name);
			}
		});
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
	