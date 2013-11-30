<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/head.jsp"%>

<div id="container" class="grid-elastic cf">

	<div id="sidebar" class="grid-220">
		<div class="menu">
			<h3>参数设置</h3>
			<div class="menu-content">
				<ul>
					<li><a href="<c:url value="/settings/sensor" />">传感器设置</a></li>
					<li class="mactive"><a href="<c:url value="/settings/area" />">厂区设置</a></li>
					<li><a href="<c:url value="/settings/alert" />">分区设置</a></li>
					<li><a href="<c:url value="/settings/" />">通讯设置</a></li>
					<li><a href="<c:url value="/settings/system" />">系统设置</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //sidebar -->

	<div id="content" class="wrapper">
		<h3>${status}</h3>
		<div class="mod-1">
			<div class="hd-1 cf">
				<h2>厂区设置</h2>
			</div>
			<div class="bd-1">

				<div class="mod-2">
					<div class="hd-2 cf">
						<h2>厂区设置</h2>
					</div>
					<div class="bd-2">
						<div class="wrap-cont">
							<div class="wrap-cont">
								<form action="<c:url value="/settings/area"/>" method="post">
									<ul class="form-list">
										<li><label for="radio"> 上级：</label> <select
											name="pId" class="select-2 ipt-f3">
												<option value="0">无</option>
												<c:forEach var="area" items="${areaList}">
													<option value="${area.id }">${area.name }</option>
												</c:forEach>
										</select></li>
										<li><label for="radio">名称：</label>
											<input name="name" value="" />
										</li>
										<li><label for="radio">背景图片：</label><input type="file" name="background" value="" /></li>
										<li><label for="radio">范围：</label>
										<input name="scope_start" value="" />-<input
								name="scope_end" value="" />
										</li>
										<li><input class="btn btn-blue"
								type="submit" value="保存" />
										</li>
									</ul>
								</form>
							</div>

						</div>
						<div class="btn-wrap">
							<table class="table table-auto">
								<tr>
									<td>名次</td>
									<td>父级</td>
									<td>背景</td>
									<td>范围上限</td>
									<td>范围下限</td>
									<td>创建时间</td>
									<td>操作</td>
								</tr>
								<c:forEach var="area" items="${areaList}">
									<tr>
										<td>${area.name}</td>
										<td>${area.pId }</td>
										<td>${area.background }</td>
										<td>${area.scope_start }</td>
										<td>${area.scope_end }</td>
										<td>${area.created_at}</td>
										<td><a href="<c:url value="/settings/area/delete/${area.id }"/>">删除</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
				<!-- mod-2 -->





			</div>
		</div>
		<!-- mod-1 -->

	</div>
	<!-- //content -->

</div>
<!-- //container -->
</body>
</html>