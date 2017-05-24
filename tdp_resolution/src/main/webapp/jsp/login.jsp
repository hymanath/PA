<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cadre Registration</title>
<link rel="shortcut icon" href="static/img/fevicon.png">
<link href="static/css/TemplateBase.css" type="text/css" rel="stylesheet">
<link href="static/css/MaterialDesignStyles.css" type="text/css" rel="stylesheet">
<link href="static/css/MaterialDesignAnimations.css" rel="stylesheet" type="text/css">
<link href="static/css/TemplateStyles.css" rel="stylesheet" type="text/css">
<style type="text/css">

header
{
	margin-top:80px;
}

section
{
	margin-top:40px;
}
body.dark { background-color: #3f4652;
 background-image: url("static/img/bg_tiles.jpg") !important; 
 background-size: cover; background-position: 0 0 }
 .bg_ff{background:#fff;}
 .center{top:15%;left:0;height:60%;padding:3em;position:absolute}
 .m_top20{margin-top:20px;}
 .input-group .input-group-addon{background:none;margin:auto}
 .input-group .form-control{box-shadow:none;border-left:0px;}
 .input-group .form-control:active{border:0px;}
</style>
<body class="dark">
<section>
	<div id="buildModalId"></div>
</section>

<section>
	<div class="container">
		<form action="userValidation" id="formId">
			<div class="row">
				<div class="col-md-4 col-md-offset-4 col-xs-10 col-xs-offset-1 col-sm-4 col-sm-offset-4 bg_ff center">
					<img src="static/img/updated_logo.gif" class="img-responsive" style="display:block;margin:auto;height:100px;">
					<div class="form-group label-floating m_top20">
						<label class="control-label">Username</label>
						<input type="text" id="unameId" class="form-control">
					</div>
					<div class="form-group label-floating m_top20">
						<label class="control-label">Password</label>
						<input type="password" id="pwdId" class="form-control m_top20">
					</div>
					<button type="button" id="submitId" class="tmpCls btn btn-primary btn-green btn-raised btn-block btn-info m_top20">SUBMIT</button>
					<div id="successDiv"></div>
					<p id="userNameError" class="text-danger"></p>
					<p id="passwordError" class="text-danger"></p>
				</div>
			</div>
		</form>
	</div>
</section>
<script src="static/js/JqueryBase.js" type="text/javascript"></script>
<script src="static/js/TemplateBase.js" type="text/javascript"></script>
<script src="static/js/MaterialDesignBase.js" type="text/javascript"></script>
<script src="static/js/MaterialDesignAnimations.js" type="text/javascript"></script>
<script src="static/js/md5.js" type="text/javascript"></script>
<script src="static/js/ajaxCallsJS/loginAjaxcalls.js" type="text/javascript"></script>
<script src="http://fezvrasta.github.io/snackbarjs/dist/snackbar.min.js"></script>
<script>
$(function () {
    $.material.init();
  }); 
window !== window.top && t();
    (function() {
        function a() {
            $("body").mousemove(function(a) {
                m = a.clientX;
                q = a.clientY;
                r = Date.now();
                p || g()
            });
            $(window).on("blur mouseout", function() {
                q = m = null
            }).on("resize", function() {
                c && c.parentNode && c.parentNode.removeChild(c);
                b()
            });
            b()
        }

        function b() {
            c = document.createElement("canvas");
            c.className = "loginFun";
            c.width = $(window).width();
            c.height = $(window).height();
            $("body").append(c);
            f = document.createElement("canvas");
            f.width = $(window).width();
            f.height = $(window).height();
            if (c.getContext && c.getContext("2d") &&
                (k = c.getContext("2d"), e = f.getContext("2d"), e.lineCap = "round", e.shadowColor = "#000000", e.shadowBlur = -1 < navigator.userAgent.indexOf("Firefox") ? 0 : 30, !h)) {
                h = new Image;
                var a = $("body").css("background-image");
                a && ($(h).one("load", g), a = a.replace(/url\((.*)\)/, "$1").replace(/["']/gi, "").replace(/\.jpg/, "_color.jpg"), $(h).attr("src", a))
            }
        }

        function g() {
            var a, b = Date.now();
            p = b > r + 500 ? !1 : !0;
            m && p && d.splice(0, 0, {
                time: b,
                x: m,
                y: q
            });
            for (a = 0; a < d.length;) 1E3 < b - d[a].time ? d.splice(a, d.length) : a++;
            0 < d.length && window.l(g);
            e.clearRect(0,
                0, f.width, f.height);
            for (a = 1; a < d.length; a++) {
                var u = Math.sqrt(Math.pow(d[a].x - d[a - 1].x, 2) + Math.pow(d[a].y - d[a - 1].y, 2));
                e.strokeStyle = "rgba(0,0,0," + Math.max(1 - (b - d[a].time) / 1E3, 0) + ")";
                e.lineWidth = 25 + 75 * Math.max(1 - u / 50, 0);
                e.beginPath();
                e.moveTo(d[a - 1].x, d[a - 1].y);
                e.lineTo(d[a].x, d[a].y);
                e.stroke()
            }
            a = c.width;
            b = c.width / h.naturalWidth * h.naturalHeight;
            b < c.height && (b = c.height, a = c.height / h.naturalHeight * h.naturalWidth);
            k.drawImage(h, 0, 0, a, b);
            k.globalCompositeOperation = "destination-in";
            k.drawImage(f, 0, 0);
            k.globalCompositeOperation =
                "source-over"
        }
        var c, f, k, e, h, m = null,
            q = null,
            d = [],
            r = 0,
            p = !0;
        "createTouch" in document || $(a);
        window.l = function() {
            return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame || function(a) {
                window.setTimeout(a, 1E3 / 60)
            }
        }()
    })();
</script>
</body>
</html>
