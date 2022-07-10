$(function () {
    var vue = new Vue({
        "el": "#main_div",
        data: {
            pageInfo:{}
        },
        methods: {
            queryActivityByCondition:function (pageNo,pageSize) {
                var name = $("#name").val();
                var company = $("#company").val();
                var phone = $("#phone").val();
                var source = $("#source").val();
                var owner = $("#owner").val();
                var mobilePhone = $("#mphone").val();
                var state = $("#state").val();

                axios({
                    method: "POST",
                    url: "workbench/clue/queryClue.do",
                    params: {
                        name: name,
                        company: company,
                        phone: phone,
                        source: source,
                        owner: owner,
                        mobilePhone: mobilePhone,
                        state: state,
                        pageNo:pageNo,
                        pageSize:pageSize
                    }
                })
                    .then(function (value) {
                        vue.pageInfo = value.data;
                        $("#page").bs_pagination({
                            currentPage: value.data.pageNum,//当前页号,相当于pageNo

                            rowsPerPage: value.data.pageSize,//每页显示条数,相当于pageSize
                            totalRows: value.data.total,//总条数
                            totalPages: value.data.pages,  //总页数,必填参数.

                            visiblePageLinks: 5,//最多可以显示的卡片数

                            showGoToPage: true,//是否显示"跳转到"部分,默认true--显示
                            showRowsPerPage: true,//是否显示"每页显示条数"部分。默认true--显示
                            showRowsInfo: true,//是否显示记录的信息，默认true--显示

                            //用户每次切换页号，都自动触发本函数;
                            //每次返回切换页号之后的pageNo和pageSize
                            onChangePage: function (event, pageObj) { // returns page_num and rows_per_page after a link has clicked
                                vue.queryActivityByCondition(pageObj.currentPage, pageObj.rowsPerPage);
                            }
                        });
                    })
                    .catch(function (reason) {
                        console.log(reason);
                    });
            },
            toDetail:function (id) {
                window.location.href='workbench/clue/detail.do?id='+id;
            }
        },
        mounted:function () {
            this.queryActivityByCondition(1,10);
        }

    });
    ;

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
                if (value.data.code === "1") {
                    $("#createClueModal").modal("hide");
                    vue.queryActivityByCondition();
                } else {
                    $("#createClueModal").modal("show");
                    alert(value.data.message);
                }
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


