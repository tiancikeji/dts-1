<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!--[if IE 6]><html class="ie6 ielt9 no-css3 no-js lte7" lang="en" dir="ltr"><![endif]-->
<!--[if IE 7]><html class="ie7 ielt9 no-css3 no-js lte7" lang="en" dir="ltr"><![endif]-->
<!--[if IE 8]><html class="ie8 ielt9 no-css3 no-js" lang="en" dir="ltr"><![endif]-->
<!--[if IE 9]><html class="ie9 ielt9 no-css3 no-js" lang="en" dir="ltr"><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8) | !(IE 9) ]><!--><html lang="en" dir="ltr" class="no-js"><!--<![endif]-->
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>NEINE预警系统</title>
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" href="<c:url value="/assets/css/reset.css" />" />
<link rel="stylesheet" href="<c:url value="/assets/css/screen.css" />" />
<!--[if IE]>
<script src="js/ie/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
<style type="text/c
ss">.css3{behavior: url(js/ie/css3.htc);-pie-lazy-init: true; position: relative;}.ie6png{-pie-png-fix: true;}</style>
<script src="js/ie/ie9.js"></script>
<![endif]-->
<!--[if IE 6]>
<script src="js/ie/png.js"></script>
<script>DD_belatedPNG.fix('.pngfix, .pngfix img, #logo img, .icon-nav, .divider-v');</script>
<![endif]-->
<script src="<c:url value="/assets/js/ie/modernizr.js" />"></script>
<script src="<c:url value="/assets/js/jquery/jquery.js" />"></script>
<script src="<c:url value="/assets/js/jquery/jquery.tools.min.js" />"></script>

<!--
ztree
http://www.ztree.me/v3/main.php
-->
<link rel="stylesheet" href="<c:url value="/assets/js/jquery/ztree/css/zTreeStyle/zTreeStyle.css" />" type="text/css">
<script type="text/javascript" src="<c:url value="/assets/js/jquery/ztree/jquery.ztree.core-3.5.js" />"></script>
<!--
highcharts
http://www.highcharts.com/demo/
-->

<script src="<c:url value="/assets/js/map/map1.js"  />"></script>

<script>
	$(function() {
		// tabs1
		// http://jquerytools.org/demos/tabs/index.html
		$("ul#Tabs-1").tabs("div.panes-1 > div");
		// tabs2
		$("ul#Tabs-2").tabs("div.panes-2 > div");
	});
</script>
</head>
<body>
<div id="container" class="grid-elastic cf">
		<c:import url="left.jsp"></c:import>
		<div id="content" class="wrapper">
			<div class="mod-1">
			
				<div class="bd-1">
					<div class="panes-2">
				实时监控
						<div>
						
						</div>
					</div>
					<!-- tabs2 -->

				</div>
			</div>
			<!-- mod-1 -->

		</div>
		<!-- //content -->

	</div>
	<!-- //container -->
</body>
</html>