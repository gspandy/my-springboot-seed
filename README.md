my-springboot-seed（SpringSecurity+JWT）
================
[![GitHub release](https://img.shields.io/github/release/ruyangit/my-springboot-seed.svg)](https://github.com/ruyangit/my-springboot-seed/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

#### 介绍

采用SpringBoot、SpringSecurity、JWT 授权认证，权限管理。

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
http://127.0.0.1:9999/test
```

#### 开发

``` bash
整理中...
```

#### 版本管理

* **v0.0.5**
  * 完善权限模块
  * 角色管理
  * 权限管理
  * 用户管理
  * 采用tk.mapper进行数据库操作
  * 全局异常AccessDeniedException捕获

* **v0.0.4**
  * 添加数据源Druid，配置数据源访问账户

* **v0.0.3**
  * 完善认证逻辑，异常统一处理
  * 新增邮箱验证
  * 新增图片验证码
  * 新增短信验证码
  * 新增短信验证码登录

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