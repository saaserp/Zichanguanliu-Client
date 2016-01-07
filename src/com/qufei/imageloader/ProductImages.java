package com.qufei.imageloader;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qufei.androidapp.R;
import com.qufei.imageloader.util.HttpUtil;
import com.qufei.imageloader.util.ImageLoader;
import com.qufei.imageloader.util.ImageLoader.ImageDownloader;
import com.qufei.imageloader.util.ItemBean;
import com.qufei.query.ProductInfoActivity;

public class ProductImages extends Activity {

	public ImageLoader imageLoader;
	public void init(){
		imageLoader = new ImageLoader(new ImageDownloader(){
			@Override
			public Bitmap download(String path, int width, int height) {
				try{
					return HttpUtil.download(path);
				}catch(Exception e){
					Toast.makeText(ProductImages.this, "手机内存不足，无法加载图片。", Toast.LENGTH_SHORT).show();
					return null;
				}
			}
		});

		final Gallery galleryView = (Gallery)this.findViewById(R.id.galleryView);
		this.findViewById(R.id.title_left).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ProductImages.this.finish();
			}
		});

		try{//显示图片
			List<ItemBean> dataList = getDataList();

			galleryView.setAdapter(new ListViewAdapter(ProductImages.this, dataList));
		}catch(Exception e){
			Toast.makeText(ProductImages.this, "图片资源不存在", Toast.LENGTH_LONG).show();
		}

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.images_activity_main);
		init();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//		imageLoader.destory();

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//		imageLoader.destory();
	}

	private class ListViewAdapter extends BaseAdapter{
		private Context context;
		private List<ItemBean> dataList;
		public ListViewAdapter(Context context, List<ItemBean> dataList){
			this.context = context;
			this.dataList = dataList;
		}
		@Override
		public int getCount() {
			return dataList.size();
		}
		@Override
		public Object getItem(int position) {
			return dataList.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if(convertView == null){
				holder = new Holder();
				convertView = new ItemView(context);
				holder.itemView = (ItemView)convertView;
				convertView.setTag(holder);
			}else{
				holder = (Holder)convertView.getTag();
			}

			ItemView itemView = holder.itemView;


			ImageView itemImageView = itemView.getImageView();
			ItemBean item = dataList.get(position);

			itemImageView.setImageResource(R.drawable.tz);

			//			itemImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageLoader.loadImage(item.getImagePath(), 64, 64, itemImageView);
			return itemView;
		}
		class Holder{
			ItemView itemView;
		}
	}

	private List<ItemBean> getDataList(){
		List<ItemBean> dataList = new ArrayList<ItemBean>();

		ArrayList<String> arrayList=getIntent().getStringArrayListExtra("imageURLs");
		for(String str:arrayList){
			ItemBean item = new ItemBean();

			item.setImagePath(str);
			dataList.add(item);
		}
		return dataList;
	}

	class ItemView extends RelativeLayout{
		private ImageView image;
		public ItemView(Context context) {
			super(context);
			init();
		} 
		public ItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}
		public ItemView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init();
		}
		private void init(){
			final LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
			View v = mLayoutInflater.inflate(R.layout.images_item, null, false);
			addView(v);
			image = (ImageView)v.findViewById(R.id.item_image);
		}
		public ImageView getImageView() {
			return image;
		}
	}


}
