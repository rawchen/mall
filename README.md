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

## 技术要点

Nginx代理、Sentinel流控、支付宝沙箱、Elasticsearch检索引擎、Sleuth链路追踪、Redis分布式锁、Seata分布式事务、手机验证码、邮件订单生成提醒、RabbitMQ订单仓储消息队列、阿里OSS存储、Excel导出、Quick BI看板

## 项目使用说明

1. 下载解压项目zip，重命名mall-main文件夹为mall，IDEA打开该项目，前端部署renren-fast-vue

2. 导入sql数据：mall_admin.sql、mall_oms.sql、mall_pms.sql、mall_sms.sql、mall_ums.sql、mall_wms.sql

3. 安装虚拟机，配置私有网络，里面安装nginx，Elasticsearch，kibana。（redis/mysql/RabbitMQ嫌麻烦可安装本地），安装步骤往下拉可看到。

4. 配置本地hosts，可直接使用mall.com根域名。修改nginx配置（参考页面下）。

5. 配置Nacos的配置中心（也是参考最后面）

6. 由于项目static里面存在大量静态图片资源因此github不保存这些，需自行下载放入。[https://cdn.rawchen.com/mall/static.zip](https://cdn.rawchen.com/mall/static.zip)

7. 最后检查所有的 *.yml，确保符合自己环境（如果端口冲突需要修改，如果中间件密码不对要改，如果各种xxx的秘钥需要自己去弄）

## 服务

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/05.png)

http://localhost:8848/nacos				Nacos后台
http://localhost:15672					RabbitMQ后台
http://localhost:8082					Sentinel后台
http://192.168.56.10:5601				Kibana后台

## 架构图

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/06.png)

## 重要类类图

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/07.png)

## 功能实现部分截图

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/01.png)

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/02.png)

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/03.png)

![](https://fastly.jsdelivr.net/gh/rawchen/JsDelivr/static/mall/04.png)

## 虚拟机配置
```bash
安装docker
https://docs.docker.com/engine/install/centos/
镜像加速器
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

yum install wget
yum install unzip

开启root
sudo vi /etc/ssh/sshd_config
PermitRootLogin yes
service sshd restart

使用nano编辑器
yum -y install nano

==========================
安装ElasticSearch7.4.2
docker pull elasticsearch:7.4.2
docker pull kibana:7.4.2
mkdir -p /mydata/elasticsearch/config
mkdir -p /mydata/elasticsearch/data
echo "http.host: 0.0.0.0">>/mydata/elasticsearch/config/elasticsearch.yml
chmod -R 777 /mydata/elasticsearch/

docker run --name elasticsearch --restart=always -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms128m -Xmx256m" \
-v /mydata/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data \
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.4.2

docker run --name kibana --restart=always -e ELASTICSEARCH_HOSTS=http://192.168.56.10:9200 -p 5601:5601 \
-d kibana:7.4.2

docker exec -it es容器id /bin/bash
wget https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.4.2/elasticsearch-analysis-ik-7.4.2.zip
unzip elasticsearch-analysis-ik-7.4.2.zip -d ik
chmod -R 777 ik/
最后删掉zip包

==========================================
安装nginx1.10
docker run -p 80:80 --name nginx -d nginx:1.10

docker container cp nginx:/etc/nginx .

docker run -p 80:80 --restart=always --name nginx \
-v /mydata/nginx/html:/usr/share/nginx/html \
-v /mydata/nginx/logs:/var/log/nginx \
-v /mydata/nginx/conf:/etc/nginx \
-d nginx:1.10

==============================
docker 命令
docker pull elasticsearch:7.4.2		拉取镜像
docker images					查看已经拉取的镜像
docker run -d -p 81:80 nginx		启动镜像（新建一个容器）
docker ps -a						查看正在运行中的容器,包括未运行的
docker start 容器名称/容器ID		启动该容器
docker stop nameOrContainerId		根据容器名或容器id停止
docker rm容器ID					删除某一容器 //停止容器--》删除容器--》删除镜像
docker rmi 镜像ID					删除某一个镜像
systemctl restart docke				重启docker
===============================
```

## Nginx配置
nginx.conf
```nginx
http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    #gzip  on;
    upstream mall{
        server 192.168.56.1:88;
    }

    include /etc/nginx/conf.d/*.conf;
}

```

conf.d/mall.conf
```nginx
server {
    listen       80;
    server_name  *.mall.com mall.com;

    location / {
        proxy_set_header Host $host;
        proxy_pass http://mall;
	    #proxy_pass http://192.168.56.1:10000;

        #root   /usr/share/nginx/html;
        #index  index.html index.htm;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }
}

```

## Nacos配置中心
oss-sms.yml
```yaml
spring:
  cloud:
    alicloud:
      access-key: LTAI4Fdzxxxxxxxxxxxxxxx
      secret-key: 4WedSXJ6630pxUxxxxxxxxxxxxxxxx
      oss:
        endpoint: oss-cn-hangzhou.aliyuncs.com
        bucket: rawchen
      sms:
        host: https://dfsns.market.alicloudapi.com
        path: /data/send_sms
        template-id: TPL_09xxx
        app-code: 46xxxxxxxxxxxxxxxxxxxxx
```

datasource.yml
```yaml
spring:
  datasource:
    username: root
    password: root
    url: jdbc:mysql://localhost:3306/mall_sms?serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
```

mybatis.yml
```yaml
mybatis-plus:
  mapper-locations: classpath:/mapper/**/*.xml
  global-config:
    db-config:
      id-type: auto
```

other.yml
```yaml
spring:
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
    sentinel:
      transport:
        dashboard: localhost:8080
  application:
    name: mall-coupon
  zipkin:
    base-url: 127.0.0.1:9411/
    # 关闭我们自动的服务发现功能
    discovery-client-enabled: false
    sender:
      type: web
  sleuth:
    sampler:
      probability: 1
server:
  port: 7000
```



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