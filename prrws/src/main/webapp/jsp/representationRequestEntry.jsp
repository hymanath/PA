
<link href="static/css/common/Dropdown.css" rel="stylesheet" type="text/css">
<html>
<title> ABCD </title>
<section>
	<div class="container">
		<div class="row bg_white content_shadow">
			<div class="col-md-11 col-xs-10 col-sm-10">
				<section class="pad_top10 pad_left10">
					<div class="row bg-e3">
					<form action="saveRepresentRequestDetails" id="adminProfileForm" name="adminProfileFormName" enctype="multipart/form-data" method="post">
						Candidate Name : <input type="text" id="name" name="representationRequestVO.name" />
						By Self : <input type="radio" value="1" id="selfRadio" name="representationRequestVO.memberType"/>
						By Represent : <input type="radio" value="2" id="representRadio" name="representationRequestVO.memberType"/>
						<input type="button" class="btn btn-success" value="Save Details" onclick="saveRepresentRequestDetails()"/>
						
						Upload File : <input type="file" id="fileId" name="representationRequestVO.file" />
					</form>
					</div>
				</section>
			</div>
	   </div>
	</div>
</section>

<script src="Assests/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="Assests/Plugins/Scroller/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assests/representationRequest/representationRequestEntry.js" type="text/javascript"></script>

<script>

function saveRepresentRequestDetails(){
	 var formData = new FormData();
	 formData.append("name","hyma");
	 formData.append("memberType",2);
	 formData.append("endorsmentDate","2017-12-01");
	 formData.append("representationDate","2017-12-01");
	 formData.append("mobileNo","9581434970");
	 formData.append("emailId","srishailam@itgrids.com");
	 formData.append("voterCardNo","WARDU765T");
	 formData.append("referrerCandidateId",2);
	 formData.append("file",$('input[type=file]')[0].files[0]);
	 
	 
	 formData.append("workName",2);
	 formData.append("noOfWorks",2);
	 formData.append("estimationCost",2);
	 formData.append("subject",2);
	 formData.append("subjectId",2);
	 formData.append("deptId",2);
	 formData.append("isPreviousPetition",2);
	 formData.append("previousPetitionRefNo",2);
	 formData.append("projectDescription",2);
	 
	 formData.append("candidateAddressVO.stateId",2);
	 formData.append("candidateAddressVO.districtId",2);
	 formData.append("candidateAddressVO.parliamentId",2);
	 formData.append("candidateAddressVO.assemblyId",2);
	 formData.append("candidateAddressVO.localElectionBodyId",2);
	 formData.append("candidateAddressVO.tehsilId",2);
	 formData.append("candidateAddressVO.panchayatId",2);
	 formData.append("candidateAddressVO.wardId",2);
	 formData.append("candidateAddressVO.divisionId",2);
	 
	 
	 formData.append("worksList[0].deptId",2);
	 formData.append("worksList[0].locationLevelId",2);
	 formData.append("worksList[0].locationValue",2);
	 
	 formData.append("worksList[0].candidateAddressVO.stateId",2);
	 formData.append("worksList[0].candidateAddressVO.districtId",2);
	 formData.append("worksList[0].candidateAddressVO.parliamentId",2);
	 formData.append("worksList[0].candidateAddressVO.assemblyId",2);
	 formData.append("worksList[0].candidateAddressVO.localElectionBodyId",2);
	 formData.append("worksList[0].candidateAddressVO.tehsilId",2);
	 formData.append("worksList[0].candidateAddressVO.panchayatId",2);
	 formData.append("worksList[0].candidateAddressVO.wardId",2);
	 formData.append("worksList[0].candidateAddressVO.divisionId",2);

	  $.ajax({
        url: $("#adminProfileForm").attr("action"),
        data: formData,
        type: "POST",               
         processData: false,
         contentType: false,
        success: function(ajaxresp) {
			alert("success");
			console.log(ajaxresp);
        },
        error: function(request,error) { 
			alert("error");
			
         }
     });	 
}
$(document).ready(function(){
	alert("ready")
	getPetitionDesignationList();
	getPetitionDepartmentList();
})
</script>
</html>