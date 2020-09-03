# api说明
**_tips：所有请求地址前面需加主机名+端口号(8081或其他)，如：https://192.168.0.1:8081,由于主机名和端口号并不一定确定，故而在代码中需要特别注意，便于修改，使得一次修改就达目的_**
## 目录
<a href="#用户注册" title="用户注册">注册</a><br />
<a href="#用户登录" title="用户登录">登录</a><br />
<a href="#发帖" title="发帖">发帖</a><br />

## 用户相关API
### 用户注册
    地址：/user/register
    完整地址：主机名:端口号/user/register (以后不说明)
    需要上传的body参数(左边为参数名，右边为具体属性，其中姓名和密码不能为空)：
        {
            "name":"zs",
            "password":"123",
            "email":"356@qq.com",
            "homePage":"howay.site"
        }
    方式：Post
    返回内容：
        成功示例：{"RE_DESC":"SUCCESS","RE_CODE":0}
        失败示例：{"RE_DESC":"名字或密码为必填","RE_CODE":1001}
### 用户登录
    地址：/user/login
    上传参数：name,password  ———此处使用form-data格式
    方式：post
    返回内容：
        good：{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad：{"RE_DESC":"用户名或密码错误","RE_CODE":1002}

## 贴子相关API
### 发帖
    地址：/essay/writePost
    上传参数：
        {
            "title":"测试3",
            "content":"这是测试内容，this is a test essay",
            "publisher":2,   //这个是发布人，楼主id
            "label":"test"  //发表内容归类，标签
        }
    返回内容：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"用户不存在","RE_CODE":1002}
        //其中错误代码1002指用户名等参数错误或不存在