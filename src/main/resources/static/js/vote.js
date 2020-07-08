var vote = {
    init : function () {
        var _this = this;
        $('#positive').on('click', function () {
            _this.positive();
        });
        $('#negative').on('click', function () {
            _this.negative();
        })
    },

    positive : function () {
        var data = {
            idx: $('#candidate_idx').val(),
            opposite: true
        };

        if (confirm("정말 찬성하시겠습니까?") == true){
            $.ajax({
                type: 'POST',
                url: '/api/v3/vote',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = '/voteend';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }else{   //취소
            return false;
        }
    },

    negative : function () {
        var data = {
            idx: $('#candidate_idx').val(),
            opposite: false
        };

        if (confirm("정말 반대하시겠습니까?") == true){
            $.ajax({
                type: 'POST',
                url: '/api/v3/vote',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                window.location.href = '/voteend';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }else{   //취소
            return false;
        }
    }
};

vote.init();