<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import Login from "@/views/Login";

export default {
  name: 'app',
  components: {
    Login,
  }
}
</script>

<style>

</style>
