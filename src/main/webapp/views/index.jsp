<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 변수 지원, 흐름 제어, URL처리 -->
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %><!-- xml코어, 흐름제어, xml변환 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- 지역, 메세지형식, 숫자, 날짜형식 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %><!-- 데이터베이스 -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <%@ taglib prefix="form"uri="http://www.springframework.org/tags/form" %> --%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="ko">
<head>
<title>PageBuilder CMS 씨엠에스</title>
<meta name="viewport" content="width=900">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7" />
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, user-scalable=yes">

<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/contents.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery-ui-1.10.3.css">
<!--[if lt IE 10]>
<link rel="stylesheet" type="text/css" href="css/ie9.css" />
<![endif]-->

<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/custom-form-elements.min.js"></script><!-- radio & checkbox -->
<script type="text/javascript" src="${contextPath}/resources/js/placeholders.min.js"></script><!-- placeholder -->
<script type="text/javascript" src="${contextPath}/resources/js/global_func.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.bpopup.min.js"></script>

<script>
    ;(function($) {
        $(function() {
            // From jQuery v.1.7.0 use .on() instead of .bind()
            $('#my-button').bind('click', function(e) {
                e.preventDefault();
                $('.pop_wrap').bPopup();
            });
        });
    })(jQuery);
    $(document).ready(function(){
        $('.sh_id').click(function(){
            validate(1);
        });
        $('#fm_email1').keyup(function(e){
            if (e.keyCode == 13) {validate(1);}
        });
        $('.sh_pwd').click(function(){
            validate(2);
        });
        $('#fm_email2').keyup(function(e){
            if (e.keyCode == 13) {validate(2);}
        });
        $('.pop-close2').click(function(){
            $("#fm_first_name").val("");
            $("#fm_last_name").val("");
            $("#fm_user_id").val("");
            $("#fm_email1").val("");
            $("#fm_email2").val("");
        });

        $('#loginBtn').click(function(){
            var userId = $("#usernameText").val();
            var userPw = $("#passwordText").val();
            userStatusValidate(userId, userPw);
        });

        $('#passwordText').keydown(function(e){
            if(e.keyCode == 13){
                var userId = $("#usernameText").val();
                var userPw = $("#passwordText").val();
                userStatusValidate(userId, userPw);
                e.preventDefault();
            }
        });
    });

    function userStatusValidate(userId, userPw){
        $.ajax({
            url:"/member/validateUser.html",
            type:"POST",
            data:{
                "userId":userId,
                "userPw":userPw
            },
            success:function(result){
                switch(result){
                    case 1 :
                        //message : 탈퇴한 계쩡입니다.
                        alert("<spring:message code='page.index.009' />");
                        return;
                        break;
                    case 2 :
                        //message : 이용 정지된 계정입니다.
                        alert("<spring:message code='page.index.010' />");
                        return;
                        break;
                    case 3 :
                        //message : 강제 탈퇴된 계정입니다.
                        alert("<spring:message code='page.index.011' />");
                        return;
                        break;
                    case 4 :
                        $('#log_f').submit();
                        return;
                        break;
                    case 5 :
                        //message : 메일 인증후 사용 가능합니다.
                        alert("<spring:message code='page.index.012' />");
                        return;
                        break;
                    case 6 :
                        //message : 아이디 또는 비밀번호를 다시 확인하세요.
                        alert("<spring:message code='page.index.013' />");
                        return;
                        break;
                        //message : 유효기간이 지난 계정입니다.
                    case 7:
                        alert("<spring:message code='page.index.016' />");
                        break;
                    case 8:
                        //message : 이미 로그인 되어있는 상태입니다.
                        alert("<spring:message code='login.duplicate.device' />");
                        break;
                }
            }
        });
    }

    function validate(val) {
        if(val == 1){
            if (!$('#fm_first_name').val()) {
                alert("<spring:message code='page.index.idsh.004' />");
                $('#fm_first_name').focus();
                return;
            }
            if (!$('#fm_last_name').val()) {
                alert("<spring:message code='page.index.idsh.005' />");
                $('#fm_last_name').focus();
                return;
            }
            if (escape($('#fm_email1').val()).match(/^(\w+)@(\w+)[.](\w+)$/ig) == '' &&
                escape($('#fm_email1').val()).match(/^(\w+)@(\w+)[.](\w+)[.](\w+)$/ig) == '') {
                alert("<spring:message code='extend.local.001' />1.");
                $('#fm_email1').focus();
                return;
            }
            submit1();
        }else if(val == 2){
            if (!$('#fm_user_id').val()) {
                alert("<spring:message code='page.index.idsh.006' />");
                $('#fm_user_id').focus();return;
            }
            if (escape($('#fm_email2').val()).match(/^(\w+)@(\w+)[.](\w+)$/ig) == '' &&
                escape($('#fm_email2').val()).match(/^(\w+)@(\w+)[.](\w+)[.](\w+)$/ig) == '') {
                alert("<spring:message code='extend.local.001' />2.");
                $('#fm_email2').focus();
                return;
            }
            submit2();
        }
    }

    function submit1(){
        $.ajax({
            type : "POST",
            url : "/find_id.html",
            data : $("#frm_find_id").serialize(),
            failure : function(_response) {
                alert("error");
                return false;
            },
            success : function(msg) {
                msg = msg.replace(/(\r\n|\n|\r)/gm,"");
                //alert("*");
                if (msg == "successTrue") {
                    $("#find_id").text('');
                    $('.pop-close2').click();
                    alert("<spring:message code='page.index.idsh.007' />");
                }
                else {
                    $("#find_id").text("<spring:message code='page.index.idsh.008' />");
                }
            }
        });
    }

    function submit2(){
        $.ajax({
            type : "POST",
            url : "/find_password.html",
            data : $("#frmFindPwd").serialize(),
            failure : function(_response) {
                alert("error");
                return false;
            },
            success : function(msg) {
                msg = msg.replace(/(\r\n|\n|\r)/gm,"");
                if (msg == "True!") {
                    $("#find_pwd").text('');
                    $('.pop-close2').click();
                    alert("<spring:message code='page.index.idsh.009' />");
                }
                else {
                    $("#find_pwd").text("<spring:message code='page.index.014' />");
                }
            }
        });
    }

    function moveToDownList(){
        var isMobile = "NOMB";
        if (/Android/i.test(navigator.userAgent)){
            isMobile = "ADD";
        }
        else if (/iPhone|iPad/i.test(navigator.userAgent)){
            isMobile = "IPHD";
        }
        window.location.href="/down/list.html?currentPage=1&isMobile=" + isMobile;
    }
</script>
</head>

<body>
<div class="pop_wrap" id="pop_login">
    <div class="pop_con">
        <span class="pop-close pop-close2" title="<spring:message code='page.index.015' />">×</span>
        <div>
            <div class="pop_header">
                <h1><spring:message code='page.index.001'/>/<spring:message code='page.index.002'/></h1>
            </div>
            <form id="frm_find_id" name="frm_find_id" method="post" action="return true;">
                <div class="pop_section first">
                    <h2><spring:message code='page.index.003' /></h2>
                    <input name="fm_first_name" id="fm_first_name" type="text" placeholder="First_Name">
                    <input name="fm_last_name" id="fm_last_name" type="text" placeholder="Last_Name">
                    <input name="fm_email1" id="fm_email1" type="text" placeholder="E-mail">
                    <p id="find_id"></p>
                    <div class="btn_area">
                        <a href="#" class="btn btnL btn_red sh_id"><spring:message code='page.index.004' /></a>
                    </div>
                </div>
            </form>
            <form id="frmFindPwd" name="frmFindPwd" method="post" action="return true;">
            <div class="pop_section">
                <h2><spring:message code='page.index.002' /></h2>
                <input name="fm_user_id" id="fm_user_id" type="text" placeholder="ID">
                <input name="fm_email2" id="fm_email2" type="text" placeholder="E-mail">
                <p id="find_pwd"></p>
                <div class="btn_area">
                    <a href="#" class="btn btnL btn_red sh_pwd"><spring:message code='page.index.004' /></a>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>

<div id="login_wrap">
	<div id="container">
		<div class="login_area">
		<div>
            <h1><img src="/resources/images/logo_login.png" alt="PageBuilder"></h1>
            <form action="j_spring_security_check" method="post" name="log_f" id="log_f">
                <div class="section first_section">
                    <div class="form_area">
                        <input name="j_username" id="usernameText" type="text" style="">
                        <input name="j_password" id="passwordText" type="password" style="">
                        <a href="#" id="loginBtn" name="loginBtn" class="btn btn_login" >LOGIN</a>
                    </div>
                    <div class="member_area">
                        <ul>
                        <li><a href="member/join.html"><spring:message code='page.index.005'/></a></li>
                        <li><a href="#" id="my-button"><spring:message code='page.index.001'/>/<spring:message code='page.index.002'/></a></li>
                        <li><a href="javascript:moveToDownList();"><spring:message code='page.index.006'/></a></li>
                        </ul>
                    </div>
                </div>
            </form>
		</div>
		</div>
	</div>
	<%@ include file="inc/footer.jsp" %>
</div>

</body>
</html>
