package bvp.bvphackthon.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import bvp.bvphackthon.BVPHackthonApplication;
import bvp.bvphackthon.R;
import bvp.bvphackthon.models.Post;
import bvp.bvphackthon.views.BVPParseImageView;


public class PostsListAdapter extends ArrayAdapter<Post> {

    private static DecimalFormat df;
    static {
        df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.CEILING);
    }

    private static class ViewHolder {
        ParseImageView ivFoodImageList;
        TextView tvMinsList;
        TextView tvMilesList;
        TextView tvMealsList;
        TextView tvPostDescription;
        TextView tvPostTitle;
        TextView tvMinsLabelList;

        LinearLayout llConfirmedOnFeed;
        LinearLayout llBlankSlate;
        int position;
    }

    public PostsListAdapter(Context context, List<Post> posts) {
        super(context, 0, posts);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Student student = getItem(position);
//
//        final ViewHolder viewHolder;
//        if (convertView != null) {
//            viewHolder = (ViewHolder) convertView.getTag();
//        } else {
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.student_list_item, parent, false);
//            viewHolder.rpivListStudentImage = (RoundedParseImageView) convertView.findViewById(R.id.rpivListStudentImage);
//            viewHolder.tvListStudentName = (TextView) convertView.findViewById(R.id.tvListStudentName);
//            convertView.setTag(viewHolder);
//        }
//
//        String currentItemNameInConvertView = viewHolder.tvListStudentName.getText().toString();
//        if (!currentItemNameInConvertView.equalsIgnoreCase(student.getName())) {
//            viewHolder.tvListStudentName.setText(student.getName());
//            Log.d("test", "~~~~~ Name: " + student.getName());
//            viewHolder.rpivListStudentImage.setImageResource(R.drawable.ic_placeholder);
//            viewHolder.rpivListStudentImage.loadParseFileImageInBackground(student.getProfileImage());
//        }
//
//        return convertView;
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);

        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.post_list_item, parent, false);
            viewHolder.ivFoodImageList = (ParseImageView) convertView.findViewById(R.id.ivFoodImageList);
            viewHolder.tvMealsList = (TextView) convertView.findViewById(R.id.tvMealsList);
            viewHolder.tvMinsList = (TextView) convertView.findViewById(R.id.tvMinsList);
            viewHolder.tvMinsLabelList = (TextView) convertView.findViewById(R.id.tvMinsLabelList);

            viewHolder.tvMilesList = (TextView) convertView.findViewById(R.id.tvMilesList);
            viewHolder.tvPostDescription = (TextView) convertView.findViewById(R.id.tvPostDescription);
            viewHolder.tvPostTitle = (TextView) convertView.findViewById(R.id.tvPostTitle);
            viewHolder.llConfirmedOnFeed = (LinearLayout) convertView.findViewById(R.id.llConfirmedOnFeed);
            viewHolder.llBlankSlate = (LinearLayout) convertView.findViewById(R.id.llBlankSlate);

            convertView.setTag(viewHolder);
        }

        viewHolder.position = position;
        viewHolder.tvMealsList.setText(String.valueOf(post.getNumberOfFeeders()));
        viewHolder.tvMilesList.setText(String.valueOf(post.getDistance()));
        viewHolder.tvPostDescription.setText(post.getDescription());
        viewHolder.tvPostTitle.setText(post.getTitle());

        Date createdAt = post.getCreatedAt();
        long elapsedTimeMs = System.currentTimeMillis() - createdAt.getTime();
        double elapsedTime = elapsedTimeMs / (1000 * 60);
        if (elapsedTime > 60) {
            elapsedTime = elapsedTime / 60;
            viewHolder.tvMinsLabelList.setText("Hrs");
        } else {
            viewHolder.tvMinsLabelList.setText("Mins");
        }
        viewHolder.tvMinsList.setText(df.format(elapsedTime));

        if (post.isClaimed() || BVPHackthonApplication.isClaimedByMe(post.getObjectId())) {
            viewHolder.llBlankSlate.setVisibility(View.VISIBLE);
            viewHolder.llConfirmedOnFeed.setVisibility(View.VISIBLE);
        } else {
            viewHolder.llBlankSlate.setVisibility(View.GONE);
            viewHolder.llConfirmedOnFeed.setVisibility(View.GONE);
        }

        ParseFile image = post.getPhoto();
        viewHolder.ivFoodImageList.setParseFile(image);
        viewHolder.ivFoodImageList.loadInBackground();

//
//        viewHolder.ivFoodImageList.loadParseFileImageInBackground(post.getPhoto(), new GetDataCallback() {
//            public void done(byte[] data, ParseException e) {
//                if (e == null) {
//                    if (viewHolder.position == position) {
//                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                        viewHolder.ivFoodImageList.setImageBitmap(bmp);
//                        viewHolder.ivFoodImageList.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//        });

        return convertView;
    }


}



