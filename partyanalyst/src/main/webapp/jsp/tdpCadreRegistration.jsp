<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tdp Cadre Registration</title>

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

	<!-- YUI Dependency files (End) -->
	
	
    <script>$('#yourElement').addClass('animated fadeInDown');</script>
    <script>$('#fadeInLeft').addClass('animated fadeInLeft');</script>
    <script>$('#fadeInRight').addClass('animated fadeInRight');</script>
    <script>$('#fadeInUp').addClass('animated fadeInUp');</script>
    <script>$('#fadeInUp1').addClass('animated fadeInUp');</script>


	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db !important;}
	 
	 .datePickerCls{
			 cursor: text !important;
			}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	</style>
	
		
	<script>
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
	var participationCount = 0;
		<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
					<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
						participationCount = participationCount + 1;
					</c:forEach>
		</s:if>

	var isRolesCount = 0 ;
		
		<s:if test="%{voterInfoVOList[0].previousParticipationInfoList != null && voterInfoVOList[0].previousParticipationInfoList.size() > 0}">
			<c:forEach var="role" items="${voterInfoVOList[0].previousParticipationInfoList}" varStatus="indexValue">
				isRolesCount = isRolesCount+1;
			</c:forEach>
		</s:if>	
	</script>
	
   	<script>
	
	var constituencyId = '${constiteucnyId}';
	var panchayatId    = '${constiteucnyId}';
	var boothId 	   = '${boothId}';
	$(document).ready(function(){
	    $('.datePickerCls').datepicker({
		dateFormat: 'dd-mm-yy',
		maxDate: new Date(),
		changeMonth: true,
		changeYear: true,
		yearRange: "-100:+0"
	  });
		
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
		
		
		prepopulateOptions();
		//prepopulateElctionOptions();
		$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		 
	});

	var rolesSize = 1;
	var isRolesSet = true;
	function createNewForm()
	{
		if(participationCount >= 1 &&  isRolesSet)
		{
			rolesSize = participationCount;
			isRolesSet = false;
		}
		
		var str = '';
		str += '<div class="row rolesList'+rolesSize+'" style="margin-top:-15px;">';
		str += '<div class="span3">';
		str += '<div class=" text-align1 " >';
		//str += '<h5 class="text-align1">Select Level </h5>';
		str += '<select class="form-control border-radius-0" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].designationLevelId">';
		str += '<option value = "0"> Select Level</option>';
			if(cadreLevelArr != null && cadreLevelArr.length>0)
			{
				for(var i in cadreLevelArr)
				{
					str += '<option value = "'+cadreLevelArr[i].id+'">'+cadreLevelArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" text-align1" >';
		//str += '<h5 class="text-align1">Party Designation </h5>';
		str += '<select class="form-control border-radius-0 " name="cadreRegistrationVO.previousRollesList['+rolesSize+'].designationLevelValue">';
		str += '<option value = "0"> Select Designation </option>';
			if(partyDesignationArr != null && partyDesignationArr.length>0)
			{
				for(var i in partyDesignationArr)
				{
					str += '<option value = "'+partyDesignationArr[i].id+'">'+partyDesignationArr[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">From Date</h5>';
		str += '<div class="input-prepend text-align2 ">';
		str += '<input type="text" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].fromDateStr"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2 ">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">To Date</h5>';
		str += '<div class="input-prepend  ">';
		str += '<input type="text" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].toDateStr"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		/*str += '<div class="span2">';
		str += '<a onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" class="btn btn-success"></a>';
		str += '<div>';*/
		str += '<div class="span2 ">';
		str += '<div class=" " >';
		str += '<div class="input-prepend text-align2 ">';	
		str += '<a class="icon-minus-sign" style="float:right" onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" title="Remove Details"></a>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		
		rolesSize++;
		//alert(rolesSize);
		
		$('#rollesDiv').append(str);
		//$('.datePickerCls').datepicker({dateFormat: 'dd-mm-yy',minDate: '01-01-1900',maxDate: new Date()});
		
		 $('.datePickerCls').datepicker({
		dateFormat: 'dd-mm-yy',
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
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					console.log(uploadResult);
					showUploadStatus(uploadResult);	
				}
			};

		YAHOO.util.Connect.setForm('uploadCadreForm',true);
		YAHOO.util.Connect.asyncRequest('POST','tdpCadreSaveRegistrationAction.action',uploadHandler);
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
			str+= '<h3 class="text-align">Successfully Registration Completed</h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Enrollment No :'+resultArr[1]+' </p>';
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
	function prepopulateOptions()
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
		var photoElmt = document.getElementById("uploadFileId");
		var file = photoElmt.files[0];
		var reader = new FileReader();
		reader.onloadend = handleReaderLoadEnd;
		reader.readAsDataURL(file);
	}
	function handleReaderLoadEnd(evt)
	{
		var img = document.getElementById("actuploadImg");
		img.src = evt.target.result;
		evt=null;
	} 
	var eletionCont = 1;
	var isRolesCountSet = true;
	function createNewFormForElections()
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
		str += '<select id="electionTypeId'+eletionCont+'" style="margin-left: 12px;" onChange="getElectionYears(this.value,\'electionYears'+eletionCont+'\')">';
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
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Year</h5>';
		str += '<select  id="electionYears'+eletionCont+'" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].electionTypeId" >	';	
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Constituency</h5>';
		str += '<select class="" name="cadreRegistrationVO.previousParicaptedElectionsList['+eletionCont+'].constituencyId" style="margin-left: 12px;">';
		str += '<option value = "0"> Select Constituency </option>';
			if(constituencyArray != null && constituencyArray.length>0)
			{
				for(var i in constituencyArray)
				{
					str += '<option value = "'+constituencyArray[i].id+'">'+constituencyArray[i].name+'</option>';
				}
			}
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<div class=" " >';
		//str += '<h5 class="text-align1">Add More</h5>';
		str += '<a class="icon-minus-sign" style="float:left;" onClick="deleteRollesForm(\'electionsList'+eletionCont+'\')" title="Remove Details"></a>';
		str += '</div>';
		str += '</div>';
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
	str +=' <td>  <input id="countVar" type="hidden" value="'+voterCount+'">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Name " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterName" ></input> ';
	str +=' <input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterId"></input> ';
	str +=' </td>';
	str +=' <td> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Enter Voter Id " name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].voterCadreNO"  style="width:120px;"></input>';
	str +=' </td>';
	str +=' <td style="width:50px;"> ';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder=" Age " style="width:50px;"></input> ';
	str +=' </td>';
	str +=' <td style="width:50px;">';
	str +=' <input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender"  style="width:50px;"> </input>';
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

	str +='<input type="text" id="familyOccupation'+voterCount+'" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Enter Occupation "  style="width:150px;"  value=""> </input>';												 
	str +='<input type="hidden"  id="familyOccupation'+voterCount+'0" name="cadreRegistrationVO.cadreFamilyDetails['+voterCount+'].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>';

	str +=' </select>';
	str +=' </td>';
	str +='<td style="width:15px;"> ';
		str +='<a class="icon-minus-sign" style="float:right;margin-top:-10px;" onClick="deleteRollesForm(\'voterDev'+voterCount+'\');" title="Remove Details"></a>';
	str +='</td>';
	str +=' </tr>';

	
	$('#addVotersDiv').after(str);
	
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
		
	
	}
	
	
	function isNumber()
	{
		var mobileNumber = $('#mobileNumberId').val();
		$('#mobileErr').html('');
		 if (isNaN(mobileNumber)) 
		  {
			$('#mobileErr').html('Invalid Mobile No.');
			return false;
		  }		 
	}
	
	function getConstiteuncyListForElection(eletionId,constiListId)
	{
			var jsObj = 
			   {			
				  electionId : eletionId,
				  task       : "getConstiteuncyListForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstiteuncyListForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){

					$('#'+constiListId+'').find('option').remove();
					$('#'+constiListId+'').append('<option value="0"> Select Constituency </option>');
					if(result != null)
					{
						for(var i in result)
						{
							$('#'+constiListId+'').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}					
				});
	}
	
	function getCandidateDetailsForElection(candidateId,electionId)
	{
		var electionValue = $('#'+electionId+'').val();
			var jsObj = 
			   {			
				  electionId : electionValue,
				  candidateId : candidateId,
				  task          : "getCandidateDetailsForElection"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getCandidateDetailsForElectionAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					console.log(result);
					
				});
	}
	
	</script>

</head>
  <body class="bgc">
		<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<img src="images/cadre_images/2014-cadre-Registration-Logo.png" />
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
					<div class="span6   show-grid"  id="fadeInLeft">
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${voterInfoVOList[0].voterId}" > 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${constiteucnyId}" name="cadreRegistrationVO.constituencyId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value="${panchayatId}" name="cadreRegistrationVO.panchayatId"> 
					
					<input type="hidden" class="form-control border-radius-0 text-align2" value = "${boothId}" name="cadreRegistrationVO.boothId" > 
											<h5 class="text-align1"> CANDIDATE NAME </h5>
											<input type="text" class="form-control border-radius-0 text-align2" placeholder="Candidate Name" name="cadreRegistrationVO.voterName"  value="${voterInfoVOList[0].name}"></input>
									<div class="row-fluid">
										<div class="span7">
											<h5 class="text-align1">DATE OF BIRTH</h5>
												
												<div class="input-prepend text-align2 ">
													
													<input type="text" class="datePickerCls" name="cadreRegistrationVO.dobStr" value="${voterInfoVOList[0].dateOfBirth}" placeholder="Date of Birth"></input>
													</div>
													
													<h5 class="text-align1">GENDER</h5>	 
												<div class="row-fluid form-inline" style="margin-left:5px;">
												<s:if test="%{voterInfoVOList[0].gender != null}">
												
													<c:if test="${voterInfoVOList[0].gender == 'M' || voterInfoVOList[0].gender == 'Male'}">
													   <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" checked="true" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender"> FEMALE</input></label>
													</c:if>
													<c:if test="${voterInfoVOList[0].gender == 'F' || voterInfoVOList[0].gender == 'Female'}">												
														<label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" > MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender"  checked="true"> FEMALE</input></label>
													</c:if>
												
												</s:if>
												<s:else>
												  <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" > MALE</input></label>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender" > FEMALE</input></label>
												</s:else>
														
												</div>			
										</div>
								
								
										<div class="span4  m_top10">
											<div class="well  pad-5">
												<!--<img src="user.jpg" class="img-responsive" />-->
												<span id="uploadImg"><img  style="width: 140px; height: 120px;" id="actuploadImg" src="images/mahaNadu/user image.jpg"></span>
												<input type="file" style="width: 79px;margin-left: 10px;" id="uploadFileId" onchange="changeImg();" name="cadreRegistrationVO.uploadImage" class="m_top10">
												<!--<button class="btn btn-primary btn-xs btn-block border-radius-0 m_top10 " type="button" >Upload Photo </button>-->
											</div>
										</div>
								
									</div>
										
										
								<div class="m_top10">
									<div class="row-fluid">
										
										<div class="span6">
										<h5 class="text-align1">GUARDIAN NAME</h5>
										<input type="text" class="form-control border-radius-0 text-align2" placeholder="Guardian Name" name="cadreRegistrationVO.relativeName"   value="${voterInfoVOList[0].relativeName}"></input>
										</div>
										<input type="hidden" value="${voterInfoVOList[0].voterId}" name="cadreRegistrationVO.voterId"></input>
										<div class="span6">
										<h5 class="text-align1">Age</h5>
											<input type="text" class="form-control border-radius-0 " placeholder="Age" name="cadreRegistrationVO.age"   value="${voterInfoVOList[0].age}"></input>
										</div>
									</div>
								</div>
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">VOTER ID</h5>
												<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"   value="${voterInfoVOList[0].voterCardNo}"></input>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo"   value="${voterInfoVOList[0].houseNo}"></input>
											</div>
										</div>
								</div>	
								
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">PARTY MEMBER SINCES</h5>
												<input type="text" class="form-control border-radius-0 text-align2 datePickerCls" placeholder="Party Member Since " name="cadreRegistrationVO.partyMemberSinceStr"  value="${voterInfoVOList[0].activeDate}"></input>
											</div>
											
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
					<div class="span6 show-grid pad-10b" id="fadeInRight"  >
					
								<div class=" m_top20" >
										<h5 class="text-align1">STREET/HAMLET</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" placeholder=" Street / Hamlet " name="cadreRegistrationVO.street"  value="${voterInfoVOList[0].location}" style="width:260px;"></input>
								</div>	
							<div class=" m_top20" >
										<h5 class="text-align1">CASTE NAME</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" id="casteIdValue" placeholder=" Caste Name "   value="${voterInfoVOList[0].casteName}" style="width:260px;"></input>
										
										<input type="hidden" class="form-control border-radius-0  input-block-level" id="casteId" placeholder="Enter Caste" name="cadreRegistrationVO.casteId"  value="${voterInfoVOList[0].casteId}" style="width:260px;"></input>
										
							</div>
							<div class=" m_top20" >
										<h5 class="text-align1">MOBILE NUMBER  <span id="mobileErr" style="color:red;font-size:12px;"></span> </h5>
										<input type="text" id="mobileNumberId" class="form-control border-radius-0 input-block-level" placeholder=" Mobile Number "  name="cadreRegistrationVO.mobileNumber"  value="${voterInfoVOList[0].mobileNo}" style="width:260px;" maxlength="10" onKeyup="isNumber()"></input>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">EDUCATION</h5>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:260px;" name="cadreRegistrationVO.educationId" value="%{voterInfoVOList[0].education}"/>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">OCCUPATION</h5>

							<input type="hidden" class="form-control border-radius-0 input-block-level"  id="occupationId" placeholder="Occupation" name="cadreRegistrationVO.occupationId" value="${voterInfoVOList[0].occupationId}" style="width:260px;"></input>
							
							<input type="text" class="form-control border-radius-0 input-block-level"  id="occupationValue"  placeholder=" Enter Occupation "  value="${voterInfoVOList[0].occupation}" style="width:260px;"></input>
							
							</div>
							<div class=" m_top20" >
									<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
									<input type="text" id="preEnrollNoValue" class="form-control border-radius-0 input-block-level" placeholder="Previous Enrollment No."  value="${voterInfoVOList[0].memberShipId}" style="width:260px;" onkeyup="getExistingCadreInfo()"></input>
									
									<input type="hidden" id="preEnrollNo" class="form-control border-radius-0 input-block-level" placeholder="Text input" name="cadreRegistrationVO.previousEnrollmentNumber" value="${voterInfoVOList[0].memberShipId}" style="width:260px;" ></input>
									
							</div>
						
					</div>
				</div>
		
		</div>
	</div>
	<!----  srishailam starts  -->
	
		<div class="span12" >
				<div class="row-fluid">
					<div class="span  offset3 show-grid m_top10"  id="fadeInLeft" style="margin-left:200px;margin-bottom:10px;">						
						<div class="m_top10">
						<div class="span12 " style="position: relative;">
							<h3 class="text-align "> FAMILY DETAILS </h3>
						</div>
								<div class="row-fluid">		
											<table>
											<thead>
											<tr>
												<th> VOTER NAME </th>
												<th  style="width:120px;"> VOTER CARD NO</th>
												<th  style="width:50px;"> AGE </th>
												<th  style="width:50px;"> GENDER </th>
												<th  style="width:100px;"> EDUCATION  </th>
												<th  style="width:100px;"> OCCUPATION </th>
												<th></th>
													
							<s:if test="%{voterInfoVOList[0].voterInfoVOList != null && voterInfoVOList[0].voterInfoVOList.size() > 0}">
											</tr>
											</thead>
											<tbody>		
											
									<c:forEach var="familyVO" items="${voterInfoVOList[0].voterInfoVOList}" varStatus="commentLoop">
									
										<input type="hidden" value="${commentLoop.index}" id="countVar"></input>
											<tr>
												<td>   
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Name " value="${familyVO.name}" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterName"></input> 
													<input type="hidden" class="form-control border-radius-0 text-align2" value="${familyVO.voterId}"  name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterId"></input> 
												</td>
												<td> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Card No " value="${familyVO.voterCardNo}"name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].voterCadreNO" style="width:120px;"></input>
												</td>
												<td style="width:50px;"> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Age " value="${familyVO.age}" style="width:50px;"></input> 
												</td>
												<td style="width:50px;">
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender " value="${familyVO.gender}" style="width:50px;"> </input>
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
												
													 <input type="text" id="familyOccupation${commentLoop.index}" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder=" Occupation "  style="width:150px;"  value="${familyVO.occupation}"> </input>
													 
													 <input type="hidden"  id="familyOccupation${commentLoop.index}0" name="cadreRegistrationVO.cadreFamilyDetails[${commentLoop.index}].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value="${familyVO.occupationId}"> </input>
													 
													 
												</td>
												<td style="width:15px;"> 
													
												</td>
											</tr>
											
									</c:forEach>
									</tbody>									
								</table>
								
								<a class="icon-plus-sign" style="float:right;margin-right:50px;margin-top:-30px;" onClick="addMoreVoters();"  title="Add More Voter Details"> </a>
									<div  id="addVotersDiv"></div>
									
								</s:if>
								<s:else>
											
											</tr>
											</thead>
											<tbody>	
										<input type="hidden" value="0" class="countVar"></input>
											<tr id="addVotersDiv">
												<td> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Voter Name "  ></input> 
													<input type="hidden" class="form-control border-radius-0 text-align2"  name="cadreRegistrationVO.cadreFamilyDetails[0].voterId"  ;"></input> 
												</td>
												<td> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Voter Id " name="cadreRegistrationVO.cadreFamilyDetails[0].voterCadreNO"  style="width:120px;"></input>
												</td>
												<td style="width:50px;"> 
													<input type="text" class="form-control border-radius-0 text-align2" placeholder=" Age " style="width:50px;"></input> 
												</td>
												<td style="width:50px;">
													<input type="text" class="form-control border-radius-0 text-align2" placeholder="Gender"  style="width:50px;"> </input>
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
													<input type="text" id="familyOccupation0" class="familyOccupationsCls form-control border-radius-0 text-align2" placeholder="  Occupation "  style="width:150px;"  value=""> </input>
													 
													<input type="hidden"  id="familyOccupation00" name="cadreRegistrationVO.cadreFamilyDetails[0].occupationId" class="form-control border-radius-0 text-align2" placeholder="Enter Occupation " style="width:50px;" value=""> </input>
												</td>
												<td style="width:15px;"> 
													<a class="icon-plus-sign" style="float:right;margin-top:-30px;" onClick="addMoreVoters();"  title="Add More Voter Details"></a>
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
	<!----  srishailam end  -->
	
	
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">ACCIDENTAL INSURANCE DETAILS</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;" id="accDiv">
				<div class="span2">
					<h5 class="text-align1"> Aadheer Number </h5>
					<input type="text" class="" style="width: 138px;" placeholder="Aadheer Number"  name="cadreRegistrationVO.aadheerNo"></input> 
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Nominee Name </h5>
					<input type="text" class="" style="width: 138px;"placeholder="Nominee Name"  name="cadreRegistrationVO.nomineeName"></input>
				</div>
				
				
				
				<div class="span2">
					<h5 class="text-align1"> Gender </h5>
					<select name="cadreRegistrationVO.nomineeGender" style="width: 138px;">
							<option value="0">Select Gender</option>	
							<option value="1">Male</option>
							<option value="2">Female</option>
					</select>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Age </h5>
					<input type="text" class=""  style="width: 100px;" placeholder="Nominee Name"  name="cadreRegistrationVO.nomineeAge"></input>
				</div>
				
				<div class="span2">
					<h5 class="text-align1"> Relation Type </h5>
					<select name="cadreRegistrationVO.voterRelationId" style="width:160px;">
							<option value="0">Select Relation</option>	
							<option value="1">Father</option>
							<option value="2">Mother</option>
							<option value="3">Wife</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUS ROLES PARTICIPATED IN PARTIES</h3>
			</div>
		</div>
		<div class="container m_top10">
			
			<div class="span12 show-grid" style="position: relative;" id="rollesDiv">	
				<!--<div class="row">
					<a class="btn btn-success" style="float:right" onClick="createNewForm();">Add More</a>
				</div>-->
				<s:if test="%{voterInfoVOList[0].cadreRolesList != null && voterInfoVOList[0].cadreRolesList.size() > 0}">
					<c:forEach var="participation" items="${voterInfoVOList[0].cadreRolesList}" varStatus="indexValue">

					<div class="row rolesList">
					<div class="span3">
							<div class=" " >
							<c:if test="${indexValue.index == 0}">	
								<h5 class="text-align1"> Select Level </h5>
							</c:if>
							<select  name="cadreRegistrationVO.previousRollesList[${indexValue.index}].designationLevelId" id="cadreLevelId" style="margin-left: 12px">
								<option value="0">Select Level</option>
								<c:forEach var="educationList" items="${eletionTypesList}" >															
									<c:if test="${educationList.id == participation.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != participation.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
													 
													 
							</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Party Designation </h5>
						</c:if>
								 
							<select  name="cadreRegistrationVO.previousRollesList[${indexValue.index}].designationLevelValue" id="partyDesignationId" style="margin-left: 12px">
								<option value="0">Select Level</option>
								<c:forEach var="educationList" items="${selectOptionVOList}" >															
									<c:if test="${educationList.id == participation.count }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != participation.count }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
							
						</div>
		
					</div>
		
					<div class="span2">
						<div class=" " >

						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">From Date </h5>
						</c:if>
								<div class="input-prepend text-align2 ">				
									<input type="text" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].fromDateStr" value="${participation.startTime}" placeholder="From Date"></input></span>
								</div>
						</div>
					</div>
					<div class="span2 ">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">To Date </h5>
						</c:if>
								<div class="input-prepend  ">	
									<input type="text" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].toDateStr"  value="${participation.endTime}" placeholder="To Date"></input></span>
								</div>
						</div>
					</div>
					
				</div>
					</c:forEach>
						<a class="icon-plus-sign" style="float: right;margin-right: 120px;margin-top:-30px;" onClick="createNewForm();" title="Add More Details"></a>
			</s:if>
			<s:else>
				<div class="row rolesList">
					<div class="span3">
							<div class=" " >
								<h5 class="text-align1"> Select Level </h5>
									<select class="" name="cadreRegistrationVO.previousRollesList[0].designationLevelId" id="cadreLevelId" style="margin-left: 12px">
									 </select>
							</div>
					</div>
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Party Designation </h5>
								<select class="" name="cadreRegistrationVO.previousRollesList[0].designationLevelValue" id="partyDesignationId" style="margin-left: 12px">								
								  </select>
						</div>
		
					</div>
		
					<div class="span2">
						<div class=" " >
							<h5 class="text-align1">From Date</h5>
								<div class="input-prepend text-align2 ">				
									<input type="text" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[0].fromDateStr" placeholder="From Date"></input></span>
								</div>
						</div>
					</div>
					<div class="span2 ">
						<div class=" " >
							<h5 class="text-align1">To Date</h5>
								<div class="input-prepend  ">	
									<input type="text" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[0].toDateStr" placeholder="To Date"></input></span>
								</div>
						</div>
					</div>
					<div class="span2 ">
						<div class=" " >
								<div class="input-prepend text-align2 ">	
								<a class="icon-plus-sign" style="float:left;margin-top:30px;" onClick="createNewForm();" title="Add More Details"></a>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			</div>
	</div>
	</div>
	<div id="fadeInUp1">
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

							<select  style="margin-left: 12px" onChange="getElectionYears(this.value,'electionYearId${indexValue.index}')">
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
							 <select class="" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].electionTypeId" id="electionYearId${indexValue.index}" >
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
						</div>
					</div>
					<div class="span3">
						<div class=" " >
						<c:if test="${indexValue.index == 0}">	
							<h5 class="text-align1">Constituency</h5>
						</c:if>

							<select   id="constituencyList${indexValue.index}" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].constituencyId" style="margin-left: 12px">
								<option value="0"> Select Constituency</option>
								<c:forEach var="educationList" items="${constituencyesList}" >															
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
				</div>				
					</c:forEach>
					<a class="icon-plus-sign" style="float:right;margin-right:206px;;margin-top:-30px;" onClick="createNewFormForElections();" title="Add More Details"></a>
			</s:if>
			<s:else>
			
				<div class="row-fluid">
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Election Type</h5>
							<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="electionTypeId" list="eletionTypesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Election Type" style="width:220px;margin-left: 12px"   onChange="getElectionYears(this.value,'electionYearId')" value="%{role.id}"/>
						</div>
					</div>
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Year</h5>
							<select class="" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId" id="electionYearId">							
							  </select>
						</div>
					</div>
					<div class="span3">
						<div class=" " >
							<h5 class="text-align1">Constituency</h5>

							 <s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level"  list="constituencyesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:220px;" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId"/>

						</div>
					</div>
					<div class="span2">
						<div class="" >
									<a class="icon-plus-sign" style="float:left;margin-top:30px;" onClick="createNewFormForElections();" title="Add More Details"></a>
						</div>
					</div>
				</div>
				
			</s:else>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<!-- <a class="btn btn-primary m_top20 border-radius-0 text-align2" href="search-constituency.html"><span class="icon-chevron-left icon-white"></span>&nbsp;&nbsp;Back </a> -->
				
				<a  class="btn btn-success text-align3 m_top20 pull-right border-radius-0"  onClick="submitCadreForm();"> &nbsp;&nbsp;Next<span class=" icon-chevron-right icon-white"></span></a>
			</div>
		</div>
	</div>
</form>
</div>
</div>

<div id="statusDiv">
</div>
	<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		
</body>
<script type="text/javascript">
var existingCadreArr = [];
var existingCadreInfoArr = [];
function getExistingCadreInfo()
{

	var value = $('#preEnrollNoValue').val();
	$('#preEnrollNo').val('');
	var constituencyId = '${constiteucnyId}';
	var panchayatId = '${houseNo}';  // panchayat Id 
	
	if(value.trim().length >2)
	{
		existingCadreArr = [];
		existingCadreInfoArr = [];
			var jsObj = 
			   {	
				name : value,
				constituencyId : constituencyId,
				panchayatId : panchayatId,
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
										console.log(existingCadreArr[k]);
										$('#preEnrollNo').val(existingCadreArr[k].enrollNo);
										$('#preEnrollNoValue').val(existingCadreArr[k].enrollNo);	
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
</script>
</html>