# FWManager

Mini project for Firewall Manager

一个超级简单的Linux防火墙管理程序，使用java编写，仅自用。

## 支持的功能
- UFW防火墙放行IP

**使用简记：**

1. 适用于安装、启用了UFW防火墙的Linux系统；
2. 系统需要安装java环境；
3. 运行命令（默认80端口）：
    ```shell script
    java -jar fw_managet-1.0.0.jar
    ```
4. 指定端口运行
    ```shell script
    java -Dserver.port=8080 -jar fw_manager-1.0.0.jar
    ```
5. 配合Nginx反代 + SSL可以实现HTTPS？ 自己暂时没有此场景的需求。

目前已经达到个人使用的目的，暂时不考虑添加功能或其他更新。


