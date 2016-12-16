
 //CASTE WISE FUNCTIONS
 function getCasteCategoryWiseTdpCadreSummaryReport(){
	 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj = {
			           accessType : accessType,
                       locationArray : locationArray 
 		           }
		$.ajax({
			type : 'POST',
			url : 'casteCategoryWiseCadreSummaryReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("caste category summary report...");
		});
  }
  function getstateWiseTdpCadreCasteCounts(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			           accessType : accessType,
                       locationArray : locationArray,
                       limit : 1.0				   
					}
		$.ajax({
			type : 'POST',
			url : 'stateWiseCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("caste summary report...");
		});
	}
	function getdistrictWiseTdpCadreCasteCounts(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{ 
					 locationArray : districtArray,
					 limit : 1.0
					}
		$.ajax({
			type : 'POST',
			url : 'districtWiseCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("caste - district wise  report...");
		});
	}
	function getConstituencyWiseTdpCadreCasteCountsAction(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{ 
					 locationArray : constituencyArray,
					 limit : 1.0
					}
		$.ajax({
			type : 'POST',
			url : 'constituencyWiseCadreCasteCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("caste - constituency wise  report...");
		});
	}
	
	
  //AGE WISE FUNCTIONS
  function getAgeWiseTdpCadreSummaryReport(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			          accessType : accessType,
                      locationArray : locationArray 
					}
		
		$.ajax({
			type : 'POST',
			url : 'ageWiseCadreSummaryReportAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("age wise summary report..");
		});
	}
	function getDistrictWisegeWiseTdpCadreCounts(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj ={ locationArray : districtArray ,searchType : "district" }
		
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("age - dist");
		});
	}
	function getConstituencyWisegeWiseTdpCadreCounts(){
		
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  locationArray : constituencyArray ,searchType : "constituency"  }
		$.ajax({
			type : 'POST',
			url : 'getLocationWisegeWiseCadreCountsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("age - const");
		});
	}
	
	
	//GENDER WISE
	function getGenderSummaryCounts(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jobj =	{  
			          accessType : accessType,
                      locationArray : locationArray 
					}
		
		$.ajax({
			type : 'POST',
			url : 'getGenderSummaryCountsByUserAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("gender - summary");
		});
	}
	function getDistrictWiseCadreGenderCounts(){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		 var jobj ={ locationArray : districtArray ,searchType : "district"  }
		
		$.ajax({
			type : 'POST',
			url : 'locationWiseCadreGenderCountsByUserAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("gender - district");
		});
	}
	function getConstituencyWiseCadreGenderCounts(stateId,districtId,searchType){
		 //$("#").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner "><div class="dot1"></div><div class="dot2"></div></div></div>');
		 
		var jobj =	{  locationArray : constituencyArray ,searchType : "constituency" }
		
		$.ajax({
			type : 'POST',
			url : 'locationWiseCadreGenderCountsByUserAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			alert("gender - constituency..");
		});
	}
	