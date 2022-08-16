<template lang="">
  <div class = "box">
    <HeadBar></HeadBar>
        <el-container>
      <el-main>
    <div>
      <img  src="../assets/img/logo1.svg"  alt="cannot  show"  class="logo">
    </div>
    <div class="center">
    <div class="login1">
      <div class="center"><h2 class = "title0">Welcome to Login</h2></div>
      
      
        <!-- <label for="male">Account number:</label> -->
        <div class="center">
        <input class = "inputbox1" type="text" v-model="loginForm.username" placeholder="Username"/>
      </div>
      <div class="center">
        <!-- <label for="male">Password：</label> -->
        <input class = "inputbox2" type="password" v-model="loginForm.password" placeholder="Password"/>
      </div>
      <div>
        <!-- <button v-on:click="login">登录账号</button>&nbsp;&nbsp; -->
        <div class="center"><button class= "Login" @click="login">Log in</button></div>
        <div class="center"><button class= "Register1" @click="regist">Sign up</button></div>
      </div>
    </div>
    </div>
        </el-main>
    </el-container>
  <div id="app">
    <BottomBar></BottomBar>
  </div>
  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import request from '../utils/request'
import { mapMutations } from 'vuex';
import { Loading } from 'element-ui';

export default {
    name: 'App',
  components: {
    HeadBar,
    BottomBar,
  },

  data () {
    return {
      loginForm:{
      username: '',
      password: ''
      },
      userToken:''
    };
  },
  methods: {
    // login () {     

    //   const username = this.username
    //   const password = this.password
    //   if(username.length == 0 || password.length == 0) {
    //     alert('Please enter your account and password!')
    //     return;
    //   }

    //   request.post("users/login",{username:username,password:password}).then(res=>{
    //     console.log(res);
    //     if(res.success === true){
    //       alert(res.msg);
    //       this.$router.push({path:"/"});
    //     }else{
    //         alert(res.msg);
    //       }
      // })


      // this.$axios
      //     .post("/user/login",{
      //       username: username,
      //       password: password,
      //     })
      //     .then((resp) => {
      //       console.log(resp);
      //       let code = resp.data.code;
      //       alert(code)
      //     if(code === 200){
      //       alert("successful!");
      //       this.$router.push({path:"/"});
      //     }else{
      //       alert("login failure!");
      //     }
      //     });  
    // },
    regist(){
      this.$router.push({path:"/signup"})
    },
    // regist(){
    //   const username = this.username
    //   const password = this.password
    //   if(username.length == 0 || password.length == 0) {
    //     alert('Please enter your account and password!')
    //     return;
    //   }

    //   request.post("user/regist",{username:username,password:password}).then(res=>{
    //     console.log(res);
    //     if(res.success === true){
    //       alert(res.msg);
    //       alert("please log in");
    //     }else{
    //         alert(res.msg);
    //       }
    //   })
    // }
...mapMutations(['changeLogin']),
    login () {
      
      let _this = this;
      if (this.loginForm.username === '' || this.loginForm.password === '') {
        this.$message({
        message: 'Please enter your account number and password',
        type: 'warning'
        });
      } else {
        // this.axios({
        //   method: 'post',
        //   url: '/users/login',
        //   data: _this.loginForm
        // })
        let loadingInstance = Loading.service({ fullscreen: true });
        request.post("users/login",_this.loginForm).then(res => {
          let token = res.data.token
          let userInfo = res.data

          localStorage.setItem("userInfo",JSON.stringify(userInfo))
          localStorage.setItem("token",token)

           console.log(res);
          _this.userToken = '';
          // console.log(_this.userToken)
          // 将用户token保存到vuex中
          _this.changeLogin({ Authorization: '' });
          // alert('Log in successfully');
          loadingInstance.close();
          this.$message({
          message:'Log in successfully',
          type:'success'});
          _this.$router.push('/');
          }).catch(error => {
          //  alert('Log in unsuccessful');
          loadingInstance.close();
          this.$message.error('Log in unsuccessful');
           console.log(error);
        });
      }
      }
  }
}

</script>
 
<style>
.box{
  background-color:aliceblue;
}
.title0{
  font-size: 24px;
}
.center0{
  display: flex;
  justify-content: center;
}
.center{
  display: flex;
  justify-content: center;
}


.login1 {
  width: 456px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  /*margin: 20% auto;*/
  margin-top: -320px;
  margin-left: 50%;
  cursor:pointer;
  /* margin-top: 20px; */
}
.logo{
  margin-top: 150px;
  margin-left: 12%;
  width: 300px;
  
}

.inputbox1{
  width: 364px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px
  /* margin-left: 35px ;  */


}
.inputbox2{
  width: 364px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;

}
.Login{
  width: 364px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  cursor:pointer;
}

.Register1{
  width: 364px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  cursor:pointer;
}
@media screen and (min-width: 900px) and (max-width: 1120px) {
  .login1 {
  width: 456px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  /*margin: 20% auto;*/
  margin-top: -320px;
  margin-left: 40%;
  cursor:pointer;
  /* margin-top: 20px; */
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
}
}
 @media screen and (min-width: 720px) and (max-width: 899px) {
  .login1 {
  width: 456px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  /*margin: 20% auto;*/
  margin-left: 0; 

  cursor:pointer;
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  visibility:hidden;
}
}

 @media screen and (min-width: 426px) and (max-width: 720px) {
  .title0{
  font-size: 18px;

}
  .login1 {
  width: 300px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  margin-left: 0; 
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  visibility:hidden;
}

.inputbox1{
  width: 220px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px;
}
.inputbox2{
  width: 220px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;
}
.Login{
  width: 220px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  cursor:pointer;
}

.Register1{
  width: 220px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  cursor:pointer;
}
}
@media screen and (min-width: 0px) and (max-width: 425px) {
  .title0{
  font-size: 18px;

}
  .login1 {
  width: 280px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  margin-left: 0; 
  /* margin-top: 20px;  */

}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  opacity:100;
  visibility:hidden;
}


.inputbox1{
  width: 200px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px;

}
.inputbox2{
  width:200px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;

}
.Login{
  width: 200px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;

  cursor:pointer;
}

.Register1{
  width: 200px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px  solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;

  cursor:pointer;
}
}

</style>

