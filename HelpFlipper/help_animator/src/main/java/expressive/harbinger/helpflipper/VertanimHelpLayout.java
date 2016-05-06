package expressive.harbinger.helpflipper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import expressive.harbinger.helpflipper.utils.GifView;
import expressive.harbinger.helpflipper.utils.Graphics;

/**
 * Created by alex on 05.05.2016.
 */
public class VertanimHelpLayout extends LinearLayout {

    float scale = 4f/3f;

    private VertanimHelpLayout(Context context) {
        super(context);
    }

    private VertanimHelpLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private VertanimHelpLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static VertanimHelpLayout defaultInstance(Context ctx, String message, GifView gv) {
        VertanimHelpLayout vhl = new VertanimHelpLayout(ctx);
        vhl.setParams();
        vhl.setupLayout(message, gv);

        return vhl;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public VertanimHelpLayout setupLayout(String message, GifView gv) {

        TextView tv = new TextView(getContext());
        tv.setPadding(50, 50, 50, 50);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(message);

        LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                (int)(Graphics.getRowDimensions(getContext(), scale).y*0.7)));
        ll.setGravity(Gravity.CENTER_HORIZONTAL);

        gv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                (int)(Graphics.getRowDimensions(getContext(), scale).y*0.7)));
        ll.addView(gv);
        //gv.setPadding(10, 10, 10, 10);

        //image.recycle();
        return addWidgetView(tv).addWidgetView(ll);
    }

    public VertanimHelpLayout setParams () {

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                Graphics.getRowDimensions(getContext(), scale).y);

        setLayoutParams(lp);
        setOrientation(LinearLayout.VERTICAL);

        return this;
    }

    public VertanimHelpLayout addOnClickListener(OnClickListener listener) {
        setOnClickListener(listener);
        return this;
    }

    public VertanimHelpLayout addWidgetView(View v) {
        addView(v);
        return this;
    }
}
