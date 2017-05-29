	//getAllMandalsByDistrictID(11)
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
	//getAllHamletByPanchayatID(1)
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
	//getMeekosamOccupationList()
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
	//getMeekosamCasteCategoryList()
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
	//getMeekosamArgeeCategoryList()
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
	//getMeekosamAnnualIncomeList()
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
	