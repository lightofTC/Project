<template>
  <div>
    <div class="login-wrapper">
      <el-row>
        <el-col span="7" offset="5">
          <div id="intro">
            <p class="introtitle">欢迎登录</p>
            <el-divider></el-divider>
            <p class="introcontent">农产品批发市场大数据分析系统</p>
          </div>
        </el-col>
        <el-col span="6">
          <div id="login">
            <br>
            <p class="title">登录</p>
            <el-form
              :model="ruleFormLogin"
              status-icon
              :rules="rulesLogin"
              ref="ruleFormLogin"
              label-width="0"
              class="demo-ruleFormLogin"
            >
              <br>
              <el-form-item label="用户名：" prop="name" label-width="80px">
                <el-input v-model="ruleFormLogin.name" auto-complete="on" placeholder="用户名" maxlength="20"></el-input>
              </el-form-item>
              <br>
              <el-form-item prop="pass" label="密码：" label-width="80px">
                <el-input type="password" v-model="ruleFormLogin.pass" auto-complete="on"
                          placeholder="长度4-15个字符" show-password></el-input>
              </el-form-item>
              <br>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleFormLogin')" class="el-button--primary"
                           v-loading.fullscreen.lock="fullscreenLoading">登录
                </el-button>
                <el-button type="primary" @click="resetForm('ruleFormLogin')" class="el-button--primary">重置
                </el-button>
              </el-form-item>
              <el-form-item>
                <p class="login" @click="gotoLogin">没有账号？立即注册</p>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-dialog append-to-body
               :visible.sync="isSlideVisible"
               :show-close="false"
               :close-on-click-modal="false"
               width="350px"
               @close="gotoMenu"
    >
      <SlideVerify ref="Slide" :r="8" @success="onSuccess"></SlideVerify>
    </el-dialog>
  </div>
</template>

<script>
  import SlideVerify from "./tools/SlideVerify";

  export default {
    name: "Login",
    components: {
      SlideVerify
    },
    data() {
      //验证用户名格式
      let validateName = (rule, value, callback) => {
        let re = /^[1-9a-zA-Z#_\u4E00-\u9FA5]{1,20}$/
        if (value != '') {
          if (!re.test(value)) {
            callback(new Error("用户名格式不正确"))
          } else {
            callback();
          }
        } else {
          callback(new Error("用户名不能为空"));
        }
      }

      //验证密码
      let validatePass = (rule, value, callback) => {
        let re = /^[a-zA-Z0-9#_]{4,15}$/
        if (value != '') {
          if (!re.test(value)) {
            callback(new Error("密码格式不正确"))
          } else {
            callback()
          }
        } else {
          callback(new Error("密码不能设置为空"));
        }
      }

      return {
        ruleFormLogin: {
          name: "",
          pass: "",
        },
        rulesLogin: {
          pass: [{validator: validatePass, trigger: 'change'}],
          name: [{validator: validateName, trigger: 'change'}],
        },
        isSlideVisible: false
      }
    },
    methods: {
      getStatus: function () {
      },
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid) {
            setTimeout(() => {
                this.$message.success('登陆中...')
                this.$axios
                  //发送数据到后端
                  .post(this.GLOBAL.host + '/user/loginCheck?userName=' + this.ruleFormLogin.name + '&userPass=' + this.ruleFormLogin.pass)
                  .then(successResponse => {
                    if (successResponse.data.status == '200') {
                      this.isSlideVisible = true;
                      this.$store.dispatch('ChangeInfo_Login', successResponse.data.data)
                      console.log(successResponse.data.data)
                    } else {
                      this.$message.error('用户名或者密码错误，请重新输入')
                    }
                  })
              }
            )

          }
        })
      },
      // <!--进入注册页-->
      gotoLogin() {
        this.$router.push({
          path: "/Register"
        });
      },
      gotoMenu() {
        // this.$store.dispatch('ChangeInfo',this.ruleFormLogin)
        this.$router.push({
          path: "/Menu/Amount"
        });
      },
      onSuccess(left) {
        if (this.$refs.Slide.isSuccess()) {
          setTimeout(() => {
            this.isSlideVisible = false
          }, 1000)
        } else {
          this.$refs.Slide.reset()
        }
      },
      //重置界面
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }

  }
</script>

<style scoped>
  .loading-wrapper {
    position: fixed;
    top: 0px;
    right: 0;
    left: 0;
    bottom: 0;
    background: #aedff8;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .login-wrapper img {
    position: absolute;
    z-index: 1;
  }

  .login-wrapper {
    position: relative;
    background-image: url("../assets/background.jpg");
    background-size: cover;
    height: 100%;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    padding: 60px;

  }

  #login {
    width: 400px;
    height: 500px;
    margin: 60px auto;
    background: #fff;
    padding: 20px 40px;
    position: relative;
    z-index: 9;
    box-sizing: border-box;
    border-radius: 0 10px 10px 0;
  }

  #intro {
    width: 600px;
    height: 500px;
    margin: 60px auto;
    background-color: #A7A184;
    padding: 20px 40px;
    position: relative;
    z-index: 9;
    box-sizing: border-box;
    border-radius: 10px 0 0 10px;
  }

  .title {
    font-size: 26px;
    line-height: 50px;
    font-weight: bold;
    margin: 10px;
    text-align: center;
  }

  .introtitle {
    font-size: 26px;
    color: white;
    line-height: 100px;
    font-weight: bold;
    margin: 20px;
    text-align: left;
  }

  .introcontent {
    font-size: 20px;
    color: white;
    line-height: 223px;
    font-weight: bold;
    margin: 10px;
    text-align: left;
  }

  .el-form-item {
    text-align: center;
  }

  .login {
    margin-top: 10px;
    font-size: 14px;
    line-height: 22px;
    color: #2c2fd6;
    cursor: pointer;
    text-align: left;
    text-indent: 8px;
    width: 160px;
  }

  .login:hover {
    color: #2c2fd6;
  }

  .code >>> .el-form-item__content {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-button--primary {
    width: 40%;
    background: #2c2fd6;
    border-color: #2c2fd6;
    color: #fff;
  }

  .code button {
    margin-left: 20px;
    width: 140px;
    text-align: center;
  }
</style>
