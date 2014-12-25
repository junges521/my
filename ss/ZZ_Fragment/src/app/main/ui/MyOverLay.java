package app.main.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MyOverLay extends ItemizedOverlay<OverlayItem> {  
	  
    private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();  
    private Context mContext;  
  
    private double mLat1 = 39.90923;//39.9022; // point1纬度  
    private double mLon1 = 116.397428;//116.3822; // point1经度  
  
    private double mLat2 = 39.9022;  
    private double mLon2 = 116.3922;  
  
    private double mLat3 = 39.917723;  
    private double mLon3 = 116.3722;  
  
    public MyOverLay(Drawable marker, Context context,MapView mMapView){  
        super(marker, mMapView);  
        this.mContext= context;  
        // 用给定的经纬度构造GeoPoint，单位是微度 (�?* 1E6)  
        GeoPoint p1 = new GeoPoint((int)(mLat1 * 1E6), (int)(mLon1 * 1E6));  
        GeoPoint p2 = new GeoPoint((int)(mLat2 * 1E6), (int)(mLon2 * 1E6));  
        GeoPoint p3 = new GeoPoint((int)(mLat3 * 1E6), (int)(mLon3 * 1E6));  
  
        GeoList.add(new OverlayItem(p1, "P1", "point1"));  
        GeoList.add(new OverlayItem(p2, "P2", "point2"));  
        GeoList.add(new OverlayItem(p3, "P3", "point3"));         
        //populate();//createItem(int)方法构�?item。一旦有了数据，在调用其它方法前，首先调用这个方�? 
    }  
  
    @Override  
    protected OverlayItem createItem(int i){  
        return GeoList.get(i);  
    }  
  
    @Override  
    public int size(){  
        return GeoList.size();  
    }  
  
    @Override  
    // 处理当点击事�? 
    protected boolean onTap(int i){  
        Toast.makeText(this.mContext, GeoList.get(i).getSnippet(), Toast.LENGTH_SHORT).show();  
        return true;  
    }

  
  
}  
