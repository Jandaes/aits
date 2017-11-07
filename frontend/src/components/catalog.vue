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
    <p id="test">{{message}}</p>
    <!--v-on指令绑定一个事件监听器-->
    <button v-on:click="reverseMessage">逆转消息</button>
  </div>
  <div>
    <p>{{message}}</p>
    <!--v-model指令实现表单输入和应用状态之间双向绑定-->
    <input v-model="message">
  </div>
  <div>
    <!--v-once指令，只执行一次性地插值，当数据改变时，插值处的呢绒不会更新，！会影响到该节点上所有的数据绑定-->
    <span v-once>Message:{{message}}</span>
  </div>
  <!--输出内容为真正的html标签，而不是普通文本数据-->
  <div v-html="replacehaha">
  </div>
  <!--Mustache 语法不能作用在 HTML 特性上，遇到这种情况应该使用 v-bind 指令-->
  <div v-bind:id="test">222</div>
  <!--此处按钮被禁用掉了-->
  <button v-bind:disabled="isButtonDisabled">Button</button>

  <!--以下为各种Mustache语法测试,这里边所有的绑定都只能包含单个表达式，不能写javascrip语句-->
  <div>
    {{ number + 1}}
    {{ ok ? 'YES' : 'NO'}}
    {{ message.split('').reverse().join('') }}
    <div v-bind:id="'list-'+id">3</div>
  </div>
  <!--v-bind可以响应试更新html属性-->
  <a v-bind:href="url">请看我的url跳转</a>
  <!--v-on命令用于监听DOM事件-->
  <a v-on:click="doSomething">监听点击事件</a>
  </html>
</template>
<script>

  export default {
    // el:'#app',
    data() {
      return {
        message: 'Hello Vue!',
        test: 'test11',
        isButtonDisabled: true,
        seen: true,
        todos: [
          {text: 'i am one'},
          {text: 'i am two'},
          {text: 'i am three'}
        ],
        replacehaha: '<div>hello，我是真正的html标签</div>',
        number: 1,
        ok: 'YES',
        id: 22,
        url: 'http://www.baidu.com'
       // doSomething: 'reverseMessage'
      }
    },
    //也有一些其他的钩子，在实例生命周期中不同场景下调用，如mounted、updated、destroyed，钩子的this指向调用它的Vue实例
    //不要在选项属性或回调上使用箭头函数，因为箭头函数和父级上下文绑定在一起，this不会拿到当前Vue实例
    created: function () {
      //alert("this is created");
    },
    methods: {
      reverseMessage: function () {
        this.message = this.message.split('').reverse().join('')
      },
      doSomething: function () {
        alert("我是触发出来的事件");
      }
    },
    watch: {
      'message': {
        handler: () => {
          alert("watch");
        }
      }
    }
  }

</script>
