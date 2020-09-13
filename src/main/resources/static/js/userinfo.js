$("#usertable tr button").click(function(){

    var tr = $(this).parent().parent();
    var td = tr.children();

    var data = {
        idx : td.eq(0).text(),
        value : td.eq(5).children().select().val()
    };

    $.ajax({
        type: 'POST',
        url: '/api/v3/role',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function() {
        alert("변경되었습니다.");
        window.location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
});

function roleChangeAllVoter() {

    var data = {
        idx : 0,
        value : "VOTER"
    };

    $.ajax({
        type: 'POST',
        url: '/api/v3/role',
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function() {
        alert("변경되었습니다.");
        window.location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}