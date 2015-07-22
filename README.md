## Mblog

> Mblog 是一个简单的内容分享社区, 希望为用户提供一个纯粹、高质的交流平台.


### 使用的框架：

* Bootstrap 3
* Spring mvc
* Velocity
* Hibernate
* Lucene


### Mysql 数据库配置
> 配置文件： src/main/resources/init.properties


```
jdbc.url=jdbc:mysql://localhost:3306/db_mblog?autoReconnect=true&useUnicode=true&characterEncoding=utf-8
jdbc.username={username}
jdbc.password={password}

# 初始数据库文件位置(sql/db_init.sql), 默认用户: admin / 12345
```


### 索引文件存放目录

> 配置文件：src/main/resources/init.properties

```
# 配置 lucene 索引文件存放目录
hibernate.search.indexs=d:/data/indexs
```


### 图片工具(GraphicsMagick)安装路径配置
> 配置文件：src/main/resources/mtons.properties

```
# graphicsmagick for windows
gmagick.home=C:/Program Files/GraphicsMagick-1.3.20-Q8
```

[Graphicsmagick 下载](http://www.graphicsmagick.org/download.html)


### 关于Tomcat部署
> 请再Connector设置url编码 
> <Connector port="8080" protocol="HTTP/1.1" ... URIEncoding="UTF-8"/>


### 开源协议

本系统遵循 Apache Licene 2.0 协议, 如果您的网站使用了 Mblog, 请在网站页面页脚处保留 Mblog 相关版权信息链接


QQ交流群：378433412