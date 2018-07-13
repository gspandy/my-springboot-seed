my-springboot-seed（Oauth2）
================
[![GitHub release](https://img.shields.io/github/release/ruyangit/my-springboot-seed.svg)](https://github.com/ruyangit/my-springboot-seed/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

#### 介绍

采用SpringBoot、SpringSecurity、Oauth2 授权认证，权限管理。

* **Oauth2 Grant Type**
  * Authorization Code  授权码模式类似微博、微信等.
  * Implicit  不常用不做说明.
  * Resource Owner Password Credentials(password)  受信任的应用，该系统主要应用方式.
  * Client Credentials  用在应用API客户端，如  android、ios、webapp.

#### 开始使用
``` bash
下载
git clone https://github.com/ruyangit/my-springboot-seed.git

进入目录
cd my-springboot-seed

安装
mvn clean package -Dmaven.test.skip=true -P dev

运行
java -jar target/my-springboot-seed.jar --spring.profiles.active=dev

访问
http://127.0.0.1:9999/current
```

#### 开发

``` bash
整理中...
```

#### 版本管理

* **v0.0.2**
  * 新增验证码code处理器，code发送，校验，销毁
  * 新增邮件code校验
  * 新增redis缓存配置

* **v0.0.1**
  * 初始版本，完成授权认证。
  * 完成基础工具类编写收集。
  * 异常统一处理。
  * 异常状态码统一管理。
  * Objectmapper统一返回Json数据处理。
  * logbak日志处理。

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request