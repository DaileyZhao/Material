package com.zcm.support.basetitle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zcm.support.R;


public class BaseTitleView extends FrameLayout {
	private Context context;
	private RelativeLayout rel_title;
	private TextView tv_left;
	private TextView tv_title;
	private TextView tv_right;
	private ProgressBar eventIconPro;
	private View title_line;

	private Drawable leftDra;
	private Drawable rightDra;
	private String leftText;
	private String titleText;
	private String rightText;
	private boolean lineShow;

	public BaseTitleView(Context context) {
		super(context);
		this.context = context;
		initView();
	}

	public BaseTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.BaseTitleView);
		leftDra = a.getDrawable(R.styleable.BaseTitleView_leftRes);
		rightDra = a.getDrawable(R.styleable.BaseTitleView_rightRes);

		leftText = a.getString(R.styleable.BaseTitleView_leftText);
		titleText = a.getString(R.styleable.BaseTitleView_titleText);
		rightText = a.getString(R.styleable.BaseTitleView_rightText);

		lineShow = a.getBoolean(R.styleable.BaseTitleView_lineShow, true);
		this.context = context;
		initView();
	}

	public BaseTitleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		initView();
	}

	private void initView() {
		View view = LayoutInflater.from(context).inflate(
				R.layout.base_title_layout, this);
		rel_title = (RelativeLayout) view.findViewById(R.id.rel_title);
		tv_left = (TextView) view.findViewById(R.id.tv_left);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_right = (TextView) view.findViewById(R.id.tv_right);
		eventIconPro= (ProgressBar) view.findViewById(R.id.eventIconProgressBar);
		title_line = view.findViewById(R.id.title_line);

		tv_left.setCompoundDrawablesWithIntrinsicBounds(leftDra, null, null, null);
		tv_right.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDra, null);
		if (!TextUtils.isEmpty(leftText)) {
			tv_left.setText(leftText);
		}
		if (!TextUtils.isEmpty(titleText)) {
			tv_title.setText(titleText);
		}
		if (!TextUtils.isEmpty(rightText)) {
			tv_right.setText(rightText);
		}
		if (lineShow) {
			title_line.setVisibility(VISIBLE);
		} else {
			title_line.setVisibility(GONE);
		}
	}

	/**
	 * 设置title左侧文字
	 * 
	 * @param leftStr
	 */
	public void setLeftText(String leftStr) {
		tv_left.setText(leftStr);
	}
	public void setRightTextColor(int color){
		tv_right.setTextColor(color);
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 */
	public void setTitleText(String title) {
		tv_title.setText(title);
	}

	/**
	 * 获取标题栏textview
	 * @return
	 */
	public TextView getTitleView() {
		return tv_title;
	}
	
	/**
	 * 设置右侧文字
	 * 
	 * @param rightStr
	 */
	public void setRightText(String rightStr) {
		tv_right.setText(rightStr);
	}
	/**
	 * 设置右侧按钮背景
	 * 
	 * @param drawable
	 */
	public void setTieleImageResources(int drawable) {
		if(drawable==0){
			tv_title.setCompoundDrawablesWithIntrinsicBounds (null,null, null, null);
		}else{
			Drawable drawAble = getResources().getDrawable(drawable);
			tv_title.setCompoundDrawablesWithIntrinsicBounds (null,null, drawAble, null);
		}
	}
	
	/**
	 * 设置右侧按钮背景
	 * 
	 * @param drawable
	 */
	public void setRightImageResources(int drawable) {
		if(drawable==0){
			tv_right.setCompoundDrawablesWithIntrinsicBounds (null,null, null, null);
		}else{
			Drawable drawAble = getResources().getDrawable(drawable);
			tv_right.setCompoundDrawablesWithIntrinsicBounds (null,null, drawAble, null);
		}
	}

	/**
	 * 设置左侧按钮背景
	 * 
	 * @param drawable
	 */
	public void setLeftImageResources(int drawable) {
		if(drawable==0){
			tv_left.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}else{
			Drawable drawAble = getResources().getDrawable(drawable);
			tv_left.setCompoundDrawablesWithIntrinsicBounds(drawAble, null, null, null);
		}
	}

	/**
	 * 获取左侧textView
	 * 
	 * @return
	 */
	public TextView getLeftTextView() {
		return tv_left;
	}

	/**
	 * 获取右侧textView
	 * 
	 * @return
	 */
	public TextView getRightTextView() {
		return tv_right;
	}

	/**
	 * 设置是否隐藏下滑线
	 */
	public void setTitleLineVisibility(int visibility) {
		title_line.setVisibility(visibility);
	}
	/**
	 * 设置背景色
	 */
	public void setBackGroundColor(int color){
		rel_title.setBackgroundColor(context.getResources().getColor(color));
	}
	/**
	 * 设置progressBar是否显示
	 */
	public void setProgressVisibilty(int visibilty){
		eventIconPro.setVisibility(visibilty);
	}
}
