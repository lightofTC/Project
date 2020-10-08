//一级路由
import Vue from 'vue';
import Router from 'vue-router';
import Index from '../components/Index';
import Login from "../components/Login";
import Register from "../components/Register";
import Menu from "../components/Menu";


//二级路由
import Amount from "../components/Amount";
import ForecastPrice from "../components/ForecastPrice";
import Grab from "../components/Grab";
import PriceTrend from "../components/PriceTrend";
import PriceQueryPage from "../components/PriceQueryPage";
import PriceComparisonPage from "../components/PriceComparisonPage";
import Personal from "../components/Personal";
import UserPass from "../components/UserPass";



Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/',
      name: Index,
      component: Index
    },

    {
      path: '/Login',
      name:Login,
      component: Login
    },

    {
      path: '/Register',
      name: Register,
      component: Register
    },
    {
      path: '/Menu',
      name: Menu,
      component: Menu,
      children: [         //二级路由
        {
          path:'/Menu/Grab',
          name:'Grab',
          component: Grab
        },
        {
          path:'/Menu/Amount',
          name:'Amount',
          component:Amount
        },
        {
          path: '/Menu/PriceQueryPage',
          name: 'PriceQueryPage',
          component: PriceQueryPage
        },
        {
          path: '/Menu/PriceTrend',
          name: 'PriceTrend',
          component: PriceTrend
        },
        {
          path: '/Menu/ForecastPrice',
          name: 'ForecastPrice',
          component: ForecastPrice
        },
        {
          path: '/Menu/PriceComparisonPage',
          name: 'PriceComparisonPage',
          component: PriceComparisonPage
        },
        {
          path: '/Menu/Personal',
          name: Personal,
          component: Personal
        },
        {
          path: '/Menu/UserPass',
          name: UserPass,
          component: UserPass
        },
      ]
    }


  ]
})
