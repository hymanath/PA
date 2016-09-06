	function getNewsBasicCounts(){
		var temp="";
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=i==0?globalUserAccessLevelValues[i]:temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate='08-01-2016',endDate='08-31-2016';
		var state = globalState;
		console.log(temp);
		var url = window.location.href;
		var wurl = url.substr(0,(url.indexOf(".com")+4));
		$.ajax({
			//url: wurl+"/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
			url: "http://localhost:8080/CommunityNewsPortal/webservice/getNewsBasicCounts/"+globalUserAccessLevelId+"/"+temp+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
	}
	
	function getUserTypeWiseNewsCounts(){
		var startDate,endDate;
		var jsObj={
				activityMemberId : globalActivityMemberId ,
				userTypeId : globalUserTypeId,
				state:globalState,
				fromDate:fromDate,
				toDate:toDate,
				benefitId:benefitId
			}
		
			$.ajax({
				type : 'POST',
				url : 'getUserTypeWiseNewsCountsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result != null && result.length > 0){
					
				}
			});
	}
	
	$(document).on("click","#detailedPartyId",function(){
		getDetailedPartyMainEditionsOverview();
		getDetailedPartyDistrictEditionsOverview();
		getDetailedPartyNewsTypeAnalysis();
		getDetailedPartyPartyVsPublications();
	});
	
	function getDetailedPartyMainEditionsOverview(){
		var arr = globalUserAccessLevelValues;
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate,endDate;
		var state = globalState;
		var jsObj ={
				userAccessLevelId : globalUserAccessLevelId,
				userAccessLevelValuesArray : temp,
				state : state,
				startDate : startDate,
				endDate : endDate
		}
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyMainEditionsOverview/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
		
	}
	
	function getDetailedPartyDistrictEditionsOverview(){
		var arr = globalUserAccessLevelValues;
		var temp;
		if(globalUserAccessLevelValues != null && globalUserAccessLevelValues.length > 0){
			for(var i in globalUserAccessLevelValues){
				temp=temp+","+globalUserAccessLevelValues[i];
			}
		}
		var startDate,endDate;
		var state = globalState;
		var jsObj ={
				userAccessLevelId : globalUserAccessLevelId,
				userAccessLevelValuesArray : temp,
				state : state,
				startDate : startDate,
				endDate : endDate
		}
		
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDetailedPartyDistrictEditionsOverview/"+userAccessLevelId+"/"+userAccessLevelValuesArray+"/"+state+"/"+startDate+"/"+endDate+""
		}).then(function(result){
			if(result != null && result.length > 0){
				
			}
		});
	}
	
	function getDetailedPartyNewsTypeAnalysis(){
		
	}
	
	function getDetailedPartyPartyVsPublications(){
		
	}
