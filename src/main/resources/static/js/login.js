var login = {
    init : function () {
        var _this = this;
        $('#signin').on('click', function () {
            _this.signin();
        });
    },

    signin : function () {
        var data = {
            studentId: $('#studentId').val(),
            phoneNumber: $('#phoneNumber').val()
        };

        if(data.studentId == ""){
            alert("학번을 입력해주세요.");
            return;
        }

        else if(data.phoneNumber == ""){
            alert("비밀번호를 입력해주세요.");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v2/login',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: data
        }).done(function() {
            window.location.href = '/';
        }).fail(function (error) {
            $('#lg_error').visible(true);
            $('#phoneNumber').val("");
        });
    }
};

login.init();