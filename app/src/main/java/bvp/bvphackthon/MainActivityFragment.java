package bvp.bvphackthon;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.FindCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import bvp.bvphackthon.adapters.PostsListAdapter;
import bvp.bvphackthon.models.Post;
import bvp.bvphackthon.utils.ParseClient;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @InjectView(R.id.lvPosts)
    ListView lvPosts;

    List<Post> postsList;
    PostsListAdapter postsListAdapter;

    PostsListFragmentListener mListener;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postsList = new ArrayList<Post>();
        postsListAdapter = new PostsListAdapter(getActivity(), postsList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, v);

        setupHandlers();

        loadPostsDataFromParse();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        lvPosts.setAdapter(postsListAdapter);
    }

    private void setupHandlers() {
        lvPosts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onPostListItemClick(postsListAdapter.getItem(position));
            }
        });
    }

    private void loadPostsDataFromParse() {
        ParseClient.getAll(Post.class, new FindCallback<Post>() {
            @Override
            public void done(List<Post> postsList, com.parse.ParseException e) {
                if (e == null) {
                    for (Post post : postsList) {
                        postsListAdapter.add(post);
                    }
                    postsListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (PostsListFragmentListener) activity;
        } catch (ClassCastException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface PostsListFragmentListener {
        void onPostListItemClick(Post post);
    }

}


