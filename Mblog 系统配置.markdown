# Mblog 系统配置

标签（空格分隔）： 未分类

---
Mblog 系统配置

1. Mysql 数据库配置
```
# 文件位置： src/main/resources/init.properties
jdbc.url=jdbc:mysql://localhost:3306/db_mblog?autoReconnect=true&useUnicode=true&characterEncoding=utf-8
jdbc.username={username}
jdbc.password={password}

# 初始数据库文件位置(sql/db_init.sql), 默认用户: admin / 12345
```

2. 索引文件存放目录
```
# 文件位置：src/main/resources/init.properties

# 配置 lucene 索引文件存放目录
hibernate.search.indexs=d:/data/indexs
```

3. 图片工具(GraphicsMagick)安装路径配置
```
# 文件位置：src/main/resources/mtons.properties
# graphicsmagick for windows
gmagick.home=C:/Program Files/GraphicsMagick-1.3.20-Q8
```

[Graphicsmagick 下载](http://www.graphicsmagick.org/download.html)

4. 邮件服务器
```
mail.host = smtp.mtons.com (smtp服务地址)
mail.auth = true
mail.timeout = 25000
mail.username = admin@mtons.com(你的账号)
mail.password = 你的密码
```

5. 关于Tomcat部署
```
# 请在Connector设置url编码, 否则标签页会出现乱码
<Connector port="8080" protocol="HTTP/1.1" ... URIEncoding="UTF-8"/>
```





