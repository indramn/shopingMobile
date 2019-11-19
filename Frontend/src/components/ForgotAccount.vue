<template>
  <div class="forgot-account">
    <form class="form-signup" @submit.prevent="forgotAccount">
      <img class="mb-4" src="../assets/create-account.svg" alt width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Update password</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-for="(error, index) in errorMessage"
        :key="index"
      >{{error}}</div>

      <div class="row">
      </div>
      <div class="row">
        <div class="col-md mb-3">
          <input
            type="text"
            class="form-control"
            v-model="user.email"
            id="emailId"
            placeholder="Email address"
            value
            required
          >
          <div class="invalid-feedback">Valid Email Id is required.</div>
        </div>
      </div>
      <div class="row">
        <div class="col-md mb-3">
          <input
            type="password"
            class="form-control"
            id="act-password"
            placeholder="New password"
            value
            v-model="user.password"
            required
          >
          <div class="invalid-feedback">Valid Password is required.</div>
        </div>
      </div>
      <div class="row">
        <div class="col-md mb-3">
          <input
            type="password"
            class="form-control"
            id="confirm-password"
            placeholder="confirm password"
            value
            v-model="confirmPassword"
            required
          >
          <div class="invalid-feedback">Valid Confirm Password is required.</div>
        </div>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        <i class="fa fa-spinner fa-spin mr-1" v-if="showLoader"></i>Update Password
      </button>
    </form>
  </div>
</template>
<script>
import http from "../http-common";
import { successToaster, errorToaster } from "./shared/service/ErrorHandler.js";
export default {
  name: "ForgotAccount",
  data() {
    return {
      showLoader: false,
      user: {
        email: "",
        password: ""
      },
       confirmPassword:"",
      errorMessage: []
    };
  },
  methods: {
    forgotAccount(e) {
      this.showLoader = true;
      this.errorMessage = [];
      
      if (this.ValidateEmail(this.user.email) === false) {
        this.errorMessage.push("Please provide a valid Email address");
      }
      if (this.user.password!=this.confirmPassword) {
        this.errorMessage.push(
          "password is Mismatch"
        );
      }
      if (this.errorMessage.length === 0) {
        console.log('user Object',this.user)
        http
          .post("/updateUser", this.user)
          .then(response => {
            if(response&&response.data){
            this.showLoader = false;
            successToaster(
              "Registered Successfully",
              "User Registered Successfully"
            );
            this.$router.push({name:'login'});
            }else{
               this.errorMessage.push(
          "invalid User"
        );
            }
          })
          .catch(error => {
            console.log(error);
            errorToaster(
              "Registeration Failed",
              "Please try again after sometime"
            );
          });
      }
      this.showLoader = false;
    },

    ValidateEmail(mail) {
      if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        return true;
      }
      return false;
    }
  }
};
</script>

<style>
.form-signup {
  width: 100%;
  max-width: 500px;
  padding: 15px;
  margin: auto;
}
</style>
