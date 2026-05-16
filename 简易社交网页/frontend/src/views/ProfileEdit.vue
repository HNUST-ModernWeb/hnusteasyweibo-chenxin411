<template>
  <div>
    <h3>修改资料</h3>
    <img :src="user.avatar || 'https://picsum.photos/100/100'" style="width:100px;border-radius:50%">
    <input type="file" accept="image/*" @change="uploadAvatar">
    <input v-model="user.username" placeholder="用户名">
    <input v-model="user.email" placeholder="邮箱">
    <button @click="save">保存</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import request from '../utils/request'

const router = useRouter()
const user = ref({})

onMounted(() => {
  user.value = JSON.parse(localStorage.getItem('user'))
})

const uploadAvatar = (e) => {
  const file = e.target.files[0]
  const reader = new FileReader()
  reader.onload = (res) => {
    user.value.avatar = res.target.result
  }
  reader.readAsDataURL(file)
}

const save = async () => {
  try {
    const res = await request.post('/user/update', user.value)
    localStorage.setItem('user', JSON.stringify(res.data.data))
    alert('修改成功！')
    router.push('/profile')
  } catch (err) {
    alert('修改失败：用户名已存在')
  }
}
</script>