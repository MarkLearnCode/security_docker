<template>
  <div class="pageBox">
    <p>Page C</p>
    <p>可進入權限為2</p>
  </div>
</template>

<script>
export default {
  mounted() {},
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
body {
  height: 100vh;
}
.pageBox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70vh;
  width: 100vw;
  font-size: 3rem;
  font: bolder;
}

.pageBox p {
  margin-bottom: 1rem;
}
</style>
