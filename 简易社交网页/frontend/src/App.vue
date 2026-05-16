<template>
  <div id="app">
    <nav class="navbar">
      <div class="logo">社交平台</div>
      <div class="nav-links">
        <!-- 必须用 router-link，确保跳转生效 -->
        <router-link to="/" class="nav-item">首页</router-link>
        <router-link to="/publish" class="nav-item">发布</router-link>
        <router-link to="/profile" class="nav-item">我的</router-link>
        <!-- 登录/退出按钮，绑定点击事件 -->
        <button v-if="!userStore.userInfo" @click="goLogin" class="login-btn">登录</button>
        <button v-else @click="logout" class="login-btn">退出</button>
      </div>
    </nav>
    <!-- 必须有 router-view，路由页面才会渲染 -->
    <router-view />
  </div>
</template>

<script setup>
import { useUserStore } from './stores/user'
import { useRouter } from 'vue-router'

// 正确引入路由实例
const router = useRouter()
const userStore = useUserStore()

// 登录跳转方法
const goLogin = () => {
  router.push('/login')
}

// 退出登录方法
const logout = () => {
  userStore.logout()
  router.push('/') // 退出后跳回首页
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 24px;
  background: #2d8cf0;
  color: white;
}
.logo {
  font-size: 20px;
  font-weight: bold;
}
.nav-links {
  display: flex;
  gap: 20px;
  align-items: center;
}
.nav-item {
  color: white;
  text-decoration: none;
  font-size: 16px;
  transition: color 0.3s;
}
.nav-item:hover {
  color: #e0e0e0;
}
.login-btn {
  background: white;
  color: #2d8cf0;
  border: none;
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  font-weight: bold;
}
.login-btn:hover {
  background: #f0f0f0;
}
</style>