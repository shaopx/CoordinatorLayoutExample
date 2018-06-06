# 说明
学习CoordinatorLayout, NestedScrollingParent和 NestedScrollingChild时整理的一些示例

# 博文整理 (目前个人感觉讲的最深入浅出的几篇)
https://www.jianshu.com/p/f09762df81a5  
https://www.cnblogs.com/wjtaigwh/p/6398562.html  

# 学习记录
## 为什么appbarlayout可以在手触摸拖动的时候上滑?  它没有监听下面的recyclerView, 也没有onIntercepteTouchEvent() onTouchEvent()方法.  答案在这两个博文里:
https://blog.csdn.net/litefish/article/details/52588235  
https://www.jianshu.com/p/99adaad8d55c  

# 示例说明

## ScrollingActivity
谷歌官方的示例代码, 每次新建工程时都可以选中的那个嵌套滑动模板代码 

 ### 知识点一 
 解决了谷歌官方模板中存在的appbarlayout抖动的问题. 具体问题描述请见下面博文:  
(CoordinatorLayout和AppBarLayout 嵌套滑动时抖动的一个原生bug)  https://www.jianshu.com/p/367c0f251445 
 ### 知识点二  
 解决了谷歌官方模板中存在的recyclerview滑动到边界之后, idle事件不能及时接收的问题
 bug位置: https://issuetracker.google.com/issues/66996774
 里面也附带了chrisbanes的解决方法. 

## CustomNestedScrolling  和 CustomFoldingScrolling
从https://github.com/543441727/MyNestedScrolling中复制过来的
博文: https://www.cnblogs.com/wjtaigwh/p/6398562.html
整理到这里, 看的时候方便. 简单来说就是通过实现NestedScrollingParent和 NestedScrollingChild接口, 实现了最最简单的嵌套滑动, 但是对于学习这两个概念, 真是非常好的示例. 

## CustomMyCoorLayoutActivity 
也是自定义了一个根布局, 这我自己的一个实现的例子, 并没有什么特别之处.  但是实现了基本的几个功能点: 状态栏透明, 使用了tab, 使用了viewpager来包含多个RecyclerView. 
博文:https://www.jianshu.com/p/505ff43e9d9e

## JiKeTopicDetailActivity
仿照即刻话题详情页的一份示例   基本的框架和功能都有.  


