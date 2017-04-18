<%--
  Created by IntelliJ IDEA.
  User: wcg
  Date: 16/6/3
  Time: 上午10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../commen.jsp" %>
<!DOCTYPE>
<html>
<style>
    .lianmeng {
        margin-top: 10px;
        margin-left: 10%;
    }
    .lianmeng > div {
        margin: 5px 0;
    }
    .lianmeng input {
        width: 30%;
        margin: 0 5px;
    }
</style>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>乐+生活 后台模板管理系统</title>
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/commonCss.css"/>
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/openshop.css"/>
    <link type="text/css" rel="stylesheet" href="${resourceUrl}/css/combo.select.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="html5shiv.min.js"></script>
    <script src="respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${resourceUrl}/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${resourceUrl}/js/vendor/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${resourceUrl}/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="${resourceUrl}/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="${resourceUrl}/js/jquery.combo.select.js"></script>
</head>
<style>
    .combo-select {
        width: 70% !important;
        border:0 !important;
    }
    .combo-select input {
        width: 100% !important;
    }
</style>
<body>
<div id="topIframe">
    <%@include file="../common/top.jsp" %>
</div>
<div id="content">
    <div id="leftIframe">
        <%@include file="../common/left.jsp" %>
    </div>
    <div class="m-right">
        <p>
            <button onclick="goMerchantPage()">返回商户管理</button>
            新建商户
        </p>
        <div>
            <label for="merchantUser" style="width:10%">所属商户</label>
            <select name="name" id="merchantuser" class="check">
                <option value="">- 请选择 -</option>
                <c:forEach items="${merchantUsers}" var="merchantUser">
                    <option value="${merchantUser.id}">${merchantUser.merchantName}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="partner">所属合伙人</label>
            <select name="name" id="partners" class="check">
                <c:forEach items="${partners}" var="partner">
                    <option value="${partner.id}">${partner.name}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="sales">所属销售</label>
            <c:if test="${salesStaff ==null}">
                <select name="name" id="sales" class="check">
                    <option value="nullValue"></option>
                    <c:forEach items="${sales}" var="saleStaff">
                        <option value="${saleStaff.id}">${saleStaff.name}</option>
                    </c:forEach>
                </select>
            </c:if>
            <c:if test="${salesStaff !=null}">
                <select name="name" id="sales" class="check">
                    <option value="nullValue"></option>
                    <option value="${salesStaff.id}">${salesStaff.name}</option>
                    <c:forEach items="${sales}" var="saleStaff">
                        <option value="${saleStaff.id}">${saleStaff.name}</option>
                    </c:forEach>
                </select>
            </c:if>
        </div>


        <div>
            <label for="shopname">店铺名称&nbsp</label>
            <input value="${merchant.name}" type="text" id="shopname" class="check"
                   placeholder="请输入店铺名称，最长不超过16位"/>
        </div>
        <div>
            <label>所在城市&nbsp</label>
            <select id="locationCity" class="check">
                <%--<option value="1">鞍山</option>--%>
            </select>
        </div>
        <div>
            <label>所在区域&nbsp</label>
            <select id="locationArea" class="check">
                <%--<option value="1">xxx商圈</option>--%>
            </select>
        </div>
        <div>
            <label for="address">详细地址&nbsp</label>
            <input value="${merchant.location}" type="text" id="address" class="check"/>
        </div>
        <div>
            <label>店铺类型&nbsp</label>
            <select id="merchantType" class="check">
                <c:forEach items="${merchantTypes}" var="merchantType">
                    <option value="${merchantType.id}">${merchantType.name}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <label for="contact">商户联系人</label>
            <input value="${merchant.contact}" type="text" id="contact" class="check"/>
        </div>
        <div>
            <label for="phone">绑定手机号</label>
            <input type="text" id="phone" class="check" value="${merchant.merchantPhone}"/>
        </div>
        <div>
            <label>合约类型&nbsp</label>
            <input type="radio" class="radio" id="normal" name="type" value="0"/><span>普通商户</span>
            <input type="radio" class="radio" id="partner" name="type" value="1"/><span>联盟商户</span>
            <div class="pt" style="margin-left: 10%">
                <label>手续费</label>
                <input type="text" class="money pt"  placeholder="输入手续费" name="pt-ljCommission"/>%
                <label>积分返利点</label>
                <input type="text" id="fl" value="${merchant.scoreBRebate}" class="money check"
                       placeholder="按交易额百分比，返给用户" name="pt-scoreBRebate"/>&nbsp%
            </div>
            <div class="lianmeng">
                <style>
                    .lianmeng .form-control {
                        width: 20%;
                        display: inline;
                    }
                </style>
                <div>
                    <span>普通订单费率</span><input type="text" class="form-control" name="lm-ljBrokerage" /><span>%</span><span>积分返点</span><input type="text" class="form-control" name="lm-scoreBRebate"/><span>%</span>
                </div>
                <div>
                    <span>导流订单费率</span><input type="text" class="form-control" name="lm-ljCommission"/><span>%</span><span>红包比例</span><input type="text" class="form-control" name="lm-scoreARebate"/><span>%</span><span>积分返点</span><input type="text" class="form-control" name="lm-importScoreBScale"/><span>%</span><br>
                </div>
                <div>
                    <span>会员订单费率：</span><input type="radio" style="width: auto !important;" name="hydd" checked="checked" id="yj-hydd" value="0"/><span>佣金费率</span><input type="radio" style="width: auto !important;" name="hydd" id="pt-hydd" value="1"/><span>普通费率</span>
                    <div class="yj">
                        <div>
                            <input type="radio" style="width: auto !important;" name="policy" id="q-policy" value="1"/><span>成本差全数发放</span><span>（佣金和微信手续费成本的差额，将全部用来发放红包）</span><span>积分返点</span><input type="number" name="lm-userScoreBScaleB" class="form-control" /><span>%</span>
                        </div>
                        <div>
                            <input type="radio" style="width: auto !important;" name="policy" id="b-policy" value="0" checked="checked" /><span>按比例发放</span><span>红包比例</span><input type="number" name="lm-userScoreAScale" class="form-control" /><span>%</span><span>积分返点</span><input type="number" name="lm-userScoreBScale" class="form-control" /><span>%</span><br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <label for="lock">锁定上限&nbsp</label>
            <input type="text" id="lock" value="${merchant.userLimit}" class="check"
                   placeholder="请输入锁定会员的名额上限，不能低于当前已锁定会员"/>
        </div>
        <div>
            <label>红包收取权限</label>
            <input type="radio" id="open" class="radio" name="type1" value="0"
                   checked/><span>未开通</span>
            <input type="radio" id="close" class="radio" name="type1" value="1"/><span>已开通</span>
        </div>
        <div class="pay">
            <label>结算方式&nbsp</label>
            <input type="radio" class="radio" id="alipay" name="pay" value="0"/><span>支付宝结算</span>
            <input type="radio" class="radio" id="bankPay" name="pay" value="1"/><span>银行卡结算</span>

            <div class="zfbpay dis">
                <div>
                    <label for="card">支付宝账号</label>
                    <input type="text" id="zfb"/>
                </div>
                <div>
                    <label for="payee">收款人</label>
                    <input type="text" id="skr"/>
                </div>
                <div>
                    <label for="zfbzhouqi">结算周期</label>
                    <select name="name" id="zfbzhouqi" class="check">
                        <option value="1">T+1</option>
                        <option value="2">T+2</option>
                    </select>
                </div>
            </div>
            <div class="cardpay dis">
                <div>
                    <label for="card">银行卡号</label>
                    <input type="text" id="card" class="money"/>
                </div>
                <div>
                    <label for="bank">开户支行</label>
                    <select id="selBankName">
                        <option value=""> -- 请选择 --</option>
                        <option value="1">中国农业银行</option>
                        <option value="2">中国工商银行</option>
                        <option value="3">中国银行</option>
                        <option value="4">交通银行</option>
                        <option value="5">中信银行</option>
                        <option value="6">中国光大银行</option>
                        <option value="7">华夏银行</option>
                        <option value="8">中国民生银行</option>
                        <option value="9">广发银行股份有限公司</option>
                        <option value="10">中国邮政储蓄银行</option>
                        <option value="11">平安银行</option>
                        <option value="12">兴业银行</option>
                        <option value="13">上海浦东发展银行</option>
                        <option value="14">中国建设银行</option>
                        <option value="15">鞍山市商业银行</option>
                        <option value="16">营口银行</option>
                        <option value="17">农村信用社</option>
                        <option value="18">锦州银行</option>
                        <option value="19">北京农村商业银行</option>
                        <option value="20">吉林银行</option>
                        <option value="21">盛京银行</option>
                        <option value="22">大连银行</option>
                    </select>
                    <div>
                        <input type="text" id="bank" style="width:180px"/>
                    </div>
                </div>
                <div>
                    <label for="payee">收款人</label>
                    <input type="text" id="payee"/>
                </div>
                <div>
                    <label for="cardzhouqi">结算周期</label>
                    <select name="name" id="cardzhouqi" class="check">
                        <option value="1">T+1</option>
                        <option value="2">T+2</option>
                    </select>
                </div>
            </div>
        </div>
        <div>
            <label>结算协议&nbsp(非必填)</label>
            <c:if test="${merchant.merchantProtocols!=null}">
                <c:forEach items="${merchant.merchantProtocols}" var="merchantProtocol">
                    <div id="0">
                        <div>
                            <div><img id="preview0" src="${merchantProtocol.picture}"
                                      class="fileimg"></div>
                            <div><input class="picture" type="file"
                                        data-url="/manage/file/saveImage"
                                        id="doc0" name="file" style="width: 110px;">
                                <button onclick="deleteImg(0);" style="background-color: red;">X
                                </button>
                                <div>
                                    <button class="downLoad">下载协议</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <div class="img">
                <div class="imgAdd">+</div>
            </div>
        </div>
        <div>
            <button id="enter">确认创建</button>
            <%--<button>取消创建</button>--%>
        </div>
    </div>
</div>
<input type="hidden" id="merchantId" value="${merchant.id}"/>
<input type="hidden" id="merchantBankId" value="${merchant.merchantBank.id}"/>
<input type="hidden" id="merchantRebatePolicyId" value="${merchantRebatePolicy.id}"/>
<div id="bottomIframe">
    <%@include file="../common/bottom.jsp" %>
</div>
</body>
<%--<script src="js/jquery-2.0.3.min.js"></script>--%>
<script type="text/javascript" language="javascript">
    $('#merchantuser').comboSelect();
    $(".lianmeng").hide();
    if (${merchant!=null}) {
        $.ajax({
            type: 'GET',
            url: '/manage/city/ajax',
            async: false,
            dataType: 'json',
            success: function (data) {
                console.log(data[0]);
                var dataStr1 = '',
                        dataStr2 = '';
                $.each(data, function (i) {
                    dataStr1 +=
                            '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                    if (data[i].id == '${merchant.city.id}') {
                        $.each(data[i].areas, function (j) {
                            dataStr2 +=
                                    '<option value="' + data[i].areas[j].id + '">'
                                    + data[i].areas[j].name
                                    + '</option>';
                        });
                    }
                });
                $('#locationCity').empty().append(dataStr1);
                $('#locationArea').empty().append(dataStr2);
            },
            error: function (jqXHR) {
                alert('发生错误：' + jqXHR.status);
            }
        });
        $("#locationCity option[value=${merchant.city.id}]").attr("selected", true);
        $("#locationArea option[value=${merchant.area.id}]").attr("selected", true);
    } else {
        $.ajax({
            type: 'GET',
            url: '/manage/city/ajax',
            async: false,
            dataType: 'json',
            success: function (data) {
                console.log(data[0]);
                var dataStr1 = '',
                        dataStr2 = '';
                $.each(data, function (i) {
                    dataStr1 +=
                            '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                });
                $.each(data[0].areas, function (j) {
                    dataStr2 +=
                            '<option value="' + data[0].areas[j].id + '">' + data[0].areas[j].name
                            + '</option>';
                });
                $('#locationCity').empty().append(dataStr1);
                $('#locationArea').empty().append(dataStr2);
            },
            error: function (jqXHR) {
                alert('发生错误：' + jqXHR.status);
            }
        });
    }
    $("#merchantType option[value=${merchant.merchantType.id}]").attr("selected", true);
    $('#locationCity').change(function () {
        var val = $(this).val();
        $.ajax({
            type: 'GET',
            url: '/manage/city/ajax',
            async: false,
            dataType: 'json',
            success: function (data) {
                $.each(data, function (i) {
                    if (data[i].id == val) {
                        var dataStr = '';
                        $.each(data[i].areas, function (j) {
                            dataStr +=
                                    '<option value="' + data[i].areas[j].id + '">'
                                    + data[i].areas[j].name + '</option>';
                        });
                        $('#locationArea').empty().append(dataStr);
                    }
                });
            },
            error: function (jqXHR) {
                alert('发生错误：' + jqXHR.status);
            }
        });
    })
    var fnMyFunc1;
    $(function () {
        $("#partners").find("option[value='${merchant.partner.id}']").attr("selected", true);
        $("#sales").find("option[value='${merchant.salesStaff.id}']").attr("selected", true);
        initProtocol();
        if(${merchant.merchantUser!=null}) {
            $("#merchantuser").val(${merchant.merchantUser.id});
            var i = "${merchant.merchantUser.merchantName}";
            $("#merchantuser").next().next().next().val(i);
        }
        if (${merchant.partnership==1}) {
            $('#partner').prop("checked", true);
//            $(".changeDisplay").css("display", 'block');
            $("input[name=lm-ljBrokerage]").val(${merchant.ljBrokerage});                       // 普通订单费率
            $("input[name=lm-scoreBRebate]").val(${merchant.scoreBRebate});                     // 普通订单积分返点
            $("input[name=lm-ljCommission]").val(${merchant.ljCommission});                     // 导流订单费率
            $("input[name=lm-scoreARebate]").val(${merchant.scoreARebate});                         // 导流订单红包
            $("input[name=lm-importScoreBScale]").val(${merchantRebatePolicy.importScoreBScale});   // 导流订单积分返点
            $("input[name=lm-userScoreBScaleB]").val(${merchantRebatePolicy.userScoreBScaleB});      // 会员发放红包 【全额】
            $("input[name=lm-userScoreAScale]").val(${merchantRebatePolicy.userScoreAScale});        // 会员发放红包 【比例】
            $("input[name=lm-userScoreBScale]").val(${merchantRebatePolicy.userScoreBScale});        // 会员发放红包 【比例】

            if(${merchantRebatePolicy.rebateFlag==0}) {
                $("#b-policy").attr("checked",true);
                $("#b-policy").nextAll().removeAttr("disabled");
            }
            if(${merchantRebatePolicy.rebateFlag==1}) {
                $("#q-policy").attr("checked",true);
                $("#q-policy").nextAll().removeAttr("disabled");
            }
            if(${merchant.memberCommission==merchant.ljCommission}) {
                $("#yj-hydd").attr("checked",true);
            }else {
                $("#pt-hydd").attr("checked",true);
            }
        }
        if (${merchant.partnership==0}) {
            $('#normal').prop("checked", true);
            $("input[name=pt-ljCommission]").val(${merchant.ljCommission});
            $("input[name=pt-scoreBRebate]").val(${merchant.scoreBRebate})
        }
        var val = $('input:radio[name="type"]:checked').val();
        if (val == 0) {
            $(".pt").removeAttr("disabled");
            $(".lm").attr("disabled", "disabled");
            $(".lianmeng").hide();
            $(".pt").show();
        }
        if (val == 1) {
            $(".pt").attr("disabled", "disabled");
            $(".lm").removeAttr("disabled");
            $(".lianmeng").show();
            $(".pt").hide();
        }
        $("input[name=type]").click(function () {
            var cVal = $(this).next().html();
            console.log(cVal);
            switch (cVal){
                case "普通商户":
                    $(".pt").show();
                    $(".lianmeng").hide();
                    break;
                case "联盟商户":
                    $(".pt").hide();
                    $(".lianmeng").show();
            }
        });
        /*$("input[name=hydd]").click(function () {
         var hVal = $(this).next().html();
         switch (hVal){
         case "佣金费率":
         $(".yj").show();
         break;
         case "普通费率":
         $(".yj").hide();
         break;
         }
         });*/
        $(".yj input[type=number]").attr("disabled","disabled");
        $("input[name=policy]").click(function () {
            $(".yj input[type=number]").attr("disabled","disabled");
            $(this).nextAll().removeAttr("disabled");
            $(this).parent().siblings().children("input").val("");
        });
        if (${merchant.receiptAuth==1}) {
            $('#close').prop("checked", true);
//            $(".changeDisplay").css("display", 'block');
        }
        if (${merchant.merchantBank!=null}) {
            if (${merchant.merchantBank.bankName=="支付宝"}) {
                $('#alipay').prop("checked", true);
                $("#zfb").val('${merchant.merchantBank.bankNumber}');
                $("#skr").val("${merchant.payee}");
                $("#zfbzhouqi").find("option[value='${merchant.cycle}']").attr("selected", true);
            } else {
                $('#bankPay').prop("checked", true);
                $("#payee").val("${merchant.payee}");
                $("#card").val('${merchant.merchantBank.bankNumber}');
                $("#bank").val("${merchant.merchantBank.bankName}");
                $("#cardzhouqi").find("option[value='${merchant.cycle}']").attr("selected", true);
            }
            var val = $('input:radio[name="pay"]:checked').val();
            if (val == 0) {
                $(".cardpay").addClass("dis");
                $(".zfbpay").removeClass("dis");
            }
            if (val == 1) {
                $(".cardpay").removeClass("dis");
                $(".zfbpay").addClass("dis");
            }
        }
        var fnMyFunc1;
        $("input[type=radio]").bind("click", fnMyFunc1 = function () {
            var val = $('input:radio[name="type"]:checked').val();
            if (val == 0) {
                $(".pt").removeAttr("disabled");
                $(".lm").attr("disabled", "disabled");
                $(".lianmeng").hide();
            }
            if (val == 1) {
                $(".pt").attr("disabled", "disabled");
                $(".lm").removeAttr("disabled");
                $(".lianmeng").show();
            }
        });
    });
    $(function () {
        var fnMyFunc1;
        $("input[name=pay]").bind("click", fnMyFunc1 = function () {
            var val = $('input:radio[name="pay"]:checked').val();
            if (val == 0) {
                $(".cardpay").addClass("dis");
                $(".zfbpay").removeClass("dis");
            }
            if (val == 1) {
                $(".cardpay").removeClass("dis");
                $(".zfbpay").addClass("dis");
            }
        });
        $("#selBankName").bind('change',function(){
            var $selBankName = $("#selBankName");
            if($selBankName.val()!=null && $selBankName.val()!='') {
                $("#bank").val($selBankName.find("option:selected").text());
            }
        });
    });
</script>
<script type="text/javascript">
    function setImagePreview(docObj, avalue) {
        var imgObjPreview = document.getElementById(avalue);
        if (docObj.files && docObj.files[0]) {
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '150px';
            imgObjPreview.style.height = '180px';
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        }
        else {
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = imgObjPreview.parentNode;
            localImagId.style.width = "150px";
            localImagId.style.height = "180px";
            try {
                localImagId.style.filter =
                        "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src =
                        imgSrc;
            }
            catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    }
</script>
<script>
    var num = 0;
    var delete_thisId;
    var idName = 'idName';
    var addi = 0;
    $(".imgAdd").click(function () {
        var a = $("<div></div>").attr("id", addi).append(
                $("<div></div>").append(
                        $("<div></div>").append(
                                $("<img />").attr("id", "preview" + num).attr("class", "fileimg")
                        )
                ).append(
                        $("<div></div>").append(
                                $("<input class='picture' />").attr("type", "file").attr("data-url",
                                        "/manage/file/saveImage").attr("id",
                                        "doc"
                                        + num).attr("name",
                                        "file").css("width",
                                        "110"
                                        + "px")
                                //.attr("onchange","javascript:setImagePreview(this,'preview" + num + "');")
                        ).append(
                                $("<button></button>").attr("onclick", "deleteImg(" + addi
                                        + ");").css("background-color",
                                        "red").html("X")
                        ).append(
                                $("<div></div>").append(
                                        $("<button class='downLoad'></button>").html("下载协议")
                                )
                        )
                )
        );
        $(".imgAdd").before(a);
        num++;
        addi++;
        initProtocol();
    });
    function initProtocol() {
        $('.picture').each(function () {
            $(this).fileupload({
                dataType: 'json',
                maxFileSize: 5000000,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                add: function (e, data) {
                    data.submit();
                },
                done: function (e, data) {
                    var resp = data.result;
                    $(this).parent().parent().find(".fileimg").attr('src',
                            '${ossImageReadRoot}/'
                            + resp.data);
                }
            });
        })
        $('.downLoad').each(function () {
            $(this).bind("click", function () {
                var url = $(this).parent().parent().parent().find(".fileimg").attr('src');
                if (url == "" || url == null) {
                    alert("上传后才能下载");
                    return;
                }
                post("/manage/file/downloadPicture?url=" + url, null);
            });
        })
    }
    function post(URL, PARAMS) {
        var temp = document.createElement("form");
        temp.action = URL;
        temp.method = "post";
        temp.style.display = "none";
        for (var x in PARAMS) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = PARAMS[x];
            // alert(opt.name)
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }
    function deleteImg(Obj) {
        $("#" + Obj).remove();
    }
</script>
<script>
    $("#enter").click(function () {
        submitMerchant();
    });
    function submitMerchant() {
        if ($("#partner").val() == "") {
            $("#partner").css("background-color", "red");
            return;
        }
        if ($("#person").val() == "") {
            $("#person").css("background-color", "red");
            return;
        }
        if ($("#phone").val() == "") {
            $("#phone").css("background-color", "red");
            return;
        }
        if ($("#shopname").val() == "") {
            $("#shopname").css("background-color", "red");
            return;
        }
        if ($("#lock").val() == "") {
            $("#lock").css("background-color", "red");
            return;
        }
        var merchant = {};
        var partner = {};
        var salesStaff = {};
        var merchantRebatePolicy={};
        salesStaff.id = $("#sales").val();
        if(salesStaff.id=="nullValue"){
            merchant.salesStaff = null;
        }else{
            merchant.salesStaff = salesStaff;
        }
        partner.id = $("#partners").val();
        merchant.partner = partner;
        var city = {};
        city.id = $("#locationCity").val();
        merchant.city = city;
        var area = {};
        area.id = $("#locationArea").val();
        merchant.area = area;
        var merchantType = {};
        if ($("#merchantType").val() == null || $("#merchantType").val() == 0) {
            alert("请输入商户类型")
            return;
        }
        var merchantUser = {};
        var merchantUserId = $("#merchantuser").val();
        if(merchantUserId!=null && merchantUserId!="") {
            merchantUser.id = merchantUserId;
        }else {
            alert("请选择门店所属商户");
            return;
        }
        merchant.merchantUser = merchantUser;
        merchantType.id = $("#merchantType").val();
        merchant.merchantType = merchantType;
        merchant.location = $("#address").val()
        merchant.name = $("#shopname").val();
        merchant.contact = $("#contact").val();
        merchant.merchantPhone = $("#phone").val();
        var value = $("input[name='type']:checked").val();
        if (value == "" || value == null) {
            alert("请选择合约类型");
            return;
        }
        if (value == 0) {
            // 置空
            merchant.ljBrokerage = null;
            merchant.scoreBRebate = null;
            merchant.ljCommission = null;
            merchantRebatePolicy.importScoreBScale = null;
            merchantRebatePolicy.scoreARebate =  null;
            merchantRebatePolicy.userScoreBScaleB = null;
            merchantRebatePolicy.userScoreAScale = null;
            merchantRebatePolicy.userScoreBScale = null;
            merchant.memberCommission = null;
            // 设置
            merchant.partnership = 0;
            if ($("input[name=pt-ljCommission]").val() > 100 || $("input[name=pt-ljCommission]").val() == "" || $("input[name=pt-ljCommission]").val() == null) {
                alert("请输入正确的手续费");
                return;
            }
            merchant.ljCommission = $("input[name=pt-ljCommission]").val();
            if ($("input[name=pt-scoreBRebate]").val() > 100 || $("input[name=pt-scoreBRebate]").val() == "" || $("input[name=pt-scoreBRebate]").val() == null) {
                alert("请选择积分返利比");
                return;
            }
            merchant.scoreBRebate = $("input[name=pt-scoreBRebate]").val();
            merchantRebatePolicy.rebateFlag = 2;
        }
        if (value == 1) {
            //  置空
            merchant.scoreBRebate = null;
            merchant.ljCommission = null;
            //  设置
            merchant.partnership = 1;
            if ($("input[name=lm-ljBrokerage]").val() > 100 || $("input[name=lm-ljBrokerage]").val() == null || $("input[name=lm-ljBrokerage]").val() == "") {
                alert("请输入普通订单费率")
                return;
            }
            if ($("input[name=lm-scoreBRebate]").val() > 100 || $("input[name=lm-scoreBRebate]").val() == null || $("input[name=lm-scoreBRebate]").val() == "") {
                alert("请输入普通订单积分返点")
                return;
            }
            if ($("input[name=lm-importScoreBScale]").val() > 100 || $("input[name=lm-importScoreBScale]").val() == null || $("input[name=lm-importScoreBScale]").val() == "") {
                alert("请输入导流订单积分")
                return;
            }
            if ($("input[name=lm-scoreARebate]").val() > 100 || $("input[name=lm-scoreARebate]").val() == null || $("input[name=lm-scoreARebate]").val() == "") {
                alert("请输入导流订单红包")
                return;
            }
            var hydd = $("input[name='hydd']:checked").val();
            if(hydd==0) {                       //  会员订单费率： 佣金费率
                merchant.memberCommission = $("input[name=lm-ljCommission]").val();                  // 会员订单费率
            }
            if(hydd==1) {                       //  会员订单费率： 普通费率
                merchant.memberCommission = $("input[name=lm-ljBrokerage]").val();
            }
            var policy = $("input[name='policy']:checked").val();
            if(policy==1) {                     //   成本差全额
                if ($("input[name=lm-userScoreBScaleB]").val() > 100 || $("input[name=lm-userScoreBScaleB]").val() == null || $("input[name=lm-userScoreBScaleB]").val() == "") {
                    alert("请输入全额发放积分返点")
                    return;
                }
                merchantRebatePolicy.rebateFlag = 1;
                merchantRebatePolicy.userScoreAScale = null;
                merchantRebatePolicy.userScoreBScale = null;
                merchantRebatePolicy.userScoreBScaleB = $("input[name=lm-userScoreBScaleB]").val();      // 会员发放红包 【全额】
            }
            if(policy==0) {                     //   比例发放
                if ($("input[name=lm-userScoreAScale]").val() > 100 || $("input[name=lm-userScoreAScale]").val() == null || $("input[name=lm-userScoreAScale]").val() == "") {
                    alert("请输入比例发放红包")
                    return;
                }
                if ($("input[name=lm-userScoreBScale]").val() > 100 || $("input[name=lm-userScoreBScale]").val() == null || $("input[name=lm-userScoreBScale]").val() == "") {
                    alert("请输入比例发放积分")
                    return;
                }
                merchantRebatePolicy.rebateFlag = 0;
                merchantRebatePolicy.userScoreBScaleB = null;
                merchantRebatePolicy.userScoreAScale = $("input[name=lm-userScoreAScale]").val();        // 会员发放红包 【比例】
                merchantRebatePolicy.userScoreBScale = $("input[name=lm-userScoreBScale]").val();        // 会员发放红包 【比例】
            }
            merchant.ljBrokerage = $("input[name=lm-ljBrokerage]").val();                       // 普通订单费率
            merchant.scoreBRebate = $("input[name=lm-scoreBRebate]").val();                     // 普通订单积分返点
            merchant.ljCommission = $("input[name=lm-ljCommission]").val();                     // 导流订单费率
            merchant.scoreARebate =  $("input[name=lm-scoreARebate]").val();                         // 导流订单红包
            merchantRebatePolicy.importScoreBScale =  $("input[name=lm-importScoreBScale]").val();   // 导流订单积分返点
        }
        merchant.userLimit = $("#lock").val();
        merchant.receiptAuth = $("input[name='type1']:checked").val();
        var merchantBank = {};
        merchantBank.id = $("#merchantBankId").val();
        var payWay = $("input[name='pay']:checked").val();
        if (payWay == "" || payWay == null) {
            alert("请选择结算方式");
            return;
        }
        if (payWay == 0) {
            merchant.payee = $("#skr").val();
            merchantBank.bankName = "支付宝";
            merchantBank.bankNumber = $("#zfb").val();
            merchant.cycle = $("#zfbzhouqi").val()
        }
        if (payWay == 1) {
            merchant.payee = $("#payee").val();
            merchantBank.bankName = $("#bank").val();
            merchantBank.bankNumber = $("#card").val();
            merchant.cycle = $("#cardzhouqi").val()
        }
        merchant.id = $("#merchantId").val();
        merchantRebatePolicy.id = $("#merchantRebatePolicyId").val();
        if (merchantBank.bankName == "" || merchantBank.bankNumber == null) {
            alert("请输入结算人 / 开户支行");
            return;
        }
        if (merchantBank.bankNumber == "" || merchantBank.bankNumber == null) {
            alert("请输入结算卡号");
            return;
        }
        merchant.merchantBank = merchantBank;
        var merchantProtocals = [];
        $(".fileimg").each(function () {
            var merchantProtocal = {};
            merchantProtocal.picture = $(this).attr("src");
            merchantProtocals.push(merchantProtocal);
        });
        merchant.merchantProtocols = merchantProtocals;
        $("#enter").unbind("click");
        var merchantDto = {};
        merchantDto.merchant=merchant;
        merchantDto.merchantRebatePolicy=merchantRebatePolicy;
        if (merchant.id == null || merchant.id == "") {
            $.ajax({
                type: "post",
                url: "/manage/merchant",
                contentType: "application/json",
                data: JSON.stringify(merchantDto),
                success: function (data) {
                    alert(data.data);
                    setTimeout(function () {
                        location.href = "/manage/merchant";
                    }, 0);
                }
            });
        } else {
            $.ajax({
                type: "put",
                url: "/manage/merchant",
                contentType: "application/json",
                data: JSON.stringify(merchantDto),
                success: function (data) {
                    alert(data.data);
                    setTimeout(function () {
                        location.href = "/manage/merchant";
                    }, 0);
                }
            });
        }
    }
</script>
<script>
    $(function () {
        $(".money").keyup(function () {
            this.value = this.value.replace(/[^\d.]/g, "");
            this.value = this.value.replace(/^\./g, "");  //验证第一个字符是数字而不是.
            this.value = this.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的.
            this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
            this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');
            // this.value = this.value.replace(/^\d+(?=\.{0,1}\d+$|$)/ , '').replace(/(\d{4})(?=\d)/g, "$1 ");
        })
    });
    function goMerchantPage() {
        location.href = "/manage/merchant";
    }
</script>
</html>
