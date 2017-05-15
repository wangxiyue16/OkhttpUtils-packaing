# OkhttpUtils-packaing
对okhttputils的封装类，okhttputils见：[https://github.com/hongyangAndroid/okhttputils.


## 用法

* Android Studio
	
	```
	compile 'com.wangyue:okhttputils-packaging:1.0.2'
	```
	当前使用的是这个版本,其它版本未测试
	

## 第一步建议在项目下创建一个接口继承`lib.network.bean`包下的`NetworkListener`接口,这个接口名称可随意取,附上本人的代码:

```java
public interface INetwork extends NetworkListener {

    /**
     * @param tag
     * @param request
     */
    void exeNetworkRequest(int tag, NetworkRequest request);

    /**
     * 可以自行设置重试次数及超时时间, 多用于一些需要不断重试的任务
     *
     * @param tag
     * @param request
     * @param listener
     */
    void exeNetworkRequest(int tag, NetworkRequest request, NetworkListener listener);

    /**
     * 取消所有网络任务
     */
    void cancelAllNetworkRequest();

    void cancelNetworkRequest(int tag);
}
```


## 第二步在你项目最底层的Fragment和Activity中实现`INetwork`这个你定义的接口,并完善网络请求的代码,
    定义一个`lib.network`包下的`NetworkExecutor`的成员变量,
```java
	private NetworkExecutor mNetworkExecutor;
```

	然后,
```java
 public void exeNetworkRequest(int what, NetworkRequest request) {
        exeNetworkRequest(what, request, this);
    }

    public void exeNetworkRequest(int what, NetworkRequest request, NetworkListener listener) {
        if (isFinishing()) {
            return;
        }
        if (mNetworkExecutor == null) {
            mNetworkExecutor = new NetworkExecutor(getClass().getName(), this);
        }
        mNetworkExecutor.execute(what, request, listener);
    }

    @Override
    public void cancelAllNetworkRequest() {
        if (mNetworkExecutor != null) {
            mNetworkExecutor.cancelAll();
        }
    }

    @Override
    public void cancelNetworkRequest(int tag) {
        if (mNetworkExecutor != null) {
            mNetworkExecutor.cancel(tag);
        }
    }
	
```
	不要忘记在`onDestroy`方法中添加取消任务
```java
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mNetworkExecutor != null) {
            mNetworkExecutor.destroy();
            mNetworkExecutor = null;
        }
    }
	
```

## 第三步就简单了,当你创建的Activity或者是Fragment继承你写的基类时,
	网络请求就调用这个方法就可以了,
```java
	exeNetworkRequest(int what, NetworkRequest request);
```
	第一个参数what是你你开启的请求的序列,请求成功或者失败或者是取消请求都用到该值,
	第二个参数是请求的任务实例,NetworkRequest可以通过`lib.network.bean.NetworkRequest`类里面的
```java
		NetworkRequest task = newPost(String url);
```
等方法得到,也可以通过`lib.network.bean.NetworkRequest`类里面的
```java
		task.addParam(String name, String value);
```
等方法添加请求的参数

## 最后一步,在你的项目中(尽量在Application中)调用方法:
```java
		NetworkRequest.init(this);
```

## 其它几个方法的解释:
```java
	    Object onNetworkResponse(int id, NetworkResponse nr);
```
可以通过`nr.getText()`得到请求到的网络数据(Sring字符串类型),这个方法是让你把得到的String类型数据解析成json数据并封装到bean类的,返回的Object即你封装好数据的bean这个类
```java
	     void onNetworkSuccess(int id, Object result);
```
这个方法是让你对成功拿到网络数据并解析成功后的操作的,`result`是解析后的bean数据,只需把result强转成`onNetworkResponse`这个方法返回的Objec(即你返回的自己定义的bean)即可
```java
	     void onNetworkError(int id, NetError error);
```
这个方法是网络请求数据错误或者其它错误时操作的方法,例如取消Loading等,可以从`error`中拿到错误内容



## 混淆

```该库的
 -keep class okhttp3.**
 -keep class okio.**

#okhttp的
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


#okio的
-dontwarn okio.**
-keep class okio.**{*;}


```






