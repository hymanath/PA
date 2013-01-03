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
			getPanchayatList('pollingstationByPublication','pollingStationField');
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
{key:"firstName", label: "Name", sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&publicationId="+publicationDateId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType"],
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
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&panchaytId="+panchaytId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType"],
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
	}


	function getPanchayatList(checkedele,selectedEle)
	{
		var mandalId=document.getElementById("mandalField");
		var name=mandalId.options[mandalId.selectedIndex].name;
		var value1=mandalId.options[mandalId.selectedIndex].value;
		var value = value1.substring(1);
		var publicationValue = $('#publicationDateList').val();
		var alertEl = document.getElementById("AlertMsg");
		alertEl.innerHTML = '';
		var selectname = mandalField.options[mandalField.selectedIndex].text;
		var flag= selectname.search("MUNCIPALITY");
		 if(value1 == 0)
		{
			alertEl.innerHTML ='<P>Please Select Mandal</P>';
			return;
		}
		if(flag != -1)
		{
		document.getElementById('panchayatDiv').style.display='none';
		document.getElementById('pollingStationDiv').style.display='none';
		}
		
		if(flag == -1)
		{
		var jsObj=
			{
					
				selected:value,
				checkedele:checkedele,
				selectedEle:selectedEle,
				flag:flag,
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
							}catch (e) {}  
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
$(document).ready(function(){
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
		  { 
		    //getBasicInfo();
		  }
			
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
		var validflag= mandalText.search("MUNCIPALITY");
		 type = 'booth';
		 id = $("#pollingStationField").val();
		 typename = $('#pollingStationField :selected').text();
		 if(constituencyValue == 0 || constituencyValue == null)
			{
				str +='Please Select Constituency';
				flag =false;
			}
			else if(validflag != -1)
			{
				str +='There are No Booths,Select Other Report Level to view Report';
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
		str +='<th>Total Voters</th>';
		str +='<th>Caste Population</th>';
		str +='<th>Male Population</th>';
		str +='<th>Female Population</th>';
		str +='<th>CastePercentage</th>';
		
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
var level = $("#reportLevel").val();
if(level == 3)
var typename = $('#panchayatField :selected').text()+ ' Panchayat ';
if(level == 4)
var typename = $('#pollingStationField :selected').text();
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
	 $("#votersByLocationTabContentDiv_body").html("");
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
	var validflag= mandalText.search("MUNCIPALITY");

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
//console.log(results);
	$("#localCastStatsVotersTitle").html(" "+jsObj.caste+" Caste voters Details In " +jsObj.typename+" in "+jsObj.publicationDate+" ");
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var boothId=oRecord.getData("boothId");
		var id=oRecord.getData("voterId");
		var name = oRecord.getData("name");
		elLiner.innerHTML ='<a onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
	 var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,
							formatter:YAHOO.widget.DataTable.NameLink},
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
						 fields : [ "sNo","name","gender","age","houseNo","gaurdian","relationship","cast","castCategory","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersResultColumnDefs,myDataSource, myConfigs);


}


function buildVotersInFamily(results,hno){
//console.log(results);
    $("#impFamDtlsTitle").html("<b>Voter Details in House No : "+hno+"</b>");
	YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("voterId");
		var boothId=oRecord.getData("boothId"); 
		var name = oRecord.getData("name");
		
		elLiner.innerHTML ='<a onclick=" openProblemEditForm('+id+','+boothId+');">'+name+'</a>';
		
	}
     var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							{key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							{key:"gaurdian", label: "Guardian Name",sortable:true},
							{key:"relationship", label: "Relationship",sortable:true}
						]; 

    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "sNo","name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"]
					};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);

}

function buildCastInfoData(myresults,jsObj)
{
	var result = myresults.voterCastInfodetails;
	var ajaxImageDiv = document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
	var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
	var totalVoters = result.totalVoters;
	var totalCasts = result.totalCasts;
	
	var localCastStatsTabContent = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"> Total Voters : '+totalVoters+'&nbsp;&nbsp;&nbsp;';
	localCastStatsTabContent += 'Total Casts : '+totalCasts+'<br><br>';
	localCastStatsTabContent += '<span>Caste Assigned Voters : '+result.maleVoters+'</span>';
	localCastStatsTabContent += '<span style="padding-left:40px;">Caste Not Assigned Voters : '+result.femaleVoters+'</span>';
	localCastStatsTabContent += '<br><br>';

	for(var i=0;i<result.castCategoryWiseVotersList.length;i++)
		localCastStatsTabContent += '<span style="padding-left:25px;">'+result.castCategoryWiseVotersList[i].name+' Voters : '+result.castCategoryWiseVotersList[i].id+'</span>';

	localCastStatsTabContent += '</div>';
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
			castePopulation : cast[i].totalVoters,
			malePopulation : cast[i].maleVoters,
			femalePopulation : cast[i].femaleVoters,
			castePercentage:cast[i].votesPercent,
			};
		castIno.push(castStats);
		constMgmtMainObj.castStatsArray =castIno; 
		}

		if(cast != '' && type =='booth')
		{
		buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId);	
		}
		else if(cast != '' && type!='booth')
		{
		buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId);	
		}
		else{
		$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
		$("#localCastStatsTabContent_body").html("No Data Found");
		}
   
}
   function buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId)
	{
	
	$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
	 var castArray = constMgmtMainObj.castStatsArray;
	 
         var str =' <table id="localCastStatsJqTable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
          str+='     <th>Caste</th>';
		  str+='     <th>Caste Population</th>';
          str+='     <th>Male Population</th>';
          str+='     <th>Female Population</th>';
          str+='	 <th>Caste Percentage</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	   for(var i in castArray){
	      str +='   <tr>';
		  str +='		<td>'+castArray[i].caste+'</td>';
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
		"aaSorting": [[ 1, "desc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null
     
	  
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
			};  

		}
  function  buildFamilyMembers(result,publicationDateId,type){

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
          str+=' <table id="impfamilydatatable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
          str+='     <th>SNo</th>';
		  str+='     <th>Booth</th>';
          str+='     <th>House No</th>';
          str+='     <th>Members In Family</th>';
          str+='	 <th>Eldest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='     <th>Youngest Person</th>';
		  str+='	 <th>Gender</th>';
		  str+='	 <th>Age</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	 for(var i in result){
	   var sno = parseInt(i)+1;
	      str +='   <tr>';
          str +='		<td>'+sno+'</td>';
		  str +='		<td>'+result[i].boothName+'</td>';
          str +='		<td><a href="javascript:{}" onclick="getVotersInAFamily('+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\')">'+result[i].houseNo+'</a></td>';
          str +='		<td>'+result[i].numberOfPeople+'</td>';
          str +='		<td>'+result[i].elder+'</td>';
		  str +='		<td>'+result[i].elderGender+'</td>';
		  str +='		<td>'+result[i].elderAge+'</td>';
          str +='		<td>'+result[i].younger+'</td>';
		  str +='		<td>'+result[i].youngerGender+'</td>';
		  str +='		<td>'+result[i].youngerAge+'</td>';
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
		  "aoColumns": [null,null,null,null,null,null,null,null,null,null
     
	  
    ] 
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
	 impFamiList.push(data);
  }
  
  $("#impFamilesBasicSubDetailsTitle").html(impFamilesData[0].type+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
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
if(type == "constituency" || type == "Mandal/Tehsil")
	{
	$("#NoteDiv").css("display","block"); 
	$("#NoteDiv").html('<font style="font-family:verdana;font-size:12px;"> <strong>Note : </strong> To View Family wise Voter Details Select Report Level Panchayat/Polling Station</font>');
	}
}

function impFamilesStaticTable(myresults,jsObj)
{
	var str='';
	var type = jsObj.type;
	str+='<div class="impFamilesMainDiv" >';

	$("#impFamiliesTitle").html(" "+jsObj.typename+" Family Wise Statistics in "+publicationYear+"");
	str+='</br>';

	str += '<div style="font-family: verdana;font-size: 13px;margin-left: 2px;font-weight:bold;"> Total Voters : '+myresults.totalVoters+' ';
	str+='&nbsp;&nbsp;&nbsp; Total Families : '+myresults.totalFamalies+'</div>';
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

	if(myresults.betwn4to6Popul != null)
		str+='<td><b>'+myresults.betwn4to6Popul+'</td></b>';
	else
		str+='<td><b>'+0+'</td></b>';

	if(myresults.betwn7to10Popul != null)
		str+='<td><b>'+myresults.betwn7to10Popul+'</td></b>';
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
	else if(validflag != -1)
		{
				str +='There are No Booths In Muncipality,Select Other Report Level to view Report';
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
