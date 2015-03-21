package mangues.baidumap;

import java.util.ArrayList;
import java.util.List;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Bitmap;

public class MainActivity extends Activity  {
	MapView mMapView = null;
	BaiduMap mBaiduMap = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_main);
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 普通地图
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
	//	MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(mBaiduMap.getMaxZoomLevel());
	//	mBaiduMap.animateMapStatus(u);

		List<Info> infos = new ArrayList<Info>();
		infos.add(new Info(34.242652, 108.971171, R.drawable.maker, "英伦贵族小旅馆",
				"距离209米", 1456));
		infos.add(new Info(34.242952, 108.972171, R.drawable.maker, "沙井国际洗浴会所",
				"距离897米", 456));
		infos.add(new Info(34.242852, 108.973171, R.drawable.maker, "五环服装城",
				"距离249米", 1456));
		infos.add(new Info(34.242152, 108.971971, R.drawable.maker, "老米家泡馍小炒",
				"距离679米", 1456));

		addInfosOverlay(infos);
		addListener();
	}

	//view 转化为bitmap
	private Bitmap viewtoBitmap(String name) {
		LayoutInflater factory = LayoutInflater.from(this);
		View textEntryView = factory.inflate(R.layout.baidumap_body, null); ////把视图转换成Bitmap 再转换成Drawable
		TextView tv=(TextView)textEntryView.findViewById(R.id.baidumap_body_tv);
		tv.setText(name);
		textEntryView.setDrawingCacheEnabled(true);
		textEntryView.measure(
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		textEntryView.layout(0, 0, textEntryView.getMeasuredWidth(),
				textEntryView.getMeasuredHeight());
	    textEntryView.buildDrawingCache();
	    Bitmap newbmp = textEntryView.getDrawingCache();
	    //BitmapDrawable bd =new BitmapDrawable(newbmp);
	    return newbmp;  
	}
	
	//设置Marker  的view
	private View setMarkerView(Info info) {
		LayoutInflater factory = LayoutInflater.from(this);
		View textEntryView = factory.inflate(R.layout.baidumap_body, null); ////把视图转换成Bitmap 再转换成Drawable
		TextView tv=(TextView)textEntryView.findViewById(R.id.baidumap_body_tv);
		tv.setText(info.getName());
		return textEntryView;

	}
	
	
	/**
	 * 初始化图层
	 */
	public void addInfosOverlay(List<Info> infos) {
		mBaiduMap.clear();
		LatLng latLng = null;
		OverlayOptions overlayOptions = null;
		Marker marker = null;
		for (Info info : infos) {
			//设置Marker View
            View view=setMarkerView(info);
//			BitmapDescriptor bitmap = BitmapDescriptorFactory.fromBitmap(viewtoBitmap(info.getName()));
			BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
			// 位置
			latLng = new LatLng(info.getLatitude(), info.getLongitude());
			// 图标
			overlayOptions = new MarkerOptions()
			        .position(latLng) // 设置marker的位置
					.icon(bitmap) // 设置marker图标
					.zIndex(5); // 设置marker所在层级
			// .draggable(true); //设置手势拖拽

			marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
			Bundle bundle = new Bundle();
			bundle.putSerializable("info", info);
			marker.setExtraInfo(bundle);
		}
		// 将地图移到到最后一个经纬度位置
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
		MapStatusUpdate h=MapStatusUpdateFactory.zoomTo(mBaiduMap.getMaxZoomLevel());
		mBaiduMap.setMapStatus(u);
		mBaiduMap.setMapStatus(h);
	}

	
	
	//增加Marker点击事件
	private void addListener() {
		//对Marker的点击
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				//获得marker中的数据  
                Info info = (Info) marker.getExtraInfo().get("info");
                Toast.makeText(MainActivity.this, info.getName(), 500).show();
                
				return true;
			}
		});
			
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
}
