##脚手架目录结构说明
###  frontend
      build
      config
         -dev.env.js
         -index.js                这个是配置文件
         -prod.env.js
         -test.env.js
      node_modules
      src                         这个是我们用来写各种代码的地方
        assets                    用来存图片
            logo.png
        components                用来存组件的
            Hello.vue
        router                    用来配置路由的
            index.js
        App.vue
        main.js                   页面
      static
        .gitkeep
      test
        .babelrc
        .editorconfig
        .eslintignore
        .eslintrc.js
        .gitignore
        .postcssrc.js
        .index.html
        .package.json
        README.md  



# frontend

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
