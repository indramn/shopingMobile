<template>
    <div class="productForm">
        <form id="productCU" @submit="actionProduct" v-if="!loading">
                <p v-if="errors.length">
                  <b>Please correct the following error(s):</b>
                  <ul>
                    <li v-for="error in errors" :key="error">{{ error }}</li>
                  </ul>
                </p>

              <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" class="form-control" id="productName" 
                v-model="product.productName" name="productName" aria-describedby="emailHelp" placeholder="Enter Product Name">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
              </div>
              <div class="form-group">
                <label for="productPrice">Product Price</label>
                <input type="text" class="form-control" id="productPrice" 
                v-model="product.productPrice" name="productPrice" placeholder="Enter Product Price">
             </div>
             <div class="form-group">
                <label for="productDescription">Product Description</label>
                <input type="text" class="form-control" id="productDescription" 
                v-model="product.productDescription" name="productDescription" placeholder="Enter Product Description">
             </div>
              <div class="form-group">
                <label for="productCategory">Product Category</label>
                <select class="form-control"   id="productCategory" name="productCategory" 
                v-model="product.productCategory">
                    <option 
                      v-for="(category, index) in categories" :key="index" 
                      v-bind:value="category.productCategory" 
                      >{{category.productCategory}}
                    </option>
                </select>
                <!-- <input type="password" class="form-control" id="productCategroyDummy"  v-if="product.productCategory === 'Create New'" placeholder="Password" name="productCategroyDummy" v-model="product.productCategroyDummy"> -->
              </div>
              <div class="form-group">
                <label for="productSeller">Product Seller</label>
                <select class="form-control"   id="productSeller" name="productSeller" v-model="product.productSeller">
                    <option 
                      v-for="(seller, index) in sellers" :key="index" 
                      v-bind:value="seller.productSeller" 
                      >{{seller.productSeller}}
                    </option>
                </select>
                <!-- <input type="password" class="form-control" id="productSellerDummy" 
                 v-if="product.productSeller === 'Create New'" placeholder="Password" 
                 name="productSellerDummy" v-model="product.productSellerDummy"> -->
              </div>
              <div class="row">
                <div class="col">
                  <div class="form-group">
                    <label for="isBestProduct">Best Product</label>
                    <input type="checkbox" class="form-control" id="isBestProduct" name="isBestProduct" v-model="product.isBestProduct" placeholder="Password">
                  </div>
                </div>
                <div class="col">
                    <div class="form-group">
                      <label for="isTopProduct">Top Product</label>
                      <input type="checkbox" class="form-control" id="isTopProduct" name="isTopProduct" placeholder="Password" v-model="product.isTopProduct">
                    </div>
                </div>
                <div class="col">
                  <div class="form-group">
                    <label for="productRating">Product Rating</label>
                     <star-rating  v-model="product.productRating"></star-rating>
                   
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label for="productImage">Product Image</label>
                <input type="text" class="form-control" id="productImage" 
                v-model="product.productImage" placeholder="Product Image URL">
              </div>
              <br>
              <button type="submit" class="btn buttonGreen">Save changes</button>
        </form>

        <div class="lds-dual-ring" v-if="loading"></div>
    </div>
</template>
<script>
import http from "../../../http-common";
import StarRating from 'vue-star-rating'
import {errorToaster} from "../../shared/service/ErrorHandler.js"
export default {
  name: "productForm",
  props: ["product"],
  components:{StarRating },
  data() {
    return {
      errors: [],
      productCategroyDummy: "",
      productSellerDummy: "",
      categories: [],
      sellers: [],
      loading: false
    };
  },
  methods: {
    actionProduct(event) {
      event.preventDefault();
      this.$emit("submit-form", this.product);
    }
  },
  created() {
    this.loading = true;
    this.categories.push({ productCategory: "Create New" });
    this.categories.push({ productCategory: "Type 1" });
    this.categories.push({ productCategory: "Type 2" });
    this.sellers.push({ productSeller: "Create New" });
    this.sellers.push({ productSeller: "Type 1" });
    this.sellers.push({ productSeller: "Type 2" });
    this.loading = false;
  }
};
</script>
<style lang="scss">

.productForm {

  div {
    text-align: start;
    
  }
  #productCU {
    div {
      text-align: start;
    }
    button {
      text-align: center;
    }
  }
}

.lds-dual-ring {
  display: inline-block;
  width: 64px;
  height: 64px;
}
.lds-dual-ring:after {
  content: " ";
  display: block;
  width: 46px;
  height: 46px;
  margin: 1px;
  border-radius: 50%;
  border: 5px solid #fff;
  border-color: #41b883 transparent #41b883 transparent;
  animation: lds-dual-ring 1.2s linear infinite;
}
@keyframes lds-dual-ring {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>