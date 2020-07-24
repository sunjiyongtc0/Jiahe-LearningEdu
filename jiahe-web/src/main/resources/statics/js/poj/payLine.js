

function initJs(){
    var myChart = echarts.init(document.getElementById('map1'));
    var myData = ['投诉率', '好评率', '成交率', '升学率']
    var lineData = [100, 100, 100, 100]

    var thisYearData = {
        1: [11, 82, 76, 79]
    }
    var timeLineData = [1]
    var background = "#061326";//"#0e2147"; //背景

    var option = {
        baseOption: {
            backgroundColor: background,
            timeline: {
                show: false,
                top: 0,
                data: []
            },
            legend : {
                top : '5%',
                left : '5%',
                itemWidth : 22,
                itemHeight : 22,
                itemGap: 343,
                icon : 'horizontal',
                textStyle : {
                    color : '#ffffff',
                    fontSize : 20,
                },
                data: ['2020教学评价']
            },
            grid: [{
                show: false,
                left: '5%',
                top: '10%',
                bottom: '8%',
                containLabel: true,
                width: '70%'
            },
                {// 进度条左侧文字位置调整 -- 开始
                    show: false,
                    left: '10%',
                    top: '10%',
                    bottom: '8%', // 进度条左侧文字之间的间距
                    width: '0%'
                },// 进度条左侧文字位置调整 -- 结束
                {// 进度条位置调整 -- 开始
                    show: false,
                    left: '18%',
                    top: '10%',
                    bottom: '8%', // 进度条之间的间距
                    containLabel: true,
                    width: '70%'
                }// 进度条位置调整 -- 结束
            ],
            xAxis: [{
                type: 'value',
                inverse: true,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                position: 'top',
                axisLabel: {
                    show: false
                },
                splitLine: {
                    show: false
                }
            }, {
                gridIndex: 1,
                show: false
            }, {
                gridIndex: 2,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                position: 'top',
                axisLabel: {
                    show: false
                },
                splitLine: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'category',
                inverse: true,
                position: 'right',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                data: myData
            }, {
                gridIndex: 1,
                type: 'category',
                inverse: true,
                position: 'left',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#ffffff',
                        fontSize: 20
                    }

                },
                data: myData.map(function(value) {
                    return {
                        value: value,
                        textStyle: {
                            align: 'center' // 进度条左侧文字对齐方式
                        }
                    }
                })
            }, {
                gridIndex: 2,
                type: 'category',
                inverse: true,
                position: 'left',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false

                },
                data: myData
            }],
            series: []

        },
        options: []
    }

    option.baseOption.timeline.data.push(timeLineData[0])
    option.options.push({
        series: [
            {
                type: 'pictorialBar',
                xAxisIndex: 2,
                yAxisIndex: 2,
                symbol: 'rect',
                itemStyle: {
                    normal: {
                        color: 'rgba(54,215,182,0.27)' // 进度条阴影颜色
                    }
                },
                barWidth: 10,
                symbolRepeat: true,// 是否显示进度条阴影
                symbolSize: 14, // 进度条阴影格子大小
                data: lineData,
                barGap: '-100%',
                barCategoryGap: 0,
                label: {
                    normal: {
                        show: true, // 是否显示进度条上方的百分比
                        formatter: (series) => {
                            return thisYearData[timeLineData[0]][series.dataIndex] + '%'
                        },
                        position: 'insideTopRight',
                        textStyle:{
                            color: '#ffffff', // 进度条上方百分比字体颜色
                            fontSize: 20,
                        },
                        offset: [0, -35],
                    }
                },
                z: -100,
                animationEasing: 'elasticOut',
                animationDelay: function (dataIndex, params) {
                    return params.index * 30;
                }
            }, {
                name: '2020教学评价',
                type: 'pictorialBar',
                xAxisIndex: 2,
                yAxisIndex: 2,
                symbol: 'rect',
                barWidth: 10,
                itemStyle: {
                    normal: {
                        barBorderRadius: 5,
                        color: '#36d7b6'  // 进度条颜色 #36d7b6
                    }
                },
                symbolRepeat: true,
                symbolSize: 14,	// 进度条格子大小
                data: thisYearData[timeLineData[0]],
                animationEasing: 'elasticOut',
                animationDelay: function (dataIndex, params) {
                    return params.index * 30 * 1.1;
                }
            }
        ]
    });

myChart.setOption(option);
initJs2();
}

function initJs2() {
    var myChart = echarts.init(document.getElementById('map2'));
    data = [{
        name: "教学收入",
        value: 275.17
        },
        {
            name: "教材收入",
            value: 48.35
        },
        {
            name: "1对1",
            value: 165.36
        }
    ];
    xAxisData = [];
    seriesData1 = [];
    sum = 0;
    barTopColor = ["#02c3f1", "#53e568", "#a154e9"];
    barBottomColor = ["rgba(2,195,241,0.1)", "rgba(83, 229, 104, 0.1)", "rgba(161, 84, 233, 0.1)"];
    data.forEach(item => {
        xAxisData.push(item.name);
        seriesData1.push(item.value);
        sum += item.value;
    });
    option = {
        backgroundColor: '#061326',
        title: {
            text: '收入占比（万元）',
            top:20,
            left:'center',
            textStyle:{
                color:'#fff',
                fontSize:20
            }
        },
        grid:{
            top:'25%',
            bottom:'15%'
        },
        xAxis: {
            data: xAxisData,
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                show: true,
                margin: 25,
                align: 'center',
                formatter: function(params, index) {

                    return '{a|' + (seriesData1[index] / sum * 100).toFixed(2) + '%}' + '\n' + '{b|' + params + '}';
                },
                textStyle: {
                    fontSize: 14,
                    color: '#ffffff',
                    rich: {
                        a: {
                            fontSize: 12,
                            color: '#ffffff'
                        },
                        b: {
                            height: 20,
                            fontSize: 14,
                            color: '#ffffff'
                        }
                    }
                }
            },
            interval: 0
        },
        yAxis: {
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            },
            axisLabel: {
                show: false
            }
        },
        series: [{
            name: '柱顶部',
            type: 'pictorialBar',
            symbolSize: [26, 10],
            symbolOffset: [0, -5],
            z: 12,
            itemStyle: {
                normal: {
                    color: function(params) {
                        return barTopColor[params.dataIndex];
                    }
                }
            },
            label: {
                show: true,
                position: 'top',
                fontSize: 16
            },
            symbolPosition: 'end',
            data: seriesData1,
        }, {
            name: '柱底部',
            type: 'pictorialBar',
            symbolSize: [26, 10],
            symbolOffset: [0, 5],
            z: 12,
            itemStyle: {
                normal: {
                    color: function(params) {
                        return barTopColor[params.dataIndex];
                    }
                }
            },
            data: seriesData1
        }, {
            name: '第一圈',
            type: 'pictorialBar',
            symbolSize: [47, 16],
            symbolOffset: [0, 11],
            z: 11,
            itemStyle: {
                normal: {
                    color: 'transparent',
                    borderColor: '#3ACDC5',
                    borderWidth: 2
                }
            },
            data: seriesData1
        }, {
            name: '第二圈',
            type: 'pictorialBar',
            symbolSize: [62, 22],
            symbolOffset: [0, 17],
            z: 10,
            itemStyle: {
                normal: {
                    color: 'transparent',
                    borderColor: barTopColor[0],
                    borderWidth: 2
                }
            },
            data: seriesData1
        }, {
            type: 'bar',
            itemStyle: {
                normal: {
                    color: function(params) {
                        return new echarts.graphic.LinearGradient(
                            0, 0, 0, 1,
                            [{
                                offset: 1,
                                color: barTopColor[params.dataIndex]
                            },
                                {
                                    offset: 0,
                                    color: barBottomColor[params.dataIndex]
                                }
                            ]
                        );
                    },
                    opacity: 0.8
                }
            },
            z: 16,
            silent: true,
            barWidth: 26,
            barGap: '-100%', // Make series be overlap
            data: seriesData1
        }]
    };

    myChart.setOption(option);
}
