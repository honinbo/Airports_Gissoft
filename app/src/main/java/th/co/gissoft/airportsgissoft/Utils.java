package th.co.gissoft.airportsgissoft;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import Gissoft.UnitConverter.AngleUtil;

/**
 * Created by getgo_000 on 5/1/2558.
 */
public class Utils {

    private Context mContext;

    public Utils(Context mContext) {
        this.mContext = mContext;
    }

    public Bitmap getPictureFromAssets(String filename) {
        String file = filename + ".png";
        Bitmap airlineLogo;
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        airlineLogo = BitmapFactory.decodeStream(istr);
        return airlineLogo;
    }

//    public double calculateBearing(double lat1, double lon1, double lat2, double lon2) {
//
//
//    }

    public double getBearing(double lat1, double lon1, double lat2, double lon2)
    {

        double r = 6371;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);


        double dlon = Math.toRadians(lon1-lon2);

        double y = Math.sin(dlon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) -
                Math.sin(lat1) * Math.cos(lat2) * Math.cos(dlon);

        double bearing = AngleUtil.Radian2Degree(Math.atan2(y, x));
        bearing = (bearing+360) %360;

        return bearing;
    }
}
