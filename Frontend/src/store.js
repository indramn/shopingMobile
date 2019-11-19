import Vue from 'vue'
import Vuex from 'vuex'
import {
    encryptUser
} from './components/shared/service/authService'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        cartProducts: [],
        loggedUser: {},
        heartProducts:[]
    },
    getters: {
        cartProducts: state => {
            return state.cartProducts
        },
        heartProducts: state => {
            return state.heartProducts
        },
        getLoggedUser: state => {
            return state.loggedUser
        }
    },
    mutations: {
        ADD_CART_LOCAL: (state, product) => {
            state.cartProducts.push(product)
            localStorage.setItem('iki-cart', JSON.stringify(state.cartProducts))
        },

        ADD_LOGGED_USER: (state, user) => {
            state.loggedUser = user
            localStorage.setItem('_auth', encryptUser(user))
        },
        ADD_HEART_PRODUCT:(state, product) => {
            state.heartProducts.push(product)
            localStorage.setItem('heart-cart', JSON.stringify(state.heartProducts))
        },
        SET_HEART_PRODUCTS:(state, products) => {
            state.heartProducts = []
            state.heartProducts = products
        },
        SET_CART_PRODUCTS: (state, products) => {
            state.cartProducts = []
            state.cartProducts = products
        },
    },
    actions: {}
})