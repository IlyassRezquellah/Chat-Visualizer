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

var interfaceColors = new am4core.InterfaceColorSet();

var chartAverage = am4core.create("chartAverage", am4charts.XYChart);
//Import data
chartAverage.data = dataCharsAverage;
//chart.data = data;

// the following line makes value axes to be arranged vertically.
chartAverage.bottomAxesContainer.layout = "horizontal";
chartAverage.bottomAxesContainer.reverseOrder = true;

var categoryAxis = chartAverage.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "category";
categoryAxis.renderer.grid.template.stroke = interfaceColors.getFor("background");
categoryAxis.renderer.grid.template.strokeOpacity = 1;
categoryAxis.renderer.grid.template.location = 1;
categoryAxis.renderer.minGridDistance = 20;

//Person 1
var valueAxis1 = chartAverage.xAxes.push(new am4charts.ValueAxis());
valueAxis1.tooltip.disabled = true;
valueAxis1.renderer.baseGrid.disabled = true;
valueAxis1.marginRight = 30;
valueAxis1.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis1.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis1.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis1.renderer.grid.template.strokeOpacity = 1;
valueAxis1.title.text = name0;

var series1 = chartAverage.series.push(new am4charts.LineSeries());
series1.dataFields.categoryY = "category";
series1.dataFields.valueX = name0;
series1.xAxis = valueAxis1;
series1.name = "Series 1";
var bullet1 = series1.bullets.push(new am4charts.CircleBullet());
bullet1.tooltipText = "{valueX.value}";

//Person 2
var valueAxis2 = chartAverage.xAxes.push(new am4charts.ValueAxis());
valueAxis2.tooltip.disabled = true;
valueAxis2.renderer.baseGrid.disabled = true;
valueAxis2.marginRight = 30;
valueAxis2.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis2.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis2.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis2.renderer.grid.template.strokeOpacity = 1;
valueAxis2.title.text = "general";

var series2 = chartAverage.series.push(new am4charts.ColumnSeries());
series2.dataFields.categoryY = "category";
series2.dataFields.valueX = "general";
series2.xAxis = valueAxis2;
series2.name = "Series 2";
var bullet2 = series2.bullets.push(new am4charts.CircleBullet());
bullet2.fillOpacity = 0;
bullet2.strokeOpacity = 0;
bullet2.tooltipText = "{valueX.value}";

//Person 3
var valueAxis3 = chartAverage.xAxes.push(new am4charts.ValueAxis());
valueAxis3.tooltip.disabled = true;
valueAxis3.renderer.baseGrid.disabled = true;
valueAxis3.renderer.gridContainer.background.fill = interfaceColors.getFor("alternativeBackground");
valueAxis3.renderer.gridContainer.background.fillOpacity = 0.05;
valueAxis3.renderer.grid.template.stroke = interfaceColors.getFor("background");
valueAxis3.renderer.grid.template.strokeOpacity = 1;
valueAxis3.title.text = name1;

var series3 = chartAverage.series.push(new am4charts.LineSeries());
series3.dataFields.categoryY = "category";
series3.dataFields.valueX = name1;
series3.xAxis = valueAxis3;
series3.name = "Series 3";
var bullet3 = series3.bullets.push(new am4charts.CircleBullet());
bullet3.tooltipText = "{valueX.value}";

chartAverage.cursor = new am4charts.XYCursor();
chartAverage.cursor.behavior = "zoomY";

var scrollbarY = new am4core.Scrollbar();
chartAverage.scrollbarY = scrollbarY;

//Hide shadow tooltip
series2.tooltip.background.filters.clear();
series1.tooltip.background.filters.clear();
series3.tooltip.background.filters.clear(); 

createChatTittle("chartAverage", "Chart average");
