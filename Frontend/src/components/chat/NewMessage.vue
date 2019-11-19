<template>
<div class="new-message">
    <form @submit.prevent="addMessage">
    <label for="new-message">New Message(Enter to Add)</label>
    <input type="text" name="new-message" v-model="newMessage">
    <p v-if="feedBack" class="red-text">{{feedBack}}</p> 
    <button class="btn">Enter</button>
    </form>
</div>
</template>

<script>
import db from '@/firebase/init'
export default {
    name:"NewMessage",
    props:['name'],
    data(){
        return{
            newMessage:null,
            feedBack:null
        }
    },
    methods:{
       addMessage(){
           if(this.newMessage){
            this.feedBack=null;
            db.collection('messages').add({
                content:this.newMessage,
                name:this.name,
                timestamp:Date.now()
            }).catch(err=>{
                console.log(err)
            })
            this.newMessage=null
           }else{
            this.feedBack="Please Enter message"
           }
       } 
    }
}
</script>
<style >

</style>