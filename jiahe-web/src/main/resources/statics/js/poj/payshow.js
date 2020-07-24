var name_title = "全国招生情况";
var subname = '数据截止至2020年6月30日';
var nameColor = " rgb(55, 75, 113)";
var name_fontFamily = '等线';
var subname_fontSize = 15;
var name_fontSize = 18;
var mapName = 'china';
var data = [
    {name:"北京",value:114},
    {name:"天津",value:31},
    {name:"河北",value:65},
    {name:"山西",value:35},
    {name:"内蒙古",value:148},
    {name:"辽宁",value:4903},
    {name:"吉林",value:174},
    {name:"黑龙江",value:243},
    {name:"上海",value:11},
    {name:"江苏",value:12},
    {name:"浙江",value:42},
    {name:"安徽",value:20},
    {name:"福建",value:41},
    {name:"江西",value:62},
    {name:"山东",value:58},
    {name:"河南",value:78},
    {name:"湖北",value:32},
    {name:"湖南",value:77},
    {name:"重庆",value:18},
    {name:"四川",value:14},
    {name:"贵州",value:12},
    {name:"云南",value:7},
    {name:"西藏",value:1},
    {name:"陕西",value:63},
    {name:"甘肃",value:26},
    {name:"青海",value:8},
    {name:"宁夏",value:17},
    {name:"新疆",value:14},
    {name:"广东",value:35},
    {name:"广西",value:7},
    {name:"海南",value:4},
];

var geoCoordMap = {};
var toolTipData = [
    {name:"北京",value:[{name:"报名人数",value:114},{name:"为学完",value:1}]},
    {name:"天津",value:[{name:"报名人数",value:31},{name:"为学完",value:0}]},
    {name:"河北",value:[{name:"报名人数",value:65},{name:"为学完",value:1}]},
    {name:"山西",value:[{name:"报名人数",value:35},{name:"为学完",value:0}]},
    {name:"内蒙古",value:[{name:"报名人数",value:148},{name:"为学完",value:0}]},
    {name:"辽宁",value:[{name:"报名人数",value:4903},{name:"为学完",value:0}]},
    {name:"吉林",value:[{name:"报名人数",value:174},{name:"为学完",value:0}]},
    {name:"黑龙江",value:[{name:"报名人数",value:243},{name:"为学完",value:1}]},
    {name:"上海",value:[{name:"报名人数",value:12},{name:"为学完",value:1}]},
    {name:"江苏",value:[{name:"报名人数",value:12},{name:"为学完",value:0}]},
    {name:"浙江",value:[{name:"报名人数",value:42},{name:"为学完",value:0}]},
    {name:"安徽",value:[{name:"报名人数",value:20},{name:"为学完",value:0}]},
    {name:"福建",value:[{name:"报名人数",value:10},{name:"为学完",value:0}]},
    {name:"江西",value:[{name:"报名人数",value:16},{name:"为学完",value:0}]},
    {name:"山东",value:[{name:"报名人数",value:15},{name:"为学完",value:0}]},
    {name:"河南",value:[{name:"报名人数",value:27},{name:"为学完",value:2}]},
    {name:"湖北",value:[{name:"报名人数",value:14},{name:"为学完",value:162}]},
    {name:"湖南",value:[{name:"报名人数",value:27},{name:"为学完",value:0}]},
    {name:"重庆",value:[{name:"报名人数",value:18},{name:"为学完",value:0}]},
    {name:"四川",value:[{name:"报名人数",value:14},{name:"为学完",value:1}]},
    {name:"贵州",value:[{name:"报名人数",value:12},{name:"为学完",value:0}]},
    {name:"云南",value:[{name:"报名人数",value:7},{name:"为学完",value:0}]},
    {name:"西藏",value:[{name:"报名人数",value:1},{name:"为学完",value:0}]},
    {name:"陕西",value:[{name:"报名人数",value:6},{name:"为学完",value:0}]},
    {name:"甘肃",value:[{name:"报名人数",value:2},{name:"为学完",value:0}]},
    {name:"青海",value:[{name:"报名人数",value:8},{name:"为学完",value:0}]},
    {name:"宁夏",value:[{name:"报名人数",value:17},{name:"为学完",value:0}]},
    {name:"新疆",value:[{name:"报名人数",value:14},{name:"为学完",value:0}]},
    {name:"广东",value:[{name:"报名人数",value:35},{name:"为学完",value:0}]},
    {name:"广西",value:[{name:"报名人数",value:78},{name:"为学完",value:0}]},
    {name:"海南",value:[{name:"报名人数",value:46},{name:"为学完",value:1}]},
];

function initJs(){
    var myChart = echarts.init(document.getElementById('div_map'));
/*获取地图数据*/
myChart.showLoading();
var mapFeatures = echarts.getMap(mapName).geoJson.features;
myChart.hideLoading();
mapFeatures.forEach(function(v) {
    // 地区名称
    var name = v.properties.name;
    // 地区经纬度
    geoCoordMap[name] = v.properties.cp;

});

var max = 5880,
    min = 9; // todo
var maxSize4Pin = 20,
    minSize4Pin = 5;

var convertData = function(data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value),
            });
        }
    }
    return res;
};
option = {
    title: {
        text: name_title,
        subtext: subname,
        x: 'center',
        textStyle: {
            color: nameColor,
            fontFamily: name_fontFamily,
            fontSize: name_fontSize
        },
        subtextStyle:{
            fontSize:subname_fontSize,
            fontFamily:name_fontFamily
        }
    },
    tooltip: {
        trigger: 'item',
        formatter: function(params) {
            if (typeof(params.value)[2] == "undefined") {
                var toolTiphtml = ''
                for(var i = 0;i<toolTipData.length;i++){
                    if(params.name==toolTipData[i].name){
                        toolTiphtml += toolTipData[i].name+':<br>'
                        for(var j = 0;j<toolTipData[i].value.length;j++){
                            toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                        }
                    }
                }
                return toolTiphtml;
            } else {
                var toolTiphtml = ''
                for(var i = 0;i<toolTipData.length;i++){
                    if(params.name==toolTipData[i].name){
                        toolTiphtml += toolTipData[i].name+':<br>'
                        for(var j = 0;j<toolTipData[i].value.length;j++){
                            toolTiphtml+=toolTipData[i].value[j].name+':'+toolTipData[i].value[j].value+"<br>"
                        }
                    }
                }
                return toolTiphtml;
            }
        }
    },

    visualMap: {
        show: true,
        min: 1,
        max: 300,
        left: 'left',
        top: 'bottom',
        text: ['高', '低'], // 文本，默认为数值文本
        calculable: true,
        seriesIndex: [1],
        inRange: {
            color: ['#00467F', '#A5CC82'] // 蓝绿
        }
    },
    geo: {
        show: true,
        map: mapName,
        label: {
            normal: {
                show: false
            },
            emphasis: {
                show: false,
            }
        },
        roam: true,
        itemStyle: {
            normal: {
                areaColor: '#031525',
                borderColor: '#3B5077',
            },
            emphasis: {
                areaColor: '#2B91B7',
            }
        }
    },
    series: [{
        name: '散点',
        type: 'scatter',
        coordinateSystem: 'geo',
        data: convertData(data),
        symbolSize: function(val) {

            return val[2] / 120>20?20:5;
        },
        label: {
            normal: {
                formatter: '{b}',
                position: 'right',
                show: true
            },
            emphasis: {
                show: true
            }
        },
        itemStyle: {
            normal: {
                color: '#05C3F9'
            }
        }
    },
        {
            type: 'map',
            map: mapName,
            geoIndex: 0,
            aspectScale: 5.75, //长宽比
            showLegendSymbol: false, // 存在legend时显示
            label: {
                normal: {
                    show: true
                },
                emphasis: {
                    show: false,
                    textStyle: {
                        color: '#fff'
                    }
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#031525',
                    borderColor: '#3B5077',
                },
                emphasis: {
                    areaColor: '#2B91B7'
                }
            },
            animation: false,
            data: data
        },
        {
            name: '点',
            type: 'scatter',
            coordinateSystem: 'geo',
            symbol: 'pin', //气泡
            symbolSize: function(val) {
                var a = (maxSize4Pin - minSize4Pin) / (max - min);
                var b = minSize4Pin - a * min;
                b = maxSize4Pin - a * max;
                return a * val[2] + b;
            },
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize: 9,
                    }
                }
            },
            itemStyle: {
                normal: {
                    color: '#F62157', //标志颜色
                }
            },
            zlevel: 6,

        },
        {
            name: 'Top 5',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            data: convertData(data.sort(function(a, b) {
                return b.value - a.value;
            }).slice(0, 5)),
            symbolSize: function(val) {
                return val[2] / 1500;
            },
            showEffectOn: 'render',
            rippleEffect: {
                brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: true
                }
            },
            itemStyle: {
                normal: {
                    color: 'yellow',
                    shadowBlur: 10,
                    shadowColor: 'yellow'
                }
            },
            zlevel: 1
        },

    ]
};
myChart.setOption(option);
}
