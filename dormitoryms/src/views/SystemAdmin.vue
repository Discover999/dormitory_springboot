<template>
  <!--  系统管理员界面-->
  <el-container class="home_container">
    <el-header class="home_header">
      <div class="home_title">宿舍管理系统-系统管理员</div>
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

          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">首页</span>
          </el-menu-item>

          <el-submenu index="1">
            <template slot="title"><i class="el-icon-s-platform"></i>宿管模块</template>
            <el-menu-item index="/dormitoryAdminAdd" :class="$route.path==='/dormitoryAdminAdd'?'is-active':''">
              <i class="el-icon-folder-add"></i>添加宿管
            </el-menu-item>
            <el-menu-item index="/dormitoryAdminManager" :class="$route.path==='/dormitoryAdminManager'?'is-active':''">
              <i class="el-icon-document-copy"></i>宿管管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-user-solid"></i>学生模块</template>
            <el-menu-item index="/studentAdd" :class="$route.path==='/studentAdd'?'is-active':''">
              <i class="el-icon-folder-add"></i>添加学生
            </el-menu-item>
            <el-menu-item index="/studentManager">
              <i class="el-icon-document-copy"></i>学生管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-office-building"></i>楼栋模块</template>
            <el-menu-item index="/buildingAdd">
              <i class="el-icon-folder-add"></i>添加楼栋
            </el-menu-item>
            <el-menu-item index="/buildingManager">
              <i class="el-icon-document-copy"></i>楼栋管理
            </el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title"><i class="el-icon-school"></i>宿舍模块</template>
            <el-menu-item index="/dormitoryAdd">
              <i class="el-icon-folder-add"></i>添加宿舍
            </el-menu-item>
            <el-menu-item index="/dormitoryManager">
              <i class="el-icon-document-copy"></i>宿舍管理
            </el-menu-item>
          </el-submenu>

          <el-menu-item index="/moveoutRegister">
            <i class="el-icon-s-unfold"></i>
            <span slot="title">学生迁出登记</span>
          </el-menu-item>

          <el-menu-item index="/moveoutRecord">
            <i class="el-icon-s-order"></i>
            <span slot="title">学生迁出记录</span>
          </el-menu-item>

          <el-menu-item index="/absentRecord">
            <i class="el-icon-document-delete"></i>
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
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
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
        localStorage.removeItem('systemAdmin')
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
    this.admin = JSON.parse(window.localStorage.getItem('systemAdmin'))
  }
}
</script>
<style>
.home_container {
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}

.home_header {
  background-color: #2B2B2B;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.home_title {
  color: #C2C2C2;
  font-size: 22px;
  display: inline;
}

.home_userinfo {
  color: #fff;
  cursor: pointer;
}

.home_userinfoContainer {
  display: inline;
  margin-right: 20px;
}

.home_aside {
  height: calc(100vh - 60px);
  background-color: #FFFFFF;
}

.home_footer {
  background-color: #FFFFFF;
  color: #000000;
  font-size: 16px;
  line-height: 60px;
  text-align: center;
}
</style>
