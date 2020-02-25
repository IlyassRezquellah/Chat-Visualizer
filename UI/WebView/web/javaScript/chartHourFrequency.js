
// Themes begin
am4core.useTheme(am4themes_dataviz);
am4core.useTheme(am4themes_animated);
// Themes end

/* Create chart instance */
var chartHourFrequency = am4core.create("chartHourFrequency", am4charts.RadarChart);

/* Add data */
/*
chart.data = [ {
    "country": "9H",
    "litres": 40
  }];*/

  chartHourFrequency.data =HourAverage ;

  // Create axes 
var categoryAxis = chartHourFrequency.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "hour";

var valueAxis = chartHourFrequency.yAxes.push(new am4charts.ValueAxis());
valueAxis.renderer.axisFills.template.fill = chartHourFrequency.colors.getIndex(2);
valueAxis.renderer.axisFills.template.fillOpacity = 0.05;

// Create and configure series 
var series = chartHourFrequency.series.push(new am4charts.RadarSeries());
series.dataFields.valueY = "data";
series.dataFields.categoryX = "hour";
series.name = "Sales";
series.strokeWidth = 3;

/*
chart.radius = am4core.percent(95);
chart.startAngle = 270 - 180;
chart.endAngle = 270 + 180;
chart.innerRadius = am4core.percent(60);

var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "hour";
categoryAxis.renderer.labels.template.location = 0.5;
categoryAxis.renderer.grid.template.strokeOpacity = 0.1;
categoryAxis.renderer.axisFills.template.disabled = true;
categoryAxis.mouseEnabled = false;

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.tooltip.disabled = true;
valueAxis.renderer.grid.template.strokeOpacity = 0.05;
valueAxis.renderer.axisFills.template.disabled = true;
valueAxis.renderer.axisAngle = 260;
valueAxis.renderer.labels.template.horizontalCenter = "right";
valueAxis.min = 0;

var series1 = chart.series.push(new am4charts.RadarColumnSeries());
series1.columns.template.radarColumn.strokeOpacity = 1;
series1.name = "Series 1";
series1.dataFields.categoryX = "hour";
series1.columns.template.tooltipText = "{name}: {valueY.data}";
series1.dataFields.valueY = "data";
series1.stacked = true;


chart.seriesContainer.zIndex = -1;

var slider = chart.createChild(am4core.Slider);
slider.start = 0.5;
slider.exportable = false;
slider.events.on("rangechanged", function() {
  var start = slider.start;

  chart.startAngle = 270 - start * 179 - 1;
  chart.endAngle = 270 + start * 179 + 1;

  valueAxis.renderer.axisAngle = chart.startAngle;
});*/
//Hide shadow tooltip
series.tooltip.background.filters.clear(); 
