<%@ page isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<a href="javascript:void(0);" id="connectWebSocket" onclick="connectWebSocket()">连接WebSocket</a><br/>
<br/>
bbb
</body>
<script type="text/javascript" src="/static/jquery.js"></script>
<script type="text/javascript">
    $(function () {
    });

    function connectWebSocket() {
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
