<script lang="ts" setup>
import RouteButton from '@/components/button/RouteButton.vue'
</script>
<template>
  <header>
    <a href="/" class="logo" width="125" height="125">현이의 가방끈</a>
    <div class="flex-empty"></div>
    <nav>
      <RouteButton route="/" label="Home" />
      <RouteButton route="/shop" label="Shop" />
      <RouteButton route="/study" label="Study" />
      <RouteButton route="/community" label="Community" />
      <!-- <RouteButton route="/event" label="About" /> -->
      <RouteButton route="/faq" label="FAQ" />
    </nav>
    <div class="flex-empty"></div>
    <nav>
      <RouteButton route="/login" label="Login" type="text" />
      <!-- <RouteButton route="/cscenter" label="CSCenter" /> -->
      <RouteButton route="/cart" label="Login" type="icon" icon="Cart" />
      <RouteButton route="/noti" label="noti" type="icon" icon="Noti" />
    </nav>
  </header>
</template>
<style scoped>
header {
  display: flex;
  height: 100px;
  width: 100vw;
  box-shadow: 0px 1px 1px gray;
}

nav {
  margin: auto 0px;
}

.logo {
  display: inline-block;
  margin: auto 1rem;
  font-size: 1.3rem;
  font-weight: bold;
}

.logo:hover {
  cursor: pointer;
}
</style>
