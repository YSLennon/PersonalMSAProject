<script setup lang="ts">
import { RouterView } from 'vue-router'
import HeaderView from './components/layout/HeaderView.vue'
</script>

<template class="layout-flex">
  <HeaderView />
  <RouterView />
  <footer class="footer">
    <div>현이의 가방끈</div>
    <div>책과 배움을 이어주는 플랫폼</div>
    <div>스터디 모임 | 커뮤니티 | 도서 판매</div>
    <div>© 2024 현이의 가방끈. All rights reserved.</div>
  </footer>
</template>

<style scoped>
.layout-flex {
  display: flex;
  flex-direction: column;
}
</style>
