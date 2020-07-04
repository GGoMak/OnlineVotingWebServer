var board = {
    init : function () {
        var _this = this;
        $('#save').on('click', function () {
            _this.save();
        });

        $('#update').on('click', function () {
            _this.update();
        });

        $('#delete').on('click', function () {
            _this.delete();
        });
    },

    save : function () {
        var data = {
            title: $('#board_title').val(),
            boardType: $('#board_type').val(),
            content: $('#board_content').val()
        };


        if($('#board_type option:selected').val() == "--게시판--"){
            alert("게시판을 선택해주세요");
            return;
        }
        else if($('#board_title').val() == ""){
            alert("제목을 입력해주세요");
            return;
        }
        else if($('#board_content').val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert("글이 등록되었습니다.")
            window.location.href = '/'+ $('#board_type option:selected').val();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            boardType: $('#board_type').val(),
            title: $('#board_title').val(),
            content: $('#board_content').val()
        };

        var idx = $('#board_idx').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/'+ data.boardType;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var idx = $('#board_idx').val();
        var boardType = $('#board_type').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/' + boardType;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

board.init();