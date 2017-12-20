# aits
vue2+adminlte

# 效果图查看
![效果图查看](https://gitee.com/uploads/images/2017/1102/170928_300656a3_872520.png "效果图查看")

backend后端启动需要redis，在aits/backend/src/main/resources/路径下有一个Redis-x64-3.0.504.rar的包，可以在windows下启动即可。
上边的redis已经改动为分布式redis
已经提供一个6节点redis
需要安装ruby，同目录下有一个rubyinstaller-2.4.2-2-x64.exe为ruby安装包
安装完以后
执行gem install redis
然后打开我的redis压缩包，点击start.bat

ruby redis-trib.rb create --replicas 1 127.0.0.1:6379 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004
