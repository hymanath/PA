<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Committee Dashboard</title>
	<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/css/style.css" rel="stylesheet"/>
    	<!--Bootstrap DatePicker-->
    <link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
		<!--Hover Menu-->
    <link href="css/cadreCommitee/css/jquery.smartmenus.bootstrap.css" rel="stylesheet" />
    	<!--Circle-->
    <link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

<style>
	.widget
	{
		background-color:rgba(0,0,0,0.1);
		text-align:center;
		padding:10px 0px 10px 0px;
		margin-right:20px;
		border:1px solid #999;
		box-shadow:1px 1px 5px #999;
	}
	.borderbox
	{
		margin-top:10px;
		border:5px solid #CCC;
		border-radius:5px;
	}
	.tablecaption
	{
		background-color:#FC6;
		padding-left:5px;
		font-size:16px;
		font-weight:bold;
		color:#FFF;
	}
</style>
</head>

<body>

<div class="container">
	<div class="col-md-10 col-md-offset-1">
		<h3 style="text-align:center">Cadre Committee Role Information</h3>
		<hr style="margin-top:5px;border-color:#FC6"/>
	</div>
	<div class="col-md-8 col-md-offset-2" style="padding:30px 10px 30px 40px;background-color:rgba(0,0,0,0.1);margin-top:10px;box-shadow:1px 1px 5px #999;color:#686868">
          <div class="col-md-6 col-xs-10">
			<label>Committee Type</label>
			<select class="form-control" style="width:220px">
				<option>Main Committee</option>
			</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
			<label>Committee Level</label>
			<select class="form-control" style="width:220px">
				<option>Select Committee Level</option>
			</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
				<label>Position</label>
				<select class="form-control" style="width:220px">
					<option>All </option>
					<option>Presidents </option>
				</select>
		  </div>
		  <div class="col-md-6 col-xs-10">
				<label>Location Level</label>
				<select class="form-control" style="width:220px">
					<option>State</option>
				</select>
		  </div>
		  <div class="col-md-3">
			<button type="submit" class="btn btn-default btn-success" style="margin-top:10px">Get Details</button>
		  </div>
		
    </div>
	<div class="col-md-3 col-md-offset-8" style="margin-top:10px;">
        <button class="btn btn-xs btn-success pull-right"><label class="radio-inline"><input type="radio" name="optradio" checked>ALL</label></button>
		<button class="btn btn-xs btn-danger pull-right"><label class="radio-inline"><input type="radio" name="optradio">AP</label></button>
		<button class="btn btn-xs btn-danger pull-right"><label class="radio-inline"><input type="radio" name="optradio">TS</label></button>
    </div>
    <div class="col-md-10 col-md-offset-1 borderbox" >	
    	<div class="col-md-10 col-md-offset-1">
        <h3 style="text-align:center;margin-top:10px;">State Level Presidents Information       
</h3>
        <hr style="margin-top:5px;border-color:#FC6"/>
        </div>
    <div class="col-md-10 col-md-offset-1 widget" style="margin-top:10px;">
    	<div class="col-md-4" >
        	<div class="col-md-5" style="padding:0px;">Total Candidates</div>
            <div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">10000</div>
        </div>
    	<div class="col-md-4" style="border-left:1px solid #FFF" >
        	<div class="col-md-5" style="padding:0px;">
            <i class="icon-male"></i>
            Male Candidates</div>
            <div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">7000</div>
        </div>
    	<div class="col-md-4" style="border-left:1px solid #FFF" >
        	<div class="col-md-5" style="padding:0px;">
            <i class="icon-female"></i>
            Female Candidates</div>
            <div class="col-md-7" style="font-size:32px;padding:0px;display:inline-block">3000</div>
        </div>
	</div>
   <div class="col-md-10 col-md-offset-1" style="margin-top:20px;">
        <div class="table-responsive">
            <table class="table table-bordered" style="border:2px solid #FC6 !important">
            	<caption class="tablecaption" >Caste Category Wise Presidents Information
                	<i class="glyphicon glyphicon-remove pull-right" style="margin-right:10px;cursor:pointer"></i>	
                <hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>
                </caption>
                <thead>
                    <tr>
                        <th width="25%">Caste Category</th>
                        <th width="25%">Total Count</th>
                        <th width="25%">Male</th>
                        <th width="25%">Female</th>
                    </tr>
                </thead>
                <tr>
                    <td>SC</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>ST</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>BC</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>OC</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
            </table>
			<table class="table table-bordered" style="border:2px solid #FC6 !important">
            	<caption class="tablecaption" >Caste Wise Presidents Information
                	<i class="glyphicon glyphicon-remove pull-right" style="margin-right:10px;cursor:pointer"></i>	
                <hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>
                </caption>
                <thead>
                    <tr>
                        <th width="25%">Caste Name</th>
                        <th width="25%">Total Count</th>
                        <th width="25%">Male</th>
                        <th width="25%">Female</th>
                    </tr>
                </thead>
                <tr>
                    <td>Kamma</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>Reddy</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>Kamma</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>Reddy</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>Kappu</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
                <tr>
                    <td>Kappu</td>
                    <td>650</td>
                    <td>400</td>
                    <td>250</td>
                </tr>
            </table>    
            <table class="table table-bordered" style="border:2px solid #FC6 !important">
        	    <caption class="tablecaption">Age Range Wise Presidents Information
                	<i class="glyphicon glyphicon-remove pull-right" style="margin-right:10px;cursor:pointer"></i>	
                <hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>
                <thead>
                    <tr>
                        <th width="25%">Between Age</th>
                        <th width="25%">Total Count</th>
                        <th width="25%">Male</th>
                        <th width="25%">Female</th>
                    </tr>
                </thead>
                <tr>
                    <td>20-30</td>
                    <td>200</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
                <tr>
                    <td>30-40</td>
                    <td>100</td>
                    <td>50</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>40-50</td>
                    <td>200</td>
                    <td>100</td>
                    <td>100</td>
                </tr>
            </table> 
            <table class="table table-bordered" style="border:2px solid #FC6 !important;cursor:pointer">
        	    <caption class="tablecaption">District Wise Presidents Information
                	<i class="glyphicon glyphicon-remove pull-right" style="margin-right:10px;cursor:pointer"></i>	
	                <hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>
                <thead>
                    <tr>
                        <th width="25%">District Name</th>
                        <th width="25%">Total</th>
                        <th width="25%">Male</th>
                        <th width="25%">Female</th>
                    </tr>
                </thead>
                <tr>
                    <td>Srikakulam</td>
                    <td>100</td>
                    <td>50</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>Vijayawada</td>
                    <td>100</td>
                    <td>50</td>
                    <td>50</td>
                </tr>
                <tr>
                    <td>RangaReddy</td>
                    <td>100</td>
                    <td>50</td>
                    <td>50</td>
                </tr>
            </table> 
        </div>   
    </div>
    </div>
 
</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jqueryui/1.11.1/jquery-ui.js "></script>
	<script src=" http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<!--Circle js file-->
	
    <script src="js/cadreCommittee/dist/js/jquery.circliful.min.js"></script>
    <!--Bootstrap Date Picker-->
   <script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script> 
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>   
    <!--Jquery Sparkline-->
    <script src="js/cadreCommittee/js/jquery.sparkline.js" type="text/javascript"></script>
    <!-- Custom JS File-->
    <script src="js/cadreCommittee/js/custom.js" type="text/javascript"></script>
    <!--Hover Menu-->
    <script src="js/cadreCommittee/js/jquery.smartmenus.min.js" type="text/javascript"></script>
    <script src="js/cadreCommittee/js/jquery.smartmenus.bootstrap.min.js" type="text/javascript"></script>
	 <script src="js/jquery.classyloader.min.js"></script>
    <script type="text/javascript" src="js/jquery.dataTables.js"></script>

	
<script>
	$(document).ready(function(){		
		getRolesBasedReport(0);
	});
	
	function getRolesBasedReport(roleId)
	{
		var rolesArr = new Array();
		var casteCategoryArr = new Array();
		var casteCategoryGroupArr = new Array();
		var casteIdsArr = new Array();
		
		var locationLevelId =1;
		var committeeTypeId =1;
		var userAccessType ="AP";
		var castePercentage =5;
		var searchTypeId =0;
		
		if(roleId == 0)
		{
			rolesArr.push(1);
			rolesArr.push(2);
			rolesArr.push(3);
			rolesArr.push(4);
			rolesArr.push(5);
			rolesArr.push(6);
			rolesArr.push(7);
			rolesArr.push(8);
			rolesArr.push(9);
		}
		else
		{
			rolesArr.push(roleId);
		}

		var jObj = {
			rolesArr : rolesArr,
			casteCategoryArr : casteCategoryArr,
			casteCategoryGroupArr : casteCategoryGroupArr,
			casteIdsArr : casteIdsArr,
			locationLevelId: locationLevelId,
			committeeTypeId : committeeTypeId,
			userAccessType : userAccessType,
			castePercentage:castePercentage,
			searchTypeId:searchTypeId
		};
		$.ajax({
          type:'GET',
          url: 'getCommitteeRolesBasedReport.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
		});
	}
</script>
</body>
</html>
