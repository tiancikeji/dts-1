<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../includes/head.jsp" %>

<div id="container" class="grid-elastic cf">

    <div id="sidebar" class="grid-220">
        <div class="menu">
            <h3>参数设置</h3>
            <div class="menu-content">
                <ul>
                    <li class="mactive"><a href="<c:url value="/settings/sensor" />">传感器设置</a></li>
                    <li><a href="<c:url value="/settings/area" />">厂区设置</a></li>
                    <li><a href="<c:url value="/settings/alert" />">报警设置</a></li>
                    <li><a href="<c:url value="/settings/communication" />">通讯设置</a></li>
                    <li><a href="<c:url value="/settings/system" />">系统设置</a></li>
                </ul>
            </div>
        </div>
    </div><!-- //sidebar -->

    <div id="content" class="wrapper">

        <div class="mod-1">
            <div class="hd-1 cf">
                <h2>通讯设置</h2>
            </div>
            <div class="bd-1">
                
                <div class="mod-2">
                    <div class="hd-2 cf">
                        <h2>光开关设置</h2>
                    </div>
                    <div class="bd-2">
                        <div class="wrap-cont">
                     <form action="<c:url value="/settings/addsensor"/>" method="post" >
                        <ul>
                        	<li><label for="radio">名称：</label>
								<input name="name" style=" width:74px" value="" />
							</li><br/>
							<li><input class="btn btn-blue" type="submit" value="保存" />
							</li>
                        </ul>
                      </form>
                     </div>
                        <div class="btn-wrap">
                            	<table   class="table table-auto">
								<tr>
									<td>名字</td>
									<td>操作</td>
								</tr>
								<c:forEach var="sensor" items="${sensorList}">
									<tr>
										<td>${sensor.name}</td>
										<!-- <td><a href="<c:url value="/settings/sensor/update/${sensor.id }"/>">修改</a></td>-->
										<td><a href="<c:url value="/settings/sensor/delete/${sensor.id }"/>">删除</a></td>
									</tr>
								</c:forEach>
							</table>
                        </div>
                    </div>
                </div><!-- mod-2 -->

                <div class="mod-2">
                    <div class="hd-2 cf">
                        <h2>模块设置</h2>
                    </div>
                    <div class="bd-2">
                        <div class="wrap-cont">
                     <form action="<c:url value="/settings/addchannel"/>" method="post" >
                        <ul>
                        	<li><label for="radio"> 设备：</label> 
										<select	name="sensor_id" class="select-2 ipt-f3"  style=" width:100px">
												<option value="0">无</option>
												<c:forEach var="sensor" items="${sensorList}">
													<option  value="${sensor.id }">${sensor.name }</option>
												</c:forEach>
										</select></li><br/>
                        	<li><label for="radio">名称：</label>
								<input name="channelname" style=" width:74px" value="" />
							</li><br/>
							<li><input class="btn btn-blue" type="submit" value="保存" />
							</li>
                        </ul>
                      </form>
                        </div>
                         <div class="btn-wrap">
                            	<table   class="table table-auto">
								<tr>
									<td>名字</td>
									<td>操作</td>
								</tr>
					<c:forEach var="channel" items="${channelList}">
								<tr>
								<td>${channel.channelname}</td>
								<td><a href="<c:url value="/settings/channel/delete/${channel.id }"/>">删除</a></td>
						</c:forEach>
							</table>
                        </div>
                    </div>
                </div><!-- mod-2 -->

                <div class="mod-2">
                    <div class="hd-2 cf">
                        <h2>转发设置</h2>
                    </div>
                    <div class="bd-2">
                        <div class="wrap-cont">
                            转发设置
                        </div>
                        <div class="btn-wrap">
                            <input class="btn btn-blue" type="button" value="保 存" />
                        </div>
                    </div>
                </div><!-- mod-2 -->

                <div class="mod-2">
                    <div class="hd-2 cf">
                        <h2>报警联动</h2>
                    </div>
                    <div class="bd-2">
                        <div class="wrap-cont">
                            报警联动
                        </div>
                        <div class="btn-wrap">
                            <input class="btn btn-blue" type="button" value="保 存" />
                        </div>
                    </div>
                </div><!-- mod-2 -->

            </div>
        </div><!-- mod-1 -->

    </div><!-- //content -->   

</div><!-- //container -->
</body>
</html>