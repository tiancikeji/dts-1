<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="<c:url value="assets/css/reset.css" />" />
<link rel="stylesheet" href="<c:url value="assets/css/screen.css"/>" />
<!--[if IE]>
<script src="js/ie/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
<style type="text/css">.css3{behavior: url(js/ie/css3.htc);-pie-lazy-init: true; position: relative;}.ie6png{-pie-png-fix: true;}</style>
<script src="js/ie/ie9.js"></script>
<![endif]-->
<!--[if IE 6]>
<script src="js/ie/png.js"></script>
<script>DD_belatedPNG.fix('.pngfix, .pngfix img, #logo img, .icon-nav, .divider-v');</script>
<![endif]-->
<script src="js/ie/modernizr.js"></script>
<script src="js/jquery/jquery.js"></script>
<script src="js/jquery/jquery.tools.min.js"></script>
<script>
$(function() {

	// tabs1
    // http://jquerytools.org/demos/tabs/index.html
    $("ul#Tabs-1").tabs("div.panes-1 > div"); 

    // tabs2
    $("ul#Tabs-2").tabs("div.panes-2 > div"); 
	
});
</script>

<!--
ztree
http://www.ztree.me/v3/main.php
-->
<link rel="stylesheet" href="js/jquery/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/jquery/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
    var setting = {
        view: {
            showIcon: showIconForTree
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"厂区n", open:true},
        { id:11, pId:1, name:"电缆沟n"},
        { id:111, pId:11, name:"区段n"},
        { id:112, pId:11, name:"区段n"},
        { id:113, pId:11, name:"区段n"},
        { id:114, pId:11, name:"区段n"},
        { id:12, pId:1, name:"电缆沟w"},
        { id:121, pId:12, name:"区段n"},
        { id:122, pId:12, name:"区段n"},
        { id:123, pId:12, name:"区段n"},
        { id:124, pId:12, name:"区段n"},
        { id:13, pId:1, name:"电缆沟n", isParent:true},
    ];

    function showIconForTree(treeId, treeNode) {
        return !treeNode.isParent;
    };

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>

<!--
highcharts
http://www.highcharts.com/demo/
-->
<script src="js/jquery/highcharts/highcharts.js"></script>
<script src="js/jquery/highcharts/modules/exporting.js"></script>
<script type="text/javascript">
$(function () {
    $('#highcharts').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Snow depth at Vikjafjellet, Norway'
        },
        subtitle: {
            text: 'Irregular time data in Highcharts JS'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b'
            }
        },
        yAxis: {
            title: {
                text: 'Snow depth (m)'
            },
            min: 0
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m';
            }
        },
        
        series: [{
            name: 'Winter 2007-2008',
            // Define the data points. All series have a dummy year
            // of 1970/71 in order to be compared on the same x axis. Note
            // that in JavaScript, months start at 0 for January, 1 for February etc.
            data: [
                [Date.UTC(1970,  9, 27), 0   ],
                [Date.UTC(1970, 10, 10), 0.6 ],
                [Date.UTC(1970, 10, 18), 0.7 ],
                [Date.UTC(1970, 11,  2), 0.8 ],
                [Date.UTC(1970, 11,  9), 0.6 ],
                [Date.UTC(1970, 11, 16), 0.6 ],
                [Date.UTC(1970, 11, 28), 0.67],
                [Date.UTC(1971,  0,  1), 0.81],
                [Date.UTC(1971,  0,  8), 0.78],
                [Date.UTC(1971,  0, 12), 0.98],
                [Date.UTC(1971,  0, 27), 1.84],
                [Date.UTC(1971,  1, 10), 1.80],
                [Date.UTC(1971,  1, 18), 1.80],
                [Date.UTC(1971,  1, 24), 1.92],
                [Date.UTC(1971,  2,  4), 2.49],
                [Date.UTC(1971,  2, 11), 2.79],
                [Date.UTC(1971,  2, 15), 2.73],
                [Date.UTC(1971,  2, 25), 2.61],
                [Date.UTC(1971,  3,  2), 2.76],
                [Date.UTC(1971,  3,  6), 2.82],
                [Date.UTC(1971,  3, 13), 2.8 ],
                [Date.UTC(1971,  4,  3), 2.1 ],
                [Date.UTC(1971,  4, 26), 1.1 ],
                [Date.UTC(1971,  5,  9), 0.25],
                [Date.UTC(1971,  5, 12), 0   ]
            ]
        }, {
            name: 'Winter 2008-2009',
            data: [
                [Date.UTC(1970,  9, 18), 0   ],
                [Date.UTC(1970,  9, 26), 0.2 ],
                [Date.UTC(1970, 11,  1), 0.47],
                [Date.UTC(1970, 11, 11), 0.55],
                [Date.UTC(1970, 11, 25), 1.38],
                [Date.UTC(1971,  0,  8), 1.38],
                [Date.UTC(1971,  0, 15), 1.38],
                [Date.UTC(1971,  1,  1), 1.38],
                [Date.UTC(1971,  1,  8), 1.48],
                [Date.UTC(1971,  1, 21), 1.5 ],
                [Date.UTC(1971,  2, 12), 1.89],
                [Date.UTC(1971,  2, 25), 2.0 ],
                [Date.UTC(1971,  3,  4), 1.94],
                [Date.UTC(1971,  3,  9), 1.91],
                [Date.UTC(1971,  3, 13), 1.75],
                [Date.UTC(1971,  3, 19), 1.6 ],
                [Date.UTC(1971,  4, 25), 0.6 ],
                [Date.UTC(1971,  4, 31), 0.35],
                [Date.UTC(1971,  5,  7), 0   ]
            ]
        }, {
            name: 'Winter 2009-2010',
            data: [
                [Date.UTC(1970,  9,  9), 0   ],
                [Date.UTC(1970,  9, 14), 0.15],
                [Date.UTC(1970, 10, 28), 0.35],
                [Date.UTC(1970, 11, 12), 0.46],
                [Date.UTC(1971,  0,  1), 0.59],
                [Date.UTC(1971,  0, 24), 0.58],
                [Date.UTC(1971,  1,  1), 0.62],
                [Date.UTC(1971,  1,  7), 0.65],
                [Date.UTC(1971,  1, 23), 0.77],
                [Date.UTC(1971,  2,  8), 0.77],
                [Date.UTC(1971,  2, 14), 0.79],
                [Date.UTC(1971,  2, 24), 0.86],
                [Date.UTC(1971,  3,  4), 0.8 ],
                [Date.UTC(1971,  3, 18), 0.94],
                [Date.UTC(1971,  3, 24), 0.9 ],
                [Date.UTC(1971,  4, 16), 0.39],
                [Date.UTC(1971,  4, 21), 0   ]
            ]
        }]
    });
});
</script>

</head>
<body>
<header id="header" class="cf">

		<h1 id="logo">
			<a href="index.html">
				<img src="images/logo.png" alt="NEINE预警系统" title="NEINE预警系统" />
			</a>
		</h1><!-- logo -->

	    <nav id="nav">
	    	<ul>
                <li class="active"><a href="index.html"><i class="icon-nav icon-nav1"></i>实时监测</a></li>
                <li><a href="query.html"><i class="icon-nav icon-nav2"></i>查询报表</a></li>
                <li><a href="pro.html"><i class="icon-nav icon-nav3"></i>项目信息</a></li>
                <li><a href="set.html"><i class="icon-nav icon-nav4"></i>参数设置</a></li>
                <li><a href="help.html"><i class="icon-nav icon-nav5"></i>帮助手册</a></li>
                <li><a href="index.html"><i class="icon-nav icon-nav6"></i>安全退出</a></li>
                <li class="divider-v"></li>
                <li>
                    <p><a class="nav-help" href="help.html">帮助</a></p>
                    <p id="localTime"><script src="js/localtime.js"></script></p>
                </li>
            </ul>
	    </nav><!-- nav -->

</header><!-- //header -->


<div id="container" class="grid-elastic cf">

    <div id="sidebar" class="grid-220">
        <div class="menu">
            <h3>实时监测</h3>
            
            <ul id="Tabs-1" class="tabs-1 cf">
                <li><a href="#">监测区域</a></li>
                <li><a href="#">仪器</a></li>
                <li><a href="#">传感器</a></li>
            </ul>
            <div class="panes-1">
                <div>
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div>
                    仪器
                </div>
                <div>
                    传感器
                </div>
            </div><!-- tabs1 -->

        </div>
        <div class="form-mini ac">
            <p>监测名称：<input class="ipt-txt ipt-s" type="text" /></p>
            <p>最高温度：<input class="ipt-txt ipt-s" type="text" /></p>
            <p>最低温度：<input class="ipt-txt ipt-s" type="text" /></p>
            <p>平均温度：<input class="ipt-txt ipt-s" type="text" /></p>
        </div>
    </div><!-- //sidebar -->

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
                            <img alt="图" src="images/pic/pic3.jpg" />
                        </div>
                    </div>
                    <div>
                        <h3 class="title-2">曲线图</h3>
                        <div class="wrap-img">
                            <div id="highcharts" style="max-width: 100%; min-width: 690px; height: 400px; margin: 0 auto"></div>       
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
                </div><!-- tabs2 -->   

            </div>
        </div><!-- mod-1 -->

    </div><!-- //content -->   

</div><!-- //container -->
</body>
</html>