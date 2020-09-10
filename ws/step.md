### react material  

### 组件
home 
login/register 

#### home 
  - header
  - maim
  - footer 


### 基本路由
 - 主页 ->帖子/我的->Login->register->主页

### api接口请求函数封装  
- 登录注册函数封装 完成登录注册功能 登录成功后跳转主页



### redux 数据管理 
- redux 管理essay 数据 
- 展示essay
- 发帖 
- 去掉mine中登录按钮
- 按id搜索帖子 


### 发表评论，回复评论 
- 向服务器发请求评论文章，然后重新请求整个文章内容，更新redux中的数据 或 向服务器发请评论文章，然后手动更新redux中的评论列表数据
- 发表评论，当前文章评论最后一项level 加1  
- 回复一级评论（回复楼主，增加此楼层数） 
- 回复二级评论 

### 登录之后才能进行发帖操作 


### 回复，评论  需要登录 
- 点击评论时如果未登录 跳转到login 并且通过路由参数传递当前e_id给login组件，登录成功后回到原帖 
- 回复（楼层）登录功能同理  


### 样式调整 
- topbar
- detail 
- publish 
- signin 
- signup  

### 搜索功能  
- 头部搜索框 请求 数据 ，修改redux 中allEssay列表数据  用户个人页不显示搜索框 


### 修改文章功能 



### 我的消息 
- 其他的用户回复我的 回复我的essay  floor  layer 
### 我的回复  
- 用户回复别人的 回复其他用户的 essay floor layer

### Mine 中我的帖子拆分成单独路由组件
- 通过路由传参确定是要显示我的帖子，然后从redux获取数据 


### Mine 三个按钮改成菜单 路由跳转 

### 与我相关 样式 锚点定位跳转到文章 


### 内网穿透 Invalid Host header 问题解决  
- 根目录创建 .env.development.local 文件 
- DANGEROUSLY_DISABLE_HOST_CHECK=true 
### 运行mvn spring-boot:run命令启动服务
 


### 布局文字溢出问题 