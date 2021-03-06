# api说明
**_tips：所有请求地址前面需加主机名+端口号(8081或其他)，如：https://192.168.0.1:8081,由于主机名和端口号并不一定确定，故而在代码中需要特别注意，便于修改，使得一次修改就达目的_**
## 目录
<a href="#用户注册" title="用户注册">注册</a><br />
<a href="#用户登录" title="用户登录">登录</a><br />
<a href="#通过id查找用户信息" title="通过id查找用户信息">通过id查找用户信息</a><br />
<a href="#发帖" title="发帖">发帖</a><br />
<a href="#修改" title="修改">修改</a><br />
<a href="#查找所有贴子" title="查找所有贴子">查找所有贴子</a><br />
<a href="#通过用户id查找贴子" title="通过用户id查找贴子">通过用户id查找贴子</a><br />
<a href="#搜索" title="搜索">搜索</a><br />
<a href="#查找某个贴子内容一级全部一级评论和二级评论" title="查询单个贴子">查询单个贴子</a><br />
<a href="#发言" title="发言">写一级评论</a><br />
<a href="#回复" title="回复">写二级评论</a><br />
<a href="#删除贴子" title="删除贴子">删除贴子</a><br />
<a href="#发言删除" title="发言删除">删除一级评论</a><br />
<a href="#回复删除" title="回复删除">删除二级评论</a><br />
<a href="#我的消息" title="我的消息">我的消息</a><br />
<a href="#我的回复" title="我的回复">我的回复</a><br />

## 用户相关API
### 用户注册
    地址：/user/register
    完整地址：主机名:端口号/user/register (以后不说明)
    需要上传的body参数(左边为参数名，右边为具体属性，其中姓名和密码不能为空)：
        {
            "name":"zs",
            "password":"123",
            "email":"356@qq.com",
            "homePage":"howay.site",
            "avatar":null, //头像,无头像传null值，有头像上传头像url，字符串
            "info":"{}" //前端自定义json字段，自己定义自己解析
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
### 通过id查找用户信息
    地址：/user/info
    参数：{"u_id":1}
    返回：
        good:
            {
                "RE_DESC": "SUCCESS",
                "name": "zs",
                "homePage": "howay.site", //如果不存在这个字段则表示用户之前没有填写这个
                "RE_CODE": 0,
                "email": "356@qq.com" //如果不存在这个字段则表示用户之前没有填写这个
            }
        bad:{"RE_DESC":"用户不存在","RE_CODE":1002}
### 更新用户信息
    地址：/user/update
    参数：{
            "u_id":12, //必填
            "name":"zsk2gengxin",
            "password":"123",
            "email":"356@qq.com",
            "homePage":"howay.site",
            "avatar":"http://8089:00000.jpg",
            "info":"{"sex":0}",
            "privacy":"PRIVATE",
            "diary_privacy":"PRIVATE"
        }
    返回：{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"更新失败","RE_CODE":1004}
### 验证用户是否存在
    地址：/user/isExist
    参数：{
        "name":""
    }
    返回：{"RE_DESC":"用户不存在","RE_CODE":0}
        {"RE_DESC":"用户名存在","RE_CODE":1005}
    /**请注意，这里不存在好坏的返回，看你用在何处，在注册之前验证，只有用户名不存在才允许下一步的注册*/
### 忘记密码
### 已登录用户直接修改密码

## 贴子相关API
### 发帖
    地址：/essay/writePost
    上传参数：
        {
            "title":"测试3",
            "content":"这是测试内容，this is a test essay",
            "publisher":2,   //这个是发布人，楼主id
            "label":"test",  //发表内容归类，标签
            "type":"ESSAY",  /* 文章类型，分为：BLOG/ESSAY/DIARY 其中BLOG为新闻、资讯、技术等分享类型文章，由系统管理员直接发布，原则上不允许用户进行发布；ESSAY为默认类型，属于圈子，由用户发布并永久属于PULIC类型的文章；DIARY为动态类型，由用户发布，可以自定义隐私：PUBLIC(公开)/PRIVATE(私密)/ONLYFANS(仅关注之人) */
            "info":"{}" //前端自定义json字段，自己定义自己解析
        }
    返回内容：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"用户不存在","RE_CODE":1002}
        //其中错误代码1002指用户名等参数错误或不存在

### 删除贴子
    地址：/essay/delete
    说明：删除贴子以及全部回复
    方式：post
    参数：
        {
            "e_id":9,
            "opretor":2
        }
    返回内容：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"删除失败","RE_CODE":1003}
### 修改
    地址：/essay/update
    说明：仅修改标题和内容，更新时间自动变化（贴子排序按更新时间进行排序（最新或最早））
    方式：post
    参数：
        {
            "e_id":1,   //更新贴子id，
            "opretor":5  ///操作人id
            "content":"文章修改",  
            "title":"更新"
        }
    返回：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"更新失败","RE_CODE":1004} (e_id错误会更新失败)

### 查找所有贴子
    地址：/essay/all
    参数：{
        "type":"ESSAY" //只返回当前大类的文章
    }
    返回内容：
        {"RES":[{"creation_time":"2020-09-03 21:54:41","update_time":"2020-09-03 at 21:54:41 CST","publisher":5,"e_id":1,"label":"test","title":"测试","content":"这是测试内容，this is a test essay"},{"creation_time":"2020-09-03 21:56:49","update_time":"2020-09-03 21:56:49","publisher":2,"e_id":3,"label":"test","title":"测试3","content":"这是测试内容，this is a test essay"}],"RE_DESC":"SUCCESS","RE_CODE":0}
        其中RES为结果，即所有贴子的集合
### 通过用户id查找贴子
    地址：/essay/byId
    参数：{"id":1,"type":"ESSAY"} //新增ESSAY
    返回内容：
        {"RES":[{"creation_time":"2020-09-03 21:55:39","update_time":"2020-09-03 at 21:55:39 CST","publisher":1,"e_id":2,"label":"test","title":"测试2","content":"这是测试内容，this is a test essay"}],"RE_DESC":"SUCCESS","RE_CODE":0}
### 查找某个贴子内容以及全部一级评论和二级评论
    地址：/essay/find
    参数：{"e_id":1} //该贴子id
    返回内容：
    {
        "creation_time": "2020-09-03 21:54:41",
        "update_time": "2020-09-03 21:54:41",
        "RE_DESC": "SUCCESS",
        "publisher": 5,
        "e_id": 1,
        "label": "test",
        "title": "测试",
        "floor": [   //floor为该贴子下的一级评论
            {
                "creation_time": "2020-09-03 23:22:17",
                "update_time": "2020-09-03 23:22:17",
                "level": 1,
                "essay": 1,
                "publisher": 7,
                "f_id": 1,
                "content": "这是测试内容，this is a test essay",
                "layer": [ //layer为该层发言下的二级评论
                    {
                        "creation_time": "2020-09-04 10:37:05",
                        "update_time": "2020-09-04 10:37:05",
                        "level": 1,
                        "publisher": 1,
                        "responder": 7,
                        "l_id": 1,
                        "floor": 1,
                        "content": "这是测试内容，this is a test essay"
                    },
                    {
                        "creation_time": "2020-09-04 10:47:24",
                        "update_time": "2020-09-04 10:47:24",
                        "level": 2,
                        "publisher": 2,
                        "responder": 1,
                        "l_id": 2,
                        "floor": 1,
                        "content": "这是测试内容，this is a test essay"
                    }
                ]
            },
            {
                "creation_time": "2020-09-03 23:28:34",
                "update_time": "2020-09-03 23:28:34",
                "level": 2,
                "essay": 1,
                "publisher": 1,
                "f_id": 2,
                "content": "这是测试内容，this is a test essay",
                "layer": []
            }
        ],
        "RE_CODE": 0,
        "content": "这是测试内容，this is a test essay"
    }

    bad：{"RE_DESC":"贴子不存在","RE_CODE":1002}

### 搜索
    地址：/essay/search
    说明：关键字进行模糊搜索，包括标题和内容
    参数：{"key":"发布"}
    返回：
        {"RES":[{"creation_time":"2020-09-07 00:52:21","update_time":"2020-09-07 00:52:21","publisher_name":"zs","publisher":2,"e_id":10,"label":"IT","title":"微软","content":"微软发布surface duo"}],"RE_DESC":"SUCCESS","RE_CODE":0}

## 一级评论相关API
### 发言
    地址：/floor/write
    参数：
        {
            "essay":1,  //当前所在贴子id
            "content":"这是测试内容，this is a test essay",
            "publisher":7, //当前评论人id，如果是楼主，前端应显示
            "level":1, //当前所在楼层，为当前贴子最后一楼level加1
            "info":"{}" //前端自定义
        }
    返回：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"用户或者文章不存在","RE_CODE":1002}、
### 发言删除
    说明：删除该楼，同时删除该楼下面全部二级评论
    地址：/floor/delete
    参数：
    {
        "f_id":4,
        "opretor":2 //执行删除操作的用户id
    }
    返回：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"删除失败","RE_CODE":1003}

## 二级评论相关API
### 回复
    地址：/layer/write
    参数：
    {
        "floor":1,  //这是当前回复所在一级评论的id
        "content":"这是测试内容，this is a test essay",
        "publisher":1, //当前评论人id
        "responder":7, //被回复人id
        "level":1, //所在层数，为当前楼层最后一层加1
        "replied_lid":5, //回复指向的另外二级评论id，如果为-1，则表示直接回复这楼
        "info":"{}",//前端自定义
    }
    返回：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"用户或者回复不存在","RE_CODE":1002}

### 回复删除
    地址：/layer/delete
    说明：某个用户删除二级评论，同时删除该二级评论的所有回复
    参数：
    {
        "l_id":3,  //需要删除的二级评论id
        "opretor":6   //执行操作的用户id
    }
    返回：
        good:{"RE_DESC":"SUCCESS","RE_CODE":0}
        bad:{"RE_DESC":"删除失败","RE_CODE":1003}

## 其他API
### 我的消息
    地址：/relation/getMessages
    参数：{ "u_id":"2"}
    返回：
    {
        "RES": [
            {
                "original": {
                    "creation_time": "2020-09-08 21:08:06",
                    "publisher_name": "zs",
                    "level": 1,
                    "responder": 4,
                    "type": "LAYER",
                    "essayId": 17,
                    "content": "呵呵呵呵呵",
                    "update_time": "2020-09-08 21:08:06",
                    "replied_lid": -1,
                    "publisher": 2,
                    "responder_name": "cyf",
                    "l_id": 83,
                    "floor": 39
                },
                "reply": {
                    "creation_time": "2020-09-08 21:08:13",
                    "publisher_name": "zs",
                    "level": 2,
                    "responder": 2,
                    "type": "LAYER",
                    "essayId": 17,
                    "content": "哎呦，不错哦",
                    "update_time": "2020-09-08 21:08:13",
                    "replied_lid": 83,
                    "publisher": 2,
                    "responder_name": "zs",
                    "l_id": 84,
                    "floor": 39
                }
            }
        ],
        "RE_DESC": "SUCCESS",
        "RE_CODE": 0
    }
    其中，original是被回复的消息，原消息；
    reply是回复消息。
    由两条组成一组关系，上面显示回复，下面显示被回复，
    其中都有type，type有三个值{"ESSAY","FLOOR","LAYER"},
    需要关心消息的属性值有：内容、更新时间、用户昵称、具体定位
    （可以根据essayId定位文章，floor定位楼，layer定位层，当然type为FLOOR的只能定位到floor,ESSAY只能定位到essay...）
### 我的回复
    地址：/relation/getReplies
    参数：{ "u_id":"2"}
    返回内容与我的消息类似，相关要求也一样