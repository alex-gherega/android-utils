package expressive.harbinger.helpflipper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by alex on 27.04.2016.
 */
public class Graphics {

    public static GradientDrawable getBorder(int hexColor, int radius, int hexStroke, int strokeWidth) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(hexColor);
        gd.setCornerRadius(radius);
        gd.setStroke(strokeWidth, hexStroke);
        return gd;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap b, int pixels) {
        Bitmap output = Bitmap.createBitmap(b.getWidth(), b
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int c = 0xff424242;
        final Paint p = new Paint();
        final Rect rect = new Rect(0, 0, b.getWidth(), b.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        p.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        p.setColor(c);
        canvas.drawRoundRect(rectF, roundPx, roundPx, p);

        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(b, rect, rect, p);

        return output;
    }

    public static Point getRowDimensions(Context ctx, float scale) {

        Display display = ((Activity) ctx).getWindowManager().getDefaultDisplay();

        DisplayMetrics outMetrics = new DisplayMetrics ();

        display.getMetrics(outMetrics);

        return new Point((int)(outMetrics.widthPixels/scale),
                (int)(outMetrics.heightPixels/scale));
    }

    public static int getScaleForRowDimensions(Context ctx, int y) {


        Display display = ((Activity) ctx).getWindowManager().getDefaultDisplay();

        DisplayMetrics outMetrics = new DisplayMetrics ();

        display.getMetrics(outMetrics);

        return outMetrics.heightPixels/y;
    }

    public static ImageButton getImageButton(Context ctx, int iconID) {
        ImageButton categoryButton = new ImageButton(ctx);
        // categoryButton.setImageBitmap(BitmapFactory.decodeResource(getResources(), iconID));
        categoryButton.setImageResource(iconID);
        categoryButton.setAdjustViewBounds(true);
        categoryButton.setScaleType(ImageView.ScaleType.FIT_START);
        categoryButton.setBackground(null);
        categoryButton.setBackgroundColor(Color.TRANSPARENT);

        return categoryButton;
    }

    public static ImageButton getImageButton(Context ctx, int iconID, StateListDrawable states) {
        ImageButton categoryButton = new ImageButton(ctx);
        // categoryButton.setImageBitmap(BitmapFactory.decodeResource(getResources(), iconID));

        //categoryButton.setImageResource(iconID);
        categoryButton.setImageDrawable(states);
        categoryButton.setAdjustViewBounds(true);
        categoryButton.setScaleType(ImageView.ScaleType.FIT_START);
        categoryButton.setBackground(null);
        categoryButton.setBackgroundColor(Color.TRANSPARENT);

        return categoryButton;
    }

    /** read bitmaps efficiently as stated in the Android doc:
     * http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
     * **/

    public static BitmapFactory.Options readBitmapDimensions(Context ctx, int imageID) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(ctx.getResources(), imageID, options);
        return options;
    }

    public static int getBitmapSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Context ctx, int resId,
                                                         int reqWidth, int reqHeight) {

        Resources res = ctx.getResources();
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = getBitmapSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
