<template>
    <div class="createProduct">
        <modal :header="'Create Product'" :isShow="showModal" v-if="showModal" @close="showModal = false">
            <product-form :product="product" v-on:submit-form="productAction"/>
       </modal>
    </div>
</template>
<script>
import Modal from "../../shared/Modal.vue";
import ProductForm from "./ProductForm";
import http from "../../../http-common";
export default {
  name: "createProduct",
  components: { Modal, ProductForm },
  data() {
    return {
      product: new Object(),
      showModal: false
    };
  },
  methods: {
    showModalForm: function() {
      this.showModal = true;
    },

    productAction: function(product) {
      console.log("Creating new Product", product);
      var data = {
        productName: this.product.productName,
        productCategory: this.product.productCategory,
        productSeller:this.product.productSeller,
        isBestProduct: this.product.isBestProduct,
        isTopProduct: this.product.isTopProduct,
        productImage: this.product.productImage,
        productRating: this.product.productRating,
        productDescription:this.product.productDescription,
        productPrice:this.product.productPrice
      };
      http
        .post("/addProduct", data)
        .then(response => {
          this.customer.id = response.data.id;
          console.log(response.data);
          this.$router.push("/");  
        })
        .catch(e => {
          console.log(e);
        });
    this.$router.push("/");  
    }
  }
};
</script>
<style lang="scss">
.showModal {
    display: block;
    overflow-y: auto;
    background-color: rgba(0, 0, 0, 0.5);
}
</style>