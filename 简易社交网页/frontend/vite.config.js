import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    server: {
        port: 5175, // 固定端口，避免自动切换
        open: true // 启动自动打开浏览器
    }
})