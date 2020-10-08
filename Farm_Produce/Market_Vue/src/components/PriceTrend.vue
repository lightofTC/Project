<template>
  <div class="PTCharts">
    <div class="select">
      <el-select v-model="curProduct" filterable placeholder="名称" style="text-align: left">
        <el-option
            v-for="item in productList"
            :key="item.productID"
            :label="item.productName"
            :value="item.productID">
        </el-option>
      </el-select>
      <el-button type="primary" icon="el-icon-search" @click="drawEChart()">搜索</el-button>
    </div>
    <div>
      <div id="mainCharts" style="margin:30px auto;width: 800px;height:400px;"></div>
      <el-divider></el-divider>
      <el-divider></el-divider>
      <el-row style="height: 800px">
        <el-table
          height="700"
          border
          :data="priceList"
          :default-sort = "{prop: 'date', order: 'descending'}"
          style="width: 100%">
          <el-table-column
            prop="date"
            label="日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="lowest"
            label="最低价">
          </el-table-column>
          <el-table-column
            prop="average"
            label="平均价">
          </el-table-column>
          <el-table-column
            prop="highest"
            label="最高价">
          </el-table-column>
        </el-table>
      </el-row>
    </div>

  </div>
</template>

<script>
export default {
  name: "PriceTrend",
  data() {
    return {
      productList: [],
      priceList:[],
      curProduct: "13084",//默认的某个农产品id
    }
  },
  methods: {
    initProduct() {
      let that = this
      this.$axios
          .post(this.GLOBAL.host+"/product/getProductList")
          .then((res) => {
            console.log(res)
            if(res.status=="200"){
              that.productList = res.data.data;
              that.drawEChart();
            }else{
              alert("获取农产品列表失败，错误码是"+res.status);
            }
          }).catch((e) => {
            console.log('获取数据失败');
          })
    },
    drawEChart() {
      // 获取数据
      this.$axios
          .post(this.GLOBAL.host+"/price_trend/getPriceTrend?productID="+this.curProduct
          )
          .then((res) => {
            console.log(res)
            this.priceList = res.data.data

            // 基于准备好的dom，初始化echarts实例
            var myChart = this.$echarts.init(document.getElementById('mainCharts'));
            var option = {
              title: {
                text: '价格走势',
                x: 'center'
              },
              legend: {
                orient: 'vertical',
                left: 'right',
                top:'center'
              },
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                  type: 'shadow'
                }
              },
              dataZoom: [
                {
                  type: 'slider',
                  show: true,
                  start: 90,
                  end: 100
                },
                {
                  type: 'inside',
                  start: 75,
                  end: 100
                }
              ],
              dataset: {
                dimensions: ['date', 'lowest', 'average', 'highest'],
                source: this.priceList
              },
              toolbox: {
                show: true,
                //orient: 'vertical',
                left: 'right',
                //top: 'center',
                feature: {
                  magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                  restore: {show: true},
                  saveAsImage: {show: true}
                }
              },
              xAxis: {type: 'category'},
              yAxis: {},
              series: [
                {name: '最低价', type: 'bar'},
                {name: '平均价', type: 'bar'},
                {name: '最高价', type: 'bar'}
              ]
            };
            if (option && typeof option === "object") {
              myChart.setOption(option, true);
            }
          }).catch((e) => {
        console.log('获取数据失败');
      })

    },
  },
  mounted() {
    this.initProduct();
  }
}


</script>

<style scoped>
.select {
  width: 400px;
  margin: 20px auto;
}
.export {
  margin-left: 80%;
  margin-bottom: 20%;
}
</style>
