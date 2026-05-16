<template>
  <div class="register">
    <h2>注册</h2>
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" placeholder="密码" type="password" />
    <input v-model="email" placeholder="邮箱" />
    <button @click="register">注册</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import request from '../utils/request'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const email = ref('')
const router = useRouter()

const register = async () => {
  const res = await request.post('/user/register', {
    username: username.value,
    password: password.value,
    email: email.value
  })
  if (res.data.code === 200) {
    alert('注册成功')
    router.push('/login')
  } else {
    alert(res.data.msg)
  }
}
</script>

<style scoped>
.register {
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