### Mac系统快捷键
* control+空格 切换中文英文
* control+command+Q 锁屏
* 张开五指 回到桌面
* alt+command+esc 正在运行中程序
* 显示/隐藏 .文件
  
  * 方式一：shift+command+. 「显示/隐藏」
  
  * 方式二：执行该命令 
    ```
    //显示
    defaults write com.apple.finder AppleShowAllFiles -bool true
    //隐藏
    defaults write com.apple.finder AppleShowAllFiles -bool false
    ```
    而后重启访达

* 配置环境变量「范例JDK环境变量配置」
  
  * 1、打开终端Terminal；

  * 2、进入当前用户主目录，cd ~；

  * 3、临时授权，sudo su；

  * 4、输入密码（密码不显示）；

  * 5、创建.bash_profile文件，touch .bash_profile（如果存在则不必新建，我的没有）；

  * 6、打开.bash_profile文件，open .bash_profile（能打开则新建成功）；

  * 7、输入jdk文件路径，系统默认的是1.6，我的是1.8，可以两个都配置，方便以后切换。
    ```
    export JAVA_6_HOME=/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home

    export JAVA_8_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_51.jdk/Contents/Home

    export JAVA_HOME=$JAVA_8_HOME
    ```
    保存并退出；

  * 8、读取并执行文件中的命令，source .bash_profile；

  * 9、在Terminal中输入java -version，显示jdk信息，则配置成功。
