<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Blood Bank Registration</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="dist/activityDashboard/SelectDropDown/dropkick.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-1 col-xs-1">
                  	  <img src="dist/BloodBankCustomFiles/logo.png" class="img-responsive" alt="Logo"/>
                    </div>
                    <div class="col-md-11 col-xs-11">
                    	<h3 class="text-capitalise">ntr trust blood bank</h3>
                        <p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
                        <p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
                        
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12">
                    	<hr style="border-color:#333;"/>
                    </div>
                    <div class="col-md-6">
                    	<label>Enter Cadre Number To Prepopulate Details</label>
                        <div class="input-group inputWithButton">
                        	<input placeholder="Ex: 56985617" type="text" class="form-control"/>
                            <span class="input-group-addon">
                            	<button class="btn btn-success" type="button">POPULATE DETAILS</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 m_top20">
                    	<h4>SHOWING DETAILS</h4>
                    </div>
                </div>
                <div class="row m_top20">
                    <div class="col-md-3">
                    	<label>Name</label>
                        <input type="text" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Father/Spouse</label>
                        <input type="text" class="form-control"/>
                    </div>
                    <div class="col-md-6">
                    	<div class="row">
                        	<div class="col-md-3">
                            	<label>Sex</label>
 		                        <select>
                                	<option>Male</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                            	<label>Age</label>
 		                        <input type="number" class="form-control"/>
                            </div>
                            <div class="col-md-3">
                            	<label>Date Of Birth</label>
 		                        <input type="text" class="form-control"/>
                            </div>
                            <div class="col-md-3">
                            	<label>Married</label>
 		                        <select>
                                	<option>Male</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                    	<label>Address</label>
                        <textarea class="form-control" rows="4"></textarea>
                    </div>
                    <div class="col-md-6">
                    	<div class="row">
                        	<div class="col-md-6">
                            	<label>Mobile</label>
                                <input type="tel" class="form-control"/>
                            </div>
                            <div class="col-md-6">
                            	<label>E-Mail</label>
                                <input type="tel" class="form-control"/>
                            </div>
                            <div class="col-md-6">
                            	<label>Education</label>
                                <select>
                                	<option>Graduation</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                            	<label>Occupation</label>
                                <select>
                                	<option>Farmer</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row m_top10">
                    <div class="col-md-3">
                    	<label>No.of times Blood Donation in BB</label>
                        <input type="number" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Other than BB</label>
                    	<select>
                        	<option>No</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Date Of Last Donation</label>
                    	<input type="text" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Donation</label>
                    	<input type="text" class="form-control"/>
                    </div>
                </div>
                <div class="row m_top10">
                    <div class="col-md-3">
                    	<label>Willing to Donate in Emergency</label>
                    	<select>
                        	<option>Yes</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Willing to be Called for Donation</label>
                    	<select>
                        	<option>Yes</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                    	<label>Remarks</label>
                    	<input type="text" class="form-control"/>
                    </div>
                    <div class="col-md-3">
                    	<label>Category</label>
                    	<select>
                        	<option>Yes</option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 m_top10">
                    	<button class="btn btn-success">SUBMIT</button>
                    </div>
                </div>
                <div class="row m_top30">
                	<div class="col-md-12">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
                            	<h4 class="panel-title">HISTORY OF HEALTH</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<table class="table tableHealthHistory">
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                    <tr>
                                        <td>Heart Disease/Cadriovascular Diseases
                                        </td>
                                        <td>Yes</td>
                                        <td>No</td>
                                        <td>Addiction to Alcohol/Drugs/Aspirin</td>
                                        <td>Yes</td>
                                        <td>No</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
                            	<h4 class="panel-title">MEDICAL CHECKUP</h4>
                            </div>
                            <div class="panel-body">
                            	<div class="row">
                                	<div class="col-md-2">
                                    	<label>Temp</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>HB</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-2">
                                    	<label>BP</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Pulse</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">0C(98.40F)</span>
                                    </div>
                                    <div class="col-md-3">
                                    	<label>Weight</label>
                                        <input class="form-control" type="text"/>
                                        <span class="textItalic pull-right">Kgs(&gt;45 Kgs)</span>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Reasons for Rejecting Doctor</label>
                                        <textarea class="form-control"></textarea>
                                    </div>
                                    <div class="col-md-6">
                                    	<label>Post Phlebotomy Reaction</label>
                                        <textarea class="form-control"></textarea>
                                    </div>
                                    <div class="col-md-3 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Medical Officer</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 col-md-offset-1 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Phlebotomy</p>
                                        <p>Date:___________________</p>
                                    </div>
                                    <div class="col-md-3 m_top10">
                                    	<textarea class="form-control" disabled></textarea>
                                        <p>Signature Of Donor</p>
                                        <p>Date:___________________</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <h4 class="text-capitalise">Donor Consent</h4>
                <ul>
                	<li>I Have Accurately answered all questions and voluntary willing to donate 350/450 ml of blood</li>
                    <li>I understand that my blood will be tested for evidence of infections including HIV(AIDS),HBsAg,HCV and Syphilis</li>
                    <li>I understand that test results will be made known to health authorities only. I would like to be informed in case of Sero-Positive result.</li>
                </ul>
 	        </div>
        </div>
    </div>
</div>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>
<div class="modal fade dataSubmittingModal" id="myModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body">
        <p class="text-capitalise">details are submitting</p>
        <p>dou want to get print</p>
        <button type="button" class="m_top20 btn btn-primary">PRINT</button>
        <button type="button" class="btn btn-default m_top20" data-dismiss="modal">CANCEL</button>
        
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/activityDashboard/SelectDropDown/dropkick.js" type="text/javascript"></script>
<script type="text/javascript">
$('select').dropkick();
</script>
<script type="text/javascript">
$(document).ready(function(){
	 getOccuations();
	 getEducationQualifications();
	 getCadreDetails();
});
function getOccuations(){
	
	   var jObj = {
		}
		$.ajax({
		  type:'GET',
		  url: 'getOccuationsAction.action',
		  data : {} ,
		}).done(function(result){
			//console.log(result);
		});
}
function getEducationQualifications(){
		
	   var jObj = {
		}
		$.ajax({
		  type:'GET',
		  url: 'getEducationQualificationsAction.action',
		  data : {} ,
		}).done(function(result){
			//console.log(result);
		});
}
 function getCadreDetails(){
		
	   var jObj = {
		   memberShipNo:12800104
		}
		$.ajax({
		  type:'GET',
		  url: 'getCadreDetailsAction.action',
		  data : {task:JSON.stringify(jObj)} ,
		}).done(function(result){
			//console.log(result);
		});
}

</script>

</body>
</html>
