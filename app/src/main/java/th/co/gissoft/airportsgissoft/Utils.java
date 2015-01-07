package th.co.gissoft.airportsgissoft;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

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

    public double getDistance(double lat1, double lon1, double lat2, double lon2)
    {
//        WebMercator sourceMercator = CoordinateConverter.LLT2WebMercator(lat1, lon1);
//        WebMercator destMercator = CoordinateConverter.LLT2WebMercator(lat2, lon2);
        double r = 6371;

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;
        double dPhi = Math.log( Math.tan(Math.PI/4 + lat2/2) / Math.tan(Math.PI/4 + lat1/2) );

        double q = Math.abs(dlon) > Math.pow(10, -11) ? dlat/dPhi : Math.cos(lat1);

        if (Math.abs(dlon) > Math.PI)
            dlon = dlon > 0 ? -(2*Math.PI - dlon) : (2*Math.PI+dlon);

        double distance = Math.sqrt(dlat*dlat + q*q*dlon*dlon) * r;


        return distance;
    }
}
