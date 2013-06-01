
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
	function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
		callAjax(jsObj,url);

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
{key:"firstName", label: "Name",width:100, sortable: true},
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
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","partNo"],

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


function buildVoterToSelectedType(result,jsObj)
{
	if(jsObj.type == "influencingPeople")
	{
		if(result.resultCode == 1)
		{
			addInfluencingPeople(jsObj.voterId,jsObj.name);
		}
		else 
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Influencing People</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "cadre")
	{
		if(result.resultCode == 1)
		{
			openRegistrationForm(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a Cadre</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
	if(jsObj.type == "candidate")
	{
		if(result.resultCode == 1)
		{
		addToPolitician(jsObj.voterId,jsObj.name);
		}
		else
		{
			$('#errorMessageDiv').html('<b style="color:red">Voter is already added as a politician</b>');
			$('#errorMessageDiv').show().delay("2000").hide('slow');
		}
	}
	
}

function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}

function checkForVoter(voterId,type,name)
{
	var jsObj = {
			voterId:voterId,
			type : type,
			name : name,
			task:"ckeckForVoterId"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "ckeckForVoterIdAction.action?"+rparam;
	callAjax(jsObj, url);
}


function addInfluencingPeople(voterId,name)
{
	var type='edit';
	var urlStr = "influencingPeopleAction.action?windowTask="+type+"&voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
}


function openRegistrationForm(voterId,name)
{
	var task = "update_existing";
	var urlStr = "cadreRegisterPageAction.action?voterId="+voterId+"&windowTask="+task+"&name="+name;
	var updateBrowser = window.open(urlStr,"cadreRegistration","scrollbars=yes,left=200,top=200");	
	updateBrowser.focus();				
}
function addToPolitician(voterId,name)
{
	var urlStr = "assigningCandidatesToVoterAction.action?voterId="+voterId+"&name="+name;
	var browser2 = window.open(urlStr,"assigningCandidatesToVoterAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	browser2.focus();
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


function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 	 success : function( o ) {
	  try {												
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		
			if(jsObj.task == "getCasteWiseCustomVotersCount")
			  buildCasteWiseCustomVotersCount(myResults,jsObj);
			if(jsObj.task=="getAgeWiseCustomVotersInGroup"){
			  buildAgeWiseInGroupTable(myResults);
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
		  str +='<td><a href="javascript:{}" onclick="getCasteWiseCustomVoters('+results[i].casteStateId+','+results[i].casteId+',\''+results[i].castName+'\',\''+results[i].casteCategoryName+'\')">'+results[i].castName+'</a></td>';
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

  function openCadreSearchWindow(clickedId){
  
	var cadreWindow = window.open("cadreSearchAction.action?windowTask=Search&voterId="+clickedId,"cadreSearch","scrollbars=yes,height=600,width=750,left=200,top=200");
	cadreWindow.focus();
}

function getInfluencePeopleOfAnUser(voterId){

  showInfluencePeopleDialog(voterId)
}



function showInfluencePeopleDialog(voterId){

	$('#searchResultsDiv').html('');
	$('#influencePeopleInnerDiv').show();
	$('#totalCountId').hide();
	$('#searchResultsDiv').hide();
	var str='';
    str+='<form class="form-horizontal">';
   str+='<div class="control-group">';
	 str+='<span><label class="control-label" style="font-size: 15px;margin-left: 5px;">Enter Name :</label></span><div class="controls"><input id="nameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div><span id="nameErrMsg" class="locationErrorMsg" style="float: right;margin-right: 90px;margin-top: -24px;"></span></div>';
	 str+='<div class="control-group"><span><label class="control-label" style="font-size: 15px;margin-left: 10px;">Father Name :</label></span><div class="controls"><input id="fatherNameId" type="text" name="name" style="margin-left: 49px;width: 169px;"/></div></div>';
    
	// str+='<h5>Select Scope</h5>';
    str+='<div class="control-group">';
	  str+='<span><label class="control-label" style="font-size: 15px;margin-left: 2px;">Select Scope</label></span>';
	  str+='<div class="controls">';
	  str+='<select id="scopeId" onChange="showLocationsDiv();" style="font-size:14px;font-family:helvetica;width:185px;margin-left: 50px;">';
	        str+='<option value="0" >Select</option>';
			str+='<option value="2">STATE</option>';
			str+='<option value="3">DISTRICT</option>';
			str+='<option value="4">CONSTITUENCY</option>';
			str+='<option value="5">MANDAL</option>';
			str+='<option value="6">VILLAGE</option>';
			str+='<option value="7">MUNCIPAL-CORP-GMC</option>';
			str+='<option value="8">WARD</option>';
			str+='<option value="9">BOOTH</option>';
	  str+='</select>';
	  str+='</div>';
    str+='</div>';

    str+='<div id="locationsDiv" style="display:none;padding:10px;margin:5px;width:525px;">';
	  str+='<div  id="regionstitleDiv" style="display:none;margin-left: 60px;"><h5>Select region</h5></div>';

	  str+='<div id="stateSelect" style="display:none;margin-left: 39px;margin-top: 14px" class="locationDivClass "><div class="control-group"><label class="control-label" style="font-size: 12px;width: 59px;">STATE</label><div class="controls"><select id="stateSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="1">ANDHRA PRADESH</option></select></div></div></div>';

	  str+='<div id="districtSelect" style="display:none;margin-left: 39px;" class="locationDivClass control-group"><label class="control-label" style="font-size: 12px;width: 78px;">DISTRICT</label><div class="controls"><select id="districtSelectId" onChange="getConstituenciesInDistrict();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="districtErrMsg" style="float: right; margin-right: -32px; margin-top: -23px;" class="locationErrorMsg" ></span></div>';

	  str+='<div id="constituencySelect" style="display:none;margin-left: 39px" class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px; width: 114px;">CONSTITUENCY</label><div class="controls"><select id="constituencySelectId" onChange="getMandalsOrMuncipalities();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="constituencyErrMsg" class="locationErrorMsg" style="float: right; margin-right: -73px; margin-top: -21px;"></span></div>';

	  str+='<div id="mandalSelect"  class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 141px;">TEHSIL/MUNCIPALITY</label><div class="controls"><select id="mandalSelectId" onChange="getHamletsOrWards();" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="tehsilOrMuncipalityErrMsg" class="locationErrorMsg" style="float: right; margin-right: -62px; margin-top: -23px;"></span></div>';

	  str+='<div id="wardSelect"style="display:none;margin-left: 39px"  class="locationDivClass control-group" ><label class="control-label" style="font-size: 12px;">VILLAGE/WARD/DIVISION</label><div class="controls"><select id="wardSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="villageOrWardErrMsg" class="locationErrorMsg" style="float: right; margin-right: -20px; margin-top: -21px;"></span></div>';

	  str+='<div id="boothSelect" class="locationDivClass control-group" style="display:none;margin-left: 39px"><label class="control-label" style="font-size: 12px; width: 59px;">BOOTH</label><div class="controls"><select id="boothSelectId" style="font-size:14px;font-family:helvetica;width:185px;"><option value="0">Select</option></select></div><span id="boothErrMsg" class="locationErrorMsg" style="float: right; margin-right: -28px; margin-top: -24px;"></span></div>';

	str+='</div>';
	  str+='</form >';
	str+='<div><a class="btn btn-primary" id="searchButtonId" style="float:left; margin-left: 264px;margin-top: -25px;display:none; color: white;" href="javaScript:{callAjaxToSearchInfluencingPeople('+voterId+');}">Search</a></div>';
	str+='<div id="ajaxImageDiv1" style="display:none;"><img style="margin-left:244px;" src="images/icons/ajaxImg.gif"></div>';


	$('#influencePeopleInnerDiv').html(str);
	
	getDistrictsInAState();

	$('#influencePeopleOuterDiv').dialog({ 
	                            title:'Search Influence People',
	                            height: 'auto',
								width: 750,
								height:750,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });


	 

}

  function getCasteWiseCustomVoters(casteStateId,casteId,casteName,casteCategoryName)
  {
	
	var urlstr = "getCasteWiseCustomVotersDetails.action?casteStateId="+casteStateId+"&casteId="+casteId+"&casteName="+casteName+"&casteCategoryName="+casteCategoryName+"&customVoterGroupId="+customVoterGroupId+"&";

	var browser1 = window.open(urlstr,"casteWiseDetails","scrollbars=yes,height=600,width=1050,left=200,top=200");	
	browser1.focus();
  }
  