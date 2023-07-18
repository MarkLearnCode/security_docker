<template>
  <div class="homeBox">
    <div>
      <p>HOME PAGE</p>
      <p v-if="!jwtBool">尚未登入</p>
      <p v-if="jwtBool">請按下方按鈕前往頁面</p>
    </div>
    <div class="userInfo" v-if="jwtBool">
      <p>目前訪問者 : {{ username }}</p>
      <p>權限 : {{ role }}</p>
      <p>JWT : {{ jwt }}</p>
      <!-- <p>登入時間 : {{ loginTime }}</p> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
let url = "http://localhost:8080/api/jwtLogin";
export default {
  mounted() {
    // (this.jwt = localStorage.getItem("jwt"))
    this.jwt = localStorage.getItem("jwt");
    this.jwtBool = !(this.jwt === null || this.jwt === "");
    console.log(this.jwtBool);
    console.log(this.jwt);
    // checkLoginTime();
    // console.log(this.jwt);
  },
  data() {
    return {
      username: localStorage.getItem("username"),
      role: localStorage.getItem("role"),
      jwt: "",
      jwtBool: Boolean,
      loginTime: "",
    };
  },
  methods: {
    
  },
};
</script>
<style scoped>
body {
  height: 100vh;
}
.homeBox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70vh;
  width: 100vw;
  font-size: 3rem;
  font: bolder;
}
.homeBox .userInfo {
  width: 80vw;
  word-wrap: break-word; /* 适应容器宽度自动换行 */
  overflow-wrap: break-word; /* 适应容器宽度自动换行 (与 word-wrap 相同，用于兼容性) */
  font-size: 2rem;
}
.homeBox .userInfo p {
  margin-top: 1rem;
}
</style>
