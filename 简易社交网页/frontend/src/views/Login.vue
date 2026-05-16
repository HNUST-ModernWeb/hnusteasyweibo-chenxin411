<template>
  <div class="login">
    <h2>登录</h2>
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" placeholder="密码" type="password" />
    <button @click="login">登录</button>
    <p @click="$router.push('/register')">没有账号？去注册</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '../stores/user'
import request from '../utils/request'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const userStore = useUserStore()
const router = useRouter()

const login = async () => {
  const res = await request.post('/user/login', {
    username: username.value,
    password: password.value
  })
  if (res.data.code === 200) {
    userStore.setUser(res.data.data)
    router.push('/')
    alert('登录成功')
  } else {
    alert(res.data.msg)
  }
}
</script>

<style scoped>
.login {
  max-width: 400px;
  margin: 50px auto;
  display: flex;
  flex-direction: column;
  gap: 16px;
}
input {
  padding: 10px;
}
button {
  padding: 10px;
  background: #2d8cf0;
  color: white;
  border: none;
  cursor: pointer;
}
</style>