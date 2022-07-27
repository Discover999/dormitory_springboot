<template>
<!--  宿舍管理员界面-->
  <el-container class="home_container">
    <el-header class="home_header">
      <div class="home_title">宿舍管理系统-宿舍管理员</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
                  <span class="el-dropdown-link home_userinfo">
                    {{ admin.name }}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
                  </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>

      <el-aside class="home_aside" width="200px">

        <el-menu router>
          <el-menu-item index="/dormitoryHome">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <el-menu-item index="/absentRegister" :class="$route.path==='/absentRegister'?'is-active':''">
            <i class="el-icon-s-order"></i>
            <span slot="title">学生缺寝登记</span>
          </el-menu-item>

          <el-menu-item index="/absentRecord2">
            <i class="el-icon-error"></i>
            <span slot="title">学生缺寝记录</span>
          </el-menu-item>

          <el-menu-item index="/about">
            <i class="el-icon-info"></i>
            <span slot="title">关于</span>
          </el-menu-item>
        </el-menu>

      </el-aside>

      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/dormitoryHome' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>
        <el-footer class="home_footer">2022 © 宿舍管理系统</el-footer>
      </el-container>

    </el-container>

  </el-container>
</template>
<script>
export default {
  methods: {
    logout() {
      let _this = this;
      this.$confirm('注销登录吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        localStorage.removeItem('access-admin')
        _this.$router.replace({path: '/login'})
      })
    }
  },
  data() {
    return {
      admin: ''
    }
  },
  created() {
    let admin = JSON.parse(window.localStorage.getItem('dormitoryAdmin'))
    this.admin = admin
    this.$store.state.admin = admin
  }
}
</script>
<style>

</style>
