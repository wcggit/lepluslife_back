<%--
  Created by IntelliJ IDEA.
  User: lss
  Date: 16/7/25
  Time: 下午2:45
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="merchantUrl" value="http://www.lepluslife.com/lepay/merchant/"></c:set>
<%@include file="../commen.jsp" %>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>乐+生活 后台模板管理系统</title>
    <link href="${resourceUrl}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${resourceUrl}/css/daterangepicker-bs3.css">
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/commonCss.css"/>
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/jquery.page.css"/>
    <style>
        thead th, tbody td {
            text-align: center;
        }

        table tr td img {
            width: 80px;
            height: 80px;
        }

        .table > thead > tr > td, .table > thead > tr > th {
            line-height: 40px;
        }

        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th {
            line-height: 60px;
        }

        .modal-body .main {
            width: 345px;
            height: 480px;
            margin: 0 auto;
            background: url("${resourceUrl}/images/lefuma.png") no-repeat;
            background-size: 100% 100%;
            position: relative;
        }

        .modal-body #myShowImage {
            width: 345px;
            height: 480px;
            position: absolute;
            top: 20px;
            left: 0;
            right: 0;
            margin: auto;
            display: none;
        }

        .modal-body .main .rvCode {
            width: 230px;
            height: 230px;
            position: absolute;
            top: 120px;
            left: 0;
            right: 0;
            margin: auto;
        }

        .modal-body .main .shopName {
            text-align: center;
            font-size: 24px;
            color: #fff;
            position: absolute;
            top: 365px;
            left: 0;
            right: 0;
            margin: auto;
            font-family: Arial;
        }
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${resourceUrl}/js/jquery-2.0.3.min.js"></script>
    <script src="${resourceUrl}/js/jquery.page.js"></script>
    <script type="text/javascript" src="${resourceUrl}/js/html2canvas.js"></script>
    <script src="${resourceUrl}/js/bootstrap.min.js"></script>
    <script src="${resourceUrl}/js/daterangepicker.js"></script>
    <%--<script src="${resourceUrl}/js/bootstrap-datetimepicker.zh-CN.js"></script>--%>
    <script src="${resourceUrl}/js/moment.min.js"></script>

</head>

<body>
<div id="topIframe">
    <%@include file="../common/top.jsp" %>
</div>
<div id="content">
    <div id="leftIframe">
        <%@include file="../common/left.jsp" %>
    </div>
    <div class="m-right">
        <div class="main">
            <div class="container-fluid" style="padding-top: 20px">
                <div class="row" style="margin-bottom: 30px">
                </div>
                <div class="form-group col-md-3"></div>
            </div>
            <ul id="myTab1" class="nav nav-tabs">
                <li><a href="#lunbotu" data-toggle="tab" onclick="showRole()">角色管理</a></li>
                <li><a href="#lunbotu" data-toggle="tab" onclick="showManageUser()">账号管理</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="lunbotu">
                    <a type="button" class="btn btn-primary btn-create" style="margin:10px;"
                       href="/manage/userCreatePage"/>新建账号</a>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <c:if test="${roleMap.list.size()!=0&&roleMap!=null}">
                            <tr class="active">
                                <th>角色名字</th>
                                <th>操作</th>
                            </tr>

                            <c:forEach items="${roleMap.list}" var="ManageRole">
                                <tr>
                                    <td>${ManageRole.roleName}</td>
                                    <td>
                                        <button type="button" class="btn btn-default"
                                                onclick="serchRoleManage(${ManageRole.id})">查看角色权限
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                onclick="deleteRole(${ManageRole.id})">删除角色
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${managementUserMap.managementUserList.size()!=0&&managementUserMap!=null}">
                            <tr class="active">
                                <th>账号名字</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach items="${managementUserMap.managementUserList}"
                                       var="ManageUser">
                                <tr>
                                    <td>${ManageUser.name}</td>
                                    <td>
                                        <button type="button" class="btn btn-default"
                                                onclick="serch(${ManageUser.id})">查看账号角色
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                onclick="edit(${ManageUser.id})">修改账号角色
                                        </button>
                                        <button type="button" class="btn btn-default"
                                                onclick="deletManageUser(${ManageUser.id})">删除账号
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>

                        </c:if>


                        </thead>
                        <tbody id="merchantContent">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="tcdPageCode" style="display: inline;">
            </div>
        </div>
    </div>
</div>
</div>
<div id="bottomIframe">
    <%@include file="../common/bottom.jsp" %>
</div>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${resourceUrl}/js/bootstrap.min.js"></script>
<script>
    function serchRoleManage(id) {

        location.href = "/manage/roleManange?id=" + id;

    }
    function deleteRole(id) {
        location.href = "/manage/deleteRole?id=" + id;
    }
    function showRole() {
        location.href = "/manage/roleList";
    }
    function showManageUser() {
        location.href = "/manage/managementUserList";
    }
    function serch(id) {
        location.href = "/manage/serchUserRole?id=" + id;
    }
    function edit(id) {
        location.href = "/manage/editUserRolePage?id=" + id;
    }
    function deletManageUser(id) {
        location.href = "/manage/deleteUser?id=" + id;
    }

</script>
</body>
</html>


