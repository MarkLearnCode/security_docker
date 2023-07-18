<template>
  <div class="navBar">
    <p>Spring Security 6 and Vue3</p>
    <ul class="navList">
      <li class="login button" @click="login">登入</li>
      <li class="sign button" @click="sign">註冊</li>
      <li class="profile button" @click="profile">個人資料</li>
      <li class="logout button" @click="logout">登出</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {},
  methods: {
    login() {
      this.$router.push("/login");
    },
    sign() {
      this.$router.push("/sign");
    },
    profile() {
      this.$router.push("/profile");
    },
    logout() {
      localStorage.removeItem("username");
      localStorage.removeItem("role");
      localStorage.removeItem("jwt");
      alert("登出成功");
      this.$router.push("/");
      location.reload();
    },
  },
};
</script>
<style scoped>
.navBar {
  padding: 1rem;
  display: flex;
  justify-content: space-between;
}
.navBar p {
  font-size: 2rem;
}

.navList {
  display: flex;
  list-style: none;
  padding: 0;
}

.navList li {
  margin-right: 1.5rem;
  font-size: 2rem;
}

.logout {
  margin-left: auto; /* 将最后一项推到最右边 */
}

/* 基本按钮样式 */
.button {
  display: inline-block;
  padding: 8px 16px;
  border: none;
  background-color: #007bff;
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
