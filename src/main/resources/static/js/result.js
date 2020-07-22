function drawChart() {

    var department = $('#department').val();

    $.ajax({
        type: 'GET',
        url: '/api/v3/result/' + department,
    }).done(function (receiveData) {
        var data = new google.visualization.DataTable();
        data.addColumn('string', '학과');
        data.addColumn('number', '결과');

        var tmpdata = ["찬성", receiveData[0]];
        data.addRow(tmpdata);
        tmpdata = ["반대", receiveData[1]];
        data.addRow(tmpdata);
        tmpdata = ["미투표", receiveData[2]];
        data.addRow(tmpdata);

        var options = {
            title: '투표결과',
            isStacked: 'percent',
            focusTarget: 'category',
            height: 400,
            width: '100%',
            legend: {position: "top", textStyle: {fontSize: 13}},
            pointSize: 5,
            tooltip: {textStyle: {fontSize: 12}, showColorCode: true, trigger: 'both'},
            vAxis: {minValue: 100, viewWindow: {min: 0}, textStyle: {fontSize: 12}},
            animation: {startup: true, duration: 1000, easing: 'in'},
        };

        var chart = new google.visualization.PieChart(document.getElementById('chart_div4'));
        chart.draw(data, options);

    }).fail(function (error){
        alert(JSON.stringify(error));
    })
}

google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);