import firebase from 'firebase'
import firestore from 'firebase/firestore'

var firebaseConfig = {
    apiKey: "AIzaSyBwMg3NQZNCqUsseFQGw_D8qqgu0ZNHvzA",
    authDomain: "indra-chat.firebaseapp.com",
    databaseURL: "https://indra-chat.firebaseio.com",
    projectId: "indra-chat",
    storageBucket: "",
    messagingSenderId: "874465203090",
    appId: "1:874465203090:web:0755780a166c122df1a74b",
    measurementId: "G-XM3TF5V464"
  };
  // Initialize Firebase
   const firebaseApp =firebase.initializeApp(firebaseConfig);

   export default firebaseApp.firestore();