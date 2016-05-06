package expressive.harbinger.helpflipper.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.InputStream;

public class GifView extends View {

    private Movie movie;
    private int resource;
    private InputStream inputStream;
    private long movieStart;

    public GifView(Context context, int resourceLocation) {
        super(context);
        inputStream = context.getResources().openRawResource(resourceLocation);
        movie = Movie.decodeStream(inputStream);
//        setLayoutParams(new LinearLayout.LayoutParams(
//                500,500/*LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT*/));
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();
        if (movieStart == 0) { // first time
            movieStart = now;
        }
        if(movie != null) {
            int relTime = (int) ((now - movieStart) % movie.duration());
            movie.setTime(relTime);
            int position = ((View) getParent()).getWidth() / 2 - movie.width() / 2;
            movie.draw(canvas, position, 0);
        }
        this.invalidate();
    }

    @Override
    public void onDetachedFromWindow() {
        movie = null;
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputStream = null;

        super.onDetachedFromWindow();
    }
}
