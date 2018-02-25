本质规定：View 的操作必须在创建它的 UI 线程上进行

原因：
Activity 的创建需要新建一个 ViewRootImpl 对象，而该对象则是使用主线程创建的
所以，只能有主线程才能修改UI

在其他非原始线程更新ui的情况：有，如 SurfaceView 就可在其他线程更新