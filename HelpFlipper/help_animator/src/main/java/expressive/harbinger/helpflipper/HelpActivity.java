package expressive.harbinger.helpflipper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import expressive.harbinger.helpflipper.utils.Graphics;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        List<ViewGroup> layouts = new ArrayList<>();

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Food", Graphics.decodeSampledBitmapFromResource(this,
                        R.drawable.swipe,
                        100,100)));

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Clothes", Graphics.decodeSampledBitmapFromResource(this,
                        R.drawable.swipe,
                        100,100)));

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Accomodation", Graphics.decodeSampledBitmapFromResource(this,
                        R.drawable.swipe,
                        100,100)));


        // layouts.add(VertanimHelpLayout.defaultInstance(this,"Hello anim", R.raw.start_screen_01));

        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_layout,
                HelpFragment.newInstance(layouts),
                "VIEW_FLIPPER").commit();
    }
}
