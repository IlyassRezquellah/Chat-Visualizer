/**
 * ---------------------------------------
 * This demo was created using amCharts 4.
 * 
 * For more information visit:
 * https://www.amcharts.com/
 * 
 * Documentation is available at:
 * https://www.amcharts.com/docs/v4/
 * ---------------------------------------
 */
//https://www.w3schools.com/js/js_json_http.asp
// Load json
var xmlhttp = new XMLHttpRequest();
var url = "hola/MessageCount.json";
xmlhttp.onreadystatechange = function(){
    if (this.readyState == 4 && this.status == 200) {
        chart.data = JSON.parse(this.responseText);
        //myLog(chart.data);
    }
};
xmlhttp.open("GET", url, true);
xmlhttp.send();

//Log
function myLog(arr) {
    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
        out += '<a href="' + arr[i].url + '">' +
        arr[i].display + '</a><br>';
    }
    document.getElementById("log").innerHTML = out;
}

// Themes begin
am4core.useTheme(am4themes_dataviz);
am4core.useTheme(am4themes_animated);
// Themes end

// Create chart instance
var chart = am4core.create("chartdiv", am4charts.XYChart);

//chart.jsonData = jsonData;
//chart.dataSource.url = "https://raw.githubusercontent.com/EliassReque/FinalProject/master/Graficas/Grafica%20Total%20Mensajes/hola/MessageCount.json";

// Set input format for the dates
chart.dateFormatter.inputDateFormat = "yyyy-MM-dd";

// Create axes
var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());


// Create series
var series = chart.series.push(new am4charts.LineSeries());
series.dataFields.valueY = "Christian";
series.dataFields.dateX = "date";
//series.tooltipText = "{Christian}"
//series.tooltipText = "[bold]{date.formatDate()}:[/] {Christian}\n[bold]{previousDate.formatDate()}:[/] {Ilyass}";
series.strokeWidth = 2;
series.minBulletDistance = 15;

//Create series 2
var series2 = chart.series.push(new am4charts.LineSeries());
series2.dataFields.valueY = "Ilyass";
series2.dataFields.dateX = "date";
//series.tooltipText = "{Ilyass}"
series2.strokeWidth = 2;
series2.minBulletDistance = 15;
series2.strokeDasharray = "3,4";
series2.stroke = series.stroke;

series.tooltipText = `{dateX}[/]
Christian: {Christian}`;
series.tooltip.pointerOrientation = "vertical";

series2.tooltipText = `{dateX}[/]
Ilyass: {Ilyass}`;
series2.tooltip.pointerOrientation = "vertical";

//Sacar las bullets en hover --> Bullet series 2
var bullet2 = series2.bullets.push(new am4charts.Bullet());
bullet2.width = 12;
bullet2.height = 12;
bullet2.horizontalCenter = "middle";
bullet2.verticalCenter = "middle";

//Darle forma a los bullets
var triangle = bullet2.createChild(am4core.Triangle);
triangle.strokeWidth = 2;
triangle.direction = "top";
triangle.width = 12;
triangle.height = 12;
triangle.fill = am4core.color("#fff");

var bullethover = bullet2.states.create("hover");
bullethover.properties.scale = 1.3;


// Drop-shaped tooltips
series.tooltip.background.cornerRadius = 20;
series.tooltip.background.strokeOpacity = 0;
series.tooltip.pointerOrientation = "vertical";
series.tooltip.label.minWidth = 40;
series.tooltip.label.minHeight = 40;
series.tooltip.label.textAlign = "middle";
series.tooltip.label.textValign = "middle";

//Sacar las bullets en hover  --> Bullet series 1
var bullet = series.bullets.push(new am4charts.CircleBullet());
bullet.circle.strokeWidth = 2;
bullet.circle.radius = 4;
bullet.circle.fill = am4core.color("#fff");

var bullethover = bullet.states.create("hover");
bullethover.properties.scale = 1.3;

// Make a panning cursor
chart.cursor = new am4charts.XYCursor();
chart.cursor.behavior = "panXY";
chart.cursor.xAxis = dateAxis;
chart.cursor.snapToSeries = series;

// Create vertical scrollbar and place it before the value axis
chart.scrollbarY = new am4core.Scrollbar();
chart.scrollbarY.parent = chart.leftAxesContainer;
chart.scrollbarY.toBack();

// Create a horizontal scrollbar with previe and place it underneath the date axis
chart.scrollbarX = new am4charts.XYChartScrollbar();
chart.scrollbarX.series.push(series);
chart.scrollbarX.parent = chart.bottomAxesContainer;

