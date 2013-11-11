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
<script src="<c:url value="/assets/js/jquery/highcharts/highcharts.js" />"></script>
<script src="<c:url value="/assets/js/jquery/highcharts/modules/exporting.js" />"></script>
<script src="<c:url value="/assets/js/map/map1.js"  />"></script>
<script src="<c:url value="/assets/js/map/map.js"   />"></script>
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
				<div class="hd-1 cf">
					<ul id="Tabs-2" class="tabs-2 fl">
						<li><a href="#">平面视图</a></li>
						<li><a href="#">数据报表</a></li>
						<li><a href="#">报警信息</a></li>
					</ul>
					<div class="fr toolbar">
						<ul>
							<li><a href="#"><i class="icon-toolbar icon-toolbar2"></i>打印</a></li>
							<li><a href="#"><i class="icon-toolbar icon-toolbar3"></i>导出</a></li>
						</ul>
					</div>
				</div>
				<div class="bd-1">
					<div class="panes-2">
						<div>
							<div class="wrap-img">
								<img alt="图" src="<c:url value="/assets/js/localtime.js" />" />
							</div>
						</div>
						<div>
							<h3 class="title-2">曲线图</h3>
							<div class="wrap-img">
								<div id="highcharts"
									style="max-width: 100%; min-width: 690px; height: 400px; margin: 0 auto"></div>
							</div>
							<hr />
							<h3 class="title-2">实时数据</h3>
							<table class="table table-auto">
								<thead>
									<tr>
										<th>序号</th>
										<th>时间</th>
										<th>S1</th>
										<th>S2</th>
										<th>S3</th>
										<th>S4</th>
										<th>S5</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>2013年02月27日 15:13:32</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2013年02月27日 15:13:32</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
									</tr>
									<tr>
										<td>1</td>
										<td>2013年02月27日 15:13:32</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
										<td>=GetHiDat</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div>
							<table class="table table-auto">
								<thead>
									<tr>
										<th>序号</th>
										<th>报警类型</th>
										<th>日期</th>
										<th>时间</th>
										<th>传感器</th>
										<th>传感器位号</th>
										<th>火警温度</th>
										<th>报警阔值</th>
										<th>报警类别</th>
										<th>确认</th>
										<th>操作人</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td><span class="color-red">火警</span></td>
										<td>2013-9-24</td>
										<td>14:21:34</td>
										<td>S1</td>
										<td>揽沟1回路</td>
										<td>81</td>
										<td>81</td>
										<td>紧急</td>
										<td>确认</td>
										<td>工程师</td>
									</tr>
									<tr>
										<td>1</td>
										<td><span class="color-red">火警</span></td>
										<td>2013-9-24</td>
										<td>14:21:34</td>
										<td>S1</td>
										<td>揽沟1回路</td>
										<td>81</td>
										<td>81</td>
										<td>紧急</td>
										<td>确认</td>
										<td>工程师</td>
									</tr>
									<tr>
										<td>1</td>
										<td><span class="color-red">火警</span></td>
										<td>2013-9-24</td>
										<td>14:21:34</td>
										<td>S1</td>
										<td>揽沟1回路</td>
										<td>81</td>
										<td>81</td>
										<td>紧急</td>
										<td>确认</td>
										<td>工程师</td>
									</tr>
								</tbody>
							</table>
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