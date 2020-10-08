// import {getToken, setToken, removeToken} from "../components/utils/auth";

const user = {
  state: {
    userName: '',
    userPhone:'',
  },
  getters: {
    userName: state => state.userName,
    userPhone:state=>state.userPhone,
  },
  mutations: {
    set_userName: (state, payload) => {
      state.userName = payload.name;
      state.userPhone=payload.phone;
    },
    setInfo: (state, payload) => {
      state.userName = payload.userName;
      state.userPhone=payload.userPhone;
    },
  },
  actions: {
    ChangeInfo({commit}, reqData) {
      return new Promise((resolve, reject) => {
          commit('set_userName',reqData)
          .catch(error => {
          reject(error)
        })
      })
    },
    ChangeInfo_Login({commit}, reqData) {
      return new Promise((resolve, reject) => {
        commit('setInfo',reqData)
          .catch(error => {
            reject(error)
          })
      })
    },
    ChangeInfo_Logout({commit}) {
      return new Promise((resolve, reject) => {
        commit('setInfo','')
          .catch(error => {
            reject(error)
          })
      })
  },
}}
export default user
