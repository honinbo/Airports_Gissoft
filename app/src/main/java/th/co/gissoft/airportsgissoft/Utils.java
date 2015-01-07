package th.co.gissoft.airportsgissoft;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.esri.core.geometry.Point;

import java.io.IOException;
import java.io.InputStream;

import Gissoft.UnitAdapter.WebMercator;
import Gissoft.UnitConverter.CoordinateConverter;

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

        double r = 3958;
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double dlat = Math.toRadians(lat2-lat1);
        double dlon = Math.toRadians(lon2- lon1);
        double a = Math.sin(dlat/2) * Math.sin(dlat/2) +
            Math.cos(lat1) * Math.cos(lat2) *
                    Math.sin(dlon/2) * Math.sin(dlon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        double distance = (r * c);

        return Math.floor(distance * 100) / 100;
    }

    public double getAngle(double lat1, double lon1, double lat2, double lon2) {

        WebMercator sourceMercator = CoordinateConverter.LLT2WebMercator(lat1, lon1);
        Point sourcePoint = new Point(sourceMercator.X, sourceMercator.Y);
        WebMercator destMercator = CoordinateConverter.LLT2WebMercator(lat2, lon2);
        Point destPoint = new Point(destMercator.X, destMercator.Y);

        double dlat = destPoint.getX() - sourcePoint.getX();
        double dlon = destPoint.getY() - sourcePoint.getY();

        double angle = Math.toDegrees(Math.atan2(dlon, dlat));
        if (angle < 0)
            angle += 360;

        return angle;
    }
}
