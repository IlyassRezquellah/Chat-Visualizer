
// Themes begin
am4core.useTheme(am4themes_dataviz);
am4core.useTheme(am4themes_animated);
// Themes end

/* Create chart instance */
var chart = am4core.create("chartHourFrequency", am4charts.RadarChart);

/* Add data */
chart.data = [{
  "country": "0H",
  "litres": 301
}, {
  "country": "1H",
  "litres": 40
}, {
  "country": "2H",
  "litres": 301
}, {
  "country": "3H",
  "litres": 266
}, {
  "country": "4H",
  "litres": 165
}, {
  "country": "5H",
  "litres": 139
}, {
  "country": "6H",
  "litres": 336
}, {
  "country": "7H",
  "litres": 290
}, {
  "country": "8H",
  "litres": 325
}, {
  "country": "9H",
  "litres": 160
}, {
    "country": "10H",
    "litres": 320
  }, {
    "country": "11H",
    "litres": 400
  }, {
    "country": "12H",
    "litres": 250
  }, {
    "country": "13H",
    "litres": 180
  }, {
    "country": "14H",
    "litres": 97
  }, {
    "country": "15H",
    "litres": 100
  }, {
    "country": "16H",
    "litres": 300
  }, {
    "country": "17H",
    "litres": 350
  }, {
    "country": "18H",
    "litres": 280
  }, {
    "country": "19H",
    "litres": 120
  }, {
    "country": "20H",
    "litres": 70
  }, {
    "country": "21H",
    "litres": 260
  }, {
    "country": "22H",
    "litres": 340
  }, {
    "country": "23H",
    "litres": 400
  }, {
    "country": "9H",
    "litres": 40
  }];

/* Create axes */
var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "country";

var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
valueAxis.renderer.axisFills.template.fill = chart.colors.getIndex(2);
valueAxis.renderer.axisFills.template.fillOpacity = 0.05;

/* Create and configure series */
var series = chart.series.push(new am4charts.RadarSeries());
series.dataFields.valueY = "litres";
series.dataFields.categoryX = "country";
series.name = "Sales";
series.strokeWidth = 3;

//Hide shadow tooltip
series.tooltip.background.filters.clear();