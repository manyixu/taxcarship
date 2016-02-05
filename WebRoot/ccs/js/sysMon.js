
	var clientSec = 7;//页面刷新服务器信息频率（毫秒）
	var serverSec = 20;//服务端更新系统信息频率（毫秒）
	
	
	
	function loadMemDataO(sysInfoObj){
		$("#cont_mem").html('<b>计算机名：<b>'+sysInfoObj.computerName+'<br>'+
				'<b>ip：<b>'+sysInfoObj.ip+'<br>'+
				'<b>javaVersion：<b>'+sysInfoObj.javaVersion+'<br>'+
				'<b>jvm总内存：<b>'+sysInfoObj.jvmTotalMem+'<br>'+
				'<b>jvm当前分配总内存：<b>'+sysInfoObj.jvmFreeMem+'<br>'+
				'<b>jvm最大可分配内存：<b>'+sysInfoObj.jvmMaxMem+'<br>'+
				'<b>系统总内存：<b>'+sysInfoObj.totalMem+'<br>'+
				'<b>系统可用内存：<b>'+sysInfoObj.freeMem
		);
	}
	
	// $('#cont_cpu').highcharts({
	function loadCpuData() {
		$('#cont_cpu').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: 'CPU使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [80],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 80;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	}
	function loadDiskData(){

		$('#cont_disk').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: '磁盘使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [50],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 60;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}
	function loadMemData(){

		$('#cont_mem').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: '内存使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [40],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 40;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}
	function loadJvmMemData(){

		$('#cont_jvmmem').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: 'JVM内存使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [20],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 20;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}

	//DB Part Below
	function loadDbCpuData() {
		$('#cont_cpu_db').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: 'CPU使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [80],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 80;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	}
	function loadDbDiskData(){

		$('#cont_disk_db').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: '磁盘使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [50],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 60;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}
	function loadDbMemData(){

		$('#cont_mem_db').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: '内存使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [40],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 40;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}
	function loadDbTsData(){

		$('#cont_tablespace_db').highcharts({
			
		    chart: {
		        type: 'gauge',
		        plotBackgroundColor: null,
		        plotBackgroundImage: null,
		        plotBorderWidth: 0,
		        plotShadow: false
		    },
		    
		    title: {
		        text: '表空间使用率'
		    },
		    
		    pane: {
		        startAngle: -150,
		        endAngle: 150,
		        background: [{
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#FFF'],
		                    [1, '#333']
		                ]
		            },
		            borderWidth: 0,
		            outerRadius: '109%'
		        }, {
		            backgroundColor: {
		                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
		                stops: [
		                    [0, '#333'],
		                    [1, '#FFF']
		                ]
		            },
		            borderWidth: 1,
		            outerRadius: '107%'
		        }, {
		            // default background
		        }, {
		            backgroundColor: '#DDD',
		            borderWidth: 0,
		            outerRadius: '105%',
		            innerRadius: '103%'
		        }]
		    },
		       
		    // the value axis
		    yAxis: {
		        min: 0,
		        max: 100,
		        
		        minorTickInterval: 'auto',
		        minorTickWidth: 1,
		        minorTickLength: 10,
		        minorTickPosition: 'inside',
		        minorTickColor: '#666',
		
		        tickPixelInterval: 30,
		        tickWidth: 2,
		        tickPosition: 'inside',
		        tickLength: 10,
		        tickColor: '#666',
		        labels: {
		            step: 2,
		            rotation: 'auto'
		        },
		        title: {
		            text: ''//km/h
		        },
		        plotBands: [{
		            from: 0,
		            to: 25,
		            color: '#55BF3B' // green
		        }, {
		            from: 25,
		            to: 50,
		            color: '#FFFF00' // yellow
		        }, {
		            from: 50,
		            to: 75,
		            color: '#FFDAB9' //
		        }, {
		            from: 75,
		            to: 100,
		            color: '#FF0000' // red
		        }]        
		    },
		
		    series: [{
		        name: '已使用',
		        data: [10],
		        tooltip: {
		            valueSuffix: ' %'
		        }
		    }]
		
		}, 
		// Add some life
		function (chart) {
			if (!chart.renderer.forExport) {
			    setInterval(function () {

			        var newVal = 10;
			        
			        point.update(newVal);//更新
			        
			    }, 3000);//更新频率
			}
		});
	
	}
	/**
	 * 点击磁盘监控查看详细磁盘信息
	 * @return
	 */
	function diskMonDet(){
		SysInfoUtil.getDiskListObj(loadDiskDataDet);
	}
	function loadDiskDataDet(diskListStr){//磁盘监控回调函数展示服务端获取的磁盘信息
		var diskListObj = JSON.parse(diskListStr);
		 $('#cont_disk_det').highcharts({
		        chart: {
		            type: 'pie',
		            options3d: {
						enabled: true,
		                alpha: 45,
		                beta: 0
		            }
		        },
		        title: {
		            text: '系统磁盘分区'
		        },
		        tooltip: {
		            pointFormat: '盘符 : <b>{point.devName}</b><br>'+
		            			'分区总大小 : <b>{point.totalC}</b><br>'+
		            			'可用空间 : <b>{point.free}</b><br>'+
		            			'磁盘利用率 : <b>{point.userPercent}</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                depth: 35,
		                dataLabels: {
		                    enabled: true,
		                    format: '{point.devName}'// 数据饼块标签名称
		                }
		            }
		        },
		        series: [{
		            data: diskListObj
		        }]

		    });
	}
	
	function loadCpuDynamicData(){

		$('#cont_db').highcharts({
		chart: {  
	          renderTo: 'cont_db',  
	          defaultSeriesType: 'spline',  
	          marginRight: 10  
	        },  
	        title: {  
	          text: 'CPU使用率动态曲线图'  
	        },  
	        xAxis: {  
	          title: {  
	            text: '时间'  
	          },  
	          // linear" or "datetime"
	          type: 'datetime',  
	          // 坐标间隔
	          tickPixelInterval: 150  
	        },  
	        yAxis: {  
	          title: {  
	            text: '使用率'  
	          },  
	          // 指定y=3直线的样式
	          plotLines: [  
	            {  
	              value: 0,  
	              width: 10,  
	              color: '#808080'  
	            }  
	          ]  
	        },  
	        // 鼠标放在某个点上时的提示信息
	        // dateFormat,numberFormat是highCharts的工具类
	        tooltip: {  
	          formatter: function() {  
	            return '<b>' + this.series.name + '</b><br/>' +  
	                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +  
	                    Highcharts.numberFormat(this.y, 2);  
	          }  
	        },  
	        // 曲线的示例说明，像地图上得图标说明一样
	        legend: {  
	          enabled: true  
	        },  
	        // 把曲线图导出成图片等格式
	        exporting: {  
	          enabled: true  
	        },  
	        // 放入数据
	        series: [  
	          {  
	            name: 'CPU使用率',  
	            data: (function() {  
	              // 初始化数据  
	              var data = [],  
	                      time = (new Date()).getTime(),  
	                      i;  
	              for (i = -19; i <= 0; i++) {  
	                data.push({  
	                  x: time + i * 1000,  
	                  y: Math.random() * 100  
	                });  
	              }  
	              return data;  
	            })()  
	          }  
	        ]  
	      });  
	
	}