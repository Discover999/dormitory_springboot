<template>
  <div class="center-middle">
    <div>
      您好,<span class="user-name">{{ admin.name }}</span>
      <h3 style="font-style: italic">Welcome! 欢迎使用</h3>
      <h2>宿舍管理系统</h2>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
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

<style scoped>
.center-middle {
  height: 90%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.user-name {
  font-style: oblique;
  padding-left: 5px;
  color: red;
}
</style>