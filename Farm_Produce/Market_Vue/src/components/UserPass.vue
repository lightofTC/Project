<template>
  <div class="pass-wrapper">
    <div id="pass">
      <p class="title">密码修改</p>
      <br>
      <el-form
        :model="ruleFormPass"
        status-icon
        :rules="rulesPass"
        ref="ruleFormPass"
        class="demo-ruleFormPass"
        label-width="120"
      >
        <el-steps  :active="active" finish-status="success">
          <el-step title="输入原密码"></el-step>
          <el-step title="输入新密码"></el-step>
          <el-step title="再次输入新密码"></el-step>
        </el-steps>
        <br>
        <el-form-item label="原密码:" prop="pass">
          <el-input type="password" v-model="ruleFormPass.pass" auto-complete="off"
                    placeholder="密码长度至少为4" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码:" prop="newPass">
          <el-input type="password" v-model="ruleFormPass.newPass" auto-complete="off"
                    placeholder="输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="再次输入新密码:" prop="checkPass">
          <el-input type="password" v-model="ruleFormPass.checkPass" auto-complete="off"
                    placeholder="需与新密码一致" show-password></el-input>
        </el-form-item>
        <br>
        <el-form-item>
          <el-button type="primary" @click="save('ruleFormPass')" class="el-button--primary">
            保存
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from "vuex";

  export default {
    data() {
      let validatePass = (rule, value, callback) => {
        let re = /^[a-zA-Z0-9#_]{4,25}$/
        if (!re.test(value)) {
          callback(new Error("密码格式不正确"))
        } else {
          callback();
          this.active=1;
        }
      }
      let validatePass1 = (rule, value, callback) => {
        let re = /^[a-zA-Z0-9#_]{4,25}$/
        if (!re.test(value)) {
          callback(new Error("密码格式不正确"))
        } else {
          callback();
          this.active=2;
        }
      }
      let validatePass2 = (rule, value, callback) => {
        if (value === "") {
          callback(new Error("请再次输入密码"));
        } else if (value !== this.ruleFormPass.newPass) {
          callback(new Error("两次输入密码不一致!"));
        } else {
          callback();
          this.active=3;
        }
      };
      return {
        active:'',
        ruleFormPass: {
          pass: "",
          newPass: "",
          checkPass: "",
        },
        rulesPass: {
          pass: [{validator: validatePass,trigger: "change"}],
          newPass: [{validator: validatePass1,trigger: "change"}],
          checkPass: [{validator: validatePass2, trigger: "blur"}]
        },
        fullscreenLoading:false,
      }
    },
    methods:{
      save(formName){
        this.$refs[formName].validate(valid => {
          if (valid) {
            this.fullscreenLoading = true;
            setTimeout(() => {
                this.fullscreenLoading = false;
                this.$axios
                  //发送数据到后端
                  .post(this.GLOBAL.host+'/user/resetPass?userName='+this.$store.getters.userName+'&userPass='+this.ruleFormPass.pass+'&newUserPass=' + this.ruleFormPass.newPass)
                  .then(successResponse => {
                    if (successResponse.data.status == '200') {
                      this.$message.success('更改成功')

                    } else {
                      this.$message.error('原密码错误，请重新输入')
                      this.resetForm(formName)
                    }

                  })
              }
            )

          }
        })
      }
    },
    mounted:{
      ...mapGetters ( ['userName'])
    }
  }
</script>

<style scoped>
  .pass-wrapper img {
    position: relative;
    z-index: 1;
  }

  .pass-wrapper {
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

  #pass {
    width: 600px;
    height: 600px;
    margin: 60px auto;
    background: #fff;
    padding: 20px 40px;
    position: relative;
    z-index: 9;
    box-sizing: border-box;
    border-radius: 10px 10px 10px 10px;
  }

  .title {
    position: relative;
    font-size: 26px;
    line-height: 50px;
    font-weight: bold;
    margin: 10px;
    text-align: center;
  }

  .el-form-item {
    text-align: center;
  }

  .pass {
    margin-top: 10px;
    font-size: 14px;
    line-height: 22px;
    color: #2c2fd6;
    cursor: pointer;
    text-align: left;
    text-indent: 8px;
    width: 160px;
  }

  .pass:hover {
    color: #2c2fd6;
  }

  .code >>> .el-form-item__content {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }


  .el-button--primary {
    width: 100%;
    background: #2c2fd6;
    border-color: #2c2fd6;
    color: #fff;
  }

  .code button {
    margin-left: 20px;
    width: 140px;
    text-align: center;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
</style>
