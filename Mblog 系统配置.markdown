# Mblog 系统配置

标签（空格分隔）： 未分类

---
##Mblog 系统配置

### 1. Mysql 数据库配置
文件位置： mblog-web/src/main/resources/init.properties
```
jdbc.url=jdbc:mysql://localhost:3306/db_mblog?autoReconnect=true&useUnicode=true&characterEncoding=utf-8
jdbc.username={username}
jdbc.password={password}
```
创建完数据库后，需要导入初始数据，初始数据库文件位置(mblog-web/sql/db_init.sql), 默认管理员用户: admin，密码：12345

### 2. 索引文件存放目录
文件位置：mblog-web/src/main/resources/mtons.properties
```
# 配置 lucene 索引文件存放目录
hibernate.search.indexs=d:/data/indexs
```

### 3. 图片工具(GraphicsMagick)安装路径配置
文件位置：src/main/resources/mtons.properties
```
gmagick.home=C:/Program Files/GraphicsMagick-1.3.20-Q8
```
> 这个配置只有`windows`需要配置，其他系统不需要配置, 如果你的目录地址中有空格, 最好手动替换下空格

> [Graphicsmagick 下载](http://www.graphicsmagick.org/download.html)

### 4. 邮件服务器
```
mail.host = smtp.mtons.com (smtp服务地址)
mail.auth = true
mail.timeout = 25000
mail.username = admin@mtons.com(你的账号)
mail.password = 你的密码
```
> 此处配置只要是用来发送邮箱确认邮件

### 5. 第三方登录
QQ互联配置文件：mblog-web/src/main/resources/qqconnectconfig.properties
微博配置文件：mblog-web/src/main/resources/config.properties
```
#QQ
app_ID = 101235848
app_KEY = 6e6ecd79faed5679403894c66bd413d3
redirect_URI = http://mtons.com/oauth/callback/qq.do

#weibo
client_ID = 3554307689
client_SERCRET = 005c1e81cc05eb4e319a72e908bd278d
redirect_URI = http://mtons.com/oauth/callback/weibo.do

# 其中 redirect_URI 改成你对应的域名即可
# 注意：第三方登录只能在正式环境下调试, 即第三方能通过域名回调进的系统
```
除了以上配置, 你还需要在后台的`系统配置`中修改第三方回调地址, 修改`client_id`和`redirect_uri`中的域名即可

### 6. 关于Tomcat部署
请在Connector设置url编码, 否则标签页会出现乱码
```
<Connector port="8080" protocol="HTTP/1.1" ... URIEncoding="UTF-8"/>
```





