<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
					</form>
					</div>
				</section>
			</div>
	   </div>
	</div>
</section>
<script>

function saveRepresentRequestDetails(){
	alert(12345);
	 var formData = new FormData();
	 formData.append("name",$('#name').val());
	 formData.append("memberType",2);
	 
	  $.ajax({
        url: $("#saveRepresentRequestDetails").attr("action"),
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
			$.unblockUI();
         }
     });	 
}

</script>
</html>