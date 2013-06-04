<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Complete Profile</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>



<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/gallery-2010.03.02-18/build/gallery-accordion/assets/skins/sam/gallery-accordion.css">
<!-- YUI Dependency files (End) -->

<style type="text/css">
	
	body
	{
		background-color:#FFFFFF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}
	
	#cadreInfo_head
	{
		color:#8E320A;
		font-size:17px;
		font-weight:bold;
		text-align:center;
		text-decoration:underline;
		padding:5px;
		margin-top:10px;
	}
	
	.cadreProfileInfoTable th
	{
		color:#652D2D;
		padding:4px;
		text-align:left;
	}

	.cadreProfileInfoTable td
	{
		color:#31383C;
		padding:4px;
		text-align:left;
	}
	
	.tableHeadingDiv
	{
		color:#1C3752;
		font-size:15px;
		font-weight:bold;
		margin-left:0;
		padding:6px;
		text-decoration:underline;
	}
	
	#cadreInfo_footer
	{
		margin-right:10px;
		padding:10px;
		text-align:right;
	}
	fieldset {
		border:4px solid #CFD6DF;
		margin-bottom:10px;
		padding:10px;		
	}
	legend {
		background-color:#567AAF;
		color:#FFFFFF;
		font-size:12px;
		padding:5px;
		font-weight:bold;
	}

</style>

<script type="text/javascript">
	
var cadreId = '${cadreId}';
var userParty = '${sessionScope.USER.partyShortName}';
var userType = '${sessionScope.USER.userType}';

function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "getCadreInfoByCadreId")
								buildCadreInfo(jsObj,myResults);

						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildCadreInfo(jsObj,results)
{
	var elmt = document.getElementById("cadreInfoMain");
	if(!elmt)
		return;

	var str = '';
	str += '<div id="cadreInfo_head">';
	str += results.firstName+" "+results.lastName+" "+"Complete Profile";
	str += '</div>';
	str += '<div id="cadreInfo_body">';
	str += '	<fieldset>';
	str += '	<legend>Personal Details</legend>';	
	str += '	<table width="100%" border="0">';
	str += '	<tr>';
	str += '		<td width="70%" valign="top">';
	str += '			<table width="100%" class="cadreProfileInfoTable">';
	str += '			<tr>';
	str += '				<th style="width:125px">First Name </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.firstName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Last Name</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.lastName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Gender</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.gender+'</td>';
	str += '			</tr>';	
	str += '			<tr>';
	str += '				<th style="width:125px">Date Of Birth</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.dateOfBirth+'</td>';
	str += '			</tr>';
	str += '				<th style="width:125px">Blood Group</th>';
	str += '				<th>:</th>';
	str += '				<td>'+results.bloodGroupStr+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">No Of Family Members </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.noOfFamilyMembers+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">No Of Voters </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.noOfVoters+'</td>';
	str += '			</tr>';
	str += '			</table>';
	str += '		</td>';
	str += '		<td width="50%" valign="top">';
	str += '			<img height="120px" width="140px" src="images/cadre_images/'+results.image+'"/>';
	str += '		</td>';
	str += '	</tr>';
	str += '	</table>';

	var familyMemberTableFlag = results.firstFamilyMemberName != null || 														results.secondFamilyMemberName !=null ||
								results.thirdFamilyMemberName != null;
	if(familyMemberTableFlag)
	{
	str += '	<table width="100%" border="0" class="cadreProfileInfoTable" >';
	str += '	<tr>';
	str += '	<th style="width:150px" ><U>Family Member Details :</U></th>';
	str += '	<td></td>';
	str += '	</tr>';
	str += '	</table>';
		
	str += '	<table width="100%" border="0" class="cadreProfileInfoTable" >';
	}
	
	if(results.firstFamilyMemberName != null)
	{
	str += '	<tr>';
	str += '	<th style="width:60px" >1)&nbsp;&nbsp; Name :</th>';
	str += '	<td style="width:80px">'+results.firstFamilyMemberName+'</td>';
	str += '	<th style="width:80px" >Relationship :</th>';
	str += '	<td style="width:60px">'+results.firstFamilyMemberRelation+'</td>';
	str += '	<th style="width:80px" > Date Of Birth:</th>';
	str += '	<td style="width:60px">'+results.firstFamilyMemberDOB+'</td>';
	str += '	</tr>';
	}

	if(results.secondFamilyMemberName !=null)
	{
	str += '	<tr>';
	str += '	<th style="width:60px" >2)&nbsp;&nbsp; Name :</th>';
	str += '	<td style="width:80px">'+results.secondFamilyMemberName+'</td>';
	str += '	<th style="width:80px" >Relationship :</th>';
	str += '	<td style="width:60px">'+results.secondFamilyMemberRelation+'</td>';
	str += '	<th style="width:80px" > Date Of Birth:</th>';
	str += '	<td style="width:60px">'+results.secondFamilyMemberDOB+'</td>';
	str += '	</tr>';
	}

	if(results.thirdFamilyMemberName != null)
	{
	str += '	<tr>';
	str += '	<th style="width:60px" >3)&nbsp;&nbsp; Name :</th>';
	str += '	<td style="width:80px">'+results.thirdFamilyMemberName+'</td>';
	str += '	<th style="width:80px" >Relationship :</th>';
	str += '	<td style="width:60px">'+results.thirdFamilyMemberRelation+'</td>';
	str += '	<th style="width:80px" > Date Of Birth:</th>';
	str += '	<td style="width:60px">'+results.thirdFamilyMemberDOB+'</td>';
	str += '	</tr>';
	}
	if(familyMemberTableFlag)
	{
		str += '	</table>';
	}

	str += '	</fieldset>';

	str += '	<fieldset>';
	str += '	<legend>Contact Details</legend>';
	str += '	<table width="100%" class="cadreProfileInfoTable" border="0">';
	str += '		<tr>';
	str += '			<th style="width:125px">Land Line</th>';
	str += '			<th>&nbsp:</th>';
	str += '			<td style="width: 111px;">'+results.telephone+'</td>';
	str += '			<th style="width:125px">Mobile</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.mobile+'</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">Email</th>';
	str += '			<th>&nbsp:</th>';
	str += '			<td colspan="4">'+results.email+'</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '	<table border="0" width="100%">';
	str += '		<tr>';
	str += '			<td width="50%">';
	str += '			<table class="cadreProfileInfoTable">';
	str += '				<tr>';
	str += '					<th><U>Current Address</U></th>';
	str += '				</tr>';
	str += '					<tr>';
	str += '					<th style="width:125px">House No </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.houseNo+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">Street </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.street+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">Village/Ward </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.villageName+'</td>';
	str += '				</tr>';

	if(results.boothName != null && results.boothName.length >0)
	{
	str += '					<th style="width:125px">Booth </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.boothName+'</td>';
	str += '				</tr>';
	str += '				</tr>';
	}
	str += '					<th style="width:125px">Mandal/Municipality </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.mandalName+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">Constituency</th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.constituencyName+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">District</th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.districtName+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">State </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.stateName+'</td>';
	str += '				</tr>';
	str += '				<tr>';
	str += '					<th style="width:125px">Pincode</th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.pinCode+'</td>';
	str += '				</tr>';	
	str += '		</table>';
	str += '</td>';
	str += '<td valign="top">';
	//str += '			<div class="tableHeadingDiv">Official Address</div>';
	str += '			<table width="100%" class="cadreProfileInfoTable">';
	str += '				<tr>';
	str += '					<th><U>Official Address</U></th>';
	str += '				</tr>';
	if(results.sameAsCA != true)
	{	
	str += '			<tr>';
	str += '				<th style="width:125px">House No </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.phouseNo+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Street </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pstreet+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Village/Ward </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pvillageName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	if(results.PBoothName != null && results.PBoothName.length >0)
	{
	str += '					<th style="width:125px">Booth </th>';
	str += '					<th> : </th>';
	str += '					<td>'+results.PBoothName+'</td>';
	str += '				</tr>';
	str += '				</tr>';
	}
	str += '			<tr>';
	str += '				<th style="width:125px">Mandal/Municipality </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pmandalName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Constituency</th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pconstituencyName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">District</th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pdistrictName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">State </th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.pstateName+'</td>';
	str += '			</tr>';
	str += '			<tr>';
	str += '				<th style="width:125px">Pincode</th>';
	str += '				<th> : </th>';
	str += '				<td>'+results.ppinCode+'</td>';
	str += '			</tr>';	
	}
	else if(results.sameAsCA == true)
	{
	str += '			<tr>';
	str += '				<td>Same As Current Address</td>';
	str += '			</tr>';	
	}
	str += '			</table>';
	str += '		</td>';
	str += '	</tr>';
	str += '	</table>';
	str += '	</fieldset>';
	str += '	<fieldset>';
	str += '	<legend>Social Status</legend>';
	str += '	<table width="100%" class="cadreProfileInfoTable" border="0">';
	str += '		<tr>';
	str += '			<th style="width:125px">Education</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.educationStr+'</td>';
	str += '			<th style="width:125px">Occupation</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.professionStr+'</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">Annual Income</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.income+'</td>';
	str += '			<th style="width:125px">Caste Category</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.casteCategoryStr+'</td>';
	str += '		</tr>';
	str += '	</table>';
	str += '	<table width="100%" class="cadreProfileInfoTable" border="0">';
	str += '		<tr>';
	str += '			<th style="width:125px" colspan="6"><u>Language Efficiency</u></th>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">English</th>';
	str += '			<th>:</th>';
	for(var i in results.languageOptions_English)
	{
		if(results.languageOptions_English[i] != null)
			str += '<td>'+results.languageOptions_English[i]+'</td>';
	}
	str += '		</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">Hindi</th>';
	str += '			<th>:</th>';
	for(var j in results.languageOptions_Hindi)
	{
		if(results.languageOptions_Hindi[j] != null)
			str += '<td>'+results.languageOptions_Hindi[j]+'</td>';
	}
	str += '		</tr>';	
	str += '	</table>';
	str += '	</fieldset>';
	
	str += '	<fieldset>';
	str += '	<legend>Cadre Level Details</legend>';
	str += '	<table class="cadreProfileInfoTable" border="0">';
	str += '		<tr>';
	str += '			<th style="width:125px">Member Type</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.memberType+'</td>';
	str += '		</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">Membership No</th>';
	str += '			<th>:</th>';
	if(results.memberShipNo != null)
	 str += '			<td>'+results.memberShipNo+'</td>';
	else
	 str += '			<td></td>';
	str +='			</tr>';
	str += '		<tr>';
	str += '			<th style="width:125px">Remarks</th>';
	str += '			<th>:</th>';
	if(results.note != null)
	 str += '			<td>'+results.note+'</td>';
	else
	 str += '			<td></td>';
	str += '		</tr>';
	if(results.memberType == "Active")
	{
	str += '		<tr>';
	str += '			<th style="width:125px">Cadre Level</th>';
	str += '			<th>:</th>';
	str += '			<td>'+results.strCadreLevel+'-'+results.strCadreLevelValue+'</td>';
	str += '		</tr>';

	if(results.cadreRolesStr != null && results.cadreRolesStr.length > 0)
	{
	str += '		<tr>';
	str += '			<th style="width:125px">Cadre Roles</th>';
	str += '			<th>:</th>';

		for(var k in results.cadreRolesStr)
		{
			str += '<td>'+results.cadreRolesStr[k]+'</td>';
		}
	str += '		</tr>';
	}
		if(results.partyCommitteeName != null)
		{		
			str += '		<tr>';
			str += '			<th style="width:125px">Party Committee</th>';
			str += '			<th>:</th>';
			str += '			<td>'+results.partyCommitteeName+'</td>';
			str += '			<th style="width:125px">Designation</th>';
			str += '			<th>:</th>';
			str += '			<td>'+results.designationStr+'</td>';
			str += '		</tr>';
			str += '		<tr>';
			str += '			<th style="width:125px">Effective Date</th>';
			str += '			<th>:</th>';
			str += '			<td>'+results.effectiveDate+'</td>';
			str += '			<th style="width:125px">Ending Date</th>';
			str += '			<th>:</th>';
			if(results.endingDate != null)
			str += '			<td>'+results.endingDate+'</td>';
			str += '		</tr>';
		}	
	}
	str += '		</table>';
	str += '	</fieldset>';
	if(results.memberType == "Active" && userType == 'Party' && userParty == 'BJP')
	{
	str += '	<fieldset>';
	str += '	<legend>Other Details</legend>';
	str += '		<table class="cadreProfileInfoTable">';
	str += '		<tr>';
	str += '		<th><U>Skills</U></th>';
	str += '		</tr>';
	if(results.cadreSkillsNames != null)
	{
		str += '		<tr>';
		for(var l in results.cadreSkillsNames)
		{			
			str += '		<td>'+results.cadreSkillsNames[l]+'</td>';
			if (i != 0 && i % 3 == 0)
				str += '		</tr>';	
		}
	}
	str += '		<tr>';
	str += '		<th><U>Participated Training Camps</U></th>';
	str += '		</tr>';		
	if(results.cadreParticipatedCampNames != null)
	{
		str += '		<tr>';
		for(var k in results.cadreParticipatedCampNames)
		{
			str += '		<td>'+results.cadreParticipatedCampNames[k]+'</td>';
			if (i != 0 && i % 3 == 0)
				str += '		</tr>';	
		}
	}		
	str += '		</table>';	
	str += '	</fieldset>';
	}
	str += '</div>';
	//str += '<div id="cadreInfo_footer">';
	//str += ' <input type="button" value="Send SMS" onclick="senedSMSToCadre(\''+results.mobile+'\')">';
	str += '<span id="errorMsgSpan" style="color:red;font-size:10px;"></span>';
	str += '</div>';

	elmt.innerHTML = str;
}

function senedSMSToCadre(mobileNo)
{
	var elmt = document.getElementById("errorMsgSpan");
	if(!elmt)
		return;
	if(mobileNo == null || mobileNo == "")
	{
		elmt.innerHTML = 'SMS cannot be send due to absence of mobile number';
		return;
	}
	
	var selectedCadresArray = new Array();
	selectedCadresArray.push(mobileNo);
	

	var jsObj=
		{				
				cadreIds:selectedCadresArray,
				task:"sendSMSForCadreIds"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/sendSMSForCadresAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getCadreInfo()
{	
	var jsObj=
		{				
				cadreId:cadreId,
				task:"getCadreInfoByCadreId"
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCadreInfoAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

</script>
</head>
<body>
	<div id="cadreInfoMain">
		
	</div>

	<script type="text/javascript">
		getCadreInfo();
	</script>
</body>
</html>