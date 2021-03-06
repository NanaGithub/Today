## 宗旨
从0开始，重新开始。

## 编程准备 
### 开发工具
1.  JDK
2.  AndroidStudio
3.  Git

### 辅助工具
1.  谷歌浏览器（记录常用网站等）
2.  有道云笔记，有道云词典
3.  Sublime Text（http://www.sublimetext.cn/）

### 交流工具
1.  QQ
2.  微信 
3.  钉钉

### 谷歌浏览器实用插件
1.  Octotree（方便Github代码查看）
2.  谷歌访问助手插件（搜索更便捷）

## 上线代码检查
* 检查版本相关（目标版本、最小版本，编译版本）
* 检查versionCode
* 检查三方配置，类似AppId等
* 检查代码中涉及debug和release的代码逻辑
* App更新机制
* 生成签名文件
* App加固签名



## 其他

### MD编辑器
*  Preference——>Plugins——>搜索 "Markdown"——>选择 Markdown
   Navigator「预览插件」
  

### AS中创建一个JAVA可运行项目
*  File—>New—>Module—>Java Library
*  点开MyClass类，添加入口 
   ```
    public static void main(String[] args) {
        
    }
    ```
* 右击MyClass文件，选择 Run ‘MyClass.main()’;

### 获取应用签名

需求场景：三方平台（微信分享、高德地图等）应用注册时，需要填写包名和签名。

方式一：通过AS直接获取，最快捷，最推荐。

* 打开AS——>点击右侧的Gradle——>点击app——>Tasks——>android——>singingReport——>控制台查看签名「MD5值需将冒号去掉，大写转换为小写」

* 第一次编译会慢很多，默认打印debug，打印release需要配置。

方式二：通过命令获取，在控制台输入命令，需要记命令，麻烦。

* 第一步：切换到你存放 xxx.jks 的目录

* 第二步：输入以下命令回车
   ```
   keytool -list -v -keystore xxx.jks 
   ```
* 第三步：输入秘钥口令「也就是密码」

* 成功后，取 MD5值，类似的三方平台所需要的签名就是该 MD5值，需要注意的就是 将 冒号去掉 并 大写转换为小写

方式三：通过代码获取，快捷方便，推荐。

[点击查看工具类](https://github.com/NanaGithub/Today/blob/master/base/src/main/java/com/jnn/mylibrary/util/AppSigning.java)



