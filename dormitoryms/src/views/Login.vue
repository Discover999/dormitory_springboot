<template>
<!--  登录主界面-->
  <div>
    <el-form ref="loginForm" :model="loginForm" :rules="rules" class="loginForm">
      <h3 class="loginTitle">系统登录</h3>
      <!--用户名输入框-->
      <el-form-item prop="username">
        <el-input v-model="loginForm.username"
                  auto-complete="false"
                  placeholder="请输入用户名"
                  type="text">
        </el-input>
      </el-form-item>
      <!--密码输入框-->
      <el-form-item prop="password">
        <el-input v-model="loginForm.password"
                  auto-complete="false"
                  placeholder="请输入密码"
                  type="password">
        </el-input>
      </el-form-item>
      <!--角色选择框-->
      <el-form-item>
        <el-radio v-model="type" label="systemAdmin">系统管理员</el-radio>
        <el-radio style="position: relative;left: 80px" v-model="type" label="dormitoryAdmin">宿舍管理员</el-radio>
      </el-form-item>
      <!--功能区-->
      <el-button style="width: 100%" type="primary" @click="submitLogin" :loading="loginLoad">登录</el-button>
    </el-form>
  </div>
</template>

<script>
import Nprogress from 'nprogress'
import 'nprogress/nprogress.css'

export default {
  name: "Login",
  data() {
    return {
      captchaUrl: '',
      loginForm: {
        username: 'admin',
        password: '123456'
      },
      type: 'systemAdmin',
      loginLoad: false,
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      }
    }
  },
  methods: {
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          Nprogress.start()
          this.loginLoad = true
          let _this = this
          if (_this.type === 'dormitoryAdmin') {
            axios.get('http://localhost:9090/dormitoryAdmin/login', {params: _this.loginForm}).then(function (resp) {
              _this.loginLoad = false
              Nprogress.done()
              if (resp.data.code === -1) {
                _this.$alert('用户名不存在', '错误提示', {
                  confirmButtonText: '确定'
                })
              }
              if (resp.data.code === -2) {
                _this.$alert('密码错误', '错误提示', {
                  confirmButtonText: '确定'
                })
              }
              if (resp.data.code === 0) {
                //跳转到SystemAdmin
                //展示当前登录用户信息
                localStorage.setItem('dormitoryAdmin', JSON.stringify(resp.data.data));
                _this.$router.replace({path: '/dormitoryAdmin'})
              }
            })
          }
          if (_this.type === 'systemAdmin') {
            axios.get('http://localhost:9090/systemAdmin/login', {params: _this.loginForm}).then(function (resp) {
              _this.loginLoad = false
              Nprogress.done()
              if (resp.data.code === -1) {
                _this.$alert('用户名不存在', '错误提示', {
                  confirmButtonText: '确定'
                })
              }
              if (resp.data.code === -2) {
                _this.$alert('密码错误', '错误提示', {
                  confirmButtonText: '确定'
                })
              }
              if (resp.data.code === 0) {
                //跳转到SystemAdmin
                //展示当前登录用户信息
                localStorage.setItem('systemAdmin', JSON.stringify(resp.data.data));
                _this.$router.replace({path: '/systemAdmin'})
              }
            })
          }

        } else {
          this.$message.error('请输入所有字段');
          // console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
.loginForm {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 15px 35px 15px 35px;
  background: white;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.loginTitle {
  margin: 0 auto 40px auto;
  text-align: center;
}
</style>
