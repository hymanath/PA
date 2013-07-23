function getVotersCount()
{

		var jsObj=
			{
			publicationDateId : publicationDateId,
			customVoterGroupId :customVoterGroupId,
			task:"getVotersCount"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getTotalVotersForCustomGroup.action?"+rparam;						
		callAjax(jsObj,url);
}
function getInfluencingPeopleCount(id)
{
	var jsObj=
			{
		customVoterGroupId:id,
		
		publicationDateId:publicationDateId,
		
		task:"getInfluencingPeopleCount"
	};
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj);
		
    var url1 = "getInfluencingPeopleCountForCustomGroupAction.action?"+rparam1;
	callAjax(jsObj,url1);
}
	
function getVoterInfoForACustomGroup()
{

 $("#ajaxImageDiv").css('display','none');
	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});
	$('#atterHeadingDiv').show();
	$('#impFamilySelectedDetails').show();
	$('#imgDescriptionDiv').show();
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("firstName");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	{
		var str ='';
		var id=oRecord.getData("voterIds");
		var isInfluencePerson=oRecord.getData("isInfluencePerson");
		var isCadere = oRecord.getData("isCadrePerson");
		var isPolitician = oRecord.getData("isPoliticion");
		if(isInfluencePerson == true)
		{
			str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
		}
		if(isCadere == true)
		{
			str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
		}
		if(isPolitician == true)
		{
			str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
		}
		elLiner.innerHTML =str;
	}
YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var voterId= oRecord.getData("voterIds");


		str+='<a href="javaScript:{getInfluencePeopleOfAnUser('+voterId+');}">Click here</a>';
		
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},
{key:"name", label: "Name",width:100, sortable: true},
{key:"voterIDCardNo", label: "Voter ID",width:120,sortable: true},
{key:"partNo", label: "Booth No",width:30, sortable:true},
{key:"gender", label: "Gen", width:15, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "HNO",width:20, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:70,sortable:true},
{key:"Type", label: "Type", width:60,formatter:YAHOO.widget.DataTable.Type},

{key:"mobileNo",label:"MobileNo",sortable:true,width:50},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}


];

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetailsForACustomGroupAjax.action?customvoterGroupId="+customVoterGroupId+"&publicationId="+8+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [{key:"serialNo", parser:"number"},
"name", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
};
var myConfigs = {
initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 100 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
pResults = oResponse.results;
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}

return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};


}


function checkForAttributesToDisplay(){
	confTrue = true;
	reqfields = "";
	reqfieldsArr = new Array();
	$('.attributeTypeClassIni1').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
	$('.notRequiredAttrClass').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfields = reqfields+","+ids[0];
		    }
          });
		   if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);
		  
		
			var jsObj=
			{
				
				customVoterGroupId:customVoterGroupId,
				publicationId: publicationDateId,
				reqfields:reqfields,
				startIndex : 0,
				maxIndex : 100,
				sort : "voterId",
				dir : "asc",
				task:"getVoterData"
			}
			
		
		   buildVotersInFamilyWithRetrievedResults();

}



function buildVotersInFamilyWithRetrievedResults(){
	

	$('#votersByLocationTabContentDiv_body').show();
	var x = 1;
	
	    YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
			{
			var vId = oRecord.getData("voterId");
			var vName = oRecord.getData("name");
			var vBoothId = oRecord.getData("boothId");
			 
			elLiner.innerHTML ='<a href="javascript:{};" style="cursor:pointer;" onclick="openProblemEditForm('+vId+','+vBoothId+');">'+vName+'</a>';
				
			} 
         
		  
YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var name = oRecord.getData("name");
var influencePerson=oRecord.getData("influencePerson");
str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'cadre\',\''+name+'\');">Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};"  onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'influencingPeople\',\''+name+'\');">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{getInfluencePeopleOfAnUser('+id+')};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" onclick="checkForVoter('+id+',\'candidate\',\''+name+'\');">Add To Politician</a>';
str += ' </li>';
str += ' </ul>';
str += ' </li>';
str += ' </ul>';
elLiner.innerHTML =str;
}
		 
YAHOO.widget.DataTable.Type = function(elLiner, oRecord, oColumn, oData)
	    {
			var str ='';
			var id=oRecord.getData("voterId");
			var isInfluencePerson=oRecord.getData("isInfluencePerson");
			var isCadere = oRecord.getData("isCadrePerson");
			var isPolitician = oRecord.getData("isPoliticion");
			if(isInfluencePerson == true)
			{
				str+='<img title="InfluencingPeople" alt="InfluencePerson" src="./images/icons/influencing.png"/>';
			}
			if(isCadere == true)
			{
				str+='<img title="Cadre" alt="CadrePerson" src="./images/icons/cadre.png"/>';
			}
			if(isPolitician == true)
			{
				str+='<img title="Politician" alt="Politicion" src="./images/icons/politican.png"/>';
			}
			elLiner.innerHTML =str;
	    }
		
	YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{ 
			confTrue = false;
		    var check = false;
			var vId = oRecord.getData("voterId");
			var vBoothId = oRecord.getData("boothId");
            for(var i in selectedVotersArr){
			if(selectedVotersArr[i].boothId == vBoothId && selectedVotersArr[i].voterId == vId ){
				 check = true;
			 }
		    }		
			
			if(check)
			  elLiner.innerHTML="<input type='checkbox' checked='checked' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";
            else
              elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+vId+"'/><input type='hidden' class='selectedBoothId' value='"+vBoothId+"'/>";			
		  };
	
	for(var i in reqfieldsArr)
	{
		var id3 = reqfieldsArr[i].split(",");
		YAHOO.widget.DataTable[""+id3[0]] = function(elLiner, oRecord, oColumn, oData) 
		{ 			
			var ids={};
			ids[0]=oColumn.field;
			var val = "";
				var categ = oRecord.getData("categoriesList");
				  for(var i in categ){
					if(categ[i].categoryValuesId == ids[0])
					   if(categ[i].name != null)
						val = categ[i].name;
				  }
				
				elLiner.innerHTML=val;			
		  };
		 
		 }


		var votersResultColumnDefs = 
	 [ 		    	             
		{key:"serialNo", label: "SNo",width:15,sortable: true,formatter:"number"},  	            
		{key:"name", label: "Name", sortable: true,width:100},
		{key:"houseNo", label: "HNO",sortable:true,width:20},
		{key:"voterId", label: "Voter Card Id",sortable:true,width:100},
		{key:"partNo", label: "Booth No",sortable:true,width:70}
	]; 
	if($("#ageId").is(':checked'))
	{
		obj = {key:"age",label: "Age",sortable: true,width:15};
		votersResultColumnDefs.push(obj);	
	}
	if($("#genderId").is(':checked'))
	{
		obj = {key:"gender",label: "Gender",sortable: true,width:35};
		votersResultColumnDefs.push(obj);
	}
	if($("#casteId").is(':checked'))
	{
		obj = {key:"casteName",label: "Caste",width:50};
		votersResultColumnDefs.push(obj);
	}
	if($("#partyId").is(':checked'))
	{

		obj = {key:"partyName",label: "Party",width:40};
		votersResultColumnDefs.push(obj);
	}
	if($("#typeId").is(':checked'))
	{
		obj = {key:"Type",label: "Type",formatter:YAHOO.widget.DataTable.Type,width:70};
		votersResultColumnDefs.push(obj);
	}
	if($("#actionsId").is(':checked'))
	{
		obj = {key:"Actions",label: "Actions",formatter:YAHOO.widget.DataTable.ActionLink};
		votersResultColumnDefs.push(obj);
	}

		 for(var i in reqfieldsArr){
		    var ids1 = reqfieldsArr[i].split(",");
		     
		   obj = {
				key:""+ids1[0], label: ""+ids1[1],formatter:YAHOO.widget.DataTable[""+ids1[0]]
					};
					votersResultColumnDefs.push(obj);
					
		 }
		var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDataForCustomGroup.action?customVoterGroupId="+customVoterGroupId+"&reqfields="+reqfields+"&publicationId=8&");
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		votersByLocBoothDataSource.responseSchema = {
		resultsList: "votersList",
		fields : ["voterId","name","houseNo","age","gender","casteName","partyName","isCadrePerson","isInfluencePerson","isPoliticion","categoriesList","partNo","serialNo","voterIds"],
		metaFields: {
		totalRecords: "totalVoters" // Access to value in the server response
		}
		};

		
//end
		var myConfigs = {
		initialRequest: "sort=initial&dir=asc&startIndex=0&results=100", // Initial request for first page of data
		dynamicData: true, // Enables dynamic server-driven data
		sortedBy : {key:"serialNo", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
		   paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit 
						})  // Enables pagination
		};
		if(confTrue){
		 var stindx = 0;
		 try{
		stindx =  (parseInt($.trim($('.yui-pg-current-page').html()))-1)*100;
		  //stindx = 0;
		  myConfigs["paginator"] = new YAHOO.widget.Paginator({ 
						rowsPerPage    : limit ,
						initialPage:$('.yui-pg-current-page').html(),
						totalRecords:totalReq
						}) 
		  }catch(e){}
		 myConfigs["initialRequest"] ="sort=name&dir=asc&startIndex="+stindx+"&results="+limit+"&initialPage ="+$('.yui-pg-current-page').html();
		}
		var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
		votersResultColumnDefs, votersByLocBoothDataSource, myConfigs);
        
		votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		totalReq = oResponse.meta.totalRecords;
		return oPayload;
		}


		return {
		oDS: votersByLocBoothDataSource,
		oDT: votersByLocBoothDataTable
		};
    }




function buildVoterTypeDetails(myResults,jsObj)
{
	
	var customGroupId = 1;
	var publicationId = 8;
	if(myResults != null)
	{
		 $('#voterTypeId').html('<span>Influencing People : </span><span class="btnName" onclick="getPeopleData('+customGroupId+',\''+publicationId+'\',\'InfluencePeople\' )"><b style="color:navy;">'+myResults.influencePeopleCount+'</b></span><span style="margin-left: 10px;">Cadre : </span><span class="btnName"  onclick="getPeopleData('+customGroupId+',\''+publicationId+'\',\'Cadre\' )"><b style="color:navy;">'+myResults.cadreCount+'</b></span><span style="margin-left: 10px;">Politican : </span><span class="btnName" onclick="getPeopleData('+customGroupId+',\''+publicationId+'\',\'Politician\' )"><b style="color:navy;">'+myResults.politicianCount+'</b></span>'); 
		
	}
	//getVoterDetails();
}

function getCasteWiseCustomVotersCount()
{
  var jsObj=
  {
	customVoterGroupId:customVoterGroupId,
	task:"getCasteWiseCustomVotersCount"
  };

  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "getCasteWiseCustomVotersCountAction.action?"+rparam+"&save=";	
  callAjax(jsObj,url);
}

function callAjax(jsObj,url){
var myResults;

		 var callback = {			
				   success : function( o ) {
					try {

						  myResults =  YAHOO.lang.JSON.parse(o.responseText);
						   if(jsObj.task == "getVotersCountForPartyByCustomGroup"){
							   buildVotersCountForPartyByCustomGroup(myResults);
						   
						   }
						 else  if(jsObj.task == "getCasteWiseCustomVotersCount")
			 				 buildCasteWiseCustomVotersCount(myResults,jsObj);
			 			else if(jsObj.task=="getAgeWiseCustomVotersInGroup"){
			 					 buildAgeWiseInGroupTable(myResults);
						}else if(jsObj.task=="CustomVoterImpFamilies"){		CustomVoterImpFamiliesTable(myResults,jsObj);
						}
					}catch (e) {

					}
				   },
						scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		           };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
}


var castesArr=[];
	var totalVotersArr=[];
	var maleVotersArr=[];
	var femaleVotersArr=[];
	var castPercentageArr=[];
	var castArray=[];
		
	function buildCasteWiseCustomVotersCount(results,jsObj)
	{
		
		$("#casteWiseVotersCountInnerDiv").html('');
		if(results == null || results.length == 0)
		{
			$("#casteWiseVotersCountInnerDiv").html('No Data Found.');
			return;
		}
		var str = '';
		str +='<table class="table table-bordered table-striped table-hover">';
		str +='<tr>';
		str +='<th>Caste</th>';
		str +='<th>Caste Category</th>';
		str +='<th>Male Voters</th>';
		str +='<th>Female Voters</th>';
		str +='<th>Voters</th>';
		str +='<th>Caste Percentage</th>';
		str +='</tr>';
		for(var i in results)
		{
		  str +='<tr>';
		  str +='<td><a style="font-weight:bold;" href="javascript:{}" onclick="getCasteWiseCustomVoters('+results[i].casteStateId+','+results[i].casteId+',\''+results[i].castName+'\',\''+results[i].casteCategoryName+'\')">'+results[i].castName+'</a></td>';
		  str +='<td>'+results[i].casteCategoryName+'</td>';
		  str +='<td>'+results[i].maleVoters+'</td>';
		  str +='<td>'+results[i].femaleVoters+'</td>';
		  str +='<td>'+results[i].totalVoters+'</td>';
		  str +='<td>'+results[i].castePercentage+'</td>';
		  str +='</tr>';
		}
		str +='</table>';
		$("#casteWiseVotersCountInnerDiv").html(str);
		
		castArray = results;
		
		buildGraphBySlide(castArray);
		
	}


 var casteRange;	
$(function() {
$( "#slider" ).slider({
value:1,
min: 0,
max: 40,
step: 1,
slide: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
},
change: function( event, ui ) {
$( "#amount" ).val( "Percentage of Voters Caste: " + ui.value +" %");
casteRange=ui.value;
buildGraphBySlide(castArray,casteRange);
}
});
casteRange=$( "#amount" ).val( "Percentage of Voters Caste: " + $( "#slider" ).slider( "value" ) +" %");
casteRange=$( "#slider" ).slider( "value" );
});



function buildGraphBySlide(castArray,casteRange)
{
	if(casteRange == null)
		casteRange = 1;

	    castesArr=[];
		totalVotersArr=[];
		maleVotersArr=[];
		femaleVotersArr=[];
		castPercentageArr=[];

		for(var i in castArray)
		{
		  if(castArray[i].castePercentage >=casteRange)
		  {
			 castesArr.push(castArray[i].castName);
			 totalVotersArr.push(castArray[i].totalVoters);
			 maleVotersArr.push(castArray[i].maleVoters);
			 femaleVotersArr.push(castArray[i].femaleVoters);
			 castPercentageArr.push(castArray[i].castePercentage);
		  }
		}
	 buildCasteWiseCustomVotersCountGraph(castesArr,totalVotersArr,maleVotersArr,femaleVotersArr,castPercentageArr);

	}

  function buildCasteWiseCustomVotersCountGraph(cs,tv,mv,fv,cp)
  {

   $('#casteWiseVotersCountGrapDiv').highcharts({
            chart: {
                zoomType: 'xy',
				marginRight: 130,
                marginBottom: 100,
				width:890,height:390
            },
			
            title: {
                text: 'Caste Wise Analysis'
            },
            
            xAxis: [{
                categories: cs,
				labels: {
                                align:'right',
                                style: {
                                      cursor: 'pointer',
                                      fontSize: '14px',
                                      //fontWeight:'bold'
                                },
                                rotation:300, 
                            } 
            }],
            yAxis: [{ // Primary yAxis
				min: 0,
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: 'caste Percentage ',
                    style: {
                        color: '#89A54E'
                    }
                },
                opposite: true
    
            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Caste Percentage/Total Voters',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#4572A7'
                    }
                }
    
            }, { // Tertiary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Total Voters',
                    style: {
                        color: '#AA4643'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#AA4643'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 600,
                verticalAlign: 'top',
                y: 40,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [ {
                name: 'Total Voters',
                type: 'spline',
                color: '#AA4643',
                yAxis: 2,
                data: tv,
               /* marker: {
                    enabled: false
                },*/
                //dashStyle: 'shortdot',
                tooltip: {
                    valueSuffix: ''
                }
    
            }, {
                name: 'Caste Percentage',
                color: '#89A54E',
                type: 'spline',
                data: cp,
                tooltip: {
                    valueSuffix: ''
                }
            }]
        });
		$('tspan:last').hide();
  }
  




 function getCasteWiseCustomVoters(casteStateId,casteId,casteName,casteCategoryName)
  {
	
	var urlstr = "getCasteWiseCustomVotersDetails.action?casteStateId="+casteStateId+"&casteId="+casteId+"&casteName="+casteName+"&casteCategoryName="+casteCategoryName+"&customVoterGroupId="+customVoterGroupId+"&";

	var browser1 = window.open(urlstr,"casteWiseDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
	browser1.focus();
  }
  

//For Part wise VotersInfo
  function getVotersCountForPartyByCustomGroupId(){
	var jsObj=
			{
				custGroupId:customVoterGroupId,
				task:"getVotersCountForPartyByCustomGroup"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForPartyByCustomGroupIdAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function CustomVoterImpFamiliesTable(myresults,jsObj){
	var value=0;
	var str='';
	var type = jsObj.type;	
	str+='<div class="impFamilesMainDiv row">';
	
		str += '<div class="span3" style="margin-left: 30px;"><ul class="FamiliyList"> <li> <div style="width:68%;float:left;">Total Voters </div> <span style="clear:left;">: '+myresults.totalVoters+'</span></li>';

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
	str+='<table id="FamilyTable" class="table table-bordered table-hover"  style="margin-top: 15px; font-size: 13px;">';
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
	
}

  function buildVotersCountForPartyByCustomGroup(myResults){

		var data = myResults.partyWisevoterCastInfoVOList;
		if(data == null || data.length == 0){
          $("#partyWiseVotersDiv").html("<font style='font-weight:bold;'>NO DATA AVAILABLE</font>");
			    return;
		}

		var str1 = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;">';
		str1 += '<span>Party Assigned Voters : '+myResults.partyWiseAssignedVoters+'</span>';
		str1 += '<span style="padding-left:40px;">Party Not Assigned Voters : '+myResults.partyWiseNotAssignedVoters+'</span>';
		str1 += '<br><br>';
		str1 += '</div>';
		$('#partyWiseAssignedVotersDiv').html(str1);

		var str =' <table id="partyWiseVotersJqTable" cellpadding="0" cellspacing="0" border="0"  style="border:2px solid black">';
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

			  for(var i in data){
		      str +='   <tr>';
			  str +='		<td><a href="partyPageAction.action?partyId='+data[i].partyId+' ">'+data[i].partyName+'</td>';
			  str +='		<td>'+data[i].totalVoters+'</td>';
			  str +='		<td>'+data[i].maleVoters+'</td>';
			  str +='		<td>'+data[i].femaleVoters+'</td>';
			  str +='		<td>'+data[i].votesPercent+'</td>';
	          str+='   </tr>';
		   }
	          str+='  </tbody>';
	          str+=' </table>';
		  
		  $("#partyWiseVotersDiv").html(str);
		  
		  	$('#partyWiseVotersJqTable').dataTable({
			"aaSorting": [[ 1, "asc" ]],
			"iDisplayLength": 15,
			"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
			//"bFilter": false,"bInfo": false
			  "aoColumns": [null,null,null,null,null
	     
		  
	    ] 
			});
	}