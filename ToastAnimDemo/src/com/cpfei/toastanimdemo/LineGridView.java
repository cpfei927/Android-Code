package com.cpfei.toastanimdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

public class LineGridView extends GridView {

	public LineGridView(Context context) {
		this(context, null);
	}

	public LineGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		int column = getNumColumns();
		int childCount = getChildCount();// 子view的总数
		Paint localPaint;// 画笔
		localPaint = new Paint();
		localPaint.setStyle(Paint.Style.STROKE);

		localPaint.setColor(getContext().getResources().getColor(R.color.line));// 设置画笔的颜色
		
		float strokeWidth = localPaint.getStrokeWidth();

		for (int i = 0; i < childCount; i++) {// 遍历子view
			View cellView = getChildAt(i);// 获取子view
			// 第一行和第一列画左边和上面的线
			if (i < column) {// 第一行
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(),
						localPaint);
			}

			if (i % column == 0) {// 第一列
				canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(),
						localPaint);
			}
			
			// 画右边的Line
			canvas.drawLine(cellView.getRight() - (strokeWidth * column), cellView.getTop(), 
					cellView.getRight() - (strokeWidth * column), cellView.getBottom(),
					localPaint);
			
			// 画子view底部横线
			canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(),
					localPaint);
			
		}

	}

}
