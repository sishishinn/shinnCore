<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<aside class="sidebar canvas-left">
    <nav class="main-navigation">
        <ul id="siderUl">
            <li class="">
                <a href="${ctx}/core/web/user/user.action"><i class="fa fa-coffee"></i><span>用户管理</span></a>
            </li>
            <li>
                <a href="${ctx}/core/web/role/role.action"><i class="fa fa-tasks"></i><span>角色管理</span></a>
            </li>
            <li>
                <a href="${ctx}/core/web/template/template!toCore.action"><i class="fa fa-ellipsis-h"></i><span>模版管理</span></a>
            </li>
            <li>
                <a href="javascript:;"><i class="fa fa-envelope"></i><span>管理菜单</span></a>
            </li>
            <li>
                <a href="javascript:;"><i class="fa fa-file"></i><span>管理菜单</span></a>
            </li>
            <li>
                <a href="javascript:;"><i class="fa fa-gift"></i><span>管理菜单</span></a>
            </li>
            <li>
                <a href="javascript:;"><i class="fa fa-info"></i><span>管理菜单</span></a>
            </li>
        </ul>
    </nav>

	<footer>
        <div class="footer-toolbar pull-left">
            <a href="javascript:;" class="toggle-sidebar pull-right hidden-xs">
                <i class="fa fa-angle-left"></i>
            </a>
        </div>
    </footer>
<script>
    var pathname = location.pathname;
    var paths = pathname.split("!");
    var as = document.getElementById("siderUl").getElementsByTagName('a');
    for (var i = 0, j = as.length; i < j; i++)
        if (as[i].href.indexOf(pathname) != -1) {
        	as[i].parentNode.className = 'active';
        } else if(as[i].href.indexOf(paths[0]) != -1){
        	as[i].parentNode.className = 'active';
        }else{
        	as[i].parentNode.className = '';
        }
</script>
</aside>