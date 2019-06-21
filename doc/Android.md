## Android学习记录
学习思路
  * 是什么？
  * 为什么？
  * 怎么做？
  * 原理 & 封装扩展

目录
 * [基础](https://github.com/NanaGithub/Today/blob/master/doc/Android.md#%E5%9F%BA%E7%A1%80)

##  基础
#### [packageName和applicationId有什么区别](https://blog.csdn.net/u011889786/article/details/54296462)
#### DialogFragment

[Android 官方推荐 : DialogFragment 创建对话框](https://blog.csdn.net/lmj623565791/article/details/37815413)

[Android 撸起袖子，自己封装 DialogFragment
](https://mp.weixin.qq.com/s?__biz=MzIxNzU1Nzk3OQ==&mid=2247484330&idx=1&sn=b1b4f88041520fa01685a8ca3885ff7e&chksm=97f6bd1ea08134088d28281021cb210d636546057fbf2f5100d6c98a695d67aa20b7bc7237d7&scene=38#wechat_redirect)

### EditText
#### 常用需求

* 焦点和键盘问题 [点击获取工具类](https://github.com/NanaGithub/Today/blob/master/base/src/main/java/com/jnn/mylibrary/util/EditViewUtil.java)

#### 扩展需求
#### 问题记录

* 正常情况：有焦点且不会自动调起软键盘
* recyclerview中有EditText，有焦点且弹出键盘
* 键盘弹出时，底部tab被顶起
  
  ```android:windowSoftInputMode="stateHidden|adjustPan"```
 

