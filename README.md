## MBLOG

> mblog (mtons blog) 是一个简单的内容分享社区, 希望为用户提供一个纯粹、高质的交流平台. 

### 技术选型：

* **服务端**
* JDK8
* SSH (Spring、SpringMVC、Hibernate）
* 安全权限 Shiro
* 搜索工具 Lucene
* 缓存 Ehcache
* 视图模板 Velocity
* 其它 Jsoup、fastjson、GraphicsMagick

* **前端**
* jQuery、Seajs
* Bootstrap 前端框架
* UEditor/Markdown编辑器
* font-wesome 字体/图标

[部署手册](https://www.zybuluo.com/langhsu/note/165902)

### 系统配置

Mysql 数据库配置
> 配置文件： src/main/resources/init.properties


```
jdbc.url=jdbc:mysql://localhost:3306/db_mblog?autoReconnect=true&useUnicode=true&characterEncoding=utf-8
jdbc.username={username}
jdbc.password={password}

# 初始数据库文件位置(sql/db_init.sql), 默认用户: admin / 12345
```


索引文件存放目录

> 配置文件：src/main/resources/init.properties

```
# 配置 lucene 索引文件存放目录
hibernate.search.indexs=d:/data/indexs
```


图片工具(GraphicsMagick)安装路径配置
> 配置文件：src/main/resources/mtons.properties

```
# graphicsmagick for windows
gmagick.home=C:/Program Files/GraphicsMagick-1.3.20-Q8
```

[Graphicsmagick 下载](http://www.graphicsmagick.org/download.html)

邮件服务器
```
mail.host = smtp.mtons.com (smtp服务地址)
mail.auth = true
mail.timeout = 25000
mail.username = admin@mtons.com(你的账号)
mail.password = 你的密码
```

关于Tomcat部署
> 请在Connector设置url编码, 否则标签页会出现乱码

```
<Connector port="8080" protocol="HTTP/1.1" ... URIEncoding="UTF-8"/>
```


### 开源协议

如果您的网站使用了 Mblog, 请在网站页面页脚处保留 Mtons相关版权信息链接

QQ交流群：378433412