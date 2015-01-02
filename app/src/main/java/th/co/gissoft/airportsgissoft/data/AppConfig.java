package th.co.gissoft.airportsgissoft.data;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class AppConfig {

    public static String APPLICATION_TAG = "Airport_Tag";

    // Path Database
    public static String ASSETSDB = "Flights.db3";
    public static String PATHANDROID = "/Android/data/";
    public static String PATHDATABASE = "/database";
    private static String mDataBasePath = null;

    public String url_basemap = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";

    public static String getmDataBasePath() {
        return mDataBasePath;
    }

    public static void createPath(Context cx) {
        StringBuilder mainPath = new StringBuilder();
        mainPath.append(Environment.getExternalStorageDirectory());
        mainPath.append(PATHANDROID);
        mainPath.append(cx.getPackageName());
//        mainPath.append(File.separatorChar);
        mainPath.append(PATHDATABASE);
        File dbPath = new File(mainPath.toString());

        mainPath.append(File.separatorChar);
        mainPath.append(ASSETSDB);
        mDataBasePath = mainPath.toString();
        if (!dbPath.exists()) {
            dbPath.mkdirs();
            InputStream assetsDB = null;
            File file = new File(mDataBasePath);
            try {
                assetsDB = cx.getAssets().open(ASSETSDB);
                OutputStream dbOut = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int length = 1024;
                while ((length = assetsDB.read(buffer)) > 0) {
                    dbOut.write(buffer, 0, length);
                }

                dbOut.flush();
                dbOut.close();
                assetsDB.close();
            } catch (IOException e) {
                Log.e(AppConfig.APPLICATION_TAG, e.toString());
            }

        }
    }
}
