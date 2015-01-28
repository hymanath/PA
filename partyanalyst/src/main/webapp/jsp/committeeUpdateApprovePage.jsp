<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Committee Update Approve Page</title>

   
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/bootstrap.min.js"></script>
	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

	 
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
		
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/> 
	<script type="text/javascript" src="js/simplePagination/simplePagination1.js" ></script>
	

  </head>
  <body>
  
	<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
			<div class="span4">
			  <span>
			     <a href="newlogoutAction.action" class="btn btn-xs btn-default pull-left m_top20">Logout</a>	
			  </span>	
			</div>
		</div>
        <div class="container">
        	<!--Content Start-->
            <div class="row" style="text-align:center;">
                <div class="col-md-6 col-md-offset-3">
                    <h3 class="panel-header">APPROVE REQUEST</h3>
                    <hr style="border-color:#F00;margin-top:10px;" />
                </div>
            </div>
            <!-- First Block Start-->
    		<div class="row">
            	<div class="col-md-offset-3 col-md-3"  style="background-color:rgba(0,0,0,0.1);"><h1 style="display:inline">
                <u>100</u></h1><small>REQUEST <BR/>FOR INCREASE POSITION</small></div>
                <div class="col-md-3"  style="background-color:rgba(0,0,0,0.1);margin-left:1px;"><h1 style="display:inline">
                <u>40</u></h1><small>REQUEST <BR/>FOR CHANGE MEMBER POSITION</small></div>
            </div>
            <div class="text-center m_top20">Note: Click on the <u>count</u> to view request details</div>
            <div class="row">
            	<div class="col-md-10 col-md-offset-1 m_top20" id="posTable">
                	<!--<table class="table table-condensed" style="background-color:rgba(0,0,0,0.1);">
						<thead>
                        	<th colspan="5"><h4 class="text-success" style="display:inline">CHANGE POSITION REQUEST STATUS</h4></th>
                            <th colspan="2"><div class="input-group pull-right">
  <input type="text" class="form-control input-sm pull-right" placeholder="Search" aria-describedby="basic-addon2">
  <span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-search"></i></span>
</div></th>
                        </thead>
                        <thead>
                        	<th width="13%">REQUEST NO</th>
                        	<th width="12%">LOCATION</th>
                            <th width="18%">COMMITTEE NAME</th>
                            <th width="17%">POSITION NAME</th>
                            <th width="12%"><small>CURRENT MAX POSITONS</small></th>
                            <th width="13%"><small>REQUESTED MAX POSITIONS</small></th>
                            <th width="18%"><small>UPDATE STATUS</small></th>
                        </thead>
                         <tbody>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td>VICE PRESIDENT</td>
                                <td>01</td>
                                <td>02</td>
                                <td><select class="form-control input-sm"><option>Select Status</option></select></td>
                            </tr>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td>VICE PRESIDENT</td>
                                <td>01</td>
                                <td>02</td>
                                <td><select class="form-control input-sm"><option>Select Status</option></select></td>
                            </tr>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td>VICE PRESIDENT</td>
                                <td>01</td>
                                <td>02</td>
                                <td><select class="form-control input-sm"><option>Select Status</option></select></td>
                            </tr>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td>VICE PRESIDENT</td>
                                <td>01</td>
                                <td>02</td>
                                <td><select class="form-control input-sm"><option>Select Status</option></select></td>
                            </tr>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td>VICE PRESIDENT</td>
                                <td>01</td>
                                <td>02</td>
                                <td><select class="form-control input-sm"><option>Select Status</option></select></td>
                            </tr>

                        </tbody>
                    </table>-->
                </div>
                </div>
                 <!--<div class="row">
            	<div class="col-md-10 col-md-offset-1 m_top20"  >
                	<table class="table table-condensed" style="background-color:rgba(0,0,0,0.1);">
						</caption>		
						<thead>
                        	<th colspan="6"><h4 class="text-success" style="display:inline">CHANGE POSITION REQUEST STATUS</h4></th>
                            <th colspan="3"><div class="input-group pull-right">
  <input type="text" class="form-control input-sm pull-right" placeholder="Search" aria-describedby="basic-addon2">
  <span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-search"></i></span>
</div></th>
                        </thead>
                        <thead>
                        	<th width="10%"><small>REQUEST NO</small></th>
                        	<th width="8%"><small>LOCATION</small></th>
                            <th width="15%"><small>COMMITTEE NAME</small></th>
                            <th width="1%"><small>MEMBER IMAGE</small></th>
                            <th width="15%"><small>COMMITTEE MEMBER NAME</small></th>
                            <th width="8%"><small>MEMBERSHIP NO</small></th>
                            <th width="18%"><small>COMMITTE MEMBER CURRENT POSITION</small></th>
                            <th width="12%"><small>REQUESTED FOR</small></th>
                            <th width="10%"><small>UPDATE STATUS</small></th>
                        </thead>
                         <tbody>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                        	<tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            <tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            <tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            <tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            <tr>
                                <td>#2015001</td>
                                <td>KAVALI</td>
                                <td>MAIN COMMITTEE</td>
                                <td><img src="Images/Member_thamb_image.png" /></td>
                                <td>SRIKANTH</td>
                                <td>MEMBERSHIP NO</td>
                                <td>VICE PRESIDENT</td>
                                <td>PRESIDENT</td>
                                <td>
                                	<button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i></button>
                                    <button class="btn btn-success btn-xs"><i class="glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                </div>-->
            <!--Content END-->
        
        </div>
	<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>
	<script>
		getCandidateDetailsById();
		function getCandidateDetailsById(){
				var jsObj = {
						startNo : 0,
						endNo : 0
				}				   
				$.ajax({
					type : "POST",
					url : "getCommitteesForApprovalAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					console.log(result);
					buildRequests(result);
				});
		}
		
		function buildRequests(result){
			var str = '';
			$("#posTable").html("");
			str+='<table class="table table-condensed" style="background-color:rgba(0,0,0,0.1);">';
						str+='<thead>';
                        str+='	<th colspan="5"><h4 class="text-success" style="display:inline">CHANGE POSITION REQUEST STATUS</h4></th>';
                        str+='    <th colspan="2"><div class="input-group pull-right">';
						//str+='<input type="text" class="form-control input-sm pull-right" placeholder="Search" aria-describedby="basic-addon2">';
						//str+='<span class="input-group-addon" id="basic-addon2"><i class="glyphicon glyphicon-search"></i></span>';
						str+='</div></th>';
						str+='</thead>';
						str+='<thead>';
						str+='<th width="13%">REQUEST NO</th>';
                        str+='<th width="12%">LOCATION</th>';
                            str+='<th width="18%">COMMITTEE NAME</th>';
                            str+='<th width="17%">POSITION NAME</th>';
                            str+='<th width="12%"><small>CURRENT MAX POSITONS</small></th>';
                            str+='<th width="13%"><small>REQUESTED MAX POSITIONS</small></th>';
                            str+='<th width="18%"><small>UPDATE STATUS</small></th>';
                        str+='</thead>';
                         str+='<tbody>';
							for(var i in result){
								str+='<tr>';
                                str+='<td> #'+result[i].committeeId+'</td>';
                                str+='<td> '+result[i].location+' '+result[i].locationType+'</td>';
                                str+='<td>'+result[i].committeeName+'</td>';
                                str+='<td>'+result[i].role+'</td>';
                                str+='<td>'+result[i].currentPosCount+'</td>';
                                str+='<td>'+result[i].requestdPosCount+'</td>';
								str+='<td>';
								if(result[i].status="pending"){
									str+='<select class="form-control input-sm">';
									str+='<option selected="selected"> Pending </option>';
									str+='<option> Approved </option>';
									str+='<option> Rejected </option>';
									str+='</select>';
								}else{
									str+=''+result[i].status+'';
								}
                                str+='</td>';
								str+='</tr>';
							}
                        	
                        str+='</tbody>';
                    str+='</table>';
					
		$("#posTable").html(str);
		}
	</script>
  </body>
</html>