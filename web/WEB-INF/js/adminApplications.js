/**
 * Created by Administrator on 2015/4/14.
 */


$(document).ready(function() {
    $('nav ul a').on('click', function() {
        $('nav ul a').removeClass('active');
        $(this).toggleClass('active');
    });

    $('.check-detail-btn').click(function () {
        $.ajax({
            url: '/Admin/appDetail.json',
            type: 'GET',
            data: {"appid": parseInt(this.value)}
        });
    })
});

var ValidateNumber = function(e, pnumber){

    if (!/^\d+$/.test(pnumber)){
        $(e).val(/^\d+/.exec($(e).val()));
    }
    return false;
};


/*
 ### 审核申请
 PUT请求
 Admin/Application.json  put
 {
 "app_id": number
 }

 返回
 {
 "Code": 3205 - 申请失败, 4005 - 该申请无法通过, 0 - 操作成功
 "Msg": "申请失败"(3205), ...
 }
*/

var applicationReviewed = function(){
    var app_id = $("#input-appid-reviewed").val();
    if (app_id === '') {
        return;
    }
    $.ajax({
        url: '/Admin/ApplicationReceive.json',
        type: 'POST',
        data: {"app_id": parseInt(app_id)},
        success: function(result) {
            // When success
            if (parseInt(result['Code']) === 0) {
                alert('操作成功！');
            }
            else if (parseInt(result['Code']) === 4005) {
                alert('申请无法通过！');
            }
            else if (parseInt(result['Code']) === 3205) {
                alert('申请失败！');
            }
        }
    });
};

/*
 ### 取消申请
 Admin/ApplicationCancel.json put
 {
 "app_id": nubmer,
 "content": string // 取消理由
 }

 返回内容：
 {
 "Code": 4009 - 申请失败, 1010 - 找不到该申请, 3209 - 该申请无法取消, 0 - 操作成功, 2004 - 未知错误: 2004
 "Msg": "申请失败"(4009), ...
 }
*/

var applicationCancel = function() {
    var app_id = $('#input-appid-cancel').val();
    var content = $('#input-reason-cancel').val();

    if (app_id === '') {
        return;
    }

    $.ajax({
        url: '/Admin/ApplicationCancel.json',
        type: 'POST',
        data: {
            'app_id': parseInt(app_id),
            'content': content
        },
        success: function(result){
            if (parseInt(result['Code']) === 0) {
                alert('操作成功！');
            }
            else if (parseInt(result['Code']) === 1010) {
                alert('找不到该申请，请检查ID输入是否正确！');
            }
            else if (parseInt(result['Code']) === 3209) {
                alert('该申请无法取消。');
            }
            else if (parseInt(result['Code']) === 4009) {
                alert('申请失败！');
            }
            else if (parseInt(result['Code']) === 2004) {
                alert('未知错误。');
            }
        }
    });
};

var applicationComplete = function() {
    var app_id = $("#input-appid-complete").val();
    if (app_id === '') {
        return;
    }
    $.ajax({
        url: '/Admin/ApplicationRetrieve.json',
        type: 'POST',
        data: {"app_id": parseInt(app_id)},
        success: function(result) {
            // When success
            if (parseInt(result['Code']) === 0) {
                alert('操作成功！');
            }
            else if (parseInt(result['Code']) === 4005) {
                alert('申请无法通过！');
            }
            else if (parseInt(result['Code']) === 3205) {
                alert('申请失败！');
            }
        }
    });
};

