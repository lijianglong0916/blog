/**
 * 回复
 */
function replyPost() {
    var questionId = $("#questionId").val();
    var commentText = $("#commentText").val();
    var creator = $("#creator").val();
    if (!commentText) {
        alert("内容不能为空！")
        return;
    }
    if (creator == null) {
        alert("请登陆后再在回复！")
        return;
    }
    $.ajax({
        type: "POST",
        url: "comment",
        contentType: 'application/json',
        data: JSON.stringify({"questionId": questionId, "commentText": commentText}),
        success: function (response) {
            if (response == "success") {
                alert("回复成功！");
                location.reload();
            }

        }
    });
    console.log(questionId)

}

/**
 * 删除问题
 */
function deleteQue() {
    var questionId = $("#questionId").val();
    $.ajax({
        type: "post",
        url: "deleteQuestion",
        contentType: 'application/json',
        data: JSON.stringify({"id": questionId}),
        success: function (response) {
            if (response == "success") {
                alert("删除成功！");
                location.reload();
            }
        }
    });
}

/**
 * 删除用户（root用户操作）
 */
function deleteUser(account_id) {
    var account_id = account_id;
    $.ajax({
        type: "post",
        url: "deleteUser",
        contentType: 'application/json',
        data: JSON.stringify({"account_id": account_id}),
        success: function (response) {
            alert(response['msg']);
            location.reload();
        }
    });
}

/**
 * 回复框验证
 */
function commentReply() {
    var creator = $("#creator").val();
    if (creator != "") {
        document.getElementById("commentText").removeAttribute('disabled');
        $("#commentText").focus();
    } else {
        alert("请先登录！")
    }

}

/**
 * 点赞
 */
function commentLike() {
    var question_id = $("#question_id").val();
    var user_name = $("#user_name").val();
    if (user_name == "") {
        alert("请登录！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "commentLike",
        contentType: 'application/json',
        data: JSON.stringify({"question_id": question_id}),
        success: function (response) {
            alert(response);
            location.reload();
        }
    });
}

function showLogIn() {
    $("#myModal").modal('show'); //显示模态框
}

/**
 * 标签填入设置
 * @param value
 */
function setTag(value) {
    var per = $("#tag").val();
    if (per.indexOf(value) != -1) {
        return;
    } else {
        if (per == '') {
            ;
            $("#tag").val(value);
            return;
        } else {
            $("#tag").val(per + ',' + value);
            return;
        }
    }
}

/**
 * 修改question
 */
function publishPost() {
    var title = $("#title").val();
    var description = $("#description").val();
    var tag = $("#tag").val();
    var user_name = $("#user_name").val();
    var id = $("#questionId").val();
    if (title == '') {
        alert("标题不能为空！")
        return;
    }
    if (description == '') {
        alert("内容不能为空！")
        return;
    }
    if (tag == '') {
        alert("tag不能为空！")
        return;
    }
    if (user_name == '') {
        alert("请先登陆！")
        return;
    }
    $.ajax({
        type: "POST",
        url: "publish",
        contentType: 'application/json',
        data: JSON.stringify({"title": title, "description": description, "tag": tag, "id": id}),
        success: function (response) {
            alert(response['msg']);
            $("#tag").val('');
            $("#description").val('');
            $("#title").val('');
            location.reload();

        }
    });

}

function displayTags() {
    $("#labelTag").show();
}

function blockTags() {
    if ($("#labelTag").focus()) {
    } else {
        $("#labelTag").hide();
    }
}

/**
 * 图片上传 做个下简易的验证  大小 格式
 */
//检查图片
function checkImage() {
    var fileName = $("#filename").val();
    fileName = fileName.replace("C:\\fakepath\\", "");
    var flag = true;
    if (fileName == "") {
        flag = false;
        alert("请选择图片");
    } else {
        var size = $("#filename")[0].files[0].size;
        if (size / 1000 > 1024 * 2) {
            flag = false;
            alert("图片大小不能超过2M");
        }
    }
    if (!flag)
        $("#filename").val("");
    return flag;
}

/**
 * 改变文件上传按钮的状态
 */
function changeHP() {
    $("#filename").removeAttr("disabled");
    $("#submitBtn").removeAttr("disabled");
    $("#submitBtn").attr("style", "display:block;");
    $("#filename").attr("style", "opacity: 1");
    $("#changeHP").attr("style", "display:none;");
}

/**
 * 上传头像图片
 */
function picSubmit() {
    if (checkImage()) {
        // var fileName = $.trim(consoleDlg.find("#filename").val());
        var formData = new FormData(); //不能传的参数太多，好像有限制，一般传文件和描述即可
        formData.append("fileName", document.getElementById("filename").files[0]);
        console.log(formData)
        $.ajax({
            url: "imageUpload",
            type: 'POST',
            cache: false,
            data: formData,
            dataType: 'json',
            contentType: false,
            processData: false,
            async: true,
            success: function (response) {
                alert(response['message']);
                $("#filename").val("");
                $("#filename").attr("disabled", "true");
                $("#submitBtn").attr("disabled", "true");
                location.reload();
            },
            error: function (textStatus, e) {
                alert("系统ajax交互错误: " + textStatus);
            }
        });
    }
}

/**
 * 注册用户
 */
function registerSubmit() {
    var account = $("#registerAccount").val();
    var name = $("#registerName").val();
    var password = $("#registerPassword").val();
    if (account == "") {
        alert("用户账号不能为空！");
        return;
    }
    if (!validate(account)) {
        alert("账号只能是数字");
        return;
    }
    if (name == "") {
        alert("用户名不能为空！");
        return;
    }
    if (password == "") {
        alert("密码不能为空！");
        return;
    }

    $.ajax({

        type: "POST",
        url: "userRegister",
        contentType: 'application/json',
        data: JSON.stringify({"account_id": account, "name": name, "password": password}),
        success: function (response) {
            alert(response['msg']);
            $('#registerModal').attr("style", "display:none");
            location.reload();
        }

    });

}

function validate(obj) {
    var reg = /^[0-9]*$/;
    return reg.test(obj);

};

/**
 * 改变信息输入框状态
 */
function changeMessage() {

    $("#user_true_name").attr("readonly", false);
    $('#user_true_name').attr("class", "person-input-change");
    $("#sex").attr("readonly", false);
    $('#sex').attr("class", "person-input-change");
    $("#birthday").attr("readonly", false);
    $("#birthday").attr('type', 'date');
    $('#birthday').attr("class", "person-input-change");
    $("#position").attr("readonly", false);
    $('#position').attr("class", "person-input-change");
    $("#company").attr("readonly", false);
    $('#company').attr("class", "person-input-change");
    $("#education").attr("readonly", false);
    $('#education').attr("class", "person-input-change");
    $("#school").attr("readonly", false);
    $('#school').attr("class", "person-input-change");
    $("#industry").attr("readonly", false);
    $('#industry').attr("class", "person-input-change");
    $("#introduction").attr("readonly", false);
    $('#introduction').attr("class", "person-input-change");
    $("#updateUM").attr("style", "display:block;");
}

/**
 * 提交信息
 */
function saveMessage() {
    var user_true_name = $("#user_true_name").val();
    var sex = $("#sex").val();
    var birthday = $("#birthday").val();
    var position = $("#position").val();
    var company = $("#company").val();
    var education = $("#education").val();
    var school = $("#school").val();
    var industry = $("#industry").val();
    var introduction = $("#introduction").val();

    if (sex != "") {
        if (sex != "男" && sex != "女") {
            alert("性别只能输入男或女！");
            return;
        }
    }

    $.ajax({

        type: "POST",
        url: "updateUM",
        contentType: 'application/json',
        data: JSON.stringify({
            "user_true_name": user_true_name, "sex": sex,
            "birthday": new Date(birthday).valueOf(), "position": position, "company": company,
            "education": education, "school": school, "industry": industry, "introduction": introduction
        }),
        success: function (response) {
            alert(response['msg']);
            location.reload();
        }

    });


}

function inputSearch() {
    var message = $("#searchQuestion").val();
    if (message == "") {
        $("#errorMsg").attr("value", "请输入查询信息！");
        $("#errorMsg").attr("readonly", true);
        $("#errorMsg").show();
        return;
    }
    $("#messageForm").action = "inputSearch";
    $("#messageForm").submit();
}

/**
 * 找回密码
 */
function missPass() {
    // $('#myModal').attr("style", "display:none");
    $('#changePassModal').modal('show') //显示模态框

}


/**
 * 注册用户
 */
function changePassSubmit() {
    var account = $("#changePassAccount").val();
    var name = $("#changePassName").val();
    if (account == "") {
        alert("用户账号不能为空！");
        return;
    }
    if (!validate(account)) {
        alert("账号只能是数字");
        return;
    }
    if (name == "") {
        alert("用户名不能为空！");
        return;
    }

    $.ajax({

        type: "POST",
        url: "changePass",
        contentType: 'application/json',
        data: JSON.stringify({"account_id": account, "name": name}),
        success: function (response) {
            alert("密码为：" + response['msg']);
            $('#changePassModal').attr("style", "display:none");
            location.reload();
        }

    });

}

/**
 * 用户登录
 */
function userLogin() {
    var account_id = $("#account_id").val();
    var password = $("#password").val();
    if (account_id == "") {
        alert("用户名不能为空！");
        return;
    }
    if (password == "") {
        alert("密码不能为空！");
        return;
    }

    $.ajax({

        type: "POST",
        url: "login",
        contentType: 'application/json',
        data: JSON.stringify({"account_id": account_id, "password": password}),
        success: function (response) {
            alert(response['msg']);
            // $('#changePassModal').attr("style", "display:none");
            location.reload();
        }

    });
}
