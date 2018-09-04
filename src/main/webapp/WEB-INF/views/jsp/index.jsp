<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="javascript:void(0);" id="createHtml" onclick="createHtml()">生成html模板...</a><br/>
<a href="${pageContext.request.contextPath}/freemarkerController/jumpFtl" id="jumpFtl">跳转ftl</a>
<br/>
bbb
</body>
<script type="text/javascript" src="/static/jquery.js"></script>
<script type="text/javascript">
    $(function () {
    });

    function createHtml() {
        $.ajax({
            url: '',
            data: {key: '123456'},
            type: 'post',
            //dataType: 'text',
            success: function(data){
                alert(data);
            },
            error: function (msg) {
                alert(msg);
            }
        })
    }
</script>
</html>
