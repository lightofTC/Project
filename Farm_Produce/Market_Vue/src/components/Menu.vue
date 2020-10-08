<template>
  <div class="menu">
    <el-container style="height: 100%">
      <el-aside class="menuLayout">
        <el-menu
          default-active="$route.path"
          :default-openeds="openeds"
          style="height: 100%"
          class="el-menu-vertical-demo"
          router
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>数据监控</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/Menu/Grab">抓取量查询</el-menu-item>
              <el-menu-item index="/Menu/Amount">实时监控</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-search"></i>
              <span>数据查询</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/Menu/PriceQueryPage">价格查询</el-menu-item>
              <el-menu-item index="/Menu/PriceTrend">价格走势</el-menu-item>
              <el-menu-item index="/Menu/PriceComparisonPage">价格对比</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-bangzhu"></i>
              <span>价格预测</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/Menu/ForecastPrice">价格预测</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>个人中心</span>
              </template>
              <el-menu-item-group>
                <el-menu-item index="/Menu/Personal">个人信息</el-menu-item>
                <el-menu-item index="/Menu/UserPass">密码修改</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Menu",
    data(){
      return{
        openeds:['1','2','3'],
        uniqueOpened:false
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style scoped>
  .menu{
    height: 100%;
  }


  .menuLayout {
    display: block;
    position: relative;
    overflow-y: hidden;
    height:100%;
  }
</style>
