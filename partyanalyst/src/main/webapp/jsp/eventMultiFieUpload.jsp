<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<style>
.errorCls{color:red;font-size:12px;}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">



<title>File Upload</title>

    

	<!--jQuery
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>-->
	
	
	<!-- Bootstrap -->
    <link href="dist/activityDashboard/css/bootstrap.css" rel="stylesheet">
    <link href="dist/activityDashboard/css/custom.css" rel="stylesheet">
  
</head>
<body>
<div class="container m_top20">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">
						File Upload
					</div>
					<div class="panel-body">
						<div class="row">
						     
						      <div class="errorDiv"></div>
							 
							<div class="col-md-6">
								<label>Activity Type</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Type" name="surveyType" id="activityTypeList" value="1" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" cssClass="input-block-level form-control"/>
							</div>
							<div class="col-md-6">
								<label  class="control-label">Activity Level</label>
								<s:select theme="simple" headerKey="0" headerValue="Select Activity Level" name="surveyType" id="activityLevelList" value="0" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level form-control"/>
							 </div>
							 <div class="col-md-6">
									<label> Activity Name </label>
									<select id="ActivityList" class="form-control">
										<option value="0"> Select Activity </option>
									</select>
							 </div>
							
							<div class="col-md-6">
									<label> Source Folder Name </label>
									<input type="text" class="form-control" id="sourceFolderId" />
							 </div>
							 
							 <div class="col-md-6" style="margin-top: 28px;">
									<label> Upload Document </label>
									<button type="button" id="uploadDocument" class="btn btn-success">Upload Document</button>
							 </div>
							
						</div>
					</div>
				</div>
		</div>
	</div>
	
 
</div>

<script src="dist/activityDashboard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/activityDashboard/js/bootstrap.js" type="text/javascript"></script>

<script type="text/javascript">

function getActivityNames()
{
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'" selected="true">'+result[i].name+'</option>');	
			}
			
		});

}
	
  $(document).on("click","#uploadDocument",function() {
	  
     $(".errorDiv").html("");
	 var activityLevel = $("#activityLevelList").val();
	 var activityScopeId = $("#ActivityList").val();
	 var sourceFolderId = $("#sourceFolderId").val().trim();
	 
	 var str = '';
	 var flag = true;
	 
	 if(activityLevel == 0){
		str += 'Please Select Activity Level <br>';
		flag = false;
	}
	if(activityScopeId == 0){
		str += 'Please Select Activity Name <br>';
		flag = false;
	}
	if(sourceFolderId.length == 0){
		str += 'Please Enter Source Folder Name <br>';
		flag = false;
	}
	$(".errorDiv").html(str).addClass("errorCls");;
	if(!flag)
	{
		return;
	}
	 
	 var jsObj=
	   {		
          activityScopeId:activityScopeId,	   
		  sourceFolderId:sourceFolderId,
		  task:"uploadFile"				
		}
		$.ajax({
				  type:'GET',
				  url: 'uploadActivityDocuments.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   console.log(result)
		   });

})
	
	
</script>
</body>
</html>