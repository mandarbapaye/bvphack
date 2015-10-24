package bvp.bvphackthon;

import android.app.Activity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import bvp.bvphackthon.models.Post;


public class PostDetailsFragment extends Fragment {
    private static final String POST_ID = "postId";

    private String postId;
    private Post post;
    private boolean isClaimClicked = false;
    private PostDetailsFragmentListener mListener;

    private String selectedTime = "30";

    @InjectView(R.id.ivFoodImageDetails)
    ParseImageView ivFoodImageDetails;

    @InjectView(R.id.ivClaim)
    ImageView ivClaim;

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

    @InjectView(R.id.llArrivalTime)
    LinearLayout llArrivalTime;


    @InjectView(R.id.timer5)
    TextView timer5;

    @InjectView(R.id.timer10)
    TextView timer10;

    @InjectView(R.id.timer20)
    TextView timer20;

    @InjectView(R.id.timer30)
    TextView timer30;

    @InjectView(R.id.timer45)
    TextView timer45;

    @InjectView(R.id.timer60)
    TextView timer60;


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

        ivClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClaimClicked) {
                    ivClaim.setImageResource(R.drawable.confirm_button);
                    llArrivalTime.setVisibility(View.VISIBLE);
                } else {
                    BVPHackthonApplication.putInClaimedPostsCache(post);
                    mListener.onConfirmation();
                }
            }
        });

        timer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

        timer10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

        timer20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

        timer30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

        timer45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

        timer60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markTextAsSelected((TextView)v);
            }
        });

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

    private void markTextAsSelected(TextView textView) {
        selectedTime = textView.getText().toString();
        textView.setTextColor(getResources().getColor(R.color.teal));
        textView.setTypeface(null, Typeface.BOLD);

        markTextAsDeSelected();
    }

    private void markTextAsDeSelected() {
        TextView[] tvs = {timer5, timer10, timer20, timer30, timer45, timer60};

        for (TextView tv : tvs) {
            if (!tv.getText().toString().equalsIgnoreCase(selectedTime)) {
                tv.setTextColor(getResources().getColor(android.R.color.black));
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setBackground(null);
            }
        }

    }


}
