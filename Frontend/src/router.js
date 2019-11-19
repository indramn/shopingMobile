import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import Pricing from './views/Pricing.vue'
import CreateProduct from './components/products/actions/CreateProduct'
import ForgotAccount from "./components/ForgotAccount";
import toastr from './components/shared/service/ErrorHandler';
import Welcome from '@/components/chat/Welcome'
import Chat from '@/components/chat/Chat'
import {
    isLoggedIn
} from './components/shared/service/authService'

Vue.use(Router)

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
        path: '/',
        name: 'Home',
        component: Home
    },{
        path: '/welcome',
        name: 'Welcome',
        component: Welcome
    },
    {
      path: '/chat',
      name: 'Chat',
      component: Chat,
      props:true,
      beforeEnter:(to,from,next)=>{
        if(to.params.name){
          next()
        }else{
          next({name:'Welcome'})
        }
      }
    },
    
    {
        path: '/pricing',
        name: 'Pricing',
        component: Pricing
    },
    {
        path: '/forgotAccount',
        name: 'ForgotAccount',
        component: ForgotAccount
    },
    {
        path: '/createProduct',
        name: 'CreateProduct',
        component: CreateProduct
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },
    {
        path: '/products',
        name: 'products',
        component: () => import('./views/Products.vue')
    },
    {
        path: '/products/:id',
        name: 'productDetails',
        component: () => import('./components/products/ProductDetail.vue')
    },
    {
        path: '/createProduct',
        name: 'createProduct',
        component: () => import('./components/products/actions/CreateProduct.vue')
    },
    {
        path: '/cart',
        name: 'cart',
        component: () => import('./components/products/cart/CartProducts.vue'),
        beforeEnter: (to, from, next) => {
            if (isLoggedIn()) {
                next()
            } else {
                next({
                    name: 'login',
                    query: {
                        from: to.name
                    }
                })
            }
        }
    },{
        path: '/heart',
        name: 'heart',
        component: () => import('./components/products/heart/HeartProduct.vue'),
        beforeEnter: (to, from, next) => {
            if (isLoggedIn()) {
                next()
            } else {
                next({
                    name: 'login',
                    query: {
                        from: to.name
                    }
                })
            }
        }
    },
    {
        path: '/checkout',
        name: 'checkout',
        component: () => import('./components/products/cart/Checkout.vue'),
        beforeEnter: (to, from, next) => {
            if (isLoggedIn()) {
                next()
            } else {
                next({
                    name: 'login',
                    query: {
                        from: to.name
                    }
                })
            }
        }
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('./views/login.vue')
    }
    ]
})
