<!--<script src="../../../../MarketProject/Farm_Produce/Market_Vue/config/index.js"-->
<!--        xmlns:el-col="http://www.w3.org/1999/html"></script>-->
<template>
  <el-menu
    style="margin: 0;height: 100px;"
    class="el-menu-demo"
    mode="horizontal"
    background-color="#748F83"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-row :gutter="24">
      <el-col :span="22">
        <h2 style="color: white;margin-left: 40px;margin-top: 40px">农产品批发市场大数据分析系统</h2>
      </el-col>
      <el-col :span="2">
        <el-dropdown @command="handleCommand">
          <avatar class="user-avator" v-bind:username=this.userName background-color="#fff" color="rgb(255, 208, 75)"
          ></avatar>
          <el-dropdown-menu slot="dropdown" class="el-dropdown-menu">
            <el-dropdown-item command="1" style="color: white">个人信息</el-dropdown-item>
            <el-dropdown-item command="2" style="color: white">修改密码</el-dropdown-item>
            <el-dropdown-item command="3" divided style="color: white">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </el-menu>
</template>

<script>
  import Avatar from 'vue-avatar';
  import {mapGetters} from "vuex";

  export default {
    name: "Header",
    components: {
      Avatar
    },
    methods: {
      gotoPersonal() {
        this.$router.push({
          path: "/Menu/Personal"
        });
      },
      gotoUserPass() {
        this.$router.push({
          path: "/Menu/UserPass"
        });
      },
      gotoLogin() {
        this.$store.dispatch('ChangeName_Logout')
        this.$router.push({
          path: "/Login"
        });
      },
      handleCommand(val) {
        switch (parseInt(val)) {
          case 1:
            this.gotoPersonal()
            break
          case 2:
            this.gotoUserPass()
            break
          case 3:
            this.gotoLogin()
            break
        }
      }
    },
    computed: {
      ...mapGetters(['userName'])
    }

  }
</script>

<style scoped>
  .user-avator {
    width: 35px;
    height: 35px;
    margin-left: 20px;
    margin-top: 30px;
    cursor: pointer;
  }

  .el-dropdown-menu {
    background-color: #748F83;
  }

  .welcome {
    display: inline-block;
    vertical-align: middle;
    padding: 0 5px;
  }

  .name {
    line-height: 20px;
    text-align: center;
    font-size: 12px;
    margin-top: 40px;
  }
  .avatar{
    width: 32px;
    height: 32px;
    border-radius: 50%;
    vertical-align: middle;
    display: inline-block;
  }

  .avatarname {
    color: #a9d86e;
    font-weight: bolder;
    font-size: 13px;
    margin-top: 40px;
  }
</style>
