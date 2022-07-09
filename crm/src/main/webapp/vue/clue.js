$(function () {
    queryActivityByCondition();

    $("#saveClue").click(function () {
        let owner = $("#create-clueOwner").val();
        let company = $("#create-company").val();
        let appellation = $("#create-call").val();
        let fullname = $("#create-surname").val();
        let job = $("#create-job").val();
        let email = $("#create-email").val();
        let phone = $("#create-phone").val();
        let website = $("#create-website").val();
        let mphone = $("#create-mphone").val();
        let state = $("#create-status").val();
        let source = $("#create-source").val();
        let description = $("#create-describe").val();
        let contactSummary = $("#create-contactSummary").val();
        let nextContactTime = $("#create-nextContactTime").val();
        let address = $("#create-address").val();


        axios({
            method: "POST",
            url: "workbench/clue/createClue.do",
            params: {
                owner: owner,
                company: company,
                appellation: appellation,
                fullname: fullname,
                job: job,
                email: email,
                phone: phone,
                website: website,
                mphone: mphone,
                state: state,
                source: source,
                description: description,
                contactSummary: contactSummary,
                nextContactTime: nextContactTime,
                address: address,
            }
        })
            .then(function (value) {

            })
            .catch(function (reason) {
                console.log(reason);
            });
    });


    $(".mydate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        minView: 'month',	//可选择的最小视图
        initData: new Date(),	//初始化显示的时间
        autoclose: true,		//选择完自动关闭
        todayBtn: true,		//显示今天的按钮
        clearBtn: true		//显示清空按钮，但是是英文，改中文要在框架中修改
    });
});

function queryActivityByCondition() {
    var name = $("#name").val();
    var company = $("#company").val();
    var phone = $("#phone").val();
    var source = $("#source").val();
    var owner = $("#owner").val();
    var mobilePhone = $("#mphone").val();
    var state = $("#state").val();

    axios({
        method:"POST",
        url:"workbench/clue/queryClue.do",
        params: {
            name:name,
            company:company,
            phone:phone,
            source:source,
            owner:owner,
            mobilePhone:mobilePhone,
            state:state,
        }
    })
        .then(function (value) {

        })
        .catch(function (reason) {

        });
}