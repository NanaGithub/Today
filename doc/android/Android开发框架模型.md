## MVC、MVP、MVVM

课程学习 https://www.imooc.com/learn/1177

MVC
* 优点：一定程度上实现了Model和View的分离，降低了耦合性。
* 缺点：Controller和View无法完全的解耦，随着项目业务复杂的提升，Controller会越来越臃肿。

MVP
* 优点：解决了MVC中Controller与View过度耦合的缺点，指责划分相对明确，更易于维护。
* 缺点：接口数量多，代码量增加，随着项目复杂度的提升，Presenter层会越来越臃肿。
* 使用建议：
  * 封装父类接口，减少接口数量；
  * 使用三方插件自动生成MVP代码；
  * 对于一些简单的界面，可以选择不使用框架。

MVVM
* 优点：代码逻辑更加简洁
* 缺点：使用databinding进行数据双向绑定，bug难调试，学习成本高。
