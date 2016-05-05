package expressive.harbinger.helpflipper;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HelpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class HelpFragment extends Fragment
        implements GestureDetector.OnGestureListener, View.OnTouchListener {

    private GestureDetectorCompat mDetector;

    private OnFragmentInteractionListener mListener;

    private List<ViewGroup> layouts = new ArrayList<>();

    private ViewAnimator vf;

    public HelpFragment() {

    }

    public static HelpFragment newInstance(List<ViewGroup> layouts) {

        Bundle args = new Bundle();

        HelpFragment fragment = new HelpFragment();
        fragment.setArguments(args);
        fragment.layouts = layouts;

        return fragment;
    }
    public List<ViewGroup> getLayouts() {
        return layouts;
    }

    public void setLayouts(List<ViewGroup> layouts) {
        this.layouts = layouts;
    }

    public void setupGestureDetector(View layout) {



        layout.setFocusable(true);
        layout.setFocusableInTouchMode(true);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                ((IFragmentWithSwipes) ((AppCompatActivity) getContext())
//                        .getSupportFragmentManager()
//                        .findFragmentById(fragmentId)).setGestureDetector(mDetector);
                HelpFragment.this.mDetector.onTouchEvent(event);
                return true;
            }
        });
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_help, container, false);

        return setupFragmentLayout(v);
    }


    public View setupFragmentLayout(View v) {
        this.mDetector = new GestureDetectorCompat(getContext(),this);

        vf = (ViewAnimator)v.findViewById(R.id.viewFlipper);

        if (layouts.size() == 0) throw new RuntimeException(layouts.toString());

        for (ViewGroup item: layouts) {
            setupGestureDetector(item);
            ScrollView sv = new ScrollView(getContext());
            sv.setLayoutParams(item.getLayoutParams());

            sv.addView(item);

            vf.addView(sv);

            //vf.addView(item);
        }


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
//        ((IFragmentWithSwipes) ((AppCompatActivity) getContext())
//                .getSupportFragmentManager()
//                .findFragmentById(fragmentId)).setGestureDetector(mDetector)
//        super.ononTouchEvent(event);
        mDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
//        ((IFragmentWithSwipes) ((AppCompatActivity) getContext())
//                .getSupportFragmentManager()
//                .findFragmentById(fragmentId)).setGestureDetector(mDetector);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
//        ((IFragmentWithSwipes) ((AppCompatActivity) getContext())
//                .getSupportFragmentManager()
//                .findFragmentById(fragmentId)).setGestureDetector(mDetector);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        if (isLeftToRight(event1, event2)) {
            final Animation inAnim = AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left);
            final Animation outAnim = AnimationUtils.loadAnimation(getContext(),android.R.anim.slide_out_right);

            vf.setInAnimation(inAnim);
            vf.setOutAnimation(outAnim);

            vf.showPrevious();
        } else {

            final Animation inAnim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_left);
            final Animation outAnim = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);

            vf.setInAnimation(inAnim);
            vf.setOutAnimation(outAnim);

            vf.showNext();
        }

        return false;
    }

    private boolean isLeftToRight(MotionEvent event1, MotionEvent event2) {
        return (event1.getX() - event2.getX() < 0);
    }


}
