<template>
  <html>
  <div>
    {{message}}
  </div>
  <div>
    <!--v-bind属性被称为指令，指令带有前缀v-，以表示它们是Vue提供的特殊属性，会再渲染的DOM上应用特殊的响应试行为，此处含义将元素的DOM上的title属性和message保持一致-->
    <span v-bind:title="message">
      鼠标悬停几秒钟查看此动态绑定的提示信息！
    </span>
  </div>
  <div>
    <!--切换一个元素的显示，属性在data的seen，通过true和false控制-->
    <p v-if="seen">现在你看到我了</p>
  </div>
  <div>
    <ol>
      <!--v-for指令可以绑定数组的数据来渲染一个项目列表-->
      <li v-for="todo in todos">
        {{todo.text}}
      </li>
    </ol>
  </div>
  <div>
    <p>{{message}}</p>
    <!--v-on指令绑定一个事件监听器-->
    <button v-on:click="reverseMessage">逆转消息</button>
  </div>
  <div>
    <p>{{message}}</p>
    <!--v-model指令实现表单输入和应用状态之间双向绑定-->
    <input v-model="message">
  </div>
  </html>
</template>
<script>

  export default {
    // el:'#app',
    data() {
      return {
        message: 'Hello Vue!',
        seen: true,
        todos: [
          { text: 'i am one'},
          { text: 'i am two'},
          { text: 'i am three'}
        ]
      }
    },
    methods: {
      reverseMessage: function () {
        this.message=this.message.split('').reverse().join('')
      }
    },
    watch: {
        'message':  {
            handler:()=>{
              alert("hello1");
            }
        }
    }
  }

</script>
