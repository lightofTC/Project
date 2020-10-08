<template>
    <el-container>
      <el-main>
        <el-row style="border: 1px solid black;">
          <div style="text-align-last: left;font-weight: bold;font-size:30px;padding: 40px 0 0 100px">批发市场抓取量</div>
          <el-col :span="4" :offset="5">
            <div id="myMap" style="width:500px;height:500px;margin-left: 10px"></div>
          </el-col>
        </el-row>
        <div style="border: 1px solid black;height: 600px">
        <br>
        <el-row type="flex" justify="center">
          <div class="select">
            <el-select
              v-model="value1"
              placeholder="省"
              v-on:change="getMarketByProvinceName($event)">
              <el-option
                v-for="item in provinceOptions"
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
              v-on:change="select2Changed">
              <el-option
                v-for="item in marketOptions"
                :key="item.marketID"
                :label="item.marketName"
                :value="item.marketName">
              </el-option>
            </el-select>

            <el-select
              v-model="value3"
              collapse-tags
              style="margin-left: 5px;"
              placeholder="种类">
              <el-option
                v-for="item in typeOptions"
                :key="item.typeID"
                :label="item.typeName"
                :value="item.typeName">
              </el-option>
            </el-select>
            <el-button type="primary" icon="el-icon-search" @click="getLowerTwoEchartsData">查询</el-button>
          </div>
          </el-row>
          <br>
          <br>
          <el-row>
          <el-col :span="8" >
            <div id="areaChart" style="width:500px;height:400px;"></div>
          </el-col>
          <el-col :span="8" :offset="2">
            <div id="pieChart" style="width:500px;height:400px;"></div>
          </el-col>
          </el-row>
          </div>
      </el-main>
    </el-container>
</template>

<script>
    import Menu from "../components/Menu";
    export default {
      name: "Grab",
      components:{
          Menu
      },
      data() {
        return {
          provinceOptions: [],
          marketOptions: [],
          typeOptions: [],
          mapData: [],
          dateList: [],
          areaEchartData: [],
          pieData: [],
          value1: null,
          value2: null,
          value3: null
        }
      },
      methods:{
        myAreaEcharts(){
          // 初始化echarts实例
          var areaChart = this.$echarts.init(document.getElementById('areaChart'));
          //echarts参数配置
          var option = {
            title: {
              text: '品种数量',
              x : 'center'
            },
            tooltip: {
              trigger: 'axis'
            },
            xAxis: {
              type: 'category',
              boundaryGap: true,
              data: this.dateList,
            },
            yAxis: {
              type: 'value'
            },
            series: [{
              data: this.areaEchartData,
              type: 'line',
              areaStyle: {}
            }]
          };
          // 使用刚指定的配置项和数据显示图表。
          areaChart.setOption(option);
        },
        myPieEcharts(){
          var pieChart = this.$echarts.init(document.getElementById('pieChart'));
          var option = {
            title: {
              text: '种类占比',
              left: 'center'
            },
            tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
              orient: 'vertical',
              left: 'right',
              data: [this.value3, '其他']
            },
            series: [
              {
                name: '品种数',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: this.pieData,
                emphasis: {
                  itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
                }
              }
            ]
          };
          pieChart.setOption(option);
        },
        myMap(){
          var myMap = this.$echarts.init(document.getElementById('myMap'));
          function randomValue() {
            return Math.round(Math.random()*100);
          }
          var option = {
            visualMap: {
              min: 0,
              max: 15,
              left: 'left',
              top: 'bottom',
              text: ['高','低'],
              inRange: {
                color: ['#e0ffff', '#006edd']
              },
              show:true
            },
            geo: {
              map: 'china',
              roam: false,
              zoom:1.23,
              label: {
                normal: {
                  show: true,
                  fontSize:'10',
                  color: 'rgba(0,0,0,0.7)'
                }
              },
              itemStyle: {
                normal:{
                  borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis:{
                  areaColor: '#F3B329',
                  shadowOffsetX: 0,
                  shadowOffsetY: 0,
                  shadowBlur: 20,
                  borderWidth: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            },
            series : [
              {
                name: '批发市场数量',
                type: 'map',
                geoIndex: 0,
                data: this.mapData,
              }
            ]
          };
          myMap.setOption(option);
          myMap.on('click', function (params) {
            alert(params.name+':'+params.seriesName+':'+params.value);
          });
        },
        getProvinceOption() {
          this.$axios
            .post(this.GLOBAL.host+"/province/getProvinceList")
            .then((res) => {
              if(res.status=="200"){
                this.provinceOptions = res.data.data;
              }else{
                alert("获取省份列表失败，错误码是"+res.status);
              }
            }).catch((e) => {
            console.log('获取数据失败');
          })
        },
        getMarketByProvinceName(pname) {
          this.marketOptions = [];
          this.value2 = null;
          this.value3 = null;
          this.$axios
            .post(this.GLOBAL.host+"/market/getMarketByProvinceName?provinceName=" + pname)
            .then((res) => {
              if(res.status=="200"){
                this.marketOptions = res.data.data;
              }else{
                alert("获取农产品市场列表失败，错误码是"+res.status)
              }
            }).catch((e) => {
            console.log('获取数据失败');
          })
        },
        select2Changed() {
          this.value3 = null;
        },
        getTypeOption(){
          this.$axios
          .post(this.GLOBAL.host+"/type/getTypeList")
          .then((res) => {
            if(res.status=="200"){
              this.typeOptions = res.data.data
            }else{
              alert("获取农产品种类列表失败，错误码是"+res.status)
            }
          })
        },
        getMapData(){
          this.$axios
            .post(this.GLOBAL.host+"/market/getMarketNoByProvince")
            .then((res) => {
              if(res.status == "200"){
                console.log(res.data.data)
                this.mapData = res.data.data
                this.myMap()
              }else{
                alert('获取批发市场抓取量失败，错误码是'+res.status)
              }
            }).catch((e) => {
              console.log('获取数据失败')
          })
        },
        getLowerTwoEchartsData(){
          if(this.value2 == null)
            alert('未选择批发市场')
          if(this.value3 == null)
            alert('未选择农产品种类')
          // 柱状图数据
          this.$axios
            .post(this.GLOBAL.host+"/product/getProductNoByType?marketName="+this.value2+"&type="+this.value3)
            .then((res) => {
              this.dateList = []
              this.areaEchartData = []
              if(res.status=="200"){
                for(var item in res.data.data){
                  this.dateList.push(res.data.data[item].date)
                  this.areaEchartData.push(res.data.data[item].value)
                }
                this.myAreaEcharts()
              }else{
                alert("获取品种数量失败，错误码是"+res.status)
              }
            }).catch((e) => {
              console.log('获取数据失败')
          })
          // 饼图数据
          this.$axios
            .post(this.GLOBAL.host+"/product/getTypePercents?marketName="+this.value2+"&type="+this.value3)
            .then((res) => {
              if(res.status=="200"){
                this.pieData = res.data.data
                this.myPieEcharts()
              }else{
                alert("获取农产品种类占比失败，错误码是"+res.status)
              }
            }).catch((e) => {
              console.log('获取数据失败')
          })
        },
        initPage(){
          // 柱状图数据
          this.$axios
            .post(this.GLOBAL.host+"/product/getProductNoByType?marketName=北京丰台区新发地农产品批发市场&type=水果")
            .then((res) => {
              this.dateList = []
              this.areaEchartData = []
              if(res.status=="200"){
                for(var item in res.data.data){
                  this.dateList.push(res.data.data[item].date)
                  this.areaEchartData.push(res.data.data[item].value)
                }
                this.myAreaEcharts()
              }else{
                alert("获取品种数量失败，错误码是"+res.status)
              }
            }).catch((e) => {
            console.log('获取数据失败')
          })
          // 饼图数据
          this.$axios
            .post(this.GLOBAL.host+"/product/getTypePercents?marketName=北京丰台区新发地农产品批发市场&type=水果")
            .then((res) => {
              if(res.status=="200"){
                this.pieData = res.data.data
                this.myPieEcharts()
              }else{
                alert("获取农产品种类占比失败，错误码是"+res.status)
              }
            }).catch((e) => {
            console.log('获取数据失败')
          })
        }
      },
      mounted() {
        this.getProvinceOption();
        this.getTypeOption();
        this.getMapData();
        this.myAreaEcharts();
        this.myPieEcharts();
        this.initPage();
      }
    }
</script>

<style scoped>

</style>
