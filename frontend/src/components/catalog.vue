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
  <!--阻止元素发生默认行为，此时action将不能提交-->
  <form v-on:submit.prevent="onSubmit" action="http://www.baidu.com">.I am form..
    <button type="submit">submit</button>
  </form>

  <!--缩写方式-->
  <div>
    <a v-bind:href="url">.完整..</a>
    <a :href="url">.缩写..</a>

    <a v-on:click="doSomething">.完整..</a>
    <a @click="doSomething">.缩写..</a>
  </div>

  <!--计算属性-->
  <div>
    {{ message.split('').reverse().join('')}}
    <!--计算属性和方法pk-->
    <div>
      <p>Original message : "{{message}}"</p>
      <p>Compited reversed message: "{{reversedMessage}}"</p>
      <!--用着这种调用方法的方式也是可以执行的,区别：计算属性除非它们依赖的值变化了才会重新求值，否则会立刻返回结果，并不会再次执行函数，调用方法的话，每次都会执行函数-->
      <p>Compited reversed message: "{{reversed()}}"</p>
      <!--这里的缓存是指一次请求同一个页面出现多次标签，刚开始我的理解错误-->
      <p>当前时间:{{ now}}</p>
      <p>当前时间:{{ nowTime()}}</p>
      <p>当前时间:{{ now}}</p>
      <p>当前时间:{{ nowTime()}}</p>
    </div>
    <!--计算属性和watch进行pk-->
    <div>
      firstName:<input v-model="firstName">
      lastname:<input v-model="lastName">
      fullName: {{fullName}}-----假装有分隔符-----
      fullNameC:{{fullNameC}}
      <div>setter测试
        fullNameS:<input v-model="fullNameS">
        first:<input v-model="last">
        last:<input v-model="first">
      </div>
    </div>
  </div>
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
        url: 'http://www.baidu.com',
        firstName: 'jared',
        lastName: 'liu',
        fullName: 'Jared liu',
        first: 's1',
        last: 'l1'
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
      },
      onSubmit: function () {
        alert("我是提交之前执行的，哈哈哈");
      },
      reversed: function () {
        return this.message.split('').reverse().join('');
      },
      nowTime: function () {
        return Date.now();
      }
    },
    computed: {
      //计算属性的getter
      reversedMessage: function () {
        //this指向vm实例
        return this.message.split('').reverse().join('')
      },
      now: function () {
        return Date.now()
      },
      fullNameC: function () {
        return this.firstName + this.lastName
      },
      fullNameS: {
        //getter
        get: function () {
          return this.fullNameS=this.first+" "+this.last
        },
        set: function (val) {
          var names=val.split(" ")
          this.first=names[0]
          this.last=names[names.length-1]
        }

      }

    },
    watch: {
      'message': {
        handler: () => {
          alert("watch");
        }
      },
      'firstName': function (val) {
        this.fullName = val + this.lastName
      },
      'lastName': function (val) {
        this.fullName = this.firstName + val
      }
    }
  }

</script>
