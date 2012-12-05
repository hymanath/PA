function buildOuterView()
		{
		var divEle = document.getElementById("votersouterDiv");	
		var str='';
		
		str+='<fieldset>';
		str+='<div style="color:#707070;font-weight:bold;font-size:12px;">Please select from the following list boxes to view detailed statistics by Assmbly/mandal/Panchayat/Polling station level</div>';
		str+='<P >Fields marked with <font class="requiredFont"> * </font> are mandatory</P>';
		str+='<div id="locationAlertMsg" align="left"></div>';

		str +='<div class="control-group form-horizontal" >';


		str+='<div id="reportLevelDiv" style="width:80%;padding-top:10px;padding-bottom:10px;margin-left:auto;margin-right:auto;">';
		
		
		
		str+='Select Report Level : <select id="reportLevel" class="selectWidth" style="margin-left:21px;" name="reportLevel" onchange="showReportLevel(this.options[this.selectedIndex].value);">';
		str+='<option value=1>Constituency</option>';
		str+='<option value=2>Mandal</option>';
		str+='<option value=3>Panchayat</option>';
		str+='<option value=4>PollingStation</option>';
		str+='</select>';
		
		str+='</div>';
		

		str+='<div id="ConstituencyDiv" style="width:80%;padding-top:10px;padding-bottom:10px;margin-left:auto;margin-right:auto;">';
		
		
		
		str+='Select Constituency : <select id="constituencyList" class="selectWidth" style="margin-left:17px;" name="constituencyList" onchange="getPublicationDate();getMandalList(\'mandalField\');">';
		for(var i in locationDetails.constituencyArr)
		{
			str+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		} 
		str+='</select>';
		
		str+='Select Publication Date : <select id="publicationDateList" class="selectWidth" style="margin-left:17px;width:175px;" name="publicationDateList">';
		str+='</select>';
		str+='</div>';
		
		
		
		str+='<div id="mandalDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">';
		
		
		
		str+='Select Mandal : <select id="mandalField" class="selectWidth" style="margin-left:49px;" name="state" onchange="getPanchayatList(\'panchayat\',\'panchayatField\');getPanchayatList(\'pollingstation\',\'pollingStationField\');"></select></div>';
		
	
	
		

		str+='<div id="panchayatDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">';
		
		str+='Select Panchayat :<select id="panchayatField" class="selectWidth" name="state" style="margin-left:32px;"></select></div>';
	
		
		
		
		str+='<div id="pollingStationDiv" style="width:80%;padding-top:10px;padding-bottom:10px;display:none;margin-left:auto;margin-right:auto;">';
		
	
		str+='Select PollingStation : <select id="pollingStationField" class="selectWidth" name="state" style="margin-left:15px;"></select></div>';
	


		str+='</div>';
		divEle.innerHTML= str;
		
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
			getPanchayatList('pollingstation','pollingStationField');
			
		}
	}
	

function buildVotersByLocBoothDataTable(id)
{

var boothId = $('#'+id).val();


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

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&");
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
		document.getElementById('votersMainOuterDiv1').style.display='block';
		document.getElementById('votersMainOuterDiv2').style.display='none';
		document.getElementById('votersMainOuterDiv3').style.display='none';
		document.getElementById('votersMainOuterDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		
		
	}
	
	function showLocalCastDiv()
	{
		var LocalCastDiv = document.getElementById('LocalCastDiv');
		
		document.getElementById('votersMainOuterDiv1').style.display='none';
		document.getElementById('votersMainOuterDiv2').style.display='block';
		document.getElementById('votersMainOuterDiv3').style.display='none';
		document.getElementById('votersMainOuterDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		
	}

	function showVotersDiv()
	{
		var VotersDiv = document.getElementById('votersDiv');
		
		document.getElementById('votersMainOuterDiv1').style.display='none';
		document.getElementById('votersMainOuterDiv2').style.display='none';
		document.getElementById('votersMainOuterDiv3').style.display='block';
		document.getElementById('votersMainOuterDiv4').style.display='none';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #F61D50"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #0063DC"});
		
	}

	function showAgeDiv()
	{
		var VotersDiv = document.getElementById('ageWiseInfoDiv');
		
		document.getElementById('votersMainOuterDiv1').style.display='none';
		document.getElementById('votersMainOuterDiv2').style.display='none';
		document.getElementById('votersMainOuterDiv3').style.display='none';
		document.getElementById('votersMainOuterDiv4').style.display='block';
		$("#importantFamiliesId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#localCaststatId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#votersId").css({"background":"none repeat scroll 0 0 #0063DC"});
		$("#ageWiseId").css({"background":"none repeat scroll 0 0 #F61D50"});
		

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
									buildVotersBasicInfo(myResults);
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
    $("#constituencyList").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 1 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getvotersBasicInfo();
	});
	$("#mandalField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 2 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getvotersBasicInfo();
	});
	$("#panchayatField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 3 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getvotersBasicInfo();
	});
	$("#pollingStationField").live("change",function(){
	   if($(this).val() != 0 && $("#reportLevel").val() == 4 && $("#publicationDateList option").length > 0 && $("#publicationDateList").val() != 0)
	      getvotersBasicInfo();
	});
    $("#publicationDateList").live("change",function(){
	   if($(this).val() != 0 ){
	     if($("#reportLevel").val() == 1 && $("#constituencyList option").length > 0 && $("#constituencyList").val() != 0)
	       getvotersBasicInfo();
		 else if($("#reportLevel").val() == 2 && $("#mandalField option").length > 0 && $("#mandalField").val() != 0 )
		   getvotersBasicInfo();
		 else if($("#reportLevel").val() == 3 && $("#panchayatField option").length > 0 && $("#panchayatField").val() != 0 )
		   getvotersBasicInfo();
		 else if($("#reportLevel").val() == 4 && $("#pollingStationField option").length > 0 && $("#pollingStationField").val() != 0 )
		   getvotersBasicInfo();
		}
	});
});
function getvotersBasicInfo(){
   $("#votersBasicInfoDiv").html("");
   $("#votersBasicInfoSubChartDiv").html("");
   $("#votersBasicInfoSubDiv").html("");
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
	var jsObj=
			{
					
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;						
		callAjax(jsObj,url);
}

function buildVotersBasicInfo(votersbasicinfo){
   var str = '<div id="votersBasicInfoDivSub">';
   if(votersbasicinfo != null && votersbasicinfo.datapresent){
      var name = votersbasicinfo.name+'  '+votersbasicinfo.type;
      str+='<b>Voters Info in '+name+'</b>';
      str+='<div>Male Voters : '+votersbasicinfo.totalMaleVoters+'  Female Voters : '+votersbasicinfo.totalFemaleVoters+'  ' ;
	  if(votersbasicinfo.unKnowVoters != null && votersbasicinfo.unKnowVoters != 0 && votersbasicinfo.unKnowVoters != "0")
	  str+='UnKnown Voters : '+votersbasicinfo.unKnowVoters+'  ';
	  str+='Total Voters : '+votersbasicinfo.unKnowVoters+'</div></div>';
	  $("#votersBasicInfoDiv").html(str);
	  
	  str = '';
     if(votersbasicinfo.subLevelExists && votersbasicinfo.votersInfoForMandalVOList != null && votersbasicinfo.votersInfoForMandalVOList.length > 0){
       buildVotersChart(votersbasicinfo.votersInfoForMandalVOList,name);
	   
  
  var votersResultColumnDefs = [ 		    	             
		    	            
							{key:"name", label: votersbasicinfo.votersInfoForMandalVOList[0].type, sortable: true},
		    	           	{key:"totalMaleVoters", label: "Male Voters", sortable: true},
							
							{key:"totalFemaleVoters", label: "Female Voters", sortable: true},
		    				{key:"totVoters", label: "Total Voters",sortable:true},
							{key:"percent", label: votersbasicinfo.votersInfoForMandalVOList[0].type+" % Share", sortable: true}
		    	        ]; 
	var newsResultDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList); 
	


    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(votersbasicinfo.votersInfoForMandalVOList);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "name","totalMaleVoters","totalFemaleVoters","totVoters","percent"]
					};

		var newsResultDataSource = new YAHOO.widget.DataTable("votersBasicInfoSubDiv", votersResultColumnDefs,myDataSource, myConfigs);

     }
   }else{
     $("#votersBasicInfoDiv").html("<div id='votersBasicInfoDivSub' style='font-weight:bold;'>No Data Found</div>");
   }
}

function buildVotersChart(chartInfo,reqTitle){

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
                       'width':420,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('votersBasicInfoSubChartDiv'));
        chart.draw(data, options);




}	