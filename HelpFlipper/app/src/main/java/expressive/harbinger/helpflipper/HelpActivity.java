package expressive.harbinger.helpflipper;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        List<ViewGroup> layouts = new ArrayList<>();

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Food", BitmapFactory.decodeResource(getResources(),
                        R.drawable.food_and_drink)));

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Clothes", BitmapFactory.decodeResource(getResources(),
                        R.drawable.clothes)));

        layouts.add(VertivalHelpLayout.defaultInstance(this,
                "Accomodation", BitmapFactory.decodeResource(getResources(),
                        R.drawable.accomodation)));

        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_layout,
                HelpFragment.newInstance(layouts),
                "VIEW_FLIPPER").commit();
    }
}
