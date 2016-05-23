<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bleading Details</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/BloodBankCustomFiles/custom.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Roboto:400,500,300,500italic,400italic,300italic,700,900" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-lg-12 col-xs-12 col-sm-12">
        	<div class="block">
            	<div class="row">
                	<div class="col-md-3 col-xs-3">
                    	<h3 class="m_top20">BLEADING DETAILS</h3>
                    </div>
                    <div class="col-md-1 col-xs-2">
                  	  <img src="dist/BloodBankCustomFiles/logo.png" class="img-responsive" alt="Logo"/>
                    </div>
                    <div class="col-md-8 col-xs-7">
                    	<h3 class="text-capitalise">ntr trust blood bank</h3>
                        <p class="text-capitalise">ntr trust bhavan, road no-2, banjara hills, hyderabad - 34</p>
                        <p>Tel: 040 30799999 | E-Mail:hydbb@ntrtrust.org | Lic No: 01/HD/AP/2008/BB/CP</p>
                    </div>
                </div>
                <hr style="border-color:#333"/>
                <div class="row">
                	<div class="col-md-3">
                    	<label>Showing</label>
                        <select id="totalStatusId" class="form-control totalStatusCls">
                        	<option value="0">All Applications</option>
                        </select>
                    </div>
					<div id="errorDiv" style="color:#ff0020;" class="pull-right"></div>
                    <div class="col-md-3 col-md-offset-6">
                    	<label>Search</label>
                    	<div class="input-group inputWithButton">
                        	<input class="form-control" type="text" id="searchInputId"/>
                            <span class="input-group-addon">
                            	<button class="btn btn-success" id="serachButtonId" onclick="getPrePopulateTheDataDetails()">SEARCH</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                	<div class="col-md-12 m_top10">
                    	<div class="bg-info">
                        	<!--<p>Currently Showing Search Results - 40<span class="pull-right">Clear Search Results</span></p>-->	
                        </div>
                    </div>
                	<div class="col-md-12 m_top10">
                    	<div class="panel panel-default">
                        	<div class="panel-heading bg_cc">
                            	<h4 class="panel-title text-capitalise">all applications</h4>
                            </div>
                            <div class="panel-body pad_0">
                            	<table id="BleedingCadreDetailsId" class="table table-condensed">
								<!--swadhin
                                	<thead style="background:#EBEBEB">
                                    	<th>Membership No</th>
                                        <th>Name</th>
                                        <th>Mobile No</th>
                                        <th>Registration Status</th>
                                        <th>Blood Bag No</th>
                                        <th>Blood Bag Type	</th>
										<th>Blood Bag Quantity	</th>
                                        <th>Quantity</th>
                                        <th></th>
                                    </thead>
                                    <tbody>
                                    	<tr>
                                        	<td>565654</td>
                                            <td>Santhosh</td>
                                            <td>9654894654</td>
                                            <td>
                                            	<select class="form-control">
													
                                                	<option>Approved</option>
													<option>Rejected</option>
													<option>pending</option>
                                                </select>
                                            </td>
                                            <td>
                                            	<input type="text" class="form-control" style="width:100px"/>
                                            </td>
                                            <td>
                                            	<select class="form-control">
                                                	<option>Approved</option>
													
                                                </select>
                                            </td>
											<td>
                                            	<select class="form-control">
                                                	<option>With Sagm 350ml</option>
													<option>With Sagm 450ml</option>
                                                </select>
                                            </td>
											<td>
												<select class="form-control">
                                                	<option>350ml</option>
													<option>450ml</option>
                                                </select>
											</td>
                                            <td>
                                            	<button class="btn btn-success btn-sm">SUBMIT</button>
                                            </td>
                                        </tr>
                                        <tr>
                                        	<td>565654</td>
                                            <td>Santhosh</td>
                                            <td>9654894654</td>
                                            <td>
                                            	<select class="form-control">
													<option>Rejected</option>
                                                </select>
                                            </td>
											<td>
                                            	<input type="text" class="form-control" style="width:100px"/>
                                            </td>
                                            <td colspan="3">
                                            	<input type="text" placeholder="remarks" class="form-control"/>
                                            </td>
                                            <td>
                                            	<button class="btn btn-success btn-sm">SUBMIT</button>
                                            </td>
                                        </tr>
										<tr>
                                        	<td>565654</td>
                                            <td>Santhosh</td>
                                            <td>9654894654</td>
                                            <td>
                                            	<select class="form-control">
													<option>pending</option>
                                                </select>
                                            </td>
											<td>
                                            	<input type="text" class="form-control" style="width:100px"/>
                                            </td>
                                            <td colspan="3">
                                            	<input type="text" placeholder="remarks" class="form-control"/>
                                            </td>
                                            <td>
                                            	<button class="btn btn-success btn-sm">SUBMIT</button>
                                            </td>
                                        </tr>
                                    </tbody>
									swadhin-->
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
             </div>
         </div>
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/bloodBankScript/bloodBankBleading.js" type="text/javascript"></script>
<script>
function getPrePopulateTheDataDetails(){
	$('#errorDiv').html("");
	var search = $("#searchInputId").val();
	if(search==0){
		$('#errorDiv').html('Please Select Search');
		return;
	}
	
	var jObj={
		searchType:search
	};
	$.ajax({
		type:"GET",
		url:'getPrePopulateTheDataDetailsAction.action',
		dataType:'json',
		data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		buildBleedingCadreDetails(result);
	});
}
</script>
</body>
</html>
