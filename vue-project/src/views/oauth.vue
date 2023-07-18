<script setup>
import { ref } from "vue";
const data = ref();

const callback = (response) => {
  data.value = response;
};
</script>

<template>
  <div>
    <GoogleLogin :callback="callback" />
    <p>
      {{ data }}
    </p>
  </div>
</template>

<style scoped>
p {
  margin-top: 12px;
  word-break: break-all;
}
</style>
