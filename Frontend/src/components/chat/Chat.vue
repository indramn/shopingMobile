<template>
  <div class="chat container">
       <small style="float: right;margin-top: 14px;" class="text-muted footerIcons" v-if="loggedUser&&loggedUser.isAdmin">
              <a href="javascript:;;" class="p-2" v-on:click="removeAllMesgCart()">
                <i class="fa fa-trash"></i>
              </a>
            </small>
    <h2 class="center teal-text">{{this.name}} Chat Box</h2>
    <div class="card">
      <div class="card-content">
        <ul class="messages" vue-chat-scroll>
          <li v-for="mesg in messages" :key="mesg.id">
            <span class="teal-text">{{mesg.name}}</span>
            <span class="grey-text text-darken-3">{{mesg.content}}</span>
            <span class="grey-text time">{{mesg.timestamp}}</span>
            <small class="text-muted footerIcons" v-if="loggedUser&&loggedUser.isAdmin">
              <a href="javascript:;;" class="p-2" v-on:click="removeMesgCart(mesg)">
                <i class="fa fa-trash"></i>
              </a>
            </small>
          </li>
        </ul>
      </div>
      <div class="card-action">
        <NewMessage :name="name" />
      </div>
    </div>
  </div>
</template>
<script>
import NewMessage from "@/components/chat/NewMessage";
import db from "@/firebase/init";
import {
  isLoggedIn,
  getLoggedInUser,
  isAdmin
} from "@/components/shared/service/authService";
import moment from "moment";
export default {
  name: "Chat",
  props: ["name"],
  components: {
    NewMessage
  },
  data() {
    return {
      messages: [],
      loggedUser:''
    };
  },
  methods: {
    removeMesgCart(mesg) {
       db.collection('messages').doc(mesg.content).delete()
      .then(()=>{
              this.messages=this.messages.filter(message=>{
        return message.content!=mesg.content;
      })
      })
    }, 
    removeAllMesgCart() {
       const ref=db.collection('messages')
       ref.get().then(function(querySnapshot) {
  querySnapshot.forEach(function(doc) {
    doc.ref.delete();
  });
});
    this.messages=[];
    }
  },
  created() {
       this.loggedUser = getLoggedInUser();
    let ref = db.collection("messages").orderBy("timestamp");
    ref.onSnapshot(snapshot => {
      snapshot.docChanges().forEach(element => {
        if (element.type == "added") {
          let doc = element.doc;
          this.messages.push({
            id: doc.id,
            name: doc.data().name,
            content: doc.data().content,
            timestamp: moment(doc.data().timestamp).format("lll")
          });
        }
      });
    });
  }
};
</script>

<style >
.chat h2 {
  font-size: 2.6em;
  margin-bottom: 40px;
}
.chat span {
  font-size: 1.4em;
}
.chat .time {
  display: block;
  font-size: 0.8em;
}
.messages {
  max-height: 300px;
  overflow: auto;
}
.messages::-webkit-scrollbar {
  width: 3px;
}
.messages::-webkit-scrollbar-track {
  background: #dddddd;
}
.messages::-webkit-scrollbar-thumb {
  background: #aaaaaa;
}
</style>