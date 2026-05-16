<template>
  <div class="profile">
    <!-- 头像 -->
    <div class="avatar">
      <img :src="user.avatar || 'https://picsum.photos/100/100'" alt="头像">
    </div>
    <!-- 用户信息 -->
    <h2>{{ user.username }}</h2>
    <p>邮箱：{{ user.email || '未设置' }}</p>
    <!-- 编辑按钮 -->
    <button @click="$router.push('/profile/edit')">编辑资料</button>

    <!-- 我的动态 -->
    <div class="posts">
      <h3>我的动态</h3>

      <div v-if="myPosts.length > 0" class="posts-list">
        <div v-for="post in myPosts" :key="post.id" class="post-item">

          <!-- 动态内容 -->
          <div class="content">{{ post.content }}</div>

          <!-- 🔥 删除按钮 -->
          <button @click="deletePost(post.id)" class="del-btn">删除</button>

        </div>
      </div>

      <div v-else class="no-posts">暂无动态</div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '../utils/request'

// 用户信息
const user = ref(JSON.parse(localStorage.getItem('user')) || {})
// 我的动态
const myPosts = ref([])

// 加载
onMounted(() => {
  if (user.value.id) {
    getMyPosts()
  }
})

// 获取我的动态
const getMyPosts = async () => {
  try {
    const res = await request.get(`/post/user/${user.value.id}`)
    if (res.data.code === 200) {
      myPosts.value = res.data.data
    }
  } catch (err) {
    console.error('获取失败', err)
  }
}

// 🔥 删除动态
const deletePost = async (id) => {
  if (!confirm('确定要删除这条动态吗？')) return

  try {
    await request.delete('/post/delete/' + id)
    alert('删除成功！')
    getMyPosts() // 刷新列表
  } catch (e) {
    alert('删除失败')
  }
}
</script>

<style scoped>
.profile {
  max-width: 800px;
  margin: 30px auto;
  padding: 20px;
  text-align: center;
}
.avatar img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}
.posts {
  margin-top: 30px;
  text-align: left;
}
.post-item {
  padding: 12px;
  background: #f5f5f5;
  margin: 10px 0;
  border-radius: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.content {
  flex: 1;
}
.del-btn {
  background: #ff4444;
  color: #fff;
  border: none;
  padding: 6px 10px;
  border-radius: 4px;
  font-size: 12px;
  cursor: pointer;
}
</style>