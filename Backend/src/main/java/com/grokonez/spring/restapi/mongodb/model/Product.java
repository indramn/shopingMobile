package com.grokonez.spring.restapi.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

	@Id
	private String productId;
	private String productName, productCategory, productSeller, productImage, productRating, productPrice,
			productDescription;
	private boolean isBestProduct, isTopProduct;

	public Product(String productId, String productName, String productCategory, String productSeller,
			String productImage, String productRating, String productPrice, String productDescription,
			boolean isBestProduct, boolean isTopProduct) {
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productSeller = productSeller;
		this.productImage = productImage;
		this.productRating = productRating;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.isBestProduct = isBestProduct;
		this.isTopProduct = isTopProduct;
	}

	public Product() {

	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSeller() {
		return productSeller;
	}

	public void setProductSeller(String productSeller) {
		this.productSeller = productSeller;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductRating() {
		return productRating;
	}

	public void setProductRating(String productRating) {
		this.productRating = productRating;
	}

	public boolean isBestProduct() {
		return isBestProduct;
	}

	public void setBestProduct(boolean isBestProduct) {
		this.isBestProduct = isBestProduct;
	}

	public boolean isTopProduct() {
		return isTopProduct;
	}

	public void setTopProduct(boolean isTopProduct) {
		this.isTopProduct = isTopProduct;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productCategory="
				+ productCategory + ", productSeller=" + productSeller + ", productImage=" + productImage
				+ ", productRating=" + productRating + ", isBestProduct=" + isBestProduct + ", isTopProduct="
				+ isTopProduct + "]";
	}

}
