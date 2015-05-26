<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>TDP Cadre Family Details</title>
	 <!-- Bootstrap -->
   
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 <script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
		 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<script>
	
	</script>
	<style>

tr.lowPerf{background:#FFA500 !important;}
tr.lowPerf td{background:#FFA500 !important;}
#dayWiseUsersDetailsId  thead th{
background-color: #dff0d8  !important;
color : #468847 !important;
line-height: 15px !important;
}
#dayWiseUsersDetailsId th{text-align:center !important;}
#dayWiseUsersDetailsId tr.odd td.sorting_1{
background-color: #d3d3d3 !important;
}
#dayWiseUsersDetailsId tr.even td.sorting_1 {
background-color: #fafafa !important;
}
#dayWiseUsersDetailsId tr.odd {
background-color: #E5E5E5 !important;
}

	</style>
	
  </head>
  <body>
<div class="container">
			<!-------ADD BLOCK------>

	<div class="row m_top20" id="searchcadreDiv" class="row m_top20">	
	
	
		<div class="span12" style="margin-top: 20px;">
			<h4 id="headingDiv" class="text-uppercase"> Search Cadre</h4>
		
		</div>
		<div class="span12" >
			<div class="span4" id="errorDiv" style="margin-top: 10px;margin-bottom: 10px; margin-left: 0px;"></div>
		</div>
		<div class="span12" style="margin-bottom: 20px;">
			
			<div class="row-fluid">  
				<div class="span4"> 
					<input type="text" id="membershipNo" class="input-block-level" placeholder="ENTER MEMBERSHIP ID ">
				</div>
				
				<div class="span4" >
					<input type="text" placeholder="ENTER MOBILE NO " value="9581434970" class="input-block-level" id="mobileNo" maxlength="10" onkeyup="">
				</div>
				
				<div class="span4">
					<input type="text" placeholder=" ENTER VOTERID" class="input-block-level" id="voterId">
				</div>
			</div>
			
			
			<div class="row-fluid">
				<div class="span4 offset4">
					<input type="button" id="searchbtn" style="margin-bottom: 20px;" class="btn btn-success btn-block" onclick="getCadreDetails()" value="SEARCH">
				</div>
				<div class="span12" id="tableDataDiv" style="margin-left: -15px"></div>
			</div>
		</div>
		<img src='images/Loading-data.gif' class="offset5"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>
		<div id="updateTableDiv" style="clear:both;overflow:scroll;display:none;"></div>
		<button class="btn btn-success offset4" style="margin-top:15px;margin-bottom:15px;;display:none;" onclick="updateFamilyInfo();" id="updateeBtn"> UPDATE DETAILS </button>

		
	<script>
	
	var relationsArr = new Array();
	var casteArr = new Array();
	var occupationArr = new Array();
	var educationArr = new Array();
	var bloodGroupArr = new Array();
	<c:forEach var="caste" items="${castesList}">
				var obj = {
					id :  '${caste.casteId}',
					name :  '${caste.castName}'
				}
				casteArr.push(obj);
		</c:forEach>

	var searchArr = new Array();
	
	<c:forEach var="caste" items="${educationList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				educationArr.push(obj);
		</c:forEach>
		
		<c:forEach var="caste" items="${occupationsList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				occupationArr.push(obj);
		</c:forEach>

				
	</script>
	<script>
	
	$(document).ready(function(){
			$(document).keypress(function(e) {
				if(e.keyCode==13){
					 getCadreDetails();
				}
		  });
	});
	
	
	function getCadreDetails()
	{
				
		var mobileNo =	$('#mobileNo').val().trim();
		var voterId = $('#voterId').val().trim();
		var membershipNo =$('#membershipNo').val().trim();
		searchArr=[];
		if(mobileNo.trim().length == 0 && voterId.trim().length == 0 && membershipNo.trim().length == 0)
		{
				$('#errorDiv').html('Please Enter Any Search Criteria').css("color","red");	
				return false;
		}
		$("#tableDataDiv").html("");
		$('#searchDataImg').show();	
		$('#errorDiv').html('');	
		$("#updateTableDiv").html('');	
		$("#updateFamilyTableDiv").html('');	
		//$("#ajaxImage").show();
		var jsObj = {
			mobileNo:mobileNo,
			membershipNo:membershipNo,
			voterId:voterId,			
			task:"getDetails"            
		}
  
		$.ajax({
			type : "POST",
			url : "searchCadreForFamilyDetlsUpdationAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//$("#ajaxImage").hide();
			$('#searchDataImg').hide();				
			if(result != null && result.length>0)			
			{
				buildTableCadreInfo(result);
			}
			else{
				$("#tableDataDiv").html("</span style='text-align:center;'> No data Available<span>");
			}
		});
		
	}

	
	function buildTableCadreInfo(result){
		var str="";	
		str+='<div class="span12">';
			
		str+=' <table class="table table-bordered" style="margin-left: 0px;"  id="tableId">';
		str+=' <thead>';
		str+=' <tr>';
		str+=' <th class="alert-info"> Cadre Name  </th>';
		str+=' <th class="alert-info"> Image  </th>';
		str+=' <th class="alert-info" > Membership Number  </th>';
		str+=' <th class="alert-info" > Mobile Number </th>';
		str+=' <th class="alert-info" > Constituency </th>';
		str+=' </tr>';
		str+=' </thead>';
		str+=' <tbody>';
	
		for(var i in result){
			str+=' <tr>';
		
		
			str+=' <td><a href="javascript:{};" style="cursor:pointer;" onclick="getFamilyDetails(\''+result[i].cadreId+'\');">'+result[i].nameType+'</a></td>';
			str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageBase64String+'"  style="height:50px;width:50px;"/></td>';
			str+=' <td>'+result[i].address+'</td>';
			str+=' <td>'+result[i].mobileNumber+'</td>';
			str+=' <td>'+result[i].constituencyId+'</td>';
			
			str+=' </tr>';
			var cadreObj={
				name:result[i].nameType,
				mobileNo:result[i].mobileNumber,
				gender:result[i].gender,
				age:result[i].age,
				votercardNo :result[i].voterCardNo,
				relationId:null,
				dob: (result[i].dobStr).substring(0,11),
				marriageDay:(result[i].marriageDateStr).substring(0,11),
				email:result[i].email,
				casteStateId:result[i].casteId ,
				educationId:result[i].educationId,
				occupationId:result[i].occupationId,
				whatsappStatus :result[i].whatsAppStatus,
				partyMemberSince:(result[i].partyMemberSinceStr).substring(0,11),
				tdpCadreId:result[i].cadreId,
				bloodGroupId : 0
			};
			
			searchArr.push(cadreObj);
		}
		//console.log(searchArr);
		str+='</tbody></table></div>';
		$("#tableDataDiv").html(str);
		$("#tableId").dataTable();
	}

function getFamilyDetails(tdpCadreId)
	{
		$('#searchDataImg').show();	
		$('#updateeBtn').hide();	
		$('#updateTableDiv').hide();	
		$('#updateFamilyTableDiv').hide();	
		var jsObj = {
			tdpCadreId:tdpCadreId,			
			task:"getFamilyDetails"            
		}
  
		$.ajax({
			type : "POST",
			url : "getFamilyDetailsByCadreIdAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			//$("#ajaxImage").hide();
			$('#searchDataImg').hide();	
			$('#updateeBtn').show();	
			$('#updateTableDiv').show();	
			$('#updateFamilyTableDiv').show();	
			if(result != null && result.length>0)			
				buildfamilyDetails(result,tdpCadreId);
			else
				$("#updateTableDiv").html("</span style='text-align:center;'> No data Available<span>");
		});
		
	}
	
	

	function buildfamilyDetails(result,tdpCadreId)
	{
		$('#nextBuildBtnId').remove();
		tdpCadreId = tdpCadreId;
		var str ='';
		str+='<h4  class="offset5"> CADRE DETAILS </h4>';
		str+='<table class="table table-bordered" id="dayWiseUsersDetailsId"  style="margin-top:15px;">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>Name</th>';
		str+='<th>Mobile</th>';
		//str+='<th>Gender</th>';
		//str+='<th>Age</th>';
		//str+='<th>VoterCardNo</th>';
		//str+='<th>Relation</th>';
		str+='<th>DOB</th>';
		str+='<th>MarriageDay</th>';
		str+='<th>Email</th>';
		str+='<th>Caste State</th>';
		str+='<th>Education</th>';
		str+='<th>Occupation</th>';
		//str+='<th>Blood Group</th>';
		str+='<th> interested in WhatsApp Group? </th>';
		str+='<th>MemberSince</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		if(searchArr != null && searchArr.length>0)
		{
			for(var l in searchArr)
			{
				if(searchArr[l].tdpCadreId == tdpCadreId)
				{
						str+='<tr class="familyInfo">';
						str+='<td style="display:none;"><input type="hidden" value="'+searchArr[l].tdpCadreId+'"  class="tdpCadre" /></td>';
						if(searchArr[l].name != null)
						{
							str+='<td> <input type="text" value="'+searchArr[l].name+'" readonly class="name" /> </td>';
						}
						else
						{
							str+='<td> <input type="text"  class="name" /> </td>';
						}
						if(searchArr[l].mobileNo != null)
						{
							str+='<td><input type="text" value="'+searchArr[l].mobileNo+'"  class="mobile"  style="width: 100px"  maxlength="10" /> <input type="hidden" value="'+searchArr[l].votercardNo+'" class="voter"  style="width: 100px"/></td>';
						}
						else
						{
							str+='<td><input type="text"  class="mobile"  style="width: 100px"  maxlength="10" /> <input type="hidden" value="'+searchArr[l].votercardNo+'" class="voter"  style="width: 100px"/></td>';
						}

						str+=' </td>';

						if(searchArr[l].dob != null)
						{
							str+='<td><input type="text" value="'+searchArr[l].dob+'"  class="dob" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						else
						{
							str+='<td><input type="text"  class="dob" readonly="true" style="cursor:text;"  style="width: 100px"/> </td>';
						}
						
						if(searchArr[l].marriageDay != null)
						{
							str+='<td><input type="text" value="'+searchArr[l].marriageDay+'"  class="marriageDay" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						else
						{
							str+='<td><input type="text" class="marriageDay" readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
						if(searchArr[l].email != null)
						{
							str+='<td><input type="text" value="'+searchArr[l].email+'"  class="email"  style="width: 200px"/> </td>';
						}
						else
						{
							str+='<td><input type="text" class="email"  style="width: 200px"/> </td>';
						}
						
						str+='<td> ';
						if(casteArr != null && casteArr.length>0)
						{
							str+='<select id="" class="casteState"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Caste</option>';
							for(var k in casteArr)
							{
								if(searchArr[l].casteStateId != null && (casteArr[k].id ==searchArr[l].casteStateId ))
								{
									str+='<option value="'+casteArr[k].id+'" selected="selected">'+casteArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+casteArr[k].id+'" >'+casteArr[k].name+'</option>';
								}
							}
							str+='</select>';
						}
						str+=' </td>';
						
						str+='<td> ';
						if(educationArr != null && educationArr.length>0)
						{
							str+='<select id="" class="education"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Education</option>';
							for(var k in educationArr)
							{
								if(searchArr[l].educationId != null && (educationArr[k].id ==searchArr[l].educationId ))
								{
									str+='<option value="'+educationArr[k].id+'" selected="selected">'+educationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
								}
							}
							str+='</select>';
						}
						str+=' </td>';
						
						str+='<td> ';
						if(occupationArr != null && occupationArr.length>0)
						{
							str+='<select id="" class="occupation"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Occupation</option>';
							for(var k in occupationArr)
							{
								if(searchArr[l].occupationId != null && (occupationArr[k].id ==searchArr[l].occupationId ))
								{
									str+='<option value="'+occupationArr[k].id+'" selected="selected">'+occupationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
								}
							}
							str+='</select>';
						}
						str+=' </td>';
						str+='<td> ';
						
							str+='<select id="" class="whatsappStatus"  style="width: 130px">';
							str+='<option value="NO" >No</option>';
							str+='<option value="YES">Yes</option>';
							str+='</select>';

						str+=' </td>';
						
						if(searchArr[l].partyMemberSince != null)
						{
							str+='<td><input type="text" value="'+searchArr[l].partyMemberSince+'"  class="membersince"  readonly="true" style="cursor:text; width: 100px"/> </td>';
						}
						else
						{
							str+='<td><input type="text"  class="membersince"  readonly="true" style="cursor:text;width: 100px"/> </td>';
						}
					str+='</tr>';
					
				}
			}
		}
		
		str+='</tbody>';
			str+='</table>';
			str+='<div>';
			str+='<h4  class="offset5"> FAMILY DETAILS  </h4>';
			str+='<table class="table table-bordered" id="dayWiseUsersDetailsId" style="margin-top:15px;">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>Name</th>';
		str+='<th>Mobile</th>';
		//str+='<th>Gender</th>';
		//str+='<th>Age</th>';
		//str+='<th>VoterCardNo</th>';
		str+='<th>Relation</th>';
		str+='<th>DOB</th>';
		//str+='<th>MarriageDay</th>';
		str+='<th>Email</th>';
		//str+='<th>Caste State</th>';
		str+='<th>Education</th>';
		str+='<th>Occupation</th>';
		//str+='<th>Blood Group</th>';
		//str+='<th>WhatsAPP</th>';
		//str+='<th>MemberSince</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody id="familyMembers">';
		for(var i=0;i<result.length;i++)
		{
			str+='<tr class="familyInfo" id="familyInnfo'+i+'"> ';
				str+='<td style="display:none;"><input type="hidden" value="cadreId"  class="tdpCadre" /></td>';
				if(result[i].name != null)
				{
					str+='<td>  <a style="margin-top:10px" onclick="removeDetails(\'familyInnfo'+i+'\');" title="Click here to remove family member."><i class="icon-remove"></i></a> <input type="text" value="'+result[i].name+'"  class="name" readonly style="margin-left:15px;margin-top:-20px"/> </td>';
				}
				else
				{
					str+='<td>  <a style="margin-top:10px" onclick="removeDetails(\'familyInnfo'+i+'\');" title="Click here to remove family member."><i class="icon-remove"></i></a> <input type="text"  class="name" style="margin-left:15px;margin-top:-20px"/> </td>';
				}
				if(result[i].mobileNo != null)
				{
					str+='<td><input type="text" value="'+result[i].mobileNo+'"  class="mobile"   style="width: 100px"  maxlength="10" /> </td>';
				}
				else
				{
					str+='<td><input type="text"  class="mobile"  style="width: 100px"  maxlength="10" /> </td>';
				}
				
				
				

				str+='<td> ';
				if(relationsArr != null && relationsArr.length>0)
				{
					str+='<select id="" class="relation"  style="width: 150px">';
					str+='<option value="0" selected="selected">Select Relation</option>';
					for(var k in relationsArr)
					{
						if(result[i].relationId != null && (relationsArr[k].id ==result[i].relationId ))
						{
							str+='<option value="'+relationsArr[k].id+'" selected="selected">'+relationsArr[k].name+'</option>';
						}
						else
						{
							str+='<option value="'+relationsArr[k].id+'" >'+relationsArr[k].name+'</option>';
						}
					}
					str+='</select>';
				}
				str+=' </td>';
				if(result[i].dob != null)
				{
					str+='<td><input type="text" readonly value="'+(result[i].dob).substring(0,11)+'"  class="dob"   readonly="true"  style="width: 100px"/> </td>';
				}
				else
				{
					str+='<td><input type="text" class="dob"   readonly="true"  style="width: 100px"/> </td>';
				}
				
				if(result[i].email != null)
				{
					str+='<td><input type="text" value="'+result[i].email+'"  class="email"  style="width: 200px"/> </td>';
				}
				else
				{
					str+='<td><input type="text" class="email"  style="width: 200px"/> </td>';
				}
				
				str+='<td> ';
				if(educationArr != null && educationArr.length>0)
				{
					str+='<select id="" class="education"  style="width: 150px">';
					str+='<option value="0" selected="selected">Select Education</option>';
					for(var k in educationArr)
					{
						if(result[i].educationId != null && (educationArr[k].id ==result[i].educationId ))
						{
							str+='<option value="'+educationArr[k].id+'" selected="selected">'+educationArr[k].name+'</option>';
						}
						else
						{
							str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
						}
					}
					str+='</select>';
				}
				str+=' </td>';
				
				str+='<td> ';
						if(occupationArr != null && occupationArr.length>0)
						{
							str+='<select id="" class="occupation"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Occupation</option>';
							
							for(var k in occupationArr)
							{
							
								if(occupationArr[k].id != null && (occupationArr[k].id ==result[i].occupationId ))
								{
									str+='<option value="'+occupationArr[k].id+'" selected="selected">'+occupationArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
								}
							}
							str+='</select>';
						}
						str+=' </td>';
					/*str+='<td> ';
						if(bloodGroupArr != null && bloodGroupArr.length>0)
						{
							str+='<select id="" class="bloodGroup"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Blood Group</option>';
							for(var k in bloodGroupArr)
							{
								if(bloodGroupArr[l].educationId != null && (bloodGroupArr[k].id ==result[i].bloodGroupId ))
								{
									str+='<option value="'+bloodGroupArr[k].id+'" selected="selected">'+bloodGroupArr[k].name+'</option>';
								}
								else
								{
									str+='<option value="'+bloodGroupArr[k].id+'" >'+bloodGroupArr[k].name+'</option>';
								}
							}
							str+='</select>';
						}
						str+=' </td>';		
						*/
				/*if(result[i].partyMemberSince != null)
				{
					str+='<td><input type="text" value="'+(result[i].partyMemberSince).substring(0,11)+'"  class="membersince"  readonly="true" style="cursor:text; width: 100px" /> </td>';
				}
				else
				{
					str+='<td><input type="text"  class="membersince"  readonly="true" style="cursor:text;width: 100px"/> </td>';
				}*/
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table> <br>';
		str+='</div>';

		$("#updateTableDiv").html(str);
		
		var str1='';
		str1+='<div id="nextBuildBtnId">';
				str1+='<button id="addNewMember" class="newMemberCls btn btn-info pull-right" onclick="addNewMemberInFamily('+result.length+')" title="Add a new member in family." style="margin-top: 15px;margin-right:15px;"> + </button>';
			str1+='</div>';
		$("#updateTableDiv").after(str1);
		
		$( ".marriageDay" ).datepicker({
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: "-100:+0"
		});
		$( ".dob" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".membersince" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					yearRange: "-100:+0"
		});		
	}
	
function updateFamilyInfo()
{
	
	    var age = 0;
		var casteStateId= 0;
		var dob = "";
		var name="";
		var education=0;
		var email="";
		var gender="";
		var marriageDay="";
		var whatsappStatus="";
		var mobileNo="";
		var partyMemberSince = "";
		var voterId=0;
		var relationId = 0;
		var casteStateId = 0;
		var occupationId=0;
		var tdpCadre = 0;
	var dataArr = new Array();
	var finalCasteId = 0;
	var count = 0;
	$(".familyInfo").each(function(){
		count = count+1;
		$this = $(this);
		if($(this).find(".age").val())
		var age = $(this).find(".age").val();
		
		var dob = $(this).find(".dob").val();
		var name = $(this).find(".name").val();
	    var education = $(this).find(".education").val();
		var email = $(this).find(".email").val();
		var gender = "";
		var marriageDay = "";
		var whatsAPP = "";
		var mobileNo = $(this).find(".mobile").val();
		var partyMemberSince = $(this).find(".membersince").val();
		var voterId = $(this).find(".voter").val();
		var relation = 0;
	
	    var occupation = $(this).find(".occupation").val();
	    var bloodGroup = $(this).find(".bloodGroup").val();
		//alert(count)
			
		if(count == 1)
			{
			age = 0;
			finalCasteId = casteStateId;
			casteStateId = $(this).find(".casteState").val();
			gender = $(this).find(".gender").val();
			marriageDay = $(this).find(".marriageDay").val();
			whatsAPP = $(this).find(".whatsappStatus").val();
			tdpCadre = $(this).find(".tdpCadre").val();
			dob = $(this).find(".dob").val();
			relation = 13;
		}
		else
		{
			relation = $(this).find(".relation").val();;
			voterId = "";
			partyMemberSince="";
		}
		var obj = {
		age:0,
		casteStateId:casteStateId,
		dob:dob,
		name:name,
		education:education,
		email:email,
		gender:"",
		marriageDay:marriageDay,
		whatsappStatus:whatsAPP,
		mobileNo:mobileNo,
		partyMemberSince : partyMemberSince,
		voterId:voterId,
		relationId:relation,
		tdpCadreId:tdpCadre,
		occupationId:occupation,
		bloodGroup:bloodGroup
	}
		dataArr.push(obj);
	});

	var jsObj = 
	{
		dataArr:dataArr,
		task:"updateFamilyInfo"
	}
	$.ajax({
          type:'GET',
          url: 'updateFamilyInfoAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){ 
			  if(result != null && result.resultCode ==0){
				 getCadreDetails();
				}
	   });
	  
}

function getAllRelationDetails(){
	 $.ajax({
		type : "POST",
		url : "getAllRelationDetails.action"
	}).done(function(result){
	  if(result != null && result.length > 0){	   
	   for(var i in result){
		 //$('#relativeTypeId').append('<option value='+result[i].id+'>'+result[i].id+'</option>');
		 var obj={
				id:result[i].id,
				name:result[i].name
			};
			relationsArr.push(obj);
	    }
	  }
	});
}

function addNewMemberInFamily(size)
{
	size = parseInt(size)+1;
	var str='';

				str+='<tr class="familyInfo" id="familyInnfo'+size+'">';
				str+='<td>  <a style="margin-top:10px" onclick="removeDetails(\'familyInnfo'+size+'\');" title="Click here to remove family member."><i class="icon-remove"></i></a> <input type="text"  class="name" style="margin-left:15px;margin-top:-20px" /> </td>';
				str+='<td><input type="text"  class="mobile"  style="width: 100px"  maxlength="10" /> </td>';
				

				str+='<td> ';
				if(relationsArr != null && relationsArr.length>0)
				{
					str+='<select id="" class="relation"  style="width: 150px">';
					str+='<option value="0" selected="selected">Select Relation</option>';
					for(var k in relationsArr)
					{
						str+='<option value="'+relationsArr[k].id+'" >'+relationsArr[k].name+'</option>';
					}
					str+='</select>';
				}
				str+=' </td>';
				str+='<td><input type="text"   class="dob"  readonly="true" style="width: 100px"/> </td>';
				str+='<td><input type="text" class="email"  style="width: 200px"/> </td>';
				str+='<td> ';
				if(educationArr != null && educationArr.length>0)
				{
					str+='<select id="" class="education"  style="width: 150px">';
					str+='<option value="0" selected="selected">Select Education</option>';
					for(var k in educationArr)
					{
						str+='<option value="'+educationArr[k].id+'" >'+educationArr[k].name+'</option>';
					}
					str+='</select>';
				}
				str+=' </td>';
				str+='<td> ';
						if(occupationArr != null && occupationArr.length>0)
						{
							str+='<select id="" class="occupation"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Occupation</option>';
							for(var k in occupationArr)
							{
								str+='<option value="'+occupationArr[k].id+'" >'+occupationArr[k].name+'</option>';
							}
							str+='</select>';
						}
						str+=' </td>';
						/*str+='<td> ';
						if(bloodGroupArr != null && bloodGroupArr.length>0)
						{
							str+='<select id="" class="bloodGroup"  style="width: 130px">';
							str+='<option value="0" selected="selected">Select Blood Group </option>';
							for(var k in bloodGroupArr)
							{
								str+='<option value="'+bloodGroupArr[k].id+'" >'+bloodGroupArr[k].name+'</option>';
							}
							str+='</select>';
						}
						str+=' </td>';*/
				//str+='<td><input type="text"  class="membersince"  readonly="true" style="cursor:text;width: 100px"/> </td>';

			str+='</tr>';
		
		$('#familyMembers').append(str);
		
			$( ".marriageDay" ).datepicker({
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".dob" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});
		$( ".membersince" ).datepicker({	
					dateFormat: 'yy-mm-dd',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date(),
					yearRange: "-100:+0"
		});

		$("#nextBuildBtnId").html('<button id="addNewMember" class="newMemberCls btn btn-info pull-right" onclick="addNewMemberInFamily('+size+')" title="Add a new member in family." style="margin-top: 15px;margin-right:15px;"> + </button>');
		
}

function removeDetails(divId)
{
	var x=window.confirm("Are you sure want to remove it?")
	if (x)
	{
		$('#'+divId+'').remove();
	}
}
getAllRelationDetails();
	</script>
	
  </body>
 </html>