<!-- 
<script setup>
import { ref } from "vue";
import { googleTokenLogin } from "vue3-google-login";
// const data = ref();
// const GOOGLE_CLIENT_ID =
//   "34570728790-fb1r5c48bb6inri6cghbacbk5brgm9qq.apps.googleusercontent.com";

// const callback = (response) => {
//   data.value = response;
//   console.log(response);
// };
const handleGoogleAccessTokenLogin = () => {
  googleTokenLogin({
    clientId: GOOGLE_CLIENT_ID,
  }).then((response) => {
    data.value = response;
    console.log(response);
    console.log(response.access_token);
  });
};
</script>

 -->

<template>
  <!-- <div>
    <GoogleLogin :callback="callback" popup-type="TOKEN" />
    <p>
      {{ data }}
    </p>
  </div> -->

  <div class="loginBox">
    <p class="hFont">Login PAGE</p>
    <div>
      <label class="font">username : </label>
      <input class="font" v-model="username" type="text" />
    </div>
    <div>
      <label class="font">password : </label>
      <input class="font" v-model="password" type="password" />
    </div>
    <div><button @click="login">登入</button></div>
    <div>
      <button type="button" @click="handleGoogleAccessTokenLogin">
        使用 Google 進行登入
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { ref } from "vue";
import { googleTokenLogin } from "vue3-google-login";
let url = "http://localhost:8080/api/login";
let jwtUrl = "http://localhost:8081/api/jwtLogin";
let oAuthUrl = "http://localhost:8081/api/oauthLogin";

const data = ref();
const GOOGLE_CLIENT_ID =
  "34570728790-fb1r5c48bb6inri6cghbacbk5brgm9qq.apps.googleusercontent.com";

export default {
  mounted() {
    this.jwt = localStorage.getItem("jwt");
    this.jwtBool = !(this.jwt === null || this.jwt === "");
    this.jwtLogin();
  },
  data() {
    return {
      username: "",
      password: "",
      jwtBool: Boolean,
    };
  },
  methods: {
    handleGoogleAccessTokenLogin() {
      googleTokenLogin({
        clientId: GOOGLE_CLIENT_ID,
      }).then((response) => {
        data.value = response;
        console.log(response);
        console.log(response.access_token);
        // googleToken = response.access_token;
        let googleToken = { gJwt: response.access_token };
        // googleToken.gJwt = response.access_token;
        this.oAuthLogin(googleToken);
      });
    },
    oAuthLogin(googleToken) {
      axios
        .post(oAuthUrl, googleToken)
        .then((res) => {
          console.log(res);
          console.log("login success");
          localStorage.setItem("username", res.data.username);
          localStorage.setItem("role", res.data.role);
          localStorage.setItem("jwt", res.data.jwt);
          alert("登入成功");
          this.$router.push("/");
          location.reload();
        })
        .catch((e) => {
          console.log(e);
          alert("請確認登入資訊");
        });
    },
    onSuccess(response) {
      console.log("success");
      console.log(response);
    },
    onError(error) {
      console.log("error");
      console.log(error);
    },
    login() {
      const loginData = {
        jwt: "",
        username: this.username,
        password: this.password,
      };
      axios
        .post(url, loginData)
        .then((res) => {
          console.log(res);
          console.log("login success");
          localStorage.setItem("username", res.data.username);
          localStorage.setItem("role", res.data.role);
          localStorage.setItem("jwt", res.data.jwt);
          alert("登入成功");
          this.$router.push("/");
          location.reload();
        })
        .catch((e) => {
          console.log(e);
          alert("請確認登入資訊");
        });
    },

    jwtLogin() {
      if (this.jwtBool) {
        const headers = {
          Authorization: `Bearer ${this.jwt}`,
        };
        axios
          .post(jwtUrl, {}, { headers })
          .then((res) => {
            console.log(res);
            console.log("jwtLogin success");
            // localStorage.setItem("username", res.data.username);
            // localStorage.setItem("role", res.data.role);
            // localStorage.setItem("jwt", res.data.jwt);
            this.$router.push("/");
          })
          .catch((e) => {
            console.log(e);
            alert("請確認登入資訊");
          });
      }
    },
  },
};
</script>

<style scoped>
body {
  height: 100vh;
}
.loginBox {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 70vh;
}
.loginBox div {
  margin-top: 1rem;
}
.loginBox button {
  font-size: 2rem;
}
.loginBox .hFont {
  font-size: 3rem;
}
.loginBox .font {
  font-size: 2rem;
}
button {
  cursor: pointer;
}
</style>
