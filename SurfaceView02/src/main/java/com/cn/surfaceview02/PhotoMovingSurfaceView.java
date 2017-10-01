package com.cn.surfaceview02;

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

public class PhotoMovingSurfaceView extends SurfaceView implements Callback {
	private int lastX=0;
	private Paint mPaint;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private boolean isSurfaceRun = true;
	private SurfaceHolder mSurfaceHolder;
	private DrawMovingImageThread mDrawMovingImageThread;
   
	public PhotoMovingSurfaceView(Context context) {
		super(context);
		init();
	}

	private void init(){
		mPaint=new Paint();
		//SurfaceView的方法getHolder()
		mSurfaceHolder=this.getHolder();
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
		mCanvas=mSurfaceHolder.lockCanvas();
		if (mCanvas!=null) {
			//以下四行为清屏的核心代码:
			Paint paint = new Paint();
			paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
			mCanvas.drawPaint(paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC));
			//每次画之前先进行清屏
			mCanvas.drawBitmap(mBitmap, lastX, 40, mPaint);
			lastX+=20;
			if (lastX>=370) {
				lastX=0;
			}
			mSurfaceHolder.unlockCanvasAndPost(mCanvas);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mDrawMovingImageThread.start();
	}
	
	//以下三个方法为android.view.SurfaceHolder.Callback接口的实现
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//调用SurfaceView的onDraw()方法
		onDraw(null);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
           isSurfaceRun=false;
	}

}
