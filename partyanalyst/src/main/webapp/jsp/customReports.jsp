<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>cadre Demographic Reports</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/css/responsive.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet" type="text/css">
<link href="dist/DateRange/daterangepicker.css" type="text/css" rel="stylesheet"/>
<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
<!-- for file uploader -->
<link href="dragAndDropPhoto/css/jquery.filer.css" type="text/css" rel="stylesheet" />
<link href="dragAndDropPhoto/css/themes/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet" />  
<!-- for file uploader -->
<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
<script type="text/javascript" src="js/yahoo/datasource-min.js"></script>  
<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/2.0.0/handlebars.js"></script>
<style type="text/css">
.text-capital{
	
	text-transform:uppercase;
}
.f_18{
	font-size:18px;
}
</style>
</head>
<body>  
	<div class="container m_top20">
		<div class="row">
			<div class="col-md-12 col-xs-12 col-sm-12">  
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
								<div class="col-md-9 col-xs-12 col-sm-12" style="margin-top: 10px;">
									<h3 class="panel-title text-capital f_18">Program</h3>
								</div>
							<div class="col-md-3 col-xs-12 col-sm-12">
								<div class="input-group inputGCustom">
									<span class="input-group-addon">
										<i class="glyphicon glyphicon-calendar"></i>
									</span>
									<input type="text" class="form-control multiDateRangePicker"/>
								</div>
							</div>
						</div>
						
				    </div>
					    <div class="panel-body">
							<div id="tempDivId"></div>
							<select class="form-control" id="programSelId">
							</select>
							
							<button type="button" class="btn btn-success uploadDivCls">Upload</button>
					    </div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="uploadModalDivId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title text-capital" id="myModalLabel">Upload Documents</h4>
		  </div>
		  <div class="modal-body">
			<div class="row" >
				<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
					<h3 class="m_0 text-success font_weight" style="margin-left:425px;">UPLOAD SCAN COPY</h3> 
					<form name="customApplication" method="post" id="customApplication">
					<input type="file" id="update_CustomReportId" multiple="multiple"  name="files[]" class="m_top20"/>
					<span id="errFileId" style="color:red;margin-left:470px;"></span>   
					
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="uploacFilesBtnId">Save changes</button>
					</form>
				</div>
			</div>  
		  </div>
		  <div class="modal-footer">
			
		  </div>
		</div>
	  </div>
	</div>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script src="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>

<script type="text/javascript" src="dragAndDropPhoto/js/customNominated.jquery.filter.min.js?v=1.0.5"></script>
<script type="text/javascript" src="dragAndDropPhoto/js/updateCustomReorts.js?v=1.0.5"></script>
<script id="select-box" type="text/x-handlebars">
	<option value="0">Select Option</option>
	{{#each this}}
		<option value="{{id}}" selected="{{selected}}">{{name}}</option>
	{{/each}}
</script>
<script src="js/customReports.js" type="text/javascript"></script>
<script type="text/javascript">
initializeCustomReport();
	$('select').chosen({width:'100%'});
	$(".multiDateRangePicker").daterangepicker({
		opens: 'left',
	 	locale: {
		  format: 'MM/DD/YYYY'
		}		
	});
	
	$(document).on('click','.uploadDivCls',function(){
		$("#uploadModalDivId").modal("show");	
	});
	
	$(document).on("click","#uploacFilesBtnId",function(){
		var uploadHandler = { 
			upload: function(o) {
				uploadResult = o.responseText;
				//showSbmitStatusNew(uploadResult);
			}
		};
		YAHOO.util.Connect.setForm('customApplication',true);  
		YAHOO.util.Connect.asyncRequest('POST','saveCustomReportUploadFileAction.action',uploadHandler);
	});
	
	
	
	function getRequiredDocumentsSummary(){alert(12);
		var jsObj={
			id:$("#programSelId").val()
		}
		
		$.ajax({
		  type : "GET",
		  url : "getTotalExpectedReportsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}
</script>
</body>
</html>