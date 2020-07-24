function findUser(idx) {

    var studentId = $('#input'+idx).val();

    $.ajax({
        type: 'POST',
        url: '/api/v3/getuser/' + studentId,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8;',
    }).done(function(jsonData){
        $('#candidate'+idx+' td').remove();
        $('#candidate'+idx+' tbody').append("<tr><td>" + jsonData.studentId + "</td><td>" + jsonData.department + "</td><td>" + jsonData.name + "</td></tr>");
    }).fail(function(error){
        if(error.responseText == "Already Regist") {
            alert("이미 등록된 후보입니다.");
            $('#candidate' + idx + ' td').remove();
        }
    });
}

function registCandidate(){

    var formData = new FormData();

    formData.append("department", $("select[name=department]").val());
    formData.append("teamName", $('#teamName').val());
    formData.append("candidate1", $("#candidate1 td").eq(0).text());
    formData.append("candidate2", $("#candidate2 td").eq(0).text());
    formData.append("candidate3", $("#candidate3 td").eq(0).text());
    formData.append("poster", $("input[name=poster_file]")[0].files[0]);
    formData.append("candidate1_pic", $("input[name=candidate1_file]")[0].files[0]);
    formData.append("candidate2_pic", $("input[name=candidate2_file]")[0].files[0]);

    var data = {
        department: $('#department').val(),
        teamName: $('#teamName').val(),
        candidate1: $('#candidate1 td').eq(0).text(),
        candidate2: $('#candidate2 td').eq(0).text(),
        candidate3: $('#candidate3 td').eq(0).text(),
        candidate1_pic: $('#candidate1_file').val(),
        candidate2_pic: $('#candidate2_file').val(),
        candidate3_pic: $('#candidate3_file').val()
    };
    if($('#department option:selected').val() == "--학과--"){
        alert("학과를 선택해주세요");
        return;
    }

    if($('#teamName').val() == ""){
        alert("팀 이름을 작성해주세요.");
        return;
    }

    if($('#poster_file').val() == ""){
        alert("포스터를 등록해주세요.");
        return;
    }

    if(data.candidate1 == ""){
        alert("정후보가 지정되지 않았습니다.");
    }
    else if(data.candidate2 == ""){
        alert("부후보가 지정되지 않았습니다.");
    }
    else if(data.candidate3 == ""){
        if(confirm("부후보가 1명입니다. 이대로 진행하시겠습니까?") == true){

            if(data.candidate1_pic == ""){
                alert("정후보 사진을 등록해주세요.");
                return;
            }

            else if(data.candidate2_pic == ""){
                alert("부후보1 사진을 등록해주세요.");
                return;
            }

            $.ajax({
                type: 'POST',
                url: '/api/v3/reguser',
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
                data: formData
            }).done(function(){
                alert("후보등록이 완료되었습니다.");
                window.location.href="/candidatelist";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        }
        else {
            return false;
        }
    }
    else{

        if(data.candidate1_pic == ""){
            alert("정후보 사진을 등록해주세요.");
            return;
        }

        else if(data.candidate2_pic == ""){
            alert("부후보1 사진을 등록해주세요.");
            return;
        }

        else if(data.candidate3_pic == ""){
            alert("부후보2 사진을 등록해주세요.");
            return;
        }

        formData.append("candidate3_pic", $("input[name=candidate3_file]")[0].files[0]);
        $.ajax({
            type: 'POST',
            url: '/api/v3/reguser',
            enctype: "multipart/form-data",
            processData: false,
            contentType: false,
            data: formData
        }).done(function(){
            alert("후보등록이 완료되었습니다.");
            window.location.href="/candidatelist";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}