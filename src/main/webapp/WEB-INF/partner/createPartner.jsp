<%--
  Created by IntelliJ IDEA.
  User: wcg
  Date: 16/7/21
  Time: 下午3:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/commonCss.css"/>
    <style>
        .thumbnail {
            width: 160px;
            height: 160px;
        }

        .thumbnail img {
            width: 100%;
            height: 100%;
        }
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${resourceUrl}/js/jquery-2.0.3.min.js"></script>
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
            <div class="container-fluid">
                <a type="button" class="btn btn-primary btn-return" style="margin:10px;"
                   href="/manage/partner">返回合伙人列表</a>
                <hr>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="partnerManager" class="col-sm-2 control-label">合伙人管理员</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="partnerManager">
                                <c:forEach items="${partnerManagers}" var="partnerManager">
                                    <option value="${partnerManager.id}">${partnerManager.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">合伙人姓名</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="partnerName"
                                   value="${partner.partnerName}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系手机号</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="partnerPhone"
                                   value="${partner.phoneNumber}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">锁定店铺上限</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="merchantLimit"
                                   value="${partner.merchantLimit}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">锁定会员上限</label>

                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="userLimit"
                                   value="${partner.userLimit}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cooperate-style" class="col-sm-2 control-label">结算方式</label>

                        <div class="col-sm-4" id="cooperate-style">
                            <div class="radio-inline">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optionsRadios1"
                                           value="0" checked>
                                    银行卡结算
                                </label>
                            </div>
                            <div class="radio-inline">
                                <label>
                                    <input type="radio" name="optionsRadios" id="optionsRadios2"
                                           value="1">
                                    支付宝结算
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="bank">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">银行卡号</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="bankNumber">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">开户支行</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="bankName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收款人</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="bankPayee">
                            </div>
                        </div>
                    </div>
                    <div class="zhifubao">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">支付宝账号</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="alipayNumber">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">收款人</label>

                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="alipayPayee">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">每日发福利次数</label>

                        <div class="col-sm-4">
                            <input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" class="form-control" id="benefitTime"
                                   value="${partner.benefitTime}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="button" class="btn btn-primary" id="btnSubmit" onclick="submitPartner()">
                                提交
                            </button>
                            <a type="button" class="btn btn-primary btn-return" style="margin:10px;"
                               href="/manage/partner">取消</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="bottomIframe">
    <%@include file="../common/bottom.jsp" %>
</div>
<script src="${resourceUrl}/js/bootstrap.min.js"></script>
<script>
    $("#partnerManager").find("option[value='${partner.partnerManager.id}']").attr("selected",
                                                                                   true);
    if (${partner!=null}) {
        if ('${partner.bankName}' == '支付宝') {
            $('.bank').css({display: "none"});
            $('.zhifubao').css({display: "block"});
            $('#optionsRadios2').prop("checked", true);
            $("#alipayNumber").val('${partner.bankNumber}');
            $("#alipayPayee").val('${partner.payee}');
        } else {
            $('.bank').css({display: "block"});
            $('.zhifubao').css({display: "none"});
            $("#bankNumber").val('${partner.bankNumber}');
            $("#bankName").val('${partner.bankName}');
            $("#bankPayee").val('${partner.payee}');
        }
    } else {
        $('.zhifubao').css({display: "none"});
    }
    $('#optionsRadios1').on('click', function () {
        $('.bank').css({display: "block"});
        $('.zhifubao').css({display: "none"});
    })
    $('#optionsRadios2').on('click', function () {
        $('.bank').css({display: "none"});
        $('.zhifubao').css({display: "block"});
    })
    function submitPartner() {
        var partner = {};
        var partnerManager = {};
        partnerManager.id = $("#partnerManager").val();
        partner.partnerManager = partnerManager;
        if($("#partnerName").val()==null||$("#partnerName").val()==""){
            alert("请输入合伙人姓名");
            return;
        }
        if($("#partnerPhone").val()==null||$("#partnerPhone").val()==""){
            alert("请输入合伙人电话");
            return;
        }
        if($("#merchantLimit").val()==null||$("#merchantLimit").val()==""){
            alert("请输入商户限制");
            return;
        }
        if($("#userLimit").val()==null||$("#userLimit").val()==""){
            alert("请输入会员限制");
            return;
        }
        if($("#benefitTime").val()==null||$("#benefitTime").val()==""){
            alert("请输入每日发放限制");
            return;
        }
        partner.partnerName = $("#partnerName").val();
        partner.name = $("#partnerName").val();
        partner.phoneNumber = $("#partnerPhone").val();
        partner.merchantLimit = $("#merchantLimit").val();
        partner.userLimit = $("#userLimit").val();
        partner.benefitTime = $("#benefitTime").val();
        var payWay = $("input[name='optionsRadios']:checked").val();
        if (payWay == 0) {
            partner.bankNumber = $("#bankNumber").val();
            partner.payee = $("#bankPayee").val();
            partner.bankName = $("#bankName").val();
        } else {
            partner.bankNumber = $("#alipayNumber").val();
            partner.payee = $("#alipayPayee").val();
            partner.bankName = '支付宝';
        }
        if(partner.payee==null||partner.payee==""){
            alert("请输入收款人");
            return;
        }
        if(partner.bankNumber==null||partner.bankNumber==""){
            alert("请输入银行卡号");
            return;
        }
        if(partner.bankName==null||partner.bankName==""){
            alert("请输入银行");
            return;
        }
        if (${partner!=null}) {
            $("#btnSubmit").attr("disabled",true);
            partner.id = '${partner.id}';
            $.ajax({
                       type: "put",
                       url: "/manage/partner",
                       contentType: "application/json",
                       data: JSON.stringify(partner),
                       success: function (data) {
                           alert(data.msg);
                               location.href = "/manage/partner";
                       }
                   });
        }else{
            $("#btnSubmit").attr("disabled",true);
            $.ajax({
                       type: "post",
                       url: "/manage/partner",
                       contentType: "application/json",
                       data: JSON.stringify(partner),
                       success: function (data) {
                           alert(data.msg);
                               location.href = "/manage/partner";
                       }
                   });
        }

    }
</script>
</body>
</html>

