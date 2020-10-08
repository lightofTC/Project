<template>
  <div>
    <div class="register-wrapper">
      <el-row>
        <el-col span="7" offset="5">
          <div id="intro">
            <p class="introtitle">欢迎注册</p>
            <el-divider></el-divider>
            <p class="introcontent">农产品批发市场大数据分析系统</p>
          </div>
        </el-col>
        <el-col span="6">
          <div id="register">
            <br>
            <p class="title">注册</p>
            <el-form
              :model="ruleFormRegister"
              status-icon
              :rules="rulesRegister"
              ref="ruleFormRegister"
              label-width="0"
              class="demo-ruleForm"
            >
              <el-form-item label="用户名：" prop="name" label-width="80px">
                <el-input v-model="ruleFormRegister.name" auto-complete="off" placeholder="请输入用户名,长度不超过20"
                          maxlength="20"></el-input>
              </el-form-item>
              <el-form-item prop="pass" label="密码：" label-width="80px">
                <el-input type="password" v-model="ruleFormRegister.pass" auto-complete="off"
                          placeholder="输入密码，长度不超过15" show-password maxlength="15"></el-input>
              </el-form-item>
              <el-form-item prop="checkPass" label="确认密码:" label-width="80px">
                <el-input type="password" v-model="ruleFormRegister.checkPass" auto-complete="off"
                          placeholder="确认密码" show-password maxlength="15"></el-input>
              </el-form-item>
              <el-form-item prop="validateCode" class="code">
                <el-row>
                  <el-col span="12">
                    <el-input v-model="ruleFormRegister.validateCode" placeholder="验证码"></el-input>
                  </el-col>
                  <el-col span="4" offset="1">
                    <div class="verify-box" @click="refreshCode">
                      <Identify :identifyCode="identifyCode"></Identify>
                    </div>
                  </el-col>
                </el-row>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleFormRegister')" class="el-button--primary">注册
                </el-button>
                <el-button type="primary" @click="resetForm('ruleFormLogin')" class="el-button--primary">重置
                </el-button>

              </el-form-item>
              <el-form-item><p class="login" @click="gotoLogin">已有账号？立即登录</p></el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import Identify from "./tools/Identify";

  export default {
    name: "Register",
    components: {Identify},
    mounted() {
      this.identifyCode = "";
      this.generatedCode(this.identifyCodes, 4)
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

      //二次验证密码
      let validatePassRegister = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.ruleFormRegister.pass) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
        }
      }
      //验证码格式
      let validateCode = (rule, value, callback) => {
        let re = /^[A-Z0-9#_]{0,4}$/
        if (!re.test(value)) {
          callback(new Error("验证码格式错误"))
        } else {
          callback()
        }
      };
      return {
        ruleFormRegister: {
          pass: "",
          checkPass: "",
          name: "",
          validateCode: ""
        },
        rulesRegister: {
          pass: [{validator: validatePass, trigger: 'change'}],
          checkPass: [{validator: validatePassRegister, trigger: 'change'}],
          name: [{validator: validateName, trigger: 'change'}],
          validateCode: [{validator: validateCode, trigger: 'change'}],
        },
        identifyCodes: '1234567890QWERTYUIOPASDFGHJKLZXCVBNM',
        identifyCode: 'SDF5'
      }
    },
    methods: {
      refreshCode() {
        this.identifyCode = '';
        this.generatedCode(this.identifyCodes, 4);
      },
      randomNum(min, max) {
        return Math.floor(Math.random() * (max - min) + min);
      },
      generatedCode(o, l) {
        for (let i = 0; i < l; i++) {
          this.identifyCode += this.identifyCodes[
            this.randomNum(0, this.identifyCodes.length)]
        }
      },
      //提交注册
      submitForm(formName) {
        this.$refs[formName].validate(valid => {
          if (valid && this.checkCode()) {
            setTimeout(() => {
              this.$axios
                .post(this.GLOBAL.host + '/user/regAdd?userName=' + this.ruleFormRegister.name + "&userPass=" + this.ruleFormRegister.pass + "&userPhone=null")
                .then(successResponse => {
                  console.log(successResponse)
                  if (successResponse.data.status == '200') {
                    this.$message.success('注册成功')
                    this.$router.replace({path: '/login'})
                  } else {
                    this.$message.error(successResponse.data.errorMsg + "，请重新注册")
                    this.resetForm(formName)
                    this.refreshCode()
                  }
                })
            }, 400);
          } else {
            console.log("error submit!!");
            this.resetForm();
            this.refreshCode();
          }
        })
      },
      //进入登录页
      gotoLogin() {
        this.$router.push({
          path: "/Login"
        });
      },
      //验证码是否正确
      checkCode() {
        let vcode = this.ruleFormRegister.validateCode
        let ccode = this.identifyCode
        if (vcode !== ccode) {
          this.$message.error('验证码不正确!')
          this.$set(this.ruleFormRegister, 'validateCode', '')
          this.$set(this.ruleFormRegister, 'pass', '')
          this.$set(this.ruleFormRegister, 'checkPass', '')
          return false
        } else {
          return true
        }
      },
      //重置界面
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }

    }
  };
</script>

<style scoped>
  .loading-wrapper {
    position: fixed;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    background: #aedff8;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .register-wrapper img {
    position: absolute;
    z-index: 1;
  }

  .register-wrapper {
    position: relative;
    background-image: url("../assets/login-bg.png");
    background-size: cover;
    height: 100%;
    top: 0;
    right: 0;
    left: 0;
    bottom: 0;
    padding: 60px;

  }

  #register {
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

  .title {
    font-size: 26px;
    line-height: 50px;
    font-weight: bold;
    margin: 10px;
    text-align: center;
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

  .login:hover {
    color: #2c2fd6;
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

  .code >>> .el-form-item__content {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .code button {
    margin-left: 20px;
    width: 140px;
    text-align: center;
  }

  .verify-box {
    cursor: pointer;
  }

  .el-button--primary {
    width: 40%;
    background: #2c2fd6;
    border-color: #2c2fd6;
    color: #fff;
  }
</style>
