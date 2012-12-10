
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	

<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js">
</script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js">
</script>
</head>
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script>

    $(function() {

	
	$( "#newCasteName" ).autocomplete({
            source: function( request, response ) {
			  
			    var jsobjn={
							letters:request.term,
							stateId:${stateId},
							task:"getCasteNamesByUsingAutoPopuate"
							};
                $.ajax({
                    url: "getcandidateFormAction.action",
                    dataType: "json",
                    data: {
					    task:YAHOO.lang.JSON.stringify(jsobjn)										
                    },
                    success: function( data ) {

                        response( $.map( data, function( item ) {
                            return {
                                label: item.name,
                                value: item.name
                            }
                        }));
                    }
                });
            },
            minLength: 2,
           open: function() {
                $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
            },
            close: function() {
                $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
            }
        });
    });

    </script>

<script type="text/javascript">

function getDistrictsComboBoxForAState(value,elmtId)
{
	showBusyImgWithId(elmtId);
	clearOptionsListForSelectElmtId(elmtId);
	clearOptionsListForSelectElmtId("districtSelectBox");
	createSelectOptionsForSelectConstituencyElmtId("districtSelectBox");
	clearOptionsListForSelectElmtId("constituencySelectBox");
	createSelectOptionsForSelectConstituencyElmtId("constituencySelectBox");
	var jsObj=
		{				
				stateId:value,
				elmtId:elmtId,
				task:"findDistrictsForAState",
				taskType:"getRegions"				
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getDistrictsForAStateAjaxAction.action?"+rparam;						
	callCandidateUpdatePageAjax(jsObj,url);
}

function createOptionsForSelectElmtId(elmtId,optionsList)
{	
	var elmt = document.getElementById("district");
	
	if( !elmt || optionsList == null)
		return;
		clearOptionsListForSelectElmtId("district");
	for(var i in optionsList)
	{
		
		var option = document.createElement('option');
		option.value=optionsList[i].id;
		option.text=optionsList[i].name;
		try
		{
			elmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			elmt.add(option); // IE only
		}
	}

}



function getCasteCategories(val){

//alert("nowwww");
var jsObj=
		{
			task:"getCasteCategories",
			val:val
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getCasteCategory.action?"+rparam;						
		callCandidateUpdatePageAjax(jsObj,url);
		


}

function callCandidateUpdatePageAjax(jsObj,url){
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "getCasteCategories")
							{
								getCasteCategory(myResults,jsObj.val);
							}
							if(jsObj.task=="getCasteCategoryGroupNames"){
								getCasteCategoriesGroupNames(myResults);
							}/*if(jsObj.task == "getCasteNames")
							{
								getCasteName(myResults);
							}*/if(jsObj.task =='getStates')
							{
								getStatesInfo(myResults);
							}
							if(jsObj.task == "findDistrictsForAState")
							{
								
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								
							}
						}catch(e)						{   
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

function getCasteCategory(myResults,val){
	
	var elmt = document.getElementById('categoryNames');
	elmt.options.length=0;
	var option = document.createElement('option');

	clearOptionsListForSelectElmtId('categoryNames');
	option.value="0";
	option.text="-- select --";
	try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in myResults)
	{
		
		var option=document.createElement('option');
		option.value=myResults[i].casteCategoryId;
		option.text=myResults[i].casteCategoryName;
		
	
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	}

  if(val != null && val != 'null' && $.trim(val).length > 0 && elmt.options.length > 0){
       elmt.value = val;
  }
}
function getCasteCategoryGroupNames(categoryNames){

	
	var categoryEle= document.getElementById("categoryNames").value;
		
	if(categoryEle == 1 ||categoryEle == 2)
	{
	document.getElementById('casteCategoryGroupNames').disabled = false;
	document.getElementById('casteCategoryGroupNames').style.display="block";
	
	getCasteCategoryGroupName();
		
	}else if(categoryEle == 3 || categoryEle == 4)
	{

		var value=document.getElementById('casteCategoryGroupNames');
		value.disabled = true;
		//var elmt1 = document.getElementById('casteCategoryGroupNames');
		//alert(value);
			
		value.options.length=0;
		//alert(value);
		var option = document.createElement('option');
	
		//alert(option);
		clearOptionsListForSelectElmtId('casteCategoryGroupNames');
				//alert(option);
		//clearOptionsListForSelectElmtId(value);
		
	}
}
function clearOptionsListForSelectElmtId(elmtId)
{


	var elmt = document.getElementById(elmtId);

	if(!elmt)
		return;
	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}
function getCasteCategoryGroupName(){

//	debugger;
	var categoryEle=document.getElementById('categoryNames').value;
	var jsObj=
	{						
		categoryId:categoryEle,
		task:"getCasteCategoryGroupNames"
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCasteCategoryGroupNames.action?"+rparam;						
	callCandidateUpdatePageAjax(jsObj,url);
}

function getCasteCategoriesGroupNames(myResults){
	//debugger;
	var elmt1 = document.getElementById('casteCategoryGroupNames');
	elmt1.options.length=0;

	var option = document.createElement('option');

	clearOptionsListForSelectElmtId('casteCategoryGroupNames');
	option.value="0";
	option.text="-- select --";
	
	
	try
	{
	elmt1.add(option,null);	

	}
	catch (ex)
	{
		elmt1.add(option);
	}
	for(var i in myResults)
	{
		
		var option=document.createElement('option');
		option.value=myResults[i].casteCategoryGroupId;
		option.text=myResults[i].casteCategoryGroupName;
		
	
		try
	{
	elmt1.add(option,null);	

	}
	catch (ex)
	{
		elmt1.add(option);
	}
	}
}

/*function getCasteNames(groupName){
var id = document.getElementById('casteCategoryGroupNames').value;
	
	var jsObj=
	{
			casteGroupId:id,
			task:"getCasteNames"					
	};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCasteNames.action?"+rparam;						
	callCandidateUpdatePageAjax(jsObj,url);
}

function getCasteName(myResults){

	
	var elmt = document.getElementById('casteNames');
	elmt.options.length=0;
	var option = document.createElement('option');

	clearOptionsListForSelectElmtId('casteNames');
	option.value="0";
	option.text="select";
	try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	for(var i in myResults)
	{
		
		var option=document.createElement('option');
		option.value=myResults[i].casteId
		option.text=myResults[i].casteNames;
		
	
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
}*/
	/*	function checkElectionType(electionTypeId)
{

var electionType = document.getElementById('country').value;

if(electionType == 1)
	{
getStates();
document.getElementById('state').style.display="none";
getElectionYearsInHomePage('Parliament');
	}

if(electionType == 2)
	{
	document.getElementById('state').style.display="block";

getStatesForNewLayOut();

	}
}*/
function getStatesForCountry(val)
{

	

	var jsObj=
		{						
				val:val,
				electionType:1,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}
function getStatesInfo(myResults,val){


	if(myResults == null || myResults.length == 0)
		return;
	
	var electionTypeElmt = document.getElementById("state");
	electionTypeElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select State";
	try
	{
		electionTypeElmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		electionTypeElmt.add(option); // IE only
	}
	
	for(var i in myResults)
	{
		var option = document.createElement('option');
		option.value = myResults[i].id;
		option.text = myResults[i].name;
		try
		{
			electionTypeElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionTypeElmt.add(option); // IE only
		}
	}	

	hideBusyImgWithId("state");
 if(val != null && val != 'null' && $.trim(val).length > 0 && electionTypeElmt.options.length > 0){
       electionTypeElmt.value = val;
  }

}
function callHomePageAjax(jsObj,url)
{	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							/*if(jsObj.task == 'constituenciesInDistrict')
							{
								getConstituenciesInDistrict(myResults);				
							}*/
							if(jsObj.task =='getMandalNames')
							{
								//alert(3);
								getMandalNameInSelecteDistricts(myResults);				
							}
							if(jsObj.task =='getStates')
							{
								getStatesInfo(myResults,jsObj.val);
							}
							if(jsObj.task =='getCasteNamesByUsingAutoPopuate')
							{  

								casteNames = myResults;
						
								
								//getCasteNamesByUsingAutoPopulateResult(myResults);
							}
						}catch(e)
						{   
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
function getMandalNames(districtId){

var districtId = document.getElementById('district').value;

	var jsObj=
		{						
				
				districtId:districtId,
				task:"getMandalNames"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getMandalsForDistricts.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}
function getMandalNameInSelecteDistricts(myResults){
	//alert('4');

if(myResults == null || myResults.length == 0)
		return;
	
	var electionTypeElmt = document.getElementById("mandal");
	electionTypeElmt.options.length=0;
clearOptionsListForSelectElmtId("mandal");

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select mandal";
	try
	{
		electionTypeElmt.add(option,null); // standards compliant
	}
	catch(ex)
	{
		electionTypeElmt.add(option); // IE only
	}
	
	for(var i in myResults)
	{
		var option = document.createElement('option');
		option.value = myResults[i].candId;
		option.text = myResults[i].cname;
		try
		{
			electionTypeElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionTypeElmt.add(option); // IE only
		}
	}	

}

function getCasteNamesByUsingAutoPopulate(){
var letters=document.getElementById('newCasteName').value;
//alert(letters);

var stateId=${stateId};
var task="getCasteNamesByUsingAutoPopuate";

var jsobj={
	letters:letters,
	stateId:stateId,
	task:task
	}
//alert(stateId);
	var rparam="task="+YAHOO.lang.JSON.stringify(jsobj);
	var url="getcandidateFormAction.action?"+rparam;
//alert(letters);
	callHomePageAjax(jsobj,url);
}

function getCasteNamesByUsingAutoPopulateResult(myResult){
	if(myResults == null || myResults.length == 0)
		return;

	var electionTypeElmt = document.getElementById("newCasteName");
	electionTypeElmt.options.length=0;
}

function disableNewCasteNames(){


	var value=document.getElementById('newCasteName');
	var value1=document.getElementById('casteNames').value;

	if(value1==0){
		
				value.disabled = false;
		}else{
			document.getElementById('newCasteName').value = '';
			value.disabled = true;

	 }
}

</script>


<style type="text/css">


#mailId,#website,#twitterId,#facebookid,
#candidateName,#categoryNames,#newCasteName,
#address1,#address2,#pincode,#phoneNumber,#city{
width :225px;
}


</style>
<body>
<form name="uploadForm" action="candidateUpdateAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">'

<h2 class="reg" align="center"><b> ${candidateName} Profile Information  </b></h2>

<input type="hidden" name="CandidateDetailsVO.addressList[0].candidateAddressId" 
value="${CandidateDetailsVO.addressList[0].candidateAddressId}"/>
<input type="hidden" name="candidateDetailsVO.addressList[0].phoneList[0].candidatePhoneId" value="${candidateDetailsVO.addressList[0].phoneList[0].candidatePhoneId}"/>
<input type="hidden" name="CandidateDetailsVO.addressList[0].addressContactId" value="${CandidateDetailsVO.addressList[0].addressContactId}"/>
<input type="hidden" name="CandidateDetailsVO.casteStateId" value="${CandidateDetailsVO.casteStateId}"/>


<input type="hidden" name="CandidateDetailsVO.addressList[0].addressId" value="${CandidateDetailsVO.addressList[0].addressId}"/>
<input type="hidden" name="candidateDetailsVO.addressList[0].phoneList[0].phoneNumberId" value="${candidateDetailsVO.addressList[0].phoneList[0].phoneNumberId}"/>
<input type="hidden" name="CandidateDetailsVO.candidatecasteId" value="${CandidateDetailsVO.candidatecasteId}"/>
<input type="hidden" name="CandidateDetailsVO.candidateSocialFacebookId" value="${CandidateDetailsVO.candidateSocialFacebookId}"/>
<input type="hidden" name="CandidateDetailsVO.candidateSocialTwitterId" value="${CandidateDetailsVO.candidateSocialTwitterId}"/>
<input type="hidden" name="CandidateDetailsVO.websiteId" value="${CandidateDetailsVO.websiteId}"/>


	<div id="div1" align="center"  style="border:2px solid blue">
<br>
				<span style="margin-left: 210px;">
				<img src="images/candidates/${candidateName}.jpg" height="100px" style="margin-left: 20px;cursor: default;"/>
			</span>
</br>
</br>
<span style="margin-left: 320px;"> 
				<input type="file" name="userImage"id="newsFileId" size="25" />
			</span>
<br>
	<b style="margin-left: -347px;">Candidate Name : &nbsp<input type="hidden" value="${candidateName}"  
				name="candidateName"  id="candidateName" class="required" style="width: 220px;"/></b>
		  <span style="margin-left: -9px;"> 
									<b style="margin-left: 15px;">	 ${candidateName}</b>
			</span>

			<input type="hidden" name="candidateId" value="${candidateId}">
</br>
</br>
		<div id="webDetails" style="width: 800px; height: 185px; margin-top: -19px; margin-left: -6px;">
		<br>
			<span  style="margin-left: -185px;"> 
				email-id : &nbsp<input type="text" value="${CandidateDetailsVO.emailId}" 
 name="emailId"  id="mailId" class="required"/>
			</span>
<br>
<br>
			<span  style="margin-left: -240px;">
				Website Address : &nbsp<input type="text" value="${CandidateDetailsVO.websiteAddress}" name="websiteAddress" id="website"/>
			</span>
</br>
</br>
			<span  style="margin-left: -196px;">	
				Twitter Id : &nbsp;<input type="text" value="${CandidateDetailsVO.twitterUrl}" name="twitterUrl" id="twitterId"/>
			</span>
</br>
</br>
			<span  style="margin-left: -208px;">
				Facebook Id : &nbsp;<input type="text" value="${CandidateDetailsVO.facebookUrl}" name="facebookUrl" id="facebookid"/>
			</span>
</br>
		</div>		
		<div id="casteDetails" style="width: 1000px; height: 90px;">
<br>
<table  style="margin-left: 54px;">
<tr>
<td>
			<span style="margin-left: 93px;"> 
				Caste : &nbsp;<select theme="simple" cssClass="selectBoxWidth" label="Select  Caste"
				name="casteCategoryId" id="categoryNames" 
				onchange="getCasteCategoryGroupNames(this.options[this.selectedIndex].value)" style="width: 235px; height: 25px;">
				<option>- select -</option>
				</select>
			</span> 
</td>
<td >
			<span  style="margin-left: 60px;"> 
				Group : &nbsp;
				</span>
</td> 
<td>		<span><s:select theme="simple" cssClass="selectBoxWidth" label="Select  Caste" 
					name="candidateDetailsVO.casteGroupId" 
				onchange="getCasteNames(this.options[this.selectedIndex].value)" 
				id="casteCategoryGroupNames" list="candidateDetailsVO.casteGroupList" listKey="id" 
				listValue="name" value="casteGroupId"/>
			</span>
</td>
</tr>
</table>
<br>
			<span style="margin-left: 149px;"> 
				Caste Name : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" label="Select  Caste" 
					name="candidateDetailsVO.casteId" id="casteNames" style="width: 235px; height: 25px;"
					list="candidateDetailsVO.casteGroupNameList" listKey="id" listValue="name" onchange="disableNewCasteNames()"/>
			</span> 
			<span style="margin-left: 42px;"> 
				if, Others : &nbsp;<input type="text" id="newCasteName" value="${CandidateDetailsVO.newCaste}" 
				 name="newCaste" />
			</span> 
<br>
		</div>	

		<div id="AddressDiv" style="width: 1000px; margin-left: -240px; margin-top: 15px;">
<br>
		<b style="margin-left: -236px;">Address Details : </b>
<br>
<br>
			<span style="margin-left: 21px;">
				Address Type : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].addressTypeId" 
				id="addressType" list="candidateDetailsVO.addressList[0].addressTypeList" 
				listKey="id" listValue="name" style="width: 235px; height: 25px;" />

			</span>
<br>
</br>
			<span style="margin-left: 42px;">
				Address1 : &nbsp;<input type="text" id="address1" 
				name="candidateDetailsVO.addressList[0].address1" 
				value="${CandidateDetailsVO.addressList[0].address1}"/>
			</span>
<br>
<br>
			<span style="margin-left: 42px;">
				Address2 : &nbsp;<input type="text" id="address2" 
			 name="candidateDetailsVO.addressList[0].address2" 
			 value="${CandidateDetailsVO.addressList[0].address2}"/>
			
			</span>
</br>
</br>
			<span style="margin-left: 80px;">
				City : &nbsp;<input type="text" id="city" name="candidateDetailsVO.addressList[0].city"
				value="${CandidateDetailsVO.addressList[0].city}"/>
			</span>
<br>
<br>

			<span style="margin-left: 71px;">
				State : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" label="Select  Caste" 
				name="candidateDetailsVO.addressList[0].stateId" id="state" list="statesList" listKey="id"
				listValue="name" style="width: 235px; height: 25px;"
				onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value)"/>

			</span>
<br>
<br>
			<span style="margin-left: 58px;">
				District : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].districtId" 
				id="district" list="candidateDetailsVO.addressList[0].districtList" listKey="id" 
				style="width: 235px; height: 25px;"
				listValue="name" onchange="getMandalNames(this.options[this.selectedIndex].value)"/>

			</span>
<br>
<br>
			<span style="margin-left: 60px;">
				Mandal : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].mandalId" id="mandal" 
				list="candidateDetailsVO.addressList[0].tehsilList" listKey="id" 
				style="width: 235px; height: 25px;" listValue="name"/>
			</span>
</br>
</br>
			<span style="margin-left: 47px;">
				Pin Code : &nbsp;<input type="text" id="pincode" style="height: 16px;" 
				name="candidateDetailsVO.addressList[0].pincode" 
				value="${CandidateDetailsVO.addressList[0].pincode}" maxlength="6"/>
			</span>
</br>
</br>
			<b style="margin-left: -236px;">Contact Details : </b>
</br>
</br>
			<span style="margin-left: 33px;">
				Phone Type : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].phoneList[0].phoneType"
				id="phoneType" list="candidateDetailsVO.addressList[0].phoneList[0].phoneTypeList" 
				listKey="id" listValue="name" style="width: 235px; height: 25px;"/>

			</span>
</br>
</br>
			<span style="margin-left: 5px;">
				Phone Category : &nbsp;<s:select theme="simple" cssClass="selectBoxWidth" 
				label="Select  Caste" 
				name="candidateDetailsVO.addressList[0].phoneList[0].phoneCategory" id="phoneCategory" 
				list="candidateDetailsVO.addressList[0].phoneList[0].phoneCategoryList"
				listKey="id" listValue="name" style="width: 235px; height: 25px;" />

			</span>
</br>
</br>
			<span style="margin-left: 12px;">
				Phone Number : &nbsp;<input type="text" id="phoneNumber" name="candidateDetailsVO.addressList[0].phoneList[0].phoneNumber" maxLength=10 
				value="${CandidateDetailsVO.addressList[0].phoneList[0].phoneNumber}" size="10" />
			</span>
			
		</div>

<br>
			<span style="margin-left: -131px;">
				<input type="reset" style="margin-left: 35px; height: 30px; width: 60px;" value="clear"/>
			</span>
			<span>
				<input type="submit" style="margin-left: 35px; height: 30px; width: 60px;" value="submit" 
				onclick="return ValidateForm();"/>
			</span>
<br><br>
	</div>
	
</form>
<script type="text/javascript">
function ValidateForm(){

var flag = false;
	var emailID=document.getElementById('mailId').value;
var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[c,o,m,i,n,c,o]{2,8}$/i;
	//alert(emailID.match(emailRegEx));
	if (emailID == ''){
			return true
	}
	else if (emailID.match(emailRegEx)){
			return true;
	}
	alert("invalid e-mailID ");
return false;
 }
disableNewCasteNames();
getCasteCategories();
getCasteCategories('${CandidateDetailsVO.casteCategoryId}');
getStatesForCountry('${CandidateDetailsVO.stateId}');


</script>

</script>
</body>