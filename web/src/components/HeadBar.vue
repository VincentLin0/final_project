<template>
  <div class="navigation_whole">
<!--    {{userInfo}}-->
    <img src="../assets/img/logo1.png" alt="cannot show" class="logo1">
    <span class="navigation">
      <span @click="home" class="nav1">Home</span>
      <span v-if = "isShow == false" @click="login"  class="nav3" >Log in</span> 
			<span v-if = "isShow == false" @click="signup"  class="nav3" >Sign up</span>
      <span v-if = "isShow == true" @click="login"  class="nav3" >My collection</span>
      <span v-if = "isShow1 == true" @click="backStage"  class="nav3" >Management</span>
      <span v-if = "isShow == true" @click="logout"  class="nav3" >Log out</span>
      
				</span>
  </div>
</template>

<script>
import "../css/font.css";

export default {
  name: 'HeadBar',
    data:function(){
      return{
        userInfo:[],
      isShow : false,
      isShow1 : false,
      admin:{
        roles:{
          name :''
        },
        
      },
      }
    },
  methods:{
    backStage(){
      this.$router.push('/backStage/testManagement');
    },
    home(){
      this.$router.push('/');
    },
    login(){
      this.$router.push('/login');
    },
    signup(){
      this.$router.push('/signup');
    },
    changeIsShow(){

    let token = localStorage.getItem('token')||'';
    console.log(token,"-------------------")
 
    if (token === null || token === '') {
      this.isShow = false;
    } else {
      this.isShow = true;
    }
    },
        changeIsShow1(){
          
          let adminStr = localStorage.getItem('userInfo')
          this.admin = JSON.parse(adminStr)
          if(this.admin != null){
          console.log(this.admin.roles[0].name)
          if( this.admin.roles[0]!= null &&'ROLE_ADMIN' == this.admin.roles[0].name ){
            // console.log("admin")
            this.isShow1 = true
          }
          if(this.admin.roles[1]!= null && 'ROLE_ADMIN' == this.admin.roles[1].name){
            // console.log("admin")
            this.isShow1 = true
          }
          }
          // console.log(admin.roles[0])
          // if(admin.hasOwnProperty('ROLE_ADMIN')){

          // }
    // let token = localStorage.getItem('token')||'';
    // console.log(token,"-------------------")
 
    // if (token === null || token === '') {
    //   this.isShow1 = false;
    // } else {
    //   this.isShow1 = true;
    // }
    },
        
    logout(){
      //清空本地缓存，跳转首页

      localStorage.clear()
      this.$router.push('login');


/*      request.post("users/logout",this.loginForm).then(res => {
          console.log(res);
          this.$message({
            message:'You have been signed out!',
            type:'success'});
          // alert("You've been signed out!");
          localStorage.removeItem('Authorization');
          this.$router.go(0)
          }).catch(error => {
            this.$message.error(error);
            
        });*/
    }

},
    created(){
        this.userInfo = localStorage.getItem("userInfo") !==null ?JSON.parse(localStorage.getItem("userInfo")):[]
        this.changeIsShow()
        this.changeIsShow1()
    },

    }
  

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
			.navigation_whole {
				/* width: content; */
            height: 6em;
				background-color:rgb(80,130,241)
        }

        .navigation {
				/* background-color: aqua; */
				height: 89px;
            line-height: 109px;
            float: right;
            margin-right: 2em;	

        }
        .nav1{
            margin-left: 10px;
            cursor:pointer;
            color: rgb(255, 255, 255);
            font-size:135%;
            font-family: "sans-serif";
            text-decoration: none;

            
        }

          .nav3{

				margin-left:3em;
        cursor:pointer;
        color: rgb(255, 255, 255);
				font-size:135%;
				font-family: "sans-serif";
				text-decoration: none;
      }
        .logo1 {
            width: 4em;
            height: 4em;
            line-height: 109px;
            position: relative;
            left: 4%;
				top: 0.8em;
				background-color:rgb(80,130,241)

        }

        .navigation a {
            font-size: 16 px;
        }


    @media screen and (min-width: 0px) and (max-width: 768px){
              .nav1{
            margin-left: 0px;
        }

          .nav3{

				margin-left:0em;
      }
        .nav1{
            margin-left: 0.5em;
            margin-right: 0em;
            cursor:pointer;
            color: rgb(255, 255, 255);
            font-size:100%;
            font-family: "sans-serif";
            text-decoration: none;

            
        }

          .nav3{

				margin-left:0.5em;
        cursor:pointer;
        color: rgb(255, 255, 255);
				font-size:100%;
				font-family: "sans-serif";
				text-decoration: none;
      }
    }
</style>
