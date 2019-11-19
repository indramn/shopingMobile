<template>
  <div class="login">
    <div class="row">
      <div class="col">
        <createAccount v-if="isSignUp"></createAccount>
        <form class="form-signin" @submit.prevent="login" v-if="!isSignUp">
          <img class="mb-4" src="../assets/login-img.png" alt width="72" height="72">
          <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
          <label for="email" class="sr-only">Email address</label>
          <input
            type="email"
            id="email"
            class="form-control"
            v-model="email"
            placeholder="Email address"
            required
            autocomplete="email"
          >
          <label for="password" class="sr-only">Password</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="form-control"
            placeholder="Password"
            required
            autocomplete="current-password"
          >
          <p class="text-muted">
            <a href="javascript:;;" @click="toggleForget">Forget password ?</a>
          </p>
          <span v-if="feedBack" class="red-text center">{{feedBack}}</span>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            <i class="fa fa-spinner fa-spin mr-1" v-if="showLoader"></i> Log In
          </button>
        </form>
        <p class="mt-3 text-muted">
          <span v-if="isSignUp">
            Already a member?
            <a href="javascript:;;" @click="toggleForm">Sign in</a>
          </span>
          <span v-if="!isSignUp">
            New to This App?
            <a href="javascript:;;" @click="toggleForm">Create an Account</a>
          </span>
        </p>
      </div>
    </div>
  </div>
</template>
<script>
import CreateAccount from "@/components/CreateAccount";
import ForgotAccount from "@/components/ForgotAccount";
import { encryptUser } from "../components/shared/service/authService";
import http from "../http-common";
import {
  successToaster,
  errorToaster
} from "../components/shared/service/ErrorHandler.js";

import { mapState, mapActions, mapMutations } from "vuex";

export default {
  name: "login",
  components: { CreateAccount,ForgotAccount },
  data() {
    return {
      email: "",
      password: "",
      showLoader: false,
      isSignUp: false,
      feedBack:null
    };
  },
  methods: {
    toggleForm() {
      this.isSignUp = !this.isSignUp;
    },
    toggleForget() {
      this.$router.push({name:'ForgotAccount'})
    },

    ...mapMutations(["ADD_LOGGED_USER"]),

    //  Login function usign email and password
    login(event) {
      this.showLoader = true;
      const user = {
        email: this.email,
        password: this.password
      };

      http
        .post("/login", user)
        .then(response => {
          if(response&&response.data[0]){
          console.log('response ind',response.data[0])
          this.showLoader = false;
          this.ADD_LOGGED_USER(response.data[0]);
          event.target.reset();
          this.feedBack=null;
          this.$router.push(this.$route.query.from || "/");
          }else{
            this.showLoader = false;
            this.feedBack="invalid Credentials"
          }
        })
        .catch(error => {
          this.showLoader = false;
           this.feedBack="invalid Credentials"
          errorToaster("Invalid Credentials", "");
          console.log(error);
        });
    }
  }
};
</script>
<style>
.login {
  position: relative;
  top: 50px;
  margin-bottom: 15%;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
