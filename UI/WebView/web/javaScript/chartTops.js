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


var chartTops = am4core.create("chartTops", am4plugins_wordCloud.WordCloud);
var series = chartTops.series.push(new am4plugins_wordCloud.WordCloudSeries());

series.data = dataChartTops;

series.accuracy = 4;
series.step = 15;
series.rotationThreshold = 0.7;
series.labels.template.tooltipText = "{word}: {value}";
series.fontFamily = "Courier New";
series.maxFontSize = am4core.percent(30);

series.dataFields.word = "word";
series.dataFields.value = "weight";
//Hide shadow tooltip
series.tooltip.background.filters.clear();  