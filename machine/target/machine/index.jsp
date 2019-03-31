<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>机器学习</title>
    <link rel="icon" href="favicon.ico" sizes="32x32">
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="./css/style.css">
    <link rel="stylesheet" media="screen" href="./layui/css/layui.css">
    <style>
        .file-upload {
            position: absolute;
            top: 50%;
            left: 50%;
            display: block;
            margin-left: -300px;
            margin-top: -150px;
            width: 600px;
            height: 150px;
            background: rgba(255, 96, 84, 0.5);
        }

        .layui-upload {
            /*position: absolute;*/
            /*bottom: 20px;*/
            /*left: 20%;*/
            /*margin-left: -0px;*/
            /*width: 400px;*/
            display: block;
            text-align: center;
            margin: 40px auto 10px;

        }

        .file-upload h2 {
            width: 400px;
            margin: 10px auto;
            text-align: center;
            font-size: 26px;
        }

    </style>
</head>
<body style="position: relative">

<!--&lt;!&ndash; count particles &ndash;&gt;-->
<!--<div class="count-particles">-->
<!--<span class="js-count-particles">&#45;&#45;</span> particles-->
<!--</div>-->

<!-- particles.js container -->
<div id="particles-js">
</div>

<!-- scripts -->
<script src="./particles.js"></script>
<script src="./js/app.js"></script>
<script src="./layui/layui.js"></script>
<div class="file-upload" id="file-upload">
    <div><h2>MACHINE LEARNING</h2></div>
    <div class="layui-upload">
        <button type="button" class="layui-btn" id="select-file" style="background-color: #393D49">选择文件</button>
        <button type="button" class="layui-btn" id="upfile" style="background-color: #393D49">开始上传</button>
    </div>
</div>
<script>
    layui.use(['layer', 'upload'], function () {
        var layer = layui.layer
            , upload = layui.upload;
        upload.render({
            elem: '#select-file'
            , url: 'Servlet'
            , auto: false
            , bindAction: '#upfile'
            , accept: 'file'
            , exts: 'docx|ipynb'
            , drag: 'true'
            , done: function (res) {
                console.log(res);
                if (res.code == "ok") {
                    layer.msg("文件上传成功");
                }
                if (res.code == "error") {
                    layer.msg("文件格式错误，正确格式 应数11601-1604270328-裴常旺-作业2.docx，注意与老师的格式有不同");
                }
                if (res.code == "lock") {
                    layer.msg("文件格式错误，正确格式 应数11601-1604270328-裴常旺-作业2.docx，注意与老师的格式有不同");
                }

            }
            , error: function (index, upload) {
                layer.msg("文件上传失败，请联系助教QQ1241169737");

            }
        });
    });


</script>
</body>
</html>