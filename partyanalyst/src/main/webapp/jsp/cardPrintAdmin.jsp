<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>CARDS PRINTING ADMIN DASHBOARD</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<h4 class="panel-title">CARDS PRINTING ADMIN DASHBOARD</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<label>CONSTITUENCY:</label>
							<select id="constituencyId" class="chosenSelect">
								<option value="0">Select Constituency</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
	

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script type ="text/javascript">

$(document).ready(function(){
	$(".chosenSelect").chosen();
	getConstituencyDetailsList();
});
function getConstituencyDetailsList(){
	
	var jsObj ={
			
	}
	$.ajax({
		type:"post",
		url:"getConstituencyDetailsListAction.action",
		dataType:"json",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		
		if(result != null && result.length>0){
			for(var i in result){
				$("#constiencyId").append('<option valuue='+result[i].id+'>'+result[i].name+'</option>');
			}
			
		}
	});
}
</script>
</body>
</html>