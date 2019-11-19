<template>
    <div class="welcome container">
        <div class="card">
            <div class="card-content center-align">
                <h2 class="teal-text">Welcome</h2>
                <form @submit.prevent="enterChart">
                    <label for="name">Enter your Name</label>
                    <input type="text" name="name" v-model="name">
                    <p v-if="feedBack" class="red-text">{{feedBack}}</p>
                    <button class="btn teal">Enter Chat</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name:'Welcome',
    data(){
        return{
            name:null,
            feedBack:null
        }
    },
    methods:{
        enterChart(){
            if(this.name){
            this.$router.push({name:'Chat',params:{name:this.name}})
            }else{
                this.feedBack='Please Enter Name';
            }
        }
    }
}
</script>

<style >
.welcome {
 max-width: 400px;
 margin-top: 100px;
}
.welcome h2{
    font-size: 3em;
}
.welcome button{
    margin: 30px auto;
}
</style>