<template>
  <div class="footer">
    <div><p>開放權限頁面</p></div>
    <div>
      <ul class="navList">
        <li class="button" @click="home">Home</li>
        <li v-if="checkAuth('pageA')" class="pageA button" @click="pageA">
          pageA
        </li>
        <li v-if="checkAuth('pageB')" class="pageB button" @click="pageB">
          pageB
        </li>
        <li v-if="checkAuth('pageC')" class="pageC button" @click="pageC">
          pageC
        </li>
        <li v-if="checkAuth('pageD')" class="pageD button" @click="pageD">
          pageD
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  created() {
    console.log("当前路径：", this.$route.path);
    console.log("参数：", this.$route.params);
    console.log("查询参数：", this.$route.query);
    console.log("完整路径：", this.$route.fullPath);
    console.log("当前视图路径：", window.location.pathname);
  },

  data() {
    return {
      role: localStorage.getItem("role"),
      path: window.location.pathname,
    };
  },
  watch: {
    $route(to, from) {
      console.log(to.path);
      if (to.path == "/") {
        console.log("from.path = ", from.path);
        location.reload;
      }
    },
  },
  methods: {
    home() {
      this.$router.push("/");
    },
    pageA() {
      this.$router.push("/pageA");
    },
    pageB() {
      this.$router.push("/pageB");
    },
    pageC() {
      this.$router.push("/pageC");
    },
    pageD() {
      this.$router.push("/pageD");
    },
    checkAuth(page) {
      // role = localStorage.getItem("role");
      let role = this.role;
      let pageB_Auths = ["VIP", "MANERGE", "ADMIN"];
      let pageC_Auths = ["MANERGE", "ADMIN"];
      let pageD_Auths = ["ADMIN"];
      if (page === "pageA" && role) {
        return true;
      }
      if (page === "pageB" && pageB_Auths.includes(role)) {
        return true;
      }
      if (page === "pageC" && pageC_Auths.includes(role)) {
        return true;
      }
      if (page === "pageD" && pageD_Auths.includes(role)) {
        return true;
      }
      return false;
    },
  },
};
</script>

<style scoped>
.footer {
  margin-top: 0.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
}
.footer p {
  font-size: 2rem;
  margin-right: 1.5rem;
}

.navList {
  display: flex;
  list-style: none;
  padding: 0;
}

.navList li {
  margin-right: 1.5rem;
  font-size: 1.3rem;
}

/* 基本按钮样式 */
.button {
  display: inline-block;
  padding: 8px 16px;
  border: none;
  background-color: #173e68;
  color: #fff;
  font-size: 14px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color ease;
  user-select: none;
}

.button:hover {
  background-color: #031222;
  color: coral;
  transition: 0.4s;
}

/* 激活样式 */
.button:active {
  background-color: firebrick;
  color: antiquewhite;
  transition: 0.01s;
}
</style>
