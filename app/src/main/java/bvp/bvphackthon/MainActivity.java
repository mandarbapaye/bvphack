package bvp.bvphackthon;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import bvp.bvphackthon.models.Post;

public class MainActivity extends Activity
        implements MainActivityFragment.PostsListFragmentListener,
        PostDetailsFragment.PostDetailsFragmentListener {

    private PostDetailsFragment postDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    @Override
    public void onPostListItemClick(Post post) {
        BVPHackthonApplication.putInCache(post.getObjectId(), post);

//        if (postDetailsFragment == null) {
            postDetailsFragment = PostDetailsFragment.newInstance(post.getObjectId());
//        } else {
//            postDetailsFragment.setPostId(post.getObjectId());
//        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, postDetailsFragment, "post_details");
        transaction.addToBackStack("details");
        transaction.commit();
    }

    @Override
    public void onConfirmation() {
        Log.d("BVP", "Pickup Confirmed");
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}


