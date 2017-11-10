
var employeeAttendanceOverView=[{name:'TOTAL EMPLOYEES',color:'#CFCFCF'},{name:'PRESENT',color:'#BCF4E9'},{name:'ABSENT',color:'#FFDBDB'},{name:'BIOMETRIC DEVICES',color:'#FFE6B5'}];
buildEmpOverView()
function buildEmpOverView(){
	var str='';
	for(var i in employeeAttendanceOverView){
		str+='<div class="col-sm-3" >';
			str+='<div class="media" style="border:1px solid '+employeeAttendanceOverView[i].color+';padding:10px;">';
				str+='<div class="media-left" >';
					str+='<h5><span>IT</span><span class="pull-right">1000</span></h5>';
					str+='<h5 class="m_top10"><span>Panchyathi-Raj</span> <span class="pull-right">1000</span></h5>';
				str+='</div>';
				str+='<div class="media-body" style="background-color:'+employeeAttendanceOverView[i].color+';">';
					str+='<h3 class="text-center">20000</h3>';
					str+='<h5 class="text-center m_top10">'+employeeAttendanceOverView[i].name+'</h5>';
				str+=' </div>';
				
			str+='</div>';
		str+='</div>';
	}
	
	$("#empOverViewBlockId").html(str);
}

