<template lang="">
  <div class = "box">
    <HeadBar></HeadBar>
    <div>
      <img  src="../assets/img/logo1.svg"  alt="cannot  show"  class="logo">
    </div>
    <div class="login" id="login">
      <h2 class = "title">Welcome to sign up</h2>
      <div class="input-wrap">
        <!-- <label for="male">Account number:</label> -->
        <input id = "inputbox1" type="text" v-model="username" placeholder="Account number"/>
        
      </div>
      <div class="input-wrap">
        <!-- <label for="male">Account number:</label> -->
        <input id = "inputbox2" type="email" v-model="email" placeholder="Email"/>
        
      </div>
      <div class="input-wrap">
        <!-- <label for="male">Password：</label> -->
        <input id = "inputbox2" type="password" v-model="password" placeholder="Password"/>
      </div>
      <div class="input-wrap">
        <!-- <button v-on:click="login">登录账号</button>&nbsp;&nbsp; -->
        <button id= "Register" @click="regist">Sign up</button>

      </div>
      
    </div>
  <div id="app">
    <BottomBar></BottomBar>
  </div>
  </div>
</template>
<script>
import HeadBar from '../components/HeadBar.vue'
import BottomBar from '../components/BottomBar.vue'
import request from '../utils/request'
import { Loading } from 'element-ui';


export default {
    name: 'App',
  components: {
    HeadBar,
    BottomBar,
  },

  data () {
    return {
      username: '',
      email:'',
      password: ''
    }
  },
  methods: {
    
    // login () {     

    //   const username = this.username
    //   const email = this.email
    //   const password = this.password
    //   if(username.length == 0 || password.length == 0) {
    //     alert('Please enter your account and password!')
    //     return;
    //   }

    //   request.post("user/login",{username:username,email:email,password:password}).then(res=>{
    //     console.log(res);
    //     if(res.success === true){
    //       alert(res.msg);
    //       this.$router.push({path:"/"});
    //     }else{
    //         alert(res.msg);
    //       }
    //   })


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
      
      const username = this.username
      const password = this.password
      const email = this.email
      if(username.length == 0 || password.length == 0 || email.length ==0) {
        
        this.$message({
        message: 'Please enter your account number and password',
        type: 'warning'
        });
        return;
      }
      var verify = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/;
      if (verify.test(this.email)){
      let loadingInstance = Loading.service({ fullscreen: true });
      request.post("users/signup",{username:username,email:email,password:password}).then(res=>{
          console.log(res);
          loadingInstance.close();
          this.$message({
          message:'Sign up successfully',
          type:'success'});
          this.$router.push('/login');
          }).catch(error => {
            loadingInstance.close();
            console.log(error);
           this.$message.error("Sorry,The user name or email address has been registered!");})
      //   if(res.success === true){
      //     alert(res.msg);
      //     alert("please log on");
      //   }else{
      //       alert(res.msg);
      //     }
      }else{
        this.$message({
        message: 'Please enter the correct account number and password',
        type: 'warning'
        });
        return;
      }
    }

  }
}
</script>
 
<style>
.box{
  background-color:aliceblue;
}
.title{
  font-size: 24px;
  margin-left: 115px;
}
.login {
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
  /* margin-top: 20px; */
  
}
.logo{
  margin-top: 150px;
  margin-left: 12%;
  width: 300px;
}

#inputbox1{
  width: 364px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px;
  margin-left: 35px ;


}
#inputbox2{
  width: 364px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;
  margin-left: 35px ;
}
#Login{
  width: 364px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  margin-left:55px;
}

#Register{
  width: 364px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  margin-left:55px;
  cursor:pointer;
}
@media screen and (min-width: 900px) and (max-width: 1120px) {
  .login {
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
  /* margin-top: 20px; */
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
}
}
 @media screen and (min-width: 720px) and (max-width: 899px) {
  .login {
  width: 456px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  /*margin: 20% auto;*/
  /* margin-top: 20px;  */
  margin-left: 20%;
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  opacity:0;
}
}

 @media screen and (min-width: 425px) and (max-width: 720px) {
  .title{
  font-size: 18px;
  margin-left: 65px;
}
  .login {
  width: 300px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;

  margin-left: 20%;
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  opacity:0;
}

#inputbox1{
  width: 250px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px;
  margin-left: 20px ;
}
#inputbox2{
  width: 250px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;
  margin-left: 20px ;
}
#Login{
  width: 250px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  margin-left:20px;
}

#Register{
  width: 250px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  margin-left:20px;
  cursor:pointer;
}
}
@media screen and (min-width: 0px) and (max-width: 425px) {
  .title{
  font-size: 18px;
  margin-left: 65px;
}
  .login {
  width: 300px;
  height: 396px;
  padding:10px;
  border:0px solid;
  border-radius:7px;
  background-color: white;
  box-shadow: 1px 1px 5px #c4c4c4;
  /*margin: 20% auto;*/
  /* margin-top: 20px;  */
  margin-left: 5%;
}
.logo{
  margin-top: 150px;
  margin-left: 5%;
  width: 300px;
  opacity:0;
}

#inputbox1{
  width: 250px;
  height: 53px;
  margin-top: 15px;
  margin-bottom: 20px;
  margin-left: 20px ;
}
#inputbox2{
  width: 250px;
  height: 53px;
  margin-top: 0px;
  margin-bottom: 20px;
  margin-left: 20px ;
}
#Login{
  width: 250px;
  height: 53px;
  background-color: rgb(80,130,241);
  border:0px solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 20px;
  margin-left:20px;
}

#Register{
  width: 250px;
  height: 53px;
  background-color: rgb(95,170,63);
  border:0px  solid;
  border-radius:5px;
  color: rgb(255, 255, 255);
  font-size: 24px;
  margin-top: 15px;
  margin-left:20px;
  cursor:pointer;
}
}

</style>

