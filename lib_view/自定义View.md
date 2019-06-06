## attr属性 `fromat` 的属性值意义 

*   string、boolean、float、integer
*   color、enum
*   dimension「尺寸」
*   fraction「百分比」
*   flags「位或运算」
*   reference「资源id」

戳此详细了解 
[Android自定义属性，attr format取值类型](https://www.cnblogs.com/rayray/p/3442026.html)

tips：自定义view时，为了更简洁，指定一种类型即可。

## TypedArray中 getDimension()、getDimensionPixelSize() 和 getDimensionPixelOffset()的区别
*   三者的结果值 = 将资源文件中定义的dip值 乘以 屏幕密度,如果定义的是
    px值，则不进行转换。最终都是得到px值， 比如 171*1.5=256.5，
*   不同点在于 getDimension() 返回float，其他两个返回int
    
    ```
    getDimension: 256.500000

    getDimensionPixelSize: 257「四舍五入」

    getDimensionPixelOffset:256「舍」
   
     ```
*   [getDimension()、getDimensionPixelSize()和getDimensionPixelOffset()的区别
          ](https://www.jianshu.com/p/282032797637)