<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				<div>仪器</div>
				<div>传感器</div>
			</div>
			<!-- tabs1 -->
		</div>
		<div class="form-mini ac">
			<p>
				监测名称：<input class="ipt-txt ipt-s" type="text" />
			</p>
			<p>
				最高温度：<input class="ipt-txt ipt-s" type="text" />
			</p>
			<p>
				最低温度：<input class="ipt-txt ipt-s" type="text" />
			</p>
			<p>
				平均温度：<input class="ipt-txt ipt-s" type="text" />
			</p>
		</div>
	</div>
	<!-- //container -->


