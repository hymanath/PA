


<div class="row" id="searchcadrenewDiv">	
	
	<div class="row">
		<div style="border-top:1px solid #fff;" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2 text-center m_top20" id="step2Id">
			<span class="badge" style="z-index: 2; margin-top: -10px;">STEP- 2</span>
	   
		</div>
	</div>
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4 id="headingDiv" class="text-uppercase"> Search Candidate For selected Designation </h4>
			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline ">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  onclick="refreshExistingDetails();"  value="5"> Advanced Search &nbsp;&nbsp;</label>
						<input type="hidden" id="cadreSearchType" value="membershipId" />
					</div>				  
				</div>
			</div>
			<div class="row m_top20" id="committeLocationsDiv" >
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6  m_top20">
					<div class="form-group col-xs-12 pull-right">
						<label for="searchLevel">SEARCH LEVEL <img id="dataLoadingsImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/></label>
						<select class="form-control" id="searchLevelId" onchange="updateSearchType(this.value)">
						<option value="0">Select Location</option>
						<option value="1">Village/Ward</option>
						<option value="2">Town/Division</option>
						</select >
						<div id="searchLevelErrDiv"></div>
					 </div>
				</div>	
				<div class="col-md-4  col-sm-6 col-xs-6  m_top20">
					<div class="form-group col-xs-12 pull-right">
						<label for="committeLocationId">SELECT LOCATION </label>
						<select class="form-control" id="committeLocationId" ><option value="0">Select Location</option></select >
						<div id="committeLocationIdErr"></div>
					 </div>
				</div>			
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0"  id="basicSearchDiv">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO / NAME"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<!--<h6>Advanced Search</h6>-->
					<div id="advancedSearchErrDiv"></div>
					<div class="row">					
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste-Group
								<select class="form-control col-md-12 col-sm-12 col-xs-12 " id="casteCategory" onchange="casteDetailsByGroupId();">
								<option value="0" selected>All</option>
								<option value="1">OC</option>
								<option value="2">BC</option>
								<option value="3">SC</option>
								<option value="4">ST</option>
								</select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-5 ">
							<label>Caste Name
								
								<s:select theme="simple" cssClass="form-control editClass col-md-12 col-sm-12 col-xs-12" id="casteList" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width: 200px;"/>
							</label>
						</div>
						
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age Range
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="ageRange" onchange="clearbetwbAgeFields()" ><option>select</option></select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							 <b>Between Age<br>
								<input type="text" id="fromAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" From "/> - <input type="text" id="toAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" To  "/> 
							</b>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-4 ">
							<label>Gender
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="gender"><option value="0" selected>All</option><option  value="1" >Male</option><option  value="2" >Female</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
						</div>				
					</div>			
				</div>			
			</div>
			</div>