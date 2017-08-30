Highcharts.setOptions({
                     colors: ['#3BCCBF', '#3BCCBF', '#3BCCBF', '#3BCCBF', '#3BCCBF']
         
                         });
            Highcharts.chart('gramPanchayatTransactions', {
                 chart: {
                     type: 'column'
                    },
                 title: {
                      text: '0 - 20+ Transaction GPs',
                      align: 'left'
                        },
                       
                        xAxis: {
                               type: 'category'
                             },
                        yAxis: {
                               visible: false
                            },
         
                         legend: {
                                enabled: false
                            },
                            plotOptions: {
                               series: {
							 borderWidth: 0,
							 dataLabels: {
								 enabled: true,
								 format: '{point.y}'
								},
							pointWidth: 40
								}
							},
					 credits: {
				 enabled:false
				 },
					 tooltip: {
						 headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						 pointFormat: '<span>{point.name}</span>: <b>{point.y}</b> of total<br/>'
					 },
					 navigation: {
							   buttonOptions: {
								 enabled: false
							  },
							},
					 series: [{
						 name: 'Brands',
						 colorByPoint: true,
						 data: [{
							 name: '0-5',
							 y: 800,
							 drilldown: '0-5'
						 }, {
							 name: '6-10',
							 y: 500,
							 drilldown: '6-10'
						 }, {
							 name: '11-15',
							 y: 900,
							 drilldown: '11-15'
						 }, {
							 name: '16-20',
							 y: 350,
							 drilldown: '16-20'
						 }, {
							 name: '20+',
							 y: 550,
							 drilldown: '20+'
						 }]
					 }]
				});
		  var dataArr = '';
	projectDataConstruction()
  function projectDataConstruction(){
	  var collapse='';
	
	  dataArr = ['District','Division','Mandal'];
	  
	 
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
	  collapse+='<div class="panel-group" id="accordionOverview" role="tablist" aria-multiselectable="true">';
	  for(var i in dataArr)
				{
				
				collapse+='<div class="panel panel-default panel-black">';
			    collapse+='<div class="panel-heading" role="tab" id="headingConsolidated'+dataArr[i]+'">';  
				if(i == 0)
				{
					collapse+='<a role="button" class="panelCollapseIcon" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordionOverview" href="#collapseConsolidated'+dataArr[i]+'" aria-expanded="true" aria-controls="collapseConsolidated'+dataArr[i]+'">';
				}else{
					collapse+='<a role="button" class="panelCollapseIcon collapsed" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordionOverview" href="#collapseConsolidated'+dataArr[i]+'" aria-expanded="true" aria-controls="collapseConsolidated'+dataArr[i]+'">';
				}
				
				collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level - overview</h4>';
				collapse+='</a>';
				collapse+='</div>';
				if(i == 0)
				{
					collapse+='<div id="collapseConsolidated'+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingConsolidated'+dataArr[i]+'">';
				}else{
					collapse+='<div id="collapseConsolidated'+dataArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingConsolidated'+dataArr[i]+'">';
				}
				
				collapse+='<div class="panel-body">';
				collapse+='<div class="row">';
				collapse+='<div class="col-sm-12">';
				collapse+='<div class="col-sm-3">';
				collapse+='<ul class="list-inline tableMenu" attr_block_name="'+dataArr[i]+'">';
				collapse+='<li class="active" id="districtId'
				+dataArr[i]+'" attr_location_type="district">District</li>';
				collapse+='<li id="parlimentId'+dataArr[i]+'" attr_location_type="parliament">Parliment</li>';
                collapse+='</ul>';
				collapse+='</div>';
				if(i == 1){
				collapse+='<div class="col-sm-3">';
				collapse+='<select class="form-control chosen-select" id="consitituencyLevelDistricts">';
				collapse+='</select>';
				collapse+='</div>';
				collapse+='<div class="col-sm-3">';
				collapse+='<select class="form-control chosen-select" id="consitituencyLevelRevenueDivision">';
				collapse+='</select>';
				collapse+='</div>';
				}
				if(i == 2){
				collapse+='<div class="col-sm-3">';
				collapse+='<select class="form-control chosen-select" id="manadalLevelDistricts">';
				collapse+='</select>';
				collapse+='</div>';
				collapse+='<div class="col-sm-3">';
				collapse+='<select class="form-control chosen-select" id="mandalLevelRevenueDivision">';
				collapse+='</select>';
				collapse+='</div>';
				collapse+='<div class="col-sm-3">';
				collapse+='<select class="form-control chosen-select" id="mandalLevelSubDivision">';
				collapse+='</select>';
				collapse+='</div>';
				}
				collapse+='</div>';
				collapse+='</div>';
				collapse+='<div class="row">';
				collapse+='<div class="col-sm-12">'
				collapse+='<div id="collapseConsolidatedView'+dataArr[i]+'"></div>';
				collapse+='</div>';
				collapse+='</div>';
				collapse+='</div>';
				collapse+='</div>';
				}
				collapse+='</div>';
				collapse+='</div>';
				collapse+='</div>';
				
				$("#projectDataOverview").html(collapse);
				for(var i in dataArr){
				 tableView(dataArr[i]);	
                   				 
     }
  }
 //$('.chosen-select').chosen();
  function tableView(divId)
  {
	  var str='';
	  var location_type='';
	  	$('.tableMenu li').each(function(){
					if($(this).hasClass('active')){
				  location_type = $(this).attr("attr_location_type");
				
			    }
				});
		
	 str+='<table class="table table-condensed table-striped" id="datatable'+divId+'" style="width:100%;">';
	 str+='<thead>';
     str+='<tr>';
	 if(divId == 'District'){
		 if(location_type == 'district'){
		 str+='<th> DISTRICTS </th>';
		 str+='<th> MANDALS </th>';
		 }else{
			str+='<th> Parliments </th>';
		    str+='<th> MANDALS </th>';
		}
	 }
	 if(divId == 'Mandal'){
		  if(location_type == 'district'){
		 str+='<th> MANDAL </th>';
		 str+='<th> MANDALID </th>';
	 }else{
		 str+='<th> Parliment </th>';
		 str+='<th> MANDALID </th>';
	  }
	 }
	 if(divId == 'Division'){
		  if(location_type == 'district'){
		 str+='<th> Divisions </th>';
		  str+='<th> DDOCode </th>';
		  str+='<th> TDDOCode </th>';
	 }else{
		 str+='<th> Parliment </th>';
		  str+='<th> DDOCode </th>';
		  str+='<th> TDDOCode </th>';
	  }   
	 } 
	   str+="<th> GP's </th>";
	   str+='<th><img src="Assests/img/Gross_Amount_icon.png"><br/>Grossamount</th>';
		str+='<th><img src="Assests/img/Deductions_icon.png"><br/>Deductions</th>';
		str+='<th><img src="Assests/img/Netamount_icon.png"><br/>Netamount</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		if(divId == "District"){
		   str+='<tr>';
			str+='<td>Srikakulam</td>';
			str+='<td>11</td>';
			str+='<td>90</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
			str+='<tr>';
			str+='<td>Srikakulam</td>';
			str+='<td>11</td>';
			str+='<td>90</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
			//str+='</tbody>';
		}else if(divId == "Division"){
			 str+='<tr>';
			str+='<td>EE, PRI Division, Chittoor</td>';
			str+='<td>03PRR010</td>';
			str+='<td>11012202074</td>';
			str+='<td>90</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
			 str+='<tr>';
			str+='<td>EE, PRI Division, Chittoor</td>';
			str+='<td>03PRR010</td>';
			str+='<td>11012202074</td>';
			str+='<td>90</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
			
		}else{
			 str+='<tr>';
			str+='<td class="gpsClickView" style="cursor:pointer;text-decoration:underline" >Ananthapur</td>';
			str+='<td>200</td>';
			str+='<td>18</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
			str+='<tr>';
			str+='<td>Ananthapur</td>';
			str+='<td>200</td>';
			str+='<td>18</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='<td><i class="fa fa-inr"></i> 645612933</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	  $("#collapseConsolidatedView"+divId).html(str);
	  
  }
	  
	  
  $(document).on("click",".tableMenu li",function(){
	  $(this).closest("ul").find("li").removeClass("active")
	  $(this).addClass("active")
	  
  });
   $(".gpsClickView").click(function(){
     $("#modalGsDivId").modal('show');
	 $("#modalGsHeadingId").html("<h4 class='text-capital'>Ananthapur MANDAL-Gps</h4>");
	 buildGsDetails()
   });
   function buildGsDetails(){
	   var tableView='';
		tableView+='<table class="table table-condensed table-striped" id="dataTableGpTable">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>Panchayat</th>';
					tableView+='<th>Code</th>';
					tableView+='<th>BankAccountNo</th>';
					tableView+='<th>Ifsc Code</th>';
					tableView+='<th>GrossAmount</th>';
					tableView+='<th>Deductions</th>';
					tableView+='<th>Netamount</th>';
					tableView+='<th>TotalTransactions</th>';
					tableView+='</tr>';
					tableView+='</thead>';
					tableView+='<tbody>';
					tableView+='<tr>';
					tableView+='<td attr_Panchayatname="Ahmedguda">Ahmedguda</td>';
					tableView+='<td attr_code="03PRGP010">03PRGP010</td>';
					tableView+='<td attr_no="33515115800">33515115800</td>';
					tableView+='<td attr_isfcode="SBIN0022032">SBIN0022032</td>';
					tableView+='<td attr_grossamount="44000"><i class="fa fa-inr"></i>44000</td>';
					tableView+='<td attr_deduction="22000"><i class="fa fa-inr"></i>22000</td>';
					tableView+='<td attr_netamount><i class="fa fa-inr">22000</td>';
					tableView+='<td>6</td>';
			tableView+='<td><button type="button" class="btn btn-success" id="gramPanchayatOverView" >View</button></td>';
			tableView+='</tr>';
			tableView+='<tr>';
					tableView+='<td attr_Panchayatname="Ahmedguda">Ahmedguda</td>';
					tableView+='<td attr_code="03PRGP010">03PRGP010</td>';
					tableView+='<td attr_no="33515115800">33515115800</td>';
					tableView+='<td attr_isfcode="SBIN0022032">SBIN0022032</td>';
					tableView+='<td attr_grossamount="44000"><i class="fa fa-inr"></i>44000</td>';
					tableView+='<td attr_deduction="22000"><i class="fa fa-inr"></i>22000</td>';
					tableView+='<td attr_netamount><i class="fa fa-inr">22000</td>';
					tableView+='<td>6</td>';
			tableView+='<td><button type="button" class="btn btn-success" id="gramPanchayatOverView" >View</button></td>';
			tableView+='</tr>';
					tableView+='</tbody>';
				$('#modalGsTable').html(tableView);
   }
   
     $(document).on("click","#gramPanchayatOverView",function(){
	
	 $("#modalGramPanchayatHeadingId").html("");
	 $("#gramPanchayatcode").html("");
	 $("#gramPanchayataccno").html("");
	 $("#gramPanchayatisfc").html("");
		 var code="";
		 var acno="";
		 var isfccode="";
		 var netAmount="";
		 var grossAmount="";
		 var deduction="";
		 code=$(this).closest('tr').find("td").eq(1).text();
		 acno=$(this).closest('tr').find("td").eq(2).text();
		 isfccode=$(this).closest('tr').find("td").eq(3).text();
		 netAmount=$(this).closest('tr').find("td").eq(5).text();
		 grossAmount=$(this).closest('tr').find("td").eq(4).text();
		 deduction=$(this).closest('tr').find("td").eq(6).text();
	   //$("#modalGsDivId").modal('hide');
	   $("#modalGramPanchayatDivId").modal('show');
	   $("#modalGramPanchayatHeadingId").append(" <div class='media'>");
	   $("#modalGramPanchayatHeadingId").append(" <p class='media-left circle'>AH</p>");
	   $("#modalGramPanchayatHeadingId").append(" <p class='media-body text-capital' style='padding-left: 15px; padding-top: 20px;'>AHMEDGUDA -GRAMAPANCHAYAT OVERVIEW </p>");
	   $("#modalGramPanchayatHeadingId").append(" </div>");
		$("#gramPanchayatcode").append("<span>code:"+code+"</span>");
		$("#gramPanchayataccno").append("<span>acno:"+acno+"</span>");
		$("#gramPanchayatisfc").append("<span>isfccode:"+isfccode+"</span>");
		$("#gramPanchayatNetAmount").append("<span>"+netAmount+"</span>");
		$("#gramPanchayatGrossAmount").append("<span>"+grossAmount+"</span>");
		$("#gramPanchayatDeduction").append("<span>"+deduction+"</span>");
   });
   buildTableForTransaction()   
  function buildTableForTransaction(){
	  var tableView='';
		tableView+='<table class="table table-condensed table-striped" id="dataTableGpTable">';
			tableView+='<thead>';
			tableView+='<tr>';
					tableView+='<th>GROSS AMOUNT</th>';
					tableView+='<th>DEDUCTIONS</th>';
					tableView+='<th>NET AMOUNT</th>';
					tableView+='<th>C.CHQNO</th>';
					tableView+='<th>C.CHQDATE</th>';
					tableView+='<th>Work Description</th>';
					tableView+='</tr>';
					tableView+='</thead>';
					tableView+='<tbody>';
					tableView+='<tr>';
					tableView+='<td><i class="fa fa-inr">88000</td>';
					tableView+='<td><i class="fa fa-inr">22000</td>';
					tableView+='<td><i class="fa fa-inr">66000</td>';
					tableView+='<td>CTA3159991</td>';
					tableView+='<td>14/7/2017</td>';
					tableView+='<td>FEW Lines About Fund Release Work</td>'
			        tableView+='</tr>';
					tableView+='<tr>';
					tableView+='<td><i class="fa fa-inr">88000</td>';
					tableView+='<td><i class="fa fa-inr">22000</td>';
					tableView+='<td><i class="fa fa-inr">66000</td>';
					tableView+='<td>CTA3159991</td>';
					tableView+='<td>14/7/2017</td>';
					tableView+='<td>FEW Lines About Fund Release Work</td>'
			        tableView+='</tr>';
					tableView+='</tbody>';
				$('#modalGramPanchayatTable').html(tableView);
	  
  }

  
  
           $("#consitituencyLevelDistricts").html('');
		    $("#consitituencyLevelDistricts").append("<option value=0>Select District</option>");
		   $("#consitituencyLevelDistricts").trigger("chosen:updated");
		    $("#consitituencyLevelRevenueDivision").html('');
		    $("#consitituencyLevelRevenueDivision").append("<option value=0>Select RevenueDivision</option>");
		   $("#consitituencyLevelRevenueDivision").trigger("chosen:updated");
		   $("#manadalLevelDistricts").html('');
		    $("#manadalLevelDistricts").append("<option value=0>Select District</option>");
		   $("#manadalLevelDistricts").trigger("chosen:updated");
		   $("#mandalLevelRevenueDivision").html('');
		    $("#mandalLevelRevenueDivision").append("<option value=0>Select RevenueDivision</option>");
		   $("#mandalLevelRevenueDivision").trigger("chosen:updated");
		   $("#mandalLevelSubDivision").html('');
		    $("#mandalLevelSubDivision").append("<option value=0>Select Revenue Sub-Division</option>");
		   $("#mandalLevelSubDivision").trigger("chosen:updated");