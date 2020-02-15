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

var chart = am4core.create("chartPercentages", am4charts.PieChart);
chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
/*
chart.data = [	
  {	
    country: name0,	 
    value: 401	   
  },	  
  {	  
    country: name1,	   
    value: 300	    
  }	  
];	
*/ 
chart.data = dataChatGlobalPercentage;
chart.radius = am4core.percent(70);
chart.innerRadius = am4core.percent(40);
chart.startAngle = 180;
chart.endAngle = 360;  

var series = chart.series.push(new am4charts.PieSeries());
series.dataFields.value = "value";
series.dataFields.category = "name";

series.slices.template.cornerRadius = 10;
series.slices.template.innerCornerRadius = 7;
series.slices.template.draggable = true;
series.slices.template.inert = true;
series.alignLabels = false;

series.hiddenState.properties.startAngle = 90;
series.hiddenState.properties.endAngle = 90;

chart.legend = new am4charts.Legend();
//Editar contenido tooltip
series.slices.template.tooltipText = "{category}";


//Hide shadow tooltip
series.tooltip.background.filters.clear(); 