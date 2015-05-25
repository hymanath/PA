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
	
	<script>
	
	</script>
	<style>
		
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
					<input type="text" placeholder="ENTER MOBILE NO " class="input-block-level" id="mobileNo">
				</div>
				
				<div class="span4">
					<input type="text" placeholder=" ENTER VOTERID" class="input-block-level" id="voterId">
				</div>
			</div>
			
			
			<div class="row-fluid">
				<div class="span4 offset4">
					<input type="button" id="searchbtn" style="margin-bottom: 20px;" class="btn btn-success btn-block" onclick="getCadreDetails()" value="SEARCH">
				</div>
				<div class="span12" id="tableDataDiv"></div>
			</div>
		</div>
		<div id="updateTableDiv" style="clear:both;"></div>
		<button class="btn btn-success" onclick="updateFamilyInfo();"> UPDATE</button>

		
	<script>
	
	function getCadreDetails()
	{
				
		var mobileNo =	$('#mobileNo').val().trim();
		var voterId = $('#voterId').val().trim();
		var membershipNo =$('#membershipNo').val().trim();
		
		if(mobileNo.trim().length == 0 && voterId.trim().length == 0 && membershipNo.trim().length == 0)
		{
				$('#errorDiv').html('Please Enter Any Search Criteria').css("color","red");	
				return false;
		}
	
		$('#errorDiv').html('');	
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
			buildTableCadreInfo(result);
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
			str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageBase64String+'"  /></td>';
			str+=' <td>'+result[i].address+'</td>';
			str+=' <td>'+result[i].mobileNumber+'</td>';
			str+=' <td>'+result[i].constituencyId+'</td>';
			
			str+=' </tr>';
		}
		str+='</tbody></table></div>';
		$("#tableDataDiv").html(str);
		$("#tableId").dataTable();
	}

function getFamilyDetails(tdpCadreId)
	{

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
			
		});
		
	}
	
	

	function buildfamilyDetails()
	{
		var str ='';
		str+='<table class="table table-bordered">';
		str+='<tr>';
		str+='<th>Name</th>';
		str+='<th>Mobile</th>';
		str+='<th>Gender</th>';
		str+='<th>Age</th>';
		str+='<th>VoterCardNo</th>';
		str+='<th>Relation</th>';
		str+='<th>DOB</th>';
		str+='<th>MarriageDay</th>';
		str+='<th>Email</th>';
		str+='<th>Caste State</th>';
		str+='<th>Education</th>';
		str+='<th>WhatsAPP</th>';
		str+='<th>MemberSince</th>';
		str+='</tr>';
		for(var i=0;i<3;i++)
		{
		str+='<tr class="familyInfo">';
		str+='<td style="display:none;"><input type="hidden" value="cadreId"  class="tdpCadre" /></td>';
		str+='<td><input type="text" value="Name'+i+'"  class="name" />Name'+i+'</td>';
		str+='<td><input type="text" value="Mobile'+i+'" class="mobile" />Mobile'+i+'</td>';
		str+='<td><select class="gender"><option value="M">Male</option><option value="F">FeMale</option></select></td>';
		str+='<td><input type="text" value="age'+i+'"  class="age" />Age'+i+'</td>';
		str+='<td><input type="text" value="voter'+i+'"  class="voter" />VoterCardNo'+i+'</td>';
		str+='<td><select class="relation"	<option value="0">Select Relation</option><option value="1">Father</option><option value="2">Mother</option>	<option value="3">Wife</option>	<option value="4">Brother</option><option value="5">Sister</option>	<option value="7">Husband</option>	<option value="8">Son</option>	<option value="9">Daughter</option>	<option value="10">Grand Son</option>	<option value="11">Grand Daughter</option>	<option value="12">Others</option>	</select></td>';	
		str+='<td><input type="text" value="dob'+i+'" class="dob" />dob'+i+'</td>';
		str+='<td><input type="text" value="marriage'+i+'"  class="marriageDay" />marriage'+i+'</td>';
		str+='<td><input type="text" value="email'+i+'"  class="email" />email'+i+'</td>';
		str+='<td><select class="casteState"><option value="0">Select Caste</option></select></td>';
		str+='<td><select class="education"><option value="0">Select Education</option></select></td>';
		str+='<td><input type="text" value="whatsappDate'+i+'"  class="whatsappStatus" />whatsAPP'+i+'</td>';
		str+='<td><input type="text" value="membersince'+i+'"  class="membersince" />membersince'+i+'</td>';
		str+='</tr>';
		}
			str+='</table>';
		$("#updateTableDiv").html(str);
		
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
		var voterId="";
		var relationId = 0;
		var tdpCadreId =0;
		var occupationId=0;
	var dataArr = new Array();
	
	$(".familyInfo").each(function(){
		$this = $(this);
		var age = $(this).find(".age").val();
		var casteStateId = $(this).find(".casteState").val();
		var dob = $(this).find(".dob").val();
		var name = $(this).find("name").val();
	    var education = $(this).find(".education").val();
		var email = $(this).find(".email").val();
		var gender = $(this).find(".gender").val();
		var marriageDay = $(this).find(".marriageDay").val();
		var whatsAPP = $(this).find(".whatsappStatus").val();
		var mobileNo = $(this).find(".mobile").val();
		var partyMemberSince = $(this).find(".membersince").val();
		var voterId = $(this).find(".voter").val();
		var relation = $(this).find(".relation").val();
		var tdpCadre = $(this).find(".tdpCadre").val();
	    var occupation = $(this).find(".occupation").val();
		
		var obj = {
		age:age,
		casteStateId:casteStateId,
		dob:dob,
		name:name,
		education:education,
		email:email,
		gender:gender,
		marriageDay:marriageDay,
		whatsappStatus:whatsAPP,
		mobileNo:mobileNo,
		partyMemberSince : partyMemberSince,
		voterId:voterId,
		relationId:relation,
		tdpCadreId:tdpCadre,
		occupationId:occupation,
			bloodGroup:0
	}
		dataArr.push(obj);
	})
console.log(dataArr);
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
			  if(result != null){
				
			}
	   });
}
	</script>
	
  </body>
 </html>