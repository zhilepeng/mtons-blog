# Mblog 部署手册

标签（空格分隔）： 未分类

---

Mblog 部署手册

1. 准备工作
   - 安装 JDK8
   - 安装图片处理工具：GraphicsMagick1.3.20，[下载地址][1]
   - 安装 Maven
   - 准备 IDE (如果你不看源码，可以忽略下面的步骤，直接通过Maven编译war包)

2. IDE 需要配置的东西
   - 编码方式设为UTF-8
   - 配置Maven
   - 设置JDK8
   
   > 如果没有使用过maven工具, 请自行百度学习; 以上配置在不同的IDE有不同的配置方式, 不介意的话, 您也顺便百度了吧.
   
3. 获取代码导入到IDE
   - 从 http://git.oschina.net/mtons/mblog 拉取最新代码
   - 导入到IDE的时候请选择Maven导入方式
   - [系统配置手册][2]
   
4. 配置完毕
   - 后台管理的地址是 /admin, 如果你是管理员账号点导航栏的头像会看到"后台管理"

5. 常见问题总结
   - java.io.FileNotFoundException: gm 应该是你 GraphicsMagick 安装不成功或者重启下电脑再试试
   - 进入系统后, 菜单加载不出来, 那应该是你没有导 db_init.sql
   - 点标签显示乱码, 那应该是你的应用服务器 URIEncoding 没设


  [1]: http://www.graphicsmagick.org/download.html
  [2]: https://www.zybuluo.com/langhsu/note/165905