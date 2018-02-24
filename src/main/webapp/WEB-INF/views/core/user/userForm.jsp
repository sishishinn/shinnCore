<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs/taglibs.jsp" %>
<!DOCTYPE HTML>
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
    <script src="${ctx}/static/laydate/laydate.js"></script>
    <link rel="stylesheet" href="${ctx}/static/layui/css/layui.css" media="all">
    <script src="${ctx}/static/layui/layui.js"></script>
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
						            	<c:if test="${not empty user.id}">
						            		<input type="hidden" id="id" name="id" value="${user.id}"> 
						            	</c:if>
						                <div class="form-group">
						                    <label class="col-sm-2 control-label">UserName</label>
						                    <div class="col-sm-10">
						                        <input type="text" class="form-control" id="name" name="name" value="${user.name }" placeholder="UserName">
						                    </div>
						                </div>
						                <div class="form-group">
						                    <label for="inputPassword" class="col-sm-2 control-label">Password</label>
						                    <div class="col-sm-10">
						                        <input type="password" class="form-control" id="password" name="password" value="${user.password }" placeholder="Password" readonly onfocus="this.removeAttribute('readonly');this.removeAttribute('style');" style="background-color: white">
						                    </div>
						                </div>
						                <div class="form-group">
						                    <label class="col-sm-2 control-label">Age</label>
						                    <div class="col-sm-10">
						                        <input type="text" class="form-control" id="age" name="age" value="${user.age }" placeholder="age">
						                    </div>
						                </div>
						                <div class="form-group">
						                    <label class="col-sm-2 control-label">Date</label>
						                    <div class="col-sm-10">
						                        <input type="text" class="form-control" id="birthday" name="birthday" value="<fmt:formatDate value='${user.birthday}' pattern='yyyy-MM-dd HH:mm'/>" placeholder="birthday">
						                    </div>
						                </div>
						                <div class="form-group">
                                            <label class="col-sm-2 control-label">Role</label>
                                            <div class="col-sm-10">
                                                <select class="form-control selectpicker" name="roleId" id="roleId">
                                                	<option value="" <c:if test="${empty user.role}">selected="selected"</c:if>>请选择角色</option>
                                                	<c:forEach var="role" items="${roleList}">
                                                    	<option value="${role.id }" <c:if test="${role.id == user.role.id}">selected="selected"</c:if>>${role.name }</option>
                                                	</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                        	<div class="layui-upload-list" id="imglist">
                                        	</div>
	                                        <button type="button" class="layui-btn" id="test1">
											  <i class="layui-icon">&#xe67c;</i>上传图片
											</button>
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
//图片上传
layui.use('upload', function(){
  var upload = layui.upload;
  //执行实例
  var uploadInst = upload.render({
    elem: '#test1' //绑定元素
    ,url: '${ctx}/core/upload/uploadImgs' //上传接口
    ,multiple:true
   	,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		layer.load(); //上传loading
	}
    ,done: function(res){
	   	//请求成功回调
    	if("ok"==res.state){
    		var filenameList = res.data.filenameList;
    		for(var i =0;i<filenameList.length;i++){
		    	$("#imglist").append('<img class="layui-upload-img" width="100px" id="" src="${ctx}/core/upload/showImg?filename='+res.data.filenameList[i]+'">');
		    	$("#imglist").append('<input type="hidden" name="img" value="'+res.data.filenameList[i]+'">');
    		}
    	}
    	layer.closeAll('loading'); //关闭loading
    }
    ,error: function(){
		//请求异常回调
		layer.closeAll('loading'); //关闭loading
    }
  });
});
//日期插件
laydate.render({
  elem: '#birthday',		//指定元素
  type: 'datetime',			//默认date
  format: 'yyyy-MM-dd HH:mm',
});

$(".back").click(function(){
	window.location.href = "${ctx}/core/user?backFlag=1";
});
$(".save").click(function(){
	var formData = $("#formId").serialize();
	$.ajax({
		url:'${ctx}/core/user/user',
		type:'post',
		data:formData,
		dataType:'json',
		success:function(data){
			if ("ok" == data.state) {
				window.location.href = "${ctx}/core/user?backFlag=1";
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