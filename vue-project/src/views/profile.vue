<template>
  <div class="user-details">
    <div class="field">
      <label for="id">ID:</label>
      <input class="readonlyInput" type="text" id="id" readonly value="123" />
    </div>
    <div class="field">
      <label for="username">Username:</label>
      <input
        class="readonlyInput"
        type="text"
        id="username"
        readonly
        value="xxx"
      />
    </div>
    <div class="field">
      <label for="password">Password:</label>
      <input type="password" id="password" value="******" />
    </div>
    <div class="field">
      <label for="role">Role:</label>
      <select v-model="selectedRole">
        <option v-for="(role, index) in roles" :value="index" :key="index">
          {{ role }}
        </option>
      </select>
      <!-- <input type="text" id="role" value="..." /> -->
    </div>
    <div class="field">
      <label for="is_used">帳號狀態:</label>
      <input
        class="readonlyInput"
        type="text"
        id="is_used"
        readonly
        value="..."
      />
    </div>
    <div class="field">
      <label for="is_google_sign">帳號類型:</label>
      <input
        class="readonlyInput"
        type="text"
        id="is_google_sign"
        readonly
        value="..."
      />
    </div>
    <div class="button-container">
      <button id="update-button">確認修改</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
let url = "http://localhost:8080/api/sign";
export default {
  mounted() {},
  data() {
    return {
      roles: ["GUEST", "VIP", "MANERGE", "ADMIN"],
      selectedRole: 0,
      id: "",
      username: "",
      is_used: "",
    };
  },
  methods: {
    // update(){
    //     const user={
    //     }
    // }
  },
};
</script>

<style scoped>
.user-details {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
}

.field {
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}

label {
  font-weight: bold;
  width: 120px;
}

input[type="text"],
input[type="password"] {
  width: 200px;
  padding: 5px;
  font-size: 1rem;
  text-align: center;
}

.button-container {
  margin-top: 10px;
  margin-bottom: 10px;
}
#update-button {
  font-size: 1.5rem;
  cursor: pointer;
}
select {
  width: 200px;
  padding: 5px;
  font-size: 1rem;
  text-align: center;
  border: 2px solid;
}
.readonlyInput {
  cursor: default;
}
</style>
