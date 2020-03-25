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

var chartPercentages = am4core.create("chartPercentages", am4charts.PieChart);
chartPercentages.hiddenState.properties.opacity = 0; // this creates initial fade-in
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
chartPercentages.data = dataChatGlobalPercentage;
chartPercentages.radius = am4core.percent(70);
chartPercentages.innerRadius = am4core.percent(40);
chartPercentages.startAngle = 180;
chartPercentages.endAngle = 360;  

var series = chartPercentages.series.push(new am4charts.PieSeries());
series.dataFields.value = "value";
series.dataFields.category = "name";

series.slices.template.cornerRadius = 10;
series.slices.template.innerCornerRadius = 7;
series.slices.template.draggable = true;
series.slices.template.inert = true;
series.alignLabels = false;

series.hiddenState.properties.startAngle = 90;
series.hiddenState.properties.endAngle = 90;

chartPercentages.legend = new am4charts.Legend();
//Editar contenido tooltip
series.slices.template.tooltipText = "{category}";


//Hide shadow tooltip
series.tooltip.background.filters.clear();

createChatTittle("chartPercentages", "Who is more active?");
