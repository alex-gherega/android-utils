package expressive.harbinger.helpflipper.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
}
