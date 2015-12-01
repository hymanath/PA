<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Activity</title>
	    <link href="dist/css/bootstrap.min.css" rel="stylesheet"/>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	
	<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
	<!--<script type="text/javascript" src="js/bootstrap.js" ></script> -->
	 <link href="dist/js/bootstrap.min.js" rel="stylesheet"/> 
<body>
   	
<div class="container">
	<div class="row">
   		<div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH TO UPDATE PROGRAM ACTIVITIES</h4>
                </div>
                <div class="panel-body">
                	<div class="row">
                    	<div class="col-md-3">
                        	<img src="img/searchicon.png" style="border-right:1px solid #00B17D">
                        </div>
                        <div class="col-md-9">
                        	<div class="row">
							<div class="col-md-4">
                                	<label>Activity Type</label>
                                    <s:select theme="simple"  name="surveyType" id="activityTypeList" value="surveyTypeId" list="basicVO.panchayatVoterInfo" listKey="id" listValue="name" onchange="get();" cssClass="input-block-level"/>
                                </div>
                            	<div class="col-md-4">
                                	<label>Activity Level</label>
                                    <s:select theme="simple"  name="surveyType" id="activityLevelList" value="surveyTypeId" list="idNameVOList" listKey="id" listValue="name" onchange="getActivityNames(this.value);" cssClass="input-block-level"/>
                                </div>
                                <div class="col-md-4">
                                	<label> Activity Name </label>
                                    <select id="ActivityList">
                                    	<option value="0"> Select Activity </option>
                                    </select>
                                </div>
                                <div class="col-md-12 m_top10">
                                	<label class="radio-inline">
                                    	<input type="radio">Constituency
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Mandal/ Town / Division
                                    </label>
                                    <label class="radio-inline">
                                    	<input type="radio">Panchayat/ Ward
                                    </label>
                                </div>
                                <div class="col-md-4">
                                	<label>Constituency</label>
                                    <select>
                                    	<option>Nuzvid</option>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                	<label>Mandal/ Town/ Division</label>
                                    <select>
                                    	<option>Nuzvid</option>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                	<label>Panchayat/ Ward</label>
                                    <select>
                                    	<option>Nuzvid</option>
                                    </select>
                                </div>
                                <div class="col-md-4 m_top10">
                                	<button class="btn btn-block btn-custom btn-success" type="button">SEARCH</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
        	<div class="panel panel-default panel-custom">
            	<div class="panel-heading">
                	<h4 class="panel-title">SEARCH RESULTS<span class="font-12">- Activity Name(Activity level)</span>
                    <span class="pull-right">
                    	<label class="checkbox-inline">
                        	<input type="checkbox">Show Committee Members
                        </label>
                    </span>
                    </h4>
                </div>
                <div class="panel-body">
                	<div>
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs nav-tabs-custom" role="tablist">
                        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Planed</a></li>
                        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Conducted</a></li>
                        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Lately Conducted</a></li>
                        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Not Conducted</a></li>
                        <button class="btn btn-sm btn-custom btn-success pull-right">SEARCH</button>
                        <li class="pull-right">
                        	<div class="input-group input-g">
                            	<input type="text" class="form-control">
                                <span class="input-group-addon">
                                	<i class="glyphicon glyphicon-search"></i>
                                </span>
                                
                            </div>
                        </li>
                      </ul>
                    
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                        	<table class="table table-bordered bg_ff">
                            	<thead>
                                	<th>CONSTITUENCY</th>
                                    <th>MANDAL/ TOWN/ DIVISION</th>
                                    <th>PANCHAYAT/ WARD</th>
                                    <th colspan="2">PLANNED DATE</th>
                                    <th colspan="2">CONDUCTED DATE</th>
                                    <th>PRESIDENT</th>
                                    <th>GENERAL SECRETARY</th>
                                    <th>OTHER MEMBERS</th>
                                </thead>
                                <tr>
                                	<td></td>
                                    <td></td>
                                    <td></td>
                                    <td>From</td>
                                    <td>To</td>
                                    <td>From</td>
                                    <td>To</td>
                                </tr>
                                <tr>
                                	<td>Nuzvid</td>
                                    <td>Nuzvid</td>
                                    <td>Nuzvid</td>
                                    <td>10/12/2015</td>
                                    <td>10/12/2015</td>
                                    <td>
                                    	<div class="input-group input-g1">
                                        	<span class="input-group-addon">
                                            	<i class="glyphicon glyphicon-calendar"></i>
                                            </span>
                                            <input type="text" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                    	<div class="input-group input-g1">
                                        	<span class="input-group-addon">
                                            	<i class="glyphicon glyphicon-calendar"></i>
                                            </span>
                                            <input type="text" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                    	<p class="font-12">Ramaiah Chowdary</p>
                                        <span>877874454</span>
                                    </td>
                                    <td>
                                    	<p class="font-12">Ramaiah Chowdary</p>
                                        <span>877874454</span>
                                    </td>
                                    <td>
                                    	<button class="btn btn-custom btn-success btn-xs">VIEW</button>
                                    </td>
                                </tr>
                            </table>
                            <button class="btn btn-custom btn-success">SAVE</button>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="profile">...</div>
                        <div role="tabpanel" class="tab-pane" id="messages">...</div>
                        <div role="tabpanel" class="tab-pane" id="settings">...</div>
                      </div>
                    
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<footer>
	<p>All &copy; 2015 Telugu Desam Party</p>
</footer>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/js/custom.js" type="text/javascript"></script>
<script src="dist/Date/moment.js" type="text/javascript"></script>
<script src="dist/Date/daterangepicker.js" type="text/javascript"></script>

<script>
$(document).ready(function(){
	
	
});
function getActivityNames()
{
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getLocationWiseAsOfNowDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			console.log(result);
		});
		
}
</script>
</body>
</html>




		   
		   