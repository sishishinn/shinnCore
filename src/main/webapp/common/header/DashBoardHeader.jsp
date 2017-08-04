<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<header class="header header-fixed navbar">
	<div class="brand" style="background-color: #802dcb">
		<a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>
		<a href="${ctx }/mushi/web/welcome/welcome.action" class="navbar-brand">
			<i class="fa fa-stop mg-r-sm"></i>
			<span class="heading-font">Mushi <b>ADMIN</b></span>
		</a>
	</div>
	<ul class="nav navbar-nav navbar-right off-right">
		
		<li class="notifications dropdown hidden-xs">
			<a href="javascript:;" data-toggle="dropdown" style="height: 50px">
		 		<i class="fa fa-bell" style="padding-top:7px"></i>
	        	<div class="badge badge-top bg-danger animated flash">7</div>
	    	</a>
		</li>
		
		<li class="quickmenu">
		    <a href="javascript:;" data-toggle="dropdown" style="height: 50px">
				<img src="${ctx}/static/Cameo/img/avatar.jpg" class="avatar pull-left img-circle" alt="user" title="user">
				<i class="caret mg-l-xs hidden-xs no-margin"></i>
			</a>
			<ul class="dropdown-menu dropdown-menu-right mg-r-xs">
			    <li>
			        <a href="profile.html">Settings</a>
			    </li>
			    <li>
			        <a href="javascript:;">Upgrade</a>
			    </li>
			    <li>
			        <a href="javascript:;">Help ?</a>
			    </li>
			    <li class="divider"></li>
			    <li>
			        <a href="${ctx }/logout">Logout</a>
				</li>
			</ul>
		</li>
		
	</ul>
</header>