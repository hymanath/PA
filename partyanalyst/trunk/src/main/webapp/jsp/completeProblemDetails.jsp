<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" href="styles/assets/css/problemmanagstyle.css" rel="stylesheet">
<style>
  #postedon{
    float:left;
    margin-left:19px;
   }
   .btn-group > .dropdown-toggle {
    height: 28px;
  }
  .errStyle{
    background-color:#FFFFFF;
	font-weight:bold;
	width:100%;
	height:300px;
	text-align:center;
	padding-top:50px;
  }
</style>
</table>

<article class="background">

<div class="container" ><!-- Container Opening -->

<h2 class="h1header"> Your Locality Problems</h2>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess != 'true' ">
<s:if test="completeProblemDetailsVO.userStatus == 'both' && completeProblemDetailsVO.isTaken == 'true' && completeProblemDetailsVO.changedToPrivate == 'true' ">
  <div class="errStyle">We Are Sorry To Inform,This Problem Is Moved To Private State By The Posted User</div>
</s:if>
<s:else>
<div class="span12" style="margin-top:25px;">

<div class="row">
<div class="span8 right-panel m5-left" style="background:#fff;">
<div class="span1 pull-left m0-left" id="userdiv">
			<div class="userdesc">
			
			 <h5>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</h5>
			    <span>Friends(25)</span>
				<s:if test=" (completeProblemDetailsVO.userStatus == 'both' || completeProblemDetailsVO.userStatus == 'freeuser') && completeProblemDetailsVO.isConnectPeopleReq !='false' ">
			      <a href="#"><i class="icon-plus-sign"></i>Connect Now</a>
			    </s:if>
			 </div>
			 
          <a class="thumbnail" href="#" >
             <s:if test="completeProblemDetailsVO.profileImg != null ">
                <img alt="" src="pictures/profiles/${completeProblemDetailsVO.profileImg}">
			 </s:if>
			  <s:if test="completeProblemDetailsVO.profileImg == null ">
                <img alt="" src="http://placehold.it/120x80">
			 </s:if>
			 
          </a>
		  <!--<a class="label">Posted by: <b>${completeProblemDetailsVO.firstName}&nbsp; ${completeProblemDetailsVO.lastName}</b></a>-->
</div>
<div class="span7 subheader-problem" >
      <h2 class="span5 pull-left">${completeProblemDetailsVO.problemTitle} </h2>
       <s:if test="completeProblemDetailsVO.isPublic == 'true' && completeProblemDetailsVO.userStatus != 'notlogged' ">
	   <div class="pull-right">
        <div class="btn-group dropup inline" style="display:inline-block;width:100px;">
          <button class="btn btn-primary"><i class="icon-share icon-white"></i> Share</button>
          <button data-toggle="dropdown" class="btn dropdown-toggle btn-primary"><span class="caret"></span></button>
          <ul class="dropdown-menu pull-right">
            <li><a href="#"><i class="icon-hand-right"></i> Facebook </a></li>
			<li><a href="#"><i class="icon-hand-right"></i> Twitter </a></li>
			<li><a href="#"><i class="icon-hand-right"></i> Google + </a></li>
			<li class="divider"></li>            
          </ul>
         </div>
		 <div class="m-t10">
                        <span >
						   
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span></div>
        </div>
        </s:if>
		
		  <div class="row m-t10">
            <div class="span5"><h5>${completeProblemDetailsVO.problemCompleteLoc}</h5></div>
          </div>
          <div>
           <div id="postedon">Posted On : ${completeProblemDetailsVO.identifiedOn}</div>
           <div style="float:right">Existing From : ${completeProblemDetailsVO.existingFrom}</div>
          </div>
      </div>


<div class="row m-t10">
		<div class="span pull-left">
                     <p>${completeProblemDetailsVO.problemDesc}</p>
					
	</div>
</div>	


<div class="tabbable tabs-left">
        <ul class="nav nav-tabs">
          <li class="active"><a data-toggle="tab" href="#lA">Details</a></li>
          <li><a data-toggle="tab" href="#lB">Activities</a></li>
          <!--<li><a data-toggle="tab" href="#lC">Problem Status</a></li>-->
        </ul>
        <div class="tab-content">
          <div id="lA" class="tab-pane active">
		  <h2>Details</h2>
            <p><ul class="thumbnails">

		<s:if test="completeProblemDetailsVO.problemStatus != null  && completeProblemDetailsVO.modifyAccess == 'true'  && (completeProblemDetailsVO.userStatus == 'customer' || completeProblemDetailsVO.userStatus == 'both') " >
		
        <li class="span6 thumbnail well" style="height:70px;;margin-bottom:0px;border-radius:0px 0px 0px 0px;border-bottom:1px solid #d4d4d4;">
		<h3>Department</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.departmentOrganisation != null" >
		    <p>Currently Assigned To <b>${completeProblemDetailsVO.problemStatus.departmentOrganisation}</b>.</p>
			<s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
			<div>
		    <a href="#" class="pull-left btn btn-mini">Change</a>
		    <a href="#" class="pull-right btn btn-mini">Remove</a> 		  
		   </div>
		   </s:if>
		</s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.departmentOrganisation == null" >
		    <p>Not Assigned To Any Department.</p>
			<s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
			<div>
		    <a href="#" class="pull-left btn btn-mini">Assign</a>	  
		   </div>
		   </s:if>
		</s:if>

        </li>
		
        <li class="span6 thumbnail well" style="height:70px;margin-bottom:0px;border-radius:0px 0px 0px 0px;border-bottom:1px solid #d4d4d4;">
		<h3>Problem Type</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.probClassification != null" >
		   <p>Currenly Assigned To <b>${completeProblemDetailsVO.problemStatus.probClassification}</b>.</p>
		   <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		   <div>
		    <a href="#" class="pull-left btn btn-mini">Change</a>		  
		   </div>
		   </s:if>
		</s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.probClassification == null" >
		   <p>Not Assigned To Any Problem Type.</p>
		   <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		   <div>
		    <a href="#" class="pull-left btn btn-mini">Assign</a>		  
		   </div>
		   </s:if>
		</s:if>
        </li>
        <li class="span6 thumbnail well" style="height:70px;margin-bottom:0px;border-radius:0px 0px 0px 0px;border-bottom:1px solid #d4d4d4;">

		<h3>Cadre</h3>
		<s:if test="completeProblemDetailsVO.problemStatus.cadre != null" >
		<p>This Problem Has Been Assigned To <b>${completeProblemDetailsVO.problemStatus.cadre}</b>.</p>
		  <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		  <div>
		    <a href="#" class="pull-left btn btn-mini">Change</a>
		    <a href="#" class="pull-right btn btn-mini">Remove</a> 		  
		   </div>
		  </s:if>
		 </s:if>
		<s:if test="completeProblemDetailsVO.problemStatus.cadre == null" >
		<p>This Problem Has Not Been Assigned To Any Cadre.</p>
		  <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >
		  <div>
		    <a href="#" class="pull-left btn btn-mini">Assign</a>		  
		   </div>
		  </s:if>
		 </s:if>
        </li>
		</s:if>
        <li class="span6 thumbnail well" style="height:70px;margin-bottom:0px;border-radius:0px 0px 0px 0px;border-bottom:1px solid #d4d4d4;">

		<h3>Status:</h3>
		<p>This Problem Is Under <b>${completeProblemDetailsVO.status}</b> Stage.</p>
        <s:if test="completeProblemDetailsVO.modifyAccess == 'true' " >		
	    <div>
		<a href="#" class="pull-left btn btn-mini">Change</a>
		</div>
		</s:if>
        </li>
        <li class="span6 thumbnail well" style="height:150px;margin-bottom:0px;border-radius:0px 0px 0px 0px;border-bottom:1px solid #d4d4d4;">
		 <h3>Submissions</h3>
		 <s:if test="completeProblemDetailsVO.userStatus != 'notlogged' " >
		 <div>
		   <a href="#" class="pull-right btn">Upload Files</a>
		</div>
		</s:if>
		 <div>
		  <img alt="" src="http://placehold.it/160x120">
		  <img alt="" src="http://placehold.it/160x120">
		 </div>
		
          <a  href="#">
            <!--<img alt="" src="http://placehold.it/160x120">-->
          </a>
        </li>
      </ul></p>
          </div>
          <div id="lB" class="tab-pane">
		  <h2>Activities</h2>
            <p>Test ME</p>
          </div>
         <!-- <div id="lC" class="tab-pane">
		  <h2>PROBLEM Status</h2>
            <p>Tabbed 3</p>
          </div>-->
        </div>
      </div>

					
</div> <!-- Left Panel _ Closed-->

<div class="span3 left-panel" >
<h3>Related Problems</h3>
<!-- Problesm Display Collection -->
<ul class="unstyled relatedproblem" style="width:200px;">
			<li>
						<h5>Electricity Problem</h5>
						<div>
							<span>
								<a href=# class="icon-star"></a>
								<a href=# class="icon-star"></a>
								<a href=# class="icon-star-empty"></a>
								<a href=# class="icon-star-empty"></a>
								<a href=# class="icon-star-empty"></a>
							</span>
							<h6>Hyderabad</h6>
						</div>
			</li>
			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>


			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>

			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>


			<li>
					<h5>Electricity Problem</h5>
					<div>
						<span>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
							<a href=# class="icon-star-empty"></a>
						</span>
						<h6>Hyderabad</h6>
					</div>
			</li>
</ul>
<!-- Problem Display Collection End-->



</div>

</div> <!-- Row [Span12]closed-->


</div>
</s:else>
</s:if>
<s:if test="completeProblemDetailsVO != null && completeProblemDetailsVO.noAccess == 'true' ">
<div class="errStyle">You Didn't Have Access To View This Content </div>
</s:if>
</div><!-- container Closing-->
</article>

</body>
</html>
<script src="styles/assets/js/google-code-prettify/jquery.js"></script>
<script src="styles/assets/js/google-code-prettify/bootstrap-dropdown.js"></script>
<script src="styles/assets/js/google-code-prettify/bootstrap-tab.js"></script>

<script>
$('.dropdown-toggle').dropdown();

/*$('#myTab a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
    });
/*var arr;
$(".icon-star-empty").hover(
      function () {
	  alert("test");
	     arr=$(this).parent().html();
        $(this).addClass("icon-star").removeClass("icon-star-empty").prevAll().addClass("icon-star").removeClass("icon-star-empty");
		}, 
      function () {
	    $(this).parent().html(arr);
        //$(this).removeClass("icon-star").addClass("icon-star-empty").prevAll().removeClass("icon-star").addClass("icon-star-empty");
      }
    );*/
	
	function callAjax(jsObj,url)
{	
	var callback = {			
	               success : function( o ) {
					try {

						myResults = YAHOO.lang.JSON.parse(o.responseText);

						if(jsObj.task =="saveProblemRatingDetails")
						{
							saveProblemRatingResults(myResults);
							
						}
						else if(jsObj.task =="getAvgProblemRating")
						{
							getAvgProblemRatingResults(myResults);
							
						}
						else if(jsObj.task =="saveProblemRatingDetails")
						{
							getRateWiseCountOfAProblem(myResults);
							
						}

						
					}catch (e) {   
					  // 	alert("Invalid JSON result" + e);   
					}  
	               },
	               scope : this,
	               failure : function( o ) {
	                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

/* function saveRatingOfAProblem()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		rating     : 3,
		task       : "saveProblemRatingDetails"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "saveProblemRatingDetailsAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
  
  function getAvgProblemRating()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		task       : "getAvgProblemRating"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getAvgProblemRatingAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
function rateWiseCountOfAProblem()
  {

	var jsObj = {
		time       : new Date().getTime(),
		problemId  : 17,
		task       : "rateWiseCountOfAProblem"

	};
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "rateWiseCountOfAProblemAction.action?"+rparam;
	 callAjax(jsObj,url);
  }
  */
 
 
 

</script>

