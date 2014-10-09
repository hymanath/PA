<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadre Registration Report</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

<script type="text/javascript" src="js/exportexcel.js"></script>
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   .imgStyle{
      margin-left: 75px;
      margin-top: 30px;
	}

  #userStatusDialogDIV{
    padding-top: 35px;
  }
	</style>
</head>
<body>
   <div class="container m_top10">
        <div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">Users Working Status</h3>
			</div>
		</div>
	    <div class="row-fluid ">
	       <div style="min-height: 300px;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
            <label style="margin-left:100px;"><b>From Date :</b>&nbsp;<input type="text" readonly="readonly" id="fromDate"/></label>
	             <label><b>To Date   :</b>&nbsp;<input type="text" readonly="readonly" id="toDate" /> </label>
	            <input type="button" class="btn btn-success" id="getCandidateDataCollectionInfoId" onclick="getCandidateDataCollectionInfo();" value="Submit"/>
				<img id="ajaxImgStyle" style="display:none;margin-left: 10px;" src="images/icons/search.gif"/>
	            <div id="userStatusDialogDIV"></div>
	      </div>
        </div>
   </div>
<script type="text/javascript">
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
   function getCandidateDataCollectionInfo(){
    $("#userStatusDialogDIV").html("");
	$("#getCandidateDataCollectionInfoId").attr("disabled","disabled");
	$("#ajaxImgStyle").show();
    $.ajax({
          type:'GET',
          url: 'getCadreDashBoardBasicInfo.action',
          data: {task:"candidateDataCollectionInfo",fromDate:$("#fromDate").val(),toDate:$("#toDate").val()}
       }).done(function(result){
	       var str='';
	       if(result.length > 0){
		        str+='<input type="button"  style="margin-bottom:15px;margin-left: 375px;"  class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		        str+='<div id="resultTableDiv" style="overflow-x:scroll;"><table class="table table-bordered table-striped table-hover"><thead>';
				str+='<tr>';
				str+='<th rowspan="2">User</th>';
				for(var i in result[0].infoList){
				  str+='<th colspan="3">'+result[0].infoList[i].date+'</th>';
				}
				str+='</tr>';
				str+='<tr>';
				for(var i in result[0].infoList){
				  str+='<th>Start Time</th>';
				  str+='<th>End Time</th>';
				  str+='<th>Count</th>';
				}
				str+='</tr>';
				str+='</thead><tbody>';
				for(var i in result){
				  str+='<tr>';
				  str+='  <td>'+result[i].name+'</td>';
				  for(var j in result[i].infoList){
				    if(result[i].infoList[j].area != null){
				      str+='  <td>'+result[i].infoList[j].area+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
					if(result[i].infoList[j].location != null){
				      str+='  <td>'+result[i].infoList[j].location+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
					if(result[i].infoList[j].totalCount != null){
				      str+='  <td>'+result[i].infoList[j].totalCount+'</td>';
					}else{
					  str+='  <td>-</td>';
					}
				  }
				  str+='</tr>';
				}
				str+='</tbody></table></div>';
				 str+='<input type="button" style="margin-top:15px;margin-left: 375px;" class="btn" onclick="generateExcel();" value="Click Here To Generate Excel"/>';
		   }else{
		     str+='<div style="font-weight:bold;padding-left: 375px;padding-top: 30px;">No Data Available</div>';
		   }
		   $("#userStatusDialogDIV").html(str);
		   $("#ajaxImgStyle").hide();
		   $("#getCandidateDataCollectionInfoId").removeAttr("disabled");
       });
   }
   function generateExcel(){
     tableToExcel("resultTableDiv", 'Users Working Status');
   }
</script>
</body>
</html>