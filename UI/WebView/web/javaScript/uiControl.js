function setMessages(){
    chartMessagesCount.data = dataMessagesCount
    chartAverage.data = dataMessagesAverage
}
function setWords(){
    chartMessagesCount.data = dataWordsCount
    chartAverage.data = dataWordsAverage
}
function setCharts(){
    chartMessagesCount.data = dataChartsCount
    chartAverage.data = dataCharsAverage
}

function exportPDF() {
  
    Promise.all([
        chartMessagesCount.exporting.pdfmake,
        chartMessagesCount.exporting.getImage("png"),
    ]).then(function(res) { 
      
      var pdfMake = res[0];
      
      // pdfmake is ready
      // Create document template
      var doc = {
        pageSize: "A4",
        pageOrientation: "portrait",
        pageMargins: [30, 30, 30, 30],
        content: []
      };
      
      doc.content.push({
        text: "In accumsan velit in orci tempor",
        fontSize: 20,
        bold: true,
        margin: [0, 20, 0, 15]
      });
      pdfMake.createPdf(doc).download("report.pdf");
    });
}