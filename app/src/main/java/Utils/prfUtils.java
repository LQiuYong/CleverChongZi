package Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chongzi007 on 2016/8/9.
 */
public class prfUtils {
    public static String name = "config";

    public static boolean getBoolean(Context ctx, String key, boolean value) {
        SharedPreferences mpref = ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
        boolean isuser = mpref.getBoolean(key, value);
        return isuser;

    }

    public static void setBoolean(Context ctx, String key, boolean value){
        SharedPreferences mpref = ctx.getSharedPreferences(name, Context.MODE_PRIVATE);
        mpref.edit().putBoolean(key,value).commit();

    }
}
