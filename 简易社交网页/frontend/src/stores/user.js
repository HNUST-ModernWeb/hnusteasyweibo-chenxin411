import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => ({
        userInfo: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : null
    }),
    actions: {
        setUser(user) {
            this.userInfo = user
            localStorage.setItem('user', JSON.stringify(user))
        },
        logout() {
            this.userInfo = null
            localStorage.removeItem('user')
        }
    }
})