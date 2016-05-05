package expressive.harbinger.helpflipper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import expressive.harbinger.helpflipper.utils.Graphics;

/**
 * Created by alex on 05.05.2016.
 */
public class VertivalHelpLayout extends LinearLayout {

    float scale = 4f/3f;

    private VertivalHelpLayout(Context context) {
        super(context);
    }

    private VertivalHelpLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private VertivalHelpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static VertivalHelpLayout defaultInstance(Context ctx, String message, Bitmap image) {
        VertivalHelpLayout vhl = new VertivalHelpLayout(ctx);
        vhl.setParams();
        vhl.setupLayout(message, image);

        return vhl;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public VertivalHelpLayout setupLayout(String message, Bitmap image) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, out);


        TextView tv = new TextView(getContext());
        tv.setPadding(50, 50, 50, 50);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(message);

        ImageView iv = new ImageView(getContext());
        iv.setPadding(10, 10, 10, 10);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setImageBitmap(BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray())));

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //image.recycle();
        return addWidgetView(tv).addWidgetView(iv);
    }

    public VertivalHelpLayout setParams () {

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                Graphics.getRowDimensions(getContext(), scale).y);

        setLayoutParams(lp);
        setOrientation(LinearLayout.VERTICAL);

        return this;
    }

    public VertivalHelpLayout addOnClickListener(View.OnClickListener listener) {
        setOnClickListener(listener);
        return this;
    }

    public VertivalHelpLayout addWidgetView(View v) {
        addView(v);
        return this;
    }
}
