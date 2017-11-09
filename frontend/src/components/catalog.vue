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

  <!--侦听器-->
  <div>
    <p>
      Ask a yes/no question:
      <input v-model="question">
    </p>
    <p>{{ answer }}</p>
  </div>
  <!--通过v-bind:class，动态切换class-->
  <div v-bind:class="{ active : isActive , 'text-danger' : hasError }">class</div>
  <!--渲染效果和上面一样，这里绑定一个返回对象的计算属性，这个是常用且强大的模式-->
  <div v-bind:class="classObject">properties</div>
  <!--数组传给v-bind:class-->
  <div v-bind:class="[activeClass,errorClass]">arrays</div>
  <!--三元表达式-->
  <div v-bind:class="[isActive?activeClass:'',errorClass]">sanmu</div>
  <!--上边的写法将会始终添加errorClass，其实只有在isActive是truthy时才会添加activeClass-->
  <!--多条件class时，写法略繁琐，所以在数组语法中也可以使用对象语法-->
  <div v-bind:class="[{ active : isActive}, errorClass]">duixiang</div>

  <!--现在开始条件渲染ok/true  -->
  <h1 v-if="ok">Yes</h1>
  <h1 v-else>No</h1>

  <div v-if="Math.random() > 0.5">
    Now you see me
  </div>
  <div v-else>
    Now you don't
  </div>
  <!--vue2.1.0新增的v-else-if-->
  <div v-if="type === 'A'">
    A
  </div>
  <div v-if="type === 'B'">
    B
  </div>
  <div v-if="type === 'C'">
    C
  </div>
  <div v-else>
    Not A/B/C
  </div>

  <!--搞一次条件控制模块渲染-->
  <div>
    <div v-if="loginType === 'username'">
      <label>Username</label>
      <!--加入key会使输入框重新渲染-->
      <input placeholder="Enter your username" key="username-input">
    </div>
    <div v-else>
      <label>Email</label>
      <input placeholder="Enter your email address" key="email-input">
    </div>
    <button @click="changeButton">changeButton</button>
  </div>


  <!--v-show不支持template，也不支持v-else-->
  <!--v-show只是简单地切换元素css属性display，v-show的元素始终被渲染并保留在DOM中-->
  <!--v-if 是“真正”的条件渲染，因为它会确保在切换过程中条件块内的事件监听器和子组件适当地被销毁和重建。-->
  <!--v-if 也是惰性的：如果在初始渲染时条件为假，则什么也不做——直到条件第一次变为真时，才会开始渲染条件块。-->
  <!--相比之下，v-show 就简单得多——不管初始条件是什么，元素总是会被渲染，并且只是简单地基于 CSS 进行切换。-->
  <!--一般来说，v-if 有更高的切换开销，而 v-show 有更高的初始渲染开销。因此，如果需要非常频繁地切换，则使用 v-show 较好；如果在运行时条件很少改变，则使用 v-if 较好。-->
  <div v-show="ok">Hello</div>
  <!--v-if和v-for一起使用时，v-for比v-if更高的优先级-->


  <!--开始列表渲染-->
  <!--v-for 指令需要使用 item in items 形式的特殊语法，items 是源数据数组并且 item 是数组元素迭代的别名。-->
  <ul>
    <li v-for="item in items">
      {{ item.message }}
    </li>
  </ul>

  <ul>
    <!--迭代数组，of可以替换in -->
    <li v-for="( item ,index ) in items">
      {{ parentMessage }} - {{ index }} - {{ item.message }}
    </li>
  </ul>

  <!--一个对象的v-for-->
  <ul>
    <li v-for="value in object">
      {{ value }}
    </li>
  </ul>
  <!--第二个参数为键名-->
  <div v-for=" ( value , key ) in object">
    {{ key }}: {{ value}}
  </div>
  <!--第三个参数为索引-->
  <div v-for="(value,key,index) in object">
    {{ index}}.{{key}}:{{value}}
  </div>

  <!--数据更新检测-->
  <!--变异方法，会改变被这些方法调用的原始数组-->
  <!--push()、pop()、shift()、unshift()、splice()、sort()、reverse()-->

  <!--非变异方法，不会改变原始数组，但总是返回一个新数组，当使用非变异方法时，可以用新数组替换旧数组-->
  <!--filter(), concat() 和 slice()-->
  <!-- ------------------------由于 JavaScript 的限制，Vue 不能检测以下变动的数组：-----------------------
   当你利用索引直接设置一个项时，例如：vm.items[indexOfItem] = newValue
   当你修改数组的长度时，例如：vm.items.length = newLength
   为了解决第一类问题，以下两种方式都可以实现和 vm.items[indexOfItem] = newValue 相同的效果，同时也将触发状态更新：
   // Vue.set
   Vue.set(example1.items, indexOfItem, newValue)
   // Array.prototype.splice
   example1.items.splice(indexOfItem, 1, newValue)
   为了解决第二类问题，你可以使用 splice：
   example1.items.splice(newLength)-->


  <!--对象更改检测注意事项-->
  <!--还是由于 JavaScript 的限制，Vue 不能检测对象属性的添加或删除：-->
  <!--var vm = new Vue({-->
  <!--data: {-->
  <!--a: 1-->
  <!--}-->
  <!--})-->
  <!--// `vm.a` 现在是响应式的-->
  <!--vm.b = 2-->
  <!--// `vm.b` 不是响应式的-->
  <!--对于已经创建的实例，Vue 不能动态添加根级别的响应式属性。但是，可以使用 Vue.set(object, key, value) 方法向嵌套对象添加响应式属性。例如，对于：-->
  <!--var vm = new Vue({-->
  <!--data: {-->
  <!--userProfile: {-->
  <!--name: 'Anika'-->
  <!--}-->
  <!--}-->
  <!--})-->
  <!--你可以添加一个新的 age 属性到嵌套的 userProfile 对象：-->
  <!--Vue.set(vm.userProfile, 'age', 27)-->
  <!--你还可以使用 vm.$set 实例方法，它只是全局 Vue.set 的别名：-->
  <!--this.$set(this.userProfile, 'age', 27)-->
  <!--有时你可能需要为已有对象赋予多个新属性，比如使用 Object.assign() 或 _.extend()。在这种情况下，你应该用两个对象的属性创建一个新的对象。所以，如果你想添加新的响应式属性，不要像这样：-->
  <!--Object.assign(this.userProfile, {-->
  <!--age: 27,-->
  <!--favoriteColor: 'Vue Green'-->
  <!--})-->
  <!--你应该这样做：-->
  <!--this.userProfile = Object.assign({}, this.userProfile, {-->
  <!--age: 27,-->
  <!--favoriteColor: 'Vue Green'-->
  <!--})-->

  <!--显示过滤/排序结果,不改变或重置原始数据-->
  <li v-for="n in evenNumbers">{{n}}</li>

  <!--或者使用方法-->
  <li v-for="n in even(numbers)">{{n}}</li>

  <!--一段取值范围v-for-->
  <div>
    <span v-for="n in 10">{{n}}</span>
  </div>
<!--v-for优先级比v-if更高，所以v-if将分别重复运行于每个v-for循环中，当想为仅有的一些渲染节点时，这种优先级比较有用-->
  <!--在2.2.0+的版本中，当组件中使用v-for时，key现在是必须的-->
  <li v-for="todo in todos" v-if="!todo.isComplete" :key="todo.isComplete">
    {{todo}}
  </li>

  <!--事件处理-->
  <div>
    <button v-on:click="counter += 1">增加1</button>
    <p>这个按钮被点击了{{counter}}次</p>
  </div>

  <!--有的事件逻辑比较复杂，所以不会把js代码写在v-on指令是不行的-->
  <div>
    <button v-on:click="greet">Greet</button>
  </div>

  <!--内联处理器里的方法-->
  <div>
    <button v-on:click="say('hi')">Say Hi</button>
    <button v-on:click="say('what')">Say what</button>
  </div>

  <!--有时需要内联语句处理器中访问原生DOM事件，可以用特殊变量$event把它传入方法-->
  <button v-on:click="warn('Form cannot be submitted yet',$event)">Submit</button>

  <!--事件修饰符，methods 只有纯粹的数据逻辑，而不是去处理 DOM 事件细节-->
  <!--Vue.js 为 v-on 提供了事件修饰符。通过由点 (.) 表示的指令后缀来调用修饰符。-->
  <!--.stop 、.prevent 、  .capture  、   .self   、  .once-->
  <!-- 阻止单击事件冒泡 -->
  <!--<a v-on:click.stop="doThis"></a>-->
  <!-- 提交事件不再重载页面 -->
  <!--<form v-on:submit.prevent="onSubmit"></form>-->
  <!-- 修饰符可以串联 -->
  <!--<a v-on:click.stop.prevent="doThat"></a>-->
  <!-- 只有修饰符 -->
  <!--<form v-on:submit.prevent></form>-->
  <!-- 添加事件侦听器时使用事件捕获模式 -->
  <!--<div v-on:click.capture="doThis">...</div>-->
  <!-- 只当事件在该元素本身 (比如不是子元素) 触发时触发回调 2.1.4新增-->
  <!--<div v-on:click.self="doThat">...</div>-->
  </html>

</template>
<script>
  let _ = require("lodash");
  let axios = require('axios');
  export default {
    // el:'#app',
    data() {
      return {
        message: 'Hello Vue!',
        test: 'test11',
        isButtonDisabled: true,
        seen: true,
        todos: [
          {id:1,text: 'i am one'},
          {id:2,text: 'i am two'},
          {id:3,text: 'i am three'},
          {id:4,isComplete:true,text:'i am four'}
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
        last: 'l1',
        // doSomething: 'reverseMessage'
        question: '',
        answer: 'I cannot give you an answer until you ask a question!',
        isActive: true,
        error: null,
        hasError: false,
        activeClass: 'active',
        errorClass: 'text-danger',
        type: 'A',
        loginType: 'username',
        items: [
          {message: 'Foo'},
          {message: 'Jared'}
        ],
        parentMessage: 'Parent',
        object: {
          firstName: 'john',
          lastName: 'Doe',
          age: 30
        },
        numbers: [1, 2, 3, 4, 5],
        counter: 0,
        name: 'Vue.js'
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
      },
      // _.debounce是一个通过Lodash限制操作频率的函数(函数去抖，也就是说当调用动作n毫秒后，才会执行该动作，若在这n毫秒内又调用此动作则将重新计算执行时间)
      //在本demo中，这里限制了访问yesno.wtf/api的频率
      //AJAX请求直接到用户输入完毕才会发出
      //
      getAnswer: _.debounce(
        function () {
          alert("111")
          if (this.question.indexOf('?') === -1) {
            this.answer = 'Questions usually contain a question mark...'
            return
          }
          this.answer = 'Thinking...'
          var vm = this
          axios.get('https://yesno.wtf/api')
            .then(function (response) {
              vm.answer = _.capitalize(response.data.answer)
            })
            .cache(function (error) {
              vm.answer = 'Error!Could not reach the API.' + error
            })
        },
        500
      ),
      changeButton: function () {
        this.loginType = 'll'
      },
      even: function (numbers) {
        return numbers.filter(function (number) {
          return number % 2 ===0
        })
      },
      greet: function (event) {
        // 'this' 在方法里指当前Vue实例
        alert("Hello "+this.name+" !")
        // event是原生DOM事件
        if(event){
          alert(event.target.tagName);
        }
      },
      say: function (message) {
        alert(message);
      },
      warn: function (message,event) {
       //现在我们可以访问原生事件对象
        if(event){
          event.preventDefault()
        }
        alert(message)
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
          return this.fullNameS = this.first + " " + this.last
        },
        set: function (val) {
          var names = val.split(" ")
          this.first = names[0]
          this.last = names[names.length - 1]
        }

      },
      classObject: function () {
        return {
          active: this.isActive && !this.error,
          'text-danger': this.error && this.error.type === 'fatal'
        }
      },
      evenNumbers: function () {
        return this.numbers.filter(function (number) {
          return number % 2 === 0
        })
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
      },
      //如果'question'发生改变，这个函数就会运行
      question: function () {
        this.answer = 'Waiting for you to stop typing...'
        this.getAnswer()
      }

    }
  }

</script>
