<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Grievance Portal</title>

<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
<link href="dist/Icomoon/style.css" rel="stylesheet" type="text/css">
<link href="dist/css/dropkick.css" rel="stylesheet" type="text/css">
<link href="dist/css/jquery.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/css/dataTables.responsive.css" rel="stylesheet" type="text/css">
<link href="dist/sidebar/css/component.css" rel="stylesheet" type="text/css">
<link href="dist/Scroller/fm.scrollator.jquery.css" rel="stylesheet" type="text/css">
<link href="dist/css/daterangepicker.css" rel="stylesheet" type="text/css">
<link href="dist/css/daterangeinline.css" rel="stylesheet" type="text/css">
<link href="dist/bootstrap3-editable/css/bootstrap-editable.css" type="text/css">
<link rel="stylesheet" href="scrollnew/jquery.mCustomScrollbar.css">
<link rel="stylesheet" href="scrollnew/stylescroll.css">
<link rel="stylesheet" type="text/css" href="css/simplePagination.css"/>
<!-- Add mousewheel plugin (this is optional) -->
<script type="text/javascript" src="pdfexpand/lib/jquery.mousewheel-3.0.6.pack.js"></script>
<!-- Add fancyBox main JS and CSS files -->
<script type="text/javascript" src="pdfexpand/source/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="pdfexpand/source/jquery.fancybox.css?v=2.1.5" media="screen" />


	  <!-- End -->
<style>
.remarks-ul-scroll{max-height:400px !important;}
.scroll-li-p{padding-left:10px;}
.mCSB_scrollTools{width:8px}
.mCustomScrollBox{max-height:200px !important;height:auto !important}
.text-uppercase
{text-transform:uppercase !important}
.path {
  stroke-dasharray: 100; 
  stroke-dashoffset: 1000;
  animation: dash 5s linear alternate infinite;
}

@keyframes dash {
  from {
    stroke-dashoffset: 1000;
  }
  to {
    stroke-dashoffset: 0;
  }
}
.problemDescDivCls{height:150px;overflow-y:scroll;padding:15px 7px 7px 16px;}
.mouse-over
    {
        padding:0px;
    }
    
    .mouse-over:hover:after
    {
        content:"click here to expand";
        position:absolute;
        left:35px;
        z-index:9999;
        color:#fff;
        background:rgba(0,0,0,0.5);
        padding:10px;
        font-size:22px;
		margin-top:20px;
    }

	.active{
		background-color:#fff;
	}
	#donutchart .highcharts-legend-item span{text-transform:capitalize}
	
	.crossmarkalign {
    height: 10px;
    margin-left: 7px;
    margin-top: 0px;
    width: 10px;
	}
.alignDiv{
	padding:5px;
	border-radius:10px;
	border:1px solid #ccc;
	margin-top:5px;
}	
</style>

</head>

<body>

	<!-- language convertion-->
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

// Load the Google Transliterate API
   google.load("elements", "1", {
         packages: "transliteration"
       });

</script> 
  <script>

 var control;
	var lang;
   function onLoad() {
	
       lang = $("input[name=language]:checked").val();
     var options = {
         sourceLanguage:
             google.elements.transliteration.LanguageCode.ENGLISH,
         destinationLanguage:
             [''+lang+''],
         shortcutKey: 'alt+t',
         transliterationEnabled: true
     };

     // Create an instance on TransliterationControl with the required
     // options.
     control =
         new google.elements.transliteration.TransliterationControl(options);

     // Enable transliteration in the textbox with id
     // 'descrptionId'.

	 	if ($('#infoCenterRemarksId').length){
control.makeTransliteratable(['infoCenterRemarksId']);
 

}
   }
   function languageChangeHandler() {
  
        var lang1 = $("input[name=language]:checked").val();
		if(lang1 =="en")
	   {
		control.disableTransliteration();
		}
		else
	   {
		   control.enableTransliteration();
           control.setLanguagePair(
            google.elements.transliteration.LanguageCode.ENGLISH,
            lang1);
			}
      }
 google.setOnLoadCallback(onLoad);
</script>	
<svg class="page-load-svg m_top20" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
	 width="100%" height="100%" viewBox="0 0 300 300" enable-background="new 0 0 300 300" xml:space="preserve">
	 <g>
	<path class="path" stroke="red" stroke-width="2" d="M940.68,291.557l-1.934,9.289h-8.186l9.41-40.654h10.119l8.443,40.654h-8.186l-1.869-9.289H940.68z M947.705,285.404
		l-1.418-8.505c-0.451-2.474-1.031-6.515-1.418-9.229h-0.193c-0.451,2.714-1.096,6.937-1.547,9.289l-1.611,8.444H947.705z"/>
	<path class="path" stroke="red" stroke-width="2" d="M963.303,260.795c2.578-0.543,5.93-0.785,9.604-0.785c4.061,0,7.992,0.483,11.021,2.896
		c2.32,1.809,3.287,4.523,3.287,7.358c0,3.619-1.998,7.117-6.316,8.866v0.242c5.027,1.326,7.734,5.247,7.734,9.711
		c0,3.378-1.16,5.971-3.223,7.962c-2.578,2.653-6.961,4.102-14.244,4.102c-3.223,0-5.93-0.181-7.863-0.423V260.795z
		 M971.746,276.899h1.805c3.029,0,5.35-2.413,5.35-5.729c0-2.956-1.482-5.248-4.963-5.248c-0.902,0-1.676,0.061-2.191,0.241V276.899
		z M971.746,294.935c0.516,0.12,1.096,0.12,1.869,0.12c3.416,0,6.252-1.93,6.252-6.092c0-4.041-2.965-6.152-6.316-6.213h-1.805
		V294.935z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1015.25,300.242c-1.225,0.543-3.609,0.966-6.574,0.966c-11.086,0-16.371-8.565-16.371-20.087
		c0-15.32,9.088-21.291,17.531-21.291c2.965,0,5.027,0.543,5.865,1.085l-1.418,6.636c-0.967-0.423-2.063-0.785-3.932-0.785
		c-4.77,0-9.088,3.861-9.088,13.934c0,9.712,3.932,13.512,9.088,13.512c1.418,0,3.029-0.302,4.061-0.604L1015.25,300.242z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1019.889,260.795c2.127-0.482,5.221-0.785,8.572-0.785c5.479,0,9.281,1.207,12.053,3.619
		c3.738,3.137,5.672,8.506,5.672,16.407c0,8.203-2.256,14.054-6.059,17.19c-2.9,2.654-7.219,3.921-13.277,3.921
		c-2.643,0-5.285-0.241-6.961-0.423V260.795z M1028.332,294.572c0.387,0.121,1.031,0.121,1.482,0.121
		c4.061,0,7.605-3.74,7.605-14.959c0-8.324-2.32-13.391-7.412-13.391c-0.58,0-1.16,0-1.676,0.182V294.572z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1069.904,282.992h-9.926v10.978h11.215v6.876h-19.658v-40.654h18.885v6.876h-10.441v9.35h9.926V282.992z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1076.929,260.191h18.885v6.876h-10.441v10.435h9.797v6.575h-9.797v16.769h-8.443V260.191z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1126.299,299.76c-1.869,0.724-5.865,1.448-9.023,1.448c-4.963,0-8.701-1.327-11.602-4.042
		c-3.738-3.378-5.736-9.409-5.607-16.406c0.193-14.777,9.217-20.93,18.434-20.93c3.287,0,5.736,0.603,7.09,1.205l-1.482,6.816
		c-1.225-0.543-2.836-0.904-4.963-0.904c-5.736,0-10.313,3.68-10.313,14.295c0,9.771,4.061,13.271,7.992,13.271
		c0.773,0,1.354-0.061,1.676-0.182V284.5h-3.867v-6.455h11.666V299.76z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1141.057,260.191v16.285h8.83v-16.285h8.443v40.654h-8.443v-17.01h-8.83v17.01h-8.443v-40.654H1141.057z"/>
	<path class="path" stroke="red" stroke-width="2" d="M1174.25,260.191v40.654h-8.443v-40.654H1174.25z"/>
</g>
<g>
	<g>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M29.85,153.437c-3.914,2.715-2.088,11.171-7.782,12.105
			c-2.741-2.459-3.348-12.768-0.433-15.564c2.119,0.331,2.033,2.869,3.891,3.458c6.697-14.631,17.29-25.367,33.29-30.695
			c-0.391-2.203-1.003-4.185-1.729-6.053c-2.972-0.342-7.706,1.078-8.214-1.729c3.111-3.383,10.655-1.506,15.132-1.297
			c16.437,0.764,35.156,0.774,48.421,4.755c2.646-1.533,3.763-4.595,5.62-6.917c-1.604-4.448-3.022-9.083-4.755-13.402
			c-5.344,0.709-10.264,1.841-15.132,3.026c-1.502-1.595-0.003-2.142,0.865-3.459c-1.456-0.273-4.016,0.558-4.756-0.432
			c-0.297-1.45,0.726-1.581,1.729-1.729c-0.208-1.378-2.875-0.295-3.459-1.297c-0.386-1.972,1.422-1.749,2.594-2.162
			c-0.405-1.324-2.396-1.063-3.026-2.162c-0.353-2.947,0.351-4.837,1.729-6.053c10.167-0.212,21.583,1.58,31.56,0.865
			c4.211-0.302,8.134-2.52,11.673-0.432c-0.556,9.82-11.519,9.232-20.752,10.375c-0.126,3.352,2.63,7.418,3.459,11.241
			c27.237,0,54.474,0,81.711,0c0.353-6.544-2.472-13.982-4.756-20.319c9.269-0.098,16.204-2.53,19.887-8.214
			c-3.037-1.173-3.137,0.923-5.188,1.729c-6.141-1.959-26.39,2.441-25.94-4.755c0.407-6.545,19.18-0.659,26.372-2.162
			c1.328,2.852,6.364,1.996,7.782,4.756c-1.421,6.793-8.963,7.466-13.403,11.241c-1.584,0-3.17,0-4.754,0
			c0.704,3.618,2.123,6.523,3.025,9.943c5.839,5.113,6.767,15.137,9.944,22.914c-2.038,0.282-5.283,4.751-6.485,1.729
			c-15.397,15.874-30.201,32.344-45.396,48.42c1.789,3.401,4.621,5.757,3.891,11.675c-5.246,5.369-6.415-3.627-9.078-6.053
			c-0.17,2.337,2.583,5.481,2.162,9.51c3.69,1.211,8.414,1.386,12.104,2.596c1.453-0.445,0.674,1.344,0.865,2.16
			c2.436,0.016,5.352-0.452,5.62,1.731c-0.659,3.142-8.17,1.552-10.809,1.296c-1.207-1.334,0.235-2.64,0.865-3.458
			c-3.464,0.004-5.503-1.415-9.079-1.298c-3.396,12.609-22.156,10.357-34.155,6.917c-9.754,18.482-25.293,33.559-53.176,31.562
			C38.207,225.394,13.831,188.63,29.85,153.437z M123.666,110.636c5.629,18.007,12.499,38.514,19.887,56.203
			c2.918-0.555,8.605,1.918,11.671,1.298c3.677-0.746,10.181-9.935,13.403-13.403c11.429-12.299,21.666-23.546,32.426-35.019
			c2.056-2.193,5.723-4.438,3.025-8.214C179.163,109.497,149.287,111.203,123.666,110.636z M115.884,119.715
			c0.701,1.187,3.365,2.668,1.729,4.755c-2.094,1.557-2.42-1.724-4.323-1.729c-1.412,2.48-3.385,4.396-4.756,6.917
			c12.555,7.909,19.957,20.971,24.211,37.18c1.456-0.274,4.016,0.558,4.755-0.434c-6.514-16.83-11.786-34.905-18.59-51.446
			C118.036,116.679,116.567,117.804,115.884,119.715z M60.545,121.876c15.88-3.832,35.745-1.162,45.827,6.053
			c1.25-2.353,3.224-3.981,4.323-6.485c-15.125-3.61-33.302-4.166-51.447-4.755C59.145,118.954,60.3,119.96,60.545,121.876z
			 M61.842,126.2c9.304-3.243,23.529-2.434,31.993,0.433c4.044,1.37,7.098,4.688,10.376,3.891
			C95.118,124.571,74.533,120.448,61.842,126.2z M30.714,151.708c6.203-12.099,16.665-19.939,29.398-25.508
			C46.127,130.229,35.785,140.205,30.714,151.708z M64.004,132.685c4.102,11.318,6.526,24.313,11.673,34.587
			c3.662,0.038,5.237-4.143,6.917-6.485c5.793-8.072,11.569-15.713,16.861-23.778C91.724,131.632,75.678,128.8,64.004,132.685z
			 M113.29,137.44c-1.735-1.692-5.009-4.94-7.35-4.323c11.434,7.156,18.127,19.053,22.48,33.288
			C126.91,155.675,120.307,144.291,113.29,137.44z M36.767,155.166c10.851,5.723,21.243,11.904,32.425,17.293
			c0.12-2.906,2.856-3.196,3.891-5.188c-3.958-11.174-6.766-23.497-11.24-34.154C50.697,137.68,41.943,144.634,36.767,155.166z
			 M101.617,138.737c-5.908,9.08-12.672,17.302-18.59,26.372c12.427,0.544,25.386,0.555,37.613,1.296
			C117.946,153.537,111.18,144.738,101.617,138.737z M92.538,188.023c-8.486-2.38-23.762-4.091-24.21-13.834
			c-11.125-5.593-21.305-12.13-32.857-17.294c-1.811,5.856-3.491,9.543-3.891,15.132c-2.036,28.412,20.06,50.264,46.692,49.287
			c19.557-0.719,32.019-11.647,39.342-26.806C110.64,192.287,102.138,190.714,92.538,188.023z M83.459,172.027
			c-0.144,1.729,0.771,2.4,0.432,4.322c12.348,1.488,24.881,2.789,37.613,3.893c0.695-1.598,0.695-5.754,0-7.35
			C108.46,172.965,96.586,171.869,83.459,172.027z M129.718,181.105c0.485-1.507,1.22-6.976-0.432-7.782c-0.289,0-0.577,0-0.865,0
			C129.089,175.683,126.967,180.831,129.718,181.105z M134.042,178.945c0.631-2.541,2.807-3.535,4.323-5.189
			c-1.211-0.518-2.983-0.476-4.755-0.433C133.887,175.064,133.048,177.921,134.042,178.945z M83.026,178.945
			c-1.354,1.094-2.638,2.261-3.891,3.457c13.399,3.318,26.031,7.403,39.775,10.378c0.525-3.511,2.237-5.835,2.162-9.944
			C108.204,181.725,95.567,180.383,83.026,178.945z M151.768,187.591c-3.421,0.182-2.729,2.494-5.621,2.595
			c-3.327,0.114-5.087-2.384-5.188-5.189c-0.067-1.852,2.692-3.555,0.865-5.188C132.592,186.446,148.436,198.418,151.768,187.591z
			 M150.47,184.564c0.501,0.362,1.16,0.569,2.162,0.432c-0.05-1.103-0.081-2.224-1.297-2.16
			C151.11,183.475,150.695,183.924,150.47,184.564z M125.395,194.509c4.316,0.585,9.486,2.614,13.402,2.162
			c-3.841-2.213-6.315-5.791-6.485-11.675c-2.221,0.349-1.804-1.94-4.323-1.296C127.601,187.779,126.396,191.042,125.395,194.509z"
			/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M230.021,85.561c1.956,3.961,0.511,10.101-2.595,11.673
			c-7.584,0.378-12.468-1.944-15.132-6.485C214.559,85.162,223.231,82.841,230.021,85.561z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M257.256,122.741c-1.416,3.856-6.984,1.107-10.808,0.865
			c-11.775-0.746-23.787,1.6-30.264,6.053c36.976-17.293,79.557,8.237,76.522,50.583c-1.825,25.491-21.59,45.622-46.691,47.557
			c-31.77,2.446-49.772-17.013-56.203-41.937c-2.435,3.088,4.026,8.72,1.297,12.104c-2.804,0.406-4.02-4.231-4.756-6.918
			c-6.608-24.139,4.693-50.93,20.319-60.093c0.71-0.926-1.102-2.099-0.433-4.323c3.331-2.002,6.342-4.324,10.809-5.188
			c1.393,0.192,2.511,0.661,2.162,2.594c6.191-2.615,16.425-4.29,25.508-3.891C249.179,120.343,254.302,120.354,257.256,122.741z
			 M189.38,184.564c-2.601-21.194,5.351-36.252,15.132-45.827c2.753-2.694,6.484-4.511,8.646-7.782
			C197.168,139.192,184.171,160.045,189.38,184.564z M240.828,221.314c24.959,0,45.77-20.374,45.395-45.829
			c-0.327-22.126-16.56-42.67-41.071-44.53c-12.184-0.925-20.156,1.945-27.237,6.485c4.39,13.048,8.965,25.91,17.726,34.587
			c9.308-10.417,20.854,9.875,8.646,12.537c-7.9,1.723-6.548-4.063-10.809-5.188c-1.866-0.494-5.779,0.787-9.079,1.297
			c-9.514,1.468-20.141,3.015-27.668,4.322C202.52,205.959,216.131,221.314,240.828,221.314z M228.29,169.001
			c-6.245-8.263-9.543-18.574-12.97-29.398c-0.024-0.264-0.064-0.512-0.434-0.433c-12.136,8.385-21.477,23.491-18.59,44.098
			c12.469-2.373,26.552-3.136,38.046-6.484C233.666,173.198,230.292,171.651,228.29,169.001z M239.963,174.621
			c1.494,0.811,3.975,0.636,3.891,3.026c-0.337,0.096-0.501,0.361-0.432,0.864c-1.369,1.154-3.427-0.915-4.756,0.434
			C244.776,186.057,248.646,170.197,239.963,174.621z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M92.105,144.358c-1.449,3.054-7.294,5.222-11.241,3.026
			c-1.336-1.635-1.303-6.657,0.432-7.781C86.264,138.142,91.055,140.473,92.105,144.358z"/>
	</g>
</g>
<g>
	<g>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M29.85,153.437c-3.914,2.715-2.088,11.171-7.782,12.105
			c-2.741-2.459-3.348-12.768-0.433-15.564c2.119,0.331,2.033,2.869,3.891,3.458c6.697-14.631,17.29-25.367,33.29-30.695
			c-0.391-2.203-1.003-4.185-1.729-6.053c-2.972-0.342-7.706,1.078-8.214-1.729c3.111-3.383,10.655-1.506,15.132-1.297
			c16.437,0.764,35.156,0.774,48.421,4.755c2.646-1.533,3.763-4.595,5.62-6.917c-1.604-4.448-3.022-9.083-4.755-13.402
			c-5.344,0.709-10.264,1.841-15.132,3.026c-1.502-1.595-0.003-2.142,0.865-3.459c-1.456-0.273-4.016,0.558-4.756-0.432
			c-0.297-1.45,0.726-1.581,1.729-1.729c-0.208-1.378-2.875-0.295-3.459-1.297c-0.386-1.972,1.422-1.749,2.594-2.162
			c-0.405-1.324-2.396-1.063-3.026-2.162c-0.353-2.947,0.351-4.837,1.729-6.053c10.167-0.212,21.583,1.58,31.56,0.865
			c4.211-0.302,8.134-2.52,11.673-0.432c-0.556,9.82-11.519,9.232-20.752,10.375c-0.126,3.352,2.63,7.418,3.459,11.241
			c27.237,0,54.474,0,81.711,0c0.353-6.544-2.472-13.982-4.756-20.319c9.269-0.098,16.204-2.53,19.887-8.214
			c-3.037-1.173-3.137,0.923-5.188,1.729c-6.141-1.959-26.39,2.441-25.94-4.755c0.407-6.545,19.18-0.659,26.372-2.162
			c1.328,2.852,6.364,1.996,7.782,4.756c-1.421,6.793-8.963,7.466-13.403,11.241c-1.584,0-3.17,0-4.754,0
			c0.704,3.618,2.123,6.523,3.025,9.943c5.839,5.113,6.767,15.137,9.944,22.914c-2.038,0.282-5.283,4.751-6.485,1.729
			c-15.397,15.874-30.201,32.344-45.396,48.42c1.789,3.401,4.621,5.757,3.891,11.675c-5.246,5.369-6.415-3.627-9.078-6.053
			c-0.17,2.337,2.583,5.481,2.162,9.51c3.69,1.211,8.414,1.386,12.104,2.596c1.453-0.445,0.674,1.344,0.865,2.16
			c2.436,0.016,5.352-0.452,5.62,1.731c-0.659,3.142-8.17,1.552-10.809,1.296c-1.207-1.334,0.235-2.64,0.865-3.458
			c-3.464,0.004-5.503-1.415-9.079-1.298c-3.396,12.609-22.156,10.357-34.155,6.917c-9.754,18.482-25.293,33.559-53.176,31.562
			C38.207,225.394,13.831,188.63,29.85,153.437z M123.666,110.636c5.629,18.007,12.499,38.514,19.887,56.203
			c2.918-0.555,8.605,1.918,11.671,1.298c3.677-0.746,10.181-9.935,13.403-13.403c11.429-12.299,21.666-23.546,32.426-35.019
			c2.056-2.193,5.723-4.438,3.025-8.214C179.163,109.497,149.287,111.203,123.666,110.636z M115.884,119.715
			c0.701,1.187,3.365,2.668,1.729,4.755c-2.094,1.557-2.42-1.724-4.323-1.729c-1.412,2.48-3.385,4.396-4.756,6.917
			c12.555,7.909,19.957,20.971,24.211,37.18c1.456-0.274,4.016,0.558,4.755-0.434c-6.514-16.83-11.786-34.905-18.59-51.446
			C118.036,116.679,116.567,117.804,115.884,119.715z M60.545,121.876c15.88-3.832,35.745-1.162,45.827,6.053
			c1.25-2.353,3.224-3.981,4.323-6.485c-15.125-3.61-33.302-4.166-51.447-4.755C59.145,118.954,60.3,119.96,60.545,121.876z
			 M61.842,126.2c9.304-3.243,23.529-2.434,31.993,0.433c4.044,1.37,7.098,4.688,10.376,3.891
			C95.118,124.571,74.533,120.448,61.842,126.2z M30.714,151.708c6.203-12.099,16.665-19.939,29.398-25.508
			C46.127,130.229,35.785,140.205,30.714,151.708z M64.004,132.685c4.102,11.318,6.526,24.313,11.673,34.587
			c3.662,0.038,5.237-4.143,6.917-6.485c5.793-8.072,11.569-15.713,16.861-23.778C91.724,131.632,75.678,128.8,64.004,132.685z
			 M113.29,137.44c-1.735-1.692-5.009-4.94-7.35-4.323c11.434,7.156,18.127,19.053,22.48,33.288
			C126.91,155.675,120.307,144.291,113.29,137.44z M36.767,155.166c10.851,5.723,21.243,11.904,32.425,17.293
			c0.12-2.906,2.856-3.196,3.891-5.188c-3.958-11.174-6.766-23.497-11.24-34.154C50.697,137.68,41.943,144.634,36.767,155.166z
			 M101.617,138.737c-5.908,9.08-12.672,17.302-18.59,26.372c12.427,0.544,25.386,0.555,37.613,1.296
			C117.946,153.537,111.18,144.738,101.617,138.737z M92.538,188.023c-8.486-2.38-23.762-4.091-24.21-13.834
			c-11.125-5.593-21.305-12.13-32.857-17.294c-1.811,5.856-3.491,9.543-3.891,15.132c-2.036,28.412,20.06,50.264,46.692,49.287
			c19.557-0.719,32.019-11.647,39.342-26.806C110.64,192.287,102.138,190.714,92.538,188.023z M83.459,172.027
			c-0.144,1.729,0.771,2.4,0.432,4.322c12.348,1.488,24.881,2.789,37.613,3.893c0.695-1.598,0.695-5.754,0-7.35
			C108.46,172.965,96.586,171.869,83.459,172.027z M129.718,181.105c0.485-1.507,1.22-6.976-0.432-7.782c-0.289,0-0.577,0-0.865,0
			C129.089,175.683,126.967,180.831,129.718,181.105z M134.042,178.945c0.631-2.541,2.807-3.535,4.323-5.189
			c-1.211-0.518-2.983-0.476-4.755-0.433C133.887,175.064,133.048,177.921,134.042,178.945z M83.026,178.945
			c-1.354,1.094-2.638,2.261-3.891,3.457c13.399,3.318,26.031,7.403,39.775,10.378c0.525-3.511,2.237-5.835,2.162-9.944
			C108.204,181.725,95.567,180.383,83.026,178.945z M151.768,187.591c-3.421,0.182-2.729,2.494-5.621,2.595
			c-3.327,0.114-5.087-2.384-5.188-5.189c-0.067-1.852,2.692-3.555,0.865-5.188C132.592,186.446,148.436,198.418,151.768,187.591z
			 M150.47,184.564c0.501,0.362,1.16,0.569,2.162,0.432c-0.05-1.103-0.081-2.224-1.297-2.16
			C151.11,183.475,150.695,183.924,150.47,184.564z M125.395,194.509c4.316,0.585,9.486,2.614,13.402,2.162
			c-3.841-2.213-6.315-5.791-6.485-11.675c-2.221,0.349-1.804-1.94-4.323-1.296C127.601,187.779,126.396,191.042,125.395,194.509z"
			/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M230.021,85.561c1.956,3.961,0.511,10.101-2.595,11.673
			c-7.584,0.378-12.468-1.944-15.132-6.485C214.559,85.162,223.231,82.841,230.021,85.561z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M257.256,122.741c-1.416,3.856-6.984,1.107-10.808,0.865
			c-11.775-0.746-23.787,1.6-30.264,6.053c36.976-17.293,79.557,8.237,76.522,50.583c-1.825,25.491-21.59,45.622-46.691,47.557
			c-31.77,2.446-49.772-17.013-56.203-41.937c-2.435,3.088,4.026,8.72,1.297,12.104c-2.804,0.406-4.02-4.231-4.756-6.918
			c-6.608-24.139,4.693-50.93,20.319-60.093c0.71-0.926-1.102-2.099-0.433-4.323c3.331-2.002,6.342-4.324,10.809-5.188
			c1.393,0.192,2.511,0.661,2.162,2.594c6.191-2.615,16.425-4.29,25.508-3.891C249.179,120.343,254.302,120.354,257.256,122.741z
			 M189.38,184.564c-2.601-21.194,5.351-36.252,15.132-45.827c2.753-2.694,6.484-4.511,8.646-7.782
			C197.168,139.192,184.171,160.045,189.38,184.564z M240.828,221.314c24.959,0,45.77-20.374,45.395-45.829
			c-0.327-22.126-16.56-42.67-41.071-44.53c-12.184-0.925-20.156,1.945-27.237,6.485c4.39,13.048,8.965,25.91,17.726,34.587
			c9.308-10.417,20.854,9.875,8.646,12.537c-7.9,1.723-6.548-4.063-10.809-5.188c-1.866-0.494-5.779,0.787-9.079,1.297
			c-9.514,1.468-20.141,3.015-27.668,4.322C202.52,205.959,216.131,221.314,240.828,221.314z M228.29,169.001
			c-6.245-8.263-9.543-18.574-12.97-29.398c-0.024-0.264-0.064-0.512-0.434-0.433c-12.136,8.385-21.477,23.491-18.59,44.098
			c12.469-2.373,26.552-3.136,38.046-6.484C233.666,173.198,230.292,171.651,228.29,169.001z M239.963,174.621
			c1.494,0.811,3.975,0.636,3.891,3.026c-0.337,0.096-0.501,0.361-0.432,0.864c-1.369,1.154-3.427-0.915-4.756,0.434
			C244.776,186.057,248.646,170.197,239.963,174.621z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#d9dbd4" d="M92.105,144.358c-1.449,3.054-7.294,5.222-11.241,3.026
			c-1.336-1.635-1.303-6.657,0.432-7.781C86.264,138.142,91.055,140.473,92.105,144.358z"/>
	</g>
</g>
</svg>
<section  id="content-section">
		<div class="container section-container" id="st-container" style="min-height:700px;">
        	<nav class="st-menu st-effect-1" id="menu-1" style="margin-top:45px;z-index:9999;height:auto;">
				<h3>FILTERS</h3><div id="crossAppend"></div>
				<ul class="sidemenu-links">
				   <c:if test="${sessionScope.USER.userType == 'Agent' || sessionScope.USER.userType == 'Task Manager'  || (sessionScope.USER.userType == 'verification mgr' && sessionScope.USER.accessDistricts != null && fn:length(sessionScope.USER.accessDistricts) gt 1)}">
					<li><a>DISTRICTS<p>Filter By districts</p><div id="districtsMenuId"></div>
					</a></li>
				   </c:if>
				   <c:if test="${sessionScope.USER.userType == 'verification mgr' && sessionScope.USER.accessParliaments != null && fn:length(sessionScope.USER.accessParliaments) gt 1}">
					<li><a>PARLIAMENTS<p>Filter By Parliaments</p><div id="parliamentMenuId"></div>
					</a></li>
				   </c:if>
					<!--<li><a href="">QUALIFICATION<p>FILTER BY QUALIFICATION</p>
					</a></li>-->
					<c:if test="${sessionScope.USER.userType != 'Task Manager'}">
					<li><a>TASK MANAGERS<p>FILTER BY TASKMANAGERS</p><div id="taskMangersMenuId"></div>
					</a></li>
					</c:if>
					<li><a>CATEGORY<p>FILTER BY CATEGORY</p>
					<ul class="sidemenu-dropdown demo-y categoryDecheight arrow_box" style="width:500px;">
                        	<ul class="display-style" style="margin-left:10px;">
                            	<h5 style="margin-left:10px;">Party</h5>
                            	<li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Nominated posts">Nominated Posts
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="General">General
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Donations"> Donations
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Complaints"> Complaints
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="party_others"> Others
                                        </label>
                                    </div>
                                </li>
                            </ul>
                            <ul class="display-style pull-left">
                            	<h5 style="margin-left:10px;">Government</h5>
                            	<li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Police Case">Policy Case
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Deputations">  Deputations
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="CM Relief"> C M Relief
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Transfers"> Transfers
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Job"> Job Purpose
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="govt_others"> Others
                                        </label>
                                    </div>
                                </li>
                            </ul>
                            <ul class="display-style pull-right pad_right">
                            	<h5 style="margin-left:10px;">Welfare</h5>
                            	<li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Personal"> Personal
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Educational"> Educational
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Health"> Health
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Job Purpose"> Job Purpose
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio"  class="searchCls categoryCls" name="categoryRadio" value="EDP"> EDP
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="Financial Support"> Financial Support
                                        </label>
                                    </div>
                                </li>
                                <li>
                                    <div class="checkbox">
                                        <label>
                                          <input type="radio" class="searchCls categoryCls" name="categoryRadio" value="welfare_others"> Others
                                        </label>
                                    </div>
                                </li>
                            </ul>
                        </ul></a>
                    </li>
					<c:if test="${sessionScope.USER.userType == 'Agent' || sessionScope.USER.userType == 'Task Manager'}">
					<li><a>STATUS<p>FILTER BY STATUS</p>
					 <ul class="sidemenu-dropdown arrow_box">
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls statusCls" name="statusRadio" value="Completed"> Completed
                                    </label>
                                </div>
                            </li>
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls statusCls" name="statusRadio" value="In Progress"> IN Progress
                                    </label>
                                </div>
                            </li>
                        	
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls statusCls" name="statusRadio" value="Not Eligible"> Not Eligible
                                    </label>
                                </div>
                            </li>
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls statusCls" name="statusRadio" value="Not Verified"> Not Verified
                                    </label>
                                </div>
                            </li>
								<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls statusCls" name="statusRadio" value="not possible"> Not Possible
                                    </label>
                                </div>
                            </li>
								
                        </ul></a>
                    </li>
					</c:if>
                    <li><a>PARTY MEMBER<p>FILTER BY PARTY MEMBER</p>
					
                        <ul class="sidemenu-dropdown arrow_box">
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls partymemCls" name="partyMamberRadio" value="Voter"> Voter
                                    </label>
                                </div>
                            </li>
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls partymemCls" name="partyMamberRadio" value="Cadre"> Cadre
                                    </label>
                                </div>
                            </li>
                        	<li>
                                <div class="checkbox">
                                    <label>
                                      <input type="radio" class="searchCls partymemCls" name="partyMamberRadio" value="Other Party"> Other Party
                                    </label>
                                </div>
                            </li>
                        </ul></a>
                    </li>
                    <li><a>DATE FILTER BY DATE
                    <p id="dateId">Date : <i class="glyphicon glyphicon-remove"></i></p>
                    </a>
                    
                    <ul class="sidemenu-dropdown arrow_box" style="width:428px;border:0px;"><ul class="display-style">
                    	<div id="date-range12"><div id="date-range12-container"></div></div>
						<div class="calendar-butons">
							<button id="applyBtn" class="btn btn-info btn-sm">Apply</button>
							<!--<button onclick="cleardates();" class="btn btn-warning btn-sm">Cancel</button>-->
							<button onclick="cleardates();" class="btn btn-default btn-sm">Clear</button>
						</div>
                    </ul></ul>
                    </li>
				</ul>
			</nav>
            
        	<div class="page-heading">
                <div class="display-style">
	                <h2 class="display-style">Complaint Box</h2>
                    <span></span>
					<!--<span>/filters not applied</span>-->
                </div>
				<span class="pull-right display-style filters-icon"  id="st-trigger-effects">
                	<button class="filter-button-icon changeicon" data-effect="st-effect-1"><i class="glyphicon glyphicon-filter" style="color:#fff;"></i></button>
                </span>
				<button class="pull-right btn btn-danger" onclick="clearAllSelFields();" style="padding: 4px; margin-top: 7px;">CLEAR</button>
                <img src="dist/img/header-bottom.png" alt="bottom">
            </div>
            <ol class="breadcrumb breadcrumb-custom m_0">
             <c:if test="${sessionScope.USER.userType == 'Agent' || sessionScope.USER.userType == 'Task Manager'}">
              <li><a href="homeAction.action">Home</a></li>
			  <li class="active">Complaint Box</li>
              </c:if>
			 </ol>
			 <ul class="list-inline">
			  <li id="appenddistrict" class="districtRemove"></li>
			  <li id="tastmanager" class="taskRemove"></li>
			  <li id="appendcategoryCls" class="categoryRemove"></li>
			  <li id="appendstatusCls" class="statusRemove"></li>
			  <li id="appendpartymemCls" class="partyRemove"></li>
			 </ul>
		    <div id="content-animate">
           <div class="row">
           		<div class="col-md-8 col-xs-12 m_top10">
                	<h3 class="m_0">Search By Complaint</h3><br>
                    <div class="row m_top5">
                        <div class="col-md-12 col-xs-12">
                            <form class="form-inline row">
                            <div class="form-group col-md-4 col-xs-4">
                                <select class="select" id="searchComplaintId">
								<option value="Complaint ID">Complaint ID</option>
                                    <option value="Mobile Number">Mobile Number</option>
									<option value="Membership">Membership ID</option>
									<option value="Name">Name</option>
									<option value="Voter ID">Voter ID</option>
                                </select>
                            </div>
                            <div class="form-group">
	                            <input id="searchValue" class="form-control search-text" type="text">
                            </div>
                            <button type="button" class="btn btn-danger" id="clickbutton" onClick="searchComplaintByCategory(0);getExcelData('graph');">SEARCH</button>
                            </form>
							
                        </div>
                    </div>
                </div>
			
                <div class="col-md-4 col-xs-12 total-comp m_top-10">
                	<h3>TOTAL COMPLAINTS&nbsp;&nbsp;&nbsp;&nbsp;<span id="totalCntId"></span></h3>
                    <div class="display-style">
                        <div id="donutchart" class="display-style" style="height: 150px;float:left;width:310px;"></div>
                        <!--<ul class="display-style pull-right graph-list">
                            <li><span class="inp"></span>In Progress<span class="pull-right">157</span></li>
                            <li><span class="cmp"></span>Completed<span class="pull-right">100</span></li>
                            <li><span class="ncmp"></span>Not Completed<span class="pull-right">100</span></li>
                            <li><span class="note"></span>Not Eligible<span class="pull-right">100</span></li>
                        </ul>-->
                    </div>
                </div>
           </div> 
        <div class="row m_top20">
				<span class="pull-right export-excel" id="excelDataSpanId" style="margin-right: 25px;"><a class="btn btn-danger" style="cursor:pointer;" onClick="getExcelData('excel');">Export To Excel</a><img src="images/search.gif" style="display:none" id="processingImg1"/></span>
					<div style="display:block;" id="autoExport"></div>
				<span class="pull-right export-excel" id="getComplaintExcelDataId" style="display:none;"><a style="cursor:pointer;" onClick="getComplaintExcelData();">Export To Excel</a></span>
           		<div class="col-md-12 pad_0" id="resultDivId" style="margin-top:15px;"></div> </div>
				<div id="paginationDivId"  style="margin-left: 350px;"></div>
                 <div class="m_top20" id="resultDivIdForExcel" style="display:none;"></div>	
          
           </div>
        </div>
        </div>
</section>
<section class="disnone" id="profile">
	<div class="container section-container">
    	<div class="page-heading">
                <div>
	                <h2 class="display-style" id="complaintTitleId">Complaint Box</h2>
					<a class="btn btn-default btn-lg pull-right m_top10 btn-danger" style="margin-left: -16px; padding-bottom: 8px; padding-top: 6px;"><i class="glyphicon glyphicon-search" id="editSearchBtn" onclick="searchComplaints()"></i></a><div class="col-md-3 m_top10 pull-right "><input type="text" id="editsearchComplaintId" placeholder="Search by Complaint ID" class="form-control"></div>
                </div>
                <img src="dist/img/header-bottom.png" alt="bottom">
            </div>
    	<div class="row bg-white profile-row">
        	<div class="col-md-4 profile-info">
            	<h5><img src="dist/img/left-arrrow.png" width="18px" height="18px" alt="left arrow" class="left-arrow"/>&nbsp;&nbsp;&nbsp;PROFILE & STATUS</h5>
            	<div class="media m_0">
                  <div class="media-left pad_top10" id="profileImgDiv">
                   
                  </div>
                  <div class="media-body" id="profileDiv">
                  	
                  </div>
                </div>
				<div id="complaintCountDiv">
					<!-- <ul class="list-inline" style="border-top:1px solid #ccc;margin-top:5px">
						<li>
							<h1 class="m_0 text-center" style="font-size:50px;color:#666">05</h1>
							<h6 class="m_0">TOTAL COMPLAINTS</h6>
						</li>
						<li style="margin-top:5px">
								<ul class="display-style pull-right graph-list count-list">
									<li><span class="inp"></span>In Progress<span class="pull-right">157</span></li>
									<li><span class="cmp"></span>Completed<span class="pull-right">100</span></li>
									<li><span class="ncmp"></span>Not Completed<span class="pull-right">100</span></li>
									<li><span class="note"></span>Not Eligible<span class="pull-right">100</span></li>
								</ul>
						</li>
					</ul> -->
				</div>
				<div id="complaintsDiv">
               <!-- <ul class="inbox-messages row" style="margin-bottom:0px;">
					<li class="inbox-not-opened">
                    	<p class="m_0">Sir Please transfer to AP Model School Amadguru to Ananthapur</p>
                        <p class="m_0 font-10 m_top5">C ID 175486</p>
                        <p class="m_0 font-10">June 14 2015 13:32:45</p>
                    </li>
                    <li class="inbox-in-process">
                    	<p class="m_0">Sir Please transfer to AP Model School Amadguru to Ananthapur</p>
                        <p class="m_0 font-10 m_top5">C ID 175486</p>
                        <p class="m_0 font-10">June 14 2015 13:32:45<span class="pull-right"><i class="glyphicon glyphicon-paperclip"></i></span></p>
                    </li>
                    <li class="inbox-completed">
                        <p class="m_0">Sir Please transfer to AP Model School Amadguru to Ananthapur</p>
                        <p class="m_0 font-10 m_top5">C ID 175486</p>
                        <p class="m_0 font-10">June 14 2015 13:32:45</p>
                    </li>
                    <li class="inbox-not-eligible">
                    	<p class="m_0">Sir Please transfer to AP Model School Amadguru to Ananthapur</p>
                        <p class="m_0 font-10 m_top5">C ID 175486</p>
                        <p class="m_0 font-10">June 14 2015 13:32:45</p>
                    </li>
                    <li class="inbox-not-possible">
                    	<p class="m_0">Sir Please transfer to AP Model School Amadguru to Ananthapur</p>
                        <p class="m_0 font-10 m_top5">C ID 175486</p>
                        <p class="m_0 font-10">June 14 2015 13:32:45</p>
                    </li>                	
                </ul> -->
				</div>
            </div>
            <div class="col-md-8" id="leftPanel">
			<img src="images/loading.gif" style="width:200 height:400" id="processingImg" style="display:none;"/>
            	<div class="message-preview well" id="conversationDiv">
                   
                </div>
				<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
				  <div class="pdf-div" id="scanCopyDiv">
					
				  </div>
				  </c:if>
				<button id="showcompanyDetails" class="pull-right btn btn-default btn-custom m_right10 btn-success toggle_addcompany" style="margin-top:10px;display:none;"  type="button" value="Add Company Details" ><span id="showHideBasicinf">Add Company Details</span></button>
			
				<p class="m_0 text-italic m_top10">Problem Description</p>
				<p class="m_0" id="descriptionId"></p>
				<div id="remarksMainDiv"><div id="remarksErrorDiv"></div>
				 <div class="remarks">
                	<div class="m_top10"><span  class="text-italic">Remarks</span><span class="add-remarks"></span></div>
					<c:if test="${sessionScope.USER.userType == 'verification mgr'}">
                	<div><b>Select Language: </b> <input type="radio"  value="te" name="language" class="lang" id="telugu" checked  onclick="languageChangeHandler();"> Telugu <input type="radio" value="hi" name="language" class="lang" id="hindi" onclick="languageChangeHandler();"> Hindi<input type="radio"  value="en" name="language" class="lang" id="eng" onclick="languageChangeHandler();"> English </div>
					<textarea class="form-control" id="infoCenterRemarksId"></textarea>
					<button class="btn btn-success btn-sm m_top10" onclick="updateRemarks();">Submit</button>
					
					</c:if>
                    <div class="remarks-ul-scroll">    	
					<ul id="remarksInfoDiv">
                    	
  		            </ul>
					</div>
					</div>
                </div>
				
				<span id="SavedCompanyDetails"></span>
						<div class="row well basic-info" id="categoryId" style="display:none;margin-left: 10px;margin-right: 10px;">
							
					<input type="hidden" id="hiddencomplaintId" class="clearCls"/>
				<div class="col-md-3 col-xs-6">
				   <label class="categoryLabel">Company Name</label>
				    <input  type="text" id="companyId" class="form-control"/>
				</div>
				<div class="col-md-3 col-xs-6">
				  <label class="categoryLabel">Officer Name</label>
				  <input  type="text" id="officerId" class="form-control"/>
				</div>
				<div class="col-md-3 col-xs-6">
				    <label class="categoryLabel">Salary</label>
					<input  type="text" id="salaryId" class="form-control"/>
				</div>
				<div class="col-md-3 col-xs-6">
				  <label class="categoryLabel">Grade</label>
				  <input  class="form-control" id="gradeId" type="text" />
				</div>
				<div class="col-md-3 col-xs-6" style="margin-top:10px;">
				 <label class="categoryLabel">Date</label>
				<input type="text" readonly="readonly" id="datejoiningId" class="form-control"  style="cursor:pointer;width:160px;margin-top:10px;"/>
				</div>
				<button class="pull-right btn btn-default btn-custom m_right10 btn-success " style="margin-top:10px;" onclick="savingCompanyDetails();" type="button" value="Add Company Details" >submit</button>
				
				</div>
				<span id="updatestatusDiv"></span>
			    <div class="complaint-status well" id="complaintstatusDiv" >
		
				<span id="typeOfIssue"></span> </span>&nbsp;&nbsp;
                    <span id="category"></span>  </span>&nbsp;&nbsp;<span id="assignedToId"></span>
					&nbsp;&nbsp;<span id="educationSupportForId"></span>
					 &nbsp;&nbsp;<span id="educationPurposeId"></span>
					
					<!--<button class="pull-right btn btn-success btn-sm" id="editBtn" onclick="showDoneBtn()">EDIT</button>-->
			       <span class="pull-right" id="editBtn"><a style="cursor:pointor;"><button class="btn btn-success btn-sm" style="display:none;" ><i class="glyphicon glyphicon-edit"></i>   EDIT</button></a></span>
					
					<div class="row m_top20">
                    	<div class="col-md-4"><label>Complaint Status</label><select class="form-control" id="statusSelect" >
						<option  value="Select">Select Status</option>
						<c:if test="${sessionScope.USER.userType == 'verification mgr'}">
						<option  value="not verified">Not Verified</option>
						<option value="in progress">IN Progress</option>
						</c:if>
						
						<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
						<option  value="not verified">Not Verified</option>
						<option value="in progress">IN Progress</option>
						<option value="not eligible">Not Eligible</option>
						<option value="not possible">Not Possible</option>
						<option value="completed">Completed</option>
						</c:if>
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'Super Admin') }" >
						   <option value="approved">Approved</option>
						</c:if>
						</select></div>
						<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
                        <div class="col-md-4"><label>Is Party Member</label><select  class="form-control" id="partyMemberSelect" ><option value="Select">Select</option><option value="cadre">Cadre</option><option value="voter">Voter</option><option value="other party">Other Party</option></select></div>
                       
                        <div class="col-md-4"><label>Assigned to</label><select  class="form-control" id="assignedTo" ><option> Not Assigned</option></select></div>
						
						<div class="col-md-4" id="requestedAmountDiv" style="display:none;">
                        	<label>Requested Amount</label>
                            <input type="text" class="form-control" id="requestAmount" disabled/>
                            	
                        </div>
						
						<div class="col-md-4" id="educationalSeatPurpose" style="display:none;">
                        	<label>Purpose</label>
                            <input type="text" class="form-control" id="seatPurpose" />
						</div>
						
						<c:if test="${fn:contains(sessionScope.USER.entitlements, 'Super Admin') }" >
                    	<div class="col-md-4" id="approvedamountDiv" style="display:none;">
                        	<label>Approved Amount</label>
                            <input type="text" class="form-control" id="approvedAmount" />
                            	
                        </div>
						</c:if>
						
						<div class="col-md-4" id="solveStatusDiv" style="display:none;">
                        	<label>Is this Issue Solved</label>
							<select id="solveId" class="form-control clearSelect"><option value="0">Select </option><option value="Y">YES</option><option value="N">NO</option></select>
						 </div>
						
						</c:if>
						<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
						<div class="col-md-12 m_top10">
						<label>Comments</label><textarea id="commentsTextId" class="form-control" type="text"></textarea></div>
						
						</c:if>
						<div class="col-md-12">
						<button class="pull-right btn btn-success btn-sm m_top10" id="doneBtn" onclick="updateAll();">UPDATE</button></div>
                    </div>	
					
                </div>
                <!--<div class="complaint-status1" id="complaintstatus1Div">
					<div class="row">
                    	<div class="col-md-3 col-xs-6 col-sm-6"><label>Complaint Status</label><select class="select" id="statusSelect" onchange="updateDetails('updateStatus')"><option  value="Select">Select Status</option><option  value="not verified">Not Verified</option><option value="completed">Completed</option><option value="in progress">In Progress</option><option value="not eligible">Not Eligible</option><option value="not possible">Not possible</option></select></div>
                        <div class="col-md-3 col-xs-6 col-sm-6" ><label>Is Party Member</label><select class="select" id="partyMemberSelect" onchange="updateDetails('updateType')"><option value="Select">Select</option><option value="cadre">Cadre</option><option value="voter">Voter</option><option value="other party">Other Party</option></select></div>
                       
                        <div class="col-md-3 col-xs-6 col-sm-6" ><label>Assigned to</label><select class="select" id="assignedTo" onchange="assignTaskManager();"><option> Not Assigned</option></select></div>
                    </div>
                  <button type="button" class="btn btn-success btn-sm done-processing m_top5" onclick="complaintDoneClick();">Close!</button>
                </div>-->
				
				
					<div id="commentsDiv" class="comments " >
					
						<div class="comments demo-y" style="margin-top: 10px;">
						<ul id="commentInfoDiv">
						 
						</ul>
					</div>
					</div>
				
            </div>
        </div>
    </div>
</section>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document" style="width: 990px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remarks</h4>
	  </div>
      <div class="modal-body demo-y" id="modelheightincr">
		<div class="row">
			<div class="col-md-12">
				<div id="popupContentDiv" class="remarks" ></div>
			</div>
		</div>
		
      </div>
     
    </div>
  </div>
  </div>
  <div class="modal fade" id="commentsPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog " role="document" style="width: 990px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Comments</h4>
	  </div>
      <div class="modal-body demo-y" id="modelheightincr">
		<div class="row">
			<div class="col-md-12">
				<div id="commentsPopupContentDiv" class="comments" ></div>
			</div>
		</div>
		
      </div>
     
    </div>
  </div>
  </div>
<footer>
	
</footer>
<script src="dist/js/moment.min.js" type="text/javascript"></script>
<script src="dist/js/dropkick.js" type="text/javascript"></script>
<script src="dist/js/highcharts.js" type="text/javascript"></script>
<script src="dist/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="dist/js/dataTables.responsive.js" type="text/javascript"></script>
<script src="dist/sidebar/js/classie.js" type="text/javascript"></script>
<script src="dist/sidebar/js/sidebarEffects.js" type="text/javascript"></script>
<script src="dist/Scroller/fm.scrollator.jquery.js" type="text/javascript"></script>
<script src="dist/js/daterangepicker.js" type="text/javascript"></script>
<script src="dist/js/jquery.daterangepicker.js" type="text/javascript"></script>
<script src="dist/bootstrap3-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<script src="js/simplePagination/simplePagination.js" type="text/javascript"></script>
<script src="scrollnew/jquery.mCustomScrollbar.min.js"></script>

<script>

  var userId = '${sessionScope.USER.userId}';
var complaintType = '${complaintType}';
var complaintId = '${complaintId}';
var voterId = '${voterId}';
 var requestfromDate = '${fromDate}';
 var requesttoDate = '${toDate}';
populateSearchBoxForDashBoard();

$('.background-head').addClass('animated zoomIn');
$('header').addClass('disnone');
$('#profile,#content-section').addClass('disnone');
setTimeout(function(){
$('.page-load-svg').hide();
$('#profile').addClass('disnone');
},1000);
setTimeout(function(){
	$('header').removeClass('disnone');
	$('header').addClass('animated-cust slideInDown');
	},2000);
setTimeout(function(){
	$('#content-section').removeClass('disnone');
	$('#content-section').addClass('animated-cust slideInUp');
	},2000);
$('.select').dropkick({theme : 'black'});
setTimeout(function(){
$('header').removeClass('animated-cust slideInDown');
$('#content-section').removeClass('animated-cust slideInUp');
$('.background-head').removeClass('animated zoomIn');
},2100);
$(function () {
	   /*$('.DataTableDiv').DataTable( {
	    responsive: true,
		"paging":   false,
        "info":     false,
		"searching": false,
		//"scrollY":   "100px",
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		
    } );*/
});

$(document).ready(function(){
	$("#datejoiningId").datepicker({
		dateFormat: "yy-mm-dd",
		inline: true,
		keepOpen:true,
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
		$(document).on("mouseover", ".day",function(){

			$(".day").css("cursor","pointer");
		});
		$(document).on("click", ".day",function(){

			$(".datepicker").hide();
		});
			
	$("#datejoiningId").datepicker("setDate", new Date());
	$('.toggle_addcompany').click(function(){
		$('.basic-info').toggle();
		var currentText = $("#showHideBasicinf").html();
	if(currentText == "Add Company Details"){
		$("#showHideBasicinf").html("Hide Company Details");
	}else{
		$("#showHideBasicinf").html("Add Company Details");
	}
	});
});
function serverUnreachableHandler(a) { document.getElementById("remarksErrorDiv").innerHTML = "Transliteration Server unreachable"; }
function serverReachableHandler(a) { document.getElementById("remarksErrorDiv").innerHTML = ""; }

 $('.assign-modal-scrollar').scrollator({
    custom_class: 'scrollar',   // A class to be added to this scrollator lane
    zIndex: '10000',
});


$('.left-arrow').click(function(){
   $('.profile-info').addClass('animated slideOutLeft');
   $('.profile-info').removeClass('animated slideInLeft');
   $('.profile-image').removeClass('animated zoomIn');
	setTimeout(function(){
	$('#profile').addClass('disnone');
	$('#profile').addClass('animated slideOutLeft');
	$('#content-section').removeClass('disnone');
	$('#content-animate').addClass('animated slideInUp');
	},10);
});
 function complaintEditClick()
 {
 $('.complaint-status').hide();
	$('.complaint-status1').show();
 }

 function populateSearchBoxForDashBoard()
 {


 if(complaintId != null && complaintId.length > 0)
 {

 $("#searchComplaintId").val('Complaint ID');
 $("#searchValue").val(complaintId);
 }
 if(voterId != null && voterId.length > 0)
 {
 $("#searchComplaintId").val('Voter ID');
 $("#searchValue").val(voterId);
 }
 }
  function complaintDoneClick()
 {
$('.complaint-status').show();
	$('.complaint-status1').hide();
	<c:if test="{sessionScope.USER.userType != 'verification mgr'}">
	getTotalComplaintsForAUser(complaintIdVar);
	</c:if>
	getConversationDetailsByComplaint(complaintIdVar,null);
 }
 </script><script>
$('.complaint-status1,.edit-input,.comments-input,.com-ar-btn,.text-ar-btn').hide();
/*$('.compalaint-edit').click(function(){
	$('.complaint-status').hide();
	$('.complaint-status1').show();
});
$('.done-processing').click(function(){
	$('.complaint-status').show();
	$('.complaint-status1').hide();
});*/
$('.edit-text').click(function(){
	$('.edit-input').show();
	$(this).hide();
});
$('.text-ar-btn').click(function()
{
	$('.text-ar,.text-ar-btn').hide();
	$('.edit-text').show();
});

/*$('.com-ar-btn').click(function()
{
	$('.com-ar,.com-ar-btn').hide();
});*/
$('.comments-text').click(function(){
	$('.comments-input,.com-ar-btn').toggle();
});
$('.inbox-messages').scrollator();
$('#date-range12').dateRangePicker(
		{
			inline:true,
			container: '#date-range12-container', 
			alwaysOpen:true,
			format: 'MM/DD/YYYY',
		});
	

		
function cleardates(){
	$(".day").each(function(){
		$(this).removeClass("checked");
	});
	$(".start-day").html("...");
	$(".end-day").html("...");
	//$(".default-top").html("Please select a date range");
	$(".selected-days").html("");
}
$('.edit-kk').click(function(){
		$('.status').editable('toggleDisabled', true);
		$('.status1').editable('toggleDisabled', true);
		$('.status2').editable('toggleDisabled', true);
		$('.status3').editable('toggleDisabled', true);
	});

	
function getDistrictsByState(stateId)
{
	var jobj = {
		stateId : stateId,
		task:"getDistricts"
	}
	$.ajax({
          type:'POST',
          url: 'getDistrictsAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			var count = 0;
			var str='';
			str+=' <ul class="sidemenu-dropdown arrow_box">';
			str+='<ul class="display-style">';
			<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
			for(var i in result)
			{
				if(result[i].type == 1){
                    str+='<li>';
                    str+='<div class="checkbox"><label><input type="radio" onClick="searchClick()" name="districtRadio" class="district" value="'+result[i].name+'">'+result[i].name+'</label></div>';
                    str+='</li>';  
				}
				else if(result[i].type == 2){
					count = count +1;
					if(count==1){
						str+='</ul>';
						str+='<ul class="display-style pull-right pad_right">';
					}
					str+='<li>';
                    str+='<div class="checkbox"><label><input type="radio" onClick="searchClick()" name="districtRadio" class="district" value="'+result[i].name+'">'+result[i].name+'</label></div>';
                    str+='</li>';
				}
			}
			</c:if>
			<c:if test="${sessionScope.USER.userType == 'verification mgr' && sessionScope.USER.accessDistricts != null && fn:length(sessionScope.USER.accessDistricts) gt 1}">
			   <c:forEach items="${sessionScope.USER.accessDistrictNames}" var="dist" >
			     str+='<li>';
                 str+='<div class="checkbox"><label><input type="radio" onClick="searchClick()" name="districtRadio" class="district" value=\'<c:out value="${dist}"/>\'><c:out value="${dist}"/></label></div>';
                 str+='</li>';
                </c:forEach>
			</c:if>
			str+='</ul>';	 
			str+='</ul>';
			
			$("#districtsMenuId").html(str);
				
		  });
		 
}


<c:if test="${sessionScope.USER.userType == 'verification mgr' && sessionScope.USER.accessParliaments != null && fn:length(sessionScope.USER.accessParliaments) gt 1}">
   var parlStr ="";
   parlStr+=' <ul class="sidemenu-dropdown arrow_box">';
   parlStr+='<ul class="display-style">';
		<c:forEach items="${sessionScope.USER.accessParliamentsList}" var="parl" >
		parlStr+='<li>';
		parlStr+='<div class="checkbox"><label><input type="radio" onClick="searchClick()" name="parlRadioChk" class="parlRadioChk" value=\'<c:out value="${parl.id}"/>\'><c:out value="${parl.name}"/></label></div>';
		parlStr+='</li>';
	   </c:forEach>
	   parlStr+='</ul>';	 
	   parlStr+='</ul>';
	$("#parliamentMenuId").html(parlStr);
</c:if>
var tskmgr = [];
function getAllTaskManagers()
{
tskmgr = new Array();
	var jobj = {
		task:"getTaskManagers"
	}
	$.ajax({
          type:'POST',
          url: 'getAllTaskManagersAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		  taskManagers = new Array();
		    var cnt = 0;
			var str='';	
			
			str+='<ul class="sidemenu-dropdown demo-y taskManagerDecheight arrow_box"><ul class="display-style">';
			for(var i in result){
				cnt = cnt+1;
				str+='<li><div class="checkbox"><label><input type="radio" value="'+result[i].name+'" onClick="searchClick()" class="taskManagerRadio" name="taskManagerRadio" >'+result[i].name+'</label></div></li>';
				if(cnt==parseInt(result.length/2)+1){
				str+='</ul><ul class="display-style pull-right pad_right">';
				}
				var obj = {
				id:result[i].id,
				name : result[i].name
				}
			tskmgr.push(obj);
				
            }                 
            str+='</ul></ul>';
		  
			$("#taskMangersMenuId").html(str);
			 $(".demo-y").mCustomScrollbar();
			$(".taskManagerDecheight").parent().parent().find(".mCustomScrollBox").addClass("widthAddedDyna");
					
					$('.widthAddedDyna').css('height', '360px');
		  });
}	
			
 $(document).on("click",".district",function(){
	      var str='';
			str+='<span style="font-weight:bold;">DISTRICT:&nbsp; &nbsp;&nbsp;</span>';
			str+=$(this).val();
			
			$("#appenddistrict").html(str);
			$("#appenddistrict").append("<img  class='crossmarkalign ' src='images/close_icon1.png' style='cursor:pointer;'>");
			$("#appenddistrict").addClass('alignDiv');
			
	});
	$(document).on("click",".taskManagerRadio",function(){
		 var str='';
			str+='<span style="font-weight:bold;">TASK MANAGERS:&nbsp; &nbsp;&nbsp;</span>';
			str+=$(this).val();
			$("#tastmanager").html(str);
			$("#tastmanager").append("<img  class='crossmarkalign ' src='images/close_icon1.png' style='cursor:pointer;'>");
			$("#tastmanager").addClass('alignDiv');
	});
	 $(document).on("click",".partymemCls",function(){
		  var str='';
			str+='<span style="font-weight:bold;">PARTY MEMBERS:&nbsp; &nbsp;&nbsp;</span>';
			str+=$(this).val();
			$("#appendpartymemCls").html(str);
			$("#appendpartymemCls").append("<img  class='crossmarkalign ' src='images/close_icon1.png' style='cursor:pointer;'>");
			$("#appendpartymemCls").addClass('alignDiv');
	});
	 $(document).on("click",".statusCls",function(){
		   var str='';
			str+='<span style="font-weight:bold;">STATUS:&nbsp; &nbsp;&nbsp;</span>';
			str+=$(this).val();
			$("#appendstatusCls").html(str);
			$("#appendstatusCls").append("<img  class='crossmarkalign ' src='images/close_icon1.png' style='cursor:pointer;'>");
			$("#appendstatusCls").addClass('alignDiv');
		});
	 $(document).on("click",".categoryCls",function(){
		   var str='';
			str+='<span style="font-weight:bold;">CATEGORY:&nbsp; &nbsp;&nbsp;</span>';
			str+=$(this).val();
			$("#appendcategoryCls").html(str);
			$("#appendcategoryCls").append("<img  class='crossmarkalign ' src='images/close_icon1.png' style='cursor:pointer;'>");
			$("#appendcategoryCls").addClass('alignDiv');
	});

	$(document).on("click",".districtRemove",function(){
			$('.district').attr('checked', false);
			$(this).html('');
			$("#appenddistrict").removeClass('alignDiv');
			searchComplaintByCategory(0);
			getExcelData('graph');
	
	});
	$(document).on("click",".taskRemove",function(){
		$('.taskManagerRadio').attr('checked', false);
		$(this).html('');
		$("#tastmanager").removeClass('alignDiv');
			searchComplaintByCategory(0);
			getExcelData('graph');
	});
	$(document).on("click",".partyRemove",function(){
		$('.partymemCls').attr('checked', false);
		$(this).html('');
		$("#appendpartymemCls").removeClass('alignDiv');
		searchComplaintByCategory(0);
		getExcelData('graph');
	});
	$(document).on("click",".categoryRemove",function(){
	$('.categoryCls').attr('checked', false);
		$(this).html('');
		$("#appendcategoryCls").removeClass('alignDiv');
		searchComplaintByCategory(0);
		getExcelData('graph');
	});
	$(document).on("click",".statusRemove",function(){
		$('.statusCls').attr('checked', false);
		$(this).html('');
		$("#appendstatusCls").removeClass('alignDiv');
		searchComplaintByCategory(0);
		getExcelData('graph');
	}); 
 </script><script>
function getQualificationList()
{
	var jobj = {
		task:"getQualificationList"
	}
	$.ajax({
          type:'POST',
          url: 'getQualificationListAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			var str='';	
			
			str+='<ul class="sidemenu-dropdown qualification arrow_box">';
			for(var i in result)
            str+='<li> <div class="checkbox"><label><input type="radio" onClick="searchClick()" name="qualificationRadio" value="'+result[i]+'">'+result[i]+'</label></div></li>';
			str+='</ul>';
			$("#qualificationMenuId").html(str);
		  });
}
var prevClkIndx = 0;
function searchComplaintByCategory(index){

	prevClkIndx = index;
	$("#getComplaintExcelDataId").css("display","none");
	$("#excelDataSpanId").css("display","none");
	$("#resultDivId").html('');
	$("#paginationDivId").hide();
$("#tableId").html("");
	var searchOpt = $("#searchComplaintId").val();
	var searchVal = $("#searchValue").val().trim();
	var district = typeof($("input[name='districtRadio']:checked").val()) != 'undefined' ? $("input[name='districtRadio']:checked").val() : "";

	var qualification = typeof($("input[name='qualificationRadio']:checked").val()) != 'undefined' ? $("input[name='qualificationRadio']:checked").val() : "";
	
	var taskManager = typeof($("input[name='taskManagerRadio']:checked").val()) != 'undefined' ? $("input[name='taskManagerRadio']:checked").val() : "";
	
	var category = typeof($("input[name='categoryRadio']:checked").val()) != 'undefined' ? $("input[name='categoryRadio']:checked").val() : "";
	
	var status = typeof($("input[name='statusRadio']:checked").val()) != 'undefined' ? $("input[name='statusRadio']:checked").val() : "";
	
	var partyMember =  typeof($("input[name='partyMamberRadio']:checked").val()) != 'undefined' ? $("input[name='partyMamberRadio']:checked").val() : "";
	var parliamentId =  typeof($("input[name='parlRadioChk']:checked").val()) != 'undefined' ? $("input[name='parlRadioChk']:checked").val() : "0";
	var searchOpt = $("#searchComplaintId").val();
	var searchVal = $("#searchValue").val();
	/*var dateValue = $("#reservation").val();
	if(typeof(dateValue) != 'undefined' && dateValue !== null && dateValue.length>0){	
	var date = dateValue.split("-");
	var fromDate = date[0];
	

	var toDate = date[1];
	}*/
	var fromDate = $(".start-day").text();
	var toDate = $(".end-day").text();
	if((fromDate == '...' || fromDate == null) && (toDate == null || toDate == '...')) {
		 fromDate = "";
		 toDate = "";
	}
	
	if(requestfromDate != '' && requestfromDate != null)
	{
	 
		fromDate = requestfromDate;
		toDate = requesttoDate;
	}
	var jobj={
	userId:userId,
	district:district,
	category:category,
	fromDate:fromDate,
	toDate:toDate,
	memberType:"",
	partyMember:partyMember,
	qualification:qualification,
	taskManager:taskManager,
	searchOpt:searchOpt,
	searchVal:searchVal,
	status:status,
	startIndex:index,
	maxIndex:25,
	parliamentId:parliamentId,
	type:'search'
	}
	$.ajax({
          type:'POST',
          url: 'complaintsSearchAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			if(result != null && result.length >0)
				buildSearchComplaintByCategoryResult(result,index);
			
			else{
			    $("#totalCntId").html('0');
				$("#resultDivId").html("No Complaints For This Search Criteria");
				}
		  });
		
}
$( "#searchValue" ).keypress(function( event ) {
			 if ( event.which == 13 ) {
				 event.preventDefault();
				$("#clickbutton").trigger("click");
			  }
          });
		  
		  $( "#editsearchComplaintId" ).keypress(function( event ) {
			 if ( event.which == 13 ) {
				 event.preventDefault();
				$("#editSearchBtn").trigger("click");
			  }
          });
$(".searchCls").click(function(){
	searchComplaintByCategory(0);
});

function searchClick(){
    searchComplaintByCategory(0);
}
function buildSearchComplaintByCategoryResult(result,startIndex){

	$("#paginationDivId").show();
	$("#excelDataSpanId").css("display","block");
	
	if(result == null || result.length == 0)
	$("#totalCntId").html('0');
	else
	$("#totalCntId").html(result[0].count);
	var str = '';
	str+='<table class="display table table-bordered table-striped table-hover m_top5" id="complaintTableId">';
    str+='<thead style="border-top:1px solid #ccc">';
    str+='<th style="width: 62px;">CID</th>';
	str+='<th style="width:12.5%">Name</th>';
    str+='<th style="width:12.5%">Subject</th>';
	str+='<th style="width:10.5%">Type Of Issue</th>';
	str+='<th style="width:12.5%">Task Manager</th>';
    str+='<th style="width:12.5%">Is Party Member?</th>';
    str+='<th style="width:12.5%">Status</th>';
    str+='<th style="width:12.5%">Date</th>';
   // str+='<th style="width:12.5%">Remarks</th>';
	str+='<th style="width:14.5%">Edit</th>';
    str+='</thead>';
   
	for(var i in result){
		str+='<tr id="'+result[i].complaintId+'">';
         str+='<td>'+result[i].complaintId+'</td>';
		
		str+='<td>';
		str+='N :'+result[i].name+'<br/>';
		str+='Mobile :'+result[i].mobile+'<br/>';
		/* <c:if test="${not fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint' )}">
		str+='N :'+result[i].name+'<br/>';
		</c:if>
		<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint')}">
		str+='<a href="editComplaintAction.action?complaintId='+result[i].complaintId+'" TARGET="_blank">N :'+result[i].name+'</a><br/>';
		</c:if> */
		str+='D :'+result[i].location+'<br/>C :'+result[i].constituency+'<br/>M :'+result[i].mandal+'<br/>V :'+result[i].villageName+'</td>';
        
		str+='<td>'+result[i].subject+'</td>';
		str+='<td>'+result[i].typeOfIssue+'</td>';
		str+='<td>'+result[i].taskMgr+'</td>';
		str+='<td>'+result[i].memberType.toUpperCase()+'</td>';	
        //str+='<td>'+result[i].status+'</td>';
		
		if(result[i].status.toLowerCase() == ('Not Verified').toLowerCase())
			 str+='<td style="color:#D64D54"><span class="textTransFormCls">'+result[i].status.toUpperCase()+'</span></td>';
		else if(result[i].status.toLowerCase() == ('Completed').toLowerCase())
			 str+='<td style="color:#00B17D"><span class="textTransFormCls">'+result[i].status.toUpperCase()+'</span></td>';
		else if(result[i].status.toLowerCase() == ('In Progress').toLowerCase())
			 str+='<td style="color:#66CDCC"><span class="textTransFormCls">'+result[i].status.toUpperCase()+'</span></td>';
        else if(result[i].status.toLowerCase() == ('Not Eligible').toLowerCase())
			 str+='<td style="color:#663300"><span class="textTransFormCls">'+result[i].status.toUpperCase()+'</span></td>';
		else if(result[i].status.toLowerCase() == ('Not possible').toLowerCase())
			 str+='<td style="color:#FF9933"><span class="textTransFormCls">'+result[i].status.toUpperCase()+'</span></td>';
		else
			str+='<td></td>';
		
        str+='<td>'+result[i].raisedDate+'</td>';
       // str+='<td>Remarks</td>';
		str+='<td>';
		
		<c:if test="${not fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint')}">
		str+='<i class="glyphicon glyphicon-edit" onClick="editDetails('+result[i].complaintId+');getUserBasicDetailsByComplaintId('+result[i].complaintId+');" title="Edit Status"></i>';
		
		</c:if>
		 <c:if test="${sessionScope.USER.userType == 'Agent'}">
		 str +='<a href="editComplaintAction.action?complaintId='+result[i].complaintId+'"  style="margin-left: 15px;color:black;" title="Edit Complaint"><i class="glyphicon glyphicon-pencil" ></i></a>';
		</c:if>
		 <c:if test="${sessionScope.USER.userType == 'Task Manager'}">
         <c:if test="${fn:contains(sessionScope.USER.entitlements, 'EditComplaint')}">
		str +='<a href="editComplaintAction.action?complaintId='+result[i].complaintId+'"  style="margin-left: 15px;color:black;" title="Edit Complaint"><i class="glyphicon glyphicon-pencil" ></i></a>';
		</c:if>
		</c:if>
		
		 <c:if test="${sessionScope.USER.userType != 'verification mgr'}">
		 if(result[i].remarks  != null && result[i].remarks == "true")
		  str+='<i style="margin-left: 15px;cursor:pointer;" class="glyphicon glyphicon-file modelshow" title="Remarks" onclick="getRemarksPopup(\''+result[i].complaintId+'\');"></i>';
		 </c:if>
		 <c:if test="${sessionScope.USER.userType == 'Agent'}">
		   if(result[i].comments  != null && result[i].comments == "true")
		   str+='<i style="margin-left: 15px;cursor:pointer;" class="glyphicon glyphicon-list-alt commentsModelshow" title="Comments" onclick="getCommentsByComplaintId(\''+result[i].complaintId+'\');"></i>';
		 </c:if>
		 <c:if test="${sessionScope.USER.userType == 'Agent'}">
		  <c:if test="${not fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint')}">
		  str +='<i class="glyphicon glyphicon-remove-circle" onclick="deleteComplaint('+result[i].complaintId+')" style="margin-left: 15px;cursor:pointer;" title="Delete Complaint"></i>';
		 </c:if>
		 </c:if>
		str +='</td>';
        str+='</tr>';
	}
    str+='</table>';
		$("#resultDivId").html(str);		
		$('#tableId').DataTable({
		responsive: true,
		"info":     false,
		"bSearching": false,
		"order": [[1,"desc"]],
		"sDom": '<"top"i>rt<"bottom"flp><"clear">',
		"columnDefs": [
			{ "width": "25%", "targets": 0 }],		
		"bPaginate": false,
		"bLengthChange": false,
		"bAutoWidth": false,
		
		});
		getExcelData('graph');
		
		if(startIndex==0){
		
		$("#paginationDivId").pagination({
		items: result[0].count,
		//itemsOnPage: 50,
		itemsOnPage: 25,
		cssStyle: 'light-theme',
		onPageClick: function(pageNumber, event) {
			var num=(pageNumber-1)*25;
			searchComplaintByCategory(num);
			
		}
		});
		}
		
}
 </script><script>
function getTaskMgrsWithCounts(complaintId)
{
	var jobj = {
		complaintId:complaintId,
		task:"getTaskMgrs"
	}
	$.ajax({
          type:'POST',
          url: 'getTaskMgrsWithAssignedCountAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		    var cnt = 0;
			var str='';	
			str+='<table class="table table-bordered">';
			str+='<thead>';
			str+='<th>Manager Name</th>';
            str+='<th>No of Complaints Assigned</th>';
			str+='</thead>';
			for(var i in result){
				str+='<tr>';
				str+='<td><label><input type="radio" class="taskMgrRadioCls" value="'+result[i].id+'" name="taskMgr">'+result[i].name+'</label></td>';
				if(result[i].type != null)
				str+='<td>'+result[i].type+'</td>';
				else
				str+='<td>0</td>';
				str+='</tr>';
			}
			str+='</table>';
			$("#taskMgrTableDiv").html(str);
			$("#submitDivId").html('<button class="btn btn-default btn-custom" onClick="assignTaskManager('+complaintId+');">SUBMIT</button>');
		  });
}
function assignTaskManager(){
 //var takMgrId = $('input[name="taskMgr"]:checked').val();
 var takMgrId = $("#assignedTo").val();
 if(takMgrId == 0){
 return false;
 }
 var jobj = {
		complaintId:complaintIdVar,
		takMgrId :takMgrId,
		userId:userId,
		task:"getTaskMgrs"
	}
	$.ajax({
          type:'POST',
          url: 'assignComaplaintToTaskMgrAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		  
		  });
 
}
function updateApprovedAmount(){

 var amount = $("#approvedAmount").val();
 if(amount < 0){
 return false;
 }
 var jobj = {
		complaintId:complaintIdVar,
		amount :amount,
		userId:userId,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'updateApprovedAmountAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		  
		  });
 
}

function savingCompanyDetails(){
	
	var complaintId=$("#hiddencomplaintId").val(); 
	var companyName= $("#companyId").val();
	var officerName= $("#officerId").val();
	var salary= $("#salaryId").val();
	var grade= $("#gradeId").val();
	var dateOfJoining=$("#datejoiningId").val();
	
	var jobj={
		complaintId:complaintId,
		companyName:companyName,
		officerName:officerName,
		salary:salary,
		grade:grade,
		dateOfJoining:dateOfJoining
	}
	
	$.ajax({
		 type:'POST',
          url: 'savingCompanyDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jobj)},
	}).done(function(result){
		$("#SavedCompanyDetails").html('<font color="green">Saved Successfully</font>');
		setTimeout(function()
		{
		$("#SavedCompanyDetails").html('');
		},1000)
	
	});
	
	
}

function updateSolveStatus()
{

var isSolved = $("#solveId").val();

if(isSolved == '0')
return;
var jobj = {
		complaintId:complaintIdVar,
		solveComment :"",
		userId:userId,
		isChecked:isSolved,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'updateSolveStatusAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		  
		  });

}

function updateDetails(type){
  var status="";

  if(type == 'updateType')
	status = $('#partyMemberSelect').val();
  else
	status= $("#statusSelect").val();
	
	if(status == 'Select' || status.length == 0 || status == ''){
		return;
	}
 var jobj = {
		complaintId:complaintIdVar,
		status :status,
		type:type,
		userId:userId,
		task:"update"
	}
	$.ajax({
          type:'POST',
          url: 'updateStatusAndTypeAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
		     if(type == 'updateStatus')
			 {
			   getTotalComplaintsForAUser(complaintIdVar);
			 }
		  });
 
}

function getExcelData(type){
	
	if(type == "excel")
		{
			$("#processingImg1").show();
		} 
    var searchOpt = $("#searchComplaintId").val();

    var searchVal = $("#searchValue").val().trim();
	var district = typeof($("input[name='districtRadio']:checked").val()) != 'undefined' ? $("input[name='districtRadio']:checked").val() : "";

	var qualification = typeof($("input[name='qualificationRadio']:checked").val()) != 'undefined' ? $("input[name='qualificationRadio']:checked").val() : "";
	
	var taskManager = typeof($("input[name='taskManagerRadio']:checked").val()) != 'undefined' ? $("input[name='taskManagerRadio']:checked").val() : "";
	
	var category = typeof($("input[name='categoryRadio']:checked").val()) != 'undefined' ? $("input[name='categoryRadio']:checked").val() : "";
	
	var status = typeof($("input[name='statusRadio']:checked").val()) != 'undefined' ? $("input[name='statusRadio']:checked").val() : "";
	
	var partyMember =  typeof($("input[name='partyMamberRadio']:checked").val()) != 'undefined' ? $("input[name='partyMamberRadio']:checked").val() : "";
	var parliamentId =  typeof($("input[name='parlRadioChk']:checked").val()) != 'undefined' ? $("input[name='parlRadioChk']:checked").val() : "0";
	var searchOpt = $("#searchComplaintId").val();
	var searchVal = $("#searchValue").val();
	var fromDate = $(".start-day").text();
	var toDate = $(".end-day").text();
	if((fromDate == '...' || fromDate == null) && (toDate == null || toDate == '...')) {
		 fromDate = "";
		 toDate = "";
	}
	if(requestfromDate != '' && requestfromDate != null)
	{
		fromDate = requestfromDate;
		toDate = requesttoDate;
	}
	var jobj={
	userId:userId,
	district:district,
	category:category,
	fromDate:fromDate,
	toDate:toDate,
	memberType:"",
	partyMember:partyMember,
	qualification:qualification,
	taskManager:taskManager,
	searchOpt:searchOpt,
	searchVal:searchVal,
	status:status,
	parliamentId:parliamentId,
	startIndex:prevClkIndx,
	maxIndex:25,
	type:type
	}
	$.ajax({
          type:'POST',
          url: 'complaintsSearchAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			  
				if(type=='excel')
				{
					$("#processingImg1").hide();
					buildSearchResult1(result);
				}
				
				else
				{
					buildPieChart(result);
			  }
		  });

}
function buildPieChart(result){

//$("#totalCntId").html('0');
$('#donutchart').html('');
if(result == null || result.length == 0)
return;

	var dataArr = [];
	var dataArr1 = [];
	var colorsArr =new Array();
	for(var i in result)
	{
		dataArr = new Array();
		colorsArr.push(result[i].color);
		dataArr.push(result[i].status, result[i].count);
		dataArr1.push(dataArr);
	}
	
	Highcharts.setOptions({
        colors: colorsArr
    });
	$('#donutchart').highcharts({
       chart: {
            type: 'pie',
			backgroundColor: 'transparent',
            options3d: {
                enabled: false,
                alpha: 45
            }
        },	
		plotOptions: {	
			pie: {
                innerSize: 65,
                depth: 60,
				showInLegend: true,
				dataLabels: {
                    enabled: false,
				}
            }, 
		},
		legend: {
			itemStyle: {
               fontWeight: 'normal',
				'font-family':'roboto',
				'font-size':'13px'
            },
			enabled: true,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
			useHTML: true,
			
			labelFormatter: function() {
				return this.name + ' ' + this.y + '';
			}
		},
		
		series:[{
            name : 'Count',
            data: dataArr1
        }]
	});
     
}




function buildSearchResult1(result){
	var str='';
	str+='<table class="table table-hover table-custom" id="tableIdForExcel">';
    str+='<thead>';
    str+='<th>Complaint Id</th>';
    str+='<th>Name</th>';
    str+='<th>Mobile</th>';
	str+='<th>Subject</th>';
    str+='<th>Village</th>';
    str+='<th>Mandal</th>';
    str+='<th>Constituency</th>';
    str+='<th>District</th>';
    str+='</thead>';
   
	for(var i in result){
		str+='<tr>';
        str+='<td>'+result[i].complaintId+'</td>';      
        str+='<td>'+result[i].name+'</td>';
		str+='<td>'+result[i].mobile+'</td>';
		str+='<td>'+result[i].subject+'</td>';
        str+='<td>'+result[i].villageName+'</td>';
		str+='<td>'+result[i].mandal+'</td>';		
        str+='<td>'+result[i].constituency+'</td>';
        str+='<td>'+result[i].location+'</td>';
        str+='</tr>';
	}
    str+='</table>';

	$("#resultDivIdForExcel").html(str);
	generateExcel(result);
}
function updateRemarks()
{
	$("#remarksErrorDiv").html("");
	var remarks = $("#infoCenterRemarksId").val().trim();
	if(remarks.length == 0){
		$("#remarksErrorDiv").html("Remarks is Required.").css("color","red");
		return;
	}
 	var isLang = "0";
	$.ajax({
		type : "POST",
		url  : "updateRemarksAction.action",
		data : {complaintId:complaintIdVar,remarks:remarks,isLang:isLang}
		
	}).done(function(result){
		if(result == "success" || result == "updated"){
			$("#editRemarksId").html(""+remarks+"").css("margin-right","7px");
		 $("#remarksErrorDiv").html("Remarks Saved Successfully.").css("color","green");
		 setTimeout(function(){$("#remarksErrorDiv").html("")}, 2000);
              getConversationDetailsByComplaint(complaintIdVar,null);
		}
		else if(result == "noAccess")
		{
			$("#remarksErrorDiv").html("You Didn't Have Access To Add Remarks").css("color","red");
			 setTimeout(function(){$("#remarksErrorDiv").html("")}, 2000);
		  return;	
		}
		else{
			$("#remarksErrorDiv").html("Error Occured Try Again!...").css("color","red");
		}
		
	});
}


function generateExcel(result)
{
	
	$("#autoExport").html('<a id="exportBtn" href="ComplaintExcelReports/'+result[0].updatedTime+'" onClick="clickEve(\''+result[0].updatedTime+'\');" download  target_blank></a>');
	$( "#exportBtn").trigger('click');
}
$(".applyBtn").click(function(){
	searchComplaintByCategory(0);
});
function clickEve(path)
{
window.location.href = "ComplaintExcelReports/"+path+"";
}
function getComplaintsDashBoard(tempVar)
{
	
	$("#getComplaintExcelDataId").css("display","block");
	$("#excelDataSpanId").css("display","none");
	
	//stateId = 0;
		var pTType = 0;
		var issueType1 =issueType;
	if(issueType == "1" || issueType == "2") // Mode Value
	{
	pTType = issueType;
	issueType1 = '';
	}
	
  var jobj = {
	userId:userId,
	category : category,
    issueType:issueType1,
	cmpStatus:cmpStatus,
	constName:constName,
	dist     :dist,
	stateId    :stateId,
	fromDate :fromDate,
	toDate   :toDate,
	deptName  :deptName,
	pTType    : pTType,
         task:""
	}
	$.ajax({
          type:'POST',
          url: 'getComplaintsDashBoardAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){ 
		 
		 if(tempVar == 'table'){
			 $("#resultDivId").html("");
			if(result != null && result.length > 0){
				//buildSearchResult(result,0);
				buildComplaintResults(result);
			}
		 }
		 else{
			buildSearchResult1(result);
		 }
		  });
}


function getComplaintsVoterCadreWise(tempVar)
{
	
	$("#getComplaintExcelDataId").css("display","block");
	$("#excelDataSpanId").css("display","none");
	var jobj = {
		cadreType : cadreType,
		fromDate  : fromDate,
		toDate    : toDate,
		dist      : dist,
		constName : constName,
		stateId	  : stateId,
		userId    : "1450390",
		task      : "",
       
	}
	$.ajax({
		type     : "POST",
		url      :  "getComplaintsVoterCadreWiseAction.action",
		dataType : "json",
		data : {task:JSON.stringify(jobj)},
			
		
	}).done(function(result){
		if(tempVar == 'table'){
			$("#resultDivId").html("");
			if(result != null && result.length > 0){
				//buildSearchResult(result,0);
				buildComplaintResults(result);
			}
		}
		else{
			buildSearchResult1(result);
		}
		
	});
}

function getComplaintsDistWise(tempVar)
{
	$("#getComplaintExcelDataId").css("display","block");
	$("#excelDataSpanId").css("display","none");
	if(dist == 'Andhra Pradesh')
		dist = 1;
	if(dist == 'Telangana')
		dist = 2;
	var pMode = 0;
	if(locationType == "state")
		pMode = 3;
	else if(locationType == "district")
		pMode = 1;
	else if(locationType == "constituency")
		pMode = 4;
	else if(locationType == "dept")
	{
		pMode = 2;
		dist = dist.replace("~", "&");
	}
	var jobj = {
			p_disid   : dist,
			cmpStatus : cmpStatus,
			fromDate  : fromDate,
			toDate    : toDate,
			pMode     : pMode,
			stateId   : stateId,
			constName : constName,
			task      : "getComplaintsDistWise"
	}
	$.ajax({
		type       : "POST",
		url        : "getComplaintsDistWiseAction.action",
		dataType   : "json",
		data       : {task:JSON.stringify(jobj)},
		
	}).done(function(result){
		
		if(tempVar == 'table'){
			$("#resultDivId").html("");
			if(result != null && result.length > 0){
					//buildSearchResult(result,0);
					buildComplaintResults(result);
				}
		}
		else{
			buildSearchResult1(result);
		}
		
	});
}

function buildComplaintResults(result)
{
	
	var str = '';
	str += '<div id="errorDivId"></div>';
	str+='<table class="table table-hover table-custom" id="complaintTableId">';
    str+='<thead >';
    str+='<th style="border-top:1px solid #ccc">Complaint ID</th>';
    str+='<th>Subject</th>';
    str+='<th>Name</th>';
    str+='<th>Task Manager</th>';
    str+='<th>Is Party Member?</th>';
    str+='<th>Status</th>';
    str+='<th>Date</th>';
    str+='<th>Remarks</th>';
    str+='</thead>';
   
	for(var i in result){
		str+='<tr>';
        str+='<td><a style="cursor:pointer;" onClick="getConversationDetails('+result[i].complaintId+');">'+result[i].complaintId+'</a></td>';
        str+='<td >'+result[i].subject+'</td>';
        str+='<td>';
		<c:if test="${not fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint') || not fn:contains(sessionScope.USER.entitlements, 'EditComplaint')}">
		str+='N :'+result[i].name+'<br/>';
		</c:if>
		<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CreateEditComplaint') || fn:contains(sessionScope.USER.entitlements, 'EditComplaint') }" >
		str+='<a href="editComplaintAction.action?complaintId='+result[i].complaintId+'" TARGET="_blank">N :'+result[i].name+'</a><br/>';
		</c:if>
		//str+='Mobile :'+result[i].mobile+'<br/>';
        str+='D :'+result[i].location+'<br/>';
        str+='C :'+result[i].constituency+'<br/>';
        str+='M :'+result[i].mandal+'<br/>';
        str+='V :'+result[i].villageName+'</td>';
        str+='<td><p data-toggle="modal" data-target="#myModal" onClick="getTaskMgrsWithCounts('+result[i].complaintId+');" class="cursor-point" >Assign Here</p></td>';
        str+='<td><select class="form-control" id="typeSelectId" onChange="updateDetails('+result[i].complaintId+'\,\'updateType\');">';
		if(result[i].memberType == 'Cadre')
			str+='<option value="0">Select</option><option selected value="Cadre">cadre</option><option value="Voter">voter</option><option value="Other Party">Other Party</option>';
		else if(result[i].memberType == 'Voter')
			str+='<option value="0">Select</option><option  value="Cadre">cadre</option><option selected value="Voter">voter</option><option value="Other Party">Other Party</option>';
		else if(result[i].memberType == 'Other Party')
			str+='<option value="0">Select</option><option  value="Cadre">cadre</option><option  value="Voter">voter</option><option value="Other Party" selected>Other Party</option>';
        else
			str+='<option selected value="0">Select</option> <option  value="Cadre">cadre</option><option  value="Voter">voter</option><option value="Other Party" >Other Party</option>';
			str+='</select></td>';
		str+='<td><select class="form-control" id="statusSelectId" onChange="updateDetails('+result[i].complaintId+'\,\'updateStatus\');">';
		if(result[i].status.toLowerCase() == ('Not Verified').toLowerCase())
			str+='<option selected value="Not Verified">Not Verified</option><option value="In Progress">IN Progress</option><option value="Completed">Completed</option><option value="Not Eligible">Not Eligible</option><option value="Not Possible">Not Possible</option></select>';
		else if(result[i].status.toLowerCase() == ('Completed').toLowerCase())
			str+='<option value="Not Verified">Not Verified</option><option value="In Progress">IN Progress</option><option  selected value="Completed">Completed</option><option value="Not Eligible">Not Eligible</option><option value="Not Possible">Not Possible</option></select>';
		else if(result[i].status.toLowerCase() == ('In Progress').toLowerCase())
			str+='<option  value="Not Verified">Not Verified</option><option selected value="In Progress">IN Progress</option><option value="Completed">Completed</option><option value="Not Eligible">Not Eligible</option><option value="Not Possible">Not Possible</option></select>';
        else if(result[i].status.toLowerCase() == ('Not Eligible').toLowerCase())
			str+='<option value="Not Verified">Not Verified</option><option value="In Progress">IN Progress</option><option value="Completed">Completed</option><option selected value="Not Eligible">Not Eligible</option><option value="Not Possible">Not Possible</option></select>';
		else if(result[i].status.toLowerCase() == ('Not possible').toLowerCase())
			str+='<option  value="Not Verified">Not Verified</option><option value="In Progress">IN Progress</option><option value="Completed">Completed</option><option selected value="Not Eligible">Not Eligible</option><option selected value="Not Possible">Not Possible</option></select>';	
		else{
			str+='<option selected value="0">Select</option> <option value="Not Verified">Not Verified</option><option value="In Progress">IN Progress</option><option value="Completed">Completed</option><option value="Not Eligible">Not Eligible</option><option value="Not Possible">Not Possible</option></select>';
		}		
		str+='</select></td>';
        //str+='<td>'+result[i].status+'</td>';
        str+='<td>'+result[i].raisedDate+'</td>';
        str+='<td>Remarks</td>';
        str+='</tr>';
	}
        str+='</table>';
		$("#resultDivId").html(str);	
		$("#complaintTableId").dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]],
			"aSearching": false,
			
      });
	
}
 </script><script>
function getComplaintExcelData()
{
	if(complaintType == "voter"){
	    getComplaintsVoterCadreWise('excel');
	}
	else if(complaintType == "issueType"){
	  getComplaintsDashBoard('excel');
	}
	else if(complaintType == "area"){
	  getComplaintsDistWise('excel')
	}
	
}

getAllTaskManagers();
getDistrictsByState(0);
//getQualificationList();
function callFun()
{
  if(complaintType == "issueType"){
	 getComplaintsDashBoard('table');
   }
	else if(complaintType == "voter"){
	 getComplaintsVoterCadreWise('table');
   }
	else if(complaintType == "area"){
	 getComplaintsDistWise('table');
   }
	else{
     searchComplaintByCategory(0);
  }

}
callFun();
$("#applyBtn").click(function(){

	var startDay = $(".start-day").text();
	var endDay = $(".end-day").text()
	
	$("#dateId").html(startDay +"  TO  "+ endDay);
	searchComplaintByCategory(0);
});
var complaintIdVar = 0;
var profileImg = 0;

function editDetails(complaintId){

profileImg = 0;
	$('#content-section').addClass('disnone');
	$('#profile').removeClass('animated slideOutLeft disnone');
	setTimeout(function(){
	   $('#profile').removeClass('disnone');
	   $('.profile-info').addClass('animated slideInLeft');
	},1);
	setTimeout(function(){
	  $('.profile-image').addClass('animated zoomIn');
	},500);
	   $('.profile-info').removeClass('animated slideOutLeft');
	   $('#content-animate').removeClass('animated slideInUp');
	   complaintIdVar = complaintId;
	getConversationDetailsByComplaint(complaintIdVar,null);
	 <c:if test="${sessionScope.USER.userType != 'verification mgr'}">
	getTotalComplaintsForAUser(complaintIdVar);
	</c:if>
}
function getConversationDetailsByComplaint(complaintId,temp){

	getAllStatusDetailsForComplaint(complaintId);
	complaintIdVar = complaintId;
$("#complaintNumDiv").html('');
$("#processingImg").show();
	clearConversations(temp);
	var jobj = {
		complaintId:complaintId
	}
	$.ajax({
          type:'POST',
          url: 'getComplaintConversationAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			  $("#processingImg").hide();
			buildConversationDetails(result,jobj);
		  });
       
}
function searchComplaints()
{
var compId = $("#editsearchComplaintId").val().trim();
if(compId.length > 0 || compId > 0)
{

getConversationDetailsByComplaint(compId,null);
 <c:if test="${sessionScope.USER.userType != 'verification mgr'}">
getTotalComplaintsForAUser(compId);
</c:if>
getUserBasicDetailsByComplaintId(compId);
}


}
/* function selectedElementDiv(selectedElement){
	$(selectedElement).css("background","#fff");
} */
 $(document).on('click','.inbox-messages li', function() {
    var $this =  $(this);
    $this.closest('.inbox-messages').find('li').not($this.addClass('active icondisplay')).removeClass('active icondisplay');
});

 </script><script>
/*function buildConversationDetails(result)
{
$("#commentsDiv").show();
var str ='';
str+='<div id="probSubjectDiv"><h4 class="m_0">'+result.subject+'</h4>';
str+='<p class="m_0 m_top10"><i class="glyphicon glyphicon-calendar"></i> As On : '+result.raisedDate+'</p>';
if(result.scanCopy != null && result.scanCopy !='')
str+='<p class="m_0"><i class="glyphicon glyphicon-paperclip"></i> Scancopy :<a target="_blank" href="http://mytdp.com/Grievance/complaintScannedCopy/'+result.scanCopy+'">'+result.scanCopy+'</a></p></div>';
//str+='<img src="http://mytdp.com/Grievance/complaintScannedCopy/'+result.scanCopy+'/> </p>';

str+='<div id="probDesMainDiv"><p class="m_0 text-italic m_top10">Problem Description</p>';
if(result.subList.length > 3){
str += '<div id="problemDescDiv"  class="problemDescDivCls">';
}else{
str += '<div id="problemDescDiv">';
}
for(var i in result.subList)
{
  str+='<p class="m_0">'+result.subList[i].subject+'</p>';
  str+='<p><b>Responded by:</b> '+result.subList[i].name+' on  '+result.subList[i].raisedDate+'</p>'; 
}
str += '</div></div>';
str += '<div id="remarksMainDiv"><div id="remarksErrorDiv"></div>';
if(result.remarks == null || result.remarks == "")
{
str+='<p class="m_0 text-italic m_top10">Remarks</p>';
str+='<p class="m_0"><span id="editRemarksId">No Remarks </span><span class="text-danger edit-text">Edit</span></p>';
str+='<textarea class="form-control edit-input text-ar" type="text" id="infoCenterRemarksId"></textarea>';
}
else{
str+='<p class="m_0 text-italic m_top10">Remarks</p>';
str+='<p class="m_0"><span id="editRemarksId">&nbsp;'+result.remarks +' </span><span class="text-danger edit-text">Edit</span></p>';
str+='<textarea class="form-control edit-input text-ar" type="text" id="infoCenterRemarksId">'+result.remarks+' </textarea>';
}
str+='<button class="btn btn-danger edit-input btn-sm text-ar-btn m_top5" onclick="updateRemarks()">Submit</button></div>';
$("#conversationDiv").html(str);
var str1='';
str1+='<div class="m_0">Complaint Status : <span class="text-diff">'+result.status+'</span>';
str1+='<span class="compalaint-edit pull-right" ><i class="glyphicon glyphicon-edit" onclick="complaintEditClick();"></i></span></div>';
str1+='<p class="m_0">Type Of Issue : '+result.typeOfIssue+'</p>';
if(result.issueType == 'Select' || result.issueType == '')
str1+='<p class="m_0">Category : - </p>';
else
str1+='<p class="m_0">Category : '+result.issueType+'</p>';
if(result.assignedTo == '')
str1+='<p class="m_0">Assigned to : - </p>';
else
str1+='<p class="m_0">Assigned to : '+result.assignedTo+'</p>';
$("#complaintstatusDiv").html(str1);

var str2 ='';
str2+='<option value="0">Select</option>';
for(var i in tskmgr)
str2+='<option value="'+tskmgr[i].id+'">'+tskmgr[i].name+'</option>';
$("#assignedTo").html(str2);
$("#assignedTo").dropkick('refresh');
<!-- if(profileImg == 0)
setProfileImg(result.image);
profileImg = 1; -->

if(result.status != null && result.status != ''){
   $("#statusSelect").val(""+result.status.toLowerCase()+"").dropkick("refresh");
}
if(result.partyMember != null && result.partyMember != ''){
  $("#partyMemberSelect").val(""+result.partyMember.toLowerCase()+"").dropkick("refresh");
}
if(result.assignedTo != null && result.assignedTo != ''){
	$("#assignedTo option").each(function() {
		if($(this).text() == ''+result.assignedTo+'') {
			$(this).attr('selected', 'selected');
			$('#assignedTo').dropkick('refresh');
		}                        
	});
 
} 


}*/
function setProfile(name,district,consti,mandal,village,isPartyMember)
{
$("#profileDiv").html('');
var str='';
str+='<ul class="media-list profile-ul m_0" >';
str+='<li>N : '+name+'</li>';
str+='<li>D : '+district+'</li>';
str+='<li>C : '+consti+'</li>';
str+=' <li>M : '+mandal+'</li>';
str+=' <li>V : '+village+'</li>';
str+=' <li>Voter/Cader : '+isPartyMember+'</li>';
str+='</ul>';
$("#profileDiv").html(str);

}
function setProfileImg(result)
{

 var str3 = '';
 if(result.voterId != null)
 {
 str3+='<a href="#">';
 str3+='<img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="http://mytdp.com/voter_images/'+result.scanCopy+'/Part'+result.status+'/'+result.voterId +'.jpg" onerror="setDefaultImage(this);" alt="Profile Image">';
 str3+='</a>';
 }
 else{
 str3+='<a href="#">';
 str3+='<img style="height:50px;width:50px;" class="media-object img-border profile-image img-circle" src="http://mytdp.com/images/cadre_images/'+result.image+'" onerror="setDefaultImage(this);" alt="Profile Image">';
 str3+='</a>';
 }
 $("#profileImgDiv").html(str3);
}
 function setDefaultImage(img){
	  img.src = "dist/img/profileimage.png";
   }
function getTotalComplaintsForAUser(complaintId)
{
	$("#complaintCountDiv").html('');
	$("#complaintsDiv").html('');
	$.ajax({
		type : "POST",
		url  : "getTotalComplaintsForAUserAction.action",
		data : {complaintId:complaintId}
	}).done(function(result){
		if(result != null){
		  buildTotalComplaints(result,complaintId);
		}
		else{
			$("#complaintCountDiv").html('No Data Available.');
			$("#complaintsDiv").html('No Data Available.');
		}
	});
}
function buildTotalComplaints(result,complaintId)
{

	var str = '';
	str += '<ul class="list-inline" style="border-top:1px solid #ccc;margin-top:5px">';
	str += '<li>';
	str += '<h1 class="m_0 text-center" style="font-size:50px;color:#666">'+result[0].count+'</h1>';
	str += '<h6 class="m_0">TOTAL COMPLAINTS</h6>';
	str += '</li>';
	
	str += '<li style="margin-top:5px">';
	str += '<ul class="display-style pull-right graph-list count-list">';
	for(var i in result[0].subList){
		
	
		if(result[0].subList[i].status.toLowerCase() == ("In Progress").toLowerCase()){
		 str += '<li><span class="inProgress"></span><span class="inProgress-text">'+result[0].subList[i].status+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		}
		else if(result[0].subList[i].status.toLowerCase() == ("Completed").toLowerCase()){
		 str += '<li style="color:#00B17D;"><span class="completed"></span><span>'+result[0].subList[i].status+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		}
		else if(result[0].subList[i].status.toLowerCase() == ("Not Verified").toLowerCase()){
		 str += '<li><span class="notverified"></span><span class="notverified-text">'+result[0].subList[i].status+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		}
		else if(result[0].subList[i].status.toLowerCase() == ("Not Eligible").toLowerCase()){
		 str += '<li><span class="notEligible"></span><span class="notEligible-text">'+result[0].subList[i].status+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		}
		else if(result[0].subList[i].status.toLowerCase() == ("Not possible").toLowerCase()){
		 str += '<li><span class="notpossible"></span><span class="notpossible-text">'+result[0].subList[i].status+'<span class="pull-right">'+result[0].subList[i].count+'</span></span></li>';
		} 
		
		
	}
	str += '</ul>';
	str += '</li>';
	str += '</ul>';
	$("#complaintCountDiv").html(str);
	
	var comp = '';
	comp += '<ul class="inbox-messages row" style="margin-bottom:0px;">';
	for(var j in result){
		if(result[j].complaintId == complaintId){
			if(result[j].status.toLowerCase() == ("Not Verified").toLowerCase()){
			  comp += '<li class="inbox-not-opened"';
			}
			else if(result[j].status.toLowerCase() == ("Completed").toLowerCase()){
			  comp += '<li class="inbox-completed"';
			}
			else if(result[j].status.toLowerCase() == ("In Progress").toLowerCase()){
			  comp += '<li class="inbox-in-process"';
			}
			else if(result[j].status.toLowerCase() == ("Not Eligible").toLowerCase()){
			  comp += '<li class="inbox-not-eligible"';
			}
			else if(result[j].status.toLowerCase() == ("Not possible").toLowerCase()){
			  comp += '<li class="inbox-not-possible"';
			}
			
			comp += ' onclick="getConversationDetailsByComplaint('+result[j].complaintId+',null);getUserBasicDetailsByComplaintId('+result[j].complaintId+')">';
			//comp += '<li class="inbox-not-opened">';
			comp += '<p class="m_0">C ID - '+result[j].complaintId+'</p>';
			comp += '<p class="m_0">'+result[j].subject+'</p>';
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[j].status+'</span></p>';
			if(result[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[j].raisedDate+'</p>';
			comp += '</li>';
		}
   }
   for(var j in result){
		if(result[j].complaintId != complaintId){
			if(result[j].status.toLowerCase() == ("Not Verified").toLowerCase()){
			  comp += '<li class="inbox-not-opened"';
			}
			else if(result[j].status.toLowerCase() == ("Completed").toLowerCase()){
			  comp += '<li class="inbox-completed"';
			}
			else if(result[j].status.toLowerCase() == ("In Progress").toLowerCase()){
			  comp += '<li class="inbox-in-process"';
			}
			else if(result[j].status.toLowerCase() == ("Not Eligible").toLowerCase()){
			  comp += '<li class="inbox-not-eligible"';
			}
			else if(result[j].status.toLowerCase() == ("Not possible").toLowerCase()){
			  comp += '<li class="inbox-not-possible"';
			}
			
			comp += ' onclick="getConversationDetailsByComplaint('+result[j].complaintId+',null);getUserBasicDetailsByComplaintId('+result[j].complaintId+');">';
			comp += '<p class="m_0">C ID - '+result[j].complaintId+'</p>';
			comp += '<p class="m_0">'+result[j].subject+'</p>';
			
			comp += '<p class="m_0">Status - <span class="textTransFormCls">'+result[j].status+'</span></p>';
			if(result[j].raisedDate != null)
			 comp += '<p class="m_0">'+result[j].raisedDate+'</p>';
			comp += '</li>';
		}
   }
   
	comp += '</ul>';
    $("#complaintsDiv").html(comp);
		if(result[0].count>=7){
			
			   $("#complaintsDiv").css("height","760px");
			 }else {
			  
			    $("#complaintsDiv").css("height","auto"); 
		   }
	
}
function updateComments(){
	var commentsText = $("#commentsTextId").val();
	var jobj = {
			complaintId:complaintIdVar,
			userId:userId,
			text:commentsText,
			task:"updateComments"
	}
	$.ajax({
		type       : "POST",
		url        : "updateCommentsAction.action",
		dataType   : "json",
		data       : {task:JSON.stringify(jobj)},
	}).done(function(result){
	if(result == "success");
		$("#commentsErrorDiv").html("Comment Submitted Successfully").css('color','green').show().delay(2000).fadeOut(400);
		$("#commentsTextId").val('');
		getConversationDetailsByComplaint(complaintIdVar,"comments");
		$('.com-ar,.com-ar-btn').hide();
	});
}
function clearConversations(temp){

if(temp != "comments"){
	
	$("#probSubjectDiv").html("");
    $("#remarksMainDiv").hide();
	$("#complaintstatusDiv").hide();
	//$("#scanCopyDiv").hide();
	$("#complaintNumDiv").html('');
	
}
	$("#scanCopyDiv").html('');
	$("#remarksInfoDiv").html("");
	$("#probDesMainDiv").html("");
	$('.complaint-status').hide();
	$('.complaint-status1').hide();
	$('.com-ar,.com-ar-btn').hide();
	$("#commentsDiv").hide();
}
function clearAllSelFields(){
	$(".taskManagerRadio,.district,.searchCls,.parlRadioChk").each(function(){
		$(this).prop('checked', false);
	});
	$("#dateId").html('Date : <i class="glyphicon glyphicon-remove"></i>');
	$(".day").each(function(){
		$(this).removeClass("checked");
	});
	$(".start-day").html("...");
	$(".end-day").html("...");
	$(".selected-days").html("");
   searchClick();
   $(".districtRemove, .taskRemove, .categoryRemove, .statusRemove, .partyRemove").html('');
}

function buildConversationDetails(result,jobj)
{

$('.complaint-status').show();
$("#commentsDiv").show();
$("#approvedAmount").val('');
 $("#remarksMainDiv").show();
 $("#complaintNumDiv").html('');
 $("#complaintstatusDiv").show();
 $("#hiddencomplaintId").val('');
var str =''
if(result.typeOfIssue != null)
{
	$("#hiddencomplaintId").val(jobj.complaintId);
str+='<h4 id="complaintNumDiv">COMPLAINT ID - '+jobj.complaintId+'</h4>';

}
else
{
str+='<h4 id="complaintNumDiv">COMPLAINT ID - </h4>';
$("#complaintstatusDiv").hide();
}
if($.trim(result.subject).length > 0){
   str+='<div id="probSubjectDiv"><h4 class="m_0 text-italic">Problem Subject</h4><h4 class="m_1">'+result.subject+'</h4>';
}else{
   str+='<div id="probSubjectDiv"><h4 class="m_0 text-italic">Subject</h4><h4 class="m_1"></h4>';
}

<c:if test="${sessionScope.USER.userType != 'verification mgr'}">
if(result.scanCopyList != null && result.scanCopyList !='')
{
for(var i in result.scanCopyList)
	{
 str+='<p class="m_0 m_top10"><i class="glyphicon glyphicon-calendar"></i> As On : '+result.raisedDate+'<span class="pull-right"><i class="glyphicon glyphicon-paperclip"></i> Scancopy : '+result.scanCopyList[i].path+'</span></p>';
 }
 }
</c:if>
$("#conversationDiv").html(str);
var pdfStr = '';
for(var i in result.scanCopyList)
	{
if(result.scanCopyList[i] != null && result.scanCopyList[i].path !=''){

			var scanCopySpl = result.scanCopyList[i].path.split("."); 
			var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
			
		pdfStr+='<div class="pdf-scroll">';
			if(scanCopyExt =="pdf"){

				pdfStr+='<a class="fancybox mouse-over" href="#inline1'+i+'">';
				if(result.scanCopyList[i].newCopy == true)
				{
				pdfStr+='<object data="complaintScannedCopy'+result.scanCopyList[i].path+'\" type="application/pdf" width="100%" height="300px;"></object>';
				}
				else
				{
				pdfStr+='<object data="complaintScannedCopy/old/'+result.scanCopyList[i].path+'\" type="application/pdf" width="100%" height="300px;"></object>';
				}
				pdfStr+='<div id="inline1'+i+'" style="width:100%;display: none;">';
				if(result.scanCopyList[i].newCopy == true)
				{
				pdfStr+='<object data="complaintScannedCopy'+result.scanCopyList[i].path+'\" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
				}
				else
				{
				pdfStr+='<object data="complaintScannedCopy/old/'+result.scanCopyList[i].path+'\" type="application/pdf" style="cursor:pointer;height:1000px;width:1000px"></object>';
				}
				pdfStr+='</div>';
				
			}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
				
				pdfStr+='<a class="fancybox mouse-over appendclick"  href="#inline2'+i+'">';
				if(result.scanCopyList[i].newCopy == true)
				{
					pdfStr+='<img src="complaintScannedCopy/'+result.scanCopyList[i].path+'\" width="100%" height="300px;" style="margin-top:10px;"/>';
					
				}
				else
				{
				pdfStr+='<img src="complaintScannedCopy/old/'+result.scanCopyList[i].path+'\" width="100%" height="300px;" style="margin-top:10px;"/>';
				}
				pdfStr+='<div id="inline2'+i+'" style="width:100%;display: none;">';
				if(result.scanCopyList[i].newCopy == true)
				{
				pdfStr+='<img src="complaintScannedCopy'+result.scanCopyList[i].path+'\" style="width:1000px;width:1000px"/>';
				}
				else
				{
				pdfStr+='<img src="complaintScannedCopy/old/'+result.scanCopyList[i].path+'\" style="width:1000px;width:1000px"/>';
				}
				pdfStr+='</div>';
			}else if( scanCopyExt =="doc" || scanCopyExt =="docx"){
			pdfStr+='<a class="fancybox mouse-over" href="#inline3'+i+'">';
			if(result.scanCopyList[i].newCopy == true)
				{
				pdfStr+='<b>Click <a href="javascript:{};" onclick="openDoc(\'complaintScannedCopy'+result.scanCopyList[i].path+'\')">Here</a> To View Document</b>';
				
				pdfStr+='<object data="http://docs.google.com/gview?url=http://mytdp.com/Grievance/complaintScannedCopy'+result.scanCopyList[i].path+'\&embedded=true" width="100%" height="300px;" ></object>';
				}
				else
				{
				pdfStr+='<b>Click <a href="javascript:{};" onclick="openDoc(\'complaintScannedCopy/old/'+result.scanCopyList[i].path+'\')">Here</a> To View Document</b>';
				pdfStr+='<div id="inline3'+i+'" style="width:100%;display: none;">';
				pdfStr+='<object data="http://docs.google.com/gview?url=http://mytdp.com/Grievance/complaintScannedCopy/old/'+result.scanCopyList[i].path+'\&embedded=true" width="1000px" height="1000px;" ></object>';
				pdfStr+='</div>';
				}
			}
			else if( scanCopyExt =="xlsx" || scanCopyExt =="xls" || scanCopyExt =="csv"){
		
			if(result.scanCopyList[i].newCopy == true)
				{
				pdfStr+='<b>Click <a href="javascript:{};" onclick="openDoc(\'complaintScannedCopy'+result.scanCopyList[i].path+'\')">Here</a> To View Excel Document</b>';
			
				}
				else
				{
				pdfStr+='<b>Click <a href="javascript:{};" onclick="openDoc(\'complaintScannedCopy/old/'+result.scanCopyList[i].path+'\')">Here</a> To View Document</b>';
				
				}
			}
			}
			
			pdfStr+='</div>';
		}
		
		$("#scanCopyDiv").html(pdfStr);
		
		
		
		
		$('.fancybox').fancybox();
		if(result.description != null)
$("#descriptionId").html(''+result.description+'');
else
$("#descriptionId").html('');
if(result.typeOfIssue != null)
$("#typeOfIssue").html('TYPE OF ISSUE : '+result.typeOfIssue+' ');
else
$("#typeOfIssue").html('TYPE OF ISSUE : ');
if(result.issueType == '' || result.issueType == null)
result.issueType = '-';
$("#category").html('Category : '+result.issueType+' ');
if(result.assignedTo == '' || result.assignedTo == null)
result.assignedTo = '-';
$("#assignedToId").html('Assigned To: '+result.assignedTo+' ');

var str2 ='';
str2+='<option value="0">Select</option>';
for(var i in tskmgr)
str2+='<option value="'+tskmgr[i].id+'">'+tskmgr[i].name+'</option>';
$("#assignedTo").html(str2);

if(result.status != null && result.status != ''){
$("#statusSelect").val(""+result.status.toLowerCase()+"");
}
if(result.partyMember != null && result.partyMember != ''){
  $("#partyMemberSelect").val(""+result.partyMember.toLowerCase()+"");
}
if(result.assignedTo != null && result.assignedTo != ''){
	$("#assignedTo option").each(function() {
		if($(this).text() == ''+result.assignedTo+'') {
			$(this).attr('selected', 'selected');
			//$('#assignedTo').dropkick('refresh');
		}                        
	});
	
	if(result.requestAmount)
	{
	$("#requestedAmountDiv").show();
	$("#approvedamountDiv").show();
	$("#solveStatusDiv").hide();
	$("#requestAmount").val(result.requestedAmount);
	$("#educationalSeatPurpose").hide();
	}
	else{
	$("#requestedAmountDiv").hide();
	$("#approvedamountDiv").hide();
	$("#requestAmount").val('');
	$("3approvedAmount").val('');
	}
	if(result.issueType == 'Educational')
	{
	
	$("#educationSupportForId").html('Support For: '+result.supportFor+' ');
	$("#educationPurposeId").html('Support Purpose: '+result.supportPurpose+' ');
		if(result.supportPurpose == "Seat")
		{
			$("#educationalSeatPurpose").show();
			$("#solveStatusDiv").show();
			$("#seatPurpose").val('Seat');
			$("#approvedamountDiv").hide();
		}
		else{
		$("#approvedamountDiv").show();
		}
		$("#requestedAmountDiv").hide();
	}
} 
var commentStr = '';
/*  commentStr +='<li><textarea id="commentsTextId" class="form-control" type="text"></textarea>';
 
 commentStr +='<button class="btn btn-success m_top10 btn-sm" onClick="updateComments();">Submit</button></li>'; */
for(var i in result.subList)
{
  commentStr+='<li><p>'+result.subList[i].subject+'</p>';
  commentStr+=' <p class="text-bold"> - '+result.subList[i].name+'[  '+result.subList[i].raisedDate+']</p></li>'; 
}
$("#commentInfoDiv").html(commentStr);
 $(".demo-y").mCustomScrollbar();
$(".mCSB_dragger_bar").css("width","8px").css("margin-left","1px");
			$(".mCSB_draggerRail").css("width","8px");
			$(".mCSB_draggerContainer").css("margin-left","8px");
			$(".mCSB_dragger_bar").css("width","8px").css("margin-left","1px");
			$(".mCSB_draggerRail").css("width","8px");
getRemarks();


if(result.approvedAmount != null){
	$("#approvedAmount").val(result.approvedAmount);
}

if(result.issueType == 'Job purpose' || result.issueType == 'Job Purpose' )
{

	$("#showcompanyDetails").show();
}
else
	$("#showcompanyDetails").hide();
}
function openDoc(docmnt){
	 window.open(docmnt);
}
function getRemarks()
{
var jobj = {
		complaintId:complaintIdVar,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'getRemarksAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			 
			buildRemarks(result.subList);
		  });
}
function getRemarksPopup(complaintId)
{
$("#popupContentDiv").html('');
var jobj = {
		complaintId:complaintId,
		task:""
	}
	$.ajax({
          type:'POST',
          url: 'getRemarksAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jobj)},
     	  }).done(function(result){
			 
			buildRemarksForPopup(result.subList);
		  });
}
function buildRemarksForPopup(result)
{
try{
var str = '';


var j=1;
if(result == null || result.length == 0)
str+='No Remarks ';
str+='<ul>';
for(var i in result)
{
j++;
var remark = result[i].remarks;

str+='<li class="arrow_boxleft">';
str+='<span class="verifier-name">R'+j+'</span>';
if(remark.length == 0)
{
if(result[i].updatedTime == null)
str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold"> '+result[i].name+'';
else
str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold">- '+result[i].name+' ( '+result[i].updatedTime+' )</p>';
}
else{
if(result[i].updatedTime == null)
str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold"> '+result[i].name+'';
else
str+='<p class="scroll-li-p">'+remark+' </p><p class="text-bold">- '+result[i].name+' ( '+result[i].updatedTime+' )</p>';
}
str+='</li>';

}
str+='</ul>';
}

catch(err)
{

}
$("#popupContentDiv").html(str);
$(".scroll-li-p").mCustomScrollbar();
$(".remarks-ul-scroll").mCustomScrollbar();

}
function buildRemarks(result)
{
try{
var str = '';

var j=1;

if(result == null || result.length == 0)
	str+='No Remarks ';

for(var i in result)
{
	j++;
	
	str+='<li class="arrow_boxleft">';
	str+='<span class="verifier-name">R'+j+'</span>';

	if(result[i].remarks.length == 0)
	{
		if(result[i].updatedTime == null)
			str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold">- '+result[i].name+'';
		else
		str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold">- '+result[i].name+' ( '+result[i].updatedTime+' )</p>';
	}
	else
	{
		if(result[i].updatedTime == null)
			str+='<p class="scroll-li-p">'+result[i].remarks.trim()+' </p><p class="text-bold">- '+result[i].name+'';
		else
			str+='<p class="scroll-li-p">'+result[i].remarks+' </p><p class="text-bold">- '+result[i].name+' ( '+result[i].updatedTime+' )</p>';
	}
	str+='</li>';

	}
}
catch(err)
{
	console.log(err);
}
$("#remarksInfoDiv").html(str);
$(".scroll-li-p").mCustomScrollbar();
$(".remarks-ul-scroll").mCustomScrollbar();

}
function DisablingBoxes(){
	
	 $('#requestAmount, #approvedAmount, #statusSelect, #partyMemberSelect, #assignedTo, #solveId,#commentsTextId').prop('disabled', true);
	 
}
function updateAll()
{
//DisablingBoxes();
if(!validateFields())
{
return;
}
$("#updatestatusDiv").html('');
if($("#statusSelect").length > 0)
{
var statusSelect = $("#statusSelect").val();
if(statusSelect != 'Select')
updateDetails('updateStatus');
}
if($("#partyMemberSelect").length > 0)
{
var partyMemberSelect = $("#partyMemberSelect").val();
if(partyMemberSelect != 'Select')
updateDetails('updateType');
}
if($("#assignedTo").length > 0)
{
var assignedTo = $("#assignedTo").val();
if(assignedTo != 'Select')
assignTaskManager();
}
var approvedAmount = '';
try{
approvedAmount = $("#approvedAmount").val().trim();
}catch(e){
}
if(approvedAmount !='' && approvedAmount.length > 0 && !isNaN(approvedAmount))
{
updateApprovedAmount();
}
if($("#solveStatusDiv").length > 0)
{
updateSolveStatus();
}
if($("#commentsTextId").length > 0)
{
var comment = $("#commentsTextId").val().trim();
if(comment.length > 0)
updateComments();
}

$("#updatestatusDiv").html('<font color="green">Updated Successfully</font>');
setTimeout(function()
{
$("#updatestatusDiv").html('');
},1000)

//$("#editBtn").css("display","block");
//getConversationDetailsByComplaint(complaintIdVar,null);
}
$(".filter-button-icon").click(function()
{
	 $("#menu-1").show();
	/* if( $(this).find('i').hasClass( "glyphicon-filter" )){
		$("#menu-1").show();
        $(this).html('<span class="closeFilter"><i  class="glyphicon glyphicon-remove " style="color:#fff;"></i></span>');	  
       }else if($(this).find('i').hasClass("glyphicon-remove" )){
		$("#menu-1").hide();
        $(this).html('<i  class="glyphicon glyphicon-filter " style="color:#fff;"></i>');
	} */
	
	 $('#crossAppend').html('<span class="closeFilter pull-right "style="margin-top: -40px;margin-right: 50px;"><i  class="glyphicon glyphicon-remove filters-iconed" style="color:#fff;cursor:pointer;"></i></span>');	
	
});
 $(document).on("click",".closeFilter",function(){
	$("#menu-1").hide('');
});


function validateFields()
{
var flag = true;
 var status = $("#statusSelect").val();
	var partyMember = $("#partyMemberSelect").val();
	var assignedTo = $("#assignedTo").val();
	var approvedAmount = '';
	try{
	if($("#approvedAmount").length > 0)
	approvedAmount = $("#approvedAmount").val().trim();
	}catch(e){
	}
$("#updatestatusDiv").html('');
	
	if(status == 'Select' && partyMember == 'Select' && assignedTo == '0' && approvedAmount.length == 0)
	{
		$("#updatestatusDiv").html('Please Select at least one').css("color","red");
		flag = false;
		return flag;
	}
	return flag;
}
function getUserBasicDetailsByComplaintId(complaintId)
{
	$("#complaintTitleId").html('Complaint Box');
	$("#profileDiv").html('');
	$("#profileImgDiv").html('');
	$.ajax({
		type : "POST",
		data : {complaintId:complaintIdVar},
		url  : "getUserBasicDetailsByComplaintIdAction.action"
	}).done(function(result){
	if(result != null)

		if(result != null)
		  setUserBasicDetails(result)
	  else{
		$("#profileDiv").html(''); 
		$("#profileImgDiv").html('');
	  }
	});
}

function setUserBasicDetails(result)
{
	
	
	$("#complaintTitleId").html(''+result.name+' COMPLAINT DETAILS');
    var str='';
    str+='<ul class="media-list profile-ul m_0" >';
    str+='<li>N : '+result.name+'</li>';
	str+='<li>D : '+result.location+'</li>';
	str+='<li>C : '+result.constituency+'</li>';
	str+=' <li>M : '+result.mandal+'</li>';
	str+=' <li>V : '+result.villageName+'</li>';
	str+=' <li>Voter/Cadre: '+result.partyMember+'</li>';
	if(result.membershipId != '' && result.id != null)
	{
	
	str+=' <li>Membership Number: <a target="_blank" title="Click here to View '+result.name+' Cadre Details " href="http://mytdp.com/cadreDetailsAction.action?memberShipId='+result.membershipId+'&constituencyId='+result.id+' ">'+result.membershipId+'</a></li>';
	}
	else if(result.membershipId != '')
	{
	str+=' <li>Membership Number: <a target="_blank" title="Click here to View '+result.name+' Cadre Details " href="http://mytdp.com/cadreDetailsAction.action?memberShipId='+result.membershipId+' ">'+result.membershipId+'</a></li>';
	}
	
	str+='</ul>';
$("#profileDiv").html(str);

setProfileImg(result);
}
function EnablingBoxes(){
	
	 $('#requestAmount, #approvedAmount, #statusSelect, #partyMemberSelect, #assignedTo, #solveId,#commentsTextId').prop('disabled', false);
	 
}
function showDoneBtn()
{
	EnablingBoxes();
	
	
	$("#doneBtn").css("display","block");
	$("#editBtn").css("display","none");
	var status = $("#statusSelect").val();
	var partyMember = $("#partyMemberSelect").val();
	var assignedTo = $("#assignedTo").val();
	var approvedAmount = '';
	try{
	if($("#approvedAmount").length > 0)
	approvedAmount = $("#approvedAmount").val().trim();
	}catch(e){
	}
	
	$("#updatestatusDiv").html('');
	
	if(status == 'Select' && partyMember == 'Select' && assignedTo == '0' && approvedAmount.length == 0)
	{
		$("#updatestatusDiv").html('Please Select at least one').css("color","red");
		return;
	}
	
}
$(".demo-y").mCustomScrollbar();
$(".categoryDecheight").parent().find(".mCustomScrollBox").addClass("widthAddedDyna2");
$('.widthAddedDyna2').css('height', '260px');

function convertCP2Char ( textString ) {
 try{
if(textString.indexOf("????") > -1)
return "";
  var outputString = '';
  textString = textString.replace(/^\s+/, '');
  if (textString.length == 0) { return ""; }
      textString = textString.replace(/\s+/g, ' ');
  var listArray = textString.split(' ');
  for ( var i = 0; i < listArray.length; i++ ) {
    var n = parseInt(listArray[i], 16);
    if (n <= 0xFFFF) {
      outputString += String.fromCharCode(n);
    } else if (n <= 0x10FFFF){
      n -= 0x10000
      outputString += String.fromCharCode(0xD800 | (n >> 10)) + String.fromCharCode(0xDC00 | (n & 0x3FF));
    } else {
      outputString += 'convertCP2Char error: Code point out of range: '+dec2hex(n);
    }
  }
  }
 catch(err)
  {
  
  }
  return( outputString );
}

$(function () {
    $('body').delegate('.lang', 'click', function () {
			 var t = $(this).val();
        if ((t == 1) || (t == 32)) {
            transliterationControl.disableTransliteration();
            transliterationControl.setLanguagePair(google.elements.transliteration.LanguageCode.ENGLISH, "te")
        }
        else if (t == 0) { transliterationControl.disableTransliteration(); }
        else {
            transliterationControl.enableTransliteration();
            transliterationControl.setLanguagePair(google.elements.transliteration.LanguageCode.ENGLISH, t)
        }
    });
});

 function deleteComplaint(complaintId)
{
	$("#errorDivId").html("");
	var txt;
	var r = confirm("Are U Sure! You Want To Delete the Complaint");
	if (r == true) {
     
	var jobj = {
		complaintId : complaintId,
		userId      : userId,
		task        : ""
	}
	$.ajax({
		type : "POST",
		url  : "deleteComplaintAction.action",
		dataType: 'json',
        data: {task:JSON.stringify(jobj)},
	}).done(function(result){
		if(result != null && result == "Deleted")
		{
			//$("#"+complaintId+"").css("display","none");
			searchComplaintByCategory(0);
			getExcelData('graph');
		}
		else
		{
			var errStr = '<font color="red">Error Occured,Try again</font>';
			$("#errorDivId").html(errStr);
		}
		
	})
  }
}
 
 
 function getCommentsByComplaintId(complaintId)
 {
	 $("#commentsPopupContentDiv").html("");
	 $("#commentsPopupContentDiv").html("<img src='images/search.gif'>");
	 
	 $.ajax({
		 type : "POST",
		 url  : "getCommentsByComplaintIdAction.action",
		 data : {complaintId:complaintId}
		 
	 }).done(function(result){
		 $("#commentsPopupContentDiv").html("");
		 if(result != null && result.length > 0)
		  buildComments(result);
		
	 });
 }
 
 function buildComments(result)
 {
	 var str = '';
	 str += '<ul>';
	 var j = 0;
	 for(var i in result)
	 {
		 j++;
		str+='<li>';
		//str+='<span class="verifier-name">C'+j+'</span>';
		str+='<p class="scroll-li-p">'+result[i].comment.trim()+' </p><p class="text-bold"> '+result[i].name+'';
        str += '</li>';		
	 }
	 str += '</ul>';
	 $("#commentsPopupContentDiv").html(str);
	 $(".scroll-li-p").mCustomScrollbar();
   $(".remarks-ul-scroll").mCustomScrollbar();
 }
 
 function getAllStatusDetailsForComplaint(complaintId)
 {
	var jobj = {
		complaintId : complaintId
	}
	
	$.ajax({
		type : "POST",
		url  : "getAllStatusDetailsForComplaintAction.action",
		dataType: 'json',
        data: {task:JSON.stringify(jobj)},
	}).done(function(result){
		
	});
 }
 

 $('body').on("click",".modelshow",function(event){
                $("#myModal").modal();
            });
			
	$('body').on("click",".commentsModelshow",function(event){
                $("#commentsPopUp").modal();
            });		
			
	
</script>
<script>

var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
 <style>
 #tableId_info,#tableId_filter{display:none;}
.prev, .next {width:40px !important}

#filterCloseImg{width: 13px; height: 12px; margin-left: 12px;}
.textTransFormCls{text-transform: capitalize;}
</style>	
	
</body>
</html>
