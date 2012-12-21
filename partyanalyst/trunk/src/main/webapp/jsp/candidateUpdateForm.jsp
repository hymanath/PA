<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
   <head>
     <title>User Details Updation</title>
  </head>
  
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js">
</script>
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js">
</script>
</head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<style>
 h1 {
    font: 1.2em Arial, Helvetica, sans-serif;
}
 
input.txt {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 200px;
	border-radius:2px;
	height:17px;
}

select {
    color: #00008B;
    /*background-color: #E3F2F7;*/
    border: 1px solid #c3c3c3;
    width: 212px;
	height:27px;
	border-radius:2px;
}
 
input.btn {
    color: #00008B;
    background-color: #ADD8E6;
    border: 1px outset #00008B;
}
 
form {
    width: 40%;
	margin-left:250px;
}
 
form div {
    clear: left;
    margin: 0;
    padding: 0;
    padding-top: 0.6em;
}
 
form div label {
    float: left;
    width: 40%;
    font: bold 0.9em Arial, Helvetica, sans-serif;
}

.spanClass {
    float: left;
    width: 36%;
    font-weight: bold;
	font-family:verdana;
	font-size:13px;
}
 fieldset {
    border: 1px dotted #61B5CF;
    margin-top: 1.4em;
    padding: 0.6em;
}
 
.legendClass {
    font: bold 0.8em Arial, Helvetica, sans-serif;
    color: #00008B;
    background-color: #FFFFFF;
	border-style:none ;
	margin-bottom:0px;
	width:42%;
	font-weight:bold;
	font-size:16px;
	text-align:center;
}
.error{
	color:red;
	font-size:12px;
}
 
 </style>
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
function handleReaderLoadEnd(evt) {
  var img = document.getElementById("Imgpreview");
  img.src = evt.target.result;
}
function previewImg()
{
	var photoElmt = document.getElementById("photoUploadElmt");
	var photoStatusElmt = document.getElementById("uploadPic_window_status");
	var fileLimit = 1048576; //1024*1024 = 1048576 bytes (2MB photo limit)
	
	var file = photoElmt.files[0];
	
	var fileType = file.type;
	var fileImgType = fileType.substring(fileType.indexOf('/')+1,fileType.length);
	

	if(fileImgType == "jpeg" || fileImgType == "png" || fileImgType == "gif")
	{
	
		var fileSize = file.fileSize/fileLimit;
		if(fileSize > 2)
		{
			photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image size should be < 2MB.</span>';
		}
		else
		{
			var reader = new FileReader();
           //  init the reader event handlers
			//reader.onloadend = handleReaderLoadEnd;
			 
			// begin the read operation
			reader.readAsDataURL(file);
			
			photoStatusElmt.innerHTML = '';
			var previewElmt = document.getElementById("Imgpreview");
			//previewElmt.src = file.getAsDataURL();
			uploadPicStatus = true;
		}
	}
	else
	{
		photoStatusElmt.innerHTML = '<span class="errorStatusMsg">The Image is not of the type specified.</span>';
	}
}

var refreshTime=5;
function getUploadpic()
{
//var photoStatusElmt = document.getElementById("uploadPic_window_status");
	var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				showUploadStatus();				
			}
		};

	
	YAHOO.util.Connect.setForm('uploadPicForm',true);
	YAHOO.util.Connect.asyncRequest('POST', 'uploadPicAction.action?param=true', uploadHandler);
}

function showUploadStatus()
{
	window.location.reload();
	
}

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
 
    <body>

	<form name="uploadForm1" action="uploadPicAction.action" enctype="multipart/form-data"  method="post" id="uploadPicForm">

	<h4 style="padding:14px;">${candidateName} Profile Information</h4>
	<input type="hidden" name="candidateName"  id="candidateName" value="${candidateName}">

	<div id="uploadPic_window_status"></div>

    <div>
	<img src="images/candidates/${candidateName}.jpg" height="100px" style="float:right;width:100px;"/>

	<input style="height:28px;" type="file" size="45" id="photoUploadElmt" name="upload" onchange="previewImg()" />

	 <input type="button" class="btn" value="Upload" id="uploadPicButton"  onclick="getUploadpic()"/>
	 </div>


	</form>

       <form name="uploadForm" action="candidateUpdateAction.action" enctype="multipart/form-data"  method="post" id="uploadNewsForm">

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

		<input type="hidden" value="${candidateName}"  
				name="candidateName"  id="candidateName" class="required" style="width: 220px;"/>

		<input type="hidden" name="candidateId" value="${candidateId}">

            <fieldset>
                <legend class="legendClass">Personal Information</legend>
 
            <div>
                <span class="spanClass">Candidate Name:</span>
                 <b>${candidateName}</b>
            </div>
			<br>
 
            <div> 
                 <span class="spanClass">Email Address:</span>
                <input type="text" class="txt"  value="${CandidateDetailsVO.emailId}"  name="emailId"  id="mailId"/>
				<span id="emailErrMsg" class="error" style="float:right;margin-right:18px;"></span>
            </div>
 
            <div>
                 <span class="spanClass">Website Address :</span>
                <input type="text"  class="txt" value="${CandidateDetailsVO.websiteAddress}" name="websiteAddress" id="website"/>
            </div>
 
            <div>
                <span class="spanClass">Twitter Id :</span>
                <input type="text"  class="txt" value="${CandidateDetailsVO.twitterUrl}" name="twitterUrl" id="twitterId"/>
            </div>

			<div>
                 <span class="spanClass">Facebook Id :</span>
                <input type="text" value="${CandidateDetailsVO.facebookUrl}" name="facebookUrl" id="facebookid" class="txt" />
            </div>

			 </fieldset>

			 <fieldset>
			  <legend class="legendClass">Caste Information</legend>

			<div>
			 <span class="spanClass">Caste Category:</span>
                <select theme="simple" class="txt" name="casteCategoryId" id="categoryNames"  onchange="getCasteCategoryGroupNames(this.options[this.selectedIndex].value)">
				<option>- select -</option>
				</select>
            </div>

			<div>
			 <span class="spanClass">Caste Group:</span>
                <s:select theme="simple"  label="Select  Caste" 
					name="candidateDetailsVO.casteGroupId" 
				onchange="getCasteNames(this.options[this.selectedIndex].value)" 
				id="casteCategoryGroupNames" list="candidateDetailsVO.casteGroupList" listKey="id" 
				listValue="name" value="casteGroupId"/>          
			</div>

			<div>
			 <span class="spanClass">Caste Name:</span>
               <s:select theme="simple"  label="Select  Caste" 
					name="candidateDetailsVO.casteId" id="casteNames" 
					list="candidateDetailsVO.casteGroupNameList" listKey="id" listValue="name" onchange="disableNewCasteNames()"/>
            </div>

			<div>
                 <span class="spanClass">if, Others :</span>
                <input type="text" id="newCasteName" value="${CandidateDetailsVO.newCaste}" 
				 name="newCaste" class="txt" />
            </div>
            </fieldset>
 
            <fieldset>
                <legend class="legendClass">Address Details</legend>
 
                <div>
                    <span class="spanClass">Address Type :</span>
                 <s:select theme="simple" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].addressTypeId" 
				id="addressType" list="candidateDetailsVO.addressList[0].addressTypeList" 
				listKey="id" listValue="name"/>
                </div>

                <div>
                    <span class="spanClass">Address1 :</span>
                    <input type="text"  class="txt" id="address1" 
				name="candidateDetailsVO.addressList[0].address1" 
				value="${CandidateDetailsVO.addressList[0].address1}"/>
                </div>

				<div>
                   <span class="spanClass">Address2 :</span>
                    <input type="text" id="address2" 
			 name="candidateDetailsVO.addressList[0].address2" 
			 value="${CandidateDetailsVO.addressList[0].address2}" class="txt" />
                </div>
 
                <div>
					<span class="spanClass">Town / City:</span>
					<input type="text"  class="txt" id="city" name="candidateDetailsVO.addressList[0].city"
				value="${CandidateDetailsVO.addressList[0].city}"/>
                </div>

				<div>
					<span class="spanClass">State :</span>
					<s:select theme="simple"  label="Select  Caste" 
				name="candidateDetailsVO.addressList[0].stateId" id="state" list="statesList" listKey="id"
				listValue="name" 
				onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value)"/>
                </div>

				<div>
					<span class="spanClass">District</span>
					<s:select theme="simple"  
				label="Select  Caste" name="candidateDetailsVO.addressList[0].districtId" 
				id="district" list="candidateDetailsVO.addressList[0].districtList" listKey="id" 
				listValue="name" onchange="getMandalNames(this.options[this.selectedIndex].value)"/>
                </div>

				<div>
					<span class="spanClass">Mandal:</span>
					<s:select theme="simple" 
				label="Select  Caste" name="candidateDetailsVO.addressList[0].mandalId" id="mandal" 
				list="candidateDetailsVO.addressList[0].tehsilList" listKey="id" 
				 listValue="name"/>
                </div>
 
                <div>
					<span class="spanClass">Zip / Post Code:</span>
					<input type="text"  id="pincode"  name="candidateDetailsVO.addressList[0].pincode" 
				value="${CandidateDetailsVO.addressList[0].pincode}" maxlength="6" class="txt"/> 
                </div>
            </fieldset>

			 <fieldset>
                <legend class="legendClass">Contact Details</legend>
 
                <div>
                   <span class="spanClass">Phone Type :</span>
                     <s:select theme="simple"  
				label="Select  Caste" name="candidateDetailsVO.addressList[0].phoneList[0].phoneType"
				id="phoneType" list="candidateDetailsVO.addressList[0].phoneList[0].phoneTypeList" 
				listKey="id" listValue="name"/>
                </div>
                <div>
                   <span class="spanClass">Phone Category :</span>
                   <s:select theme="simple"  
				label="Select  Caste" 
				name="candidateDetailsVO.addressList[0].phoneList[0].phoneCategory" id="phoneCategory" 
				list="candidateDetailsVO.addressList[0].phoneList[0].phoneCategoryList"
				listKey="id" listValue="name" />
                </div>
 
                <div>
					<span class="spanClass">Phone Number :</span>
					<input type="text" class="txt" id="phoneNumber" name="candidateDetailsVO.addressList[0].phoneList[0].phoneNumber" maxLength=10 
				value="${CandidateDetailsVO.addressList[0].phoneList[0].phoneNumber}" size="10"/>
                </div>
 
               
            </fieldset>
            <div style="float:right;">
             <input class="btn btn-success" type="submit" onclick="return ValidateForm();" value="Update">
			<!-- <input class="btn btn-success" type="reset"  value="clear">-->
			 </div>
			<br>
        </form>



<script type="text/javascript">
function ValidateForm(){
//alert("sri");
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
	//alert("invalid e-mailID ");

	$('#emailErrMsg').html('Enter a valid email id');
return false;
 }
disableNewCasteNames();
getCasteCategories();
getCasteCategories('${CandidateDetailsVO.casteCategoryId}');
getStatesForCountry('${CandidateDetailsVO.stateId}');


</script>
    </body>
</html>
 
