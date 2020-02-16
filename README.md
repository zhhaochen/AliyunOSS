本项目属于自己练手小项目，灵感来自于平时在写博客时需要将一些图片上传到阿里云服务器上，一般都是用PicGo，但为了练手最近学到的技能，基于spring boot2.2和阿里云OSS的官方SDK实现了一个用于上传图片的工具。

目前实现了拖拽和点击上传，可以单图也可以多图，但管理界面还没有写好，需要下一步改进。

另外相关的用于阿里云OSS鉴权的参数并没有上传，需要新建application.properties文件自行添加如下：
```java
#节点以北京节点为例
aliyunoss.endpoint=http://oss-cn-beijing.aliyuncs.com
#建议使用RAM账户，阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号
aliyunoss.accessKeyId=
aliyunoss.accessKeySecret=
#用于存放的bucket名
aliyunoss.bucketname=
```