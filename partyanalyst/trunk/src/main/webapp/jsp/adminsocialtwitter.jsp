<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<link href="styles/socialnetwork/style.css" rel="stylesheet" type="text/css" />
	<link type="text/css" href="styles/socialnetwork/bootstrap.css"/>
	<link type="text/css" href="styles/socialnetwork/bootstrap-responsive.min.css" rel="stylesheet" />
	<link type="text/css" href="styles/socialnetwork/bootstrap-responsive.css" rel="stylesheet" />
<script type="text/javascript" src="js/commonUtilityScript/regionSelect.js"></script>
<script type="text/javascript" src="js/homePage/homePage.js"> </script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js">
</script>
<style>

#resultofajax * tr:nth-child(odd){background:#E0E0E0 ;}
#resultofajax * tr:nth-child(even){background:#F0F0F0 ;}


#electionTypeId,#state,#district,#constituency{
width :225px;
}
</style>

<script type="text/javascript">

		var timeST = new Date().getTime();

		function checkElectionType(electionTypeId)
{

var electionType = document.getElementById('electionTypeId');

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


}	



function showDetailedElectionResult()
	{
var	status;
 var constituencyEle = document.getElementById("constituency");
	
	 var constituencyId = constituencyEle.options[constituencyEle.selectedIndex].value;
	  
var electionType=document.getElementById('electionTypeId');
var elecType = electionType.options[electionType.selectedIndex].value;


var allRadioele=document.getElementById('allRadio');

var task="getParliamentInfo";

var stateEle=document.getElementById("state");

 var stateId = stateEle.options[stateEle.selectedIndex].value;
//alert("state value:"+stateId);
//debugger;
var	districtEle=document.getElementById("district");

 var districtId = districtEle.options[districtEle.selectedIndex].value;
//alert("district value:"+districtId);

	if(allRadio.checked)
	status="all";
	else if(winnerRadio.checked)
	status="Winner";
	else if(placeRadio.checked)
	status="Runner";
//alert("status value:"+status);

var jsObj=
	{
	stateId:stateId,
		districtId:districtId,
		status:status,
	constituencyId:constituencyId,
		electionType:"Assembly",
		rank:1,
		task:task
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAdminSocialAction.action?"+rparam;
	 callHomePageAjax(jsObj,url);
	}



function getStatesForNewLayOut()
{

	var electionType = document.getElementById('electionTypeId');

	var jsObj=
		{						
				
				electionType:electionType,
				task:"getStates"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getStatesForHomepage.action?"+rparam;						
	callHomePageAjax(jsObj,url);

}
function getAllDetails(id,task,areaType,constId)
   {
	  var jsObj =
		{ 
            time : timeST,
			id:id,
			task:task,
			taskType:"",
			selectElementId:"",
			address:"",
			areaType:areaType,
			constId:constId
		};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url ="locationsHierarchiesAjaxAction.action?"+rparam;						
	 callHomePageAjax(jsObj,url);
   }

function getConstituenciesInDistrict(result){
	var elmt = document.getElementById('constituency');
	var option = document.createElement('option');

	 clearOptionsListForSelectElmtId('constituency');
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
	for(var i in result)
	{
		
		var option=document.createElement('option');
		option.value=result[i].id;
		option.text=result[i].name;
		
		try
	{
	elmt.add(option,null);	

	}
	catch (ex)
	{
		elmt.add(option);
	}
	}
}

function getParliamentInfo(result){
	var stateEle=document.getElementById("state");

 var stateId = stateEle.options[stateEle.selectedIndex].value;
if(result == null || result.length == 0)
	return;

var str='';


str +='<table  align="center" width="700px" >';
str +='<tr bgcolor="#C0C0C0" style=" height:35px;">';
str +='<th>Serial No</th>';
str +='<th>Candidate Name</th>';
str +='<th>Caste</th>';
str +='<th>Address</th>';
str +='<th>Image</th>';
str +='<th></th>';
str +='</tr>';

for(i=0; i<result.length ;i++){


	str +='<tr >';
	  str +='<td id="td" style="width: 60px; height: 100px;" >'+(i+1)+'</td>';
    str +='<td id="td" style="width: 60px; height: 100px;">'+result[i].lastName+'</td>';
	if(result[i].casteName!=null)
	  str +='<td id="td" style="width: 60px; height: 100px;">'+result[i].casteName+'</td>';
	else
	  str +='<td id="td" style="width: 60px; height: 100px;">--</td>';
	if(result[i].addressName!=null)  
	   str +='<td id="td" style="width: 60px; height: 100px;">'+result[i].addressName+'</td>';
	else  
	   str +='<td id="td" style="width: 60px; height: 100px;">--</td>';
	if(result[i].addressName!=null)     
	    str +='<td id="td" style="width: 25px; height: 100px; cursor: default; margin-left: 20px;"><img src="images/candidates/'+result[i].lastName+'.jpg" height="100px" width="100px"/></td>';
	else 
		str +='<td id="td" style="width: 25px; height: 100px; cursor: default; margin-left: 20px;"><img src="pictures/profiles/member.jpg"/></td>';
	   str +='<td id="td" style="width: 40px; height: 100px;"><a href="candidateUpdateFormAction.action?stateId='+stateId+'&candidateId='+result[i].candId+'&candidateName='+result[i].lastName+'">Edit</a></td>';
	str +='</tr>';
}
	
str+='</table>';
$("#resultofajax").html(str);
	
}

function callHomePageAjax(jsObj,url)
{	
	
	
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == 'constituenciesInDistrict')
							{
								getConstituenciesInDistrict(myResults);				
							}if(jsObj.task =='getParliamentInfo')
							{
								getParliamentInfo(myResults);				
							}
							if(jsObj.task =='getStates')
							{
								getStatesInfo(myResults);
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


function getStatesInfo(myResults){
	
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


}
function getDetails(){

	var divele=document.getElementById('buttons');
	divele.style.display="block";
	showDetailedElectionResult();

}

function getPartyName()
{
	
	var str='';
	var member;
	var ele=document.getElementById('ele');
	var allRadioele=document.getElementById('allRadio');
	var winnerRadioele=document.getElementById('winnerRadio');
	var placeRadioele=document.getElementById('placeRadio');
	var task = null;
	if(allRadioele.checked)
	{
	member =allRadioele.value;
	task="getPartyNames";
	}
	else if(winnerRadioele.checked)
	{
	member=winnerRadioele.value;
		task="getCandidateNames";
	}else if(placeRadioele.checked)
	{
		member=placeRadioele.value
		task="getNames";
	}
		var jsObj=
		{
				elmtId:member,
				task:task					
		};
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getTwitterAction.action?"+rparam;						
		callHomePageAjax(jsObj,url);
	}

</script>


<style>
#div1{
text-align: center;
 margin-left: 20px;
}


</style>
</head>

<body>
<h2 class="reg" align="center"> Candidate Profile Form 
		</h2>
<div id="mainDiv" align="center" style="border:2px solid blue">
<table align="center">
	
	<tr><td> &nbsp;</td></tr>
	<tr>
		<td style="padding-left: 25px;"> ElectionType :</td>
		<td>
			<select name="electionTypeId" style="margin-left: 0px; margin-top: 0px;" 
			onchange="checkElectionType(this.options[this.selectedIndex].value,'state')"  id="electionTypeId" style="margin-left: 100px; margin-top: -16px;">
			<option value="0">- -ElectionType- -</option>
			<option value="2">Assembly</option>
			<option value="1">Parliment</option>
			</select>
		</td>
	</tr>

  <tr><td> &nbsp;</td></tr>

  <tr>
		<td style="padding-left: 69px;"> State :</td>
		<td> 
			<select name="state" style="margin-left: 0px; margin-top: 0px;" onchange="getDistrictsComboBoxForAState(this.options[this.selectedIndex].value,'district')" id="state" style="margin-left: 100px; margin-top: -16px;">
				<option>- -State- -</option>

			</select>
		</td>
  </tr>
  <tr><td> &nbsp;</td></tr>
	<tr>
		<td style="padding-left: 55px;"> District :</td>
		<td> 
			<select name="district" style="margin-left: 0px; margin-top: 0px;" onchange="getAllDetails(this.options[this.selectedIndex].value,'constituenciesInDistrict','','');" id="district" style="margin-left: 100px; margin-top: -16px;" >
				<option>- -District- -</option>
				
			</select>
		</td>
	</tr>
	<tr><td> &nbsp;</td></tr>
	<tr>
		<td style="padding-left: 20px;"> Constituency :</td>
		<td> 
			<select name="constituency" style="margin-left: 0px; margin-top: 0px;" onchange="getDetails()" id="constituency" style="margin-left: 100px; margin-top: -16px;">
				<option>- -Constituency- -</option>
			</select>
		</td>
	</tr>
</table>




<br><br>
	<div id="div1">

		<div id="buttons" style="display:none; ">

			<input name="allRadio" type="radio" id="allRadio" checked="checked"   onclick="showDetailedElectionResult()"  style="cursor:pointer;"/>&nbsp;&nbsp;All 
			 
			<input name="allRadio" type="radio" id="winnerRadio" onclick="showDetailedElectionResult()"/>&nbsp;&nbsp;Winners 
			
			<input name="allRadio" type="radio" id="placeRadio" onclick="showDetailedElectionResult()"/>&nbsp;&nbsp;SecondPlace 
<br><br>
			<div id="resultofajax"class="reg">
			</div>

		</div>	
	</div>
<br><br>
</div>

</body>
</html>