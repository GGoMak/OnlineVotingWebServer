function drawChart1() {

    var chartDateformat     = 'HH시';
    var chartLineCount    = 10;

    $.ajax({
        type: 'GET',
        url: '/api/v3/rtvote',
        traditional : true
    }).done(function(receiveData) {

        // Create the data table.
        var data1 = new google.visualization.DataTable();
        var dataRow = [];
        data1.addColumn('datetime' , '시간');
        data1.addColumn('number'   , '투표수');

        var hours = 9;

        for(i in receiveData) {
            dataRow = [new Date(2020, 7, 3, hours), receiveData[i]];
            data1.addRow(dataRow);
            hours++;
        }

        // Set chart options
        var options1 = {
            isStacked: 'percent',
            focusTarget: 'category',
            height: 500,
            width: '100%',
            legend: {position: "top", textStyle: {fontSize: 13}},
            pointSize: 5,
            tooltip: {textStyle: {fontSize: 12}, showColorCode: true, trigger: 'both'},
            hAxis: {
                title: "시간", format: "HH", gridlines: {
                    count: chartLineCount, units: {
                        hours: {format: ['HH시']}
                    }
                }, textStyle: {fontSize: 12}
            },
            vAxis: {minValue: 100, viewWindow: {min: 0}, gridlines: {count: chartDateformat}, textStyle: {fontSize: 12}},
            animation: {startup: true, duration: 1000, easing: 'in'},
            annotations: {
                pattern: chartDateformat,
                textStyle: {
                    fontSize: 15,
                    bold: true,
                    italic: true,
                    color: '#871b47',
                    auraColor: '#d799ae',
                    opacity: 0.8,
                    pattern: chartDateformat
                }
            }
        };

        // Instantiate and draw our chart, passing in some options.

        var chart1 = new google.visualization.LineChart(document.getElementById('chart_div1'));
        chart1.draw(data1, options1);

    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function drawChart2() {

    $.ajax({
        type: 'GET',
        url: '/api/v3/gradevote',
        traditional: true
    }).done(function (receiveData) {
        // Create the data table.
        var data2 = new google.visualization.DataTable();
        data2.addColumn('string', '학년');
        data2.addColumn('number', '투표수');

        var data = ["1학년", receiveData[0]];
        data2.addRow(data);
        data = ["2학년", receiveData[1]];
        data2.addRow(data);
        data = ["3학년", receiveData[2]];
        data2.addRow(data);
        data = ["4학년", receiveData[3]];
        data2.addRow(data);

        // Set chart options
        var options2 = {'title':'학년별 투표수',
            'height' : 400,
            'pieHole': 0.4};

        // Instantiate and draw our chart, passing in some options.

        var chart2 = new google.visualization.PieChart(document.getElementById('chart_div2'));
        chart2.draw(data2, options2);

    }).fail(function (error) {
       alert(JSON.stringify(error));
    });
}

function drawChart3() {

    $.ajax({
        type: 'GET',
        url: '/api/v3/departmentvote/' + $('#sessionUser').val()
    }).done(function (receiveData) {
        var data3 = new google.visualization.DataTable();
        data3.addColumn('string', '결과');
        data3.addColumn('number', '투표수');

        var data = ["찬성", receiveData[0]];
        data3.addRow(data);
        data = ["반대", receiveData[1]];
        data3.addRow(data);

        // Set chart options
        var options3 = {'title': $('#sessionUser').val() + " 결과",
            'height' : 400};

        // Instantiate and draw our chart, passing in some options.

        var chart3 = new google.visualization.PieChart(document.getElementById('chart_div3'));
        chart3.draw(data3, options3);

    }).fail(function (error) {
        JSON.stringify(error);
    });
}

google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart1);
google.charts.setOnLoadCallback(drawChart2);
google.charts.setOnLoadCallback(drawChart3);