# 分布式微服务跨境电商系统 - Spring Cloud & Alibaba
基于Spring Cloud框架的分布式跨境电商系统的设计与实现（毕设）

项目起止日期：2021.11.20 - 2022.05.20

**演示地址：**[https://mall.rawchen.com](https://mall.rawchen.com)



## 项目简介

本毕设项目是一套为解决大用户量且业务复杂的时代背景下，传统单体服务系统带来的诸多问题而设计与实现的基于Spring Cloud框架的分布式微服务跨境电商系统。

项目采用了前后端分离开发，实现了权限分组、JWT鉴权、手机验证码注册、支付宝沙箱支付、OSS存储资源、接口限流降级、导出订单与支付流水报表、订单邮件提醒、订单延时自动取消、Quick BI图表分析、关于锁的高并发抢购场景等。



## 技术栈

后端相关：
Spring Cloud+MyBatis-Plus+Elasticsearch+Redis+RabbitMQ+Nacos+Sentinel+Zipkin+Nginx+Docker+OSS+JWT

前端相关：
Vue+npm+Element UI+Axios+Thymeleaf

JDK：1.8
开发工具：IDEA_2021.2
数据库：MySQL_5.7
Redis：6.2.6
RabbitMQ：3.7.14
Nginx：1.10
Elasticsearch：7.4.2
Docker：20.10.12
Kibana：7.4.2


## 项目使用说明

1. 导入结构和数据：mall_admin.sql、mall_oms.sql、mall_pms.sql、mall_sms.sql、mall_ums.sql、mall_wms.sql

2. 解压下载项目zip，重命名mall-master文件夹为mall，IDEA打开该项目，前端部署renren-fast-vue

3. 安装虚拟机，配置私有网络，里面安装nginx，Elasticsearch，kibana。（redis和mysql嫌麻烦可安装本地），安装步骤往下拉可看到。

4. 配置本地hosts，可直接使用mall.com根域名。修改nginx配置。

5. 配置Nacos的配置中心（也是参考最后面）

6. 由于项目static里面存在大量静态图片资源因此github不保存这些，需自行下载放入。[https://cdn.rawchen.com/mall/static.zip](https://cdn.rawchen.com/mall/static.zip)

7. 最后检查所有的 *.yml，确保符合自己环境（如果端口冲突需要修改，如果中间件密码不对要改，如果各种xxx的秘钥需要自己去弄）
```



## 服务

![blog-01.png](https://cdn.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/01.png)



## 架构图

![blog-01.png](https://cdn.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/01.png)


## 功能实现部分截图

![blog-01.png](https://cdn.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/01.png)


## Stargazers

[![](https://reporoster.com/stars/rawchen/mall)](https://github.com/rawchen/mall/stargazers)

## Forkers

[![](https://reporoster.com/forks/rawchen/mall)](https://github.com/rawchen/mall/network/members)

## 待完成

- [ ] 分布式集群部署
- [ ] 云服务器上线
- [ ] 部分功能未实现

## 如何贡献
Fork 项目到你自己仓库，本地拉取你 fork 的项目并部署修改。
提交本地仓库更改，推送到你 fork 的项目仓库中。
在我的项目发起 Pull requests，我看到后将考虑合并到主分支。