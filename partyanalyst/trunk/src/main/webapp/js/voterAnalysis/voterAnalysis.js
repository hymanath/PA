var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };
var publicationYear = "";
var impFamiliesEditArray = new Array();
var totalCategories = 0;
function populate(id,boothId,publicationId,houseNo){
     if($('#'+id).is(':checked')){
	   var obj={
	     boothId:boothId,
		 publicationId:publicationId,
		 houseNo:houseNo
	   }
	   impFamiliesEditArray.push(obj);
	 }else{
	     for(var i in impFamiliesEditArray){
	       if(impFamiliesEditArray[i].boothId == boothId && impFamiliesEditArray[i].publicationId == publicationId && impFamiliesEditArray[i].houseNo == houseNo){
		      impFamiliesEditArray.splice(i, 1);
		   }
	   }
	 
	 }

}

function editSelectedFamilies(){
		var errorElmt = document.getElementById("errorMessageDiv");
		var errorElmt1 = document.getElementById("errorMessageDiv1");
  if(impFamiliesEditArray.length > 0){
	  	$("#errorMessageDiv").html('');
			$("#errorMessageDiv1").html('');
	   if(impFamiliesEditArray.length > 5){
      alert("Please select atmost 5 families to edit");
      return;
   }
   totalCategories = 0;
     var jsObj=
	{
		selectedFamilies:impFamiliesEditArray,
		task:"editAllFamilies"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMultipleFamilesInfoAction.action?"+rparam+"&save=";	
	callAjax(jsObj,url);
  }
  else if(impFamiliesEditArray.length == '0'){
	  alert("new");
	 errorElmt.innerHTML = 'Please select on any  Voters family';
	errorElmt1.innerHTML = 'Please select on any  Voters family';
 }
}

function getAllVoterFamiliesForEdit(){
  var impFamiliesEditInfo = new Array();
	 $('.familyMemberCheck').each(function() {
           if($(this).is(':checked')){
		      var voterId = $(this).val();
			  var boothId = $(this).closest("tr").find(".selectedBoothId").val();
		       var obj={
				 boothId:boothId,
				 voterId:voterId
			   }
			   impFamiliesEditInfo.push(obj);
		   }
        });
    if(impFamiliesEditInfo.length > 0){
	     $("#multipleVoterFamiliesEditDiv").html("");
		 if(votersLimitExist){
		    if(impFamiliesEditInfo.length > 30)
			{
			  alert("Please select atomst 30 voters to edit");
			   return;
			}
		  }
		 totalCategories = 0;
		  var jsObj=
		  {
			selectedVoters:impFamiliesEditInfo,
			task:"allFamiliesEditInfo"
		  };
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";	
		callAjax(jsObj,url);
	}
}

function buildVotersInFamilyForEdit(results){
 var localid = 0;
   var str = "";
   if(results != null){
      for(var i in results.boothsList){
	    str+= '<div style="margin-top:10px;"><span><b>Panchayat Name: </b>'+results.boothsList[i].panchayatName+'</span> ';
        str+= '&nbsp;&nbsp;<span><b>Booth Name: </b></span>'+results.boothsList[i].boothName+'</div><div style="margin-top:5px;">';
	    str+= '<span ><b>Villiage Covered: </b></span>'+results.boothsList[i].villiageCovered+'</div>';
		var family = results.boothsList[i].familiesList;
		for(var j in family){
		   str+="<div class='votersEditTitle'>Voters in House No : "+family[j].houseNo+"</div>";
		   var voters = family[j].votersList;
		   str+="<fieldset>";
		   str+="<table style='width:900px;'>";
           var k = 0;		   
		   for(k in voters){
		       if(voters[k].categoriesList != null){
			      totalCategories = voters[k].categoriesList.length;
			   }
		       localid = localid+1;
		      if(k == 0)
			  str+="<tr>";	
		      str+="<td  style='width:300px;padding-top:20px;'>";
              str+="<table>";
			  str+="   <tr><td><b>Voter Name:</b></td><td>"+voters[k].name+"<input type='hidden' class='familyVoterEditId' value='"+voters[k].voterId+"' /></td></tr>";
			  str+="   <tr><td><b>Guardian Name:  </b></td><td>"+voters[k].gaurdian+"</td></tr>";
			  str+="   <tr><td><b>RelationShip:</b></td><td>"+voters[k].relationship+"</td></tr>";
			  str+="   <tr><td><b>Age:</b></td><td>"+voters[k].age+"</td></tr>";
			  str+="   <tr><td><b>Gender:</b></td><td>"+voters[k].gender+"</td></tr>";
			  str+="   <tr>";	
		      str+="    <td><b>Caste:</b></td>";		  
			  str+="    <td><select id='castvalid"+localid+"' class='castallfamily castallfamily"+j+"' >";
				     for(var l in results.casteGroupNameList){
					   if(voters[k].casteStateId != results.casteGroupNameList[l].id)
						str+="<option value="+results.casteGroupNameList[l].id+">"+results.casteGroupNameList[l].name+"</option>";
					   else
						str+="<option value="+results.casteGroupNameList[l].id+" selected='selected'>"+results.casteGroupNameList[l].name+"</option>";			
					 }
			  str+="    </select></td>";
			  str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"castvalid"+localid+"\",\"castallfamily"+j+"\")'><i class='icon-ok'></i></a></td>";
			  str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"castvalid"+localid+"\",\"castallfamily\")'><i class='icon-ok-sign'></i></a></td>";
			  str+="   </tr>";
		      str+="   <tr>";
		      str+="   <td><b>Party Name:</b></td>";
			  str+="    <td><select id='partyvalid"+localid+"' class='partyallfamily partyallfamily"+j+"' >";
				     for(var l in results.parties){
					   if(voters[k].partyId != results.parties[l].id)
						str+="<option value="+results.parties[l].id+">"+results.parties[l].name+"</option>";
					   else
						str+="<option value="+results.parties[l].id+" selected='selected'>"+results.parties[l].name+"</option>";			
					 }
			  str+="    </select></td>";
			  str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"partyvalid"+localid+"\",\"partyallfamily"+j+"\")'><i class='icon-ok'></i></a></td>";
			  str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"partyvalid"+localid+"\",\"partyallfamily\")'><i class='icon-ok-sign'></i></a></td>";
		       str+="   </tr>";
			    
			  for(var m in voters[k].categoriesList){
		         str+="   <tr>";
		          str+=" <td><b>"+voters[k].categoriesList[m].userCategoryValueName+":</b><input type='hidden' class='categ"+m+"main' value='"+voters[k].categoriesList[m].userCategoryValueId+"' /></td>";
				  str+="    <td><select id='categ"+m+"val"+localid+"' class='categ"+m+"ori categ"+m+"ori"+j+"' >";	
						for(var l in voters[k].categoriesList[m].category){
						   if(voters[k].categoriesList[m].categoryValuesId != voters[k].categoriesList[m].category[l].id)
							str+="<option value="+voters[k].categoriesList[m].category[l].id+">"+voters[k].categoriesList[m].category[l].name+"</option>";
						   else
							str+="<option value="+voters[k].categoriesList[m].category[l].id+" selected='selected'>"+voters[k].categoriesList[m].category[l].name+"</option>";			
						 }
				   str+="   </select></td>";
				   str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"categ"+m+"val"+localid+"\",\"categ"+m+"ori"+j+"\")'><i class='icon-ok'></i></a></td>";
			       str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"categ"+m+"val"+localid+"\",\"categ"+m+"ori\")'><i class='icon-ok-sign'></i></a></td>";
				   str+="   </tr>";
		         

			 }
			   str+="</table>";
			  str+="</td>";
			   if(k != 0 && k%3 == 2)
			    str+="</tr>";	
           }
		      if(k != 0 && k%2 != 2)
			     str+="</tr>";	
		   str+="</table>";	
           str+="</fieldset>";		   
		}
	 
	}
	 str+="<input id='votersEditSaveButtnImg' style='margin-bottom: 20px;margin-left: 360px;margin-top: 10px;' type='button' class='btn btn-success' onclick='updateAllSelectedVoters();' value='Update All Voters'/><input  style='margin-left: 10px;margin-top: -8px;' type='button' class='btn btn-success' onclick='ClearAllSelectedVoters();' value='Clear All Voters'/><img id='votersEditSaveAjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/ajaxImg.gif'>";
	 $("#multipleVoterFamiliesEditDiv").html(str);
   }
}

function ClearAllSelectedVoters(){
 $("#multipleVoterFamiliesEditDiv").html("");
}

function applyValueToAllVoters(id,classId){
       var value = $("#"+id).val();
       $('.'+classId).each(function() {
            $(this).val(value).trigger('change');;
        });
}

function updateAllSelectedVoters(){
      var votersEditInfo = new Array();
     $('.familyVoterEditId').each(function() {
	        var obj={
	    
	        };
			  obj["voterId"] = $(this).val();
			  obj["castId"] = $(this).closest("table").find(".castallfamily").val();
			  obj["partyId"] = $(this).closest("table").find(".partyallfamily").val();
			  if(totalCategories > 0){
			    for(var i=0; i<totalCategories ; i++){
				  var val1= $(this).closest("table").find(".categ"+i+"main").val();
				  var val2= $(this).closest("table").find(".categ"+i+"ori").val();
				  obj["categ"+i] = val1+","+val2;
				}
			  }
			  votersEditInfo.push(obj);
			
        });
		if(votersEditInfo.length > 0){
		 if(votersLimitExist){
		    if(votersEditInfo.length > 30)
			{
			  alert("Please select atomst 30 voters to edit");
			   return;
			}
		  }
		    $("#votersEditSaveAjaxImg").show();
			$("#votersEditSaveButtnImg").attr("disabled", "disabled");	
		     var jsObj=
		  {
		    total:totalCategories,
			selectedVoters:votersEditInfo,
			task:"allFamiliesEditInfoSave"
		  };
		  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		  var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";		  
		  callAjax(jsObj,url);
		}
}

function showReportLevel(value)
	{
		if(value == 1)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'none';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			document.getElementById('wardDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';
			document.getElementById('localityDiv').style.display = 'none';
		}
		else if(value == 2)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			document.getElementById('wardDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';
			document.getElementById('localityDiv').style.display = 'none';
			getMandalList('mandalField');

		}
		else if(value == 3)
		{
			
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			document.getElementById('wardDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';
			document.getElementById('localityDiv').style.display = 'none';
			getPanchayatList('panchayat','panchayatField');

		}
		else if(value == 4)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'block';
			document.getElementById('wardDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';
			document.getElementById('localityDiv').style.display = 'none';
			getPanchayatList('pollingstationByPublication','pollingStationField');
		}
		else if(value == 5)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('wardDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';
            document.getElementById('localityDiv').style.display = 'none';
           	//getWardsList('wardByPublication','wardField');
			getPanchayatList('ward','wardField');
		}
		else if(value == 6)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			//getPanchayatList('pollingstationByPublication','pollingStationField');
			document.getElementById('wardDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'block';
			document.getElementById('localityDiv').style.display = 'none';
		}
		else if(value == 7)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			
		
			document.getElementById('pollingStationDiv').style.display = 'none';
			//getPanchayatList('pollingstationByPublication','pollingStationField');
			
		
			document.getElementById('localityDiv').style.display = 'block';
			id = $("#mandalField").val();
			if(id.charAt(0) =="1"){
			document.getElementById('wardDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('hamletDiv').style.display = 'none';}
			else if(id.charAt(0) =="2"){ 
				document.getElementById('wardDiv').style.display = 'none';
				document.getElementById('panchayatDiv').style.display = 'block';
				document.getElementById('hamletDiv').style.display = 'block';
				getPanchayatList('panchayat','panchayatField');
			}else{
				document.getElementById('panchayatDiv').style.display = 'none';
				document.getElementById('hamletDiv').style.display = 'none';
				document.getElementById('wardDiv').style.display = 'none';
			}
			
			getPanchayatList('panchayat','panchayatField');
		}
	}

	

function buildVotersByLocBoothDataTable(id)
{

var boothId = $('#'+id).val();
var publicationDateId = $('#publicationDateList').val();
if(boothId == "0" || boothId == null || publicationDateId == null || publicationDateId == "0")
	return false;

var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
{key:"voterIDCardNo",label: "voter Id",sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
//{key:"relationshipType", label: "Relationship", sortable:true},
	{key:"mobileNo",label:"mobileNo",sortable:true}
];

YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
	var id = oRecord.getData("voterIds");
	var name = oRecord.getData("firstName");
	elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&publicationId="+publicationDateId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}

function buildVotersByLocPanchayatDataTable(id)
{
var publicationId = $('#publicationDateList').val();
var panchaytId =  $('#panchayatField').val();

if(panchaytId == "0" || publicationId == "0")
	return false;
var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true},
{key:"voterIDCardNo", label: "voter ID",sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"mobileNo",sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&panchaytId="+panchaytId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}
return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}	

function showImportantFamiliesDiv()
	{
		var ImpDiv = document.getElementById('ImportantFamiliesDiv');
		document.getElementById('votersDiv1').style.display='block';
		document.getElementById('votersDiv2').style.display='none';
		document.getElementById('votersDiv3').style.display='none';
		document.getElementById('votersDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		 getvotersBasicInfo("impFamilies","");
		  $("#votersbasicinfoForImpFam").hide();
		  $("#votersBasicInfoDivForImpFam").html("");
		  $("#votersBasicInfoSubChartDivForImpFam").html("");
		  $("#votersBasicInfoSubDivForImpFam").html("");
		  $("#impFamShowBasicInfo").val("View Basic Voter Details");
		
	}
	
	function showLocalCastDiv()
	{
		
		var LocalCastDiv = document.getElementById('LocalCastDiv');
		document.getElementById('votersDiv1').style.display='none';
		document.getElementById('votersDiv2').style.display='block';
		document.getElementById('votersDiv3').style.display='none';
		document.getElementById('votersDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		  $("#votersbasicinfoForLclCastSts").hide();
		  $("#votersBasicInfoDivForLclCastSts").html("");
		  $("#votersBasicInfoSubChartDivForLclCastSts").html("");
		  $("#votersBasicInfoSubDivForLclCastSts").html("");
		  $("#lclCastStsShowBasicInfo").val("View Basic Voter Details");
		 $("#localCastStatsVotersTitle").css({"background":"none repeat scroll 0 0 #FFF;"});

	}

	function showVotersDiv()
	{
		var VotersDiv = document.getElementById('votersDiv');
		
		document.getElementById('votersDiv1').style.display='none';
		document.getElementById('votersDiv2').style.display='none';
		document.getElementById('votersDiv3').style.display='block';
		document.getElementById('votersDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		 getvotersBasicInfo("voters","");
		 getVotersData();
		 showNewsDetails();
		  getCounts();
		  getProblemsByLocation();
	}
	function DefaultHighLight()
	{
		var VotersDiv = document.getElementById('votersDiv');
		document.getElementById('votersDiv1').style.display='none';
		document.getElementById('votersDiv2').style.display='none';
		document.getElementById('votersDiv3').style.display='block';
		document.getElementById('votersDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		
		 
	}

	function showAgeDiv()
	{
		var VotersDiv = document.getElementById('ageWiseInfoDiv');
		
		document.getElementById('votersDiv1').style.display='none';
		document.getElementById('votersDiv2').style.display='none';
		document.getElementById('votersDiv3').style.display='none';
		document.getElementById('votersDiv4').style.display='block';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#votersbasicinfoForAgeWiseDetls").hide();
		$("#votersBasicInfoDivForAgeWiseDetls").html("");
		$("#votersBasicInfoSubChartDivForAgeWiseDetls").html("");
		$("#votersBasicInfoSubDivForAgeWiseDetls").html("");
        $("#ageWiseDetlsShowBasicInfo").val("View Basic Voter Details");
		 callCorrespondingAjaxCall();
	}
	
	
	function getMandalList(selectElmt)
	{ 	 //  document.getElementById('ajaxLoad').style.display = 'block';
		var constituencyID = document.getElementById("constituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		if(value == 0)
		{
			return false;
		}
		var jsObj=
			{
					
					selected:value,
					selectElmt:selectElmt,
					task:"getMandalList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		showNewsDetails();
	}
	function getHamletsList(type,selectElmt)
	{
		var panchayatId = document.getElementById("panchayatField");
		var name=panchayatId.options[panchayatId.selectedIndex].name;
		var value=panchayatId.options[panchayatId.selectedIndex].value;
		var publicationValue = $('#publicationDateList').val();
		var choice=false;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		if(value == 0)
		{
			return false;
		}
		var jsObj=
			{
					
					selected:value,
					selectedEle:selectElmt,
					publicationValue : publicationValue,
					task:"getHamletsList"
			};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		showNewsDetails();
	}


	function getPanchayatList(checkedele,selectedEle)
	{
		showNewsDetails();
		var reportLevel = $("#reportLevel").val();
		var mandalId=document.getElementById("mandalField");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;
		var type = "mandal";
		if(value1.charAt(0) =="1"){
		 type = "muncipality";
		}
		var value = value1.substring(1);
		var publicationValue = $('#publicationDateList').val();
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		var selectname = mandalId.options[mandalId.selectedIndex].text;
		var flag= selectname.search("MUNCIPALITY");
		 if(value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1 && checkedele == "panchayat")
		{
		document.getElementById('panchayatDiv').style.display='none';
		}
		else if(flag != -1 && checkedele == "pollingstationByPublication" && reportLevel == 4)
		{
		 flag = -1;
		 document.getElementById('panchayatDiv').style.display='none';
		}
		if(flag == -1 && checkedele != "ward" && reportLevel != 5 )
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				constituencyId:$("#constituencyList").val(),
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}else
	if(checkedele == "ward" && reportLevel == 5 )
		{
		document.getElementById('panchayatDiv').style.display='none';
		 
			document.getElementById('pollingStationDiv').style.display = 'none';
			//getPanchayatList('pollingstationByPublication','pollingStationField');
			document.getElementById('wardDiv').style.display = 'block';
			document.getElementById('hamletDiv').style.display = 'none';
			document.getElementById('localityDiv').style.display = 'none';
		
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getWards"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "voterAnalysisAjaxAction.action?"+rparam;								
		callAjax(jsObj,url);
		
	}
	}
	
	
	function getPublicationDate()
	{
	document.getElementById('ajaxLoad').style.display = 'block';
	var constituencyID = document.getElementById("constituencyList");
	var name=constituencyID.options[constituencyID.selectedIndex].name;
	var value=constituencyID.options[constituencyID.selectedIndex].value;
	var choice=false;
	var locationAlertEl =  document.getElementById("locationAlertMsg");
	
	var jsObj=
	{
		selected:value,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	
	callAjax(jsObj,url);
}

	

		function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
									if(jsObj.task == "getMandalList")
								{     
										buildMandalList(myResults,jsObj);
										
								}

								else if(jsObj.task == "getPanchayat")
									{
										buildPanchayatData(myResults,jsObj);
									}
								else if(jsObj.task == "getPublicationDate")
								{
									buildPublicationDateList(myResults);
								}
								else if(jsObj.task == "votersbasicinfo")
								{
								    if(myResults != null)
									  buildVotersBasicInfo(myResults,jsObj.to,jsObj);
									else if( jsObj.to == ""){
									  $("#votersBasicInfoSubChartDiv").removeAttr('style');
									  $("#votersBasicInfoSubDiv").removeAttr('style');
									}
									 
								}
							    else if(jsObj.task == "getCastInfo")
								{
									buildCastInfoData(myResults,jsObj);
								}
								else if(jsObj.task == "getCountForLevel")
								{
									if(jsObj.type == "constituency")
									buildCountData(myResults,jsObj);
									else
									buildCountData1(myResults,jsObj);
								}
								else if(jsObj.task == "getCastInfoForsubLevels")
								{
									buildCastInfoForSubLevels(myResults,jsObj);
								}								
								else if(jsObj.task == "importantFamiliesinfo")
								{
								  if(myResults != null){
								    impFamilesStaticTable(myResults,jsObj);
									buildImpFamilesChart(myResults);
								    if(myResults.subList != null && myResults.subList.length > 0)
									  {
										buildTableForImpFamilesMandal(myResults.subList,myResults.name,myResults.type);
										impFamilesVariableDescription();
									  }
								   }
								}
								else if(jsObj.task == "gettotalimpfamlies")
								{
								    buildFamilyMembers(myResults,jsObj.publicationDateId,jsObj.type);
								}
                                else if(jsObj.task == "getVotersInAFamily")
								{
								    buildVotersInFamily(myResults,jsObj.hno);
								}
								else if(jsObj.task =="getVotersInACaste")
								{
									buildVotersInACaste(myResults,jsObj)
								}
								else if(jsObj.task =="editAllFamilies"){
								    if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else{
								     buildVotersInFamily(myResults);
									}
								}
								else if(jsObj.task =="allFamiliesEditInfo"){
								    if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else{
								     buildVotersInFamilyForEdit(myResults);
									}
								}
								else if(jsObj.task =="allFamiliesEditInfoSave"){
								    $("#votersEditSaveAjaxImg").hide();
									$("#votersEditSaveButtnImg").removeAttr("disabled");
									if(myResults == true){
									  alert("Voters Information Updated SuccessFully");
									}else if(myResults == "notLogged"){
									  openDialogForLoginWindow();
									}else if(myResults == "exception"){
									  alert("Unable To Updated Voters Information Please Try Again");
									}
								}

								else if(jsObj.task =="getPreviousEleVotingTrends")
								{
									showPreviousEleVotingTrends(myResults,jsObj);	
								}
                                else if(jsObj.task == "getVotersInAFamilySearch")
								{
								    buildVotersInFamilySearch(myResults,jsObj.hno);
								}	else if(jsObj.task == "getWards")
								{
								   buildWardsList(myResults,jsObj);
								}	
                                else if(jsObj.task == "getHamletsList")
								{
								   buildWardsList(myResults,jsObj);
								}
                                 else if(jsObj.task == "getLocalities")
								{
								   populatedataTodiv(myResults,jsObj);
								}								
							}catch (e) { 
							     $("#votersEditSaveAjaxImg").hide();
							     $("#votersEditSaveButtnImg").removeAttr("disabled");
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}

	
	function buildMandalList(results,jsObj)
	{   
		//var selectedElmt=document.getElementById("mandalField");
		var selectElmt =jsObj.selectElmt;
		var selectedElmt=document.getElementById(selectElmt);
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}
	function buildWardsList(results,jsObj)
	{
		//var selectedElmt=document.getElementById("mandalField");
		var selectElmt =jsObj.selectedEle;
		var selectedElmt=document.getElementById(selectElmt);
		removeSelectElements(selectedElmt);
		for(var val in results)
		{
			var opElmt = document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}	
		}
	}
		
	function buildPanchayatData(results,jsObj)
		{
	
		var reportLevel = $("#reportLevel").val();
		var checkedEle = jsObj.checkedele;
		var selectedEle = jsObj.selectedEle;
		var flag = jsObj.flag;
		var select = document.getElementById(selectedEle);
	
		if(reportLevel == 3)
		{
		document.getElementById('panchayatDiv').style.display = 'block';
		}
		if(reportLevel == 4)
		{
		document.getElementById('pollingStationDiv').style.display = 'block';
		}
		removeSelectElements(select);
		for(var i in results)
		{
			if(results[i] == null)
				continue;
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				select.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				select.add(opElmt); // IE only
				}
		}

	}
	function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	function buildPublicationDateList(results)
	{
	document.getElementById('ajaxLoad').style.display = 'none';
	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}
$(document).ready(function(){

	$("#votersId").click(function(){
		getPreviousElectionVotingTrends();
		
	});	
    $("#publicationDateList").change(function(){
	    if($("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0){
		   var str = $('#publicationDateList :selected').text();
		   var year=str.split("-");
		   try{
		    publicationYear =year[2];
		    }catch(e){
		    }
			if($("#reportLevel").val() == 4 && $("#mandalField").val() >0){
			  getPanchayatList('pollingstationByPublication','pollingStationField');
			}
		}else{
		    publicationYear = "";
		}
	});
    $("#impFamShowBasicInfo").click(function(){
	   if($('#votersbasicinfoForImpFam').css('display') == 'none'){
	     $(this).val("Hide Basic Voter Details");
		  getvotersBasicInfo("voters","impfamilies");
		 $('#votersbasicinfoForImpFam').show();  
	   }else{
	     $(this).val("View Basic Voter Details");
		 $('#votersbasicinfoForImpFam').hide();
	   }
	});
	$("#lclCastStsShowBasicInfo").click(function(){
	  if($('#votersbasicinfoForLclCastSts').css('display') == 'none'){
	     $(this).val("Hide Basic Voter Details");
		  getvotersBasicInfo("voters","localcaststs");
		 $('#votersbasicinfoForLclCastSts').show();  
	   }else{
	     $(this).val("View Basic Voter Details");
		 $('#votersbasicinfoForLclCastSts').hide();
	   }
	});
	$("#ageWiseDetlsShowBasicInfo").click(function(){
	   if($('#votersbasicinfoForAgeWiseDetls').css('display') == 'none'){
	     $(this).val("Hide Basic Voter Details");
		  getvotersBasicInfo("voters","agewise");
		 $('#votersbasicinfoForAgeWiseDetls').show();  
	   }else{
	     $(this).val("View Basic Voter Details");
		 $('#votersbasicinfoForAgeWiseDetls').hide();
	   }
	});
    $("#constituencyList").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 1 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
		{
	      getBasicInfo();
		 getPreviousElectionVotingTrends();
		}

	  
	});
	$("#mandalField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 2 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getBasicInfo();
		 getPreviousElectionVotingTrends();

	});
	$("#panchayatField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 3 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getBasicInfo();
	   getPreviousElectionVotingTrends();

	});
	$("#pollingStationField").live("change",function(){
	  $('.voterDetails').html('');
	   $('.noteDiv').html('');
	   if($(this).val() != 0 && $("#reportLevel").val() == 4 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getBasicInfo();
		
	});
    $("#publicationDateList").live("change",function(){
    
       $('.voterDetails').html('');
	   $('.noteDiv').html('');
	   if($(this).val() != 0 ){

	     if($("#reportLevel").val() == 1 && $("#constituencyList option").length > 0 && $("#constituencyList").val() != 0)
		   
	       getBasicInfo();
		else if($("#constituencyList option").length > 0 && $("#constituencyList").val() != 0)
			getCountsForConstituency();
		   
		 else if($("#reportLevel").val() == 2 && $("#mandalField option").length > 0 && $("#mandalField").val() != 0 )
		  
		   getBasicInfo();
		 
		 else if($("#reportLevel").val() == 3 && $("#panchayatField option").length > 0 && $("#panchayatField").val() != 0 )
		  
		   getBasicInfo();
		  
		 else if($("#reportLevel").val() == 4 && $("#pollingStationField option").length > 0 && $("#pollingStationField").val() != 0 )
		  { 
		    //getBasicInfo();
		  }
			
		}
	});

	$('.linkClass').live('click',function(){

		$('.linkClass').removeClass('selectedLink');
		$(this).addClass('selectedLink');
	});
});

function getBasicInfo(){
         if($('#votersDiv1').css('display') == 'block'){
		   getvotersBasicInfo("impFamilies","");
		   $("#votersbasicinfoForImpFam").hide();
		   $("#votersBasicInfoDivForImpFam").html("");
		   $("#votersBasicInfoSubChartDivForImpFam").html("");
		   $("#votersBasicInfoSubDivForImpFam").html("");
		   $("#impFamShowBasicInfo").val("View Basic Voter Details");
		 }else if($('#votersDiv2').css('display') == 'block'){
		   getVotersCastInfo();
		   getCastInfoForsubLevel();
		   $("#votersbasicinfoForLclCastSts").hide();
		   $("#votersBasicInfoDivForLclCastSts").html("");
		   $("#votersBasicInfoSubChartDivForLclCastSts").html("");
		   $("#votersBasicInfoSubDivForLclCastSts").html("");
		   $("#lclCastStsShowBasicInfo").val("View Basic Voter Details");
		 }else if($('#votersDiv3').css('display') == 'block'){
		   getvotersBasicInfo("voters","");
		   getVotersData();
		   showNewsDetails();
		    getCounts();
			getProblemsByLocation();			
			getPreviousElectionVotingTrends();
		 }else if($('#votersDiv4').css('display') == 'block'){
			
             callCorrespondingAjaxCall();
           
		   $("#votersbasicinfoForAgeWiseDetls").hide();
		   $("#votersBasicInfoDivForAgeWiseDetls").html("");
		   $("#votersBasicInfoSubChartDivForAgeWiseDetls").html("");
		   $("#votersBasicInfoSubDivForAgeWiseDetls").html("");
		   $("#ageWiseDetlsShowBasicInfo").val("View Basic Voter Details");
		 }else{
		   DefaultHighLight();
		   getvotersBasicInfo("voters","");
			
		   getVotersData();
		   getCounts();
		 }
}


function callCorrespondingAjaxCall(){
	var reportLevel = $('#reportLevel').val();

	if(reportLevel == "1")
		getVoterDetailsForConstituency();
	else if(reportLevel == "2")
		getVoterDetailsForMandal();
	else if(reportLevel == "3")
		getVoterDetailsForPanchayat();
	else if(reportLevel == "4")
		getVoterDetailsForBooth();
}

function getVotersData(){
  var level = $("#reportLevel").val();
    if(level == 3){
	  $("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocPanchayatDataTable("panchayatField");
	}else if(level == 4){
	  $("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocBoothDataTable("pollingStationField");
	}else{
	  $("#votersByLocationTabContentDiv_body").removeAttr('style'); 
	}
}

function getVotersCastInfo()
	{
  $("#localCastStatsTabContent_header").html("");
  $("#localCastStatsTabContentTitle").html("");
  $("#localCastStatsTabContent_body").html("");
  var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
  var errorDivEle = document.getElementById('AlertMsg');
  var publicationDateId = $("#publicationDateList").val();
	var level = $("#reportLevel").val();
	var type = '';
	var id='';
	var typename='';
	var mandalId='';
	var flag =true;
	var str= '<font color="red">';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	typename = $('#constituencyList :selected').text() + ' Constituency ';
	if(id == 0 ||id == null)
		{
		str +='Please Select Constituency';
		flag =false;
		}
	}
	else if(level == 2){
	type = 'mandal';
	id = $("#mandalField").val();
	typename = $('#mandalField :selected').text();
	if(id == 0 || id == null)
	{
	str +='Please Select Mandal';
	flag =false;
	}
 }
	else if(level == 3){
	var constituencyValue =$("#constituencyList").val(); 
	var mandalValue = $("#mandalField").val();
	var mandalText = $('#mandalField :selected').text();
	var validflag= mandalText.search("MUNCIPALITY");
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  typename = $('#panchayatField :selected').text()+ ' Panchayat ';
	if(constituencyValue == 0 || constituencyValue == null)
			{
				str +='Please Select Constituency';
				flag =false;
			}
	else if(validflag != -1)
			{
				str +='There are No Panchayats In Muncipality,Select Other Report Level to view Report';
				flag =false;
				
			}
	else if(mandalValue == 0 || mandalValue == null)
			{
				str +='Please Select Mandal';
				flag =false;
			}
			
	else if(id == 0 || id == null)
			{
				str +='Please Select Panchayat';
				flag =false;
			}
	}
	else if(level == 4){
		var constituencyValue =$("#constituencyList").val(); 
		var mandalValue = $("#mandalField").val();
		var mandalText = $('#mandalField :selected').text();
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 typename = $('#pollingStationField :selected').text();
		 if(constituencyValue == 0 || constituencyValue == null)
			{
				str +='Please Select Constituency';
				flag =false;
			}
			else if(mandalValue == 0 || mandalValue == null)
			{
				str +='Please Select Mandal';
				flag =false;
			}
			
			else if(id == 0 || id == null)
			{
			str +='Please Select Booth';
			flag =false;
			}
	}

	 if(publicationDateId == 0 || publicationDateId == null )
		{
		str +='<br>Please Select Publication Date';
		flag =false;
		}
		errorDivEle.innerHTML = str;
		if(flag)
		{
		errorDivEle.innerHTML ='';
		showAjaxImgDiv('ajaxImageDiv');
		var jsObj=
			{
				type:type,	
				id:id,
				typename:typename,
				publicationDateId:publicationDateId,
				task:"getCastInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
	 }
}
function showAjaxImgDiv(id)
{
	document.getElementById(id).style.display = 'block';
}
function hideAjaxImgDiv(id)
{
	document.getElementById(id).style.display = 'none';
}
function buildCastInfoForSubLevels(myresults,jsObj)
	{
		
		var str ='';
		$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");
		var divId=document.getElementById('localCastStatsTabContent_subbody');
		var publicationDateId = jsObj.publicationDateId;
		var type=jsObj.type;
		var	subLevelcastInfo = new Array();
		var cast = myresults.castVosList;
		var typeName=jsObj.typeName;
		for(var i in cast)
		{
		if(cast[i].voterCastInfoVO != null)
		{
		var subLevelcastData = cast[i].voterCastInfoVO; 
		if(cast[i].mandalName != null)
		var name = cast[i].mandalName;
		else
		var name ="";
		if(cast[i].locationId != null)
		var locationId=cast[i].locationId;
		else
		locationId = 0;
		var totalVoters=subLevelcastData.totalVoters;
		var cast1 =subLevelcastData.castVOs;
			
			for(var k in cast1)
			{
		var castStats1 = {
			mandal : name,
			locationId:locationId,
			caste : cast1[k].castName,
			casteCategory:cast1[k].casteCategoryName,
			castePopulation : cast1[k].castCount,
			malePopulation : cast1[k].malevoters,
			femalePopulation : cast1[k].femalevoters,
			castePercentage:cast1[k].castPercentage,
			totalVoters:totalVoters,
			castStateId:cast1[k].castStateId,
			};
		subLevelcastInfo.push(castStats1);
			}
		   }
		 }
		constMgmtMainObj.castStatssubArray =subLevelcastInfo;
		if(type != 'booth')
		{
		str +='<table id="subLevelTable">';
		if(type == 'constituency')
		str+='<h4 id="sublevelHeading">Mandal/Muncipality wise Caste Statistics In '+typeName+'Constituency</h4>';
		else if(type == "mandal")
		str+='<h4 id="sublevelHeading">Panchayat wise Caste Statistics In '+typeName+' </h4>';
		else if(type =="panchayat")
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Panchayat</h4>';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "constituency")
		str +='<th>Mandal</th>';
		if(type == "mandal")
		str +='<th>Panchayat</th>';
		if(type =="panchayat")
		str +='<th>Booth</th>';

		str +='<th>Caste</th>';
		str+='<th>Caste Category</th>';
		str +='<th>Total Voters</th>';
		str +='<th>Caste Voters</th>';
		str +='<th>Male Voters</th>';
		str +='<th>Female Voters</th>';
		str +='<th>Caste Percentage</th>';
		
		str+='</tr>';
		str+='</thead>';
		
		str+='<tbody>';
		for(var i in constMgmtMainObj.castStatssubArray)
		{
		str+='<tr>';
		
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].mandal+'</td>';
		if(type == "mandal")
		{
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'panchayat\',\''+constMgmtMainObj.castStatssubArray[i].mandal+' Panchayat\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="panchayat")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'booth\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].caste+'</td>';
		}
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].casteCategory+'</td>';
		str +='<td>'+constMgmtMainObj.castStatssubArray[i].totalVoters+'</td>';
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].castePopulation+'</td>';
		if(constMgmtMainObj.castStatssubArray[i].malePopulation ==null)
		str+='<td>'+0+'</td>';
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].malePopulation+'</td>';
		}
		if(constMgmtMainObj.castStatssubArray[i].femalePopulation ==null)
		{
			str+='<td>'+0+'</td>';
		}
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].femalePopulation+'</td>';
		}
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].castePercentage+'</td>';
	
		}
		
		str +='</tr>';
		str+='</tbody>';
		str +='</table>';

		divId.innerHTML = str;
		$('#subLevelTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null
		] 
		});
	$('#subLevelTable tr').removeClass("odd");
	$('#subLevelTable tr').removeClass("even");
	$('#subLevelTable td').removeClass("sorting_1"); 
	}	
	}
function getVotersInACaste(id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
{
$("#localCastStatsVotersTitle").html("");
$("#localCastStatsTabContent_subbody1").html("");
var level = $("#reportLevel").val();
if(level == 2)
var typename = $('#mandalField :selected').text();
if(level == 3)
var typename = $('#panchayatField :selected').text()+ ' Panchayat ';
if(level == 4)
var typename = $('#pollingStationField :selected').text();
var publicationDateVal=$('#publicationDateList :selected').text();
var year=publicationDateVal.substr(publicationDateVal.length - 4)
var jsObj={
			id:id,
			publicationDateId:publicationDateId,
			//caste:"32",
			caste:casteStateId,
			casteName:caste,
			typename:typename,
			type:type,
			publicationDate:year,
			Name:Name,
			casteCategory:casteCategory,
			task:"getVotersInACaste"

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

}
function getvotersBasicInfo(buttonType,voterBasicInfoFor){
  if(buttonType == "voters"){
     $("#votersBasicInfoDiv").html("");
     $("#votersBasicInfoSubChartDiv").html("");
     $("#votersBasicInfoSubDiv").html("");
	 $("#votersByLocationTabContentDiv_body").html("");
	 $("#previousEleVotingTrendsDiv").html('');
	 //$("#votersByPanchayatTabContentDiv_body").html("");
   }
  if(buttonType == "impFamilies"){
   $("#impFamilesBasicDetails").html("");
   $("#impFamilesBasicInfoSubChartDiv").html("");
   $("#impFamilesBasicSubDetails").html("");
   $("#impFamilesBasicSubDetailsTitle").html("");
   $("#impFamPancBothDtls").html("");
   $("#impFamDtlsTitle").html("");
   $("#impFamDtls").html("");
   $("#NoteDiv").css("display","none"); 
   $("#NoteDiv").html("");
   $("#descriptionDiv").html('');
  }
   var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
  
    var level = $("#reportLevel").val();
	var type = '';
	var id = '';
	var str ='<font color="red">';
	var flag =true;
	var errorDivEle =document.getElementById('AlertMsg'); 
	var publicationDateId = $("#publicationDateList").val();
	var publicationDateText =$("#publicationDateList option:selected").text();
	var year=publicationDateText.substr(publicationDateText.length - 4);
	var typename='';
	if(level == 1){
	   type = 'constituency';
	   id = $("#constituencyList").val();
	   typename =$("#constituencyList :selected").text() + ' Constituency';
	   if(id == 0 ||id == null)
		{
		  str +='Please Select Constituency';
		  flag=false;
		}

	}else if(level == 2){
	  type = 'mandal';
	  id = $("#mandalField").val();
	  typename =$("#mandalField :selected").text();
	   if(id == 0 ||id == null)
		{
		  str +='Please Select Mandal';
		  flag=false;
		}
    }else if(level == 3){
		var constituencyValue =$("#constituencyList").val(); 
		var mandalValue = $("#mandalField").val();
		var mandalText = $('#mandalField :selected').text();
		var validflag= mandalText.search("MUNCIPALITY");
		type = 'panchayat';
		id = $("#panchayatField").val();
		typename =$("#panchayatField :selected").text()+' Panchayat';
	 if(constituencyValue == 0 || constituencyValue == null)
		{
				str +='Please Select Constituency';
				flag =false;
		}
		else if(mandalValue == 0 || mandalValue == null)
		{
				str +='Please Select Mandal';
				flag =false;
		}
	  else if(validflag != -1)
		{
				str +='There are No Panchayats In Muncipality,Select Other Report Level to view Report';
				flag =false;
				
		}
	   else if(id == 0 ||id == null)
		{
		  str +='Please Select Panchayat';
		  flag=false;
		}
	}else if(level == 4){
	var constituencyValue =$("#constituencyList").val(); 
	var mandalValue = $("#mandalField").val();
	var mandalText = $('#mandalField :selected').text();
	var validflag = -1;
	//var validflag= mandalText.search("MUNCIPALITY");

	  type = 'booth';
	  id = $("#pollingStationField").val();
	  typename =$("#pollingStationField :selected").text();
	  if(constituencyValue == 0 || constituencyValue == null)
		{
				str +='Please Select Constituency';
				flag =false;
		}
	else if(validflag != -1)
		{
				str +='There are No Panchayats In Muncipality,Select Other Report Level to view Report';
				flag =false;
				
		}
		else if(mandalValue == 0 || mandalValue == null)
		{
				str +='Please Select Mandal';
				flag =false;
		}
	   else if(id == 0 || id == null)
		{
		  str +='Please Select Booth';
		  flag=false;
		}
	}
	if(publicationDateId == 0 || publicationDateId == null)
	{
		str +='<br>Please Select Publication Date';
		flag = false;
	}
	errorDivEle.innerHTML = str;
	if(flag)
	{
	errorDivEle.innerHTML = '';		
   if(buttonType == "voters"){
	  showAjaxImgDiv('ajaxImageDiv');
	var jsObj=
			{
				
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				to:voterBasicInfoFor,
				year:year,
				typename:typename,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	if(buttonType == "impFamilies"){
		showAjaxImgDiv('ajaxImageDiv');
	   var jsObj1=
			{
					
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				typename:typename,
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;						
		callAjax(jsObj1,url1);
	if(type == 'panchayat' || type == 'booth' ){
	 var reqtype = type;
	 if(type == 'booth'){
	    reqtype = 'pollingstation';
	 }
	 showAjaxImgDiv('ajaxImageDiv');
	    var jsObj2=
			{
					
				type:reqtype,
				id:id,
				publicationDateId:publicationDateId,
				typename:typename,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	}
   }
	}
}

function getvotersFamileyInfo(buttonType,voterBasicInfoFor)
 { 
  $('#imgDiv').show();
  $('#impfamilydatatable_wrapper').hide();
  var level = $("#reportLevel").val();
  var publicationDateId = $("#publicationDateList").val();
  var type = '';
  var id = '';
  var typename = '';
  if(level == 3)
  {
	type='panchayat';
	id = $('#panchayatField').val();
	typename =$("#panchayatField :selected").text()+' Panchayat';
  }
  if(level == 4)
  {
	
	type = 'booth';
    id = $("#pollingStationField").val();
	typename =$("#pollingStationField :selected").text();
  }
	if(buttonType == "impFamilies"){
	if(type == 'panchayat' || type == 'booth' ){
	 var reqtype = type;
	 if(type == 'booth'){
	    reqtype = 'pollingstation';
	 }
	 
	 
	 showAjaxImgDiv('ajaxImageDiv');
	    var jsObj2=
			{
					
				type:reqtype,
				id:id,
				publicationDateId:publicationDateId,
				typename:typename,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	
   }
	}
}

function getVotersInAFamily(id,publicationDateId,hNo){
    var jsObj=
			{
					
				hno:hNo,
				id:id,
				publicationDateId:publicationDateId,
				task:"getVotersInAFamily"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "votersFamilyDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);

}
function openProblemEditForm(id,boothId)
{

	var urlStr="votersEditAction.action?voterId="+id+"&boothId="+boothId+" ";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();	
}	


function buildVotersInACaste(results,jsObj)
{

var result = results;

	if(results.votersByHouseNos != null)
		$("#localCastStatsVotersTitle").addClass("localCastStatsVotersTitle").html(" "+jsObj.casteName+"("+jsObj.casteCategory+") Caste voters Details In " +jsObj.Name+" in "+jsObj.publicationDate+" ");
	else
		$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");

	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var boothId=oRecord.getData("boothId");
		var id=oRecord.getData("voterId");
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	 var votersResultColumnDefs = [ 		    	             
		    	            
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,
							formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"voterIdCardNo",label:"Voter Id",sortable: true},
	                        {key:"mobileNo",label:"mobileNo",sortable:true}
		    	        ]; 

    
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};
		
		
	var myDataSource = new YAHOO.util.DataSource(results.votersByHouseNos);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId","voterIdCardNo"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersResultColumnDefs,myDataSource, myConfigs);


}


function buildVotersInFamily(results){
//console.log(results);
    $("#multipleVoterFamiliesEditDiv").html("");
    $("#impFamDtlsTitle").html("<b>Voter Details</b>");
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a id="openProblemEditFormId" onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	  YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	  {
		var name = oData;
		var id= oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
					
	  };
     var votersResultColumnDefs = [ 		    	             
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"mobileNo",label:"mobileNo",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#impFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
}

function buildCastInfoData(myresults,jsObj)
{
	$('#localCastDetailsHeadingDiv').html('');
	$('.localCastStatsVotersTitle').html('');
	
	var result = myresults.voterCastInfodetails;
	var ajaxImageDiv = document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
	var totalVoters = result.totalVoters;
	var totalCasts = result.totalCasts;
	var totalMale = result.maleVoters;
	var totalFemale = result.femaleVoters;
	var voters = '';
	
	var localCastStatsTabContent = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"> Total Voters : '+totalVoters+'&nbsp;&nbsp;&nbsp;';
	localCastStatsTabContent += '&nbsp;&nbsp;&nbsp;Total Casts : '+totalCasts+'&nbsp;&nbsp;&nbsp;<br><br>';
	//localCastStatsTabContent += 'Total Male Voters : '+totalMale+'&nbsp;&nbsp;&nbsp;';
	//localCastStatsTabContent += 'Total Female Voters : '+totalFemale+'<br><br>';
	localCastStatsTabContent += '<span>Caste Assigned Voters : '+result.maleVoters+'</span>';
	localCastStatsTabContent += '<span style="padding-left:40px;">Caste Not Assigned Voters : '+result.femaleVoters+'</span>';
	localCastStatsTabContent += '<br><br>';
	
	$('#localCastDetailsHeadingDiv').html('Cast category wise voters details').css("background","#06ABEA");
	for(var i=0;i<result.castCategoryWiseVotersList.length;i++)
		voters +='<p>'+result.castCategoryWiseVotersList[i].name+' Voters : '+result.castCategoryWiseVotersList[i].id+'</p>';
		voters +='</div>';
		

	localCastStatsTabContent += '</div>';
	$('#localCastDetailsDiv').html(voters);
	localCastStatsTabContent_headerEl.innerHTML = localCastStatsTabContent;

	var typeName = jsObj.typename;	
	var publicationDateId=jsObj.publicationDateId;
	var boothId=jsObj.id;
	var type =jsObj.type;
	var	castIno = new Array();
	
	var cast = result.voterCastInfoVOList;
		for(var i in cast)
		{
		var castStats = 
			{
			caste : cast[i].castName,
			casteCategory:cast[i].casteCategoryName,
			castePopulation : cast[i].totalVoters,
			malePopulation : cast[i].maleVoters,
			femalePopulation : cast[i].femaleVoters,
			castePercentage:cast[i].votesPercent,
			casteStateId : cast[i].casteStateId,
			
			};
		castIno.push(castStats);
		constMgmtMainObj.castStatsArray =castIno; 
		}
		if(cast != '')
	{
		buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId,type);	

	}
		
		else{
		$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
		$("#localCastStatsTabContent_body").html("No Data Found");
		$('.localCastStatsVotersTitle').css("backgrond","#FFF;");
		}

		buildCastPiechart(myresults,jsObj);  
		buildPartyWisePiechart(myresults,jsObj);
		buildPartyWiseCastData(myresults,typeName,publicationDateId,boothId,type);
		buildPartyWiseCastDetailsTable(myresults,jsObj); 
}

	function buildPartyWiseCastDetailsTable(myresults,jsObj){
	   $("#partyWiseLocalCastStatsTab").html("");
	   if(myresults != null && myresults.voterCastInfodetails != null && myresults.voterCastInfodetails.castVOs != null && myresults.voterCastInfodetails.castVOs.length > 0){
	       var result = myresults.voterCastInfodetails.castVOs;
		   var str ='<div style="overflow-x:scroll;">';
		      str+='<div id="partyWiseLocalCastStatsTabTitle">Cast Vs Party analysis of '+jsObj.typename+' in '+publicationYear+'</div>';
		      str+=' <table id="partyWiseCastJqTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black;">';
			  str+='  <thead>';
			  str+='   <tr>';
			  str+='     <th>Caste</th>';
			  str+='     <th>Voters</th>';
			  str+='     <th>Party not assigned voters</th>';
			  str+='     <th>Party assigned voters</th>';
			 if(result[0].partiesList != null && result[0].partiesList.length > 0){
			   for(var p in result[0].partiesList)
			     str+='     <th>'+result[0].partiesList[p].partyName+'</th>';
			 }
			  str+='   </tr>';
			  str+='  </thead>';
			  str+='  <tbody>';
			   for(var i in result){
				  str +='   <tr>';
				  str +='		<td>'+result[i].castName+'</td>';
				  str +='		<td>'+result[i].castCount+'</td>';
				  str +='		<td>'+result[i].partyNotAssigCount+'</td>';
				  str +='		<td>'+result[i].partyCount+'</td>';
				  if(result[i].partiesList != null && result[i].partiesList.length > 0){
					for(var k in result[i].partiesList) 
					  str +=' <td>'+result[i].partiesList[k].partyCount+'</td>';
                  }
				  str+='   </tr>';
			   }
			  str+='  </tbody>';
			  str+=' </table>';
			  str+='</div>';
			  $("#partyWiseLocalCastStatsTab").html(str);
	  
				$('#partyWiseCastJqTable').dataTable({
				"aaSorting": [[ 1, "asc" ]],
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
				});
	   }
	}
function buildPartyWiseCastData(results,typeName,publicationDateId,boothId,type)
{
	
		  var result = results.voterCastInfodetails;
		  var Data = result.partyWisevoterCastInfoVOList;
		  var str =' <table id="partyWiseJqTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
          str+='     <th>Party</th>';
		 
		  str+='     <th>Voters</th>';
          str+='     <th>Male Voters</th>';
          str+='     <th>Female Voters</th>';
          str+='	 <th>Party Percentage</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';

		  for(var i in Data){
	      str +='   <tr>';
		  str +='		<td><a href="partyPageAction.action?partyId='+Data[i].partyId+' ">'+Data[i].partyName+'</td>';
		  str +='		<td>'+Data[i].totalVoters+'</td>';
          str +='		<td>'+Data[i].maleVoters+'</td>';
		  str +='		<td>'+Data[i].femaleVoters+'</td>';
		  str +='		<td>'+Data[i].votesPercent+'</td>';
          str+='   </tr>';
	   }
          str+='  </tbody>';
          str+=' </table>';
	  
	  $("#partyWiselocalcastDiv").html(str);
	  
	  	$('#partyWiseJqTable').dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null
     
	  
    ] 
		});
}

function buildCastPiechart(myResults,jsObj)
{

	
	$('#localCastChatDiv').html('');
	var results = myResults.voterCastInfodetails;
	if(results.castCategoryWiseVotersList.length == 0)
	{
		$("#localCastDetailsHeadingDiv").html('').css("background","#FFF");
		$(".castDetailsMainDiv").css("border","none")
		return;
	}
	$(".castDetailsMainDiv").css("border","1px solid #CCC")
	var data = new google.visualization.DataTable();
	data.addColumn('string','cast');
	data.addColumn('number','percentage');
	data.addRows(results.castCategoryWiseVotersList.length);

	for(var j=0; j<results.castCategoryWiseVotersList.length; j++)
	{
		data.setValue(j,0,results.castCategoryWiseVotersList[j].name);
		data.setValue(j,1,results.castCategoryWiseVotersList[j].id);
	} 

	var chart = new google.visualization.PieChart(document.getElementById('localCastChatDiv'));
		chart.draw(data, {width: 400, height: 230,legend:'right',legendTextStyle:{fontSize:12}, title:'Cast category wise voters details chart',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:13}
	});
}
function buildPartyWisePiechart(myResults,jsObj)
{
	$("#partyWiseDetailsHeadingDiv").html("Party Wise Voters Details").css("background","#06ABEA");
	
	var partyWise_header=document.getElementById('partyWise_header');
	var results = myResults.voterCastInfodetails;
	var voters= '';
	var str = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;">';
	
	str += '<span>Party Assigned Voters : '+results.partyWiseAssignedVoters+'</span>';
	str += '<span style="padding-left:40px;">Party Not Assigned Voters : '+results.partyWiseNotAssignedVoters+'</span>';
	str += '<br><br>';
	for(var i=0;i<results.partyWisevoterCastInfoVOList.length;i++)
		voters +='<p>'+results.partyWisevoterCastInfoVOList[i].partyName+' Voters : '+results.partyWisevoterCastInfoVOList[i].totalVoters+'</p>';
		voters +='</div>';
	$('#partyWiseDetailsDiv').html(voters);
	partyWise_header.innerHTML = str;

	
	
	var data = new google.visualization.DataTable();
	data.addColumn('string','party');
	data.addColumn('number','percentage');
	data.addRows(results.partyWisevoterCastInfoVOList.length);

	for(var j=0; j<results.partyWisevoterCastInfoVOList.length; j++)
	{
		data.setValue(j,0,results.partyWisevoterCastInfoVOList[j].partyName);
		data.setValue(j,1,results.partyWisevoterCastInfoVOList[j].totalVoters);
	} 

	var chart = new google.visualization.PieChart(document.getElementById('partyWiseChatDiv'));
		chart.draw(data, {width: 400, height: 230,legend:'right',legendTextStyle:{fontSize:12}, title:'Party wise voters details chart',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:13}
	});
}
   function buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId,type)
	{
	
	$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
	 var castArray = constMgmtMainObj.castStatsArray;
	$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");
         var str =' <table id="localCastStatsJqTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
          str+='     <th>Caste</th>';
		  str+='	 <th>Caste category</th>';
		  str+='     <th>Voters</th>';
          str+='     <th>Male Voters</th>';
          str+='     <th>Female Voters</th>';
          str+='	 <th>Caste Percentage</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	   for(var i in castArray){
	      str +='   <tr>';
		  //str +='		<td>'+castArray[i].caste+'</td>';
		  if(type =='booth')
		   {	  
		  str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+boothId+','+publicationDateId+',\''+castArray[i].caste+'\',\'booth\',\''+typeName+'\',\''+castArray[i].casteStateId+'\',\''+castArray[i].casteCategory+'\')">'+castArray[i].caste+'</a></td>';
		   }
		   else if(type =='panchayat')
		   {
			   str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+boothId+','+publicationDateId+',\''+castArray[i].caste+'\',\'panchayat\',\''+typeName+'\',\''+castArray[i].casteStateId+'\',\''+castArray[i].casteCategory+'\')">'+castArray[i].caste+'</a></td>';
		   }
		   else
		  str +='		<td>'+castArray[i].caste+'</td>';
		  str+='        <td>'+castArray[i].casteCategory+'</td>';
          str +='		<td>'+castArray[i].castePopulation+'</td>';
          str +='		<td>'+castArray[i].malePopulation+'</td>';
		  str +='		<td>'+castArray[i].femalePopulation+'</td>';
		  str +='		<td>'+castArray[i].castePercentage+'</td>';
          str+='   </tr>';
	   }
          str+='  </tbody>';
          str+=' </table>';
	  
	  $("#localCastStatsTabContent_body").html(str);
	  
	  	$('#localCastStatsJqTable').dataTable({
		"aaSorting": [[ 1, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null
     
	  
    ] 
		});

/*
	YAHOO.widget.DataTable.casteLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var caste = oData;
		
		var caste = oRecord.getData("caste");

		elLiner.innerHTML ='<a onclick="getVotersInACaste('+boothId+','+publicationDateId+',\''+caste+'\')">'+caste+'</a>';
	};

	var localCastStatsColumnDefs = [ 
		    	            
		    	            {key:"caste", label: "Caste", sortable: true,formatter:YAHOO.widget.DataTable.casteLink},
							{key:"castePopulation", label: "Caste Population", formatter:"number", sortable: true},
		    				{key:"malePopulation", label: "Male Population", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"femalePopulation", label: "Female Population", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"castePercentage", label: "Caste Percentage", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}	
		    					    			    				
		    	        ]; 
	var localCastStatsDataSource = new YAHOO.util.DataSource(constMgmtMainObj.castStatsArray); 
		localCastStatsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		localCastStatsDataSource.responseSchema = { 
            fields: ["caste",{key:"castePopulation", parser:"number"},{key:"malePopulation", parser:"number"},{key:"femalePopulation", parser:"number"},{key:"castePercentage", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        };
		
    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};
		
	var localCastStatsDataTable =  new YAHOO.widget.DataTable("localCastStatsTabContent_body", localCastStatsColumnDefs,localCastStatsDataSource,myConfigs);

		return {
				oDS: localCastStatsDataSource,
				oDT: localCastStatsDataTable
			};*/
	}

	function buildLocalCastStatisticsDataTableForAssembly(typeName,publicationDateId,boothId)
	{
	
	$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
	
	var localCastStatsColumnDefs = [ 
		    	            
		    	            {key:"caste", label: "Caste", sortable: true},
							{key:"castePopulation", label: "Caste Voters", formatter:"number", sortable: true},
		    				{key:"malePopulation", label: "Male Voters", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"femalePopulation", label: "Female Voters", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"castePercentage", label: "Caste Percentage", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}	
		    					    			    				
		    	        ]; 

		var localCastStatsDataSource = new YAHOO.util.DataSource(constMgmtMainObj.castStatsArray); 
		localCastStatsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		localCastStatsDataSource.responseSchema = { 
            fields: ["caste",{key:"castePopulation", parser:"number"},{key:"malePopulation", parser:"number"},{key:"femalePopulation", parser:"number"},{key:"castePercentage", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        };
		
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10
			    }) 
				};
		
		var localCastStatsDataTable =  new YAHOO.widget.DataTable("localCastStatsTabContent_body", localCastStatsColumnDefs,localCastStatsDataSource,myConfigs);

		
		return {
				oDS: localCastStatsDataSource,
				oDT: localCastStatsDataTable
			};  

		}
  function  buildFamilyMembers(result,publicationDateId,type){
	 impFamiliesEditArray = new Array();
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
    var name = "";
     if(type == "panchayat"){
	    name = $("#panchayatField option:selected").text();
	 }else{
	  type = "";
	   name = $("#pollingStationField option:selected").text();
	 }
      var str ='<div id="impFamPancBothDtlstitle">Voters Family details in '+name+' '+type+' in '+publicationYear+'</div>';
	      str+=' <div><b style="font-size:14px;">Hint: Please select atmost 5 families to edit</b></div>';
		  str+=' <div id="errorMessageDiv" style="color:red"></div>';
          str+=' <div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:15px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>';
		  str+=' <table id="impfamilydatatable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
		  str+='     <th>Select</th>';
         // str+='     <th>SNo</th>';
		  str+='     <th>Booth</th>';
          str+='     <th>House No</th>';
          str+='     <th>Members In Family</th>';
		  str +='	 <th>Caste</th>';
		  str+='	 <th class="widthStyle">Eldest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='     <th class="widthStyle">Youngest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	 for(var i in result){
	   var sno = parseInt(i)+1;
	      str +='   <tr>';
		  str +='		<td><input class="impFamilMulSel" id="impFamilSel'+sno+'" type="checkbox" onclick="populate(this.id,'+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\');"/></td>';
         // str +='		<td>'+sno+'</td>';
		  str +='		<td>'+result[i].boothName+'</td>';
          str +='		<td><a href="javascript:{}" title="Click here to view and edit members in family" onclick="getVotersInAFamily('+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\')">'+result[i].houseNo+'</a></td>';
          str +='		<td>'+result[i].numberOfPeople+'</td>';

		  str +='       <td>'+result[i].cast+'</td>';
          str +='		<td class="widthStyle">'+result[i].elder+'</td>';
		  str +='		<td>'+result[i].elderGender+'</td>';
		  str +='		<td>'+result[i].elderAge+'</td>';
          str +='		<td class="widthStyle">'+result[i].younger+'</td>';
		  str +='		<td>'+result[i].youngerGender+'</td>';
		  str +='		<td>'+result[i].youngerAge+'</td>';
          str+='   </tr>';
	 }
          str+='  </tbody>';
          str+=' </table>';
		  str+=' <div style="clear:both;"><b style="font-size:14px;">Hint: Please select atmost 5 families to edit</b></div>';
		  str+=' <div id="errorMessageDiv1" style="color:red"></div>';
	      str+=' <div style="clear:both;"><input type="button" style="margin-top:16px;margin-left:20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:-17px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:-17px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv1" style="display:none;margin-top: 0px;"src="./images/icons/search.gif"></img></div>';
	  $("#impFamPancBothDtls").html(str);
	  
	  	$('#impfamilydatatable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null,null,null,null,null,null
     
	  
    ] 
		});
  }
  
function clearAllCheckBoxes()
{
	impFamiliesEditArray = new Array();
	$('.impFamilMulSel').each(function(){
		$(this).attr("checked",false);
	});
	
}
function buildTableForImpFamilesMandal(impFamilesData,name,type)
{
	
  var impFamiList = new Array();
  for(var i in impFamilesData){
     var data={};
	 
	 data["name"] = impFamilesData[i].name;  
	 data["below3"] = impFamilesData[i].below3;
	 data["below3perc"] = impFamilesData[i].below3perc;
	 data["betwn4to6"] = impFamilesData[i].betwn4to6;
	 data["betwn4to6perc"] = impFamilesData[i].betwn4to6perc;
	 data["betwn7to10"] = impFamilesData[i].betwn7to10;
	 data["betwn7to10perc"] = impFamilesData[i].betwn7to10perc;
	 data["above10"] = impFamilesData[i].above10;
	 data["above10perc"] = impFamilesData[i].above10perc;
	 data["totalVoters"] =  impFamilesData[i].totalVoters;
	 data["totalFemaleVoters"] = impFamilesData[i].totalFemaleVoters;
	 data["totalMaleVoters"] = impFamilesData[i].totalMaleVoters;
	 impFamiList.push(data);
  }
  
  $("#impFamilesBasicSubDetailsTitle").html(impFamilesData[0].type+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
  var impFamilesColumnDefs = [
    {key:"name", label: ""+impFamilesData[0].type+"", sortable: true},
	{key:"totalVoters", label:"Total",sortable: true},
	{key:"totalMaleVoters", label:"Male Voters",sortable: true},
	{key:"totalFemaleVoters", label:"Female Voters",sortable: true},
    {key:"below3", label: "<3", formatter:"number", sortable: true},
    {key:"below3perc", label: "<3 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn4to6", label: "4 to 6", formatter:"number", sortable: true},
    {key:"betwn4to6perc", label: "4 to 6%", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn7to10", label: "7 to 10", formatter:"number", sortable: true},
    {key:"betwn7to10perc", label: "7 to 10 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"above10", label: ">10", formatter:"number",sortable:true},
    {key:"above10perc", label: ">10 %", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}
  ];
var impFamilesDataSource = new YAHOO.util.DataSource(impFamiList);
impFamilesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
impFamilesDataSource.responseSchema = {
fields: [{key:"name"},{key:"below3", parser:"number"},{key:"totalVoters"},{key:"totalMaleVoters"},{key:"totalFemaleVoters"},{key:"below3perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn4to6", parser:"number"},{key:"betwn4to6perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn7to10", parser:"number"},{key:"betwn7to10perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"above10", parser:"number"},{key:"above10perc", parser:YAHOO.util.DataSourceBase.parseNumber}]
};
var myConfigs = {
};
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetails", impFamilesColumnDefs,
impFamilesDataSource, myConfigs);
return {
oDS: impFamilesDataSource,
oDT: impFamilesDataTable
};
if(type == "constituency" || type == "Mandal/Tehsil")
	{
	$("#NoteDiv").css("display","block"); 
	$("#NoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}
}

function impFamilesStaticTable(myresults,jsObj)
{
	var value=0;
	var str='';
	var type = jsObj.type;	
	str+='<div class="impFamilesMainDiv" >';
	
	$("#impFamiliesTitle").html(" "+jsObj.typename+" Family Wise Statistics in "+publicationYear+"");
	str+='</br>';
	
	str += '<div style="font-family: verdana;font-size: 13px;margin-left: 2px;font-weight:bold;"> Total Voters : '+myresults.totalVoters+' ';
	
	if(myresults.totalFamalies==null){
		str+='&nbsp;&nbsp;&nbsp; Total Families : '+value+'';
	}
	else{
	str+='&nbsp;&nbsp;&nbsp; Total Families : '+myresults.totalFamalies+'';
	}

	if(myresults.totalMaleVoters==null){
		str +='&nbsp;&nbsp;&nbsp; Total Male Voters : '+value+'';
	}
	else{
	str +='&nbsp;&nbsp;&nbsp; Total Male Voters : '+myresults.totalMaleVoters+'';
	}

	if(myresults.totalFemaleVoters==null){
		str +='&nbsp;&nbsp;&nbsp; Total Female Voters : '+value+'';
	}
	else{
	str +='&nbsp;&nbsp;&nbsp; Total Female Voters : '+myresults.totalFemaleVoters+'';
	}
	
	str +='</div>';
	str+='</br>';
	str+='<div style="font-family:verdana;">';
	str+='<table class="impTableDiv">';
	str+='<tr>';
	str+='<th>Report</th><th>Voters Below 3</th><th>Voters Between 4-6</th><th>Voters Between 7-10</th><th>Above 10 Voters</th>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>No of Familes</th>';

	if(myresults.below3 != null)
		str+='<td><b>'+myresults.below3+'</td></b>';
	else
		str+='<td><b>'+0+'</td></b>';

	if(myresults.betwn4to6 != null)
		str+='<td><b>'+myresults.betwn4to6+'</td></b>';
	else
		str+='<td><b>'+0+'</td></b>';

	if(myresults.betwn7to10 != null)
		str+='<td><b>'+myresults.betwn7to10+'</td></b>';
	else
		str+='<td><b>'+0+'</td></b>';

	if(myresults.above10 != null)
		str+='<td><b>'+myresults.above10+'</td></b>';
	else
		str+='<td><b>'+0+'</td></b>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Familes %</th>';

	if(myresults.below3perc != null)
		str+='<td><b>'+myresults.below3perc+'%</td></b>';
	if(myresults.betwn4to6perc != null)
		str+='<td><b>'+myresults.betwn4to6perc+'%</td></b>';
	if(myresults.betwn7to10perc != null)
		str+='<td><b>'+myresults.betwn7to10perc+'%</td></b>';
	if(myresults.above10perc != null)
		str+='<td><b>'+myresults.above10perc+'%</td></b>';

	str+='<tr>';
	str+='</table>';
	str+='</div>';
	$("#impFamilesBasicDetails").html(str);
	if(type == "constituency" || type == "mandal")
	{
	$("#NoteDiv").css("display","block"); 
	$("#NoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}
}

function buildImpFamilesChart(chartInfo)
{
// Create the data table.
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	var data = google.visualization.arrayToDataTable([
			  ['Task', 'Percentage'],
			  ['Families Below 3 Voters',  chartInfo.below3perc],
			  ['Families Between 4-6 Voters', chartInfo.betwn4to6perc],
			  ['Families Between 7-10 Voters',  chartInfo.betwn7to10perc],
			  ['Families Above 10 Voters', chartInfo.above10perc]
			]);

	// Set chart options
	var title = " Family wise Voters details chart of "+chartInfo.name+" "+chartInfo.type+" in "+publicationYear+"";
	var options = {'title':title,
	'width':800,
	'height':280};
	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoSubChartDiv'));
	chart.draw(data, options);
}

function getVoterDetailsForConstituency(){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	$("#votersBasicInfoSubDiv").html("");
	$("#votersByLocationTabContentDiv_body").html("");
	var constituencyId = $('#constituencyList').val();
	var publicationId = $('#publicationDateList').val();
	var name = $('#constituencyList option:selected').text() +' Constituency';
	var str='<font color="red">';
	var errorDivEle = document.getElementById('AlertMsg');
	var flag = true;
	
	 if(constituencyId == null || constituencyId == "0")
	{
		str +='Please Select Constituency';
		flag=false;
	}
	else if(publicationId == null || publicationId == "0")
	{
		str +='<br>Please Select Publication Date';
		flag=false;
	}
	errorDivEle.innerHTML = str;
	if(flag)
	{
		errorDivEle.innerHTML='';
		var jsObj=
				{					
					constituencyId:constituencyId,
					publicationDateId:publicationId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
					name:name,
				    type:"constituency",
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;						
		
		callAjaxorVoterDetails(jsObj,url);
	}
}

function getVoterDetailsForMandal(){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	$("#votersBasicInfoSubDiv").html("");
	$("#votersBasicInfoSubChartDiv").html("");
	var mandalId = $('#mandalField').val();
	var name = $('#mandalField option:selected').text();
	var publicationId = $('#publicationDateList').val();
	var str='<font color="red">';
	var errorDivEle = document.getElementById('AlertMsg');
	var flag = true;
	
	if(mandalId == null || mandalId == "0")
	{
	str +='Please Select Mandal';
	flag=false;
	}
	else if(publicationId == null || publicationId == "0")
	{
	str +='<br>Please Select Publication Date';
	flag=false;
	}
	errorDivEle.innerHTML=str;
	var startNumber = mandalId.substring(0,1);
	mandalId = mandalId.substring(1);
	if(flag)
	{
		errorDivEle.innerHTML='';
        if(startNumber == "2"){

			var jsObj=
					{
						constituencyId:'0',
						publicationDateId:publicationId,
						mandalId:mandalId,
						boothId:'0',
						panchayatId:'0',
						name:name,
						type:"mandal"
						
					};
		}else if(startNumber == "1"){

			var jsObj=
					{
						mandalId:mandalId,
						publicationDateId:publicationId,
						name:name,
						type:"localElectionBody"
						
					};
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

		callAjaxorVoterDetails(jsObj,url);
	}
}

function getVoterDetailsForPanchayat(){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	$("#votersBasicInfoSubDiv").html("");
	$("#votersBasicInfoSubChartDiv").html("");

	var constituencyValue =$("#constituencyList").val(); 
	var mandalValue = $("#mandalField").val();
	var panchayatId = $('#panchayatField').val();
	var publicationId = $('#publicationDateList').val();
	var mandalText = $('#mandalField :selected').text();
	var validflag= mandalText.search("MUNCIPALITY");
	var name = $('#panchayatField option:selected').text()+' Panchayat';
	var str='<font color="red">';
	var errorDivEle = document.getElementById('AlertMsg');
	var flag = true;
	if(constituencyValue == 0 || constituencyValue == null)
		{
				str +='Please Select Constituency';
				flag =false;
		}
	else if(validflag != -1)
		{
		  str +='There are No Panchayats In Muncipality,Select Other Report Level to view Report';
		  flag =false;
				
		}
	else if(mandalValue == 0 || mandalValue == null)
		{
				str +='Please Select Mandal';
				flag =false;
		}
	else if(panchayatId == null || panchayatId =="0")
	{
	str +='Please Select Panchatyat';
	flag=false;
	}
	else if(publicationId == null || publicationId == "0" )
	{
	str +='<br>Please Select Publication Date';
	flag=false;
	}
	errorDivEle.innerHTML=str;
	if(flag)
	{
	errorDivEle.innerHTML='';
		var jsObj=
				{
			        constituencyId:'0',
					mandalId:'0',
					boothId:'0',
					panchayatId:panchayatId,
					publicationDateId:publicationId,
					name:name,
					type:"panchayat"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjaxorVoterDetails(jsObj,url);
	} 
}

function getVoterDetailsForBooth(){
	//$("#votersByLocationTabContentDiv_body").html("");
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	var constituencyValue =$("#constituencyList").val(); 
	var mandalValue = $("#mandalField").val();
	var boothId = $('#pollingStationField').val();
	var publicationId = $('#publicationDateList').val();
	var name = $('#pollingStationField option:selected').text();
	var mandalText = $('#mandalField :selected').text();
	var validflag= mandalText.search("MUNCIPALITY");
	var str='<font color="red">';
	var errorDivEle = document.getElementById('AlertMsg');
	var flag = true;
	if(constituencyValue == 0 || constituencyValue == null)
		{
				str +='Please Select Constituency';
				flag =false;
		}
	
	else if(mandalValue == 0 || mandalValue == null)
		{
				str +='Please Select Mandal';
				flag =false;
		}
	else if(boothId == null || boothId == "0")
	{
	str +='Please Select Booth';
	flag=false;
	}
	else if(publicationId == null || publicationId == "0")
	{
	str +='<br>Please Select Publication Date';
	flag=false;
	}
	errorDivEle.innerHTML=str;
	if(flag)
	{
	errorDivEle.innerHTML='';
	var jsObj=
				{ 
					constituencyId:'0',
					publicationDateId:publicationId,
					mandalId:'0',
					panchayatId:'0',					
					boothId:boothId,
					name:name,
					type:"booth",
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);
	}
}

function callAjaxorVoterDetails(jsObj,url){


$('#ajaxImageDiv').css('display','block');

	var myResults;

		 var callback = {			
				   success : function( o ) {
					try {
							$('#ajaxImageDiv').css('display','none');

					  myResults =  YAHOO.lang.JSON.parse(o.responseText);					
							buildVoterDetailsTable(myResults,jsObj.type);
                             buildAgeWiseVoterAnalysisChart(myResults,jsObj);

							if(jsObj.type != "booth"){
								buildAgewiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
							}
						 
						
					}catch (e){   
							
						//alert("Invalid JSON result" + e);   
					}  
				   },
				   scope : this,
				   failure : function( o ) {
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);


}

function buildVoterDetailsTable(result,type){

	var noteString = '';

	if(type == "constituency")
		noteString = $('#constituencyList :selected').text()+" "+"constituency";
	else if(type == "mandal")
		noteString = $('#mandalField :selected').text()+" ";
	else if(type == "panchayat")
		noteString = $('#panchayatField :selected').text()+" "+"panchayat";
	else if(type == "localElectionBody")
		noteString = $('#mandalField :selected').text()+" "+"localElection";
	else 
		noteString = $('#pollingStationField :selected').text();

	$('#voterDetailsNote').html('<h4 style="color:green;margin-left:40px;font-family: arial;">'+noteString+" "+"voters details"+' in '+publicationYear+'</h4>');

	var str='';
	str+='<table border="1" style="margin-top:20px;text-align:center;min-width:97%;" class="gridtable">';
	str+='<tr>'
	str+='<th rowspan="2">Age Range</th>';
	str+='<th colspan="2">Total Voters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">Female</th>';
	//str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>Total Voters</th>';
	str+='<th>Total Petcentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	//str+='<th>Voters</th>';
	//str+='<th>Percentage</th>';
	str+='</tr>';

	for(var i in result.votersDetailsVO){

	str+='<tr>';
	str+='<td>'+result.votersDetailsVO[i].ageRange+'</td>';
	str+='<td>'+result.votersDetailsVO[i].totalVoters+'</td>';

	if(result.votersDetailsVO[i].totalVotersPercent != null)
		 str+='<td>'+result.votersDetailsVO[i].totalVotersPercent.toFixed(2)+'</td>';	 
	else
		str+='<td>0.00</td>';
	

	str+='<td>'+result.votersDetailsVO[i].totalMaleVoters+'</td>';
    
	if(result.votersDetailsVO[i].totalMaleVotersPercent != null)
		str+='<td>'+result.votersDetailsVO[i].totalMaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';

	str+='<td>'+result.votersDetailsVO[i].totalFemaleVoters+'</td>';

	if(result.votersDetailsVO[i].totalFemaleVotersPercent != null)
	  str+='<td>'+result.votersDetailsVO[i].totalFemaleVotersPercent.toFixed(2)+'</td>';
	else
		str+='<td>0.00</td>';
	//str+='<td>'+result.votersDetailsVO[i].totalUnknownVoters+'</td>';

	//if(result.votersDetailsVO[i].totalUnknownVotersPercent != null)
	 //  str+='<td>'+result.votersDetailsVO[i].totalUnknownVotersPercent.toFixed(2)+'</td>';
	//else
	//	str+='<td>0.00</td>';
	
	str+='</tr>';

	}

	str+='</table>';

	$('#tableDiv').html(str);


}

function buildAgewiseDetails(results , obj){

   var type = obj.type;
   var innerResults;
   var noteString;

	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
			noteString = "Panchayat wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
	  	noteString = "Booth wise voters age details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voter age details of "+obj.name+" in "+publicationYear;
	}


	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgewiseDetailsNote').html('<h4 style="color:green;margin-left:45px;font-family: arial;">'+noteString+'</h4>');

	var str='';
	str+='<table border="1" style="margin-top:20px;text-align:center;min-width:97%;" class="gridtable">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Booth No</th>';
	str+='<th  rowspan="2">Total Voters</th>';
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';
	str+='<tr>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='</tr>';

for(var i=0;i<innerResults.length;i++){

	str+='<tr>';

	if(type == "constituency")
	 str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	 str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	 str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';

	str+='<td>'+innerResults[i].totalVoters+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].votersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].votersPercentForAbove60+'</td>';
	str+='</tr>';

}
str+='</table>';


$('#agewiseDetails').html(str);

}

function buildAgeAndGenderWiseDetails(results , obj){
    var type = obj.type;
	
	var innerResults;
	var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters Age and gender details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:green;margin-left:45px;font-family: arial;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="margin-top:20px;text-align:center;min-width:97%;" class="gridtable">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Booth No</th>';
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';

for(var i=0;i<innerResults.length;i++){

	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotesFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor18To25+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor26To35+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor36To45+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor46To60+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersForAbove60+'</td>';

	str+='</tr>';
}
   str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
}

/*function buildAgeAndGenderWiseDetails(results , type){

	var innerResults;
	var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voter details:Age and gender wise";

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat wise voter details:Age and gender wise";
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth wise voter details:Age and gender wise";
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voter details:Age and gender wise";
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:green;margin-left:45px;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="margin-top:20px;text-align:center;" class="gridtable">';

	str+='<tr>';
	str+='<th rowspan="2">Mandal Name</th>';
	str+='<th colspan="2">18-25</th>';
	str+='<th colspan="2">26-35</th>';
	str+='<th colspan="2">36-45</th>';
	str+='<th colspan="2">46-60</th>';
	str+='<th colspan="2">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';

for(var i=0;i<innerResults.length;i++){

	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotesFor18To25+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor18To25+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor26To35+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor36To45+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersFor46To60+'</td>';

	str+='<td>'+innerResults[i].totalMaleVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].totalFemaleVotersForAbove60+'</td>';

	str+='</tr>';
}
   str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
}*/

function buildAgeAndGenderWiseDetailsForPercent(results , obj){
     var type = obj.type;
	var innerResults;
	var noteString;


	if(type == "constituency"){
		innerResults = results.mandalsVotersDetails;
		noteString = "Mandal wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;

	}
	else if(type == "mandal"){
		innerResults = results.panchayatVotersDetails;
		noteString = "Panchayat wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "panchayat"){
		innerResults = results.boothVotersDetails;
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNoteInPercent').html('<h4 style="color:green;margin-left:45px;font-family: arial;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="margin-top:20px;text-align:center;min-width:97%;" class="gridtable">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Booth No</th>';
	str+='<th colspan="3">18-25</th>';
	str+='<th colspan="3">26-35</th>';
	str+='<th colspan="3">36-45</th>';
	str+='<th colspan="3">46-60</th>';
	str+='<th colspan="3">60-Above</th>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='<th>Total</th>';
	str+='<th>Male</th>';
	str+='<th>Female</th>';
	str+='</tr>';

for(var i=0;i<innerResults.length;i++){

	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor18To25+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor18To25+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor18To25+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor26To35+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor26To35+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor26To35+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor36To45+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor36To45+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor36To45+'</td>';

    str+='<td>'+innerResults[i].totalVotersFor46To60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentFor46To60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentFor46To60+'</td>';

    str+='<td>'+innerResults[i].totalVotersForAbove60+'</td>';
	str+='<td>'+innerResults[i].maleVotersPercentForAbove60+'</td>';
	str+='<td>'+innerResults[i].femaleVotersPercentForAbove60+'</td>';

	str+='</tr>';
}
   str+='</table>';

$('#voterAgeAngGenderwiseDetailsInPercent').html(str);
if(type == "constituency" || type == "mandal")
	{
$("#AgeWiseNoteDiv").css("display","block"); 
$("#AgeWiseNoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}
}


function buildAgeWiseVoterAnalysisChart(chartInfo,jsObj){

$("#AgeWisetitle").html("Age Wise Voters Information Of "+jsObj.name+" in "+publicationYear+" ");
// Create the data table.
var data = google.visualization.arrayToDataTable([
['Task', 'Percentage'],
[chartInfo.votersDetailsVO[0].ageRange, chartInfo.votersDetailsVO[0].totalVotersPercent],
[chartInfo.votersDetailsVO[1].ageRange, chartInfo.votersDetailsVO[1].totalVotersPercent],
[chartInfo.votersDetailsVO[2].ageRange, chartInfo.votersDetailsVO[2].totalVotersPercent],
[chartInfo.votersDetailsVO[3].ageRange, chartInfo.votersDetailsVO[3].totalVotersPercent],
[chartInfo.votersDetailsVO[4].ageRange, chartInfo.votersDetailsVO[4].totalVotersPercent]
]);


// Set chart options
var title = " Age wise detail chart of "+jsObj.name+" in "+publicationYear+"";
var options = {'title':title,
'width':700,
'height':300};
// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('ageWiseVotersBasicInfoSubChartDiv'));
chart.draw(data, options);

}

function buildVotersBasicInfo(votersbasicinfo,to,jsObj)
{

	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	if(to == ""){
	  $("#votersBasicInfoSubChartDiv").removeAttr('style');
	  $("#votersBasicInfoSubDiv").removeAttr('style');
	}
	var str = '<div id="votersBasicInfoDivSub">';
	if(votersbasicinfo != null && votersbasicinfo.datapresent)
	{
    
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		
		str += '<div>';
		str += '<b><span>Total Voters : '+votersbasicinfo.totVoters+'</span>';
		str += '<span style="margin-left:25px;">Male Voters : '+votersbasicinfo.totalMaleVoters+'</span>';
		str += '<span style="margin-left:25px;">Female Voters : '+votersbasicinfo.totalFemaleVoters+'</span>';
				
		if(votersbasicinfo.unKnowVoters != null && votersbasicinfo.unKnowVoters != 0 && votersbasicinfo.unKnowVoters != "0")
			str += '<span>UnKnown Voters : '+votersbasicinfo.unKnowVoters+'</span>';
		
		str += '</b></div></div></br></br>';
        if(votersbasicinfo.previousElectInfoList != null && votersbasicinfo.previousElectInfoList.length >0){
		    var prevElecInfo = votersbasicinfo.previousElectInfoList;
			str += '<table class="votersPrevCountTableDiv" style="margin-bottom:5px;font-family:verdana;">';
			str += '  <tr>';
			str += '    <th rowspan="2">Year</th>';
			str += '    <th rowspan="2">Total Voters</th>';
			str += '    <th rowspan="2">Male Voters</th>';
			str += '    <th rowspan="2">Female Voters</th>';
			str += '    <th colspan="3">Voters Comparision From '+prevElecInfo[prevElecInfo.length-1].electionYear+' To '+jsObj.year+'</th>';
			str += '  </tr>';
			str += '  <tr>';
			str += '    <th>Total Voters Comparision</th>';
			str += '    <th>Male Voters Comparision</th>';
			str += '    <th>Female Voters Comparision</th>';
			str += '  </tr>';
			for(var i in prevElecInfo){
			    str += '  <tr>';
				str += '    <td><b>'+prevElecInfo[i].electionYear+'</b></td>';
				str += '    <td>'+prevElecInfo[i].totalVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalMaleVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalFemaleVoters+'</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '  </tr>';
			   
			   /* if(prevElecInfo[i].totalVotersDiff < 0){
			     var count = prevElecInfo[i].totalVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].totalVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].totalVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
			    str += '    <td>'+prevElecInfo[i].totalVotersDiff+'</td>';
				
			   if(prevElecInfo[i].maleVotersDiff < 0){
			    var count = prevElecInfo[i].maleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].maleVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].maleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+prevElecInfo[i].maleVotersDiff+'</td>';
				
			   if(prevElecInfo[i].femaleVotersDiff < 0){
			   var count = prevElecInfo[i].femaleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(prevElecInfo[i].femaleVotersDiff > 0)
			    str += '    <td>'+prevElecInfo[i].femaleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+prevElecInfo[i].femaleVotersDiff+'</td>';
				str += '  </tr>';*/
			}
			    str += '  <tr>';
				str += '    <td><b>'+jsObj.year+'</b></td>';
				str += '    <td>'+votersbasicinfo.totVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalMaleVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalFemaleVoters+'</td>';
				

				if(votersbasicinfo.totalVotersDiff < 0){
			     var count = votersbasicinfo.totalVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(votersbasicinfo.totalVotersDiff > 0)
			    str += '    <td>'+votersbasicinfo.totalVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
			    str += '    <td>'+votersbasicinfo.totalVotersDiff+'</td>';
				
			   if(votersbasicinfo.maleVotersDiff < 0){
			    var count = votersbasicinfo.maleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(votersbasicinfo.maleVotersDiff > 0)
			    str += '    <td>'+votersbasicinfo.maleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+votersbasicinfo.maleVotersDiff+'</td>';
				
			   if(votersbasicinfo.femaleVotersDiff < 0){
			   var count = votersbasicinfo.femaleVotersDiff+"";
				str += '    <td>'+count.slice(1)+' <img class="imageSize" src="images/downarrow.png" /></td>';
			   }else if(votersbasicinfo.femaleVotersDiff > 0)
			    str += '    <td>'+votersbasicinfo.femaleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
			   else
				str += '    <td>'+votersbasicinfo.femaleVotersDiff+'</td>';
				/*str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';*/

				str += '  </tr>';
			str += '</table>';
			if(jsObj.type == "panchayat" && votersbasicinfo.panchayatInfoPresent){
			  str +='<div style="font-family:arial;margin-top:10px;margin-bottom:10px;font-size: 13px;">';
			  str +=' <div><b>Total Booths in '+votersbasicinfo.electionYear+' : '+votersbasicinfo.prevBoothsCount+'('+votersbasicinfo.prevBooths+')</b></div>';
			  str +=' <div><b>Total Booths in '+jsObj.year+' : '+votersbasicinfo.presentBoothsCount+'('+votersbasicinfo.presentBooths+')</b></div>';
			  if(votersbasicinfo.completelyRemoved != null && votersbasicinfo.completelyRemoved.length >0){
			     str +=' <div><b>Removed Booths Are :</b>';
				 for(var i in votersbasicinfo.completelyRemoved)
				  str +=votersbasicinfo.completelyRemoved[i]+' ';
				 str +=' </div>';
			  }
			  if(votersbasicinfo.newlyAdded != null && votersbasicinfo.newlyAdded.length >0){
			     str +=' <div><b>Newly Added Booths Are :</b>';
				 for(var i in votersbasicinfo.newlyAdded)
				  str +=votersbasicinfo.newlyAdded[i]+' ';
				 str +=' </div>';
			  }
			 
			  if(votersbasicinfo.otherComment != null && votersbasicinfo.otherComment.length >0){
			   for(var i in votersbasicinfo.otherComment)
			    str +=' <div><img src="images/icons/diamond.png"/> '+votersbasicinfo.otherComment[i]+'</div>';
			  }
			   str +='</div>';
			}
		}
		if(to == ""){
			$("#votersBasicInfoDiv").html(str);
			if(jsObj.type != "booth"){
			  $("#votersBasicInfoSubChartDiv").css("border","1px solid black"); 
	          $("#votersBasicInfoSubDiv").css("border","1px solid black");
			 }
		}
		else if(to == "impfamilies")
			$("#votersBasicInfoDivForImpFam").html(str);
		else if(to == "localcaststs")
			$("#votersBasicInfoDivForLclCastSts").html(str);
		else if(to == "agewise")
			$("#votersBasicInfoDivForAgeWiseDetls").html(str);
	  
		str = '';
		if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       
		buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,jsObj.typename,to);
		
		var reqDiv ='';
        if(to == "")
			reqDiv = 'votersBasicInfoSubDiv' ;
	    else if(to == "impfamilies")
			reqDiv = 'votersBasicInfoSubDivForImpFam';
	    else if(to == "localcaststs")
			reqDiv = 'votersBasicInfoSubDivForLclCastSts';
	    else if(to == "agewise")
			reqDiv = 'votersBasicInfoSubDivForAgeWiseDetls';
		 
		var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label: votersbasicinfo.votersInfoForMandalVOList[0].type, sortable: true},
		    	           	{key:"totalMaleVoters", label: "Male Voters", sortable: true},
							
							{key:"totalFemaleVoters", label: "Female Voters", sortable: true},
		    				{key:"totVoters", label: "Total Voters",sortable:true},
							{key:"percent", label: votersbasicinfo.votersInfoForMandalVOList[0].type+" % Share", sortable: true}
		    	        ]; 
		var myConfigs = {};
		var myDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "name","totalMaleVoters","totalFemaleVoters","totVoters","percent"]
					};

		var impFamliesResultDataSource = new YAHOO.widget.DataTable(reqDiv, votersResultColumnDefs,myDataSource, myConfigs);

		}
		//$('#votersByLocationTabContentDiv_body').css("border","1px solid black");
	}
	
	else
	{
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		
		if(to == "")
			$("#votersBasicInfoDiv").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
		else if(to == "impfamilies")
			$("#votersBasicInfoDivForImpFam").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
		else if(to == "localcaststs")
			$("#votersBasicInfoDivForLclCastSts").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
		else if(to == "agewise")
			$("#votersBasicInfoDivForAgeWiseDetls").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");	 
	}
}

function buildVotersChart(chartInfo,reqTitle,to)
{

 // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'type');
        data.addColumn('number', 'value');
		data.addRows(chartInfo.length);

		for(var i = 0 ; i< chartInfo.length ; i++){
		var name = chartInfo[i].name;
		var val = parseFloat(chartInfo[i].percent);
		  data.setValue(i,0,name);
		  data.setValue(i,1,val);
		}
        
        // Set chart options
		var title = chartInfo[0].type+' wise Voters % Share in '+reqTitle; 
        var options = {'title':title,
                       'width':800,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
		var reqDiv = '';
		if(to == "")
	     reqDiv = 'votersBasicInfoSubChartDiv' ;
	    else if(to == "impfamilies")
	     reqDiv = 'votersBasicInfoSubChartDivForImpFam';
	    else if(to == "localcaststs")
	     reqDiv = 'votersBasicInfoSubChartDivForLclCastSts';
	    else if(to == "agewise")
	     reqDiv = 'votersBasicInfoSubChartDivForAgeWiseDetls';
		 
        var chart = new google.visualization.PieChart(document.getElementById(reqDiv));
        chart.draw(data, options);
}

function impFamilesVariableDescription()
{
 $('#descriptionDiv').html('');
  var div = $('<div class="descriptionInnerDiv"></div>');
  div.append('<span> <b>3 -</b> Families Below 3 Voters</span>');
  div.append('<span> <b>3% -</b> Families Below 3% Voters</span>');
  div.append('<span> <b>4 to 6 -</b> Families Between 4 to 6 Voters</span>');
  div.append('<span> <b>4 to 6 % -</b> Families Between 4-6 % Voters</span>');
  div.append('<span> <b>7 to 10 -</b> Families Between 7 to 10 Voters</span>');
  div.append('<span> <b>7 to 10 % -</b> Families Between 7-10 % Voters</span>');
  div.append('<span> <b>10 - </b> Families Above 10 Voters</span>');
  div.append('<span> <b>10% -</b> Families Above 10% Voters</span>');
  $("#descriptionDiv").append(div).css("display","block");

}

function selectAll(id){
	 $('.'+id).each(function() {
            $(this).attr('checked','checked');
        });
}

function deSelectAll(id){
    $('.'+id).each(function() {
     $(this).removeAttr('checked');
	});
}
function openNewSearchWindow(){
	var urlStr="votersSearchAction.action";
	var updateBrowser = window.open(urlStr,"votersSearch","scrollbars=yes,height=700,width=980,left=10,top=0");	
	updateBrowser.focus();
}

function getPreviousElectionVotingTrends()
	{
	
		var publicationDateId = $("#publicationDateList").val();
		var level = $("#reportLevel").val();
		var constituencyId = $("#constituencyList").val(); 
		var type = '';
		var id='';

		if(level == 1)
		{
			 id = $("#constituencyList").val();
			type = "Constituency";
			name = $("#constituencyList option:selected").text()+" "+"Constituency.";
		}
		if(level == 2)
		{
			 id = $("#mandalField").val();
			type = "Mandal";
			name = $("#mandalField option:selected").text()+" ";
		}
		if(level == 3)
		{
			id = $("#panchayatField").val();
			type = "panchayat";
			name = $("#panchayatField option:selected").text()+" "+"Panchayat";
		}
		if(level == 4)
		{
			$("#previousEleVotingTrendsDiv").html('');
			return;
		}

		if(id == 0 || id == null || id == '')
			return false;
		else if(publicationDateId == 0 || publicationDateId == '' || publicationDateId == null)
			return false;

		var jsObj=
		{
			id                :id,
			publicationDateId :publicationDateId,
			constituencyId    :constituencyId,
			name              :name,
			type              :type,
			task:"getPreviousEleVotingTrends"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPreviousEleVotingTrendsAction.action?"+rparam;	
		callAjax(jsObj,url);

	}

function showPreviousEleVotingTrends(results,jsObj)
{
	 var flag = false;
	 $("#previousEleVotingTrendsDiv").html('');
      for(var i in results)
		 if(results[i].partyVotesEarnedVOs != null && results[i].partyVotesEarnedVOs .length > 0)
		   flag = true;

		if(flag)
		{
		  var str = '';
			$("#previousEleVotingTrendsDiv").append('<div style="margin-bottom: 20px;"><span id="prevVotTrendHeadingSpan">Previous Election Voting Trends in '+jsObj.name+' </span></div>');
				str +='<table style="width:95%;font-family: arial;">';
				str +='<tr>';
				str +='<th>Election Type</th>';
				str +='<th><b>Year</b></th>';
				str +='<th>Total Voters</th>';
				  str +='<th>Votes Polled</th>';
			   for(var i in results[0].partiesList)
			     str +='<th>'+results[0].partiesList[i]+'</th>';
			     str +='</tr>';
				
				for(var j in results)
				{
				  str +='<tr>';
				  str +='<td>'+results[j].reqType+'</td>';
				  str += '<td>'+results[j].electionYear+'</td>';
				  str +='<td>'+results[j].totalVotes+'</td>';
					str +='<td>'+results[j].polledVotes+'</td>';
					var partyVotesEarnedVOs = results[j].partyVotesEarnedVOs;
					  for(var k in partyVotesEarnedVOs)
				         str +='<td><b>'+partyVotesEarnedVOs[k].votesEarned+'</td>';
						
					     str +='</tr>';
		 			
				}
			 
			 str +='</table>';
			 $("#previousEleVotingTrendsDiv").append(str);
			 
			}
		
	}

function getCountsForConstituency()
{

$("#reportLevelheading").html("");

document.getElementById('reportLevelCountDiv').style.display = 'none';

var level =  $("#reportLevel").val();
var publicationDateId = $("#publicationDateList").val();
var typeName = '';

type = 'constituency';
id = $("#constituencyList").val();
typeName = $('#constituencyList :selected').text() + ' Constituency ';

var jsObj=
		{
			type:type,	
			id:id,
			typeName:typeName,
			publicationDateId:publicationDateId,
			task:"getCountForLevel"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCountForLevelAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function getCounts()
{

$("#reportLevelheading1").html("");
document.getElementById('reportLevelCountDiv1').style.display = 'none';
var level =  $("#reportLevel").val();
var publicationDateId = $("#publicationDateList").val();
var typeName = '';
if(level == 1){
type = 'constituency';
id = $("#constituencyList").val();
typeName = $('#constituencyList :selected').text() + ' Constituency ';
}
else if(level == 2){
type = 'mandal';
id = $("#mandalField").val();

typeName = $('#mandalField :selected').text();
}
else if(level == 3){
type = 'panchayat';
id = $("#panchayatField").val();
 typeName = $('#panchayatField :selected').text()+ ' Panchayat ';
}
else if(level == 4)
{
type = 'booth';
id = $("#pollingStationField").val();
return;
}
var jsObj=
		{
			type:type,	
			id:id,
			typeName:typeName,
			publicationDateId:publicationDateId,
			task:"getCountForLevel"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCountForLevelAction.action?"+rparam;						
		callAjax(jsObj,url);

}
function buildCountData(results,jsObj)
{


var str = '';

var result = results[0].mandalList;
var divEle = document.getElementById('reportLevelCountDiv');

/*if(jsObj.type != "constituency")
	{
	$("#reportLevelheading1").html(" "+jsObj.typeName+" Information");
	document.getElementById('reportLevelCountDiv1').style.display = 'block';
	$("#reportLevelCountDiv1").css({'border':'1px solid #d3d3d3','float':'left','margin':'6px 3px 6px 15px','background-color':'#f5f5f5','padding':'10px'});
	
	}*/


if(jsObj.type == "constituency")
{
$("#reportLevelheading").html(" "+jsObj.typeName+" Information");
document.getElementById('reportLevelCountDiv').style.display = 'block';
$("#reportLevelCountDiv").css({'border':'1px solid #d3d3d3','float':'left','margin':'6px 3px 6px 15px','background-color':'#f5f5f5','padding':'10px'});
	str+='<div style="margin:10px;">';
	if(results[0].totalmandals == null)
		results[0].totalmandals = 0;
	if(results[0].noOfLocalBodies == null)
	results[0].noOfLocalBodies = 0;
	if(results[0].totalPanchayats == null)
		results[0].totalPanchayats = 0;
	
	/*str+='<ul>';

	str +='<li><a class="parent" href="#"><span class="badge badge-info badge-add">'+results[0].totalmandals+'</span></a><span class="help-inline f2">Mandals</span>&nbsp;&nbsp;';
	for(var i in result)
	{
	str+='<li><a onclick="getvotersSubBasicInfo('+result[i].id+',\''+result[i].name+'\');showSubNewsDetails(\''+result[i].id+'\',5);"><span>'+result[i].name+'</span></a></li>';
	}
	str+='</ul>';*/

	str +='<span class="badge badge-info badge-add">'+results[0].totalmandals+'</span><span class="help-inline f2">Mandals</span>&nbsp;&nbsp;';
	str +='<span class="badge badge-info badge-add">'+results[0].noOfLocalBodies+'</span><span class="help-inline f2">Muncipalities</span>&nbsp;&nbsp;';
	str +='<span class="badge badge-info badge-add">'+results[0].totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>&nbsp;&nbsp;';
	str +='<span class="badge badge-info badge-add">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>&nbsp;&nbsp;';

str+='</div>';
divEle.innerHTML = str;
}

}

function getvotersSubBasicInfo(id,typename)
{
var publicationDateText =$("#publicationDateList option:selected").text();
var year=publicationDateText.substr(publicationDateText.length - 4);

 
var publicationDateId = $("#publicationDateList").val();
var jsObj=
			{
				
				type:"mandal",
				id:id,
				to:"",
				year:year,
				typename:typename,
				publicationDateId:publicationDateId,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;						
		callAjax(jsObj,url);
	}

	function buildCountData1(results,jsObj)
	{
	var value = $('#mandalField').val();
	var str = '';
	var divEle1 = document.getElementById('reportLevelCountDiv1');
	$("#reportLevelheading1").html(" "+jsObj.typeName+" Information");
	document.getElementById('reportLevelCountDiv1').style.display = 'block';
	$("#reportLevelCountDiv1").css({'border':'1px solid #d3d3d3','float':'left','margin':'6px 3px 6px 15px','background-color':'#f5f5f5','padding':'10px'});
	
		if(value.charAt(0) =="1"){
		type = "muncipality";
	}

	str +='<div>';
	if(jsObj.type == "mandal" && type != "muncipality")

	{
	str +='<span class="badge badge-info badge-add">'+results[0].totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>&nbsp;&nbsp;';
	str +='<span class="badge badge-info badge-add">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>&nbsp;&nbsp;';
	}
	if( jsObj.type == "mandal" && type == "muncipality")

	{
	str +='<span class="badge badge-info badge-add">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>&nbsp;&nbsp;';
	}
	if(jsObj.type == "panchayat")

	{
	str +='<span class="badge badge-info badge-add">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>&nbsp;&nbsp;';
	}
	str+='</div>';

	divEle1.innerHTML = str;

	}
	function getLocalitiesList(selectboxId,divId,familyId)
	{    var optionValue="";
		showNewsDetails();
		//var reportLevel = $("#reportLevel").val();
	//	var localityId=document.getElementById(selectboxId);
	  try{
	    var localityId =$("#"+selectboxId+"");
		//var name=localityId.options[localityId.selectedIndex].name;
		var value=$("#"+selectboxId+"").val();
		
		var type = "locality";
		var localityDiv=divId;
		var publicationValue = $('#publicationDateList').val();
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		var family="false";
	  if(familyId != null )
	   {
	   family="true";
	   }
	  
		 if(value == 0)
		{ 
		 if($("#singleAttributeType").is(':checked'))
		 $(this).closest("table").find(".localityFamily").val(0) ;
	    else	
		var localityId= $("#localitylocationdiv").val(0);
			//alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		var jsObj=
			{
					
				selected:value,
				divToBuild:localityDiv,
				selectBoxId:selectboxId,
				familyType: family,
				familyId: familyId,
			
				type:type,
				publicationValue : publicationValue,
				task:"getLocalities"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "voterAnalysisAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
		}catch(e){
		return;
		}
		
	}
	function populatedataTodiv(results,jobj)
	{    
	
	var str="";
	               str+="<table>";	
	               str+="<tr>";	
		      str+="    <td><b>SubLocalityName:</b></td>";		  
			  str+="    <td><select id='locality"+jobj.divToBuild+"' class='localityFamily'>";
				        for(var val in results){
						
						 str+="<option value="+results[val].id+">"+results[val].value+"</option>";
					    }
			  str+="    </select></td>";
			  
			      var family = jobj.familyType;
				 var familyId= jobj.familyId;
				 if( family === "true" ){
			     str+="    <td><a title='Apply this value to this family' href='javascript:{};' onclick='applyValueToAllVoters(\"locality"+jobj.divToBuild+"\",\"localityFamily"+familyId+"\")'><i class='icon-ok'></i></a></td>";
				 str+="    <td><a title='Apply this value to all families' href='javascript:{};' onclick='applyValueToAllVoters(\"locality"+jobj.divToBuild+"\",\"localityFamily\")'><i class='icon-ok-sign'></i></a></td>";
			  }
			  str+="</tr>"; 
              str+="</table>";
	
     $("#"+jobj.divToBuild).html(str);	
   
	}
	function openNewWindow(){
	var urlStr="votersCategoeryAction.action";
	var updateBrowser = window.open(urlStr,"editAnnouncement","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}
	
	
	