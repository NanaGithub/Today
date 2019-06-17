## Android学习记录
###  基础
[packageName和applicationId有什么区别](https://blog.csdn.net/u011889786/article/details/54296462)

### EditText
#### 常用需求

* 焦点和键盘问题 [点击获取工具类](https://github.com/NanaGithub/Today/blob/master/base/src/main/java/com/jnn/mylibrary/util/EditViewUtil.java)

#### 扩展需求
#### 问题记录

* 正常情况：有焦点且不会自动调起软键盘
* recyclerview中有EditText，有焦点且弹出键盘
* 键盘弹出时，底部tab被顶起
  
  ```android:windowSoftInputMode="stateHidden|adjustPan"```
 

