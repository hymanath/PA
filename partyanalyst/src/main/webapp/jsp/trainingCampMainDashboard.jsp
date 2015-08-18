<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Training Camp Main Dashboard</title>
	<link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="css/Training/css/custom.css" rel="stylesheet" type="text/css"/>
	<link href="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css" />
	<link href="js/cadreCommittee/dist/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
	<link href="css/Training/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css"/>
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<!-- <link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
	<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/DateRange/daterangepicker.css" rel="stylesheet" type="text/css">
	<link href="dist/css/jquery.circliful.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css"> -->
<style type="text/css">

.glyphicon-trash
{
	cursor:pointer
}

.add-plus
{
	background-color:#ccc;
	color:#666;
	padding:5px;
	border-radius:50%;
}
.addRemove
{
	background-color:#ccc;
	color:#666;
	padding:5px;
	border-radius:50%;
	cursor:pointer
}
</style>
	</head>
<body>
<header>
	<img src="dist/img/header.jpg" width="100%" alt="">
</header>
<!--  accordian start-->
<main>
	<div class="container">
    	<div class="row">
        	<div class="col-md-12">
           	  <div class="panel panel-default">
                	<div class="panel-heading">
                    	<h4 class="panel-title"></h4>
                    </div>
                    <div class="panel-body">
                    	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"></div>
					</div>	
               </div>          
			</div>			  
        </div>                 
	</div>					  
</main>
<!--  accordian start-->
                  <!-- pop up modal start-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">UPDATE USER</h4>
      </div>
      <div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
				<table class="table table-bordered m_0">
					
					<tr>
						<td></td>
						<td>Name</td>
						<td>Designation</td>
						<td>Mobile No</td>
						<td>Constituency</td>
						<td>District</td>
						<td>Trained On</td>
					</tr>
					<tr>
						<td><img src="dist/img/profile-img.png" class="img-responsive" style="width:30px;"></td>
						<td>Mahendar</td>
						<td>Mandal President</td>
						<td>9848021364</td>
						<td>Makthal</td>
						<td>Adilabad</td>
						<td>Leadership Skills</td>
					</tr>
					
				</table>
				</div>
			</div>
		</div>
		
        <div class="row m_top20">
			<div class="col-md-12">
				<label>Achievements</label>
				<div class="row">
					<div class="col-md-11">
						<input class="form-control" type="text" disabled value="First in Debate">
					</div>
					<div class="col-md-1">
						<i class="glyphicon glyphicon-minus m_top10 add-plus"></i>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-md-11">
						<input class="form-control" type="text" disabled value="Batch Topper">
					</div>
					<div class="col-md-1">
						<i class="glyphicon glyphicon-minus m_top10 add-plus"></i>
					</div>
				</div>
				<div class="row m_top10">
					<div class="col-md-11">
						<input class="form-control" type="text">
					</div>
					<div class="col-md-1">
						<i class="glyphicon glyphicon-plus m_top10 add-plus"></i>
					</div>
				</div>
			</div>
        </div>
        <div class="row m_top20">
			<div class="col-md-6">
				<label>Goals</label>
				<input type="text" class="form-control" disabled value="To become a leader">
			</div>
			<div class="col-md-4">
				<label>Date & TIme</label>
				<div class="input-group">
					<input type="text" class="form-control" disabled>
				</div>
			</div>
			<div class="col-md-2">
				<i class="glyphicon glyphicon-minus addRemove m_top20"></i>
				<i class="glyphicon glyphicon-edit addRemove m_top30"></i>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-md-6">
				<input type="text" class="form-control">
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-calendar"></i>
					</span>
					<input type="text" class="form-control">
				</div>
			</div>
			<div class="col-md-2">
				<i class="glyphicon glyphicon-plus addRemove"></i>
			</div>
		</div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Leadership Level</label>
                <select class="form-control">
                	<option>District</option>
                    <option>State</option>
                    <option>Constituency</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Communication Skills</label>
                <select class="form-control">
                	<option>Average</option>
                    <option>Poor</option>
                    <option>Good</option>
                    <option>Very Good</option>
                    <option>Excellent</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Leadership Skills</label>
                <select class="form-control">
                	<option>Average</option>
                    <option>Poor</option>
                    <option>Good</option>
                    <option>Very Good</option>
                    <option>Excellent</option>
                </select>
            </div>
        </div>
        <div class="row">
        	<div class="col-md-12">
            	<label>Health</label>
                <select class="form-control">
                	<option></option>
                </select>
            </div>
        </div>
		<div class="row">
			<div class="col-md-12">
				<label>Comments</label>
				<textarea class="form-control"></textarea>
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>
                  <!-- pop up modal end -->

<footer>
		<img src="dist/img/footer.jpg" width="100%">
</footer>
	<script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/dist/js/bootstrap.min.js"></script>
	<script src="js/cadreCommittee/bootstrapDaterangepicker/moment.min.js" type="text/javascript"></script>
	<script src="js/cadreCommittee/bootstrapDaterangepicker/daterangepicker.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="css/Training/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="js/highcharts/js/highcharts.js" type="text/javascript"></script>
	<!-- <script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="dist/js/bootstrap.js" type="text/javascript"></script>
	<script src="dist/DateRange/moment.js" type="text/javascript"></script>	
	<script src="dist/DateRange/daterangepicker.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
	<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
	<script src="dist/HighCharts/highcharts.js" type="text/javascript"></script> -->

	
	<script>
	
		 getTdpCadreDetailsforASchedule(1);
		 function getTdpCadreDetailsforASchedule(scheduleId)
		 { 
			var jsObj={scheduleId:scheduleId }
			$.ajax({
			  type:'POST',
			  url :'getTdpCadreDetailsforAScheduleAction.action',
			  data:{task:JSON.stringify(jsObj)},
		    }).done(function(results){
			    if(results!=null && results.length>0){
				     buildCadreDetails(results);
				}else{
				    alert("NO Data Available.");
				}
			});
		}
		function buildCadreDetails(results){
		   var str='';
		   for(var i in results){
		     str+='<div class="panel panel-default">'
				str+='<div class="panel-heading" role="tab" id="heading'+i+'">'
                   str+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">'
                   str+='<h4 class="panel-title">'+results[i].name+'<span class="pull-right"><i class="glyphicon glyphicon-chevron-down"></i></span></h4>'
                   str+='</a>';               
                str+='</div>';                
                str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">'
                  str+='<div class="panel-body">';
                      str+='<table class="table table-bordered m_0">'
                      str+='<thead>';
					  str+='<th></th>';
                      str+='<th>Name</th><th>Mobile</th><th>Constituency</th><th>Achievements</th><th>Goals</th>'
					  str+='<th>Leadership <br/>Level</th><th>Communication<br/> Skills</th> <th>Leadership Skills</th> <th>Health</th> <th></th>';
                      str+='</thead>';                 
                      for(var j in results[i].subList){
					   str+='<tr>';
					   str+='<td><img src="dist/img/profile-img.png" style="height:40px" class="img-reponsive"></td>'
					   str+='<td>'+results[i].subList[j].name+'</td>'
					   str+='<td>'+results[i].subList[j].mobileno+'</td>'
					   str+='<td>'+results[i].subList[j].constituency+'</td>'
					   if(results[i].subList[j].achievements){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].goals){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }  
					   if(results[i].subList[j].leaderShipLevels){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].communicationSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].leaderShipSkills){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   if(results[i].subList[j].health){
					     str+='<td><i class="glyphicon glyphicon-ok text-success text-center"></i></td>'
					   }else{
					    str+='<td><i class="glyphicon glyphicon-remove text-danger text-center"></i></td>'
					   }
					   str+='<td><button  type="button" id="updateId" class="btn btn-success btn-xs" data-toggle="modal" data-target="#myModal" attr-cadreId='+results[i].subList[j].id+' attr-batchId='+results[i].id+'>UPDATE</button></td>'
                       str+='</tr>';
                    }
					str+='</table>'
                  str+='</div>'
                str+='</div>'
              str+='</div>'
		   }
		   $("#accordion").html(str);
		}
		
	
	/* $(document).on('click','#updateId',function(){
	
       var tdpCadreId=$(this).attr('attr-cadreId');
	   var batchId=$(this).attr('attr-batchId');
	   var jsObj={tdpCadreId:tdpCadreId,batchId:batchId}
	   $.ajax({
		  type:'POST',
		  url :'getDetailsForACadreAction.action',
		  data:{task:JSON.stringify(jsObj)},
	   }).done(function(results){
			
	   });
	   
   }); */
	</script>
</body>
</html>