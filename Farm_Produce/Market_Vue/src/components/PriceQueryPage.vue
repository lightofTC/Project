<template>
    <div>
        <div class="select">
          <el-row type="flex" justify="center">
            <el-select
                    v-model="value1"
                    placeholder="省份"
                    v-on:change="getMarketByProvinceName($event)">
                <el-option
                        v-for="item in provinceList"
                        :key="item.provinceID"
                        :label="item.provinceName"
                        :value="item.provinceName">
                </el-option>
            </el-select>

            <el-select
                    v-model="value2"
                    collapse-tags
                    style="margin-left: 5px;"
                    placeholder="批发市场"
                    v-on:change="getProductListByMarket($event)">
                <el-option
                        v-for="item in marketList"
                        :key="item.marketID"
                        :label="item.marketName"
                        :value="item.marketName">
                </el-option>
            </el-select>

            <el-select
                    v-model="value3"
                    collapse-tags
                    style="margin-left: 5px;"
                    placeholder="产品">
                <el-option
                        v-for="item in productList"
                        :key="item.productID"
                        :label="item.productName"
                        :value="item.productName">
                </el-option>
            </el-select>

            <el-button type="primary" icon="el-icon-search" @click="priceQuery" style="margin-left: 5px;">搜索</el-button>
          </el-row>
        </div>
        <div id="chartDiv" style="width:800px;height:400px;margin-top:5%"></div>
      <el-divider></el-divider>
      <el-divider></el-divider>
      <el-row style="height: 800px">
        <el-table
          height="700"
          border
          :data="infoList"
          :default-sort = "{prop: 'date', order: 'descending'}"
          style="width: 100%">
          <el-table-column
            prop="date"
            label="日期">
          </el-table-column>
          <el-table-column
            prop="price"
            label="价格">
          </el-table-column>
        </el-table>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "PriceQueryPage",
        data() {
            return {
                provinceList: [],
                marketList: [],
                productList: [],
                infoList: [],
                dateList: [],
                priceList: [],
                value1: null,
                value2: null,
                value3: null
            }
        },

        methods:{
            getProductListByMarket(mName){
              this.value3 = null
              let that = this
              this.$axios
                .post(this.GLOBAL.host+"/product/getProductListByMarketName?marketName="+mName)
                .then((res) => {
                  if(res.status=="200"){
                    console.log(res)
                    that.productList = res.data.data;
                  }else{
                    alert("获取产品列表失败，错误码是"+res.status);
                  }
                }).catch((e) => {
                console.log('获取数据失败');
              })
            },
            getProvinceList() {
              this.$axios
                  .post(this.GLOBAL.host+"/province/getProvinceList")
                  .then((res) => {
                    if(res.status=="200"){
                      this.provinceList = res.data.data;
                    }else{
                      alert("获取省份列表失败，错误码是"+res.status);
                    }
                  }).catch((e) => {
                    console.log('获取数据失败');
                  })
            },
            getMarketByProvinceName(pname) {
              this.value2 = null;
              this.value3 = null;
              let that = this
              this.$axios
                  .post(this.GLOBAL.host+"/market/getMarketByProvinceName?provinceName=" + pname)
                  .then((res) => {
                    if(res.status=="200"){
                      that.marketList = res.data.data;
                    }else{
                      alert("获取农产品市场列表失败，错误码是"+res.status)
                    }
                  }).catch((e) => {
                    console.log('获取数据失败');
              })
            },
            priceQuery() {
              if(this.value1 == null || this.value1 == '')
                alert('未选择省份')
              else if(this.value2 == null || this.value2 == '')
                alert('未选择农产品市场');
              else if(this.value3 == null || this.value3 == '')
                alert('未选择农产品种类');
              this.$axios
                  .post(this.GLOBAL.host+"/price/getPriceByMarketAndProduct?marketName=" + this.value2 + "&productName=" + this.value3)
                  .then((res) => {
                    this.dateList = [];
                    this.priceList = [];
                    this.infoList = [];
                    if(res.status=="200"){
                      for(var item in res.data.data) {
                        this.dateList.push(res.data.data[item].date);
                        this.priceList.push(res.data.data[item].price);
                        this.infoList=res.data.data;
                      }
                      this.priceQueryCharts();
                    }else{
                      alert("获取价格失败，错误码是"+res.status);
                    }
                  }).catch((e) => {
                    console.log('获取数据失败');
              })
            },
            priceQueryCharts() {
                // 基于准备好的dom，初始化echarts实例
                var priceQueryChart = this.$echarts.init(document.getElementById('chartDiv'));

                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '价格查询',
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        name: '日期',
                        data: this.dateList
                    },
                    yAxis: {
                        name: '价格'
                    },
                    toolbox: {
                        left: 'center',
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    dataZoom: [
                      {
                        type: 'slider',
                        show: true,
                        start: 75,
                        end: 100
                      },
                      {
                        type: 'inside',
                        start: 75,
                        end: 100
                      }
                    ],
                    series: [
                        {
                            name: '2019',
                            type: 'line',
                            data: this.priceList
                        }
                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                priceQueryChart.setOption(option);
            },
            initPage(){
              this.$axios
                .post(this.GLOBAL.host+"/price/getPriceByMarketAndProduct?marketName=天津市武清区大沙河蔬菜批发市场&productName=玉米")
                .then((res) => {
                  this.dateList = [];
                  this.priceList = [];
                  if(res.status=="200"){
                    for(var item in res.data.data) {
                      this.dateList.push(res.data.data[item].date);
                      this.priceList.push(res.data.data[item].price);
                      this.infoList=res.data.data;
                    }
                    this.priceQueryCharts();
                  }else{
                    alert("获取价格失败，错误码是"+res.status);
                  }
                }).catch((e) => {
                console.log('获取数据失败');
              })
            }
        },
        mounted() {
            this.getProvinceList();
            this.priceQueryCharts();
            this.initPage();
        }
    }
</script>

<style scoped>
    #chartDiv{
        margin: auto;
    }

    .export {
      margin-left: 80%;
      margin-bottom: 20%;
    }
</style>
