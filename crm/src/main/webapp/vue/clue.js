$(function () {
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
});