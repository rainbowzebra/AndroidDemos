package com.cn.surfaceview01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class ImageMovingSurfaceView extends SurfaceView implements Callback {
	private int lastX=0;
	private Paint mPaint;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private boolean isSurfaceRun = true;
	private SurfaceHolder mSurfaceHolder;
	private DrawMovingImageThread mDrawMovingImageThread;
   
	public ImageMovingSurfaceView(Context context) {
		super(context);
		init();
	}

	private void init(){
		mPaint=new Paint();
		//获取SurfaceView的SurfaceHolder
		mSurfaceHolder=this.getHolder();
		//为SurfaceHolder设置回调
		mSurfaceHolder.addCallback(this);
		mDrawMovingImageThread=new DrawMovingImageThread();
		mBitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
	}
	
	//开启线程不断地绘制Bitmap
	private class DrawMovingImageThread extends Thread{
		@Override
		public void run() {
			super.run();
			while (isSurfaceRun) {
				drawMovingImage();
				try {
					Thread.sleep(300);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private void drawMovingImage(){
		//从SurfaceHolder获取画布,并在锁定后开始绘画
		mCanvas=mSurfaceHolder.lockCanvas();
		if (mCanvas!=null) {
			//以下四行为清屏的核心代码:
			Paint paint = new Paint();
			paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			mCanvas.drawPaint(paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
			//在每次画之前先进行清屏
			mCanvas.drawBitmap(mBitmap, lastX, 40, mPaint);
			lastX+=20;
			if (lastX>=370) {
				lastX=0;
			}
			//解锁
			mSurfaceHolder.unlockCanvasAndPost(mCanvas);
		}
	}
	
	//以下三个方法为android.view.SurfaceHolder.Callback接口的实现
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
          mDrawMovingImageThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
           isSurfaceRun=false;
	}

}
