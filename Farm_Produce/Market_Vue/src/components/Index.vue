<template>
  <div class="index-wrapper">
    <el-carousel :interval="4000" type="card" height="450px">
      <el-carousel-item v-for="item in imgList" :key="item.id">
        <img ref="imgHeight" :src="item.src" style="width: 100%;height: 100%;"/>
      </el-carousel-item>
    </el-carousel>

    <router-link to="/Login" style="text-decoration: none">
      <div style="margin-top:130px" >
        <el-button type="primary" style="width: 20%;display:block;margin: 0 auto;" icon="el-icon-position" round >登陆/注册</el-button>
      </div>
    </router-link>
  </div>

</template>

<script>
  export default {
  name: "Index",
    data(){
        return{
          imgList:[
            {src: require('../assets/介绍.png'),id:1},
            {src: require('../assets/数据监控.png'),id:2},
            {src: require('../assets/数据查询.png'),id:3},
            {src: require('../assets/价格预测.png'),id:4},
          ]
        }
    }
  }
</script>

<style scoped>
  .index-wrapper{
    position: relative;
    background-image: url("../assets/bg.png");
    background-size: cover;
    height: 80%;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    padding: 60px;
  }
</style>
