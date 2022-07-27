<template>
  <div class="main">
    <div class="left">
      <h3>------------------------------------------------------------------------</h3>
      <h3>此程序来自B站 <a href="https://space.bilibili.com/434617924" target="_blank">"楠哥教你学Java"</a></h3>
      <div style="font-size: 26px"><a href="https://www.bilibili.com/video/BV1YS4y1v7hL" target="_blank">B站视频链接</a></div>
      <h1>@如有侵权联系删库</h1>
      <h3>------------------------------------------------------------------------</h3>
    </div>
    <div class="right">
      <div style="padding-left: 60px">
        <h2>如果你觉得有帮助</h2>
        <img class="img" src="../assets/wechat.jpg" alt=""/>&nbsp;&nbsp;&nbsp;&nbsp;
        <img class="img" src="../assets/alipay.jpg" alt=""/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "About",
  data() {
    return {}
  }
}
</script>

<style scoped>
.main {
  width: 100%;
  height: 90%;
  display: flex;
}

.left {
  width: 300px;
  height: 100%;
  overflow-y: auto;
}

.right {
  flex: 1;
  height: 100%;
  overflow-y: auto;
  border-radius: 20px;
  background-image: linear-gradient( 135deg, #EE9AE5 10%, #5961F9 100%);
}

.img{
  width:300px;
  height:400px;
}

</style>