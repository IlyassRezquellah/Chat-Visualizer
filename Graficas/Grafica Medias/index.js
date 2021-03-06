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

// Themes begin
am4core.useTheme(am4themes_dataviz);
am4core.useTheme(am4themes_animated);
// Themes end
/*
var data = [];
var value1 = 100;
var value2 = 200;
var value3 = 400;

var names = ["December",
  "November",
  "October",
  "September",
  "August",
  "July",
  "June",
  "May",
  "April",
  "March",
  "February",
  "January" 
];*/

/*
for (var i = 0; i < names.length; i++) {
  value1 += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 5);
  value2 += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 5);
  value3 += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 5);
  data.push({ category: names[i], value1: value1, value2:value2, value3:value3 });
}*/


var interfaceColors = new am4core.InterfaceColorSet();

var chart = am4core.create("chartdiv", am4charts.XYChart);
//Import data
chart.dataSource.url = "hola2/test.json";
//chart.data = data;
// the following line makes value axes to be arranged vertically.
chart.bottomAxesContainer.layout = "horizontal";
chart.bottomAxesContainer.reverseOrder = true;

var categoryAxis = chart.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.grid.template.stroke = interfaceColors.getFor("background");
categoryAxis.renderer.grid.template.strokeOpacity = 1;
categoryAxis.renderer.grid.template.location = 1;
categoryAxis.renderer.minGridDistance = 20;

//Person 1
var valueAxis1 = chart.xAxes.push(new am4charts.ValueAxis());
valueAxis1.tooltip.disabled = true;
valueAxis1.renderer.baseGrid.disabled = true;
valueAxis1.marginRight = 30;
valueAxis1.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis1.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis1.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis1.renderer.grid.template.strokeOpacity = 1;
valueAxis1.title.text = "Person 1";

var series1 = chart.series.push(new am4charts.LineSeries());
series1.dataFields.categoryY = "category";
series1.dataFields.valueX = "value1";
series1.xAxis = valueAxis1;
series1.name = "Series 1";
var bullet1 = series1.bullets.push(new am4charts.CircleBullet());
bullet1.tooltipText = "{valueX.value}";

//Person 2
var valueAxis2 = chart.xAxes.push(new am4charts.ValueAxis());
valueAxis2.tooltip.disabled = true;
valueAxis2.renderer.baseGrid.disabled = true;
valueAxis2.marginRight = 30;
valueAxis2.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis2.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis2.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis2.renderer.grid.template.strokeOpacity = 1;
valueAxis2.title.text = "General";

var series2 = chart.series.push(new am4charts.ColumnSeries());
series2.dataFields.categoryY = "category";
series2.dataFields.valueX = "value2";
series2.xAxis = valueAxis2;
series2.name = "Series 2";
var bullet2 = series2.bullets.push(new am4charts.CircleBullet());
bullet2.fillOpacity = 0;
bullet2.strokeOpacity = 0;
bullet2.tooltipText = "{valueX.value}";

//Person 3
var valueAxis3 = chart.xAxes.push(new am4charts.ValueAxis());
valueAxis3.tooltip.disabled = true;
valueAxis3.renderer.baseGrid.disabled = true;
valueAxis3.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis3.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis3.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis3.renderer.grid.template.strokeOpacity = 1;
valueAxis3.title.text = "Person 2";

var series3 = chart.series.push(new am4charts.LineSeries());
series3.dataFields.categoryY = "category";
series3.dataFields.valueX = "value3";
series3.xAxis = valueAxis3;
series3.name = "Series 3";
var bullet3 = series3.bullets.push(new am4charts.CircleBullet());
bullet3.tooltipText = "{valueX.value}";

chart.cursor = new am4charts.XYCursor();
chart.cursor.behavior = "zoomY";

var scrollbarY = new am4core.Scrollbar();
chart.scrollbarY = scrollbarY;