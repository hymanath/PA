	var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
	//var globalDeptArr={"dept":"Panchayati Raj & RD & RWS","id";"panchayatiRaj"}
	var blockNames = ['ENC','RWS','MGNREGS'];

$(".chosenSelect").chosen();
onLoadCalls();
function onLoadCalls(){
	getAllSubLocationsOnsuperLocation("21");
}
/* 
$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	 */
function getAllSubLocationsOnsuperLocation(superLocationId){
	 var json = {
          superLocationId:superLocationId
        }
    $.ajax({ 
      url: 'getAllSubLocationsOnsuperLocation', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
		var str='';
		str+='<option value="0">SELECT DISTRICT</option>';
		for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>'
		}
     $("#districtSelId").html(str);
    // $("#"+selId+"SelId").html("chosen");
	$("#districtSelId").chosen();
	 $("#districtSelId").trigger('chosen:updated');
    });
}
$(document).on("change","#districtSelId",function(){
	var constincyId=$(this).val();
	var conId=constincyId.substr(1,constincyId.length-1);
	getLocationsNamesBySubLocation(conId);
	});
function getLocationsNamesBySubLocation(locationId){  
	 var json = {
          locationId:locationId
        }
    $.ajax({ 
      url: 'getLocationsNamesBySubLocation', 
      type:'POST',  
      data : JSON.stringify(json),
      dataTypa : 'json',   
      beforeSend: function(xhr) {
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
      },
            
    }).done(function(result){
		var str='';
		str+='<option value="0">SELECT CONSTITUENCY</option>';
		for(var i in result){
			str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>'
		}
		 $("#constituencySelId").html(str);
	$("#constituencySelId").chosen();
	 $("#constituencySelId").trigger('chosen:updated');
    });
}	
collapseBlock();
function collapseBlock(){
	var collapse='';
	if(blockNames != null){
	collapse+='<div class="row">';
		collapse+='<div class="col-sm-12">'
			for(var i in blockNames){
			
				collapse+='<div class="panel-group " id="accordion'+blockNames[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black m_top20">';
					collapse+='<div class="panel-heading" role="tab" id="headingOne'+blockNames[i]+'">';
						collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+blockNames[i]+'" href="#collapseOne'+blockNames[i]+'" aria-expanded="true" aria-controls="collapseOne'+blockNames[i]+'">';
							collapse+='<h4 class="panel-title">'+blockNames[i]+' DEPARTMENT - OVERVIEW</h4>';
						collapse+='</a>';
					collapse+='</div>';
					collapse+='<div id="collapseOne'+blockNames[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+blockNames[i]+'">';
						collapse+='<div class="panel-body">';
							collapse+='<div class="col-sm-4">';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
					collapse+='</div>';
			collapse+='</div>'
				
			}
			
		collapse+='</div>'
	collapse+='</div>';
	
	}
	collapse+='<div class="row m_top10">';
		collapse+='<div class="col-sm-1 col-sm-offset-11">';
			collapse+='<button class="btn btn-md btn-success">PRINT</button>';
		collapse+='</div>';
	collapse+='</div>';
	$("#deptBlocks").html(collapse);
}