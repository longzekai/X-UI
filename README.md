X-UI
====
<br>
[Demo apk下载](http://fir.im/pxlq)
<br>
<br>
该库旨在减少常用页面风格Activity或Fragment的代码量，包含了很多常用的操作，<br>且大部分功能都暴露了定制接口。如果你的应用页面风格属于非主流的，可绕道。
XUIApplication
====
实现了ActivityLifecycleCallbacks接口，在接口的回调方法中，可监控应用内所有的Activity生命周期的变化，<br>
在这些回调的基础上，封装处理了一个用于监听应用切后台切换的监听器，如果你的应用需要在切到后台或从后台<br>
回来时做些什么，你可以使用该监听器来处理。同时也实现了自定义的一个接口IFilterInterface，用于过滤和拦<br>
截所有页面发出的Intent，此处的用途，视需求自由发挥。
```java
DemoApplication.getInstance().setForegroundBackgroundListener(new XUIApplication.ForegroundBackgroundListener() {
            @Override
            public void enterForeground() {
                
            }

            @Override
            public void enterBackground() {

            }
        });
```
Activity
====
>IBaseActivity
>>FilterActivity
>>>ToolsActivity
>>>>SwipeBackActivity
>>>>>LoadStateActivity
>>>>>>TopBarActivity

<br>
###IBaseActivity
```java
public abstract class IBaseActivity extends AppCompatActivity implements IBaseInterface,View.OnClickListener
```
<br>
```java
public interface IBaseInterface {
    /** the method ,you can use it for restore instance*/
    void initRestoreInstance(Bundle savedInstanceState);
    /** you can get intent params in this method*/
    void parseIntent();
    /** return you activity user content view*/
    View getContentView(LayoutInflater inflater, ViewGroup container);
    /** you can find some views in this method*/
    void findViewById();
    /** in this method you can init something*/
    void initData();
    /** you can set view listener in this method*/
    void setListener();
}
```
<br>
###FilterActivity
```java
public abstract class FilterActivity extends IBaseActivity implements IFilterInterface
```
<br>
```java
public interface IFilterInterface {
    FilterParams onStartActivityForResult(Intent intent, int requestCode, Bundle options);
}
```
<br>
###ToolsActivity
```java
public abstract class ToolsActivity extends FilterActivity implements IToolsInterface
```
<br>
```java
public interface IToolsInterface {
    /** find view , No need for conversion*/
    <T extends View> T findView(int id);
    /** show toast , default is short toast*/
    void showToast(String message);
    /** show dialog */
    void showDialog(String message,DialogListener dialogListener);
    void showDialog(DialogInfo dialogInfo, DialogListener dialogListener);
    void showDialogSingleButton(String message,DialogListener dialogListener);
    void showDialogSingleButton(String message,String buttonText, DialogListener dialogListener);
    /** Return to network connection status */
    boolean isNetworkConnected();
    /** Return to network connection status is wifi */
    boolean isWifi();
    /** full screen for activity*/
    void fullScreen();
    /** Keep the screen always bright*/
    void keepScreenOn();
    /** quit full screen*/
    void quitFullScreen();
    /** force close keyboard*/
    void closeKeyCode(Context context);
}
```
<br>
###SwipeBackActivity
```java
public abstract class SwipeBackActivity extends ToolsActivity implements SwipeListener
```
<br>
```java
public interface SwipeListener {
    void onScroll(float percent, int px);
    void onEdgeTouch();
    /**
     * Invoke when scroll percent over the threshold for the first time
     */
    void onScrollToClose();
}
```
<br>
###LoadStateActivity
```java
public abstract class LoadStateActivity extends SwipeBackActivity implements ILoadStateInterface,ILoadStateHandle,ILoadStateListener
```
<br>
```java
public interface ILoadStateInterface {
    /** you can return loading style what you want custom */
    View getLoadingView();
    /** you can return error style what you want custom */
    View getErrorView();
}
```
<br>
```java
public interface ILoadStateHandle {
    /** you can accord activity page state ,set loading show style*/
    void setPageState(PageState pageState);
    /** show the custom loading dialog*/
    void showLoadingDialog(String message);
    /** close the loading dialog if it has show*/
    void closeLoadingDialog();
}
```
<br>
```java
public interface ILoadStateListener {
    /** this method will execute when you click in error page*/
    void retryLoad();
}

```
<br>
###TopBarActivity
```java
public abstract class TopBarActivity extends LoadStateActivity implements ITopBarInterface,ITopBarHandle,TopBarListener
```
<br>
```java
public interface ITopBarInterface {
    /** return the default top bar view*/
    View getDefaultTopBarView();
    /** if you want custom top bar view , use this method*/
    View getCustomTopBarView();
}
```
<br>
```java
public interface ITopBarHandle {
    /** return navigation view object*/
    View getNavigationView();
    /** return top bar title view object*/
    View getTitleView();
    /** return top bar menu view object , may be ImageView or TextView */
    View getMenuView();
    /** return the top bar view object*/
    View getTopBarView();
    /** return the top bar visible state*/
    boolean isTopBarVisible();
    /** set top bar shadow enable state*/
    void setShadowEnable(boolean enable);
    /** set color for top bar background color*/
    void setTopBarColor(int color);
    /** set top bar visible state*/
    void setTopBarVisible(boolean visible);
    /** find top bar view by view id*/
    <T extends View> T findTopBarViewById(int id);
    /** set top bar shadow visible state*/
    void setShadowVisible(boolean state);
    /** set custom navigation icon*/
    void setNavigationIcon(int resId);
    /** set navigation icon visible state*/
    void setNavigationVisible(boolean visible);
    /** set top bar center title text*/
    void setTopBarTitle(String title);
    /** set top bar center title drawable*/
    void setTitleDrawable(int drawableType,int drawId);
    /** is need menu function*/
    void setMenuEnable(boolean enable);
    /** set top bar menu type and value for resource id*/
    void setMenuType(MenuType type,int resId);
    /** set top bar menu text when menu type is text*/
    void setMenuText(String menuText);
    /** set top bar menu icon when menu type is icon*/
    void setMenuIcon(int icon);
}
```
<br>
```java
public interface TopBarListener {
    /** the method will executed , when the navigation clicked*/
    void onNavigationClick();
    /** the method will executed , when the top bar center title clicked*/
    void onTitleClick();
    /** the method will executed , when the top bar menu clicked*/
    void onMenuClick();
}
```
<br>
Fragment
====
the Fragment Similar to Activity,No longer detailed instructions.
