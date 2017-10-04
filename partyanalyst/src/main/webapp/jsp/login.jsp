<html>
<head>
<title>TDP Party's Election Analysis &amp; Management Platform</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="Assets/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://use.fontawesome.com/e94c241642.js"></script>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/loginpopup1.js"> </script>
<script src="Assets/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="Assets/js/bootstrap.js" type="text/javascript"></script>
	
<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<!-- YUI Dependency files (End) -->
	<style>
@import url('https://fonts.googleapis.com/css?family=Montserrat:400,400i,500,500i,600,600i');
body
{
	font-size: 14px;
	background-color: #fff !important;
	font-family: 'Montserrat', sans-serif;
	font-weight:400;
}
.cycle{ margin:0; padding:0 ;position:relative;height:100vh;overflow:hidden;}
#circle-image
{
	height:98vh;
	top:-10vh;
	position:absolute;	
	-webkit-animation: zoomIn 2s linear;
			animation: zoomIn 2s linear;
	animation-delay:1s;
	transform:scale(0.3);
}
@media (max-width: 768px) {
	.loginblock
	{
		width:100vw;
		left:0px;
		padding:20px;
		margin-top:5vh;
	}
	.yellow-circle
	{
		background-color:#fff !important;
	}
	
}
.loginblock
{
	position:absolute;
	width:30vw;
	left:60%;
	height:98vh;
	padding:40px;
	margin-top:15vh;
}
@keyframes zoomIn {
	5% {
		transform:scale(0.3);
		left:-0%;
	}
	10% {
		transform:scale(0.4);
		left:-0.5%;
	}
	15% {
		transform:scale(0.5);
		left:-1.5%;
	}
	20% {
		transform:scale(0.6);
		left:-3%;
	}	
	25% {
		transform:scale(0.7);
		left:-4.5%;
	}	
	30% {
		transform:scale(0.8);
		left:-6%;
	}
	35% {
		transform:scale(0.9);
		left:-7.5%;
	}
	40% {
		transform:scale(1.1);
		left:-9%;
	}
	45%,50% {
		transform:scale(1.4);
		left:-10.5%;
	}
	
	55% {
		transform:scale(1.7);
		left:-12%;
	}
	60% {
		transform:scale(1.9);
		left:-13.5%;
	}
	65% {
		transform:scale(2.1);
		left:-15%;
	}
	70%,100% {
		transform:scale(2.4);
		left:-16.5%;
	}
	to
	{
		transform:scale(2.4);
		left:-18%;
	}
}
@-webkit-keyframes zoomIn {
	5% {
		-webkit-transform:scale(0.3);
		transform:scale(0.3);
		left:-0%;
	}
	10% {
		-webkit-transform:scale(0.4);
		transform:scale(0.4);
		left:-1.5%;
	}
	15% {
		-webkit-transform:scale(0.5);
		transform:scale(0.5);
		left:-3.5%;
	}
	20% {
		-webkit-transform:scale(0.6);
		transform:scale(0.6);
		left:-6%;
	}	
	25% {
		-webkit-transform:scale(0.7);
		transform:scale(0.7);
		left:-8.5%;
	}	
	30% {
		-webkit-transform:scale(0.8);
		transform:scale(0.8);
		left:-12%;
	}
	35% {
		-webkit-transform:scale(0.9);
		transform:scale(0.9);
		left:-14.5%;
	}
	40% {
		-webkit-transform:scale(1.1);
		transform:scale(1.1);
		left:-18%;
	}
	45%,50% {
		-webkit-transform:scale(1.4);
		transform:scale(1.4);
		left:-20.5%;
	}
	
	55% {
		-webkit-transform:scale(1.7);
		transform:scale(1.7);
		left:-24%;
	}
	60% {
		-webkit-transform:scale(1.9);
		transform:scale(1.9);
		left:-26.5%;
	}
	65% {
		-webkit-transform:scale(2.1);
		transform:scale(2.1);
		left:-30%;
	}
	70%,100% {
		-webkit-transform:scale(2.4);
		transform:scale(2.4);
		left:-32.5%;
	}
	to
	{
		-webkit-transform:scale(2.4);
		transform:scale(2.4);
		left:-36%;
	}
}
@-moz-keyframes zoomIn {
	5% {
		-moz-transform:scale(0.3);
		transform:scale(0.3);
		left:-0%;
	}
	10% {
		-moz-transform:scale(0.4);
		transform:scale(0.4);
		left:-1.5%;
	}
	15% {
		-moz-transform:scale(0.5);
		transform:scale(0.5);
		left:-3%;
	}
	20% {
		-moz-transform:scale(0.6);
		transform:scale(0.6);
		left:-4.5%;
	}	
	25% {
		-moz-transform:scale(0.7);
		transform:scale(0.7);
		left:-6%;
	}	
	30% {
		-moz-transform:scale(0.8);
		transform:scale(0.8);
		left:-7.5%;
	}
	35% {
		-moz-transform:scale(0.9);
		transform:scale(0.9);
		left:-9%;
	}
	40% {
		-moz-transform:scale(1.1);
		transform:scale(1.1);
		left:-10.5%;
	}
	45%,50% {
		-moz-transform:scale(1.4);
		transform:scale(1.4);
		left:-12.5%;
	}
	
	55% {
		-moz-transform:scale(1.7);
		transform:scale(1.7);
		left:-14%;
	}
	60% {
		-moz-transform:scale(1.9);
		transform:scale(1.9);
		left:-16.5%;
	}
	65% {
		-moz-transform:scale(2.1);
		transform:scale(2.1);
		left:-20%;
	}
	70%,100% {
		-moz-transform:scale(2.4);
		transform:scale(2.4);
		left:-22.5%;
	}
	to
	{
		-moz-transform:scale(2.4);
		transform:scale(2.4);
		left:-24%;
	}
}

.animated {
  animation-duration: 1s;
  animation-fill-mode: both;
}
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translate3d(0, 100%, 0);
  }

  to {
    opacity: 1;
    transform: none;
  }
}

.fadeInUp {
  animation-name: fadeInUp;
}
.input-group-addon
{
	background-color:#fff;
	border:1px solid #fbc928;
}
.form-control
{
	border-left:0px;
	border-color:#fbc928;
	box-shadow:none;
	padding:15px;
	height:50px;
	color:#333 !important;
}
.input-group, .btn
{
	margin-top:30px;
}
.btn
{
	padding:15px;
}
.welcomeblock
{
	position:relative;
}
.yellow-circle
{
	height: 25px;
    width: 25px;
    position: absolute;
    background-color: #fbc928;
    border-radius: 50%;
    top: 20px;
    left: -10px;
}
.red-circle
{
	height: 14px;
    width: 14px;
    position: absolute;
    background-color: red;
    border-radius: 50%;
    top: 0px;
    left: 85px;
    z-index: -1;
}
.green-rect
{
	height: 10px;
    width: 10px;
    background-color: green;
    top: 25px;
    left: 200px;
    position: absolute;
}
.movingCycle , path
{
	animation-name: leftToRight;
    animation-duration: 2s;
	animation-delay:4.2s;
}
@keyframes leftToRight {
    from {
		transform:translate(-2342.11px,1966.401px)
	}
	5% {
		transform:translate(-2342.11px,1966.401px)
	}
	15% {
		transform:translate(-2336.11px,1966.401px)
	}
	25% {
		transform:translate(-2330.11px,1966.401px)
	}
	35% {
		transform:translate(-2324.11px,1966.401px)
	}
	35% {
		transform:translate(-2318.11px,1966.401px)
	}
	45% {
		transform:translate(-2312.11px,1966.401px)
	}
	55% {
		transform:translate(-2306.11px,1966.401px)
	}
	65% {
		transform:translate(-2300.11px,1966.401px)
	}
	75% {
		transform:translate(-2294.11px,1966.401px)
	}
	85% {
		transform:translate(-2288.11px,1966.401px)
	}
	95% {
		transform:translate(-2282.11px,1966.401px)
	}
	100% {
		transform:translate(-2276.11px,1966.401px)
	}
    to { transform:translate(-2276.11px,1966.401px)}
}
#anim-extra-one {
  height: 20px;
  width: 20px;
  display:inline-block;
  -webkit-animation: square-to-circle 2s 1s infinite cubic-bezier(1, 0.005, 0.215, 1.2) alternate;
          animation: square-to-circle 2s 1s infinite cubic-bezier(1, 0.005, 0.215, 1.2) alternate;
}

@-webkit-keyframes square-to-circle {
  0% {
    border-radius: 0 0 0 0;
    background: coral;
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  25% {
    border-radius: 50% 0 0 0;
    background: darksalmon;
    -webkit-transform: rotate(45deg);
            transform: rotate(45deg);
  }
  50% {
    border-radius: 50% 50% 0 0;
    background: indianred;
    -webkit-transform: rotate(90deg);
            transform: rotate(90deg);
  }
  75% {
    border-radius: 50% 50% 50% 0;
    background: lightcoral;
    -webkit-transform: rotate(135deg);
            transform: rotate(135deg);
  }
  100% {
    border-radius: 50%;
    background: darksalmon;
    -webkit-transform: rotate(180deg);
            transform: rotate(180deg);
  }
}

@keyframes square-to-circle {
  0% {
    border-radius: 0 0 0 0;
    background: coral;
    -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
  }
  25% {
    border-radius: 50% 0 0 0;
    background: darksalmon;
    -webkit-transform: rotate(45deg);
            transform: rotate(45deg);
  }
  50% {
    border-radius: 50% 50% 0 0;
    background: indianred;
    -webkit-transform: rotate(90deg);
            transform: rotate(90deg);
  }
  75% {
    border-radius: 50% 50% 50% 0;
    background: lightcoral;
    -webkit-transform: rotate(135deg);
            transform: rotate(135deg);
  }
  100% {
    border-radius: 50%;
    background: darksalmon;
    -webkit-transform: rotate(180deg);
            transform: rotate(180deg);
  }
}
</style>
</head>
<body>
<div class="cycle">	
	<svg id="circle-image" width="100%;" height="100%" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="-1933 2038 518.6 518.6">
  <defs>
    <style>
      .cls-1, .cls-5 {
        fill: #fbc928;
      }

      .cls-2 {
        fill: #fff;
      }

      .cls-3 {
        clip-path: url(#clip-path);
      }

      .cls-4 {
        fill: #e72420;
      }

      .cls-5 {
        font-size: 16px;
        font-family: 'Montserrat', sans-serif;
        font-weight: 700;
      }

      .cls-6 {
        filter: url(#Ellipse_2);
      }
    </style>
    <filter id="Ellipse_2" x="-1933" y="2038" width="518.6" height="518.6" filterUnits="userSpaceOnUse">
      <feOffset input="SourceAlpha"/>
      <feGaussianBlur stdDeviation="6" result="blur"/>
      <feFlood flood-opacity="0.322"/>
      <feComposite operator="in" in2="blur"/>
      <feComposite in="SourceGraphic"/>
    </filter>
    <clipPath id="clip-path">
      <circle id="Ellipse_1" data-name="Ellipse 1" class="cls-1" cx="241.3" cy="241.3" r="241.3" transform="translate(0 0)"/>
    </clipPath>
  </defs>
  <g id="Group_185" data-name="Group 185" transform="translate(640.8 317.8)">
    <g id="Group_10" data-name="Group 10" transform="translate(-2867.8 -3553.8)">
      <g class="cls-6" transform="matrix(1, 0, 0, 1, 2227, 3236)">
        <circle id="Ellipse_2-2" data-name="Ellipse 2" class="cls-2" cx="241.3" cy="241.3" r="241.3" transform="translate(-1915 2056)"/>
      </g>
      <g id="Group_1" data-name="Group 1" transform="translate(312 5292)">
        <circle id="Ellipse_2-3" data-name="Ellipse 2" class="cls-1" cx="241.3" cy="241.3" r="241.3" transform="translate(0 0)"/>
        <g id="Mask_Group_1" data-name="Mask Group 1" class="cls-3">
          <rect id="Rectangle_1" data-name="Rectangle 1" class="cls-4" width="530.86" height="298.61" transform="translate(-21.115 289.56)"/>
        </g>
      </g>
    </g>
    <path class="movingCycle" id="Path_30" data-name="Path 30" d="M63.938,24.048c-.81.713-1.652,1.394-2.43,2.139-3.953,3.823-7.874,7.679-11.891,11.47a2.992,2.992,0,0,0-.81,3.013,6.43,6.43,0,0,1-.486,3.5c-.451.972-.421,1.426.386,1.976a3.55,3.55,0,0,1,1.847,3.662,2.938,2.938,0,0,1-.81,1.491c-1.134.94-4.666.81-5.67-.227a2.442,2.442,0,0,1-.583-2.009,3.3,3.3,0,0,1,1.62-1.555,4.428,4.428,0,0,1,1.652-.258v-.52c-.356-.032-.81-.193-1.1-.064a9.331,9.331,0,0,1-6.124.81,1.743,1.743,0,0,0-2.171,1.166c-2.074,5.508-6.221,8.781-11.632,10.625-1.263.421-2.495,1-3.791,1.426a4.141,4.141,0,0,1-2.041.356c-3.985-.778-8.1-1.3-11.47-3.888a19.56,19.56,0,0,1-6.671-8.746,15.584,15.584,0,0,1-.81-3.435c-.324-2.851-.518-5.7-.81-8.521-.129-1.426.129-2.592,1.652-3.11a1.481,1.481,0,0,0,.745-.583c1.685-2.3,3.338-4.6,5.119-7.1-1.847-.583-3.273-1.491-2.365-3.467a3.337,3.337,0,0,1,2.139-1.847c5.767-.681,11.6-1.2,17.4-1.75.356-.032.778.193,1.1.129.583-.162,1.458-.322,1.652-.745a50.682,50.682,0,0,0,1.555-4.83c-1.976.386-3.726.386-5.087,1.069-1.491.745-2.722,1.069-3.985-.064-1.328-1.2-.227-2.333.386-3.6A6.188,6.188,0,0,1,19.286,9.5c-.322-.583-.81-1.458-.583-1.847.322-.518,1.2-.778,1.879-1a1.891,1.891,0,0,1,1.1.129c3.953,1.555,7.841.81,11.7-.421a21.38,21.38,0,0,1,4.7-1.037c1.847-.193,2.268.94,1.782,2.657-.713,2.463-2.4,3.37-4.6,3.823a3.241,3.241,0,0,0-1.652,1.1H61.734c-.356-.713-.551-1.523-1.782-1.166-.322.1-.94-.386-1.231-.778a12.516,12.516,0,0,1-.972-1.847c-.94-1.75-2.074-2.4-3.985-1.879a32.749,32.749,0,0,0-4.05,1.587,8.311,8.311,0,0,1-1.3.681,5.788,5.788,0,0,1-1.652-.129c.193-.518.193-1.231.551-1.491a21.078,21.078,0,0,1,2.916-1.62c.258-.129.486-.258.745-.386-.064-.162-.129-.322-.193-.451A29.031,29.031,0,0,0,48,6.489c-.972.451-1.847,1.134-2.786,1.62-1.458.681-2.333-.129-3.046-1.3-.778-1.231-.129-2.009.907-2.657a17.745,17.745,0,0,0,1.587-1.137,8.06,8.06,0,0,1,8.489-1.3,4.627,4.627,0,0,0,4.731-.451C60.212-.315,62.8.333,65.2,1.144a1.67,1.67,0,0,1,.681,2.722A37.709,37.709,0,0,1,61.67,7.333c.193.551.162,1.879,2.009,1.328a.932.932,0,0,1,.745.421,1.437,1.437,0,0,1-.292,1.134,1.273,1.273,0,0,0-.356,1.815c1.426,3.013,2.819,6.059,4.147,9.137a1.764,1.764,0,0,0,2.4,1.166C79.685,20.2,90.442,27.1,92.807,36.559c3.175,12.572-6.286,23.653-18.7,23.815-7.388.1-12.7-3.92-16.266-10.239-4.83-8.587-2.981-16.687,3.92-23.07.875-.81,1.815-1.555,2.722-2.333a4.863,4.863,0,0,0-.551-.684Zm-29.226-7.1c.227.713.356,1.134.518,1.523C37.3,23.562,39.41,28.649,41.484,33.7c1.134,2.722,3.629,3.273,5.8,1.263,4.665-4.309,9.331-8.652,14.029-12.9a2.743,2.743,0,0,0,.81-3.435c-.518-1.328-1.426-1.652-2.786-1.652-6.48.032-12.96-.032-19.408-.032Zm33.341,8.457c.713-.193,1.394-.421,2.106-.551a17.642,17.642,0,0,1,4.439-.681c9.882.907,16.622,7.517,16.233,17.692-.451,11.179-12.378,19.084-22.584,14.84C54.7,51.071,56.032,34.9,64.23,28.034c.421-.356.907-.616,1.328-.907a3.883,3.883,0,0,0-.386-.356c-5.122.972-9.237,11.664-7.39,19.214C60.179,55.7,69.77,60.859,79.62,57.746,89.6,54.6,94.46,44.2,90.507,34.19a16.427,16.427,0,0,0-22.454-8.781Zm9.4,17.95c-.193.1-.386.227-.616.322,1.134,3.629,2.268,7.226,3.5,11.211,1.879-1.491,3.4-2.722,4.925-3.856.972-.713.907-1.328-.032-2.009C83.152,47.5,81.111,45.916,79,44.361a10.679,10.679,0,0,0-1.555-1Zm-2.2,1.394h-.518c-1.1,3.467-2.2,6.966-3.4,10.754,2.139,0,3.953.032,5.735,0,1.328-.032,1.62-.81,1.231-2.009-.81-2.495-1.588-5.023-2.4-7.55C75.732,45.53,75.44,45.141,75.246,44.753Zm-15.423.032c1,1.782,1.782,3.273,2.625,4.7.551.94,1.2,1,2.074.193,1.944-1.782,3.985-3.467,5.93-5.216a9.875,9.875,0,0,0,1-1.3c-.064-.129-.162-.292-.227-.421-3.662.063-7.129,1.392-11.405,2.039ZM77.805,40.02H89.631c-.616-1.879-1.1-3.5-1.717-5.023-.129-.356-.972-.778-1.2-.648-2.981,1.685-5.93,3.467-8.878,5.249-.031,0,0,.2-.031.424ZM35.684,47.442c-4.277,8-10.723,12.183-19.829,10.336C6.392,55.866,2.764,49.062,2.6,39.86.722,47.085,6.1,56.9,16.373,58.879,23.339,60.241,34.194,55.7,35.684,47.442ZM8.726,51.3c.909.746,1.617,1.3,2.333,1.88,2.884,2.236,2.884,2.236,4.307-1.1,1.069-2.495,2.106-4.99,3.143-7.485-.1-.064-.193-.162-.292-.227C15.141,46.6,12.063,48.868,8.726,51.3ZM79.07,41.706c-.064.193-.129.356-.193.551,2.463,1.847,4.925,3.759,7.42,5.508.258.162,1.3-.193,1.394-.551a27.624,27.624,0,0,0,1.166-4.342,1.481,1.481,0,0,0-.713-1.166C85.129,41.675,82.08,41.706,79.07,41.706Zm-15,10.368c1.587.875,2.949,1.652,4.342,2.4a1.1,1.1,0,0,0,1.782-.648c.972-3.013,1.912-6.027,2.851-9.04a1.965,1.965,0,0,1-.322-.227c-2.851,2.464-5.67,4.926-8.651,7.517ZM16.275,41.9c0-.129.032-.258.032-.421-3.6-1.134-7.226-2.268-10.952-3.467-.129.875-.292,1.62-.356,2.365-.356,3.046-.322,3.078,2.722,2.722C10.572,42.744,13.424,42.322,16.275,41.9Zm58.874-4.83c.129.032.292.1.421.129,2.041-3.046,4.051-6.091,6.221-9.3-1.328-.451-2.495-.778-3.629-1.231-1.263-.486-1.815-.064-1.879,1.231a8.1,8.1,0,0,1-.162.94c-.357,2.757-.649,5.513-.973,8.235Zm-14.225-2.2c3.629,1.426,7.064,2.786,10.5,4.147.162-.193.292-.386.451-.616-2.625-2.528-5.216-5.023-7.971-7.647C62.868,32.181,61.994,33.412,60.924,34.87ZM16.145,43.943a1.739,1.739,0,0,1-.162-.356c-2.43.322-4.83.616-7.226,1-1.134.193-2.236.518-3.338.81a29.988,29.988,0,0,0,1.555,3.435,1.753,1.753,0,0,0,1.685.451C11.188,47.571,13.651,45.757,16.145,43.943Zm53.884-3.856c-2.463-.94-4.439-1.717-6.448-2.463-3.208-1.166-3.6-.875-3.888,2.56,0,.064-.032.1-.032.162-.1,1.587.451,2.106,2.009,1.75C64.294,41.513,66.887,40.864,70.029,40.087Zm-50.19,6.48c-.129-.032-.258-.032-.421-.064-1.3,3.013-2.56,6.059-3.953,9.267,1.62.162,2.981.292,4.374.421.875.1,1.361-.258,1.2-1.2C20.649,52.172,20.26,49.354,19.839,46.567ZM31.8,18.6c-.193.032-.421.032-.616.064-.583,1.815-1.134,3.629-1.75,5.508,3.175,1.231,5.152,4.115,7.614,6.61C35.295,26.737,33.545,22.687,31.8,18.6ZM86.424,32.537c-3.078-4.083-3.175-4.083-5.67-.322-.356.551-.713,1.069-1.069,1.62-.81,1.2-1.587,2.43-2.4,3.629a1.8,1.8,0,0,1,.258.193C80.495,35.939,83.411,34.286,86.424,32.537ZM21.783,44.817c-.162.032-.322.1-.486.129.356,2.43.713,4.86,1.134,7.29.648,3.92.875,3.985,4.5,1.75C25.218,50.941,23.5,47.9,21.783,44.817Zm51.292-9.1c.227,0,.421-.032.648-.032.292-2.819.616-5.6.875-8.424.1-.875-.421-1.263-1.3-1.2-.94.064-2.139-.129-2.2,1.263C70.9,30.3,72.136,32.959,73.075,35.712ZM50.264,2.857c0,.193.032.356.032.551,2.269.875,4.6,1.652,6.837,2.623a6.073,6.073,0,0,1,1.879,1.652,8.81,8.81,0,0,1,.875,1.426,2.721,2.721,0,0,0-.162-1.458c-.681-2.2-.227-2.819,2.009-3.046A7.179,7.179,0,0,0,64,3.86a1.143,1.143,0,0,0,.356-1.1c-.1-.258-.713-.451-1.069-.421a1.907,1.907,0,0,0-1.069.616,4.508,4.508,0,0,1-4.472,1.231c-1.62-.386-3.208-.875-4.86-1.231a23.4,23.4,0,0,0-2.625-.1ZM36.04,14.651c0,.193.032.386.032.616,8.651,0,17.27.193,25.921.064v-.648Q49.017,14.635,36.04,14.651ZM31.731,49.937c-2.56-1.361-4.83-2.592-7.1-3.823l-.193.292c1.231,2.171,2.463,4.374,3.791,6.74C29.43,52.043,30.467,51.071,31.731,49.937ZM16.955,26.769c.81,2.851,1.523,5.345,2.236,7.809a40.247,40.247,0,0,0,1.944-6.707,1.288,1.288,0,0,0-.648-1.037,24.793,24.793,0,0,0-3.532-.065Zm-1.523.583a6.979,6.979,0,0,0-1.976.81c-.386.322-.745,1.263-.551,1.555,1.555,2.365,3.208,4.637,4.86,6.9.129-.064.227-.129.356-.193-.874-2.917-1.717-5.866-2.692-9.073Zm7.906,15.358c.322.551.356.778.451.778,3.273.486,6.545.972,9.818,1.328.193.032.648-.745.648-1.166a1.182,1.182,0,0,0-.842-.81c-1.717-.1-3.435-.129-5.152-.129Zm-2.106-3.143c.032.162.064.292.1.451H34.679c-.162-.81-.292-1.361-.451-2.074C29.855,38.5,25.54,39.018,21.233,39.568Zm10.79-5.995c-.81-.681-.842-2.333-2.171-1.491-2.365,1.491-4.536,3.3-6.8,4.958a1.967,1.967,0,0,0,.227.322c2.819-1.229,5.638-2.461,8.748-3.789ZM8.693,31.921c2.3,1.717,4.472,3.4,6.675,5.054a1.269,1.269,0,0,1,.322-.258c-1.166-1.62-2.3-3.273-3.5-4.894C10.8,29.88,10.637,29.88,8.693,31.921Zm5.538,6.448a1.151,1.151,0,0,1,.129-.292c-1.555-1.134-3.078-2.3-4.637-3.435-2.009-1.458-2.4-1.361-3.531,1.069,2.663.876,5.353,1.783,8.042,2.658Zm12.151-9.853c-.81,1.879-1.587,3.662-2.365,5.409.1.064.227.162.322.227,1.652-1.3,3.3-2.56,5.087-3.953-1.13-.643-2.005-1.129-3.042-1.68Zm9.332,7.293c-.322-3.985-6.156-9.753-10.207-9.853A19.119,19.119,0,0,1,35.715,35.809Zm-2.43.583c.064-.129.129-.258.193-.421-.451-.258-1-.875-1.328-.745a32.319,32.319,0,0,0-3.435,1.491c.032.129.064.227.1.356Zm9.366,3.531c-.648.907-2.041,1.267-1.069,2.5a1.621,1.621,0,0,0,1.587.064C44.465,41.513,43.2,40.864,42.65,39.924ZM23.922,25.117c-2.43-1.263-4.765-.907-7.1,0ZM6.814,23.724c2.106.386,2.56.1,2.722-1.976-.486-.064-1-.162-1.134-.162C7.786,22.46,7.332,23.076,6.814,23.724ZM75.829,41.739l.1-.972c-.713-.129-1.426-.258-2.139-.356-.032.451-.064.907-.1,1.328Zm-45.91,4.8a2.852,2.852,0,0,0-.162.486,17.853,17.853,0,0,0,2.463,1c.227.064.648-.551,1.2-1.069C31.958,46.761,30.953,46.632,29.919,46.536Z"/>
    <text id="TELUGU_DESAM_PARTY" data-name="TELUGU DESAM
PARTY" class="cls-5" transform="translate(-2299 2069)"><tspan x="0" y="0">TELUGU DESAM</tspan><tspan x="0" y="22">PARTY</tspan></text>
  </g>
</svg>
	<!---Login Div---->
<div class="loginblock hide">
	<div id="wrapper">
		<div id="wrappertop"></div>

		<div id="wrappermiddle">
<div id="forgot_password_window1" style="background-color: #f5f5f5;">
<div id="forgot_password_window_inner1" style="font-size:0.8em"></div>
</div>
			<div class="welcomeblock">
				<span class="yellow-circle"></span>
				<span class="red-circle"></span>
				<span class="green-rect"></span>
				<h1>WELCOME</h1>
			</div>
			<h4>PLEASE LOGIN</h4>
<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">
			<div id="username_input">

				<div id="username_inputleft"></div>

				<div id="username_inputmiddle">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="fa fa-user-o"></i>
						</span>
						<input type="text" id="userName1" placeholder="User Name" class="url form-control">
					</div>
				</div>

				<div id="username_inputright"></div>

			</div>

			<div id="password_input">
				
				<div id="password_inputleft"></div>
				<div id="password_inputmiddle">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="fa fa-lock"></i>
						</span>
						<input type="password" id="passWord_Id1" placeholder="Password" class="url form-control">
					</div>
				</div>
				<div id="password_inputright"></div>
			</div>

			<div id="submit"> 
				<!--<a class="signin" id="submit1" onclick="ajaxCallForLoginPopup();" >SIGN IN</a>-->
				<!--<input type="submit" class="signin" value="SIGN IN">SIGN IN</input>-->
				<button class="btn btn-success btn-block signin"  id="submit1" type="submit">SIGN IN  </button>
			</div>


			<div id="links_left">
				<div id="ajaxcallimage"  class = "col-sm-12" style="display:none;font-weight:bold;color: #333;font-size:small;margin:8px auto;">
					<font  style="font-size:14px;"><span id="anim-extra-one"></span> Sending Your Request. Please wait...</font>
				</div>
				<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 2px;"></div>
			<!--<a onclick="showForgotPasswordPanelForPopup();" href="javascript:{}">Forgot your Password?</a>-->

			</div>

			<!-----<div id="links_right"><a href="#">Not a Member Yet?</a></div>---->

		</div>
</form>
		<div id="wrapperbottom"></div>
		
		<div id="powered">
		<p>&copy; 2017 Telugu Desam Party</p>
		</div>
	</div><!---Login Div---->
</div>
</div>
<script>
/*$("#submit").live("click",function(){
	$("#wrappermiddle").css("height","257px");
});*/
/* setTimeout(function(){
	$("#circle-image").css("transform","scale(2.4)");
	$("#circle-image").css("left","-18%");
	$(".loginblock").addClass("show");
	$(".loginblock").addClass(" animated fadeInUp");
	if($(window).width() < 768)
	{
		$(".party-name").hide();
	}
},2000); */
setTimeout(function(){
	$("#circle-image").css("transform","scale(2.4) translate(-22%,0px)");
	//$("#circle-image").css("transform","translate(-40,0)");
	$(".loginblock").addClass("show");
	$(".loginblock").addClass(" animated fadeInUp");
	if($(window).width() < 768)
	{
		$(".party-name").hide();
	}
},3000);
setTimeout(function(){
	$(".movingCycle").attr("transform","translate(-2276.11,1966.401)");
},5000)


var url = window.location.href;

if(url.indexOf("mydepartments.in")  == 0 ){
	window.location.href="https://www.mydepartments.in/govtLoginAction.action";
}

var userip = "";
</script>
<script type="text/javascript" src="https://l2.io/ip.js?var=userip"></script>
</body>
</html>