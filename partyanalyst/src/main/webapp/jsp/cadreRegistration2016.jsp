<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cadre Registration</title>
<link href="dist/cadreRegistration/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/cadreRegistration/dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/cadreRegistration/dist/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="dist/cadreRegistration/dist/css/animate.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
<link href="dist/cadreRegistration/dist/plugins/scrollNew/scroll.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<!--<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />-->
</head>
<body>
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
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M29.85,153.437c-3.914,2.715-2.088,11.171-7.782,12.105
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
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M230.021,85.561c1.956,3.961,0.511,10.101-2.595,11.673
			c-7.584,0.378-12.468-1.944-15.132-6.485C214.559,85.162,223.231,82.841,230.021,85.561z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M257.256,122.741c-1.416,3.856-6.984,1.107-10.808,0.865
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
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M92.105,144.358c-1.449,3.054-7.294,5.222-11.241,3.026
			c-1.336-1.635-1.303-6.657,0.432-7.781C86.264,138.142,91.055,140.473,92.105,144.358z"/>
	</g>
</g>
<g>
	<g>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M29.85,153.437c-3.914,2.715-2.088,11.171-7.782,12.105
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
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M230.021,85.561c1.956,3.961,0.511,10.101-2.595,11.673
			c-7.584,0.378-12.468-1.944-15.132-6.485C214.559,85.162,223.231,82.841,230.021,85.561z"/>
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M257.256,122.741c-1.416,3.856-6.984,1.107-10.808,0.865
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
		<path class="path" stroke="red" stroke-width="2" fill-rule="evenodd" clip-rule="evenodd" fill="#ECF0F1" d="M92.105,144.358c-1.449,3.054-7.294,5.222-11.241,3.026
			c-1.336-1.635-1.303-6.657,0.432-7.781C86.264,138.142,91.055,140.473,92.105,144.358z"/>
	</g>
</g>
</svg>


<div class="container cadreRegistration hide">
	<div class="row mainBlocks">
    	<div class="col-md-12 col-xs-12 col-sm-12 col-lg-12 m_top30">
        	<hr class="rulerLine"/>
        	<h2><span class="mainHeading">Welcome to cadre registration</span></h2>
        </div>
        <div class="col-md-6 col-xs-12 col-sm-6">
        	<div class="mainBlock new">
            	<div class="row">
                	<div class="col-md-8 col-xs-12 col-sm-6 col-md-offset-3">
                    	<h2 class="text-left text-muted">కొత్త సభ్యత్వం</h2>
                    </div>
                    <div class="col-md-8 col-xs-12 col-sm-6 col-md-offset-3 m_top30">
                    	<h3 class="text-left text-capital text-muted">New Membership</h3>
                        <h6 class="text-muted">Register as a new member using Voter ID</h6>
                    </div>
                	<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 m_top50">
                    	<button class="btn btn-success btn-block btn-lg text-capital registerNew">new registration</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xs-12 col-sm-6">
        	<div class="mainBlock renewal">
            	<div class="row">
                	<div class="col-md-8 col-xs-12 col-sm-6 col-md-offset-3">
                    	<h2 class="text-left text-muted">సభ్యత్వం  పునరుద్ధరణ</h2>
                    </div>
                    <div class="col-md-8 col-xs-12 col-sm-6 col-md-offset-3 m_top30">
                    	<h3 class="text-left text-capital text-muted">Renewal Membership</h3>
                        <h6 class="text-muted">Using existing [2014-2016] membership number</h6>
                    </div>
                	<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 m_top50">
                    	<button class="btn btn-success btn-block btn-lg text-capital renewalBtn">renewal now</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row  hide subBlock">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelRegistration">
				<div class="panel-heading renewalN">
                	<h3 class="text-left text-muted">సభ్యత్వం  పునరుద్ధరణ</h3>
                    <h3 class="text-left text-capital text-muted m_top10">Renewal Membership - <small class="text-capitalize">Using Existing [2014-2016] Membership Number</small></h3>
                </div>
            	<div class="panel-heading new newProfile">
                	<h3 class="text-left text-muted">కొత్త సభ్యత్వం</h3>
                    <h3 class="text-left text-capital text-muted m_top10">New Membership</h3>
                </div>
                <!-- Voter ID Search Block -->
                <div class="panel-body voterIdSearch hide">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="panel-title text-capital">search your voter id</h4>
                        </div>
                    </div>
					
                    <div class="row m_top30">
						<div class="col-md-12 col-xs-12 col-sm-12">
							<div id="errorDivId" style="color:red;"></div>
						</div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>State</label>
                           <span id="statesDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
						  <select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="select">
							<option value="0">Select State</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
						  </select>
						 
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>District</label>
							<span id="districtDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                            <select class="select" id="districtId" class="form-control" onchange="getConstituenciesForDistricts(this.value)">
							<option value="0">Select District</option>
							</select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Constituency</label>
							<span id="constituencyDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                            <select class="select" id="constituencyId" class="form-control" onchange="getMandalCorporationsByConstituency(this.value)">
							<option value="0">Select Constituency</option>
							</select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Mandal</label>
							<span id="mandalDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                            <select class="select" id="mandalList" class="form-control" onchange="getPanchayatWardByMandal(this.value)">
							<option value="0">Select Mandal</option>
							</select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10" id="panchayatTwnId">
                        	<label>Village/Panchayat</label>
							<span id="panchayatDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                           <select class="select" id="panchayatList" class="form-control" onchange="getAllCadreInPanchayat(this.value)">
						   <option value="0">Select Village/Panchayat</option>
							</select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Booth</label>
							<span id="boothDivIdImg" style="display:none;"><img src="images/search.gif"/></span>
                             <select class="select" id="boothsList" class="form-control">
							  <option value="0">Select Booth</option>
							</select>
                        </div>
					</div>
					<div class="row">
						<div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<label>VoterId</label>
                            <input type="text" class="form-control" id="searchVoterId"/>
                        </div>
                       <div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<label>Name</label>
                            <input type="text" class="form-control" id="nameId"/>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<label>Mobile No</label>
                            <input type="text" class="form-control" id="mobileId"/>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6 m_top10">
                        	<label>House Number</label>
                            <input type="text" class="form-control" id="huseNOId"/>
                        </div>
					</div>
					<div class="row">
						<div class="col-md-1 col-xs-12 col-sm-1 m_top30">
							<span id="voterIdBack" class="backBtn"> ← Back</span>
						</div>
                        <div class="col-md-6 col-md-offset-5 col-xs-12 col-sm-6 m_top10 col-sm-offset-5 m_top30">
                        	<button class="btn btn-block btn-success text-capital btn-lg voterSearch" onclick="getNewCadreDetails();">Search voter id</button>
                        </div>
                    </div>
                </div>
                <!-- Voter ID Search Block End-->
                <!-- search Results Block -->
                <div class="panel-body searchResultsBlock hide" >
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12" id="searchVoterDetailsId"></div>
                    	<div id="searchVoterDetailsImgId" style="display:none;"><center><img src="images/search.gif"/></center></div>
						<div class="col-md-1 col-xs-12 col-sm-1 m_top30">
							<span id="searchResultsBack" class="backBtn"> ← Back</span>
						</div>
                        <div class="col-md-4 col-xs-12 col-sm-4 col-md-offset-3 col-sm-offset-1 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital voterIdBtn"  onclick="getSearchByRelativeVoterIdDetails()">relative voter id</button>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-4 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital voterIdBtn" onclick="getSearchByMyVoterIdDetails()">my voter id</button>
                        </div>
                    </div>
					<div id="checkVoterId" style="color:red;"></div>
                </div>
                <!-- search Results Block End -->
                <!-- profile details Block -->
				
             <div class="panel-body profileDetailsBlock hide">
				<form name="submitCadreForm" id="submitCadreForm"  method="post" enctype="multipart/form-data">
                	<div class="row" id="basicDataId1">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="text-capital">profile details</h4>
                        </div>
                        <div class="col-md-8 col-xs-12 col-sm-12 m_top30">
                        	<div class="row">
                            	<div class="col-md-6 col-xs-12 col-sm-6">
                                    <label>Name</label>
                                    <input type="text" class="form-control" id="nameId1" name="cadreRegistrationVO.cadreName"/>
									<input type="hidden" class="form-control" id="hiddenTdpCadreId" name="cadreRegistrationVO.tdpCadreId"/>
									<input type="hidden" class="form-control" id="hiddenVoterId" name="cadreRegistrationVO.voterId"/>
									<input type="hidden" class="form-control" id="hiddenFamilyVoterId" name="cadreRegistrationVO.familyVoterId"/>
									<input type="hidden" class="form-control" id="hiddenConstId" name="cadreRegistrationVO.constituencyId"/>
									<input type="hidden" class="form-control" id="hiddenNewImgExist" name="cadreRegistrationVO.isNewImageExist"/>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-6" style="display:none;" id="teluguNameDivId">
                                    <label>Telugu Name</label>
                                    <input type="text" class="form-control"/>
                                </div>
								<div class="col-md-6 col-xs-12 col-sm-6" style="display:none;" id="cadreMembrSpId">
                                    <label>Existing Membership Number</label>
                                    <input type="text" class="form-control" id="membershipId" name="cadreRegistrationVO.memberShipNo"/>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3 m_top20">
                                    <label>Gender</label>
                                    <select class="select" id="genderId" name="cadreRegistrationVO.gender">
										<option value="0">Select Gender</option>
										<option value="M">Male</option>
										<option value="F">Female</option>
										<option value="O">Others</option>
									</select>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3 m_top20">
                                    <label>Age</label>
                                    <input type="text" class="form-control" id="ageId" name="cadreRegistrationVO.age"/>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-6 m_top20">
                                    <label>Date Of Birth</label>
                                    <input type="text" class="form-control" id="dobId" name="cadreRegistrationVO.dateOfBirth"/>
                                </div>
                            </div>
                        </div>
					<div id="imgErrDivId" style="color:red;"></div>
                        <div class="col-md-4 col-xs-12 col-sm-12 m_top30">
                        	<div class="col-md-6 col-xs-12 col-sm-6">
                            	<div class="imageDiv">
                                	<img src="" class="cadreImage img-responsive" id="existImgId"/>
                                    <label class="checkbox-inline m_top5">
                                    	<input type="checkbox" class="imageCheckBox isImageCheck"  value="1" id="exstCheckImgId" name="option"/>Existing Image
                                    </label>
                                </div>
                            </div>
                     <div class="col-md-6 col-xs-12 col-sm-6">
                            	<div class="imageDiv">
                                	<img src="dist/img/default_image.png" id="uploadImgId" class="cadreImage img-responsive" alt="existing image"/>
                                    <label class="checkbox-inline m_top5">
                                    	<input type="checkbox" class="imageCheckBox isImageCheck" id="newCheckImgId"  value="1" name="option"/><input type="file"   onchange="loadFile(event)" id="upladImgId" name="uploadImage" style="width:80px"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                  	</div>
                 	<div class="row">
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Apartment Name</label>
                            <input type="text" class="form-control" />
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Street Name</label>
                            <input type="text" class="form-control" />
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Area Name</label>
                            <input type="text" class="form-control" id="areaId"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Land Mark</label>
                            <input type="text" class="form-control" id="landMarkId"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Hamlet</label>
                            <input type="text" class="form-control" id="hamletId"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Pincode</label>
                            <input type="text" class="form-control" id="pinCodeId"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label class="control-label" for="inputSuccess2">Mobile Number</label>
                        	<div class="form-group has-feedback formExtra">
                              <input type="text" class="form-control" id="mobileId1" name="cadreRegistrationVO.mobileNumber">
                              <span class="form-control-feedback text-muted" aria-hidden="true">+91</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10" id="emailDivId" style="display:none;">
                        	<label class="control-label" for="inputSuccess2">Email Id</label>
                        	<div class="form-group has-feedback formExtra">
                              <input type="text" class="form-control" id="emailId" name="cadreRegistrationVO.email">
                              <span class="form-control-feedback text-muted" aria-hidden="true">@</span>
                            </div>
                        </div>
						<div class="col-md-4 col-xs-12 col-sm-6 m_top10" id="cadreVoterDivId" style="display:none;">
                        	<label>Voter Id -<i> <span class="text-warning">Voter ID</span></i></label>
                            <input type="text" class="form-control" id="selfVoetrId"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10" id="cadreUpdateVotrDivId" style="display:none;">
                        	<label>Updated Voter Id -<i> <span class="text-danger">Self V.ID</span></i></label>
                            <input type="text" class="form-control" id="updatedVoetrId" />
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10" id="voterDvId" style="display:none;">
                        	<label>Voter Id -<i> <span class="" id="relVotCls">Relative V.ID</span> / <span class="" id="selfVotCls">Self V.ID</span></i></label>
                            <input type="text" class="form-control" id="voterIdText" disabled/>
                        </div>
                     </div>
                     <div class="row">
                     	<div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Aadhar Number</label>
                            <input type="text" class="form-control" id="aadharId" name="cadreRegistrationVO.aadharNo"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Caste</label>
                            <select class="select" id="casteListId" name="cadreRegistrationVO.casteStateId">
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Education</label>
                           <select class="select" id="eductnQualId" name="cadreRegistrationVO.educationId">
                            </select>
                        </div>
                    </div>
					<div class="row" id="cadrePrvNomneDivId">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="text-capital">nominee details<span class="text-capitalize text-warning"><i>Your Previous nominee</i></span></h4>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top20">
                            <label>Nominee Name</label>
                            <input type="text" class="form-control" id="prvNomneNameId" name="cadreRegistrationVO.nomineeName"/>
							<div id="prvNomneNameDivId" style="color:red;"></div>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6 m_top20">
                            <label>Gender</label>
                            <select class="select" id="prvNomneGendrId" name="cadreRegistrationVO.nomineeGender">
								<option value="0">Select Gender</option>
								<option value="M">Male</option>
								<option value="F">Female</option>
								<option value="O">Others</option>
							</select>
                            <!--<input type="text" class="form-control" id="prvNomneGendrId" name="cadreRegistrationVO.preNomineeGender"/>-->
							<div id="prvNomneGendrDivId" style="color:red;"></div>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6 m_top20">
                            <label>Age</label>
                            <input type="text" class="form-control" id="prevNomneAgeId" name="cadreRegistrationVO.nomineeAge"/>
							<div id="prevNomneAgeDivId" style="color:red;"></div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top20">
                            <label>Relative Relationship</label>
                            <select class="select" id="prevNomneReltvId" name="cadreRegistrationVO.nomineeRelationId">
                            </select>
							<div id="prevNomneReltvDivId" style="color:red;"></div>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top30">
                        	<label class="checkbox-inline" id="prevNomineeId">
                            	<input type="checkbox" class="nomineeDetailsCls" id="PrvNomineeDetailsId"/><span id="defaultNomineeId"></span>
                            </label>
                            <span style="margin-left:10px;margin-right:10px;" id="prevNomiConId"><i>/Or/</i></span>
                            <label class="checkbox-inline">
                            	<input type="checkbox" id="changeNomineeId" class="nomineeDetailsCls"/>Change Nominee
                            </label>
							<span style="margin-left:10px;margin-right:10px;"><i>/Or/</i></span>
                            <label class="checkbox-inline">
                            	<input type="checkbox" id="addNewNomineeId" class="nomineeDetailsCls"/>Add New Nominee
                            </label>
                        </div>
                        <!--<div class="col-md-2 col-xs-4 col-xs-offset-4 col-sm-4 col-sm-offset-8 col-md-offset-8 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital">submit</button>
                        </div>-->
                    </div>
					
					<div class="row">
						<!--<div id="addNewNominatedId" style="display:none;">
							<div class="col-md-4 col-xs-12 col-sm-6 m_top20">
								<label>Nominee Name</label>
								<input type="text" class="form-control" id="nomineeNameId" name="cadreRegistrationVO.nomineeName"/>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
								<label>Gender</label>
							<!--<input type="text" class="form-control" id="nomineeGenderId" name="cadreRegistrationVO.nomineeGender"/>-->
								<!--<select class="select" id="nomineeGenderId" name="cadreRegistrationVO.nomineeGender">
									<option value="0">Select Gender</option>
									<option value="M">Male</option>
									<option value="F">Female</option>
									<option value="O">Others</option>
								</select>
							</div>
							<div class="col-md-2 col-xs-12 col-sm-6 m_top20">
								<label>Age</label>
								<input type="text" class="form-control" id="nomineeAgeId" name="cadreRegistrationVO.nomineeAge"/>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-6 m_top20">
								<label>Relative Relationship</label>
								<select class="select" id="relativeId" name="cadreRegistrationVO.nomineeRelationId">
									<!--<option></option>-->
								<!--</select>
							</div>
						</div>-->
						<div class="col-md-1 col-xs-12 col-sm-1 m_top30  hide">
							<span id="searchResultsBackBtn" class="backBtn"> ← Back</span>
						</div>
						<div class="col-md-1 col-xs-12 col-sm-1 m_top30 hide">
							<span id="searchResultsBackBtnR" class="backBtn"> ← Back</span>
						</div>
                        <div class="col-md-2 col-xs-4 col-xs-offset-3 col-sm-4 col-sm-offset-7 col-md-offset-7 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital" type="button" onclick="savingCadreDetails();">submit</button>
                        </div>
                    </div>
					</form>
                </div>
				
                <!-- profile details Block End-->
            </div>
        </div>
    </div>
    <div class="row hide subBlockR">
		<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="panel panel-default panelRegistration">
            	<div class="panel-heading renewal">
                	<h3 class="text-left text-muted">సభ్యత్వం  పునరుద్ధరణ</h3>
                    <h3 class="text-left text-capital text-muted m_top10">Renewal Membership - <small class="text-capitalize">Using Existing [2014-2016] Membership Number</small></h3>
                </div>
                <div class="panel-body selectMembership hide">
                	<div class="row">
                    	<div class="col-md-6 col-xs-12 col-sm-6" style="border-right:1px solid #333;">
                        	<div class="pad_20">
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<label>Please Enter your existing membership number</label>
									<input type="text" class="form-control" id="validateRenMemshipId"/>
								</div>
                            	 <div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<label>Mobile Number</label>
									<input type="text" class="form-control" id="renewalMobileId"/>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<label>Voter ID</label>
									<input type="text" class="form-control" id="renewalVoterId"/>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<button class="btn btn-success btn-block m_top10 text-capital validateNumber" onclick="validateRenewalMemshipDetails()">search cadre</button>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top10">
									<p>Note: <i>If you forgot your membership number search using your Mobile Number, Voter Id</i></p>
								</div>
								<div class="col-md-12 col-xs-12 col-sm-12 m_top30">
									<span id="renewalBackBtn" class="backBtn"> ← Back</span>
								</div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6" id="renewalMembershipId"></div>
                    </div>
                </div>
                <div class="panel-body existingMembershipR hide">
                	<div class="row">
                    	<div class="col-md-6 col-xs-12 col-sm-6 m_top30" style="border-right:1px solid #333;">
                        	<div class="pad_20">
                            	<label>Please Enter your existing membership number</label>
                                <input type="text" class="form-control"/>
                                <button class="btn btn-success btn-block m_top30 text-capital validateNo">validate membership number</button>
                                <p class="m_top50">Note: <i>If you forgot your membership number search using your Mobile Number, Voter Id</i></p>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <div class="panel-body updateProfileR hide">
                	<div class="row">
                    	<div class="col-md-6 col-xs-12 col-sm-6">
                        	<h4 class="panel-title text-capital">selected profile</h4>
                            <img src="dist/img/membership_icon.png" class="img-responsive m_top50" style="margin:auto" alt="membership Icon"/>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                        	<div class="pad_20" >
                            	<div class="row">
                                	<div class="col-md-12 col-xs-12 col-sm-12" id="renwalMembRelativeId"></div>
                                    <div class="col-md-12 col-xs-12 col-sm-12">
                                    	<h4 class="text-capital">your profile showing</h4>
                                        <h4 class="panel-title">registered with relative voter id</h4>
                                    </div>
                                	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                                    	<button class="btn btn-success btn-block text-capital updateId" onclick="getCadreDetailsForRelativeCadre('update')">update your voter id</button>
                                        <div class="m_top20 text-center">/Or/</div>
                                        <button class="btn btn-success btn-block text-capital m_top20 updateId" onclick="getCadreDetailsForRelativeCadre('continue')">continue with relative voter id</button>
                                    </div>
									<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
										<span id="profileBackBtnR" class="backBtn pull-right"> ← Back To Search</span>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Voter ID search Renewal Start-->
                <div class="panel-body voterIdSearchR hide">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="panel-title text-capital">search your voter id</h4>
                        </div>
                    </div>
                    <div class="row m_top30">
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>State</label>
                            <select class="select">
                            	<option>Andhra Pradesh</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>District</label>
                            <select class="select">
                            	<option>Distreict Name</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Constituency</label>
                            <select class="select">
                            	<option>Constituency</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Mandal</label>
                            <select class="select">
                            	<option>Mandal</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Village</label>
                            <select class="select">
                            	<option>Andhra Pradesh</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Booth</label>
                            <select class="select">
                            	<option>Andhra Pradesh</option>
                            </select>
                        </div>
                       <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Name</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Mobile No</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>House Number</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-6 col-md-offset-6 col-xs-12 col-sm-6 m_top10 col-sm-offset-6 m_top30">
                        	<button class="btn btn-block btn-success text-capital btn-lg voterSearchR">serach voter id</button>
                        </div>
                    </div>
                </div>
            	<!-- Voter ID search Renewal End-->
                <!-- search Results Block -->
                <div class="panel-body searchResultsBlockR hide">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="panel-title text-capital">search results</h4>
                        </div>
                    	<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
                        	<ul class="searchResults">
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-7" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-7" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-8" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-8" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-9" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-9" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-10" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-10" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-11" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-11" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-12" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-12" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-13" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-13" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-14" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-14" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-15" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-15" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="dist/img/default_image.png" class="media-object cadreImage" alt="candidateImage"/>
                                        </div>
                                        <div class="media-body">
                                            <h5 class="text-capitalize">Harish Kumar</h5>
                                            <p>S/o: Balaraj</p>
                                            <p>V.ID VD32564789</p>
                                            <p>H.no: 5-6-11/2A &nbsp;&nbsp;|
                                                <span>&nbsp;&nbsp;Gender : M&nbsp;&nbsp;|</span>
                                                <span>&nbsp;&nbsp;Age : 28</span>
                                            </p>
                                            <div class="checkboxAlign">
                                                <input id="checkbox-16" class="checkbox-custom" name="checkbox-1" type="checkbox">
                                                <label for="checkbox-16" class="checkbox-custom-label" style="font-size:13px;font-weight:200;text-transform:uppercase">&nbsp;</label>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-4 col-md-offset-8 col-sm-offset-8 col-xs-12 col-sm-4 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital voterIdBtnR">my voter id</button>
                        </div>
                    </div>
                </div>
                <!-- search Results Block End -->
                <!-- profile details Block -->
                <div class="panel-body profileDetailsBlockR hide">
                	<div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="text-capital">profile details</h4>
                        </div>
                        <div class="col-md-8 col-xs-12 col-sm-12 m_top30">
                        	<div class="row">
                            	<div class="col-md-6 col-xs-12 col-sm-6">
                                    <label>Name</label>
                                    <input type="text" class="form-control" id="profileNameR"/>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-6">
                                    <label>Existing Membership Number</label>
                                    <input type="text" class="form-control" id="membershipNoR"/>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3 m_top20">
                                    <label>Gender</label>
                                    <input type="text" class="form-control" id="profileGenderR"/>
                                </div>
                                <div class="col-md-3 col-xs-12 col-sm-3 m_top20">
                                    <label>Age</label>
                                    <input type="text" class="form-control" id="profileAgeR"/>
                                </div>
                                <div class="col-md-6 col-xs-12 col-sm-6 m_top20">
                                    <label>Date Of Birth</label>
                                    <input type="text" class="form-control" id="profileDobR"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-12 m_top30">
                        	<div class="col-md-6 col-xs-12 col-sm-6">
                            	<div class="imageDiv">
                                	<img src="dist/img/default_image.png" id="profileImageR" class="cadreImage img-responsive" alt="existing image"/>
                                    <label class="checkbox-inline m_top5">
                                    	<input type="checkbox"/>Existing Image
                                    </label>
                                </div>
                            </div>
                            <div class="col-md-6 col-xs-12 col-sm-6">
                            	<div class="imageDiv">
                                	<img src="dist/img/default_image.png" class="cadreImage img-responsive" alt="existing image"/>
                                    <label class="checkbox-inline m_top5">
                                    	<input type="checkbox"/>BROWSE
                                    </label>
                                </div>
                            </div>
                        </div>
                  	</div>
                 	<div class="row">
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Apartment Name</label>
                            <input type="text" class="form-control" id="profileAdd1"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Street Name</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Area Name</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Land Mark</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Hamlet</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Pincode</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label class="control-label" for="inputSuccess2">Mobile Number</label>
                        	<div class="form-group has-feedback formExtra">
                              <input type="text" id="profileMobileR" class="form-control">
                              <span class="form-control-feedback text-muted" aria-hidden="true">+91</span>
                            </div>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Voter Id -<i> <span class="text-warning">Voter ID</span></i></label>
                            <input type="text" class="form-control" id="ownVIDR"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Updated Voter Id -<i> <span class="text-danger">Self V.ID</span></i></label>
                            <input type="text" class="form-control"/>
                        </div>
                     </div>
                     <div class="row">
                     	<div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Aadhar Number</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Caste</label>
                            <select class="select">
                            	<option>SC</option>
                            </select>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top10">
                        	<label>Education</label>
                            <select class="select">
                            	<option>SC</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                    	<div class="col-md-12 col-xs-12 col-sm-12">
                        	<h4 class="text-capital">nominee details<span class="text-capitalize text-warning"><i>Your Previous nominee</i></span></h4>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top20">
                            <label>Nominee Name</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6 m_top20">
                            <label>Gender</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6 m_top20">
                            <label>Age</label>
                            <input type="text" class="form-control"/>
                        </div>
                        <div class="col-md-4 col-xs-12 col-sm-6 m_top20">
                            <label>Relative Relationship</label>
                            <select class="select">
                            	<option>Brother</option>
                            </select>
                        </div>
                        <div class="col-md-12 col-xs-12 col-sm-12 m_top30">
                        	<label class="checkbox-inline">
                            	<input type="checkbox"/>Use Ramesh As Nominee
                            </label>
                            <span ><i>/Or/</i></span>
                            <label class="checkbox-inline">
                            	<input type="checkbox" />Change Nominee
                            </label>
                        </div>
						<div class="col-md-1 col-xs-12 col-sm-1 m_top30">
							<span id="profileBackR" class="backBtn"> ← Back</span>
						</div>
                        <div class="col-md-2 col-xs-4 col-xs-offset-3 col-sm-4 col-sm-offset-7 col-md-offset-7 m_top30">
                        	<button class="btn btn-success btn-block btn-lg text-capital">submit</button>
                        </div>
                    </div>
                </div>
                <!-- profile details Block End-->
            </div>
        </div>
	</div>
</div>
<div class="modal fade" id="nomineeModalId">
  <div class="modal-dialog" role="document" style="width:80%;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Voter Family Details</h4>
      </div>
      <div class="modal-body">
        <div class="row m_top30" style="display:none;" id="familyDetailsDivId">
			<div class="col-md-12 col-xs-12 col-sm-12 m_top20 cadreFamilyDetailsCls" ></div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!--<button type="button" class="btn btn-primary">Add Nominee</button>-->
      </div>
    </div>
  </div>
</div>
<input type="hidden" id="voterId"/>
<input type="hidden" id="tdpCadreId"/>
<input type="hidden" id="statusId"/>
<input type="hidden" id="tdpCdrIdR"/>
<input type="hidden" id="stusIdR"/>
<input type="hidden" id="votrIdR"/>
<input type="hidden" id="hidnFamlyVoterId"/>


<script src="dist/cadreRegistration/dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/cadreRegistration/dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/cadreRegistration/dist/plugins/scrollNew/scroll.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="js/cadreRegistration2016/cadreRegistration2016.js" type="text/javascript"></script>
<script src="js/cadreRegistration2016/NewCadreRegistration2016.js" type="text/javascript"></script>
<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<!--<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>-->		
<script type="text/javascript">
   $("#dobId").datetimepicker({
	   format:'YYYY-MM-DD'
   });
  //GLOBAL VARIABLES
  var casteNamesArray = [];
  var educationsArray = [];
  var relationsArray = [];
   
  onLoadCalls();
  
  
var a = $(window).height();
var b = $(window).width();
$(".page-load-svg").height(a);
$(".page-load-svg").width(b);
setTimeout(function(){
	$(".page-load-svg").remove();
	$(".cadreRegistration").removeClass("hide");
	$(".mainBlocks").addClass("animated fadeIn");
},1000)
setTimeout(function(){
	$(".mainBlocks").removeClass("animated fadeIn");
},1500);
$(".searchResults").mCustomScrollbar({
	setHeight:'300px'
});
$(".renewalSearchResults").mCustomScrollbar({
	setHeight:'300px'
});
$('.select').chosen({width:'100%'});
$(document).on("click",".registerNew",function(){
	$(".mainBlocks").addClass("animated fadeOut");
	setTimeout(function(){
		$(".mainBlocks").addClass("hide");
		$(".subBlock,.voterIdSearch").removeClass("hide");
		$(".subBlock,.voterIdSearch").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".subBlock,.voterIdSearch").removeClass("animated fadeIn");
		$(".mainBlocks").removeClass("animated fadeOut");
	},1000);
});
$(document).on("click","#searchResultsBackBtn",function(){
	$(".profileDetailsBlock").addClass("animated fadeOut");
	setTimeout(function(){
		$(".profileDetailsBlock").addClass("hide");
		$(".searchResultsBlock").removeClass("hide");
		$(".searchResultsBlock").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".searchResultsBlock").removeClass("animated fadeIn");
		$(".profileDetailsBlock").removeClass("animated fadeOut");
	},1000);
});

$(document).on("click","#voterIdBack",function(){
	$(".voterIdSearch").addClass("animated fadeOut");
	setTimeout(function(){
		$(".subBlock,.voterIdSearch").addClass("hide");
		$(".mainBlocks").addClass("animated fadeIn");
		$(".mainBlocks").removeClass("hide");
	},500)
	setTimeout(function(){
		$(".subBlock,.voterIdSearch").removeClass("animated fadeIn");
		$(".mainBlocks").removeClass("animated fadeIn");
	},1000)
});
function searchVoterDetails(){
	$(".voterIdSearch").addClass("animated fadeOut");
	setTimeout(function(){
		$(".voterIdSearch").addClass("hide");
		$(".searchResultsBlock").removeClass("hide");
		$(".searchResultsBlock").addClass("animated fadeIn");		
	},500);
	setTimeout(function(){
		$(".voterIdSearch").removeClass("animated fadeOut");
		$(".searchResultsBlock").removeClass("animated fadeIn");		
	},500);
}
function myVoterButtonDetails(){
	$(".searchResultsBlock").addClass("animated fadeOut");
	setTimeout(function(){
		$(".searchResultsBlock").addClass("hide");
		$(".profileDetailsBlock").removeClass("hide");
		$(".profileDetailsBlock").addClass("animated fadeIn");	
   	},500);
	setTimeout(function(){
		$(".searchResultsBlock").removeClass("animated fadeOut");
		$(".profileDetailsBlock").removeClass("animated fadeIn");	
   	},1000);
}


/* Renewal Click Actions*/
$(document).on("click",".renewalBtn",function(){
	$(".mainBlocks").addClass("animated fadeOut");
	setTimeout(function(){
		$(".mainBlocks").addClass("hide");
		$(".subBlockR,.selectMembership").removeClass("hide");
		$(".subBlockR,.selectMembership").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".mainBlocks").removeClass("animated fadeOut");
		$(".subBlockR,.selectMembership").removeClass("animated fadeIn");
	},1500)
});

$(document).on("click","#profileBackR",function(){
	$(".profileDetailsBlockR").addClass("animated fadeOut");
	setTimeout(function(){
		$(".profileDetailsBlockR").addClass("hide");
		$(".selectMembership").removeClass("hide");
		$(".selectMembership").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".profileDetailsBlockR").removeClass("animated fadeOut");
		$(".selectMembership").removeClass("animated fadeIn");
	},1500)
	/* empty field*/
	$("#profileNameR").val(' ');
	$("#membershipNoR").val(' ');
	$("#profileGenderR").val(' ');
	$("#profileAgeR").val(' ');
	$("#profileDobR").val(' ');
	$("#profileImageR").attr("src","dist/img/default_image.png");
	$("#profileAdd1").val(' ');
	$("#profileMobileR").val(' ');
	$("#ownVIDR").val(' ');
});
$(document).on("click","#profileBackBtnR",function(){
	$(".updateProfileR").addClass("animated fadeOut");
	setTimeout(function(){
		$(".updateProfileR").addClass("hide");
		$(".selectMembership ").removeClass("hide");
		$(".selectMembership ").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".updateProfileR").removeClass("animated fadeOut");
		$(".selectMembership").removeClass("animated fadeIn");
	},1500)
	$("#validateRenMemshipId,#renewalMobileId,#renewalVoterId").val('');
	$("#renewalMembershipId").html(' ');
});

$(document).on("click","#searchResultsBack",function(){
	$(".searchResultsBlock").addClass("animated fadeOut");
	setTimeout(function(){
		$(".searchResultsBlock").addClass("hide");
		$(".voterIdSearch").removeClass("hide");
		$(".voterIdSearch").addClass("animated fadeIn");
	},500);
	setTimeout(function(){
		$(".voterIdSearch").removeClass("animated fadeOut");
		$(".searchResultsBlock").removeClass("animated fadeIn");
	},1000);
	//$("#nameId").
});
$(document).on("click","#renewalBackBtn",function(){
	$(".selectMembership,.subBlockR,.renewalN").addClass("animated fadeOut");
	setTimeout(function(){
		$(".selectMembership,.subBlockR,.renewalN").addClass("hide");
		$(".mainBlocks,.renewal").removeClass("hide");
		$(".mainBlocks,.renewal").addClass("animated fadeIn");
	},500);
	setTimeout(function(){
		$(".selectMembership,.subBlockR,.renewalN").removeClass("animated fadeOut");
		$(".mainBlocks,.renewal").removeClass("animated fadeIn");
	},1000);
	setTimeout(function(){
	$(".selectMembership,.subBlockR,.renewalN").addClass("hide");
	},1600);
});

$(document).on("click",".validateNo",function(){
	$(".existingMembershipR").addClass("animated fadeOut");
	setTimeout(function(){
		$(".existingMembershipR").addClass("hide");
		$(".updateProfileR").removeClass("hide");
		$(".updateProfileR").addClass("animated fadeIn");		
	},500);
	setTimeout(function(){
		$(".existingMembershipR").removeClass("animated fadeOut");
		$(".updateProfileR").removeClass("animated fadeIn");		
	},1000);
	
});
$(document).on("click","#searchResultsBackBtnR",function(){
	$(".profileDetailsBlock,.subBlockR").addClass("animated fadeOut");
	
	setTimeout(function(){
		$(".selectMembership,.subBlockR").removeClass("hide");
		$(".profileDetailsBlock").addClass("hide");
		$(".selectMembership").addClass("animated fadeIn");		
	},500);
	setTimeout(function(){
		$(".profileDetailsBlock,.subBlockR").removeClass("animated fadeOut");
		$(".selectMembership").removeClass("animated fadeIn");		
	},1500);
});

$(document).on("click",".voterSearchR",function(){
	$(".voterIdSearch").addClass("animated fadeOut");
	setTimeout(function(){
		$(".voterIdSearchR").addClass("hide");
		$(".searchResultsBlockR").removeClass("hide");
		$(".searchResultsBlockR").addClass("animated fadeIn");		
	},500);
});
$(document).on("click",".updateId",function(){
	$(".updateProfileR").addClass("animated fadeOut");
	setTimeout(function(){
		$(".updateProfileR,.renewal,.subBlockR,.newProfile").addClass("hide");
		$(".profileDetailsBlock,.subBlock,.renewalN").removeClass("hide");
		$(".profileDetailsBlock").addClass("animated fadeIn");
	},500)
	setTimeout(function(){
		$(".updateProfileR").removeClass("animated fadeOut");
		$(".profileDetailsBlock").removeClass("animated fadeIn");
	},1500)
	/*$(".updateProfileR").addClass("animated fadeOut");
	setTimeout(function(){
		$(".updateProfileR").addClass("hide");
		$(".profileDetailsBlockR").removeClass("hide");
		$(".profileDetailsBlockR").addClass("animated fadeIn");		
	},500);*/
});

$('.imageDiv input:checkbox').click(function() {
    $('.imageDiv input:checkbox').not(this).prop('checked', false);
}); 

 var loadFile = function(event) {
    var imagePath = document.getElementById('uploadImgId');
    imagePath.src = URL.createObjectURL(event.target.files[0]);
  };
</script>
</body>
</html>