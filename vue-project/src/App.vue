<script setup>
import { RouterLink, RouterView } from "vue-router";
import CountBtn from "./components/countBtn.vue";
import NuvBarVue from "./components/navBar.vue";
import Home from "./views/home.vue";
import Footer from "./components/footer.vue";
</script>

<template>
  <header>
    <NuvBarVue />
  </header>
  <section>
    <RouterView />
  </section>
  <footer>
    <Footer />
  </footer>
</template>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  width: 100vw;
  /* height: 100vh; */
}
</style>
<!-- <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink> -->
