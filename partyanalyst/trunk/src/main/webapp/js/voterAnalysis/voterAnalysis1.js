var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };
var publicationYear = "";
var impFamiliesEditArray = new Array();
var totalCategories = 0;
var impFamltype;
var impFamlId;
var impFamlpublicationDateId;
var impFamltypename;
var mainreqid;
var mainpublicationId;
var maintype;
var mainname;
var parliamentConstituencyId;
var areatype='';

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
  if(impFamiliesEditArray.length > 0){
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
		  // str +='<div id="errorDiv"></div>';
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
			  str+="   <tr><td id='errorDiv' class='errorDiv'></td></tr>";
			  str+="   <tr><td><b>Voter Name:</b></td><td>"+voters[k].name+"<input type='hidden' class='familyVoterEditId' value='"+voters[k].voterId+"' /></td></tr>";
			  str+="   <tr><td><b>Guardian Name:  </b></td><td>"+voters[k].gaurdian+"</td></tr>";
			  str+="   <tr><td><b>RelationShip:</b></td><td>"+voters[k].relationship+"</td></tr>";
			  str+="   <tr><td><b>Age:</b></td><td>"+voters[k].age+"</td></tr>";
			  str+="   <tr><td><b>Gender:</b></td><td>"+voters[k].gender+"</td></tr>";
			  str+="   <tr><td><b>MobileNo:</b></td><td><input type='text' id='mobileNo' class='mobileNo' value="+voters[k].mobileNo+"></input></td></tr>";
			  

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
            $(this).val(value);
        });
}

function updateAllSelectedVoters(){
	
      var votersEditInfo = new Array();
	  var flag = true;
	  var str ='';
	  var errorDiv = 
	
     $('.familyVoterEditId').each(function() {
	        var obj={
	    
	        };
			 
			var $errorDiv = $(this).closest("table").find(".errorDiv");
			$errorDiv.text("").css("color", "#fff");
			var mobile = $.trim($(this).closest("table").find(".mobileNo").val());
			
			if(isNaN(mobile)) {
			
			$errorDiv.text("Enter valid").css("color", "red");
			
			flag =false;
			return;
			}
			
			
			else if(!(mobile.length == 0 || (mobile.length >=10 && mobile.length<=12)))
			{
			$errorDiv.text("Enter valid").css("color", "red");
			flag =false;
			return;
			}
			
			if(flag == true)
			{
			  $errorDiv.text("").css("color", "#fff");
			}
			
			  obj["voterId"] = $(this).val();
			  obj["castId"] = $(this).closest("table").find(".castallfamily").val();
			  obj["partyId"] = $(this).closest("table").find(".partyallfamily").val();
			  obj["mobileNo"] =$(this).closest("table").find(".mobileNo").val();
			  if(totalCategories > 0){
			    for(var i=0; i<totalCategories ; i++){
				  var val1= $(this).closest("table").find(".categ"+i+"main").val();
				  var val2= $(this).closest("table").find(".categ"+i+"ori").val();
				  obj["categ"+i] = val1+","+val2;
				}
			  }
			  votersEditInfo.push(obj);
			
        });
		if(flag == true)
			{
			
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
}

function showReportLevel(value)
	{
		if(value == 1)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'none';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
		}
		else if(value == 2)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'none';
			getMandalList('mandalField');

		}
		else if(value == 3)
		{
			
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'block';
			document.getElementById('pollingStationDiv').style.display = 'none';
			getPanchayatList('panchayat','panchayatField');

		}
		else if(value == 4)
		{
			document.getElementById('ConstituencyDiv').style.display = 'block';
			document.getElementById('mandalDiv').style.display = 'block';
			document.getElementById('panchayatDiv').style.display = 'none';
			document.getElementById('pollingStationDiv').style.display = 'block';
			getPanchayatList('pollingstationByPublication','pollingStationField');
		}
	}
	

function buildVotersByLocBoothDataTable(id)
{

var boothId = mainreqid;
var publicationDateId = mainpublicationId;
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

                   $("#votersOuterDiv1").dialog({ 
	                            title:'Voters Details',
	                            height: 'auto',
								width: 950,
								closeOnEscape: false,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								overlay: { opacity: 0.5, background: 'black'},
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};



}

function buildVotersByLocPanchayatDataTable(id)
{

var publicationId = mainpublicationId;
var panchaytId =  mainreqid;

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


	   $("#votersOuterDiv1").dialog({ 
					title:'Voters Details',
					height: 'auto',
					width: 950,
					show: "blind",
					modal: true,
					overlay: { opacity: 0.5, background: 'black'},
					 buttons: {
				   "Close":function() {$(this).dialog("close")}
					   }	

	   });

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}	

/*function showImportantFamiliesDiv()
	{
		var ImpDiv = document.getElementById('ImportantFamiliesDiv');
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
		
	}*/
	
	/*function showLocalCastDiv()
	{
		
		var LocalCastDiv = document.getElementById('LocalCastDiv');
	
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

	}*/

	/*function showVotersDiv()
	{
		var VotersDiv = document.getElementById('votersDiv');

		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		 getvotersBasicInfo("voters","");
		 getVotersData();
		 showNewsDetails();
		  getCounts();
	}
	function DefaultHighLight()
	{
		var VotersDiv = document.getElementById('votersDiv');

		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		
		 
	}*/

	/*function showAgeDiv()
	{
		var VotersDiv = document.getElementById('ageWiseInfoDiv');
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
	}*/
	
	function getAllTabs(id,publicationId,type){
		  //$("#votersbasicinfoForImpFam").hide();
		  //$("#votersBasicInfoDivForImpFam").html("");
		  //$("#votersBasicInfoSubChartDivForImpFam").html("");
		  //$("#votersBasicInfoSubDivForImpFam").html("");
		  //$("#impFamShowBasicInfo").val("View Basic Voter Details");

		  //$("#votersbasicinfoForLclCastSts").hide();
		  //$("#votersBasicInfoDivForLclCastSts").html("");
		  //$("#votersBasicInfoSubChartDivForLclCastSts").html("");
		  //$("#votersBasicInfoSubDivForLclCastSts").html("");
		 // $("#lclCastStsShowBasicInfo").val("View Basic Voter Details");
		  $("#localCastStatsVotersTitle").css({"background":"none repeat scroll 0 0 #FFF;"});
		  //$("#votersbasicinfoForAgeWiseDetls").hide();
			//$("#votersBasicInfoDivForAgeWiseDetls").html("");
			//$("#votersBasicInfoSubChartDivForAgeWiseDetls").html("");
			//$("#votersBasicInfoSubDivForAgeWiseDetls").html("");
			//$("#ageWiseDetlsShowBasicInfo").val("View Basic Voter Details");
			$("#votersTitle").html("");
			$("#partyWiselocalcastDiv").html("");
			$("#previousEleVotingTrendsDiv").html("");
			$("#partyWiseLocalCastStatsTab").removeAttr('style');
			  $("#localCastStatsTabContent_body").removeAttr('style');
			  $("#LocalCastDiv").css('padding-bottom','0px');
			$("#castPartyPopupShowBtn").hide();
			$("#reportLevelheading1").html("");
			$('#newsCountDiv').html('');
			$('problemsCountDiv').html('');
			$("#reportLevelCountDiv1").html("");
			$('#votersBasicInfoDiv1').html('');
			$("#votersBasicInfoSubChartDiv").html("");
			$("#votersBasicInfoSubDiv").html("");
			$("#AgeWisetitle").html("");
			$("#tableDiv1").html("");
			$("#voterDetailsNote").html("");
			$("#tableDiv").html("");
			$("#voterAgewiseDetailsNote").html("");
			$("#agewiseDetails").html("");
			$("#voterAgeAngGenderwiseDetailsNote").html("");
			$("#ageAndgenderWiseDetails").html("");
			$("#voterAgeAngGenderwiseDetailsNoteInPercent").html("");
			$("#voterAgeAngGenderwiseDetailsInPercent").html("");
			$("#impFamiliesTitle").html("");
			$("#impFamilesBasicDetails").html("");
			$("#impFamilesBasicInfoSubChartDiv").html("");
			$("#impFamilesBasicSubDetailsTitle").html("");
			$("#impFamilesBasicSubDetails").html("");
			$("#descriptionDiv").html("");
			$("#impFamPancBothDtls").html("");
			$("#localCastStatsTabContentTitle").html("");
			$("#localCastStatsTabContent_header").html("");
			$("#localCastDetailsDiv").html("");
			$("#localCastStatsTabContent_body").html("");
			$("#partyBasicInfoStatsTab").html("");
			$("#partyWiseLocalCastStatsTab").html("");
			$("#partyWiselocalcastDiv").html("");
			$("#localCastStatsTabContent_subbody").html("");
			$("#partyWise_header").html("");
			$("#partyWiseDetailsDiv").html("");
			$("#partyWiseChatDiv").html("");
			
			$("#reportLevelCountDiv1").removeAttr('style');
			$("#AgeWisetitle").html("Age Wise Voters Information Of "+mainname+" in "+publicationYear+" ");
	   $("#votersDiv4").show();  
	   if(type == "booth"){
	     $("#ageLink").hide();  
	   }else{
	     $("#ageLink").show();
	   }
	   if(type == "constituency"){
		var area='';
		  $("#votersBasicInfoBtnDiv").show();
		  $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'><span id='areaId'>"+area+"</span> Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Mandal Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");	
		  $("#votersShareBtn1").css('display','none');
	   }
	   else if(type == "booth"){
	     $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Booth Wise Age Details</a>');
	      $("#impFamiliesMoreInfoButn").attr("value","View More Details");
		  $("#votersBasicInfoBtnDiv").hide();
	   }else if(type == "mandal" && mainreqid.substring(0,1) == "2"){
	      $("#impFamiliesMoreInfoButn").attr("value","View Panchayat Wise Family Details");
	   }else if(type=="panchayat"){
	     $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Booth Wise Age Details</a>');
	     $("#impFamiliesMoreInfoButn").attr("value","View Booth Wise Family Details");
		 $("#votersBasicInfoBtnDiv").show();
		 $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
		 
	   }else if(type == "mandal" && mainreqid.substring(0,1) == "1"){
	      $("#impFamiliesMoreInfoButn").attr("value","View Ward Wise Family Details");
	   }else if(type == "localElectionBody"){
	      $("#impFamiliesMoreInfoButn").attr("value","View Ward Wise Family Details");
	   }else{
	      $("#impFamiliesMoreInfoButn").attr("value","View More Details");
	   }
	   if(type == "mandal" && mainreqid.substring(0,1) == "2"){

		$("#votersBasicInfoBtnDiv").show();
		$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Panchayat Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Panchayat Wise Voters Info of  "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");

	   $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Panchayat Wise Age Details</a>');
	     getElectionYearsAjaxAction();
		  $("#revenueVillageWiseElecResults").show();
		  $("#revenueVillageWiseElecResults").css('color','#000000');
		  $("#revenueVillageWiseElecIdTitle").html("<h4>Panchayat Wise Results In "+mainname+"</h4>");
		 }
	    else{
           $("#revenueVillageWiseElecResults").hide();
	    }
	   if(type == "panchayat" || type == "booth"){
	      $("#votersInfoMoreShowHide").show();
		 }else{
		  $("#votersInfoMoreShowHide").hide();
		 }

		 if(type == "mandal" && mainreqid.substring(0,1) == "1")
		 {
			$("#votersBasicInfoBtnDiv").show();
			$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of  "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
			
		 }
		  $("#votersHeaderDiv3").hide();
		  $("#votersMainOuterDiv3").show();
		  getPreviousVotersDetails1();
		    // getPreviousVotersDetails();
		//getvotersBasicInfo("voters",id,publicationId,type);
		// getVotersData();
		 showNewsDetails(id,publicationId,type);
		 //getProblemsByLocation(id,publicationId,type);
		 getProblemsByLocation(id,publicationId,type);
		 getCounts(id,publicationId,type);
		 getVotersCastInfo(id,publicationId,type);
         //getCastInfoForsubLevel(id,publicationId,type);
         getvotersBasicInfo("impFamilies",id,publicationId,type);
				
		// callCorrespondingAjaxCall();
		 getPreviousElectionVotingTrends(id,publicationId,type);
		 callCorrespondingAjaxCall('brief');
		 //getElectionyearsByMandalId(id,type);
	}

	
	function getPreviousVotersDetails1(){

		$("#votersBasicInfoDiv").html("");
        $("#votersBasicInfoSubChartDiv").html("");
        $("#votersBasicInfoSubDiv").html("");
	    $("#votersByLocationTabContentDiv_body").html("");
	    $("#previousEleVotingTrendsDiv").html('');
	    $("#votersByPanchayatTabContentDiv_body").html("");
		$('#votersBasicInfoDiv1').html('');

		if(maintype == "constituency"){
			constituencyId = mainreqid;
			getPrevioesVotersDetailsForConstituency();

		}
		else if(maintype == "mandal"){
			mandalId = mainreqid;
			getPrevioesVotersDetailsForMandal();
		}
		else if(maintype == "panchayat"){
			panchayatId = mainreqid;
			getPrevioesVotersDetailsForPanchayat();
		}
		else if(maintype == "booth"){
			boothId = mainreqid;
			getPrevioesVotersDetailsForBooth();
		}else if(maintype == "ward"){
			boothId = mainreqid;
			getPrevioesVotersDetailsForWard();
		}
	}

	function getPrevioesVotersDetailsForConstituency(){

		var jsObj=
					{					
						constituencyId:constituencyId,
						mandalId:0,
						boothId:0,
						panchayatId:0,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');
	}

	function getPrevioesVotersDetailsForMandal(){



		var jsObj=
					{					
						constituencyId:$('#constituencyList').val(),
						mandalId:mandalId,
						boothId:0,
						panchayatId:0,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');

	}

	function getPrevioesVotersDetailsForPanchayat(){

		var jsObj=
					{					
						constituencyId:$('#constituencyList').val(),
						mandalId:0,
						boothId:0,
						panchayatId:panchayatId,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');

	}

	function getPrevioesVotersDetailsForBooth(){

		var jsObj=
					{					
						constituencyId:$('#constituencyList').val(),
						mandalId:0,
						boothId:boothId,
						panchayatId:0,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');

	
	}
	function getPrevioesVotersDetailsForWard(){

		var jsObj=
					{					
						constituencyId:$('#constituencyList').val(),
						mandalId:0,
						boothId:boothId,
						panchayatId:0,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');

	
	}
	function getPreviousVotersDetails(){


		$("#votersBasicInfoDiv").html("");
        $("#votersBasicInfoSubChartDiv").html("");
        $("#votersBasicInfoSubDiv").html("");
	    $("#votersByLocationTabContentDiv_body").html("");
	    $("#previousEleVotingTrendsDiv").html('');
	    $("#votersByPanchayatTabContentDiv_body").html("");
		$('#votersBasicInfoDiv1').html('');

		var constituencyId = $('#constituencyList').val();
		var publicationDateId = mainpublicationId;
		var mandalId = 0;
		var boothId = 0;
		var panchayatId = 0;


		if(maintype == "constituency"){
			constituencyId = mainreqid;

		}
		else if(maintype == "mandal"){
			mandalId = mainreqid;
		}
		else if(maintype == "panchayat"){
			panchayatId = mainreqid;
		}
		else if(maintype == "booth"){
			boothId = mainreqid;
		}
        else if(maintype == "ward"){
			panchayatId = mainreqid;
		}

		var jsObj=
					{					
						constituencyId:constituencyId,
						publicationDateId:publicationDateId,
						mandalId:mandalId,
						boothId:boothId,
						panchayatId:panchayatId,
						//name:mainname,
					    type:maintype,
						task:"getVotersCountForAllElections"
					};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForAllElections.action?"+rparam;

			callAjax(jsObj,url);

	$('#basicDetailsAjax').css('display','block');

	}

	function ShowCastPartyPopupDiv(){
	
	 getCastInfoForsubLevel(mainreqid,$("#publicationDateList").val(),maintype);

	    $("#castPartyPopupDiv").dialog({
            modal: true,
            title: "<b>Voters Party Details</b>",
			width: 980,
            height: 600
           
        });
		$("#voterCasteAjaxImg").css("display","block");
	}
		
	function getMandalList(selectElmt)
	{
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
		if(flag == -1)
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				flag:flag,
				type:type,
				publicationValue : publicationValue,
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
		
	}
	}
	
	
	function getPublicationDate()
	{
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

	$('#publicationAjaxImage').css('display','block');
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
								$('#publicationAjaxImage').css('display','none');

									buildPublicationDateList(myResults);
								}
								else if(jsObj.task == "votersbasicinfo")
								{
								    if(myResults != null)
									  buildVotersBasicInfo(myResults,jsObj);
									else{
									  $("#votersBasicInfoSubChartDiv").removeAttr('style');
									  $("#votersBasicInfoSubDiv").removeAttr('style');
									}
									 
								}
							    else if(jsObj.task == "getCastInfo")
								{
								    $("#votersDiv2").show();
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
								{   $("#votersDiv2").show();
									buildCastInfoForSubLevels(myResults,jsObj);
								}								
								else if(jsObj.task == "importantFamiliesinfo")
								{
								  if(myResults != null){
								     $("#votersDiv1").show();
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
								{   $("#impFamPancBothDtlsAgxImg").hide();
								    buildFamilyMembers(myResults,jsObj.publicationDateId,jsObj.type);
								}
                                else if(jsObj.task == "getVotersInAFamily")
								{
								    buildVotersInFamily(myResults,jsObj.hno);
								}
								else if(jsObj.task =="getVotersInACaste")
								{
								    
						              $("#localCastStatsVotersPopUpDiv").dialog({
											modal: true,
											title: "<b>Voters Details</b>",
											width: 970,
											height: 600
										   
										});
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
								}
								else if(jsObj.task == "getElectionYearsForPanchayat")
								{
								    buildElectionYears(myResults);
								}
								else if(jsObj.task == "getConstituencyResults"){
									buildConstituencyResults(myResults,jsObj);
									showAllPartiesAllElectionResultsChartInVtrsPage(myResults,jsObj);
								}
								else if(jsObj.task == "getVotersCountForAllElections"){
									$('#basicDetailsAjax').css('display','none');
									hideAjaxImgDiv('ajaxImageDiv');
									buildPreviousVotersDetails(myResults,jsObj);
		
								
								}
								else if(jsObj.task == "getElectionyearsByMandalId")
								{
									$("#crossVotingEleyearAjaximg").css("display","none");
									clearOptionsListForSelectElmtId("electionYearsForCrossVoting");
									createOptionsForSelectElmtId("electionYearsForCrossVoting",myResults);
									getPartiesList();
									getParliamentConstituencyId();
									
								}
								else if(jsObj.task == "getParliamentConstituencyId")
								{
									parliamentConstituencyId = myResults;
									
								}
								else if(jsObj.task=="getParty")
								{	
									$("#crossVotingPartyAjaximg").css("display","none");
									clearOptionsListForSelectElmtId("PartySelect");
									createOptionsForSelectElmtId("PartySelect",myResults.dataList);
									
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
	
	function showAllPartiesAllElectionResultsChartInVtrsPage(myResults,jsObj)
	{
	
	var chartColumns = myResults[0].partiesList;
  
     var data = new google.visualization.DataTable();
	
	 data.addColumn('string', 'Party');
 
     var partiesArray = new Array();
     //for chart columns
	 for(var i in chartColumns)
	 {
	   var colData = chartColumns[i].name;
	  
	   data.addColumn('number', colData);

	   partiesArray.push(chartColumns[i].name);
	 }

      //for chart rows
	  for(var j in myResults)
	  {
		  
		  var array = new Array();
		  var year = myResults[j].electionYear+" "+myResults[j].electionType;
		  array.push(year);

		  for(var k in myResults[j].partyResultsVO)
		  {
			  var percentage = myResults[j].partyResultsVO[k].votesPercent;
              array.push(percentage);
		  }
		 
		  data.addRow(array);
	  }

		//var ctitle = 'All Parties Performance In Different Elections in '+jsObj.cnstncy; 
		var ctitle='';
		var chartResultDiv = document.getElementById("constituencyPageElectionImgDiv");
		$('#constituencyPageElectionImgDiv').css('display','none');
      //static colors for parties
      var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);

	  if(staticColors != null && staticColors.length > 0)
	  {
		  new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,colors:staticColors,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
	  else
	  {
          new google.visualization.LineChart(chartResultDiv).
			  draw(data, {curveType: "function",width: 623, height: 400,title:ctitle,pointSize: 4,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:40}});
	  }
}
	
	var cnstncy;
	var elecType;
	function buildConstituencyResults(results,jsObj){
	$('#constituencyResults').html('');
		cnstncy=jsObj.cnstncy;
		var conid=jsObj.conId;
		var str='';
		
		str+='<div id="cnstHeading" class="thumbnail" style="background:#f0f0f0;border-radius:0px;text-align:left;">Previous Election Results of '+cnstncy+' '+results[0].electionType+' Constituency <a id="ShowConstMenu" class="btn pull-right" style="margin-top:0px;" href="javascript:{}" >Hide<i class="icon-chevron-up"></i></a></div>';
		str+='<div id="constituencyResultsInner" style="padding-bottom:25px;">';
		str+='<table class="table table-bordered table-hover"><thead><tr class="" style="background:#E6E6E6;"><th>Election Year</th><th>Won Candidate</th><th>Majority</th><th>Lost Candidate</th></tr></thead>';
        for(var i in results){
		elecType=results[i].electionType;
		str+='<tbody><tr style="cursor:pointer;" id="constituencyElectionInfo_'+i+'" title="Click here to View '+cnstncy+' '+elecType+' Constituency results in the Year '+results[i].electionYear+'" onclick="showDetailedElectionResult(this.id,'+conid+','+results[i].electionYear+')"><td>'+results[i].electionYear+'</td><td>'+results[i].candidateResultsVO.candidateName+'-<span>['+results[i].candidateResultsVO.partyShortName+']</span></td><td>'+results[i].candidateResultsVO.votesMargin+'</td>'
		
		str+='<td>'+results[i].candidateOppositionList[0].candidateName+'-<span>['+results[i].candidateOppositionList[0].partyShortName+']</span></td></tr>';
        }        
        str+='</tbody></table>';
		str+='<span class="btn btn-info pull-right" onClick="partiesPerformancePopup()" style="margin-top:-12px;">All Parties Performance In Previous  Elections Graphically</span>'
		str+='</div>'
        $('#constituencyResults').html(str);
	}
	var showConst=false;
	
	$('#ShowConstMenu').live('click',function(){
	if(!showConst) {
			$("#constituencyResultsInner").hide();
			$(this).html('Show <i class="icon-chevron-down"></i>'); 
			showConst=true;
		}
		else {
			$("#constituencyResultsInner").css("display","block");
			$(this).html('Hide <i class="icon-chevron-up"></i>');
			showConst=false;
		}
	});
	
	function showDetailedElectionResult(id,conid,elYear)
	{
	var index = id.substring((id.indexOf('_')+1),id.length);
	
	var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+conid+"&electionType="+elecType+"&electionYear="+elYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
    browser1.focus();
	}
	
	
	function partiesPerformancePopup(){
	
		$("#constituencyPageElectionImgDiv").dialog({ stack: false,
						title:'All Parties Performance In Different Elections in '+cnstncy,
						height: 'auto',
						width: 750,
						closeOnEscape: true,
						position:[40,40],
						show: "blind",
						hide: "explode",
						display:"block",
						modal: true,
						maxWidth : 750,
						minHeight: 550,
						overlay: { opacity: 0.5, background: 'black'},
						close: function(event, ui) {
						}
		  			});
		$("#constituencyPageElectionImgDiv").dialog();
	}

	function buildElectionYears(results){
	        if(results != null && results.length > 0){
			    $("#revenueVillageWiseElecId option").remove();
				for(var i in results)
				  if(results[i].id != 0)
			        $("#revenueVillageWiseElecId").append('<option value='+results[i].id+'>'+results[i].name+'</option>');
			}else{
			    $("#revenueVillageWiseElecResults").hide();
			}
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
var fromOnChange=false;
$(document).ready(function(){
    $("#publicationDateList").change(function(){
	    if($("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0){
		//getCounts();
		   var str = $('#publicationDateList :selected').text();
		  scrollToMandals();
		   var year=str.split("-");
		   try{
		    publicationYear =year[2];
		    }catch(e){
		    }
		}else{
		    publicationYear = "";
		}
	});
	$("#publicationDateList").change(function(){
	     if($(this).val() != 0){
		    if($("#constituencyList option").length > 0 && $("#constituencyList").val() != 0){
			     mainreqid = $("#constituencyList").val() ;
				 mainpublicationId = $("#publicationDateList").val();
				 maintype = "constituency";
				 mainname = $('#constituencyList :selected').text();
				 getAllTabs(mainreqid,mainpublicationId,maintype)
			}
		 }
	});
    /*$("#impFamShowBasicInfo").click(function(){
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
	});*/
    $("#constituencyList").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 1 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
		{
	      getAllTabs();
		 
		}

	  
	});
	$("#mandalField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 2 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getAllTabs();
	});
	$("#panchayatField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 3 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	    getAllTabs();

	});
	$("#pollingStationField").live("change",function(){
	  $('.voterDetails').html('');
	  $('.noteDiv').html('');
	   if($(this).val() != 0 && $("#reportLevel").val() == 4 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getAllTabs();
	});
    $("#publicationDateList").live("change",function(){
		fromOnChange=true;
		$('#ShowMenu').trigger('click');
		//$('.voterDetails').html('');
		//$('.noteDiv').html('');
	   if($(this).val() != 0 ){

	     if($("#reportLevel").val() == 1 && $("#constituencyList option").length > 0 && $("#constituencyList").val() != 0)
		   getAllTabs();
		else if($("#constituencyList option").length > 0 && $("#constituencyList").val() != 0)
			getCountsForConstituency();
		   
		 else if($("#reportLevel").val() == 2 && $("#mandalField option").length > 0 && $("#mandalField").val() != 0 )
		  
		    getAllTabs();
		 
		 else if($("#reportLevel").val() == 3 && $("#panchayatField option").length > 0 && $("#panchayatField").val() != 0 )
		  
		   getAllTabs();
		  
		 else if($("#reportLevel").val() == 4 && $("#pollingStationField option").length > 0 && $("#pollingStationField").val() != 0 )
		  { 
		     getAllTabs();
		  }
			
		}
	});

	$('.linkClass').live('click',function(){

		$('.linkClass').removeClass('selectedLink');
		$(this).addClass('selectedLink');
	});
	
	$("#electionYearsForCrossVoting").live("change",function(){
		getParliamentConstituencyId();
		getPartiesList();
	
	});
	
});

/*function getBasicInfo(){
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
}*/

function showProcessingDialog(){
	$('#processingDialogInnerDiv').html('Your request is in process....<img alt="Processing Image" src="./images/icons/search.gif">');
	  $("#processingDialogOuterDiv").dialog({
            modal: false,
			closeOnEscape: false,
            title: "Processing Request...",
			width: 300,
            height: 100           
        });
	
}
function callCorrespondingAjaxCall(retrieveType){


	//showProcessingDialog();

	if(maintype == "constituency")
		getVoterDetailsForConstituency(retrieveType);
	else if(maintype == "mandal")
		getVoterDetailsForMandal(retrieveType);
	else if(maintype == "panchayat")
		getVoterDetailsForPanchayat(retrieveType);
	else if(maintype == "booth")
		getVoterDetailsForBooth(retrieveType);
	else if(maintype == "ward")
		getVoterDetailsForWard(retrieveType);
}

function getVotersData(){
  //var level = $("#reportLevel").val();
    if(maintype == "panchayat"){
	  //$("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocPanchayatDataTable("panchayatField");
	}else if(maintype == "booth"){
	  //$("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocBoothDataTable("pollingStationField");
	}else{
	  $("#votersByLocationTabContentDiv_body").removeAttr('style'); 
	}
}

function getVotersCastInfo(id,publicationId,type)
	{
  $("#localCastStatsTabContent_header").html("");
  $("#localCastStatsTabContentTitle").html("");
  $("#localCastStatsTabContent_body").html("");
  var typename=mainname;
  var castewiseAjaxDiv =  document.getElementById('castewiseAjaxDiv');

		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
			{
				type:type,	
				id:id,
				typename:typename,
				publicationDateId:publicationId,
				task:"getCastInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
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
	if(mainreqid == jsObj.id){

		$("#voterCasteAjaxImg").css("display","none");
		
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
		if(constMgmtMainObj.castStatssubArray == null || constMgmtMainObj.castStatssubArray.length == 0)
		  return;
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
function getvotersBasicInfo(buttonType,id,publicationId,type){
    if(buttonType == "voters"){
      $("#votersByLocationTabContentDiv_body").html("");
   	  $("#votersByPanchayatTabContentDiv_body").html("");
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
   var ajaxImageDiv =  document.getElementById('ImpFamwiseAjaxDiv');
  
    var level = $("#reportLevel").val();
	//var type = '';
	//var id = '';
	//var str ='<font color="red">';
	var flag =true;
	//var errorDivEle =document.getElementById('AlertMsg'); 
	var publicationDateId = $("#publicationDateList").val();
	//var publicationDateText =$("#publicationDateList option:selected").text();
	//var year=publicationDateText.substr(publicationDateText.length - 4);
	var typename=mainname;
	/*if(level == 1){
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
	errorDivEle.innerHTML = str;*/
	if(true)
	{
	//errorDivEle.innerHTML = '';		
	 impFamltype = type;
     impFamlId = id;
     impFamlpublicationDateId = publicationDateId;
     impFamltypename = typename;
	 
   if(buttonType == "voters"){
	  showAjaxImgDiv('ajaxImageDiv');
	var jsObj=
			{
				
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				year:publicationYear,
				typename:typename,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	if(buttonType == "impFamilies"){
	    $("#impFamiliesMoreInfoButn").show();
	    if(type == 'panchayat' || type == 'booth'){
		   
		   if(type == 'booth'){
		       $("#impFamilesBasicSubDetails").removeAttr('style');
		   }else{
			   $("#impFamilesBasicSubDetails").css("border","1px solid black");
		   }
		}else{
		   //$("#impFamiliesMoreInfoButn").hide();
		}
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

   }
	}
}

function getImpFamiliesVotersToShow(){
    $("#impFamilesAllInfoPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
   	if(impFamltype == 'panchayat' || impFamltype == 'booth' ){
	   
	 var reqtype = impFamltype;
	 if(impFamltype == 'booth'){
	    reqtype = 'pollingstation';
	 }
	 showAjaxImgDiv('ajaxImageDiv');
	 $("#impFamPancBothDtlsAgxImg").show();
	    var jsObj2=
			{
					
				type:reqtype,
				id:impFamlId,
				publicationDateId:impFamlpublicationDateId,
				typename:impFamltypename,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	}
}
function getvotersFamileyInfo(buttonType,voterBasicInfoFor)
{ 
 $('#imgDiv').show();
 $('#impfamilydatatable_wrapper').hide();



	if(impFamltype == 'panchayat' || impFamltype == 'booth' ){
	   
	 var reqtype = impFamltype;
	 if(impFamltype == 'booth'){
	    reqtype = 'pollingstation';
	 }
	 showAjaxImgDiv('ajaxImageDiv');
	    var jsObj2=
			{
					
				type:reqtype,
				id:impFamlId,
				publicationDateId:impFamlpublicationDateId,
				typename:impFamltypename,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
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
  if(mainreqid == jsObj.id)
  {
	$('#localCastDetailsHeadingDiv').html('');
	$('.localCastStatsVotersTitle').html('');
	
	var result = myresults.voterCastInfodetails;
	var castewiseAjaxDiv = document.getElementById('castewiseAjaxDiv');
	hideAjaxImgDiv('castewiseAjaxDiv');
	var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
	var totalVoters = result.totalVoters;
	var totalCasts = result.totalCasts;
	var totalMale = result.maleVoters;
	var totalFemale = result.femaleVoters;
	var voters = '';
	if(result.maleVoters > 0){
	    $("#castPartyPopupShowBtn").show();
		$("#partyBasicInfoStatsTabNewTitle").html("<h2 id='subHeading'>Party Wise Voters Details</h2>");
	    $("#LocalCastDiv").css('padding-bottom','20px');
	}
	else{
	   		$("#partyBasicInfoStatsTabNewTitle").html("").css("background","#ffffff");
	}
	var localCastStatsTabContent = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span class="" style="padding:10px;"> Total Voters : '+totalVoters+'</span>';
	localCastStatsTabContent += '<span class="">Total Casts : '+totalCasts+'</span>';
	//localCastStatsTabContent += 'Total Male Voters : '+totalMale+'&nbsp;&nbsp;&nbsp;';
	//localCastStatsTabContent += 'Total Female Voters : '+totalFemale+'<br><br>';
	localCastStatsTabContent += '<span class="">Caste Assigned Voters : '+result.maleVoters+'</span>';
	localCastStatsTabContent += '<span class="">Caste Not Assigned Voters : '+result.femaleVoters+'</span>';

	if(result.voterCastInfoVOList != null && result.voterCastInfoVOList.length > 0)
	  $('#localCastDetailsHeadingDiv').html('<h2 id="subHeading">Caste category wise voters details</h2>');
	else
     	$('#localCastDetailsHeadingDiv').html('').css("background","#ffffff");
	 voters +='<table><tr>';
	  var reqx = 0;
	  for(var i=0;i<result.castCategoryWiseVotersList.length;i++){
	    reqx = 1;
	    if(i != 0 && i % 5 == 0)
		 voters +='<tr>';
		voters +='<td style="padding-left:14px;margin:2px;clear:both;display:block;padding:10px;">'+result.castCategoryWiseVotersList[i].name+' Voters : '+result.castCategoryWiseVotersList[i].id+'</td>';
	     if(i != 0 && i%5 == 0)
		 voters +='</tr>';
	 }
		 if(reqx % 5 != 0)
		    voters+='</tr>';
		
    voters +='</table>';
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
		//$("#localCastStatsTabContent_body").html("Caste Is Not Assigned To Any Voter");
		$('.localCastStatsVotersTitle').css("backgrond","#FFF;");
		}

		//buildCastPiechart(myresults,jsObj);  
		buildPartyWisePiechart(myresults,jsObj);
		buildPartyWiseCastData(myresults,typeName,publicationDateId,boothId,type);
		buildPartyWiseCastDetailsTable(myresults,jsObj);
  }
}

	function buildPartyWiseCastDetailsTable(myresults,jsObj){
	   $("#partyWiseLocalCastStatsTab").html("");
	   if(myresults != null && myresults.voterCastInfodetails != null && myresults.voterCastInfodetails.castVOs != null && myresults.voterCastInfodetails.castVOs.length > 0){
	       var result = myresults.voterCastInfodetails.castVOs;
		   var str ='<div>';
		      str+='<div id="partyWiseLocalCastStatsTabTitle" style="width:409px;"><h2 id="subHeading" >Cast V/s Party analysis of '+jsObj.typename+' in '+publicationYear+'</h2></div>';
		      str+=' <table id="partyWiseCastJqTable" cellpadding="0" cellspacing="0" border="0" width="100%">';
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
	   $("#partyWiseLocalCastStatsTab").css("margin-top","25px;");
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
		  if(Data == null || Data.length == 0)
		    return;
		  var str =' <table id="partyWiseJqTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:2px solid black">';
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
		chart.draw(data, {width: 400, height: 230,legend:'right',legendTextStyle:{fontSize:12}, title:'Caste category wise voters details chart',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:13}
	});
}
function buildPartyWisePiechart(myResults,jsObj)
{
	$("#partyWiseDetailsHeadingDiv").html("Party Wise Voters Details").css("background","#06ABEA");
	
	var partyWise_header=document.getElementById('partyWise_header');
	var results = myResults.voterCastInfodetails;
	if(results.partyWiseAssignedVoters > 0){
	  $("#castPartyPopupShowBtn").show();
	   $("#LocalCastDiv").css('padding','20px');
	}
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
    $('#partyBasicInfoStatsTab').html(str);
	if(results == null || results.partyWisevoterCastInfoVOList == null || results.partyWisevoterCastInfoVOList.length == 0 )
	 {
	    $('.partyWiseDetailsMainDiv').removeAttr('style');
	   $('.partyWiseDetailsMainDiv').css('border','1px solid #ffffff').css('clear','both').css('border', '1px solid #ffffff;').css('display','table').css('margin-left','10px').css('width','926px');
        return;
      }
	  
       $('.partyWiseDetailsMainDiv').removeAttr('style');
	   $('.partyWiseDetailsMainDiv').css('border','1px solid #CCCCCC').css('clear','both').css('border', '1px solid #ffffff;').css('display','table').css('margin-left','10px').css('width','926px');
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
		chart.draw(data, {width: 450, height: 300,legend:'right',legendTextStyle:{fontSize:12}, title:'Party wise voters details chart',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:13}
	});
}
   function buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId,type)
	{
	 if(constMgmtMainObj.castStatsArray == null || constMgmtMainObj.castStatsArray.length == 0)
	    return;
	$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
	 var castArray = constMgmtMainObj.castStatsArray;
	$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");
         var str =' <table id="localCastStatsJqTable" cellpadding="0" cellspacing="0" border="0" width="100%">';
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
	  $("#localCastStatsTabContent_body").css("margin-bottom","35px").css("margin-top","10px");
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
  var reqtytle ="Name";
  for(var t in impFamilesData){
     if(impFamilesData[t].type != null)
	   reqtytle = impFamilesData[t].type;
  }
  $("#impFamilesBasicSubDetailsTitle").html(reqtytle+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
  var impFamilesColumnDefs = [
    {key:"name", label: ""+reqtytle+"", sortable: true},
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
	str+='<div class="impFamilesMainDiv row" >';
	
	$("#impFamiliesTitle").html(" "+jsObj.typename+" Family Wise Statistics in "+publicationYear+"");
		
	str += '<div class="span3"><ul class="FamiliyList"> <li> <div style="width:68%;float:left;">Total Voters </div> <span style="clear:left;">: '+myresults.totalVoters+'</span></li>';
		if(myresults.totalFamalies==null){
		str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str+='<li><div style="width:68%;float:left;"> Total Families </div> : <span style="clear:left;">'+myresults.totalFamalies+'</span></li> ';
	}

	if(myresults.totalMaleVoters==null){
		str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;"> '+value+'</span></li> ';
	}
	else{
	str +='<li><div style="width:68%;float:left;"> Total Male Voters </div> : <span style="clear:left;">'+myresults.totalMaleVoters+'</span> </li> ';
	}

	if(myresults.totalFemaleVoters==null){
		str +='<li><div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+value+'</span> </li> ';
	}
	else{
	str +='<li> <div style="width:68%;float:left;">Total Female Voters </div>: <span style="clear:left;">'+myresults.totalFemaleVoters+'</span> </li> ';
	}
	
	str +='</ul></div>';
	str+='<div class="span9" style="margin-top:6px;">';
	str+='<table class="table table-bordered table-hover">';
	str+='<thead class="info"><tr>';
	str+='<th>Report</th><th>Voters Below 3</th><th>Voters Between 4-6</th><th>Voters Between 7-10</th><th>Above 10 Voters</th>';
	str+='</tr></thead><tbody>';
	str+='<tr>';
	str+='<th>No of Familes</th>';

	if(myresults.below3 != null)
		str+='<td>'+myresults.below3+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn4to6 != null)
		str+='<td>'+myresults.betwn4to6+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.betwn7to10 != null)
		str+='<td>'+myresults.betwn7to10+'</td>';
	else
		str+='<td>'+0+'</td>';

	if(myresults.above10 != null)
		str+='<td>'+myresults.above10+'</td>';
	else
		str+='<td>'+0+'</td>';
	str+='</tr>';

	str+='<tr>';
	str+='<th>Familes %</th>';

	if(myresults.below3perc != null)
		str+='<td>'+myresults.below3perc+'%</td>';
	if(myresults.betwn4to6perc != null)
		str+='<td>'+myresults.betwn4to6perc+'%</td>';
	if(myresults.betwn7to10perc != null)
		str+='<td>'+myresults.betwn7to10perc+'%</td>';
	if(myresults.above10perc != null)
		str+='<td>'+myresults.above10perc+'%</td>';

	str+='<tr>';
	str+='</tbody></table>';
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
	var ImpFamwiseAjaxDiv =  document.getElementById('ImpFamwiseAjaxDiv');
	hideAjaxImgDiv('ImpFamwiseAjaxDiv');
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

function getVoterDetailsForConstituency(retrieveType){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	//$("#votersBasicInfoSubDiv").html("");
	//$("#votersByLocationTabContentDiv_body").html("");
	/*var constituencyId = $('#constituencyList').val();
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
	errorDivEle.innerHTML = str;*/
	if(true)
	{
		//errorDivEle.innerHTML='';
		var jsObj=
				{					
					constituencyId:mainreqid,
					publicationDateId:mainpublicationId,
					mandalId:'0',
					boothId:'0',
					panchayatId:'0',
					name:mainname,
					retrieveType:retrieveType,
				    type:"constituency",
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;						
		
		callAjaxorVoterDetails(jsObj,url);
	}
}

function getVoterDetailsForMandal(retrieveType){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	//$("#votersBasicInfoSubDiv").html("");
	//$("#votersBasicInfoSubChartDiv").html("");
	var mandalId = '';
	var name = mainname;
	/*var name = $('#mandalField option:selected').text();
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
	errorDivEle.innerHTML=str;*/
	var startNumber = mainreqid.substring(0,1);
	mandalId = mainreqid.substring(1);
	if(true)
	{
		//errorDivEle.innerHTML='';
        if(startNumber == "2"){

			var jsObj=
					{
						constituencyId:'0',
						publicationDateId:mainpublicationId,
						mandalId:mandalId,
						boothId:'0',
						panchayatId:'0',
						name:name,
						retrieveType:retrieveType,
						type:"mandal"
						
					};
		}else if(startNumber == "1"){

			var jsObj=
					{
					constituencyId:'0',
					mandalId:mandalId,
					publicationDateId:mainpublicationId,
					name:name,
					boothId:0,
					panchayatId:'0',
					retrieveType:retrieveType,
					type:"localElectionBody"
						
					};
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

		callAjaxorVoterDetails(jsObj,url);
	}
}

function getVoterDetailsForPanchayat(retrieveType){
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	//$("#votersBasicInfoSubDiv").html("");
	//$("#votersBasicInfoSubChartDiv").html("");
    var name = mainname;

	/*var constituencyValue =$("#constituencyList").val(); 
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
	errorDivEle.innerHTML=str;*/
	if(true)
	{
	//errorDivEle.innerHTML='';
		var jsObj=
				{
			        constituencyId:'0',
					mandalId:'0',
					boothId:'0',
					panchayatId:mainreqid,
					publicationDateId:mainpublicationId,
					name:name,
					retrieveType:retrieveType,
					type:"panchayat"
				};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getAgewiseVoterDetails.action?"+rparam;

      callAjaxorVoterDetails(jsObj,url);
	} 
}

function getVoterDetailsForWard(retrieveType){
   $("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	var name = mainname;
	var jsObj=
				{ 
					constituencyId:'0',
					publicationDateId:mainpublicationId,
					mandalId:'0',
					panchayatId:mainreqid,					
					boothId:'0',
					name:name,
					retrieveType:retrieveType,
					type:"ward",
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);
}

function getVoterDetailsForBooth(retrieveType){
	//$("#votersByLocationTabContentDiv_body").html("");
	$("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	var name = mainname;
	/*var constituencyValue =$("#constituencyList").val(); 
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
	errorDivEle.innerHTML=str;*/
	if(true)
	{
	//errorDivEle.innerHTML='';
	var jsObj=
				{ 
					constituencyId:'0',
					publicationDateId:mainpublicationId,
					mandalId:'0',
					panchayatId:'0',					
					boothId:mainreqid,
					name:name,
					retrieveType:retrieveType,
					type:"booth",
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);
	}
}

function callAjaxorVoterDetails(jsObj,url){
	$('#agewiseAjaxDiv').css('display','block');


//$('#ajaxImageDiv').css('display','block');

	var myResults;

		 var callback = {			
				   success : function( o ) {
					try {
							//$('#ajaxImageDiv').css('display','none');
                    $('#agewiseAjaxDiv').css('display','none');
					//$('#ageLink').css('display','block');

					  myResults =  YAHOO.lang.JSON.parse(o.responseText);
					  
					  if(jsObj.retrieveType == "brief"){
							buildVoterDetailsTable(myResults,jsObj.type,jsObj.retrieveType);
					  }else if(jsObj.retrieveType == "all"){

						 // buildVoterDetailsTable(myResults,jsObj.type);
                            // buildAgeWiseVoterAnalysisChart(myResults,jsObj);

							if(jsObj.type != "booth"){
								buildAgewiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
							}
					  }
						 // $("#processingDialogOuterDiv").dialog('close');
						
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

function buildVoterDetailsTable(result,type,retrieveType){

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

	$('#voterDetailsNote').html('<h4 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+" "+"voters details"+' in '+publicationYear+'</h4>');
	//$('#voterDetailsNote1').html('<h5 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+" "+"voters details"+' in '+publicationYear+'</h5>');


	var str='';
	str+='<table  class="table table-bordered table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;">';
	
	str+='<thead class="info">'
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
	str+='</thead><tbody>';
	

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

	str+='</tbody>';
	str+='</table>';

	$('#tableDiv').html(str);
	$('#tableDiv1').html(str);


	if(retrieveType == "all"){
		$('#tableDiv').css('display','block');
		$('#voterDetailsNote').css('display','block');

	//$('#ageWiseVotersDetailsOuterDiv').dialog();

      $('#ageWiseVotersDetailsOuterDiv').dialog({ 
	                            title:'Voters Details',
	                            height: 'auto',
								width: 950,
								closeOnEscape: false,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								overlay: { opacity: 0.5, background: 'black'},
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });
	}


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
		noteString = "Ward wise voter age details of "+obj.name+" in "+publicationYear;
	}
   else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise voter age details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgewiseDetailsNote').html('<h4 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+'</h4>');

	var str='';
	str+='<table border="1" style="margin-top:20px;min-width:97%;" class="table table-hover table-bordered">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
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
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='<th>Voters</th>';
	str+='<th>%</th>';
	str+='</tr>';

for(var i=0;i<innerResults.length;i++){
  if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';

	if(type == "constituency")
	 str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	 str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	 str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
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
	   	noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Ward wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
     else if(type == "ward"){
		innerResults = results.boothVotersDetails;
	   	noteString = "Booth wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="margin-top:20px;min-width:97%;" class="table table-hover table-bordered">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
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
  if(innerResults[i].totalMaleVotesFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
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
	   	noteString = "Booth wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "localElectionBody"){
		innerResults = results.boothVotersDetails;
		noteString = "Ward wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}
	else if(type == "ward"){
		innerResults = results.boothVotersDetails;
		noteString = "Booth wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNoteInPercent').html('<h4 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="margin-top:20px;min-width:97%;" class="table table-hover table-bordered">';

	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
	   str+='<th rowspan="2">Booth</th>';  
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
 if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "ward")
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
}
   str+='</table>';

$('#voterAgeAngGenderwiseDetailsInPercent').html(str);


if(obj.retrieveType == "all"){
	$('#tableDiv').css('display','block');
	$('#voterDetailsNote').css('display','block');

  // $('#ageWiseVotersDetailsOuterDiv').dialog();

      $('#ageWiseVotersDetailsOuterDiv').dialog({ 
	                            title:'Voters Details',
	                            height: 'auto',
								width: 950,
								closeOnEscape: false,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								overlay: { opacity: 0.5, background: 'black'},
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });
}




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

function buildVotersBasicInfo(votersbasicinfo,jsObj)
{ 
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	$("#votersInfoAjaxImg").css("display","none");
	  //$("#votersBasicInfoSubChartDiv").removeAttr('style');
	  //$("#votersBasicInfoSubDiv").removeAttr('style');

	var str = '<div id="votersBasicInfoDivSub">';
	var title = " Voters Basic Information of "+jsObj.typename+" in "+jsObj.year+"";
	if(votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0)
	{
		if(jsObj.type == "constituency")
			title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" Constituency";
		else
		 title = ""+votersbasicinfo.votersInfoForMandalVOList[0].type+" wise Voters Information in "+jsObj.typename+" ";
	}

	$('#votersBasicInfoMainDiv').dialog({ 
					title:title,
					height: 'auto',
					width: 950,
					show: "blind",
					modal: true,
					overlay: { opacity: 0.5, background: 'black'},
					 buttons: {
				   "Close":function() {$(this).dialog("close")}
					   }	

	   });

	 if(votersbasicinfo.votersInfoForMandalVOList == null || votersbasicinfo.votersInfoForMandalVOList.length == 0)
	 {
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
			$("#votersBasicInfoSubChartDiv").css('border','1px solid #FFF');
			$("#votersBasicInfoSubDiv").css('border','1px solid #FFF');

			$("#votersBasicInfoMsgDiv").html("<span id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</span>");
		 return;
	}
	if(votersbasicinfo != null && votersbasicinfo.datapresent)
	{
		
		$("#votersBasicInfoSubChartDiv").css('border','1px solid black');
		$("#votersBasicInfoSubDiv").css('border','1px solid black');
		$("#votersBasicInfoMsgDiv").html('');
		//$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);
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
				str += '    <td>'+prevElecInfo[i].electionYear+'</td>';
				str += '    <td>'+prevElecInfo[i].totalVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalMaleVoters+'</td>';
				str += '    <td>'+prevElecInfo[i].totalFemaleVoters+'</td>';
			   if(prevElecInfo[i].totalVotersDiff < 0){
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
				str += '  </tr>';
			}
			    str += '  <tr>';
				str += '    <td>'+jsObj.year+'</td>';
				str += '    <td>'+votersbasicinfo.totVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalMaleVoters+'</td>';
				str += '    <td>'+votersbasicinfo.totalFemaleVoters+'</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
				str += '    <td>--</td>';
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

			//$("#votersBasicInfoDiv").html(str);
			if(jsObj.type != "booth"){
			  $("#votersBasicInfoSubChartDiv").css("border","1px solid black"); 
	          $("#votersBasicInfoSubDiv").css("border","1px solid black");
			 }
		
		str = '';
		if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       
		buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,jsObj.typename);

	    	 
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

		var impFamliesResultDataSource = new YAHOO.widget.DataTable('votersBasicInfoSubDiv', votersResultColumnDefs,myDataSource, myConfigs);

		}
		//$('#votersByLocationTabContentDiv_body').css("border","1px solid black");
	}
	
	else
	{
		$("#votersTitle").html("Voters Information of "+jsObj.typename+" in "+jsObj.year+" ");
		//$("#votersTitle").html(jsObj.typename);

			$("#votersBasicInfoDiv").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
		
	}
}

function buildVotersChart(chartInfo,reqTitle)
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
        var chart = new google.visualization.PieChart(document.getElementById('votersBasicInfoSubChartDiv'));
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

function getPreviousElectionVotingTrends(id,publicationId,type)
	{
	
		/*var publicationDateId = $("#publicationDateList").val();
		var level = $("#reportLevel").val();
		var constituencyId = $("#constituencyList").val(); 

		if(level == 1 || level == 2)
		{
			 $("#previousEleVotingTrendsDiv").html('');
			 return;
		}
		if(level == 3)
		{
			id = $("#panchayatField").val();
			type = "panchayat";
			name = $("#panchayatField option:selected").text()+" "+"Panchayat";
		}
		if(level == 4)
		{
			id = $("#pollingStationField").val();
			type = "booth";
			name = $("#pollingStationField option:selected").text()
		}

		if(id == 0 || id == null || id == '')
			return false;*/
		
		
		
		/* Updated by sasi*/
		//Because booth doesnt consists the results iam hiding the PreviousElections results div by calling the function
		
		if(maintype=="booth"|| maintype == "ward"){
			$("#previousEleVotingTrendsDiv1").css('display','none');
		}
	  if(maintype == "constituency" || maintype == "panchayat" || maintype == "mandal"){
		  $("#previousEleAjaxImg").css("display","block");
		var jsObj=
		{
			id                :id,
			publicationDateId :publicationId,
			constituencyId    :$("#constituencyList").val(),
			name              :mainname,
			type              :type,
			task:"getPreviousEleVotingTrends"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getPreviousEleVotingTrendsAction.action?"+rparam;	
		callAjax(jsObj,url);
      }
	 }

function showPreviousEleVotingTrends(results,jsObj)
{
	 $("#previousEleAjaxImg").css("display","none");
	if(mainreqid == jsObj.id)
	{	
	 var flag = false;
	 $("#previousEleVotingTrendsDiv1").css({'display':'block','width':'96%','color':'#000'});
	 $("#previousEleVotingTrendsDiv").html('');
      for(var i in results)
		 if(results[i].partyVotesEarnedVOs != null && results[i].partyVotesEarnedVOs .length > 0)
		   flag = true;

		if(flag)
		{
		  var str = '';
			$("#previousEleVotingTrendsDiv").append('<h4 id="prevVotTrendHeadingSpan" style="margin: 0px -20px; padding: 10px 10px 10px 20px; float:left;clear:both;">Previous Election Voting Trends in '+jsObj.name+' </h4>');
				str +='<table class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;">';
				str +='<thead class="info"><tr>';
				str +='<th>Election Type</th>';
				str +='<th>Year</th>';
				str +='<th>Total Voters</th>';
				  str +='<th>Votes Polled</th>';
			   for(var i in results[0].partiesList)
			     str +='<th>'+results[0].partiesList[i]+'</th>';
			     str +='</tr></thead><tbody>';
				
				for(var j in results)
				{
				  str +='<tr>';
				  str += '<td>'+results[j].reqType+'</td>';
				  str += '<td>'+results[j].electionYear+'</td>';
				  str +='<td>'+results[j].totalVotes+'</td>';
					str +='<td>'+results[j].polledVotes+'</td>';
					var partyVotesEarnedVOs = results[j].partyVotesEarnedVOs;
					  for(var k in partyVotesEarnedVOs)
				         str +='<td>'+partyVotesEarnedVOs[k].votesEarned+'</td>';
						
					     str +='</tr>';
		 			
				}
			 
			 str +='</tbody></table>';
			 $("#previousEleVotingTrendsDiv").append(str);
			 
		}
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

function getCounts(id,publicationId,type)
{
var typeName = mainname;
/*$("#reportLevelheading1").html("");
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
}*/
if(type == 'booth')
{
//type = 'booth';
//id = $("#pollingStationField").val();
return;
}
var jsObj=
		{
			type:type,	
			id:id,
			typeName:typeName,
			publicationDateId:publicationId,
			task:"getCountForLevel"
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCountForLevelAction.action?"+rparam;						
		callAjax(jsObj,url);

}
var resultDataForNav=null;
function buildCountData(results,jsObj)
{
document.getElementById("showHideDiv").style.display = "block";
resultDataForNav=results;
areatype=results[0].areaType;
		   
		  if(areatype=="RURAL"){
		  area='MANDAL';
		  }
		  if(areatype=="URBAN"){
		  area='WARD';
		  }
		  if(areatype=="RURAL-URBAN"){
		  area='MANDAL/MUNCIPALITY/CORPORATION';
		  }
		  $("#areaId").text(area);
		  $('#votersShareBtn1').css('display','block');
		  var btnAgeLnk='View '+area+' Wise Age Details';
		  var btnFmlyLnk='View '+area+' Wise Family Details' ;
		  
		  $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">'+btnAgeLnk+'</a>');
	      $("#impFamiliesMoreInfoButn").attr("value",btnFmlyLnk);
			
		   
//console.log(resultDataForNav);
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
document.getElementById('reportLevelCountDiv').style.display = 'inline-block';
//$("#reportLevelCountDiv").css({'border':'1px solid #d3d3d3','float':'left','margin':'6px 3px 6px 15px','background-color':'#f5f5f5','padding':'10px'});
	str+='<div style="margin:10px;">';
	if(results[0].totalmandals == null)
		results[0].totalmandals = 0;
	if(results[0].noOfLocalBodies == null)
	results[0].noOfLocalBodies = 0;
	if(results[0].totalPanchayats == null)
		results[0].totalPanchayats = 0;
	if(results[0].totalBooths == null)
		results[0].totalBooths = 0;
	
	/*str+='<ul>';

	str +='<li><a class="parent" href="#"><span class="badge badge-info badge-add">'+results[0].totalmandals+'</span></a><span class="help-inline f2">Mandals</span>&nbsp;&nbsp;';
	for(var i in result)
	{
	str+='<li><a onclick="getvotersSubBasicInfo('+result[i].id+',\''+result[i].name+'\');showSubNewsDetails(\''+result[i].id+'\',5);"><span>'+result[i].name+'</span></a></li>';
	}
	str+='</ul>';*/
    if(results[0].totalmandals !=null && results[0].totalmandals!=0 )
	str +='<span class="btn btn-success btn-small">'+results[0].totalmandals+'</span><span class="help-inline f2">Mandals</span>';
	if(results[0].noOfLocalBodies !=null && results[0].noOfLocalBodies!=0 )
	str +='<span class="btn btn-info btn-small">'+results[0].noOfLocalBodies+'</span><span class="help-inline f2">Muncipalities</span>';
	 if(results[0].totalNoOfWards !=null && results[0].totalNoOfWards!=0 )
	str +='<span class="btn btn-info btn-small">'+results[0].totalNoOfWards+'</span><span class="help-inline f2">Wards</span>';
	if(results[0].totalPanchayats !=null && results[0].totalPanchayats!=0 )
	str +='<span class="btn btn-info btn-small">'+results[0].totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>';
	if(results[0].totalBooths !=null && results[0].totalBooths!=0 )
	str +='<span class="btn btn-info btn-small">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>';
   
str+='</div>';
divEle.innerHTML = str;
}

if(results[0].totalmandals>0) {
$(".leftNav-Mandals").show();
$("#leftNav-Mandals-list").customMenu(resultDataForNav,0);}
else {
$(".leftNav-Mandals").hide();}

if(results[0].noOfLocalBodies>0) {
$(".leftNav-Municipalities").show();
$("#leftNav-Municipalities-list").customMenu(resultDataForNav,1); }
else {
$(".leftNav-Municipalities").hide();}

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
	//var value = $('#mandalField').val();
	var str = '';
	var divEle1 = document.getElementById('reportLevelCountDiv1');
	//$("#reportLevelheading1").html(" "+jsObj.typeName+" Information");
	//document.getElementById('reportLevelCountDiv1').style.display = 'block';
	$("#reportLevelCountDiv1").css({'display':'block','padding':'10px 0'});
	  if(jsObj.type == "mandal"){
		if(mainreqid.charAt(0) =="1"){
		type = "muncipality";
	  }
	}

	str +='<div>';
	if(jsObj.type == "mandal" && type != "muncipality")

	{
		if(results[0].totalPanchayats == null)
		results[0].totalPanchayats = 0;
		if(results[0].totalBooths == null)
		results[0].totalBooths = 0;
	str +='<span class="btn btn-info">'+results[0].totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>';
	str +='<span class="btn btn-info">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>';
	}
	if( jsObj.type == "mandal" && type == "muncipality")

	{
			if(results[0].totalBooths == null)
			results[0].totalBooths = 0;
			if(results[0].totalNoOfWards !=null && results[0].totalNoOfWards!=0 )
	str +='<span class="btn btn-info">'+results[0].totalNoOfWards+'</span><span class="help-inline f2">Wards</span>';
	str +='<span class="btn btn-info">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>';
	}
	if(jsObj.type == "panchayat")

	{
		if(results[0].totalBooths == null)
			results[0].totalBooths = 0;
	str +='<span class="btn btn-info">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>';
	}
	if(jsObj.type == "ward")

	{
		if(results[0].totalBooths == null)
			results[0].totalBooths = 0;
	str +='<span class="btn btn-info">'+results[0].totalBooths+'</span><span class="help-inline f2">Booths</span>';
	}
	str+='</div>';

	divEle1.innerHTML = str;

	}


function buildPreviousVotersDetails(myResults,jsObj){


			//$('#votersTitle').html(mainname);

				

			if(myResults.length == 0){
				$('#votersBasicInfoDiv1').html("<span style='margin-left:15px;color:red;'> No  Data Available</span>");
			return false;
			}

		
			var str='';

			str+='<table id="voterBasicInfoTable" class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;">';
			 str+='<thead class="info"><tr>';
			  str+='<th>Year</th>';
		      str+='<th>Total Voters</th>';
		 	  str+='<th>Male Voters</th>';
		 	  str+='<th>Female Voters</th>';
			  str+='<th>Total Voters Difference</td>';
			  str+='<th>Male Voters Difference</td>';
		  	  str+='<th>Female Voters Difference</td>';
			 str+='</tr></thead><tbody>';


			 for(var i=myResults.length;i>=1;i--){

			  str+='<tr>';

			  if(myResults[i-1].isPublication =="false")
			    str+='<td><span title='+myResults[i-1].electionDate+'>'+myResults[i-1].electinYear+'</span><b style="color:red">&nbsp;(E)<b></td>';
			  else 
				str+='<td><span title='+myResults[i-1].publicationDate+'>'+myResults[i-1].electinYear+'</span><b style="color:red">&nbsp;(P)<b></td>';

		      str+='<td>'+myResults[i-1].totVoters+'</td>';
		 	  str+='<td>'+myResults[i-1].totalMaleVoters+'</td>';
		 	  str+='<td>'+myResults[i-1].totalFemaleVoters+'</td>';

		     
			  if(i !=1){

				 if(myResults[i-1].totalVotersDiff < 0)
				  str+='<td>'+myResults[i-1].totalVotersDiff+'<img class="imageSize" src="images/downarrow.png" /></td>';
				 else if(myResults[i-1].totalVotersDiff > 0)
				  str+='<td>'+myResults[i-1].totalVotersDiff+'<img class="imageSize" src="images/uparrow.png" /></td>';
				 else
				  str+='<td>'+myResults[i-1].totalVotersDiff+'</td>';
			 }
			 else
				  str+='<td>---</td>';



			  if(i != 1){
			  
				 if(myResults[i-1].maleVotersDiff < 0)		
				   str+='<td>'+myResults[i-1].maleVotersDiff+' <img class="imageSize" src="images/downarrow.png" /></td>';
				 else if(myResults[i-1].maleVotersDiff > 0)
					str+='<td>'+myResults[i-1].maleVotersDiff+' <img class="imageSize" src="images/uparrow.png" /></td>';
				 else 
				   str+='<td>'+myResults[i-1].maleVotersDiff+'</td>';
			  }
			 else
		          str+='<td>---</td>';
			

			 if(i !=1){

				 if(myResults[i-1].femaleVotersDiff < 0)
				  str+='<td>'+myResults[i-1].femaleVotersDiff+'<img class="imageSize" src="images/downarrow.png" /></td>';
				 else if(myResults[i-1].femaleVotersDiff > 0)
				  str+='<td>'+myResults[i-1].femaleVotersDiff+'<img class="imageSize" src="images/uparrow.png" /></td>';
				 else
				  str+='<td>'+myResults[i-1].femaleVotersDiff+'</td>';
			 }
			 else
				  str+='<td>---</td>';


			 str+='</tr>';
			 }
			str+='</tbody></table>';

			str+='<div class="breadcrumb pull-left" style="margin: 3px 0;"><font style="color:red;">Note</font> :<span class="help-inline f2"><b>P</b>- Publication</span><span class="help-inline f2"><b>E</b>- Election</span>';
			str+='<span class="help-inline f2"><img class="imageSize" src="images/uparrow.png" /> -Voters Increased</span> <span class="help-inline f2"><img class="imageSize" src="images/downarrow.png" /> - Voters Decrease</span></div>';


			$('#votersBasicInfoDiv1').html(str);

		     //$('#voterBasicInfoTable tr').eq(1).css('font-weight','bold');

		}
		function getElectionyearsByMandalId(id,type)
		{
			
			if(maintype == "constituency")
			{
			 if(id == 0)
				return;
			$("#crossVotingMainDiv").css("display","block");
			$("#crossVotingEleyearAjaximg").css("display","inline-block");	
		  var jsObj=
		  {
			 id                :id,
			 type              :maintype,
			 task:"getElectionyearsByMandalId"
		 };
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "getElectionyearsByMandalIdAction.action?"+rparam;	
		 callAjax(jsObj,url);
		  }
		  else
			{
			$("#crossVotingMainDiv").css("display","none");
			return;
			}

	 }
		$('#votersShareBtn').live("click",function(){

			$('#votersInfoAjaxImg').css("display","block");
			getvotersBasicInfo("voters",mainreqid,$("#publicationDateList").val(),maintype);
			
		});

		
		 function getParliamentConstituencyId()
		{
			
				var id = $("#constituencyList").val();
				var eleYear = $("#electionYearsForCrossVoting").val();

				if(id == 0 || id == -1)
					return;
				else if(eleYear == 0 || eleYear == -1)
					return;

				var jsObj=
				{
				 id                :id,
				 eleYear		   :eleYear,
				 type              :"constituency",
				 task:"getParliamentConstituencyId"
				};
				 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				 var url = "getParliamentConstituencyIdAction.action?"+rparam;	
				 callAjax(jsObj,url);
			
		}

		function getPartiesList()
		{
		
			var elecValue =  $("#electionYearsForCrossVoting").val();
			var assemblyValue =  $("#constituencyList").val();
					
			if(elecValue == -1 || assemblyValue == -1 || elecValue == 0 || assemblyValue == 0)
				return;
				
			else
			{
				$("#crossVotingPartyAjaximg").css("display","inline-block");
				var jsObj={						
						electionYear:elecValue,
						assemblyVal:assemblyValue ,
						task:"getParty"
				  }
			
				var rparam="crossVotingReportAjaxAction.action?assemblyValue="+jsObj.assemblyVal+"&election="+jsObj.electionYear;
				callAjax(jsObj,rparam);
			}

		}

		function getCrossVoting()
		{
		
			var elecValue =  $("#electionYearsForCrossVoting").val();
			var partyValue = $("#PartySelect").val();
			var assemblyValue =  $("#constituencyList").val();
			var parliamentValue =  parliamentConstituencyId;

			var jsObj={
							electionValue : elecValue,
							partyValue : partyValue,
							parliamentValue : partyValue,
							assemblyValue : assemblyValue,
							alliances		:"false",
							task:"crossVotingReport"
					  }
				
				var rparam="crossVotingReportAjaxAction.action?election="+jsObj.electionValue+"&party="+jsObj.partyValue+"&parliamentValue="+jsObj.parliamentValue+"&assemblyValue="+jsObj.assemblyValue+"&includeAliance="+jsObj.alliances;
				callAjax(jsObj,rparam);

		}
		
		$("#crossVotingReportBtn").live("click",function(){

			var elecValue =  $("#electionYearsForCrossVoting").val();
			var partyValue = $("#PartySelect").val();
			var assemblyValue =  $("#constituencyList").val();
			var parliamentValue =  parliamentConstituencyId;

			$("#crossVotingErrorMsgDiv").html('');
			 if(elecValue == 0 || elecValue == -1 || elecValue == '')
			{
				$("#crossVotingErrorMsgDiv").html('Please Select Election Year');
				return;
			}
			else if(partyValue == 0 || partyValue == -1 || partyValue == '')
			{
				$("#crossVotingErrorMsgDiv").html('Please Select Party.');
				return;
			}
			
			var url = "crossVotingReportInputAction.action?electionYear="+elecValue+"&&acId="+assemblyValue+"&&pcId="+parliamentValue+"&&party="+partyValue+"&&";
			window.open(url,'_blank');
			window.focus();
		});


	/*** FUNCTIONS FOR NAVIGATIONS START***/

(function($) {
  $.fn.customMenu = function(menudata,menutype,mandalid,panchayatid,municipalityid) {
  /* menudata= resultset of the consistuency data upto booth 
     menutype= 0-Mandals, 1-Municipalities, 2-Panchayats, 3-Wards/Booths */
	 
  var str="";
		 switch(menutype)
		 {
		
		case 0: // Build Mandal Menu
		  if(menudata[0].mandalList!=null){
		  $.each(menudata[0].mandalList, function(iter,mandals){
            str+='<li><a onClick="" data-mandalid="'+ mandals.id+'" name-mandal="'+mandals.name+'"><span class="checkbox"><input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+mandals.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i> '+mandals.name+'</a></li>';
		  });}
		  $(".middleNav ul").html("");
		  $(".rightNav-Booths").hide();
		  $(".middleNav-Panchayats").hide();
		  $(".middleNav-Wards").hide(); 
		 break;
		
		 case 1: // Build municipality Menu	
		  if(menudata[0].localbodiesList!=null){
		  	$.each(menudata[0].localbodiesList, function(iter,municipality){
			if( municipality.selectOptionsList != null){
		  	str+='<li><a onClick="" dest-atr="ward"  data-municipalityid="'+ municipality.id+'" name-muncipal="'+municipality.name+' " ><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+municipality.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i>'+municipality.name+'</a></li>';
		  } else if(municipality.selectOptionsList1 != null){
		  	str+='<li><a onClick="" dest-atr="booth" data-municipalityid="'+ municipality.id+'" name-muncipal="'+municipality.name+' "><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+municipality.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i>'+municipality.name+'</a></li>';
		  }
		  }); }
		   $(".middleNav-Panchayats").hide();
		  $(".middleNav ul").html("");
		  $(".rightNav ul").html("");
		 break;
		 
		 
		 
		 case 2: // Build Panchayats Menu
		    $.each(menudata[0].mandalList, function(iter,mandals){
		     if(mandalid==mandals.id && mandals.selectOptionsList != null){
					  $.each(mandals.selectOptionsList,function(iter,panchayats){
					str+='<li><a onClick="" data-panchayatid="'+ panchayats.id+'" data-mandalid="'+ mandals.id+'" name-panchayat="'+panchayats.name+' Panchayat"><span class="checkbox"> <input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+panchayats.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span><i class="icon-chevron-right"></i>'+panchayats.name+'</a></li>';
					 });
			}
			});
			
			$(".middleNav").show();
			$(".middleNav-Wards").hide(); 
			$(".middleNav-Panchayats").show();
			$(".rightNav-Booths").hide();
			$(".rightNav ul").html("");
		    $(".rightNav").css("width","490px");	
		 break;
		 
		 
		 case 3: // Build Booths Menu
		  $.each(menudata[0].mandalList, function(iter,mandals){
		     if(mandalid==mandals.id && mandals.selectOptionsList != null){
					  $.each(mandals.selectOptionsList,function(iter,panchayats){
							 if(panchayatid==panchayats.id && panchayats.selectOptionsList != null){
									  $.each(panchayats.selectOptionsList,function(iter,booths){
									str+='<li class="nav nav-pills"><a class="btn" data-panchayatid="'+ panchayats.id+'" data-mandalid="'+ mandals.id+'" data-boothid="'+ booths.id+'" name-booth="Booth-'+booths.name+'" href="javascript:{}" style="width:44px;"><span class="checkbox"><input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
									 });
								}
					 }); 
			}
		  });
		  $(".rightNav-Booths").show();
		 break;
		 
		case 4: // Build Booths Menu For Munucipalities
		 	   $.each(menudata[0].localbodiesList, function(iter,municipality){
		     if(municipalityid==municipality.id && municipality.selectOptionsList1 != null){
					  $.each(municipality.selectOptionsList1,function(iter,booths){
									str+='<li class="nav nav-pills"><a class="btn" data-municipalityid="'+ municipality.id+'" data-municipalityid="'+ municipality.id+'" data-boothid="'+ booths.id+'" name-booth="Booth-'+booths.name+'" href="javascript:{}" style="width:44px;"><span class="checkbox"><input type="radio" data-mandalid="'+ municipality.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
									 });
								}
			});
			$(".rightNav-Booths").show();
			$(".rightNav ul").html("");
			$(".middleNav").hide();
			$(this).closest(".rightNav").css("width","720px");	
		 break;
	      case 5: // Build Wards Menu
		     $.each(menudata[0].localbodiesList, function(iter,municipality){
		     if(municipalityid==municipality.id && municipality.selectOptionsList != null){
					  $.each(municipality.selectOptionsList,function(iter,wards){ 
									//str+='<li class="nav nav-pills"><a class="btn" data-municipalityid="'+ municipality.id+'" data-municipalityid="'+ municipality.id+'" data-boothid="'+ booths.id+'" name-booth="'+booths.name+'" href="javascript:{}"><span class="checkbox"><input type="radio" data-mandalid="'+ municipality.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
									str+='<li><a onClick="" data-wardid="'+ wards.id+'" data-municipalityid="'+ municipality.id+'" name-ward="'+wards.name+' wards"><span class="checkbox"> <input type="radio" data-municipalityid="'+ municipality.id+'" id="Chk-'+wards.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span><i class="icon-chevron-right"></i>'+wards.name+'</a></li>';
									});
									}
			});
			$(".middleNav").show();
			$(".middleNav-Panchayats").hide();
			$(".middleNav-Wards").show(); 
			 $(".rightNav-Booths").hide();
			$(".rightNav ul").html("");
		    $(".rightNav").css("width","490px");	
		 break;
		 case 6: // Build Booths Menu for wards 
		 $.each(menudata[0].localbodiesList, function(iter,municipality){
		     if(municipalityid==municipality.id && municipality.selectOptionsList != null){
		  $.each(municipality.selectOptionsList, function(iter,wards){
		     if(panchayatid==wards.id && wards.selectOptionsList != null){
					  $.each(wards.selectOptionsList,function(iter,booths){
						str+='<li class="nav nav-pills"><a class="btn" data-wardid="'+ wards.id+'" data-municipalityid="'+ municipality.id+'" data-boothid="'+ booths.id+'" name-booth="'+booths.name+'" href="javascript:{}"><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
									 });
								}
					 }); 
			}
			  });
		   $(".rightNav-Booths").show();
		 break;
		 
		
			}	
		
		$(this).html(str);
	$(".radio").dgStyle();
	$(".checkbox").dgStyle();
  };
  
})(jQuery);
var showflag=true;
$(document).ready(function(){

// Menu Show Events...
$("#ShowMenu").click(function(){
	if(showflag||fromOnChange) {
		showConst=false;
		$("#ShowConstMenu").trigger('click');
		$(".customMenu").css("display","inline-block");
		$(this).html('Hide Menu <i class="icon-chevron-up"></i>');
		if(!fromOnChange){
			showflag=false;
		}
		else{
			fromOnChange=false;
			showflag=false;
		}
		}
	else {
		$(".customMenu").hide();
		$(this).html('Show Menu <i class="icon-chevron-down"></i>'); 
		showflag=true;
	}

});


$("#leftNav-Mandals-list a").live("click",function(){
$(".leftNav li").removeClass("active");
$(this).closest("li").addClass("active");//.siblings().removeClass("active");

$("#middleNav-Panchayats-list").customMenu(resultDataForNav,2,$(this).attr("data-mandalid"));

});

$("#leftNav-Municipalities-list a").live("click",function(){
$(".leftNav li").removeClass("active");
//$(this).closest("li").addClass("active").siblings().removeClass("active");
$(this).closest("li").addClass("active");
var dest=$(this).attr("dest-atr");
  if(dest === "booth")
    $("#rightNav-Booths-list").customMenu(resultDataForNav,4,0,0,$(this).attr("data-municipalityid"));
  else if(dest === "ward")
$("#middleNav-Wards-list").customMenu(resultDataForNav,5,0,0,$(this).attr("data-municipalityid"));
});


$("#middleNav-Panchayats-list a").live("click",function(){
$(this).closest("li").addClass("active").siblings().removeClass("active");
$("#rightNav-Booths-list").customMenu(resultDataForNav,3,$(this).attr("data-mandalid"),$(this).attr("data-panchayatid"));
});

$("#middleNav-Wards-list a").live("click",function(){
$(this).closest("li").addClass("active").siblings().removeClass("active");
$("#rightNav-Booths-list").customMenu(resultDataForNav,6,$(this).attr("name-muncipal"),$(this).attr("data-wardid"),$(this).attr("data-municipalityid"));
});

$("#rightNav-Booths-list a").live("click",function(){
$(this).addClass("btn-primary").closest("li").siblings().find("a").removeClass("btn-primary");
});

// CLICK EVENTS END

// BUILD PAGE FUNCTIONS CALLING BASED ON THE RADIO BUTTON SELECTED

// Mandals CLICKED
$("#leftNav-Mandals-list a .checkbox").live("click",function(){
$("#constituencyResults").trigger('click');
$(this).find("input").attr("checked",true);
var levelId =2;
var mandalid=$(this).closest("a").attr("data-mandalid");
var mandalName =$(this).closest("a").attr("data-mandalid"); 
//alert("SHOW Mandal DATA");

mainreqid = mandalid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'mandal';
mainname = $(this).closest("a").attr("name-mandal"); 
scrollToNewsDiv();
getAllTabs(mandalid,$("#publicationDateList").val(),'mandal');
//getvotersSubBasicInfo(mandalid,"mandal-name","mandal");
//showSubNewsDetails(mandalid,"Mandal");
});

//// Municipalities CLICKED
$("#leftNav-Municipalities-list a .checkbox").live("click",function(){
$(this).find("input").attr("checked",true);
var municipalityid=$(this).closest("a").attr("data-municipalityid");
var levelId =2;
mainreqid = municipalityid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'mandal';
mainname = $(this).closest("a").attr("name-muncipal");
scrollToNewsDiv();
getAllTabs(municipalityid,$("#publicationDateList").val(),'mandal');
//alert("SHOW Municipality DATA");
//getvotersSubBasicInfo(municipalityid,"mun-name","mandal");
//showSubNewsDetails(municipalityid,"localbody");
});

//Panchayats CLICKED
$("#middleNav-Panchayats-list a .checkbox").live("click",function(){
$(this).find("input").attr("checked",true);
var mandalid=$(this).closest("a").attr("data-mandalid");
var panchayatid=$(this).closest("a").attr("data-panchayatid");
var levelId =3;
mainreqid = panchayatid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'panchayat';
mainname = $(this).closest("a").attr("name-panchayat");
scrollToNewsDiv();
getAllTabs(panchayatid,$("#publicationDateList").val(),'panchayat');
//alert("SHOW Panchayat DATA");
//getvotersSubBasicInfo(panchayatid,"panchayat-name","panchayat");
//howSubNewsDetails(panchayatid,"Panchayat");
//buildVotersByPanchayatDataTable(panchayatid);
});
//wardclicked
$("#middleNav-Wards-list a .checkbox").live("click",function(){
$(this).find("input").attr("checked",true);
var muncipalityid=$(this).closest("a").attr("data-municipalityid");
var panchayatid=$(this).closest("a").attr("data-wardid");
var levelId =3;
mainreqid = panchayatid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'ward';
mainname = $(this).closest("a").attr("name-ward");
getAllTabs(panchayatid,$("#publicationDateList").val(),'ward');
//alert("SHOW Panchayat DATA");
//getvotersSubBasicInfo(panchayatid,"panchayat-name","panchayat");
//howSubNewsDetails(panchayatid,"Panchayat");
//buildVotersByPanchayatDataTable(panchayatid);
});

//Booths CLICKED
$("#rightNav-Booths-list a .checkbox").live("click",function(){
$(this).find("input").attr("checked",true);
var mandalid=$(this).closest("a").attr("data-mandalid");
var levelId =4;
var panchayatid=$(this).closest("a").attr("data-panchayatid");
var boothid=$(this).closest("a").attr("data-boothid");
mainreqid = boothid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'booth';
mainname = $(this).closest("a").attr("name-booth");
scrollToNewsDiv();
getAllTabs(boothid,$("#publicationDateList").val(),'booth');
//alert("SHOW BOOOTH DATA");

//getvotersSubBasicInfo(boothid,"booth-name","booth");
//buildVotersByBoothDataTable(boothid);
});

});
/** END FUNCTIONS FOR NAVIGATIONS **/
	

 
