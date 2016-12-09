**Real Android recycles RecyclerView/ListView**

*To fix react-native [[ListView] renders all rows#499](https://github.com/facebook/react-native/issues/499)*

*Thanks for [@talkol](https://github.com/talkol) [articles](http://blog.wix.engineering/2016/06/30/recycling-rows-for-high-performance-react-native-list-views/). I implement the android RecyclerView(react-native-RealRecyclerView)*

**How to use**

1.  run  this project

  copy from another project react-native 0.39.0 version of the node_modules folders can be used.

2.  use RealRecyclerView component

  copy My*.java &RealRecycler*.java file to your project;

  setup UIImplementationProvider. ReactInstanceManager.Builder.setUIImplementationProvider(new MyUIImplementationProvider())

--------
**真正的 Android 回收复用 RecyclerView/ListView**

*解决 react-native [[ListView] renders all rows#499](https://github.com/facebook/react-native/issues/499) 而不是复用listview item视图问题*

**使用方法**

1.  怎样跑这个实例

从其他项目拷贝react-native 0.39.0版本的node_modules文件夹既可使用。

2.  怎样使用RealRecyclerView组件

  拷贝 My*.java 和RealRecycler*.java 文件到您的项目;
  
  设置UIImplementationProvider:ReactInstanceManager.Builder.setUIImplementationProvider(new MyUIImplementationProvider())
  
详情见我的博文《[react native Android 真正回收复用 RecyclerView/ListView](https://my.oschina.net/droidwolf/blog/750479)》

*demo*

![](https://github.com/droidwolf/react-native-RealRecyclerView/blob/master/GIF.gif)

--------
**Authors**

droidwolf [droidwolf2006@gmail.com](mailto:droidwolf2006@gmail.com "droidwolf2006@gmail.com")


**License**

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0")

