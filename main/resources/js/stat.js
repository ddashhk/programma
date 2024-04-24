google.charts.load("current", {packages: ['corechart']});
google.charts.setOnLoadCallback(drawIntensity);
google.charts.setOnLoadCallback(drawKPD);

function drawIntensity() {
    var integers0 = parseInt(document.querySelector('#integers0').textContent);
    var integers1 = parseInt(document.querySelector('#integers1').textContent);
    var integers2 = parseInt(document.querySelector('#integers2').textContent);
    var integers3 = parseInt(document.querySelector('#integers3').textContent);
    var integers4 = parseInt(document.querySelector('#integers4').textContent);
    var strings0 = document.querySelector('#strings0').textContent;
    var strings1 = document.querySelector('#strings1').textContent;
    var strings2 = document.querySelector('#strings2').textContent;
    var strings3 = document.querySelector('#strings3').textContent;
    var strings4 = document.querySelector('#strings4').textContent;
    var data = google.visualization.arrayToDataTable([
        ['Сотрудник', 'Трудовые затраты', {role: 'style'}, {role: 'annotation'}],
        [strings0, integers0, "red", integers0],
        [strings1, integers1, "brown", integers1],
        [strings2, integers2, "green", integers2],
        [strings3, integers3, "blue", integers3],
        [strings4, integers4, "purple", integers4],
    ]);


    var options = {
        title: 'Топ-5 лучших работников по трудовым затратам',
        hAxis: {title: 'Сотрудник'},
        vAxis: {title: 'Трудовые затраты'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('drawIntensity'));
    chart.draw(data, options);
}



function drawKPD() {
    let res = [['Сотрудник', 'КПД']];

    for (let i = 0; i < stringsKPD.length; i++) {
        res.push([stringsKPD[i], intKPD[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Топ-5 лучших работников по КПД',
        hAxis: {title: 'Сотрудник'},
        vAxis: {title: 'КПД'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('drawKPD'));
    chart.draw(data, options);
}