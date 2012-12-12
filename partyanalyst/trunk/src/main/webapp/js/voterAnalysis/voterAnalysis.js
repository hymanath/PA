var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
							
					};
var publicationYear = "";
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
			getPanchayatList('pollingstation','pollingStationField');
			
		}
	}
	

function buildVotersByLocBoothDataTable(id)
{

var boothId = $('#'+id).val();
var publicationDateId = $('#publicationDateList').val();
if(boothId == "0" || boothId == null || publicationDateId == null || publicationDateId == "0")
	return false;

if(boothId == "0")
	return false;

var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true},
{key:"cast", label: "Cast", sortable:true},
{key:"castCatagery", label: "CastCategory", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&publicationId="+publicationDateId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType","cast","castCatagery"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=20", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
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
var publicationId = $('#buildVotersByLocPanchayatDataTable').val();
var panchaytId =  $('#publicationDateList').val();

if(panchaytId == "0" || publicationId == "0")
	return false;




var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true},
{key:"cast", label: "Cast", sortable:true},
{key:"castCatagery", label: "CastCategory", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId=1&panchaytId=444&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType","cast","castCatagery"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=20", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByPanchayatTabContentDiv_body",
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
	{
		
		
		var constituencyID = document.getElementById("constituencyList");
		var name=constituencyID.options[constituencyID.selectedIndex].name;
		var value=constituencyID.options[constituencyID.selectedIndex].value;
		var choice=false;
		var locationAlertEl =  document.getElementById("locationAlertMsg");
		
		var jsObj=
			{
					
					selected:value,
					selectElmt:selectElmt,
					task:"getMandalList"
			};
		


		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "voterAnalysisAjaxAction.action?"+rparam;						
		
		callAjax(jsObj,url);
	}


	function getPanchayatList(checkedele,selectedEle)
	{
		
		var mandalId=document.getElementById("mandalField");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;
		var value = value1.substring(1);
		
		
		var alertEl = document.getElementById("locationAlertMsg");
		alertEl.innerHTML = '';
		
		selectname = mandalField.options[mandalField.selectedIndex].text;
		var flag= selectname.search("MUNCIPALITY");
		
		
		 if(value == 0)
		{
			alertEl.innerHTML ='<P><%=locationAlert%></P>';
			return;
		}

		
		
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				
				task:"getPanchayat"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getPanchayatByConstituencyAction.action?"+rparam;						
		callAjax(jsObj,url);
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
									buildVotersBasicInfo(myResults,jsObj.to);
								}
							else if(jsObj.task == "getCastInfo")
								{
									buildCastInfoData(myResults,jsObj);
								}

								else if(jsObj.task == "getCastInfoForsubLevels")
								{
									buildCastInfoForSubLevels(myResults,jsObj);
								}								
								else if(jsObj.task == "importantFamiliesinfo")
								{
								  if(myResults != null){
								    impFamilesStaticTable(myResults);
									buildImpFamilesChart(myResults);
								    if(myResults.subList != null && myResults.subList.length > 0)
								      buildTableForImpFamilesMandal(myResults.subList,myResults.name,myResults.type);
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
							}catch (e) {   
								
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
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
		
		
		var checkedEle = jsObj.checkedele;
		var selectedEle = jsObj.selectedEle;
		
		var select = document.getElementById(selectedEle);
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
$(document).ready(function(){
    $("#publicationDateList").change(function(){
	    if($("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0){
		   var str = $('#publicationDateList :selected').text();
		   var year=str.split("-");
		   try{
		    publicationYear =year[2];
		    }catch(e){
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
	      getBasicInfo();
	  
	});
	$("#mandalField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 2 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getBasicInfo();
	});
	$("#panchayatField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 3 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getBasicInfo();
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
		 else if($("#reportLevel").val() == 2 && $("#mandalField option").length > 0 && $("#mandalField").val() != 0 )
		   getBasicInfo();
		 else if($("#reportLevel").val() == 3 && $("#panchayatField option").length > 0 && $("#panchayatField").val() != 0 )
		   getBasicInfo();
		 else if($("#reportLevel").val() == 4 && $("#pollingStationField option").length > 0 && $("#pollingStationField").val() != 0 )
		   getBasicInfo();
			
		}
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
		 }else if($('#votersDiv4').css('display') == 'block'){

             callCorrespondingAjaxCall();
           
		   $("#votersbasicinfoForAgeWiseDetls").hide();
		   $("#votersBasicInfoDivForAgeWiseDetls").html("");
		   $("#votersBasicInfoSubChartDivForAgeWiseDetls").html("");
		   $("#votersBasicInfoSubDivForAgeWiseDetls").html("");
		   $("#ageWiseDetlsShowBasicInfo").val("View Basic Voter Details");
		 }else{
		   getvotersBasicInfo("voters","");
		   getVotersData();
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
	  buildVotersByLocPanchayatDataTable("panchayatField");
	}else if(level == 4){
	  buildVotersByLocBoothDataTable("pollingStationField");
	}
}

function getVotersCastInfo()
	{
	
  $("#localCastStatsTabContent_header").html("");
  var divEle = document.getElementById('localCastStatsTabContent_body');
	divEle.innerHTML ='';
	var publicationDateId = $("#publicationDateList").val();
	var level = $("#reportLevel").val();
	var type = '';
	var id='';
	var typename='';
	var mandalId='';
	if(level == 1){
	type = 'constituency';
	id = $("#constituencyList").val();
	typename = $('#constituencyList :selected').text() + ' Constituency ';
	}
	else if(level == 2){
	type = 'mandal';
	mandalId = $("#mandalField").val();
	id=mandalId.substring(1);
	typename = $('#mandalField :selected').text();
	

	}
	else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	  typename = $('#panchayatField :selected').text()+ ' Panchayat ';

	}
	else if(level == 4){
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 typename = $('#pollingStationField :selected').text() + ' Booth';
	}
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

function buildCastInfoForSubLevels(myresults,jsObj)
	{
		
		var str ='';
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
		var totalVoters=subLevelcastData.totalVoters;
		var cast1 =subLevelcastData.castVOs;
			
			for(var k in cast1)
			{
		var castStats1 = {
			mandal : name,
			caste : cast1[k].castName,
			castePopulation : cast1[k].castCount,
			malePopulation : cast1[k].malevoters,
			femalePopulation : cast1[k].femalevoters,
			castePercentage:cast1[k].castPercentage,
			totalVoters:totalVoters,
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
		str+='<h4 id="sublevelHeading">Mandal/Muncipality wise Cast Statistics In '+typeName+'Constituency</h4>';
		else if(type == "mandal")
		str+='<h4 id="sublevelHeading">Panchayat wise Cast Statistics In '+typeName+' </h4>';
		else if(type =="panchayat")
		str+='<h4 id="sublevelHeading">Booth wise Cast Statistics In '+typeName+' Panchayat</h4>';
		str+='<thead>';
		str+='<tr>';
		
		if(type == "constituency")
		str +='<th>MandalName</th>';
		if(type == "mandal")
		str +='<th>PanchayatName</th>';
		if(type =="panchayat")
		str +='<th>Booth No</th>';
		str +='<th> Caste</th>';
		str +='<th>TotalVoters</th>';
		str +='<th>castPopulation</th>';
		str +='<th>malePopulation</th>';
		str +='<th>FemalePopulation</th>';
		str +='<th>castPercentage</th>';
		
		str+='</tr>';
		str+='</thead>';
		
		str+='<tbody>';
		for(var i in constMgmtMainObj.castStatssubArray)
		{
		str+='<tr>';
		
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].mandal+'</td>';
		if(type =="panchayat")
		{
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].mandal+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else
		{
		str+='<td>'+constMgmtMainObj.castStatssubArray[i].caste+'</td>';
		}
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
		  "aoColumns": [null,null,null,null,null,null,null
     
	  
    ] 
		});
	$('#subLevelTable tr').removeClass("odd");
	$('#subLevelTable tr').removeClass("even");
	$('#subLevelTable td').removeClass("sorting_1"); 
	}	

	
}
function getVotersInACaste(id,publicationDateId,caste)
{
$("#localCastStatsVotersTitle").html("");
$("#localCastStatsTabContent_subbody1").html("");
var typename = $('#panchayatField :selected').text()+ ' Panchayat ';
var publicationDateVal=$('#publicationDateList :selected').text();
var year=publicationDateVal.substr(publicationDateVal.length - 4)
var jsObj={
			id:id,
			publicationDateId:publicationDateId,
			caste:caste,
			typename:typename,
			publicationDate:year,
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
   }
  if(buttonType == "impFamilies"){
   $("#impFamilesBasicDetails").html("");
   $("#impFamilesBasicInfoSubChartDiv").html("");
   $("#impFamilesBasicSubDetails").html("");
   $("#impFamilesBasicSubDetailsTitle").html("");
   $("#impFamPancBothDtls").html("");
   $("#impFamDtlsTitle").html("");
   $("#impFamDtls").html("");
  }
    var level = $("#reportLevel").val();
	var type = '';
	var id = '';
	var publicationDateId = $("#publicationDateList").val();
	if(level == 1){
	   type = 'constituency';
	   id = $("#constituencyList").val();
	}else if(level == 2){
	  type = 'mandal';
	  id = $("#mandalField").val();
    }else if(level == 3){
	  type = 'panchayat';
	  id = $("#panchayatField").val();
	}else if(level == 4){
	  type = 'booth';
	  id = $("#pollingStationField").val();
	}
   if(buttonType == "voters"){
	var jsObj=
			{
					
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				to:voterBasicInfoFor,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	if(buttonType == "impFamilies"){
	   var jsObj1=
			{
					
				type:type,
				id:id,
				publicationDateId:publicationDateId,
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
	    var jsObj2=
			{
					
				type:reqtype,
				id:id,
				publicationDateId:publicationDateId,
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
function buildVotersInACaste(results,jsObj)
{
	$("#localCastStatsVotersTitle").html("voters Details In "+jsObj.typename+" in "+jsObj.publicationDate+" ");
	 var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true},
							
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"cast", label: "Cast",sortable:true},
							{key:"castCategory", label: " Cast Category", sortable: true}
		    	        ]; 

    
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};
		
		
	var myDataSource = new YAHOO.util.DataSource(results.votersByHouseNos);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "sNo","name","gender","age","houseNo","gaurdian","relationship","cast","castCategory"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersResultColumnDefs,myDataSource, myConfigs);


}

function buildVotersInFamily(results,hno){
    $("#impFamDtlsTitle").html("<b>Voter Details in House No : "+hno+"</b>");
     var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true},
							
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true},
							{key:"cast", label: "Cast",sortable:true},
							{key:"castCategory", label: " Cast Category", sortable: true}
		    	        ]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "sNo","name","gender","age","houseNo","gaurdian","relationship","cast","castCategory"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);

}

function buildVotersBasicInfo(votersbasicinfo,to){
   var str = '<div id="votersBasicInfoDivSub">';
   if(votersbasicinfo != null && votersbasicinfo.datapresent){
      var name = votersbasicinfo.name+'  '+votersbasicinfo.type;
      str+='<b>Voters Info in '+name+'</b>';
      str+='<div>Male Voters : '+votersbasicinfo.totalMaleVoters+'  Female Voters : '+votersbasicinfo.totalFemaleVoters+'  ' ;
	  if(votersbasicinfo.unKnowVoters != null && votersbasicinfo.unKnowVoters != 0 && votersbasicinfo.unKnowVoters != "0")
	  str+='UnKnown Voters : '+votersbasicinfo.unKnowVoters+'  ';
	  str+='Total Voters : '+votersbasicinfo.totVoters+'</div></div></br></br>';
	  if(to == "")
	   $("#votersBasicInfoDiv").html(str);
	  else if(to == "impfamilies")
	   $("#votersBasicInfoDivForImpFam").html(str);
	  else if(to == "localcaststs")
	   $("#votersBasicInfoDivForLclCastSts").html(str);
	  else if(to == "agewise")
	   $("#votersBasicInfoDivForAgeWiseDetls").html(str);
	  
	  str = '';
     if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,name,to);
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

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "name","totalMaleVoters","totalFemaleVoters","totVoters","percent"]
					};

		var impFamliesResultDataSource = new YAHOO.widget.DataTable(reqDiv, votersResultColumnDefs,myDataSource, myConfigs);

     }
   }else{
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

function buildVotersChart(chartInfo,reqTitle,to){

 // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'type');
        data.addColumn('number', 'value');
		data.addRows(chartInfo.length);
		for(var i = 0 ; i< chartInfo.length ; i++){
		var name = chartInfo[i].name+' '+chartInfo[i].type;
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


	function buildCastInfoData(myresults,jsObj)
	{

	var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
	var totalVoters = myresults.voterCastInfodetails.totalVoters;
	var totalCasts = myresults.voterCastInfodetails.totalCasts;
	var localCastStatsTabContent = '<div style="font-family: verdana;font-size: 13px;margin-left: 124px;"> Total Voters : '+totalVoters+' ';
	localCastStatsTabContent+='&nbsp;&nbsp;&nbsp; Total Casts : '+totalCasts+'</div>';
	localCastStatsTabContent_headerEl.innerHTML=localCastStatsTabContent;
	var typeName =jsObj.typename;	
		var	castIno = new Array();
		var cast = myresults.voterCastInfodetails.castVOs;
		
		
		for(var i in cast)
		{
		var castStats = 
			{
			caste : cast[i].castName,
			castePopulation : cast[i].castCount,
			malePopulation : cast[i].malevoters,
			femalePopulation : cast[i].femalevoters,
			castePercentage:cast[i].castPercentage,
			};

			castIno.push(castStats);

		constMgmtMainObj.castStatsArray =castIno; 
		}
		if(cast != '')
		{
		buildLocalCastStatisticsDataTableForAssembly(typeName);	
		}
		else{
     $("#localCastStatsTabContent_body").html("No Data Found");
   }	
	}
   function buildLocalCastStatisticsDataTableForAssembly(typeName)
	{
		
		$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
		var localCastStatsColumnDefs = [ 
		    	            
		    	            {key:"caste", label: "caste", sortable: true}, 
							{key:"castePopulation", label: "caste Population", formatter:"number", sortable: true},
		    				{key:"malePopulation", label: "male Population", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"femalePopulation", label: "female Population", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true},
							{key:"castePercentage", label: "caste Percentage", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}	
		    					    			    				
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
    var name = "";
     if(type == "panchayat"){
	    name = $("#panchayatField option:selected").text();
	 }else{
	  type = "";
	   name = $("#pollingStationField option:selected").text();
	 }
      var str ='<div id="impFamPancBothDtlstitle"><b>Voters Family details in '+name+' '+type+' in '+publicationYear+'</b></div>';
          str+=' <table id="impfamilydatatable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
          str+='     <th>SNo</th>';
          str+='     <th>House No</th>';
          str+='     <th>Members In Family</th>';
          str+='	 <th>Eldest Person</th>';
          str+='     <th>Youngest Person</th>';
          str+='     <th>Cast</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	 for(var i in result){
	   var sno = parseInt(i)+1;
	      str+='   <tr>';
          str+='     <td>'+sno+'</td>';
          str+='     <td><a href="javascript:{}" onclick="getVotersInAFamily('+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\')">'+result[i].houseNo+'</a></td>';
          str+='     <td>'+result[i].numberOfPeople+'</td>';
          str+='	 <td>'+result[i].elder+'</td>';
          str+='     <td>'+result[i].younger+'</td>';
          str+='     <td>'+result[i].cast+'</td>';
          str+='   </tr>';
	 }
          str+='  </tbody>';
          str+=' </table>';
	  
	  $("#impFamPancBothDtls").html(str);
	  
	  	$('#impfamilydatatable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null
     
	  
    ] 
		});
  }
  



 function buildTableForImpFamilesMandal(impFamilesData,name,type)
{
  var impFamiList = new Array();
  for(var i in impFamilesData){
     var data={};
	 if(impFamilesData[i].type != "Booth")
	   data["name"] = impFamilesData[i].name+" "+impFamilesData[i].type;
	  else
	    data["name"] = impFamilesData[i].name+" ";  
	 data["below3"] = impFamilesData[i].below3;
	 data["below3perc"] = impFamilesData[i].below3perc;
	 data["betwn4to6"] = impFamilesData[i].betwn4to6;
	 data["betwn4to6perc"] = impFamilesData[i].betwn4to6perc;
	 data["betwn7to10"] = impFamilesData[i].betwn7to10;
	 data["betwn7to10perc"] = impFamilesData[i].betwn7to10perc;
	 data["above10"] = impFamilesData[i].above10;
	 data["above10perc"] = impFamilesData[i].above10perc;
	 impFamiList.push(data);
  }
  
  $("#impFamilesBasicSubDetailsTitle").html(impFamilesData[0].type+" wise voters family analysis of "+name+" "+type+" in "+publicationYear+"");
  var impFamilesColumnDefs = [
    {key:"name", label: ""+impFamilesData[0].type+"", sortable: true},
    {key:"below3", label: "Below 3", formatter:"number", sortable: true},
    {key:"below3perc", label: "Below 3 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn4to6", label: "Between 4-6", formatter:"number", sortable: true},
    {key:"betwn4to6perc", label: "Between 4-6 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"betwn7to10", label: "Between 7-10", formatter:"number", sortable: true},
    {key:"betwn7to10perc", label: "Between 7-10 %", formatter:YAHOO.widget.DataTable.formatFloat, sortable: true},
    {key:"above10", label: "Above10", formatter:"number",sortable:true},
    {key:"above10perc", label: "Above10 %", formatter:YAHOO.widget.DataTable.formatFloat,sortable:true}
  ];
var impFamilesDataSource = new YAHOO.util.DataSource(impFamiList);
impFamilesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
impFamilesDataSource.responseSchema = {
fields: [{key:"name"},{key:"below3", parser:"number"},{key:"below3perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn4to6", parser:"number"},{key:"betwn4to6perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"betwn7to10", parser:"number"},{key:"betwn7to10perc", parser:YAHOO.util.DataSourceBase.parseNumber},{key:"above10", parser:"number"},{key:"above10perc", parser:YAHOO.util.DataSourceBase.parseNumber}]
};
var myConfigs = {
};
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetails", impFamilesColumnDefs,
impFamilesDataSource, myConfigs);
return {
oDS: impFamilesDataSource,
oDT: impFamilesDataTable
};

}
function impFamilesStaticTable(myresults)
{
var str='';
str+='<div class="impFamilesMainDiv" >';
str+='<div><h5><strong>';
str+=''+myresults.name+' '+myresults.type+' Family Wise Statistics in '+publicationYear+'';
str+='</strong></h5></div>';
str+='</br>';
str+='<div style="border:1px solid black">';
str+='<table>';
str+='<tr>';
str+='<th style="color:black">TotalVoters :</th>';
str+='<td>'+myresults.totalVoters+'</td>';
str+='</tr>';
str+='<tr>';
str+='<th style="color:black">TotalFamiles :</th>';
str+='<td>'+myresults.totalFamalies+'</td>';
str+='</tr>';
str+='</table>';
str+='</div>';
str+='</br>';
str+='<div>';
str+='<table class="impTableDiv">';
str+='<tr>';
str+='<th>Report</th><th>Below3</th><th>Between4-6</th><th>Between7-10</th><th>Above10</th>';
str+='</tr>';
str+='<tr>';
str+='<th>No of Familes</th>';
str+='<td>'+myresults.below3+'</td>';
str+='<td>'+myresults.betwn4to6Popul+'</td>';
str+='<td>'+myresults.betwn7to10Popul+'</td>';
str+='<td>'+myresults.above10+'</td>';
str+='<tr>';
str+='<tr>';
str+='<th>Familes %</th>';
str+='<td>'+myresults.below3perc+'%</td>';
str+='<td>'+myresults.betwn4to6perc+'%</td>';
str+='<td>'+myresults.betwn7to10perc+'%</td>';
str+='<td>'+myresults.above10perc+'%</td>';
str+='<tr>';
str+='</table>';
str+='</div>';
$("#impFamilesBasicDetails").html(str);
}

function buildImpFamilesChart(chartInfo){
// Create the data table.

var data = google.visualization.arrayToDataTable([
          ['Task', 'Percentage'],
          ['Below 3',  chartInfo.below3perc],
          ['Between 4-6', chartInfo.betwn4to6perc],
          ['Between 7-10',  chartInfo.betwn7to10perc],
          ['Above 10', chartInfo.above10perc]
        ]);

// Set chart options
var title = " Family wise detail chart of "+chartInfo.name+" "+chartInfo.type+" in "+publicationYear+"";
var options = {'title':title,
'width':800,
'height':280};
// Instantiate and draw our chart, passing in some options.
var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoSubChartDiv'));
chart.draw(data, options);
}
