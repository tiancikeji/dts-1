
$(function() {
	var cables = new Array();
	$.ajax({
		url : "http://localhost:8080/dts/monitor/19.json",
		type : "get",
		dataType : "json",
		success : init_data
	});
	function init_data(data) {
		for (var i = 0; i < data.length; i++) {
			cables[i] = [ data[i].length, data[i].temperature ];
		}
		$('#highcharts').highcharts({
			chart : {
				type : 'spline'
			},
			title : {
				text : '温度图表'
			},
			xAxis : {
				type : '长度',
				min : 0,
				max : 1000
			},
			yAxis : {
				title : {
					text : '温度'
				},
				min : 0,
				max : 100
			},
			// tooltip: {
			// formatter: function() {
			// return '<b>'+ this.series.name +'</b><br/>'+
			// Highcharts.dateFormat('%e. %b', this.x) +': '+ this.y +' m';
			// }
			// },
			series : [ {
				data : cables
			} ]
		});
	}


});