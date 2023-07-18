<template>
  <div class="signBox">
    <p class="hFont">Sign PAGE</p>
    <div>
      <label class="font">username : </label>
      <input class="font" v-model="username" type="text" />
    </div>
    <div>
      <label class="font">password : </label>
      <input class="font" v-model="password" type="password" />
    </div>
    <div>
      <label class="font">check password : </label>
      <input class="font" v-model="checkPassword" type="password" />
    </div>
    <div>
      <select v-model="selectedRole">
        <option v-for="(role, index) in roles" :value="index" :key="index">
          {{ role }}
        </option>
      </select>
    </div>
    <div><button @click="sign">註冊</button></div>
  </div>
</template>

<script>
import axios from "axios";
let url = "http://localhost:8080/api/sign";

export default {
  mounted() {},
  data() {
    return {
      username: "",
      password: "",
      checkPassword: "",
      roles: ["GUEST", "VIP", "MANERGE", "ADMIN"],
      selectedRole: 0,
    };
  },
  methods: {
    sign() {
      if (this.checkPwd()) {
        const signData = {
          username: this.username,
          password: this.password,
          role: this.selectedRole,
        };
        axios
          .post(url, signData)
          .then((res) => {
            console.log(res);
            console.log("sign success");
            alert("註冊成功 前往登入頁面");
            this.$router.push("/login");
          })
          .catch((e) => {
            console.log(e);
            alert("帳號重複");
          });
      } else {
        alert("請確認密碼");
      }
    },
    checkPwd() {
      if (this.password === this.checkPassword) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
<style scoped>
body {
  height: 100vh;
}
.signBox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70vh;
}
.signBox div {
  margin-top: 1rem;
  display: flex;
  text-align: center;
}
.signBox button {
  font-size: 2rem;
}
.signBox .hFont {
  font-size: 3rem;
}
.signBox .font {
  font-size: 2rem;
}
select {
  font-size: 2rem;
}
option {
  font-size: 2rem;
}
div label {
  width: 300px;
}
</style>
