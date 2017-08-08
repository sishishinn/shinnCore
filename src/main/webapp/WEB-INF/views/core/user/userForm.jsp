<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="Flat, Clean, Responsive, admin template built with bootstrap 3">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title>Cameo | Responsive Admin Dashboard</title>
    <link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
    
    <%@include file="/common/header/CameoHeader.jsp" %>
    <!-- bootstrap -->
	<link href="${ctx}/static/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="${ctx}/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<link href="${ctx}/static/bootstrap/3.3.7/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    
</head>

<body>
    <div class="app" data-sidebar="locked">
        <%@include file="/common/header/DashBoardHeader.jsp" %>
        <section class="layout">

			<%@include file="/common/sidebar/DashBoardSidebar.jsp" %>

            <section class="main-content">
                <div class="content-wrap">
                	<div class="row">
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
						    <section class="panel">
						        <header class="panel-heading">Form User</header>
						        <div class="panel-body">
						            <form class="form-horizontal bordered-group" id="formId" action="" enctype="multipart/form-data" method="post">
						            	<c:if test="${not empty entity.id}">
						            		<input type="hidden" id="id" name="id" value="${entity.id}"> 
						            	</c:if>
						                <div class="form-group">
						                    <label class="col-sm-2 control-label">UserName</label>
						                    <div class="col-sm-10">
						                        <input type="text" class="form-control" id="name" name="name" value="${entity.name }" placeholder="UserName">
						                    </div>
						                </div>
						                <div class="form-group">
						                    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
						                    <div class="col-sm-10">
						                        <input type="password" class="form-control" id="password" name="password" value="${entity.password }" placeholder="Password" readonly onfocus="this.removeAttribute('readonly');this.removeAttribute('style');" style="background-color: white">
						                    </div>
						                </div>
						                <div class="form-group">
                                            <label class="col-sm-2 control-label">Role</label>
                                            <div class="col-sm-10">
                                                <select class="form-control selectpicker" name="roleId" id="roleId">
                                                	<option value="" <c:if test="${empty entity.role}">selected="selected"</c:if>>请选择角色</option>
                                                	<c:forEach var="role" items="${roleList}">
                                                    	<option value="${role.id }" <c:if test="${role.id == entity.role.id}">selected="selected"</c:if>>${role.name }</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
						                <button type="button" class="btn btn-default back">Back</button>
                                        <button type="button" class="btn btn-default save">Submit</button>
						            </form>
						        </div>
						    </section>
						</div>
                    </div>
                </div>
            </section>

        </section>
    </div>
<script type="text/javascript">
$(".back").click(function(){
	window.location.href = "${ctx}/user";
});
$(".save").click(function(){
	var formData = $("#formId").serialize();
	$.ajax({
		url:'${ctx}/user/add',
		type:'post',
		data:formData,
		dataType:'json',
		success:function(data){
			if ("ok" == data.state) {
				window.location.href = "${ctx}/user";
			}else{
				alert(data.msg);
			}
		},
		error:function(data){
		}
	});
});
</script>
</body>
</html>