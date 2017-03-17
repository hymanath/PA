$(window).load(function() {
 getCountsForConstituency();
});


function getCountsForConstituency(){
	var tehsilId=0;
	$("#reportLevelheading").html("");

	var level =  $("#reportLevel").val();
	var publicationDateId = 22;
	var typeName = '';

	type = 'constituency';
	id = 232;

	var jsObj=
		{
			tehsilId:tehsilId,
			type:type,	
			id:id,
			publicationDateId:publicationDateId,
			constituencyId:232,
			task:"getCountForLevel"
		}
		$.ajax({
		  type:'GET',
		  url: 'getCountForLevelAction.action',
		  data: {task :JSON.stringify(jsObj)}
		}).done(function(results){
			var str="";	

			if(results !=null && results.length>0){
				
				if(results[0].totalmandals == null)
					results[0].totalmandals = 0;
				if(results[0].noOfLocalBodies == null)
				results[0].noOfLocalBodies = 0;
				if(results[0].totalPanchayats == null)
					results[0].totalPanchayats = 0;
				if(results[0].totalBooths == null)
					results[0].totalBooths = 0;

				if(results[0].totalmandals !=null && results[0].totalmandals!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Mandals</h4>';
							str +='<h3>'+results[0].totalmandals+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				if(results[0].noOfLocalBodies !=null && results[0].noOfLocalBodies!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Muncipalities</h4>';
							str +='<h3>'+results[0].noOfLocalBodies+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				
				if(results[0].totalPanchayats !=null && results[0].totalPanchayats!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Panchayats</h4>';
							str +='<h3>'+results[0].totalPanchayats+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalNoOfWards !=null && results[0].totalNoOfWards!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Wards</h4>';
							str +='<h3>'+results[0].totalNoOfWards+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalBooths !=null && results[0].totalBooths!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Booths</h4>';
							str +='<h3>'+results[0].totalBooths+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				if(results[0].totalNoOfHamlets !=null && results[0].totalNoOfHamlets!=0 ){
					str +='<td>';
							str +='<h4 class="text-capitalize text-muted">Hamlets</h4>';
							str +='<h3>'+results[0].totalNoOfHamlets+'</h3>';
							str +='<i class="glyphicon glyphicon-option-horizontal pull-right text-muted f-16"></i>';
					str +='</td>';
				}
				
				$("#belowLevelsSizeId").html(str);
				
			}
		});		
		 			
}
function getDistrictDetails(){
    jsObj={
      locationId:1
    }
    $.ajax({
      type : "GET",
      url : "getDistrictsDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select district</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].districtId+'">'+result[i].districtName+'</option>';
        }  
    }  
       $("#districtId").html(str);    
  });
  }
  function getConstituencyDetails(){
    jsObj={
      districtId:19
    }
    $.ajax({
      type : "GET",
      url : "getConstituenciesForADistrictDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select constituency</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#constituencyId").html(str);    
  });
  }
 function getMandalDetails(){
    jsObj={
      constituencyId:232
    }
    $.ajax({
      type : "GET",
      url : "getMandalsForAConstituencyDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#mandalId").html(str);    
  });
  }
  function getPanchayatDetails(){
    jsObj={
      mandalId:844
    }
    $.ajax({
      type : "GET",
      url : "getVillagesForMandalIdDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#municipalityId").html(str);    
  });
  }
  function getBoothDetails(){
    jsObj={
      panchayatId:1
    }
    $.ajax({
      type : "GET",
      url : "getAllBoothsForPanchayatDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#boothId").html(str);    
  });
  }
$(document).on("click","#summaryId",function(){
	constiELecId = 232;
	  getcandidateAssetsAndLiabilities(constiELecId);
	  getAssetsElectionYearsInfo(constiELecId);
});
function getcandidateAssetsAndLiabilities(constiELecId)
	{
		showBusyImgWithId("electionYearSelectForAssets");

		var jsObj=
		{
				constiElecId:constiELecId,
				task:"getCandidateAssetsAndLiabilitiesInfo"						
		};

	/* 	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = <%=request.getContextPath()%>+"/candidateAssetsAndLiabilitiesAction.action?"+rparam; */
	  $.ajax({
      type : "GET",
      url : "candidateAssetsAndLiabilitiesAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildAssetsAndLiabilities(result);
	});
		
}
function getAssetsElectionYearsInfo(constiId){
        var jsObj=
		{
				constituencyId:constiId,
				task:"getConstituencyElectionYears"						
		};
	$.ajax({
      type : "GET",
      url : "constituencyElectionYearsWithAssets.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildElectionYearsWithAssets(result);
	});
}
function showBusyImgWithId(elmtId){		
		
		var spanElmt = document.getElementById(elmtId+"_ImgSpan");
		if(spanElmt)
			spanElmt.style.display = 'block';
}


function buildAssetsAndLiabilities(results){
	$("#myModal").modal("show");
	if(results != null && results.candidateNominations.length > 0){
		var str='';
 str+='<table style="width:100%">';
  str+='<thead>';
 str+='<tr>';
		str+='<th>CandidateName</th>';
		str+='<th>Party</th>'; 
		str+='<th>Gender</th>';
		str+='<th>Education</th>';
		str+='<th>Assets</th>';
		str+='<th>Liabilities</th>';
  str+='</tr>';
  str+='</thead>';
  str+='<tbody>';
  for (var i in results.candidateNominations){
	  	 str+='<tr>';
	  	 str+='<td>'+results.candidateNominations[i].candidateName+'</td>';
	  	 str+='<td>'+results.candidateNominations[i].party+'</td>';
		 if(results.candidateNominations[i].gender != null){
			 str+='<td>'+results.candidateNominations[i].gender+'</td>'; 
		 }else{
			  str+='<td>-</td>';
		 }
		 if(results.candidateNominations[i].education != null){
			str+='<td>'+results.candidateNominations[i].education+'</td>'; 
		 }else{
			 str+='<td>-</td>';
		 }
		 if(results.candidateNominations[i].assets != null){
			 str+='<td>'+results.candidateNominations[i].assets+'</td>';
		 }else{
			 str+='<td>-</td>';
		 }
	  	 if(results.candidateNominations[i].liabilities != null){
			 str+='<td>'+results.candidateNominations[i].liabilities+'</td>';
		 }else{
			 str+='<td>-</td>';
		 }
		 str+='</tr>';
  }
  str+='</tbody>';
str+='</table>';
$("#buildPoupupImage").html(str);
	}
	
}
function buildElectionYearsWithAssets(myResults)
	{

		var divElmt = document.getElementById("electionYearsWithAssets_Div");
		var selectedVal = '';

        var electionYearSelect = '';

        electionYearSelect += '<table>';
		electionYearSelect += '<th>Select Election Year to view Assets & Liabilities :</th>';
		electionYearSelect += '<th style="text-align:left;">';
		electionYearSelect += '<select id="electionYearSelectForAssets" class = "selectWidth" onchange = "getcandidateAssetsAndLiabilities(this.options[this.selectedIndex].value)">';
		for(var i in myResults)
		{			
			electionYearSelect += '<option value='+myResults[i].id+'>'+myResults[i].name+'</option>';
			if(i == 0)
				selectedVal = myResults[i].id;
		}
		electionYearSelect += '</select>';
		electionYearSelect += '<td><span id="electionYearSelectForAssets_ImgSpan" style="display:none;"><img src="images/icons/search.gif" /></span></td>';
		electionYearSelect += '</th>';

		electionYearSelect += '</table>';
	    divElmt.innerHTML = electionYearSelect; 

		getcandidateAssetsAndLiabilities(selectedVal);

	}
$(document).on("click","#candidateProfileId",function(){
	window.open('cadreDetailsAction.action?cadreId=5709765','_blank');
});
function getAssetsElectionYearsInfo(constiId){
        var jsObj=
		{
				candidateId:constiId					
		};
	$.ajax({
      type : "GET",
      url : "getConstituencyDetailedInformationAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		buildElectionYearsWithAssets(result);
	});
}