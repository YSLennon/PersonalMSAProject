<script lang="ts" setup>
import { computed, defineAsyncComponent } from 'vue'

const props = defineProps<{
  label: string
  type?: 'box' | 'text' | 'icon'
  route?: string
  session?: boolean // 로그인 검증
  icon?: string
  onClick?: () => void
}>()

const buttonClass = computed(() => `btn btn-${props.type ?? 'box'}`)

const iconComponent = computed(() => {
  if (props.icon) {
    return defineAsyncComponent(() => import(`@/component/icons/${props.icon}Icon.vue`))
  } else return null
})
</script>
<template>
  <a :href="route ?? ''" :class="buttonClass" @click="onClick">
    <span v-if="type !== 'icon'">
      {{ label }}
    </span>
    <component v-else-if="iconComponent" :is="iconComponent" />
  </a>
</template>
<style scoped>
.btn {
  padding: 10px 7px;
  border: none;
  cursor: pointer;
  font-size: 16px;
  border-radius: 5px;
  font-weight: bold;
}

.btn-box {
  padding: 10px 20px;
  color: var(--color-text);
  font-weight: bold;
  background-color: var(--color-background-soft);
}

button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

@media (hover: hover) {
  .btn-box:hover {
    background-color: var(--color-background-inverse);
    color: var(--color-text-inverse);
  }

  .btn-text:hover {
    color: var(--color-text-soft);
  }
}
</style>
