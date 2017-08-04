<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="Flat, Clean, Responsive, admin template built with bootstrap 3">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title>Cameo | Responsive Admin Dashboard</title>
    <%@include file="/common/header/CameoHeader.jsp" %>
</head>

<body>
    <div class="app" data-sidebar="locked">
        <header class="header header-fixed navbar">
            <div class="brand">
                <a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>
                <a href="index.html" class="navbar-brand">
                    <i class="fa fa-stop mg-r-sm"></i>
                    <span class="heading-font">
					Cameo<b>ADMIN</b>
					</span>
                </a>
            </div>
            <ul class="nav navbar-nav navbar-right off-right">
                <li class="hidden-xs">
                    <a href="javascript:;">
					+Gerald Theodore Morris
					</a>
                </li>
                <li class="notifications dropdown hidden-xs">
                    <a href="javascript:;" data-toggle="dropdown">
                        <i class="fa fa-bell"></i>
                        <div class="badge badge-top bg-danger animated flash">3</div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right animated slideInRight">
                        <div class="panel bg-white no-border no-margin">
                            <div class="panel-heading no-radius">
                                <small>
								<b>Notifications</b>
								</small>
                                <small class="pull-right">
								<a href="javascript:;" class="mg-r-xs">mark as read</a>&#8226;
								<a href="javascript:;" class="fa fa-cog mg-l-xs"></a>
								</small>
                            </div>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <a href="javascript:;">
                                        <span class="pull-left mg-t-xs mg-r-md">
										<img src="${ctx}/static/Cameo/img/face4.jpg" class="avatar avatar-sm img-circle" alt="">
										</span>
                                        <div class="m-body show pd-t-xs">
                                            <span>Dean Winchester</span>
                                            <span>Posted on to your wall</span>
                                            <small>2 mins ago</small>
                                        </div>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">
                                        <span class="pull-left mg-t-xs mg-r-md">
											<span class="fa-stack fa-lg">
											<i class="fa fa-circle fa-stack-2x text-warning"></i>
											<i class="fa fa-download fa-stack-1x fa-inverse"></i>
											</span>
                                        </span>
                                        <div class="m-body show pd-t-xs">
                                            <span>145 MB download in progress.</span>
                                            <div class="progress progress-xs mg-t-xs mg-b-xs">
                                                <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                                </div>
                                            </div>
                                            <small>Started 23 mins ago</small>
                                        </div>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <a href="javascript:;">
                                        <span class="pull-left mg-t-xs mg-r-md">
											<img src="${ctx}/static/Cameo/img/face3.jpg" class="avatar avatar-sm img-circle" alt="">
											</span>
                                        <div class="m-body show pd-t-xs">
                                            <span>Application</span>
                                            <span>Where is my workspace widget</span>
                                            <small>5 days ago</small>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                            <div class="panel-footer no-border">
                                <a href="javascript:;">See all notifications</a>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="quickmenu">
                    <a href="javascript:;" data-toggle="dropdown">
                        <img src="${ctx}/static/Cameo/img/avatar.jpg" class="avatar pull-left img-circle" alt="user" title="user">
                        <i class="caret mg-l-xs hidden-xs no-margin"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right mg-r-xs">
                        <li>
                            <a href="javascript:;">
                                <div class="pd-t-sm">
                                    gerald@morris.com
                                    <br>
                                    <small class="text-muted">4.2 MB of 51.25 GB used</small>
                                </div>
                                <div class="progress progress-xs no-radius no-margin mg-b-sm">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="profile.html">Settings</a>
                        </li>
                        <li>
                            <a href="javascript:;">Upgrade</a>
                        </li>
                        <li>
                            <a href="javascript:;">Notifications
							<div class="badge bg-danger pull-right">3</div>
							</a>
                        </li>
                        <li>
                            <a href="javascript:;">Help ?</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="signin.html">Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </header>
        <section class="layout">

            <aside class="sidebar canvas-left">
                <nav class="main-navigation">
                    <ul>
                        <li class="active">
                            <a href="index.html">
                                <i class="fa fa-coffee"></i>
                                <span>Framework</span>
                            </a>
                        </li>
                        <li class="dropdown show-on-hover">
                            <a href="javascript:;" data-toggle="dropdown">
                                <i class="fa fa-ellipsis-h"></i>
                                <span>UI Elements</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="buttons.html">
                                        <span>Buttons</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="forms.html">
                                        <span>Forms</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="tables.html">
                                        <span>Tables</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="calendar.html">
                                        <span>Calendar</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="components.html">
                                        <span>Components</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="sortable.html">
                                        <span>Sortable</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="chart.html">
                                        <span>Charts</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="editor.html">
                                        <span>Editor</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="maps.html">
                                        <span>Google Maps</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="vector.html">
                                        <span>Vector Maps</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="widgets.html">
                                        <span>Widgets</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="icons.html">
                                        <span>Icons</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="grid.html">
                                        <span>Grid</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown show-on-hover">
                            <a href="javascript:;" data-toggle="dropdown">
                                <i class="fa fa-tasks"></i>
                                <span>Layouts</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="boxed.html">
                                        <span>Boxed</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="horizontal.html">
                                        <span>Horizontal menu</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="horizontal_boxed.html">
                                        <span>Horizontal Boxed</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="small-sidebar.html">
                                        <span>Small sidebar</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="right-sidebar.html">
                                        <span>Right Sidebar</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="right-sidebar-collapsible.html">
                                        <span>Right Sidebar collapsible</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="both.html">
                                        <span>Mixed menus</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="collapsible.html">
                                        <span>Collapsible Menu</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="footer.html">
                                        <span>With Footer</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="mail.html">
                                <div class="badge bg-none pull-right">8</div>
                                <i class="fa fa-envelope"></i>
                                <span>Mailbox</span>
                            </a>
                        </li>
                        <li class="dropdown show-on-hover">
                            <a href="javascript:;" data-toggle="dropdown">
                                <i class="fa fa-file"></i>
                                <span>Pages</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="tasks.html">
                                        <span>Tasks</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="profile.html">
                                        <span>Profile</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="invoice.html">
                                        <span>Invoice</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="gallery.html">
                                        <span>Gallery</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="signin.html">
                                        <span>Signin</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="signup.html">
                                        <span>Signup</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="lock.html">
                                        <span>Lock Screen</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="404.html">
                                        <span>404 Page</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="500.html">
                                        <span>500 Page</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="blank.html">
                                        <span>Blank</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown show-on-hover">
                            <a href="javascript:;" data-toggle="dropdown">
                                <i class="fa fa-gift"></i>
                                <span>Extras</span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="pricing.html">
                                        <span>Pricing Tables</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="mail_alt.html">
                                        <span>Mail Alt.</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="email.html">
                                        <span>Email Template</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="subscription.html">
                                        <span>Subscription</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="timeline.html">
                                        <span>Timeline</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="feed.html">
                                        <span>Feed</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="chat.html">
                                        <span>Chat</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="changelog.html">
                                <i class="fa fa-info"></i>
                                <span>Change log</span>
                            </a>
                        </li>
                    </ul>
                </nav>
                <footer>
                    <div class="about-app pd-md animated pulse">
                        <a href="javascript:;">
                            <img src="${ctx}/static/Cameo/img/about.png" alt="">
                        </a>
                        <span>
						<b>Cameo</b>&#32;is a responsive admin template powered by bootstrap 3.
						<a href="javascript:;">
						<b>Find out more</b>
						</a>
						</span>
                    </div>
                    <div class="footer-toolbar pull-left">
                        <a href="javascript:;" class="pull-left help">
                            <i class="fa fa-question-circle"></i>
                        </a>
                        <a href="javascript:;" class="toggle-sidebar pull-right hidden-xs">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </div>
                </footer>
            </aside>

            <section class="main-content">
                <div class="content-wrap">
                </div>
            </section>

        </section>
    </div>
</body>

</html>