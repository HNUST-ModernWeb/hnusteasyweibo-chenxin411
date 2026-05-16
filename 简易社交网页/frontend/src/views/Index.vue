<template>
  <div class="home">
    <h1>动态广场</h1>

    <div class="post-list">
      <div v-for="post in postList" :key="post.id" class="post-item">
        <div class="post-header">
          <img :src="post.avatar || 'https://picsum.photos/40/40'" class="avatar" />
          <span>{{ post.username || '匿名用户' }}</span>
        </div>

        <div class="content">{{ post.content }}</div>

        <!-- 🔥 显示图片 -->
        <img v-if="post.image" :src="post.image" class="post-image" />

        <div class="like-area">
          <button
              @click="toggleLike(post.id)"
              class="like-btn"
              :class="{ active: likeStatus[post.id] }"
          >
            👍 {{ likeCount[post.id] || 0 }}
          </button>
        </div>

        <div class="comments">
          <div v-for="c in comments[post.id]" :key="c.id" class="comment">
            <img :src="c.avatar || 'https://picsum.photos/28/28'" class="c-avatar" />
            <div class="c-info">
              <div class="c-name">{{ c.username }}</div>
              <div class="c-content">{{ c.content }}</div>
            </div>
          </div>
        </div>

        <div class="comment-input">
          <input v-model="commentText[post.id]" placeholder="发表评论..." />
          <button @click="publishComment(post.id)">发送</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import request from '../utils/request'

const postList = ref([])
const comments = ref({})
const commentText = ref({})
const user = ref(JSON.parse(localStorage.getItem('user')) || null)

const likeCount = ref({})
const likeStatus = ref({})

onMounted(() => {
  if (!user.value) {
    alert("请先登录！")
    return
  }
  getPosts()
})

const getPosts = async () => {
  try {
    const res = await request.get('/post/list')
    postList.value = res.data.data
    postList.value.forEach(post => {
      getComments(post.id)
      loadLikeInfo(post.id)
    })
  } catch (err) {
    console.error("获取动态失败", err)
  }
}

const loadLikeInfo = async (postId) => {
  try {
    const res = await request.get(`/like/info/${postId}?userId=${user.value.id}`)
    likeCount.value[postId] = res.data.count
    likeStatus.value[postId] = res.data.liked
  } catch (e) {}
}

const toggleLike = async (postId) => {
  try {
    await request.post(`/like/toggle/${postId}?userId=${user.value.id}`)
    loadLikeInfo(postId)
  } catch (e) {
    alert("操作失败")
  }
}

const getComments = async (postId) => {
  try {
    const res = await request.get('/comment/list/' + postId)
    comments.value[postId] = res.data.data
  } catch (err) {
    console.error("获取评论失败", err)
  }
}

const publishComment = async (postId) => {
  if (!user.value) {
    alert("请先登录")
    return
  }
  let content = commentText.value[postId]
  if (!content || content.trim() === '') {
    alert("请输入评论内容")
    return
  }

  try {
    await request.post('/comment/publish', {
      postId: postId,
      userId: user.value.id,
      content: content.trim(),
      username: user.value.username,
      avatar: user.value.avatar
    })
    commentText.value[postId] = ''
    getComments(postId)
  } catch (err) {
    alert("评论失败")
  }
}
</script>

<style scoped>
.home { max-width: 800px; margin: 30px auto; padding: 20px; }
.post-item { background: #f5f5f5; padding: 15px; margin: 10px 0; border-radius: 10px; }
.post-header { display: flex; align-items: center; gap: 10px; margin-bottom: 8px; }
.avatar { width: 40px; height: 40px; border-radius: 50%; }
.content { margin-bottom: 10px; }

/* 🔥 图片样式 */
.post-image {
  max-width: 100%;
  max-height: 300px;
  border-radius: 8px;
  margin: 10px 0;
  object-fit: cover;
}

.like-area { margin-bottom: 10px; }
.like-btn {
  border: none;
  background: #eee;
  padding: 4px 10px;
  border-radius: 6px;
  cursor: pointer;
  font-size: 14px;
}
.like-btn.active {
  background: #ff6b6b;
  color: white;
}

.comments { margin: 10px 0; font-size: 14px; }
.comment { display: flex; gap: 8px; padding: 4px 0; }
.c-avatar { width: 28px; height: 28px; border-radius: 50%; }
.c-name { font-weight: bold; }
.c-content { color: #555; }

.comment-input { display: flex; gap: 8px; margin-top: 8px; }
.comment-input input { flex: 1; padding: 6px; border-radius: 4px; border: 1px solid #ddd; }
.comment-input button { padding: 6px 10px; background: #42b983; color: #fff; border: none; border-radius: 4px; }
</style>