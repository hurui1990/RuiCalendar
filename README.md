# RuiCalendar
自定义日历控件
### 使用手册
project gradle.build中添加
```java
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
app gradle.build中添加
```java
dependencies {
    compile 'com.github.hurui1990:RuiCalendar:v1.0.2'
}
```
具体的调用代码如下：
布局文件中
```java
<com.hurui.customcalendar.RuiCalendar
    android:id="@+id/ruicalendar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
</com.hurui.customcalendar.RuiCalendar>
```
MainActivity中
```java
RuiCalendar mRuiCalendar = (RuiCalendar) findViewById(R.id.ruicalendar);//获取控件
mRuiCalendar.setOnItemClickListener(this);//设置监听
```
