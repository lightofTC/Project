<template>
  <div class="hello">
    <el-container>
      <el-header height="0px"></el-header>
      <el-main>
        <el-row class="text">实时监测</el-row>
        <el-row type="flex" align="middle" class="datarow">
          <el-col :span="4" :offset="1">
            <el-card :body-style="{ padding: '0px' }" class="card1">
              <div style="padding: 14px;">
                <i class="el-icon-edit" ></i>
                <span>批发市场</span>
                <div class="bottom clearfix">
                  <span ref="marketNum"></span>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="4" :offset="2">
            <el-card :body-style="{ padding: '0px' }" class="card2">
              <div style="padding: 14px;">
                <i class="el-icon-shopping-cart-1" ></i>
                <span>种类数量</span>
                <div class="bottom clearfix">
                  <span ref="typeNum"></span>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="4" :offset="2">
            <el-card :body-style="{ padding: '0px' }" class="card3">
              <div style="padding: 14px;">
                <i class="el-icon-discover" ></i>
                <span>品种数量</span>
                <div class="bottom clearfix">
                  <span ref="productNum"></span>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col :span="4" :offset="2">
            <el-card :body-style="{ padding: '0px' }" class="card4">
              <div style="padding: 14px;">
                <i class="el-icon-search" ></i>
                <span>总数据量</span>
                <div class="bottom clearfix">
                  <span ref="dataNum"></span>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-divider></el-divider>
        <el-row class="text">最新数据</el-row>
        <el-row style="height: 800px">
          <el-table
            height="650"
            border
            :data="tableData"
            style="width: 100%">
            <el-table-column
              prop="product"
              label="产品"
              width="180">
            </el-table-column>
            <el-table-column
              prop="province"
              label="省份"
              width="180">
            </el-table-column>
            <el-table-column
              prop="market"
              label="批发市场">
            </el-table-column>
            <el-table-column
              prop="price"
              label="价格">
            </el-table-column>
            <el-table-column
              prop="date"
              label="日期">
            </el-table-column>
          </el-table>
          <div class="pagination" align="center">
            <el-pagination
              background
              @current-change="handleCurrentChange"
              :current-page="pageCode"
              :page-size="pageSize"
              layout="total, prev, pager, next, jumper"
              :total="totalPage">
            </el-pagination>
          </div>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<style>

  .datarow{
    background-color: white ;
    height:150px;
    type:flex;
    flex-wrap: wrap;
    text-align: center;
  }

  .card1{
    background-color: #E6A23C ;
    font-size:20px;
    font-family: "Arial Black";
    color:white;
    font-weight: bold;
    height: 50%;
    transition: all .5s;
  }

  .card2{
    background-color: #F56C6C ;
    font-size:20px;
    font-family: "Arial Black";
    color:white;
    font-weight: bold;
    height: 50%;
    transition: all .5s;
  }
  .card3{
    background-color: #FFE877 ;
    font-size:20px;
    font-family: "Arial Black";
    color:white;
    font-weight: bold;
    height: 50%;
    transition: all .5s;
  }

  .card4{
    background-color: #46E6B3 ;
    font-size:20px;
    font-family: "Arial Black";
    color:white;
    font-weight: bold;
    height: 50%;
    transition: all .5s;
  }

  .text{
    height:50px;
    font-size:20px;
    font-family: "Arial Black";
    font-weight: bold;
    text-align-last: left;
    type:flex;
    flex-wrap: wrap;
    align:middle;
  }

</style>

<script>
  export default {
    data() {
      return {
        pageCode:1,
        pageSize:10,
        totalPage:12,
        tableData:[],
        showMarketNum: false,
      }
    },
    mounted(){
      this.initPage();
      this.getData(this.pageCode,this.pageSize);
    },

    methods:{
      initPage(){
        this.$axios.post(this.GLOBAL.host+"/market/getLatestMarketNumber").then((res) =>{
          this.$refs.marketNum.innerText = res.data.data[0].result
        })
        this.$axios.post(this.GLOBAL.host+"/type/getTypeNumber").then((res) =>{
          this.$refs.typeNum.innerText = res.data.data[0].result
        })
        this.$axios.post(this.GLOBAL.host+"/product/getLatestProductNumber").then((res) =>{
          this.$refs.productNum.innerText = res.data.data[0].result
        })
        this.$axios.post(this.GLOBAL.host+"/price/getTotalDataVolume").then((res) =>{
          this.$refs.dataNum.innerText = res.data.data[0].result
        })
      },
      getData(pageCode,pageSize){
        var that=this;
        this.$axios.post(this.GLOBAL.host+"/price/getLatestPrice?currentPage="+pageCode+"&size="+pageSize).then(response=>{
          this.tableData=response.data.data;
          this.totalPage=response.data.count;
        },response=>{
          console.log("error");
        })
      },
      handleCurrentChange(val){
        this.getData(val,this.pageSize);
      },
      created(){
        this.getData(this.pageCode,this.pageSize);
      }
    }
  }
</script>
