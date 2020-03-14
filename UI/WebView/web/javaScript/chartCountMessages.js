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

// Create chart instance
var chartMessagesCount = am4core.create("chartCountMessages", am4charts.XYChart);


// Add data
chartMessagesCount.data = dataMessagesCount;

//chart.dataSource.url = "MessageCount.json";
//chart.dataSource.load();


// Set input format for the dates
chartMessagesCount.dateFormatter.inputDateFormat = "yyyy-MM-dd";

// Create axes
var dateAxis = chartMessagesCount.xAxes.push(new am4charts.DateAxis());
var valueAxis = chartMessagesCount.yAxes.push(new am4charts.ValueAxis());


// Create series
var series = chartMessagesCount.series.push(new am4charts.LineSeries());
series.dataFields.valueY = name0;
series.dataFields.dateX = "date";
//series.tooltipText = "{Christian}"
//series.tooltipText = "[bold]{date.formatDate()}:[/] {Christian}\n[bold]{previousDate.formatDate()}:[/] {Ilyass}";
series.strokeWidth = 2;
series.minBulletDistance = 15;

//Create series 2
var series2 = chartMessagesCount.series.push(new am4charts.LineSeries());
series2.dataFields.valueY = name1;
series2.dataFields.dateX = "date";
//series.tooltipText = "{Ilyass}"
series2.strokeWidth = 2;
series2.minBulletDistance = 15;
series2.strokeDasharray = "3,4";
//Con la linea de abajo las dos series tendran el mismo color
//series2.stroke = series.stroke;
//Con la linea de abajo la serie tiene un color personalizado 
series2.stroke = am4core.color("#953B3C");

series.tooltipText = `{dateX}[/]
`+name0+`: {`+name0+`}`;
series.tooltip.pointerOrientation = "vertical";

series2.tooltipText = `{dateX}[/]
`+name1+`: {`+name1+`}`;
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
chartMessagesCount.cursor = new am4charts.XYCursor();
chartMessagesCount.cursor.behavior = "panXY";
chartMessagesCount.cursor.xAxis = dateAxis;
chartMessagesCount.cursor.snapToSeries = series;

// Create vertical scrollbar and place it before the value axis
chartMessagesCount.scrollbarY = new am4core.Scrollbar();
chartMessagesCount.scrollbarY.parent = chartMessagesCount.leftAxesContainer;
chartMessagesCount.scrollbarY.toBack();

// Create a horizontal scrollbar with previe and place it underneath the date axis
chartMessagesCount.scrollbarX = new am4charts.XYChartScrollbar();
chartMessagesCount.scrollbarX.series.push(series);
chartMessagesCount.scrollbarX.parent = chartMessagesCount.bottomAxesContainer;

//Hide shadow tooltip
series2.tooltip.background.filters.clear();
series.tooltip.background.filters.clear();



//Hide shadow tooltip
series.tooltip.background.filters.clear(); 

createChatTittle("chartCountMessages", "Chart messageWordChar Count");
