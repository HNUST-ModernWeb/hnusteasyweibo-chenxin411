import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'

// 创建Vue实例
const app = createApp(App)

// 挂载Pinia（状态管理）
app.use(createPinia())
// 挂载Vue Router（路由）
app.use(router)

// 挂载到#app元素
app.mount('#app')