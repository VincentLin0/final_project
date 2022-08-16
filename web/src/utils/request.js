// import axios from 'axios'

// const request = axios.create({
// 	baseURL: '/api',  // 注意！！ 这里是全局统一加上了 '/api' 前缀，也就是说所有接口都会加上'/api'前缀在，页面里面写接口的时候就不要加 '/api'了，否则会出现2个'/api'，类似 '/api/api/user'这样的报错，切记！！！
//     timeout: 500000
// })

// // request 拦截器
// // 可以自请求发送前对请求做一些处理
// // 比如统一加token，对请求参数统一加密
// request.interceptors.request.use(config => {
//     config.headers['Content-Type'] = 'application/json;charset=utf-8';
  
//  // config.headers['token'] = user.token;  // 设置请求头
//     return config
// }, error => {
//     return Promise.reject(error)
// });

// // response 拦截器
// // 可以在接口响应后统一处理结果
// request.interceptors.response.use(
//     response => {
//         let res = response.data;
//         // 如果是返回的文件
//         if (response.config.responseType === 'blob') {
//             return res
//         }
//         // 兼容服务端返回的字符串数据
//         if (typeof res === 'string') {
//             res = res ? JSON.parse(res) : res
//         }
//         return res;
//     },
//     error => {
//         console.log('err' + error) // for debug
//         return Promise.reject(error)
//     }
// )


// export default request


import axios from 'axios'
import Element from "element-ui"



const request = axios.create({
    baseURL: process.env.VUE_APP_SERVER,  // 注意！！ 这里是全局统一加上了 后端接口前缀 前缀，后端必须进行跨域配置！
    
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密

request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    config.headers['Authorization'] = localStorage.getItem("Authorization");
    // config.headers['Set-Cookie'] = localStorage.getItem("Set-Cookie");
    config.headers['token'] = localStorage.getItem("token");  
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(

    response => {
        let res = response.data;
        
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        console.log("abc",response.data)
        if(response.data.code==="500"||response.data.code==="400"||response.data.code==="401"){
            Element.Message.error(response.data.msg)
            return Promise.reject(response.data.msg)
        }

        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error => {
        
        if(error.response.data.code==="400"){
            error.response.data.msg="Login failed. Please log in again~"
            Element.Message.error(error.response.data.msg)
            
        }
        if(error.response.data.code==="403"){
            error.response.data.msg="Insufficient permission, please use the administrator account login"
            Element.Message.error(error.response.data.msg)
            
        }
        if(error.response.data.code==="405"){
            Element.Message.error(error.response.data.msg)
            
        }
        if(error.response.data.code==="500"){
            Element.Message.error(error.response.data.msg)
            
        }
        console.log("error" ,error.response.data.code) // for debug
        return Promise.reject(error)
    }
)


export default request

