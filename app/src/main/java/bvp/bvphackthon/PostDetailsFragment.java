package bvp.bvphackthon;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import bvp.bvphackthon.models.Post;


public class PostDetailsFragment extends Fragment {
    private static final String POST_ID = "postId";

    private String postId;
    private Post post;
    private PostDetailsFragmentListener mListener;

    @InjectView(R.id.ivFoodImageDetails)
    ParseImageView ivFoodImageDetails;

    @InjectView(R.id.tvPostDetailsTitle)
    TextView tvPostDetailsTitle;

    @InjectView(R.id.tvMealsDetails)
    TextView tvMealsDetails;

    @InjectView(R.id.tvMinsDetails)
    TextView tvMinsDetails;

    @InjectView(R.id.tvMilesDetails)
    TextView tvMilesDetails;

    @InjectView(R.id.tvDetailsDescription)
    TextView tvDetailsDescription;

    @InjectView(R.id.tvDetailsAddress)
    TextView tvDetailsAddress;

    // TODO: Rename and change types and number of parameters
    public static PostDetailsFragment newInstance(String postId) {
        PostDetailsFragment fragment = new PostDetailsFragment();
        Bundle args = new Bundle();
        args.putString(POST_ID, postId);
        fragment.setArguments(args);
        return fragment;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public PostDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            postId = getArguments().getString(POST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post_details, container, false);
        ButterKnife.inject(this, v);

        if (postId != null) {
            post = (Post) BVPHackthonApplication.readFromCache(postId);
        }

        tvPostDetailsTitle.setText(post.getTitle());
        tvMealsDetails.setText(String.valueOf(post.getNumberOfFeeders()));
        tvMinsDetails.setText(String.valueOf("40"));
        tvMilesDetails.setText(String.valueOf("1.9"));
        tvDetailsDescription.setText(post.getDescription());
        tvDetailsAddress.setText(post.getAddress());

        ivFoodImageDetails.setParseFile(post.getPhoto());
        ivFoodImageDetails.loadInBackground();

        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (PostDetailsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public interface PostDetailsFragmentListener {
        public void onConfirmation();
    }

}
