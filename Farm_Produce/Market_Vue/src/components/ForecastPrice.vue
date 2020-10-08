<template>
  <div class="FPcharts">
    <div class="select">
      <el-row type="flex" justify="center" style="margin-top: 80px;">
      <el-select
          v-model="curProvince"
          placeholder="省市"
          v-on:change="getMarketByProvinceID($event)"
          filterable>
        <el-option
          v-for="item in provinceList"
          :key="item.provinceID"
          :label="item.provinceName"
          :value="item.provinceID">
        </el-option>
      </el-select>

      <el-select
          v-model="curMarket"
          collapse-tags
          style="margin-left: 5px;"
          placeholder="批发市场"
          v-on:change="getProductListByMarketID($event)"
          filterable>
        <el-option
          v-for="item in marketList"
          :key="item.marketID"
          :label="item.marketName"
          :value="item.marketID">
        </el-option>
      </el-select>

      <el-select
          v-model="curProduct"
          collapse-tags
          style="margin-left: 5px;"
          placeholder="产品"
          filterable>
        <el-option
          v-for="item in productList"
          :key="item.productID"
          :label="item.productName"
          :value="item.productID">
        </el-option>
      </el-select>

      <el-button type="primary" icon="el-icon-search" @click="myEcharts" style="margin-left: 5px;">搜索</el-button>
      </el-row>
    </div>

    <div id="main" style="width: 800px;height:400px;margin-top: 50px;"></div>

    <el-divider></el-divider>
    <el-divider></el-divider>
    <el-row style="height: 200px">
      <el-table
        height="100"
        border
        :data="tableList"
        :default-sort = "{prop: 'date', order: 'descending'}"
        style="width: 60%;margin:0 auto;">
        <el-table-column
          prop="date"
          label="日期"
          width="300">
        </el-table-column>
        <el-table-column
          prop="forprice"
          label="预测价格">
        </el-table-column>
      </el-table>
    </el-row>

  </div>
</template>

<script>
  export default {
    name: 'ForecastPrice',
    data() {
      return {
        provinceList: [],
        marketList: [],
        productList: [],
        curProvince: [],//42
        curMarket: [],//20661
        curProduct: [],//13509
        tableList:[],
        today:'',
      }
    },

  methods: {
    getProvinceList() {
      let that = this
      this.$axios
        .post(this.GLOBAL.host + "/province/getProvinceList")
        .then((res) => {
          if (res.status == "200") {
            that.provinceList = res.data.data;
          } else {
            alert("获取省份列表失败，错误码是" + res.status);
          }
        }).catch((e) => {
        console.log('获取数据失败');
      })
    },
    getProductListByMarketID(mID) {
      this.curProduct = null
      let that = this
      this.$axios
        .post(this.GLOBAL.host + "/product/getProductListByMarketID?marketID=" + mID)
        .then((res) => {
          if (res.status == "200") {
            //console.log(res)
            that.productList = res.data.data;
          } else {
            alert("获取产品列表失败，错误码是" + res.status);
          }
        }).catch((e) => {
        console.log('获取数据失败');
      })
    },
    getMarketByProvinceID(pID) {
      this.curMarket = null
      this.curProduct = null
      let that = this
      this.$axios
        .post(this.GLOBAL.host + "/market/getMarketByProvinceID?provinceID=" + pID)
        .then((res) => {
          if (res.status == "200") {
            that.marketList = res.data.data;
          } else {
            alert("获取农产品市场列表失败，错误码是" + res.status)
          }
        }).catch((e) => {
        console.log('获取数据失败');
      })
    },
    myEcharts() {
      //获取数据
      this.$axios
        .post(this.GLOBAL.host + "/forecast_price/getForecastPriceByMarketAndProduct?marketID=" + this.curMarket + "&productID=" + this.curProduct)
        .then((res) => {
          console.log(res)
          this.fpriceList = res.data.data

          // 基于准备好的dom，初始化echarts实例
          var myChart = this.$echarts.init(document.getElementById('main'));

          // 指定图表的配置项和数据
          var option = {
            title: {
              text: '价格预测(虚线部分为预测价格)',
              x: 'center'
            },
            legend: {
              orient: 'vertical',
              left: 'right',
              top: 'center'
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
                start: 75,
                end: 100
              },
              {
                type: 'inside',
                start: 75,
                end: 100
              }
            ],
            dataset: {
              dimensions: ['date', 'price', 'forprice'],
              source: this.fpriceList
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
              {name: '实际值', type: 'line'},
              {
                name: '预测值', type: 'line', smooth: false,   //关键点，为true是不支持虚线，实线就用true
                itemStyle: {
                  normal: {
                    lineStyle: {
                      width: 2,
                      type: 'dotted'
                    }
                  }
                }
              }
            ]
          }

          // 使用刚指定的配置项和数据显示图表。
          myChart.setOption(option);
        })

      this.$axios
        .post(this.GLOBAL.host + "/forecast_price/getForecastPriceByMarketAndProductAndDate?marketID=" + this.curMarket + "&productID=" + this.curProduct+"&date="+this.today)
        .then((res) => {
          console.log(res)
          this.tableList = res.data.data
        })
    },
    dateFtt(fmt, date) {
      var o = {
        "M+": date.getMonth() + 1,     //月份
        "d+": date.getDate(),     //日
        "h+": date.getHours(),     //小时
        "m+": date.getMinutes(),     //分
        "s+": date.getSeconds(),     //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()    //毫秒
      };
      if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
      for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
      return fmt;
    },
    //创建时间格式化显示
    crtTimeFtt(){
      let crtTime = new Date();
      let today = this.dateFtt("yyyy-MM-dd",crtTime);
      console.log(today);
      this.today = today;
    }
  },

  mounted() {
    this.crtTimeFtt();
    this.getProvinceList();
    this.getMarketByProvinceID("42")
    this.getProductListByMarketID("20661")
    this.curProvince = "42"
    this.curMarket = "20661"
    this.curProduct = "13509"
    this.myEcharts();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  #main{
    margin: auto;
  }

  .select {
    margin: 20px auto;
  }

</style>
