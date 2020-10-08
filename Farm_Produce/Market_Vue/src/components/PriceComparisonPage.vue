<template>
    <el-container>
        <el-main>
          <el-header>
            <el-row :gutter="20">
              <el-col :span="2" :offset="5">
                <el-tag effect="plain" style="font-size: large; margin-top: 5px">农产品名称</el-tag>
              </el-col>
              <el-col :span="6" :offset="2">
                <div class="grid-content">
                  <el-select v-model="value" placeholder="请选择">
                    <el-option
                      v-for="product in productList"
                      :key="product.productID"
                      :label="product.productName"
                      :value="product.productName">
                    </el-option>
                  </el-select>
                </div>
              </el-col>
              <el-col :span="1">
                <el-button type="primary" icon="el-icon-search" @click="getProvinceAvpriceByProduct">搜索</el-button>
              </el-col>
            </el-row>
          </el-header>
            <div id="map" style="width:800px;height:700px;margin-top: 60px;margin-left: 150px"></div>
            <el-drawer
                    id = 'drawer'
                    :visible.sync="showDrawer"
                    :with-header="false"
                    size="1000"
                    @opened="loadHistogram">
                <div id="histogram" style="width: 1000px; height: 600px; margin-top: 8%"></div>
            </el-drawer>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "PriceComparisonPage",
        data() {
            return {
                productList: [],
                provinceAvgPrice: [],
                marketList: [],
                priceList: [],
                value: null,
                showDrawer: false,
                clickedProvince: ""
            }
        },

        methods: {
          getProductList() {
            this.$axios
              .post(this.GLOBAL.host+"/product/getProductList")
              .then((res) => {
                if(res.status=="200"){
                  this.productList = res.data.data;
                }else{
                  alert("获取农产品种类列表失败，错误码是"+res.status)
                }
              }).catch((e) => {
              console.log('获取数据失败');
            })
          },
          getProvinceAvpriceByProduct() {
            if(this.value == null || this.value == '')
              alert('未选择农产品名称');
            this.$axios
                .post(this.GLOBAL.host+"/province_avprice/getProvinceAvpriceByProduct?productName=" + this.value)
                .then((res) => {
                  if(res.status=="200"){
                    this.provinceAvgPrice = [];
                    this.provinceAvgPrice = res.data.data;
                    this.comparisonMap();
                  }else{
                    alert("获取农产品列表失败，错误码是"+res.status);
                  }
                }).catch((e) => {
                console.log('获取数据失败');
            })
          },
            // 地图
            comparisonMap(){
                // 基于准备好的dom，初始化echarts实例
                var mapChart = this.$echarts.init(document.getElementById('map'));


                mapChart.setOption({
                    tooltip: {
                        show: true,
                        formatter: function(e, t, n) {
                            return e.name + e.seriesName + "：" + e.value
                        }

                    },
                    visualMap: {
                        min: 0,
                        max: 80,
                        left: 26,
                        bottom: 130,
                        showLabel: !0,
                        text: ["价格高", "价格低"],
                        pieces: [{
                            gt: 10,
                            label: "价格偏高",
                            color: "#409EFF"
                        }, {
                            gte: 5,
                            lte: 10,
                            label: "价格在平均范围",
                            color: "#A2D0FF"
                        }, {
                            gte: 0,
                            lt: 5,
                            label: "价格偏低",
                            color: "#d3e9ff"
                        }, {
                            value: 0,
                            label: "无数据",
                            color: "#ffffff"
                        }],
                        show: !0
                    },
                    geo: {
                        map: "china",
                        roam: !1,
                        scaleLimit: {
                            min: 1,
                            max: 2
                        },
                        zoom: 1,
                        top: 25,
                        left: 'center',
                        label: {
                            normal: {
                                show: !0,
                                fontSize: "14",
                                color: "rgba(0,0,0,0.7)"
                            }
                        },
                        itemStyle: {
                            normal: {
                                //shadowBlur: 50,
                                //shadowColor: 'rgba(0, 0, 0, 0.2)',
                                borderColor: "rgba(0, 0, 0, 0.2)"
                            },
                            emphasis: {
                                areaColor: "#f2d5ad",
                                shadowOffsetX: 0,
                                shadowOffsetY: 0,
                                borderWidth: 0
                            }
                        }
                    },
                    series: [{
                        name: "平均价格",
                        type: "map",
                        geoIndex: 0,
                        data: this.provinceAvgPrice
                    }]
                });

                // 地图点击事件
                mapChart.on('click', (params) => {
                    this.showDrawer = true;
                    this.clickedProvince = params.name;
                });
            },

            // 加载柱状图
            loadHistogram(){
                // 设置弹窗标题
                var title = "";
                switch (this.clickedProvince) {
                    case "香港":
                    case "澳门":
                        title = this.clickedProvince + "特别行政区内农产品价格对比";
                        break;
                    case "北京":
                    case "天津":
                    case "上海":
                    case "重庆":
                        title = this.clickedProvince + "市内农产品价格对比";
                        break;
                    case "新疆":
                        title = this.clickedProvince + "维吾尔自治区内农产品价格对比";
                        break;
                    case "宁夏":
                        title = this.clickedProvince + "回族自治区内农产品价格对比";
                        break;
                    case "广西":
                        title = this.clickedProvince + "壮族自治区内农产品价格对比";
                        break;
                    case "内蒙古":
                    case "西藏":
                        title = this.clickedProvince + "自治区内农产品价格对比";
                        break;
                    default:
                        title = this.clickedProvince + "省内农产品价格对比";
                        break;
                }

                this.$axios
                    .post(this.GLOBAL.host+"/price/getPriceByProvinceAndProduct?provinceName="+this.clickedProvince+"&productName="+this.value)
                    .then((res) => {
                      if(res.status=="200"){
                        this.marketList = [];
                        this.priceList = [];
                        for(var item in res.data.data){
                          this.marketList.push(res.data.data[item].marketName);
                          this.priceList.push(res.data.data[item].price);
                        }

                        // 基于准备好的dom，初始化echarts实例
                        var histogram = this.$echarts.init(document.getElementById('histogram'));

                        histogram.setOption({
                          title: {
                            text: title,
                            left: 'center',
                            top: 'top'
                          },
                          grid: {containLabel: true},
                          xAxis: {
                            name: '价格',
                            type: 'value'
                          },
                          yAxis: {
                            type: 'category',
                            data: this.marketList
                          },
                          visualMap: {
                            orient: 'horizontal',
                            left: 'center',
                            min: 0,
                            max: 10,
                            text: ['高价格', '低价格'],
                            // Map the score column to color
                            dimension: 0,
                            inRange: {
                              color: ['#D7DA8B', '#E15457']
                            }
                          },
                          series: [
                            {
                              type: 'bar',
                              data: this.priceList
                            }
                          ]
                        });
                      }else{
                        alert("获取省内价格对比失败，错误码是"+res.status)
                      }
                    }).catch((e) => {
                      console.log('获取数据失败')
                })

            },
            initPage(){
              this.$axios
                .post(this.GLOBAL.host+"/province_avprice/getProvinceAvpriceByProduct?productName=香蕉")
                .then((res) => {
                  if(res.status=="200"){
                    this.provinceAvgPrice = [];
                    this.provinceAvgPrice = res.data.data;
                    this.comparisonMap();
                  }else{
                    alert("获取农产品列表失败，错误码是"+res.status);
                  }
                }).catch((e) => {
                console.log('获取数据失败');
              })
            }
        },
        mounted () {
            this.getProductList();
            this.comparisonMap();
            this.initPage();
        }
    }
</script>

<style scoped>
    .grid-content {
        border-radius: 4px;
    }

</style>
