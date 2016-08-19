package com.cpfei.toastanimdemo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义时长的Toast
 * 
 * @author DexYang
 */
public class CToast {

	// 去掉重复的 remove_time 毫秒
	static String last_msg = "";
	static long last_remove_time = 0;

	public static void show_makeText_removeDuplicate(Context context, String text, int duration, int remove_time) {
		if (text == null || 0 == text.length()) {
			return;
		}

		if (System.currentTimeMillis() - last_remove_time < remove_time) {
			return;
		}

		last_remove_time = System.currentTimeMillis();
		last_msg = text;
		CToast result = new CToast(context);

		LinearLayout mLayout = new LinearLayout(context);
		TextView tv = new TextView(context);
		tv.setText(text);
		tv.setTextColor(Color.WHITE);
		tv.setGravity(Gravity.CENTER);
		mLayout.setBackgroundResource(R.drawable.ic_launcher);

		int w = context.getResources().getDisplayMetrics().widthPixels / 2;
		int h = context.getResources().getDisplayMetrics().widthPixels / 10;
		mLayout.addView(tv, w, h);
		result.mNextView = mLayout;
		result.mDuration = duration;

		result.show();
	}

	public static CToast makeText(Context context, CharSequence text, int duration) {
		CToast result = new CToast(context);

		LinearLayout mLayout = new LinearLayout(context);
		TextView tv = new TextView(context);
		tv.setText(text);
		tv.setTextColor(Color.WHITE);
		tv.setGravity(Gravity.CENTER);
		mLayout.setBackgroundResource(R.drawable.ic_launcher);

		int w = context.getResources().getDisplayMetrics().widthPixels / 2;
		int h = context.getResources().getDisplayMetrics().widthPixels / 10;
		mLayout.addView(tv, w, h);
		result.mNextView = mLayout;
		result.mDuration = duration;

		return result;
	}

	/**
	 * 
	 * @param context
	 * @param text
	 *            文字
	 * @param backgroudId
	 *            背景资源id
	 * @return
	 */
	public static CToast makeStrokeText(Context context, CharSequence text, int backgroudId) {
		CToast result = new CToast(context);

		LinearLayout mLayout = new LinearLayout(context);
		ViewGroup.LayoutParams params = mLayout.getLayoutParams();
		mLayout.setGravity(Gravity.CENTER);
		TextView tv = new TextView(context);
		// tv.setStroke(6, 0xff3c3c3c);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
		tv.setGravity(Gravity.CENTER);
		// tv.getBorderTextView().setGravity(Gravity.CENTER);
		tv.setText(text);
		tv.setTextColor(0xffededed);
		mLayout.setBackgroundResource(backgroudId);
		int w = context.getResources().getDisplayMetrics().widthPixels / 2;
		int h = context.getResources().getDisplayMetrics().widthPixels / 10;
		mLayout.addView(tv, w, h);
		result.mNextView = mLayout;
		result.mDuration = 500;
		return result;

	}

	public void setBackgroundResourceId(int resId) {
		if (null == mNextView && resId != 0) {
			mNextView.setBackgroundResource(resId);
		}
	}

	public static final int LENGTH_SHORT = 2000;
	public static final int LENGTH_LONG = 3500;

	private final Handler mHandler = new Handler();
	private int mDuration = LENGTH_SHORT;
	private int mGravity = Gravity.CENTER;
	private int mX, mY;
	private float mHorizontalMargin;
	private float mVerticalMargin;
	private View mView;
	private View mNextView;

	private WindowManager mWM;
	private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();

	public CToast(Context context) {
		init(context);
	}

	/**
	 * Set the view to show.
	 * 
	 * @see #getView
	 */
	public void setView(View view) {
		mNextView = view;
	}

	/**
	 * Return the view.
	 * 
	 * @see #setView
	 */
	public View getView() {
		return mNextView;
	}

	/**
	 * Set how long to show the view for.
	 * 
	 * @see #LENGTH_SHORT
	 * @see #LENGTH_LONG
	 */
	public void setDuration(int duration) {
		mDuration = duration;
	}

	/**
	 * Return the duration.
	 * 
	 * @see #setDuration
	 */
	public int getDuration() {
		return mDuration;
	}

	/**
	 * Set the margins of the view.
	 * 
	 * @param horizontalMargin
	 *            The horizontal margin, in percentage of the container width,
	 *            between the container's edges and the notification
	 * @param verticalMargin
	 *            The vertical margin, in percentage of the container height,
	 *            between the container's edges and the notification
	 */
	public void setMargin(float horizontalMargin, float verticalMargin) {
		mHorizontalMargin = horizontalMargin;
		mVerticalMargin = verticalMargin;
	}

	/**
	 * Return the horizontal margin.
	 */
	public float getHorizontalMargin() {
		return mHorizontalMargin;
	}

	/**
	 * Return the vertical margin.
	 */
	public float getVerticalMargin() {
		return mVerticalMargin;
	}

	/**
	 * Set the location at which the notification should appear on the screen.
	 * 
	 * @see android.view.Gravity
	 * @see #getGravity
	 */
	public void setGravity(int gravity, int xOffset, int yOffset) {
		mGravity = gravity;
		mX = xOffset;
		mY = yOffset;
	}

	/**
	 * Get the location at which the notification should appear on the screen.
	 * 
	 * @see android.view.Gravity
	 * @see #getGravity
	 */
	public int getGravity() {
		return mGravity;
	}

	/**
	 * Return the X offset in pixels to apply to the gravity's location.
	 */
	public int getXOffset() {
		return mX;
	}

	/**
	 * Return the Y offset in pixels to apply to the gravity's location.
	 */
	public int getYOffset() {
		return mY;
	}

	/**
	 * schedule handleShow into the right thread
	 */
	public void show() {
		mHandler.post(mShow);

		if (mDuration > 0) {
			mHandler.postDelayed(mHide, mDuration);
		}
	}

	public void showDelayTime(long delaytime) {
		mHandler.postDelayed(mShow, delaytime);
		if (mDuration > 0) {
			mHandler.postDelayed(mHide, mDuration + delaytime);
		}

	}

	/**
	 * schedule handleHide into the right thread
	 */
	public void hide() {
		mHandler.post(mHide);
	}

	private final Runnable mShow = new Runnable() {
		public void run() {
			try {
				handleShow();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private final Runnable mHide = new Runnable() {
		public void run() {
			handleHide();
		}
	};

	private void init(Context context) {
		final WindowManager.LayoutParams params = mParams;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.format = PixelFormat.TRANSLUCENT;
		params.windowAnimations = R.style.toastAnim;
		params.type = WindowManager.LayoutParams.TYPE_TOAST;
		params.setTitle("Toast");

		mWM = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	}

	private void handleShow() {

		if (mView != mNextView) {
			// remove the old view if necessary
			handleHide();
			mView = mNextView;
			// mWM = WindowManagerImpl.getDefault();
			final int gravity = mGravity;
			mParams.gravity = gravity;
			if ((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
				mParams.horizontalWeight = 1.0f;
			}
			if ((gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
				mParams.verticalWeight = 1.0f;
			}
			mParams.x = mX;
			mParams.y = mY;
			mParams.verticalMargin = mVerticalMargin;
			mParams.horizontalMargin = mHorizontalMargin;
			if (mView.getParent() != null) {
				mWM.removeView(mView);
			}
			try {
				mWM.addView(mView, mParams);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void handleHide() {
		if (mView != null) {
			if (mView.getParent() != null) {
				mWM.removeView(mView);
			}
			mView = null;
		}
	}

	public void setWindowAnimations(int anim) {
		mParams.height = WindowManager.LayoutParams.MATCH_PARENT;
		mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
		mParams.windowAnimations = anim;
	}
}
