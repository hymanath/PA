<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Election Location</title>
<!-- Bootstrap -->
    <link href="js/cadreCommittee/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

        <div class="col-md-3 col-xs-12 col-sm-3"  id="districtDiv">
             <label>DISTRICT<font class="requiredFont">*</font></label>
               <select class="form-control" id="districtId">     
					<option value="0">SELECT DISTRICT</option>
               </select>
         </div>
        <div class="col-md-3 col-xs-12 col-sm-3" id="constituencyDiv">
             <label>CONSTITUENCY<font class="requiredFont">*</font></label>
               <select class="form-control" id="constituencyId">     
					<option value="0">SELECT CONSTITUENCY</option>
               </select>
        </div>
         <div class="col-md-3 col-xs-12 col-sm-3"  id="mandalDiv">
              <label>MANDAL<font class="requiredFont">*</font></label>
               <select class="form-control" id="mandalId">     
                   <option value="0">SELECT MANDAL</option>
               </select>
         </div>
         <div class="col-md-3 col-xs-12 col-sm-3" id="municipalityDiv">
              <label>Panchayat<font class="requiredFont">*</font></label>
                <select class="form-control" id="municipalityId">     
                  <option value="0">SELECT panchayat</option>
              </select>
         </div>
		 <div class="col-md-3 col-xs-12 col-sm-3" id="municipalityDiv">
              <label>Booth<font class="requiredFont">*</font></label>
                <select class="form-control" id="boothId">     
                  <option value="0">SELECT Booth</option>
              </select>
         </div>
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
getDistrictDetails();
function getDistrictDetails(){
    jsObj={
      locationId:1
    }
    $.ajax({
      type : "GET",
      url : "getDistrictsDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select district</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].districtId+'">'+result[i].districtName+'</option>';
        }  
    }  
       $("#districtId").html(str);    
  });
  }   
  getConstituencyDetails();
  function getConstituencyDetails(){
    jsObj={
      districtId:19
    }
    $.ajax({
      type : "GET",
      url : "getConstituenciesForADistrictDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select constituency</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#constituencyId").html(str);    
  });
  }  
getMandalDetails();
 function getMandalDetails(){
    jsObj={
      constituencyId:232
    }
    $.ajax({
      type : "GET",
      url : "getMandalsForAConstituencyDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#mandalId").html(str);    
  });
  }
  getPanchayatDetails();
  function getPanchayatDetails(){
    jsObj={
      mandalId:844
    }
    $.ajax({
      type : "GET",
      url : "getVillagesForMandalIdDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#municipalityId").html(str);    
  });
  }
  getBoothDetails();
  function getBoothDetails(){
    jsObj={
      panchayatId:1
    }
    $.ajax({
      type : "GET",
      url : "getAllBoothsForPanchayatDetailsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
      var str='';
      str+='<option value="0">select mandal</option>';
      if(result != null && result.length > 0){
        for(var i in result)
        {
             str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
        }  
    }  
       $("#boothId").html(str);    
  });
  }
</script>
</body>
</html>