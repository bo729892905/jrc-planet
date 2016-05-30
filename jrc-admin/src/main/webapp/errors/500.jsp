<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>出错了</title>
    <style>
        body{
            font-family:"Microsoft YaHei","微软雅黑","Tahoma","Helvetica";
        }
        .error_bg{
            background-color: #fff; padding: 20px 0 38px 0;
        }
        .error_content{
            text-align: center;
        }
        .error_pa{
            position: relative; width: 100px; margin: 0 auto; left: -38px;
        }
        .error_content p{
            color: #9f9c96;
        }
        .error_bl{
            height: 40px; width: 35px; display: inline-block;
            background-color:#7dd0f3 ;  margin: 0 auto;
            -webkit-transform: rotate(-45deg);
            -moz-transform: rotate(-45deg);
            -o-transform: rotate(-45deg);
            -ms-transform: rotate(-45deg);

        }
        .error_or{
            height: 40px; width: 35px; display: inline-block;
            background-color:#f89922 ;  margin:0 auto 0 -6px;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
        }
        .error_wh{
            position: absolute; left: 42px; top:14px;
            height: 13px; width: 13px; display:
            inline-block; background-color:#fff;
            -webkit-transform: rotate(45deg);
            -moz-transform: rotate(45deg);
            -o-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
        }
        .error_hr{
            border-bottom:1px #c6cbcf solid; margin: 26px 0 38px;
        }
        .error_content dl dt{
            color: #37a8b0; font-size: 145px;
        }
        .error_content dl dd{
            color: #9f9c96; font-size: 24px;
        }
        .error_phonebg{
            background: #3e739d; text-align: center; padding: 40px 0 80px 0;
        }
        .error_phonebg div{
            margin: 0 auto;
        }
        .error_phonebg div{
            color: #fff; width:10px ; height: 20px;
        }
        .error_phonebg p{
            color: #fff; font-size: 24px; letter-spacing: 1px;
        }
        .error_footer{
            background: #fff; text-align: center; padding-top: 20px;
        }
        .error_footer p{
            color: #666666;
        }
        .error_logohead{
            position: absolute; top:1px; left: 86px; width: 100px;
        }
        .error_logohead span{
            font-size: 20px; color: #4d4e50; font-weight: bold;
            letter-spacing: 5px; display: inline-block;
        }
        .error_logobody{
            position: absolute; top:20px; left: 86px; width: 100px;
        }
        .error_logobody span{
            font-size: 5px; color: #4d4e50; font-weight: bold; line-height: 20px;
        }
    </style>
</head>
<body>
<div class="error_bg">
    <div class="col-xs-5 container error_content">
        <p> </p>
        <div class="error_hr"></div>
        <dl>
            <dt>500</dt>
            <dd>服务器内部错误</dd>
        </dl>
        <p>输入的网址不正确或者已经失效。</p>
    </div>
</div>
</body>
</html>