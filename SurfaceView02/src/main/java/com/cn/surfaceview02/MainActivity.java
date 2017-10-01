package com.cn.surfaceview02;

import android.app.Activity;
import android.os.Bundle;

/**
 * 示例描述:
 * 1 Surface和SurfaceView以及SurfaceHolder的认识及使用
 * 2 Canvas清屏的实现
 * 3 在SurfaceView上不断绘制图片
 *   所绘制的图片在屏幕上的X轴值不断变化
 * 
 * 请注意:
 * 1 Surface用于管理数据，SurfaceView用于展示数据
 * 2 SurfaceHolder是一个接口，其作用相当于是Surface的监听器.
 *   SurfaceHolder提供了访问和控制SurfaceView背后的Surface的相关方法.
 *   即SurfaceHolder通过三个回调方法可知Surface的创建、销毁或者改变
 *   可通过SurfaceView中的方法getHolder()获得SurfaceView所对应的Surface所对应的SurfaceHolder
 * 3 SurfaceView是在一个新线程中绘制图像,而不是在UI线程
 * 
 */
public class MainActivity extends Activity {
    private PhotoMovingSurfaceView mPhotoMovingSurfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPhotoMovingSurfaceView = new PhotoMovingSurfaceView(this);
		setContentView(mPhotoMovingSurfaceView);
	}

}
