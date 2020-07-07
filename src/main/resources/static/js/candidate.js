var candidate = {
    init : function () {
        var _this = this;
        $('#regist').on('click', function () {
            _this.regist();
        });
    },

    regist : function () {

        var idx = $('#user_idx').text();
        alert(idx);

        $.ajax({
            type: 'PUT',
            url: '/api/v3/candidate/reg' + idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    search : function () {
        var data = {
            type: $('#search_type option:selected').val(),
            word: $('#search_word').val(),
        };

        $.ajax({
            type: 'GET',
            url: '/userinfo',
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

candidate.init();