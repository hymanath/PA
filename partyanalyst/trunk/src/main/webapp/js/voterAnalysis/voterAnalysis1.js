



var constMgmtMainObj={
							
							castStatsArray:[],
							castStatssubArray:[],
					 };
var publicationYear = "";
var impFamiliesEditArray = new Array();
var impFamiliesEditForHamletArray = new Array();
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
var pResults;
var reqfields = "";
var reqfieldsArr = new Array();
var boothsLoc = new Array();
var buildType = "hamlet";
var assemblyLocalEleBodyId;

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

var selectType="";
function populateForHamlets(id,hamletId ,boothId,publicationId,houseNo){


	console.log("hamletId"+hamletId);


     if($('#'+id).is(':checked')){
	   var obj={
	     hamletId:hamletId,
	     boothId:boothId,
		 publicationId:publicationId,
		 houseNo:houseNo
	   }
	  // impFamiliesEditArray.push(obj);
	   impFamiliesEditForHamletArray.push(obj);
	 }else{
	     for(var i in impFamiliesEditForHamletArray){
	       if(impFamiliesEditForHamletArray[i].boothId == boothId && impFamiliesEditForHamletArray[i].publicationId == publicationId && impFamiliesEditForHamletArray[i].houseNo == houseNo){
		      impFamiliesEditForHamletArray.splice(i, 1);
		   }
	   }
	 
	 }

}

function editSelectedFamiliesForHamlet(){


  if(impFamiliesEditForHamletArray.length > 0){
   if(impFamiliesEditForHamletArray.length > 30){
      alert("Please select atmost 30 families to edit");
      return;
   }
   totalCategories = 0;
     var jsObj=
	{
		//selectedFamilies:impFamiliesEditArray,
		selectType:selectType,
		selectedFamilies:impFamiliesEditForHamletArray,
		task:"editAllFamilies"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	//var url = "getMultipleFamilesInfoAction.action?"+rparam+"&save=";	
	var url = "getMultipleFamilesInfoForHamletAction.action?"+rparam+"&save=";	
	callAjax(jsObj,url);
  }

}

function editSelectedFamilies(){
  if(impFamiliesEditArray.length > 0){
   if(impFamiliesEditArray.length > 30){
      alert("Please select atmost 30 families to edit");
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
		  $("#getAllVoterFamiliesInfoForEditFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
			  
			  var uploadHandler = {
				success: function(o) {
					var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					showAllVoterSelectedUpdatedStatus(uploadResult);
				}
			};

		
		  YAHOO.util.Connect.setForm('getAllVoterFamiliesInfoForEditForm',false);
		  YAHOO.util.Connect.asyncRequest('POST','getMultipleFamilesInfoForEditAction.action?save=',uploadHandler);
		/*var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";	
		callAjax(jsObj,url);*/
	}
}

function showAllVoterSelectedUpdatedStatus(myResults){
    $("#getAllVoterFamiliesInfoForEditFormValues").val("");
    if(myResults == "notLogged"){
	 openDialogForLoginWindow();
	}else{
	    buildVotersInFamilyForEdit(myResults);
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
			  str+="   <tr><td><b>MobileNo:</b></td><td><input type='text' id='mobileNo' class='mobileNo' style='width:136px;' value="+voters[k].mobileNo+"></input></td></tr>";
			  

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
			
			$errorDiv.text("Enter valid MobileNo").css("color", "red");
			
			flag =false;
			return;
			}
			
			
			else if(!(mobile.length == 0 || (mobile.length >=10 && mobile.length<=12)))
			{
			$errorDiv.text("Enter valid MobileNo").css("color", "red");
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
		  $("#getAllVoterFamiliesForEditFormValues").val(YAHOO.lang.JSON.stringify(jsObj));
			  
			  var uploadHandler = {
				success: function(o) {
					var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
					showSelectedUpdatedStatus(uploadResult);
				}
			};

		
		  YAHOO.util.Connect.setForm('getAllVoterFamiliesForEditForm',false);
		  YAHOO.util.Connect.asyncRequest('POST','getMultipleFamilesInfoForEditAction.action?save=',uploadHandler);
		  /*var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		  var url = "getMultipleFamilesInfoForEditAction.action?"+rparam+"&save=";		  
		  callAjax(jsObj,url);*/
		}
		}
}
function showSelectedUpdatedStatus(myResults){
     $("#getAllVoterFamiliesForEditFormValues").val("");
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
		str += ' <a href= "javascript:{};" onclick="openCadreSearchWindow('+id+');">Add To Existing Cadre</a>';
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
var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo",width:15,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",width:110,sortable: true},
{key:"gender", label: "Gender", width:45, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "House No",width:30, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"serialNo", label:"Serial No"},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

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
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson","serialNo"],
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
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo",width:15,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",width:110,sortable: true},
{key:"gender", label: "Gender", width:45, sortable: true},
{key:"age", label: "Age",  width:15,sortable:true},
{key:"houseNo", label: "House No",width:30, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&panchaytId="+panchaytId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
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

       if(type == "hamlet")
		  $('#impFamiliesForBooths').show();
	   else
		   $('#impFamiliesForBooths').hide();
	   if(type == "booth")
		    $('#impFamiliesForHamletsByBooth').show();
	   else
		   	$('#impFamiliesForHamletsByBooth').hide();
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
			$("#selectedBoothInfo").html('');
            $("#selectedBoothInfo").hide('');			
			$("#reportLevelCountDiv1").removeAttr('style');
			$("#AgeWisetitle").html("Age Wise Voters Information Of "+mainname+" in "+publicationYear+" ");
	   $("#votersDiv4").show();
       if(type == "booth" || type == "hamlet"){
         $("#casteRefreshButtonDiv").hide();
		  
       }else{
         $("#casteRefreshButtonDiv").show();
		 
       }
       if(type == "booth" || type == "hamlet" || type == "panchayat" || type == "ward"){
          $("#getLatestCastsSubcategoryWise").hide();
	   }else{
	       $("#getLatestCastsSubcategoryWise").show();
	   }
	   if(type == "booth"){
	     getBoothInfo();
	     $("#ageLink").hide();  
	   }else{
	     $("#ageLink").show();
	   }
	   if(type == "constituency"){
		var area='';
		  $("#votersBasicInfoBtnDiv").show();
		  $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'><span id='areaId'>"+area+"</span> Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Mandal Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");	
		  $("#votersShareBtn1").css('display','none');
		  checkForLocalBodyElection();
	   }
	   //updated by sasi for getting ward related headings
	   else if(type == "ward"){
			$("#impFamiliesMoreInfoButn").attr("value","View Booth Wise Family Details");
			$("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Booth Wise Age Details</a>');
			$("#votersBasicInfoBtnDiv").show();
			
		$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
	   
	   
	   }
	   else if(type == "booth"){
	     $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Hamlet Wise Age Details</a>');
	      $("#impFamiliesMoreInfoButn").attr("value","View More Details");
		  $("#votersBasicInfoBtnDiv").show();
		 $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Hamlet Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Hamlet Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
	   }else if(type == "hamlet"){
		   $("#votersBasicInfoBtnDiv").show();
		// $("#cnstHeading").html();
		 $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Localities Wise Age Details</a>');
         	$("#impFamiliesMoreInfoButn").show();
		 $("#impFamiliesMoreInfoButn").attr("value","View Locality Wise Family Details");
		 $("#previousEleVotingTrendsDiv").css('display','none');
		 //$("#impFamiliesMoreInfoButn").css('display','none');
		 $("#InfluencingPeopleCountDiv").css('display','none');
		  $("#votersCountVaryDiv").css('display','none');
	        $("#votersDiv1").show();
		 $("#votersDiv1").css('display','block');
		 $("#AgeWisetitle").html("Age Wise Voters Information Of "+mainname+" Hamlet in "+publicationYear+" ");
	     // $("#impFamiliesMoreInfoButn").attr("value","View More Details");
		//  $("#votersBasicInfoBtnDiv").hide();
		$("#castTab").html('<input type="button" onclick="ShowCastSubLevelPopupDiv(\'booth\');" style="float:right;margin-top:7px;margin-bottom:5px;" value="Booth Wise Caste Info of '+mainname+'" class="btn btn-info">');
	   }
	   else if(type == "mandal" && mainreqid.substring(0,1) == "2"){
	      $("#impFamiliesMoreInfoButn").attr("value","View Panchayat Wise Family Details");
	   }else if(type=="panchayat"){
	     if(buildType == "hamlet"){
		 $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Hamlet Wise Age Details</a>');
	      $("#impFamiliesMoreInfoButn").attr("value","View Hamlet Wise Family Details");
		 $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Hamlet Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Hamlet Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
		} else{
			  $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Booth Wise Age Details</a>');
		
		//$("#impFamiliesMoreInfoButn").attr("value","View Booth Wise Family Details");
		
		//$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
		 }
		  $("#votersBasicInfoBtnDiv").show();
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
	   if(type == "panchayat" || type == "booth" || type == "hamlet" ){
	      $("#votersInfoMoreShowHide").show();
		 }else{
		  $("#votersInfoMoreShowHide").hide();
		 }
		 if(type != "hamlet" && type != "booth")
           $("#votersShareBtn1").css('display','block');
		 if(type == "mandal" && mainreqid.substring(0,1) == "1")
		 {
			$("#votersBasicInfoBtnDiv").show();
			$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of  "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
			
		 }
		 if(type != "hamlet")
		 {
		 $("#castTab").html('');
		  $("#previousEleVotingTrendsDiv").css('display','block');
		 }

		  if(type == "hamlet")
		  {    $("#ageLinkForHamletBooths").css('display','block');
			  $("#previousEleVotingTrendsDiv1").css('display','none');
			  $("#permanentlyUpdateDiv").css('display','none');
		  }
		  else
		  {
		       $("#ageLinkForHamletBooths").css('display','none');
			  $("#previousEleVotingTrendsDiv1").css('display','block');
			  $("#permanentlyUpdateDiv").css('display','block');
		  } 
		
		 $("#votersHeaderDiv3").hide();
		  $("#votersMainOuterDiv3").show();
		 //--
		 getPreviousVotersDetails1();
		    // getPreviousVotersDetails();
		//getvotersBasicInfo("voters",id,publicationId,type);
		// getVotersData();
		//--
		showNewsDetails(id,publicationId,type);
		 //getProblemsByLocation(id,publicationId,type);
		//--
		getProblemsByLocation(id,publicationId,type);
		 //--
		// if(type != "hamlet")
		 getInfluencingPeopleCount(id,type);
		//-- 
		getCounts(id,publicationId,type);
		getVotersCastInfo(id,publicationId,type);
       //getCastInfoForsubLevel(id,publicationId,type);
       getvotersBasicInfo("impFamilies",id,publicationId,type);
				
		//-- callCorrespondingAjaxCall();
		//-- 
		 if(type != "hamlet")
		getPreviousElectionVotingTrends(id,publicationId,type);
		//-- 
		callCorrespondingAjaxCall('brief');
		 //getElectionyearsByMandalId(id,type)
		 var fromPublicationDateId=0;
		 //getModifiedVotersCountBetweenPublications(type,id,fromPublicationDateId,publicationId);

		 if(type == "mandal" && mainreqid.substring(0,1) == "1")
			getAssemblyLocalEleBodyId();
			
		if(type == "hamlet")
		 checkLocalityDataExist();
		else
		{
		  $("#ageLink").show();
		  $("#impFamiliesMoreInfoButn").show();
		  $("#castPartyPopupShowBtn").show();
		}
	
	}
	function getModifiedVotersCountBetweenPublications(locationType,locationValue,fromPublicationDateId,toPublicationDateId){
	$("#votersCountModifyAjaxDiv").css("display","block");
	constituencyId=$('#constituencyList option:selected').val()
		var jsObj={					
						toPublicationDateId:toPublicationDateId,
						fromPublicationDateId:fromPublicationDateId,
						locationValue:locationValue,
						locationType:locationType,
						constituencyId:constituencyId,
						task:"getModifiedVotersCountBetweenPublications"
				  };

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getModifiedVotersCountBetweenPublications.action?"+rparam;

			callAjax(jsObj,url);
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
		else if(maintype == "hamlet"){
			boothId = mainreqid;
			getPreviousVotersDetailsForhamlet();
		}
	}
	//updated by gayathri to get hamlet basic info
	function getPreviousVotersDetailsForhamlet(){
		//alert('11111');
		
	var jsObj=
					{					
						constituencyId:$('#constituencyList').val(),
						mandalId:0,
						boothId:0,
						panchayatId:0,
						hamletId:mainreqid,
						type:"hamlet",
						task:"getVotersCountForAllElectionsForHamlet"
					};
						var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountForAllElectionsForHamlet.action?"+rparam;

			callAjax(jsObj,url);

	  $('#basicDetailsAjax').css('display','block');
	
	
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
	
	 getCastInfoForsubLevel(mainreqid,$("#publicationDateList").val(),maintype,"");

	    $("#castPartyPopupDiv").dialog({
            modal: true,
            title: "<b>Voters Details - Caste Wise</b>",
			width: 980,
            height: 600
           
        });
		$("#voterCasteAjaxImg").css("display","block");
	}
	function ShowCastSubLevelPopupDiv(resultFor){
	
	 getCastInfoForsubLevel(mainreqid,$("#publicationDateList").val(),maintype,resultFor);

	    $("#castPartyPopupDiv").dialog({
            modal: true,
            title: "<b>Voters Details - Caste Wise</b>",
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
				constituencyId:$("#constituencyList").val(),
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
								{    if(jsObj.type=="panchayat")
								 {
								 
								   if(jsObj.buildType != buildType )
								   return;
								 }
								   if(jsObj.type=="hamlet")
								 {
								 hideAjaxImgDiv("ajaxImageDiv1");								 
								 }
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
								else if(jsObj.task == "getPartyInfo")
								{
								    $("#votersDiv2").show();
									buildPartyWisePiechart(myResults,jsObj);
									buildPartyWiseCastData(myResults,jsObj.typename,jsObj.publicationDateId,jsObj.id,jsObj.type);
								}
								else if(jsObj.task == "getPartyCastInfo")
								{
									buildPartyWiseCastDetailsTable(myResults,jsObj);
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
								else if(jsObj.task == "importantFamiliesinfoForHamletsByBooth"){
									buildImpFamilesChartForHamletsByBooth(myResults);
								buildTableForImpFamilesForHamletByBooth(myResults.subList,myResults.name,myResults.type);

								}
								else if(jsObj.task == "importantFamiliesinfo")
								{ 
									$('#ImpFamwiseAjaxDiv').hide();
									$('#votersDiv1').hide();
								  if(myResults != null && jsObj.type == maintype){
								    $("#votersDiv1").show();
								    impFamilesStaticTable(myResults,jsObj);
									if(jsObj.type == "hamlet" && jsObj.requestFor != 'booth'){
									buildImpFamilesChart(myResults);
									}									
								    if(myResults.subList != null && myResults.subList.length > 0)
							  		  {

										buildTableForImpFamilesMandal(myResults.subList,myResults.name,myResults.type);
										impFamilesVariableDescription();
									  }

									  if(myResults.subListForHamlets != null && myResults.subListForHamlets.length > 0)
									  {
										  buildImpFamilesForHamletChart(myResults);
                                        buildTableForImpFamilesForHamlets(myResults.subListForHamlets,myResults.name,myResults.type,myResults);
										//buildTableForImpFamilesByHamlet(myResults.subList,myResults.name,myResults.type);
										impFamilesVariableDescription1();
									  }
								   }

								   
								   
								}
								else if(jsObj.taskType == "gettotalimpfamliesForHamletsByBooth")
								{
									$('#impFamPancBothDtlsAgxImgForHamletByBooth').hide();
								    buildFamilyMembersForBooth(myResults,jsObj,jsObj.type);
								}
								else if(jsObj.task == "gettotalimpfamlies"  )
								{   
								$("#impFamPancBothDtlsAgxImg").hide();
								      if(myResults == null || myResults.length == 0 ){
									  $("#impFamilesBasicSubDetailsForHamletTitle").html("");
                                       $("#impFamilesBasicSubDetailsForHamlet").html("");
								       //  $("#descriptionDiv").html("");
									   
									 //    $("#descriptionInnerDiv").hide();
									      $("#descriptionDiv1 > .descriptionInnerDiv").css('display','none');
									  	  $("#impFamilesBasicInfoForHamletSubChartDiv").html("");
                                          $("#assigAndUnassig").html("");
                                          $("#impFamilesBasicSubDetailsForHamletTitle").html("");
										$("#impFamPancBothDtlsAgxImgForHamlet").html("");
									  return;
									  }
									//  impFamilesVariableDescription();
									  $(".descriptionInnerDiv").css('display','block');
								    buildFamilyMembers(myResults,jsObj,jsObj.type);
								}
                                else if(jsObj.task == "getVotersInAFamily")
								{
								    buildVotersInFamily(myResults,jsObj.hno);
									pResults = myResults;
								}
								else if(jsObj.task == "getUserCategories")
								{
									buildCategories(myResults);
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
										pResults = myResults;
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
											
								else if(jsObj.task=="getModifiedVotersCountBetweenPublications")
								{	
									buildModfiedVotersCountSection(myResults,jsObj);							
								}
								else if(jsObj.task=="ckeckForVoterId")
								{	
									buildVoterToSelectedType(myResults,jsObj);
								}
								else if(jsObj.task=="getBoothInfo"){
								   showBoothInfo(myResults);
								}								
								else if(jsObj.task == "getInfluencingPeopleBySearch")
                                {
									$('#ajaxImageDiv1').css('display','none');
                                     buildInfluencePeopleSearchResults(myResults,jsObj.voterId);
								}  
								else if(jsObj.task == "mapVoterAsInfluencingPerson"){
									alert("Voter Added as influence person.");
									$('#influencePeopleOuterDiv').dialog('close');
								}
								else if(jsObj.task == "addVoterToCadre"){
									if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else if(myResults == "error" || myResults == "failure"){
									  alert("Unable to process your request,please try later.");
									}else{
									   alert("Voter Added To Cadre Successfully.");
									}
								}
								
								else if(jsObj.task == "getVotersCountForAllElectionsForHamlet"){
									$('#basicDetailsAjax').css('display','none');
									hideAjaxImgDiv('ajaxImageDiv');
									buildPreviousVotersDetails(myResults,jsObj);
								}
								else if(jsObj.task == "getInfluencingPeopleCount")
								{
									buildInfluencingPeopleCount(myResults,jsObj);
									}
									
									else if(jsObj.task == "checkForLocalBodyElection"){
									buildLocalBodyElectionDetails(myResults);
								}
								else if(jsObj.task == "addVoterToCadre"){
									if(myResults == "notLogged"){
									 openDialogForLoginWindow();
									}else if(myResults == "error" || myResults == "failure"){
									  alert("Unable to process your request,please try later.");
									}else{
									   alert("Voter Added To Cadre Successfully.");
									}
								}else if(jsObj.task == "insertCastAndPartyVotersData"){
								    $("#permanentlyUpdateDiv").removeAttr("disabled");
								    getVotersCastInfo(mainreqid,mainpublicationId,maintype);
								}

								else if(jsObj.task == "getAssemblyLocalEleBodyId")
									assemblyLocalEleBodyId = myResults;
								else if(jsObj.task == "checkLocalityData")
								try{
								  hideAndShowLocalityDIvs(myResults,jsObj);
									}catch(e){$("#sse2").css("display","block");}								
									
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
	
	function buildModfiedVotersCountSection(results,jsObj){
		var addedCount=results.addedCount;
		var deletedCount=results.deletedCount;
		var presPId=results.presentPublicationId;
		var prevPId=results.previousPublicationId;
		var url='voterModificationReportAction.action?toPublicationDateId='+presPId+'&fromPublicationDateId='+prevPId+'&constituencyId='+jsObj.constituencyId+'&locationType='+jsObj.locationType+'&locationValue='+jsObj.locationValue;
		$('#detailModifiedVoters').attr('url',url);
		
		if(addedCount!=null){$("#addedVtrs").text(addedCount);}
		else{$("#addedVtrs").text('No Data');}
		
		if(deletedCount!=null){$("#delVtrs").text(deletedCount);}
		else{$("#delVtrs").text('No Data');}
		
		
		var selectedElmt=document.getElementById("prespublicationDateList");
		removeSelectElements(selectedElmt);
		
		for(var val in publicationDatesList)
		{
		
			var opElmt = document.createElement('option');
			opElmt.value=publicationDatesList[val].id;
			opElmt.text=publicationDatesList[val].name;

			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}
		  if(publicationDatesList[val].id==presPId){	
			$(opElmt).attr("selected","selected") ;		  
		  }
		}
		
		
		var selectedElmt2=document.getElementById("prevpublicationDateList");
		removeSelectElements(selectedElmt2);
		
		for(var val in publicationDatesList)
		{
		
			var opElmt2 = document.createElement('option');
			opElmt2.value=publicationDatesList[val].id;
			opElmt2.text=publicationDatesList[val].name;

			try
			{
				selectedElmt2.add(opElmt2,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt2.add(opElmt2); // IE only
			}
		  if(publicationDatesList[val].id==prevPId){	
			$(opElmt).attr("selected","selected") ;		  
		  }
		}
		$("#votersCountModifyAjaxDiv").css("display","none");
		
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
        str+='<div id="localBodyElectionDiv" style="dispaly:none;margin-bottom: -15px;margin-top: -14px;"><a href="javascript:{}" class="btn btn-info" id="showId" onclick="openNewWindowForLocalBodyElection();">All Parties Performance In Local Body Elections </a></div>';
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
	var publicationDatesList;
	function buildPublicationDateList(results)
	{
	publicationDatesList=results;
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

$('#publicationDateList').live('change',function(){
	getPreAndPresentPublicationDtaeList();
});

$("#voterDetailedReportId").live("click",function(){
	var constituencyId = $("#constituencyList").val();
	var prevPId = $("#prevpublicationDateIdsList").val();
	var presPId = $("#prespublicationDateIdsList").val();
	var presPidDate=$("#prespublicationDateIdsList :selected").text();
	var prevPidDate=$("#prevpublicationDateIdsList :selected").text();
	
	var dateArrayprev = prevPidDate.split("-");
	var dateprev=new Date(dateArrayprev[2], dateArrayprev[1]-1, dateArrayprev[0]);
	var dateArraypres = presPidDate.split("-");
	var datepres=new Date(dateArraypres[2], dateArraypres[1]-1, dateArraypres[0]);
	
	
	var locationType = maintype;
	var locationValue = mainreqid;
	
	$('#errorMsgDiv').html('');
	if(presPId == 0 || presPId == ""){
		$("#errorMsgDiv").html("<span>Invalid Present Publication Date</span><br>");
		return;
	}
	if(prevPId==0){
		$("#errorMsgDiv").html("<span>Invalid Previous Publication Date</span><br>");
		return;
	}
	
	if(presPId==prevPId){
		$("#errorMsgDiv").html("<span>Previous and Present Publication Date should not be same</span><br>");
		return;
	}
	
	
	if(dateprev > datepres){
		$("#errorMsgDiv").html("<span>Previous Publication Date should not be Greater Than Present Publication Date</span><br>");
		return;
	}
	if(maintype == 'mandal')
	{
		if(mainreqid.substring(0,1) == "2")
		{
			locationType = 'mandal';
			locationValue = mainreqid.substring(1);
		}
		else 
		{
			locationType = 'localElectionBody';
			locationValue = assemblyLocalEleBodyId;
		}
		
		
	}
	var urlStr='voterModificationReportAction.action?constituencyId='+constituencyId+'&fromPublicationDateId='+prevPId+'&toPublicationDateId='+presPId+'&locationType='+locationType+'&locationValue='+locationValue+'&';
	window.open(urlStr,'_blank');
	window.focus();

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
		else if(maintype == "hamlet")
		getVoterDetailsForHamlet(retrieveType);
}

function getVotersData(){
  //var level = $("#reportLevel").val();
    if(maintype == "panchayat"){
	  //$("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocPanchayatDataTable("panchayatField");
	}else if(maintype == "booth"){
	  //$("#votersByLocationTabContentDiv_body").css("border","1px solid black");
	  buildVotersByLocBoothDataTable("pollingStationField");
	}else if(maintype == "hamlet")
	{
	buildVotersByLocHamletTestDataTable(mainpublicationId,mainreqid);
	}
	else{
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
				constituencyId:$("#constituencyList").val(),
				queryType:"sub",
				task:"getCastInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		
		var jsObj1=
			{
				type:type,	
				id:id,
				typename:typename,
				publicationDateId:publicationId,
				constituencyId:$("#constituencyList").val(),
				queryType:"sub",
				task:"getPartyInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj1,url);
}

function getPartyWiseCastInfo(){
  $("#partyWiseLocalCastStatsTab").dialog({
            modal: true,
            title: "<b>Caste V/s Party Analysis</b>",
			width: 970,
            height: 500
           
        });
		$("#partyWiseLocalCastStatsTab").html('<div style="margin-left:375px;margin-top:70px;"><img style="clear: both; display: block;" src="images/icons/goldAjaxLoad.gif" ></div>');
  var typename=mainname;
		var jsObj=
			{
				type:maintype,	
				id:mainreqid,
				typename:typename,
				publicationDateId:mainpublicationId,
				constituencyId:$("#constituencyList").val(),
				task:"getPartyCastInfo"
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
		var res=jsObj.resultFor;
		var restype=jsObj.buildType;
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
		if(constMgmtMainObj.castStatssubArray == null || constMgmtMainObj.castStatssubArray.length == 0){
		  $("#localCastStatsTabContent_subbody").html("<b style='margin-left: 350px;'>No Data Available</b>");
		  return;
		}  
		//if(type != 'booth')
		//{
		str +='<table id="subLevelTable">';
		if(type == 'constituency')
		str+='<h4 id="sublevelHeading">Mandal/Muncipality wise Caste Statistics In '+typeName+' Constituency</h4>';
		else if(type == "mandal")
		str+='<h4 id="sublevelHeading">Panchayat wise Caste Statistics In '+typeName+' </h4>';
        else if(type =="panchayat"){
		 if(restype== "booth")  
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Panchayat</h4>';
		else 
		str+='<h4 id="sublevelHeading">Hamlet wise Caste Statistics In '+typeName+' </h4>';
		}
		else if(type =="ward")
		str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Ward</h4>';
		else if(type =="hamlet"){
		   if(res == "booth")
		   str+='<h4 id="sublevelHeading">Booth wise Caste Statistics In '+typeName+' Hamlet</h4>';
		else
		str+='<h4 id="sublevelHeading">Locality wise Caste Statistics In '+typeName+' Hamlet</h4>';
			}
		
		str+='<thead>';
		str+='<tr>';
		
		if(type == "constituency")
		str +='<th>Mandal</th>';
		if(type == "mandal")
		str +='<th>Panchayat</th>';
		if(type =="panchayat"){
		if(restype== "booth")  
		str +='<th>Booth</th>';
		else 
		str +='<th>Hamlet</th>';
		}
		if(type =="ward")
		str +='<th>Booth</th>';
		if(type =="hamlet")
		{ if(res == "booth")
		str +='<th>Booth</th>';
		else
	    str +='<th>Locality</th>';
		
		}
		if(type =="booth")
	    str +='<th>Hamlet</th>';

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
		else if(type =="panchayat" && buildType != "hamlet")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'booth\',\'boothNo - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="panchayat" && buildType == "hamlet")
		{
		str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'panchayat\',\'Hamlet - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}
		else if(type =="hamlet")
		{
		if(jsObj.resultFor == "booth")
				str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+constMgmtMainObj.castStatssubArray[i].locationId+','+jsObj.id+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\''+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';

		else
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForLocality('+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].hamletId+','+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
		}else if(type =="booth")
		{
		
		str+='<td><a href="javascript:{}" onclick="getVotersInACasteForDidffrentLevels('+jsObj.id+','+constMgmtMainObj.castStatssubArray[i].locationId+','+publicationDateId+',\''+constMgmtMainObj.castStatssubArray[i].caste+'\',\'boothHamlet\',\'Hamlet - '+constMgmtMainObj.castStatssubArray[i].mandal+'\',\''+constMgmtMainObj.castStatssubArray[i].castStateId+'\',\''+constMgmtMainObj.castStatssubArray[i].casteCategory+'\')">'+constMgmtMainObj.castStatssubArray[i].caste+'</a></td>';
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
	//}
	}	
	}


function getVotersInACasteForLocality(id , publicationDateId , hamletId , casteStateId,casteCategory)
{
	var jsObj={
			id:id,
			publicationDateId:publicationDateId,
			caste:casteStateId,
			hamletId:mainreqid,
			type:"locality",
            buildType:"",
			task:"getVotersInACaste"

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

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
            buildType:buildType,
			constituencyId:$("#constituencyList").val(),
			casteCategory:casteCategory,
			task:"getVotersInACaste"

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

}
function getvotersBasicInfo(buttonType,id,publicationId,type){
    //debugger;
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
	var hresult="";
	if(true)
	{
	if(type == "booth")
	buildType="hamlet";
	
	//errorDivEle.innerHTML = '';		
	 impFamltype = type;
     impFamlId = id;
     impFamlpublicationDateId = publicationDateId;
     impFamltypename = typename;
	 if(type == "hamletBooth"){
	 hresult="booth";
	type="hamlet";
	
	}
	if(type == "hamletLocal"){
	type="hamlet";
	hresult="localArea";
	}
   if(buttonType == "voters"){
	  showAjaxImgDiv('ajaxImageDiv');
	var jsObj=
			{
				
				type:type,
				id:id,
				publicationDateId:publicationDateId,
				year:publicationYear,
				typename:typename,
				constituencyId:$("#constituencyList").val(),
                buildType:buildType,
				resultFor:hresult,
				task:"votersbasicinfo"
	
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getVotersCountInfoAction.action?"+rparam;	
    if(type=="hamlet")
	 showAjaxImgDiv('ajaxImageDiv1');
		callAjax(jsObj,url);
	}
	if(buttonType == "impFamilies"){
	    $("#impFamiliesMoreInfoButn").show();
	    if(type == 'panchayat' || type == 'booth'){
		   
		   if(type == 'booth'  ){
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
				constituencyId:$("#constituencyList").val(),
				buildType:buildType,
				requestFor:"",
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			
			//var url1 ="";

          /*if(buildType == "hamlet" && type== 'panchayat')
            url1 = "getImportantFamaliesDetailsForHamlet.action?"+rparam1;
             else*/
         var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
			//var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;						
		callAjax(jsObj1,url1);

   }
	}
}

/*function getImpFamiliesVotersForHamlet()
{
	var jsObj1=
			{
					
				type:"hamlet",
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:"",
				constituencyId:$("#constituencyList").val(),
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url1 = "getImportantFamaliesDetailsForHamlet.action?"+rparam1;						
		callAjax(jsObj1,url1);


}*/

/*function getImpFamiliesVotersToShow(){
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
}*/

function impFamilesAllInfoForHamletPopUp(){
	$('#impFamPancBothDtlsAgxImgForHamlet').show();
    $("#impFamilesAllInfoForHamletPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
		 
		 var jsObj2=
			{
					
				type:maintype,
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:impFamltypename,
				buildType:"hamlet",
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2); 
		}


/*function getImpFamiliesVotersToShow(){
	$('#impFamPancBothDtlsAgxImg').show();
     if(buildType == "hamlet" && maintype == "panchayat" ){
	 //getvotersBasicInfo("impFamilies",mainreqid,mainpublicationId,"panchayat"); */
     // impFamilesAllInfoForHamletPopUp();
  // }else{
	 //var buildType = "hamlet";
	//getImpFamiliesVotersForHamlet();
   /* $("#impFamilesAllInfoPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });
		/*	if(buildType = "hamlet"){
			var jsObj1=
			{
					
				type:"hamlet",
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:"",
				constituencyId:$("#constituencyList").val(),
				task:"importantFamiliesinfo"
	
			}
			
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url1 = "getImportantFamaliesDetailsForHamlet.action?"+rparam1;						
		callAjax(jsObj1,url1);
   
         }else {	 */   
		/* var jsObj2=
			{
					
				type:"panchayat",
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:impFamltypename,
				buildType:buildType,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);  }
	}*/

function getImpFamiliesVotersToShow(){
 //$(".descriptionInnerDiv").css('display','block');
    // hideBoothDivs();
	
  $("#descriptionDiv").show();

	
     if((buildType == "hamlet" && maintype == "panchayat")  || maintype == "hamlet"){
	 $('#impFamPancBothDtlsAgxImg').show();
      impFamilesAllInfoForHamletPopUp();
	  	 hideHamletDivs();

   }else{
		
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
                buildType:buildType,
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	}
		
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

function getUserCategories(){

	var jsObj=
			{
			 task:"getUserCategories"
	
			}
	   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getUserCategoriesAction.action?"+rparam;						
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
	                        {key:"mobileNo",label:"MobileNo",sortable:true}
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
	  getUserCategories();

	$('.requiredAttrClass').each(function(){
	  $(this).attr('checked','checked');
    });

	$('.notRequiredAttrClass').each(function(){
		$(this).attr('checked',false);
	});
//console.log(results);
    $("#multipleVoterFamiliesEditDiv").html("");
   // $("#impFamDtlsTitle").html("<b>Voter Details</b>");
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
							{key:"mobileNo",label:"MobileNo",sortable:true}
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
	if(totalVoters == 0){
		$('#votersMainOuterDiv2').hide();
		return false;
	}
	$('#votersMainOuterDiv2').show();
	if(result.maleVoters > 0){
	    $("#castPartyPopupShowBtn").show();
		$("#partyBasicInfoStatsTabNewTitle").html("<div id='partyDiv'><h2 id='subHeading'>Party Wise Voters Details</h2><input type='button' value='Graphical Representation Of Party Wise Voters Details' class='btn btn-info pull-right' onClick='showPartyWiseDetailsInGraph()' id='partyGraphButtonId'/></div>");
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
		$('.localCastStatsVotersTitle').css("backgrond","#FFF;");
		}
  }
}

	function buildPartyWiseCastDetailsTable(myresults,jsObj){
	   $("#partyWiseLocalCastStatsTab").html("");
	   if(myresults != null && myresults.voterCastInfodetails != null && myresults.voterCastInfodetails.castVOs != null && myresults.voterCastInfodetails.castVOs.length > 0){
	       var result = myresults.voterCastInfodetails.castVOs;
		   var str ='<div>';
		      str+='<div id="partyWiseLocalCastStatsTabTitle" style="width:409px;"><h2 id="subHeading" >Caste V/s Party analysis of '+jsObj.typename+' in '+publicationYear+'</h2></div>';
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
				$("#partyWiseCastJqTable_wrapper").css("border","1px solid #D3D3D3");
	            $("#partyWiseCastJqTable_wrapper").css("padding","8px 8px 25px");
	   }else{
			$("#partyWiseLocalCastStatsTab").html('<div style="font-weight:bold;margin-left:375px;margin-top:70px;">Data Not Available</div>');
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
		   }else if(type =='hamlet')
		   {	  
		  str+='<td><a href="javascript:{}" onclick="getVotersInACaste('+boothId+','+publicationDateId+',\''+castArray[i].caste+'\',\'Hamlet\',\''+typeName+'\',\''+castArray[i].casteStateId+'\',\''+castArray[i].casteCategory+'\')">'+castArray[i].caste+'</a></td>';
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
  function  buildFamilyMembers(result,jsObj,type){
  
	  if((type == "panchayat" && jsObj.buildType =="hamlet") || (type == "hamlet" && jsObj.requestFor != "booth") ){
		  buildFamilyMembers1(result,jsObj,type);
		  return false;
	   }
  if($("impfamilydatatable_wrapper"))
  {
  $("impfamilydatatable_wrapper").remove();
  }
	var publicationDateId =   jsObj.publicationDateId;
 //debugger;
 //alert("ok");
	 impFamiliesEditArray = new Array();
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
    var name = "";
     if(type == "panchayat"){
	    name = $("#panchayatField option:selected").text();
	 }else{
	  //type = "";
	   name = $("#pollingStationField option:selected").text();
	 }
      var str ='<div id="impFamPancBothDtlstitle">Voters Family details in '+name+' '+type+' in '+publicationYear+'</div>';
	      str+=' <div><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
          str+=' <div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:15px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>';
		  str+=' <table id="impfamilydatatable" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
		  str+='     <th>Select</th>';
         // str+='     <th>SNo</th>';
		 if(type == "panchayat" && jsObj.buildType =="hamlet" )
		  str+='     <th>Hamlet</th>';
		   if(type == "hamlet" && jsObj.requestFor != "booth")
		  str+='     <th>LocalArea</th>';
		  
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
		  if(type == "panchayat" && jsObj.buildType =="hamlet" )
		  str +='		<td>'+result[i].hamletName+'</td>';		 
		   if(type == "hamlet" && jsObj.requestFor != "booth")
		    str +='		<td>'+result[i].localAreaName+'</td>';
		  
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
		  str+=' <div style="clear:both;"><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
	      str+=' <div style="clear:both;"><input type="button" style="margin-top:16px;margin-left:20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:-17px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:-17px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv1" style="display:none;margin-top: 0px;"src="./images/icons/search.gif"></img></div>';
		  if((jsObj.buildType =="hamlet" && type == "panchayat") || (type == "hamlet") && jsObj.requestFor != "booth")
	       {
			  $('#impFamPancBothDtlsAgxImgForHamlet').html(str);
			  $('#impFamPancBothDtlsAgxImgForHamle').hide();
	      }
		  else
	       $("#impFamPancBothDtls").html(str);
	  
	  	$('#impfamilydatatable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"bDestroy": true,
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
		//"bFilter": false,"bInfo": false
		 // "aoColumns": [null,null,null,null,null,null,null,null,null,null,null
     
	  
  //  ] 
		});


		
  }

  
function clearAllCheckBoxes()
{
	impFamiliesEditArray = new Array();
	$('.impFamilMulSel').each(function(){
		$(this).attr("checked",false);
	});
	
}//

function buildTableForImpFamilesForHamlets(impFamilesData,name,type,results)
{
//Updated by sasi for assigned and unassigned voters count
	if(type=="Panchayat"){
		if(buildType=="hamlet"){
			
			var totalvoter=results.assignedVotersByUser+results.unassignedVotersByUser;
			strl ='';
			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
			strl += '<tbody><td style="text-align:center;">'+totalvoter+'</td><td style="text-align:center;">'+results.assignedVotersByUser+'</td><td style="text-align:center;">'+results.unassignedVotersByUser+'</td></tbody>';
			
			strl += '</table>';
			$("#assigAndUnassig").html(strl);
			}
			else{
			$("#assigAndUnassig").html('');
			}
		}
		else if(type=="Hamlet"){
			
			var totalvoterlclbdis=results.assignedVotersForLocalBodies+results.unassignedVotersForLocalBodies;
			strl ='';
			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
			strl += '<tbody><td style="text-align:center;">'+totalvoterlclbdis+'</td><td style="text-align:center;">'+results.assignedVotersForLocalBodies+'</td><td style="text-align:center;">'+results.unassignedVotersForLocalBodies+'</td></tbody>';
			
			strl += '</table>';
			$("#assigAndUnassig").html(strl);
		}
		
		else{
			$("#assigAndUnassig").html('');
		}
	
	//Updated by sasi for assigned and unassigned voters count

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
  $("#impFamilesBasicSubDetailsForHamletTitle").html("<h4>"+reqtytle+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"</h4>");
  
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
var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetailsForHamlet", impFamilesColumnDefs,
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
	//if(jsObj.type=="hamlet")
	//$('#impFamiliesMoreInfoButn').hide();
	
	
	
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
function buildImpFamilesForHamletChart(chartInfo)
{

	//console.log(chartInfo);
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
	var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoForHamletSubChartDiv'));
	chart.draw(data, options);
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
						constituencyId:$("#constituencyList").val(),
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
					constituencyId:$("#constituencyList").val(),
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
			        constituencyId:$("#constituencyList").val(),
					mandalId:'0',
					boothId:'0',
					panchayatId:mainreqid,
					publicationDateId:mainpublicationId,
					name:name,
					retrieveType:retrieveType,
					buildType:buildType,
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
					constituencyId:$("#constituencyList").val(),
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
					constituencyId:$("#constituencyList").val(),
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
						  if(jsObj.type == maintype || jsObj.type == "hamletLocalArea" )
						  buildVoterDetailsTable(myResults,jsObj.type,jsObj.retrieveType);
					  }else if(jsObj.retrieveType == "all"){

						 // buildVoterDetailsTable(myResults,jsObj.type);
                            // buildAgeWiseVoterAnalysisChart(myResults,jsObj);
                           
							if(jsObj.type != "booth" && jsObj.type!= "hamlet" ){
							if(jsObj.type == "panchayat" || jsObj.type == "localElectionBody" || jsObj.type == "ward"|| jsObj.type == "hamletLocalArea")
							 {
							 if(myResults.boothVotersDetails!=null && myResults.boothVotersDetails.length!=0){
								buildAgewiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
								}
								else{
								$("#NoResultsDialogue").find('span').css('display','block');
								$("#NoResultsDialogue").dialog({
									modal: true,
									title: "<b>Voters Details</b>",
									width: 440,
									height: 100
								});}
							  }else{
							    buildAgewiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetails(myResults,jsObj);
								buildAgeAndGenderWiseDetailsForPercent(myResults,jsObj);
							  }
							}
					  }
						 // $("#processingDialogOuterDiv").dialog('close');
						
					}catch (e){   
							
						//alert("Invalid JSON result" + e);   
					}  
				   },
				   scope : this,
				   failure : function( o ) {
				   //alert("No Data Found");
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);


}

function buildVoterDetailsTable(result,type,retrieveType){

	if(result.votersDetailsVO.length == 0){
		$('#votersDiv4').hide();
		return false;
	}
	else
		$('#votersDiv4').show();

	var noteString = '';

	if(type == "constituency")
		noteString = $('#constituencyList :selected').text()+" "+"constituency";
	else if(type == "mandal")
		noteString = $('#mandalField :selected').text()+" ";
	else if(type == "panchayat")
		noteString = $('#panchayatField :selected').text()+" "+"panchayat";
	else if(type == "localElectionBody")
		noteString = $('#mandalField :selected').text()+" "+"localElection";
	else if(type == "hamlet")
	noteString =  "Hamlet" +" "+"Details";
	else 
		noteString = $('#pollingStationField :selected').text();

	$('#voterDetailsNote').html('<h4 style="color:#3F3939;margin-left: 4px;font-family: Verdana;">'+noteString+" "+"voters details"+' in '+publicationYear+'</h4>');
	//$('#voterDetailsNote1').html('<h5 style="color:#E36A30;margin-left:40px;font-family: Verdana;">'+noteString+" "+"voters details"+' in '+publicationYear+'</h5>');


	var str='';
	str+='<table class="table table-bordered table-hover" id="ageWiseDetailsTable" style="width: 103%; max-width: 103%; margin: 1px -18px;" >';
	
	str+='<thead class="info">'
	str+='<tr>'
	str+='<th rowspan="2" id="ageRangeId">Age Range</th>';
	str+='<th colspan="2">Total Voters</th>';
	str+='<th colspan="2">Male</th>';
	str+='<th colspan="2">Female</th>';
	//str+='<th colspan="2">UnKnown</th>';
	str+='</tr>';
    
	str+='<tr>';
	str+='<th>Total Voters</th>';
	str+='<th>Total Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	str+='<th>Voters</th>';
	str+='<th>Percentage</th>';
	//str+='<th>Voters</th>';
	//str+='<th>Percentage</th>';
	str+='</tr>';
	str+='</thead><tbody>';
	if(result.votersDetailsVO == null ||result.votersDetailsVO.length ==0 ){
	str='<span style="color:red">No Data Available</span>';
	//$("#ageLink").hide();
	return;
}
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
	
	$('#ageWiseDetailsTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		//"iDisplayLength":50,
		//"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  //"aoColumns": [null,null,null,null,null,null,null] 
		});
	$('#tableDiv1').html(str);
	
	/*$('#ageWiseDetailsTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		//"iDisplayLength":50,
		//"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		//  "aoColumns": [null,null,null,null,null,null,null] 
		});
		//$('#ageRangeId').css('width','-4px');*/
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
		//console.log(results.boothVotersDetails);
		
		innerResults = results.boothVotersDetails;
		//console.log(innerResults.length)
		//console.log(innerResults[0].totalVotersFor18To25);
		if(buildType == "hamlet")
		noteString = "Hamlet wise voters age details of "+obj.name+" in "+publicationYear;
		else
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
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise voter age details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgewiseDetailsNote').html('<h4 style="color:#3F3939;margin-left: 4px;font-family: Verdana;">'+noteString+'</h4>');

	var str='';
	str+='<table border="1"  class="table table-hover table-bordered" id="mandalWiseVoterAgeTable" style="width: 104%; max-width: 104%; margin: 1px -18px 1px 10px;">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		str+='<th rowspan="2">HamletName</th>';
		else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
	   str+='<th rowspan="2">Booth No</th>';
	   else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
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
	str+='</thead>';
	str+='<tbody>';
//if(innerResults == null ||innerResults.length )
//str = 'No Data Found';
//else
for(var i=0;i<innerResults.length;i++){
  if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';

	if(type == "constituency")
	 str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	 str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	 	if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	 str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	 str+='<td>'+innerResults[i].boothName+'</td>';
      else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
	   
	   
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
str+='</tbody>';
str+='</table>';

$('#agewiseDetails').html(str);

$('#mandalWiseVoterAgeTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		//"iDisplayLength":50,
		//"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  //"aoColumns": [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null] 
		});
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
		if(buildType == "hamlet")
		noteString = "Hamlet wise voters Age and gender details of "+obj.name+" in "+publicationYear;
		else
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
		else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise voters Age and gender details of "+obj.name+" in "+publicationYear;
	}
	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNote').html('<h4 style="color:#3F3939;margin-left: 4px;font-family: Verdana;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" style="width: 104%; max-width: 104%; margin: 1px -18px 1px 10px;" class="table table-hover table-bordered" id="mandalWiseAgeAndGenderTable">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	  	if(buildType == "hamlet")
		 str+='<th rowspan="2">HamletName</th>';
		 else
	  str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
	   str+='<th rowspan="2">Booth No</th>';
	    else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   
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
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
  if(innerResults[i].totalMaleVotesFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
		if(buildType == "hamlet")
		str+='<td>'+innerResults[i].hamletName+'</td>';
		else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
    else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';
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
   str+='</tbody>';
   str+='</table>';

$('#ageAndgenderWiseDetails').html(str);
$('#mandalWiseAgeAndGenderTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		//"iDisplayLength":50,
		//"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  //"aoColumns": [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null] 
		});
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
	   		if(buildType == "hamlet")
			noteString = "Hamlet wise voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
			else
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
	else if(type="hamlet" && obj.type == "hamletLocalArea"){
		innerResults = results.boothVotersDetails;
		noteString = "LocalArea wise  voters Age and gender(Percentage) details of "+obj.name+" in "+publicationYear;
	}

	if(innerResults.length == 0){
		return false;
	}

	$('#voterAgeAngGenderwiseDetailsNoteInPercent').html('<h4 style="color:#3F3939;margin-left: 4px;;font-family: Verdana;">'+noteString+'</h4>');

	var str='';

	str+='	<table border="1" id="mandalWiseAgePercentageTable"        style="width: 104%; max-width: 104%; margin: 1px -18px 1px 10px;" class="table table-hover table-bordered">';
	str+='<thead class="info">';
	str+='<tr>';
	if(type == "constituency")
	   str+='<th rowspan="2">Mandal Name</th>';
	else if(type == "mandal")
	   str+='<th rowspan="2">Panchayat Name</th>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	 str+='<th rowspan="2">Hamlet Name</th>';
	else
	   str+='<th rowspan="2">Booth No</th>';
	else if(type == "localElectionBody")
	   str+='<th rowspan="2">Ward</th>';
	else if(type == "ward")
	   str+='<th rowspan="2">Booth</th>';  
	       else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<th rowspan="2">LocalArea</th>';
	   
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
	str+='</thead>';
	str+='<tbody>';
for(var i=0;i<innerResults.length;i++){
 if(innerResults[i].totalVotersFor18To25 != null){
	str+='<tr>';
	if(type == "constituency")
	str+='<td>'+innerResults[i].tehsilName+'</td>';
	else if(type == "mandal")
	str+='<td>'+innerResults[i].panchayatname+'</td>';
	else if(type == "panchayat")
	if(buildType == "hamlet")
	str+='<td>'+innerResults[i].hamletName+'</td>';
	else
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "localElectionBody")
	str+='<td>'+innerResults[i].boothName+'</td>';
	else if(type == "ward")
	str+='<td>'+innerResults[i].boothName+'</td>';
	 else if(type="hamlet" && obj.type == "hamletLocalArea")
	   str+='<td>'+innerResults[i].localityName+'</td>';

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
   str+='</tbody>';
   str+='</table>';

$('#voterAgeAngGenderwiseDetailsInPercent').html(str);

$('#mandalWiseAgePercentageTable').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		//"iDisplayLength":50,
		//"aLengthMenu": [[50, 100, 200, 500,1000,-1], [50, 100, 200, 500,1000,"All"]],
		//"bFilter": false,"bInfo": false
		  //"aoColumns": [null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null] 
		});

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
      $("#votersBasicInfoSubChartDiv").html('');
	  $("#votersBasicInfoSubDiv").html('');
	  
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
		
		if(jsObj.type=="panchayat"){
		if(buildType=="hamlet"){
			var totalvoter=votersbasicinfo.assignedVotersByUser+votersbasicinfo.unassignedVotersByUser;
			strl ='';
    			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
    			strl += '<tbody><td style="text-align:center;">'+totalvoter+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersByUser+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersByUser+'</td></tbody>';
			
			strl += '</table>';
			$("#assAndUnass").html(strl);
			}
			else{
			$("#assAndUnass").html('');
			}
		}
				
		else if(jsObj.type=="hamlet" && jsObj.resultFor == "localArea"){
			var totalvoterlclbds=votersbasicinfo.assignedVotersForLocalBodies+votersbasicinfo.unassignedVotersForLocalBodies;
			strl ='';
			strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Total Voters</th><th style="text-align:center;">Assigned by User</th><th style="text-align:center;">UnAssigned Voters</th></thead>';
			strl += '<tbody><td style="text-align:center;">'+totalvoterlclbds+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersForLocalBodies+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersForLocalBodies+'</td></tbody>';
			
			strl += '</table>';
				strl += '<table class="table tableas table-bordered" style="margin-top:20px;"><thead><th style="text-align:center;">Voters In Publication</th><th style="text-align:center;">Assigned by User In publication </th><th style="text-align:center;">Voters Need To Be Assigned </th></thead>';
    			strl += '<tbody><td style="text-align:center;">'+votersbasicinfo.publicationVoters+'</td><td style="text-align:center;">'+votersbasicinfo.assignedVotersByUser+'</td><td style="text-align:center;">'+votersbasicinfo.unassignedVotersByUser+'</td></tbody>';
				strl += '</table>';
			$("#assAndUnass").html(strl);
			
		}
		else{
			$("#assAndUnass").html('');
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
				if(jsObj.type != "booth"){
				   if((jsObj.type == 'mandal' && jsObj.id.substring(0,1) == "1") || jsObj.type == 'ward')
				    str +='<th>Total Booths/Wards</th>';
				   else
				     str +='<th>Total Booths</th>';
				}
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
				  if(jsObj.type != "booth")
                  str += '<td>'+results[j].totalBooths+'</td>';
				  str +='<td>'+results[j].totalVotes+'</td>';
					str +='<td>'+results[j].polledVotes+'</td>';
					var partyVotesEarnedVOs = results[j].partyVotesEarnedVOs;
					  for(var k in partyVotesEarnedVOs)
					{
						 
						  if(partyVotesEarnedVOs[k].votesEarned == 0)
						 str +='<td style="text-align:center">-</td>';
						  else
				         str +='<td>'+partyVotesEarnedVOs[k].votesEarned+'</td>';
					}
						
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
			constituencyId:$("#constituencyList").val(),
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
			constituencyId:$("#constituencyList").val(),
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
		  if(jsObj.type=maintype){
		  $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">'+btnAgeLnk+'</a>');
		  /* if(maintype == "hamlet"){
		   $("#impFamiliesMoreInfoButn").css('display','none');
		   }
		  else{ */
			$("#impFamiliesMoreInfoButn").attr("value",btnFmlyLnk);
		 //}
		 }
		   
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
	if(results[0].totalPanchayats !=null && results[0].totalPanchayats!=0 ){
	
	str +='<span class="btn btn-info btn-small">'+results[0].totalPanchayats+'</span><span class="help-inline f2">Panchayats</span>';
	str +='<span class="btn btn-info btn-small">'+results[0].totalNoOfHamlets+'</span><span class="help-inline f2">Hamlets</span>';
	}if(results[0].totalBooths !=null && results[0].totalBooths!=0 )
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
				constituencyId:$("#constituencyList").val(),
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
	
	str +='<span class="btn btn-info">'+results[0].totalNoOfHamlets+'</span><span class="help-inline f2">Hamlets</span>';
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
				$('#votersInfoMoreShowHide').hide();
				$('#votersBasicInformationDiv').hide();
				$('#votersBasicInfoDiv1').html("<span style='margin-left:15px;color:red;'> No  Data Available</span>");
			return false;
			}
			
			if(jsObj.type!= maintype){
			
			return false;
			}
			$('#votersBasicInformationDiv').show();
			//$('#votersInfoMoreShowHide').show();
			
			
         
		
			var str='';

			str+='<table id="voterBasicInfoTable" class="table table-bordered table-striped table-hover" style="width: 104%; max-width: 104%; margin: 1px -18px;">';
			 str+='<thead class="info"><tr>';
			  str+='<th>Year</th>';
			 if(jsObj.type != "booth" && jsObj.type != "hamlet")
			  str+='<th>Booths</th>';
		      str+='<th>Total</th>';
		 	  str+='<th>Male</th>';
		 	  str+='<th>Female</th>';
			  str+='<th>Total Difference</td>';
			  str+='<th>Male Difference</td>';
		  	  str+='<th>Female Difference</td>';
			 str+='</tr></thead><tbody>';


			 for(var i=myResults.length;i>=1;i--){

			  str+='<tr>';

			  if(myResults[i-1].isPublication =="false")
			    str+='<td><span title='+myResults[i-1].electionDate+'>'+myResults[i-1].electinYear+'</span><b style="color:red">&nbsp;(E)<b></td>';
			  else 
				str+='<td><span title='+myResults[i-1].publicationDate+'>'+myResults[i-1].electinYear+'</span><b style="color:red">&nbsp;(P)<b></td>';
              if(jsObj.type != "booth" && jsObj.type != "hamlet")
			  str+='<td>'+myResults[i-1].totalBooths+'</td>';
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
			 $("#assAndUnass").html('');
			 $("#votersBasicInfoSubDiv").html('');
			 $("#votersBasicInfoSubChartDiv").html('');
			getvotersBasicInfo("voters",mainreqid,mainpublicationId,maintype);
			
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
  var str1="";
		 switch(menutype)
		 {
		
		case 0: // Build Mandal Menu
		  if(menudata[0].mandalList!=null){
		  $.each(menudata[0].mandalList, function(iter,mandals){
            str+='<li><a onClick="" data-mandalid="'+ mandals.id+'" name-mandal="'+mandals.name+'"><span class="checkbox"><input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+mandals.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i> '+mandals.name.replace('MANDAL','')+'</a></li>';
			/*replace() method added by prasad Thiragabthina to remove "MANDAL" string at the end of Mandal name*/
		  });}
		  $(".middleNav ul").html("");
		  $(".rightNav-Booths").hide();
		  $(".middleNav-Panchayats").hide();
		  $(".middleNav-Wards").hide(); 
		  $("#tabContainer").css({ display: "none" });
		  $(".tabscontent").css({ display: "none" });

		 break;
		
		 case 1: // Build municipality Menu	
		  if(menudata[0].localbodiesList!=null){
		  	$.each(menudata[0].localbodiesList, function(iter,municipality){
			if( municipality.selectOptionsList != null){
		  	str+='<li><a onClick="" dest-atr="ward"  data-municipalityid="'+ municipality.id+'" name-muncipal="'+municipality.name+' " ><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+municipality.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i>'+municipality.name.replace('Greater Municipal Corp','')+'</a></li>';
		  } else if(municipality.selectOptionsList1 != null){
		  	str+='<li><a onClick="" dest-atr="booth" data-municipalityid="'+ municipality.id+'" name-muncipal="'+municipality.name+' "><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+municipality.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup"></span><i class="icon-chevron-right"></i>'+municipality.name.replace('Greater Municipal Corp','')+'</a></li>';
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
			$(".tabscontent").css({ display: "block" });
		 break;
		 
		 
		 case 3: // Build Booths Menu
		  $.each(menudata[0].mandalList, function(iter,mandals){
		     if(mandalid==mandals.id && mandals.selectOptionsList != null){
					  $.each(mandals.selectOptionsList,function(iter,panchayats){
							 if(panchayatid==panchayats.id && panchayats.selectOptionsList != null){
									  $.each(panchayats.selectOptionsList,function(iter,booths){
									str1+='<li class="nav nav-pills"><a class="btn" data-panchayatid="'+ panchayats.id+'" data-mandalid="'+ mandals.id+'" data-boothid="'+ booths.id+'" name-booth="Booth-'+booths.name+'" href="javascript:{}" style="width:44px;"><span class="checkbox"><input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</span></a></li>';
									 });
								}
								if(panchayatid==panchayats.id && panchayats.selectOptionsList1 != null){
									  $.each(panchayats.selectOptionsList1,function(iter,hamlets){
									str+='<li class="nav nav-pills "><a class="btn wid " data-panchayatid="'+ panchayats.id+'" data-mandalid="'+ mandals.id+'" data-hamletid="'+ hamlets.id+'" name-hamlet="'+hamlets.name+'" href="javascript:{}" style="width:44px;"><span class="checkbox marg"><input type="radio" data-mandalid="'+ mandals.id+'" id="Chk-'+hamlets.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span><span class="marg1">'+hamlets.name+'</span></a></li>';
									 });
								}
					 }); 
			}
		  });
		  $(".rightNav-Booths").hide();
		 break;
		 
		case 4: // Build Booths Menu For Munucipalities
		 	   $.each(menudata[0].localbodiesList, function(iter,municipality){
		     if(municipalityid==municipality.id && municipality.selectOptionsList1 != null){
					  $.each(municipality.selectOptionsList1,function(iter,booths){
									str+='<li class="nav nav-pills"><a class="btn"  data-municipalityid="'+ municipality.id+'" data-municipalityid="'+ municipality.id+'" data-boothid="'+ booths.id+'" name-booth="Booth-'+booths.name+'" href="javascript:{}" style="width:44px;"><span class="checkbox"><input type="radio" data-mandalid="'+ municipality.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
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
									str+='<li><a onClick="" data-wardid="'+ wards.id+'" data-municipalityid="'+ municipality.id+'" name-ward="'+wards.name+'"><span class="checkbox"> <input type="radio" data-municipalityid="'+ municipality.id+'" id="Chk-'+wards.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span><i class="icon-chevron-right"></i>'+wards.name+'</a></li>';
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
						str+='<li class="nav nav-pills"><a class="btn" data-wardid="'+ wards.id+'" data-municipalityid="'+ municipality.id+'" data-boothid="'+ booths.id+'" name-booth="Booth-'+booths.name+'" href="javascript:{}"><span class="checkbox"><input type="radio" data-municipality="'+ municipality.id+'" id="Chk-'+booths.id+'" style="margin-top: -2px; margin-right: 4px;" name="menugroup" ></span>'+booths.name+'</a></li>';
									 });
								}
					 }); 
			}
			  });
		   $(".rightNav-Booths").show();
		 break;
		 
		
			}	
		
		$(this).html(str);
		
		$("#rightNav-Booths-list2").html(str1);
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
loadMyTabs();
//$('#tabpage_1').alternateScroll();
$("#tabpage_2").css({ display: "none" });
$("#tabContainer").css({ display: "none" });
//$("#forHide").css({ display: "none" });

$("#leftNav-Mandals-list a").live("click",function(){
$(".leftNav li").removeClass("active");
$(this).closest("li").addClass("active");//.siblings().removeClass("active");

$("#middleNav-Panchayats-list").customMenu(resultDataForNav,2,$(this).attr("data-mandalid"));
hideMyTabs();
});

$("#leftNav-Municipalities-list a").live("click",function(){
$(".leftNav li").removeClass("active");
$("#tabContainer").css({ display: "none" });
//$(this).closest("li").addClass("active").siblings().removeClass("active");
$(this).closest("li").addClass("active");
var dest=$(this).attr("dest-atr");
  if(dest === "booth"){
    $("#rightNav-Booths-list").customMenu(resultDataForNav,4,0,0,$(this).attr("data-municipalityid"));
	$("#impFamiliesMoreInfoButn").css('display','none');
	$("#ageLink").css('display','none');
	$("#votersShareBtn1").css('display','none');
	}
  else if(dest === "ward"){
	$("#middleNav-Wards-list").customMenu(resultDataForNav,5,0,0,$(this).attr("data-municipalityid"));
	//$("#impFamiliesMoreInfoButn").css('display','none');
	//$("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Ward Wise Age Details</a>');
	$("#tabContainer").css({ display: "none" });
	}
});


$("#middleNav-Panchayats-list a").live("click",function(){
$(this).closest("li").addClass("active").siblings().removeClass("active");
$("#rightNav-Booths-list1").customMenu(resultDataForNav,3,$(this).attr("data-mandalid"),$(this).attr("data-panchayatid"));
//$("#tabHeader_1").trigger('change');
//$("#tabContainer").css({ display: "block" });
showMyTabs();
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
hideMyTabs();
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
showMyTabs();
$(this).find("input").attr("checked",true);
var mandalid=$(this).closest("a").attr("data-mandalid");
var panchayatid=$(this).closest("a").attr("data-panchayatid");
var levelId =3;
mainreqid = panchayatid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'panchayat';
mainname = $(this).closest("a").attr("name-panchayat");
scrollToNewsDiv();
$("#tabHeader_2").trigger("click");
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
$("#rightNav-Booths-list2 a .checkbox").live("click",function(){
buildForBooths(this);
});
//hamlets clicked
$("#rightNav-Booths-list1 a .checkbox").live("click",function(){
buildForHamlets(this);
});

});

// tabevents clicked
 //$("#tabHeader_1").live("change",function(){
 //buildType="hamlet"; 
 //$("#tabHeader_1").addClass("tabActiveHeader");
 //$("#tabHeader_2").removeClass("tabActiveHeader");
 //});
 $("#tabHeader_1").live("click",function(){
buildType="hamlet"; 
  
	 if( maintype == "panchayat" ){
	 
	  $("#impFamiliesMoreInfoButn").attr("value","View Hamlet Wise Family Details");
       $("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Hamlet Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Hamlet Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
       $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Hamlet Wise Age Details</a>');
	// getvotersBasicInfo("impFamilies",mainreqid,mainpublicationId,"panchayat");	
	 }

});
$("#tabHeader_2").live("click",function(){
buildType="booth";
 $("#tabpage_2").css({ display: "block" });
 if( maintype == "panchayat" ){
	 $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Booth Wise Age Details</a>');

$("#impFamiliesMoreInfoButn").attr("value","View Booth Wise Family Details");
$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>Booth Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know Booth Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
	// getvotersBasicInfo("impFamilies",mainreqid,mainpublicationId,"panchayat");	
 }
});

/** END FUNCTIONS FOR NAVIGATIONS **/


function buildForBooths(obj)
{ 
$(obj).find("input").attr("checked",true);
var mandalid=$(obj).closest("a").attr("data-mandalid");
var levelId =4;
var panchayatid=$(obj).closest("a").attr("data-panchayatid");
var boothid=$(obj).closest("a").attr("data-boothid");
mainreqid = boothid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'booth';
mainname = $(obj).closest("a").attr("name-booth");
scrollToNewsDiv();
$(obj).closest("li").siblings().find('a').removeClass("btn-primary");

$(obj).closest("a").addClass("btn-primary");

getAllTabs(boothid,$("#publicationDateList").val(),'booth');

}
function buildForHamlets(obj)
{ 
$(obj).find("input").attr("checked",true);
var mandalid=$(obj).closest("a").attr("data-mandalid");
$(".wid").removeClass("btn-primary");
$(obj).closest("a").addClass("btn-primary");
$(".marg1").removeClass("mybtn");
$(obj).siblings().addClass("mybtn");


var levelId =6;
var panchayatid=$(obj).closest("a").attr("data-panchayatid");
var boothid=$(obj).closest("a").attr("data-hamletid");
mainreqid = boothid;
mainpublicationId = $("#publicationDateList").val();
maintype = 'hamlet';
mainname = $(obj).closest("a").attr("name-hamlet");
scrollToNewsDiv();
//alert(mainreqid+maintype+mainname);
 //$("#votersShareBtn1").hide();
 
	 	 $("#votersShareBtn1").css('display','block');
	   $("#impFamiliesMoreInfoButn").attr("value","View Localities Wise Family Details");
      $("#votersShareBtn1").html("");  
 		var astr="";
			 astr += "<div id='tabsForLocal'  class='thumbnail1' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>";
             astr += "<div id='sse2' style='display:none' >";
             astr += "<div id='sses2'>";
             astr += "<ul>";
            astr += "<li class='btn btn-success btn-small highlight11' style=' margin-left:300px;' align='center' ><a href='javascript:{getvotersBasicInfo(\"voters\","+mainreqid+","+mainpublicationId+",\"hamletBooth\")}'>Booth Wise Voters Info of "+mainname+"</a></li>";
            astr += "<li id='myli'style='display:none'><a href='javascript:{getvotersBasicInfo(\"voters\","+mainreqid+","+mainpublicationId+",\"hamletLocal\")}'>LocalArea Wise Voters Info of "+mainname+"</a></li>";
            astr += "</ul>";
			astr += "<span style='display:none;margin-bottom: 12px;' id='ajaxImageDiv1'><img src='./images/icons/search.gif' /></span>";
			astr += "</div>";
			astr += "</div>";
			astr += "</div>";
			$("#votersShareBtn1").html(astr);	  
	  //$("#votersShareBtn1").html("<div id='cnstHeading'  class='thumbnail' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;'>LocalArea Wise Voters Info of "+mainname+"<span id='votersShareBtn' class='btn' title='Click Here to know LocalArea Wise Voters Info of "+mainname+ "' style='margin-left: 15px;'>Show</span><span style='display:none;' id='votersInfoAjaxImg'><img src='./images/icons/search.gif' /></span></div>");
       $("#ageLink").html('<a class="btn btn-info" href="javaScript:{showAllAgewiseDetails()}">View Localities Wise Age Details</a>');
	// getvotersBasicInfo("impFamilies",mainreqid,mainpublicationId,"panchayat");	
		//mybuildMenu();
getAllTabs(boothid,mainpublicationId,maintype);

}

function buildVotersInFamilyWithRetrievedResults(){


	var results = pResults;

    $("#multipleVoterFamiliesEditDiv").html("");
   // $("#impFamDtlsTitle").html("<b>Voter Details</b>");
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

	 for(var i in reqfieldsArr){
		YAHOO.widget.DataTable["select"+i] = function(elLiner, oRecord, oColumn, oData) 
	  { 

		 var ids = reqfieldsArr[oColumn.field.charAt(oColumn.field.length-1)].split(",");


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


     var votersResultColumnDefs = [ 		    	             
		    	            {key:"select", label: "Select", formatter:YAHOO.widget.DataTable.select},
							//{key:"sNo", label: "SNo", sortable: true},
		    	           	{key:"name", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.NameLink},
							//{key:"gender", label: "Gender", sortable: true},
		    				//{key:"age", label: "Age",sortable:true},
							{key:"houseNo", label: "House No",sortable:true},
							//{key:"gaurdian", label: "Guardian Name",sortable:true},
							//{key:"relationship", label: "Relationship",sortable:true},
							//{key:"mobileNo",label:"Mobile No",sortable:true}
						]; 
             if($("#ageId").is(':checked')){

				obj = {key:"age",label: "Age",sortable: true};
				votersResultColumnDefs.push(obj);
			 }

			if($("#guardianNameId").is(':checked')){

				obj = {key:"gaurdian",label: "Guardian Name",sortable: true};
				votersResultColumnDefs.push(obj);
			}

			if($("#relationShipId").is(':checked')){

				obj = {key:"relationship",label: "Relationship",sortable: true};
				votersResultColumnDefs.push(obj);
			}

			if($("#genderId").is(':checked')){

				obj = {key:"gender",label: "Gender",sortable: true};
				votersResultColumnDefs.push(obj);
			}

			if($("#mobileId").is(':checked')){

				obj = {key:"mobileNo",label: "Mobile No",sortable: true};
				votersResultColumnDefs.push(obj);
			}

			if($("#casteId").is(':checked')){

				obj = {key:"cast",label: "Caste",sortable: true};
				votersResultColumnDefs.push(obj);
			}

          if($("#partyId").is(':checked')){

				obj = {key:"party",label: "Party",sortable: true};
				votersResultColumnDefs.push(obj);
			}

			
			 for(var i in reqfieldsArr){

			 //console.log(reqfieldsArr);
		    var ids = reqfieldsArr[i].split(",");

		      obj = {
				key:"select"+i, label: ""+ids[1], formatter:YAHOO.widget.DataTable["select"+i]
					};
					votersResultColumnDefs.push(obj);
		      }
    var myConfigs = { 
			    
				};
	var myDataSource = new YAHOO.util.DataSource(results);

	myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
	myDataSource.responseschema = {
		 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterId","boothId"
		 ,"categoriesList","cast","party"]
	};

		var familesDataSource = new YAHOO.widget.DataTable("impFamDtls", votersResultColumnDefs,myDataSource, myConfigs);
    $("#impFamDtlsOuterPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });

		
 reqfields = "";
 reqfieldsArr = new Array();
}

function checkForAttributesToDisplay(){


	 $('.attributeTypeClassIni1').each(function() {
           if($(this).is(':checked')){
		        var ids = ($(this).val()).split(",");
		       reqfieldsArr.push($(this).val());
		       reqfields = reqfields+","+ids[0];
		    }
          });
		   if(reqfields.length > 0)
		  reqfields = reqfields.slice(1);

		   buildVotersInFamilyWithRetrievedResults();

}

function buildCategories(results){

    var str='';

	for(var i in results){
      
       str+='<label style="float:left;margin:3px;"><input type="checkbox" style="margin:0px 7px 4px 0px;" class="attributeTypeClassIni1" value="'+results[i].id+','+results[i].name+'"/>'+results[i].name+'</label>';
	}

	$('#impFamilySelectedDetails1').html(str);

}
function getBoothInfo(){
        var jsObj=
					{					
						boothId:mainreqid,
						task:"getBoothInfo"
					};

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getBoothBasicInfoAction.action?"+rparam;

			callAjax(jsObj,url);
}
function showBoothInfo(result){
boothsLoc = result;
var str = "<h4 style='margin-top: 6px; margin-bottom: 8px;'>Booth Location In Various Publications</h4><div>Select <select id='boothprevlocs' onchange='showPrevBoothLocs();'></select> to get previous booth locations</div><br/>";
  if(result != null && result.length > 0){
       $("#selectedBoothInfo").show('');	
      for(var i in result){
	      if(result[i].type == "true")
		  str+=result[i].location;
	  }
	   $("#selectedBoothInfo").html(str);
	      $("#boothprevlocs").append('<option value="0">All</option>');
	    for(var i in result){
	      if(result[i].type == "true")
		   $("#boothprevlocs").append('<option value='+result[i].id+' selected=selected>'+result[i].name+'</option>');
		  else
		   $("#boothprevlocs").append('<option value='+result[i].id+' >'+result[i].name+'</option>');
	  }
   }
   
}

function showPrevBoothLocs(){
  if($("#boothprevlocs").val() == '0'){
     var str = '';
     for(var i in boothsLoc){
			str+=boothsLoc[i].location;
	  }
	  $("#boothlocval").html(str);
  }
  else{
	  for(var i in boothsLoc){
		 if(boothsLoc[i].id == $("#boothprevlocs").val())
			$("#boothlocval").html(boothsLoc[i].location);
	  }
  }
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

	$('#influencePeopleOuterDiv').dialog({ 
	                            title:'Search Influence People',
	                            height: 'auto',
								width: 730,
								closeOnEscape: false,
								show: "blind",
								hide: "explode",
								modal: true,
	                             buttons: {
							   "Close":function() {$(this).dialog("close")}
								   }	

     });


	 getDistrictsInAState();

}


function showLocationsDiv(){
		$('#searchButtonId').show();
		if($('#scopeId').val() == "0")
		{
			$('#regionstitleDiv').css('display','none');
			$('#locationsDiv').css('display','none');
		}
		else
	    {
			$('#regionstitleDiv').css('display','block');
			$('#locationsDiv').css('display','block');
		}

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').find('option').remove();

		$('#constituencySelectId ,#mandalSelectId , #wardSelectId ,#wardSelectId,#boothSelectId').append('<option value="0">Select</option>');

		$('#districtSelectId').val("0");

	if($('#scopeId').val() == "2")
	{
		$('#stateSelect').css('display','block');
		$('#districtSelect , #constituencySelect , #mandalSelect , #wardSelect , #boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "3")
	{
		$('#stateSelect , #districtSelect').css('display','block');

		$('#constituencySelect , #mandalSelect ,#wardSelect ,#boothSelect').css('display','none');
		
	}
	else if($('#scopeId').val() == "4")
	{
		$('#stateSelect , #districtSelect , #constituencySelect').css('display','block');

		$('#mandalSelect , #wardSelect , #boothSelect').css('display','none');
	
	}
	else if($('#scopeId').val() == "5" || $('#scopeId').val() == "7")
	{
		$('#stateSelect , #districtSelect , #constituencySelect ,#mandalSelect ').css('display','block');
		
		$('#wardSelect , #boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "6" || $('#scopeId').val() == "8")
	{
		$('#stateSelect , #districtSelect , #constituencySelect , #mandalSelect , #wardSelect ').css('display','block');
		
		$('#boothSelect').css('display','none');
	}
	else if( $('#scopeId').val() == "9")
	{
		$('.locationDivClass').css('display','block');
	}

}

function getMandalsOrMuncipalities()
{
	if($('#scopeId').val() == "5" || $('#scopeId').val() == "6")
      getMandalsOrMuncipalities1("RURAL");
	else if($('#scopeId').val() == "7" || $('#scopeId').val() == "8")
	 getMandalsOrMuncipalities1("URBAN");
	else if($('#scopeId').val() == "9")
	 getMandalsOrMuncipalities1("");

}

function getHamletsOrWards(){

	if($('#scopeId').val() == "9"){
 	 getHamletsOrRegions();
	 getBoothsInTehsilOrMunicipality();
	}else{
		  getHamletsOrRegions();
  
	}
   //if($('#scopeId').val() == "6")
	  // getHamletsOrRegions('hamlets');
  // else if($('#scopeId').val() == "8")
   	   //getHamletsOrRegions('wards');

}

function callAjaxToSearchInfluencingPeople(voterId)
{

	$('#searchResultsDiv').show();
	$('#totalCountId').show();
	$('#districtErrMsg ,#constituencyErrMsg , #tehsilOrMuncipalityErrMsg ,#villageOrWardErrMsg , #boothErrMsg , #searchResultsDiv , #nameErrMsg').html('');
	var isValid = true;

	var scopeSelected = $('#scopeId').val();

	if(scopeSelected == "3"){
		if($('#districtSelectId').val() == "0")
		{
			 isValid = false; 
			$('#districtErrMsg').html('District is required');
		}

	}
	else if(scopeSelected == "4"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "5"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			 isValid = false;
		}

	}
	else if(scopeSelected == "6"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Mandal is required');
			isValid = false;
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Village is required');
			isValid = false;
		}

	}
	else if(scopeSelected == "7"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			isValid = false;
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}

	}
	else if(scopeSelected == "8"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			isValid = false;

		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
	}
	else if(scopeSelected == "9"){
		if($('#districtSelectId').val() == "0")
		{
			$('#districtErrMsg').html('District is required');
			 isValid = false; 
		}
		if($('#constituencySelectId').val() == "0")
		{
			$('#constituencyErrMsg').html('Constituency is required');
			 isValid = false; 
		}
		if($('#mandalSelectId').val() == "0")
		{
			$('#tehsilOrMuncipalityErrMsg').html('Muncipality is required');
			 isValid = false; 
		}
		if($('#wardSelectId').val() == "0")
		{
			$('#villageOrWardErrMsg').html('Ward is required');
			 isValid = false; 
		}
		if($('#boothSelectId').val() == "0")
		{
			$('#boothErrMsg').html('Booth is required');
			 isValid = false; 
		}
	}

	if($('#nameId').val() == "")
	{
		$('#nameErrMsg').html('Name is required');
		isValid = false; 
	}


	if(isValid == false)	
		isValid = true;
	else	
     callAjaxToSearchInfluencingPeople1(voterId);

}

function callAjaxToSearchInfluencingPeople1(voterId)
{

document.getElementById('ajaxImageDiv1').style.display = 'block';



	var stateVal = 0;
	var districtVal = 0;
	var constituencyVal = 0;
	var mandalVal = 0;
	var muncipalityVal = 0;
	var villageVal = 0;
	var wardVal = 0;
	var boothVal = 0;
	var name="";
	var fatherOrSpouceName = "";

	name=$('#nameId').val();
	fatherOrSpouceName = $('#fatherNameId').val();

	var scopeVal = $('#scopeId').val();

	if(scopeVal == "2")
		stateVal = $('#stateSelectId').val();
	else if (scopeVal == "3")
	{
	     districtVal = $('#districtSelectId').val();
	}
	else if (scopeVal == "4")
	{
	     constituencyVal = $('#constituencySelectId').val();
	}
	else if (scopeVal == "5" || scopeVal == "7")
	{	
		 var mandalTemp = $('#mandalSelectId').val();

		 if(mandalTemp.substring(0,1) == "2")
			mandalVal =  mandalTemp.substring(1);
		 else if(mandalTemp.substring(0,1) == "1")
			muncipalityVal =  mandalTemp.substring(1);
    
	}
	else if (scopeVal == "6" || scopeVal == "8")
	{

		 var villageTemp = $('#wardSelectId').val();

		 if(villageTemp.substring(0,1) == "2")
			villageVal =  villageTemp.substring(1);
		 else if(villageTemp.substring(0,1) == "1")
			wardVal =  villageTemp.substring(1);
	}
	else if (scopeVal == "9")
	{
         boothVal = $('#boothSelectId').val();
	}


	var jsObj=
			{   scope:scopeVal,
				name:name,
				state:stateVal,
 			    district: districtVal,
                constituency:constituencyVal,
                mandal:mandalVal,
				muncipality:muncipalityVal,
				hamlet:villageVal,
				ward:wardVal,
				booth:boothVal,	
				fatherOrSpouceName:fatherOrSpouceName,
				voterId:voterId,
				task:"getInfluencingPeopleBySearch"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getInfluencingPeopleBySearch.action?"+rparam;						
		callAjax(jsObj,url);
		

}

function buildInfluencePeopleSearchResults(results,voterId){
	 
    $("#searchResultsDiv").html("");
	$('#totalCountId').show();
	var totalRecords = results.length;
	$('#totalCountId').html('<span style="float: none;position: relative;top: 5px;"><b>Total Records: </b><b style="color: red;">'+totalRecords+'</b></span>');
	 YAHOO.widget.DataTable.select = function(elLiner, oRecord, oColumn, oData) 
	{
	    var str='';
		var name = oData;
		var id= oRecord.getData("influencePersonId");

		str+='<span><a href="javaScript:{mapAsInfluencePeople('+id+','+voterId+');}" class="btn btn-mini" ">Map to voter</a></span>';
	
		elLiner.innerHTML=str;
					
	};

	
     var influencePeopleColumnDefs =
		                [ 
						 {key:"firstName",label:"First Name",sortable:true ,width:150},
						 {key:"lastName",label:"Last Name",sortable:true,width:120},
   		                 {key:"contactNumber",label:"Mobile No",sortable:true,width:120},
		                 {key:"Select",label:"Select", formatter:YAHOO.widget.DataTable.select,width:200}
						]; 

     var myConfigs =
		        { 
			        paginator : new YAHOO.widget.Paginator({ 
		              rowsPerPage    : 10
			         }) 
				};
			    
				
	 var myDataSource = new YAHOO.util.DataSource(results);
		 myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
		 myDataSource.responseschema = {
						 fields : ["firstName","lastName"]
					};

	 var familesDataSource = new YAHOO.widget.DataTable("searchResultsDiv", influencePeopleColumnDefs,myDataSource, myConfigs);
 
}


function mapAsInfluencePeople(id,voterId){


	var jsObj=
			{
				influencePersonId:id,
                voterId:voterId,
				task:"mapVoterAsInfluencingPerson"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "mapVoterAsInfluencingPerson.action?"+rparam;						
		callAjax(jsObj,url);
}


function getDistrictsInAState(){

	var jsObj=
			{
				id:1,
				task:"districtsInState"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}

function getConstituenciesInDistrict(){

	var jsObj=
			{
				id:$('#districtSelectId').val(),
				task:"constituenciesInDistrict"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);
}


function getMandalsOrMuncipalities1(areaType){

	var jsObj=
			{
				id:$('#constituencySelectId').val(),
				task:"subRegionsInConstituency",
				areaType:areaType
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function getHamletsOrRegions(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				task:"hamletsOrWardsInRegion"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}


function getBoothsInTehsilOrMunicipality(){

	var jsObj=
			{
				id:$('#mandalSelectId').val(),
				constId:$('#constituencySelectId').val(),
				task:"boothsInTehsilOrMunicipality"
			}
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getLocationsById.action?"+rparam;						
		callAjaxForLocations(jsObj,url);

}

function callAjaxForLocations(jsObj,url)
{
			
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "districtsInState")
								{
									buildDistricts(myResults);										
								}
								else if(jsObj.task == "constituenciesInDistrict")
								{
                                     buildConstituencies(myResults);
								}
								else if(jsObj.task == "subRegionsInConstituency")
								{
									buildMandalsOrMuncipalities(myResults);
								}
								else if(jsObj.task == "hamletsOrWardsInRegion")
								{
									buildHamletsOrWardsInRegion(myResults);

								}
								else if(jsObj.task == "boothsInTehsilOrMunicipality")
								{
									buildBooths(myResults);
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

function buildDistricts(results)
{

	$('#districtSelectId').find('option').remove();
	$('#districtSelectId').append('<option value="0">Select</option>');

	for(var i=0;i<results.length;i++)
		$('#districtSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildConstituencies(results)
{
  $('#constituencySelectId').find('option').remove();
  $('#constituencySelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#constituencySelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}

function buildMandalsOrMuncipalities(results)
{
 $('#mandalSelectId').find('option').remove();
 $('#mandalSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#mandalSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}


function buildHamletsOrWardsInRegion(results){

	$('#wardSelectId').find('option').remove();
	$('#wardSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#wardSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');


}

function buildBooths(results){

	$('#boothSelectId').find('option').remove();
	$('#boothSelectId').append('<option value="0">Select</option>');

  for(var i=0;i<results.length;i++)
		$('#boothSelectId').append('<option value="'+results[i].id+'">'+results[i].name+'</option>');

}
function showPartyWiseDetailsInGraph()
{
	 $("#partyGraphDiv").dialog({
            modal: true,
            title: "<b>Voters Details - Party Wise</b>",
			width: 980,
            height: 500
           
        });
}
//added by mahesh for adding cadre to voter	
function mapSelectedCadreToVoter(cid,cName,voterId){
	 var jsObj=
		{
			 cadreId:cid,
			 voterId:voterId,
			 task:"addVoterToCadre"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "addVoterToCadreAction.action?"+rparam;	
		callAjax(jsObj,url);
}	
function openCadreSearchWindow(clickedId){
  
	var cadreWindow = window.open("cadreSearchAction.action?windowTask=Search&voterId="+clickedId,"cadreSearch","scrollbars=yes,height=600,width=750,left=200,top=200");
  cadreWindow.focus();
}
function getPreAndPresentPublicationDtaeList()
{
	
	var selectedElmt=document.getElementById("prevpublicationDateIdsList");
	var presentPublicationList = document.getElementById("prespublicationDateIdsList");

	removeSelectElements(selectedElmt);
	removeSelectElements(presentPublicationList);

	for(var val in publicationDatesList)
	{
		var opElmt = document.createElement('option');
		opElmt.value=publicationDatesList[val].id;
		opElmt.text=publicationDatesList[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	for(var val in publicationDatesList)
	{
		var opElmt = document.createElement('option');
		opElmt.value=publicationDatesList[val].id;
		opElmt.text=publicationDatesList[val].name;

		try
		{
			prespublicationDateIdsList.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			prespublicationDateIdsList.add(opElmt); // IE only
		}	
	}
}


function getInfluencingPeopleCount(locationValue,type)
{

	document.getElementById('InfluencingPeopleCountDiv').style.display = 'none';
	var publicationId = $("#publicationDateList").val();
	$("#InfluencingPeopleAjaxImg").show();
	var jObj=
	{
		locationValue:locationValue,
		type : type,
		publicationDateId:publicationId,
		name             :mainname,
		task:"getInfluencingPeopleCount"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jObj);
	var url = "getInfluencingPeopleCountAction.action?"+rparam;	

	callAjax(jObj,url);
}

function buildInfluencingPeopleCount(results,jsObj)
{
	$("#InfluencingPeopleAjaxImg").hide();
	var str='';
	document.getElementById('InfluencingPeopleCountDiv').style.display = 'block';
	var locationValue =jsObj.locationValue;
	var name = jsObj.name;
	var	typeValue = jsObj.type;
	var	publicationDateId = jsObj.publicationDateId;
	$("#InfluencingPeopleCountDiv").css({'width':'96%','color':'#000000'});
	var divEle = document.getElementById('InfluencingPeopleCountDiv');
	
	str+='<h4 style="margin: 0px -20px; padding: 10px 10px 10px 20px;" class="">Influencing People , Cadre , and Politicians in '+jsObj.name+'</h4><div class="widget-block breadcrumb">';
	//str+='<div class="span11 breadcrumb"></div>';
	//str+='<div class="widget-block breadcrumb"> ';
	//str+='<div class="row-fluid"> ';
	if(results != null)
	{
		if(results[0].influencePeopleCount > 0)
	  str +='<a id ="InfluencingDetails" onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'InfluencePeople\');"><span class="btn">'+results[0].influencePeopleCount+'</span></a><span class="help-inline f2"> Influencing People</span>';
		else
		str +='<a id ="InfluencingDetails"><span class="btn">'+results[0].influencePeopleCount+'</span></a><span class="help-inline f2"> Influencing People</span>';
		if(results[0].cadreCount > 0)
	  str +='<a id ="CadreDetails" onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'Cadre\');"><span class="btn">'+results[0].cadreCount+'</span></a><span class="help-inline f2"> Cadre</span>';
		else
		str +='<a id ="CadreDetails"><span class="btn">'+results[0].cadreCount+'</span></a><span class="help-inline f2"> Cadre</span>';
		if(results[0].politicianCount > 0)
	  str +='<a id ="PoliticianDetails" onclick="getInfluencingPeopleVotersDetails('+locationValue+',\''+maintype+'\','+publicationDateId+',\'Politician\');"><span class="btn">'+results[0].politicianCount+'</span></a><span class="help-inline f2"> Politician</span>';
		else
			str +='<a id ="PoliticianDetails"><span class="btn">'+results[0].politicianCount+'</span></a><span class="help-inline f2"> Politician</span>';

	}
	str+='</div>';
	divEle.innerHTML=str;
}

function getInfluencingPeopleVotersDetails(locationValue,typeValue,publicationDateId,btnName)
{
	if(btnName == "Politician")
	{
YAHOO.widget.DataTable.NameLink = function(elLiner, oRecord, oColumn, oData) 
	{
		
		var id=oRecord.getData("candidateId");
		var name = oRecord.getData("firstName");
		elLiner.innerHTML ='<a target="_blank" id="candidateId" href="candidateElectionResultsAction.action?candidateId='+id+' ">'+name+'</a>';
		
	}	
	var votersByLocBoothColumnDefs = [
	{key:"voterId", label: "SNo",width:20,sortable: true},
	{key:"firstName", label: "Name",width:80, sortable: true},
	{key:"voterIDCardNo", label: "voter ID",sortable: true},
	{key:"gender", label: "Gender", width:50, sortable: true},
	{key:"age", label: "Age",  width:30,sortable:true},
	{key:"houseNo", label: "House No",width:60, sortable:true},
	{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
	//{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
	//{key:"relationshipType", label: "Relationship", sortable:true},
	{key:"mobileNo",label:"MobileNo",sortable:true},
	{key:"localArea", label: "Location", sortable: true},
	//{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
	//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}
];
var votersByLocBoothDataSource = new YAHOO.util.DataSource("getInfluencingPeopleVotersDetailsAction.action?locationValue="+mainreqid+"&type="+maintype+"&publicationDateId="+publicationDateId+"&buttonName="+btnName+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","localArea","candidateId"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};
	}
	if(btnName != "Politician")
	{
var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo",width:20,sortable: true},
	{key:"firstName", label: "Name",width:80, sortable: true},
	{key:"voterIDCardNo", label: "voter ID",sortable: true},
	{key:"gender", label: "Gender", width:50, sortable: true},
	{key:"age", label: "Age",  width:30,sortable:true},
	{key:"houseNo", label: "House No",width:60, sortable:true},
	{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
	//{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
	//{key:"relationshipType", label: "Relationship", sortable:true},
	{key:"mobileNo",label:"MobileNo",sortable:true},
	{key:"localArea", label: "Location", sortable: true},
	//{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
	//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];
var votersByLocBoothDataSource = new YAHOO.util.DataSource("getInfluencingPeopleVotersDetailsAction.action?locationValue="+mainreqid+"&type="+maintype+"&publicationDateId="+publicationDateId+"&buttonName="+btnName+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName","voterIDCardNo", "gender", "age", "houseNo","relativeFirstName","voterIds","mobileNo","influencePerson","localArea"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};
	}


var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=3", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 3 
			    })  // Enables pagination
};


var votersByLocBoothDataTable = new YAHOO.widget.DataTable("InfluencingPeopleDetailsDiv",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


 $("#influencyPopupDiv").dialog({ 
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

/*
	This Method is used to sean a ajax call to chech weather local election bodys are present or not on that particular constituency
*/
function checkForLocalBodyElection()
{
	var jsObj = {
			constituencyId:$('#constituencyList').val(),
			task:"checkForLocalBodyElection"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "localBodyElectionResultsAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
}
/*
	This Method is Used To Build Local Body Election Details Button 
*/
function buildLocalBodyElectionDetails(myResults)
{
	if(myResults.resultCode == 0)
	{
		$('#localBodyElectionDiv').show();
	}
}
/*
	This Method Is Used To Open a New Window For Showing Local Body Election Details 
*/
function openNewWindowForLocalBodyElection(){
	var constituencyId = $('#constituencyList').val();
	var constituencyName = $('#constituencyList option:selected').text();
	var urlStr="localBodyElectionResultsAction.action?constituencyId="+constituencyId+"&constituencyName="+constituencyName;
	var updateBrowser = window.open(urlStr,"localBodyElectionResultsAction","scrollbars=yes,height=600,width=700,left=200,top=200");	
	updateBrowser.focus();
}



function getVotersCastInfoTest(id,publicationId,type)
	{
  
		var jsObj=
			{
				type:"hamlet",	
				id:10,
				//typename:typename,
				publicationDateId:7,
					constituencyId:232,
					queryType:"sub",
				task:"getCastInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		
		var jsObj1=
			{
				type:"hamlet",	
				id:10,
				//typename:typename,
				publicationDateId:7,
				constituencyId:232,
				queryType:"sub",
				task:"getPartyInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj1,url);
}



function buildVotersByLocHamletTestDataTable(publicationId,hamletId)
{


YAHOO.widget.DataTable.ActionLink = function(elLiner, oRecord, oColumn, oData)
{
var str ='';
var id=oRecord.getData("voterIds");
var influencePerson=oRecord.getData("influencePerson");

str += '<ul class="dd_menu">';
str += ' <li><i class="icon-th-list"></i>';
str += ' <ul>';
str += ' <li>';
str += ' <a href= "javascript:{};" >Create New Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};" >Add To Existing Cadre</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Create New Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Add To Existing Influencing People</a>';
str += ' </li>';
str += ' <li>';
str += ' <a href= "javascript:{};">Add To Politician</a>';
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
		//var boothId=oRecord.getData("boothId"); 
		//elLiner.innerHTML="<input type='checkbox' class='familyMemberCheck' value='"+id+"'/><input type='hidden' class='selectedBoothId' value='"+boothId+"'/>";
		//elLiner.innerHTML=id;
		elLiner.innerHTML=str;
					
	};

var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo",width:20,sortable: true},
{key:"firstName", label: "Name",width:80, sortable: true},
{key:"voterIDCardNo", label: "voter ID",sortable: true},
{key:"gender", label: "Gender", width:50, sortable: true},
{key:"age", label: "Age",  width:30,sortable:true},
{key:"houseNo", label: "House No",width:50, sortable:true},
{key:"relativeFirstName", label: "Guardian Name", width:100,sortable:true},
{key:"Type", label: "Type", width:70,formatter:YAHOO.widget.DataTable.Type},
//{key:"relationshipType", label: "Relationship", sortable:true},
{key:"mobileNo",label:"MobileNo",sortable:true},
{key:"Actions", label: "Actions", formatter:YAHOO.widget.DataTable.ActionLink}
//{key:"select", label: "Add as influence person", formatter:YAHOO.widget.DataTable.select}

];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId="+publicationId+"&hamletId="+hamletId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","voterIDCardNo","mobileNo","voterIds","influencePerson","isInfluencePerson","isPoliticion","isCadrePerson"],

metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
},
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

function loadMyTabs()
{
  var container = document.getElementById("tabContainer");
    // set current tab
    var navitem = container.querySelector(".tabs ul li");
    //store which tab we are on
	//console.log(navitem);
    var ident = navitem.id.split("_")[1];
	//console.log(ident);
    navitem.parentNode.setAttribute("data-current",ident);
    //set current tab with class of activetabheader
    navitem.setAttribute("class","tabActiveHeader");

    //hide two tab contents we don't need
    var pages = container.querySelectorAll(".anils");
    for (var i = 1; i < pages.length; i++) {
      pages[i].style.display="none";
    }

    //this adds click event to tabs
    var tabs = container.querySelectorAll(".tabs ul li");
    for (var i = 0; i < tabs.length; i++) {
      tabs[i].onclick=displayPage;
    }

}
 function displayPage() {
  $('.tabActiveHeader').css("font-weight","normal");
  var current = this.parentNode.getAttribute("data-current");
  //remove class of activetabheader and hide old contents
  document.getElementById("tabHeader_" + current).removeAttribute("class");
  document.getElementById("tabpage_" + current).style.display="none";

  var ident = this.id.split("_")[1];
  //add class of activetabheader to new active tab and show contents
  this.setAttribute("class","tabActiveHeader");
  document.getElementById("tabpage_" + ident).style.display="block";
  this.parentNode.setAttribute("data-current",ident);
  $('.tabActiveHeader').css("font-weight","bold");   
}
function getVoterDetailsForHamlet(retrieveType){
   $("#AgeWiseNoteDiv").css("display","none"); 
	$("#AgeWiseNoteDiv").html("");
	var myType;
	if(retrieveType == "all")
	 myType="hamletLocalArea";
	else
	 myType="hamlet";
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
					type:myType,
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAgewiseVoterDetails.action?"+rparam;

	callAjaxorVoterDetails(jsObj,url);
}
function hideMyTabs()
{
  $("#tabContainer").css({ display: "none" });
 $(".tabscontent").css({ display: "none" });
}
function showMyTabs()
{
 $("#tabContainer").css({ display: "block" });
 $(".tabscontent").css({ display: "block" });
 $('.tabActiveHeader').css("font-weight","bold");

}
function ageBlockValidation(type)
{

if(type!="hamlet")
     if(maintype != type)
      return;
	  else
	    if(type != "hamletLocalArea")
		return;

}
function hideBoothDivs()
{
//$("#impFamilesBasicInfoSubChartDiv").html("");
//$("#impFamilesBasicSubDetailsTitle").html("");
//$("#descriptionDiv").html("");
$("#impFamPancBothDtls").html("");
 
}
function hideHamletDivs()
{
//$("#impFamilesBasicInfoForHamletSubChartDiv").html("");
//$("#assigAndUnassig").html("");
//$("#impFamilesBasicSubDetailsForHamletTitle").html("");
//$("#impFamilesBasicSubDetailsForHamlet").html("");
//$("#descriptionDiv").html("");
//$("#impFamPancBothDtlsAgxImgForHamlet").html("");
}
function impFamilesVariableDescription1()
{ 
 $('#descriptionDiv1').html('');
  var div = $('<div class="descriptionInnerDiv"></div>');
  div.append('<span> <b>3 -</b> Families Below 3 Voters</span>');
  div.append('<span> <b>3% -</b> Families Below 3% Voters</span>');
  div.append('<span> <b>4 to 6 -</b> Families Between 4 to 6 Voters</span>');
  div.append('<span> <b>4 to 6 % -</b> Families Between 4-6 % Voters</span>');
  div.append('<span> <b>7 to 10 -</b> Families Between 7 to 10 Voters</span>');
  div.append('<span> <b>7 to 10 % -</b> Families Between 7-10 % Voters</span>');
  div.append('<span> <b>10 - </b> Families Above 10 Voters</span>');
  div.append('<span> <b>10% -</b> Families Above 10% Voters</span>');
  $("#descriptionDiv1").append(div).css("display","block");

}

function getUpdatedCastePartyInfo(){
    $("#localCastStatsTabContent_header").html("");
    $("#localCastStatsTabContentTitle").html("");
    $("#localCastStatsTabContent_body").html("");
	$("#localCastDetailsDiv").html("");
	$("#partyBasicInfoStatsTab").html("");
	$("#partyWiselocalcastDiv").html("");
  var typename=mainname;
  var castewiseAjaxDiv =  document.getElementById('castewiseAjaxDiv');
		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
			{
				type:maintype,	
				id:mainreqid,
				typename:typename,
				publicationDateId:mainpublicationId,
				constituencyId:$("#constituencyList").val(),
				queryType:"main",
				task:"getCastInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
		
		var jsObj1=
			{
				type:maintype,	
				id:mainreqid,
				typename:typename,
				publicationDateId:mainpublicationId,
				constituencyId:$("#constituencyList").val(),
				queryType:"main",
				task:"getPartyInfo"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj1,url);
}
function getLatestCastsSubcategoryWise(){
  $("#voterCasteAjaxImg").css("display","block");
  $("#localCastStatsTabContent_subbody").html("");
  var jsObj=
		{		
				type:maintype,	
				id:mainreqid,
				typeName:mainname,
				publicationDateId:mainpublicationId,
				constituencyId:$("#constituencyList").val(),
                buildType:buildType,
                queryType:"main",
				task:"getCastInfoForsubLevels"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getvotersCastInfoByConstituency.action?"+rparam;						
		callAjax(jsObj,url);
}

function permanentlyUpdateCastePartyInfo(){
        var r=confirm("This Process Will Take Some Time. Do You Want To Proceed ?")
      if (r==true)
       {
	    $("#permanentlyUpdateDiv").attr("disabled", "disabled");
	    $("#localCastStatsTabContent_header").html("");
        $("#localCastStatsTabContentTitle").html("");
        $("#localCastStatsTabContent_body").html("");
	    $("#localCastDetailsDiv").html("");
	    $("#partyBasicInfoStatsTab").html("");
	    $("#partyWiselocalcastDiv").html("");
		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
		{
		  id				:$("#constituencyList").val(),
		  publicationDateId :$("#publicationDateList").val(),
		  task:"insertCastAndPartyVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "insertVotersCasteAndPartyDataToIntermediateTablesAction.action?"+rparam;	
		 callAjax(jsObj,url);
	  }
}

function getAssemblyLocalEleBodyId()
{
	var id = mainreqid.substring(1);
    var jsObj=
		{
		  localEleBodyId  :id,
		  task            :"getAssemblyLocalEleBodyId"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "getAssemblyLocalEleBodyIdByLocalEleBodyIdAction.action?"+rparam;	
		 callAjax(jsObj,url);
}

function refreshingchildWindowWindow()
{
	getVotersData();
}


var rebound = 20; //set it to 0 if rebound effect is not prefered
    var slip, rak;

function mybuildMenu(){

 var m = document.getElementById('sses2');
            if (!m) return;
		//	debugger;
            var ul = m.getElementsByTagName("ul")[0];
            m.style.width = ul.offsetWidth + 1 + "px";
            var items = m.getElementsByTagName("li");
            var a = m.getElementsByTagName("a");

            slip = document.createElement("li");
            slip.className = "highlight";
            ul.appendChild(slip);

            var url = document.location.href.toLowerCase();
            rak = -1;
            var nLength = -1;
            for (var i = 0; i < a.length; i++) {
                if (url.indexOf(a[i].href.toLowerCase()) != -1 && a[i].href.length > nLength) {
                    rak = i;
                    nLength = a[i].href.length;
                }
            }

            if (rak == -1 && /:\/\/(?:www\.)?[^.\/]+?\.[^.\/]+\/?$/.test) {
                for (var i = 0; i < a.length; i++) {
                    if (a[i].getAttribute("maptopuredomain") == "true") {
                        rak = i;
                        break;
                    }
                }
                if (rak == -1 && a[0].getAttribute("maptopuredomain") != "false")
                    rak = 0;
            } 
            if (rak > -1) {
                slip.style.width = items[rak].offsetWidth + "px";
                //slip.style.left = items[rak].offsetLeft + "px";
                mymove(items[rak]); //comment out this line and uncomment the line above to disable initial animation
            }
            else {
                slip.style.visibility = "hidden";
            }

            for (var i = 0; i < items.length - 1; i++) {
                items[i].onmouseover = function () {
                    if (rak == -1) slip.style.visibility = "visible";
                    if (this.offsetLeft != slip.offsetLeft) {
                        mymove(this);
                    }
                }
            }

            m.onmouseover = function () {
                if (slip.t2)
                    slip.t2 = clearTimeout(slip.t2);
            };

            m.onmouseout = function () {
                if (rak > -1 && items[rak].offsetLeft != slip.offsetLeft) {
                    slip.t2 = setTimeout(function () { mymove(items[rak]); }, 50);
                }
                if (rak == -1) slip.t2 = setTimeout(function () { slip.style.visibility = "hidden"; }, 50);
            }
}
function mymove(target)
{
 clearInterval(slip.timer);
            var direction = (slip.offsetLeft < target.offsetLeft) ? 1 : -1;
         slip.timer = setInterval(function () { mymv(target, direction); }, 15);

}
function mymv(target, direction) {
          if (direction == 1) {
                if (slip.offsetLeft - rebound < target.offsetLeft)
                    this.mychangePosition(target, 1);
                else {
                    clearInterval(slip.timer);
                    slip.timer = setInterval(function () {
                        myrecoil(target, 1);
                    }, 15);
                }
            }
            else {
                if (slip.offsetLeft + rebound > target.offsetLeft)
                    this.mychangePosition(target, -1);
                else {
                    clearInterval(slip.timer);
                    slip.timer = setInterval(function () {
                        myrecoil(target, -1);
                    }, 15);
                }
            }
            this.mychangeWidth(target);
			}
			
 function myrecoil(target, direction) {
            if (direction == -1) {
                if (slip.offsetLeft > target.offsetLeft) {
                    slip.style.left = target.offsetLeft + "px";
                    clearInterval(slip.timer);
                }
                else slip.style.left = slip.offsetLeft + 2 + "px";
            }
            else {
                if (slip.offsetLeft < target.offsetLeft) {
                    slip.style.left = target.offsetLeft + "px";
                    clearInterval(slip.timer);
                }
                else slip.style.left = slip.offsetLeft - 2 + "px";
            }
        }
		
		function mychangePosition(target, direction) {
            if (direction == 1) {
                //following +1 will fix the IE8 bug of x+1=x, we force it to x+2
                slip.style.left = slip.offsetLeft + Math.ceil(Math.abs(target.offsetLeft - slip.offsetLeft + rebound) / 10) + 1 + "px";
            }
            else {
                //following -1 will fix the Opera bug of x-1=x, we force it to x-2
                slip.style.left = slip.offsetLeft - Math.ceil(Math.abs(slip.offsetLeft - target.offsetLeft + rebound) / 10) - 1 + "px";
            }
        }
		function mychangeWidth(target) {
            if (slip.offsetWidth != target.offsetWidth) {
                var diff = slip.offsetWidth - target.offsetWidth;
                if (Math.abs(diff) < 4) slip.style.width = target.offsetWidth + "px";
                else slip.style.width = slip.offsetWidth - Math.round(diff / 3) + "px";
            }
        }
function getVotersInACasteForDidffrentLevels(mainId,id,publicationDateId,caste,type,Name,casteStateId,casteCategory)
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
var jsObj={ hamletId:id,
			id:mainId,
			publicationDateId:publicationDateId,
			//caste:"32",
			caste:casteStateId,
			casteName:caste,
			typename:typename,
			type:type,
			publicationDate:year,
			Name:Name,
            buildType:buildType,
			constituencyId:$("#constituencyList").val(),
			casteCategory:casteCategory,
			task:"getVotersInACaste"

		}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getvotersCastInfoByConstituency.action?"+rparam;				
	callAjax(jsObj,url);

}

function clearAllCheckBoxesForHamlet()
{
	impFamiliesEditForHamletArray = new Array();
	$('.impFamilMultiSel').each(function(){
		$(this).attr("checked",false);
	});

}


function buildTableForImpFamilesForBooths(impFamilesData,name,type)
{

   $('#impFamilesAllInfoPopUp1').dialog();
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
  $("#impFamilesBasicSubDetailsTitle1").html(reqtytle+" wise Voters Family analysis of "+name+" "+type+" in "+publicationYear+"");
  
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
	var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetails1", impFamilesColumnDefs,
	impFamilesDataSource, myConfigs);
	return {
	oDS: impFamilesDataSource,
	oDT: impFamilesDataTable
	};


}




function getImpFamiliesVotersToShowForBooth(){

	 var jsObj1=
			{
					
				type:"hamlet",
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:"",
				constituencyId:$('#constituencyList').val(),
				buildType:"",
				requestFor:"booth",
				task:"importantFamiliesinfo"
	
			}
	var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
			
	
         var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;
		callAjax(jsObj1,url1);
	
     $("#descriptionDiv").show();

    $("#impFamilesAllInfoPopUp").dialog({
            modal: true,
            title: "<b>Voters Details</b>",
			width: 970,
            height: 600
           
        });	   
	
	 showAjaxImgDiv('ajaxImageDiv');
	 $("#impFamPancBothDtlsAgxImg").show();
	    var jsObj2=
			{
					
				type:"hamlet",
				id:mainreqid,
				publicationDateId:mainpublicationId,
				typename:"",
                buildType:buildType,
				requestFor:"booth",
				task:"gettotalimpfamlies"
	
			}
	   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
			var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
		callAjax(jsObj2,url2);
	
	}


function  buildFamilyMembers1(result,jsObj,type){


	var publicationDateId =   jsObj.publicationDateId;

	 impFamiliesEditArray = new Array();
	var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
	hideAjaxImgDiv('ajaxImageDiv');
    var name = "";
     if(type == "panchayat"){
	    name = $("#panchayatField option:selected").text();
	 }else{
	  //type = "";
	   name = $("#pollingStationField option:selected").text();
	 }
      var str ='<div id="impFamPancBothDtlstitle">Voters Family details in '+name+' '+type+' in '+publicationYear+'</div>';
	      str+=' <div><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
          str+=' <div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamiliesForHamlet();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="clearAllCheckBoxesForHamlet()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:15px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>';
		  str+=' <table id="impfamilydatatable1" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
          str+='  <thead>';
          str+='   <tr>';
		  str+='     <th>Select</th>';
         // str+='     <th>SNo</th>';
		 if(type == "panchayat" && jsObj.buildType =="hamlet" ){
		  str+='     <th>Hamlet</th>';
		  selectType = "hamlet";
		 }
		 if(type == "hamlet" ){
		  str+='     <th>LocalArea</th>';
		  selectType = "localType";
		 }
		  
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

          if(type == "panchayat" && jsObj.buildType =="hamlet" )
		    str +='		<td><input class="impFamilMultiSel" id="impFamilSel'+sno+'" type="checkbox" onclick="populateForHamlets(this.id,'+result[i].hamletId+','+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\');"/></td>';


          if(type == "hamlet" )
		   str +='		<td><input class="impFamilMultiSel" id="impFamilSel'+sno+'" type="checkbox" onclick="populateForHamlets(this.id,'+result[i].localitityId+','+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\');"/></td>';

         // str +='		<td>'+sno+'</td>';
		  if(type == "panchayat" && jsObj.buildType =="hamlet" )
		  str +='		<td>'+result[i].hamletName+'</td>';
		    if(type == "hamlet" )
		  str +='		<td>'+result[i].localAreaName+'</td>';
		  
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
		  str+=' <div style="clear:both;"><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
	      str+=' <div style="clear:both;"><input type="button" style="margin-top:16px;margin-left:20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamiliesForHamlet();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:-17px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:-17px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv1" style="display:none;margin-top: 0px;"src="./images/icons/search.gif"></img></div>';

		  if((jsObj.buildType =="hamlet" && type == "panchayat") || (type == "hamlet"))
	       {
			  $('#impFamPancBothDtlsAgxImgForHamlet').html(str);
			  $('#impFamPancBothDtlsAgxImgForHamle').hide();
	      }
		  else
	       $("#impFamPancBothDtls").html(str);
	  
	  	$('#impfamilydatatable1').dataTable({
		"aaSorting": [[ 1, "desc" ]],
		"bDestroy": true,
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		 // "aoColumns": [null,null,null,null,null,null,null,null,null,null,null
     
	  
   // ] 
		});
  }
		function checkLocalityDataExist()
		{
			if(maintype == 'hamlet')
			{
			 var jsObj=
			 {		
				type:maintype,	
				id:mainreqid,
				task:"checkLocalityData"				
			 };
			 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			 var url = "checkLocalityDataExistAction.action?"+rparam;						
			 callAjax(jsObj,url);
			}
		}

		function hideAndShowLocalityDIvs(result,jsObj)
		{  if(maintype == jsObj.type)
            {
				if(maintype == "hamlet" && result != null && result == "false"){
					$("#ageLink").hide();
					$("#impFamiliesMoreInfoButn").hide();
					
					$("#castPartyPopupShowBtn").css("display","none");
	            $("#sse2").css("display","block");
				}else{
				$("#sse2").css("display","block");
				  $("#ageLink").show();
				  $("#impFamiliesMoreInfoButn").show();
				  $("#castPartyPopupShowBtn").css("display","block");
				  try{
				  $("#myli").css('display','block');
					  $("#myli").siblings().removeAttr('style class align');
						 mybuildMenu();
				}catch(e){
				//console.log(e);
				}
			}
		}
		}  
		function getImpFamiliesVotersForHamletBooth()
		{
			$('#impFamPancBothDtlsAgxImgForHamletByBooth').show();
			var jsObj1=
					{
							
						type:"booth",
						id:mainreqid,
						publicationDateId:mainpublicationId,
						typename:"",
						constituencyId:$('#constituencyList').val(),
						requestFor:"hamletBooth",
						task:"importantFamiliesinfoForHamletsByBooth",
			
					}
			var rparam1 ="task="+YAHOO.lang.JSON.stringify(jsObj1);
					var url1 = "getImportantFamiliesInfoAction.action?"+rparam1;						
				callAjax(jsObj1,url1);

			var jsObj2=
				{
						
					type:"pollingstation",
					id:mainreqid,
					publicationDateId:mainpublicationId,
					typename:"",
					buildType:buildType,
		            constituencyId:$('#constituencyList').val(),
					requestFor:"booth",
		            requestFor:"hamletBooth",
					task:"gettotalimpfamlies",
					taskType:"gettotalimpfamliesForHamletsByBooth"

				}
		   var rparam2 ="task="+YAHOO.lang.JSON.stringify(jsObj2);
				var url2 = "votersFamilyDetailsAction.action?"+rparam2;						
			callAjax(jsObj2,url2);


		}


		function buildTableForImpFamilesForHamletByBooth(impFamilesData,name,type)
		{
		$('#impFamilesnfoForHamletByBooth').dialog({
		            modal: true,
		            title: "<b>Voters Details</b>",
					width: 970,
		            height: 600
		           
		        });
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
		  $("#impFamilesBasicSubDetailsForHamletByBoothTitle").html("<b>Hamlet wise voters family analysis of "+name+" in "+publicationYear+"</b>");
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
		var impFamilesDataTable = new YAHOO.widget.DataTable("impFamilesBasicSubDetailsForHamletByBooth", impFamilesColumnDefs,
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


		function buildFamilyMembersForBooth(result,jsObj,type)
		{
			
		 if($("impfamilydatatable_wrapper"))
		  $("impfamilydatatable_wrapper").remove();

			var publicationDateId =   jsObj.publicationDateId;
			 impFamiliesEditArray = new Array();
			//var ajaxImageDiv =  document.getElementById('ajaxImageDiv');
			//hideAjaxImgDiv('ajaxImageDiv');
		    var name = "";
		     if(type == "panchayat")
			    name = $("#panchayatField option:selected").text();
			 else
			   name = $("#pollingStationField option:selected").text();
			
		      var str ='<div id="impFamPancBothDtlstitle">Voters Family details in '+name+' '+type+' in '+publicationYear+'</div>';
			      str+=' <div><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
		          str+=' <div><input type="button" style="margin-bottom: 14px;margin-left: 20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:15px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:15px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv" style="display:none;margin-left: 37px;margin-bottom: 12px;"src="./images/icons/search.gif"></div>';
				  str+=' <table id="impfamilydatatableForBooth" cellpadding="0" cellspacing="0" border="0" width="100%" style="border:1px solid black">';
		          str+='  <thead>';
		          str+='   <tr>';
				  str+='     <th>Select</th>';
				  str+='     <th>Hamlet</th>';		  
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
			      str +='<tr>';
				  str +='<td><input class="impFamilMulSel" id="impFamilSel'+sno+'" type="checkbox" onclick="populate(this.id,'+result[i].boothId+','+publicationDateId+',\''+result[i].houseNo+'\');"/></td>';
				  str+='<td>'+result[i].hamletName+'</td>';
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
				  str+=' <div style="clear:both;"><b style="font-size:14px;">Hint: Please select atmost 30 families to edit</b></div>';
			      str+=' <div style="clear:both;"><input type="button" style="margin-top:16px;margin-left:20px;" class="btn" value="Edit all selected families" onclick="editSelectedFamilies();"/><input class="btn" type="button" value="UnSelectAll" style="width:100px; margin-bottom:-17px;margin-left: 10px;"onClick="clearAllCheckBoxes()"></input><input type="button" class="btn" value="Refresh" style="width:100px; margin-bottom:-17px;margin-left: 10px;" onClick="getvotersFamileyInfo(\'impFamilies\',\'\')"></input><img alt="Processing Image" id="imgDiv1" style="display:none;margin-top: 0px;"src="./images/icons/search.gif"></img></div>';
				 
					  $('#importantFamiliesForBooth').html(str);
					  //$('#impFamPancBothDtlsAgxImgForHamle').hide();
			      
			  
			  	$('#impfamilydatatableForBooth').dataTable({
				"aaSorting": [[ 1, "desc" ]],
				"bDestroy": true,
				"iDisplayLength": 15,
				"aLengthMenu": [[15, 30, 90, -1], [15, 30, 90, "All"]]
				
				});
		}

		function buildImpFamilesChartForHamletsByBooth(chartInfo)
		{
			var totalCount = 0;
			var below3 = 0;
			var between4And6 = 0;
			var between7And10 = 0;
			var above10 = 0;
		   for(var i in chartInfo.subList)
			{
			   below3 = below3+ chartInfo.subList[i].below3;
			   between4And6 = between4And6 + chartInfo.subList[i].betwn4to6;
			   between7And10 = between7And10 + chartInfo.subList[i].betwn4to6;
			   above10 = above10 + chartInfo.subList[i].above10;
		   }
		   totalCount = below3 + between4And6 + between7And10 + above10;

			var below3Perc = Math.round((below3/totalCount)*100);
			var between4And6Perc = Math.round((between4And6/totalCount)*100);
			var between7And10Perc = Math.round((between7And10/totalCount)*100);
			var above10Perc = Math.ceil((above10/totalCount)*100);


			hideAjaxImgDiv('ImpFamwiseAjaxDiv');
			var data = google.visualization.arrayToDataTable([
					  ['Task', 'Percentage'],
					  ['Families Below 3 Voters',  below3Perc],
					  ['Families Between 4-6 Voters', between4And6Perc],
					  ['Families Between 7-10 Voters',  between7And10Perc],
					  ['Families Above 10 Voters', above10Perc]
					]);

			// Set chart options
			var title = " Family wise Voters details chart of "+chartInfo.name+" "+chartInfo.type+" in "+publicationYear+"";
			var options = {'title':title,
			'width':800,
			'height':280};
			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.PieChart(document.getElementById('impFamilesBasicInfoSubChartDivForHamletsByBooths'));
			chart.draw(data, options);
		}