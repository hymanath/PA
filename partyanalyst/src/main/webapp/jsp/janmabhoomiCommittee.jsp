<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Janmabhoomi Committee Dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="D2D_Assests/less/bootstrap.less" rel="stylesheet" type="text/less">
<link href="D2D_Assests/css/custom.less" rel="stylesheet" type="text/less"/>
<link href="D2D_Assests/Plugins/DataTable/dataTable.css" type="text/css" rel="stylesheet"/>
<script src="D2D_Assests/Plugins/Less/less.js"></script>
<link href="D2D_Assests/css/responsive.css" type="text/css" rel="stylesheet"/>
<link href="D2D_Assests/Plugins/Chosen/chosen.css" type="text/css" rel="stylesheet"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<style type="text/css">
#menu_multilevelpushmenu{box-shadow:none;}
</style>
</head>
<body>
<section class="janmabhoomi">
	<div class="container-fluid m_top10">
		<div class="row">
			<div class="col-sm-12">
				<div class="white_block_JanB">
					<div class="row">
						<div class="col-sm-2">
							<div id="committeeMainBlockGraphId" style="height:200px;"></div>
						</div>
						<div id="overAllCommitteeMainBlockId"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12">
				<div id="committeeWiseDetailsDivId"></div>
			</div>
		</div>
		<div class="row m_top10">
			<div class="col-sm-12">
				<div id="levelWisecommitteeDetailsDivId"></div>
			</div>
		</div>
	</div>		
</section>
<div class="modal fade janmabhoomi" tabindex="-1" id="committeeWiseModalOpen" role="dialog" style="z-index:9999;">
 <div class="modal-dialog" role="document" style="width:85%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">District Committee Overview</h4>      
		</div>
		<div class="modal-body">   
          <div id="committeeWisePopUpDetailsId"></div>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<div class="modal fade janmabhoomi" tabindex="-1" id="memberAddEditModalOpen" role="dialog" style="z-index:9999;">
 <div class="modal-dialog" role="document" style="width:85%;">   
    <div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close closeShowPdfCls" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title">Add Memberes</h4>      
		</div>
		<div class="modal-body">   
          <div id="memberAddEditPopUpDetailsId"></div>
          <div id="memberAddedPopUpDetailsId"></div>
       </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default closeShowPdfCls" data-dismiss="modal">Close</button>
      </div>
    </div><!--   /.modal-content -->  
  </div><!--  /.modal-dialog -->
</div><!--   /.modal -->
<script src="D2D_Assests/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="D2D_Assests/js/bootstrap.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Highcharts/highcharts.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="D2D_Assests/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script type="text/javascript" src="js/janmabhoomiCommittee.js"></script></body>
</html>