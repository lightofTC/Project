<template>
  <div class="personal-wrapper">
    <div id="personal">
      <p class="title">信息修改</p>
      <br>
      <el-form
        :model="ruleFormPersonal"
        status-icon
        :rules="rulesPersonal"
        ref="ruleFormPersonal"
        class="demo-ruleFormPersonal"
        label-width="120"
      >
        <el-form-item label="用户名:" prop="name">
          <el-input v-model="ruleFormPersonal.name" auto-complete="off"
                    ></el-input>
        </el-form-item>
        <el-form-item label="手机号:" prop="phone">
          <el-input v-model="ruleFormPersonal.phone" auto-complete="off"
                   ></el-input>
        </el-form-item>
        <br>
        <el-form-item>
          <el-button type="primary" @click="save('ruleFormPersonal')" class="el-button--primary">
           更新信息
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    name: "Personal",
    data() {
      //验证用户名格式
      let validateName = (rule, value, callback) => {
        if (value != null) {
          callback();
        } else {
          callback(new Error("用户名格式不正确"));
        }
      }

      //验证手机号
      let validatePhone = (rule, value, callback) => {
        let re = /^1[34578]\d{9}$/
        if (re.test(value)) {
          callback();
        } else {
          callback(new Error("手机号不正确"));
        }
      }

      return {
        ruleFormPersonal: {
          name:this.$store.getters.userName,
          phone: this.$store.getters.userPhone,
        },
        rulesPersonal: {
          name: [{validator: validateName, trigger: 'change'}],
          phone: [{validator: validatePhone, trigger: 'blur'}],
        },
        isSlideVisible: false,
      }
    },
    methods: {
      getStatus: function () {
      },
      save(formName) {

        this.$refs[formName].validate(valid => {
          if (valid) {
            setTimeout(() => {
              this.$message.success('保存中...')
              this.$axios
                //发送数据到后端
                .post(this.GLOBAL.host+'/user/resetUserInfo?userName='+this.$store.getters.userName+'&newName='+this.ruleFormPersonal.name+'&userPhone='+this.ruleFormPersonal.phone)
                .then(successResponse => {
                  if (successResponse.data.status == '200') {
                    this.$message.success('更改成功！')
                    this.$store.dispatch('ChangeInfo',this.ruleFormPersonal)
                  } else {
                  }
                })
            })
          }
        })
      }
    },
    computed: {
     ...mapGetters(['userName']),
    }}
</script>

<style scoped>

  .personal-wrapper img {
    position: relative;
    z-index: 1;
  }

  .personal-wrapper {
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

  #personal {
    width: 600px;
    height: 500px;
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

  .personal {
    margin-top: 10px;
    font-size: 14px;
    line-height: 22px;
    color: #2c2fd6;
    cursor: pointer;
    text-align: left;
    text-indent: 8px;
    width: 160px;
  }

  .personal:hover {
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

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

</style>
