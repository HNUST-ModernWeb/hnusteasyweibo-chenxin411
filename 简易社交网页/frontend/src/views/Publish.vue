<template>
  <div class="publish">
    <h2>发布动态</h2>

    <textarea v-model="content" rows="4" placeholder="说点什么..."></textarea>

    <!-- 🔥 图片上传 -->
    <input type="file" accept="image/*" @change="uploadImage" />
    <img v-if="preview" :src="preview" class="preview" />

    <button @click="publish">发布</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import request from '../utils/request'
import { useRouter } from 'vue-router'

const router = useRouter()
const content = ref('')
const preview = ref('')
const imageBase64 = ref('')
const user = JSON.parse(localStorage.getItem('user'))

// 上传图片（转 Base64）
const uploadImage = (e) => {
  const file = e.target.files[0]
  if (!file) return

  const reader = new FileReader()
  reader.onload = () => {
    imageBase64.value = reader.result
    preview.value = reader.result
  }
  reader.readAsDataURL(file)
}

// 发布
const publish = async () => {
  try {
    await request.post('/post/publish', {
      content: content.value,
      userId: user.id,
      image: imageBase64.value // 🔥 传图片
    })
    alert('发布成功！')
    router.push('/')
  } catch (err) {
    alert('发布失败')
  }
}
</script>

<style scoped>
.publish {
  max-width: 600px;
  margin: 30px auto;
  padding: 20px;
}
textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ddd;
}
.preview {
  width: 100%;
  max-height: 200px;
  object-fit: cover;
  margin: 10px 0;
  border-radius: 8px;
}
button {
  padding: 10px 20px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 6px;
}
</style>