$(function () {
    var vue = new Vue({
        "el":"#remarkDivList",
        data:{
            noteContent:""
        },
        methods:{
            addRemark:function () {
                alert("aaaaa");
                var id = '[[${activity.id}]]';
                axios({
                    method:"POST",
                    url:"workbench/activity/addRemark.do",
                    params:{
                        noteContent:vue.noteContent,
                        activityId:id,
                    }
                })
                    .then(function (value) {
                        if (value.data.code === "1"){
                            //清空输入备注框
                            vue.noteContent="";
                            var htmlStr="";
                            htmlStr+="<div class=\"remarkDiv\" id=\"div_"+value.data.retData.id+"\" style=\"height: 60px;\">";
                            htmlStr+="	<img th:title=\"${session.sessionUser.name}\" src=\"image/user-thumbnail.png\"";
                            htmlStr+="		 style=\"width: 30px; height:30px;\">";
                            htmlStr+="		<div style=\"position: relative; top: -40px; left: 40px;\">";
                            htmlStr+="			<h5>"+noteContent+"</h5>";
                            htmlStr+="			<font color=\"gray\">市场活动</font> <font color=\"gray\">-</font>";
                            htmlStr+="			<b>[[${activity.name}]]</b> <small style=\"color: gray;\"> "+value.data.retData.createTime;
                            htmlStr+="			由[[${session.user.name}]]创建</small>";
                            htmlStr+="			<div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">";
                            htmlStr+="				<a class=\"myHref\" name=\"editA\" remarkId=\""+value.data.retData.id+"\" href=\"javascript:void(0);\"><span";
                            htmlStr+="						class=\"glyphicon glyphicon-edit\"";
                            htmlStr+="						style=\"font-size: 20px; color: #E6E6E6;\"></span></a>";
                            htmlStr+="				&nbsp;&nbsp;&nbsp;&nbsp;";
                            htmlStr+="				<a class=\"myHref\" name=\"deleteA\" remarkId=\""+value.data.retData.id+"\" href=\"javascript:void(0);\"><span";
                            htmlStr+="						class=\"glyphicon glyphicon-remove\"";
                            htmlStr+="						style=\"font-size: 20px; color: #E6E6E6;\"></span></a>";
                            htmlStr+="			</div>";
                            htmlStr+="		</div>";
                            htmlStr+="</div>";
                            $("#remarkDiv").before(htmlStr);
                        }else {
                            alert(value.data.message);
                        }
                    })
                    .catch(function (reason) {
                        console.log(reason)
                    });
            }
        }
    });
});