import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/Index.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Publish from '../views/Publish.vue'
import Profile from '../views/Profile.vue'
import ProfileEdit from '../views/ProfileEdit.vue'  // 👈 加了编辑页

const routes = [
    {
        path: '/',
        name: 'Index',
        component: Index,
        meta: { title: '首页' }
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
        meta: { title: '登录' }
    },
    {
        path: '/register',
        name: 'Register',
        component: Register,
        meta: { title: '注册' }
    },
    {
        path: '/publish',
        name: 'Publish',
        component: Publish,
        meta: { title: '发布' },
        beforeEnter: (to, from, next) => {
            const user = JSON.parse(localStorage.getItem('user'))
            if (user) {
                next()
            } else {
                next('/login')
            }
        }
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile,
        meta: { title: '我的' },
        beforeEnter: (to, from, next) => {
            const user = JSON.parse(localStorage.getItem('user'))
            if (user) {
                next()
            } else {
                next('/login')
            }
        }
    },
    // 👇 👇 👇 关键：编辑资料页面（我给你加上了）
    {
        path: '/profile/edit',
        name: 'ProfileEdit',
        component: ProfileEdit,
        meta: { title: '编辑资料' },
        beforeEnter: (to, from, next) => {
            const user = JSON.parse(localStorage.getItem('user'))
            if (user) {
                next()
            } else {
                next('/login')
            }
        }
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router