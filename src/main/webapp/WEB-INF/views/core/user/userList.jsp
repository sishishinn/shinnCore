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
	    
    <!-- jqgrid -->
	<script type="text/ecmascript" src="${ctx }/static/jqgrid/5.2/js/jquery.jqGrid.min.js"></script>
	<script type="text/ecmascript" src="${ctx }/static/jqgrid/5.2/js/i18n/grid.locale-en.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/jqgrid/5.2/css/ui.jqgrid-bootstrap.css" />
    <script>
		$.jgrid.defaults.responsive = true;
		$.jgrid.defaults.styleUI = 'Bootstrap';
	</script>
	<!-- 禁用水平滑动 -->
    <style type="text/css">
    	.ui-jqgrid .ui-jqgrid-bdiv{ overflow-x: hidden; }
    </style>
    
</head>

<body>
    <div class="app" data-sidebar="locked">
        <%@include file="/common/header/DashBoardHeader.jsp" %>
        <section class="layout">
            <%@include file="/common/sidebar/DashBoardSidebar.jsp" %>
            <section class="main-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="col-lg-12">
                        	<button type="button" class="btn btn-sm btn-default" onclick="add()"><i class="fa fa-plus"></i>&nbsp;add</button>
                        	<button type="button" class="btn btn-sm btn-default" onclick="edit()" id="editBut" disabled="disabled"><i class="fa fa-edit"></i>&nbsp;edit</button>
                        	<button type="button" class="btn btn-sm btn-default" onclick="del()" id="delBut" disabled="disabled"><i class="fa fa-trash-o"></i>&nbsp;del</button>
                        	<input type="text" class="no-border no-padding" id="keyword" style="height: 26px;width: 12%;border-radius: 2px;margin-left:1%"  placeholder="Search">
		                    <button type="button" class="btn btn-sm btn-default" title="搜索" onclick="search()"><i class="fa fa-search"></i></button>
                        	<button type="button" class="btn btn-sm btn-default" title="刷新" onclick="refresh()"><i class="fa fa-refresh"></i></button>
                        </div>
                        <div class="col-md-12">
                            <section class="panel">
                                <div class="panel-body no-padding">
                                    <div class="table-responsive" style="overflow-x:hidden">
                                    	<table class="table table-striped responsive" id="jqGrid"></table>
										<div id="jqGridPager"></div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </div>
            </section>
        </section>
    </div>
<script type="text/javascript">
$(document).ready(function () {
	$("#jqGrid").jqGrid({
		url: '${ctx}/core/user/loadData',
		colNames:[ '账号','角色', '创建时间','操作'],//指定了jqGrid每列的title，按顺序依次排列，并且可以看出实际上它就是一个字符串数组。
		colModel: [
	      			{ name: 'name',index: 'name', width: 90 ,formatter :function(cellvalue, options, rowObject) {return "<a href='javascript:jqGridedit(\""+rowObject.id+"\")' >"+cellvalue+"</a>";}},
	      			{ name: 'rolename',index: 'rolename', width: 100 },
	      			{ name: 'createtime',index: 'createtime', width: 100 },
	      			{ name: 'jump',index: 'jump', width: 80, sortable:false,
	      				formatter : function(cellvalue, options, rowObject){//jqgrid自定义格式化  
							var actionHtml = '<button class="btn btn-danger btn-xs" style="margin-right:3px" title="删除"  onclick="jqGriddel(\''+rowObject.id+'\')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 删除</button>';
							actionHtml +=    '<button class="btn btn-warning btn-xs" style="margin-right:3px" title="修改" onclick="jqGridedit(\''+rowObject.id+'\')"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> 修改</a>';
							return actionHtml;
						}
	      			}                   
		],//指定了jqGrid各列的具体格式，"name"指定对应数据中属性名，“index”用于列排序，“width”显然是指定列宽，“align”对齐方式 eg:align:"right",，“sortable”指定是否支持排序。
		datatype: "json",//指定了jqGrid调用的数据的格式，常用格式有json，xml，local。
		mtype:"post",//定义请求方式
		jsonReader:{
	        id: "id",//相当于设置主键
	        root : "rows", //Json数据
	        page : "pageIndex",//当前页
	        total : "pageCount",//总页数
	        records : "total",//总记录数
	        repeatitems : false//如果repeatitems为false，json 中数据可以乱序，并且允许数据空缺；   如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。注：cell、id在repeatitems为true时可以用到，即每一个记录是由一对id和cell组合而成，即可以适用另一种json结构。援引文档中的例子：
	    },
	    //caption : 'title',//制订了jqGrid的标题，如果设置了，则将显示在Grid的Header层。
	    height:'auto',//高度，表格高度。可为数值、百分比或'auto'
	  	autowidth:true,//自动宽
	    viewrecords: true,//是否在浏览导航栏显示记录总数
	    //rownumbers:true,//添加左侧行号
	    rowNum:20,//每页显示记录数
	    //rowList:[10,20,30],//用于改变显示行数的下拉列表框的元素数组。
	    //altRows:true,//设置为交替行表格,默认为false
	    //sortname:'createDate',//指定了jqGrid默认的排序列，可以是列名也可以是数字。
	    //sortorder:'asc',//指定了jqGrid默认排序列的默认排序方式。
	    //loadonce: true,// this is just for the demo
		pager: "#jqGridPager",//指定了jqGrid页脚显示位置。
		multiselect : true,//是否可以选择多行数据
		onSelectRow : function(rowid, status) {
			var arrrow = $("#jqGrid").jqGrid('getGridParam', 'selarrrow');
			if (arrrow.length == 1) {
				$("#editBut").prop("disabled", false);
				$("#delBut").prop("disabled", false);
			} else {
				$("#editBut").prop("disabled", true);
				if (arrrow.length == 0) {
					$("#delBut").prop("disabled", true);
				}
			}
		},
		onSelectAll : function(aRowids, status) {
			if (status == true) {
				$("#delBut").prop("disabled", false);
				$("#editBut").prop("disabled", true);
			} else {
				$("#delBut").prop("disabled", true);

			}
		}
	});
	// activate the build in search with multiple option
    $('#jqGrid').navGrid("#jqGridPager", {
        search: false, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: false,
    },
    {}, // edit options
    {}, // add options
    {}, // delete options
    { multipleSearch: true } // search options - define multiple search
    );
});

function add(){
	window.location.href = "${ctx}/core/user/form";
}

function edit(){
	var id = $("#jqGrid").jqGrid("getGridParam","selrow");
	if (!id) {
		alert("请选择一条记录");
	}else{
		window.location.href = "${ctx}/core/user/form?id="+id;
	}
}

function jqGridedit(id){
	window.location.href = "${ctx}/core/user/form?id="+id;
}

function del(){
	var arrrow = $("#jqGrid").jqGrid('getGridParam','selarrrow');
	if (arrrow.length==0) {
		alert("请至少选择一条记录");
	}else{
		$.ajax({
			url:'${ctx}/core/user/delete',
			type:'post',
			data:'ids='+arrrow.join(","),
			success:function(data){
				if ("ok" == data.state) {
					alert("删除成功");
					$('#jqGrid').trigger('reloadGrid');
				}else{
					
				}
			}
		});
	}
}

function jqGriddel(id){
	$.ajax({
		url:'${ctx}/core/user/delete',
		type:'post',
		data:'ids='+id,
		success:function(data){
			if ("ok" == data.state) {
				alert("删除成功");
				$('#jqGrid').trigger('reloadGrid');
			}else{
				
			}
		}
	});
}

function search(){
	var keyword = $("#keyword").val();
	$("#jqGrid").setGridParam({
		postData : {
			'keyword' : keyword,
		},
		page : 1
	});
	$("#jqGrid").trigger("reloadGrid");
	$("#keyword").val("");
}

function refresh(){
	search();
}
</script>    
</body>
</html>