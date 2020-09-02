# api说明
### tips：所有请求地址前面需加主机名，如：https://192.168.0.1
## 目录
<a href="#用户注册" title="标题">注册</a><br />
<a href="#用户登录" title="标题">登录</a><br />


## 用户相关API
### 用户注册
    地址：/user/register
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

## 贴子相关API