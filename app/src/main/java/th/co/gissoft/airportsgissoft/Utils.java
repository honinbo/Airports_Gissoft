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
        String file = filename +".png";
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
}
