<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BOOTH WISE RESULTS</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript" src="js/commonVoterDetails.js"></script>
<style type="text/css">
table tr.separator { height: 40px; }

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
</style>
<style type="text/css">
#boothResultsDiv {
    text-align: left;
	margin-left: 50px;
	font-size: 12px;
	
}

.boothResultHeadingDiv
{
   margin-top: 20px;
   color:#23318B;
   text-decoration: underline;
   font-weight: bold;
   font-size:13px;
   font-family:verdana;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	background-color:#C4DEFF;
	color:#3F546F;
}

.yui-skin-sam .yui-dt-liner {
	padding:4px 8px;
	max-width:350px;
	word-wrap:break-word;
}

#boothResultsTableId th{
	background-color:#C4DEFF;
	text-align:center;
	font-weight:bold;
	color:#1031B6;
	height:30px;
}

#boothResultsTableId td{

	/*color:#180206;
	font-weight:bold;
	width:140px;
	border: 1px solid #b0bec7;
	padding: 0 0 0 10px;*/

color:#5B5B5B;
width: 660px;
height: 25px;
font-family: tahoma, arial, helvetica, verdana, sans-serif;
font-size: 13px;
font-weight: bold;
text-decoration: none;
background: transparent;
border: 1px solid #b0bec7;
padding: 0 0 0 10px;
}
#boothResultsTableId{width:95%;}
#boothResultsTableId tr:nth-child(even){background:#F8FBFF;}
#boothResultsTableId tr:nth-child(odd){background:#F8FBFF;}


#titleDiv
{
	color: #ffffff;
    font-size: 13px;
    font-weight: bold;
    margin-top: 20px;
	margin-left:auto;
	margin-right:auto;
	background:#06ABEA;
	padding:5px;
	border-radius:5px;
	padding-left: 30px;
    width: 470px;
}

}
.resultTableDiv{
	width :100%;
	margin-bottom:5px;
}
#mainDiv
{
	margin-left:auto;
	margin-right:auto;
	width:980px;
}

table#searchresultsTable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	margin-top:10px;
}
table#searchresultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table#searchresultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #5B5B5B;
	background-color: #ffffff;
}

#boothResultsMarkup table{border: 1px solid #C4DEFF;margin-right: 15px;
	font-family:arial;
	margin-bottom:20px;
	
}

a, .reg-form li a:hover, a.change:hover {
    cursor: pointer;
    outline: medium none;
    text-decoration: none;
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

<!--<div id="boothWiseResult"></div>-->
 	<div id= "headDivStyle">
	<table border="0" cellpadding="0" cellspacing="0">          
		<tr><!--<td><img src="images/icons/cadreReport/bg_left.png"/></td>-->
			<td><div id="headingImgStyle"><span>&nbsp;Booth Wise Results</span></div></td>
	       <!--<td><img src="images/icons/cadreReport/bg_right.png"/></td>-->
	   </tr>
		</table>
	</div>
				<div class="form-horizontal boothResults " name='boothSelection' style="display:block;border: 3px solid #d3d3d3;margin-top: -20px;margin-bottom: 20px;">

				<div class="control-group" id="errorDiv" style="color:red;"></div>

					<s:if test="#session.parlConstis  != null && #session.parlConstis.size() > 1">
					<div class="control-group" align="center">
					</s:if>
					<s:else>
					<div class="control-group" style="display:none">
					</s:else>
						
						<div  style="margin-left: 300px;">
							<label class="span2" for="firstName">Election Type</label>
							<label class="span3"  style="margin-left: auto;"><input type="radio" name="electionType" value="2" checked="checked">  Assembly  </input><input type="radio" name="electionType" value="1">  Parliament</input></label>
						</div>
						
						
					</div>
					<table>
					<tr class="separator">
					<td><label class="span2" for="firstName">Select Year</label></td>
					<td><s:select theme="simple" list="electionYearsList" name="electionYear" listKey="id" listValue="name" headerKey="0" headerValue="Select Year" id="electionYearsId" onChange="constituencyOptions('')"/> </td>
					</tr>
					<tr class="separator">
					<td><label class="span2" for="firstName">Constituency</label></td>
					<td><select id="constiId" onChange="getPartiesForElections('')" name="constituencyName">
								<option value='0'>Select Constituency</option>
							</select></td>
					</tr>
					<tr class="separator">
					<td><label class="span2" for="firstName">Party</label></td>
					<td><select id="partyId" name="partyName">
								<option value='0'>Select Party</option>
							</select></td>
					</tr>
					</table>
					<button class="btn btn-small btnStyle" data-dismiss="modal" aria-hidden="true" onclick="submitRes()" style="margin-top:8px;">SUBMIT</button>

</div>



		


<!--<div id="boothResultsDiv"></div>-->

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

function submitRes(){
	
	var electionYear=$("#electionYearsId option:selected").text();
	var val=$("#electionYearsId option:selected").val();
	if(val==0){$('#errorDiv').html("Please Select Election Year"); return ;}
	
	var constituencyName=$("#constiId option:selected").val();
	if(constituencyName==0){$('#errorDiv').html("Please Select Constituency;"); return;}
	
	var partyName=$("#partyId option:selected").val();
	if(partyName==0){$('#errorDiv').html("Please Select Party"); return;}
	
	$('#errorDiv').html("");
	var boothResultsWindow = window.open("partyBoothResult2Action.action?constituencyName="+constituencyName+"&electionYear="+electionYear+"&partyName="+partyName);


		/*var jsObj =
		{  	
			constituencyName:constituencyName,
			electionYear:electionYear,
			partyName:partyName,
           	task:'ajaxpartyBoothResult'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "ajaxpartyBoothResult2Action.action?"+rparam;
		
		callAjax(jsObj, url);*/

}

function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task=="forConstituencies"){

									if(jsObj.type == "default")
										buildConstituenciesDefault(myResults);
									else
 									  buildConstituencies(myResults);
								}
								if(jsObj.task =="forParty")
								{
									buildPartiesSelectBox(myResults,jsObj.type);
								}
								if(jsObj.task =="ajaxpartyBoothResult")
								{
									buildPartyBoothResult(myResults,jsObj.type);
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

function getPartiesForElections(type){
	var electionYear=$("#electionYearsId option:selected").text();
	var constiId=$("#constiId option:selected").val();
	var jsObj =
		{  	
			electionYear:electionYear,
			constituencyId:constiId,
            type:type,
			task:'forParty'
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "optionsForBoothAction.action?"+rparam;
		callAjax(jsObj, url);
}
function buildPartiesSelectBox(myResult ,type){
	if(myResult == null || myResult.length == 0)
		return;
	
	$('#errorDiv').html("");
	var electionYearsElmt = document.getElementById("partyId");
	electionYearsElmt.options.length=0;

	var option = document.createElement('option');
	option.value = "0";
	option.text = "Select Party";
	electionYearsElmt.add(option);
	
	
	for(var i in myResult)
	{
		option = document.createElement('option');
		option.value = myResult[i].id;
		option.text = myResult[i].name;
		try
		{
			electionYearsElmt.add(option,null); // standards compliant
		}
		catch(ex)
		{
			electionYearsElmt.add(option); // IE only
		}
	}

	if(type == "default")
		$('#partyId').val(872);

}
function buildPartyBoothResult(myResult ,type){
if(myResult == null || myResult.length == 0)
		return;


var str = ''; 
str+='<div id="titleDiv"><s:property value="boothResult.constituencyName" /> Constituency Booth Results for '+myResult.partyName+' in  '+myResult.electionYear+'&nbsp;'+myResult.electionType+'</div>';
str+='<div class="resultTableDiv">';
str+='<div class="boothResultHeadingDiv" style="margin-eft:15px;">Candidate Details : </div>';
str+='<table class="searchresultsTable" id="searchresultsTable" style="width: auto; float: left;">';
str+='	<tr>';
str+='		<th style="background-color:#C4DEFF;">Candidate Name</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.candidateName+'  [ Rank - '+myResult.rank+'  ]<br></td>';

str+='		<th style="background-color:#C4DEFF;">Total Votes</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.totalVotes+'</td>';
str+='	</tr>';

str+='	<tr>';
str+='	<th style="background-color:#C4DEFF;">Total Valid Votes</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.totalValidVotes+'</td>	';
		
str+='		<th style="background-color:#C4DEFF;">Voting Percentage</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.votingPercentage+'</td>';
	
str+='	</tr>';
str+='	<tr>';
		
str+='		<th style="background-color:#C4DEFF;">Total Votes Gained</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.votesGained+'</td>';

str+='		<th style="background-color:#C4DEFF;">Total Votes Gained Percentage</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.percentage+'</td>';
		
str+='	</tr>';

str+='	<tr id="wonInfo">';
		
str+='		<th style="background-color:#C4DEFF;">Winning Candidate</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.wonCandidate[0][1]+' - '+myResult.wonCandidate[0][2]+'</td>';

str+='		<th style="background-color:#C4DEFF;">Margin Votes</th>';
str+='		<td style="background-color:#F8FBFF;">'+myResult.marginVotes+'</td>';
		
str+='	</tr>';

str+='</table>';
str+='</div>';



str+='<BR><BR><BR><BR><BR><BR>';

str+='<div style="float:left;width:auto;">';
str+='<table><tr><td>';
str+='			<div class="boothResultHeadingDiv" style="margin-left:70px;">Polling Percentage vs Party Votes Percentage</div><br>';
str+='			<table id="boothResultsTableId">';
str+='				<tr> ';	 	
str+='			       <th>Polling % Range</th>';
str+='				   <th>Total No of Booths</th>';
str+='				   <th>Party Votes %</th></tr>';
for(var i in myResult.perWiseboothResults){
str+='				 <tr><td>'+myResult.perWiseboothResults[i].location+'</td>';
if(myResult.perWiseboothResults[i].votesEarned = 0){
str+='						<td id="partyvotesEarnedId"><a title="Click to Know all the Booths Details">'+myResult.perWiseboothResults[i].votesEarned+'</a></td>';
}
if(myResult.perWiseboothResults[i].votesEarned == 0){
str+='						<td id="partyvotesEarnedId"><span style="color:#3983A8;">'+myResult.perWiseboothResults[i].votesEarned+'</span></td>';
}
str+='				   <td>'+myResult.perWiseboothResults[i].percentage+'</td>';
str+='				 </tr>';
}
str+='			</table>';
str+='	</td><td><div class="boothResultHeadingDiv" style="margin-left:80px;">Party Votes Percentage vs Polling Percentage</div><br>';
str+='			<table id="boothResultsTableId">';
str+='				<tr><th>Party Votes % Range</th>';
str+='				   <th>Total No of Booths</th>';
str+='				   <th>Polling %</th></tr>';
for(var i in myResult.partyPerWiseboothResults){
str+='				 <tr><td>'+myResult.partyPerWiseboothResults[i].location+'</td>';
if(myResult.partyPerWiseboothResults[i].votesEarned != 0){
str+='						<td id="pollingPercentId"><a title="Click to Know all the Booths Details">'+myResult.partyPerWiseboothResults[i].votesEarned+'</a></td>';
}
if(myResult.partyPerWiseboothResults[i].votesEarned == 0){
str+='						<td id="pollingPercentId"><span style="color:#3983A8;">'+myResult.partyPerWiseboothResults[i].votesEarned+'</span></td>';
}
str+='				   <td>'+myResult.partyPerWiseboothResults[i].percentage+'</td>';
str+='				 </tr>';
}
str+='			</table>';

str+='		</td></tr></table></div>';







$("#boothResultsDiv").html(str);





}


</script>
</body>
</html>
