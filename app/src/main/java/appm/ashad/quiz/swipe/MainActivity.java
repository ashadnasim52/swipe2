package appm.ashad.quiz.swipe;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {





    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

//
//
//        mylistofanswer.add("ashad");
//        mylistofanswer.add(",\"nasum\",\"is\"");
//        mylistofanswer.add("fsdfdfgdfg");
//        mylistofanswer.add("asfdsdfdsfhad");
        final ArrayList<String> name = new ArrayList<>();


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),name);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });




//url to make request
        String myurl="https://learncodeonline.in/api/android/datastructure.json";

        JsonObjectRequest myjsonObjectRequest=new JsonObjectRequest(Request.Method.GET, myurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response","is "+response);

                        try {

                            JSONArray jsonArrayofmine=response.getJSONArray("questions");



                            String jsonString = jsonArrayofmine.toString();


                            Log.i("jsonarray","is "+jsonArrayofmine);


                            for (int i=0;i<jsonArrayofmine.length();i++)
                            {
                                //taking quesstion and answer
                                JSONObject jsonObjectinarray=jsonArrayofmine.getJSONObject(i);

                                String question=jsonObjectinarray.getString("question");
                                String answer=jsonObjectinarray.getString("Answer");
                                name.add(question);


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //show snackbar
            }
        }

        );
        mysingleton.getInstance(getApplicationContext()).addToRequestque(myjsonObjectRequest);






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String  text) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putString(ARG_SECTION_NUMBER,text);

            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<String> name = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm,ArrayList<String> name) { //TODO
            super(fm);
            this.name=name;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(name.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }
    }



    public void myjsonrequest()
    {
        //url to make request
        String myurl="https://learncodeonline.in/api/android/datastructure.json";

        JsonObjectRequest myjsonObjectRequest=new JsonObjectRequest(Request.Method.GET, myurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response","is "+response);

                        try {

                            JSONArray jsonArrayofmine=response.getJSONArray("questions");



//                            String jsonString = jsonArrayofmine.toString();


                            Log.i("jsonarray","is "+jsonArrayofmine);


                            for (int i=0;i<jsonArrayofmine.length();i++)
                            {
                                //taking quesstion and answer
                                JSONObject jsonObjectinarray=jsonArrayofmine.getJSONObject(i);

                                String question=jsonObjectinarray.getString("question");
                                String answer=jsonObjectinarray.getString("Answer");


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //show snackbar
            }
        }

        );
        mysingleton.getInstance(getApplicationContext()).addToRequestque(myjsonObjectRequest);


    }

}
