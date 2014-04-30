<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IMPORTANT FAMILY DETAILS</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript" src="js/commonVoterDetails.js"></script>

	<script type="text/javascript" src="js/blockui.js"></script>

	<script type="text/javascript" src="js/blockui.js"></script>
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
	<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
	<script type="text/javascript" src="js/homePage/newHomePage.js"> </script>
	<script type="text/javascript" src="js/homePage/newHomePage_inner.js"> </script>
	<link href="styles/tdphome_inner_styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/newhome_styles.css">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" src="js/md5.js"></script>


<style type="text/css">

  #ajaxImage{
    display:none; 
  }
  
#buildTableDiv table,#buildTableDiv1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#buildTableDiv table tr:nth-child(even),#buildTableDiv1 table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}
#buildTableDiv table td,#buildTableDiv1 table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#buildTableDiv table th,#buildTableDiv1 table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	color:#333333;
	border:1px solid #d3d3d3;
	}
#buildTableDiv,#buildTableDiv1{
	font-family : arial;
	font-size: 13px;
    margin-top:0px;
	padding: 10px 10px 10px 0px;
	 width: 900px;
}

#headingImgStyle{
	 background: none repeat scroll 0 0 #05A8E9;
    border-radius: 5px 5px 5px 5px;
    color: #FFFDFF;
    padding: 6px;
    text-align: center;
    width: 940px;
}
#headDivStyle{
	color: #ffffff;
    font-size: 20px;
    font-weight: normal;
    padding:20px;
}
</style>

</head>
<body>
<div id="mainDiv" align="center">
 	<div id= "headDivStyle">
	<table border="0" cellpadding="0" cellspacing="0">          
		<tr><!--<td><img src="images/icons/cadreReport/bg_left.png"/></td>-->
			<td><div id="headingImgStyle"><span>&nbsp;Important Family Details</span></div></td>
	       <!--<td><img src="images/icons/cadreReport/bg_right.png"/></td>-->
	   </tr>
		</table>
	</div>

				<div class="form-horizontal boothResults " name='boothSelection' style="display:block;border: 3px solid #d3d3d3;margin-top: -20px;margin-bottom: 20px;">

				<div class="control-group" id="errorDiv" style="color:red;"></div>

				<div>
				   <table>
				     <tr>
				        <td><label class="span2" for="firstName">Select Constituency</label></td>
						<td><s:select cssClass="selectstyle" theme="simple" id="districtListForImp" name="crossVotingYear" list="constituencyList" listKey="id" listValue="name" onChange=""/></td>
				        <td><label class="span2" for="firstName">Publication Date</label></td>
						<td><select id="prevPublicationIdForImp">
							<option value="0">Select Publication Date</option>
							<option value="10">1-2-2014</option>
							<option value="9">1-1-2014</option>
							<option value="8">1-2-2013</option>
							<option value="7">1-1-2013</option>
						</select></td>
						<td></td>
				     </tr>
				     <tr>
						<td><label class="span2" for="firstName">Form Value</label></td><td><input type="text" id="fromAgeRange" style="width: 137px;"></input></td>
						<td><label class="span2" for="firstName">To Value</label></td><td><input type="text" id="toAgeRange" style="width: 137px;"></input></td>
				    </tr>
				   </table>
				</div>
				<button class="btn btn-small btnStyle" data-dismiss="modal" aria-hidden="true" onclick="getImpFamilyDetails1();" style="margin-top:8px;">SUBMIT</button>
				
			</div>
			</div>

</div>


<script>

var constituencyId = '${constituencyId}';
function getPanchayatAssemblyResultDetails()
{
	    var constituencyId = $("#constituencyId").val();
		$("#ajaxImage").show();
		  var jsObj = 
	       {
		      constituencyId:constituencyId,
              effectElectionId : 38,
			  partyId:872,
	       }	
		    $.ajax({
				type : "POST",
				url : "getPanchayatAssemblyResultDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
					if(result != null && result.length > 0)
					{
						$("#ajaxImage").hide();
					  buildChartResult(result);
					  buildTableResult(result,1);
                      buildTableResult1(result,2)
					}
					else{
						  $("#ajaxImage").hide();
		                  $("#buildGraphDiv").html("");
		                  $("#buildTableDiv").html("");
		                  $("#buildGraphDiv").html("<span style='color:red;font-weight:bold;font-size:12px;'>No Data Available</span>");
					  }
			});
}
      
var dataArray = new Array();

function buildChartResult(result)
{

	for(var j in result)
	{
			var data = 
			{
				name : result[j].name+"-2009",
				value : result[j].margin
			}
			dataArray.push(data);
			var name = "";
			var val = "";
			if(result[j].rank == 1)
			{
					  
			   if(result[j].winPartyName == "TDP")
				{
					  var name = result[j].name+"-2013";
					  var val  = result[j].margin;
				}
				else{
					var name = result[j].name+"-2013";
					 var val   = parseFloat(result[j].margin)/2;
				}								
			}
			else
			{
			   if(result[j].winPartyName == "TDP")
				{
				   var name = result[j].name+"-2013";
				   var val   = parseFloat(result[j].margin)*2;
				}
				else{
					var name = result[j].name+"-2013";
					var val   = result[j].margin;
				}
			  ;
			}
			var data1 = 
			{
				name : name,
				value : val
			}

			dataArray.push(data1);
	}
	var data = new google.visualization.DataTable();
    data.addColumn('string', 'name');
	data.addColumn('number', 'Margin Values');
	data.addRows(dataArray.length);
	for(var i = 0 ; i < dataArray.length ;i++)
	{
		data.setValue(i,0,dataArray[i].name);
		data.setValue(i,1,dataArray[i].value);
	}

  
		var title = 'Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat'; 
        var options = {'title':title,
			vAxis: {
              title: 'PANCHAYAT',
			  'fontSize': 18
            },
            hAxis: {
              title: 'MARGIN'
            },
		    'width': '100%',
            'height': 1800,
            'fontSize': 12,
		    'chartArea': {top: 50, right: 50, bottom: 30, height:1800, width:'75%'}

        };
         var chart = new google.visualization.BarChart(document.getElementById('buildGraphDiv'));       
	     chart.draw(data, options); 
}
function buildTableResult(result,rank)
{
		var str ="<div><b><center><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr>";
		
		for(var j = 0 ; j< result.length ; j++){	
	      if(result[j].rank == rank){
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		 str+="<td>"+result[j].winPartyName+"</td>";
		  }
        }
        str+="</table><br/>";		
      $("#buildTableDiv").html(str);

}

function buildTableResult1(result,rank)
{
		var str ="<div><b><center><h5>Election Results Comparision Chart b/w 2009 Assembly & 2013 Panchayat</h5></center></b></div><br/>";
		str+="<table><tr>";
		str+="<th>Panchayat</th>";
		str+="<th>Total Votes</th>";
		str+="<th>Votes Polled</th>";
		str+="<th>TDP Gained</th>";
		str+="<th>margin</th>";
		str+="<th>2013 Total Votes</th>";
		str+="<th>Win Party Name</th>";
		str+="  </tr>";
		
		for(var j = 0 ; j< result.length ; j++){	
	     if(result[j].rank == rank){
         str+="<tr>";
		 str+="<td>"+result[j].name+"</td>";
		 str+="<td>"+result[j].totalVoters+"</td>";
		 str+="<td>"+result[j].totalValidVotes+"</td>";
		 str+="<td>"+result[j].selectedPartyTotalVoters+"</td>";
		 str+="<td>"+result[j].margin+"</td>";
		 str+="<td>"+result[j].winPartyTotal+"</td>";
		 str+="<td>"+result[j].winPartyName+"</td>";
		}
        }
        str+="</table><br/>";		
      $("#buildTableDiv1").html(str);
}


function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task=="forConstituencies")
								{

								}
								else if(jsObj.task == "getFamilyDetails")
								{
									
									if(myResults != null)
									{
										$.unblockUI();
										window.open(myResults[0].influencePartyName);
									}
									else
									{
										$.unblockUI();
										alert("Error occured while creating the Report");
									}
								}
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
 	
 	function getImpFamilyDetails1(){
 		
 		var constituencyId = $("#districtListForImp").val();
 		var publicationId = $("#prevPublicationIdForImp").val();
 		var fromValue =  $.trim($("#fromAgeRange").val());
 		var toValue =  $.trim($("#toAgeRange").val());
 		var maxr = null;
 		var str ='<font color="red">';
 		var flag = true;
 		if(constituencyId == 0)
 		{
 			str+='Select Constituency<br/>';
 			flag =false;
 		}
 		if(publicationId == 0)
 		{
 			str+='Select Publication<br/>';
 			flag =false;
 		}
 		if(fromValue == "" || isNaN(fromValue))
 		{
 			str+='Enter From Value Must Be Number<br/>';
 			flag =false;
 		}
 		else if(isNaN(toValue))
 		{
 			str+='Enter To Value Must Be Number<br/>';
 			flag =false;
 		}
 		if(toValue == "")
 		{
 	     toValue = 0;
 		}
 		else
 		{
 		 if(toValue < fromValue)
 		{
 	        str+='From Value must be greter than To value<br/>';
 			flag =false;
 		}
 		}
 		
 		if(flag == false)
 		{
 				$("#errorDiv").html(str);
 				return;
 		}
 		getImpFamilyDetails();
 	}
</script>
</body>
</html>
