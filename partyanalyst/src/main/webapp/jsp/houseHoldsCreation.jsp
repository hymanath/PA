<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 

</head>
<body>
<style>
textarea{
	background-color:#fff;
}
#contenttable{
	min-height:200px;
}

</style>

<script>
$(document).ready(function(){
	$('#mainTypeId').change(function(){
		getSubSurveyTypes(this.value);
	});
	$('#mainTypeId').change();
});
</script>

  <div class="container" id="btnDiv">
        
		<div class="span" id="mainQtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createMainQuestions();" value="Create Main Question"></input></div>

		<div class="span" id="subQtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createSubQuestions();" value="Create Sub Questions" /></div>

		<div class="span" id="qtnDiv"><input type="button" class="btn btn-primary" style="margin:70px 50px" onClick="createQuestions();" value="Create Questions"></input></div>

		<div class="span" id="leaderDiv"><input type="button" class="btn btn-primary" style="margin:20px 50px" onclick="createNewLeader();" value ="Create Leader"></input></div>
     
 </div>
<div id="mainQtnDivDialog" style="display:none">
<div id="errorQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv1"></div>
    <div id="mainQtnDivId" style="margin:15px">Create MainQuestion :<font class="mandatory">*</font><input id="mainQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateMainQtn();"></input>
</div>

<div id="subQtnDivDialog" style="display:none">
<div id="errorSubQtnDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
<div id="statusDiv2"></div>
     <div id="subQtnDivId" style="margin:15px 70px;">Select Main Type:
	 <s:select theme="simple" name="mainTypeId1" id="mainTypeId1" list="mainSurveyTypes" listKey="id" listValue="name"/></div>
    <div style="margin-left:36px;">Create SubQuestion :<font class="mandatory">*</font><input id="subQtnId" type="text" maxlength="100"></div>
	<input type="button" class="btn" value="Create" style="margin-left:160px" onClick="validateSubQtn();"></input>
</div>

<div id="searchDiv" style="">
 <div id="errorSearchDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
</div>

 <div class="container" id="createQstnDiv" style="display:none">
   <div id="surveyQuestion">
     <div id="errorDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
	 <div id="statusDiv"></div>
     <div class="well">
     Select Main Type:
	 <s:select theme="simple" name="mainTypeId" id="mainTypeId" list="mainSurveyTypes" listKey="id" listValue="name"/>     	 
	     Select Sub Type:
	   <select id="subTypeId">			
	  </select>
	  </div>

	<!--<div id="errorDiv"></div>
	<div id="statusDiv"></div>-->

		<div id="questionDiv" class="well">

		<u><h5>Question:</h5></u>

		<!--<div class="note">Enter Question :
		<!-- EXAMPLE: Who will be the next C.M? :: Q1-->
		<!--</div>-->
		  <textarea id="questionId"></textarea>
		  <div id="qstnErrMsg"></div>
		</div>

		<div id="questionTypeDiv" class="well">
			<div style="margin:4px;">
			
			<b>Choose question type :</b>

			 <s:select theme="simple" name="optionTypeId" id="optionTypeId" list="optiontypes" listKey="id" listValue="name"/>  
			 </div>
		</div>
		<div id="locationTypeDiv" class="checkbox">
		</div>

    
		<div id="answersDiv" class="well">
           <u><h5>Answer Options:</h5></u>
			<div>
			<div class="note">NOTE :Enter options seperated by ::</div>
			<h5>FOR EXAMPLE: FIRST OPTION :: SECOND OPTION :: THIRD OPTION</h5>
			  <textarea  rows="3" cols="70" id="options"></textarea>

				<label><input type="checkbox" id="commentId">Comment Field</input></label>
			     <div id="optnErrMsg"></div>			  
			</div>
			 <a class="btn pull-right" href="javascript:{saveQuestionDetails()}">Add Question</a>

		</div>
	</div>
 </div>

<div id="leaderDivDialog" style="display:none">
 <div id="errorLeaderDiv" style="color:red;font-weight:bold;margin-bottom:10px;"></div>
  <div id="statusDiv3"></div>
    <div style="margin-left:36px;">
	<div>Voter Id:<font class="mandatory">*</font><input id="lVoterId" type="text" maxlength="20" style="margin-left:39px;" onblur="validateVoterId();"></div>
    <div>Name :<font class="mandatory">*</font><input id="leaderNameId" type="text" maxlength="100" style="margin-left:48px;"><input type="button" class="btn" value="Search" style="margin:1px 8px 14px" onClick="searchNow1();"></input></div>
	<div>Mobile No :<font class="mandatory">*</font><input id="lmobileId" type="text" maxlength="20" style="margin-left:24px;"></div>	
	<div>Book No:<font class="mandatory">*</font><input id="luniqueId" type="text" maxlength="20" style="margin-left:37px;"></div>
	<div>is Active:<font class="mandatory">*</font><input id="yesId" type="radio" value="YES" name="radiobtn" style="margin:0px 5px 0px 40px;" checked/>YES<input id="noId" type="radio" style="margin:0px 5px 0px 20px;" value="NO" name="radiobtn" style="margin-left:15px;"/>NO
	</div>
 
	<div id="ConstituencyDiv" class="selectDiv">		
		 Select Constituency<font class="requiredFont">*</font>
		<select id="constituencyId" onChange="getPublicationDates()" style="margin:7px 29px;">
		</select>
	</div>
 
   <div id="publicationDiv" class="selectDiv">		
		 Select Publication Date<font class="requiredFont">*</font>
		<select id="publicationId" style="margin:7px;"><option value="0">Select Publication Date</option>
		</select> <span style='display:none;float: right;margin:7px 38px;' id='ajaxLoad'><img src='./images/icons/search.gif' /></span>
	</div>

	<div id="BoothsDiv" class="selectDiv">		
		 Select Booth<font class="requiredFont">*</font>
		<select id="boothsId" style="margin:7px 72px;"><option value="0">Select Booth</option></select>
	</div>	
	
	<input type="button" class="btn" value="Create" style="margin:20px 112px" onClick="validateLeaderDetails();"></input>
</div>

<script>
 function getSubSurveyTypes(mainTypeId)
 {
	  $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});
	    $.ajax({
		  type:'POST',
		  url: 'getSubSurveyTypes.action',
		  dataType: 'json',
		  data: {mainTypeId:mainTypeId},			 
		  success: function(data){  

			  buildSubTypeDetails(data);
		  },
		  error:function() { 
		  }
	});
 }
 function buildSubTypeDetails(result)
 {
    $('#subTypeId').find('option').remove();
	$('#subTypeId').append('<option value="0">Select</option>');
	 $.each(result,function(index,value){
       $('#subTypeId').append('<option value="'+value.id+'">'+value.name+'</option>');
	 });
	 
 }
 var questionDtls={
	 surveyTypeId:'',
     surveySubTypeId:'',
     question:'',
     options:'',
     optnTypeId:'',
	 commentInd:''
 };

 function saveQuestionDetails()
 {
	 $('#optnErrMsg').html('');

	 var errorStr = '';

if($('#optionTypeId').val() != 3)
	 if($('#options').val().trim() == "")
		 errorStr +='<span>Atleast one option is required</span><br>';

	if($('#questionId').val().trim() == "")
		 errorStr +='<span>Question is required</span>';

	if(errorStr.length > 0)
	 {
		$('#errorDiv').html(errorStr);
		return;
	 }

	questionDtls.commentInd   = $('#commentId').is(':checked')?"true":"false";
	questionDtls.surveyTypeId = $('#mainTypeId').val();
	questionDtls.surveySubTypeId = $('#subTypeId').val();
	questionDtls.question = $('#questionId').val();
	questionDtls.options = $('#options').val();
	questionDtls.optnTypeId = $('#optionTypeId').val();

	  $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	  });

	    $.ajax({
		  type:'POST',
		  url: 'saveQuestionDetails.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(questionDtls)},			 
		  success: function(data){  

		  },
		  error:function() { 
		  }
	});
 }

 function createQuestions(){
    $('#createQstnDiv').dialog({
	 title:'Enter Question Details',
	 width:800
   });
 }

  function createSubQuestions(){
    $('#subQtnDivDialog').dialog({
	 title:'Enter Question Details',
	 width:500,
		 height:300
   });
 }
 
 function createMainQuestions(){
    $('#mainQtnDivDialog').dialog({
	 title:'Enter Question Details',
	 width:500
   });
 }
 
 
 function validateMainQtn()
 {
	var mainQtnDtls={mainQtnType:''};
	
	mainQtnDtls.mainQtnType = $('#mainQtnId').val().trim();
	if(mainQtnDtls.mainQtnType == "")
	 {
       $("#errorQtnDiv").html("Please enter the Question");
	   return;
	 }
    
	   $.ajax({
		  type:'POST',
		  url: 'saveMainQuestion.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(mainQtnDtls)},			 
		  success: function(data){  
              $('#statusDiv1').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
		  },
		  error:function() { 
		  }
	   });
 }
 
 
 function validateSubQtn()
 {	 
	var subQtnDtls={mainQtnTypeId:'',subQtnType:''};
 
	subQtnDtls.mainQtnTypeId = $('#mainTypeId1 option:selected').val();;
    subQtnDtls.subQtnType=$('#subQtnId').val().trim();
	
    if(subQtnDtls.subQtnType == ""){
       $("#errorSubQtnDiv").html("Please enter the SubQuestion");
	   return;
	}
	if(subQtnDtls.mainQtnTypeId == 0){
		$("#errorSubQtnDiv").html("Please enter the MainQuestion");
		return;
	}
   
	  $.ajax({
		  type:'POST',
		  url: 'saveSubQuestion.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(subQtnDtls)},			 
		  success: function(data){  
              $('#statusDiv2').html("<h5 style='text-align:center;color:green;'>Saved Successfully..</h5>");
		  },
		  error:function() { 
		  }
	  });
 }
var voterIdsArray=[];
function createNewLeader()
{  
	$("#errorLeaderDiv").html("");
    $('#lmobileId,#lVoterId,#luniqueId,#leaderNameId').val("");

	$('#leaderDivDialog').dialog({
	 title:'Enter Leader Details',
	 width:500
    });

	var districtDtls={
             electionTypeId:2,
			 stateId:1,
			 task:"getConstituencies"
	};
	
	$.ajax({
		  type:"POST",
		  url:"getAllConstituenciesInState.action",
		  data :{task:JSON.stringify(districtDtls)},
	}).done(function(result){
        $("#constituencyId option").remove();
        for(var i in result)
        {
		   $('#constituencyId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');         
        }
	});
 }
 
 function validateLeaderDetails()
 {
   var leaderDtls={leaderName:'',mobileNo:'',voterId:'',uniqueCode:'',constituencyId:'',boothId:'',isActive:'YES'};
   
   leaderDtls.mobileNo = $("#lmobileId").val().trim();
   leaderDtls.voterId  = $("#lVoterId").val().trim();
   leaderDtls.uniqueCode = $("#luniqueId").val().trim();
   leaderDtls.leaderName = $("#leaderNameId").val().trim();
   leaderDtls.isActive  = $("input[name='radiobtn']:checked").val();
   leaderDtls.constituencyId = $("#constituencyId option:selected").val();
   leaderDtls.boothId = $("#boothsId option:selected").val();
   var pattern1= /^\d{10}$/;

    if (leaderDtls.leaderName.length == 0){
      $("#errorLeaderDiv").html("Please enter the Name");
         return ;
    }

    if (leaderDtls.mobileNo.length == 0){
      $("#errorLeaderDiv").html("Please enter mobile number");
         return ;
    }

    if (leaderDtls.mobileNo.length > 0){
	  if( (leaderDtls.mobileNo.length !=10) || (!pattern1.test(leaderDtls.mobileNo))) {  
		$("#errorLeaderDiv").html("Please enter valid mobile number");
        return ;
		 } 
	}
	 if(leaderDtls.voterId.length == 0){
       $('#errorLeaderDiv').html("Please enter Voter Id<br>");
	   return;
   }
   
   if(leaderDtls.voterId.length > 0){
     for(var i in voterIdsArray){
        if(leaderDtls.voterId == voterIdsArray[i])
		  {
		    $('#errorLeaderDiv').html("Voter Id already exists<br>");
	         return;
		  }	
     }
   }
   if(leaderDtls.uniqueCode.length == 0){
       $('#errorLeaderDiv').html("Please enter Unique Code<br>");
	   return;
   }

   if(leaderDtls.constituencyId == 0)
   {
        $('#errorLeaderDiv').html("Please Select Constituency<br>");
	   return;
   }
   if($("#publicationId option:selected").val() ==0)
   {
        $('#errorLeaderDiv').html("Please Select Publication Date<br>");
	   return;
   }
   if(leaderDtls.boothId == 0)
   {
        $('#errorLeaderDiv').html("Please Select Booth<br>");
	   return;
   }
	if($("input[name='radiobtn']:checked").length == 0)
	{
		  $('#errorLeaderDiv').html("Please Select radio button<br>");
	   return;
	}
     $.ajax({
		  type:'POST',
		  url: 'saveLeaderDetails.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(leaderDtls)},	
          }).done(function(result){
		  if(result != null){
			  if(result.resultCode == 0){
				 
            $("#errorLeaderDiv").html('');
            $("#statusDiv3").css("visibility","visible");
		    $('#statusDiv3').text('Saved Successfully..').css("color","green");
		    setTimeout('$("#statusDiv3").css("visibility","hidden")',2500);
			$('#lmobileId,#lmobileId,#lVoterId,#luniqueId,#leaderNameId').val("");
			$('#constituencyId','#boothsId','#publicationId').val(0);			
			  }
		      else if(result.resultCode == 1){
			     $('#errorLeaderDiv').html("Name already exists,Please Enter another<br>");
	             return;
		      }
		  }
	   });
 }
  
 function validateVoterId()
 {
      var voterIdDtls={task:"getvoterIds"}
	   $.ajax({
	   type:"POST",
	   url:"getVoterIdsAction.action",
       dataType: 'json',
	   data:{task:JSON.stringify(voterIdDtls)},
        }).done(function(result){
        voterIdsArray=result;
      });
    
      var voterDtls={voterIdCardNo:$('#lVoterId').val()};
	   $.ajax({
	   type:"POST",
	   url:"getAllVoterDetailsAction.action",
       dataType: 'json',
	   data:{task:JSON.stringify(voterDtls)},
        }).done(function(result){
		 $("#boothsId option").remove();
	     $('#publicationId option').remove();
		 for(var i in result.publicationDatesList)
          {
			  $('#publicationId').append('<option value="'+result.publicationDatesList[i].id+'">'+result.publicationDatesList[i].name+'</option>');
		  }
		  for(var i in result.boothList)
          {
		     $('#boothsId').append('<option value='+result.boothList[i].id+'>Booth-'+result.boothList[i].name+'</option>');         
          }
          $('#leaderNameId').val(result.constyPublicationIds[0].voterName);
          $('#constituencyId').val(result.constyPublicationIds[0].constituencyId);  $('#publicationId').val(result.constyPublicationIds[0].publicationDateId);
          $('#boothsId').val(result.constyPublicationIds[0].boothId);
		  
        
	 });
 }

function searchNow1(){

    $("#searchDiv").html("");
	
    if($("#leaderNameId").val().trim() == "")
	{
      $('#errorLeaderDiv').html("Please Select Name to search<br>");
	}
	else if($("#constituencyId").val() == 0)
	{
      $('#errorLeaderDiv').html("Please Select Constituency<br>");
	}
	else if($("#publicationId").val() == 0)
	{
      $('#errorLeaderDiv').html("Please Select Publication Date<br>");
	}
	else {
       $('#searchDiv').dialog({
		title:"search", 
		width:800,
		modal:true,
        buttons: {
			"OK":function() {$(this).dialog('close');}
		}
	   });
		
		var voterCardNo=$("#lVoterId").val();
		var voterName=$("#leaderNameId").val();
		if(voterName==""){
			$("#errorSearchDiv").html("<span style='color:red'> Please Enter Name to Search </span>");
			return;
		}
		//$("#ajaxImg").css("display","inline-block");
		var publicationDateId=$("#publicationId").val();
		var constituency =  $("#constituencyId option:selected").val()
		var jsObj =
		{
			voterCardNo:voterCardNo,
			voterName:voterName,
			publicationDateId:publicationDateId,
			constituency:constituency,
			task:"getVotersMatched"
		};
		$.ajax({
		   type: "POST",
		   url: "getVotersForSearchingAction.action",		
		   data: {task:JSON.stringify(jsObj)},
		}).done(function(results) {
		   buildTableForSearchedVoters(results);
	    });
	}
}
function getPublicationDates()
{
	var constnDtls={
             selected:$('#constituencyId').val(),
			 task:"getPublicationDate"
	};
	$.ajax({
          type:'POST',
          url: 'voterAnalysisAjaxAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(constnDtls)},

          success: function(result){ 
			$('#publicationId').find('option').remove();
			  $.each(result,function(index,value){
				  $('#publicationId').append('<option value="'+value.id+'">'+value.name+'</option>');
			  });			  
         },
          error:function() { 
           console.log('error', arguments);
         }
    });
}

function buildTableForSearchedVoters(results){
		
		if(results!=null && results.length>0)
		{
		var str="";
		str+="<table class='table table-bordered' id='searchVotersTableId'>";
			str+="<thead>";
				str+="<tr>";
				//str+="<th>Select</th>";
				str+="<th>Name</th><th>Voter CardId</th><th>Serial No</th><th>Booth No</th></tr></thead><tbody>";
				for(var i in results){
					str+="<tr>";
						//str+="<td><input type='checkbox' id= 'check"+i+"'   name='searchedVoters' class= 'checkedCls' value="+results[i].voterId+" onclick='getChecked(this.id)'></input></td>";
						str+="<td>"+results[i].name+"</td>";
						str+="<td>"+results[i].voterIdCardNo+"</td>";
						str+="<td>"+results[i].toSno+"</td>";
						str+="<td>"+results[i].partNo+"</td>";						
					str+="</tr>";
				}
			str+="</tbody>";
		str+="</table>";
		temp = results;
		str+="<br><span onclick=addVoter() class='btn btn-info'>Add Voter</span>";
		$("#searchDiv").html(str);
		$('#searchVotersTableId').dataTable();
		}
		else
		{
			$("#searchDiv").html("<span style='color:red'>No Data Available For Given Search Details");
		}
	}  

function addVoter(){	
       
	$('input[name="searchedVoters"]:checked').each(function() {
		for(var i in temp){
				if(temp[i].voterId==this.value){
                  $("#lVoterId").attr("value",temp[i].voterIdCardNo);
				  $("#leaderNameId").attr("value",temp[i].name);
				  $('#boothsId').append('<option value="'+temp[i].boothId+'">'+temp[i].partNo+'</option>');
				}
		}$('#searchDiv').dialog( "close" );
	});
}
function getChecked(id){
             
		$(".checkedCls").removeAttr("checked");
	    $('#'+id).attr('checked',true);
}

</script>
</body>
</html>