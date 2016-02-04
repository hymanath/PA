<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rtc Union Registration</title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
		<script src="js/icheck/icheck.js"></script>
	 
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
		<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>
		
		<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
		<link rel="stylesheet" href="js/flipclock/flipclock.css">		
		<script src="js/flipclock/flipclock.js"></script>
  <style>
	<!-- YUI Dependency files (End) -->
	
	
    <script>$('#yourElement').addClass('animated fadeInDown');</script>
    <script>$('#fadeInLeft').addClass('animated fadeInLeft');</script>
    <script>$('#fadeInRight').addClass('animated fadeInRight');</script>
    <script>$('#fadeInUp').addClass('animated fadeInUp');</script>
    <script>$('#fadeInUp1').addClass('animated fadeInUp');</script>


	<style>
	body{margin-bottom:120px;}
	.footerFixedStrip{
		bottom:0px; 
		left:0px; 
		background:#ff3333; 
		width:100%;
		height:115px;
		padding:20px 10px 5px 10px; 
		display:none;
	}
	.flip{transform:none !important;}
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db !important;}
	 
	 .datePickerCls{
			 cursor: text !important;
			}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	.levelCls{width:160px;float:left;margin:5px;}
	.levelClsDt{width:140px;float:left;}
	select.levelCls{border-radius:4px;}
	.mandatory
	{
	color:#FF0000;
	}
	#wrapper{
	width: 470px;
	margin: 10px auto 30px;
	position: relative;
}
  .detailsCls{
    cursor: pointer;
  }
  .fadeInUpClass{
	display:none;
  }
  .icon-remove{
    cursor:pointer;
  }
	</style>
	
		
	<script>
	var remainingTime = '${countDownTime}';

	$(document).ready(function(){
			$("#toggleButtonId").click(function(){
				$(".fadeInUpClass").toggle();
			});
			
			var clock;
				    
			if(remainingTime.trim().length >0)
			{
				clock = $('.clock').FlipClock({
		        clockFace: 'DailyCounter',
		        autoStart: false,
		        callbacks: {
		        	stop: function() {
							$('.message').html('Registrations Closed By 4:00PM!');
							$('.clock').hide();
						}
					}
				});
			
				$('.footerFixedStrip').hide();
				clock.setTime(parseInt(remainingTime));
				clock.setCountdown(true);
				clock.start();			
			}
			
		});
		
		
		var constituencyArray = new Array();
			<c:forEach var="constituency" items="${constituencyesList}">
				var constituencys ={
				id:"${constituency.id}",
				name:"${constituency.name}"
				}
				constituencyArray.push(constituencys);
			</c:forEach>
		var electionArray = new Array();	
			<c:forEach var="election" items="${eletionTypesList}">
				var elections ={
				id:"${election.id}",
				name:"${election.name}"
				}
				electionArray.push(elections);
			</c:forEach>
/*
		var casteArr = new Array();
		var casteDetailsArr = new Array();
		<c:forEach var="caste" items="${voterInfoVOList[0].genericVOList}">
				var obj = {
					id :  '${caste.id}',
					name :  '${caste.name}'
				}
				casteDetailsArr.push( '${caste.name}');
				casteArr.push(obj);
		</c:forEach>

		
		var occupationArr = new Array();
		var occupationDetailsArr = new Array();
		<c:forEach var="occupation" items="${selectOptionVOList}">
				var obj = {
					id :  '${occupation.id}',
					name :  '${occupation.name}'
				}
				occupationDetailsArr.push( '${occupation.name}');
				occupationArr.push(obj);
		</c:forEach>
	*/
	
	
		var participationCount = 0;
		<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
			<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
				participationCount = participationCount + 1;
			</c:forEach>
		</s:if>
  
		var isRolesCount = 0 ;
		<s:if test="%{voterInfoVOList[0].cadreRolesList != null && voterInfoVOList[0].cadreRolesList.size() > 0}">
			<c:forEach var="role" items="${voterInfoVOList[0].cadreRolesList}" varStatus="indexValue">
				isRolesCount = isRolesCount+1;
			</c:forEach>
		</s:if>	
	</script>
	
   	<script>
	
	var constituencyId = '${constiteucnyId}';
	var panchayatId    = '${constiteucnyId}';
	var boothId 	   = '${boothId}';
	var srchType	   = '${searchType}';
	var candiId		   = '${candidateId}';
	var newCamPhotoTaken = false;
	var newPhotoUploaded = false;
	var alreadyImgPresent = false;
	 <s:if test="voterInfoVOList[0].image != null">
		alreadyImgPresent = true;
	 </s:if>
	 </script>
	 <script>
	$(document).ready(function(){
	    $('.datePickerCls').datepicker({
		dateFormat: 'yy-mm-dd',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
	  
	  $('.dateOfBirthDtCls').datepicker({
		dateFormat: 'yy-mm-dd',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
		
		/*
		$( "#casteIdValue" ).autocomplete({ 
			source:casteDetailsArr,
			select: function (event, ui) {
				$('#casteIdValue').val(ui.item.value);	
				for(var i in casteArr)
				{
					if(casteArr[i].name == ui.item.value)
					{
						$('#casteId').val(casteArr[i].id);
						break;
					}
				}
				return false;
			}
		});
		
		$( "#occupationValue" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$('#occupationValue').val(ui.item.value);	
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						$('#occupationId').val(occupationArr[i].id);
						break;
					}
				}
				return false;
			}
			
		});
		
		$( ".familyOccupationsCls" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$(this ).val(ui.item.value);
				
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						var fieldId = $(this).attr('id');
						$('#'+fieldId+'0').val(occupationArr[i].id);
						break;
					}
				}
					return false;
			}
		});
		
		*/
		
		prepopulateOptions();
		//prepopulateElctionOptions();
		/*$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });*/
		 		
		var nomineeGender = '${voterInfoVOList[0].nomineeGender}';
		var relativeId = '${voterInfoVOList[0].voterRelationId}';
		
		if(nomineeGender.trim().length>0)
			$('#nomineeGenderId').val(parseInt(nomineeGender));
		if(relativeId.trim().length>0)
			$('#voterRelationId').val(relativeId);
				
	
	});

	var rolesSize = 1;
	var isRolesSet = true;
	
	function enableSearchByName(){
		//$('#preEnrollNoValue').removeAttr('readOnly');
		$('#searchNameId,#searchVoterCardId,#searchHNoId').val("")
		$('#searchDetailsDiv').html("");
		$('#tableElement').hide();
		var errDivId = "#errorDiv1";
		$('#searchDetailsDiv1').html('');
		$('#tableElement1').hide();
		$('#preEnrollNo').val('');
		$(errDivId).html('');
		$( "#myModal1" ).dialog({title: "Search For Enrollment Number", width: "auto",
            height: "auto", position: { my: 'right', at: 'top+10' }});
	}
	function enableSearchByfName(){
		$('#searchNameIdFmly,#searchVoterCardIdFmly,#searchHNoIdFmly').val("")
		$('#searchDetailsDivFmly').html("");
		$('#tableElementFmly').hide();
		$( "#myModal2" ).dialog({title: "Search For Family Member Voter Card No", width: "auto",
            height: "auto", position: { my: 'right', at: 'top+10' }});
	}
	function enableLookupName(){
		//$('#cardNumber').removeAttr('readOnly');
		$('#searchNameId,#searchVoterCardId,#searchHNoId').val("")
		$('#searchDetailsDiv').html("");
		$('#tableElement').hide();
		$( "#myModal" ).dialog({title: "Search For Voter Card No", width: "auto",
            height: "auto", position: { my: 'right', at: 'top+10' }});
	}
	
	function createNewForm(){
		if(isRolesCount >= 1 &&  isRolesSet)
		{
			rolesSize = isRolesCount+1;
			isRolesSet = false;
		}

		var str = '';
		str += '<div class="rolesList'+rolesSize+'" style="margin-top:0px;float:left;margin-left:-5px;">';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1 " >';
		//str += '<h5 class="text-align1">Select Level </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreCommitteeLevelId" id="CadreCommitteeLevelsId'+rolesSize+'" onchange="getCadreCommitteDetails(this.value,\'CadreRolesId'+rolesSize+'\')">';
			if(cadreCmmittLvlArr != null && cadreCmmittLvlArr.length>0)
			{
				for(var i in cadreCmmittLvlArr)
				{
					str += '<option value = "'+cadreCmmittLvlArr[i].id+'">'+cadreCmmittLvlArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1" >';
		//str += '<h5 class="text-align1">Party Designation </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreCommitteeId" id="CadreRolesId'+rolesSize+'" onchange="getCadreCommitteRoles(this.value,\'CadreCommitteeId'+rolesSize+'\',\'CadreCommitteeLevelsId'+rolesSize+'\');">';
		str += '<option value = "0"> Select Committee </option>';
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" text-align1" >';
		//str += '<h5 class="text-align1">Party Designation </h5>';
		str += '<select class="form-control border-radius-0 levelCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].cadreRoleId" id="CadreCommitteeId'+rolesSize+'">';
		str += '<option value = "0"> Select Role </option>';
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">From Date</h5>';		
		str += '<div class="text-align2">';
		str += '<input type="text" id="fromDateId'+rolesSize+'" key="'+rolesSize+'" style="width:120px;float:left;margin-left:15px;margin-top:5px;" class="form-control span2 border-radius-0 border-right-0 datePickerCls fromDateCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].fromDateStr"  readOnly="true" id="'+rolesSize+'" placeholder="From Date"></input> ';
		str +='<br><span id="fromDateErr'+rolesSize+'" style="color:red;font-size:12px;"></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="levelCls ">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">To Date</h5>';		
		str += '<div class="text-align2">';
		str += '<input type="text"  id="toDateId'+rolesSize+'"  style="width:120px;float:left;margin-left:15px;margin-top:5px;" class="form-control span2  border-radius-0 border-right-0 datePickerCls toDateCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].toDateStr"  readOnly="true" id="'+rolesSize+'" placeholder="To Date"></input>';
		str +='<br><span id="toDateErr'+rolesSize+'" style="color:red;font-size:12px;"></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		/*str += '<div class="span2">';
		str += '<a onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" class="btn btn-success"></a>';
		str += '<div>';*/
		str += '<div class="" style="width:60px;float:left;margin-top:-10px;">';
		str += '<div class=" " >';
		str += '<div class="input-prepend text-align2 ">';	
		//str += '<a class="icon-minus-sign" style="float:left;margin-top:30px;" onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" title="Remove Details"></a>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		
		rolesSize++;
		//alert(rolesSize);
		
		$('#rollesDiv').append(str);
		//$('.datePickerCls').datepicker({dateFormat: 'dd-mm-yy',minDate: '01-01-1900',maxDate: new Date()});
		
		 $('.datePickerCls').datepicker({
		dateFormat: 'yy-mm-dd',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
	  
	}
	
	function deleteRollesForm(id)
	{
		if(confirm('Are you sure want to delete it? '))
		{
			$('.'+id).remove();
		}		
	}
	
	function submitCadreForm()
	{	
	    $("#submitCadreFormBtnReqId").removeAttr("onclick");
	    $("#submitCadreFormBtnReqId").attr("disabled","disabled");
		if(validateDetails())
		{
				if(!isNumber()){
				
					$('html,body').animate({
					scrollTop:  $("#casteIdValue").offset().top 
					});
					return false;
				}
				$("#submitCadreFormBtnReqId").removeAttr("onclick");
				$("#submitCadreFormBtnReqId").attr("disabled","disabled");
				var uploadHandler = {
						upload: function(o) {
							uploadResult = o.responseText;
							//console.log(uploadResult);
							showUploadStatus(uploadResult);	
						}
					};

				YAHOO.util.Connect.setForm('uploadCadreForm',true);
				YAHOO.util.Connect.asyncRequest('POST','tdpCadreSaveRegistrationAction.action',uploadHandler);
		}		
	}
	
	function validateDetails()
	{
		var isSuccess = false;
		var isErrorStr = '';
		
		var casteId = $('#casteId').val();
		var mobileNumber = $('#mobileNumberId').val();
		var cadreAge = $('#cadreAgeId').val();
		var cadreCardNumber = $('#cardNumber').val();
		var dateOfbirth = $('#dateOfbirthId').val();
		var cadreName = $('#cadreNameId').val();
		
		var NAadharNo = $('#nomineAadharId').val();
		var Nname = $('#nomineNameId').val();
		var Ngender = $('#nomineeGenderId').val();
		var NAge = $('#nomineeAgeId').val();
		var Nrelation = $('#voterRelationId').val();		
		var emailId = $.trim($("#emailId").val());
		var emailreg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		
		$('#imageErr').html('');
		$('#familyVtrCrdIdErr').html("");
		$('#NaadharErr,#NnameErr,#NgenderErr,#NageErr,#dobErr,#NrelationErr,#gendReqErr').html('');
		$('#casteErr,#mobileErr,#ageErr,#cardErr,#dobErr,#nameErr').html('');
		 if(!$("#maleGenderRId").is(':checked') && !$("#femaleGenderRId").is(':checked')){
			 $('#gendReqErr').html('Please select gender');
		 }
			$('#imageErr').html('');
			//To check if user upload any file
			if (!newCamPhotoTaken && !newPhotoUploaded && !alreadyImgPresent && !$("#voterActualImgId").is(':checked') && !$("#cadreActualImgId").is(':checked')) 
			{
				$('#imageErr').html('Please upload an image');
				isErrorStr = " error";
			}

		if(validateName('nameErr','cadreNameId',1)){
			isErrorStr = " error";	
		}
		if(validateName('ageErr','cadreAgeId',0)){
			isErrorStr = " error";	
		}
		if(validateName('garErr','gardianNameId',1)){
			isErrorStr = " error";	
		}
		if(validateName('relErr','relationTypessId',1)){
			isErrorStr = " error";	
		}
		if(isValidName('name')){
			isErrorStr = " error";	
		}
		if(isValidName('number')){
			isErrorStr = " error";	
		}
		if(casteId == 0)
		{
			isErrorStr = " error";
			$('#casteErr').html(' Caste is required.');
		}
		if(mobileNumber.trim().length == 0)
		{
			isErrorStr = " error";
			$('#mobileErr').html(' Mobile No is required.');
		}
		if(emailId != null && emailId.trim().length > 0 && emailreg.test(emailId) == false)
		{
			isErrorStr = " error";
			$("#emailErr").html('Invalid Email Id.');
		}
		if(cadreAge != null && cadreAge.trim().length == 0)
		{
			isErrorStr = " error";
			$('#ageErr').html(' Candidate Age is required.');
		}		
		if(cadreCardNumber != null && cadreCardNumber.trim().length == 0)
		{
			var cbck = $('#relativeTypeChecked').is(':checked'); 
			
			if(!cbck){
				isErrorStr = " error";
				$('#cardErr').html(' Voter Card No is required.');
			}else{
				var familyVoterId = $('#familyVtrCrdId').val();
				if(familyVoterId == null || $.trim(familyVoterId).length == 0){
					isErrorStr = " error";
					$('#familyVtrCrdIdErr').html('Family  Voter CardId Required');
				}
			}
			
		}
		/*if(dateOfbirth != null && dateOfbirth.trim().length == 0)
		{
			isErrorStr = " error";
			$('#dobErr').html(' Date of Birth is required.');
		}*/
		if(cadreName != null && cadreName.trim().length == 0)
		{
			isErrorStr = " error";
			$('#nameErr').html(' Candidate Name is required.');
		}		
		
		/*if(NAadharNo != null && NAadharNo.trim().length == 0)
		{
			isErrorStr = " error";
			$('#NaadharErr').html(' Aadhar No required.');
		}*/
		if(Nname != null && Nname.trim().length == 0)
		{
			isErrorStr = " error";
			$('#NnameErr').html(' Nominee Name required.');
		}
		
		if(Nname != null && Nname.trim().length>0 && !(/^[a-zA-Z ]+$/.test(Nname)))
		{
				$('#NnameErr').html('Candidate Name allows only alphabets.');
			isErrorStr = " error";
		}
		if(Ngender == 0)
		{
			isErrorStr = " error";
			$('#NgenderErr').html(' Nominee Gender required.');
		}
		if(NAge != null && NAge.trim().length == 0)
		{
			isErrorStr = " error";
			$('#NageErr').html(' Nominee Age required.');
		}
		if(NAge != null && NAge.trim().length > 0){
		  if(isNaN(NAge.trim())){
		    isErrorStr = " error";
			$('#NageErr').html(' Nominee Age must be Number.');
		  }
		}
		if( Nrelation == 0)
		{
			isErrorStr = " error";
			$('#NrelationErr').html(' Nominee Relation required.');
		}
		
		$('.fromDateCls').each(function(){
		
			var keyId = $(this).attr('key');
			$('#fromDateErr'+keyId+'').html('')	;		
			$('#toDateErr'+keyId+'').html('');
			
			var startDate = $('#fromDateId'+keyId+'').val();
			var endDate = $('#toDateId'+keyId+'').val();	
			
			if((startDate != null && startDate.trim().length >0) && (endDate != null && endDate.trim().length >0))
			{
				var arrr = startDate.split("-");
				    var fromyear=arrr[0];
					var frommonth=arrr[1];
					var fromDat=arrr[2];
			   var arr = endDate.split("-");
					var toyear=arr[0];
					var tomonth=arr[1];
					var toDat=arr[2];
					
					if(fromyear>toyear){
						$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
						  isErrorStr = " error";
					}
					 if(frommonth>tomonth){
						   if(fromyear == toyear){
							$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
						    isErrorStr = " error";
						}
						
					}
					
					if(fromDat>toDat){	
						if(frommonth == tomonth && fromyear == toyear){			
							$('#fromDateErr'+keyId+'').html('<font style="color:red;">From Date should not greater than To Date </font>');
						    isErrorStr = " error";		
						   }
					}
			}
			
		});
				
		
		if(!isNumber()){//iferror return false
			isErrorStr = " error";	
		} 

		/*if(!isAadharNumber('nomineAadharId','Aadhar No ')){
			isErrorStr = " error";	
		}*///iferror return false
		if(!isAadharNumber('candAdrId','Aadhar No ')){
			isErrorStr = " error";	
		}//iferror return false
		
		$('.famAgeErrCls').each(function(){
			var key = $(this).attr('key');
			$('#famAgeErr'+key).html('');
			if($.trim($(this).val()).length>0){	 	 
				if (isNaN($.trim($(this).val()))) 
				{
					isErrorStr = " error";	
					$('#famAgeErr'+key).html('must be number.');			
				}
			}else{
				$(this).val("");
			}
		});
		if(isErrorStr.trim().length >0)
		{
		    $("#submitCadreFormBtnReqId").removeAttr("disabled");
		    $("#submitCadreFormBtnReqId").attr("onclick","submitCadreForm()");
			$('html,body').animate({
			scrollTop:  $("#yourElement").offset().top 
			});
			
			isSuccess = false;
		}
		else
		{
			isSuccess = true; 
		}

	return isSuccess;	
	}
	
	function showUploadStatus(myResult)
	{
		$('#mainDiv').html('');
		var result = (String)(myResult);
		var errorDivEle = document.getElementById('errorMsgDiv');
		var str = '';
		var resultArr = result.split(',');
		if(result.search('SUCCESS') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align"> Successfully Registration Completed </h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Membership No : '+resultArr[2]+' </p>';
			str+= '<p class="text-align">Your Enrollment No : '+resultArr[1]+' </p>';			
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		else if(result.search('FAILURE') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">Error raised while cadre registration</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		/*else
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align">Data Not Found For your Voter Id</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
			
		}*/
		$('#statusDiv').html(str);
	}
	var cadreLevelArr = [];
	var partyDesignationArr = [];
	
	function openSearchForm()
	{
		window.location.assign('tdpCadreSearchAction.action')
	}
	/* function prepopulateOptions()
	{
		var jsObj = 
			   {				
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getOptionDetailsForCadreAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#cadreLevelId').append('<option value="0"> Select Level </option>');
							$('#partyDesignationId').append('<option value="0"> Select Designation </option>');
						if(result != null && result.length >0)
						{
							if(result[0].selectOptionsList != null && result[0].selectOptionsList.length >0)
							{
								for(var i in result[0].selectOptionsList)
								{
									$('#cadreLevelId').append('<option value="'+result[0].selectOptionsList[i].id+'">'+result[0].selectOptionsList[i].name+'</option>');
									var cadreObj = 
									{
										id		:	result[0].selectOptionsList[i].id,
										name	:	result[0].selectOptionsList[i].name								
									}
									
									cadreLevelArr.push(cadreObj);
									
								}
							
							}
							
							if(result[0].selectOptionsList1 != null && result[0].selectOptionsList1.length >0)
							{
							
								for(var i in result[0].selectOptionsList1)
								{
									$('#partyDesignationId').append('<option value="'+result[0].selectOptionsList1[i].id+'">'+result[0].selectOptionsList1[i].name+'</option>');
									
									var cadreObj = 
									{
										id		:	result[0].selectOptionsList1[i].id,
										name	:	result[0].selectOptionsList1[i].name								
									}
									
									partyDesignationArr.push(cadreObj);
									
								}							
							}	
						}
				});
	} */
	
	function prepopulateOptions(){
		var jsObj ={}				   
			$.ajax({
				type : "POST",
				url : "getCadreLevelsForCadreSearchAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result.length>0){
					buildSelectBoxes(result);
				}
			});
	}
	
	var cadreCmmittArr = [];
	var cadreCmmittLvlArr = [];
	var cadreRolesArr = [];
	
	function buildSelectBoxes(results){
		
		for(var i in results){
		
			if(results[i].name=="CadreCommitteeList"){
				var str = "";					
					for(var j in results[i].selectOptionsList){						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}							
						cadreCmmittArr.push(cadreCmmtObj);
					}
			}
			if(results[i].name=="CadreCommitteeLevelsList"){
				var str = "";
					for(var j in results[i].selectOptionsList){						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreCmmittLvlArr.push(cadreCmmtObj);
					}
			}
			if(results[i].name=="CadreRolesList"){
				var str = "";
					for(var j in results[i].selectOptionsList)
					{						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreRolesArr.push(cadreCmmtObj);
					}
			}
			
		}
	}
	
	function prepopulateElctionOptions()
	{
		var jsObj = 
			   {				
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getElectionOptionDetailsForCadreAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#electionTypeId').append('<option value="0"> Select Election </option>');
							$('#electionYearId').append('<option value="0"> Select Year </option>');
						if(result != null && result.length >0)
						{
							if(result[0].selectOptionsList != null && result[0].selectOptionsList.length >0)
							{
								for(var i in result[0].selectOptionsList)
								{
									$('#electionTypeId').append('<option value="'+result[0].selectOptionsList[i].id+'">'+result[0].selectOptionsList[i].name+'</option>');		
								}
							
							}
							
							if(result[0].selectOptionsList1 != null && result[0].selectOptionsList1.length >0)
							{
								for(var i in result[0].selectOptionsList1)
								{
									$('#electionYearId').append('<option value="'+result[0].selectOptionsList1[i].id+'">'+result[0].selectOptionsList1[i].name+'</option>');														
								}
							
							}
							
						}
				});
	}
	
	function changeImg()
	{
	$("#uploadImg").html('<img style="width: 140px; height: 120px;" id="actuploadImg" src="images/mahaNadu/user image.jpg">');
		$("#base64Image").val("");
		newCamPhotoTaken = false;
		var photoElmt = document.getElementById("uploadFileId");
		var FileUploadPath = photoElmt.value;
		$('#imageErr').html('');
		//To check if user upload any file
        if (FileUploadPath == '') 
		{
			$('#imageErr').html('Please upload an image');
		}
		else 
		{
            var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
			//The file uploaded is an image
			if (Extension == "gif" || Extension == "png" || Extension == "bmp"
								|| Extension == "jpeg" || Extension == "jpg") 
			{
                    var reader = new FileReader();
					var file = photoElmt.files[0];
					var reader = new FileReader();
					reader.onloadend = handleReaderLoadEnd;
					reader.readAsDataURL(file); 
                    newPhotoUploaded = true;					
            } 
			//The file upload is NOT an image
			else 
			{       clearExistingImg('uploadImg');
			        newPhotoUploaded = false;
					$('#imageErr').html('Image Formate Must Be .GIF, .PNG, .JPG, .JPEG and .BMP Only');
			}
        }
	}
	function handleReaderLoadEnd(evt)
	{
		var img = document.getElementById("actuploadImg");
		img.src = evt.target.result;
		evt=null;
	} 
	var eletionCont = 1;
	var isRolesCountSet = true;
	function createNewFormsForElections()
	{
		if(isRolesCount >=1 && isRolesCountSet)
		{
			isRolesCountSet = false;
			eletionCont = isRolesCount;
		}
		var str = '';
		str += '<div class="row-fluid electionsList'+eletionCont+'">';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Election Type</h5>';
		str += '<select id="electionTypeId'+eletionCont+'" style="margin-left: 12px;" onChange="getElectionYears(this.value,\'electionYears'+eletionCont+'\')" >';
		if(electionArray != null && electionArray.length>0)
		{
			str += '<option value = "0">Select Election Year</option>';
			for(var i in electionArray)
			{
				str += '<option value = "'+electionArray[i].id+'">'+electionArray[i].name+'</option>';
			}
		}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Year</h5>';
		str += '<select  id="electionYears'+eletionCont+'" style="width: 150px;" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].electionTypeId"  onchange="getConstiteuncyListForElection(this.value,\'constituencyList'+eletionCont+'\')" >';	
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Constituency</h5>';
		str += '<select class="" id="constituencyList'+eletionCont+'" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].constituencyId" style="margin-left: 10px;width:220px" onchange="getCandidateDetailsForElection(this.value,\'candidatesList'+eletionCont+'\',\'electionYears'+eletionCont+'\',\'electionTypeId'+eletionCont+'\');">';
		str += '<option value = "0"> Select Constituency </option>';
		/*	if(constituencyArray != null && constituencyArray.length>0)
			{
				for(var i in constituencyArray)
				{
					str += '<option value = "'+constituencyArray[i].id+'">'+constituencyArray[i].name+'</option>';
				}
			}
		*/
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
				str += '<div class=" " >';
				str += '<select   id="candidatesList'+eletionCont+'"  style="margin-left: 10px;width:150px;" onchange="getCandidateDetailsById(\'constituencyList'+eletionCont+'\',\'electionYearId'+eletionCont+'\',this.value); " name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].candidateId">';
				str += '<option value="0"> Select Candidate</option>	';							
				str += '</select>';							
				str += '</div>';
			str += '</div>';

		//str += '<h5 class="text-align1">Add More</h5>';
		str += '<a class="icon-minus-sign" style="float:right;margin-top:10px;margin-right:35px;" onClick="deleteRollesForm(\'electionsList'+eletionCont+'\')" title="Remove Details"></a>';

		str += '</div>';
		
		$('#electionsDiv').append(str);
		eletionCont ++;
	}
	
	function getElectionYears(id,divId)
	{
		var jsObj = 
			   {			
				  eletionTypeId : id,
				  task          : "getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getElectionYearsByElectionType.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						$('#'+divId+'').html('');
						$('#'+divId+'').append('<option value="0"> Select Election </option>');
						if(result != null && result.length >0)
						{
							for(var i in result)
							{
								$('#'+divId+'').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');		
							}
						}
				});
	}

	var familyArr =new Array();
	
	<c:forEach var="votersInfo" items="${voterInfoVOList[0].voterInfoVOList}" >
		familyArr.push(${votersInfo.voterId});
	</c:forEach>
		
	var voterCount = familyArr.length - 1;

	function addMoreVoters()
	{
	if(voterCount >= 0 )
		voterCount = voterCount+1;
	else
		voterCount = 1;
		
	var str ='';

	str +=' <tr class="voterDev'+voterCount+'">';  
	str +='<td style="width:25px;text-align:center;">  ';
	//str +='<input type="checkbox" id="checkBox'+voterCount+'"  name="" class="nomineeCls" onclick="nomineeUpdate(\'checkBox'+voterCount+'\','+voterCount+')" style="margin-top: -10px;" title="Click here to use this member as nominee."></td>';
	str +='<a class="icon-remove nomineeCls" id="checkBox'+voterCount+'" style="margin-top:-10px;"  onclick="nomineeUpdate(\'checkBox'+voterCount+'\','+voterCount+')"  title="Click here to use this member as nominee."></a> </td>';
	str +=' <td style="width:100px;">  <input id="countVar" type="hidden" value="'+voterCount+'">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Name " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterName" id="voterName'+voterCount+'" ></input> ';
	str +=' <input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterId" id="voterId'+voterCount+'"></input> ';
	str +=' </td>';
	str +=' <td style="width:100px;"> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Id " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterCadreNO" style="width:121px;"  id="voterCard'+voterCount+'"></input>';
	str +=' </td>';
	str +=' <td style="width:80px;"> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2 famAgeErrCls" placeholder=" Age " style="width:55px;"   name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].age"  key="'+voterCount+'" id="voterAge'+voterCount+'"></input> <br/> <span style="color:red;font-size:12px;" id="famAgeErr'+voterCount+'"></span>';
	str +=' </td>';
	str +=' <td style="width:82px;">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender"  style="width:53px;"  name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].gender"  id="voterGender'+voterCount+'"> </input>';
	str +=' </td>';
	str +=' <td style="width:100px;"> ';

	str +=' <select name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].educationId" style="width:160px;">';
	str +=' <option value="0">Select Education</option>';
	
	<c:forEach var="educationList" items="${genericVOList}" >
		str +=' <option value="${educationList.id}">${educationList.name}</option>';
	</c:forEach>
	str +=' </select>';

	str +=' </td>';
	str +=' <td style="width:100px;"> ';

	str +=' <select id="familyOccupation'+voterCount+'0" name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].occupationId" style="width:180px;margin-left: 15px;" >';
		str +=' <option value="0">Select Occupation</option>';	
		<c:forEach var="occupation" items="${selectOptionVOList}">
			str +=' <option value="${occupation.id}">${occupation.name}</option>';
		</c:forEach>
		
	str +=' </select>';
	
	/*
	str +='<input type="text" id="familyOccupation'+voterCount+'" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Enter Occupation "  style=//"width:151px;"  value=""> </input>';												 
	str +='<input type="hidden"  id="familyOccupation'+voterCount+'0" name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>';
	*/
	
	str +=' </td>';
	str +='<td style="width:15px;"> ';
	str +='<input type="checkbox"   class="removeCls"   style="margin-top: -10px;"  id="voterDev'+voterCount+'">';
		/*str +='<a class="icon-minus-sign" style="float:right;" onClick="deleteRollesForm(\'voterDev'+voterCount+'\');" title="Remove Details"></a>';*/
	str +='</td>';
	str +=' </tr>';

	
	$('#addVotersDiv').append(str);
	/*
	$( ".familyOccupationsCls" ).autocomplete({ 
		source:occupationDetailsArr,
		select: function (event, ui) {
				$(this ).val(ui.item.value);
				
				for(var i in occupationArr)
				{
					if(occupationArr[i].name == ui.item.value)
					{
						var fieldId = $(this).attr('id');
						$('#'+fieldId+'0').val(occupationArr[i].id);
						break;
					}
				}
					return false;
			}
		});
	*/
	
	}
	
	
	function isNumber()
	{
		var numberFlag = true;
		var mobileNumber = $('#mobileNumberId').val().trim();
		$('#mobileErr').html('');
		if (mobileNumber.length == 0) 
		{
			$('#mobileErr').html('Please Enter Mobile No');		
			numberFlag= false;
		}		 
		else if (isNaN(mobileNumber) || mobileNumber.length != 10) 
		{
			$('#mobileErr').html('Invalid Mobile No.');			
			numberFlag = false;
		}
	
			return numberFlag;
	}
	
	function isAadharNumber(fieldId,AadharNo)
	{
		var numberFlag = true;
		var errDiv='#NaadharErr';
		if(fieldId == "candAdrId"){
			errDiv='#errcandAdrId';
		}
		var mobileNumber = $('#'+fieldId+'').val().trim();
		
		$(errDiv).html('');
		
		
		
		if(mobileNumber.length == 0) 
		{
			if(!(fieldId == "candAdrId")){
				$(errDiv).html(''+AadharNo+' Required.');		
				numberFlag= false;
			}
		}		 
		else if (isNaN(mobileNumber)) 
		{
			$(errDiv).html('Invalid '+AadharNo+'.');			
			numberFlag = false;
		}
		/*else if(mobileNumber.length < 12) 
		{
			$('#NaadharErr').html(''+AadharNo+' should be 12 digits.');		
			numberFlag= false;
		}*/
			return numberFlag;
	}
	
	function getConstiteuncyListForElection(eletionId,constiListId)
	{
			$('#loadingImg').show();
			
			$('#'+constiListId+'').find('option').remove();
			$('#'+constiListId+'').append('<option value="0"> Select Constituency </option>');
			
			var jsObj = 
			   {			
				  electionId : eletionId,
				  constituencyId : constituencyId,
				  task       : "getConstiteuncyListForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstiteuncyListForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){

					$('#loadingImg').hide();
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+constiListId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}					
				});
	}
	
	function getCandidateDetailsForElection(constituencyId,candidateId,electionId,electionTypeId)
	{
		var electionValue = $('#'+electionId+'').val();
		var electionTypeId = $('#'+electionTypeId+'').val();
		$('#'+candidateId+'').find('option').remove();
		$('#'+candidateId+'').append('<option value="0"> Select Candidate </option>');
		
			var jsObj = 
			   {	
				  electionTypeId : electionTypeId,
				  electionId : electionValue,
				  constituencyId : constituencyId,
				  task         : "getCandidateDetailsForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getCandidateDetailsForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+candidateId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}		
					
				});
	}
	
	</script>

</head>
  <body class="bgc">
		<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
	<div class="container m_top10 " id="yourElement">
		<div class="span12 show-grid" style="position: relative;">
			<h3 class="text-align">CADRE REGISTRATION</h3>
		</div>
	</div>
	<div id="mainDiv">
		<div>
			<form action="tdpCadreSaveRegistrationAction.action" method="POST" enctype="multipart/form-data" name="uploadCadreForm">	
		<div class="container m_top10"style="position: relative;">
		
			<div class="span12" >
				<div class="row-fluid">
					<div class="span6   show-grid"  id="fadeInLeft" style="min-height: 830px;">
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${voterInfoVOList[0].voterId}" > 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${constiteucnyId}" name="cadreRegistrationVO.constituencyId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${houseNo}" name="cadreRegistrationVO.panchayatId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value = "${boothId}" name="cadreRegistrationVO.boothId" > 
					<input type="hidden"  value = "${voterInfoVOList[0].cadreId}" name="cadreRegistrationVO.cadreId" > 
					
										<div class="span12">
										<div class="span6">
											<h5 style="color: #9a9a9a;">  CANDIDATE NAME <span class="mandatory">*</span> </h5>
											<input type="text" class="form-control border-radius-0" placeholder="Candidate Name" name="cadreRegistrationVO.voterName"  value="${voterInfoVOList[0].name}" id="cadreNameId" onkeyup="validateName('nameErr','cadreNameId',1);"></input>
											<span id="nameErr" style="color:red;font-size:12px;"></span>
										</div>	
										<div class="span5">	
											<h5 class="text-align1">Age <span class="mandatory">*</span> </h5>
											<input style="width:180px;" id="cadreAgeId" type="text" class="form-control border-radius-0 text-align2"  placeholder="Age" name="cadreRegistrationVO.age"   value="${voterInfoVOList[0].age}"  onkeyup="validateName('ageErr','cadreAgeId',0);"></input>
											<span id="ageErr" style="color:red;font-size:12px;"></span>
										</div>
										</div>
										<div class="span12">
										<div class="span6">
											<h5 style="color: #9a9a9a;">  Voter Telugu Name  </h5>
											<s:if test="voterInfoVOList[0].nameType != null && (voterInfoVOList[0].nameType == 'VOTER' || voterInfoVOList[0].nameType == 'voter')">	
											  <input type="checkbox" id="voterTNameId" class="cadreTnamesCls"  style="margin-top:-15px;" checked="true" value="Voter" name="cadreRegistrationVO.nameType" onClick="updateNameSelection('cadreTNameId');"/>
											</s:if>
											<s:else>
											  <input type="checkbox" id="voterTNameId" class="cadreTnamesCls"  style="margin-top:-15px;"  value="Voter" name="cadreRegistrationVO.nameType" onClick="updateNameSelection('cadreTNameId');"/>
											</s:else>
											<input type="text" class="form-control border-radius-0" placeholder="Voter Telugu Name" style="width:190px;"  value="${voterInfoVOList[0].teluguName}" name="cadreRegistrationVO.voterTeluguName" ></input>											
										</div>	
										<div class="span6">	
											<h5 class="text-align1"> Cadre Telugu Name  </h5>
										<s:if test="voterInfoVOList[0].nameType != null && (voterInfoVOList[0].nameType == 'CADRE' || voterInfoVOList[0].nameType == 'cadre')">												
											<input type="checkbox"  id="cadreTNameId" class="cadreTnamesCls" value="Cadre" style="margin-top:-15px;" name="cadreRegistrationVO.nameType"  checked="true" value="Cadre"  onClick="updateNameSelection('voterTNameId');"/>
										</s:if>
										<s:else>
										   <input type="checkbox"  id="cadreTNameId" class="cadreTnamesCls" value="Cadre" style="margin-top:-15px;" name="cadreRegistrationVO.nameType" value="Cadre"  onClick="updateNameSelection('voterTNameId');"/>
                                        </s:else>										
											<input type="text" class="form-control border-radius-0" placeholder="Cadre Telugu Name" style="width:170px;" value="${voterInfoVOList[0].teluguRelativeName}" ></input>
											
										</div>
										</div>
										<div class="span12"> 
											  <ul class="unstyled inline text-center">
												  <li id="voterActualImgLiId">
												   <s:if test="voterInfoVOList[0].voterImagePresent == true">
													 <div class="well  pad-5 m_top10" style="width: 125px; padding-bottom: 15px;padding-top: 13px;">
														<span><img src="${voterInfoVOList[0].voterImage}" style="width: 140px; height: 120px;"></span>
														<div class="btn btn-mini btn-block"> <input type="checkbox" class="m_top10" onclick="hideCadreImg();" name="cadreUploadImgVoterType" id="voterActualImgId" style="margin-top:-1px;">
															 <span style="color: #9a9a9a;font-weight: bold;">&nbsp;Use This Photo</span>
														</div>
													 </div>
													</s:if>
												  </li>
												  <li id="cadreActualImgLiId">
												   <s:if test="voterInfoVOList[0].cadreImagePresent == true">
													 <div class="well  pad-5 m_top10" style="width: 125px; padding-bottom: 15px;padding-top: 13px;">
														<span><img src="${voterInfoVOList[0].cadreImage}" style="width: 140px; height: 120px;"></span>
														<div class="btn btn-mini btn-block"> <input type="checkbox" class="m_top10" onclick="hideVoterImg();" name="cadreUploadImgCadreType" id="cadreActualImgId" style="margin-top:-1px;">
															 <span style="color: #9a9a9a;font-weight: bold;">&nbsp;Use This Photo</span>
														</div>
													 </div>
													</s:if>
												  </li>
												  <li>
													<div style="width: 125px;" class="well  pad-5 m_top10">
														<s:if test="voterInfoVOList[0].image != null">
														  <span id="uploadImg"><img style="width: 140px; height: 120px;" id="actuploadImg" src="${voterInfoVOList[0].image}"></span>
														</s:if>
														<s:else>
														  <span id="uploadImg"><img style="width: 140px; height: 120px;" id="actuploadImg" src="images/mahaNadu/user image.jpg"></span>
														</s:else>
														<div class="btn-group"><input type="hidden" id="base64Image" name="cadreRegistrationVO.imageBase64String"/>
															<span style="display:none;"><input type="checkbox" style="margin-top:-1px;" id="newTakenImgId" name="newTakenImgType" onclick="hideVoterImg();"></span>
															<input type="file" class="m_top10 btn btn-mini" name="cadreRegistrationVO.uploadImage" onchange="changeImg();" id="uploadFileId" style="width: 58px; margin-left: 0px; padding-left: 0px;">
															<span onclick="showTakeImage();"><img style="width: 22px; height: 17px; padding: 7px 3px; margin-top: 8px;" class="btn btn-mini" title="Take Picture" src="images/candidatePage/camera.png"  id="takePicture"></span>
															<a onclick="clearExistingImg('uploadImg');" style="padding-top: 10px; padding-bottom: 7px; margin-top: 10px;" class="btn btn-mini"><span class="icon-remove" style="cursor: pointer;" title="Click Here To Delete Existing Image" ></span></a>
															<br/><span id="imageErr" style="color:red;font-size:12px;"></span>
														</div>
													</div>
												  </li>
											  </ul>
										</div>
									<div class="span12">
										<div class="span6">
											<h5 class="text-align1">DATE OF BIRTH  <span class="mandatory"></span></h5>
												
												<div class="input-prepend text-align2 ">
													
													<input type="text" class="dateOfBirthDtCls" name="cadreRegistrationVO.dobStr" value="${voterInfoVOList[0].dateOfBirth}" placeholder="Date of Birth" readOnly="true" id="dateOfbirthId"></input>
													<br><span id="dobErr" style="color:red;font-size:12px;"></span>
												</div>
										</div>	
										<div class="span6">	
											<h5 class="text-align1">GENDER</h5>	 
											<div class="row-fluid form-inline" style="margin-left:5px;">
												<s:if test="%{voterInfoVOList[0].gender != null}">
												
													<c:if test="${voterInfoVOList[0].gender == 'M' || voterInfoVOList[0].gender == 'Male'}">
													   <label class="radio"><input type="radio" value="MALE" id="maleGenderRId" name="cadreRegistrationVO.gender" checked="true" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  id="femaleGenderRId"  name="cadreRegistrationVO.gender"> FEMALE</input></label>
													</c:if>
													<c:if test="${voterInfoVOList[0].gender == 'F' || voterInfoVOList[0].gender == 'Female'}">												
														<label class="radio"><input type="radio" value="MALE"  id="maleGenderRId"  name="cadreRegistrationVO.gender" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE" id="femaleGenderRId"   name="cadreRegistrationVO.gender"  checked="true"> FEMALE</input></label>
													</c:if>
												
												</s:if>
												<s:else>
												  <label class="radio"><input type="radio" value="MALE"  id="maleGenderRId"  name="cadreRegistrationVO.gender" > MALE</input></label>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<label class="radio"><input type="radio" value="FEMALE"  id="femaleGenderRId"  name="cadreRegistrationVO.gender" > FEMALE</input></label>
												</s:else>
													<br><span id="gendReqErr" style="color:red;font-size:12px;"></span>
											</div>		
										</div>
									</div>	
								<div class="m_top10">
									<div class="row-fluid">
										
										<div class="span6">
										<h5 class="text-align1">GUARDIAN NAME </h5>
										<input type="text" class="form-control border-radius-0 text-align2" placeholder="Guardian Name" name="cadreRegistrationVO.relativeName"   value="${voterInfoVOList[0].relativeName}"  id="gardianNameId" onkeyup="validateName('garErr','gardianNameId',1);"></input>
										<br><span id="garErr" style="color:red;font-size:12px;"></span>
										</div>
										<input type="hidden" value="${voterInfoVOList[0].voterId}" name="cadreRegistrationVO.voterId"></input>
										<div class="span6">
										<h5 class="text-align1">Relationship Type</h5>
											<input type="text" class="form-control border-radius-0 " placeholder="Relationship Type" name="cadreRegistrationVO.relationType"   value="${voterInfoVOList[0].relationType}" id="relationTypessId" onkeyup="validateName('relErr','relationTypessId',1);"></input>
												<br><span id="relErr" style="color:red;font-size:12px;"></span>
										</div>
									</div>
								</div>
								<div class="m_top10">
										<div class="row-fluid">
										  <s:if test="voterInfoVOList[0].voterCardNo == null || voterInfoVOList[0].voterCardNo.length == 0 ">
											<div style="width:150px;float:left;">
											<h5 class="text-align1">VOTER ID <span class="mandatory">*</span></h5>
												<input type="text" class="form-control border-radius-0 text-align2 " placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"   id="cardNumber" value="${voterInfoVOList[0].voterCardNo}" readonly style="width:135px;"></input>
												 <div id="cardErr" style="color:red;font-size:12px;"></div>
												<!--<input type="hidden" id="cardNo" class="form-control border-radius-0 input-block-level" placeholder="Text input" value="${voterInfoVOList[0].voterCardNo}" style="width:260px;" ></input>-->
											</div>
											
											<div style="width: 120px; float: left; margin-top: 20px;">
												<a id="searchByNameId" class="btn btn-success" href="javascript:{enableLookupName();}" style="margin-top: 20px; width: 120px; margin-left: 16px;">LookUp</a>
											</div>
											
											<div style="width: 120px; float: left; margin-left: 49px;">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo" style="width: 120px; float: left; margin-left: 0px;"  value="${voterInfoVOList[0].houseNo}"></input>
											</div>
										    </s:if>
										  <s:else>
											<div class="span6">
											<h5 class="text-align1">VOTER ID <span class="mandatory">*</span></h5>
												<input type="text" class="form-control border-radius-0 text-align2 " placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"   id="cardNumber" value="${voterInfoVOList[0].voterCardNo}" readonly ></input>
												 <div id="cardErr" style="color:red;font-size:12px;"></div>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo" style="float: left; margin-left: 0px;"  value="${voterInfoVOList[0].houseNo}"></input>
											</div>
										  </s:else>
												<c:if test="${not empty voterInfoVOList[0].fmlyVCardNo}">
													<div class="span4 famlyMemClsDiv">												
														<input type="checkbox" title="Please Check If Cadre Didn't Have Voter Card And Using His Family Members Voter Card" id="relativeTypeChecked" name="relativeTypeChecked" onclick="showHideFamRelatinoSts();" checked="true"/> Is Family Member
													</div>
													<div  class="span6" id="showHideFammemberType" style="display:block; margin-left: 165px;margin-top: -33px;">
														<span style="color: #9a9a9a;font-weight: bold;">Relation &nbsp;</span><select name="relativeTypeId" id="relativeTypeId"> </select>
														<span style="color: #9a9a9a;font-weight: bold;">Voter Card <span class="mandatory">*</span>&nbsp;</span><input type="text" readonly="readonly" id="familyVtrCrdId" style="width: 190px;" name="relativeVoterCardNo" value="${voterInfoVOList[0].fmlyVCardNo}"><span class="icon-remove" style="cursor: pointer;" title="Click Here To Clear Voter Card No" onclick="clearSelDiv('familyVtrCrdId');"></span>
														 <span id="familyVtrCrdIdErr" style="color:red;font-size:12px;"></span>
														<a id="searchByNameId" class="btn btn-success" href="javascript:{enableSearchByfName();}" style="margin-top:10px;"> LookUp </a>
													</div>
												</c:if>
												<c:if test="${empty voterInfoVOList[0].fmlyVCardNo}">	
													<div class="span4 famlyMemClsDiv">
														<input type="checkbox" title="Please Check If Cadre Didn't Have Voter Card And Using His Family Members Voter Card" id="relativeTypeChecked" name="relativeTypeChecked" onclick="showHideFamRelatinoSts();"/> Is Family Member 
													</div>
													<div  class="span6" id="showHideFammemberType" style="display:none ; margin-left: 165px;margin-top: -33px;">
														<span style="color: #9a9a9a;font-weight: bold;">Relation &nbsp;</span><select name="relativeTypeId" id="relativeTypeId"> </select>
														<span style="color: #9a9a9a;font-weight: bold;">Voter Card <span class="mandatory">*</span>&nbsp;</span><input type="text" readonly="readonly" id="familyVtrCrdId" style="width: 190px;" name="relativeVoterCardNo" value="${voterInfoVOList[0].fmlyVCardNo}"><span class="icon-remove" style="cursor: pointer;" title="Click Here To Clear Voter Card No" onclick="clearSelDiv('familyVtrCrdId');"></span>
														<span id="familyVtrCrdIdErr" style="color:red;font-size:12px;"></span>
														<a id="searchByNameId" class="btn btn-success" href="javascript:{enableSearchByfName();}" style="margin-top:10px;"> LookUp </a>
													</div>
												</c:if>
										</div>
								</div>	
								<input type="hidden" id="fmlyVtrId" class="form-control border-radius-0 input-block-level" placeholder="Text input"  style="width:260px;"  name="cadreRegistrationVO.familyVoterId"></input>
								
								<div class="m_top10">
										<div class="row-fluid">
										
											<!--<div class="span6">
											<h5 class="text-align1">PARTY MEMBER SINCE</h5>
												<input type="text" class="form-control border-radius-0 text-align2 datePickerCls" style="width: 186px;" placeholder="Party Member Since " name="cadreRegistrationVO.partyMemberSinceStr" id="reqPartyMemberSinceStrId" value="${voterInfoVOList[0].activeDate}"  readOnly="true"></input><span class="icon-remove" style="cursor: pointer;" title="Click Here To Clear Party Member Since" onclick="clearSelDiv('reqPartyMemberSinceStrId');"></span>
											</div>-->
											
											<div class="span6">
											<h5 class="text-align1">Blood Group</h5>
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="bloodgroupId" list="voterInfoVOList[0].selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Blood Group" style="width:220px;" name="cadreRegistrationVO.bloodGroupId"  value="%{voterInfoVOList[0].blodGroupId}"/>
											
											</div>
										</div>
								</div>	
								<!--<div class="m_top10">
									
											<h5 class="text-align1">REFERED BY</h5>
												<input type="text" class="form-control border-radius-0 text-align1" placeholder="">
							
								</div>-->
						
					</div>
					<div class="span6 show-grid pad-10b" id="fadeInRight" style="min-height: 779px;" >
							<!-- 
							<div class=" m_top20" >
								<h5 class="text-align1">Aadhar Card No .</h5>
								<input type="text" class=""  style="width:260px;" placeholder="Aadhar Number"  name="cadreRegistrationVO.aadheerNo" value="${voterInfoVOList[0].aadharNo}"></input>
							-->
								<div class="" >
									<h5 class="text-align1">AADHAR CARD NO .</h5>
									<input type="text" class=""  style="width:260px;" placeholder="Aadhar Number" id="candAdrId" onkeyup="isAadharNumber('candAdrId','Aadhar No ')"  name="cadreRegistrationVO.candidateAadherNo" value="${voterInfoVOList[0].candidateAadharNo}"></input>
								    <br/><span id="errcandAdrId" style="color:red;font-size:12px;"></span>
								</div>
							
							
								<div class=" " >
										<h5 class="text-align1">ADDRESS/STREET/HAMLET/PINCODE</h5>
										<textarea  class="form-control border-radius-0  input-block-level" placeholder="ADDRESS/STREET/HAMLET/PINCODE" name="cadreRegistrationVO.street"  style="width:260px;">${voterInfoVOList[0].location}</textarea>
								</div>	
							<div class=" " >
										<h5 class="text-align1">CASTE NAME <span class="mandatory">*</span><span id="casteErr" style="color:red;font-size:12px;"></span>  </h5>
									<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="casteId" list="voterInfoVOList[0].genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width:260px;" name="cadreRegistrationVO.casteId"   value="%{voterInfoVOList[0].casteId}"/>	
								
									
									<!-- <input type="text" class="form-control border-radius-0  input-block-level" id="casteIdValue" placeholder=" Caste Name "   value="${voterInfoVOList[0].casteName}" style="width:260px;"></input>
										
										<input type="hidden" class="form-control border-radius-0  input-block-level" id="casteId" placeholder="Enter Caste" name="cadreRegistrationVO.casteId"  value="${voterInfoVOList[0].casteId}" style="width:260px;"></input>
										-->
							</div>
							<div class=" " >
										<h5 class="text-align1"> EMAIL ID : <span id="emailErr" style="color:red;font-size:12px;"></span> </h5>
										<input type="text" id="emailId" class="form-control border-radius-0 input-block-level" placeholder=" Enter Email Id "  name="cadreRegistrationVO.emailId"  value="${voterInfoVOList[0].emailId}" style="width:260px;"  onKeyup="isValidMailId()"></input>
							</div>
							
							<div class=" " >
										<h5 class="text-align1">MOBILE NUMBER <span class="mandatory">*</span> <span id="mobileErr" style="color:red;font-size:12px;"></span> </h5>
										<input type="text" id="mobileNumberId" class="form-control border-radius-0 input-block-level" placeholder=" Mobile Number "  name="cadreRegistrationVO.mobileNumber"  value="${voterInfoVOList[0].mobileNo}" style="width:260px;" maxlength="10" onKeyup="isNumber()"></input>
							</div>
							<div class=" " >
								<h5 class="text-align1">EDUCATION</h5>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:260px;" name="cadreRegistrationVO.educationId" value="%{voterInfoVOList[0].education}"/>
							</div>
							<div class=" " >
								<h5 class="text-align1">OCCUPATION</h5>											
									<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="occupationId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Occupation " style="width:260px;" name="cadreRegistrationVO.occupationId"   value="%{voterInfoVOList[0].occupationId}"/>	
											
							<!--
							<input type="hidden" class="form-control border-radius-0 input-block-level"  id="occupationId" placeholder="Occupation" name="cadreRegistrationVO.occupationId" value="${voterInfoVOList[0].occupationId}" style="width:260px;"></input>
							
							<input type="text" class="form-control border-radius-0 input-block-level"  id="occupationValue"  placeholder=" Enter Occupation "  value="${voterInfoVOList[0].occupation}" style="width:260px;"></input>
							-->
							</div>
							
							<div class="span7" >
								<h5 class="text-align1">DESIGNATION</h5>
								<select style="width:100%">
									<option value="0">Select Designation</option>
								</select>
							</div>
							<div class="span6" style="margin-left:0px;" >
								<h5 class="text-align1">ZONE</h5>
								<select class="form-control border-radius-0 text-align2 " style="width:100%;margin:0px">
									<option value="0">Select Zone</option>
								</select>
							</div>
							<div class="span6" style="margin-left:0px;" >
								<h5 class="text-align1">REGION</h5>
								<select class="form-control border-radius-0 text-align2 " style="width:100%;margin:0px">
									<option value="0">Select Region</option>
								</select>
							</div>
							<div class="span6" style="margin-left:0px;" >
								<h5 class="text-align1">DEPOT</h5>
								<select class="form-control border-radius-0 text-align2 " style="width:100%;margin:0px">
									<option value="0">Select Depot</option>
								</select>
							</div>
							<!--<div class=" m_top20" > 
									<h5 class="text-align1"> PREVIOUSLY ENROLLED  ? </h5>
									<input type="text" id="preEnrollNoValue" class="form-control border-radius-0 input-block-level" placeholder="Previous Enrollment No."  value="${voterInfoVOList[0].memberShipId}" style="width:260px;"  onkeyup="getExistingCadreInfo2();" name="cadreRegistrationVO.previousEnrollmentNumber" readonly></input>&nbsp;<span onclick="clearPreviousEnrol();" title="Click Here To Clear Previous Enrollment Number" style="cursor: pointer;" class="icon-remove"></span>
									<a id="searchByNameId" class="btn btn-success" href="javascript:{enableSearchByName();}" > LookUp For EnrollmentNo</a>
									<input type="hidden" id="preEnrollNo" class="form-control border-radius-0 input-block-level" placeholder="Text input"  value="${voterInfoVOList[0].memberShipId}" style="width:260px;" ></input>
									
							</div>-->
						
					</div>
				</div>
		
		</div>
	</div>
	<!----  srishailam starts  -->
	
		<div class="container" >
		  <div class="span12" >
				<div class="row-fluid">
					<div class="span  offset3 show-grid m_top10"  id="fadeInLeft" style="margin-left:0px;margin-bottom:10px;">						
						<div class="m_top10">
						<div class="span12 " style="position: relative;">
							<h3 class="text-align "> FAMILY DETAILS </h3>
						</div>
								<div class="row-fluid" id="addVotersDiv">		
											<table>
											<thead>
											<tr>
												<th style="width:25px;">  </th>
												<th style="width:223px;"> VOTER NAME </th>
												<th  style="width:145px;"> VOTER CARD NO</th>
												<th  style="width:80px;"> AGE </th>
												<th  style="width:80px;"> GENDER </th>
												<th  style="width:156px;"> EDUCATION  </th>
												<th  style="width:182px;"> OCCUPATION <a class="icon-plus-sign" style="cursor:pointer;float:right;" onClick="addMoreVoters();"  title="Click Here To Add More Family Members"> </a><br></th>
												
												<th> 
												<a class="icon-minus-sign"  style="cursor:pointer; padding-bottom: 5px;"  onClick="deleteRecordsForm();" title="Select Multiple Family Members And Click Here To Remove Them">
												</th>
													
							<s:if test="%{voterInfoVOList[0].voterInfoVOList != null && voterInfoVOList[0].voterInfoVOList.size() > 0}">
											</tr>
											</thead>
											<tbody>		
											
									<c:forEach var="familyVO" items="${voterInfoVOList[0].voterInfoVOList}" varStatus="commentLoop">
									
										<input type="hidden" value="${commentLoop.index}" id="countVar"></input>
										<input type="hidden" value="${commentLoop.index}" id="countVar"></input>
											<tr class="voterDev${commentLoop.index}">
												<td style="width:25px;text-align:center;">   
												<!--	<input type="checkbox" id="checkBox${commentLoop.index}"  name="" class="nomineeCls" onclick="nomineeUpdate('checkBox${commentLoop.index}',${commentLoop.index})" style="margin-top: -10px;" title="Click here to add make this member as nominee.">
												-->
													<a class="icon-remove nomineeCls" style="margin-top:-10px;" id="checkBox${commentLoop.index}" onclick="nomineeUpdate('checkBox${commentLoop.index}',${commentLoop.index})" title="Click here to add this member as nominee.">
													
												</td>
												<td style="width:100px;">	
													<input type="text" id="voterName${commentLoop.index}" class="form-control border-radius-0 text-align2" placeholder="Voter Name " value="${familyVO.name}" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterName"></input> 
													<input type="hidden" id="voterId${commentLoop.index}" class="form-control border-radius-0 text-align2" value="${familyVO.voterId}"  name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterId"></input> 
												</td>
												<td style="width:100px;"> 
												
													<input type="text" id="voterCard${commentLoop.index}" class="form-control border-radius-0 text-align2" placeholder="Voter Card No " value="${familyVO.voterCardNo}"name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterCadreNO" style="width:120px;"></input>
												</td>
												<td style="width:80px;"> 
													<input type="text" id="voterAge${commentLoop.index}" class="form-control border-radius-0 text-align2 famAgeErrCls" placeholder="Age "  name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].age"  value="${familyVO.age}" key="${commentLoop.index}" style="width:53px;"></input> <br/> <span style="color:red;font-size:12px;" id="famAgeErr${commentLoop.index}"></span> 
												</td>
												<td style="width:80px;">
													<input type="text" id="voterGender${commentLoop.index}" class="form-control border-radius-0 text-align2" placeholder="Gender "  name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].gender"  value="${familyVO.gender}" style="width:50px;"> </input>
												</td>
												<td style="width:100px;"> 
												
													<select name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].educationId" style="width:160px;">
													<option value="0">Select Education</option>
														<c:forEach var="educationList" items="${genericVOList}" >															
															<c:if test="${educationList.id == familyVO.education }">																
																<option value="${educationList.id}" selected="selected">${educationList.name}</option>
															</c:if>	
															<c:if test="${educationList.id != familyVO.education }">																
																<option value="${educationList.id}">${educationList.name}</option>
															</c:if>	
														</c:forEach>
													</select>
													 
												</td>
												<td style="width:100px;"> 
												
												<!--
													 <input type="text" id="familyOccupation${commentLoop.index}" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Occupation "  style="width:150px;"  value="${familyVO.occupation}"> </input>
													 
													 <input type="hidden"  id="familyOccupation${commentLoop.index}0" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value="${familyVO.occupationId}"> </input>
													-->
											<select name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].occupationId" id="familyOccupation%{commentLoop.index}" style="width:180px;margin-left:15px;">
													<option value="0">Select Occupation</option>
													
											<c:forEach var="educationList" items="${selectOptionVOList}" >													
													<c:if test="${educationList.id == familyVO.occupationId }">																
														<option value="${educationList.id}" selected="selected">${educationList.name}</option>
													</c:if>	
													<c:if test="${educationList.id != familyVO.occupationId }">																
														<option value="${educationList.id}">${educationList.name}</option>
													</c:if>	
											</c:forEach>
														
											
											</select>
											
													 
												</td>
												<td style="width:15px;"> 
												
												<input type="checkbox"  class="removeCls" style="float:left;margin-top:-10px;" id="voterDev${commentLoop.index}">
												<!--<a class="icon-minus-sign" style="float:right;margin-left:-3px;" onClick="deleteRollesForm('voterDev${commentLoop.index}');" title="Remove Details"></a> -->
												</td>
											</tr>
											
									</c:forEach>
									</tbody>									
								</table>
								
								
									
								</s:if>
								<s:else>
											
											</tr>
											</thead>
											<tbody>	
										<input type="hidden" value="0" class="countVar"></input>
											<tr class="voterDev0">
											    <td style="width:25px;text-align:center;">
											    <!-- <input type="checkbox" id="checkBox0"   class="nomineeCls" onclick="nomineeUpdate('checkBox0',0)" style="margin-top: -10px;" title="Click here to add make this member as nominee."> -->
													<a class="icon-remove nomineeCls" style="margin-top:-10px;" onclick="nomineeUpdate('checkBox0',0)" title="Click here to add this member as nominee.">
												</td>
												<td> 
													<input type="text" id="voterName0" class="form-control border-radius-0 text-align2" name="cadreRegistrationVO.cadreFamilyDetails[0].voterName" id="voterName0"  placeholder=" Voter Name "  ></input> 
													<input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails[0].voterId"  ></input> 
												</td>
												<td> 
													<input type="text" id="voterCard0" class="form-control border-radius-0 text-align2" placeholder=" Voter Id " name="cadreRegistrationVO.cadreFamilyDetails[0].voterCadreNO"  style="width:120px;"></input>
												</td>
												<td style="width:80px;"> 
													<input type="text" id="voterAge0" key="0" class="form-control border-radius-0 text-align2 famAgeErrCls" placeholder=" Age "  name="cadreRegistrationVO.cadreFamilyDetails[0].age"  style="width:50px;"></input>  <br/> <span style="color:red;font-size:12px;" id="famAgeErr0"></span>
												</td>
												<td style="width:80px;">
													<input type="text" id="voterGender0" class="form-control border-radius-0 text-align2" placeholder="Gender"  name="cadreRegistrationVO.cadreFamilyDetails[0].gender"  style="width:50px;"> </input>
												</td>
												<td style="width:100px;"> 
												
													<select name="cadreRegistrationVO.cadreFamilyDetails[0].educationId" style="width:160px;">
													<option value="0">Select Education</option>
														<c:forEach var="educationList" items="${genericVOList}" >
															<option value="${educationList.id}">${educationList.name}</option>
														</c:forEach>
													</select>
													 
												</td>
												<td style="width:100px;"> 
												
												<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level"  list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Occupation " style="width:180px;margin-left:15px" id="familyOccupation0"  name="cadreRegistrationVO.cadreFamilyDetails[0].occupationId"/>
												
												<!--
													<input type="text" id="familyOccupation0" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder="  Occupation "  style="width:150px;"  value=""> </input>
													 
													<input type="hidden"  id="familyOccupation00" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>
												-->
												
												</td>
												<td style="width:15px;"> 
													<input type="checkbox"  class="removeCls" style="float:left;margin-top:-10px;" id="voterDev0">
													<!-- <a class="icon-minus-sign" style="float:right;" onClick="deleteRollesForm('voterDev0');" title="Remove Details"> -->
												</td>
											</tr>
											
									</tbody>									
									</table>	
																							
								</s:else>
								
								</div>
						</div>
					</div>
				
	</div>
	</div>
	</div>
	<!----  srishailam end  -->
	
	
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">NOMINEE DETAILS</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="accDiv">
				<div class="span2">
					<h5 class="text-align1"> Aadhar Card  <span class="mandatory"></span></h5>
					<input type="text" class="" style="width: 138px;" placeholder="Aadhaar Number"  name="cadreRegistrationVO.aadheerNo" value="${voterInfoVOList[0].aadharNo}" id="nomineAadharId" onkeyup="isAadharNumber('nomineAadharId','Aadhar No ')" maxlength="12"></input> 
					 <span id="NaadharErr" style="color:red;font-size:11px;"></span>
				</div>
				
				<div class="span2">
					<input type="hidden" class="" style="width: 138px;" placeholder="Nominee VoterId"    id="nomineeVoterId"></input>
					<input type="hidden" class="" style="width: 138px;" placeholder="Nominee VoterId"    id="nomineeVoterCardId"></input>
					<h5 class="text-align1"> Nominee Name  <span class="mandatory">*</span></h5>
					<input type="text" class="" style="width: 138px;"placeholder="Nominee Name"  name="cadreRegistrationVO.nomineeName" value="${voterInfoVOList[0].nomineeName}" id="nomineNameId" onkeyup="isValidName('name')"></input>
					<span id="NnameErr" style="color:red;font-size:11px;"></span>
				</div>
				
				
				
				<div class="span2">
					<h5 class="text-align1"> Gender <span class="mandatory">*</span></h5>
					<select name="cadreRegistrationVO.nomineeGender" style="width: 138px;" id="nomineeGenderId" >
							<option value="0">Select Gender</option>	
							<option value="1">Male</option>
							<option value="2">Female</option>
					</select>
					 <span id="NgenderErr" style="color:red;font-size:11px;"></span>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Age  <span class="mandatory">*</span></h5>
					<input type="text" class=""  style="width: 100px;" placeholder=" Age "  name="cadreRegistrationVO.nomineeAge" value="${voterInfoVOList[0].nomineAge}" id="nomineeAgeId"  onkeyup="isValidName('number')"></input>
					<span id="NageErr" style="color:red;font-size:11px;"></span>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Relation Type <span class="mandatory">*</span></h5>
					<select name="cadreRegistrationVO.voterRelationId" style="width:160px;" id="voterRelationId">
							<option value="0">Select Relation</option>	
							<option value="1">Father</option>	
							<option value="2">Mother</option>	
							<option value="3">Wife</option>	
							<option value="4">Brother</option>	
							<option value="5">Sister</option>	
							<option value="7">Husband</option>	
							<option value="8">Son</option>	
							<option value="9">Daughter</option>	
							<option value="10">Grand Son</option>	
							<option value="11">Grand Daughter</option>	
							<option value="12">Others</option>	
					</select>
					<span id="NrelationErr" style="color:red;font-size:11px;"></span> 
				</div>
			</div>
		</div>
	</div>
	<!--<div class="container"><div class="row"><div  id="toggleButtonDiv" class="span8 offset3">
		<a class="btn btn-success  m_top10 m_bottom10 border-radius-0" id="toggleButtonId">Click To See/Hide  Previous Roles And Previously Participated In Elections</a>
  </div></div></div>-->
	<div id="fadeInUp" class="fadeInUpClass">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUS ROLES PARTICIPATED IN PARTIES</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="rollesDiv">	
			<s:if test="%{voterInfoVOList[0].cadreRolesList != null && voterInfoVOList[0].cadreRolesList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].cadreRolesList}" varStatus="indexValue">
					
			
				
			
					<div class="levelCls">
						<div>
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1"> Committee Level </h5>
						</c:if>
							<select class="levelCls" id="CadreCommitteeLevelsId${indexValue.index}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreCommitteeLevelId"  style="margin-left: 12px" onchange="getCadreCommitteDetails(this.value,'CadreCommitteeId${indexValue.index}')">			
							<c:forEach var="educationList" items="${cadreRolesVOList[1].selectOptionsList}" >		
									<c:if test="${educationList.id == role.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>															
									
							</select>
						</div>
		
					</div>
					
					<div class="levelCls">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1"> Cadre Committee </h5>
						</c:if>
							<select class="levelCls" id="CadreCommitteeId${indexValue.index}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreCommitteeId" style="margin-left: 12px" onchange="getCadreCommitteRoles(this.value,'CadreRolesId${indexValue.index}','CadreCommitteeLevelsId${indexValue.index}')">
							<c:forEach var="educationList" items="${voterInfoVOList[0].cadreRolesList[indexValue.index].basicVO.hamletCasteInfo}" >		
										<c:if test="${educationList.id == role.count }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.count }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>
							</select>
									
						</div>
					</div>
					
					<div class="levelCls">
						<div class="text-align2" >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Cadre Role </h5>
						</c:if>
								<select class="levelCls" id="CadreRolesId${indexValue.index}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreRoleId" style="margin-left: 12px">
								<c:forEach var="educationList" items="${voterInfoVOList[0].cadreRolesList[indexValue.index].basicVO.panchayatVoterInfo}" >		
										<c:if test="${educationList.id == role.rank }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.rank }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
							</c:forEach>
								</select>
						</div>
		
					</div>
		
					<div class="levelCls">
						<div class="text-align2" >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">From Date</h5>
						</c:if>								
								<div class=" text-align2">				
									<input type="text" id="fromDateId${indexValue.index}" key ="${indexValue.index}" class="levelDtCls form-control span2 border-radius-0 border-right-0 datePickerCls fromDateCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].fromDateStr" placeholder="From Date"  readOnly="true" value="${role.startTime}"></input>
								<br><span id="fromDateErr${indexValue.index}" style="color:red;font-size:12px;"></span> 									
									
								</div>
						</div>
					</div>
					<div class="levelCls">
						<div class=" text-align2" >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">To Date</h5>
						</c:if>
						
								<div class="text-align2">	
									<input type="text" id="toDateId${indexValue.index}" class="levelDtCls form-control span2  border-radius-0 border-right-0 datePickerCls toDateCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].toDateStr" placeholder="To Date"  readOnly="true" value="${role.endTime}"></input>
									<div id="toDateErr${indexValue.index}" style="color:red;font-size:12px;"></div> 
								</div>
						</div>
					</div>
					
		
			</c:forEach>
			<div>
				
			</div>
				
			</s:if>
			<s:else>
					<div class="levelCls">
						<div>
							<h5 class="text-align1"> Committee Level </h5>
							<select class="levelCls" id="CadreCommitteeLevelsId0" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeLevelId"  style="margin-left: 12px"  onchange="getCadreCommitteDetails(this.value,'CadreCommitteeId0')">			
							<c:forEach var="educationList" items="${cadreRolesVOList[1].selectOptionsList}" >																	
										<option value="${educationList.id}">${educationList.name}</option>
							</c:forEach>															  
									
							</select>
						</div>
		
					</div>
					
					<div class="levelCls">
						<div class=" " >	
							<h5 class="text-align1"> Cadre Committee </h5>
							<select class="levelCls" id="CadreCommitteeId0" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeId" style="margin-left: 12px" onchange="getCadreCommitteRoles(this.value,'CadreRolesId0','CadreCommitteeLevelsId0')">
								<option value="0">Select Committee </option>
							</select>
									
						</div>
					</div>
					
					<div class="levelCls">
						<div class=" " >
							<h5 class="text-align1">Cadre Role </h5>
								<select class="levelCls" id="CadreRolesId0" name="cadreRegistrationVO.previousRollesList[0].cadreRoleId" style="margin-left: 12px">
									<option value="0">Select Role </option>
								</select>
						</div>
		
					</div>
		
					<div class="levelCls">
						<div class="text-align2 " >
							<h5 class="text-align2">From Date</h5>
							
								<div class="text-align2">				
									<input type="text" id="fromDateId0" key="0" class="levelDtCls form-control span2 border-radius-0 border-right-0 datePickerCls fromDateCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].fromDateStr" placeholder="From Date"  readOnly="true" value=""></input>
									<br><span id="fromDateErr0" style="color:red;font-size:12px;"></span> 
								</div>
						</div>
					</div>
					<div class="levelCls">
						<div class=" text-align2" >
							<h5 class="text-align2">To Date</h5>						
								<div class="text-align2">	
									<input type="text" id="toDateId0" class="levelDtCls form-control span2  border-radius-0 border-right-0 datePickerCls toDateCls" style="margin-top:5px;" name="cadreRegistrationVO.previousRollesList[0].toDateStr" placeholder="To Date"  readOnly="true" value=""></input>
										<br><span id="toDateErr0" style="color:red;font-size:12px;"></span>
								</div>
						</div>
					</div>					

			</s:else>
			
			<a class="icon-plus-sign" style="float:left;margin-top:15px;" onClick="createNewForm();" title="Add More Details"></a>
			
			</div>
	</div>
	</div>
	<div id="fadeInUp1"  class="fadeInUpClass">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUSLY PARTICIPATED IN ELECTION</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="electionsDiv">
			
			<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
					
					<div class="row-fluid">
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Election Type</h5>
						</c:if>

							<select  style="margin-left: 12px" onChange="getElectionYears(this.value,'electionYearId${indexValue.index}')" id="electionsTypeID${indexValue.index}">
								<option value="0"> Select Election Type</option>
								<c:forEach var="educationList" items="${eletionTypesList}" >															
									<c:if test="${educationList.id == role.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
							
						</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Year</h5>
						</c:if>							  		
							 <select class="" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].electionTypeId" id="electionYearId${indexValue.index}" onchange="getConstiteuncyListForElection(this.value,'constituencyList${indexValue.index}')">
								<option value="0"> Select Election </option>
								<c:forEach var="educationList" items="${role.genericVOList}" >															
									<c:if test="${educationList.id == role.count }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.count }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>	
							<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>							
						</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Constituency</h5>
						</c:if>

							<select   id="constituencyList${indexValue.index}"  style="margin-left: 12px" onchange="getCandidateDetailsForElection(this.value,'candidatesList${indexValue.index}','electionYearId${indexValue.index}',
							'electionsTypeID${indexValue.index}');" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].constituencyId">
								<option value="0"> Select Constituency</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletCasteInfo}" >															
									<c:if test="${educationList.id == role.rank }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.rank }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
							
						</div>
					</div>	

					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Candidate </h5>
						</c:if>

							<select   id="candidatesList${indexValue.index}"  style="margin-left: 12px;width:150px" onchange="getCandidateDetailsById('constituencyList${indexValue.index}','electionYearId${indexValue.index}',this.value);" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].candidateId">
								<option value="0"> Select Candidate</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletVoterInfo}" >
								<c:if test="${educationList.id == role.nomineeAge }">	<option value="${educationList.id}" selected="selected">${educationList.name}</option>
								</c:if>	
								<c:if test="${educationList.id != role.nomineeAge }">	<option value="${educationList.id}">${educationList.name}</option>
								</c:if>	
								</c:forEach>
							</select>
							
						</div>
					</div>		
					
				</div>				
					</c:forEach>
						<a title="Add More Details" onclick="createNewFormsForElections();" style="float:right;margin-right:35px;margin-top:-30px;" class="icon-plus-sign"></a>
					
					<div id="candidateDivTab" style="float:left;"></div>
					
			</s:if>
			<s:else>
			
				<div class="row-fluid">
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Election Type</h5>
							<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="electionTypeId" list="eletionTypesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Election Type" style="width:220px;margin-left: 12px"   onChange="getElectionYears(this.value,'electionYearId0')" value="%{role.id}"/>
						</div>
					</div>
					<div class="span2">
						<div class=" " >
							<h5 class="text-align1">Year</h5>
							<select class="" style="width: 150px;" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId" id="electionYearId0" onchange="getConstiteuncyListForElection(this.value,'constituencyList0')">							
							  </select>
							  <img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
						</div>
					</div>
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Constituency</h5>
							
							<select id="constituencyList0" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId" style="margin-left: 12px" onchange="getCandidateDetailsForElection(this.value,'candidatesList0','electionYearId0',
							'electionsTypeID0');">		
							<option value="0"> Select Constituency</option>
							 </select>
							
						</div>
					</div>
					
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Candidate </h5>
							<select   id="candidatesList0"  style="margin-left: 12px;width:150px" onchange="getCandidateDetailsById('constituencyList0','electionYearId0',this.value); " name="cadreRegistrationVO.previousParicaptedElectionsList[0].candidateId" >
								<option value="0"> Select Candidate</option>
								<c:forEach var="educationList" items="${voterInfoVOList[0].previousParticipationInfoList[indexValue.index].basicVO.hamletVoterInfo}" ><option value="${educationList.id}">${educationList.name}</option>
								</c:forEach>
							</select>
							
						</div>
					</div>
				</div>				
			</s:else>
				<a title="Add More Details" onclick="createNewFormsForElections();" style="float:right;margin-right:35px;margin-top:-30px" class="icon-plus-sign"></a>
				
				<div id="candidateDivTab" style="float:left;"></div>
				
			</div>
		</div>
		
	</div>
	<div class="container m_top10">
			<div style="position: relative;">
				<!-- <a class="btn btn-primary m_top20 border-radius-0 text-align2" href="search-constituency.html"><span class="icon-chevron-left icon-white"></span>&nbsp;&nbsp;Back </a> -->
				
				<div  class="btn btn-success text-align3 m_top20 pull-right border-radius-0" id="submitCadreFormBtnReqId" onClick="submitCadreForm();"> &nbsp;&nbsp;Next<span class=" icon-chevron-right icon-white"></span></div>
			</div>
		</div>
</form>
</div>
</div>
				
				<div id="myModal" style="display:none;">
						<div style="border:1px solid lightgray;">
							<div class="pad-10b">
								<h5 class="text-align1">CANDIDATE NAME</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName" style="width:425px;" onkeyUp="searchCandidatesDetailsBySearchCriteria('voter');">
							</div>
							<div class=" m_top10 pad-10b">
								<div class="row-fluid">
									<div class="span6">
										<h5 class="text-align1">VOTER ID</h5>
										<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard" onkeyUp="searchCandidatesDetailsBySearchCriteria('voter');">
									</div>
									
									<div class="span6">
										<h5 class="text-align1">H NO</h5>
										<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo" onkeyUp="searchCandidatesDetailsBySearchCriteria('voter');">
									</div>
								</div>
							</div>
							<a href="javascript:{searchCandidatesDetailsBySearchCriteria('voter');}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
							<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg" class="" src="images/Loading-data.gif"></div>
							<div id="errorDiv" class="mandatory"></div>
							<div class="show-grid pad-5 m-bottom-10">
								<div class="container" id="tableElement" style="margin-top:25px;display:none;">
									<h3 class="text-align" style="color:red;">SEARCH DETAILS</h3>
									
									<div class="table-responsive" id="searchDetailsDiv" ></div>
								</div>
							</div>
						</div>
				</div>
				
				<div id="myModal2" style="display:none;">
						<div style="border:1px solid lightgray;">
							<div class="pad-10b">
								<h5 class="text-align1">CANDIDATE NAME</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameIdFmly" name="searchName" style="width:425px;" onkeyUp="searchCandidatesDetailsBySearchCriteria('family');">
							</div>
							<div class=" m_top10 pad-10b">
								<div class="row-fluid">
									<div class="span6">
										<h5 class="text-align1">VOTER ID</h5>
										<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardIdFmly"  name="searchVoterCard" onkeyUp="searchCandidatesDetailsBySearchCriteria('family');">
									</div>
									
									<div class="span6">
										<h5 class="text-align1">H NO</h5>
										<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoIdFmly"   name="searchHNo" onkeyUp="searchCandidatesDetailsBySearchCriteria('family');">
									</div>
								</div>
							</div>
							<a href="javascript:{searchCandidatesDetailsBySearchCriteria('family');}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
							<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg3" class="" src="images/Loading-data.gif"></div>
							<div id="errorDivFmly" class="mandatory"></div>
							<div class="show-grid pad-5 m-bottom-10">							
								<div class="container" id="tableElementFmly" style="margin-top:25px;display:none;">
									<h3 class="text-align" style="color:red;">SEARCH DETAILS</h3>
									<div class="table-responsive" id="searchDetailsDivFmly" ></div>
								</div>
							</div>
						</div>
				</div>
				
				<div id="myModal1" style="display:none;">
					<div style="border:1px solid lightgray;">
						<div class="pad-10b">
								<h5 class="text-align1">CANDIDATE NAME</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="candiNameId" name="searchName1" style="width:425px;" onkeyUp="getExistingCadreInfo1();">
						</div>
						<div class=" m_top10 pad-10b">
							<div class="row-fluid">
								<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Enrollment Number"  id="enrollmentNoId"  name="searchVoterCard" onkeyUp="getExistingCadreInfo1();">
							</div>
						</div>
						
						<a href="javascript:{getExistingCadreInfo1();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
						<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg1" class="" src="images/Loading-data.gif"></div>
					</div>
					<div id="errorDiv1"  class="mandatory"></div>
					<div class="show-grid pad-5 m-bottom-10">
						<div class="container" id="tableElement1" style="margin-top:25px;display:none;">
							<h3 class="text-align" style="color:red;">SEARCH DETAILS</h3>
							<div class="table-responsive" id="searchDetailsDiv1" ></div>
						</div>
					</div>
				</div>
	
<div id="statusDiv">
</div>
<div id="wrapper">
			<div id="example"></div>
		</div>


	<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					TDP Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		<!-- start FlipClock -->
<div class="footerFixedStrip" >		
		<div class="" style="width:44%; float:left;">
			<h1 style="margin-right:-100px;float:right;color:#cccccc; text-shadow:0 1px 2px rgb(0, 0, 0);font-size:18px">2014 Cadre Registrations Will be Closed <br>By 2014-12-23 After Noon 04:00PM</h2>
		</div>		
		<div class="" style="width:56%;float:left;">
			<div class="message"  style="font-size: 25px;margin-top:30px;margin-left: 125px;"></div>
			<div class="clock"></div>
		</div>
		
	</div>

<!-- end FlipClock -->	
</body>
<script type="text/javascript">
var existingCadreArr = [];
var existingCadreInfoArr = [];
function getExistingCadreInfo(){
	var value = $('#preEnrollNoValue').val();
	$('#preEnrollNo').val('');
	var constituencyId = '${constiteucnyId}';
	var panchayatId = '${houseNo}';  // panchayat Id 
	var boothId = '${boothId}';  // boothId Id 
	var isPresentCadre = '${panchayatId}';  // ispresentCader checked ot not 
	
	if(value.trim().length >2)
	{
		existingCadreArr = [];
		existingCadreInfoArr = [];
			var jsObj = 
			   {	
				name : value,
				constituencyId : constituencyId,
				panchayatId : panchayatId,
				boothId : boothId,
				isPresentCadre:isPresentCadre,
				task:"getExistingCadreInfo"             
			   }
			   
			   $.ajax({
					type : "POST",
					url : "getExistingCadreInfoAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null && result.length > 0)
					{
						for(var i in result)
						{
							var cadreObj = {
							id : result[i].id,
							name : 	result[i].name,
							relativeName : result[i].desc,
							enrollNo : result[i].caste,
							check : result[i].name+" - "+result[i].desc+" - "+result[i].caste
							}
							
							existingCadreArr.push(cadreObj);
							
							existingCadreInfoArr.push(result[i].name+" - "+result[i].desc+" - "+result[i].caste);
						}
						
						$( "#preEnrollNoValue" ).autocomplete({ 
							source:existingCadreInfoArr,
							select: function (event, ui) {
								$('#preEnrollNoValue').val(ui.item.value);	
								
								for(var k in existingCadreArr)
								{
									
									if(existingCadreArr[k].check == ui.item.value)
									{
										//console.log(existingCadreArr[k]);
										$('#preEnrollNo').val(existingCadreArr[k].enrollNo);
										$('#preEnrollNoValue').val(existingCadreArr[k].enrollNo);
										if(existingCadreArr[k].enrollNo != null && $.trim(existingCadreArr[k].enrollNo).length > 0)
                                           getCadreImage(existingCadreArr[k].enrollNo);										
										break;
									}
								}
								
								return false;
							}
						});
		
					}
				});
	}
}

$("#cardNumber1").keyup(function(){
	var cardNumberVal = $("#cardNumber").val();
	if(cardNumberVal.trim().length>2){
		var constituencyId = '${constiteucnyId}';
		var panchayatId = '${houseNo}'; 
		var boothId = '${boothId}';
		var isPresentCadre = '${panchayatId}';
		searchedVoters = [];
		searchedVotersInfoArr = [];
		
		var jsObj ={
					  constituencyId:constituencyId,
					  searchType :"voter", 
					  candidateName:cardNumberVal,
					  houseNo : "",
					  voterCardNo : "",
					  panchayatId : panchayatId,
					  boothId : boothId ,
					  locationId : 0,
					  isPresentCadre:isPresentCadre,
					  task:"searchCandidatesDtailsBySearchCriteria"             
				   }
		
		 $.ajax({
					type : "POST",
					url : "searchVoterAndCadreInfoBySearchCriteriaAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					//if(result==null){return;}
					for(var i in result){
							var vtrsObj = {
								id : result[i].id,
								name : 	result[i].name,
								relativeName : result[i].desc,
								voterCardNo :result[i].voterCardNo,
								check : result[i].name+" - "+result[i].voterCardNo+" - "+result[i].relativeName+" - "+result[i].relationType+" - "+result[i].age+" - "+result[i].gender
							}
							searchedVoters.push(vtrsObj);
							searchedVotersInfoArr.push(result[i].name+" - "+result[i].voterCardNo+" - "+result[i].relativeName+" - "+result[i].relationType+" - "+result[i].age+" - "+result[i].gender);
					}
					
					$( "#cardNumber" ).autocomplete({ 
							source:searchedVotersInfoArr,
							select: function (event, ui) {
								$('#cardNumber').val(ui.item.value);	
								for(var k in searchedVoters){
									//console.log(searchedVoters);
									if(searchedVoters[k].check == ui.item.value){
										//$('#cardNo').val(searchedVoters[k].voterCardNo);
										$('#cardNumber').val(searchedVoters[k].voterCardNo);	
										break;
									}
								}
								
								return false;
							}
						});
						
						
				});
	}
	
});

function getCandidateDetailsById(constituencyId,electionId,nominationId )
{
var electionsId = $('#'+electionId+'').val();
	$('#candidateDivTab').html('');
	var jsObj = 
	   {
			electionId : electionsId,
			nominationId : nominationId,
			task:"getCandidateDetailsById"             
	   }				   
	   $.ajax({
			type : "POST",
			url : "getCandidateInfoByNominationAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			buildCandidateDetails(result,nominationId);
		});
}
var prvEleCount = 1;
function buildCandidateDetails(result,nominationId)
{
	var str ='';
	if(result != null)
	{
		str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab1" style="width:850px;">';
		str+='<tbody>';
		
		for(var i in result)
		{
			str+='<tr class="removeId'+i+'" >';
				str += '<input type="hidden" value=""></input>';
				str += '<input type="hidden" value='+result[i].orderId+' name="cadreRegistrationVO.previousParicaptedElectionsList['+prvEleCount+'].electionTypeId"></input>';
				str += '<input type="hidden" value='+result[i].mainAccountId+' name="cadreRegistrationVO.previousParicaptedElectionsList['+prvEleCount+'].constituencyId"></input>';
				str += '<input type="hidden" value='+nominationId+' name="cadreRegistrationVO.previousParicaptedElectionsList['+prvEleCount+'].candidateId"></input>';
				str +='<td>'+result[i].name+'</td>';
				str +='<td>'+result[i].location+'</td>';
				str +='<td>'+result[i].panchayatName+' ('+result[i].partno+')</td>';
				str +='<td> <a class="icon-minus-sign" href="javascript:{deleteDetails(\'removeId'+i+'\')}"></a></td>';
			str+='</tr>';
			prvEleCount++;
		}
		
		str+='</tbody>';
		str +='</table>';
		
		$('#candidateDivTab').html(str);
	}
}
function showHideFamRelatinoSts(){
   $("#cardErr").html("");
   if($('#relativeTypeChecked').prop('checked')==true){
     $('#showHideFammemberType').show();
   }else{
     $('#showHideFammemberType').hide();
   }
}
function getAllRelationDetails(){
	 $.ajax({
		type : "POST",
		url : "getAllRelationDetails.action"
	}).done(function(result){
	  if(result != null && result.length > 0){
	   //$('#relativeTypeId').append('<option value="0">Select Relation</option>');
	   for(var i in result){
		 $('#relativeTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	    }
	  }
	 
	  <s:if test="voterInfoVOList[0].relative != null">
	    $('#relativeTypeId').val('${voterInfoVOList[0].relationTypeId}');
		$('#relativeTypeChecked').attr("checked","checked");
		showHideFamRelatinoSts();
	  </s:if>
	});
}
getAllRelationDetails();
function deleteDetails(id)
	{
		if(confirm('Are you sure want to delete it? '))
		{
			$('.'+id).remove();
		}		
	}
function getCadreImage(id){
         $.ajax({
			type : "POST",
			url : "getCadreImageByPreviousEnrolId.action",
			data : {enrolmentId:id}
		}).done(function(result){
				if(result != null && result.length > 0){
				  $("#cadreActualImgLiId").html('<div style="width: 125px; padding-bottom: 15px;padding-top: 13px;" class="well  pad-5 m_top10"><span><img style="width: 140px; height: 120px;" src="'+result+'"></span><input type="checkbox" style="margin-top:-1px;" id="cadreActualImgId" onclick="hideVoterImg();" name="cadreUploadImgCadreType" class="m_top10"/><span style="color: #9a9a9a;font-weight: bold;">&nbsp;Use This Photo</span></div>');
				}
		});
}	
function hideVoterImg(){
  $("#voterActualImgId").removeAttr('checked');
  $("#newTakenImgId").removeAttr('checked');
}
function hideCadreImg(){
  $("#cadreActualImgId").removeAttr('checked');
  $("#newTakenImgId").removeAttr('checked');
}
function showNewTakenImg(){
  $("#cadreActualImgId").removeAttr('checked');
  $("#voterActualImgId").removeAttr('checked');
  $("#newTakenImgId").attr("checked","checked");
}
	function getCadreCommitteDetails( levelId,divId)
	{
		$('#'+divId+'').find('option').remove();
		$('#'+divId+'').append('<option value="0"> Select Committee </option>');
		
		var jsObj = 
		   {
				levelId : levelId,
				task:"getCadreCommitteDetails"             
		   }				   
		   $.ajax({
				type : "POST",
				url : "getCadreCommitteDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				for(var i in result)
				{
					$('#'+divId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
				}
			});
	}
	
	function clearExistingImg(id){
		$('#imageErr').html('');
		$("#"+id+"").html('');
		$("#"+id+"").html('<img style="width: 140px; height: 120px;" id="actuploadImg" src="images/mahaNadu/user image.jpg">');
		$("#newTakenImgId").removeAttr('checked');
		$("#base64Image").val("");
		newCamPhotoTaken = false;
	    newPhotoUploaded = false;
		document.getElementById("uploadFileId").value = "";
		
	}
	
	
	function getCadreCommitteRoles(committeeId,buildDivId,divId)
	{
		var levelId = $('#'+divId+'').val();
		
		$('#'+buildDivId+'').find('option').remove();
		$('#'+buildDivId+'').append('<option value="0"> Select Role </option>');
		
		
		var jsObj = 
		   {
				levelId : levelId,
				committeeId : committeeId,
				task:"getCadreCommitteRoles"             
		   }				   
		   $.ajax({
				type : "POST",
				url : "getCadreCommitteRolesAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				for(var i in result)
				{
					$('#'+buildDivId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
				}
			});
	}
	
	function isValid(str)
	{
		var flag = true;
		var iChars = "`~!@#$%^&*()_-+=}]{[\"':;|\?/><,";		 
		for (var i = 0; i < str.length; i++) 
		{
			if (iChars.indexOf(str.charAt(i)) != -1) 
			{			
				flag = false;
			}
		}
	return flag;
	}
	var request;	
	function searchCandidatesDetailsBySearchCriteria(fromDiv){
		
		var srchNameId = '#searchNameId';
		var srchNameCardId = '#searchVoterCardId';
		var hNoId = '#searchHNoId';
		var errDivId = '#errorDiv';
		var srchDtlsDivId = '#searchDetailsDiv';
		var tbleElmntId = '#tableElement';
		if(fromDiv == "family"){
			srchNameId = '#searchNameIdFmly';
			srchNameCardId = '#searchVoterCardIdFmly';
			hNoId = '#searchHNoIdFmly';
			errDivId = '#errorDivFmly';
			srchDtlsDivId = '#searchDetailsDivFmly';
			tbleElmntId = '#tableElementFmly';
		}
	
		var cosntiteucnyId = '${constiteucnyId}';
		var candidateName = $(srchNameId).val();
		var voterCardNo = $(srchNameCardId).val();
		var houseNo = $(hNoId).val();
		var searchType = "voter";
		var panchayatId = '${houseNo}';
		var boothId = '${boothId}';
		var isPresentCadre = '${panchayatId}';
				
		var isError = false ;
		
		if(candidateName != null && candidateName.trim().length>0 && !(/^[a-zA-Z ]+$/.test(candidateName)))
		{
				$(errDivId).html('Candidate Name allows only alphabets.');
			return;
		}
		  
		if(!isValid(candidateName))
		{
			$(errDivId).html('Special Characters not allowed for Candidate Name.');
			return ;
		}
		if(!isValid(voterCardNo))
		{
			var iChars = "`~!@#$%^&*()._-+=}]{[\"':;|\?/><,";		 
			for (var i = 0; i < voterCardNo.length; i++) 
			{
				if (iChars.indexOf(voterCardNo.charAt(i)) != -1) 
				{			
					$(errDivId).html('Special Characters not allowed for Voter Card No.');
				return ;
				}
			}
		}
	
		if(!isValid(houseNo))
		{
			var iChars = "`~!@#$%^&*()_+=}]{[\"':;|\?><,.";		 
			for (var i = 0; i < houseNo.length; i++) 
			{
				if (iChars.indexOf(houseNo.charAt(i)) != -1) 
				{			
					$(errDivId).html('Special Characters not allowed for House No.');
					return ;
				}
			}
		}
		
		if((voterCardNo == null || voterCardNo.length == 0) && (houseNo == null || houseNo.length == 0) && (candidateName == null || candidateName.length ==0))
		{
			$(errDivId).html('Enter any search criteria for details.');
			 isError = true ;
		}
		
		if(candidateName == null || candidateName.length <=2)
		{	
			if(voterCardNo != null && voterCardNo.length  >=3 )
			{
				 isError = false ;
			}
			else if(houseNo != null && houseNo.length >=3 )
			{						
				  isError = false ;
			} 
			else 
			{
				$(errDivId).html('Atleast 3 Characters required for Candidate Name.');
				isError = true ;	
			}		
		}
		else
		{
			 isError = false ;
		}

		if(!isError)
		{			
			$(errDivId).html('');
			 
			$(srchDtlsDivId).html('');
			$(tbleElmntId).hide();
			
			if(typeof(request) != "undefined")
			{
				request.abort();
			}
					
			$('#searchDataImg').show();
			$('#searchDataImg3').show();

	cosntiteucnyId = (cosntiteucnyId != null && cosntiteucnyId != null ? cosntiteucnyId = cosntiteucnyId:0);
	 candidateName = (candidateName != null && candidateName.length > 0 ? candidateName = candidateName:"");
	   voterCardNo = (voterCardNo != null && voterCardNo.length > 0 ? voterCardNo = voterCardNo:"");
	       houseNo = (houseNo != null && houseNo.length > 0 ? houseNo = houseNo:"");
	    searchType = (searchType != null && searchType.length > 0 ? searchType = searchType:"");
	   panchayatId = (typeof(panchayatId) != 'undefined' && panchayatId != null ? panchayatId = panchayatId:0);
	       boothId = ( typeof(boothId) != 'undefined' && boothId != null ? boothId = boothId:0);
		   
		 //console.log("panchayatId  :"+panchayatId);
		
			var jsObj = 
				   {
					  constituencyId:cosntiteucnyId,
					  searchType :searchType, 
					  candidateName:candidateName,
					  houseNo : houseNo,
					  voterCardNo : voterCardNo,
					  panchayatId : panchayatId,
					  boothId : boothId ,
					  isPresentCadre : isPresentCadre,
					  task:"searchCandidatesDtailsBySearchCriteria"             
				   }

				   
				   
				request =   $.ajax({
						type : "POST",
						url : "searchVoterAndCadreInfoBySearchCriteriaAction.action",
						data : {task:JSON.stringify(jsObj)} ,
					}).done(function(result){
						isSubmit = true;
						$('#searchDataImg').hide();
						$('#searchDataImg3').hide();
						if(result != null && result.length >0)
						{
							buildSearchDetails(result,fromDiv,searchType);
						}
						else
						{
							$(srchDtlsDivId).html('<div align="center">No Data Available...</div>');
							$(tbleElmntId).show();
						}
					});
		}
			
	}
	
	function buildSearchDetails(result,fromDiv,searchType)
	{

		var tableName = "seachDetalsTab";
		if(fromDiv == "family"){
			tableName = "searchDetailsTab1";
		}
	
	var str = '';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id=tableName>';
			str +='<thead>';
			str +='<tr>';
			str +='<th class="text-align1"></th>';
			str +='<th class="text-align1">PHOTO</th>';
			str +='<th class="text-align1">NAME</th>';
			str +='<th class="text-align1">GUARDIAN NAME</th>';
			str +='<th class="text-align1">RELATION</th>';
			str +='<th class="text-align1">AGE</th>';
			str +='<th class="text-align1">GENDER</th>';
			str +='<th class="text-align1">H.NO</th>';
			//str +='<th class="text-align1"></th>';
			str +='</tr>';
			str +='</thead>';
			str +='<tbody>';
			
			for(var i in result)
			{
			if(result[i].isRegistered == 'Y')
			{
				str +='<tr>';
				if(fromDiv == "family"){
					str +='<td  style="background-color: #f9f9f9;"><input type="checkbox" class="votersCB" name="voters" onclick="updateFamilyVtrId(\''+result[i].voterCardNo+'\',\''+result[i].voterId+'\')"></span>';
				}else{
					str +='<td  style="background-color: #f9f9f9;"><input type="checkbox" class="votersCB" name="voters" onclick="updateText(\''+result[i].voterId+'\')"></span>';
				}
				
				
				str +='<td  style="background-color: #f9f9f9;"><img style="width:80px;height:80px;" src="'+result[i].image+'" class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'" id="candimgShowIdReg'+i+'" onerror="setDefaultImage(this);" /></td>';
				if(result[i].name != null)
					str +='<td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].name+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
					
				if(result[i].relativeName != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				if(result[i].relationType != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';					
				if(result[i].age != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].age+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				if(result[i].gender != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].gender+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'"> -- </span></td>';
				
				if(result[i].houseNo != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				
				//str +='<td style="background-color:#52A552;"><input type="radio" value="'+result[i].id+'" name="optionsRadios" onClick="getDetailsForUser();"></label></td>';
				str +='</tr>';
			}
			else
			{
				str +='<tr>';
				if(fromDiv == "family"){
					str +='<td  style="background-color: #f9f9f9;"><input type="checkbox" class="votersCB" name="voters" onclick="updateFamilyVtrId(\''+result[i].voterCardNo+'\',\''+result[i].voterId+'\')"></span>';
				}else{
					str +='<td  style="background-color: #f9f9f9;"><input type="checkbox" class="votersCB" name="voters" onclick="updateText(\''+result[i].voterId+'\')"></span>';
				}
				str +='<td style="background-color: #f9f9f9;"><img style="width:80px;height:80px;" class="detailsCls"  key="'+result[i].id+'" reqSearchType="'+searchType+'"  src="'+result[i].image+'" id="candimgShowId'+i+'" onerror="setDefaultImage(this);" /></td>';
				if(result[i].name != null)
					str +='<td style="cursor:pointer;"><span  class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'">'+result[i].name+'</span></td>';
				else
					str +='<td><span  class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'"> -- </span></td>';
					
				if(result[i].relativeName != null)
					str +='<td><span  class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				else
					str +='<td><span  class="detailsCls" reqSearchType="'+searchType+'"  key="'+result[i].id+'"> -- </span></td>';
				if(result[i].relationType != null)
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				else
					str +='<td><span  class="detailsCls" reqSearchType="'+searchType+'"  id="'+result[i].id+'"> -- </span></td>';					
				if(result[i].age != null)
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].age+'</span></td>';
				else
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				if(result[i].gender != null)
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].gender+'</span></td>';
				else
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				
				if(result[i].houseNo != null)
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				else
					str +='<td><span  class="detailsCls"  reqSearchType="'+searchType+'" key="'+result[i].id+'"> -- </span></td>';
				
			//	str +='<td><input type="radio" value="'+result[i].id+'" name="optionsRadios" onClick="getDetailsForUser();"></label></td>';
				str +='</tr>';
			}
			}
			
			str +='</tbody>';
			str +='</table>';
			if(fromDiv =="family"){
				$('#searchDetailsDivFmly').html(str);
				$('#tableElementFmly').show();
			}else{
				$('#searchDetailsDiv').html(str);
				$('#tableElement').show();
			}
			
		 $(tableName).dataTable({
			"iDisplayLength": 100,
			"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
			
		/*	
			$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		  
		   $('input[name="optionsRadios"]').on('ifClicked', function (event) {
				//alert("You clicked " + this.value);
				getDetailsForUser(this.value);
				
			});
			*/
			 $(".detailsCls").click(function(){
			var id = $(this).attr('key');
			var reqSearchType =  $(this).attr("reqSearchType");	
			getDetailsForUser(id,reqSearchType);
		  
		  });
	}
	
	
	function nomineeUpdate(voterSerialId,keyId)
	{
		  
		$('.nomineeCls').each(function(){
			var id = $(this).attr('id');
			$("#"+id+"").removeClass("icon-remove");
			
			if(id != voterSerialId)
			{				
				$("#"+id+"").removeClass("icon-ok");
				$("#"+id+"").removeClass("label-info");
				$("#"+id+"").addClass("icon-remove");
			}
			else
			{
				$("#"+id+"").addClass("icon-ok");
				$("#"+id+"").addClass("label-info");
			}
			
		});
		
			$('#nomineNameId').val('');
			$('#nomineeGenderId').val(0);
			$('#nomineeAgeId').val('');
			$('#nomineAadharId').val('');
			$('#voterRelationId').val(0);
			
			var name = $('#voterName'+keyId+'').val();
			var genderName = $('#voterGender'+keyId+'').val();
			var age = $('#voterAge'+keyId+'').val();
			var gender = 0;
			if(genderName == 'M' || genderName == 'm')
			{
				gender = 1;
			}
			else if(genderName == 'F' || genderName == 'f')
			{
				gender = 2;
			}
			
			$('#nomineNameId').val(name);
			$('#nomineeGenderId').val(gender);
			$('#nomineeAgeId').val(age);
		
	}
	
	function isValidName(type)
	{
		$('#NnameErr,#NageErr').html('');
		var candidateName = $('#nomineNameId').val();
		var candidateAge = $('#nomineeAgeId').val();
		if(type =='name' && candidateName != null && candidateName.trim().length>0 && !(/^[a-zA-Z ]+$/.test(candidateName)))
		{
				$('#NnameErr').html('Name allows only alphabets.');
			return true;
		}
		if(type =='number' && candidateAge != null && candidateAge.trim().length>0 && (/^[a-zA-Z ]+$/.test(candidateAge)))
		{
				$('#NageErr').html('Nominee Age must be Number.');
			return true;
		}
		return false;
	}
	
	
	function getDetailsForUser(candidateId,reqSearchType)
	{
		//var candidateId = $('input[name="optionsRadios"]:checked').val();
		//var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var cosntiteucnyId = '${constiteucnyId}';	
		var boothId = '${boothId}';	
		var houseNo = '${houseNo}';	
		
		var panchayatId = 0;
		
			
		window.open('tdpCadreRegistrationAction.action?candidateId='+candidateId+'&searchType='+reqSearchType+'&constiteucnyId='+cosntiteucnyId+'&houseNo='+houseNo+'&boothId='+boothId+'&panchayatId='+panchayatId+'');
		
	}
	function setDefaultImage(img)
	{
		img.src = "images/mahaNadu/user image.jpg";
	}
	
	function updateText(vCardNo){
		var cosntiteucnyId = '${constiteucnyId}';
		var candidateName = $('#searchNameId').val();
		var voterCardNo = $('#searchVoterCardId').val();
		var houseNo = $('#searchHNoId').val();
		var searchType = "voter";
		var panchayatId = '${houseNo}';
		var boothId = '${boothId}';
		var isPresentCadre = '${panchayatId}';
		var url = "tdpCadreRegistrationAction.action?candidateId="+vCardNo+"&searchType=voter&constiteucnyId="+cosntiteucnyId+"&houseNo="+panchayatId+"&boothId="+boothId+"&panchayatId="+isPresentCadre+"";
		window.location.href = url;
		
		//$('#myModal').dialog('close');
		//$("#familyVtrCrdId").val(vCardNo);
	}
	
	function updateFamilyVtrId(vCardNo,vCardId){
		$('#myModal2').dialog('close');
		$("#familyVtrCrdId").val(vCardNo);
		$("#fmlyVtrId").val(vCardId);
	}
	
	function updateEnrollmentNo(enrollmentNo){
		$('#myModal1').dialog('close');
		$("#preEnrollNoValue").val(enrollmentNo);
		$('#preEnrollNo').val(enrollmentNo);
	}
	
</script>
<script>
function getExistingCadreInfo1(){
    var errDivId = "#errorDiv1";
	$('#searchDetailsDiv1').html('');
	$('#tableElement1').hide();
	$('#preEnrollNo').val('');
	$(errDivId).html('');
	
	var candidateName = $('#candiNameId').val();
	var enrollmentNo = $('#enrollmentNoId').val();
	var constituencyId = '${constiteucnyId}';
	var panchayatId = '${houseNo}';  // panchayat Id 
	var boothId = '${boothId}';  // boothId Id 
	var isPresentCadre = '${panchayatId}';  // ispresentCader checked ot not 
	
	var isError = false;
	if((candidateName == null || candidateName.length == 0) && (enrollmentNo == null || enrollmentNo.length == 0))
		{
			$(errDivId).html('Enter any search criteria for details.');
			 isError = true ;
		}
		
		if(candidateName == null || candidateName.length <=2)
		{	
			if(enrollmentNo != null && enrollmentNo.length  >=3 )
			{
				 isError = false ;
			}
			else 
			{
				$(errDivId).html('Atleast 3 Characters required for Candidate Name.');
				isError = true ;	
			}		
		}
		else
		{
			 isError = false ;
		}
	if(!isError){
	$('#searchDataImg1').show();
		var jsObj = {	
			name : candidateName,
			constituencyId : constituencyId,
			panchayatId : panchayatId,
			boothId : boothId,
			isPresentCadre:isPresentCadre,
			enrollmentNumber : enrollmentNo,
			task:"getExistingCadreInfo"             
		}
			   
		$.ajax({
			type : "POST",
			url : "getExistingCadreInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		$('#searchDataImg1').hide();
			buildExistingCadres(result);
		});
	}
}	

 
 function validateName(errDiv,fieldid,isNumber)
 {
	var str = $('#'+fieldid+'').val();
	$('#'+errDiv+'').html('');
	var reqErstatus = false;
	if(isNumber >0)
	{
		if(str != null && str.trim().length>0 && !(/^[a-zA-Z ]+$/.test(str)))
		{
			$('#'+errDiv+'').html('Allows only alphabets.');
			reqErstatus = true;
		}		  
		if(!isValid(str))
		{
			reqErstatus = true;
			$('#'+errDiv+'').html('Special Characters are not allowed.');
		} 
	}
	else
	{
		$('#ageErr').html('');
		if (str.length == 0) 
		{
			reqErstatus = true;
			$('#'+errDiv+'').html('Please Enter Candidate Age');
			$('#cadreAgeId').val('');
		}		 
		else if (isNaN(str)) 
		{
			 reqErstatus = true;
			$('#'+errDiv+'').html('Candidate Age must be number.');	
			$('#cadreAgeId').val('');			
		}
	}
	return reqErstatus;
 }
 
	function buildExistingCadres(results){
			$('#searchDetailsDiv1').html("");
			$('#tableElement1').show();
		if(results==null){

			$('#searchDetailsDiv1').html("<h4 style='text-align:center;'> No Data Available </h4>");	
			return;
		}
		var str = '';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab1">';
				str +='<thead>';
					str +='<tr>';
						str +='<th class="text-align1"></th>';
						str +='<th class="text-align1">NAME</th>';
						str +='<th class="text-align1">GUARDIAN NAME</th>';
						str +='<th class="text-align1">ENROLLMENT NO</th>';
					str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in results){
					str +='<tr>';
						str +='<td class="text-align1"><input type="checkbox" name="voters" onclick="updateEnrollmentNo(\''+results[i].caste+'\')"></th>';
						str +='<td>'+results[i].name+'</td>';
						str +='<td>'+results[i].desc+'</td>';
						str +='<td>'+results[i].caste+'</td>';
					str +='</tr>';
				}
				str +='</tbody>';
			str +='</table>';
			
			$('#searchDetailsDiv1').html(str);
			
		 
			$('#seachDetalsTab').dataTable({
				"iDisplayLength": 100,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
	
	
}
function showTakeImage(){
  $('#wrapper').dialog({
            autoOpen: true,
			width:600,
			title:"Take Image",
            modal: true,
			resizable: false
        });
}
$(document).ready(function(){
	$("#wrapper").hide();
});

	if(srchType=="voter" && candiId =="0"){
		$(".famlyMemClsDiv").css("display","block");
	}else if(srchType=="voter" && candiId !="0"){
		$(".famlyMemClsDiv").css("display","none");
	}else{
		$(".famlyMemClsDiv").css("display","block");
	}
	
	function updateNameSelection(id)
	{
		$('#'+id+'').removeAttr('checked');		
	}
	function clearPreviousEnrol(){
		$("#preEnrollNoValue").val("");
	}
	function clearSelDiv(selId){
	 $("#"+selId).val("");
	}
		
	function deleteRecordsForm()
	{
		var removeVoterArr = new Array();
		$('.removeCls').each(function(){
			var id = $(this).attr('id');
			if ($('#'+id+'').is(':checked')) 
			 {
				$('.'+id+'').remove();
			 }
		});

	}
	
	function isValidMailId()
	{
		var emailId = $.trim($("#emailId").val());
		var emailreg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		$("#emailErr").html('');
		if(emailId != null && emailId.trim().length > 0 && emailreg.test(emailId) == false)
		{
			$("#emailErr").html('Invalid Email Id.');
		}
	}
</script>
</html>