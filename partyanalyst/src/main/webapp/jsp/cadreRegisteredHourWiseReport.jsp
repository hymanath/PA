<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"> 
<script type="text/javascript" src="js/exportexcel.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
<script type="text/javascript" src="js/sample.js"></script>

<style>

</style>
</head>
<body>
   <div class="container m_top10">		
			<div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> </h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
			   <div id="errorDiv" align="center" ></div>
			   <table  style="margin-left: 270px;">
				   <tr><td><b>From Date :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="fromDate"/></td></tr>
				   <tr><td><b>To Date   :</b>&nbsp;</td><td><input type="text" readonly="readonly" id="toDate" /></td></tr>
				  <tr>
					  <td></td><td><input type="button" style="margin-top: 13px;" class="btn btn-success" onclick="getHourWiseUserColletedData();" value="Submit"/> 
						<div><img id="ajaxImgId" style="width:70px;display:none;" src="images/Loading-data.gif"/></div>
					  </td>
				  </tr>
			 </table>
			 <div id="tableDataDiv"></div>					 
			 </div>			
			</div>			
	</div>
 
   <script>
		$("#fromDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#fromDate").datepicker("setDate", new Date());
		$("#toDate").datepicker({
			dateFormat: "dd-mm-yy",
			changeMonth: true,
			changeYear: true,
			maxDate: new Date()
		})
		$("#toDate").datepicker("setDate", new Date());
 

	function getHourWiseUserColletedData(){		
		$("#tableDataDiv").html('');	
		$("#ajaxImgId").show();
		var jObj = {		
		fromDate:$("#fromDate").val(),
		toDate:$("#toDate").val(),
		task:"hourWiseUsersCollectedData"
		}
		 $.ajax({
          type:'GET',
          url: 'getHourWiseUserColletedInfoAction.action',
         data : {task:JSON.stringify(jObj)} ,
       }).done(function(result){
				$("#ajaxImgId").hide();
				buildResultData(result);
		});
	}
  function buildResultData(result)
  {
   var str ='';
	if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 355px;margin-top:10px;"  class="btn" onclick="generateExcel(\'hourWiseTableId\');" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDivId" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover" id="hourWiseTableId"><thead>';
				str+='<tr>';								
				str+='<th rowspan="2">District</th>';
				str+='<th rowspan="2">Constituency</th>';
				str+='<th rowspan="2">User</th>';
				str+='<th rowspan="2">Name</th>';
				for(var i in result[0].infoList){												
					str+='<th colspan="24">'+result[0].infoList[i].date+'</th>';				
				}
				str+='</tr>';				
				str+='<tr>';
				for(var j in result[0].infoList){
					for(var k= 0;k<24;k++){
					   str+='<th>'+k+'</th>';	
					}					   
				}
				str+='</tr>';
			
				str+='</thead><tbody>';
				
				
				for(var i in result){
				  str+='<tr>';
				  str+='<td>'+result[i].district+'</td>';
				  str+='<td>'+result[i].constituency+'</td>';
				  str+='<td>'+result[i].name+'</td>';
				  str+='<td>'+result[i].uname+'</td>';
					for(var j in result[i].infoList){
					if(result[i].infoList[j].hours.length > 0){
						for(var k in result[i].infoList[j].hours){						
								str+='<td>'+result[i].infoList[j].hours[k]+'</td>';							
						}
					}	
					else{
					
						for(var i=0;i<24;i++){						
								str+='<td>0</td>';							
						}
					}
					}
				 str+='</tr>';
				}	
				
				str+='</tbody></table></div>';
			
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#tableDataDiv").html(str);

		 $("#hourWiseTableId").dataTable({
			aLengthMenu: [
						[25, 50, 100, 200, -1],
						[25, 50, 100, 200, "All"]
					],
					iDisplayLength: 25
		 });
		
  }
  function generateExcel(reqId){
     tableToExcel(reqId, 'User Cadre Collected Count Hour Wise');
   }
  	
   </script>
</body>
</html>