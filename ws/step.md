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