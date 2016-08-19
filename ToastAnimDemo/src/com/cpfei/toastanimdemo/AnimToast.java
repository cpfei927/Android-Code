package com.cpfei.toastanimdemo;

import java.lang.reflect.Field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class AnimToast extends Toast {

	public AnimToast(Context context) {
		super(context);
	}

	/**
	 * 调用有动画的Toast
	 * 
	 * @param context
	 * @param text
	 * @param duration
	 * @param 自定义的动画id
	 * @return
	 */
	@SuppressLint("InflateParams")
	public static Toast makeTextAnim(Context context, CharSequence text, int duration, int styleId) {

		View view = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
		Toast toast = new Toast(context);
		toast.setDuration(duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setView(view);

		try {
			Object mTN = null;
			mTN = getField(toast, "mTN");
			if (mTN != null) {
				Object mParams = getField(mTN, "mParams");
				if (mParams != null && mParams instanceof WindowManager.LayoutParams) {
					WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
					params.windowAnimations = styleId;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toast;
	}

	/**
	 * 反射字段
	 * 
	 * @param object
	 *            要反射的对象
	 * @param fieldName
	 *            要反射的字段名称
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	private static Object getField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		Field field = object.getClass().getDeclaredField(fieldName);
		if (field != null) {
			field.setAccessible(true);
			return field.get(object);
		}
		return null;
	}

}
